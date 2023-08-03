package org.bcie.fenix.view.encabezadogestionar;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import pa12prepagoght.bean.PrepagoActionBean;

public class EncabezadoGestionarActionBean {
    private RichOutputText initForm;
    
    public static ADFLogger logger = null;
    private String obtenerUrl;
    
    /**
     * Define el valor para opcion "Si" en el componente  selectOneRadio "ExisteDifCobertura"
     */
    public static final Integer VALUE_SI = 1;
    /**
     * Define el valor para opcion "No" en el componente  selectOneRadio "ExisteDifCobertura"
     */
    public static final Integer VALUE_NO = 0;

    public void setObtenerUrl(String obtenerUrl) {
        this.obtenerUrl = obtenerUrl;
    }

    public void llenarListaMoneda() {
        logger.warning("Inside obtenerMoneda.");

        EncabezadoGestionarBean encabezadoGestionarBean =
            (EncabezadoGestionarBean) JSFUtils.resolveExpression("#{pageFlowScope.encabezadoGestionarBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long pIdPrepago = null;

        try {
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}")) {
                pIdPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
                logger.warning("Valor de pIdPrepago: " + pIdPrepago);
            }

            operationBinding = bindings.getOperationBinding("setpIdPrepago");
            operationBinding.getParamsMap().put("value", pIdPrepago);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
        } catch (Exception e) {
            logger.warning("Error en obtenerMoneda." + e.getClass() + "." + e.getMessage());
        }
    }
    
    public String getObtenerUrl() {
        logger.warning("inside getObtenerUrl.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long pIdPrepago = null;

        try {
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}")) {
                pIdPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
                logger.warning("Valor de pIdPrepago: " + pIdPrepago);
            }

            operationBinding = bindings.getOperationBinding("consultarInformeByIdPrepago");
            operationBinding.getParamsMap().put("idPrepago", pIdPrepago);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                obtenerUrl = (String)operationBinding.getResult();
            }
        } catch (Exception e) {
            logger.warning("Error en obtenerMoneda." + e.getClass() + "." + e.getMessage());
        }
        
        return obtenerUrl;
    }

    public EncabezadoGestionarActionBean() {
        super();
        if (logger == null){
            logger = ADFLogger.createADFLogger(PrepagoActionBean.class);
        }
    }
    
    public void setInitForm(RichOutputText initForm) {
        this.initForm = initForm;
    }

    public RichOutputText getInitForm() {
        logger.warning("invocar Catalogo Moneda.");
        this.llenarListaMoneda();
        return initForm;
    }
    
    public void diferenciaCoberturasValueChangeListener(ValueChangeEvent vce){
        logger.warning("Dentro de diferenciaCoberturasValueChangeListener");
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Integer newValue = null;
        EncabezadoGestionarBean encabezadoGestionarBean = null;
        AttributeBinding montoReal = null;
        AttributeBinding idTcaTipoMonedaReal = null;
        try{
        encabezadoGestionarBean = (EncabezadoGestionarBean)
            JSFUtils.resolveExpression("#{pageFlowScope.encabezadoGestionarBean}");
        }catch(Exception e){
            logger.severe("Ocurrio un error al recuperar el Bean encabezadoGestionarBean",e);
        }
        if(null != vce.getNewValue()){
            newValue = (Integer) vce.getNewValue();
            logger.warning("newValue es : "+newValue);
            if(null != newValue && newValue.equals(VALUE_SI)){
                encabezadoGestionarBean.setEsVisibleCostoReal(Boolean.TRUE);
                logger.log(ADFLogger.WARNING, "Valor setEsVisibleCostoReal :" + encabezadoGestionarBean.getEsVisibleCostoReal());
            }else if(null != newValue && newValue.equals(VALUE_NO)){
                montoReal = ADFUtils.findControlBinding("MontoReal");
                idTcaTipoMonedaReal = ADFUtils.findControlBinding("IdTcaTipoMonedaReal");
                if (null != montoReal) {
                    montoReal.setInputValue(null); 
                }
                
                if(null != idTcaTipoMonedaReal){
                    idTcaTipoMonedaReal.setInputValue(null);
                }
                encabezadoGestionarBean.setEsVisibleCostoReal(Boolean.FALSE);
                logger.log(ADFLogger.WARNING, "Valor setEsVisibleCostoReal :" + encabezadoGestionarBean.getEsVisibleCostoReal());
            }
        }else{
            logger.severe("getNewValue() en ValueChangeListener es nulo");
        }
        
        logger.warning("Termina diferenciaCoberturasValueChangeListener");
    }
    
}
