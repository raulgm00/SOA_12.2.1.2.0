package pa15formalizacionenmiendasgenerichumantask.bean;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

import pa15formalizacionenmiendasgenerichumantask.util.PA15Constants;

public class FormalizacionEnmiendaActionBean extends FenixActionBean {

    private static final String BUNDLE =
        "pa15formalizacionenmiendasgenerichumantask.PA15FormalizacionEnmiendasGenericHumanTaskBundle";
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(FormalizacionEnmiendaActionBean.class);

    /*OUTCOMES usados en este proeso*/
    private static final String OUTCOME_FINALIZAR_TAREA = "FINALIZARTAREA";
    private static final String OUTCOME_MAS_INFORMACION = "MASINFORMACION";
    private static final String OUTCOME_REQUIERE_AJUSTES = "REQUIEREAJUSTES";
    private static final String OUTCOME_CANCELAR_FORMALIZACION_ENMIENDA = "CANCELARFORMALIZACIONENMIENDA";
    private static final String OUTCOME_MODIFICAR_CON_CAMBIOS_FINANCIEROS = "MODIFICARCONDICIONESFINANCIERAS";

    /** Bean del proceso. Inyectado desde la configuración del Taskflow.*/
    private FormalizacionEnmiendaBean formalizacionBean;

    /** Popup usado para solicitar confirmacion.*/
    private RichPopup popupConfirmacion;

    public FormalizacionEnmiendaActionBean() {
        super();
    }

    public void setFormalizacionBean(FormalizacionEnmiendaBean formalizacionBean) {
        this.formalizacionBean = formalizacionBean;
    }

    public FormalizacionEnmiendaBean getFormalizacionBean() {
        return formalizacionBean;
    }

    public void setPopupConfirmacion(RichPopup popupMasInfo) {
        this.popupConfirmacion = popupMasInfo;
    }

    public RichPopup getPopupConfirmacion() {
        return popupConfirmacion;
    }
    
    /**
     * Genera el título que de las formas.
     * Verifica el origen de la Formalización de Enmienda para mostrar o no la advertencia del origen genérico.
     * @return Título que debe mostrar la forma.
     */
    public String getFormTitle() {
        StringBuilder title = new StringBuilder(JSFUtils.resolveExpressionAsString("#{bindings.NombreTarea.inputValue}"));

        if (formalizacionBean != null &&
            formalizacionBean.getOrigen() == FenixModelConstants.ORIGEN_FORMALIZACION_ENMIENDA_GENERICO_VALUE) {
            title.append("-").append(ADFUtils.getStringFromBundle("FORM_TITLE_ORIGEN_GENERICO_WARNING", BUNDLE));
        }

        return title.toString();
    }
    
    /**
     * Genera el texto del popup de confirmación.
     * Verifica la acción seleccionada en el encabezado para definir el texto que debe ir en el popup.
     * @return Texto que debe mostrar el popup de confirmación.
     */
    public String getConfirmationPopupText() {
        String accion = JSFUtils.resolveExpressionAsString("#{viewScope.headerAction}");
        String text = null;
        
        switch (accion) {
        case OUTCOME_FINALIZAR_TAREA:
            text = ADFUtils.getStringFromBundle("POPUP_FINALIZAR_TXT", BUNDLE);
            break;
        case OUTCOME_MAS_INFORMACION:
            text = ADFUtils.getStringFromBundle("POPUP_MASINFO_TXT", BUNDLE);
            break;
        case OUTCOME_REQUIERE_AJUSTES:
            text = ADFUtils.getStringFromBundle("POPUP_AJUSTES_TXT", BUNDLE);
            break;
        case OUTCOME_CANCELAR_FORMALIZACION_ENMIENDA:
            text = ADFUtils.getStringFromBundle("POPUP_CANCELAR_PROCESO_TXT", BUNDLE);
            break;
        case OUTCOME_MODIFICAR_CON_CAMBIOS_FINANCIEROS:
            text = ADFUtils.getStringFromBundle("POPUP_CAMBIOS_FINANCIEROS_TXT", BUNDLE);
            break;
        }

        return text;
    }

