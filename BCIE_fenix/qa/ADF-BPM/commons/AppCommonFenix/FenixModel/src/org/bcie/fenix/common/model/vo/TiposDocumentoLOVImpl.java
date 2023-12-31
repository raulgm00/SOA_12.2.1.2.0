package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.TiposDocumentoLOV;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Oct 27 17:35:40 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TiposDocumentoLOVImpl extends ViewObjectImpl implements TiposDocumentoLOV {
    /**
     * This is the default co
     * nstructor (do not remove).
     */
    
    private static ADFLogger logger = null;
    
    public TiposDocumentoLOVImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    /**
     * Returns the bind variable value for idTareaBpm.
     * @return bind variable value for idTareaBpm
     */
    public Integer getidTareaBpm() {
        return (Integer) getNamedWhereClauseParam("idTareaBpm");
    }

    /**
     * Sets <code>value</code> for bind variable idTareaBpm.
     * @param value value to bind as idTareaBpm
     */
    public void setidTareaBpm(Integer value) {
        logger.log(ADFLogger.WARNING, "Inside setidTareaBpm with parameter idTareaBpm : ."+value);
        setNamedWhereClauseParam("idTareaBpm", value);
    }

    /**
     * Returns the variable value for varIdTipo.
     * @return variable value for varIdTipo
     */
    public Integer getvarIdTipo() {
        return (Integer) ensureVariableManager().getVariableValue("varIdTipo");
    }

    /**
     * Sets <code>value</code> for variable varIdTipo.
     * @param value value to bind as varIdTipo
     */
    public void setvarIdTipo(Integer value) {
        ensureVariableManager().setVariableValue("varIdTipo", value);
    }
    
    public String getTipoDocumentoById(Integer idTipo){
        logger.log(ADFLogger.WARNING, "Inside getTipoDocumentoById.");
        logger.log(ADFLogger.WARNING, "With parameter  idTipo :"+idTipo);
        String tipoDocumento = null;
        Integer idTareaBpm = getidTareaBpm();

        // Como el query puede estar filtrado por el Id de tarea Bpm, lo hacemos null para traer todos los tipos
        setidTareaBpm(null);
        
        // Ejecutamos VC
        ViewCriteria criteriaById = this.getViewCriteriaManager().getViewCriteria("TiposDocumentoLOVCriteriaById");
        criteriaById.ensureVariableManager().setVariableValue("varIdTipo", idTipo);
        
        this.applyViewCriteria(criteriaById);
        this.executeQuery();
        
        if(this.getEstimatedRowCount() > 0)
            tipoDocumento = (String) this.getRowAtRangeIndex(0).getAttribute("Descripcion");
        
        //This takes care of removing the applied VC.
        this.getViewCriteriaManager().removeApplyViewCriteriaName("TiposDocumentoLOVCriteriaById");
        
        // Reasignamos el Id de tarea Bpm
        setidTareaBpm(idTareaBpm);
        
        // No basta con quitar el VC aplicado, hay que re-ejecutar el query o se queda filtrado el combo
        this.executeQuery();
        
        logger.log(ADFLogger.WARNING, "return value tipoDocumento :"+tipoDocumento);
        return tipoDocumento;
    }
}

