package org.bcie.fenix.view.gestoroperaciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.faces.model.SelectItem;

import oracle.adf.share.ADFContext;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class SeguridadBean {
    
    private static ADFLogger logger = null;
    private GestorOperacionesBean gestorOperacionesBean = null;
    private static final String SEG_APLICACION = "OID_FENIX" ;    
    private static final String SEG_VENTANA = "OPERACION";
    
    private static final String  SEG_CONTROL_INICIAR_LAFT = "INICIAR_LAFT";
    private static final String  SEG_CONTROL_INICIAR_ENMIENDAS = "INICIAR_ENMIENDAS";
    private static final String  SEG_CONTROL_INICIAR_FORMALIZACION_ENMIENDAS = "INICIAR_FORMALIZACION_ENMIENDAS";
    private static final String  SEG_CONTROL_INICIAR_EVALUACIONES = "INICIAR_EVALUACIONES";
    private static final String  SEG_CONTROL_INICIAR_PROCESO_PREPAGO = "INICIAR_PROCESO_PREPAGO";
    private static final String  SEG_CONTROL_INICIAR_CIERRE_OPERACION = "INICIAR_CIERRE_OPERACION";
    private static final String  SEG_CONTROL_INICIAR_PROCESO_SUPERVISION = "INICIAR_PROCESO_SUPERVISION";
    private static final String  SEG_CONTROL_INICIAR_IMPLEMENTACION_PCT = "INICIAR_IMPLEMENTACION_PCT";
    private static final String  SEG_CONTROL_GESTIONAR_COBRO_COMISIONES = "GESTIONAR_COBRO_COMISIONES";
    private static final String  SEG_CONTROL_REGISTRAR_COMISION = "REGISTRAR_COMISION";
    private static final String  SEG_CONTROL_INICIAR_CUMPLIMIENTO_CONDICIONES = "INICIAR_CUMPLIMIENTO_CONDICIONES";
    private static final String  SEG_CONTROL_CONSULTAR_ESTADO_CONDICIONES = "CONSULTAR_ESTADO_CONDICIONES";
    private static final String  SEG_CONTROL_DETERMINAR_FACTIBILIDAD = "DETERMINAR_FACTIBILIDAD";
    private static final String  SEG_CONTROL_ENVIO_AL_GASTO = "ENVIO_AL_GASTO";
    private static final String  SEG_CONTROL_INICIAR_FIRMA_CONTRATO = "INICIAR_FIRMA_CONTRATO";
    private static final String  SEG_CONTROL_SOLICITAR_FORMALIZACION_PARCIAL = "SOLICITAR_FORMALIZACION_PARCIAL";
    private static final String  SEG_CONTROL_INICIAR_FORMALIZACION = "INICIAR_FORMALIZACION";
    private static final String  SEG_CONTROL_SOLICITAR_SPLIT = "SOLICITAR_SPLIT";
    private static final String  SEG_CONTROL_REGISTRAR_FIN_CONCURSO = "REGISTRAR_FIN_CONCURSO";
    private static final String  SEG_CONTROL_RESTABLECER_OPERACION = "RESTABLECER_OPERACION";
    private static final String  SEG_CONTROL_CANCELAR_OPERACION = "CANCELAR_OPERACION";
    private static final String  SEG_CONTROL_SUSPENDER_OPERACION = "SUSPENDER_OPERACION";
    private static final String  SEG_CONTROL_EVALUAR_LIMITES = "EVALUAR_LIMITES";
    private static final String  SEG_CONTROL_REASIGNAR_RESPONSABLE_OPERACION = "REASIGNAR_RESPONSABLE_OPERACION";
    private static final String  SEG_CONTROL_CAMBIAR_RESPONSABLE_OPERACION = "CAMBIAR_RESPONSABLE_OPERACION";
    private static final String  SEG_CONTROL_ESTADO_CONDICIONES = "ESTADO_CONDICIONES";
    private static final String  SEG_CONTROL_INICIAR_ADMINISTRAR_OPERACION = "INICIAR_ADMINISTRAR_OPERACION";
    
    private Map<String,String> _listaPermisosUsuario;
    
    public SeguridadBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }

        if (gestorOperacionesBean == null) {
            gestorOperacionesBean = (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
            this.listaPermisosUsuario();
        }
        logger.warning("SeguridadBean iniciado");
    }

    public Map<String,String> getListaPermisosUsuario() {
        return this._listaPermisosUsuario;
    }

    public void setListaPermisosUsuario(Map<String,String> value) {
        this._listaPermisosUsuario = value;
    }
    
    /**
     * metodo para consultar si un usuario tiene acceso a un control determinado  
     */
    public void listaPermisosUsuario(){
        logger.warning("Inicio listaPermisosUsuario");
        String pUser = ADFContext.getCurrent().getSecurityContext().getUserName(); 
        String pApplication = SEG_APLICACION; 
        String pWindow = SEG_VENTANA;
        String pControl = null;
        Map<String,String> listaPermisos = new HashMap<String,String>();
        logger.warning("pUser: " + pUser);
        logger.warning("pApplication: " + pApplication);
        logger.warning("pWindow: " + pWindow);
        //declaracion de variables 
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        //obtener si ocurre un error en el modelo
        try{ 
            operationBinding = bindings.getOperationBinding("obtienePermisosUsuario");
            //asignar valores para el metodo "consultarTieneAcceso" 
            operationBinding.getParamsMap().put("pUser", pUser);  
            operationBinding.getParamsMap().put("pApplication", pApplication);  
            operationBinding.getParamsMap().put("pWindow", pWindow);  
            operationBinding.getParamsMap().put("pControl", pControl);  
            //ejecutar el metodo y obtener el resultado 
            operationBinding.execute();  
            if(operationBinding.getErrors().size() == 0){
                listaPermisos = operationBinding.getResult() != null? (Map<String,String>)operationBinding.getResult() : null;
                logger.warning("listaPermisos: " + listaPermisos);
                if(listaPermisos != null){
                    String resultadoWS = listaPermisos.get("RESULTADO") != null?  (String)listaPermisos.get("RESULTADO") : "";
                    String mensajeWS = listaPermisos.get("MENSAJE") != null?  (String)listaPermisos.get("MENSAJE") : "";
                    logger.warning("resultadoWS: " + resultadoWS);
                    logger.warning("mensajeWS: " + mensajeWS);
                    
                    if(resultadoWS.equals("OK")){ 
                        logger.warning("listaPermisos OK: " + listaPermisos);
                        setListaPermisosUsuario(listaPermisos);
                    }
                    else
                    {
                        logger.warning("resultadoWS: " + resultadoWS); 
                        logger.warning("mensajeWS: " + mensajeWS); 
                    }
                }
                else{
                        logger.warning("listaPermisos es nulo"); 
                }
            }
            else{
                logger.warning("operationBinding tiene errores"); 
            } 
        }catch(Exception e){ 
            logger.warning("Error en listaPermisosUsuario :" + e);
        } 
        logger.warning("Finaliza listaPermisosUsuario");
    }
    
    public boolean tieneAcceso(String pControl){
        logger.warning("tieneAcceso _listaPermisosUsuario:" + this._listaPermisosUsuario);
        boolean resultado = false;
        if(this._listaPermisosUsuario != null){
            if(this._listaPermisosUsuario.containsKey(pControl))
            {
                resultado = true; 
            }
            else{
                resultado = false;
            }
        }
        else
        {
            resultado = false;
        }
        logger.warning("tieneAcceso _listaPermisosUsuario:" + this._listaPermisosUsuario);
        return resultado;
    }

    public boolean isAccesoIniciarLAFT(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_LAFT);
    }
    public boolean isAccesoIniciarEnmiendas(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_ENMIENDAS);
    }
    public boolean isAccesoIniciarFormalizacionEnmiendas(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_FORMALIZACION_ENMIENDAS);
    }
    public boolean isAccesoIniciarEvaluaciones(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_EVALUACIONES);
    }
    public boolean isAccesoIniciarProcesoPrepago(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_PROCESO_PREPAGO);
    }
    public boolean isAccesoIniciarCierreOperacion(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_CIERRE_OPERACION);
    }
    public boolean isAccesoIniciarProcesoSupervision(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_PROCESO_SUPERVISION);
    }
    public boolean isAccesoIniciarImplementacionPCT(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_IMPLEMENTACION_PCT);
    }
    public boolean isAccesoGestionarCobroComisiones(){
        return this.tieneAcceso(SEG_CONTROL_GESTIONAR_COBRO_COMISIONES);
    }
    public boolean isAccesoIniciarRegistrarComision(){
        return this.tieneAcceso(SEG_CONTROL_REGISTRAR_COMISION);
    }
    public boolean isAccesoIniciarCumplimientoCondiciones(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_CUMPLIMIENTO_CONDICIONES);
    }
    public boolean isAccesoConsultarEstadoCondiciones(){
        return this.tieneAcceso(SEG_CONTROL_CONSULTAR_ESTADO_CONDICIONES);
    }
    public boolean isAccesoDeterminarFactibilidad(){
        return this.tieneAcceso(SEG_CONTROL_DETERMINAR_FACTIBILIDAD);
    }
    public boolean isAccesoEnvioGasto(){
        return this.tieneAcceso(SEG_CONTROL_ENVIO_AL_GASTO);
    }
    public boolean isAccesoIniciarFirmaContrato(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_FIRMA_CONTRATO);
    }
    public boolean isAccesoSolicitarFormalizacionParcial(){
        return this.tieneAcceso(SEG_CONTROL_SOLICITAR_FORMALIZACION_PARCIAL);
    }
    public boolean isAccesoIniciarFormalizacion(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_FORMALIZACION);
    }
    public boolean isAccesoSolicitarSPLIT(){
        return this.tieneAcceso(SEG_CONTROL_SOLICITAR_SPLIT);
    }
    public boolean isAccesoRegistrarFinConcurso(){
        return this.tieneAcceso(SEG_CONTROL_REGISTRAR_FIN_CONCURSO);
    }
    public boolean isAccesoRestablecerOperacion(){
        return this.tieneAcceso(SEG_CONTROL_RESTABLECER_OPERACION);
    }
    public boolean isAccesoCancelarOperacion(){
        return this.tieneAcceso(SEG_CONTROL_CANCELAR_OPERACION);
    }
    public boolean isAccesoSuspenderOperacion(){
        return this.tieneAcceso(SEG_CONTROL_SUSPENDER_OPERACION);
    }
    public boolean isAccesoEvaludarLimites(){
        return this.tieneAcceso(SEG_CONTROL_EVALUAR_LIMITES);
    }
    public boolean isAccesoReasignarResponsableOperacion(){
        return this.tieneAcceso(SEG_CONTROL_REASIGNAR_RESPONSABLE_OPERACION);
    }
    public boolean isAccesoCambiarResponsableOperacion(){
        return this.tieneAcceso(SEG_CONTROL_CAMBIAR_RESPONSABLE_OPERACION);
    }
    public boolean isAccesoEstadoCondiciones(){
        return this.tieneAcceso(SEG_CONTROL_ESTADO_CONDICIONES);
    }
    public boolean isAccesoIniciarAdministrarOperacion(){
        return this.tieneAcceso(SEG_CONTROL_INICIAR_ADMINISTRAR_OPERACION);
    }

    
}