    /**
     * Se encarga de mostrar el popup de confirmacion.
     */
    private void mostrarPopupConfirmacion() {
        RichPopup popup = getPopupConfirmacion();
        if (popup != null) {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupConfirmacion().show(popupHints);
        }
    }

    /**
     * Invoca los metodos especificos para cada una de las acciones disponibles en las formas.
     * @param actionEvent
     */
    public void invocarAccion(ActionEvent actionEvent) {
        LOGGER.info("[FormalizacionEnmiendas] Inicia invocarAccion");
        
        String accion = JSFUtils.resolveExpressionAsString("#{viewScope.headerAction}");
        switch (accion) {
        case OUTCOME_FINALIZAR_TAREA:
            invocarFinalizarTarea(actionEvent);
            break;
        case OUTCOME_MODIFICAR_CON_CAMBIOS_FINANCIEROS:
            invocarModificarCondicionesFinancieras(actionEvent);
            break;
        case OUTCOME_MAS_INFORMACION:
        case OUTCOME_REQUIERE_AJUSTES:
        case OUTCOME_CANCELAR_FORMALIZACION_ENMIENDA:        
            invocarMasInformacion(actionEvent);
            break;
        }

    }

    /**
     * Invoca métodos de validaciones de las tareas antes de proceder a preguntar por la confirmación de finalizar la tarea.
     * Si va bien, muestra el popup de confirmación.
     * Es responsabilidad de cada tarea el realizar sus validaciones y mostrar los mensajes necesarios.
     *
     * @param actionEvent
     */
    private void invocarFinalizarTarea(ActionEvent actionEvent) {
        LOGGER.info("[FormalizacionEnmiendas] Inicia invocarFinalizarTarea");

        //controla si se puede finalizar la tarea
        boolean finalizaOK = true;

        //Por cada una de las tareas se realizarán sus respectivas validaciones y acciones.
        switch (formalizacionBean.getCodigoTarea()) {
        case FenixConstants.PA15_SOLICITAR_FORMALIZACION_ENMIENDA:
            finalizaOK = validarSolicitarFormalizacion();
            break;
        case FenixConstants.PA15_ANALIZAR_SOLICITUD_ENMIENTA:
            finalizaOK = validarAnalizarSolicitud();
            break;
        case FenixConstants.PA15_VALIDAR_CONDICIONES_FINANCIERAS:
            //La forma de validar condiciones financieras no tiene validaciones/acciones adicionales
            finalizaOK = true;
            break;
        case FenixConstants.PA15_REVISAR_DOCUMENTACION_CLIENTE:
            //Esta forma no tiene validaciones/acciones adicionales para finalizar tarea
            finalizaOK = true;
            break;
        case FenixConstants.PA15_AJUSTAR_BORRADOR_ENMIENDA:
            finalizaOK = validarAjustarBorrador();
            break;
        case FenixConstants.PA15_DOCUMENTAR_FORMALIZACION_ENMIENDA:
            finalizaOK = validarDocumentarFormalizacion();
            break;
        case FenixConstants.PA15_CUSTODIAR_DOCUMENTACION_SOPORTE:
            finalizaOK = validarCustodiaDocumentacion();
            break;
        case FenixConstants.PA15_REGISTRAR_CAMBIOS:
            //Esta forma no tiene validaciones/acciones adicionales para finalizar tarea
            finalizaOK = true;
            break;
        case FenixConstants.PA15_VALIDAR_REGISTRO_CAMBIOS:
            finalizaOK = validarActualizarInformacionParaFinalizar();
            break;
        default:
            LOGGER.warning("[FormalizacionEnmiendas] Código de tarea no encontrado");
            finalizaOK = false;
            String strErrorMsg = ADFUtils.getStringFromBundle("TAREA_NO_ENCONTRADA_ERROR", BUNDLE);
            JSFUtils.addFacesErrorMessage(strErrorMsg);
            break;
        }

        if (finalizaOK) {
            mostrarPopupConfirmacion();
        }

        LOGGER.info("[FormalizacionEnmiendas] Finaliza invocarFinalizarTarea");
    }


