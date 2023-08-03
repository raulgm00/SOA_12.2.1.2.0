package org.bcie.fenix.view.resolucion.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class ResolucionBean implements Serializable{
    
    @SuppressWarnings("compatibility:-7823763413481587893")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    private Boolean esEstadoCompletado;
        
    private String numeroResolucion;
    private Long idOperacion;
    
    public ResolucionBean() {
        super();
        if(null == logger){
                    logger = ADFLogger.createADFLogger(this.getClass());
                }
    }
    
    public void obtenerNumeroResolucion() {
            logger.warning("Entra en obtenerNumeroResolucion.");
            OperationBinding operationBinding = null;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            Long numeroOperacion;
            try{
                if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")){
                    numeroOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
                    setIdOperacion(numeroOperacion);
                }else{
                    logger.warning("El id de la operacion es nulo.");
                }
                
                logger.warning("Valor de la operacion : " + getIdOperacion());
                
                operationBinding = bindings.getOperationBinding("obtenerUltimoNumeroResolucion");
                operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
                operationBinding.execute();
                
                if(null != operationBinding.getResult()){
                    numeroResolucion = (String)operationBinding.getResult();
                    logger.warning("Numero de la resolucion obtenida : " + numeroResolucion);
                }else{
                    logger.warning("No se recupero el numero de la resolucion");
                }
            }catch(Exception e){
                logger.warning("Error en obtenerNumeroResolucion.", e);
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
        
        public void setNumeroResolucion(String numeroResolucion) {
            this.numeroResolucion = numeroResolucion;
        }

        public String getNumeroResolucion() {
            return numeroResolucion;
        }

        public void setIdOperacion(Long idOperacion) {
            this.idOperacion = idOperacion;
        }

        public Long getIdOperacion() {
            return idOperacion;
        }


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
