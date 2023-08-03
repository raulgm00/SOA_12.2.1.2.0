package pa05cancelaroperaciongenerichumantask.beans;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.AttributeBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class CancelarOperacionActionBean extends FenixActionBean {
    
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(CancelarOperacionActionBean.class);
    public static final String BUNDLE ="pa05cancelaroperaciongenerichumantask.PA05CancelarOperacionGenericHumanTaskBundle";
    
    private RichPopup popupCancelarOperacion;
    private RichPopup popupRechazarSolicitud;
    
    /**
     * Define clave de Bundle para indicar error en la validacion al finalizar tarea
     */
     public static final String MSG_ERROR_OBSERVACIONES ="MSG_ERROR_OBSERVACIONES";
    
    public CancelarOperacionActionBean() {
        super();
    }


    public void setPopupCancelarOperacion(RichPopup popupCancelarOperacion) {
        this.popupCancelarOperacion = popupCancelarOperacion;
    }

    public RichPopup getPopupCancelarOperacion() {
        return popupCancelarOperacion;
    }

    public void setPopupRechazarSolicitud(RichPopup popupRechazarSolicitud) {
        this.popupRechazarSolicitud = popupRechazarSolicitud;
    }

    public RichPopup getPopupRechazarSolicitud() {
        return popupRechazarSolicitud;
    }
    
    public String invocarCancelarOperacion(){
        LOGGER.warning("invocarCancelarOperacion");
        CancelarOperacionBean cancelarOperacionBean = (CancelarOperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.cancelarOperacionBean}");
        
        AttributeBinding attributeObservacion;
        attributeObservacion = ADFUtils.findControlBinding("ObservacionCancela");
        
        List<String> budleKeyErroList = new ArrayList<String>();
        
        if(null == attributeObservacion.getInputValue() || "".equals(attributeObservacion.getInputValue().toString().trim())){
            budleKeyErroList.add(MSG_ERROR_OBSERVACIONES);
        }
        
        if(budleKeyErroList.size()>0)
        {
          for(String bundleKey : budleKeyErroList)
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
        }else{
            cancelarOperacionPopup();            
        }
        
        return null;
    }
    
    public String invocarRechazarSOlicitud(){
        LOGGER.warning("invocarRechazarSOlicitud");
        CancelarOperacionBean cancelarOperacionBean = (CancelarOperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.cancelarOperacionBean}");
        
        AttributeBinding attributeObservacion;
        attributeObservacion = ADFUtils.findControlBinding("ObservacionCancela");
        
        List<String> budleKeyErroList = new ArrayList<String>();
        
        if(null == attributeObservacion.getInputValue() || "".equals(attributeObservacion.getInputValue().toString().trim())){
            budleKeyErroList.add(MSG_ERROR_OBSERVACIONES);
        }
        
        if(budleKeyErroList.size()>0)
        {
          for(String bundleKey : budleKeyErroList)
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
        }else{
            rechazarSolicitudPopup();            
        }
        
        return null;
    }
    
    public void cancelarOperacionPopup(){
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCancelarOperacion.show(hints);
    }
    
    public String ocultarCancelarOperacionPopup(){
        popupCancelarOperacion.hide();
        return null;
    }
    
    public void rechazarSolicitudPopup(){
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupRechazarSolicitud.show(hints);
    }
    
    public String ocultarRechazarSolicitudPopup(){
        popupRechazarSolicitud.hide();
        return null;
    }

    public String confirmarCancelarOperacion() {
        ocultarCancelarOperacionPopup();
        BindingContainer bindings = getBindings();
        try {
        OperationBinding operationBinding = bindings.getOperationBinding("guardarObservacionesCancelarOperacion");
        operationBinding.execute();
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en confirmarCancelarOperacion " + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Error al guardar las observaciones. Por favor intente más tarde.");
            return null;
        }
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public String confirmarRechazarSolicitud(){
        ocultarRechazarSolicitudPopup();
        BindingContainer bindings = getBindings();
        try {
        OperationBinding operationBinding = bindings.getOperationBinding("guardarObservacionesCancelarOperacion");
        operationBinding.execute();
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en confirmarRechazarSolicitud " + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Error al guardar las observaciones. Por favor intente más tarde.");
            return null;
        }
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
}
