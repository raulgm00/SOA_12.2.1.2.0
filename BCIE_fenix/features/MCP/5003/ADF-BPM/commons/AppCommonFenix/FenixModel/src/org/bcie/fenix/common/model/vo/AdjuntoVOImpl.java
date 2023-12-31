package org.bcie.fenix.common.model.vo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.OutputStream;

import java.sql.Blob;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.server.ViewObjectImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Dec 14 11:45:31 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AdjuntoVOImpl extends ViewObjectImpl {
    private static ADFLogger logger = null;
    /**
     * This is the default constructor (do not remove).
     */
    public AdjuntoVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }


    public BlobDomain getAdjuntoPorIdDocumento(Long idDocumento){
        logger.warning("INSIDE _ getAdjuntoPorIdDocumento: " + idDocumento);

        BlobDomain content = null;
        ViewCriteria criteria = null;
        
        if(null == idDocumento){
            logger.warning("Parametro idDocumento requerido es NULL");

        }

        try{
            criteria = this.getViewCriteriaManager().getViewCriteria("AdjuntoVOCriteria");
            criteria.ensureVariableManager().setVariableValue("pIdDocumento", idDocumento);
            this.applyViewCriteria(criteria);
            this.executeQuery();
            
            setCurrentRow(first());
            Row fila =  null;
            fila = getCurrentRow();
            
            
            if(fila != null){
                
                logger.warning("Si existe documento");
                content = (BlobDomain) fila.getAttribute("Content");
                
            }else{
                logger.log(ADFLogger.WARNING, "No existe documento");
            }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error en getAdjuntoDocumento: " + e.getClass() +
                        ":" + e.getMessage());
        }
        this.getViewCriteriaManager().removeApplyViewCriteriaName("AdjuntoVOCriteria");
        return content;
    }
    /**
     * Returns the variable value for pIdDocumento.
     * @return variable value for pIdDocumento
     */
    public Long getpIdDocumento() {
        return (Long) ensureVariableManager().getVariableValue("pIdDocumento");
    }

    /**
     * Sets <code>value</code> for variable pIdDocumento.
     * @param value value to bind as pIdDocumento
     */
    public void setpIdDocumento(Long value) {
        ensureVariableManager().setVariableValue("pIdDocumento", value);
    }
}

