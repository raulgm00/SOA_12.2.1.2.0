package org.bcie.fenix.common.model.bo.adminlineacredito;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;

import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.bo.BaseBO;
import org.bcie.fenix.common.model.vo.DatosLineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoAsociadaOperacionVOImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoAsociadaOperacionVORowImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoVOImpl;
import org.bcie.fenix.common.model.vo.LineaCreditoVORowImpl;

/**
 * [KB:14513]
 * Permite la carga de líneas de crédito de un contrato.
 * Se encarga de configurar las demas VO que se requieran para su visualización en la vista de admin líneas de crédito.
 * ATENCION: Una vez ejecutada (método execute es invocado), no podrá volverse a ejecutar y una nueva instancia será requerida.
 */
public class CargarLineasByContratoBO extends BaseBO {

    private static ADFLogger logger = ADFLogger.createADFLogger(CargarLineasByContratoBO.class);

    /** Identificador la operacion actual.*/
    private Long idOperacion;
    
    /** Identificador del contrato de donde se consultarán las líneas de crédito.*/
    private Long idContrato;
    
    /** Cache para los nombres de fondos de las lineas.*/
    private Map<String, String> nombreFondos;

    public CargarLineasByContratoBO(FenixAMImpl fenixAM, Long idOperacion, Long idContrato) {
        super(fenixAM);

        Objects.requireNonNull(idOperacion, "idOperacion es requerido.");
        Objects.requireNonNull(idContrato, "idContrato es requerido.");

        this.idOperacion = idOperacion;
        this.idContrato = idContrato;
        this.nombreFondos = new HashMap<>();
    }

    @Override
    public FenixAMImpl getAM() {
        return (FenixAMImpl) super.getAM();
    }

    /**
     * Ejecuta esta operación de negocio.
     * Carga la info de las líneas de crédito a objetos de memoria y configura LOVs para la vista admin líneas de crédito.
     */
    @Override
    public void execute() {

        //si el recurso ya fue liberado, entonces no se puede continuar y se debe crear otra instancia de esta clase
        if (this.getAM() == null) {
            logger.warning("Instancia ya liberada, no se continuará.");
            throw new IllegalStateException("El AM de esta instancia ya fue liberado, por lo tanto no puede volver a usarse. Debe crear una nueva instancia de esta clase.");
        }

        logger.info("Iniciando carga de datos de líneas de crédito a memoria.");

        try {
            this.cargarLineasCreditoEnMemoria();
            this.cargarLineasCreditoAsociadasEnMemoria();
        } finally {
            //Se asegura de liberar recursos por si de casualidad el cliente de esta clase no lo hace.
            try {
                this.close();
            } catch (Exception ex) {
                logger.severe(ex);
            }
        }

        logger.info("Finaliza carga de datos de líneas de crédito a memoria.");
    }

    /**
     * Carga datos de las lineas de credito desde base de datos a memoria (DatosLineaCreditoVO) para que sean
     * usadas por la vista en admin lineas de credito.
     */
    private void cargarLineasCreditoEnMemoria() {
        FenixAMImpl fenixAM = this.getAM();

        //limpiamos objeto en memoria que usa la vista admin lineas credito para el resumen de las LC
        DatosLineaCreditoVOImpl datosLineaVO = fenixAM.getDatosLineaCreditoVO();
        datosLineaVO.executeQuery();

        //Obtenemos la línea de crédito desde base de datos
        LineaCreditoVOImpl lineasCreditoVO = fenixAM.getLineaCreditoVO();
        ViewCriteria byActivasVC = lineasCreditoVO.getViewCriteriaManager().getViewCriteria("LineaCreditoVOActivas");
        if (byActivasVC == null) {
            logger.warning("No se encuentra ViewCriteria: LineaCreditoVOActivas. No se puede continuar.");
            return;
        }

        try {
            lineasCreditoVO.setcontratoId(this.idContrato);
            lineasCreditoVO.applyViewCriteria(byActivasVC);
            lineasCreditoVO.executeQuery();

            if (lineasCreditoVO.getEstimatedRowCount() > 0) {
                RowSetIterator rsi = lineasCreditoVO.createRowSetIterator(null);

                try {
                    while (rsi.hasNext()) {
                        LineaCreditoVORowImpl lcDbRow = (LineaCreditoVORowImpl) rsi.next();
                        String nombreFondo = findNombreFondo(lcDbRow.getFondo());
                        datosLineaVO.insertCopyOf(lcDbRow, nombreFondo);
                    }
                } finally {
                    rsi.closeRowSetIterator();
                }
            } else {
                logger.warning("No se encontraron Líneas de Crédito para el contrato ID: {0}", this.idContrato);
            }
        } finally {
            lineasCreditoVO.getViewCriteriaManager().removeApplyViewCriteriaName("LineaCreditoVOActivas");
        }
    }
    
