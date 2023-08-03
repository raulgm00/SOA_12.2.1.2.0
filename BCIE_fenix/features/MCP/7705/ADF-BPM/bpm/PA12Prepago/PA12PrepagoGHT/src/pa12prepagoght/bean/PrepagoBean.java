package pa12prepagoght.bean;

import java.io.IOException;

import java.io.Serializable;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.sql.SQLException;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import java.util.List;

import java.util.Map;

import javax.faces.model.SelectItem;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.jbo.JboException;

import oracle.xml.parser.v2.XMLElement;

import org.apache.myfaces.trinidad.model.DateListProvider;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.BPMUtils;

import org.bcie.fenix.common.utils.JSFUtils;

import org.bcie.fenix.common.view.beans.FenixPanelBean;
import org.bcie.fenix.view.observacioncargoprepago.ObservacionCargoPrepagoBean;

import org.w3c.dom.NodeList;

public class PrepagoBean extends FenixPanelBean  implements Serializable{
    @SuppressWarnings("compatibility:-6289394161311057925")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String CODIGO_PRODUCTO = "CodigoProducto";
    private static final String NUM_SERIE_DOCUMENTO="IdFlujo";
    private static final String CODIGO_ROL="CodigoRol";
    private static final String CODIGO_PREPAGO="idPrepago";
    
    private static final String SECTOR = "Sector";
    private static final String TIPO_GARANTIA = "TipoGarantia";
    
    private static final String PREPAGO_INCUMPLIDO = "prepagoIncumplido";
    private static final String PREPAGO_RECIBIDO = "prepagoRecibido";
    private static final String REQUIERE_INFORMACION_COFI = "requiereMasInfoCOFI";
    
    private static final String MONTO_CARGO_DEFECTO = "500";
    private static final String STATE = "state";
    private static final String COMPLETED = "COMPLETED";
    private static final String ASSIGNED = "ASSIGNED";
    
    private String codigoTarea;
    private oracle.jbo.domain.Number codigoOperacion;
    private Long operacionId;
    private String idOperacion;
    private String instanciaProceso;
    private Boolean esLineaGlobal;
    private String codigoRol;
    private oracle.jbo.domain.Number numeroSerieDocumento;
    private String codigoPrepago;
    private Integer codigoProducto;
    private Long idLineaCredito;
    private Long idPrepago;
    
    private String loginUsuario;
    private String nombreUsuario;
    
    private Integer sectorInstitucional;
    private Integer tipoGarantia;
    
    private String prepagoIncumplido;
    private String prepagoRecibido;
    private String requiereMasInfoCOFI;
    
    private Date fechaAmortizacion;

    //variable para lista del tipo de moneda
    private List<SelectItem> listaTipoMoneda;
    
    //variable para consultarInformacionPrepagoBTF
    private List<String> listaLineaCredito = new ArrayList<String>();
    private List<String> listaContratoDesembolso = new ArrayList<String>();
    private RichRegion regionConsultarInformacionBinding;
    private RichSelectOneRadio tipoCapturaBinding;
    private RichSelectOneRadio selectOneRadioTipoCapturaBinding;
    private RichRegion regionCondicionEspecialBinding;
    
    private String opSalidaTarea;
    private String mensajeFinalizar;
    private String mensajeMasInformacion;
    private Boolean isValidaComision; 
    private Long idComision;
    private String descripcionComision;

    //variable para check pago total (Ingresar solicitud de prepago)
    private Boolean esLecturaPagoTotal;
    
    //variables para atributos de prepago
    private Integer idTcaTipoMoneda = null;
    private Integer idTcaTipoResolucion = null;
    private Timestamp fechaPrepago = null;
    private Timestamp fechaRenovacion = null; 
    private BigDecimal montoPrepago = null;
    
    
    private DateListProvider disabledDaysValue;
    
    //variables para Validar penalidad de prepago
    private String cargoTramitePrepago = null;
    private BigDecimal tasaLibor = null;
    private BigDecimal prime = null;
    private Timestamp fechaReferencia = null;
    private Timestamp fechaCalculoDefinitivo = null;
    //variables para guardar el valor de cargoTramitePrepago
    private BigDecimal montoCargoTramite = null;
    private Integer idTcaTipoMonedaTramite = null;
    
    //Sector institucional
    public static final Integer SECTOR_PUBLICO = 1;
    public static final Integer SECTOR_PRIVADO = 2;
    
    //Tipo garantia
    public static final Integer GARANTIA_NO_SOBERANA = 1;
    public static final Integer GARANTIA_SOBERANA = 2;
    
    //Tipo cliente
    public static final Integer CLIENTE_PRE10_2008_PUBLICO_SOBERANO = 1;
    public static final Integer CLIENTE_PRE10_2008_PRIVADO_PUBLICO_NO_SOBERANO = 2;
    public static final Integer CLIENTE_PRE28_2003_PUBLICO = 3;
    public static final Integer CLIENTE_PRE28_2003_PRIVADO = 4;
    
    //Tipos de resolucion
    public static final Integer PRE10_2008 = 1;
    public static final Integer PRE28_2003 = 2;
    public static final Integer OTRAS_CONDICIONES = 3;
    
    //Codigos de tasas en flexcube
    public static final String CODIGO_TASA_LIBOR_6_MESES_FLEXCUBE = "1104";
    public static final String CODIGO_TASA_PRIME_FLEXCUBE = "1002";
    
    //Codigo externo de dolater
    public static final String CODIGO_EXTERNO_MONEDA_USD = "USD";
    private RichInputText initForm;
    
    //variable para hacer visible la columna "Intereses" en la tabla "Detalle de la penalidad" 
    //en la pantalla Validar penalidad de prepago, se cumpla la RN_11
    private Boolean esVisibleIntereses = null;
    private RichSelectBooleanCheckbox pagoTotalHeaderBinding;
    
    private Boolean esPagoTotalHeader;
    
    private List<Date> diasInhabilesMoneda;
    private RichOutputText initFormObtenerListas;

    private String state;
    private Boolean esTareaCompletada = Boolean.FALSE;

    public PrepagoBean() {
        super();
        if (logger == null)
        {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void getPayloadInformation()
        throws WorkflowException, IOException, SQLException {
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();

        if (logger.isWarning()) // if(LOGGER.isFiner())
        {
            //get the payload as a simple string, useful for debugging
            java.io.StringWriter writer = new java.io.StringWriter();
            xmlPayload.print(writer);
            String payloadAsString = writer.toString();
            logger.log(ADFLogger.WARNING, payloadAsString);
        }
        
        NodeList nl;
          
        nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
        if (nl.getLength() > 0) {
            codigoTarea = nl.item(0).getTextContent();
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
        if(nl.getLength() > 0){
            idOperacion = nl.item(0).getTextContent();
            
            try {
                if((idOperacion != null) && (idOperacion.trim().length() > 0))
                    codigoOperacion = new oracle.jbo.domain.Number(Long.valueOf(idOperacion));
                    setOperacionId(Long.valueOf(idOperacion));
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, "Error al inicializar el valor de codigoOperacion: " + e.getMessage());
            }
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_ROL);
        if(nl.getLength() > 0){
            codigoRol = nl.item(0).getTextContent();
        }
        nl = xmlPayload.getElementsByTagName(NUM_SERIE_DOCUMENTO);
        if (nl.getLength() > 0){
            try {
                setNumeroSerieDocumento(new oracle.jbo.domain.Number(nl.item(0).getTextContent()));
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, "Error al inicializar el valor de IdFlujo: " + e.getMessage());
            }
        }
        
        nl = xmlPayload.getElementsByTagName(CODIGO_PRODUCTO);
        if(nl != null && (nl.getLength() > 0)) {
            String codigoProducto = nl.item(0).getTextContent().trim();
            if(codigoProducto != null && codigoProducto != "") {
                setCodigoProducto(Integer.parseInt(nl.item(0).getTextContent()));
            }
        }
        logger.warning("Validacion para operacion 1672 y codigoPrepago nulo. valor idOperacion= "+idOperacion);
        
        nl = xmlPayload.getElementsByTagName(CODIGO_PREPAGO);
        if(nl.getLength() > 0){
            codigoPrepago = nl.item(0).getTextContent();
        }
        logger.warning("Codigo de Prepago: "+codigoPrepago);
        
        if(((idOperacion == "1672") || (idOperacion.compareTo("1672") == 0)) && (codigoPrepago == null))
        { 
            logger.warning("Validacion para operacion 1672 y codigoPrepago nulo.");
            //---------------------------------------------------------------------- 
            codigoPrepago = "24"; //--Codigo de prepago para la operacion 1672
            try {
                if((codigoPrepago != null) && (codigoPrepago.trim().length() > 0)){
                    setIdPrepago(Long.parseLong(codigoPrepago));
                    try{
                        if(null != getIdPrepago()){
                            long startTime = System.currentTimeMillis();
                            //obtener los atributos de la tabla prepago en base al idPrepago
                            this.obtenerAtributosPrepago(getIdPrepago());
                            long endTime = System.currentTimeMillis();
                            logger.warning("Tiempo de respuesta del metodo  obtenerAtributosPrepago  "
                                           + (endTime - startTime)/1000 + " segundos");
                        }else{
                            logger.severe("IdPrepago es nulo, no se pueden obtener los atributos de prepago");
                        }
                    }catch(Exception e){
                        logger.severe("Error obtenerAtributoPrepago : "+e);
                    }
                     
                }else{
                    logger.log(ADFLogger.WARNING, "codigoPrepago no se recupero , valor es nulo.");
                }
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, "Error al inicializar el valor de codigoPrepago: " + e.getMessage());
            } 
            //----------------------------------------------------------------------
        }
        else
        {
        logger.warning("Asignacion normal codigo de prepago");
        //----------------------------------------------------------------------
        nl = xmlPayload.getElementsByTagName(CODIGO_PREPAGO);
        if(nl.getLength() > 0){
            codigoPrepago = nl.item(0).getTextContent();
            
            try {
                if((codigoPrepago != null) && (codigoPrepago.trim().length() > 0)){
                    setIdPrepago(Long.parseLong(codigoPrepago));
                    try{
                        if(null != getIdPrepago()){
                            long startTime = System.currentTimeMillis();
                            //obtener los atributos de la tabla prepago en base al idPrepago
                            this.obtenerAtributosPrepago(getIdPrepago());
                            long endTime = System.currentTimeMillis();
                            logger.warning("Tiempo de respuesta del metodo  obtenerAtributosPrepago  "
                                           + (endTime - startTime)/1000 + " segundos");
                        }else{
                            logger.severe("IdPrepago es nulo, no se pueden obtener los atributos de prepago");
                        }
                    }catch(Exception e){
                        logger.severe("Error obtenerAtributoPrepago : "+e);
                    }
                    
        //                    try{
        //                        if(null != getCodigoOperacion() && null != getIdTcaTipoResolucion()
        //                                && null != getIdTcaTipoMoneda() && null != getIdPrepago()){
        //                            long startTime = System.currentTimeMillis();
        //                            //obtener los atributos de contrato de desembolsos
        //                            this.obtenerAtributosContratosDesembolsos(getCodigoOperacion().intValue(),getIdTcaTipoResolucion(),
        //                                                                        getIdTcaTipoMoneda(),getIdPrepago());
        //                            long endTime = System.currentTimeMillis();
        //                            logger.warning("Tiempo de respuesta del metodo  obtenerAtributosContratosDesembolsos "
        //                                           + (endTime - startTime)/1000 + " segundos");
        //                        }else{
        //                            logger.severe("No se puede obtener atributos de los contratos de desembolso");
        //                            logger.warning("idOperacion : " + getCodigoOperacion());
        //                            logger.warning("idTcaTipoResolucion : "+getIdTcaTipoResolucion());
        //                            logger.warning("idTcaTipoMoneda : "+getIdTcaTipoMoneda());
        //                            logger.warning("idPrepago : "+getIdPrepago());
        //                        }
        //                    }catch(Exception e){
        //                        logger.severe("Error obtenerAtributosContratosDesembolsos",e);
        //                    }
                }else{
                    logger.log(ADFLogger.WARNING, "codigoPrepago no se recupero , valor es nulo.");
                }
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, "Error al inicializar el valor de codigoPrepago: " + e.getMessage());
            }
        }  
        //----------------------------------------------------------------------
        }
        
        

        
        nl = xmlPayload.getElementsByTagName(PREPAGO_INCUMPLIDO);
        if (nl.getLength() > 0) {
            prepagoIncumplido = nl.item(0).getTextContent();
            if(prepagoIncumplido == null){
                logger.warning("Indicador de prepago Incumplido no recibido, se toma valor por defecto");
                prepagoIncumplido = Boolean.FALSE.toString();
            }
        }else{
            logger.warning("Indicador deprepago Incumplido no recibido, se toma valor por defecto");
            prepagoIncumplido = Boolean.FALSE.toString();
        }
        
