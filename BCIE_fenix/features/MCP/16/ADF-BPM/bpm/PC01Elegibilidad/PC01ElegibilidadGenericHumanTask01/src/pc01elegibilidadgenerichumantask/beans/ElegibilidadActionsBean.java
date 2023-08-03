
package pc01elegibilidadgenerichumantask.beans;

import com.bcie.xmlns.cuestionariobo.Archivo;
import com.bcie.xmlns.cuestionariomo.CrearReporteElegibilidadRequestType;
import com.bcie.xmlns.cuestionariomo.CrearReporteElegibilidadResponseType;
import com.bcie.xmlns.cuestionarioservice.Cuestionario12BndQSService;
import com.bcie.xmlns.cuestionarioservice.CuestionarioPT;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.math.BigDecimal;

import java.net.URLEncoder;

import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.jbo.JboException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.PollEvent;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.view.elegibilidad.Cuestionario;

public class ElegibilidadActionsBean extends FenixActionBean
{
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ElegibilidadActionsBean.class);
    public static final String BUNDLE ="pc01elegibilidadgenerichumantask.PC01ElegibilidadGenericHumanTaskBundle";
    private final String REPORTE_FILE_NAME = "ReporteElegibilidad.pdf";

    private RichPopup popupFinalizarTarea;
    private RichPopup popupFinalizarTareaDescarga;
    private RichPopup popupMasInformacion;
    private RichPopup popupRetornar;
    private RichPopup popupCancelarOperacion;
    private RichPopup popupReformularOperacion;
    
    private RichSelectOneRadio operacionElegible;

    private RichInputText conclusionElegibilidad;
    private RichInputText determinarElegibilidad;
    private RichSelectBooleanCheckbox sbcbASJUR;
    private RichSelectBooleanCheckbox sbcbGERIES;
    private RichPoll descargarReportePoll;
    


  public ElegibilidadActionsBean()
    {
        super();
    }

  public void setConclusionElegibilidad(RichInputText conclusionElegibilidad)
    {
        this.conclusionElegibilidad = conclusionElegibilidad;
    }

    public RichInputText getConclusionElegibilidad()
    {
        return conclusionElegibilidad;
    }

    public void setDeterminarElegibilidad(RichInputText determinarElegibilidad)
    {
        this.determinarElegibilidad = determinarElegibilidad;
    }

    public RichInputText getDeterminarElegibilidad()
    {
        return determinarElegibilidad;
    }

    public void setOperacionElegible(RichSelectOneRadio operacionElegible)
    {
        this.operacionElegible = operacionElegible;
    }

    public RichSelectOneRadio getOperacionElegible()
    {
        return operacionElegible;
    }

    public void setPopupFinalizarTareaDescarga(RichPopup popupFinalizarTareaDescarga)
    {
      this.popupFinalizarTareaDescarga = popupFinalizarTareaDescarga;
    }
  
    public RichPopup getPopupFinalizarTareaDescarga()
    {
      return popupFinalizarTareaDescarga;
    }

  private Boolean guardarElegibilidad(Long idOperacion, Boolean esElegible, String strElegibilidad,  String strDeterminar)
    {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("guardarElegibilidad");
        
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("esElegible", esElegible);
        operationBinding.getParamsMap().put("strElegibilidad", strElegibilidad);
        operationBinding.getParamsMap().put("strDeterminar", strDeterminar);
        
        Boolean result = (Boolean) operationBinding.execute();
        
        if (!operationBinding.getErrors().isEmpty())
        {
            LOGGER.severe("La operacion de guardar elegibilidad contiene errores. Errores: " + operationBinding.getErrors().toString());
            return false;
        }
        
        return result;
    }
    
    private Boolean validarCuestionarioElegibilidadCompletado()
    {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarCuestionarioCompleto");

        Boolean result = (Boolean) operationBinding.execute();
        
        if (!operationBinding.getErrors().isEmpty())
        {
            return false;
        }
        
        return result;
    }

    private boolean getConclusionElegibilidadValue()
    {
        return  (null == operacionElegible.getValue()? false: 
                                            ((String)operacionElegible.getValue()).equalsIgnoreCase("0") ? false : true);
    }
    
    private Integer getCodigoTarea()
    {
        ElegibilidadBean ElegibilidadBean = (ElegibilidadBean) JSFUtils.resolveExpression("#{pageFlowScope.elegibilidadBean}");

        if (null != ElegibilidadBean.getCodigoTarea() && ElegibilidadBean.getCodigoTarea().trim().length() > 0)
        {
            return Integer.parseInt(ElegibilidadBean.getCodigoTarea());
        }

        return 0;
    }
    
    private Long getIdOperacion()
    {
        ElegibilidadBean ElegibilidadBean = (ElegibilidadBean) JSFUtils.resolveExpression("#{pageFlowScope.elegibilidadBean}");
        
        if (null != ElegibilidadBean.getIdOperacion() && ElegibilidadBean.getIdOperacion().trim().length() > 0)
        {
            return Long.parseLong(ElegibilidadBean.getIdOperacion());
        }

        return 0L;
    }
    
    public Integer getCodigoProducto()
    {
        ElegibilidadBean ElegibilidadBean = (ElegibilidadBean) JSFUtils.resolveExpression("#{pageFlowScope.elegibilidadBean}");
  
        if (null != ElegibilidadBean.getCodigoProducto() && ElegibilidadBean.getCodigoProducto().trim().length() > 0)
        {
            return Integer.parseInt(ElegibilidadBean.getCodigoProducto());
        }
  
        return 0;
    }
    
    private Boolean validarRespuestasCuestionario()
    {
        Boolean esCuestionarioCompletado = Boolean.FALSE;
        Cuestionario cuestionarioBean = (Cuestionario)JSFUtils.resolveExpression("#{preguntasElegibilidadBean}");
        
        if(null != cuestionarioBean)
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext ectx = facesContext.getExternalContext();
            HttpServletRequest request = (HttpServletRequest)ectx.getRequest();

            esCuestionarioCompletado = cuestionarioBean.guardarRespuestasCuestionario(request);
        }
        else
        {
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_GUARDARCUESTIONARIO", BUNDLE));
        }
            
        return esCuestionarioCompletado;
    }
    
    private Boolean guardarRespuestasCuestionario()
    {
        Boolean esCuestionarioGuardado = Boolean.FALSE;
        Cuestionario cuestionarioBean = (Cuestionario)JSFUtils.resolveExpression("#{preguntasElegibilidadBean}");
        
        if(cuestionarioBean!=null)       
        {
            List<Map> respuestasCuestionario = cuestionarioBean.getRespuestasList();

            BindingContainer bindings =  getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("guardarPreguntasCuestionario");

            operationBinding.getParamsMap().put("plPreguntas", respuestasCuestionario);                            

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty())
            {
                esCuestionarioGuardado = Boolean.FALSE;
                LOGGER.log(ADFLogger.ERROR, "guardarRespuestasCuestionario(): Error al ejecutar metodo guardarPreguntasCuestionario(List <Map>)");                
            }
            else
            {
                esCuestionarioGuardado = Boolean.TRUE;
            }
        }
        else
        {
            LOGGER.log(ADFLogger.ERROR, "guardarRespuestasCuestionario(): No es posible obtener repuestas de cuestionario: preguntasElegibilidadBean");
        }
        
        return esCuestionarioGuardado;
    }

    public String invokeFinalizarTarea()
    {
        
        String bundleKeyError = "";
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean  messageAdded = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea)
        {
            case FenixConstants.PC01_COMPLETARCUESTIONARIO:
                LOGGER.fine("PC01_COMPLETARCUESTIONARIO");
                isValidFinalizarTarea = validarRespuestasCuestionario();
                
                bundleKeyError = "MSG_CUESTIONARIO_INCOMPLETO";
            break;
        
            case FenixConstants.PC01_VERIFICARREQLEGALES:
                LOGGER.fine("PC01_VERIFICARREQLEGALES");
                isValidFinalizarTarea = validarRespuestasCuestionario();
                
                bundleKeyError = "MSG_CUESTIONARIO_INCOMPLETO";
            break;
        
            case FenixConstants.PC01_ANALIZARPERFILRIESGO:
                LOGGER.fine("PC01_VERIFICARREQLEGALES");
                isValidFinalizarTarea = validarRespuestasCuestionario();
                
                bundleKeyError = "MSG_CUESTIONARIO_INCOMPLETO";
            break;
        
            case FenixConstants.PC01_ANALIZARELEFIBILIDAD:
                LOGGER.fine("PC01_ANALIZARELEFIBILIDAD");
            
                /*  
                 * FNXII-2232 Se remueve validacion de captura del campo Conclusión Elegibilidad
                Boolean tipoRecomedacion = getConclusionElegibilidadValue();
                String conclusionElegibilidadTxt = (String) conclusionElegibilidad.getValue();
            
                if(tipoRecomedacion!= null && (conclusionElegibilidadTxt!=null && conclusionElegibilidadTxt.trim().length()>0))
                {
                    isValidFinalizarTarea = Boolean.TRUE;               
                }
                else
                {
                    isValidFinalizarTarea = Boolean.FALSE;          
                }
                
                if (!isValidFinalizarTarea)
                {
                    messageAdded = true;
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ANALIZAR_INCOMPLETO", BUNDLE)); 
                } 
                */
                //Validar preguntas
                if(!messageAdded)
                {
                    //Validar repuestas de todos
                    isValidFinalizarTarea = validarCuestionarioElegibilidadCompletado();
                    if (!isValidFinalizarTarea)
                    {
                        messageAdded = true;
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ANALIZAR_CUESTIONARIO_INCOMPLETO", BUNDLE)); 
                    } 
                }
            
                if (messageAdded)
                {
                    isValidFinalizarTarea = Boolean.FALSE;
                    bundleKeyError = null;
                }
                  
            break;
        
            case FenixConstants.PC01_DETERMINAELEDIBILIDAD:
                LOGGER.fine("PC01_DETERMINAELEDIBILIDAD");
            
                isValidFinalizarTarea = Boolean.TRUE;
                
                String conclusionDetElegibilidadTxt = null;
                try{
                    conclusionDetElegibilidadTxt = (String) ADFUtils.getBoundAttributeValue("ComentarioDeterminarAttrVar");
                }catch(Exception e){
                    LOGGER.severe("Error al obtener atributo de la conclusion-comentario de determinar elegibilidad");
                }
                
                if(conclusionDetElegibilidadTxt != null &&
                   !conclusionDetElegibilidadTxt.isEmpty()){
                    LOGGER.warning("Se capturo Conclusion de Determininar Elegibilidad");
                }else{
                    LOGGER.warning("No se capturo Conclusion de Determinar Elegibilidad");
                    conclusionDetElegibilidadTxt = "";
                }
                
                String conAnalisisElegibilidadTxt = null;
                try{
                    conAnalisisElegibilidadTxt = (String) ADFUtils.getBoundAttributeValue("ComentarioElegibilidad");
                }catch(Exception e){
                    LOGGER.severe("Error al obtener atributo de la conclusion-comentario de determinar elegibilidad");
                }
                
                if(conAnalisisElegibilidadTxt != null &&
                   !conAnalisisElegibilidadTxt.isEmpty()){
                    LOGGER.warning("Existe valor de la conclusion del analisis de elegibilidad");
                }else{
                    LOGGER.severe("No existe valor de la conclusion del analisis de elegibilidad");
                }
                
                Boolean esElegible = null;
                Integer intEsElegible = null;
                try{
                    intEsElegible = Integer.valueOf(ADFUtils.getBoundAttributeValue("EsElegible").toString());
                }catch(Exception e){
                    LOGGER.severe("Error al obtener atributo de Es elegible");
                }
                
                if(intEsElegible != null){
                    if(intEsElegible.intValue() == 0){
                        esElegible = false;
                    }else{
                        esElegible = true;
                    }
                }else{
                    LOGGER.severe("No existe valor para atributo Es Elegible");
                }
                
                guardarElegibilidad(getIdOperacion(), esElegible, conAnalisisElegibilidadTxt, conclusionDetElegibilidadTxt);
                
                //Se comenta linea al considerar que es un error hacer el guardado de datos antes de confirmar finalizar tarea    
                //isValidFinalizarTarea = guardarElegibilidad(getIdOperacion(), null, null, determinarElegibilidad.getValue()==null?"":determinarElegibilidad.getValue().toString());
            
                /* 
                 * FNXII-2232 Se remueve validacion de captura del campo Conclusión Elegibilidad
                String determinarElegibilidadtxt = (String)determinarElegibilidad.getValue();
                
                if(determinarElegibilidadtxt!=null && determinarElegibilidadtxt.trim().length()>0)
                {
                    isValidFinalizarTarea = Boolean.TRUE;               
                }
                else
                {
                    isValidFinalizarTarea = Boolean.FALSE;          
                }
            
                bundleKeyError = "MSG_ANALIZAR_INCOMPLETO";
                */
            break;
        
            case FenixConstants.PC01_FEFORMULAR:
            break;
        
            case FenixConstants.PC01_RECOPILARINFOASJUR:
                LOGGER.fine("PC01_RECOPILARINFOASJUR");
                
                isValidFinalizarTarea = validateDocumento(getIdOperacion(), FenixConstants.TD_ESCRITURACONSTITUCION);
                
                bundleKeyError = "MSG_DCTM_ESCRITURACONSTITUCION";
             
            break;
        
            case FenixConstants.PC01_RECOPILARINFOGERIES:
                LOGGER.fine("PC01_RECOPILARINFOGERIES");
            
                isValidFinalizarTarea = validateDocumento(getIdOperacion(), FenixConstants.TD_PERFILOPERACION);
            
                if (!isValidFinalizarTarea)
                {
                    messageAdded = true;
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_DCTM_PERFILOPERACION", BUNDLE)); 
                } 
            
                
//                isValidFinalizarTarea = validateDocumento(getIdOperacion(), FenixConstants.TD_ESTADOSFINANCIEROSAUDITADOS)
//                                        || validateDocumento(getIdOperacion(), FenixConstants.TD_ESTADOSFINANCIEROSNTERNOS);
//            
//                if (!isValidFinalizarTarea)
//                {
//                    messageAdded = true;
//                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_DCTM_ESTADOSFINANCIEROS", BUNDLE)); 
//                } 
            
                if (messageAdded)
                {
                    isValidFinalizarTarea = Boolean.FALSE;
                    bundleKeyError = null;
                }
            break;

            default:
                LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        
        if (!isValidFinalizarTarea)
        {
            if(bundleKeyError != null)
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKeyError, BUNDLE));
        } else
        {
            finalizarTareaPopup();
        }
    
        return null;
    }

    public String invokeMasInformacion()
    {
        String bundleKeyError = "";
        Boolean isValidMasInformacion = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea)
        {
            case FenixConstants.PC01_ANALIZARPERFILRIESGO:
                LOGGER.fine("PC01_ANALIZARPERFILRIESGO");
                isValidMasInformacion = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                
                bundleKeyError = "MSG_NO_OBSERVACIONES2"; 
            break;
        
            case FenixConstants.PC01_VERIFICARREQLEGALES:
                LOGGER.fine("PC01_VERIFICARREQLEGALES");
                isValidMasInformacion = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                
                bundleKeyError = "MSG_NO_OBSERVACIONES2"; 
            break;

            default:
                LOGGER.log(ADFLogger.ERROR, "invokeMasInformacion(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        
        if (!isValidMasInformacion)
        {
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKeyError, BUNDLE));
        } else
        {
            masInformacionPopup();
        }
        
        return null;
    }

    public String invokeRetornar()
    {
        Boolean isValidRetornar =  validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
        
        int codigoTarea = getCodigoTarea();
    if(codigoTarea==FenixConstants.PC01_DETERMINAELEDIBILIDAD){
        if(isValidRetornar){
            retornarPopup();
        } else {
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_AGREGAR_COMENTARIO", BUNDLE));
        }
    }
    else{
            retornarPopup();
        }
      
        return null;
    }

    public String invokeReformularOperacion()
    {
        Boolean isValidReformular =  validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
        if(isValidReformular) {
          LOGGER.log("isValidReformular == true");
          reformularOperacionPopup();
        } else {
            LOGGER.log("isValidReformular == false");
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_AGREGAR_COMENTARIO", BUNDLE));
        }
        return null;
    }

    public String invokeOperacionCancelada()
    {

        cancelarOperacionPopup();
        return null;
    }
    

    public String aceptarFinalizarTarea()
    {
        popupFinalizarTarea.hide();
        
        Boolean esValidoFinalizar = Boolean.TRUE;
        
        Integer idRolAsjur = 11;
        Integer idRolGeries = 16;
        
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea)
        {
            case FenixConstants.PC01_COMPLETARCUESTIONARIO:
                LOGGER.warning("Completar Cuestionario.");
                
                esValidoFinalizar = guardarRespuestasCuestionario();
            
                refreshAdjuntosEvidencias(esValidoFinalizar);
                
                /*
                if(!(consultarPreguntasRol(idRolAsjur))) {
                    try{
                        actualizarPayloadElement("requiereAjustesAsjur",Boolean.FALSE);
                    }catch(Exception e){
                        LOGGER.severe("Error al actualizar el Payload ->",e);
                    }
                }
            
                if(!(consultarPreguntasRol(idRolGeries))) {
                    try{
                        actualizarPayloadElement("requiereAjustesGeries",Boolean.FALSE);
                    }catch(Exception e){
                        LOGGER.severe("Error al actualizar el Payload ->",e);
                    }
                }*/
                
            break;
            case FenixConstants.PC01_VERIFICARREQLEGALES:      
            case FenixConstants.PC01_ANALIZARPERFILRIESGO:
                LOGGER.warning("Verificar, Analizar Perfil.");
            
                esValidoFinalizar = guardarRespuestasCuestionario();
                
                refreshAdjuntosEvidencias(esValidoFinalizar);
            break;
        
            case FenixConstants.PC01_ANALIZARELEFIBILIDAD:
                LOGGER.warning("Analizar Elegibilidad.");
                
                String conclusionAElegibilidadTxt = null;
                try{
                    conclusionAElegibilidadTxt = (String) ADFUtils.getBoundAttributeValue("ComentarioElegibilidadAttrVar");
                }catch(Exception e){
                    LOGGER.severe("Error al obtener atributo de la conclusion-comentario de analisis de elegibilidad");
                }
                
                if(conclusionAElegibilidadTxt != null &&
                   !conclusionAElegibilidadTxt.isEmpty()){
                    LOGGER.warning("Se capturo Conclusion de Analisis de Elegibilidad");
                }else{
                    LOGGER.warning("No se capturo Conclusion de Analisis Elegibilidad");
                    conclusionAElegibilidadTxt = "";
                }
                esValidoFinalizar = guardarElegibilidad(getIdOperacion(), getConclusionElegibilidadValue(), conclusionAElegibilidadTxt, null);
            
                refreshAdjuntosEvidencias(esValidoFinalizar);
            break;
        
            case FenixConstants.PC01_DETERMINAELEDIBILIDAD:
                popupFinalizarTareaDescarga.hide();
                
                // Se mueve guardado para visualizar Conclusión en Reporte
                /*
                String conclusionDetElegibilidadTxt = null;
                try{
                    conclusionDetElegibilidadTxt = (String) ADFUtils.getBoundAttributeValue("ComentarioDeterminarAttrVar");
                }catch(Exception e){
                    LOGGER.severe("Error al obtener atributo de la conclusion-comentario de determinar elegibilidad");
                }
                
                if(conclusionDetElegibilidadTxt != null &&
                   !conclusionDetElegibilidadTxt.isEmpty()){
                    LOGGER.warning("Se capturo Conclusion de Determininar Elegibilidad");
                }else{
                    LOGGER.warning("No se capturo Conclusion de Determinar Elegibilidad");
                    conclusionDetElegibilidadTxt = "";
                }
            
                String conAnalisisElegibilidadTxt = null;
                try{
                    conAnalisisElegibilidadTxt = (String) ADFUtils.getBoundAttributeValue("ComentarioElegibilidad");
                }catch(Exception e){
                    LOGGER.severe("Error al obtener atributo de la conclusion-comentario de determinar elegibilidad");
                }
                
                if(conAnalisisElegibilidadTxt != null &&
                   !conAnalisisElegibilidadTxt.isEmpty()){
                    LOGGER.warning("Existe valor de la conclusion del analisis de elegibilidad");
                }else{
                    LOGGER.severe("No existe valor de la conclusion del analisis de elegibilidad");
                }
            
                Boolean esElegible = null;
                Integer intEsElegible = null;
                try{
                    intEsElegible = Integer.valueOf(ADFUtils.getBoundAttributeValue("EsElegible").toString());
                }catch(Exception e){
                    LOGGER.severe("Error al obtener atributo de Es elegible");
                }
            
                if(intEsElegible != null){
                    if(intEsElegible.intValue() == 0){
                        esElegible = false;
                    }else{
                        esElegible = true;
                    }
                }else{
                    LOGGER.severe("No existe valor para atributo Es Elegible");
                }
            
                esValidoFinalizar = guardarElegibilidad(getIdOperacion(), esElegible, conAnalisisElegibilidadTxt, conclusionDetElegibilidadTxt);
                */
            
                esValidoFinalizar = Boolean.TRUE;
            break;
          
          
            case FenixConstants.PC01_RECOPILARINFOASJUR:
            case FenixConstants.PC01_RECOPILARINFOGERIES:
            case FenixConstants.PC01_FEFORMULAR:
            break;
        
            default:
                LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        
        if(esValidoFinalizar)
        {
            //invocar metodo para cargar documentos onBase
            cargarDocumentosOnBase();
            
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        }
        else
        {
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_GUARDARCUESTIONARIO", BUNDLE));
            return null;
        }
    }
    
    private Map<String,Object> validarBanderasReformular(String idOperacion,String idCliente,BigDecimal montoSolicitado,
                                                                String unidadEjecutora,String idProducto){
        LOGGER.warning("Into validarBanderasReformular method");
        LOGGER.warning("Values from payLoad");
        LOGGER.warning("idOperacion :" +idOperacion);
        LOGGER.warning("idCliente :" +idCliente);
        LOGGER.warning("montoSolicitado :" +montoSolicitado);
        LOGGER.warning("unidadEjecutora :" +unidadEjecutora);
        LOGGER.warning("idProducto :" +idProducto);
        
        Map<String,Object> mapaBanderasPayLoad = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarBanderasReformular");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.getParamsMap().put("montoSolicitado", montoSolicitado);
        operationBinding.getParamsMap().put("unidadEjecutora", unidadEjecutora);
        operationBinding.getParamsMap().put("idProducto", idProducto);
        
        operationBinding.execute();
        if(operationBinding.getErrors().isEmpty())
        {
          mapaBanderasPayLoad = (Map<String,Object>) operationBinding.getResult();
        }
        else
        {
          for(Object error :operationBinding.getErrors()){
            Exception ex = (Exception)error;
            StringBuilder msg = new StringBuilder("Error en la operacion -validarBanderasReformular- ")
                                .append(ex.getClass())
                                .append(":")
                                .append(ex.getMessage());
              
            LOGGER.log(ADFLogger.ERROR, msg.toString());
          }
        }
        
        return mapaBanderasPayLoad;
    }
    
    private void actualizarBanderasReformular(Map<String,Object> mapaBanderasPayLoad)
    {
      try
      {
        // Banderas de producto
        Boolean reformuloProductoBandera = (Boolean) mapaBanderasPayLoad.get("reformuloProducto");
        Boolean requiereCuestionarioBandera = (Boolean)mapaBanderasPayLoad.get("requiereCuestionario");
        // Banderas de Cliente
        Boolean reformuloClienteBandera = (Boolean)mapaBanderasPayLoad.get("reformuloCliente");
        // Banderas de MontoSolicitado
        Boolean reformuloMontoSolicitadoMasMenosBandera = (Boolean)mapaBanderasPayLoad.get("reformuloMontoSolicitadoMasMenos");
        Boolean reformuloMontoSolicitadoMenosMasBandera = (Boolean)mapaBanderasPayLoad.get("reformuloMontoSolicitadoMenosMas");
        Boolean reformuloMontoSolicitadoBandera = (Boolean)mapaBanderasPayLoad.get("reformuloMontoSolicitado");
        // Banderas de UnidadEjecutora
        Boolean reformuloUnidadEjecutoraBandera = (Boolean)mapaBanderasPayLoad.get("reformuloUnidadEjecutora");
        
        LOGGER.warning("Values flags");
        LOGGER.warning("flag reformuloProducto :" +reformuloProductoBandera);
        LOGGER.warning("flag requiereCuestionario :" +requiereCuestionarioBandera);
        LOGGER.warning("flag reformuloCliente :" +reformuloClienteBandera);
        LOGGER.warning("flag reformuloMontoSolicitadoMasMenos :" +reformuloMontoSolicitadoMasMenosBandera);
        LOGGER.warning("flag reformuloMontoSolicitadoMenosMas :" +reformuloMontoSolicitadoMenosMasBandera);
        LOGGER.warning("flag reformuloMontoSolicitado :" +reformuloMontoSolicitadoBandera);
        LOGGER.warning("flag reformuloUnidadEjecutora :" +reformuloUnidadEjecutoraBandera);
      
        // Asignar banderas al Payload
        if(null!=mapaBanderasPayLoad.get("NombreOperacion"))
        {
          actualizarPayloadElement("NombreOperacion",(String)mapaBanderasPayLoad.get("NombreOperacion"));
        }
        
        if(null!=reformuloProductoBandera?reformuloProductoBandera:false)
        {
          actualizarPayloadElement("reformuloProducto",true);
          actualizarPayloadElement("requiereCuestionario",null!=requiereCuestionarioBandera?requiereCuestionarioBandera:false);
          actualizarPayloadElement("CodigoProducto",(Integer)mapaBanderasPayLoad.get("CodigoProducto"));
          actualizarPayloadElement("NombreProducto",mapaBanderasPayLoad.get("NombreProducto"));
        }
        
        if(null!=reformuloClienteBandera?reformuloClienteBandera:false)
        {
          actualizarPayloadElement("reformuloCliente",true);  
          actualizarPayloadElement("CodigoCliente",((Long)mapaBanderasPayLoad.get("CodigoCliente")).toString());
          actualizarPayloadElement("NombreCliente",mapaBanderasPayLoad.get("NombreCliente"));
        }
        
        if(null!=reformuloUnidadEjecutoraBandera?reformuloUnidadEjecutoraBandera:false)
        {
          actualizarPayloadElement("reformuloUnidadEjecutora",true);  
          actualizarPayloadElement("unidadEjecutora",(String)mapaBanderasPayLoad.get("UnidadEjecutora"));
        }
        
        if(null!=reformuloMontoSolicitadoBandera?reformuloMontoSolicitadoBandera:false)
        {
          actualizarPayloadElement("reformuloMontoSolicitado",true);
          actualizarPayloadElement("reformuloMontoSolicitadoMasMenos",null!=reformuloMontoSolicitadoMasMenosBandera?reformuloMontoSolicitadoMasMenosBandera:false);
          actualizarPayloadElement("reformuloMontoSolicitadoMenosMas",null!=reformuloMontoSolicitadoMenosMasBandera?reformuloMontoSolicitadoMenosMasBandera:false);
          actualizarPayloadElement("MontoSolicitado",(BigDecimal)mapaBanderasPayLoad.get("MontoSolicitado"));
        }
      }
      catch (Exception ex)
      {
        throw new JboException(new Exception("Error al asignar valores de payload Reformular.",ex));
      }
    }

    public String aceptarMasInformacion()
    {
        popupMasInformacion.hide();
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
      
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    public String aceptarRetornar()
    {
        popupRetornar.hide();
      
        Boolean esValidoRetornar = Boolean.TRUE;
        
        int codigoTarea = getCodigoTarea();
        
        switch (codigoTarea)
        {
            case FenixConstants.PC01_ANALIZARELEFIBILIDAD: 
              Boolean  retASJUR = getRetASJUR();
              Boolean retGERIES = getRetGERIES();
              if(retASJUR || retGERIES)
              {
                esValidoRetornar = Boolean.TRUE;
                if(retASJUR)
                {
                  actualizarPayloadElement("requiereAjustesAsjur",true);
                }
                if(retGERIES)
                {
                  actualizarPayloadElement("requiereAjustesGeries",true);
                }
                
              }
            break;

            case FenixConstants.PC01_COMPLETARCUESTIONARIO:
            case FenixConstants.PC01_VERIFICARREQLEGALES:      
            case FenixConstants.PC01_ANALIZARPERFILRIESGO:   
            case FenixConstants.PC01_DETERMINAELEDIBILIDAD:                  
            case FenixConstants.PC01_RECOPILARINFOASJUR:
            case FenixConstants.PC01_RECOPILARINFOGERIES:
            case FenixConstants.PC01_FEFORMULAR:
             
            break;
        
            default:
                LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        
        if(esValidoRetornar)
        {
            //invocar metodo para cargar documentos onBase
            cargarDocumentosOnBase();
            
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        }
        else
        {
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_NO_RETORNO_SELECCIONADO", BUNDLE));
            return null;
        }
    }

    public String aceptarReformularOperacion()
    {   
        popupReformularOperacion.hide();
        LOGGER.warning("into aceptarReformularOperacion");
        int codigoTarea = getCodigoTarea();
        
        if(codigoTarea == FenixConstants.PC01_FEFORMULAR  || codigoTarea == FenixConstants.PC01_DETERMINAELEDIBILIDAD){
            LOGGER.warning("Into aceptarReformularOperacion in case PC01_FEFORMULAR");
            String idOperacion = null;
            String idProducto = null;
            String idCliente = null;
            BigDecimal montoSolicitado = null;
            String unidadEjecutora = null;
            ElegibilidadBean ElegibilidadBean = (ElegibilidadBean) 
                                      JSFUtils.resolveExpression("#{pageFlowScope.elegibilidadBean}");
            if(null != ElegibilidadBean.getIdOperacion())
                idOperacion = ElegibilidadBean.getIdOperacion();
            if(null != ElegibilidadBean.getCodigoProducto())
                idProducto = ElegibilidadBean.getCodigoProducto();
            if(null != ElegibilidadBean.getCodigoCliente()) 
                idCliente = ElegibilidadBean.getCodigoCliente();
            if(null != ElegibilidadBean.getMontoSolicitado())
                montoSolicitado = ElegibilidadBean.getMontoSolicitado();
            if(null != ElegibilidadBean.getUnidadEjecutora())
                unidadEjecutora = ElegibilidadBean.getUnidadEjecutora();
            
            LOGGER.warning("Invoke validarBanderasReformular");
            Map<String,Object> mapaBanderasPayLoad = validarBanderasReformular(idOperacion,idCliente,montoSolicitado,unidadEjecutora,idProducto);
            
            if(codigoTarea == FenixConstants.PC01_FEFORMULAR)
              actualizarBanderasReformular(mapaBanderasPayLoad);
            
            if(codigoTarea == FenixConstants.PC01_DETERMINAELEDIBILIDAD)
              actualizarPayloadElement("unidadEjecutora",(String)mapaBanderasPayLoad.get("UnidadEjecutora"));
        }
      
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    public String aceptarOperacionCancelada()
    {
        popupCancelarOperacion.hide();
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    public String cancelarFinalizarTareaDescarga()
    {
        popupFinalizarTareaDescarga.hide();
        return null;
    }

    public String cancelarFinalizarTarea()
    {
        popupFinalizarTarea.hide();
        return null;
    }

    public String cancelarMasInformacion()
    {
        popupMasInformacion.hide();
        return null;
    }

    public String cancelarRetornar()
    {
        popupRetornar.hide();
        return null;
    }

    public String cancelarReformularOperacion()
    {
        popupReformularOperacion.hide();
        return null;
    }

    public String cancelarOperacionCancelada()
    {
        popupCancelarOperacion.hide();
        return null;
    }

    public void finalizarTareaPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }

    public void finalizarTareaPopupDescarga()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTareaDescarga.show(hints);
    }

    public void masInformacionPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupMasInformacion.show(hints);
    }

    public void retornarPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupRetornar.show(hints);
    }

    public void reformularOperacionPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupReformularOperacion.show(hints);
    }

    public void cancelarOperacionPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCancelarOperacion.show(hints);
    }

    public RichPopup getPopupFinalizarTarea()
    {
        return popupFinalizarTarea;
    }

    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea)
    {
        this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupMasInformacion()
    {
        return popupMasInformacion;
    }

    public void setPopupMasInformacion(RichPopup popupMasInformacion)
    {
        this.popupMasInformacion = popupMasInformacion;
    }

    public RichPopup getPopupRetornar()
    {
        return popupRetornar;
    }

    public void setPopupRetornar(RichPopup popupRetornar)
    {
        this.popupRetornar = popupRetornar;
    }

    public RichPopup getPopupCancelarOperacion()
    {
        return popupCancelarOperacion;
    }

    public void setPopupCancelarOperacion(RichPopup popupCancelarOperacion)
    {
        this.popupCancelarOperacion = popupCancelarOperacion;
    }

    public RichPopup getPopupReformularOperacion()
    {
        return popupReformularOperacion;
    }

    public void setPopupReformularOperacion(RichPopup popupReformularOperacion)
    {
        this.popupReformularOperacion = popupReformularOperacion;
    }


    /**
     * Descarga el reporte
     */
    public void downloadReporteElegibilidad(FacesContext facesContext, OutputStream outputStream)
    {
      downloadReporte(facesContext,outputStream,Boolean.FALSE);
    }
    
  /**
   * Descarga el reporte
   */
  public void downloadReporteElegibilidadOnBase(FacesContext facesContext, OutputStream outputStream)
  {
    facesContext.getExternalContext().getSessionMap().put("isProcessing", true);
    
    downloadReporte(facesContext,outputStream,Boolean.TRUE);
    
    facesContext.getExternalContext().getSessionMap().put("isProcessing", false);
    
  }
  
  public void pollListener(PollEvent pollEvent) {
      Boolean seEstaprocesando = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("isProcessing")!=null?
                                  (Boolean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("isProcessing"):true;
      LOGGER.log(ADFLogger.TRACE,"pollListener start " +  seEstaprocesando + " - " + descargarReportePoll.getInterval());
      if (!seEstaprocesando) {
          LOGGER.log(ADFLogger.TRACE,"pollListener in");
  
          descargarReportePoll.setInterval(-1);
          AdfFacesContext.getCurrentInstance().addPartialTarget(descargarReportePoll);
            
          FacesContext fctx = FacesContext.getCurrentInstance(); 
          ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
          erks.addScript(fctx, "finalizaDescargaReporteElegibilidad();"); 
          
          popupFinalizarTarea.hide();
          finalizarTareaPopupDescarga();
          
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("isProcessing");
      }
      LOGGER.log(ADFLogger.TRACE,"pollListener end");
  }
  
  private void downloadReporte(FacesContext facesContext, OutputStream outputStream, Boolean enviarOnBase)
  {
      Long idOperacion = getIdOperacion();
      byte[] reporteElegibilidad = null;
      try
      {
          applyIE11Fix(REPORTE_FILE_NAME);
          reporteElegibilidad = createPdf(idOperacion, enviarOnBase);

          if (reporteElegibilidad != null && reporteElegibilidad.length > 0)
              outputStream.write(reporteElegibilidad);

      } catch (IOException ioex)
      {
          ioex.printStackTrace();
      } finally
      {
          flushAndCloseOutputStream(outputStream);
      }
  }
    
    /**
        * Crea el archivo PDF para la operaci&oacute;n especificada
        * @param idOperacion el identificador de la operaci&oacute;n
        * @return los bytes del archivo PDF para la operaci&oacute;n especificada
        */
       private byte[] createPdf(long idOperacion, Boolean enviarOnBase) {
        
           String responsableOpe = "ELEGIBILIDAD";
           String tipoGen = "ELEGIBILIDAD";
           Cuestionario12BndQSService service = this.initCuestionarioService();
           CuestionarioPT port = service.getCuestionario12BndQSPort();
           // Add your code to call the desired methods.
           CrearReporteElegibilidadRequestType request = new CrearReporteElegibilidadRequestType();
           request.setIdOperacion(idOperacion);
           request.setGuardarReporte(enviarOnBase);
           Archivo file = new Archivo();
           //file.setArchivo(new StringBuilder().append(idOperacion).append(".pdf").toString());
           file.setArchivo(REPORTE_FILE_NAME);
           request.setNombreArchivo(file);
           
           // Se agregan parametros para Req 3 de CE
           request.setResponsableOperacion(responsableOpe);
           request.setTipoGeneracion(tipoGen);
           
           try{LOGGER.log(ADFLogger.WARNING,">HNWS"+ writeXMLRequest(request,request.getClass()).toString());}catch(Exception ex){;}
            CrearReporteElegibilidadResponseType response = port.crearReporteElegibilidad(request);
           try{LOGGER.log(ADFLogger.WARNING,">HNWS"+ writeXMLRequest(response,response.getClass()).toString());}catch(Exception ex){;}
           
           return response != null ? response.getCuestionario() : null;
       }

    private void applyIE11Fix(String docName)
    {
        RequestContext requestCtx = RequestContext.getCurrentInstance();
        Agent agent = requestCtx.getAgent();
        String version = agent.getAgentVersion();
        String browser = agent.getAgentName();

        //if ie 11
        if (browser != null && "ie".equals(browser) && version != null && version.startsWith("11"))
        {

            HttpServletResponse resp =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            String finalName = docName;

            try
            {
                finalName = URLEncoder.encode(docName, "UTF-8");
            } catch (UnsupportedEncodingException e)
            {
                ;
            }

            resp.setHeader("content-disposition", "attachment; filename=\"" + finalName + "\"");
            resp.setContentType("application/octet-stream");
        }
    }
    
    public String getReporteFileName()
    {
        return REPORTE_FILE_NAME;
    }
    
    private void flushAndCloseOutputStream(OutputStream output)
    {
        flushOutputStream(output);
        closeOutputStream(output);
    }

    private void flushOutputStream(OutputStream output)
    {
        try
        {
            if (output != null)
                output.flush();
        } catch (IOException ioex)
        {
            ;
        }
    }

    private void closeOutputStream(OutputStream output)
    {
        try
        {
            if (output != null)
                output.close();
        } catch (IOException ioex)
        {
            ;
        }
    }
    
    public StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);       
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);        
        return writer;
    }

  public void setSbcbASJUR(RichSelectBooleanCheckbox sbcbASJUR)
  {
    this.sbcbASJUR = sbcbASJUR;
  }

  public RichSelectBooleanCheckbox getSbcbASJUR()
  {
    return sbcbASJUR;
  }

  public void setSbcbGERIES(RichSelectBooleanCheckbox sbcbGERIES)
  {
    this.sbcbGERIES = sbcbGERIES;
  }

  public RichSelectBooleanCheckbox getSbcbGERIES()
  {
    return sbcbGERIES;
  }

  private Boolean getRetASJUR()
  {
    
    return sbcbASJUR.isSelected();
  }

  private Boolean getRetGERIES()
  {
    
    return sbcbGERIES.isSelected();
  }
  
  private Cuestionario12BndQSService initCuestionarioService() {
      DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
      ViewObject configuracionVoImpl = dcItteratorBindings.getViewObject();
      Row row = configuracionVoImpl.getRow(new Key(new Object[]{IWsdlLocation.CUESTIONARIO}));
      String wsdl = row == null ? null : (String) row.getAttribute("Valor");

      return IWsdlLocation.Service.getInstance(Cuestionario12BndQSService.class, wsdl);
  }

  public void setDescargarReportePoll(RichPoll descargarReportePoll)
  {
    this.descargarReportePoll = descargarReportePoll;
  }

  public RichPoll getDescargarReportePoll()
  {
    return descargarReportePoll;
  }
  
    public Boolean consultarPreguntasRol(Integer idRol) {
        LOGGER.warning("Inside consultarPreguntasRol.");
        LOGGER.warning("idRol: " + idRol);
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerPreguntasRol");

        operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
        operationBinding.getParamsMap().put("idRol", idRol);

        Boolean result = (Boolean) operationBinding.execute();
        
        LOGGER.warning("result: " + result);

        return result;
    }
    
    public void refreshAdjuntosEvidencias(Boolean validoFinalizar) {
        LOGGER.warning("Inside refreshAdjuntosEvidencias.");
        
        if (validoFinalizar) {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("refreshDocumentosAdjuntadosAction");

            operationBinding.execute();
        } else {
            LOGGER.warning("No es posible finalizar, no se actualizan evidencias.");
        }
    }
}
