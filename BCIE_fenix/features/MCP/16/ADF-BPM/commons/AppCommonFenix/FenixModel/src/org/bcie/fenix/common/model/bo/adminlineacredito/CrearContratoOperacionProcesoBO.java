package org.bcie.fenix.common.model.bo.adminlineacredito;

import java.sql.Timestamp;

import java.util.Objects;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.bo.BaseBO;
import org.bcie.fenix.common.model.vo.ClienteOperacionVOImpl;
import org.bcie.fenix.common.model.vo.ClienteOperacionVORowImpl;
import org.bcie.fenix.common.model.vo.ContratoVOImpl;
import org.bcie.fenix.common.model.vo.ContratoVORowImpl;
import org.bcie.fenix.common.model.vo.CuentasClienteOperacionVOImpl;
import org.bcie.fenix.common.model.vo.CuentasClienteOperacionVORowImpl;
import org.bcie.fenix.common.model.vo.MontoOperacionVOImpl;
import org.bcie.fenix.common.model.vo.TerminosOperacionVOImpl;
import org.bcie.fenix.common.model.vo.TerminosOperacionVORowImpl;

/**
 * [KB:14513]
 * Crea un contrato para la operación en el proceso actual.
 * Además lo actualiza con los valores encontrados en otras tablas.
 * ATENCION: Una vez ejecutada (método execute es invocado), no podrá volverse a ejecutar y una nueva instancia será requerida.
 */
public class CrearContratoOperacionProcesoBO extends BaseBO {

    private static ADFLogger logger = ADFLogger.createADFLogger(CrearContratoOperacionProcesoBO.class);

    /** Identificador la operacion actual.*/
    private Long idOperacion;

    /** Instancia del proceso actual.*/
    private String instanciaProceso;

    /** Identificador del contrato del proceso.*/
    private Long idContratoProceso;