        nl = xmlPayload.getElementsByTagName(PREPAGO_RECIBIDO);
        if (nl.getLength() > 0) {
            prepagoRecibido = nl.item(0).getTextContent();
            if(prepagoRecibido == null){
                logger.warning("Indicador de prepago Recibido no recibido, se toma valor por defecto");
                prepagoRecibido = Boolean.FALSE.toString();
            }
        }else{
            logger.warning("Indicador deprepago Recibido no recibido, se toma valor por defecto");
            prepagoRecibido = Boolean.FALSE.toString();
        }
        nl = xmlPayload.getElementsByTagName(REQUIERE_INFORMACION_COFI);
        if (nl.getLength() > 0) {
            requiereMasInfoCOFI = nl.item(0).getTextContent();
            if(requiereMasInfoCOFI == null){
                logger.warning("Indicador de prepago Recibido no recibido, se toma valor por defecto");
                requiereMasInfoCOFI = Boolean.FALSE.toString();
            }
        }else{
            logger.warning("Indicador deprepago Recibido no recibido, se toma valor por defecto");
            requiereMasInfoCOFI = Boolean.FALSE.toString();
        }
        
        setInstanciaProceso(BPMUtils.getCurrentTask().getProcessInfo().getInstanceId());
        setEsLineaGlobal(Boolean.FALSE);
        
        //recuperamos la sesión de usuario 
        if(null != JSFUtils.resolveExpression("#{securityContext.userName}")){
            setLoginUsuario((String)JSFUtils.resolveExpression("#{securityContext.userName}"));
            //recuperar el nombre completo del usuario en sesión
            setNombreUsuario(this.obtenerNombreCompleto(getLoginUsuario()));
        }
    
        obtenerEstadoTarea();
        
        logger.warning("codigoTarea: " + codigoTarea);
        logger.warning("codigoOperacion: " + codigoOperacion);
        logger.warning("instanciaProceso: " + instanciaProceso);
        logger.warning("codigoProducto: " + codigoProducto);
        logger.warning("idPrepago : " + idPrepago);
        logger.warning("numeroSerieDocumento value: " + numeroSerieDocumento);
        logger.warning("usuario en sesion : " + loginUsuario);
        logger.warning("nombre completo del usuario en sesion : " + nombreUsuario);
        logger.warning("prepagoIncumplido: " + prepagoIncumplido);
        logger.warning("prepagoRecibido" + prepagoRecibido);
        logger.warning("requiereMasInfoCOFI" + requiereMasInfoCOFI);
        logger.warning("state: " + state);
    }
    
    public BigDecimal getMontoCargoTramite() {
        return montoCargoTramite;
    }

    public void setMontoCargoTramite(BigDecimal montoCargoTramite) {
        this.montoCargoTramite = montoCargoTramite;
    }

    public Integer getIdTcaTipoMonedaTramite() {
        return idTcaTipoMonedaTramite;
    }

    public void setIdTcaTipoMonedaTramite(Integer idTcaTipoMonedaTramite) {
        this.idTcaTipoMonedaTramite = idTcaTipoMonedaTramite;
    }

    public void setInitForm(RichInputText initForm) {
        this.initForm = initForm;
    }

    public RichInputText getInitForm() {
        logger.log(ADFLogger.WARNING, "Into getInitForm.");
        AttributeBinding fechaVigentePenalizacion;
        AttributeBinding montoCargoTramite;
        AttributeBinding idTcaTipoMonedaTramite;
        AttributeBinding fechaReferencia;
        AttributeBinding tasaLibor;
        AttributeBinding prime;
        try{
        fechaVigentePenalizacion = ADFUtils.findControlBinding("FechaVigentePenalizacion");
        montoCargoTramite = ADFUtils.findControlBinding("MontoCargoTramite");
        idTcaTipoMonedaTramite = ADFUtils.findControlBinding("IdTcaTipoMonedaTramite");
        fechaReferencia = ADFUtils.findControlBinding("FechaReferencia");
        tasaLibor = ADFUtils.findControlBinding("TasaLibor");
        prime = ADFUtils.findControlBinding("Prime");
            
        logger.log(ADFLogger.WARNING, "montoCargoTramite: " + getMontoCargoTramite());
        logger.log(ADFLogger.WARNING, "idTcaTipoMonedaTramite: " + getIdTcaTipoMonedaTramite());
        
        fechaVigentePenalizacion.setInputValue(getFechaCalculoDefinitivo());
        montoCargoTramite.setInputValue(getMontoCargoTramite());
        idTcaTipoMonedaTramite.setInputValue(getIdTcaTipoMonedaTramite());
        fechaReferencia.setInputValue(getFechaReferencia());
        tasaLibor.setInputValue(getTasaLibor());
        prime.setInputValue(getPrime());
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Exception :"+e.getMessage());
        }
        return initForm;
    }
    
    public void setCodigoTarea(String codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    public String getCodigoTarea() {
        return codigoTarea;
    }

    public void setCodigoOperacion(oracle.jbo.domain.Number codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public oracle.jbo.domain.Number getCodigoOperacion() {
        return codigoOperacion;
    }
    
    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getIdOperacion() {
        return idOperacion;
    }
    
    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }
    
    public void setCodigoProducto(Integer codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getCodigoProducto() {
        return codigoProducto;
    }
    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;         
    }
    public String getCodigoRol() {
        return codigoRol;
    }
    public void setEsLineaGlobal(Boolean esLineaGlobal) {
        logger.log(ADFLogger.WARNING, "esLineaGlobal : " + esLineaGlobal);
        this.esLineaGlobal = esLineaGlobal;
    }

    public Boolean getEsLineaGlobal() {
        return esLineaGlobal;
    }
    
    public void setNumeroSerieDocumento(oracle.jbo.domain.Number numeroSerieDocumento) {
        this.numeroSerieDocumento = numeroSerieDocumento;
    }

    public oracle.jbo.domain.Number getNumeroSerieDocumento() {
        return numeroSerieDocumento;
    }
    
    public Long getIdLineaCredito() {
        return idLineaCredito;
    }

    public void setIdLineaCredito(Long idLineaCredito) {
        this.idLineaCredito = idLineaCredito;
    }
    
    public void setSectorInstitucional(Integer sectorInstitucional) {
        this.sectorInstitucional = sectorInstitucional;
    }

    public Integer getSectorInstitucional() {
        return sectorInstitucional;
    }
    
    public void setTipoGarantia(Integer tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }

    public Integer getTipoGarantia() {
        return tipoGarantia;
    }
    
    public void setFechaAmortizacion(Date fechaAmortizacion) {
        this.fechaAmortizacion = fechaAmortizacion;
    }

    public Date getFechaAmortizacion() {
        return fechaAmortizacion;
    }
    
    public void setDisabledDaysValue(DateListProvider disabledDaysValue) {
        this.disabledDaysValue = disabledDaysValue;
    }

    public DateListProvider getDisabledDaysValue() {
        return disabledDaysValue;
    }
    
    public void inicializarIngresarSolicitudPrepago() {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        //metodo para obtener si el produto es 'Linea global de credito'
        logger.log(ADFLogger.WARNING, "inicializarIngresarSolicitudPrepago");
        logger.log(ADFLogger.WARNING, "method obtenerCampo, parameters idProducto : "+getCodigoProducto());
        operationBinding = bindings.getOperationBinding("obtenerCampo");
        operationBinding.getParamsMap().put("idProducto", getCodigoProducto());
        operationBinding.getParamsMap().put("columna", 8);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Boolean resultado =(Boolean)operationBinding.getResult();
            setEsLineaGlobal(resultado);
        }
        //metodo para inicializar 'FormularioPrepagoVO'
        operationBinding = bindings.getOperationBinding("crearRowFormularioPrepago");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            logger.log(ADFLogger.WARNING, "crearRowFormularioPrepago is succesfull");
        }
        //metodo para inicializar 'ContratoDesembolsoPrepagoVo'
