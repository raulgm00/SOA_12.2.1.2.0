package org.bcie.fenix.common.view.beans;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.math.BigInteger;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import oracle.adf.controller.ControllerContext;
import oracle.adf.model.BindingContext;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;



public class FenixActionBean
{   
    /**
     * Define ubicacion y nombre del archivo properties de resource bundle
     */
    public static final String BUNDLE = "org.bcie.fenix.common.view.skinBundle";
    
    /**
     * Define clave de Bundle para mostrar mensaje generico del manejador de excepciones
     */
    public static final String EXCEPTION_HANDLER_MSG_GENERIC_KEY = "exception_handler_msg_generic";
    
    private static ADFLogger LOGGER = null;
    
    private String msgError;
    
    private String msgException;

    private String stacktrace;


    public FenixActionBean()
    {
        super();
        if (LOGGER == null) {
            LOGGER = ADFLogger.createADFLogger(this.getClass());
        }
       
    }
    
    public BindingContainer getBindings()
    {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public Boolean validateDocumento(Long idOperacion, Integer idTipoDocumento)
    {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarDocumento");

        operationBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
        operationBinding.getParamsMap().put("idOperacion", idOperacion);

        Boolean result = (Boolean) operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty())
        {   
            return false;
        }else{
            if(operationBinding.getResult() != null &&
               result == null){
                result = (Boolean) operationBinding.getResult();
            }
        }

        return result;
    }
    
  public Boolean validateDocumento(Long idOperacion, Integer idTipoDocumento, Integer idTareaBpm)
    {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarDocumento");

        operationBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idTareaBPM", idTareaBpm);

        Boolean result = (Boolean) operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty())
        {   
            return false;
        }else{
            if(operationBinding.getResult() != null &&
               result == null){
                result = (Boolean) operationBinding.getResult();
            }
        }

