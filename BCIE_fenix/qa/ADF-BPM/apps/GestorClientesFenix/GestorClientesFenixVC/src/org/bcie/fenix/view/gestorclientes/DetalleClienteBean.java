package org.bcie.fenix.view.gestorclientes;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;

import org.apache.myfaces.trinidad.util.Service;

import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import javax.faces.context.FacesContext;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.application.ViewHandler;

import javax.faces.component.UIViewRoot;

import oracle.binding.AttributeBinding;

import oracle.jbo.domain.Date;

public class DetalleClienteBean {
    private static ADFLogger logger = null;
    private static final String MENSAJE_VALIDACION_DIA_RANGO = "El día se encuentra fuera del rango de 1 a 31.";
    private static final String TITULO_VALIDACION = "Validación";
    private RichPopup popupConfirmarHeaderAction;
    private String mensajeConfirmacion = null;
    private String tituloPopupContactos = null;
    private RichPopup popupContactos;
    private RichPanelGroupLayout pg1;
    private RichPanelFormLayout pfl1;
    private RichButton bdefinitivo;
    private RichButton bmodificar;
    private RichButton beliminar;
    private RichButton bguardar;
    private RichButton bcancelar;
    private RichButton bmodificarC;
    private RichButton beliminarC;
    private RichPopup popupAgregarContacto;
    private RichInputText itNombreContacto;
    private RichInputText itTelefonoContacto;
    private RichInputText itCorreoContacto;
    private RichInputText itCargoContacto;
    private RichInputText itDiaPago;
    private RichPanelFormLayout pfl2;
    private boolean disableDetalleCliente;
    private boolean disableOperaciones;
    private String urlGestorOperaciones;

    // Variables Permisos Gestor
    private Boolean esResponsableOperacion=false;
    private Boolean esAnalistaUCE=false;
    private Boolean esAnalistaSeguimientoCrediticio=false;
    private Boolean esCoordinadorSeguimientoCrediticio=false;
    private Boolean esAnalistaCOFI=false;
    private Boolean habilitarEnvioManualAvisoCobro=false;
    private Boolean esResponsableCliente = Boolean.FALSE;

    // Variables de Cliente
    private Long idCliente;
    private Long idScr;
    private RichTable uiContactosTable;

    private boolean contactoSeleccionado = Boolean.FALSE;
    private boolean operacionRelSeleccionada = Boolean.FALSE;
    private boolean activeRegionDocumentos = Boolean.FALSE;
    
    
    private String justificacionSuspenso;
    public String getJustificacionSuspenso(){
        return justificacionSuspenso;
    }
    
    public void setJustificacionSuspenso(String justificacionSuspenso){
        this.justificacionSuspenso = justificacionSuspenso;
    }
    

    public void setActiveRegionDocumentos(boolean activeRegionDocumentos) {
        this.activeRegionDocumentos = activeRegionDocumentos;
    }

    public boolean isActiveRegionDocumentos() {
        return activeRegionDocumentos;
    }

    private Long idContactoSeleccionado = Long.valueOf(0);


    public void setIdContactoSeleccionado(Long idContactoSeleccionado) {
        this.idContactoSeleccionado = idContactoSeleccionado;
    }

    public Long getIdContactoSeleccionado() {
        return idContactoSeleccionado;
    }

    public void setEsAnalistaCOFI(Boolean esAnalistaCOFI) {
        this.esAnalistaCOFI = esAnalistaCOFI;
    }

    public Boolean getEsAnalistaCOFI() {
        return esAnalistaCOFI;
    }
    
    public void setEsAnalistaSeguimientoCrediticio(Boolean esAnalistaSeguimientoCrediticio) {
        this.esAnalistaSeguimientoCrediticio = esAnalistaSeguimientoCrediticio;
    }

    public Boolean getEsAnalistaSeguimientoCrediticio() {
        return esAnalistaSeguimientoCrediticio;
    }

    public void setEsCoordinadorSeguimientoCrediticio(Boolean esCoordinadorSeguimientoCrediticio) {
        this.esCoordinadorSeguimientoCrediticio = esCoordinadorSeguimientoCrediticio;
    }

    public Boolean getEsCoordinadorSeguimientoCrediticio() {
        return esCoordinadorSeguimientoCrediticio;
    }
    
    public void setEsAnalistaUCE(Boolean esAnalistaUCE) {
        this.esAnalistaUCE = esAnalistaUCE;
    }

    public Boolean getEsAnalistaUCE() {
        return esAnalistaUCE;
    }

    public void setEsResponsableOperacion(Boolean esResponsableOperacion) {
        this.esResponsableOperacion = esResponsableOperacion;
    }

    public Boolean getEsResponsableOperacion() {
        return esResponsableOperacion;
    }

    public void setDisableDetalleCliente(boolean disableDetalleCliente) {
        this.disableDetalleCliente = disableDetalleCliente;
    }

    public boolean isDisableDetalleCliente() {
        return disableDetalleCliente;
    }

    public void setDisableOperaciones(boolean disableOperaciones) {
        this.disableOperaciones = disableOperaciones;
    }

