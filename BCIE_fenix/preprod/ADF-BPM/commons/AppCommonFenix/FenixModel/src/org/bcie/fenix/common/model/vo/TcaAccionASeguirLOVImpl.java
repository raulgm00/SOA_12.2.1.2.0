package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.TcaAccionASeguirLOV;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Nov 24 14:13:47 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TcaAccionASeguirLOVImpl extends ViewObjectImpl implements TcaAccionASeguirLOV {
    
    /**
     * Log de la aplicacion
     */
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(TcaAccionASeguirLOVImpl.class);
    
    /**
     * Define nombre de view criteria para buscar por Id de Nivel Aprobacion y Id Tipo Solicitud
     */
    public static final String TCA_ACCION_A_SEGUIR_NIVEL_TIPO_SOL_VC = "TcaAccionASeguirNivelYTipoSolVC";
    
    /**
     * Define nombre de view criteria para buscar por Id de Nivel Aprobacion y Id Tipo Solicitud para Presidencia
     */
    public static final String TCA_ACCION_A_SEGUIR_NIVEL_TIPO_SOL_PRESIDENCIA_VC = "TcaAccionASeguirNivelYTipoSolPresidenciaVC";
    
    /**
     * Define nombre de view criteria para buscar por Id de Nivel Aprobacion, Tipo Solicitud y Tipo decision
     */
    public static final String TCA_ACCION_A_SEGUIR_NIVEL_TIPOSOL_TIPODEC_VC = "TcaAccionASeguirNivelTipoSolTipoDecVC";
    
    /**
     * Define nombre de view criteria para buscar por Id
     */
    public static final String TCA_ACCION_A_SEGUIR_POR_ID_VC = "TcaAccionASeguirPorIdVC";
    
    /**
     * This is the default constructor (do not remove).
     */
    public TcaAccionASeguirLOVImpl() {
    }


    /**
     * Returns the variable value for idTipoSolicitudAprobacion.
     * @return variable value for idTipoSolicitudAprobacion
     */
    public Integer getidTipoSolicitudAprobacion() {
        return (Integer) ensureVariableManager().getVariableValue("idTipoSolicitudAprobacion");
    }

    /**
     * Sets <code>value</code> for variable idTipoSolicitudAprobacion.
     * @param value value to bind as idTipoSolicitudAprobacion
     */
    public void setidTipoSolicitudAprobacion(Integer value) {
        ensureVariableManager().setVariableValue("idTipoSolicitudAprobacion", value);
    }

    /**
     * Returns the variable value for pIdNivelAprob.
     * @return variable value for pIdNivelAprob
     */
    public Number getpIdNivelAprob() {
        return (Number) ensureVariableManager().getVariableValue("pIdNivelAprob");
    }

    /**
     * Sets <code>value</code> for variable pIdNivelAprob.
     * @param value value to bind as pIdNivelAprob
     */
    public void setpIdNivelAprob(Number value) {
        ensureVariableManager().setVariableValue("pIdNivelAprob", value);
    }
    
    /**
     * Realiza una busqueda de Acciones a Seguir por Id de Nivel Aprobacion y Id Tipo Solicitud Aprobacion
     * @param idNivelAprobacion contiene id de nivel aprobacion
     * @param idTipoSolicitudAprob contiene id tipo solicitud aprobacion
     */
    public void buscarPorNivelYTipoSol(Number idNivelAprobacion,
                                       Integer idTipoSolicitudAprob){
        
        if(idNivelAprobacion == null ||
           idTipoSolicitudAprob == null){
            return;
        }
        
        //Asigna valores a Variables Bind
        setpIdNivelAprob(idNivelAprobacion);
        setidTipoSolicitudAprobacion(idTipoSolicitudAprob);
        
        try{
            ViewCriteria vc = getViewCriteria(TCA_ACCION_A_SEGUIR_NIVEL_TIPO_SOL_VC);
            applyViewCriteria(vc);
            executeQuery();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.severe("Error al ejecutar View Criteria", e);
        }
    }
    
    /**
     * Realiza una busqueda de Acciones a Seguir por Id de Nivel Aprobacion y Id Tipo Solicitud Aprobacion
     * para Presidencia
     * @param idNivelAprobacion contiene id de nivel aprobacion
     * @param idTipoSolicitudAprob contiene id tipo solicitud aprobacion
     */
    public void buscarPorNivelYTipoSolPresidente(Number idNivelAprobacion,
                                                 Integer idTipoSolicitudAprob){
        
        if(idNivelAprobacion == null ||
           idTipoSolicitudAprob == null){
            return;
        }
        
        //Asigna valores a Variables Bind
        setpIdNivelAprob(idNivelAprobacion);
        setidTipoSolicitudAprobacion(idTipoSolicitudAprob);
        setpEsPositiva(1);
        
        try{
            ViewCriteria vc = getViewCriteria(TCA_ACCION_A_SEGUIR_NIVEL_TIPO_SOL_PRESIDENCIA_VC);
            applyViewCriteria(vc);
            executeQuery();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.severe("Error al ejecutar View Criteria", e);
        }
    }
    
    /**
     * Realiza una busqueda de Acciones a Seguir por Id de Nivel Aprobacion, Id Tipo Solicitud Aprobacion
     * y por Id de tipo decision (1 = Positiva, 2 = Negativa)
     * @param idNivelAprobacion contiene id de nivel aprobacion
     * @param idTipoSolicitudAprob contiene id tipo solicitud aprobacion
     * @param tipoDesicion define id del tipo de decision
     */
    public void buscarPorNivelTipoSolTipoDec(Number idNivelAprobacion,
                                             Integer idTipoSolicitudAprob,
                                             Integer tipoDecision){
        
        //Asigna valores a Variables Bind
        setpIdNivelAprob(idNivelAprobacion);
        setidTipoSolicitudAprobacion(idTipoSolicitudAprob);
        setpEsDecisionPositiva(tipoDecision);
        
        try{
            ViewCriteria vc = getViewCriteria(TCA_ACCION_A_SEGUIR_NIVEL_TIPOSOL_TIPODEC_VC);
            applyViewCriteria(vc);
            executeQuery();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.severe("Error al ejecutar View Criteria", e);
        }
    }
    
    /**
     * Realiza la busqueda con view criteria por medio del id
     * @param id contiene id de llave primaria
     */
    public void buscarPorId(Number id){
        
        LOGGER.entering(TcaAccionASeguirLOVImpl.class.getName(), 
                        "buscarPorId",
                        id);
        
        if(id == null){
            return;
        }
        
        //Asigna valor a bind variables
        LOGGER.fine("Asigna Id: " + id);
        setpId(id);
        
        try{
            ViewCriteria vc = getViewCriteria(TCA_ACCION_A_SEGUIR_POR_ID_VC);
            LOGGER.fine("Aplica view criteria: " + TCA_ACCION_A_SEGUIR_POR_ID_VC);
            applyViewCriteria(vc);
            
            LOGGER.fine("Ejecuta Query");
            executeQuery();
            
            setCurrentRow(first());
        }catch(Exception e){
            LOGGER.severe("Error al ejecutar View Criteria", e);
        }
        
        LOGGER.exiting(TcaAccionASeguirLOVImpl.class.getName(),
                       "buscarPorId");
    }


    /**
     * Returns the variable value for pEsDecisionPositiva.
     * @return variable value for pEsDecisionPositiva
     */
    public Integer getpEsDecisionPositiva() {
        return (Integer) ensureVariableManager().getVariableValue("pEsDecisionPositiva");
    }

    /**
     * Sets <code>value</code> for variable pEsDecisionPositiva.
     * @param value value to bind as pEsDecisionPositiva
     */
    public void setpEsDecisionPositiva(Integer value) {
        ensureVariableManager().setVariableValue("pEsDecisionPositiva", value);
    }

    /**
     * Returns the variable value for pId.
     * @return variable value for pId
     */
    public Number getpId() {
        return (Number) ensureVariableManager().getVariableValue("pId");
    }

    /**
     * Sets <code>value</code> for variable pId.
     * @param value value to bind as pId
     */
    public void setpId(Number value) {
        ensureVariableManager().setVariableValue("pId", value);
    }

    /**
     * Returns the variable value for pEsPositiva.
     * @return variable value for pEsPositiva
     */
    public Integer getpEsPositiva() {
        return (Integer) ensureVariableManager().getVariableValue("pEsPositiva");
    }

    /**
     * Sets <code>value</code> for variable pEsPositiva.
     * @param value value to bind as pEsPositiva
     */
    public void setpEsPositiva(Integer value) {
        ensureVariableManager().setVariableValue("pEsPositiva", value);
    }
}

