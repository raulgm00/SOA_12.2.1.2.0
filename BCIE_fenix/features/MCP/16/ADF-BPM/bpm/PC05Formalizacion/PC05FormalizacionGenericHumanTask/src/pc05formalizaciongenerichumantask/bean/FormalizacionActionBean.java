package pc05formalizaciongenerichumantask.bean;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.util.ResourceBundle;

import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.model.AutoSuggestUIHints;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;

import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.common.view.beans.FenixActionBean.OperationExecuteException;
import org.bcie.lineacreditomo.PropagarFuenteLCResponseType;
import org.bcie.matriztccbo.Validacion;
import org.bcie.matriztccmo.ValidarTCCResponseType;

import org.bcie.resultbo.Resultado;

import org.w3c.dom.NodeList;

import pc05formalizaciongenerichumantask.constants.BundleConstants;
import pc05formalizaciongenerichumantask.constants.ConRecursoExternoEnum;
import pc05formalizaciongenerichumantask.constants.FormalizacionConstants;
import pc05formalizaciongenerichumantask.constants.TareaFormalizacionEnum;

import pc05formalizaciongenerichumantask.util.DateTimeUtil;
import pc05formalizaciongenerichumantask.util.NumbersUtil;


public class FormalizacionActionBean extends FenixActionBean implements Serializable {
    @SuppressWarnings("compatibility:2336697215917996322")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    private static final String BUNDLE = "pc05formalizaciongenerichumantask.PC05FormalizacionGenericHumanTaskBundle";
    private static final Integer ID_TCC_TERMINO_T203 = 12;
    private static final Integer ID_TCC_TERMINO_T603 = 30;
    private static final Integer ID_TCC_TERMINO_T604 = 31;
    private static final Integer ID_TCA_ESTADO_TCC_FORMALIZADA = 15;
    private static final Integer ID_TCA_TERMINO_ORDEN_INICIO = 39;
    private static final String CLASS_NAME = FormalizacionActionBean.class.getName();


    //variables para los popup´s
    private transient RichPopup popupConfirmarFinalizarTarea;
    private transient RichPopup popupAgregarFuente;
    private transient RichPopup popupEliminarFuente;
    private transient RichPopup popupEliminarFuentesAgregadas;
    private transient RichPopup popupAgregarModificarFuente; // Usado en pantalla Asignación de recursos
    private transient RichPopup popupConfirmarMasInformacion;

    // Inicializacion de componentes visuales en pantallas
    private transient RichOutputText otInitForm; // Inicializa radio button Si/No en pantalla Definir recursos externos y condiciones
    private transient RichInputText montoEscrituradoUIC;

    private RichButton verDocumentoButtonUIC;
    
   //Inicialicion de lista de advertencia
   //jenamorado15/07/2021
   private List<String> listaAdvertencias = new ArrayList<String>(); 
   
    //Set de lista de advertencia
    //jenamorado15/07/2021
    public void setListaAdvertencias(List<String> listaAdvertencias) {
        this.listaAdvertencias = listaAdvertencias;
    }
    //Get de lista de advertencia
    //jenamorado15/07/2021
    public List<String> getListaAdvertencias() {
        return listaAdvertencias;
    }
   
    public FormalizacionActionBean() {

        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void setPopupConfirmarFinalizarTarea(RichPopup popupConfirmarFinalizarTarea) {
        this.popupConfirmarFinalizarTarea = popupConfirmarFinalizarTarea;
    }

    public RichPopup getPopupConfirmarFinalizarTarea() {
        return popupConfirmarFinalizarTarea;
    }

    public void setPopupAgregarFuente(RichPopup popupAgregarFuente) {
        this.popupAgregarFuente = popupAgregarFuente;
    }

    public RichPopup getPopupAgregarFuente() {
        return popupAgregarFuente;
    }

    public void setPopupEliminarFuente(RichPopup popupEliminarFuente) {
        this.popupEliminarFuente = popupEliminarFuente;
    }

    public RichPopup getPopupEliminarFuente() {
        return popupEliminarFuente;
    }

    public void setPopupEliminarFuentesAgregadas(RichPopup popupEliminarFuentesAgregadas) {
        this.popupEliminarFuentesAgregadas = popupEliminarFuentesAgregadas;
    }

    public RichPopup getPopupEliminarFuentesAgregadas() {
        return popupEliminarFuentesAgregadas;
    }

    public void setPopupAgregarModificarFuente(RichPopup popupAgregarModificarFuente) {
        this.popupAgregarModificarFuente = popupAgregarModificarFuente;
    }

    public RichPopup getPopupAgregarModificarFuente() {
        return popupAgregarModificarFuente;
    }


    /*** START Pantalla solicitar elaboración de documentación contractual ***/
    /*  @Source: Action - PC05CumplimientoCondicionesFinancieras (FINALIZAR_TAREA)
     *  @Source: SWITCH - PC05_SOLICITAR_ELABORACION_DOCUMENTACION (FINALIZAR_TAREA)
     *  @Source: SWITCH - PC05_CUMPLIR_CONDICIONES_FINANCIERAS (FINALIZAR_TAREA)
     *  NOTA: De rastreo de ejecución
     *  @developer: RUGM kruger
     * */
    public String invokeFinalizarTarea() {
        //mostrar Popup de confirmacion para finalizar la tarea
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarFinalizarTarea().show(popupHints);
        return null;
    }

    /*  @Source: Action - PC05CumplimientoCondicionesFinancieras (MAS_INFORMACION)
     *  NOTA: De rastreo de ejecución
     *  @developer: RUGM kruger
     * */
    public String invokeMasInformacion() {
        //mostrar Popup de confirmacion para finalizar la tarea
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarMasInformacion().show(popupHints);
        return null;
    }

    public String aceptarFinalizarTarea() {
        // Invocar método para cargar documentos en onBase
        cargarDocumentosOnBase();

        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    /**
     * Aceptar finalizar tarea Cumplmiento de Condiciones Financieras.
     * @return action
     */
    public String aceptarFinalizarTareaCCF() {
        final String METHOD_NAME = "aceptarFinalizarTareaCCF";

        logger.entering(CLASS_NAME, METHOD_NAME);

        BindingContainer bindings = null;
        OperationBinding opValidarJustificacion = null;
        String idOperacion = null;
        String errorMessage = null;
        InvokeActionBean invokeActionBean = null;
        Long justification = null;
        String returnAction = null;

        bindings = ADFUtils.getBindingContainer();
        opValidarJustificacion = bindings.getOperationBinding("validarJustificacion");
        idOperacion =
            JSFUtils.resolveExpression("#{bindings.CodigoOperacion.inputValue}") != null ?
            JSFUtils.resolveExpression("#{bindings.CodigoOperacion.inputValue}").toString() : null;

        if (idOperacion != null) {
            if (opValidarJustificacion != null) {
                opValidarJustificacion.getParamsMap().put("idOperacion", idOperacion);
                opValidarJustificacion.execute();

                if (!opValidarJustificacion.getErrors().isEmpty()) {
                    for (Object error : opValidarJustificacion.getErrors()) {
                        logger.severe(error.toString());
                    }
                } else {
                    justification =
                        opValidarJustificacion.getResult() != null ? (Long) opValidarJustificacion.getResult() : null;
                }

            } else {
                errorMessage = "Error: Al validar la justificación.";
            }
        } else {
            errorMessage = "Error: Operación Invalida.";
        }

        if (errorMessage == null) {

            if (null != justification && justification > 0) {
                // Invocar método para cargar documentos en onBase
                cargarDocumentosOnBase();
                invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                returnAction = invokeActionBean.invokeOperation();
            } else {
                JSFUtils.addFacesErrorMessage("Debe ingresar una justificación para finalizar.");
            }

        } else {
            JSFUtils.addFacesErrorMessage(errorMessage);
            return null;
        }

        logger.exiting(CLASS_NAME, METHOD_NAME);
        return returnAction;
    }

    /**
     * validar Mas informacion tarea Cumplmiento de Condiciones Financieras.
     * @return action
     */
    public String validarMasInformacionCCF() {
        final String METHOD_NAME = "validarMasInformacionCCF";

        logger.entering(CLASS_NAME, METHOD_NAME);

        BindingContainer bindings = null;
        OperationBinding opValidarComentario = null;
        String idOperacion = null;
        String idTarea = null;
        oracle.jbo.domain.Number operacion = null;
        oracle.jbo.domain.Number tarea = null;
        String errorMessage = null;
        InvokeActionBean invokeActionBean = null;
        Long comentario = null;
        String returnAction = null;

        bindings = ADFUtils.getBindingContainer();
        opValidarComentario = bindings.getOperationBinding("validarComentario");
        idOperacion =
            JSFUtils.resolveExpression("#{bindings.CodigoOperacion.inputValue}") != null ?
            JSFUtils.resolveExpression("#{bindings.CodigoOperacion.inputValue}").toString() : null;

        idTarea =
            JSFUtils.resolveExpression("#{bindings.CodigoTarea.inputValue}") != null ?
            JSFUtils.resolveExpression("#{bindings.CodigoTarea.inputValue}").toString() : null;

        try {
            operacion = new Number(idOperacion);
            tarea = new Number(idTarea);
        } catch (SQLException e) {
            errorMessage = e.getMessage();
        }


        if (idOperacion != null && idTarea != null) {
            if (opValidarComentario != null) {
                opValidarComentario.getParamsMap().put("idOperacion", operacion);
                opValidarComentario.getParamsMap().put("idTarea", tarea);
                logger.severe("idOperacion: " + operacion + " - idTarea: " + tarea);
                opValidarComentario.execute();

                if (!opValidarComentario.getErrors().isEmpty()) {
                    for (Object error : opValidarComentario.getErrors()) {
                        logger.severe(error.toString());
                    }
                } else {
                    comentario =
                        opValidarComentario.getResult() != null ? (Long) opValidarComentario.getResult() : null;
                }

            } else {
                errorMessage = "Error: Operación Invalida.";
            }
        } else {
            errorMessage = "Error: Parametros Invalidos.";
        }

        if (errorMessage == null) {

            if (null != comentario && comentario > 0) {
                // Invocar método para cargar documentos en onBase
                cargarDocumentosOnBase();
                invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                returnAction = invokeActionBean.invokeOperation();
            } else {
                JSFUtils.addFacesErrorMessage("Debe ingresar un Comentario para solicitar Mas Informacion.");
            }

        } else {
            JSFUtils.addFacesErrorMessage(errorMessage);
            return null;
        }

        logger.exiting(CLASS_NAME, METHOD_NAME);
        return returnAction;
    }

    public void cancelarFinalizarTarea(ActionEvent ev) {
        popupConfirmarFinalizarTarea.hide();
    }

    /*** END Pantalla solicitar elaboración de documentación contractual ***/


    /*** START Pantalla Definir recursos externos y condiciones ***/

    public void agregarFuenteActionListener(ActionEvent actionEvent) {
        // Crea row de captura y abre popup de Agregar fuente en pantalla Definir recursos externos y condiciones
        logger.log(ADFLogger.WARNING, "Inside agregarFuenteActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        RichPopup.PopupHints popupHints = null;

        // Creamos row de captura
        operationBinding = bindings.getOperationBinding("crearRowFuente");

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        getPopupAgregarFuente().show(popupHints);
    }

    public void eliminarFuenteActionListener(ActionEvent actionEvent) {
        // Abre popup de Confirmar eliminar fuente en pantalla Definir recursos externos y condiciones
        logger.log(ADFLogger.WARNING, "Inside eliminarFuenteActionListener: " + actionEvent.getComponent().getId());

        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupEliminarFuente().show(popupHints);
    }

    public void aceptarAgregarFuenteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside aceptarAgregarFuenteActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        FormalizacionBean formalizacionBean = null;
        DCIteratorBinding voAgregarFuente = null;
        OperationBinding operationBinding = null;
        Long idOperacion = null;
        String instanciaProceso = null;
        String codigo = null;
        String nombre = null;
        BigDecimal porcentaje = BigDecimal.ZERO;

        // Asignación de variables
        formalizacionBean = (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        voAgregarFuente = ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarFuenteVOIterator");
        idOperacion = Long.valueOf(formalizacionBean.getIdOperacion());
        instanciaProceso = (String) JSFUtils.resolveExpression("#{bindings.instanceId.inputValue}");
        codigo = (String) voAgregarFuente.getRowAtRangeIndex(0).getAttribute("Codigo");
        nombre = (String) voAgregarFuente.getRowAtRangeIndex(0).getAttribute("Nombre");
        porcentaje = (BigDecimal) voAgregarFuente.getRowAtRangeIndex(0).getAttribute("Porcentaje");

        // Agregamos fuente a la BD
        operationBinding = bindings.getOperationBinding("agregarFuente");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
        operationBinding.getParamsMap().put("codigo", codigo);
        operationBinding.getParamsMap().put("nombre", nombre);
        operationBinding.getParamsMap().put("porcentaje", (porcentaje != null) ? porcentaje : null);

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Actualizamos tabla de Fuentes externas
        actualizarFuentesExternas();

        // Cerramos popup de Agregar fuente en pantalla Definir recursos externos y condiciones
        getPopupAgregarFuente().hide();
    }

    public void cancelarAgregarFuenteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,
                   "Inside cancelarAgregarFuenteActionListener: " + actionEvent.getComponent().getId());

        // Cerramos popup de Agregar fuente en pantalla Definir recursos externos y condiciones
        getPopupAgregarFuente().hide();
    }

    public void confirmEliminarFuenteActionListener(ActionEvent actionEvent) {
        logger.warning("Dentro confirmEliminarFuenteActionListener");
        // Eliminamos de BD la fuente seleccionada
        this.eliminarFuente();

        // Actualizamos tabla con Fuentes externas
        actualizarFuentesExternas();

        // Cerramos popup de confirmar Eliminar fuente
        getPopupEliminarFuente().hide();
        logger.warning("Fuera confirmEliminarFuenteActionListener");
    }

    public String aceptarFinalizarTareaRecExternosAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarFinalizarTareaRecExternosAction.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        String returnAction = null;

        actualizarPayloadRecursosExternos();

        FormalizacionBean formalizacionBean = getFormalizacionManagedBean();
        ConRecursoExternoEnum cuentaRecusoExtEnum =
            ConRecursoExternoEnum.lookup(formalizacionBean.getCuentaConRecExternos());

        switch (cuentaRecusoExtEnum) {
        case SI:
            //RN01 En caso de que el Analista DAECI indique que la operación requiere de recursos externos se debe de
            //contar con el 100% de los fondos asignados para terminar la tarea.
            //          Se comenta la validacion por ajustes solicitado en "documento de ajustes"
            //          Incidendia relacionada : UAT- FASE III-6603 - No se puede asignar un porcentaje total menor a 100%
            //            if(!validarFondosAsignados()){
            //                //cierra popup de confirmacion finalizar tarea
            //                getPopupConfirmarFinalizarTarea().hide();
            //                //Mostrar mensaje de error
            //                JSFUtils.addFacesErrorMessage("Debe completar el 100% de los fondos asignados.");
            //            }else{
            returnAction = finalizarDefinirRecursosExternos();
            //            }
            break;
        case NO:
            //Ya no se no se toma en cuanta la RN01 y la EX02
            returnAction = finalizarDefinirRecursosExternos();
            break;

        case GESTION:
            // Se valida que se haya ingresado un comentario
            if (validateComentario(Long.valueOf(formalizacionBean.getIdOperacion()), formalizacionBean.getIdTarea(),
                                   getInstanciaTarea())) {
                // Se finaliza la tarea
                returnAction = finalizarDefinirRecursosExternos();
            } else {
                // Se notifica que se debe ingresar un comentario
                notificarValidacion(TareaFormalizacionEnum.DEFINIR_RECURSOS_EXTERNOS,
                                    BundleConstants.COMENTARIO_PARA_CONTINUAR);
            }
            break;

        default:
            // Se notifica que debe seleccionar el campo requerido
            notificarValidacion(TareaFormalizacionEnum.DEFINIR_RECURSOS_EXTERNOS, "INDICAR_SI_CUENTA_CON_RECURSOS_EXT");
            break;
        }

        return returnAction;
    }

    private void actualizarFuentesExternas() {
        logger.warning("Dentro actualizarFuentesExternas");
        DCIteratorBinding voFuentesExternas = null;
        try {
            voFuentesExternas = ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasVOIterator");
            voFuentesExternas.executeQuery();
        } catch (Exception e) {
            logger.severe("Error al recuperar FuentesExternasVOIterator : " + e);
        }
        logger.warning("Fuera actualizarFuentesExternas");
    }

