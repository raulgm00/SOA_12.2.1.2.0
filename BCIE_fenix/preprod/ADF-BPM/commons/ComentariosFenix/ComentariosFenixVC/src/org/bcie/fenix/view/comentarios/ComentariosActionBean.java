package org.bcie.fenix.view.comentarios;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;


import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;

import oracle.jbo.ViewObject;

import oracle.jbo.server.ViewRowSetImpl;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class ComentariosActionBean {
    
    /**
     * Define paquete y nombre de Resource Bundle
     */
    public static final String BUNDLE = "org.bcie.fenix.view.ComentariosFenixVCBundle";
    
    private static final ADFLogger logger = ADFLogger.createADFLogger(ComentariosActionBean.class);
    
    private RichInputText comentarioUIC;
    
    private RichInputText comentarioRespuestaUIC;

    private RichButton agregarComentarioUIC;
    private RichButton agregarComentarioRespUIC;
    
    private RichPopup popUpAbrir;
    
    private boolean comentarioInvalido;
    private boolean comentarioRespuestaInvalido;
    
    private RichPopup popUpAbrirComentInd;
    private RichPopup popUpAbrirComentCas;
    
    private RichButton eliminarComentarioIndUIC;
    private RichButton eliminarComentarioCasUIC;
    private RichInputText justificacionUIC;
    private RichButton agregarJustificacionUIC;
    private RichPopup popUpAbrirComentAgr;

    /**
     * Constructor por defecto
     */
    public ComentariosActionBean() {
        super();   
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
    
    public void comentarioValueChangeListener(ValueChangeEvent valueChangeEvent) {

        if(valueChangeEvent == null){
            return;
        }
        
        String comentario = null;
        if(valueChangeEvent.getNewValue() != null){
            comentario = valueChangeEvent.getNewValue().toString();    
        }
        
        //validaComentarioUIC(comentario);
    }
    
    public boolean validaJustificacionUIC(String justificacion){
        
        boolean valido = validarJustificacion(justificacion);
        if(!valido){
            ComentariosBean comentariosBean = 
                (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");        
            if(comentariosBean != null){
                comentariosBean.setJustificacion(null);    
            }
            
            if(getJustificacionUIC() != null){
                AdfFacesContext.getCurrentInstance().addPartialTarget(getJustificacionUIC());    
            }
            
            JSFUtils.addFacesErrorMessage(getStringFromBundle("msg.comentario.valido"));
        }
        
        if(getJustificacionUIC() != null){
            AdfFacesContext.getCurrentInstance().addPartialTarget(getJustificacionUIC());    
        }
        
        return valido;
    }
    
    public boolean validaComentarioUIC(String comentario){
        
        boolean valido = validarComentario(comentario);
        if(!valido){
            ComentariosBean comentariosBean = 
                (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");        
            if(comentariosBean != null){
                comentariosBean.setComentario(null);    
            }
            
            if(getComentarioUIC() != null){
                AdfFacesContext.getCurrentInstance().addPartialTarget(getComentarioUIC());    
            }
            
            JSFUtils.addFacesErrorMessage(getStringFromBundle("msg.comentario.valido"));
        }
        
        if(getAgregarComentarioUIC() != null){
            AdfFacesContext.getCurrentInstance().addPartialTarget(getAgregarComentarioUIC());    
        }
        
        return valido;
    }
    
    public void comentarioRespuestaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        
        if(valueChangeEvent == null){
            return;
        }
        
        String comentario = null;
        if(valueChangeEvent.getNewValue() != null){
            comentario = valueChangeEvent.getNewValue().toString();    
        }
        
        //validaComentarioRespUIC(comentario);
    }
    
    public boolean validaComentarioRespUIC(String respuesta){
        
        boolean valido = validarComentario(respuesta);
        if(!valido){
            ComentariosBean comentariosBean = 
                (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");        
            if(comentariosBean != null){
                comentariosBean.setComentarioRespuesta(null);    
            }
            
            if(getComentarioRespuestaUIC() != null){
                AdfFacesContext.getCurrentInstance().addPartialTarget(getComentarioRespuestaUIC());    
            }
            
            JSFUtils.addFacesErrorMessage(getStringFromBundle("msg.comentario.valido"));
        }
        
        if(getAgregarComentarioRespUIC() != null){
            AdfFacesContext.getCurrentInstance().addPartialTarget(getAgregarComentarioRespUIC());    
        }
        
        return valido;
    }
    
    public boolean validarJustificacion(String justificacion){
        boolean valido = true;
        if(justificacion != null){
            String strLongitud = getStringFromBundle("comentario.longitud");
            int intLongitud = 0;
            if(strLongitud != null){
                intLongitud = Integer.parseInt(strLongitud);
            }
            
            if(justificacion != null &&
               intLongitud > 0){
                if(justificacion.length() > intLongitud){
                    valido = false;
                }else{
                    if(justificacion.trim().isEmpty()){
                        valido = false;
                    }
                }
            }
        }
        
        return valido;
    }
    
    public boolean validarComentario(String comentario){
        
        boolean valido = true;
        if(comentario != null){
            
            String strLongitud = getStringFromBundle("comentario.longitud");
            int intLongitud = 0;
            if(strLongitud != null){
                intLongitud = Integer.parseInt(strLongitud);
            }
            
            if(comentario != null &&
               intLongitud > 0){
                if(comentario.length() > intLongitud){
                    valido = false;
                }else{
                    if(comentario.trim().isEmpty()){
                        valido = false;
                    }
                }
            }
        }
        
        return valido;
    }

    public void setComentarioUIC(RichInputText comentarioUIC) {
        this.comentarioUIC = comentarioUIC;
    }

    public RichInputText getComentarioUIC() {
        return comentarioUIC;
    }

    public void setAgregarComentarioUIC(RichButton agregarComentarioUIC) {
        this.agregarComentarioUIC = agregarComentarioUIC;
    }

    public RichButton getAgregarComentarioUIC() {
        return agregarComentarioUIC;
    }
    
    public void setComentarioInvalido(boolean comentarioInvalido) {
        this.comentarioInvalido = comentarioInvalido;
    }
    
    public void setComentarioRespuestaUIC(RichInputText comentarioRespuestaUIC) {
        this.comentarioRespuestaUIC = comentarioRespuestaUIC;
    }

    public RichInputText getComentarioRespuestaUIC() {
        return comentarioRespuestaUIC;
    }

    public void setAgregarComentarioRespUIC(RichButton agregarComentarioRespUIC) {
        this.agregarComentarioRespUIC = agregarComentarioRespUIC;
    }

    public RichButton getAgregarComentarioRespUIC() {
        return agregarComentarioRespUIC;
    }
    
    public void setPopUpAbrir(RichPopup popUpAbrir) {
        this.popUpAbrir = popUpAbrir;
    }

    public RichPopup getPopUpAbrir() {
        return popUpAbrir;
    }

    public boolean isComentarioInvalido() {
        
        comentarioInvalido = true;
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        if(comentariosBean != null &&
           comentariosBean.getComentario() != null){
            if(validarComentario(comentariosBean.getComentario())){
                comentarioInvalido = false;
            }
        }
        
        return comentarioInvalido;
    }
    
    public void setComentarioRespuestaInvalido(boolean comentarioRespuestaInvalido) {
        this.comentarioRespuestaInvalido = comentarioRespuestaInvalido;
    }

    public boolean isComentarioRespuestaInvalido() {
        
        comentarioRespuestaInvalido = true;
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        if(comentariosBean != null &&
           comentariosBean.getComentarioRespuesta() != null){
            if(validarComentario(comentariosBean.getComentarioRespuesta())){
                comentarioRespuestaInvalido = false;
            }
        }
        
        return comentarioRespuestaInvalido;
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    @SuppressWarnings("unchecked")
    public void agregarComentarioActionListener(ActionEvent action) {
        
        if(action == null){
            return;
        }
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        
        if(comentariosBean != null){
            
            String comentario = comentariosBean.getComentario();
            
            boolean valido = validaComentarioUIC(comentario);
            if(valido){
                
                if(null != comentario &&  comentario.trim().length()>0)
                {
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("agregarComentario");
                    operationBinding.getParamsMap().put("loginUsuario", JSFUtils.resolveExpression("#{pageFlowScope.pIdUsuario}"));
                    operationBinding.getParamsMap().put("nombreUsusario", JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}"));
                    operationBinding.getParamsMap().put("idTcaTareaBpm", JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}"));
                    operationBinding.getParamsMap().put("idOperacion", JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
                    operationBinding.getParamsMap().put("idInstanciaTarea", JSFUtils.resolveExpression("#{pageFlowScope.pIdInstanciaTarea}"));
                    operationBinding.getParamsMap().put("comentario",comentario);
                    
                    Object result = operationBinding.execute();
                    if(result != null){
                        
                    }
                    
                    operationBinding = bindings.getOperationBinding("Commit");
                    Object resultCommit =operationBinding.execute();
                    if(resultCommit != null){
                        
                    }
                    
                    comentariosBean.setComentario(null);
                    comentarioUIC.resetValue();
                    
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getComentarioUIC());
                    
                    if (!operationBinding.getErrors().isEmpty()) {
                        //return null;
                    }
                    DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                    
                    DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ComentarioOperacionVOIterator");
                    comentariosIterator.executeQuery();
                    
                    getPopUpAbrirComentAgr().cancel();
                    
                }
                else
                {
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_NO_COMMENTS",BUNDLE));
                }
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public void agregarJustificacionActionListener(ActionEvent action) {
        
        if(action == null){
            return;
        }
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        
        if(comentariosBean != null){
            
            String justificacion = comentariosBean.getJustificacion();
            
            boolean valido = validaJustificacionUIC(justificacion);
            if(valido){
                
                if(null != justificacion &&  justificacion.trim().length()>0)
                {
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("agregarJustificacionOperacion");
                    operationBinding.getParamsMap().put("loginUsuario", JSFUtils.resolveExpression("#{pageFlowScope.pIdUsuario}"));
                    operationBinding.getParamsMap().put("nombreUsusario", JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}"));
                    operationBinding.getParamsMap().put("idTcaTareaBpm", JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}"));
                    operationBinding.getParamsMap().put("idOperacion", JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
                    operationBinding.getParamsMap().put("idInstanciaTarea", JSFUtils.resolveExpression("#{pageFlowScope.pIdInstanciaTarea}"));
                    operationBinding.getParamsMap().put("comentario",justificacion);
                    
                    operationBinding.execute();
                    if(!operationBinding.getErrors().isEmpty()) {
                        for(Object error : operationBinding.getErrors()) {
                            logger.severe(error.toString());
                        }    
                    } 
                    
                    OperationBinding opCommit = bindings.getOperationBinding("Commit");
                    opCommit.execute();
                    if(!opCommit.getErrors().isEmpty()) {
                        for(Object error : opCommit.getErrors()) {
                            logger.severe(error.toString());
                        }    
                    } 
                    
                    OperationBinding opObtenerJustificacion = bindings.getOperationBinding("obtenerJustificacionPorId");
                    if (opObtenerJustificacion != null) {
                        opObtenerJustificacion.getParamsMap().put("idOperacion", JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
                        opObtenerJustificacion.getParamsMap().put("tipo", Integer.valueOf(2));
                        opObtenerJustificacion.execute();
                        
                        if(!opObtenerJustificacion.getErrors().isEmpty()) {
                            for(Object error : opObtenerJustificacion.getErrors()) {
                                logger.severe(error.toString());
                            }    
                        }
                        
                    } else {
                        logger.severe("obtenerJustificacionPorId operacion no encontrada.");
                    }
                    //DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                    //DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("JustificacionOperacionVO1Iterator");
                    //comentariosIterator.executeQuery();
                }
                else
                {
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_NO_COMMENTS",BUNDLE));
                }
            }
        }
    }
    
    public void eliminarComentarioAllActionListener(ActionEvent action) {
        logger.warning("Ingresa metodo eliminarComentarioActionListener");
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        
        int idComentarioPadre = comentariosBean.getIdComentarioPadre();
        int idComentarioHijo = comentariosBean.getIdComentarioHijo();
        
        logger.warning("Valor idComentarioPadre: " + idComentarioPadre);
        //logger.warning("Valor idComentarioHijo: " + idComentarioHijo);
        
        try{
            operationBinding = bindings.getOperationBinding("eliminarComentarioPadreCascada");
            operationBinding.getParamsMap().put("idComentarioPadre", Integer.parseInt(String.valueOf(idComentarioPadre)));
            operationBinding.execute();
            Boolean respuestaElimarCascada = (Boolean) operationBinding.getResult();
            if(respuestaElimarCascada == true){
                operationBinding = bindings.getOperationBinding("eliminarComentarioPadre");
                operationBinding.getParamsMap().put("idComentario", Long.parseLong(String.valueOf(idComentarioPadre)));
                operationBinding.execute();
                Boolean respuestaElimarPadre = (Boolean) operationBinding.getResult();
                if(respuestaElimarPadre == true)
                    logger.warning("Se han eliminado los comentarios Padre e hijos correctamente");
                else
                    logger.warning("Ha ocurrido un error al eliminar el comentario padre");
            }
            else
                logger.warning("Ha ocurrido un error al querer eliminar algun comentario hijo, no se procede a eliminar el comentario padre");
        }
        catch(Exception e){
            logger.warning("Error al querer eliminar comentarios... " , e);
        }
        
        getPopUpAbrirComentCas().cancel();
        
        DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        
        DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ComentarioOperacionVOIterator");
        comentariosIterator.executeQuery();
        
        logger.warning("Finaliza metodo eliminarComentarioActionListener");
    }
    
    public void eliminarComentarioOnlyActionListener(ActionEvent action) {
        logger.warning("Ingresa metodo eliminarComentarioActionListener");
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        
        int idComentarioPadre = comentariosBean.getIdComentarioPadre();
        int idComentarioHijo = comentariosBean.getIdComentarioHijo();
        
        logger.warning("Valor idComentarioPadre: " + idComentarioPadre);
        logger.warning("Valor idComentarioHijo: " + idComentarioHijo);
        
        try{
            operationBinding = bindings.getOperationBinding("eliminarComentarioIndividual1");
            operationBinding.getParamsMap().put("idComentario", Integer.parseInt(String.valueOf(idComentarioHijo)));
            operationBinding.execute();
            Boolean respuestaElimarIndividual = (Boolean) operationBinding.getResult();
            if(respuestaElimarIndividual == true)
                logger.warning("Se ha eliminado el comentario individual correctamente");
            else
                logger.warning("Ha ocurrido un error al querer eliminar el comentario individual");
        }
        catch(Exception e){
            logger.warning("Error al querer eliminar comentario individual ... " , e);
        }
        
        getPopUpAbrirComentInd().cancel();
        
        DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        
        DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ComentarioOperacionVOIterator");
        comentariosIterator.executeQuery();
        
        logger.warning("Finaliza metodo eliminarComentarioActionListener");
    }

    @SuppressWarnings("unchecked")
    public void agregarRespuestaComentarioActionListener(ActionEvent action) {
        
        if(action == null){
            return;
        }
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        
        if(comentariosBean != null){
            
            String comentarioRespuesta = comentariosBean.getComentarioRespuesta();
            int idComentarioPadre = comentariosBean.getIdComentarioPadre();
            int idComentarioHijo = comentariosBean.getIdComentarioHijo();
            
            boolean valido = validaComentarioRespUIC(comentarioRespuesta);
            if(valido){
                
                BindingContainer bindings = getBindings();
                OperationBinding operationBinding = bindings.getOperationBinding("agregarRespuestaComentario");
                operationBinding.getParamsMap().put("idComentarioOperacion",idComentarioPadre);
                operationBinding.getParamsMap().put("idTcaTareaBpm", JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}"));
                operationBinding.getParamsMap().put("loginUsuario", JSFUtils.resolveExpression("#{pageFlowScope.pIdUsuario}"));
                operationBinding.getParamsMap().put("nombreUsusario", JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}"));
                operationBinding.getParamsMap().put("idInstanciaTarea", JSFUtils.resolveExpression("#{pageFlowScope.pIdInstanciaTarea}"));
                operationBinding.getParamsMap().put("repuesta",comentarioRespuesta);
                
                Object result = operationBinding.execute();
                if(result != null){
                    
                }
                
                operationBinding = bindings.getOperationBinding("Commit");
                Object resultCommit =operationBinding.execute();
                if(resultCommit != null){
                    
                }
                
                comentariosBean.setComentarioRespuesta(null);
                
                //RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                getPopUpAbrir().cancel();
                if (!operationBinding.getErrors().isEmpty()) {
                   // return null;
                }
                
                DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                
                DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ComentarioOperacionVOIterator");
                comentariosIterator.executeQuery();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    public void agregarComentarioClienteActionListener(ActionEvent action) {
        
        if(action == null){
            return;
        }
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        
        if(comentariosBean != null){
            
            String comentario = comentariosBean.getComentario();
            
            boolean valido = validaComentarioUIC(comentario);
            if(valido){
                
                if(null != comentario &&  comentario.trim().length()>0)
                {
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("agregarComentarioCliente");
                    operationBinding.getParamsMap().put("loginUsuario", JSFUtils.resolveExpression("#{pageFlowScope.pIdUsuario}"));
                    operationBinding.getParamsMap().put("nombreUsusario", JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}"));
                    operationBinding.getParamsMap().put("idTcaTareaBpm", JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}"));
                    operationBinding.getParamsMap().put("idCliente", JSFUtils.resolveExpression("#{pageFlowScope.pIdCliente}"));
                    operationBinding.getParamsMap().put("idInstanciaTarea", JSFUtils.resolveExpression("#{pageFlowScope.pIdInstanciaTarea}"));
                    operationBinding.getParamsMap().put("comentario",comentario);
                    
                    Object result = operationBinding.execute();
                    if(result != null){
                        
                    }
                    /*operationBinding = bindings.getOperationBinding("Commit");
                    Object resultCommit =operationBinding.execute();
                    */
                    
                    comentariosBean.setComentario(null);
                    comentarioUIC.resetValue();
                    
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getComentarioUIC());
                    
                    if (!operationBinding.getErrors().isEmpty()) {
                        //return null;
                    }
                    DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                    
                    DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ComentarioClienteVOIterator");
                    comentariosIterator.executeQuery();
                }
                else
                {
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MSG_NO_COMMENTS",BUNDLE));
                }
            }
        }
    }
    
    public void eliminarComentarioClienteAllActionListener(ActionEvent action) {
        logger.warning("Ingresa metodo eliminarComentarioClienteAllActionListener");
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        
        int idComentarioPadre = comentariosBean.getIdComentarioPadre();
        int idComentarioHijo = comentariosBean.getIdComentarioHijo();
        
        logger.warning("Valor idComentarioPadre: " + idComentarioPadre);
        //logger.warning("Valor idComentarioHijo: " + idComentarioHijo);
        
        try{
            operationBinding = bindings.getOperationBinding("eliminarComentarioClientePadreCascada1");
            operationBinding.getParamsMap().put("idComentarioPadre", Integer.parseInt(String.valueOf(idComentarioPadre)));
            operationBinding.execute();
            Boolean respuestaElimarCascada = (Boolean) operationBinding.getResult();
            if(respuestaElimarCascada == true){
                operationBinding = bindings.getOperationBinding("eliminarComentarioClientePadre");
                operationBinding.getParamsMap().put("idComentario", Long.parseLong(String.valueOf(idComentarioPadre)));
                operationBinding.execute();
                Boolean respuestaElimarPadre = (Boolean) operationBinding.getResult();
                if(respuestaElimarPadre == true)
                    logger.warning("Se han eliminado los comentarios Padre e hijos correctamente");
                else
                    logger.warning("Ha ocurrido un error al eliminar el comentario padre");
            }
            else
                logger.warning("Ha ocurrido un error al querer eliminar algun comentario hijo, no se procede a eliminar el comentario padre");
        }
        catch(Exception e){
            logger.warning("Error al querer eliminar comentarios... " , e);
        }
        
        getPopUpAbrirComentCas().cancel();
        
        DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        
        DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ComentarioClienteVOIterator");
        comentariosIterator.executeQuery();
        
        logger.warning("Finaliza metodo eliminarComentarioClienteAllActionListener");
    }
    
    public void eliminarComentarioClienteOnlyActionListener(ActionEvent action) {
        logger.warning("Ingresa metodo eliminarComentarioClienteOnlyActionListener");
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        
        int idComentarioPadre = comentariosBean.getIdComentarioPadre();
        int idComentarioHijo = comentariosBean.getIdComentarioHijo();
        
        logger.warning("Valor idComentarioPadre: " + idComentarioPadre);
        logger.warning("Valor idComentarioHijo: " + idComentarioHijo);
        
        try{
            operationBinding = bindings.getOperationBinding("eliminarComentarioClienteIndividual");
            operationBinding.getParamsMap().put("idComentario", Integer.parseInt(String.valueOf(idComentarioHijo)));
            operationBinding.execute();
            Boolean respuestaElimarIndividual = (Boolean) operationBinding.getResult();
            if(respuestaElimarIndividual == true)
                logger.warning("Se ha eliminado el comentario individual correctamente");
            else
                logger.warning("Ha ocurrido un error al querer eliminar el comentario individual");
        }
        catch(Exception e){
            logger.warning("Error al querer eliminar comentario individual ... " , e);
        }
        
        getPopUpAbrirComentInd().cancel();
        
        DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ComentarioClienteVOIterator");
        comentariosIterator.executeQuery();
        
        logger.warning("Finaliza metodo eliminarComentarioClienteOnlyActionListener");
    }
    
    
    @SuppressWarnings("unchecked")
    public void agregarRespuestaComentarioClienteActionListener(ActionEvent action) {
        
        if(action == null){
            return;
        }
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        
        if(comentariosBean != null){
            
            String comentarioRespuesta = comentariosBean.getComentarioRespuesta();
            int idComentarioPadre = comentariosBean.getIdComentarioPadre();
            
            boolean valido = validaComentarioRespUIC(comentarioRespuesta);
            if(valido){
                
                BindingContainer bindings = getBindings();
                OperationBinding operationBinding = bindings.getOperationBinding("agregarRespuestaComentarioCliente");
                operationBinding.getParamsMap().put("idComentarioCliente",idComentarioPadre);
                operationBinding.getParamsMap().put("idTcaTareaBpm", JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}"));
                operationBinding.getParamsMap().put("loginUsuario", JSFUtils.resolveExpression("#{pageFlowScope.pIdUsuario}"));
                operationBinding.getParamsMap().put("nombreUsusario", JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}"));
                operationBinding.getParamsMap().put("idInstanciaTarea", JSFUtils.resolveExpression("#{pageFlowScope.pIdInstanciaTarea}"));
                operationBinding.getParamsMap().put("repuesta",comentarioRespuesta);
                
                Object result = operationBinding.execute();
                if(result != null){
                    
                }
                /*operationBinding = bindings.getOperationBinding("Commit");
                Object resultCommit =operationBinding.execute();*/
                
                comentariosBean.setComentarioRespuesta(null);
                
                //RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                getPopUpAbrir().cancel();
                if (!operationBinding.getErrors().isEmpty()) {
                   // return null;
                }
                
                DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                
                DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ComentarioClienteVOIterator");
                comentariosIterator.executeQuery();
            }
        }
        
    }
    @SuppressWarnings("unchecked")
    public void agregarJustificacionOperacionTipoActionListener(@SuppressWarnings("unused") ActionEvent action) {
        try{
            final JustificacionBean justificacionBean =  (JustificacionBean) JSFUtils.resolveExpression("#{pageFlowScope.JustificacionManageBean}");
            if(justificacionBean != null){
                Boolean exito=Boolean.TRUE;
                if(justificacionBean.getJustificacion().getTexto()==null){
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("msg.comentario.valido"));
                    return; 
                }
                if(!this.validarJustificacion(justificacionBean.getJustificacion().getTexto())){
                    justificacionBean.getJustificacion().setTexto("");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("msg.comentario.valido"));
                    return;
                }
                if(justificacionBean.getActualizar()){
                    logger.warning("Actualizando registro existente "+ justificacionBean.getJustificacion().getTexto().trim());
                    final BindingContainer bindings = getBindings();
                    JSFUtils.setExpressionValue("#{bindings.Comentario.inputValue}", justificacionBean.getJustificacion().getTexto().trim());
                    logger.warning("Valor en el binding "+ JSFUtils.resolveExpression("#{bindings.Comentario.inputValue}"));
                    final OperationBinding opCommit = bindings.getOperationBinding("Commit");
                    opCommit.execute();
                    if(!opCommit.getErrors().isEmpty()) {
                        logger.severe("Error guardando "+opCommit.getErrors().size());
                        exito=Boolean.FALSE;
                    }
                    
                }
                if(!justificacionBean.getActualizar()){
                    logger.warning("Guardando un registro nuevo ");
                    final BindingContainer bindings = getBindings();
                    final OperationBinding operationBinding = bindings.getOperationBinding("agregarJustificacionOperacionTipo");
                    operationBinding.getParamsMap().put("loginUsuario", JSFUtils.resolveExpression("#{pageFlowScope.pIdUsuario}"));
                    operationBinding.getParamsMap().put("nombreUsusario", JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}"));
                    operationBinding.getParamsMap().put("idTcaTareaBpm", JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}"));
                    operationBinding.getParamsMap().put("idOperacion", JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
                    operationBinding.getParamsMap().put("idInstanciaTarea", JSFUtils.resolveExpression("#{pageFlowScope.pIdInstanciaTarea}"));
                    operationBinding.getParamsMap().put("comentario",justificacionBean.getJustificacion().getTexto().trim());
                    operationBinding.getParamsMap().put("tipo",JSFUtils.resolveExpression("#{pageFlowScope.pTipo}"));
                    
                    operationBinding.execute();
                    if(!operationBinding.getErrors().isEmpty()){
                        logger.severe("Error guardando "+operationBinding.getErrors().size());
                        exito=Boolean.FALSE;
                    } 
                    
                    final OperationBinding opCommit = bindings.getOperationBinding("Commit");
                    opCommit.execute();
                    if(!opCommit.getErrors().isEmpty()) {
                        logger.severe("Error guardando "+opCommit.getErrors().size());
                        exito=Boolean.FALSE;
                    }
                    
                    final OperationBinding opObtenerJustificacion = bindings.getOperationBinding("obtenerJustificacionPorId");
                    if (opObtenerJustificacion != null) {
                        opObtenerJustificacion.getParamsMap().put("idOperacion", JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}"));
                        opObtenerJustificacion.getParamsMap().put("tipo", JSFUtils.resolveExpression("#{pageFlowScope.pTipo}"));
                        opObtenerJustificacion.execute();
                        
                        if(!opObtenerJustificacion.getErrors().isEmpty()) {
                            logger.severe("Error guardando "+opCommit.getErrors().size());    
                        }
                        
                    } else {
                        logger.severe("obtenerJustificacionOperacionTipo operacion no encontrada.");
                    }
                    if(exito){
                        justificacionBean.setBlockGuardar(Boolean.TRUE);
                    }
                }
                if(exito){
                    justificacionBean.getJustificacion().setGuardado(Boolean.TRUE);
                }
            }  
        }catch(Exception e){
            logger.severe(e);
            JSFUtils.addFacesErrorMessage(getStringFromBundle(e.getMessage()));
        }
    }
    public void openDialogActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpAbrir().show(popupHints);
    }
    
    
    public void changeLongitudComent(ActionEvent actionEvent) {
        
            final Boolean leerMas = Boolean.valueOf(actionEvent.getComponent().getAttributes().get("leerMas").toString());
            final FacesCtrlHierNodeBinding fc = (FacesCtrlHierNodeBinding) actionEvent.getComponent().getAttributes().get("row");
           

            if(leerMas){
                System.out.println("1");
                fc.setAttribute("LeerMas", Boolean.FALSE);
            }else{
                System.out.println("2");
                fc.setAttribute("LeerMas", Boolean.TRUE);
            }
     
    }
     

    public void closeDialogActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        comentariosBean.setComentarioRespuesta(null);
        comentarioRespuestaUIC.resetValue();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getComentarioRespuestaUIC());
        
        
        getPopUpAbrir().cancel();
    }
    
    public void closeDialogComActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        
        ComentariosBean comentariosBean = 
            (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
        comentariosBean.setComentario(null);
        comentarioUIC.resetValue();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getComentarioUIC());
        
        getPopUpAbrirComentAgr().cancel();
    }
    
    public void openDialogEliComIndActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpAbrirComentInd().show(popupHints);
    }
    
    public void closeDialogEliComIndActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        getPopUpAbrirComentInd().cancel();
    }
    
    public void openDialogEliComCasActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpAbrirComentCas().show(popupHints);
    }
    
    public void closeDialogEliComCasActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        getPopUpAbrirComentCas().cancel();
    }
    
    public void setPopUpAbrirComentInd(RichPopup popUpAbrirComentInd) {
        this.popUpAbrirComentInd = popUpAbrirComentInd;
    }

    public RichPopup getPopUpAbrirComentInd() {
        return popUpAbrirComentInd;
    }

    public void setPopUpAbrirComentCas(RichPopup popUpAbrirComentCas) {
        this.popUpAbrirComentCas = popUpAbrirComentCas;
    }

    public RichPopup getPopUpAbrirComentCas() {
        return popUpAbrirComentCas;
    }

    public void setEliminarComentarioIndUIC(RichButton eliminarComentarioIndUIC) {
        this.eliminarComentarioIndUIC = eliminarComentarioIndUIC;
    }

    public RichButton getEliminarComentarioIndUIC() {
        return eliminarComentarioIndUIC;
    }

    public void setEliminarComentarioCasUIC(RichButton eliminarComentarioCasUIC) {
        this.eliminarComentarioCasUIC = eliminarComentarioCasUIC;
    }

    public RichButton getEliminarComentarioCasUIC() {
        return eliminarComentarioCasUIC;
    }

    public void setJustificacionUIC(RichInputText justificacionUIC) {
        this.justificacionUIC = justificacionUIC;
}

    public RichInputText getJustificacionUIC() {
        return justificacionUIC;
    }

    public void setAgregarJustificacionUIC(RichButton agregarJustificacionUIC) {
        this.agregarJustificacionUIC = agregarJustificacionUIC;
    }

    public RichButton getAgregarJustificacionUIC() {
        return agregarJustificacionUIC;
    }
    
    /**
     * @Featuature: 4297
     * @Developer: vmartinez
     * @Date:09/07/2021
     * */
    public void onChangeFiltrarCometariosProceso(ValueChangeEvent valueChangeEvent) {
        try{
            logger.warning("Inicio onChangeFiltrarCometariosProceso: " + valueChangeEvent.getNewValue());
            final Boolean check=(Boolean)valueChangeEvent.getNewValue();
            DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ComentarioOperacionVOIterator");
            if(check){
                logger.warning("Ingreso check: " + valueChangeEvent.getNewValue());
                ComentariosBean comentariosBean =  (ComentariosBean) JSFUtils.resolveExpression("#{pageFlowScope.ComentariosManagedBean}");
                ViewCriteria criterial = comentariosIterator.getViewObject().getViewCriteriaManager().getViewCriteria("ComentarioOperacionVsInstanciaProcesoVOCriteria");
                logger.warning("Id Intancia Tarea: " + comentariosBean.getIdInstancia());
                criterial.ensureVariableManager().setVariableValue("p_idProceso",comentariosBean.getIdProceso());
                comentariosIterator.getViewObject().applyViewCriteria(criterial);
            }else{
                logger.warning("Remover el criterio: ");
                final Boolean apply=comentariosIterator.getViewObject().getViewCriteriaManager().removeApplyViewCriteriaName("ComentarioOperacionVsInstanciaProcesoVOCriteria"); 
                logger.warning("Resultado operacion "+apply);
            }
            comentariosIterator.executeQuery();
        }catch(Exception e){
            logger.severe(e);
            JSFUtils.addFacesErrorMessage(e.getMessage());
        }
    }

    public void setPopUpAbrirComentAgr(RichPopup popUpAbrirComentAgr) {
        this.popUpAbrirComentAgr = popUpAbrirComentAgr;
    }

    public RichPopup getPopUpAbrirComentAgr() {
        return popUpAbrirComentAgr;
    }

    public void openDialogAddComActionListener(ActionEvent actionEvent) {
        if(actionEvent == null){
            return;
        }
         
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpAbrirComentAgr().show(popupHints);
    }
}
