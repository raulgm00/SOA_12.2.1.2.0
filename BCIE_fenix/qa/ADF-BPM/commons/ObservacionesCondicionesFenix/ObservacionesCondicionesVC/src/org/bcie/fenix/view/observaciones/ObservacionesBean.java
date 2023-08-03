package org.bcie.fenix.view.observaciones;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.utils.JSFUtils;

public class ObservacionesBean implements Serializable {
    @SuppressWarnings("compatibility:8540097809300129445")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;
    
    // Variables usadas para persistir datos en fragmento
    private String observacionCumplimiento;
    // Variable para validar el estado de la tarea
    private Boolean esEstadoCompletado;

    public ObservacionesBean() {
        
        if (logger == null) {
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
    
    public void setObservacionCumplimiento(String observacionCumplimiento) {
        this.observacionCumplimiento = observacionCumplimiento;
    }

    public String getObservacionCumplimiento() {
        return observacionCumplimiento;
    }

    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