    /**
     * Realiza las validaciones necesarias por la tarea SolicitarFormalización antes de su finalización.
     * Dentro de las validaciones que tiene la forma SolicitarFormalización se encuentran:
     * 1. Si el origen es GENERICO, entonces se deben haber adjuntado los documentos requeridos que se encuentren configurados en base de datos.
     *
     * @return {@code true} si y solo si las validaciones están OK  {@code false} en caso contrario.
     */
    private boolean validarSolicitarFormalizacion() {
        boolean valido = true;
        
        //solo si es genérica debe validar docs
        if (formalizacionBean != null &&
            formalizacionBean.getOrigen() == FenixModelConstants.ORIGEN_FORMALIZACION_ENMIENDA_GENERICO_VALUE) {
            //Hace uso de la nueva forma de validar documentos
            valido = this.validarDocumentosTarea("FinalizarTareaGenerica");
        }

        return valido;
    }

    /**
     * Realiza las validaciones necesarias por la tarea AnalizarSolicitud antes de su finalización.
     * Dentro de las validaciones que tiene esta forma se encuentran:
     * 1. Se deben haber adjuntado los documentos requeridos que se encuentren configurados en base de datos.
     * 2. [KB: 15525] Explicitamente se debe tener un adjunto de Borrador de contrato de Enmienda.
     *
     * @return {@code true} si y solo si las validaciones están OK  {@code false} en caso contrario.
     */
    private boolean validarAnalizarSolicitud() {
        boolean valido = true;
        //Para este caso se hace uso de la nueva forma de validar documentos
        valido = this.validarDocumentosTarea(null);

        //Si la tarea ya tiene todos sus documentos al día, verificar explicitamente que se adjunte el Borrador de contrato de ENmienda
        //Esto para que no se muestre dos veces el mensaje de error de que se requiere dicho documento
        if(valido){
            //[KB: 15525] Solicita validar explicitamente que exista un adjunto del Borrador de contrato de Enmienda.
            valido &= this.validarBorradorContratoEnmiendaAdjunto();
        }

        return valido;
    }


    /**
     * Realiza las validaciones necesarias por la tarea AjustarBorrador antes de su finalización.
     * Dentro de las validaciones que tiene esta forma se encuentran:
     * 1. Se deben haber adjuntado los documentos requeridos que se encuentren configurados en base de datos o se debe haber
     * adicionado un comentario.
     *
     * @return {@code true} si y solo si las validaciones están OK  {@code false} en caso contrario.
     */
    private boolean validarAjustarBorrador() {
        //Para este caso se hace uso de la nueva forma de validar documentos
        boolean validaDocs = this.validarDocumentosTarea(null);
        boolean validarComentario = validarComentarioPresente();
        
        // Para este caso, se deben remver los mensajes adicionados, pues unicamente nos interesa el valor booleano de la validacion
        removerFacesMessages();
        
        //Retornar de acuerdo a si se ha adjuntado el documento de borrador o se ha hecho un comentario
        return validaDocs || validarComentario;
    }
    
    /**
     * Realiza las validaciones necesarias por la tarea DocumentarFormalizacion antes de su finalización.
     * Dentro de las validaciones que tiene esta forma se encuentran:
     * 1. Se deben haber adjuntado los documentos requeridos que se encuentren configurados en base de datos.
     *
     * @return {@code true} si y solo si las validaciones están OK  {@code false} en caso contrario.
     */
    private boolean validarDocumentarFormalizacion() {
        boolean valido = true;
        //Para este caso se hace uso de la nueva forma de validar documentos
        valido = this.validarDocumentosTarea(null);

        return valido;
    }

    /**
     * Realiza las validaciones necesarias por la tarea DocumentarFormalizacion antes de su finalización.
     * Dentro de las validaciones que tiene esta forma se encuentran:
     * 1. Se deben haber adjuntado los documentos requeridos que se encuentren configurados en base de datos.
     *
     * @return {@code true} si y solo si las validaciones están OK  {@code false} en caso contrario.
     */
    private boolean validarCustodiaDocumentacion() {
        boolean valido = true;
        String msgError = ADFUtils.getStringFromBundle("FINALIZAR_TAREA_DEFAULT_ERROR", BUNDLE);
        
        String numeroCustodia = formalizacionBean.getNumCustodia();
        if(numeroCustodia==null){
            valido = false;
            msgError = ADFUtils.getStringFromBundle("CUSTODIA_DOC_FALTA_INFO_ERROR", BUNDLE);
        }

        if (!valido) {
            LOGGER.info("[FormalizacionEnmiendas] validarCustodiaDocumentacion incumplida: " + msgError);
            JSFUtils.addFacesErrorMessage(msgError);
        }


        return valido;
    }


