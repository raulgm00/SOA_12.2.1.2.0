package pa13uceght.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class RecuperacionActionsBean extends FenixActionBean implements Serializable{
    @SuppressWarnings("compatibility:8476724599252805721")
    private static final long serialVersionUID = 1L;
    public static ADFLogger logger = null;
    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;
    public static final String BUNDLE = "pa13uceght.PA13UCEGHTBundle";


    public RecuperacionActionsBean() {
        super();
        if (logger == null){
            logger = ADFLogger.createADFLogger(RecuperacionActionsBean.class);
        }
    }
    
    public String invocarFinalizarTarea(){
        RecuperacionBean recuperacionBean = (RecuperacionBean) 
                                        JSFUtils.resolveExpression("#{pageFlowScope.recuperacionBean}");
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean isJustificacionSolicitudIntervencion = Boolean.FALSE;
        Boolean isDocumento = Boolean.FALSE;
        Boolean isAccionAgregada = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA13_SOLICITAR_TRASLADO_UCE:
            //Solicita la salida “Finalizar tarea” para la tarea "Solicitar traslado UCE"
            //RN_01
            //Para Finalizar la tarea, el campo de “Justificación de solicitud de intervención”  no debe estar vacío.
            isJustificacionSolicitudIntervencion=this.validarJustificacionSolicitudIntervencion(recuperacionBean.getIdCliente().longValue(),
                                                                                 recuperacionBean.getInstanciaProceso());
            if(!isJustificacionSolicitudIntervencion){
                //EX_01
                //El sistema valida que el campo “Justificación de solicitud de intervención” no esté vacío. MSG_01
                //MSG_01  
                //Para finalizar la tarea, es necesario ingresar el campo “Justificación de solicitud de intervención”.
                budleKeyErroList.add("MSG_01_SOLICITAR_TRASLADO_UCE");
            }
            //RN_02   
            //Para Finalizar la tarea, se debe adjuntar el documento tipo “Soporte justificación de Intervención de UCE”.
            isDocumento = validarDocumentoClientePorNumeroSerieDocumento(recuperacionBean.getIdCliente().longValue(),
                                                                            FenixConstants.TIPO_DOCUMENTO_SOPORTE_JUSTIFICACION_INTERVENCION_UCE,
                                                                                recuperacionBean.getNumeroSerieDocumento());
            if(!isDocumento){
                //EX_02   
                //El sistema valida que se haya adjuntado el documento “Soporte justificación de Intervención de UCE”. MSG_02   
                //MSG_02  
                //Para finalizar la tarea, es necesario adjuntar el documento tipo “Soporte justificación de Intervención de UCE”.
                budleKeyErroList.add("MSG_02_SOLICITAR_TRASLADO_UCE");
            }
            //Si alguna bandera "isJustificacionSolicitudIntervencion" o "isDocumento" es "FALSE"
            //No permitir finalizar la tarea y asignar a "isValidFinalizarTarea" el valor "FALSE"
            //Mostrar el mensaje solicitado.
            isValidFinalizarTarea = (isJustificacionSolicitudIntervencion && isDocumento) ? Boolean.TRUE : Boolean.FALSE;
            
            break;
        case FenixConstants.PA13_VALIDAR_SOLICITUD_DE_TRASLADO_UCE:
            //No existen ninguna validacion para finalizar "Validar solicitud de traslado UCE"
            isValidFinalizarTarea = Boolean.TRUE;
            break;
        case FenixConstants.PA13_ANALIZAR_Y_ELABORAR_PROPUESTAS_DE_ACCIONES:
            //RN_01   
            //Se debe capturar o modificar al menos un registro en el plan de acciones, para poder finalizar la tarea. 
            isAccionAgregada = validarAccionIngresadaPorCliente(FenixConstants.ESTADO_ACCION_REGISTRADO, 
                                                                FenixConstants.CATEGORIA_UCE, 
                                                                recuperacionBean.getIdCliente().longValue());
            if(!isAccionAgregada){
            //EX_01   
            //El sistema valida que exista al menos un registro en el plan de acciones que haya sido ingresado en esa tarea. MSG_01
            //MSG_01  
            //Para finalizar la tarea, es necesario capturar al menos un registro en el plan de acciones.
            budleKeyErroList.add("MSG_01_ANALIZAR_Y_ELABORAR_PROPUESTAS_DE_ACCIONES");
            }
            
            isValidFinalizarTarea = (isAccionAgregada) ? Boolean.TRUE : Boolean.FALSE;
            break;
        case FenixConstants.PA13_VALIDAR_PROPUESTAS_DE_ACCIONES:
            
            isAccionAgregada = validarAccionIngresadaPorCliente(FenixConstants.ESTADO_ACCION_REGISTRADO, 
                                                                FenixConstants.CATEGORIA_UCE, 
                                                                recuperacionBean.getIdCliente().longValue());
            if(!isAccionAgregada){
            //EX_01   
            //El sistema valida que exista al menos un registro en el plan de acciones. MSG_01
            //MSG_01    
            //Para finalizar la tarea, es necesario capturar al menos un registro en el plan de acciones.
            budleKeyErroList.add("MSG_01_VALIDAR_PROPUESTAS_DE_ACCIONES");
            }
            isValidFinalizarTarea = (isAccionAgregada) ? Boolean.TRUE : Boolean.FALSE;
            break;
        default:
        break;
        }
        logger.warning("Finaliza  " + isValidFinalizarTarea );
        if (!isValidFinalizarTarea){
            if(budleKeyErroList.size()>0){
                for(String bundleKey : budleKeyErroList)
                             JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));}
            }else{
                finalizarTareaPopup();
            }
            return null;
        }
    
    private Integer getCodigoTarea(){
        RecuperacionBean recuperacionBean = (RecuperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.recuperacionBean}");
        if (null != recuperacionBean.getCodigoTarea() && recuperacionBean.getCodigoTarea().trim().length() > 0){
            return Integer.parseInt(recuperacionBean.getCodigoTarea());}
        return 0;
    }
    
    public void finalizarTareaPopup(){
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            popupFinalizarTarea.show(hints);
    }


    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea) {
        this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupFinalizarTarea() {
        return popupFinalizarTarea;
    }

    public String cancelarFinalizarTarea() {
       getPopupFinalizarTarea().hide();
        return null;
    }

    public String aceptarFinalizarTarea() {
        logger.log(ADFLogger.TRACE, "Inside aceptarFinalizarTarea.");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        RecuperacionBean recuperacionBean = (RecuperacionBean) 
                                        JSFUtils.resolveExpression("#{pageFlowScope.recuperacionBean}");    
        invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        returnAction = invokeActionBean.invokeOperation();
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA13_VALIDAR_PROPUESTAS_DE_ACCIONES:
            logger.log(ADFLogger.TRACE, "Into aceptarFinalizarTarea  in Case VALIDAR PROPUESTAS DE ACCIONES.");
            String loginUsuario = (String)JSFUtils.resolveExpression("#{securityContext.userName}");
            String codigoRol = (String)JSFUtils.resolveExpression("#{data.pa13uceght_PA13ValidarPropuestasAccionesPageDef.CodigoRol.inputValue}");
            Integer codigoRolInteger = Integer.valueOf(codigoRol);
            String nombreCompleto = this.obtenerNombreCompleto(loginUsuario);
            Integer codigoTareaInteger = Integer.valueOf(recuperacionBean.getCodigoTarea());
            logger.log(ADFLogger.WARNING,"codigoRol :" + codigoRolInteger );
            logger.log(ADFLogger.WARNING,"codTarea :" +codigoTareaInteger );
            logger.log(ADFLogger.WARNING,"loginUsuario :" +loginUsuario );
            logger.log(ADFLogger.WARNING,"nombreCompleto :" +nombreCompleto );
            this.actualizarEstadosAccion(Boolean.TRUE,codigoRolInteger,codigoTareaInteger,loginUsuario,nombreCompleto);
            
            break;
        default:
            logger.log(ADFLogger.TRACE, "Into aceptarFinalizarTarea  in DEFAULT CASE.");
            break;
        }
        
        //invoca el metodo para subir documentos adjuntos en el gestorDocumentosCliente a onBase
        cargarDocumentosClienteOnBase();
        // Cerramos popup de Confirmar Finalizar Tarea
        getPopupFinalizarTarea().hide();
        return returnAction;
    }
    
    public String invocarMasInformacion() {
        RecuperacionBean recuperacionBean = (RecuperacionBean) 
                                        JSFUtils.resolveExpression("#{pageFlowScope.recuperacionBean}");
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean isValidMasInformacion = Boolean.FALSE;
        Boolean isComentarioCliente = Boolean.FALSE;
        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA13_SOLICITAR_TRASLADO_UCE:
            //Solicita la salida “Cancelar intervención”.
            //RN_03
            //Se debe capturar al menos un comentario obligatoriamente en esta tarea, siempre y 
            //cuando el responsable de la operación solicite la cancelación de la tarea.
            isComentarioCliente=validarComentarioCliente(recuperacionBean.getIdCliente().longValue(),
                                                         recuperacionBean.getCodigoTarea(),
                                                         getInstanciaTarea());
            if(!isComentarioCliente){
                //EX_03   
                //El sistema valida que al menos exista un comentario. 
                //MSG_03
                //Para el cancelar la tarea, es necesario capturar al menos un comentario.
                budleKeyErroList.add("MSG_03_VALIDAR_SOLICITAR_TRASLADO_UCE");
            }
            //Si "isComentarioCliente" es true, isValidMasInformacion = TRUE.
            //En otro caso  isValidMasInformacion = FALSE y no permitir finalizar la tarea.
            isValidMasInformacion = (isComentarioCliente) ? Boolean.TRUE : Boolean.FALSE;
            break;
        case FenixConstants.PA13_VALIDAR_SOLICITUD_DE_TRASLADO_UCE:
            //Solicita la salida “Retornar a responsable”. 
            //RN_01	
            //Se debe capturar al menos un comentario obligatoriamente en esta tarea, 
            //siempre y cuando el Gerente de país solicite el retorno al responsable de operación.
            isComentarioCliente=validarComentarioCliente(recuperacionBean.getIdCliente().longValue(),
                                                         recuperacionBean.getCodigoTarea(),
                                                         getInstanciaTarea());
            if(!isComentarioCliente){
                //EX_01   
                //El sistema valida que al menos exista un comentario. MSG_01
                //MSG_01  
                //Para retornar la tarea al responsable de la operación, es necesario capturar al menos un comentario.
                budleKeyErroList.add("MSG_01_VALIDAR_SOLICITAR_TRASLADO_UCE");
            }
            //Si alguna bandera "isComentarioCliente" es "FALSE"
            //No permitir MasInformacion y asignar a "isValidMasInformacion" el valor "FALSE"
            //Mostrar el mensaje solicitado.
            isValidMasInformacion = (isComentarioCliente) ? Boolean.TRUE : Boolean.FALSE;
            break;
        case FenixConstants.PA13_ANALIZAR_Y_ELABORAR_PROPUESTAS_DE_ACCIONES:
            //RN_02   
            //Se debe capturar al menos un comentario obligatoriamente en esta tarea, 
            //siempre y cuando el Analista UCE solicite el retorno al responsable de operación.
            isComentarioCliente=validarComentarioCliente(recuperacionBean.getIdCliente().longValue(),
                                                         recuperacionBean.getCodigoTarea(),
                                                         getInstanciaTarea());
            if(!isComentarioCliente){
                //EX_02	
                //El sistema valida que exista al menos un comentario. MSG_02
                //MSG_02	
                //Para retornar la tarea al responsable de la operación, es necesario capturar al menos un comentario.
                budleKeyErroList.add("MSG_02_ANALIZAR_Y_ELABORAR_PROPUESTAS_DE_ACCIONES");
            }
            //Si alguna bandera "isComentarioCliente" es "FALSE"
            //No permitir MasInformacion y asignar a "isValidMasInformacion" el valor "FALSE"
            //Mostrar el mensaje solicitado.
            isValidMasInformacion = (isComentarioCliente) ? Boolean.TRUE : Boolean.FALSE;
            break;
        
        case FenixConstants.PA13_VALIDAR_PROPUESTAS_DE_ACCIONES:
            //RN_01   
            //Se debe capturar al menos un comentario obligatoriamente en esta tarea, 
            //siempre y cuando el Jefe UCE solicite el retorno al analista UCE.
            isComentarioCliente=validarComentarioCliente(recuperacionBean.getIdCliente().longValue(),
                                                         recuperacionBean.getCodigoTarea(),
                                                         getInstanciaTarea());
            if(!isComentarioCliente){
                //EX_02   
                //El sistema valida que exista al menos un comentario. MSG_02
                //MSG_02	
                //Para retornar la tarea al analista de la UCE, es necesario capturar al menos un comentario.
                budleKeyErroList.add("MSG_02_VALIDAR_PROPUESTAS_DE_ACCIONES");
            }
            //Si alguna bandera "isComentarioCliente" es "FALSE"
            //No permitir MasInformacion y asignar a "isValidMasInformacion" el valor "FALSE"
            //Mostrar el mensaje solicitado.
            isValidMasInformacion = (isComentarioCliente) ? Boolean.TRUE : Boolean.FALSE;
            break;
        default:
            break;
        }
        logger.warning("Mas informacion  " + isValidMasInformacion );
        if (!isValidMasInformacion){
            if(budleKeyErroList.size()>0){
                for(String bundleKey : budleKeyErroList)
                             JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));}
            }else{
                masInformacionPopup();
            }
        return null;
    }
    
    public void masInformacionPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupMasInformacion.show(hints);
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


    public Boolean validarJustificacionSolicitudIntervencion(Long idCliente,String instanciaProceso){
        Boolean resultado = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validarJustificacionSolicitudIntervencion");
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            resultado = (Boolean) operationBinding.getResult();
        }
        return resultado;
    }
    
    public void actualizarEstadosAccion(Boolean requiereValidacion, Integer rolDUsuario,
                                        Integer idTarea, String loginUsuario, String nombre){
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("actualizarEstadosAccion");
        operationBinding.getParamsMap().put("requiereValidacion", requiereValidacion);
        operationBinding.getParamsMap().put("rolDUsuario", rolDUsuario);
        operationBinding.getParamsMap().put("idTarea", idTarea);
        operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
        operationBinding.getParamsMap().put("nombre", nombre);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
    }
    
    public String obtenerNombreCompleto(String loginUsuario){
        String nombreUsuarioCompleto = null;
        HashMap<String,Object> resultado = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("consultarAtributosUsuario");
        operationBinding.getParamsMap().put("login", loginUsuario);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            if(operationBinding.getResult() != null){
            resultado = (HashMap<String,Object>) operationBinding.getResult();
            nombreUsuarioCompleto=(String)resultado.get("nombreUsuario");
            } 
        }
        
        return nombreUsuarioCompleto;
    }

}

