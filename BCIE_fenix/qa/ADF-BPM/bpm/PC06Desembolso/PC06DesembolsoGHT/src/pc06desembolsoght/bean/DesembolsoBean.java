package pc06desembolsoght.bean;

import java.io.IOException;
import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.controller.binding.TaskFlowBindingAttributes;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.BPMUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewObject;

import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.gestordesembolsos.VerCrearSolicitudDesembolsosActionBean;
import oracle.jbo.domain.Number;

import org.bcie.fenix.common.FenixConstants;

import org.w3c.dom.NodeList;
public class DesembolsoBean extends FenixPanelBean implements Serializable {
    
    @SuppressWarnings("compatibility:-4227689755034822546")
    private static final long serialVersionUID = 1L;
    //private static final long serialVersionUID = 1642764905463718648L;
    
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(DesembolsoBean.class);
    
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String INSTANCIA_TAREA = "InstanciaProceso";
    private static final String ID_SOLICITUD = "idSolicitud";
    private static final String CODIGO_ROL = "CodigoRol";
    private static final String USUARIO_PROCESO = "UsuarioIniciaProceso";
    private static final String ID_DESEMBOLSO = "idDesembolso";
    private static final String ESTADO_DESEMBOLSO = "estadoDesembolso";
    private static final String ID_PROCESO_BPM = "IdProceso";
    private static final String RESPONSABLE_OPERACION = "ResponsableOperacion";
    private static final String ID_FLUJO = "IdFlujo";
    
    public static ADFLogger logger = null;
    private String idTarea = "";
    private String idOperacion = "";
    private String instanciaTarea = "";
    private String idSolicitud = "";
    private String rol = "";
    private String usuario = "";
    private String idDesembolso = "";
    private String codigoExterno = "";
    private String idLineaCredito = "";
    private String idEstadoSolicitud = "";
    private Long linea;
    private String estado;
    private String contrato;
    private String urlLotusNote;
    private String numeroDResolucion;
    private String idProceso = "";
    private String responsableOperacion;
    private String usuarioActivo;
    private String instanciaProceso;
    //variables para detalle de la linea de credito ------------->
    private Long pidLineaCredito;
    private String pNumeroLinea;
    private String pDescripcionLinea;
    private String pDescripcionCortaLinea;
    private BigDecimal pMontoLinea;
    private String pFondoLinea;    
    private BigDecimal montoDisponible = null;
    private BigDecimal montoTransito = null;
    private BigDecimal montoDisponibleDesembolso = null;
    private BigDecimal montoProgramado = null;
    private java.sql.Timestamp fecharegistro = null;
    private java.sql.Timestamp fechaVencimiento = null;
    private java.sql.Timestamp fechaMaximaDesembolso = null;
    private Long idContratoDesembolso = null;
    private Long aprobarFueraHorario = 0L;
    
    private List<String> listaErrores = new ArrayList<String>();
    //---------------------             ------------------------->
    
    private Integer cuentaPresupuestoMonto = Integer.valueOf(0);
    private Integer seCuentaPresupuestoMonto = null;
    
    private oracle.jbo.domain.Number idFlujo;
    
    private Boolean aplicarScroll = Boolean.FALSE;
    private Boolean esErrorProgramacion = Boolean.FALSE;
    
    private Boolean errorDatosCarga = Boolean.FALSE;
   

    private List<TaskFlowBindingAttributes> multiTaskFlowDetOpeBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowGesDocBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowMatTccBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowComentBindings = new ArrayList<TaskFlowBindingAttributes>();
    
    private Integer cargarRegionDetOpe = Integer.valueOf(0);
    private Integer cargarRegionGesDoc = Integer.valueOf(0);
    private Integer cargarRegionMatTcc = Integer.valueOf(0);
    private Integer cargarRegionComent = Integer.valueOf(0);
    
    public DesembolsoBean() {
        if (logger == null){
            logger = ADFLogger.createADFLogger(DesembolsoBean.class);
        }
    }