    /**
     * Realiza las validaciones necesarias por la tarea ValidarRegistroCambios antes de su finalización.
     * Dentro de las validaciones que tiene esta forma se encuentran:
     * 1. Se debe haber actualizado la informacion previamente.
     * 
     * Si no se trata de una desobligacion, entonces no es necesario validar pues la informacion no iria a flexcube.
     *
     * @return {@code true} si y solo si las validaciones están OK  {@code false} en caso contrario.
     */
    private boolean validarActualizarInformacionParaFinalizar() {
        //solo validar cuando es desobligacion dado que es cuando se requiere propagar las linea de credito.
        if(!formalizacionBean.isDesobligacion()){
            return true;
        }
        
        boolean valido = true;        
        valido = formalizacionBean.isInfoLineaCreditoActualizada();

        if (!valido) {
            String msgError = ADFUtils.getStringFromBundle("VALIDAR_REGISTRO_FALTA_ACTUALIZAR_INFO_ERROR", BUNDLE);
            LOGGER.info("[FormalizacionEnmiendas] validarActualizarInformacionParaFinalizar incumplida: " + msgError);
            JSFUtils.addFacesErrorMessage(msgError);
        }


        return valido;
    }

    /**
     * Invoca métodos de validaciones de las tareas antes de proceder a solicitar más info.
     * Si va bien, muestra el popup de confirmación.
     * Es responsabilidad de cada tarea el realizar sus acciones/validaciones y mostrar los mensajes necesarios.
     * 
     * @param actionEvent 
     */
    private void invocarMasInformacion(ActionEvent actionEvent) {
        LOGGER.info("[FormalizacionEnmiendas] Inicia invocarMasInformacion");
        //controla si se puede solicitar má info
        //Actualmente esta es una validacion que aplica para todas las formas que ueden solicitar mas info
        boolean finalizaOK = validarComentarioPresente();

        if (finalizaOK) {
            mostrarPopupConfirmacion();
        }

        LOGGER.info("[FormalizacionEnmiendas] Finaliza invocarMasInformacion");
    }

    /**
     * [KB: 15525]
     * Realiza validaciones sobre el outcome Modificar Condiciones Financieras.
     * Valida que exista un comentario presente y además que se encuentre un adjunto del tipo Borrador de contrato de Enmienda.
     */
    private void invocarModificarCondicionesFinancieras(ActionEvent actionEvent) {
        LOGGER.info("[FormalizacionEnmiendas] Inicia invocarModificarCondicionesFinancieras");
        //controla si se puede solicitar má info
        //Actualmente esta es una validacion que aplica para todas las formas que ueden solicitar mas info
        boolean finalizaOK = validarComentarioPresente();
        //[KB: 15525] Solicita validar explicitamente que exista un adjunto del Borrador de contrato de Enmienda.
        finalizaOK &= validarBorradorContratoEnmiendaAdjunto();

        if (finalizaOK) {
            mostrarPopupConfirmacion();
        }

        LOGGER.info("[FormalizacionEnmiendas] Finaliza invocarMasInformacion");
    }

    /**
     * Ejecutar la aceptación de la acción seleccionada en el header.
     *
     * @return outcome OUTCOME de la accion seleccionada en el header.
     */
    public String aceptarPopupConfirmacionAction() {
        LOGGER.warning("[FormalizacionEnmiendas] Acepta popup de confirmacion");
        getPopupConfirmacion().hide();

        String outcome = null;
        String accion = JSFUtils.resolveExpressionAsString("#{viewScope.headerAction}");
        switch (accion) {
        case OUTCOME_MODIFICAR_CON_CAMBIOS_FINANCIEROS:
        case OUTCOME_FINALIZAR_TAREA:
            outcome = aceptarFinalizarTareaAction();
            break;
        case OUTCOME_MAS_INFORMACION:
        case OUTCOME_REQUIERE_AJUSTES:
        case OUTCOME_CANCELAR_FORMALIZACION_ENMIENDA:
            outcome = aceptarMasInformacionAction();
            break;
        }

        return outcome;
    }

