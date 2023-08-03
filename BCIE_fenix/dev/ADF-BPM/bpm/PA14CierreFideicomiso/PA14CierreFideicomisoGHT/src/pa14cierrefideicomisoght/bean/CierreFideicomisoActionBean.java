package pa14cierrefideicomisoght.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import org.bcie.fenix.common.view.beans.FenixActionBean;

public class CierreFideicomisoActionBean extends FenixActionBean{
    
    public static final String BUNDLE ="pa14cierrefideicomisoght.PA14CierreFideicomisoGHTBundle";
                                       
    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;
    private RichPopup popupCancelarTarea;
    public static ADFLogger logger = null;
    private RichOutputText obtenerJustificacion;


    public CierreFideicomisoActionBean() {
        super();
        if (logger == null){
            logger = ADFLogger.createADFLogger(CierreFideicomisoActionBean.class);
        }
    }

    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea) {
        this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupFinalizarTarea() {
        return popupFinalizarTarea;
    }

    public String aceptarFinalizarTarea() {
        logger.log(ADFLogger.WARNING, "Inside aceptarFinalizarTarea." );
        
        cargarDocumentosOnBase();
        
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean resultado = Boolean.FALSE;
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        
            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA14_SOLICITAR_CIERRE_OPERACION: 
            resultado = agregarJustificacion();
            if(!resultado){
                budleKeyErroList.add("MSG_ERROR_FINALIZAR_TAREA");
                returnAction = null;
            }            
        break;
            case FenixConstants.PA14_REVISAR_CUENTAS_CONTABLES:
                resultado = Boolean.TRUE;
            break;
            case FenixConstants.PA14_REVISAR_CUENTAS_BANCARIAS_INVERSIONES:
                resultado = Boolean.TRUE;
            break;
            case FenixConstants.PA14_SOLICITAR_DOCUMENTO_CIERRE:
                resultado = Boolean.TRUE;
            break;
            case FenixConstants.PA14_PREPARAR_DOCUMENTO_CIERRE:
                resultado = Boolean.TRUE;
            break;
            case FenixConstants.PA14_NEGOCIAR_DOCUMENTO_CIERRE:
                resultado = Boolean.TRUE;
            break;
            case FenixConstants.PA14_CERRAR_CUENTAS_CONTABLES:
                resultado = Boolean.TRUE;
            break;
            case FenixConstants.PA14_CERRAR_CUENTAS_BANCARIAS_INVERSIONES:
                resultado = Boolean.TRUE;
            break;
            case FenixConstants.PA14_VALIDAR_DOCUMENTO_CIERRE:
                resultado = Boolean.TRUE;
            break;
            case FenixConstants.PA14_CUSTODIAR_DOCUMENTO_CIERRE:
                resultado = agregarNumeroCustodiaCierre();
            if(!resultado){
                budleKeyErroList.add("MSG_ERROR_FINALIZAR_TAREA");
                returnAction = null;
            }
            break;
        default:
        break;
        }        
            if(budleKeyErroList.size()>0){
                for(String bundleKey : budleKeyErroList)
                             JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
    
        getPopupFinalizarTarea().hide();
         

        return returnAction;
       
    }

    public String cancelarFinalizarTarea() {
        logger.log(ADFLogger.WARNING, "Inside aceptarFinalizarTarea." );
        getPopupFinalizarTarea().hide();
        return null;
    }

    public void setPopupMasInformacion(RichPopup popupMasInformacion) {
        this.popupMasInformacion = popupMasInformacion;
    }

    public RichPopup getPopupMasInformacion() {
        return popupMasInformacion;
    }

    public String aceptarMasInformacion() {
        logger.log(ADFLogger.TRACE, "Inside aceptarMasInformacion.");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        
        cargarDocumentosOnBase();
            
            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();

        // Cerramos popup de Confirmar Mas informacion
        getPopupMasInformacion().hide();
        
        return returnAction;
    }

    public String cancelarMasInformacion() {
        popupMasInformacion.hide();
        return null;
    }

    public String invocarMasInformacion() {
        CierreFideicomisoBean cierreFideicomisoBean = (CierreFideicomisoBean) JSFUtils.resolveExpression("#{pageFlowScope.cierreFideicomisoBean}");
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean isValidMasInformacion = Boolean.FALSE;
        Boolean isComentario = Boolean.FALSE;
        Boolean isDocumento = Boolean.FALSE;
        Boolean isValidarDocumento = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA14_SOLICITAR_CIERRE_OPERACION:
            isComentario = validateComentario(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                              cierreFideicomisoBean.getCodigoTarea(), getInstanciaTarea());
            
            if(!isComentario){
                //Se valida que se ingrese un comentario
                budleKeyErroList.add("MSG_03_SOLICITAR_CIERRE_OPERACION");
            }
            //Valida que se haya ingresado un comentario
            isValidMasInformacion = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_SOLICITAR_DOCUMENTO_CIERRE:
            isComentario = validateComentario(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                              cierreFideicomisoBean.getCodigoTarea(), 
                                              getInstanciaTarea());
            
            if(!isComentario){
                //Valida que se ingrese un comentario
                budleKeyErroList.add("MSG_02_SOLICITAR_DOCUMENTO_CIERRE");
            }
            
            isValidMasInformacion = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_PREPARAR_DOCUMENTO_CIERRE:
            isComentario = validateComentario(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                                          cierreFideicomisoBean.getCodigoTarea(), 
                                                          getInstanciaTarea());
            isDocumento = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                                          FenixConstants.DOCUMENTO_BORRADOR_DOCUMENTO_CIERRE,
                                                          Integer.parseInt(cierreFideicomisoBean.getCodigoTarea()));
            
            if(!isComentario || !isDocumento){
                //Valida que se haya ingresado un comentario y adjuntado un documento
                budleKeyErroList.add("MSG_04_PREPARAR_DOCUMENTO_CIERRE");
                isValidarDocumento = Boolean.FALSE;
            }else
                isValidarDocumento = Boolean.TRUE;
        
            isValidMasInformacion = (isValidarDocumento) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_NEGOCIAR_DOCUMENTO_CIERRE:
            isComentario = validateComentario(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                              cierreFideicomisoBean.getCodigoTarea(), 
                                              getInstanciaTarea());
            isDocumento = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                            FenixConstants.DOCUMENTO_BORRADOR_DOCUMENTO_CIERRE,
                                            Integer.parseInt(cierreFideicomisoBean.getCodigoTarea()));
            
            if(!isComentario && !isDocumento){
                //Valida que no se a adjuntado el documento "Borrador documento de cierre" y 
                //Agregado un comentario
                budleKeyErroList.add("MSG_03_NEGOCIAR_DOCUMENTO_CIERRE");
                isValidarDocumento = Boolean.FALSE;
            }else 
                isValidarDocumento = Boolean.TRUE;
            
            isValidMasInformacion = (isValidarDocumento) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_VALIDAR_DOCUMENTO_CIERRE:
            isComentario = validateComentario(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                              cierreFideicomisoBean.getCodigoTarea(), 
                                              getInstanciaTarea());
            
            if(!isComentario){
                //Valida que se ha ingresado un comentario
                budleKeyErroList.add("MSG_03_VALIDAR_DOCUMENTO_CIERRE");
            }
            
            isValidMasInformacion = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
        break;
        default:
        break;
        }
        logger.warning("Finaliza  " + isValidMasInformacion );
        if (!isValidMasInformacion){
            if(budleKeyErroList.size()>0){
                for(String bundleKey : budleKeyErroList)
                             JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));}
            }else{
        masInformacionPopup();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public String invocarFinalizarTarea() {
        CierreFideicomisoBean cierreFideicomisoBean = (CierreFideicomisoBean)
                                                      JSFUtils.resolveExpression("#{pageFlowScope.cierreFideicomisoBean}");
        
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean isValidarFinalizarTarea = Boolean.FALSE;
        Boolean isValidarDocumento = Boolean.FALSE;
        Boolean isComentario = Boolean.FALSE;
        Boolean isJustificacionCierre = Boolean.FALSE;
        Boolean isNumeroCustodiaDocCierre = Boolean.FALSE;
        
        Boolean isDocumento = Boolean.FALSE;
        Boolean isDocumentoAux = Boolean.FALSE;
        Map params = null;
        
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA14_SOLICITAR_CIERRE_OPERACION:
            if(null == cierreFideicomisoBean.getJustificacion() || cierreFideicomisoBean.getJustificacion().trim().length()==0){
                //Valida que la justificación no este vacia
                budleKeyErroList.add("MSG_02_SOLICITAR_CIERRE_OPERACION");
                isJustificacionCierre = Boolean.FALSE;
            }else {
                isJustificacionCierre = Boolean.TRUE;
            }
            
            isValidarFinalizarTarea = (isJustificacionCierre) ? Boolean.TRUE : Boolean.FALSE;

            break;
        case FenixConstants.PA14_REVISAR_CUENTAS_CONTABLES:
            isDocumento = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                                     FenixConstants.DOCUMENTO_ESTADOS_FINANCIEROS);
            if(!isDocumento){
                //Valida que se haya adjuntado el documento Estados Financieros
                budleKeyErroList.add("MSG_01_REVISAR_CUENTAS_CONTABLES");
            }
            isValidarFinalizarTarea = (isDocumento) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_REVISAR_CUENTAS_BANCARIAS_INVERSIONES:
            
            isComentario = validateComentario(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                              cierreFideicomisoBean.getCodigoTarea(), 
                                              getInstanciaTarea());
            isDocumento = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                                         FenixConstants.DOCUMENTO_ESTADO_CUENTAS_BANCARIAS);
            isDocumentoAux = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                                         FenixConstants.DOCUMENTO_ESTADO_CUENTAS_CONTABLES);
            
            if(!isComentario && !isDocumento && !isDocumentoAux){
                //Valida que se haya adjuntado El documento "Estado de cuentas Bancarias",
                //el documento "Estado de cuentas contables" o se agregue un comentario
                budleKeyErroList.add("MSG_02_REVISAR_CUENTAS_BANCARIAS_INVERSIONES");
                isValidarDocumento = Boolean.FALSE;
            }else
                isValidarDocumento = Boolean.TRUE;
            
            isValidarFinalizarTarea = (isValidarDocumento) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_SOLICITAR_DOCUMENTO_CIERRE:
            isComentario = validateComentario(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                              cierreFideicomisoBean.getCodigoTarea(), 
                                              getInstanciaTarea());
            isDocumento = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                                          FenixConstants.DOCUMENTO_BORRADOR_DOCUMENTO_CIERRE,
                                                          Integer.parseInt(cierreFideicomisoBean.getCodigoTarea()));
            
            if(!isComentario && !isDocumento){
                //Valida que se adjunte un documento o se agregue un comentario
                budleKeyErroList.add("MSG_01_SOLICITAR_DOCUMENTO_CIERRE");
                isValidarDocumento = Boolean.FALSE;
            }else
                isValidarDocumento = Boolean.TRUE;
            
            isValidarFinalizarTarea = (isValidarDocumento) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_PREPARAR_DOCUMENTO_CIERRE:
            
            logger.warning("Ejecuta validacion de documento Id Tipo: " + 
                           FenixConstants.DOCUMENTO_PLACEME);
            isDocumento = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                            FenixConstants.DOCUMENTO_PLACEME,
                                            Integer.parseInt(cierreFideicomisoBean.getCodigoTarea()));
            params = new HashMap();
            try{
                logger.warning("Asigna parametros para validacion de documento Id Tipo: " + 
                               FenixConstants.DOCUMENTO_BORRADOR_DOCUMENTO_CIERRE);
                params.put("idOperacion", Long.valueOf(cierreFideicomisoBean.getIdOperacion()));
                params.put("idTipoDocumento", FenixConstants.DOCUMENTO_BORRADOR_DOCUMENTO_CIERRE);
                
                isDocumentoAux = (Boolean) execute(params, "validarDocumentoAux");
            }catch(Exception e){
                logger.severe("Error al validar documento Id Tipo: " + 
                              FenixConstants.DOCUMENTO_BORRADOR_DOCUMENTO_CIERRE, e);
            }
            
            if(!isDocumento || !isDocumentoAux){
                //Valida que se haya adjuntado al menos un documento
                budleKeyErroList.add("MSG_02_PREPARAR_DOCUMENTO_CIERRE");
                isValidarDocumento = Boolean.FALSE;
            }else
                isValidarDocumento = Boolean.TRUE;
            
            isValidarFinalizarTarea = (isValidarDocumento) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_NEGOCIAR_DOCUMENTO_CIERRE:
            isDocumento = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                            FenixConstants.DOCUMENTO_CIERRE_FIRMADO);
            
            if(!isDocumento){
                //Valida que se haya adjuntado el documento Cierre Firmado
                budleKeyErroList.add("MSG_01_NEGOCIAR_DOCUMENTO_CIERRE");
            }
            isValidarFinalizarTarea = (isDocumento) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_CERRAR_CUENTAS_CONTABLES:
            isDocumento = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                            FenixConstants.DOCUMENTO_ESTADOS_FINANCIEROS_FINALES);
            
            if(!isDocumento){
                //Valida que se haya agregado el documento estados financieros
                budleKeyErroList.add("MSG_02_CERRAR_CUENTAS_CONTABLES");
            }
            isValidarFinalizarTarea = (isDocumento) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_CERRAR_CUENTAS_BANCARIAS_INVERSIONES:
            isDocumento = validateDocumento(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                            FenixConstants.DOCUMENTO_CONFIRMACION_CIERRE_CUENTAS_BANCARIAS_INVERSIONES);
            
            if(!isDocumento){
                //Valida que se haya agregado el documento estados financieros
                budleKeyErroList.add("MSG_02_CERRAR_CUENTAS_BANCARIAS_INVERSIONES");
            }
            isValidarFinalizarTarea = (isDocumento) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PA14_VALIDAR_DOCUMENTO_CIERRE:
            isValidarFinalizarTarea = Boolean.TRUE;
        break;
        case FenixConstants.PA14_CUSTODIAR_DOCUMENTO_CIERRE:
            if(null == cierreFideicomisoBean.getNumeroCustodiaDocCierre() || cierreFideicomisoBean.getNumeroCustodiaDocCierre().trim().length()==0){
                //Valida que la justificación no este vacia
                budleKeyErroList.add("MSG_02_CUSTODIAR_DOCUMENTO_CIERRE");
                isNumeroCustodiaDocCierre = Boolean.FALSE;
            }else {
                isNumeroCustodiaDocCierre = Boolean.TRUE;
            }
            
            isValidarFinalizarTarea = (isNumeroCustodiaDocCierre) ? Boolean.TRUE : Boolean.FALSE;
        break;
        default:
            break;
        }
        logger.warning("Finaliza  " + isValidarFinalizarTarea );
        if (!isValidarFinalizarTarea){
            if(budleKeyErroList.size()>0){
                for(String bundleKey : budleKeyErroList)
                             JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));}
            }else{
                    finalizarTareaPopup();
            }
        return null;
    }
    
    public void finalizarTareaPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }
    
    public void masInformacionPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupMasInformacion.show(hints);
    }
    
    public void cancelarTareaPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCancelarTarea.show(hints);
    }

    public String invocarCancelarTarea() {
        CierreFideicomisoBean cierreFideicomisoBean = (CierreFideicomisoBean)
                                                      JSFUtils.resolveExpression("#{pageFlowScope.cierreFideicomisoBean}");
        
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean isValidarCancelarTarea = Boolean.FALSE;
        Boolean isComentario = Boolean.FALSE;
        
        
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
            case FenixConstants.PA14_NEGOCIAR_DOCUMENTO_CIERRE:
                isComentario = validateComentario(Long.valueOf(cierreFideicomisoBean.getIdOperacion()), 
                                                  cierreFideicomisoBean.getCodigoTarea(), 
                                                  getInstanciaTarea());
            
            if(!isComentario){
                //Valida que se haya agregado un comentario para cancelar la tarea
                budleKeyErroList.add("MSG_02_NEGOCIAR_DOCUMENTO_CIERRE");
            }
            isValidarCancelarTarea = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
            break;        
            default:
                break;
            }
            logger.warning("Cancelar  " + isValidarCancelarTarea );
            if (!isValidarCancelarTarea){
                if(budleKeyErroList.size()>0){
                    for(String bundleKey : budleKeyErroList)
                                 JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));}
                }else{
        cancelarTareaPopup();
        }
        return null;
    }

    public void setPopupCancelarTarea(RichPopup popupCancelarTarea) {
        this.popupCancelarTarea = popupCancelarTarea;
    }

    public RichPopup getPopupCancelarTarea() {
        return popupCancelarTarea;
    }

    public String aceptarCancelarTarea() {
        popupCancelarTarea.hide();
        return null;
    }

    public String cancelarAccionTarea() {
        popupCancelarTarea.hide();
        return null;
    }

     /**
     ???? * Se crea metodo para Obtener la justificacion de la base de datos
     ???? * @param 
     ???? * @since 08/08/2016
     ???? * @by José Hernández Cruz
     ???? */   
    public String obtenerJustificacion() {
        // Add event code here...
        logger.log(ADFLogger.WARNING, "Inside CierreFideicomisoActionBean obtenerJustificacion." );
        String justificacion = null; 
        CierreFideicomisoBean cierreFideicomisoBean = (CierreFideicomisoBean) JSFUtils.resolveExpression("#{pageFlowScope.cierreFideicomisoBean}");
        String idOperacion = cierreFideicomisoBean.getIdOperacion();
        BindingContainer bindings = getBindings();
        try{
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerJustificacion");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.execute();
        justificacion =(String) operationBinding.getResult();
        cierreFideicomisoBean.setJustificacion(justificacion);
        }catch(Exception ex){
            logger.log(ADFLogger.WARNING, "Error en obtenerJustificacion " + ex.getClass() + ":" + ex.getMessage());
            JSFUtils.addFacesErrorMessage("Error al guardar la Justificacion. Por favor intente más tarde.");          
        }
        
        return "irRevisarCuentasContables";
    }
    
    /**
    ???? * Se crea metodo para almacenar la justificacion 
            en la tarea de Solicitar cierre de la operación 
    ???? * @param 
    ???? * @since 08/08/2016
    ???? * @by José Hernández Cruz
    ???? */  
    public Boolean agregarJustificacion(){
        logger.log(ADFLogger.WARNING, "Inside agregarJustificacion." );
        CierreFideicomisoBean cierreFideicomisoBean = (CierreFideicomisoBean)
                                                      JSFUtils.resolveExpression("#{pageFlowScope.cierreFideicomisoBean}");
        Boolean resultado = null;
        String idOperacion = cierreFideicomisoBean.getIdOperacion();
        String justificacion = cierreFideicomisoBean.getJustificacion();
        BindingContainer bindings = getBindings();
        try{
        OperationBinding operationBinding = bindings.getOperationBinding("agregarJustificacion");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("justificacion", justificacion);
        operationBinding.execute();
            resultado = (Boolean)operationBinding.getResult();
            
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en agregarJustificacion " + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Error al guardar la Justificacion. Por favor intente más tarde.");
            
        }
        return resultado;
    }

    public Boolean agregarNumeroCustodiaCierre(){
        logger.log(ADFLogger.WARNING, "Inside agregarJustificacion." );
        CierreFideicomisoBean cierreFideicomisoBean = (CierreFideicomisoBean)
                                                      JSFUtils.resolveExpression("#{pageFlowScope.cierreFideicomisoBean}");
        Boolean resultado = null;
        String idOperacion = cierreFideicomisoBean.getIdOperacion();
        String numeroCustodiaDocCierre = cierreFideicomisoBean.getNumeroCustodiaDocCierre();
        BindingContainer bindings = getBindings();
        try{
        OperationBinding operationBinding = bindings.getOperationBinding("agregarNumeroCustodia");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("numeroCustodiaDocCierre", numeroCustodiaDocCierre);
        operationBinding.execute();
            resultado = (Boolean)operationBinding.getResult();
            
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en agregarJustificacion " + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Error al guardar la Justificacion. Por favor intente más tarde.");
            
        }
        return resultado;
    }    
    private Integer getCodigoTarea() {
        CierreFideicomisoBean cierreFideicomisoBean = (CierreFideicomisoBean) 
                                                        JSFUtils.resolveExpression("#{pageFlowScope.cierreFideicomisoBean}");
        
        if (null != cierreFideicomisoBean.getCodigoTarea() && cierreFideicomisoBean.getCodigoTarea().trim().length() > 0) {
            return Integer.parseInt(cierreFideicomisoBean.getCodigoTarea());
        }
        
        return 0;    
    }

    public void setObtenerJustificacion(RichOutputText obtenerJustificacion) {
        this.obtenerJustificacion = obtenerJustificacion;
    }

    public RichOutputText getObtenerJustificacion() {
        this.obtenerJustificacion();
        return obtenerJustificacion;

    }
    
    public void guardarJustificacionActionListener(ActionEvent actionEvent) {
        logger.warning("Dentro de  guardarJustificacionActionListener");
        CierreFideicomisoBean cierreFideicomisoBean = (CierreFideicomisoBean)
                JSFUtils.resolveExpression("#{pageFlowScope.cierreFideicomisoBean}");
        Boolean resultado = null;
        String idOperacion = cierreFideicomisoBean.getIdOperacion();
        String justificacion = cierreFideicomisoBean.getJustificacion();
        BindingContainer bindings = getBindings();
        logger.warning("idOperacion :"+idOperacion);
        logger.warning("justificacion :"+justificacion);
        try{
            OperationBinding operationBinding = bindings.getOperationBinding("agregarJustificacion");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("justificacion", justificacion);
            operationBinding.execute();
            resultado = (Boolean)operationBinding.getResult();
            logger.warning("resultado :"+resultado);
            if(resultado){
                JSFUtils.addFacesInformationMessage("Información actualizada correctamente");
            }else{
                JSFUtils.addFacesErrorMessage("Error al guardar la Justificacion. Por favor intente más tarde.");
            }
        }catch(Exception e){
            logger.severe("Error en agregarJustificacion :",e);
            JSFUtils.addFacesErrorMessage("Error al guardar la Justificacion. Por favor intente más tarde.");
            
        }
        logger.warning("Fuera de  guardarJustificacionActionListener");
    }
}