    public void getPayloadInformation() throws WorkflowException, IOException {
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();
        java.io.StringWriter writer = new java.io.StringWriter();
        xmlPayload.print(writer);
        String payloadAsString = writer.toString();
        LOGGER.warning("BPM Payload " + payloadAsString);
        LOGGER.warning("*Inf, --------->>  Log de prueba v1.0 de 06/06/2017 <<------------");
        
        NodeList nl;

        nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if (nl.getLength() > 0) {
            idTarea = nl.item(0).getTextContent();
        }

        nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if (nl.getLength() > 0) {
            idOperacion = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(INSTANCIA_TAREA);
        if (nl.getLength() > 0) {
            instanciaTarea = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(ID_SOLICITUD);
        if (nl.getLength() > 0) {
            idSolicitud = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_ROL);
        if (nl.getLength() > 0) {
            rol = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(USUARIO_PROCESO);
        if (nl.getLength() > 0) {
            usuario = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(ID_DESEMBOLSO);
        if (nl.getLength() > 0) {
            idDesembolso = nl.item(0).getTextContent();
            //idDesembolso = "1";
            contrato=nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(ESTADO_DESEMBOLSO);
        if (nl.getLength() > 0) {
            estado = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(ID_PROCESO_BPM);
        if (nl.getLength() > 0) {
            idProceso = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(RESPONSABLE_OPERACION);
        if (nl.getLength() > 0) {
            responsableOperacion = nl.item(0).getTextContent();
        }
        
        //recuperamos la sesión de usuario 
        if(null != JSFUtils.resolveExpression("#{securityContext.userName}")){
            setUsuarioActivo((String)JSFUtils.resolveExpression("#{securityContext.userName}"));
        }
        
        
        nl = xmlPayload.getElementsByTagName(ID_FLUJO);
        if (nl.getLength() > 0){
            try {
                setIdFlujo(new oracle.jbo.domain.Number(nl.item(0).getTextContent()));
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, "Error al inicializar el valor de IdFlujo: " + e.getMessage());
            }
        }
        
        instanciaProceso = BPMUtils.getCurrentTask().getProcessInfo().getInstanceId();

        if(null!=idTarea && !idTarea.isEmpty()){
            Integer codigoTarea=Integer.parseInt(idTarea);
            if(codigoTarea.compareTo(FenixConstants.CGD_VALIDAR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS)==0 ||
                codigoTarea.compareTo(FenixConstants.CGD_REGISTRAR_DESEMBOLSO)==0 ||
                codigoTarea.compareTo(FenixConstants.CGD_RESERVAR_FONDOS)==0){
                setIdDesembolsoLineaCreditoEstadoByIdContrato();
            }
        }

        LOGGER.warning("instanciaProceso" + instanciaProceso);
        LOGGER.warning(CODIGO_TAREA + idTarea);
        LOGGER.warning(CODIGO_OPERACION + idOperacion);
        LOGGER.warning(ID_SOLICITUD + idSolicitud);
        LOGGER.warning(INSTANCIA_TAREA + instanciaTarea);
        LOGGER.warning(CODIGO_ROL + " " + rol);
        LOGGER.warning("Usuario activo" + " " + usuarioActivo);
        LOGGER.warning(USUARIO_PROCESO + " " + usuario);
        LOGGER.warning(ID_DESEMBOLSO + " " + idDesembolso);
        LOGGER.warning(ID_PROCESO_BPM + " " + idProceso);
        LOGGER.warning(RESPONSABLE_OPERACION + " " + responsableOperacion);
        LOGGER.warning(ID_FLUJO +idFlujo);
    }
        
    
    public void obtenerSolicitudDesemByIdDesembolso() {
        logger.warning("*Inf, Inicia metodo obtenerSolicitudDesemByIdContrato  contrato:" + idDesembolso);

        if (!idDesembolso.equals("")) {
            try {
                Long idContrato = new Long(idDesembolso);

                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("obtenerSolicitudByIdDesembolso");
                operationBinding.getParamsMap().put("idContrato", idContrato);
                operationBinding.execute();

                if (!operationBinding.getErrors().isEmpty()) {
                    logger.warning("OperationBinding devuelve errores metodo setpIdSolicitudRegSolDesExc");
                            logger.warning("***Error ->"+operationBinding.getErrors().toString());
                            setErrorDatosCarga(Boolean.TRUE);
                        }else{                                                
                                Long solicitud = (Long)operationBinding.getResult();
                                if(solicitud != null){
                                       logger.warning("*Inf, ok idSolicitud recueprado : "+solicitud);
                                       this.idSolicitud = solicitud.toString();
                                }else{                                
                                        logger.warning("***Error, idSolicitud recueprado es resuelto a null: ");
                                        setErrorDatosCarga(Boolean.TRUE);
                                }  
                         }

                }catch(Exception e){
                        logger.warning("***Error, al ejecutar metodo obtenerSolicitudDesemByIdDesembolso ", e);
                        setErrorDatosCarga(Boolean.TRUE);
                }
           }else{
               logger.warning("idDesmbolso null");
           }    
            
        logger.warning("*Inf, Termina metodo obtenerSolicitudDesemByIdContrato");
        
        renderizarPanelesAcordionComunes();
    }


    public void precargaValoresJustificarDesembolsoExcepcion(){
        LOGGER.warning("Metodo precarga valores justificar desembolso excepcion");
                try{
                        BindingContainer binding = ADFUtils.getBindingContainer();
                        OperationBinding operationBinding = binding.getOperationBinding("setpIdSolicitudRegSolDesExc");
                        operationBinding.getParamsMap().put("value", Long.parseLong(idSolicitud.toString()));
                        operationBinding.execute();
                    
                        if(!operationBinding.getErrors().isEmpty()){
                            logger.warning("OperationBinding devuelve errores metodo setpIdSolicitudRegSolDesExc");
                        }else{
                                logger.warning("***Carga IdSolicitud Listo, redireccionando... ");
                            }
                    }catch(Exception e){
                            logger.log(ADFLogger.ERROR, "Error en consultarDatosSolicitud " + e.getClass() + ":" + e.getMessage());
                           
                    }
            
                try{
                        BindingContainer binding = ADFUtils.getBindingContainer();
                        OperationBinding operationBinding = binding.getOperationBinding("setpInstanciaProcesoRegSolDesExc");
                        operationBinding.getParamsMap().put("value", instanciaTarea.toString());
                        operationBinding.execute();
                    
                        if(!operationBinding.getErrors().isEmpty()){
                            logger.warning("OperationBinding devuelve errores metodo setpIdSolicitudRegSolDesExc");
                        }else{
                                logger.warning("***Carga IdSolicitud Listo, redireccionando... ");
                            }
                    }catch(Exception e){
                            logger.log(ADFLogger.ERROR, "Error en consultarDatosSolicitud " + e.getClass() + ":" + e.getMessage());
                            
                    }
        
                 try{
                         BindingContainer binding = ADFUtils.getBindingContainer();
                         OperationBinding operationBinding = binding.getOperationBinding("setpIdOp");
                         operationBinding.getParamsMap().put("value", Long.parseLong(idOperacion.toString()));
                         operationBinding.execute();
                     
                         if(!operationBinding.getErrors().isEmpty()){
                             logger.warning("OperationBinding devuelve errores setpIdOp");
                         }else{
                                 logger.warning("***Carga IdOperacion Listo, redireccionando... ");
                             }
                     }catch(Exception e){
                             logger.log(ADFLogger.ERROR, "Error en consultarDatosOperacion " + e.getClass() + ":" + e.getMessage());
                             
                     }
                 
                    try{
                            BindingContainer binding = ADFUtils.getBindingContainer();
                            OperationBinding operationBinding = binding.getOperationBinding("cargarRowVo");
                            operationBinding.execute();
                        
                            if(!operationBinding.getErrors().isEmpty()){
                                logger.warning("OperationBinding devuelve errores");
                            }else{
                                    logger.warning("***Carga VO Programatica Listo, redireccionando... ");
                                }
                        }catch(Exception e){
                                logger.log(ADFLogger.ERROR, "Error en cargarVoProgramatica " + e.getClass() + ":" + e.getMessage());
                                
                        } 
                         //logger.warning("*** Listo, redireccionando... ");
                          //FacesContext facesContext = FacesContext.getCurrentInstance();
                          //facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "goPC06JustificarDesembolso");
                    
                          try{
                                  BindingContainer binding = ADFUtils.getBindingContainer();
                                  OperationBinding operationBinding = binding.getOperationBinding("setpIdSolicitudEviSol");
                                  operationBinding.getParamsMap().put("value", Long.parseLong(idSolicitud.toString()));
                                  operationBinding.execute();
                              
                                  if(!operationBinding.getErrors().isEmpty()){
                                      logger.warning("OperationBinding devuelve errores metodo setpIdSolicitudEviSol");
                                  }else{
                                          logger.warning("***Carga IdSolicitud Listo, redireccionando... ");
                                      }
                              }catch(Exception e){
                                      logger.log(ADFLogger.ERROR, "Error en consultarDatosSolicitud " + e.getClass() + ":" + e.getMessage());
                                      
                              }  
                    
                    try{
                          logger.log(ADFLogger.WARNING, "inicializarJustificacionExcepcion");

                          BindingContainer bindings = ADFUtils.getBindingContainer();
                          OperationBinding operationBinding = null;
                          //metodo para obtener si el produto es 'Linea global de credito'
                          operationBinding = bindings.getOperationBinding("crearRowFormularioJustificacionExcepcion");
                          operationBinding.getParamsMap().put("idTcaTareaBpm", idTarea);
                          operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                          operationBinding.getParamsMap().put("instanciaProceso", instanciaTarea);
                          operationBinding.getParamsMap().put("loginUsuario", usuarioActivo);
                          operationBinding.getParamsMap().put("nombreUsuario", usuarioActivo);
                          operationBinding.getParamsMap().put("idRol", Integer.valueOf(rol));  
                          operationBinding.execute();

                          crearConsultaJustificacionExcepcion();
                        }catch(Exception e){
                                logger.log(ADFLogger.ERROR, "Error en cargar inicializarJustificacionExcepcion " + e.getClass() + ":" + e.getMessage());
                                
                        } 
    }
    
    public void inicializarValidacionExcepcion(){
        
        LOGGER.warning("Ingresa a inicializarValidacionExcepcion");
        
        crearConsultaJustificacionExcepcion();
        
        try{
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("setpIdSolicitudRegSolDesExc");
                operationBinding.getParamsMap().put("value", Long.parseLong(idSolicitud.toString()));
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("OperationBinding devuelve errores metodo setpIdSolicitudRegSolDesExc");
                }else{
                        logger.warning("***Carga IdSolicitud Listo, redireccionando... ");
                    }
            }catch(Exception e){
                    logger.log(ADFLogger.ERROR, "Error en consultarDatosSolicitud " + e.getClass() + ":" + e.getMessage());
                   
            }
        
        try{
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("setpInstanciaProcesoRegSolDesExc");
                operationBinding.getParamsMap().put("value", instanciaTarea.toString());
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("OperationBinding devuelve errores metodo setpIdSolicitudRegSolDesExc");
                }else{
                        logger.warning("***Carga IdSolicitud Listo, redireccionando... ");
                    }
            }catch(Exception e){
                    logger.log(ADFLogger.ERROR, "Error en consultarDatosSolicitud " + e.getClass() + ":" + e.getMessage());
                    
            }
          
        
        try{
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("setpIdSolicitudEviSol");
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("OperationBinding devuelve errores metodo setpIdSolicitudEviSol");
                }else{
                        logger.warning("***Carga IdSolicitud Listo, redireccionando... ");
                    }
            }catch(Exception e){
                    logger.log(ADFLogger.ERROR, "Error en consultarDatosSolicitud " + e.getClass() + ":" + e.getMessage());
            } 
    }
    
    public void crearConsultaJustificacionExcepcion(){
    
        logger.log(ADFLogger.WARNING, "crearConsultaJustificacionExcepcion");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        //metodo para obtener si el produto es 'Linea global de credito'
        operationBinding = bindings.getOperationBinding("setValores");
        operationBinding.getParamsMap().put("idSolicitud", Long.valueOf(idSolicitud));
        operationBinding.execute();
        
    }

    @SuppressWarnings("unchecked")
    public void precargaGestionarDesembolsosFondosExternos() {
        logger.warning("inside precargaGestionarDesembolsosFondosExternos");
        LOGGER.warning(CODIGO_TAREA + idTarea);
        LOGGER.warning(CODIGO_OPERACION + idOperacion);
        LOGGER.warning(ID_DESEMBOLSO + " " + idDesembolso);
        this.setCodigoExterno();
        this.setIdDesembolsoLineaCreditoEstadoByIdContrato();
        LOGGER.warning("CodigoExterno" + " " + codigoExterno);
        LOGGER.warning("IdSolicitud" + " " + idSolicitud);
        LOGGER.warning("IdLineaCredito" + " " + idLineaCredito);
        LOGGER.warning("IdEstadoSolicitud" + " " + idEstadoSolicitud);
        
    }
    
    @SuppressWarnings("unchecked")
    public void precargaAprobarDesembolsos() {
        logger.warning("inside precargaAprobarDesembolsos");
        LOGGER.warning(CODIGO_TAREA + idTarea);
        LOGGER.warning(CODIGO_OPERACION + idOperacion);
        LOGGER.warning(ID_DESEMBOLSO + " " + idDesembolso);
        //this.setCodigoExterno();
        this.setIdDesembolsoLineaCreditoEstadoByIdContrato();
        LOGGER.warning("CodigoExterno" + " " + codigoExterno);
        LOGGER.warning("IdSolicitud" + " " + idSolicitud);
        LOGGER.warning("IdLineaCredito" + " " + idLineaCredito);
        LOGGER.warning("IdEstadoSolicitud" + " " + idEstadoSolicitud);
        
    }

    @SuppressWarnings("unchecked")
    private void setCodigoExterno() {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("getIdProductoFlexcubeByIdContrato");
        operationBinding.getParamsMap().put("idContrato", idDesembolso);
        operationBinding.execute();
        
        if(operationBinding.getErrors().isEmpty()){
            this.codigoExterno = operationBinding.getResult() != null ? operationBinding.getResult().toString() : "";
        }else{
            logger.warning("OperationBinding devuelve errores");
        }
    }

    @SuppressWarnings("unchecked")
    private void setIdDesembolsoLineaCreditoEstadoByIdContrato() {
        LOGGER.warning("Inicia metodo setIdDesembolsoLineaCreditoEstadoByIdContrato.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("buscarInfoContratoDesembolsoByIdControta");
        operationBinding.getParamsMap().put("idContrato", idDesembolso);
        operationBinding.execute();
        
        if(operationBinding.getErrors().isEmpty()){
            Row row = operationBinding.getResult() != null ? ((Row) operationBinding.getResult()) : null;
            
            if (row != null) {
                this.idSolicitud = row.getAttribute("IdSolicitud") != null ? row.getAttribute("IdSolicitud").toString() : "";
                this.idLineaCredito = row.getAttribute("IdLineaCredito") != null ? row.getAttribute("IdLineaCredito").toString() : "";
                this.idEstadoSolicitud = row.getAttribute("IdEstadoSolicitud") != null ? row.getAttribute("IdEstadoSolicitud").toString() : "";
            }
        }else{
            logger.warning("OperationBinding devuelve errores: " + operationBinding.getErrors().toString());
        }
        
        LOGGER.warning("ID_SOLICITUD: " + idSolicitud);
        LOGGER.warning("ID_LINEA_CREDITO: " + idLineaCredito);
        LOGGER.warning("ID_ESTADO_SOLICITUD: " + idEstadoSolicitud);
        LOGGER.warning("Termina metodo setIdDesembolsoLineaCreditoEstadoByIdContrato.");
    }
    
    public String getIdTarea() {
        return this.idTarea;
    }
    
    public String getIdOperacion() {
        return this.idOperacion;
    }

    public void setInstanciaTarea(String instanciaTarea) {
        this.instanciaTarea = instanciaTarea;
    }

    public String getInstanciaTarea() {
        return instanciaTarea;
    }


    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setIdLineaCredito(String idLineaCredito) {
        this.idLineaCredito = idLineaCredito;
    }

    public String getIdLineaCredito() {
        return this.idLineaCredito;
    }
    
    public void setIdDesembolso(String idDesembolso) {
        this.idDesembolso = idDesembolso;
    }
    
    public String getIdDesembolso() {
        return this.idDesembolso;
    }
    
    public void setCodigoExterno(String codigoExterno) {
        this.codigoExterno = codigoExterno;
    }
    
    public String getCodigoExterno() {
        return this.codigoExterno;
    }
    
    public void setIdEstadoSolicitud(String idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }
    
    public String getIdEstadoSolicitud() {
        return this.idEstadoSolicitud;
    }

    public Long getLinea() {
        return linea;
    }

    public void setLinea(Long linea) {
        this.linea = linea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public void precargaValidarFondosPresupuestarios() {
        logger.warning("inside precargaValidarFondosPresupuestarios");
        LOGGER.warning(CODIGO_TAREA + idTarea);
        LOGGER.warning(CODIGO_OPERACION + idOperacion);
        LOGGER.warning(ID_DESEMBOLSO + " " + idDesembolso);
        this.setIdDesembolsoLineaCreditoEstadoByIdContrato();
        LOGGER.warning("IdSolicitud" + " " + idSolicitud);
        LOGGER.warning("IdLineaCredito" + " " + idLineaCredito);
        LOGGER.warning("IdEstadoSolicitud" + " " + idEstadoSolicitud);
        this.consultarDatosLineaCreditoWS();
        this.buildUrlLotusNoteResolucion();
        this.getConsultarCuentaPresupuestoMonto(idDesembolso);
        //this.precargarInformacionFinanciera();
    }

    


    @SuppressWarnings("unchecked")
    private void consultarDatosLineaCreditoWS() {
        logger.warning("*Inf inicia metodo consultarDatosLineaCreditoWS");
           Map<String,Object> resultMap = new HashMap<String,Object>();
           Long  pIdSolicitud = null;
           
           try{
              pIdSolicitud = new Long(idSolicitud);      
           }catch(Exception e){               
             logger.warning("*Error, al parsear idSolicitud ->",e);
           }
           
            String tipoMoneda = recuperarMonedaSolcitud(pIdSolicitud);
         
            BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding operationBinding = null;
                
            try{   
                operationBinding = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
                operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
                operationBinding.getParamsMap().put("tipoMoneda", tipoMoneda);
                operationBinding.execute();
              }catch(Exception e){
                     LOGGER.warning("***Error al ejecutar operBinding consultarLineaCreditoByIdLineaCredito", e);
              }
  
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("***Error, al intentar recuerar los datos de la linea-> "+operationBinding.getErrors());
                JSFUtils.addFacesErrorMessage("Error, "+operationBinding.getErrors());
            } else {
                
                resultMap = (Map)operationBinding.getResult();  
                
                //Datos de la linea           
                 
                           pidLineaCredito = (resultMap.get("idLineaCredito") != null)
                                          ? (Long)resultMap.get("idLineaCredito") : null;
                        
                             pNumeroLinea = (resultMap.get("numeroLinea") != null)
                                          ? (String)resultMap.get("numeroLinea") : null;
                        
                        pDescripcionLinea = (resultMap.get("descripcionLinea") != null)
                                          ? (String)resultMap.get("descripcionLinea") : null;
                
                              pMontoLinea = (resultMap.get("montoLinea") != null) 
                                          ? (BigDecimal)resultMap.get("montoLinea") : null;
                
                   pDescripcionCortaLinea = (resultMap.get("descripcionCortaLinea") != null)
                                          ? (String)resultMap.get("descripcionCortaLinea") : null;                
                        
                              pFondoLinea = (resultMap.get("fondoLinea") != null)
                                          ? (String)resultMap.get("fondoLinea") : null;
                                        
                //Recuperan montos
                
                          montoDisponible = (resultMap.get("DISPONIBLE") != null)
                                          ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
                            
                            montoTransito = (resultMap.get("TRANSITO") != null) 
                                          ? (BigDecimal)resultMap.get("TRANSITO") : null;
                
                montoDisponibleDesembolso = (resultMap.get("DISPONIBLE_DESEMBOLSO") != null) 
                                          ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
                
                          montoProgramado = (resultMap.get("PROGRAMADO") != null )
                                          ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
                
             //recuperan fechas
            
                            fecharegistro = (resultMap.get("FECHAREGISTRO") != null)
                                          ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
                    
                         fechaVencimiento = (resultMap.get("FECHAVENCIMIENTO") != null)
                                          ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
                    
                    fechaMaximaDesembolso = (resultMap.get("FECHAMAXIMADESEMBOLSO") != null) 
                                          ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
                                                        
             LOGGER.warning("Consulta de los datos de la línea de crédito se ejecutó exitosamente");
            }
            
        LOGGER.warning("*Inf, datos recuperados del servicio de consultar Linea :");        
        logger.warning("*Inf, idLineaCredito: "+pidLineaCredito);
        logger.warning("*Inf, numeroLinea: "+pNumeroLinea);
        logger.warning("*Inf, montoLinea: "+pMontoLinea);
        logger.warning("*Inf, descripcionLinea: "+pDescripcionLinea);
        logger.warning("*Inf, descripcionCortaLinea: "+pDescripcionCortaLinea);
        logger.warning("*Inf, fondoLinea: "+pFondoLinea);
        logger.warning("*Inf, montoDisponible: "+montoDisponible);
        logger.warning("*Inf, montoTransito: "+montoTransito);
        logger.warning("*Inf, montoDisponibleDesembolso: "+montoDisponibleDesembolso);
        logger.warning("*Inf, montoProgramado: "+montoProgramado);
        logger.warning("*Inf, fecharegistro: "+fecharegistro);
        logger.warning("*Inf, fechaVencimiento: "+fechaVencimiento);
        logger.warning("*Inf, fechaMaximaDesembolso: "+fechaMaximaDesembolso);
            
            
        logger.warning("*Inf, termina metodo consultarDatosLineaCreditoWS");
    }
    
    public String recuperarMonedaSolcitud(Long pIdSolicitud){
          logger.warning("*Inf inicia metodo recuperarMonedaSolcitud");
          String tMoneda = null;
          BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();    
          
            try{               
                OperationBinding operationBinding = bindings.getOperationBinding("getTipoMonedaDeSolicitud");
                operationBinding.getParamsMap().put("idSolicitud", pIdSolicitud);
                operationBinding.execute();
               
                if(!operationBinding.getErrors().isEmpty()){
                       logger.warning("***Error, al intentar recuerar la moneda de la solicitud"+operationBinding.getErrors());
                       JSFUtils.addFacesErrorMessage("Error, "+operationBinding.getErrors());
                }else{
                    tMoneda = (String) operationBinding.getResult();
                    logger.warning("tMoneda->" +operationBinding.getResult()+"<-");
                }           
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error al recuperar moneda de solicitud ",  e);
                JSFUtils.addFacesErrorMessage("Error al recueprar el tipo de moneda de la solicitud");
            }

          logger.warning("*Inf termina metodo recuperarMonedaSolcitud");
        return tMoneda;
        }
    
    
    
    public void buildUrlLotusNoteResolucion() {
        
        try {
            String numeroResolucionOperacion = this.getNumeroResolucionOperacion();
            LOGGER.warning("Numero de la resolucion :" + numeroResolucionOperacion);
            if(numeroResolucionOperacion != null){
                this.numeroDResolucion=numeroResolucionOperacion;
                String numeroResolucion = formatResolucionToUrl(numeroResolucionOperacion);
                
                DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
                ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
                Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_LOTUS_NOTE_ABRIR_DOC"}));
                String urlDB = row == null ? null : (String) row.getAttribute("Valor");
                LOGGER.warning("Numero de resolucion :" + numeroResolucion);
                this.urlLotusNote = MessageFormat.format(urlDB, numeroResolucion);
                LOGGER.warning("Valor de la URL Lotus :" + this.getUrlLotusNote());
            } else {
                LOGGER.warning("No se cuenta con el número de resolución...");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error > " + e.getMessage());
        }
        //this.urlLotusNote = "http://www.google.com.mx";
    }

    @SuppressWarnings("unchecked")
    private String getNumeroResolucionOperacion() {
        String numeroResolucion = null;
        try {
            Number idAprobacion = this.getIdAprobacionByIdLinea();
            logger.warning("Valor del numero de aprobacion :" + idAprobacion);
            if (idAprobacion != null) {
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("getNumeroResolucionById");
                operationBinding.getParamsMap().put("id", idAprobacion);
                operationBinding.execute();
                
                if(operationBinding.getErrors().isEmpty()){
                    numeroResolucion = (String) operationBinding.getResult();
                }else{
                    logger.warning("OperationBinding devuelve errores");
                }
            } else {
                logger.warning("El idAprobacion es NULL");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, e.getMessage());
        }
        return numeroResolucion;
    }

    @SuppressWarnings("unchecked")
    private Number getIdAprobacionByIdLinea() {
        Number idAprobacion = null;
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("getIdAprobacionByIdLinea");
            operationBinding.getParamsMap().put("idLineaCredito", this.idLineaCredito);
            operationBinding.execute();
            
            if(operationBinding.getErrors().isEmpty()){
                idAprobacion = (Number) operationBinding.getResult();
            }else{
                logger.warning("OperationBinding devuelve errores");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, e.getMessage());
        }
        logger.warning("Valor del idAprobacion :" + idAprobacion);
        return idAprobacion;
    }
    
    private String formatResolucionToUrl(String resolucion){
        
        if(resolucion != null){
            logger.warning("Valor sin modificar :" + resolucion);
            resolucion = resolucion.replace("/", "-");
            logger.warning("Valor modificado :" + resolucion);
        }
        return resolucion;
    }
    
    public String getUrlLotusNote() {
        return this.urlLotusNote;
    }
    
    public void setUrlLotusNote(String urlLotusNote) {
        this.urlLotusNote = urlLotusNote;
    }

    public void setIdProceso(String idProceso) {
        this.idProceso = idProceso;
    }

    public String getIdProceso() {
        return idProceso;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setResponsableOperacion(String responsableOperacion) {
        this.responsableOperacion = responsableOperacion;
    }

    public String getResponsableOperacion() {
        return responsableOperacion;
    }

    /**--------------- Realizar ajustes a desembolso -----------------**/ 
 public void precargaAjustesDesembolso(){
    logger.warning("Inicia metodo precargaAjustesDesembolso");        
        Long idLinea = null;         
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerLineaCredito");
                operationBinding.getParamsMap().put("idContrato", idDesembolso);
                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                operationBinding.execute();             
                 
                 if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("*Inf, OperationBinding  obtenerLineaCredito devuelve errores : " + operationBinding.getErrors());                      
                 }else{
                      idLinea = (Long)operationBinding.getResult(); 
                         logger.warning("*Inf, ok idLinea recuperado : "+idLinea);                      
                  }                
            
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "***Error en buscarTransferenciaParaReservarFondos " + e);
        } 
        
        if(idLinea == null){
            logger.warning("***Error, la linea de credito es resuelta a null");
        }else{
            idLineaCredito =  idLinea.toString();
            setLinea(idLinea);
            }
        verDatosDisponibles();
    logger.warning("termina metodo precargaAjustesDesembolso");
    }
    
/**---------------  Reserva de Fondos -----------------**/

public void precargarLiquidarContrato(){
    logger.warning("Inicia metodo de precarga para reservar fondos");
       verDatosDisponibles();    
       //TODO validacion de FT05 cuando el modo de ejecucion sea Generación de FT05
}

public void precargarInformacionFinanciera(){
    logger.warning("Inicia metodo precargarInformacionFinanciera");
    verDatosDisponibles();
    Long idLinea = null;         
    try{
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerLineaCredito");
            operationBinding.getParamsMap().put("idContrato", idDesembolso);
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.execute();     
              
            
             if(!operationBinding.getErrors().isEmpty()){
                logger.warning("*Inf, OperationBinding obtenerLineaCredito devuelve errores");                      
             }else{
                 idLinea = (Long)operationBinding.getResult();
                 linea = idLinea;
              }
        
        
    }catch(Exception e){
        logger.log(ADFLogger.ERROR, "***Error en metodo precargarInformacionFinanciera : "+ e );
    } 
    
    if(idLinea == null){
        logger.warning("***Error, la linea de credito es resuelta a null");
    }else{
        idLineaCredito =  idLinea.toString();
    }
    
    logger.warning("Termina metodo precargarInformacionFinanciera");
    //TODO Validad el BHQ de respuesta del servicio
}
    
public void precargaReservaFondos(){
     logger.warning("Inicia metodo de precarga para reservar fondos");
        verDatosDisponibles();
        Long idLinea = null;         
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerLineaCredito");
                operationBinding.getParamsMap().put("idContrato", idDesembolso);
                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                  idLinea = (Long)operationBinding.execute();             
                 if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("*** Error, OperationBinding devuelve errores: "+operationBinding.getErrors());                      
                }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "***Error en buscarTransferenciaParaReservarFondos " + e);
        } 
        
