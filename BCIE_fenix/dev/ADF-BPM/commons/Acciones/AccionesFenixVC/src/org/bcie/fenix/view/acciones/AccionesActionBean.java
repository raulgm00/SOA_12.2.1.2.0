package org.bcie.fenix.view.acciones;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;

import java.util.Set;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichListView;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.QueryEvent;

import oracle.adf.view.rich.model.AttributeCriterion;
import oracle.adf.view.rich.model.AttributeDescriptor;
import oracle.adf.view.rich.model.ConjunctionCriterion;
import oracle.adf.view.rich.model.Criterion;
import oracle.adf.view.rich.model.FilterableQueryDescriptor;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.vo.common.AccionesTablaVO;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class AccionesActionBean implements Serializable {
    @SuppressWarnings("compatibility:8319091430672918515")
    private static final long serialVersionUID = -4935414325418439071L;
    private static ADFLogger logger = null;
    private RichPopup popupNuevoEditarAccion;
    private RichPopup popupEliminarAccion;
    private RichPopup popupDetalleAccion;
    private RichListView observacionesListViewUIC;
    private RichInputText observacionInputUIC;
        
    public static final String BUNDLE = "org.bcie.fenix.view.acciones.AccionesFenixVCBundle";
    private RichTable accionesTablaUIC;
    private long tiempo;


    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public long getTiempo() {
        tiempo = System.currentTimeMillis();
        return tiempo;
    }

    public AccionesActionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public RichPopup getPopupNuevoEditarAccion() {
        return popupNuevoEditarAccion;
    }

    public void setPopupNuevoEditarAccion(RichPopup popupNuevoEditarAccion) {
        this.popupNuevoEditarAccion = popupNuevoEditarAccion;
    }

    public RichPopup getPopupEliminarAccion() {
        return popupEliminarAccion;
    }

    public void setPopupEliminarAccion(RichPopup popupEliminarAccion) {
        this.popupEliminarAccion = popupEliminarAccion;
    }

    public RichPopup getPopupDetalleAccion() {
        return popupDetalleAccion;
    }

    public void setPopupDetalleAccion(RichPopup popupEditarAccion) {
        this.popupDetalleAccion = popupEditarAccion;
    }

    public void mostrarNuevoEditarAccion(ActionEvent actionEvent) {
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
        accionesBean.setTituloNuevoEditarAccion("Crear Acción");
        accionesBean.setBotonAccion("Crear");
        accionesBean.setNuevaAccion(1);
        logger.warning("Nueva accion}: " + accionesBean.getNuevaAccion());
        logger.warning("etiqueta: " + accionesBean.getTituloNuevoEditarAccion());

        logger.warning("procesoActual: " + accionesBean.getProceso());
        logger.warning("requiereValidacion: " + accionesBean.getRequireValidacion());
        
        Boolean nuevaAcciones=Boolean.FALSE;
        if(accionesBean.getRequireValidacion()){
                accionesBean.setEstadoAccion(FenixConstants.ESTADO_ACCION_REGISTRADO);                
            }
        else{
                accionesBean.setEstadoAccion(FenixConstants.ESTADO_ACCION_PORATENDER);
             }
        nuevaAcciones=nuevaAccion(accionesBean.getEstadoAccion());
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        
        logger.warning("Respuesta: "+ nuevaAcciones);
        if(null!= nuevaAcciones && nuevaAcciones){
                getPopupNuevoEditarAccion().show(popupHints);
            }
    }

    public void mostrarEditarAccion(ActionEvent actionEvent) {
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
        AttributeBinding idAccionAsignado;
        idAccionAsignado = ADFUtils.findControlBinding("Id1");
        AttributeBinding idAccionRegistardo;
        idAccionRegistardo = ADFUtils.findControlBinding("IdAccionRegistrado");
        if(null==(Long)idAccionRegistardo.getInputValue()){
                idAccionRegistardo = ADFUtils.findControlBinding("IdAccionPorAtender");
            }
        accionesBean.setNuevaAccion(0);
        accionesBean.setIdAccion((Long) idAccionAsignado.getInputValue());
        accionesBean.setTituloNuevoEditarAccion("Modificar acción");
        accionesBean.setBotonAccion("Guardar");
        logger.warning("Nueva accion}: " + accionesBean.getNuevaAccion());
        logger.warning("etiqueta: " + accionesBean.getTituloNuevoEditarAccion());
        logger.warning("id: " + accionesBean.getIdAccion());

        logger.warning("procesoActual: " + accionesBean.getProceso());
        logger.warning("reqVal: " + accionesBean.getRequireValidacion());
        switch (accionesBean.getProceso()) {
       
        case FenixConstants.PROCESO_GESTOR_CLIENTES:
            if(null!=(Long)idAccionRegistardo.getInputValue()){
                    accionesBean.setBotonCategoria(habilitarCatalogo((Long)idAccionRegistardo.getInputValue()));
                }
            break;
        case FenixConstants.PROCESO_GESTOR_OPERACIONES:
            if(null!=(Long)idAccionRegistardo.getInputValue()){
                    accionesBean.setBotonCategoria(habilitarCatalogo((Long)idAccionRegistardo.getInputValue()));
                }
            break;
        default:
            break;
        }
        if(seleccionarAccion()){
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
             getPopupNuevoEditarAccion().show(popupHints);
        }  
    }

    public void mostrarEliminarAccion(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupNuevoEditarAccion().show(popupHints);
    }

    public void mostrarDetalleAccion(ActionEvent actionEvent) {
        AttributeBinding nombreAsignado;
        logger.warning("Entra a metodo mostrarDetalleAccion");
        nombreAsignado = ADFUtils.findControlBinding("NombreRolAsignado");

        String rolAsignadoNombre = (String) nombreAsignado.getInputValue();
            Long idAccion = null;
            Integer idEstadoAccion = null;
            
            try{
                idAccion = new Long(ADFUtils.getBoundAttributeValue("Id1").toString());
            } catch(Exception e){
                logger.warning("Error al obtener idAccion: ", e);
            }
            
            try{
                idEstadoAccion = Integer.parseInt(ADFUtils.getBoundAttributeValue("IdEstadoAccion1").toString());
                logger.warning("IdEstadoAccion al cargar popup de detalleAccion: " + idEstadoAccion);
            }catch(Exception e){
                logger.warning("Error al obtener idEstadoAccion: ", e);
            }
            AccionesBean accionesBean=(AccionesBean)JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
            if(null != idAccion){
                
                if(null != accionesBean){
                    accionesBean.setObservacion(null);
                    accionesBean.setIdAccion(idAccion);
                    accionesBean.setEstadoAccion(idEstadoAccion);
                    logger.warning("Seteando idObservacion a NULL");
                    accionesBean.setIdObservacion(null);
                }
                
                for (String rol : accionesBean.getListaPermisos()) {
            if (rolAsignadoNombre.equalsIgnoreCase(rol)) {
                accionesBean.setRolAsignadoAplica(Boolean.TRUE);
            }
            if (rol.contains(FenixConstants.ROL_RESPONSABLE) && rolAsignadoNombre.equalsIgnoreCase(FenixConstants.ROL_RESPONSABLE)) {
                accionesBean.setRolAsignadoAplica(Boolean.TRUE);
            }
        }
                
                try{
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("cargarObservacionesAccion");
                    operationBinding.getParamsMap().put("idAccion", idAccion);
                    operationBinding.execute();
                    
                    if(!operationBinding.getErrors().isEmpty()){
                        logger.warning("OperationBinding devuelve errores: " + operationBinding.getErrors());
                    }
                }catch(Exception e){
                    logger.warning("Error al invocar operationBinding: ", e);
                }
            }
            
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        logger.warning("Abriendo popupDetalleAccion");
        getPopupDetalleAccion().show(popupHints);
        logger.warning("Termina metodo mostrarDetalleAccion");
    }
    
    public Boolean habilitarCatalogo(Long idAccion) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("habilitaCatologoGestor");
        operationBinding.getParamsMap().put("idAccion", idAccion);
        Boolean resultado = (Boolean) operationBinding.execute();

        logger.warning("resultado= " + resultado);
        logger.warning("resultado2= " + operationBinding.getResult());
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return null;
        }
        Boolean resultadoFinal = !resultado;
        return resultadoFinal;
    }

    public Boolean nuevaAccion(Integer nuevaAccionews) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("nuevaAccion");
        operationBinding.getParamsMap().put("estadoAccion", nuevaAccionews);
        Boolean resultado = (Boolean) operationBinding.execute();

        logger.warning("resultado= " + resultado);
        logger.warning("resultado2= " + operationBinding.getResult());
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return null;
        }
        return resultado;
    }

    public String guardarAccion() {
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");

        Integer categoria = null;
        logger.warning("proceso: " + accionesBean.getProceso());
        logger.warning("RequireValidacion: " + accionesBean.getRequireValidacion());
        logger.warning("Nueva accion: " + accionesBean.getNuevaAccion());

        if (validarAcciones()) {


            AttributeBinding claveAccion;
            claveAccion = ADFUtils.findControlBinding("Id");
            accionesBean.setIdAccion((Long) claveAccion.getInputValue());

            AttributeBinding estadoActual;
            estadoActual = ADFUtils.findControlBinding("IdEstadoAccion");
            Integer estadoAccion = (Integer) estadoActual.getInputValue();
            Date fecha = new Date();
            java.sql.Timestamp fechaActual = new java.sql.Timestamp(fecha.getTime());
            fechaActual.setHours(0);
            fechaActual.setMinutes(0);
            fechaActual.setSeconds(0);
            fechaActual.setNanos(0);
            logger.warning("fechaActual: " + fechaActual);
            java.sql.Timestamp fechaSugerida = null;
            AttributeBinding fechaAtencion;
            fechaAtencion = ADFUtils.findControlBinding("FechaAtencion");
            fechaSugerida = (java.sql.Timestamp) fechaAtencion.getInputValue();

            if (null != accionesBean.getNuevaAccion()) {
                if (accionesBean.getNuevaAccion().compareTo(1) == 0) {
                    accionesBean.setIdObservacion(null);
                    accionesBean.setEstadoAnterior(null);
                    guardarSeguimientoAccion((Integer) estadoActual.getInputValue(), accionesBean.getEstadoAnterior(),
                                             null, accionesBean.getRolBPM());
                }
                if (!accionesBean.getRequireValidacion()) {

                    if (fechaActual.compareTo(fechaSugerida) > 0 &&
                        estadoAccion.compareTo(FenixConstants.TCA_ESTADO_ACCION_POR_ATENDER) == 0) {
                        logger.warning("se actualiza estado a vencida");
                        estadoActual.setInputValue(FenixConstants.TCA_ESTADO_ACCION_VENCIDA);
                        Boolean seguimientoAccion = Boolean.TRUE;
                        seguimientoAccion = buscarRegistroEstadoAccion(accionesBean.getIdAccion());

                        if (seguimientoAccion) {
                            seguimientoAccion = actualizarEstadoSeguimientoAccion();

                            if (seguimientoAccion) {
                                seguimientoAccion =
                                    guardarSeguimientoAccion(FenixConstants.TCA_ESTADO_ACCION_VENCIDA,
                                                             FenixConstants.TCA_ESTADO_ACCION_POR_ATENDER, null,
                                                             accionesBean.getRolBPM());


                            } else {
                                logger.warning("Error al actualizar el estado anterior");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                            }
                        } else {
                            logger.warning("Error al obtener el row de Seguimiento accion");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                        }
                    }
                }
                else{
                        estadoActual.setInputValue(FenixConstants.TCA_ESTADO_ACCION_REGISTRADA);
                    }
                getPopupNuevoEditarAccion().hide();
                if (guardarCommit()) {
                    DCIteratorBinding voAcciones =
                        ADFUtils.getDCBindingContainer().findIteratorBinding("AccionesTablaVOIterator");
                   // ViewObject acccionesTablaVO = null;
                    //acccionesTablaVO = voAcciones.getViewObject();
                    voAcciones.executeQuery();
                   // logger.warning("query: " + acccionesTablaVO.getQuery());
                } else {
                    if (null == cancelarRollback() || !cancelarRollback()) {
                        logger.warning("error al guardar los datos");
                    }
                }
            }


        } else {
           RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
           logger.warning("Abriendo popup mensajes");
           getPopupEliminarAccion().show(popupHints);
        }
        return null;
    }
    
    public String cancelarEliminar() {
        getPopupEliminarAccion().hide();
        return null;
    }

    public String cancelarGuardarAccion() {
        cancelarRollback();
        getPopupNuevoEditarAccion().hide();
        return null;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    public Boolean guardarCommit() {
        Boolean resultado=Boolean.TRUE;
        logger.warning("guardando accion");
        OperationBinding commit = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        commit = bindings.getOperationBinding("Commit");

        commit.execute();
        if (!commit.getErrors().isEmpty()) {
            // Manejo de errores
            resultado=Boolean.FALSE;
        }
        return resultado;
    }

    public Boolean cancelarRollback() {
        Boolean resultado=Boolean.TRUE;
        OperationBinding rollback = null;
        BindingContainer bindings = null;

        bindings = ADFUtils.getBindingContainer();
        rollback = bindings.getOperationBinding("Rollback");

        rollback.execute();
        if (!rollback.getErrors().isEmpty()) {
            resultado=Boolean.FALSE;
        }
        return resultado;
    }

    public Boolean guardarSeguimientoAccion(Integer estadoActual, Integer estadoAnterior, Long idAccionAnterior, Integer rolBpm) {
        logger.warning("Inicia metodo guardarSeguimientoAccion");
        Boolean resultado = Boolean.TRUE;
        
        try{
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("ingresarSeguimientoAccion");
            operationBinding.getParamsMap().put("rolBpm", rolBpm);
            operationBinding.getParamsMap().put("estadoAnterior", estadoAnterior);
            operationBinding.getParamsMap().put("estadoDespues", estadoActual);
            operationBinding.getParamsMap().put("idSeguimientoAnterior", idAccionAnterior);
            resultado = (Boolean) operationBinding.execute();
    
            logger.warning("resultado= " + resultado);
            logger.warning("resultado2= " + operationBinding.getResult());
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("Error");
                return null;
            }
        }catch(Exception e){
            logger.warning("Error al ejecutar el operationBinding: ", e);
        }
        
        logger.warning("termina metodo guardarSeguimientoAccion");
        return resultado;
    }


    public void catalogoRoles(Integer categoria) {
        Boolean resultado = Boolean.TRUE;

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("cargaRoles");
        operationBinding.getParamsMap().put("idCategoria", categoria);
        resultado = (Boolean) operationBinding.execute();

        logger.warning("resultado= " + resultado);
        logger.warning("resultado2= " + operationBinding.getResult());
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
        }

    }

    public Boolean seleccionarAccion() {
        Boolean resultado = Boolean.TRUE;

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("seleccionarAccion");
        resultado = (Boolean) operationBinding.execute();

        logger.warning("resultado= " + resultado);
        logger.warning("resultado2= " + operationBinding.getResult());
        if (!operationBinding.getErrors().isEmpty()) {
            logger.warning("Error");
            return null;
        }

        return resultado;
    }

    public void guardarObservacionesAccion(ActionEvent actionEvent){
        logger.warning("Entra a metodo guardarObservacionesAccion");

        oracle.jbo.domain.Number idObservacionAccionCreada = null;
        Long idAccion = null;
        String login = null;
        String nombreUsuario = null;
        Integer rolBpm = null;
        Integer tarea = null;
        String observaciones = null;
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
        
        if(null != accionesBean){
            if(null == accionesBean.getObservacion() ||
               accionesBean.getObservacion().trim().equals("")){
                logger.warning("La observacion es NULL o vacia.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("AGREGAR_OBSERVACION_MSG"));
                return;
            }            
        }
                
        try{
            idAccion = new Long(ADFUtils.getBoundAttributeValue("Id1").toString());
            logger.warning("IdAccion: " + ADFUtils.getBoundAttributeValue("Id1").toString());
        }catch(Exception e){
            logger.warning("Error al obtener idAccion: ", e);
        }
        
        try{
            login = (String) JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}");
            logger.warning("login: " + login);
        } catch (Exception e){
            logger.warning("Error al obtener login: ", e);
        }
        
        try{
            rolBpm = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pRolUsuraio}");
            logger.warning("rolBpm: " + rolBpm);
        }catch(Exception e){
            logger.warning("Error al obtener rolBpm: ", e);
        }
        
        try{
            tarea = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pTareaProceso}").toString());
            logger.warning("tarea: " + tarea);
        }catch(Exception e){
            logger.warning("Error al obtener tarea: ", e);
        }
        
        if(null != accionesBean){
            if(null != accionesBean.getNombreCompleto()){
                nombreUsuario = accionesBean.getNombreCompleto();
                logger.warning("NombreUsuario: " + nombreUsuario);
            }else{
                logger.warning("NombreUsuario es NULL");
            }
            
            if(null != accionesBean.getObservacion()){
                observaciones = accionesBean.getObservacion();
                logger.warning("Observaciones: " + observaciones);
                logger.warning("Validando longitud de observacion, longitud: " + observaciones.length());
                if(observaciones.length() > 4000){
                    logger.warning("Longitud de observacion mayor a 4000 caracteres.");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("LONGITUD_MAXIMA_ERROR_MSG"));
                    return;
                }
            }else{
                logger.warning("Observaciones es NULL");
            }
        }
        
        try{
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("crearObservacion");
            operationBinding.getParamsMap().put("idAccion", idAccion);
            operationBinding.getParamsMap().put("login", login);
            operationBinding.getParamsMap().put("nombre", nombreUsuario);
            operationBinding.getParamsMap().put("rolBpm", rolBpm);
            operationBinding.getParamsMap().put("observacion", observaciones);
            operationBinding.getParamsMap().put("tarea", tarea);
        idObservacionAccionCreada = (oracle.jbo.domain.Number) operationBinding.execute();

        logger.warning("resultado2= " + operationBinding.getResult());
        if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("Error en el operation binding");
            }else{
            if(null != idObservacionAccionCreada){
                logger.warning("idObservacionAccioneCreada = " + idObservacionAccionCreada);            
                DCIteratorBinding observacionAccionIteratorVO = null;
                ViewObject observacionRootVO = null;
                
                observacionAccionIteratorVO = ADFUtils.findIterator("ObservacionAccionVOIterator");
                observacionRootVO = observacionAccionIteratorVO.getViewObject();
                observacionRootVO.executeQuery(); 
            
                if(null != accionesBean){
                    accionesBean.setObservacion(null);
                    Long idObservacionCreada = new Long(idObservacionAccionCreada.longValue());
                    logger.warning("idObservacionCreada = " + idObservacionCreada);
                    accionesBean.setIdObservacion(idObservacionCreada);
                    logger.warning("idObservacionCreada en el bean = " + accionesBean.getIdObservacion());
                }
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(getObservacionesListViewUIC());
                AdfFacesContext.getCurrentInstance().addPartialTarget(getObservacionInputUIC());
            
            }else{
                logger.warning("idObservacion es NULL, ocurrio un error al guardar la Observacion.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_OBSERVACION_MSG"));
            }
        }

            
            
        }catch(Exception e){
            logger.warning("Error al invocar operationBinding", e);
        }
        
        logger.warning("Termina metodo guardarObservacionesAccion");
    }

    public void setObservacionesListViewUIC(RichListView observacionesListViewUIC) {
        this.observacionesListViewUIC = observacionesListViewUIC;
    }

    public RichListView getObservacionesListViewUIC() {
        return observacionesListViewUIC;
    }

    public void setObservacionInputUIC(RichInputText observacionInputUIC) {
        this.observacionInputUIC = observacionInputUIC;
    }

    public RichInputText getObservacionInputUIC() {
        return observacionInputUIC;
    }

    public void cerrarPopupDetalle(DialogEvent dialogEvent) {
        logger.warning("Inica metodo cerrarPopupDetalle");
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
            logger.warning("Entro a evento OK");
            if(null != accionesBean){
                logger.warning("Seteando idObservacion a NULL");
                accionesBean.setIdObservacion(null);
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(getAccionesTablaUIC());
        logger.warning("Termina metodo cerrarPopupDetalle");
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
    
    public Boolean buscarRegistroEstadoAccion(Long idAccion){
        logger.warning("Inicia metodo buscarRegistroEstadoAccion");
        logger.warning("idAccion: " + idAccion);
        Boolean resultado = Boolean.FALSE;
        
        try{
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("obtenerEstadoActualAccion");
            //operBinding.getParamsMap().put("idAccion", idAccion);
            resultado = (Boolean) operBinding.execute();
            
            if(!operBinding.getErrors().isEmpty()){
                logger.warning("OperationBinding devuelve errores");
            }
        }catch(Exception e){
            logger.warning("Error al ejecutar el operationBinding: ", e);
        }
        
        logger.warning("Termina metodo buscarRegistroEstadoAccion");
        return resultado;
    }
    
    public Boolean actualizarEstadoSeguimientoAccion(){
        logger.warning("Inicia metodo actualizarEstadoSeguimientoAccion");
        
        Boolean resultado = Boolean.FALSE;
        
        try{
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("actualizarEstatusEstadoAnterior");
            resultado = (Boolean) operBinding.execute();
            
            if(!operBinding.getErrors().isEmpty()){
                logger.warning("OperationBinding devuelve errores");
            }
        }catch(Exception e){
            logger.warning("Error al ejecutar el operationBinding: ", e);
        }
        
        logger.warning("Termina metodo actualizarEstadoSeguimientoAccion");
        return resultado;
    }
    
    public Boolean obtenerRegistroAccion(){
        logger.warning("Inicia metodo obtenerRegistroAccion");
        Boolean resultado = Boolean.FALSE;
        
        try{
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("obtenerRegistroAccion");
            resultado = (Boolean) operBinding.execute();
            
            if(!operBinding.getErrors().isEmpty()){
                logger.warning("OperationBinding devuelve errores");
            }
        }catch(Exception e){
            logger.warning("Error al ejecutar el operationBinding: ", e);
        }
        
        logger.warning("Termina metodo obtenerRegistroAccion");
        return resultado;
    }
    
    public Boolean actualizarEstadoAccion(Integer idEstadoAccion){
        logger.warning("Inicia metodo actualizarEstadoAccion");
        Boolean resultado = Boolean.FALSE;
        
        try{
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("actualizarEstadoAccion");
            operBinding.getParamsMap().put("idEstadoAccion", idEstadoAccion);
            resultado = (Boolean) operBinding.execute();
            
            if(!operBinding.getErrors().isEmpty()){
                logger.warning("OperationBinding devuelve errores");
            }
        }catch(Exception e){
            logger.warning("Error al ejecutar el operationBinding: ", e);
        }
        
        logger.warning("Termina metodo actualizarEstadoAccion");
        return resultado;
    }

    public void desestimarAccion(ActionEvent actionEvent) {
        logger.warning("Inicia metodo desestimarAccion");
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
        Boolean resultado = Boolean.FALSE;        
        Long idAccion = new Long(ADFUtils.getBoundAttributeValue("Id1").toString());
        logger.warning("IdAcciones: " + idAccion);

        if(null != accionesBean){
            if(null != accionesBean.getIdObservacion()){
                resultado = obtenerRegistroAccion();
                
                if(resultado){
                    resultado = actualizarEstadoAccion(FenixConstants.TCA_ESTADO_ACCION_DESESTIMADA);
                    
                    if(resultado){
                        resultado = buscarRegistroEstadoAccion(idAccion);
                        
                        if(resultado){            
                            resultado = actualizarEstadoSeguimientoAccion();
                            
                            if(resultado){
                                    logger.warning("Recuperando idAccionEstado1 con valor: " + accionesBean.getEstadoAccion());
                                    resultado = guardarSeguimientoAccion(FenixConstants.TCA_ESTADO_ACCION_DESESTIMADA, accionesBean.getEstadoAccion(), null, accionesBean.getRolBPM());
                                    
                                    if(resultado){
                                        logger.warning("Se han registrado los cambios correctamente");
                                        accionesBean.setIdObservacion(null);
                                        try{
                                            logger.warning("Ejecutando query para cargar AccionesTablaVO");
                                            
                                            resultado = refrescarTablaAcciones(idAccion);                                                                                        
                                            if(resultado){
                                                logger.warning("La tabla se ha actualizado correctamente");
                                            }else{
                                                logger.warning("Error al refrescar y recuperar row de AccionesTablaVO");
                                            }
                                            
                                            AdfFacesContext.getCurrentInstance().addPartialTarget(getAccionesTablaUIC());
                                        }catch(Exception e){
                                            logger.warning("Error al ejecutar el query de AccionesTablaVO");
                                        }
                                    }else{
                                        logger.warning("Error al actualizar la creacion de nuevo seguimientoAccion");
                                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                    }                
                            } else {
                                logger.warning("Error al actualizar el estado anterior");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                            }
                        } else {
                            logger.warning("Error al obtener el row de Seguimiento accion");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                        }
                    }else{
                        logger.warning("Error al actualizar el estado de la accion");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                    }
                } else {
                    logger.warning("Error al obtener el row de accion");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                }
            }else{
                logger.warning("Observación no ingresada.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("AGREGAR_OBSERVACION_MSG"));
            }
        } else {
            logger.warning("El bean es NULL");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
        }
        
        logger.warning("Termina metodo desestimarAccion");
    }
    
    private int tableCustomProcessQueryExecutions = 0;
    
    /**
     * method to find the rows that contained the criteria
     * @param queryEvent event which triggers the method
     */
    public void tableCustomProcessQuery(QueryEvent queryEvent) {
        tableCustomProcessQueryExecutions++;
        logger.warning("inside tableCustomProcessQuery.  Numero de ejecucion: "+tableCustomProcessQueryExecutions);
        
        FilterableQueryDescriptor fqd = (FilterableQueryDescriptor) queryEvent.getDescriptor();
        
        ConjunctionCriterion conjunctionCriterion = fqd.getFilterConjunctionCriterion();
        List<Criterion> criterionList = conjunctionCriterion.getCriterionList();
        logger.warning("primer for.  Numero de ejecucion: "+tableCustomProcessQueryExecutions);
        for (Criterion criterion : criterionList) {
            AttributeDescriptor attrDescriptor = ((AttributeCriterion) criterion).getAttribute();
            Object value = ((AttributeCriterion) criterion).getValue();
            
            if (attrDescriptor.getType().equals(String.class)) {
                if (value != null) {
                    logger.warning("Filtro Accion: " + attrDescriptor.getName());
                    logger.warning("Valor texto accion: " + ((AttributeCriterion) criterion).getValue());
                    ((AttributeCriterion) criterion).setValue("%" + value + "%");
                }
            }
            
            if (attrDescriptor.getName().equals("IdEstadoAccion")) {
                if (value != null) {
                    logger.warning("Filtro IdEstadoAccion: " + attrDescriptor.getName()+".  Numero de ejecucion: "+tableCustomProcessQueryExecutions);
                    Integer aux = null;
                    if (value instanceof String && ((String)value).trim().length()>0 ) {
                        aux = Integer.valueOf((String)value)-1;
                    } else if (value instanceof Integer) {
                        aux = (Integer)value - 1;
                    } else {
                        aux = 0;
                    }
                    
                    //Integer aux = (Integer)value;
                    ((AttributeCriterion) criterion).setValue(aux);
                    
                    logger.warning("value: " + ((AttributeCriterion) criterion).getValue());
                } else {
                    logger.warning("Null IdEstadoAccion.  Numero de ejecucion: "+tableCustomProcessQueryExecutions);
                }
            }
            
            if (attrDescriptor.getName().equals("FechaAtencion")) {
                if (value != null) {
                    logger.warning("Filtro FechaAtencion: " + attrDescriptor.getName());
                    asignarQueryRagnoFecha();
                } else {
                    logger.warning("Null FechaAtencion");
                }
            }
            
            if (attrDescriptor.getName().equals("IdTcaCategoriaAccion")) {
                if (value != null) {
                    logger.warning("Filtro IdTcaCategoriaAccion: " + attrDescriptor.getName());
                    
                    logger.warning("valor a filtrar en Categoria: " + value);
                    logger.warning("Class: " + value.getClass());
                    
                    //Valida si value es String convertir a Integer
                    if(value.getClass().equals(String.class)) {
                        logger.warning("Convertir String: " + value + " a Integer");
                        value = Integer.parseInt(value.toString().trim());
                        logger.warning("Tipo de dato value: " + value.getClass());
                    }
                    
                    ((AttributeCriterion) criterion).setValue((Integer)value - 1);
                    logger.warning("Valor texto accion: " + ((AttributeCriterion) criterion).getValue() + 1);
                    logger.warning("value:" + ((AttributeCriterion) criterion).getValue());
                } else {
                    logger.warning("Null IdTcaCategoriaAccion");
                }
            }
        }
        
        invokeEL("#{bindings.AccionesTablaVOQuery.processQuery}", new Class[] { QueryEvent.class },  new Object[] { queryEvent });
        
        logger.warning("segundo for.  Numero de ejecucion: "+tableCustomProcessQueryExecutions);
        for (Criterion criterion : criterionList) {
            AttributeDescriptor attrDescriptor = ((AttributeCriterion) criterion).getAttribute();
            Object value = ((AttributeCriterion) criterion).getValue();
            if (attrDescriptor.getType().equals(String.class)) {
                if (value != null) {
                    ((AttributeCriterion) criterion).setValue(value.toString().replace("%", ""));
                }
            }
            
            if (attrDescriptor.getName().equals("IdEstadoAccion")) {
                if (value != null) {
                    //Integer aux = (Integer)value + 1;
                    Integer aux = null;
                    if (value instanceof String && ((String)value).trim().length()>0) {
                        aux = Integer.valueOf((String)value);
                    } else if (value instanceof Integer) {
                        //aux = (Integer)value - 1;
                        aux = (Integer)value;
                    } else {
                        aux = 0;
                    }
                    ((AttributeCriterion) criterion).setValue(aux);
                    logger.warning("value: " + ((AttributeCriterion) criterion).getValue()+".  Numero de ejecucion: "+tableCustomProcessQueryExecutions);
                } 
            }
            
            if (attrDescriptor.getName().equals("IdTcaCategoriaAccion")) {
                if (value != null) {
                    ((AttributeCriterion) criterion).setValue((Integer)value + 1);
                    logger.warning("value:" + ((AttributeCriterion) criterion).getValue());
                }
            }
        }
    }

    public void setTableCustomProcessQueryExecutions(int tableCustomProcessQueryExecutions) {
        this.tableCustomProcessQueryExecutions = tableCustomProcessQueryExecutions;
    }

    public int getTableCustomProcessQueryExecutions() {
        return tableCustomProcessQueryExecutions;
    }

    public static Object invokeEL(String el, Class[] paramTypes, Object[] params) {
        logger.warning("inside invokeEL");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(elContext, el, Object.class, paramTypes);
        
        return exp.invoke(elContext, params);
    }
    
    public void asignarQueryRagnoFecha() {
        logger.warning("inside asignarQueryRagnoFecha");
        
        BindingContainer bindings = getBindings();
    
        AttributeBinding startDate = (AttributeBinding) bindings.getControlBinding("attrStartDate");
        java.sql.Timestamp sd = (java.sql.Timestamp) startDate.getInputValue();
        AttributeBinding endDate = (AttributeBinding) bindings.getControlBinding("attrEndDate");
        java.sql.Timestamp ed = (java.sql.Timestamp) endDate.getInputValue();
        
        logger.warning("Fecha inicio: " + sd);
        logger.warning("Fecha fin: " + ed);
        
        if (validaFechaInicio(sd, ed)) {
            BindingContainer bindingsMethod = getBindings();
            
            OperationBinding operationBinding = bindingsMethod.getOperationBinding("filtrarPorRangoFecha");
            operationBinding.getParamsMap().put("fechaInicio", sd);
            operationBinding.getParamsMap().put("fechaFin", ed);

            operationBinding.execute();    
        } 
    }
    
    public void filtrarAccionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("inside filtrarAccionValueChangeListener");
        logger.warning("Valor actual: " + valueChangeEvent.getNewValue());
        
        activateFilter();
    }
    
    private Integer valorSelleccionadoInt;

    public void setValorSelleccionadoInt(Integer valSelleccionadoInt) {
        logger.warning("Asignado valor a setValorSelleccionadoInt: "+valSelleccionadoInt);
        this.valorSelleccionadoInt = valSelleccionadoInt;
    }

    public Integer getValorSelleccionadoInt() {
        return valorSelleccionadoInt;
    }

    public void filtrarAccionPorTipoValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("inside filtrarAccionPorTipoValueChangeListener");
        logger.warning("Valor nuevo: " + valueChangeEvent.getNewValue());
        logger.warning("Tipo del valor nuevo: " + valueChangeEvent.getNewValue().getClass());
        
        logger.warning("Valor anterior: " + getValorSelleccionadoInt());
        
        int valorRecibido=0;
        if (valueChangeEvent.getNewValue() instanceof String && ((String)valueChangeEvent.getNewValue()).trim().length()>0 ) {
            valorRecibido = Integer.valueOf((String)valueChangeEvent.getNewValue()).intValue();
            if (getValorSelleccionadoInt()!= null && getValorSelleccionadoInt().intValue() == valorRecibido ) {
                logger.warning("El valor nuevo es string e igual al anterior");
                setValorSelleccionadoInt(valorRecibido);
                return;
            }
            setValorSelleccionadoInt(valorRecibido);
        } else if (valueChangeEvent.getNewValue() instanceof Integer) {
            valorRecibido = ((Integer)valueChangeEvent.getNewValue()).intValue();
            if (getValorSelleccionadoInt()!= null && getValorSelleccionadoInt().intValue() == valorRecibido) {
                logger.warning("El valor nuevo es integer e igual al anterior");
                setValorSelleccionadoInt(valorRecibido);
                return;
            }
            setValorSelleccionadoInt(valorRecibido);
        } else if (valueChangeEvent.getNewValue() instanceof String && 
                    ((String)valueChangeEvent.getNewValue()).trim().length()== 0 && 
                       getValorSelleccionadoInt() != null && getValorSelleccionadoInt() != 0 ) {
            logger.warning("El valor anterior se conserva ");
            return;
        }
        
        //activateFilterTipoAccion((Integer)valueChangeEvent.getNewValue());
        activateFilterIdTipoAccion();
    }
    
    public void filtrarFechaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("inside filtrarFechaValueChangeListener");
        logger.warning("Valor actual: " + valueChangeEvent.getNewValue());
        
        Boolean validaFecha = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        
        java.sql.Timestamp sd = (java.sql.Timestamp) valueChangeEvent.getNewValue();
        AttributeBinding endDate = (AttributeBinding) bindings.getControlBinding("attrEndDate");
        java.sql.Timestamp ed = (java.sql.Timestamp) endDate.getInputValue();
        validaFecha = validaFechaInicio(sd, ed);
        logger.warning("validaFecha; " + validaFecha);
        if (validaFecha) {
            activateFilter();
        } else {
            JSFUtils.addFacesErrorMessage("La fecha ingresada en el campo \"A\" debe ser mayor a la fecha ingresada en el campo \"De\".");
        }
    }
    
    public void filtrarFechaFinValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("inside filtrarFechaFinValueChangeListener");
        logger.warning("Valor actual: " + valueChangeEvent.getNewValue());
        
        Boolean validaFecha = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        
        AttributeBinding startDate = (AttributeBinding) bindings.getControlBinding("attrStartDate");
        java.sql.Timestamp sd = (java.sql.Timestamp) startDate.getInputValue();
        java.sql.Timestamp ed = (java.sql.Timestamp) valueChangeEvent.getNewValue();
        validaFecha = validaFechaInicio(sd, ed);
        logger.warning("validaFecha; " + validaFecha);
        if (validaFecha) {
            activateFilter();
        } else {
            JSFUtils.addFacesErrorMessage("La fecha ingresada en el campo \"A\" debe ser mayor a la fecha ingresada en el campo \"De\".");
        }
    }
    
    public Boolean validaFechaInicio(java.sql.Timestamp startDate,java.sql.Timestamp endDate) {
        logger.warning("inside filtrarFecha");
        logger.warning("Fecha inicial: " + startDate);
        logger.warning("Fecha final: " + endDate);
        Boolean result = Boolean.FALSE;
        
        if (startDate != null && endDate != null) {
            if (startDate.before(endDate)) {
                logger.warning("Fecha inicial menor que Fecha final.");
                result = Boolean.TRUE;
            }
            
            if (startDate.equals(endDate)) {
                logger.warning("Fecha inicial igual que Fecha final.");
                result = Boolean.TRUE;
            }    
        } 
        
        if (startDate != null && endDate == null) {
            logger.warning("Fecha inicial diferente de null.");
            result = Boolean.TRUE;
        }
        
        if (startDate == null && endDate == null) {
            logger.warning("Fecha nulas.");
            result = Boolean.TRUE;
        }
        
        return (result);
    }
    
    public void activateFilterIdTipoAccion() {
        logger.warning("inside activateFilterIdTipoAccion");
        FilterableQueryDescriptor queryDescriptor = (FilterableQueryDescriptor) getAccionesTablaUIC().getFilterModel();
        tableCustomProcessQuery(new QueryEvent(getAccionesTablaUIC(), queryDescriptor));
    }
    
    public void activateFilter() {
        logger.warning("inside activateFilter");
        FilterableQueryDescriptor queryDescriptor = (FilterableQueryDescriptor) getAccionesTablaUIC().getFilterModel();
        tableCustomProcessQuery(new QueryEvent(getAccionesTablaUIC(), queryDescriptor));
    }
    
    /*
     * Nuevo método de filtrar acciones
     */
    public void activateFilterTipoAccion(Integer opcion) {
        logger.warning("inside activateFilterTipoAccion");
        
        BindingContainer bindings = getBindings();
        
        AttributeBinding startDate = (AttributeBinding) bindings.getControlBinding("attrStartDate");
        java.sql.Timestamp sd = (java.sql.Timestamp) startDate.getInputValue();
        AttributeBinding endDate = (AttributeBinding) bindings.getControlBinding("attrEndDate");
        java.sql.Timestamp ed = (java.sql.Timestamp) endDate.getInputValue();
        
        FilterableQueryDescriptor queryDescriptor = (FilterableQueryDescriptor) getAccionesTablaUIC().getFilterModel();

        if (queryDescriptor != null && queryDescriptor.getFilterConjunctionCriterion() != null) {
            logger.warning("Primera Iteracion");
            ConjunctionCriterion cc = queryDescriptor.getFilterConjunctionCriterion();
            List<Criterion> lc = cc.getCriterionList();
            for (Criterion c : lc) {
                AttributeDescriptor attrDescriptor = ((AttributeCriterion) c).getAttribute();
                Object value = ((AttributeCriterion) c).getValue();

                if (attrDescriptor.getName().equals("IdEstadoAccion")) {
                    if (value != null) {
                        logger.warning("Filtro IdEstadoAccion: " + attrDescriptor.getName());
                        Integer aux = opcion;
  
                        ((AttributeCriterion) c).setValue(aux);
                        
                        logger.warning("value:" + ((AttributeCriterion) c).getValue());
                    } else {
                        logger.warning("Null IdEstadoAccion");
                        Integer aux = opcion;
                        ((AttributeCriterion) c).setValue(aux);
                        logger.warning("value:" + ((AttributeCriterion) c).getValue());
                    }
                }
            }
            // aplicación de filtros
            getAccionesTablaUIC().queueEvent(new QueryEvent(getAccionesTablaUIC(), queryDescriptor));

            /*
            FilterableQueryDescriptor queryDescriptorAux = (FilterableQueryDescriptor) getAccionesTablaUIC().getFilterModel();
            
            if (queryDescriptorAux != null && queryDescriptorAux.getFilterConjunctionCriterion() != null) {
                logger.warning("Segunda Iteracion");
                
                ConjunctionCriterion ccAux = queryDescriptorAux.getFilterConjunctionCriterion();
                List<Criterion> lcAux = ccAux.getCriterionList();
                
                for (Criterion cAux : lcAux) {
                    
                    AttributeDescriptor attrDescriptor = ((AttributeCriterion) cAux).getAttribute();
                    Object value = ((AttributeCriterion) cAux).getValue();
                    
                    if (attrDescriptor.getName().equals("IdEstadoAccion")) {
                        if (value != null) {
                            logger.warning("Filtro IdEstadoAccion: " + attrDescriptor.getName());
                            Integer aux = (Integer)value + 1;
                                     
                            ((AttributeCriterion) cAux).setValue(aux);
                            
                            logger.warning("value:" + ((AttributeCriterion) cAux).getValue());
                        }
                    }
                }
            }
            */
        }
    }
    
    /**
     * method to reset filter attributes on an af:table
     * @param actionEvent event which triggers the method
     */
    public void resetTableFilter(ActionEvent actionEvent) {
        logger.warning("inside resetTableFilter");
        
        tableCustomProcessQueryExecutions = 0;
        
        BindingContainer bindings = getBindings();
        
        AttributeBinding startDate = (AttributeBinding) bindings.getControlBinding("attrStartDate");
        java.sql.Timestamp sd = (java.sql.Timestamp) startDate.getInputValue();
        AttributeBinding endDate = (AttributeBinding) bindings.getControlBinding("attrEndDate");
        java.sql.Timestamp ed = (java.sql.Timestamp) endDate.getInputValue();
        
        FilterableQueryDescriptor queryDescriptor = (FilterableQueryDescriptor) getAccionesTablaUIC().getFilterModel();
        if (queryDescriptor != null && queryDescriptor.getFilterConjunctionCriterion() != null || (sd != null && ed != null)) {
            ConjunctionCriterion cc = queryDescriptor.getFilterConjunctionCriterion();
            List<Criterion> lc = cc.getCriterionList();
            for (Criterion c : lc) {
                if (c instanceof AttributeCriterion) {
                    AttributeCriterion ac = (AttributeCriterion) c;
                    ac.setValue(null);
                }
                if (startDate.getInputValue() != null || endDate.getInputValue() != null) {
                    startDate.setInputValue(null);
                    endDate.setInputValue(null);       
                }
            }
            setValorSelleccionadoInt(null);
            getAccionesTablaUIC().queueEvent(new QueryEvent(getAccionesTablaUIC(), queryDescriptor));
        }
    }

    public void setAccionesTablaUIC(RichTable accionesTablaUIC) {
        this.accionesTablaUIC = accionesTablaUIC;
    }

    public RichTable getAccionesTablaUIC() {
        return accionesTablaUIC;
    }
    public Boolean validarAcciones(){
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
        Boolean resultado=Boolean.TRUE;
            accionesBean.setCatalogo4(Boolean.FALSE);
            accionesBean.setCatalogo5(Boolean.FALSE);
            accionesBean.setCatalogo3(Boolean.FALSE);
            accionesBean.setCatalogo6(Boolean.FALSE);

            AttributeBinding fechaAtencion;
            fechaAtencion = ADFUtils.findControlBinding("FechaAtencion");

            AttributeBinding descripcionAccion;
            descripcionAccion = ADFUtils.findControlBinding("Accion");
                                                                          
            AttributeBinding idrol;
            idrol = ADFUtils.findControlBinding("IdTcaRolAsignado1");
            
            if(null==idrol.getInputValue()){
                    accionesBean.setCatalogo4(Boolean.TRUE);
                    resultado=Boolean.FALSE;
                }

            if(null==fechaAtencion.getInputValue()){
                    accionesBean.setCatalogo4(Boolean.TRUE);
                    resultado=Boolean.FALSE;
                   // JSFUtils.addFacesErrorMessage("Falta dato de la fecha de atención sugerida");
                }
            if(null==descripcionAccion.getInputValue()){
                    accionesBean.setCatalogo4(Boolean.TRUE);
                    resultado=Boolean.FALSE;
                   // JSFUtils.addFacesErrorMessage("Falta dato de la acción");
                }
            else{
                /*
                if(((String)descripcionAccion.getInputValue()).length()<=0 || ((String)descripcionAccion.getInputValue()).equalsIgnoreCase("")){
                        accionesBean.setCatalogo4(Boolean.TRUE);
                        resultado=Boolean.FALSE;
                    }
                    if(((String)descripcionAccion.getInputValue()).length()>4000){
                        accionesBean.setCatalogo5(Boolean.TRUE);
                        resultado=Boolean.FALSE;
                    }*/
                }
        return resultado;
        }
    public void cerrarPopupDetalleAccion(DialogEvent dialogEvent) {
        logger.warning("Inica metodo cerrarPopupDetalle");
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
            logger.warning("Entro a evento CANCELAR");
            if(null != accionesBean){
                logger.warning("Seteando idObservacion a NULL");
                accionesBean.setIdObservacion(null);
            }
        }
        logger.warning("Termina metodo cerrarPopupDetalle");
    }

    public void eliminarAccion(ActionEvent actionEvent) {
        logger.warning("Inicia metodo eliminarAccion");
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
        Boolean resultado = Boolean.FALSE;        
        Long idAccion = new Long(ADFUtils.getBoundAttributeValue("Id1").toString());
        logger.warning("IdAcciones: " + idAccion);

        if(null != accionesBean){
            if(null != accionesBean.getIdObservacion()){
                resultado = obtenerRegistroAccion();
                
                if(resultado){
                    resultado = actualizarEstadoAccion(FenixConstants.TCA_ESTADO_ACCION_ELIMINADA);
                    
                    if(resultado){
                        resultado = buscarRegistroEstadoAccion(idAccion);
                        
                        if(resultado){            
                            resultado = actualizarEstadoSeguimientoAccion();
                            
                            if(resultado){
                                    logger.warning("Recuperando idAccionEstado1 con valor: " + accionesBean.getEstadoAccion());
                                    resultado = guardarSeguimientoAccion(FenixConstants.TCA_ESTADO_ACCION_ELIMINADA, accionesBean.getEstadoAccion(), null, accionesBean.getRolBPM());
                                    
                                    if(resultado){
                                        logger.warning("Se han registrado los cambios correctamente");
                                        accionesBean.setIdObservacion(null);
                                        try{
                                            logger.warning("Ejecutando query para cargar AccionesTablaVO");
                                            
                                            resultado = refrescarTablaAcciones(idAccion);                                                                                        
                                            if(resultado){
                                                logger.warning("La tabla se ha actualizado correctamente");
                                            }else{
                                                logger.warning("Error al refrescar y recuperar row de AccionesTablaVO");
                                            }
                                            
                                            AdfFacesContext.getCurrentInstance().addPartialTarget(getAccionesTablaUIC());
                                        }catch(Exception e){
                                            logger.warning("Error al ejecutar el query de AccionesTablaVO");
                                        }
                                    }else{
                                        logger.warning("Error al actualizar la creacion de nuevo seguimientoAccion");
                                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                    }                
                            } else {
                                logger.warning("Error al actualizar el estado anterior");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                            }
                        } else {
                            logger.warning("Error al obtener el row de Seguimiento accion");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                        }
                    }else{
                        logger.warning("Error al actualizar el estado de la accion");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                    }
                } else {
                    logger.warning("Error al obtener el row de accion");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                }
            }else{
                logger.warning("Observación no ingresada.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("AGREGAR_OBSERVACION_MSG"));
            }
        } else {
            logger.warning("El bean es NULL");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
        }
        
        logger.warning("Termina metodo eliminarAccion");
    }

    public void activarAccion(ActionEvent actionEvent) {
        logger.warning("Inicia metodo activarAccion");
        AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
        Boolean resultado = Boolean.FALSE;        
        Long idAccion = new Long(ADFUtils.getBoundAttributeValue("Id1").toString());
        logger.warning("IdAcciones: " + idAccion);

        if(null != accionesBean){
            if(null != accionesBean.getIdObservacion()){
                resultado = obtenerRegistroAccion();
                
                if(resultado){
                    resultado = buscarRegistroEstadoAccion(idAccion);
                    
                    if(resultado){
                        Integer idEstadoAnterior = null;
                        
                        try{    
                            Row currentRow = this.getIteratorCurrentRow("SeguimientoAccionVOIterator");
                            idEstadoAnterior = Integer.parseInt(currentRow.getAttribute("IdTcaEstadoAnterior").toString());
                            logger.warning("idEstadoAnterior: " + idEstadoAnterior);
                            
                        }catch(Exception e){
                            logger.warning("Ocurrio un error al obtener el iterator de SeguimientoAccionVO");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                            return;
                        }
                        
                        resultado = actualizarEstadoAccion(idEstadoAnterior);
                        
                        if(resultado){            
                            resultado = actualizarEstadoSeguimientoAccion();
                            
                            if(resultado){
                                    logger.warning("Recuperando idAccionEstado1 con valor: " + accionesBean.getEstadoAccion());
                                    resultado = guardarSeguimientoAccion(idEstadoAnterior, accionesBean.getEstadoAccion(), null, accionesBean.getRolBPM());
                                    
                                    if(resultado){
                                        logger.warning("Se han registrado los cambios correctamente");
                                        accionesBean.setIdObservacion(null);
                                        try{
                                            logger.warning("Ejecutando query para cargar AccionesTablaVO");
                                            
                                            resultado = refrescarTablaAcciones(idAccion);                                                                                        
                                            if(resultado){
                                                logger.warning("La tabla se ha actualizado correctamente");
                                            }else{
                                                logger.warning("Error al refrescar y recuperar row de AccionesTablaVO");
                                            }
                                            
                                            AdfFacesContext.getCurrentInstance().addPartialTarget(getAccionesTablaUIC());
                                        }catch(Exception e){
                                            logger.warning("Error al ejecutar el query de AccionesTablaVO");
                                        }
                                    }else{
                                        logger.warning("Error al actualizar la creacion de nuevo seguimientoAccion");
                                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                    }                
                            } else {
                                logger.warning("Error al actualizar el estado anterior");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                            }
                        } else {
                            logger.warning("Error al actualizar el estado de la accion");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                        }
                    }else{
                        logger.warning("Error al obtener el row de Seguimiento accion");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                    }
                } else {
                    logger.warning("Error al obtener el row de accion");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                }
            }else{
                logger.warning("Observación no ingresada.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("AGREGAR_OBSERVACION_MSG"));
            }
        } else {
            logger.warning("El bean es NULL");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
        }
        
        logger.warning("Termina metodo activarAccion");
    }
    
    public Boolean refrescarTablaAcciones(Long idAccion){
        logger.warning("Inicia método refrescarTablaAcciones");
        Boolean resultado = Boolean.FALSE;
        
        DCIteratorBinding accionesTablaVOIterator = null;
        accionesTablaVOIterator = ADFUtils.findIterator("AccionesTablaVOIterator");
        accionesTablaVOIterator.executeQuery();
        
        
        if(accionesTablaVOIterator.getEstimatedRowCount() > 0){
            Row[] rows = accionesTablaVOIterator.getAllRowsInRange();
            for(Row row : rows){
                if(row.getAttribute("Id") != null){
                    Long idRow = (Long)row.getAttribute("Id");
                    if(idRow != null &&
                       idRow.equals(idAccion)){
                        accionesTablaVOIterator.setCurrentRowWithKey(row.getKey().toStringFormat(true));
                        resultado = Boolean.TRUE;
                        break;
                    }else{
                        logger.warning("IdRow no coincide con row de tabla");
                    }
                }else{
                    logger.warning("El atributo Id de row es NULL");
                }
            }
        }else{
            logger.warning("Los row de AccionesTablaVOIterator es 0");
        }
        
        logger.warning("Inicia método refrescarTablaAcciones");
        return resultado;
    }
    
    private Row getIteratorCurrentRow(String psIteratorName)
    {
      DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator(psIteratorName);
      ViewObject voTableData = dcItteratorBindings.getViewObject();
      return voTableData.getCurrentRow();
    }
    
    public void darComoAtendidaAccion(ActionEvent actionEvent) {
            logger.warning("Inicia metodo darComoAtendidaAccion");
            AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
            Boolean resultado = Boolean.FALSE;        
            Long idAccion = new Long(ADFUtils.getBoundAttributeValue("Id1").toString());
            logger.warning("IdAcciones: " + idAccion);
            
            AttributeBinding rolAsignado;
            rolAsignado = ADFUtils.findControlBinding("IdTcaRolAsignado");
            Integer rolUsuarioAsignado=(Integer)rolAsignado.getInputValue();

            if(null != accionesBean){
                if(null != accionesBean.getIdObservacion()){
                    resultado = obtenerRegistroAccion();
                    
                    if(resultado){
                        resultado = actualizarEstadoAccion(FenixConstants.TCA_ESTADO_ACCION_ATENDIDA);
                        
                        if(resultado){
                            resultado = buscarRegistroEstadoAccion(idAccion);
                            
                            if(resultado){            
                                resultado = actualizarEstadoSeguimientoAccion();
                                
                                if(resultado){
                                        logger.warning("Recuperando idAccionEstado1 con valor: " + accionesBean.getEstadoAccion());
                                        if(null==accionesBean.getRolBPM()){
                                                resultado = guardarSeguimientoAccion(FenixConstants.TCA_ESTADO_ACCION_ATENDIDA, accionesBean.getEstadoAccion(), null, rolUsuarioAsignado);
                                            }
                                        else{
                                                resultado = guardarSeguimientoAccion(FenixConstants.TCA_ESTADO_ACCION_ATENDIDA, accionesBean.getEstadoAccion(), null, accionesBean.getRolBPM());
                                            }
                                                                               
                                        if(resultado){
                                            logger.warning("Se han registrado los cambios correctamente");
                                            accionesBean.setIdObservacion(null);
                                        try{
                                            logger.warning("Ejecutando query para cargar AccionesTablaVO");
                                            
                                            resultado = refrescarTablaAcciones(idAccion);                                                                                        
                                            if(resultado){
                                                logger.warning("La tabla se ha actualizado correctamente");
                                            }else{
                                                logger.warning("Error al refrescar y recuperar row de AccionesTablaVO");
                                            }
                                            
                                            AdfFacesContext.getCurrentInstance().addPartialTarget(getAccionesTablaUIC());
                                        }catch(Exception e){
                                            logger.warning("Error al ejecutar el query de AccionesTablaVO");
                                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                        }
                                        }else{
                                            logger.warning("Error al actualizar la creacion de nuevo seguimientoAccion");
                                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                        }                
                                } else {
                                    logger.warning("Error al actualizar el estado anterior");
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                }
                            } else {
                                logger.warning("Error al obtener el row de Seguimiento accion");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                            }
                        }else{
                            logger.warning("Error al actualizar el estado de la accion");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                        }
                    } else {
                        logger.warning("Error al obtener el row de accion");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                    }
                }else{
                    logger.warning("Observación no ingresada.");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("AGREGAR_OBSERVACION_MSG"));
                }
            } else {
                logger.warning("El bean es NULL");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
            }
            logger.warning("Termina metodo darComoAtendidaAccion");
        }

        public void accionValidar(ActionEvent actionEvent) {
            logger.warning("Inicia metodo accionValidar");
            AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
            Boolean resultado = Boolean.FALSE;        
            Long idAccion = new Long(ADFUtils.getBoundAttributeValue("Id1").toString());
            logger.warning("IdAcciones: " + idAccion);

            if(null != accionesBean){
                if(null != accionesBean.getIdObservacion()){
                    resultado = obtenerRegistroAccion();
                    
                    if(resultado){
                        resultado = actualizarEstadoAccion(FenixConstants.TCA_ESTADO_ACCION_VALIDADA);
                        
                        if(resultado){
                            resultado = buscarRegistroEstadoAccion(idAccion);
                            
                            if(resultado){            
                                resultado = actualizarEstadoSeguimientoAccion();
                                
                                if(resultado){
                                        logger.warning("Recuperando idAccionEstado1 con valor: " + accionesBean.getEstadoAccion());
                                        resultado = guardarSeguimientoAccion(FenixConstants.TCA_ESTADO_ACCION_VALIDADA, accionesBean.getEstadoAccion(), null, accionesBean.getRolBPM());
                                        
                                        if(resultado){
                                            logger.warning("Se han registrado los cambios correctamente");
                                            accionesBean.setIdObservacion(null);
                                        try{
                                            logger.warning("Ejecutando query para cargar AccionesTablaVO");
                                            
                                            resultado = refrescarTablaAcciones(idAccion);                                                                                        
                                            if(resultado){
                                                logger.warning("La tabla se ha actualizado correctamente");
                                            }else{
                                                logger.warning("Error al refrescar y recuperar row de AccionesTablaVO");
                                            }
                                            
                                            AdfFacesContext.getCurrentInstance().addPartialTarget(getAccionesTablaUIC());
                                        }catch(Exception e){
                                            logger.warning("Error al ejecutar el query de AccionesTablaVO");
                                        }
                                        }else{
                                            logger.warning("Error al actualizar la creacion de nuevo seguimientoAccion");
                                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                        }                
                                } else {
                                    logger.warning("Error al actualizar el estado anterior");
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                }
                            } else {
                                logger.warning("Error al obtener el row de Seguimiento accion");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                            }
                        }else{
                            logger.warning("Error al actualizar el estado de la accion");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                        }
                    } else {
                        logger.warning("Error al obtener el row de accion");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                    }
                }else{
                    logger.warning("Observación no ingresada.");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("AGREGAR_OBSERVACION_MSG"));
                }
            } else {
                logger.warning("El bean es NULL");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
            }
            logger.warning("Termina metodo accionValidar");
        }

        public void accionPorAtender(ActionEvent actionEvent) {
            logger.warning("Inicia metodo accionPorAtender");
            AccionesBean accionesBean = (AccionesBean) JSFUtils.resolveExpression("#{pageFlowScope.accionesBean}");
            Boolean resultado = Boolean.FALSE;        
            Long idAccion = new Long(ADFUtils.getBoundAttributeValue("Id1").toString());
            logger.warning("IdAcciones: " + idAccion);

            if(null != accionesBean){
                if(null != accionesBean.getIdObservacion()){
                    resultado = obtenerRegistroAccion();
                    
                    if(resultado){
                        resultado = actualizarEstadoAccion(FenixConstants.TCA_ESTADO_ACCION_POR_ATENDER);
                        
                        if(resultado){
                            resultado = buscarRegistroEstadoAccion(idAccion);
                            
                            if(resultado){            
                                resultado = actualizarEstadoSeguimientoAccion();
                                
                                if(resultado){
                                        logger.warning("Recuperando idAccionEstado1 con valor: " + accionesBean.getEstadoAccion());
                                        resultado = guardarSeguimientoAccion(FenixConstants.TCA_ESTADO_ACCION_POR_ATENDER, accionesBean.getEstadoAccion(), null, accionesBean.getRolBPM());
                                        
                                        if(resultado){
                                            logger.warning("Se han registrado los cambios correctamente");
                                            accionesBean.setIdObservacion(null);
                                        try{
                                            logger.warning("Ejecutando query para cargar AccionesTablaVO");
                                            
                                            resultado = refrescarTablaAcciones(idAccion);                                                                                        
                                            if(resultado){
                                                logger.warning("La tabla se ha actualizado correctamente");
                                            }else{
                                                logger.warning("Error al refrescar y recuperar row de AccionesTablaVO");
                                            }
                                            
                                            AdfFacesContext.getCurrentInstance().addPartialTarget(getAccionesTablaUIC());
                                        }catch(Exception e){
                                            logger.warning("Error al ejecutar el query de AccionesTablaVO");
                                        }
                                        }else{
                                            logger.warning("Error al actualizar la creacion de nuevo seguimientoAccion");
                                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                        }                
                                } else {
                                    logger.warning("Error al actualizar el estado anterior");
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                                }
                            } else {
                                logger.warning("Error al obtener el row de Seguimiento accion");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                            }
                        }else{
                            logger.warning("Error al actualizar el estado de la accion");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                        }
                    } else {
                        logger.warning("Error al obtener el row de accion");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
                    }
                }else{
                    logger.warning("Observación no ingresada.");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("AGREGAR_OBSERVACION_MSG"));
                }
            } else {
                logger.warning("El bean es NULL");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIO_MSG"));
            }
            logger.warning("Termina metodo accionPorAtender");
        }
        
    public void cambioCategoria(ValueChangeEvent valueChangeEvent) {
           logger.warning("Categoria: "+(Integer)valueChangeEvent.getNewValue());
           // Add event code here...
           valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
           AttributeBinding rolAsignado;
           rolAsignado = ADFUtils.findControlBinding("IdTcaRolAsignado1");
           rolAsignado.setInputValue(null);
       }

       public void cambioResponsable(ValueChangeEvent valueChangeEvent) {
           logger.warning("RolSeleccionado: "+(Integer)valueChangeEvent.getNewValue());
           // Add event code here...
       }
}