        return result;
    }
  
    public Boolean validarDocumentosTarea(Long idOperacion, Integer idTareaBpm, String descripcionCorta)
      {
          LOGGER.warning("Inicia metodo validarDocumentosTarea");
          LOGGER.warning("Parametros:");
          LOGGER.warning("  idOperación: " + idOperacion);
          LOGGER.warning("  idTareaBpm: " + idTareaBpm);
          LOGGER.warning("  descripcionCorta: " + descripcionCorta);
          
          BindingContainer bindings = getBindings();
          OperationBinding operationBinding = bindings.getOperationBinding("validarDocumentosTarea");

          operationBinding.getParamsMap().put("idTarea", idTareaBpm);
          operationBinding.getParamsMap().put("idOperacion", idOperacion.intValue());
          operationBinding.getParamsMap().put("descripcionCorta", descripcionCorta);
        
          Boolean result = false;
          List<String> resultado = (List<String>) operationBinding.execute();
          LOGGER.warning("Mensajes devueltos: " + resultado.size());
          if(resultado.isEmpty())
          {
              result = true;
          }
          else
          {
              result = false;
              for(String mensaje : resultado)
              {
                  LOGGER.warning("Mensaje: " + mensaje);
                  JSFUtils.addFacesErrorMessage(mensaje);   
              }
          }
                    
          if (!operationBinding.getErrors().isEmpty())
          {   
              return false;
          }else{
              if(operationBinding.getResult() != null &&
                 resultado == null){
                  resultado = (List<String>) operationBinding.execute();
                  LOGGER.warning("Mensajes devueltos: " + resultado.size());
                  
                  if(resultado.isEmpty())
                  {
                      result = true;
                  }
                  else
                  {
                      result = false;
                      for(String mensaje : resultado)
                      {
                          LOGGER.warning("Mensaje: " + mensaje);
                          JSFUtils.addFacesErrorMessage(mensaje);   
                      }
                  }
              }
          }
          
          LOGGER.warning("Finaliza metodo validarDocumentosTarea");

          return result;
      }

      /** validarDocumentoPorNumeroSerieDocumento"
    * Metodo que regresa true o false , segun el criterio de busqueda
    * @param  idOperacion,idTipoDocumento,numeroSerieDocumento
    * @since 18/08/2016
    * @by Gabriel Niño Rosales
    */
    public Boolean validarDocumentoPorNumeroSerieDocumento(Long idOperacion, Integer idTipoDocumento,
                                                                oracle.jbo.domain.Number numeroSerieDocumento)
    {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarDocumentoPorNumeroSerieDocumento");

        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
        operationBinding.getParamsMap().put("numeroSerieDocumento", numeroSerieDocumento);

        Boolean resultado = (Boolean) operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty())
        {   
            return false;
        }else{
            if(operationBinding.getResult() != null &&
               resultado == null){
                resultado = (Boolean) operationBinding.getResult();
            }
        }

        return resultado;
    }
      
    /** validarDocumentoPorOperacionIdTareaNumeroSerieDocumento"
     * Metodo que regresa true o false , segun el criterio de busqueda
     * @param  idOperacion, idTareaBpm, idTipoDocumento, numeroSerieDocumento
     * @since 28/12/2016
     */
    public Boolean validarDocumentoPorOperacionIdTareaNumeroSerieDocumento(Long idOperacion, Integer idTareaBpm,
                                                                           Integer idTipoDocumento,
                                                                           oracle.jbo.domain.Number numeroSerieDocumento) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("validarDocumentoPorOperacionIdTareaNumeroSerieDocumento");

        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idTareaBpm", idTareaBpm);
        operationBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
        operationBinding.getParamsMap().put("numeroSerieDocumento", numeroSerieDocumento);

        Boolean resultado = (Boolean) operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return false;
        } else {
            if (operationBinding.getResult() != null && resultado == null) {
                resultado = (Boolean) operationBinding.getResult();
            }
        }

        return resultado;
    }

    
    /**
     * Validar si existen documentos en base al idCliente y idTipoDocumento
     * @param Long idCliente, Integer idTipoDocumento 
     * @since 21/07/2016
     * @by Gabriel Niño Rosales
     */
    public Boolean validarDocumentoCliente(Long idCliente, Integer idTipoDocumento){
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarDocumentoCliente");
        operationBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
        operationBinding.getParamsMap().put("idCliente", idCliente);
        Boolean resultado = (Boolean) operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else if(operationBinding.getResult() != null) {
                resultado = (Boolean) operationBinding.getResult();
        }
        return resultado;
    }
    
    /**
     * Validar si existen documentos en base al idCliente y idTipoDocumento
     * @param Long idCliente, Integer idTipoDocumento, idTareaBPM, idFlujo
     * @since 21/07/2016
     * @by Gabriel Niño Rosales
     */
    public Boolean validarDocumentoClienteTareaBpmIdFlujo(Long idCliente, Integer idTipoDocumento, 
                                           Integer idTareaBPM, oracle.jbo.domain.Number idFlujo){
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarDocumentoClientePorNumeroSerieTarea");
        operationBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.getParamsMap().put("idTareaBPM", idTareaBPM);
        operationBinding.getParamsMap().put("numeroSerieDocumento", idFlujo);
        Boolean resultado = (Boolean) operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else if(operationBinding.getResult() != null) {
                resultado = (Boolean) operationBinding.getResult();
        }
        return resultado;
    }
    
    /**
     * Validar si existen documentos en base al idCliente,idTipoDocumento y numeroSerieDocumento
     * @param Long idCliente, Integer idTipoDocumento, oracle.jbo.domain.Number numeroSerieDocumento
     * @since 17/08/2016
     * @by Gabriel Niño Rosales
     */
    public Boolean validarDocumentoClientePorNumeroSerieDocumento(Long idCliente, Integer idTipoDocumento, 
                                                                    oracle.jbo.domain.Number numeroSerieDocumento){
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = 
                    bindings.getOperationBinding("validarDocumentoClientePorNumeroSerieDocumento");
        operationBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.getParamsMap().put("numeroSerieDocumento", numeroSerieDocumento);
        Boolean resultado = (Boolean) operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else if(operationBinding.getResult() != null) {
                resultado = (Boolean) operationBinding.getResult();
        }
        return resultado;
    }
    
    /**
    * Validar si existen documentos en base al idCliente, idTipoDocumento y mimeType
    * (ValidarClienteTipoDocumentoYMimeTypeVO)
    * @param Long idCliente, Integer idTipoDocumento , String mimeType
    * @since 07/11/2016
    * @by Jerry Farinha
    */
    @SuppressWarnings("unchecked")
    public Boolean validarDocumentoClientePorTipoDocYMimeType(Long idCliente, Integer idTipoDocumento, String mimeType){
    BindingContainer bindings = getBindings();
    OperationBinding operationBinding = bindings.getOperationBinding("validarDocumentoClientePorTipoDocumentoYMimeType");
    operationBinding.getParamsMap().put("idCliente", idCliente);
    operationBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
    operationBinding.getParamsMap().put("mimeType", mimeType);
    Boolean resultado = (Boolean) operationBinding.execute();
    if(!operationBinding.getErrors().isEmpty()){
      // Manejo de errores
      JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
    }else if(operationBinding.getResult() != null) {
          resultado = (Boolean) operationBinding.getResult();
    }
    return resultado;
    }
    
    /**
    * Validar si existen documentos en base al idCliente, idTipoDocumento, numero de serie o flujo  y mimeType
    * (ValidarClienteTipoDocumentoYMimeTypeVO)
    * @param Long idCliente, Integer idTipoDocumento , String mimeType
    * @since 07/11/2016
    * @by Jerry Farinha
    */
    @SuppressWarnings("unchecked")
    public Boolean validarDocumentoClienteSerieYMimeType(Long idCliente, Integer idTipoDocumento, String mimeType, Long serie){
    BindingContainer bindings = getBindings();
    OperationBinding operationBinding = bindings.getOperationBinding("validarDocumentoClienteMimeTypeSerie");
    operationBinding.getParamsMap().put("idCliente", idCliente);
    operationBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
    operationBinding.getParamsMap().put("mimeType", mimeType);
    operationBinding.getParamsMap().put("serie", serie);
    Boolean resultado = (Boolean) operationBinding.execute();
    if(!operationBinding.getErrors().isEmpty()){
      // Manejo de errores
      JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        resultado=Boolean.FALSE;
    }else if(operationBinding.getResult() != null) {
          resultado = (Boolean) operationBinding.getResult();
    }
    return resultado;
    }

    public Boolean validateComentario(Long idOperacion, String idTcaTareaBpm, String IdInstanciaTarea){
        LOGGER.warning("Inicia metodo validateComentario");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        Boolean result = Boolean.FALSE;
        try{   
            operationBinding = bindings.getOperationBinding("validarComentario");
        
            operationBinding.getParamsMap().put("IdInstanciaTarea", IdInstanciaTarea);
            operationBinding.getParamsMap().put("idTcaTareaBpm", idTcaTareaBpm);
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
        
             result = (Boolean) operationBinding.execute();
                
        }catch(Exception e){
            LOGGER.warning("Error al validar comentario->",e);            
        }
            
        Object object = operationBinding.getResult();
            
        if (!operationBinding.getErrors().isEmpty()){
            return null;
        }
        
        LOGGER.warning("Termina metodo validateComentario result: "+result);
        return (result);
    }
    
    /**
     * Validar si se agrego un comentario
     * @param Long idCliente, String idTcaTareaBpm, String IdInstanciaTarea
     * @since 22/07/2016
     * @by Gabriel Niño Rosales
     */
    public Boolean validarComentarioCliente(Long idCliente, String idTcaTareaBpm, String IdInstanciaTarea){
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarComentarioCliente");

        operationBinding.getParamsMap().put("IdInstanciaTarea", IdInstanciaTarea);
        operationBinding.getParamsMap().put("idTcaTareaBpm", idTcaTareaBpm);
        operationBinding.getParamsMap().put("idCliente", idCliente);

        Boolean result = (Boolean) operationBinding.execute();

        Object object = operationBinding.getResult();
        
        if (!operationBinding.getErrors().isEmpty()){
            return null;
        }
        return (result);
    }
    
    public void cargarDocumentosOnBase(){
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("cargarDocumentos");
        operationBinding.execute();
        Object result = operationBinding.getResult();
        if (!operationBinding.getErrors().isEmpty())
        {
            //Control de errores
        }
    }
    
    /**
     * cargarDocumentosClienteOnBase
     * Metodo generico para cargar los documentos adjuntos a onBase, en base a DocumentosAdjuntosClientesVO
     * que se utilizar para el "GestorDocumentosCliente"
     * @since 10/08/2016
     * @by Gabriel Niño Rosales
     */ 
    public void cargarDocumentosClienteOnBase(){
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("cargarDocumentosCliente");
        operationBinding.execute();
        Object result = operationBinding.getResult();
        if (!operationBinding.getErrors().isEmpty())
        {
            //Control de errores
        }
    }
    
    
    public void actualizarPayloadElement(String psElementName, Object poValue) {
      AttributeBinding attributeBinding = null;
    
      attributeBinding = ADFUtils.findControlBinding(psElementName);
      
      if(attributeBinding!=null)
          attributeBinding.setInputValue(poValue);
      
    }
    
    public String getInstanciaTarea()
    {
        String returnValue="";
        try
        {
          BigInteger taskNumber = (BigInteger) JSFUtils.resolveExpression("#{bindings.taskNumber.inputValue}");
    
          if (taskNumber != null)
              returnValue = taskNumber.toString();
    
         
        }catch(Exception ex)
        {
          ex.printStackTrace();  
        }
        
        return returnValue;
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
    
    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getMsgError() {
        
        msgError = getStringFromBundle(EXCEPTION_HANDLER_MSG_GENERIC_KEY);
        JSFUtils.addFacesErrorMessage(msgError);
        return msgError;
    }
    
    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getStacktrace() {
        ControllerContext cc = null;
        cc = ControllerContext.getInstance(); 
        if(cc != null){
            if(cc.getCurrentViewPort() != null){
                if ( cc.getCurrentViewPort().getExceptionData()!=null ) {  
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    cc.getCurrentViewPort().getExceptionData().printStackTrace(pw);
                    stacktrace = sw.toString();  
                } 
            }
        }
        return stacktrace;
    }
    
    public void setMsgException(String msgException) {
        this.msgException = msgException;
    }

    public String getMsgException() {
        
        ControllerContext cc = null;
        cc = ControllerContext.getInstance(); 
        if(cc != null){
            if(cc.getCurrentViewPort() != null){
                if ( cc.getCurrentViewPort().getExceptionData()!=null ) {  
                    msgException = cc.getCurrentViewPort().getExceptionData().getMessage();
                }  
            }
        }
        return msgException;
    }
    
    public <T> T execute(Map<String, Object> params, String method) throws OperationExecuteException {
        T result = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding(method);
        
        if(params != null && !params.isEmpty()) {
            for(Map.Entry<String, Object> param : params.entrySet()) {
                operationBinding.getParamsMap().put(param.getKey(), param.getValue());
            }
        }
        operationBinding.execute();
        
        if(!operationBinding.getErrors().isEmpty()){
            throw new OperationExecuteException(operationBinding.getErrors().toString());
        } else {
            result = (T) operationBinding.getResult();
        }
        return result;
    }
    
    public static class OperationExecuteException extends Exception {
        public OperationExecuteException(String message) {
            super(message);
        }
    }
    
    /**
     * Actualiza "IdEstadoAccion" de la tabla "accion"
     * @param Long idCliente,Integer idEstadoAccion,Integer idEstadoAccionNuevo,Integer IdTcaCategoriaAccion
     * @since 09/08/2016
     * @by Gabriel Niño Rosales
     */
    public void actualizarEstadoAccion(Long idCliente,Integer idEstadoAccion,Integer idEstadoAccionNuevo,
                                       Integer IdTcaCategoriaAccion){
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarEstadoAccion");
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.getParamsMap().put("idEstadoAccion", idEstadoAccion);
        operationBinding.getParamsMap().put("idEstadoAccionNuevo", idEstadoAccionNuevo);
        operationBinding.getParamsMap().put("IdTcaCategoriaAccion", IdTcaCategoriaAccion);
        operationBinding.execute();        
        if (!operationBinding.getErrors().isEmpty()){
            //Control de errores
        }
    }
    
    /**
     * Metodo generico que valida que exista un row en la tabla "accion" en base al estadoAccion y CategoriaAccion
     * @param Integer idEstadoAccion,Integer IdTcaCategoriaAccion,Long idCliente)
     * @return  TRUE or FALSE, si existe row return "true", caso contrario return "false"
     * @since 09/08/2016
     * @by Gabriel Niño Rosales
     */
    public Boolean validarAccionIngresadaPorCliente(Integer idEstadoAccion,Integer IdTcaCategoriaAccion,Long idCliente){
        Boolean resultado = Boolean.FALSE; 
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("validarAccionIngresadaPorCliente");
        operationBinding.getParamsMap().put("idEstadoAccion", idEstadoAccion);
        operationBinding.getParamsMap().put("idTcaCategoriaAccion", IdTcaCategoriaAccion);
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.execute();  
        if(!operationBinding.getErrors().isEmpty()){
            //Control de errores
            
        }else{
            resultado = (Boolean) operationBinding.getResult();
        }
        return resultado;
    }
}
