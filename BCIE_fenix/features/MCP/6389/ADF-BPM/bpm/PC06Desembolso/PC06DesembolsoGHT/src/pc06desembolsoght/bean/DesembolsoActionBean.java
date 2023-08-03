package pc06desembolsoght.bean;
 
import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.domain.Number;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

import java.util.Iterator;


import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPoll;
import oracle.adf.view.rich.component.rich.data.RichListView;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;

import oracle.jbo.JboException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;

import org.apache.myfaces.trinidad.event.PollEvent;

import org.bcie.fenix.view.gestordesembolsos.ContratoDesembolsosBean;

public class DesembolsoActionBean extends FenixActionBean {

    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(DesembolsoActionBean.class);

    public static final String BUNDLE = "pc06desembolsoght.PC06DesembolsoGHTBundle";
    public static final String VALIDAR_COMENTARIO_OPERACION_FH_ACTUAL_OPER = "valComentarioOperUsuFchActual";
    public static final String MSG_ERROR_COMENTARIO_OPER_REQUERIDO = "pc06desembolso_revisar_solicitud_presidencia_msg_error_comentario_operacion_requerido";
    public static final String MSG_ERROR_COMENTARIO_OPER_FALLO = "pc06desembolso_revisar_solicitud_presidencia_msg_error_comentario_operacion_validacion_fallo";
    public static final String MSG_ERROR_COMENTARIO_OPER_NO_ENCONTRADO = "pc06desembolso_revisar_solicitud_presidencia_msg_error_comentario_operacion_no_encontrado";
    public static final String MSG_ERROR_COMENTARIO= "MSG_COMENTARIO_REQUERIDO";
    public static final String INSERTAR_FORM_JUSTIFICACION_EXCEPCION = "insertarFormulariosJustificacionExcepcion";
    public static final String COMMIT_DATOS_CONTRATO = "comitDatosContrato";
    public static final String MSG_ERROR_ACTUALIZAR_DATOS= "MSG_ERROR_ACTUALIZAR_DATOS";
    public static final String MSG_ERROR_VALIDACION_DATOS= "MSG_ERROR_VALIDACION_DATOS";
    
    public static final String MSG_ERROR_FECHA_INICIAL_MAYOR = "pc06desembolso_justificar_desembolso_excepcion_msg_error_fecha_inicial_mayor";
    public static final String MSG_ERROR_AGREGAR_COMENTARIO = "pc06desembolso_justificar_desembolso_agregar_comentario";
    public static final String MSG_ERROR_ASOCIAR_EVIDENCIAS_JUSTIFICACIONES = "pc06desembolso_justificar_desembolso_excepcion_msg_error_asociar_evidencias_justificaciones";
    public static final String MSG_ERROR_APLICAR_COMPENSACION_DESEMBOLSO = "MSG_ERROR_APLICAR_COMPENSACION_DESEMBOLSO";
    public static final String ES_NECESARIO_CAPTURAR_DATOS_OBLIGATORIOS = "ES_NECESARIO_CAPTURAR_DATOS_OBLIGATORIOS"; 
    public static final String MSG_ERROR_BHQ_TRANSFERENCIA_FT05 = "MSG_ERROR_BHQTRANSFERENCIAFT05";
    public static final String MSG_ERROR_SALDO_CONTRATO_DESEMBOLSO = "MSG_ERROR_SALDOCONTRATODESEMBOLSO";
    public static final String COD_EXT_MONEDA_LINEA_CREDITO = "USD";

    public static final String SELECCIONDADO = "1";
    public static final Integer ID_MONEDA_USD = 1;
    
    private RichPopup popupConfFinalizarTarea;
    private RichPopup popupConfMasInfo;
    private RichPopup popupDesestimarDesembolso;
    private RichPopup popupAgregarEvidenciasDesembolsoExcepcion;
    private RichPanelGroupLayout contenedorJustificaciones;
    private RichPopup popupEliminarEvidencia;
    private RichPopup popupConfFinalizarTareaDesembolsoExcepcion;
    private RichPopup popupCancelOperacionDesembolsoExcepcion;
    
    private String docpop;
    
    private static Integer bandera = 0;
    private RichPopup popupReservaFondos;
    private RichSelectOneRadio selectOptionSOR;

    private static RichPoll pollRealizarAjustes = new RichPoll();
    private static RichPoll pollFinalizarTarea = new RichPoll();
    private RichOutputText initForm;
    private RichSelectItem selectItemSi1;
    private RichSelectItem selectItemSi2;
    private RichPopup alertaMontosPopUpIUC;
    
    private RichListView listaEvidencias;
    private RichPopup alertaProgramacionUIC;
    
    private RichRegion detalleOperacionUIC;
    private RichRegion gestorDocumentosUIC;
    private RichRegion matrizTccUIC;
    private RichRegion comentariosUIC;
    private List<String> listaAdvertencias = new ArrayList<String>();
    // SPS | 07/01/2020 | Se anexa para atender T14286
    /* ==============      CONSTANTES  CA     ============= */
    private static final String ID_PANELCMP = "pgCAEditable";
    private static final String ID_SECTOR = "socSCTREd";
    private static final String ID_APORTE = "socAPRTEd";
    private static final String ID_CATEGORIA = "socCATEd";
    private static final String ID_SUBCATEGORIA = "socSUBCEd";
    private static final String ID_PANELPADRE = "pglCmpCAPE";
    private static final String ID_LBL_SECTOR = "lblSector1";
    private ArrayList<String> erroresCA;
    //---------------------------------------------------------
    

    public DesembolsoActionBean() {
        super();
    }

