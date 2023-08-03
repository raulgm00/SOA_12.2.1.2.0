package pa06admonclienteght.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.vo.DetalleClienteVORowImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class ClienteActionsBean extends FenixActionBean
{
  public static final ADFLogger LOGGER = ADFLogger.createADFLogger(ClienteActionsBean.class);
  public static final String BUNDLE ="pa06admonclienteght.PA06AdmonClienteGHTBundle";
  
  /*Define nombre del Iterador para obtener los datos de Linea Aprobacion asociadas a una table y el Modelo*/
  public static final String DETALLE_CLIENTE_ITERATOR = "DetalleClienteVOIterator";
  public static final String MSG_ERROR_AGREGAR_COMENTARIO = "ERROR_NO_COMENTARIOS";
  public static final String MSG_ERROR_AGREGAR_COMENTARIO2 = "ERROR_NO_COMENTARIOS2";
  
  private Boolean isClienteDefinitivo;
  private RichPopup popupFinalizarTarea;
  private RichPopup popupMasInfo;
  
  public ClienteActionsBean()
  {
    super();
  }

  public Boolean getIsClienteDefinitivo()
  {
    ClienteBean managedBean = (ClienteBean) JSFUtils.resolveExpression("#{pageFlowScope.clienteBean}");

    Number tipoCliente = managedBean.getNTipoCliente();
    
    if(null!=tipoCliente && tipoCliente.compareTo(1)==0) // 1 Cliente Prospecto
    {
      return Boolean.FALSE;
    }
    
    return Boolean.TRUE;
  }

  public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea)
  {
    this.popupFinalizarTarea = popupFinalizarTarea;
  }

  public RichPopup getPopupFinalizarTarea()
  {
    return popupFinalizarTarea;
  }

  public void setPopupMasInfo(RichPopup popupMasInfo)
  {
    this.popupMasInfo = popupMasInfo;
  }

  public RichPopup getPopupMasInfo()
  {
    return popupMasInfo;
  }
  
  private Integer getCodigoTarea()
  {
      ClienteBean managedBean = (ClienteBean) JSFUtils.resolveExpression("#{pageFlowScope.clienteBean}");

      if (null != managedBean.getCodigoTarea() && managedBean.getCodigoTarea().trim().length() > 0)
      {
          return Integer.parseInt(managedBean.getCodigoTarea());
      }

      return 0;
  }
  
  private Boolean getEsCambioDefinitivo()
  {
      ClienteBean managedBean = (ClienteBean) JSFUtils.resolveExpression("#{pageFlowScope.clienteBean}");

      return managedBean.getEsCambioDefinitivo();
  }
  
  /*private Boolean getEsClienteProspecto()
  {
    ClienteBean managedBean = (ClienteBean) JSFUtils.resolveExpression("#{pageFlowScope.clienteBean}");

    Number tipoCliente = managedBean.getNTipoCliente();
    
    if(null!=tipoCliente && tipoCliente.compareTo(1)==0)
    {
      return Boolean.TRUE;
    }
    
    return Boolean.FALSE;
  }*/
  
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
  
  public void masInformacionPopup()
  {
      RichPopup.PopupHints hints = new RichPopup.PopupHints();
      popupMasInfo.show(hints);
  }
  
  public String cancelarMasInformacion()
  {
      popupMasInfo.hide();
      return null;
  }
  
  public String invocarMasInformacion()
  {
      List<String> budleKeyErroList = new ArrayList<String>();
      Boolean esValidoMasInformacion = Boolean.FALSE;
      
      int codigoTarea = getCodigoTarea();

      switch (codigoTarea)
      {
          case FenixConstants.PA06_APROBAR_CLIENTE: 
              LOGGER.fine("PA06_APROBAR_CLIENTE");
              esValidoMasInformacion = validarComentarioCliente(getIdCliente(), getCodigoTarea().toString(), getInstanciaTarea());
            
              if(!esValidoMasInformacion)
              { 
                budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
              }
              else
              {            
                esValidoMasInformacion = Boolean.TRUE;
              }
          
          break;
      
          case FenixConstants.PA06_EDITAR_CLIENTE: 
            LOGGER.fine("PA06_EDITAR_CLIENTE");
           
              esValidoMasInformacion = validarComentarioCliente(getIdCliente(), getCodigoTarea().toString(), getInstanciaTarea());
              
              if(!esValidoMasInformacion)
              { 
                budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO2);
              }
              else
              {            
                esValidoMasInformacion = Boolean.TRUE;
              }
          
          break;
    
          default:
              LOGGER.log(ADFLogger.ERROR, "invocarMasInformacion(): Codigo de tarea no valido - " + codigoTarea);
          break;
      }
        
      if (!esValidoMasInformacion)
      {
          if(budleKeyErroList.size()>0)
          {
            for(String bundleKey : budleKeyErroList)
              JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
          }
      } else
      {
        masInformacionPopup();
      }
    
    return null;
  }
  
  public String invocarFinalizarTarea()
  {
      List<String> budleKeyErroList = new ArrayList<String>();
      Boolean esValidoFinalizarTarea = Boolean.FALSE;
      
      int codigoTarea = getCodigoTarea();

      switch (codigoTarea)
      {
          case FenixConstants.PA06_APROBAR_CLIENTE:
              LOGGER.fine("PA06_APROBAR_CLIENTE");
              esValidoFinalizarTarea = Boolean.TRUE;
          break;
      
          case FenixConstants.PA06_EDITAR_CLIENTE: 
            LOGGER.fine("PA06_EDITAR_CLIENTE");
            esValidoFinalizarTarea = Boolean.TRUE;
          break;
    
          default:
              LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
          break;
      }
        
      if (!esValidoFinalizarTarea)
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
  
  public String aceptarMasInformacion()
  {
    popupMasInfo.hide();
    
    Boolean esValidoMasInformacion = Boolean.TRUE;
        
    int codigoTarea = getCodigoTarea();
    
    switch (codigoTarea)
    {
        case FenixConstants.PA06_APROBAR_CLIENTE: 
            LOGGER.fine("PA06_APROBAR_CLIENTE");
            esValidoMasInformacion = Boolean.TRUE;
        break;
    
        case FenixConstants.PA06_EDITAR_CLIENTE: 
          LOGGER.fine("PA06_EDITAR_CLIENTE");
          esValidoMasInformacion = Boolean.TRUE;
        break;
    
        default:
            LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
        break;
    }
    
    if(esValidoMasInformacion)
    {        
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    else
    {
        LOGGER.log(ADFLogger.ERROR, "aceptarMasInformacion(): " + esValidoMasInformacion);
        return null;
    }
  }
  
  public String aceptarFinalizarTarea()
  {
    popupFinalizarTarea.hide();
    
    Boolean esValidoFinalizar = Boolean.TRUE;
        
    int codigoTarea = getCodigoTarea();
    
    switch (codigoTarea)
    {
        case FenixConstants.PA06_APROBAR_CLIENTE: 
            LOGGER.fine("PA06_APROBAR_CLIENTE");
            esValidoFinalizar = Boolean.FALSE;

            OperationBinding oper = null;
            try{
                oper = executeOperBinding(null, "actualizarCliente");    
                if(null != oper && oper.getErrors().isEmpty())
                {
                  esValidoFinalizar = Boolean.TRUE;
                }
                
                if(esValidoFinalizar && (getEsCambioDefinitivo() && !getIsClienteDefinitivo())) 
                {
                  esValidoFinalizar = Boolean.FALSE;
                  oper = executeOperBinding(null, "crearClienteDefinitivo");    
                  if(null != oper && oper.getErrors().isEmpty())
                  {
                    esValidoFinalizar = Boolean.TRUE;
                  }
                  
                }
                
            }catch(Exception e){
                LOGGER.severe("Error al ejecutar la actualizacion de cliente", e);
                oper = null;
            }
        
        break;
    
        case FenixConstants.PA06_EDITAR_CLIENTE: 
          LOGGER.fine("PA06_EDITAR_CLIENTE");
          
          actualizarPayload();
          
          esValidoFinalizar = Boolean.TRUE;
        break;
    
        default:
            LOGGER.log(ADFLogger.ERROR, "invokeFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
        break;
    }
    
    if(esValidoFinalizar)
    {        
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    else
    {
        LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): " + esValidoFinalizar);
        return null;
    }
  }
  
  
  private void actualizarPayload()
  {
    DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator(DETALLE_CLIENTE_ITERATOR);
    ViewObject voData = dcItteratorBindings.getViewObject(); 
    
    Row currentRow = voData.getCurrentRow();
    
    //
    String[] names = currentRow.getAttributeNames();
    for (String name : names) {
        LOGGER.warning("Datos del current row: ".concat(currentRow.getAttribute(name) == null ? "none" : currentRow.getAttribute(name).toString()));
    }
    //
    
    String sRazonSocial = (String)currentRow.getAttribute("RazonSocial");
      String sBicCode = (String)currentRow.getAttribute("BicCode");
      String sDireccion = (String)currentRow.getAttribute("Direccion");
    Number nTipoPersona = (Number)currentRow.getAttribute("TipoPersona");
    Number nSector = (Number)currentRow.getAttribute("Sector");
    Number nPais = (Number)currentRow.getAttribute("Pais");
    Number nDiaPago = (Number)currentRow.getAttribute("DiaPago");
    Number nOficina = (Number)currentRow.getAttribute("Oficina");
    String sAbreviatura = (String)currentRow.getAttribute("Abreviatura");
    Number nTipoCliente = (Number)currentRow.getAttribute("TipoCliente");
    Number nTipoInstitucion = (Number)currentRow.getAttribute("TipoInstitucion");
    Number nGrupoEconomico = (Number)currentRow.getAttribute("GrupoEconomico");
    String sNumeroIdentificacion = (String)currentRow.getAttribute("NumeroIdentificacion");
      Number nReqEnvioAvisoCobroAut = (Number)currentRow.getAttribute("ReqEnvioAvisoCobroAut");
    
    actualizarPayloadElement("razonSocial", sRazonSocial==null? "" : sRazonSocial);
      actualizarPayloadElement("bicCode", sBicCode==null? "" : sBicCode);
      actualizarPayloadElement("direccion", sDireccion==null? "" : sDireccion);
    actualizarPayloadElement("idTipoPersona", nTipoPersona == null? null: nTipoPersona.intValue());
    actualizarPayloadElement("idSector", nSector == null? null:  nSector.intValue());
    actualizarPayloadElement("idPais", nPais == null? null: nPais.intValue());
    actualizarPayloadElement("diaPago", nDiaPago == null? null: nDiaPago.intValue());
    actualizarPayloadElement("idOficina", nOficina == null? null: nOficina.intValue());
    actualizarPayloadElement("abreviatura", sAbreviatura == null? "": sAbreviatura);
    actualizarPayloadElement("idTipoCliente", nTipoCliente == null? null: nTipoCliente.intValue());
    actualizarPayloadElement("idTipoInstitucion", nTipoInstitucion == null? null: nTipoInstitucion.intValue());
    actualizarPayloadElement("idGrupoEconomico", nGrupoEconomico == null? null: nGrupoEconomico.intValue());
    actualizarPayloadElement("numeroIdentificacion",sNumeroIdentificacion == null? "": sNumeroIdentificacion);
    actualizarPayloadElement("reqEnvioAvisoCobroAut", nReqEnvioAvisoCobroAut == null ? null : nReqEnvioAvisoCobroAut.intValue());
  }
  
  public Boolean validarComentarioCliente(Long idCliente, String idTcaTareaBpm, String IdInstanciaTarea)
  {
      BindingContainer bindings = getBindings();
      OperationBinding operationBinding = bindings.getOperationBinding("validarComentarioCliente");

      operationBinding.getParamsMap().put("IdInstanciaTarea", IdInstanciaTarea);
      operationBinding.getParamsMap().put("idTcaTareaBpm", idTcaTareaBpm);
      operationBinding.getParamsMap().put("idCliente", idCliente);

      Boolean result = (Boolean) operationBinding.execute();

      Object object = operationBinding.getResult();
      
      if (!operationBinding.getErrors().isEmpty())
      {
          return null;
      }
      
      return (result);
  }

  private Long getIdCliente()
  {
    ClienteBean clienteBean = (ClienteBean) JSFUtils.resolveExpression("#{pageFlowScope.clienteBean}");
    
    if (null != clienteBean.getCodigoCliente() && clienteBean.getCodigoCliente().trim().length() > 0)
    {
        return Long.parseLong(clienteBean.getCodigoCliente());
    }

    return 0L;
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

  // El día de Pago debe ser entre 1 y 31
  public void valueChangeMethod(ValueChangeEvent e){
      //System.out.println("---ok--valida día pago ");
      if(e.getNewValue() != null){
            e.getComponent().processUpdates(FacesContext.getCurrentInstance());
            oracle.jbo.domain.Number  diaDePago = (oracle.jbo.domain.Number) ADFUtils.getBoundAttributeValue("DiaPago");
            if(diaDePago != null){
                    LOGGER.warning("--->diaPago "+diaDePago.intValue());
                    if((diaDePago.intValue() < 1) || (diaDePago.intValue() > 31)) {
                        ADFUtils.setBoundAttributeValue("DiaPago", null);
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("DIA_DE_PAGO_DEBE_SER_ENTRE_UNO_TRESUNO", BUNDLE));    
                    }   
                }             
          }    
  }
  
}
