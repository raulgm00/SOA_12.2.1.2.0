package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ConsultarContratosDesembolsoQueryVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 18 13:48:07 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConsultarContratosDesembolsoQueryVOImpl extends ViewObjectImpl implements ConsultarContratosDesembolsoQueryVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public ConsultarContratosDesembolsoQueryVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public Boolean validarExisteContratoPorEstado(Long idContratoDesembolso, Integer idEstado){
        logger.warning("Inicia metodo validarExisteContratoPorEstado.");
        Boolean existeContrato = Boolean.FALSE;
        ViewCriteria vc = null;
        
        if(null == idContratoDesembolso || null == idEstado){
            logger.warning("Parametros requeridos son NULL. IdContrato: " + idContratoDesembolso + 
                           ", IdEstado: " + idEstado);
            return null;
        }
        
        try{
            logger.warning("Ejecutando criteria de busqueda de Contrato por IdContrato: " + idContratoDesembolso + 
                           " y IdEstado: " + idEstado);
            vc = this.getViewCriteriaManager().getViewCriteria("ConsultarContratoPorIdEstadoVC");
            vc.ensureVariableManager().setVariableValue("pIdContrato", idContratoDesembolso);
            vc.ensureVariableManager().setVariableValue("pIdEstado", idEstado);
            this.applyViewCriteria(vc);
            this.executeQuery();
        }catch(Exception e){
            logger.warning("ERROR al ejecutar el crietria ConsultarContratoPorIdEstadoVC.", e);
            //Eliminamos el ViewCriteria
            this.getViewCriteriaManager().removeApplyViewCriteriaName("ConsultarContratoPorIdEstadoVC");
            return null;
        }
        
        logger.warning("Registros encontrados para el contrato: " + getEstimatedRowCount());
        if(getEstimatedRowCount() > 0){
            existeContrato = Boolean.TRUE;
        }
        
        //Eliminamos el ViewCriteria
        this.getViewCriteriaManager().removeApplyViewCriteriaName("ConsultarContratoPorIdEstadoVC");
        logger.warning("Termina metodo validarExisteContratoPorEstado.");
        return existeContrato;
    }

    /**
     * Returns the variable value for pIdEstado.
     * @return variable value for pIdEstado
     */
    public Integer getpIdEstado() {
        return (Integer) ensureVariableManager().getVariableValue("pIdEstado");
    }

    /**
     * Sets <code>value</code> for variable pIdEstado.
     * @param value value to bind as pIdEstado
     */
    public void setpIdEstado(Integer value) {
        ensureVariableManager().setVariableValue("pIdEstado", value);
    }

    /**
     * Returns the variable value for pIdContrato.
     * @return variable value for pIdContrato
     */
    public Long getpIdContrato() {
        return (Long) ensureVariableManager().getVariableValue("pIdContrato");
    }

    /**
     * Sets <code>value</code> for variable pIdContrato.
     * @param value value to bind as pIdContrato
     */
    public void setpIdContrato(Long value) {
        ensureVariableManager().setVariableValue("pIdContrato", value);
    }
}