    public void recursosExternosValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "Inside recursosExternosValueChangeListener.");
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        DCIteratorBinding voFuentesExternas =
            ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasVOIterator");
        RichPopup.PopupHints popupHints = null;

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // update model values

        // Si eligió la opción de NO y existen fuentes agregadas, advertimos al usuario que serán eliminadas
        if ((formalizacionBean.getCuentaConRecExternos().intValue() == 2) &&
            (voFuentesExternas.getEstimatedRowCount() > 0)) {

            // Abrimos popup de confirmar Eliminar fuentes agregadas
            popupHints = new RichPopup.PopupHints();
            getPopupEliminarFuentesAgregadas().show(popupHints);
        }

        //actualizarPayloadRecursosExternos(); Actualizacion se realiza al finalizar tarea
    }

    /**
     * Actualiza banderas del Payload para la tarea de Definir Recursos Externos
     */
    public void actualizarPayloadRecursosExternos() {

        logger.warning("Inicia actualizarPayloadRecursosExternos");

        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");

        //Se aplica modificacion conforme a la incidencia FNXII-4252 y Documento de Ajustes Fase III Rev 1011
        if (formalizacionBean.getCuentaConRecExternos() != null) {

            if (formalizacionBean.getCuentaConRecExternos().intValue() == 1 ||
                formalizacionBean.getCuentaConRecExternos().intValue() == 3) {

                logger.warning("Se asigna bandera al Payload para indicar que se requiere de Fuentes Externas");

                //Se setea Payload conforme a lo indicado en la incidencia
                ADFUtils.setBoundAttributeValue("requiereFuenteExterna", true);
            } else {
                logger.warning("Se asigna bandera al Payload para indicar que No se requiere de Fuentes Externas");

                //Se setea Payload conforme a lo indicado en la incidencia
                ADFUtils.setBoundAttributeValue("requiereFuenteExterna", false);
            }
        } else {
            logger.warning("No se pudo obtener un valor seleccionado");
        }

        logger.warning("Finaliza actualizarPayloadRecursosExternos");
    }

    public void confirmEliminarFuentesAgregadasActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,
                   "Inside confirmEliminarFuentesAgregadasActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voFuentesExternas =
            ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasVOIterator");
        OperationBinding operationBinding = null;
        List<Long> idsFuente = new ArrayList<Long>();
        Row[] rows = null;

        // Asignamos arreglo con ids de fuentes agregadas
        voFuentesExternas.setRangeSize(-1);
        rows = voFuentesExternas.getAllRowsInRange();

        for (Row row : rows) {
            idsFuente.add((Long) row.getAttribute("Id"));
        }

        // Eliminamos de BD todas las fuentes agregadas (borrado lógico)
        operationBinding = bindings.getOperationBinding("eliminarFuentes");
        operationBinding.getParamsMap().put("idsFuente", idsFuente);

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Actualizamos tabla con Fuentes externas
        actualizarFuentesExternas();

        // Cerramos popup de confirmar Eliminar fuentes agregadas
        getPopupEliminarFuentesAgregadas().hide();
    }

    public void cancelEliminarFuentesAgregadasActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside cancelEliminarFuenteActionListener: " + actionEvent.getComponent().getId());

        // Cerramos popup de confirmar Eliminar fuentes agregadas
        getPopupEliminarFuentesAgregadas().hide();
    }

    /*** END Pantalla Definir recursos externos y condiciones ***/


    /*** START Pantalla Elaborar borrador de contrato 
     * @Overview: metodo comun para los botones de finalizar tarea
     * @Sources: (1)
     * 
     * ***/
    public String aceptarFinalizarTareaBorradorContratoAction() {
        
        logger.warning("Inside aceptarFinalizarTareaBorradorContratoAction.");
        FormalizacionBean formalizacionBean = (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;

        /**@Overview: US: 2012; Se agrega La validación de comantario para poder finalizar la tarea solo para cuando esRetorno
         * @Premisas: Validar si es retonor de la oepración (*COFI) por lo tanto debe contener un comentario de justificación hacia COFI 
         * @Nota: se deja las lineas cpmentadas de la sección (1) con el fin de contar con ellas si se desea validar algun docmuento en la tarea
         * @Develper: RUGM
         * @Date: 01/06/2021
         * */
     
        returnAction = logicaFinalizarTareaBorradorContratoAction(formalizacionBean.getEsRetorno(),formalizacionBean.getAccionBoton(),Long.valueOf(formalizacionBean.getIdOperacion()),formalizacionBean.getIdTarea(),getInstanciaTarea());
        logger.warning("returnAction: " + returnAction );
        logger.warning("Outside aceptarFinalizarTareaBorradorContratoAction." );
        return returnAction;
    }
    
    
    /**@Overview: US: 2012; Se agrega método de validación d elogica para la ejecución de finalzar la tarea en retorpectiva de la acción
     * @Accion: 1) ENVIAR COFI, 2)ENVIAR RESPONSABLE DE OPERACION, 3)FINALIZR, 4)SOLCIITAR AJUSTES POR FUENTES EXTERNAS
     * @Develper: RUGM
     * @Date: 01/06/2021
     * */
    
    public String logicaFinalizarTareaBorradorContratoAction(Boolean esRetorno, String accion, Long idOperacion, String idTcaTareaBpm, String IdInstanciaTarea){
        
        logger.warning("Inside logicaFinalizarTareaBorradorContratoAction.");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        logger.warning("Accion boton: " + accion);
        
        // Enviar notificación de comentario cuando se requeira ir a Enviar aCOFI y sea retorno
        if(esRetorno && ( accion.equalsIgnoreCase("EnviarCofi")) ){
            
            if (validateComentario(idOperacion, idTcaTareaBpm,IdInstanciaTarea) ){
                // RN: El sistema verifica que se cuente con el documento de tipo "Borrador de contrato".
                //if(validateDocumento(Long.valueOf(formalizacionBean.getIdOperacion()), Integer.valueOf(FenixConstants.DOCUMENTO_BORRADOR_DE_CONTRATO))) {

                if (validarComisionesDispensadasPagadas() && ( validarTccElaborarBorradorContrato(idOperacion) )) {
                    // Invocar método para cargar documentos en onBase
                    cargarDocumentosOnBase();
                    invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                    returnAction = invokeActionBean.invokeOperation();
                }
                //}
                //else {
                //    JSFUtils.addFacesErrorMessage("Debe de adjuntar el borrador de contrato digitalizado para finalizar la tarea.");
                //}
                // Cerramos popup de Confirmar Finalizar Tarea
                getPopupConfirmarFinalizarTarea().hide();
            } else {
                JSFUtils.addFacesErrorMessage("Se tiene que registrar al menos un comentario para poder avanzar.");
                // Cerramos popup de Confirmar Finalizar Tarea
                getPopupConfirmarFinalizarTarea().hide();
            }
            
        }else{
            
            /* Overview: Complemento de codigo para evitar la validación del comentario para cuandso no es Retorno de proceso
             * Nota: se deja las lineas copmentadas de la sección (1) con el fin de contar con ellas si se desea validar algun docmuento en la tarea
             * Develper: RUGM
             * Date: 01/06/2021
             * */
            // RN: El sistema verifica que se cuente con el documento de tipo "Borrador de contrato".
            //if(validateDocumento(Long.valueOf(formalizacionBean.getIdOperacion()), Integer.valueOf(FenixConstants.DOCUMENTO_BORRADOR_DE_CONTRATO))) {

            if (validarComisionesDispensadasPagadas() && ( validarTccElaborarBorradorContrato(idOperacion) )) {
                // Invocar método para cargar documentos en onBase
                cargarDocumentosOnBase();
                invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                returnAction = invokeActionBean.invokeOperation();
            }
            //}
            //else {
            //    JSFUtils.addFacesErrorMessage("Debe de adjuntar el borrador de contrato digitalizado para finalizar la tarea.");
            //}
            // Cerramos popup de Confirmar Finalizar Tarea
            getPopupConfirmarFinalizarTarea().hide();
            
        }        
        logger.warning("Outside logicaFinalizarTareaBorradorContratoAction.");
        return returnAction;
    
    }
        public String aceptarSolicitarAjustesBorradorContratoAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarSolicitarAjustesBorradorContratoAction.");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");

        if (validateComentario(Long.valueOf(formalizacionBean.getIdOperacion()), formalizacionBean.getIdTarea(),
                               getInstanciaTarea())) {

            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();

            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();
        } else {
            JSFUtils.addFacesErrorMessage("Se tiene que registrar al menos un comentario para poder avanzar.");

            // Cerramos popup de Confirmar Finalizar Tarea
            getPopupConfirmarFinalizarTarea().hide();
        }
        return returnAction;
    }

    public Boolean validarComisionesDispensadasPagadas() {
        Boolean esEjecucionExitosa = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarComisionesDispensadasPagadas");
        operationBinding.getParamsMap().put("estadoDispensado", FenixConstants.ESTADO_COMISION_DISPENSADO);
        operationBinding.getParamsMap().put("estadoPagado", FenixConstants.ESTADO_COMISION_PAGADO);
        operationBinding.getParamsMap().put("momentoActual", FenixConstants.MOMENTO_COBRO_FORMALIZACION);

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
        } else if ((operationBinding.getResult() != null) && ((Boolean) operationBinding.getResult()))
            esEjecucionExitosa = Boolean.TRUE;
        else
            JSFUtils.addFacesErrorMessage("Algunas de las comisiones de la operación deben de tener el estado pagada o dispensada.");

        return esEjecucionExitosa;
    }

    private Boolean validarTccElaborarBorradorContrato(Long idOperacion) {
        logger.log(ADFLogger.WARNING, "Inside validarTccElaborarBorradorContrato.");
        Boolean esEjecucionExitosa = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        // Ejecutamos método que verifica que la combinación de Estados con Sub-estados coincida con el diagrama de estados.
        // Lo anterior, debido a que esta es la última pantalla donde se pueden editar los TCC.
        operationBinding = bindings.getOperationBinding("validarTccElaborarBorradorContrato");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);


        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        } else if ((operationBinding.getResult() != null) && ((Boolean) operationBinding.getResult()))
            esEjecucionExitosa = Boolean.TRUE; // sub-estados válidos.
        else
            JSFUtils.addFacesErrorMessage("Uno o más sub-estados de TCC son inválidos, favor de revisar."); // getResult() es null o false

        return esEjecucionExitosa;
    }

    /*** END Pantalla Elaborar borrador de contrato ***/


    /*** START Pantalla Negociar documentación contractual con el cliente ***/
    public String aceptarFinalizarTareaNegociarDocumentacionAction() {

        logger.log(ADFLogger.WARNING, "Inside aceptarFinalizarTareaNegociarDocumentacionAction.");
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voDeclaracionJuradaLaft = null;
        String noObjecion = null;
        String nombreNoObjecion = null;
        OperationBinding operationBinding = null;
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        HashMap<String, Boolean> validarCondicionesFormalizacion = null;
        Boolean esCondicionesFormalizar = Boolean.FALSE;
        Boolean esPlazoFormalizacion = Boolean.FALSE;
        //lista de advertencia
        //jenamorado 15/07/2021
        Integer codigoTarea = Integer.parseInt(formalizacionBean.getIdTarea());
        listaAdvertencias = new ArrayList<String>();
        // Consultamos la DJ para la operación, con el fin de obtener el estado de la "No objeción"
        operationBinding = bindings.getOperationBinding("consultarDeclaracionJurada");
        operationBinding.getParamsMap().put("idOperacion", formalizacionBean.getIdOperacion());

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            voDeclaracionJuradaLaft =
                ADFUtils.getDCBindingContainer().findIteratorBinding("DeclaracionJuradaLaftVOIterator");
            noObjecion = (String) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("NoObjecion");
            nombreNoObjecion = (String) voDeclaracionJuradaLaft.getRowAtRangeIndex(0).getAttribute("NombreNoObjecion");

            // RN1: Para poder finalizar la tarea es necesario que exista una "No objeción" vigente.
            // Nota: la regla anterior sólo aplica cuando el NombreEstadoNoObjecion sea diferente a "No aplica"
            if ((noObjecion != null) && (noObjecion.trim().equalsIgnoreCase("SI")) ||
                ((nombreNoObjecion != null) && (nombreNoObjecion.trim().equalsIgnoreCase("No Aplica")))) {

                validarCondicionesFormalizacion =
                    (HashMap<String, Boolean>) validarCondicionesFormalizacion(formalizacionBean.getOperacionId().longValue());

                if (validarCondicionesFormalizacion != null) {

                    esCondicionesFormalizar =
                        validarCondicionesFormalizacion.get("validaCondiciones") == null ? Boolean.FALSE :
                        validarCondicionesFormalizacion.get("validaCondiciones");
                    esPlazoFormalizacion =
                        validarCondicionesFormalizacion.get("validaFechaFormalizacion") == null ? Boolean.FALSE :
                        validarCondicionesFormalizacion.get("validaFechaFormalizacion");

                    // RN2: El sistema valida que las condiciones a formalizar no se han cumplido.
                    if (!esCondicionesFormalizar)
                        JSFUtils.addFacesErrorMessage("Para finalizar la tarea, las condiciones a formalizar de la operación deben cumplirse.");

                    // RN3: El sistema valida que el plazo de formalización se ha vencido.
                   /* if (!esPlazoFormalizacion){
                        //se comenta la siguente linea porque no deja terminar el proceso y se determino dejar el error como advertencia.
                        //JSFUtils.addFacesErrorMessage("No es posible finalizar la tarea el plazo de formalizar la operación se ha vencido.");
                        //se agrego else para que en la pantalla de negociarDocumentacionConCliente sea advertencia y en adjuntar contratoFirmado sea error
                        //jenamorado 15/07/2021
                        
                        if (FenixConstants.PC05_ADJUNTAR_CONTRATO_FIRMADO ==codigoTarea.toString() ){
                             JSFUtils.addFacesErrorMessage("No es posible finalizar la tarea el plazo de formalizar la operación se ha vencido.");
                        }
                        else{
                             listaAdvertencias.add("No es posible finalizar la tarea el plazo de formalizar la operación se ha vencido.");
                         }}*/

                    if (esCondicionesFormalizar && /* esPlazoFormalizacion &&*/
                        validarTccFinalizarNegociarDocumentacion(formalizacionBean.getOperacionId().longValue() ) ){
                        // Invocar método para cargar documentos en onBase
                        cargarDocumentosOnBase();

                        invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                        returnAction = invokeActionBean.invokeOperation();
                    }
                }
            } else {
                JSFUtils.addFacesErrorMessage("Para finalizar la tarea la operación debe de contar con una “No Objeción” vigente.");
            }
        }

        // Cerramos popup de Confirmar Finalizar Tarea
        getPopupConfirmarFinalizarTarea().hide();

        return returnAction;
    }
    /* Overview: Seleccion de boton Requiere Ajustes
     * Sources: PC05NegociarDocumentacionConCLiente
     * Nota: Rastreo de ejecución
     * Date: 01/06/2021
     * */
    public String aceptarSolicitarAjustesNegociarDocumentacionAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarSolicitarAjustesBorradorContratoAction.");
        FormalizacionBean formalizacionBean = (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;

        // RN: El sistema solicita los comentarios obligatoriamente.
        if (validateComentario(Long.valueOf(formalizacionBean.getIdOperacion()), formalizacionBean.getIdTarea(),getInstanciaTarea())) {
            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();
            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();
        } else {
            JSFUtils.addFacesErrorMessage("Se tiene que registrar al menos un comentario para poder avanzar.");
            // Cerramos popup de Confirmar Finalizar Tarea
            getPopupConfirmarFinalizarTarea().hide();
        }

        return returnAction;
    }

    private Boolean validarTccFinalizarNegociarDocumentacion(Long idOperacion) {
        logger.log(ADFLogger.WARNING, "Inside validarTccFinalizarNegociarDocumentacion.");
        Boolean esEjecucionExitosa = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        // Ejecutamos método que realiza RN05: Los elementos de la Matriz TCC que se encuentren en el estado
        // "Por validar" al finalizar la tarea deben pasar al estado "Validada".
        operationBinding = bindings.getOperationBinding("validarTccFinalizarNegociarDocumentacion");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        } else if ((operationBinding.getResult() != null) && ((Boolean) operationBinding.getResult()))
            esEjecucionExitosa = Boolean.TRUE; // TCCs actualizados exitosamente.
        else
            JSFUtils.addFacesErrorMessage("Error al actualizar elementos TCC."); // getResult() es null o false

        return esEjecucionExitosa;
    }

    /*** END Pantalla Negociar documentación contractual con el cliente ***/


    /*** START Pantalla Elabora CORE T&C ***/
    public String aceptarFinalizarTareaElaboraCoreTyCAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarFinalizarTareaElaboraCoreTyCAction.");
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;

        // RN: Se deberá haber asociado a la operación el documento de términos y condiciones digital.
        //if(validateDocumento(Long.valueOf(formalizacionBean.getIdOperacion()), Integer.valueOf(FenixConstants.DOCUMENTO_CORE_TYC))) {

        // Invocar método para cargar documentos en onBase
        cargarDocumentosOnBase();

        invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        returnAction = invokeActionBean.invokeOperation();
        //}
        //else {
        //    JSFUtils.addFacesErrorMessage("Debe adjuntar el documento de términos y condiciones.");

            // Cerramos popup de Confirmar Finalizar Tarea
        //    getPopupConfirmarFinalizarTarea().hide();
        //}

        return returnAction;
    }

    /*** END Pantalla Elabora CORE T&C ***/


    /*** START Pantalla Analizar documentación contractual ***/
    public String aceptarFinalizarTareaAnalizarDocumentacionAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarFinalizarTareaAnalizarDocumentacionAction.");
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;

        // RN: El sistema valida que no se ha adjuntado "Acta del CORE".
        //if(validateDocumento(Long.valueOf(formalizacionBean.getIdOperacion()), Integer.valueOf(FenixConstants.DOCUMENTO_ACUERDO_DEL_CORE))) {

        // Invocar método para cargar documentos en onBase
        cargarDocumentosOnBase();

        invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        returnAction = invokeActionBean.invokeOperation();
        //}
        //else {
        //    JSFUtils.addFacesErrorMessage("Debe de adjuntar el Acuerdo del CORE para finalizar la tarea.");

        //    Cerramos popup de Confirmar Finalizar Tarea
        //   getPopupConfirmarFinalizarTarea().hide();
        //}

        return returnAction;
    }

    public String aceptarSolicitarAjustesAnalizarDocumentacionAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarSolicitarAjustesAnalizarDocumentacionAction.");
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;

        // RN: El sistema solicita los comentarios obligatoriamente.
        if (validateComentario(Long.valueOf(formalizacionBean.getIdOperacion()), formalizacionBean.getIdTarea(),
                               getInstanciaTarea())) {

            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();

            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();
        } else {
            JSFUtils.addFacesErrorMessage("Se tiene que registrar al menos un comentario para poder avanzar.");
        }

        // Cerramos popup de Confirmar Finalizar Tarea
        getPopupConfirmarFinalizarTarea().hide();

        return returnAction;
    }

    /*** END Pantalla Analizar documentación contractual ***/

    public Boolean actualizarComisionesMontoFormalizado() {
        Boolean retorno = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("actualizarComisionMonto");
        operationBinding.getParamsMap().put("tipoMonto",
                                            FenixConstants.MONTO_FORMALIZADO); // Monto Aprobado Id = 3, según tabla TCA_TIPO_MONTO_OPERACION
        operationBinding.execute();
        if (operationBinding.getErrors().isEmpty()) {
            // Asignación para Monto aprobado
            retorno = (Boolean) operationBinding.getResult();
        }
        if (null == retorno || !retorno) {
            JSFUtils.addFacesErrorMessage("Error al actualizar los montos en las comisiones");
        }
        return retorno;
    }


    /*** START Pantalla Adjuntar contrato firmado ***/
    public String aceptarFinalizarTareaContratoFirmadoAction() {
        logger.warning("Inicia metodo aceptarFinalizarTareaContratoFirmadoAction.");
        FormalizacionBean formalizacionBean = (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        boolean condicionesValidas = Boolean.TRUE;
        Boolean solicitudContratacion = Boolean.FALSE;
        Boolean ordenInicio = Boolean.FALSE;
        BigDecimal montoObtenido = BigDecimal.ZERO;

        String strMontoEscriturado = null;
        if (JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}") != null) {
            strMontoEscriturado = JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}").toString();
            if (null != strMontoEscriturado) {
                if (strMontoEscriturado.length() > 0) {
                    montoObtenido = new BigDecimal(strMontoEscriturado);
                }
            }

        }

        // Cuando no se ingrese un monto formalizado, se cargara el monto aprovado
        if (!seIngresoMontoFormalizado()) {
            logger.log(ADFLogger.WARNING, "> > > SE TOMARA MONTO APROBADO EN LUGAR DEL MONTO ESCRITURADO");
            BigDecimal montoAprob = obtenerMontoAprobado();
            //montoAprob = BigDecimal.TEN;
            logger.log(ADFLogger.WARNING, "montoAprobado: " + montoAprob);
            ADFUtils.setBoundAttributeValue("MontoEscriturado", montoAprob);
            montoObtenido = montoAprob;
        }

        //No se validara la tcc 101 en caso de haberse adjuntado el
        //contrato firmado y la fecha de firma
        if (!contratoAdjuntadoConFirma())
            condicionesValidas = tcc101Valida();

        String keyError = null;
        if (condicionesValidas) {
            if (formalizacionBean.getSolicitudContratacion()) {
                logger.warning("Se omite validacion debido a que viene del proceso de implementacionPCT");
                //condicionesValidas=Boolean.TRUE;
                condicionesValidas = validarLotePresupuestado(montoObtenido);
            } else {
                // RN2: El monto escriturado no deberá exceder la diferencia del monto aprobado y el monto total de las formalizaciones ya ejecutadas.
                logger.warning("Entran en validar total formalizacion");
                condicionesValidas = totalFormalizacionesValidas();
            }
            logger.warning("condicionesValidas : " + condicionesValidas);
            if (condicionesValidas) {
                // RN1: La fecha de la firma no debe ser posterior al plazo máximo para formalizar, incluido en los términos y condiciones de la operación.

                boolean blnContinuar = false;
                Boolean blnValidacion = saveOrUpdateTerminos();
                logger.warning("blnValidacion : " + blnValidacion);
                if (blnValidacion != null && blnValidacion) {
                    blnValidacion =
                        actualizarFechaInicioTerminoTipoPlazo(formalizacionBean.getOperacionId().longValue());
                    if (blnValidacion != null && blnValidacion) {
                  
                        blnValidacion =
                            validarTccAdjuntarContratoFirmado(formalizacionBean.getOperacionId().longValue() );
                        if (blnValidacion != null && blnValidacion) {
                            blnValidacion = validarContratoFirmado();
                            if (blnValidacion != null && blnValidacion) {
                                blnContinuar = true;
                            } else {
                                logger.severe("Error al validar montos del contrato");
                            }
                        } else {
                            keyError = "ADJUNTAR_CONTRATO_FIRMADO_MSG_VAL_TCC_ESTADO_FORMALIZADA";
                        }
                    } else {
                        keyError = "ADJUNTAR_CONTRATO_FIRMADO_MSG_VAL_GUARDAR_ACTUALIZAR_TERMINO_TIPO_PLAZO";
                    }
                } else {
                    keyError = "ADJUNTAR_CONTRATO_FIRMADO_MSG_VAL_GUARDAR_ACTUALIZAR_TERMINOS";
                }

                if (blnContinuar) {
                    // Guardamos en la tabla Contrato las fechas y monto ingresados
                    guardarDatosEnContrato(formalizacionBean.getOperacionId().longValue(),
                                           formalizacionBean.getInstanciaProceso());
                    if (actualizarComisionesMontoFormalizado()) {

                        // Invocar método para cargar documentos en onBase
                        cargarDocumentosOnBase();
                        // Si eligió que la Fecha de vigencia es igual a fecha de firma, actualizamos flag en payload
                        if (formalizacionBean.getEsFechaVigenciaIgualFechaFirma())
                            actualizarFlagPayload("requiereFechaVigencia", false);
                        else
                            actualizarFlagPayload("requiereFechaVigencia", true);

                        try {
                            solicitudContratacion = (Boolean) ADFUtils.getBoundAttributeValue("solicitudContratacion");
                        } catch (Exception e) {
                            logger.warning("ERROR al recuperar el ATributo solicitudContratacion.", e);
                        }
                        logger.warning("solicitudContratacion : " + solicitudContratacion);
                        if (null != solicitudContratacion && solicitudContratacion) {
                            //Se hace busqueda del Termino T704 - Orden de inicio
                            ordenInicio = obtenerValorOrdenInicio();

                            if (null != ordenInicio) {
                                logger.warning("Asignando valor ordenInicio al Payload.");
                                actualizarPayloadElement("requiereLineaCredito", ordenInicio);
                            } else {
                                logger.warning("Orden de inicio es NULL.");
                                blnContinuar = Boolean.FALSE;
                            }
                        }

                        if (blnContinuar) {
                            logger.warning("Se invoca operacion FINALIZARTAREA de BPM");
                            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                            returnAction = invokeActionBean.invokeOperation();
                        }
                        //Agregamos la actualizacion
                        logger.log(ADFLogger.WARNING, "Inicia MODIFICACION DE MONTO ESCRITURADO EN AMPLIACION LINEA CREDITO: ");
                        BindingContainer bindings = ADFUtils.getBindingContainer();
                        OperationBinding operationBinding = null;         
                                         operationBinding = bindings.getOperationBinding("modificaMontoAmpliacion");
                        operationBinding.getParamsMap().put("idOperacion",Long.valueOf(formalizacionBean.getOperacionId().longValue()));
                        operationBinding.execute();
                        logger.log(ADFLogger.WARNING, "FIN MODIFICACION DE MONTO ESCRITURADO EN AMPLIACION LINEA CREDITO SI ESQUE FUE AMPLIACION ");
                    } else {
                        keyError = "ADJUNTAR_CONTRATO_FIRMADO_MSG_VAL_COMISIONES_MONTO";
                    }
                }
                
            } else {
                if (!formalizacionBean.getSolicitudContratacion()) {
                    keyError = "MSG02_DIFERENCIA_MONTO_TOTAL_FORMALIZACIONES";
                } else {
                    JSFUtils.addFacesErrorMessage("Error al validar el monto escriturado, excede el limite del monto presupuestado ");
                }
            }
        } else {
            logger.severe("Error al finalizar tarea, condiciones no validas");
            //Notificar validacion
            notificarValidacion(TareaFormalizacionEnum.ADJUNTAR_CONTRATO_FIRMADO,
                                BundleConstants.FECHA_VENCIMIENTO_MENOR);
        }

        if (keyError != null) {
            logger.severe("Error al finalizar tarea");
            JSFUtils.addFacesErrorMessage(getStringFromBundle(keyError));
        }

        // Cerramos popup de Confirmar Finalizar Tarea
        getPopupConfirmarFinalizarTarea().hide();
        logger.warning("termina metodo aceptarFinalizarTareaContratoFirmadoAction.");
        return returnAction;
    }

    public Boolean obtenerValorOrdenInicio() {
        logger.warning("Inicia metodo obtenerValorOrdenInicio.");
        Boolean valorOrdenInicio = Boolean.FALSE;
        Integer valorOrdenInicioInt = null;
        Map<String, Object> params = new HashMap<String, Object>();
        Long idOperacion = null;
        String idOperacionStr = null;
        Row row = null;
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");

        try {
            idOperacionStr = formalizacionBean.getIdOperacion();
        } catch (Exception e) {
            logger.warning("ERROR al recuperar IdOperacion de FormalizacionBean.", e);
            JSFUtils.addFacesErrorMessage("No se pudo recuperar el Orden de Inicio: " + e.getMessage());
            return null;
        }

        try {
            idOperacion = new Long(idOperacionStr);
        } catch (Exception e) {
            logger.warning("ERROR al castear IdOperacionStr a Long.", e);
        }

        //Se busca el registro del termino T704 - Orden de inicio con ID = 39
        params.put("idOperacion", idOperacion);
        params.put("idTcaTermino", ID_TCA_TERMINO_ORDEN_INICIO);
        try {
            row = execute(params, "obtenerTerminoPorIdOperacionTcaTermino");
        } catch (Exception e) {
            logger.warning("ERROR al ejecutar operBinding obtenerTerminoPorIdOperacionTcaTermino.", e);
            JSFUtils.addFacesErrorMessage("No se pudo recuperar el Orden de Inicio: " + e.getMessage());
            valorOrdenInicio = null;
        }

        if (null != row) {

            valorOrdenInicioInt = (Integer) row.getAttribute("RequiereOrdenInicio");
            if (null != valorOrdenInicioInt) {
                if (valorOrdenInicioInt.compareTo(0) == 0) {
                    valorOrdenInicio = Boolean.FALSE;
                } else {
                    valorOrdenInicio = Boolean.TRUE;
                }
            }

            logger.warning("Orden de inicio: " + valorOrdenInicio);
            if (null == valorOrdenInicio) {
                logger.warning("El Termino t704 no tiene el valor OrdenInicio.");
            }
        } else {
            logger.warning("No se encontro registro de T704.");
        }

        logger.warning("Termina metodo obtenerValorOrdenInicio.");
        return valorOrdenInicio;
    }

    private boolean totalFormalizacionesValidas() {
        logger.warning("Entra en totalFormalizacionesValidas.");
        // EX02: El sistema valida la diferencia del monto aprobado y el monto total de las formalizaciones ya ejecutadas.
        boolean totalValido = Boolean.TRUE;
        BigDecimal montoTotalFormalizaciones = obtenerTotalFormalizacionesEjecutadas();
        BigDecimal montoAprobado = obtenerMontoAprobado();
        BigDecimal montoDiferencia = BigDecimal.ZERO;
        Double montoEscriturado = obtenerMontoEscrituradoDouble();
        logger.warning("montoTotalFormalizaciones : " + montoTotalFormalizaciones);
        logger.warning("montoAprobado : " + montoAprobado);
        logger.warning("montoEscriturado : " + montoEscriturado);
        if (null != montoAprobado) {
            montoDiferencia = montoAprobado.subtract(montoTotalFormalizaciones);
        }
        logger.warning("montoDiferencia : " + montoDiferencia);
        if (montoEscriturado.compareTo(montoDiferencia.doubleValue()) > 0) {
            totalValido = Boolean.FALSE;
            logger.warning("totalValido : " + totalValido);
            logger.warning("montoEscriturado.compareTo(montoDiferencia.doubleValue()) prb: " + montoEscriturado.compareTo(montoDiferencia.doubleValue()));
        }

        return totalValido;
    }


    public Boolean validarLotePresupuestado(BigDecimal monto) {
        Boolean respuesta = Boolean.FALSE;
        logger.warning("Inicia metodo de validarLotePresupuestado");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        //Long lote=85L;
        if (null != formalizacionBean.getIdLote() && null != monto && null != formalizacionBean.getIdOperacion()) {
            // Actualizamos fechas, creamos Monto escriturado y lo asignamos al Contrato correspondiente
            operationBinding = bindings.getOperationBinding("validarMontoLote");
            operationBinding.getParamsMap().put("idOperacion", formalizacionBean.getIdOperacion());
            //operationBinding.getParamsMap().put("idLote", lote);
            operationBinding.getParamsMap().put("idLote", formalizacionBean.getIdLote());
            operationBinding.getParamsMap().put("monto", monto);

            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
            } else if (operationBinding.getResult() != null) {
                respuesta = (Boolean) operationBinding.getResult();
            } else {
                // JSFUtils.addFacesErrorMessage("Error al comparar el monto presupuestado o el monto ingresado excede el limite del monto presupuestado");
                logger.log(ADFLogger.WARNING, "No se puede recuperar el montot total de formalizaciones ejecutadas");
            }
        }

        logger.warning("Termina metodo de validarLotePresupuestado");
        logger.warning("resultado obtenido: " + respuesta);
        return respuesta;
    }


    @SuppressWarnings("unchecked")
    private BigDecimal obtenerTotalFormalizacionesEjecutadas() {
        logger.warning("Entra en obtenerTotalFormalizacionesEjecutadas.");
        BigDecimal montoTotalFormalizaciones = BigDecimal.ZERO;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");

        // Actualizamos fechas, creamos Monto escriturado y lo asignamos al Contrato correspondiente
        operationBinding = bindings.getOperationBinding("obtenerMontoTotalFormalizaciones");
        operationBinding.getParamsMap().put("idOperacion", formalizacionBean.getIdOperacion());

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        } else if (operationBinding.getResult() != null) {
            montoTotalFormalizaciones = (BigDecimal) operationBinding.getResult();
        } else {
            logger.log(ADFLogger.WARNING, "No se puede recuperar el montot total de formalizaciones ejecutadas");
        }

        return montoTotalFormalizaciones;
    }

    private Boolean saveOrUpdateTerminos() {
        logger.warning("Dentro saveOrUpdateTerminos");
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        Map<String, Object> atributos = new HashMap<String, Object>();
        logger.warning("Value montoEscriturado :"+JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}"));
        logger.warning("Value fechaFirma :"+JSFUtils.resolveExpression("#{bindings.FechaFirma.inputValue}"));
        logger.warning("Value fechaVigente :"+JSFUtils.resolveExpression("#{bindings.FechaVigencia.inputValue}"));
		logger.warning("Value idTipoMoneda :"+JSFUtils.resolveExpression("#{bindings.IdTipoMoneda.inputValue}"));
		
        try {
            
            double dbNumber = 0;
            String strNumber = null;
            if (JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}") != null) {
                strNumber = JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}").toString();
                try {
                    dbNumber = NumberFormat.getInstance().parse(strNumber).doubleValue();
                } catch (ParseException e) {
                    logger.severe("Error al convertir Monto Escriturado en Double number");
                }
            }

            BigDecimal monto = BigDecimal.valueOf(dbNumber);

            atributos.put("Monto", monto);

            // Id Tipo Moneda
            String strIdTipoMonedaNumber = null;
            Integer idTipoMoneda = 1;
            if (JSFUtils.resolveExpression("#{bindings.IdTipoMoneda.inputValue}") != null) {
                strIdTipoMonedaNumber = JSFUtils.resolveExpression("#{bindings.IdTipoMoneda.inputValue}").toString();
                if (strIdTipoMonedaNumber != null) {
                    try {
                        idTipoMoneda = Integer.parseInt(strIdTipoMonedaNumber);

                    } catch (NumberFormatException e) {
                        logger.warning("Error al convertir Id Tipo Moneda en Integer");
                    }
                    atributos.put("IdTcaMoneda", idTipoMoneda);
                }
            }

            ejecutarLogicaParaFormarParteTCC(formalizacionBean.getOperacionId().longValue(), ID_TCC_TERMINO_T203,
                                             atributos);

            try {
                atributos.clear();
                logger.warning("Fecha firma : " +
                               convertStringToTimestamp(JSFUtils.resolveExpression("#{bindings.FechaFirma.inputValue}").toString()));
                atributos.put("Fecha",
                              convertStringToTimestamp(JSFUtils.resolveExpression("#{bindings.FechaFirma.inputValue}").toString()));
                logger.warning("Fecha Firma de atributos :" + atributos.get("Fecha"));
                ejecutarLogicaParaFormarParteTCC(formalizacionBean.getOperacionId().longValue(), ID_TCC_TERMINO_T603,
                                                 atributos);

            } catch (Exception e) {
                logger.warning("Error al obtener la fecha firma.", e);
            }


            if (formalizacionBean.getEsFechaVigenciaIgualFechaFirma()) {
                atributos.clear();
                logger.warning("Fecha Vigencia : " +
                               convertStringToTimestamp(JSFUtils.resolveExpression("#{bindings.FechaVigencia.inputValue}").toString()));
                atributos.put("Fecha",
                              convertStringToTimestamp(JSFUtils.resolveExpression("#{bindings.FechaVigencia.inputValue}").toString()));
                ejecutarLogicaParaFormarParteTCC(formalizacionBean.getOperacionId().longValue(), ID_TCC_TERMINO_T604,
                                                 atributos);
            }
            logger.warning("Fuera saveOrUpdateTerminos");
            return Boolean.TRUE;
        } catch (OperationExecuteException e) {
            logger.severe("Error en saveOrUpdateTerminos :" + e);
            JSFUtils.addFacesErrorMessage(e.getMessage());
            return Boolean.FALSE;
        }
    }

    /**
     * Metodo para guardar TCC para la tarea RegistrarFechaVigencia
     * FNXII-7197
     * @return
     */
    private Boolean saveOrUpdateTerminosRegistrarFechaVigencia() {
        logger.warning("Dentro saveOrUpdateTerminosRegistrarFechaVigencia");
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        Map<String, Object> atributos = new HashMap<String, Object>();
        logger.warning("Value montoEscriturado :"+JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}"));
        logger.warning("Value fechaFirma :"+JSFUtils.resolveExpression("#{bindings.FechaFirma.inputValue}"));
        logger.warning("Value fechaVigente :"+JSFUtils.resolveExpression("#{bindings.FechaVigencia.inputValue}"));
		logger.warning("Value idTipoMoneda :"+JSFUtils.resolveExpression("#{bindings.IdTipoMoneda.inputValue}"));
		
        try {

            double dbNumber = 0;
            String strNumber = null;
            if (JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}") != null) {
                strNumber = JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}").toString();
                try {
                    dbNumber = NumberFormat.getInstance().parse(strNumber).doubleValue();
                } catch (ParseException e) {
                    logger.severe("Error al convertir Monto Escriturado en Double number");
                }
            }

            // Id Tipo Moneda
            String strIdTipoMonedaNumber = null;
            Integer idTipoMoneda = 1;
            if (JSFUtils.resolveExpression("#{bindings.IdTipoMoneda.inputValue}") != null) {
                strIdTipoMonedaNumber = JSFUtils.resolveExpression("#{bindings.IdTipoMoneda.inputValue}").toString();
                if (strIdTipoMonedaNumber != null) {
                    try {
                        idTipoMoneda = Integer.parseInt(strIdTipoMonedaNumber);

                    } catch (NumberFormatException e) {
                        logger.warning("Error al convertir Id Tipo Moneda en Integer");
                    }
                    atributos.put("IdTcaMoneda", idTipoMoneda);
                }
            }

            BigDecimal monto = BigDecimal.valueOf(dbNumber);
            try {
                logger.warning("Monto : " + monto);
                atributos.put("Monto", monto);
                ejecutarLogicaParaFormarParteTCC(formalizacionBean.getOperacionId().longValue(), ID_TCC_TERMINO_T203,
                                                 atributos);
            } catch (Exception e) {
                logger.severe("Error al actualizar termino Monto/Formalizado con id : " + ID_TCC_TERMINO_T203, e);
            }

            try {
                atributos.clear();
                logger.warning("Fecha firma : " +
                               convertStringToTimestamp(JSFUtils.resolveExpression("#{bindings.FechaFirma.inputValue}").toString()));
                atributos.put("Fecha",
                              convertStringToTimestamp(JSFUtils.resolveExpression("#{bindings.FechaFirma.inputValue}").toString()));
                logger.warning("Fecha Firma de atributos :" + atributos.get("Fecha"));
                ejecutarLogicaParaFormarParteTCC(formalizacionBean.getOperacionId().longValue(), ID_TCC_TERMINO_T603,
                                                 atributos);

            } catch (Exception e) {
                logger.warning("Error al actualizar termino Fecha/Firma del Contrato con id : " + ID_TCC_TERMINO_T603,
                               e);
            }

            try {
                atributos.clear();
                logger.warning("Fecha Vigencia : " +
                               convertStringToTimestamp(JSFUtils.resolveExpression("#{bindings.FechaVigencia.inputValue}").toString()));
                atributos.put("Fecha",
                              convertStringToTimestamp(JSFUtils.resolveExpression("#{bindings.FechaVigencia.inputValue}").toString()));
                ejecutarLogicaParaFormarParteTCC(formalizacionBean.getOperacionId().longValue(), ID_TCC_TERMINO_T604,
                                                 atributos);
            } catch (Exception e) {
                logger.warning("Error al actualizar termino Fecha/Vigencia del Contrato con id : " +
                               ID_TCC_TERMINO_T604, e);
            }

            logger.warning("Fuera saveOrUpdateTerminosRegistrarFechaVigencia");
            return Boolean.TRUE;
        } catch (Exception e) {
            logger.severe("Error en saveOrUpdateTerminosRegistrarFechaVigencia :" + e);
            JSFUtils.addFacesErrorMessage(e.getMessage());
            return Boolean.FALSE;
        }
    }

    public void ejecutarLogicaParaFormarParteTCC(Long idOperacion, Integer idTcaTermino,
                                                 Map<String, Object> atributos) throws OperationExecuteException {
        logger.warning("Dentro ejecutarLogicaParaFormarParteTCC");
        Map<String, Object> params = new HashMap<String, Object>();
        String loginUsuario = null;
        params.put("idOperacion", idOperacion);
        params.put("idTcaTermino", idTcaTermino);

        Long idTermino = super.<Long>execute(params, "existeTerminoByIdOperacionAndIdTcaTermino");

        try {
            loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName().toString();
        } catch (Exception e) {
            logger.warning("Ha ocurrido un error al recuperar el login usuario ->", e);
        }
        logger.warning("loginUsuario: " + loginUsuario);

        String nombreUsuario = getNombreUsuario(loginUsuario);
        logger.warning("nombreUsuario: " + nombreUsuario);

        if (idTermino != null) {
            logger.warning("Dentro if de ejecutarLogicaParaFormarParteTCC");
            logger.warning("idTermino :" + idTermino);

            try {
                params.clear();
                params.put("id", idTermino);
                params.put("atributos", atributos);
                params.put("loginUsuarioUltimo", loginUsuario);
                params.put("nombreUsuarioUltimo", nombreUsuario);
                super.execute(params, "updateTerminoLogin");
            } catch (Exception e) {
                logger.warning("ocurrio un error al ejecutar el metodo  updateTerminoLogin ->", e);
                JSFUtils.addFacesErrorMessage("Ha ocurrido un error al ejecutar updateTerminoLogin");
            }


        } else {
            logger.warning("Dentro del else de ejecutarLogicaParaFormarParteTCC");

            try {
                params.clear();
                params.put("idTcaTermino", idTcaTermino);
                String nombre = super.<String>execute(params, "getConcatNombreById");

                params.clear();
                params.put("idOperacion", idOperacion);
                params.put("nombreTermino", nombre);
                params.put("idTcaTermino", idTcaTermino);
                params.put("idTcaEstadoTcc", ID_TCA_ESTADO_TCC_FORMALIZADA);
                params.put("idTcaSubEstadoTcc", null);

                params.put("loginUsuario", loginUsuario);
                params.put("nombreUsuario", nombreUsuario);

                //Se comenta para atender incidencia FNXII-3910 por uso incorrecto de tipo de dato, banEstatus es Integer y no Booleano, debe estar como activo = 1
                //params.put("banEstatus", Boolean.FALSE);
                params.put("banEstatus", new Integer(1));

                params.put("atributos", atributos);
                super.<Long>execute(params, "crearTerminoCompleto");
            } catch (Exception e) {
                logger.warning("ocurrio un error al ejecutar el metodo  crearTerminoCompleto ->", e);
                JSFUtils.addFacesErrorMessage("Ha ocurrido un error al ejecutar crearTerminoCompleto");
            }
        }
        logger.warning("Fuera ejecutarLogicaParaFormarParteTCC");
    }


    public String getNombreUsuario(String login) {
        logger.warning("Inicia metodo getAtributosUsuario");
        Map datosUsuario = new HashMap();
        String nombreUsuario = null;
        BindingContainer binding = ADFUtils.getBindingContainer();
        OperationBinding operBinding = null;

        try {
            operBinding = binding.getOperationBinding("consultarAtributosUsuario");
            operBinding.getParamsMap().put("login", login);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding consultarAtributosUsuario devuelve errores" +
                               operBinding.getErrors().toString());
            } else {
                datosUsuario = (Map) operBinding.getResult();

                nombreUsuario =
                    (null == datosUsuario.get("nombreUsuario")) ? null : (String) datosUsuario.get("nombreUsuario");
            }

        } catch (Exception e) {
            logger.warning("Error al recuperar los datos del usuario ", e);
            JSFUtils.addFacesErrorMessage("Ha ocurrido un error al ejecutar consultarAtributosUsuario");
        }

        logger.warning("Inicia metodo getAtributosUsuario");
        return nombreUsuario;
    }

    public java.sql.Timestamp convertStringToTimestamp(String dateString) {
        logger.warning("Dentro convertStringToTimestamp");
        logger.warning("dateString :" + dateString);
        Date date = null;
        java.sql.Timestamp dateTimeStamp = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", new Locale("es"));
            date = sdf.parse(dateString);
            dateTimeStamp = new java.sql.Timestamp(date.getTime());
        } catch (ParseException e) {
            logger.warning("Error cast fecha con formato dd/MMM/yyyy :", e);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("es"));
            try {
                date = sdf.parse(dateString);
            } catch (ParseException f) {
                logger.warning("Error cast fecha con formato yyyy-MM-dd :", f);
            }
            dateTimeStamp = new java.sql.Timestamp(date.getTime());
        }
        logger.warning("Fuera convertStringToTimestamp , return :" + dateTimeStamp);
        return dateTimeStamp;
    }

    private Boolean validarContratoFirmado() {

        logger.warning("Inicia validarContratoFirmado");

        Boolean esDatosCorrectos = Boolean.TRUE;
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        Double montoEscriturado = null;
        BigDecimal montoAprobado = null;
        Double montoAprobadoDouble = null;

        // Asignación de variables
        //montoEscriturado = (oracle.jbo.domain.Number)JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}");
        montoEscriturado = obtenerMontoEscrituradoDouble();
        montoAprobado = obtenerMontoAprobado();
        montoAprobadoDouble = (montoAprobado != null ? montoAprobado.doubleValue() : null);

        logger.warning("Valor de montoEscriturado: " + montoEscriturado);
        logger.warning("Valor de montoAprobado: " + montoAprobado);
        logger.warning("Valor de montoAprobadoDouble: " + montoAprobadoDouble);

        // RN3: El actor accede a la sección "Documentos" y adjunta el archivo del contrato digitalizado.
        //if(!validateDocumento(Long.valueOf(formalizacionBean.getIdOperacion()),
        //                      Integer.valueOf(FenixConstants.DOCUMENTO_CONTRATO_FIRMADO))) {

        //   esDatosCorrectos = Boolean.FALSE;
        //   String msg = null;
        //   msg = getStringFromBundle("ADJUNTAR_CONTRATO_FIRMADO_MSG_VALIDAR_ARCHIVO_CONTRATO");
        //   logger.warning("Validacion incumplida: " + msg);
        //   JSFUtils.addFacesErrorMessage(msg);
        // }

        // El monto escriturado deberá ser menor o igual al monto aprobado.
        if ((montoEscriturado != null) && (montoAprobadoDouble != null)) {
            if ((montoEscriturado > montoAprobadoDouble)) {

                esDatosCorrectos = Boolean.FALSE;
                String msg = null;
                msg = getStringFromBundle("ADJUNTAR_CONTRATO_FIRMADO_MSG_VAL_MONTO_ESCRITURADO_APROBADO");
                logger.warning("Validacion incumplida: " + msg);
                JSFUtils.addFacesErrorMessage(msg);
            }
        } else {
            if (montoEscriturado == null) {
                logger.severe("El Monto Escriturado es NULL");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ADJUNTAR_CONTRATO_FIRMADO_MSG_VAL_MONTO_ESCRITURADO_EMPTY"));
            }
            if (montoAprobadoDouble == null) {
                logger.severe("El Monto Aprobado es NULL");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ADJUNTAR_CONTRATO_FIRMADO_MSG_VAL_MONTO_APROBADO_EMPTY"));
            }
            esDatosCorrectos = Boolean.FALSE;
        }

        logger.warning("Finaliza validarContratoFirmado");
        return esDatosCorrectos;
    }

    private boolean seIngresoMontoFormalizado() {
        logger.log(ADFLogger.WARNING, "Entrando en seIngresoMontoFormalizado.");
        boolean montoValido = Boolean.TRUE;
        Double montoEscriturado = obtenerMontoEscrituradoDouble();
        logger.warning("Valor de montoEscriturado: " + montoEscriturado);

        if (null == montoEscriturado) {
            montoValido = Boolean.FALSE;
        }

        return montoValido;
    }

    private void guardarDatosEnContrato(Long idOperacion, String instanciaProceso) {
        logger.warning("guardarDatosEnContrato");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        DCIteratorBinding voAdjuntarContratoFirmado = null;

        // Asignación de variables
        voAdjuntarContratoFirmado =
            ADFUtils.getDCBindingContainer().findIteratorBinding("AdjuntarContratoFirmadoVOIterator");

        // Actualizamos fechas, creamos Monto escriturado y lo asignamos al Contrato correspondiente
        operationBinding = bindings.getOperationBinding("actualizarContratoFirmado");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);

        oracle.jbo.domain.Number montoEscriturado = null;
        if (voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("MontoEscriturado") != null) {
            try {
                String strMonto =
                    voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("MontoEscriturado").toString();
                double dbMonto = 0;
                dbMonto = NumberFormat.getInstance().parse(strMonto).doubleValue();
                montoEscriturado = new oracle.jbo.domain.Number(dbMonto);
            } catch (Exception e) {
                logger.severe("Error al obtener el Monto Escriturado");
            }
        }

        operationBinding.getParamsMap().put("montoEscriturado", montoEscriturado);
        operationBinding.getParamsMap().put("fechaFirma",
                                            voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("FechaFirma"));
        operationBinding.getParamsMap().put("fechaVigencia",
                                            voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("FechaVigencia"));
        logger.warning("id Tca Tipo de Moneda: " +
                       voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("IdTipoMoneda"));
        operationBinding.getParamsMap().put("idTcaTipoMoneda",
                                            voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("IdTipoMoneda"));

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            logger.warning("Manejo de errores: " + operationBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
    }

    public Boolean actualizarFechaInicioTerminoTipoPlazo(Long idOperacion) {
        logger.warning("Dentro actualizarFechaInicioTerminoTipoPlazo");
        logger.warning("idOperacion:" + idOperacion);
        AttributeBinding fechaFirma;
        fechaFirma = ADFUtils.findControlBinding("FechaFirma");
        AttributeBinding fechaVigencia;
        fechaVigencia = ADFUtils.findControlBinding("FechaVigencia");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean retorno = Boolean.FALSE;


        if (null != fechaFirma.getInputValue() && !fechaFirma.getInputValue().toString().trim().equals("")) {
            logger.warning("Actualizar Terminos Plazo con tipo Fecha Inicio Escrituracion");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", new Locale("es"));
            Timestamp fechaFirmaConvertida = null;

            try {
                Date fecha = formatter.parse(fechaFirma.getInputValue().toString());
                fechaFirmaConvertida = new Timestamp(fecha.getTime());
            } catch (Exception e) {
                logger.severe("Error al convertir la fecha de firma a Timestamp :" + e);
            }

            try {
                operationBinding = bindings.getOperationBinding("actualizarFechaInicioTerminosTipoPlazo");
                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                operationBinding.getParamsMap().put("fecha", fechaFirmaConvertida);
                operationBinding.getParamsMap().put("idTipoFechaInicio", 9); //id 9 = Tipo Fecha de Inicio Escrituracion

                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    logger.warning("Error al actualizar los terminos tipo Plazo");
                    return Boolean.FALSE;
                } else {
                    retorno = Boolean.TRUE;
                }
            } catch (Exception e) {
                logger.warning("ha ocurrido una excepcion al ejecutar actualizarFechaInicioTerminosTipoPlazo FF ->", e);
                JSFUtils.addFacesErrorMessage("Error al actualizar FechaInicio Terminos TipoPlazo");
            }

        }

        if (null != fechaVigencia.getInputValue() && !fechaVigencia.getInputValue().toString().trim().equals("")) {
            logger.warning("Actualizar Terminos Plazo con tipo Fecha Inicio Vigencia");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy", new Locale("es"));
            Timestamp fechaVigenciaConvertida = null;
            try {
                Date fecha = formatter.parse(fechaVigencia.getInputValue().toString());
                fechaVigenciaConvertida = new Timestamp(fecha.getTime());
            } catch (Exception e) {
                logger.severe("Error al convertir la fecha inicio vigencia a Timestamp :" + e);
            }

            try {
                operationBinding = bindings.getOperationBinding("actualizarFechaInicioTerminosTipoPlazo");
                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                operationBinding.getParamsMap().put("fecha", fechaVigenciaConvertida);
                operationBinding.getParamsMap().put("idTipoFechaInicio", 13); //id 13 = Tipo Fecha de Inicio Vigencia

                operationBinding.execute();

                logger.warning("Fuera actualizarFechaInicioTerminoTipoPlazo");
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    logger.warning("Error al actualizar los terminos tipo Plazo");
                    return Boolean.FALSE;
                } else {
                    retorno = Boolean.TRUE;
                }
            } catch (Exception e) {
                logger.warning("ha ocurrido una excepcion al ejecutar actualizarFechaInicioTerminosTipoPlazo FV ->", e);
                JSFUtils.addFacesErrorMessage("Error al actualizar FechaInicio Terminos TipoPlazo");
            }
        }
        return retorno;
    }


    public void fechaVigenciaIgualFechaFirmaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "Inside fechaVigenciaIgualFechaFirmaValueChangeListener.");
        Boolean esFechaVigenciaIgualFechaFirma = Boolean.FALSE;
        DCIteratorBinding voAdjuntarContratoFirmado = null;

        // Asignación de variables
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // update model values
        esFechaVigenciaIgualFechaFirma = (Boolean) valueChangeEvent.getNewValue();
        voAdjuntarContratoFirmado =
            ADFUtils.getDCBindingContainer().findIteratorBinding("AdjuntarContratoFirmadoVOIterator");

        if (esFechaVigenciaIgualFechaFirma) {

            // Asignamos valor de Fecha de firma a Fecha de vigencia
            voAdjuntarContratoFirmado.getRowAtRangeIndex(0).setAttribute("FechaVigencia",
                                                                         voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("FechaFirma"));
        }
       
    }

    private Boolean validarTccAdjuntarContratoFirmado(Long idOperacion) {
        logger.log(ADFLogger.WARNING, "Inside validarTccAdjuntarContratoFirmado.");
        Boolean esEjecucionExitosa = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        // Ejecutamos método que realiza: Todos los elementos TCC que se encuentren en un estado diferente a "Por validar"
        // deben pasar al estado "Formalizada" al finalizar la tarea. Lo anterior de acuerdo a incidencia FNXII-3165.
        operationBinding = bindings.getOperationBinding("validarTccAdjuntarContratoFirmado");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);


        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        } else if ((operationBinding.getResult() != null) && ((Boolean) operationBinding.getResult()))
            esEjecucionExitosa = Boolean.TRUE; // TCCs actualizados exitosamente.
        else
            JSFUtils.addFacesErrorMessage("Error al actualizar elementos TCC."); // getResult() es null o false

        return esEjecucionExitosa;
    }

    /*** END Pantalla Adjuntar contrato firmado ***/


    /*** START Pantalla Custodiar contrato ***/

    public String aceptarFinalizarTareaCustodiarContrato() {
        // Add event code here...
        logger.log(ADFLogger.WARNING, "Inside aceptarFinalizarTareaCustodiarContrato.");
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        Long idContrato = null;
        String numeroCustodia = formalizacionBean.getNumeroCustodia();
        if (numeroCustodia != null) {
            //recuperar el ID de CONTRATO
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding;
            operationBinding = bindings.getOperationBinding("getIdContratoByOperacionInstanciaProceso");
            operationBinding.getParamsMap().put("idOperacion", formalizacionBean.getIdOperacion());
            operationBinding.getParamsMap().put("instanciaProceso", formalizacionBean.getInstanciaProceso());
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());

            }
            idContrato = (Long) operationBinding.getResult();
            //actualizar la tabla CONTRATO (NUMERO_CUSTODIA)
            operationBinding = bindings.getOperationBinding("actualizarNumeroCustodia");
            operationBinding.getParamsMap().put("id", idContrato);
            operationBinding.getParamsMap().put("numeroCustodia", numeroCustodia);
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());

            }
            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();
            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();

        } else {
            //el sistema valida que la información está incompleta.  MSG01
            JSFUtils.addFacesErrorMessage("Debe completar la información  requerida.");
            //cerramos popup de Confirmar Finalizar Tarea
            getPopupConfirmarFinalizarTarea().hide();
        }
        return returnAction;
    }
    /*** END Pantalla Custodiar contrato ***/


    /*** START Pantalla Registrar fecha de vigencia ***/
    public String aceptarFinalizarTareaFechaVigenciaAction() {
        logger.warning("Inside aceptarFinalizarTareaFechaVigenciaAction.");
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        DCIteratorBinding voRegistrarFechaVigencia = null;
        java.sql.Timestamp fechaFirma = null;
        java.sql.Timestamp fechaVigencia = null;
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;

        // Asignación de variables
        voRegistrarFechaVigencia =
            ADFUtils.getDCBindingContainer().findIteratorBinding("RegistrarFechaVigenciaVOIterator");
        fechaFirma = (java.sql.Timestamp) voRegistrarFechaVigencia.getRowAtRangeIndex(0).getAttribute("FechaFirma");
        fechaVigencia =
            (java.sql.Timestamp) voRegistrarFechaVigencia.getRowAtRangeIndex(0).getAttribute("FechaVigencia");

        // RN1: La fecha de Vigencia deberá ser mayor ó igual a la fecha de firma del contrato.
        // Nota: Fecha de firma nunca debería llegar como null y la Fecha de vigencia es requerida en pantalla
        if ((fechaFirma != null) && (fechaVigencia != null) && (fechaVigencia.compareTo(fechaFirma) >= 0)) {

            // Actualizamos fechas en BD
            if (actualizarFechasContrato(formalizacionBean.getOperacionId().longValue(),
                                         formalizacionBean.getInstanciaProceso(), fechaFirma,
                                         new oracle.jbo.domain.Date(fechaVigencia)) &&
                actualizarFechaInicioTerminoTipoPlazo(formalizacionBean.getOperacionId().longValue()) &&
                saveOrUpdateTerminosRegistrarFechaVigencia()) {

                // Invocar método para cargar documentos en onBase
                cargarDocumentosOnBase();

                invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                returnAction = invokeActionBean.invokeOperation();
            }
        } else {
            JSFUtils.addFacesErrorMessage("La fecha de Vigencia deberá ser mayor ó igual a la fecha de firma del contrato.");

            // Cerramos popup de Confirmar Finalizar Tarea
            getPopupConfirmarFinalizarTarea().hide();
        }

        return returnAction;
    }

    private Boolean actualizarFechasContrato(Long idOperacion, String instanciaProceso, Timestamp fechaFirma,
                                             oracle.jbo.domain.Date fechaVigencia) {
        Boolean esEjecucionExitosa = Boolean.FALSE;


        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {
            // Actualizamos tabla Contrato en BD con la FechaFirma y FechaVigencia
            operationBinding = bindings.getOperationBinding("actualizarFechas");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
            operationBinding.getParamsMap().put("fechaFirma", fechaFirma);
            operationBinding.getParamsMap().put("fechaVigencia", fechaVigencia);

            operationBinding.execute();
        } catch (Exception e) {
            logger.warning("Ha ocurrido un error al ejecutar el metodo actualizarFechas ->", e);
        }

        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores (no usamos addFacesErrorMessage debido a que se lanzan desde el Model encapsulados en una JboException)
        } else
            esEjecucionExitosa = Boolean.TRUE; // Actualización exitosa en BD

        return esEjecucionExitosa;
    }
    /*** END Pantalla Registrar fecha de vigencia ***/


    /*** START Pantalla Registrar datos de la línea de crédito ***/
    public String aceptarFinalizarTareaRegistrarLineaCreditoAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarFinalizarTareaRegistrarLineaCreditoAction.");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;

        // Validación de datos en header
        if (validarRegistrarLineaCredito()) {
            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();

            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();
        }

        // Cerramos popup de Confirmar Finalizar Tarea
        getPopupConfirmarFinalizarTarea().hide();

        return returnAction;
    }

    private Boolean validarRegistrarLineaCredito() {

        logger.warning("Inicia validarRegistrarLineaCredito");

        Boolean esDatosCorrectos = Boolean.TRUE;
        Integer diasDePago = null;
        BigDecimal montoEscriturado = null;
        BigDecimal montoTotal = null;
        FormalizacionBean formalizacionBean = getFormalizacionManagedBean();
        
        try {
            if (ADFUtils.getBoundAttributeValue("MontoEscriturado") != null) {

                logger.warning("Valor de MontoEscriturado: " +
                               ADFUtils.getBoundAttributeValue("MontoEscriturado").toString());

                if (!ADFUtils.getBoundAttributeValue("MontoEscriturado").toString().isEmpty()) {

                    String strValue = ADFUtils.getBoundAttributeValue("MontoEscriturado").toString();
                    double dNewValue = NumberFormat.getInstance().parse(strValue).doubleValue();
                    montoEscriturado = BigDecimal.valueOf(dNewValue);
                }
            } else {
                logger.warning("El valor de MontoEscriturado es NULL");
            }
        } catch (Exception e) {
            logger.severe("Error al leer y convertir MontoEscriturado", e);
            montoEscriturado = null;
        }

        montoTotal = obtenerMontoTotal();

        //El monto total se compara contra el monto escriturado cuando este es ingresado y
        //es comparado contra el monto aprobado en caso contrario
        if (!montoTotalValido()) {
            esDatosCorrectos = Boolean.FALSE;
        }

        /*  Gabriel Niño Rosales 29/02/2016
         *      FNXII-2967 Pruebas-PC05-Formalización-Estructuración de prestamo
         *      Si el producto es Préstamo Estructurado, se validara al finalizar que el montoSindicado no sea mayor
         *      al montoEscriturado
         *      Préstamo Estructurado == 2
        */
        if (formalizacionBean.getIdProducto() == 2) {
            //Crear instancia de AdministradorEstructuracionPrestamoBean
            Double montoTotalSindicado = (Double) ADFContext.getCurrent().getSessionScope().get("montoTotalSindicado");
            if (montoTotalSindicado != null) {
                // El monto sindicado no debe exceder el monto escriturado.
                if (montoEscriturado.doubleValue() < montoTotalSindicado) {
                    esDatosCorrectos = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("El monto total sindicado no puede ser mayor al monto escriturado.");
                }

            } else {
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("El monto total sindicado es requerido.");
            }
        }

        //Previo a finalizar, obtiene si las lineas fueron guardadas y validadas, de no ser asi devuelve un mensaje de error indicando que deben validarse previamente.
        ADFContext adfCtx = ADFContext.getCurrent();
        Map sessionScope = adfCtx.getSessionScope();
        Object val = sessionScope.get("lineasValidasGuardadas");
        String lineasGuardadas = val != null ? val.toString() : "N";

        if (lineasGuardadas == "N") {
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("Debe guardar la línea de crédito para identificar las validaciones pendientes.");
        }
        
        logger.warning("Finaliza validarRegistrarLineaCredito con esDatosCorrectos en: " + esDatosCorrectos);
        return esDatosCorrectos;
    }
    /*** END Pantalla Registrar datos de la línea de crédito ***/


    /*** START Pantalla Validar datos de línea de crédito ***/
    public String aceptarFinalizarTareaValidarLineaCreditoAction() {

        logger.warning("Inicia aceptarFinalizarTareaValidarLineaCreditoAction.");

        String returnAction = null;
        InvokeActionBean invokeActionBean = null;
        BindingContainer bindings = null;

        bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("propagarLineaCreditoInstancia");
        String resultado = (String) operationBinding.execute();

        logger.log(ADFLogger.WARNING, "propagarLineaCreditoInstancia resultado: " + resultado);

        if (!operationBinding.getErrors().isEmpty()) {
            logger.severe("El operationBinding para propagarLineaCreditoInstancia contiene errores");
        } else {

            if (null != resultado) {

                if (resultado.equalsIgnoreCase("Pasa") || resultado.equals("")) {
                    logger.warning("Invoca la carga de documentos a Onbase");
                    cargarDocumentosOnBase();
                    invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                    logger.warning("Invoca outcome de BPM");
                    returnAction = invokeActionBean.invokeOperation();

                    //Se actualiza en nuevo monto en flexcube
                    Integer idProducto = this.getFormalizacionManagedBean().getIdProducto();
                    if (idProducto == FenixConstants.ID_AMPLIACION_LINEA_GLOBAL_CREDITO_IFI) {

                    }
                } else {
                    logger.severe("El operationBinding propagarLineaCreditoInstancia no devuelve resultado exitoso");
                }
            } else {
                logger.severe("El objeto resultado del operationBinding propagarLineaCreditoInstancia es NULL");
                JSFUtils.addFacesErrorMessage("Error al propagar la linea de credito.");
            }
        }
        logger.warning("Termina aceptarFinalizarTareaValidarLineaCreditoAction");
        return returnAction;
    }

    public String aceptarSolicitarAjustesValidarLineaCreditoAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarSolicitarAjustesValidarLineaCreditoAction.");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");

        if (validateComentario(Long.valueOf(formalizacionBean.getIdOperacion()), formalizacionBean.getIdTarea(),
                               getInstanciaTarea())) {
            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();

            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            returnAction = invokeActionBean.invokeOperation();
        } else {
            //Revisar CU Formalizacion BCIE-F2-CU-PC05-Validar datos de la l_nea de cr_dito
            //EX01
            //MSG01
            JSFUtils.addFacesErrorMessage("Debe ingresar la información requerida.");

            // Cerramos popup de Confirmar Finalizar Tarea
            getPopupConfirmarFinalizarTarea().hide();
        }

        return returnAction;
    }
    /*** END Pantalla Validar datos de línea de crédito ***/


    /*** START Pantalla Asignación de recursos ***/
    public String aceptarFinalizarTareaAsignacionRecursosAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarFinalizarTareaAsignacionRecursosAction.");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;

        // Debe de existir al menos una línea de crédito
        if (validarNumeroLineasCredito()) {

            // Valida que las fuentes de cada línea de crédito para que no tenga mas de dos una fuente sin fecha
            if (validarNoFuentesLineasCredito()) {

                // RN02: Para poder finalizar la tarea el "Monto total en USD" de cada línea debe ser igual al total asignado a cada una.
                // Se aplica modificiacion en la validacion conforme a la incidencia FNXII-4211, aunque el monto de las asignaciones
                // no cubra el monto de las lineas se debe permitir finalizar tarea requiriendo un comentario
                if (validarAsignacionesPorMontoTotalLinea()) {

                    logger.warning("Las asignaciones cubren los montos de las lineas");


                    // Finalizando tarea
                    returnAction = finalizarAsignarRecursos();
                } else {

                    logger.warning("Las asignaciones no cubren los montos de las lineas, se requiere comentario");

                    // Se valida el ingreso de un comentario para permitir finalizar la tarea
                    if (validateComentario(Long.valueOf(getFormalizacionManagedBean().getIdOperacion()),
                                           getFormalizacionManagedBean().getIdTarea(), getInstanciaTarea())) {

                        logger.warning("Existe comentario, continua con la finalizacion de la tarea");
                        // Finalizando tarea
                        returnAction = finalizarAsignarRecursos();
                    } else {

                        logger.warning("No existe comentario, muestra mensaje de validacion");
                        // Se notifica que se debe ingresar un comentario
                        notificarValidacion(TareaFormalizacionEnum.ASIGNACION_RECURSOS,
                                            BundleConstants.COMENTARIO_PARA_CONTINUAR);
                    }
                }
            }
        }

        // Cerramos popup de confirmar finalizar tarea
        getPopupConfirmarFinalizarTarea().hide();

        return returnAction;
    }

    private Boolean validarNoFuentesLineasCredito() {
        Boolean result = Boolean.FALSE;
        try {
            result = super.execute(null, "validarLineaCreditoNumeroFuentes");

            if (!result) {
                JSFUtils.addFacesErrorMessage("No se puede tener mas de una fuente sin fecha.");
            }
        } catch (OperationExecuteException e) {
            result = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("No se pudo validar el número de fuentes de las líneas de crédito.");
            logger.warning(e);
        }
        return result;
    }

    private Boolean validarNumeroLineasCredito() {
        Boolean esDatosCorrectos = Boolean.TRUE;
        Integer numLineasCredito;

        if (JSFUtils.resolveExpression("#{bindings.LineaCreditoVO.estimatedRowCount}") != null) {

            numLineasCredito =
                Integer.valueOf(JSFUtils.resolveExpression("#{bindings.LineaCreditoVO.estimatedRowCount}").toString());

            if (numLineasCredito < 1) {
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("Debe registrar al menos una línea de crédito.");
            }
        }

        return esDatosCorrectos;
    }

    private Boolean validarAsignacionesPorMontoTotalLinea() {
        Boolean esDatosCorrectos = Boolean.TRUE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        // Asignación de variables
        operationBinding = bindings.getOperationBinding("validarMontoTotalAsignacionesPorLinea");

        // Ejecutamos validación
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Boolean result = (Boolean) operationBinding.getResult();
            logger.warning("Resultado de validarMontoTotalAsignacionesPorLinea " + result);

            if (!result) {
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ASIGNACION_RECURSOS_MSG_RN02"));
            }
        } else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ASIGNACION_RECURSOS_MSG_RN02_FAILED"));
        }

        return esDatosCorrectos;
    }

    private Boolean propagarFuente() {
        logger.log(ADFLogger.WARNING, "Inside propagarFuente.");
        Boolean isValidPropagarFuente = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        HashMap<String, PropagarFuenteLCResponseType> respuestaServicio = null;
        PropagarFuenteLCResponseType response = null;
        String errorCode = null;
        String message = null;

        // Asignación de variables
        operationBinding = bindings.getOperationBinding("propagarFuente");

        // Ejecutamos validación
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {

            respuestaServicio = (HashMap<String, PropagarFuenteLCResponseType>) operationBinding.getResult();
            response = respuestaServicio.get("response");

            if (response.getResultado().getError() != null) {
                errorCode =
                    response.getResultado().getError().getErrorCode() == null ? "" :
                    response.getResultado().getError().getErrorCode();
            } else {
                errorCode = "";
            }

            if ((response.getResultado().getResult() != null) &&
                (response.getResultado().getResult().value().equalsIgnoreCase("OK")) &&
                (errorCode.trim().length() == 0)) {

                isValidPropagarFuente = Boolean.TRUE;
            } else {
                // Error al ejecutar el servicio Propagar fuente
                JSFUtils.addFacesErrorMessage("No se puede realizar la asignación de la línea en ARE.");

                message =
                    response.getResultado().getMessage() == null ? "" :
                    ("Mensaje: " + response.getResultado().getMessage()) + ". ";
                JSFUtils.addFacesErrorMessage(message);
                // TODO El Objeto response.getResultado().getError() viene null. 23/dic/2015
                //                JSFUtils.addFacesErrorMessage(message + "Código de error: "
                //                                              + response.getResultado().getError().getErrorCode() + ". Descripción: "
                //                                              + response.getResultado().getError().getErrorDescription() + ".");
                //                logger.log(ADFLogger.ERROR, response.getResultado().getError().getErrorDescription());
            }
        }

        return isValidPropagarFuente;
    }

    public void agregarFuentePorLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,
                   "Inside agregarFuentePorLineaCreditoActionListener: " + actionEvent.getComponent().getId());
        agregarModificarFuenteActionListener(false);
    }

    public void modificarFuentePorLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,
                   "Inside modificarFuentePorLineaCreditoActionListener: " + actionEvent.getComponent().getId());
        agregarModificarFuenteActionListener(true);
    }

    private void agregarModificarFuenteActionListener(Boolean esModificar) {
        logger.log(ADFLogger.WARNING, "Inside agregarModificarFuenteActionListener.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        FormalizacionBean formalizacionBean = null;
        OperationBinding operationBinding = null;
        RichPopup.PopupHints popupHints = null;
        DCIteratorBinding voFuentesAsignadas = null;
        DCIteratorBinding voAgregarFuentePorLineaCredito = null;
        Row fuentesAsignadas = null;
        Row agregarFuente = null;

        // Creamos row de captura
        operationBinding = bindings.getOperationBinding("crearRowFuentePorLineaCredito");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Pre-llenamos datos si la operación es modificar
        if (esModificar) {
            formalizacionBean =
                (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
            voFuentesAsignadas = ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesAsignadasVOIterator");
            voAgregarFuentePorLineaCredito =
                ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarFuentePorLineaCreditoVOIterator");
            fuentesAsignadas = voFuentesAsignadas.getCurrentRow();
            agregarFuente = voAgregarFuentePorLineaCredito.getCurrentRow();

            agregarFuente.setAttribute("IdFuente", fuentesAsignadas.getAttribute("Id"));
            agregarFuente.setAttribute("IdLineaPasiva", fuentesAsignadas.getAttribute("IdLineaPasiva"));
            agregarFuente.setAttribute("Monto", fuentesAsignadas.getAttribute("MontoAsignado"));
            agregarFuente.setAttribute("Nombre", fuentesAsignadas.getAttribute("Descripcion"));

            if (fuentesAsignadas.getAttribute("FechaObtenido") != null) {
                agregarFuente.setAttribute("FechaObtenido", fuentesAsignadas.getAttribute("FechaObtenido"));
                formalizacionBean.setEsObtenidoEnFecha(true);
            }

            agregarFuente.setAttribute("Comentario", fuentesAsignadas.getAttribute("Comentario"));
        }

        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        getPopupAgregarModificarFuente().show(popupHints);
    }

    public void aceptarAgregarModificarFuenteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,
                   "Inside aceptarAgregarModificarFuenteActionListener: " + actionEvent.getComponent().getId());
        String opAgregarModificar = null;

        if (this.validarMontoAsignar()) {
            opAgregarModificar = (String) JSFUtils.resolveExpression("#{viewScope.opAgregarModificar}");
            Boolean esAgregar =
                (opAgregarModificar != null && opAgregarModificar.equalsIgnoreCase("agregar")) ? Boolean.TRUE :
                Boolean.FALSE;

            if (!esAgregar) {
                if (this.validaOpModificarFuentesExistentes()) {
                    if (validarFuenteExistente()) {
                        saveOrUpdateFuentes(esAgregar);
                    }
                } else {
                    saveOrUpdateFuentes(esAgregar);
                }
            } else {
                if (validarFuenteExistente()) {
                    saveOrUpdateFuentes(esAgregar);
                }
            }
        }
    }

    private void saveOrUpdateFuentes(Boolean esAgregar) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        FormalizacionBean formalizacionBean = null;
        DCIteratorBinding voLineaCreditoVO = null;
        DCIteratorBinding voAgregarFuentePorLineaCredito = null;
        OperationBinding operationBinding = null;
        Long idLineaCredito = null;
        Long idFuente = null;
        String idLineaPasiva = null;
        String nombre = null;
        java.sql.Timestamp fechaObtenido = null;
        BigDecimal montoAsignado = null;
        String comentario = null;

        formalizacionBean = (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        voAgregarFuentePorLineaCredito =
            ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarFuentePorLineaCreditoVOIterator");

        idLineaPasiva = (String) voAgregarFuentePorLineaCredito.getRowAtRangeIndex(0).getAttribute("IdLineaPasiva");
        nombre = (String) voAgregarFuentePorLineaCredito.getRowAtRangeIndex(0).getAttribute("Nombre");
        fechaObtenido =
            (java.sql.Timestamp) voAgregarFuentePorLineaCredito.getRowAtRangeIndex(0).getAttribute("FechaObtenido");
        montoAsignado = (BigDecimal) voAgregarFuentePorLineaCredito.getRowAtRangeIndex(0).getAttribute("Monto");
        comentario = (String) voAgregarFuentePorLineaCredito.getRowAtRangeIndex(0).getAttribute("Comentario");

        // Agregamos o modificamos fuente a la BD
        if (esAgregar) {
            voLineaCreditoVO = ADFUtils.getDCBindingContainer().findIteratorBinding("LineaCreditoVOIterator");
            idLineaCredito = (Long) voLineaCreditoVO.getCurrentRow().getAttribute("Id");

            operationBinding = bindings.getOperationBinding("agregarFuentePorLineaCredito");
            operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
        } else {
            idFuente = (Long) voAgregarFuentePorLineaCredito.getRowAtRangeIndex(0).getAttribute("IdFuente");

            operationBinding = bindings.getOperationBinding("modificarFuente");
            operationBinding.getParamsMap().put("idFuente", idFuente);
        }

        if (formalizacionBean.getEsObtenidoEnFecha()) {
            operationBinding.getParamsMap().put("fechaObtenido", fechaObtenido);
        } else {
            operationBinding.getParamsMap().put("fechaObtenido", null);
        }

        operationBinding.getParamsMap().put("idLineaPasiva", idLineaPasiva);
        operationBinding.getParamsMap().put("nombre", nombre);
        operationBinding.getParamsMap().put("montoAsignado", montoAsignado);
        operationBinding.getParamsMap().put("comentario", comentario);

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        // Actualizamos tabla con Fuentes asignadas
        actualizarFuentesAsignadas();

        // Cerramos popup
        getPopupAgregarModificarFuente().hide();
    }

    private Boolean validaOpModificarFuentesExistentes() {
        Boolean valida = Boolean.TRUE;
        DCIteratorBinding voFuentesAsignadas = null;
        Row fuentesAsignadas = null;

        voFuentesAsignadas = ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesAsignadasVOIterator");
        fuentesAsignadas = voFuentesAsignadas.getCurrentRow();

        // Obtiene datos capturados
        try {
            String idLineaPasivaMod = (String) ADFUtils.getBoundAttributeValue("IdLineaPasivaAttrValue");
            String nombreFuenteMod = (String) ADFUtils.getBoundAttributeValue("NombreAtrrValue");
            String idLineaPasivaSav =
                fuentesAsignadas.getAttribute("IdLineaPasiva") != null ?
                fuentesAsignadas.getAttribute("IdLineaPasiva").toString() : null;
            String nombreFuenteSav =
                fuentesAsignadas.getAttribute("Descripcion") != null ?
                fuentesAsignadas.getAttribute("Descripcion").toString() : null;

            if (idLineaPasivaMod.equals(idLineaPasivaSav) && nombreFuenteMod.equals(nombreFuenteSav)) {
                valida = Boolean.FALSE;
            }
        } catch (Exception e) {
            logger.severe("Error al obtener atributos " + e.getMessage());
        }
        return valida;
    }

    private Boolean validarMontoAsignar() {
        // NI07: El valor del monto a asignar no puede ser mayor a la disponibilidad de la línea.
        Boolean esDatosCorrectos = Boolean.TRUE;
        DCIteratorBinding voAgregarFuentePorLineaCredito = null;
        BigDecimal disponibilidad = null;
        BigDecimal montoAsignar = null;

        // Asignación de variables
        voAgregarFuentePorLineaCredito =
            ADFUtils.getDCBindingContainer().findIteratorBinding("AgregarFuentePorLineaCreditoVOIterator");
        disponibilidad =
            (BigDecimal) voAgregarFuentePorLineaCredito.getRowAtRangeIndex(0).getAttribute("Disponibilidad");
        montoAsignar = (BigDecimal) voAgregarFuentePorLineaCredito.getRowAtRangeIndex(0).getAttribute("Monto");

        if ((disponibilidad != null) && (montoAsignar != null) && (montoAsignar.compareTo(disponibilidad) > 0) &&
            disponibilidad.doubleValue() > 0) {

            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("El monto de asignación no puede ser mayor a la cantidad disponible.");
        }

        return esDatosCorrectos;
    }

    private Boolean validarFuenteExistente() {

        Boolean result = null;
        String idLineaPasiva = null;
        String nombreFuente = null;

        //Obtiene los valores de Linea pasiva y nombre de la fuente que se desea agregar y asignar a la linea de credito
        try {
            idLineaPasiva = (String) ADFUtils.getBoundAttributeValue("IdLineaPasivaAttrValue");
        } catch (Exception e) {
            logger.severe("Error al obtener el Id de Linea Pasiva");
        }

        try {
            nombreFuente = (String) ADFUtils.getBoundAttributeValue("NombreAtrrValue");
        } catch (Exception e) {
            logger.severe("Error al obtener el nombre de la Fuente");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idLineaPasiva", idLineaPasiva);
        params.put("nombreFuente", nombreFuente);

        try {
            result = (Boolean) execute(params, "validarFuenteExistente");
        } catch (Exception e) {
            logger.severe("Error al ejecutar el Operator Binding validarFuenteExistente", e.getMessage());
        }

        if (result != null) {

            if (!result) {
                //Muestra mensaje para indicar que la fuente ya existe y esta asignada a la linea de credito
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_VALIDAR_FUENTE_DUPLICADA"));
            }
        } else {
            result = Boolean.FALSE;
            //Muestra mensaje de Error
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_VALIDAR_FUENTE_DUPLICADA_ERROR"));
        }

        return result;
    }

    public void cancelarAgregarModificarFuenteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,
                   "Inside cancelarAgregarModificarFuenteActionListener: " + actionEvent.getComponent().getId());

        // Cerramos popup de Agregar/Modificar fuente en pantalla Asignación de recursos
        getPopupAgregarModificarFuente().hide();
    }

    public void confirmEliminarFuentePorLineaCreditoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,
                   "Inside confirmEliminarFuentePorLineaCreditoActionListener: " + actionEvent.getComponent().getId());

        // Eliminamos de BD la fuente seleccionada
        this.eliminarFuente();

        // Actualizamos tabla con Fuentes asignadas
        actualizarFuentesAsignadas();

        // Cerramos popup de confirmar Eliminar fuente
        getPopupEliminarFuente().hide();
    }

    public void descripcionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "Inside descripcionValueChangeListener.");

        // Guardamos la descripción de la línea de crédito si se modificó
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // update model values
        this.commitData();
    }

    private void actualizarFuentesAsignadas() {
        logger.log(ADFLogger.WARNING, "Inside actualizarFuentesAsignadas.");
            DCIteratorBinding voFuentesAsignadas =
            ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesAsignadasVOIterator");
        voFuentesAsignadas.executeQuery();
    }
    /*** END Pantalla Asignación de recursos ***/


    /*** START acciones genéricas ***/
    /* @Source: BTN_SOLICITAR_AJUSTES_POR_FUENTES_EXTERNAS - PC05ElaborarBorradorContrato (valida)
     * @Developer: RUGM
     * @Nota: Rastreo de flujo
     * */
    public void confirmarFinalizarTareaActionListener(ActionEvent actionEvent) {
        // Abre popup de confirmar finalizar tarea en todas las pantallas
        String acccion = "SOLICITAR_AJUSTE_POR_FUENTES_EXTERNAS";
        logger.warning("Inside confirmarFinalizarTareaActionListener: " + actionEvent.getComponent().getId());
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarFinalizarTarea().show(popupHints);
    }
    
    /* Overview: Se mdifica el metodo para agregar la acción proveniente de ELABORACION DE CONTRATO FINAL
     * Developer: RUGM
     * Date: 01/06/2021
     * */
    public void confirmarFinalizarTareaElaborarBorradorContratoActionListener(ActionEvent actionEvent, String accion) {
        // Abre popup de confirmar finalizar tarea en todas las pantallas
        logger.warning("Dentro de confirmarFinalizarTareaElaborarBorradorContratoActionListener");
        logger.warning("Parametro: actionEvent: " + actionEvent.toString());
        logger.warning("ConfirmarFinalizarTareaElaborarBorradorContratoActionListener: " + actionEvent.getComponent().getId());
        logger.warning("headerAction :" + JSFUtils.resolveExpressionAsString("#{viewScope.headerAction}"));
        logger.warning("accion :" + accion);
        FormalizacionBean formalizacionBean = (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        //validar si tiene condiciones
        Boolean existeCondicion = existenCondicionesPorIdOperacion();
        if (existeCondicion) {
            //si existe condcion es true mostrar el mensaje normal de finalizar tarea
            logger.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
        } else {
            /**
             * @Overview: Renderización de mensajes en PopupConfirmarFinalizarTarea para escenario FinalizarTareaSinCondicion 
             * @Developer RUGM
             * @Date: 01/06/2021
             * */
            switch(accion){
                case "enviarCofi":
                    JSFUtils.setExpressionValue("#{viewScope.headerAction}", "enviarCofi");
                    logger.warning("Seteo de #{viewScope.headerAction}: enviarCofi" );
                    break;
                case "enviarResponsableOperacion":
                    JSFUtils.setExpressionValue("#{viewScope.headerAction}", "enviarResponsableOperacion");
                    logger.warning("Seteo de #{viewScope.headerAction}: enviarResponsableOperacion" );
                    break;
                case "finalizarTarea":
                    // Si no existe condicion es false mostrará el mensaje de finalizar tarea sin condicion 
                    JSFUtils.setExpressionValue("#{viewScope.headerAction}", "finalizarTareaSinCondicion");
                    logger.warning("Seteo de #{viewScope.headerAction}: finalizarTareaSinCondicion" );
                    break;
                case "solicitarAjustes":
                    JSFUtils.setExpressionValue("#{viewScope.headerAction}", "solicitarAjustes");
                    logger.warning("Seteo de #{viewScope.headerAction}: solicitarAjustes" );
                    break;
                default:
                    // Si no existe condicion es false mostrará el mensaje de finalizar tarea sin condicion 
                    JSFUtils.setExpressionValue("#{viewScope.headerAction}", "finalizarTarea");
                    logger.warning("Seteo de #{viewScope.headerAction}: finalizarTarea" );
            }
                //JSFUtils.setExpressionValue("#{viewScope.headerAction}", "finalizarTarea");
                logger.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
        }
        //Levantara el popup con el nombre "PopupConfirmarFinalizarTarea"
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarFinalizarTarea().show(popupHints);
        logger.warning("Fuera de confirmarFinalizarTareaElaborarBorradorContratoActionListener");
    }

    public void confirmarFinalizarTareaNegociarDocumentacionConClienteActionListener(ActionEvent actionEvent) {
        // Abre popup de confirmar finalizar tarea en todas las pantallas
        logger.warning("Dentro de confirmarFinalizarTareaNegociarDocumentacionConClienteActionListener");
        logger.warning("headerAction :" + JSFUtils.resolveExpressionAsString("#{viewScope.headerAction}"));
        Boolean Validacion;
        //validar si tiene condiciones
        //modificacion 20/07/2021
        //Jenamorado
        //Ejecucion de advertencia 
       Boolean existeCondicion = existenCondicionesPorIdOperacion();
        if (existeCondicion) {
            //si existe condcion es true mostrar el mensaje normal de finalizar tarea
            logger.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
        } else {
            // se existe condicion es false mostrar el mensaje de finalizar tarea sin condicion
            JSFUtils.setExpressionValue("#{viewScope.headerAction}", "finalizarTareaSinCondicion");
            logger.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
        }
       Validacion=ValidarAdvertenciaFinalizarTareaNegociarDocumentacionAction();
   
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarFinalizarTarea().show(popupHints);
      
        logger.warning("Fuera de confirmarFinalizarTareaNegociarDocumentacionConClienteActionListener");
    }
//jenamorado 15/07/2021
    public void confirmarFinalizarTareaAdjuntarContratoActionListener(ActionEvent actionEvent,Long idOperacion) {
        FormalizacionBean formalizacionBean = (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        Boolean Validar;
        logger.warning("Dentro de confirmarFinalizarTareaAdjuntarContratoActionListener");
        logger.warning("headerAction :" + JSFUtils.resolveExpressionAsString("#{viewScope.headerAction}"));
        //jenamorado 21
       // guardarDatosEnContrato
        //validar plazo y mostrar error
         logger.warning("Entrando a Guardar guardarFechasEnContrato");
        // Guardamos en la tabla Contrato las fechas y monto ingresados
        Long codigoOperacion = idOperacion;
        logger.warning("codigoOperacion: "+codigoOperacion);
        
        String instanciaProceso=formalizacionBean.getInstanciaProceso().toString();       
        
        logger.warning("instanciaProceso: "+instanciaProceso);
        guardarFechasEnContrato(codigoOperacion,
                               instanciaProceso);
        Validar=ValidarAdvertenciaFinalizarTareaNegociarDocumentacionAction();
        if(Validar)
        {
         RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
         getPopupConfirmarFinalizarTarea().show(popupHints);
     }
        logger.warning("Fuera de confirmarFinalizarTareaAdjuntarContratoActionListener");
    }
    public void confirmarFinalizarTareaRegistrarLineaCreditoActionListener(ActionEvent actionEvent) {
        // Abre popup de confirmar finalizar tarea en todas las pantallas
        logger.warning("Dentro de confirmarFinalizarTareaRegistrarLineaCreditoActionListener");
        logger.warning("headerAction :" + JSFUtils.resolveExpressionAsString("#{viewScope.headerAction}"));
        //validar si tiene condiciones
        Boolean existeCondicion = existenCondicionesPorIdOperacion();
        if (existeCondicion) {
            //si existe condcion es true mostrar el mensaje normal de finalizar tarea
            logger.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
        } else {
            // se existe condicion es false mostrar el mensaje de finalizar tarea sin condicion
            JSFUtils.setExpressionValue("#{viewScope.headerAction}", "finalizarTareaSinCondicion");
            logger.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
        }
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarFinalizarTarea().show(popupHints);
        logger.warning("Fuera de confirmarFinalizarTareaRegistrarLineaCreditoActionListener");
    }

    public void confirmarFinalizarTareaRegFechaVig(ActionEvent actionEvent) {
        // Abre popup de confirmar finalizar tarea en todas las pantallas

        if (ADFUtils.getBoundAttributeValue("FechaVigencia") == null) {
            JSFUtils.addFacesErrorMessage("Es necesario ingresar la Fecha de vigencia");
        } else {
            logger.warning("Inside confirmarFinalizarTareaActionListener: " + actionEvent.getComponent().getId());
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupConfirmarFinalizarTarea().show(popupHints);
        }
    }

    public void cancelarFinalizarTareaActionListener(ActionEvent actionEvent) {
        // Cerramos popup de confirmar finalizar tarea en todas las pantallas
        logger.log(ADFLogger.WARNING,"Inside cancelarFinalizarTareaRecExternosActionListener: " + actionEvent.getComponent().getId());
        getPopupConfirmarFinalizarTarea().hide();
    }

    public void cancelarMasInformacion(ActionEvent actionEvent) {
        // Cerramos popup de confirmar finalizar tarea en todas las pantallas
        logger.log(ADFLogger.WARNING,"Inside cancelarFinalizarTareaRecExternosActionListener: " + actionEvent.getComponent().getId());
        getPopupConfirmarMasInformacion().hide();
    }

    private void eliminarFuente() {
        logger.warning("Dentro eliminarFuente");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idFuente = null;

        // Asignación de variables
        try {
            idFuente = (Long) JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
        } catch (Exception e) {
            logger.severe("Error al recuperar el id de la fuente externa : " + e);
        }

        logger.warning("idFuente : " + idFuente);

        // Eliminamos de BD la fuente seleccionada
        operationBinding = bindings.getOperationBinding("eliminarFuente");
        operationBinding.getParamsMap().put("id", idFuente);

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        logger.warning("Fuera eliminarFuente");
    }

    public void cancelEliminarFuenteActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside cancelEliminarFuenteActionListener: " + actionEvent.getComponent().getId());

        // Cerramos popup de confirmar Eliminar fuente
        getPopupEliminarFuente().hide();
    }

    private void commitData() {
        logger.log(ADFLogger.WARNING, "Inside commitData");
        BindingContainer bindings = null;
        OperationBinding commit = null;

        bindings = ADFUtils.getBindingContainer();
        commit = bindings.getOperationBinding("Commit");

        commit.execute();
        if (!commit.getErrors().isEmpty()) {
            // Manejo de errores
        }
    }

    private Map validarCondicionesFormalizacion(Long idOperacion) {
        logger.log(ADFLogger.WARNING, "Inside validarCondicionesFormalizacion.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        ValidarTCCResponseType response = null;
        HashMap<String, ValidarTCCResponseType> respuestaServicio = null;
        HashMap<String, Boolean> validarCondicionesFormalizacion = null;
        OperationBinding operationBinding = null;
        String mensajeError = null;
        Long idContrato=null;
        // Validamos Condiciones a formalizar y Plazo de formalización
        operationBinding = bindings.getOperationBinding("validarCondicionesFormalizacion1");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        /*
         * jenamorado 03/09/2021
         * se agrego idContrato
         * */
        logger.log(ADFLogger.WARNING, "Entrando ObtenerContratoporIntancia");
        idContrato=ObtenerContratoporIntancia();
        logger.log(ADFLogger.WARNING, "Saliendo ObtenerContratoporIntancia");
        operationBinding.getParamsMap().put("idContrato", idContrato);

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());

        } else if (operationBinding.getResult() != null) {
            respuestaServicio = (HashMap<String, ValidarTCCResponseType>) operationBinding.getResult();
            response = respuestaServicio.get("response");

            if ((response.getResultado().getResult() != null) &&
                (response.getResultado().getResult().value().equalsIgnoreCase("OK"))) {

                // Ejecución exitosa
                validarCondicionesFormalizacion = new HashMap<String, Boolean>();

                if (response.getListaValidaciones().getValidacion().size() > 0) {

                    for (Validacion validacion : response.getListaValidaciones().getValidacion()) {

                        String tipo = validacion.getTipo();

                        if (tipo != null) {

                            if (tipo.equalsIgnoreCase("PlazoFormalizacion"))
                                validarCondicionesFormalizacion.put("validaFechaFormalizacion",
                                                                    Boolean.valueOf(validacion.getResultado()));
                            else if (tipo.equalsIgnoreCase("CondicionEventoFormalizacion"))
                                validarCondicionesFormalizacion.put("validaCondiciones",
                                                                    Boolean.valueOf(validacion.getResultado()));
                        }
                    }
                }
            } else {
                // Error al ejecutar el servicio
                mensajeError = response.getResultado().getMessage();

                if (response.getResultado().getError() != null)
                    mensajeError = mensajeError.concat(response.getResultado().getError().getErrorDescription());

                JSFUtils.addFacesErrorMessage("Error al validar Condiciones de Formalización: " + mensajeError);
                logger.log(ADFLogger.ERROR, "Error al validar Condiciones de Formalización: " + mensajeError);
            }
        } else {
            logger.log(ADFLogger.WARNING, "operationBinding.getResult() es null.");
        }

        return validarCondicionesFormalizacion;
    }

    public void setOtInitForm(RichOutputText otInitForm) {
        this.otInitForm = otInitForm;
    }

    public RichOutputText getOtInitForm() {
        // Inicializamos componentes visuales.
        // Usado cuando queremos acceder a un componente que existe hasta que se desplegó en pantalla
        FormalizacionBean formalizacionBean = (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        Integer fuentesExternasRowCount;

        if (formalizacionBean.getIdTarea().equalsIgnoreCase(FenixConstants.PC05_DEFINIR_RECURSOS_EXTERNOS)) {
            // Pantalla Definir recursos externos y condiciones
            if (JSFUtils.resolveExpression("#{bindings.FuentesExternasVO.estimatedRowCount}") != null) {

                fuentesExternasRowCount =
                    Integer.valueOf(JSFUtils.resolveExpression("#{bindings.FuentesExternasVO.estimatedRowCount}").toString());

                if (fuentesExternasRowCount > 0)
                    formalizacionBean.setCuentaConRecExternos(1); // Sí cuenta con recursos externos
            }
        }

        return otInitForm;
    }

    private void actualizarFlagPayload(String variable, Object valor) {
        AttributeBinding attributeBinding = null;

        attributeBinding = ADFUtils.findControlBinding(variable);
        attributeBinding.setInputValue(valor);
    }

    /*** END acciones genéricas ***/

    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    public void obtenidoFechaChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {
            FormalizacionBean formalizacionBean =
                (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
            formalizacionBean.setEsObtenidoEnFecha(Boolean.valueOf(valueChangeEvent.getNewValue().toString()));
        }
    }

    /**
     * Valida el cambio de monto escriturado
     * @param valueChangeEvent contiene evento
     */
    public void montoEscrituradoValueChangeListener(ValueChangeEvent valueChangeEvent) {

        if (valueChangeEvent == null) {
            return;
        }

        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        String strNewValue = valueChangeEvent.getNewValue().toString();
        oracle.jbo.domain.Number montoEsc = null;
        try {
            if (strNewValue != null) {
                double dNewValue = NumberFormat.getInstance().parse(strNewValue).doubleValue();
                montoEsc = new oracle.jbo.domain.Number(dNewValue);
            }
        } catch (Exception e) {
            logger.warning("No se pudo obtener el valor numerico de Monto Escriturado");
        }

        if (montoEsc != null) {

            if (montoEsc.intValue() <= 0) {

                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_EL_VALOR_NO_PUEDE_SER_MENOR_A_CERO"));
                ADFUtils.setBoundAttributeValue("MontoEscriturado", null);

                if (getMontoEscrituradoUIC() != null) {
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getMontoEscrituradoUIC());
                }
            }
        }
    }

    public void tipoMonedaValueChangeListener(ValueChangeEvent valueChangeEvent){
        logger.warning("Inside tipoMonedaValueChangeListener: " + valueChangeEvent.getComponent().getId());

        /*
        Integer IdTcaTipoMoneda = (Integer) valueChangeEvent.getNewValue();
        IdTcaTipoMoneda = IdTcaTipoMoneda + 1;
        Row rowSelected = getIteratorCurrentRow("AdjuntarContratoFirmadoVOIterator");
        rowSelected.setAttribute("IdTipoMoneda", IdTcaTipoMoneda);
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        logger.warning("id Moneda: " + IdTcaTipoMoneda);
        */

        Integer IdTcaTipoMoneda = null;
        DCIteratorBinding voAdjuntarContratoFirmado = null;
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        IdTcaTipoMoneda = Integer.valueOf(JSFUtils.resolveExpression("#{bindings.Moneda1.inputValue}").toString());
        // Asignamos en current row de CrearOperacionVO el valor de idMoneda
        voAdjuntarContratoFirmado =
            ADFUtils.getDCBindingContainer().findIteratorBinding("AdjuntarContratoFirmadoVOIterator");
        IdTcaTipoMoneda = IdTcaTipoMoneda + 1;
        voAdjuntarContratoFirmado.getRowAtRangeIndex(0).setAttribute("IdTipoMoneda", IdTcaTipoMoneda);
        logger.warning("id Moneda: " + IdTcaTipoMoneda);
    }

    public void setMontoEscrituradoUIC(RichInputText montoEscrituradoUIC) {
        this.montoEscrituradoUIC = montoEscrituradoUIC;
    }

    public RichInputText getMontoEscrituradoUIC() {
        return montoEscrituradoUIC;
    }

    /**
     * M&eacute;todo para recuperar el bean de taskflow
     * @author Jonathan Ruiz
     * @return FormalizacionBean Bean usado en el taskflow de formalizaci&oacute;n
     */
    private FormalizacionBean getFormalizacionManagedBean() {
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        return formalizacionBean;
    }

    /**
     * M&eacute;todo para recuperar el monto escriturado
     * @return oracle.jbo.domain.Number - monto escriturado
     */
    private oracle.jbo.domain.Number obtenerMontoEscriturado() {
        oracle.jbo.domain.Number montoEscriturado = null;
        String strMontoEscriturado = obtenerMontoEscrituradoStr();
        try {
            if (strMontoEscriturado != null) {
                double dNewValue = NumberFormat.getInstance().parse(strMontoEscriturado).doubleValue();
                montoEscriturado = new oracle.jbo.domain.Number(dNewValue);
            }
        } catch (Exception e) {
            logger.warning("No se pudo obtener el valor numerico de Monto Escriturado");
        }
        return montoEscriturado;
    }

    /**
     * M&eacute;todo para recuperar el monto escriturado
     * @return oracle.jbo.domain.Number - monto escriturado
     */
    private Double obtenerMontoEscrituradoDouble() {
        Double montoEscriturado = null;
        String strMontoEscriturado = obtenerMontoEscrituradoStr();
        try {
            if (strMontoEscriturado != null) {
                montoEscriturado = NumberFormat.getInstance().parse(strMontoEscriturado.trim()).doubleValue();
                //double dNewValue = NumberFormat.getInstance().parse(strMontoEscriturado).doubleValue();
                //montoEscriturado = BigDecimal.valueOf(dNewValue);
            }
        } catch (Exception e) {
            logger.warning("No se pudo obtener el valor numerico de Monto Escriturado");
        }
        return montoEscriturado;
    }

    /**
     * M&eacute;todo para recuperar el monto escriturado como cadena de texto
     * @return java.lang.String - monto escriturado
     */
    private String obtenerMontoEscrituradoStr() {
        String strMontoEscriturado = null;
        if (JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}") != null) {
            strMontoEscriturado = JSFUtils.resolveExpression("#{bindings.MontoEscriturado.inputValue}").toString();
        }

        return strMontoEscriturado;
    }

    /**
     * M&eacute;todo para recuperar el total de la suma de todas las l&iacute;neas de cr&eacute;dito
     * @return java.math.BigDecimal - monto total
     */
    private BigDecimal obtenerMontoTotal() {
        BigDecimal montoTotal = null;
        try {
            if (ADFUtils.getBoundAttributeValue("MontoTotal") != null) {

                logger.warning("Valor de MontoTotal: " + ADFUtils.getBoundAttributeValue("MontoTotal").toString());

                if (!ADFUtils.getBoundAttributeValue("MontoTotal").toString().isEmpty()) {

                    String strValue = ADFUtils.getBoundAttributeValue("MontoTotal").toString();
                    double dNewValue = NumberFormat.getInstance().parse(strValue).doubleValue();
                    montoTotal = BigDecimal.valueOf(dNewValue);
                }
            } else {
                logger.warning("El valor de MontoTotal es NULL");
            }
        } catch (Exception e) {
            logger.severe("Error al leer y convertir MontoTotal", e);
            montoTotal = null;
        }
        return montoTotal;
    }

    /**
     * M&eacute;todo para recuperar el monto aprobado
     * @return java.math.BigDecimal - monto aprobado
     */
    private BigDecimal obtenerMontoAprobado() {
        logger.warning("Entra en obtenerMontoAprobado.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerMontoPorTipo");
        FormalizacionBean formalizacionBean = getFormalizacionManagedBean();
        BigDecimal montoAprobado = null;
        long lngIdOperacion = 0;

        if (formalizacionBean.getOperacionId() != null) {
            lngIdOperacion = formalizacionBean.getOperacionId().longValue();
            operationBinding.getParamsMap().put("varIdOperacion", lngIdOperacion);
        } else {
            logger.severe("Error al obtener el Id de Operacion desde Managed Bean");
        }
        operationBinding.getParamsMap().put("varTipoMonto",
                                            new Integer(3)); // Monto Aprobado Id = 3, según tabla TCA_TIPO_MONTO_OPERACION
        operationBinding.execute();
        if (operationBinding.getErrors().isEmpty()) {
            // Asignación para Monto aprobado
            montoAprobado = (BigDecimal) operationBinding.getResult();
        } else {
            logger.severe("Error al ejecutar el operation binding obtenerMontoPorTipo");
        }

        return montoAprobado;
    }

    /**
     * M&eacute;todo para validar el monto total
     * @return java.lang.Boolean - true en caso de ser v&aacute;lido
     */
    private Boolean montoTotalValido() {

        logger.warning("Inicia montoTotalValido");

        Boolean esDatosCorrectos = Boolean.TRUE;
        FormalizacionBean formalizacionBean = getFormalizacionManagedBean();
        BigDecimal montoAprobado = obtenerMontoAprobado();
        oracle.jbo.domain.Number montoEscriturado = obtenerMontoEscriturado();
        BigDecimal montoTotal = obtenerMontoTotal();

        //Si no hay monto escriturado la suma del total de las lineas
        //debera ser menor o igual al monto aprobado.
        String stMontoEscriturado = obtenerMontoEscrituradoStr();

        if (stMontoEscriturado == null || stMontoEscriturado == "") {
            //Cuando NO se haya ingresado un monto escriturado
            logger.warning("No se ingreso un monto escriturado.");

            //EX02 - No se agregaron lineas
            if (montoTotal == null) {
                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("Debe agregar al menos una l\u00EDnea de cr\u00E9dito.");
            } else {
                // El monto escriturado deberá ser menor o igual al monto aprobado.
                if ((montoAprobado == null) || (montoTotal.compareTo(montoAprobado) < 1)) {
                    esDatosCorrectos = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("La suma total del monto de cada L\u00EDnea debe corresponder al monto aprobado.");
                }
            }

            logger.warning("montoTotal = " + montoTotal + ", montoAprobado = " + montoAprobado);
        } else {
            // Cuando se haya ingresado un monto escriturado
            logger.warning("Se ingreso un monto escriturado.");

            // EX06 Monto total mayor a monto escriturado.
            if ((montoEscriturado == null) || (montoTotal == null) ||
                (montoTotal.compareTo(montoEscriturado.getBigDecimalValue()) == 1)) {

                esDatosCorrectos = Boolean.FALSE;
                JSFUtils.addFacesErrorMessage("El monto total de las líneas registradas supera el monto escriturado.");
                //JSFUtils.addFacesErrorMessage("La suma total del monto de cada Línea debe corresponder al monto escriturado.");
            }
            logger.warning("montoTotal = " + montoTotal + ", montoEscriturado = " + montoEscriturado);
        }

        logger.warning("Finaliza montoTotalValido. Resultado: " + esDatosCorrectos);
        return esDatosCorrectos;
    }

    /**
     * M&eacute;todo para recuperar una lista de recursos externos
     * por las coincidencias totales o parciales en el nombre del recurso
     * @return java.util.List - lista de recursos externos
     */
    public List obtenerRecursosPorNombre(String nombreRecurso) {
        logger.warning("Dentro obtenerRecursosPorNombre onSuggest nombreRecurso: " + nombreRecurso);
        List<SelectItem> resultItems = new ArrayList<SelectItem>();
        Row[] rowList = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        //        String nombreRecurso = null;
        //        nombreRecurso = (JSFUtils.resolveExpression("bindings.Nombre.inputValue") != null) ?
        //            (String)JSFUtils.resolveExpression("bindings.Nombre.inputValue") : null;
        DCBindingContainer bc = (DCBindingContainer) getBindings();
        try {
            OperationBinding operationBinding = bindings.getOperationBinding("buscarFuentePorNombre");
            operationBinding.getParamsMap().put("nombre", nombreRecurso);
            operationBinding.execute();
            if (operationBinding.getErrors().isEmpty()) {
                // Asignación para Monto aprobado
                rowList = (Row[]) operationBinding.getResult();
                for (Row row : rowList) {
                    resultItems.add(new SelectItem(row.getAttribute("Descripcion")));
                }
            } else {
                logger.severe("Error al ejecutar el operation binding buscarFuentePorNombre");
            }

        } catch (Exception e) {
            logger.severe("Error en obtenerRecursosPorNombre", e);
        }
        logger.warning("Fuera obtenerRecursosPorNombre");
        return resultItems;
    }

    /**
     * M&eacute;todo que finaliza la tarea Definir Recursos Externos
     * @return InvokeActionBean.invokeOperation()
     */
    private String finalizarDefinirRecursosExternos() {
        String returnAction = null;
        // Invocar metodo para cargar documentos en onBase
        cargarDocumentosOnBase();
        // Invocar aprovacion del humman task
        returnAction = approveHumanTask();

        return returnAction;
    }

    /**
     * M&eacute;todo para notificar la validaciones que no pasaron exitosamente
     * @author Jonathan Ruiz
     * @param pantallaEnum enum de la pantalla que notifica
     * @param bundleKey cadena con el key del bundle
     */
    private void notificarValidacion(TareaFormalizacionEnum pantallaEnum, String bundleKey) {
        // Notificamos validacion
        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));

        switch (pantallaEnum) {
        case DEFINIR_RECURSOS_EXTERNOS:
            // Cerramos popup de Confirmar Finalizar Tarea
            getPopupConfirmarFinalizarTarea().hide();
            break;
        }
    }

    /**
     * M&eacute;todo que valida la disponibilidad en todas las lineas
     * @author Jonathan Ruiz
     */
    private boolean existeDisponibilidadLineas() {
        boolean existencia = Boolean.FALSE;

        return existencia;
    }

    /**
     * M&eacute;todo que finaliza la tarea Asignar Recursos
     * @return InvokeActionBean.invokeOperation()
     */
    private String finalizarAsignarRecursos() {
        String returnAction = null;
        // Propagamos fuentes de las líneas de crédito
        if (propagarFuente()) {
            // Invocar método para cargar documentos en onBase
            cargarDocumentosOnBase();
            // Invocar aprovacion del humman task
            returnAction = approveHumanTask();
        }
        return returnAction;
    }

    /**
     * M&eacute;todo que llama a la operaci&oacute;n de aprovaci&oacute;n del Human Task
     * @author Jonathan Ruiz
     */
    private String approveHumanTask() {
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        logger.warning("into approveHumanTask.");
        invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        returnAction = invokeActionBean.invokeOperation();

        return returnAction;
    }

    /**
     * M&eacute;todo para recuperar la URL de la resoluci&oacute;n de aprobaci&oacute;n
     * @author Jonathan Ruiz
     * @return URL de resoluci&oacute;n
     */
    @SuppressWarnings("unchecked")
    public String getUrlLotusNoteResolucion() {
        logger.warning("Entra en getUrlLotusNoteResolucion.");
        String numeroResolucion = null;
        String urlLotusNoteResolucion = null;

        //        OperationBinding numResolucionBinding = ADFUtils.findOperation("obtenerUltimoNumeroResolucion");
        //        numResolucionBinding.getParamsMap().put("idOperacion", getFormalizacionManagedBean().getIdOperacion());
        //        Object numResolucionObject = numResolucionBinding.execute();
        //        numeroResolucion = (String)numResolucionObject;
        //logger.info("++++++++++++++++++++++ numeroResolucion: " + numeroResolucion);

        numeroResolucion = getFormalizacionManagedBean().getNumeroResolucion();
        logger.warning("Numero de resolucion obtenida : " + numeroResolucion);

        if (numeroResolucion != null) {
            numeroResolucion = formatResolucionToUrl(numeroResolucion);

            OperationBinding urlBinding = ADFUtils.findOperation("getValorWsdl");
            Object urlObject = urlBinding.execute();
            String urlResolucion = (String) urlObject;

            urlLotusNoteResolucion = MessageFormat.format(urlResolucion, numeroResolucion);

            //urlLotusNoteResolucion = "https://www.google.com.mx/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
            verDocumentoButtonUIC.setDestination(urlLotusNoteResolucion);
        }

        //logger.info("++++++++++++++++++++++ urlLotusNoteResolucion: " + urlLotusNoteResolucion);
        return urlLotusNoteResolucion;
    }

    private String formatResolucionToUrl(String resolucion) {
        if (resolucion != null)
            resolucion = resolucion.replace("/", "-");
        return resolucion;
    }

    public RichButton getVerDocumentoButtonUIC() {
        return verDocumentoButtonUIC;
    }

    public void setVerDocumentoButtonUIC(RichButton verDocumentoButtonUIC) {
        this.verDocumentoButtonUIC = verDocumentoButtonUIC;
    }

    public RichPopup getPopupConfirmarMasInformacion() {
        return popupConfirmarMasInformacion;
    }

    public void setPopupConfirmarMasInformacion(RichPopup popupConfirmarMasInformacion) {
        this.popupConfirmarMasInformacion = popupConfirmarMasInformacion;
    }

    /**
     * M&eacute;todo para escuchar el evento del bot&oacute;n de m&aacute;s informaci&oacute;n
     * @author Jonathan Ruiz
     * @param actionEvent
     */
    public void adjuntarContratoMasInformacionAction() {
        logger.log(ADFLogger.WARNING, "Inside adjuntarContratoMasInformacionActionListener.");
        FormalizacionBean formalizacionBean = getFormalizacionManagedBean();

        // Se valida que se haya ingresado un comentario
        if (validateComentario(Long.valueOf(formalizacionBean.getIdOperacion()), formalizacionBean.getIdTarea(),
                               getInstanciaTarea())) {
            // Abre popup de confirmar más informacion en todas las pantallas
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupConfirmarMasInformacion().show(popupHints);
        } else {
            // Se notifica que se debe ingresar un comentario
            notificarValidacion(TareaFormalizacionEnum.DEFINIR_RECURSOS_EXTERNOS,
                                BundleConstants.COMENTARIO_PARA_CONTINUAR);
        }
    }


    public void registrarFechaVigenciaMasInformacionAction() {
        logger.warning("Inside registrarFechaVigenciaMasInformacionAction.");
        FormalizacionBean formalizacionBean = getFormalizacionManagedBean();

        // Se valida que se haya ingresado un comentario
        if (validateComentario(Long.valueOf(formalizacionBean.getIdOperacion()), formalizacionBean.getIdTarea(),
                               getInstanciaTarea())) {
            // Abre popup de confirmar más informacion en todas las pantallas
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupConfirmarMasInformacion().show(popupHints);
        } else {
            // Se notifica que se debe ingresar un comentario
            notificarValidacion(TareaFormalizacionEnum.DEFINIR_RECURSOS_EXTERNOS,
                                BundleConstants.COMENTARIO_PARA_CONTINUAR);
        }
    }


    /**
     * M&eacute;todo para ir a la navegaci&oacute;n de m&aacute;s informaci&oacute;n
     * @author Jonathan Ruiz
     * @return InvokeActionBean.invokeOperation()
     */
    public String aceptarMasInformacionContratoFirmadoAction() {
        logger.log(ADFLogger.WARNING, "Inside aceptarMasInformacionContratoFirmadoAction.");

        // Establecemos el atributo del payload requiereRevisionCORE a false
        actualizarPayloadElement("requiereRevisionCORE", Boolean.FALSE);

        // Cerramos popup de Confirmar Mas Informacion
        getPopupConfirmarMasInformacion().hide();

        return approveHumanTask();
    }

    /**
     * Método para cancelar la acción de más información de
     * todas las tareas de formalizaci&oacute;n
     * @author Jonathan Ruiz
     * @param actionEvent
     */
    public void cancelarMasInformacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING,
                   "Inside cancelarMasInformacionContratoFirmadoActionListener: " + actionEvent.getComponent().getId());

        String idTarea = getFormalizacionManagedBean().getIdTarea();
        TareaFormalizacionEnum tareaEnum = TareaFormalizacionEnum.lookup(idTarea);

        // Cerramos popup de confirmar mas informacion en todas las pantallas
        switch (tareaEnum) {
        case ELABORAR_CONTRATO_FIRMADO:
            getPopupConfirmarFinalizarTarea().hide();
            break;

        default:
            logger.log(ADFLogger.WARNING,
                       "El id de la tarea {0} no es esta registrada en PantallaFormalizacionEnum: ".replace("{0}",
                                                                                                            idTarea));
            break;
        }
    }


    public String aceptarMasInformacionRegistrarFechaVigencia() {
        logger.warning("Inside aceptarMasInformacionRegistrarFechaVigencia.");

        // Cerramos popup de Confirmar Mas Informacion
        getPopupConfirmarMasInformacion().hide();

        return approveHumanTask();
    }

    public String cancelarMasInformacionRegistrarFechaVigencia() {
        logger.warning("Inside cancelarMasInformacionRegistrarFechaVigencia.");
        // Cerramos popup de Confirmar Mas Informacion
        getPopupConfirmarMasInformacion().hide();
        return null;
    }

    /**
     * Método para validar la TCC 101 - Fecha de vencimiento
     * @author Jonathan Ruiz
     * @return true en caso de ser valida
     */
    @SuppressWarnings("unchecked")
    private boolean tcc101Valida() {
        boolean valida = Boolean.TRUE;
        AttributeBinding fechaFirmaBinding = ADFUtils.findControlBinding("FechaFirma");
        Timestamp fechaVencimiento = obtenerFechaVencimientoBinding();

        //En caso de existir la fecha de vencimiento
        if (fechaVencimiento != null) {
            //La fecha de firma no debe ser mayor a la fecha de vencimiento
            Timestamp fechaFirma = DateTimeUtil.stringToTimestamp(fechaFirmaBinding.getInputValue().toString());

            if (fechaFirma.compareTo(fechaVencimiento) > 0) {
                valida = Boolean.FALSE;
            }
        }

        //logger.info(">>>>>>>>>>> fechaVencimiento: " + fechaVencimiento);
        //logger.info(">>>>>>>>>>> valida: " + valida);
        return valida;
    }

    /**
     * Método para validar si se adjunto el contrato firmado y se
     * ha ingresado la fecha de firma
     * @author Jonathan Ruiz
     * @return true en caso de ser verdadero
     */
    private boolean contratoAdjuntadoConFirma() {
        boolean verdadero = Boolean.FALSE;
        FormalizacionBean formalizacionBean = getFormalizacionManagedBean();
        AttributeBinding fechaFirmaAT = ADFUtils.findControlBinding("FechaFirma");
        boolean contratoFirmadoAdjuntado =
            validateDocumento(Long.valueOf(formalizacionBean.getIdOperacion()),
                              Integer.valueOf(FenixConstants.DOCUMENTO_CONTRATO_FIRMADO));
        boolean fechaFirmaIngresada =
            (null != fechaFirmaAT.getInputValue() && !fechaFirmaAT.getInputValue().toString().trim().equals(""));

        contratoFirmadoAdjuntado = false;
        if (contratoFirmadoAdjuntado && fechaFirmaIngresada) {
            verdadero = Boolean.TRUE;
        }

        //        logger.info(">>>>>>>>>>>>>>>>>> contratoFirmadoAdjuntado: " + contratoFirmadoAdjuntado);
        //        logger.info(">>>>>>>>>>>>>>>>> fechaFirmaIngresada: " + fechaFirmaIngresada);
        //        logger.info(">>>>>>>>>>>>>>>>> verdadero: " + verdadero);

        return verdadero;
    }

    /**
     * Método para recuperar la fecha de vencimiento por binding
     * @author Jonathan Ruiz
     * @return true en caso de ser verdadero
     */
    @SuppressWarnings("unchecked")
    private Timestamp obtenerFechaVencimientoBinding() {
        FormalizacionBean formalizacionBean = getFormalizacionManagedBean();
        Long idOperacion = Long.valueOf(formalizacionBean.getIdOperacion());
        Timestamp fechaVencimiento = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerFechaTerminoPorId");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idTcaTermino", ID_TCC_TERMINO_T604);

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            if (operationBinding.getResult() != null) {
                Date fechaVencimientoDate = (Date) operationBinding.getResult();
                fechaVencimiento = DateTimeUtil.dateToTimestamp(DateTimeUtil.dateToTimestamp(fechaVencimientoDate));
            }
        }

        return fechaVencimiento;
    }

    public void confirmarFinalizarTareaComunicarVigenciaContratoActionListener(ActionEvent actionEvent) {
        // Abre popup de confirmar finalizar tarea en todas las pantallas
        logger.warning("Inside confirmarFinalizarTareaComunicarVigenciaContratoActionListener");
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        String msg = null;
        //Para poder finalizar la tarea "Comunicar vigencia de contrato"
        //es necesario contar con el documento de tipo "Evidencia de la vigencia del contrato"
        //if(validateDocumento(Long.valueOf(formalizacionBean.getIdOperacion()),
        //                      Integer.valueOf(FenixConstants.DOCUMENTO_EVIDENCIA_DE_LA_VIGENCIA_DEL_CONTRATO))) {

        getPopupConfirmarFinalizarTarea().show(popupHints);

        //}else{
        //   msg = getStringFromBundle("COMUNICAR_VIGENCIA_CONTRATO_MSG_VALIDAR_DOCUMENTO");
        //   logger.warning("Validacion incumplida: " + msg);
        //   JSFUtils.addFacesErrorMessage(msg);
        //}
        logger.warning("Leave confirmarFinalizarTareaComunicarVigenciaContratoActionListener");
    }

    public String aceptarFinalizarTareaComunicarVigenciaContratoAction() {
        logger.warning("Into aceptarFinalizarTareaComunicarVigenciaContratoAction");
        InvokeActionBean invokeActionBean = null;
        String returnAction = null;

        // Invocar método para cargar documentos en onBase
        cargarDocumentosOnBase();

        invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        returnAction = invokeActionBean.invokeOperation();

        // Cerramos popup de Confirmar Finalizar Tarea
        getPopupConfirmarFinalizarTarea().hide();
        logger.warning("Leave aceptarFinalizarTareaComunicarVigenciaContratoAction, return : " + returnAction);
        return returnAction;
    }

    public Boolean validarFuenteSinDisponibilidad() {
        DCIteratorBinding iterator = null;
        Boolean result = Boolean.FALSE;
        List<String> idLineaPasivaList = new ArrayList<String>();
        BindingContainer bindings = ADFUtils.getBindingContainer();

        logger.warning("Dentro validarFuenteSinDisponibilidad");

        try {
            iterator = ADFUtils.findIterator("FuentesAsignadasVOIterator");

            for (Row row : iterator.getAllRowsInRange()) {
                idLineaPasivaList.add((String) row.getAttribute("IdLineaPasiva"));
            }

            OperationBinding operationBinding = bindings.getOperationBinding("validarFuenteSinDisponibilidad");
            operationBinding.getParamsMap().put("idLineaPasivaList", idLineaPasivaList);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {
                if (operationBinding.getResult() != null) {
                    result = (Boolean) operationBinding.getResult();
                }
            }

        } catch (Exception e) {
            logger.severe("Error al recuperar FuentesAsignadasVOIterator : " + e);
        }

        logger.warning("Fuera validarFuenteSinDisponibilidad, return : " + result);

        return result;
    }


    public void confirmarFinalizarTareaAsignacionRecursosActionListener(ActionEvent actionEvent) {
        // Abre popup de confirmar finalizar tarea "AsignarRecursos"
        logger.warning("Dentro confirmarFinalizarTareaAsignacionRecursosActionListener");
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        String msg = null;
        //Si retorna true significa que existen fuentes sin disponibilidad en el monto_disponible
        if (validarFuenteSinDisponibilidad()) {
            //solicitar un comentario para poder finalizar la tarea
            if (validateComentario(Long.valueOf(getFormalizacionManagedBean().getIdOperacion()),
                                   getFormalizacionManagedBean().getIdTarea(), getInstanciaTarea())) {
                //muestra popup
                getPopupConfirmarFinalizarTarea().show(popupHints);
            } else {
                // muestra mensaje solicitando el comentario para poder finalizar la tarea
                msg = getStringFromBundle("AGREGAR_UN_COMENTARIO_ASIGNACION_RECURSOS");
                logger.warning("Validacion incumplida: " + msg);
                JSFUtils.addFacesErrorMessage(msg);
            }
        } else {
            //muestra popup,sigue flujo normal
            getPopupConfirmarFinalizarTarea().show(popupHints);
        }
    }

    public Boolean validarFondosAsignados() {
        logger.warning("Dentro validarFondosAsignados");
        Boolean resultado = Boolean.FALSE;
        DCIteratorBinding iterator = null;
        BigDecimal porcentajeTotal = new BigDecimal(0);
        try {
            iterator = ADFUtils.findIterator("FuentesExternasVOIterator");
            for (Row row : iterator.getAllRowsInRange()) {
                BigDecimal porcentajeAux = null;
                porcentajeAux =
                    row.getAttribute("Porcentaje") != null ? (BigDecimal) row.getAttribute("Porcentaje") : null;
                if (porcentajeAux != null) {
                    porcentajeTotal = porcentajeTotal.add(porcentajeAux);
                }
            }

            if (porcentajeTotal.compareTo(new BigDecimal("100")) == 0) {
                logger.warning("porcentaje total es igual a 100");
                resultado = Boolean.TRUE;
            } else {
                logger.warning("porcentaje total es diferente a 100");
                resultado = Boolean.FALSE;
            }

        } catch (Exception ex) {
            logger.severe("Error en validarFondosAsignados", ex);
            resultado = Boolean.FALSE;
        }

        logger.warning("Fuera validarFondosAsignados resultado : " + resultado);
        return resultado;
    }

    /**
     *Este metodo se deja de utilizar en el componente FechaFirma de la tarea Adjuntar Contrato Firmado,
     * ya que de acuerdo con QA, la fecha de creación de la operación es la que se debe cambiar a horas
     * en cero y asi ya no se modificarian las fechas que se muestran en la línea del tiempo del gestor
     * de operciones.
     *
     * @param valueChangeEvent
     */
    public void fechaFirmaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("Dentro de fechaFirmaValueChangeListener");
        String fechaFirmaStr = null;
        Timestamp fechaFirma = null;
        DCIteratorBinding voAdjuntarContratoFirmado = null;
        Timestamp fechaActual = new java.sql.Timestamp(System.currentTimeMillis());
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // update model values
        voAdjuntarContratoFirmado =
            ADFUtils.getDCBindingContainer().findIteratorBinding("AdjuntarContratoFirmadoVOIterator");
        logger.warning("fechaFirma iterator :" +
                       voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("FechaFirma"));
        fechaFirma = (Timestamp) voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("FechaFirma");
        logger.warning("fechaFirmaImputValue:" + fechaFirma);
        logger.warning("fechaActual:" + fechaActual);
        logger.warning("fechaActual horas :" + fechaActual.getHours());
        logger.warning("fechaActual minutos : " + fechaActual.getMinutes());
        logger.warning("fechaActual segundos : " + fechaActual.getSeconds());
        fechaFirma.setHours(fechaActual.getHours());
        fechaFirma.setMinutes(fechaActual.getMinutes());
        fechaFirma.setSeconds(fechaActual.getSeconds());
        logger.warning("fechaFirmaImputValue con horas,minutos,segundos :" + fechaFirma);
        voAdjuntarContratoFirmado.getRowAtRangeIndex(0).setAttribute("FechaFirma", fechaFirma);
        logger.warning("Fuera de fechaFirmaValueChangeListener");
    }

    private Boolean existenCondicionesPorIdOperacion() {
        logger.warning("Entra en existenCondicionesPorIdOperacion");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("existenCondicionesPorIdOperacion");
        FormalizacionBean formalizacionBean = getFormalizacionManagedBean();
        Boolean existenCondiciones = null;
        Long idOperacion = null;

        if (formalizacionBean.getOperacionId() != null) {
            idOperacion = formalizacionBean.getOperacionId().longValue();
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
        } else {
            logger.severe("Error al obtener el Id de Operacion desde Managed Bean");
        }

        operationBinding.execute();
        if (operationBinding.getErrors().isEmpty()) {
            // Asignación para Monto aprobado
            existenCondiciones = (Boolean) operationBinding.getResult();
        } else {
            logger.severe("Error al ejecutar el operation binding existenCondicionesPorIdOperacion");
        }

        logger.warning("Fuera de existenCondicionesPorIdOperacion,existenCondiciones :" + existenCondiciones);
        return existenCondiciones;
    }

     /**
        * Procedimientos para realiza la verificacion de documentos obligatorios en cada una de las tareas en el proceso de
        * Formalizacion.
        * hernandeza@27ene2020
        * @Traza @kruger 17/05/2021 - us 802
        * @PC05SolicitarElaboradorDocumento (Finalizar Tarea)
        * */
    public void finalizarTareaFormalizacion(ActionEvent actionEvent) {
     
        logger.warning("Inicia finalizarTareaFormalizacion.");
        String accion = "finalizarTarea";
        validarDocumentosTareaFormalizacion(actionEvent, accion);
        logger.warning("Termina finalizarTareaFormalizacion.");
    }

    /**
     * Método que realiza función de enviar tarea a COFI
     * @funtion: replica la funcionalidad de finalizar tarea
     * @restriccciones: Se ejecuta logica en el constructor para determianr mostrar o no el botón
     * @since 04/05/2021
     * @by Raul Garcia
     */
    public void enviarCofi(ActionEvent actionEvent) {
        logger.warning("Inicia enviarCofiFormalizacion " + actionEvent.getComponent().getId());
        String accion = "enviarCofi";
        validarDocumentosTareaFormalizacion(actionEvent, accion);
        logger.warning("Termina enviarCofiFormalizacion.");
    }

    /**
     * Método que realiza función de enviar tarea a Responsable de la operación
     * @funtion: Enviar outcome y avanzar dos tareas en el proceso de BPM
     * @restriccciones: Se ejecuta logica en el constructor para determianr mostrar o no el botón
     * @since 04/05/2021
     * @by Raul Garcia
     */
    public void enviarResponsableOper(ActionEvent actionEvent) {
        /**
          * Validar logica de envio a nuevo outcome
          */
        logger.warning("Inicia enviarResponsableOperacionFormalizacion" + actionEvent.getComponent().getId());
        String accion = "enviarResponsableOperacion";
        validarDocumentosTareaFormalizacion(actionEvent, accion);
        logger.warning("Termina enviarResponsableAreaFormalizacion.");
    }


    public void masInformacionTareaFormalizacion(ActionEvent actionEvent) {
        logger.warning("Inicia masInformacionTareaFormalizacion.");
        String accion = "masInformacion";
        validarDocumentosTareaFormalizacion(actionEvent, accion);
        logger.warning("Inicia masInformacionTareaFormalizacion.");
    }
    
    /*
    * @Traza @kruger 17/05/2021 - US 802
    * @PC05SolicitarElaboradorDocumento (Finalizar Tarea)
    * 
     * */
    public void validarDocumentosTareaFormalizacion(ActionEvent actionEvent, String p_accion) {
        logger.warning("Inicia validarDocumentosTareaFormalizacion.");
        
        //Obtiene el Bean de la instancia, con el fin de recueprar todos sus atributos
        FormalizacionBean formalizacionBean = (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");

        InvokeActionBean invokeActionBean = null;
        String returnAction = null;
        String accion = p_accion;
        Integer codigoTarea = Integer.parseInt(formalizacionBean.getIdTarea());
        Long codigoOperacion = Long.parseLong(formalizacionBean.getIdOperacion());
        /* Iverview : Se agrega la validación de tipo de producto para agregar alertas asociadas a la US:802
         * @Team: kruger
         * @developer: Raul Garcia
         * @Date: 17/05/2021
         * */
        Integer  idProducto =  formalizacionBean.getIdProducto();
        
        /*End*/
        
        Boolean isValidDocumentos = Boolean.FALSE;
        Boolean isValidFinalizarTarea = Boolean.TRUE;

        logger.warning("accion: " + accion);
        logger.warning("codigoTarea: " + codigoTarea);
        logger.warning("codigoOperacion: " + codigoOperacion);
        logger.warning("tipo Producto: " + idProducto);
        
        isValidDocumentos = validarDocumentosTarea(codigoOperacion, codigoTarea, accion);

        if (isValidDocumentos) {
            logger.warning("isValidDocumentos Valido");
            /*Overview: Pregunta por la tarea en la que esta parado el proceso de FormalIzación*/
        
        switch (codigoTarea.toString()) {
            case FenixConstants.PC05_SOLICITAR_ELABORACION_DOCUMENTACION:
                logger.warning("Tarea: PC05_SOLICITAR_ELABORACION_DOCUMENTACION");
                if (accion == "finalizarTarea") {
                    
                    invokeFinalizarTarea();
                }
                logger.warning("Finaliza Tarea: PC05_SOLICITAR_ELABORACION_DOCUMENTACION");
                break;
            case FenixConstants.PC05_CUMPLIR_CONDICIONES_FINANCIERAS:
                //Funcionara estable cuando realice la homologación de codigo *Posteriro a las pruebas 07/05/2021
                // KRUGER
                // US2012: *Falata homologar codigo PC05ElaboracionContratos Vs PC05CumplimientoCondicionesFinacieras
                    logger.warning("Tarea: PC05_CUMPLIMIENTO_CONDICIONES_FINANCIERAS");
                    // Envia a EnviarResponsableOperacion
                    if (accion == "finalizarTarea") {
                        invokeFinalizarTarea();
                    }
                    logger.warning("Finaliza Tarea: PC05_SOLICITAR_ELABORACION_DOCUMENTACION");
                    break;
            case FenixConstants.PC05_DEFINIR_RECURSOS_EXTERNOS:
                logger.warning("Tarea: PC05_DEFINIR_RECURSOS_EXTERNOS");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaActionListener(actionEvent);
                }
                logger.warning("Finaliza Tarea: PC05_DEFINIR_RECURSOS_EXTERNOS");
                break;
            case FenixConstants.PC05_ELABORAR_BORRADOR_CONTRATO:
                //Valida en que tarea(Pantalla) se encuentra el usuario
                logger.warning("Tarea: PC05_ELABORAR_BORRADOR_CONTRATO");
                if (accion == "finalizarTarea") {
                    formalizacionBean.setAccionBoton(accion);
                    confirmarFinalizarTareaElaborarBorradorContratoActionListener(actionEvent, accion);
                    /**invokeFinalizarTarea();**/
                } else if (accion == "enviarCofi") {
                    formalizacionBean.setAccionBoton(accion);
                    confirmarFinalizarTareaElaborarBorradorContratoActionListener(actionEvent, accion);
                }else if (accion == "enviarResponsableOperacion") {
                    /*invokeFinalizarTarea();*/
                    formalizacionBean.setAccionBoton(accion);
                    confirmarFinalizarTareaElaborarBorradorContratoActionListener(actionEvent, accion);
                }
                
                logger.warning("Finaliza Tarea: PC05_ELABORAR_BORRADOR_CONTRATO");
                break;
            case FenixConstants.PC05_NEGOCIAR_DOCUMENTACION_CONTRACTUAL:
                logger.warning("Tarea: PC05_NEGOCIAR_DOCUMENTACION_CONTRACTUAL");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaNegociarDocumentacionConClienteActionListener(actionEvent);
                }
                logger.warning("Finaliza Tarea: PC05_NEGOCIAR_DOCUMENTACION_CONTRACTUAL");
                break;
            case FenixConstants.PC05_ELABORAR_CORE_TYC:
                logger.warning("Tarea: PC05_ELABORAR_CORE_TYC");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaActionListener(actionEvent);
                }
                logger.warning("Finaliza Tarea: PC05_ELABORAR_CORE_TYC");
                break;
            case FenixConstants.PC05_ANALIZAR_DOCUMENTACION_CONTRACTUAL:
                logger.warning("Tarea: PC05_ANALIZAR_DOCUMENTACION_CONTRACTUAL");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaActionListener(actionEvent);
                }
                logger.warning("Finaliza Tarea: PC05_ANALIZAR_DOCUMENTACION_CONTRACTUAL");
                break;
            case FenixConstants.PC05_ADJUNTAR_CONTRATO_FIRMADO:
                logger.warning("Tarea: PC05_ADJUNTAR_CONTRATO_FIRMADO");
                if (accion == "finalizarTarea") {
                    //jenamorado 15/07/2021
                   // confirmarFinalizarTareaActionListener(actionEvent);
                    //
                    confirmarFinalizarTareaAdjuntarContratoActionListener(actionEvent,codigoOperacion);
                }
                logger.warning("Finaliza Tarea: PC05_ADJUNTAR_CONTRATO_FIRMADO");
                break;
            case FenixConstants.PC05_REGISTRAR_FECHA_VIGENCIA:
                logger.warning("Tarea: PC05_REGISTRAR_FECHA_VIGENCIA");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaRegFechaVig(actionEvent);
                } else if (accion == "masInformacion") {
                    registrarFechaVigenciaMasInformacionAction();
                }
                logger.warning("Finaliza Tarea: PC05_REGISTRAR_FECHA_VIGENCIA");
                break;
            case FenixConstants.PC05_CUSTODIAR_CONTRATO:
                logger.warning("Tarea: PC05_CUSTODIAR_CONTRATO");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaActionListener(actionEvent);
                }
                logger.warning("Finaliza Tarea: PC05_CUSTODIAR_CONTRATO");
                break;
            case FenixConstants.PC05_REGISTRAR_DATOS_LINEA_CREDITO:
                logger.warning("Tarea: PC05_REGISTRAR_DATOS_LINEA_CREDITO");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaRegistrarLineaCreditoActionListener(actionEvent);
                }
                logger.warning("Finaliza Tarea: PC05_REGISTRAR_DATOS_LINEA_CREDITO");
                break;
            case FenixConstants.PC05_ASIGNAR_RECURSOS:
                logger.warning("Tarea: PC05_ASIGNAR_RECURSOS");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaAsignacionRecursosActionListener(actionEvent);
                }
                logger.warning("Finaliza Tarea: PC05_ASIGNAR_RECURSOS");
                break;
            case FenixConstants.PC05_VALIDAR_DATOS_LINEA_CREDITO:
                logger.warning("Inicia Tarea: PC05_VALIDAR_DATOS_LINEA_CREDITO");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaActionListener(actionEvent);
                }
                logger.warning("Finaliza Tarea: PC05_VALIDAR_DATOS_LINEA_CREDITO");
                break;
            case FenixConstants.PC05_COMUNICAR_VIGENCIA_CONTRATO:
                logger.warning("Tarea: PC05_COMUNICAR_VIGENCIA_CONTRATO");
                if (accion == "finalizarTarea") {
                    confirmarFinalizarTareaComunicarVigenciaContratoActionListener(actionEvent);
                }
                logger.warning("Finaliza Tarea: PC05_COMUNICAR_VIGENCIA_CONTRATO");
                break;
            }
        } else {
            logger.warning("isValidDocumentos NO Valido");
        }
        logger.warning("Finaliza validarDocumentosTareaFormalizacion.");
    }

  /*
   * jenamorado
   * 20/07/2021
   * creacion de validacion de advertencia en la pantalla negociar documentacion contractual
   * */
    public boolean ValidarAdvertenciaFinalizarTareaNegociarDocumentacionAction() {
        logger.warning("ValidarAdvertenciaFinalizarTareaNegociarDocumentacionAction " );  
    
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
 

        
        BindingContainer bindings = ADFUtils.getBindingContainer();
 
        HashMap<String, Boolean> validarCondicionesFormalizacion = null;
        Boolean esCondicionesFormalizar = Boolean.FALSE;
        Boolean esPlazoFormalizacion = Boolean.FALSE;
        //lista de advertencia
        //jenamorado 15/07/2021
        Boolean Resultado =Boolean.TRUE;
        Integer codigoTarea = Integer.parseInt(formalizacionBean.getIdTarea());
        listaAdvertencias = new ArrayList<String>();
  

                validarCondicionesFormalizacion =
                    (HashMap<String, Boolean>) validarCondicionesFormalizacion(formalizacionBean.getOperacionId().longValue());

                if (validarCondicionesFormalizacion != null) {

                    esCondicionesFormalizar =
                        validarCondicionesFormalizacion.get("validaCondiciones") == null ? Boolean.FALSE :
                        validarCondicionesFormalizacion.get("validaCondiciones");
                    esPlazoFormalizacion =
                        validarCondicionesFormalizacion.get("validaFechaFormalizacion") == null ? Boolean.FALSE :
                        validarCondicionesFormalizacion.get("validaFechaFormalizacion");


                   /* // RN2: El sistema valida que las condiciones a formalizar no se han cumplido.
                    if (!esCondicionesFormalizar)
                        JSFUtils.addFacesErrorMessage("Para finalizar la tarea, las condiciones a formalizar de la operación deben cumplirse.");*/     
                   logger.warning("Plazo de Formalizacion: "+esPlazoFormalizacion.toString() );

    
                   
                    if (!esPlazoFormalizacion){
                    
                             logger.warning("Constante de tarea: "+FenixConstants.PC05_ADJUNTAR_CONTRATO_FIRMADO );           
                if (FenixConstants.PC05_ADJUNTAR_CONTRATO_FIRMADO.equals (codigoTarea.toString())) {
                            Resultado =Boolean.FALSE;
                            logger.warning("Entro al error codigo de tarea: "+codigoTarea.toString() );  
                             JSFUtils.addFacesErrorMessage("No es posible finalizar la tarea el plazo de formalizar la operación se ha vencido.");
                            
                        }
                        else{
                             listaAdvertencias.add("El plazo de formalizar la operación se ha vencido.");
                             logger.warning("Entro a la Advertencia codigo de tarea: "+codigoTarea.toString() );  
                         }}
         
                }
                
          return Resultado;      
   
    }
    
    /*
     * jenamorado
     * 29/07/2021
     * creacion de validacion de advertencia en la pantalla abjuntar Contrato firma
     * */
    public boolean ValidarFechasFinalizarTareaAdjuntarContratoFirmaAction() {
        FormalizacionBean formalizacionBean =
            (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");
        DCIteratorBinding voAdjuntarContratoFirmado = null;
        Integer codigoTarea = Integer.parseInt(formalizacionBean.getIdTarea());
        voAdjuntarContratoFirmado =
            ADFUtils.getDCBindingContainer().findIteratorBinding("AdjuntarContratoFirmadoVOIterator");
        Date fechaFirma;
        Date fechaVencimiento= (Date) obtenerFechaVencimientoBinding() ;
        fechaFirma =  (Date) voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("FechaFirma");
        Boolean Resultado =Boolean.TRUE;

            // RN1: La fecha de Vencimiento deberá ser mayor ó igual a la fecha de firma del contrato.
            if (fechaVencimiento.compareTo(fechaFirma) >= 0) {
                logger.warning("Fecha de Vencimiento es mayor a fecha de firma. Fecha de vencimiento: " +
                               fechaVencimiento.toString() + "Fecha de Firma: " + fechaFirma);
               
            } else {
                logger.warning("Fecha de Vencimiento es menor a fecha de firma. Fecha de vencimiento: " +
                               fechaVencimiento.toString() + "Fecha de Firma: " + fechaFirma);
                Resultado =Boolean.FALSE;
                logger.warning("Entro al error codigo de tarea: "+codigoTarea.toString() );  
                 JSFUtils.addFacesErrorMessage("No es posible finalizar la tarea el plazo de formalizar la operación se ha vencido.");
              
            }

       
        return Resultado;
    }
    private void guardarFechasEnContrato(Long idOperacion, String instanciaProceso) {
        logger.warning("guardarFechasEnContrato");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        DCIteratorBinding voAdjuntarContratoFirmado = null;

        // Asignación de variables
        voAdjuntarContratoFirmado =
            ADFUtils.getDCBindingContainer().findIteratorBinding("AdjuntarContratoFirmadoVOIterator");
        logger.warning("idOperacion: "+idOperacion);
        logger.warning("instanciaProceso: "+instanciaProceso);
        // Actualizamos fechas y lo asignamos al Contrato correspondiente
        operationBinding = bindings.getOperationBinding("actualizarFechas");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
        operationBinding.getParamsMap().put("fechaFirma",
                                            voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("FechaFirma"));
        operationBinding.getParamsMap().put("fechaVigencia",
                                            voAdjuntarContratoFirmado.getRowAtRangeIndex(0).getAttribute("FechaVigencia"));
      
       

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            logger.warning("Manejo de errores: " + operationBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
    }
/*
 * Jenamorado 07/09/2021
 * obtener idcontrato 
 * */
    public Long ObtenerContratoporIntancia(){
        Long idContrato=null;
        
            logger.log(ADFLogger.WARNING, "Inside ObtenerContratoporIntancia.");
            FormalizacionBean formalizacionBean =
                (FormalizacionBean) JSFUtils.resolveExpression("#{pageFlowScope.FormalizacionManagedBean}");


                //recuperar el ID de CONTRATO
                BindingContainer bindings = getBindings();
                OperationBinding operationBinding;
                operationBinding = bindings.getOperationBinding("getIdContratoByOperacionInstanciaProceso");
                operationBinding.getParamsMap().put("idOperacion", formalizacionBean.getIdOperacion());
                operationBinding.getParamsMap().put("instanciaProceso", formalizacionBean.getInstanciaProceso());
                operationBinding.execute();
                idContrato =( null==operationBinding.getResult()? null : (Long) operationBinding.getResult());
            logger.log(ADFLogger.WARNING, "End ObtenerContratoporIntancia.");
        return idContrato;
        }

}
