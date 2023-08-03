package org.bcie.fenix.common.model.bo.adminlineacredito;

import java.math.BigDecimal;

import java.util.Objects;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.bo.BaseBO;
import org.bcie.fenix.common.model.vo.CatCuentaClienteLOVImpl;
import org.bcie.fenix.common.model.vo.ContratoVORowImpl;
import org.bcie.fenix.common.model.vo.CuentasClienteOperacionVOImpl;
import org.bcie.fenix.common.model.vo.CuentasClienteOperacionVORowImpl;
import org.bcie.fenix.common.model.vo.EncabezadoRegistrarDatosLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.EncabezadoRegistrarDatosLineaCreditoVORowImpl;
import org.bcie.fenix.common.model.vo.MontoOperacionVOImpl;

/**
 * [KB:14513]
 * Permite la carga del contrato del proceso a memoria.
 * Se encarga de configurar las demas VO que se requieran para su visualización en la vista de admin líneas de crédito.
 * ATENCION: Una vez ejecutada (método execute es invocado), no podrá volverse a ejecutar y una nueva instancia será requerida.
 */
public class CargarContratoOperacionProcesoBO extends BaseBO {

    private static ADFLogger logger = ADFLogger.createADFLogger(CargarContratoOperacionProcesoBO.class);

    /** Identificador la operacion actual.*/
    private Long idOperacion;

    /** Instancia del proceso actual.*/
    private String instanciaProceso;

    public CargarContratoOperacionProcesoBO(FenixAMImpl fenixAM, Long idOperacion, String instanciaProceso) {
        super(fenixAM);

        Objects.requireNonNull(idOperacion, "idOperacion es requerido.");
        Objects.requireNonNull(instanciaProceso, "instanciaProceso es requerido.");

        this.idOperacion = idOperacion;
        this.instanciaProceso = instanciaProceso;
    }

    @Override
    public FenixAMImpl getAM() {
        return (FenixAMImpl) super.getAM();
    }

    /**
     * Ejecuta esta operación de negocio.
     * Carga la info del contrato a objeto de memoria usado en la vista admin líneas de crédito.
     */
    @Override
    public void execute() {
        //si el recurso ya fue liberado, entonces no se puede continuar y se debe crear otra instancia de esta clase
        if (this.getAM() == null) {
            logger.warning("Instancia ya liberada, no se continuará.");
            throw new IllegalStateException("El AM de esta instancia ya fue liberado, por lo tanto no puede volver a usarse. Debe crear una nueva instancia de esta clase.");
        }

        logger.info("Iniciando carga de contrato a memoria.");

        try {
            EncabezadoRegistrarDatosLineaCreditoVORowImpl encabezado = this.cargarContratoEnMemoria();
            this.configurarLOVs(encabezado);
        } finally {
            //Se asegura de liberar recursos por si de casualidad el cliente de esta clase no lo hace.
            try {
                this.close();
            } catch (Exception ex) {
                logger.severe(ex);
            }
        }

        logger.info("Finaliza carga de contrato a memoria.");
    }

    /**
     * Carga datos del contrato del proceso desde base de datos a memoria (EncabezadoRegistrarDatosLineaCreditoVO) para que sean
     * usadas por la vista en admin lineas de credito.
     * @return Encabezado creado con la información de base de datos.
     */
    private EncabezadoRegistrarDatosLineaCreditoVORowImpl cargarContratoEnMemoria() {
        FenixAMImpl fenixAM = this.getAM();

        //limpiamos objeto en memoria que usa la vista admin lineas credito para mostrar la info del contrato
        EncabezadoRegistrarDatosLineaCreditoVOImpl encabezadoVO = fenixAM.getEncabezadoRegistrarDatosLineaCreditoVO();
        encabezadoVO.executeQuery();

        //Recupera el contrato por idOperacion e InstanciaProceso
        ContratoVORowImpl contratoRow =
            fenixAM.getContratoVO().getContratoByOperacionInstanciaProceso(this.idOperacion, this.instanciaProceso);
        if (contratoRow == null) {
            logger.warning("No se encontró un contrato con idOperacion: {0} e instanciaProceso: {1}", new Object[] {
                           idOperacion, instanciaProceso
            });
            return null;
        }

        //Obtenemos la info del monto escrituracion desde base de datos
        MontoOperacionVOImpl montoOperacionVO = fenixAM.getMontoOperacionVO();
        BigDecimal montoEscrituradoValue =
            montoOperacionVO.obtenerMontoPorTipo(this.idOperacion, FenixModelConstants.TIPO_MONTO_ESCRITURADO);

        EncabezadoRegistrarDatosLineaCreditoVORowImpl encabezadoRow =
            (EncabezadoRegistrarDatosLineaCreditoVORowImpl) encabezadoVO.insertCopyOf(contratoRow,
                                                                                      montoEscrituradoValue);
        encabezadoVO.setCurrentRow(encabezadoRow);

        return encabezadoRow;
    }

    /**
     * Carga las LOVs requeridas por la pantalla de admin lineas de credito respecto aal encabezado (contrato).
     * @param encabezado Encabezado con los valores que deben ser selecionados en las LOVs.
     */
    private void configurarLOVs(final EncabezadoRegistrarDatosLineaCreditoVORowImpl encabezado) {
        FenixAMImpl fenixAM = this.getAM();

        // Se limpia CatCuentaClienteLOV antes de ser llenarla, para FNXII-6178
        CatCuentaClienteLOVImpl cuentaClienteLOV = fenixAM.getCatCuentaClienteLOV();
        cuentaClienteLOV.executeQuery();

        if (encabezado == null) {
            logger.warning("Encabezado es nulo. No se continua con la carga de LOVs del encabezado.");
            return;
        }

        //obtiene las cuentas cliente de la operacion para llenar el objeto en memoria
        CuentasClienteOperacionVOImpl cuentasClienteOperacionVO = fenixAM.getCuentasClienteOperacionVO();
        cuentasClienteOperacionVO.setpIdOperacion(this.idOperacion);
        cuentasClienteOperacionVO.executeQuery();
        RowSetIterator rsi = cuentasClienteOperacionVO.createRowSetIterator(null);
        try {
            while (rsi.hasNext()) {
                CuentasClienteOperacionVORowImpl rowCuentaCliente = (CuentasClienteOperacionVORowImpl) rsi.next();
                cuentaClienteLOV.insertCopyOf(rowCuentaCliente);
            }
        } finally {
            rsi.closeRowSetIterator();
        }

        Row[] rows = cuentaClienteLOV.getFilteredRows("CuentaCliente", encabezado.getCuentaCliente());
        if (rows != null && rows.length > 0) {
            cuentaClienteLOV.setCurrentRow(rows[0]);
        }
    }
}
