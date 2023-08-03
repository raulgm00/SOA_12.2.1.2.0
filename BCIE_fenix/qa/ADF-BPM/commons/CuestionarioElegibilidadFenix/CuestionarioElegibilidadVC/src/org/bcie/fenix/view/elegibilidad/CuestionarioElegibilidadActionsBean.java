package org.bcie.fenix.view.elegibilidad;

import com.bcie.xmlns.cuestionariomo.LeerCuestionarioRequestType;
import com.bcie.xmlns.cuestionariomo.LeerCuestionarioResponseType;
import com.bcie.xmlns.cuestionariomo.ObtenerCuestionarioRequestType;
import com.bcie.xmlns.cuestionariomo.ObtenerCuestionarioResponseType;
import com.bcie.xmlns.cuestionarioservice.Cuestionario12BndQSService;
import com.bcie.xmlns.cuestionarioservice.CuestionarioPT;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.sql.SQLException;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import oracle.jbo.domain.BlobDomain;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.model.UploadedFile;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;

import org.apache.myfaces.trinidad.util.Service;

import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.vo.ConfiguracionVOImpl;
import org.bcie.fenix.common.model.vo.common.ConfiguracionVO;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;


public class CuestionarioElegibilidadActionsBean
{
    private static ADFLogger logger = null;
    private static final String BUNDLE_NAME = "org.bcie.fenix.view.elegibilidad.CuestionarioElegibilidadVCBundle";
    private final String QUESTIONARY_FILE_NAME = "CuestionarioElegibilidad.xls";
    private final String MSG_ERROR_GUARDAR = "MSG_ERROR_SAVING";
    private final String MSG_ERROR_GUARDAR2 = "MSG_ERROR_SAVING2";
    private UploadedFile uploadedFile;
    private RichOutputText htmlQuestionary;
    private RichPopup popupEvidencia;
    private static final Integer TIPO_DOCUMENTO_EVIDENCIA = 1183;


    public CuestionarioElegibilidadActionsBean()
    {
        super();
        if (logger == null)
        {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    private BindingContainer getBindings()
    {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String getQuestionaryFileName()
    {
        return QUESTIONARY_FILE_NAME;
    }

    /**
     * Asigna los datos del archivo cargado
     * @param uploadedFile los datos del archivo cargado
     */
    public void setUploadedFile(UploadedFile uploadedFile)
    {
        this.uploadedFile = uploadedFile;     
    }

    /**
     * Env&iacute;a el cuestionario leyendo el archivo especificado
     * @param fileName el nombre del archivo que contiene el cuestionario
     */
    private void sendQuestionary(byte[] archivo)
    {
        
        Cuestionario12BndQSService service = this.initCuestionarioService();
        CuestionarioPT port = service.getCuestionario12BndQSPort();
        LeerCuestionarioRequestType request = new LeerCuestionarioRequestType();
        request.setIdOperacion(getIdOperacion());
        request.setIdResponsable(getIdResponsable());
        request.setArchivo(archivo);
        try
        {
            logger.log(ADFLogger.WARNING,
                       ">HNWS sendQuestionary Req " + writeXMLRequest(request, request.getClass()).toString());
        } catch (Exception ex)
        {
            ;
        }
        LeerCuestionarioResponseType response = port.leerCuestionario(request);
        try
        {
            logger.log(ADFLogger.WARNING,
                       ">HNWS sendQuestionary Res " + writeXMLRequest(response, response.getClass()).toString());
        } catch (Exception ex)
        {
            ;
        }

        CuestionarioElegibilidadBean cuestionarioBean =
            (CuestionarioElegibilidadBean) JSFUtils.resolveExpression("#{pageFlowScope.cuestionarioElegibilidadBean}");

        try
        {
            List<Map> cuestionario = this.obtenerPreguntasCuestionario();
            
            if(null!=cuestionario)
            {
              cuestionarioBean.setPreguntasList(cuestionario);
              cuestionarioBean.generarCuestionario();
            }
          
        } 
        catch (Exception e)
        {
            ;
        }

    }

    public UploadedFile getUploadedFile()
    {
        return uploadedFile;
    }

    /**
     * Construye el completo nombre del archivo
     * @param fileName el nombre del archivo
     * @return el nombre completo del archivo
     */
    /*private String buildFileName(String fileName)
    {
        StringBuilder str = new StringBuilder();
        str.append(System.getProperty("java.io.tmpdir"));
        str.append(fileName);
        return str.toString();
    }*/

    public Boolean getEsSoloLectura()
    {
        try
        {
            return (Boolean) JSFUtils.resolveExpression("#{pageFlowScope.pEsSoloLectura}");
        } catch (Exception ex)
        {
            return Boolean.TRUE;
        }
    }
    
    public Boolean getEsAnalizarElegibilidad()
    {
        try
        {
            return (Boolean) JSFUtils.resolveExpression("#{pageFlowScope.pEsAnalizarElegibilidad}");
        } catch (Exception ex)
        {
            return Boolean.FALSE;
        }
    }

    public Long getIdOperacion()
    {
        try
        {
            return JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") == null ? 0L :
                   Long.parseLong((JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString()));
        } catch (Exception ex)
        {
            ex.printStackTrace();
            return 0L;
        }
    }
    
    
    public String getIdResponsable()
    {
        try
        {
            return JSFUtils.resolveExpression("#{pageFlowScope.pIdResponsable}") == null ? "0" :
                   (JSFUtils.resolveExpression("#{pageFlowScope.pIdResponsable}").toString());
        
        } catch (Exception ex)
        {
            ex.printStackTrace();
            return "0";
        }
    }

    public String guardarRespuestasCuestionario()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ectx = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ectx.getRequest();

        CuestionarioElegibilidadBean cuestionarioBean =
            (CuestionarioElegibilidadBean) JSFUtils.resolveExpression("#{pageFlowScope.cuestionarioElegibilidadBean}");

        if (cuestionarioBean != null)
        {
            cuestionarioBean.guardarRespuestasCuestionario(request);

            List<Map> respuestasCuestionario = cuestionarioBean.getCuestionario().getRespuestasList();

            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("guardarPreguntasCuestionario");

            operationBinding.getParamsMap().put("plPreguntas", respuestasCuestionario);


            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty())
            {
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(MSG_ERROR_GUARDAR2, BUNDLE_NAME));
            }

        } else
        {
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(MSG_ERROR_GUARDAR, BUNDLE_NAME));
        }

