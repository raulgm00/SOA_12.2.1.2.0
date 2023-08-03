package org.bcie.fenix.view.comentarios;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.modelo.Justificacion;

public class JustificacionBean implements Serializable {
    @SuppressWarnings("compatibility:6717091977299406865")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger logger = ADFLogger.createADFLogger(JustificacionBean.class);
    private static final String CLASS_NAME = ComentariosBean.class.getName();
    private Justificacion justificacion;
    private Boolean esEstadoCompletado;
    private Boolean editable;
    private Boolean actualizar;
    private Boolean blockGuardar;
    public JustificacionBean() {
        super();
        if(JSFUtils.resolveExpression("#{pageFlowScope.pJustificacion}")!=null){
            this.justificacion = (Justificacion)JSFUtils.resolveExpression("#{pageFlowScope.pJustificacion}");
        }else{
            this.justificacion =  new Justificacion();
        }
        this.editable=Boolean.TRUE;
        this.actualizar=Boolean.FALSE;
        this.blockGuardar=Boolean.FALSE;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pEditable}")!=null){
            this.editable = (Boolean)JSFUtils.resolveExpression("#{pageFlowScope.pEditable}");
        }
    }

    @SuppressWarnings("unchecked")
    public void precargaJustificacionOperacionTipo(){
        final String METHOD_NAME = "precargaJustificacionOperacionTipo";
        try {    
            
            logger.entering(CLASS_NAME, METHOD_NAME);
            logger.warning("Ingresanod a la operacion manage bean precargaJustificacionOperacionTipo: ");
            // declarar recursos
            final BindingContainer binding = ADFUtils.getBindingContainer();
            final OperationBinding operationBinding = binding.getOperationBinding("obtenerJustificacionPorId");
            final String idOperacion =  JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
            final Integer tipo = (Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pTipo}").toString()));

            logger.warning("Parametros : "+operationBinding+" - "+idOperacion+" - "+tipo);
            logger.warning("idOperacion enviado: " + idOperacion);
            
            if(operationBinding != null) {
                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                operationBinding.getParamsMap().put("tipo", tipo);
                operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("OperationBinding devuelve errores metodo setIdOperacion");
                }else{
                    logger.warning("***Carga IdOperacion Listo, redireccionando... ");
                }
            } else {
                logger.severe("Operacion no encontrada.");
            }  
            if(JSFUtils.resolveExpression("#{bindings.Comentario.inputValue}")!=null){
                this.actualizar=Boolean.TRUE;
                this.justificacion.setTexto(JSFUtils.resolveExpression("#{bindings.Comentario.inputValue}").toString());
            }
            
            this.evaluarParametroPStateBpm();
        } catch (Exception e){
            e.printStackTrace();
            logger.log(ADFLogger.ERROR, METHOD_NAME, e.getMessage());
        }
        
        logger.exiting(CLASS_NAME, METHOD_NAME);
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
    public void setJustificacion(Justificacion justificacion) {
        this.justificacion = justificacion;
    }

    public Justificacion getJustificacion() {
        return justificacion;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }

    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
    }

    public Boolean getActualizar() {
        return actualizar;
    }

    public void setBlockGuardar(Boolean blockGuardar) {
        this.blockGuardar = blockGuardar;
    }

    public Boolean getBlockGuardar() {
        return blockGuardar;
    }
}
