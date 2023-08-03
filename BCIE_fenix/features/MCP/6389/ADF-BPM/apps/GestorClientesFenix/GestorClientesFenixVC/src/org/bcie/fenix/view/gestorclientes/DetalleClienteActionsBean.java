package org.bcie.fenix.view.gestorclientes;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class DetalleClienteActionsBean implements Serializable {
    @SuppressWarnings("compatibility:8332328305424533663")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    private RichPopup popUpSolicitarIntervencionUCE;
    private RichPopup popUpSolicitarSeguimientoCrediticio;
    private RichPopup popUpArgumentarCalificacionScr;
    private RichPopup popUpRevaluarSeguimientoCrediticio;
    private RichPopup popUpReasignarResponsableCliente;
    
    private String justificacionSuspenso;
    public String getJustificacionSuspenso(){
        return justificacionSuspenso;
    }
    
    public void setJustificacionSuspenso(String justificacionSuspenso){
        this.justificacionSuspenso = justificacionSuspenso;
    }
    
    private static final Integer PROCESO_UCE = 27;
    private static final Integer PROCESO_SEGUIMIENTO_CREDITICIO = 20;
    private static final Integer TAREA_BPM_REVALORIZACION = 111;
    private static final String GENERIC_INSTANCIA_TAREA = "0000000";
    private RichRegion regionDocumentosBinding;
    
    public static final String BUNDLE = "org.bcie.fenix.view.GestorClientesFenixVCBundle";

    public DetalleClienteActionsBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void solicitarIntervencionUceActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside solicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        showPopUpSolicitarIntervencionUce();
    }
    
    public void aceptarSolicitarIntervencionUceActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside aceptarSolicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        getPopUpSolicitarIntervencionUCE().hide();
        this.solicitarIntervencionUce();
    }
    
    public void cancelarSolicitarIntervencionUceActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside cancelarSolicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        getPopUpSolicitarIntervencionUCE().hide();
    }
    
    
    public void solicitarIntervencionUce(){
        logger.log(ADFLogger.WARNING, "Inside solicitarIntervencionUce.");
        Long idCliente= (Long)JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
        logger.log(ADFLogger.WARNING, "idCliente value: "+idCliente);
        String responsableCliente = ADFContext.getCurrent().getSecurityContext().getUserName();
        logger.log(ADFLogger.WARNING, "responsableCliente value: "+responsableCliente);
        Map<String,Object> resultado = new HashMap<String,Object>();
        String mensaje = null;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesosCliente");
        operationBinding.getParamsMap().put("cliente", idCliente);
        operationBinding.getParamsMap().put("proceso", PROCESO_UCE);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_UCE"));
        } else {
            try{
                logger.log(ADFLogger.WARNING, "value idCliente : " + idCliente);
                operationBinding = bindings.getOperationBinding("solicitarIntervencionUCE");
                operationBinding.getParamsMap().put("idCliente", idCliente);
                operationBinding.getParamsMap().put("responsableCliente", responsableCliente);
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                } else {
                    resultado = (Map<String,Object>)operationBinding.getResult();
                    mensaje = (String)resultado.get("mensaje");
                    if((Boolean)resultado.get("resultado")){
                        JSFUtils.addFacesInformationMessage(mensaje);
                    }else{
                        JSFUtils.addFacesErrorMessage(mensaje);
                    }
                }
            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "Exception in solicitarIntervencionUce:" + e.getMessage());
            }
        }
    }
    
    public void solicitarSeguimientoCrediticioActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside solicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        showPopUpSolicitarSeguimientoCrediticio();
    }
    
    public void aceptarSolicitarSeguimientoCrediticioActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside aceptarSolicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        getPopUpSolicitarSeguimientoCrediticio().hide();
        this.solicitarSeguimientoCrediticio();
    }
    
    public void cancelarSolicitarSeguimientoCrediticioActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside cancelarSolicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        getPopUpSolicitarSeguimientoCrediticio().hide();
    }
    
    public void solicitarSeguimientoCrediticio(){
        logger.log(ADFLogger.WARNING, "Inside solicitarSeguimientoCreditcio.");
        Long idCliente= (Long)JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
        logger.log(ADFLogger.WARNING, "idCliente value: "+idCliente);
        String responsableCliente = ADFContext.getCurrent().getSecurityContext().getUserName();
        logger.log(ADFLogger.WARNING, "responsableCliente value: "+responsableCliente);
        
        
        Map<String,Object> resultado = new HashMap<String,Object>();
        String mensaje = null;
        String tipoInicio = "NORMAL";
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesosCliente");
        operationBinding.getParamsMap().put("cliente", idCliente);
        operationBinding.getParamsMap().put("proceso", PROCESO_SEGUIMIENTO_CREDITICIO);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SEGUIMIENTO_CREDITICIO"));
        } else {
            try{
                logger.log(ADFLogger.WARNING, "value idCliente : " + idCliente);
                operationBinding = bindings.getOperationBinding("solicitarInicioSeguimientoCrediticio");
                operationBinding.getParamsMap().put("idCliente", idCliente);
                operationBinding.getParamsMap().put("responsableCliente", responsableCliente);
                operationBinding.getParamsMap().put("tipoInicio", tipoInicio);
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                } else {
                    resultado = (Map<String,Object>)operationBinding.getResult();
                    mensaje = (String)resultado.get("mensaje");
                    if((Boolean)resultado.get("resultado")){
                        JSFUtils.addFacesInformationMessage(mensaje);
                    }else{
                        JSFUtils.addFacesErrorMessage(mensaje);
                    }
                }
            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "Exception in solicitarSeguimientoCrediticio:" + e.getMessage());
            }
        }
    }
    
    private BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public boolean validarComentario(String comentario){
        
        boolean valido = true;
        if(comentario != null){
            
            String strLongitud = getStringFromBundle("JUSTIFICACION_LONGITUD");
            int intLongitud = 0;
            if(strLongitud != null){
                intLongitud = Integer.parseInt(strLongitud);
            }
            
            if(comentario != null &&
               intLongitud > 0){
                if(comentario.length() > intLongitud){
                    valido = false;
                }else{
                    if(comentario.trim().isEmpty()){
                        valido = false;
                    }
                }
            }
        }
        return valido;
    }
    
    @SuppressWarnings("unchecked")
    private void agregarJustificacionSuspenso() {
        
        DetalleClienteBean detalleClienteBean = 
            (DetalleClienteBean)JSFUtils.resolveExpression("#{pageFlowScope.DetalleClienteBean}");
                
        if(detalleClienteBean == null){
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_NO_DETCLIBEAN",BUNDLE));
            return;
        }
        
            String justificacion = detalleClienteBean.getJustificacionSuspenso();
            if(justificacion == null || justificacion.isEmpty()){
                justificacion = "Este es un mensaje de prueba con la justificación en null";
            }
            
            boolean valido = validarComentario(justificacion);
            if(valido){
                
      
                    BindingContainer bindings = getBindings();
                    
                    Long idCliente= (Long)JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
                    logger.log(ADFLogger.WARNING, "idCliente value: "+idCliente);
                    String responsableCliente = ADFContext.getCurrent().getSecurityContext().getUserName();
                    
                    OperationBinding operationBinding = bindings.getOperationBinding("agregarComentarioCliente");
                    operationBinding.getParamsMap().put("loginUsuario", responsableCliente);
                    operationBinding.getParamsMap().put("nombreUsusario", responsableCliente);
                    operationBinding.getParamsMap().put("idTcaTareaBpm", TAREA_BPM_REVALORIZACION);
                    operationBinding.getParamsMap().put("idCliente", idCliente);
                    operationBinding.getParamsMap().put("idInstanciaTarea", GENERIC_INSTANCIA_TAREA );
                    operationBinding.getParamsMap().put("comentario",justificacion);
                    
                    Object result = operationBinding.execute();
                    
                }
                else
                {
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_NO_COMMENTS",BUNDLE));
                }
        detalleClienteBean.setJustificacionSuspenso(null);
    }


    public Boolean inicioArgumentarCalificacionScrActionListener() {
        logger.log(ADFLogger.WARNING, "Inside inicioArgumentarCalificacionScrActionListener.");
        Long idCliente= (Long)JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
        logger.log(ADFLogger.WARNING, "idCliente value: "+idCliente);
        String responsableCliente = ADFContext.getCurrent().getSecurityContext().getUserName();
        logger.log(ADFLogger.WARNING, "responsableCliente value: "+responsableCliente);
        
        Integer ContadorErrores = 0;
        
        try {
            logger.log(ADFLogger.WARNING, "value idCliente : " + idCliente);
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("inicioAprobacionCliente");
            operationBinding.getParamsMap().put("idCliente", idCliente);
            operationBinding.getParamsMap().put("loginUsuario", responsableCliente);
            Boolean result = (Boolean) operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                logger.log(ADFLogger.ERROR, "Error");
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());

                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar Argumentar Calificion SCR realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en inicioArgumentarCalificacionSCR " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }
    
    public void inicioRevaluarSeguimientoCrediticio(){
        logger.log(ADFLogger.WARNING, "Inside inicioRevaluarSeguimientoCrediticio.");
        Long idCliente= (Long)JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
        logger.log(ADFLogger.WARNING, "idCliente value: "+idCliente);
        String responsableCliente = ADFContext.getCurrent().getSecurityContext().getUserName();
        logger.log(ADFLogger.WARNING, "responsableCliente value: "+responsableCliente);
        Map<String,Object> resultado = new HashMap<String,Object>();
        String mensaje = null;
        String tipoInicio = "SUSPENSO";
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesosCliente");
        operationBinding.getParamsMap().put("cliente", idCliente);
        operationBinding.getParamsMap().put("proceso", PROCESO_SEGUIMIENTO_CREDITICIO);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SEGUIMIENTO_CREDITICIO"));
        } else {
            try{
                logger.log(ADFLogger.WARNING, "value idCliente : " + idCliente);
                operationBinding = bindings.getOperationBinding("solicitarInicioSeguimientoCrediticio");
                operationBinding.getParamsMap().put("idCliente", idCliente);
                operationBinding.getParamsMap().put("responsableCliente", responsableCliente);
                operationBinding.getParamsMap().put("tipoInicio", tipoInicio);
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                } else {
                    resultado = (Map<String,Object>)operationBinding.getResult();
                    mensaje = (String)resultado.get("mensaje");
                    if((Boolean)resultado.get("resultado")){
                        //tarjeta 13190 -inicio
                        this.agregarJustificacionSuspenso();
                        //tarjeta 13190 - fin
                        JSFUtils.addFacesInformationMessage(mensaje);
                    } else {
                        JSFUtils.addFacesErrorMessage(mensaje);
                    }
                }
            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "Exception in solicitarSeguimientoCrediticio:" + e.getMessage());
            }
        }
    }
    
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }
    
    private void showPopUpArgumentarCalificacionScr(){
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpArgumentarCalificacionScr().show(popupHints);
    }
    
    private void showPopUpRevaluarSeguimientoCrediticio(){
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpRevaluarSeguimientoCrediticio().show(popupHints);
    }
    
    private void showPopUpSolicitarSeguimientoCrediticio(){
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpSolicitarSeguimientoCrediticio().show(popupHints);
    }
    
    private void showPopUpSolicitarIntervencionUce(){
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpSolicitarIntervencionUCE().show(popupHints);
    }

    public void setPopUpSolicitarIntervencionUCE(RichPopup popUpSolicitarIntervencionUCE) {
        this.popUpSolicitarIntervencionUCE = popUpSolicitarIntervencionUCE;
    }

    public RichPopup getPopUpSolicitarIntervencionUCE() {
        return popUpSolicitarIntervencionUCE;
    }

    public RichPopup getPopUpSolicitarSeguimientoCrediticio() {
        return popUpSolicitarSeguimientoCrediticio;
    }

    public void setPopUpSolicitarSeguimientoCrediticio(RichPopup popUpSolicitarSeguimientoCrediticio) {
        this.popUpSolicitarSeguimientoCrediticio = popUpSolicitarSeguimientoCrediticio;
    }

    public void solicitarArgumentarCalificacionScrActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside solicitarArgumentarCalificacionScrActionListener: " + actionEvent.getComponent().getId());
        showPopUpArgumentarCalificacionScr();
    }

    public void solicitarRevaluarSeguimientoCrediticioActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside solicitarRevaluarSeguimientoCrediticioActionListener: " + actionEvent.getComponent().getId());
        showPopUpRevaluarSeguimientoCrediticio();
    }

    public void aceptarArgumentarCalificacionScrActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside aceptarSolicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        getPopUpArgumentarCalificacionScr().hide();
        this.inicioArgumentarCalificacionScrActionListener();
    }

    public void cancelarArgumentarCalificacionScrActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside cancelarSolicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        getPopUpArgumentarCalificacionScr().hide();
    }

    public void aceptarRevaluarSeguimientoCrediticioActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside aceptarSolicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        getPopUpRevaluarSeguimientoCrediticio().hide();
        
        this.inicioRevaluarSeguimientoCrediticio();

    }

    public void cancelarRevaluarSeguimientoCrediticioActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE,"Inside cancelarSolicitarIntervencionUceActionListener: " + actionEvent.getComponent().getId());
        getPopUpRevaluarSeguimientoCrediticio().hide();
    }

    public void setPopUpArgumentarCalificacionScr(RichPopup popUpArgumentarCalificacionScr) {
        this.popUpArgumentarCalificacionScr = popUpArgumentarCalificacionScr;
    }

    public RichPopup getPopUpArgumentarCalificacionScr() {
        return popUpArgumentarCalificacionScr;
    }

    public void setPopUpRevaluarSeguimientoCrediticio(RichPopup popUpRevaluarSeguimientoCrediticio) {
        this.popUpRevaluarSeguimientoCrediticio = popUpRevaluarSeguimientoCrediticio;
    }

    public RichPopup getPopUpRevaluarSeguimientoCrediticio() {
        return popUpRevaluarSeguimientoCrediticio;
    }

    public void iniciarGestionCobroActionListener(ActionEvent actionEvent) {
        Long idCliente= (Long)JSFUtils.resolveExpression("#{bindings.IdCliente.inputValue}");
        Map resultado = null;
        
        String responsableCliente = ADFContext.getCurrent().getSecurityContext().getUserName();
        logger.log(ADFLogger.WARNING, "responsableCliente value: " + responsableCliente);
        
        AttributeBinding modalidadBinding = ADFUtils.findControlBinding("ReqEnvioAvisoCobroAut");
        Boolean requiereAutomatico=Boolean.FALSE;
        if(null!=modalidadBinding.getInputValue()){
            if((Boolean)modalidadBinding.getInputValue()){
                requiereAutomatico=Boolean.TRUE;
            }
        }
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("inicioGestionCobro");
        
        operationBinding.getParamsMap().put("idTarea", 111);
        operationBinding.getParamsMap().put("idCliente", idCliente.intValue());
        operationBinding.getParamsMap().put("responsableCliente", responsableCliente);
        operationBinding.getParamsMap().put("inicio", FenixConstants.INICIO_MANUAL);
        operationBinding.getParamsMap().put("automatico", requiereAutomatico);
        
        operationBinding.execute();
       
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            resultado = (Map)operationBinding.getResult();
            Boolean success = (Boolean) resultado.get("success");
            String message = (String) resultado.get("message");
            if(null != success && success.equals(Boolean.TRUE)){
                JSFUtils.addFacesInformationMessage("Se ha iniciado el proceso de Gesti\u00F3n de Cobro correctamente");
            }else{
                if(message.compareTo("Ya existe un proceso de Gesti\u00F3n de Cobro en ejecuci\u00F3n") == 0)
                    JSFUtils.addFacesErrorMessage(message);
                else if(message.compareTo("Error al iniciar el proceso GestionCobro") == 0)
                    JSFUtils.addFacesErrorMessage(message);
                    //JSFUtils.addFacesErrorMessage("No se ha iniciado el proceso de Gesti\u00F3n de Cobro");
                else if (message == "")
                    JSFUtils.addFacesErrorMessage("No se ha iniciado el proceso de Gesti\u00F3n de Cobro");
            }
        }
    }

    public boolean isEnvioAutomaticoAdapter() {
        boolean envioAutomatico = Boolean.TRUE;
        //Invertimos el valor de ReqEnvioAvisoCobroAut
        AttributeBinding reqEnvioAvisoCobroAutBinding = ADFUtils.findControlBinding("ReqEnvioAvisoCobroAut");
        if (null != reqEnvioAvisoCobroAutBinding) {
            if (null != reqEnvioAvisoCobroAutBinding.getInputValue()) {
                try {
                    envioAutomatico = (Boolean)reqEnvioAvisoCobroAutBinding.getInputValue();
                } catch(Exception e) {
                    logger.log(ADFLogger.ERROR, "no se puede convertir el valor de envio automatico", e);
                }
            } else {
                //El valor en base de datos viene nulo y se debe mostrar como un 1
                //En el RETURN se INVIERTE su valor
                envioAutomatico = Boolean.FALSE;
            }
        }
        return !envioAutomatico;
    }

    public void setEnvioAutomaticoAdapter(boolean envioAutomaticoAdapter) {
        //Invertimos el valor de ReqEnvioAvisoCobroAut
        try {
            ADFUtils.setBoundAttributeValue("ReqEnvioAvisoCobroAut", !envioAutomaticoAdapter);
        } catch(Exception e) {
            logger.log(ADFLogger.ERROR, "no se puede establecer el valor de envio automatico", e);
        }
        
    }
    
    public void reasignarResponsableDelClienteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,"Inside reasignarResponsableDelClienteActionListener: " + actionEvent.getComponent().getId());
        showPopUpReasignarResponsableCliente();
    }
    
    private void showPopUpReasignarResponsableCliente(){
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpReasignarResponsableCliente().show(popupHints);
    }
    
    public void aceptarReasignarResponsableClienteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,"Inside aceptarReasignarResponsableClienteActionListener: " + actionEvent.getComponent().getId());
        getPopUpReasignarResponsableCliente().hide();
        this.reasignarResponsableDelCliente();
    }
    
    public void cancelarReasignarResponsableClienteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,"Inside cancelarReasignarResponsableClienteActionListener: " + actionEvent.getComponent().getId());
        getPopUpReasignarResponsableCliente().hide();
    }
    
    public void reasignarResponsableDelCliente(){
        logger.warning("inside: reasignarResponsableDelCliente");
        DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator("CrearClienteVOIterator");
        ViewObject voData = dcItteratorBindings.getViewObject(); 
        Map map = null;
        Long idClienteCurrentRow = null;
        Integer idCliente = null;
        String responsableCLiente = null;
        
        
        Row currentRow = voData.getCurrentRow();
        try{
            idClienteCurrentRow = (Long) currentRow.getAttribute("IdCliente");
            logger.warning("IdCliente currentRow: " + idClienteCurrentRow);
            idCliente = Integer.valueOf(idClienteCurrentRow.toString());
            logger.warning("idCliente: " + idCliente);
        }catch(Exception e){
            logger.warning("error al obtener el idCliente del currentRow o al convertir de Long a Integer el idCliente");
        }
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        try{
            AttributeBinding attributeBindingEjecutivo = null;
            attributeBindingEjecutivo = ADFUtils.findControlBinding("Responsable");
            String ejecutivo = attributeBindingEjecutivo.getInputValue().toString();
            logger.warning("Valor ejecutivo seleccionado de la lista de valores: " + ejecutivo);
            operationBinding = bindings.getOperationBinding("cambiarResponsableCliente");
            operationBinding.getParamsMap().put("ejecutivo", ejecutivo);
            operationBinding.execute();
        }
        catch(Exception e){
            logger.warning("ERROR, al ejecutar el metodo cambiarResponsableCliente... ", e);
        }
        
        try{
            responsableCLiente = currentRow.getAttribute("Ejecutivo").toString();
            logger.warning("Ejecutivo currentRow: " + responsableCLiente);
        }catch(Exception e){
            logger.warning("error al obtener el responsableCliente del currentRow");
        }
        
        operationBinding = bindings.getOperationBinding("inicioReasignarCliente");
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.getParamsMap().put("responsableCliente", responsableCLiente);
        operationBinding.execute();
        map = (Map)operationBinding.getResult();
        if (!operationBinding.getErrors().isEmpty()) {
            
        } else {
            Boolean existeError = (Boolean)map.get("success");
            String mensaje = (String)map.get("message");
            if(existeError){
                logger.warning("El servicio inicioReasignarCliente a regresado un ERROR");
                JSFUtils.addFacesInformationMessage(mensaje);
            }
            else{
                logger.warning("El servicio inicioReasignarCliente se ejecuto correctamente");
                JSFUtils.addFacesInformationMessage(mensaje);
            }
            ADFUtils.findIterator("BuscarClienteVOIterator").getViewObject().executeQuery();
        }
        
        logger.warning("finaliza: reasignarResponsableDelCliente");
    }

    public void iniciarDetalleCliente(ActionEvent actionEvent){
        logger.warning("inicia metodo iniciarDetalleCliente");
       
     
        Row rowCliente = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        try{
          DetalleClienteBean detalleClienteBean = (DetalleClienteBean)JSFUtils.resolveExpression("#{pageFlowScope.DetalleClienteBean}");
          operationBinding = bindings.getOperationBinding("consultarDetalleCliente");
          operationBinding.execute();
            
          rowCliente = (Row)operationBinding.getResult();
          detalleClienteBean.setDatosCliente(rowCliente); 
          verDatosCliente();
            
        }catch(Exception e){
          logger.warning("ha ocurrido un error al consultar el detalle del cliente-> " ,e);
        }
        
        logger.warning("termina metodo iniciarDetalleCliente");
        
    }

    public void verDatosCliente(){
        logger.warning("datos recuperados de DatosClienteBean");
        
        try{
          DatosClienteBean datosCliente = (DatosClienteBean)JSFUtils.resolveExpression("#{pageFlowScope.DatosClienteBean}");
        
            logger.warning(" RazonSocial: "+datosCliente.getRazonSocial());
            logger.warning(" TipoPersona: "+datosCliente.getTipoPersona());
            logger.warning(" Sector: "+datosCliente.getSector());
            logger.warning(" Pais: "+datosCliente.getPais());
            logger.warning(" DiaPago: "+datosCliente.getDiaPago()); 
            logger.warning(" ScrId: "+datosCliente.getScrId());
            logger.warning(" IdCliente: "+datosCliente.getIdCliente());
            logger.warning(" Oficina: "+datosCliente.getOficina());
            logger.warning(" Abreviatura: "+datosCliente.getAbreviatura());
            logger.warning(" TipoCliente: "+datosCliente.getTipoCliente()); 
            logger.warning(" TipoInstitucion: "+datosCliente.getTipoInstitucion());
            logger.warning(" GrupoEconomico: "+datosCliente.getGrupoEconomico());
            logger.warning(" NumeroIdentificacion: "+datosCliente.getNumeroIdentificacion());
            logger.warning(" IdFlexcube: "+datosCliente.getIdFlexcube());
            logger.warning(" EsDeteriorado: "+datosCliente.getEsDeteriorado());
            logger.warning(" Bic Code: "+datosCliente.getBicCode());
            logger.warning(" Direccion: "+datosCliente.getDireccion());
        }catch(Exception e){
            logger.warning("ha ocurrido un error al  onsultar los datos de DatosClienteBean");
        }  
        logger.warning("termina metodo iniciarDetalleCliente");
        
    }

        public void setRegionDocumentosBinding(RichRegion regionDocumentosBinding) {
            this.regionDocumentosBinding = regionDocumentosBinding;
        }
    
        public RichRegion getRegionDocumentosBinding() {
            return regionDocumentosBinding;
        }

      public void cargarRegionDocumentos(DisclosureEvent disclosureEvent) {
          DetalleClienteBean detalleClienteBean = (DetalleClienteBean)JSFUtils.resolveExpression("#{pageFlowScope.DetalleClienteBean}");

        if (disclosureEvent.isExpanded()) {
             detalleClienteBean.setActiveRegionDocumentos(true);
             AdfFacesContext.getCurrentInstance().addPartialTarget(regionDocumentosBinding);
             
             logger.warning("Refrescando pantalla...");
             FacesContext fctx = FacesContext.getCurrentInstance();
             String page = fctx.getViewRoot().getViewId();
             ViewHandler ViewH = fctx.getApplication().getViewHandler();
             UIViewRoot UIV = ViewH.createView(fctx, page);
             UIV.setViewId(page);
             fctx.setViewRoot(UIV);
    //    } else {
    //      activeRegionBean.setRegionActivation(false);
        }
      }
   
    

    public void setPopUpReasignarResponsableCliente(RichPopup popUpReasignarResponsableCliente) {
        this.popUpReasignarResponsableCliente = popUpReasignarResponsableCliente;
    }

    public RichPopup getPopUpReasignarResponsableCliente() {
        return popUpReasignarResponsableCliente;
    }
    
   
}
