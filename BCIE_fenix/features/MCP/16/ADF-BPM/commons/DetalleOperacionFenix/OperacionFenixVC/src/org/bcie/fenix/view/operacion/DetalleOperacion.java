package org.bcie.fenix.view.operacion;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.utils.JSFUtils;

public class DetalleOperacion  implements Serializable{
    @SuppressWarnings("compatibility:-761354103182497140")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;
    private Boolean esEstadoCompletado;
   

    public DetalleOperacion() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    
    public boolean validateVisibility(Object poValue) {
        if(poValue!=null && poValue!="") {
            return true;
        }   
        
        return false;
    }
    
    
    public void evaluarParametroPStateBpm(){
            logger.warning("Dentro de evaluarParametroPStateBpm");
           String state = null;
            try {
                if (JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}") != null) {
                    state = (String) JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}");
                    logger.warning("state :" + state);
                    setEsEstadoCompletado(state.equals("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
               }else{
                    logger.warning("pState es nulo");
                    setEsEstadoCompletado(Boolean.FALSE);
                }
            } catch (Exception ex) {
                logger.severe("Error al recuperar #{pageFlowScope.pStateBpm} :",ex);
                logger.warning("pState no obtenido");
            }
    
            logger.warning("esEstadoCompletado : " + getEsEstadoCompletado());
            logger.warning("Fuera de evaluarParametroPStateBpm");
        }
        
    
    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }

    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }
    
}
