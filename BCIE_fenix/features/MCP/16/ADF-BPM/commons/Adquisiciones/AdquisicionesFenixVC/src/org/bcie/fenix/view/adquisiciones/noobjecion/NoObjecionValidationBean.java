package org.bcie.fenix.view.adquisiciones.noobjecion;

import java.io.Serializable;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.HashMap;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.AttributeBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.adquisiciones.AdquisicionesActionBean;

public class NoObjecionValidationBean implements Serializable {
    @SuppressWarnings("compatibility:-7370293332785803083")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;
    
    private static final String BUNDLE = "org.bcie.fenix.view.adquisiciones.AdquisicionesFenixVCBundle";

    public NoObjecionValidationBean() {
        super();
        
        if (logger == null){
            logger = ADFLogger.createADFLogger(NoObjecionValidationBean.class);
        }
    }
    
    /**
     * M&eacute;todo para validar que la fecha de publicaci&oacute;n debe ser menor o igual a la 
     * fecha de inicio de disponibilidad de documento base.
     * @author Jonathan Ruiz
     * @return true en caso de ser v&acute;lido
     */
    public boolean evaluarValidaCampos(HashMap<String, Object> map) {
        boolean valida = Boolean.TRUE;

        @SuppressWarnings("unchecked")
        Map<String, Object> mapMensajes = (Map<String, Object>)map.get("mensajes");;
        
        Boolean lugarObtenerDocBase = (Boolean)map.get("lugarObtenerDocBase");
        Boolean fechaPublicacion = (Boolean)map.get("fechaPublicacion");
        Boolean fechaInicioDocBase = (Boolean)map.get("fechaInicioDocBase");
        Boolean fechaFinDocBase = (Boolean)map.get("fechaFinDocBase");
        Boolean fechaRecepcion = (Boolean)map.get("fechaRecepcion");
        Boolean correoInfoAdicional = (Boolean)map.get("correoInfoAdicional");
        Boolean dirPresentPropuesta = (Boolean)map.get("dirPresentPropuesta");
        Boolean contratadosRequeridos = (Boolean)map.get("contratadosRequeridos");
        Boolean adjudicatariosRequeridos = (Boolean)map.get("adjudicatariosRequeridos");
        Boolean idTcaResultadoProceso = (Boolean)map.get("idTcaResultadoProceso");
        
        Boolean nombreOferente = (Boolean)map.get("nombreOferente");
        Boolean nacionalidadOferente = (Boolean)map.get("nacionalidadOferente");
        Boolean ambosOferente = (Boolean)map.get("ambosOferente");
        
        Boolean todosAdjudicatarios = (Boolean)map.get("todosAdjudicatarios");
        Boolean montoAdjudicatarios = (Boolean)map.get("montoAdjudicatarios");
        
        Boolean todosContatado = (Boolean)map.get("todosContatado");
        Boolean instanciaContratado = (Boolean)map.get("instanciaContratado");
        
        Boolean RN_05 = (Boolean)map.get("RN_05");
        Boolean RN_06 = (Boolean)map.get("RN_06");
        Boolean RN_07 = (Boolean)map.get("RN_07");
        Boolean RN_08 = (Boolean)map.get("RN_08");
        Boolean VA_50 = (Boolean)map.get("VA_50");
        
        //Campos requeridos
        if (null != lugarObtenerDocBase && lugarObtenerDocBase) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("lugarObtenerDocBase"));
            logger.log(ADFLogger.WARNING, "Falta lugarObtenerDocBase.");
            valida = Boolean.FALSE;
        }

        if (null != fechaPublicacion && fechaPublicacion) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("fechaPublicacion"));
            logger.log(ADFLogger.WARNING, "Falta fechaPublicacion.");
            valida = Boolean.FALSE;
        }
        
        if (null != fechaInicioDocBase && fechaInicioDocBase) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("fechaInicioDocBase"));
            logger.log(ADFLogger.WARNING, "Falta fechaInicioDocBase.");
            valida = Boolean.FALSE;
        }
        
        if (null != fechaFinDocBase && fechaFinDocBase) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("fechaFinDocBase"));
            logger.log(ADFLogger.WARNING, "Falta fechaFinDocBase.");
            valida = Boolean.FALSE;
        }
        
        if (null != fechaRecepcion && fechaRecepcion) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("fechaRecepcion"));
            logger.log(ADFLogger.WARNING, "Falta fechaRecepcion.");
            valida = Boolean.FALSE;
        }
        
        if (null != correoInfoAdicional && correoInfoAdicional) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("correoInfoAdicional"));
            logger.log(ADFLogger.WARNING, "Falta correoInfoAdicional.");
            valida = Boolean.FALSE;
        }
        
        if (null != dirPresentPropuesta && dirPresentPropuesta) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("dirPresentPropuesta"));
            logger.log(ADFLogger.WARNING, "Falta dirPresentPropuesta.");
            valida = Boolean.FALSE;
        }
        
        //Validaciones
        if (null != RN_05 && RN_05) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("RN_05"));
            logger.log(ADFLogger.WARNING, "No se cumple RN_05.");
            valida = Boolean.FALSE;
        }
        
        if (null != RN_06 && RN_06) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("RN_06"));
            logger.log(ADFLogger.WARNING, "No se cumple RN_06.");
            valida = Boolean.FALSE;
        }
        
        if (null != RN_07 && RN_07) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("RN_07"));
            logger.log(ADFLogger.WARNING, "No se cumple RN_07.");
            valida = Boolean.FALSE;
        }
        
        if (null != RN_08 && RN_08) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("RN_08"));
            logger.log(ADFLogger.WARNING, "No se cumple RN_08.");
            valida = Boolean.FALSE;
        }
        
        if (null != VA_50 && VA_50) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("VA_50"));
            logger.log(ADFLogger.WARNING, "No se cumple VA_50.");
            valida = Boolean.FALSE;
        }
        
        if (null != contratadosRequeridos && contratadosRequeridos) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("contratadosRequeridos"));
            logger.log(ADFLogger.WARNING, "Falta contratadosRequeridos.");
            valida = Boolean.FALSE;
        }
        
        if (null != adjudicatariosRequeridos && adjudicatariosRequeridos) {
            String mensajeFaltante=null;
            if(null!=mapMensajes.get("adjudicatariosRequeridos")){
                    mensajeFaltante=(String)mapMensajes.get("adjudicatariosRequeridos");
                    logger.log(ADFLogger.WARNING, "Falta adjudicatariosRequeridos.");
                    JSFUtils.addFacesErrorMessage(mensajeFaltante);
                    valida = Boolean.FALSE;
                }
        }
        
        if (null != idTcaResultadoProceso && idTcaResultadoProceso) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("idTcaResultadoProceso"));
            logger.log(ADFLogger.WARNING, "Falta idTcaResultadoProceso.");
            valida = Boolean.FALSE;
        }
        
        if (null != nombreOferente && nombreOferente) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("nombreOferente"));
            logger.log(ADFLogger.WARNING, "Falta nombreOferente.");
            valida = Boolean.FALSE;
        }
        
        if (null != nacionalidadOferente && nacionalidadOferente) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("nacionalidadOferente"));
            logger.log(ADFLogger.WARNING, "Falta nacionalidadOferente.");
            valida = Boolean.FALSE;
        }
        
        if (null != ambosOferente && ambosOferente) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("ambosOferente"));
            logger.log(ADFLogger.WARNING, "Falta ambosOferente.");
            valida = Boolean.FALSE;
        }
        
        if (null != todosAdjudicatarios && todosAdjudicatarios) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("todosAdjudicatarios"));
            logger.log(ADFLogger.WARNING, "Falta todosAdjudicatarios.");
            valida = Boolean.FALSE;
        }
        
        if (null != montoAdjudicatarios && montoAdjudicatarios) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("montoAdjudicatarios"));
            logger.log(ADFLogger.WARNING, "Falta montoAdjudicatarios.");
            valida = Boolean.FALSE;
        }
        
        if (null != todosContatado && todosContatado) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("todosContatado"));
            logger.log(ADFLogger.WARNING, "Falta todosContatado.");
            valida = Boolean.FALSE;
        }
        
        if (null != instanciaContratado && instanciaContratado) {
            JSFUtils.addFacesErrorMessage((String)mapMensajes.get("instanciaContratado"));
            logger.log(ADFLogger.WARNING, "Falta instanciaContratado.");
            valida = Boolean.FALSE;
        }
        return valida;
    }
    
    /**
     * M&eacute;todo para validar que la fecha de publicaci&oacute;n debe ser menor o igual a la 
     * fecha de inicio de disponibilidad de documento base.
     * @author Jonathan Ruiz
     * @return true en caso de ser v&acute;lido
     */
    public boolean validaRN05() {
        boolean valida = Boolean.TRUE;
        AttributeBinding fechaPublicacion = ADFUtils.findControlBinding("FechaPublicacion");
        AttributeBinding fechaFinDocBase = ADFUtils.findControlBinding("FechaFinDispDoctoBase");
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
        Timestamp fechaPublicacionT = null;
        Timestamp fechaFinDocBaseT = null;
        
        try {
            if (null != fechaPublicacion && null != fechaPublicacion.getInputValue()) {
                fechaPublicacionT = (Timestamp)fechaPublicacion.getInputValue();
            }
        } catch (Exception e) {
            logger.warning("No se pudo convertir la fecha", e);
        }
        
        try {
            if (null != fechaFinDocBase && null != fechaFinDocBase.getInputValue()) {
                fechaFinDocBaseT = (Timestamp)fechaFinDocBase.getInputValue();
            }
        } catch (Exception e) {
            logger.warning("No se pudo convertir la fecha", e);
        }
        
        if (null != fechaPublicacionT && null != fechaFinDocBaseT) {
            if (fechaPublicacionT.compareTo(fechaFinDocBaseT) == 1) {
                valida = Boolean.FALSE;
            }
        }
        
        return valida;
    }
}