    public CrearContratoOperacionProcesoBO(FenixAMImpl fenixAM, Long idOperacion, String instanciaProceso) {
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

    public Long getIdContratoProceso() {
        return this.idContratoProceso;
    }

    /**
     * Ejecuta esta operación de negocio.
     * Crea un contrato para el proceso actual y asigna valores a sus columnas basandose en otras tablas.
     */
    @Override
    public void execute() {
        FenixAMImpl fenixAM = this.getAM();

        //si el recurso ya fue liberado, entonces no se puede continuar y se debe crear otra instancia de esta clase
        if (fenixAM == null) {
            logger.warning("Instancia ya liberada, no se continuará.");
            throw new IllegalStateException("El AM de esta instancia ya fue liberado, por lo tanto no puede volver a usarse. Debe crear una nueva instancia de esta clase.");
        }

        logger.info("Iniciando creacion del contrato.");

        try {
            //Recupera el contrato por idOperacion e InstanciaProceso
            ContratoVORowImpl contratoRow =
                fenixAM.getContratoVO().getContratoByOperacionInstanciaProceso(this.idOperacion, this.instanciaProceso);
            if (contratoRow != null) {
                logger.warning("Ya existe un contrato con idOperacion: {0} e instanciaProceso: {1}. No se creaá otro.", new Object[] {
                               idOperacion, instanciaProceso
                });
                return;
            }

            this.crearContratoInstanciaProceso();

        } finally {
            //Se asegura de liberar recursos por si de casualidad el cliente de esta clase no lo hace.
            try {
                this.close();
            } catch (Exception ex) {
                logger.severe(ex);
            }
        }

        logger.info("Finaliza creacion del contrato.");
    }

    /**
     * Crea un contrato para el proceso y asigna los valores a sus columnas.
     */
    private void crearContratoInstanciaProceso() {
        FenixAMImpl fenixAM = this.getAM();

        //Obtenemos los datos necesarios para actualizar el contrato recien creado
        Integer idMontoOperacion = this.obtenerIdMontoOperacion();
        Long idMontoLong = idMontoOperacion != null ? idMontoOperacion.longValue() : null;
        Timestamp fechaEscrituracion = this.obtenerFechaContrato(FenixModelConstants.TERMINO_FECHA_ESCRITURACION);
        Timestamp fechaVigencia = this.obtenerFechaContrato(FenixModelConstants.TERMINO_FECHA_VIGENCIA);
        String cuentaCliente = this.obtenerCuentaCliente();

        ContratoVOImpl contratoVO = fenixAM.getContratoVO();
        //Se obtiene el id del contrato. Como ya se ha validado que no existe contrato para el proceso, este metodo crea uno nuevo.
        this.idContratoProceso =
            contratoVO.getIdContratoByOperacionInstanciaProceso(this.idOperacion, this.instanciaProceso);
        //Actualiza los campos con la informacion consultada
        contratoVO.actualizarDatosContrato(this.idContratoProceso, idMontoLong, fechaEscrituracion, fechaVigencia,
                                           cuentaCliente);
    }

    /**
     * Obtiene el ID del monto de la operación o null si no se encuentra alguno.
     * @return ID del monto de la operación o null si no se encuentra alguno.
     */
    private Integer obtenerIdMontoOperacion() {
        FenixAMImpl fenixAM = this.getAM();

        Integer idMontoOperacion = null;
        MontoOperacionVOImpl montoOperacionVO = fenixAM.getMontoOperacionVO();
        ViewCriteria criteriaById =
            montoOperacionVO.getViewCriteriaManager().getViewCriteria("MontoOperacionVOCriteriaByIdOperacion");
        if (criteriaById != null) {
            try {
                criteriaById.ensureVariableManager().setVariableValue("varIdOperacion", this.idOperacion);
                criteriaById.ensureVariableManager().setVariableValue("varTipoMonto",
                                                                      FenixModelConstants.TIPO_MONTO_ESCRITURADO);
                montoOperacionVO.applyViewCriteria(criteriaById);
                montoOperacionVO.executeQuery();

                Row row = montoOperacionVO.first();
                if (row != null) {
                    idMontoOperacion = (Integer) row.getAttribute("Id");
                }
            } finally {
                montoOperacionVO.getViewCriteriaManager().removeApplyViewCriteriaName("MontoOperacionVOCriteriaByIdOperacion");
            }
        }

        return idMontoOperacion;
    }

    /**
     * Obtiene la cuenta de cliente de la operacion.
     * Se retorna la primera cuenta contrada en la consulta de CuentasClienteOperacionVO.
     * @return Cuenta del cliente o null si no se encuentra.
     */
    private String obtenerCuentaCliente() {
        FenixAMImpl fenixAM = this.getAM();

        String cuentaCliente = null;

        CuentasClienteOperacionVOImpl cuentasClienteOperacionVO = fenixAM.getCuentasClienteOperacionVO();
        cuentasClienteOperacionVO.setpIdOperacion(this.idOperacion);
        cuentasClienteOperacionVO.executeQuery();

        CuentasClienteOperacionVORowImpl row = (CuentasClienteOperacionVORowImpl) cuentasClienteOperacionVO.first();
        if (row != null) {
            cuentaCliente = row.getCuenta();
        }

        return cuentaCliente;
    }

    /**
     * Obtiene la fecha de la operación del tipo término pasado por param.
     * @param tipoFecha Id Tipo termino a buscar.
     * @return Fecha del tipo términto pasado por param de la operación.
     */
    private Timestamp obtenerFechaContrato(final Integer tipoFecha) {
        FenixAMImpl fenixAM = this.getAM();

        Timestamp fecha = null;

        TerminosOperacionVOImpl terminosOperacionVO = fenixAM.getTerminosOperacionVO();
        terminosOperacionVO.setpIdOperacion(this.idOperacion);
        terminosOperacionVO.setpIdTipoTermino(tipoFecha);
        terminosOperacionVO.executeQuery();

        TerminosOperacionVORowImpl row = (TerminosOperacionVORowImpl) terminosOperacionVO.first();
        if (row != null) {
            fecha = row.getFecha();
        }

        return fecha;
    }

    /**
     * Obtiene el dia de pago del cliente de la operación.
     * @return Día de pago del cliente de la operación o nulo si no lo hay.
     */
    private Integer obtenerDiaPago() {
        FenixAMImpl fenixAM = this.getAM();

        Integer dia = null;

        ClienteOperacionVOImpl clienteOperacionVO = fenixAM.getClienteOperacionVO();
        clienteOperacionVO.setpIdOperacion(this.idOperacion);
        clienteOperacionVO.executeQuery();

        ClienteOperacionVORowImpl row = (ClienteOperacionVORowImpl) clienteOperacionVO.first();
        if (row != null) {
            dia = row.getDiaPago();
        }

        return dia;
    }
}
