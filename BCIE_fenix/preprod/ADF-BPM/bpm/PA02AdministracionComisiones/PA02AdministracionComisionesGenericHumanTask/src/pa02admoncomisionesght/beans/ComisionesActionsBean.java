package pa02admoncomisionesght.beans;

import com.bcie.xmlns.comisionservice.Comision;
import com.bcie.xmlns.comisionservice.ComisionPT;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;

import org.bcie.comisionmo.CrearAvisoCobroComisionRequestType;
import org.bcie.comisionmo.CrearAvisoCobroComisionResponseType;
import org.bcie.comisionmo.CrearAvisoCobroComisionV2RequestType;
import org.bcie.comisionmo.CrearAvisoCobroComisionV2ResponseType;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class ComisionesActionsBean extends FenixActionBean
{
  public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ComisionesActionsBean.class);
  public static final String BUNDLE ="pa02admoncomisionesght.PA02AdmonComisionesGHTBundle";
    private final String DOCUMENTO_FILE_NAME = "PlantillaAvisoCobro.docx";
  
  private RichPopup popupFinalizarTarea;
  private RichPopup popupRetornar;
  
    /**
     * Dewarning clave de Bundle para indicar error en la validacion al finalizar tarea
     */
    public static final String MSG_ERROR_AGREGAR_COMENTARIO ="MSG_ERROR_AGREGAR_COMENTARIO";

    /**
     * Dewarning clave de Bundle para indicar error en la validacion al finalizar tarea
     */
    public static final String MSG_ERROR_ACTUALIZAR_ESTADO ="MSG_ERROR_ACTUALIZAR";

    /**
     * Dewarning clave de Bundle para indicar error en la validacion al finalizar tarea
     */
    public static final String MSG_ERROR_DISPENSADO ="MSG_ERROR_DISPENSADO";

    /**
     * Dewarning clave de Bundle para indicar error en la validacion al finalizar tarea
     */
    public static final String MSG_ERROR_PAGADO ="MSG_ERROR_PAGADO";
    /**
     * Dewarning clave de Bundle para indicar error en la validacion al finalizar tarea
     */
     public static final String MSG_BTN_ERROR_SALIDA ="MSG_BTN_ERROR_SALIDA";
    
    /**
     * Dewarning clave de Bundle para indicar error en la validacion al finalizar tarea
     */
     public static final String MSG_BTN_ERROR_SALIDA2 ="MSG_BTN_ERROR_SALIDA2";
    
    public static final Integer ID_SUBESTADO_POR_VALIDAR = 21;
    public static final Integer ID_SUBESTADO_ELIMINADA = 10;
    public static final Integer ID_ESTADO_REGISTRADA = 26;
    public static final Integer ID_SUBESTADO_EN_REGISTRO = 17;
    
    private RichPopup popupMasInformacion;
    private RichPopup popUpErrorComisionesValidadas;
    
    /* MSG_COMISION_NO_PAGADA */
    public static final String MSG_COMISION_NO_PAGADA ="MSG_COMISION_NO_PAGADA";
    

    public ComisionesActionsBean()
  {
    super();
  }
  
    public boolean avisoDeCobro(){
        ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");

        comisionesBean.setBotonSalida("Confirmar dispensa");
        
        AttributeBinding estadoTCC;
        estadoTCC = ADFUtils.findControlBinding("IdEstadoTCC");
        LOGGER.warning("idOperacion :" + estadoTCC.getInputValue());

        if((Number)estadoTCC.getInputValue()==4){
                comisionesBean.setBotonSalida("Dispensar pago");
                comisionesBean.setAvisoCobro(Boolean.TRUE);
            }
        if((Number)estadoTCC.getInputValue()==6){
                comisionesBean.setBotonSalida("Confirmar pago");
                comisionesBean.setAvisoCobro(Boolean.FALSE);
                comisionesBean.setColorEstado("Green");
            }                         
                                     
    return comisionesBean.getAvisoCobro();               
    }
    
    public boolean isAvisoDeCobro() {
        boolean respuesta=false;
        ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");

        comisionesBean.setBotonSalida("Confirmar dispensa");
        comisionesBean.setAvisoCobro(Boolean.FALSE);
        
        AttributeBinding estadoTCC;
        estadoTCC = ADFUtils.findControlBinding("IdEstadoTCC");

        if((Number)estadoTCC.getInputValue()==4){
                comisionesBean.setBotonSalida("Dispensar pago");
                if(getCodigoTarea()==FenixConstants.PC02_GESTIONAR_COMISIONES){
                        comisionesBean.setAvisoCobro(Boolean.TRUE);
                    }

                respuesta= true;
            }
        if((Number)estadoTCC.getInputValue()==6){
                comisionesBean.setBotonSalida("Confirmar pago");
                comisionesBean.setColorEstado("Green");
                respuesta= false;
            } 
       
        return comisionesBean.getAvisoCobro();
    }

   
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

  public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea)
  {
    this.popupFinalizarTarea = popupFinalizarTarea;
  }

  public RichPopup getPopupFinalizarTarea()
  {
    return popupFinalizarTarea;
  }
  
  private Integer getCodigoTarea()
  {
      ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");

      if (null != comisionesBean.getCodigoTarea() && comisionesBean.getCodigoTarea().trim().length() > 0)
      {
          return Integer.parseInt(comisionesBean.getCodigoTarea());
      }

      return 0;
  }
  
  private Long getIdOperacion()
  {
      ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");
      
      if (null != comisionesBean.getIdOperacion() && comisionesBean.getIdOperacion().trim().length() > 0)
      {
          return Long.parseLong(comisionesBean.getIdOperacion());
      }

      return 0L;
  }
  
    /**
     * Overview: Metodo que invoca el boton superior [ BTN_DISPERSAR_PAGO && BTN_CONFIRMAR_PAGO ]
     * US: 6415
     * @Feature:6389 
     * Date: 04-11-2021
     * Developer: @RUGM
     * Step: 1
     * Nota: Rastreo de logica desde botones dinamicos
     * */
  
  public String invocarFinalizarTarea()
  {
      ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");

      List<String> budleKeyErroList = new ArrayList<String>();
      Boolean isValidFinalizarTarea = Boolean.FALSE;
      
      int codigoTarea = getCodigoTarea();
      AttributeBinding btnSalida;
      btnSalida = ADFUtils.findControlBinding("BtnSalida");
      String salida = null;
      LOGGER.warning("Opcion de Btn: " + btnSalida.getInputValue().toString() );
      
      
      if(null != btnSalida){
          salida=(String) btnSalida.getInputValue();  
      }else{
          LOGGER.warning("El valor de BtnSalida es nulo.");
      }
      
      LOGGER.warning("Codigo Tarea: " + codigoTarea );
      LOGGER.warning("Opcion de Btn: " + salida );
      switch (codigoTarea) 
      {
          /**
           * Overview: Ejecucion referente al codigo de tarea = 67 = GESTIONAR COMISION
           * US: 6415
           * @Feature:6389 
           * Date: 04-11-2021
           * Developer: @RUGM
           * Step: 2
           * Nota: Rastreo de logica desde botones dinamicos
           * */   
          case FenixConstants.PC02_GESTIONAR_COMISIONES: //TODO AJUSTAR
              LOGGER.warning("PC02_GESTIONAR_COMISIONES");
              isValidFinalizarTarea = Boolean.TRUE;
          
          if(salida.equalsIgnoreCase("Confirmar pago")){
                  /**
                   * Overview: Ejecutamos la logica para saber si la Operacion es candiddata a poder Confirma el Pago
                   * US: 6415
                   * @Feature:6389 
                   * Date: 04-11-2021
                   * Developer: @RUGM
                   * Step: 3
                   * Nota: Rastreo de logica desde botones dinamicos
                   * */       
                    boolean esCandidata = this.validaComisionesPagadas();
                    
                    if(esCandidata){
                        //            isValidFinalizarTarea=!validateDocumento(getIdOperacion(), FenixConstants.DOCUMENTO_COMISION_DISPENSADO);
                        //            budleKeyErroList.add(MSG_ERROR_DISPENSADO);
                        isValidFinalizarTarea = Boolean.TRUE;
                        comisionesBean.setMensajeConfirmacion("¿Confirma el pago de la comisión?");
                    }else{
                            isValidFinalizarTarea = Boolean.FALSE;
                            budleKeyErroList.add(MSG_COMISION_NO_PAGADA);
                        }
                
                  
              }
          
          if(salida.equalsIgnoreCase("Dispensar pago")){
                 isValidFinalizarTarea=validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                  budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
                  LOGGER.warning("IdOperacion "+ getIdOperacion());
                  LOGGER.warning("CodigoTarea " +getCodigoTarea().toString() );
                  LOGGER.warning("Intancia " + getInstanciaTarea());


                  comisionesBean.setMensajeConfirmacion("¿Confirma dispensar pago?");
              }
          
          if(salida.equalsIgnoreCase("Confirmar dispensa") || salida.equalsIgnoreCase("Finalizar tarea")){
                  isValidFinalizarTarea = Boolean.FALSE;
                  budleKeyErroList.add(MSG_BTN_ERROR_SALIDA);
              }
          
          break;
      
          case FenixConstants.PC02_VALIDAR_COMISIONES: //TODO AJUSTAR
            LOGGER.warning("PC02_VALIDAR_COMISIONES");
          isValidFinalizarTarea = Boolean.TRUE;
          if(!salida.equalsIgnoreCase("Confirmar dispensa")){
                  isValidFinalizarTarea = Boolean.FALSE;
                  budleKeyErroList.add(MSG_BTN_ERROR_SALIDA2);
              }
          break;
          case FenixConstants.PA02_REGISTRAR_COMISION: //TODO AJUSTAR
            LOGGER.warning("PA02_REGISTRAR_COMISION");
          isValidFinalizarTarea = validaComisionSubEstadoEnRegistro();
          if(!isValidFinalizarTarea){
              budleKeyErroList.add("MSG02_REGISTRAR_COMISION");
          }
          break;
          case FenixConstants.PA02_VALIDAR__REGISTRO_COMISION: //TODO AJUSTAR
            LOGGER.warning("PA02_VALIDAR__REGISTRO_COMISION");                            
               isValidFinalizarTarea = validaComisionvalidadasByOperacion();
          break;
    
          default:
              LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
          break;
      }
      
      LOGGER.warning("Finaliza  " + isValidFinalizarTarea );
      if (!isValidFinalizarTarea)
      {
          if(budleKeyErroList.size()>0)
          {
            for(String bundleKey : budleKeyErroList)
              JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
          }
          
      } else
      {
        finalizarTareaPopup();
      }
    
    return null;
  }
  
    public String invocarRetornar()
    {
        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        AttributeBinding btnSalida;
        btnSalida = ADFUtils.findControlBinding("BtnSalida");
        String salida=(String) btnSalida.getInputValue();
        Boolean validarEstado=(salida.equalsIgnoreCase("Confirmar dispensa")|| salida.equalsIgnoreCase("Confirmar pago"))? Boolean.TRUE:Boolean.FALSE;
      
                LOGGER.warning("PC02_RETORNAR_COMISIONES");
                if(!validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea())){
                        budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
                    }
                if(!validarEstado){
                        budleKeyErroList.add(MSG_BTN_ERROR_SALIDA2);
                    }
                    isValidFinalizarTarea=validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea()) && validarEstado;
                     
        if (!isValidFinalizarTarea)
        {
            if(budleKeyErroList.size()>0)
            {
              for(String bundleKey : budleKeyErroList)
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else
        {
          retornarPopup();
        }
      
      return null;
    }
  
    public String invocarMasInformacion()
    {
        ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");

        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean validaMasInformacion = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();
        LOGGER.warning("Codigo Tarea  " + codigoTarea );
        switch (codigoTarea) {
        case FenixConstants.PA02_REGISTRAR_COMISION: //TODO AJUSTAR
            LOGGER.warning("PA02_REGISTRAR_COMISION");
            validaMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            if (!validaMasInformacion) {
                LOGGER.warning("No se ha ingresado un comentario.");
                budleKeyErroList.add("MSG01_REGISTRAR_COMISINES");
            }
        break;
        case FenixConstants.PA02_VALIDAR__REGISTRO_COMISION: //TODO AJUSTAR
            LOGGER.warning("PA02_VALIDAR__REGISTRO_COMISION");
            validaMasInformacion =
                validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            if (!validaMasInformacion) {
                LOGGER.warning("No se ha ingresado un comentario.");
                budleKeyErroList.add("VALIDAR_REGISTRAR_COMISINES");
            }
        break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        LOGGER.warning("validaMasInformacion  " + validaMasInformacion );
        if (!validaMasInformacion)
        {
            if(budleKeyErroList.size()>0)
            {
              for(String bundleKey : budleKeyErroList)
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else
        {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getPopupMasInformacion().show(hints);
        }
      
      return null;
    }
    
    public String aceptarMasInformacion() {
        ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");

        List<String> budleKeyErroList = new ArrayList<String>();
        Boolean aceptarMasInformacion = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();
        
        LOGGER.warning("Codigo Tarea  " + codigoTarea );
        switch (codigoTarea) {
        case FenixConstants.PA02_REGISTRAR_COMISION: //TODO AJUSTAR
            LOGGER.warning("PA02_REGISTRAR_COMISION");
            aceptarMasInformacion = eliminaComisionCancelarRegistrarComision();
            if(!aceptarMasInformacion){
                LOGGER.warning("Error al eliminar las comisiones.");
                budleKeyErroList.add("ERROR_CANCELAR_PROCESO_REGISTRAR_COMISION");
            }
            break;
            case FenixConstants.PA02_VALIDAR__REGISTRO_COMISION: //TODO AJUSTAR
                LOGGER.warning("PA02_VALIDAR__REGISTRO_COMISION");
                aceptarMasInformacion = actualizarSubEstadoComision(FenixConstants.SUBESTADO_COMISION_EN_REGISTRO);
                if(!aceptarMasInformacion){
                    LOGGER.warning("Error al eliminar las comisiones.");
                    budleKeyErroList.add("ERROR_CANCELAR_PROCESO_REGISTRAR_COMISION");
                }
                break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        LOGGER.warning("validaMasInformacion  " + aceptarMasInformacion );
        if (!aceptarMasInformacion)
        {
            if(budleKeyErroList.size()>0)
            {
              for(String bundleKey : budleKeyErroList)
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else
        {
            LOGGER.warning("Aceptar mas informacion correctamente");
            cargarDocumentosOnBase();
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();   
        }
        
        return null;
    }
    
  public void finalizarTareaPopup()
  {
      RichPopup.PopupHints hints = new RichPopup.PopupHints();
      popupFinalizarTarea.show(hints);
  }
  
  public String cancelarFinalizarTarea()
  {
      popupFinalizarTarea.hide();
      return null;
  }
  
  public String aceptarFinalizarTarea()
  {
    popupFinalizarTarea.hide();
    Boolean esValidoFinalizar = Boolean.FALSE;
        
    int codigoTarea = getCodigoTarea();
      AttributeBinding btnSalida;
      btnSalida = ADFUtils.findControlBinding("BtnSalida");
      String salida=null;
      if(null != btnSalida){
          salida=(String) btnSalida.getInputValue();  
      }else{
          LOGGER.warning("El valor de BtnSalida es nulo.");
      }
      Long validado=28L;
      Long porValidar=21L;
      Long estadoPagado=6L;
    
    switch (codigoTarea)
    {

        case FenixConstants.PC02_GESTIONAR_COMISIONES: //TODO AJUSTAR
            LOGGER.warning("PC02_GESTIONAR_COMISIONES");         
            
            esValidoFinalizar = Boolean.TRUE;
        if(salida.equalsIgnoreCase("Confirmar pago")){
                LOGGER.warning("*Into Confirmar Pago");
                esValidoFinalizar=actualizarComisionTCC(estadoPagado, validado);
            }
        if(salida.equalsIgnoreCase("Dispensar pago")){
                LOGGER.warning("*Into Dispensar Pago");
                esValidoFinalizar=actualizarComisionTCC(FenixConstants.PC02_ESTADO_COMISION_VALOR_DISPENSA, porValidar);
                actualizarPayloadElement("esDispensa", Boolean.TRUE);
            }
        
        break;
    
        case FenixConstants.PC02_VALIDAR_COMISIONES: //TODO AJUSTAR
          LOGGER.warning("PC02_VALIDAR_COMISIONES");
        esValidoFinalizar=actualizarComisionTCC(FenixConstants.PC02_ESTADO_COMISION_DISPENSADO_ADMON, validado);
        break;
        case FenixConstants.PA02_REGISTRAR_COMISION:
          LOGGER.warning("PA02_REGISTRAR_COMISION");
        esValidoFinalizar = actualizarSubEstadoComision(FenixConstants.SUBESTADO_COMISION_PORVALIDAR);
        if(esValidoFinalizar){
            LOGGER.warning("Actualizacion correcta.");
        }else{
            LOGGER.warning("Error al actualizar las comisiones, no se puede finalizar la tarea.");
            JSFUtils.addFacesErrorMessage("ERROR_ACTUALIZAR_SUBESTADO_COMISION");
        }
        break;
        case FenixConstants.PA02_VALIDAR__REGISTRO_COMISION: //TODO AJUSTAR
          LOGGER.warning("PA02_VALIDAR__REGISTRO_COMISION");
        //if(registrarComisionFlex()){
            esValidoFinalizar = actualizarEstadoComision(FenixConstants.ESTADO_COMISION_REGISTRADA);
            esValidoFinalizar = Boolean.TRUE;
//        }else{
//            LOGGER.warning("no se registraron las comisiones en Flex");
//            JSFUtils.addFacesErrorMessage("Error al actualizar las comisiones.");           
//        }
        break;
        default:
            LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
        break;
    }
      LOGGER.warning("FinalizaTarea " +esValidoFinalizar);
    
    if(esValidoFinalizar)
    {
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
            LOGGER.warning("LLega aqui");
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
        }
    
    else
    {
        return null;
    }
    
  }
  
    public void retornarPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupRetornar.show(hints);
    }
    
    public String cancelarRetornar()
    {
        popupRetornar.hide();
        return null;
    }
    
    public String aceptarRetornar()
    {
        Long porValidado=21L;   
        AttributeBinding btnSalida;
        btnSalida = ADFUtils.findControlBinding("BtnSalida");
        String salida=(String) btnSalida.getInputValue();
        
        Boolean respuesta=Boolean.TRUE;
        if(!salida.equalsIgnoreCase("Confirmar pago")){
            respuesta=actualizarComisionTCC(FenixConstants.PC02_ESTADO_COMISION_POR_PAGAR, porValidado);
        }
        LOGGER.warning("FinalizaTarea " +respuesta);
        
        popupRetornar.hide();
        if(respuesta){
                actualizarPayloadElement("esDispensa", Boolean.FALSE);
                LOGGER.warning("LLega aqui");
                cargarDocumentosOnBase();
                
                InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                return invokeActionBean.invokeOperation();
            }
        return null;
    }
    
    public Boolean actualizarComisionTCC(long estado, long subestado){
            LOGGER.warning("Inicia metodo actualizarComisionTCC");
            Boolean result=Boolean.FALSE;
            BindingContainer bindings = getBindings();
            try {
              
            ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");              
            OperationBinding operationBinding = bindings.getOperationBinding("actualizarTCCAdmon");
            
            LOGGER.warning("IdComision recuperado: "+comisionesBean.getIdComision());
            
            operationBinding.getParamsMap().put("idTCC",comisionesBean.getIdComision());
            operationBinding.getParamsMap().put("idEstadoTcc", estado);
            operationBinding.getParamsMap().put("subEstado", subestado);
            operationBinding.getParamsMap().put("tipoTCC", FenixConstants.COMISION);
          
          
            result = (Boolean) operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    LOGGER.warning("Error");
                    return false;
                }
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "Error en actualizarComisionTCC " + e.getClass() + ":" + e.getMessage());
  
            }
            LOGGER.warning("termina metodo actualizarComisionTCC");
        return result;
        }
    /**
     * Metodo que actualiza el subestado de las comisiones
     * @param idTcaSubEstadoTcc
     * @return
     */
    public Boolean actualizarSubEstadoComision(Integer idTcaSubEstadoTcc){
        LOGGER.warning("Entra en actualizarSubEstadoComision.");
        Boolean comisionActualizada = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        try{
            ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");
            
            operationBinding = bindings.getOperationBinding("actualizarSubEstadoComision");
            operationBinding.getParamsMap().put("idTcaSubEstadoTcc", idTcaSubEstadoTcc);
            operationBinding.getParamsMap().put("idOperacion", comisionesBean.getIdOperacion());
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                comisionActualizada = (Boolean)operationBinding.getResult();
                if(comisionActualizada){
                    LOGGER.warning("El subestado de las comisiones fueron actualizadas correctamente.");
                }else{
                    LOGGER.warning("Error al actualizar las comisiones.");
                }
            }else{
                LOGGER.warning("El valor de retorno es nulo.");
            }
        }catch(Exception e){
            LOGGER.warning("Error en actualizarSubEstadoComision.", e);
        }
        return comisionActualizada;
    }
    
    /**
     *  Metodo para registrar las comisiones en flexcube
     *  creadas en 'Registrar comisiones'
     *  
     * @return
     */
    public Boolean registrarComisionFlex(){
        LOGGER.warning("Entra en registrarComisionFlex");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        Long idOperacion = null;
        Boolean actualiza = Boolean.FALSE;
        try {
            ComisionesBean comisionesBean =
                (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");
            
            if(null != comisionesBean.getIdOperacion()){
                idOperacion = Long.valueOf(comisionesBean.getIdOperacion());
                LOGGER.warning("El valor de la operacion es : " + idOperacion);
            }else{
                LOGGER.warning("El valor de la operacion es nulo");
            }
            operationBinding = bindings.getOperationBinding("registrarComisionesFlexcube");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                actualiza = (Boolean) operationBinding.getResult();
                if (actualiza) {
                    LOGGER.warning("Las comisiones fueron registradas correctamente.");
                } else {
                    LOGGER.warning("Error al registrar las comisiones.");
                    if(!(operationBinding.getErrors().isEmpty())){
                        JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    }else{
                        LOGGER.warning("No se regresaron errores para mostrar.");
                    }
                }
            } else {
                LOGGER.warning("El valor de retorno es nulo.");
            }
        } catch (Exception e) {
            LOGGER.warning("Error en registrarComisionFlex.", e);
        }
        return actualiza;
    }
    /**
     * Metodo que actualiza el estado de las comisiones
     * @param idTcaSubEstadoTcc
     * @return
     */
    public Boolean actualizarEstadoComision(Integer idTcaEstadoTcc){
        LOGGER.warning("Entra en actualizarEstadoComision.");
        Boolean comisionActualizada = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        try{
            ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");
            
            operationBinding = bindings.getOperationBinding("actualizarEstadoComision");
            operationBinding.getParamsMap().put("idTcaEstadoTcc", idTcaEstadoTcc);
            operationBinding.getParamsMap().put("idOperacion", comisionesBean.getIdOperacion());
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                comisionActualizada = (Boolean)operationBinding.getResult();
                if(comisionActualizada){
                    LOGGER.warning("El estado de las comisiones fueron actualizadas correctamente.");
                }else{
                    LOGGER.warning("Error al actualizar el estado de las comisiones.");
                }
            }else{
                LOGGER.warning("El valor de retorno es nulo.");
            }
        }catch(Exception e){
            LOGGER.warning("Error en actualizarSubEstadoComision.", e);
        }
        return comisionActualizada;
    } 
    
    /**
     * Se invoca metodo que valida que todas las comisiones hayan sido 
     * editadas y se haya guardado la informacion requerida.
     * @return
     */
    public Boolean validaComisionSubEstadoEnRegistro(){
        LOGGER.warning("Entra en validaComisionSubEstadoEnRegistro.");
        Boolean enRegistro = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        try{
            ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");
            
            operationBinding = bindings.getOperationBinding("validaComisionEnRegistro");
            operationBinding.getParamsMap().put("idOperacion", comisionesBean.getIdOperacion());                                                                         
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                enRegistro = (Boolean)operationBinding.getResult();
                if(enRegistro){
                    LOGGER.warning("Las comisiones han sido registradas.");
                }else{
                    LOGGER.warning("Faltan comisiones por registrar.");
                }
            }else{
                LOGGER.warning("El valor de retorno es nulo.");
            }
        }catch(Exception e){
            LOGGER.warning("Error en actualizarSubEstadoComision.", e);
        }
        return enRegistro;
    }
    
    
    /**
     * Se invoca metodo que valida que todas las comisiones hayan sido 
     * validadas
     * @return
     */
    public Boolean validaComisionvalidadasByOperacion(){
        LOGGER.warning("inicia metodo validaComisionvalidadasByOperacion.");
        Boolean comisionesValidadas = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        try{
            ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");
            
            operationBinding = bindings.getOperationBinding("comisionesValidadasByOperacion");
            operationBinding.getParamsMap().put("idOperacion", comisionesBean.getIdOperacion());                                                                         
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                comisionesValidadas = (Boolean)operationBinding.getResult();
                if(comisionesValidadas){
                    LOGGER.warning("Las comisiones han sido comisionesValidadas.");
                }else{
                    LOGGER.warning("Faltan comisiones por comisionesValidadas.");                                    
                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                    getPopUpErrorComisionesValidadas().show(hints);
                    
                }
            }else{
                LOGGER.warning("El valor de retorno es nulo.");
            }
        }catch(Exception e){
            LOGGER.warning("Error al verificar comisiones validadas.", e);
        }
        
        LOGGER.warning("termina metodo validaComisionvalidadasByOperacion.");
        return comisionesValidadas;
    }
    
    
    /**
     * Metodo para eliminar las comisiones cuando se cancela el proceso.
     * @param idTcaSubEstadoTcc
     * @return
     */
    public Boolean eliminaComisionCancelarRegistrarComision(){
        LOGGER.warning("Entra en eliminaComisionCancelarRegistrarComision.");
        Boolean esEliminada = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        try{
            ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");
            
            operationBinding = bindings.getOperationBinding("eliminaComisionCancelarRegistrarComision");
            operationBinding.getParamsMap().put("idTcaSubEstadoTcc", null);
            operationBinding.getParamsMap().put("idOperacion", comisionesBean.getIdOperacion());
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                LOGGER.warning("Error al eliminar las comisiones."); 
            }else{
                if(null != operationBinding.getResult()){
                    esEliminada = (Boolean)operationBinding.getResult();
                    if(esEliminada){
                        LOGGER.warning("Las comisiones fueron eliminadas correctamente");
                    }else{
                        LOGGER.warning("Error al eliminar las comisiones.");
                    }
                }else{
                    LOGGER.warning("El valor de retorno es nulo.");
                } 
            }
        }catch(Exception e){
            LOGGER.warning("Error en actualizarSubEstadoComision.", e);
        }
        return esEliminada;
    }
    
    


    /**
     * Overview: metodo que asigna le valor del boton { 4 = Dispensar pago / 6 = Confirmar pago}dinamicamente
     * Ticket: 98879
     * US: 6339
     * Date: 04-11-2021
     * Developer: @RUGM
     * Step: 1
     * Nota: etiquetas de rastreo botones dinamicos
     * */
    public String isOutcomeTexto() {
        ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");

        comisionesBean.setBotonSalida("Confirmar dispensa");
        
        AttributeBinding estadoTCC;
        estadoTCC = ADFUtils.findControlBinding("IdEstadoTCC");
        LOGGER.warning("EstadoTCC :" + estadoTCC.getInputValue());

        if((Number)estadoTCC.getInputValue()==4){
                comisionesBean.setBotonSalida("Dispensar pago");
                comisionesBean.setAvisoCobro(Boolean.TRUE);
            }
        if((Number)estadoTCC.getInputValue()==6){
                comisionesBean.setBotonSalida("Confirmar pago");
                comisionesBean.setAvisoCobro(Boolean.FALSE);
                comisionesBean.setColorEstado("Green");
            }
        return comisionesBean.getBotonSalida();
    }


    public void cerrarPopUpErrorComisionesValidadas(ActionEvent actionEvent){
            getPopUpErrorComisionesValidadas().hide();
        }

    public RichPopup getPopupRetornar() {
        return popupRetornar;
    }
    
    public void setPopupRetornar(RichPopup popupRetornar) {
        this.popupRetornar = popupRetornar;
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
    public void downloadAvisoCobro(FacesContext facesContext, OutputStream outputStream)
    {
        ComisionesBean comisionesBean = (ComisionesBean) JSFUtils.resolveExpression("#{pageFlowScope.comisionesBean}");

        Long idOperacion = getIdOperacion();
        byte[] documentoAvisoCobro = null;
        try
        {
        applyIE11Fix(DOCUMENTO_FILE_NAME);
        documentoAvisoCobro= crearDoc(idOperacion, comisionesBean.getIdComision());
            if (documentoAvisoCobro!= null && documentoAvisoCobro.length>0){
                    outputStream.write(documentoAvisoCobro);
                }
            
    } catch (IOException ioex)
    {
        ioex.printStackTrace();
    } finally
    {
        flushAndCloseOutputStream(outputStream);
    }
    }
    private byte[] crearDoc(long idOperacion, Long idComision) {
            String mensajeError=null;
            Boolean esError=Boolean.FALSE;
            
            CrearAvisoCobroComisionV2RequestType request=new CrearAvisoCobroComisionV2RequestType();

            CrearAvisoCobroComisionV2ResponseType response=null;
            
                    try{
                Comision comision = this.initComisionService();
                ComisionPT comisionPT = comision.getComision12Bnd();
                // Add your code to call the desired methods.
                
                request.setIdComision(idComision);
                request.setIdOperacion(idOperacion);
                    String mensaje = null;
                    StringWriter xmlEntrada = null;
                    StringWriter xmlSalida = null;      
                    mensaje = "Invocando Servicio - Aviso de cobro comision-";
                   //try{xmlEntrada = writeXMLRequest(request, request.getClass());}catch(Exception ex){;}
                          try{LOGGER.log(ADFLogger.WARNING,">HNWS"+ writeXMLRequest(request, request.getClass())); }catch(Exception ex){;}
            //response = comisionPT.crearAvisoCobroComision(request);
            response = comisionPT.crearAvisoCobroComisionV2(request);
            mensaje = "Invocando Servicio - Aviso de cobro comision -";
            try{LOGGER.log(ADFLogger.WARNING,">HNWS"+  writeXMLRequest(response, response.getClass()));}catch(Exception ex){;}
  
                        if(response.getResultado().getResult().value()=="ERROR"){
                                mensajeError=response.getResultado().getMessage();
                                mensajeError=mensajeError.concat(response.getResultado().getError().getErrorDescription());
                            esError=Boolean.TRUE;
                            }
                }catch(Exception e){
                                 LOGGER.log(ADFLogger.ERROR, "Error al generar el aviso de cobro " + e.getClass() + ":" + e.getMessage());
                                 
                             } 
            finally {
                        if (esError) {
                            JSFUtils.addFacesErrorMessage(mensajeError);
                        }
                    }
            return response != null ? response.getDocumentoCobroComision() : null;
        }
    
    public StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);       
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);        
        return writer;
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
        return DOCUMENTO_FILE_NAME;
    }
  
  private Comision initComisionService() {
      DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
      ViewObject configuracionVoImpl = dcItteratorBindings.getViewObject();
      Row row = configuracionVoImpl.getRow(new Key(new Object[]{IWsdlLocation.COMISION}));
      String wsdl = row == null ? null : (String) row.getAttribute("Valor");

      return IWsdlLocation.Service.getInstance(Comision.class, wsdl);
  }

    public void setPopupMasInformacion(RichPopup popupMasInformacion) {
        this.popupMasInformacion = popupMasInformacion;
    }

    public RichPopup getPopupMasInformacion() {
        return popupMasInformacion;
    }

    public String cancelarMasInformacion() {
        LOGGER.warning("Entra en cancelarMasInformacion");
        getPopupMasInformacion().hide();
        return null;
    }

    public void setPopUpErrorComisionesValidadas(RichPopup popUpErrorComisionesValidadas) {
        this.popUpErrorComisionesValidadas = popUpErrorComisionesValidadas;
    }

    public RichPopup getPopUpErrorComisionesValidadas() {
        return popUpErrorComisionesValidadas;
    }
    
    /**
     * Overview: metodo que recupera el valor del TCC para valdiar si la Operacion es candidata a continuar o detenerse en caso no haber pagado las comisiones
     * Ticket: 98879
     * US: 6339
     * Date: 04-11-2021
     * Developer: @RUGM
     * Step: 4
     * Nota: etiquetas de rastreo botones dinamicos
     * */
    public Boolean validaComisionesPagadas(){
        Boolean ban = Boolean.TRUE;
        //Obetenemoe el valor del atributo
        //#{bindings.TipoEstadoTCC.inputValue}
        
        AttributeBinding tipoEstadoTcc;
        tipoEstadoTcc = ADFUtils.findControlBinding("TipoEstadoTCC");
        LOGGER.warning("TipoEstadoTCC:  " + tipoEstadoTcc.getInputValue().toString() );
        String salida = null;
        if(null != tipoEstadoTcc){
            salida = (String) tipoEstadoTcc.getInputValue();  
            
            if(salida.equals("Pagada")){
                ban = Boolean.TRUE;
                LOGGER.warning("Operacion con comisiones Pagadas, es candidata a continuar con la logica");
            }else{
                ban = Boolean.FALSE;
                LOGGER.warning("Operacion con comisiones No Pagadas, es candidata a detener la logica");
            }
            
            
        }else{
            LOGGER.warning("El valor de TipoEstadoTCC es nulo.");
            ban = Boolean.FALSE;
        }
        
        return ban;
        
    }
}
