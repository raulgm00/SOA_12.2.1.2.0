package pc06evaluaciongenerichumantask.beans;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.bcie.correobo.Param;
import org.bcie.correobo.Usuario;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.dto.CorreoElectronicoDTO;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class EvaluacionesActionsBean extends FenixActionBean{
   
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(EvaluacionesActionsBean.class);
    public static final String BUNDLE = "pc06evaluaciongenerichumantask.PC06EvaluacionGenericHumanTaskBundle";
    
    public static final int EVALUACION_MEDIO_TERMINO = 2;
    public static final int EVALUACION_EX_POST = 3;
    
    public static final String FENIX_ANALISTA_ODE = "FENIX_ANALISTA_ODE";
    
    @SuppressWarnings("oracle.jdeveloper.java.field-not-serializable")
    private RichPopup popupConfSolicitarAjustesARespuestas;
    private RichPopup popupConfFinalizarTarea;
    private RichPopup popupConfSolicitarAjustesEnDiseno;
    private RichPopup popupConfPublicarInforme;
    private RichPopup popupConfSolicitarAjustes;
    private RichPopup popupConfRequiereValidarDiseno;
    // SPS | 05/12/2019 | Se anexa para atender T14286
    /* ==============      CONSTANTES  CA     ============= */
    private static final String ID_PANELCMP = "pgCAEditable";
    private static final String ID_SECTOR = "socSCTREd";
    private static final String ID_APORTE = "socAPRTEd";
    private static final String ID_CATEGORIA = "socCATEd";
    private static final String ID_SUBCATEGORIA = "socSUBCEd";
    private static final String ID_PANELPADRE = "pglCmpCAPE";
    private static final String ID_LBL_SECTOR = "lblSector1";
    private ArrayList<String> erroresCA;
    //---------------------------------------------------------
    public EvaluacionesActionsBean() {
        super();
    }
    
    private String getStringFromBundle(String psKey)
    {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null)
        {
            txt = resourceBundle.getString(psKey);
        }

        return txt;
    }
    
    private Long getIdOperacion()
    {   
        EvaluacionesBean evaluacionesBean = (EvaluacionesBean) 
            JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");

        if (null != evaluacionesBean.getIdOperacion() && evaluacionesBean.getIdOperacion().trim().length() > 0)
        {
            return Long.parseLong(evaluacionesBean.getIdOperacion());
        }

        return 0L;
    }
    
    private Integer getCodigoTarea()
    {
        EvaluacionesBean evaluacionesBean = (EvaluacionesBean)
            JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");

        if (null != evaluacionesBean.getIdTarea()&& evaluacionesBean.getIdTarea().trim().length() > 0)
        {
            return Integer.parseInt(evaluacionesBean.getIdTarea());
        }

        return 0;
    }
    
    private Integer getIdProceso()
    {
        EvaluacionesBean evaluacionesBean = (EvaluacionesBean)
            JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");

        if (null != evaluacionesBean.getIdProceso())
        {
            return evaluacionesBean.getIdProceso();
        }

        return 0;
    }
    
    //Se comenta ya que tipoEvaluacion es String no se puede convertir a Integer
    //Para evitar que se siga usando este metodo 
