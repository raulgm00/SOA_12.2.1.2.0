package pc04aprobaciongenerichumantask.bean;

import java.io.IOException;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jbo.domain.Timestamp;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AprobacionBean extends FenixPanelBean implements Serializable {

    @SuppressWarnings("compatibility:-7683741210102923315")
    private static final long serialVersionUID = -4676803582748166716L;

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(AprobacionBean.class);

    /**
     * Constates para manejo de Payload
     */
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String REUNION_PRESENCIAL = "reunionPresencial";    
    private static final String ID_SOLICITUD = "IdSolicitud";
    private static final String ID_TIPO_DECISION = "IdTcaTipoDecision";
    private static final String TIPO_SOLICITUD = "IdTipoAprobacion";
    private static final String ID_USUARIO_REUNION = "IdUsuarioReunion";
    private static final String ID_ROL_BPM = "CodigoRol";
    private static final String NIVEL_APROBACION ="idNivelAprobacion";
    //Constantes Aprobacion Cliente
    public static final String BEAN_EXPRESSION = "#{pageFlowScope.aprobacionManagedBean}";
    private static final String CODIGO_CLIENTE = "IdCliente";
    private static final String TIPO_INICIO = "IdTipoAprobacion";
    private static final String TI_MEJORA_SCR = "MejoraSCR";
    private static final String TI_ARGUMENTACION = "Argumentacion";
    private static final String IDFELXCUBE = "IdFlexCube";
    private static final String IDFLUJO = "IdFlujo";
    private static final String ACCION = "accion";
    private static final String USUARIO_PROCESO = "UsuarioIniciaProceso";
    private static final String PROCESO_SEGUIMIENTO = "InstanciaProcesoSeguimiento";
    //fin Constantes Aprobacion Cliente

    /**
     * Variables agregadas para nuevo metodo getPayLoadInformation
     */
    private String idOperacion = "";
    private String idTarea = "";
    private Boolean reunionPresencial = Boolean.FALSE;

    private Date fechaReunion;
    private String horaReunion;
    private String lugarReunion;
    private String fechaApertura;
    private Date fechaCierre;
    private String comentario;
    private String idSolicitud;
    private String numeroAcuerdo;
    private String acuerdo;
    private String numeroActa;
    private String idUsuarioReunion;
    private String idTcaTipoDecision;
    private String idVotoAprobacion;
    private String accionASeguir;
    private String observaciones;
    private Timestamp fechaCierreActual;
    private Boolean seAgregoVotoFueraSistem;
    private Boolean operLineaCreacion;
    private String operCondicion; //CMR Create Edit Read
    private String idRolBPM;
    
    private Long idAccionSeguir = null;
    private Boolean lineaCredito;
    private Long lngIdOperacion;
    private Integer intIdNivelAprob;
    private Integer intIdTipoSolicitud;
    private Integer intTipoReunion;
    private Long lngIdSolicitudAprob;
    
    private String tituloPopup;
    
    private Map<String,Object> mapaAtributosSolicitud;
    
    private String instanciaProceso;
    //variables Aprobacion Cliente
    private String idCliente;
    private Long idClienteFlex;
    private String tipoInicio; 
    private String idFlexcube;
    private Integer claveTarea;
    private Long claveFlujo;
    private oracle.jbo.domain.Number idFlujo;
    private String scrVigente;
    private String scrRecomendado;
    private String instanciaProcesoSeguimiento;
    
    private Integer idTcaScrRecomendado;
    private Integer idTcaScrVigente;
    
    private String idTipoReunion;
    private String tipoAccion;

    private Map<String, Row> mapaEquipoTrabajo;
    private Map<String, Row> mapaConNotificacion;
    private Map<Integer, Row> mapaComiteCredito;
    
    private Map<String, Integer> conNotificacionMap;
    private Map<Integer, String> comiteCreditoMap;
    
    private String loginUsuario;
    private String nombreUsuario;
    //VA_02 Esta opcion se habilitara cuando exista al menos un cambio
    //en las tablas Comite de Credito y/o Con notificacion.
    private Boolean existenCambiosComiteCredito;
    private Boolean existenCambiosConNotificacion;
    
    private oracle.jbo.domain.Number IdTipoAccion;
    private String codigoTipoAccion;
    Long aprobacionId = null;
    
    private Boolean existeCondicion;
    
    
    private Boolean esEstadoCompletado = Boolean.FALSE;//Variable Estado BPM

    public void setAprobacionId(Long aprobacionId) {
        this.aprobacionId = aprobacionId;
    }

    public Long getAprobacionId() {
        return aprobacionId;
    }

    public void setMapaEquipoTrabajo(Map<String, Row> mapaEquipoTrabajo) {
        this.mapaEquipoTrabajo = mapaEquipoTrabajo;
    }
    
    public Map<String, Row> getMapaEquipoTrabajo() {
        return this.mapaEquipoTrabajo;
    }
    
    public void setMapaConNotificacion(Map<String, Row> mapaConNotificacion) {
        this.mapaConNotificacion = mapaConNotificacion;
    }
    
    public Map<String, Row> getMapaConNotificacion() {
        return this.mapaConNotificacion;
    }
    
    public void setMapaComiteCredito(Map<Integer, Row> mapaComiteCredito) {
        this.mapaComiteCredito = mapaComiteCredito;
    }
    
    public Map<Integer, Row> getMapaComiteCredito() {
        return this.mapaComiteCredito;
    }

    public void setOperCondicion(String operCondicion)
  {
    this.operCondicion = operCondicion;
  }

  public String getOperCondicion()
  {
    return operCondicion;
  }

  public void setOperLineaCreacion(Boolean operLineaCreacion)
    {
      this.operLineaCreacion = operLineaCreacion;
    }
  
    public Boolean getOperLineaCreacion()
    {
      return operLineaCreacion;
    }

    public void setMapaAtributosSolicitud(Map<String, Object> mapaAtributosSolicitud) {
        this.mapaAtributosSolicitud = mapaAtributosSolicitud;
    }

    public void setSeAgregoVotoFueraSistem(Boolean seAgregoVotoFueraSistem) {
        this.seAgregoVotoFueraSistem = seAgregoVotoFueraSistem;
    }

    public Boolean getSeAgregoVotoFueraSistem() {
        return seAgregoVotoFueraSistem;
    }

    public Map<String, Object> getMapaAtributosSolicitud() {
        return mapaAtributosSolicitud;
    }

    public void setFechaCierreActual(Timestamp fechaCierreActual) {
        this.fechaCierreActual = fechaCierreActual;
    }

    public Timestamp getFechaCierreActual() {
        return fechaCierreActual;
    }

    public AprobacionBean() {
        super();
    }

    public void getPayloadInformation() throws WorkflowException, IOException {
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();

        //if (LOGGER.isFiner()) {
            //get the payload as a simple string, useful for debugging
            java.io.StringWriter writer = new java.io.StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            LOGGER.warning("BPM Payload " + payloadAsString);
        //}
        NodeList nl;
        
        nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if (nl.getLength() > 0) {
            idTarea = nl.item(0).getTextContent();
            if(null!=idTarea && idTarea.length()>0){
                claveTarea=Integer.parseInt(idTarea);
                }
        }

        nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if (nl.getLength() > 0) {
            idOperacion = nl.item(0).getTextContent();
            
            try {
                lngIdOperacion = Long.valueOf(idOperacion);
            } catch(Exception e) {
                LOGGER.severe("Error al convertir valor de idOperacion a Long", e);
            }
        }

        nl = xmlPayload.getElementsByTagName(REUNION_PRESENCIAL);
        if (nl.getLength() > 0) {
            String valor = nl.item(0).getTextContent();
            try{
                reunionPresencial = Boolean.parseBoolean(valor);
            }catch(Exception e){
                LOGGER.severe("Error al convertir valor de Reunion Presencial a Booleano", e);
            }
        }
        
        nl = xmlPayload.getElementsByTagName(ACCION);
        if(nl.getLength() > 0) {
            tipoAccion = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(ID_SOLICITUD);
        if(nl.getLength() > 0) {
            idSolicitud = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(ID_TIPO_DECISION);
        if(nl.getLength() > 0){
            idTcaTipoDecision = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(ID_USUARIO_REUNION);
        if(nl.getLength() > 0){
            idUsuarioReunion = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(TIPO_SOLICITUD);
        if(nl.getLength() > 0) {
            String strIdTipoSolicitud = null;
            try{
                strIdTipoSolicitud = nl.item(0).getTextContent();
                intIdTipoSolicitud = Integer.parseInt(strIdTipoSolicitud);               
            }catch(Exception e){
                LOGGER.severe("Error al obtener Id de Tipo Solicitud", e);
            }
        }
        
        nl = xmlPayload.getElementsByTagName(ID_ROL_BPM);
        if(nl.getLength() > 0){
            idRolBPM = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(NIVEL_APROBACION);
        if(nl.getLength() > 0){
            try{
                intIdNivelAprob = Integer.parseInt( nl.item(0).getTextContent());                
            }catch(Exception e){
                LOGGER.severe("Error al obtener Id de Nivel Aprobacion", e);
            }
        }
        
        //Inicializacion de variables Aprobacion Cliente
        nl = xmlPayload.getElementsByTagName(IDFELXCUBE);
        if (nl.getLength() > 0) {
            LOGGER.warning("IdFLEXCUBE del payload: "+ nl.item(0).getTextContent());
            setIdFlexcube(nl.item(0).getTextContent());
            LOGGER.warning("IdFLEXCUBE: "+ getIdFlexcube());
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_CLIENTE);
        if (nl.getLength() > 0) {
            idCliente = nl.item(0).getTextContent();
            if(null!=idCliente && idCliente.length()>0){
                    idClienteFlex=Long.parseLong(idCliente);                    
                }
            else{
                    idClienteFlex=null;
                }
        }
        
        //10/03/2020@JURBINA Se obtiene la instancia del proceso de seguimientoCrediticio
        try
        {
            NodeList nlName = xmlPayload.getElementsByTagName("parameterName");
            nl = xmlPayload.getElementsByTagName("parameterValue");
            if (nl.getLength() > 0 && nlName.getLength() > 0) 
            {
                for(int i = 0; i < nlName.getLength(); i++)
                {
                    if(nlName.item(i).getTextContent().equals("INSTANCIA_SEGUIMIENTO"))
                    {
                        instanciaProcesoSeguimiento = nl.item(i).getTextContent();
                    }
                }
            }
        }
        catch(Exception e)
        {
            LOGGER.log(ADFLogger.ERROR, "Error al obtener la instancia seguimiento: " + e.getMessage());
        }
        LOGGER.warning("BPM instanciaProcesoSeguimiento " + instanciaProcesoSeguimiento);
        
        nl = xmlPayload.getElementsByTagName(TIPO_INICIO);
        if (nl.getLength() > 0) {
            String strIdTipoAprobacion = nl.item(0).getTextContent();            
            if(null!=strIdTipoAprobacion && strIdTipoAprobacion.length()>0){
                    Integer intIdTipoAprobacion=Integer.parseInt(strIdTipoAprobacion);
                    if(intIdTipoAprobacion.compareTo(3)==0){tipoInicio=TI_MEJORA_SCR;}
                    if(intIdTipoAprobacion.compareTo(4)==0){tipoInicio=TI_ARGUMENTACION;}
                }
        }
        
        nl = xmlPayload.getElementsByTagName(IDFLUJO);
        if (nl.getLength() > 0) {
            try {
                setIdFlujo(new oracle.jbo.domain.Number(nl.item(0).getTextContent()));
                claveFlujo=idFlujo.longValue();
            } catch (Exception e) {
                LOGGER.log(ADFLogger.ERROR, "Error al inicializar el valor de IdFlujo: " + e.getMessage());
            }
        }
        //Fin de Inicializacion de variables Aprobacion Cliente
        
        String usuario=null;
        nl = xmlPayload.getElementsByTagName(USUARIO_PROCESO);
        if (nl.getLength() > 0) {
            usuario = nl.item(0).getTextContent();
        }
        
        if (null != JSFUtils.resolveExpression("#{securityContext.userName}")) {
            setLoginUsuario((String) JSFUtils.resolveExpression("#{securityContext.userName}"));
            LOGGER.warning("get payload loginUsuario: " + getLoginUsuario());
            
            if (getLoginUsuario().equalsIgnoreCase("anonymous") || getLoginUsuario().equalsIgnoreCase("weblogic")) {
                LOGGER.warning("login anonymous || weblogic  reasignando valor a USUARIO_PROCESO recibido de payload");
                if (usuario != null) {
                    setLoginUsuario(usuario);
                }
            }
            
            
        } else {
            LOGGER.warning("Importante! securityContext.userName resuelto a null");
            if (usuario != null) {
                setLoginUsuario(usuario);
            }
        }
        
        nombreUsuario=obtenerNombreUsuario(loginUsuario);
        obtenerInstanciaProceso();
        
        setIdAccionSeguir(0L);
        setLineaCredito(Boolean.FALSE);
        LOGGER.warning(CODIGO_TAREA + idTarea);
        LOGGER.warning(CODIGO_OPERACION + idOperacion);
        LOGGER.warning(CODIGO_CLIENTE + idCliente);
        LOGGER.warning(TIPO_INICIO + tipoInicio);
        LOGGER.warning("idSolicitud: " + idSolicitud);
        LOGGER.warning("tipoAccion: " + tipoAccion);
        LOGGER.warning("loginUsuario: " + getLoginUsuario());
        LOGGER.warning("nombreUsuario: " + nombreUsuario);
    }
    
    public void cargarDatosSeguimiento(){
        try{
            setFechaCierreActual((Timestamp) mapaAtributosSolicitud.get("FechaCierreActual"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /** obtenerScrVigente"
    * Metodo que obtiene el SCR vigente por idCliente
    * @param  Long idCliente
    * @since 31/10/2016
    * @by Gabriel Niño Rosales
    */
    @SuppressWarnings("unchecked")
    public Integer obtenerScrVigente(Long idCliente){
        LOGGER.log(ADFLogger.WARNING, "Dentro de obtenerScrVigente, idCliente : " + idCliente);
        Integer idTcaScr = null;
        try{
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("consultarSCRClienteByIdCliente");
        operationBinding.getParamsMap().put("idCliente", idCliente);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else {
            if(operationBinding.getResult() != null){
                Long idTcaScrLong= (Long) operationBinding.getResult();
                idTcaScr = idTcaScrLong.intValue();
            } 
        }
        }catch(Exception e){
            LOGGER.log(ADFLogger.ERROR, "Error en obtenerScrVigente: " + e.getMessage());
        }
        LOGGER.log(ADFLogger.WARNING, "retorna  : " + idTcaScr);
        return idTcaScr;
    }
    
    /** obtenerScrVigente"
    * Metodo que obtiene el SCR recomendado por idCliente (De seguimiento crediticio)
    * @param  Long idCliente
    * @since 31/10/2016
    * @by Gabriel Niño Rosales
    */
    @SuppressWarnings("unchecked")
    public Integer obtenerScrRecomendado(Long idCliente){
        LOGGER.log(ADFLogger.WARNING, "Dentro de obtenerScrRecomendado, idCliente : " + idCliente);
        Integer idTcaScr = null;
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("obtenerIdTcaSrcRecomendado1");
            operationBinding.getParamsMap().put("banEstatus", 0);
            operationBinding.getParamsMap().put("idCliente", idCliente);
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }else {
            if(operationBinding.getResult() != null){
                idTcaScr= (Integer) operationBinding.getResult();
            } 
        }
        }catch(Exception e){
            LOGGER.log(ADFLogger.ERROR, "Error en obtenerScrRecomendado: " + e.getMessage());
        }
        LOGGER.log(ADFLogger.WARNING, "retorna  : " + idTcaScr);
        return idTcaScr;
    }

    public String getIdTarea() {
        return this.idTarea;
    }

    public String getIdOperacion() {
        return this.idOperacion;
    }

    public Boolean getReunionPresencial() {
        return reunionPresencial;
    }

    public void setFechaReunion(Date fechaReunion) {
        this.fechaReunion = fechaReunion;
    }

    public Date getFechaReunion() {
        return fechaReunion;
    }

    public void setHoraReunion(String horaReunion) {
        this.horaReunion = horaReunion;
    }

    public String getHoraReunion() {
        return horaReunion;
    }

    public void setLugarReunion(String lugarReunion) {
        this.lugarReunion = lugarReunion;
    }

    public String getLugarReunion() {
        return lugarReunion;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }
    
    public void setLngIdOperacion(Long lngIdOperacion) {
        this.lngIdOperacion = lngIdOperacion;
    }

    public Long getLngIdOperacion() {
        lngIdOperacion = Long.parseLong(idOperacion);
        return lngIdOperacion;
    }


    public void setTituloPopup(String tituloPopup) {
        this.tituloPopup = tituloPopup;
    }

    public String getTituloPopup() {
        return tituloPopup;
    }
    
    /**Tree Table Binding in managed bean*/
    private transient RichTreeTable treeTabBind;

    public void setTreeTabBind(RichTreeTable treeTabBind) {
        this.treeTabBind = treeTabBind;
    }

    public RichTreeTable getTreeTabBind() {
        return treeTabBind;
    }
    
    public void setIntIdNivelAprob(Integer intIdNivelAprob) {
        this.intIdNivelAprob = intIdNivelAprob;
    }

    public Integer getIntIdNivelAprob() {
        /*try{
            intIdNivelAprob = 
                Integer.parseInt(ADFUtils.getBoundAttributeValue(AprobacionActionBean.ID_NIVEL_APROB_ATRIBUTO).toString());    
        }catch(Exception e){
            LOGGER.severe("Error al obtener Id de Nivel Aprobacion", e);
        }*/
        return intIdNivelAprob;
    }
    
    public void setIntIdTipoSolicitud(Integer intIdTipoSolicitud) {
        this.intIdTipoSolicitud = intIdTipoSolicitud;
    }

    public Integer getIntIdTipoSolicitud() {
        return intIdTipoSolicitud;
    }
    
    public void iniciaConsolidarDecision(){
        
        //Obtiene valores
        
        //Llama operator bindings
    }

    public void setNumeroAcuerdo(String numeroAcuerdo) {
        this.numeroAcuerdo = numeroAcuerdo;
    }

    public String getNumeroAcuerdo() {    
        return numeroAcuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setNumeroActa(String numeroActa) {
        this.numeroActa = numeroActa;
    }

    public String getNumeroActa() {
        return numeroActa;
    }
    
    public void setIdVotoAprobacion(String idVotoAprobacion) {
        this.idVotoAprobacion = idVotoAprobacion;
    }

    public String getIdVotoAprobacion() {
        return idVotoAprobacion;
    }

    public void setIdUsuarioReunion(String idUsuarioReunion) {
        this.idUsuarioReunion = idUsuarioReunion;
    }

    public String getIdUsuarioReunion() {
        return idUsuarioReunion;
    }

    public void setIdTcaTipoDecision(String idTcaTipoDecision) {
        this.idTcaTipoDecision = idTcaTipoDecision;
    }

    public String getIdTcaTipoDecision() {
        return idTcaTipoDecision;
    }


    public void setAccionASeguir(String accionASeguir) {
        this.accionASeguir = accionASeguir;
    }

    public String getAccionASeguir() {
        return accionASeguir;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }
    
    public void setIntTipoReunion(Integer intTipoReunion) {
        this.intTipoReunion = intTipoReunion;
    }

    public Integer getIntTipoReunion() {
        if(reunionPresencial){
            intTipoReunion = 1;
        }else{
            intTipoReunion = 2;            
        }
        return intTipoReunion;
    }
    
    public void setLngIdSolicitudAprob(Long lngIdSolicitudAprob) {
        this.lngIdSolicitudAprob = lngIdSolicitudAprob;
    }

    public Long getLngIdSolicitudAprob() {
        
        if(idSolicitud != null && !idSolicitud.isEmpty() && !idSolicitud.equals("")){
            try{
                lngIdSolicitudAprob = Long.parseLong(idSolicitud);
            }catch(Exception e){
                LOGGER.severe("Error al convertir String Id Solicitud a Long", e);
            }
        }else{
            LOGGER.warning("No se pudo recuperar el Id de la solicitud del payload");
        }
        
        return lngIdSolicitudAprob;
    }

    public String getIdRolBPM() {
        return idRolBPM;
    }

    public Long getIdAccionSeguir() {
        return idAccionSeguir;
    }

    public void setIdAccionSeguir(Long idAccionSeguir) {
        this.idAccionSeguir = idAccionSeguir;
    }

    public Boolean getLineaCredito() {
        return lineaCredito;
    }

    public void setLineaCredito(Boolean lineaCredito) {
        this.lineaCredito = lineaCredito;
    }
    
    public void obtenerInstanciaProceso(){
        
        String instancia = null;
        try{
            if(BPMUtils.getCurrentTask() != null &&
               BPMUtils.getCurrentTask().getProcessInfo() != null &&
               BPMUtils.getCurrentTask().getProcessInfo().getInstanceId() != null){
                instancia = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();
            } 
        }catch(Exception e){
            LOGGER.severe("Error al obtener la Instancia del Proceso", e);
        }
        
        if(instancia != null){
            setInstanciaProceso(instancia);
        }
    }
    
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }


    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setInstanciaProcesoSeguimiento(String instanciaProcesoSeguimiento) {
        this.instanciaProcesoSeguimiento = instanciaProcesoSeguimiento;
    }

    public String getInstanciaProcesoSeguimiento() {
        return instanciaProcesoSeguimiento;
    }

    public void setIdClienteFlex(Long idClienteFlex) {
        this.idClienteFlex = idClienteFlex;
    }

    public Long getIdClienteFlex() {
        return idClienteFlex;
    }

    public void setTipoInicio(String tipoInicio) {
        this.tipoInicio = tipoInicio;
    }

    public String getTipoInicio() {
        return tipoInicio;
    }
    
    public void setIdFlexcube(String idFlexcube) {
        this.idFlexcube = idFlexcube;
    }

    public String getIdFlexcube() {
        return idFlexcube;
    }

    public void setClaveTarea(Integer claveTarea) {
        this.claveTarea = claveTarea;
    }

    public Integer getClaveTarea() {
        return claveTarea;
    }

    public void setClaveFlujo(Long claveFlujo) {
        this.claveFlujo = claveFlujo;
    }

    public Long getClaveFlujo() {
        return claveFlujo;
    }


    public void setIdFlujo(oracle.jbo.domain.Number idFlujo) {
        this.idFlujo = idFlujo;
    }

    public oracle.jbo.domain.Number getIdFlujo() {
        return idFlujo;
    }
    
    public void setScrVigente(String scrVigente) {
        this.scrVigente = scrVigente;
    }

    public String getScrVigente() {
        if(scrVigente==null)
        {
            
            setScrVigente("SCR-"+String.valueOf(obtenerScrVigente(idClienteFlex)));
        }
        return scrVigente;
   }


    public void setScrRecomendado(String scrRecomendado) {
        this.scrRecomendado = scrRecomendado;
    }

    public String getScrRecomendado() {
        if(scrRecomendado==null)
        {
            setScrRecomendado("SCR-"+String.valueOf(obtenerScrRecomendado(idClienteFlex)));
        }
        return scrRecomendado;
    }

    public Integer getIdTcaScrRecomendado() {
        return idTcaScrRecomendado;
    }

    public void setIdTcaScrRecomendado(Integer idTcaScrRecomendado) {
        this.idTcaScrRecomendado = idTcaScrRecomendado;
    }

    public Integer getIdTcaScrVigente() {
        return idTcaScrVigente;
    }

    public void setIdTcaScrVigente(Integer idTcaScrVigente) {
        this.idTcaScrVigente = idTcaScrVigente;
    }

    public void obtenerScrByIdCliente() {
        Long idCliente = null;
        Integer idTcaScrVigente = null;
        Integer idTcaScrRecomendado = null;
        LOGGER.log(ADFLogger.WARNING,"Dentro de obtenerSrcByIdCliente.");
        try{
        //Invocar metodo para obtener los SCR del cliente.
//            AprobacionBean aprobacionBean = 
//                (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            if(null != this.getIdCliente()){
                idCliente = Long.parseLong(this.getIdCliente());
                idTcaScrVigente = this.obtenerScrVigente(idCliente);
                this.setIdTcaScrVigente(idTcaScrVigente);
                idTcaScrRecomendado = this.obtenerScrRecomendado(idCliente);
                this.setIdTcaScrRecomendado(idTcaScrRecomendado);
               
            }else{
                LOGGER.log(ADFLogger.WARNING, "No se recupero el idCliente. No es posible consultar SCR.");
            }
        }catch(Exception e){
            LOGGER.log(ADFLogger.ERROR, "Error en obtenerSrcByIdCliente : "+e.getMessage());
        }
    }


    public String getIdTipoReunion() {
        return idTipoReunion;
    }

    public void setIdTipoReunion(String idTipoReunion) {
        this.idTipoReunion = idTipoReunion;
    }
    
    public boolean isTipoInicioArgumentacion(){
        return tipoInicio.equals(TI_ARGUMENTACION)? true : false;
    }
    
    /**
    * Precarga la información para iniciar la Tarea de "Dar Seguimiento a Votación" (Aprobación Cliente)
    */
    public void precargaDarSeguimientoAVotacion() {
        this.obtenerScrByIdCliente();
        this.findDescripcionSCRById(this.idTcaScrVigente, "findDescripcionSCRById");
        this.findDescripcionSCRById(this.idTcaScrRecomendado, "findDescripcionSCRById1");
//        this.inicializarDarSeguimientoVotacion_aprobacion_cliente();
    }
    
    /**
    * Precarga la información para iniciar la Tarea de "Consolidar Decision Comite de Credito" (Aprobación Cliente)
    */
    public void precargaConsolidarDecisionComiteCredito() {
        this.obtenerScrByIdCliente();
        this.findDescripcionSCRById(this.idTcaScrVigente, "findDescripcionSCRById");
        this.findDescripcionSCRById(this.idTcaScrRecomendado, "findDescripcionSCRById1");
    }
    
    /**
     * Método del AM que se utiliza para inicializar la pantalla de Dar Seguimiento a Votación para Aprobación
     * En este caso se utiliza el mismo método para Aprobación-Cliente
     */
    @SuppressWarnings("unchecked")
    private void inicializarDarSeguimientoVotacion_aprobacion_cliente() {
        LOGGER.log(ADFLogger.WARNING, "** Dentro de inicializarDarSeguimientoVotacion_aprobacion_cliente");
        LOGGER.log(ADFLogger.WARNING, "** this.idSolicitud " + this.idSolicitud);
        LOGGER.log(ADFLogger.WARNING, "** this.idOperacion " + this.idOperacion);
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            
            if (bindings != null) {
                OperationBinding operationBinding = bindings.getOperationBinding("inicializarDarSeguimientoCliente");
                
                if (operationBinding != null) { 
                    operationBinding.getParamsMap().put("idSolicitud", this.lngIdSolicitudAprob);
                    operationBinding.getParamsMap().put("idCliente", this.idClienteFlex);
                    operationBinding.execute();
                        
                    if(operationBinding.getErrors() != null && !operationBinding.getErrors().isEmpty()){
                        LOGGER.log(ADFLogger.ERROR, "**** Error en inicializarDarSeguimiento: " + operationBinding.getErrors().toString());
                    } else {
                        LOGGER.log(ADFLogger.WARNING, "*** Se cargó de forma correcta inicializarDarSeguimientoVotacion_aprobacion_cliente");
                    }
                } else {
                    LOGGER.log(ADFLogger.WARNING, "*** OperationBinding NULL");
                }
            } else {
                LOGGER.log(ADFLogger.WARNING, "*** BindingContainer NULL");
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "**** Error en inicializarDarSeguimientoVotacion_aprobacion_cliente: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void findDescripcionSCRById(Integer id, String method) {
        LOGGER.log(ADFLogger.WARNING, "** Dentro de findDescripcionSCRById method " + method);
        LOGGER.log(ADFLogger.WARNING, "** id " + id);
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            
            if (bindings != null) {
                OperationBinding operationBinding = bindings.getOperationBinding(method);
                
                if (operationBinding != null) { 
                    operationBinding.getParamsMap().put("id", id);
                    operationBinding.execute();
                        
                    if(operationBinding.getErrors() != null && !operationBinding.getErrors().isEmpty()){
                        LOGGER.log(ADFLogger.ERROR, "**** Error en findDescripcionSCRById: " + operationBinding.getErrors().toString());
                    } else {
                        LOGGER.log(ADFLogger.WARNING, "*** Se cargó de forma correcta findDescripcionSCRById");
                    }
                } else {
                    LOGGER.log(ADFLogger.WARNING, "*** OperationBinding NULL");
                }
            } else {
                LOGGER.log(ADFLogger.WARNING, "*** BindingContainer NULL");
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "**** Error en findDescripcionSCRById: " + e.getMessage());
        }
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String obtenerNombreUsuario(String login){
        LOGGER.warning("Inicia metodo de nombreUsuario");
        String respuesta=login;
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("obtenerNombre");
            operationBinding.getParamsMap().put("login", login);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("Error al obtener el nombre de usuario");
                }
            else{
                    respuesta=(String)operationBinding.getResult();
                    LOGGER.warning("Nombre de usuario obtenido: " + respuesta);
                }
            LOGGER.warning("Termina metodo de nombreUsuario");
            LOGGER.warning("valor obtenido: " +respuesta);
        return respuesta;
        }


    public void cargarVariables(){
     LOGGER.warning("*Inf, inicia metodo cargarVariables");
        Row fila = null;
        BindingContainer binding = ADFUtils.getBindingContainer();
        OperationBinding operBinding = null;
             
         try{
             operBinding = binding.getOperationBinding("getCurrentRegistrarDatosAprovacionVO");             
             operBinding.execute();                                        
         }catch(Exception e){
             LOGGER.log(ADFLogger.ERROR, "Error al recuperar datos cuenta " + e);
         }   
                    
        if(!operBinding.getErrors().isEmpty()){
            LOGGER.warning("OperationBinding getCurrentRegistrarDatosAprovacionVO devuelve errores ->"+operBinding.getErrors().toString());
        }else{
               
              fila = (Row)operBinding.execute();
            
            if(fila != null){
                    try{
                      idAccionSeguir = (Long) fila.getAttribute("IdAccionSeguir");
                      
                    }catch(Exception e){
                        LOGGER.severe("Error al recuperar IdAccionSeguir: ", e);
                    }            
                    
                    try {
                       aprobacionId = (Long)fila.getAttribute("AprobacionId");               
                    }catch (Exception e){
                       LOGGER.severe("Error al recuperar AprobacionId: ", e);
                    }
            }else{
                LOGGER.warning("El Current de RegistrarDatosAprovacionVo es resuelto a null");
            }
            
            LOGGER.warning("aprobacionId: "+aprobacionId);
            LOGGER.warning("idAccionSeguir: "+idAccionSeguir);            
        }
    
     LOGGER.warning("*Inf, termina metodo cargarVariables");
    }
    
    
    public void evaluarParametroPStateBpm()  {
        LOGGER.warning("Dentro de evaluarParametroPStateBpm State");

        String state =  (null != ADFUtils.getBoundAttributeValue("state")) 
                    ? (String)ADFUtils.getBoundAttributeValue("state"): null; 
        LOGGER.warning("Var State: "+state);

        try {
            if (state != null) {
                LOGGER.warning("state :" + state);                
                setEsEstadoCompletado(state.equalsIgnoreCase("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            }else{
                LOGGER.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            LOGGER.warning("Error al recuperar NodeList state :",ex);
            LOGGER.warning("pState no obtenido");
        }

        LOGGER.warning("esEstadoCompletado : " + getEsEstadoCompletado());
        LOGGER.warning("Fuera de evaluarParametroPStateBpm");        
    }


    public Boolean getExistenCambiosComiteCredito() {
        return existenCambiosComiteCredito;
    }

    public void setExistenCambiosComiteCredito(Boolean existenCambiosComiteCredito) {
        this.existenCambiosComiteCredito = existenCambiosComiteCredito;
    }

    public Boolean getExistenCambiosConNotificacion() {
        return existenCambiosConNotificacion;
    }

    public void setExistenCambiosConNotificacion(Boolean existenCambiosConNotificacion) {
        this.existenCambiosConNotificacion = existenCambiosConNotificacion;
    }
    
    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
     }

    public void setConNotificacionMap(Map<String, Integer> conNotificacionMap) {
        this.conNotificacionMap = conNotificacionMap;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }
    
    public Map<String, Integer> getConNotificacionMap() {
        return conNotificacionMap;
    }

    public void setComiteCreditoMap(Map<Integer, String> comiteCreditoMap) {
        this.comiteCreditoMap = comiteCreditoMap;
    }

    public Map<Integer, String> getComiteCreditoMap() {
        return comiteCreditoMap;
    }

    public oracle.jbo.domain.Number getIdTipoAccion() {
        return IdTipoAccion;
    }

    public void setIdTipoAccion(oracle.jbo.domain.Number IdTipoAccion) {
        this.IdTipoAccion = IdTipoAccion;
    }


    public String getCodigoTipoAccion() {
        return codigoTipoAccion;
    }

    public void setCodigoTipoAccion(String codigoTipoAccion) {
        this.codigoTipoAccion = codigoTipoAccion;
    }


    public Boolean getExisteCondicion() {
        return existeCondicion;
    }

    public void setExisteCondicion(Boolean existeCondicion) {
        this.existeCondicion = existeCondicion;
    }


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }
}
