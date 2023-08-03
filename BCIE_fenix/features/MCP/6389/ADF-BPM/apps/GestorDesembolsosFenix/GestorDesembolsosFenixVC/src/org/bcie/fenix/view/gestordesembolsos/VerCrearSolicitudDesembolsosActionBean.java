package org.bcie.fenix.view.gestordesembolsos;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.GregorianCalendar;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.event.DisclosureEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.bcie.fenix.common.view.beans.FenixActionBean;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.acciones.AccionesBean;

public class VerCrearSolicitudDesembolsosActionBean extends FenixActionBean{
    public static ADFLogger logger = null;
    
    public static final String BUNDLE_DESEMBOLSOS = "view.GestorDesembolsosFenixVCBundle";
    
    private RichPopup popUpConsultarOperacion;
    private RichTable tablaOperacionesParaSolicitud;
    private RichPopup popUpNotificacionCreacionSolicitud;
    private RichTable tablaOperaciones;

    private RichOutputText comprobarCrearSolicitudOutputtext;
    private RichButton btnBuscarOperacion;

   private Integer eventoClick = 0;
    private RichRegion gestorDocumentosUIC;

    public VerCrearSolicitudDesembolsosActionBean() {
        if (logger == null){
            logger = ADFLogger.createADFLogger(VerCrearSolicitudDesembolsosActionBean.class);
        }
    }
    
    VerCrearSolicitudDesembolsosBean DesembolsosBean = (VerCrearSolicitudDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.VerCrearSolicitudDesembolsosBean}");