//    private Integer getTipoEvaluacion()
//    {
//        EvaluacionesBean evaluacionesBean = (EvaluacionesBean)
//            JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");
//
//        if (null != evaluacionesBean.getTipoEvaluacion()&& evaluacionesBean.getTipoEvaluacion().trim().length() > 0)
//        {
//            return Integer.parseInt(evaluacionesBean.getTipoEvaluacion());
//        }
//
//        return 0;
//    }

   // SPS | 05/12/2019 | Se modifica para atender T14286 
    public String invokeFinalizarTarea() {
        
        if (validacionesCA()) {
            LOGGER.log(ADFLogger.WARNING, "INTO invokeFinalizarTarea.");
            EvaluacionesBean evaluacionesBean =
                (EvaluacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");
            //String bundleKeyError = "";
            List<String> bundleKeyError = new ArrayList<String>();
            Boolean esValidaFinalizarTarea = Boolean.TRUE;
            Boolean esValidaDocumento = Boolean.TRUE;
            Boolean esValidaPorcentaje = Boolean.TRUE;
            Boolean esValidaValor = Boolean.TRUE;
            
            Integer idTarea = null;
            try{
                if(evaluacionesBean.getIdTarea() != null){
                    idTarea = Integer.valueOf(evaluacionesBean.getIdTarea());    
                }else{
                    LOGGER.warning("Id de Tarea del Managed Bean es NULL");
                }
            }catch(Exception e){
                LOGGER.warning("No se pudo obtener el Id de Tarea");
            }

            //int codigoTarea = getCodigoTarea();
            try {
                switch (evaluacionesBean.getIdTarea()) {
                case FenixConstants.PC06_GENERARCUESTIONARIOS:
                    esValidaFinalizarTarea = validarAgregoEvaluaciones(); //TODO Check this validation
                    bundleKeyError.add("MSG_EVALUACION_NO_VALIDA");
                    break;
                case FenixConstants.PC06_VALIDARDISENOCUESTIONARIOS:
                    evaluacionesBean.setFinalizarTarea(getStringFromBundle("CONFIRMAR_FINALIZAR"));
                    break;
                case FenixConstants.PC06_GENERARINFORMES:
                    if (evaluacionesBean.getTipoEvaluacion() != null &&
                        evaluacionesBean.getTipoEvaluacion().trim().length() > 0) {
                        if (evaluacionesBean.getTipoEvaluacion().equalsIgnoreCase("IBCIE")) {
                            esValidaFinalizarTarea = validateDocumento(getIdOperacion(), 
                                                                       FenixConstants.DOCUMENTO_I_BCIE,
                                                                       idTarea);
                            bundleKeyError.add("MSG_DOC_IBCIE");
                        }
                        if (evaluacionesBean.getTipoEvaluacion().equalsIgnoreCase("SIEMAS")) {
                            esValidaFinalizarTarea = validateDocumento(getIdOperacion(), 
                                                                       FenixConstants.DOCUMENTO_SIEMAS,
                                                                       idTarea);
                            bundleKeyError.add("MSG_DOC_SIEMAS");
                        }
                    }else{
                        LOGGER.log(ADFLogger.WARNING, "Error al finalizar tarea.");
                        esValidaFinalizarTarea = Boolean.FALSE;
                        bundleKeyError.add("Error, no se ingreso tipo de evaluacion.");
                    }
                    break;
                case FenixConstants.PC06_COMPLETARCUESTIONARIOS:
                    LOGGER.log(ADFLogger.WARNING, "INTO PC06_COMPLETARCUESTIONARIOS.");
                    esValidaFinalizarTarea = validarPorcentajeEvaluaciones(); //TODO Check this validation
                    //FNXII-2717
                    bundleKeyError.add("MSG_EVALUACION_COMPLETAR_CUESTIONARIOS_FINALIZAR_TAREA");
                    break;
                case FenixConstants.PC06_VALIDARCUESTIONARIOS:
                    LOGGER.log(ADFLogger.WARNING, "INTO PC06_VALIDARCUESTIONARIOS.");                                        
                    esValidaFinalizarTarea =  validarEstadoEvaluacion();
                    //FNXII-2719
                    bundleKeyError.add("MSG_EVALUACION_VALIDAR_CUESTIONARIOS_COMPLETADOS_FINALIZAR_TAREA");
                    evaluacionesBean.setFinalizarTarea(getStringFromBundle("CONFIRMAR_FINALIZAR"));
                    break;
                case FenixConstants.PC06_REVISARINFORMES:

                    break;
                case FenixConstants.PC06_REVISAR_DISENIO_CUESTIONARIO_IBCIE:
                    LOGGER.log(ADFLogger.WARNING, "INTO PC06_REVISAR_DISENIO_CUESTIONARIO_IBCIE");
                    esValidaFinalizarTarea = Boolean.TRUE;
                    break;
                case FenixConstants.PC06_VALIDAR_DISENO_CUESTIONARIOS_IBCIE:
                    evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_01_VALIDAR_DISENIO_CUESTIONARIO"));
                    break;
                case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SUPERVISION_IBCIE:
                    LOGGER.log(ADFLogger.WARNING, "INTO PC06_COMPLETAR_CUESTIONARIO_SUPERVISION_IBCIE.");
                    evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_01_COMPLETAR_CUESTIONARIO"));
                    break;
                case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_IBCIE:
                    LOGGER.log(ADFLogger.WARNING, "INTO PC06_COMPLETAR_CUESTIONARIO_IBCIE.");
                    esValidaDocumento =
                            validarDocumentoPorOperacionIdTareaNumeroSerieDocumento(getIdOperacion(), idTarea,
                                                                            FenixConstants.TD_SOPORTE_IBCIE,
                                                                            evaluacionesBean.getNumeroSerieDocumento());

                    esValidaPorcentaje = validarPorcentajeEvaluaciones();
                    if (!esValidaPorcentaje) {
                        bundleKeyError.add("MSG_02_COMPLETAR_CUESTIONARIO");
                    }
                    if (!esValidaDocumento) {
                        bundleKeyError.add("MSG_05_COMPLETAR_CUESTIONARIO");
                    }
                    evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_01_COMPLETAR_CUESTIONARIO"));
                    esValidaFinalizarTarea = (esValidaPorcentaje && esValidaDocumento) ? Boolean.TRUE : Boolean.FALSE;
                    break;
                case FenixConstants.PC06_VALIDAR_CUESTIONARIO_COMPLETO_IBCIE:
                    LOGGER.warning("Valor retornado " + ADFUtils.findControlBinding("retorno").getInputValue());
                    AttributeBinding valorRetornoValido = ADFUtils.findControlBinding("retorno");
                    if(null != valorRetornoValido.getInputValue()){
                        boolean valorR = (Boolean) valorRetornoValido.getInputValue();
                        if(valorR){
                            esValidaFinalizarTarea = Boolean.TRUE;
                            evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_01_VALIDAR_CUESTIONARIO_COMPLETO"));
                        } else {
                            esValidaFinalizarTarea = validarEstadoEvaluacion();
                            bundleKeyError.add("MSG_02_VALIDAR_CUESTIONARIO_COMPLETO");
                            evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_01_VALIDAR_CUESTIONARIO_COMPLETO"));    
                        }
                    }else {
                        esValidaFinalizarTarea = validarEstadoEvaluacion();
                        bundleKeyError.add("MSG_02_VALIDAR_CUESTIONARIO_COMPLETO");
                        evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_01_VALIDAR_CUESTIONARIO_COMPLETO"));
                    }
                    break;
                case FenixConstants.PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE:
                    esValidaValor = validarEstadoValor();
                    if (esValidaValor) {
                    Boolean validarCalificacion = validarCalificacionEvaluacionDuplicada();
                    LOGGER.log(ADFLogger.WARNING, "Valida calificacion: " + validarCalificacion);
                    try {
                        esValidaDocumento =
                            validarDocumentoPorOperacionIdTareaNumeroSerieDocumento(getIdOperacion(), idTarea,
                                                                                    FenixConstants.TD_INFORME_IBCIE,
                                                                                    evaluacionesBean.getNumeroSerieDocumento());

                    } catch (Exception e) {
                        LOGGER.log(ADFLogger.WARNING, "Error al validar el documento." + e.getClass() + "." + e);
                    }
                            LOGGER.warning("Valida Documento Informe IBCIE: " + esValidaDocumento); 
                            if(esValidaDocumento != null &&
                               !esValidaDocumento){
                                bundleKeyError.add("PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE_MSG02");
                            }else{
                                esValidaDocumento = Boolean.TRUE;
                            }
                    if(!validarCalificacion){
                        //Valida que la calificacion no es la correcta
                        bundleKeyError.add("MSG_06_EVALUACIONES");
                        LOGGER.log(ADFLogger.WARNING, "Verificar valores introducidos en la columna Calificacion IBCIE");
                    }
                    esValidaFinalizarTarea = (esValidaDocumento && validarCalificacion) ? Boolean.TRUE : Boolean.FALSE;
                    } else {
                        LOGGER.warning("no hay un valor valido, se dispone a mostrar el mensaje: ");
                        esValidaFinalizarTarea = Boolean.FALSE;
                        bundleKeyError.add("MSG_06_IMPEDIR_FIN_TAREA_EVALUACIONES");
                    }
                    break;
                case FenixConstants.PC06_VALIDAR_INFORME_IBCIE:

                    break;
                case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS_SUPERVISION:
                    LOGGER.log(ADFLogger.WARNING, "INTO PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS_SUPERVISION.");
                    evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_01_COMPLETAR_CUESTIONARIO"));
                    break;
                case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS:
                    LOGGER.log(ADFLogger.WARNING, "INTO PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS.");
                    esValidaDocumento =
                        validarDocumentoPorOperacionIdTareaNumeroSerieDocumento(getIdOperacion(), idTarea,
                                                                    FenixConstants.TD_SOPORTE_SIEMAS,
                                                                    evaluacionesBean.getNumeroSerieDocumento());

                    esValidaPorcentaje = validarPorcentajeEvaluaciones();
                    if (!esValidaPorcentaje) {
                        bundleKeyError.add("MSG_02_COMPLETAR_CUESTIONARIO");
                    }
                    if (!esValidaDocumento) {
                        bundleKeyError.add("MSG_06_COMPLETAR_CUESTIONARIO");
                    }
                    evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_01_COMPLETAR_CUESTIONARIO"));
                    esValidaFinalizarTarea = (esValidaPorcentaje && esValidaDocumento) ? Boolean.TRUE : Boolean.FALSE;
                    break;
                case FenixConstants.PC06_VALIDAR_CUESTIONARIO_COMPLETO_SEGUIMIENTO_SIEMAS:
                    LOGGER.log(ADFLogger.WARNING, "INTO PC06_VALIDAR_CUESTIONARIO_COMPLETO_SEGUIMIENTO_SIEMAS.");
                    esValidaFinalizarTarea = validarEstadoEvaluacion();
                    bundleKeyError.add("MSG_02_VALIDAR_CUESTIONARIO_COMPLETO");
                    evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_01_VALIDAR_CUESTIONARIO_COMPLETO"));
                    break;
                case FenixConstants.PC06_GENERAR_INFORME_SEGUIMIENTO_SIEMAS:
                    if (evaluacionesBean.getTipoEvaluacion() != null &&
                        evaluacionesBean.getTipoEvaluacion().trim().length() > 0) {
                        if (evaluacionesBean.getTipoEvaluacion().equalsIgnoreCase("SIEMAS")) {
                            esValidaFinalizarTarea = 
                                    validarDocumentoPorOperacionIdTareaNumeroSerieDocumento(getIdOperacion(), idTarea,
                                                                        FenixConstants.TD_INFORME_SIEMAS,
                                                                        evaluacionesBean.getNumeroSerieDocumento());
                            bundleKeyError.add("MSG_DOC_SIEMAS_ODE");
                        }
                    }
                    break;
                case FenixConstants.PC06_REVISAR_INFORME_SEGUIMIENTO_SIEMAS:

                    break;
                case FenixConstants.PC06_ACTUALIZAR_CONDICIONES_SIEMAS:
                    esValidaFinalizarTarea = validarTCC();
                    break;
                default:
                    break;
                } //Cierre switch
            } catch (Exception e) {
                LOGGER.log(ADFLogger.WARNING,
                           "Error en Switch de invokeFinalizarTarea." + e.getClass() + "." + e);
            }
            try {
                if (!esValidaFinalizarTarea) {
                    if (bundleKeyError.size() > 0) {
                        for (String bundleKey : bundleKeyError)
                            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                    }
                } else {
                    LOGGER.log(ADFLogger.WARNING, "entra a abrir Popup.");
                    RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                    popupConfFinalizarTarea.show(popupHints);
                }
            } catch (Exception e) {
                LOGGER.log(ADFLogger.WARNING,
                           "Error al validar abrir popup de invokeFinalizarTarea." + e.getClass() + "." + e.getMessage());
            }
        }else{
            //Abrir popup
            LOGGER.log(ADFLogger.WARNING, "No se ejecutan acciones del metodo invokeFinalizarTarea por validaciones de CA");
            if (!erroresCA.isEmpty()){
                for (String msg : erroresCA) {
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }  
        }
        LOGGER.log(ADFLogger.WARNING, "Finaliza metodo invokeFinalizarTarea");
        return null;
    }
    
    public String aceptarFinalizarTarea() {
        LOGGER.warning("Inside aceptarFinalizarTarea.");
        
        EvaluacionesBean evaluacionesBean =
            (EvaluacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");
        InvokeActionBean invokeActionBean = null;
        String invokeActions = null;
        List<String> bundleKeyError = new ArrayList<String>();
        Boolean aceptarFinalizarTarea = Boolean.TRUE;
        
        try {
            //invocar metodo para cargar documentos onBase
            LOGGER.log(ADFLogger.WARNING, "Se llama metodo para cargar a ONBASE");
            cargarDocumentosOnBase();
            LOGGER.log(ADFLogger.WARNING, "Finaliza metodo para cargar a ONBASE");
            
            try {
                switch (evaluacionesBean.getIdTarea()) {
                    case FenixConstants.PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE:
                    agregarActualizarCalificacionEvaluacion();
                    aceptarFinalizarTarea = Boolean.TRUE;
                    break;
                    case FenixConstants.PC06_ACTUALIZAR_CONDICIONES_SIEMAS:
                    LOGGER.warning("PC06_Actualizar_Condiciones_SIEMAS.");
                    boolean exito = true;
                    exito = actualizarTCCEstadoActualizada();
                    aceptarFinalizarTarea = Boolean.TRUE;
                    if(!exito){
                        LOGGER.severe("Error al actualizar Estados TCC a Aprobado");
                    }
                    break;
                    case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_IBCIE:
                    aceptarFinalizarTarea = bloquearPreguntasEvaluacion();
                    if(!aceptarFinalizarTarea){
                        bundleKeyError.add("MSG_ERROR_BLOQUEO_PREGUNTAS"); 
                    }
                    break;
                    default:
                    break;
                }
                
                // Notificar unicamente para el tipo de evaluacion Medio termino y Ex Post
                //De acuerdo con QA no se envia la notificacion si no se finaliza la tarea
                if(aceptarFinalizarTarea){
                    if (null != evaluacionesBean.getEventoEvaluacion() && 
                        (evaluacionesBean.getEventoEvaluacion().compareTo(EVALUACION_MEDIO_TERMINO) == 0 || 
                        evaluacionesBean.getEventoEvaluacion().compareTo(EVALUACION_EX_POST) == 0) ) {
                        LOGGER.warning("Si aplica notificacion para el tipo de evaluacion.");
                        LOGGER.warning("eventoEvaluacion :"+evaluacionesBean.getEventoEvaluacion());
                        if (notificarTareaFinalizada()) {
                            LOGGER.warning("Consumo de servicio de notificacion exitosa");
                        } else {
                            // No se definio accion alguna cuando exista un error en la notificacion
                            LOGGER.warning("Error al consumir el servicio de notificacion.");
                        }
                    } else {
                        LOGGER.warning("No aplica notificacion para el tipo de evaluacion.");
                        LOGGER.warning("eventoEvaluacion :"+evaluacionesBean.getEventoEvaluacion());
                    }
                }else{
                    LOGGER.warning("No se manda a notificar ya que la tarea no finaliza.");
                }
                
            } catch (Exception e) {
                LOGGER.log(ADFLogger.WARNING,
                           "Error en sentencia switch, aceptarFinalizarTarea.", e);
            }
            
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING, "Error en aceptarFinalizarTarea.", e);
        }
        
        try{
            if(aceptarFinalizarTarea){
                invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                invokeActions = invokeActionBean.invokeOperation();
                LOGGER.log(ADFLogger.WARNING, "Termino ejecucion de metodo invokeOperation correctamente." + evaluacionesBean.getNombreTarea()); 
            }else{
                if (bundleKeyError.size() > 0) {
                    for (String bundleKey : bundleKeyError)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
            }
            
            popupConfFinalizarTarea.hide();
            
        }catch(Exception e){
            LOGGER.log(ADFLogger.WARNING, "Error al ejecutar invokeActionBean.invokeOperation()." + e);
            JSFUtils.addFacesErrorMessage("Error al aceptar finalizar la tarea : "  + evaluacionesBean.getNombreTarea());
        }
        
        LOGGER.log(ADFLogger.WARNING, "aceptar finalizar tarea :" + invokeActions);
        
        return invokeActions;
    }

    @SuppressWarnings("unchecked")
    private boolean notificarTareaFinalizada() {
        LOGGER.warning("Entrando en notificarTareaFinalizada.");
        boolean resultado = Boolean.FALSE;
        CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
        EvaluacionesBean evaluacionesBean =
            (EvaluacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");
        correo.setIdOperacion(getIdOperacion());
        correo.setIdPlantilla(FenixConstants.PLANTILLA_FLUJO_EVALUACION_IBCIE);
        
        Usuario usuario = new Usuario();
        usuario.getUsuario().add(FENIX_ANALISTA_ODE);
        
        correo.setTo(usuario);
        Param parametro1 = new Param();
        parametro1.setTag("TAREA");
        parametro1.setValor(getCodigoTarea().toString());
                
        Param parametro2 = new Param();
        parametro2.setTag("TIPO_EVALUACION");
        parametro2.setValor(evaluacionesBean.getEventoEvaluacion().toString());
        
        correo.getParametros().add(parametro1);
        correo.getParametros().add(parametro2);
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBindingValidar = bindings.getOperationBinding("enviarCorreoService");
        operationBindingValidar.getParamsMap().put("objetoCorreo", correo);
        operationBindingValidar.execute();
        
        if (operationBindingValidar.getErrors().isEmpty()) {
            resultado = Boolean.TRUE;
        } else {
            JSFUtils.addFacesErrorMessage(operationBindingValidar.getErrors().toString());
        }
         
        LOGGER.warning("resultado: " + resultado);
        return resultado;
    }
    
    public void cancelarFinalizarTarea(ActionEvent ev)
    {
        popupConfFinalizarTarea.hide();
    }
    
    public boolean validarPorcentajeEvaluaciones(){
        DCIteratorBinding voEvaluaciones = null;
        int cantidadEvaluacionesCompletas=0;
        //recuperar el Iterador de evalucionesVO
        voEvaluaciones = ADFUtils.getDCBindingContainer().findIteratorBinding("EvaluacionVOIterator");
        ViewObject vo = voEvaluaciones.getViewObject();
        //FNXII-2864
        Double porcentajeCompleto=new Double(100);
        //filtrar por el campo de estado
        Row[] evaluacionesCompletas= vo.getFilteredRows("porcentajeAvance",porcentajeCompleto);
        for(Row row : evaluacionesCompletas ){
            cantidadEvaluacionesCompletas++;
        }
        LOGGER.warning("cantidad de evaluaciones"+voEvaluaciones.getEstimatedRowCount());
        LOGGER.warning("cantidad de evaluaciones Completas"+cantidadEvaluacionesCompletas);
        if(cantidadEvaluacionesCompletas==voEvaluaciones.getEstimatedRowCount()){
            return true;
        }
        return false;
    }
    
    public boolean validarEvaluaciones(){
        DCIteratorBinding voEvaluaciones = null;   
        int cantidadEvaluacionesNoValidas=0;
        voEvaluaciones = ADFUtils.getDCBindingContainer().findIteratorBinding("EvaluacionVOIterator");
        ViewObject vo = voEvaluaciones.getViewObject();
        Row[] evaluacionesNovalidas= vo.getFilteredRows("validado",false);
        for(Row row : evaluacionesNovalidas ){
            cantidadEvaluacionesNoValidas++;
        }
        if(cantidadEvaluacionesNoValidas!=0){
            return false;
        }
        
        return true;
    }

    public Boolean validarEstadoEvaluacion() {
        LOGGER.warning("Entra en validarEstadoEvaluacion.");
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Boolean validaEstado = Boolean.FALSE;
        try {
            operationBinding = bindings.getOperationBinding("validarEstadoEvaluacion");
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                validaEstado = (Boolean) operationBinding.getResult();
            } else {
                LOGGER.warning("El valor de retorno es nulo.");
            }
        } catch (Exception e) {
            LOGGER.warning("Error en validarEstadoEvaluacion." + e.getClass() + "." + e);
        }
        LOGGER.warning("Valor de retorno :" + validaEstado);
        return validaEstado;
    }
    
    public boolean validarAgregoEvaluaciones(){
        DCIteratorBinding voEvaluaciones = null;   
        voEvaluaciones = ADFUtils.getDCBindingContainer().findIteratorBinding("EvaluacionVOIterator");
        if(voEvaluaciones.getRowAtRangeIndex(0)==null){
            return false;
        }else{
            return true;
        }

    }
    
    public Boolean validarCalificacionEvaluacion() {
        LOGGER.warning("Entra en validarCalificacionEvaluacion.");
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Boolean validaCalificacion = Boolean.FALSE;
        try {
            operationBinding = bindings.getOperationBinding("validarCalificacionEvaluacion");
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                validaCalificacion = (Boolean) operationBinding.getResult();
            } else {
                LOGGER.warning("El valor de retorno es nulo.");
            }
        } catch (Exception e) {
            LOGGER.warning("Error en validarCalificacionEvaluacion." + e.getClass() + "." + e);
        }
        LOGGER.warning("Valor de retorno :" + validaCalificacion);
        return validaCalificacion;
    }
    
    public Boolean validarCalificacionEvaluacionDuplicada() {
        LOGGER.warning("Entra en validarCalificacionEvaluacionDuplicada.");
        
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Boolean validaCalificacion = Boolean.FALSE;
        
        try {
            operationBinding = bindings.getOperationBinding("validarCalificacionDuplicada");
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                validaCalificacion = (Boolean) operationBinding.getResult();
            } else {
                LOGGER.warning("El valor de retorno es nulo.");
            }
        } catch (Exception e) {
            LOGGER.warning("Error en validarCalificacionDuplicada." + e.getClass() + "." + e);
        }
        
        LOGGER.warning("Valor de retorno :" + validaCalificacion);
        return validaCalificacion;
    }
    
    public Boolean validarEstadoValor() {
            LOGGER.warning("Entra en validarEstadoValor.");
            OperationBinding operationBinding = null;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            Boolean esValidaValor = Boolean.FALSE;

            try {
                operationBinding = bindings.getOperationBinding("validarValorEvaluacion");
                operationBinding.execute();
                if (null != operationBinding.getResult()) {
                    esValidaValor = (Boolean) operationBinding.getResult();
                } else {
                    LOGGER.log(ADFLogger.WARNING, "El valor es nulo.");
                }
            } catch (Exception e) {
                LOGGER.log(ADFLogger.WARNING, "Error al ejecutar validarValorEvaluacion.", e);
            }
            LOGGER.warning("Valor de retorno :" + esValidaValor);
            return esValidaValor;
    }

    
    public Boolean agregarActualizarCalificacionEvaluacion() {
        LOGGER.warning("Entra en agregarActualizarCalificacionEvaluacion.");
        
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Boolean validaAgregarActualizar = Boolean.FALSE;
        
        try {
            operationBinding = bindings.getOperationBinding("actualizarCalificacionDuplicada");
            operationBinding.execute();
            
            validaAgregarActualizar = (Boolean) operationBinding.getResult();  
            
        } catch (Exception e) {
            LOGGER.warning("Error en agregarActualizarCalificacionEvaluacion." + e.getClass() + "." + e);
        }
        
        LOGGER.warning("validaAgregarActualizar: " + validaAgregarActualizar);
        
        return validaAgregarActualizar;
    }
    
    public String invokeSolicitarAjustesARespuestas() {
        EvaluacionesBean evaluacionesBean =
            (EvaluacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");

        String bundleKeyError = "";
        Boolean esValidoSolicitarAjustes = Boolean.FALSE;
        String idTarea = null;
        try {
            if (null != evaluacionesBean.getIdTarea()) {
                idTarea = evaluacionesBean.getIdTarea();
            } else {
                LOGGER.log(ADFLogger.WARNING, "Valor del Id de la tarea es nulo.");
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING,
                       "Error al obtener IdTarea en invokeSolicitarAjustesARespuestas" + e.getClass() + "." +
                       e.getMessage());
        }
        LOGGER.fine("PC06_SOLICITAR_AJUSTES_A_RESPUESTAS");
        try {
            switch (idTarea) {
            case FenixConstants.PC06_VALIDARCUESTIONARIOS:
                esValidoSolicitarAjustes =
                    validateComentario(getIdOperacion(), getCodigoTarea().toString(),
                                       getInstanciaTarea()); //TODO Check this validation
                //FNXII-2718
                evaluacionesBean.setFinalizarTarea(getStringFromBundle("CONFIRMA_SOLICITAR_AJUSTES_A_RESPUESTAS"));
                bundleKeyError = "MSG_EVALUACION_VALIDAR_CUESTIONARIOS_COMPLETADOS_SOLICITAR_AJUSTES_RESPUESTAS";
                break;
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_COMPLETO_IBCIE:
                LOGGER.log(ADFLogger.WARNING, "Entra en PC06_VALIDAR_CUESTIONARIO_COMPLETO_IBCIE.");
                esValidoSolicitarAjustes =
                    validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_04_VALIDAR_CUESTIONARIO_COMPLETO"));
                bundleKeyError = "MSG_03_VALIDAR_CUESTIONARIO_COMPLETO";
                break;
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_COMPLETO_SEGUIMIENTO_SIEMAS:
                esValidoSolicitarAjustes =
                    validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_04_VALIDAR_CUESTIONARIO_COMPLETO"));
                bundleKeyError = "MSG_03_VALIDAR_CUESTIONARIO_COMPLETO";
                break;
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE:
                esValidoSolicitarAjustes =
                  validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                evaluacionesBean.setFinalizarTarea(getStringFromBundle("MSG_04_VALIDAR_CUESTIONARIO_COMPLETO"));
                bundleKeyError = "MSG_EVALUACION_VALIDAR_CUESTIONARIOS_ODE_Y_GENERAR_INFORMES_SOLICITAR_AJUSTES_RESPUESTAS";
                break;
            case FenixConstants.PC06_VALIDAR_INFORME_IBCIE:
                esValidoSolicitarAjustes =
                    validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                bundleKeyError = "MSG_ES_NECESARIO_AGREGAR_AL_MENOS_UN_COMENTARIO_PARA_SOLICITAR_AJUSTES_A_LAS_RESPUESTAS";
                break;
            case FenixConstants.PC06_REVISAR_INFORME_SEGUIMIENTO_SIEMAS:
                esValidoSolicitarAjustes =
                    validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                bundleKeyError = "MSG_ES_NECESARIO_AGREGAR_AL_MENOS_UN_COMENTARIO_PARA_SOLICITAR_AJUSTES_A_LAS_RESPUESTAS";
                break;
            default:
                break;
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING,
                       "Error en Switch de invokeSolicitarAjustesARespuestas." + e.getClass() + "." + e.getMessage());
        }
        try {
            if (!esValidoSolicitarAjustes) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));

            } else {
                RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                popupConfSolicitarAjustesARespuestas.show(popupHints);
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING,
                       "Error al abrir popup de invokeSolicitarAjustesARespuestas." + e.getClass() + "." +
                       e.getMessage());
        }
        return null;
    }
    
    
    public String invokeSolicitarAjustesEnDiseno() {
        EvaluacionesBean evaluacionesBean =
            (EvaluacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");

        String bundleKeyError = "";
        Boolean esValidoSolicitarAjustes = Boolean.FALSE;
        LOGGER.fine("PC06_SOLICITAR_AJUSTES_EN_DISENO");
        String idTarea = null;
        if (null != evaluacionesBean.getIdTarea()) {
            idTarea = evaluacionesBean.getIdTarea();
        } else {
            LOGGER.log(ADFLogger.WARNING, "El Id de la tarea es nulo.");
        }
        switch (idTarea) {
        case FenixConstants.PC06_COMPLETARCUESTIONARIOS:
            esValidoSolicitarAjustes =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(),
                                   getInstanciaTarea()); //TODO Check this validation
            //FNXII-2716
            bundleKeyError = "MSG_EVALUACION_COMPLETAR_CUESTIONARIOS_SOLICITAR_AJUSTES_DISENO";
            break;
        case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SUPERVISION_IBCIE:
            esValidoSolicitarAjustes =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(),
                                   getInstanciaTarea()); //TODO Check this validation

            bundleKeyError = "MSG_03_COMPLETAR_CUESTIONARIO";
            break;
        case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_IBCIE:
            esValidoSolicitarAjustes =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(),
                                   getInstanciaTarea()); //TODO Check this validation

            bundleKeyError = "MSG_03_COMPLETAR_CUESTIONARIO";
            break;
        case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS_SUPERVISION:
            esValidoSolicitarAjustes =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(),
                                   getInstanciaTarea()); //TODO Check this validation

            bundleKeyError = "MSG_03_COMPLETAR_CUESTIONARIO";
            break;
        case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS:
            esValidoSolicitarAjustes =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(),
                                   getInstanciaTarea()); //TODO Check this validation

            bundleKeyError = "MSG_03_COMPLETAR_CUESTIONARIO";
            break;
        default:
            break;
        } //Cierre de Switch
        if (!esValidoSolicitarAjustes) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));

        } else {
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfSolicitarAjustesEnDiseno.show(popupHints);
        }

        return null;
    }
    
    public String aceptarSolicitarAjustesARespuestas() {
        EvaluacionesBean evaluacionesBean =
            (EvaluacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");
        String idTarea = null;
        if (null != evaluacionesBean.getIdTarea()) {
            idTarea = evaluacionesBean.getIdTarea();
        } else {
            LOGGER.log(ADFLogger.WARNING, "El Id de la tarea es nulo.");
        }
        switch (idTarea) {
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE:
                Boolean respuestaServicio = null;
                BindingContainer bindings = getBindings();
                OperationBinding operationBinding = bindings.getOperationBinding("compensarEvaluacion");
                operationBinding.getParamsMap().put("instanciaProceso", evaluacionesBean.getInstanciaProceso().toString());
                operationBinding.getParamsMap().put("idOperacion", evaluacionesBean.getIdOperacion().toString());
                operationBinding.getParamsMap().put("tipoEvaluacion", evaluacionesBean.getEventoEvaluacion().toString());
                operationBinding.getParamsMap().put("loginusuario", evaluacionesBean.getLoginUsuario().toString());
                respuestaServicio = (Boolean)operationBinding.execute();
                LOGGER.warning("Valor paylod retorno : " + ADFUtils.findControlBinding("retorno").getInputValue());
                actualizarPayloadElement("retorno", Boolean.TRUE);
                LOGGER.warning("Valor paylod retorno : " + ADFUtils.findControlBinding("retorno").getInputValue());
                LOGGER.log(ADFLogger.WARNING, "Respuesta servicio compensarEvaluacion: " + respuestaServicio);
                break;
            default:
            break;
        }
        InvokeActionBean invokeActionBean = null;
        try {
            //invocar metodo para cargar documentos onBase
            cargarDocumentosOnBase();
            invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        } catch (Exception e) {
            LOGGER.log(ADFLogger.WARNING,
                       "Error en aceptarSolicitarAjustesARespuestas." + e.getClass() + "." + e.getMessage());
        }
        return invokeActionBean.invokeOperation();
    }
    
    public void cancelarSolicitarAjustesARespuestas(ActionEvent ev){
        popupConfSolicitarAjustesARespuestas.hide();
    }
    
    public String aceptarSolicitarAjustesEnDiseno()
    {   
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public void cancelarSolicitarAjustesEnDiseno(ActionEvent ev){
        popupConfSolicitarAjustesEnDiseno.hide();
    }
    
    public String invokePublicarInforme() {
        LOGGER.warning("Inside invokePublicarInforme");

        EvaluacionesBean evaluacionesBean =
            (EvaluacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");
        Boolean esValidaDocumento = Boolean.TRUE;
        String bundleKeyError = "";
        Integer idTarea = null;

        try {
            if (evaluacionesBean.getIdTarea() != null) {
                idTarea = Integer.valueOf(evaluacionesBean.getIdTarea());
            } else {
                LOGGER.warning("Id de Tarea del Managed Bean es NULL");
            }
        } catch (Exception e) {
            LOGGER.warning("No se pudo obtener el Id de Tarea");
        }

        if (idTarea != null) {
            //Para las tareas 
            //Validar informe medio término.
            //Validar informe Ex post.
            //validar que exista un documento del tipo Informe Medio Término y Ex Post”.
            if(FenixConstants.PC06_VALIDAR_INFORME_IBCIE.equals(idTarea.toString())){
                LOGGER.warning("Validar informe");
                LOGGER.warning("Se valida documento");
                LOGGER.warning("idOperacion:"+getIdOperacion());
                LOGGER.warning("idTarea:"+idTarea);
                LOGGER.warning("idTipoDocumento:"+FenixConstants.TD_INFORME_IBCIE);
                LOGGER.warning("numeroSerieDocumento:"+evaluacionesBean.getNumeroSerieDocumento());
                esValidaDocumento =
                            validarDocumentoPorOperacionIdTareaNumeroSerieDocumento(getIdOperacion(), idTarea,
                                                                                        FenixConstants.TD_INFORME_IBCIE,
                                                                                            evaluacionesBean.getNumeroSerieDocumento());
                LOGGER.warning("esValidaDocumento:"+esValidaDocumento);
                bundleKeyError = "MSG01_VALIDAR_INFORME_IBCIE";
            }else{
                LOGGER.warning("No valida documento");
                LOGGER.warning("idTarea:"+idTarea);
                //no validar documento
                esValidaDocumento = Boolean.TRUE;
            }

            if (esValidaDocumento) {
                RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                popupConfPublicarInforme.show(popupHints);
            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));
            }
        } else {
            LOGGER.warning("El idTarea es Null.");
        }
        LOGGER.warning("Leave invokePublicarInforme");
        return null;
    }
    
    public String aceptarPublicarInforme()
    {   
        // Invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        
        notificarCorreoFinalizar();
        
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public void cancelarPublicarInforme(ActionEvent ev){
        popupConfPublicarInforme.hide();
    }
    
    public String invokeSolicitarAjustes()
    {
        String bundleKeyError = "";
        Boolean esValidoSolicitarAjustes = Boolean.FALSE;
        LOGGER.fine("PC06_SOCILITAR_AJUSTES");
        esValidoSolicitarAjustes =validateComentario(getIdOperacion(), getCodigoTarea().toString(),
                                       getInstanciaTarea()); //TODO Check this validation
                //FNXII-2720
                bundleKeyError = "MSG_EVALUACION_REVISAR_INFORMES_SOLICITAR_AJUSTES";

        if (!esValidoSolicitarAjustes)
        {
            JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));

        } else
        { 
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            popupConfSolicitarAjustes.show(popupHints);
        }

        return null;
    }
    
    public String aceptarSolicitarAjustes()
    {   
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public void cancelarSolicitarAjustes(ActionEvent ev){
        popupConfSolicitarAjustes.hide();
    }


    public RichPopup getPopupConfSolicitarAjustesARespuestas() {
        return popupConfSolicitarAjustesARespuestas;
    }

    public void setPopupConfSolicitarAjustesARespuestas(RichPopup popupConfSolicitarAjustesARespuestas) {
        this.popupConfSolicitarAjustesARespuestas = popupConfSolicitarAjustesARespuestas;
    }


    public RichPopup getPopupConfFinalizarTarea() {
        return popupConfFinalizarTarea;
    }

    public void setPopupConfFinalizarTarea(RichPopup popupConfFinalizarTarea) {
        this.popupConfFinalizarTarea = popupConfFinalizarTarea;
    }


    public RichPopup getPopupConfSolicitarAjustesEnDiseno() {
        return popupConfSolicitarAjustesEnDiseno;
    }

    public void setPopupConfSolicitarAjustesEnDiseno(RichPopup popupConfSolicitarAjustesEnDiseno) {
        this.popupConfSolicitarAjustesEnDiseno = popupConfSolicitarAjustesEnDiseno;
    }


    public RichPopup getPopupConfPublicarInforme() {
        return popupConfPublicarInforme;
    }

    public void setPopupConfPublicarInforme(RichPopup popupConfPublicarInforme) {
        this.popupConfPublicarInforme = popupConfPublicarInforme;
    }


    public RichPopup getPopupConfSolicitarAjustes() {
        return popupConfSolicitarAjustes;
    }

    public void setPopupConfSolicitarAjustes(RichPopup popupConfSolicitarAjustes) {
        this.popupConfSolicitarAjustes = popupConfSolicitarAjustes;
    }

    public String invokeRequiereRevisarDisenoCuestionarios() {
        
        LOGGER.log(ADFLogger.WARNING, "INTO invokeRequiereRevisarDisenoCuestionarios.");
        Boolean esRevisarDisenio = Boolean.FALSE;
        String bundleKeyError = "";
        try{
                esRevisarDisenio =
                    validateComentario(getIdOperacion(), getCodigoTarea().toString(),
                                       getInstanciaTarea()); 
                bundleKeyError = "MSG_EVALUACION_REVISAR_DISENO_CUESTIONARIOS";
                
            if (!esRevisarDisenio) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle(bundleKeyError));

            } else {
                RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                getPopupConfRequiereValidarDiseno().show(popupHints);
            }
        }catch(Exception e){
            LOGGER.log(ADFLogger.WARNING, "Error en invokeRequiereRevisarDisenoCuestionarios." + e.getClass() + "." + e.getMessage());
        }
        return null;
    }

    public void setPopupConfRequiereValidarDiseno(RichPopup popupConfRequiereValidarDiseno) {
        this.popupConfRequiereValidarDiseno = popupConfRequiereValidarDiseno;
    }

    public RichPopup getPopupConfRequiereValidarDiseno() {
        return popupConfRequiereValidarDiseno;
    }

    public String aceptarRevisarDisenoCuestionario() {
        //invocar metodo para cargar documentos onBase
        InvokeActionBean invokeActionBean = null;
        try{
        cargarDocumentosOnBase();
        invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        }catch(Exception e){
            LOGGER.log(ADFLogger.WARNING, "Error en aceptarRevisarDisenoCuestionario." + e.getClass() + "." + e.getMessage());
        }
        return invokeActionBean.invokeOperation();
    }

    public String cancelarRevisarDisenoCuestionario() {
        getPopupConfRequiereValidarDiseno().hide();
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public boolean validarTCC(){
            
        boolean esValido = false;
        OperationBinding oper = null;
        
        Long idOperacion = null;
        try{
            if(getIdOperacion() != null){
                idOperacion = getIdOperacion();    
            }else{
                LOGGER.severe("Error el Codigo de Operacion es NULL");
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el codigo de operacion");
        }
        
        if(idOperacion != null){
            
            Map params = null;
            params = new HashMap();
            params.put("idOperacion", idOperacion);
            
            oper = executeOperBinding(params, "validarEstadosTCCEvaluacion");
            if(oper != null){
                if(oper.getErrors().isEmpty()){
                    if(oper.getResult() != null){
                        try{
                            esValido = Boolean.valueOf(oper.getResult().toString());    
                        }catch(Exception e){
                            LOGGER.severe("Error al obtener el resultado de la Operacion: validarEstadosTCCAprobacion");
                        }
                        
                        if(esValido){
                            LOGGER.warning("El resultado de la validacion de TCC es positiva");    
                        }else{
                            LOGGER.severe("El resultado de la validacion de TCC es negativa");
                            String msg = getStringFromBundle("VALIDAR_TCC_NEGATIVO");
                            JSFUtils.addFacesErrorMessage(msg);
                        }
                    }else{
                        String msg = getStringFromBundle("VALIDAR_TCC_ERROR");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                }else{
                    LOGGER.severe("Error al ejecutar la operacion");
                    JSFUtils.addFacesErrorMessage(oper.getErrors().toString());
                }
            }else{
                LOGGER.severe("Error Operator Binding no definido");
                String msg = getStringFromBundle("VALIDAR_TCC_ERROR");
                JSFUtils.addFacesErrorMessage(msg);
            }
        }else{
            LOGGER.severe("Error ID de Operacion no definida");
            String msg = getStringFromBundle("VALIDAR_TCC_ERROR");
            JSFUtils.addFacesErrorMessage(msg);
        }
        
        return esValido;
    }
    
    /**
     * Ejecuta un operator bindings
     * @param params contiene mapa de objetos de la operacion
     * @param operBindName contiene el nombre de la operacion
     * @return devuelve objeto Operator Bindings
     */
    @SuppressWarnings("unchecked")
    private OperationBinding executeOperBinding(Map params,
                                                String operBindName){
        
        BindingContainer bindings = getBindings();
        OperationBinding oper = null;
        oper = bindings.getOperationBinding(operBindName);
        if (null != oper) {
            
            if(params != null){
                oper.getParamsMap().putAll(params);    
            }
            try {
                LOGGER.warning("EJECUTA METODO ".concat(operBindName));
                oper.execute();
            } catch (Exception e) {
                LOGGER.severe("Error al ejecutar Operator Bindings: " + operBindName, e);                
            }

            if (!oper.getErrors().isEmpty()) {
                LOGGER.severe("Operator Bindings devuelve errores: " + operBindName);
            }else{
                LOGGER.warning("Operator Bindings se ejecuto correctamente");
            }
        }else{
            LOGGER.severe("Error el operator Binding " + operBindName + 
                          " no fue encontrado");
        }
        
        return oper;
    }
    
    /**
     * Realiza la actualizacion de TCC cambiando sus Estados a Aprobado,
     * al finalizar la tarea de Actualizar TCC
     */
    @SuppressWarnings("unchecked")
    public boolean actualizarTCCEstadoActualizada() {
        LOGGER.warning("Inside actualizarTCCEstadoActualizada.");

        boolean exito = false;
        Long idOperacion = null;
        
        try {
            if (getIdOperacion() != null) {
                idOperacion = getIdOperacion();
            }

            if (idOperacion != null) {
                LOGGER.severe("Error el valor del ID de Operacion es NULL");
            }
        } catch (Exception e) {
            LOGGER.severe("Error al obtener el ID de Operacion");
        }

        if (idOperacion != null) {
            Map params = new HashMap();
            params.put("idOperacion", idOperacion);
            params.put("idProceso", getIdProceso());
            OperationBinding oper = executeOperBinding(params, "actualizarEstadosTCCEvaluacion");
            if (oper != null) {
                if (oper.getErrors().isEmpty()) {

                    Boolean result = (Boolean) oper.getResult();
                    if (result != null) {
                        if (result) {
                            LOGGER.warning("Actualizacion de Estados TCC exitosa");
                            exito = true;
                        } else {
                            LOGGER.severe("Resultado de la actualizacion de Estados TCC no exitosa");
                            exito = false;
                            String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_no_realizado");
                            JSFUtils.addFacesErrorMessage(msg);
                        }
                    } else {
                        LOGGER.severe("Resultado de actualizacion de Estados TCC NULL");
                        exito = false;
                        String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_no_resultado");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                } else {
                    LOGGER.severe("Error en la operacion de actualizar Estados TCC. " + oper.getErrors().toString());
                    exito = false;
                    String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_contiene_fallo");
                    msg = msg + ". " + oper.getErrors().toString();
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }
        }

        return exito;
    }
    
    public Boolean bloquearPreguntasEvaluacion(){
        LOGGER.warning("Entra en bloquearPreguntasEvaluacion");
        EvaluacionesBean evaluacionesBean =
            (EvaluacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");
        
        OperationBinding operationBinding = null;
        Map params = new HashMap();
        Boolean esBloqueo = Boolean.FALSE;
        String loginUsuario = null;
        
        try{
            loginUsuario = evaluacionesBean.getLoginUsuario();
            LOGGER.warning("Login de Usuario : " + loginUsuario);
            LOGGER.warning("eventoEvaluacion : "+evaluacionesBean.getEventoEvaluacion());
            if (null != evaluacionesBean.getEventoEvaluacion() && 
                (evaluacionesBean.getEventoEvaluacion().compareTo(EVALUACION_MEDIO_TERMINO) == 0 || 
                evaluacionesBean.getEventoEvaluacion().compareTo(EVALUACION_EX_POST) == 0) ) {
                LOGGER.warning("Si aplica para el bloqueo de preguntas.");
                params.put("loginUsuario", loginUsuario);
                operationBinding = executeOperBinding(params, "bloqueoPreguntasPorEvaluacion");
                if(null != operationBinding.getResult()){
                    esBloqueo = (Boolean)operationBinding.getResult();
                }else{
                    LOGGER.warning("No se obtuvo valor de retorno del metodo bloqueoPreguntasPorEvaluacion.");
                }
            } else {
                LOGGER.warning("No aplica para el bloqueo de preguntas.");
                esBloqueo = Boolean.TRUE;
            }
        }catch(Exception e){
            LOGGER.warning("Error en bloquearPreguntasEvaluacion.", e);
        }
        return esBloqueo;
    }
    
    public void notificarCorreoFinalizar() {
        LOGGER.warning("Inside notificarCorreoFinalizar.");
        
        EvaluacionesBean evaluacionesBean =
            (EvaluacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.EvaluacionesManagedBean}");
        
        LOGGER.warning("eventoEvaluacion :"+evaluacionesBean.getEventoEvaluacion());
        
        if (null != evaluacionesBean.getEventoEvaluacion() && 
            (evaluacionesBean.getEventoEvaluacion().compareTo(EVALUACION_MEDIO_TERMINO) == 0 || 
            evaluacionesBean.getEventoEvaluacion().compareTo(EVALUACION_EX_POST) == 0) ) {
            
            LOGGER.warning("Si aplica notificacion para el tipo de evaluacion.");
            
            if (notificarTareaFinalizada()) {
                LOGGER.warning("Consumo de servicio de notificacion exitosa");
            } else {
                // No se definio accion alguna cuando exista un error en la notificacion
                LOGGER.warning("Error al consumir el servicio de notificacion.");
            }
            
        } else {
            LOGGER.warning("No aplica notificacion para el tipo de evaluacion.");
        }
    }
    //========== SPS | 05/12/2019 | Metodos de utilerias para la validacion de CA  ============= //
    /**
     * Método empleado para la validacion de los componentes de Clasificacion Ambiental al finalizar la tarea.
     * @author : S&P Solutions
     * @param  : 
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 05/12/2019
     */
    public boolean validacionesCA() {
        boolean banError = true;
        if(esEditable()){
            int cont = obtenerNoComponentes();
            LOGGER.log(ADFLogger.WARNING, "validacionesCA - #Componentes:"+cont);
            erroresCA = new ArrayList<String>();
            erroresCA.clear();
            for (int x = 1; x <=cont; x++) {
                obtenerValoresComponentes(x);
            }
            
            if (!erroresCA.isEmpty()){
                banError = false;
              
            } 
        }
       
    return banError;
    }
    /**
     * Método auxiliar empleado para validar si la seccion de Clasificacion Ambiental es editable o no.
     * @author : S&P Solutions
     * @param  : 
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 06/01/2020
     */
    public boolean esEditable(){
        boolean editable = true;
        UIComponent ui =  obtenerComponente(ID_LBL_SECTOR);
        if(ui!=null){
            editable = false;
        }
        
        LOGGER.log(ADFLogger.WARNING, "esEditable:"+editable);
        return editable;
    } 
    /**
     * Método auxiliar empleado para obtener el # de elementos en la seccion de Clasificacion Ambiental.
     * @author : S&P Solutions
     * @param  : 
     * @return : int
     * @version: v1.0
     * @Fecha  : 02/01/2020
     */
    public int obtenerNoComponentes() {
        UIComponent panelPadre = obtenerComponente(ID_PANELCMP);
        List<UIComponent> hijos = panelPadre.getChildren();
        LOGGER.log(ADFLogger.WARNING, "obtenerNoComponentes | hijos:"+hijos.size());
        int cont = 0;
        for(UIComponent ui : hijos){
            if(ui.getId().startsWith(ID_PANELPADRE)){
                cont++;
            }
        }
        
        LOGGER.log(ADFLogger.WARNING, "panelPadre | cont:"+cont);
        return cont;
    }

    /**
     * Método que busca y retorna un componente dado su id.
     * @author : S&P Solutions
     * @param  : String
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 29/06/2019
     */
    public UIComponent obtenerComponente(String id) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        UIComponent ui = findComponent(facesCtx.getViewRoot(), id);
        return ui;
    }

    /**
     * Método que busca y retorna un componente dado un componente base y el id del componente a encontrar.
     * @author :
     * @param  : UIComponent
     * @param  : String
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 29/06/2019
     */
    private UIComponent findComponent(UIComponent base, String id) {

        if (id.equals(base.getId())) {
            return base;
        }

        UIComponent children = null;
        UIComponent result = null;
        Iterator childrens = base.getFacetsAndChildren();
        while (childrens.hasNext() && (result == null)) {
            children = (UIComponent) childrens.next();
            if (id.equals(children.getId())) {
                result = children;
                break;
            }
            result = findComponent(children, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }
    
    /**
     * Método que obtiene los valores de cada componente de los formularios dinámicos.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public Boolean obtenerValoresComponentes(int index) {
        Boolean esDatosCorrectos = Boolean.TRUE;
        LOGGER.log("=======  obtenerValoresComponentes index:" + index);
        //Sector
        String sector = getValorTxtSector(obtenerComponente(ID_PANELCMP), index);
        if (sector.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
            //JSFUtils.addFacesErrorMessage("El campo Sector es requerido.");
            erroresCA.add("El campo Sector es requerido.");
        } 
        //Aporte
        String aporte = getValorTxtAporte(obtenerComponente(ID_PANELCMP), index);
        if (aporte.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
            //JSFUtils.addFacesErrorMessage("El campo Aporte es requerido.");
            erroresCA.add("El campo Aporte es requerido.");
        }
        //Categoria (como requerida)
        /**
         * Eliminacion de Categoria Ambiental
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        /*String categoria = getValorTxtCategoria(obtenerComponente(ID_PANELCMP), index);
        if (categoria.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
          // JSFUtils.addFacesErrorMessage("El campo Categoría es requerido.");
          erroresCA.add("El campo Categoría es requerido.");
        }*/
        
        //SubCategoria
        /**
         * Eliminacion de Subcategoria Ambiental (como requerida)
         * @author : Kruger
         * @Feature: 5003 
         * @version: v2.0
         * @Fecha  : 20/10/21
         * @Step   : *
         **/
        /*String subcategoria = getValorTxtSubCategoria(obtenerComponente(ID_PANELCMP), index);
        if (subcategoria.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
         //   JSFUtils.addFacesErrorMessage("El campo Subcategoría es requerido.");
         erroresCA.add("El campo Subcategoría es requerido.");
        } */
        
        return esDatosCorrectos;
       
    }
    
    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtSector(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_SECTOR + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("===  getValorTxtSector  | index:" + index + "| SECTOR value:" + valor);
        return valor;
    }

    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtAporte(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_APORTE + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("===  getValorTxtAporte  | index:" + index + "| APORTE value:" + valor);
        return valor;

    }

    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtCategoria(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_CATEGORIA + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("===  getValorTxtCategoria  | index:" + index + "| CATEGORIA value:" + valor);
        return valor;
    }


    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtSubCategoria(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_SUBCATEGORIA + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("===  getValorTxtSubCategoria  | index:" + index + "| SUBCATEGORIA value:" + valor);
        return valor;
    }
    
    
    public void setErroresCA(ArrayList<String> erroresCA) {
        this.erroresCA = erroresCA;
    }

    public ArrayList<String> getErroresCA() {
        return erroresCA;
    }
    //========== SPS | 05/12/2019 | Fin metodos ============= //
}
