package org.bcie.fenix.view.detallecliente;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.utils.JSFUtils;

public class DetalleClienteBean {
    
    private static ADFLogger logger = null;
    
    public DetalleClienteBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    private Boolean esEstadoCompletado;


    //metodo para evaluar el parametro de entrada #{pageFlowScope.pStateBpm}
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