        return null;
    }


    /**
     * Obtiene el cuestionario para la operaci&oacute;n especificada
     * @param idOperacion el identificador de la operaci&oacute;n
     * @return los bytes del cuestionario para la operaci&oacute;n especificada
     */
    private byte[] getQuestionary(Long idOperacion)
    {
        
        logger.warning("GETQUESTIONARY:========> " + idOperacion);
        Cuestionario12BndQSService cuestionario12BndQSService = this.initCuestionarioService();
        CuestionarioPT cuestionarioPT = cuestionario12BndQSService.getCuestionario12BndQSPort();
        logger.warning("GETQUESTIONARY:========> " + idOperacion);
        ObtenerCuestionarioRequestType request = new ObtenerCuestionarioRequestType();
        request.setIdOperacion(idOperacion);
        request.setIdResponsable(getIdResponsable());
        try
        {
            logger.log(ADFLogger.WARNING,
                       ">HNWS getQuestionary Req " + writeXMLRequest(request, request.getClass()).toString());
        } catch (Exception ex)
        {
            ;
        }
        ObtenerCuestionarioResponseType response = cuestionarioPT.obtenerCuestionario(request);
        try
        {
            logger.log(ADFLogger.WARNING,
                       ">HNWS getQuestionary Req " + writeXMLRequest(response, response.getClass()).toString());
        } catch (Exception ex)
        {
            ;
        }
        
        return response != null ? response.getCuestionario() : null;
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

    /**
     * Descarga el cuestionario
     */
    public void downloadCuestionarioElegibilidad(FacesContext facesContext, OutputStream outputStream)
    {
        Long idOperacion = getIdOperacion();
        byte[] questionary = null;
        try
        {
            applyIE11Fix(QUESTIONARY_FILE_NAME);
            questionary = getQuestionary(idOperacion);
            //for(int i=0;i<questionary.length;i++)
            logger.warning("GETQUESTIONARY:========> DescargarCuestionarioElegibilidad");
            logger.warning(questionary.getClass().getFields().toString());
                
            if (questionary != null && questionary.length > 0)
                outputStream.write(questionary);

        } catch (IOException ioex)
        {
            ioex.printStackTrace();
        } finally
        {
            flushAndCloseOutputStream(outputStream);
        }
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

    public void uploadCuestionarioVCL(ValueChangeEvent valueChangeEvent)
    {
        logger.warning("Updating file");
        uploadedFile = (UploadedFile) valueChangeEvent.getNewValue();
        if (uploadedFile == null)
            return;
        logger.warning(new StringBuilder().append("Content-Type: ").append(uploadedFile.getContentType()).toString());
        logger.warning(new StringBuilder().append("File Name:").append(uploadedFile.getFilename()).toString());
        InputStream fileinputstream = null;
        String fileName = null;
        try
        {
            fileinputstream = uploadedFile.getInputStream();
            fileName = uploadedFile.getFilename();//buildFileName(uploadedFile.getFilename());
            logger.warning(fileName);
            logger.warning("Sending questionary");
          
            byte[] filebytes = getBytes(fileinputstream);
            sendQuestionary(filebytes);
          
            logger.warning("Questionary sent");
        } catch (Exception ex)
        {
          JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(MSG_ERROR_GUARDAR2, BUNDLE_NAME));
            
        } finally
        {
            try
            {
                if (fileinputstream != null)
                    fileinputstream.close();
            } catch (IOException ioex)
            {
                ;
            }
        }

    }

    private byte[] getBytes(InputStream is) throws IOException
    {

        int len;
        int size = 1024;
        byte[] buf;

        if (is instanceof ByteArrayInputStream)
        {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1)
                bos.write(buf, 0, len);
            buf = bos.toByteArray();
        }
        return buf;
    }

    public StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException
    {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);
        return writer;
    }

  public void setHtmlQuestionary(RichOutputText htmlQuestionary)
  {
    this.htmlQuestionary = htmlQuestionary;
  }

  public RichOutputText getHtmlQuestionary()
  {
    return htmlQuestionary;
  }

  public List<Map> obtenerPreguntasCuestionario()
  {
    BindingContainer bindings = getBindings();
    OperationBinding operationBinding = bindings.getOperationBinding("getPreguntasCuestionario");
    List<Map> preguntasCuestionario = (List<Map>) operationBinding.execute();
    if (!operationBinding.getErrors().isEmpty())
    {
      return null;
    }
    return preguntasCuestionario;
  }
  
  private Cuestionario12BndQSService initCuestionarioService() {
      DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
      ViewObject configuracionVoImpl = dcItteratorBindings.getViewObject();
      
      Row row = configuracionVoImpl.getRow(new Key(new Object[]{IWsdlLocation.CUESTIONARIO}));
      logger.warning("GETQUESTIONARY:========> initCuestionary");
      for(String colName:row.getAttributeNames())
          logger.warning("ENTRA ATRIBUTO = "+colName);
      String wsdl = row == null ? null : (String) row.getAttribute("Valor");
      
      return IWsdlLocation.Service.getInstance(Cuestionario12BndQSService.class, wsdl);
  }
  
    public void abrirPopUpEvidencia(ActionEvent actionEvent) {
        logger.warning("Inicia  abrirPopUpEvidencia");
        //declaracion de variables
        RichPopup.PopupHints popupHints =  null;
        //mandar llamar metodo para limpiar valores e inicialisar campos
        inicialisarFormularioEvidencia();
        //abrir popup
        popupHints = new RichPopup.PopupHints();
        this.getPopupEvidencia().show(popupHints);
        logger.warning("Termina  abrirPopUpEvidencia");
    }


    public void guardarEvidencia(ActionEvent actionEvent) {
        // Add event code here...
        logger.warning("Dentro de guardarEvidencia");
        
        //declaracion de variables
        Long idPregunta = null;
        Long idDocumento = null;
        Long idOperacion = null;
        Long idTareaBpm = null;
        Long idAccion = null;
        Long numeroSerieDocumento = null;
        String instanciaProceso = null;
        Boolean resultado = null;
        
        
        //obtener bean de pageflowscope
        CuestionarioElegibilidadBean cuestionarioElegibilidadBean = (CuestionarioElegibilidadBean) 
            JSFUtils.resolveExpression("#{pageFlowScope.cuestionarioElegibilidadBean}");
        
        //recuperar valores necesarios para adjuntar evidencia a una pregunta
        idPregunta = cuestionarioElegibilidadBean.getIdPregunta();
        idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
        idTareaBpm =  Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString());
        idAccion = 1L;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") != null){
            instanciaProceso = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}").toString();
            logger.warning("pInstanciaProceso :"+instanciaProceso);
        }else{
            logger.warning("pInstanciaProceso es nulo");
        }
        
        
        logger.warning("idPregunta :"+idPregunta);
        logger.warning("idOperacion :"+idOperacion);
        logger.warning("idTareaBpm :"+idTareaBpm);
        logger.warning("idAccion :"+idAccion);
        logger.warning("numeroSerieDocumento :"+numeroSerieDocumento);
        logger.warning("instanciaProceso :"+instanciaProceso);
        
        idDocumento = adjuntarEvidencia(cuestionarioElegibilidadBean, idOperacion, idTareaBpm, idAccion, 
                                            numeroSerieDocumento, instanciaProceso);
        
        logger.warning("idDocumento :"+idDocumento);
        
        if(null != idDocumento){
            
            resultado = agregarEvidenciaPorId(idPregunta, idDocumento);
            
            if(resultado == Boolean.TRUE ){
                //execute js
                String idElemento = "nameTA-"+idPregunta;
                String script =" insertarNombreEvidenciaHtml('"+idElemento+"','"+
                               cuestionarioElegibilidadBean.getNombreEvidencia()+"');";
                addScriptOnPartialRequest(script);
            }else{
                logger.severe("agregarEvidencia return FALSE");
            }
            this.getPopupEvidencia().hide();
                                                                 
        }else{
            logger.severe("No se asocio el documento con la respuesta ");
        }
        
        logger.warning("Fuera de guardarEvidencia");
    }

    public void cancelarEvidencia(ActionEvent actionEvent) {
        // Add event code here...
        logger.warning("Dentro de cancelarEvidencia");
        this.getPopupEvidencia().hide();
        logger.warning("Fuera de cancelarEvidencia");
    }
    
    private Long adjuntarEvidencia(CuestionarioElegibilidadBean cuestionarioElegibilidadBean, Long idOperacion, 
                                        Long idTareaBpm, Long idAccion,Long numeroSerieDocumento,
                                            String instanciaProceso){
        
        logger.warning("Dentro de adjuntarEvidencia");
        
        logger.warning("nombreEvidencia :"+cuestionarioElegibilidadBean.getNombreEvidencia());
        logger.warning("evidenciaAdjunto :"+cuestionarioElegibilidadBean.getEvidenciaAdjunto());
        logger.warning("evidenciaInputStream :"+cuestionarioElegibilidadBean.getEvidenciaInputStream());
        logger.warning("codigoArchivo :"+cuestionarioElegibilidadBean.getCodigoArchivo());
        logger.warning("resumenArchivo :"+cuestionarioElegibilidadBean.getResumenArchivo());
        logger.warning("FechaArchivo :"+cuestionarioElegibilidadBean.getFechaArchivo());
        
        BindingContainer bindings = ADFUtils.getBindingContainer();                   
        OperationBinding adjuntarDocumento = bindings.getOperationBinding("adjuntarDocumento");
        Long idDocumento = null;
        
        if(cuestionarioElegibilidadBean != null){            
            adjuntarDocumento.getParamsMap().put("idOperacion", idOperacion);
            adjuntarDocumento.getParamsMap().put("idTareaBpm", idTareaBpm);
            adjuntarDocumento.getParamsMap().put("esJustificacion", "");
            
            if((cuestionarioElegibilidadBean.getEvidenciaAdjunto() != null) 
                && (cuestionarioElegibilidadBean.getEvidenciaInputStream() != null)){ // Check that file is not empty
               
                adjuntarDocumento.getParamsMap().put
                    ("content", createBlobDomain(cuestionarioElegibilidadBean.getEvidenciaInputStream()));
                adjuntarDocumento.getParamsMap().put
                    ("mimeType", cuestionarioElegibilidadBean.getEvidenciaAdjunto().getContentType().toString());
            }
            
            adjuntarDocumento.getParamsMap().put("fileName", cuestionarioElegibilidadBean.getNombreEvidencia());
            adjuntarDocumento.getParamsMap().put("codigo", cuestionarioElegibilidadBean.getCodigoArchivo());
            adjuntarDocumento.getParamsMap().put("fecha", cuestionarioElegibilidadBean.getFechaArchivo());
            
            
            adjuntarDocumento.getParamsMap().put("idTipo", TIPO_DOCUMENTO_EVIDENCIA);
            adjuntarDocumento.getParamsMap().put("resumen", cuestionarioElegibilidadBean.getResumenArchivo());
            adjuntarDocumento.getParamsMap().put("idAccion", idAccion); // 1=Agregado, 2=Modificado 3=Eliminado. Según catálogo TCA_ACCION_DOCUMENTO
            adjuntarDocumento.getParamsMap().put("loginCreadoPor", ADFContext.getCurrent().getSecurityContext().getUserName());  // Creado por
            adjuntarDocumento.getParamsMap().put("nombreCreadoPor", ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName()); // Nombre creado por
            adjuntarDocumento.getParamsMap().put("idOnbase", null);
            adjuntarDocumento.getParamsMap().put("numeroSerieDocumento", numeroSerieDocumento);
            adjuntarDocumento.getParamsMap().put("instanciaProceso", instanciaProceso);
            
            logger.log(ADFLogger.WARNING, "idOperacion : " + idOperacion);
            logger.log(ADFLogger.WARNING, "idTareaBpm : " + idTareaBpm);
            logger.log(ADFLogger.WARNING, "numeroSerieDocumento : " + numeroSerieDocumento);
            logger.log(ADFLogger.WARNING, "instanciaProceso : " + instanciaProceso);
            
            adjuntarDocumento.execute();
            if(!adjuntarDocumento.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(adjuntarDocumento.getErrors().toString());
            }
            else if(adjuntarDocumento.getResult() != null) {
                idDocumento = (Long)adjuntarDocumento.getResult();
            }
        }
        
        logger.warning("idDocumento :"+idDocumento);
        logger.warning("Fuera de adjuntarEvidencia");
        return idDocumento;
    }
    
    private Boolean agregarEvidenciaPorId(Long idPregunta,Long idDocumento){
        logger.warning("Dentro de agregarEvidenciaPorId");
        logger.warning("idPregunta :"+idPregunta);
        logger.warning("idDocumento :"+idDocumento);
        
        Boolean resultado = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();                   
        OperationBinding operationBinding = bindings.getOperationBinding("agregarEvidenciaPorId");
        operationBinding.getParamsMap().put("id", idPregunta);
        operationBinding.getParamsMap().put("idDocumento", idDocumento);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else if(operationBinding.getResult() != null) {
            resultado = (Boolean)operationBinding.getResult();
        }
        
        logger.warning("resultado :"+resultado);
        logger.warning("Fuera de agregarEvidenciaPorId");
        
        return resultado;
    }
    
    
    private BlobDomain createBlobDomain(InputStream in) {
        logger.warning("Dentro de createBlobDomain");
        
        BlobDomain blobDomain = null;
        OutputStream out = null;

        try {
            blobDomain = new BlobDomain();
            out = blobDomain.getBinaryOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            in.close();

        } catch (IOException e) {
            logger.severe(e.getMessage()); 
        } catch (SQLException e) {
            logger.severe(e.getMessage()); 
        }
        
        logger.warning("Fuera de createBlobDomain");
        return blobDomain;
    }
    
    private void inicialisarFormularioEvidencia(){
        logger.warning("Dentro de inicialisarFormularioEvidencia");
        //declaracion de variables
        CuestionarioElegibilidadBean cuestionarioElegibilidadBean = null;
        //obtener bean de pageflowscope    
        cuestionarioElegibilidadBean = (CuestionarioElegibilidadBean) 
            JSFUtils.resolveExpression("#{pageFlowScope.cuestionarioElegibilidadBean}");
        //set value en null
        cuestionarioElegibilidadBean.setEvidenciaAdjunto(null);
        cuestionarioElegibilidadBean.setNombreEvidencia(null);
        cuestionarioElegibilidadBean.setFechaArchivo(null);
        cuestionarioElegibilidadBean.setCodigoArchivo(null);
        cuestionarioElegibilidadBean.setResumenArchivo(null);
        //tomar fecha actual
        oracle.jbo.domain.Date fechaActual = new 
            oracle.jbo.domain.Date(new java.sql.Timestamp(System.currentTimeMillis()));
        //set valor de fecha a la vista
        cuestionarioElegibilidadBean.setFechaArchivo(fechaActual.getValue());
        
        logger.warning("Fuera de inicialisarFormularioEvidencia");
    }
    
    /**
    * Add a script to the render kit
    */
    public static void addScriptOnPartialRequest(String script) {
        logger.warning("Dentro de addScriptOnPartialRequest");
        logger.warning("script :"+script);
        FacesContext context = FacesContext.getCurrentInstance();
        if (AdfFacesContext.getCurrentInstance().isPartialRequest(context)) {
            ExtendedRenderKitService erks =
            Service.getRenderKitService(context, ExtendedRenderKitService.class);
            erks.addScript(context, script);
        }
        logger.warning("Fuera de addScriptOnPartialRequest");
    }
    
    public void setPopupEvidencia(RichPopup popupEvidencia) {
        this.popupEvidencia = popupEvidencia;
    }

    public RichPopup getPopupEvidencia() {
        return popupEvidencia;
    }

}
