package pa01lavadoactivosgenerichumantask.bean;

import java.io.IOException;
import java.io.Serializable;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.NodeList;

public class LavadoActivosBean extends FenixPanelBean implements Serializable {
    @SuppressWarnings("compatibility:2041691145505044965")
    private static final long serialVersionUID = 1L;
    
    private static ADFLogger logger = null;
        
    // Renders de p�gina Realizar an�lisis LAFT
    private Boolean esNuevaLaft = Boolean.FALSE; // Realizar an�lisis LAFT cuando es nueva
    private Boolean esActualizacionLaft = Boolean.FALSE; // Realizar an�lisis LAFT  por actualizaci�n de informaci�n
    private Boolean esExcepcionLaft = Boolean.FALSE; // Realizar an�lisis LAFT por excepci�n
    private Boolean esAsociacionLaft = Boolean.FALSE; // Realizar an�lisis LAFT por asociaci�n
    private oracle.jbo.domain.Number idDjAntesDeAsociar = null; // Variable para guardar el Id de la DJ, antes de asociar una con la aplicaci�n externa de LAA.
    
    // Renders de p�gina Recopilar Informacion OFIC
    private Boolean esSinDjOfic = Boolean.FALSE; // Recopilar Informacion OFIC sin DJ
    private Boolean esConDjOfic = Boolean.FALSE; // Recopilar Informacion OFIC con DJ
    private Boolean esNuevaOfic = Boolean.FALSE; // Recopilar Informacion OFIC cuando es nueva
    private Boolean esConDjAsociacionOfic = Boolean.FALSE; // Recopilar Informacion OFIC con DJ, por asociaci�n
    private Boolean esConDjExcepcionOfic = Boolean.FALSE; // Recopilar Informacion OFIC con DJ, por excepci�n
    private Boolean esPanelOficSinDjVisible = Boolean.TRUE; // Variable para ocultar panel OFIC sin DJ o por Seguimiento
    private Boolean esDjCreada = Boolean.FALSE; // Variable para deshabilitar el bot�n de "Crear nueva declaraci�n jurada" y ocultar el de "Duplicar declaraci�n jurada".
    private Boolean esDjDuplicada = Boolean.FALSE; // Variable para deshabilitar el bot�n de "Duplicar declaraci�n jurada" y ocultar el de "Crear nueva declaraci�n jurada".
    private Integer tipoSolicitudSinDj; // Panel primario Tipo de Solicitud, con radios "Nueva declaraci�n jurada", "asociaci�n de declaraci�n" y "excepci�n de declaraci�n" en Recopilar Informacion OFIC y Realizar An�lisis LAFT.
    private Integer tipoSolicitudAsoc; // Panel secundario Tipo de Solicitud, con radios "asociaci�n de declaraci�n" y "excepci�n de declaraci�n" en Recopilar Informacion OFIC y Realizar An�lisis LAFT.
    private Integer tipoSeguimiento; // Radios  Por Actualizaci�n/Por Alerta
    
    // Variables del Payload
    private String idOperacion;
    private String idTareaBpm;
    private String responsableRo;
    private String accionLaft;
    private String responsableOfic;
    private String instanciaProceso;
    private Boolean esExisteRetorno = Boolean.FALSE;
    private Boolean esSolicitoAnalista = Boolean.FALSE; 
    private Boolean esRequiereAsociacion = Boolean.FALSE;
    private Boolean esRequiereExcepcion = Boolean.FALSE;
    private Boolean esPorSeguimiento = Boolean.FALSE;
    private Boolean esPorVencimiento = Boolean.FALSE; // Flujo de Seguimiento
    private Boolean esPorNoticia = Boolean.FALSE; // Flujo de Seguimiento
    private Boolean esElevarOtraInstancia = Boolean.FALSE; // Flag para escenario Elevar a otra instancia, el cual no est� implementado
    
