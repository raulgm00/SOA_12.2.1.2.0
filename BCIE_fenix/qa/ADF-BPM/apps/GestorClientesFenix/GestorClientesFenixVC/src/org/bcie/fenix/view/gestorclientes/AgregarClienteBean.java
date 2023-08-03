package org.bcie.fenix.view.gestorclientes;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class AgregarClienteBean implements Serializable{
    @SuppressWarnings("compatibility:7280451396606725148")
    private static final long serialVersionUID = 1L;
    public static final String BUNDLE ="org.bcie.fenix.view.GestorClientesFenixVCBundle";
    public static final String INFO_REQUERIDA ="MENSAJE_REQUERIDOS";
    public static final String ABREVIATURA_REPETIDA ="MSG_ABREVIATURA_REPETIDA";
  
    
    private static ADFLogger logger = null;
    private String mensajeConfirmacion = null;
    private transient RichPopup popupConfirmarHeaderAction;
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(AgregarClienteBean.class);
    
    private transient RichSelectOneChoice tipoInstitucionSOC;
    private transient RichInputText razonSocialIT;
    private transient RichInputText bicCodeIT;
    private transient RichInputText direccionIT;
    private transient RichSelectOneChoice tipoPersonaSOC;
    private transient RichSelectOneChoice grupoEconomicoSOC;
    private transient RichSelectOneChoice oficinaSOC;
    private transient RichInputText abreviaturaIT;
    private transient RichSelectOneChoice sectorSOC;
    private transient RichSelectOneChoice paisSOC;
    private RichPopup popupClientesRepetidos;
    private RichInputText numeroIdentificacionUIC;

    public AgregarClienteBean(){
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }   
    }
    
    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
    }

    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public void setPopupConfirmarHeaderAction(RichPopup popupConfirmarHeaderAction) {
        this.popupConfirmarHeaderAction = popupConfirmarHeaderAction;
    }

    public RichPopup getPopupConfirmarHeaderAction() {
        return popupConfirmarHeaderAction;
    }

    /**
     * @autor Damacio Cordoba Medina
     * @since 24/12/2015
     *  Método utilizado para mandar llamar el popup de confirmacion
     * @return
     */
    public String agregarCliente() {
        if(validarAgregar())
        {
          setMensajeConfirmacion("¿Confirma agregar los datos del cliente?");
          showPopupConfirmarHeaderAction();
        }
        return null;
    }
    
    public String regresarCliente(){
        setMensajeConfirmacion("Perderá los cambios realizados. ¿Desea continuar?");
        showPopupConfirmarHeaderAction();
    return null;                    
    }
    
    public String aceptarAgregarCliente() {
      return aceptarAgregarClienteValidacion(Boolean.TRUE);
    }
    
    public String aceptarAgregarClienteCoincidencias() {
      return aceptarAgregarClienteValidacion(Boolean.FALSE);
    }
    
    public String aceptarAgregarClienteValidacion(Boolean esValidacionRequerida) {
        // Add event code here...
        
        String accion = (String) JSFUtils.resolveExpression("#{viewScope.accion}");
        
        if ((accion != null) && (accion.trim().length() > 0)) {

            if (accion.equalsIgnoreCase("agregar")){
                logger.log(ADFLogger.TRACE, "dentro de aceptarAgregarCliente");
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = null;
                operationBinding = bindings.getOperationBinding("crearCliente");
                operationBinding.getParamsMap().put("isValidar", esValidacionRequerida);
                
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    
                } else {
                    if (null!=operationBinding.getResult() && operationBinding.getResult().toString().equalsIgnoreCase("OK") ){
                        JSFUtils.addFacesInformationMessage("Se ha agregado el cliente exitosamente.");                           
                        return "irGestorClientes";
                    }else if (null!=operationBinding.getResult() && operationBinding.getResult().toString().equalsIgnoreCase("COINCIDENCIAS") ){
                        //REVM
                        showPopupClientesRepetidos();
                    }else if (null!=operationBinding.getResult() && operationBinding.getResult().toString().equalsIgnoreCase("REPETIDO") ){
                      JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(ABREVIATURA_REPETIDA, BUNDLE));
                    }
                    
                }        
            }
            if (accion.equalsIgnoreCase("cancelar")){
                return "irGestorClientes";
            }
            
        }
        
        getPopupConfirmarHeaderAction().hide();
        return null;
    }
    
    public String cancelarAgregarCliente() {
        // Add event code here...
        logger.log(ADFLogger.TRACE, "Dentro de cancelarAgregarCliente");
        getPopupConfirmarHeaderAction().hide();
        return null;
    }

    private void showPopupConfirmarHeaderAction() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarHeaderAction().show(popupHints);
    }
    
    private void showPopupClientesRepetidos() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupClientesRepetidos().show(popupHints);
    }

  public Boolean validarAgregar()
  {    
    Boolean registroValido = Boolean.TRUE;
    
    if(null == tipoInstitucionSOC.getValue() || !(tipoInstitucionSOC.getValue().toString().length()>0))
    {
      JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),tipoInstitucionSOC);    
      registroValido = Boolean.FALSE;
    }
    
    if(null == razonSocialIT.getValue() || !(razonSocialIT.getValue().toString().length()>0))
    {
      JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),razonSocialIT);   
      registroValido = Boolean.FALSE;
    }
    /*
    if(null == bicCodeIT.getValue() || !(bicCodeIT.getValue().toString().length()>0))
    {
        JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),bicCodeIT);   
        registroValido = Boolean.FALSE;
    }*/
    
    if(null == direccionIT.getValue() || !(direccionIT.getValue().toString().length()>0))
    {
        JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),direccionIT);   
        registroValido = Boolean.FALSE;
    }
    
    if(null == tipoPersonaSOC.getValue() || !(tipoPersonaSOC.getValue().toString().length()>0))
    {
      JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),tipoPersonaSOC);      
      registroValido = Boolean.FALSE;
    }
    
    if(null == grupoEconomicoSOC.getValue() || !(grupoEconomicoSOC.getValue().toString().length()>0))
    {
      JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),grupoEconomicoSOC);      
      registroValido = Boolean.FALSE;
    }
    
    if(null == oficinaSOC.getValue() || !(oficinaSOC.getValue().toString().length()>0))
    {
      JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),oficinaSOC);      
      registroValido = Boolean.FALSE;
    }
    
    if(null == abreviaturaIT.getValue() || !(abreviaturaIT.getValue().toString().length()>0))
    {
      JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),abreviaturaIT);      
      registroValido = Boolean.FALSE;
    }
    
    if(null == sectorSOC.getValue() || !(sectorSOC.getValue().toString().length()>0))
    {
      JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),sectorSOC);      
      registroValido = Boolean.FALSE;
    }
    
    if(null == paisSOC.getValue() || !(paisSOC.getValue().toString().length()>0))
    {
      JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),paisSOC);      
      registroValido = Boolean.FALSE;
    }
    
    if(null == numeroIdentificacionUIC.getValue() || !(numeroIdentificacionUIC.getValue().toString().length()>0))
    {
        JSFUtils.addUIXFacesErrorMessage(ADFUtils.getStringFromBundle(INFO_REQUERIDA, BUNDLE),numeroIdentificacionUIC);      
        registroValido = Boolean.FALSE;
    }
    
    return registroValido;
  }

  public void setTipoInstitucionSOC(RichSelectOneChoice tipoInstitucionSOC)
  {
    this.tipoInstitucionSOC = tipoInstitucionSOC;
  }

  public RichSelectOneChoice getTipoInstitucionSOC()
  {
    return tipoInstitucionSOC;
  }

  public void setRazonSocialIT(RichInputText razonSocialIT)
  {
    this.razonSocialIT = razonSocialIT;
  }

  public RichInputText getRazonSocialIT()
  {
    return razonSocialIT;
  }

  public void setTipoPersonaSOC(RichSelectOneChoice tipoPersonaSOC)
  {
    this.tipoPersonaSOC = tipoPersonaSOC;
  }

  public RichSelectOneChoice getTipoPersonaSOC()
  {
    return tipoPersonaSOC;
  }

  public void setGrupoEconomicoSOC(RichSelectOneChoice grupoEconomicoSOC)
  {
    this.grupoEconomicoSOC = grupoEconomicoSOC;
  }

  public RichSelectOneChoice getGrupoEconomicoSOC()
  {
    return grupoEconomicoSOC;
  }

  public void setOficinaSOC(RichSelectOneChoice oficinaSOC)
  {
    this.oficinaSOC = oficinaSOC;
  }

  public RichSelectOneChoice getOficinaSOC()
  {
    return oficinaSOC;
  }

  public void setAbreviaturaIT(RichInputText abreviaturaIT)
  {
    this.abreviaturaIT = abreviaturaIT;
  }

  public RichInputText getAbreviaturaIT()
  {
    return abreviaturaIT;
  }
  
  public void setBicCodeIT(RichInputText bicCodeIT) {
      this.bicCodeIT = bicCodeIT;
  }
  
  public RichInputText getBicCodeIT() {
      return bicCodeIT; 
  }

    public void setDireccionIT(RichInputText direccionIT) {
        this.direccionIT = direccionIT;
    }
    
    public RichInputText getDireccionIT() {
        return direccionIT; 
    }  

  public void setSectorSOC(RichSelectOneChoice sectorSOC)
  {
    this.sectorSOC = sectorSOC;
  }

  public RichSelectOneChoice getSectorSOC()
  {
    return sectorSOC;
  }

  public void setPaisSOC(RichSelectOneChoice paisSOC)
  {
    this.paisSOC = paisSOC;
  }

  public RichSelectOneChoice getPaisSOC()
  {
    return paisSOC;
  }

    public void setPopupClientesRepetidos(RichPopup popupClientesRepetidos) {
        this.popupClientesRepetidos = popupClientesRepetidos;
    }

    public RichPopup getPopupClientesRepetidos() {
        return popupClientesRepetidos;
    }

    public void sectorValueChangeListener(ValueChangeEvent valueChangeEvent) {
        this.tipoInstitucionSOC.resetValue();
    }

  public void cancelarCrearClienteCoincidencias(ActionEvent actionEvent)
  {
    getPopupClientesRepetidos().hide();
  }

    public void setNumeroIdentificacionUIC(RichInputText numeroIdentificacionUIC) {
        this.numeroIdentificacionUIC = numeroIdentificacionUIC;
    }

    public RichInputText getNumeroIdentificacionUIC() {
        return numeroIdentificacionUIC;
    }
}
