package org.bcie.fenix.common.model.vo;

import java.util.Calendar;

import oracle.jbo.domain.Timestamp;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.catalogobo.Catalogo;
import org.bcie.fenix.common.model.vo.common.SolicitudAprobacionVO;
import org.bcie.solicitudaprobacionbo.SolicitudAprobacion;

import static org.bcie.fenix.common.model.FenixModelConstants.NIVEL_APROBACION_COMITE_CREDITO_INT;
import static org.bcie.fenix.common.model.FenixModelConstants.NIVEL_APROBACION_PRESIDENCIA_INT;
import static org.bcie.fenix.common.model.FenixModelConstants.TIPO_REUNION_PRESENCIAL;
import static org.bcie.fenix.common.model.FenixModelConstants.TIPO_REUNION_VIRTUAL;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Nov 26 12:57:44 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SolicitudAprobacionVOImpl extends ViewObjectImpl implements SolicitudAprobacionVO {
    
    /**
     * Log de la aplicacion
     */
    private static ADFLogger logger = ADFLogger.createADFLogger(SolicitudAprobacionVOImpl.class);
    
    /**
     * Define nombre de View Criteria para buscar Solicitud Aprobacion por Id
     */
    public static final String SOL_APROB_POR_ID_VC = "SolicitudAprobacionPorIdVOCriteria";
    
    /**
     * Define nombre de View Criteria para buscar por Id de Solicitud de Aprobacion
     */
    public static final String BUSCAR_POR_ID_VC = "PorIdSolicitudVC";
    
    public static final String BUSCAR_POR_OPER_VC = "SolicitudAprobacionVOByIdOperCriteria";
    
    /**
     * This is the default constructor (do not remove).
     */
    public SolicitudAprobacionVOImpl() {
    }
    
    /**
     * Realiza la busqueda de Solicitud de Aprobacion por Id
     * @param id contiene id de Solicitud de Aprobacion
     * @param idOperacion contiene id de operacion
     */
    public void buscarSolicitudAprobacionPorId(Number id,
                                               Number idOperacion){
        if(id == null){
            return;
        }
        
        //Asigna valores de id a Bind Variables
        setpId(id);
        setpIdOperacion(idOperacion);
        
        //Ejecuta busqueda por VC
        buscarSolicitudAprobacionPorIdVC();
    }
    
    /**
     * Ejecuta View Criteria para buscar un registro de Solicitud Aprobacion
     */
    public void buscarSolicitudAprobacionPorIdVC(){
        
        try{
            ViewCriteria vc = getViewCriteria(SOL_APROB_POR_ID_VC);
            applyViewCriteria(vc);
            executeQuery();
            
            setCurrentRow(first());
        }catch(Exception e){
            logger.severe("Error en la ejecucion del view criteria: " + SOL_APROB_POR_ID_VC);
        }
    }
    
    /**
     * Modifica el valor de descripción de Nivel de Aprobacion
     * @param nivelAprob contiene cadena de Nivel de aprobacion
     */
    public void setDescNivelAprobacion(String nivelAprob){
        if(nivelAprob == null){
            return;
        }
        
        SolicitudAprobacionVORowImpl row = (SolicitudAprobacionVORowImpl) getCurrentRow();
        if(row != null){
            row.setDesNivelAprobacion(nivelAprob);
        }
    }

    /**
     * Returns the variable value for pId.
     * @return variable value for pId
     */
    public Number getpId() {
        return (Number) ensureVariableManager().getVariableValue("pId");
    }

    /**
     * Sets <code>value</code> for variable pId.
     * @param value value to bind as pId
     */
    public void setpId(Number value) {
        ensureVariableManager().setVariableValue("pId", value);
    }

    /**
     * Returns the variable value for pIdOperacion.
     * @return variable value for pIdOperacion
     */
    public Number getpIdOperacion() {
        return (Number) ensureVariableManager().getVariableValue("pIdOperacion");
    }

    /**
     * Sets <code>value</code> for variable pIdOperacion.
     * @param value value to bind as pIdOperacion
     */
    public void setpIdOperacion(Number value) {
        ensureVariableManager().setVariableValue("pIdOperacion", value);
    }
    
    /**
     * Crea un objeto de Solicitud de Aprobacion para el WS
     * @param idOperacion contiene id de operacion 
     * @param loginUsuario contiene nombre de usuario en session
     * @param tipoSolicitud contiene id de tipo solicitud
     * @param nivelAprob contiene id de nivel de aprobacion
     * @param tipoReunion contiene bandera para identificar el tipo de reunion, 1 = Presencia y 2 = virtual
     * @param fecha contiene fecha de reunion presencial
     * @param hora contiene hora de reunion presencial
     * @param lugar contiene lugar de reunion presencial
     * @param fechaApertura contiene fecha de inicio de reunion virtual
     * @param fechaTermino contiene fecha de termino de reunion virtual
     * @return devuelve objeto solicitud de aprobacion
     */
    public SolicitudAprobacion getSolicitudAprobWSNode(Long idOperacion,
                                                       String loginUsuario,
                                                       Integer tipoSolicitud,
                                                       Integer tipoReunion,
                                                       Date fecha,
                                                       Timestamp hora,
                                                       String lugar,
                                                       Date fechaApertura,
                                                       Date fechaTermino,
                                                       Integer nivelAprob){
        logger.entering(SolicitudAprobacionVOImpl.class.getName(), 
                        "getSolicitudAprobWSNode",
                        new Object[]{idOperacion,
                                     loginUsuario,
                                     tipoSolicitud,
                                     tipoReunion,
                                     fecha,
                                     hora,
                                     lugar,
                                     fechaApertura,
                                     fechaTermino,
                                     nivelAprob});
        
        SolicitudAprobacion solAprob = null;
        
        //Valida objetos de entrada
        if(idOperacion == null ||
           loginUsuario == null ||
           tipoSolicitud == null ||
           nivelAprob == null){
            logger.severe("Error los campos minimos requeridos son null");
           return solAprob; 
        }else{
            if(tipoReunion != null &&
               tipoReunion.equals(TIPO_REUNION_PRESENCIAL) &&
               nivelAprob.equals(NIVEL_APROBACION_COMITE_CREDITO_INT) && 
               (fecha== null ||
                hora == null ||
                lugar == null)
            ){
                logger.severe("Error faltan valores en los campos minimos para reunion presencial");
                return solAprob; 
            }else{
                if(tipoReunion != null &&
                   tipoReunion.equals(TIPO_REUNION_VIRTUAL) &&
                   nivelAprob.equals(NIVEL_APROBACION_COMITE_CREDITO_INT) && 
                   (fechaApertura == null || 
                    fechaTermino == null)
                ){
                    logger.severe("Error faltan valores en los campos minimos para reunion virtual");
                    return solAprob; 
                }else{
                    
                    if(nivelAprob.equals(NIVEL_APROBACION_PRESIDENCIA_INT) &&
                       (fechaApertura == null ||
                        fechaTermino == null)
                    ){
                        logger.severe("Error faltan valores en los campos minimos para solicitud nivel presidencia");
                        return solAprob;
                    }
                }
            }
        }
        
        solAprob = new SolicitudAprobacion();
        
        //Objetos comunes
        Catalogo cat = null;
        XMLGregorianCalendar xgcal = null;
        GregorianCalendar gc = null;
        
        //Estatus de creacion
        solAprob.setEstado(true);
        
        //Agrega Usuario
        solAprob.setLoginUsuario(loginUsuario);
        
        //Id de operacion
        solAprob.setIdOperacion(idOperacion);
        
        //Catalgo de Nivel de Aprobacion
        cat = new Catalogo();
        cat.setId(new Long(nivelAprob));
        solAprob.setNivelAprobacion(cat);
        
        if(nivelAprob.equals(NIVEL_APROBACION_COMITE_CREDITO_INT)){
            
            if(tipoReunion.equals(TIPO_REUNION_PRESENCIAL)){
                //Tipo Reunion Presencial
                solAprob.setEsReunionVirtual(false);

                try {
                    gc = new GregorianCalendar();
                    gc.setTime(fecha);
                    xgcal = 
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                } catch (DatatypeConfigurationException e) {
                    logger.severe("Error al convertir Fecha Inicio", e);
                }
                //Asigna fecha de inicio
                solAprob.setFechaInicio(xgcal);
                
                try {
                    gc = new GregorianCalendar();
                    gc.setTimeInMillis(hora.getTime());
                    xgcal = 
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                } catch (DatatypeConfigurationException e) {
                    logger.severe("Error al convertir Hora", e);
                }
                //Asigna Hora de la Reunion
                solAprob.setHoraReunion(xgcal);
                
                //Asigna Lugar de la Reunion
                solAprob.setLugar(lugar);
            }
            
            if(tipoReunion.equals(TIPO_REUNION_VIRTUAL)){
                //Tipo Reunion Virtual
                solAprob.setEsReunionVirtual(true);

                try {
                    gc = new GregorianCalendar();
                    gc.setTime(fechaApertura);
                    xgcal = 
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                } catch (DatatypeConfigurationException e) {
                    logger.severe("Error al convertir Fecha Inicio", e);
                }
                //Asigna fecha apertura
                solAprob.setFechaInicio(xgcal);
                
                try {
                    gc = new GregorianCalendar();
                    gc.setTime(fechaTermino);
                    xgcal = 
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                } catch (DatatypeConfigurationException e) {                    
                    logger.severe("Error al convertir Fecha", e);
                }
                //Asigna fecha de termino
                //solAprob.setFechaCierre(xgcal);
                solAprob.setFechaTermino(xgcal);
            }
        }else{
            if(nivelAprob.equals(NIVEL_APROBACION_PRESIDENCIA_INT)){
                
                solAprob.setEsReunionVirtual(true);
                
                try {
                    gc = new GregorianCalendar();
                    gc.setTime(fechaApertura);
                    xgcal = 
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                } catch (DatatypeConfigurationException e) {
                    logger.severe("Error al convertir Fecha Inicio", e);
                }
                //Asigna fecha apertura
                solAprob.setFechaInicio(xgcal);
                
                try {
                    gc = new GregorianCalendar();
                    gc.setTime(fechaTermino);
                    xgcal = 
                        DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                } catch (DatatypeConfigurationException e) {
                    logger.severe("Error al convertir Fecha", e);
                }
                //Asigna fecha de cierre
                //solAprob.setFechaCierre(xgcal);
                solAprob.setFechaTermino(xgcal);
            }
        }
        
        //Asigna tipo de solicitud
        cat = new Catalogo();
        cat.setId(new Long(tipoSolicitud));
        solAprob.setTipoSolicitud(cat);
        
        logger.exiting(SolicitudAprobacionVOImpl.class.getName(), 
                       "getSolicitudAprobWSNode", 
                       new Object[]{solAprob});
        return solAprob;
    }
    
    /**
     * Ejecuta View Criteria para buscar un registro de Solicitud Aprobacion
     */
    public void buscarSolicitudAprobacionPorIdVC(Number idSolicitud){
        
        setpId(idSolicitud);
        
        ViewCriteria vc = getViewCriteria(BUSCAR_POR_ID_VC);
        applyViewCriteria(vc);
        executeQuery();
        
        setCurrentRow(first());
    }
    
    /**
     * Asigna la fecha de cierre con la fecha actual
     * @return devuelve valor booleano, true si la asignacion es correcta o false en caso contrario
     */
    public boolean asignaFechaCierreConFechaActual(){
        
        boolean exito = false;
        SolicitudAprobacionVORowImpl row = 
            (SolicitudAprobacionVORowImpl) getCurrentRow();
        if(row != null){
            
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp time = new java.sql.Timestamp(cal.getTimeInMillis());
            row.setFechaCierre(time);
            if(row.getFechaCierre() != null){
                exito = true;
            }else{
                logger.severe("Error valor fecha no asignado");
            }
        }else{
            logger.severe("Error no existe registro actual que modificar");
        }
        
        return exito;
    }
    
    /**
     * Asigna el usuario en session al momento del cierre de votacion de la solicitud de aprobacion
     * @return devuelve valor booleano, true si la asignacion fue exitosa o false en caso contrario
     */
    public boolean asignaUsuarioCierre(){
        
        boolean exito = false;
        String usuario = null;
        SolicitudAprobacionVORowImpl row = 
            (SolicitudAprobacionVORowImpl) getCurrentRow();
        if(row != null){
            usuario = ADFContext.getCurrent().getSecurityContext().getUserName();
            row.setLoginUsuarioCierre(usuario);
            exito = true;
        }
        
        return exito;
    }
    
    public void buscarSolicitudAprobacionPorIdOperVC(Number idOperacion){
        logger.warning("Entra a buscarSolicitudAprobacionPorIdOperVC");
        setpIdOperacion(idOperacion);
        
        ViewCriteria vc = getViewCriteria(BUSCAR_POR_OPER_VC);
        applyViewCriteria(vc);
        executeQuery();
        
        setCurrentRow(last());
        logger.warning("Termina buscarSolicitudAprobacionPorIdOperVC");
    }

    /**
     * Returns the variable value for pIdCliente.
     * @return variable value for pIdCliente
     */
    public Long getpIdCliente() {
        return (Long) ensureVariableManager().getVariableValue("pIdCliente");
    }

    /**
     * Sets <code>value</code> for variable pIdCliente.
     * @param value value to bind as pIdCliente
     */
    public void setpIdCliente(Long value) {
        ensureVariableManager().setVariableValue("pIdCliente", value);
    }
    
    public Boolean isTipoReunionPresencial(Number id, Long idCliente) {
        logger.warning("*** Inicia metodo isTipoReunionPresencial");
        Boolean reunionPrecencial = null;
        
        if (id == null && idCliente == null) {
            logger.warning("*** El parametro id y el idCliente son requeridos para la busqueda");
            return null;
        } else {
            try {
                ViewCriteria vc = getViewCriteriaManager().getViewCriteria("SolicitudAprobacionByIdAndIdClienteVOCriteria");
                vc.ensureVariableManager().setVariableValue("pId", id);
                vc.ensureVariableManager().setVariableValue("pIdCliente", idCliente);
                applyViewCriteria(vc);
                executeQuery();
                
                if (getEstimatedRowCount() > 0) {
                    setCurrentRow(first());
                    try  {
                        Integer esReunionVirtual = (Integer) getCurrentRow().getAttribute("EsReunionVirtual");
                        reunionPrecencial = esReunionVirtual == 1 ? Boolean.FALSE : Boolean.TRUE;
                        logger.warning("reunionPrecencial recuperado -> " + reunionPrecencial);
                    } catch (Exception e) {
                        logger.log(ADFLogger.ERROR, "*** Error al obtener el atributo " + e.getMessage());
                    }
                } else {
                    logger.warning("El row recuperado es Null no hay coincidencias en la busqueda");
                }
            } catch (Exception e) {
                logger.log(ADFLogger.WARNING,
                           "*** Error al Buscar Por id e idCliente??->" + e.getClass() + ":" + e.getMessage());
            } finally {
                getViewCriteriaManager().removeApplyViewCriteriaName("SolicitudAprobacionByIdAndIdClienteVOCriteria");
                executeQuery();
            }
        }
        logger.warning("*** Termina metodo isTipoReunionPresencial");
        return reunionPrecencial;
    }
}