    public void busquedaOperaciones(ActionEvent actionEvent) {
        logger.warning("Inicia metodo busquedaOperaciones");        
        eventoClick = eventoClick + 1;
        
        if(eventoClick == 1){
        
         limpiarTabla();
      
            Long idOperacion = null;
            String nombreOperacion = null;
            String razonSocialCliente = null;
            String numeroLineaCredito = null;
            String responsableOperacion = null;
            Integer paisCliente = null;
            String descripcionProducto = null;
            Integer etapa = null;            
            Integer estado = null;
            
            Row rowOperacion = null;
            Long fistIdOperacion = null;
            Integer idEstadoOperacion = null;
            
            BindingContainer binding = getBindings();
            OperationBinding operBinding;
                         
                    
                         idOperacion = ( null != ADFUtils.getBoundAttributeValue("idOperacion"))
                                     ? new Long(ADFUtils.getBoundAttributeValue("idOperacion").toString()) : null;                            
                
                      nombreOperacion = ( null != ADFUtils.getBoundAttributeValue("nombreOperacion"))
                                      ? ADFUtils.getBoundAttributeValue("nombreOperacion").toString() :null;
               
                
                    razonSocialCliente = ( null != ADFUtils.getBoundAttributeValue("razonSocialCLiente") )
                                      ? ADFUtils.getBoundAttributeValue("razonSocialCLiente").toString() :null;
                                    
                 
                   numeroLineaCredito = ( null != ADFUtils.getBoundAttributeValue("numeroLineaCredito") )
                                      ? ADFUtils.getBoundAttributeValue("numeroLineaCredito").toString() :null;
                 
                 responsableOperacion = (null == ADFUtils.getBoundAttributeValue("responsableOperacion"))
                                      ? null: ADFUtils.getBoundAttributeValue("responsableOperacion").toString();   
                 
                         paisCliente = (ADFUtils.getBoundAttributeValue("paisCliente") == null || ADFUtils.getBoundAttributeValue("paisCliente").toString().equals(""))
                                     ? null : Integer.parseInt(ADFUtils.getBoundAttributeValue("paisCliente").toString());
                 
                               etapa = (ADFUtils.getBoundAttributeValue("etapa") == null ||ADFUtils.getBoundAttributeValue("etapa").toString().equals(""))
                                     ? null : Integer.parseInt(ADFUtils.getBoundAttributeValue("etapa").toString());         
                
                 descripcionProducto = (null != ADFUtils.getBoundAttributeValue("descripcionProducto") )
                                     ? ADFUtils.getBoundAttributeValue("descripcionProducto").toString() :null ;                    
                 
                         estado =  (null == ADFUtils.getBoundAttributeValue("estado") || ADFUtils.getBoundAttributeValue("estado").toString().equals(""))
                                ? null : Integer.parseInt(ADFUtils.getBoundAttributeValue("estado").toString());              
                              
                
                operBinding = binding.getOperationBinding("buscarOperacionesParaCrearSolicitud");
                operBinding.getParamsMap().put("idOperacion", idOperacion);
                operBinding.getParamsMap().put("nombreOperacion", nombreOperacion);      
                operBinding.getParamsMap().put("razonSocialCliente", razonSocialCliente);
                operBinding.getParamsMap().put("numeroLinea", numeroLineaCredito);
                operBinding.getParamsMap().put("responsableOperacion", responsableOperacion);
                operBinding.getParamsMap().put("paisCliente", paisCliente);
                operBinding.getParamsMap().put("descripcionProducto", descripcionProducto);
                operBinding.getParamsMap().put("etapa", etapa);
                operBinding.getParamsMap().put("estado", estado);
               
                operBinding.execute();
                                
               if(!operBinding.getErrors().isEmpty()){
                   logger.warning("OperationBinding devuelve errores");
               }else{
                       rowOperacion = (Row)operBinding.getResult();  
                        
                   if(rowOperacion != null){
                        fistIdOperacion = (Long)rowOperacion.getAttribute("IdOperacion");
                        idEstadoOperacion = (Integer)rowOperacion.getAttribute("Estado");
                    }                   
                    
                }
           
           logger.warning("*** ID de la primera Operacion ->"+fistIdOperacion); 
           logger.warning("*** Estado de la primera Operacion ->"+idEstadoOperacion); 
           DesembolsosBean.setIdOperacion(fistIdOperacion);
           DesembolsosBean.setIdEstadoOperacion(idEstadoOperacion);           
              
          AdfFacesContext.getCurrentInstance().addPartialTarget(getPopUpConsultarOperacion()); 
          eventoClick = 0;
        }
         logger.warning("*********** termina metodo busquedaOperaciones");
    }    
    
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void abrirPopUpConsultarOperacion(ActionEvent actionEvent) {
        String loginUsuario = null;
        eventoClick = eventoClick + 1;
        
        if(eventoClick == 1){                
        cargarRow(); // se crea una fila en la VO programatica para utilizar en el formulario
       // metodo limpia las row de la vo          
        limpiarTabla();
       
       //Obtencion del usuario de sesion para filtrar campo responsable de operacion por default con este valor
       loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();
       logger.warning("Usuario de sesion: " + loginUsuario);
       
       //Limpiar campos del formulario
       ADFUtils.setBoundAttributeValue("idOperacion", null);
       ADFUtils.setBoundAttributeValue("nombreOperacion", null);
       ADFUtils.setBoundAttributeValue("razonSocialCLiente", null);
       ADFUtils.setBoundAttributeValue("numeroLineaCredito", null);
       
       ADFUtils.setBoundAttributeValue("responsableOperacionAttrValue", loginUsuario);
       
       ADFUtils.setBoundAttributeValue("paisCliente", null);
       ADFUtils.setBoundAttributeValue("descripcionProducto", null);
      // ADFUtils.setBoundAttributeValue("etapa1", null);
       ADFUtils.setBoundAttributeValue("estado", null);
       
       //restablecer idOperacion en Bean a null
        DesembolsosBean.setIdOperacion(null);
      
       // Abrir popUp consultar operacion
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();        
        getPopUpConsultarOperacion().show(popupHints);
        
        //habilitamos el boton de buscar 
        DesembolsosBean.setBtnBuscarOperacion(Boolean.FALSE);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getBtnBuscarOperacion());
        eventoClick = 0;
        }   
    }

    public void cargarRow(){
            logger.warning("*** Inicia la llamada a el metodo cargarRow de VO ");
            try{
                BindingContainer binding = getBindings();
                OperationBinding operBinding = binding.getOperationBinding("cargarRowVo");
                 operBinding.execute();
                
                if(!operBinding.getErrors().isEmpty()){
                    logger.warning(" :( OperationBinding devuelve errores");
                }
            }catch(Exception e){
                    logger.warning(" :( Error al ejecutar el operationBinding: ", e);
            }     
            logger.warning("*** Termina la llamada a el metodo cargarRow de VO ");
        }


    public void limpiarTabla() {
        // Accedemos y ejecutamlos el metodo limpiarFilas() de OperacionesDatosSolicitudVO
        try {
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("limpiarFilas");
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
            }
        } catch (Exception e) {
            logger.warning(" :( Error al ejecutar el operationBinding: ", e);
        }
    }

    public void ejecutarQuery(String numeroLineaCredito) {
        logger.warning("inside ejecutarQuery.");
        logger.warning("numeroLineaCredito: " + numeroLineaCredito);
        try {
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("setpNumeroLineaCredito");
            operBinding.getParamsMap().put("value", numeroLineaCredito);
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning(" :( OperationBinding devuelve errores");
            }
        } catch (Exception e) {
            logger.warning(" :( Error al ejecutar el operationBinding: ", e);
        }
    }

   

    public void SeleccionarOperacionParacrearSolicitud(SelectionEvent selectionEvent) {   
        
        Long IdOperacion = new Long(0);      
        Integer idEstadoOperacion = null;
        // Actualizamos row seleccionado en tabla
        JSFUtils.resolveMethodExpression("#{bindings.OperacionDatosSolicitudVO2.collectionModel.makeCurrent}", 
                                         Object.class, new Class[] { SelectionEvent.class }, 
                                         new Object[] { selectionEvent });
        //Recupera el "ID" de la fila seleccionada en la tabla
        if(null != JSFUtils.resolveExpression("#{bindings.OperacionDatosSolicitudVO2.IdOperacion}")){
            IdOperacion=(Long)JSFUtils.resolveExpression("#{bindings.OperacionDatosSolicitudVO2.IdOperacion}");
        }
        
        //Recupera el "Estado" de la fila seleccionada en la tabla
        if(null != JSFUtils.resolveExpression("#{bindings.OperacionDatosSolicitudVO2.Estado}")){
            idEstadoOperacion=(Integer)JSFUtils.resolveExpression("#{bindings.OperacionDatosSolicitudVO2.Estado}");
        }
         
        DesembolsosBean.setIdOperacion(IdOperacion);
        DesembolsosBean.setIdEstadoOperacion(idEstadoOperacion);

      //  AdfFacesContext.getCurrentInstance().addPartialTarget(getPopUpConsultarOperacion());     
        logger.warning("*** Se ha seleccionado una operacion con un ID:"+IdOperacion+" y estado: "+idEstadoOperacion);
       
    }

    public void cerrarPopUpConsultarOperacion(ActionEvent actionEvent) {
        eventoClick = eventoClick + 1;
        
        if(eventoClick == 1){
           getPopUpConsultarOperacion().hide();
           eventoClick = 0;
        }
    }

    public void crearSolicitudEnOperacion(ActionEvent actionEvent) {
        logger.warning("*********** Ha iniciado el metodo crearSolicitudEnOperacion ");
        eventoClick = eventoClick + 1;

        if (eventoClick == 1) {
            Date fecha = new Date();
            Long idOperacion = DesembolsosBean.getIdOperacion();
            Integer idEstadoOperacion = DesembolsosBean.getIdEstadoOperacion();

            Map mapaDatos = new HashMap();
            Long idSolicitud = null;
            Integer idEstadoSolicitud = null;
            
            logger.warning("idOperacion: " + idOperacion);
            logger.warning("idEstadoOperacion: " + idEstadoOperacion);

            if (idOperacion != null && null != idEstadoOperacion) {
                
                //Se valida que la operacion no se encuentre cancelada o cerrada
                if (!(idEstadoOperacion.compareTo(FenixConstants.ID_ESTADO_OPERACION_CANCELADA) == 0 ||
                    idEstadoOperacion.compareTo(FenixConstants.ID_ESTADO_OPERACION_CERRADA) == 0)) {
                    try {
                        BindingContainer binding = getBindings();
                        OperationBinding operBinding = binding.getOperationBinding("crearSolicitudDesembolso");
                        operBinding.getParamsMap().put("idOperacion", idOperacion);
                        operBinding.getParamsMap().put("fechaCreacionSolicitud", fecha);

                        mapaDatos = (Map) operBinding.execute();
                        
                        if (!operBinding.getErrors().isEmpty()) {
                            logger.warning("OperationBinding  crearSolicitudDesembolso devuelve errores" +
                                           operBinding.getErrors().toString());
                            JSFUtils.addFacesErrorMessage("Error al crear Solicitud Desembolso: " +
                                                          operBinding.getErrors().toString());
                        } else {
                            if (mapaDatos == null || mapaDatos.isEmpty()) {
                                logger.warning("***Error, El mapa que regresa el metodo de VO es nulo ->idSolicitud: " +
                                               idSolicitud + " idEstadoSolicitud: " + idEstadoSolicitud);
                                JSFUtils.addFacesErrorMessage("Error al crear Solicitud Desembolso: No se recuperaron el Id y el estado de la nueva solicitud.");
                            } else {
                                idSolicitud = new Long(mapaDatos.get("newIdSolicitud").toString());
                                idEstadoSolicitud = Integer.parseInt(mapaDatos.get("idEstadoSolicitud").toString());
                                logger.warning("*** Seteando id de la solicitud -->" + idSolicitud +
                                               " idEstadoSolicitud->" + idEstadoSolicitud);
                                DesembolsosBean.setIdSolicitud(idSolicitud);
                                DesembolsosBean.setIdEstadoSolicitud(idEstadoSolicitud);
                                getPopUpConsultarOperacion().hide();

                                DesembolsosBean.setSeHaMostrarPopUp(Boolean.FALSE);
                                // Redireccionar a outcome
                                logger.warning("*** Listo solicitud creada, redireccionando... ");
                                FacesContext facesContext = FacesContext.getCurrentInstance();
                                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null,
                                                                                                      "irConsultarDatosSolicitud");
                            }
                        }
                    } catch (Exception e) {
                        logger.warning(" :( Error al ejecutar el operationBinding: ", e);
                    }
                } else {
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("NO_CREAR_SOLICITUD_OPERACION_INVALIDA", BUNDLE_DESEMBOLSOS));
                }
            } else {
                logger.warning(" no se a podido crear la solicitud se necesita un id de operacion -> revise SeleccionarOperacionParacrearSolicitud()");
            }
            eventoClick = 0;
        }
        logger.warning("*********** Ha Terminado el metodo crearSolicitudEnOperacion --");
    }
    
    
    public void comprobarCreacionSolicitud(){
            logger.warning("*********** Inicia metodo comprobarCreacionSolicitud ");            
            String opcionSolicitud = JSFUtils.resolveExpression("#{pageFlowScope.operacionSolicitud}").toString();
            Long idSolicitud = DesembolsosBean.getIdSolicitud();
            Long idOperacion = DesembolsosBean.getIdOperacion();
            if(opcionSolicitud.equals("crearSolicitud") && !DesembolsosBean.getSeHaMostrarPopUp() ){                                               
                   RichPopup.PopupHints popupHints = new RichPopup.PopupHints();                     
                   getPopUpNotificacionCreacionSolicitud().show(popupHints); 
                   DesembolsosBean.setSeHaMostrarPopUp(Boolean.TRUE);
                   AdfFacesContext.getCurrentInstance().addPartialTarget(getPopUpNotificacionCreacionSolicitud());     
             }
            logger.warning("*********** Termina metodo comprobarCreacionSolicitud idSolicitud recuperado->"+idSolicitud+" idOperacion ->"+idOperacion);            
        
        }
    
    public void restablecerFormularioBuscarOperacion(ActionEvent actionEvent) {
        eventoClick = eventoClick + 1;
        
        if(eventoClick == 1){
        // metodo limpia las row de la vo          
         limpiarTabla();
        
        //Limpiar campos del formulario
        ADFUtils.setBoundAttributeValue("idOperacion", null);
        ADFUtils.setBoundAttributeValue("nombreOperacion", null);
        ADFUtils.setBoundAttributeValue("razonSocialCLiente", null);
        ADFUtils.setBoundAttributeValue("numeroLineaCredito", null);
      // ADFUtils.setBoundAttributeValue("responsableOperacionAttrValue", null);
        ADFUtils.setBoundAttributeValue("paisCliente", null);
        ADFUtils.setBoundAttributeValue("descripcionProducto", null);
      //  ADFUtils.setBoundAttributeValue("etapa1", null);
        ADFUtils.setBoundAttributeValue("estado", null);
        
        DesembolsosBean.setIdOperacion(null);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPopUpConsultarOperacion());
        eventoClick = 0;
        }
    }             
   
    public String returnTaskflowInicio() {
        logger.warning("*********** iniciando redireccion return--> ");
         FacesContext ctx = FacesContext.getCurrentInstance();
         ExternalContext ectx = ctx.getExternalContext();
         HttpServletRequest httpRequest = (HttpServletRequest) ectx.getRequest();
         try {
             String logoutUrl = ectx.getRequestContextPath() + "/faces/pages/inicio.jspx";
             redirect(logoutUrl);
         } catch (Exception e) {
             logger.warning("ServletException Ocurrió un error, Por favor consulte el log para más información.");
            
         }
         return null;
    }
         
        private void redirect(String forwardUrl) {
           FacesContext ctx = FacesContext.getCurrentInstance();
           ExternalContext ectx = ctx.getExternalContext();
           try {
             ectx.redirect(forwardUrl);
           } catch (IOException ie) {
               logger.warning("Ocurrió un error al redireccionar la página de inicio.  Por favor consulte el log para más información.");           
           }
    }
   
    public void cerrarPopUpNotificacionCreacionSolicitud(ActionEvent actionEvent) {
        eventoClick = eventoClick + 1;        
        if(eventoClick == 1){
            getPopUpNotificacionCreacionSolicitud().hide(); 
            eventoClick = 0;
        }
    }
   
    public void returnTaskflowDesembolsos(ActionEvent actionEvent) {
        eventoClick = eventoClick + 1;        
        if(eventoClick == 1){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "irReturnTaskflow");       
            eventoClick = 0;
        }
       
    }
  
    public void comprobarErrores(){
      logger.warning("*Inf, Inicia metodo comprobarErrores");
        Row[] errorsList = null;
        Row filaError = null;
        String descripcionError = "";
        
        try {
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("consultarErrores");            
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding consultarErrores devuelve errores ->"+operBinding.getErrors().toString());
            }else{
                errorsList = (Row[])operBinding.getResult();
                
                if(errorsList != null){ 
                    logger.warning("*Inf, numero de errores encontrados ->"+errorsList.length);                      
                      
                    for(Row rowError : errorsList){                                            
                         descripcionError = rowError.getAttribute("DescripcionError").toString();
                         JSFUtils.addFacesErrorMessage(descripcionError);
                    }
                    
                }else{
                    logger.warning("*Inf, No se encontraron errores");
                }
            }
        } catch (Exception e) {
            logger.warning("***Error al ejecutar el operationBinding consultarErrores: ", e);
            JSFUtils.addFacesErrorMessage("Error ->"+e.getMessage().toString());
        }
            
      logger.warning("*Inf, termina metodo comprobarErrores");  
    }
       
       
    public void limpiarManejoErroresVO(){
      logger.warning("*Inf, Inicia metodo limpiarManejoErroresVO");       
        
        try {
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("limpiarManejoErroresVO");            
            operBinding.execute();

            if (!operBinding.getErrors().isEmpty()) {
                logger.warning("OperationBinding limpiarManejoErroresVO devuelve errores ->"+operBinding.getErrors().toString());
            }
             
        } catch (Exception e) {
            logger.warning("***Error al ejecutar el operationBinding limpiarManejoErroresVO: ", e);
            JSFUtils.addFacesErrorMessage("Error ->"+e.getMessage().toString());
        }
            
      logger.warning("*Inf, termina metodo limpiarManejoErroresVO");  
    }
    
    public void disclosureListenerCargarGestorDocumentos(DisclosureEvent disclosureEvent) {
        DesembolsosBean.setCargarRegionDocumentos(Integer.valueOf(2));
        DesembolsosBean.renderRegionGestorDocumentos();
    }
    //

   //  Accesors 
   
    public void setPopUpNotificacionCreacionSolicitud(RichPopup popUpNotificacionCreacionSolicitud) {
        this.popUpNotificacionCreacionSolicitud = popUpNotificacionCreacionSolicitud;
    }

    public RichPopup getPopUpNotificacionCreacionSolicitud() {
        return popUpNotificacionCreacionSolicitud;
    }

    public void setTablaOperaciones(RichTable tablaOperaciones) {
        this.tablaOperaciones = tablaOperaciones;
    }

    public RichTable getTablaOperaciones() {
        return tablaOperaciones;
    }
    public void setPopUpConsultarOperacion(RichPopup popUpConsultarOperacion) {
        this.popUpConsultarOperacion = popUpConsultarOperacion;
    }

    public RichPopup getPopUpConsultarOperacion() {
        return popUpConsultarOperacion;
    }

    public void setTablaOperacionesParaSolicitud(RichTable tablaOperacionesParaSolicitud) {
        this.tablaOperacionesParaSolicitud = tablaOperacionesParaSolicitud;
    }

    public RichTable getTablaOperacionesParaSolicitud() {
        return tablaOperacionesParaSolicitud;
    }

    public void setComprobarCrearSolicitudOutputtext(RichOutputText comprobarCrearSolicitudOutputtext) {
        this.comprobarCrearSolicitudOutputtext = comprobarCrearSolicitudOutputtext;
    }

    public RichOutputText getComprobarCrearSolicitudOutputtext() {
        limpiarManejoErroresVO();
        comprobarErrores();        
        comprobarCreacionSolicitud();        
        return comprobarCrearSolicitudOutputtext;
    }

    public void setBtnBuscarOperacion(RichButton btnBuscarOperacion) {
        this.btnBuscarOperacion = btnBuscarOperacion;
    }

    public RichButton getBtnBuscarOperacion() {
        return btnBuscarOperacion;
    }

    public void setGestorDocumentosUIC(RichRegion gestorDocumentosUIC) {
        this.gestorDocumentosUIC = gestorDocumentosUIC;
    }

    public RichRegion getGestorDocumentosUIC() {
        return gestorDocumentosUIC;
    }

    public String limpiarTablas() {
        // Add event code here...
    String respuesta="irRegresarBusqueda";
        logger.warning("***Inicia Limpiar tabla de busqueda");
        try{
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("limpiarTabla");
             operBinding.execute();
            
            if(!operBinding.getErrors().isEmpty()){
                logger.warning(" :( OperationBinding devuelve errores");
            }
        }catch(Exception e){
                logger.warning(" :( Error al ejecutar el operationBinding: ", e);
        }     
        logger.warning("*** Termina Limpiar tabla de busqueda ");
    return respuesta;
    }
    
}