    /**
     * Carga datos de las lineas de credito asociadas desde base de datos a memoria (DatosLineaCreditoVO) para que sean
     * usadas por la vista en admin lineas de credito.
     */
    private void cargarLineasCreditoAsociadasEnMemoria() {
        FenixAMImpl fenixAM = this.getAM();

        //No limpiamos el objeto en memoria porque podriammos perder las lineas credito de la operacion
        DatosLineaCreditoVOImpl datosLineaVO = fenixAM.getDatosLineaCreditoVO();

        //Obtenemos la línea de crédito desde base de datos
        LineaCreditoAsociadaOperacionVOImpl lineasCreditoAsociadasVO = fenixAM.getLineaCreditoAsociadaOperacionVO();
        lineasCreditoAsociadasVO.setpIdOperacion(this.idOperacion);
        lineasCreditoAsociadasVO.executeQuery();

        if (lineasCreditoAsociadasVO.getEstimatedRowCount() > 0) {
            RowSetIterator rsi = lineasCreditoAsociadasVO.createRowSetIterator(null);

            try {
                while (rsi.hasNext()) {
                    LineaCreditoAsociadaOperacionVORowImpl lcDbRow =
                        (LineaCreditoAsociadaOperacionVORowImpl) rsi.next();
                    String nombreFondo = findNombreFondo(lcDbRow.getFondo());
                    datosLineaVO.insertCopyAsociadaOf(lcDbRow, this.idOperacion, nombreFondo);
                }
            } finally {
                rsi.closeRowSetIterator();
            }
        } else {
            logger.info("No se encontraron Líneas de Crédito asociadas para la operacion: {0}", this.idOperacion);
        }
    }
    
    /**
     * Retorna el nombre del fondo cuyo ID es el pasado por param.
     * @param idFondo Identifiador del fondo a buscar. Primero busca en cache, si no se encuentra e hace consulta y se
     * almacena en cache.
     * @return Nombre del fondo o null si no se encuentra o el id pasado es nulo.
     */
    private String findNombreFondo(final String idFondo) {
        logger.info("Buscando Fondo con ID: {0}", idFondo);
        //Si no se pasa el ID no buscar
        if (idFondo == null) {
            return null;
        }

        FenixAMImpl fenixAM = this.getAM();

        if (!nombreFondos.containsKey(idFondo)) {
            Row[] filteredArray = fenixAM.getCatFondoContableVO().findByKey(new Key(new Object[] { idFondo }), 1);
            if (filteredArray != null && filteredArray.length > 0) {
                logger.info("Fondo encontrado!");
                Row rowFound = filteredArray[0];
                nombreFondos.put(idFondo, (String) rowFound.getAttribute("CodeDesc"));
            } else {
                logger.info("Fondo no encontrado.");
            }
        } else {
            logger.info("Retornando nombreFondo desde cache.");
        }

        return nombreFondos.get(idFondo);
    }
}
