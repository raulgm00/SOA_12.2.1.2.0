package pa09adquisicionght.bean;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.view.adquisiciones.noobjecion.NoObjecionBean;

public class AdquisicionesActionBean extends FenixActionBean{
    
    public static ADFLogger logger = null;
    private RichPanelFormLayout pflAdquisicionFormUIC;
    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;
    public static final String BUNDLE = "pa09adquisicionght.PA09AdquisicionGHTBundle";
    public static final Integer OFICIAL_ADQUISICIONES = 6;
    public static final Integer ABOGADO = 11;
    public static final Integer ANALISTA_SUPERVISION_OPERACIONES = 49;
    
    private String documentoVista;
    private Boolean  servicioRespuesta;
    private String mensaje;

    public AdquisicionesActionBean() {
        super();
        if (logger == null){
            logger = ADFLogger.createADFLogger(AdquisicionesActionBean.class);
        }
    }

    public void setPflAdquisicionFormUIC(RichPanelFormLayout pflAdquisicionFormUIC) {
        this.pflAdquisicionFormUIC = pflAdquisicionFormUIC;
    }

    public RichPanelFormLayout getPflAdquisicionFormUIC() {
        return pflAdquisicionFormUIC;
    }

    public void seleccionarAdquisicion(SelectionEvent selectionEvent) {
        
        logger.warning("Entra a seleccionarAdquisicion");
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPflAdquisicionFormUIC());
    }


    public void agregarActionListener(ActionEvent actionEvent) {
        logger.warning("Entra a agregarActionListener");
        
        //Se habilitan/deshabilitan camponentes
    
        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        adquisicionBean.setDisableFormulario(Boolean.FALSE);
        
        //Se crea row para un nuevo registro en BD
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        //metodo para obtener si el produto es 'Linea global de credito'
        operationBinding = bindings.getOperationBinding("agregarNuevaRow");
        //operationBinding.getParamsMap().put("idSolicitud", Long.valueOf(idSolicitud));
        //operationBinding.getParamsMap().put("rol", rol);
        operationBinding.execute();
        
        
    }

    public void modificarActionListener(ActionEvent actionEvent) {
        logger.warning("Entra a modificarActionListener");
        
        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        adquisicionBean.setDisableFormulario(Boolean.FALSE);
        
    }

    public void eliminarAdquisicion(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void guardarAdquisicion(ActionEvent actionEvent) {
        logger.warning("Entra a guardarAdquisicion");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        //metodo para obtener si el produto es 'Linea global de credito'
        operationBinding = bindings.getOperationBinding("guardarAdquisicion");
        //operationBinding.getParamsMap().put("idSolicitud", Long.valueOf(idSolicitud));
        //operationBinding.getParamsMap().put("rol", rol);
        operationBinding.execute();
    }
    
    public String invokeFinalizar() {
        logger.warning("Inicia validacion para mostrar mensaje de confirmacion de Finalizar tarea");
        
        try {
            AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
            List<String> bundleKeyErrorList = new ArrayList<String>();
            List<String> msgErrorList = new ArrayList<String>();
            Boolean isValidFinalizarTarea = Boolean.TRUE;
            Boolean condicion1 = Boolean.FALSE;
            Boolean condicion2 = Boolean.FALSE;
            Boolean condicion3 = Boolean.FALSE;
            Boolean isValidDocumentos = Boolean.FALSE;
            String accion = "finalizarTarea";
            int codigoTarea = getCodigoTarea();
            Long IdOperacion = getIdOperacion(); 
            Long idNoObjecion = adquisicionBean.getIdNoObjecion();

            logger.warning("accion: " + accion);
            logger.warning("codigoTarea: " + codigoTarea);
            logger.warning("codigoOperacion: " + IdOperacion);
            
            switch (codigoTarea) {
                case FenixConstants.PA09_REVISAR_ASPECTOS_JURIDICOS:
                    logger.fine("PA09_REVISAR_ASPECTOS_JURIDICOS");
                    logger.warning("Revisar aspectos juridicos");
                    // RN_01 En caso de que la participación del área para la No Objeción sea obligatoria, se debe contar con el documento de “Opinión Jurídica de la No Objeción”
                    // asociado a la adquisición para poder finalizar la tarea. De lo contrario, se podrá finalizar la tarea agregando un comentario.
                    if (validaParticipacion()) {
                        condicion1 = validarDocumentosTarea(IdOperacion, codigoTarea, accion); //validarDocumentoPorNumeroSerieDocumento(getIdOperacion(), FenixConstants.DOC_REVISAR_ASPECTOS_JURIDICOS, adquisicionBean.getNumeroSerieDocumento());
                        condicion3=validaDocumentoAsociadoEmitir(FenixConstants.DOC_REVISAR_ASPECTOS_JURIDICOS);
                        logger.warning("Condicion1 -> validarDocumentoPorNumeroSerieDocumento: " + condicion1);
                        logger.warning("Condicion3 -> validaDocumentoAsociadoEmitir: " + condicion3);
                        if (!condicion1) {
                            bundleKeyErrorList.add("MENSAJE_EMITIR");
                            }
                        if (!condicion3) {
                            bundleKeyErrorList.add("MSG_ASPECTOS_JURIDICOS");
                        }
                        isValidFinalizarTarea = (condicion1 && condicion3);
                    } else {
                        condicion2 = validateComentario(getIdOperacion(), adquisicionBean.getIdTarea(), getInstanciaTarea());
                        logger.warning("condicion2 -> validateComentario: " + condicion2);
                        if (!condicion2) {
                            bundleKeyErrorList.add("MSG_COMENTARIO");
                        }
                        isValidFinalizarTarea = condicion2;
                    }
                break;
                case FenixConstants.PA09_REVISAR_ASPECTOS_NORMATIVOS:
                    logger.fine("PA09_REVISAR_ASPECTOS_NORMATIVOS");
                    logger.warning("Revisar aspectos normativos");
                    // RN_01 En caso de que la participación del área para la No Objeción sea obligatoria, se debe contar con el documento de “Opinión Jurídica de la No Objeción”
                    // asociado a la adquisición para poder finalizar la tarea. De lo contrario, se podrá finalizar la tarea agregando un comentario.
                    
                    if (validaParticipacion()) {
                        condicion1 = validarDocumentosTarea(IdOperacion, codigoTarea, accion); //validarDocumentoPorNumeroSerieDocumento(getIdOperacion(), FenixConstants.DOC_REVISAR_ASPECTOS_NORMATIVOS, adquisicionBean.getNumeroSerieDocumento());
                        condicion3=validaDocumentoAsociadoEmitir(FenixConstants.DOC_REVISAR_ASPECTOS_NORMATIVOS);
                        logger.warning("Condicion 1 -> validarDocumentoPorNumeroSerieDocumento: "+condicion1);
                        logger.warning("Condicion 3 -> validaDocumentoAsociadoEmitir: "+condicion3);
                        if (!condicion1) {
                            bundleKeyErrorList.add("MENSAJE_EMITIR");
                            logger.warning("MENSAJE_EMITIR");
                            }
                        if (!condicion3) {
                            bundleKeyErrorList.add("MSG_ASPECTOS_NORMATIVOS");
                            logger.warning("MSG_ASPECTOS_NORMATIVOS");
                        }
                        isValidFinalizarTarea = (condicion1 && condicion3);
                    } 
                    else 
                    {
                        condicion2 = validateComentario(getIdOperacion(), adquisicionBean.getIdTarea(), getInstanciaTarea());
                        logger.warning("Condicion 2 -> validateComentario: " + condicion2);
                        if (!condicion2) {
                            bundleKeyErrorList.add("MSG03_EMITIR_RESPUESTA");
                        }
                        isValidFinalizarTarea = condicion2;
                    }
                break;
                case FenixConstants.PA09_REVISAR_ASPECTOS_TECNICOS:
                    logger.fine("PA09_REVISAR_ASPECTOS_TECNICOS");
                    logger.warning("Revisar aspectos tecnicos");
                    // RN_01 En caso de que la participación del área para la No Objeción sea obligatoria, se debe contar con el documento de “Opinión Jurídica de la No Objeción”
                    // asociado a la adquisición para poder finalizar la tarea. De lo contrario, se podrá finalizar la tarea agregando un comentario.
                    
                    if (validaParticipacion()) 
                    {
                        condicion1 = validarDocumentosTarea(IdOperacion, codigoTarea, accion); //validarDocumentoPorNumeroSerieDocumento(getIdOperacion(), FenixConstants.DOC_REVISAR_ASPECTOS_TECNICOS, adquisicionBean.getNumeroSerieDocumento());
                        condicion3=validaDocumentoAsociadoEmitir(FenixConstants.DOC_REVISAR_ASPECTOS_TECNICOS);
                        logger.warning("Condicion 1 -> validarDocumentoPorNumeroSerieDocumento: " + condicion1);
                        logger.warning("Condicion 3 -> validarDocumentoPorNumeroSerieDocumento: " + condicion3);
                        if (!condicion1) {
                            bundleKeyErrorList.add("MENSAJE_EMITIR");
                                }
                        if (!condicion3) {
                            bundleKeyErrorList.add("MSG_ASPECTOS_TECNICOS");
                            logger.warning("MSG_ASPECTOS_TECNICOS");
                        }
                                isValidFinalizarTarea = (condicion1 && condicion3);
                    }
                    else 
                    {
                        condicion2 = validateComentario(getIdOperacion(), adquisicionBean.getIdTarea(), getInstanciaTarea());
                        logger.warning("Condicion 2 -> validateComentario: " + condicion2);
                        if (!condicion2) 
                        {
                            bundleKeyErrorList.add("MSG_COMENTARIO");
                            logger.warning("MSG_COMENTARIO");
                        }
                        isValidFinalizarTarea = condicion2;
                    }
                break;
                case FenixConstants.PA09_REALIZAR_AJUSTES:
                    logger.fine("PA09_REALIZAR_AJUSTES");
                    logger.warning("Realizar ajustes");
                
                    Integer idResultadoProceso = obtenerResultadoProceso(idNoObjecion);
                    Integer TipoResultadoProcesoDesierto = 3;
                        
                    if(!(idResultadoProceso.compareTo(TipoResultadoProcesoDesierto) == 0))
                    {    
                        if (montoAdjudicatariosValido())
                        {
                        OperationBinding validarCamposBinding = null;
                        validarCamposBinding = ADFUtils.findOperation("validarCampos");
                        validarCamposBinding.getParamsMap().put("idAdquisicion", adquisicionBean.getNumAdquisicion());
                        
                        HashMap<String, Object> resultMap = (HashMap<String, Object>)validarCamposBinding.execute();
                        
                        isValidFinalizarTarea = !exitenErrores(resultMap);
                        logger.warning("isValidFinalizarTarea:"+isValidFinalizarTarea);
                        
                            if (!isValidFinalizarTarea)
                            {
                            msgErrorList.addAll(obtenerListaErrores(resultMap));
                        }
                        } 
                        else 
                        {
                        isValidFinalizarTarea = Boolean.FALSE;
                        msgErrorList.add(ADFUtils.getStringFromBundle("MONTO_ADJUDICATARIOS_INVALIDA", BUNDLE));
                        }
                }

                break;
                case FenixConstants.PA09_EMITIR_RESPUESTA_CLIENTE:
                    logger.fine("PA09_EMITIR_RESPUESTA_CLIENTE");
                    logger.warning("Emitir respuesta cliente");
                    condicion1=validarCampos();
                    condicion2 = validaDocumentoAsociadoEmitir(FenixConstants.DOC_EMITIR_RESPUESTA_CLIENTE);
                    condicion3 = validarDocumentosTarea(IdOperacion, codigoTarea, accion); //validarDocumentoPorNumeroSerieDocumento(getIdOperacion(), FenixConstants.DOC_EMITIR_RESPUESTA_CLIENTE, adquisicionBean.getNumeroSerieDocumento());
                    logger.warning("Condicion1 -> validarCampos: "+condicion2);
                    logger.warning("Condicion2 -> validaDocumentoAsociadoEmitir: "+condicion2);
                    logger.warning("Condicion3 -> validarDocumentoPorNumeroSerieDocumento: "+condicion3);
                    if (!condicion1) {
                        bundleKeyErrorList.add("MENSAJE_EMITIR");
                    }
                    if (!condicion2) {
                        bundleKeyErrorList.add("MSG03_EMITIR_RESPUESTA");
                    }
                    if (!condicion3) {
                        bundleKeyErrorList.add("MSG02_EMITIR_RESPUESTA");
                    }
                isValidFinalizarTarea = condicion1 && condicion2 && condicion3;
                break;
                default:
                break;
            }
            if (!isValidFinalizarTarea) {
                logger.warning("Entra imprimir bundle Error Principal");
                if (bundleKeyErrorList.size() > 0) {
                    for (String bundleKey : bundleKeyErrorList)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
                
                logger.warning("Entra imprimir mensajes Error Principal");
                if (msgErrorList.size() > 0) {
                    for (String message : msgErrorList) {
                        logger.warning("message:"+message);
                        JSFUtils.addFacesErrorMessage(message);
                    }
                        
                }
            } else {
                finalizarPopup();
            }
        } catch(Exception e) {
            logger.warning("Error al invocar finalizar tarea:", e);
        }
            
        return "";
        }
    
    public Integer obtenerResultadoProceso(Long idNoObjecion){
        logger.warning("Inicio obtenerResultadoProceso");
        Integer idResultadoProceso = new Integer("0");
        try{
            logger.warning("Obtener idResultadoProceso");
            Map resultadoMap = new HashMap(); 
            logger.warning("idNoObjecion: " + idNoObjecion);
            BindingContainer binding = ADFUtils.getBindingContainer(); 
            OperationBinding operationBinding = binding.getOperationBinding("obtenerTipoResultado");
            operationBinding.getParamsMap().put("idObjecion", idNoObjecion); 
            operationBinding.execute();
            
            logger.warning("getResult: " + operationBinding.getResult());
            resultadoMap = (HashMap)operationBinding.getResult(); 
            if((Boolean)resultadoMap.get("resultado"))
            { 
                idResultadoProceso = resultadoMap.get("tipoResultado") != null? (Integer)resultadoMap.get("tipoResultado") : 0; 
                logger.warning("idResultadoProceso: " + idResultadoProceso);
            }
        }
        catch(Exception e)
        {    
            logger.warning("Error AdquisicionesActionBean.obtenerResultadoProceso: " + e);
        }
        return idResultadoProceso;
    }  
    public String invokeMasInformacion() {
            logger.warning("Se validan las condiciones para mostrar mensaje de confirmacion al solicitar Mas informacion");
            
            AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
            List<String> bundleKeyErrorList = new ArrayList<String>();
            Boolean isValidMasInformacion = Boolean.TRUE;
            Boolean condicion1 = Boolean.FALSE;
            Boolean condicion2 = Boolean.FALSE;
            Boolean condicion3 = Boolean.FALSE;
            int codigoTarea = getCodigoTarea();
            recuperaDatos();
            switch (codigoTarea) {
                case FenixConstants.PA09_REVISAR_ASPECTOS_JURIDICOS:
                    logger.fine("PA09_REVISAR_ASPECTOS_JURIDICOS");
                    logger.warning("Revisar aspectos juridicos");
                    isValidMasInformacion= validateComentario(getIdOperacion(), adquisicionBean.getIdTarea(), getInstanciaTarea());
                    if(!isValidMasInformacion){
                        bundleKeyErrorList.add("MSG_COMENTARIO");
                    }
                break;
                case FenixConstants.PA09_REVISAR_ASPECTOS_NORMATIVOS:
                    logger.fine("PA09_REVISAR_ASPECTOS_NORMATIVOS");
                    logger.warning("Revisar aspectos normativos");
                    isValidMasInformacion= validateComentario(getIdOperacion(), adquisicionBean.getIdTarea(), getInstanciaTarea());
                    if(!isValidMasInformacion){
                        bundleKeyErrorList.add("MSG_COMENTARIO");
                    }
                break;
                case FenixConstants.PA09_REVISAR_ASPECTOS_TECNICOS:
                    logger.fine("PA09_REVISAR_ASPECTOS_TECNICOS");
                    logger.warning("Revisar aspectos tecnicos");
                    isValidMasInformacion= validateComentario(getIdOperacion(), adquisicionBean.getIdTarea(), getInstanciaTarea());
                    if(!isValidMasInformacion){
                        bundleKeyErrorList.add("MSG_COMENTARIO");
                    }
                break;
                case FenixConstants.PA09_REALIZAR_AJUSTES:
                    logger.fine("PA09_REALIZAR_AJUSTES");
                    logger.warning("Realizar ajustes");
                break;
                case FenixConstants.PA09_EMITIR_RESPUESTA_CLIENTE:
                    logger.fine("PA09_EMITIR_RESPUESTA_CLIENTE");
                    logger.warning("Emitir respuesta cliente");
                break;
                default:
                break;
            }
            if (!isValidMasInformacion) {
                logger.warning("Entra imprimir bundle Error Principal");
                if (bundleKeyErrorList.size() > 0) {
                    for (String bundleKey : bundleKeyErrorList)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
            } else {
                masInfoPopup();
                recuperaDatos();
            }
        return null;
    }
    
    public String aceptarFinalizar() {
        logger.warning("Inside aceptarFinalizarTarea");

        popupFinalizarTarea.hide();

        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");

        adquisicionBean.setStartTime(System.currentTimeMillis());
        logger.warning("Tiempo de inicio respuesta del: " + adquisicionBean.getStartTime() + " segundos");
                
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        String responseValidFinalizarTarea = "";
        List<String> bundleKeyErrorList = new ArrayList<String>();

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
        case FenixConstants.PA09_REVISAR_ASPECTOS_JURIDICOS:
            logger.fine("PA09_REVISAR_ASPECTOS_JURIDICOS");
            logger.warning("Revisar aspectos juridicos");
            
            isValidFinalizarTarea = Boolean.TRUE;

            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                bundleKeyErrorList.add("No es posible actualizar los datos para Revisar aspectos juridicos.");
            }
            break;
        case FenixConstants.PA09_REVISAR_ASPECTOS_NORMATIVOS:
            logger.fine("PA09_REVISAR_ASPECTOS_NORMATIVOS");
            logger.warning("Revisar aspectos normativos");
            
            isValidFinalizarTarea = Boolean.TRUE;

            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                bundleKeyErrorList.add("No es posible actualizar los datos para Revisar aspectos normativos.");
            }
            
            break;
        case FenixConstants.PA09_REVISAR_ASPECTOS_TECNICOS:
            logger.fine("PA09_REVISAR_ASPECTOS_TECNICOS");
            logger.warning("Revisar aspectos tecnicos");
            
            isValidFinalizarTarea = Boolean.TRUE;

            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                bundleKeyErrorList.add("No es posible actualizar los datos para Revisar aspectos tecnicos.");
            }
            break;
        case FenixConstants.PA09_REALIZAR_AJUSTES:
            logger.fine("PA09_REALIZAR_AJUSTES");
            logger.warning("Realizar ajustes");
            
            responseValidFinalizarTarea = guardarNoObjecion();
            if(responseValidFinalizarTarea == "OK")
            {
                responseValidFinalizarTarea = guardarNoObjecion();
                if(responseValidFinalizarTarea == "OK")
                {
                    isValidFinalizarTarea = Boolean.TRUE;
                }
            }
            
            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                bundleKeyErrorList.add(responseValidFinalizarTarea);
            }
            break;
        case FenixConstants.PA09_EMITIR_RESPUESTA_CLIENTE:
            logger.fine("PA09_EMITIR_RESPUESTA_CLIENTE");
            logger.warning("Emitir respuesta cliente");
          
            responseValidFinalizarTarea = actualizaEmiteObjecion();
            if(responseValidFinalizarTarea == "OK")
            { 
                isValidFinalizarTarea = Boolean.TRUE; 
            } 
            if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                bundleKeyErrorList.add(responseValidFinalizarTarea);
            }
            break;
        default:
            break;
        }
        logger.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): " + isValidFinalizarTarea);
        if (isValidFinalizarTarea) {
            logger.warning("Se finaliza la tarea de adquisiciones y pasa bpm");
            cargarDocumentosOnBase();
            logger.warning("Actualizar registros en tre evidencia adquisicion.");
            actualizarSePuedeEliminarTreEvidenciaAdquisicion();
            adquisicionBean.setEndTime(System.currentTimeMillis());
            logger.warning("Tiempo de respuesta del: " + adquisicionBean.getEndTime() + " segundos");
            logger.warning("Tiempo de respuesta del metodo Salida derealizar ajustes adquisiciones " + (adquisicionBean.getEndTime() - adquisicionBean.getStartTime())/1000 + " segundos");
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            if (bundleKeyErrorList.size() > 0) 
            {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(bundleKey);
            }
            logger.warning("Se cancela la confirmacion de Finalizar tarea");
            return null;
        }
    }

    public String aceptarMasInformacion() {
        logger.warning("inside aceptarMasInformacion");

        popupMasInformacion.hide();

        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");

        Boolean isValidMasInformacion = Boolean.TRUE;

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
            case FenixConstants.PA09_REVISAR_ASPECTOS_JURIDICOS:
                logger.fine("PA09_REVISAR_ASPECTOS_JURIDICOS");
                logger.warning("Revisar aspectos juridicos");
            actualizarPayloadElement("requiereMasInfoASJUR", Boolean.TRUE);
                
            break;
            case FenixConstants.PA09_REVISAR_ASPECTOS_NORMATIVOS:
                logger.fine("PA09_REVISAR_ASPECTOS_NORMATIVOS");
                logger.warning("Revisar aspectos normativos");
            actualizarPayloadElement("requiereMasInfoAdquisicion", Boolean.TRUE);
                
            break;
            case FenixConstants.PA09_REVISAR_ASPECTOS_TECNICOS:
                logger.fine("PA09_REVISAR_ASPECTOS_TECNICOS");
                logger.warning("Revisar aspectos tecnicos");
            actualizarPayloadElement("requiereMasInfoSupervision", Boolean.TRUE);
                
            break;
            case FenixConstants.PA09_REALIZAR_AJUSTES:
                logger.fine("PA09_REALIZAR_AJUSTES");
                logger.warning("Realizar ajustes");
            
            break;
            case FenixConstants.PA09_EMITIR_RESPUESTA_CLIENTE:
                logger.fine("PA09_EMITIR_RESPUESTA_CLIENTE");
                logger.warning("Emitir respuesta cliente");
                                
            break;
            default:
            break;
        }

        if (isValidMasInformacion) {
            logger.log(ADFLogger.WARNING, "INTO aceptar mas Informacion. el valor es. :" + isValidMasInformacion);
            logger.warning("Se finaliza la tarea de adquisiciones y pasa bpm");
            logger.warning("Se actualiza tre evidencia adquisicion.");
            actualizarSePuedeEliminarTreEvidenciaAdquisicion();
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            logger.log(ADFLogger.ERROR, "aceptarMasInformacion(): " + isValidMasInformacion);
            return null;
        }
    }
    
    public Boolean actualizarSePuedeEliminarTreEvidenciaAdquisicion() {
        logger.warning("Entra en actualizarSePuedeEliminarTreEvidenciaAdquisicion");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        Long idAdquisicion = null;
        try{
            AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
            idAdquisicion = adquisicionBean.getNumAdquisicion();
            //para actualizar la columna SE_PUEDE_ELIMINAR en TRE_EVIDENCIA_CONDICION
            operationBindingIdOp = bindingsIdOp.getOperationBinding("actualizarSePuedeEliminarTreEvidenciaAdquisicion");
            operationBindingIdOp.getParamsMap().put("idAdquisicion", idAdquisicion);
            resultado = (Boolean) operationBindingIdOp.execute();
            if (!operationBindingIdOp.getErrors().isEmpty()) {
                logger.warning("error al actualizar los registros en treEvidenciaAdquisicion");
                resultado = Boolean.FALSE;
            }
        }catch(Exception e){
            logger.warning("Error en actualizarSePuedeEliminarTreEvidenciaAdquisicion.", e);
        }

        logger.warning("valor obtenido: " + resultado);
        return resultado;
    }
    
    public Boolean validaCamposAjustes() {
        logger.warning("inside validaCamposAjustes.");
        
        Boolean result = Boolean.TRUE;
        
        logger.warning("validaCamposAjustes: " + result);
        return result;
    }
    
    public Boolean validaParticipacion() {
        logger.warning("inside validaParticipacion.");
        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        Boolean result = Boolean.FALSE;
        Integer valor = null;
        Integer participante = null;
        
        participante = asignarParticipante();
        
        BindingContainer bindings = getBindings();
        
        OperationBinding operationBindingValidar = bindings.getOperationBinding("campo");
        operationBindingValidar.getParamsMap().put("idObjecion", adquisicionBean.getIdNoObjecion());
        operationBindingValidar.getParamsMap().put("rol", participante);
        operationBindingValidar.execute();
        
        if(null != operationBindingValidar.getResult()){
            valor = (Integer)operationBindingValidar.getResult();
            if (valor.compareTo(2) == 0) {
                result = Boolean.TRUE;
            }
        } else {
            logger.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
        }
        logger.warning("validaParticipacion: " + result);
        return result;
    }
    
    public Integer asignarParticipante() {
        logger.warning("inside asignarParticipante.");
        Integer result = null;
        
        if (getCodigoTarea() != null) {
            if (getCodigoTarea().compareTo(FenixConstants.PA09_REVISAR_ASPECTOS_JURIDICOS) == 0 ) {
                result = ABOGADO;
            }
            if (getCodigoTarea().compareTo(FenixConstants.PA09_REVISAR_ASPECTOS_NORMATIVOS) == 0 ) {
                result = OFICIAL_ADQUISICIONES;
            }
            if (getCodigoTarea().compareTo(FenixConstants.PA09_REVISAR_ASPECTOS_TECNICOS) == 0 ) {
                result = ANALISTA_SUPERVISION_OPERACIONES;
            }    
        }
        return result;
    }
    
    public Boolean validaCamposEmitir() {
        logger.warning("inside validaCamposEmitir.");
        
        Boolean result = Boolean.FALSE;
        
        BindingContainer bindings = getBindings();
        
        OperationBinding operationBindingValidar = bindings.getOperationBinding("validaCamposEmitir");
        operationBindingValidar.execute();
        
        if (null != operationBindingValidar.getResult()) {
            result = (Boolean)operationBindingValidar.getResult();
        } else {
            logger.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
        }
        logger.warning("result: " + result);
        return result;
    }
    
    public Boolean validaDocumentoAsociadoEmitir(Integer tipoDocumento) {
        logger.warning("inside validaDocumentoEmitir.");
            
        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        Boolean result = Boolean.FALSE;
        
        BindingContainer bindings = getBindings();
        
        OperationBinding operationBindingValidar = bindings.getOperationBinding("validaDocumentoAsociadoPorTipoDocumento");
        operationBindingValidar.getParamsMap().put("idAdquisicion", adquisicionBean.getNumAdquisicion());
        operationBindingValidar.getParamsMap().put("numSerie", adquisicionBean.getNumeroSerieDocumento().longValue());
        operationBindingValidar.getParamsMap().put("idTipoDocumento", tipoDocumento);
        operationBindingValidar.execute();
        
        if(null != operationBindingValidar.getResult()) {
            result = (Boolean)operationBindingValidar.getResult();
        } else {
            logger.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
        }
        logger.warning("validaDocumentoAsociadoEmitir: " + result);
        return result;
    }
    
    
    public Boolean validateDocumentoPorTipo(Integer idTipoDocumento)
    {
        logger.warning("Entra a validateDocumentoPorTipo");
        
        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        Boolean result = Boolean.FALSE;
        
        BindingContainer bindings = getBindings();
        
        String tipoDoc = String.valueOf(idTipoDocumento);
        OperationBinding operationBindingValidar = bindings.getOperationBinding("validaDocumentoAsociadoPorTipoDocumento");
        operationBindingValidar.getParamsMap().put("idAdquisicion", adquisicionBean.getNumAdquisicion());
        operationBindingValidar.getParamsMap().put("idTipoDocumento", tipoDoc);
        operationBindingValidar.execute();
        
        if(null != operationBindingValidar.getResult()) {
            result = (Boolean)operationBindingValidar.getResult();
        } else {
            logger.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
        }
        logger.warning("validateDocumentoPorTipo: " + result);
        return result;
    }
    
    public Boolean actualizarNoObjecion() {
        logger.warning("inside actualizarNoObjecion");

        Boolean finaliza = Boolean.TRUE;

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarNoObjecion");
        logger.warning("Se ejecuta metodo AM Actualizar No Objecion");
        finaliza = (Boolean) operationBinding.execute();
        logger.warning("Resultado: " + finaliza);
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return false;
        }

        return finaliza;
    }
    
    public Boolean actualizarRespuesta() {
        logger.warning("inside actualizarRespuesta");

        Boolean finaliza = Boolean.TRUE;

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarRespuesta");
        logger.warning("Se ejecuta metodo AM Actualizar Respuesta");
        finaliza = (Boolean) operationBinding.execute();
        logger.warning("Resultado: " + finaliza);
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return false;
        }

        return finaliza;
    }
       
    public Boolean actualizaAdquisicion() {
        logger.warning("inside actualizaAdquisicion");
        Boolean finaliza = Boolean.TRUE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("cargaAdquisicion");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return false;
        }
        return finaliza;
    }
    
    public Boolean actualizaObjecion() {
        logger.warning("inside actualizaAdquisicion");
        Boolean finaliza = Boolean.TRUE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("precargarAdquisicion");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return false;
        }
        return finaliza;
    }  //recargaConcursantes

    public Boolean seleccionarObjecion() {
        logger.warning("inside seleccionarNoObjecion");
        Boolean finaliza = Boolean.TRUE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("seleccionarNoObjecion");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return false;
        }
        return finaliza;
    }
    public Boolean recargaConcursantes() {
        logger.warning("inside actualizaAdquisicion");
        Boolean finaliza = Boolean.TRUE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("recargaConcursantes");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return false;
        }
        return finaliza;
    }
    public void recuperaDatos(){
            //seleccionarObjecion();
            actualizaAdquisicion();
            actualizaObjecion();
            recargaConcursantes();
        }
    
    private Integer getCodigoTarea() {
        logger.warning("inside getCodigoTarea");

        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");

        if (null != adquisicionBean.getIdTarea() && adquisicionBean.getIdTarea().trim().length() > 0) {
            return Integer.parseInt(adquisicionBean.getIdTarea());
        }
        return 0;
    }
    
    private Long getIdOperacion() {
        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");

        if (null != adquisicionBean.getIdOperacion() && adquisicionBean.getIdOperacion().trim().length() > 0) {
            return Long.parseLong(adquisicionBean.getIdOperacion());
        }

        return 0L;
    }
    
    public String cancelarFinalizarTarea() {
        logger.warning("Cancela confirmacion de finalizar tarea");

        popupFinalizarTarea.hide();
        return null;
    }
    
    public String cancelarMasInformacion() {
        logger.warning("Cancela confirmacion de finalizar tarea");

        popupMasInformacion.hide();
        return null;
    }
    
    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea) {
        this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupFinalizarTarea() {
        return popupFinalizarTarea;
    }
    
    public void finalizarPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }
    
    public void masInfoPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupMasInformacion.show(hints);
    }
    
    public RichPopup getPopupMasInformacion() {
        return popupMasInformacion;
    }

    public void setPopupMasInformacion(RichPopup popupMasInformacion) {
        this.popupMasInformacion = popupMasInformacion;
    }
    