//        operationBinding = bindings.getOperationBinding("llenarContratoDesembolsoPrepago");
//        operationBinding.getParamsMap().put("idOperacion",getCodigoOperacion());
//        operationBinding.getParamsMap().put("idTcaTipoResolucion",1);
//        operationBinding.execute();
//        if(!operationBinding.getErrors().isEmpty()){
//            // Manejo de errores
//            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
//        } else {
//            logger.log(ADFLogger.WARNING, "llenarContratoDesembolsoPrepago is succesfull");
//        }
        //metodo para inicializar 'FormularioObservacionPrepagoVO'
        operationBinding = bindings.getOperationBinding("crearRowFormularioObservacionPrepago");
        operationBinding.getParamsMap().put("idTcaTareaBpm",getCodigoTarea());
        operationBinding.getParamsMap().put("loginUsuario",getLoginUsuario());
        operationBinding.getParamsMap().put("nombreUsuario",this.obtenerNombreCompleto(getLoginUsuario()));
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            logger.log(ADFLogger.WARNING, "crearRowFormularioObservacionPrepago is succesfull");
        }
        
        //metodo para obtener el Sector Institucional y el Tipo de Garantia
        
        operationBinding = bindings.getOperationBinding("obtenerSectorGarantia");
        operationBinding.getParamsMap().put("idOperacion", getCodigoOperacion());
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Map<String, Object> resultado = new HashMap<String, Object>();
            resultado =(HashMap<String, Object>) operationBinding.getResult();
            
            Integer sector = (Integer) resultado.get(SECTOR);
            Integer garantia = (Integer) resultado.get(TIPO_GARANTIA);
            
            setSectorInstitucional(sector);
            setTipoGarantia(garantia);
            
            logger.warning("SectorInstitucional: " + getSectorInstitucional());
            logger.warning("TipoGarantia: " + getTipoGarantia());
        }
    }

    public String obtenerNombreCompleto(String loginUsuario){
        String nombreUsuarioCompleto = null;
        HashMap<String,Object> resultado = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("consultarAtributosUsuario");
        operationBinding.getParamsMap().put("login", loginUsuario);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            if(operationBinding.getResult() != null){
            resultado = (HashMap<String,Object>) operationBinding.getResult();
            nombreUsuarioCompleto=(String)resultado.get("nombreUsuario");
            } 
        }
        
        return nombreUsuarioCompleto;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }


    public List<SelectItem> getListaTipoMoneda() {
        return listaTipoMoneda;
    }

    public void setListaTipoMoneda(List<SelectItem> listaTipoMoneda) {
        this.listaTipoMoneda = listaTipoMoneda;
    }
    
    public Timestamp restarDiasFecha(Timestamp fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_WEEK, dias);
        return new Timestamp(calendar.getTime().getTime());
    }
    
    public Integer tipoCliente(Integer idTipoResolucion,Integer sector, Integer tipoGarantia) {
            logger.log(ADFLogger.WARNING, "Dentro de tipoCliente.");
            logger.log(ADFLogger.WARNING, "idTipoResolucion :"+idTipoResolucion);
            logger.log(ADFLogger.WARNING, "sector :"+sector);
            logger.log(ADFLogger.WARNING, "tipoGarantia :"+tipoGarantia);
            Integer result = null;
            
            if (idTipoResolucion != null && sector != null && tipoGarantia != null) {
                if (idTipoResolucion.compareTo(PRE10_2008) == 0 && sector.compareTo(SECTOR_PUBLICO) == 0 && tipoGarantia.compareTo(GARANTIA_SOBERANA) == 0) {
                    result = CLIENTE_PRE10_2008_PUBLICO_SOBERANO;    
                }
                
                if ((idTipoResolucion.compareTo(PRE10_2008) == 0) && (sector.compareTo(SECTOR_PRIVADO) == 0) || ((sector.compareTo(SECTOR_PUBLICO) == 0) && (tipoGarantia.compareTo(GARANTIA_NO_SOBERANA) == 0))) {
                    result = CLIENTE_PRE10_2008_PRIVADO_PUBLICO_NO_SOBERANO;
                }
                
                if (idTipoResolucion.compareTo(PRE28_2003) == 0 && sector.compareTo(SECTOR_PUBLICO) == 0) {
                    result = CLIENTE_PRE28_2003_PUBLICO;    
                }
                
                if (idTipoResolucion.compareTo(PRE28_2003) == 0 && sector.compareTo(SECTOR_PRIVADO) == 0) {
                    result = CLIENTE_PRE28_2003_PRIVADO;
                }
            } else {
                /*
                if(idTipoResolucion == null)
                    logger.log(ADFLogger.WARNING, "idTipoResolucion es null :");
                if(sector == null)
                    logger.log(ADFLogger.WARNING, "sector es null :");
                if(tipoGarantia == null)
                    logger.log(ADFLogger.WARNING, "tipoGarantia es null :");*/             
                if(idTipoResolucion != null && sector != null ){
                    try {
                        if ((idTipoResolucion.compareTo(PRE10_2008) == 0) &&
                            ((sector.compareTo(SECTOR_PRIVADO) == 0) || ((sector.compareTo(SECTOR_PUBLICO) == 0)))) {
                            result = CLIENTE_PRE10_2008_PRIVADO_PUBLICO_NO_SOBERANO;
                        }
                        if (idTipoResolucion.compareTo(PRE28_2003) == 0 && sector.compareTo(SECTOR_PUBLICO) == 0) {
                            result = CLIENTE_PRE28_2003_PUBLICO;
                        }

                        if (idTipoResolucion.compareTo(PRE28_2003) == 0 && sector.compareTo(SECTOR_PRIVADO) == 0) {
                            result = CLIENTE_PRE28_2003_PRIVADO;
                        }
                    } catch (Exception e) {
                        logger.log(ADFLogger.WARNING,
                                   "Error al obtener el tipo de cliente cuando no se tiene tipo de garantia." + e);
                    }
                }else{
                    logger.warning("Parametro de entrada en nulo.");
                }
            }
            
            logger.log(ADFLogger.WARNING, "resultado :"+result);
            return (result);
        }
    
    public  BigDecimal convertirDolaresPorTipoMoneda(Integer idTcaTipoMonedaPrepago){
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        BigDecimal resultado = null;
        try{
        operationBinding = bindings.getOperationBinding("convertirMonedasPrepago");
        operationBinding.getParamsMap().put("claveTipo", idTcaTipoMonedaPrepago);
        operationBinding.getParamsMap().put("codigoA","USD");
        operationBinding.getParamsMap().put("monto", new BigDecimal(500));
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else {
            if(operationBinding.getResult() != null){
                resultado = (BigDecimal) operationBinding.getResult();
                resultado = resultado.setScale(2,RoundingMode.FLOOR);
            } 
        }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR,"Error en el metodo convertirDolaresPorTipoMoneda." + e.getClass() +"."+ e.getMessage());
        }
        return resultado;
    }
    
    public BigDecimal convertirDivisasPorMonedaPrepago(Integer idTcaTipoMonedaPrepago,String codigoExternoMonedaConvertir,
                                                            BigDecimal monto){
        logger.log(ADFLogger.WARNING, "Dentro de convertirDivisasPorMonedaPrepago.");
        logger.log(ADFLogger.WARNING, "idTcaTipoMonedaPrepago :"+idTcaTipoMonedaPrepago);
        logger.log(ADFLogger.WARNING, "codigoExternoMonedaConvertir :"+codigoExternoMonedaConvertir);
        logger.log(ADFLogger.WARNING, "monto :"+monto);
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        BigDecimal resultado = null;
        try{
        operationBinding = bindings.getOperationBinding("convertirMonedasPrepago");
        operationBinding.getParamsMap().put("claveTipo", idTcaTipoMonedaPrepago);
        operationBinding.getParamsMap().put("codigoA",codigoExternoMonedaConvertir);
        operationBinding.getParamsMap().put("monto", monto);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else {
            if(operationBinding.getResult() != null){
                resultado= (BigDecimal) operationBinding.getResult();
            } 
        }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR,"Error en el metodo convertirDivisasPorMonedaPrepago." + e.getClass() +"."+ e.getMessage());
        }
        logger.log(ADFLogger.WARNING, "convertirDivisasPorMonedaPrepago retorna : "+resultado);
        return resultado;
        
    }
    
    public BigDecimal convertirMontoPrepago(BigDecimal montoPrepago){
        //1/20 del 1% calculado sobre el monto del prepago.
        BigDecimal resultado= null;
        try{
        resultado = montoPrepago.multiply(new BigDecimal(0.01));
        resultado = resultado.divide(new BigDecimal(20));
        resultado = resultado.setScale(2,RoundingMode.FLOOR);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR,"Error en el metodo convertirMontoPrepago." + e.getClass() +"."+ e.getMessage());
        }
        return resultado;
    }
    
    public Timestamp obtenerFechaEfectiva(String codigoTasa,String codigoMonedaTasa,Timestamp fechaCalculoDefinitivo){
        logger.log(ADFLogger.WARNING, "Dentro de obtenerFechaEfectiva.");
        logger.log(ADFLogger.WARNING, "codigoTasa: "+codigoTasa);
        logger.log(ADFLogger.WARNING, "codigoMonedaTasa: "+codigoMonedaTasa);
        logger.log(ADFLogger.WARNING, "fechaCalculoDefinitivo :" +fechaCalculoDefinitivo);
        Timestamp fechaEfectiva = null;
        try{
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        operationBinding = bindings.getOperationBinding("obtenerFechaEfectiva");
        operationBinding.getParamsMap().put("codigo", codigoTasa);
        operationBinding.getParamsMap().put("codigoMoneda", codigoMonedaTasa);
        operationBinding.getParamsMap().put("fechaCalculo", fechaCalculoDefinitivo);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else {
            if(operationBinding.getResult() != null){
                fechaEfectiva = (Timestamp) operationBinding.getResult();
            } 
        }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR,"Error en el metodo obtenerFechaEfectiva." + e.getClass() +"."+ e.getMessage());
        }
        logger.log(ADFLogger.WARNING, "obtenerFechaEfectiva retorna : "+fechaEfectiva);
        return fechaEfectiva;
    }
    
    public BigDecimal obtenerTasas(String codigoTasa,String codigoMonedaTasa, Timestamp fechaCalculoDefinitivo){
        logger.log(ADFLogger.WARNING, "Dentro de obtenerTasas.");
        logger.log(ADFLogger.WARNING, "codigoTasa :" +codigoTasa);
        logger.log(ADFLogger.WARNING, "codigoMoneda :" +codigoMonedaTasa);
        logger.log(ADFLogger.WARNING, "fechaCalculoDefinitivo :" +fechaCalculoDefinitivo);
        BigDecimal montoTasa = null;
        try{
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        operationBinding = bindings.getOperationBinding("obtenerTasa");
        operationBinding.getParamsMap().put("codigo", codigoTasa);
        operationBinding.getParamsMap().put("codigoMoneda", codigoMonedaTasa);
            operationBinding.getParamsMap().put("fechaCalculo", fechaCalculoDefinitivo);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else {
            if(operationBinding.getResult() != null){
                montoTasa = (BigDecimal) operationBinding.getResult();
            } 
        }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR,"Error en el metodo obtenerTasas." + e.getClass() +"."+ e.getMessage());
        }
        logger.log(ADFLogger.WARNING, "obtenerTasas retorno :"+montoTasa);
        return montoTasa;
    }
    
    public String obtenerDescripcionMoneda(Integer idTcaTipoMoneda){
        logger.log(ADFLogger.WARNING, "Dentro de obtenerDescripcionMoneda.");
        logger.log(ADFLogger.WARNING, "idTcaTipoMoneda : " +idTcaTipoMoneda);
        String resultado = null;
        try{
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("obtenerDescripcionMoneda");
        operationBinding.getParamsMap().put("idTipoMoneda", idTcaTipoMoneda);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }else {
            if(operationBinding.getResult() != null){
                resultado= (String) operationBinding.getResult();
            } 
        }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR,"Error en el metodo obtenerDescripcionMoneda." + e.getClass() +"."+ e.getMessage());
        }
        return resultado;
    }
    
    public void calcularDetallePenalidad(Long idPrepago,Integer idProducto,Integer idOperacion, Timestamp fechaCalculodefinitivo){
        logger.log(ADFLogger.WARNING, "Dentro de calcularDetallePenalidad.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try{
        operationBinding = bindings.getOperationBinding("llenarDetallePenalidad");
        operationBinding.getParamsMap().put("idPrepago", idPrepago);
        operationBinding.getParamsMap().put("idProducto", idProducto);
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("fechaCalculoDefinitivo", fechaCalculodefinitivo);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR,"Error en el metodo calcularDetallePenalidad." + e.getClass() +"."+ e.getMessage());
        }
    }
    //Si retorna true, se calcula cargo por tramite prepago
    //Si retorn false, no se calcula cargo por tramite prepago
    //FNXII-6085
    @SuppressWarnings("unchecked")
    public Boolean calcularCargoPorTramite(){
        logger.warning("Dentro de calcularCargoPorTramite");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean result = Boolean.FALSE;
        List<BigDecimal> listaPlazo = new ArrayList<BigDecimal>();
        BigDecimal valueComparate = new BigDecimal(1);
        try{
            operationBinding = bindings.getOperationBinding("obtenerListaPlazo");
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                listaPlazo = (List<BigDecimal>) operationBinding.getResult();
                for(BigDecimal plazoAux : listaPlazo){
                    
                    if(plazoAux.compareTo(valueComparate)== 0 || plazoAux.compareTo(valueComparate)== 1){
                        logger.warning("Se encontro plazo igual o mayor a 1");
                        result = Boolean.TRUE;
                        break;
                    }
                }
            }else{
                logger.severe("operationBinding result  es nulo."); 
            }
        }catch(Exception e){
            logger.severe("Error en calcularCargoPorTramite",e);
            
        }
        
        if(!result){
            logger.warning("No se encontraron plazos mayores a 1");
        }
        logger.warning("Fuera de calcularCargoPorTramite, return : "+result);
        return result;
    }
    
    public void calcularIntereses(Long idPrepago,Integer idTcaTipoResolucion,Timestamp fechaPrepago){
        logger.warning("Dentro de calcularIntereses");
        logger.warning("idPrepago: "+idPrepago);
        logger.warning("idTcaTipoResolucion: "+idTcaTipoResolucion);
        logger.warning("fechaPrepago: "+fechaPrepago);
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try{
            operationBinding = bindings.getOperationBinding("llenarCalculoIntereses");
            operationBinding.getParamsMap().put("idPrepago", idPrepago);
            operationBinding.getParamsMap().put("idTcaTipoResolucion", idTcaTipoResolucion);
            operationBinding.getParamsMap().put("fechaPrepago", fechaPrepago);
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
        }catch(Exception e){
            logger.severe("Error en el metodo calcularIntereses.", e);
        }
    
        logger.warning("Fuera de calcularIntereses");
    }
    
    public String moneda_desc;
    public void realizarCalculosInteresesPenalidades(){
        logger.log(ADFLogger.WARNING, "Dentro de realizarCalculosInteresesPenalidades.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String descripcionMonedaPrepago = null;
        BigDecimal resultadoConversion = null;
        BigDecimal resultadoConversionMontoPrepago = null;
        String CargoTramitePrepagoAux = null;
        Timestamp fechaCalculoDefinitivoAux = null;
        Calendar fechaCalculoDefinitivoCalendarAux = null;
        try{
            logger.log(ADFLogger.WARNING, "Id de la moneda :" + idTcaTipoMoneda);
        descripcionMonedaPrepago = obtenerDescripcionMoneda(idTcaTipoMoneda);
            logger.log(ADFLogger.WARNING, "Descripcion de la moneda :" + descripcionMonedaPrepago); 
            moneda_desc = descripcionMonedaPrepago;
        //Inicio de calculo de intereses
        //idTcaTipoMoneda = 2;
        //idTcaTipoResolucion = 1;
        
        //fin de calculo de intereses
        
        //Fecha de cálculo definitivo
        //VA_16
        //El sistema debe realizar el cálculo de la fecha de cálculo definitivo en base a la siguiente fórmula:
        //Fecha de cálculo definitivo= Fecha prepago - días mínimos de notificación de acuerdo al tipo de cliente.
        //PRE-10/2008:
        //•       10 días hábiles, para el sector privado e instituciones del sector público sin garantía soberana del Estado.
        //•       30 días hábiles al sector público con garantía soberana.
        //PRE-28/2003:  
        //•       30 días hábiles antes de su próxima amortización, para el sector público.
        //•       5 días hábiles antes de su próxima amortización, para el sector privado.
        
        if(null != getCodigoOperacion()){
            //Invocar metodo para obtener el Sector Institucional y el Tipo de Garantia
            consultarSectorGarantia(getCodigoOperacion());
            //Consultar tipo de cliente
            Integer cliente = tipoCliente(idTcaTipoResolucion,sectorInstitucional,tipoGarantia);
            Calendar fechaCaluloDefinitivoCalendar = Calendar.getInstance();
            fechaCaluloDefinitivoCalendar.setTime(fechaPrepago);
            if(null != cliente && null != fechaPrepago){

                if(cliente.compareTo(CLIENTE_PRE10_2008_PRIVADO_PUBLICO_NO_SOBERANO) == 0){
                    //10 días hábiles, para el sector privado e instituciones del sector público sin garantía soberana del Estado.
                    //fechaCalculoDefinitivoAux = restarDiasFecha(fechaPrepago, -10);
                    fechaCalculoDefinitivoCalendarAux = fechaCalculoDefinitivo(fechaCaluloDefinitivoCalendar, 10, descripcionMonedaPrepago);
                    fechaCalculoDefinitivoAux = new Timestamp(fechaCalculoDefinitivoCalendarAux.getTime().getTime());
                    setFechaCalculoDefinitivo(fechaCalculoDefinitivoAux);
                }
        
                if(cliente.compareTo(CLIENTE_PRE10_2008_PUBLICO_SOBERANO) == 0){
                    //30 días hábiles al sector público con garantía soberana.
                    //fechaCalculoDefinitivoAux = restarDiasFecha(fechaPrepago, -30);
                    fechaCalculoDefinitivoCalendarAux = fechaCalculoDefinitivo(fechaCaluloDefinitivoCalendar, 30, descripcionMonedaPrepago);
                    fechaCalculoDefinitivoAux = new Timestamp(fechaCalculoDefinitivoCalendarAux.getTime().getTime());
                    setFechaCalculoDefinitivo(fechaCalculoDefinitivoAux);
                }
        
                if(cliente.compareTo(CLIENTE_PRE28_2003_PUBLICO) == 0){
                    //30 días hábiles antes de su próxima amortización, para el sector público.
                    //fechaCalculoDefinitivoAux = restarDiasFecha(fechaPrepago, -30);
                    fechaCalculoDefinitivoCalendarAux = fechaCalculoDefinitivo(fechaCaluloDefinitivoCalendar, 30, descripcionMonedaPrepago);
                    fechaCalculoDefinitivoAux = new Timestamp(fechaCalculoDefinitivoCalendarAux.getTime().getTime());
                    setFechaCalculoDefinitivo(fechaCalculoDefinitivoAux);
            
                }
        
                if(cliente.compareTo(CLIENTE_PRE28_2003_PRIVADO) == 0){
                    //5 días hábiles antes de su próxima amortización, para el sector privado.
                    //fechaCalculoDefinitivoAux = restarDiasFecha(fechaPrepago, -5);
                    fechaCalculoDefinitivoCalendarAux = fechaCalculoDefinitivo(fechaCaluloDefinitivoCalendar, 5, descripcionMonedaPrepago);
                    fechaCalculoDefinitivoAux = new Timestamp(fechaCalculoDefinitivoCalendarAux.getTime().getTime());
                    setFechaCalculoDefinitivo(fechaCalculoDefinitivoAux);
                }
            
            
            }else{
                
                if(cliente == null)
                    logger.log(ADFLogger.WARNING, "cliente es null");
                if(fechaPrepago == null)
                    logger.log(ADFLogger.WARNING, "fechaPrepago es null");
                
            }
        }else{
            
            logger.log(ADFLogger.WARNING, "codigoOperacion es null");
            
        }
            
        //Obtener la tasaLibor por codigo de tasa y codigo externo de moneda
        BigDecimal MontoTasaLibor = obtenerTasas(CODIGO_TASA_LIBOR_6_MESES_FLEXCUBE,CODIGO_EXTERNO_MONEDA_USD, getFechaCalculoDefinitivo());
            if(MontoTasaLibor != null){
                setTasaLibor(MontoTasaLibor);
            }else{
                logger.log(ADFLogger.WARNING, "valor de tasaLibor no se recupero.");
            }
        
        //Obtener la fecha referencia por codigo de tasa y codigo externo de moneda
        Timestamp fechaRefencia = obtenerFechaEfectiva(CODIGO_TASA_LIBOR_6_MESES_FLEXCUBE,CODIGO_EXTERNO_MONEDA_USD, getFechaCalculoDefinitivo());
            if(fechaRefencia != null){
                setFechaReferencia(fechaRefencia);
            }else{
                logger.log(ADFLogger.WARNING, "valor fechaReferencia no se recupero.");
            }
        //Obtener tasa prime por codigo de tasa y codigo externo de moneda
        BigDecimal MontoTasaPrime = obtenerTasas(CODIGO_TASA_PRIME_FLEXCUBE,CODIGO_EXTERNO_MONEDA_USD, getFechaCalculoDefinitivo());
            if(MontoTasaPrime != null){
                setPrime(MontoTasaPrime);
            }else{
                logger.log(ADFLogger.WARNING, "valor de prime no se recupero.");
            }
            //codigoProducto = 1;
            if(null != idPrepago && null != codigoProducto && null != idOperacion){
                Integer idOperacionInteger = Integer.valueOf(idOperacion);
                //llenar la tabla de "detalle de penalidad" para la pantalla "validar penalidad de prepago"
                calcularDetallePenalidad(idPrepago,codigoProducto,idOperacionInteger, getFechaCalculoDefinitivo());
            }else{
                logger.log(ADFLogger.WARNING, "No se realizo el calculo de penalidades.");
                if(null ==  idPrepago)
                    logger.log(ADFLogger.WARNING, "idPrepago es nulo.");
                if(null ==  codigoProducto)
                    logger.log(ADFLogger.WARNING, "codigoProducto es nulo.");
                if(null ==  idOperacion)
                    logger.log(ADFLogger.WARNING, "idOperacion es nulo.");
            }
            
            logger.log(ADFLogger.WARNING, "idTcaTipoMoneda :" + idTcaTipoMoneda);
            logger.log(ADFLogger.WARNING, "idTcaTipoResolucion :" + idTcaTipoResolucion);
            
            //Se cambia la posicion de calcular Cargo pro tramite de prepago, ya que se necesita los plazos de FormularioDetallePenalidadVO 
                
            /*RN_04   El cargo por trámite se debe calcular en base a la resolución a prepagar:
            •       Para la Resolución PRE-10/2008 el sistema debe realizar la conversión de 500 dólares a la moneda del contrato de desembolso. Aplica cuando el prepago sea en una moneda diferente a dólares.
            •       Para la Resolución PRE-28/2003 el sistema debe tomar el cálculo que resulte mayor entre la conversión a la moneda del contrato de desembolso de 500 dólares o 1/20 del 1% calculado sobre el monto del prepago. La conversión aplica cuando el prepago sea en una moneda diferente a dólares.
            El sistema debe actualizar esta información de acuerdo a la fecha actual cuando se haya solicitado la actualización de la penalidad en la tarea Gestionar prepago con el cliente. En caso que al momento de realizar la tarea ya se haya cumplido la fecha de actualización de tasas, deberá mostrar las tasas aplicables a dicha fecha.
            */
            //Cargo por trámite de prepago
            
            /* FNXII-7249
            if (idTcaTipoResolucion.compareTo(FenixConstants.PRE10_2008) == 0){
                logger.warning("Entra a la opcion PRE-10/2008");
                //Recuperar la lista de plazos,para determinar si se calcula un cargo por tramite de prepago para PRE-10/2008
                //Retorna true se calcula el cargo por tramite de prepago
                if(this.calcularCargoPorTramite()){
                    logger.warning("Se calcula cargo por tramite de prepago, plazo es mayor o igual 1");
                    //Se mueve validacion de dolares por incidencia FNXII-4302
                    if (!(idTcaTipoMoneda.compareTo(FenixConstants.TIPO_MONEDA_DOLAR) ==0 )) {
                        logger.log(ADFLogger.WARNING, "Entra en tipo de moneda 1");
                        //Para la Resolución PRE-10/2008 el sistema debe realizar la conversión de 500 dólares a la moneda
                        //del contrato de desembolso. Aplica cuando el prepago sea en una moneda diferente a dólares.
                        resultadoConversion = convertirDolaresPorTipoMoneda(idTcaTipoMoneda);
                        CargoTramitePrepagoAux = resultadoConversion+ " " + descripcionMonedaPrepago + " (500 USD)";
                        setCargoTramitePrepago(CargoTramitePrepagoAux);
                        //llenar variables para guardar el monto y el tipo de moneda
                        setMontoCargoTramite(resultadoConversion);
                        logger.log(ADFLogger.WARNING, "Valor de monto Cargo :" + getMontoCargoTramite());
                        setIdTcaTipoMonedaTramite(idTcaTipoMoneda);
                    } else {
                        // Mantenemos los valores originales por la incidencia FNXII-4302
                        setIdTcaTipoMonedaTramite(idTcaTipoMoneda);
                        setMontoCargoTramite(new BigDecimal(500)); // Por defecto el cargo por tramite de prepago es 500 USD
                        setCargoTramitePrepago(MONTO_CARGO_DEFECTO+ " " + descripcionMonedaPrepago + " (500 USD)"); // Por defecto el cargo por tramite de prepago es 500 USD
                    }
                }else{
                    setMontoCargoTramite(new BigDecimal(0));
                    logger.warning("No se calculo cargo por tramite de prepago, ya que los plazos son menores a 1");
                }
            }*/
            
            if(idTcaTipoResolucion.equals(FenixConstants.PRE28_2003) &&  null != montoPrepago){            
                if (!idTcaTipoMoneda.equals(FenixConstants.TIPO_MONEDA_DOLAR)) {
                    //La conversión aplica cuando el prepago sea en una moneda diferente a dólares.
                    resultadoConversion = convertirDolaresPorTipoMoneda(idTcaTipoMoneda);
                } else {
                    resultadoConversion = new BigDecimal(500);
                }
                
                // Obtenemos el 1/20 del 1% calculado sobre el monto del prepago.
                resultadoConversionMontoPrepago = convertirMontoPrepago(montoPrepago);
                logger.log(ADFLogger.WARNING, "resultadoConversion :" + resultadoConversion);
                logger.log(ADFLogger.WARNING, "resultadoConversionMontoPrepago :" + resultadoConversionMontoPrepago);
                
                //Para la Resolución PRE-28/2003 el sistema debe tomar el cálculo que resulte mayor entre la conversión
                //a la moneda del contrato de desembolso de 500 dólares o 1/20 del 1% calculado sobre el monto del prepago.
                if(resultadoConversion.floatValue() > resultadoConversionMontoPrepago.floatValue()){
                    CargoTramitePrepagoAux = resultadoConversion + " " + descripcionMonedaPrepago + " (500 USD)";
                    setMontoCargoTramite(resultadoConversion);
                    setIdTcaTipoMonedaTramite(idTcaTipoMoneda);
                }else{
                    CargoTramitePrepagoAux = resultadoConversionMontoPrepago + " " + descripcionMonedaPrepago + " (porcentaje 1/20-1%)";
                    setMontoCargoTramite(resultadoConversionMontoPrepago);
                    setIdTcaTipoMonedaTramite(idTcaTipoMoneda);
                }
                
                setCargoTramitePrepago(CargoTramitePrepagoAux);
            }
            


            if(null != idPrepago){
                //llenar la tabla de "calculo de interes" para la pantalla "validar penalidad de prepago"
                try {
                    calcularIntereses(idPrepago,idTcaTipoResolucion,fechaPrepago);
                } catch (Exception e) {
                    logger.log(ADFLogger.WARNING, "Error al llenar la pantalla Validar penalidad de prepago." + e);
                }
            }else{
                logger.log(ADFLogger.WARNING, "No se realizo el calculo de intereses.");
                if(null ==  idPrepago)
                    logger.log(ADFLogger.WARNING, "idPrepago es nulo.");
            }
            
            
            
            
            
        }catch (Exception e) {
            logger.log(ADFLogger.ERROR, e.getMessage());
            JboException ex = new JboException(e);
            ex.addToExceptions(new Exception("Error en realizarCalculosInteresesPenalidades."));
            throw ex;
        }
   
    }

    public void inicializarDatosCargoPrepago() {
        logger.log(ADFLogger.WARNING, "INTO inicializarDatosCargoPrepago.");
        ObservacionCargoPrepagoBean observacionCargoPrepagoBean = (ObservacionCargoPrepagoBean)JSFUtils.resolveExpression("#{pageFlowScope.observacionCargoPrepagoBean}");
        Boolean validaRowCargoPrepago = Boolean.FALSE;
        Long idPrepago = getIdPrepago();
        //this.setCodigoRol("2");
        Integer idTcaRolBpm = Integer.parseInt(getCodigoRol());
        Integer idTcaTareaBpm = Integer.parseInt(getCodigoTarea().toString());
//        String loginUsuario = getLoginUsuario();
//        String nombreUsuario = this.obtenerNombreCompleto(loginUsuario);
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        
        try{
            
            OperationBinding operationBindingValidar = bindings.getOperationBinding("validarObservacionCargoPrepago");
            
            operationBindingValidar.getParamsMap().put("idPrepago", idPrepago);
            operationBindingValidar.getParamsMap().put("idTcaRolBpm", idTcaRolBpm);
            operationBindingValidar.execute();
            if(null != operationBindingValidar.getResult()){
            validaRowCargoPrepago = (Boolean)operationBindingValidar.getResult();
                logger.warning("validaRowCargoPrepago:"+validaRowCargoPrepago);
            }else{
                logger.log(ADFLogger.WARNING, "Valor de retorno operationBindingValidar.getResult() es nulo."); 
            }
            if(validaRowCargoPrepago){
                if(null != observacionCargoPrepagoBean){
                    observacionCargoPrepagoBean.cargarDatosCargoPrepago();
                }
//                OperationBinding operationBinding = bindings.getOperationBinding("llenarFormularioCargoPrepago");
//                operationBinding.execute();
                
            }else{
                try {
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("crearRowFormularioObservacionCargoPrepago");

                    operationBinding.getParamsMap().put("idPrepago", idPrepago);
                    operationBinding.getParamsMap().put("idTcaRolBpm", idTcaRolBpm);
                    operationBinding.getParamsMap().put("idTcaTareaBpm", idTcaTareaBpm);
                    operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
                    operationBinding.getParamsMap().put("nomUsuario", nombreUsuario);
                    operationBinding.execute();
                } catch (Exception e) {
                    logger.log(ADFLogger.WARNING, "Error al ejecutar crearRowFormularioObservacionCargoPrepago.");
                }
            }    
            
            
            this.realizarCalculosInteresesPenalidades();
            
            //validar si se muestra la columna "Intereses" de la tabla "Detalle de la penalidad"
            //validar si se muestra la tabla "Cálculo de intereses"
            this.validarInteresVisible();
        
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al agregar datos en inicializarDatosCargoPrepago." + e.getClass() +"."+ e.getMessage());
        }
    }
    
    /*
     * metodo para validar RN_11 del CU-PA12-Prepago-Validar penalidad de prepago
     * mostrar en la tabla "Detalle de la penalidad" la columna "Intereses" 
     * mostrar la tabla "Cálculo de intereses"
     */
    public void validarInteresVisible(){
        logger.entering(this.getClass().getName(),"Into validarInteresVisible",idTcaTipoResolucion);
        if(null != idTcaTipoResolucion){
            //RN_11	Se mostrará el cálculo de interés en caso de prepagos totales de la PRE-10/2008 
            //y otras condiciones cuando el prepago se efectuará en una fecha diferente a una amortización.
            if (idTcaTipoResolucion.equals(FenixConstants.PRE10_2008)){
                setEsVisibleIntereses(Boolean.TRUE);
            }
        
            if(idTcaTipoResolucion.equals(FenixConstants.OTRAS_CONDICIONES)){
                setEsVisibleIntereses(Boolean.TRUE);
            }
        }
        logger.exiting(this.getClass().getName(),"Into validarInteresVisible", getEsVisibleIntereses());
    }


    public List<String> getListaLineaCredito() {
        return listaLineaCredito;
    }

    public void setListaLineaCredito(List<String> listaLineaCredito) {
        this.listaLineaCredito = listaLineaCredito;
    }

    public List<String> getListaContratoDesembolso() {
        return listaContratoDesembolso;
    }

    public void setListaContratoDesembolso(List<String> listaContratoDesembolso) {
        this.listaContratoDesembolso = listaContratoDesembolso;
    }

    public void setRegionConsultarInformacionBinding(RichRegion regionConsultarInformacionBinding) {
        this.regionConsultarInformacionBinding = regionConsultarInformacionBinding;
    }

    public RichRegion getRegionConsultarInformacionBinding() {
        return regionConsultarInformacionBinding;
    }

    public void setTipoCapturaBinding(RichSelectOneRadio tipoCapturaBinding) {
        this.tipoCapturaBinding = tipoCapturaBinding;
    }

    public RichSelectOneRadio getTipoCapturaBinding() {
        return tipoCapturaBinding;
    }

    public void setSelectOneRadioTipoCapturaBinding(RichSelectOneRadio selectOneRadioTipoCapturaBinding) {
        this.selectOneRadioTipoCapturaBinding = selectOneRadioTipoCapturaBinding;
    }

    public RichSelectOneRadio getSelectOneRadioTipoCapturaBinding() {
        return selectOneRadioTipoCapturaBinding;
    }

    public void setRegionCondicionEspecialBinding(RichRegion regionCondicionEspecialBinding) {
        this.regionCondicionEspecialBinding = regionCondicionEspecialBinding;
    }

    public RichRegion getRegionCondicionEspecialBinding() {
        return regionCondicionEspecialBinding;
    }


    public Boolean getEsLecturaPagoTotal() {
        return esLecturaPagoTotal;
    }

    public void setEsLecturaPagoTotal(Boolean esLecturaPagoTotal) {
        this.esLecturaPagoTotal = esLecturaPagoTotal;
    }
    
    public void setCodigoPrepago(String codigoPrepago) {
        this.codigoPrepago = codigoPrepago;
    }

    public String getCodigoPrepago() {
        return codigoPrepago;
    }

    public void setIdPrepago(Long idPrepago) {
        this.idPrepago = idPrepago;
    }

    public Long getIdPrepago() {
        return idPrepago;
    }


    public Integer getIdTcaTipoMoneda() {
        return idTcaTipoMoneda;
    }

    public void setIdTcaTipoMoneda(Integer idTcaTipoMoneda) {
        this.idTcaTipoMoneda = idTcaTipoMoneda;
    }

    public Integer getIdTcaTipoResolucion() {
        return idTcaTipoResolucion;
    }

    public void setIdTcaTipoResolucion(Integer idTcaTipoResolucion) {
        this.idTcaTipoResolucion = idTcaTipoResolucion;
    }
    
    public void obtenerAtributosContratosDesembolsos(){
        logger.log(ADFLogger.WARNING,"Dentro de obtenerAtributosContratosDesembolsos");
        Map<String,Object> atributosContratosDesembolsosMap = new HashMap<String,Object>();
        List<String> listaContratoDesembolso = new ArrayList<String>();
        List<Long> listaIdLineaCredito = new ArrayList<Long>();

        try{
            
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerAtributosContratosDesembolsos");
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                atributosContratosDesembolsosMap = (Map<String, Object>) operationBinding.getResult();
                logger.warning("obtenerAtributosContratosDesembolsos resultado : " + atributosContratosDesembolsosMap);
                //recuperar listas de Map
                if (null != atributosContratosDesembolsosMap.get("listaContratoDesembolso")) {
                    listaContratoDesembolso =
                        (List<String>) atributosContratosDesembolsosMap.get("listaContratoDesembolso");
                    this.setListaContratoDesembolso(listaContratoDesembolso);
                } else {
                    logger.log(ADFLogger.WARNING, "Lista de contrato de desembolso es nula.");
                }
                if (null != atributosContratosDesembolsosMap.get("listaIdLineaCredito")) {
                    listaIdLineaCredito =
                        (List<Long>) atributosContratosDesembolsosMap.get("listaIdLineaCredito");
                    if(listaIdLineaCredito.size() > 0){
                    this.setIdLineaCredito(listaIdLineaCredito.get(0));
                    logger.log(ADFLogger.WARNING, "Valor de la linea credito ID :" + this.getIdLineaCredito());
                    }else{
                        logger.log(ADFLogger.WARNING, "No existe un id de linea de credito.");
                    }
                } else {
                    logger.log(ADFLogger.WARNING, "Lista de de id de Linea de creito es nula.");
                }
            } else {
                logger.log(ADFLogger.WARNING, "Valor de retorno obtenerAtributosContratosDesembolsos.getResult() es nulo.");
            }
        }catch(Exception e){
            logger.severe("obtenerAtributosContratosDesembolsos : ",e);
        }
    }
    
    public void obtenerAtributosPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING,"Dentro de obtenerAtributosPrepago con el parametro idPrepago :"+idPrepago);
        Map<String,Object> atributosPrepagoMap = new HashMap<String,Object>();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        try{
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerAtributosPrepago");
            operationBinding.getParamsMap().put("idPrepago", idPrepago);
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                atributosPrepagoMap = (Map<String,Object>)operationBinding.getResult();
                logger.log(ADFLogger.WARNING,"obtenerAtributosPrepago resultado : " +atributosPrepagoMap);
                //recuperar el atributo "idTcaTipoMoneda" de prepago
                if(null != atributosPrepagoMap.get("idTcaTipoMoneda")){
                    logger.log(ADFLogger.WARNING,"idTcaTipoMoneda : " +atributosPrepagoMap.get("idTcaTipoMoneda"));
                    setIdTcaTipoMoneda((Integer)atributosPrepagoMap.get("idTcaTipoMoneda"));
                }
                //recuperar el atributo "idTcaTipoResolucion" de prepago
                if(null != atributosPrepagoMap.get("idTcaTipoResolucion")){
                    logger.log(ADFLogger.WARNING,"idTcaTipoResolucion : " +atributosPrepagoMap.get("idTcaTipoResolucion"));
                    setIdTcaTipoResolucion((Integer)atributosPrepagoMap.get("idTcaTipoResolucion"));
                }
            
                //recuperar el atributo "fechaPrepago" de prepago
                if(null != atributosPrepagoMap.get("fechaPrepago")){
                    logger.log(ADFLogger.WARNING,"fechaPrepago : " +atributosPrepagoMap.get("fechaPrepago"));
                    setFechaPrepago((Timestamp)atributosPrepagoMap.get("fechaPrepago"));
                }
                
                //recuperar el atributo "fechaRenovacion" de prepago
                if(null != atributosPrepagoMap.get("fechaRenovacion")){
                    logger.log(ADFLogger.WARNING,"fechaRenovacion : " +atributosPrepagoMap.get("fechaRenovacion"));
                    setFechaRenovacion((Timestamp)atributosPrepagoMap.get("fechaRenovacion"));
                }
            
                //recuperar el atributo "montoPrepago" de prepago
                if(null != atributosPrepagoMap.get("montoPrepago")){
                    logger.log(ADFLogger.WARNING,"montoPrepago : " +atributosPrepagoMap.get("montoPrepago"));
                    setMontoPrepago((BigDecimal)atributosPrepagoMap.get("montoPrepago"));
                }

            }else{
                logger.log(ADFLogger.WARNING, "Valor de retorno obtenerAtributosPrepago.getResult() es nulo."); 
            }
        }catch(Exception e){
            logger.severe("Error en obtenerAtributosPrepago",e);
        }
        
        logger.warning("Termina obtenerAtributosPrepago");
    }
    
    public void consultarContratosDesembolsos(Long idOperacion,Integer idTcaTipoResolucion,Integer idTcaTipoMoneda,
                                                Long idPrepago, Timestamp fechaRenovacion, Timestamp fechaPrepago){
        
        try{
            if(fechaPrepago!=null){
                logger.log(ADFLogger.WARNING,"Dentro de consultarContratosDesembolsos" +" con los parametro idOperacion :"+idOperacion + 
                                             " idTcaTipoResolucion :"+idTcaTipoResolucion +" idTcaTipoMoneda :"+idTcaTipoMoneda +
                                             " idPrepago : "+idPrepago);
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("llenarContratoDesembolsoPrepago");
                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                operationBinding.getParamsMap().put("idTcaTipoResolucion", idTcaTipoResolucion);
                operationBinding.getParamsMap().put("idTcaTipoMoneda", idTcaTipoMoneda);
                operationBinding.getParamsMap().put("idPrepago", idPrepago);
                operationBinding.getParamsMap().put("fechaRenovacion",fechaRenovacion);
                operationBinding.getParamsMap().put("fechaPrepago",fechaPrepago);
                operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    logger.log(ADFLogger.WARNING,"errorx1  :" + operationBinding.getErrors().toString());
                } 
                    logger.log(ADFLogger.WARNING," no errorx1  :" + operationBinding.getErrors().toString());
            }
            else
            {
                logger.log(ADFLogger.WARNING," fechaPrepago es nula en consultarContratosDesembolsos");
            }
        }
        catch(Exception exp){
                logger.log(ADFLogger.WARNING,"errorx2  :" + exp.getMessage());
                logger.log(ADFLogger.WARNING,"errorx2  :" + exp.toString());
            }
    }
    
    public void consultarContratosDesembolsosTree(Long idOperacion,Integer idTcaTipoResolucion,Integer idTcaTipoMoneda,
                                                Long idPrepago, Timestamp fechaRenovacion, Timestamp fechaPrepago){
        logger.log(ADFLogger.WARNING,"Dentro de consultarContratosDesembolsosTree" +" con los parametro idOperacion :"+idOperacion + 
                                     " idTcaTipoResolucion :"+idTcaTipoResolucion +" idTcaTipoMoneda :"+idTcaTipoMoneda +
                                     " idPrepago : "+idPrepago);
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("llenarContratoDesembolsoPrepagoTree");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idTcaTipoResolucion", idTcaTipoResolucion);
        operationBinding.getParamsMap().put("idTcaTipoMoneda", idTcaTipoMoneda);
        operationBinding.getParamsMap().put("idPrepago", idPrepago);
        operationBinding.getParamsMap().put("fechaRenovacion",fechaRenovacion);
        operationBinding.getParamsMap().put("fechaPrepago",fechaPrepago);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } 
    }
    
    public void consultarContratoDesembolsoPorRangoFechasPrepago(Long idOperacion,String contratoDesembolso,
                                                                    Timestamp fechaSolicitud,Timestamp fechaPrepago,
                                                                        Integer idTcaTipoMoneda){
        logger.warning("Into consultarContratoDesembolsoPorRangoFechasPrepago");
        logger.warning("idOperacion :"+idOperacion);
        logger.warning("contratoDesembolso :"+contratoDesembolso);
        logger.warning("fechaSolicitud :"+fechaSolicitud);
        logger.warning("fechaPrepago :"+fechaPrepago);
        logger.warning("idTcaTipoMoneda :"+idTcaTipoMoneda);
  
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("llenarContratoDesembolsoPorRangoFechasPrepago");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("contratoDesembolso", contratoDesembolso);
        operationBinding.getParamsMap().put("fechaSolicitud", fechaSolicitud);
        operationBinding.getParamsMap().put("fechaPrepago", fechaPrepago);
        operationBinding.getParamsMap().put("idTcaTipoMoneda",idTcaTipoMoneda);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        logger.warning("Leave consultarContratoDesembolsoPorRangoFechasPrepago");
    }

    @SuppressWarnings("unchecked")
    public void consultarContratoDesembolsoPorRangoFechasPrepago(Long idOperacion, String contratoDesembolso,
                                                                 Timestamp fechaSolicitud, Timestamp fechaPrepago,
                                                                 Integer idTcaTipoMoneda, Long idPadre) {
        logger.warning("Into consultarContratoDesembolsoPorRangoFechasPrepago");
        logger.warning("idOperacion :" + idOperacion);
        logger.warning("contratoDesembolso :" + contratoDesembolso);
        logger.warning("fechaSolicitud :" + fechaSolicitud);
        logger.warning("fechaPrepago :" + fechaPrepago);
        logger.warning("idTcaTipoMoneda :" + idTcaTipoMoneda);

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding =
            bindings.getOperationBinding("insertarContratoDesembolsoHijo");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("contratoDesembolso", contratoDesembolso);
        operationBinding.getParamsMap().put("fechaSolicitud", fechaSolicitud);
        operationBinding.getParamsMap().put("fechaPrepago", fechaPrepago);
        operationBinding.getParamsMap().put("idTcaTipoMoneda", idTcaTipoMoneda);
        operationBinding.getParamsMap().put("idPadre", idPadre);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        logger.warning("Leave consultarContratoDesembolsoPorRangoFechasPrepago");
    }

    @SuppressWarnings("unchecked")
    public void removerContratoDesembolsoPorRangoFechasPrepago(Long idPadre) {
        logger.warning("Into removerContratoDesembolsoPorRangoFechasPrepago");
        logger.warning("idPadre :" + idPadre);

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding =
            bindings.getOperationBinding("removerContratoDesembolsoHijo");
        operationBinding.getParamsMap().put("idPadre", idPadre);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }

        logger.warning("Leave removerContratoDesembolsoPorRangoFechasPrepago");
    }
    
    public void llenarFormularioPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING,"Dentro de llenarFormularioPrepago, idPrepago : "+idPrepago);
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("consultarPrepagoById");
        operationBinding.getParamsMap().put("idPrepago", idPrepago);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
    }
    
    public void llenarFormularioObservacionPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING,"Dentro de llenarFormularioObservacionPrepago, idPrepago : "+idPrepago);
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("consultarObservacionByIdPrepago");
            operationBinding.getParamsMap().put("idPrepago", idPrepago);
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
        }catch(Exception e){
            logger.severe("consultarObservacionByIdPrepago : ",e);
        }
    }

    public void inicializarRealizarAjustesSolicitudPrepago() {
        logger.entering(this.getClass().getName(), "inicializarRealizarAjustesSolicitudPrepago");
        Boolean esLineaGlobalCredito = null;
        //Invocar metodo para consultar y llenar el formulario de prepago
        this.llenarFormularioPrepago(getIdPrepago());
        //Invocar metodo para consultar los contratos de desembolsos
        this.consultarContratosDesembolsos(getCodigoOperacion().longValue(),getIdTcaTipoResolucion(),
                                            getIdTcaTipoMoneda(),getIdPrepago(),getFechaRenovacion(),getFechaPrepago());
        //Invocar metodo para consultar y llenar observacion prepago
        this.llenarFormularioObservacionPrepago(getIdPrepago());
        
        //consultar si el producto de la operacion se trata de una linea global de credito 
         esLineaGlobalCredito = this.obtenerLineaGlobalPorProducto(getCodigoProducto());
         if(null != esLineaGlobalCredito){
             logger.info("El codigoProducto : "+getCodigoProducto()+
                         " pertenece a una linea global de credito :"+esLineaGlobalCredito);
            //asignar el valor a la variable "esLineaGlobal"  usada en la vista
            setEsLineaGlobal(esLineaGlobalCredito);
         }else{
             logger.severe("No se recupero si el producto pertenece a una linea global de credito");
         }
         
         //Invocar metodo para obtener el Sector Institucional y el Tipo de Garantia
         this.consultarSectorGarantia(getCodigoOperacion());
         
        logger.exiting(this.getClass().getName(), "inicializarRealizarAjustesSolicitudPrepago");
    }
    
    public void inicializarGestionarPrepagoCliente() {
        logger.warning("Dentro de inicializarGestionarPrepagoCliente");
        try{
            if(null != getIdPrepago()){
                //Invocar metodo para consultar y llenar el formulario de prepago
                this.llenarFormularioPrepago(getIdPrepago());
            }else{
                logger.severe("idPrepago es nulo, no se ejecuto el llenado del formulario de prepago");
            }
        }catch(Exception e){
            logger.severe("Error en llenarFormularioPrepago : ", e);
        }
        try{
            if(null != getCodigoOperacion()){
                //Invocar metodo para obtener el Sector Institucional y el Tipo de Garantia
                this.consultarSectorGarantia(getCodigoOperacion());
            }else{
                logger.severe("idOperacion es nulo, no se consulto sector Institucional y tipo de Garantia");
            }
        }catch(Exception e){
            logger.severe("Error en consultarSectorGarantia : ", e);
        }
        logger.warning("Termino de inicializarGestionarPrepagoCliente");
    }
    
    /**
     * metodo para recuperar el sector institucional y el tipo de gerantia soberana
     * @param idOperacion
     */
    public void consultarSectorGarantia(oracle.jbo.domain.Number idOperacion) {
        logger.entering(this.getClass().getName(), "consultarSectorGarantia", idOperacion);
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Map<String, Object> resultado = new HashMap<String, Object>();
        Integer sector = null;
        Integer garantia = null;
        try{
            //metodo para obtener el Sector Institucional y el Tipo de Garantia
            operationBinding = bindings.getOperationBinding("obtenerSectorGarantia");
            //añadir parametro codigoOperacion 
            operationBinding.getParamsMap().put("idOperacion",idOperacion);
            //ejecutar el metodo 
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                //se recupera el resultado del metodo "obtenerSectorGarantia"
                resultado =(HashMap<String, Object>) operationBinding.getResult();
                if(null != resultado){
                    
                    if(null != resultado.get(SECTOR)){
                        try{
                            sector = (Integer) resultado.get(SECTOR);
                            setSectorInstitucional(sector);
                        }catch(Exception e){
                            logger.severe("Ocurrio un error al recuperar el valor de SECTOR del resultado de obtenerSectorGarantia");
                        }
                    }
                    
                    if(null != resultado.get(TIPO_GARANTIA)){
                        try{
                            garantia = (Integer) resultado.get(TIPO_GARANTIA);
                            setTipoGarantia(garantia);
                        }catch(Exception e){
                            logger.severe("Ocurrio un error al recuperar el valor de TIPO_GARANTIA del resultado de obtenerSectorGarantia");
                        }
                    }
                    
                }else{
                    logger.severe("el resultado de  obtenerSectorGarantia es nulo");
                }
            
                logger.warning("SectorInstitucional: " + getSectorInstitucional());
                logger.warning("TipoGarantia: " + getTipoGarantia());
            }
            
        }catch(Exception e){
            logger.severe("ocurrion un problema al ejecutar el metodo obtenerSectorGarantia",e); 
        }
        
        logger.exiting(this.getClass().getName(), "consultarSectorGarantia");
    }
    
    /**
     * metodo para recuperar si es una linea global de credito apartir del codigoProducto
     * @param codigoProducto id de la tabla "CAT_PRODUCTO"
     * @return true o false
     */
    public Boolean obtenerLineaGlobalPorProducto(Integer codigoProducto){
        logger.entering(this.getClass().getName(),"obtenerLineaGlobalPorProducto",codigoProducto);
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean resultado = null;
        try{
            //ejecutar el metodo "obtenerCampo" declarado en "FenixAMImpl"
            operationBinding = bindings.getOperationBinding("obtenerCampo");
            //añadir parametros idProducto y columna
            operationBinding.getParamsMap().put("idProducto", codigoProducto);
            operationBinding.getParamsMap().put("columna", new Integer(8));
            //ejecutar el metodo
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                //manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                //recuperar el resultado del metodo "obtenerCampo"
                resultado =(Boolean)operationBinding.getResult();
            }
        }catch(Exception e){
            logger.severe("Ocurrio un problema al ejecutar el metodo obtenerCampo",e);
        }
        logger.exiting(this.getClass().getName(),"obtenerLineaGlobalPorProducto",resultado);
        return resultado;
    }
    
    public Calendar fechaCalculoDefinitivo(Calendar fechaInicial, Integer dias, String descripcionMoneda) {
        logger.entering(this.getClass().getName(),"fechaCalculoDefinitivo",new Object[]{fechaInicial,dias});
        
//        String diaInhabil = null;
//        String diaInicio = null;
//        Date fechaInicio = null;
//        diasInhabilesMoneda(descripcionMoneda);
//        HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}");
//        Integer diasHabiles = 0;
//        Integer cantidadDiasInhabiles = 0;
//        Boolean fechaBusqueda = Boolean.FALSE;
//        List<Date> diasInhabiles = new ArrayList<Date>();
//        diasInhabiles = (ArrayList) holidaysBean.getHoliDay();
//        logger.warning("Lista dias: " + diasInhabiles.size());
//        if (diasInhabiles != null) {
//            
//            while (diasHabiles < dias || fechaBusqueda) {
//                fechaInicio = fechaInicial.getTime();
//                for (Date fechaInhabil : diasInhabiles) {
//                    if (compararIgualdadFecha(fechaInhabil, fechaInicio)) {
//                        logger.warning("la fecha inicial :" + diaInicio + " es igual a fecha inhabil : "+ diaInhabil);
//                        fechaBusqueda = Boolean.TRUE;  
//                        diasHabiles--;
//                        cantidadDiasInhabiles++;
//                    } else {
//                        
//                        fechaBusqueda = Boolean.FALSE;
//                    }
//                }
//                
//                if ((!fechaBusqueda) && (diasHabiles < dias)) {
//                    diasHabiles++;   
//                }
//                
//                fechaInicial.add(Calendar.DATE, -1);
//            }
//        }
//        
//        logger.warning("Fecha maxima para solicitar el prepago: " + fechaInicial.getTime());
//        logger.warning("dias habiles: " + diasHabiles);
//        logger.warning("dias inhabiles encontrados: "+cantidadDiasInhabiles);
//        
//        logger.exiting(this.getClass().getName(),"fechaCalculoDefinitivo",fechaInicial);
//        
        Calendar fechaInicialCal = new GregorianCalendar();
        fechaInicialCal.setTime(new Date(fechaInicial.getTime().getTime()));
        fechaInicialCal.add(Calendar.MONTH, -2);
        
        return (fechaCalculoDefinitivoPorRango(fechaInicial.getTime(), dias, descripcionMoneda, fechaInicialCal.getTime(), fechaInicial.getTime()));
    }
    
    private Calendar fechaCalculoDefinitivoPorRango
        (
            Date fechaInicial, 
            Integer dias, 
            String moneda, 
            Date fechaRangoInicial,
            Date fechaRangoFinal
        ) 
    {
        logger.log(ADFLogger.WARNING, "Entrando en fechaCalculoDefinitivoPorRango.");
        logger.log(ADFLogger.WARNING, "fechaInicial: " + fechaInicial);
        logger.log(ADFLogger.WARNING, "dias: " + dias);
        logger.log(ADFLogger.WARNING, "moneda: " + moneda);
        logger.log(ADFLogger.WARNING, "fechaRangoInicial: " + fechaRangoInicial);
        logger.log(ADFLogger.WARNING, "fechaRangoFinal: " + fechaRangoFinal);
        
        String diaInhabil = null;
        String diaInicio = null;
        
        diasInhabilesPorRango(moneda, fechaRangoInicial, fechaRangoFinal);
        Date fechaInicio = null;
        Integer diasHabiles = 0;
        Integer cantidadDiasInhabiles = 0;
        Boolean fechaBusqueda = Boolean.FALSE; 
        List<Date> diasInhabiles = new ArrayList<Date>();
        HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}"); 
        diasInhabiles = (ArrayList) holidaysBean.getHoliDay();
        logger.warning("Lista dias: " + diasInhabiles.size());

        Calendar fechaInicialCal = Calendar.getInstance();
        fechaInicialCal.setTime(fechaInicial); 
        if (diasInhabiles != null) { 
            while (diasHabiles < dias) {
                fechaInicialCal.add(Calendar.DATE, -1);  
                fechaInicio = fechaInicialCal.getTime();  
                logger.warning("while fechaInicio: " + fechaInicio);
                if (!EsDiaInhabil(diasInhabiles, fechaInicio)) {   
                    diasHabiles++;
                }   
                logger.warning("diasHabiles: " + diasHabiles + " y dias: " + dias);
                logger.warning("disminucion de fechaInicialCal: " + fechaInicialCal);        
            }
        }

        logger.warning("Fecha maxima para solicitar el prepago: " + fechaInicialCal.getTime());
        logger.warning("dias habiles: " + diasHabiles);
        logger.warning("dias inhabiles encontrados: " + cantidadDiasInhabiles);


        logger.log(ADFLogger.WARNING, "fechaCalculoDefinitivo: " + fechaInicialCal.getTime());
        return (fechaInicialCal);
    }
    
    public boolean EsDiaInhabil(List<Date> diasInhabiles,Date fechaInicio)
    {
        boolean vb_EsDiaInhabil = false;
        
        for (Date fechaInhabil : diasInhabiles) { 
            if (compararIgualdadFecha(fechaInhabil, fechaInicio)) {   
                vb_EsDiaInhabil = true;
                return vb_EsDiaInhabil;
            }
        }    
        return vb_EsDiaInhabil;
    }
    
    public void diasInhabilesMoneda(String descripcionMoneda) {
        logger.warning("inside diasInhabilesMoneda.");
        
        HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        //String descripcionMoneda = obtenerDescripcionMoneda();
        
        OperationBinding operationBindingValidar = bindings.getOperationBinding("obtenerDiasInhabilesMoneda");
        operationBindingValidar.getParamsMap().put("descripcionMoneda", descripcionMoneda);
        operationBindingValidar.execute();
        
        if (null != operationBindingValidar.getResult()) {
            setDiasInhabilesMoneda((ArrayList) operationBindingValidar.getResult());
        } else{
            logger.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
        }
        
        holidaysBean.setHoliDay(getDiasInhabilesMoneda());
    }
    
    public void diasInhabilesPorRango(String moneda, Date fechaRangoInicial, Date fechaRangoFinal) {
        logger.warning("inside diasInhabilesMoneda.");
        
        HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        
        OperationBinding operationBindingValidar = bindings.getOperationBinding("obtenerDiasInhabilesMonedaRango");
        operationBindingValidar.getParamsMap().put("descripcionMoneda", moneda);
        operationBindingValidar.getParamsMap().put("fechaRangoInicial", fechaRangoInicial);
        operationBindingValidar.getParamsMap().put("fechaRangoFinal", fechaRangoFinal);
        operationBindingValidar.execute();
        
        if (null != operationBindingValidar.getResult()) {
            setDiasInhabilesMoneda((ArrayList) operationBindingValidar.getResult());
        } else{
            logger.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
        }
        
        holidaysBean.setHoliDay(getDiasInhabilesMoneda());
    }
    
    public BigDecimal getTasaLibor() {
        return tasaLibor;
    }

    public void setTasaLibor(BigDecimal tasaLibor) {
        this.tasaLibor = tasaLibor;
    }

    public BigDecimal getPrime() {
        return prime;
    }

    public void setPrime(BigDecimal prime) {
        this.prime = prime;
    }

    public Timestamp getFechaReferencia() {
        return fechaReferencia;
    }

    public void setFechaReferencia(Timestamp fechaReferencia) {
        this.fechaReferencia = fechaReferencia;
    }


    public Timestamp getFechaCalculoDefinitivo() {
        return fechaCalculoDefinitivo;
    }

    public void setFechaCalculoDefinitivo(Timestamp fechaCalculoDefinitivo) {
        this.fechaCalculoDefinitivo = fechaCalculoDefinitivo;
    }


    public Timestamp getFechaPrepago() {
        return fechaPrepago;
    }

    public void setFechaPrepago(Timestamp fechaPrepago) {
        this.fechaPrepago = fechaPrepago;
    }


    public String getCargoTramitePrepago() {
        return cargoTramitePrepago;
    }

    public void setCargoTramitePrepago(String cargoTramitePrepago) {
        this.cargoTramitePrepago = cargoTramitePrepago;
    }


    public BigDecimal getMontoPrepago() {
        return montoPrepago;
    }

    public void setMontoPrepago(BigDecimal montoPrepago) {
        this.montoPrepago = montoPrepago;
    }
    public void setPrepagoIncumplido(String prepagoIncumplido) {
        this.prepagoIncumplido = prepagoIncumplido;
    }

    public String getPrepagoIncumplido() {
        return prepagoIncumplido;
    }

    public void setPrepagoRecibido(String prepagoRecibido) {
        this.prepagoRecibido = prepagoRecibido;
    }

    public String getPrepagoRecibido() {
        return prepagoRecibido;
    }
    
    public void setRequiereMasInfoCOFI(String requiereMasInfoCOFI) {
        this.requiereMasInfoCOFI = requiereMasInfoCOFI;
    }

    public String getRequiereMasInfoCOFI() {
        return requiereMasInfoCOFI;
    }
    
    public void setOpSalidaTarea(String opSalidaTarea) {
        this.opSalidaTarea = opSalidaTarea;
    }

    public String getOpSalidaTarea() {
        return opSalidaTarea;
    }
    
    public void setMensajeFinalizar(String mensajeFinalizar) {
        this.mensajeFinalizar = mensajeFinalizar;
    }

    public String getMensajeFinalizar() {
        return mensajeFinalizar;
    }
    
    public void setMensajeMasInformacion(String mensajeMasInformacion) {
        this.mensajeMasInformacion = mensajeMasInformacion;
    }

    public String getMensajeMasInformacion() {
        return mensajeMasInformacion;
    }

    public Boolean cargarComisionActiva() {
        logger.log(ADFLogger.WARNING, "INTO cargarComisionActiva");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Long pIdPrepago = null;
        Boolean isComision = Boolean.FALSE;
        Long comision = null;
        
        try{
            if(null != this.getIdPrepago()){
                pIdPrepago = this.getIdPrepago();
                logger.log(ADFLogger.WARNING, "Valor de Id Prepago.." + pIdPrepago);
            }else{
                logger.log(ADFLogger.WARNING, "Valor de Id Prepago es nulo.");
            }
            
            operationBinding = bindings.getOperationBinding("cargarComisionPrepago");
            operationBinding.getParamsMap().put("idPrepago", pIdPrepago);
            operationBinding.execute();
            if(null != operationBinding.getResult()){
            map = (Map)operationBinding.getResult();
                comision = (Long)map.get("idComision");
                descripcionComision = (String)map.get("descripcionComision");
                logger.log(ADFLogger.WARNING, "Valor idComision." + comision + ".Valor comision Descripcion :" + descripcionComision);
                isComision = Boolean.TRUE;
                setIdComision(comision);
                logger.log(ADFLogger.WARNING, "Valor id comision Seteado :" + getIdComision());
            }else{
                logger.log(ADFLogger.WARNING, "Valor idComision es nulo.");
            }
//            operationBinding = bindings.getOperationBinding("obtenerComisionPrepagoByIdPrepago");
//            operationBinding.getParamsMap().put("idPrepago", pIdPrepago);
//            operationBinding.execute();
//            idComision = (Long)operationBinding.getResult();
//            logger.log(ADFLogger.WARNING, "Valor retorno de la comision es ..." + idComision);
//            operationBinding = null;
//            operationBinding = bindings.getOperationBinding("buscarComisionPrepagoPorId");
//            operationBinding.getParamsMap().put("id", idComision);
//            operationBinding.execute();
//            isComision = (Boolean)operationBinding.getResult();
          this.setIsValidaComision(Boolean.TRUE);
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en cargarComisionActiva." + e.getClass() + "." + e.getMessage());
        }
        logger.log(ADFLogger.ERROR, "Valor de retorno al inicializar la pantalla." + isComision);
        return isComision;
    }
    
    public void setIsValidaComision(Boolean isValidaComision) {
        this.isValidaComision = isValidaComision;
    }

    public Boolean getIsValidaComision() {
        return isValidaComision;
    }
    public void setIdComision(Long idComision) {
        this.idComision = idComision;
    }

    public Long getIdComision() {
        return idComision;
    }
    
    public void setDescripcionComision(String descripcionComision) {
        this.descripcionComision = descripcionComision;
    }

    public String getDescripcionComision() {
        return descripcionComision;
    }

    public Boolean getEsVisibleIntereses() {
        return esVisibleIntereses;
    }

    public void setEsVisibleIntereses(Boolean esVisibleIntereses) {
        this.esVisibleIntereses = esVisibleIntereses;
    }
    
    public oracle.jbo.domain.Date getCurrentDate(){  
        oracle.jbo.domain.Date currentDate=new oracle.jbo.domain.Date(new 
                java.sql.Date(new java.util.Date().getTime()+(1000*60*60*24))); 
        return currentDate;  
    }

    public void setPagoTotalHeaderBinding(RichSelectBooleanCheckbox pagoTotalHeaderBinding) {
        this.pagoTotalHeaderBinding = pagoTotalHeaderBinding;
    }

    public RichSelectBooleanCheckbox getPagoTotalHeaderBinding() {
        return pagoTotalHeaderBinding;
    }


    public Boolean getEsPagoTotalHeader() {
        return esPagoTotalHeader;
    }

    public void setEsPagoTotalHeader(Boolean esPagoTotalHeader) {
        this.esPagoTotalHeader = esPagoTotalHeader;
    }


    public void setDiasInhabilesMoneda(List<Date> diasInhabilesMoneda) {
        this.diasInhabilesMoneda = diasInhabilesMoneda;
    }

    public List<Date> getDiasInhabilesMoneda() {
        return diasInhabilesMoneda;
    }


    public void setInitFormObtenerListas(RichOutputText initFormObtenerListas) {
        this.initFormObtenerListas = initFormObtenerListas;
    }

    public RichOutputText getInitFormObtenerListas() {
        logger.warning("Dentro getInitObtenerAtributosContratosDesembolsos");
        this.obtenerAtributosContratosDesembolsos();
        logger.warning("Fuera getInitObtenerAtributosContratosDesembolsos");
        return initFormObtenerListas;
    }
    
    private boolean compararIgualdadFecha(Date fechaA, Date fechaB) {
        boolean fechasIguales = Boolean.FALSE;

        Calendar fechaInicial = new GregorianCalendar();
        fechaInicial.setTime(fechaA);

        Calendar fechaFinal = new GregorianCalendar();
        fechaFinal.setTime(fechaB);

        if (null != fechaA && null != fechaB) {
            int anioA = fechaInicial.get(Calendar.YEAR);
            int anioB = fechaFinal.get(Calendar.YEAR);

            int mesA = fechaInicial.get(Calendar.MONTH);
            int mesB = fechaFinal.get(Calendar.MONTH);

            int diaA = fechaInicial.get(Calendar.DAY_OF_MONTH);
            int diaB = fechaFinal.get(Calendar.DAY_OF_MONTH);

            //logger.log(ADFLogger.WARNING, "Compraracion. " + anioA + "/" + mesA + "/" + diaA + " - " + anioB + "/" + mesB + "/" + diaB);
            if (anioA == anioB && mesA == mesB && diaA == diaB) {
                fechasIguales = Boolean.TRUE;
            }
        } else {
            logger.log(ADFLogger.WARNING, "Error, no se puede comparar fechas nulas.");
        }

        return fechasIguales;
    }
    
    private void obtenerEstadoTarea(){
        logger.warning("Entra en obtenerEstadoTarea");
        
        Integer numeroTarea;
        if(null != codigoTarea && !"".equalsIgnoreCase(codigoTarea)){
            logger.warning("codigoTarea : " + codigoTarea);
            numeroTarea = Integer.valueOf(codigoTarea); 
            
            switch(numeroTarea){
            case 113:
            case 145:
                logger.warning("Estado Ingreasar solicitud : " + JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12IngresarSolicitudPrepagoPageDef.state.inputValue}"));
                if(null != JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12IngresarSolicitudPrepagoPageDef.state.inputValue}")){
                    state = JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12IngresarSolicitudPrepagoPageDef.state.inputValue}").toString();
                    validaEsTareaCompletada();
                }else{
                    logger.warning("El estado de la tarea es nulo");
                }
            break;
            case 114:
                logger.warning("Estado Determinar cargo prepago COPRES : " + JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoCOPRESPageDef.state.inputValue}"));
                if(null != JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoCOPRESPageDef.state.inputValue}")){
                    state = JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoCOPRESPageDef.state.inputValue}").toString();
                    validaEsTareaCompletada();
                }else{
                    logger.warning("El estado de la tarea es nulo");
                }
            break;
            
            case 115:
                logger.warning("Estado Determinar cargo prepago DAECI : " + JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoDAECIPageDef.state.inputValue}"));
                if(null != JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoDAECIPageDef.state.inputValue}")){
                    state = JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoDAECIPageDef.state.inputValue}").toString();
                    validaEsTareaCompletada();
                }else{
                    logger.warning("El estado de la tarea es nulo");
                }
            break;
            case 116:
                logger.warning("Estado Determinar cargo prepago MDC : " + JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoMDCPageDef.state.inputValue}"));
                if(null != JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoMDCPageDef.state.inputValue}")){
                    state = JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoMDCPageDef.state.inputValue}").toString();
                    validaEsTareaCompletada();
                }else{
                    logger.warning("El estado de la tarea es nulo");
                }
            break;
            case 117:
                logger.warning("Estado Determinar cargo prepago SEPRI : " + JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoSEPRIPageDef.state.inputValue}"));
                if(null != JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoSEPRIPageDef.state.inputValue}")){
                    state = JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12DeterminarCargosPrepagoSEPRIPageDef.state.inputValue}").toString();
                    validaEsTareaCompletada();
                }else{
                    logger.warning("El estado de la tarea es nulo");
                }
            break;
            case 118:
                logger.warning("Estado Validar penalidad de prepago COFI : " + JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12ValidarPenalidadPrepagoCOFIPageDef.state.inputValue}"));
                if(null != JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12ValidarPenalidadPrepagoCOFIPageDef.state.inputValue}")){
                    state = JSFUtils.resolveExpression("#{data.pa12prepagoght_PA12ValidarPenalidadPrepagoCOFIPageDef.state.inputValue}").toString();
                    validaEsTareaCompletada();
                }else{
                    logger.warning("El estado de la tarea es nulo");
                }
            break;
            default:
                logger.warning("No se valida el estado de esta tarea.");
            break;
            }
        }else{
            logger.warning("No se obtiene el codigo de tarea");
        }
    } 
    
    private void validaEsTareaCompletada(){
        logger.warning("Entra en validaEsTareaCompletada");
        logger.warning("Estado de la tarea recibida");
        
        if(null != state && !"".equals(state)){
            logger.warning("Estado de la tarea recibida : " + state);
            if(state.equalsIgnoreCase(COMPLETED)){
                esTareaCompletada = Boolean.TRUE;
            }
                    
            if(state.equalsIgnoreCase(ASSIGNED)){
                esTareaCompletada = Boolean.FALSE;
            } 
        }
        
        logger.warning("esTareaCompletada : " + esTareaCompletada);
    }

    public void setFechaRenovacion(Timestamp fechaRenovacion) {
        this.fechaRenovacion = fechaRenovacion;
    }

    public Timestamp getFechaRenovacion() {
        return fechaRenovacion;
    }


    public void setOperacionId(Long operacionId) {
        this.operacionId = operacionId;
    }

    public Long getOperacionId() {
        return operacionId;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
    
    public void setEsTareaCompletada(Boolean esTareaCompletada) {
        this.esTareaCompletada = esTareaCompletada;
    }

    public Boolean getEsTareaCompletada() {
        return esTareaCompletada;
    }
}