        if(idLinea == null){
            logger.warning("***Error, la linea de credito es resuelta a null");
        }else{
            idLineaCredito =  idLinea.toString();
            }

     logger.warning("Termina metodo de precarga para reservar fondos");
    }
  
    public void verDatosDisponibles(){
        
      
        if(idOperacion == null){
            logger.warning("El parametro idOperacion es resuelto a null");
        }if(idSolicitud == null){
            logger.warning("El parametro idSolicitud es resuelto a null");
        }if(idDesembolso == null){
            logger.warning("El parametro idDesembolso es resuelto a null");     
        }if(usuario == null){
            logger.warning("El parametro usuario es resuelto a null");     
        }
        logger.warning("<---------------------------------->");     
            logger.warning(" idOperacion:"+idOperacion);     
            logger.warning(" idSolicitud:"+idSolicitud);                
            logger.warning(" idDesembolso:"+idDesembolso);               
            logger.warning(" usuario:"+usuario);     
        logger.warning("<---------------------------------->");     

           
        }

    public void setCuentaPresupuestoMonto(Integer cuentaPresupuestoMonto) {
        this.cuentaPresupuestoMonto = cuentaPresupuestoMonto;
    }

    public Integer getCuentaPresupuestoMonto() {
        return cuentaPresupuestoMonto;
    }
    
    public String getConsultarCuentaPresupuestoMonto(String idDesembolso){
        Boolean respuesta = Boolean.FALSE;
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("getBanderaCuentaConPresupuestoMonto");
                operationBinding.getParamsMap().put("idContrato", Long.valueOf(idDesembolso));
                operationBinding.execute();
                respuesta = (Boolean)operationBinding.getResult();
                 if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("*** Error, OperationBinding devuelve errores: "+operationBinding.getErrors());                      
                }
                 else{
                     if(respuesta.compareTo(Boolean.TRUE) == 0){
                         logger.warning("Se cuenta con monto presupuesto... se selecciona la opcion Si...");
                         this.setSeCuentaPresupuestoMonto(1);
                    }
                }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "***Error en getConsultarCuentaPresupuestoMonto " + e);
        }
        
        return null;
    }
    
    public void precargaGestionarProgramacion() {
        logger.warning("Inicia metodo precargaGestionarProgramacion");
        LOGGER.warning(CODIGO_TAREA + idTarea);
        LOGGER.warning(CODIGO_OPERACION + idOperacion);
        LOGGER.warning(ID_DESEMBOLSO + " " + idDesembolso);
        //this.setIdDesembolsoLineaCreditoEstadoByIdContrato();
        LOGGER.warning("IdSolicitud" + " " + idSolicitud);
        LOGGER.warning("IdLineaCredito" + " " + idLineaCredito);
        LOGGER.warning("IdEstadoSolicitud" + " " + idEstadoSolicitud);
        this.consultarDatosLineaCreditoWS();
        logger.warning("Termina metodo precargaValidarFondosPresupuestarios");
    }
    
    public void obtenerLineaCreditoPorIdContrato(){
        logger.warning("Inicia metodo obtenerLineaCreditoPorIdContrato.");
        Long idContratoDesembolso = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        try{
            idContratoDesembolso = new Long(idDesembolso);
        }catch(Exception e){
            logger.warning("ERROR al castear IDDESEMBOLSO a Long.", e);
        }
        
        logger.warning("IdContratoDesembolso long: " + idContratoDesembolso);
        try{
            operationBinding = bindings.getOperationBinding("obtenerLineaCreditoPorIdContrato");
            operationBinding.getParamsMap().put("idContrato", idContratoDesembolso);
            linea = (Long) operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                logger.warning("ERROR al recuperar el IDLINEACREDITO: " + operationBinding.getErrors().toString());
            }
        }catch(Exception e){
            logger.warning("ERROR al ejecutar OperBinding obtenerLineaCreditoPorIdContrato.", e);
        }
        logger.warning("Termina metodo obtenerLineaCreditoPorIdContrato.");
    }
    
    public void renderizarPanelesAcordionComunes(){
        logger.warning("Inicia metodo renderizarPanelesAcordionComunes...");
        //bandera en cero, no se carga el taskflow en la precarga de la pantalla, solo hasta el evento DisclosureListener
        setCargarRegionDetOpe(Integer.valueOf(1));
        renderRegionDetalleOperacion();
        
        setCargarRegionGesDoc(Integer.valueOf(1));
        renderRegionGestorDocumentos();
        
        setCargarRegionMatTcc(Integer.valueOf(1));
        renderRegionMatrizTcc();
        
        setCargarRegionComent(Integer.valueOf(1));
        renderRegionComentarios();
        
        logger.warning("Finaliza metodo renderizarPanelesAcordionComunes...");
    }
    
    public void renderRegionDetalleOperacion(){
        logger.warning("Inicia renderRegionDetalleOperacion en DesembolsoBean");
        Integer bandera = getCargarRegionDetOpe();
        logger.warning("Valor cargarRegionDetalleOperacion: " + bandera);
        if(bandera == 1){
            logger.warning("Precarga de la pantalla, no se realiza ninguna accion para cargar el taskFlow detalleOperacionBTF");
        }
        else if(bandera == 2){
            logger.warning("Se ejecuta el evento DisclosureListener, se ingresa al if... cargando taskFlow detalleOperacionBTF");
            multiTaskFlowDetOpeBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_DetalleOperacion");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/operacion/detalleOperacionBTF.xml",
                                                    "detalleOperacionBTF"));
            multiTaskFlowDetOpeBindings.add(taskFlowBindingAttributes);
        }
        logger.warning("Finaliza renderRegionDetalleOperacion en DesembolsoBean");
    }
    
    public void renderRegionGestorDocumentos(){
        logger.warning("Inicia renderRegionGestorDocumentos en DesembolsoBean");
        Integer bandera = getCargarRegionGesDoc();
        logger.warning("Valor cargarRegionDocumentos: " + bandera);
        if(bandera == 1){
            logger.warning("Precarga de la pantalla, no se realiza ninguna accion para cargar el taskFlow gestorDocumentosBTF");
        }
        else if(bandera == 2){
            logger.warning("Se ejecuta el evento DisclosureListener, se ingresa al if... cargando taskFlow gestorDocumentosBTF");
            multiTaskFlowGesDocBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_GestorDocumentos");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/documentos/gestorDocumentosBTF.xml",
                                                    "gestorDocumentosBTF"));
            multiTaskFlowGesDocBindings.add(taskFlowBindingAttributes);
        }
        logger.warning("Finaliza renderRegionGestorDocumentos en DesembolsoBean");
    }
    
    public void renderRegionMatrizTcc(){
        logger.warning("Inicia renderRegionMatrizTcc en DesembolsoBean");
        Integer bandera = getCargarRegionMatTcc();
        logger.warning("Valor cargarRegionMatrizTcc: " + bandera);
        if(bandera == 1){
            logger.warning("Precarga de la pantalla, no se realiza ninguna accion para cargar el taskFlow matriztccBTF");
        }
        else if(bandera == 2){
            logger.warning("Se ejecuta el evento DisclosureListener, se ingresa al if... cargando taskFlow matriztccBTF");
            multiTaskFlowMatTccBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_MatrizTcc");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/matriztcc/matriztccBTF.xml",
                                                    "matriztccBTF"));
            multiTaskFlowMatTccBindings.add(taskFlowBindingAttributes);
        }
        logger.warning("Finaliza renderRegionMatrizTcc en DesembolsoBean");
    }
    
    public void renderRegionComentarios(){
        logger.warning("Inicia renderRegionComentarios en DesembolsoBean");
        Integer bandera = getCargarRegionComent();
        logger.warning("Valor cargarRegionComentarios: " + bandera);
        if(bandera == 1){
            logger.warning("Precarga de la pantalla, no se realiza ninguna accion para cargar el taskFlow comentariosBTF");
        }
        else if(bandera == 2){
            logger.warning("Se ejecuta el evento DisclosureListener, se ingresa al if... cargando taskFlow comentariosBTF");
            multiTaskFlowComentBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_Comentarios");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/comentarios/comentariosBTF.xml",
                                                    "comentariosBTF"));
            multiTaskFlowComentBindings.add(taskFlowBindingAttributes);
        }
        logger.warning("Finaliza renderRegionComentarios en DesembolsoBean");
    }

    public void setSeCuentaPresupuestoMonto(Integer seCuentaPresupuestoMonto) {
        this.seCuentaPresupuestoMonto = seCuentaPresupuestoMonto;
    }

    public Integer getSeCuentaPresupuestoMonto() {
        return seCuentaPresupuestoMonto;
    }

    public void setPidLineaCredito(Long pidLineaCredito) {
        this.pidLineaCredito = pidLineaCredito;
    }

    public Long getPidLineaCredito() {
        return pidLineaCredito;
    }

    public void setPNumeroLinea(String pNumeroLinea) {
        this.pNumeroLinea = pNumeroLinea;
    }

    public String getPNumeroLinea() {
        return pNumeroLinea;
    }

    public void setPDescripcionLinea(String pDescripcionLinea) {
        this.pDescripcionLinea = pDescripcionLinea;
    }

    public String getPDescripcionLinea() {
        return pDescripcionLinea;
    }
    
    public void setPMontoLinea(BigDecimal pMontoLinea) {
        this.pMontoLinea = pMontoLinea;
    }

    public BigDecimal getPMontoLinea() {
        return pMontoLinea;
    }
    
    public void setPDescripcionCortaLinea(String pDescripcionCortaLinea) {
        this.pDescripcionCortaLinea = pDescripcionCortaLinea;
    }

    public String getPDescripcionCortaLinea() {
        return pDescripcionCortaLinea;
    }

    public void setPFondoLinea(String pFondoLinea) {
        this.pFondoLinea = pFondoLinea;
    }

    public String getPFondoLinea() {
        return pFondoLinea;
    }

    public void setMontoDisponible(BigDecimal montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    public BigDecimal getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoTransito(BigDecimal montoTransito) {
        this.montoTransito = montoTransito;
    }

    public BigDecimal getMontoTransito() {
        return montoTransito;
    }

    public void setMontoDisponibleDesembolso(BigDecimal montoDisponibleDesembolso) {
        this.montoDisponibleDesembolso = montoDisponibleDesembolso;
    }

    public BigDecimal getMontoDisponibleDesembolso() {
        return montoDisponibleDesembolso;
    }

    public void setMontoProgramado(BigDecimal montoProgramado) {
        this.montoProgramado = montoProgramado;
    }

    public BigDecimal getMontoProgramado() {
        return montoProgramado;
    }

    public void setFecharegistro(Timestamp fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Timestamp getFecharegistro() {
        return fecharegistro;
    }

    public void setFechaVencimiento(Timestamp fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Timestamp getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaMaximaDesembolso(Timestamp fechaMaximaDesembolso) {
        this.fechaMaximaDesembolso = fechaMaximaDesembolso;
    }

    public Timestamp getFechaMaximaDesembolso() {
        return fechaMaximaDesembolso;
    }

    public String getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(String usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }


    public Number getIdFlujo() {
        return idFlujo;
    }

    public void setIdFlujo(Number idFlujo) {
        this.idFlujo = idFlujo;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public void setListaErrores(List<String> listaErrores) {
        this.listaErrores = listaErrores;
    }

    public List<String> getListaErrores() {
        return listaErrores;
    }

    public void setAplicarScroll(Boolean aplicarScroll) {
        this.aplicarScroll = aplicarScroll;
    }

    public Boolean getAplicarScroll() {
        return aplicarScroll;
    }

    public void setIdContratoDesembolso(Long idContratoDesembolso) {
        this.idContratoDesembolso = idContratoDesembolso;
    }

    public Long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }
    
    public void setAprobarFueraHorario(Long aprobarFueraHorario) {
        this.aprobarFueraHorario = aprobarFueraHorario; 
    }

    public Long getAprobarFueraHorario() {
        return aprobarFueraHorario;
    }

    public void setEsErrorProgramacion(Boolean esErrorProgramacion) {
        this.esErrorProgramacion = esErrorProgramacion;
    }

    public Boolean getEsErrorProgramacion() {
        return esErrorProgramacion;
    }

    public String getNumeroDResolucion() {
        return numeroDResolucion;
    }

    public void setNumeroDResolucion(String numeroDResolucion) {
        this.numeroDResolucion = numeroDResolucion;
    }
    
    public void setErrorDatosCarga(Boolean errorDatosCarga) {
        this.errorDatosCarga = errorDatosCarga;
    }

    public void setMultiTaskFlowDetOpeBindings(List<TaskFlowBindingAttributes> multiTaskFlowDetOpeBindings) {
        this.multiTaskFlowDetOpeBindings = multiTaskFlowDetOpeBindings;
    }

    public Boolean getErrorDatosCarga() {
        return errorDatosCarga;
    }
    
    public List<TaskFlowBindingAttributes> getMultiTaskFlowDetOpeBindings() {
        return multiTaskFlowDetOpeBindings;
    }

    public void setMultiTaskFlowGesDocBindings(List<TaskFlowBindingAttributes> multiTaskFlowGesDocBindings) {
        this.multiTaskFlowGesDocBindings = multiTaskFlowGesDocBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowGesDocBindings() {
        return multiTaskFlowGesDocBindings;
    }

    public void setMultiTaskFlowMatTccBindings(List<TaskFlowBindingAttributes> multiTaskFlowMatTccBindings) {
        this.multiTaskFlowMatTccBindings = multiTaskFlowMatTccBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowMatTccBindings() {
        return multiTaskFlowMatTccBindings;
    }

    public void setMultiTaskFlowComentBindings(List<TaskFlowBindingAttributes> multiTaskFlowComentBindings) {
        this.multiTaskFlowComentBindings = multiTaskFlowComentBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowComentBindings() {
        return multiTaskFlowComentBindings;
    }

    public void setCargarRegionDetOpe(Integer cargarRegionDetOpe) {
        this.cargarRegionDetOpe = cargarRegionDetOpe;
    }

    public Integer getCargarRegionDetOpe() {
        return cargarRegionDetOpe;
    }

    public void setCargarRegionGesDoc(Integer cargarRegionGesDoc) {
        this.cargarRegionGesDoc = cargarRegionGesDoc;
    }

    public Integer getCargarRegionGesDoc() {
        return cargarRegionGesDoc;
    }

    public void setCargarRegionMatTcc(Integer cargarRegionMatTcc) {
        this.cargarRegionMatTcc = cargarRegionMatTcc;
    }

    public Integer getCargarRegionMatTcc() {
        return cargarRegionMatTcc;
    }

    public void setCargarRegionComent(Integer cargarRegionComent) {
        this.cargarRegionComent = cargarRegionComent;
    }

    public Integer getCargarRegionComent() {
        return cargarRegionComent;
    }

    public void precargarEstadoDesembolso() {
        try{
            final DCBindingContainer dcBindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            final DCIteratorBinding comentariosIterator = dcBindings.findIteratorBinding("ContratoDesembolsoQueryVOIterator");
            if(this.contrato!=null){
                final ViewCriteria criterial = comentariosIterator.getViewObject().getViewCriteriaManager().getViewCriteria("ContratoDesembolsoPorIdVC");
                criterial.ensureVariableManager().setVariableValue("pIdContratoDesembolso",Long.parseLong(this.contrato));
                comentariosIterator.getViewObject().applyViewCriteria(criterial);
                comentariosIterator.executeQuery();
            }
        }catch(Exception e){
            e.printStackTrace();
            
        }
    }
}
