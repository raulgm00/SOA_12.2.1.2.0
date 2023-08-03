package org.bcie.fenix.view.gestordesembolsos;


import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;


import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Timestamp;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.event.RangeChangeEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.modelo.CondicionDesembolso;

public class SolicitudDesembolsosBean extends FenixActionBean implements Serializable {
    
    @SuppressWarnings("compatibility:-3931071545168145524")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;    

    public SolicitudDesembolsosBean() {
        if(logger == null){
            logger = ADFLogger.createADFLogger(SolicitudDesembolsosBean.class);
        }
    }

    //-------------  Estos datos son parametros de entrada del taskflow  ---------------->    
    private Long idOperacion  = (null == JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}"))? null  
                               :  new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString());
   
    private Long idSolicitud  = (null == JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}"))? null  
                               : new Long(JSFUtils.resolveExpression("#{pageFlowScope.idSolicitud}").toString());    
  
    private Integer idTareaBPM = (null == JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}"))? null
                               : Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}").toString());
   
    private Long idContratoDes = (null == JSFUtils.resolveExpression("#{pageFlowScope.idContratoDesembolso}"))? null
                               : new Long(JSFUtils.resolveExpression("#{pageFlowScope.idContratoDesembolso}").toString());
   
    private String operacionSolicitud = (null == JSFUtils.resolveExpression("#{pageFlowScope.operacionSolicitud}"))? null
                                      : JSFUtils.resolveExpression("#{pageFlowScope.operacionSolicitud}").toString();
   
    private Integer idProcesoBPM = (null == JSFUtils.resolveExpression("#{pageFlowScope.idProcesoBPM}"))? null
                                 : Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.idProcesoBPM}").toString());
   
    private String idInstanciaTareaBPM = (null == JSFUtils.resolveExpression("#{pageFlowScope.idInstanciaTareaBPM}"))? null
                                : JSFUtils.resolveExpression("#{pageFlowScope.idInstanciaTareaBPM}").toString();
        
    private String instanciaProceso = (null == JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}"))? null
                                     : JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}").toString();

    // ---------------------------------------------------------------------------------->
    private Integer idEstadoSolicitud = null;
    
    private Long idLineaCredito = (null == JSFUtils.resolveExpression("#{pageFlowScope.pIdLineaCredito}"))? null
                               : new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdLineaCredito}").toString());
   
    // referencia:  getLineasCredito()
    private String numeroLineaCredito = null;
    private Long idContratoDesembolso = null;
    private Long idContrato = null;
    RichTreeTable treeTable1 = null;
    private String idCodigoExterno = null;
    private Long idContratoDefault = null;
    private Long montoDesembolso = null;
    private Long montoTotal = null;
    private oracle.jbo.domain.Timestamp fechaValorFlex;
    private Integer tipoMoneda = null;  //   referencia:  cargarLineaCredito();
    private String tMoneda = null;
    private Boolean tieneTipoMoneda = Boolean.FALSE;
    private Date fechaVigenciaLiena = new Date();
    public final String MSG_DESESTIMAR = "Se desestimo la solicitud numero: "; 
    private Boolean desabilitarBtnIniciarCumplimientoCon = Boolean.TRUE;
    private Boolean desabilitarBtnDesestimarDesembolso = Boolean.TRUE;
    private Boolean btnCrearDesembolsoVisible = null;
    private Boolean btnDetalleLineaVisible = Boolean.TRUE;
    private Boolean btnIniciarCumplimientoCondicionesVisible = null;
    private Boolean btnDesestimarDesembolsoVisible = Boolean.TRUE;
    private Boolean btnDesestimarSollicitudVisible = null;
    private Boolean desHabilitarBtnDetalleLinea = Boolean.FALSE;
    private Boolean treeDatosSolicitud = null;
    private BigDecimal montoTotalSolicitud = null;
    private Boolean fechaSolicitudLectura = Boolean.TRUE;
    
    private Double montoLinea;
    private Long idLineaCreditoSeleccionada = null;
    
    private Integer idTipoMonedaLinea;
    private Integer idTipoMonedaTotal;

    private Integer idModoEjecucion = null;
    private Boolean esVisible = null;
    //-----------Varibles usadas en la tarea de Realizar agustes de desembolso  -------->
    private Boolean treeVisible = Boolean.TRUE;
    //-----------Variable para ocultar o mostrar el contrato de desembolso
    private Boolean contratoVisible = Boolean.TRUE;
    //----------- Varibles Modo de escucion para componentes  -------->
    private Integer modoEjecucionTabTransferencias = null;
    //----------- Variable del contrato seleccionado del treeTable -------------->
    private Long idContratoSeleccionado;
    //---------------------------------------------------------------------------------->
    private Boolean esCumplimientoCondicion = Boolean.FALSE;
    
    //variables para mostrar Datos de la linea de credito en PC06DesembolsoGHT para la tarea Realizar ajustes
    private Boolean esVisibleDatosLineaCredito = Boolean.FALSE;
    //variables para detalle de la linea de credito 
    private BigDecimal montoDisponible = null;
    private BigDecimal montoTransito = null;
    private BigDecimal montoDisponibleDesembolso = null;
    private BigDecimal montoProgramado = null;
    private BigDecimal montoAprobado = null;
    private java.sql.Timestamp fecharegistro = null;
    private java.sql.Timestamp fechaVencimiento = null;
    private java.sql.Timestamp fechaMaximaDesembolso = null;
    private String fondoLinea = null;
    private String descripcionLinea = null;    
    
    public String numeroLinea = null;
    private String actividadEconomica = null;
    private String ejeEstrategico = null;
    private String areaFocalizacion = null;
    
    //Variables para forzar validacion de fechas y montos de linea de credito al crear contrato de desembolso.
    private Date fechaVencimientoService;
    private Date fechaMaximaService;
    private BigDecimal montoDisponibleService;
    private String estatusTareaBpm;
    
    // variables para popUp de detalle de la linea
    
    public String numeroLineaCreditoRespOut = null;
    public String numeroContrato = null;
    public String descripcionLineaString = null;
    public String moneda = null;
    public String montoAprobadoString = null;
    public String codigoCliente = null;
    public String revolvencia = null;
    public String fondo = null;
    public String lineaFinanciera = null;
    public String destino = null;
    public String tipoRecurso = null;
    public String pais = null;
    public String actividadEconomicaString = null;
    public String sectorInstitucional = null;
    public String ejecutivo = null;
    public String ejeEstrategicoString = null;
    public String areaFocalizacionString = null;
    public String objeticoEstrategico = null;
    public String plantillaLimite = null;
    public String serialLimite = null;
    public String saldo = null;
    public String disponibilidad = null;
    public String disponibilidadIfacil = null;
    public String tieneCondEspeciales = null;
    public String descCondEspeciales = null;
    public String fechaMaxDesembolsar = null;
    public String numeroDesembolsos = null;
    
    
    
    private boolean btnDesestimarSolicitudDeshabilitar = Boolean.TRUE;
    private boolean btnRenderDesestimarDesembolso = Boolean.TRUE;
    private List<CondicionDesembolso> listaCondiciones;
    private Boolean requiereValidacionAsignacion =  Boolean.FALSE;
    private Boolean soloCondicionesCalendario =  Boolean.FALSE;
    
    public void habilitarAccionesSobreTree(){
        logger.warning("*** Inicia el metodo habilitarAccionesSobreTree");
         final int ESTADO_SOLICITUD_DIAPUESTA = 20;
         
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("estadoContratoSeleccionado", "");
         
            verValoresDisponibles();
            if(idEstadoSolicitud != null){     
                    
                    switch(idEstadoSolicitud){
                    
                     case FenixConstants.ESTADO_SOLICITUD_CREADA:  
                           logger.warning("Inf, ESTADO_SOLICITUD_CREADA");
                                this.btnCrearDesembolsoVisible = Boolean.TRUE;
                                this.btnIniciarCumplimientoCondicionesVisible = Boolean.TRUE;
                                this.btnDesestimarDesembolsoVisible = Boolean.TRUE;
                                this.btnDesestimarSollicitudVisible = Boolean.TRUE;
                                this.setBtnDesestimarSolicitudDeshabilitar(Boolean.FALSE);
                             logger.warning("*Inf, fecha de solicitud sera editable");
                                this.fechaSolicitudLectura = Boolean.FALSE;                          
                        break;
                        case FenixConstants.ESTADO_SOLUCITUD_EN_VALIDACION_RECURSOS:
                           logger.warning("Inf, ESTADO_SOLUCITUD_EN_VALIDACION_RECURSOS");
                                     this.btnCrearDesembolsoVisible = Boolean.TRUE;
                                     this.btnIniciarCumplimientoCondicionesVisible = Boolean.TRUE;
                                     this.btnDesestimarDesembolsoVisible = Boolean.TRUE;    
                                     this.desabilitarBtnDesestimarDesembolso = Boolean.TRUE;
                                     this.esCumplimientoCondicion = Boolean.TRUE;                                 
                        break;
                         case FenixConstants.ESTADO_SOLICITUD_EN_CUMPLIMIENTO_CONDICIONES:
                         logger.warning("Inf, ESTADO_SOLICITUD_EN_CUMPLIMIENTO_CONDICIONES");
                               this.btnCrearDesembolsoVisible = Boolean.TRUE;
                               this.btnIniciarCumplimientoCondicionesVisible = Boolean.TRUE;
                               this.btnDesestimarDesembolsoVisible = Boolean.TRUE;    
                               this.btnDesestimarSollicitudVisible = Boolean.TRUE;
                               
                               this.esCumplimientoCondicion = Boolean.TRUE;
                               this.desabilitarBtnDesestimarDesembolso = Boolean.FALSE; 
                               this.setBtnDesestimarSolicitudDeshabilitar(Boolean.TRUE);                          
                       break;
                      case ESTADO_SOLICITUD_DIAPUESTA:
                         logger.warning("Inf, ESTADO_SOLICITUD_DISPUESTA");
                               this.btnCrearDesembolsoVisible = Boolean.TRUE;
                               this.btnIniciarCumplimientoCondicionesVisible = Boolean.TRUE;
                               this.btnDesestimarDesembolsoVisible = Boolean.TRUE;    
                               this.btnDesestimarSollicitudVisible = Boolean.TRUE;
                               
                               this.esCumplimientoCondicion = Boolean.TRUE;
                               this.desabilitarBtnDesestimarDesembolso = Boolean.FALSE;                               
                               this.setBtnDesestimarSolicitudDeshabilitar(Boolean.TRUE);                          
                       break;
                        case FenixConstants.ESTADO_SOLICITUD_CERRADA:
                            logger.warning("Inf, ESTADO_SOLICITUD_CERRADA");
                                this.btnCrearDesembolsoVisible = Boolean.FALSE;
                                this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
                                this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
                                this.btnDesestimarSollicitudVisible = Boolean.FALSE;                                                                                             
                        break;
                        default:
                           
                        break;
                      }                                                
                    
                    
            }else{
                logger.warning("*** Error el el parametro idEstado solicitud es null :(  ");                                     
            }
            logger.warning("*** Termina el metodo habilitarAccionesSobreTree");
        }
    
    
    public Boolean esUsuarioResponsableDeOperacion(){
        logger.warning("*Inf, inicia metodo esUsuarioResponsableDeOperacion para el nivel de SolicitudDesemboso");
       
        Long idOperacion = null;
        String usuarioSession = "";
        BindingContainer bindings = null;
        OperationBinding operBinding = null;
        Boolean esResponsableOperacion = Boolean.FALSE;
                
        //recuperamos el id de la operacionn del taskflow
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}")) {
            idOperacion = (Long)JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}");
        }
        
        //recuperamos la sesión de usuario
        if (null != JSFUtils.resolveExpression("#{securityContext.userName}")) {
            usuarioSession = (String) JSFUtils.resolveExpression("#{securityContext.userName}");
        }

        logger.warning("Usuario en Session: " + usuarioSession);
                             
         try{
              bindings  = ADFUtils.getBindingContainer();
              operBinding = bindings.getOperationBinding("verificarUsuarioComoResponsable");
              operBinding.getParamsMap().put("usuario", usuarioSession);
              operBinding.getParamsMap().put("idOperacion", idOperacion);
              operBinding.execute();            
         }catch(Exception e){
             logger.warning("OperationBinding verificarUsuarioComoResponsable devuelve errores ->", e);
             JSFUtils.addFacesErrorMessage("Error al verificar usuario como responsable->"+e.getMessage());
         }
        
        if(!operBinding.getErrors().isEmpty()){
            logger.warning("Error operBinding obtenerDiasInhabilesMoneda ->"+operBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage("Error operBinding obtenerDiasInhabilesMoneda ->"+operBinding.getErrors().toString());
        }else{
             esResponsableOperacion = (Boolean)operBinding.getResult();  
        }
             
        
        logger.warning("*Inf, termina metodo esUsuarioResponsableDeOperacion esResponsableOperacion: "+esResponsableOperacion);
      return esResponsableOperacion;   
    }
    
    
    public void verValoresDisponibles(){ 
        
       try{
               idEstadoSolicitud = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.idEstadoSolicitud}");
       }catch(Exception e){
               logger.log(ADFLogger.WARNING, "Error al recuperar el idEstadoSolicitud");
       }
        
       try{
            estatusTareaBpm = (null == JSFUtils.resolveExpression("#{pageFlowScope.estatusTareaBpm}"))? null
                            : JSFUtils.resolveExpression("#{pageFlowScope.estatusTareaBpm}").toString();
       }catch(Exception e){
                logger.warning("Error al recuperar el estatusTareaBpm");
       }
       
        
        
        
       logger.warning("-------- Inside verValoresDisponibles ---------");
       logger.warning("idOperacion       -> " + idOperacion); 
       logger.warning("idSolicitud       -> " + idSolicitud); 
       logger.warning("idEstadoSolicitud -> " + idEstadoSolicitud); 
       logger.warning("idContrato        -> " + idContratoDes);
       logger.warning("idLineaCredito    -> " + idLineaCredito);
       logger.warning("Tipo operacion    -> " + operacionSolicitud);
       logger.warning("InstanciaProceso  -> " + instanciaProceso);
       logger.warning("estatusTareaBpm   -> " + estatusTareaBpm);
       logger.warning("------------------------------------------------");
    }

    public void cargarSolicitudDesembolso(){
        if(idOperacion == null || idSolicitud == null){
            logger.log(ADFLogger.WARNING, "parametros requeridos son nulos revise idOperacion, idSolicitud");
        }else{                    
            logger.log(ADFLogger.WARNING, "Inside cargarSolicitudDesembolso");
             
                verValoresDisponibles();                
                inicializarTree(idOperacion, idSolicitud);
            
                //obtenerFechaFlexcube();                                         
                obtenerSumaContratosSolicitud();
                obtenerTipomoneda();
                obtenerLineaCurrent();
                cargarLineaDesembolso();
                actualizarMonedaSolicitud(idOperacion, idSolicitud);
        }
    }
        
    public void inicializarTree(Long idOperacion, Long idSolicitud){
            logger.warning("Inf, Inicia metodo inicializarTree");
            
            logger.warning("***idOperacion->"+idOperacion);
            logger.warning("***idSolicitud->"+idSolicitud);
            
            if(idOperacion == null || idSolicitud == null){
                logger.warning("***Error, parametros requeridos son resueltos a null");
                logger.warning("***Error, idOperacion->"+idOperacion);
                logger.warning("***Error, idSolicitud->"+idSolicitud);
            }else{
            
                BindingContainer bindings = getBindings();
                
                if(null != idTareaBPM && (idTareaBPM.compareTo(FenixConstants.CGD_REALIZAR_AJUSTES_A_DESEMBOLSO)==0 ||
                                          idTareaBPM.compareTo(FenixConstants.CGD_GESTIONAR_PROGRAMACION)==0 || 
                                          idTareaBPM.compareTo(FenixConstants.CGD_REGISTRAR_DESEMBOLSO)==0 || 
                                          idTareaBPM.compareTo(FenixConstants.CGD_VALIDAR_INFORMACION_FINANCIERA_DE_DESEMBOLSO)==0)){
                    logger.warning("Invocando Inicializar Tree TareaBPM.");
                    try{                    
                       OperationBinding operationBinding = bindings.getOperationBinding("inicializarTreeTareaBPM");
                        operationBinding.getParamsMap().put("idOperacion", idOperacion);             
                        operationBinding.getParamsMap().put("idSolicitud", idSolicitud);   
                        operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
                          operationBinding.execute();      
                        
                    }catch(Exception e){
                       logger.log(ADFLogger.ERROR, "Error al inicializarTreeBPM", e);               
                    }

                }else{
                    logger.warning("Invocando Inicializar Tree.");
                    try{                    
                       OperationBinding operationBinding = bindings.getOperationBinding("inicializarTree");
                        operationBinding.getParamsMap().put("idOperacion", idOperacion);             
                        operationBinding.getParamsMap().put("idSolicitud", idSolicitud);               
                          operationBinding.execute();      
                        
                    }catch(Exception e){
                       logger.log(ADFLogger.ERROR, "Error al inicializarTree", e);               
                    }
                }
            }
               logger.warning("Inf, Termina metodo inicializarTree");
        }

    
    public void refrescarTree(){
        logger.warning("Inf, inicia metodo refrescarTree");
           inicializarTree(idOperacion, idSolicitud);
         logger.warning("Inf, Termina metodo refrescarTree");
        }
    
    
    public void obtenerLineaCurrent(){
     logger.warning("Inf, Inicia metodo obtenerLineaCurrent");
        Row fila = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        String  numeroLinea = null; 
            try{                    
                 operationBinding = bindings.getOperationBinding("getLineaSeleccionada");                             
                 operationBinding.execute();                         
                                                   
                 if(!operationBinding.getErrors().isEmpty()){
                      
                      logger.warning("Error operationBinding getLineaSeleccionada->"
                                                      +operationBinding.getErrors().toString());                     
                 }else{
                     
                     fila = (Row)operationBinding.getResult();
                     
                     idLineaCredito = (null == fila.getAttribute("Id"))
                                    ? null : (Long)fila.getAttribute("Id");
                     
                        numeroLinea = (null == fila.getAttribute("NumeroLineaCredito"))
                                    ? null : (String)fila.getAttribute("NumeroLineaCredito");
                     
                     setIdLineaCreditoSeleccionada(idLineaCredito);  
                     setNumeroLineaCredito(numeroLinea);
                 }
                    
                 logger.warning("Inf, idLinea recuperada como current :"+idLineaCredito+" numeroLinea: "+numeroLinea);
                
            }catch(Exception e){
               logger.warning("Error al recueperar la linea de credito Current ->", e);
               JSFUtils.addFacesErrorMessage("Error al recueperar la linea de credito Current ->"
                                                           + e.getLocalizedMessage().toString());
            }
            
            
        logger.warning("Inf, Termina metodo obtenerLineaCurrent");
        }


    public void obtenerTipomoneda(){
        logger.warning("Inf, Inicia metodo obtenerTipomoneda idOperacion: "+idOperacion);
        BindingContainer binding = getBindings();
        try{
            OperationBinding operationBinding = binding.getOperationBinding("obtenerTipoMonedaMontoFormalizado");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            if(!operationBinding.getErrors().isEmpty()){
                logger.warning("No tiene moneda");
                }
            else{
                tipoMoneda = (Integer) operationBinding.execute();
                }

        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en Obtener el Tipo Moneda", e);
            JSFUtils.addFacesErrorMessage("Error al obtener Tipo Moneda. Por favor intente más tarde.");
        }
        logger.warning("Inf, Termina metodo obtenerTipomoneda");
    }
   

    public void obtenetMontoTotal(){
        logger.warning("---Inside obtenerMontoTotal");
        BindingContainer bindings = getBindings();
        Row fila = null;
        try{    
            logger.warning("Inside try");
           OperationBinding operationBinding = bindings.getOperationBinding("precargarQuery");
           operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
           operationBinding.execute();      
            if(operationBinding.getErrors() != null){
                 fila = (Row) operationBinding.getResult();
                montoTotalSolicitud = (BigDecimal)fila.getAttribute("MontoTotalSolicitud");
            }else{
                logger.warning("No se recupero el Monto Total");
            }
        
        }catch(Exception e){
           logger.log(ADFLogger.ERROR, "Error en cargarLineaCreditoDesembolso" + e);
           JSFUtils.addFacesErrorMessage("Error al cargarLineaCreditoDesembolso. Por favor intente más tarde.");
        }
        
        logger.warning("Termina el metodo obtenetMontoTotal montoTotalRecuperado: "+montoTotalSolicitud);
    }
    

    public void obtenerSumaContratosSolicitud(){
        logger.warning("Inicia metodo obtenerSumaContratosSolicitud");
    
        BindingContainer bindings = getBindings();       
        try {
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerMontosSolicitud");        
             operationBinding.getParamsMap().put("idSolicitud", idSolicitud);               
               operationBinding.execute();         
        }catch(Exception e){
                   logger.log(ADFLogger.ERROR, "Error al obtenerSumaContratosSolicitud" + e); 
        }

        logger.warning("Termina metodo obtenerSumaContratosSolicitud");
    }

    
    public void obtenerIdContratoDefault(){
        logger.log(ADFLogger.WARNING, "* Inside obtenerIdContratoDefault *");
        BindingContainer bindings = getBindings();
        if(idContratoDes != null){
            idContratoDefault = idContratoDes;
        }else{
            //Vamos por el primer contrato Seleccionado en el tree table
            try{
                logger.log(ADFLogger.WARNING, " GO_TO < *obtenerIdContratoDefault* >  " + idOperacion);
                OperationBinding operationBinding = bindings.getOperationBinding("getContratoDefault");
                //TODO cambiar a obtenerIdContratoDefault
                operationBinding.execute();            
                idContratoDefault = (Long) operationBinding.getResult();
                if(!operationBinding.getErrors().isEmpty()) {
                    logger.log(ADFLogger.ERROR, "Error al retornar la lineaCredito " +
                               " < : > " + idOperacion);
                }
             
            }catch(Exception e){    
                logger.log(ADFLogger.ERROR, "ERROR al obtener el idContratoDefault" + e);
                //JSFUtils.addFacesErrorMessage("Error al obtener idContratoDefault. Por favor intente más tarde.");
            }   
        }
    }

    public void cargarLineaDesembolso(){
        BindingContainer bindings = getBindings();    
        //Vamos por el tipo moneda de la Solicitud
        try{
            logger.log(ADFLogger.WARNING, " GO_TO < *getTipoMonedaDeSolicitud* >  " + idSolicitud);
            OperationBinding operationBinding = bindings.getOperationBinding("getTipoMonedaDeSolicitud");
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.execute();
            if(operationBinding.getErrors().isEmpty()){
                logger.log(ADFLogger.WARNING, "tMoneda: " +operationBinding.getResult());
                tMoneda = (String) operationBinding.getResult();                
            }else{
                logger.log(ADFLogger.ERROR, "Error al retornar la tipoMoneda de la linea " +
                           " < : > " + idSolicitud);
            }           
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en cargarLineaDesembolso" + e);
            JSFUtils.addFacesErrorMessage("Error al Cargar LineaTipoMoneda. Por favor intente más tarde.");
        }
        
        //Recuperamos datos de la linea de fenix        
        try{            
            OperationBinding operationBinding = bindings.getOperationBinding("setlineaComoCurrent");
            operationBinding.getParamsMap().put("idLineaCredito", idLineaCredito);
            operationBinding.execute();
              if(!operationBinding.getErrors().isEmpty()){
                  logger.warning("***Error, Operation Binding setlineaComoCurrent debuelve errores");
                }           
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "***Error al cargar los datos de la linea de Fenix" + e);        
        }

        //Vamos por los Datos de la linea (*Servicio)        
           /* try{
            if(tMoneda != null){
                tieneTipoMoneda = true;
            }
            logger.log(ADFLogger.WARNING, " GO_TO < *consultarLineaCredito* (service)  >  " + 
                                          idLineaCredito + " tMoneda " + tMoneda );
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
            operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tMoneda);
            operationBindingLineaCredito.execute();
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito"+ e);
        }  */

        //Vamos por la solicitud @Deprecate
         try{ 
            OperationBinding operationBinding = bindings.getOperationBinding("getSolicitudDesembolso");
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.getParamsMap().put("idSolicitudOperacion", idOperacion);
            operationBinding.execute();
            
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en cargarSolicitudDesembolso" + e);
            JSFUtils.addFacesErrorMessage("Error al Cargar solicitud Desembolso. Por favor intente más tarde.");
        } 
    }

      
    public void obtenerFechaFlexcube(){
        logger.log(ADFLogger.WARNING, "--> INTO obtenerFechaFlexcube");
        BindingContainer bindings = getBindings();
        try{ 
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerFechaFlexcube");
            fechaValorFlex = (oracle.jbo.domain.Timestamp) operationBinding.execute();
            logger.log(ADFLogger.WARNING, "fechaFkex " + fechaValorFlex);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en Obtener Fecha Flexcube" + e);
            JSFUtils.addFacesErrorMessage("Error al obtener la fecha flexcube. Por favor intente más tarde.");
        }
    }
          
    public void onPrintSelection(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside onPrintSelection");
        Long idContratoSeleccionado = null;
        
        RichTreeTable treeTable = this.getTreeTable1();
        RowKeySet rks = treeTable.getSelectedRowKeys();        
        Iterator keys = rks.iterator();
        while(keys.hasNext()){
          List key = (List) keys.next();
          treeTable.setRowKey(key);
          JUCtrlHierNodeBinding node = (JUCtrlHierNodeBinding) treeTable.getRowData();
          Row rw = node.getRow();         
          idContratoSeleccionado = (Long) rw.getAttribute("Id"); 
                                          
            //verTcaEstadoContratosRegDesem();
            //desestimarContrato(idContratoSeleccionado);
            
            //Vamos por los Datos de la linea (*Servicio)    
            BindingContainer bindings = getBindings();
            try{
                logger.log(ADFLogger.WARNING, " Evento de cambio de linea   " + 
                                              idLineaCredito + " tMoneda " + tMoneda );
                OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCredito");
                operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
                operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tMoneda);
                operationBindingLineaCredito.execute();
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito" + e);
            }            
        }                        
    }
    
    
    public void onDesestimarSolicitud(ActionEvent actionEvent){
        Boolean esError = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        try{
            OperationBinding operationBinding =  bindings.getOperationBinding("desestimarSolicitud");
            esError = (Boolean) operationBinding.execute();
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en Obtener el Desestimar Contrato" +e);
            JSFUtils.addFacesErrorMessage("Error al desestimar contratoa. Por favor intente más tarde."+e);
        }
        if(esError){
            JSFUtils.addFacesErrorMessage(MSG_DESESTIMAR + idSolicitud);                       
        }
    }
    
    
    public void verTcaEstadoContratosRegDesem() {
        logger.warning("Inf, Inicia metodo verTcaEstadoContratosRegDesem");
        esVisible = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        try{
            OperationBinding operationBinding =  bindings.getOperationBinding("verTcaEstadoContratosRegDesem");
             esVisible = (Boolean) operationBinding.execute();
            
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en Obtener el Desestimar Contrato" + e);
            JSFUtils.addFacesErrorMessage("Error al desestimar contratoa. Por favor intente más tarde." + e);
        }              
      logger.warning("Inf, Termina metodo verTcaEstadoContratosRegDesem");
    }
    
    public void getModoEjecucion(){
        logger.warning("Inicia metodo getModoEjecucion idTarea bpm recibido ->"+idTareaBPM);
        Boolean esSoloLectura = Boolean.FALSE;
        if(null != idTareaBPM){
            
            if(!(idTareaBPM.compareTo(FenixConstants.CG10_GESTOR_DESEMBOLSOS)==0)){
                logger.warning("Invocando metodo consultarOperacionByIdOperacion");
                consultarOperacionByIdOperacion();
            }
            logger.warning("*Inf, SWITCH TAREA BPM");
            switch(idTareaBPM){
            case FenixConstants.CG10_GESTOR_DESEMBOLSOS:
                Map<String,Object> resultMap = new HashMap<String,Object>();
                //setEsCumplimientoCondicion(Boolean.FALSE);
                //TODO: Implementacion ID_ESTADO_SOLICITUD / Modos de ejecucion
                logger.warning("Configurando formulario de Linea de credito en Gestor de desembolsos para obtener datos de POJO.");
                this.setEsVisibleDatosLineaCredito(Boolean.TRUE);                
                logger.warning("**Inf, se hace la llamda al servicio para actulizar los detalles de la linea");
                logger.warning("Parametros idLinea: "+idLineaCredito+ " Moneda: " +tipoMoneda );
                
                BindingContainer bindings = getBindings(); 
                OperationBinding operationBindingLineaCredito = null;
                
                try{                                                    
                    operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
                    operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
                      if(null!=tipoMoneda){
                            operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
                      }
                     operationBindingLineaCredito.execute();
                }catch(Exception e){
                    logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
                }
                   
                    resultMap = (Map)operationBindingLineaCredito.getResult();
                    //Recuperan montos
                    BigDecimal montoTotal1=BigDecimal.ZERO;
                    montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
                    if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                            montoLinea=montoTotal1.doubleValue();
                        }
                    montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
                    montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
                    montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
                    montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
                    //recuperan fechas
                    fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
                    fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
                    fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
                       
                       
                            fondoLinea  = (resultMap.get("fondoLinea") == null) ? null
                                        : (String)resultMap.get("fondoLinea");
                            
                            idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                                        : (Integer)resultMap.get("idTipoMonedaMontoLinea");     
                                          
                            idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                                            : (Integer)resultMap.get("idTipoMonedaMontoTotal");
							
                            numeroLinea  = (resultMap.get("numeroLinea") == null) ? null
                                        : (String)resultMap.get("numeroLinea");                
                
                       descripcionLinea = (resultMap.get("descripcionLinea") == null) ? null
                                        : (String)resultMap.get("descripcionLinea");
                                                
                         ejeEstrategico = (resultMap.get("ejeEstrategico") == null) ? null
                                        : (String)resultMap.get("ejeEstrategico");
                   
                       areaFocalizacion = (resultMap.get("areaFocalizacion") == null) ? null
                                        : (String)resultMap.get("areaFocalizacion");
                 
                    actividadEconomica  = (resultMap.get("actividadEconomica") == null) ? null
                                        : (String)resultMap.get("actividadEconomica");                                   
              
                       montoProgramado  = (resultMap.get("PROGRAMADO") == null) ? null
                                        : (BigDecimal)resultMap.get("PROGRAMADO");
                
                   //datos para el PopUp de detalle de la linea 
                
                    
                   numeroLineaCreditoRespOut = (resultMap.get("numeroLineaCreditoRespOut") == null) ? null
                                             : resultMap.get("numeroLineaCreditoRespOut").toString();
                                             
                              numeroContrato = (resultMap.get("numeroContrato") == null) ? null
                                             : resultMap.get("numeroContrato").toString();
                                             
                      descripcionLineaString = (resultMap.get("descripcionLineaString") == null) ? null
                                             : resultMap.get("descripcionLineaString").toString();
                                             
                                      moneda = (resultMap.get("moneda") == null) ? null
                                             : resultMap.get("moneda").toString();
                                             
                        montoAprobadoString = (resultMap.get("montoAprobadoString") == null) ? null
                                             : resultMap.get("montoAprobadoString").toString();
                                             
                               codigoCliente = (resultMap.get("codigoCliente") == null) ? null
                                             : resultMap.get("codigoCliente").toString();
                                             
                                 revolvencia = (resultMap.get("revolvencia") == null) ? null
                                             : resultMap.get("revolvencia").toString();
                                             
                                       fondo = (resultMap.get("fondo") == null) ? null
                                             : resultMap.get("fondo").toString();
                                             
                             lineaFinanciera = (resultMap.get("lineaFinanciera") == null) ? null
                                             : resultMap.get("lineaFinanciera").toString();
                                             
                                     destino = (resultMap.get("destino") == null) ? null
                                             : resultMap.get("destino").toString();
                                             
                                 tipoRecurso = (resultMap.get("tipoRecurso") == null) ? null
                                             : resultMap.get("tipoRecurso").toString();
                                             
                                        pais = (resultMap.get("pais") == null) ? null
                                             : resultMap.get("pais").toString();
                                             
                    actividadEconomicaString = (resultMap.get("actividadEconomicaString") == null) ? null
                                             : resultMap.get("actividadEconomicaString").toString();
                                             
                         sectorInstitucional = (resultMap.get("sectorInstitucional") == null) ? null
                                             : resultMap.get("sectorInstitucional").toString();
                                             
                                   ejecutivo = (resultMap.get("ejecutivo") == null) ? null
                                             : resultMap.get("ejecutivo").toString();
                                             
                        ejeEstrategicoString = (resultMap.get("ejeEstrategicoString") == null) ? null
                                             : resultMap.get("ejeEstrategicoString").toString();
                                             
                      areaFocalizacionString = (resultMap.get("areaFocalizacionString") == null) ? null
                                             : resultMap.get("areaFocalizacionString").toString();
                                             
                         objeticoEstrategico = (resultMap.get("objeticoEstrategico") == null) ? null
                                             : resultMap.get("objeticoEstrategico").toString();
                                             
                             plantillaLimite = (resultMap.get("plantillaLimite") == null) ? null
                                             : resultMap.get("plantillaLimite").toString();
                                             
                                serialLimite = (resultMap.get("serialLimite") == null) ? null
                                             : resultMap.get("serialLimite").toString();
                                             
                                       saldo = (resultMap.get("saldo") == null) ? null
                                             : resultMap.get("saldo").toString();
                                             
                              disponibilidad = (resultMap.get("disponibilidad") == null) ? null
                                             : resultMap.get("disponibilidad").toString();
                                             
                        disponibilidadIfacil = (resultMap.get("disponibilidadIfacil") == null) ? null
                                             : resultMap.get("disponibilidadIfacil").toString();
                                             
                         tieneCondEspeciales = (resultMap.get("tieneCondEspeciales") == null) ? null
                                             : resultMap.get("tieneCondEspeciales").toString();
                                             
                          descCondEspeciales = (resultMap.get("descCondEspeciales") == null) ? null
                                             : resultMap.get("descCondEspeciales").toString();
                                             
                         fechaMaxDesembolsar = (resultMap.get("fechaMaxDesembolsar") == null) ? null
                                             : resultMap.get("fechaMaxDesembolsar").toString();
                                             
                           numeroDesembolsos = (resultMap.get("numeroDesembolsos") == null) ? null
                                             : resultMap.get("numeroDesembolsos").toString();
                                             
                                                                                                        
                if(null != idEstadoSolicitud){
                    switch(idEstadoSolicitud){
                    case FenixConstants.ESTADO_SOLICITUD_CREADA:
                        logger.warning("*Inf, fecha de solicitud sera editable");
                        this.fechaSolicitudLectura = Boolean.FALSE;
                        break;
                    case FenixConstants.ESTADO_SOLUCITUD_EN_VALIDACION_RECURSOS:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_EN_CUMPLIMIENTO_CONDICIONES:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_EN_PROCESO:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_CERRADA:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_CREADO:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_VALIDACION_DE_RECURSOS:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_Cumplimiento_de_condiciones:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_Preparado:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_EN_TRANSITO:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_APROBADO_POR_GERENTE:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_FONDOS_RESERVADOS:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_REGISTRADO:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_DESEMBOLSADO:
                        break;
                    case FenixConstants.ESTADO_SOLICITUD_DESESTIMADO:
                        //ocultamos botones de accion del tree sobre el contrato
                        this.btnCrearDesembolsoVisible = Boolean.FALSE;
                        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
                        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
                        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
                        break;
                    default:
                        break;
                    }
                }
                
                if(!esUsuarioResponsableDeOperacion()){  
                    logger.warning("Configurando partalla para usuario diferente a responsable");
                    this.fechaSolicitudLectura = Boolean.TRUE;
                    this.btnCrearDesembolsoVisible = Boolean.FALSE;
                    this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
                    this.setBtnDesestimarDesembolsoVisible(Boolean.FALSE);                    
                    this.btnDesestimarSollicitudVisible = Boolean.FALSE; 
                    this.setDesabilitarBtnDesestimarDesembolso(Boolean.TRUE);   
                    this.btnRenderDesestimarDesembolso = Boolean.FALSE;
                }                
                
                
                break;
            case FenixConstants.CGD_VALIDAR_FONDOS_PRESUPUESTARIOS:
                
                tareaBpmValidarFondosPresupuestarios();
                
                break;
            case FenixConstants.CGD_GESTIONAR_DESEMBOLSO_DE_FUENTES_EXTERNAS:
                break;
            case FenixConstants.CGD_REALIZAR_AJUSTES_A_DESEMBOLSO:   
                    //Se agrega bandera para mostrar panelGroupLayout de datos de la linea de credito
                    this.setEsVisibleDatosLineaCredito(Boolean.TRUE);
                    comportamientoEnTareaBpmRealizarAjustesDesembolso();  
                
                break;
            case FenixConstants.CGD_VALIDAR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS:
                
                tareaBpmValidarClasificacionEstrategica();
                
                break;
            case FenixConstants.CGD_VALIDAR_INFORMACION_FINANCIERA_DE_DESEMBOLSO:
                
                comportamientoTareaBpmValidarInformacionFinanciera();                
                
                break;  
            case FenixConstants.CGD_APROBAR_DESEMBOLSO:
                break;   
            case FenixConstants.CGD_GESTIONAR_PROGRAMACION:
                logger.warning("Configuracion de solicitud para pantalla GestionarProgramacion.");
                comportamientoGestionarProgramacion();
                break;   
            case FenixConstants.CGD_RESERVAR_FONDOS:
                break;   
            case FenixConstants.CGD_REGISTRAR_DESEMBOLSO:
                logger.warning("Configuracion de solicitud para pantalla Registrar desembolso.");
                comportamientoTareaBpmRegistrarDesembolso();
                break;   
            case FenixConstants.CGD_LIQUIDAR_CONTRATO_DE_DESEMBOLSO:
                break;   
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_DAECI:
                treeVisible = Boolean.FALSE;
                tareaBpmValidarAsignacionRecursosDaeci();
                esSoloLectura = Boolean.TRUE;
                idModoEjecucion = 1;
                break;   
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_FINAM:
                
                tareaBpmvalidarAsignacinoRecursosFinam();
                
                break;   
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PCT:
                
                tareaBpmValidarAsignacionRecursosPct();
                
                break; 
            case FenixConstants.CGD_VALIDAR_RAROC:
                    
                tareaBpmValidarRAROC();
                    
                break;  
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PREA:
                
                tareaBpmVAlidarAsignacionRecursosPrea();
                
                break;   
            case FenixConstants.CGD_REALIZAR_AJUSTES_DE_ASIGNACION_DE_RECURSOS:
                esVisible = Boolean.TRUE;
                esSoloLectura = true;
                idModoEjecucion = 1;                
                configurarBotonesTareaBpmRealizarAjustesAsignacionRecursos();
                break;   
            case FenixConstants.CGD_JUSTIFICAR_DESEMBOLSO_POR_EXCEPCION:
                break;   
            case FenixConstants.CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION:
                
                tareaBpmValidarDesembolsoExcepcion();
                
                break; 
            default:
                break;
            }
        }else{
            logger.warning("El idTareaBPM del TaskFlow es NULL");
        }        

        logger.warning("Termina metodo getModoEjecucion");
        verValoresDisponibles();
    }
    
    public void consultarOperacionByIdOperacion(){
        logger.warning("Inicia metodo consultarOperacionByIdOperacion");
        Long idOperacionActiva = getIdOperacion();
        Map<String, Object> params = new HashMap<String, Object>();
        
        if(null != idOperacionActiva){
            params.put("idOperacion", idOperacionActiva);
            try{
                execute(params, "consultarOperacionByIdOperacion");
            }catch(Exception e){
                logger.warning("ERROR al ejecutar consultarOperacionByIdOperacion");
            }
        }else{
            logger.warning("IdOperacion es NULL;");
        }
        
        logger.warning("Termina metodo consultarOperacionByIdOperacion");
    }

 //-- Comportamiento del componente en la tarea de CGD_REALIZAR_AJUSTES_A_DESEMBOLSO -->
    
    public void comportamientoEnTareaBpmRealizarAjustesDesembolso(){        
            logger.warning("Entro a comportamientoEnTareaBpmRealizarAjustesDesembolso ");
            Map<String,Object> resultMap = new HashMap<String,Object>();
              this.treeVisible = Boolean.FALSE;  //  ocultamos el tree
              this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA; // para ver editable la fecha y el monto de la cabecera del comoponente
              //ocultamos botones de accion del tree sobre el contrato
                this.btnCrearDesembolsoVisible = Boolean.FALSE;
                this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
                this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
                this.btnDesestimarSollicitudVisible = Boolean.FALSE;
                this.btnDetalleLineaVisible = Boolean.FALSE;
             
                this.idContratoSeleccionado = this.idContratoDes;
        
            //obtenemos los datos la tabla tre_solicitud_linea_credito en base al IdContrato                    
             Row rowDatosContrato = null;            
            try{ 
                BindingContainer bindings = getBindings();
                OperationBinding operationBinding = bindings.getOperationBinding("getTreSolicitudLineaCredito");
                operationBinding.getParamsMap().put("idContrato", idContratoDes);               
                rowDatosContrato = (Row)operationBinding.execute();
                
                if(!operationBinding.getErrors().isEmpty()){
                    logger.warning("***OperationBinding devuelve errores");
                }
                
            }catch(Exception e){
                 logger.warning("** :( Error al ejecutar el operationBinding: ", e);
            }    
            
            if(rowDatosContrato != null){
                logger.warning("** se recuperaron valores asociados al contato IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud);                  
                   Long idLinea = (Long)rowDatosContrato.getAttribute("IdLinea");      
                   this.idSolicitud = (Long)rowDatosContrato.getAttribute("IdSolicitud");
               // Establecemos la linea encontrada como actual en la VO  
                   
//                try{ 
//                    BindingContainer bindings = getBindings();
//                    OperationBinding operationBinding = bindings.getOperationBinding("establecerRowComoActual");
//                    operationBinding.getParamsMap().put("idLinea", idLinea);               
//                    operationBinding.getParamsMap().put("idSolicitud", idSolicitud);                                   
//                    operationBinding.execute();
//                    
//                    if(!operationBinding.getErrors().isEmpty()){
//                        logger.warning("***OperationBinding devuelve errores");
//                    }
//                    
//                }catch(Exception e){
//                     logger.warning("***Error al ejecutar el operationBinding: "+ e);
//                }    
            // se hace la llamda al servicio para actulizar los detalles de la linea 
            logger.warning("**Inf, se hace la llamda al servicio para actulizar los detalles de la linea");
            try{
                BindingContainer bindings = getBindings(); 
                logger.log(ADFLogger.WARNING, " Evento de cambio de linea   " + 
                                              idLineaCredito+ " tMoneda " +tipoMoneda );
                OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
                operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
                if(null!=tipoMoneda){
                        operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
                    }
                operationBindingLineaCredito.execute();
                resultMap = (Map) operationBindingLineaCredito.getResult();
                //Recuperan montos
                BigDecimal montoTotal1=BigDecimal.ZERO;
                montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
                if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                        montoLinea=montoTotal1.doubleValue();
                }
				
                idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                                : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                              
                idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                                : (Integer)resultMap.get("idTipoMonedaMontoTotal");
				
                montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
                montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
                montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
                montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
                //recuperan fechas
                fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
                fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
                fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
                
                setEsVisibleDatosLineaCredito(Boolean.TRUE);
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
            }
                                                    
        }else{
            logger.warning(" **** El metodo getRowSeleccionada esta debolviendo un row nulo :( ");                        
            JSFUtils.addFacesErrorMessage(" Error al recuperar datos de la fila actual Datos de la linea  -idLinea -numeroCredito");                       
        }
            logger.warning("*Inf, Saliendo de comportamientoEnTareaBpmRealizarAjustesDesembolso con datos:");                                       
            logger.warning("*Inf, IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud+"idContratoDes->"+idContratoDes);                  
            
        }
    
    public void comportamientoGestionarProgramacion(){
        logger.warning("Tarea BPM: GESTIONAR PROGRAMACION.");
        this.treeVisible = Boolean.FALSE;
        this.btnCrearDesembolsoVisible = Boolean.FALSE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
        this.btnDetalleLineaVisible = Boolean.FALSE;
        
        this.idContratoSeleccionado = this.idContratoDes;
        
        //obtenemos los datos la tabla tre_solicitud_linea_credito en base al IdContrato
        Row rowDatosContrato = null;
        try{
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("getTreSolicitudLineaCredito");
            operationBinding.getParamsMap().put("idContrato", idContratoDes);               
            rowDatosContrato = (Row)operationBinding.execute();
    
            if(!operationBinding.getErrors().isEmpty()){
            logger.warning("***OperationBinding devuelve errores");
            }
    
        }catch(Exception e){
            logger.warning("** :( Error al ejecutar el operationBinding: ", e);
        }
        
        if(rowDatosContrato != null){
            logger.warning("** se recuperaron valores asociados al contato IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud);                  
            Long idLinea = (Long)rowDatosContrato.getAttribute("IdLinea");      
            this.idSolicitud = (Long)rowDatosContrato.getAttribute("IdSolicitud");
            // Establecemos la linea encontrada como actual en la VO
               
//            try{ 
//                BindingContainer bindings = getBindings();
//                OperationBinding operationBinding = bindings.getOperationBinding("establecerRowComoActual");
//                operationBinding.getParamsMap().put("idLinea", idLinea);               
//                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);                                   
//                operationBinding.execute();
//                
//                if(!operationBinding.getErrors().isEmpty()){
//                    logger.warning("***OperationBinding devuelve errores");
//                }
//                
//            }catch(Exception e){
//                logger.warning("***Error al ejecutar el operationBinding: "+ e);
//            }    
            
            // se hace la llamda al servicio para actulizar los detalles de la linea
            logger.warning("**Inf, se hace la llamda al servicio para actulizar los detalles de la linea");
            recuperarLineaCreditoGestionarProgramacion();                             
        }else{
            logger.warning(" **** El metodo getRowSeleccionada esta debolviendo un row nulo :( ");
            JSFUtils.addFacesErrorMessage(" Error al recuperar datos de la fila actual Datos de la linea  -idLinea -numeroCredito");
        }
        
        logger.warning("Termina metodo: IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud+"idContratoDes->"+idContratoDes);
    
    }
    
    public void recuperarLineaCreditoGestionarProgramacion(){
        logger.warning("Inicia metodo recuperarLineaCreditoGestionarProgramacion.");
        logger.warning("Valores necesarios para invocar metodo: LineaCredito: " + idLineaCredito + ", tipoMoneda: " + tipoMoneda);
        Map<String,Object> params = new HashMap<String, Object>();
        Map resultMap = new HashMap();
        
        obtenerTipomoneda();
        
        if(null != idLineaCredito){
            params.put("idLineaCredito", idLineaCredito);
            if(null!=tipoMoneda){
                params.put("tipoMoneda", tipoMoneda); 
                }

            try{
                resultMap = execute(params, "consultarLineaCreditoByIdLineaCredito");
            }catch(Exception e){
                logger.warning("ERROR al ejecutar Operation Binding.", e);
            }
        }
        
        if(resultMap.size()>0){
            logger.warning("Recuperando valores de lineaCredito.");
            try{
                //Recuperan montos
                BigDecimal montoTotal1=BigDecimal.ZERO;
                montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
                if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                        montoLinea=montoTotal1.doubleValue();
                }
				
                idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                                : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                              
                idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                                : (Integer)resultMap.get("idTipoMonedaMontoTotal");
				
                montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
                montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
                montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
                montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
                //recuperan fechas
                fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
                fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
                fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            }catch(Exception e){
                logger.warning("ERROR al recuperar los datos de la linea.", e);
            }
        }
        logger.warning("Activando formulario de linea basado en mapa.");
        this.setEsVisibleDatosLineaCredito(Boolean.TRUE);
        logger.warning("Termina metodo recuperarLineaCreditoGestionarProgramacion.");
    }
    
    public void comportamientoTareaBpmValidarInformacionFinanciera(){
        logger.warning("*Inf, inicia el metodo comportamientoTareaBpmValidarInformacionFinanciera  Modo ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura       
        Map<String,Object> resultMap = new HashMap<String,Object>();
          this.treeVisible = Boolean.FALSE;  //  ocultamos el tree
          this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA; // para ver editable la fecha y el monto de la cabecera del comoponente
          //ocultamos botones de accion del tree sobre el contrato
            this.btnCrearDesembolsoVisible = Boolean.FALSE;
            this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
            this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
            this.btnDesestimarSollicitudVisible = Boolean.FALSE;
            this.btnDetalleLineaVisible = Boolean.FALSE;
            
            this.idContratoSeleccionado = this.idContratoDes;
        
        logger.warning("*Inf, idContratoSeleccionado: "+idContratoSeleccionado);
        
        //obtenemos los datos la tabla tre_solicitud_linea_credito en base al IdContrato                    
         Row rowDatosContrato = null;            
        try{ 
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("getTreSolicitudLineaCredito");
            operationBinding.getParamsMap().put("idContrato", idContratoDes);               
            rowDatosContrato = (Row)operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                logger.warning("***OperationBinding devuelve errores");
            }
            
        }catch(Exception e){
             logger.warning("** :( Error al ejecutar el operationBinding: ", e);
        }    
        
        if(rowDatosContrato != null){
            logger.warning("** se recuperaron valores asociados al contato IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud);                  
               Long idLinea = (Long)rowDatosContrato.getAttribute("IdLinea");      
               this.idSolicitud = (Long)rowDatosContrato.getAttribute("IdSolicitud");
           // Establecemos la linea encontrada como actual en la VO  
               
//            try{ 
//                BindingContainer bindings = getBindings();
//                OperationBinding operationBinding = bindings.getOperationBinding("establecerRowComoActual");
//                operationBinding.getParamsMap().put("idLinea", idLinea);               
//                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);                                   
//                operationBinding.execute();
//                
//                if(!operationBinding.getErrors().isEmpty()){
//                    logger.warning("***OperationBinding devuelve errores");
//                }
//                
//            }catch(Exception e){
//                 logger.warning("***Error al ejecutar el operationBinding: "+ e);
//            }
            
        // se hace la llamda al servicio para actulizar los detalles de la linea 
        logger.warning("**Inf, se hace la llamda al servicio para ver los datos de la linea");
        try{
            BindingContainer bindings = getBindings(); 
            logger.warning(" Evento de cambio de linea   " + idLineaCredito+ " tMoneda " +tipoMoneda );
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
            operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
            operationBindingLineaCredito.execute();
            resultMap = (Map) operationBindingLineaCredito.getResult();
            //Recuperan montos
            BigDecimal montoTotal1=BigDecimal.ZERO;
            montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
            if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                    montoLinea=montoTotal1.doubleValue();
            }
			
            idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                          
            idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoTotal");
			
            montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
            montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
            montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
            montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
            //recuperan fechas
            fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
            fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
            fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            
            setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
                                                
        }else{
        logger.warning(" **** El metodo getRowSeleccionada esta debolviendo un row nulo :( ");                        
        JSFUtils.addFacesErrorMessage(" Error al recuperar datos de la fila actual Datos de la linea  -idLinea -numeroCredito");                       
        }
        logger.warning("*Inf, Saliendo de comportamientoTareaBpmValidarInformacionFinanciera con datos:");                                       
        logger.warning("*Inf, IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud+"idContratoDes->"+idContratoDes);                  
        
    }
    
    public void comportamientoTareaBpmRegistrarDesembolso(){
        logger.warning("*Inf, inicia el metodo comportamientoTareaBpmRegistrarDesembolso  Modo ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura       
        Map<String,Object> resultMap = new HashMap<String,Object>();
          this.treeVisible = Boolean.FALSE;  //  ocultamos el tree
          this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA; // para ver editable la fecha y el monto de la cabecera del comoponente
          //ocultamos botones de accion del tree sobre el contrato
            this.btnCrearDesembolsoVisible = Boolean.FALSE;
            this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
            this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
            this.btnDesestimarSollicitudVisible = Boolean.FALSE;
            this.btnDetalleLineaVisible = Boolean.FALSE;
            this.idContratoSeleccionado = this.idContratoDes;
        
        logger.warning("*Inf, idContratoSeleccionado: "+idContratoSeleccionado);
        
        //obtenemos los datos la tabla tre_solicitud_linea_credito en base al IdContrato                    
         Row rowDatosContrato = null;            
        try{ 
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("getTreSolicitudLineaCredito");
            operationBinding.getParamsMap().put("idContrato", idContratoDes);               
            rowDatosContrato = (Row)operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                logger.warning("***OperationBinding devuelve errores");
            }
            
        }catch(Exception e){
             logger.warning("** :( Error al ejecutar el operationBinding: ", e);
        }    
        
        if(rowDatosContrato != null){
            logger.warning("** se recuperaron valores asociados al contato IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud);                  
               Long idLinea = (Long)rowDatosContrato.getAttribute("IdLinea");      
               this.idSolicitud = (Long)rowDatosContrato.getAttribute("IdSolicitud");
           // Establecemos la linea encontrada como actual en la VO  
               
//            try{ 
//                BindingContainer bindings = getBindings();
//                OperationBinding operationBinding = bindings.getOperationBinding("establecerRowComoActual");
//                operationBinding.getParamsMap().put("idLinea", idLinea);               
//                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);                                   
//                operationBinding.execute();
//                
//                if(!operationBinding.getErrors().isEmpty()){
//                    logger.warning("***OperationBinding devuelve errores");
//                }
//                
//            }catch(Exception e){
//                 logger.warning("***Error al ejecutar el operationBinding: "+ e);
//            }    
        // se hace la llamda al servicio para actulizar los detalles de la linea 
        logger.warning("**Inf, se hace la llamda al servicio para ver los datos de la linea");
        try{
            BindingContainer bindings = getBindings(); 
            logger.warning(" Evento de cambio de linea   " + idLineaCredito+ " tMoneda " +tipoMoneda );
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
            operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
            operationBindingLineaCredito.execute();
            resultMap = (Map) operationBindingLineaCredito.getResult();
            //Recuperan montos
            BigDecimal montoTotal1=BigDecimal.ZERO;
            montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
            if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                    montoLinea=montoTotal1.doubleValue();
                }
            
            idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                    : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                                      
            idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                    : (Integer)resultMap.get("idTipoMonedaMontoTotal");
            
            montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
            montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
            montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
            montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
            //recuperan fechas
            fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
            fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
            fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            
            setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
                                                
        }else{
        logger.warning(" **** El metodo getRowSeleccionada esta debolviendo un row nulo :( ");                        
        JSFUtils.addFacesErrorMessage(" Error al recuperar datos de la fila actual Datos de la linea  -idLinea -numeroCredito");                       
        }
        logger.warning("*Inf, Saliendo de comportamientoTareaBpmRegistrarDesembolso con datos:");                                       
        logger.warning("*Inf, IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud+"idContratoDes->"+idContratoDes);                  
        
    }
    
    public void tareaBpmValidarClasificacionEstrategica(){
        logger.warning("Tarea BPM: VALIDAR CLASIFICACION ESTRATEGICA, Modo de ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura
        this.treeVisible = Boolean.TRUE;
        this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA;
        //ocultamos botones de accion del tree sobre el contrato
        this.btnCrearDesembolsoVisible = Boolean.FALSE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
    }
    
    public void tareaBpmValidarFondosPresupuestarios(){
        logger.warning("Tarea BPM: VALIDAR FONDOS PRESUPUESTARIOS, Modo de ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura
        Map<String,Object> resultMap = new HashMap<String,Object>();
            this.treeVisible = Boolean.FALSE;
        this.contratoVisible = Boolean.FALSE;
        this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA;
        //ocultamos botones de accion del tree sobre el contrato
        this.btnCrearDesembolsoVisible = Boolean.FALSE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
        this.btnDetalleLineaVisible = Boolean.FALSE;
        
        this.idContratoSeleccionado = this.idContratoDes;
        
        logger.warning("*Inf, idContratoSeleccionado: "+idContratoSeleccionado);
        
        //obtenemos los datos la tabla tre_solicitud_linea_credito en base al IdContrato
        Row rowDatosContrato = null;
        try{
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("getTreSolicitudLineaCredito");
        operationBinding.getParamsMap().put("idContrato", idContratoDes);               
        rowDatosContrato = (Row)operationBinding.execute();
        
        if(!operationBinding.getErrors().isEmpty()){
            logger.warning("***OperationBinding devuelve errores");
        }
        
        }catch(Exception e){
         logger.warning("** :( Error al ejecutar el operationBinding: ", e);
        }
        
        if(rowDatosContrato != null){
        logger.warning("** se recuperaron valores asociados al contato IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud);                  
           Long idLinea = (Long)rowDatosContrato.getAttribute("IdLinea");      
           this.idSolicitud = (Long)rowDatosContrato.getAttribute("IdSolicitud");
        // Establecemos la linea encontrada como actual en la VO
           
        //            try{
        //                BindingContainer bindings = getBindings();
        //                OperationBinding operationBinding = bindings.getOperationBinding("establecerRowComoActual");
        //                operationBinding.getParamsMap().put("idLinea", idLinea);
        //                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
        //                operationBinding.execute();
        //
        //                if(!operationBinding.getErrors().isEmpty()){
        //                    logger.warning("***OperationBinding devuelve errores");
        //                }
        //
        //            }catch(Exception e){
        //                 logger.warning("***Error al ejecutar el operationBinding: "+ e);
        //            }
        
        // se hace la llamda al servicio para actulizar los detalles de la linea
        logger.warning("**Inf, se hace la llamda al servicio para ver los datos de la linea");
        try{
        BindingContainer bindings = getBindings(); 
        logger.warning(" Evento de cambio de linea   " + idLineaCredito+ " tMoneda " +tipoMoneda );
        OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
        operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
        operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
        operationBindingLineaCredito.execute();
        resultMap = (Map) operationBindingLineaCredito.getResult();
        //Recuperan montos
        BigDecimal montoTotal1=BigDecimal.ZERO;
        montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
        if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                montoLinea=montoTotal1.doubleValue();
            }
            
        idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                                      
        idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                : (Integer)resultMap.get("idTipoMonedaMontoTotal");
            
        montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
        montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
        montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
        montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
        //recuperan fechas
        fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
        fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
        fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
        
        setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
        logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
                                            
        }else{
        logger.warning(" **** El metodo getRowSeleccionada esta debolviendo un row nulo :( ");
        JSFUtils.addFacesErrorMessage(" Error al recuperar datos de la fila actual Datos de la linea  -idLinea -numeroCredito");
        }
        logger.warning("*Inf, Saliendo de comportamientoTareaBpmValidarInformacionFinanciera con datos:");
        logger.warning("*Inf, IdLinea->"+idLineaCredito+" IdSolicitud->"+idSolicitud+"idContratoDes->"+idContratoDes);
    }
    
    public void tareaBpmValidarAsignacionRecursosDaeci(){
        logger.warning("Tarea BPM: VALIDAR ASIGNACION DE RECURSOS DAECI, Modo de ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura
        this.treeVisible = Boolean.TRUE;
        this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA;
        //ocultamos botones de accion del tree sobre el contrato
        this.btnCrearDesembolsoVisible = Boolean.FALSE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
        this.btnDetalleLineaVisible = Boolean.FALSE;
        
        try{
            Map<String,Object> resultMap = new HashMap<String,Object>();
            BindingContainer bindings = getBindings(); 
            logger.log(ADFLogger.WARNING, " Evento de cambio de linea   " + 
                                          idLineaCredito+ " tMoneda " +tipoMoneda );
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
            if(null!=tipoMoneda){
                    operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
                }
            operationBindingLineaCredito.execute();
            resultMap = (Map) operationBindingLineaCredito.getResult();
            //Recuperan montos
            BigDecimal montoTotal1=BigDecimal.ZERO;
            montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
            if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                    montoLinea=montoTotal1.doubleValue();
            }
			
            idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                          
            idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoTotal");
			
            montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
            montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
            montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
            montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
            //recuperan fechas
            fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
            fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
            fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            
            setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
    }
    
    public void tareaBpmvalidarAsignacinoRecursosFinam(){
        logger.warning("Tarea BPM: VALIDAR ASIGNACION DE RECURSOS FINAM, Modo de ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura
        this.treeVisible = Boolean.TRUE;
        this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA;
        //ocultamos botones de accion del tree sobre el contrato
        this.btnCrearDesembolsoVisible = Boolean.FALSE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
        this.btnDetalleLineaVisible = Boolean.FALSE;
        
        try{
            Map<String,Object> resultMap = new HashMap<String,Object>();
            BindingContainer bindings = getBindings(); 
            logger.log(ADFLogger.WARNING, " Evento de cambio de linea   " + 
                                          idLineaCredito+ " tMoneda " +tipoMoneda );
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
            if(null!=tipoMoneda){
                    operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
                }
            operationBindingLineaCredito.execute();
            resultMap = (Map) operationBindingLineaCredito.getResult();
            //Recuperan montos
            BigDecimal montoTotal1=BigDecimal.ZERO;
            montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
            if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                    montoLinea=montoTotal1.doubleValue();
            }
			
            idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                          
            idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoTotal");
			
            montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
            montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
            montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
            montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
            //recuperan fechas
            fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
            fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
            fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            
            setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
    }
    
    public void tareaBpmValidarAsignacionRecursosPct(){
        logger.warning("Tarea BPM: VALIDAR ASIGNACION DE RECURSOS PCT, Modo de ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura
        this.treeVisible = Boolean.TRUE;
        this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA;
        //ocultamos botones de accion del tree sobre el contrato
        this.btnCrearDesembolsoVisible = Boolean.FALSE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
        this.btnDetalleLineaVisible = Boolean.FALSE;
        
        try{
            Map<String,Object> resultMap = new HashMap<String,Object>();
            BindingContainer bindings = getBindings(); 
            logger.log(ADFLogger.WARNING, " Evento de cambio de linea   " + 
                                          idLineaCredito+ " tMoneda " +tipoMoneda );
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
            if(null!=tipoMoneda){
                    operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
                }
            operationBindingLineaCredito.execute();
            resultMap = (Map) operationBindingLineaCredito.getResult();
            //Recuperan montos
            BigDecimal montoTotal1=BigDecimal.ZERO;
            montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
            if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                    montoLinea=montoTotal1.doubleValue();
            }
			
			idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                                        : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                          
            idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoTotal");
			
            montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
            montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
            montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
            montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
            //recuperan fechas
            fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
            fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
            fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            
            setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
    }
    
    public void tareaBpmValidarRAROC(){
        logger.warning("Tarea BPM: VALIDAR RAROC, Modo de ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura
        this.treeVisible = Boolean.TRUE;
        this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA;
        //ocultamos botones de accion del tree sobre el contrato
        this.btnCrearDesembolsoVisible = Boolean.FALSE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
        this.btnDetalleLineaVisible = Boolean.FALSE;
        
        try{
            Map<String,Object> resultMap = new HashMap<String,Object>();
            BindingContainer bindings = getBindings(); 
            logger.log(ADFLogger.WARNING, " Evento de cambio de linea   " + 
                                          idLineaCredito+ " tMoneda " +tipoMoneda );
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
            if(null!=tipoMoneda){
                    operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
                }
            operationBindingLineaCredito.execute();
            resultMap = (Map) operationBindingLineaCredito.getResult();
            //Recuperan montos
            BigDecimal montoTotal1=BigDecimal.ZERO;
            montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
            if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                    montoLinea=montoTotal1.doubleValue();
            }
                        
                        idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                                        : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                          
            idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoTotal");
                        
            montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
            montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
            montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
            montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
            //recuperan fechas
            fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
            fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
            fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            
            setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
    }
    
    public void tareaBpmVAlidarAsignacionRecursosPrea(){
        logger.warning("Tarea BPM: VALIDAR ASIGNACION DE RECURSOS PREA, Modo de ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura
        this.treeVisible = Boolean.TRUE;
        this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA;
        //ocultamos botones de accion del tree sobre el contrato
        this.btnCrearDesembolsoVisible = Boolean.FALSE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
        this.btnDetalleLineaVisible = Boolean.FALSE;
        
        try{
            Map<String,Object> resultMap = new HashMap<String,Object>();
            BindingContainer bindings = getBindings(); 
            logger.log(ADFLogger.WARNING, " Evento de cambio de linea   " + 
                                          idLineaCredito+ " tMoneda " +tipoMoneda );
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
            if(null!=tipoMoneda){
                    operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
                }
            operationBindingLineaCredito.execute();
            resultMap = (Map) operationBindingLineaCredito.getResult();
            //Recuperan montos
            BigDecimal montoTotal1=BigDecimal.ZERO;
            montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
            if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                    montoLinea=montoTotal1.doubleValue();
            }
			
            idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                          
            idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoTotal");
			
            montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
            montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
            montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
            montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
            //recuperan fechas
            fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
            fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
            fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            
            setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
    }
    
    public void tareaBpmValidarDesembolsoExcepcion(){
        logger.warning("Tarea BPM: VALIDAR DESEMBOLSO POR EXCEPCION, Modo de ejecucion: LECTURA");
        idModoEjecucion = 1; //Lectura
        this.treeVisible = Boolean.TRUE;
        this.idEstadoSolicitud = FenixConstants.ESTADO_SOLICITUD_CREADA;
        //ocultamos botones de accion del tree sobre el contrato
        this.btnCrearDesembolsoVisible = Boolean.FALSE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.FALSE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
    }
    
    public void configurarBotonesTareaBpmRealizarAjustesAsignacionRecursos(){
        logger.warning("Tarea BPM: REALIZAR AJUSTES DE ASIGNACION DE RECURSOS");
        this.btnCrearDesembolsoVisible = Boolean.TRUE;
        this.btnIniciarCumplimientoCondicionesVisible = Boolean.FALSE;
        this.btnDesestimarDesembolsoVisible = Boolean.TRUE;
        this.btnDesestimarSollicitudVisible = Boolean.FALSE;
        this.btnDetalleLineaVisible = Boolean.FALSE;
        
        try{
            Map<String,Object> resultMap = new HashMap<String,Object>();
            BindingContainer bindings = getBindings(); 
            logger.log(ADFLogger.WARNING, " Evento de cambio de linea   " + 
                                          idLineaCredito+ " tMoneda " +tipoMoneda );
            OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCreditoByIdLineaCredito");
            operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
            if(null!=tipoMoneda){
                    operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tipoMoneda);
                }
            operationBindingLineaCredito.execute();
            resultMap = (Map) operationBindingLineaCredito.getResult();
            //Recuperan montos
            BigDecimal montoTotal1=BigDecimal.ZERO;
            montoTotal1= resultMap.get("idMontoTotal") != null ? (BigDecimal)resultMap.get("idMontoTotal") : BigDecimal.ZERO;
            if(montoTotal1.compareTo(BigDecimal.ZERO)!=0){
                    montoLinea=montoTotal1.doubleValue();
            }
            
            idTipoMonedaLinea  = (resultMap.get("idTipoMonedaMontoLinea") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoLinea");
                          
            idTipoMonedaTotal  = (resultMap.get("idTipoMonedaMontoTotal") == null) ? null
                            : (Integer)resultMap.get("idTipoMonedaMontoTotal");
			
            montoDisponible = resultMap.get("DISPONIBLE") != null ? (BigDecimal)resultMap.get("DISPONIBLE") : null;
            montoTransito = resultMap.get("TRANSITO") != null ? (BigDecimal)resultMap.get("TRANSITO") : null;
            montoDisponibleDesembolso = resultMap.get("DISPONIBLE_DESEMBOLSO") != null ? (BigDecimal)resultMap.get("DISPONIBLE_DESEMBOLSO") : null;
            montoProgramado = resultMap.get("PROGRAMADO") != null ? (BigDecimal)resultMap.get("PROGRAMADO") : null;
            //recuperan fechas
            fecharegistro = resultMap.get("FECHAREGISTRO") != null ? (java.sql.Timestamp)resultMap.get("FECHAREGISTRO") : null;
            fechaVencimiento = resultMap.get("FECHAVENCIMIENTO") != null ? (java.sql.Timestamp)resultMap.get("FECHAVENCIMIENTO") : null;
            fechaMaximaDesembolso = resultMap.get("FECHAMAXIMADESEMBOLSO") != null ? (java.sql.Timestamp)resultMap.get("FECHAMAXIMADESEMBOLSO") : null;
            
            setEsVisibleDatosLineaCredito(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito", e);                
        }
    }

    public void onChangeListener(RangeChangeEvent rangeChangeEvent) {
        logger.log(ADFLogger.WARNING, "---------------------NEW-------------------------");
        logger.log(ADFLogger.WARNING, "Inside onChangeListener");
        Long idContratoSeleccionado = null;
        
        RichTreeTable treeTable = this.getTreeTable1();
        RowKeySet rks = treeTable.getSelectedRowKeys();        
        Iterator keys = rks.iterator();
        while(keys.hasNext()){
          List key = (List) keys.next();
          treeTable.setRowKey(key);
          JUCtrlHierNodeBinding node = (JUCtrlHierNodeBinding) treeTable.getRowData();
          Row rw = node.getRow();         
          idContratoSeleccionado = (Long) rw.getAttribute("Id"); 
            //desestimarContrato(idContratoSeleccionado);
            //verTcaEstadoContratosRegDesem();

            //Vamos por los Datos de la linea (*Servicio)    
            BindingContainer bindings = getBindings();
            try{
                logger.log(ADFLogger.WARNING, " Evento de cambio de linea   " + 
                                              idLineaCredito + " tMoneda " + tMoneda );
                OperationBinding operationBindingLineaCredito = bindings.getOperationBinding("consultarLineaCredito");
                operationBindingLineaCredito.getParamsMap().put("idLineaCredito", idLineaCredito);
                operationBindingLineaCredito.getParamsMap().put("tipoMoneda", tMoneda);
                operationBindingLineaCredito.execute();
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error al llamar al servicio ConsultarLineaCredito" 
                                            + e.getClass() + " : " + e.getMessage());
            }            
        }  
    }
    
 
    
    /**************---------- Accesors ------------- ********************/
    
    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setTMoneda(String tMoneda) {
        this.tMoneda = tMoneda;
    }

    public String getTMoneda() {
        return tMoneda;
    }

    public void setTipoMoneda(Integer tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Integer getTipoMoneda() {
        return tipoMoneda;
    } 

    public void setOperacionSolicitud(String operacionSolicitud) {
        this.operacionSolicitud = operacionSolicitud;
    }

    public String getOperacionSolicitud() {
        return operacionSolicitud;
    }   


    public void setFechaValorFlex(Timestamp fechaValorFlex) {
        this.fechaValorFlex = fechaValorFlex;
    }

    public Timestamp getFechaValorFlex() {
        return fechaValorFlex;
    }

    public static void setLogger(ADFLogger logger) {
        SolicitudDesembolsosBean.logger = logger;
    }

    public static ADFLogger getLogger() {
        return logger;
    }
    
    public void setDesabilitarBtnIniciarCumplimientoCon(Boolean habilitarBtnIniciarCumplimientoCon) {
        this.desabilitarBtnIniciarCumplimientoCon = habilitarBtnIniciarCumplimientoCon;
    }

    public Boolean getDesabilitarBtnIniciarCumplimientoCon() {
        return desabilitarBtnIniciarCumplimientoCon;
    }

    public void setDesabilitarBtnDesestimarDesembolso(Boolean habilitarBtnDesestimarDesembolso) {
        logger.warning("Inf, setando valor a btn deshabilitar desembolso ->"+habilitarBtnDesestimarDesembolso);
        this.desabilitarBtnDesestimarDesembolso = habilitarBtnDesestimarDesembolso;
    }

    public Boolean getDesabilitarBtnDesestimarDesembolso() {
        return desabilitarBtnDesestimarDesembolso;
    }

    public void setBtnCrearDesembolsoVisible(Boolean btnCrearDesembolsoVisible) {
        this.btnCrearDesembolsoVisible = btnCrearDesembolsoVisible;
    }

    public Boolean getBtnCrearDesembolsoVisible() {
        return btnCrearDesembolsoVisible;
    }

    public void setBtnIniciarCumplimientoCondicionesVisible(Boolean btnIniciarCumplimientoCondicionesVisible) {
        this.btnIniciarCumplimientoCondicionesVisible = btnIniciarCumplimientoCondicionesVisible;
    }

    public Boolean getBtnIniciarCumplimientoCondicionesVisible() {
        return btnIniciarCumplimientoCondicionesVisible;
    }

    public void setBtnDesestimarDesembolsoVisible(Boolean btnDesestimarDesembolsoVisible) {
        logger.warning("setBtnDesestimarDesembolsoVisible: "+btnDesestimarDesembolsoVisible);
        this.btnDesestimarDesembolsoVisible = btnDesestimarDesembolsoVisible;
    }

    public Boolean getBtnDesestimarDesembolsoVisible() {
        logger.warning("getBtnDesestimarDesembolsoVisible: "+btnDesestimarDesembolsoVisible);
        return btnDesestimarDesembolsoVisible;
    }

    public void setBtnDesestimarSollicitudVisible(Boolean btnDesestimarSollicitudVisible) {
        this.btnDesestimarSollicitudVisible = btnDesestimarSollicitudVisible;
    }

    public Boolean getBtnDesestimarSollicitudVisible() {
        return btnDesestimarSollicitudVisible;
    }    

    public void setIdContratoDesembolso(Long idContratoDesembolso) {
        this.idContratoDesembolso = idContratoDesembolso;
    }
    
    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public Long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }
    public Long getIdContrato() {
        return idContrato;
    }
    public void setFechaVigenciaLiena(Date fechaVigenciaLiena) {
        this.fechaVigenciaLiena = fechaVigenciaLiena;
    }
    public Date getFechaVigenciaLiena() {
        return fechaVigenciaLiena;
    }
    public void setIdLineaCredito(Long idLineaCredito) {
        this.idLineaCredito = idLineaCredito;
    }

    public Long getIdLineaCredito() {
        return idLineaCredito;
    }
    public void setTreeTable1(RichTreeTable treeTable1) {
        this.treeTable1 = treeTable1;
    }

    public RichTreeTable getTreeTable1() {
        return treeTable1;
    }

    public void prueba(ActionEvent actionEvent) {
        // Add event code here...
    }
    public void setNumeroLineaCredito(String numeroLineaCredito) {
        this.numeroLineaCredito = numeroLineaCredito;
    }

    public String getNumeroLineaCredito() {
        return numeroLineaCredito;
    }
    public void setIdEstadoSolicitud(Integer idEstadoSolicitud) {
        this.idEstadoSolicitud = idEstadoSolicitud;
    }

    public Integer getIdEstadoSolicitud() {
        return idEstadoSolicitud;
    }

    public void setEsVisible(Boolean esVisible) {
        this.esVisible = esVisible;
    }
    public void getEstado(AttributeChangeEvent attributeChangeEvent) {
    }
    public Boolean getEsVisible() {
        return esVisible;
    }
    public void setTreeVisible(Boolean treeVisible) {
        this.treeVisible = treeVisible;
    }

    public Boolean getTreeVisible() {
        return treeVisible;
    }
    
    public void setContratoVisible(Boolean contratoVisible) {
        this.contratoVisible = contratoVisible;
    }

    public Boolean getContratoVisible() {
        return contratoVisible;
    }
    
    public void setModoEjecucionTabTransferencias(Integer modoEjecucionTabTransferencias) {
        this.modoEjecucionTabTransferencias = modoEjecucionTabTransferencias;
    }

    public Integer getModoEjecucionTabTransferencias() {
        return modoEjecucionTabTransferencias;
    }
    public void setIdContratoDes(Long idContratoDes) {
        this.idContratoDes = idContratoDes;
    }

    public Long getIdContratoDes() {
        return idContratoDes;
    }
    public void setTieneTipoMoneda(Boolean tieneTipoMoneda) {
        this.tieneTipoMoneda = tieneTipoMoneda;
    }

    public Boolean getTieneTipoMoneda() {
        return tieneTipoMoneda;
    }
    
    public void setMontoDesembolso(Long montoDesembolso) {
        this.montoDesembolso = montoDesembolso;
    }
    
    public void setEsCumplimientoCondicion(Boolean esCumplimientoCondicion) {
        this.esCumplimientoCondicion = esCumplimientoCondicion;
    }

    public Boolean getEsCumplimientoCondicion() {
        return esCumplimientoCondicion;
    }

    public Long getMontoDesembolso() {
        return montoDesembolso;
    }
    public void setMontoTotal(Long montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Long getMontoTotal() {
        return montoTotal;
    }

    public void setTreeDatosSolicitud(Boolean treeDatosSolicitud) {
        this.treeDatosSolicitud = treeDatosSolicitud;
    }

    public Boolean getTreeDatosSolicitud() {
        return treeDatosSolicitud;
    }
       
    public void setIdCodigoExterno(String idCodigoExterno) {
        this.idCodigoExterno = idCodigoExterno;
    }

    public String getIdCodigoExterno() {
        return idCodigoExterno;
    }

    public void setIdTareaBPM(Integer idTareaBPM) {
        this.idTareaBPM = idTareaBPM;
    }

    public Integer getIdTareaBPM() {
        return idTareaBPM;
    }

    public void setIdModoEjecucion(Integer idModoEjecucion) {
        this.idModoEjecucion = idModoEjecucion;
    }

    public Integer getIdModoEjecucion() {
        return idModoEjecucion;
    }

    public void setIdContratoDefault(Long idContratoDefault) {
        this.idContratoDefault = idContratoDefault;
    }

    public Long getIdContratoDefault() {
        return idContratoDefault;
    }

    public void setIdInstanciaTareaBPM(String idInstanciaTareaBPM) {
        this.idInstanciaTareaBPM = idInstanciaTareaBPM;
    }

    public String getIdInstanciaTareaBPM() {
        return idInstanciaTareaBPM;
    }

    public void setIdProcesoBPM(Integer idProcesoBPM) {
        this.idProcesoBPM = idProcesoBPM;
    }

    public Integer getIdProcesoBPM() {
        return idProcesoBPM;
    }

    public void setMontoTotalSolicitud(BigDecimal montoTotalSolicitud) {
        this.montoTotalSolicitud = montoTotalSolicitud;
    }

    public BigDecimal getMontoTotalSolicitud() {
        return montoTotalSolicitud;
    }

    public void setIdContratoSeleccionado(Long idContratoSeleccionado) {
        this.idContratoSeleccionado = idContratoSeleccionado;
    }

    public Long getIdContratoSeleccionado() {
        return idContratoSeleccionado;
    }
    
    public void setFechaSolicitudLectura(Boolean fechaSolicitudLectura) {
        this.fechaSolicitudLectura = fechaSolicitudLectura;
    }

    public Boolean getFechaSolicitudLectura() {
        return fechaSolicitudLectura;
    }


    public Boolean getEsVisibleDatosLineaCredito() {
        return esVisibleDatosLineaCredito;
    }

    public void setEsVisibleDatosLineaCredito(Boolean esVisibleDatosLineaCredito) {
        this.esVisibleDatosLineaCredito = esVisibleDatosLineaCredito;
    }


    public BigDecimal getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoDisponible(BigDecimal montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    public BigDecimal getMontoTransito() {
        return montoTransito;
    }

    public void setMontoTransito(BigDecimal montoTransito) {
        this.montoTransito = montoTransito;
    }

    public BigDecimal getMontoDisponibleDesembolso() {
        return montoDisponibleDesembolso;
    }

    public void setMontoDisponibleDesembolso(BigDecimal montoDisponibleDesembolso) {
        this.montoDisponibleDesembolso = montoDisponibleDesembolso;
    }

    public BigDecimal getMontoProgramado() {
        return montoProgramado;
    }

    public void setMontoProgramado(BigDecimal montoProgramado) {
        this.montoProgramado = montoProgramado;
    }

    public java.sql.Timestamp getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(java.sql.Timestamp fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public java.sql.Timestamp getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(java.sql.Timestamp fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public java.sql.Timestamp getFechaMaximaDesembolso() {
        return fechaMaximaDesembolso;
    }

    public void setFechaMaximaDesembolso(java.sql.Timestamp fechaMaximaDesembolso) {
        this.fechaMaximaDesembolso = fechaMaximaDesembolso;
    }
	
    public Integer getIdTipoMonedaLinea() {
        return idTipoMonedaLinea;
    }

    public void setIdTipoMonedaLinea(Integer idTipoMonedaLinea) {
        this.idTipoMonedaLinea = idTipoMonedaLinea;
    }
    
    public Integer getIdTipoMonedaTotal() {
        return idTipoMonedaTotal;
    }

    public void setdTipoMonedaTotal(Integer idTipoMonedaTotal) {
        this.idTipoMonedaTotal = idTipoMonedaTotal;
    }

    public Double getMontoLinea() {
        return montoLinea;
    }

    public void setMontoLinea(Double montoLinea) {
        this.montoLinea = montoLinea;
    }

    public void setFechaVencimientoService(Date fechaVencimientoService) {
        this.fechaVencimientoService = fechaVencimientoService;
    }

    public Date getFechaVencimientoService() {
        return fechaVencimientoService;
    }

    public void setFechaMaximaService(Date fechaMaximaService) {
        this.fechaMaximaService = fechaMaximaService;
    }

    public Date getFechaMaximaService() {
        return fechaMaximaService;
    }

    public void setMontoDisponibleService(BigDecimal montoDisponibleService) {
        this.montoDisponibleService = montoDisponibleService;
    }

    public BigDecimal getMontoDisponibleService() {
        return montoDisponibleService;
    }
    
    public void setPInstanciaProceso(String pInstanciaProceso) {
        this.instanciaProceso = pInstanciaProceso;
    }

    public String getPInstanciaProceso() {
        return instanciaProceso;
    }
    
    public void setDesHabilitarBtnDetalleLinea(Boolean desHabilitarBtnDetalleLinea) {
        this.desHabilitarBtnDetalleLinea = desHabilitarBtnDetalleLinea;
    }
    
    public void setIdLineaCreditoSeleccionada(Long idLineaCreditoSeleccionada) {
        this.idLineaCreditoSeleccionada = idLineaCreditoSeleccionada;
    }

    public Boolean getDesHabilitarBtnDetalleLinea() {
        return desHabilitarBtnDetalleLinea;
    }

    public Long getIdLineaCreditoSeleccionada() {
        return idLineaCreditoSeleccionada;
    }
    
    public void setFondoLinea(String fondoLinea) {
        this.fondoLinea = fondoLinea;
    }

    public String getFondoLinea() {
        return fondoLinea;
    }

    public void setActividadEconomica(String actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    public String getActividadEconomica() {
        return actividadEconomica;
    }

    public void setEjeEstrategico(String ejeEstrategico) {
        this.ejeEstrategico = ejeEstrategico;
    }

    public String getEjeEstrategico() {
        return ejeEstrategico;
    }

    public void setAreaFocalizacion(String areaFocalizacion) {
        this.areaFocalizacion = areaFocalizacion;
    }

    public String getAreaFocalizacion() {
        return areaFocalizacion;
    }
    
    public void setDescripcionLinea(String descripcionLinea) {
        this.descripcionLinea = descripcionLinea;
    }

    public String getDescripcionLinea() {
        return descripcionLinea;
    }
    
    public void setNumeroLinea(String numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public String getNumeroLinea() {
        return numeroLinea;
    }
    
    public void setMontoAprobado(BigDecimal montoAprobado) {
        this.montoAprobado = montoAprobado;
    }

    public BigDecimal getMontoAprobado() {
        return montoAprobado;
    }
    
    public void setBtnDetalleLineaVisible(Boolean btnDetalleLineaVisible) {
        this.btnDetalleLineaVisible = btnDetalleLineaVisible;
    }

    public Boolean getBtnDetalleLineaVisible() {
        return btnDetalleLineaVisible;
    }
    
    public void actualizarMonedaSolicitud(Long idOperacion, Long idSolicitud) {
        BindingContainer bindings = getBindings();

        try {
            OperationBinding operationBinding = bindings.getOperationBinding("validarActualizarMoneda");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.execute();

        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error al validarActualizarMoneda", e);
        }
    }

    public boolean isBtnDesestimarSolicitudDeshabilitar() {
        return btnDesestimarSolicitudDeshabilitar;
    }

    public void setBtnDesestimarSolicitudDeshabilitar(boolean btnDesestimarSolicitudDeshabilitar) {
        this.btnDesestimarSolicitudDeshabilitar = btnDesestimarSolicitudDeshabilitar;
    }
    

    public void setNumeroLineaCreditoRespOut(String numeroLineaCreditoRespOut) {
        this.numeroLineaCreditoRespOut = numeroLineaCreditoRespOut;
    }

    public String getNumeroLineaCreditoRespOut() {
        return numeroLineaCreditoRespOut;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setDescripcionLineaString(String descripcionLineaString) {
        this.descripcionLineaString = descripcionLineaString;
    }

    public String getDescripcionLineaString() {
        return descripcionLineaString;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMontoAprobadoString(String montoAprobadoString) {
        this.montoAprobadoString = montoAprobadoString;
    }

    public String getMontoAprobadoString() {
        return montoAprobadoString;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setRevolvencia(String revolvencia) {
        this.revolvencia = revolvencia;
    }

    public String getRevolvencia() {
        return revolvencia;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public String getFondo() {
        return fondo;
    }

    public void setLineaFinanciera(String lineaFinanciera) {
        this.lineaFinanciera = lineaFinanciera;
    }

    public String getLineaFinanciera() {
        return lineaFinanciera;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setActividadEconomicaString(String actividadEconomicaString) {
        this.actividadEconomicaString = actividadEconomicaString;
    }

    public String getActividadEconomicaString() {
        return actividadEconomicaString;
    }

    public void setSectorInstitucional(String sectorInstitucional) {
        this.sectorInstitucional = sectorInstitucional;
    }

    public String getSectorInstitucional() {
        return sectorInstitucional;
    }

    public void setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
    }

    public String getEjecutivo() {
        return ejecutivo;
    }

    public void setEjeEstrategicoString(String ejeEstrategicoString) {
        this.ejeEstrategicoString = ejeEstrategicoString;
    }

    public String getEjeEstrategicoString() {
        return ejeEstrategicoString;
    }

    public void setAreaFocalizacionString(String areaFocalizacionString) {
        this.areaFocalizacionString = areaFocalizacionString;
    }

    public String getAreaFocalizacionString() {
        return areaFocalizacionString;
    }

    public void setObjeticoEstrategico(String objeticoEstrategico) {
        this.objeticoEstrategico = objeticoEstrategico;
    }

    public String getObjeticoEstrategico() {
        return objeticoEstrategico;
    }

    public void setPlantillaLimite(String plantillaLimite) {
        this.plantillaLimite = plantillaLimite;
    }

    public String getPlantillaLimite() {
        return plantillaLimite;
    }

    public void setSerialLimite(String serialLimite) {
        this.serialLimite = serialLimite;
    }

    public String getSerialLimite() {
        return serialLimite;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidadIfacil(String disponibilidadIfacil) {
        this.disponibilidadIfacil = disponibilidadIfacil;
    }

    public String getDisponibilidadIfacil() {
        return disponibilidadIfacil;
    }

    public void setTieneCondEspeciales(String tieneCondEspeciales) {
        this.tieneCondEspeciales = tieneCondEspeciales;
    }

    public String getTieneCondEspeciales() {
        return tieneCondEspeciales;
    }

    public void setDescCondEspeciales(String descCondEspeciales) {
        this.descCondEspeciales = descCondEspeciales;
    }

    public String getDescCondEspeciales() {
        return descCondEspeciales;
    }

    public void setFechaMaxDesembolsar(String fechaMaxDesembolsar) {
        this.fechaMaxDesembolsar = fechaMaxDesembolsar;
    }

    public String getFechaMaxDesembolsar() {
        return fechaMaxDesembolsar;
    }

    public void setNumeroDesembolsos(String numeroDesembolsos) {
        this.numeroDesembolsos = numeroDesembolsos;
    }

    public String getNumeroDesembolsos() {
        return numeroDesembolsos;
    }
    
    public void setBtnRenderDesestimarDesembolso(boolean btnRenderDesestimarDesembolso) {
        this.btnRenderDesestimarDesembolso = btnRenderDesestimarDesembolso;
    }

    public boolean isBtnRenderDesestimarDesembolso() {
        return btnRenderDesestimarDesembolso;
    }

    public void setListaCondiciones(List<CondicionDesembolso> listaCondiciones) {
        this.listaCondiciones = listaCondiciones;
    }

    public List<CondicionDesembolso> getListaCondiciones() {
        if(this.listaCondiciones==null){
            this.listaCondiciones= new ArrayList<CondicionDesembolso>();
        }
        return listaCondiciones;
    } 

    public void setRequiereValidacionAsignacion(Boolean requiereValidacionAsignacion) {
        this.requiereValidacionAsignacion = requiereValidacionAsignacion;
    }

    public Boolean getRequiereValidacionAsignacion() {
        return requiereValidacionAsignacion;
    }

    public void setSoloCondicionesCalendario(Boolean soloCondicionesCalendario) {
        this.soloCondicionesCalendario = soloCondicionesCalendario;
    }

    public Boolean getSoloCondicionesCalendario() {
        return soloCondicionesCalendario;
    }
}
