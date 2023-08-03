package org.bcie.fenix.view.cuestionarios;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.utils.JSFUtils;
    
public class OperacionPageFlowBean implements Serializable{
    @SuppressWarnings("compatibility:7642804051791329007")
    private static final long serialVersionUID = 1L;
    
    public ADFLogger logger = null;
    
    private Boolean esEstadoCompletado;

    public OperacionPageFlowBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
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


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
