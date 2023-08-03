package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import oracle.adf.controller.TaskFlowId;
import oracle.adf.controller.binding.TaskFlowBindingAttributes;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.Date;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class ContratoDesembolsosBean extends FenixActionBean implements Serializable {
    @SuppressWarnings("compatibility:916001930303274317")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    private RichPanelGroupLayout pglRefrescarErrorValidarAsignacion;

    public ContratoDesembolsosBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
        
        tabAsignacionRecursos = false;
        tabDatosGenerales = false;
        tabCondicionesFinancieras = false;
        tabCargos = false;
        tabTransferencias = false;
        tabLiquidarContrato = false;
        tabConsolidarTransferencias = false;
        tabReservaFondos = false;
        setTabCoberturas(false);
    }
    
    private Integer idTipoMoneda;
    private String idFondoLinea;
    private String descripcionFondo;
    private Integer idEstadoContrato;
    private Boolean esCampoSoloLectura;
    private Boolean mostrarBotones;
    private Boolean mostrarBotonGuardarTabs;
    private Boolean deshabilitarBotones;
    private Boolean mostrarTabsContrato;
    private Date fechaEstimadaDesembolsar;
    private BigDecimal montoDisponibleLineaCredito;
    private String cuentaConNoObjecion;
    private Integer scrCliente;
    private String moraCliente;
    private Long sectorCliente;
    private BigDecimal montoMaximoDesembolso;
    private BigDecimal montoMinimoDesembolso;
    private BigDecimal tasaMaximaDesembolso;
    private BigDecimal tasaMinimaDesembolso;
    private Boolean esMonedaSoloLectura;
    private BigDecimal montoConvertido;
    private Integer idTareaBPMTF;
    private Long idSolicitudTF;
    private Long idOperacionTF;
    private Boolean mostrarFormularioDesembolsos;
    private Boolean mostrarFormularioEnvioCobro;
    private Long idContratoDesembolso;
    private Long idLinea;
    private String errorServicioValidarAsgnacion;
    private Boolean esLecturaFechaEfectivaEnvioCobro;
    private Boolean esIniciarProcesoDesembolso;
    private Boolean fechaEstimadaDesemReadOnly = Boolean.FALSE;

    // Tabs Render
    private Integer renderTabAsignacionRecursos;
    private Integer renderTabDatosGenerales;
    private Integer renderTabCondicionesFinancieras;
    private Integer renderTabCargos;
    private Integer renderTabTransferencias;
    private Integer renderTabLiquidarContrato;
    private Integer renderTabConsolidarTransferencias;
    private Integer renderTabReservaFondos;
    private Boolean renderTabCoberturas;

    private Boolean deshabilitarGuardarBtn;
    private Boolean deshabilitarProcesoBtn;
    private Boolean renderBtnProceso;
    private Boolean readOnlyFondo;
    
    //Modo Ejecución
    private Integer modoEjecucionTabAsignacionRecursos;
    private Integer modoEjecucionTabDatosGenerales;
    private Integer modoEjecucionTabTransferencias;
    private Integer modoEjecucionTabConsolidacionTransferencias;
    private Integer modoEjecucionTabCargos;
    private Integer modoEjecucionTabCondicionesFinancieras;
    private Integer modoEjecucionTabReservaFondos;
    private Integer modoEjecucionTabLiquidarContrato;

    //Tabs Disclosed
    private Boolean disclosedTabAsignacionRecursos;
    private Boolean disclosedTabDatosGenerales;
    private Boolean disclosedTabCondicionesFinancieras;
    private Boolean disclosedTabCargos;
    private Boolean disclosedTabTransferencias;
    private Boolean disclosedTabLiquidarContrato;
    private Boolean disclosedTabConsolidarTransferencias;
    private Boolean disclosedTabReservaFondos;
    private Boolean disclosedTabCoberturas;

    // Tabs
    private Boolean tabAsignacionRecursos;
    private Boolean tabDatosGenerales;
    private Boolean tabCondicionesFinancieras;
    private Boolean tabCargos;
    private Boolean tabTransferencias;
    private Boolean tabLiquidarContrato;
    private Boolean tabConsolidarTransferencias;
    private Boolean tabReservaFondos;
    private Boolean tabCoberturas;

    private Integer valorAnteriorTab;
    private Boolean guardaTab;
    private Boolean esErrorMontoLimite;
    private Boolean esMontoMaximo;
    private Boolean esMontoMinimo;
    private BigDecimal montoProgramMesVigente; 
    
    private String codigoExterno;
    
    //Variables que se ocupan para indicar el TaskFlowId de las regiones de los tabs.
    //Se indicará el path en caso de que la variable render de los tabs sea 1 o true y
    //un string vacío (no nulo) en caso de que el render de los tabs sea false o 0.
    //IMPORTANTE: Si la variable es NULL al momento de llamar el TF corresponidente, mostrara ADF-FACES.
    private String taskFlowIdAsignRecRoot = "";
    private String taskFlowIdDatosGralesRoot = "";
    private String taskFlowIdCondFinanRoot = "";
    private String taskFlowIdTransfRoot = "";
    private String taskFlowIdCargosRoot = "";
    private String taskFlowIdConsTransfRoot = "";
    private String taskFlowIdResFondosRoot = "";
    private String taskFlowIdLiqContratoRoot = "";
    private String taskFlowIdCoberturasRoot = "";
    
    private String instanciaProceso;
    private String errorRequiereValidacionAsignacion = null;
    private String errorPrecargaContratoDesembolso = null;
    private String esVerificarRegistroDetalle = null;
    private String estatusTareaBpm = "";

    //The list of task flow definitions that is displayed as dynamic regions
    private List<TaskFlowBindingAttributes> multiTaskFlowAsignRecBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowDatosGralesBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowCondFinanBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowCargosBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowTransferBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowReservaFondosBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowLiquidarContBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowCoberturasBindings = new ArrayList<TaskFlowBindingAttributes>();
    private List<TaskFlowBindingAttributes> multiTaskFlowConsTransfBindings = new ArrayList<TaskFlowBindingAttributes>();

    private Boolean cambiosPendientes;
    private Integer instancia1;
    private Integer instancia2;
    public void setCodigoExterno(String codigoExterno) {
           this.codigoExterno = codigoExterno;
       }

       public String getCodigoExterno() {
           return codigoExterno;
       }

    private List<String> listaErrores = new ArrayList<String>();
    List<java.util.Date> diasInhabiles = new ArrayList<java.util.Date>();
    
    private Integer idTareaNoMostrarMontoProgramado;
    
    public void precargarInformacionContrato(){
        logger.warning("*Inf, Inicia metodo precargarInformacionContrato *");
        Map mapaPrecargaDatosContrato = new HashMap();
        Integer idTipoMoneda = null;
        String idFondo = null;
        String descripcionFondo = null;
        final Integer REALIZAR_AJUSTES_DESEMBOLSO = 151;
        
        logger.warning("*Inf, recuperando datos del TaskFlow *");
        
        Long pIdSolicitudDesembolso = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitudDesembolso}") != null 
            && !JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitudDesembolso}").toString().equals("") ){
                
                try{    
                   pIdSolicitudDesembolso =  new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitudDesembolso}").toString());     
                 }catch(Exception e){
                    logger.warning("***Error, al parsear pIdSolicitudDesembolso ->"+e);
                 }
                
        }else{
            logger.warning("Error al castear parametro pIdSolicitudDesembolso");
        }
       
        
        oracle.jbo.domain.Number pIdLineaCredito = null;
        try {
            pIdLineaCredito =
                new oracle.jbo.domain.Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdLineaCredito}").toString());
        } catch (Exception e) {
            logger.warning("Error al castear parametro pIdLineaCredito");
        }
        
        Long pIdContratoDesembolso = null;
        logger.warning("IdContrato con EL de TF: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}"));
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}")  
            && !JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}").toString().equals("") ){           
                 
                 try{                
                    pIdContratoDesembolso =
                        new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}").toString());
                 }catch(Exception e){
                         logger.warning("***Error, al parsear pIdContratoDesembolso ->"+e);
                     }
        
        }else{
            logger.warning("IdContrato es NULL");
        }
        
        Long pIdOperacion = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null 
           && !JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString().equals("") ){     
            
            try{
                pIdOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            }catch(Exception e){
                 logger.warning("***Error, al parsear pIdOperacion  ->"+e);
            }
            
        }else{
            logger.warning("IdOperacion es NULL");
        }
        
        Integer pIdTarea = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}") != null 
                && !JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}").toString().equals("")){
           
            try{
                 pIdTarea = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}").toString());
               }catch(Exception e){
                    logger.warning("***Error, al parsear pIdTareaBPM ->"+e); 
                }
                        
        }else{
            logger.warning("IdTareaBPM es NULL");
        }
        
        Integer pIdProceso = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdProcesoBPM}") != null 
            && !JSFUtils.resolveExpression("#{pageFlowScope.pIdProcesoBPM}").toString().equals("")){
            
             try{    
                    pIdProceso = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdProcesoBPM}").toString());
                }catch(Exception e){
                     logger.warning("***Error, al parsear pIdProcesoBPM ->"+e); 
                }
        
        }else{
            logger.warning("IdProcesoBPM es NULL");
        }
        
        String pInstanciaTareaBPM = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaTareaBPM}") != null 
            && !JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaTareaBPM}").toString().equals("")){
           
            try{ 
               pInstanciaTareaBPM = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaTareaBPM}").toString();
            }catch(Exception e){
                logger.warning("***Error, al parsear pInstanciaTareaBPM ->"+e);   
            }
        
        }else{
            logger.warning("pInstanciaTareaBPM es NULL");
        }            
        
        String pInstanciaDProceso = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") != null){   
            
            try{
               pInstanciaDProceso = (String) JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}");
            }catch(Exception e){
                logger.warning("***Error, al parsear pInstanciaProceso ->"+e);   
            }
        
        }else{
            logger.warning("pInstanciaProceso es NULL");
        }
        
        if(JSFUtils.resolveExpression("#{pageFlowScope.pCodigoExterno}") != null 
            && !JSFUtils.resolveExpression("#{pageFlowScope.pCodigoExterno}").toString().equals("") ){
            
                codigoExterno = JSFUtils.resolveExpression("#{pageFlowScope.pCodigoExterno}").toString();

        }else{
            logger.warning("pageFlowScope.pCodigoExterno es NULL");
        }
                
            
        try{            
            estatusTareaBpm = (null == JSFUtils.resolveExpression("#{pageFlowScope.estatusTareaBpm}"))? ""
                            : JSFUtils.resolveExpression("#{pageFlowScope.estatusTareaBpm}").toString();            
        }catch(Exception e){
            logger.warning("ha ocurrido un Error al recuperar el estado de la tarea->", e);
        }                    
            
            
                
        logger.warning("IdSolicitud de TaskFlow: " + pIdSolicitudDesembolso);
        logger.warning("codigo externo producto de TaskFlow: " + codigoExterno);
        logger.warning("IdLineaCredito de TaskFlow: " + pIdLineaCredito);
        logger.warning("IdContrato de TaskFlow: " + pIdContratoDesembolso);
        logger.warning("IdOperacion de TaskFlow: " + pIdOperacion);
        logger.warning("IdTareaBPM de TaskFlow: " + pIdTarea);
        logger.warning("estatusTareaBpm de TaskFlow: " + estatusTareaBpm);
        logger.warning("*>pInstanciaDProceso de TaskFlow: " + pInstanciaDProceso);
        logger.warning("pInstanciaTareaBPM de TaskFlow: " + pInstanciaTareaBPM);
        setIdSolicitudTF(pIdSolicitudDesembolso);
        setIdOperacionTF(pIdOperacion);
      
      
        if(null!=pIdLineaCredito){
            setIdLinea(pIdLineaCredito.longValue());
        }
        
        if(null!=pIdContratoDesembolso){
                setIdContratoDesembolso(pIdContratoDesembolso);
        }

        if(null!=pInstanciaDProceso){
            instanciaProceso=pInstanciaDProceso;
        }
        
        setIdTareaBPMTF(pIdTarea);
        if(null != pIdContratoDesembolso){
            
            //montoProgramadoMesVig(pIdContratoDesembolso); // recuperamos y seteamos el montoprogramado para el mes vigente
            double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
            TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
            try{            
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("precargarContratoPorIdContrato");
                operationBinding.getParamsMap().put("idContrato", pIdContratoDesembolso);
                operationBinding.getParamsMap().put("idLineaCredito", pIdLineaCredito);
                operationBinding.getParamsMap().put("idOperacion", pIdOperacion);
                operationBinding.getParamsMap().put("idTarea", pIdTarea);
                operationBinding.getParamsMap().put("idSolicitud", pIdSolicitudDesembolso);
                
                logger.warning("Ejecutando OperationBinding de precargaInformacionContrato");
                mapaPrecargaDatosContrato = (Map) operationBinding.execute();
                
                if(operationBinding.getErrors().isEmpty()){
                    logger.warning("El operationBinding se ejecuto correctamente.");
                    Integer idEstadoContrato = null;
                    
                    try{
                        idEstadoContrato = (Integer) ADFUtils.getBoundAttributeValue("IdTcaEstadoAttr");
                    }catch(Exception e){
                        logger.warning("ERROR al recuperar el estado del contrato.", e);
                    }
                    
                    if(null != idEstadoContrato){
                        setIdEstadoContrato(idEstadoContrato);
                    }else{
                        logger.warning("idEstadoContrato es NULL.");
                    }
                    
                    if(null != mapaPrecargaDatosContrato){
                        BigDecimal montoDisponibleMapa = null;
                        String noObjecion = null;
                        Integer scr = null;
                        String mora = null;
                        Long sector = null;
                        BigDecimal montoMaximo = null;
                        BigDecimal montoMinimo = null;
                        BigDecimal tasaMaxima = null;
                        BigDecimal tasaMinima = null;
                        Boolean monedaSoloLectura = null;
                        String monedaLectura = null;                        
                        
                        try{
                            logger.warning("Monto disponible: " + mapaPrecargaDatosContrato.get("montoDisponible"));
                            montoDisponibleMapa = (BigDecimal) mapaPrecargaDatosContrato.get("montoDisponible");
                        }catch(Exception e){
                            logger.warning("Error al obtener MontoDisponible de la linea de credito: ", e);
                        }
                        
                        try{
                            logger.warning("Monto maximo: " + mapaPrecargaDatosContrato.get("montoMaximo"));
                            montoMaximo = (BigDecimal) mapaPrecargaDatosContrato.get("montoMaximo");
                        }catch(Exception e){
                            logger.warning("Error al obtener Monto Maximo: ", e);
                        }
                        
                        try{
                            logger.warning("Monto minimo: " + mapaPrecargaDatosContrato.get("montoMinimo"));
                            montoMinimo = (BigDecimal) mapaPrecargaDatosContrato.get("montoMinimo");
                        }catch(Exception e){
                            logger.warning("Error al obtener Monto Minimo: ", e);
                        }
                        
                        try{
                            logger.warning("Tasa maxima: " + mapaPrecargaDatosContrato.get("tasaMaxima"));
                            tasaMaxima = (BigDecimal) mapaPrecargaDatosContrato.get("tasaMaxima");
                        }catch(Exception e){
                            logger.warning("Error al obtener Tasa Maxima: ", e);
                        }
                        
                        try{
                            logger.warning("Tasa minima: " + mapaPrecargaDatosContrato.get("tasaMinima"));
                            tasaMinima = (BigDecimal) mapaPrecargaDatosContrato.get("tasaMinima");
                        }catch(Exception e){
                            logger.warning("Error al obtener Tasa Minima: ", e);
                        }
                        
                        try{                            
                            logger.warning("EsMonedaSoloLectura: " + mapaPrecargaDatosContrato.get("monedaSoloLectura"));
                            monedaSoloLectura = (Boolean) mapaPrecargaDatosContrato.get("monedaSoloLectura");
                        }catch(Exception e){
                            logger.warning("Error al obtener EsMonedaSoloLectura: ", e);
                        }
                        
                        setMontoDisponibleLineaCredito(montoDisponibleMapa);
                        setScrCliente(scr);
                        setMoraCliente(mora);
                        setCuentaConNoObjecion(noObjecion);
                        setSectorCliente(sector);
                        setMontoMaximoDesembolso(montoMaximo);
                        setMontoMinimoDesembolso(montoMinimo);
                        setTasaMaximaDesembolso(tasaMaxima);
                        setTasaMinimaDesembolso(tasaMinima);
                        
                        if(pIdTarea != null && pIdTarea.compareTo(REALIZAR_AJUSTES_DESEMBOLSO) == 0 ){
                            setEsMonedaSoloLectura(Boolean.TRUE);
                            logger.warning("ok tarea realizar ajuste monera read only moneda true");
                        }else{                        
                          setEsMonedaSoloLectura(monedaSoloLectura);
                        }
                        
                        logger.warning("var EsMonedaSoloLectura: "+getEsMonedaSoloLectura());
                        
                        try{
                            montoProgramMesVigente =
                                (BigDecimal) ADFUtils.getBoundAttributeValue("MontoProgramadoMesVigente");
                        }catch(Exception e){
                            logger.warning("ERROR al recuperar el Monto programado para mes vigente.", e);
                        }
                    }else{
                        logger.warning("Mapa de precarga de datos del contrato es NULL.");
                    }
                    errorPrecargaContratoDesembolso = null;
                }else{
                    logger.warning("OperationBindings regresa con errores." + operationBinding.getErrors().toString());    
                    errorPrecargaContratoDesembolso = "ERROR al precargar informacion de contrato: " + operationBinding.getErrors().toString();
                }
            }catch(Exception e){
                logger.warning("Ocurrio un error al ejecutar el OperationBinding: ", e);
            }
                        
            //obtenerFechaEstimadaDisponibilidadRecursos();
            
            TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
            tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia
            logger.warning("termina el metodo precargarContratoPorIdContrato con una duracion de: "+tiempo+" segundos");
        }else{
            /* try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("precargaInformacionContrato");
                operationBinding.getParamsMap().put("idLineaCredito", pIdLineaCredito);
                operationBinding.getParamsMap().put("idOperacion", pIdOperacion);
                logger.warning("Ejecutando OperationBinding de precargaInformacionContrato");
                mapaPrecargaDatosContrato = (Map) operationBinding.execute();
                
                if(operationBinding.getErrors().isEmpty()){
                    logger.warning("OperationBinding ejecutado correctamente. Recuperando datos.");
                    if(null != mapaPrecargaDatosContrato){
                        idTipoMoneda = (Integer) mapaPrecargaDatosContrato.get("idTipoMoneda");
                        idFondo = (String) mapaPrecargaDatosContrato.get("idFondo");
                        descripcionFondo = (String) mapaPrecargaDatosContrato.get("descripcionFondo");
                        
                        if(null != idTipoMoneda){
                            logger.warning("idTipoMoneda obtenido: " + idTipoMoneda);
                            setIdTipoMoneda(idTipoMoneda);
                        }else{
                            logger.warning("IdTipoMoneda es NULL");
                        }
                        
                        if(null != idFondo){
                            logger.warning("idFondo obtenido: " + idFondo);
                            setIdFondoLinea(idFondo);
                        }else{
                            logger.warning("idFondo es NULL");
                        }
                        
                        if(null != descripcionFondo){
                            logger.warning("descripcionFondo obtenido: " + descripcionFondo);
                            setDescripcionFondo(descripcionFondo);
                        }else{
                            logger.warning("descripcionFondo es NULL");
                        }
                        
                    }else{
                        logger.warning("Parametros de inicializacion de contratos no obtenidos");
                    }
                }else{
                    logger.warning("OperationBindings regresa con errores.");
                }
            }catch(Exception e){
                logger.warning("Ocurrio un error al ejecutar el OperationBinding: ", e);
            } */
        }
        
        configurarFormularioContrato();
        
        logger.warning("*Inf, Estatus de tarea: "+estatusTareaBpm);
        
        //Esta bandera nos indica si la tarea BPM ya finalizo o sige activa
        if(estatusTareaBpm.equalsIgnoreCase("COMPLETED")){ //  COMPLETED - ASSIGNED
            logger.warning("*Inf, Configurando datos contrato Modo Lectura");
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);        
                setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
        }
        
        
        if(null != getIdTareaBPMTF() && !getIdTareaBPMTF().equals(FenixConstants.CG10_GESTOR_DESEMBOLSOS)){
            logger.warning("Tarea diferente de Gestor de desembolsos. Se ejecutara metodo para llenar DetalleDesembolsosOperacionVO.");
            if(null != getIdContratoDesembolso()){
                logger.warning("Se ejecuta metodo para ConulstarOperacionByIdOperacion.");
                try{
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("idOperacion", getIdOperacionTF());
                    execute(params, "consultarOperacionByIdOperacion");
                }catch(Exception e){
                    logger.warning("ERROR al ejecutar operBinding consultarOperacionByIdOperacion.", e);
                }
            }else{
                logger.warning("No hay contrato seleccionado, no se ejecuta metodo para ConulstarOperacionByIdOperacion.");
            }
                
        }
        
        asignarBHQClienteAContrato();
        
        logger.warning("Termina metodo precargarInformacionContrato");
    }
    
    public void asignarBHQClienteAContrato(){
        logger.warning("Inicia metodo asignarBHQClienteAContrato.");
        
        String bhqCliente = null;
        
        try{
            bhqCliente = (String) ADFUtils.getBoundAttributeValue("Flexcube");
        }catch(Exception e){
            logger.warning("ERROR al recuperar el BoundATtribute BHQCliente de DetalleDesembolsosOperacionVO.", e);
        }
        logger.warning("BHQCliente recuperado: " + bhqCliente);
        
        try{
            ADFUtils.setBoundAttributeValue("BHQCliente", bhqCliente);
        }catch(Exception e){
            logger.warning("ERROR al asignar BoundAttribute BHQCliente a CrearActualizarContratoDesembolsoVO.", e);
        }
        
        logger.warning("Termina metodo asignarBHQClienteAContrato.");
    }
    
    
    public void obtenerFechaEstimadaDisponibilidadRecursos(){
      logger.warning("Inicia metodo obtenerFechaEstimadaDisponibilidadRecursos");
        
        BindingContainer bindings = null;
        OperationBinding operationBinding = null;
         
        try{ 
          bindings =  ADFUtils.getBindingContainer();
          operationBinding = bindings.getOperationBinding("obtenerFechaDisponibilidadRecPrecarga");            
          operationBinding.execute();
         }catch(Exception e){
           logger.warning("Error, operBinding obtenerFechaDisponibilidadRecPrecarga ->",e);
           JSFUtils.addFacesErrorMessage("Error, operBinding ->"+e.getMessage());
         }
        
        if(!operationBinding.getErrors().isEmpty()){
            logger.warning("*Inf, Error al obtener la Fecha Estimada Disponibilidad Recursos->"
                                                                             +operationBinding.getErrors().toString());
        }        
        
      logger.warning("Termina metodo obtenerFechaEstimadaDisponibilidadRecursos");        
    }
    
    
    public Double montoProgramadoMesVig(Long idContrato){
        logger.warning("Inf, inicia metodo montoProgramadoMesVig");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
            
           Double montoProgramado = null;
           Map mapaDatos = new HashMap();
            try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("consultarDesembolsoService");
            operationBinding.getParamsMap().put("idContrato", idContrato);
            logger.warning("Ejecutando OperationBinding de precargaInformacionContrato");
            operationBinding.execute();
                
            if(!operationBinding.getErrors().isEmpty()){
                logger.warning("*** OperationBinding consultarDesembolsoService devuelve errores->"+operationBinding.getErrors());
                JSFUtils.addFacesErrorMessage("ERROR al recuperar datos de contrato de desembolso: " + operationBinding.getErrors().toString());
            }else{  
                mapaDatos = (Map) operationBinding.getResult();
                if(null != mapaDatos)
                    montoProgramMesVigente = (BigDecimal)mapaDatos.get("montoProgramado");                
                else
                    logger.warning("Ejecutando OperationBinding de precargaInformacionContrato"); 
            }
                
            }catch(Exception e){
                logger.warning("Ocurrio un error al ejecutar el OperationBinding: ", e);
            }
            
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/1000; //Calculamos los milisegundos de diferencia
        logger.warning("Inf, Termina metodo montoProgramadoMesVig con una duracion de: "+tiempo+" segundos");
        return montoProgramado;
        }
    
    public void mostrarCamposSoloLectura(){
        logger.warning("Inicia metodo mostrarCamposSoloLectura");
        Integer idTareaBPM = null;
        Boolean esSoloLectura = Boolean.FALSE;
        Boolean esCumplimientoCondicion = Boolean.FALSE;
        
        try{
            idTareaBPM = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}").toString());        
            logger.warning("idTareaBPM: " + idTareaBPM);
        }catch(Exception e){
            logger.warning("No se pudo obtener el idTareaBPM ", e);
        }
        
        try{
           // esSoloLectura = new Boolean(JSFUtils.resolveExpression("#{pageFlowScope.esSoloLectura}").toString());
            esSoloLectura = Boolean.FALSE;
            logger.warning("Es modo lectura: " + esSoloLectura);
        }catch(Exception e){
            logger.warning("No se pudo obtener el parametro esSoloLectura ", e);
        }
        
        try{
            logger.warning("Obteniendo parametro esIniciarCumplimientoCondicion");
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.esIniciarCumplimientoCondicion}")){
                esCumplimientoCondicion = (Boolean) JSFUtils.resolveExpression("#{pageFlowScope.esIniciarCumplimientoCondicion}");
            }else{
                logger.warning("El parametro esIniciarCumplimientoCondicion es NULL.");
            }
            logger.warning("Atributo esCumplimientoCondicion: " + esCumplimientoCondicion);
        }catch(Exception e){
            logger.warning("No se pudo obtener el parametro esIniciarCumplimientoCondicion ", e);
        }
        
        if(null != idTareaBPM){
            switch(idTareaBPM){
            case FenixConstants.CG10_GESTOR_DESEMBOLSOS:
                
                if(null != esSoloLectura){
                    if(esSoloLectura){
                        setMostrarBotones(Boolean.FALSE);
                        setEsCampoSoloLectura(Boolean.TRUE);
                        setDeshabilitarBotones(Boolean.TRUE);
                    }else{
                        setMostrarBotones(Boolean.TRUE);
                        setDeshabilitarBotones(Boolean.FALSE);
                        if(null != esCumplimientoCondicion){
                            if(esCumplimientoCondicion){
                                setEsCampoSoloLectura(Boolean.TRUE);
                                setMostrarTabsContrato(Boolean.TRUE);
                            }else{
                                setEsCampoSoloLectura(Boolean.FALSE);
                                setMostrarTabsContrato(Boolean.FALSE);
                            }
                        }else{
                            logger.warning("Atributo esCumplimientoCondicion es NULL");
                        }
                    }
                }else{
                    logger.warning("Atributo esSoloLectura es NULL");
                }
                break;
            case FenixConstants.PC06_REALIZAR_AJUSTES_ASIGNACION_RECURSOS:
                setMostrarBotones(Boolean.TRUE);
                setMostrarTabsContrato(Boolean.TRUE);
                setEsCampoSoloLectura(Boolean.FALSE);
                break;
            case FenixConstants.PC06_REALIZAR_AJUSTES_DESEMBOLSO:
                setMostrarBotones(Boolean.TRUE);
                setMostrarTabsContrato(Boolean.TRUE);
                setEsCampoSoloLectura(Boolean.FALSE);
                break;
            default:
                break;
            }
        }else{
            logger.warning("El idTareaBPM del TaskFlow es NULL");
        }
        
        logger.warning("Inicia metodo mostrarCamposSoloLectura");
    }
    
    /**
     * Método para definir configuracion de componentes del contrato en base a
     * IDTAREA: En caso de ser NULL se tomará como configuración de Gestor de desembolso,
     * en caso de tener algún valor se tomará en cuenta la configuración por tarea.
     * IDESTADOSOLICITUD
     */
    public void configurarFormularioContrato(){
        logger.warning("Inicia metodo configurarFormularioContrato");
        double TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
        TInicio = System.currentTimeMillis(); //Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio
        
        Integer idTareaBPM = null;
        Integer idEstadoSolicitud = null;
        Long idContratoDesembolso = null;
        String esInicioCumplimientoCondicion = null;
        Boolean esIniciarCumplimiento = Boolean.FALSE;
        Map<String, Object> params = new HashMap<String, Object>();
        Integer idEstadoContrado = null;
        Boolean requiereValidacion = Boolean.FALSE;
        Boolean existenCoberturas = Boolean.FALSE;
        String errorVerificarValidacion = null;
        Boolean esIniciarProcesoDesembolso = getEsIniciarProcesoDesembolso();
        
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}")){
                idTareaBPM = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}").toString());
            }else{
                logger.warning("Atributo del TaskFlow pIdTareaBPM es null");
            }
        }catch(Exception e){
            logger.warning("ERROR AL OBTENER EL IDTAREA DE TASKFLOW ", e);
        }
        
        try{
            /* if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdEstadoSolicitud}")){
                logger.warning("pageFlowScope.pIdEstadoSolicitud: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdEstadoSolicitud}"));
                idEstadoSolicitud = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdEstadoSolicitud}").toString());
            }else{
                logger.warning("Atributo del TaskFlow pIdEstadoSolicitud es null");
            } */
            
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("idSolicitud", idSolicitudTF);
            idEstadoSolicitud = execute(paramsMap, "obtenerEstadoSolicitudPorId");
        }catch(Exception e){
            logger.warning("ERROR AL OBTENER EL IDESTADOSOLICITUD.", e);
        }
        
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}")){
                idContratoDesembolso = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}").toString());
            }else{
                logger.warning("Atributo del TaskFlow pIdContratoDesembolso es null");
            }
        }catch(Exception e){
            logger.warning("ERROR AL OBTENER IDCONTRATODESEMBOLSO ", e);
        }
        
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.esIniciarCumplimientoCondicion}")){
                esIniciarCumplimiento =
                    (Boolean) JSFUtils.resolveExpression("#{pageFlowScope.esIniciarCumplimientoCondicion}");
                logger.log(ADFLogger.WARNING, "esIniciarCumplimiento = " + esIniciarCumplimiento);
            }else{
                logger.warning("Atributo de TaskFlow esIniciarCumplimientoCondicion es NULL");
            }
        }catch(Exception e){
            logger.warning("ERROR AL OBTENER EL ATRIBUTO ESCUMPLIMIENTOCONDICION ", e);
        }
        
        logger.warning("IdTareaBPM: " + idTareaBPM);
        logger.warning("IdEstadoSolicitud: " + idEstadoSolicitud);
        logger.warning("IdContratoDesembolso: " + idContratoDesembolso);
        logger.warning("esInicioCumplimientoCondicion: " + esIniciarCumplimiento);
        logger.warning("IdEstadoContrato: " + getIdEstadoContrato());
        
        params.put("idSolicitud", getIdSolicitudTF());
        params.put("idOperacion", getIdOperacionTF());
        
        /*if(null != idContratoDesembolso){
            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("requiereValidacionAsignacionSolicitud");
                operationBinding.getParamsMap().put("idSolicitud", getIdSolicitudTF());
                operationBinding.getParamsMap().put("idOperacion", getIdOperacionTF());
                
                logger.warning("Ejecutando OperationBinding de requiereValidacionAsignacionSolicitud");
                operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    errorRequiereValidacionAsignacion = "Hubo un error al ejecutar el operBinding requiereValidacionAsignacionSolicitud." + operationBinding.getErrors().toString();
                    logger.warning("Hubo un error al ejecutar el operBinding requiereValidacionAsignacionSolicitud." + operationBinding.getErrors().toString());
                }else{
                    errorRequiereValidacionAsignacion = null;
                    try{
                        requiereValidacion = (Boolean) operationBinding.getResult();
                    }catch(Exception ex){
                        logger.warning("ERROR al recuperar el resultado de Requiere validacion.", ex);
                    }
                }
                
                errorVerificarValidacion = (String) ADFUtils.getBoundAttributeValue("MensajeErrorServicio");
                if(null != errorVerificarValidacion && !errorVerificarValidacion.equals("")){
                    logger.warning("Mensaje de errorValidacion: " + errorVerificarValidacion);
                    JSFUtils.addFacesErrorMessage("ConfigurarFormularioContrato(): " + errorVerificarValidacion);
                    ADFUtils.setBoundAttributeValue("MensajeErrorServicio", null);
                }
                                
            }catch(Exception e){
                logger.warning("No se pudo ejecutar el OperationBinding de requiereValidacionAsignacionSolicitud");
                JSFUtils.addFacesErrorMessage(e.getMessage());
            }
        }*/
        
        //IMPORTANTE: Se inicializan todos los tabs.
        //Si se elimina alguna de las siguientes sentencias mostrara un ADF-FACES debido al seteo del path de taskFlowId.
        this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
        this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
        this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
        this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
        this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
        this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
        this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
        this.showTabLiquidarContrato(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
        this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
        
        guardaTab=Boolean.TRUE;
        setIdTareaNoMostrarMontoProgramado(0);
        
        if(null != idTareaBPM){
            switch(idTareaBPM){
            case FenixConstants.CG10_GESTOR_DESEMBOLSOS:
                logger.warning("GESTOR DE DESEMBOLSOS.");
                /**------------------------ CASE EN GESTOR DE DESEMBOLSOS  ----------------------------------------**/           
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                tabAsignacionRecursos=Boolean.TRUE;                 //Tab activo
                disclosedTabAsignacionRecursos=Boolean.TRUE;        //Tab habilitado                                                    
                
                        if(null != idEstadoSolicitud){
                            if(null != idContratoDesembolso){
                                switch(idEstadoSolicitud){
                                case FenixConstants.ESTADO_SOLICITUD_DESEMBOLSO_CREADO:
                                   logger.warning("*Inf, SE RECIBIO UN ESTADO DE LA SOLICITUD EN ESTADO CREADO DENTRO DE GESTOR");
                                    setMostrarBotones(Boolean.TRUE);
                                    setDeshabilitarGuardarBtn(Boolean.FALSE);
                                    setEsCampoSoloLectura(Boolean.FALSE);
                                    setReadOnlyFondo(Boolean.FALSE);
                                    
                                    if(esIniciarCumplimiento){
                                        setDeshabilitarProcesoBtn(Boolean.FALSE);
                                        
                                        this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        
                                        requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                        if(requiereValidacion){
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                        }else{
                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        }
                                    }else{                            
                                        setDeshabilitarProcesoBtn(Boolean.TRUE);
                                        
                                        this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                        this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                        this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                        this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                        this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                    }
                                break;
                                case FenixConstants.ESTADO_SOLICITUD_DESEMBOLSO_VALIDACION:
                                    logger.warning("*Inf, SE RECIBIO UN ESTADO DE LA SOLICITUD EN ESTADO VALIDACION DENTRO DE GESTOR");
                                    setMostrarBotones(Boolean.TRUE);
                                    setEsCampoSoloLectura(Boolean.TRUE);
                                    setReadOnlyFondo(Boolean.TRUE);
                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                    setDeshabilitarGuardarBtn(Boolean.FALSE);
                                    setDeshabilitarProcesoBtn(Boolean.FALSE);

                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    
                                    idEstadoContrado = getIdEstadoContrato();
                                    if(null != idEstadoContrado){
                                        if(idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO)==0){
                                            setMostrarBotones(Boolean.TRUE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            
                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                        }
                                        
                                        if(idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR) == 0){
                                            logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO POR LIQUIDAR DENTRO DE GESTOR");
                                            setMostrarBotones(Boolean.TRUE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
                                            
                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                        }
                                        if(idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO) == 0){
                                            logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO LIQUIDADO DENTRO DE GESTOR");
                                            setMostrarBotones(Boolean.TRUE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
                                            
                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                        }
                                        
                                        //Se agrega como WA en lo que se corrige el motivo por el cual no se actualiza el estado de la solicitud
                                        if(idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_TRANSITO) == 0){
                                            logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO EN TRANSITO DENTRO DE GESTOR");
                                            setMostrarBotones(Boolean.FALSE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                            this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                        }
                                        if(idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_APROBADO) == 0){
                                            logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO APROBADO DENTRO DE GESTOR");
                                            setMostrarBotones(Boolean.FALSE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                        }
                                        if(idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_FONDOS) == 0){
                                            logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO FONDOS DENTRO DE GESTOR");
                                            setMostrarBotones(Boolean.FALSE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                        }
                                        if(idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_REGISTRADO) == 0){
                                            logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO REGISTRADO DENTRO DE GESTOR");
                                            setMostrarBotones(Boolean.FALSE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                        }
                                        if(idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO) == 0){
                                            logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO DESEMBOLSADO DENTRO DE GESTOR");
                                            setMostrarBotones(Boolean.FALSE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                            
                                            try{
                                                existenCoberturas = execute(null, "validarCoberturasExistentesPorContrato");
                                            }catch(Exception e){
                                                logger.warning("ERROR al ejecutar operBinding validarCoberturasExistentesPorContrato", e);
                                            }
                                            
                                            if(existenCoberturas){
                                                this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
                                            }else{
                                                this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
                                            }
                                        }
                                        
                                        
                                    }
                                break;
                                case FenixConstants.ESTADO_SOLICITUD_DESEMBOLSO_CUMPLIMIENTO:
                                    logger.warning("*Inf, SE RECIBIO UN ESTADO DE LA SOLICITUD EN ESTADO CUMPLIMIENTO DENTRO DE GESTOR");
                                    setMostrarBotones(Boolean.TRUE);
                                    setEsCampoSoloLectura(Boolean.TRUE);
                                    setReadOnlyFondo(Boolean.TRUE);
                                    setDeshabilitarGuardarBtn(Boolean.FALSE);
                                    setDeshabilitarProcesoBtn(Boolean.FALSE);
                                    
                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                                       
                                        idEstadoContrado = getIdEstadoContrato();
                                        
                                        if(null != idEstadoContrado){
                                            switch(idEstadoContrado){
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO:
                                                setMostrarBotones(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.FALSE);
                                                setReadOnlyFondo(Boolean.FALSE);
                                                
                                                if(esIniciarCumplimiento){
                                                    setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    
                                                    requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                                    
                                                    if(requiereValidacion){
                                                        this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    }else{
                                                        this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    }
                                                }else{                            
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    
                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                }                                    
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_VALIDACION:
                                                setMostrarBotones(Boolean.TRUE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CUMPLIMIENTO:
                                                setMostrarBotones(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                
                                                requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                                
                                                if(requiereValidacion){
                                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                }else{
                                                    setEsCampoSoloLectura(Boolean.FALSE);
                                                    setReadOnlyFondo(Boolean.FALSE);
                                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                }
                                            break;                               
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_PREPARADO: 
                                                setMostrarBotones(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                
                                                requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                                
                                                if(requiereValidacion){
                                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                }else{
                                                    setEsCampoSoloLectura(Boolean.FALSE);
                                                    setReadOnlyFondo(Boolean.FALSE);
                                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                }                                     
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO:
                                                setMostrarBotones(Boolean.TRUE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
                                                
                                                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_TRANSITO:
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_APROBADO:
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_FONDOS:
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
    
                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_REGISTRADO:
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE);

                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO:
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    
                                                    try{
                                                        existenCoberturas = execute(null, "validarCoberturasExistentesPorContrato");
                                                    }catch(Exception e){
                                                        logger.warning("ERROR al ejecutar operBinding validarCoberturasExistentesPorContrato", e);
                                                    }
                                                    
                                                    if(existenCoberturas){
                                                        this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
                                                    }else{
                                                        this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
                                                    }
                                                break;
                                            }
                                        }else{                                            
                                            logger.warning("No se pudo recuperar IdEstadoContrado.");
                                        }
                                                                                                   
                                break;
                                case FenixConstants.ESTADO_SOLICITUD_DESEMBOLSO_DISPUESTO:  
                                    logger.warning("*Inf, SE RECIBIO UN ESTADO DE LA SOLICITUD EN ESTADO DISPUESTO DENTRO DE GESTOR");
                                    
                                    setMostrarBotones(Boolean.TRUE);
                                    setEsCampoSoloLectura(Boolean.TRUE);
                                    setReadOnlyFondo(Boolean.TRUE);
                                    setDeshabilitarGuardarBtn(Boolean.FALSE);
                                    setDeshabilitarProcesoBtn(Boolean.FALSE);
                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
                                    
                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                    
                                    
                                        idEstadoContrado = getIdEstadoContrato();
                                        
                                        if(null != idEstadoContrado){
                                            switch(idEstadoContrado){
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO:
                                                setMostrarBotones(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.FALSE);
                                                setReadOnlyFondo(Boolean.FALSE);
                                                
                                                if(esIniciarCumplimiento){
                                                    setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    
                                                    requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                                    
                                                    if(requiereValidacion){
                                                        this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    }else{
                                                        this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    }
                                                }else{                            
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    
                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                }                                    
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_VALIDACION:
                                                setMostrarBotones(Boolean.TRUE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CUMPLIMIENTO:
                                                setMostrarBotones(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                
                                                requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                                
                                                if(requiereValidacion){
                                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                }else{
                                                    setEsCampoSoloLectura(Boolean.FALSE);
                                                    setReadOnlyFondo(Boolean.FALSE);
                                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                }
                                            break;                               
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_PREPARADO: 
                                                logger.warning("Solicitud DIspuesta - ContratoPreparado.");
                                                setMostrarBotones(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                
                                                //JURBINA@07NOV2019 CUANDO LA SOLICITUD SE ENCUENTRA DISPUESTA Y EL CONTRATO PREPARADO, SE PERMITE CAMBIAR LA FECHA ESTIMADA A DESEMBOLSAR SEGUN APROBACION DE UMC
                                                setFechaEstimadaDesemReadOnly(Boolean.FALSE);
                                                
                                                
                                                if(null != esIniciarProcesoDesembolso && esIniciarProcesoDesembolso){
                                                    logger.warning("Accion es Iniciar Proceso de desembolso");
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                }else{
                                                    logger.warning("NO es Accion es Iniciar Proceso de desembolso");
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    
                                                    requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                                    
                                                    if(requiereValidacion){
                                                        this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    }else{
                                                        setEsCampoSoloLectura(Boolean.FALSE);
                                                        setReadOnlyFondo(Boolean.FALSE);
                                                        this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    }
                                                }
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_TRANSITO:
                                                setMostrarBotones(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_APROBADO:
                                                setMostrarBotones(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_FONDOS:
                                                setMostrarBotones(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                setFechaEstimadaDesemReadOnly(Boolean.TRUE);

                                                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_REGISTRADO:
                                                setMostrarBotones(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                setFechaEstimadaDesemReadOnly(Boolean.TRUE);

                                                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO:
                                                setMostrarBotones(Boolean.FALSE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                
                                                try{
                                                    existenCoberturas = execute(null, "validarCoberturasExistentesPorContrato");
                                                }catch(Exception e){
                                                    logger.warning("ERROR al ejecutar operBinding validarCoberturasExistentesPorContrato", e);
                                                }
                                                
                                                if(existenCoberturas){
                                                    this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
                                                }else{
                                                    this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
                                                }
                                            break;
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO:
                                                setMostrarBotones(Boolean.TRUE);
                                                setEsCampoSoloLectura(Boolean.TRUE);
                                                setReadOnlyFondo(Boolean.TRUE);
                                                setEsMonedaSoloLectura(Boolean.TRUE);
                                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
                                                
                                                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            break;
                                            }
                                        }else{
                                            requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                            
                                            if(requiereValidacion){
                                                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            }
                                            logger.warning("No se pudo recuperar IdEstadoContrado.");
                                        }
                                                                   
                                    
                                break;
                                case FenixConstants.ESTADO_SOLICITUD_DESEMBOLSO_PROCESO:
                                    logger.warning("*Inf, SE RECIBIO UN ESTADO DE LA SOLICITUD CON ESTADO EN PROCESO DENTRO DE GESTOR");
                                   
                                        setMostrarBotones(Boolean.TRUE);
                                        setEsCampoSoloLectura(Boolean.TRUE);
                                        setReadOnlyFondo(Boolean.TRUE);
                                        setEsMonedaSoloLectura(Boolean.TRUE);
                                        setDeshabilitarGuardarBtn(Boolean.FALSE);
                                        setDeshabilitarProcesoBtn(Boolean.FALSE);
                                        
                                        this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                        this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                        this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);                                        
                                            
                                            idEstadoContrado = getIdEstadoContrato();
                                            
                                            if(null != idEstadoContrado){
                                                switch(idEstadoContrado){
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO CREADO DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.FALSE);
                                                    setReadOnlyFondo(Boolean.FALSE);
                                                    setEsMonedaSoloLectura(Boolean.FALSE);
                                                    
                                                    if(esIniciarCumplimiento){
                                                        setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                        setEsCampoSoloLectura(Boolean.TRUE);
                                                        setReadOnlyFondo(Boolean.TRUE);
                                                        setEsMonedaSoloLectura(Boolean.TRUE);
                                                        
                                                        this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        
                                                        requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                                        
                                                        if(requiereValidacion){
                                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        }else{
                                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        }
                                                    }else{                            
                                                        setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                        
                                                        this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    }                                    
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_VALIDACION:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO EN VALIDACION DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.TRUE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                    setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CUMPLIMIENTO:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO EN CUMPLIMIENTO DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                    setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    
                                                    requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                                    
                                                    if(requiereValidacion){
                                                        this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    }else{
                                                        setEsCampoSoloLectura(Boolean.FALSE);
                                                        setReadOnlyFondo(Boolean.FALSE);
                                                        setEsMonedaSoloLectura(Boolean.FALSE);
                                                        this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                    }
                                                break;                               
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_PREPARADO:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO PREPARADO DENTRO DE GESTOR Y SOLICITUD EN PROCESO.");
                                                    setMostrarBotones(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.FALSE);
                                                    setDeshabilitarGuardarBtn(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    
                                                    if(null != esIniciarProcesoDesembolso && esIniciarProcesoDesembolso){
                                                        logger.warning("Es accion Iniciar proceso de desembolso.");
                                                        setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                        setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                        this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    }else{
                                                        logger.warning("NO es accion Iniciar proceso de desembolso.");
                                                        this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        
                                                        requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                                        
                                                        if(requiereValidacion){
                                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                        }else{
                                                            setEsCampoSoloLectura(Boolean.FALSE);
                                                            setReadOnlyFondo(Boolean.FALSE);
                                                            setEsMonedaSoloLectura(Boolean.FALSE);
                                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                                                        }
                                                    }
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_TRANSITO:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO EN TRANSITO DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_APROBADO:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO APROBADO DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_FONDOS:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO FONDOS RESERVADOS DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_REGISTRADO:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO REGISTRADO DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO DESEMBOLSADO DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.FALSE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 

                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    
                                                    try{
                                                        existenCoberturas = execute(null, "validarCoberturasExistentesPorContrato");
                                                    }catch(Exception e){
                                                        logger.warning("ERROR al ejecutar operBinding validarCoberturasExistentesPorContrato", e);
                                                    }
                                                    
                                                    if(existenCoberturas){
                                                        this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
                                                    }else{
                                                        this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
                                                    }
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO DESESTIMADO DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.TRUE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
                                                    
                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO POR LIQUIDAR DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.TRUE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
                                                    
                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO:
                                                    logger.warning("*Inf, SE RECIBIO UN CONTRATO CON ESTADO LIQUIDADO DENTRO DE GESTOR");
                                                    setMostrarBotones(Boolean.TRUE);
                                                    setEsCampoSoloLectura(Boolean.TRUE);
                                                    setReadOnlyFondo(Boolean.TRUE);
                                                    setEsMonedaSoloLectura(Boolean.TRUE);
                                                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                                                    setDeshabilitarProcesoBtn(Boolean.TRUE);
                                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE); 
                                                    
                                                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                                break;
                                                }
                                            }else{
                                                logger.warning("No se pudo recuperar IdEstadoContrado.");
                                            }
                                    
                                    requiereValidacion = obtenerRequiereValidacionAsignacionSolicitud();
                                    
                                     logger.warning("requiereValidacion: "+requiereValidacion);
                                    
                                    
                                    if(requiereValidacion){
                                        boolean aplicarCambio = Boolean.TRUE;
                                        
                                        if (null != idEstadoContrado) {
                                            switch(idEstadoContrado){
                                            case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_PREPARADO:
                                                aplicarCambio = Boolean.FALSE;
                                            break;
                                            }
                                        }
                                        
                                        if (aplicarCambio) {
                                            // se comentan lineas para FNXII-6843
                                            //setMostrarBotones(Boolean.FALSE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                           // setDeshabilitarGuardarBtn(Boolean.TRUE);
                                           // setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                        }
                                    }
                                    
                                    break;
                                case FenixConstants.ESTADO_SOLICITUD_DESEMBOLSO_CERRADO:
                                    idEstadoContrado = getIdEstadoContrato();
                                    setFechaEstimadaDesemReadOnly(Boolean.TRUE);
                                    if(null != idEstadoContrado){
                                        switch(idEstadoContrado){
                                        case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO:                                            
                                            setMostrarBotones(Boolean.FALSE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            
                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabLiquidarContrato(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            
                                            try{
                                                existenCoberturas = execute(null, "validarCoberturasExistentesPorContrato");
                                            }catch(Exception e){
                                                logger.warning("ERROR al ejecutar operBinding validarCoberturasExistentesPorContrato", e);
                                            }
                                            
                                            if(existenCoberturas){
                                                this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
                                            }else{
                                                this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
                                            }
                                            break;
                                        case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_DESEMBOLSADO:
                                            setMostrarBotones(Boolean.FALSE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            
                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabLiquidarContrato(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            
                                            try{
                                                existenCoberturas = execute(null, "validarCoberturasExistentesPorContrato");
                                            }catch(Exception e){
                                                logger.warning("ERROR al ejecutar operBinding validarCoberturasExistentesPorContrato", e);
                                            }
                                            
                                            if(existenCoberturas){
                                                this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
                                            }else{
                                                this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
                                            }
                                            break;
                                        case FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO:
                                            setMostrarBotones(Boolean.FALSE);
                                            setEsCampoSoloLectura(Boolean.TRUE);
                                            setReadOnlyFondo(Boolean.TRUE);
                                            setEsMonedaSoloLectura(Boolean.TRUE);
                                            setDeshabilitarGuardarBtn(Boolean.TRUE);
                                            setDeshabilitarProcesoBtn(Boolean.TRUE);
                                            
                                            this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabLiquidarContrato(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                                            
                                            try{
                                                existenCoberturas = execute(null, "validarCoberturasExistentesPorContrato");
                                            }catch(Exception e){
                                                logger.warning("ERROR al ejecutar operBinding validarCoberturasExistentesPorContrato", e);
                                            }
                                            
                                            if(existenCoberturas){
                                                this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
                                            }else{
                                                this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                
                                    default:
                                   
                                    break;
                                }  
                                
                            //se verifica por el responsable de la operacion
                            if(!esUsuarioResponsableDeOperacion()){                                                
                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                setEsCampoSoloLectura(Boolean.TRUE);
                                setReadOnlyFondo(Boolean.TRUE);
                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                
                                 this.modoEjecucionTabAsignacionRecursos = FenixConstants.MODO_EJECUCION_LECTURA;
                                 this.modoEjecucionTabDatosGenerales = FenixConstants.MODO_EJECUCION_LECTURA;
                                 this.modoEjecucionTabTransferencias = FenixConstants.MODO_EJECUCION_LECTURA;
                                 this.modoEjecucionTabConsolidacionTransferencias = FenixConstants.MODO_EJECUCION_LECTURA;
                                 this.modoEjecucionTabCargos = FenixConstants.MODO_EJECUCION_LECTURA;
                                 this.modoEjecucionTabCondicionesFinancieras = FenixConstants.MODO_EJECUCION_LECTURA;
                                 this.modoEjecucionTabReservaFondos = FenixConstants.MODO_EJECUCION_LECTURA;
                                 this.modoEjecucionTabLiquidarContrato = FenixConstants.MODO_EJECUCION_LECTURA;
                            }
                                
 
                            }else{
                                setMostrarFormularioDesembolsos(Boolean.FALSE);
                                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                                
                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                setEsCampoSoloLectura(Boolean.TRUE);
                                setReadOnlyFondo(Boolean.TRUE);
                                setDeshabilitarGuardarBtn(Boolean.TRUE);
                                setDeshabilitarProcesoBtn(Boolean.TRUE);
                                
                                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                                this.showTabCoberturas(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
                            }              
                        }else{
                            logger.warning("EL IDESTADOSOLICITUD ES NULL");
                        }
                        /**--------------------------- FIN DEL CASE DE GESTOR DE DESEMBOLSOS ------------------------------------------**/ 
                break;
            case FenixConstants.CGD_VALIDAR_FONDOS_PRESUPUESTARIOS:
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setIdTareaNoMostrarMontoProgramado(idTareaBPMTF);
                
                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                break;
            case FenixConstants.CGD_GESTIONAR_DESEMBOLSO_DE_FUENTES_EXTERNAS:
                logger.warning("*Inf, Configurando tabs para la tarea de CGD_GESTIONAR_DESEMBOLSO_DE_FUENTES_EXTERNAS");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                
                if(estatusTareaBpm.equalsIgnoreCase("COMPLETED")){ //  COMPLETED - ASSIGNED
                    logger.warning("*Inf, Configurando Modo Lectura");
                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                  
                }else{
                
                    this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ASIGN_REC_GESTION_TRANSFERENCIAS);
                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                }
                
                break;
            case FenixConstants.CGD_REALIZAR_AJUSTES_A_DESEMBOLSO: 
                logger.warning("*Inf, Configurando tabs para la tarea de CGD_REALIZAR_AJUSTES_A_DESEMBOLSO");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                //JURBINA@29072021 Se activa el boton de guardar a solicitud de COFI, cuando hay modificaciones en varios tabs se pierden por no lograr guardarse
                setMostrarBotones(Boolean.TRUE);  
                setRenderBtnProceso(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.FALSE);
                setReadOnlyFondo(Boolean.FALSE);
                
                logger.warning("*Inf, Estatus de tarea: "+estatusTareaBpm);
                //Esta bandera nos indica si la tarea BPM ya finalizo o sige activa
                if(estatusTareaBpm.equalsIgnoreCase("COMPLETED")){ //  COMPLETED - ASSIGNED
                  logger.warning("*Inf, Configurando Modo Lectura");
                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                }else{
                    logger.warning("*Inf, Configurando Modo Escritura");
                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                }

               
                break;
            case FenixConstants.CGD_VALIDAR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS:
                //EL NOMBRE DE LA TAREA CAMBIO A DEFINIR CLASIFICACION ESTRATEGICA
                logger.warning("*Inf, Configurando tabs para la tarea de CGD_DEFINIR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                
                logger.warning("*Inf, Estatus de tarea: "+estatusTareaBpm);
                //Esta bandera nos indica si la tarea BPM ya finalizo o sige activa
                
                if(estatusTareaBpm.equalsIgnoreCase("COMPLETED")){ //  COMPLETED - ASSIGNED
                  logger.warning("*Inf, Configurando Modo Lectura");
                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabDatosGenerales(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                }else{                
                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabDatosGenerales(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                }
                break;
            case FenixConstants.CGD_VALIDAR_INFORMACION_FINANCIERA_DE_DESEMBOLSO:
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                setIdTareaNoMostrarMontoProgramado(idTareaBPMTF);
                
                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                break;  
            case FenixConstants.CGD_APROBAR_DESEMBOLSO:
                logger.warning("Configurar tarea Aprobar desembolso");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                
                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabDatosGenerales(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA); 
                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);               
                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                break;   
            case FenixConstants.CGD_GESTIONAR_PROGRAMACION:
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                
                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabDatosGenerales(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA); 
                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);               
                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                break;  
            case FenixConstants.CGD_RESERVAR_FONDOS:
                logger.warning("*Inf, Configurando tabs para la tarea de CGD_RESERVAR_FONDOS");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                
                logger.warning("*Inf, Estatus de tarea: "+estatusTareaBpm);
                //Esta bandera nos indica si la tarea BPM ya finalizo o sige activa
                if(estatusTareaBpm.equalsIgnoreCase("COMPLETED")){ //  COMPLETED - ASSIGNED
                  logger.warning("*Inf, Configurando Modo Lectura");
                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabReservaFondos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                }else{
                
                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabReservaFondos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_RESERVA_DE_FONDOS);
                }
                
                break;   
            case FenixConstants.CGD_REGISTRAR_DESEMBOLSO:
                logger.warning("*Inf, Configurando tabs para la tarea de CGD_REGISTRAR_DESEMBOLSO");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                
                logger.warning("*Inf, Estatus de tarea: "+estatusTareaBpm);
                //Esta bandera nos indica si la tarea BPM ya finalizo o sige activa
                if(estatusTareaBpm.equalsIgnoreCase("COMPLETED")){ //  COMPLETED - ASSIGNED
                   logger.warning("*Inf, Configurando Modo Lectura");                                                                    
                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);                
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                }else{
                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabDatosGenerales(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_REGISTRO_DESEMBOLSO);                
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_TRANSFERENCIAS_APLICACION);
                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabConsolidacionTransferencias(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                }
                
                break;   
            case FenixConstants.CGD_LIQUIDAR_CONTRATO_DE_DESEMBOLSO:
                logger.warning("*Inf, Configurando tabs para la tarea de CGD_LIQUIDAR_CONTRATO_DE_DESEMBOLSO");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                
                
                logger.warning("*Inf, Estatus de tarea: "+estatusTareaBpm);
                //Esta bandera nos indica si la tarea BPM ya finalizo o sige activa
                if(estatusTareaBpm.equalsIgnoreCase("COMPLETED")){ //  COMPLETED - ASSIGNED
                    logger.warning("*Inf, Configurando Modo Lectura");
                    showTabLiquidarContrato(Boolean.TRUE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                }else{
                   showTabLiquidarContrato(Boolean.TRUE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                }
                
                break;   
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_DAECI:
                logger.warning("Entra a configuracion de tarea Validar asignacion de recursos DAECI");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                
                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                break;   
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_FINAM:
                logger.warning("Entra a configuracion de tarea Validar asignacion de recursos FINAM");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                
                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                break;   
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PCT:
                logger.warning("Entra a configuracion de tarea Validar asignacion de recursos PCT");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                                
                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                break; 
            case FenixConstants.CGD_VALIDAR_RAROC:
                logger.warning("Entra a configuracion de tarea CGD_VALIDAR_RAROC hijo");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                                    
                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                break;   
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PREA:
                logger.warning("Entra a configuracion de tarea Validar asignacion de recursos PREA");
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                
                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                break;   
            case FenixConstants.CGD_REALIZAR_AJUSTES_DE_ASIGNACION_DE_RECURSOS:
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                setMostrarBotones(Boolean.TRUE);
                setDeshabilitarGuardarBtn(Boolean.FALSE);
                setRenderBtnProceso(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.FALSE);
                setReadOnlyFondo(Boolean.FALSE);
                
                this.showTabAsignacionRecursos(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                break;   
            case FenixConstants.CGD_JUSTIFICAR_DESEMBOLSO_POR_EXCEPCION:
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                break;   
            case FenixConstants.CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION:
                setMostrarFormularioDesembolsos(Boolean.TRUE);
                setMostrarFormularioEnvioCobro(Boolean.FALSE);
                break; 
            case FenixConstants.PA11_ENVIAR_AL_COBRO:
                logger.warning("Entra en Enviar al cobro en contratoDesembolso Bean.");
                idEstadoContrado = this.getIdEstadoContrato();
                setMostrarFormularioDesembolsos(Boolean.FALSE);
                setMostrarFormularioEnvioCobro(Boolean.TRUE);
                setMostrarBotones(Boolean.FALSE);
                setMostrarBotonGuardarTabs(Boolean.TRUE);
                setDeshabilitarGuardarBtn(Boolean.TRUE);
                setRenderBtnProceso(Boolean.FALSE);
                setReadOnlyFondo(Boolean.FALSE);
                setEsMonedaSoloLectura(Boolean.FALSE);
                setEsLecturaFechaEfectivaEnvioCobro(Boolean.FALSE);
                
                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                //this.showTabDatosGenerales(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                //this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                //this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                logger.warning("Valor del estado del contrato :" + idEstadoContrado);
                if (null == idEstadoContrado ||
                    idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_POR_LIQUIDAR) == 0 ||
                    idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_LIQUIDADO) == 0) {
                    setEsCampoSoloLectura(Boolean.TRUE);
                    setReadOnlyFondo(Boolean.TRUE);
                    this.showTabDatosGenerales(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1,
                                                       FenixConstants.MODO_EJECUCION_LECTURA);
                    setEsLecturaFechaEfectivaEnvioCobro(Boolean.TRUE);
                } else {
                    if (idEstadoContrado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO_POR_IMPLEMENTACION) ==
                        0) {
                        setEsCampoSoloLectura(Boolean.TRUE);
                        setReadOnlyFondo(Boolean.FALSE);
                        this.showTabDatosGenerales(Boolean.TRUE, Boolean.TRUE, 1,
                                                   FenixConstants.MODO_EJECUCION_ESCRITURA);
                        this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_ESCRITURA);
                        this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1,
                                                           FenixConstants.MODO_EJECUCION_ESCRITURA);
                        setEsLecturaFechaEfectivaEnvioCobro(Boolean.FALSE);
                    } else {
                        logger.warning("El estado del contrato no es el correcto.");
                    }
                }
                break;
            case FenixConstants.PA11_VALIDAR_ENVIO_COBRO:
                setMostrarFormularioDesembolsos(Boolean.FALSE);
                setMostrarFormularioEnvioCobro(Boolean.TRUE);
                setMostrarBotones(Boolean.FALSE);
                setDeshabilitarGuardarBtn(Boolean.TRUE);
                setRenderBtnProceso(Boolean.FALSE);
                setEsCampoSoloLectura(Boolean.TRUE);
                setReadOnlyFondo(Boolean.TRUE);
                setEsMonedaSoloLectura(Boolean.TRUE);
                setEsLecturaFechaEfectivaEnvioCobro(Boolean.TRUE);
                
                this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabDatosGenerales(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                break;
                case FenixConstants.PA11_VALIDAR_TASA_FECHA:
                    setMostrarFormularioDesembolsos(Boolean.FALSE);
                    setMostrarFormularioEnvioCobro(Boolean.TRUE);
                    setMostrarBotones(Boolean.FALSE);
                    setDeshabilitarGuardarBtn(Boolean.TRUE);
                    setRenderBtnProceso(Boolean.FALSE);
                    setEsCampoSoloLectura(Boolean.TRUE);
                    setReadOnlyFondo(Boolean.TRUE);
                    setEsMonedaSoloLectura(Boolean.TRUE);
                    setEsLecturaFechaEfectivaEnvioCobro(Boolean.TRUE);
                    
                    this.showTabAsignacionRecursos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabDatosGenerales(Boolean.TRUE, Boolean.TRUE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCargos(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabTransferencias(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabReservaFondos(Boolean.FALSE, Boolean.FALSE, 0, FenixConstants.MODO_EJECUCION_LECTURA);
                    this.showTabCondicionesFinancieras(Boolean.FALSE, Boolean.FALSE, 1, FenixConstants.MODO_EJECUCION_LECTURA);
                    break;
            default:
                break;
            } 
        }else{
            logger.warning("No hay un idTareaBPM.");
            setMostrarFormularioDesembolsos(Boolean.TRUE);
            setMostrarFormularioEnvioCobro(Boolean.FALSE);
        }
        
        logger.warning("Mostrar formulario desembolsos: " + getMostrarFormularioDesembolsos());
        logger.warning("Mostrar formulario envio al cobro: " + getMostrarFormularioEnvioCobro());
        
        TFin = System.currentTimeMillis(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = (TFin - TInicio)/ 1000; //Calculamos los milisegundos de diferencia
        logger.warning("Termina metodo configurarFormularioContrato con una duracion de: "+tiempo+" segundos");
    }
    
    private void showTabAsignacionRecursos(boolean tab, boolean disclosed, Integer render, int modoEjecution) {
        this.tabAsignacionRecursos = tab;
        this.disclosedTabAsignacionRecursos = disclosed;
        this.renderTabAsignacionRecursos = render;
        this.modoEjecucionTabAsignacionRecursos = modoEjecution;
        
        //IMPORTANTE: Seteo de path de regiones de tabs. si se remueve mostrara un ADF-FACES.
        if(render.compareTo(1)==0){
            setTaskFlowIdAsignRecRoot("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/contratoDesembolso/asignacionrecursos/asignacionRecursosBTF.xml#asignacionRecursosBTF");
        }else{
            setTaskFlowIdAsignRecRoot("");
        }
        
        if(render.compareTo(1)==0){
            multiTaskFlowAsignRecBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_AsignRec");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/contratoDesembolso/asignacionrecursos/asignacionRecursosBTF.xml",
                                                    "asignacionRecursosBTF"));
            multiTaskFlowAsignRecBindings.add(taskFlowBindingAttributes);
        }
    }
    
    private void showTabDatosGenerales(boolean tab, boolean disclosed, Integer render, int modoEjecution) {
        this.tabDatosGenerales = tab;
        this.disclosedTabDatosGenerales = disclosed;
        this.renderTabDatosGenerales = render;
        this.modoEjecucionTabDatosGenerales = modoEjecution;
        
        //IMPORTANTE: Seteo de path de regiones de tabs. si se remueve mostrara un ADF-FACES.
        if(render.compareTo(1)==0){
            setTaskFlowIdDatosGralesRoot("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/datosgenerales/datosGeneralesDesembolsoBTF.xml#datosGeneralesDesembolsoBTF");
        }else{
            setTaskFlowIdDatosGralesRoot("");
        }
        
        if(render.compareTo(1)==0){
            multiTaskFlowDatosGralesBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_DatosGrales");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/datosgenerales/datosGeneralesDesembolsoBTF.xml",
                                                    "datosGeneralesDesembolsoBTF"));
            multiTaskFlowDatosGralesBindings.add(taskFlowBindingAttributes);
        }
    }
    
    private void showTabCondicionesFinancieras(boolean tab, boolean disclosed, Integer render, int modoEjecution) {
        this.tabCondicionesFinancieras = tab;
        this.disclosedTabCondicionesFinancieras = disclosed;
        this.renderTabCondicionesFinancieras = render;
        this.modoEjecucionTabCondicionesFinancieras = modoEjecution;
        
        //IMPORTANTE: Seteo de path de regiones de tabs. si se remueve mostrara un ADF-FACES.
        if(render.compareTo(1)==0){
            setTaskFlowIdCondFinanRoot("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/condicionesfinancieras/condicionesFinancierasBTF.xml#condicionesFinancierasBTF");
        }else{
            setTaskFlowIdCondFinanRoot("");
        }
        
        if(render.compareTo(1)==0){
            multiTaskFlowCondFinanBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_CondFinan");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/condicionesfinancieras/condicionesFinancierasBTF.xml",
                                                    "condicionesFinancierasBTF"));
            multiTaskFlowCondFinanBindings.add(taskFlowBindingAttributes);
        }
    }
    
    private void showTabLiquidarContrato(boolean tab, boolean disclosed, Integer render, int modoEjecution) {
        this.tabLiquidarContrato = tab;
        this.disclosedTabLiquidarContrato = disclosed;
        this.renderTabLiquidarContrato = render;
        this.modoEjecucionTabLiquidarContrato = modoEjecution;
        
        //IMPORTANTE: Seteo de path de regiones de tabs. si se remueve mostrara un ADF-FACES.
        if(render.compareTo(1)==0){
            setTaskFlowIdLiqContratoRoot("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/liquidarcontrato/liquidarContratoBTF.xml#liquidarContrato");
        }else{
            setTaskFlowIdLiqContratoRoot("");
        }
        
        if(render.compareTo(1)==0){
            multiTaskFlowLiquidarContBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_LiquidarCont");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/liquidarcontrato/liquidarContratoBTF.xml",
                                                    "liquidarContrato"));
            multiTaskFlowLiquidarContBindings.add(taskFlowBindingAttributes);
        }
    }
    
    private void showTabCargos(boolean tab, boolean disclosed, Integer render, int modoEjecution) {
        this.tabCargos = tab;
        this.disclosedTabCargos = disclosed;
        this.renderTabCargos = render;
        this.modoEjecucionTabCargos = modoEjecution;
        
        //IMPORTANTE: Seteo de path de regiones de tabs. si se remueve mostrara un ADF-FACES.
        if(render.compareTo(1)==0){
            setTaskFlowIdCargosRoot("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/cargos/cargosBTF.xml#cargosBTF");
        }else{
            setTaskFlowIdCargosRoot("");
        }
        
        if(render.compareTo(1)==0){
            multiTaskFlowCargosBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_Cargos");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/cargos/cargosBTF.xml",
                                                    "cargosBTF"));
            multiTaskFlowCargosBindings.add(taskFlowBindingAttributes);
        }
    }
    
    private void showTabTransferencias(boolean tab, boolean disclosed, Integer render, int modoEjecution) {
        this.tabTransferencias = tab;
        this.disclosedTabTransferencias = disclosed;
        this.renderTabTransferencias = render;
        this.modoEjecucionTabTransferencias = modoEjecution;
        
        //IMPORTANTE: Seteo de path de regiones de tabs. si se remueve mostrara un ADF-FACES.
        if(render.compareTo(1)==0){
            setTaskFlowIdTransfRoot("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/transferencias/transferenciasBTF.xml#transferenciasBTF");
        }else{
            setTaskFlowIdTransfRoot("");
        }
        
        if(render.compareTo(1)==0){
            multiTaskFlowTransferBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_Transferencias");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/transferencias/transferenciasBTF.xml",
                                                    "transferenciasBTF"));
            multiTaskFlowTransferBindings.add(taskFlowBindingAttributes);
        }
    }
    
    private void showTabReservaFondos(boolean tab, boolean disclosed, Integer render, int modoEjecution) {
        this.tabReservaFondos = tab;
        this.disclosedTabReservaFondos = disclosed;
        this.renderTabReservaFondos = render;
        this.modoEjecucionTabReservaFondos = modoEjecution;
        
        //IMPORTANTE: Seteo de path de regiones de tabs. si se remueve mostrara un ADF-FACES.
        if(render.compareTo(1)==0){
            setTaskFlowIdResFondosRoot("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/reservaDeFondos/reservaFondosBTF.xml#reservaFondosBTF");
        }else{
            setTaskFlowIdResFondosRoot("");
        }
        
        if(render.compareTo(1)==0){
            multiTaskFlowReservaFondosBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_ReservaFondos");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/reservaDeFondos/reservaFondosBTF.xml",
                                                    "reservaFondosBTF"));
            multiTaskFlowReservaFondosBindings.add(taskFlowBindingAttributes);
        }
    }
    
    private void showTabConsolidacionTransferencias(boolean tab, boolean disclosed, Integer render, int modoEjecucion){
        this.tabConsolidarTransferencias = tab;
        this.disclosedTabConsolidarTransferencias = disclosed;
        this.renderTabConsolidarTransferencias = render;
        this.modoEjecucionTabConsolidacionTransferencias = modoEjecucion;
        
        //IMPORTANTE: Seteo de path de regiones de tabs. si se remueve mostrara un ADF-FACES.
        if(render.compareTo(1)==0){
            setTaskFlowIdConsTransfRoot("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/consolidatransferencias/consolidarTransderenciasBTF.xml#consolidarTransderenciasBTF");
        }else{
            setTaskFlowIdConsTransfRoot("");
        }
        
        if(render.compareTo(1)==0){
            multiTaskFlowConsTransfBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_ConsTransf");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/consolidatransferencias/consolidarTransderenciasBTF.xml",
                                                    "consolidarTransderenciasBTF"));
            multiTaskFlowConsTransfBindings.add(taskFlowBindingAttributes);
        }
    }
    
    private void showTabCoberturas(boolean tab, boolean disclosed, boolean render){
        this.setTabCoberturas(tab);
        this.setDisclosedTabCoberturas(disclosed);
        this.setRenderTabCoberturas(render);
        
        //IMPORTANTE: Seteo de path de regiones de tabs. si se remueve mostrara un ADF-FACES.
        if(render){
            setTaskFlowIdCoberturasRoot("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/coberturas/coberturasBTF.xml#coberturasBTF");
        }else{
            setTaskFlowIdCoberturasRoot("");
        }
        
        if(render){
            multiTaskFlowCoberturasBindings = new ArrayList<TaskFlowBindingAttributes>();
            
            TaskFlowBindingAttributes taskFlowBindingAttributes = new TaskFlowBindingAttributes();
            taskFlowBindingAttributes.setId("ID_Coberturas");
            taskFlowBindingAttributes.setTaskFlowId(new TaskFlowId("/WEB-INF/org/bcie/fenix/view/gestorDesembolsos/coberturas/coberturasBTF.xml",
                                                    "coberturasBTF"));
            multiTaskFlowCoberturasBindings.add(taskFlowBindingAttributes);
        }
    }
    
    public String obtenerDescripcionMoneda(){
      logger.warning("*Inf, inicia metodo obtenerDescripcionMoneda");  
        BindingContainer bindings = null;
        OperationBinding operBinding = null;
        String descripcionMoneda = null;
        
        try{
          bindings  = ADFUtils.getBindingContainer();
          operBinding = bindings.getOperationBinding("obtenerDescripcionMoneda");         
          operBinding.execute();            
        }catch(Exception e){
            logger.warning("OperationBinding obtenerDescripcionMoneda devuelve errores ->", e);
            JSFUtils.addFacesErrorMessage("Error al obtener Descripcion Moneda->"+e.getMessage());
        } 
        
        if(!operBinding.getErrors().isEmpty()){
            logger.warning("Error operBinding obtenerDescripcionMoneda ->"+operBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage("Error operBinding obtenerDescripcionMoneda ->"+operBinding.getErrors().toString());
        }else{
             descripcionMoneda = (String)operBinding.getResult();  
        }
        
      logger.warning("*Inf, termina metodo obtenerDescripcionMoneda"); 
      return descripcionMoneda;
    } 
    
    
    public void recuperarDiasInhabiles(String descripcionMoneda){
     logger.warning("*Inf, inicia metodo recuperarDiasInhabiles");
        BindingContainer bindings = null;
        OperationBinding operBinding = null;
        
        try{
          bindings  = ADFUtils.getBindingContainer();
          operBinding = bindings.getOperationBinding("obtenerDiasInhabilesByMoneda2");
          operBinding.getParamsMap().put("descripcionMoneda", descripcionMoneda);
          operBinding.execute();            
        }catch(Exception e){
            logger.warning("OperationBinding obtenerDiasInhabilesMoneda devuelve errores ->", e);
            JSFUtils.addFacesErrorMessage("Error al obtener dias inhabiles->"+e.getMessage());
        }
        
        if(!operBinding.getErrors().isEmpty()){
            logger.warning("Error operBinding obtenerDiasInhabilesMoneda ->"+operBinding.getErrors().toString());
            JSFUtils.addFacesErrorMessage("Error operBinding obtenerDiasInhabilesMoneda ->"+operBinding.getErrors().toString());
        }else{
             diasInhabiles = (ArrayList)operBinding.getResult();  
        }
        
     logger.warning("*Inf, termina metodo recuperarDiasInhabiles");
    }
    
    
    
    public Boolean esUsuarioResponsableDeOperacion(){
        logger.warning("*Inf, inicia metodo esUsuarioResponsableDeOperacion para el nivel de ContratoDesembolso");
       
        Long idOperacion = null;
        String usuarioSession = "";
        BindingContainer bindings = null;
        OperationBinding operBinding = null;
        Boolean esResponsableOperacion = Boolean.FALSE;
                
        //recuperamos el id de la operacionn del taskflow
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")) {
            idOperacion = (Long)JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
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
    
    
    
    public void asignarModoEjecucionTabs(Integer idTareaBPM){
        
    }

    public void setIdTipoMoneda(Integer idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }

    public Integer getIdTipoMoneda() {
        return idTipoMoneda;
    }

    public void setIdFondoLinea(String idFondoLinea) {
        this.idFondoLinea = idFondoLinea;
    }

    public String getIdFondoLinea() {
        return idFondoLinea;
    }

    public void setIdEstadoContrato(Integer idEstadoContrato) {
        this.idEstadoContrato = idEstadoContrato;
    }

    public Integer getIdEstadoContrato() {
        return idEstadoContrato;
    }

    public void setDescripcionFondo(String descripcionFondo) {
        this.descripcionFondo = descripcionFondo;
    }

    public String getDescripcionFondo() {
        return descripcionFondo;
    }

    public void setEsCampoSoloLectura(Boolean esCampoSoloLectura) {
        this.esCampoSoloLectura = esCampoSoloLectura;
    }

    public Boolean getEsCampoSoloLectura() {
        return esCampoSoloLectura;
    }

    public void setMostrarBotones(Boolean ocultarBotones) {
        logger.warning("setMostrarBotones : "+ocultarBotones);
        this.mostrarBotones = ocultarBotones;
    }

    public Boolean getMostrarBotones() {
        return mostrarBotones;
    }

    public void setDeshabilitarBotones(Boolean deshabilitarBotones) {
        this.deshabilitarBotones = deshabilitarBotones;
    }

    public Boolean getDeshabilitarBotones() {
        return deshabilitarBotones;
    }

    public void setMostrarTabsContrato(Boolean mostrarTabsContrato) {
        this.mostrarTabsContrato = mostrarTabsContrato;
    }

    public Boolean getMostrarTabsContrato() {
        return mostrarTabsContrato;
    }

    public void setFechaEstimadaDesembolsar(Date fechaEstimadaDesembolsar) {
        this.fechaEstimadaDesembolsar = fechaEstimadaDesembolsar;
    }

    public Date getFechaEstimadaDesembolsar() {
        return fechaEstimadaDesembolsar;
    }

    public void setMontoDisponibleLineaCredito(BigDecimal montoDisponibleLineaCredito) {
        this.montoDisponibleLineaCredito = montoDisponibleLineaCredito;
    }

    public BigDecimal getMontoDisponibleLineaCredito() {
        return montoDisponibleLineaCredito;
    }

    public void setCuentaConNoObjecion(String cuentaConNoObjecion) {
        this.cuentaConNoObjecion = cuentaConNoObjecion;
    }

    public String getCuentaConNoObjecion() {
        return cuentaConNoObjecion;
    }

    public void setScrCliente(Integer scrCliente) {
        this.scrCliente = scrCliente;
    }

    public Integer getScrCliente() {
        return scrCliente;
    }

    public void setMoraCliente(String moraCliente) {
        this.moraCliente = moraCliente;
    }

    public String getMoraCliente() {
        return moraCliente;
    }

    public void setSectorCliente(Long sectorCliente) {
        this.sectorCliente = sectorCliente;
    }

    public Long getSectorCliente() {
        return sectorCliente;
    }

    public void setMontoMaximoDesembolso(BigDecimal montoMaximoDesembolso) {
        this.montoMaximoDesembolso = montoMaximoDesembolso;
    }

    public BigDecimal getMontoMaximoDesembolso() {
        return montoMaximoDesembolso;
    }

    public void setMontoMinimoDesembolso(BigDecimal montoMinimoDesembolso) {
        this.montoMinimoDesembolso = montoMinimoDesembolso;
    }

    public BigDecimal getMontoMinimoDesembolso() {
        return montoMinimoDesembolso;
    }

    public void setTasaMaximaDesembolso(BigDecimal tasaMaximaDesembolso) {
        this.tasaMaximaDesembolso = tasaMaximaDesembolso;
    }

    public BigDecimal getTasaMaximaDesembolso() {
        return tasaMaximaDesembolso;
    }

    public void setTasaMinimaDesembolso(BigDecimal tasaMinimaDesembolso) {
        this.tasaMinimaDesembolso = tasaMinimaDesembolso;
    }

    public BigDecimal getTasaMinimaDesembolso() {
        return tasaMinimaDesembolso;
    }

    public void setRenderTabAsignacionRecursos(Integer renderTabAsignacionRecursos) {
        this.renderTabAsignacionRecursos = renderTabAsignacionRecursos;
    }

    public Integer getRenderTabAsignacionRecursos() {
        return renderTabAsignacionRecursos;
    }

    public void setRenderTabDatosGenerales(Integer renderTabDatosGenerales) {
        this.renderTabDatosGenerales = renderTabDatosGenerales;
    }

    public Integer getRenderTabDatosGenerales() {
        return renderTabDatosGenerales;
    }

    public void setRenderTabCondicionesFinancieras(Integer renderTabCondicionesFinancieras) {
        this.renderTabCondicionesFinancieras = renderTabCondicionesFinancieras;
    }

    public Integer getRenderTabCondicionesFinancieras() {
        return renderTabCondicionesFinancieras;
    }

    public void setRenderTabCargos(Integer renderTabCargos) {
        this.renderTabCargos = renderTabCargos;
    }

    public Integer getRenderTabCargos() {
        return renderTabCargos;
    }

    public void setRenderTabTransferencias(Integer renderTabTransferencias) {
        this.renderTabTransferencias = renderTabTransferencias;
    }

    public Integer getRenderTabTransferencias() {
        return renderTabTransferencias;
    }

    public void setDeshabilitarGuardarBtn(Boolean deshabilitarGuardarBtn) {
        logger.warning("setDeshabilitarGuardarBtn: "+deshabilitarGuardarBtn);
        this.deshabilitarGuardarBtn = deshabilitarGuardarBtn;
    }

    public Boolean getDeshabilitarGuardarBtn() {        
        return deshabilitarGuardarBtn;
    }

    public void setDeshabilitarProcesoBtn(Boolean deshabilitarProcesoBtn) {
        this.deshabilitarProcesoBtn = deshabilitarProcesoBtn;
    }

    public Boolean getDeshabilitarProcesoBtn() {
        return deshabilitarProcesoBtn;
    }

    public void setModoEjecucionTabAsignacionRecursos(Integer modoEjecucionTabAsignacionRecursos) {
        this.modoEjecucionTabAsignacionRecursos = modoEjecucionTabAsignacionRecursos;
    }

    public Integer getModoEjecucionTabAsignacionRecursos() {
        return modoEjecucionTabAsignacionRecursos;
    }

    public Boolean getDisclosedTabAsignacionRecursos() {
        return disclosedTabAsignacionRecursos;
    }

    public void setDisclosedTabAsignacionRecursos(Boolean disclosedTabAsignacionRecursos) {
        this.disclosedTabAsignacionRecursos = disclosedTabAsignacionRecursos;
    }

    public Boolean getDisclosedTabDatosGenerales() {
        return disclosedTabDatosGenerales;
    }

    public void setDisclosedTabDatosGenerales(Boolean disclosedTabDatosGenerales) {
        this.disclosedTabDatosGenerales = disclosedTabDatosGenerales;
    }

    public Boolean getDisclosedTabCondicionesFinancieras() {
        return disclosedTabCondicionesFinancieras;
    }

    public void setDisclosedTabCondicionesFinancieras(Boolean disclosedTabCondicionesFinancieras) {
        this.disclosedTabCondicionesFinancieras = disclosedTabCondicionesFinancieras;
    }

    public Boolean getDisclosedTabCargos() {
        return disclosedTabCargos;
    }

    public void setDisclosedTabCargos(Boolean disclosedTabCargos) {
        this.disclosedTabCargos = disclosedTabCargos;
    }

    public Boolean getDisclosedTabTransferencias() {
        return disclosedTabTransferencias;
    }

    public void setDisclosedTabTransferencias(Boolean disclosedTabTransferencias) {
        this.disclosedTabTransferencias = disclosedTabTransferencias;
    }

    public Integer getModoEjecucionTabDatosGenerales() {
        return modoEjecucionTabDatosGenerales;
    }

    public void setModoEjecucionTabDatosGenerales(Integer modoEjecucionTabDatosGenerales) {
        this.modoEjecucionTabDatosGenerales = modoEjecucionTabDatosGenerales;
    }

    public Integer getModoEjecucionTabTransferencias() {
        return modoEjecucionTabTransferencias;
    }

    public void setModoEjecucionTabTransferencias(Integer modoEjecucionTabTransferencias) {
        this.modoEjecucionTabTransferencias = modoEjecucionTabTransferencias;
    }

    public Integer getModoEjecucionTabConsolidacionTransferencias() {
        return modoEjecucionTabConsolidacionTransferencias;
    }

    public void setModoEjecucionTabConsolidacionTransferencias(Integer modoEjecucionTabConsolidacionTransferencias) {
        this.modoEjecucionTabConsolidacionTransferencias = modoEjecucionTabConsolidacionTransferencias;
    }

    public Boolean getTabAsignacionRecursos() {
        return tabAsignacionRecursos;
    }

    public void setTabAsignacionRecursos(Boolean tabAsignacionRecursos) {
        this.tabAsignacionRecursos = tabAsignacionRecursos;
    }

    public Boolean getTabDatosGenerales() {
        return tabDatosGenerales;
    }

    public void setTabDatosGenerales(Boolean tabDatosGenerales) {
        this.tabDatosGenerales = tabDatosGenerales;
    }

    public Boolean getTabCondicionesFinancieras() {
        return tabCondicionesFinancieras;
    }

    public void setTabCondicionesFinancieras(Boolean tabCondicionesFinancieras) {
        this.tabCondicionesFinancieras = tabCondicionesFinancieras;
    }

    public Boolean getTabCargos() {
        return tabCargos;
    }

    public void setTabCargos(Boolean tabCargos) {
        this.tabCargos = tabCargos;
    }

    public Boolean getTabTransferencias() {
        return tabTransferencias;
    }

    public void setTabTransferencias(Boolean tabTransferencias) {
        this.tabTransferencias = tabTransferencias;
    }

    public Integer getValorAnteriorTab() {
        return valorAnteriorTab;
    }

    public void setValorAnteriorTab(Integer valorAnteriorTab) {
        this.valorAnteriorTab = valorAnteriorTab;
    }

    public Boolean getGuardaTab() {
        return guardaTab;
    }

    public void setGuardaTab(Boolean guardaTab) {
        this.guardaTab = guardaTab;
    }

    public Integer getRenderTabLiquidarContrato() {
        return renderTabLiquidarContrato;
    }

    public void setRenderTabLiquidarContrato(Integer renderTabLiquidarContrato) {
        this.renderTabLiquidarContrato = renderTabLiquidarContrato;
    }

    public Integer getRenderTabConsolidarTransferencias() {
        return renderTabConsolidarTransferencias;
    }

    public void setRenderTabConsolidarTransferencias(Integer renderTabConsolidarTransferencias) {
        this.renderTabConsolidarTransferencias = renderTabConsolidarTransferencias;
    }

    public Boolean getDisclosedTabLiquidarContrato() {
        return disclosedTabLiquidarContrato;
    }

    public void setDisclosedTabLiquidarContrato(Boolean disclosedTabLiquidarContrato) {
        this.disclosedTabLiquidarContrato = disclosedTabLiquidarContrato;
    }

    public Boolean getDisclosedTabConsolidarTransferencias() {
        return disclosedTabConsolidarTransferencias;
    }

    public void setDisclosedTabConsolidarTransferencias(Boolean disclosedTabConsolidarTransferencias) {
        this.disclosedTabConsolidarTransferencias = disclosedTabConsolidarTransferencias;
    }

    public Boolean getTabLiquidarContrato() {
        return tabLiquidarContrato;
    }

    public void setTabLiquidarContrato(Boolean tabLiquidarContrato) {
        this.tabLiquidarContrato = tabLiquidarContrato;
    }

    public Boolean getTabConsolidarTransferencias() {
        return tabConsolidarTransferencias;
    }

    public void setTabConsolidarTransferencias(Boolean tabConsolidarTransferencias) {
        this.tabConsolidarTransferencias = tabConsolidarTransferencias;
    }
    
    public Integer getModoEjecucionTabCargos() {
        return this.modoEjecucionTabCargos;
    }
    
    public void setModoEjecucionTabCargos(int modoEjecucionTabCargos) {
        this.modoEjecucionTabCargos = modoEjecucionTabCargos;
    }

    public void setEsMonedaSoloLectura(Boolean esMonedaSoloLectura) {
        this.esMonedaSoloLectura = esMonedaSoloLectura;
    }

    public Boolean getEsMonedaSoloLectura() {
        return esMonedaSoloLectura;
    }

    public void setListaErrores(List<String> listaErrores) {
        this.listaErrores = listaErrores;
    }

    public List<String> getListaErrores() {
        return listaErrores;
    }

    public void setModoEjecucionTabCondicionesFinancieras(Integer modoEjecucionTabCondicionesFinancieras) {
        this.modoEjecucionTabCondicionesFinancieras = modoEjecucionTabCondicionesFinancieras;
    }

    public Integer getModoEjecucionTabCondicionesFinancieras() {
        return modoEjecucionTabCondicionesFinancieras;
    }    

    public void setRenderTabReservaFondos(Integer renderTabReservaFondos) {
        this.renderTabReservaFondos = renderTabReservaFondos;
    }

    public Integer getRenderTabReservaFondos() {
        return renderTabReservaFondos;
    }
    
    public void setModoEjecucionTabReservaFondos(Integer modoEjecucionTabReservaFondos) {
        this.modoEjecucionTabReservaFondos = modoEjecucionTabReservaFondos;
    }

    public Integer getModoEjecucionTabReservaFondos() {
        return modoEjecucionTabReservaFondos;
    }
    
    public void setDisclosedTabReservaFondos(Boolean disclosedTabReservaFondos) {
        this.disclosedTabReservaFondos = disclosedTabReservaFondos;
    }

    public Boolean getDisclosedTabReservaFondos() {
        return disclosedTabReservaFondos;
    }
    
    public void setTabReservaFondos(Boolean tabReservaFondos) {
        this.tabReservaFondos = tabReservaFondos;
    }

    public Boolean getTabReservaFondos() {
        return tabReservaFondos;
    }

    public void setEsErrorMontoLimite(Boolean esErrorMontoLimite) {
        this.esErrorMontoLimite = esErrorMontoLimite;
    }

    public Boolean getEsErrorMontoLimite() {
        return esErrorMontoLimite;
    }

    public void setEsMontoMaximo(Boolean esMontoMaximo) {
        this.esMontoMaximo = esMontoMaximo;
    }

    public Boolean getEsMontoMaximo() {
        return esMontoMaximo;
    }

    public void setEsMontoMinimo(Boolean esMontoMinimo) {
        this.esMontoMinimo = esMontoMinimo;
    }

    public Boolean getEsMontoMinimo() {
        return esMontoMinimo;
    }

    public void setMontoConvertido(BigDecimal montoConvertido) {
        this.montoConvertido = montoConvertido;
    }

    public BigDecimal getMontoConvertido() {
        return montoConvertido;
    }

    public void setIdTareaBPMTF(Integer idTareaBPMTF) {
        this.idTareaBPMTF = idTareaBPMTF;
    }

    public Integer getIdTareaBPMTF() {
        return idTareaBPMTF;
    }

    public void setRenderBtnProceso(Boolean renderBtnProceso) {
        this.renderBtnProceso = renderBtnProceso;
    }

    public Boolean getRenderBtnProceso() {
        return renderBtnProceso;
    }

    public void setReadOnlyFondo(Boolean readOnlyFondo) {
        this.readOnlyFondo = readOnlyFondo;
    }

    public Boolean getReadOnlyFondo() {
        return readOnlyFondo;
    }

    public void setModoEjecucionTabLiquidarContrato(Integer modoEjecucionTabLiquidarContrato) {
        this.modoEjecucionTabLiquidarContrato = modoEjecucionTabLiquidarContrato;
    }

    public Integer getModoEjecucionTabLiquidarContrato() {
        return modoEjecucionTabLiquidarContrato;
    }

    public void setIdSolicitudTF(Long idSolicitudTF) {
        this.idSolicitudTF = idSolicitudTF;
    }

    public Long getIdSolicitudTF() {
        return idSolicitudTF;
    }

    public void setIdOperacionTF(Long idOperacionTF) {
        this.idOperacionTF = idOperacionTF;
    }

    public Long getIdOperacionTF() {
        return idOperacionTF;
    }
    
    public void setMontoProgramMesVigente(BigDecimal montoProgramMesVigente) {
        this.montoProgramMesVigente = montoProgramMesVigente;
    }

    public BigDecimal getMontoProgramMesVigente() {
        return montoProgramMesVigente;
    }

    public void setMostrarFormularioDesembolsos(Boolean mostrarFormularioDesembolsos) {
        this.mostrarFormularioDesembolsos = mostrarFormularioDesembolsos;
    }

    public Boolean getMostrarFormularioDesembolsos() {
        return mostrarFormularioDesembolsos;
    }

    public void setMostrarFormularioEnvioCobro(Boolean mostrarFormularioEnvioCobro) {
        this.mostrarFormularioEnvioCobro = mostrarFormularioEnvioCobro;
    }

    public Boolean getMostrarFormularioEnvioCobro() {
        return mostrarFormularioEnvioCobro;
    }

    public void setIdTareaNoMostrarMontoProgramado(Integer idTareaNoMostrarMontoProgramado) {
        this.idTareaNoMostrarMontoProgramado = idTareaNoMostrarMontoProgramado;
    }

    public Integer getIdTareaNoMostrarMontoProgramado() {
        return idTareaNoMostrarMontoProgramado;
    }

    public Long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }

    public void setIdContratoDesembolso(Long idContratoDesembolso) {
        this.idContratoDesembolso = idContratoDesembolso;
    }

    public Long getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Long idLinea) {
        this.idLinea = idLinea;
    }

    public Boolean getRenderTabCoberturas() {
        return renderTabCoberturas;
    }

    public void setRenderTabCoberturas(Boolean renderTabCoberturas) {
        this.renderTabCoberturas = renderTabCoberturas;
    }

    public Boolean getDisclosedTabCoberturas() {
        return disclosedTabCoberturas;
    }

    public void setDisclosedTabCoberturas(Boolean disclosedTabCoberturas) {
        this.disclosedTabCoberturas = disclosedTabCoberturas;
    }

    public Boolean getTabCoberturas() {
        return tabCoberturas;
    }
    public void setMostrarBotonGuardarTabs(Boolean mostrarBotonGuardarTabs) {
        this.mostrarBotonGuardarTabs = mostrarBotonGuardarTabs;
    }
    
        public Boolean getMostrarBotonGuardarTabs() {
        return mostrarBotonGuardarTabs;
}

    public void setTabCoberturas(Boolean tabCoberturas) {
        this.tabCoberturas = tabCoberturas;
    }

    public void setTaskFlowIdAsignRecRoot(String taskFlowIdAsignRecRoot) {
        this.taskFlowIdAsignRecRoot = taskFlowIdAsignRecRoot;
    }

    public String getTaskFlowIdAsignRecRoot() {
        return taskFlowIdAsignRecRoot;
    }

    public void setTaskFlowIdDatosGralesRoot(String taskFlowIdDatosGralesRoot) {
        this.taskFlowIdDatosGralesRoot = taskFlowIdDatosGralesRoot;
    }

    public String getTaskFlowIdDatosGralesRoot() {
        return taskFlowIdDatosGralesRoot;
    }

    public void setTaskFlowIdCondFinanRoot(String taskFlowIdCondFinanRoot) {
        this.taskFlowIdCondFinanRoot = taskFlowIdCondFinanRoot;
    }

    public String getTaskFlowIdCondFinanRoot() {
        return taskFlowIdCondFinanRoot;
    }

    public void setTaskFlowIdTransfRoot(String taskFlowIdTransfRoot) {
        this.taskFlowIdTransfRoot = taskFlowIdTransfRoot;
    }

    public String getTaskFlowIdTransfRoot() {
        return taskFlowIdTransfRoot;
    }

    public void setTaskFlowIdCargosRoot(String taskFlowIdCargosRoot) {
        this.taskFlowIdCargosRoot = taskFlowIdCargosRoot;
    }

    public String getTaskFlowIdCargosRoot() {
        return taskFlowIdCargosRoot;
    }

    public void setTaskFlowIdConsTransfRoot(String taskFlowIdConsTransfRoot) {
        this.taskFlowIdConsTransfRoot = taskFlowIdConsTransfRoot;
    }

    public String getTaskFlowIdConsTransfRoot() {
        return taskFlowIdConsTransfRoot;
    }

    public void setTaskFlowIdResFondosRoot(String taskFlowIdResFondosRoot) {
        this.taskFlowIdResFondosRoot = taskFlowIdResFondosRoot;
    }

    public String getTaskFlowIdResFondosRoot() {
        return taskFlowIdResFondosRoot;
    }

    public void setTaskFlowIdLiqContratoRoot(String taskFlowIdLiqContratoRoot) {
        this.taskFlowIdLiqContratoRoot = taskFlowIdLiqContratoRoot;
    }

    public String getTaskFlowIdLiqContratoRoot() {
        return taskFlowIdLiqContratoRoot;
    }

    public void setTaskFlowIdCoberturasRoot(String taskFlowIdCoberturasRoot) {
        this.taskFlowIdCoberturasRoot = taskFlowIdCoberturasRoot;
    }

    public String getTaskFlowIdCoberturasRoot() {
        return taskFlowIdCoberturasRoot;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public void setErrorServicioValidarAsgnacion(String errorServicioValidarAsgnacion) {
        this.errorServicioValidarAsgnacion = errorServicioValidarAsgnacion;
    }

    public String getErrorServicioValidarAsgnacion() {
        return errorServicioValidarAsgnacion;
    }

    public void setPglRefrescarErrorValidarAsignacion(RichPanelGroupLayout pglRefrescarErrorValidarAsignacion) {
        this.pglRefrescarErrorValidarAsignacion = pglRefrescarErrorValidarAsignacion;
    }

    public RichPanelGroupLayout getPglRefrescarErrorValidarAsignacion() {
        return pglRefrescarErrorValidarAsignacion;
    }
    
    public void setErrorRequiereValidacionAsignacion(String errorRequiereValidacionAsignacion) {
        this.errorRequiereValidacionAsignacion = errorRequiereValidacionAsignacion;
    }

    public String getErrorRequiereValidacionAsignacion() {
        
        if(errorRequiereValidacionAsignacion != null)
            JSFUtils.addFacesErrorMessage(errorRequiereValidacionAsignacion);
        
               errorRequiereValidacionAsignacion = null;
               
        return errorRequiereValidacionAsignacion;
    }
    
    public Boolean validarCurrentRowDetalleDesembolsosOperacion(){
        logger.warning("Inicia metodo validarCurrentRowDetalleDesembolsosOperacion.");
        Boolean existeRegistro = Boolean.FALSE;
        Row rowDetalleDesembolsosOperacion = null;
        
        try {
            rowDetalleDesembolsosOperacion =
                ADFUtils.getDCBindingContainer().findIteratorBinding("DetalleDesembolsosOperacionVOIterator").getViewObject().getCurrentRow();
        } catch (Exception e) {
            logger.warning("ERROR al recuperar el VO del iterador de DetalleDesembolsosOperacionVOIterator.", e);
        }
        
        if(null != rowDetalleDesembolsosOperacion){
            logger.warning("Existe CurrentRow de DetalleDesembolsosOperacionVO.");
            asignarDatosOperacionClienteBeanSesion(rowDetalleDesembolsosOperacion);
            existeRegistro = Boolean.TRUE;
        }else{
            logger.warning("NO Existe CurrentRow de DetalleDesembolsosOperacionVO.");
        }
        
        logger.warning("Termina metodo validarCurrentRowDetalleDesembolsosOperacion.");
        return existeRegistro;
    }
    
    public Boolean asignarDatosOperacionClienteBeanSesion(Row row) {
        logger.warning("Inicia metodo asignarDatosOperacionClienteBeanSesion.");
        Boolean resultado = Boolean.FALSE;
        String operacion = null, cliente = null, producto = null, pais = null, flexcube = null,
            noObjecion = null, scr = null, mora = null, diasMora = null,
            moneda = null, sectorDescripcion = null, descSectorMercado = null;
        
        Long IdOperacion = null, IdCliente = null, Sector = null, IdProducto = null, IdSectorMercado = null;
        
        Double MontoMora = null, MontoFormalizado = null, MontoDesembolsar = null;
        
        oracle.jbo.domain.Date VigenciaNoObjecionLaft = null;

        if (null == row) {
            logger.warning("El row de DetalleDesembolsosOperacionVO es NULL.");
            return resultado;
        }

        DatosSesionManagedBean datosSesionBean =
            (DatosSesionManagedBean) JSFUtils.resolveExpression("#{DatosSesionManagedBean}");

            logger.warning("Recuperando datos de DetalleDesembolsosOperacionVO.");
            try{
                operacion = (String) row.getAttribute("Operacion");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Operacion.", e);
            }
            
            try{
                cliente = (String) row.getAttribute("Cliente");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Cliente.", e);
            }
            
            try{
                producto = (String) row.getAttribute("Producto");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Producto.", e);
            }
            
            try{
                pais = (String) row.getAttribute("Pais");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Pais.", e);
            }
            
            try{
                flexcube = (String) row.getAttribute("Flexcube");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Flexcube.", e);
            }
            
            try{
                noObjecion = (String) row.getAttribute("NoObjecion");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo NoObjecion.", e);
            }
            
            try{
                scr = (String) row.getAttribute("Scr");
                if(null == scr || "".compareTo(scr) == 0 || scr.trim().length() == 0){
                    logger.warning("El scr es nulo o cadena vacia, se le asigna '0'");
                    scr = "0";
                }
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Scr.", e);
            }
            
            try{
                mora = (String) row.getAttribute("Mora");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Mora.", e);
            }
            
            try{
                diasMora = (String) row.getAttribute("DiasMora");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo DiasMora.", e);
            }
            
            try{
                moneda = (String) row.getAttribute("Moneda");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Moneda.", e);
            }
            
            try{
                sectorDescripcion = (String) row.getAttribute("SectorDescripcion");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo SectorDescripcion.", e);
            }
            
            try{
                descSectorMercado = (String) row.getAttribute("DescSectorMercado");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo DescSectorMercado.", e);
            }
            
            try{
                IdOperacion = (Long) row.getAttribute("IdOperacion");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo DescSectorMercado.", e);
            }
            
            try{
                IdCliente = (Long) row.getAttribute("IdCliente");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo IdCliente.", e);
            }
            
            try{
                Sector = (Long) row.getAttribute("Sector");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo Sector.", e);
            }
            
            try{
                IdProducto = (Long) row.getAttribute("IdProducto");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo IdProducto.", e);
            }
            
            try{
                IdSectorMercado = (Long) row.getAttribute("IdSectorMercado");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo IdSectorMercado.", e);
            }
            
            try{
                MontoMora = (Double) row.getAttribute("MontoMora");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo MontoMora.", e);
            }
            
            try{
                MontoFormalizado = (Double) row.getAttribute("MontoFormalizado");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo MontoFormalizado.", e);
            }
            
            try{
                MontoDesembolsar = (Double) row.getAttribute("MontoDesembolsar");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo MontoDesembolsar.", e);
            }
            
            try{
                VigenciaNoObjecionLaft = (oracle.jbo.domain.Date) row.getAttribute("VigenciaNoObjecionLaft");
            }catch(Exception e){
                logger.warning("ERROR al recuperar atributo VigenciaNoObjecionLaft.", e);
            }
        
        logger.warning("operacion" + operacion);
        logger.warning("cliente" + cliente);
        logger.warning("producto" + producto);
        logger.warning("pais" + pais);
        logger.warning("flexcube" + flexcube);
        logger.warning("noObjecion" + noObjecion);
        logger.warning("scr" + scr);
        logger.warning("mora" + mora);
        logger.warning("diasMora" + diasMora);
        logger.warning("moneda" + moneda);
        logger.warning("sectorDescripcion" + sectorDescripcion);
        logger.warning("descSectorMercado" + descSectorMercado);
        logger.warning("IdOperacion" + IdOperacion);
        logger.warning("IdCliente" + IdCliente);
        logger.warning("Sector" + Sector);
        logger.warning("IdProducto" + IdProducto);
        logger.warning("IdSectorMercado" + IdSectorMercado);
        logger.warning("MontoMora" + MontoMora);
        logger.warning("MontoFormalizado" + MontoFormalizado);
        logger.warning("MontoDesembolsar" + MontoDesembolsar);
        logger.warning("VigenciaNoObjecionLaft" + VigenciaNoObjecionLaft);
        
        logger.warning("Asignando datos de DetalleDesembolsosOperacionVO a DatosSesionManagedBean.");
        datosSesionBean.setOperacion(operacion);
        datosSesionBean.setCliente(cliente);
        datosSesionBean.setProducto(producto);
        datosSesionBean.setPais(pais);
        datosSesionBean.setFlexcube(flexcube);
        datosSesionBean.setNoObjecion(noObjecion);
        datosSesionBean.setScr(scr);
        datosSesionBean.setMora(mora);
        datosSesionBean.setDiasMora(diasMora);
        datosSesionBean.setMoneda(moneda);
        datosSesionBean.setSectorDescripcion(sectorDescripcion);
        datosSesionBean.setDescSectorMercado(descSectorMercado);
        datosSesionBean.setIdOperacion(IdOperacion);
        datosSesionBean.setIdCliente(IdCliente);
        datosSesionBean.setSector(Sector);
        datosSesionBean.setIdProducto(IdProducto);
        datosSesionBean.setIdSectorMercado(IdSectorMercado);
        datosSesionBean.setMontoMora(MontoMora);
        datosSesionBean.setMontoFormalizado(MontoFormalizado);
        datosSesionBean.setMontoDesembolsar(MontoDesembolsar);
        datosSesionBean.setVigenciaNoObjecionLaft(VigenciaNoObjecionLaft);

        logger.warning("Termina metodo asignarDatosOperacionClienteBeanSesion.");
        return resultado;
    }

    public void setEsLecturaFechaEfectivaEnvioCobro(Boolean esLecturaFechaEfectivaEnvioCobro) {
        this.esLecturaFechaEfectivaEnvioCobro = esLecturaFechaEfectivaEnvioCobro;
    }

    public Boolean getEsLecturaFechaEfectivaEnvioCobro() {
        return esLecturaFechaEfectivaEnvioCobro;
    }

    public void setErrorPrecargaContratoDesembolso(String errorPrecargaContratoDesembolso) {
        this.errorPrecargaContratoDesembolso = errorPrecargaContratoDesembolso;
    }

    public String getErrorPrecargaContratoDesembolso() {
        logger.warning("Mostrando error en facesMessage.");
        if(errorPrecargaContratoDesembolso != null){            
            JSFUtils.addFacesErrorMessage(errorPrecargaContratoDesembolso);
        }
        
        errorPrecargaContratoDesembolso = null;
        return errorPrecargaContratoDesembolso;
    }

    public void setEsIniciarProcesoDesembolso(Boolean esIniciarProcesoDesembolso) {
        this.esIniciarProcesoDesembolso = esIniciarProcesoDesembolso;
    }

    public Boolean getEsIniciarProcesoDesembolso() {
        return esIniciarProcesoDesembolso;
    }

    public void setEsVerificarRegistroDetalle(String esVerificarRegistroDetalle) {
        this.esVerificarRegistroDetalle = esVerificarRegistroDetalle;
    }

    public String getEsVerificarRegistroDetalle() {
        logger.warning("Inicia metodo getEsVerificarRegistroDetalle.");
        Map datosDetalleOperacionMap = new HashMap();
        Map<String, Object> params = new HashMap<String, Object>();
        Boolean existeRegistroDetalleOperacion = Boolean.FALSE;
        DatosSesionManagedBean datosSesionBean =
            (DatosSesionManagedBean) JSFUtils.resolveExpression("#{DatosSesionManagedBean}");
        
        String operacion = null, cliente = null, producto = null, pais = null, flexcube = null,
            noObjecion = null, scr = null, mora = null, diasMora = null,
            moneda = null, sectorDescripcion = null, descSectorMercado = null;
        
        Long IdOperacion = null, IdCliente = null, Sector = null, IdProducto = null, IdSectorMercado = null;
        
        Double MontoMora = null, MontoFormalizado = null, MontoDesembolsar = null;
        
        oracle.jbo.domain.Date VigenciaNoObjecionLaft = null;
        
        /* if(!(idTareaBPMTF.compareTo(FenixConstants.CG10_GESTOR_DESEMBOLSOS)==0)){
            logger.warning("Tarea diferente de Gestor de desembolsos.");
            return null;
        } */
        
        //Validar si existe CurrentRow de DetalleDesembolsosOperacionVO, en caso de no existir, Se crea nuevo CurrentRow con valores de bean de sesion
        existeRegistroDetalleOperacion = validarCurrentRowDetalleDesembolsosOperacion();
        if(existeRegistroDetalleOperacion){
            return null;
        }
        
        try {
            logger.warning("Recuperando valores de Managed bean de sesion.");
            operacion = datosSesionBean.getOperacion();
            cliente = datosSesionBean.getCliente();
            producto = datosSesionBean.getProducto();
            pais = datosSesionBean.getPais();
            flexcube = datosSesionBean.getFlexcube();
            noObjecion = datosSesionBean.getNoObjecion();
            scr = datosSesionBean.getScr();
            mora = datosSesionBean.getMora();
            diasMora = datosSesionBean.getDiasMora();
            moneda = datosSesionBean.getMoneda();
            sectorDescripcion = datosSesionBean.getSectorDescripcion();
            descSectorMercado = datosSesionBean.getDescSectorMercado();
            IdOperacion = datosSesionBean.getIdOperacion();
            IdCliente = datosSesionBean.getIdCliente();
            Sector = datosSesionBean.getSector();
            IdProducto = datosSesionBean.getIdProducto();
            IdSectorMercado = datosSesionBean.getIdSectorMercado();
            MontoMora = datosSesionBean.getMontoMora();
            MontoFormalizado = datosSesionBean.getMontoFormalizado();
            MontoDesembolsar = datosSesionBean.getMontoDesembolsar();
            VigenciaNoObjecionLaft = datosSesionBean.getVigenciaNoObjecionLaft();
        } catch (Exception e) {
            logger.warning("ERROR al recuperar atributos de DatosSesionManagedBean.", e);
        }
        
        logger.warning("Asignando valores a Mapa de datos de detalle de operacion.");
        datosDetalleOperacionMap.put("operacion", operacion);
        datosDetalleOperacionMap.put("cliente", cliente);
        datosDetalleOperacionMap.put("producto", producto);
        datosDetalleOperacionMap.put("pais", pais);
        datosDetalleOperacionMap.put("flexcube", flexcube);
        datosDetalleOperacionMap.put("noObjecion", noObjecion);
        datosDetalleOperacionMap.put("scr", scr);
        datosDetalleOperacionMap.put("mora", mora);
        datosDetalleOperacionMap.put("diasMora", diasMora);
        datosDetalleOperacionMap.put("moneda", moneda);
        datosDetalleOperacionMap.put("sectorDescripcion", sectorDescripcion);
        datosDetalleOperacionMap.put("descSectorMercado", descSectorMercado);
        datosDetalleOperacionMap.put("IdOperacion", IdOperacion);
        datosDetalleOperacionMap.put("IdCliente", IdCliente);
        datosDetalleOperacionMap.put("Sector", Sector);
        datosDetalleOperacionMap.put("IdProducto", IdProducto);
        datosDetalleOperacionMap.put("IdSectorMercado", IdSectorMercado);
        datosDetalleOperacionMap.put("MontoMora", MontoMora);
        datosDetalleOperacionMap.put("MontoFormalizado", MontoFormalizado);
        datosDetalleOperacionMap.put("MontoDesembolsar", MontoDesembolsar);
        datosDetalleOperacionMap.put("VigenciaNoObjecionLaft", VigenciaNoObjecionLaft);
        
        params.put("datosDetalleOperacionMap", datosDetalleOperacionMap);
        try{
            execute(params, "recargarRegistroDetalleOperacion");
        }catch(Exception e){
            logger.warning("ERROR al ejecutar el OperBinding recargarRegistroDetalleOperacion.", e);
        }
        
        logger.warning("Termino metodo getEsVerificarRegistroDetalle.");
        return null;
    }
    
    public boolean obtenerRequiereValidacionAsignacionSolicitud(){
        logger.warning("Ingresa al metodo obtenerRequiereValidacionAsignacionSolicitud");
        Boolean respuesta = Boolean.FALSE;
        String errorVerificarValidacion = null;
        
        /*
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("requiereValidacionAsignacionSolicitud");
            operationBinding.getParamsMap().put("idSolicitud", getIdSolicitudTF());
            operationBinding.getParamsMap().put("idOperacion", getIdOperacionTF());

            logger.warning("Ejecutando OperationBinding de requiereValidacionAsignacionSolicitud");
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                errorRequiereValidacionAsignacion =
                    "Hubo un error al ejecutar el operBinding requiereValidacionAsignacionSolicitud." +
                    operationBinding.getErrors().toString();
                logger.warning("Hubo un error al ejecutar el operBinding requiereValidacionAsignacionSolicitud." +
                               operationBinding.getErrors().toString());
            } else {
                errorRequiereValidacionAsignacion = null;
                try {
                    respuesta = (Boolean) operationBinding.getResult();
                } catch (Exception ex) {
                    logger.warning("ERROR al recuperar el resultado de Requiere validacion.", ex);
                }
            }

            errorVerificarValidacion = (String) ADFUtils.getBoundAttributeValue("MensajeErrorServicio");
            if (null != errorVerificarValidacion && !errorVerificarValidacion.equals("")) {
                logger.warning("Mensaje de errorValidacion: " + errorVerificarValidacion);
                JSFUtils.addFacesErrorMessage("ConfigurarFormularioContrato(): " + errorVerificarValidacion);
                ADFUtils.setBoundAttributeValue("MensajeErrorServicio", null);
            }

        } catch (Exception e) {
            logger.warning("No se pudo ejecutar el OperationBinding de requiereValidacionAsignacionSolicitud");
            JSFUtils.addFacesErrorMessage(e.getMessage());
        }
        */
        
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("consultarVerificarAsignacionRecursos");
            operationBinding.getParamsMap().put("idSolicitud", getIdSolicitudTF());

            logger.warning("Ejecutando OperationBinding de consultarVerificarAsignacionRecursos");
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                errorRequiereValidacionAsignacion =
                    "Hubo un error al ejecutar el operBinding consultarVerificarAsignacionRecursos." +
                    operationBinding.getErrors().toString();
                logger.warning("Hubo un error al ejecutar el operBinding consultarVerificarAsignacionRecursos." +
                               operationBinding.getErrors().toString());
            } else {
                errorRequiereValidacionAsignacion = null;
                try {
                    respuesta = (Boolean) operationBinding.getResult();
                } catch (Exception ex) {
                    logger.warning("ERROR al recuperar el resultado de Requiere validacion.", ex);
                }
            }
        } catch (Exception e) {
            logger.warning("No se pudo ejecutar el OperationBinding de consultarVerificarAsignacionRecursos");
            JSFUtils.addFacesErrorMessage(e.getMessage());
        }

        logger.warning("Valor respuesta a retornar: " + respuesta);
        logger.warning("Finaliza al metodo obtenerRequiereValidacionAsignacionSolicitud");
        return respuesta;
    }

    public void setMultiTaskFlowAsignRecBindings(List<TaskFlowBindingAttributes> multiTaskFlowAsignRecBindings) {
        this.multiTaskFlowAsignRecBindings = multiTaskFlowAsignRecBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowAsignRecBindings() {
        return multiTaskFlowAsignRecBindings;
    }

    public void setMultiTaskFlowDatosGralesBindings(List<TaskFlowBindingAttributes> multiTaskFlowDatosGralesBindings) {
        this.multiTaskFlowDatosGralesBindings = multiTaskFlowDatosGralesBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowDatosGralesBindings() {
        return multiTaskFlowDatosGralesBindings;
    }

    public void setMultiTaskFlowCondFinanBindings(List<TaskFlowBindingAttributes> multiTaskFlowCondFinanBindings) {
        this.multiTaskFlowCondFinanBindings = multiTaskFlowCondFinanBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowCondFinanBindings() {
        return multiTaskFlowCondFinanBindings;
    }

    public void setMultiTaskFlowCargosBindings(List<TaskFlowBindingAttributes> multiTaskFlowCargosBindings) {
        this.multiTaskFlowCargosBindings = multiTaskFlowCargosBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowCargosBindings() {
        return multiTaskFlowCargosBindings;
    }

    public void setMultiTaskFlowTransferBindings(List<TaskFlowBindingAttributes> multiTaskFlowTransferBindings) {
        this.multiTaskFlowTransferBindings = multiTaskFlowTransferBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowTransferBindings() {
        return multiTaskFlowTransferBindings;
    }

    public void setMultiTaskFlowReservaFondosBindings(List<TaskFlowBindingAttributes> multiTaskFlowReservaFondosBindings) {
        this.multiTaskFlowReservaFondosBindings = multiTaskFlowReservaFondosBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowReservaFondosBindings() {
        return multiTaskFlowReservaFondosBindings;
    }

    public void setMultiTaskFlowLiquidarContBindings(List<TaskFlowBindingAttributes> multiTaskFlowLiquidarContBindings) {
        this.multiTaskFlowLiquidarContBindings = multiTaskFlowLiquidarContBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowLiquidarContBindings() {
        return multiTaskFlowLiquidarContBindings;
    }

    public void setMultiTaskFlowCoberturasBindings(List<TaskFlowBindingAttributes> multiTaskFlowCoberturasBindings) {
        this.multiTaskFlowCoberturasBindings = multiTaskFlowCoberturasBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowCoberturasBindings() {
        return multiTaskFlowCoberturasBindings;
    }

    public void setMultiTaskFlowConsTransfBindings(List<TaskFlowBindingAttributes> multiTaskFlowConsTransfBindings) {
        this.multiTaskFlowConsTransfBindings = multiTaskFlowConsTransfBindings;
    }

    public List<TaskFlowBindingAttributes> getMultiTaskFlowConsTransfBindings() {
        return multiTaskFlowConsTransfBindings;
    }
    
    public void setDiasInhabiles(List<java.util.Date> diasInhabiles) {
        this.diasInhabiles = diasInhabiles;
    }

    public List<java.util.Date> getDiasInhabiles() {
        return diasInhabiles;
    }

    public Boolean getCambiosPendientes() {
        return cambiosPendientes;
    }

    public void setCambiosPendientes(Boolean cambiosPendientes) {
        this.cambiosPendientes = cambiosPendientes;
    }

    public Integer getInstancia1() {
        return instancia1;
    }

    public void setInstancia1(Integer instancia1) {
        this.instancia1 = instancia1;
    }

    public Integer getInstancia2() {
        return instancia2;
    }

    public void setInstancia2(Integer instancia2) {
        this.instancia2 = instancia2;
    }
    
    public void setFechaEstimadaDesemReadOnly(Boolean fechaEstimadaDesemReadOnly) {
        this.fechaEstimadaDesemReadOnly = fechaEstimadaDesemReadOnly;
    }

    public Boolean getFechaEstimadaDesemReadOnly() {
        return fechaEstimadaDesemReadOnly;
    }
    
    public void setEstatusTareaBpm(String estatusTareaBpm) {
        this.estatusTareaBpm = estatusTareaBpm;
    }

    public String getEstatusTareaBpm() {
        return estatusTareaBpm;
    }

}
