package org.bcie.fenix.view.comentarios;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ViewCriteria;

import oracle.jbo.ViewObject;

import oracle.jbo.server.ViewRowSetImpl;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class ComentariosBean implements Serializable{
    @SuppressWarnings("compatibility:3596286382254542446")
    private static final long serialVersionUID = 1L;
    public static final String BUNDLE = "org.bcie.fenix.view.ComentariosFenixVCBundle";
    private static final ADFLogger logger = ADFLogger.createADFLogger(ComentariosBean.class);
    private static final String CLASS_NAME = ComentariosBean.class.getName();
    private static final Integer ELIMINAR_COMENTARIO = 1;
    
    private String comentario;
    private String justificacion;
    private String comentarioRespuesta;
    private int idComentarioPadre;
    private int idComentarioHijo;
    private Boolean esRolAutorizado = Boolean.FALSE;
    private Boolean esEstadoCompletado;
    private Boolean filtrarProceso;
    private Boolean isExternal;
    private String idInstancia;
    private String idProceso;
    
    public ComentariosBean() {
        super();
        /**
         * @Featuature: 4297
         * @Developer: rgarcia/vmartinez
         * @Date:09/07/2021
         * */
        this.filtrarProceso=Boolean.FALSE;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdInstanciaTarea}")!=null){
            logger.warning("Id instancia origen: " + idInstancia);
            idInstancia =  JSFUtils.resolveExpression("#{pageFlowScope.pIdInstanciaTarea}").toString();
        }
        this.isExternal=Boolean.FALSE;
        logger.warning("Inicio MB id instancia: " + idInstancia);
        if(this.idInstancia!=null && this.idInstancia.equals("1")){
            logger.warning("es externo: ");
            this.isExternal=Boolean.TRUE;
        }
        
        if(!this.isExternal){
        try{
            
                this.filtrarProceso=Boolean.TRUE;
                final Boolean check=Boolean.TRUE;
            }catch(Exception e){
                logger.severe(e);
                JSFUtils.addFacesErrorMessage(e.getMessage());
            }
        }
 
    }
    
    /**
     * Carga la justificacion por operacion.
     */
    public void precargaJustificacion(){

        final String METHOD_NAME = "precargaJustificacion";
        logger.entering(CLASS_NAME, METHOD_NAME);
        
        // declarar recursos
        BindingContainer binding = null;
        OperationBinding operationBinding = null;
        String idOperacion = null;
        
        try {    
            binding = ADFUtils.getBindingContainer();
            operationBinding = binding.getOperationBinding("obtenerJustificacionPorId");
            idOperacion = JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
            
            
            logger.warning("idOperacion enviado: " + idOperacion);
            
            if(operationBinding != null) {

                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                operationBinding.execute();
                
                if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("OperationBinding devuelve errores metodo setIdOperacion");
                }else{
                    logger.warning("***Carga IdOperacion Listo, redireccionando... ");
                }

            } else {
                logger.severe("Operaci�n no encontrada.");
            }

        } catch (Exception e){
            logger.log(ADFLogger.ERROR, METHOD_NAME, e.getMessage());
        }
        
        List<String> permisosPorUsuario = inicializarPermisosUsuario();
        List<String> rolesConPermiso = rolesPermitidosPorAccion();
        Boolean banderaRolPermitodo = obtenerBanderaRolPermitidoEliminarComenterios(permisosPorUsuario, rolesConPermiso); 
        setEsRolAutorizado(banderaRolPermitodo);
        
        logger.warning("Valor bandera esRolAutorizado: " + getEsRolAutorizado());
        
        evaluarParametroPStateBpm();
        logger.exiting(CLASS_NAME, METHOD_NAME);
    }

    public void precargaComentarios(){
        logger.warning("***** Inicia metodo precargaComentarios *****");
        
        try{    
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("setIdOperacion");
            String idOperacion = JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
            logger.warning("idOperacion enviado: " + idOperacion);
            operationBinding.getParamsMap().put("value", idOperacion);
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                logger.warning("OperationBinding devuelve errores metodo setIdOperacion");
            }else{
                logger.warning("***Carga IdOperacion Listo, redireccionando... ");
            }
            
            
            String idTarea = JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}").toString();
            DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dcIterator = dcBindings.findIteratorBinding("TareaProcesoBpmVOIterator");
           
            ViewCriteria criterial = dcIterator.getViewObject().getViewCriteriaManager().getViewCriteria("TareaProcesoBpmVOCriteriaByIdTarea");
            logger.warning("Id Intancia Tarea: " +getIdInstancia());
            criterial.ensureVariableManager().setVariableValue("varIdTareaBpm",Integer.parseInt(idTarea));
            dcIterator.getViewObject().applyViewCriteria(criterial);
            dcIterator.executeQuery();
            
            setIdProceso(JSFUtils.resolveExpression("#{bindings.Idproceso.inputValue}").toString());
            
            logger.warning("idProceso "+idProceso);
            logger.warning("isExternal "+isExternal);
            
            if(!this.isExternal){
                
                dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                dcIterator = dcBindings.findIteratorBinding("ComentarioOperacionVOIterator");
                
                System.out.println("dcBindings "+dcBindings);
                System.out.println("comentariosIterator "+dcIterator);
                
                criterial = dcIterator.getViewObject().getViewCriteriaManager().getViewCriteria("ComentarioOperacionVsInstanciaProcesoVOCriteria");
                logger.warning("Id Intancia Tarea: " +getIdInstancia());
                criterial.ensureVariableManager().setVariableValue("p_idProceso",getIdProceso());
                dcIterator.getViewObject().applyViewCriteria(criterial);
                dcIterator.executeQuery();
            }
                       
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en metodo setIdOperacion ", e);
        }
        
        List<String> permisosPorUsuario = inicializarPermisosUsuario();
        List<String> rolesConPermiso = rolesPermitidosPorAccion();
        Boolean banderaRolPermitodo = obtenerBanderaRolPermitidoEliminarComenterios(permisosPorUsuario, rolesConPermiso); 
        setEsRolAutorizado(banderaRolPermitodo);
        
        logger.warning("Valor bandera esRolAutorizado: " + getEsRolAutorizado());
        
        //metodo para evaluar el parametro de entrada #{pageFlowScope.pStateBpm}
        evaluarParametroPStateBpm();
        
        logger.warning("***** Finaliza metodo precargaComentarios *****");
    }
    
    public void evaluarParametroPStateBpm(){
        logger.warning("Dentro de evaluarParametroPStateBpm");
        String state = null;
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}") != null) {
                state = (String) JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}");
                logger.warning("state :" + state);
                setEsEstadoCompletado(state.equals("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            }else{
                logger.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            logger.severe("Error al recuperar #{pageFlowScope.pStateBpm} :",ex);
            logger.warning("pState no obtenido");
        }

        logger.warning("esEstadoCompletado : " + getEsEstadoCompletado());
        logger.warning("Fuera de evaluarParametroPStateBpm");
    }
    
    public void precargaComentariosCliente(){
        logger.warning("***** Inicia metodo precargaComentariosCliente *****");
        
        try{    
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("setIdCliente");
            String idCliente = JSFUtils.resolveExpression("#{pageFlowScope.pIdCliente}").toString();
            logger.warning("idCliente enviado: " + idCliente);
            operationBinding.getParamsMap().put("value", idCliente);
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                logger.warning("OperationBinding devuelve errores metodo setIdCliente");
            }else{
                logger.warning("***Carga setIdCliente Listo, redireccionando... ");
            }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en metodo setIdCliente ", e);
        }
        
        List<String> permisosPorUsuario = inicializarPermisosUsuario();
        List<String> rolesConPermiso = rolesPermitidosPorAccion();
        Boolean banderaRolPermitodo = obtenerBanderaRolPermitidoEliminarComenterios(permisosPorUsuario, rolesConPermiso); 
        setEsRolAutorizado(banderaRolPermitodo);
        
        logger.warning("Valor bandera esRolAutorizado: " + getEsRolAutorizado());
        
        //metodo para evaluar el parametro de entrada #{pageFlowScope.pStateBpm}
        evaluarParametroPStateBpm();
        logger.warning("***** Finaliza metodo precargaComentariosCliente *****");
    }
    
    public List<String> inicializarPermisosUsuario(){
        //Metodo que obtinene los roles a los que pertenece el usuario
        logger.warning("inside: inicializarPermisosUsuario");
        
        Object resultado = null;
        List<String>grupo = new ArrayList<String>();
        
        try
        {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("getGruposUsuario");
            String usuario = ADFContext.getCurrent().getSecurityContext().getUserName();
            if(usuario.equalsIgnoreCase("anonymous"))
            {
                try {
                    if (JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}") != null)
                        usuario = JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}").toString();
                }
                catch(Exception e)
                {
                    logger.log(ADFLogger.ERROR, "Error al obtener el parametro pNombreUsuario del task.", e);
                }
            }
            logger.warning("pUsuario: " + usuario);
            operationBinding.getParamsMap().put("pUsuario", usuario);
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty())
            {
                logger.warning("operationBinding devuelve error");
            }
            else
            {
                resultado = operationBinding.getResult();       
            }
            
            if (resultado!=null){
                grupo = (List<String>)resultado;
                for(String gs : grupo){
                    logger.warning("grupo del servicio: " + gs);
                }
            }
        }
        catch(Exception e)
        {
            logger.warning("Error al obtener los roles por usuario...", e);
        }
        
        logger.warning("Valor a retornar: grupo.size() = " + grupo.size());
        logger.warning("Finaliza: inicializarPermisosUsuario");
        return grupo;
    }
    
    public List<String> rolesPermitidosPorAccion(){
        logger.warning("inside: rolesPermitidosPorAccion");
        List<String>rolesPermitidos = new ArrayList<String>();
        
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            
            operationBinding = bindings.getOperationBinding("obtenerRoles");
            operationBinding.getParamsMap().put("idAccion", ELIMINAR_COMENTARIO);
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                rolesPermitidos = (ArrayList<String>) operationBinding.getResult();
            }
        }
        catch(Exception e)
        {
            logger.warning("Error al obtener los roles... ", e);
        }
        
        logger.warning("Valor a retornar: rolesPermitidos.size = " + rolesPermitidos.size());
        logger.warning("Finaliza: rolesPermitidosPorAccion");
        return rolesPermitidos;
    }
    
    public Boolean obtenerBanderaRolPermitidoEliminarComenterios(List<String> permisosPorUsuario, List<String> rolesConPermisos){
        logger.warning("inside: obtenerBanderaRolPermitidoEliminarComenterios");
        Boolean respuesta = Boolean.FALSE;
        
        if(permisosPorUsuario.size() > 0 && rolesConPermisos.size() > 0)
        {
            for(String rolUsuario: permisosPorUsuario)
            {
                for(String rolConPermiso: rolesConPermisos)
                {
                    if( rolUsuario.equalsIgnoreCase(rolConPermiso) ||
                        (rolUsuario.startsWith("FENIX_REPRESENTANTE") && rolConPermiso.startsWith("FENIX_REPRESENTANTE")) )
                    {
                        logger.warning("Rol Obtenido del usuario: " + rolUsuario);
                        logger.warning("Rol Obtenido de la lista de roles con permiso: " + rolConPermiso);
                        logger.warning("Se encuentra una coincidencia, se habilita bandera para mostrar el boton eliminar comentario...");
                        respuesta = Boolean.TRUE;
                        break;
                    }
                }
            }
            if(!respuesta)
                logger.warning("El usuario no tiene un rol con permisos para eliminar comentaios, no se muestra el boton Eliminar comentarios");
        }
        else
        {
            logger.warning("Alguna de las listas es vacia, no tiene elementos, no se ejcuta la comparacion de roles permitidos para eliminar un comentario");
        }
        
        logger.warning("Valor a retornar respuesta: " + respuesta);
        logger.warning("Finaliza: obtenerBanderaRolPermitidoEliminarComenterios");
        return respuesta;
    }
  
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentarioRespuesta() {
        return comentarioRespuesta;
    }

    public void setComentarioRespuesta(String comentarioRespuesta) {
        this.comentarioRespuesta = comentarioRespuesta;
    }

    public int getIdComentarioPadre() {
        return idComentarioPadre;
    }

    public void setIdComentarioPadre(int idComentarioPadre) {
        this.idComentarioPadre = idComentarioPadre;
    }

    public void setIdComentarioHijo(int idComentarioHijo) {
        this.idComentarioHijo = idComentarioHijo;
    }

    public int getIdComentarioHijo() {
        return idComentarioHijo;
    }

    public void setEsRolAutorizado(Boolean esRolAutorizado) {
        this.esRolAutorizado = esRolAutorizado;
    }

    public Boolean getEsRolAutorizado() {
        return esRolAutorizado;
    }


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
    
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setFiltrarProceso(Boolean filtrarProceso) {
        this.filtrarProceso = filtrarProceso;
    }

    public Boolean getFiltrarProceso() {
        return filtrarProceso;
    }
    
    public void setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
    }

    public Boolean getIsExternal() {
        return isExternal;
    }

    public void setIdInstancia(String idInstancia) {
        this.idInstancia = idInstancia;
    }

    public String getIdInstancia() {
        return idInstancia;
    }


    public void setIdProceso(String idProceso) {
        this.idProceso = idProceso;
    }

    public String getIdProceso() {
        return idProceso;
    }
}
