package pa17leccionesaprendidasght.beans;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class LeccionAprendidaActionBean extends FenixActionBean 
{
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(LeccionAprendidaActionBean.class);
    public static final String BUNDLE ="pa17leccionesaprendidasght.PA17LeccionesAprendidasGHTBundle";
    
    private RichPopup popupFinalizarTarea;
    private RichPopup popupCancelar;
    private RichPopup popupValidar;
    private RichPopup popupRechazar;
    private RichPopup popupApelar;
    
    public LeccionAprendidaActionBean() {
        super();
    }
    
    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea)
    {
      this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupFinalizarTarea()
    {
      return popupFinalizarTarea;
    }
    
    public void setPopupCancelar(RichPopup popupCancelar)
    {
      this.popupCancelar = popupCancelar;
    }

    public RichPopup getPopupCancelar()
    {
      return popupCancelar;
    }

    public void setPopupValidar(RichPopup popupValidar)
    {
      this.popupValidar = popupValidar;
    }

    public RichPopup getPopupValidar()
    {
      return popupValidar;
    }
    
    public void setPopupRechazar(RichPopup popupRechazar)
    {
      this.popupRechazar = popupRechazar;
    }

    public RichPopup getPopupRechazar()
    {
      return popupRechazar;
    }

    public void setPopupApelar(RichPopup popupApelar)
    {
      this.popupApelar = popupApelar;
    }

    public RichPopup getPopupApelar()
    {
      return popupApelar;
    }
    
    private Integer getCodigoTarea()
    {
        LeccionAprendidaBean managedBean = (LeccionAprendidaBean) JSFUtils.resolveExpression("#{pageFlowScope.leccionAprendidaBean}");

        if (null != managedBean.getCodigoTarea() && managedBean.getCodigoTarea().trim().length() > 0)
        {
            return Integer.parseInt(managedBean.getCodigoTarea());
        }

        return 0;
    }
    
    private Long getIdOperacion()
    {
        LeccionAprendidaBean managedBean = (LeccionAprendidaBean) JSFUtils.resolveExpression("#{pageFlowScope.leccionAprendidaBean}");
        
        if (null != managedBean.getIdOperacion() && managedBean.getIdOperacion().trim().length() > 0)
        {
            return Long.parseLong(managedBean.getIdOperacion());
        }

        return 0L;
    }
    
    private Long getIdLeccionAprendida()
    {
        LeccionAprendidaBean managedBean = (LeccionAprendidaBean) JSFUtils.resolveExpression("#{pageFlowScope.leccionAprendidaBean}");
        
        if (null != managedBean.getIdLeccionAprendida() && managedBean.getIdLeccionAprendida().trim().length() > 0)
        {
            return Long.parseLong(managedBean.getIdLeccionAprendida());
        }

        return 0L;
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
    
    public void cancelarPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCancelar.show(hints);
    }
    
    public String cancelarCancelar()
    {
        popupCancelar.hide();
        return null;
    }
    
    public void validarPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupValidar.show(hints);
    }
    
    public String cancelarValidar()
    {
        popupValidar.hide();
        return null;
    }
    
    public void rechazarPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupRechazar.show(hints);
    }
    
    public String cancelarRechazar()
    {
        popupRechazar.hide();
        return null;
    }
    
    public void apelarPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupApelar.show(hints);
    }
    
    public String cancelarApelar()
    {
        popupApelar.hide();
        return null;
    }
    
    //Metodos de las acciones en pantalla
    public void actualizarPayloadElement(String psElementName, Object poValue) {
      AttributeBinding attributeBinding = null;
    
      attributeBinding = ADFUtils.findControlBinding(psElementName);
      
      if(attributeBinding!=null)
          attributeBinding.setInputValue(poValue);
      
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
    
    //Invokes por tarea
    public String invokeFinalizarTarea()
    {
        
        String bundleKeyError = "";
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean  messageAdded = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();

        switch (codigoTarea)
        {
            case FenixConstants.PA17_EMITIR_OPINION_LECCION_APRENDIDA:
                LOGGER.warning("PA17_EMITIR_OPINION_LECCION_APRENDIDA.");
                
                isValidFinalizarTarea = true;
            
            break;
            case FenixConstants.PA17_DE_ACUERDO_RESULTADOS_LECCION_APRENDIDA:
                LOGGER.warning("PA17_DE_ACUERDO_RESULTADOS_LECCION_APRENDIDA.");
                
                isValidFinalizarTarea = true;
            
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
    
    public String invokeLeccionCancelada()
    {
        int codigoTarea = getCodigoTarea();
        
        Boolean isValidCancelar = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.PA17_EMITIR_OPINION_LECCION_APRENDIDA:
                LOGGER.warning("PA17_EMITIR_OPINION_LECCION_APRENDIDA");
                isValidCancelar =  true;
            break;
            default:
                LOGGER.log(ADFLogger.ERROR, "invokeLeccionCancelada(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
                
        if (!isValidCancelar)
        {
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_AGREGAR_COMENTARIO", BUNDLE));
        } else
        {
            cancelarPopup();
        }
        
        return null;
    }
    
    public String invokeValidar()
    {
        int codigoTarea = getCodigoTarea();
        Boolean isValidValidar = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.PA17_VALIDAR_LECCION_APRENDIDA:
                LOGGER.warning("PA17_VALIDAR_LECCION_APRENDIDA");
                isValidValidar =  true;
            break;
            case FenixConstants.PA17_REVISAR_APELACION_LECCION_APRENDIDA:
                LOGGER.warning("PA17_REVISAR_APELACION_LECCION_APRENDIDA");
                isValidValidar =  true;
            break;
        
            default:
                LOGGER.log(ADFLogger.ERROR, "invokeValidar(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
                
        if (!isValidValidar)
        {
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_AGREGAR_COMENTARIO", BUNDLE));
        } else
        {
            validarPopup();
        }
        
        return null;
    }
    
    public String invokeRechazar()
    {
        int codigoTarea = getCodigoTarea();
        
        Boolean isValidRechazar = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.PA17_VALIDAR_LECCION_APRENDIDA:
                LOGGER.warning("PA17_VALIDAR_LECCION_APRENDIDA");
                isValidRechazar =  true;
            break;
            case FenixConstants.PA17_REVISAR_APELACION_LECCION_APRENDIDA:
                LOGGER.warning("PA17_REVISAR_APELACION_LECCION_APRENDIDA");
                isValidRechazar =  true;
            break;
        
            default:
                LOGGER.log(ADFLogger.ERROR, "invokeRechazar(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
                
        if (!isValidRechazar)
        {
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_AGREGAR_COMENTARIO", BUNDLE));
        } else
        {
            rechazarPopup();
        }
        
        return null;
    }
    
    public String invokeApelar()
    {
        int codigoTarea = getCodigoTarea();
        
        Boolean isValidApelar = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.PA17_DE_ACUERDO_RESULTADOS_LECCION_APRENDIDA:
                LOGGER.warning("PA17_DE_ACUERDO_RESULTADOS_LECCION_APRENDIDA");
                isValidApelar =  true;
            break;
            default:
                LOGGER.log(ADFLogger.ERROR, "invokeApelar(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
                
        if (!isValidApelar)
        {
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_ERROR_AGREGAR_COMENTARIO", BUNDLE));
        } else
        {
            apelarPopup();
        }
        
        return null;
    }
    
    public String aceptarFinalizarTarea()
    {
      popupFinalizarTarea.hide();
      
      Boolean esValidoFinalizar = Boolean.FALSE;
          
      int codigoTarea = getCodigoTarea();
      
      switch (codigoTarea)
      {
          case FenixConstants.PA17_EMITIR_OPINION_LECCION_APRENDIDA:
              LOGGER.warning("PA17_EMITIR_OPINION_LECCION_APRENDIDA.");
              
              esValidoFinalizar = true;
          
          break;
          case FenixConstants.PA17_DE_ACUERDO_RESULTADOS_LECCION_APRENDIDA:
              LOGGER.warning("PA17_DE_ACUERDO_RESULTADOS_LECCION_APRENDIDA.");
              
              esValidoFinalizar = true;
          
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
    
    public String aceptarCancelar()
    {
        popupCancelar.hide();
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
      
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public String aceptarValidar()
    {
        popupValidar.hide();
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
      
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    
    public String aceptarRechazar()
    {
        popupRechazar.hide();
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
      
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public String aceptarApelar()
    {
        popupApelar.hide();
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
      
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
}
