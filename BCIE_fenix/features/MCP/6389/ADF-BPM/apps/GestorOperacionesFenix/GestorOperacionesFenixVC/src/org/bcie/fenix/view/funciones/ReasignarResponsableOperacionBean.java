package org.bcie.fenix.view.funciones;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.aplicacionesexternas.AplicacionExternaBean;
import org.bcie.fenix.view.gestoroperaciones.GestorOperacionesBean;

public class ReasignarResponsableOperacionBean implements Serializable {
    @SuppressWarnings("oracle.jdeveloper.java.serialversionuid-stale")
    private static final long serialVersionUID = 1L;

    protected static ADFLogger logger = null;
    private static final String BUNDLE = "org.bcie.fenix.view.GestorOperacionesFenixVCBundle";
    private static final String MSG_CONFIRMACION_REASIGNACION_RESPONSABLE = "CONFIRMA_REASIGNAR_LA_OPERACION";
    private static final String MSG_EXITO_REASIGNACION_RESPONSABLE = "SE_HA_RESIGNADO_EL_RESPONSABLE";
    private static final String MSG_01_REASIGNACION_RESPONSABLE = "NO_ES_POSIBLE_REASIGNAR_RESPONSABLE";
    private static final Long ID_OPERACION_CANCELADA = 4L;

    private transient RichPopup popUpReasignarResponsable;
    private transient RichPopup popUpConfirmarReasignarResponsable;
    private String responsableOperacion;
    private String mensajeConfirmacion;
    
    //Utilizar metodo GestorOperacionesBean()
    private GestorOperacionesBean gestorOperacionesBean = null;
    
    public ReasignarResponsableOperacionBean() {
        super();

        if (logger == null) {
            logger = ADFLogger.createADFLogger(AplicacionExternaBean.class);
        }
    }
    
    private GestorOperacionesBean GestorOperacionesBean() {
        if (null == this.gestorOperacionesBean) {
            this.gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        }
        return this.gestorOperacionesBean;
    }

    public void visibilidadReasignarResponsable() {
        logger.log(ADFLogger.WARNING, "Entrando en mostrarFuncionReasignar.");
        
        // RN_02: La opción reasignar responsable solo estará disponible 
        // para el Responsable de esa Operación y su Gerente de País.
        Boolean esResponsableDeOperacion = esResponsableDeOpreacion();
        esResponsableDeOperacion = esResponsableDeOperacion == null ? false : esResponsableDeOperacion;
        
        Boolean esGerentePaisDelResponsableOperacion = GestorOperacionesBean().isEsGerentePais();
        esGerentePaisDelResponsableOperacion = esGerentePaisDelResponsableOperacion == null ? false : esGerentePaisDelResponsableOperacion;
        
        logger.log(ADFLogger.WARNING, "esResponsableDeOperacion: " + esResponsableDeOperacion);
        logger.log(ADFLogger.WARNING, "esGerentePaisDelResponsableOperacion: " + esGerentePaisDelResponsableOperacion);
        
        if (esResponsableDeOperacion || esGerentePaisDelResponsableOperacion) {
            GestorOperacionesBean().setMostrarFuncionReasignarResponsable(Boolean.TRUE);
        }
    }
    
    public boolean esResponsableDeOpreacion() {
        logger.log(ADFLogger.WARNING, "Entrando en esResponsableDeOpreacion.");
        boolean esResponsableOperacion = Boolean.FALSE;
        
        String usuario = GestorOperacionesBean().getNombreUsuario();
        usuario = usuario != null ? usuario.toLowerCase() : usuario;
        
        String responsableOperacion = GestorOperacionesBean().getResponsableOperacionInicio();
        responsableOperacion = responsableOperacion != null ? responsableOperacion.toLowerCase() : responsableOperacion;
        
        logger.log(ADFLogger.WARNING, "usuario: " + usuario);
        logger.log(ADFLogger.WARNING, "responsableOperacion: " + responsableOperacion);
        
        if (usuario.compareTo(responsableOperacion) == 0) {
            esResponsableOperacion = Boolean.TRUE;
        }
        
        logger.log(ADFLogger.WARNING, "esResponsableOperacion: " + esResponsableOperacion);
        
        return esResponsableOperacion;
    }
    
    @SuppressWarnings("unchecked")
    public boolean esGerentePaisDelResponsableOperacion() {
        logger.log(ADFLogger.WARNING, "Entrando en esGerentePaisDeResponsableOperacion.");
        logger.warning("nombre usuario session: "+GestorOperacionesBean().getNombreUsuario());
        
        boolean esGerentePais = Boolean.FALSE;
        List<String> listaGerentePais;
        
        BindingContainer bindingsGerente = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operBinding = null;
        operBinding = bindingsGerente.getOperationBinding("obtenerGerentePais");
        operBinding.getParamsMap().put("pUsuario", GestorOperacionesBean().getResponsableOperacionInicio());
        operBinding.execute();
        if(!operBinding.getErrors().isEmpty()){
            JSFUtils.addFacesErrorMessage(operBinding.getErrors().toString());
        } else {
            listaGerentePais = (List<String>)operBinding.getResult();
            
            if (listaGerentePais != null) {
                if (listaGerentePais.size() > 0) {
                    for(String gerentePais : listaGerentePais) {
                        if (gerentePais.trim().equalsIgnoreCase(GestorOperacionesBean().getNombreUsuario().trim())) {
                            esGerentePais = Boolean.TRUE;
                        }
                    }    
                }
            }
        }
        
        logger.log(ADFLogger.WARNING, "esGerentePais: " + esGerentePais);
        
        return esGerentePais;
    }