    // SPS | 07/01/2020 | Se modifica para atender T14286  
    public String invocarFinalizarTarea() {
        if (validacionesCA()) {
            LOGGER.entering(this.getClass().getName(), "invocarFinalizarTarea");
            LOGGER.warning("Inicia el proceso de Invocar Finalizar Tarea");
            //int codigoTarea = this.getDesembolsoBean().getIdTarea() != null ? (this.getDesembolsoBean().getIdTarea().trim().length() > 0 ? Integer.parseInt(this.getDesembolsoBean().getIdTarea()) : 0) : 0;

            Map<String, Object> params = new HashMap<String, Object>();
            Boolean isValidFuente = Boolean.FALSE;
			String cuentaCliente;

            // if (this.validaComentarioOperacion(codigoTarea, true)) {
            Integer codigoTarea = getCodigoTarea();
            LOGGER.warning("Codigo de Tarea: " + codigoTarea);

            DesembolsoBean desembolsoBean =
                (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
            Long idOperacion = null;
            try {
                idOperacion = new Long(desembolsoBean.getIdOperacion());
            } catch (Exception e) {
                LOGGER.warning("ERROR al obtener el idOperacion.", e);
            }

            switch (codigoTarea) {
            case FenixConstants.PC06_GESTIONAR_DESEMBOLSO_FUENTES_EXTERNAS:
                LOGGER.warning("*Inf, inicia mvalidar comentario en Gestionar desembolsos a fuentes externas..");
                LOGGER.warning("*Inf, ok comentario, validando exepciones...");
                this.validaGestionarDesembolsoFuentesExternas();
                break;
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_DAECI:
                LOGGER.warning("*Inf, case: CGD_VALIDAR_ASIGNACION_DE_RECURSOS_DAECI");
                 Boolean guardoDatos = commitDatosAsignacion();
                
                 if(guardoDatos)
                     finalizarTareaPopup();    
                            
                break;
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_FINAM:
                finalizarTareaPopup();
                break;
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PCT:
                finalizarTareaPopup();
                break;
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PREA:
                finalizarTareaPopup();
                break;
            case FenixConstants.CGD_REALIZAR_AJUSTES_DE_ASIGNACION_DE_RECURSOS:
                if (validarCamposRequridosEnRealizarAjustes() && validarMontoAsignacionContraContrato() &&
                    validarMontoContratoVsOperacion()) {
                    LOGGER.warning("Validando Fecha Inicio desembolsos y Fecha de desembolso de la totalidad de recursos.");
                    List<String> listaErroresFecha = new ArrayList<String>();
                    listaErroresFecha = validarFechasInicioDesembolsoTotalidadRecursosAsignRecursos(idOperacion);
                    if (listaErroresFecha.size() > 0) {
                        for (String mensajeFechas : listaErroresFecha) {
                            LOGGER.warning("Obteniendo errores de validacion de fechas.");
                            JSFUtils.addFacesErrorMessage(mensajeFechas);
                        }
                    } else {
                        Long idLineaCredito = null;
                        LOGGER.warning("Validando fuentes sin IDLINEAPASIVA.");
                        try {
                            idLineaCredito = (Long) ADFUtils.getBoundAttributeValue("Id");
                            params.put("idLineaCredito", idLineaCredito);
                            isValidFuente = execute(params, "validarFuentesSinIdLineaPasiva");
                        } catch (Exception e) {
                            LOGGER.warning("ERROR al ejecutar operBinding validarFuentesSinIdLineaPasiva.", e);
                            isValidFuente = null;
                        }

                        if (null != isValidFuente) {
                            if (isValidFuente) {
                                finalizarTareaPopup();
                            } else {
                                LOGGER.warning("Fuentes sin IDLINEAPASIVA.");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_FUENTES_SIN_LINEA_PASIVA_MSG"));
                            }
                        } else {
                            LOGGER.warning("ERROR al validar Fuentes sin IDLINEAPASIVA.");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_EJECUTAR_FUENTES_SIN_LINEA_MSG"));
                        }
                    }
                }
                break;
            case FenixConstants.CGD_APROBAR_DESEMBOLSO:
              
                if(validarTermino406()){  
                    if (validarReglasPorTarea()) {
                        finalizarTareaPopup();
                    }
                }
                break;
            case FenixConstants.CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION:
                //if(this.validaComentarioOperacion(FenixConstants.CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION, true)){
                finalizarTareaPopup();
                //}
                break;
            case FenixConstants.CGD_JUSTIFICAR_DESEMBOLSO_POR_EXCEPCION:
                LOGGER.warning("Ingresa al caso Justificar desembolso");
                if (validaEvidenciasJustificaciones()) {
                    finalizarTareaPopupExcepcion();
                } else {
                    String msg = getStringFromBundle(MSG_ERROR_ASOCIAR_EVIDENCIAS_JUSTIFICACIONES);
                    JSFUtils.addFacesErrorMessage(msg);
                    LOGGER.warning("validaEvidenciasJustificaciones devuelve null");
                }
                break;
            case FenixConstants.CGD_VALIDAR_FONDOS_PRESUPUESTARIOS:
                //if (this.validaComentarioOperacion(FenixConstants.CGD_VALIDAR_FONDOS_PRESUPUESTARIOS, true)) {
                this.validaValidarFondosPresupuestarios();
                //}
                break;
            case FenixConstants.CGD_GESTIONAR_PROGRAMACION:
                /*Inicia FNXII-5449*/
                if (validarFechaEstimadaDesembolsar()) {
                    //if (this.validaComentarioOperacion(FenixConstants.CGD_GESTIONAR_PROGRAMACION, true)) {
                    if (validarReglasPorTarea()) {
                        finalizarTareaPopup();
                    }
                    //}
                    /*Finaliza FNXII-5449*/
                }
                break;
            case FenixConstants.CGD_RESERVAR_FONDOS:
                /*Se atiende incidencia FNXII-5412*/
                /*if(this.validaComentarioOperacion(FenixConstants.CGD_RESERVAR_FONDOS, true)){*/				
				//cuentaCliente = (String) JSFUtils.resolveExpression("#{bindings.CuentaCliente.inputValue}");
				//Jenamorado 03/08/2021
				   cuentaCliente =  ValidarCuentaClienteNoNull();
	                                
	            if (cuentaCliente == null /*|| cuentaCliente.isEmpty()*/) 
	            {
	                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CUENTA_CLIENTE"));
	                return null;
	            }
				
                if (validarReglasPorTarea()) {
                    LOGGER.warning("Reglas cumplidas.");
                    finalizarTareaPopup();
                } else {
                    LOGGER.warning("Las reglas para Aprobar el desembolso no se han cumplido.");
                }
                //}
                break;
            case FenixConstants.CGD_REALIZAR_AJUSTES_A_DESEMBOLSO:
                LOGGER.warning("*Inf, entro al case de CGD_REALIZAR_AJUSTES_A_DESEMBOLSO");

                if (!validarMontoDisponibleTransferir())
                    return null;


                //cuentaCliente = (String) JSFUtils.resolveExpression("#{bindings.CuentaCliente.inputValue}");
                //Jenamorado 03/08/2021
                cuentaCliente = ValidarCuentaClienteNoNull();
                if (cuentaCliente == null /*|| cuentaCliente.isEmpty()*/) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CUENTA_CLIENTE"));
                    return null;
                }

                /*List<String> listaCondiciones = new ArrayList<String>();

                //listaCondiciones = validarCondiciones();          Se comenta linea a peticion de Mariano para FNXII-6323
                if (listaCondiciones.size() > 0) {
                    for (String mensajeCondicion : listaCondiciones) {
                        LOGGER.warning("Obteniendo errores de validacion de Condicion.");
                        JSFUtils.addFacesErrorMessage(mensajeCondicion);
                    }*/
                Map resultado = new HashMap<String, Object>();
                resultado = validarCondiciones();
                Boolean esValidado = (Boolean)resultado.get("esValidado");
                if(!esValidado){
                        Iterator it = resultado.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry e = (Map.Entry)it.next();
                            if(null != e.getValue()){
                                String key = (String)e.getKey();
                                LOGGER.warning("Valor del KEY :" + key);
                                if(key.equalsIgnoreCase("esValidado")){
                                    LOGGER.warning("El valor es Boolean");
                                }
                                else{
                                    LOGGER.warning("mensaje de error :" + (String)e.getValue());
                                    JSFUtils.addFacesErrorMessage((String)e.getValue());
                                }
                            }
                        }
                        it.remove();
                } else {
                    if (guardarDatosContrato()) {
                        if (validarReglasPorTarea()) {
                            LOGGER.warning("Reglas cumplidas.");
                            LOGGER.warning("Validando fuentes sin IDLINEAPASIVA.");
                            try {
                                params.put("idLineaCredito", desembolsoBean.getLinea());
                                isValidFuente = execute(params, "validarFuentesSinIdLineaPasiva");
                            } catch (Exception e) {
                                LOGGER.warning("ERROR al ejecutar operBinding validarFuentesSinIdLineaPasiva.", e);
                                isValidFuente = null;
                            }

                            if (null != isValidFuente) {
                                if (isValidFuente) {
                                    finalizarTareaPopup();
                                } else {
                                    LOGGER.warning("Fuentes sin IDLINEAPASIVA.");
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_FUENTES_SIN_LINEA_PASIVA_MSG"));
                                }
                            } else {
                                LOGGER.warning("ERROR al validar Fuentes sin IDLINEAPASIVA.");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_EJECUTAR_FUENTES_SIN_LINEA_MSG"));
                            }
                        } else {
                            LOGGER.warning("Las reglas para Realizar ajustes al desembolso no se han cumplido.");
                        }
                    }
                }
                break;
            case FenixConstants.CGD_VALIDAR_INFORMACION_FINANCIERA_DE_DESEMBOLSO:
				//cuentaCliente = (String) JSFUtils.resolveExpression("#{bindings.CuentaCliente.inputValue}");
                                 //jenamorado02/08/2021
                             cuentaCliente =  ValidarCuentaClienteNoNull();
				if (/*!cuentaCliente.isEmpty() &&*/ cuentaCliente != null) 
				{
					pollFinalizarTarea.setInterval(60000);
					pollFinalizarTarea.setInterval(60001);
					pollFinalizarTarea.setRendered(true);
					AdfFacesContext.getCurrentInstance().addPartialTarget(pollFinalizarTarea);
					finalizarTareaPopup();
				}
				else
				{
	                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CUENTA_CLIENTE"));
	            }					

                break;
            case FenixConstants.CGD_LIQUIDAR_CONTRATO_DE_DESEMBOLSO:
                pollFinalizarTarea.setInterval(60000);
                pollFinalizarTarea.setInterval(60001);
                pollFinalizarTarea.setRendered(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(pollFinalizarTarea);
                if (validaBHQTransferenciaFT05LiquidarContrato()) {
                    LOGGER.warning("Se genero el BHQ de la transferencia del FT05.");
                    if (validarSaldoContratoDesembolsoLiquidarContrato()) {
                        LOGGER.warning("El saldo del contrato de desembolso es 0.");
                        finalizarTareaPopup();
                    } else {
                        String msg = getStringFromBundle(MSG_ERROR_SALDO_CONTRATO_DESEMBOLSO);
                        JSFUtils.addFacesErrorMessage(msg);
                        LOGGER.warning("validarSaldoContratoDesembolso devuelve false");
                    }
                } else {
                    String msg = getStringFromBundle(MSG_ERROR_BHQ_TRANSFERENCIA_FT05);
                    JSFUtils.addFacesErrorMessage(msg);
                    LOGGER.warning("validaBHQTransferenciaFT05LiquidarContrato devuelve false");
                }
                break;
                case  FenixConstants.PC06_APROBAR_FUERA_HORARIO:
                    finalizarTareaPopup();
                    break;
            }
            
        }else{
            //Abrir popup
            LOGGER.log(ADFLogger.WARNING, "No se ejecutan acciones del metodo invokeFinalizarTarea por validaciones de CA");
            
            if (!erroresCA.isEmpty()){
                for (String msg : erroresCA) {
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }          
        }
        LOGGER.exiting(this.getClass().getName(), "invocarFinalizarTarea", null);
        return null;
    }

    
    
    public Boolean validarTermino406(){            
        LOGGER.warning("*Inf, inicia metodo validarTermino406");
        Boolean respuesta = Boolean.FALSE;
        Map datos = new HashMap();
        
        BindingContainer binding = getBindings();
        OperationBinding operBinding = null;                
        
        try{
            
            Long idOperacion = this.getIdOperacion();            
            operBinding =  binding.getOperationBinding("validarTermino406");
            operBinding.getParamsMap().put("idOperacion", idOperacion);                
            operBinding.execute();
            
        }catch(Exception e){
          LOGGER.warning("*Error, OperationBinding validarTermino406 devuelve errores ->"+e.getMessage());
            LOGGER.warning("*Error, OperationBinding validarTermino406 devuelve errores ->"+e.getCause());
            LOGGER.warning("*Error, OperationBinding validarTermino406 devuelve errores ->"+e.getLocalizedMessage());
            LOGGER.warning("*Error, OperationBinding validarTermino406 devuelve errores ->"+e.getStackTrace());
            LOGGER.warning("*Error, OperationBinding validarTermino406 devuelve errores ->"+e.getSuppressed());
            LOGGER.warning("*Error, OperationBinding validarTermino406 devuelve errores ->"+e);
            e.printStackTrace();
          JSFUtils.addFacesErrorMessage("Error al validar termino 406 ->"+e.getMessage());
        }
        
        if (!operBinding.getErrors().isEmpty()) {
           LOGGER.warning("*Error, OperationBinding validarTermino406 devuelve errores ->"+operBinding.getErrors());
           JSFUtils.addFacesErrorMessage("Error: "+operBinding.getErrors());
        }else{
        
        if(operBinding.getResult() !=null){
                datos = (Map)operBinding.getResult();
                respuesta = (Boolean)datos.get("respuesta");
                Long clietneEnMora = (Long)datos.get("clietneEnMora");
               
                if(!respuesta){
                    JSFUtils.addFacesErrorMessage("El cliente "+clietneEnMora+" se encuentra en Mora y forma parte del Grupo Solidaroio: ");   
                }                        
        }
        
         LOGGER.warning("*Inf, termina el metodo validarTermino406" +respuesta );
        } 
       return respuesta;
    }
    
    
    public Boolean validarSaldoContratoDesembolsoLiquidarContrato(){
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        Long idDesembolso = Long.valueOf(desembolsoBean.getIdDesembolso().toString());
        Boolean respuesta = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBindingValidarSaldoContrato = bindings.getOperationBinding("validarSaldoContratoDesembolso");
        operationBindingValidarSaldoContrato.getParamsMap().put("idContratoDesembolso", idDesembolso);
        operationBindingValidarSaldoContrato.execute();
        respuesta = (Boolean)operationBindingValidarSaldoContrato.getResult();
        LOGGER.warning("Valor obtenido de metodo validarSaldoContratoDesembolsoLiquidarContrato" + respuesta);
        return respuesta;
    }
    
    public Boolean validaBHQTransferenciaFT05LiquidarContrato(){
        Boolean respuesta = Boolean.FALSE;
        Row row = null;
        String bhqTransferenciaFt05 = null;
        
        try{
            if (ADFUtils.getDCBindingContainer().findIteratorBinding("FormularioGeneracionFT05VOIterator") != null) {
                row = ADFUtils.getDCBindingContainer().findIteratorBinding("FormularioGeneracionFT05VOIterator").getCurrentRow();
                if(row != null)
                {
                    bhqTransferenciaFt05 = row.getAttribute("BhqTransferenciaFt05").toString();
                    LOGGER.warning("BhqTransferenciaFt05 es " + bhqTransferenciaFt05);
                    respuesta = Boolean.TRUE;
                }
                else{
                    LOGGER.warning("BhqTransferenciaFt05 es null");
                    respuesta = Boolean.FALSE;
                }
            }
            else{
                LOGGER.warning("Error al buscar BhqTransferenciaFt05 en FormularioGeneracionFT05VOIterator");
                respuesta = Boolean.FALSE;
            }
        }catch(Exception e){   
            LOGGER.log(ADFLogger.ERROR, "Error en metodo validaBHQFT05." + e.getClass() + "." + e.getMessage());
        }
        LOGGER.warning("Valor obtenido de metodo validaBHQTransferenciaFT05LiquidarContrato " + respuesta);
        return respuesta;
    }
    
    public Boolean validarFechaEstimadaDesembolsar(){
        LOGGER.warning("Inicia metodo validarFechaEstimadaDesembolsar");
        Boolean resultado = Boolean.TRUE;
        oracle.jbo.domain.Date fechaEstimadaDesembolsar = null;
        Boolean exito = Boolean.FALSE;
        
        try{
            fechaEstimadaDesembolsar = (oracle.jbo.domain.Date) ADFUtils.getBoundAttributeValue("FechaEstimadaDesembolsar");
            LOGGER.warning("La fecha estimada a desembolsar: " + fechaEstimadaDesembolsar);
            exito = Boolean.TRUE;
        }catch(Exception e){
            LOGGER.warning("ERROR al obtener la FechaEstimadaDesembolsar.", e);
        }
        
        if(exito){
            if(null == fechaEstimadaDesembolsar){
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_FECHA_ESTIMADA_DESEMBOLSAR"));
                resultado = Boolean.FALSE;
            }
        }else{
            LOGGER.warning("ERROR al recuperar la Fecha estimada a desembolsar.");
            resultado = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
        }
        
        LOGGER.warning("Termina metodo validarFechaEstimadaDesembolsar");
        return resultado;
    }
    
    public Boolean validarReglasPorTarea(){
        LOGGER.warning("Inicia metodo validarReglasPorTarea");
        Boolean resultado = Boolean.FALSE;
        Boolean esErrorMonto = Boolean.FALSE;
        Map<String, Object> params = new HashMap<String, Object>();
        Map mapaReglasValidadas = new HashMap();
        List<String> listaErrores = new ArrayList<String>();
        listaAdvertencias = new ArrayList<String>();
        String mensajeErrorValidacion = null;
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        Long idTarea = null;
        Long idContrato = null;
        String instanciaTarea = null;
        Long idLineaCredito = null;
        Long idOperacion = null;
        Integer codigoTarea = getCodigoTarea();
        Boolean esErrorProgramacion = Boolean.FALSE;
        
        if(null != desembolsoBean){
            LOGGER.warning("Obtener parametros para invocar metodo de validarReglasPorTareaService");
            desembolsoBean.setEsErrorProgramacion(Boolean.FALSE);
            
            try{
                idTarea = new Long(desembolsoBean.getIdTarea());
            }catch(Exception e){
                LOGGER.warning("ERROR al obtener el idTarea.", e);
            }
            
            try{
                idContrato = new Long(desembolsoBean.getIdDesembolso());
            }catch(Exception e){
                LOGGER.warning("ERROR al obtener el idContrato.", e);
            }
            
            try{
                instanciaTarea = desembolsoBean.getInstanciaTarea();
            }catch(Exception e){
                LOGGER.warning("ERROR al obtener el instanciaTarea.", e);
            }
            
            try{
                idLineaCredito = desembolsoBean.getLinea();
            }catch(Exception e){
                LOGGER.warning("ERROR al obtener el idLineaCredito.", e);
            }
            
            try{
                idOperacion = new Long(desembolsoBean.getIdOperacion());
            }catch(Exception e){
                LOGGER.warning("ERROR al obtener el idOperacion.", e);
            }
        }
        
        LOGGER.warning("idContrato: " + idContrato);
        LOGGER.warning("idTarea: " + idTarea);
        LOGGER.warning("instanciaTarea: " + instanciaTarea);
        LOGGER.warning("idLineaCredito: " + idLineaCredito);
        
        LOGGER.warning("Añadir datos a mapa de parametros de entrada.");
        params.put("idContratoDesembolso", idContrato);
        params.put("idTarea", idTarea);
        params.put("instanciaTarea", instanciaTarea);
        
        try{
            LOGGER.warning("Ejecutar operationBinding de validarReglasPorTareaService");
            mapaReglasValidadas = super.execute(params, "validarReglasPorTareaService");
        }catch(Exception e){
            LOGGER.warning("ERROR al ejecutar el operationBinding validarReglasPorTareaService.", e);
            JSFUtils.addFacesErrorMessage("ERROR al ejecutar el operationBinding validarReglasPorTareaService: " + e.getMessage());
            return resultado;
        }        
        

        Long idRegla = null;
        String estado = null;       
        String mensajeRegla22 = null;
        if(!mapaReglasValidadas.isEmpty() && mapaReglasValidadas.size() > 0){
            
            Iterator iter = mapaReglasValidadas.keySet().iterator();
            while(iter.hasNext()){
                try{
                    idRegla = (Long) iter.next();
                    try{
                        estado = (String) mapaReglasValidadas.get(idRegla);
                        mensajeRegla22 = (String) mapaReglasValidadas.get("mensajeErrorRegla22");
                    }catch(Exception e){
                        LOGGER.warning("ERROR al obtener el estado de la regla: " + idRegla);
                    }
                    if(null != idRegla){
                        LOGGER.warning("Obteniendo resultados de regla: " + idRegla);
                        LOGGER.warning("Estado de la regla: " + estado);
                        switch (codigoTarea) {
                            case FenixConstants.CGD_APROBAR_DESEMBOLSO:
                                LOGGER.warning("Ingresa al case para obtener estado reglas validadas Aprobar Desembolso"); 
                                mensajeErrorValidacion = obtenerEstadoReglasValidadas(estado, idRegla);
                                break;
                            case FenixConstants.CGD_GESTIONAR_PROGRAMACION:
                                LOGGER.warning("Ingresa al case para obtener estado reglas validadas Aprobar Desembolso"); 
                                mensajeErrorValidacion = obtenerEstadoReglasValidadasGestionarProgramacion(estado, idRegla);
                                break;
                            case FenixConstants.CGD_RESERVAR_FONDOS:
                                LOGGER.warning("Ingresa al case para obtener estado reglas validadas Aprobar Desembolso"); 
                                mensajeErrorValidacion = obtenerEstadoReglasValidadasReservarFondos(estado, idRegla);
                                break;
                            case FenixConstants.CGD_REALIZAR_AJUSTES_A_DESEMBOLSO:
                                LOGGER.warning("Ingresa al case para obtener estado reglas validadas Realizar ajustes a desembolso"); 
                                mensajeErrorValidacion = obtenerEstadoReglasValidadasRealizarAjustesDesembolso(estado, idRegla, mensajeRegla22);
                                try{
                                    if(idRegla.compareTo(FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION)==0){
                                        if(null != mensajeErrorValidacion){
                                            LOGGER.warning("Es error de PROGRAMACION, se invocará POPUP.");
                                            if(getStringFromBundle("ERROR_ESTADO_FLEXCUBE").equalsIgnoreCase(mensajeErrorValidacion))
                                            {
                                                //no se muestra es error programacion
                                            }
                                            else
                                            {
                                                mensajeErrorValidacion = null;
                                                esErrorProgramacion = Boolean.TRUE;
                                                desembolsoBean.setEsErrorProgramacion(Boolean.TRUE);   
                                            }
                                        }
                                    }
                                }catch(Exception e){
                                    LOGGER.warning("ERROR al mostrar POPUP de PROGRAMACION.", e);
                                }
                                break;
                            default:
                                LOGGER.warning("Ingresa al case (default) para obtener estado reglas validadas"); 
                                break;
                        }
                        if(null != mensajeErrorValidacion){
                            LOGGER.warning("Añadiendo listado de mensajes.");
                            
                            //verificamos si existe este error en la lista previo a agregarlo
                            if(!listaErrores.contains(mensajeErrorValidacion))
                            {
                                listaErrores.add(mensajeErrorValidacion);
                            }
                        }
                    }
                }catch(Exception e){
                    LOGGER.warning("ERROR al castear el idTarea.");
                }
            }
        }else{
            LOGGER.warning("ERROR: No se obtivieron reglas validadas por servicio.");
            //NOTA: Se hace ajuste para NO mostrar errores en caso de no recibir reglas para evaluar.
            //listaErrores.add(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
        }
        
        /* 
         * Se validan reglas que no son por servicio
         */
        //Integer codigoTarea = getCodigoTarea();
        switch(codigoTarea){
            case FenixConstants.CGD_APROBAR_DESEMBOLSO:
            //RN_09: Monto de la linea no suficiente para monto desembolsar del contrato.
            LOGGER.warning("Obteniendo datos de LineaCredito");
            mensajeErrorValidacion = validarMontoLineaCredito(idLineaCredito);
            if(null != mensajeErrorValidacion){
                LOGGER.warning("Añadiendo listado de mensajes.");
                listaErrores.add(mensajeErrorValidacion);
            }
            
            //RN_11: El aprobador del contrato de desembolso no debe ser el mismo usuario que lo creo.
            mensajeErrorValidacion = validarAprobador();
            if(null != mensajeErrorValidacion){
                LOGGER.warning("Añadiendo listado de mensajes.");
                listaErrores.add(mensajeErrorValidacion);
            }
            
            //RN_12: Para poder aprobar el contrato de desembolso la fecha del sistema FENIX debe ser menor o igual a la fecha maxima para iniciar desembolsos (T102).
            //RN_13: Para poder aprobar el contrato de desembolso la fecha del sistema  FENIX debe ser menor a la fecha para desembolsar la totalidad de los recursos (T103).
            LOGGER.warning("Validando Fecha Inicio desembolsos y Fecha de desembolso de la totalidad de recursos.");
            List<String> listaErroresFecha = new ArrayList<String>();
            listaErroresFecha = validarFechasInicioDesembolsoTotalidadRecursos(idOperacion);
            for(String mensajeFechas : listaErroresFecha){
                LOGGER.warning("Obteniendo errores de validacion de fechas.");
                listaErrores.add(mensajeFechas);
            }
            break;
            case FenixConstants.CGD_RESERVAR_FONDOS:
              LOGGER.warning("validando reglas ADF para reserva de fondos");
              String mensajeError = getStringFromBundle("ERROR_MS3_RESERVA_FONDOS");
              Boolean respuesta = validarReservaContrato(idContrato);
            if(!respuesta){                
                    LOGGER.warning("***Error, no todas las transferencias del contrato cuentan con la reserva");
                    listaErrores.add(mensajeError);
            }else{
                    LOGGER.warning("Ok regla cumplida");
                }
            
            break;
            case FenixConstants.CGD_REALIZAR_AJUSTES_A_DESEMBOLSO:
            LOGGER.warning("validando reglas ADF para realizar ajustes a desembolso");
            
            //Evaluando RN_12: Para poder finalizar la tarea el monto de la asignación de recursos
            //debe ser igual al monto del contrato de desembolso.
            if(validarMontoContratoVsAsignacion()){
                LOGGER.warning("RN_12 Cumplida. La asignacion de recursos COINCIDE con el motno a desembolsar.");
            }else{
                LOGGER.warning("RN_12 No cumplida. La Asigancion de recursos NO coincide con el monto a desembolsar.");
                listaErrores.add(getStringFromBundle("ERROR_VALIDACION_MONTO_CONTRATO_ASIGNACION"));
            }
            
            //Evaluando RN_02 y RN_03 Fecha del sistema VS Fecha Maxima (T102)
            //y Fecha de la totalidad de los recursos (T103).
            LOGGER.warning("Validando Fecha Inicio desembolsos y Fecha de desembolso de la totalidad de recursos.");
            List<String> listaErroresFechaTerminos = new ArrayList<String>();
            listaErroresFechaTerminos = validarFechasInicioDesembolsoTotalidadRecursos(idOperacion);
            for(String mensajeFechas : listaErroresFechaTerminos){
                LOGGER.warning("Obteniendo errores de validacion de fechas.");
                listaErrores.add(mensajeFechas);
            }
            
            //RN_09: Monto de la linea no suficiente para monto desembolsar del contrato.
            LOGGER.warning("Obteniendo datos de LineaCredito");
            mensajeErrorValidacion = validarMontoLineaCredito(idLineaCredito);
            if(null != mensajeErrorValidacion){
                LOGGER.warning("Añadiendo listado de mensajes.");
                listaErrores.add(mensajeErrorValidacion);
            }
            
            //RN_06: El monto del contrato de estar dentro de los limites de la linea.
            esErrorMonto = validarLimitesTasaMonto();
            if(null == esErrorMonto){
                esErrorMonto = Boolean.FALSE;//Se asigna FALSE para permitir mostrar el error.
            }
            
            if(esErrorMonto){
                mensajeErrorValidacion = getStringFromBundle("ERROR_VALIDACION_LIMITES_MONTO");
                listaErrores.add(mensajeErrorValidacion);
            }
            
            //RN_14 Fecha estimada a desembolsar menor a la fecha actual.
            mensajeErrorValidacion = validarFechaEstimadaVSSistema();
            if(null != mensajeErrorValidacion){
                LOGGER.warning("Añadiendo listado de mensajes.");
                listaErrores.add(mensajeErrorValidacion);
            }
            
            break;
        }
        
        
        
        if(listaErrores.size() > 0){
            if(esErrorProgramacion){
                desembolsoBean.setListaErrores(listaErrores);
                desembolsoBean.setEsErrorProgramacion(Boolean.FALSE);
                mostrarPopupProgramacion();
            }else{
                LOGGER.warning("Iterando lista de errores para impresion en pantalla.");
                for(String mensaje : listaErrores){
                    JSFUtils.addFacesErrorMessage(mensaje);
                }
            }
        }else{
            LOGGER.warning("La lista de errores es vacía.");
            if(esErrorProgramacion){
                desembolsoBean.setListaErrores(listaErrores);
                desembolsoBean.setEsErrorProgramacion(Boolean.FALSE);
                mostrarPopupProgramacion();
            }else{
                resultado = Boolean.TRUE;
            }
        }
        
        LOGGER.warning("Termina metodo validarReglasPorTarea");
        return resultado;
    }
    
    public void mostrarPopupProgramacion(){
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();        
        getAlertaProgramacionUIC().show(popupHints);
    }
    
    public String validarFechaEstimadaVSSistema(){
        LOGGER.warning("Inicia metodo validarFechaEstimadaVSSistema.");
        String mensajeError = null;
        java.util.Date fechaActual = new java.util.Date();
        oracle.jbo.domain.Date fechaEstimadaDesembolsar = null;
        java.util.Date fechaEstimada = null;
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaEstimadaStr = null;
        String fechaActualStr = null;
        
        try{
            fechaEstimadaDesembolsar = (oracle.jbo.domain.Date) ADFUtils.getBoundAttributeValue("FechaEstimadaDesembolsar");
        }catch(Exception e){
            LOGGER.warning("ERROR al obtener la Fecha estimada a desembolsar.", e);
        }
        
        if(null != fechaEstimadaDesembolsar){
            try{
                java.sql.Date sqldate = fechaEstimadaDesembolsar.dateValue();
                fechaEstimada = new java.util.Date(sqldate.getTime());
            }catch(Exception e){
                LOGGER.warning("ERROR al convertir fechaEstimadaDesembolsar a java.util.Date", e);
            }
            
            LOGGER.warning("Fecha actual del sistema: " + fechaActual);
            LOGGER.warning("Fecha estimada a desembolsar: " + fechaEstimada);
            
            if(null != fechaActual && null != fechaEstimada){
            try{
                fechaEstimadaStr = dmyFormat.format(fechaEstimada);
                fechaActualStr = dmyFormat.format(fechaActual);
                
                LOGGER.warning("Fecha actual del sistema STR: " + fechaActualStr);
                LOGGER.warning("Fecha estimada a desembolsar STR: " + fechaEstimadaStr);
                
                fechaActual = dmyFormat.parse(fechaActualStr);
                fechaEstimada = dmyFormat.parse(fechaEstimadaStr);
                
                LOGGER.warning("Fecha actual del sistema: " + fechaActual);
                LOGGER.warning("Fecha estimada a desembolsar: " + fechaEstimada);
            }catch(Exception e){
                LOGGER.warning("ERROR al dar formato a las fechas.", e);
            }
            
            try{
                if(fechaEstimada.before(fechaActual)){
                    LOGGER.warning("La fecha estimada a desembolsar es menor a la fecha del sistema.");
                    mensajeError = getStringFromBundle("ERROR_FECHA_ACTUAL_MAYOR_MSG");
                }
            }catch(Exception e){
                LOGGER.warning("ERROR al comparar las fechas.", e);
                mensajeError = getStringFromBundle("ERROR_FECHA_ESTIMADA_DESEMBOLSAR");
            }
            }else{
                LOGGER.warning("No se pudo validar fecha actual contra Fecha estimada a desembolsar.");
                mensajeError = "No se pudo validar fecha actual contra Fecha estimada a desembolsar.";
                if(null == fechaEstimada){
                    LOGGER.warning("La fecha estimada a desembolsar del contrato es NULL.");
                    mensajeError = mensajeError.concat(" La fecha estimada a desembolsar del contrato es NULL.");
                }
                
                if(null == fechaActual){
                    LOGGER.warning("La fecha actual del sistema es NULL.");
                    mensajeError = mensajeError.concat(" La fecha actual del sistema es NULL.");
                }
            }
        }else{
            LOGGER.warning("La FechaEstimadaDesembolsar es NULL.");
            mensajeError = getStringFromBundle("MSG_ERROR_FECHA_ESTIMADA_DESEMBOLSAR");
        }
        
        LOGGER.warning("Termina metodo validarFechaEstimadaVSSistema.");
        return mensajeError;
    }
    
    public String validarAprobador(){
        LOGGER.warning("Inicia metodo validarAprobador");
        String mensajeError = null;
        String creadorContrato = null;
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        try{
            creadorContrato = obtenerUsuario();
            LOGGER.warning("Usuario que creó el contrato: " + creadorContrato);
        }catch(Exception e){
            LOGGER.warning("ERROR al obtener el attributeValue de loginUsuario", e);
        }
        
        if(null != creadorContrato){
            String usuario = desembolsoBean.getUsuarioActivo();
            if(null != usuario){
                LOGGER.warning("Usuario en pantalla BPM: " + usuario);
                if(creadorContrato.equalsIgnoreCase(usuario)){
                    LOGGER.warning("El creador del contrato es el mismo que el aprobador.");
                    mensajeError = getStringFromBundle("ERROR_PERSONA_CONTRATO_MSG");
                }else{
                    LOGGER.warning("El creador del contrato es diferente que el aprobador.");
                }
            }else{
                LOGGER.warning("ERROR el usuario del payload es vacio.");
                mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
            }
        }else{
            LOGGER.warning("El loginUsuario que creo el contrato es NULL");
            mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
        }
        
        LOGGER.warning("Termina metodo validarAprobador");
        return mensajeError;
    }
    
    public String obtenerEstadoReglasValidadas(String descripcionEstadoRegla, Long idReglaPorValidar){
        LOGGER.warning("Inicia metodo obtenerEstadoReglasValidadas");
        String mensajeError = null;
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        
        if(null != descripcionEstadoRegla){
            LOGGER.warning("Evaluando estado de regla.");            
            if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_CUMPLIDA")) ||
               descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_EXCEPTUADA")) ||
                descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_NO_EVALUADA_2"))){
                LOGGER.warning("Regla validada correctamente.");
            }
            else if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_ERROR_FLEXCUBE")))
            {
                mensajeError = getStringFromBundle("ERROR_ESTADO_FLEXCUBE");
            }
            else{
                LOGGER.warning("La validacion de la regla no se cumplio.");
                if(FenixConstants.ID_REGLA_TRANSACCION_LIMITES.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_PROGRAMACION_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_PROGRAMACION_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_SCR_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_SCR_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_MORA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_MORA_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_MORA_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_CONDICIONES.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_CONDICIONES_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_CONDICIONES_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_SCR_EXCEPCION_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_SCR_EXCEPCION_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_UMIPYME.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_UMIPYME_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_UMIPYME_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PRESENTACION_F1.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_F1_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_F1_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_AJUSTE_TASA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_AJUSTE_TASA_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_AJUSTE_TASA_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_MOMENTO_COBRO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_MOMENTO_COBRO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_MOMENTO_COBRO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_COMPONENTE_INTERES.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_COMPONENTE_INTERES_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_COMPONENTE_INTERES_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_PLAZO_CAPITAL_MAXIMO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MAXIMO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PERIODO_GRACIA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_PERIODO_GRACIA_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_PERIODO_GRACIA_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_PLAZO_CAPITAL_MINIMO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MINIMO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_TOTAL_LINEA_CREDITO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_TOTAL_LINEA_CREDITO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_LAFT.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_LAFT_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_LAFT_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PROXIMA_HORA_CIERRE_MONEDA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_PROXIMA_HORA_CIERRE_MONEDA_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_PROXIMA_HORA_CIERRE_MONEDA_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PREVIA_HORA_CIERRE_MONEDA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_PREVIA_HORA_CIERRE_MONEDA_SCR_MSG"));
                        //Se modifica el payload para indicar que no cumplio con el horario de desembolso
                        desembolsoBean.setAprobarFueraHorario(1L);
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_PREVIA_HORA_CIERRE_MONEDA_SCR_MSG");
                        desembolsoBean.setAprobarFueraHorario(0L);
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_LIMITE_GLOBAL.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_TOTAL_LIMITE_GLOBAL_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_TOTAL_LIMITE_GLOBAL_MSG");
                    }
                }
                //TODO REGISTRO_CONTRATO_DESEMBOLSO Y RESERVA_FONDOS
            }
        }else{
            LOGGER.warning("descripcionEstadoRegla es NULL, No se pudo obtener el estado de la regla de negocio.");
            LOGGER.warning("Regla No encontrada por posible inactivacion: " + idReglaPorValidar);
        }
        LOGGER.warning("Termina metodo obtenerEstadoReglasValidadas");
        return mensajeError;
    }
    
    public String obtenerEstadoReglasValidadasRealizarAjustesDesembolso(String descripcionEstadoRegla, Long idReglaPorValidar, String mensajeRegla22){
        LOGGER.warning("Inicia metodo obtenerEstadoReglasValidadasRealizarAjustesDesembolso");
        String mensajeError = null;
        
        if(null != descripcionEstadoRegla){
            LOGGER.warning("Evaluando estado de regla.");            
            if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_CUMPLIDA")) ||
               descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_EXCEPTUADA"))||
                descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_NO_EVALUADA_2"))){
                LOGGER.warning("Regla validada correctamente.");
            }
            else if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_ERROR_FLEXCUBE")))
            {
                mensajeError = getStringFromBundle("ERROR_ESTADO_FLEXCUBE");
            }
            else{
                LOGGER.warning("La validacion de la regla no se cumplio.");
                if(FenixConstants.ID_REGLA_TRANSACCION_LIMITES.compareTo(idReglaPorValidar)==0){
                    ///JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_EXCEDIDO_LIMITE_PAIS_GRUPO_ECONOMICO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_LIMITE_POSIBLE_DESEMBOLSAR_SUPERADO_MGS");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_PROGRAMACION_MSG"));
                    mensajeError = getStringFromBundle("ERROR_DESEMBOLSO_NO_ESTA_PROGRAMACION_MES_VIGENTE_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_SCR_MSG"));
                    mensajeError = getStringFromBundle("ERROR_SCR_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_MORA.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_MORA_MSG"));
                    mensajeError = getStringFromBundle("ERROR_CLIENTE_MORA_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_CONDICIONES.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_CONDICIONES_MSG"));
                    mensajeError = getStringFromBundle("ERROR_CONDICIONES_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_SCR_EXCEPCION_MSG"));
                    mensajeError = getStringFromBundle("ERROR_SCR_EXCEPCION_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_UMIPYME.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_UMIPYME_MSG"));
                    mensajeError = getStringFromBundle("ERROR_UMIPYME_2_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PRESENTACION_F1.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_F1_MSG"));
                    mensajeError = getStringFromBundle("ERROR_F1_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_AJUSTE_TASA.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_AJUSTE_TASA_MSG"));
                    mensajeError = getStringFromBundle("ERROR_AJUSTE_TASA_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_MOMENTO_COBRO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_MOMENTO_COBRO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_MOMENTO_COBRO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_COMPONENTE_INTERES.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_COMPONENTE_INTERES_MSG"));
                    mensajeError = getStringFromBundle("ERROR_COMPONENTE_INTERES_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_PLAZO_CAPITAL_MAXIMO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MAXIMO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PERIODO_GRACIA.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_PERIODO_GRACIA_MSG"));
                    mensajeError = getStringFromBundle("ERROR_PERIODO_GRACIA_EXCEDE_MAXIMO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_PLAZO_CAPITAL_MINIMO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MINIMO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_TOTAL_LINEA_CREDITO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_LIMITE_EXCEDIDO_PROGRAMA_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_LAFT.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_LAFT_MSG"));
                    mensajeError = getStringFromBundle("ERROR_LAFT_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PROXIMA_HORA_CIERRE_MONEDA.compareTo(idReglaPorValidar)==0){
                    mensajeError = getStringFromBundle("ERROR_PROXIMA_HORA_CIERRE_MONEDA_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PREVIA_HORA_CIERRE_MONEDA.compareTo(idReglaPorValidar)==0){
                    mensajeError = getStringFromBundle("ERROR_PREVIA_HORA_CIERRE_MONEDA_SCR_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_LIMITE_GLOBAL.compareTo(idReglaPorValidar)==0){
                    mensajeError = mensajeRegla22;
                }
                //TODO REGISTRO_CONTRATO_DESEMBOLSO Y RESERVA_FONDOS
            }
        }else{
            LOGGER.warning("descripcionEstadoRegla es NULL, No se pudo obtener el estado de la regla de negocio.");
            LOGGER.warning("Regla No encontrada por posible inactivacion: " + idReglaPorValidar);
        }
        LOGGER.warning("Termina metodo obtenerEstadoReglasValidadasRealizarAjustesDesembolso");
        return mensajeError;
    }
    
    public String obtenerEstadoReglasValidadasGestionarProgramacion(String descripcionEstadoRegla, Long idReglaPorValidar){
        LOGGER.warning("Inicia metodo obtenerEstadoReglasValidadasGestionarProgramacion");
        String mensajeError = null;
        
        if(null != descripcionEstadoRegla){
            LOGGER.warning("Evaluando estado de regla.");            
            if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_CUMPLIDA")) ||
               descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_EXCEPTUADA"))||
                descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_NO_EVALUADA_2"))){
                LOGGER.warning("Regla validada correctamente.");
            }
            else if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_ERROR_FLEXCUBE")))
            {
                mensajeError = getStringFromBundle("ERROR_ESTADO_FLEXCUBE");
            }
            else{
                LOGGER.warning("La validacion de la regla no se cumplio.");
                if(FenixConstants.ID_REGLA_TRANSACCION_LIMITES.compareTo(idReglaPorValidar)==0){
                    ///JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_PROGRAMACION_MSG"));
                    mensajeError = getStringFromBundle("ERROR_REGLA_PROGRAMACION_GESTIONARPROG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_SCR_MSG"));
                    mensajeError = getStringFromBundle("ERROR_SCR_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_MORA.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_MORA_MSG"));
                    mensajeError = getStringFromBundle("ERROR_MORA_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_CONDICIONES.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_CONDICIONES_MSG"));
                    mensajeError = getStringFromBundle("ERROR_CONDICIONES_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_SCR_EXCEPCION_MSG"));
                    mensajeError = getStringFromBundle("ERROR_SCR_EXCEPCION_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_UMIPYME.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_UMIPYME_MSG"));
                    mensajeError = getStringFromBundle("ERROR_UMIPYME_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PRESENTACION_F1.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_F1_MSG"));
                    mensajeError = getStringFromBundle("ERROR_F1_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_AJUSTE_TASA.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_AJUSTE_TASA_MSG"));
                    mensajeError = getStringFromBundle("ERROR_AJUSTE_TASA_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_MOMENTO_COBRO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_MOMENTO_COBRO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_MOMENTO_COBRO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_COMPONENTE_INTERES.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_COMPONENTE_INTERES_MSG"));
                    mensajeError = getStringFromBundle("ERROR_COMPONENTE_INTERES_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_PLAZO_CAPITAL_MAXIMO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MAXIMO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PERIODO_GRACIA.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_PERIODO_GRACIA_MSG"));
                    mensajeError = getStringFromBundle("ERROR_PERIODO_GRACIA_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_PLAZO_CAPITAL_MINIMO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MINIMO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_TOTAL_LINEA_CREDITO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_TOTAL_LINEA_CREDITO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_LAFT.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_LAFT_MSG"));
                    mensajeError = getStringFromBundle("ERROR_LAFT_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PROXIMA_HORA_CIERRE_MONEDA.compareTo(idReglaPorValidar)==0){
                    mensajeError = getStringFromBundle("ERROR_PROXIMA_HORA_CIERRE_MONEDA_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PREVIA_HORA_CIERRE_MONEDA.compareTo(idReglaPorValidar)==0){
                    mensajeError = getStringFromBundle("ERROR_PREVIA_HORA_CIERRE_MONEDA_SCR_MSG");
                }
                //TODO REGISTRO_CONTRATO_DESEMBOLSO Y RESERVA_FONDOS
            }
        }else{
            LOGGER.warning("descripcionEstadoRegla es NULL, No se pudo obtener el estado de la regla de negocio.");
            LOGGER.warning("Regla No encontrada por posible inactivacion: " + idReglaPorValidar);
        }
        LOGGER.warning("Termina metodo obtenerEstadoReglasValidadasGestionarProgramacion");
        return mensajeError;
    }
    
    public String obtenerEstadoReglasValidadasReservarFondos(String descripcionEstadoRegla, Long idReglaPorValidar){
        LOGGER.warning("Inicia metodo obtenerEstadoReglasValidadasReservarFondos");
        String mensajeError = null;
        
        if(null != descripcionEstadoRegla){
            LOGGER.warning("Evaluando estado de regla.");            
            if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_CUMPLIDA")) ||
               descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_EXCEPTUADA"))||
                descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_NO_EVALUADA_2"))){
                LOGGER.warning("Regla validada correctamente.");
            }
            else if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_ERROR_FLEXCUBE")))
            {
                mensajeError = getStringFromBundle("ERROR_ESTADO_FLEXCUBE");
            }
            else{
                LOGGER.warning("La validacion de la regla no se cumplio.");
                if(FenixConstants.ID_REGLA_TRANSACCION_LIMITES.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_REGLA_PROGRAMACION_RESERVAFONDO"));
                    }
                    else 
                    {
                        mensajeError = getStringFromBundle("ERROR_REGLA_PROGRAMACION_RESERVAFONDO");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_SCR_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_SCR_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_MORA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_MORA_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_MORA_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_CONDICIONES.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_CONDICIONES_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_CONDICIONES_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_SCR_EXCEPCION_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_SCR_EXCEPCION_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_UMIPYME.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_UMIPYME_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_UMIPYME_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PRESENTACION_F1.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_F1_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_F1_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_AJUSTE_TASA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_AJUSTE_TASA_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_AJUSTE_TASA_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_MOMENTO_COBRO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_MOMENTO_COBRO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_MOMENTO_COBRO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_COMPONENTE_INTERES.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_COMPONENTE_INTERES_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_COMPONENTE_INTERES_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_PLAZO_CAPITAL_MAXIMO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MAXIMO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PERIODO_GRACIA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_PERIODO_GRACIA_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_PERIODO_GRACIA_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_PLAZO_CAPITAL_MINIMO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_PLAZO_CAPITAL_MINIMO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_TOTAL_LINEA_CREDITO_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_TOTAL_LINEA_CREDITO_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_LAFT.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_LAFT_MSG"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_LAFT_MSG");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PROXIMA_HORA_CIERRE_MONEDA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_REGLA_PROXIMA_HORA_CIERRE_RESERVAFONDO"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_REGLA_PROXIMA_HORA_CIERRE_RESERVAFONDO");
                    }
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PREVIA_HORA_CIERRE_MONEDA.compareTo(idReglaPorValidar)==0){
                    if(descripcionEstadoRegla.equalsIgnoreCase("NO_CUMPLIDA_ADVERTENCIA"))
                    {
                        listaAdvertencias.add(getStringFromBundle("ERROR_REGLA_PREVIA_HORA_CIERRE_RESERVAFONDO"));
                    }
                    else
                    {
                        mensajeError = getStringFromBundle("ERROR_REGLA_PREVIA_HORA_CIERRE_RESERVAFONDO");
                    }
                }
                //TODO REGISTRO_CONTRATO_DESEMBOLSO Y RESERVA_FONDOS
            }
        }else{
            LOGGER.warning("descripcionEstadoRegla es NULL, No se pudo obtener el estado de la regla de negocio.");
            LOGGER.warning("Regla No encontrada por posible inactivacion: " + idReglaPorValidar);
        }
        LOGGER.warning("Termina metodo obtenerEstadoReglasValidadasReservarFondos");
        return mensajeError;
    }

    public String validarMontoLineaCredito(Long idLineaCredito) {
        LOGGER.warning("Inicia metodo validarMontoLineaCredito");
        String mensajeError = null;
        BigDecimal montoDesembolsarContrato = null;
        BigDecimal montoDisponibleLineaCredito = null;
        Map<String, Object> params = new HashMap<String, Object>();

        if (null != idLineaCredito) {
            params.put("idLineaCredito", idLineaCredito);

            try {
                montoDesembolsarContrato = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoDesembolsar");
                LOGGER.warning("MontoDesembolsar de contrato: " + montoDesembolsarContrato);
            } catch (Exception e) {
                LOGGER.warning("ERROR al obtener el monto a desembolsar del contrato.", e);
            }

            try {
                montoDisponibleLineaCredito = obtenerMontoDisponibleLinea();
                LOGGER.warning("Monto disponible Linea credito: " + montoDisponibleLineaCredito);
            } catch (Exception e) {
                LOGGER.warning("Error al obtener MontoDisponible de la linea de credito: ", e);
            }

            if (null != montoDisponibleLineaCredito) {
                if (null != montoDesembolsarContrato) {

                    Integer idTipoMoneda = null;

                    try {
                        idTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
                    } catch (Exception e) {
                        LOGGER.warning("ERROR al obtener el idTipoMoneda.");
                    }

                    if (null != idTipoMoneda) {
                        Map<String, Object> paramsMoneda = new HashMap<String, Object>();
                        BigDecimal montoConvertido = null;

                        params.put("claveTipo", idTipoMoneda);
                        params.put("codigoA", COD_EXT_MONEDA_LINEA_CREDITO);
                        params.put("monto", montoDesembolsarContrato);
                        try {
                            montoConvertido = execute(params, "convertirMonedas");
                            LOGGER.warning("Monto convertido: " + montoConvertido);
                        } catch (Exception e) {
                            LOGGER.warning("ERROR al convertir el monto a otro tipo de moneda.", e);
                        }

                        if (null != montoConvertido) {
                            if (montoDisponibleLineaCredito.compareTo(montoConvertido) == 0 ||
                                montoDisponibleLineaCredito.compareTo(montoConvertido) == 1) {
                                LOGGER.warning("Monto disponible de la linea validado correctamente.");
                            } else {
                                LOGGER.warning("ERROR, el monto de la linea de credito es menor que el monto a desembolsar del contrato");
                                mensajeError = getStringFromBundle("LINEA_CREDITO_SIN_DISPONIBILIDAD_ERROR");
                            }
                        } else {
                            LOGGER.warning("ERROR no se pudo recuperar el monto convertido del contrato de desembolso.");
                            mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
                        }
                    } else {
                        LOGGER.warning("ERROR el tipo de moneda es NULL. No se pudo validar el monto disponible de la linea.");
                        mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
                    }
                } else {
                    LOGGER.warning("Monto a desembolsar del contrato de desembolso es NULL.");
                    mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
                }
            } else {
                LOGGER.warning("Monto disponible de linea es NULL.");
                mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
            }
        } else {
            LOGGER.warning("Parametro idLineaCredito es NULL.");
            mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
        }

        LOGGER.warning("Termina metodo validarMontoLineaCredito");
        return mensajeError;
    }
    
    public List<String> validarFechasInicioDesembolsoTotalidadRecursos(Long idOperacion) {
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        LOGGER.warning("Inicia metodo validarFechasInicioDesembolsoTotalidadRecursos");
        List<String> listaErrores = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        Long lineaCredito = null;
        try {
            if (null != desembolsoBean.getLinea()) {
                lineaCredito = desembolsoBean.getLinea();
            } else {
                if (null != desembolsoBean.getIdLineaCredito() &&
                    !desembolsoBean.getIdLineaCredito().equalsIgnoreCase("")) {
                    lineaCredito = Long.parseLong(desembolsoBean.getIdLineaCredito());
                } else {
                    if (null != desembolsoBean.getPidLineaCredito()) {
                        lineaCredito = desembolsoBean.getPidLineaCredito();
                    }
                }

            }
        } catch (Exception e) {
            LOGGER.warning("ERROR al obtener el idLineaCredito.", e);
        }

        LOGGER.warning("*Inf, idOperacion: "+idOperacion);
        LOGGER.warning("*Inf, lineaCredito: "+lineaCredito);

        if (null != idOperacion && null != lineaCredito) {
            params.put("idOperacion", idOperacion);
            params.put("idLinea", lineaCredito);
            LOGGER.warning("Invocando metodo para validar las fechas de inicio de desembolsos y desembolso de la totalidad de los recursos");
            try {
                listaErrores = execute(params, "recuperaFechasTerminosLineaDesembolso");
                LOGGER.warning("Registros de la lista de errores: " + listaErrores.size());
            } catch (Exception e) {
                LOGGER.warning("Error al ejecutar el operationBinding para validar las fechas de terminos.", e);
                listaErrores.add(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
            }
        } else {
            LOGGER.warning(" parametros requeridos IdOperacion, idLinea NULL.");
            listaErrores.add(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
        }

        LOGGER.warning("Termina metodo validarFechasInicioDesembolsoTotalidadRecursos");
        return listaErrores;
    }
    
    public List<String> validarFechasInicioDesembolsoTotalidadRecursosAsignRecursos(Long idOperacion) {
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        LOGGER.warning("Inicia metodo validarFechasInicioDesembolsoTotalidadRecursosAsignRecursos");
        List<String> listaErrores = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        Long idSolicitud = null;
        
        try {
            idSolicitud = new Long(desembolsoBean.getIdSolicitud());
        } catch (Exception e) {
            LOGGER.warning("ERROR al obtener el idSolicitud.", e);
        }

        LOGGER.warning("*Inf, idOperacion: "+idOperacion);
        LOGGER.warning("*Inf, idSolicitud: "+idSolicitud);

        if (null != idOperacion && null != idSolicitud) {
            params.put("idOperacion", idOperacion);
            params.put("idSolicitud", idSolicitud);
            LOGGER.warning("Invocando metodo para validar las fechas de inicio de desembolsos y desembolso de la totalidad de los recursos");
            try {
                listaErrores = execute(params, "validarFechasInicioDesembolsoTotalidadRecursosPorSolicitud");
                LOGGER.warning("Registros de la lista de errores: " + listaErrores.size());
            } catch (Exception e) {
                LOGGER.warning("Error al ejecutar el operationBinding para validar las fechas de terminos.", e);
                listaErrores.add(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
            }
        } else {
            LOGGER.warning("Parametros requeridos NULL. IdOperacion: " + idOperacion + ", idSolicitud: " + idSolicitud);
            listaErrores.add(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
        }

        LOGGER.warning("Termina metodo validarFechasInicioDesembolsoTotalidadRecursosAsignRecursos");
        return listaErrores;
    }
    
    public BigDecimal obtenerMontoDisponibleDesembolsarLineaCredito(){
        LOGGER.warning("Inicia metodo obtenerMontoDisponibleDesembolsarLineaCredito.");
        BigDecimal montoDisponibleDesembolsarLinea = null;
        Long idLineaCredito = null;
        Map<String, Object> params = new HashMap<String, Object>();
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        Map resultMap = new HashMap();
        
        try{
            idLineaCredito = new Long(desembolsoBean.getIdLineaCredito());
        }catch(Exception e){
            LOGGER.warning("ERROR al recuperar IdLineaCredito. ", e);
        }
        LOGGER.warning("IdLineaCredito a consultar: " + idLineaCredito);
        
        if(null != idLineaCredito){
            params.put("idLineaCredito", idLineaCredito);
            params.put("tipoMoneda", null);
            try{
                resultMap = execute(params, "consultarLineaCreditoByIdLineaCredito");
            }catch(Exception e){
                LOGGER.warning("ERROR al ejecutar OperBinding consultarLineaCreditoByIdLineaCredito.", e);
            }
        }else{
            LOGGER.warning("IdLineaCredito es NULL. No se puede obtener el monto disponible de la linea.");
        }
        
        if(null != resultMap && !resultMap.isEmpty() && resultMap.size() > 0){
            LOGGER.warning("Recuperando Monto Disponible de la linea de servicio.");
            try{
                montoDisponibleDesembolsarLinea = (BigDecimal) resultMap.get("DISPONIBLE_DESEMBOLSO");
            }catch(Exception e){
                LOGGER.warning("ERROR al recuperar montoDisponibleDesembolsar de la linea.", e);
            }
            
            LOGGER.warning("Monto disponible a desembolsar de la linea: " + montoDisponibleDesembolsarLinea);
        }else{
            LOGGER.warning("resultMap no devuelve datos. No se puede recuperar el monto disponible de la linea.");
        }
        
        LOGGER.warning("Termina metodo obtenerMontoDisponibleDesembolsarLineaCredito.");
        return montoDisponibleDesembolsarLinea;
    }
    
    public BigDecimal obtenerMontoDisponibleLinea(){
        LOGGER.warning("Inicia metodo obtenerMontoDisponibleLinea.");
        BigDecimal montoDisponibleLinea = null;
        Long idLineaCredito = null;
        Map<String, Object> params = new HashMap<String, Object>();
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        Map resultMap = new HashMap();
        
        try{
            idLineaCredito = new Long(desembolsoBean.getIdLineaCredito());
        }catch(Exception e){
            LOGGER.warning("ERROR al recuperar IdLineaCredito. ", e);
        }
        LOGGER.warning("IdLineaCredito a consultar: " + idLineaCredito);
        
        if(null != idLineaCredito){
            params.put("idLineaCredito", idLineaCredito);
            params.put("tipoMoneda", null);
            try{
                resultMap = execute(params, "consultarLineaCreditoByIdLineaCredito");
            }catch(Exception e){
                LOGGER.warning("ERROR al ejecutar OperBinding consultarLineaCreditoByIdLineaCredito.", e);
            }
        }else{
            LOGGER.warning("IdLineaCredito es NULL. No se puede obtener el monto disponible de la linea.");
        }
        
        if(null != resultMap && !resultMap.isEmpty() && resultMap.size() > 0){
            LOGGER.warning("Recuperando Monto Disponible de la linea de servicio.");
            try{
                montoDisponibleLinea = (BigDecimal) resultMap.get("DISPONIBLE");
            }catch(Exception e){
                LOGGER.warning("ERROR al recuperar montoDisponibleDesembolsar de la linea.", e);
            }
            
            LOGGER.warning("Monto disponible a desembolsar de la linea: " + montoDisponibleLinea);
        }else{
            LOGGER.warning("resultMap no devuelve datos. No se puede recuperar el monto disponible de la linea.");
        }
        
        LOGGER.warning("Termina metodo obtenerMontoDisponibleLinea.");
        return montoDisponibleLinea;
    }
    
    
    /*public List<String> validarCondiciones(){
        LOGGER.warning("Inicia metodo validarCondiciones");
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        List<String> listaErrores = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        //Omite la validacion temporalmente
        Long idOperacion = null;
        try {
            idOperacion = new Long(desembolsoBean.getIdOperacion());
            params.put("idOperacion", idOperacion);
        } catch (Exception e) {
            LOGGER.warning("ERROR al obtener el idOperacion.", e);
        }
        
        Long idDesembolso=null;
        try {
            idDesembolso = new Long(desembolsoBean.getIdDesembolso());
            params.put("idDesembolso", idDesembolso);
        } catch (Exception e) {
            LOGGER.warning("ERROR al obtener el idDesembolso.", e);
        }   

            LOGGER.warning("Invocando metodo para campos de condiciones");
            try{
                listaErrores = execute(params, "validarCamposCondiciones");
                LOGGER.warning("Registros de la lista de errores: " + listaErrores.size());
            }catch(Exception e){
                LOGGER.warning("Error al ejecutar el operationBinding validarCondiciones.", e);
                listaErrores.add("Error al ejecutar la validacion de los campos en condiciones financieras");
            }
        
        LOGGER.warning("Termina metodo validarCondiciones");
        return listaErrores;
    }*/
    
    public Map validarCondiciones(){
            LOGGER.warning("Inicia metodo validarCondiciones");;
            DesembolsoBean desembolsoBean = getDesembolsoBean();
            Map<String, Object> params = new HashMap<String, Object>();
            Map<String, Object> result = new HashMap<String, Object>();
            
            Long pIdContratoDesembolso=null;
            try{
                pIdContratoDesembolso = new Long(desembolsoBean.getIdDesembolso());
                params.put("idContrato", pIdContratoDesembolso);
            }catch(Exception ep){
                    LOGGER.warning("Error al obtener idContratoDesembolso" , ep);
            }
            
            LOGGER.warning("Invocando metodo para campos de condiciones");
                
            try{
                result = execute(params, "buscarCondicionFinancieraPorIdContrato");
            }catch(Exception e){
                LOGGER.warning("Error al ejecutar el operationBinding validarCondiciones.", e);
                result.put("mjeError", "Error al ejecutar la validacion de los campos en condiciones financieras");
            }
            
            LOGGER.warning("Termina metodo validarCondiciones");
            return result;
    }
    
    /**
     * Valida si el usuario en session ha ingresado un comentario de operacion en la fecha actual
     * @param intTarea contiene id de tarea
     * @param showMsg indicador booleano, true para mostrar mensaje de error o false en caso contrario
     * @return devuelve valor booleano, true si ha agregado comentario o false en caso contrario
     */
    @SuppressWarnings("unchecked")
    public boolean validaComentarioOperacion(Integer intTarea, boolean showMsg) {
        LOGGER.entering(this.getClass().getName(), "validaComentarioOperacion", new Object[] { intTarea, showMsg });

        Boolean esComentario = false;
        Number idOperacion = null;
        String msg = null;
        try {
            idOperacion = new Number(this.getDesembolsoBean().getIdOperacion());
        } catch (Exception e) {
            LOGGER.severe("No se obtuvo Id de Operacion", e);
        }
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idOperacion", idOperacion);
        params.put("intTarea", intTarea);
        
        try {
            esComentario = super.execute(params, VALIDAR_COMENTARIO_OPERACION_FH_ACTUAL_OPER);
            LOGGER.warning("Result Oper Binding: " + esComentario);
            
            if (esComentario != null) {
                if (!esComentario && intTarea.equals(FenixConstants.CGD_REALIZAR_AJUSTES_DE_ASIGNACION_DE_RECURSOS)) {
                    LOGGER.warning("No existe comentario de operacion");
                    msg = getStringFromBundle(ES_NECESARIO_CAPTURAR_DATOS_OBLIGATORIOS);
                } else if (!esComentario) {
                    LOGGER.warning("No existe comentario de operacion");
                    msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_REQUERIDO);
                } else {
                    LOGGER.warning("Comentario de operacion si existe");
                }
            } else {
                LOGGER.severe("El resultado de la operacion no fue obtenido");
                msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_FALLO);
            }
        } catch (OperationExecuteException e) {
            LOGGER.severe("La operacion contiene errores. " + e.getMessage());
            msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_FALLO);
        }

        if (showMsg && msg != null) {
            LOGGER.warning("Muestra mensaje de error: " + msg);
            JSFUtils.addFacesErrorMessage(msg);
        }

        LOGGER.exiting(this.getClass().getName(), "validaComentarioOperacion", esComentario);
        return esComentario;
    }
    
    public boolean validaComentarioOperacionAjustes(Integer intTarea, boolean showMsg) {
        LOGGER.entering(this.getClass().getName(), "validaComentarioOperacionAjustes", new Object[] { intTarea, showMsg });

        Boolean esComentario = false;
        Number idOperacion = null;
        String msg = null;
        try {
            idOperacion = new Number(this.getDesembolsoBean().getIdOperacion());
        } catch (Exception e) {
            LOGGER.severe("No se obtuvo Id de Operacion", e);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idOperacion", idOperacion);
        params.put("intTarea", intTarea);

        try {
            esComentario = super.execute(params, VALIDAR_COMENTARIO_OPERACION_FH_ACTUAL_OPER);
            LOGGER.warning("Result Oper Binding: " + esComentario);

            if (esComentario != null) {

                if (!esComentario) {
                    LOGGER.warning("No existe comentario de operacion");
                    msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_REQUERIDO);
                } else {
                    LOGGER.warning("Comentario de operacion si existe");
                }
            } else {
                LOGGER.severe("El resultado de la operacion no fue obtenido");
                msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_FALLO);
            }
        } catch (OperationExecuteException e) {
            LOGGER.severe("La operacion contiene errores. " + e.getMessage());
            msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_FALLO);
        }

        if (showMsg && msg != null) {
            LOGGER.warning("Muestra mensaje de error: " + msg);
            JSFUtils.addFacesErrorMessage(msg);
        }

        LOGGER.exiting(this.getClass().getName(), "validaComentarioOperacionAjustes", esComentario);
        return esComentario;
    }

    /**
     * Obtiene cadena valor del Resource Bundle
     * @param psKey contiene clave de bundle
     * @return devuelve cadena
     */
    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt; 
    }

    public String finalizarTarea() {
        LOGGER.warning("*Inf,Inicia metodo finalizarTarea");
        
        //Se agrega instancia de DesembolsoBean para obtener el id delcontrato 
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        String idContratoDesembolso = null;
        
        if(null != desembolsoBean){
            if(null != desembolsoBean.getContrato()){
                idContratoDesembolso = desembolsoBean.getContrato();
            }else{
                LOGGER.warning("No se recupero el id del contrato de desembolso.");
            }
        }else{
            LOGGER.warning("La instancia del Bean es nula.");
        }
        if(bandera==0){
            popupConfFinalizarTarea.hide();
            LOGGER.warning("Cierra popupConfFinalizarTarea");
        }else if(bandera==1){
                popupConfFinalizarTareaDesembolsoExcepcion.hide();
                LOGGER.warning("Cierra popupConfFinalizarTareaDesembolsoExcepcion");
        }else if(bandera==2){
            popupCancelOperacionDesembolsoExcepcion.hide();
            LOGGER.warning("Cierra popupCancelOperacionDesembolsoExcepcion.");
        }

        LOGGER.warning("ENTRA 1");
        //invocar metodo para cargar documentos onBase
        cargarDocumentosOnBase();
        LOGGER.warning("ENTRA 2");

        int codigoTarea = this.getDesembolsoBean().getIdTarea() != null ? (this.getDesembolsoBean().getIdTarea().trim().length() > 0 ? Integer.parseInt(this.getDesembolsoBean().getIdTarea()) : 0) : 0;
        LOGGER.warning("Codigo de tarea--->"+codigoTarea);
        LOGGER.warning("CODIGO TAREA: " + codigoTarea);

        boolean exito = true;
        String msgError = null;

        
        switch (codigoTarea) {
        case FenixConstants.PC06_GESTIONAR_DESEMBOLSO_FUENTES_EXTERNAS:
            exito = guardarTransferenciaRecursos();
            if (!exito) {
                msgError = "No se pudo guardar la Transferencia de recursos.";
                LOGGER.severe(msgError);
                JSFUtils.addFacesErrorMessage(msgError);
            }
            break;
        case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_DAECI:
            
            if (!exito) {
                msgError = "Error al validar asignacion";
                LOGGER.severe(msgError);
            }
            break;
        
        case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_FINAM:
            
            if (!exito) {
                msgError = "Error al validar asignacion";
                LOGGER.severe(msgError);
            }
            break;
        
        case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PCT:
            
            if (!exito) {
                msgError = "Error al validar asignacion";
                LOGGER.severe(msgError);
            }
            break;
        
        case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PREA:
            
            if (!exito) {
                msgError = "Error al validar asignacion";
                LOGGER.severe(msgError);
            }
            break;
        
            case FenixConstants.CGD_REALIZAR_AJUSTES_DE_ASIGNACION_DE_RECURSOS:
            
            if (!exito) {
                msgError = "Error al realizar ajustes de asignacion de recursos";
                LOGGER.severe(msgError);
            }
            break;
        
        case FenixConstants.CGD_APROBAR_DESEMBOLSO:
            
            if(!exito){
                msgError = "Error al aprobar el desembolso";
                LOGGER.severe(msgError);
            } else {
                LOGGER.warning("se ejecuta el metodo de consultar cuentas BCIE que se actualiza en el payload ");
                Boolean cuentasBcie= consultaCuentas();
                LOGGER.warning("Es cuentas BCIE : "+cuentasBcie);
                if(null!=cuentasBcie){
                    actualizarPayloadElement("cuentaBCIE", cuentasBcie);
                    if(aprobarDesembolso()){
                        LOGGER.warning("Se cambio el estado del contrato correctamente.");
                    }else{
                        LOGGER.warning("No se pudo actualizar el Estado del contrato correctamente.");
                        JSFUtils.addFacesErrorMessage("No se pudo actualizar el Estado del contrato correctamente.");
                    }
                }else{
                    LOGGER.warning("variable cuentasBcie es null, no se actualizara el payload");
                    msgError = getStringFromBundle("ERROR_CUENTABCIE_PAYLOAD");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_CUENTABCIE_PAYLOAD"));
                }
                
                //JURBINA@28/04/2020  Se agrega un nuevo parameter type que indica si al finalizar la tarea se debera aprobar fuera de horario
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operBinding = bindings.getOperationBinding("CreateInsert5");
                operBinding.execute(); 
                
                actualizarFlagPayload("parameterName", "APROBAR_FUERA_HORARIO");
                actualizarFlagPayload("parameterValue", desembolsoBean.getAprobarFueraHorario());
                
                operBinding = bindings.getOperationBinding("Commit");
                operBinding.execute(); 
                
            }
            break;
        
        case FenixConstants.CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION:
           LOGGER.warning("Confirma la salida de validar desembolso por excepcion");
            LOGGER.warning("resultado del mensaje " + exito);
            if(!exito){
                LOGGER.warning("se asigna mensaje de error en CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION");
                msgError = "Error al aprobar el desembolso";
                LOGGER.severe(msgError);
            }
            break;
        
        case FenixConstants.CGD_JUSTIFICAR_DESEMBOLSO_POR_EXCEPCION:
            LOGGER.warning("Ingresa al switch Justificar desembolso");
            if(!exito){
                msgError = "Error al justificar el desembolso";
                LOGGER.severe(msgError);
            }
            break;
        
        case FenixConstants.CGD_GESTIONAR_PROGRAMACION:
            
            try{
                exito = execute(null, "actualizarDatosContratoDesembolso");
            }catch(Exception e){
                LOGGER.warning("ERROR al actualizar el desembolso.", e);
                exito = Boolean.FALSE;
            }
            
            if(!exito){
                msgError = "Error al Gestioanr programacion";
                LOGGER.severe(msgError);
            }
            break;
            case FenixConstants.CGD_RESERVAR_FONDOS:
                LOGGER.warning("Ingresa al switch reserva de fondos");
                actualizarPayloadElement("estadoDesembolso", "Fondos reservados");
                actualizarEstadoContrato(idContratoDesembolso, FenixConstants.ESTADO_SOLICITUD_FONDOS_RESERVADOS);
                if(!exito){
                    msgError = "Error en reserva de fondos";
                    LOGGER.severe(msgError);
                }
             break;
            case FenixConstants.CGD_REALIZAR_AJUSTES_A_DESEMBOLSO:
                actualizarCondicionesFinancieras();
                LOGGER.warning("*inf, Ingresa al case realizar ajustes a desembolsos");
             break;
            case FenixConstants.CGD_VALIDAR_INFORMACION_FINANCIERA_DE_DESEMBOLSO:
                LOGGER.warning("Ingresa al case validar informacion financiera de desembolso");
                    try{
                       exito = super.execute(null, COMMIT_DATOS_CONTRATO);
                    }catch(Exception e){
                        msgError=e.getMessage();
                        LOGGER.warning("**Error, Fallo el commit de los datos del contrato");
                    }
                if(!exito){
                    if(null!=msgError){
                            msgError = msgError.concat(". Error al guardar los cambios del contrato");
                        }
                    else{
                            msgError = "Error al guardar los cambios del contrato";
                        }
                    if(null!=msgError){
                            JSFUtils.addFacesErrorMessage(msgError);    
                        }
                    LOGGER.severe(msgError);
                }
             break;
            case FenixConstants.CGD_LIQUIDAR_CONTRATO_DE_DESEMBOLSO:
                if (!exito) {
                    msgError = "Error al liquidar contrato de desembolso";
                    LOGGER.severe(msgError);
                }
            break;
            case FenixConstants.CGD_VALIDAR_FONDOS_PRESUPUESTARIOS:
                LOGGER.warning("Ingresa al case validar fondos presupuestarios");
                if (!exito) {
                    msgError = "Error al validar fondo presupuestario";
                    LOGGER.severe(msgError);
                }
                else{
                    actualizarCuentaConPresupuesto();
                }
            break;
            case FenixConstants.PC06_APROBAR_FUERA_HORARIO:
                LOGGER.warning("Ingresa al case aprobar fuera de horario");
            break;
        default:
            LOGGER.log(ADFLogger.ERROR, "finalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        if (null != msgError) {
            return null;
        } else {
            return invokeActionBean.invokeOperation();
        }
    }

    private DesembolsoBean getDesembolsoBean() {
        return (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
    }

    public void finalizarTareaPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupConfFinalizarTarea.show(hints);
        bandera=0;
    }
    
    public void finalizarTareaPopupExcepcion() {
        LOGGER.warning("Abre finalizarTareaPopupExcepcion");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupConfFinalizarTareaDesembolsoExcepcion.show(hints);
        bandera=1;
    }
    
    public void cancelarOperacionPopup(){
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCancelOperacionDesembolsoExcepcion.show(hints);
        bandera=2;
    }
    
    public String cancelarFinalizarTarea() {
        popupConfFinalizarTarea.hide();
        return null;
    }
    
    public String cancelarfinalizarTareaExcepcion() {
        popupConfFinalizarTareaDesembolsoExcepcion.hide();
        return null;
    }

    public void setPopupConfFinalizarTarea(RichPopup popupConfFinalizarTarea) {
        this.popupConfFinalizarTarea = popupConfFinalizarTarea;
    }

    public RichPopup getPopupConfFinalizarTarea() {
        return popupConfFinalizarTarea;
    }

    public void setPopupAgregarEvidenciasDesembolsoExcepcion(RichPopup popupAgregarEvidenciasDesembolsoExcepcion) {
        this.popupAgregarEvidenciasDesembolsoExcepcion = popupAgregarEvidenciasDesembolsoExcepcion;
    }

    public RichPopup getPopupAgregarEvidenciasDesembolsoExcepcion() {
        return popupAgregarEvidenciasDesembolsoExcepcion;
    }
    
    public void setPopupEliminarEvidencia(RichPopup popupEliminarEvidencia) {
        this.popupEliminarEvidencia = popupEliminarEvidencia;
    }

    public RichPopup getPopupEliminarEvidencia() {
        return popupEliminarEvidencia;
    }

    private Integer getCodigoTarea()
    {
        LOGGER.warning("Entra a getCodigoTarea()");
        
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
    
        if (null != desembolsoBean.getIdTarea() && desembolsoBean.getIdTarea().trim().length() > 0)
        {
            return Integer.parseInt(desembolsoBean.getIdTarea());
        }
    
        return 0;
    }
    
    public String invocarMasInformacion() { 
        
        LOGGER.warning("Inicia proceso de Invocar Mas Informacion");
        
        List<String> budleKeyErroList = new ArrayList<String>();
      
        Boolean isValidSolicitarMasInformacion = Boolean.FALSE;
        
        Integer codigoTarea = getCodigoTarea();
        Long idOpearcion = getIdOperacion();
        boolean esComentario = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_DAECI:
                LOGGER.warning("Aplica validacion para tarea PC06_VALIDAR_ASIGNACION_RECURSOS");
                esComentario =
                    validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }else{
                    budleKeyErroList.add("MSG_VALIDACION_COMENTARIO");;
                }
            break;
        
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_FINAM:
                LOGGER.warning("Aplica validacion para tarea PC06_VALIDAR_ASIGNACION_RECURSOS");
                esComentario =
                    validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }else{
                    budleKeyErroList.add("MSG_VALIDACION_COMENTARIO");
                }
            break;
        
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PCT:
                LOGGER.warning("Aplica validacion para tarea PC06_VALIDAR_ASIGNACION_RECURSOS");
                esComentario =
                    validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }else{
                    budleKeyErroList.add("MSG_VALIDACION_COMENTARIO");
                }
            break;
        
            case FenixConstants.CGD_VALIDAR_ASIGNACION_DE_RECURSOS_PREA:
                LOGGER.warning("Aplica validacion para tarea PC06_VALIDAR_ASIGNACION_RECURSOS");
                esComentario =
                    validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }else{
                    budleKeyErroList.add("MSG_VALIDACION_COMENTARIO");
                }
            break;
        
            case FenixConstants.CGD_APROBAR_DESEMBOLSO:            
                LOGGER.warning("Aplica validacion para tarea PC06_APROBAR_DESEMBOLSOS");
                esComentario =
                    validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());
                
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }else{
                    budleKeyErroList.add(MSG_ERROR_COMENTARIO_OPER_REQUERIDO);
                }
            break;
        
            case FenixConstants.CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION:            
                LOGGER.warning("Aplica validacion para tarea CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION");
                esComentario =
                    validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());
                LOGGER.warning("Validacion CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION de comentario: " + esComentario);
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                    LOGGER.warning("Pasa la validacion del mensaje CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION");
                }else{
                    LOGGER.warning("Se asigna el error en el mensaje CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION");
                    budleKeyErroList.add(MSG_ERROR_COMENTARIO_OPER_REQUERIDO);
                }
            break;
        
            case FenixConstants.CGD_GESTIONAR_PROGRAMACION:
                LOGGER.warning("Aplica validacion para tarea CGD_VALIDAR_DESEMBOLSO_POR_EXCEPCION");
                esComentario =
                    validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());
                
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }else{
                    budleKeyErroList.add(MSG_ERROR_COMENTARIO_OPER_REQUERIDO);
                }
            break;
            case FenixConstants.CGD_RESERVAR_FONDOS:
               LOGGER.warning("Aplica validacion para tarea CGD_RESERVAR_FONDOS");
                esComentario =
                    validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());                          
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }else{
                    budleKeyErroList.add(MSG_ERROR_COMENTARIO_OPER_REQUERIDO);
                }                 
             break;
            case FenixConstants.CGD_VALIDAR_INFORMACION_FINANCIERA_DE_DESEMBOLSO:
               LOGGER.warning("Aplica validacion para tarea CGD_VALIDAR_INFORMACION_FINANCIERA_DE_DESEMBOLSO en Realizar ajustes");
                esComentario =
                    validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());                          
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                    pollRealizarAjustes.setInterval(60000);
                    pollRealizarAjustes.setInterval(60001);
                    pollRealizarAjustes.setRendered(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(pollRealizarAjustes);
                }else{
                    budleKeyErroList.add(MSG_ERROR_COMENTARIO_OPER_REQUERIDO);
                }                 
             break;
            case FenixConstants.CGD_VALIDAR_FONDOS_PRESUPUESTARIOS:
               LOGGER.warning("Aplica validacion para tarea CGD_VALIDAR_FONDOS_PRESUPUESTARIOS");
                esComentario = validateComentario(idOpearcion,codigoTarea.toString() , getInstanciaTarea());                          
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }else{
                    budleKeyErroList.add("VALIDAR_FONDOS_PRESUPUESTARIOS_MSG01");
                }                 
             break;
            case FenixConstants.CGD_GESTIONAR_DESEMBOLSO_DE_FUENTES_EXTERNAS:
                if (this.validaComentarioOperacion(FenixConstants.PC06_GESTIONAR_DESEMBOLSO_FUENTES_EXTERNAS, true)) {
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }
             break;
            case FenixConstants.PC06_APROBAR_FUERA_HORARIO:
                    isValidSolicitarMasInformacion = Boolean.TRUE;
             break;              
            default:
                LOGGER.log(ADFLogger.ERROR, "invocarMasInformacion(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        
        if (!isValidSolicitarMasInformacion)
        {
            if(budleKeyErroList.size()>0)
            {
              for(String bundleKey : budleKeyErroList)
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else
        {
            solicitarMasInfoPopup();
        }
    
        return null;
    }
    
    public void solicitarMasInfoPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupConfMasInfo.show(hints);
    }
    
    public void solicitarCancelarOutcomePopup(){
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupDesestimarDesembolso.show(hints);
    }


    /**
    * Aceptar Mas Informacion
    * @return
    */
    public String solicitarMasInformacion() {
        LOGGER.warning("Entra a solicitarMasInformacion()");
        popupConfMasInfo.hide();
        //invocar metodo para cargar documentos onBase
        
        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    public String solicitarMasInformacionGestionarProgramacion() {
        LOGGER.warning("Entra a solicitarMasInformacionGestionarProgramacion()");
        Map<String, Object> params = new HashMap<String, Object>();
        Boolean resultado = Boolean.FALSE;

        popupConfMasInfo.hide();
        //invocar metodo para cargar documentos onBase
        params.put("idEstadoActualizar", FenixConstants.ESTADO_CONTRATO_DESEMBOLSO_TRANSITO);
        try {
            resultado = execute(params, "actualizarEstadoDesembolso");
        } catch (Exception e) {
            LOGGER.warning("ERROR al ejecutar la actualizacion de estado del contrato.", e);
        }
        
        if(!resultado){
            LOGGER.warning("No fué posible actualizar el estado del contrato de desembolso a En Transito.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_CAMBIO_ESTADO_DESEMBOLSO"));
            return null;
        }

        cargarDocumentosOnBase();
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }

    /**
     *Cancelar Mas Informacion
     * @return
     */
      public String cancelarSolicitarMasInformacion()
      {
          popupConfMasInfo.hide();
          return null;
      }
    public void setPopupConfMasInfo(RichPopup popupConfMasInfo)
    {
      this.popupConfMasInfo = popupConfMasInfo;
    }
    
    public RichPopup getPopupConfMasInfo()
    {
      return popupConfMasInfo;
    }

    public String solicitarCancelarOutcome() {
        LOGGER.warning("Entra a solicitarMasInformacion()");
        Map<String, Object> params = new HashMap<String, Object>();
        Boolean resultado = Boolean.FALSE;
        String retorno = null;
        
        popupDesestimarDesembolso.hide();
        //invocar metodo para cargar documentos onBase
               
        cargarDocumentosOnBase();
        
        params.put("idEstadoActualizar", FenixConstants.ESTADO_CONTRATO_DESEMBOLSO_DESESTIMADO);
        try{
            resultado = execute(params, "actualizarEstadoDesembolso");
        }catch(Exception e){
            LOGGER.warning("ERROR al ejecutar la actualizacion de estado del contrato.", e);
        }
        
        if(resultado){
            //actualizarPayloadElement("desestimado", Boolean.TRUE);
            
            /* Atender incidencia FNXII-5143 */
            AttributeBinding attributeBindingDesestimado = null;
            attributeBindingDesestimado = ADFUtils.findControlBinding("desestimado");
            actualizarPayloadElement("desestimado", Boolean.TRUE);
            LOGGER.severe("Revisar Valor del payload en true Despues de insertar en el campo desestimado." + attributeBindingDesestimado.getInputValue());
            /* Fin incidencia FNXII-5143     */
            
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            retorno = invokeActionBean.invokeOperation();
        }else{
            LOGGER.warning("ERROR, no se pudo actualizar el estado del desembolso.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
            retorno = null;
        }
        
        return retorno;
    }
    
    public Boolean aprobarDesembolso() {
        LOGGER.warning("Inside aprobarDesembolso()");
        Map<String, Object> params = new HashMap<String, Object>();
        Boolean resultado = Boolean.FALSE;
        Boolean retorno = Boolean.FALSE;
        
        params.put("idEstadoActualizar", FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_APROBADO);
        try{
            resultado = execute(params, "actualizarEstadoDesembolso");
        }catch(Exception e){
            LOGGER.warning("ERROR al ejecutar la actualizacion de estado del contrato.", e);
        }
        
        if(resultado){
            retorno = Boolean.TRUE;
        }else{
            LOGGER.warning("ERROR, no se pudo actualizar el estado del desembolso.");
        }
        
        LOGGER.warning("Desembolso Aprobado: " + retorno);
        return retorno;
        
    }

    public String confirmarCancelarOutcome() {
        popupDesestimarDesembolso.hide();
        return null;
    }

    public String invocarCancelarOutcome() {
        LOGGER.warning("Inicia proceso de Invocar Cancelar");
        
        List<String> budleKeyErroList = new ArrayList<String>();
        
        Boolean isValidCancelarOutcome = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();
        boolean esComentario = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.CGD_APROBAR_DESEMBOLSO:            
                LOGGER.warning("Aplica validacion para tarea PC06_APROBAR_DESEMBOLSOS");
                esComentario = validaComentarioOperacion(FenixConstants.CGD_APROBAR_DESEMBOLSO, true);
                
                if(esComentario){
                    isValidCancelarOutcome = Boolean.TRUE;
                }
            break;
            case FenixConstants.CGD_REALIZAR_AJUSTES_A_DESEMBOLSO:            
                LOGGER.warning("Aplica validacion para tarea PC06_REALIZAR_AJUSTES_A_DESEMBOLSO");
                esComentario = validaComentarioOperacion(FenixConstants.CGD_REALIZAR_AJUSTES_A_DESEMBOLSO, true);
                
                if(esComentario){
                    isValidCancelarOutcome = Boolean.TRUE;
                }
            break;
            default:
                LOGGER.log(ADFLogger.ERROR, "invocarMasInformacion(): Codigo de tarea no valido - " + codigoTarea);
            break;
        }
        
        if (!isValidCancelarOutcome)
        {
            if(budleKeyErroList.size()>0)
            {
              for(String bundleKey : budleKeyErroList)
                JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else
        {
            solicitarCancelarOutcomePopup();
        }
        
        return null;
    }

    public void setPopupDesestimarDesembolso(RichPopup popupDesestimarDesembolso) {
        this.popupDesestimarDesembolso = popupDesestimarDesembolso;
    }

    public RichPopup getPopupDesestimarDesembolso() {
        return popupDesestimarDesembolso;
    }
    
    private Long getIdOperacion() {
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");

        if (null != desembolsoBean.getIdOperacion() && desembolsoBean.getIdOperacion().trim().length() > 0) {
            return Long.parseLong(desembolsoBean.getIdOperacion());
        }

        return 0L;
    }
    
    private oracle.jbo.domain.Number getIdFlujo() {
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        oracle.jbo.domain.Number  resultado = null;
        if (null != desembolsoBean.getIdFlujo()) {
            resultado = desembolsoBean.getIdFlujo();
        }

        return resultado;
    }
    
    public String invokeFinalizarTarea(){
            LOGGER.entering(this.getClass().getName(), "invocarFinalizarTarea");
            LOGGER.warning("Inicia el proceso de Invocar Finalizar Tarea");
            
            List<String> bundleKeyErrorList = new ArrayList<String>();
            Boolean isValidFinalizarTarea = Boolean.TRUE;
            Boolean condicion1 = Boolean.FALSE;
            Boolean condicion2 = Boolean.TRUE;
            Boolean condicion3 = Boolean.TRUE;
            Integer codigoTarea = getCodigoTarea();
            LOGGER.warning("Codigo de Tarea: " + codigoTarea);
            LOGGER.warning("Operacion: "+ getIdOperacion());
            LOGGER.warning("Tarea: "+ getCodigoTarea().toString());
            LOGGER.warning("Instancia: "+getInstanciaTarea() );
            DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
            switch (codigoTarea)
            {
            case FenixConstants.CGD_VALIDAR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS:
            /*
            isValidFinalizarTarea=validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                if (!isValidFinalizarTarea) {
                         bundleKeyErrorList.add("MSG_ERROR_COMENTARIO");    
                     }
              */		  

					//String cuentaCliente = (String) JSFUtils.resolveExpression("#{bindings.CuentaCliente.inputValue}");
                                        //Jenamorado 03/08/2021
                                     String   cuentaCliente =  ValidarCuentaClienteNoNull();
					if (cuentaCliente == null /*|| cuentaCliente.isEmpty()*/) 
					{
						isValidFinalizarTarea = false;
						bundleKeyErrorList.add("MSG_ERROR_CUENTA_CLIENTE");    
					}

                break;
            case FenixConstants.CGD_REGISTRAR_DESEMBOLSO:
                LOGGER.warning("idOperacion : "+getIdOperacion());
                LOGGER.warning("idTipoDocumento : "+FenixConstants.TIPO_DOCUMENTO_SOPORTE_SWIFT_DESEMBOLSO); 
                LOGGER.warning("idFlujo : "+getIdFlujo());
             condicion1=validarDocumentoPorNumeroSerieDocumento(getIdOperacion(),
                                    FenixConstants.TIPO_DOCUMENTO_SOPORTE_SWIFT_DESEMBOLSO,
                                        getIdFlujo());
                    if (!condicion1) {
                        LOGGER.warning("***Error, no se cumplio la condicion1 ");
                             bundleKeyErrorList.add("MSG_ERROR_DOCUMENTO_SWIFT");    
                         }
            condicion2=validarBHQ(desembolsoBean.getContrato());   //Boolean.TRUE; //Valdiacion de transferencias consolidadas
            if (!condicion2) {
                     LOGGER.warning("***Error, no se cumplio la condicion2 ");
                     bundleKeyErrorList.add("MSG_ERROR_BHQ_CONTRATO");    
                 }
                
            condicion3=validarConsolidadas(); //Boolean.TRUE; //Valdiacion de BHQ contrato 
            if (!condicion3) {
                     LOGGER.warning("***Error, no se cumplio la condicion3 ");
                     bundleKeyErrorList.add("MSG_ERROR_TRANSFERENCIAS");    
                 }
                isValidFinalizarTarea=condicion1&&condicion2&&condicion3;
                        break;
            }
            if (!isValidFinalizarTarea) {
                if (bundleKeyErrorList.size() > 0) {
                    for (String bundleKey : bundleKeyErrorList)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
            }
            else{
                    finalizarTareaPopup();
                }
        return null;
    }
    
    public String invokeMasInformacion() {
            List<String> bundleKeyErrorList = new ArrayList<String>();
            Boolean isValidMasInformacion = Boolean.TRUE;

            int codigoTarea = getCodigoTarea();
            LOGGER.warning("Operacion: "+ getIdOperacion());
            LOGGER.warning("Tarea: "+ getCodigoTarea().toString());
            LOGGER.warning("Instancia: "+getInstanciaTarea() );
            
            switch (codigoTarea) {
                case FenixConstants.CGD_VALIDAR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS:
               
                isValidMasInformacion=validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                    if (!isValidMasInformacion) {
                             bundleKeyErrorList.add(MSG_ERROR_COMENTARIO);    
                         }
                 
                    break;
                case FenixConstants.CGD_REGISTRAR_DESEMBOLSO:
                    final Integer DESEMBOLSO_REGISTRADO = 1;
                    
                    if(esDesembolsoRegistradoFlex() == null ){
                        JSFUtils.addFacesErrorMessage("Error no fue posible evaluar el registro del contrato de desembolso");
                    }else if(esDesembolsoRegistradoFlex().compareTo(DESEMBOLSO_REGISTRADO) == 0) {                       
                        isValidMasInformacion = Boolean.FALSE;
                        JSFUtils.addFacesInformationMessage("No se pueden requerir ajustes de un desembolso ya registrado");                     
                    }else{
                        
                        isValidMasInformacion=validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                        if (!isValidMasInformacion) {
                                 bundleKeyErrorList.add(MSG_ERROR_COMENTARIO);    
                        }
                        if(!aplicarCompensacionDesembolso()){                      
                              bundleKeyErrorList.add(MSG_ERROR_APLICAR_COMPENSACION_DESEMBOLSO);    
                              isValidMasInformacion = Boolean.FALSE;                  
                        } 
                    }   
                      
                break;
            }

            if (!isValidMasInformacion) {
                    if (bundleKeyErrorList.size() > 0) {
                        for (String bundleKey : bundleKeyErrorList)
                            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                    }
            }else{
                solicitarMasInfoPopup();
            }
            
        return null;
    }
    
    
    public Integer esDesembolsoRegistradoFlex(){
        LOGGER.warning("inicia metodo esDesembolsoRegistradoFlex");
        Integer contratoRegistrado = null;
        final Integer DESEMBOLSO_REGISTRADO = 1;
        final Integer DESEMBOLSO_NO_REGISTRADO = 0;
        Row desembolsoCurrent;
        
                
            try{
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operBinding = bindings.getOperationBinding("getContratoSeleccionado");
                operBinding.execute();               
            
              if(!operBinding.getErrors().isEmpty()){
                LOGGER.warning("OperationBinding getContratoSeleccionado devuelve errores");
              }else{
                 
                  desembolsoCurrent = (Row)operBinding.getResult();
                  
                  if(desembolsoCurrent != null){
                                                
                        if(desembolsoCurrent.getAttribute("ContratoFlexcube") != null 
                           && !desembolsoCurrent.getAttribute("ContratoFlexcube").toString().equals("")){
                                contratoRegistrado = DESEMBOLSO_REGISTRADO;
                                LOGGER.warning("El contrato de desembolso ya ha sido registrado");
                        }else{
                            contratoRegistrado = DESEMBOLSO_NO_REGISTRADO;
                            LOGGER.warning("El contrato de desembolso no ha sido registrado");
                        }                    
                  }else{
                      LOGGER.warning("Importante! el row  de contrato de desembolso recuperado es resuleto a null");                     
                  }
                  
              }            
                
            }catch(Exception e){
               LOGGER.warning("Error al ejecutar el operationBinding getContratoSeleccionado -> ", e);
            } 
       
        LOGGER.warning("termina metodo esDesembolsoRegistradoFlex");
        return contratoRegistrado;
    }
    
    
    
    public String aceptarFinalizarTarea() {
        LOGGER.warning("Inside aceptarFinalizarTarea desembolso");
        
        //Se agrega instancia de DesembolsoBean para obtener el id delcontrato 
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        String idContratoDesembolso = null;
         
        if(null != desembolsoBean){
            if(null != desembolsoBean.getContrato()){
                idContratoDesembolso = desembolsoBean.getContrato();
				LOGGER.warning("idContratoDesembolso: " + idContratoDesembolso);
            }else{
                LOGGER.warning("No se recupero el id del contrato de desembolso.");
            }
        }else{
            LOGGER.warning("La instancia del Bean es nula.");
        }

        popupConfFinalizarTarea.hide();
        
            Boolean isValidFinalizarTarea = Boolean.TRUE;
            List<String> bundleKeyErrorList = new ArrayList<String>();
            int codigoTarea = getCodigoTarea();
            Integer condicion1=null;
            switch (codigoTarea) {
                case FenixConstants.CGD_VALIDAR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS:
                Integer opcion=2;
                condicion1= actualizarDatosGenerales(opcion);
				
				LOGGER.warning("condicion1: " + condicion1);
                if(null!=condicion1){
                    if(condicion1.compareTo(1)==0){
							LOGGER.warning("Entro en 1");
                            bundleKeyErrorList.add("MSG_ERROR_VALIDACION_DATOS"); 
                            isValidFinalizarTarea = Boolean.FALSE;
                        }
                    if(condicion1.compareTo(2)==0){
							LOGGER.warning("Entro en 2");
                            bundleKeyErrorList.add("MSG_ERROR_VALIDACION_DATOS");  
                            isValidFinalizarTarea = Boolean.FALSE;
                        }
                    }
                else{
					LOGGER.warning("Entro en condicion1 nulo");
                        bundleKeyErrorList.add("MSG_ERROR_ACTUALIZAR_DATOS");  
                        isValidFinalizarTarea = Boolean.FALSE;
                    }
                    break;
                case FenixConstants.CGD_REGISTRAR_DESEMBOLSO:
                
                    Boolean isEstadoActualizado = 
                        actualizarEstadoContrato(idContratoDesembolso, FenixConstants.ESTADO_SOLICITUD_DESEMBOLSADO);
                    if(isEstadoActualizado != null &&
                       isEstadoActualizado){
                        LOGGER.warning("Estado del contrato de desembolso actualizado a Desembolsado: Id Estado: " + 
                                       FenixConstants.ESTADO_SOLICITUD_DESEMBOLSADO);
                        isValidFinalizarTarea = true;
                    }else{
                        LOGGER.severe("Estado del contrato NO actualizado");
                        isValidFinalizarTarea = false;
                    }
                break;
            }
            if (isValidFinalizarTarea) {
                cargarDocumentosOnBase();
                InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                return invokeActionBean.invokeOperation();
            } else {
                LOGGER.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): " + isValidFinalizarTarea);
                if (bundleKeyErrorList.size() > 0) {
                    for (String bundleKey : bundleKeyErrorList)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
                LOGGER.warning("Se cancela la confirmacion de Finalizar tarea desembolso");
                return null;
            }
        }
    
    public Boolean guardarDatosContrato(){
        LOGGER.warning("Inicia metodo guardarDatosContrato.");
        Boolean resultado = Boolean.FALSE;
        
        try{
            resultado = execute(null, "actualizarDatosContratoDesembolso");
        }catch(Exception e){
            LOGGER.warning("ERROR al ejecutar el operBinding actualizarDatosContratoDesembolso.", e);
        }
        
        if(resultado){
            if(validarGuardarDatosGrales()){
                if(actualizarCargos()){
                    LOGGER.warning("El guardado se realizo correctamente.");
                }else{
                    LOGGER.warning("Error al guardar los CARGOS del contrato de desembolso.");
                    JSFUtils.addFacesErrorMessage("No se pudieron guardar los Cargos correctamente.");
                    resultado = Boolean.FALSE;
                }
            }else{
                LOGGER.warning("Guardado y validado de Datos Generales es FALSE.");
                resultado = Boolean.FALSE;
            }
        }else{
            JSFUtils.addFacesErrorMessage("Error al guardar los datos del contrato de desembolso.");
        }
        
        LOGGER.warning("Termina metodo guardarDatosContrato.");
        return resultado;
    }
    
    public Boolean validarGuardarDatosGrales(){
        LOGGER.warning("Inicia metodo validarGuardarDatosGrales.");
        Boolean isValidFinalizarTarea = Boolean.TRUE;
        Integer condicion1=null;
        Integer opcion=2;
        condicion1= actualizarDatosGenerales(opcion);
        
        if(null!=condicion1){
            if(condicion1.compareTo(1)==0){ 
                LOGGER.warning("ERROR al actualizar los Datos Generales.");
                JSFUtils.addFacesErrorMessage("No se pudieron guardar los Datos Generales correctamente.");
                isValidFinalizarTarea = Boolean.FALSE;
            }
            if(condicion1.compareTo(2)==0){  
                LOGGER.warning("ERROR al validar los datos obligatorios de Datos Generales.");
                JSFUtils.addFacesErrorMessage("Los Datos Generales cuentan con campos obligatorios sin capturar.");
                isValidFinalizarTarea = Boolean.FALSE;
            }
        }else{
            LOGGER.warning("ERROR al actualizar los Datos Generales.");
            JSFUtils.addFacesErrorMessage("Datos Generales no se pudieron validar y guardar correctamente.");
            isValidFinalizarTarea = Boolean.FALSE;
        }
        
        LOGGER.warning("Termina metodo validarGuardarDatosGrales.");
        return isValidFinalizarTarea;
    }
    
    public Boolean actualizarCargos(){
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        Boolean retorna=Boolean.FALSE;
        String idContrato = desembolsoBean.getIdDesembolso();
        Long idContratoLong = null;
        
        try{
            idContratoLong = Long.valueOf(idContrato);
            LOGGER.warning("IdContrato: " + idContratoLong);
        }catch(Exception e){
            LOGGER.warning("ERROR al obtener el idContrato.", e);
        }
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("aplicarGuardado");
        operationBinding.getParamsMap().put("idContrato", idContratoLong);

        retorna=(Boolean) operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("obtuvo problemas al actualizar");
        } 
        LOGGER.warning("actualizo datos de cargos? "+retorna);
        return retorna;
    }
    
    /**
     * Este metodo ejecuta un servicio desde metodo aplicarCompensacionDesembolsoService 
     * en contratoDesembolsoVO para aplicar la compensacion al desembolso
     * @since 16/11/2016   
     * @return Boolean respuesta
     * @Carlos Orozco
     */
    
    public Boolean  aplicarCompensacionDesembolso(){
         LOGGER.warning("inicia metodo AplicarCompensacionDesembolso");
           Boolean respuesta = Boolean.FALSE;  
            DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
            
            Long idContrato = (null == desembolsoBean.getContrato() || desembolsoBean.getContrato().equals("") )? null
                            :  Long.parseLong(desembolsoBean.getContrato());
           
         if(idContrato != null){           
                BindingContainer bindings = getBindings();
                OperationBinding operationBinding = bindings.getOperationBinding("aplicarCompensacionDesembolsoService");
                operationBinding.getParamsMap().put("idDesembolso", idContrato);
                  respuesta = (Boolean)operationBinding.execute();
         }else{
              LOGGER.warning("*** Error, no se esta recibiendo un idContrato");  
             }   
             LOGGER.warning("Termina metodo AplicarCompensacionDesembolso");  
            return respuesta;
        }
    
    public String aceptarMasInformacion(){
		LOGGER.warning("inside aceptarMasInformacion");
		DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
		popupConfMasInfo.hide();
	
		Boolean isValidMasInformacion = Boolean.TRUE;
		List<String> bundleKeyErrorList = new ArrayList<String>();
		int codigoTarea = getCodigoTarea();
		Integer condicion1=null;
	 


		switch (codigoTarea) {
			case FenixConstants.CGD_VALIDAR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS:    
			Integer opcion=1;
			condicion1= actualizarDatosGenerales(opcion);
			if(null!=condicion1){
				
				LOGGER.warning("condicion1: " + condicion1);
				if(condicion1.compareTo(1)==0){
					   // bundleKeyErrorList.add("MSG_ERROR_ACTUALIZAR_DATOS"); 
						LOGGER.warning("Error al actualizar los datos");
						isValidMasInformacion = Boolean.FALSE;
						LOGGER.warning("retorna 1");
					}
				if(condicion1.compareTo(2)==0){
					 //   bundleKeyErrorList.add("MSG_ERROR_VALIDACION_DATOS");  
						isValidMasInformacion = Boolean.FALSE;
						LOGGER.warning("retorna 2");
					}
				}
			else{
				   // bundleKeyErrorList.add("MSG_ERROR_ACTUALIZAR_DATOS");  
					isValidMasInformacion = Boolean.FALSE;
					LOGGER.warning("Error al actualizar los datos");
				}
			isValidMasInformacion = Boolean.TRUE;
				break;
			case FenixConstants.CGD_REGISTRAR_DESEMBOLSO:
														
				isValidMasInformacion=actualizarEstadoContrato(desembolsoBean.getContrato(), FenixConstants.ESTADO_SOLICITUD_APROBADO_POR_GERENTE);
				if(isValidMasInformacion){
					actualizarPayloadElement("estadoDesembolso", FenixConstants.ESTADO_SOLICITUD_APROBADO_POR_GERENTE);
					}
				else{
						bundleKeyErrorList.add("MSG_ERROR_ACTUALIZAR_ESTADO_CONTRATO"); 
					}
			break;
		}
		if (isValidMasInformacion) {
			cargarDocumentosOnBase();
			LOGGER.warning("Entra al invoke mas informacion");
			InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
			return invokeActionBean.invokeOperation();
		} else {
			LOGGER.log(ADFLogger.ERROR, "isValidMasInformacion(): " + isValidMasInformacion);
			if (bundleKeyErrorList.size() > 0) {
				for (String bundleKey : bundleKeyErrorList)
					JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
			}
			LOGGER.warning("Se cancela la confirmacion de Finalizar tarea");
			return null;
		}
    }
    
    public Integer actualizarDatosGenerales(Integer opcion) {
		LOGGER.warning("Inicio actualizarDatosGenerales");
        Integer retorna = 1;
        Map resultado = new HashMap<String, Object>();
        Boolean registro = Boolean.FALSE;
        Boolean validoCampos = Boolean.FALSE;
        Boolean actualizoDatos = Boolean.FALSE;
        Boolean actualizaProyecto = Boolean.FALSE;

        BindingContainer bindings = getBindings();
		
		LOGGER.warning("validarCampos");
		LOGGER.warning("aplicaProyecto: " + actualizaProyecto);
		LOGGER.warning("valida: " + opcion); 
		
        OperationBinding operationBinding = bindings.getOperationBinding("validarCampos");
        operationBinding.getParamsMap().put("aplicaProyecto", actualizaProyecto);
        operationBinding.getParamsMap().put("valida", opcion);

        operationBinding.execute();
		
		LOGGER.warning("Ejecutado");
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("obtuvo problemas al actualizar");
        } else {
			LOGGER.warning("Inicio resltado correcto");
            resultado = (HashMap<String, Object>) operationBinding.getResult();
            if (null != (Boolean) resultado.get("actualiza")) {
                actualizoDatos = (Boolean) resultado.get("actualiza");
				LOGGER.warning("actualizoDatos: " + actualizoDatos);
                if (actualizoDatos) {
                    validoCampos = (Boolean) resultado.get("valida");
                    if (validoCampos) {
                        retorna = 3;
                        LOGGER.warning("Se actualizo correctamente retorno 3");
                    } else {
                        retorna = 2;
						LOGGER.warning("retorna 2");
                    }
                }else{
                    LOGGER.warning("ERROR No se pudieron actualizar los Datos Generales del desembolso.");
                }
            }
			LOGGER.warning("Fin resltado correcto");
        }
		LOGGER.warning("Fin actualizarDatosGenerales");
        return retorna;
    }
    
    
    /***
     * Metodos a utilizar para la tarea de Justificar Desembolso**/
    
    public void abrirPopupBusquedaDesembolsoExcepcion(ActionEvent actionEvent) {
        // metodo limpia las row de la vo          
         limpiarTabla();
         
        DesembolsoBean desembolsoBean = (DesembolsoBean)JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        desembolsoBean.setAplicarScroll(Boolean.FALSE);
        LOGGER.warning("Agregamos el valor false a la bandera AplicarScroll");
        
        // Add event code here...
        cargarRow(); // se crea una fila en la VO programatica para utilizar en el formulario
        // metodo limpia las row de la vo          
         limpiarTabla();
         
        //Limpiar campos del formulario
        ADFUtils.setBoundAttributeValue("Documento", null);
        ADFUtils.setBoundAttributeValue("IdTipoDocumento", null);
        ADFUtils.setBoundAttributeValue("IdProcesoBpm", null);
        ADFUtils.setBoundAttributeValue("FechaIni", null);
        ADFUtils.setBoundAttributeValue("FechaFin", null);
        LOGGER.warning("Listo formulario de busqueda limpio...");
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupAgregarEvidenciasDesembolsoExcepcion());
        
        // Abrir popUp busqueda documentos trazabilidad operacion
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupAgregarEvidenciasDesembolsoExcepcion().show(popupHints);
    }
    
    public void buttonBuscarDocumentosTrazabilidadOperacion(ActionEvent actionEvent){
        LOGGER.warning("Boton Buscar presionado iniciando accion...");
        String msg=null;
        limpiarTabla();
        String Documento = null;
        Integer idTipoDocumento = null;
        Integer idProcesoBpm = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = null;
        Timestamp fechaIni = null;
        Timestamp fechaFin = null;
        
        LOGGER.warning("Recuperando datos para filtrar evidencias...");
        
        Documento = ( null != ADFUtils.getBoundAttributeValue("Documento"))? ADFUtils.getBoundAttributeValue("Documento").toString() : null;
        LOGGER.warning("Documento--->" + Documento);
        
        try{
            idTipoDocumento = ( null != ADFUtils.getBoundAttributeValue("IdTipoDocumento"))? new Integer(ADFUtils.getBoundAttributeValue("IdTipoDocumento").toString()) : null;
            LOGGER.warning("idTipoDocumento--->" + idTipoDocumento);
        }catch(Exception e){
            LOGGER.warning("idTipoDocumento--->null");
        }
        
        try{
            idProcesoBpm = ( null != ADFUtils.getBoundAttributeValue("IdProcesoBpm"))? new Integer(ADFUtils.getBoundAttributeValue("IdProcesoBpm").toString()) : null;
            LOGGER.warning("idProcesoBpm--->" + idProcesoBpm);
        }catch(Exception e){
            LOGGER.warning("idProcesoBpm--->null");
        }
        
        try{
            parsedDate = dateFormat.parse(ADFUtils.getBoundAttributeValue("FechaIni").toString());
            fechaIni = new java.sql.Timestamp(parsedDate.getTime());
            LOGGER.warning("fechaIni--->" + fechaIni);
        }catch(Exception e){//this generic but you can control another types of exception
            LOGGER.warning("fechaIni es null...");
        }
        
        try{
            parsedDate = dateFormat.parse(ADFUtils.getBoundAttributeValue("FechaFin").toString());
            fechaFin = new java.sql.Timestamp(parsedDate.getTime());
            LOGGER.warning("fechaFin--->" + fechaFin);
        }catch(Exception e){//this generic but you can control another types of exception
            LOGGER.warning("fechaFin es null...");
        }
        
        if(fechaIni==null){
                LOGGER.warning("Ingresando fecha inicial (fechaIni es null...)");
                try{
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    parsedDate = dateFormat.parse("2000-01-01 00:00:00.0");
                    fechaIni = new java.sql.Timestamp(parsedDate.getTime());
                    LOGGER.warning("Listo fechaIni--->"+fechaIni);
                }catch(Exception e){//this generic but you can control another types of exception
                    LOGGER.warning("No se pudo introducir la fecha inicial(fechaIni es null...)");
                }
            }
            
            if(fechaFin==null){
                LOGGER.warning("Ingresando fecha actual del sistema (fechaFin es null...)");
                try{
                    Date date = new Date();
                    String fechaAct = dateFormat.format(date);
                    parsedDate = dateFormat.parse(fechaAct);
                    fechaFin = new java.sql.Timestamp(parsedDate.getTime());
                    LOGGER.warning("Listo fechaFin--->"+fechaFin);
                }catch(Exception e){//this generic but you can control another types of exception
                    LOGGER.warning("No se pudo introducir la fecha actual del sistema (fechaFin es null...)");
                }
            }
        
            try{
                LOGGER.warning("Inicia verificacion(fecha inicial no sea mayor que fecha fin)");
                if(fechaFin.getTime() < fechaIni.getTime()){
                    LOGGER.warning("Ingresa al if)");
                    msg = getStringFromBundle(MSG_ERROR_FECHA_INICIAL_MAYOR);
                    LOGGER.warning("Se muestra mensaje de error: )" + msg);
                }
            }catch(Exception e){
                LOGGER.warning("Error en la verificacion...", e);
            }
        
        if (msg != null) {
            LOGGER.warning("Muestra mensaje de error: " + msg);
            JSFUtils.addFacesErrorMessage(msg);
        }else{
        
            LOGGER.warning("Ejecutando el View Criteria a DocumentosTrazabilidadOperacionVO...)");
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("buscarDocumentosTrazabilidadOperacion");
            operBinding.getParamsMap().put("Documento", Documento);
            operBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
            operBinding.getParamsMap().put("idProcesoBpm", idProcesoBpm);
            operBinding.getParamsMap().put("fechaIni", fechaIni);
            operBinding.getParamsMap().put("fechaFin", fechaFin);
        
            operBinding.execute();
        
            if(!operBinding.getErrors().isEmpty()){
                LOGGER.warning("OperationBinding devuelve errores");
            }
        
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupAgregarEvidenciasDesembolsoExcepcion());
        
            LOGGER.warning("*********** termina metodo busquedaOperaciones");
        }
    }
    
    public void buttonRestablecerDocumentosTrazabilidadOperacion(ActionEvent actionEvent){
        LOGGER.warning("Se a oprimido el boton de Restablecer, iniciando accion...");
        // metodo limpia las row de la vo          
        LOGGER.warning("Llamada al metodo limpiarTabla()...");
         limpiarTabla();
        
        LOGGER.warning("Limpiando formulario de busqueda...");
        //Limpiar campos del formulario
        ADFUtils.setBoundAttributeValue("Documento", null);
        ADFUtils.setBoundAttributeValue("IdTipoDocumento", null);
        ADFUtils.setBoundAttributeValue("IdProcesoBpm", null);
        ADFUtils.setBoundAttributeValue("FechaIni", null);
        ADFUtils.setBoundAttributeValue("FechaFin", null);
        LOGGER.warning("Listo formulario de busqueda limpio...");
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupAgregarEvidenciasDesembolsoExcepcion());
    }
    
    public void limpiarTabla(){
      // Accedemos y ejecutamlos el metodo limpiarFilas() de DocumentosTrazabilidadOperacionVO
      LOGGER.warning("Accedemos y ejecutamlos el metodo limpiarFilas() de DocumentosTrazabilidadOperacionVO...");
            try{
                LOGGER.warning("Acceso permitido...");
                BindingContainer binding = getBindings();
                OperationBinding operBinding = binding.getOperationBinding("limpiarFilas");
                operBinding.execute();
                
                if(!operBinding.getErrors().isEmpty()){
                    LOGGER.warning(" :( OperationBinding devuelve errores");
                }
                
            }catch(Exception e){
                    LOGGER.warning("Acceso negado...");
                    LOGGER.warning(" :( Error al ejecutar el operationBinding: ", e);
            }
            
//            LOGGER.warning("Accedemos y ejecutamlos el metodo setpIdOp de DocumentosTrazabilidadOperacionVO...");
//            try{
//                    LOGGER.warning("Acceso permitido...");
//                    BindingContainer binding = ADFUtils.getBindingContainer();
//                    OperationBinding operationBinding = binding.getOperationBinding("setpIdOp");
//                    operationBinding.execute();
//                
//                    if(!operationBinding.getErrors().isEmpty()){
//                        LOGGER.warning("OperationBinding devuelve errores");
//                    }else{
//                            LOGGER.warning("***Carga IdOperacion Listo, redireccionando... ");
//                        }
//                }catch(Exception e){
//                        LOGGER.warning("Acceso negado...");
//                        LOGGER.log(ADFLogger.ERROR, "Error en consultarDatosOperacion " + e.getClass() + ":" + e.getMessage());
//                        JSFUtils.addFacesErrorMessage("Error al Consultar datos de la operacion. Por favor intente más tarde.");
//                }
            LOGGER.warning("Se a finalizado el metodo limpiarTabla()...");
        }
    
    public void cargarRow(){
            LOGGER.warning("*** Inicia la llamada a el metodo cargarRow de VO ");
            try{
                BindingContainer binding = getBindings();
                OperationBinding operBinding = binding.getOperationBinding("cargarRowVo");
                operBinding.execute();
                
                if(!operBinding.getErrors().isEmpty()){
                    LOGGER.warning(" :( OperationBinding devuelve errores");
                }
            }catch(Exception e){
                    LOGGER.warning(" :( Error al ejecutar el operationBinding: ", e);
            }     
            LOGGER.warning("*** Termina la llamada a el metodo cargarRow de VO ");
        }
    
    public void aceptarAgregarEvidenciaActionListener(ActionEvent actionEvent){
        aceptarAgregarEvidencia(actionEvent);
    }
    
    public Boolean aceptarAgregarEvidencia(ActionEvent actionEvent){
        LOGGER.warning("Inicia metodo para aceptarAgregarEvidencia...");
        DesembolsoBean desembolsoBean = (DesembolsoBean)JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        Boolean bandera = desembolsoBean.getAplicarScroll();
        LOGGER.warning("Valor bandera recibido: " + bandera);
        if(bandera == false){ 
            Boolean seleccion = null;
            try{
                seleccion = ( null != ADFUtils.getBoundAttributeValue("seleccion"))? new Boolean(ADFUtils.getBoundAttributeValue("seleccion").toString()) : null;
                LOGGER.warning("seleccionCheckBox--->" + seleccion);
            }catch(Exception e){
                LOGGER.warning("seleccionCheckBox--->null");
            }
            //LOGGER.warning("*** Continua proceso agregar evidencias 1");
            
            LOGGER.log(ADFLogger.TRACE, "Inside aceptarAgregarEvidenciaActionListener: " + actionEvent.getComponent().getId());
            BindingContainer bindings = ADFUtils.getBindingContainer();
            DCIteratorBinding voEvidenciasSolicitud = null;
            OperationBinding operationBinding = null;
            //LOGGER.warning("*** Continua proceso agregar evidencias 2");
            
            // ASIGNACION de variables
            voEvidenciasSolicitud = ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasSolicitudVOIterator");
            //LOGGER.warning("*** Continua proceso agregar evidencias 3");
            // Invocamos método que agrega las evidencias seleccionadas
            operationBinding = bindings.getOperationBinding("agregarEvidencias");
            //operationBinding.getParamsMap().put("idCondicion", (Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}"));
            //LOGGER.warning("*** Continua proceso agregar evidencias 4");
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            //LOGGER.warning("*** Continua proceso agregar evidencias 5");
            
            // Actualizamos lista de evidencias
            voEvidenciasSolicitud.executeQuery();
            //LOGGER.warning("*** Continua proceso agregar evidencias 6");
            // Cerramos popup
            getPopupAgregarEvidenciasDesembolsoExcepcion().hide();
            LOGGER.warning("Cerramos popUp");
            //LOGGER.warning("*** Continua proceso agregar evidencias 7");
            //actualizarEvidenciasSolicitud();
            desembolsoBean.setAplicarScroll(Boolean.TRUE);
            LOGGER.warning("Cambiamos valor de la variable AplicarScroll a true)");
        }
        return bandera;
    }
    
    public void cancelarAgregarEvidenciaActionListener(ActionEvent actionEvent){
        LOGGER.log(ADFLogger.TRACE, "Inside cancelarAgregarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup de Agregar evidencia
        getPopupAgregarEvidenciasDesembolsoExcepcion().hide();
    }
    
    private void actualizarEvidenciasSolicitud(){
        // Actualizamos lista de evidencias en base al nuevo IdCondicion
        LOGGER.log(ADFLogger.TRACE, "Inside actualizarListaEvidencias");
        LOGGER.warning("Inicia metodo actualizar evidencias solicitud");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voEvidenciasSolicitud = null;
        OperationBinding operationBinding = null;
        
        // ASIGNACION de variables
        voEvidenciasSolicitud = ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasSolicitudVOIterator");
        
        // Invocamos método que asigna la bind variable IdCondicion en EvidenciasCondicionesVO
        operationBinding = bindings.getOperationBinding("setpIdSolicitudEviSol");
        operationBinding.getParamsMap().put("value", (Long)JSFUtils.resolveExpression("#{bindings.IdSolicitud.inputValue}"));

        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            LOGGER.warning("Metodo actualizar evidencias solicitud--- bindings regresa errores");
        }
        
        // Actualizamos lista de evidencias
        voEvidenciasSolicitud.executeQuery();
    }
    
    public void eliminarEvidenciaActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside eliminarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        DesembolsoBean desembolsoBean = (DesembolsoBean)JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        desembolsoBean.setAplicarScroll(Boolean.FALSE);
        LOGGER.warning("Agregamos el valor false a la bandera AplicarScroll");
        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupEliminarEvidencia().show(popupHints);
    }
    
    public void cancelarEliminarEvidenciaActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside cancelarEliminarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupEliminarEvidencia().hide();
    }
    
    public void eliminarAgregarEvidenciaActionListener(ActionEvent actionEvent){
        eliminarAgregarEvidencia();
    }
    
    public Boolean eliminarAgregarEvidencia(){
        LOGGER.warning("Inicia metodo eliminarAgregarEvidencia");
        DesembolsoBean desembolsoBean = (DesembolsoBean)JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        Boolean bandera = desembolsoBean.getAplicarScroll();
        LOGGER.warning("Valor bandera recibido: " + bandera);
        if(bandera == false){            
            //LOGGER.warning("Inicia metodo eliminarAgregarEvidencia--1");
            BindingContainer bindings = ADFUtils.getBindingContainer();
            //LOGGER.warning("Inicia metodo eliminarAgregarEvidencia--2");
            DCIteratorBinding voEvidenciasSolicitud = null;
            //LOGGER.warning("Inicia metodo eliminarAgregarEvidencia--3");
            OperationBinding operationBinding = null;
            //LOGGER.warning("Inicia metodo eliminarAgregarEvidencia--4");
            // Asignación de variables
            voEvidenciasSolicitud = ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasSolicitudVOIterator");
            //LOGGER.warning("Inicia metodo eliminarAgregarEvidencia--5");
            // Invocamos método que elimina la evidencia seleccionada
            operationBinding = bindings.getOperationBinding("eliminarTreEvidenciaSolicitud");
            //LOGGER.warning("Inicia metodo eliminarAgregarEvidencia--6");
            LOGGER.warning("idTreEvidenciaSolicitud--->" + Integer.parseInt(JSFUtils.resolveExpression("#{viewScope.idEvidenciaSolicitud}").toString()));
            operationBinding.getParamsMap().put("idTreEvidenciaSolicitud", Integer.parseInt(JSFUtils.resolveExpression("#{viewScope.idEvidenciaSolicitud}").toString()));
            LOGGER.warning("Inicia metodo eliminarAgregarEvidencia(LLama al metodo de elminar de la VO)");
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            //LOGGER.warning("Inicia metodo eliminarAgregarEvidencia--8(Finaliza el meotodo eliminarAgregarEvidencia)");
            // Actualizamos lista de evidencias
            voEvidenciasSolicitud.executeQuery();
            LOGGER.warning("Actualizamos lista de evidencias)");
            // Cerramos popup
            getPopupEliminarEvidencia().hide();
            LOGGER.warning("Cerramos popUP)");
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupAgregarEvidenciasDesembolsoExcepcion());
            desembolsoBean.setAplicarScroll(Boolean.TRUE);
            LOGGER.warning("Cambiamos valor de la variable AplicarScroll a true)");
        }
        return bandera;
    }
    
    public void setDocpop(String docpop) {
        this.docpop = docpop;
    }

    public String getDocpop() {
        DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_DOCPOP"}));
        String url = row == null ? null : (String) row.getAttribute("Valor");
        return url;
    }
    
    public void cancelarCancelOperacionDesembolsoExcepcionActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside ccancelarFinalizarTareaDesembolsoExcepcionActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupCancelOperacionDesembolsoExcepcion().hide();
    }
    
    public void abrirCancelOperacionDesembolsoExcepcionActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside popupCancelOperacionDesembolsoExcepcionActionListener: " + actionEvent.getComponent().getId());
        // Abrimos popup
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupCancelOperacionDesembolsoExcepcion().show(popupHints);
    }

    public void setPopupConfFinalizarTareaDesembolsoExcepcion(RichPopup popupConfFinalizarTareaDesembolsoExcepcion) {
        this.popupConfFinalizarTareaDesembolsoExcepcion = popupConfFinalizarTareaDesembolsoExcepcion;
    }

    public RichPopup getPopupConfFinalizarTareaDesembolsoExcepcion() {
        return popupConfFinalizarTareaDesembolsoExcepcion;
    }


    public void setPopupCancelOperacionDesembolsoExcepcion(RichPopup popupCancelOperacionDesembolsoExcepcion) {
        this.popupCancelOperacionDesembolsoExcepcion = popupCancelOperacionDesembolsoExcepcion;
    }

    public RichPopup getPopupCancelOperacionDesembolsoExcepcion() {
        return popupCancelOperacionDesembolsoExcepcion;
    }
    
    public String invocarCancelarOperacion() {
        popupCancelOperacionDesembolsoExcepcion.hide();
        LOGGER.entering(this.getClass().getName(), "invocarCancelarOperacion");
        LOGGER.warning("Inicia el proceso de Invocar Cancelar Operacion");
        //int codigoTarea = this.getDesembolsoBean().getIdTarea() != null ? (this.getDesembolsoBean().getIdTarea().trim().length() > 0 ? Integer.parseInt(this.getDesembolsoBean().getIdTarea()) : 0) : 0;
        
        // if (this.validaComentarioOperacion(codigoTarea, true)) {
        Integer codigoTarea = getCodigoTarea();
        LOGGER.warning("Codigo de Tarea: " + codigoTarea);
        
        switch (codigoTarea)
        {
            case FenixConstants.CGD_JUSTIFICAR_DESEMBOLSO_POR_EXCEPCION:
            LOGGER.warning("Ingresa switch Invocar Cancelar Operacion");
            if (this.validaComentarioOperacionJustificar(FenixConstants.CGD_JUSTIFICAR_DESEMBOLSO_POR_EXCEPCION, true)) {
                LOGGER.warning("Ingresa al if");
                cancelarOperacionPopup();
            }else{
                    LOGGER.warning("Ingresa al else");
                }
        }   
        
        LOGGER.exiting(this.getClass().getName(), "invocarCancelarOperacion", null);
        return null;
    }
    
    public boolean validaComentarioOperacionJustificar(Integer intTarea, boolean showMsg) {
        LOGGER.entering(this.getClass().getName(), "validaComentarioOperacionJustificar", new Object[] { intTarea, showMsg });

        Boolean esComentario = false;
        Number idOperacion = null;
        String msg = null;
        try {
            idOperacion = new Number(this.getDesembolsoBean().getIdOperacion());
        } catch (Exception e) {
            LOGGER.severe("No se obtuvo Id de Operacion", e);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idOperacion", idOperacion);
        params.put("intTarea", intTarea);

        try {
            esComentario = super.execute(params, VALIDAR_COMENTARIO_OPERACION_FH_ACTUAL_OPER);
            LOGGER.warning("Result Oper Binding: " + esComentario);

            if (esComentario != null) {

                if (!esComentario) {
                    LOGGER.warning("No existe comentario de operacion");
                    msg = getStringFromBundle(MSG_ERROR_AGREGAR_COMENTARIO);
                } else {
                    LOGGER.warning("Comentario de operacion si existe");
                }
            } else {
                LOGGER.severe("El resultado de la operacion no fue obtenido");
                msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_FALLO);
            }
        } catch (OperationExecuteException e) {
            LOGGER.severe("La operacion contiene errores. " + e.getMessage());
            msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_FALLO);
        }

        if (showMsg && msg != null) {
            LOGGER.warning("Muestra mensaje de error: " + msg);
            JSFUtils.addFacesErrorMessage(msg);
        }

        LOGGER.exiting(this.getClass().getName(), "validaComentarioOperacionAjustes", esComentario);
        return esComentario;
    }
    
    public Integer banderaPopupJustificarDesembolso(){
        return bandera;
    }
    
    public Boolean validaEvidenciasJustificaciones(){
        Boolean validacion = Boolean.FALSE;
        Boolean validacionEvidencias = Boolean.FALSE;
        Boolean validacionJustifiaciones = Boolean.FALSE;
        Long idSolicitud = null;
        String instanciaProceso = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        idSolicitud = Long.parseLong(this.getDesembolsoBean().getIdSolicitud().toString());
        instanciaProceso = this.getDesembolsoBean().getInstanciaTarea().toString();
        
        LOGGER.warning("Inicia metodo validaEvidencias...");
        operationBinding = bindings.getOperationBinding("obtenerEvidenciasByIdSolicitud");
        operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
        operationBinding.execute();
        validacionEvidencias = (Boolean)operationBinding.getResult();
        LOGGER.warning("validacionEvidencias--->"+validacionEvidencias);
        
        LOGGER.warning("Inicia metodo validaJustificaciones...");
        operationBinding = bindings.getOperationBinding("obtenerJustificacionesByIdSolicitud");
        operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
        operationBinding.execute();
        validacionJustifiaciones = (Boolean)operationBinding.getResult();
        LOGGER.warning("validacionJustifiaciones--->"+validacionJustifiaciones);
        
        if(validacionEvidencias == Boolean.TRUE || validacionJustifiaciones == Boolean.TRUE)
            validacion = Boolean.TRUE;
        
        LOGGER.warning("validacion--->"+validacion);
        
        LOGGER.warning("Finaliza el metodo validaEvidenciasJustificaciones");
        
        return validacion;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    public void agregarJustificacionExcepcion(){        
        LOGGER.log(ADFLogger.WARNING, "agregarJustificacionExcepcion");
       
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding;
        Long resultado = null;
        operationBinding = bindings.getOperationBinding("insertarFormulariosJustificacionExcepcion");
        operationBinding.execute();
        
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            resultado = (Long)operationBinding.getResult();
        }
        
        DesembolsoBean bean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        
        BindingContainer bindingsConsulta = ADFUtils.getBindingContainer();
        OperationBinding operationBindingConsulta = null;
/*
        operationBindingConsulta = bindingsConsulta.getOperationBinding("setValores");
        operationBindingConsulta.getParamsMap().put("idSolicitud", Long.valueOf(bean.getIdSolicitud()));
        operationBindingConsulta.execute();
  */    
                    DCIteratorBinding voConsultaJustificacionExcepcion = null;
            
            // ASIGNACION de variables
            voConsultaJustificacionExcepcion = ADFUtils.getDCBindingContainer().findIteratorBinding("ConsultaJustificacionExcepcionVOIterator");
            
            // Actualizamos lista de evidencias
            voConsultaJustificacionExcepcion.executeQuery();
            LOGGER.warning("*** Actualizamos justificaciones");
            
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorJustificaciones());

        ADFUtils.setBoundAttributeValue("Observacion", null); 
        
    }

    public void setContenedorJustificaciones(RichPanelGroupLayout contenedorJustificaciones) {
        this.contenedorJustificaciones = contenedorJustificaciones;
    }

    public RichPanelGroupLayout getContenedorJustificaciones() {
        return contenedorJustificaciones;
    }

    /**
     * Método de validación de las excepciones del caso de uso para 
     * "Gestionar desembolsos de fuentes externas"
     */
    private void validaGestionarDesembolsoFuentesExternas() {
        if (this.validarCamposRequeridosTransferenciaRecursos() &&
            this.validaGestorDesembolsoFuentesExternasEX02() &&
            this.validarFormaTransferencia() &&
            this.validaGestorDesembolsoFuentesExternasEX04_05()){
            finalizarTareaPopup();
        }
    }
    
    /**
     * Valida si el monto de desembolsado por la fuente no
     * coincide con el monto de la transferencia.
     * EX02 del CU
     * @return valido Boolean
     */
    private Boolean validaGestorDesembolsoFuentesExternasEX02() {
        LOGGER.warning("*Inf, validando monto de fuente = monto transferencias registradas...");
        Boolean respuesta = Boolean.FALSE;
        String origenTransferencia = getOrigenTransferenciaByContrato();
        if(origenTransferencia != null){
             
               if(origenTransferencia.equalsIgnoreCase("CUENTAS_BCIE")){
                   LOGGER.warning("*Inf, se recuepero un origen de transferencias CUENTAS_BCIE aplicando EX02 del CU");
                        BigDecimal totalTransferidoFuente = this.getSumaMontoTransferidoFuente();
                        BigDecimal totalRegistradoTransferencias = this.getSumaMontoRegistradoTransferencias();                
                        
                        LOGGER.warning("*Inf, totalTransferidoFuente->"+totalTransferidoFuente);
                        LOGGER.warning("*Inf, totalRegistradoTransferencias->"+totalRegistradoTransferencias);
                       
                        if(totalTransferidoFuente != null && totalRegistradoTransferencias !=null){
                        
                            if ((totalTransferidoFuente.compareTo(totalRegistradoTransferencias)) != 0 ) {
                                JSFUtils.addFacesErrorMessage(this.getStringFromBundle("MSG_VALIDACION_GESTIONAR_DESEMBOLSOS_FUENTES_EXTERNAS_EX02"));                               
                            } else {
                                respuesta = Boolean.TRUE;
                            }  
                            
                        }else{
                            LOGGER.warning("***Error,no se pudo realizar la validacion parametros requeridos totalTransferidoFuente y totalRegistradoTransferencias");
                            JSFUtils.addFacesErrorMessage("Error no se pudo validar total Transferido Fuente igual a total Registrado Transferencias");                            
                        } 
               }else if(origenTransferencia.equalsIgnoreCase("DIRECTO_CLIENTE")){
                   LOGGER.warning("*Inf, se recuepero un origen de transferencias DIRECTO_CLIENTE se omite EX02 del CU");
                   respuesta = Boolean.TRUE;
               }else{
                   JSFUtils.addFacesErrorMessage("Error, no se pudo identificar el origen de las transferencias.");
                   LOGGER.warning("*Inf, No se pudo identificar el origen de las transferencias valor ->"+origenTransferencia);
                }
        }else{
            JSFUtils.addFacesErrorMessage("Error, no se pudo recuperar el origen de las transferencias");
            return Boolean.FALSE;
        }
        
        LOGGER.warning("respuesta: " + respuesta);
        return respuesta;
    }
    
    
    private String getOrigenTransferenciaByContrato(){
      LOGGER.warning("*Inf, Inicia metodo getOrigenTransferenciaByContrato");       
        Row fila = null;
        String origeTransferencia = null;
        
        try{
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("getContratoSeleccionado");                
                operationBinding.execute();
               
               if(!operationBinding.getErrors().isEmpty()){
                   LOGGER.warning("*Inf, Error al ejecutar el operBinding getContratoSeleccionado->"+operationBinding.getErrors());
                   JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
               }else{                   
                     fila = (Row) operationBinding.getResult();                                        
                        
                       if(fila != null){
                        
                           origeTransferencia = (fila.getAttribute("OrigenTranferenciaCliente") == null
                                               || fila.getAttribute("OrigenTranferenciaCliente").toString().equals("") )
                                               ? null : fila.getAttribute("OrigenTranferenciaCliente").toString();
                                                      
                       }else{
                           LOGGER.warning("*Inf, Iportant! se pudo recuperar el Origen de la Tranferencia");         
                       }    
                   
                }
            
        }catch(Exception e){
            LOGGER.warning("***Error, al intentar recuperar el origen de la Transferencia  ", e);
        }
        
        
      LOGGER.warning("*Inf, Termina metodo getOrigenTransferenciaByContrato"+origeTransferencia);
      return origeTransferencia;
    }
    
    
    
    /**
     * Obtiene la suma del monto transferido por la fuente de la VO 
     * FuentesExternasContratoDesembolsoVOIterator
     * @return total Double
     */
    private BigDecimal getSumaMontoTransferidoFuente() {
            
            BigDecimal total = BigDecimal.ZERO;
        
        try{
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = null;
                operationBinding = binding.getOperationBinding("obtenerMontoTotalDesembolsado");                
                operationBinding.execute();
               
               if(!operationBinding.getErrors().isEmpty()){
                   // Manejo de errores
                   JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
               }else{                   
                     BigDecimal montoDesembolsarFuente = (BigDecimal) operationBinding.getResult();
                           
                    if(montoDesembolsarFuente != null)
                        total = montoDesembolsarFuente;
                    else
                        LOGGER.warning("*Inf, Important! el monto desembolsar de la fuente es resuelto a null");
                }
            
        }catch(Exception e){
            LOGGER.warning("***Error, al intentar recuperar el current de Transferencia de Recursos  ", e);
        }
        
        return total;
    }
    
    /**
     * Obtiene la suma del monto registrado en las transferecias de la VO 
     * TransferenciasBancariasTablaVO
     * @return total BigDecimal
     */
    private BigDecimal getSumaMontoRegistradoTransferencias() {
       LOGGER.warning("*Inf, Inicia metodo recuperarMontoTotalTransferencias");    
            BigDecimal total = BigDecimal.ZERO;            
        
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();       
            OperationBinding operationBinding = bindings.getOperationBinding("recuperarMontoTotalTransferencias");                   
            operationBinding.execute(); 
                        
                 if(!operationBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding recuperarMontoTotalTransferencias devuelve errores");
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());  
                 }else{
                     
                        BigDecimal montoTotalTransferencias = (BigDecimal)operationBinding.getResult();
                     
                         if(montoTotalTransferencias != null )
                                total = montoTotalTransferencias;
                         else
                            LOGGER.warning("*Inf, Important! el monto total de las transferencias es resuelto a null");                          
                     
                     }
        }catch(Exception e){
            LOGGER.log(ADFLogger.ERROR, "***Error en consultar el monto total de las transacciones ",e);
        }     
   
        LOGGER.warning("*Inf, Termina metodo recuperarMontoTotalTransferencias ");  
        return total;
    }
    
    
  
    /**
     * Valida si el origen de las transferencias no coincide al indicado
     * por el respnsable de la operación.
     * EX03 del CU
     * @return valido Boolean
     */
    private Boolean validaGestorDesembolsoFuentesExternasEX03() {
        Integer idForma = this.getIdFormaTransferenciaFuente();
        String forma = this.getFormaTransferenciaFuente(idForma);
        String origen = this.getOrigenTransferenciaFuente();
        LOGGER.warning("TcaFormaTransferencia seleccionada " + forma);
        LOGGER.warning("OrigenTranferencia seleccionada " + origen);
        
        if (forma != null && origen != null && forma.equals(origen)) {
            return true;
        } else {
            JSFUtils.addFacesErrorMessage(this.getStringFromBundle("MSG_VALIDACION_GESTIONAR_DESEMBOLSOS_FUENTES_EXTERNAS_EX03"));
            return false;
        }
    }
    
    /**
     * Valida si el origen de las transferencias no coincide al indicado
     * por el respnsable de la operación.
     * EX03 del CU
     * @return valido Boolean
     */
    private Boolean validarFormaTransferencia(){
        LOGGER.warning("Inicia metodo validarFormaTransferencia");
        Boolean valido = Boolean.FALSE;
        String origen = this.getOrigenTransferenciaFuente();
        String formaTransferencia = null;
        String forma = null;
        DCIteratorBinding voFuentesExtContratoDesembolso =
            ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasContratoDesembolsoVOIterator");
        
        if(null != voFuentesExtContratoDesembolso){
            LOGGER.warning("Registros de Fuentes Externas con Transferencias de recursos: " + voFuentesExtContratoDesembolso.getEstimatedRowCount());
            RowSetIterator iter = voFuentesExtContratoDesembolso.getViewObject().createRowSetIterator(null);
            Row row = null;
            Integer idFormaTransferencia = null;
            if(null != iter){
                iter.reset();
                while(iter.hasNext()){
                    row = iter.next();
                    if(null != row){
                        try{
                            idFormaTransferencia = (Integer) row.getAttribute("TcaFormaTransferencia");
                        }catch(Exception e){
                            LOGGER.warning("ERROR al obtener el idFormaTransferencia.", e);
                        }
                        
                        LOGGER.warning("IDFORMATRANSFERENCIA: " + idFormaTransferencia);
                        if(null != idFormaTransferencia){
                            
                            forma = this.getFormaTransferenciaFuente(idFormaTransferencia);
                            
                            if (forma != null && origen != null) {
                                if(forma.equalsIgnoreCase(origen)){
                                    valido = Boolean.TRUE;
                                }else{
                                    LOGGER.warning("La transferencia No coincide con el origen.");
                                    JSFUtils.addFacesErrorMessage(this.getStringFromBundle("MSG_VALIDACION_GESTIONAR_DESEMBOLSOS_FUENTES_EXTERNAS_EX03"));
                                    break;
                                }
                            } else {
                                LOGGER.warning("La Forma de transferencia de Recursos o el orígen de la transferencia son NULL.");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_FORMA_TRANSFERENCIA"));
                                break;
                            }
                        }else{
                            LOGGER.warning("idFormaTransferencia es NULL.");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_FORMA_TRANSFERENCIA"));
                            break;
                        }
                    }else{
                        LOGGER.warning("EL row es NULL.");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_FORMA_TRANSFERENCIA"));
                        break;
                    }
                }
            }else{
                LOGGER.warning("El iterador es NULL.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_FORMA_TRANSFERENCIA"));
            }
            iter.closeRowSetIterator();
        }else{
            LOGGER.warning("Objeto DCIteratorBinding de FuentesExternas es NULL.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_FORMA_TRANSFERENCIA"));
        }
        
        LOGGER.warning("validarFormaTransferencia: " + valido);
        
        LOGGER.warning("Termina metodo validarFormaTransferencia");
        return valido;
    }
    
    /**
     * Obtiene el id de la forma de transferencia de la fuente
     * @return id Integer
     */
    private Integer getIdFormaTransferenciaFuente() {
        Integer idForma = null;
        if (ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasContratoDesembolsoVOIterator") != null) {
            Row row = ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasContratoDesembolsoVOIterator").getCurrentRow();
            
            if (row != null) {
                
                if (row.getAttribute("TcaFormaTransferencia") != null) {
                    try {
                        idForma = (Integer) row.getAttribute("TcaFormaTransferencia");
                    } catch(Exception e) {
                        LOGGER.warning("No se pudo obtener el atributo TcaFormaTransferencia de TransferenciaRecursosVOIterator - ", e.getMessage());
                    }
                } else {
                    LOGGER.warning("No se encontró el TcaFormaTransferencia");
                }
            } else {
                LOGGER.warning("El Row es null");
            }
        } else {
            LOGGER.warning("No se encontró el TransferenciaRecursosVOIterator");
        }
        return idForma;
    }
    
    /**
     * Obtiene la forma de transferncia de la fuente
     * (analista DAECI)
     * @param idForma Integer
     * @return forma String
     */
    private String getFormaTransferenciaFuente(Integer idForma) {
        LOGGER.warning("Inside getFormaTransferenciaFuente.");
        LOGGER.warning("idForma: " + idForma);
        
        String forma = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", idForma);
        try {
            forma = super.execute(params, "getCodigoExternoFormaTransferencia");
        } catch (OperationExecuteException e) {
            LOGGER.log(ADFLogger.ERROR, "getCodigoExternoFormaTransferencia", e);
        }
        
        LOGGER.warning("formaTransferenciaFuente: " + forma);
        
        return forma;
    }
    
    /**
     * Obtiene el origen de la transferencia.
     * (Responsable de la operación)
     * @return origen String
     */
    private String getOrigenTransferenciaFuente() {
        LOGGER.warning("Inside getOrigenTransferenciaFuente.");

        String origen = null;
        Row row = obtenerDatosContrato();

        if (row != null) {
            if (row.getAttribute("OrigenTranferenciaCliente") != null) {
                try {
                    origen = (String) row.getAttribute("OrigenTranferenciaCliente");

                    LOGGER.warning("idDesembolso: " + row.getAttribute("Id"));

                } catch (Exception e) {
                    LOGGER.warning("No se pudo obtener el atributo OrigenTranferenciaCliente de ContratoDesembolsoVOIterator - ",
                                   e.getMessage());
                }
            } else {
                LOGGER.warning("No se encontró el OrigenTranferenciaCliente");
            }
        } else {
            LOGGER.warning("El Row es null");
        }

        LOGGER.warning("origenTransferenciaFuente: " + origen);

        return origen;
    }
    
    /**
     * Valida que el origen de la transferencia es "Desembolso directo a Cliente", 
     * pero el contrato de desembolso tiene transferencias registradas.
     * Y
     * Valida que la forma de transferencia de la fuente es "Desembolso a cuentas BCIE", 
     * pero el contrato de desembolso no cuenta con transferencias registradas.
     * EX04 y EX_05 del CU
     * @return valida Boolean
     */
    private Boolean validaGestorDesembolsoFuentesExternasEX04_05() {
        LOGGER.warning("Inside validaGestorDesembolsoFuentesExternasEX04_05.");
        Boolean valida = Boolean.FALSE;
        Integer idForma = this.getIdFormaTransferenciaFuente();
        
        LOGGER.warning("idFormaTransferenciaFuente: " + idForma);
        
        switch (idForma) {
        case FenixModelConstants.FORMA_TRANSF_FUENTE_DESEMBOLSO_DIRECTO_CLIENTE:
            valida = !this.existenTransferenciasRegistradasByIdContrato();
            if (!valida) {
                JSFUtils.addFacesErrorMessage(this.getStringFromBundle("MSG_VALIDACION_GESTIONAR_DESEMBOLSOS_FUENTES_EXTERNAS_EX04"));
            }
            break;
        case FenixModelConstants.FORMA_TRANSF_FUENTE_DESEMBOLSO_CUENTAS_BCIE:
            valida = this.existenTransferenciasRegistradasByIdContrato();
            if (!valida) {
                JSFUtils.addFacesErrorMessage(this.getStringFromBundle("MSG_VALIDACION_GESTIONAR_DESEMBOLSOS_FUENTES_EXTERNAS_EX05"));
            }
            break;
        }
        
        LOGGER.warning("valida:: " + valida);
        
        return valida;
    }
    
    private Boolean existenTransferenciasRegistradasByIdContrato() {
        LOGGER.warning("Inside existenTransferenciasRegistradasByIdContrato.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idContrato", null);
        Boolean resultado = Boolean.FALSE;
        try {
            resultado = super.execute(params, "existenTransferenciasRegistradasByIdContrato");
            
            if(null == resultado){
                resultado = Boolean.FALSE;
            }
            LOGGER.warning("resultado: " + resultado);
            return resultado;
        } catch (OperationExecuteException e) {
            LOGGER.warning("resultado: " + resultado);
            return Boolean.FALSE;
        }
    }
    
    public String cancelarReserva(){
            getPopupReservaFondos().hide();
        return null;
        }

    public String reservaFondosAceptar() {
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
       
       // se comentaa el llamado al servicio para FNXII-7189 
       // if(aplicarCompensacionDesembolso()){   
              getPopupReservaFondos().hide();
              Boolean actualizarEstado=Boolean.TRUE;
              
              if(desembolsoBean != null){   
              
                 actualizarEstado=actualizarEstadoContrato(desembolsoBean.getContrato(), FenixConstants.ESTADO_SOLICITUD_APROBADO_POR_GERENTE);                        
                
                     if(actualizarEstado){
                          actualizarPayloadElement("estadoDesembolso", FenixConstants.ESTADO_SOLICITUD_APROBADO_POR_GERENTE);
                          cargarDocumentosOnBase();
                          InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
                          return invokeActionBean.invokeOperation();
                     }else{
                          JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_ACTUALIZAR_ESTADO_CONTRATO"));                     
                          return null;
                     }
                  
              }else{
                      JSFUtils.addFacesErrorMessage("Error, El objeto desembolsoBean es resuelto a null");
              }
                            
      /*    }else{
                  JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_APLICAR_COMPENSACION_DESEMBOLSO"));            
            }
      */
        return null;
    }

    public void setPopupReservaFondos(RichPopup popupReservaFondos) {
        this.popupReservaFondos = popupReservaFondos;
    }

    public RichPopup getPopupReservaFondos() {
        return popupReservaFondos;
    }

    public String reservaInvoke() {
        LOGGER.log(ADFLogger.WARNING, "Inicia metodo reservaInvoke");
        // Add event code here...
        Boolean validaComenttario=Boolean.FALSE;
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");

            String codTarea = null;
            Long codOeracion = getIdOperacion();
            String codInstanciaTarea = getInstanciaTarea();   
            
            if(desembolsoBean != null)
                codTarea = desembolsoBean.getIdTarea();
                    
            LOGGER.log(ADFLogger.WARNING, "codTarea->"+codTarea );
            LOGGER.log(ADFLogger.WARNING, "codOeracion->" +codOeracion);
            LOGGER.log(ADFLogger.WARNING, "codInstanciaTarea->"+codInstanciaTarea);       
          
            if(codTarea == null  || codOeracion == null || codInstanciaTarea.equals("") ){
                    LOGGER.warning("Parametros solicitados para validar incompletos");
                    JSFUtils.addFacesErrorMessage("Error parametros requeridos resueltos a null");
            }else{
              
              
                if(validarTransferenciasFlex()){
               
                        try{
                           validaComenttario= validateComentario(codOeracion, codTarea, codInstanciaTarea);
                        }catch(Exception e){
                            LOGGER.log(ADFLogger.WARNING, "ha ocurrido un error al validar el comentario ",e);
                            JSFUtils.addFacesErrorMessage("ha ocurrido un error al validar el comentario "+e.getMessage());
                        }    
                        
                        if(validaComenttario){   
                             LOGGER.log(ADFLogger.WARNING, "Abriendo popUp de confirmacion ...");                                                
                             RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                             getPopupReservaFondos().show(popupHints);                        
                        }else{   
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("pc06desembolso_revisar_solicitud_presidencia_msg_error_comentario_operacion_requerido"));
                        }
                
                }else{
                    JSFUtils.addFacesInformationMessage("No se pueden requerir ajustes de un desembolso con una transferencia ya registrada");
                }
                  
           }
              
        LOGGER.log(ADFLogger.WARNING, "Termina metodo reservaInvoke");
        return null;
    }
    
    
    
    public Boolean validarTransferenciasFlex(){
     LOGGER.warning("Inicia metodo validarTransferenciasFlex");
     Boolean pasoValidacion = Boolean.FALSE;
     Boolean existenTransfernciasFlex = Boolean.TRUE;
     
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try{           
            operationBinding = bindings.getOperationBinding("existenTransfernciasFlex");                       
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){                
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());                
            }else{
                existenTransfernciasFlex = (Boolean)operationBinding.getResult();                
                pasoValidacion = (existenTransfernciasFlex) ? Boolean.FALSE : Boolean.TRUE;
            }
            
        }catch(Exception ex){
           JSFUtils.addFacesErrorMessage(""+ex);
        }
        
        LOGGER.warning("termina metodo validarTransferenciasFlex");
     return pasoValidacion;
    }
    
    
    public Boolean actualizarEstadoContrato(String contrato, Integer estado){
        Boolean resultado=Boolean.TRUE;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            try{
                // Invocamos metodo y asignamos las variables correspondientes
                operationBinding = bindings.getOperationBinding("actualizarEstado");
                operationBinding.getParamsMap().put("idContrato", contrato);
                operationBinding.getParamsMap().put("estado", estado);
                
                resultado=(Boolean) operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    resultado=Boolean.FALSE;
                } 
                
            }catch(Exception ex){
            resultado=Boolean.FALSE;
                ex.printStackTrace();
                }
        return resultado;
        }
    
    public Boolean validarBHQ(String contrato){
        Boolean resultado=Boolean.TRUE;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            try{
                // Invocamos metodo y asignamos las variables correspondientes
                operationBinding = bindings.getOperationBinding("validarBHQ");
                operationBinding.getParamsMap().put("idContrato", Long.parseLong(contrato));
                
                resultado=(Boolean) operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    resultado=Boolean.FALSE;
                } 
                
            }catch(Exception ex){
            resultado=Boolean.FALSE;
                ex.printStackTrace();
                }
        return resultado;
        }
    public Boolean validarConsolidadas(){
        Boolean resultado=Boolean.TRUE;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            try{
                // Invocamos metodo y asignamos las variables correspondientes
                operationBinding = bindings.getOperationBinding("validarTransferenciasAplicadas");
                
                resultado=(Boolean) operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    resultado=Boolean.FALSE;
                } 
                
            }catch(Exception ex){
            resultado=Boolean.FALSE;
                ex.printStackTrace();
                }
        return resultado;
        }
    
    /**
     * Valida que se indicó que si se cuenta con los recursos para cubrir el monto del contrato de desembolso.
     */
    private void validaValidarFondosPresupuestarios() {
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        LOGGER.warning("Valor SelectOneRadio seleccionado: " + this.getSelectOptionSOR().getValue());
        
        if (this.getSelectOptionSOR().getValue() != null) {
            Integer valorSeleccionado = Integer.valueOf(this.getSelectOptionSOR().getValue().toString());
            if (valorSeleccionado.compareTo(1) == 0) {
                desembolsoBean.setCuentaPresupuestoMonto(1);
                finalizarTareaPopup();
            } else {
                LOGGER.warning("Segundo else");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MENSAJE_CUENTA_RECURSOS"));
            }
        } else {
            LOGGER.warning("Primer else");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MENSAJE_CUENTA_RECURSOS"));
        }
    }

    public void setSelectOptionSOR(RichSelectOneRadio selectOptionSOR) {
        this.selectOptionSOR = selectOptionSOR;
    }

    public RichSelectOneRadio getSelectOptionSOR() {
        return selectOptionSOR;
    }
    
    
    public Boolean validarReservaContrato(Long idContrato){
            LOGGER.warning("inicia metodo validarReservaContrato contrato ->"+idContrato);  
            Boolean respuesta = Boolean.FALSE;
                try{
                    BindingContainer bindings = ADFUtils.getBindingContainer();
                    OperationBinding operBinding = bindings.getOperationBinding("verificarReservaContrato");
                    operBinding.getParamsMap().put("idContrato", idContrato);
                    respuesta = (Boolean)operBinding.execute();               
                
                  if(!operBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding validarReservaContrato devuelve errores");
                   }            
                }catch(Exception e){
                   LOGGER.warning(" :( Error al ejecutar el operationBinding validarReservaContrato: ", e);
                } 
                return respuesta;
            }

    public Boolean validarCamposRequridosEnRealizarAjustes() {
        Boolean camposValidos = Boolean.FALSE;
        Boolean existeCambiosPendientesFuentesExternas = Boolean.FALSE;
        
        //AttributeBinding fechaSolicitudAttr = ADFUtils.findControlBinding("FechaSolicitud");
        Timestamp fechaSolicitudAttr = (Timestamp) ADFUtils.getBoundAttributeValue("FechaSolicitud");
        
        LOGGER.warning("fechaSolicitudAttr:"+fechaSolicitudAttr);
        
        if (null != fechaSolicitudAttr) {
            camposValidos = Boolean.TRUE;
        } else {
            String msg = getStringFromBundle(ES_NECESARIO_CAPTURAR_DATOS_OBLIGATORIOS);
            JSFUtils.addFacesErrorMessage(msg);
        }
        
        if(camposValidos){
            LOGGER.warning("Validando cambio pendientes en Fuentes externas.");
            try{
                existeCambiosPendientesFuentesExternas = execute(null, "validarCambioPendienteFuentesExternas");
            }catch(Exception e){
                LOGGER.warning("ERROR al ejecutar operBinding validarCambioPendienteFuentesExternas.", e);
            }
        }
        
        if(existeCambiosPendientesFuentesExternas){
            JSFUtils.addFacesErrorMessage("Existen cambios por guardar en Fuentes Externas.");
            camposValidos = Boolean.FALSE;
        }
        
        return camposValidos;
    }
    
    public Boolean validarMontoContratoVsOperacion(){
        LOGGER.warning("Inicia metodo validarMontoContratoVsOperacion");
        Boolean resultado = Boolean.FALSE;
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        Map<String, Object> params = new HashMap<String, Object>();
        String solicitud = null;
        Long idSolicitud = null;
        Long idOperacion = null;
        String operacion = null;
        BigDecimal montoTotalSolicitud = null;
        BigDecimal montoDisponibleDesembolsar = null;
        
        if(null != desembolsoBean){
            solicitud = desembolsoBean.getIdSolicitud();
            operacion = desembolsoBean.getIdOperacion();
            if(null != solicitud){
                try{
                    idSolicitud = new Long(solicitud);
                }catch(Exception e){
                    LOGGER.warning("No se pudo convertir la solicitud STRING a LONG.", e);
                }
                
                try{
                    idOperacion = new Long(operacion);
                }catch(Exception e){
                    LOGGER.warning("No se pudo convertir la operacion STRING a LONG.", e);
                }
                
                params.put("idSolicitud", idSolicitud);
                try{
                    montoTotalSolicitud = execute(params, "obtenerSumaMontoContratos");
                }catch(Exception e){
                    LOGGER.warning("ERROR al ejecutar el metodo de obtenerSumaMontoContratos", e);
                }
                
                try{
                    Double montoDispDesembolsar = (Double) ADFUtils.getBoundAttributeValue("MontoDesembolsarOperacion");
                    montoDisponibleDesembolsar = new BigDecimal(montoDispDesembolsar);
                }catch(Exception e){
                    LOGGER.warning("ERROR al recuperar el MontoDesembolsarOperacion.", e);
                }
                
                LOGGER.warning("Monto total de la solicitud: " + montoTotalSolicitud);
                LOGGER.warning("Monto disponible a desembolsar: " + montoDisponibleDesembolsar);
                
                if(null != montoTotalSolicitud && null != montoDisponibleDesembolsar){
                    if(montoTotalSolicitud.compareTo(montoDisponibleDesembolsar)==1){
                        LOGGER.warning("Monto total de la solicitud es mayo que el de la operacion.");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_MONTO_SOLICITUD_OPERACION"));
                    }else{
                        resultado = Boolean.TRUE;
                    }
                }else{
                    LOGGER.warning("Monto disponible o monto total son NULL. NO se pudo validar.");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_ACCION_MSG"));
                    if(null == montoTotalSolicitud){
                        LOGGER.warning("validarMontoContratoVsOperacion() - La suma de los contratos es NULL.");
                    }
                    
                    if(null == montoDisponibleDesembolsar){
                        LOGGER.warning("validarMontoContratoVsOperacion() - El monto disponible a desembolsar de la operacion es NULL.");
                    }
                }
            }else{
                LOGGER.warning("solicitud es NULL.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_ACCION_MSG"));
            }
        }else{
        LOGGER.warning("Instancia de DesembolsosBean es NULL.");
        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_ACCION_MSG"));
        }
        
        LOGGER.warning("Validacion de monto solicitud vs contrato: " + resultado);
        LOGGER.warning("Termina metodo validarMontoContratoVsOperacion");
        return resultado;
    }
    
    public Boolean validarMontoAsignacionContraContrato(){
        LOGGER.warning("Inicia metodo validarMontoAsignacionContraContrato");
        Boolean resultado = Boolean.FALSE;
        Map<String, Object> params = new HashMap<String, Object>();
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        String solicitud = null;
        Long idSolicitud = null;
        
        if(null != desembolsoBean){
            solicitud = desembolsoBean.getIdSolicitud();
            if(null != solicitud){
                try{
                    idSolicitud = new Long(solicitud);
                }catch(Exception e){
                    LOGGER.warning("No se pudo convertir la solicitud STRING a LONG.", e);
                }
                
                LOGGER.warning("Contrato a evaluar: " + idSolicitud);
                if(null != idSolicitud){
                    params.put("idSolicitud", idSolicitud);
        
                    try {
                        resultado = execute(params, "validarMontoContratoVsLineaPasiva");
                        if(!resultado){
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_MONTO_NO_CONICIDE"));
                        }
                    } catch (Exception e) {
                        LOGGER.warning("ERROR al ejecutar el operation binding montoDesembolsarLineaPasivaVsContrato", e);
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_ACCION_MSG"));
                    }
                }else{
                    LOGGER.warning("IdContrato es NULL.");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_ACCION_MSG"));
                }
            }else{
                LOGGER.warning("Contrato es NULL.");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_ACCION_MSG"));
            }
        }else{
            LOGGER.warning("Instancia de DesembolsosBean es NULL.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_ACCION_MSG"));
        }
        
        LOGGER.warning("Validacion de montos es: " + resultado);
        LOGGER.warning("Termina metodo validarMontoAsignacionContraContrato");
        return resultado;
    }
    
    public Boolean validarMontoContratoVsAsignacion(){
        LOGGER.warning("Inicia metodo validarMontoContratoVsAsignacion.");
        Boolean resultado = Boolean.FALSE;
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        Map<String, Object> params = new HashMap<String, Object>();
        String contrato = desembolsoBean.getIdDesembolso();
        Long idContrato = null;
        
        try{
            idContrato = new Long(contrato);
        }catch(Exception e){
            LOGGER.warning("ERROR al convertir idContrato.", e);
        }
        
        params.put("idContrato", idContrato);
        try{
            resultado = execute(params, "montoDesembolsarLineaPasivaVsContrato");
        }catch(Exception e){
            LOGGER.warning("ERROR al ejecutar metodo para validar MontoContrato contra Asignacion.", e);
        }
        
        LOGGER.warning("Termina metodo validarMontoContratoVsAsignacion.");
        return resultado;
    }
    
    public List<String> validarFechaMaximaTotalidadRecursos(){
        LOGGER.warning("Inicia metodo validarFechaMaximaTotalidadRecursos");
        List<String> listaErrores = new ArrayList<String>();
        List listaErroresModelo = new ArrayList();
        Map<String, Object> params = new HashMap<String, Object>();
        Long idOperacion = getIdOperacion();
        
        params.put("idOperacion", idOperacion);
        try{
            listaErroresModelo = execute(params, "validarFechasTerminosDesembolsos");
        }catch(Exception e){
            LOGGER.warning("ERROR al validar la RN_02 y RN_03.", e);
        }
        
        if(null != listaErroresModelo && listaErroresModelo.size() > 0){
            LOGGER.warning("Iterando lista de errores del modelo.");
            String mensajeError = null;
            for(Object mensaje : listaErroresModelo){
                LOGGER.warning("Mensaje de error obtenido: " + mensaje.toString());
                mensajeError = mensaje.toString();
                
                if(!(mensajeError.equalsIgnoreCase("T102")) && !(mensajeError.equalsIgnoreCase("T103"))){
                    LOGGER.warning("No es mensaje de Validacion. Ocurrió un error.");
                    listaErrores.add(mensajeError);
                }else{
                    if(!(mensajeError.equalsIgnoreCase("T102"))){
                        LOGGER.warning("Validacion de T102 NO CUMPLIDA.");
                        listaErrores.add(getStringFromBundle("ERROR_VALIDACION_T102"));
                    }else{
                        LOGGER.warning("Validacion de T103 NO CUMPLIDA.");
                        listaErrores.add(getStringFromBundle("ERROR_VALIDACION_T103"));
                    }
                }
            }
        }
        
        LOGGER.warning("Termina metodo validarFechaMaximaTotalidadRecursos");
        return listaErrores;
    }
    
    public Boolean validarLimitesTasaMonto(){
        LOGGER.warning("Inicia metodo validarLimitesTasaMonto.");
        List<String> listaErrores = new ArrayList<String>();
        Boolean esErrorMonto = null;
        Long idOperacion = getIdOperacion();
        Map<String, Object> params = new HashMap<String,Object>();
        Map mapaDatosTerminoEnDesembolsos = new HashMap<>();
        BigDecimal montoDesembolsarContrato =  null;
        BigDecimal montoEnDolares =  null;
        BigDecimal montoMaximoDesembolso = null;
        BigDecimal montoMinimoDesembolso = null;
        BigDecimal montoMaximoConvertidoDesembolso = null;
        BigDecimal montoMinimoConvertidoDesembolso = null;
        BigDecimal tasaMaximaDesembolso = null;
        BigDecimal tasaMinimaDesembolso = null;
        Integer idTipoMonedaLineaCredito = null;
        
        
        try{
            montoDesembolsarContrato = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoDesembolsar");
        }catch(Exception e){
            LOGGER.warning("ERROR al recuperar el MontoDesembolsar del contrato.", e);
        }
        
        try {
            idTipoMonedaLineaCredito = this.getIdTipoMonedaLineaCredito();
        } catch (Exception e) {
            LOGGER.warning("ERROR al obtener el idTipoMoneda.");
        }
        
        params.put("idOperacion", idOperacion);
        try{
            mapaDatosTerminoEnDesembolsos = execute(params, "obtenerTccTerminoEnDesembolsos");
        }catch(Exception e){
            LOGGER.warning("ERROR al ejecutar OperationBinding obtenerTccTerminoEnDesembolsos.", e);
        }
        
        if(null != mapaDatosTerminoEnDesembolsos && mapaDatosTerminoEnDesembolsos.size() > 0){
            LOGGER.warning("Recuperando montos y tasa del mapa.");
            try{
                montoMaximoDesembolso = (BigDecimal) mapaDatosTerminoEnDesembolsos.get("montoMaximo");
            }catch(Exception e){
                LOGGER.warning("ERROR al recuperar el MontoMaximo. ", e);
            }
            
            try{
                montoMinimoDesembolso = (BigDecimal) mapaDatosTerminoEnDesembolsos.get("montoMinimo");
            }catch(Exception e){
                LOGGER.warning("ERROR al recuperar el MontoMinimo. ", e);
            }
            
            try{
                tasaMaximaDesembolso = (BigDecimal) mapaDatosTerminoEnDesembolsos.get("tasaMaxima");
            }catch(Exception e){
                LOGGER.warning("ERROR al recuperar la tasaMaxima. ", e);
            }
            
            try{
                tasaMinimaDesembolso = (BigDecimal) mapaDatosTerminoEnDesembolsos.get("tasaMinima");
            }catch(Exception e){
                LOGGER.warning("ERROR al recuperar la tasaMinima. ", e);
            }
        }else{
            LOGGER.warning("No se encontraron datos de montos y tasas del termino EnDesembolsos (T501).");
        }
        
        montoEnDolares = convertirMonedaDolar();
        
        LOGGER.warning("MONTOMAXIMO: " + montoMaximoDesembolso);
        LOGGER.warning("MONTOMINIMO: " + montoMinimoDesembolso);
        LOGGER.warning("MONTOENDOLARES: " + montoEnDolares);
        
        if(null != montoMinimoDesembolso ){
            if(null != montoEnDolares){
                if(null != idTipoMonedaLineaCredito)
                {
                    //se obtiene el montoMinimo convertido
                    params = new HashMap<String, Object>();
                    
                    params.put("claveTipo", idTipoMonedaLineaCredito);
                    params.put("codigoA", COD_EXT_MONEDA_LINEA_CREDITO);
                    params.put("monto", montoMinimoDesembolso);
                    try {
                        montoMinimoConvertidoDesembolso = execute(params, "convertirMonedas");
                        LOGGER.warning("Monto minimo convertido: " + montoMinimoConvertidoDesembolso);
                    } catch (Exception e) {
                        LOGGER.warning("ERROR al convertir el monto minimo a otro tipo de moneda.", e);
                    }
                    
                    //se verifica el monto convertido no sea null
                    if(null != montoMinimoConvertidoDesembolso)
                    {
                        LOGGER.warning("Validando monto maximo");
                        LOGGER.warning("Monto minimo de la linea: " + montoMinimoDesembolso);
                        LOGGER.warning("Monto minimo convertido de la linea: " + montoMinimoConvertidoDesembolso);
                        LOGGER.warning("Monto a desembolsar del contrato: " + montoEnDolares);
                        
                        if(montoEnDolares.compareTo(montoMinimoConvertidoDesembolso)==-1){
                            LOGGER.warning("El monto a desembolsar del contrato es MENOR que el monto minimo de la linea");
                            esErrorMonto = Boolean.TRUE;
                            return esErrorMonto;
                        }else{
                            LOGGER.warning("El monto a desembolsar del contrato es MAYOR o IGUAL al monto minimo de la linea");
                            esErrorMonto = Boolean.FALSE;
                        }
                    }
                    else
                    {
                        LOGGER.warning("Monto minimo convertido es NULL.");
                        esErrorMonto = Boolean.TRUE;
                    }    
                }
                else
                {
                    LOGGER.warning("ERROR: el idTipoMonedaLineaCredito es NULL, se salta la validacion");
                    esErrorMonto = Boolean.FALSE;
                }
            }else{
                LOGGER.warning("ERROR no se puede realizar la validacion de limites de montos. Algun valor es NULL.");
                JSFUtils.addFacesErrorMessage("ERROR no se puede realizar la validación de límites de montos de la línea de crédito." +
                    "Algún valor es NULL.");
            }
        }else{
            LOGGER.warning("ERROR no se puede realizar la validacion de limites de montos. El monto minimo es null. Se salta la validacion");
            esErrorMonto=Boolean.FALSE;
        }
        
        
        if(null != montoMaximoDesembolso ){
                if(null != montoEnDolares){
                    if(null != idTipoMonedaLineaCredito)
                    {
                        //se obtiene el montoMinimo convertido
                        params = new HashMap<String, Object>();
                        
                        params.put("claveTipo", idTipoMonedaLineaCredito);
                        params.put("codigoA", COD_EXT_MONEDA_LINEA_CREDITO);
                        params.put("monto", montoMaximoDesembolso);
                        try {
                            montoMaximoConvertidoDesembolso = execute(params, "convertirMonedas");
                            LOGGER.warning("Monto maximo convertido: " + montoMaximoConvertidoDesembolso);
                        } catch (Exception e) {
                            LOGGER.warning("ERROR al convertir el monto maximo a otro tipo de moneda.", e);
                        }
                        
                        //se verifica el monto convertido no sea null
                        if(null != montoMaximoConvertidoDesembolso)
                        {
                            LOGGER.warning("Validando monto maximo");
                            LOGGER.warning("Monto maximo de la linea: " + montoMaximoDesembolso);
                            LOGGER.warning("Monto maximo convertido de la linea: " + montoMaximoConvertidoDesembolso);
                            LOGGER.warning("Monto a desembolsar del contrato: " + montoEnDolares);
                            
                            if(montoEnDolares.compareTo(montoMaximoConvertidoDesembolso)==1){
                                LOGGER.warning("El monto a desembolsar del contrato es MAYOR que el monto maximo de la linea");
                                esErrorMonto = Boolean.TRUE;
                            }else{
                                LOGGER.warning("El monto a desembolsar del contrato es MENOR o IGUAL al monto maximo de la linea");
                                esErrorMonto = Boolean.FALSE;
                            }
                        }
                        else
                        {
                            LOGGER.warning("Monto Maximo convertido es NULL.");
                            esErrorMonto = Boolean.TRUE;
                        }    
                    }
                    else
                    {
                        LOGGER.warning("ERROR: el idTipoMonedaLineaCredito es NULL, se salta la validacion");
                        esErrorMonto = Boolean.FALSE;
                    }
                }else{
                    LOGGER.warning("ERROR no se puede realizar la validacion de limites de montos. Algun valor es NULL.");
                    JSFUtils.addFacesErrorMessage("ERROR no se puede realizar la validación de límites de montos de la línea de crédito." +
                        "Algún valor es NULL.");
                }
            }else{
                LOGGER.warning("ERROR no se puede realizar la validacion de limites de montos. El monto maximo es null. Se salta la validacion");
                esErrorMonto=Boolean.FALSE;
            }
        
        
        LOGGER.warning("Termina metodo validarLimitesTasaMonto.");
        return esErrorMonto;
    }
    
    private Integer getIdTipoMonedaLineaCredito()
    {
        LOGGER.warning("Inicia metodo getIdTipoMonedaLineaCredito");
        
        Integer idTipoMoneda = null;
        String codExternoTipoMoneda = null;
        
        Long idLineaCredito = null;
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> paramsMoneda = new HashMap<String, Object>();
        Map resultMap = new HashMap();
        
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        
        
        idLineaCredito = desembolsoBean.getLinea();
        LOGGER.warning("IdLineaCredito a consultar: " + idLineaCredito);
        
        if(null != idLineaCredito)
        {
            params.put("idLineaCredito", idLineaCredito);
            params.put("tipoMoneda", null);
            try{
                resultMap = execute(params, "consultarLineaCreditoByIdLineaCredito");
            }catch(Exception e){
                LOGGER.warning("ERROR al ejecutar OperBinding consultarLineaCreditoByIdLineaCredito.", e);
            }
        }else{
            LOGGER.warning("IdLineaCredito es NULL. No se puede obtener el monto disponible de la linea.");
        }
        
        if(null != resultMap && !resultMap.isEmpty() && resultMap.size() > 0){
            LOGGER.warning("Recuperando codigoExterno de la linea de servicio.");
            try{
                codExternoTipoMoneda = (String) resultMap.get("moneda");
            }catch(Exception e){
                LOGGER.warning("ERROR al recuperar codExternoMoneda de la linea.", e);
            }
            
            LOGGER.warning("CodExterno Moneda de la linea: " + codExternoTipoMoneda);
        }else{
            LOGGER.warning("resultMap no devuelve datos. No se puede recuperar el monto disponible de la linea.");
        }
        
        //Una vez que se tiene el codExternoMoneda se busca el idTipoMoneda
        if(codExternoTipoMoneda != null)
        {
            paramsMoneda.put("descripcionCorta", codExternoTipoMoneda);
            try{
                idTipoMoneda = execute(paramsMoneda, "obtenerIdTcaTipoMoneda");
            }catch(Exception e){
                LOGGER.warning("ERROR al ejecutar OperBinding obtenerIdTcaTipoMoneda.", e);
            }
        }
         
        LOGGER.warning("Termina metodo getIdTipoMonedaLineaCredito");
        return idTipoMoneda;
    }
    
    public BigDecimal convertirMonedaDolar(){
        LOGGER.warning("Inicia metodo convertirMonedaDolar.");
        BigDecimal montoConvertido = null;
        BigDecimal montoDesembolsarContrato = null;
        Integer idTipoMoneda = null;
        Map<String, Object> params = new HashMap<String,Object>();
        
        try{
            idTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
        }catch(Exception e){
            LOGGER.warning("ERROR al obtener el idTcaTipoMoneda.", e);
        }
        
        try{
            montoDesembolsarContrato = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoDesembolsar");
        }catch(Exception e){
            LOGGER.warning("ERROR al obtener el montoDesembolsar de contrato.", e);
        }
        
        LOGGER.warning("MONTODESEMBOLSAR: " + montoDesembolsarContrato);
        LOGGER.warning("IDTIPOMONEDA: " + idTipoMoneda);
        
        if(null == montoDesembolsarContrato || null == idTipoMoneda){
            return montoConvertido;
        }
        
        params.put("claveTipo", idTipoMoneda);
        params.put("codigoA", "USD");
        params.put("monto", montoDesembolsarContrato);
        try{
            montoConvertido = execute(params, "convertirMonedas");
            LOGGER.warning("Monto convertido: " + montoConvertido);
        }catch(Exception e){
            LOGGER.warning("ERROR al convertir el monto a otro tipo de moneda.", e);
        }
        
        LOGGER.warning("Monto del contrato convertido a dolares: " + montoConvertido);
        LOGGER.warning("Termina metodo convertirMonedaDolar.");
        return montoConvertido;
    }
    
    public BigDecimal convertirMoneda(BigDecimal montoConvertir, Integer idTipoMoneda, Integer tipoMonedaConvertir){
        LOGGER.warning("Inicia metodo convertirMonedaDolar.");
        BigDecimal montoConvertido = null;
        String codExtMonedaContrato = null;
        Map<String, Object> params = new HashMap<String,Object>();
        
        LOGGER.warning("MONTODESEMBOLSAR: " + montoConvertir);
        LOGGER.warning("IDTIPOMONEDA: " + idTipoMoneda);
        LOGGER.warning("IDTIPOMONEDACONVERTIR: " + tipoMonedaConvertir);
        
        if(null == montoConvertir || null == idTipoMoneda || null == tipoMonedaConvertir){
            return montoConvertido;
        }
        
        params.put("idTipoMoneda", tipoMonedaConvertir);
        try{
            codExtMonedaContrato = execute(params, "obtenerCodigoExternoMoneda");
            LOGGER.warning("Codigo externo de la moneda del contrato: " + codExtMonedaContrato);
        }catch(Exception e){
            LOGGER.warning("ERROR al obtener el codigo externo de la moneda del contrato.", e);
        }
        
        LOGGER.warning("Actualizando monto a desembolsar por montoMinimo");
        
        params.put("claveTipo", idTipoMoneda);
        params.put("codigoA", codExtMonedaContrato);
        params.put("monto", montoConvertir);
        try{
            montoConvertido = execute(params, "convertirMonedas");
            LOGGER.warning("Monto convertido: " + montoConvertido);
        }catch(Exception e){
            LOGGER.warning("ERROR al convertir el monto a otro tipo de moneda.", e);
        }
        
        LOGGER.warning("Monto del contrato convertido a dolares: " + montoConvertido);
        LOGGER.warning("Termina metodo convertirMonedaDolar.");
        return montoConvertido;
    }
    
    public Boolean ajustarMontoDesembolsarLimite(BigDecimal montoLimite){
        LOGGER.warning("Inicia metodo ajustarMontoDesembolsarLimite.");
        BigDecimal montoConvertido = null;
        Integer tipoMonedaConvertir = null;
        Boolean resultado = Boolean.FALSE;
        Map<String, Object> params = new HashMap<String,Object>();
        
        try{
            tipoMonedaConvertir = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
        }catch(Exception e){
            LOGGER.warning("ERROR al obtener el idTcaTipoMoneda.", e);
        }
        montoConvertido = convertirMoneda(montoLimite, ID_MONEDA_USD, tipoMonedaConvertir);
        
        LOGGER.warning("MontoConvertido: " + montoConvertido);
        params.put("nuevoMontoDesembolsar", montoConvertido);
        try{
            resultado = execute(params, "actualizarMontoDesembolsar");
        }catch(Exception e){
            LOGGER.warning("ERROR al ejecutar la actualizacion del monto a desembolsar.", e);
        }
        
        LOGGER.warning("RESULTADO DE ACTUALIZACION DEL MONMTO: " + resultado);
        LOGGER.warning("Termina metodo ajustarMontoDesembolsarLimite.");
        return resultado;
    }
    
    public void closeOnPollRealizarAjustes(PollEvent pollEvent){
        LOGGER.warning("Se cierra el popup RealizarAjustes automaticamente debido a la inactividad.");
        
        pollRealizarAjustes = (RichPoll)pollEvent.getComponent();
        pollRealizarAjustes.setInterval(-1);
        pollRealizarAjustes.setRendered(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pollRealizarAjustes);
        popupConfMasInfo.hide();
    }

    public void closeOnPollFinalizarTarea(PollEvent pollEvent){
        LOGGER.warning("Se cierra el popup FinalizarTarea automaticamente debido a la inactividad.");
        
        pollFinalizarTarea = (RichPoll)pollEvent.getComponent();
        pollFinalizarTarea.setInterval(-1);
        pollFinalizarTarea.setRendered(false);
        AdfFacesContext.getCurrentInstance().addPartialTarget(pollFinalizarTarea);
        popupConfFinalizarTarea.hide();
    }

    public void setPollRealizarAjustes(RichPoll pollRealizarAjustes) {
        this.pollRealizarAjustes = pollRealizarAjustes;
    }

    public RichPoll getPollRealizarAjustes() {
        return pollRealizarAjustes;
    }

    public void setPollFinalizarTarea(RichPoll pollFinalizarTarea) {
        this.pollFinalizarTarea = pollFinalizarTarea;
    }

    public RichPoll getPollFinalizarTarea() {
        return pollFinalizarTarea;
    }
    
    public String actualizarCuentaConPresupuesto(){
        LOGGER.warning("Ingresa al metodo actualizarCuentaConPresupuesto actionBean.");
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        Long idContrato = Long.valueOf( desembolsoBean.getIdDesembolso() );
        LOGGER.warning("idContrato: " + idContrato);
        Integer cuentaPresupuesto = desembolsoBean.getCuentaPresupuestoMonto();
        LOGGER.warning("cuentaPresupuesto: " + cuentaPresupuesto);
        Boolean respuesta = null;
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operBinding = bindings.getOperationBinding("actualizarCuentaConPresupuestoMonto");
            operBinding.getParamsMap().put("idContrato", idContrato);
            operBinding.getParamsMap().put("cuentaConPresupuesto", cuentaPresupuesto);
            respuesta = (Boolean)operBinding.execute();               
        
          if(!operBinding.getErrors().isEmpty()){
            LOGGER.warning("OperationBinding actualizarCuentaConPresupuestoMonto devuelve errores");
           }            
        }catch(Exception e){
           LOGGER.warning(" :( Error al ejecutar el operationBinding actualizarCuentaConPresupuestoMonto: ", e);
        }
        LOGGER.warning("Respuesta metodo actualizarCuentaConPresupuesto: " + respuesta);
        LOGGER.warning("Finaliza el metodo actualizarCuentaConPresupuesto actionBean.");
        return null;
    }
    
    
    public String obtenerUsuario(){
        LOGGER.warning("Ingresa al metodo actualizarCuentaConPresupuesto actionBean.");
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        Long idContrato = Long.valueOf( desembolsoBean.getIdDesembolso() );
        LOGGER.warning("idContrato: " + idContrato);
        Integer cuentaPresupuesto = desembolsoBean.getCuentaPresupuestoMonto();
        LOGGER.warning("cuentaPresupuesto: " + cuentaPresupuesto);
        String respuesta = null;
        if(null!=idContrato){
                try{
                    BindingContainer bindings = ADFUtils.getBindingContainer();
                    OperationBinding operBinding = bindings.getOperationBinding("usuarioCreador");
                    operBinding.getParamsMap().put("idContrato", idContrato);
                    respuesta = (String)operBinding.execute();               
                
                  if(!operBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding actualizarCuentaConPresupuestoMonto devuelve errores");
                       respuesta = null;
                   }            
                }catch(Exception e){
                    respuesta = null;
                   LOGGER.warning(" :( Error al ejecutar el operationBinding actualizarCuentaConPresupuestoMonto: ", e);
                }
            }
      
        LOGGER.warning("Respuesta metodo obtecerUsuario: " + respuesta);
        LOGGER.warning("Finaliza el metodo obtecerUsuario actionBean.");
        return respuesta;
    }

    public Boolean consultaCuentas() {
        LOGGER.warning("Ingresa a la validacion de cuentas BCIE.");
        Boolean respuesta = Boolean.FALSE;
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        Long idContrato = null;
        try {
            idContrato = new Long(desembolsoBean.getIdDesembolso());
        } catch (Exception e) {
            LOGGER.warning("ERROR al obtener el idContrato.", e);
        }
        if (null != idContrato) {
            try {
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operBinding = bindings.getOperationBinding("tieneCuentasBCIE");
                operBinding.getParamsMap().put("idContrato", idContrato);
                operBinding.getParamsMap().put("valor", FenixConstants.CUENTAS_BCIE);
                respuesta = (Boolean) operBinding.execute();

                if (!operBinding.getErrors().isEmpty()) {
                    LOGGER.warning("OperationBinding tieneCuentasBCIE devuelve errores");
                    respuesta = Boolean.FALSE;
                }
            } catch (Exception e) {
                LOGGER.warning("Error al ejecutar el operationBinding tieneCuentasBCIE: ", e);
            }
        } else {
            LOGGER.warning("ERROR no se pudo obtener el idContrato.");
        }
        LOGGER.warning("Respuesta a la validacion de cuentas BCIE.: " + respuesta);
        LOGGER.warning("Finaliza el metodo tieneCuentasBCIE.");
        return respuesta;
    }

    public void setSelectItemSi1(RichSelectItem selectItemSi1) {
        this.selectItemSi1 = selectItemSi1;
    }

    public RichSelectItem getSelectItemSi1() {
        return selectItemSi1;
    }
    

    public void setSelectItemSi2(RichSelectItem selectItemSi2) {
        this.selectItemSi2 = selectItemSi2;
    }

    public RichSelectItem getSelectItemSi2() {
        return selectItemSi2;
    }
     public void setInitForm(RichOutputText initForm) {
        this.initForm = initForm;
    }
   
    public Boolean guardarTransferenciaRecursos(){
        LOGGER.warning("Inicia metodo guardarTransferenciaRecursos");
        Boolean resultado = Boolean.FALSE;
        
        try{
            resultado = execute(null, "obtenerRowFuentesTransferenciaRecursos");
        }catch(Exception e){
            LOGGER.warning("ERROR al ejecutar el operBinding para guardar la Transferencia de recursos.", e);
        }
        
        LOGGER.warning("Termina metodo guardarTransferenciaRecursos");
        return resultado;
    }
    
    public Boolean validarCamposRequeridosTransferenciaRecursos(){
        LOGGER.warning("Inicia metodo validarCamposRequeridosTransferenciaRecursos");
        Boolean resultado = Boolean.TRUE;
        Boolean esCampoNull = Boolean.FALSE;
        DCIteratorBinding voFuentesExtContratoDesembolso =
            ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasContratoDesembolsoVOIterator");
        
        if(null != voFuentesExtContratoDesembolso){
            LOGGER.warning("Registros de Fuentes Externas con Transferencias de recursos: " + voFuentesExtContratoDesembolso.getEstimatedRowCount());
            RowSetIterator iter = voFuentesExtContratoDesembolso.getViewObject().createRowSetIterator(null);
            Row row = null;
            BigDecimal montoDesembolsar = null;
            if(null != iter){
                iter.reset();
                while(iter.hasNext()){
                    row = iter.next();
                    if(null != row){
                        try{
                            montoDesembolsar = (BigDecimal) row.getAttribute("MontoDesembolsar");
                        }catch(Exception e){
                            LOGGER.warning("ERROR al obtener el montoDesembolsar.", e);
                        }
                        
                        if(null != montoDesembolsar && montoDesembolsar.compareTo(BigDecimal.ZERO)==1){
                            LOGGER.warning("Validando los campos obligatorios de Transferencia de recursos.");
                            if(null == row.getAttribute("MontoDesembolsado")){
                                LOGGER.warning("El monto desembolsado requerido es NULL.");
                                resultado = Boolean.FALSE;
                                esCampoNull = Boolean.TRUE;
                            }
                            
                            if(null == row.getAttribute("Fecha")){
                                LOGGER.warning("La fecha requerida es NULL.");
                                resultado = Boolean.FALSE;
                                esCampoNull = Boolean.TRUE;
                            }
                            
                            if(null == row.getAttribute("TcaFormaTransferencia")){
                                LOGGER.warning("La TcaFormaTransferencia requerida es NULL.");
                                resultado = Boolean.FALSE;
                                esCampoNull = Boolean.TRUE;
                            }else{
                                Integer formaTransferencia = null;
                                
                                try{
                                    formaTransferencia = (Integer) row.getAttribute("TcaFormaTransferencia");
                                }catch(Exception e){
                                    LOGGER.warning("ERROR a recuperar la formaTransferencia.", e);
                                    resultado = Boolean.FALSE;
                                }
                                
                                if(null != formaTransferencia){
                                    if(formaTransferencia.compareTo(1)==0){
                                        if(null == row.getAttribute("NumeroCuentaClientePasivo")){
                                            LOGGER.warning("El numeroCuentaCliente requerido es NULL.");
                                            resultado = Boolean.FALSE;
                                            esCampoNull = Boolean.TRUE;
                                        }
                                    }
                                    
                                    if(formaTransferencia.compareTo(2)==0){
                                        if(null == row.getAttribute("NumeroCuentaNostro")){
                                            LOGGER.warning("El numeroCuentaBCIE es NULL.");
                                            resultado = Boolean.FALSE;
                                            esCampoNull = Boolean.TRUE;
                                        }
                                    }
                                }else{
                                    LOGGER.warning("formaTransferencia es NULL.");
                                    resultado = Boolean.FALSE;
                                }
                            }
                        }else{
                            LOGGER.warning("EL montoDesembolsar es NULL o cero (0).");
                        }
                    }else{
                        LOGGER.warning("EL row es NULL.");
                        resultado = Boolean.FALSE;
                    }
                }
            }else{
                LOGGER.warning("El iterador es NULL.");
                resultado = Boolean.FALSE;
            }
            iter.closeRowSetIterator();
        }else{
            LOGGER.warning("Objeto DCIteratorBinding de FuentesExternas es NULL.");
            resultado = Boolean.FALSE;
        }
        
        if(!resultado){
            if(esCampoNull){
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CAMPOS_OBLIGATORIOS"));
            }else{
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_DATOS_GESTION"));
            }
        }
        
        LOGGER.warning("resultado: " + resultado);       
        LOGGER.warning("Termina metodo validarCamposRequeridosTransferenciaRecursos");
        return resultado;
    }
    
    public void mostrarErroresReglas(ActionEvent actionEvent){
        LOGGER.warning("Inicia metodo mostrarErroresReglas.");
        List<String> listaErrores = new ArrayList<String>();
        DesembolsoBean desembolsoBean = getDesembolsoBean();
        
        if(null != desembolsoBean.getEsErrorProgramacion()){
            if(desembolsoBean.getEsErrorProgramacion()){
                LOGGER.warning("Es Error de programacion, mostrando POPUP.");
                desembolsoBean.setEsErrorProgramacion(Boolean.FALSE);
                mostrarPopupProgramacion();
            }else{
                LOGGER.warning("No es Error de programacion, mostrando lista de errores.");
                listaErrores = desembolsoBean.getListaErrores();
                
                if(null != listaErrores && listaErrores.size() > 0){
                    LOGGER.warning("Recuperando la lista de errores de validaciones.");
                    for(String mensaje : listaErrores){
                        JSFUtils.addFacesErrorMessage(mensaje);
                    }
                }else{
                    LOGGER.warning("Mostrando popup de Finalizacion de tarea.");
                    finalizarTareaPopup();
                }
            }
        }else{
            LOGGER.warning("EsErrorProgramacion es NULL. Mostrando popup de Finalizacion de tarea.");
            finalizarTareaPopup();
        }
        
        try{
            getAlertaMontosPopUpIUC().hide();
        }catch(Exception e){
            LOGGER.warning("ERROR al cerrar el POPUP de Montos.");
        }
        
        try{
            getAlertaProgramacionUIC().hide();
        }catch(Exception e){
            LOGGER.warning("ERROR al cerrar el POPUP de Programacion.");
        }
        LOGGER.warning("Termina metodo mostrarErroresReglas.");
    }
    
    
    public Boolean validarMontoDisponibleTransferir(){
     LOGGER.warning("*Inf, Inicia metodo validarMontoDisponibleTransferir"); 
     Boolean respuesta = Boolean.FALSE;
     BigDecimal montoDisponibleTransferir = null;
        
    String origenTransferencia=  
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("origenTransferencia").toString();  
        
        if(origenTransferencia.equals("DIRECTO_CLIENTE"))
              return Boolean.TRUE;
            
            
        String montoSession =
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("montoDisponibleTransferir").toString();
           
        LOGGER.warning("*Inf, Monto session: "+montoSession);
        
        if(montoSession.equals("") || montoSession.isEmpty() ){
            LOGGER.warning("****Error, No se pudo recuperar el disponible a transferir de la session"); 
            JSFUtils.addFacesErrorMessage("***Error en transferencias no se puedo validar el monto disponible a transferir");
        }else{
            try{
                  montoDisponibleTransferir = new BigDecimal(montoSession);
            }catch(Exception e){
                LOGGER.warning("***Error, al castear monto disponible a transferir ->",e); 
                JSFUtils.addFacesErrorMessage("Error en transferencias no se puedo validar el monto disponible a transferir");
            }   
                if(montoDisponibleTransferir != null){
                       
                        if(montoDisponibleTransferir.compareTo(BigDecimal.ZERO) == 0){
                            respuesta = Boolean.TRUE;
                            LOGGER.warning("*Inf, ok el monto disponible a transferir es cero"); 
                        }else{
                            LOGGER.warning("*Inf, montoDisponibleTransferir: "+montoDisponibleTransferir); 
                            JSFUtils.addFacesErrorMessage("No es posible finalizar la tarea, aun hay un monto disponible a transferir de "
                                                                                   +montoDisponibleTransferir +" en tranferencias");
                        }
                    
                }else{
                    LOGGER.warning("***Error, monto disponible a trasferir es null"); 
                    JSFUtils.addFacesErrorMessage("Error en transferencias no se puedo validar el monto disponible a transferir");
                }
        }
                                
     LOGGER.warning("*Inf, termina metodo validarMontoDisponibleTransferir");   
     return respuesta;
    }
    
    public Boolean commitDatosAsignacion(){
      LOGGER.warning("*Inf, inicia metodo commitDatosAsignacion");     
      Boolean respuesta = Boolean.FALSE;   
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
       
        try{
           operationBinding = bindings.getOperationBinding("comitDatosContrato");
           operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                   
            }else{
                respuesta = (Boolean)operationBinding.getResult();
            }
            
        }catch(Exception e){            
            LOGGER.warning("*Inf, Error al realizar commit ->", e);     
            JSFUtils.addFacesErrorMessage("Ha ocurrido un error al guardar la informacion de asignacion de recursos ->"+e.getMessage());
        }
                
      LOGGER.warning("*Inf, termina metodo commitDatosAsignacion");
      return respuesta;
    }
    
    
    public void disclosureListenerCargarDetalleOperacion(DisclosureEvent disclosureEvent) {
        DesembolsoBean desembolsoBean =
            (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        desembolsoBean.setCargarRegionDetOpe(Integer.valueOf(2));
        desembolsoBean.renderRegionDetalleOperacion();
    }
    
    public void disclosureListenerCargarGestorDocumentos(DisclosureEvent disclosureEvent) {
        DesembolsoBean desembolsoBean =
            (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        desembolsoBean.setCargarRegionGesDoc(Integer.valueOf(2));
        desembolsoBean.renderRegionGestorDocumentos();
    }
    
    public void disclosureListenerCargarMatrizTcc(DisclosureEvent disclosureEvent) {
        DesembolsoBean desembolsoBean =
            (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        desembolsoBean.setCargarRegionMatTcc(Integer.valueOf(2));
        desembolsoBean.renderRegionMatrizTcc();
    }
    
    public void disclosureListenerCargarComentarios(DisclosureEvent disclosureEvent) {
        DesembolsoBean desembolsoBean =
            (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        desembolsoBean.setCargarRegionComent(Integer.valueOf(2));
        desembolsoBean.renderRegionComentarios();
    }
    

    private Row obtenerDatosContrato() {
        LOGGER.warning("Inside obtenerDatosContrato.");
        
        Row row = null;
        
        DesembolsoBean desembolsoBean = (DesembolsoBean) JSFUtils.resolveExpression("#{pageFlowScope.desembolsoManagedBean}");
        Long idContrato = Long.valueOf( desembolsoBean.getIdDesembolso() );
        
        LOGGER.warning("idContrato: " + idContrato);
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerContratoPorId");
        operationBinding.getParamsMap().put("idContrato", idContrato);
        operationBinding.execute();
        
        row = (Row) operationBinding.getResult();
        
        return row;
    }
    
    public void setAlertaMontosPopUpIUC(RichPopup alertaMontosPopUpIUC) {
        this.alertaMontosPopUpIUC = alertaMontosPopUpIUC;
    }

    public RichPopup getAlertaMontosPopUpIUC() {
        return alertaMontosPopUpIUC;
    }

    public void setListaEvidencias(RichListView listaEvidencias) {
        this.listaEvidencias = listaEvidencias;
    }

    public RichListView getListaEvidencias() {
        return listaEvidencias;
    }

    public void setAlertaProgramacionUIC(RichPopup alertaProgramacionUIC) {
        this.alertaProgramacionUIC = alertaProgramacionUIC;
    }

    public RichPopup getAlertaProgramacionUIC() {
        return alertaProgramacionUIC;
    }

    public void setDetalleOperacionUIC(RichRegion detalleOperacionUIC) {
        this.detalleOperacionUIC = detalleOperacionUIC;
    }

    public RichRegion getDetalleOperacionUIC() {
        return detalleOperacionUIC;
    }

    public void setGestorDocumentosUIC(RichRegion gestorDocumentosUIC) {
        this.gestorDocumentosUIC = gestorDocumentosUIC;
    }

    public RichRegion getGestorDocumentosUIC() {
        return gestorDocumentosUIC;
    }

    public void setMatrizTccUIC(RichRegion matrizTccUIC) {
        this.matrizTccUIC = matrizTccUIC;
    }

    public RichRegion getMatrizTccUIC() {
        return matrizTccUIC;
    }

    public void setComentariosUIC(RichRegion comentariosUIC) {
        this.comentariosUIC = comentariosUIC;
    }

    public RichRegion getComentariosUIC() {
        return comentariosUIC;
    }
    
    public void actualizarCondicionesFinancieras() {
        LOGGER.warning("Inside actualizarCondicionesFinancieras.");
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("guardarCondicionesFinancieras");
        operationBinding.execute();
    }
    
    private void actualizarFlagPayload(String variable, Object valor) {
            AttributeBinding attributeBinding = null;
            
            attributeBinding = ADFUtils.findControlBinding(variable);
            attributeBinding.setInputValue(valor);
        }
    
    public void setListaAdvertencias(List<String> listaAdvertencias) {
        this.listaAdvertencias = listaAdvertencias;
    }

    public List<String> getListaAdvertencias() {
        return listaAdvertencias;
    }
    
    //========== SPS | 07/01/2020 | Metodos de utilerias para la validacion de CA  ============= //
    /**
     * Método empleado para la validacion de los componentes de Clasificacion Ambiental al finalizar la tarea.
     * @author : S&P Solutions
     * @param  : 
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 05/12/2019
     */
    public boolean validacionesCA() {
        boolean banError = true;
        if(esEditable()){
            int cont = obtenerNoComponentes();
            LOGGER.log(ADFLogger.WARNING, "validacionesCA - #Componentes:"+cont);
            erroresCA = new ArrayList<String>();
            erroresCA.clear();
            for (int x = 1; x <=cont; x++) {
                obtenerValoresComponentes(x);
            }
            
            if (!erroresCA.isEmpty()){
                banError = false;
              
            } 
        }
       
    return banError;
    }
    /**
     * Método auxiliar empleado para validar si la seccion de Clasificacion Ambiental es editable o no.
     * @author : S&P Solutions
     * @param  : 
     * @return : boolean
     * @version: v1.0
     * @Fecha  : 06/01/2020
     */
    public boolean esEditable(){
        boolean editable = true;
        UIComponent ui =  obtenerComponente(ID_LBL_SECTOR);
        if(ui!=null){
            editable = false;
        }
        
        LOGGER.log(ADFLogger.WARNING, "esEditable:"+editable);
        return editable;
    } 
    /**
     * Método auxiliar empleado para obtener el # de elementos en la seccion de Clasificacion Ambiental.
     * @author : S&P Solutions
     * @param  : 
     * @return : int
     * @version: v1.0
     * @Fecha  : 02/01/2020
     */
    public int obtenerNoComponentes() {
        UIComponent panelPadre = obtenerComponente(ID_PANELCMP);
        List<UIComponent> hijos = panelPadre.getChildren();
        LOGGER.log(ADFLogger.WARNING, "obtenerNoComponentes | hijos:"+hijos.size());
        int cont = 0;
        for(UIComponent ui : hijos){
            if(ui.getId().startsWith(ID_PANELPADRE)){
                cont++;
            }
        }
        
        LOGGER.log(ADFLogger.WARNING, "panelPadre | cont:"+cont);
        return cont;
    }

    /**
     * Método que busca y retorna un componente dado su id.
     * @author : S&P Solutions
     * @param  : String
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 29/06/2019
     */
    public UIComponent obtenerComponente(String id) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        UIComponent ui = findComponent(facesCtx.getViewRoot(), id);
        return ui;
    }

    /**
     * Método que busca y retorna un componente dado un componente base y el id del componente a encontrar.
     * @author :
     * @param  : UIComponent
     * @param  : String
     * @return : UIComponent
     * @version: v1.0
     * @Fecha  : 29/06/2019
     */
    private UIComponent findComponent(UIComponent base, String id) {

        if (id.equals(base.getId())) {
            return base;
        }

        UIComponent children = null;
        UIComponent result = null;
        Iterator childrens = base.getFacetsAndChildren();
        while (childrens.hasNext() && (result == null)) {
            children = (UIComponent) childrens.next();
            if (id.equals(children.getId())) {
                result = children;
                break;
            }
            result = findComponent(children, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }
    
    /**
     * Método que obtiene los valores de cada componente de los formularios dinámicos.
     * @author : S&P Solutions
     * @param  : int index
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public Boolean obtenerValoresComponentes(int index) {
        Boolean esDatosCorrectos = Boolean.TRUE;
       // logger.warning("\n=======  obtenerValoresComponentes index:" + index);
        //Sector
        String sector = getValorTxtSector(obtenerComponente(ID_PANELCMP), index);
        if (sector.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
            //JSFUtils.addFacesErrorMessage("El campo Sector es requerido.");
            erroresCA.add("El campo Sector es requerido.");
        } 
        //Aporte
        String aporte = getValorTxtAporte(obtenerComponente(ID_PANELCMP), index);
        if (aporte.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
            //JSFUtils.addFacesErrorMessage("El campo Aporte es requerido.");
            erroresCA.add("El campo Aporte es requerido.");
        }
        //Categoria
        String categoria = getValorTxtCategoria(obtenerComponente(ID_PANELCMP), index);
        if (categoria.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
          // JSFUtils.addFacesErrorMessage("El campo Categoría es requerido.");
          erroresCA.add("El campo Categoría es requerido.");
        } 
        //SubCategoria
        String subcategoria = getValorTxtSubCategoria(obtenerComponente(ID_PANELCMP), index);
        if (subcategoria.equals("-1")) {
            esDatosCorrectos = Boolean.FALSE;
         //   JSFUtils.addFacesErrorMessage("El campo Subcategoría es requerido.");
         erroresCA.add("El campo Subcategoría es requerido.");
        } 
        
        return esDatosCorrectos;
       
    }
    
    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtSector(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_SECTOR + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("\n===  getValorTxtSector  | index:" + index + "| SECTOR value:" + valor);
        return valor;
    }

    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtAporte(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_APORTE + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("\n===  getValorTxtAporte  | index:" + index + "| APORTE value:" + valor);
        return valor;

    }

    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtCategoria(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_CATEGORIA + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("\n===  getValorTxtCategoria  | index:" + index + "| CATEGORIA value:" + valor);
        return valor;
    }


    /**
     * Método que obtiene el valor del componente Sector Ambiental.
     * @author : S&P Solutions
     * @param  : UIComponent parentUIComponent, int index
     * @return : String
     * @version: v1.0
     * @Fecha  : 24/09/2019
     */
    public String getValorTxtSubCategoria(UIComponent parentUIComponent, int index) {
        UIComponent ui = (RichSelectOneChoice) parentUIComponent.findComponent(ID_SUBCATEGORIA + index);
        String valor = "-1";
        valor =
            ui.getAttributes().get("value") != null ?
            (ui.getAttributes().get("value").toString().trim().isEmpty() ? "-1" :
             (ui.getAttributes().get("value").toString().startsWith("0") ? "-1" :
              ui.getAttributes().get("value").toString())) : "-1";
        //logger.warning("\n===  getValorTxtSubCategoria  | index:" + index + "| SUBCATEGORIA value:" + valor);
        return valor;
    }
    
    
    public void setErroresCA(ArrayList<String> erroresCA) {
        this.erroresCA = erroresCA;
    }

    public ArrayList<String> getErroresCA() {
        return erroresCA;
    }
    //========== SPS | 07/01/2020 | Fin metodos ============= //
    
    public String ValidarCuentaClienteNoNull(){
            LOGGER.warning("Inicio ValidarCuentaClienteNoNull");
            boolean Resultado =Boolean.TRUE;
            String cuentaCliente;
            cuentaCliente = (null == JSFUtils.resolveExpression("#{bindings.CuentaCliente.inputValue}")) ? null : (String) JSFUtils.resolveExpression("#{bindings.CuentaCliente.inputValue}");
            LOGGER.warning("Cuenta Cliente: "+cuentaCliente);
         
            LOGGER.warning("Final ValidarCuentaClienteNoNull");
            return cuentaCliente;
        } 
}
