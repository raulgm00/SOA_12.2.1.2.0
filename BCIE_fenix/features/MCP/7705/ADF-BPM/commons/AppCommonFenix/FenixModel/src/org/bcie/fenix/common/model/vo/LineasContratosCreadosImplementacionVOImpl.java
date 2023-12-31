package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.vo.common.LineasContratosCreadosImplementacionVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jun 12 13:48:59 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class LineasContratosCreadosImplementacionVOImpl extends ViewObjectImpl implements LineasContratosCreadosImplementacionVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public LineasContratosCreadosImplementacionVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    /**
     * Returns the bind variable value for pIdEstadoContrato.
     * @return bind variable value for pIdEstadoContrato
     */
    public Integer getpIdEstadoContrato() {
        return (Integer) getNamedWhereClauseParam("pIdEstadoContrato");
    }

    /**
     * Sets <code>value</code> for bind variable pIdEstadoContrato.
     * @param value value to bind as pIdEstadoContrato
     */
    public void setpIdEstadoContrato(Integer value) {
        setNamedWhereClauseParam("pIdEstadoContrato", value);
    }

    /**
     * Returns the bind variable value for pIdOperacion.
     * @return bind variable value for pIdOperacion
     */
    public Long getpIdOperacion() {
        return (Long) getNamedWhereClauseParam("pIdOperacion");
    }

    /**
     * Sets <code>value</code> for bind variable pIdOperacion.
     * @param value value to bind as pIdOperacion
     */
    public void setpIdOperacion(Long value) {
        setNamedWhereClauseParam("pIdOperacion", value);
    }

    public Integer cadaLineaCuentaContratoCreadoPorImpl(Long idOperacion) {
        logger.log(ADFLogger.WARNING, "entrando en cadaLineaCuentaContratoCreadoPorImpl.");
        Integer lineas = Integer.valueOf(0);
        setpIdOperacion(idOperacion);
        setpIdEstadoContrato(FenixModelConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO_POR_IMPLEMENTACION);

        executeQuery();
        try{
            if (this.getEstimatedRowCount() > 0) {
                RowSetIterator rowsLineasContratosCreadosImplementacionVo = createRowSetIterator(null);
                rowsLineasContratosCreadosImplementacionVo.reset();
                while (rowsLineasContratosCreadosImplementacionVo.hasNext()) {
                    LineasContratosCreadosImplementacionVORowImpl rowL = (LineasContratosCreadosImplementacionVORowImpl) rowsLineasContratosCreadosImplementacionVo.next();
                    if(rowL.getAttribute("IdLinea") != null){
                        logger.warning("IdLinea: " + rowL.getAttribute("IdLinea") + ", cuenta con un idContratoDesembolso, con estado creado por implementacion" );
                        lineas++;
                    }
                    else{
                        logger.warning("IdLinea es null");
                    }
                }
                rowsLineasContratosCreadosImplementacionVo.closeRowSetIterator();
            }
        }catch(Exception e){
            logger.warning("ERROR al ejecutar el metodo cadaLineaCuentaContratoCreadoPorImpl, se retorna valor 0");
        }
        
        logger.log(ADFLogger.WARNING, "Numero de lineas que cuentan con un contrato creado por implementacion: "
                                      + lineas + ", para la operacion: " + idOperacion);

        return lineas;
    }
}