    @SuppressWarnings({ "unchecked", "unused" })
    public void reasignarResponsableOperacion(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Entrando en reasignarResponsableOperacion.");
        
        // RN_04: Se valida que la operacion no se encuentre cancelada
        if (GestorOperacionesBean().getIdEstadoOperacion().compareTo(ID_OPERACION_CANCELADA) != 0) {
            responsableOperacion = GestorOperacionesBean().getResponsableOperacionInicio();
            
            cargarListaResponsables();
            
            getPopUpReasignarResponsable().show(new RichPopup.PopupHints());
            logger.log(ADFLogger.WARNING, "Responsable de la operacion: " + responsableOperacion);
        } else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle(MSG_01_REASIGNACION_RESPONSABLE));
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarListaResponsables() {
        logger.log(ADFLogger.WARNING, "Entrando en cargarListaResponsables.");
        List<SelectItem> listaResponsablesSI = new ArrayList<>();
        String[] listaResponsables = new String[0]; 
        
        //obtenerRepresentantesFenix
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("obtenerRepresentantesFenix");
        operationBinding.execute();

        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            listaResponsables = (String[])operationBinding.getResult();
            for(String responsable : listaResponsables){
                listaResponsablesSI.add(new SelectItem(responsable, responsable));
            }
        }
        
        GestorOperacionesBean().setListaResponsablesSI(listaResponsablesSI);
    }

    private void construirMensajeConfirmacion() {
        String confirmacionBundle = getStringFromBundle(MSG_CONFIRMACION_REASIGNACION_RESPONSABLE);
        
        String usuario = GestorOperacionesBean().getReasignarA();
        logger.log(ADFLogger.WARNING, "Nuevo responsable seleccionado: " + usuario);
        
        if (null != confirmacionBundle) {
            confirmacionBundle = confirmacionBundle.replace("{0}", usuario);
        }

        setMensajeConfirmacion(confirmacionBundle);
    }

    @SuppressWarnings("unused")
    public void aceptarReasignarResponsableActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Entrando en aceptarReasignarResponsableActionListener.");
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        construirMensajeConfirmacion();
        getPopUpReasignarResponsable().hide();
        getPopUpConfirmarReasignarResponsable().show(popupHints);
    }

    @SuppressWarnings("unused")
    public void cerrarReasignarResponsableActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Entrando en cerrarReasignarResponsableActionListener.");
        getPopUpReasignarResponsable().hide();
    }

    @SuppressWarnings({"unchecked", "unused"})
    public void aceptarConfirmarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Entrando en aceptarConfirmarActionListener.");
        
        getPopUpConfirmarReasignarResponsable().hide();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBindingProceso = null;
        
        String usuario = GestorOperacionesBean().getReasignarA();
        logger.log(ADFLogger.WARNING, "Responsable de la operacion actual: " + usuario);
        
        operationBindingProceso = bindings.getOperationBinding("actualizarResponsableOperacion");
        operationBindingProceso.getParamsMap().put("idOperacion", GestorOperacionesBean().getIdOperacion());
        operationBindingProceso.getParamsMap().put("loginUsuario", usuario);
        operationBindingProceso.execute();

        if (!operationBindingProceso.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBindingProceso.getErrors().toString());
        } else {
            if (cambiarResponsableOperacionBpm(usuario)) {
                GestorOperacionesBean().setResponsableOperacionInicio(usuario);
                recargarBusquedaOperacion();
                JSFUtils.addFacesInformationMessage(getStringFromBundle(MSG_EXITO_REASIGNACION_RESPONSABLE));
            } else {
                //Mensaje manejado dentro del metodo cambiarResponsableOperacionBpm()
            }
        }
    }
    
    private void recargarBusquedaOperacion() {
        logger.log(ADFLogger.WARNING, "Entrando en recargarBusquedaOperacion.");
        //recargarBusquedaOperacion
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBindingProceso = null;
        operationBindingProceso = bindings.getOperationBinding("recargarBusquedaOperacion");
        operationBindingProceso.execute();

        if (!operationBindingProceso.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBindingProceso.getErrors().toString());
        }
    }

    @SuppressWarnings("unchecked")
    private boolean cambiarResponsableOperacionBpm(String usuario) {
        logger.log(ADFLogger.WARNING, "Entrando en cambiarResponsableOperacionBpm.");
        boolean cambioConExito = Boolean.FALSE;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBindingProceso = null;
        
        operationBindingProceso = bindings.getOperationBinding("cambiarResponsableOperacionBpm");
        operationBindingProceso.getParamsMap().put("idOperacion", GestorOperacionesBean().getIdOperacion());
        operationBindingProceso.getParamsMap().put("loginUsuario", usuario);
        operationBindingProceso.execute();

        if (!operationBindingProceso.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBindingProceso.getErrors().toString());
        } else {
            cambioConExito = Boolean.TRUE;
        }
        
        return cambioConExito;
    }

    @SuppressWarnings("unused")
    public void cancelarConfirmarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Entrando en cancelarConfirmarActionListener.");
        getPopUpConfirmarReasignarResponsable().hide();
    }
    
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    public RichPopup getPopUpReasignarResponsable() {
        return popUpReasignarResponsable;
    }

    public void setPopUpReasignarResponsable(RichPopup popUpReasignarResponsable) {
        this.popUpReasignarResponsable = popUpReasignarResponsable;
    }

    public String getResponsableOperacion() {
        return responsableOperacion;
    }

    public void setResponsableOperacion(String responsableOperacion) {
        this.responsableOperacion = responsableOperacion;
    }

    public RichPopup getPopUpConfirmarReasignarResponsable() {
        return popUpConfirmarReasignarResponsable;
    }

    public void setPopUpConfirmarReasignarResponsable(RichPopup popUpConfirmarReasignarResponsable) {
        this.popUpConfirmarReasignarResponsable = popUpConfirmarReasignarResponsable;
    }

    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
    }
}