    // Respuesta del servicio Consultar Declaracion Jurada
    private transient Map<String, Object> respuestaServicio; // Esta variable se llena con el m�todo consultarDeclaracionJurada desde el LavadoActivosTaskflow
    private String idDeclaracion;

    
    public LavadoActivosBean() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void obtenerDatosDelProceso() throws WorkflowException {
        logger.log(ADFLogger.TRACE, "Inside obtenerDatosDelProceso.");
           
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        NodeList nlIdOperacion = null;
        NodeList nlIdTareaBpm = null;
        NodeList nlResponsableRo = null;
        NodeList nlAccionLaft = null;
        NodeList nlExisteRetorno = null;
        NodeList nlSolicitoAnalista = null;
        NodeList nlResponsableOfic = null;
        NodeList nlRequiereAsociacion = null;
        NodeList nlRequiereExcepcion = null;
        NodeList nlPorSeguimiento = null;
        NodeList nlPorVencimiento = null;
        NodeList nlPorNoticia = null;
        
        try {
            java.io.StringWriter writer = new java.io.StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            logger.log(ADFLogger.WARNING, "- Payload Lavado Activos -");
            logger.log(ADFLogger.WARNING, payloadAsString);
        }
        catch(IOException e){
            logger.log(ADFLogger.ERROR, e.getMessage());
        }
        
        nlIdOperacion = xmlPayload.getElementsByTagName("CodigoOperacion");         
        if(nlIdOperacion != null && (nlIdOperacion.getLength() > 0 )) {
            this.setIdOperacion((nlIdOperacion.item(0).getTextContent()));
            logger.log(ADFLogger.WARNING, "Id Operacion: " + this.getIdOperacion()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlIdTareaBpm = xmlPayload.getElementsByTagName("CodigoTarea");             
        if(nlIdTareaBpm != null && (nlIdTareaBpm.getLength() > 0)) {
            this.setIdTareaBpm((nlIdTareaBpm.item(0).getTextContent()));
            logger.log(ADFLogger.WARNING, "Id Tarea Bpm: " + this.getIdTareaBpm()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        instanciaProceso = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
        logger.log(ADFLogger.WARNING, "Instancia Proceso: " + instanciaProceso);
        
        nlResponsableRo = xmlPayload.getElementsByTagName("responsableRO");
        if(nlResponsableRo != null && (nlResponsableRo.getLength() > 0)){
            this.setResponsableRo(nlResponsableRo.item(0).getTextContent());
            logger.log(ADFLogger.WARNING, "Responsable Ro: " + this.getResponsableRo()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlAccionLaft = xmlPayload.getElementsByTagName("accionLAFT");
        if(nlAccionLaft != null && (nlAccionLaft.getLength() > 0)){
            this.setAccionLaft(nlAccionLaft.item(0).getTextContent());
            logger.log(ADFLogger.WARNING, "Accion Laft: " + this.getAccionLaft()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlExisteRetorno = xmlPayload.getElementsByTagName("existeRetorno");
        if(nlExisteRetorno != null && (nlExisteRetorno.getLength() > 0)) {
            this.setEsExisteRetorno(Boolean.valueOf(nlExisteRetorno.item(0).getTextContent()));
            logger.log(ADFLogger.WARNING, "Es Existe Retorno: " + this.getEsExisteRetorno()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlSolicitoAnalista = xmlPayload.getElementsByTagName("solicitoAnalista");
        if(nlSolicitoAnalista != null && (nlSolicitoAnalista.getLength() > 0)) {
            this.setEsSolicitoAnalista(Boolean.valueOf(nlSolicitoAnalista.item(0).getTextContent()));
            logger.log(ADFLogger.WARNING, "Es Solicito Analista: " + this.getEsSolicitoAnalista()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlResponsableOfic = xmlPayload.getElementsByTagName("responsableOFIC");
        if(nlResponsableOfic != null && (nlResponsableOfic.getLength() > 0)) {
            this.setResponsableOfic(nlResponsableOfic.item(0).getTextContent());
            logger.log(ADFLogger.WARNING, "Responsable Ofic: " + this.getResponsableOfic()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlRequiereAsociacion = xmlPayload.getElementsByTagName("requiereAsociacion");
        if(nlRequiereAsociacion != null && (nlRequiereAsociacion.getLength() > 0)){
            this.setEsRequiereAsociacion(Boolean.valueOf(nlRequiereAsociacion.item(0).getTextContent()));
            logger.log(ADFLogger.WARNING, "Es Requiere Asociacion: " + this.getEsRequiereAsociacion()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlRequiereExcepcion = xmlPayload.getElementsByTagName("requiereExcepcion");
        if(nlRequiereExcepcion != null && (nlRequiereExcepcion.getLength() > 0)) {
            this.setEsRequiereExcepcion(Boolean.valueOf(nlRequiereExcepcion.item(0).getTextContent()));
            logger.log(ADFLogger.WARNING, "Es Requiere Excepcion: " + this.getEsRequiereExcepcion()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlPorSeguimiento = xmlPayload.getElementsByTagName("porSeguimiento");
        if(nlPorSeguimiento != null && (nlPorSeguimiento.getLength() > 0)) {
            this.setEsPorSeguimiento(Boolean.valueOf(nlPorSeguimiento.item(0).getTextContent()));
            logger.log(ADFLogger.WARNING, "Es Por Seguimiento: " + this.getEsPorSeguimiento()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlPorVencimiento = xmlPayload.getElementsByTagName("porVencimiento");
        if(nlPorVencimiento != null && (nlPorVencimiento.getLength() > 0)) {
            this.setEsPorVencimiento(Boolean.valueOf(nlPorVencimiento.item(0).getTextContent()));
            if(getEsPorVencimiento())
                setTipoSeguimiento(1);
            
            logger.log(ADFLogger.WARNING, "Es Por Vencimiento: " + this.getEsPorVencimiento()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        nlPorNoticia = xmlPayload.getElementsByTagName("porNoticia");
        if(nlPorNoticia != null && (nlPorNoticia.getLength() > 0)) {
            this.setEsPorNoticia(Boolean.valueOf(nlPorNoticia.item(0).getTextContent()));
            
            if(getEsPorNoticia())
                setTipoSeguimiento(2);
            
            logger.log(ADFLogger.WARNING, "Es Por Noticia: " + this.getEsPorNoticia()); // Cambiarlo a trace una vez probado el mapeo
        }
        
        logger.warning("Valores al finalizar carga del payload.");
        impresionLogsDeclaracion();
    }
     
    public void asignarValores(){
        logger.warning("Inside asignarValores.");
        String idTaskBpm = null;
        
        if(getIdTareaBpm() != null && (getIdTareaBpm().trim().length() > 0)){
            
            idTaskBpm = getIdTareaBpm();
            
            logger.warning("Valores al iniciar el metodo.");
            impresionLogsDeclaracion();
            
            // Mapeo de consulta de datos del servicio. 
            // La variable respuestaServicio s�lo se llena cuando se consulta el servicio de consultar DJ
            if(respuestaServicio != null) {
                
                if((respuestaServicio.get("idDeclaracion") != null) 
                   && (!respuestaServicio.get("idDeclaracion").toString().trim().equals("0")))
                    this.setIdDeclaracion(String.valueOf(respuestaServicio.get("idDeclaracion")));
                
                if((respuestaServicio.get("esElevarOtraInstancia") != null)) { 
                    
                    // El servicio consultar DJ trae la bandera para prender elcheckbox Elevar a otra instancia 
                    this.setEsElevarOtraInstancia((Boolean)respuestaServicio.get("esElevarOtraInstancia"));
                }
                    
            }

            // L�gica de flujo de las pantallas
            if (idTaskBpm.equals("39")){
                logger.warning("*inf, LAFT tarea 39");
                logger.log(ADFLogger.WARNING, "Realizar an�lisis LAFT fondos externos");
                
            } else if (idTaskBpm.equals("41")) {
                logger.warning("*inf, LAFT tarea 41");
                if(getEsRequiereAsociacion()) {
                                
                    logger.log(ADFLogger.WARNING, "Realizar an�lisis LAFT por asociaci�n");
                    this.setEsAsociacionLaft(Boolean.TRUE);
                    
                    // Los valores que se pre-seleccionan a continuaci�n se sobreescriben en el bean de actions, 
                    // m�todo mappingBlobToBean(). Se asignan por si no exisitiera un registro guardado en la BD y 
                    // para NO acoplar fuertemente.
                    this.setTipoSolicitudSinDj(2); // pre seleccionado Solicitud de asociaci�n de declaraci�n
                    this.setTipoSolicitudAsoc(1); // pre seleccionado Solicitud de asociaci�n de declaraci�n (en modo read only)                                        
                }
                else if(getEsRequiereExcepcion()) {
                                    
                    logger.log(ADFLogger.WARNING,"Realizar an�lisis LAFT por excepcion");
                    this.setEsExcepcionLaft(Boolean.TRUE);
                    this.setTipoSolicitudSinDj(3); // pre seleccionado Solicitud de excepci�n de declaraci�n
                    this.setTipoSolicitudAsoc(2); // pre seleccionado Solicitud de excepci�n de declaraci�n (en modo read only)
                    
//                    if(getEsExisteRetorno()) { 
//                        // Incidencia FNXII-2933: Cuando una excepci�n es retornada, el comportamiento debe ser igual a 
//                        // si se hubiese creado una nueva declaraci�n jurada.
//                        logger.log(ADFLogger.WARNING, "Realizar an�lisis LAFT cuando es nueva");
//                        this.setEsNuevaLaft(Boolean.TRUE);
//                        this.setTipoSolicitudSinDj(1); // pre seleccionado "Nueva declaraci�n jurada"
//                        this.setEsDjCreada(true);// Deshabilitado el bot�n de "Crear nueva declaraci�n jurada"
//                    }
                }
                else if(getEsExisteRetorno()) {
                    
                    this.setEsActualizacionLaft(Boolean.TRUE);
                    logger.log(ADFLogger.WARNING, "Realizar an�lisis LAFT por actualizaci�n de informaci�n");
                }
                else {
                    
                    logger.log(ADFLogger.WARNING, "Realizar an�lisis LAFT cuando es nueva");
                    this.setEsNuevaLaft(Boolean.TRUE); 
                }
                                
            } else if (idTaskBpm.equals("40")) {
                logger.warning("*inf, LAFT tarea 40");
                if(this.getIdDeclaracion() == null) {
                    logger.log(ADFLogger.WARNING, "Recopilar Informacion OFIC Sin DJ");
                    this.setEsSinDjOfic(Boolean.TRUE);
                }
                else{
                                        
                    logger.warning("*inf, getEsRequiereAsociacion: "+getEsRequiereAsociacion());
                    logger.warning("*inf, getEsRequiereExcepcion: "+getEsRequiereExcepcion());
                    logger.warning("*inf, getEsExisteRetorno: "+getEsExisteRetorno());
                    
                    if(getEsRequiereAsociacion() || getEsRequiereExcepcion() || getEsExisteRetorno()) {
                        
                        if(getEsRequiereAsociacion()) {
                            
                            logger.log(ADFLogger.WARNING, "Recopilar Informacion OFIC Con DJ por asociaci�n");
                            this.setEsConDjAsociacionOfic(Boolean.TRUE);
                            
                            // Los valores que se pre-seleccionan a continuaci�n se sobreescriben en el bean de actions, 
                            // m�todo mappingBlobToBean(). Se asignan por si no exisitiera un registro guardado en la BD y 
                            // para NO acoplar fuertemente.
                            this.setTipoSolicitudSinDj(2); // pre seleccionado Solicitar asociaci�n de declaraci�n
                            this.setTipoSolicitudAsoc(1); // pre seleccionado Solicitar asociaci�n de declaraci�n
                        }
                        else if(getEsRequiereExcepcion()) {
                                
                            logger.log(ADFLogger.WARNING, "Recopilar Informacion OFIC Con DJ por excepci�n");
                            this.setEsConDjExcepcionOfic(Boolean.TRUE);
                            
                            this.setTipoSolicitudSinDj(3); // pre seleccionado Solicitar excepci�n de declaraci�n
                            this.setTipoSolicitudAsoc(2); // pre seleccionado Solicitar excepci�n de declaraci�n
                            
                            if(getEsExisteRetorno()) {
                                // Incidencia FNXII-2933: Cuando una excepci�n es retornada, el comportamiento debe ser igual a 
                                // si se hubiese creado una nueva declaraci�n jurada.  
                                // Incidencia FNXII-3282: (ademas de lo anterior) permitiendo capturar la fecha de firma, 
                                // la declaraci�n jurada y responder el cuestionario.
                                logger.warning("*inf, Recopilar Informacion OFIC Con DJ Retorno");
                                this.setEsNuevaOfic(Boolean.TRUE); // Deshabilitado panel "Tipo de Solicitud"
                                this.setTipoSolicitudSinDj(3); // pre seleccionado "Nueva declaraci�n jurada"
                                this.setEsDjCreada(true);// Deshabilitado el bot�n de "Crear nueva declaraci�n jurada"
                            }
                        }
                    }
                    else{
                        logger.log(ADFLogger.WARNING, "Recopilar Informacion OFIC Con DJ");
                        this.setEsConDjOfic(Boolean.TRUE); 
                    }
                }
            }     
        }
        
        logger.warning("Valores al finalizar el metodo.");
        impresionLogsDeclaracion();
    }
    
    public void impresionLogsDeclaracion() {
        logger.warning("IdTareaBpm: " + getIdTareaBpm());
        logger.warning("IdDeclaracion: " + getIdDeclaracion());
        logger.warning("EsSinDjOfic: " + getEsSinDjOfic());
        logger.warning("EsConDjAsociacionOfic: " + getEsConDjAsociacionOfic());
        logger.warning("EsConDjExcepcionOfic: " + getEsConDjExcepcionOfic());
        logger.warning("EsNuevaOfic: " + getEsNuevaOfic());
        logger.warning("TipoSolicitudSinDj: " + getTipoSolicitudSinDj());
        logger.warning("TipoSeguimiento: " + getTipoSeguimiento());
        logger.warning("EsPorSeguimiento: " + getEsPorSeguimiento());
        logger.warning("TipoSolicitudSinDj: " + getTipoSolicitudSinDj());
    }
            
    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getIdOperacion() {
        return idOperacion;
    }

    public void setIdTareaBpm(String idTareaBpm) {
        this.idTareaBpm = idTareaBpm;
    }

    public String getIdTareaBpm() {
        return idTareaBpm;
    }

    public void setEsElevarOtraInstancia(Boolean esElevarOtraInstancia) {
        this.esElevarOtraInstancia = esElevarOtraInstancia;
    }

    public Boolean getEsElevarOtraInstancia() {
        return esElevarOtraInstancia;
    }
    
    public void setEsPorVencimiento(Boolean esPorVencimiento) {
        this.esPorVencimiento = esPorVencimiento;
    }

    public Boolean getEsPorVencimiento() {
        return esPorVencimiento;
    }

    public void setEsPorNoticia(Boolean esPorNoticia) {
        this.esPorNoticia = esPorNoticia;
    }

    public Boolean getEsPorNoticia() {
        return esPorNoticia;
    }
    
    public void setEsPanelOficSinDjVisible(Boolean esPanelOficSinDjVisible) {
        this.esPanelOficSinDjVisible = esPanelOficSinDjVisible;
    }

    public Boolean getEsPanelOficSinDjVisible() {
        return esPanelOficSinDjVisible;
    }

    public void setIdDeclaracion(String idDeclaracion) {
        this.idDeclaracion = idDeclaracion;
    }

    public String getIdDeclaracion() {
        return idDeclaracion;
    }

    public void setTipoSolicitudSinDj(Integer tipoSolicitudSinDj) {
        this.tipoSolicitudSinDj = tipoSolicitudSinDj;
    }

    public Integer getTipoSolicitudSinDj() {
        return tipoSolicitudSinDj;
    }

    public void setRespuestaServicio(Map<String, Object> respuestaServicio) {
        this.respuestaServicio = respuestaServicio;
    }

    public Map<String, Object> getRespuestaServicio() {
        return respuestaServicio;
    }
    
    public void setEsNuevaLaft(Boolean esNuevaLaft) {
        this.esNuevaLaft = esNuevaLaft;
    }

    public Boolean getEsNuevaLaft() {
        return esNuevaLaft;
    }

    public void setEsActualizacionLaft(Boolean esActualizacionLaft) {
        this.esActualizacionLaft = esActualizacionLaft;
    }

    public Boolean getEsActualizacionLaft() {
        return esActualizacionLaft;
    }

    public void setEsExcepcionLaft(Boolean esExcepcionLaft) {
        this.esExcepcionLaft = esExcepcionLaft;
    }

    public Boolean getEsExcepcionLaft() {
        return esExcepcionLaft;
    } 
    
    public void setAccionLaft(String accionLaft) {
        this.accionLaft = accionLaft;
    }

    public String getAccionLaft() {
        return accionLaft;
    }

    public void setResponsableOfic(String responsableOfic) {
        this.responsableOfic = responsableOfic;
    }

    public String getResponsableOfic() {
        return responsableOfic;
    }
    
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setResponsableRo(String responsableRo) {
        this.responsableRo = responsableRo;
    }

    public String getResponsableRo() {
        return responsableRo;
    }

    public void setEsExisteRetorno(Boolean esExisteRetorno) {
        this.esExisteRetorno = esExisteRetorno;
    }

    public Boolean getEsExisteRetorno() {
        return esExisteRetorno;
    }

    public void setEsSolicitoAnalista(Boolean esSolicitoAnalista) {
        this.esSolicitoAnalista = esSolicitoAnalista;
    }

    public Boolean getEsSolicitoAnalista() {
        return esSolicitoAnalista;
    }

    public void setEsRequiereAsociacion(Boolean esRequiereAsociacion) {
        this.esRequiereAsociacion = esRequiereAsociacion;
    }

    public Boolean getEsRequiereAsociacion() {
        return esRequiereAsociacion;
    }

    public void setEsRequiereExcepcion(Boolean esRequiereExcepcion) {
        this.esRequiereExcepcion = esRequiereExcepcion;
    }

    public Boolean getEsRequiereExcepcion() {
        return esRequiereExcepcion;
    }
    
    public void setEsNuevaOfic(Boolean esNuevaOfic) {
        this.esNuevaOfic = esNuevaOfic;
    }

    public Boolean getEsNuevaOfic() {
        return esNuevaOfic;
    }

    public void setEsConDjOfic(Boolean esConDjOfic) {
        this.esConDjOfic = esConDjOfic;
    }

    public Boolean getEsConDjOfic() {
        return esConDjOfic;
    }

    public void setEsSinDjOfic(Boolean esSinDjOfic) {
        this.esSinDjOfic = esSinDjOfic;
    }

    public Boolean getEsSinDjOfic() {
        return esSinDjOfic;
    }

    public void setEsConDjExcepcionOfic(Boolean esConDjExcepcionOfic) {
        this.esConDjExcepcionOfic = esConDjExcepcionOfic;
    }

    public Boolean getEsConDjExcepcionOfic() {
        return esConDjExcepcionOfic;
    }

    public void setEsConDjAsociacionOfic(Boolean esConDjAsociacionOfic) {
        this.esConDjAsociacionOfic = esConDjAsociacionOfic;
    }

    public Boolean getEsConDjAsociacionOfic() {
        return esConDjAsociacionOfic;
    }
            
    public void setTipoSolicitudAsoc(Integer tipoSolicitudAsoc) {
        this.tipoSolicitudAsoc = tipoSolicitudAsoc;
    }

    public Integer getTipoSolicitudAsoc() {
        return tipoSolicitudAsoc;
    }
    
    public void setTipoSeguimiento(Integer tipoSeguimiento) {
        this.tipoSeguimiento = tipoSeguimiento;
    }

    public Integer getTipoSeguimiento() {
        return tipoSeguimiento;
    }
    
    public void setEsAsociacionLaft(Boolean esAsociacionLaft) {
        this.esAsociacionLaft = esAsociacionLaft;
    }

    public Boolean getEsAsociacionLaft() {
        return esAsociacionLaft;
    }
    
    public void setEsPorSeguimiento(Boolean esPorSeguimiento) {
        this.esPorSeguimiento = esPorSeguimiento;
    }

    public Boolean getEsPorSeguimiento() {
        return esPorSeguimiento;
    }
    
    public void setEsDjCreada(Boolean esDjCreada) {
        this.esDjCreada = esDjCreada;
    }

    public Boolean getEsDjCreada() {
        return esDjCreada;
    }
    
    public void setEsDjDuplicada(Boolean esDjDuplicada) {
        this.esDjDuplicada = esDjDuplicada;
    }

    public Boolean getEsDjDuplicada() {
        return esDjDuplicada;
    }
    
    public void setIdDjAntesDeAsociar(oracle.jbo.domain.Number idDjAntesDeAsociar) {
        this.idDjAntesDeAsociar = idDjAntesDeAsociar;
    }

    public oracle.jbo.domain.Number getIdDjAntesDeAsociar() {
        return idDjAntesDeAsociar;
    }
}