    public boolean isDisableOperaciones() {
        return disableOperaciones;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdScr(Long idScr) {
        this.idScr = idScr;
    }

    public Long getIdScr() {
        return idScr;
    }
    
    @SuppressWarnings("unchecked")
    public String inicializarPermisosUsuario(){
        logger.warning("inside: inicializarPermisosUsuario");
        
        Object resultado = null;
        setDisableDetalleCliente(true);
        setDisableOperaciones(true);
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("getGruposUsuario");
        String usuario = ADFContext.getCurrent().getSecurityContext().getUserName();
        operationBinding.getParamsMap().put("pUsuario", usuario);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            
        } else {
            resultado = operationBinding.getResult();                
        }
        
        if (resultado!=null){
            List<String>grupo = (List<String>)resultado;
            for(String gs : grupo){
                logger.warning("grupo del servicio:"+gs);
                if (gs.startsWith("FENIX")){
                    setDisableDetalleCliente(false);
                }
                if (gs.startsWith("FENIX_REPRESENTANTE")){
                    setHabilitarEnvioManualAvisoCobro(Boolean.TRUE);
                }
                if (gs.startsWith("FENIX_REPRESENTANTE")||gs.startsWith("FENIX_GERENTE")){
                    setDisableOperaciones(false);
                    if (gs.startsWith("FENIX_REPRESENTANTE")){
                        setEsResponsableOperacion(Boolean.TRUE);
                    }
                }
                if (gs.startsWith("FENIX_ANALISTA_UCE")){
                    setEsAnalistaUCE(Boolean.TRUE);
                }
                if (gs.startsWith("FENIX_ANALISTA_USC")){
                    setEsAnalistaSeguimientoCrediticio(Boolean.TRUE);
                }
                if (gs.startsWith("FENIX_COORDINADOR_USC")){
                    setEsCoordinadorSeguimientoCrediticio(Boolean.TRUE);
                }
                if (gs.startsWith("FENIX_ANALISTA_COFI")){
                    setEsAnalistaCOFI(Boolean.TRUE);
                }
            }
        }
    return "irGestorClientes";
    }

    public void setTituloPopupContactos(String tituloPopupContactos) {
        this.tituloPopupContactos = tituloPopupContactos;
    }

    public String getTituloPopupContactos() {
        return tituloPopupContactos;
    }
    private boolean rendered = true;
    private boolean renderedBotones = false;

    public void setRenderedBotones(boolean renderedBotones) {
        this.renderedBotones = renderedBotones;
    }

    public boolean isRenderedBotones() {
        return renderedBotones;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
    }

    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public boolean isRendered(){
        return rendered;
    }

