package org.bcie.fenix.view.operacionesrelacionadas;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.utils.JSFUtils;

public class OperacionesRelacionadasBean implements Serializable {
    @SuppressWarnings("compatibility:5163052222891370726")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;
    // Variable para validar el estado de la tarea
    private Boolean esEstadoCompletado;

    public OperacionesRelacionadasBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        } 
    }
    
    private Boolean banderaConsulta;
    
    
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

    public Boolean getBanderaConsulta() {
        return banderaConsulta;
    }

    public void setBanderaConsulta(Boolean banderaConsulta) {
        this.banderaConsulta = banderaConsulta;
    }

    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