//    public static void main(String[] args){
//        System.out.println("AdquisicionBean");
//        
//        //Test de lectura de mensajes de error
//        Map<String, Object> map = new HashMap<>();
//        map.put("mensaje1", false);
//        map.put("mensaje2", false);
//        map.put("mensaje3", false);
//        
//        Map<String, Object> mapMensajes = new HashMap<>();
//        mapMensajes.put("mensaje1", "mensaje1");
//        mapMensajes.put("mensaje2", "mensaje2");
//        mapMensajes.put("mensaje3", "mensaje3");
//        
//        map.put("mensajes", mapMensajes);
//        
//        if (exitenErrores(map)) {
//            List<String> listaErrores = obtenerListaErrores(map);
//            
//            for(String mensaje: listaErrores) {
//                System.out.println(mensaje);
//            }
//        }
//    }
    
    private boolean exitenErrores(Map<String, Object> map) {
        boolean existenErrores = Boolean.FALSE;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            if (value instanceof Boolean) {
                if (null != value && ((Boolean)value)) {
                    existenErrores = Boolean.TRUE;
                }
            }
        }
        return existenErrores;
    }
    
    @SuppressWarnings("unchecked")
    private List<String> obtenerListaErrores(Map<String, Object> map) {
        List<String> bundleKeyErrorList = new ArrayList<String>();
        Map<String, Object> mapObtMsg = (Map<String, Object>)map.get("mensajes");
        
        logger.warning("mensajes.map:"+ mapObtMsg);
        for(Map.Entry<String, Object> entry : mapObtMsg.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            logger.warning("obtenerListaErrores.key("+key+").value("+value+")");
        }
        
        logger.warning("obtenerListaErrores.map");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            logger.warning("obtenerListaErrores.key("+key+").value("+value+")");
            
            if (value instanceof Boolean) {
                if (null != value && ((Boolean)value)) {
                    if (null != mapObtMsg) {
                        if (null != (String)mapObtMsg.get(key)) {
                            String msg = (String)mapObtMsg.get(key);
                            bundleKeyErrorList.add(msg);
                        }
                    }
                }
            }
        }
        return bundleKeyErrorList;
    }
    
    public Boolean validarCampos(){
        Boolean respuesta=Boolean.TRUE;
        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        logger.warning("he revisado:  "+ adquisicionBean.getHeRevisado());
        logger.warning("correspondencia: "+adquisicionBean.getNumeroCorrespondencia());
        logger.warning("fecha firma: "+ adquisicionBean.getFechaFirma());
        logger.warning("se otorga: "+adquisicionBean.getOtorgaObjecion());
        
        if(null!=adquisicionBean.getHeRevisado()){
            if(!adquisicionBean.getHeRevisado()){
                    respuesta=Boolean.FALSE;
                }
            }
        else{
                respuesta=Boolean.FALSE;
            }
        if(null==adquisicionBean.getNumeroCorrespondencia()){
                respuesta=Boolean.FALSE;
            }
        else{
            if(adquisicionBean.getNumeroCorrespondencia().length()<=0){
                    respuesta=Boolean.FALSE;
                }
            }
        if(null==adquisicionBean.getFechaFirma()){
                respuesta=Boolean.FALSE;
            }
        if(null==adquisicionBean.getOtorgaObjecion()){
                respuesta=Boolean.FALSE;
            }
        return respuesta;
    }
    
    public Boolean actualizarNoObjecionEmitir(){
        Boolean respuesta=Boolean.FALSE;
        return respuesta;
        }
    
    public String guardarNoObjecion(){
        logger.warning("Inicio guardarNoObjecion"); 
        String respuesta = "OK"; 
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        operationBindingIdOp = bindingsIdOp.getOperationBinding("comitNoObjecion");
        respuesta=(String)operationBindingIdOp.execute();
        if(!operationBindingIdOp.getErrors().isEmpty()){
            respuesta = "Error en el método PA09Adquisicion.AdquisicionesActionBean.guardarNoObjecion" + operationBindingIdOp.getErrors().toString();
            logger.warning(respuesta); 
        }
        logger.warning("respuesta: " + respuesta); 
        logger.warning("Fin guardarNoObjecionRealizar"); 
        return respuesta;
    }
    
    public String guardarNoObjecionRealizar(){
        logger.warning("Inicio guardarNoObjecionRealizar"); 
        String respuesta = "OK"; 
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        operationBindingIdOp = bindingsIdOp.getOperationBinding("comitNoObjecionRealizar");
        respuesta=(String)operationBindingIdOp.execute();
        if(!operationBindingIdOp.getErrors().isEmpty()){
            respuesta = "Error en el método PA09Adquisicion.AdquisicionesActionBean.guardarNoObjecionRealizar" + operationBindingIdOp.getErrors().toString();
            logger.warning(respuesta); 
        }
        logger.warning("respuesta: " + respuesta); 
        logger.warning("Fin guardarNoObjecionRealizar"); 
        return respuesta;
    }
    
    public String actualizaEmiteObjecion(){ 
        logger.warning("Inicio actualizaEmiteObjecion"); 
        String respuesta = "OK"; 
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        operationBindingIdOp = bindingsIdOp.getOperationBinding("actualizaEmitirRespuesta");
        respuesta=(String)operationBindingIdOp.execute();
        if(!operationBindingIdOp.getErrors().isEmpty()){
            respuesta = "Error en el método PA09Adquisicion.AdquisicionesActionBean.actualizaEmiteObjecion" + operationBindingIdOp.getErrors().toString();
            logger.warning(respuesta); 
            }
        logger.warning("respuesta: " + respuesta); 
        logger.warning("Fin actualizaEmiteObjecion"); 
        return respuesta;
    }
    
    public String getReporteFileNameVista() {
        logger.warning("Ingresa metodo getReporteFileNameVista... ");
        Integer publica = 0;
        Integer tipo = null;
        AdquisicionBean adquisicionBean = (AdquisicionBean)JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        //documentoVista = FenixConstants.PLANTILLA_DEFAULT;
        documentoVista = "Plantilla.pdf";
        
        Map resultadoMap = new HashMap();
        Integer idTipoNoObjecion = null; 
        Integer idResultadoProceso = null;

        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("obtenerTipoResultado");
            operationBinding.execute();
            resultadoMap = (HashMap)operationBinding.getResult();
            
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding devuelve errores metodo obtenerRowNoObjecion");
            } else {
                if((Boolean)resultadoMap.get("resultado")){
                    if(null!=(Integer)resultadoMap.get("tipoObjecion")){
                            idTipoNoObjecion=(Integer)resultadoMap.get("tipoObjecion");
                            if (idTipoNoObjecion.compareTo(FenixConstants.TIPO_NOOBJECION_AVISO_COBRO) == 0) {
                                //documentoVista = FenixConstants.PLANTILLA_AVISO_COBRO;
                                documentoVista = "Plantilla_VistaPrevia_Aviso.pdf";
                            }
                            if (idTipoNoObjecion.compareTo(FenixConstants.TIPO_NOOBJECION_CONTRATO) == 0) {
                                //documentoVista = FenixConstants.PLANTILLA_CONTRATO;
                                documentoVista = "Plantilla_VistaPrevia_Contrato.pdf";
                            }
                            if(null!=(Integer)resultadoMap.get("tipoResultado")){
                                    idResultadoProceso=(Integer)resultadoMap.get("tipoResultado");
                                    if (idResultadoProceso.compareTo(FenixConstants.TIPO_NOOBJECION_INFORME_ADJUDICADO) == 0) {
                                        //documentoVista = FenixConstants.PLANTILLA_INFORME_ADJUDICADO;
                                        documentoVista = "Plantilla_VistaPrevia_InformeResultados_Adjudicado.pdf";
                                    } else {
                                        if (idResultadoProceso.compareTo(FenixConstants.TIPO_NOOBJECION_INFORME_ANULADO) == 0) {
                                            //documentoVista = FenixConstants.PLANTILLA_INFORME_ANULADO;
                                            documentoVista = "Plantilla_VistaPrevia_InformeResultados_Adjudicado.pdf";
                                        } else {
                                            //documentoVista = FenixConstants.PLANTILLA_INFORME_OTROS;
                                            documentoVista = "Plantilla_VistaPrevia_InformeResultados_Otros.pdf";
                                        }
                                    }
                                
                                }
                            else{
                                    logger.warning("El resultado que se obtuvo del tipo de resultado no fue el correcto");
                                }
                        }
                        else{
                                logger.warning("El resultado que se obtuvo del tipo de noobjecion no fue el correcto");
                            }
                    
                    
                    }
                else{
                        logger.warning("El resultado que se obtuvo no fue el correcto");
                    }
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error en obtenerRowNoObjecion " + e.getClass() + ":" + e.getMessage());
        }

        logger.warning("Valor a retornar: " + getDocumentoVista());
        logger.warning("Finaliza metodo getReporteFileNameVista... ");
        return getDocumentoVista();
    }
    
    public void downloadVistaPrevia(FacesContext facesContext, OutputStream outputStream) {
        logger.warning("Ingresa metodo downloadVistaPrevia... ");
        AdquisicionBean adquisicionBean = (AdquisicionBean)JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        servicioRespuesta=Boolean.FALSE;
        mensaje=null;

        Long idObjecion = null;
        AttributeBinding claveIdObjecion;
        claveIdObjecion = ADFUtils.findControlBinding("Id");
        if (null != (Long) claveIdObjecion.getInputValue()) {
            idObjecion = (Long) claveIdObjecion.getInputValue();
        }
        Integer publica = 0;
        byte[] documentoVistaPublica = null;
        try {
            applyIE11Fix(getDocumentoVista());
            documentoVistaPublica = crearDoc();
            if (documentoVistaPublica != null && documentoVistaPublica.length > 0) {
                outputStream.write(documentoVistaPublica);
            }

        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            flushAndCloseOutputStream(outputStream);
        }
        logger.warning("Finaliza metodo downloadVistaPrevia... ");
    }
    
    private void applyIE11Fix(String docName) {
        logger.warning("Ingresa metodo applyIE11Fix... ");
        RequestContext requestCtx = RequestContext.getCurrentInstance();
        Agent agent = requestCtx.getAgent();
        String version = agent.getAgentVersion();
        String browser = agent.getAgentName();

        //if ie 11
        if (browser != null && "ie".equals(browser) && version != null && version.startsWith("11")) {

            HttpServletResponse resp =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            String finalName = docName;

            try {
                finalName = URLEncoder.encode(docName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.warning("Error: " + e.getMessage());
            }

            resp.setHeader("content-disposition", "attachment; filename=\"" + finalName + "\"");
            resp.setContentType("application/octet-stream");
        }
        logger.warning("Finaliza metodo applyIE11Fix... ");
    }
    
    private byte[] crearDoc() {
        logger.warning("Ingresa al metodo crearDoc");
        
        byte[]resultado=null;
        Map resultadoMap = new HashMap();
        Integer vista=0;
        try {
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("crearDoc");
                operationBinding.getParamsMap().put("vistaPublicar", vista);
            operationBinding.execute();
            resultadoMap = (HashMap)operationBinding.getResult();
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.warning("OperationBinding devuelve errores metodo descarga vista previa");
                } else {
            if(null!=(byte[])resultadoMap.get("cadena")){
                    resultado=(byte[])resultadoMap.get("cadena");
                }
            else{
                    logger.warning("Error al obtener la cadena de bytes");
                }
                }
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, "Error en obtenerRowNoObjecion " + e.getClass() + ":" + e.getMessage());
            }
        
        return resultado;
    }
        
    private void flushAndCloseOutputStream(OutputStream output) {
        logger.warning("Ingresa metodo flushAndCloseOutputStream");
        flushOutputStream(output);
        closeOutputStream(output);
        logger.warning("Finaliza metodo flushAndCloseOutputStream");
    }
    
    private void flushOutputStream(OutputStream output) {
        try {
            if (output != null)
                output.flush();
        } catch (IOException ioex) {
            ;
        }
    }
    
    private void closeOutputStream(OutputStream output) {
        try {
            if (output != null)
                output.close();
        } catch (IOException ioex) {
            ;
        }
    }
    
    @SuppressWarnings("unchecked")
    private boolean montoAdjudicatariosValido() {
        boolean montoValido = Boolean.FALSE;
        AdquisicionBean adquisicionBean = (AdquisicionBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        Long idObjecion = null;
        Long idAdquisicion = null;
        
        try {
            idObjecion = adquisicionBean.getIdNoObjecion();
            idAdquisicion = adquisicionBean.getNumAdquisicion();
        } catch(Exception e) {
            logger.warning("Error al obtener el id de no no objecion.", e);
        }
        
        logger.warning("idObjecion: " + idObjecion);
        logger.warning("idAdquisicion: " + idAdquisicion);
        
        if (null != idAdquisicion && null != idObjecion) {
            BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindingsIdOp.getOperationBinding("validarMontoAdjudicatarios");
            operationBinding.getParamsMap().put("idAdquisicion", idAdquisicion);
            operationBinding.getParamsMap().put("idNoObjecion", idObjecion);
            operationBinding.execute();
            
            if (operationBinding.getErrors().isEmpty()) {
                montoValido = (Boolean)operationBinding.getResult();
            } else {
                JSFUtils.addFacesInformationMessage(operationBinding.getErrors().toString());
            }
        }
        
        return montoValido;
    }
    
    public void setDocumentoVista(String documentoVista) {
        this.documentoVista = documentoVista;
    }

    public String getDocumentoVista() {
        return documentoVista;
    }
    
    public Boolean getTiempo(){
      Boolean respuesta=Boolean.TRUE;
            AdquisicionBean adquisicionBean = (AdquisicionBean)JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
            adquisicionBean.setEndTime(System.currentTimeMillis());
            logger.warning("Tiempo de respuesta del: "
             + adquisicionBean.getEndTime() + " segundos");
            logger.warning("Tiempo de respuesta del metodo CargarPantalla metodo de adquisiciones "
            + (adquisicionBean.getEndTime() - adquisicionBean.getStartTime())/1000 + " segundos");
      return respuesta;
        }
}