    public DetalleClienteBean() {        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
  public void addPartialTarget(){
          AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
          adfFacesContext.addPartialTarget(getPg1());
          adfFacesContext.addPartialTarget(getPfl1());
          adfFacesContext.addPartialTarget(getPfl2());
          adfFacesContext.addPartialTarget(getBdefinitivo());
          adfFacesContext.addPartialTarget(getBmodificar());
          adfFacesContext.addPartialTarget(getBeliminar());
          adfFacesContext.addPartialTarget(getBguardar());
          adfFacesContext.addPartialTarget(getBcancelar());
          adfFacesContext.addPartialTarget(getBmodificarC());
          adfFacesContext.addPartialTarget(getBeliminarC());            
      }

    public String modificarCliente() {
        // Add event code here...
        logger.log(ADFLogger.TRACE, "modificarCliente");
        setRendered(false);
        setRenderedBotones(true);
        //addPartialTarget();
        return null;
    }

    public void setPopupConfirmarHeaderAction(RichPopup popupConfirmarHeaderAction) {
        this.popupConfirmarHeaderAction = popupConfirmarHeaderAction;
    }

    public RichPopup getPopupConfirmarHeaderAction() {
        return popupConfirmarHeaderAction;
    }

    @SuppressWarnings({ "unchecked", "oracle.jdeveloper.java.boolean-constructor" })
    public String aceptarAccion() {
        logger.warning("Inicia metodo aceptarAccion");
      
        DatosClienteBean datosCliente  = (DatosClienteBean)JSFUtils.resolveExpression("#{pageFlowScope.DatosClienteBean}");
        DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator("CrearClienteVOIterator");
        ViewObject voData = dcItteratorBindings.getViewObject(); 
        
        Row currentRow = voData.getCurrentRow();
        
        logger.warning("ROWS: " + voData.getEstimatedRowCount());
        logger.warning("IdCliente :" + currentRow.getAttribute("IdCliente"));
        logger.warning("IdFlexcube :" + currentRow.getAttribute("IdFlexcube"));
        logger.warning("RazonSocial :" + currentRow.getAttribute("RazonSocial"));
        logger.warning("Abreviatura :" + currentRow.getAttribute("Abreviatura"));
        logger.warning("TipoPersona :" + currentRow.getAttribute("TipoPersona"));
        logger.warning("TipoCliente :" + currentRow.getAttribute("TipoCliente"));
        logger.warning("Sector :" + currentRow.getAttribute("Sector"));
        logger.warning("TipoInstitucion :" + currentRow.getAttribute("TipoInstitucion"));
        logger.warning("Pais :" + currentRow.getAttribute("Pais"));
        logger.warning("GrupoEconomico :" + currentRow.getAttribute("GrupoEconomico"));
        logger.warning("TipoIdentificacion :" + currentRow.getAttribute("TipoIdentificacion"));
        logger.warning("NumeroIdentificacion :" + currentRow.getAttribute("NumeroIdentificacion"));
        logger.warning("Oficina :" + currentRow.getAttribute("Oficina"));
        logger.warning("FechaRegistro :" + currentRow.getAttribute("FechaRegistro"));
        logger.warning("FechaAprobacion :" + currentRow.getAttribute("FechaAprobacion"));
        logger.warning("Ejecutivo :" + currentRow.getAttribute("Ejecutivo"));
        logger.warning("ComentarioAprobacion :" + currentRow.getAttribute("ComentarioAprobacion"));
        logger.warning("Autorizo :" + currentRow.getAttribute("Autorizo"));
        logger.warning("BanEstatus :" + currentRow.getAttribute("BanEstatus"));
        logger.warning("FechaBaja :" + currentRow.getAttribute("FechaBaja"));
        logger.warning("DiaPago :" + currentRow.getAttribute("DiaPago"));
        logger.warning("Id :" + currentRow.getAttribute("Id"));
        logger.warning("Autorizo :" + currentRow.getAttribute("Autorizo"));
        logger.warning("BanEstatus :" + currentRow.getAttribute("BanEstatus"));
        logger.warning("FechaBaja :" + currentRow.getAttribute("FechaBaja"));
        logger.warning("DiaPago :" + currentRow.getAttribute("DiaPago"));
        logger.warning("Id :" + currentRow.getAttribute("Id"));
        logger.warning("ScrId :" + currentRow.getAttribute("ScrId"));
        logger.warning("EnMora :" + currentRow.getAttribute("EnMora"));
        logger.warning("ReqEnvioAvisoCobroAut :" + currentRow.getAttribute("ReqEnvioAvisoCobroAut"));
        logger.warning("EsDeteriorado :" + currentRow.getAttribute("EsDeteriorado"));
        logger.warning("idPerspectiva :" + currentRow.getAttribute("PerspectivaId"));
        logger.warning("Bic Code :" + currentRow.getAttribute("BicCode"));
        logger.warning("Direccion :" + currentRow.getAttribute("Direccion"));
        
        if (currentRow.getAttribute("PerspectivaId") == null) {
            currentRow.setAttribute("PerspectivaId", datosCliente.getPerspectivaId());
        }
        
        String accion = (String) JSFUtils.resolveExpression("#{viewScope.accion}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        if ((accion != null) && (accion.trim().length() > 0)) {            
            switch (accion) {
            case "modificar":
                logger.warning("modificar");
            break;
            case "convertir":
                logger.warning("convertir");
                
                operationBinding = bindings.getOperationBinding("inicioAdministrarCliente");
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    
                } else {
                    JSFUtils.addFacesInformationMessage("La solicitud de conversión ha sido enviada.");            
                    getPopupConfirmarHeaderAction().hide();
                }        
            break;
            case "eliminar":
                operationBinding = bindings.getOperationBinding("eliminarCliente");
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                   
                } else {
                    JSFUtils.addFacesInformationMessage("Se ha eliminado el cliente exitósamente.");            
                    ADFUtils.findIterator("BuscarClienteVOIterator").getViewObject().executeQuery();
                    getPopupConfirmarHeaderAction().hide();
                    return "irGestorClientes";                    
                }        
                
                logger.warning("eliminar");
            break;
            case "guardar":
                String tipoCliente = JSFUtils.resolveExpression("#{bindings.TipoCliente.inputValue}").toString();
                String operacion = tipoCliente.equalsIgnoreCase("1")?"actualizarCliente":"inicioAdministrarCliente";
                logger.warning("guardar");
                operationBinding = bindings.getOperationBinding(operacion);
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    
                } else {
                    JSFUtils.addFacesInformationMessage(tipoCliente.equalsIgnoreCase("1")?"Se ha actualizado el cliente exitósamente.":"La actualización ha sido enviada a aprobación.");
                    setRendered(true);
                    setRenderedBotones(false);
                    //addPartialTarget();
                    //Se actualiza el query de buscar clientes VO                    
                    ADFUtils.findIterator("BuscarClienteVOIterator").getViewObject().executeQuery();
                    getPopupConfirmarHeaderAction().hide();
                    return null;
                }    
            break;
            case "cancelar":
                logger.warning("cancelar");
                operationBinding = bindings.getOperationBinding("refrescarDatosCliente");
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    
                } else {
                    setRendered(true);
                    setRenderedBotones(false);                    
                    //addPartialTarget();                
                    getPopupConfirmarHeaderAction().hide();
                    return null;
                }  
            break;
            case "eliminarContacto":
                    logger.warning("Dentro de eliminarContacto");
                    operationBinding = bindings.getOperationBinding("eliminarContacto");
                    operationBinding.execute();
                    if (!operationBinding.getErrors().isEmpty()) {
                        
                    } else {
                        JSFUtils.addFacesInformationMessage("Se eliminó el contacto exitósamente.");
                        getPopupContactos().hide();
                        refreshTable();
                    }                
                break;
            default:                
            }//Fin switch accion
        }//Fin If Evalua accion != null
    
    getPopupConfirmarHeaderAction().hide();
    return null;
    }

    public String cancelarAccion() {
        
        logger.log(ADFLogger.TRACE, "Dentro de cancelarAccion");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;    
        operationBinding = bindings.getOperationBinding("inicioAdministrarCliente");
        if(operationBinding != null){
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                logger.severe("Error en la inicializacion del detalle de cliente. " + 
                              operationBinding.getErrors().toString());    
            }
        }
        
        getPopupConfirmarHeaderAction().hide();
        return null;
    }
    
    private void showPopupConfirmarHeaderAction() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarHeaderAction().show(popupHints);
    }

    public String convertirDefinitivo() {
        // Add event code here...
        logger.log(ADFLogger.TRACE, "Dentro de convertirDefinitivo");
        setMensajeConfirmacion("¿Confirma enviar la solicitud al evaluador COFI?");
        showPopupConfirmarHeaderAction();
        return null;
    }

    public String eliminarCliente() {
        // Add event code here...
        setMensajeConfirmacion("¿Está seguro de que desea eliminar a este cliente?");
        showPopupConfirmarHeaderAction();
        return null;
    }

    public String guardarModificacion() {
        // Add event code here...
        boolean valida = true;
        if (getItDiaPago().getValue() != null) {
            String newValue = getItDiaPago().getValue().toString();
            logger.log(ADFLogger.WARNING, "newValue : " + newValue);
            valida = this.validarDiaPago(getItDiaPago().getClientId(), newValue);
        }
        logger.log(ADFLogger.WARNING, "valida : " + valida);
        if (valida) {
            setMensajeConfirmacion("¿La información actualizada del cliente es correcta?");
            showPopupConfirmarHeaderAction();
        }
        return null;
    }

    public String cancelarModificacion() {
        // Add event code here...
        setMensajeConfirmacion("Perderá todos los cambios realizados. ¿Desea continuar?");
        showPopupConfirmarHeaderAction();
        return null;
    }

    public void setPopupContactos(RichPopup popupContactos) {
        this.popupContactos = popupContactos;
    }

    public RichPopup getPopupContactos() {
        return popupContactos;
    }
    
    private void showPopupContactos() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupContactos().show(popupHints);
    }
    
    private void showPopupAgregarContacto(){
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupAgregarContacto().show(popupHints);
    }
    

    public String aceptarContacto() {
        // Add event code here...
        logger.log(ADFLogger.TRACE, "Dentro de aceptarContacto");
        logger.log(ADFLogger.WARNING, "Dentro de aceptarContacto");
        String accion = (String) JSFUtils.resolveExpression("#{viewScope.accion}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;    
        if ((accion != null) && (accion.trim().length() > 0)) {            
            switch (accion) {
            case "agregarContacto":
                operationBinding = bindings.getOperationBinding("agregarContacto");
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    
                } else {
                    JSFUtils.addFacesInformationMessage("Se ha agregado el contacto exitósamente.");
                    getItCargoContacto().resetValue();
                    getItCorreoContacto().resetValue();
                    getItNombreContacto().resetValue();
                    getItTelefonoContacto().resetValue();
                    getPopupAgregarContacto().hide();
                    refreshTable();
                }
            break;
            case "modificarContacto":
                operationBinding = bindings.getOperationBinding("actualizarContacto");
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    
                } else {
                    JSFUtils.addFacesInformationMessage("Se ha modificado el contacto exitósamente.");
                    getPopupContactos().hide();
                    refreshTable();
                }                
            break;            
            default:                
            }//Fin switch accion    
            //addPartialTarget();
        }
        logger.log(ADFLogger.WARNING, "Finaliza aceptarContacto");
    return null;
    }

    public String cancelarContacto() {
        // Add event code here...
        logger.log(ADFLogger.TRACE, "Dentro de cancelarContacto");
        String accion = (String) JSFUtils.resolveExpression("#{viewScope.accion}");
        if (accion.equalsIgnoreCase("agregarContacto")){
            getPopupAgregarContacto().hide();
        }else{
            getPopupContactos().hide();
        }
        
        //addPartialTarget();
        return null;
    }

    public String agregarContacto() {
        // Add event code here...
        setTituloPopupContactos("Crear Contacto");
        showPopupAgregarContacto();
        return null;
    }

    public String modificarContacto() {
        // Add event code here...
        logger.log(ADFLogger.TRACE, "Dentro de modificarContacto");
        setTituloPopupContactos("Modificar Contacto");
        showPopupContactos();
        return null;
    }

    public String eliminarContacto() {
        // Add event code here...
        logger.log(ADFLogger.TRACE, "Dentro de eliminarContacto");
        setMensajeConfirmacion("¿Confirma eliminar los datos del contacto?");
        showPopupConfirmarHeaderAction();
        return null;
    }
    
    public void verDetalleOperacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside de verDetalleOperacionActionListener: " + actionEvent.getComponent().getId());
        abrirDetalleOperacion();
    }
    
    private void abrirDetalleOperacion() {
        logger.log(ADFLogger.TRACE, "Inside abrirDetalleOperacion.");
        StringBuilder script = new StringBuilder();
        String idOperacion = null;
        
        // Asignación de variables
        idOperacion = JSFUtils.resolveExpression("#{bindings.IdOperacion.inputValue}") == null ? "" : 
            JSFUtils.resolveExpression("#{bindings.IdOperacion.inputValue}").toString();
        
        // Abrimos popup con aplicación del Gestor de Operaciones
        ExtendedRenderKitService erks = Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                    ExtendedRenderKitService.class);
        script.append("window.open(\"").append(this.getUrlGestorOperaciones());
        script.append("?pIdOperacion=").append(idOperacion);
        script.append("\", \"DetalleOperacion\"");
        script.append(",\"width=1024px,height=1024px,resizable=1,location=0,scrollbars=1,fullscreen=1\")");
        erks.addScript(FacesContext.getCurrentInstance(), script.toString());
    }

    public void setPg1(RichPanelGroupLayout pg1) {
        this.pg1 = pg1;
    }

    public RichPanelGroupLayout getPg1() {
        return pg1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setBdefinitivo(RichButton bdefinitivo) {
        this.bdefinitivo = bdefinitivo;
    }

    public RichButton getBdefinitivo() {
        return bdefinitivo;
    }

    public void setBmodificar(RichButton bmodificar) {
        this.bmodificar = bmodificar;
    }

    public RichButton getBmodificar() {
        return bmodificar;
    }

    public void setBeliminar(RichButton beliminar) {
        this.beliminar = beliminar;
    }

    public RichButton getBeliminar() {
        return beliminar;
    }

    public void setBguardar(RichButton bguardar) {
        this.bguardar = bguardar;
    }

    public RichButton getBguardar() {
        return bguardar;
    }

    public void setBcancelar(RichButton bcancelar) {
        this.bcancelar = bcancelar;
    }

    public RichButton getBcancelar() {
        return bcancelar;
    }

    public void setBmodificarC(RichButton bmodificarC) {
        this.bmodificarC = bmodificarC;
    }

    public RichButton getBmodificarC() {
        return bmodificarC;
    }

    public void setBeliminarC(RichButton beliminarC) {
        this.beliminarC = beliminarC;
    }

    public RichButton getBeliminarC() {
        return beliminarC;
    }

    public void setPopupAgregarContacto(RichPopup popupAgregarContacto) {
        this.popupAgregarContacto = popupAgregarContacto;
    }

    public RichPopup getPopupAgregarContacto() {
        return popupAgregarContacto;
    }

    public void setItNombreContacto(RichInputText itNombreContacto) {
        this.itNombreContacto = itNombreContacto;
    }

    public RichInputText getItNombreContacto() {
        return itNombreContacto;
    }

    public void setItTelefonoContacto(RichInputText itTelefonoContacto) {
        this.itTelefonoContacto = itTelefonoContacto;
    }

    public RichInputText getItTelefonoContacto() {
        return itTelefonoContacto;
    }

    public void setItCorreoContacto(RichInputText itCorreoContacto) {
        this.itCorreoContacto = itCorreoContacto;
    }

    public RichInputText getItCorreoContacto() {
        return itCorreoContacto;
    }

    public void setItCargoContacto(RichInputText itCargoContacto) {
        this.itCargoContacto = itCargoContacto;
    }

    public RichInputText getItCargoContacto() {
        return itCargoContacto;
    }
    
    public void setItDiaPago(RichInputText itDiaPago) {
        this.itDiaPago = itDiaPago;
    }
    
    public RichInputText getItDiaPago() {
        return this.itDiaPago;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }
    
    public String getUrlGestorOperaciones() {
        DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_GESTOR_OPERACIONES"}));
        String url = row == null ? null : (String) row.getAttribute("Valor");
        return url;
    }

    public void validar(ValueChangeEvent valueChangeEvent) {
        
        if (valueChangeEvent.getNewValue() != null) {
            String newValue = valueChangeEvent.getNewValue().toString();
            
            this.validarDiaPago(valueChangeEvent.getComponent().getClientId(), newValue);
        }
    }
    
    private boolean validarDiaPago(String component, String value) {
        boolean valido = false;
        int result = this.validarRangoDia(value);
        
        switch (result) {
        case 1: // NULL
            valido = false;
            break;
        case 2: // Vacía
            valido = false;
            break;
        case 3: // Fuera de rango
            this.showMessageValidation(component, MENSAJE_VALIDACION_DIA_RANGO);
            valido = false;
            break;
        case 4: // Dentro de rango
            valido = true;
            break;
        case 5: // Exception
            valido = false;
            break;
        }
        return valido;
    }
    
    private int validarRangoDia(String value) {
        try {
            if (value != null && !value.isEmpty()) {
                Integer newValueInt = Integer.parseInt(value);
                
                if (newValueInt.compareTo(1) >= 0 && newValueInt.compareTo(31) <=0) {
                    return 4;
                } else {
                    return 3;
                }
            } else {
                return 1;
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, e.getMessage());
            return 5;
        }
    }
    
    private void showMessageValidation(String component, String message) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, TITULO_VALIDACION, message);
        FacesContext.getCurrentInstance().addMessage(component, fm);
    }
    
    private UIComponent getUIComponent(String id) {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getViewRoot().findComponent(id);
    }
    
    /**
     *@Autor Gabriel Niño Rosales
     * @Since 03/08/2016
     * Recuperar idCliente,ejecutar criterio por idCliente y inicializarDetalleCliente
     */
    
    public void iniciarDetalleCliente() {
        logger.log(ADFLogger.WARNING, "Into iniciarDetalleCliente.");
        Row rowCliente = null;
        activeRegionDocumentos = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Long idCliente = Long.valueOf(JSFUtils.resolveExpression("#{param.pIdCliente}").toString());
        //buscar por idCliente  en buscarClienteVO y hacer el row current
        OperationBinding operationBinding = bindings.getOperationBinding("buscarClienteVOCriteriaByIdCliente");
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.execute();
        //Ejecutar el metodo para invocar el servicio ConsultarClienteById, 
        //el id Cliente lo toma del currentRow de buscarClienteVO
        operationBinding = bindings.getOperationBinding("consultarDetalleCliente");
        operationBinding.execute();        
          
          rowCliente = (Row)operationBinding.getResult();
         
         setDatosCliente(rowCliente);
         validarResponsableCliente();
    }
    
    public void setDatosCliente(Row rowCliente){
        logger.warning("Inicia metodo setDatosCliente");
        if(rowCliente == null ){
            logger.warning("No se pudo recuperar los datos del cliente");
        }else{
        
            DatosClienteBean datosCliente  = (DatosClienteBean)JSFUtils.resolveExpression("#{pageFlowScope.DatosClienteBean}");
           
            try{
                datosCliente.setRazonSocial((String) rowCliente.getAttribute("RazonSocial"));
                datosCliente.setTipoPersona((Integer)rowCliente.getAttribute("TipoPersona"));
                datosCliente.setSector((Integer)rowCliente.getAttribute("Sector"));
                datosCliente.setPais((Integer) rowCliente.getAttribute("Pais"));
                datosCliente.setDiaPago((Integer) rowCliente.getAttribute("DiaPago"));        
                datosCliente.setScrId((Long) rowCliente.getAttribute("ScrId"));
                datosCliente.setIdCliente((Long) rowCliente.getAttribute("IdCliente"));
                datosCliente.setOficina((Integer) rowCliente.getAttribute("Oficina"));
                datosCliente.setAbreviatura((String) rowCliente.getAttribute("Abreviatura"));
                datosCliente.setTipoCliente((Long) rowCliente.getAttribute("TipoCliente"));        
                datosCliente.setTipoInstitucion((Integer) rowCliente.getAttribute("TipoInstitucion"));
                datosCliente.setGrupoEconomico((Integer) rowCliente.getAttribute("GrupoEconomico"));
                datosCliente.setNumeroIdentificacion((String) rowCliente.getAttribute("NumeroIdentificacion"));
                datosCliente.setIdFlexcube((String) rowCliente.getAttribute("IdFlexcube"));
                datosCliente.setEsDeteriorado((Boolean) rowCliente.getAttribute("EsDeteriorado"));
                datosCliente.setBicCode((String) rowCliente.getAttribute("BicCode"));
                datosCliente.setDireccion((String) rowCliente.getAttribute("Direccion"));
            }catch(Exception e){
                logger.warning("Ha ocurrido un error en la asignacion de datosClienteBean ->",e);
            }   
        
        }
    }
    
    public void validarResponsableCliente(){
        logger.warning("Entra en validarResponsableCliente.");
        BindingContainer bindings = null;
        OperationBinding operationBinding = null;
        String usuario = null;
        Boolean esResponsableCliente = null;
        try{
            usuario = ADFContext.getCurrent().getSecurityContext().getUserName();
            logger.warning("usuario : " + usuario);
            bindings = ADFUtils.getBindingContainer();
            operationBinding = bindings.getOperationBinding("validarRsponsableCliente");
            operationBinding.getParamsMap().put("usarioSesion", usuario);
            operationBinding.execute();
            
            if(null != operationBinding.getResult()){
                esResponsableCliente = (Boolean)operationBinding.getResult();
                if(esResponsableCliente){
                    logger.warning("El usuario en sesion es el responsable del cliente." + esResponsableCliente);
                    setEsResponsableCliente(esResponsableCliente);
                }else{
                    logger.warning("El usuario en sesion no es el responsable del cliente.");
                    setEsResponsableCliente(esResponsableCliente);
                }
            }else{
                logger.warning("El metodo validarRsponsableCliente regresa un valor nulo.");
            }
            
        }catch(Exception e){
            logger.warning("Error en validarResponsableCliente.", e);
        }
    }
    /**
     *@Autor Josue Hernandez Castillo
     * @Since 07/11/2016
     * Obtiene atributos del current row de Clientes y los setea a variables
     */
    
    public void obtenerAtributosCliente() {
        logger.log(ADFLogger.WARNING, "Into obtenerAtributosCliente.");
        activeRegionDocumentos = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        operationBinding = bindings.getOperationBinding("obtenerAtributosCliente");
        operationBinding.execute();
        
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Map<String, Object> atributosCliente = new HashMap<String, Object>();
            atributosCliente = (HashMap<String, Object>) operationBinding.getResult();
            
            if (atributosCliente.get("IdCliente") != null) {
                setIdCliente((Long)(atributosCliente.get("IdCliente")));
            } else {
                logger.log(ADFLogger.WARNING, "IdCliente no se recupero, valor es nulo.");
            }
            
            if (atributosCliente.get("ScrId") != null) {
                setIdScr((Long)(atributosCliente.get("ScrId")));
            } else {
                logger.log(ADFLogger.WARNING, "ScrId no se recupero, valor es nulo.");
            }
            
            logger.warning("IdCliente: " + getIdCliente());
            logger.warning("ScrId: " + getIdScr());
            
            validarResponsableCliente();
        }
    }
    
    public void avisoCobroListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "Into avisoCobroListener.");
        //valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean recibeAvisoCobroValue = (Boolean) valueChangeEvent.getNewValue();
        
        Integer recibeAvisoCobro = null;
        
        if (recibeAvisoCobroValue) {
            recibeAvisoCobro = 1;
        } else {
            recibeAvisoCobro = 0;
        }
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        Long idContacto = getIdContactoSeleccionado();
        
        operationBinding = bindings.getOperationBinding("actualizarContactoCliente");
        operationBinding.getParamsMap().put("idContacto", idContacto);
        operationBinding.getParamsMap().put("recibeAvisoCobro", recibeAvisoCobro);
        operationBinding.execute();
        
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            String result = (String) operationBinding.getResult();
            if (result != null && result.equals("true")) {
                logger.log(ADFLogger.WARNING, "se actualizado aviso de cobro valores en Binding : ");
                logger.warning("idContacto: " + getIdCliente());
                logger.warning("recibeAvisoCobro: " + getIdScr());        
            } else {
                logger.log(ADFLogger.WARNING, "No fue posible actualizar aviso de cobro.");
            }
            
        }
        /*refrescarTabla();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getUiContactosTable());
        FacesContext fctx = FacesContext.getCurrentInstance();
        String page = fctx.getViewRoot().getViewId();
        ViewHandler ViewH = fctx.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fctx, page);
        UIV.setViewId(page);
        fctx.setViewRoot(UIV);*/
        
        logger.warning("-------------------Finaliza metodo avisoCobroListener");
    }
    
    
    public void refrescarTabla(){
        logger.log(ADFLogger.WARNING, "Inicia metodo refrescarTabla");
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            
            Long idContacto = obtenerIdContacto();
            
            operationBinding = bindings.getOperationBinding("refrescarContactos");
            operationBinding.getParamsMap().put("pIdCliente", getIdCliente().toString());
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error ak refrescar tabla ->"+e);
        }
        
        logger.log(ADFLogger.WARNING, "termina metodo refrescarTabla");
    }
    
    
    public Long obtenerIdContacto() {
        logger.log(ADFLogger.WARNING, "inside obtenerIdContacto.");
        
        Long result = null;
        DCIteratorBinding voAdminContactos = ADFUtils.getDCBindingContainer().findIteratorBinding("AdminContactosVOIterator");
        ViewObject vo = voAdminContactos.getViewObject();
        Row adminContactosRow = vo.getCurrentRow();
        
        if (adminContactosRow != null) {
            result = (Long) adminContactosRow.getAttribute("IdContacto");    
        } else {
            logger.log(ADFLogger.WARNING, "Row es nulo.");
        }
        
        logger.log(ADFLogger.WARNING, "IdContacto: " + result);
        
        return result;
    }
    
    public void refreshTable() {
        logger.log(ADFLogger.WARNING, "inside refreshTable.");
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getUiContactosTable());
        
    }
    
    public void setUiContactosTable(RichTable uiContactosTable) {
        this.uiContactosTable = uiContactosTable;
    }

    public RichTable getUiContactosTable() {
        return uiContactosTable;
    }

    public void seleccionarContacto(SelectionEvent selectionEvent) {
        invokeTable("#{bindings.AdminContactosVO.collectionModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                     selectionEvent });
        
        logger.warning("IdCliente " + getIdCliente());
        logger.warning("IdContacto " + obtenerIdContacto());
        setContactoSeleccionado(Boolean.TRUE);
    }

    public void seleccionarOperacionRel(SelectionEvent selectionEvent) {
        invokeTable("#{bindings.OperacionesClienteVO.collectionModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                     selectionEvent });

        setOperacionRelSeleccionada(Boolean.TRUE);
    }
    
    public static Object invokeTable(String cadena, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext context = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(context, cadena, Object.class, paramTypes);
        return exp.invoke(context, params);
    }

    public boolean isContactoSeleccionado() {
        return contactoSeleccionado;
    }

    public void setContactoSeleccionado(boolean contactoSeleccionado) {
        this.contactoSeleccionado = contactoSeleccionado;
    }

    public boolean isOperacionRelSeleccionada() {
        return operacionRelSeleccionada;
    }

    public void setOperacionRelSeleccionada(boolean operacionRelSeleccionada) {
        this.operacionRelSeleccionada = operacionRelSeleccionada;
    }
    
    public boolean isMostrarSeguimientoCred() {
        boolean mostrarSeguimiento = Boolean.FALSE;
        boolean srcIdValido = Boolean.FALSE;
        
        AttributeBinding scrIdAttrBinding = ADFUtils.findControlBinding("ScrId");
        if (null != scrIdAttrBinding.getInputValue()) {
            String scrIdStr = (String)scrIdAttrBinding.getInputValue().toString();
            
            if (null != scrIdStr) {
                try {
                    Long scrId = Long.valueOf(scrIdStr);
                    if (scrId.intValue() > 0) {
                        srcIdValido = Boolean.TRUE;
                    }
                } catch(Exception e) {
                    logger.warning("No se puede convertir el valor de ScrId:"+scrIdStr);
                }
            }
        }
        
        if ((getEsAnalistaSeguimientoCrediticio() || 
            getEsCoordinadorSeguimientoCrediticio()) && srcIdValido) {
            mostrarSeguimiento = Boolean.TRUE;
        }
        
        return mostrarSeguimiento;
    }

    public void envioManualValueChange(ValueChangeEvent valueChangeEvent) {
        logger.warning("Into envioManualValueChange.");
        logger.warning("newValue: " + valueChangeEvent.getNewValue());
        
        Boolean envioManual = (Boolean) valueChangeEvent.getNewValue();
        Integer envioManualVal = (envioManual == true) ? 0 : 1;
        
        if (isRendered() == true) {
            BindingContainer bindings = ADFUtils.getBindingContainer();
                
            OperationBinding operationBinding = bindings.getOperationBinding("actualizarEnvioManual");
            operationBinding.getParamsMap().put("idCliente", getIdCliente());
            operationBinding.getParamsMap().put("envioManual", envioManualVal);
            operationBinding.execute();
            
            logger.warning("envioManual actualizado.");
        } else {
            logger.warning("Nuevo valor para envioManual: " + envioManual);
            ADFUtils.setBoundAttributeValue("ReqEnvioAvisoCobroAut", envioManual);
        }
    }

    public Boolean getHabilitarEnvioManualAvisoCobro() {
        return habilitarEnvioManualAvisoCobro;
    }

    public void setHabilitarEnvioManualAvisoCobro(Boolean habilitarEnvioManualAvisoCobro) {
        this.habilitarEnvioManualAvisoCobro = habilitarEnvioManualAvisoCobro;
    }

    public void setEsResponsableCliente(Boolean esResponsableCliente) {
        this.esResponsableCliente = esResponsableCliente;
    }

    public Boolean getEsResponsableCliente() {
        return esResponsableCliente;
    }


    public void razonSocialVCListener(ValueChangeEvent valueChangeEvent) {
        
        String razonSocialCurrent = (String) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("RazonSocial", razonSocialCurrent);
    }

    public void bicCodeVCListener(ValueChangeEvent valueChangeEvent) {
        
        String bicCodeCurrent = (String) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("BicCode", bicCodeCurrent);
    }
    
    public void direccionVCListener(ValueChangeEvent valueChangeEvent) {
        
        String direccionCurrent = (String) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("Direccion", direccionCurrent);
    }

    public void tipoPersonaVCListener(ValueChangeEvent valueChangeEvent) {
        
        Integer tipoPersonaCurrent = (Integer) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("TipoPersona", tipoPersonaCurrent);
    }

    public void sectorVCListener(ValueChangeEvent valueChangeEvent) {
        
        Integer sectorCurrent = (Integer) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("Sector", sectorCurrent);
    }

    public void paisVCListener(ValueChangeEvent valueChangeEvent) {
        
        Integer paisCurrent = (Integer) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("Pais", paisCurrent);
    }

    public void oficinaVCListener(ValueChangeEvent valueChangeEvent) {
        
        Integer oficinaCurrent = (Integer) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("Oficina", oficinaCurrent);
    }

    public void abreviaturaVCListener(ValueChangeEvent valueChangeEvent) {
        
        String abreviaturaCurrent = (String) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("Abreviatura", abreviaturaCurrent);
    }

    public void tipoInstitucionVCListener(ValueChangeEvent valueChangeEvent) {
        
        Integer tipoInstitucionCurrent = (Integer) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("TipoInstitucion", tipoInstitucionCurrent);
    }

    public void grupoEconomicoVCListener(ValueChangeEvent valueChangeEvent) {
        
        Integer grupoEconomicoCurrent = (Integer) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("GrupoEconomico", grupoEconomicoCurrent);
    }

    public void deterioradoVCListener(ValueChangeEvent valueChangeEvent) {
        
        Boolean deterioradoCurrent = (Boolean) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("EsDeteriorado", deterioradoCurrent);
    }

    public void diaPagoVCListener(ValueChangeEvent valueChangeEvent) {
        
        BigDecimal diaPagoCurrent = (BigDecimal) valueChangeEvent.getNewValue();
        
        ADFUtils.setBoundAttributeValue("DiaPago", diaPagoCurrent.intValue());
    }
}
