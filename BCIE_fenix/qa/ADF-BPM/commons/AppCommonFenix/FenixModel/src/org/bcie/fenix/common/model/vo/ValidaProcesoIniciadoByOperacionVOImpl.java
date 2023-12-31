package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ValidaProcesoIniciadoByOperacionVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Nov 13 17:14:03 CST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ValidaProcesoIniciadoByOperacionVOImpl extends ViewObjectImpl implements ValidaProcesoIniciadoByOperacionVO {
    
    private static ADFLogger logger = null;
    
    public ValidaProcesoIniciadoByOperacionVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    
    
    public Boolean existeProcesoIniciadoByOperacion(Long idOperacion ,Integer idProceso){
       logger.warning("Inicia metodo existeProcesoIniciadoByOperacion");
       logger.warning(" operacion: "+idOperacion +" proceso: "+idProceso);
       
        Boolean existeProcesoiniciado = Boolean.FALSE;
       
        this.setpIdOperacion(idOperacion);
        this.setpIdProceso(idProceso);
        this.executeQuery();
        
        if(this.getEstimatedRowCount() > 0){
            existeProcesoiniciado = Boolean.TRUE;
        }
        logger.warning("numProcesos iniciados: "+ getEstimatedRowCount());
         
        logger.warning("termina metodo existeProcesoIniciadoByOperacion");
      return existeProcesoiniciado;
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

    /**
     * Returns the bind variable value for pIdProceso.
     * @return bind variable value for pIdProceso
     */
    public Integer getpIdProceso() {
        return (Integer) getNamedWhereClauseParam("pIdProceso");
    }

    /**
     * Sets <code>value</code> for bind variable pIdProceso.
     * @param value value to bind as pIdProceso
     */
    public void setpIdProceso(Integer value) {
        setNamedWhereClauseParam("pIdProceso", value);
    }
}

