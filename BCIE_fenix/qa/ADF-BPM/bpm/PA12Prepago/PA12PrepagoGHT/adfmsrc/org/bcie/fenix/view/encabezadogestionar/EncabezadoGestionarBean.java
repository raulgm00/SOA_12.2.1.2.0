package org.bcie.fenix.view.encabezadogestionar;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class EncabezadoGestionarBean implements Serializable{
    @SuppressWarnings("compatibility:2158600249631029533")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    private Boolean esLecturaGestionar;
    private Boolean montoRealMayorPagado = null;
    private Boolean esVisibleCostoReal;
    private Long tareBPM = null;

    public EncabezadoGestionarBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void setEsLecturaGestionar(Boolean esLecturaGestionar) {
        this.esLecturaGestionar = esLecturaGestionar;
    }

    public Boolean getEsLecturaGestionar() {
        return esLecturaGestionar;
    }
    
    public void setMontoRealMayorPagado(Boolean montoRealMayorPagado) {
        this.montoRealMayorPagado = montoRealMayorPagado;
    }

    public Boolean getMontoRealMayorPagado() {
        return montoRealMayorPagado;
    }
    
    public void setEsVisibleCostoReal(Boolean esVisibleCostoReal){
        this.esVisibleCostoReal = esVisibleCostoReal;
    }
    
    public Boolean getEsVisibleCostoReal(){
        return esVisibleCostoReal;
    }
    
    public void inicializarGestionarCobertura() {
        logger.warning("Inside inicializarGestionarCobertura.");
        
        Boolean resultado = Boolean.FALSE;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long pIdPrepago = null;
        
        //Metodo para cargar datos en formulario si existe un registro 'FormularioGestionarCoberturaVO'
        try {
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}")) {
                pIdPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
                logger.warning("Valor de pIdPrepago: " + pIdPrepago);
            }
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}")) {
                tareBPM = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString());
                logger.warning("Valor de pIdTareaBpm: " + tareBPM);
            }
            operationBinding = bindings.getOperationBinding("consultarGestionarCoberturaById");
            operationBinding.getParamsMap().put("idPrepago", pIdPrepago);
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                resultado = (Boolean) operationBinding.getResult();
                setEsLecturaGestionar(resultado);
            }
            logger.warning("esLecturaGestionar: " + resultado);
            if (!getEsLecturaGestionar()) {
                
                crearRowInicialGestionar();
            } else {
                
                compararMonto();
            }
        } catch (Exception e) {
            logger.warning("Error en inicializar Detalle de Penalidad." + e.getClass() + "." + e.getMessage());
        }
    }
    
    public void crearRowInicialGestionar() {
        logger.warning("Inside crearRowInicialGestionar.");
        
        //Metodo para inicializar 'FormularioGestionarCoberturaVO'
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long pIdPrepago = null;
        
        try {
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}")) {
                pIdPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
                logger.warning("Valor de pIdPrepago: " + pIdPrepago);
            }
        
            operationBinding = bindings.getOperationBinding("crearInicializarGestionarCobertura");
            operationBinding.getParamsMap().put("idPrepago", pIdPrepago);
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
        } catch (Exception e) {
            logger.warning("Error en inicializar Detalle de Penalidad." + e.getClass() + "." + e.getMessage());
        }
    }
    
    public void compararMonto() {
        logger.warning("Inside compararMonto.");
        
        Boolean resultado = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        operationBinding = bindings.getOperationBinding("montoRealMayorPagado");
        operationBinding.execute();
            
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            resultado = (Boolean) operationBinding.getResult();
            setMontoRealMayorPagado(resultado);
        }
        logger.warning("montoRealMayorPagado: " + resultado);
    }


    public void setTareBPM(Long tareBPM) {
        this.tareBPM = tareBPM;
    }

    public Long getTareBPM() {
        return tareBPM;
    }

}