    /**
     * Cancela el popup de confirmación.
     * Cierra el popup de confirmación.
     */
    public void cancelarPopupConfirmacionActionListener(ActionEvent actionEvent) {
        LOGGER.info("[FormalizacionEnmiendas] Cancela popup de confirmacion.");
        getPopupConfirmacion().hide();
    }
    
    /**
     * Finaliza la ejecución de una tarea. Invoca métodos que realizan validaciones y/o acciones dependiendo de la tarea.
     * Si temrina OK, entonces se invoca el outcome FINALIZARTAREA para que el proceso continúe.
     * Es responsabilidad de cada tarea el realizar sus validaciones y mostrar los mensajes necesarios.
     *
     * @return outcome FINALIZARTAREA o null si ocurrieron errores.
     */
    private String aceptarFinalizarTareaAction() {
        LOGGER.warning("[FormalizacionEnmiendas] Inicia confirmacion de Finalizar tarea");

        boolean finalizaOK = true;

        //Por cada una de las tareas se realizarán sus respectivas validaciones y acciones.
        switch (formalizacionBean.getCodigoTarea()) {
        case FenixConstants.PA15_SOLICITAR_FORMALIZACION_ENMIENDA:
            finalizaOK = finalizarTareaCargandoDocumentosOnBase();
            break;
        case FenixConstants.PA15_ANALIZAR_SOLICITUD_ENMIENTA:
            finalizaOK = finalizarTareaCargandoDocumentosOnBase();
            break;
        case FenixConstants.PA15_VALIDAR_CONDICIONES_FINANCIERAS:
            //La forma de validar condiciones financieras no tiene validaciones/acciones adicionales
            finalizaOK = true;
            break;
        case FenixConstants.PA15_REVISAR_DOCUMENTACION_CLIENTE:
            //Esta forma no tiene validaciones/acciones adicionales para finalizar tarea
            finalizaOK = true;
            break;
        case FenixConstants.PA15_AJUSTAR_BORRADOR_ENMIENDA:
            finalizaOK = finalizarTareaCargandoDocumentosOnBase();
            break;
        case FenixConstants.PA15_DOCUMENTAR_FORMALIZACION_ENMIENDA:
            finalizaOK = finalizarTareaCargandoDocumentosOnBase();
            break;
        case FenixConstants.PA15_CUSTODIAR_DOCUMENTACION_SOPORTE:
            finalizaOK = finalizarCustodiaDocumentacion();
            break;
        case FenixConstants.PA15_REGISTRAR_CAMBIOS:
            //Esta forma no tiene validaciones/acciones adicionales para finalizar tarea
            finalizaOK = true;
            break;
        case FenixConstants.PA15_VALIDAR_REGISTRO_CAMBIOS:
            //Esta forma no tiene validaciones/acciones adicionales para finalizar tarea
            finalizaOK = true;
            break;
        default:
            LOGGER.warning("[FormalizacionEnmiendas] Código de tarea no encontrado");
            finalizaOK = false;
            String strErrorMsg = ADFUtils.getStringFromBundle("TAREA_NO_ENCONTRADA_ERROR", BUNDLE);
            JSFUtils.addFacesErrorMessage(strErrorMsg);
            break;
        }

        if (finalizaOK) {
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        }

        return null;
    }

    /**
     * Finaliza las tareas cargando los documentos a OnBase.
     *
     * @return Siempre retorna {@code true}.
     */
    private boolean finalizarTareaCargandoDocumentosOnBase() {
        super.cargarDocumentosOnBase();

        return true;
    }

