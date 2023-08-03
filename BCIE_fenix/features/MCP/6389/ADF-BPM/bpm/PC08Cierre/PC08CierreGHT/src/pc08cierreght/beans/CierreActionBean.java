package pc08cierreght.beans;

import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;


import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.OperationContainer;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class CierreActionBean extends FenixActionBean {
    
    public static final String BUNDLE ="pc08cierreght.PC08CierreGHTBundle";
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(CierreActionBean.class);
    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;
    private RichOutputText consultarLineaCredito;

    public CierreActionBean() {
        super();
    }

    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea) {
        this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupFinalizarTarea() {
        return popupFinalizarTarea;
    }

    public String aceptarFinalizarTarea() {
        LOGGER.log(ADFLogger.WARNING, "Inside aceptarFinalizarTarea." );
        
        Boolean aceptarFinalizar = Boolean.FALSE;
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean resultado = Boolean.FALSE;
        
            InvokeActionBean invokeActionBean = null;
            String returnAction = null;
                
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PC08_SOLICITAR_CIERRE_OPERACION:
        case FenixConstants.PC08_CERTIFICAR_ADEUDOS:
                if (getCodigoTarea().compareTo(FenixConstants.PC08_SOLICITAR_CIERRE_OPERACION) == 0) {
                    LOGGER.warning("Entra a Solicitar cierre de operacion");
                }
            if (getCodigoTarea().compareTo(FenixConstants.PC08_CERTIFICAR_ADEUDOS) == 0) {
                LOGGER.warning("Entra a Certificar Adeudos");
            }
            aceptarFinalizar = Boolean.TRUE;
        break;
        case FenixConstants.PC08_LIBERAR_GARANTIAS: 
        //Metodo para almacenar  la respuesta a la pregunta "¿Requiere liberación de garantías?"
            resultado = actualizarObservacion();
            if(!resultado){
                budleKeyErroList.add("MSG_ERROR_FINALIZAR_TAREA_LIBERAR_GARANTIAS");
                aceptarFinalizar = Boolean.FALSE;
            }else{          
                aceptarFinalizar = Boolean.TRUE;
            }
        break;
        case FenixConstants.PC08_GESTIONAR_ESCRITURA:
        case FenixConstants.PC08_VALIDAR_REGISTRO_ESCRITURA:
        case FenixConstants.PC08_VALIDAR_CIERRE_CLIENTE:
            if (getCodigoTarea().compareTo(FenixConstants.PC08_GESTIONAR_ESCRITURA) == 0) {
                LOGGER.warning("Entra a Gestionar escritura");
            }
            if (getCodigoTarea().compareTo(FenixConstants.PC08_VALIDAR_REGISTRO_ESCRITURA) == 0) {
                LOGGER.warning("Entra a Validar registro de escritura");
            }
            if (getCodigoTarea().compareTo(FenixConstants.PC08_VALIDAR_CIERRE_CLIENTE) == 0) {
                LOGGER.warning("Entra a Validar cierre con el cliente");
            }
            aceptarFinalizar = Boolean.TRUE;
        break;
        default:
        break;
        }
        
            cargarDocumentosOnBase();
            // Cerramos popup de Confirmar Finalizar Tarea
            getPopupFinalizarTarea().hide();
        LOGGER.log(ADFLogger.WARNING, "Valor aceptar Finalizar :" + aceptarFinalizar);
        if (aceptarFinalizar){
            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();
            return returnAction;
        }else{
            if(budleKeyErroList.size()>0){
                for(String bundleKey : budleKeyErroList)
                             JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));}
            return null;
        }
    }

    public String cancelarFinalizarTarea() {
        LOGGER.warning("Cancela confirmacion de Finalizar tarea.");
        popupFinalizarTarea.hide();
        return null;
    }

    public void setPopupMasInformacion(RichPopup popupMasInformacion) {
        this.popupMasInformacion = popupMasInformacion;
    }

    public RichPopup getPopupMasInformacion() {
        return popupMasInformacion;
    }

    public String aceptarMasInformacion() {
        LOGGER.log(ADFLogger.TRACE, "Inside aceptarMasInformacion.");
            InvokeActionBean invokeActionBean = null;
            String returnAction = null;
                
                invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                returnAction = invokeActionBean.invokeOperation();

            // Cerramos popup de Confirmar Mas informacion
            getPopupMasInformacion().hide();
            
            return returnAction;
    }

    public String cancelarMasInformacion() {
        LOGGER.warning("Cancela confirmacion de Mas informacion.");
        popupMasInformacion.hide();
        
        return null;
    }
    /**
    ???? * Metodo para Retornar la tarea 
    ???? * 
    ???? * @since 21/07/2016
    ???? */
    public String invocarMasInformacion() {
        CierreBean cierreBean = (CierreBean) JSFUtils.resolveExpression("#{pageFlowScope.cierreBean}");
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean isValidMasInformacion = Boolean.FALSE;
        Boolean isComentario = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PC08_SOLICITAR_CIERRE_OPERACION:
            LOGGER.log(ADFLogger.WARNING, "INTO invocar Mas Info tarea PC08_VALIDAR_CIERRE_CLIENTE");
            isComentario = validateComentario(Long.valueOf(cierreBean.getIdOperacion()), 
                                              cierreBean.getCodigoTarea(), getInstanciaTarea());
            
            if(!isComentario){
                //Se valida que se ingrese un comentario para enviar la solitud de cierre al abogado
                budleKeyErroList.add("MSG_01_SOLICITAR_CIERRE_OPERACION");
            }
            //Valida que la solicitud haya pasado por la tarea Certificar Adeudos
            //Valida que se haya ingresado un comentario
            isValidMasInformacion = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PC08_CERTIFICAR_ADEUDOS:
            LOGGER.log(ADFLogger.WARNING, "INTO invocar Mas Info tarea PC08_CERTIFICAR_ADEUDOS");
            isComentario = validateComentario(Long.valueOf(cierreBean.getIdOperacion()), 
                                              cierreBean.getCodigoTarea(), getInstanciaTarea());
            
            if(!isComentario){
                //Se valida que se ingrese un comentario para enviar la solitud de cierre al abogado
                budleKeyErroList.add("MSG_02_CERTIFICAR_ADEUDOS");
            }
            //Valida que la solicitud haya pasado por la tarea Certificar Adeudos
            //Valida que se haya ingresado un comentario
            isValidMasInformacion = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PC08_LIBERAR_GARANTIAS:
            LOGGER.log(ADFLogger.WARNING, "INTO invocar Mas Info tarea PC08_LIBERAR_GARANTIAS");
            isComentario = validateComentario(Long.valueOf(cierreBean.getIdOperacion()), 
                                              cierreBean.getCodigoTarea(), getInstanciaTarea());
            
            if(!isComentario){
                //Se valida que se ingrese un comentario para Retornar al ejecutivo
                budleKeyErroList.add("MSG_02_LIBERAR_GARANTIAS");
            }
            isValidMasInformacion = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
        break;
        default:
        break;
        }
        LOGGER.warning("Mas informacion  " + isValidMasInformacion );
        if (!isValidMasInformacion){
            if(budleKeyErroList.size()>0){
                for(String bundleKey : budleKeyErroList)
                             JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));}
            }else{
                masInformacionPopup();
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
    
    private Integer getCodigoTarea() {
        CierreBean cierreBean = (CierreBean) JSFUtils.resolveExpression("#{pageFlowScope.cierreBean}");
        
        if (null != cierreBean.getCodigoTarea() && cierreBean.getCodigoTarea().trim().length() > 0) {
            return Integer.parseInt(cierreBean.getCodigoTarea());
        }
        
        return 0;    
    }
    
    /**
    ???? * Metodo para abrir el Gestor de Operaciones en 
         * la tarea Solicitar Cierre Operacion
    ???? * @param actionEvent
    ???? * @since 20/07/2016
    ???? * @by José Hernández Cruz
    ???? */ 
    public void abrirGestorOperacion(ActionEvent actionEvent){
        LOGGER.log(ADFLogger.TRACE, "Inside abrirGestorOperacion.");
        StringBuilder script = new StringBuilder();
        String codigoOperacion = null;
        
        // Asignación de variables
        codigoOperacion = JSFUtils.resolveExpression("#{bindings.CodigoOperacion.inputValue}") == null ? "" : 
            JSFUtils.resolveExpression("#{bindings.CodigoOperacion.inputValue}").toString();
        
        // Abrimos popup con aplicación del Gestor de Operaciones
        ExtendedRenderKitService erks = Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                    ExtendedRenderKitService.class);
        script.append("window.open(\"").append(this.getUrlGestorOperaciones());
        script.append("?pIdOperacion=").append(codigoOperacion);
        script.append("\", \"DetalleOperacion\"");
        script.append(",\"width=1024px,height=1024px,resizable=1,location=0,scrollbars=1,fullscreen=1\")");
        erks.addScript(FacesContext.getCurrentInstance(), script.toString());
    }
    
    //Se obtiene la URL_GESTOR_OPERACIONES
    public String getUrlGestorOperaciones() {
        DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_GESTOR_OPERACIONES"}));
        String url = row == null ? null : (String) row.getAttribute("Valor");
        return url;
    }
    
    /**
    ???? * Metodo para abrir el Gestor de Clientes en 
         * la tarea Solicitar Cierre Operacion
    ???? * @param actionEvent
    ???? * @since 20/07/2016
    ???? * @by José Hernández Cruz
    ???? */ 
    public void abrirGestorCliente(ActionEvent actionEvent){
        LOGGER.log(ADFLogger.TRACE, "Inside abrirGestorCliente.");
        StringBuilder script = new StringBuilder();
        String codigoCliente = null;
        
        // Asignación de variables
        codigoCliente = JSFUtils.resolveExpression("#{bindings.CodigoCliente.inputValue}") == null ? "" : 
            JSFUtils.resolveExpression("#{bindings.CodigoCliente.inputValue}").toString();
        
        // Abrimos popup con aplicación del Gestor de Clientes
        ExtendedRenderKitService erks = Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                    ExtendedRenderKitService.class);
        script.append("window.open(\"").append(this.getUrlGestorCliente());
        script.append("?pIdCliente=").append(codigoCliente);
        script.append("\", \"DetalleCliente\"");
        script.append(",\"width=1024px,height=1024px,resizable=1,location=0,scrollbars=1,fullscreen=1\")");
        erks.addScript(FacesContext.getCurrentInstance(), script.toString());
    }
    
    //Se obtiene la URL_GESTOR_CLIENTES
    public String getUrlGestorCliente() {
        DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_GESTOR_CLIENTES"}));
        String url = row == null ? null : (String) row.getAttribute("Valor");
        return url;
    }
    
    /**
    ???? * Se invoca el metodo para Finalizar la tarea
    ???? * @param 
    ???? * @since 21/07/2016
    ???? * @by José Hernández Cruz
    ???? */   
    public String invocarFinalizarTarea() {
        CierreBean cierreBean = (CierreBean) JSFUtils.resolveExpression("#{pageFlowScope.cierreBean}");
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean isComentario = Boolean.FALSE;
        //Validacion de documentos para la tarea PC08_SOLICITAR_CIERRE_OPERACION
        Boolean isInformeCierre = Boolean.FALSE;
        Boolean isSolicitudCierreParteCliente = Boolean.FALSE;
        Boolean isLibearGarantias = Boolean.FALSE;//Validar documento de la tarea Liberar Garantias
        Boolean isLiberarGarantiaRegistrada = Boolean.FALSE; //Validar documento de la tarea Gestionar Escritura
        Boolean isLeccionAprendida = Boolean.FALSE;
        Boolean isCertificarAdeudos = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PC08_SOLICITAR_CIERRE_OPERACION:
            LOGGER.log(ADFLogger.WARNING, "INTO invocar finalizar tarea PC08_SOLICITAR_CIERRE_OPERACION");
            try{
                if(null != cierreBean.getIdOperacion() && null != cierreBean.getCodigoTarea()){
            isComentario = validateComentario(Long.valueOf(cierreBean.getIdOperacion()), 
                                              cierreBean.getCodigoTarea(),
                                              getInstanciaTarea());
                }else{
                    LOGGER.log(ADFLogger.WARNING, "Operacion o codigo de tarea son nulos.");
                }
                if(null != cierreBean.getIdOperacion() && FenixConstants.TD_INFORME_CIERRE > 0){
            isInformeCierre = validateDocumento(Long.valueOf(cierreBean.getIdOperacion()), 
                                                FenixConstants.TD_INFORME_CIERRE);
                }else{
                    LOGGER.log(ADFLogger.WARNING, "Operacion o documento TD_INFORME_CIERRE son nulos.");
                }
                if(null != cierreBean.getIdOperacion() && FenixConstants.TD_SOLICITUD_CIERRE_PARTE_CLIENTE > 0){
            isSolicitudCierreParteCliente = validateDocumento(Long.valueOf(cierreBean.getIdOperacion()), 
                                                              FenixConstants.TD_SOLICITUD_CIERRE_PARTE_CLIENTE);
                }else{
                    LOGGER.log(ADFLogger.WARNING, "Operacion o documento TD_SOLICITUD_CIERRE_PARTE_CLIENTE son nulos.");
                }
            if(cierreBean.getEnviarAsjur() == null || cierreBean.getEnviarAsjur().equals("")){
                budleKeyErroList.add("MSG_ERROR_FINALIZAR_TAREA");
                LOGGER.log(ADFLogger.ERROR, "El valor obtenido del payload es nulo o vacio.");
            }
            
            if(Boolean.parseBoolean(cierreBean.getEnviarAsjur()) == Boolean.TRUE){
            LOGGER.log(ADFLogger.WARNING, "INTO En finalizar tarea ASJUR.");
            if(!isComentario){
                    //Se valida que se ingrese un comentario para enviar la solitud de cierre al abogado
                    budleKeyErroList.add("MSG_06_SOLICITAR_CIERRE_OPERACION");
            }else {
                //Valida que la solicitud haya pasado por la tarea Certificar Adeudos
                //Valida que se haya ingresado un comentario
                cierreBean.setMensajeFinalizar(getStringFromBundle("SOLICITAR_CIERRE_OPERACION_CONFIRMAR_FINALIZAR_TAREA"));
                isValidFinalizarTarea = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
                isCertificarAdeudos = Boolean.FALSE;
            }
        }else
                isCertificarAdeudos = Boolean.TRUE;
            
        if(isCertificarAdeudos){
           LOGGER.log(ADFLogger.WARNING, "INTO En outcome finalizar tarea Certificar adeudos.");
                if(!isInformeCierre && !isSolicitudCierreParteCliente){
                    //Se valida que se haya adjuntado el documento 
                    //"Solicitud de cierre por parte del cliente" o "Infore de cierre"
                    budleKeyErroList.add("MSG_07_SOLICITAR_CIERRE_OPERACION");
                    isValidFinalizarTarea = Boolean.FALSE;
                } else {
            cierreBean.setMensajeFinalizar(getStringFromBundle("CERTIFICAR_ADEUDOS_CONFIRMAR_FINALIZAR_TAREA"));
            isValidFinalizarTarea = (isSolicitudCierreParteCliente || isInformeCierre) ? Boolean.TRUE : Boolean.FALSE;
            }
            }
            }catch(Exception e){
                LOGGER.log(ADFLogger.ERROR, "Error al finalizar tarea en PC08_SOLICITAR_CIERRE_OPERACION." + e.getClass() + "." + e.getMessage());
            }
        break;
        case FenixConstants.PC08_CERTIFICAR_ADEUDOS:
            LOGGER.log(ADFLogger.WARNING, "INTO invocar finalizar tarea PC08_CERTIFICAR_ADEUDOS");
            try{
            isCertificarAdeudos = validateDocumento(Long.valueOf(cierreBean.getIdOperacion()), 
                                                    FenixConstants.TD_CERTIFICACION_ADEUDOS);
            
            if(!isCertificarAdeudos){
                //Valida que se haya adjuntado el documento Certificación de Adeudos
                budleKeyErroList.add("MSG_01_CERTIFICAR_ADEUDOS");
            }
            isValidFinalizarTarea = (isCertificarAdeudos) ? Boolean.TRUE : Boolean.FALSE;
            }catch(Exception e){
                LOGGER.log(ADFLogger.ERROR, "Error al finalizar tarea en PC08_CERTIFICAR_ADEUDOS." + e.getClass() + "." + e.getMessage());
            }
        break;
        case FenixConstants.PC08_LIBERAR_GARANTIAS:
            LOGGER.log(ADFLogger.WARNING, "INTO invocar finalizar tarea PC08_LIBERAR_GARANTIAS");
            try{
//                isLibearGarantias = validateDocumento(Long.valueOf(cierreBean.getIdOperacion()), 
//                                                      FenixConstants.TD_ESCRITURA_LIBERACION_GARANTIAS);
                
                if(cierreBean.getValueLiberarGarantia().equals("Si")){
//                        if(!isLibearGarantias){
//                            //Valida que no se ha adjuntado el documento Escritura de Liberación de garantías registrada
//                            budleKeyErroList.add("MSG_06_LIBERAR_GARANTIAS");
//                        }
                        cierreBean.setMensajeFinalizar(getStringFromBundle("GESTION_ESCRITURA_CONFIRMAR_FINALIZAR_TAREA"));
                        isValidFinalizarTarea = Boolean.TRUE;
                }
                if(cierreBean.getValueLiberarGarantia().equals("No")){
                      cierreBean.setMensajeFinalizar(getStringFromBundle("CIERRE_CLIENTE_CONFIRMAR_FINALIZAR_TAREA"));
                        isValidFinalizarTarea =  Boolean.TRUE;
                    }
                }catch(NullPointerException npe){
                        budleKeyErroList.add("MSG_01_LIBERAR_GARANTIAS");
                    npe.getStackTrace();
                    }
        break;
        case FenixConstants.PC08_GESTIONAR_ESCRITURA:
            LOGGER.log(ADFLogger.WARNING, "INTO invocar finalizar tarea PC08_GESTIONAR_ESCRITURA");
            isLiberarGarantiaRegistrada = validateDocumento(Long.valueOf(cierreBean.getIdOperacion()), 
                                                            FenixConstants.TD_ESCRITURA_LIBERACION_GARANTIAS_REGISTRADA,
                                                            Integer.parseInt(cierreBean.getCodigoTarea()));
            
            if(!isLiberarGarantiaRegistrada){
                //Valida que no se ha adjuntado el documento Escritura de Liberación de garantías registrada
                budleKeyErroList.add("MSG_01_GESTIONAR_ESCRITURA");
            }
            isValidFinalizarTarea = (isLiberarGarantiaRegistrada) ? Boolean.TRUE : Boolean.FALSE;
        break;
        case FenixConstants.PC08_VALIDAR_REGISTRO_ESCRITURA:
            LOGGER.log(ADFLogger.WARNING, "INTO invocar finalizar tarea PC08_VALIDAR_REGISTRO_ESCRITURA");
            isLiberarGarantiaRegistrada = validateDocumento(Long.valueOf(cierreBean.getIdOperacion()), 
                                                            FenixConstants.TD_ESCRITURA_LIBERACION_GARANTIAS_REGISTRADA,
                                                            Integer.parseInt(cierreBean.getCodigoTarea()));
            
            if(!isLiberarGarantiaRegistrada){
                //Valida que no se ha adjuntado el documento Escritura de Liberación de garantías registrada
                budleKeyErroList.add("MSG_DOCUMENTO_ESCRITURA");
            }
           // isValidFinalizarTarea= validateDocumento(Long.parseLong(cierreBean.getIdOperacion()), FenixConstants.DOCUMENTO_ESCRITURA_LIBERACION_GARANTIAS);
            isValidFinalizarTarea = (isLiberarGarantiaRegistrada) ? Boolean.TRUE : Boolean.FALSE;
        break;
        
        case FenixConstants.PC08_VALIDAR_CIERRE_CLIENTE:
            LOGGER.log(ADFLogger.WARNING, "INTO invocar finalizar tarea PC08_VALIDAR_CIERRE_CLIENTE");
            isLeccionAprendida = validateDocumento(Long.valueOf(cierreBean.getIdOperacion()), 
                                                   FenixConstants.TD_INFORME_CIERRE_LECCIONES_APRENDIDAS);
            if(!isLeccionAprendida){
                //Valida que no se ha ingresado el documento de Informe de cierre y lecciones aprendidas
                budleKeyErroList.add("MSG_DOCUMENTO_LECCIONES");
            }
            
            isValidFinalizarTarea = (isLeccionAprendida) ? Boolean.TRUE : Boolean.FALSE;
            break;
        default:
        break;
        }
        LOGGER.warning("Finaliza  " + isValidFinalizarTarea );
        if (!isValidFinalizarTarea){
            if(budleKeyErroList.size()>0){
                for(String bundleKey : budleKeyErroList)
                             JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));}
            }else{
                finalizarTareaPopup();
            }
            return null;
    }
    
    /**
    ???? * Se crea metodo para almacenar  la respuesta a la pregunta "¿Requiere liberación de garantías?"
           que se requiere en la tarea “Liberar garantías”, 
    ???? * @param idOperacion, cancelarObservacion
    ???? * @since 04/08/2016
    ???? */  
    public Boolean actualizarObservacion(){
        LOGGER.log(ADFLogger.WARNING, "Inside actualizarObservacion." );
        Boolean resultado = Boolean.FALSE;
        Boolean requiereLiberacion = Boolean.FALSE;
        oracle.jbo.domain.Number idOperacion = null; 
        String requiereLibGarantias = null;
        
        CierreBean cierreBean = (CierreBean) JSFUtils.resolveExpression("#{pageFlowScope.cierreBean}");
        idOperacion = new oracle.jbo.domain.Number (Integer.parseInt(cierreBean.getIdOperacion()));
        requiereLibGarantias = cierreBean.getValueLiberarGarantia();
        BindingContainer bindings = getBindings();
        
        try{
            LOGGER.log(ADFLogger.WARNING, "Valor liberar garantias..." + requiereLibGarantias);
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarObservacionCancelar");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("requiereLibGarantias", requiereLibGarantias);
        operationBinding.execute();
            if(requiereLibGarantias.equals("Si")){
                requiereLiberacion = Boolean.TRUE;
                AttributeBinding attributeBinding = ADFUtils.findControlBinding("requiereLiberacion");
                LOGGER.severe("Revisar Valor del payload en true antes de actualizar ." + attributeBinding.getInputValue());
                actualizarPayloadElement("requiereLiberacion", requiereLiberacion);
                LOGGER.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBinding.getInputValue());
            }else{
                  AttributeBinding attributeBinding = ADFUtils.findControlBinding("requiereLiberacion");
                  LOGGER.severe("Revisar Valor del payload en false Antes de actualizar." + attributeBinding.getInputValue());
                actualizarPayloadElement("requiereLiberacion", requiereLiberacion);
                  LOGGER.severe("Revisar Valor del payload en false Despues de Actualizar." + attributeBinding.getInputValue());
              }
        resultado = Boolean.TRUE;
            
        }catch(Exception e){
            LOGGER.log(ADFLogger.ERROR, "Error en actualizarObservacion " + e.getClass() + ":" + e.getMessage());
            resultado = Boolean.FALSE;
        }
        return resultado;
    }
    
    /**
     * Obtiene cadena valor del Resource Bundle
     * @param psKey contiene clave de bundle
     * @return devuelve cadena
     */
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }
}
