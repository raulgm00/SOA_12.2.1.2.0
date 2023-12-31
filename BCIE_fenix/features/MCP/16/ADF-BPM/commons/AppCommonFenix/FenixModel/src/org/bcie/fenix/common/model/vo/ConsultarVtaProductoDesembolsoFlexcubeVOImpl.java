package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ConsultarVtaProductoDesembolsoFlexcubeVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jan 24 20:59:21 CST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConsultarVtaProductoDesembolsoFlexcubeVOImpl extends ViewObjectImpl implements ConsultarVtaProductoDesembolsoFlexcubeVO {
    
    private static ADFLogger logger = null;   
    
    public ConsultarVtaProductoDesembolsoFlexcubeVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        } 
    }
    
    
    public String recuperarDescripcionById(String id){
        logger.warning("*Inf, Inicia metodo recuperarDescripcionById en vtaProductoDesemFlexcube");    
            String Descripcion = null;
            Row filaRecuperada = null;        
            ViewCriteria criteria = null;
                            
            logger.warning("*Inf, id: "+id);
            
            if(id == null){
                logger.warning("***Error, parametros requeridos son resueltos a null");               
                return null;
            }
            executeQuery();
        
            try{                      
                criteria = getViewCriteriaManager().getViewCriteria("filtrarByIdVc");
                criteria.ensureVariableManager().setVariableValue("pId", id);
                applyViewCriteria(criteria);                                            
                executeQuery();
            
                if(getEstimatedRowCount() > 0){                                   
                    filaRecuperada = first();
                    logger.warning("Id Descripcion ->"+filaRecuperada.getAttribute("Descripcion"));                
                    Descripcion = (String)filaRecuperada.getAttribute("Descripcion");
                }else{
                     logger.warning("El row recuperado es Null no hay coincidencias en la busqueda"); 
                     getViewCriteriaManager().removeApplyViewCriteriaName("filtrarByIdVc");          
                     executeQuery();
                    }
                
            }catch(Exception e){
                logger.log(ADFLogger.WARNING, "*** Error ->" + e.getClass() + ":" + e.getMessage());     
            }finally{
               getViewCriteriaManager().removeApplyViewCriteriaName("filtrarByIdVc");          
                
            }
        
        
        logger.warning("*Inf, termina metodo recuperarDescripcionById en vtaProductoDesemFlexcube");    
        return Descripcion;
        }


    /**
     * Returns the variable value for pDescripcion.
     * @return variable value for pDescripcion
     */
    public String getpId() {
        return (String) ensureVariableManager().getVariableValue("pId");
    }

    /**
     * Sets <code>value</code> for variable pDescripcion.
     * @param value value to bind as pDescripcion
     */
    public void setpId(String value) {
        ensureVariableManager().setVariableValue("pId", value);
    }
}