    /**
     * Finaliza acciones para custodiar documentacion.
     *
     * @return
     */
    private boolean finalizarCustodiaDocumentacion() {

        //recuperar el ID de CONTRATO
        BindingContainer bindings = super.getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("asegurarExistenciaContratoProceso");
        operationBinding.getParamsMap().put("idOperacion", formalizacionBean.getIdOperacion());
        operationBinding.getParamsMap().put("instanciaProceso", formalizacionBean.getInstanciaProceso());
        Long idContrato = (Long) operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.info("Errores al obtener contrato: " + operationBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            return false;
        }

        //actualizar la tabla CONTRATO (NUMERO_CUSTODIA)
        operationBinding = bindings.getOperationBinding("actualizarNumeroCustodia");
        operationBinding.getParamsMap().put("id", idContrato);
        operationBinding.getParamsMap().put("numeroCustodia", formalizacionBean.getNumCustodia());
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.info("Errores al actualizarNumeroCustodia: " + operationBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            return false;
        }

        return true;
    }

    /**
     * Invoca el outcome MASINFORMACION para que el proceso continúe.
     * Es responsabilidad de cada tarea el realizar sus acciones/vaidaciones adicionales y mostrar los mensajes necesarios.
     *
     * @return outcome MASINFORMACION.
     */
    private String aceptarMasInformacionAction() {
        LOGGER.warning("[FormalizacionEnmiendas] Acepta confirmacion de solicitar Mas Info");

        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    /**
     * Método genérico para la validación de documentos de la tarea actual.
     * Invoca método del bean padre quien se encarga de mostrar errores si los hay.
     * 
     * @param accion Acción a la cual está asociada el documento o null si no se asocia a alguna acción sino a la tarea como tal.
     * @return {@code true} Si la tarea actual cumple con sus documentos obligatorios, {@code false} en caso contrario.
     */
    private boolean validarDocumentosTarea(String accion){
        LOGGER.info("[FormalizacionEnmiendas] Invocando validación documentos. InstanciaProceso = {0}", formalizacionBean.getInstanciaProceso());
        return super.validarDocumentosTarea(formalizacionBean.getIdOperacion(), formalizacionBean.getCodigoTarea(), accion);
    }
    
    /**
     * [KB: 15525]
     * Verifica que exista un documento adjunto en la tarea actual que sea del tipo Borrador contrato de Enmienda.
     */
    private boolean validarBorradorContratoEnmiendaAdjunto(){
        LOGGER.info("[FormalizacionEnmiendas] Verificando que exista un adjunto tipo Borrador contrato de Enmienda.");
        boolean valido = true;
        
        BindingContainer bindings = super.getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("existeDocumentoAdjuntoConCodigoExterno");
        operationBinding.getParamsMap().put("codExterno", PA15Constants.COD_EXTERNO_DOC_BORRADOR_CONTRATO_ENM);
        valido = (Boolean) operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.info("Errores al verificar existencia del contrato: " + operationBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            return false;
        }
        
        if (!valido) {
            String msgError = ADFUtils.getStringFromBundle("BORRADOR_CONTRATO_ENMIENDA_DOC_REQUERIDO", BUNDLE);
            LOGGER.info("[FormalizacionEnmiendas] validarBorradorContratoEnmiendaAdjunto incumplido: " + msgError);
            JSFUtils.addFacesErrorMessage(msgError);
        }
        
        return valido;
    }

    /**
     * Verifica que se haya ingresado el comentario necesaro para poder solicitar mas info.
     *
     * @return {@code true} si y solo si las validaciones están OK {@code false} en caso contrario.
     */
    private boolean validarComentarioPresente() {
        boolean valido = true;
        //Para este caso se hace uso de la nueva forma de validar documentos
        valido =
            super.validateComentario(formalizacionBean.getIdOperacion(),
                                     String.valueOf(formalizacionBean.getCodigoTarea()), super.getInstanciaTarea());

        if (!valido) {
            String msgError = ADFUtils.getStringFromBundle("COMENTARIO_PARA_CONTINUAR_ERROR", BUNDLE);
            LOGGER.info("[FormalizacionEnmiendas] validarMasInfoAnalizarSolicitud incumplida: " + msgError);
            JSFUtils.addFacesErrorMessage(msgError);
        }

        return valido;
    }

    /**
     * Elimina los mensajes del contexto.
     */
    private void removerFacesMessages() {
        FacesContext context = FacesContext.getCurrentInstance();
        for (Iterator<FacesMessage> iterator = context.getMessages(); iterator.hasNext();) {
            iterator.next();
            // Elimina el mensaje
            iterator.remove();
        }
    }
}
