package pc04aprobaciongenerichumantask.bean;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichListView;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.common.StringManager;
import oracle.jbo.domain.Array;
import oracle.jbo.domain.Number;
import oracle.jbo.domain.Timestamp;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.bcie.correobo.Param;
import org.bcie.correobo.Usuario;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.dto.CorreoElectronicoDTO;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.vo.SolicitudAprobacionVORowImpl;
import org.bcie.fenix.common.model.vo.UsuarioReunionAprobacionVORowImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.common.view.beans.FenixActionBean.OperationExecuteException;

import pc04aprobaciongenerichumantask.dto.MiembroNotificacionDTO;

/**
 * Managed Bean para acciones del proceso de Aprobacion de Solicitud
 *
 * @author Latbc.
 */
public class AprobacionActionBean extends FenixActionBean{
    
    /**
     * Log de la aplicacion
     */
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(AprobacionActionBean.class);

    /**
     * Define paquete y nombre de Resource Bundle
     */
    public static final String BUNDLE = "pc04aprobaciongenerichumantask.PC04AprobacionGenericHumanTaskBundle";

    /**
     * Define nombre del atributo Bindings para Tipo Reunion
     */
    public static final String CVE_TIPO_REUNION_ATRIBUTO = "idTipoReunionSelectedVar";
    
    /**
     * Define nombre del atributo Bindings para Id de Accion a Seguir
     */
    public static final String ID_ACCION_A_SEGUIR_ATRIBUTO = "IdTipoAccionVarAttr";
    
    /**
     * Define nombre del atributo Bindings para Codigo de Accion a Seguir
     */
    public static final String CODIGO_ACCION_A_SEGUIR_ATRIBUTO = "CodigoTipoAccionVarAttr";
    
    /**
     * Define nombre del atributo Bindings para Id de nivel de aprobacion
     */
    public static final String ID_NIVEL_APROB_ATRIBUTO = "idNivelAprobacion";

    /**
     * Define clave de Bundle para la conversion de Hora a Objeto Timestamp
     */
    public static final String MSG_ERROR_HORA_CONVERT_BKEY =
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_convertir_hora";

    /**
     * Define el mesaje de error de Numero de Resolucion
     */
    public static final String MSG_ERROR_AGREGAR_NUMERO_RESOLUCION="MSG_ERROR_AGREGAR_NUMERO_RESOLUCION";
    
    /**
     * Define el mesaje de error de Lineas de credito
     */
    public static final String MSG_ERROR_LINEAS_CREDITO="MSG_ERROR_LINEAS_CREDITO";

    /**
     * Define el mesaje de error de Fecha de Aprobacion.
     */
    public static final String MSG_ERROR_AGREGAR_FECHA_APROBACION="MSG_ERROR_AGREGAR_FECHA_APROBACION";
        
    /**
     * Define clave de Bundle para indicar valor vacio en el campo Hora
     */
    public static final String MSG_ERROR_HORA_VACIA_BKEY =
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_hora_vacia";

    /**
     * Define clave de Bundle para indicar valor vacio en el campo Fecha
     */
    public static final String MSG_ERROR_FECHA_VACIA_BKEY =
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_fecha_vacia";

    /**
     * Define clave de Bundle para indicar valor vacio en el campo Lugar de Reunion
     */
    public static final String MSG_ERROR_LUGAR_VACIA_BKEY =
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_lugar_vacio";

    /**
     * Define clave de Bundle para indicar valor vacio en el campo Fecha de apertura
     */
    public static final String MSG_ERROR_FECHA_INICIO_BKEY =
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_fecha_inicio_vacio";

    /**
     * Define clave de Bundle para indicar valor vacio en el campo Fecha de cierre
     */
    public static final String MSG_ERROR_FECHA_CIERRE_BKEY =
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_fecha_cierre_vacio";

    /**
     * Define clave de Bundle para indicar falta de seleccion del tipo de reunion
     */
    public static final String MSG_ERROR_TIPO_REUNION_BKEY =
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_tipo_reunion_vacio";
    
    /**
     * Define clave de Bundle para indicar fallo en la creacion de la solicitud de aprobacion
     */
    public static final String MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY = 
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_creacion_solicitud";
    
    /**
     * Define clave de Bundle para indicar la creacion exitosa de la solicitud de aprobacion
     */
    public static final String MSG_INFO_CREAR_SOLICITUD_EXITO_BKEY = 
        "pc04aprobacion_revision_solicitud_comite_credito_msg_info_creacion_solicitud";
    
    /**
     * Define clave de Bundle para indicar que la fecha de cierre fue guardada
     */
    public static final String MSG_INFO_FECHA_CIERRE_GUARDADA_BKEY = 
        "pc04aprobacion_dar_seguimiento_votacion_fecha_cierre_guardada";
    
    /**
     * Define clave de Bundle para indicar falta de seleccion de la accion a seguir
     */
    public static final String MSG_ERROR_TIPO_ACCION_EMPTY_BKEY = 
        "pc04aprobacion_consolidar_decision_msg_error_tipo_accion_vacio";
    
    /**
     * Define clave de Bundle para indicar que no se obtuvo el identificador de tipo de reunion
     */
    public static final String MSG_ERROR_TIPO_REUNION_EMPTY_BKEY = 
        "pc04aprobacion_consolidar_decision_msg_error_tipo_reunion_vacio";
    
    /**
     * Define clave de Bundle para indicar la captura vacia en el campo numero de acuerdo
     */
    public static final String MSG_ERROR_NUMERO_ACUERDO_EMPTY_BKEY = 
        "pc04aprobacion_consolidar_decision_msg_error_numero_acuerdo_vacio";
    
    /**
     * Define clave de Bundle para indicar la captura vacia en el campo numero de acta
     */
    public static final String MSG_ERROR_NUMERO_ACTA_EMPTY_BKEY = 
        "pc04aprobacion_consolidar_decision_msg_error_numero_acta_vacio";
    
    /**
     * Define clave de Bundle para indicar la captura vacia en el campo acuerdo
     */
    public static final String MSG_ERROR_ACUERDO_EMPTY_BKEY = 
        "pc04aprobacion_consolidar_decision_msg_error_acuerdo_vacio";

    /**
     * Define clave de Bundle para indicar error en la ejecucion de la operacion de bindings
     */
    public static final String MSG_ERROR_OPER_BINDINGS =
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_operacion_execucion";
    
    /**
     * Define clave de Bundle para indicar fallo al guardar registro de Consolidar Decision
     */
    public static final String MSG_ERROR_GUARDAR_CONSOLIDAR_DECISION_BKEY = 
        "pc04aprobacion_consolidar_decision_msg_error_guardar";
    
    /**
     * Define clave de Bundle para indicar fechas fuera de rango, para fecha de apertura y cierre
     */
    public static final String MSG_ERROR_FECHA_FUERA_RANGO_BKEY = 
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_fecha_fuera_rango";
    
    /**
     * Define clave de Bundle para indicar fecha de inicio invalida para reunion presencial
     */
    public static final String MSG_ERROR_FECHA_INICIO_PRESENCIAL_INVALIDA_BKEY = 
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_fecha_inicio_presencial_invalida";
    
    /**
     * Define clave de Bundle para indicar fecha de inicio invalida para reunion virtual
     */
    public static final String MSG_ERROR_FECHA_INICIO_VIRTUAL_INVALIDA_BKEY = 
        "pc04aprobacion_revision_solicitud_comite_credito_msg_error_fecha_inicio_virtual_invalida";
    
    /**
     * Define clave de Bundle para indicar que no existe una decision de la accion a seguir en la solicitud de aprobacion
     */
    public static final String MGS_ERROR_DECISION_VACIA_BKEY = 
        "pc04aprobacion_dar_seguimiento_solicitud_presidencia_error_msg_decision_vacia";
    
    /**
     * Define clave de Bundle para indicar que no se ha capturado el campo de Observacion
     */
    public static final String MSG_ERROR_OBSERVACION_VACIA_BKEY = 
        "pc04aprobacion_dar_seguimiento_solicitud_presidencia_error_msg_observacion_vacia";
    
    /**
     * Define clave de Bundle para indicar que hubo fallo al validar la existencia de decisiones de presidente
     */
    public static final String MSG_ERROR_VALID_DECISIONES_FALLO_BKEY = "pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_validacion_decisiones";
    
    /**
     * Define clave de Bundle para indica que falta capturar decisiones de presidente
     */
    public static final String MSG_ERROR_VALID_DECISIONES_VACIO_BKEY = "pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_validacion_decisiones_vacia";
    
    /**
     * Define clave de Bundle para indicar que hubo fallo al validar la existencia de votos de comite de credito
     */
    public static final String MSG_ERROR_VALID_VOTOS_FALLO_BKEY = "pc04aprobacion_dar_seguimiento_votacion_msg_error_validacion_votos";
    
    /**
     * Define clave de Bundle para indica que falta capturar decisiones de votos de comite de credito
     */
    public static final String MSG_ERROR_VALID_VOTOS_VACIO_BKEY = "pc04aprobacion_dar_seguimiento_votacion_msg_error_validacion_votos_vacio";
    
    /**
     * Define clave de Bundle para indicar error al guardar la fecha de cierre de la solicitud de aprobacion
     */
    public static final String MSG_ERROR_GUARDAR_FECHA_CIERRE = 
                           "pc04aprobacion_dar_seguimiento_msg_error_guardar_fecha_cierre";
    
    /**
     * Define clave de Bundle para indicar error al actualizar la accion a seguir a negativa
     */
    public static final String MSG_ERROR_GUARDAR_ACCION_NEGATIVA = 
        "pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_actualizar_accion_negativa";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando el ultimo voto es negativo y se requiere comentario
     */
    public static final String MSG_ERROR_COMENTARIO_REQUERIDO_VOTO_NEGATIVO = 
        "pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_comentario_requerido";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando se requiere un comentario de operacion al Revisar
     * la Solicitud de Presidente
     */
    public static final String MSG_ERROR_COMENTARIO_OPER_REQUERIDO = 
        "pc04aprobacion_revisar_solicitud_presidencia_msg_error_comentario_operacion_requerido";
    
    public static final String MSG_ERROR_CONSOLIDARDECISIONDIRECTORIO_COMENTARIO = 
        "pc04aprobacion_revisar_solicitud_presidencia_msg_error_comentario_consolidar_directorio";
    
    public static final String MSG_ERROR_REVISARSOLICITUDPRESIDENTE_COMENTARIO = 
        "pc04aprobacion_revisar_solicitud_presidencia_msg_error_comentario_revisar_presidente";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando la validacion de comentario de operacion contiene
     * errores
     */
    public static final String MSG_ERROR_COMENTARIO_OPER_FALLO = 
        "pc04aprobacion_revisar_solicitud_presidencia_msg_error_comentario_operacion_validacion_fallo";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando la operacion de validacion de comentario de operacion
     * no es encontrada en el page definitions.
     */
    public static final String MSG_ERROR_COMENTARIO_OPER_NO_ENCONTRADO = 
        "pc04aprobacion_revisar_solicitud_presidencia_msg_error_comentario_operacion_no_encontrado";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando la suma de los montos de lineas de credito sobrepasa
     * el monto aprobado
     */
    public static final String MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS = 
        "pc04aprobacion_consolidar_decision_directorio_msg_error_validacion_monto_total";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando la operacion de validacion de la suma de los montos de
     * linea de credito tiene error
     */
    public static final String MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS_FALLO_OPER = 
        "pc04aprobacion_msg_error_validacion_monto_total_con_Fallo";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando el monto de aprobacion es NULL
     */
    public static final String MSG_ERROR_VALIDAR_MONTO_TOTAL_MONTO_APROB_NULL = 
        "pc04aprobacion_consolidar_decision_directorio_msg_error_validacion_monto_aprobacion_vacio";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error al intentar obtener el valor del indicador de decision del 
     * voto de presidente o del miembro de aprobacion
     */
    public static final String MSG_ERROR_ID_VOTO_DECISION_VACIO = 
        "pc04aprobacion_certificar_decision_msg_error_indicador_voto_decision_vacio";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error al leer el valor de identificador de decision de voto
     * del presidente o miembro de aprobacion 
     */
    public static final String MSG_ERROR_LECTURA_VOTO_DECISION = 
        "pc04aprobacion_certificar_decision_msg_error_indicador_voto_decision_lectura";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando la fecha de aprobacion no fue capturada
     */
    public static final String MSG_ERROR_FECHA_APROBACION_VACIO = 
        "pc04aprobacion_certificar_decision_msg_error_fecha_aprobacion_vacia";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando el monto de aprobacion no fue capturado
     */
    public static final String MSG_ERROR_MONTO_APROBACION_VACIO = 
        "pc04aprobacion_msg_error_validacion_monto_aprobacion_vacio";
    
	/**
     * Define clave de Bundle para mostrar mensaje de error cuando el monto de aprobacion no fue capturado
     */
    public static final String MSG_ERROR_TIPO_MONEDA_APROBACION_VACIO = 
        "pc04aprobacion_msg_error_validacion_tipo_moneda_aprobacion_vacio";
	
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando existen montos invalidos en las lineas de credito
     */
    public static final String MSG_ERROR_VALIDA_LC_MONTOS_INVALIDOS = "pc04aprobacion_msg_error_validacion_monto_valor_invalido";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando existen datos obligatorios vacios
     */
    public static final String MSG_ERROR_VALIDA_DATOS_OBLIGATORIOS = "pc04consolidar_decision_comite_credito_msg_datosObligatorios";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error cuando existen miembros sin usuarios aignados
     */
    public static final String MSG_ERROR_MIEMBRO_SIN_USUARIO = "MSG_ERROR_MIEMBRO_SIN_USUARIO";
    
    /**
     * Define nombre del Operator Bindings para Crear Solicitud de Aprobacion por medio del Modelo
     */
    public static final String CREAR_SOLICITUD_OPER = "crearSolicitudAprobacionWS";
    
    /**
     * Define nombre de Operator Bindings para Guardar la fecha de cierre al finalizar la tarea de Dar
     * Seguimiento Votacion o Dar Seguimiento a Decision Presidente
     */
    public static final String GUARDAR_FECHA_CIERRE_OPER = "guardarFechaCierreSolicitudAprob";
    
    /**
     * Define nombre del Operator Bindings para guardar los datos de Consolidar Decision por medio del Modelo
     */
    public static final String GUARDAR_CONSOLIDAR_DECISION_OPER = "guardarConsolidarDecision";
    
    /**
     * Define nombre del Operator Bindings para actualizar los datos de Consolidar Decision por medio del Modelo
     */
    public static final String ACTUALIZAR_CONSOLIDAR_DECISION_OPER = "actualizarDecisionReunion";
    
    /**
     * Define nombre del Operator Bindings para guardar observacion en la decision de aprobacion para 
     * presidencia
     */
    public static final String GUARDAR_OBSERVACION_PRESIDENTE = "guardarObservacionPresidente";
    
    /**
     * Define nombre del Operator Bindings para verificar si el ultimo voto de presidente es positivo o negativo
     */
    public static final String ES_POSITIVO_VOTO_PRESI_OPER = "esPositivoUltimoVotoPresidente";
    
    /**
     * Define nombre del Operator Bindings para actualizar el registro de decision a una accion a seguir negativa
     */
    public static final String ACTUALIZAR_DECISION_ACCION_NEGATIVA_OPER = "actualizarDecisionAprobAccionNegativa";
    
    /**
     * Define nombre del Operator Bindings para validar la existencia de comentario de operacion por usuario en session
     * y en fecha actual
     */
    public static final String VALIDAR_COMENTARIO_OPERACION_FH_ACTUAL_OPER = "valComentarioOperUsuFchActual";
    
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String CREAR_LINEA_OPER = "agregarLineaAprobacion";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String MODIFICAR_LINEA_OPER = "modificarLineaAprobacion";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String ELIMINAR_LINEA_OPER = "eliminarLineaAprobacion";
    /*Define nombre del Iterador para obtener los datos de Linea Aprobacion asociadas a una table y el Modelo*/
    public static final String LINEA_TABLE_ITERATOR ="RegistrarLineaCreditoAprobacionVOIterator";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String MODIFICAR_APROBACION_OPER = "modificarAprobacion";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String CREAR_APROBACION_OPER = "generarAprobacion";
    /*Define nombre del Iterador para obtener los datos de Linea Aprobacion asociadas a una table y el Modelo*/
    public static final String DATOS_APROBACION_ITERATOR ="RegistrarDatosAprobacionVOIterator";
    /*Define nombre del Iterador para obtener los datos de Linea Aprobacion asociadas a una table y el Modelo*/
    public static final String CONDICION_TABLE_ITERATOR = "RegistrarCondicionAprobacionVOIterator";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String CREAR_CONDICION_CAP_OPER = "crearCondicionAprobacionCaptura";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String CANCELAR_CONDICION_CAP_OPER = "cancelarCondicionAprobacionCaptura";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String CANCELAR_CONDICION_ED_OPER = "cancelarCondicionAprobacionEdicion";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String ACEPTAR_AGREGAR_MODIFICAR_CONDICION_OPER = "aceptarCondicionAprobacionAgregadaModificada";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
    public static final String ACEPTAR_ELIMINAR_CONDICION_OPER = "eliminarCondicionAprobacion";
    /*Define nombre del Operator Bindings para guardar los datos de Linea Aprobacion por medio del Modelo*/
     public static final String CREAR_ACTUALIZAR_CONDICION_OPER = "crearActualizarCondicionAprobacion";  
    /*Define nombre del Operator Bindings para Crear Solicitud de Aprobacion por medio del Modelo*/
     public static final String CREAR_SOLICITUD_APROBACION_CLIENTE_OPERACION = "crearSolicitudAprobacionCliente";
    
    /* Define el atributo del pageDef para almacenar el tipo moneda de líneas de crédito.*/
    public static final String TIPO_MONEDA_LC_ATTR = "TipoMonedaLC1";
    
    public final static String ES_OTRO_VALUE = "org.bcie.fenix.common.model.vo.MiembrosAprobacionVO.EsOtro.Value";
    public final static String ES_EXCUSADO_VALUE = "org.bcie.fenix.common.model.vo.MiembrosAprobacionVO.EsExcusado.Value";
    
    public final static Integer ID_ROL_JEFE_ASJUR = 30;
    
    public final static String MAS_INFORMACION = "MASINFORMACION";
    
    /**
     * Define Id para tipo reunion presencial
     */
    public static final Number ID_REUNION_PRESENCIAL = new Number(1);
    
    /**
     * Define Id para tipo reunion virtual
     */
    public static final Number ID_REUNION_VIRTUAL = new Number(2);
    
    /**
     * Define Id para accion retornar de Consolidar Decision (Comite de credito)
     */
    public static final Integer ID_ACCION_RETORNAR = 2;
    
    /**
     * Define Id de voto positivo del presidente
     */
    public static final Integer ID_VOTO_DECISION_POSITIVA_PRESIDENTE = 1;
    
    /**
     * Define codigo de la accion retornar de Consolidar Decision (Comite de credito)
     */
    public static final String CODIGO_ACCION_RETORNAR = "RETORNAR";
    
    /**
     * Define codigo de la accion a enviar a Directorio de Dar Seguimiento a Decision de Presidente
     */
    public static final String CODIGO_ACCION_DIRECTORIO = "EVALUAR_DIRECTORIO";
    
    /**
     * Define codigo de la accion a Aprobar de Consolidar Decision Directorio
     */
    public static final String CODIGO_ACCION_APROBAR_CONDECDIR = "APROBAR";
    
    /**
     * Define clave de Bundle para indicar error en la validacion al finalizar tarea
     */
    public static final String MSG_ERROR_COMPLETAR_INFORMACION ="MSG_ERROR_COMPLETAR_INFORMACION";
    
    /**
     * Define clave de Bundle para indicar error en la validacion al finalizar tarea
     */
    public static final String MSG_ERROR_TD_PROYECTO_RESOLUCION = "MSG_ERROR_TD_PROYECTO_RESOLUCION";
    
    /**
     * Define clave de Bundle para indicar error en la validacion al finalizar tarea
     */
    public static final String MSG_ERROR_AGREGAR_COMENTARIO ="MSG_ERROR_AGREGAR_COMENTARIO";

    private RichPopup popupAgregarModificarDecision;

    /**
     * Define clave de Bundle para indicar error en la validacion al finalizar tarea
     */
    public static final String MSG_ERROR_TD_RESPALDO_VOTO="MSG_ERROR_TD_RESPALDO_VOTO";
    
    public static final String MSG_ERROR_FECHA_INVALIDA="LBL_ERROR_FECHA_INVALIDA";
    
    public static final String MSG_ERROR_HORA_INVALIDA="LBL_ERROR_HORA_INVALIDA";
    
    public static final String MSG_ERROR_FECHA_SISTEMA="LBL_ERROR_FECHA_SISTEMA";
    
    /**
     * Define clave de Bundle para mostrar mensaje de error al actualizar datos del seguimiento de votacion
     */
    public static final String MSG_ERROR_ACTUALIZAR_SEGUIMIENTO_VOTACION_BKEY = 
        "pc04aprobacion_dar_seguimiento_votacion_msg_error_actualizar_datos";
    
    /**
     * Define nombre de variables de session para almacenar clave del tipo de reunion
     */
    private static final String CVE_TIPO_REUNION_VAR_SESSION = "PC04TipoReunion";
    
    /**
     * Define la clave del tipo de solicitud de aprobacion Operacion
     */
    private static final Integer TIPO_SOLICITUD_APROB_OPERACION = 1;
    
    /**
     * Define la clave del tipo de solicitud de aprobacion Enmienda
     */
    private static final Integer TIPO_SOLICITUD_APROB_ENMIENDA = 2;
    
    /**
     * Define clave de Bundle para obtener URL de Lotus Notes para visualizar el documento de Resolucion
     */
    private static final String URL_LOTUS_NOTE_RESOLUCION = "url_lotus_note_abrir_documento_resolucion";
    
    /**
     * Define clave de Bundle para obtener mensaje de error de validacion de existencia de documento scr
     */
    private static final String MSG_ERROR_DOCUMENTO_SCR = "pc04crear_solicitud_aprobacion_msg_err_validacion_documento";
    
    private static final String TAREA_DAR_SEGUIMIENTO_VOTACION = "182";
    
    private static final String TI_MEJORA_SCR = "MejoraSCR";

    private RichInputDate fechaRPresencial;
    private RichInputText horaRPresencial;
    private RichInputText lugarRPresencial;
    private RichInputDate fechaAperturaRVirtual;
    private RichInputDate fechaCierreRVirtual;

    private RichPopup popupAgregarModificarVoto;

    private RichPopup popupConfFinalizarTarea;
    private RichPopup popupConfMasInfo;
    private RichPopup popupAgregarModificarLeerCondicion;
    private RichPopup popupAgregarModificarLinea;
    private RichPopup popupConfirmacionEliminarLinea;
    private RichPopup popupConfirmacionEliminarCondicion;
    private RichPopup popupRequiereAED;
    
    private RichSelectOneChoice tipoReunionUIComponent;
    private RichPanelFormLayout panelFormPresencialUIC;
    private RichPanelFormLayout panelFormVirtualUIC;
    private RichPanelFormLayout panelFormDecisionUIC;
    private RichInputText numAcuerdoUIC;
    private RichInputText numActaUIC;
    private RichInputText acuerdoUIC;
    private RichOutputText numActaLabelUIC;
    private RichOutputText numAcuerdoLabelUIC;
    private RichListView listViewComentariosReunion;
    private RichTreeTable treeTableHistorico;
    
    private String numAcuerdoRenderProp;
    private String numActaRenderProp;
    
    private Date currentDate;
    private Date minFechaInicio;
    private Date minFechaCierre;
    private Date maxFechaApertura;
    private RichTable comiteTableUIC;
    
    private String fechaMinString;
	
	 /**Datos necesarios para la creacion de una linea*/
    private String numeroLinea;
    private String descripcionLinea;
    private BigDecimal montoLinea;
    private RichInputText observacionUIC;
    private RichInputDate fechaInicioRevPresidenciaUIC;
    private RichInputDate fechaCierreRevPresidenciaUIC;
    
    private String accionDecPreVisibleProp;
    private RichPanelFormLayout panelFormAccionPresidenteUIC;
    private RichTable condicionAprobacionTableUIC;
    
    private String urlLotusNoteResolucion;
    private RichInputText numResolucionUIC;
    private RichButton verDocumentoButtonUIC;
    
    private RichButton agregarComentarioRButtonUIC;
    
    private RichSelectManyChoice categoriaCondicionUIC;
    private RichSelectManyChoice eventosCondicionUIC;
    private RichMessages messagePopupUIC;
    private RichTable lineasCreditoTableUIC;
    private RichInputDate fechaTerminoUIC;
    private RichButton agregarCondicionButtonUIC;
    
    private RichSelectOneChoice tipoAccionDecisionUIC;
    
    /**
     * Contiene el numero de acuerdo de la decision de reunion para la solicitud de aprobacion
     */
    private String numAcuerdo;
    
    /**
     * Contiene el numero de acta de la decision de reunion para la solicitud de aprobacion
     */
    private String numActa;
    
    /**
     * Contiene la descripcion del acuerdo de la decision de reunion para la solicitud de aprobacion
     */
    private String acuerdo;
    
    /**
     * Contiene valor bandera para indicar en pantalla si el Grid de Matriz TCC debe visualizarse
     */
    private String matrizTccGridVisible;
    
    private String matrizTccTreeEscritura;

    private String matrizTccGridEscritura;
    
    private Long idEnmienda;
    private RichOutputText getInitForm;
    
    private Boolean msgComiteCredito = Boolean.FALSE;
    private Boolean msgConNotificacion = Boolean.FALSE;
    private Boolean msgEquipoTrabajo = Boolean.FALSE;
    
    // Plantillas de correo
    private static final int PLANTILLA_EMITIR_VOTO_NIVEL_PRESIDENCIA = 16;
    private static final int PLANTILLA_EMITIR_VOTO_NIVEL_COMITE_OPERACION = 13;
    private static final int PLANTILLA_EMITIR_VOTO_NIVEL_COMITE_CLIENTE = 85;

    private static final int PLANTILLA_CON_NOTIFICACION_SECRETARIO_PRESIDENCIA = 3;
    private static final int PLANTILLA_CON_NOTIFICACION_NO_SECRETARIO_PRESIDENCIA = 18;
    private static final int PLANTILLA_CON_NOTIFICACION_NIVEL_COMITE_OPERACION = 15;
    private static final int PLANTILLA_CON_NOTIFICACION_NIVEL_COMITE_CLIENTE = 87;

    private static final int NIVEL_APROBACION_PRESIDENCIA = 2;
    private static final int SECRETARIO_PRESIDENCIA = 18;
    
    public void setMsgComiteCredito(Boolean msgComiteCredito) {
        this.msgComiteCredito = msgComiteCredito;
    }
    
    public Boolean getMsgComiteCredito() {
        return this.msgComiteCredito;
    }
    
    public void setMsgConNotificacion(Boolean msgConNotificacion) {
        this.msgConNotificacion = msgConNotificacion;
    }
    
    public Boolean getMsgConNotificacion() {
        return this.msgConNotificacion;
    }
    
    public void setMsgEquipoTrabajo(Boolean msgEquipoTrabajo) {
        this.msgEquipoTrabajo = msgEquipoTrabajo;
    }
    
    public Boolean getMsgEquipoTrabajo() {
        return this.msgEquipoTrabajo;
    }

    /**
     * Constructor por defecto
     */
    public AprobacionActionBean() {
        super();
    }

  public Boolean getIsCondicionEditable()
  {
    AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
    
    if(null != aprobacionBean.getOperCondicion() && aprobacionBean.getOperCondicion().equalsIgnoreCase("R"))
    {
        return true;
    }
    
    return false;
  }

  public void setPopupConfirmacionEliminarCondicion(RichPopup popupConfirmacionEliminarCondicion)
  {
    this.popupConfirmacionEliminarCondicion = popupConfirmacionEliminarCondicion;
  }

  public RichPopup getPopupConfirmacionEliminarCondicion()
  {
    return popupConfirmacionEliminarCondicion;
  }

  public void setPopupAgregarModificarLeerCondicion(RichPopup popupAgregarModificarLeerCondicion)
  {
    this.popupAgregarModificarLeerCondicion = popupAgregarModificarLeerCondicion;
  }

  public RichPopup getPopupAgregarModificarLeerCondicion()
  {
    return popupAgregarModificarLeerCondicion;
  }

  public void setNumeroLinea(String numeroLinea)
    {
      this.numeroLinea = numeroLinea;
    }
  
    public String getNumeroLinea()
    {
      return numeroLinea;
    }
  
    public void setDescripcionLinea(String descripcionLinea)
    {
      this.descripcionLinea = descripcionLinea;
    }
  
    public String getDescripcionLinea()
    {
      return descripcionLinea;
    }
  
    public void setMontoLinea(BigDecimal montoLinea)
    {
      this.montoLinea = montoLinea;
    }
  
    public BigDecimal getMontoLinea()
    {
      return montoLinea;
    }
  
    public void setPopupConfMasInfo(RichPopup popupConfMasInfo)
    {
      this.popupConfMasInfo = popupConfMasInfo;
    }
  
    public RichPopup getPopupConfMasInfo()
    {
      return popupConfMasInfo;
    }

    public void setFechaRPresencial(RichInputDate fechaRPresencial) {
        this.fechaRPresencial = fechaRPresencial;
    }

    public RichInputDate getFechaRPresencial() {
        return fechaRPresencial;
    }

    public void setHoraRPresencial(RichInputText horaRPresencial) {
        this.horaRPresencial = horaRPresencial;
    }

    public RichInputText getHoraRPresencial() {
        return horaRPresencial;
    }

    public void setLugarRPresencial(RichInputText lugarRPresencial) {
        this.lugarRPresencial = lugarRPresencial;
    }

    public RichInputText getLugarRPresencial() {
        return lugarRPresencial;
    }

    public void setFechaAperturaRVirtual(RichInputDate fechaAperturaRVirtual) {
        this.fechaAperturaRVirtual = fechaAperturaRVirtual;
    }

    public RichInputDate getFechaAperturaRVirtual() {
        return fechaAperturaRVirtual;
    }

    public void setFechaCierreRVirtual(RichInputDate fechaCierreRVirtual) {
        this.fechaCierreRVirtual = fechaCierreRVirtual;
    }

    public RichInputDate getFechaCierreRVirtual() {
        return fechaCierreRVirtual;
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
    
    /**
     * Obtiene cadena valor del Resource Bundle con parametros
     * @param psKey contiene clave de bundle
     * @param params contiene array de valores para los parametros de la cadena
     * @return devuelve cadena
     */
    private String getStringFromBundle(String psKey, String[] params) {
        String txt = null;
        if(params != null){
            txt = StringManager.getString(BUNDLE, psKey, null, params);    
        }
        return txt;
    }

    /**
     * Obtiene y valida los datos capturados en pantalla y ejecuta procedimiento del Modelo para
     * enviar la informacion a operacion de WS.
     * @param showMsg Indicador para mostrar mensajes
     * @return devuelve cadena con clave de mensaje de error, o devuelve null en caso de una validacion
     *         exitosa.
     */
    public String validaDatosSolicitudAprobacion(boolean showMsg) {
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validaDatosSolicitudAprobacion", 
                        showMsg);
        
        boolean esPresencial = false;
        boolean esVirtual = false;
        String msgCve = null;
        String msgError = null;
        
        Long idOperacion = null;
        Integer tipoSolicitud = null;
        Integer idNivelAprobacion = null;
        String tipoReunion = null;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        try{
            idOperacion = Long.parseLong(aprobacionBean.getIdOperacion());
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.severe("Error al obtener Id de Operacion", e);
        }
        
        tipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();
        
        try{
            idNivelAprobacion = Integer.parseInt(ADFUtils.getBoundAttributeValue(ID_NIVEL_APROB_ATRIBUTO).toString());    
        }catch(Exception e){
            LOGGER.severe("Error al obtener Id de Nivel Aprobacion", e);    
        }
        
        try{
            if(ADFUtils.getBoundAttributeValue(CVE_TIPO_REUNION_ATRIBUTO) == null){
                String strValue = 
                    (String) ADFContext.getCurrent().getSessionScope().get(CVE_TIPO_REUNION_VAR_SESSION);
                if(strValue != null){
                    tipoReunion = strValue;
                }else{
                    LOGGER.severe("Error no se obtuvo valor del Id del Tipo de Reunion");
                }
            }else{
                tipoReunion =  ADFUtils.getBoundAttributeValue(CVE_TIPO_REUNION_ATRIBUTO).toString();
                
                try{
                    //Limpia valor temporal en session
                    ADFContext.getCurrent().getSessionScope().remove(CVE_TIPO_REUNION_VAR_SESSION);    
                }catch(Exception e){
                    LOGGER.warning("No se pudo eliminar variable de session: " + CVE_TIPO_REUNION_VAR_SESSION,
                                   e);
                }
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener Id del Tipo de Reunion", e);
        }
        
        if (null != tipoReunion) {

            if ("1".equals(tipoReunion)) {
                esPresencial = true;
            }
            if ("2".equals(tipoReunion)) {
                esVirtual = true;
            }
        }

        if (esPresencial || esVirtual) {
            
            Date minFecha = getMinFechaInicio();

            if (esPresencial) {

                //Obtiene los valores para Reunion tipo Presencial
                Date fecha = null;
                String lugar = null;
                Timestamp time = null;
                String strTime = null;
                SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm");

                fecha = (Date) getFechaRPresencial().getValue();

                if (null != fecha) {
                    
                    if(fecha.compareTo(minFecha) < 0){
                        msgCve = MSG_ERROR_FECHA_INICIO_PRESENCIAL_INVALIDA_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_FECHA_INICIO_PRESENCIAL_INVALIDA_BKEY);
                    }

                    strTime = (String)getHoraRPresencial().getValue();
                    if (null != strTime) {
                        Date fechaTmp = null;
                        try {
                            fechaTmp = formatDate.parse(strTime);
                        } catch (ParseException e) {
                            LOGGER.severe("Error al convertir fecha: " + strTime, e);
                        }

                        if (null != fechaTmp) {
                            time = new Timestamp(fechaTmp.getTime());
                        }

                        if (null == time) {
                            //Muestra mensaje de error al convertir hora
                            msgCve = MSG_ERROR_HORA_CONVERT_BKEY;
                            msgError = getStringFromBundle(MSG_ERROR_HORA_CONVERT_BKEY);
                        }
                    }else{
                        //Muestra mensaje para captura de la Hora    
                        msgCve = MSG_ERROR_HORA_VACIA_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_HORA_VACIA_BKEY);
                    }
                } else {
                    //Muestra mensaje para captura la fecha
                    msgCve = MSG_ERROR_FECHA_VACIA_BKEY;
                    msgError = getStringFromBundle(MSG_ERROR_FECHA_VACIA_BKEY);
                }

                lugar = (String) getLugarRPresencial().getValue();

                if (null == lugar || lugar.isEmpty()) {

                    //Muestra mensaje para captura de Lugar
                    msgCve = MSG_ERROR_LUGAR_VACIA_BKEY;
                    msgError = getStringFromBundle(MSG_ERROR_LUGAR_VACIA_BKEY);
                }
            } else {

                //Obtiene los valores para Reunion tipo Virtual
                Date fechaInicio = null;
                Date fechaTermino = null;

                fechaInicio = (Date) getFechaAperturaRVirtual().getValue();
                        
                if (null != fechaInicio) {
                    
                    Calendar f = DateToCalendar(minFecha);
                    //f.add(Calendar.DATE, -1);
                    Date fechaMinima = f.getTime();

                    if(fechaInicio.compareTo(fechaMinima) < 0){
                        msgCve = MSG_ERROR_FECHA_INICIO_VIRTUAL_INVALIDA_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_FECHA_INICIO_VIRTUAL_INVALIDA_BKEY);
                    }

                    fechaTermino = (Date) getFechaCierreRVirtual().getValue();
                    if (null == fechaTermino) {

                        //Muestra mensaje para captura de Fecha de Cierre
                        msgCve = MSG_ERROR_FECHA_CIERRE_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_FECHA_CIERRE_BKEY);
                    }else{
                        
                        //Valida fecha de apertura con fecha cierre
                        if(fechaTermino.compareTo(fechaInicio) < 0){
                            msgCve = MSG_ERROR_FECHA_FUERA_RANGO_BKEY;
                            msgError = getStringFromBundle(MSG_ERROR_FECHA_FUERA_RANGO_BKEY); 
                        }
                    }
                } else {

                    //Muestra mensaje para captura de Fecha de Apertura
                    msgCve = MSG_ERROR_FECHA_INICIO_BKEY;
                    msgError = getStringFromBundle(MSG_ERROR_FECHA_INICIO_BKEY);
                }
            }
        } else {

            //Muestra mensajes para la seleccion del tipo de reunion
            msgCve = MSG_ERROR_TIPO_REUNION_BKEY;
            msgError = getStringFromBundle(MSG_ERROR_TIPO_REUNION_BKEY);
        }

        //Verifica mensaje de validacion
        if (null != msgError &&
            showMsg) {
            LOGGER.warning("Mensaje de Error: " + msgError);
            JSFUtils.addFacesErrorMessage(msgError);
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validaDatosSolicitudAprobacion",
                       new Object[]{msgCve});
        return msgCve;
    }
    
    /**
     * Realiza la validacion de los campos a capturar para la Consolidacion de Decision
     * @param showMsg Indicador para mostrar mensajes
     * @return devuelve cadena con clave de mensaje de error, o devuelve null en caso de una validacion
     *         exitosa.
     */
    public String validaConsolidarDecision(boolean showMsg){
        LOGGER.warning("Into validaConsolidarDecision");
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validaConsolidarDecision", 
                        showMsg);
        String msgCve = null;
        String msgError = null;
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
//        Number idTipoAccion = 
//                    (Number)ADFUtils.getBoundAttributeValue(ID_ACCION_A_SEGUIR_ATRIBUTO);
//       String codigoTipoAccion =
//            (String)ADFUtils.getBoundAttributeValue(CODIGO_ACCION_A_SEGUIR_ATRIBUTO);
        LOGGER.warning("idTipoAccionBean:"+aprobacionBean.getIdTipoAccion());
        Number idTipoAccion = aprobacionBean.getIdTipoAccion();
        LOGGER.warning("idTipoAccion:"+idTipoAccion);
        String codigoTipoAccion = aprobacionBean.getCodigoTipoAccion();
        LOGGER.warning("codigoTipoAccion:"+codigoTipoAccion);
        if(null != idTipoAccion &&
           (aprobacionBean.getIntTipoReunion().equals(ID_REUNION_PRESENCIAL.intValue()) ||
            aprobacionBean.getIntTipoReunion().equals(ID_REUNION_VIRTUAL.intValue())) 
          ){
                
            //Aplica validaciones de Operacion
            if(new Integer(1).equals(aprobacionBean.getIntIdTipoSolicitud())){
                
                String numActa = (String) getNumActaUIC().getValue();
                String numAcuerdo = (String) getNumAcuerdoUIC().getValue();
                String acuerdo = (String) getAcuerdoUIC().getValue();
                
                //Verifica la accion a seguir
                if(!idTipoAccion.equals(ID_ACCION_RETORNAR) &&
                   !codigoTipoAccion.equals(CODIGO_ACCION_RETORNAR)){
                    
                    LOGGER.warning("La accion es Positiva");
                    
                    //Define validaciones para accion positiva
                    if(null == numAcuerdo ||
                       "".equals(numAcuerdo)){ 
                        //Aplica para reunion presencial y virtual
                        msgCve = MSG_ERROR_NUMERO_ACUERDO_EMPTY_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_NUMERO_ACUERDO_EMPTY_BKEY);
                    }
                    
                    if(null == acuerdo ||
                       "".equals(acuerdo)){ 
                        //Aplica para reunion presencial y virtual
                        msgCve = MSG_ERROR_ACUERDO_EMPTY_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_ACUERDO_EMPTY_BKEY);
                    }
                    
                    if(aprobacionBean.getIntTipoReunion() != null &&
                       aprobacionBean.getIntTipoReunion().equals(ID_REUNION_PRESENCIAL.intValue())){
                        
                        if(null == numActa ||
                           "".equals(numActa)){
                            //Aplica para reunion presencial
                            msgCve = MSG_ERROR_NUMERO_ACTA_EMPTY_BKEY;
                            msgError = getStringFromBundle(MSG_ERROR_NUMERO_ACTA_EMPTY_BKEY);
                        }
                    }
                }else{
                    
                    LOGGER.warning("La accion es Negativa");
                    
                    //Define validaciones para accion negativa
                    if(null == acuerdo ||
                       "".equals(acuerdo)){ 
                        //Aplica para reunion presencial y virtual
                        msgCve = MSG_ERROR_ACUERDO_EMPTY_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_ACUERDO_EMPTY_BKEY);
                    }
                    
                    if(null != aprobacionBean.getIntTipoReunion() &&
                       aprobacionBean.getIntTipoReunion().equals(ID_REUNION_PRESENCIAL.intValue())){
                        
                        if(null == numAcuerdo ||
                           "".equals(numAcuerdo)){ 
                            //Aplica para reunion presencial
                            msgCve = MSG_ERROR_NUMERO_ACUERDO_EMPTY_BKEY;
                            msgError = getStringFromBundle(MSG_ERROR_NUMERO_ACUERDO_EMPTY_BKEY);
                        }
                    }
                }
            }else{
                //Aplica validaciones de Enmienda
                if(new Integer(2).equals(aprobacionBean.getIntIdTipoSolicitud())){
                    //TODO : Codigo por definir
                }
            }
        }else{
            if(idTipoAccion == null){
                //Muestra mensajes para la seleccion del tipo de reunion
                msgCve = MSG_ERROR_TIPO_ACCION_EMPTY_BKEY;
                msgError = getStringFromBundle(MSG_ERROR_TIPO_ACCION_EMPTY_BKEY);
            }else{
                msgCve = MSG_ERROR_TIPO_REUNION_EMPTY_BKEY;
                msgError = getStringFromBundle(MSG_ERROR_TIPO_REUNION_EMPTY_BKEY);
            }
        }
        
        //Verifica mensaje de validacion
        if(null != msgError &&
           showMsg){
            LOGGER.warning("Mensaje de Error: " + msgError);
            JSFUtils.addFacesErrorMessage(msgError);
        }
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validaConsolidarDecision", 
                        msgCve);
        LOGGER.warning("Leave validaConsolidarDecision");
        return msgCve;
    }
    
    /**
     * Realiza la validacion de Consolidar Decision Directorio al finalizar la tarea
     * @param budleKeyErroList contiene una lista de clave de Bundle para mensajes de error
     * @return devuelve valor booleano, true si la validacion es positiva o false en caso contrario
     */
    public boolean validarConsolidarDecisionDirectorio(List<String> budleKeyErroList){
        
        LOGGER.warning("Inside validarConsolidarDecisionDirectorio.");
        
        boolean isValidFinalizarTarea = Boolean.FALSE;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        AttributeBinding accionAseguir = null;
        accionAseguir = ADFUtils.findControlBinding("IdAccionSeguir");
        
        LOGGER.warning("accionAseguir : " + accionAseguir);
        
        if(null != accionAseguir.getInputValue()){
            
            //Valida comentarios segun regla RN07 para acciones a seguir negativas
            String codeAccionSeguir = null;
            Row currentRow = getCurrentRegistrarDatosAprobacionVO();
            codeAccionSeguir = (String)currentRow.getAttribute("CodExternoAccionSeguir");
            
            LOGGER.warning("codeAccionSeguir : " + accionAseguir);
            
            if(!CODIGO_ACCION_APROBAR_CONDECDIR.equals(codeAccionSeguir)){
                LOGGER.warning("Valida Accion a Seguir Negativa");
                boolean esComentario = 
                    validaComentarioOperacion(FenixConstants.PC04_CONSOLIDARDECISIONDIRECTORIO, true);
                if(esComentario){
                    LOGGER.warning("Existe comentario de operacion, validacion positiva");
                    isValidFinalizarTarea = Boolean.TRUE;
                }else{
                    LOGGER.severe("No existe comentario de operacion, validacion negativa");
                    isValidFinalizarTarea = Boolean.FALSE;
                }
            }else{
                LOGGER.warning("La Accion a Seguir es Positiva");
                isValidFinalizarTarea = Boolean.TRUE; 
                
                AttributeBinding numeroResolucion = null;
                numeroResolucion = ADFUtils.findControlBinding("NumeroResolucion");
                AttributeBinding fechaAprobacion;
                fechaAprobacion = ADFUtils.findControlBinding("FechaAprobacion");
                
                BigDecimal montoAprob = null;
                try{
                    montoAprob = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoAprobacion");
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el attribute value de Monto Aprobacion", e);
                }
                
                if(montoAprob == null){
                    LOGGER.severe("No se ha agregado el monto de aprobacion");
                    isValidFinalizarTarea = Boolean.FALSE;
                    budleKeyErroList.add(MSG_ERROR_MONTO_APROBACION_VACIO);
                }
                
                //[KB:13306]
                Integer IdTcaTipoMoneda = null;
                try {
                    IdTcaTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
                    LOGGER.info("IdTcaTipoMoneda Aprobacion: " + IdTcaTipoMoneda);
                } catch (Exception e) {
                    LOGGER.severe("Error al obtener el attribute value de Tipo Moneda aprobacion", e);
                }

                if (IdTcaTipoMoneda == null) {
                    LOGGER.warning("No se ha agregado el Tipo de Moneda de la aprobacion");
                    isValidFinalizarTarea = Boolean.FALSE;
                    budleKeyErroList.add(MSG_ERROR_TIPO_MONEDA_APROBACION_VACIO);
                }
                
                if(null== numeroResolucion.getInputValue()){
                    LOGGER.severe("No se ha agregado numero de resolucion");
                    isValidFinalizarTarea = Boolean.FALSE;
                    budleKeyErroList.add(MSG_ERROR_AGREGAR_NUMERO_RESOLUCION);
                }
                if(null==fechaAprobacion.getInputValue()){
                    LOGGER.severe("No se ha agregado fecha de aprobacion");
                    isValidFinalizarTarea = Boolean.FALSE;
                    budleKeyErroList.add(MSG_ERROR_AGREGAR_FECHA_APROBACION);
                }
                
                DCIteratorBinding lineasCredito = null;
                ViewObject eq = null;
                Row[] lineasCreditoActivas = null;
                
                DCBindingContainer container = ADFUtils.getDCBindingContainer();
                if(container != null){
                    lineasCredito = 
                        container.findIteratorBinding("RegistrarLineaCreditoAprobacionVOIterator");
                    if(lineasCredito != null){
                        eq = lineasCredito.getViewObject();        
                        if(eq != null){
                            lineasCreditoActivas = eq.getAllRowsInRange();
                        }
                    }
                }
                
                if(aprobacionBean.getLineaCredito()){
                    if(lineasCreditoActivas != null){
                        if(lineasCreditoActivas.length <= 0){
                            LOGGER.warning("No se encontraron lineas de credito");
                            //Se dehabilita validacion de lineas de credito agregadas o existentes
                            //debido a que el documento de casos de uso no esta definido y no fue solicitado
                            //isValidFinalizarTarea = Boolean.FALSE;
                            //budleKeyErroList.add(MSG_ERROR_LINEAS_CREDITO);
                        }else{
                            if(isValidFinalizarTarea){
                                //Valida los montos de las lineas de credito, segun la regla RN08.
                                OperationBinding oper = null;
                                try{
                                    oper = executeOperBinding(null, "validaMontoLineas");    
                                }catch(Exception e){
                                    LOGGER.severe("Error al ejecutar la validacion de lineas de credito", e);
                                    oper = null;
                                    budleKeyErroList.add(MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS_FALLO_OPER);
                                }
                                
                                if(oper != null){
                                    if(!oper.getErrors().isEmpty()){
                                        LOGGER.severe("La operacion de validacion de Monto de Lineas de Credito tiene errores. " + 
                                                      oper.getErrors().toString());
                                        isValidFinalizarTarea = Boolean.FALSE;
                                        budleKeyErroList.add(MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS_FALLO_OPER);
                                    }else{
                                        
                                        Boolean montoValido = null;
                                        montoValido = (Boolean) oper.getResult();
                                        if(montoValido != null){
                                            if(montoValido){
                                                LOGGER.warning("La suma de los montos de lineas de credito es valida");
                                                isValidFinalizarTarea = Boolean.TRUE;
                                            }else{
                                                
                                                isValidFinalizarTarea = Boolean.FALSE;
                                                
                                                //Verifica el proceso de validacion
                                                Boolean contieneMontoInvalido = null;
                                                contieneMontoInvalido = 
                                                    (Boolean) ADFUtils.getBoundAttributeValue("ContieneLineasMontoInvalido");
                                                if(contieneMontoInvalido != null &&
                                                   contieneMontoInvalido){
                                                    LOGGER.severe("El proceso de validacion de montos detecto montos invalidos");
                                                    budleKeyErroList.add(MSG_ERROR_VALIDA_LC_MONTOS_INVALIDOS);
                                                }else{
                                                    LOGGER.severe("La suma de los montos de lineas de credito es superior al monto aprobado");
                                                    budleKeyErroList.add(MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS);
                                                }
                                            }
                                        }else{
                                            LOGGER.severe("Error al obtener resultado de la validacion");
                                            isValidFinalizarTarea = Boolean.FALSE;
                                            budleKeyErroList.add(MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS_FALLO_OPER);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else{
                    LOGGER.warning("No se encontraron lineas de credito");
                    //Se dehabilita validacion de lineas de credito agregadas o existentes
                    //debido a que el documento de casos de uso no esta definido y no fue solicitado
                    //isValidFinalizarTarea = Boolean.FALSE;
                    //budleKeyErroList.add(MSG_ERROR_LINEAS_CREDITO);
                }
            }
        }else{
            LOGGER.severe("Error Id de Accion a Seguir no obtenido");
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validarConsolidarDecisionDirectorio",
                       isValidFinalizarTarea);
        return isValidFinalizarTarea;
    }
    
    public String validarConsolidarDecisionComiteCredito(){
        LOGGER.warning("validarConsolidarDecisionComiteCredito starts");
        List<String> bundleKeyErroList = new ArrayList<String>();
        Number idTipoAccion = null;
        String codigoTipoAccion = null;
        String numAcuerdo = null;
        String acuerdo = null; 
        
        try {
           idTipoAccion = (Number)ADFUtils.getBoundAttributeValue(ID_ACCION_A_SEGUIR_ATRIBUTO);
           codigoTipoAccion = (String)ADFUtils.getBoundAttributeValue(CODIGO_ACCION_A_SEGUIR_ATRIBUTO);
           numAcuerdo = (String) getNumAcuerdoUIC().getValue();
           acuerdo = (String) getAcuerdoUIC().getValue(); 
            LOGGER.warning("idTipoAccion: "+idTipoAccion);
            LOGGER.warning("codigoTipoAccion: "+codigoTipoAccion);
            LOGGER.warning("numAcuerdo: "+numAcuerdo);
            LOGGER.warning("acuerdo: "+acuerdo);
           if(idTipoAccion == null ||
              codigoTipoAccion == null || codigoTipoAccion.isEmpty() ||
              numAcuerdo == null || numAcuerdo.isEmpty() ||
              acuerdo == null || acuerdo.isEmpty())
           {
               LOGGER.warning("Error datos oligatorios null o vacio..");
               return MSG_ERROR_VALIDA_DATOS_OBLIGATORIOS;
           } 
        } 
        catch (Exception e) 
        {
            LOGGER.warning("Exception on validarConsolidarDecisionComiteCredito()",e);
            return MSG_ERROR_VALIDA_DATOS_OBLIGATORIOS;
        }
              
        LOGGER.warning("validarConsolidarDecisionComiteCredito ends");
        return null;
    }
    
    /**
     * Realiza la validacion de Certificar Decision al finalizar la tarea
     * @param budleKeyErroList contiene una lista de clave de Bundle para mensajes de error
     * @return devuelve valor booleano, true si la validacion es positiva o false en caso contrario
     */
    public boolean validarCertificarDecision(List<String> budleKeyErroList){
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validarCertificarDecision", 
                        budleKeyErroList);
		
		LOGGER.warning("validarCertificarDecision");
		
        boolean isValidFinalizarTarea = Boolean.FALSE;
        
        //Valida si campo fecha de aprobacion cuando la decision de presidente es positiva. Definido en la regla RN03
        Integer esDecisionPositiva = null;
        if(ADFUtils.getBoundAttributeValue("TtdEsDecisionPositiva") != null){
            try{
                esDecisionPositiva = (Integer) ADFUtils.getBoundAttributeValue("TtdEsDecisionPositiva");
            }catch(Exception e){
                LOGGER.severe("Error al obtener el indicador de decision de presidente", e);
                budleKeyErroList.add(MSG_ERROR_LECTURA_VOTO_DECISION);
            }
            
            if(esDecisionPositiva != null){
                if(ID_VOTO_DECISION_POSITIVA_PRESIDENTE.equals(esDecisionPositiva)){
                    
                    oracle.jbo.domain.Date fechaAprobacion = null;
                    if(ADFUtils.getBoundAttributeValue("FechaAprobacion") != null){
                        
                        try{
                            fechaAprobacion = 
                                (oracle.jbo.domain.Date) ADFUtils.getBoundAttributeValue("FechaAprobacion");
                            if(fechaAprobacion != null){
                                LOGGER.warning("La fecha de aprobacion fue capturada");
                                isValidFinalizarTarea = Boolean.TRUE;
                            }else{
                                budleKeyErroList.add(MSG_ERROR_FECHA_APROBACION_VACIO);
                            }
                        }catch(Exception e){
                            LOGGER.severe("Error al obtener la fecha de aprobacion", e);
                        }
                    }else{
                        LOGGER.severe("La fecha de aprobacion no fue capturada");
                        budleKeyErroList.add(MSG_ERROR_FECHA_APROBACION_VACIO);
                    }
                    BigDecimal montoAprob = null;
                    try{
                        montoAprob = (BigDecimal) ADFUtils.getBoundAttributeValue("Monto");
                    }catch(Exception e){
                        LOGGER.severe("Error al obtener el attribute value de Monto Aprobacion", e);
                    }
                    
                    if(montoAprob == null){
                        LOGGER.severe("No se ha agregado el monto de aprobacion");
                        isValidFinalizarTarea = Boolean.FALSE;
                        budleKeyErroList.add(MSG_ERROR_MONTO_APROBACION_VACIO);
                    }
					
					Integer IdTcaTipoMoneda = null;
                    try {
                        IdTcaTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
                        LOGGER.warning("IdTcaTipoMoneda: " + IdTcaTipoMoneda);
                    } catch(Exception e) {
                        LOGGER.warning("Error al obtener el attribute value de Tipo Moneda", e);
                    }
                    
                    if(IdTcaTipoMoneda == null) {
                        LOGGER.warning("No se ha agregado el Tipo de Moneda");
                        isValidFinalizarTarea = Boolean.FALSE;
                        budleKeyErroList.add(MSG_ERROR_TIPO_MONEDA_APROBACION_VACIO);
                    }
                }else{
                    LOGGER.warning("El voto del presidente es negativo, fecha de aprobacion no requerida");
                    isValidFinalizarTarea = Boolean.TRUE;
                }
            }
        }else{
            LOGGER.severe("Error al obtener el indicador de decision de presidente");
            budleKeyErroList.add(MSG_ERROR_ID_VOTO_DECISION_VACIO);
        }
        
        if(isValidFinalizarTarea){
            //Valida los montos de las lineas de credito, segun la regla RN05.
            DCIteratorBinding lineasCredito = null;
            ViewObject eq = null;
            Row[] lineasCreditoActivas = null;
            
            DCBindingContainer container = ADFUtils.getDCBindingContainer();
            if(container != null){
                lineasCredito = 
                    container.findIteratorBinding("RegistrarLineaCreditoAprobacionVOIterator");
                if(lineasCredito != null){
                    eq = lineasCredito.getViewObject();        
                    if(eq != null){
                        lineasCreditoActivas = eq.getAllRowsInRange();
                    }
                }
            }
            
            if(lineasCreditoActivas != null){
                if(lineasCreditoActivas.length <= 0){
                    LOGGER.warning("No se encontraron lineas de credito");
                    //Se dehabilita validacion de lineas de credito agregadas o existentes
                    //debido a que el documento de casos de uso no esta definido y no fue solicitado
                    //isValidFinalizarTarea = Boolean.FALSE;
                    //budleKeyErroList.add(MSG_ERROR_LINEAS_CREDITO);
                }else{
                    
                    OperationBinding oper = null;
                    try{
                        oper = executeOperBinding(null, "validaMontoLineas");    
                    }catch(Exception e){
                        LOGGER.severe("Error al ejecutar la validacion de lineas de credito", e);
                        oper = null;
                        budleKeyErroList.add(MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS_FALLO_OPER);
                    }
                    
                    if(oper != null){
                        if(!oper.getErrors().isEmpty()){
                            LOGGER.severe("La operacion de validacion de Monto de Lineas de Credito tiene errores. " + 
                                          oper.getErrors().toString());
                            isValidFinalizarTarea = Boolean.FALSE;
                            budleKeyErroList.add(MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS_FALLO_OPER);
                        }else{
                            
                            Boolean montoValido = null;
                            montoValido = (Boolean) oper.getResult();
                            if(montoValido != null){
                                if(montoValido){
                                    LOGGER.warning("La suma de los montos de lineas de credito es valida");
                                    isValidFinalizarTarea = Boolean.TRUE;
                                }else{
                                    
                                    isValidFinalizarTarea = Boolean.FALSE;
                                    
                                    //Verifica el proceso de validacion
                                    Boolean contieneMontoInvalido = null;
                                    contieneMontoInvalido = 
                                        (Boolean) ADFUtils.getBoundAttributeValue("ContieneLineasMontoInvalido");
                                    if(contieneMontoInvalido != null &&
                                       contieneMontoInvalido){
                                        LOGGER.severe("El proceso de validacion de montos detecto montos invalidos");
                                        budleKeyErroList.add(MSG_ERROR_VALIDA_LC_MONTOS_INVALIDOS);
                                    }else{
                                        LOGGER.severe("La suma de los montos de lineas de credito es superior al monto aprobado");
                                        budleKeyErroList.add(MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS);
                                    }
                                }
                            }else{
                                LOGGER.severe("Error al obtener resultado de la validacion");
                                isValidFinalizarTarea = Boolean.FALSE;
                                budleKeyErroList.add(MSG_ERROR_VALIDAR_MONTO_TOTAL_LINEAS_FALLO_OPER);
                            }
                        }
                    }
                }
            }else{
                LOGGER.warning("No se encontraron lineas de credito");
                //Se dehabilita validacion de lineas de credito agregadas o existentes
                //debido a que el documento de casos de uso no esta definido y no fue solicitado
                //isValidFinalizarTarea = Boolean.FALSE;
                //budleKeyErroList.add(MSG_ERROR_LINEAS_CREDITO);
            }
        }
        
        if(isValidFinalizarTarea){
            //TODO Implementar regla RN14, aplica para enmiendas
        }
              
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validarCertificarDecision",
                       isValidFinalizarTarea);
        return isValidFinalizarTarea;
    }
    
    /**
     * Realiza la validacion de la tarea Actualizar TCC
     * @param budleKeyErroList contiene una lista de clave de Bundle para mensajes de error
     * @return devuelve valor booleano, true si la validacion es positiva o false en caso contrario
     */
    public boolean validarActualizarTCC(List<String> budleKeyErroList){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validarActualizarTCC", 
                        budleKeyErroList);
        
        boolean isValidFinalizarTarea = Boolean.TRUE;
        
        if(budleKeyErroList == null){
             budleKeyErroList = new ArrayList<String>();
        }
        
        //Validar los datos del termino
        oracle.jbo.domain.Date fechaInicio = null;
        Integer plazo = null;
        Number tipoPlazo = null;
        
        try{
            fechaInicio = (oracle.jbo.domain.Date) ADFUtils.getBoundAttributeValue("FechaInicio");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener la fecha inicio", e);
            isValidFinalizarTarea = Boolean.FALSE;
        }
        
        if(fechaInicio == null){
            budleKeyErroList.add("pc04aprobacion_actualizar_tcc_msg_error_termino_fecha_inicio_vacia");
            isValidFinalizarTarea = Boolean.FALSE;
        }
        
        try{
            plazo = (Integer) ADFUtils.getBoundAttributeValue("Plazo");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener el plazo");
            isValidFinalizarTarea = Boolean.FALSE;
        }
    
        if(plazo == null){
            budleKeyErroList.add("pc04aprobacion_actualizar_tcc_msg_error_termino_plazo_vacio");
            isValidFinalizarTarea = Boolean.FALSE;
        }
        
        try{
            tipoPlazo = (Number) ADFUtils.getBoundAttributeValue("TipoPlazoIdTerminoAttrValue");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener el tipo plazo", e);
            isValidFinalizarTarea = Boolean.FALSE;
        }
        
        if(tipoPlazo == null){
            budleKeyErroList.add("pc04aprobacion_actualizar_tcc_msg_error_termino_tipo_plazo_vacio");
            isValidFinalizarTarea = Boolean.FALSE;
        }
        
        //Valida los registros de condicion
        RowSetIterator iterCondicion = null;
        try{
            iterCondicion = 
                ADFUtils.getDCBindingContainer().findIteratorBinding("RegistrarCondicionAprobacionVOIterator").getRowSetIterator();
            if(iterCondicion == null){
                LOGGER.severe("No se pudo obtener el iterator de Registrar Condicion Aprobacion");
                isValidFinalizarTarea = Boolean.FALSE;
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el iterator de Registrar Condicion Aprobacion");
            isValidFinalizarTarea = Boolean.FALSE;
        }
            
        if(iterCondicion != null){
            
            boolean esCategoriasValidas = true;
            boolean esEventosValidos = true;
            boolean esDescripcionesValidas = true;
            boolean esObservacionesValidas = true;
            
            Array categoriasList = null;
            Array eventosList = null;
            String descripcion = null;
            //String observaciones = null;
            
            Row row = null;
            iterCondicion.reset();
            row = iterCondicion.first();
            for(int index = 0; index < iterCondicion.getRowCount(); index++){
                if(row != null){
                    //Valida la existencia de categorias
                    categoriasList = (Array) row.getAttribute("IdCategoriaCondicionList");
                    if(categoriasList != null){
                        if(categoriasList.getSize() == 0){
                            esCategoriasValidas = false;
                        }
                    }else{
                        esCategoriasValidas = false;
                    }
                    //Valida la existencia de eventos
                    eventosList = (Array) row.getAttribute("IdEventoCondicionList");
                    if(eventosList != null){
                        if(eventosList.getSize() == 0){
                            esEventosValidos = false;
                        }
                    }else{
                        esEventosValidos = false;
                    }
                    //Valida la existencia de descripciones
                    descripcion = (String) row.getAttribute("Descripcion");
                    if(descripcion != null){
                        descripcion = descripcion.trim();
                        if(descripcion.isEmpty()){
                            esDescripcionesValidas = false;
                        }
                    }else{
                        esDescripcionesValidas = false;
                    }
                    //Valida la existencia de observaciones
                    /* Se deshabilita la validacion de observacion a peticion del cliente. FNXII-2876
                    observaciones = (String) row.getAttribute("Observaciones");
                    if(observaciones != null){
                        observaciones = observaciones.trim();
                        if(observaciones.isEmpty()){
                            esObservacionesValidas = false;
                        }
                    }else{
                        esObservacionesValidas = false;
                    }
                    */
                    
                    if(!esCategoriasValidas ||
                       !esEventosValidos ||
                       !esDescripcionesValidas ||
                       !esObservacionesValidas){
                        
                        //Asigna el codigo bundle para el mensaje de error
                        if(!esCategoriasValidas){
                            budleKeyErroList.add("pc04aprobacion_actualizar_tcc_msg_error_condiciones_categorias_vacias");
                        }
                        if(!esEventosValidos){
                            budleKeyErroList.add("pc04aprobacion_actualizar_tcc_msg_error_condiciones_eventos_vacias");
                        }
                        if(!esDescripcionesValidas){
                            budleKeyErroList.add("pc04aprobacion_actualizar_tcc_msg_error_condiciones_descripciones_vacias");
                        }
                        if(!esObservacionesValidas){
                            budleKeyErroList.add("pc04aprobacion_actualizar_tcc_msg_error_condiciones_observaciones_vacias");
                        }
                        
                        isValidFinalizarTarea = Boolean.FALSE;
                        break;
                    }
                }
                row = iterCondicion.next();
            }
            iterCondicion.closeRowSetIterator();
        }
        
        if(isValidFinalizarTarea){
            isValidFinalizarTarea = validarTCC();
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validarActualizarTCC", 
                       isValidFinalizarTarea);
        return isValidFinalizarTarea;
    }
    
    /**
     * Obtiene y valida los datos capturados en pantalla "RevisarSolicitudAComiteCredito.jspx"
     * @param showMsg Indicador para mostrar mensajes
     * @return devuelve cadena con clave de mensaje de error, o devuelve null en caso de una validacion exitosa.
     */
    public String validaDatosSolicitudAprobacionCliente(boolean showMsg) {
        
        LOGGER.entering(AprobacionActionBean.class.getName(),"validaDatosSolicitudAprobacionCliente",showMsg);
        
        boolean esPresencial = false;
        boolean esVirtual = false;
        String msgCve = null;
        String msgError = null;
        
        Long idCliente = null;
        Integer tipoSolicitud = null;
        Integer idNivelAprobacion = null;
        String tipoReunion = null;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        //recuperar idCliente
        try{
            idCliente = Long.parseLong(aprobacionBean.getIdCliente());
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.severe("Error al obtener idCliente", e);
        }
        //recuperar tipoSolicitud
        if(null != aprobacionBean.getIntIdTipoSolicitud()){
            tipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener tipoSolicitud");
        }
        //recuperar idNivelAprobacion
        if(null != aprobacionBean.getIntIdNivelAprob()){
            idNivelAprobacion = aprobacionBean.getIntIdNivelAprob();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener idNivelAprobacion");
        }
        //recuperar idTipoReunion
        if(null != aprobacionBean.getIdTipoReunion()){
            tipoReunion = aprobacionBean.getIdTipoReunion();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener tipoReunion");
        }
        
        if (null != tipoReunion) {

            if ("1".equals(tipoReunion)) {
                esPresencial = true;
            }
            if ("2".equals(tipoReunion)) {
                esVirtual = true;
            }
        }

        if (esPresencial || esVirtual) {
            
            Date minFecha = getMinFechaInicio();

            if (esPresencial) {

                //Obtiene los valores para Reunion tipo Presencial
                Date fecha = null;
                String lugar = null;
                Timestamp time = null;
                String strTime = null;
                SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm");

                fecha = (Date) getFechaRPresencial().getValue();

                if (null != fecha) {
                    
                    if(fecha.compareTo(minFecha) < 0){
                        msgCve = MSG_ERROR_FECHA_INICIO_PRESENCIAL_INVALIDA_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_FECHA_INICIO_PRESENCIAL_INVALIDA_BKEY);
                    }

                    strTime = (String)getHoraRPresencial().getValue();
                    if (null != strTime) {
                        Date fechaTmp = null;
                        try {
                            fechaTmp = formatDate.parse(strTime);
                        } catch (ParseException e) {
                            LOGGER.severe("Error al convertir fecha: " + strTime, e);
                        }

                        if (null != fechaTmp) {
                            time = new Timestamp(fechaTmp.getTime());
                        }

                        if (null == time) {
                            //Muestra mensaje de error al convertir hora
                            msgCve = MSG_ERROR_HORA_CONVERT_BKEY;
                            msgError = getStringFromBundle(MSG_ERROR_HORA_CONVERT_BKEY);
                        }
                    }else{
                        //Muestra mensaje para captura de la Hora    
                        msgCve = MSG_ERROR_HORA_VACIA_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_HORA_VACIA_BKEY);
                    }
                } else {
                    //Muestra mensaje para captura la fecha
                    msgCve = MSG_ERROR_FECHA_VACIA_BKEY;
                    msgError = getStringFromBundle(MSG_ERROR_FECHA_VACIA_BKEY);
                }

                lugar = (String) getLugarRPresencial().getValue();

                if (null == lugar || lugar.isEmpty()) {

                    //Muestra mensaje para captura de Lugar
                    msgCve = MSG_ERROR_LUGAR_VACIA_BKEY;
                    msgError = getStringFromBundle(MSG_ERROR_LUGAR_VACIA_BKEY);
                }
            } else {

                //Obtiene los valores para Reunion tipo Virtual
                Date fechaInicio = null;
                Date fechaTermino = null;

                fechaInicio = (Date) getFechaAperturaRVirtual().getValue();
                        
                if (null != fechaInicio) {
                    
                    Calendar f = DateToCalendar(minFecha);
                    //f.add(Calendar.DATE, -1);
                    Date fechaMinima = f.getTime();

                    if(fechaInicio.compareTo(fechaMinima) < 0){
                        msgCve = MSG_ERROR_FECHA_INICIO_VIRTUAL_INVALIDA_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_FECHA_INICIO_VIRTUAL_INVALIDA_BKEY);
                    }

                    fechaTermino = (Date) getFechaCierreRVirtual().getValue();
                    if (null == fechaTermino) {

                        //Muestra mensaje para captura de Fecha de Cierre
                        msgCve = MSG_ERROR_FECHA_CIERRE_BKEY;
                        msgError = getStringFromBundle(MSG_ERROR_FECHA_CIERRE_BKEY);
                    }else{
                        
                        //Valida fecha de apertura con fecha cierre
                        if(fechaTermino.compareTo(fechaInicio) < 0){
                            msgCve = MSG_ERROR_FECHA_FUERA_RANGO_BKEY;
                            msgError = getStringFromBundle(MSG_ERROR_FECHA_FUERA_RANGO_BKEY); 
                        }
                    }
                } else {

                    //Muestra mensaje para captura de Fecha de Apertura
                    msgCve = MSG_ERROR_FECHA_INICIO_BKEY;
                    msgError = getStringFromBundle(MSG_ERROR_FECHA_INICIO_BKEY);
                }
            }
        } else {

            //Muestra mensajes para la seleccion del tipo de reunion
            msgCve = MSG_ERROR_TIPO_REUNION_BKEY;
            msgError = getStringFromBundle(MSG_ERROR_TIPO_REUNION_BKEY);
        }

        //Verifica mensaje de validacion
        if (null != msgError &&
            showMsg) {
            LOGGER.warning("Mensaje de Error: " + msgError);
            JSFUtils.addFacesErrorMessage(msgError);
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validaDatosSolicitudAprobacionCliente",
                       new Object[]{msgCve});
        return msgCve;
    }
    
    /**
     * Realiza la creacion de la solicitud de aprobacion para cliente
     * @param showMsg indicador para mostrar o no mensajes
     * @return devuelve cadena con mensaje de error o null en caso de exito
     * By Gabriel niño rosales
     */
    public String crearSolicitudAprobacionCliente(boolean showMsg){
        LOGGER.log(ADFLogger.WARNING, "Dentro de crearSolicitudAprobacionCliente.");

        String msgError = null;
        String tipoReunion = null;
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        if(null != aprobacionBean.getIdTipoReunion()){
            tipoReunion = aprobacionBean.getIdTipoReunion();        
        }else{
           LOGGER.severe("Error no se pudo obtener Id de tipo reunion de AprobacionBean");
        }
    
        if(null != tipoReunion){
            if("1".equals(tipoReunion)){
                LOGGER.log(ADFLogger.WARNING, "Ejecuta la creacion de solicitud de aprobacion por cliente para reunion presencial");
                msgError = crearSolicitudAprobacionClientePresencial(showMsg);
            }else{
                if("2".equals(tipoReunion)){
                    LOGGER.log(ADFLogger.WARNING, "Ejecuta la creacion de solicitud de aprobacion por cliente para reunion virtual");
                    msgError = crearSolicitudAprobacionClienteVirtual(showMsg);
                }
            }
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener el Tipo de Reunion");
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionCliente",
                       msgError);
        
        return msgError;
    }
    
    /**
     * Ejecuta la creacion de la solicitud de aprobacion para cliente obteniendo todos los valores de la vista
     * para tipo de reunion presencial
     * @return devuelve cadena con mensaje de error, devuelve null en caso de exito
     * By Gabriel niño rosales
     */
    public String crearSolicitudAprobacionClientePresencial(boolean showMsg){
        
        LOGGER.log(ADFLogger.WARNING, "Dentro de crearSolicitudAprobacionClientePresencial.");
        
        String msgError = null;
        
        Long idCliente = null;
        Integer tipoSolicitud = null;
        Integer idNivelAprobacion = null;
        String tipoReunion = null;
        String instanciaSeguimiento = null;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        try{
            idCliente = Long.parseLong(aprobacionBean.getIdCliente());
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.log(ADFLogger.ERROR, "Error al obtener idCliente",e);
        }
        
        if(null != aprobacionBean.getIntIdTipoSolicitud()){
            tipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener tipoSolicitud");
        }
        
        if(null != aprobacionBean.getIntIdNivelAprob()){
            idNivelAprobacion = aprobacionBean.getIntIdNivelAprob();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener idNivelAprobacion");
        }
        
        if(null != aprobacionBean.getIdTipoReunion()){
            tipoReunion = aprobacionBean.getIdTipoReunion();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener tipoReunion");
        }
        
        if(null != aprobacionBean.getInstanciaProcesoSeguimiento()){
            instanciaSeguimiento = aprobacionBean.getInstanciaProcesoSeguimiento();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener instanciaSeguimiento");
        }
        
        Date fecha = null;
        String lugar = null;
        Timestamp time = null;
        String strTime = null;
        Date fechaTmp = null;
        SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm");

        fecha = (Date) getFechaRPresencial().getValue();
        strTime = getHoraRPresencial().getValue().toString();
        try {
            fechaTmp = formatDate.parse(strTime);
            time = new Timestamp(fechaTmp.getTime());
        } catch (ParseException e) {
            LOGGER.severe("Error al convertir fecha: " + strTime, e);
        }
        
        lugar = (String) getLugarRPresencial().getValue();
        
        //Ejecuta la creacion de la solicitud de aprobacion por cliente con Reunion Presencial
        msgError = 
            crearSolicitudAprobacionClientePresencial(fecha, 
                                               time, 
                                               lugar,
                                               idCliente,
                                               tipoSolicitud,
                                               idNivelAprobacion,
                                               showMsg,
                                               instanciaSeguimiento);
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionClientePresencial",
                       msgError);
        
        return msgError;
    }
    
    /**
     * Crea una solicitud de aprobacion con Reunion Presencial
     * @param fecha contiene fecha de reunion
     * @param hora contiene hora de la reunion
     * @param lugar  contiene el lugar de la reunion
     * @return devuelve cadena con mensaje de error, devuelve null en caso de exito
     * By Gabriel niño rosales
     */
    @SuppressWarnings("unchecked")
    public String crearSolicitudAprobacionClientePresencial(Date fecha, 
                                                     Timestamp hora, 
                                                     String lugar,
                                                     Long idCliente,
                                                     Integer tipoSolicitud,
                                                     Integer idNivelAprobacion,
                                                     boolean showMsg,
                                                     String instanciaSeguimiento) {
        
        LOGGER.log(ADFLogger.WARNING,"Dentro de crearSolicitudAprobacionClientePresencial",
                   new Object[]{fecha, 
                                hora, 
                                lugar, 
                                idCliente, 
                                tipoSolicitud, 
                                idNivelAprobacion,
                                showMsg,
                                instanciaSeguimiento});

        
        String msg = null;
        boolean isError = false;
        
        if (null == fecha || null == hora || null == lugar || lugar.isEmpty()) {

            msg = "ERROR al recuperar valores de la vista para crearSolicitudAprobacionClientePresencial";
            isError = true;
        }
        
        String loginUsuario = null;
        
        //Obtiene nombre de usuario en session
        loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();

        Map values = new HashMap<String, Object>();
        values.put("tipoReunion", 1);
        values.put("idCliente", idCliente);
        values.put("loginUsuario", loginUsuario);
        values.put("tipoSolicitud", tipoSolicitud);
        values.put("idNivelAprobacion", idNivelAprobacion);
        values.put("fecha", fecha);
        values.put("hora", hora);
        values.put("lugar", lugar);
        values.put("instanciaSeguimiento", instanciaSeguimiento);
        
        Map params = new HashMap();
        params.put("params", values);
        
        LOGGER.warning(">>>>>>>>> idCliente: " + idCliente);
        LOGGER.warning(">>>>>>>>> loginUsuario: " + loginUsuario);
        LOGGER.warning(">>>>>>>>> tipoSolicitud: " + tipoSolicitud);
        LOGGER.warning(">>>>>>>>> idNivelAprobacion: " + idNivelAprobacion);
        LOGGER.warning(">>>>>>>>> fecha: " + fecha);
        LOGGER.warning(">>>>>>>>> hora: " + hora);
        LOGGER.warning(">>>>>>>>> lugar: " + lugar);
        LOGGER.warning(">>>>>>>>> instanciaSeguimiento: " + instanciaSeguimiento);

        OperationBinding oper = executeCrearSolicitudAprobacionClienteWS(params);
        if(null != oper && !isError){
            if(oper.getErrors().isEmpty()){
                Boolean exito = false;
                try{
                    exito = (Boolean)oper.getResult();    
                }catch(Exception e){
                    LOGGER.severe("Error al leer resultado de la operacion");
                }
                
                LOGGER.warning("Resultado del Operator Bindings: " + exito);
                
                if(Boolean.TRUE.equals(exito)){
                    msg = getStringFromBundle(MSG_INFO_CREAR_SOLICITUD_EXITO_BKEY);
                    isError = false;
                    
                    //Actualiza Payload
                    String strIdSolicitud = ADFUtils.getBoundAttributeValue("Id").toString();
                    actualizarPayloadElement("IdSolicitud", Integer.parseInt(strIdSolicitud));
                    actualizarPayloadElement("reunionPresencial", true);
                    actualizarPayloadElement("IdTipoAprobacion", tipoSolicitud);
                    
                    if (JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION) != null) {
                        try {
                            AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                            if (aprobacionBean != null) {
                                LOGGER.warning("AprobacionBean es NULL");
                                actualizarPayloadElement("CodigoOperacion", aprobacionBean.getIdOperacion());
                            } else {
                                LOGGER.warning("AprobacionBean es NULL");
                            }
                        } catch (Exception e) {
                            LOGGER.log(ADFLogger.WARNING, "*** ERROR > " + e.getMessage());
                        }
                    } else {
                        LOGGER.warning("AprobacionBean.BEAN_EXPRESSION es NULL");
                    }
                }else{
                    msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY);
                    isError = true;
                }
            }else{
                msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY) + 
                      ". Errors: " + oper.getErrors().toString();
                isError = true;
            }
        }
        
        if(showMsg){
            if(isError){
                if(null != msg){
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }else{
                if(null != msg){
                    JSFUtils.addFacesInformationMessage(msg);    
                }
            }
        }
        
        if(!isError){
            msg = null;
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionClientePresencial", 
                       msg);
        return msg;
    }
    
    
    /**
     * Ejecuta Operator Bindings para Crear la Solicitud por cliente
     * @param params contiene mapa de parametros
     * @return devuelve objeto Operator Binding
     * By Gabriel niño rosales
     */
    private OperationBinding executeCrearSolicitudAprobacionClienteWS(Map params) {

        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "executeCrearSolicitudAprobacionClienteWS", 
                        params);
    
        OperationBinding oper = executeOperBinding(params,CREAR_SOLICITUD_APROBACION_CLIENTE_OPERACION);
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "executeCrearSolicitudAprobacionClienteWS",
                       oper);
        return oper;
    }
    
    /**
     * Ejecuta la creacion de la solicitud de aprobacion obteniendo todos los valores de la vista
     * para tipo de reunion virtual
     * @param showMsg indicador para mostrar mensaje
     * @return devuelve cadena con mensaje de error o null en caso de exito
     * By Gabriel niño Rosales
     */
    public String crearSolicitudAprobacionClienteVirtual(boolean showMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "crearSolicitudAprobacionClienteVirtual");
        
        String msgError = null;
        Long idCliente = null;
        Integer tipoSolicitud = null;
        Integer idNivelAprobacion = null;
        String tipoReunion = null;
        String instanciaSeguimiento = null;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        try{
            idCliente = Long.parseLong(aprobacionBean.getIdCliente());
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.log(ADFLogger.ERROR, "Error al obtener idCliente",e);
        }
        
        if(null != aprobacionBean.getIntIdTipoSolicitud()){
            tipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener tipoSolicitud");
        }
        
        if(null != aprobacionBean.getIntIdNivelAprob()){
            idNivelAprobacion = aprobacionBean.getIntIdNivelAprob();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener idNivelAprobacion");
        }
        
        if(null != aprobacionBean.getIdTipoReunion()){
            tipoReunion = aprobacionBean.getIdTipoReunion();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener tipoReunion");
        }
        
        if(null != aprobacionBean.getInstanciaProcesoSeguimiento()){
            instanciaSeguimiento = aprobacionBean.getInstanciaProcesoSeguimiento();
        }else{
            LOGGER.log(ADFLogger.ERROR, "Error al obtener instanciaSeguimiento");
        }
        
        Date fechaInicio = null;
        Date fechaTermino = null;

        fechaInicio = (Date) getFechaAperturaRVirtual().getValue();
        fechaTermino = (Date) getFechaCierreRVirtual().getValue(); //Fecha cierre en pantalla, fecha termino en BD
        
        msgError = crearSolicitudAprobacionClienteVirtual(fechaInicio, 
                                                   fechaTermino,
                                                   idCliente,
                                                   tipoSolicitud,
                                                   idNivelAprobacion, 
                                                   showMsg,
                                                   instanciaSeguimiento);
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionClienteVirtual", 
                       msgError);
        
        return msgError;
    }
    
    /**
     * Crea una solicitud de aprobacion por cliente con reunion Virtual
     * @param fechaApertura contiene fecha de inicio
     * @param fechaTermino contiene fecha de cierre
     * @param idCliente contiene id del cliente
     * @param tipoSolicitud contiene id del tipo de solicitud
     * @param idNivelAprobacion contiene id del nivel de aprobacion
     * @param showMsg contiene bandera para indicar si se desea mostrar mensajes de dialogo
     * @return devuelve cadena con mensaje de error, null si la operacion es exitosa
     * By Gabriel niño rosales
     */
    @SuppressWarnings("unchecked")
    public String crearSolicitudAprobacionClienteVirtual(Date fechaApertura, 
                                                Date fechaTermino,
                                                Long idCliente,
                                                Integer tipoSolicitud,
                                                Integer idNivelAprobacion,
                                                boolean showMsg,
                                                String instanciaSeguimiento) {
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "crearSolicitudAprobacionVirtual",
                        new Object[]{fechaApertura, 
                                     fechaTermino, 
                                     idCliente, 
                                     tipoSolicitud, 
                                     idNivelAprobacion,
                                     showMsg,
                                     instanciaSeguimiento});
        
        String msg = null;
        boolean isError = false;
        
        if (null == fechaApertura || null == fechaTermino) {
            msg = "ERROR al recuperar valores de la vista para crearSolicitudAprobacionClienteVirtual";
            //msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY);
            isError = true;
        }
        
        String loginUsuario = null;
        
        //Obtiene nombre de usuario en session
        loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();
        
        LOGGER.fine("Obtiene Usuario: " + loginUsuario);

        Map values = new HashMap<String, Object>();
        values.put("tipoReunion", 2);
        values.put("idCliente", idCliente);
        values.put("loginUsuario", loginUsuario);
        values.put("tipoSolicitud", tipoSolicitud);
        values.put("idNivelAprobacion", idNivelAprobacion);
        values.put("fechaApertura", fechaApertura);
        values.put("fechaTermino", fechaTermino);
        values.put("instanciaSeguimiento", instanciaSeguimiento);
        
        Map params = new HashMap();
        params.put("params", values);
        
        LOGGER.warning(">>>>>>>>> idCliente: " + idCliente);
        LOGGER.warning(">>>>>>>>> loginUsuario: " + loginUsuario);
        LOGGER.warning(">>>>>>>>> tipoSolicitud: " + tipoSolicitud);
        LOGGER.warning(">>>>>>>>> idNivelAprobacion: " + idNivelAprobacion);
        LOGGER.warning(">>>>>>>>> fechaApertura: " + fechaApertura);
        LOGGER.warning(">>>>>>>>> fechaTermino: " + fechaTermino);
        LOGGER.warning(">>>>>>>>> instanciaSeguimiento: " + instanciaSeguimiento);

        OperationBinding oper = executeCrearSolicitudAprobacionClienteWS(params);
        if(oper != null && !isError){
            if(oper.getErrors().isEmpty()){
                Boolean exito = false;
                try{
                    exito = (Boolean)oper.getResult();    
                }catch(Exception e){
                    LOGGER.severe("Error al leer resultado de la operacion");
                }
                
                LOGGER.warning("Resultado del Operator Bindings: " + exito);
                
                if(Boolean.TRUE.equals(exito)){
                    msg = getStringFromBundle(MSG_INFO_CREAR_SOLICITUD_EXITO_BKEY);
                    isError = false;
                    
                    //Actualiza Payload
                    String strIdSolicitud = ADFUtils.getBoundAttributeValue("Id").toString();
                    actualizarPayloadElement("IdSolicitud", Integer.parseInt(strIdSolicitud));
                    actualizarPayloadElement("reunionPresencial", false);
                }else{
                    msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY);
                    isError = true;
                }
            }else{
                msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY) +
                      " . " + oper.getErrors().toString();
                isError = true;
            }
        }
        
        if(showMsg){
            if(isError){
                if(null != msg){
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }else{
                if(null != msg){
                    JSFUtils.addFacesInformationMessage(msg);    
                }
            }
        }
        
        if(!isError){
            msg = null;
        }

        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionClienteVirtual",
                       msg);
        return msg;
    }


    
    /**
     * Realiza la creacion de la solicitud de aprobacion
     * @param showMsg indicador para mostrar o no mensajes
     * @return devuelve cadena con mensaje de error o null en caso de exito
     */
    public String crearSolicitudAprobacion(boolean showMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "crearSolicitudAprobacion");
        String msgError = null;
        String tipoReunion = null;
        
        if(ADFUtils.getBoundAttributeValue(CVE_TIPO_REUNION_ATRIBUTO) != null){
            tipoReunion = 
                ADFUtils.getBoundAttributeValue(CVE_TIPO_REUNION_ATRIBUTO).toString();    
        }else{
            try{
                tipoReunion = 
                    (String) ADFContext.getCurrent().getSessionScope().get(CVE_TIPO_REUNION_VAR_SESSION);    
            }catch(Exception e){
                LOGGER.severe("Error no se pudo obtener Id de tipo reunion por session", e);
            }
        }
        
        //Limpia valor temporal en session
        if(ADFContext.getCurrent().getSessionScope().get(CVE_TIPO_REUNION_VAR_SESSION) != null){
            try{
                ADFContext.getCurrent().getSessionScope().remove(CVE_TIPO_REUNION_VAR_SESSION);     
            }catch(Exception e){
                LOGGER.warning("No se pudo eliminar variable de session: " + CVE_TIPO_REUNION_VAR_SESSION,
                               e);
            }
        }

        if(null != tipoReunion){
            if("1".equals(tipoReunion)){
                LOGGER.warning("Ejecuta la creacion de solicitud de aprobacion para reunion presencial");
                msgError = crearSolicitudAprobacionPresencial(showMsg);
            }else{
                if("2".equals(tipoReunion)){
                    LOGGER.warning("Ejecuta la creacion de solicitud de aprobacion para reunion virtual");
                    msgError = crearSolicitudAprobacionVirtual(showMsg);
                }
            }
        }else{
            LOGGER.severe("Error al obtener el Tipo de Reunion");
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacion",
                       msgError);
        
        return msgError;
    }
    
    /**
     * Crea la solicitud de aproibacion de nivel presidencial 
     * @param showMsg indicador para mostrar mensajes
     * @return devuelve cadena con mensaje de error null en caso de exito
     */
    @SuppressWarnings("unchecked")
    public String crearSolicitudAprobacionPresidencia(boolean showMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "crearSolicitudAprobacionPresidencia",
                        showMsg);
        
        String msgError = null;
        String msg = null;
        boolean isError = false;
        Long idOperacion = null;
        Integer tipoSolicitud = null;
        Integer idNivelAprobacion = null;
        Number idAccion = null;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        try{
            idOperacion = Long.parseLong(aprobacionBean.getIdOperacion());
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.severe("Error al obtener Id de Operacion", e);
        }
        
        tipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();
        
        try{
            idNivelAprobacion = Integer.parseInt(ADFUtils.getBoundAttributeValue(ID_NIVEL_APROB_ATRIBUTO).toString());    
        }catch(Exception e){
            LOGGER.severe("Error al obtener Id de Nivel Aprobacion", e);    
        }
        
        Date fechaApertura = null;
        Date fechaTermino = null;
        
        try{
            fechaApertura = (Date) getFechaInicioRevPresidenciaUIC().getValue();
            fechaTermino = (Date)getFechaCierreRevPresidenciaUIC().getValue();
        }catch(Exception e){
            LOGGER.severe("Error al obtener las fechas de la reunion de aprobacion", e);
        }
        
        if (null == fechaApertura || null == fechaTermino) {
            msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY);
            isError = true;
        }
        
        try {
            idAccion = new Number(ADFUtils.getBoundAttributeValue("IdAccionASeguir").toString());
        }catch(Exception e){
            LOGGER.severe("Error al obtener Id de Accion a Seguir", e);
            isError = true;
            msg = 
                getStringFromBundle("pc04aprobacion_revisar_solicitud_presidencia_msg_error_id_accion_no_encontrado");
        }
        
        String loginUsuario = null;
        
        //Obtiene nombre de usuario en session
        loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();
        
        LOGGER.fine("Obtiene Usuario: " + loginUsuario);

        Map values = new HashMap<String, Object>();
        values.put("idOperacion", idOperacion);
        values.put("loginUsuario", loginUsuario);
        values.put("tipoSolicitud", tipoSolicitud);
        values.put("idNivelAprobacion", idNivelAprobacion);
        values.put("fechaApertura", fechaApertura);
        values.put("fechaTermino", fechaTermino);
        values.put("idAccion", idAccion);
        
        Map params = new HashMap();
        params.put("params", values);
        
        LOGGER.warning(">>>>>>>>> idOperacion: " + idOperacion);
        LOGGER.warning(">>>>>>>>> loginUsuario: " + loginUsuario);
        LOGGER.warning(">>>>>>>>> tipoSolicitud: " + tipoSolicitud);
        LOGGER.warning(">>>>>>>>> idNivelAprobacion: " + idNivelAprobacion);
        LOGGER.warning(">>>>>>>>> fechaApertura: " + fechaApertura);
        LOGGER.warning(">>>>>>>>> fechaTermino: " + fechaTermino);

        OperationBinding oper = executeCrearSolicitudWS(params);
        if(oper != null && !isError){
            if(oper.getErrors().isEmpty()){
                Boolean exito = false;
                try{
                    exito = (Boolean)oper.getResult();    
                }catch(Exception e){
                    LOGGER.severe("Error al leer resultado de la operacion");
                }
                
                LOGGER.warning("Resultado del Operator Bindings: " + exito);
                
                if(Boolean.TRUE.equals(exito)){
                    msg = getStringFromBundle(MSG_INFO_CREAR_SOLICITUD_EXITO_BKEY);
                    isError = false;
                    
                    //Actualiza Payload
                    String strIdSolicitud = ADFUtils.getBoundAttributeValue("IdSolicitudAprobacion").toString();
                    actualizarPayloadElement("IdSolicitud", Integer.parseInt(strIdSolicitud));
                }else{
                    msgError = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY);
                    isError = true;
                }
            }else{
                msgError = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY) +
                      " . " + oper.getErrors().toString();
                isError = true;
            }
        }
        
        if(showMsg){
            if(isError){
                if(null != msgError){
                    JSFUtils.addFacesErrorMessage(msgError);
                }
            }else{
                if(null != msg){
                    JSFUtils.addFacesInformationMessage(msg);    
                }
            }
        }
        
        if(!isError){
            msgError = null;
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionPresidencia", 
                       msgError);
        
        return msgError;
    }
    
    /**
     * Ejecuta la creacion de la solicitud de aprobacion obteniendo todos los valores de la vista
     * para tipo de reunion presencial
     * @return devuelve cadena con mensaje de error, devuelve null en caso de exito
     */
    public String crearSolicitudAprobacionPresencial(boolean showMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "crearSolicitudAprobacionPresencial");
        
        String msgError = null;
        
        Long idOperacion = null;
        Integer tipoSolicitud = null;
        Integer idNivelAprobacion = null;
        String tipoReunion = null;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        try{
            idOperacion = Long.parseLong(aprobacionBean.getIdOperacion());
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.severe("Error al obtener Id de Operacion", e);
        }
        
        tipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();
        
        try{
            idNivelAprobacion = Integer.parseInt(ADFUtils.getBoundAttributeValue(ID_NIVEL_APROB_ATRIBUTO).toString());    
        }catch(Exception e){
            LOGGER.severe("Error al obtener Id de Nivel Aprobacion", e);    
        }
        
        tipoReunion = ADFUtils.getBoundAttributeValue(CVE_TIPO_REUNION_ATRIBUTO).toString();
        
        Date fecha = null;
        String lugar = null;
        Timestamp time = null;
        String strTime = null;
        Date fechaTmp = null;
        SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm");

        fecha = (Date) getFechaRPresencial().getValue();
        strTime = getHoraRPresencial().getValue().toString();
        try {
            fechaTmp = formatDate.parse(strTime);
            time = new Timestamp(fechaTmp.getTime());
        } catch (ParseException e) {
            LOGGER.severe("Error al convertir fecha: " + strTime, e);
        }
        
        lugar = (String) getLugarRPresencial().getValue();
        
        //Ejecuta la creacion de la solicitud de aprobacion con Reunion Presencial
        msgError = 
            crearSolicitudAprobacionPresencial(fecha, 
                                               time, 
                                               lugar,
                                               idOperacion,
                                               tipoSolicitud,
                                               idNivelAprobacion,
                                               showMsg);
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionPresencial",
                       msgError);
        
        return msgError;
    }

    /**
     * Crea una solicitud de aprobacion con Reunion Presencial
     * @param fecha contiene fecha de reaunion
     * @param hora contiene hora de la reunion
     * @param lugar  contiene el lugar de la reunion
     * @return devuelve cadena con mensaje de error, devuelve null en caso de exito
     */
    @SuppressWarnings("unchecked")
    public String crearSolicitudAprobacionPresencial(Date fecha, 
                                                     Timestamp hora, 
                                                     String lugar,
                                                     Long idOperacion,
                                                     Integer tipoSolicitud,
                                                     Integer idNivelAprobacion,
                                                     boolean showMsg) {
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "crearSolicitudAprobacionPresencial", 
                        new Object[]{fecha, 
                                     hora, 
                                     lugar, 
                                     idOperacion, 
                                     tipoSolicitud, 
                                     idNivelAprobacion,
                                     showMsg});
        
        String msg = null;
        boolean isError = false;
        
        if (null == fecha || null == hora || null == lugar || lugar.isEmpty()) {

            msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY);
            isError = true;
        }
        
        String loginUsuario = null;
        
        //Obtiene nombre de usuario en session
        loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();

        Map values = new HashMap<String, Object>();
        values.put("tipoReunion", 1);
        values.put("idOperacion", idOperacion);
        values.put("loginUsuario", loginUsuario);
        values.put("tipoSolicitud", tipoSolicitud);
        values.put("idNivelAprobacion", idNivelAprobacion);
        values.put("fecha", fecha);
        values.put("hora", hora);
        values.put("lugar", lugar);
        
        Map params = new HashMap();
        params.put("params", values);
        
        LOGGER.warning(">>>>>>>>> idOperacion: " + idOperacion);
        LOGGER.warning(">>>>>>>>> loginUsuario: " + loginUsuario);
        LOGGER.warning(">>>>>>>>> tipoSolicitud: " + tipoSolicitud);
        LOGGER.warning(">>>>>>>>> idNivelAprobacion: " + idNivelAprobacion);
        LOGGER.warning(">>>>>>>>> fecha: " + fecha);
        LOGGER.warning(">>>>>>>>> hora: " + hora);
        LOGGER.warning(">>>>>>>>> lugar: " + lugar);

        OperationBinding oper = executeCrearSolicitudWS(params);
        if(null != oper && !isError){
            if(oper.getErrors().isEmpty()){
                Boolean exito = false;
                try{
                    exito = (Boolean)oper.getResult();    
                }catch(Exception e){
                    LOGGER.severe("Error al leer resultado de la operacion");
                }
                
                LOGGER.warning("Resultado del Operator Bindings: " + exito);
                
                if(Boolean.TRUE.equals(exito)){
                    msg = getStringFromBundle(MSG_INFO_CREAR_SOLICITUD_EXITO_BKEY);
                    isError = false;
                    
                    //Actualiza Payload
                    String strIdSolicitud = ADFUtils.getBoundAttributeValue("IdSolicitudAprobacion").toString();
                    actualizarPayloadElement("IdSolicitud", Integer.parseInt(strIdSolicitud));
                    actualizarPayloadElement("reunionPresencial", true);
                }else{
                    msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY);
                    isError = true;
                }
            }else{
                msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY) + 
                      ". Errors: " + oper.getErrors().toString();
                isError = true;
            }
        }
        
        if(showMsg){
            if(isError){
                if(null != msg){
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }else{
                if(null != msg){
                    JSFUtils.addFacesInformationMessage(msg);    
                }
            }
        }
        
        if(!isError){
            msg = null;
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionPresencial", 
                       msg);
        return msg;
    }
    
    /**
     * Ejecuta la creacion de la solicitud de aprobacion obteniendo todos los valores de la vista
     * para tipo de reunion virtual
     * @param showMsg indicador para mostrar mensaje
     * @return devuelve cadena con mensaje de error o null en caso de exito
     */
    public String crearSolicitudAprobacionVirtual(boolean showMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "crearSolicitudAprobacionVirtual");
        
        String msgError = null;
        Long idOperacion = null;
        Integer tipoSolicitud = null;
        Integer idNivelAprobacion = null;
        String tipoReunion = null;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        try{
            idOperacion = Long.parseLong(aprobacionBean.getIdOperacion());
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.severe("Error al obtener Id de Operacion", e);
        }
        
        tipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();
        
        try{
            idNivelAprobacion = Integer.parseInt(ADFUtils.getBoundAttributeValue(ID_NIVEL_APROB_ATRIBUTO).toString());    
        }catch(Exception e){
            LOGGER.severe("Error al obtener Id de Nivel Aprobacion", e);    
        }
        
        tipoReunion = ADFUtils.getBoundAttributeValue(CVE_TIPO_REUNION_ATRIBUTO).toString();
        
        Date fechaInicio = null;
        Date fechaTermino = null;

        fechaInicio = (Date) getFechaAperturaRVirtual().getValue();
        fechaTermino = (Date) getFechaCierreRVirtual().getValue(); //Fecha cierre en pantalla, fecha termino en BD
        
        msgError = crearSolicitudAprobacionVirtual(fechaInicio, 
                                                   fechaTermino,
                                                   idOperacion,
                                                   tipoSolicitud,
                                                   idNivelAprobacion, 
                                                   showMsg);
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionVirtual", 
                       msgError);
        
        return msgError;
    }

    /**
     * Crea una solicitud de aprobacion con reunion Virtual
     * @param fechaApertura contiene fecha de inicio
     * @param fechaTermino contiene fecha de cierre
     * @param idOperacion contiene id de la operacion
     * @param tipoSolicitud contiene id del tipo de solicitud
     * @param idNivelAprobacion contiene id del nivel de aprobacion
     * @param showMsg contiene bandera para indicar si se desea mostrar mensajes de dialogo
     * @return devuelve cadena con mensaje de error, null si la operacion es exitosa
     */
    @SuppressWarnings("unchecked")
    public String crearSolicitudAprobacionVirtual(Date fechaApertura, 
                                                Date fechaTermino,
                                                Long idOperacion,
                                                Integer tipoSolicitud,
                                                Integer idNivelAprobacion,
                                                boolean showMsg) {
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "crearSolicitudAprobacionVirtual",
                        new Object[]{fechaApertura, 
                                     fechaTermino, 
                                     idOperacion, 
                                     tipoSolicitud, 
                                     idNivelAprobacion,
                                     showMsg});
        
        String msg = null;
        boolean isError = false;
        
        if (null == fechaApertura || null == fechaTermino) {
            msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY);
            isError = true;
        }
        
        String loginUsuario = null;
        
        //Obtiene nombre de usuario en session
        loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();
        
        LOGGER.fine("Obtiene Usuario: " + loginUsuario);

        Map values = new HashMap<String, Object>();
        values.put("tipoReunion", 2);
        values.put("idOperacion", idOperacion);
        values.put("loginUsuario", loginUsuario);
        values.put("tipoSolicitud", tipoSolicitud);
        values.put("idNivelAprobacion", idNivelAprobacion);
        values.put("fechaApertura", fechaApertura);
        values.put("fechaTermino", fechaTermino);
        
        Map params = new HashMap();
        params.put("params", values);
        
        LOGGER.warning(">>>>>>>>> idOperacion: " + idOperacion);
        LOGGER.warning(">>>>>>>>> loginUsuario: " + loginUsuario);
        LOGGER.warning(">>>>>>>>> tipoSolicitud: " + tipoSolicitud);
        LOGGER.warning(">>>>>>>>> idNivelAprobacion: " + idNivelAprobacion);
        LOGGER.warning(">>>>>>>>> fechaApertura: " + fechaApertura);
        LOGGER.warning(">>>>>>>>> fechaTermino: " + fechaTermino);

        OperationBinding oper = executeCrearSolicitudWS(params);
        if(oper != null && !isError){
            if(oper.getErrors().isEmpty()){
                Boolean exito = false;
                try{
                    exito = (Boolean)oper.getResult();    
                }catch(Exception e){
                    LOGGER.severe("Error al leer resultado de la operacion");
                }
                
                LOGGER.warning("Resultado del Operator Bindings: " + exito);
                
                if(Boolean.TRUE.equals(exito)){
                    msg = getStringFromBundle(MSG_INFO_CREAR_SOLICITUD_EXITO_BKEY);
                    isError = false;
                    
                    //Actualiza Payload
                    String strIdSolicitud = ADFUtils.getBoundAttributeValue("IdSolicitudAprobacion").toString();
                    actualizarPayloadElement("IdSolicitud", Integer.parseInt(strIdSolicitud));
                    actualizarPayloadElement("reunionPresencial", false);
                }else{
                    msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY);
                    isError = true;
                }
            }else{
                msg = getStringFromBundle(MSG_ERROR_CREAR_SOLICITUD_APROB_BKEY) +
                      " . " + oper.getErrors().toString();
                isError = true;
            }
        }
        
        if(showMsg){
            if(isError){
                if(null != msg){
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }else{
                if(null != msg){
                    JSFUtils.addFacesInformationMessage(msg);    
                }
            }
        }
        
        if(!isError){
            msg = null;
        }

        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "crearSolicitudAprobacionVirtual",
                       msg);
        return msg;
    }

    /**
     * Ejecuta Operator Bindings para Crear la Solicitud
     * @param params contiene mapa de parametros
     * @return devuelve objeto Operator Binding
     */
    private OperationBinding executeCrearSolicitudWS(Map params) {

        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "executeCrearSolicitudWS", 
                        params);
        
        OperationBinding oper = executeOperBinding(params, CREAR_SOLICITUD_OPER);
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "executeCrearSolicitudWS",
                       oper);
        return oper;
    }
    
    /**
     * Ejecuta el guardado de los datos para Consolidar Decision de Solicitud de aprobacion del
     * comite de credito
     * @param shoMsg indicador para mostrar mensajes o no
     * @return devuelve cadena con mensaje de error o null en caso de exito
     */
    @SuppressWarnings("unchecked")
    public String guardarDatosConsolidarDecision(boolean showMsg, String tipo_ejecucion){
        LOGGER.warning("Inside guardarDatosConsolidarDecision.");
        
        String msgError = null;
        Number idSolicitudAprob = null;
        Number idTipoAccion = null;
        String numActa = null;
        String numAcuerdo = null;
        String acuerdo = null;
        String userName = null;
        String tipoAccion = null;
        //Long idDecisionReunionAprobacion = null;
        Boolean esActualizar = null;
        String idDecisionReunionEnPageDef = null;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        if(null != aprobacionBean){
            try {
                idSolicitudAprob = new Number(aprobacionBean.getIdSolicitud());
                tipoAccion = aprobacionBean.getTipoAccion();
            } catch (SQLException e) {
                LOGGER.severe("Error al obtener Id Solicitud", e);
            }
        }
        
        idTipoAccion = 
            (Number)ADFUtils.getBoundAttributeValue(ID_ACCION_A_SEGUIR_ATRIBUTO) ;
        
        
        if(tipo_ejecucion.equals("por_operacion")){
            idDecisionReunionEnPageDef = "Id";
        }
        if(tipo_ejecucion.equals("por_cliente")){
            idDecisionReunionEnPageDef = "Id2";
        }
        
        //validar si se tiene un registro en  DecisionReunionAprobacionVO
        //Si exite un id actualizar registro, de lo contrario insertarlo
        if(null != ADFUtils.getBoundAttributeValue(idDecisionReunionEnPageDef)){
            //idDecisionReunionAprobacion = (Long) ADFUtils.getBoundAttributeValue("Id");
            LOGGER.warning("idDecisionReunionAprobacion: " + ADFUtils.getBoundAttributeValue(idDecisionReunionEnPageDef));
            LOGGER.warning("actualizar registro");
            esActualizar = Boolean.TRUE;
        }else{
            LOGGER.warning("idDecisionReunionAprobacion es nulo");
            LOGGER.warning("insertar registro");
            esActualizar = Boolean.FALSE;
        }
                
        numActa = (String) getNumActaUIC().getValue();
        numAcuerdo = (String) getNumAcuerdoUIC().getValue();
        acuerdo = (String) getAcuerdoUIC().getValue();
        
        //Obtiene nombre de usuario en session
        userName = ADFContext.getCurrent().getSecurityContext().getUserName();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idSolicitudAprob", idSolicitudAprob);
        params.put("idTipoAccion", idTipoAccion);
        params.put("numActa", numActa);
        params.put("numAcuerdo", numAcuerdo);
        params.put("acuerdo", acuerdo);
        params.put("userName", userName);
        
        LOGGER.warning(">>>> idSolicitudAprob: " + idSolicitudAprob);
        LOGGER.warning(">>>> idTipoAccion: " + idTipoAccion);
        LOGGER.warning(">>>> numActa: " + numActa);
        LOGGER.warning(">>>> numAcuerdo: " + numAcuerdo);
        LOGGER.warning(">>>> acuerdo: " + acuerdo);
        LOGGER.warning(">>>> userName: " + userName);
        LOGGER.warning(">>>> tipoAccion: " + tipoAccion);
        
        OperationBinding oper = null;
        
        LOGGER.warning("esActualizar :"+esActualizar);
        
        if(MAS_INFORMACION.equalsIgnoreCase(tipoAccion) || esActualizar){
            LOGGER.warning("Execute methodAction :"+ACTUALIZAR_CONSOLIDAR_DECISION_OPER);
            oper = 
                executeOperBinding(params, ACTUALIZAR_CONSOLIDAR_DECISION_OPER);   
        } else {
            LOGGER.warning("Execute methodAction :"+GUARDAR_CONSOLIDAR_DECISION_OPER);
            oper = 
                executeOperBinding(params, GUARDAR_CONSOLIDAR_DECISION_OPER);
        }
        
        if(null != oper){
            if(oper.getErrors().isEmpty() &&
               null != oper.getResult()){
                Boolean esExito = null;
                try{
                    esExito = (Boolean) oper.getResult();
                }catch(Exception e){
                    LOGGER.warning("Error al obtener resultado de la operacion", e);
                }
                
                if(null != esExito){
                    if(esExito){
                        //Puede mostrar mensaje de exito
                        LOGGER.warning("Exito al guardar el registro valor esExito => " + esExito);
                    }else{
                        LOGGER.warning("esExito is false => " + esExito);
                        //Muestra mensaje de fallo al guardar el registro
                        msgError = 
                            getStringFromBundle(MSG_ERROR_GUARDAR_CONSOLIDAR_DECISION_BKEY);
                    }
                }else{
                    LOGGER.warning("Datos no guardados desde el Modelo variable isExito Nula");
                    //Muestra mensaje de fallo al guardar el registro
                    msgError = 
                        getStringFromBundle(MSG_ERROR_GUARDAR_CONSOLIDAR_DECISION_BKEY);
                }
            }else {
                LOGGER.warning("Datos no guardados desde el Modelo operation binding sin errores o vacio");
                //Muestra mensaje de fallo al guardar el registro
                msgError = 
                    getStringFromBundle(MSG_ERROR_GUARDAR_CONSOLIDAR_DECISION_BKEY);    
            }
        }else{
            LOGGER.warning("Datos no guardados desde el Modelo operation binding nulo");
            //Muestra mensaje de fallo al guardar el registro
            msgError = 
                getStringFromBundle(MSG_ERROR_GUARDAR_CONSOLIDAR_DECISION_BKEY);   
        }
        
        if(showMsg){
            if(null != msgError){
                JSFUtils.addFacesErrorMessage(msgError);    
            }
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(),
                       "enviarDatosConsolidarDecision",
                       msgError);
        return msgError;
    }
    
    /**
     * Ejecuta un operator bindings
     * @param params contiene mapa de objetos de la operacion
     * @param operBindName contiene el nombre de la operacion
     * @return devuelve objeto Operator Bindings
     */
    @SuppressWarnings("unchecked")
    private OperationBinding executeOperBinding(Map params,
                                                String operBindName){
        
        LOGGER.entering(AprobacionActionBean.class.getName(),
                        "executeOperBinding",
                        new Object[]{params, operBindName});
        
        BindingContainer bindings = getBindings();
        OperationBinding oper = null;
        oper = bindings.getOperationBinding(operBindName);
        if (null != oper) {
            
            if(params != null){
                oper.getParamsMap().putAll(params);    
            }
            try {
                LOGGER.warning("EJECUTA METODO ".concat(operBindName));
                oper.execute();
            } catch (Exception e) {
                LOGGER.severe("Error al ejecutar Operator Bindings: " + operBindName, e);                
            }

            if (!oper.getErrors().isEmpty()) {
                LOGGER.severe("Operator Bindings devuelve errores: " + operBindName);
            }else{
                LOGGER.warning("Operator Bindings se ejecuto correctamente");
            }
        }else{
            LOGGER.severe("Error el operator Binding " + operBindName + 
                          " no fue encontrado");
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "executeOperBinding",
                       oper);
        return oper;
    }

    @SuppressWarnings("unchecked")
    public void tipoReunionChangeValue_Listener(ValueChangeEvent valueChangeEvent) { 
        
        UIComponent c = valueChangeEvent.getComponent();
        c.processUpdates(FacesContext.getCurrentInstance());

        String strValue = null;        
        try{
            strValue = 
                (String) JSFUtils.resolveExpression("#{bindings.idTipoReunionVarList.attributeValue}");
            //Previene la perdida de la seleccion por Issue de ShowDetailItem y Regiones
            ADFContext.getCurrent().getSessionScope().put(CVE_TIPO_REUNION_VAR_SESSION, strValue);
        }catch(Exception e){
            LOGGER.warning("Error al obtener valor de la Lista de Tipo Reunion", e);
        }

        if(strValue != null){
            LOGGER.fine("Asigna valor de Lista Tipo Reunion a Atributo Bindings");
            ADFUtils.setBoundAttributeValue(CVE_TIPO_REUNION_ATRIBUTO, strValue);
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelFormPresencialUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelFormVirtualUIC());
    }
    
    public void tipoDecisionOperChangeValue_Listener(ValueChangeEvent valueChangeEvent) { 
        LOGGER.warning("Entra en tipoDecisionOperChangeValue_Listener.");
        UIComponent c = valueChangeEvent.getComponent();
        c.processUpdates(FacesContext.getCurrentInstance());
        try{
            Integer idTcaAccionAux = (Integer)valueChangeEvent.getNewValue();
            Number idTcaAccion = new Number(idTcaAccionAux);
            cambiarValorTipoAccion(idTcaAccion);
        }catch(Exception e){
            LOGGER.warning("Error en tipoDecisionOperChangeValue_Listener.", e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelFormDecisionUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getNumActaLabelUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getNumActaUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getNumAcuerdoLabelUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getNumAcuerdoUIC());
    }
    
    private void cambiarValorTipoAccion(Number idTcaAccionSeguir){
        LOGGER.warning("Entra en cambiarValorTipoAccion.");
        LOGGER.warning("Valor tipo de accion a seguir : " + idTcaAccionSeguir);
        try{
            if(null != idTcaAccionSeguir){        
                LOGGER.warning("Entra a cambiar el valor del tipo de accion en los bindings.");
                
                AprobacionBean aprobacionBean = 
                        (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                
                aprobacionBean.setIdTipoAccion(idTcaAccionSeguir);
                ADFUtils.setBoundAttributeValue("IdTipoAccionVarAttr", idTcaAccionSeguir);
                ADFUtils.setBoundAttributeValue("IdTcaAccionASeguir", idTcaAccionSeguir);
                ADFUtils.findControlBinding("IdTipoAccionVarList").setInputValue(idTcaAccionSeguir);
                
                LOGGER.warning("IdTipoAccionVarAttr :"+ADFUtils.getBoundAttributeValue("IdTipoAccionVarAttr"));
                LOGGER.warning("IdTcaAccionASeguir :"+ADFUtils.getBoundAttributeValue("IdTcaAccionASeguir"));
                LOGGER.warning("IdTipoAccionVarList :"+ADFUtils.getBoundAttributeValue("IdTipoAccionVarList"));
                LOGGER.warning("IdTipoAccionVarAttr :"+ aprobacionBean.getIdTipoAccion());
                
                RowSetIterator iter = 
                    ADFUtils.getDCBindingContainer().findIteratorBinding("TcaAccionASeguirLOV1Iterator").getRowSetIterator();
                Number idAux = null;
                int index = 0;
                iter.reset();
                for(Row row : iter.getAllRowsInRange()){
                    idAux = (Number) row.getAttribute("Id");
                    if(idTcaAccionSeguir != null &&
                       idAux != null){
                        if(idTcaAccionSeguir.equals(idAux)){
                            aprobacionBean.setCodigoTipoAccion((String)row.getAttribute("Descripcion"));
                            break;
                        }
                    }
                    index = index + 1;
                }
                LOGGER.warning("codigoTipoAccion :"+aprobacionBean.getCodigoTipoAccion());
                LOGGER.warning("Configura lista como solo lectura");
            }else{
                LOGGER.warning("No entra a settear valores");
            }
        }catch(Exception e){
            LOGGER.warning("Error en cambiarValorTipoAccion.", e);
        }
    }

    @SuppressWarnings("unchecked")
    public String agregarComentario() {
        
        try{
            AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            String comentario = aprobacionBean.getComentario();
            String nombreUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();
            String idSolicitud = aprobacionBean.getIdSolicitud();
            String idRolBpm = aprobacionBean.getIdRolBPM();
            
            LOGGER.log(ADFLogger.ERROR, ">>>> IDROLBPM: " + idRolBpm);
            
            if(comentario != null){
                comentario = comentario.trim();
            }
            
            if(null != comentario && !comentario.equals("")){
                BindingContainer bindings = getBindings();
                OperationBinding operationBindings = bindings.getOperationBinding("guardarComentarios");
                operationBindings.getParamsMap().put("comentario", comentario);
                operationBindings.getParamsMap().put("nombreUsuario", nombreUsuario);
                operationBindings.getParamsMap().put("idSolicitud", idSolicitud);
                operationBindings.getParamsMap().put("idUsuarioReunion", null);
                operationBindings.getParamsMap().put("esComentarioVoto", false);
                operationBindings.getParamsMap().put("idVotoAprobacion", null);  
                operationBindings.getParamsMap().put("idRolBpm", idRolBpm);
                            
                Boolean result = (Boolean) operationBindings.execute();
                
                if(result == null){
                    result = (Boolean) operationBindings.getResult();
                }
                
                if(result != null){
                    if(result){
                        try{
                            LOGGER.warning("Se asigna valor bandera a indicador de agregar comentario");
                            ADFUtils.setBoundAttributeValue("emiteComentarioAttr", true);
                        }catch(Exception e){
                            LOGGER.warning("Asignacion de atributo no realizada", e);
                        }
                    }
                }
                
                aprobacionBean.setComentario("");
                
                if(null == result){
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_USUARIO_COMENTARIO"));
                }else if(!result){
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_AGREGAR_COMENTARIO"));
                }
            } else {
                if(comentario == null){
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_COMENTARIO_REQUERIDO"));    
                }else{
                    aprobacionBean.setComentario(null);
                    String msg = 
                        getStringFromBundle("pc04aprobacion_dar_seguimiento_msg_error_comentario_invalido");
                    JSFUtils.addFacesErrorMessage(msg);
                }
            }
        }catch(Exception e){
            LOGGER.severe("Error al agregar comentario", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public String guardarFechaTermino() {
        
        try{
            AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            String idSolicitud = aprobacionBean.getIdSolicitud();
            String idOperacion = aprobacionBean.getIdOperacion();
            
            Map<String,Object> mapAtributosReunion = aprobacionBean.getMapaAtributosSolicitud();
            String fechaTerminoString = mapAtributosReunion.get("FechaTerminoActual").toString();
            LOGGER.warning("Valor fechaTerminoString: " + fechaTerminoString);
            Timestamp fechaTerminoActual = null;
            if(fechaTerminoString != null){
                try{
                    fechaTerminoActual = Timestamp.toTimestamp(fechaTerminoString);        
                    LOGGER.warning("Valor fechaTerminoActual: " + fechaTerminoActual);
                }catch(Exception e){
                    LOGGER.severe("Error al procesar la fecha termino", e);
                }
            }
            
            AttributeBinding fechaTermino = null;
            fechaTermino = ADFUtils.findControlBinding("FechaTermino");
            
            Timestamp fechaTerminoNueva = null;
            if(fechaTermino != null){
                try{
                    fechaTerminoNueva = new Timestamp(fechaTermino.getInputValue().toString());        
                    LOGGER.warning("Valor fechaTerminoNueva: " + fechaTerminoNueva);
                }catch(Exception e){
                    LOGGER.severe("Error al procesar la nueva fecha termino", e);
                }
            }
            
            Date fechaNueva = null;
            Date fechaAnterior = null;
            Calendar calFechaNueva = null;
            Calendar calFechaAnterior = null;
                
            if(fechaTerminoNueva != null){
                try{
                    calFechaNueva = Calendar.getInstance();
                    calFechaNueva.setTime(fechaTerminoNueva.dateValue());
                    calFechaNueva.set(Calendar.HOUR_OF_DAY,0);
                    calFechaNueva.set(Calendar.MINUTE,0);
                    calFechaNueva.set(Calendar.SECOND,0);
                    calFechaNueva.set(Calendar.MILLISECOND,0);
                    fechaNueva = calFechaNueva.getTime();    
                    LOGGER.warning("Valor fechaNueva: " + fechaNueva);
                }catch(Exception e){
                    LOGGER.severe("Error al procesar la fecha termino nueva", e);    
                }
            }
            
            if(fechaTerminoActual != null){
                try{
                    calFechaAnterior = Calendar.getInstance();
                    calFechaAnterior.setTime(fechaTerminoActual.dateValue());
                    calFechaAnterior.set(Calendar.HOUR_OF_DAY,0);
                    calFechaAnterior.set(Calendar.MINUTE,0);
                    calFechaAnterior.set(Calendar.SECOND,0);
                    calFechaAnterior.set(Calendar.MILLISECOND,0);
                    fechaAnterior = calFechaAnterior.getTime();
                    LOGGER.warning("Valor fechaAnterior: " + fechaAnterior);
                }catch(Exception e){
                    LOGGER.severe("Error al procesar la fecha termino anterior", e);
                }
            }
            
            Boolean banderaFechasIguales = Boolean.FALSE;
            if(fechaNueva.toString().compareToIgnoreCase(fechaAnterior.toString()) == 0){
                banderaFechasIguales = Boolean.TRUE;
            }
            LOGGER.warning("Valor banderaFechasIguales: " + banderaFechasIguales);
            
            if(fechaNueva != null &&
               fechaAnterior != null){
                
                if(banderaFechasIguales){
                    Boolean resultado = null;
                    BindingContainer binding = getBindings();
                    OperationBinding operationBinding = binding.getOperationBinding("guardarNuevaFechaTerminoReunion");
                    operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                    operationBinding.getParamsMap().put("idOperacion", idOperacion);
                    operationBinding.getParamsMap().put("fechaTermino", fechaTermino.getInputValue().toString());
                    operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
                    resultado = (Boolean) operationBinding.execute();
                    
                    if(resultado == null){
                        resultado = (Boolean) operationBinding.getResult();
                    }

                    if(!resultado){
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_CAMBIO_FECHA_CIERRE"));
                    }else{
                        JSFUtils.addFacesInformationMessage(getStringFromBundle(MSG_INFO_FECHA_CIERRE_GUARDADA_BKEY));
                    }
                }
                else{
                    if( fechaNueva.compareTo(fechaAnterior) <= 0 ){
                        
                        //Restaura el valor original del Attribute Value del Modelo
                        ADFUtils.setBoundAttributeValue("FechaTermino", fechaTermino.getInputValue());
                        
                        if(getFechaTerminoUIC() != null){        
                            //Restaura el valor original en el UI Component de la pantalla
                            getFechaTerminoUIC().resetValue();
                            //Refresca UI Component
                            AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaTerminoUIC());                        
                            
                            //Aplica el formato coincidente en pantalla
                            SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
                            String strDate = null;
                            try{
                                strDate = format.format(fechaAnterior);    
                            }catch(Exception e){
                                LOGGER.severe("Error al formatear la fecha termino", e);
                                strDate = null;
                            }
                            
                            //Asigna el valor al UI Component
                            getFechaTerminoUIC().setValue(strDate);
                            //Refresca UI Component
                            AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaTerminoUIC());
                        }
                        
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_FECHA_INVALIDA"));
                    }else{
                        Boolean resultado = null;
                        BindingContainer binding = getBindings();
                        OperationBinding operationBinding = binding.getOperationBinding("guardarNuevaFechaTerminoReunion");
                        operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                        operationBinding.getParamsMap().put("idOperacion", idOperacion);
                        operationBinding.getParamsMap().put("fechaTermino", fechaTermino.getInputValue().toString());
                        operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
                        resultado = (Boolean) operationBinding.execute();
                        
                        if(resultado == null){
                            resultado = (Boolean) operationBinding.getResult();
                        }
    
                        if(!resultado){
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_CAMBIO_FECHA_CIERRE"));
                        }else{
                            JSFUtils.addFacesInformationMessage(getStringFromBundle(MSG_INFO_FECHA_CIERRE_GUARDADA_BKEY));
                        }
                    }
                }
            }else{
                JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_CAMBIO_FECHA_CIERRE"));
            }
        }catch(Exception e){
            LOGGER.severe("Error al guardar fecha de cierre", e);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public String guardarFechaTerminoCliente() {
        
        try{
            AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            String idSolicitud = aprobacionBean.getIdSolicitud();
            Long idCliente = aprobacionBean.getIdClienteFlex();
            
            Map<String,Object> mapAtributosReunion = aprobacionBean.getMapaAtributosSolicitud();
            String fechaTerminoString = mapAtributosReunion.get("FechaTerminoActual").toString();
            LOGGER.warning("Valor fechaTerminoString: " + fechaTerminoString);
            
            Timestamp fechaTerminoActual = null;
            if(fechaTerminoString != null){
                try{
                    fechaTerminoActual = Timestamp.toTimestamp(fechaTerminoString);        
                    LOGGER.warning("Valor fechaTerminoActual: " + fechaTerminoActual);
                }catch(Exception e){
                    LOGGER.severe("Error al procesar la fecha termino", e);
                }
            }
            
            AttributeBinding fechaTermino = null;
            fechaTermino = ADFUtils.findControlBinding("FechaTermino");
            
            Timestamp fechaTerminoNueva = null;
            if(fechaTermino != null){
                try{
                    fechaTerminoNueva = new Timestamp(fechaTermino.getInputValue().toString());        
                    LOGGER.warning("Valor fechaTerminoNueva: " + fechaTerminoNueva);
                }catch(Exception e){
                    LOGGER.severe("Error al procesar la nueva fecha termino", e);
                }
            }
            
            Date fechaNueva = null;
            Date fechaAnterior = null;
            Calendar calFechaNueva = null;
            Calendar calFechaAnterior = null;
                
            if(fechaTerminoNueva != null){
                try{
                    calFechaNueva = Calendar.getInstance();
                    calFechaNueva.setTime(fechaTerminoNueva.dateValue());
                    calFechaNueva.set(Calendar.HOUR_OF_DAY,0);
                    calFechaNueva.set(Calendar.MINUTE,0);
                    calFechaNueva.set(Calendar.SECOND,0);
                    calFechaNueva.set(Calendar.MILLISECOND,0);
                    fechaNueva = calFechaNueva.getTime();    
                    LOGGER.warning("Valor fechaNueva: " + fechaNueva);
                }catch(Exception e){
                    LOGGER.severe("Error al procesar la fecha termino nueva", e);    
                }
            }
            
            if(fechaTerminoActual != null){
                try{
                    calFechaAnterior = Calendar.getInstance();
                    calFechaAnterior.setTime(fechaTerminoActual.dateValue());
                    calFechaAnterior.set(Calendar.HOUR_OF_DAY,0);
                    calFechaAnterior.set(Calendar.MINUTE,0);
                    calFechaAnterior.set(Calendar.SECOND,0);
                    calFechaAnterior.set(Calendar.MILLISECOND,0);
                    fechaAnterior = calFechaAnterior.getTime();
                    LOGGER.warning("Valor fechaAnterior: " + fechaAnterior);
                }catch(Exception e){
                    LOGGER.severe("Error al procesar la fecha termino anterior", e);
                }
            }
            
            Boolean banderaFechasIguales = Boolean.FALSE;
            if(fechaNueva.toString().compareToIgnoreCase(fechaAnterior.toString()) == 0){
                banderaFechasIguales = Boolean.TRUE;
            }
            LOGGER.warning("Valor banderaFechasIguales: " + banderaFechasIguales);
            
            if(fechaNueva != null &&
               fechaAnterior != null){
                
                if(banderaFechasIguales){
                    Boolean resultado = null;
                    BindingContainer binding = getBindings();
                    OperationBinding operationBinding = binding.getOperationBinding("guardarNuevaFechaTerminoReunionCliente");
                    operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                    operationBinding.getParamsMap().put("idCliente", idCliente);
                    operationBinding.getParamsMap().put("fechaTermino", fechaTermino.getInputValue().toString());
                    operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
                    resultado = (Boolean) operationBinding.execute();
                    
                    if(resultado == null){
                        resultado = (Boolean) operationBinding.getResult();
                    }

                    if(!resultado){
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_CAMBIO_FECHA_CIERRE"));
                    }else{
                        JSFUtils.addFacesInformationMessage(getStringFromBundle(MSG_INFO_FECHA_CIERRE_GUARDADA_BKEY));
                    }
                }
                else{
                    if(fechaNueva.compareTo(fechaAnterior) <= 0){
                        
                        //Restaura el valor original del Attribute Value del Modelo
                        ADFUtils.setBoundAttributeValue("FechaTermino", fechaTermino.getInputValue());
                        
                        if(getFechaTerminoUIC() != null){        
                            //Restaura el valor original en el UI Component de la pantalla
                            getFechaTerminoUIC().resetValue();
                            //Refresca UI Component
                            AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaTerminoUIC());                        
                            
                            //Aplica el formato coincidente en pantalla
                            SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
                            String strDate = null;
                            try{
                                strDate = format.format(fechaAnterior);    
                            }catch(Exception e){
                                LOGGER.severe("Error al formatear la fecha termino", e);
                                strDate = null;
                            }
                            
                            //Asigna el valor al UI Component
                            getFechaTerminoUIC().setValue(strDate);
                            //Refresca UI Component
                            AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaTerminoUIC());
                        }
                        
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_FECHA_INVALIDA"));
                    }else{
                        Boolean resultado = null;
                        BindingContainer binding = getBindings();
                        OperationBinding operationBinding = binding.getOperationBinding("guardarNuevaFechaTerminoReunionCliente");
                        operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                        operationBinding.getParamsMap().put("idCliente", idCliente);
                        operationBinding.getParamsMap().put("fechaTermino", fechaTermino.getInputValue().toString());
                        operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
                        resultado = (Boolean) operationBinding.execute();
                        
                        if(resultado == null){
                            resultado = (Boolean) operationBinding.getResult();
                        }
    
                        if(!resultado){
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_CAMBIO_FECHA_CIERRE"));
                        }else{
                            JSFUtils.addFacesInformationMessage(getStringFromBundle(MSG_INFO_FECHA_CIERRE_GUARDADA_BKEY));
                        }
                    }
                }
            }else{
                JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_CAMBIO_FECHA_CIERRE"));
            }
        }catch(Exception e){
            LOGGER.severe("Error al guardar fecha de cierre", e);
        }
        return null;
    }
    
    public void cambiarFechaInicioValueChangeListener(ValueChangeEvent event){
        LOGGER.warning("Inicia metodo cambiarFechaInicioValueChangeListener");
        
        if(event == null){
            return;
        }
        
        event.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        java.sql.Timestamp nuevaFecha = null;
        java.sql.Timestamp anteriorFecha = null;
        java.sql.Timestamp fechaActual = null;
        
        nuevaFecha = (java.sql.Timestamp) event.getNewValue();
        anteriorFecha = (java.sql.Timestamp) event.getOldValue();
        
        LOGGER.warning("Fecha anterior: " + anteriorFecha + ", nueva fecha: " + nuevaFecha);
        Calendar calFechaActual = Calendar.getInstance();
        calFechaActual.set(Calendar.HOUR_OF_DAY,0);
        calFechaActual.set(Calendar.MINUTE,0);
        calFechaActual.set(Calendar.SECOND,0);
        calFechaActual.set(Calendar.MILLISECOND,0);
        try{
            fechaActual = new java.sql.Timestamp(calFechaActual.getTime().getTime());    
        }catch(Exception e){
            LOGGER.severe("Error al convertir fecha actual en objeto Timestamp", e);
        }
        
        if(nuevaFecha.compareTo(fechaActual) < 0){
            
            //Restaura fecha de reunion en el modelo
            ADFUtils.setBoundAttributeValue("FechaInicio", anteriorFecha);
            
            //Restaura fecha de reunion en UI Component de pantalla            
            if(getFechaRPresencial() != null){
                try{
                    getFechaRPresencial().resetValue();
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaRPresencial());
    
                    getFechaRPresencial().setValue(anteriorFecha);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaRPresencial());
                }catch(Exception e){
                    LOGGER.warning("Error al refrescar componente de Fecha inicio presencial.", e);
                }
            }
            
            ADFUtils.setBoundAttributeValue("esNuevaFechaInicioAttrValue", false);    
            JSFUtils.addFacesErrorMessage(getStringFromBundle(MSG_ERROR_FECHA_SISTEMA));

        }else{
            ADFUtils.setBoundAttributeValue("esNuevaFechaInicioAttrValue", true);    
        }
        
        LOGGER.warning("Termina metodo cambiarFechaInicioValueChangeListener");
    }

    @SuppressWarnings("unchecked")
    public void votoFuera(ActionEvent actionEvent){
        
        if(actionEvent == null){
            return;
        }

        boolean esVotoPermitido = false;
        esVotoPermitido = validaVotoPorRangoFecha("FechaInicio",
                                                  "FechaTermino",
                                                  true);
        
        if(esVotoPermitido){
            oracle.adf.view.rich.util.ResetUtils.reset(getPopupAgregarModificarVoto());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupAgregarModificarVoto());
            
            AprobacionBean aprobacionBean =
                (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            
            String idSolicitud = aprobacionBean.getIdSolicitud();
            String idRolBpm = aprobacionBean.getIdRolBPM();
            
            //Se inicializan los VO's para VotoAprobacionVO y ComentarioReunionVO
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("inicializarAgregarVotoFueraSistema");
            operationBinding.getParamsMap().put("usuarioLogin", ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.getParamsMap().put("idRolBpm", idRolBpm);
            operationBinding.execute();
            
            try{
                operationBinding = binding.getOperationBinding("buscarPorIdSolicitud");
                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                operationBinding.getParamsMap().put("emiteVoto", 1);
                operationBinding.execute();     
            }catch(Exception e){
                e.printStackTrace();
            }
            
            aprobacionBean.setTituloPopup(getStringFromBundle("LBL_AGREGAR_VOTO_FUERA_DE_SISTEMA"));
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupAgregarModificarVoto().show(popupHints);
        }
    }
    /**
     * Guarda la fecha de termino por parte de Presidencia
     * @param actionEvent contiene evento
     */
    public void guardarFechaTerminoPresidencia(ActionEvent actionEvent){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "guardarFechaTerminoPresidencia");
        
        if(actionEvent == null){
            return;
        }
        Date fechaTermino = (Date) ADFUtils.getBoundAttributeValue("FechaTermino");
        LOGGER.fine("Valida fecha de termino");
        this.validaFechasCierreYTermino(fechaTermino);
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "guardarFechaTerminoPresidencia");
    }
    
    /**
     * Guarda la fecha de cierre por parte del Secretario
     * @param actionEvent contiene evento
     */
    public void guardarFechaCierre(ActionEvent actionEvent){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "guardarFechaCierre");
        
        if(actionEvent == null){
            return;
        }
        Date fechaCierre = (Date) ADFUtils.getBoundAttributeValue("FechaCierre");
        LOGGER.fine("Valida fecha de cierre");
        this.validaFechasCierreYTermino(fechaCierre);
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "guardarFechaCierre");
    }
    
    private void validaFechasCierreYTermino(Date fecha) {
        LOGGER.warning("** entra validaFechasCierreYTermino");
        String msg = null;
        boolean exito = validaFechaTerminoPresidencia(fecha);
        if(exito){
            
            //Registra usuario que realiza la operacion
            String usuario = ADFContext.getCurrent().getSecurityContext().getUserName();
            ADFUtils.setBoundAttributeValue("LoginUsuarioCierre", usuario);
            
            exito = ejecutaOperCommit();
            if(!exito){
                LOGGER.warning("Muestra mensaje de error al guardar fecha de cierre");
                //Obtiene mensaje de error al guardar fecha
                msg = getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_fecha_cierre_msg_error_guardar");
                JSFUtils.addFacesErrorMessage(msg);
            }else{
                LOGGER.warning("Muestra mensaje de exito al guardar fecha de cierre");
                //Obtiene mensaje de exito al guardar fecha
                msg = getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_fecha_cierre_guardada");
                JSFUtils.addFacesInformationMessage(msg);
            }
        }
        LOGGER.warning("** termina validaFechasCierreYTermino");
    }
    
    /**
     * Valida fecha de termino en la Revision de Presidencia y Dar seguimiento a decision presidente
     * @param fechaTermino contiene fecha de termino
     * @return devuelve true si la validacion es satisfactoria o false en caso contrario
     */
    public boolean validaFechaTerminoPresidencia(Date fechaTermino){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validaFechaInicioFin", 
                        fechaTermino);
        boolean esValida = false;
        
        Date fechaInicio = null;
        fechaInicio = (Date) ADFUtils.getBoundAttributeValue("FechaInicio");
        
        if(fechaInicio != null){
            if(fechaTermino != null){
                
                esValida = validaFechaFin(fechaInicio, fechaTermino);
                if(!esValida){
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_FECHA_INVALIDA"));
                }
            }else{
                LOGGER.severe("Fecha de cierre no recibida");
            }
        }else{
            LOGGER.severe("Fecha de Inicio no encontrada");
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validaFechaInicioFin", 
                       esValida);
        return esValida;
    }
    
    /**
     * Valida una fecha con respecto a la fecha actual y opcionalmente a 
     * una fecha inicio
     * @param fechaInicio contiene fecha inicio
     * @param fechaFin contiene fecha fin
     * @return devuelve true si la validacion es correcta o false en caso contrario
     */ 
    private boolean validaFechaFin(Date fechaInicio, 
                                   Date fechaFin){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validaFechaInicioFin", 
                        new Object[]{fechaInicio, fechaFin});
        boolean esValido = false;
        
        if(fechaFin != null){
            Date fechaActual = getCurrentDate();
            if(fechaActual.compareTo(fechaFin) < 0){
                if(fechaInicio != null){
                    if(fechaInicio.compareTo(fechaFin) < 0){
                        esValido = true;
                    }
                }else{
                    esValido = true;
                }
            }
        }
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validaFechaInicioFin", 
                       esValido);
        return esValido;
    }
    
    /**
     * Ejecuta el Operator Binding Commit para realizar guardar las modificaciones realizadas
     * @return devuelve valor booleano, true si la ejecucion fue correcta o false en caso 
     *         contrario
     */
    public boolean ejecutaOperCommit(){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "ejecutaOperCommit");
        
        boolean exito = false;
        
        BindingContainer bindings = getBindings();
        OperationBinding oper = null;
        oper = bindings.getOperationBinding("Commit");
        if(oper != null){
            boolean ejecutaRollback = false;
            try{
                oper.execute();
            }catch(Exception e){
                LOGGER.severe("Error al ejecutar Commit", e);
                ejecutaRollback = true;
            }
            
            if(!oper.getErrors().isEmpty()){
                
                LOGGER.severe("Commit ejecutado con errores");
                
                oper = null;
                oper = bindings.getOperationBinding("Rollback");
                
                if(oper != null){
                    try{
                        oper.execute();
                    }catch(Exception e){
                        LOGGER.severe("Error al ejecutar Rollback", e);
                    }
                    
                    if(!oper.getErrors().isEmpty()){
                        LOGGER.severe("Rollback ejecutado con errores");
                    }
                }else{
                    LOGGER.severe("Error Operacion Rollback no encontrada");
                }
            }else{
                exito = true;
            }
        }else{
            LOGGER.severe("Error Operacion Commit no encontrada");
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "ejecutaOperCommit", 
                       exito);
        return exito;
    }

    public String cerrarVotacion() {
        // Add event code here...
        return null;
    }

    public String cambiarTipoReunion() {
        // Add event code here...
        return null;
    }

    public String invocarMasInformacion() { 
        //variable para validar si se agrego un comentario
        Boolean esComentarioValido = Boolean.FALSE;
        
        LOGGER.warning("Inicia proceso de Invocar Mas Informacion");
        
        List<String> budleKeyErroList = new ArrayList<String>();
      
        Boolean isValidSolicitarMasInformacion = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();
        boolean esComentario = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.PC04_REVISARSOLICITUDCC:
                LOGGER.warning("Aplica validacion para tarea PC04_REVISARSOLICITUDCC");
                esComentario = 
                    validaComentarioOperacion(FenixConstants.PC04_REVISARSOLICITUDCC, true);
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }
            break;
            case FenixConstants.PC04_DARSEGUIMIENTOVOTACION:
                LOGGER.warning("Aplica validacion para tarea PC04_DARSEGUIMIENTOVOTACION");
                isValidSolicitarMasInformacion = Boolean.TRUE;
            break;
            case FenixConstants.PC04_CONSOLIDARDECISION:
            case FenixConstants.PC04_REALIZARMODIFICACIONES:
                LOGGER.warning("Aplica validacion para tareas: PC04_CONSOLIDARDECISION, PC04_MODIFICARPROYECTORESOLUCION");
                //Validar Comentarios
                Boolean isRNValid = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                if(!isRNValid)
                { 
                    budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
                }
                else
                {            
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }
            break;
            case FenixConstants.PC04_MODIFICARPROYECTORESOLUCION:
            break;
            case FenixConstants.PC04_REVISARSOLICITUDPRESIDENTE:
                LOGGER.warning("Aplica validacion para la tarea PC04_REVISARSOLICITUDPRESIDENTE");
                esComentario = 
                    validaComentarioOperacion(FenixConstants.PC04_REVISARSOLICITUDPRESIDENTE, true);
                if(esComentario){
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }
            break;
            case FenixConstants.PC04_DARSEGUIMIENTODECISIONPRESIDENTE:
            break;
            case FenixConstants.PC04_CERTIFICARDECISION:
            break;
            case FenixConstants.PC04_CONSOLIDARDECISIONDIRECTORIO:
            LOGGER.warning("Aplica validacion para tarea PC04_CONSOLIDARDECISIONDIRECTORIO");
            esComentario = 
                validaComentarioOperacion(FenixConstants.PC04_CONSOLIDARDECISIONDIRECTORIO, true);
            if(esComentario){
                isValidSolicitarMasInformacion = Boolean.TRUE;
            }
            break;
            case FenixConstants.PC04_ACTUALIZARTCC:
            case FenixConstants.PC04_COMPLETARTCC:
            break;
            case 135:
                isValidSolicitarMasInformacion = Boolean.TRUE;
                break;
            case FenixConstants.PC04_VALIDARTCC:
            LOGGER.warning("PC04ValidarTCC");
            
            Boolean comment = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            if(!comment)
            { 
                budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
            }
            else
            {            
                isValidSolicitarMasInformacion = Boolean.TRUE;
            }
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
    
    public String invocarMasInformacionCliente() { 
        //variable para validar si se agrego un comentario
        Boolean esComentarioValido = Boolean.FALSE;
        
        LOGGER.warning("Inicia proceso de Invocar Mas Informacion");
        
        List<String> budleKeyErroList = new ArrayList<String>();
      
        Boolean isValidSolicitarMasInformacion = Boolean.FALSE;
        
        int codigoTarea = getCodigoTarea();
        boolean esComentario = false;
        
        switch (codigoTarea)
        {
            case FenixConstants.PC04_ELEVAR_SOLICITUD_APROBACION:
                esComentario = 
                    validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea()) == null ?
                    false : 
                    validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea());
            
                if(!esComentario) 
                {
                    budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
                }
                else
                {
                    isValidSolicitarMasInformacion = Boolean.TRUE;
                }
            break; 
            case FenixConstants.PC04_REVISAR_SOLICITUD_COMITE:
            LOGGER.entering(AprobacionActionBean.class.getName(), "CASE REVISAR SOLICITUD A COMITE DE CREDITO : " 
                                +FenixConstants.PC04_REVISAR_SOLICITUD_COMITE);
            esComentarioValido = 
                validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea()) == null ?
                false : 
                validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea());
            
            if(!esComentarioValido) 
            {
                budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
            }
            else
            {
                isValidSolicitarMasInformacion = Boolean.TRUE;
            }
            break;
        case FenixConstants.PC04_CONSOLIDAR_DECISION_COMITE:
            esComentario = 
                validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea()) == null ?
                false : 
                validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea());
            
            if(!esComentario) 
            {
                budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
            }
            else
            {
                isValidSolicitarMasInformacion = Boolean.TRUE;
            }
        break;
            case FenixConstants.PC04_DAR_SEGUIMIENTO_VOTACION:
            esComentario = 
                validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea()) == null ?
                false : 
                validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea());
            
            if(!esComentario) 
            {
                budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
            }
            else
            {
                isValidSolicitarMasInformacion = Boolean.TRUE;
            }
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

    public String invocarFinalizarTarea() {
        LOGGER.warning("Inicia el proceso de Invocar Finalizar Tarea");
        
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        try {
            List<String> budleKeyErroList = new ArrayList<String>();
            Boolean isValidFinalizarTarea = Boolean.FALSE;
            Boolean isValidDocumentos = Boolean.FALSE;
            String accion = "FinalizarTarea";
            String msgErrorKey = null;
        
            Boolean isRN03Valid = null;
            int codigoTarea = getCodigoTarea();
            Boolean condicion1=Boolean.FALSE;
            
            isValidDocumentos = validarDocumentosTarea(getIdOperacion(), codigoTarea,accion); //JURBINA@20092019 validar los documentos por operación, tarea y accion 
        
            switch (codigoTarea)
            {
                case FenixConstants.PC04_REVISARSOLICITUDCC:
                    condicion1 = validarVotantesDecisores();
                    if (condicion1) {
                        msgErrorKey = validaDatosSolicitudAprobacion(false);
                        if (null != msgErrorKey) {
                            budleKeyErroList.add(msgErrorKey);
                        } else {
                            isValidFinalizarTarea = Boolean.TRUE;
                        }
                    } else {
                        msgErrorKey = MSG_ERROR_MIEMBRO_SIN_USUARIO;
                        budleKeyErroList.add(msgErrorKey);
                    }
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                
                    break;
                case FenixConstants.PC04_DARSEGUIMIENTOVOTACION:
                    isValidFinalizarTarea = validaDarSeguimientoVotacion(budleKeyErroList);
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case FenixConstants.PC04_CONSOLIDARDECISION:
                    msgErrorKey = validaConsolidarDecision(false);
                    if(null != msgErrorKey){
                        budleKeyErroList.add(msgErrorKey);
                    }else{
                        isValidFinalizarTarea = Boolean.TRUE;
                    }
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case FenixConstants.PC04_REALIZARMODIFICACIONES:
                    LOGGER.fine("PC04_MODIFICARPROYECTORESOLUCION");
                    //RN03 Validar Comentarios
                    isRN03Valid = 
                        validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
        
                    if(!isRN03Valid)
                    {
                        budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
                        isValidFinalizarTarea = Boolean.FALSE;      
                    }
                    else
                    {            
                        isValidFinalizarTarea = Boolean.TRUE;
                    }
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case FenixConstants.PC04_MODIFICARPROYECTORESOLUCION:
                    LOGGER.fine("PC04_MODIFICARPROYECTORESOLUCION");
                    //RN04 Validar Tipo Documento Proyecto de Resolucion
                    //Boolean isRN04Valid = 
                      //  validateDocumento(getIdOperacion(), FenixConstants.TD_PROYECTORESOLUCION);
                      Boolean isRN04Valid = isValidDocumentos;
                    //RN03 Validar Comentarios
                    isRN03Valid = 
                        validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
        
                    if(!isRN03Valid)
                    {
                        budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
                    }
        
                    /*if(!isRN04Valid)
                    {
                        budleKeyErroList.add(MSG_ERROR_TD_PROYECTO_RESOLUCION);
                    }*/
        
                    if (isRN03Valid && isRN04Valid)
                    {
                        isValidFinalizarTarea = Boolean.TRUE;
                    }
                    break;
                case FenixConstants.PC04_REVISARSOLICITUDPRESIDENTE:
                    condicion1 = validarVotantesDecisores();
                    if (condicion1) {
                        isValidFinalizarTarea = validaRevisarSolPresidente(budleKeyErroList);
                    } else {
                        msgErrorKey = MSG_ERROR_MIEMBRO_SIN_USUARIO;
                        budleKeyErroList.add(msgErrorKey);
                    }
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case FenixConstants.PC04_DARSEGUIMIENTODECISIONPRESIDENTE:
                    isValidFinalizarTarea = validaDarSeguimientoDecPresidencia(budleKeyErroList);
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case FenixConstants.PC04_CERTIFICARDECISION:
                    isValidFinalizarTarea = validarCertificarDecision(budleKeyErroList);
                    aprobacionBean.setExisteCondicion(existenCondicionesPorIdOperacion());
                    if(aprobacionBean.getExisteCondicion()){
                        //si existe condcion es true mostrar el mensaje normal de finalizar tarea
                        LOGGER.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
                    }else{
                        // se existe condicion es false mostrar el mensaje de finalizar tarea sin condicion
                        LOGGER.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
                    }
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case FenixConstants.PC04_CONSOLIDARDECISIONDIRECTORIO:
                    isValidFinalizarTarea = validarConsolidarDecisionDirectorio(budleKeyErroList);
                    aprobacionBean.setExisteCondicion(existenCondicionesPorIdOperacion());
                    if(aprobacionBean.getExisteCondicion()){
                        //si existe condcion es true mostrar el mensaje normal de finalizar tarea
                        LOGGER.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
                    }else{
                        // se existe condicion es false mostrar el mensaje de finalizar tarea sin condicion
                        LOGGER.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
                    }
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case FenixConstants.PC04_ACTUALIZARTCC:
                    //isValidFinalizarTarea = validarActualizarTCC(budleKeyErroList);
                    isValidFinalizarTarea = validarTCC();
                    aprobacionBean.setExisteCondicion(existenCondicionesPorIdOperacion());
                    if(aprobacionBean.getExisteCondicion()){
                        //si existe condcion es true mostrar el mensaje normal de finalizar tarea
                        LOGGER.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
                    }else{
                        // se existe condicion es false mostrar el mensaje de finalizar tarea sin condicion
                        LOGGER.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
                    }
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case FenixConstants.PC04_COMPLETARTCC:
                    //isValidFinalizarTarea = validarActualizarTCC(budleKeyErroList);
                    isValidFinalizarTarea = validarTCC();
                    aprobacionBean.setExisteCondicion(existenCondicionesPorIdOperacion());
                    if(aprobacionBean.getExisteCondicion()){
                        //si existe condcion es true mostrar el mensaje normal de finalizar tarea
                        LOGGER.warning("Existen condciones mostrar el mensaje normal de finalizar tarea");
                    }else{
                        // se existe condicion es false mostrar el mensaje de finalizar tarea sin condicion
                        LOGGER.warning("No existen condiciones, mostrar finalizar tarea sin condiciones");
                    }
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case 135:
                    isValidFinalizarTarea = Boolean.TRUE;
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                case FenixConstants.PC04_VALIDARTCC:
                    isValidFinalizarTarea = Boolean.TRUE;
                    isValidFinalizarTarea = isValidFinalizarTarea && isValidDocumentos;
                    break;
                default:
                    LOGGER.log(ADFLogger.ERROR, "invocarFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
                    break;
            }
        
            if (!isValidFinalizarTarea)
            {
                if(budleKeyErroList.size()>0)
                {
                    for(String bundleKey : budleKeyErroList)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
            } 
            else
            {
                finalizarTareaPopup();
            }
        }catch(Exception e){
            LOGGER.severe("Error al procesar invocar finalizar tarea", e);
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "invocarFinalizarTarea", 
                       null);
        return null;
    }
    
    public String invocarFinalizarTareaCliente() {
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "invocarFinalizarTareaCliente");
        
        try{
            LOGGER.warning("Inicia el proceso de Invocar Finalizar Tarea");
        
            List<String> budleKeyErroList = new ArrayList<String>();
            Boolean isValidFinalizarTarea = Boolean.FALSE;
            String msgErrorKey = null;
        
            Boolean isRN03Valid = null;
            int codigoTarea = getCodigoTarea();
        
            switch (codigoTarea)
            {
                case FenixConstants.PC04_DAR_SEGUIMIENTO_VOTACION:
                    isValidFinalizarTarea = validaDarSeguimientoVotacionCliente(budleKeyErroList);
                    break;           
                case FenixConstants.PC04_CREAR_SOLICITUD_APROBACION:                     
                    //RN_01 Para poder finalizar la tarea se debe adjuntar el documento de tipo “Argumentación de SCR”.
                    boolean existeDocumento = validarDocumentoClienteTareaBpmIdFlujo(getIdCliente(), FenixConstants.TD_ARGUMENTACION_SCR, getIdTareaBpm(), getIdFlujo());
                    if(!existeDocumento)
                    {
                        budleKeyErroList.add(MSG_ERROR_DOCUMENTO_SCR);
                    }
                    else 
                    {
                        isValidFinalizarTarea = Boolean.TRUE;
                    }
                    break; 
                case FenixConstants.PC04_ELEVAR_SOLICITUD_APROBACION:
                    //No rules for this action.
                    isValidFinalizarTarea = Boolean.TRUE;
                    break;
                case FenixConstants.PC04_REVISAR_SOLICITUD_COMITE:
                    msgErrorKey = validaDatosSolicitudAprobacionCliente(false); 
                    if(null != msgErrorKey){
                        budleKeyErroList.add(msgErrorKey);
                    }else{
                        isValidFinalizarTarea = Boolean.TRUE;
                    }
                    break;
                case FenixConstants.PC04_CONSOLIDAR_DECISION_COMITE:
                    msgErrorKey = validarConsolidarDecisionComiteCredito();
                    if(msgErrorKey != null)
                    {
                        budleKeyErroList.add(msgErrorKey);
                    }
                    else
                    {
                        isValidFinalizarTarea = Boolean.TRUE;
                    }
                    break;
                case FenixConstants.PC04_REALIZAR_MODIFICACIONES:
                    //TODO ver por que y como puede venir el id 185 y como lo vamos a manejar en este metodo.
                    boolean existeComentario = 
                        validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea()) == null ?
                        false : 
                        validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea());
            
                    if(!existeComentario) 
                    {
                        budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
                    }
                    else
                    {
                        isValidFinalizarTarea = Boolean.TRUE;
                    }
                    break;
                case FenixConstants.PC04_REALIZAR_MODIFICAciONES_ANALISTA_CR:
                    boolean existeComentarios = 
                    validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea()) == null ?
                    false : 
                    validarComentarioCliente(getIdCliente(), String.valueOf(codigoTarea), getInstanciaTarea());
                
                    if(!existeComentarios) 
                    {
                        budleKeyErroList.add(MSG_ERROR_AGREGAR_COMENTARIO);
                    }
                    else
                    {
                        isValidFinalizarTarea = Boolean.TRUE;
                    }
                    break;
                case FenixConstants.PC04_ERROR_PROPAGAR_SCR:
                        isValidFinalizarTarea = Boolean.TRUE;
                    break;
                default:
                    LOGGER.log(ADFLogger.ERROR, "invocarFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
                    break;
            }
        
            if (!isValidFinalizarTarea)
            {
                if(budleKeyErroList.size()>0)
                {
                    for(String bundleKey : budleKeyErroList)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
            } 
            else
            {
                finalizarTareaPopup();
            }
        }catch(Exception e){
            LOGGER.severe("Error al procesar invocar finalizar tarea", e);
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "invocarFinalizarTareaCliente", 
                       null);
        return null;
    }
    
    /**
     * Valida la salida de la tarea de Dar Seguimiento a Decision Presidencia
     * @param budleKeyErroList contiene lista de claves bundle para mensajes de error
     * @return devuelve true si la validacion es correcta o false en caso contrario
     */
    public boolean validaDarSeguimientoDecPresidencia(List<String> budleKeyErroList){
        
        LOGGER.warning("Inside validaDarSeguimientoDecPresidencia.");

        boolean esValido = false;
        String accion = null;
        String acuerdo = null;
        if (ADFUtils.getBoundAttributeValue("DescAccionASeguir") != null) {
            accion = ADFUtils.getBoundAttributeValue("DescAccionASeguir").toString();
        }
        if (accion == null) {
            asignarDesicionCurrent();
        }
        try {
            if (ADFUtils.getBoundAttributeValue("Acuerdo") != null) {
                acuerdo = ADFUtils.getBoundAttributeValue("Acuerdo").toString();
            }
        } catch (Exception e) {
            LOGGER.warning("Error al recuperar Acuerdo: ", e);
        }
        LOGGER.warning("accion: " + accion);
        LOGGER.warning("acuerdo: " + acuerdo);
        
        if(accion == null ||
           "".equals(accion)){
            LOGGER.warning("Datos invalidos");
            budleKeyErroList.add(MGS_ERROR_DECISION_VACIA_BKEY);
        }else{
            
            //Verifica la existencia de decisiones
            boolean existeDecisiones = false;
            boolean esVotoPositivo = false;
            Long cantidadDecs = null;
            try{
                //Verifica ultimo voto de presidente
                LOGGER.warning("Verifica ultimo voto de presidente");
                esVotoPositivo = esPositivoVotoPresidente();
                
                OperationBinding oper = executeOperBinding(null, "obtenerCantidadDecisiones");
                if(oper.getErrors().isEmpty()){
                    cantidadDecs = (Long) oper.getResult();
                    if(cantidadDecs != null){
                        if(cantidadDecs.compareTo(new Long(0)) > 0){
                            existeDecisiones = true;
                        }else{
                            LOGGER.warning("No hay registros de decisiones");
                            budleKeyErroList.add(MSG_ERROR_VALID_DECISIONES_VACIO_BKEY);    
                            esValido = false;
                        }
                    }else{
                        LOGGER.severe("La cantidad recibida de decisiones es NULL");
                        budleKeyErroList.add(MSG_ERROR_VALID_DECISIONES_VACIO_BKEY);    
                        esValido = false;
                    }
                }else{
                    LOGGER.severe("Error la operacion para obtener la cantidad de decisiones tiene error");
                    budleKeyErroList.add(MSG_ERROR_VALID_DECISIONES_FALLO_BKEY);    
                    esValido = false;
                }
            }catch(Exception e){
                LOGGER.severe("Error al validar la existencia de decisiones presidente", e);
                budleKeyErroList.add(MSG_ERROR_VALID_DECISIONES_FALLO_BKEY);    
                esValido = false;
            }
            
            //Valida la existencia de decisiones
            if(existeDecisiones){
                
                //RN05 Validar Tipo Documento Respaldo de Decision Fuera de Sistema
                Boolean isRN05Valid = null;
                try{
                    isRN05Valid = validaDocumentoRespaldoVotoFueraSistema();
                }catch(Exception e){
                    LOGGER.severe("Error al realizar la validacion de Documentos", e);
                    budleKeyErroList.add("pc04aprobacion_msg_error_validacion_documento_respaldo_voto_fuera_sistema_fallo");
                }
                
                if(isRN05Valid){
                    
                    //Valida si la ultima decision es positiva o negativa
                    if(!esVotoPositivo){
                        //Codigo cuando la ultima decision de presidente es negativa
                        boolean emitioComentario = false;
                        String value = null;
                        
                        if(ADFUtils.getBoundAttributeValue("emiteComentarioAttr") != null){
                            value = ADFUtils.getBoundAttributeValue("emiteComentarioAttr").toString();    
                            
                            LOGGER.warning("Valor indicador de comentario agregado: " + value);
                        }
                        
                        if(value != null){
                            emitioComentario = Boolean.valueOf(value);
                            if(!emitioComentario){
                                budleKeyErroList.add(MSG_ERROR_COMENTARIO_REQUERIDO_VOTO_NEGATIVO);
                                esValido = false;
                            }else{
                                LOGGER.warning("Validacion de comentario valido");
                                esValido = true;
                            }
                        }else{
                            budleKeyErroList.add(MSG_ERROR_COMENTARIO_REQUERIDO_VOTO_NEGATIVO);
                            esValido = false;
                        }
                    }else{
                        //Codigo cuando la ultima decision de presidente es positiva
                        String codigoExt = null;
                        String observacion = null;
                        boolean esRequerido = false;
                        
                        //Verifica si es requerido validar observaciones
                        if(ADFUtils.getBoundAttributeValue("CodigoExtAccionASeguir") != null){
                            codigoExt = ADFUtils.getBoundAttributeValue("CodigoExtAccionASeguir").toString();    
                        }
                        
                        if(codigoExt != null){
                            //Verifica si es requerido agregar observaciones
                            if(codigoExt.equals(CODIGO_ACCION_DIRECTORIO)){
                                esRequerido = true;
                            }
                        }
                        
                        if(esRequerido){
                            //Obtiene valor de las observaciones
                            if(getObservacionUIC() != null){
                                if(getObservacionUIC().getValue() != null){
                                    observacion = getObservacionUIC().getValue().toString();        
                                }
                            }
                            
                            if(observacion == null ||
                               "".equals(observacion)){
                                LOGGER.warning("Datos invalidos");
                                budleKeyErroList.add(MSG_ERROR_OBSERVACION_VACIA_BKEY);
                            }else{
                                LOGGER.warning("Datos validos");
                                esValido = true;
                            }
                        }else{
                            LOGGER.warning("Datos validos");
                            esValido = true;
                        }
                    } 
                }else{
                    budleKeyErroList.add("pc04aprobacion_msg_error_validacion_documento_respaldo_voto_fuera_sistema");
                    esValido = false;
                }
            }
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validaDarSeguimientoDecPresidencia",
                       esValido);
        return esValido;
    }
        
    /**
     * Valida la salida de la tarea de Dar Seguimiento a Votacion
     * @param budleKeyErroList contiene lista de claves bundle para mensajes de error
     * @return devuelve true si la validacion es correcta o false en caso contrario
     */
    public boolean validaDarSeguimientoVotacion(List<String> budleKeyErroList){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                       "validaDarSeguimientoVotacion",
                       budleKeyErroList);
        
        boolean isValidFinalizarTarea = false;
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            
        try{
            
            Boolean reunionPresencial = aprobacionBean.getReunionPresencial();
        
            //RN05 Validar Tipo Documento Respaldo de Voto Fuera de Sistema
            Boolean isRN05Valid = null;
            try{
                isRN05Valid = validaDocumentoRespaldoVotoFueraSistema();
            }catch(Exception e){
                LOGGER.severe("Error al realizar la validacion de Documentos", e);
                budleKeyErroList.add("pc04aprobacion_dar_seguimiento_votacion_msg_error_validacion");
            }
                
            if(null != reunionPresencial && 
               !reunionPresencial){
                if(!isRN05Valid){
                    budleKeyErroList.add(MSG_ERROR_TD_RESPALDO_VOTO);
                }else{
                    isValidFinalizarTarea = Boolean.TRUE;
                }
            }else{
                
                Boolean esNuevaFechaReunion = null;
                try{
                    esNuevaFechaReunion = (Boolean) ADFUtils.getBoundAttributeValue("esNuevaFechaInicioAttrValue");    
                }catch(Exception e){
                    LOGGER.warning("No se obtuvo bandera de Fecha Inicio modificada");
                }
                
                if(esNuevaFechaReunion != null &&
                   esNuevaFechaReunion){
                    
                    //Valida la fecha de la reunion cuando fue modificada
                    AttributeBinding fechaReunion;
                    fechaReunion = ADFUtils.findControlBinding("FechaInicio");
                    Timestamp fechaReunionNueva = null;
                    try{
                        fechaReunionNueva = new Timestamp(fechaReunion.getInputValue().toString());    
                    }catch(Exception e){
                        LOGGER.severe("Error al procesar la fecha inicio", e);
                    }
                    
                    if(fechaReunionNueva != null){
                        Calendar cal = Calendar.getInstance();
                        //cal.add(Calendar.DATE, -1);
                        cal.set(Calendar.HOUR_OF_DAY,0);
                        cal.set(Calendar.MINUTE,0);
                        cal.set(Calendar.SECOND,0);
                        cal.set(Calendar.MILLISECOND,0);
                        
                        Timestamp fechaSistema = null;
                        fechaSistema = new Timestamp(cal.getTime());
                        
                        if(fechaReunionNueva.dateValue().compareTo(fechaSistema.dateValue()) < 0){
                            budleKeyErroList.add(MSG_ERROR_FECHA_SISTEMA);
                        }else{
                            //Asigna validacion positiva para actualizar datos al aceptar la confirmacion de finalizar tarea
                            isValidFinalizarTarea = Boolean.TRUE;
                        }
                    }else{
                        budleKeyErroList.add("pc04aprobacion_dar_seguimiento_votacion_msg_error_validacion");
                    }
                }else{
                    isValidFinalizarTarea = Boolean.TRUE;
                }
            }
        }catch(Exception e){
            LOGGER.severe("Error al validar Finalizar Tarea en Dar Seguimiento a Votacion", e);
            budleKeyErroList.add("pc04aprobacion_dar_seguimiento_votacion_msg_error_validacion");
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validaDarSeguimientoVotacion",
                       isValidFinalizarTarea);
        return isValidFinalizarTarea;
    }
    
    /**
     * Valida la salida de la tarea de Dar Seguimiento a Votacion
     * @param budleKeyErroList contiene lista de claves bundle para mensajes de error
     * @return devuelve true si la validacion es correcta o false en caso contrario
     */
    public boolean validaDarSeguimientoVotacionCliente(List<String> budleKeyErroList){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                       "validaDarSeguimientoVotacion",
                       budleKeyErroList);
        
        boolean isValidFinalizarTarea = false;
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            
        try{
            
            Boolean reunionPresencial = aprobacionBean.getReunionPresencial();
        
            //RN05 Validar Tipo Documento Respaldo de Voto Fuera de Sistema
            Boolean isRN05Valid = null;
            try{
                isRN05Valid = validaDocumentoRespaldoVotoFueraSistemaCliente();
            }catch(Exception e){
                LOGGER.severe("Error al realizar la validacion de Documentos", e);
                budleKeyErroList.add("pc04aprobacion_dar_seguimiento_votacion_msg_error_validacion");
            }
                
            if(null != reunionPresencial && 
               !reunionPresencial){
                if(!isRN05Valid){
                    budleKeyErroList.add(MSG_ERROR_TD_RESPALDO_VOTO);
                }else{
                    isValidFinalizarTarea = Boolean.TRUE;
                }
            }else{
                
                Boolean esNuevaFechaReunion = null;
                try{
                    esNuevaFechaReunion = (Boolean) ADFUtils.getBoundAttributeValue("esNuevaFechaInicioAttrValue");    
                }catch(Exception e){
                    LOGGER.warning("No se obtuvo bandera de Fecha Inicio modificada");
                }
                
                if(esNuevaFechaReunion != null &&
                   esNuevaFechaReunion){
                    
                    //Valida la fecha de la reunion cuando fue modificada
                    AttributeBinding fechaReunion;
                    fechaReunion = ADFUtils.findControlBinding("FechaInicio");
                    Timestamp fechaReunionNueva = null;
                    try{
                        fechaReunionNueva = new Timestamp(fechaReunion.getInputValue().toString());    
                    }catch(Exception e){
                        LOGGER.severe("Error al procesar la fecha inicio", e);
                    }
                    
                    if(fechaReunionNueva != null){
                        Calendar cal = Calendar.getInstance();
                        //cal.add(Calendar.DATE, -1);
                        cal.set(Calendar.HOUR_OF_DAY,0);
                        cal.set(Calendar.MINUTE,0);
                        cal.set(Calendar.SECOND,0);
                        cal.set(Calendar.MILLISECOND,0);
                        
                        Timestamp fechaSistema = null;
                        fechaSistema = new Timestamp(cal.getTime());
                        
                        if(fechaReunionNueva.dateValue().compareTo(fechaSistema.dateValue()) < 0){
                            budleKeyErroList.add(MSG_ERROR_FECHA_SISTEMA);
                        }else{
                            //Asigna validacion positiva para actualizar datos al aceptar la confirmacion de finalizar tarea
                            isValidFinalizarTarea = Boolean.TRUE;
                        }
                    }else{
                        budleKeyErroList.add("pc04aprobacion_dar_seguimiento_votacion_msg_error_validacion");
                    }
                }else{
                    isValidFinalizarTarea = Boolean.TRUE;
                }
            }
        }catch(Exception e){
            LOGGER.severe("Error al validar Finalizar Tarea en Dar Seguimiento a Votacion", e);
            budleKeyErroList.add("pc04aprobacion_dar_seguimiento_votacion_msg_error_validacion");
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validaDarSeguimientoVotacion",
                       isValidFinalizarTarea);
        return isValidFinalizarTarea;
    }
    
    /**
     * Valida la salida de la tarea de Revisar Solicitud Presidente
     * @param budleKeyErroList contiene lista de claves bundle para mensajes de error
     * @return devuelve true si la validacion es correcta o false en caso contrario
     */
    public boolean validaRevisarSolPresidente(List<String> budleKeyErroList){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validaRevisarSolPresidente",
                        budleKeyErroList);
        
        boolean esValido = false;
        String keyError = null;
        
        Date fechaInicio = null;
        Date fechaFin = null;
        if(getFechaInicioRevPresidenciaUIC() != null){
            fechaInicio = (Date) getFechaInicioRevPresidenciaUIC().getValue();
            
            if(fechaInicio != null){
                
                if(getFechaCierreRevPresidenciaUIC() != null){
                    fechaFin = (Date) getFechaCierreRevPresidenciaUIC().getValue();
                    
                    if(fechaFin == null){
                        //Asigna clave de error para fecha de cierre vacia
                        keyError = "pc04aprobacion_revisar_solicitud_presidencia_msg_error_fecha_cierre_vacio";
                    }
                }else{
                    //Asigna clave de error para fecha de cierre vacia
                    keyError = "pc04aprobacion_revisar_solicitud_presidencia_msg_error_fecha_cierre_vacio";
                }
            }else{
                //Asigna clave de error para fecha de apertura vacia
                keyError = "pc04aprobacion_revisar_solicitud_presidencia_msg_error_fecha_apertura_vacio";
            }
        }else{
            //Asigna clave de error para fecha de apertura vacia
            keyError = "pc04aprobacion_revisar_solicitud_presidencia_msg_error_fecha_apertura_vacio";
        }
        
        if(fechaInicio != null){    
            Date fechaActual = getCurrentDate();
            if(fechaInicio.compareTo(fechaActual) < 0){
                
                //Asigna clave de error para fecha de apertura invalida
                keyError = "pc04aprobacion_revisar_solicitud_presidencia_msg_error_fecha_apertura_invalida";

                getFechaInicioRevPresidenciaUIC().resetValue();
                getFechaInicioRevPresidenciaUIC().setValue(null);
            }
        }
        
        if(fechaInicio != null &&
           fechaFin != null){
            if(fechaFin.compareTo(fechaInicio) < 0){
                
                //Asigna clave de error para fecha de cierre invalida
                keyError = "pc04aprobacion_revisar_solicitud_presidencia_msg_error_fecha_cierre_invalida";
                
                getFechaCierreRevPresidenciaUIC().resetValue();
                getFechaCierreRevPresidenciaUIC().setValue(null);
            }
        }
        
        if(keyError != null){
            budleKeyErroList.add(keyError);    
        }else{
            esValido = true;
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                        "validaRevisarSolPresidente",
                        esValido);
        return esValido;
    }

    @SuppressWarnings("unchecked")
    public String agregarVotoFueraSistema() {
        
        boolean esVotoPermitido = false;
                esVotoPermitido = validaVotoPorRangoFecha("FechaInicio",
                                                          "FechaTermino",
                                                          true);
        if(esVotoPermitido){
            AprobacionBean aprobacionBean =
                (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            
            String idSolicitud = aprobacionBean.getIdSolicitud();
            String idRolBpm = aprobacionBean.getIdRolBPM();
            
            //Se inicializan los VO's para VotoAprobacionVO y ComentarioReunionVO
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("inicializarAgregarVotoFueraSistema");
            operationBinding.getParamsMap().put("usuarioLogin", ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.getParamsMap().put("idRolBpm", idRolBpm);
            operationBinding.execute();
            
            try{
                operationBinding = binding.getOperationBinding("buscarPorIdSolicitud");
                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                operationBinding.getParamsMap().put("emiteVoto", 1);
                operationBinding.execute();     
            }catch(Exception e){
                LOGGER.severe("Error al inicializar Agregar Voto fuera de Sistema", e);
            }
            aprobacionBean.setTituloPopup(getStringFromBundle("LBL_AGREGAR_VOTO_FUERA_DE_SISTEMA"));
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupAgregarModificarVoto().show(popupHints);
        }
        
        return null;
    }
    
    public void cambioAccionSeguir(ValueChangeEvent valueChangeEvent){
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        aprobacionBean.setLineaCredito(Boolean.FALSE);
        
        Number nuevoValor=(Number)valueChangeEvent.getNewValue();
        LOGGER.warning("Id accion a seguir: " + nuevoValor);
        if(nuevoValor != null){
            if(nuevoValor.compareTo(13) == 0 || nuevoValor.compareTo(16) == 0){  //prueba  || nuevoValor.compareTo(6)==0
                aprobacionBean.setLineaCredito(Boolean.TRUE);
            }else{
                LOGGER.warning("Id de accion no valido para la reasignacion");
            }
        }else{
            LOGGER.warning("No hay valor que reasignar");
        }        
    }
    
    public void usuarioRolBPM_ChangeListener(ValueChangeEvent valueChangeEvent){
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Number value = (Number) ADFUtils.getBoundAttributeValue("idUsuarioReunionVar1");
        ADFUtils.setBoundAttributeValue("IdUsuarioReunion1", value);
    }

    public String editarVotoFueraSistema() {
        
        //Number VaId = (Number) ADFUtils.getBoundAttributeValue("VaId"); //Declaracion no utilizada
        
        AprobacionBean aprobacionBean =
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        aprobacionBean.setTituloPopup(getStringFromBundle("LBL_EDITAR_VOTO_FUERA_DE_SISTEMA"));
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupAgregarModificarVoto().show(popupHints);
        return null;
    }
    
    /**
     * Realiza la actualizacion de datos del Seguimiento de Votacion, al confirmar la finalizacion de la tarea
     */
    public void actualizarDatosDarSeguimientoVotacion(){
        
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        Boolean reunionPresencial = aprobacionBean.getReunionPresencial();
        if(null != reunionPresencial && reunionPresencial){

            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("actualizarDatosReunionPresencial");
            operationBinding.execute();
            
            if(!operationBinding.getErrors().isEmpty()){
                JSFUtils.addFacesErrorMessage(MSG_ERROR_ACTUALIZAR_SEGUIMIENTO_VOTACION_BKEY);
            }
        }
    }
    
    /**
     * Guarda la fecha de cierre de la solicitud de aprobacion
     * @param showMsg Indicador booleano para mostrar o no mensajes de dialogo
     * @return devuelve cadena con mensaje de error o null en caso de exito
     */
    public String guardaFechaCierreSolAprobacion(boolean showMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "guardaFechaCierreSolAprobacion");
        
        String msgError = null;
        
        BindingContainer binding = getBindings();
        OperationBinding oper = null;
        try{
            oper = binding.getOperationBinding(GUARDAR_FECHA_CIERRE_OPER);    
        }catch(Exception e){
            LOGGER.severe("Error al obtener Operator Binding: " + GUARDAR_FECHA_CIERRE_OPER, e);
        }
        
        if(oper != null){
            LOGGER.warning("Ejecuta Operator Bindings: " + GUARDAR_FECHA_CIERRE_OPER);
            oper.execute();
            if(oper.getErrors().isEmpty()){
                Boolean exito = null;
                exito = (Boolean)oper.getResult();
                if(exito != null){
                    if(!exito){
                        msgError = getStringFromBundle(MSG_ERROR_GUARDAR_FECHA_CIERRE);
                    }else{
                        LOGGER.warning("Fecha Cierre actualizada correctamente");
                    }
                }
            }else{
                msgError = getStringFromBundle(MSG_ERROR_GUARDAR_FECHA_CIERRE) + 
                    ". Errors: " + oper.getErrors().toString();
            }
        }else{
            LOGGER.severe("Operator Binding no encontrado");
        }
        
        if(msgError != null){
            if(showMsg){
                JSFUtils.addFacesErrorMessage(msgError);
            }
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "guardaFechaCierreSolAprobacion",
                       msgError);
        return msgError;
    }

    public RichPopup getPopupAgregarModificarVoto() {
        return popupAgregarModificarVoto;
    }

    public void setPopupAgregarModificarVoto(RichPopup popupAgregarModificarVoto) {
        this.popupAgregarModificarVoto = popupAgregarModificarVoto;
    }

    @SuppressWarnings("unchecked")
    public void dialogListenerAgregarModificarVoto(DialogEvent dialogEvent) {
        
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        AttributeBinding idUsuarioReunion;
        idUsuarioReunion = ADFUtils.findControlBinding("idUsuarioReunionVar1");
        LOGGER.warning("Ejecuta Agregar Modificar Voto, Id de Usuario Reunion Aprobacion: " + idUsuarioReunion);
        
        aprobacionBean.setSeAgregoVotoFueraSistem(Boolean.TRUE);
        
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            // write your custom code for ok event
            String comentario = null;
            try{
                comentario = (String) ADFUtils.getBoundAttributeValue("ComentarioFueraSistema");
            }catch(Exception e){
                LOGGER.warning("Error al obtener comentario", e);
            }
            LOGGER.warning("Comentario a guardar: " + comentario);
            
            String idSolicitud = aprobacionBean.getIdSolicitud();
            String idOperacion = aprobacionBean.getIdOperacion();
            
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("agregarVotoFueraSistema");
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.getParamsMap().put("idUsuarioReunion", idUsuarioReunion.getInputValue().toString());
            
            Boolean resultado = (Boolean) operationBinding.execute();
            if(resultado == null){
                resultado = (Boolean) operationBinding.getResult();
            }
            
            aprobacionBean.setComentario("");
            
            if(!resultado){
                JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_AGREGAR_VOTO"));
            }
            
            OperationBinding operationBindingComentario = binding.getOperationBinding("inicializarDarSeguimiento");
            operationBindingComentario.getParamsMap().put("idSolicitud", idSolicitud);
            operationBindingComentario.getParamsMap().put("idOperacion", idOperacion);
            operationBindingComentario.execute();
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(getListViewComentariosReunion());
            AdfFacesContext.getCurrentInstance().addPartialTarget(aprobacionBean.getTreeTabBind());
        }
        getPopupAgregarModificarVoto().hide();
    }
    
    @SuppressWarnings("unchecked")
    public void dialogListenerAgregarModificarVotoCliente(DialogEvent dialogEvent) {
        
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        AttributeBinding idUsuarioReunion;
        idUsuarioReunion = ADFUtils.findControlBinding("idUsuarioReunionVar1");
        LOGGER.warning("Ejecuta Agregar Modificar Voto, Id de Usuario Reunion Aprobacion: " + idUsuarioReunion);
        
        aprobacionBean.setSeAgregoVotoFueraSistem(Boolean.TRUE);
        
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            // write your custom code for ok event
            String comentario = null;
            try{
                comentario = (String) ADFUtils.getBoundAttributeValue("ComentarioFueraSistema");
            }catch(Exception e){
                LOGGER.warning("Error al obtener comentario", e);
            }
            LOGGER.warning("Comentario a guardar: " + comentario);
            
            String idSolicitud = aprobacionBean.getIdSolicitud();
            String idCliente = aprobacionBean.getIdCliente();
            
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("agregarVotoFueraSistema");
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.getParamsMap().put("idUsuarioReunion", idUsuarioReunion.getInputValue().toString());
            
            Boolean resultado = (Boolean) operationBinding.execute();
            if(resultado == null){
                resultado = (Boolean) operationBinding.getResult();
            }
            
            aprobacionBean.setComentario("");
            
            if(!resultado){
                JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_ERROR_AGREGAR_VOTO"));
            }
            
            OperationBinding operationBindingComentario = binding.getOperationBinding("inicializarDarSeguimientoCliente");
            operationBindingComentario.getParamsMap().put("idSolicitud", idSolicitud);
            operationBindingComentario.getParamsMap().put("idCliente", idCliente);
            operationBindingComentario.execute();
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(getListViewComentariosReunion());
            AdfFacesContext.getCurrentInstance().addPartialTarget(aprobacionBean.getTreeTabBind());
        }
        getPopupAgregarModificarVoto().hide();
    }

    public void cancelPopupAgregarModificarVoto(PopupCanceledEvent popupCanceledEvent) {
        
        if(popupCanceledEvent == null){
            return;
        }
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        try{
            // write your custom code for cancel event
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("cancelarVotoFueraSistema");
            operationBinding.execute();
            
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getTreeTableHistorico());
            AdfFacesContext.getCurrentInstance().addPartialTarget(aprobacionBean.getTreeTabBind());
        }catch(Exception e){
            LOGGER.severe("Error al cancelar Popup de Agregar Modificar Voto", e);
        }
    }

    public String requiereAjustes() {
        
        
        return null;
    }
    
    /**
    * Aceptar Mas Informacion
    * @return
    */
    public String solicitarMasInformacion() {
        LOGGER.warning("Inside solicitarMasInformacion.");
        
        popupConfMasInfo.hide();
        //Se genera case para todos los casos que necesitan cargar onbase por operacion, y se mueve el siguiente llamado
        //de metodo a cada caso, ya que no todos los casos cargaran documentos por operacion si no tambien por cliente.
        //invocar metodo para cargar documentos onBase
        //cargarDocumentosOnBase();
        
        int codigoTarea = getCodigoTarea(); 
        String accionPayload = "MASINFORMACION";
        
        //String msgError = null; //Declaracion no utilizada
        switch (codigoTarea){
           case FenixConstants.PC04_DARSEGUIMIENTODECISIONPRESIDENTE:
                cargarDocumentosOnBase();
                try{
                    String accion = ADFUtils.getBoundAttributeValue("CodigoExtAccionASeguir").toString();
                    ADFUtils.setBoundAttributeValue("accion", accion);
                }catch(Exception e){
                    LOGGER.warning("Error al actualizar accion en Payload", e);
                }
                break;
            case FenixConstants.PC04_CONSOLIDARDECISION:
                cargarDocumentosOnBase();
                break;
            case FenixConstants.PC04_REALIZARMODIFICACIONES:
                cargarDocumentosOnBase();
                break;
            case FenixConstants.PC04_REVISARSOLICITUDCC:
                // FNXII-6783 Actualiza campo accion
                actualizarPayloadElement("accion", accionPayload);
                cargarDocumentosOnBase();
                break;
            case FenixConstants.PC04_REVISARSOLICITUDPRESIDENTE:
                // FNXII-6783 Actualiza campo accion
                actualizarPayloadElement("accion", accionPayload);
                cargarDocumentosOnBase();
                break;
            case FenixConstants.PC04_DARSEGUIMIENTOVOTACION:
                cargarDocumentosOnBase();
                break;
            case FenixConstants.PC04_ELEVAR_SOLICITUD_APROBACION:                
                break;
            case FenixConstants.PC04_REVISAR_SOLICITUD_COMITE:
                break;
            case FenixConstants.PC04_CONSOLIDAR_DECISION_COMITE:
                break;
            case FenixConstants.PC04_DAR_SEGUIMIENTO_VOTACION:
               cargarDocumentosClienteOnBase();
                break;
            case 135:
               cargarDocumentosClienteOnBase();
                break;
            case FenixConstants.PC04_CONSOLIDARDECISIONDIRECTORIO:
                LOGGER.warning("Se acepta Solicitar ajustes para la tarea Consolidar decision directorio :"+FenixConstants.PC04_CONSOLIDARDECISIONDIRECTORIO);
                LOGGER.warning("Se asigna a accion el valor de RETORNAR");
                actualizarPayloadElement("accion","RETORNAR");
                break;
            case FenixConstants.PC04_VALIDARTCC:
                LOGGER.warning("PC04ValidarTCC");
                break;
            default:
                LOGGER.log(ADFLogger.ERROR, "finalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
                break;                
        }
        InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
        return invokeActionBean.invokeOperation();
    }
    
    /**
    * Aceptar Mas Informacion
    * @return
    */
    public String solicitarMasInformacionCliente() {
        popupConfMasInfo.hide();
        
        int codigoTarea = getCodigoTarea(); 
        //String msgError = null; //Declaracion no utilizada
        switch (codigoTarea){
            case FenixConstants.PC04_ELEVAR_SOLICITUD_APROBACION:                
                break;
            case FenixConstants.PC04_REVISAR_SOLICITUD_COMITE:
                break;
            case FenixConstants.PC04_CONSOLIDAR_DECISION_COMITE:
                break;
            case FenixConstants.PC04_DAR_SEGUIMIENTO_VOTACION:
               cargarDocumentosClienteOnBase();
                break;
            default:
                LOGGER.log(ADFLogger.ERROR, "finalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
                break;                
        }
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
    
    public Boolean actualizarPayloadDecisionPresidente(){
        Boolean retorna=Boolean.FALSE;
        AttributeBinding desicionPresidente;
        desicionPresidente = ADFUtils.findControlBinding("CodExterno");
        String nuevaDesicion=(String) desicionPresidente.getInputValue();
        LOGGER.warning("Accion: " +nuevaDesicion );
        if(null!=nuevaDesicion && nuevaDesicion.length()>0){
            AttributeBinding accionNueva;
            accionNueva = ADFUtils.findControlBinding("accion");
            accionNueva.setInputValue(nuevaDesicion);
            LOGGER.warning("AccionActualizada: " +(String)accionNueva.getInputValue());
            retorna=Boolean.TRUE;
        }
        return retorna;
    }
    
    public void actualizarPayloadElement(String psElementName, Object poValue) {
        LOGGER.warning("actualizarPayloadMasInformacion.");
        LOGGER.warning("psElementName: " + psElementName);
        LOGGER.warning("poValue: " + poValue);
        
        AttributeBinding attributeBinding = null;
        
        attributeBinding = ADFUtils.findControlBinding(psElementName);
        
        if(attributeBinding!=null) {
            attributeBinding.setInputValue(poValue);
        } else {
            LOGGER.warning("Elemento buscado es null.");
        }
    }
    
  /**
   *Aceptar Finalizar Tarea
   * @return
   */
    public String finalizarTarea() {
        popupConfFinalizarTarea.hide();
        
        //Se mueve esta instruccion a cada una de las tareas en el switch ya que no todas son por operacion 
        //algunas son por cliente y requieren un metodo distinto
        //invocar metodo para cargar documentos onBase
        //cargarDocumentosOnBase(); 
        
        int codigoTarea = getCodigoTarea();
        
        String msgError = null;
        String accionFinalizarTarea = "FINALIZARTAREA";
        switch (codigoTarea)
        {
          case FenixConstants.PC04_REVISARSOLICITUDCC:
               actualizarPayloadElement("accion", accionFinalizarTarea);
               cargarDocumentosOnBase(); 
               msgError = crearSolicitudAprobacion(true);
          break;
          case FenixConstants.PC04_DARSEGUIMIENTOVOTACION:
                cargarDocumentosOnBase(); 
                actualizarDatosDarSeguimientoVotacion();
                msgError = guardaFechaCierreSolAprobacion(true);
          break;
          case FenixConstants.PC04_DAR_SEGUIMIENTO_VOTACION:
                  cargarDocumentosClienteOnBase(); 
                  actualizarDatosDarSeguimientoVotacion();
                  msgError = guardaFechaCierreSolAprobacion(true);
          break;
          case FenixConstants.PC04_CONSOLIDARDECISION:
               cargarDocumentosOnBase(); 
               msgError = guardarDatosConsolidarDecision(true, "por_operacion"); //
               if(msgError == null){
                   String accion = ADFUtils.getBoundAttributeValue("CodigoExternoVarAttr").toString();
                   ADFUtils.setBoundAttributeValue("accion", accion); 
               }
          break;
          case FenixConstants.PC04_REALIZARMODIFICACIONES:
            cargarDocumentosOnBase(); 
          break;
          case FenixConstants.PC04_MODIFICARPROYECTORESOLUCION:
            cargarDocumentosOnBase(); 
          break;
          case FenixConstants.PC04_REVISARSOLICITUDPRESIDENTE:
            cargarDocumentosOnBase(); 
            msgError = crearSolicitudAprobacionPresidencia(true);
            if(msgError == null){
                actualizarPayloadDecisionPresidente();
            }else{
                LOGGER.severe("Error en la creacion de la solicitud de aprobacion nivel presidencia");
            }
          break;
          case FenixConstants.PC04_DARSEGUIMIENTODECISIONPRESIDENTE:
            cargarDocumentosOnBase(); 
            boolean esPositivo = esPositivoVotoPresidente();
            if(!esPositivo){
                msgError = actualizarDecisionAccionNegativa(true);
            }else{
                msgError = guardarObservacionPresidente(true);
            }
            if(msgError == null){
                msgError = guardaFechaCierreSolAprobacion(true);
                if(msgError == null){
                    try{
                        String accion = ADFUtils.getBoundAttributeValue("CodigoExtAccionASeguir").toString();
                        ADFUtils.setBoundAttributeValue("accion", accion);
                    }catch(Exception e){
                        LOGGER.severe("Error al actualizar accion en Payload", e);
                    }
                }
            }
          break;
          case FenixConstants.PC04_CERTIFICARDECISION:
            LOGGER.warning("Finalizar tarea Case PC04_CERTIFICARDECISION");
            cargarDocumentosOnBase(); 
            boolean exitoGuardarCC = false;
            try{
                //Se crea o modifica la informacion
                exitoGuardarCC = guardarAprobacionAction();
                if(!exitoGuardarCC){
                    msgError = "Error al guardar los datos de aprobacion";
                    LOGGER.severe(msgError);
                }
            }catch(Exception e){
                LOGGER.severe("Error al guardar los datos de aprobacion", e);
            }
            break;
          case FenixConstants.PC04_CONSOLIDARDECISIONDIRECTORIO:
            cargarDocumentosOnBase(); 
            boolean exitoGuardarCDD = false;
            try{
                //Se crea o modifica la informacion
                exitoGuardarCDD = guardarAprobacionAction();
                if(!exitoGuardarCDD){
                    msgError = "Error al guardar los datos de aprobacion";
                    LOGGER.severe(msgError);
                }else{
                    Boolean esActualiza = Boolean.FALSE;
                    esActualiza = actualizarAccionASeguir();
                    if(esActualiza){
                        LOGGER.warning("Actualizacion Correcta");
                    }
                }
            }catch(Exception e){
                LOGGER.severe("Error al guardar los datos de aprobacion", e);
            }
            
            if(msgError == null){
                LOGGER.warning("No Existe mensaje de error al guardar la aprobacion");
                //Se actualiza accion de outcome dependeindo de la votacion
                Row currentRow = getCurrentRegistrarDatosAprobacionVO();
                
                String codigoAccion = "";
                try
                {
                    codigoAccion = (String)currentRow.getAttribute("CodExternoAccionSeguir");
                    LOGGER.warning("codigoAccion :"+codigoAccion);
                    actualizarPayloadElement("accion",codigoAccion);
                }
                catch (Exception ex)
                {
                  LOGGER.severe("Error guardarAprobacionAction : " + ex.getClass() + "-" + ex.getCause());
                }
                
                LOGGER.warning("codigoAccion: '" + codigoAccion + "'");
            }
            break;
          case FenixConstants.PC04_ACTUALIZARTCC:
            cargarDocumentosOnBase(); 
            boolean exito = true;
            /* //Se Deshabilita con la implementacion de Matriz TCC
            exito = guardarFormalizacionAction(); 
            if(!exito){
                msgError = "Error al crear Actualizar Condicion Aprobacion";
                LOGGER.severe(msgError);
            }
            */
            exito = actualizarTCCEstadoAprobado();
            if(!exito){
                msgError = "Error al actualizar Estados TCC a Aprobado";
                LOGGER.severe(msgError);
            }
            break;
          case FenixConstants.PC04_COMPLETARTCC:
            cargarDocumentosOnBase(); 
            break;
            case FenixConstants.PC04_VALIDARTCC:
              LOGGER.log(ADFLogger.WARNING, "Aceptar Finalizar Validar TCC.");
              boolean exito2 = true;
              exito2 = actualizarTCCEstadoAprobado();
              if(!exito2){
                  msgError = "Error al actualizar Estados TCC a Aprobado";
                  LOGGER.severe(msgError);
              }
              break;
        case FenixConstants.PC04_CREAR_SOLICITUD_APROBACION:
            cargarDocumentosClienteOnBase(); 
            break;  
        case FenixConstants.PC04_ELEVAR_SOLICITUD_APROBACION:            
            break; 
        case FenixConstants.PC04_REVISAR_SOLICITUD_COMITE:
            LOGGER.warning("Revisar solicitud comite.");
            cargarDocumentosClienteOnBase(); 
            msgError = crearSolicitudAprobacionCliente(true);
            break;
        case FenixConstants.PC04_CONSOLIDAR_DECISION_COMITE:
            cargarDocumentosClienteOnBase();
            msgError = guardarDatosConsolidarDecision(true, "por_cliente"); 
            if(msgError == null){
                String accion = ADFUtils.getBoundAttributeValue("CodigoExternoVarAttr").toString();
                ADFUtils.setBoundAttributeValue("accion", accion); 
            }
            break;
        case FenixConstants.PC04_REALIZAR_MODIFICACIONES:
            cargarDocumentosClienteOnBase(); 
            break;
        case FenixConstants.PC04_REALIZAR_MODIFICAciONES_ANALISTA_CR:
            cargarDocumentosClienteOnBase(); 
            break;
        case 135:
               cargarDocumentosClienteOnBase();
            break;
        default:
              LOGGER.log(ADFLogger.ERROR, "finalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
          break;
        }
        
        if(null != msgError){
            LOGGER.log(ADFLogger.WARNING, "msgError no es null '" + msgError + "'");
            return null;
        }else{
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            if (invokeActionBean != null) {
                String invokeOperation = invokeActionBean.invokeOperation();
                
                if (invokeOperation != null) {
                    LOGGER.log(ADFLogger.WARNING, "invokeOperation '" + invokeOperation + "'");
                    return invokeOperation;
                } else {
                    LOGGER.log(ADFLogger.WARNING, "invokeOperation es null.");
                    return null;
                }
            } else {
                LOGGER.log(ADFLogger.WARNING, "InvokeActionBean es null.");
                return null;
            }  
        }
    }
  
  public Boolean actualizarAccionASeguir(){
      Boolean esActualiza = Boolean.FALSE;
      LOGGER.warning("Entra en actualizarAccionASeguir.");
      try{
          BindingContainer binding = getBindings();
          OperationBinding operationBinding = binding.getOperationBinding("actualizarAccionASeguir");
          operationBinding.execute();
          esActualiza = (Boolean)operationBinding.getResult();
          
      }catch(Exception e){
          LOGGER.warning("Error en Actualizar la accion a seguir." + e);
      }
      LOGGER.warning("Termina en actualizarAccionASeguir." + esActualiza);
      return esActualiza;
  }
    /**
     *Aceptar Finalizar Tarea
     * @return
     */
      public String finalizarTareaCliente() {
          AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
          popupConfFinalizarTarea.hide();
          
          int codigoTarea = getCodigoTarea();
          
          String msgError = null;
          
          switch (codigoTarea)
          {
            case FenixConstants.PC04_DAR_SEGUIMIENTO_VOTACION:
                    cargarDocumentosClienteOnBase(); 
                    actualizarDatosDarSeguimientoVotacion();
                    msgError = guardaFechaCierreSolAprobacion(true);
            break;
          case FenixConstants.PC04_CREAR_SOLICITUD_APROBACION:
              cargarDocumentosClienteOnBase(); 
              break;  
          case FenixConstants.PC04_ELEVAR_SOLICITUD_APROBACION:            
              break; 
          case FenixConstants.PC04_REVISAR_SOLICITUD_COMITE:              
              actualizarSCRRecomendadoRevisar();
              actualizarDatosSCR(aprobacionBean.getIdClienteFlex(),aprobacionBean.getNombreUsuario(),aprobacionBean.getLoginUsuario());              
              msgError = crearSolicitudAprobacionCliente(true);
              cargarDocumentosClienteOnBase(); 
              break;
          case FenixConstants.PC04_CONSOLIDAR_DECISION_COMITE:
              
              actualizarBanEstatusSCRAprobado();
              actualizarDatosSCR(aprobacionBean.getIdClienteFlex(),aprobacionBean.getNombreUsuario(),aprobacionBean.getLoginUsuario());
              msgError = guardarDatosConsolidarDecision(true, "por_cliente");
              if(msgError == null){
                  String accion = ADFUtils.getBoundAttributeValue("CodigoExternoVarAttr").toString();
                  ADFUtils.setBoundAttributeValue("accion", accion); 
              }
              cargarDocumentosClienteOnBase();    
              
              break;
          case FenixConstants.PC04_REALIZAR_MODIFICACIONES:
              cargarDocumentosClienteOnBase(); 
              break;
          case FenixConstants.PC04_REALIZAR_MODIFICAciONES_ANALISTA_CR:
              cargarDocumentosClienteOnBase(); 
              break;
          case FenixConstants.PC04_ERROR_PROPAGAR_SCR:
              cargarDocumentosClienteOnBase(); 
              break;          
          default:
                LOGGER.log(ADFLogger.ERROR, "finalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
            break;
          }
          
          if(null != msgError){
              LOGGER.log(ADFLogger.WARNING, "msgError no es null '" + msgError + "'");
              return null;
          }else{
              InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
              if (invokeActionBean != null) {
                  String invokeOperation = invokeActionBean.invokeOperation();
                  
                  if (invokeOperation != null) {
                      LOGGER.log(ADFLogger.WARNING, "invokeOperation '" + invokeOperation + "'");
                      return invokeOperation;
                  } else {
                      LOGGER.log(ADFLogger.WARNING, "invokeOperation es null.");
                      return null;
                  }
              } else {
                  LOGGER.log(ADFLogger.WARNING, "InvokeActionBean es null.");
                  return null;
              }  
          }
      }
    
    public void actualizarSCRRecomendadoRevisar() {
        LOGGER.warning("inside actualizarSCRRecomendadoRevisar.");
        
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        Integer valueiDTcaScr = null;
        
        valueiDTcaScr = aprobacionBean.getIdTcaScrRecomendado();
        
        LOGGER.warning("idTcaScrRecomendado: " + valueiDTcaScr);
        
        String tipoInicio = null;
        
        LOGGER.warning("idTipoInicio: " + aprobacionBean.getTipoInicio());
        
        if (valueiDTcaScr > 0) {
            if (aprobacionBean.getTipoInicio().compareTo(TI_MEJORA_SCR) == 0) {
                LOGGER.warning("idTcaScrRecomendado: " + aprobacionBean.getIdTcaScrRecomendado());

                if (aprobacionBean.getIdTcaScrRecomendado() != null) {
                    BindingContainer binding = getBindings();
                    OperationBinding operationBinding = binding.getOperationBinding("actualizarSCRRecomendado");
                    operationBinding.getParamsMap().put("idTcaScr", valueiDTcaScr);
                    operationBinding.execute();
                    LOGGER.warning("idTcaScrRecomendado Actualizado.");
                } else {
                    LOGGER.warning("idTcaScrRecomendado es null.");
                }
            }
        } else {
            LOGGER.warning("idTcaScrRecomendado es null.");
        }
    }
  
    public void actualizarBanEstatusSCRAprobado() {
        LOGGER.warning("Dentro actualizarBanEstatusSCRAprobado");
        
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        Number idTipoAccion = null;
        
        idTipoAccion = (Number)ADFUtils.getBoundAttributeValue(ID_ACCION_A_SEGUIR_ATRIBUTO);
        LOGGER.warning("idTipoAccion: " + idTipoAccion);
        
        if (idTipoAccion != null) {
            if (idTipoAccion.compareTo(19) == 0 || idTipoAccion.compareTo(1) == 0 ) {
                
                BindingContainer binding = getBindings();
                OperationBinding operationBinding = binding.getOperationBinding("actualizarBanEstatusSCRAprobado");
                operationBinding.execute();
                LOGGER.warning("BanEstatus Actualizado a 1.");
            }else{
                LOGGER.warning("idTipoAccion es diferente de 19 o 1");
            }
        } else {
            LOGGER.warning("idTipoAccion es null.");
        }
        
        LOGGER.warning("Fuera actualizarBanEstatusSCRAprobado");
    }
    
  /**
   *Cancelar Finalizar Tarea
   * @return
   */
    public String cancelarFinalizarTarea()
    {
        popupConfFinalizarTarea.hide();
        return null;
    }

    public String enviarAsjur() {
        // Add event code here...
        return null;
    }
    
    public Long getLIdOperacion()
    {
      try
      {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        return aprobacionBean.getLngIdOperacion();
      }
      catch (Exception ex)
      {
        return 0L;
      }
    }
    
    public Long getLIdSolicitudAprobacion()
    {
      try
      {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        return aprobacionBean.getLngIdSolicitudAprob();
      }
      catch (Exception ex)
      {
        return 0L;
      }
  }
    
    private Row getIteratorCurrentRow(String psIteratorName)
    {
      DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator(psIteratorName);
      ViewObject voTableData = dcItteratorBindings.getViewObject();
      return voTableData.getCurrentRow();
    }
    
    
    public void agregarCondicionPopup()
    {   
        
        Boolean esAgregarCondicion = null;
        try{
            esAgregarCondicion = (Boolean) ADFUtils.getBoundAttributeValue("esAgregarCondicionAttrValue");
        }catch(Exception e){
            LOGGER.warning("No se pudo obtener bandera de Accion Agregar Condicion");
        }
        
        if(esAgregarCondicion == null ||
           Boolean.FALSE.equals(esAgregarCondicion)){
            
            ADFUtils.setBoundAttributeValue("esAgregarCondicionAttrValue", true);
            
            AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);  
            aprobacionBean.setOperCondicion("C");
            aprobacionBean.setTituloPopup(getStringFromBundle("AGREGAR_CONDICION_TITULO"));
            
            Map<String, Object> params = new HashMap<String, Object>();
            OperationBinding operBinding  = executeOperBinding(params, CREAR_CONDICION_CAP_OPER);
            
            if(operBinding != null){
                
                //Agregar codigo de ejecución y validacion de resultado
            }else{
                LOGGER.warning("Operator Binding no definido: " + CREAR_CONDICION_CAP_OPER);
            }
            
            limpiarSeleccionCondicionPopup();
            
            //Refresca Popup
            AdfFacesContext.getCurrentInstance().addPartialTarget(popupAgregarModificarLeerCondicion);
            
            //Refresca Tabla de Condiciones
            AdfFacesContext.getCurrentInstance().addPartialTarget(getCondicionAprobacionTableUIC());
            
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            popupAgregarModificarLeerCondicion.show(hints);
        }
    } 
    
    public void leerCondicionPopup()
    {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);  
        aprobacionBean.setOperCondicion("R");
        aprobacionBean.setTituloPopup(getStringFromBundle("DETALLE_CONDICION_TITULO"));
        
        limpiarSeleccionCondicionPopup();
        asignarSeleccionCondicion();
        
        //Refresca popup
        AdfFacesContext.getCurrentInstance().addPartialTarget(popupAgregarModificarLeerCondicion);
        
        //Refresca Tabla de Condiciones
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCondicionAprobacionTableUIC());
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupAgregarModificarLeerCondicion.show(hints);
    } 
    
    public void editarCondicionPopup()
    {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);  
        aprobacionBean.setOperCondicion("E");
        aprobacionBean.setTituloPopup(getStringFromBundle("MODIFICAR_CONDICION_TITULO"));
        
        limpiarSeleccionCondicionPopup();
        asignarSeleccionCondicion();
        
        //Refresca Popup
        AdfFacesContext.getCurrentInstance().addPartialTarget(popupAgregarModificarLeerCondicion);
        
        //Refresca Tabla de Condiciones
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCondicionAprobacionTableUIC());
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupAgregarModificarLeerCondicion.show(hints);
    } 
    
    private void limpiarSeleccionCondicionPopup(){
        
        JUCtrlListBinding listBindings = null;
        try{
            listBindings = 
                (JUCtrlListBinding)getBindings().get("TcaCategoriaCondicionVO");
            listBindings.clearSelectedIndices();
            listBindings.refreshControl();
        }catch(Exception e){
            LOGGER.severe("Error al limpiar lista de seleccion de Categoria Condicion", e);
        }

        try{
            listBindings = 
                (JUCtrlListBinding)getBindings().get("TcaEventoCondicionLOV");
            listBindings.clearSelectedIndices();
            listBindings.refreshControl();
        }catch(Exception e){
            LOGGER.severe("Error al limpiar lista de seleccion de Eventos Condicion", e);
        }
        
        if(popupAgregarModificarLeerCondicion != null){
            //Restaura popup cuando content delivery lazyuncache falla
            oracle.adf.view.rich.util.ResetUtils.reset(popupAgregarModificarLeerCondicion);    
        }
    }
    
    public void eliminarCondicionPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupConfirmacionEliminarCondicion.show(hints);
    }

    @SuppressWarnings("unchecked")
    public void aceptarAgregarModificarLeerCondicionPopup() //REVM
    {   
        boolean isValid = false;
        boolean isException = false;
        boolean esLectura = false;
        String msgError = null;
        
        Number id = null;
        List idCategoriaCondList = null;
        List idEventosCondList = null;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        if(aprobacionBean != null){
            
            if(null != aprobacionBean.getOperCondicion() && 
               !aprobacionBean.getOperCondicion().equalsIgnoreCase("R"))
            {   
                JUCtrlListBinding listBindings = null;
                try{
                    listBindings = 
                        (JUCtrlListBinding)getBindings().get("TcaCategoriaCondicionVO");
                    int values[] = null;
                    if(listBindings != null){
                        values = listBindings.getSelectedIndices();    
                    }
                    if(values != null){
                        LOGGER.warning("Cantidad de Categorias Condicion Seleccionadas: " + 
                                       values.length);
                        Row row = null;
                        for (int index = 0; index < values.length; index++) {
                            row = listBindings.getRowAtRangeIndex(values[index]);
                            id = (Number) row.getAttribute("Id");
                            
                            if(idCategoriaCondList == null){
                                idCategoriaCondList = new ArrayList();
                            }
                            idCategoriaCondList.add(id);
                        }
                    }
                    
                    //Valida la seleccion de categorias
                    if(idCategoriaCondList != null &&
                       idCategoriaCondList.size() > 0){
                        isValid = true;
                    }else{
                        isValid = false;
                        msgError = getStringFromBundle("pc04aprobacion_actualizar_tcc_msg_error_categorias_vacio");
                    }
                }catch(Exception e){
                    LOGGER.severe("Error al iterar las categorias de condicion seleccionadas", e);
                    isException = true;
                }
                
                if(isValid){
                    try{
                        listBindings = 
                            (JUCtrlListBinding)getBindings().get("TcaEventoCondicionLOV");
                        int values[] = null;
                        if(listBindings != null){
                            values = listBindings.getSelectedIndices();    
                        }
                        
                        if(values != null){
                            LOGGER.warning("Cantidad de Eventos Condicion Seleccionados: " + 
                                           values.length);
                            Row row = null;
                            id = null;
                            for (int index = 0; index < values.length; index++) {
                                row = listBindings.getRowAtRangeIndex(values[index]);
                                id = (Number) row.getAttribute("TecId");
                                
                                if(idEventosCondList == null){
                                    idEventosCondList = new ArrayList();
                                }
                                idEventosCondList.add(id);
                            }
                        }
                        
                        if(idEventosCondList != null &&
                           idEventosCondList.size() > 0){
                            isValid = true;
                        }else{
                            isValid = false;
                            msgError = getStringFromBundle("pc04aprobacion_actualizar_tcc_msg_error_eventos_vacio");
                        }
                    }catch(Exception e){
                        LOGGER.severe("Error al iterar los Eventos de condicion seleccionados", e);
                        isException = true;
                    }
                }
                
                if(isValid){
                    
                    //Valida la Descripcion
                    String descripcion = null;
                    try{
                        descripcion = (String) ADFUtils.getBoundAttributeValue("Descripcion");
                    }catch(Exception e){
                        LOGGER.severe("Error no se pudo obtener valor del campo Descripcion");
                        isException = true;
                    }
                    
                    if(descripcion != null){
                        
                        if(descripcion.length() > 0 &&
                           descripcion.trim().isEmpty()){
                            LOGGER.warning("No se ha capturado una descripcion valida");
                            isValid = false;
                            msgError = getStringFromBundle("pc04aprobacion_actualizar_tcc_msg_error_descripcion_invalida");
                        }else{
                            isValid = true;    
                        }
                    }else{
                        if(descripcion == null){
                            LOGGER.warning("No se ha capturado descripcion");
                            isValid = false;
                            msgError = getStringFromBundle("pc04aprobacion_actualizar_tcc_msg_error_descripcion_vacio");    
                        }
                    }
                }
                
                if(isValid){
                    //Valida las Observaciones
                    String observaciones = null;
                    try{
                        observaciones = (String) ADFUtils.getBoundAttributeValue("Observaciones");    
                    }catch(Exception e){
                        LOGGER.severe("Error no se pudo obtener valor del campo Observaciones", e);
                        isException = true;
                    }
                    
                    if(observaciones != null){
                        if(observaciones.length() > 0 && 
                           observaciones.trim().isEmpty()){
                            LOGGER.warning("No se ha capturado observaciones validas");
                            isValid = false;
                            msgError = getStringFromBundle("pc04aprobacion_actualizar_tcc_msg_error_observacion_invalida");
                        }else{
                            isValid = true;    
                        }
                    }else{
                        if(observaciones == null){
                            LOGGER.warning("No se ha capturado observaciones");
                            //Se deshabilita la validacion de observaciones como requerido, peticion del cliente FNXII-2876
                            /*
                            isValid = false;
                            msgError = getStringFromBundle("pc04aprobacion_actualizar_tcc_msg_error_observacion_vacio");
                            */
                        }
                    }
                }
            }else{
                esLectura = true;
                isValid = true;
            }
        }else{
            LOGGER.severe("Error no se pudo obtener Managed Bean");
            isException = true;
        }
        
        if(isException){
            
            //Muestra mensaje del error
            msgError = getStringFromBundle("pc04aprobacion_actualizar_tcc_msg_error_ocurre_exception");
            JSFUtils.addFacesErrorMessage(msgError);
        }else{
            if(!isValid){
                //Muestra mensaje de validacion
                JSFUtils.addFacesErrorMessage(msgError);
            }else{
                
                if(!esLectura){
                    //Validacion correcta, se procede a la ejecucion al guardado de datos
                    Map<String, Object> params = new HashMap<String, Object>();
                    Row rowSelected = getIteratorCurrentRow(CONDICION_TABLE_ITERATOR);
                    
                    Integer intId = null;
                    if(rowSelected.getAttribute("Id") != null){
                        intId = (Integer) rowSelected.getAttribute("Id");    
                        LOGGER.warning("Obtiene el Id del Registro actual de Condicion: " + intId);
                    }
                    params.put("piId", intId);
                    params.put("pCategoriaCondList", idCategoriaCondList);
                    params.put("pEventosCondList", idEventosCondList);
                    
                    OperationBinding operBinding = executeOperBinding(params, ACEPTAR_AGREGAR_MODIFICAR_CONDICION_OPER);
                    if(operBinding != null){
                        
                        //Agregar codigo de ejecucion y validacion de resultado
                        
                    }else{
                        LOGGER.warning("Operator Binding no definido: " + ACEPTAR_AGREGAR_MODIFICAR_CONDICION_OPER);
                    }
                }
                popupAgregarModificarLeerCondicion.hide();
            }
        }
        //Refresca Tabla de Condiciones
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCondicionAprobacionTableUIC());
        
        ADFUtils.setBoundAttributeValue("esAgregarCondicionAttrValue", false);
    }
    
    public void cancelCondicionPopup()
    {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        Map<String, Object> params = new HashMap<String, Object>();
        OperationBinding operBinding;
        Row rowSelected = getIteratorCurrentRow(CONDICION_TABLE_ITERATOR);
        
        Integer intId = null;
        if(rowSelected.getAttribute("Id") != null){
            intId = (Integer) rowSelected.getAttribute("Id");
        }
        params.put("piId", intId);
      
        if(null != aprobacionBean.getOperCondicion() && aprobacionBean.getOperCondicion().equalsIgnoreCase("C"))
        {   
            operBinding  = executeOperBinding(params, CANCELAR_CONDICION_CAP_OPER);
        }
        
        if(null != aprobacionBean.getOperCondicion() && aprobacionBean.getOperCondicion().equalsIgnoreCase("E"))
        {
            operBinding  = executeOperBinding(params, CANCELAR_CONDICION_ED_OPER);
        }
        
        popupAgregarModificarLeerCondicion.hide();
        
        //Refresca Tabla de Condiciones
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCondicionAprobacionTableUIC());
        
        ADFUtils.setBoundAttributeValue("esAgregarCondicionAttrValue", false);
    }
    
    public void aceptarEliminarCondicionPopup() //REVM
    {
        Row rowSelected = getIteratorCurrentRow(CONDICION_TABLE_ITERATOR);
        
        if(rowSelected != null){
            LOGGER.warning("Row Id a Eliminar: " + rowSelected.getAttribute("Id"));    
        }
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("piId", rowSelected.getAttribute("Id"));
        
        OperationBinding operBinding  = executeOperBinding(params, ACEPTAR_ELIMINAR_CONDICION_OPER);
        if(operBinding != null){
            
            //Agregar codigo de ejecucion y validacion del resultado
            
        }else{
            LOGGER.warning("Operator Binding no definido: " + ACEPTAR_ELIMINAR_CONDICION_OPER);
        }
        
        popupConfirmacionEliminarCondicion.hide();
        
        //Refresca Tabla de Condiciones
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCondicionAprobacionTableUIC());
    }
    
    public void cancelEliminarCondicionPopup()
    {
        popupConfirmacionEliminarCondicion.hide();
    }
    
    public void tipoPlazoValueChangeListener(ValueChangeEvent valueChangeEvent) 
    {
      Row rowSelected = getIteratorCurrentRow(CONDICION_TABLE_ITERATOR);
      
      //Map<String, Object> params = new HashMap<String, Object>(); //Declaracion no utilizada
      
      rowSelected.setAttribute("TipoPlazoId", valueChangeEvent.getNewValue());
      
      if(null!=valueChangeEvent.getNewValue() && ((oracle.jbo.domain.Number)valueChangeEvent.getNewValue()).floatValue() == 4)
      {
        rowSelected.setAttribute("Plazo", null);
      }
      
      if(null!=valueChangeEvent.getNewValue() && ((oracle.jbo.domain.Number)valueChangeEvent.getNewValue()).floatValue() == 4)
      {
        LOGGER.warning(""+valueChangeEvent.getNewValue());
        AttributeBinding attributeBinding = null;
        
        attributeBinding = ADFUtils.findControlBinding("Plazo1");
        
        if(attributeBinding!=null)
            attributeBinding.setInputValue(null);
      }
      
      valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance()); // update model values
    }
    
    public boolean guardarFormalizacionAction()
    {
        boolean exito = false;
        List<String> budleKeyErroList = new ArrayList<String>();
        boolean esValido = validarActualizarTCC(budleKeyErroList);
        
        if(esValido){
            Map<String, Object> params = new HashMap<String, Object>();
            OperationBinding operBinding;
            params.put("plIdOperacion", getIdOperacion());
            
            operBinding  = executeOperBinding(params, CREAR_ACTUALIZAR_CONDICION_OPER);
            
            if(!(operBinding.getErrors().size()>0))
            {
              String msg = getStringFromBundle("LBL_GUARDAR_DATOS_OK");
              JSFUtils.addFacesInformationMessage(msg);
              exito = true;
            }
        }else{
            if(budleKeyErroList.size()>0) {
                for(String bundleKey : budleKeyErroList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        }
        return exito;
    }
    
    public void eliminarLineaPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupConfirmacionEliminarLinea.show(hints);
    }
    
    public void aceptarEliminarLineaPopup() //REVM
    {
        Row rowSelected = getIteratorCurrentRow(LINEA_TABLE_ITERATOR);
        
        if(rowSelected != null){
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("piIdLinea", rowSelected.getAttribute("Id"));
            
            OperationBinding operBinding  = executeOperBinding(params, ELIMINAR_LINEA_OPER);
            if(operBinding != null){
                
                //Agregar codigo de ejecucion y validacion del resultado
                
            }else{
                LOGGER.warning("Operator Binding no definido: " + ELIMINAR_LINEA_OPER);
            }
            
            //Refresca la tabla de lineas de credito
            AdfFacesContext.getCurrentInstance().addPartialTarget(getLineasCreditoTableUIC());
        }else{
            LOGGER.warning("No se encontro current row para eliminar");
        }
        
        popupConfirmacionEliminarLinea.hide();
    }
    
    public void cancelEliminarLineaPopup()
    {
        popupConfirmacionEliminarLinea.hide();
    }
     
    public void agregarLineaPopup()
    {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);  
        aprobacionBean.setOperLineaCreacion(Boolean.TRUE);
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        
        //Restaura popup cuando content delivery lazyuncache falla
        oracle.adf.view.rich.util.ResetUtils.reset(popupAgregarModificarLinea);
        //[kb: 13306]
        //Asigna el tipoMoneda de la aprobación a la variable del pageDef de líneas para que la lista se muestre seleccionada
        //Primero se asegura de que exista en el pageDef la variable, ya que pueden haber páginas del proceso con líneas que no tienen requieren tipoMoneda
        if(ADFUtils.findControlBinding(TIPO_MONEDA_LC_ATTR) != null){
            ADFUtils.setBoundAttributeValue(TIPO_MONEDA_LC_ATTR, ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda"));
        }
        
        popupAgregarModificarLinea.show(hints);
    }
    
    public void modificarLineaPopup()
    {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);  
        aprobacionBean.setOperLineaCreacion(Boolean.FALSE);
        
        Row rowSelected = getIteratorCurrentRow(LINEA_TABLE_ITERATOR);
        
        if(rowSelected != null){
            this.setNumeroLinea(null!=rowSelected.getAttribute("Numero")?rowSelected.getAttribute("Numero").toString():"");
            this.setDescripcionLinea(null!=rowSelected.getAttribute("Descripcion")?rowSelected.getAttribute("Descripcion").toString():"");
            this.setMontoLinea(new BigDecimal ((null!=rowSelected.getAttribute("Monto")?rowSelected.getAttribute("Monto").toString():"0")));
            //[kb: 13306]
            //Asigna el tipoMoneda de la LC desde la row selected a la variable del pageDef para que el popup la muestre correctamente
            //Primero se asegura de que exista en el pageDef la variable, ya que pueden haber páginas del proceso con líneas que no tienen requieren tipoMoneda
            if(ADFUtils.findControlBinding(TIPO_MONEDA_LC_ATTR) != null){
                ADFUtils.setBoundAttributeValue(TIPO_MONEDA_LC_ATTR, (Integer)rowSelected.getAttribute("IdTcaTipoMoneda"));
            }
        }else{
            LOGGER.warning("No se encontro registro que actualizar");
        }
        
        //Restaura popup cuando content delivery lazyuncache falla
        oracle.adf.view.rich.util.ResetUtils.reset(popupAgregarModificarLinea);
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupAgregarModificarLinea.show(hints);
    }
    
    public void aceptarAgregarModificarLineaPopup() //REVM
    {
      LOGGER.entering(AprobacionActionBean.class.getName(), "aceptarAgregarModificarLineaPopup");
      
      LOGGER.warning(this.getNumeroLinea());
      LOGGER.warning(this.getDescripcionLinea());
      //Primero se asegura de que exista en el pageDef la variable, ya que pueden haber páginas del proceso con líneas que no tienen requieren tipoMoneda
      if(ADFUtils.findControlBinding(TIPO_MONEDA_LC_ATTR) != null){
        LOGGER.warning("Tipo Moneda LC: {0}", ADFUtils.getBoundAttributeValue(TIPO_MONEDA_LC_ATTR));
      }
      LOGGER.warning("pdMonto" + (null!=this.getMontoLinea()? this.getMontoLinea().toString() :""));
      
      AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);  
      OperationBinding operBinding;
      
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("psNumeroLinea", this.getNumeroLinea());
      params.put("psDecripcion", this.getDescripcionLinea());
      params.put("pdMonto", this.getMontoLinea());
      //[kb: 13306]
      //Primero se asegura de que exista en el pageDef la variable, ya que pueden haber páginas del proceso con líneas que no tienen requieren tipoMoneda
      if(ADFUtils.findControlBinding(TIPO_MONEDA_LC_ATTR) != null){
        params.put("piIdTipoMoneda", ADFUtils.getBoundAttributeValue(TIPO_MONEDA_LC_ATTR));
      }
      
      if(aprobacionBean.getOperLineaCreacion())
      {
        operBinding  = executeOperBinding(params, CREAR_LINEA_OPER);
      }
      else
      {
        Row rowSelected = getIteratorCurrentRow(LINEA_TABLE_ITERATOR);
        if(null != rowSelected){
            params.put("piId", rowSelected.getAttribute("Id"));
            operBinding  = executeOperBinding(params, MODIFICAR_LINEA_OPER); 
        }else{
            LOGGER.warning("No se encontro registro que modificar");    
        }
      }
      
      LOGGER.exiting(AprobacionActionBean.class.getName(),"aceptarAgregarModificarLineaPopup");
      
      //Refresca tabla de lineas de credito
      AdfFacesContext.getCurrentInstance().addPartialTarget(getLineasCreditoTableUIC());
      
      popupAgregarModificarLinea.hide();
    }
    
    public void cancelLineaPopup()
    {
        popupAgregarModificarLinea.hide();
    }

    @SuppressWarnings("unchecked")
    public boolean guardarAprobacionAction() {
        LOGGER.warning("*Inf, inicia el metodo guardarAprobacionAction");
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        Row currentRow = getCurrentRegistrarDatosAprobacionVO();
        boolean exito = false;
        Long idAprobacion = null;

        idAprobacion = aprobacionBean.getAprobacionId();
         
        if (idAprobacion == null) {
            LOGGER.warning("Recuperar current idAprobacio.n");
            
            try {
                idAprobacion = (Long) currentRow.getAttribute("AprobacionId");
                LOGGER.warning("idAprobacion: " + currentRow.getAttribute("AprobacionId"));
            } catch (Exception ex) {
                LOGGER.severe("No se pudo obtener el Id de Aprobacion");
            }    
        }
        
        LOGGER.warning("idAprobacion: " + idAprobacion);

        Map<String, Object> params = new HashMap<String, Object>();
        OperationBinding operBinding;

        if (null != idAprobacion && idAprobacion > 0) {
            LOGGER.warning("*Inf modificando aprobacion");
            operBinding = executeOperBinding(params, MODIFICAR_APROBACION_OPER);
        } else {
            LOGGER.warning("*Inf creado aprobacion");
            operBinding = executeOperBinding(params, CREAR_APROBACION_OPER);
        }

        if (!operBinding.getErrors().isEmpty()) {
            exito = false;
            JSFUtils.addFacesErrorMessage(getStringFromBundle("LBL_GUARDAR_DATOS_ERROR"));
        } else {
            exito = true;
        }

        Integer codigoTarea = getCodigoTarea();

        if (null != codigoTarea && codigoTarea == FenixConstants.PC04_CONSOLIDARDECISIONDIRECTORIO) {

            LOGGER.warning("Cargando datos de Aprobacion para Consolidar Decision Directorio");
            
            Number idAccionSeguir = null;
            
            try {
                idAccionSeguir = (Number) currentRow.getAttribute("IdAccionSeguir");
                LOGGER.warning("idAccionSeguir: " + idAccionSeguir);            
                            
            } catch(Exception e) {
                LOGGER.warning("No se pudo obtener el Id de Accion a Seguir");
            }
            
            Long idOperacion = Long.parseLong(aprobacionBean.getIdOperacion());
            Long idSolicitud = Long.parseLong(aprobacionBean.getIdSolicitud());
            Integer idNivelAprobacion = aprobacionBean.getIntIdNivelAprob();
            Integer idTipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();

            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("consultarAprobacion");
            operationBinding.getParamsMap().put("plIdOperacion", idOperacion);
            operationBinding.getParamsMap().put("piIdSolicitudAprobacion", idSolicitud);
            operationBinding.getParamsMap().put("piIdNivelAprobacion", idNivelAprobacion);
            operationBinding.getParamsMap().put("piTipoSolicitud", idTipoSolicitud);
            operationBinding.execute();

            if (idAccionSeguir != null) {
                LOGGER.warning("Asignando accion a seguir. " + idAccionSeguir);
                
                Row currentRowNew = this.getIteratorCurrentRow(DATOS_APROBACION_ITERATOR);
                currentRowNew.setAttribute("IdAccionSeguir", idAccionSeguir);
            }
        }
        LOGGER.warning("*Inf, termina el metodo guardarAprobacionAction");
        return exito;
    }
    
    public Row getCurrentRegistrarDatosAprobacionVO(){
      Row fila  = null;    
       
       try{
         BindingContainer binding = getBindings();
         OperationBinding operationBinding = binding.getOperationBinding("getCurrentRegistrarDatosAprovacionVO");       
         fila = (Row)operationBinding.execute();      
         
       }catch(Exception e){
           LOGGER.warning("*Inf, ha ocurrido un error al recuperar el row de registrarDatosAprobacionVO");
       }
    
      return fila;
    }
    
    public void solicitarMasInfoPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupConfMasInfo.show(hints);
    }
    
    public void finalizarTareaPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupConfFinalizarTarea.show(hints);
    }
    
    public void requiereAEDPopup()
    {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupRequiereAED.show(hints);
    }

    private Integer getCodigoTarea()
    {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
  
        if (null != aprobacionBean.getIdTarea() && aprobacionBean.getIdTarea().trim().length() > 0)
        {
            return Integer.parseInt(aprobacionBean.getIdTarea());
        }
  
        return 0;
    }
  
    private Long getIdOperacion()
    {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
      
        if (null != aprobacionBean.getIdOperacion() && aprobacionBean.getIdOperacion().trim().length() > 0)
        {
            return Long.parseLong(aprobacionBean.getIdOperacion());
        }
  
        return 0L;
    }
    
  
    public void setPopupConfFinalizarTarea(RichPopup popupConfFinalizarTarea)
    {
      this.popupConfFinalizarTarea = popupConfFinalizarTarea;
    }
  
    public RichPopup getPopupConfFinalizarTarea()
    {
      return popupConfFinalizarTarea;
    }
  
    public void setTipoReunionUIComponent(RichSelectOneChoice tipoReunionUIComponent) {
        this.tipoReunionUIComponent = tipoReunionUIComponent;
    }

    public RichSelectOneChoice getTipoReunionUIComponent() {
        return tipoReunionUIComponent;
    }

    public void setPanelFormPresencialUIC(RichPanelFormLayout panelFormPresencialUIC) {
        this.panelFormPresencialUIC = panelFormPresencialUIC;
    }

    public RichPanelFormLayout getPanelFormPresencialUIC() {
        return panelFormPresencialUIC;
    }

    public void setPanelFormVirtualUIC(RichPanelFormLayout panelFormVirtualUIC) {
        this.panelFormVirtualUIC = panelFormVirtualUIC;
    }

    public RichPanelFormLayout getPanelFormVirtualUIC() {
        return panelFormVirtualUIC;
    }
    
    public void setPanelFormDecisionUIC(RichPanelFormLayout panelFormDecisionUIC) {
        this.panelFormDecisionUIC = panelFormDecisionUIC;
    }

    public RichPanelFormLayout getPanelFormDecisionUIC() {
        return panelFormDecisionUIC;
    }

    public void setNumAcuerdoUIC(RichInputText numAcuerdoUIC) {
        this.numAcuerdoUIC = numAcuerdoUIC;
    }

    public RichInputText getNumAcuerdoUIC() {
        return numAcuerdoUIC;
    }

    public void setNumActaUIC(RichInputText numActaUIC) {
        this.numActaUIC = numActaUIC;
    }

    public RichInputText getNumActaUIC() {
        return numActaUIC;
    }

    public void setAcuerdoUIC(RichInputText acuerdoUIC) {
        this.acuerdoUIC = acuerdoUIC;
    }

    public RichInputText getAcuerdoUIC() {
        return acuerdoUIC;
    }

    public void setNumActaLabelUIC(RichOutputText numActaLabelUIC) {
        this.numActaLabelUIC = numActaLabelUIC;
    }

    public RichOutputText getNumActaLabelUIC() {
        return numActaLabelUIC;
    }

    public void setNumAcuerdoLabelUIC(RichOutputText numAcuerdoLabelUIC) {
        this.numAcuerdoLabelUIC = numAcuerdoLabelUIC;
    }

    public RichOutputText getNumAcuerdoLabelUIC() {
        return numAcuerdoLabelUIC;
    }
    
    public void setNumAcuerdoRenderProp(String numAcuerdoRenderProp) {
        this.numAcuerdoRenderProp = numAcuerdoRenderProp;
    }

    public String getNumAcuerdoRenderProp() {
        
        boolean isVisible = false;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        Number idTipoAccion = 
            (Number)ADFUtils.getBoundAttributeValue(ID_ACCION_A_SEGUIR_ATRIBUTO);
        
        if(null != aprobacionBean){
            
            if(null != aprobacionBean.getIntTipoReunion() &&
               aprobacionBean.getIntTipoReunion().equals(ID_REUNION_PRESENCIAL.intValue())){
                isVisible = true;
            }else{
                if(null != aprobacionBean.getIntTipoReunion() &&
                   aprobacionBean.getIntTipoReunion().equals(ID_REUNION_VIRTUAL.intValue())){
                    
                    if(null != idTipoAccion){
                        if(!ID_ACCION_RETORNAR.equals(idTipoAccion.intValue())){
                            isVisible = true;
                        }
                    }else{
                        isVisible = true;
                    }
                }
            }
        }
        
        numAcuerdoRenderProp = String.valueOf(isVisible);
        return numAcuerdoRenderProp;
    }

    public void setNumActaRenderProp(String numActaRenderProp) {
        this.numActaRenderProp = numActaRenderProp;
    }

    public String getNumActaRenderProp() {
        
        boolean isVisible = false;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        Number idTipoAccion = 
            (Number)ADFUtils.getBoundAttributeValue(ID_ACCION_A_SEGUIR_ATRIBUTO) ;
        
        if(null != aprobacionBean){
            
            if(null != aprobacionBean.getIntTipoReunion() &&
               aprobacionBean.getIntTipoReunion().equals(ID_REUNION_PRESENCIAL.intValue())){
                
                if(null != idTipoAccion){
                    if(!ID_ACCION_RETORNAR.equals(idTipoAccion.intValue())){
                        isVisible = true;
                    }
                }else{
                    isVisible = true;
                }
            }
        }
        
        numActaRenderProp = String.valueOf(isVisible);
        return numActaRenderProp;
    }

    public String realizarNuevaSolicitud() {
        // Add event code here...
        List<String> budleKeyErroList = new ArrayList<String>();
        
        Boolean nuevaSolicitud = Boolean.FALSE;
        
        //Valida si ya se agrego un comentario de reunion en la session actual
        boolean emitioComentario = false;
        String value = null;
        
        try{
            if(ADFUtils.getBoundAttributeValue("emiteComentarioAttr") != null){
                value = ADFUtils.getBoundAttributeValue("emiteComentarioAttr").toString();    
                LOGGER.warning("Valor indicador de comentario agregado: " + value);
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener valor bandera que indica que se agrego comentario", e);
        }
        
        if(value != null){
            emitioComentario = Boolean.valueOf(value);
            if(!emitioComentario){
                budleKeyErroList.add(MSG_ERROR_COMENTARIO_REQUERIDO_VOTO_NEGATIVO);
                nuevaSolicitud = false;
            }else{
                LOGGER.warning("Validacion de comentario valido");
                nuevaSolicitud = true;
            }
        }else{
            budleKeyErroList.add(MSG_ERROR_COMENTARIO_REQUERIDO_VOTO_NEGATIVO);
            nuevaSolicitud = false;
        }
        
        if (!nuevaSolicitud)
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

    public String cerrarDecision() {
        // Add event code here...
        return null;
    }

    public void setListViewComentariosReunion(RichListView listViewComentariosReunion) {
        this.listViewComentariosReunion = listViewComentariosReunion;
    }

    public RichListView getListViewComentariosReunion() {
        return listViewComentariosReunion;
    }

    public String editarDecisionFueraSistema() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public String agregarDecisionFueraSistema() {
        
        boolean esVotoPermitido = false;
        esVotoPermitido = validaVotoPorRangoFecha("FechaInicio",
                                                  "FechaTermino",
                                                  true);
        
        if(esVotoPermitido){
            
            oracle.adf.view.rich.util.ResetUtils.reset(getPopupAgregarModificarDecision());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupAgregarModificarDecision());
            
            AprobacionBean aprobacionBean =
                (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            
            String idSolicitud = aprobacionBean.getIdSolicitud();
            String idRolBpm = aprobacionBean.getIdRolBPM();
            String usuario =  ADFContext.getCurrent().getSecurityContext().getUserName();
            
            //Se inicializan los VO's para VotoAprobacionVO y ComentarioReunionVO
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("inicializarAgregarVotoFueraSistema");
            operationBinding.getParamsMap().put("idRolBpm", idRolBpm);
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.getParamsMap().put("usuarioLogin", usuario);
            operationBinding.execute();
            
            try{
                operationBinding = binding.getOperationBinding("UsuReuRolBuscarPorIdSolicitud");
                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                operationBinding.getParamsMap().put("emiteVoto", 1);
                operationBinding.execute();     
            }catch(Exception e){
                LOGGER.severe("Error al ejecutar Operator Binding UsuReuRolBuscarPorIdSolicitud", e);
            }

            aprobacionBean.setTituloPopup(getStringFromBundle("LBL_AGREGAR_DECISION_FUERA_DE_SISTEMA"));
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupAgregarModificarDecision().show(popupHints);
        }
        
        return null;
    }
    
    /**
     * Valida la realizacion de la votacion dentro del rango de fechas permitido
     * @param fechaInicioAttrName contiene nombre de atributo de page definitions que contiene fecha de inicio o
     *                            fecha de apertura
     * @param fechaFinAttrName contiene nombre de atributo de page definitions que contiene fecha fin o de termino
     * @param mostrarMsg indica si se desea mostrar mensaje en pantalla de la validacion
     * @return devuelve true si el voto es permitido o false en caso contrario
     */
    public boolean validaVotoPorRangoFecha(String fechaInicioAttrName,
                                           String fechaFinAttrName,
                                           boolean mostrarMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validaVotoPorRangoFecha",
                        new Object[]{fechaInicioAttrName,
                                     fechaFinAttrName,
                                     mostrarMsg});
        
        boolean esValido = false;
        Date fechaInicio = null;
        Date fechaFin = null;
        Date fechaActual = null;
        String msgError = null;
        fechaActual = getCurrentDate();
        
        try{
            fechaInicio = (Date) ADFUtils.getBoundAttributeValue(fechaInicioAttrName);
            fechaInicio = normalizarFecha(fechaInicio);
        }catch(Exception e){
            LOGGER.severe("Error al obtener fecha de inicio. Nombre de atributo: " + fechaInicioAttrName, e);
        }
        
        try{
            fechaFin = (Date) ADFUtils.getBoundAttributeValue(fechaFinAttrName);
            fechaFin = normalizarFecha(fechaFin);
        }catch(Exception e){
            LOGGER.severe("Error al obtener fecha de fin. Nombre de atributo: " + fechaFinAttrName, e);
        }
        
        if(fechaInicio != null){
            if(fechaInicio.compareTo(fechaActual) > 0){
                msgError = 
                    getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_valida_voto_fuera_rango_fecha");
            }else{
                
                if(fechaFin != null){
                    if(fechaFin.compareTo(fechaActual) < 0){
                        msgError = 
                            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_valida_voto_fuera_rango_fecha");
                    }else{
                        LOGGER.warning("Voto valido dentro del rango permitido");
                        esValido = true;
                    }
                }else{
                    msgError =
                        getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_valida_voto_fecha_cierre_null");
                }
            }
        }else{
            msgError = 
                getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_valida_voto_fecha_inicio_null");
        }
        
        if(msgError != null){
            LOGGER.severe("Mensaje de Error: " + msgError);
            if(mostrarMsg){
                LOGGER.warning("Mensaje de error mostrado en pantalla");
                JSFUtils.addFacesErrorMessage(msgError);
            }
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validaVotoPorRangoFecha",
                       esValido);
        return esValido;
    }
    
    /**
     * Normaliza una fecha de tipo <class>java.util.Date</class> para que no contenga informacion de hora, 
     * minutos y segundos
     * @param fecha contiene objeto fecha
     * @return devuelve objeto fecha
     */
    public Date normalizarFecha(Date fecha){
        
        if(fecha != null){
            //Normaliza fechas
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
            fecha = cal.getTime();
        }
        return fecha;
    }

    public void setPopupAgregarModificarDecision(RichPopup popupAgregarModificarDecision) {
        this.popupAgregarModificarDecision = popupAgregarModificarDecision;
    }

    public RichPopup getPopupAgregarModificarDecision() {
        return popupAgregarModificarDecision;
    }

    @SuppressWarnings("unchecked")
    public void dialogListenerAgregarModificarDecision(DialogEvent dialogEvent) {
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {
            
            boolean isError = false;
            String error = 
                getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_agregar_voto");
            String idSolicitud = null;
            String idOperacion = null;
            Integer tipoSolicitud = null;
            String idUsuarioReunion = null;
            
            idSolicitud = aprobacionBean.getIdSolicitud();
            idOperacion = aprobacionBean.getIdOperacion();
            
            try{
              tipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();
                if(tipoSolicitud == null){
                    error=error.concat(" " +
            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_tipo_solicitud_vacio") +
                                       ".");
                }
            }catch(Exception e){
                isError = true;
                LOGGER.severe(" Error al obtener el Id de Tipo Solicitud");
                error=error.concat(" " +
            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_tipo_solicitud_vacio") +
                                   ".");
            }
            
            if(ADFUtils.getBoundAttributeValue("IdUsuarioReunionVarAttr") != null){
              idUsuarioReunion = ADFUtils.getBoundAttributeValue("IdUsuarioReunionVarAttr").toString();    
            }else{
              LOGGER.severe("Id de Usuario Reunion no obtenido para agregar el Voto fuera de Sistema");    
              error=error.concat(" " + 
            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_voto_usuario_vacio") +
                                 ".");
              isError = true;
            }
            
            AttributeBinding desicionPresidenteVoto;
            desicionPresidenteVoto = ADFUtils.findControlBinding("IdTcaTipoDecision");
            if(desicionPresidenteVoto != null){
              if(null == desicionPresidenteVoto.getInputValue()){
                error=error.concat(" " + 
            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_tipo_decision_vacio") +
                                   ".");
                isError = true;
              }
            }else{
                error=error.concat(" " +
            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_tipo_decision_vacio") +
                                   ".");
                isError = true;
            }
            
            Boolean resultado = Boolean.FALSE;
            BindingContainer binding = getBindings();
            if(!isError){
                
                OperationBinding operationBinding = binding.getOperationBinding("agregarVotoFueraSistema");
                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                operationBinding.getParamsMap().put("idUsuarioReunion", idUsuarioReunion);
                
                resultado = (Boolean) operationBinding.execute();
                
                if(operationBinding.getErrors().isEmpty()){
                  if(resultado == null){
                      resultado = (Boolean) operationBinding.getResult();
                      if(resultado != null){
                          if(resultado){
                              LOGGER.warning("El voto fue guardado correctamente");
                              String msg = 
                        getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_agregar_voto");
                              JSFUtils.addFacesInformationMessage(msg);
                          }else{
                              LOGGER.severe("El voto no fue guardado");
                              error = 
                    getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_agregar_voto");
                          }
                      }else{
                          resultado = Boolean.FALSE;
                      }
                  }
                }else{
                  resultado = Boolean.FALSE;
                }
            }

            if(!resultado){
                JSFUtils.addFacesErrorMessage(error);
            }else{
                
                //Reinicia valores de pantalla
                OperationBinding operationBindingComentario = 
                    binding.getOperationBinding("iniDarSeguimientoPresidencia");
                operationBindingComentario.getParamsMap().put("idSolicitud", idSolicitud);
                operationBindingComentario.getParamsMap().put("idOperacion", idOperacion);
                operationBindingComentario.getParamsMap().put("tipoSolicitud", tipoSolicitud);
                operationBindingComentario.execute();
            }
            
            aprobacionBean.setComentario("");
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(getListViewComentariosReunion());
            AdfFacesContext.getCurrentInstance().addPartialTarget(aprobacionBean.getTreeTabBind());
            
            //Verifica el voto positivo o negativo del Presidente
            esPositivoVotoPresidente();
            
            //Actualiza componente para mostrar la Accion a Seguir.
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelFormAccionPresidenteUIC());
        }
        getPopupAgregarModificarDecision().hide();
    }
    
    public void cancelPopupAgregarModificarDecision(PopupCanceledEvent popupCanceledEvent) {
        
        if(popupCanceledEvent == null){
            return;
        }
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        try{
            // write your custom code for cancel event
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("cancelarVotoFueraSistema");
            operationBinding.execute();
            
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getTreeTableHistorico());
            if(aprobacionBean.getTreeTabBind() != null){
                AdfFacesContext.getCurrentInstance().addPartialTarget(aprobacionBean.getTreeTabBind());    
            }
        }catch(Exception e){
            LOGGER.severe("Error al cancelar Popup de Agregar Modificar Voto", e);
        }
        getPopupAgregarModificarDecision().hide();
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        currentDate = cal.getTime();
        return currentDate;
    }
    
    public void setMinFechaInicio(Date minFechaInicio) {
        this.minFechaInicio = minFechaInicio;
    }
    
    /**
     * Obtiene la fecha minima para agendar una reunion
     * @return devuelve objeto <class>java.util.Date</class> de la fecha minima
     */
    public Date getMinFechaInicio() {
        
        String tipoReunion = null;
        
        try{
            tipoReunion = ADFUtils.getBoundAttributeValue(CVE_TIPO_REUNION_ATRIBUTO).toString();
        }catch(Exception e){
            LOGGER.warning("Tipo reunion no seleccionado para calcular fecha minima");
        }
        
        Date currentDate = getCurrentDate();
        
        Calendar fechaFinal = Calendar.getInstance();
        int sumaDias = 1;
        int dias = 0;
        
        if("1".equals(tipoReunion)){
            //Si la reunion es presencial la fecha minima es despues de 5 dias habiles a la fecha actual
            //dias = 5;
            
            //Se deshabilita validacion de 5 dias habiles
            dias = 0;
        }else{
            if("2".equals(tipoReunion)){
                //Si la reunion es virtual la fecha minima es despues de 3 dias habiles a la fecha actual
                //dias = 3;
                
                //Se deshabilita validacion de 3 dias habiles
                dias = 0;
            }
        }
        
        if(dias > 0){
            while(sumaDias <= dias){ 
                if(fechaFinal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                   fechaFinal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    fechaFinal.add(Calendar.DATE, 1);
                }else{
                    fechaFinal.add(Calendar.DATE, 1);
                    sumaDias = sumaDias + 1;
                }
            }
            minFechaInicio = fechaFinal.getTime();
        }else{
            minFechaInicio = currentDate;
        }
        return minFechaInicio;
    }
    
    public void setMinFechaCierre(Date minFechaCierre) {
        this.minFechaCierre = minFechaCierre;
    }

    public Date getMinFechaCierre() {
        Date fechaApertura = (Date) getFechaAperturaRVirtual().getValue();
        if(null != fechaApertura){
            minFechaCierre = fechaApertura;
        }else{
            minFechaCierre = getCurrentDate();
        }
        return minFechaCierre;
    }
    
    public void setMaxFechaApertura(Date maxFechaApertura) {
        this.maxFechaApertura = maxFechaApertura;
    }

    public Date getMaxFechaApertura() {
        Date fechaCierre = (Date) getFechaCierreRVirtual().getValue();
        maxFechaApertura = fechaCierre;
        return maxFechaApertura;
    }
    
    /**
     * Ejecuta la busqueda para el autocompletado del campo Lugar de la Reunion
     * @param string contiene caracter a buscar
     * @return devuelve lista de objetos
     */
    public List onSuggest(String string) {
        
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();;
        SelectItem element = null;
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("LugarReunionList"); 

        Row[] values = list.getAllRowsInRange();        
        if(null != values){
            for(Row obj : values){
                String value = (String) obj.getAttribute("Descripcion");
                if(null != string &&
                   null != value){
                    if(value.toUpperCase().startsWith(string.toUpperCase())){
                        element = new SelectItem(value);
                        resultItems.add(element);
                    }
                }
            }
        }else{
            element = new SelectItem("");
            resultItems.add(element);
        }
        
        return resultItems;
    }

    public void setTreeTableHistorico(RichTreeTable treeTableHistorico) {
        this.treeTableHistorico = treeTableHistorico;
    }

    public RichTreeTable getTreeTableHistorico() {
        return treeTableHistorico;
    }
    
    /**
     * Valida el valor modificado del campo Hora para que el valor tenga un formato de 24 horas
     * @param valueChangeEvent contiene evento del componente UI
     */
    public void horaUICValueChangeListener(ValueChangeEvent valueChangeEvent) {
        
        if(null != valueChangeEvent){
            
            String value = (String) valueChangeEvent.getNewValue();
            String expression = "^([01][0-9]|2[0-3]):[0-5][0-9]$";
            CharSequence inputStr = value;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(inputStr);
            if(!matcher.matches()){
                String msg = getStringFromBundle("pc04aprobacion_revision_solicitud_comite_credito_form_presencial_hora_hints");
                JSFUtils.addFacesErrorMessage(msg);
                
                getHoraRPresencial().resetValue();
                getHoraRPresencial().setValue(null);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getHoraRPresencial());
            }
        }
    }

  public void setPopupAgregarModificarLinea(RichPopup popupAgregarModificarLinea)
  {
    this.popupAgregarModificarLinea = popupAgregarModificarLinea;
  }

  public RichPopup getPopupAgregarModificarLinea()
  {
    return popupAgregarModificarLinea;
  }

  public void setPopupConfirmacionEliminarLinea(RichPopup popupConfirmacionEliminarLinea)
  {
    this.popupConfirmacionEliminarLinea = popupConfirmacionEliminarLinea;
  }

  public RichPopup getPopupConfirmacionEliminarLinea()
  {
    return popupConfirmacionEliminarLinea;
  }
  
    public String getFechaMinString() {
        fechaMinString = DateToString(getMinFechaInicio());
        return fechaMinString;
    }

    private static String DateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        String string = df.format(date);
        return string;
    }

    private static Calendar DateToCalendar(Date date){ 
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      return cal;
    }
    
    public void setComiteTableUIC(RichTable comiteTableUIC) {
        this.comiteTableUIC = comiteTableUIC;
    }

    public RichTable getComiteTableUIC() {
        return comiteTableUIC;
    }

    public void setObservacionUIC(RichInputText observacionUIC) {
        this.observacionUIC = observacionUIC;
    }

    public RichInputText getObservacionUIC() {
        return observacionUIC;
    }
    
    public void miembroAprobSelectListener(ValueChangeEvent valueEvent){
        LOGGER.warning("Dentro de miembroAprobSelectListener");
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        valueEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        //Solo cuando sea la tarea Dar seguimiento a votacion o Dar seguimiento a decision presidente
        if(null != aprobacionBean){
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}")){
                if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}").equals(
                                    FenixConstants.PC04_DARSEGUIMIENTOVOTACION) 
                                        || JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}").equals(
                                            FenixConstants.PC04_DARSEGUIMIENTODECISIONPRESIDENTE)){
                    validarSiExistenCambiosEnTablaComiteDeCredito();
                }               
            }            
        }else{
            LOGGER.warning(AprobacionBean.BEAN_EXPRESSION + " es nulo");
        }
        LOGGER.warning("Fuera de miembroAprobSelectListener");
    }
    
    public void checkNotificacionValueChangeListener(ValueChangeEvent valueEvent){
        LOGGER.warning("Dentro de checkNotificacionValueChangeListener");
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        valueEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        //Solo cuando sea la tarea Dar seguimiento a votacion o Dar seguimiento a decision presidente
        if(null != aprobacionBean){
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}")){
                if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}").equals(
                                    FenixConstants.PC04_DARSEGUIMIENTOVOTACION) 
                                        || JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}").equals(
                                            FenixConstants.PC04_DARSEGUIMIENTODECISIONPRESIDENTE)){
                    validarSiExistenCambiosEnTablaConNotificacion();
                }               
            }
        }else{
            LOGGER.warning(AprobacionBean.BEAN_EXPRESSION + " es nulo");
        }
        LOGGER.warning("Fuera de checkNotificacionValueChangeListener");
    }
    
    public void validarSiExistenCambiosEnTablaConNotificacion(){
        //GO TO
        LOGGER.warning("Dentro de validarSiExistenCambiosEnTablaConNotificacion");
        DCBindingContainer container = ADFUtils.getDCBindingContainer();
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        DCIteratorBinding iterator = container.findIteratorBinding("MiembrosAprobacionNotificacionVO1Iterator");
        
        Integer conNotificacion = null;
        
        try{
        if (iterator != null && aprobacionBean != null) {
            ViewObject vo = iterator.getViewObject();
            LOGGER.warning("Numero de registros miembros aprobacion : " + vo.getEstimatedRowCount());
            for(Row row :vo.getAllRowsInRange()){
                if (row.getAttribute("Nombre") != null) {
                    String usuario = (String) row.getAttribute("Nombre");
                    LOGGER.warning("Nombre usuario : " + usuario);
                    if (aprobacionBean != null && 
                        aprobacionBean.getMapaConNotificacion() != null && 
                        aprobacionBean.getMapaConNotificacion().containsKey(usuario)) {
                            LOGGER.warning("Los roles son iguales");
                            //Row rowMap = aprobacionBean.getMapaConNotificacion().get(usuario);
                            if(null != aprobacionBean.getConNotificacionMap().get(usuario)){
                                conNotificacion = aprobacionBean.getConNotificacionMap().get(usuario); 
                            }else{
                                LOGGER.warning("El valor recuperado de notificacion es nulo.");
                            }
                            //Integer conNotificacion = (Integer) rowMap.getAttribute("ConNotificacion");
                            Boolean conNotificacionMap = conNotificacion == 1 ? Boolean.TRUE : Boolean.FALSE;
                            Boolean conNotificacionRow = (Boolean) row.getAttribute("ConNotificacion");
                            
                            if (conNotificacionMap != null && conNotificacionRow != null &&
                                    conNotificacionMap.equals(conNotificacionRow)) {
                                LOGGER.warning("Valor del check es el mismo");
                                LOGGER.warning("conNotificacion original :"+conNotificacionMap);
                                LOGGER.warning("conNotificacion en vista :"+conNotificacionRow);
                                LOGGER.warning("No existen cambios");
                                aprobacionBean.setExistenCambiosConNotificacion(Boolean.FALSE);
                            } else {
                                LOGGER.warning("Valor del check es diferente");
                                LOGGER.warning("conNotificacion original :"+conNotificacionMap);
                                LOGGER.warning("conNotificacion en vista :"+conNotificacionRow);
                                LOGGER.warning("Se realizaron cambios");
                                //Se muestra el boton de guardar por existen cambios 
                                aprobacionBean.setExistenCambiosConNotificacion(Boolean.TRUE);
                                //con un registro que sea diferente no es neesario evaluar los demas
                                break;
                            }
                    }
                }
            }
            
            LOGGER.warning("existenCambiosComiteCredito :"+aprobacionBean.getExistenCambiosComiteCredito());
            LOGGER.warning("existenCambiosConNotificacion :"+aprobacionBean.getExistenCambiosConNotificacion());
            
        }else{
            if(iterator == null)
                LOGGER.severe("GrupoRolAprobacionVO1Iterator es nulo");
            if(aprobacionBean == null)
                LOGGER.severe(AprobacionBean.BEAN_EXPRESSION +" es nulo");
        }
        }catch(Exception e){
            LOGGER.warning("Error en validarSiExistenCambiosEnTablaConNotificacion.", e);
        }
        LOGGER.warning("Fuera de validarSiExistenCambiosEnTablaConNotificacion");
    }
    
    public void validarSiExistenCambiosEnTablaComiteDeCredito(){
        LOGGER.warning("Dentro de validarSiExistenCambiosEnTablaComiteDeCredito");
        DCBindingContainer container = ADFUtils.getDCBindingContainer();
        DCIteratorBinding iterator = container.findIteratorBinding("GrupoRolAprobacionVO1Iterator");
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        String nombreMiembroMap = null;
        try{
        if (iterator != null && aprobacionBean != null) {
            ViewObject vo = iterator.getViewObject();
            LOGGER.warning("Numero de registros GrupoRolAprobacionVO1Iterator : " + vo.getEstimatedRowCount());
            for(Row row :vo.getAllRowsInRange()){
                if (row.getAttribute("IdRolBpm") != null) {
                    Number idRolBpm = (Number) row.getAttribute("IdRolBpm");
                    LOGGER.warning("idRolBpm en vista :"+idRolBpm);
                    if (aprobacionBean != null && 
                        aprobacionBean.getMapaComiteCredito() != null && 
                        aprobacionBean.getMapaComiteCredito().containsKey(idRolBpm.intValue())) {
                        LOGGER.warning("El idRolBpm :"+idRolBpm +" existe en el estado inicial");
                        //Row rowMap = aprobacionBean.getMapaComiteCredito().get(idRolBpm.intValue());    
                        if(null != aprobacionBean.getComiteCreditoMap().get(idRolBpm.intValue())){
                            nombreMiembroMap = aprobacionBean.getComiteCreditoMap().get(idRolBpm.intValue());
                        }else{
                            LOGGER.warning("El valor de comite de credito es nulo.");
                        }
                        
                        String nombreMiembroRow = (String) row.getAttribute("NombreMiembro");
                        if (nombreMiembroMap != null && nombreMiembroRow != null &&
                            nombreMiembroMap.equals(nombreMiembroRow)) {
                            LOGGER.warning("Nombre es el mismo");
                            LOGGER.warning("Nombre estado original :"+nombreMiembroMap);
                            LOGGER.warning("Nombre actual en vista :"+nombreMiembroRow);
                            LOGGER.warning("No existen cambios");
                            //Se muestra el boton de guardar por existen cambios 
                            aprobacionBean.setExistenCambiosComiteCredito(Boolean.FALSE);
                        } else {
                            LOGGER.warning("Nombre es diferente");
                            LOGGER.warning("Nombre estado original :"+nombreMiembroMap);
                            LOGGER.warning("Nombre actual en vista :"+nombreMiembroRow);
                            LOGGER.warning("Se realizaron cambios");
                            //Se muestra el boton de guardar por existen cambios 
                            aprobacionBean.setExistenCambiosComiteCredito(Boolean.TRUE);
                            //con un registro que sea diferente no es neesario evaluar los demas
                            break;
                        }
                    }
                }
            }

            LOGGER.warning("existenCambiosComiteCredito :"+aprobacionBean.getExistenCambiosComiteCredito());
            LOGGER.warning("existenCambiosConNotificacion :"+aprobacionBean.getExistenCambiosConNotificacion());
            
        }else{
            if(iterator == null)
                LOGGER.severe("GrupoRolAprobacionVO1Iterator es nulo");
            if(aprobacionBean == null)
                LOGGER.severe(AprobacionBean.BEAN_EXPRESSION +" es nulo");
        }
        }catch(Exception e){
            LOGGER.warning("Error en validarSiExistenCambiosEnTablaComiteDeCredito", e);
        }
        LOGGER.warning("Fuera de validarSiExistenCambiosEnTablaComiteDeCredito");
    }

    public void setFechaInicioRevPresidenciaUIC(RichInputDate fechaInicioRevPresidenciaUIC) {
        this.fechaInicioRevPresidenciaUIC = fechaInicioRevPresidenciaUIC;
    }

    public RichInputDate getFechaInicioRevPresidenciaUIC() {
        return fechaInicioRevPresidenciaUIC;
    }

    public void setFechaCierreRevPresidenciaUIC(RichInputDate fechaCierreRevPresidenciaUIC) {
        this.fechaCierreRevPresidenciaUIC = fechaCierreRevPresidenciaUIC;
    }

    public RichInputDate getFechaCierreRevPresidenciaUIC() {
        return fechaCierreRevPresidenciaUIC;
    }
    
    public void setAccionDecPreVisibleProp(String accionDecPreVisibleProp) {
        this.accionDecPreVisibleProp = accionDecPreVisibleProp;
    }

    public String getAccionDecPreVisibleProp() {
        
        if(accionDecPreVisibleProp == null){
            esPositivoVotoPresidente();
        }
        return accionDecPreVisibleProp;
    }
    
    /**
     * Ejecuta la consulta del ultimo voto de Presidente para determinar si es positivo o negativo
     * @return devuelve valor booleano, true si el voto es positivo o false en caso contrario
     */
    public boolean esPositivoVotoPresidente(){
        
        boolean esPositivo = false;
        BindingContainer binding = getBindings();
        OperationBinding oper = binding.getOperationBinding(ES_POSITIVO_VOTO_PRESI_OPER);
        if(oper != null){
            
            oper.execute();
            if(oper.getErrors().isEmpty()){
                
                Boolean result = (Boolean) oper.getResult();
                if(result != null){
                    esPositivo = result.booleanValue();
                    LOGGER.warning("Se asigna resultado, el voto es positivo: " + esPositivo);
                }else{
                    LOGGER.severe("Error al determinar si el ultimo voto de Presidente es Positivo o Negativo");
                }
            }else{
                LOGGER.severe("Error al ejecutar Operator Binding: " + ES_POSITIVO_VOTO_PRESI_OPER);
            }
        }else{
            LOGGER.severe("Error no se pudo encontrar Operator Binding: " + ES_POSITIVO_VOTO_PRESI_OPER);
        }
        
        //Asigna resultado a atributo de Managed Bean para el renderizado de la Accion a Seguir
        setAccionDecPreVisibleProp(String.valueOf(esPositivo));
        
        //Refresca treetable de votacion
        AprobacionBean aprobacionBean = 
                (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        if(aprobacionBean != null){
            if(aprobacionBean.getTreeTabBind() != null){
                AdfFacesContext.getCurrentInstance().addPartialTarget(aprobacionBean.getTreeTabBind());        
            }
        }
        
        return esPositivo;
    }
    
    /**
     * Ejecuta la actualizacion de la decision a seguir con una accion negativa
     * @param showMsg indicador booleano para mostrar en pantalla mensaje de error
     * @return devuelve cadena con mensaje de error o null si la actualizacion se realizo correctamente
     */
    @SuppressWarnings("unchecked")
    public String actualizarDecisionAccionNegativa(boolean showMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "actualizarDecisionAccionNegativa",
                        showMsg);
        
        String msgError = null;
        BindingContainer binding = getBindings();
        OperationBinding oper = 
            binding.getOperationBinding(ACTUALIZAR_DECISION_ACCION_NEGATIVA_OPER);
        
        LOGGER.warning("Verificando Operator Binding: " + 
                       ACTUALIZAR_DECISION_ACCION_NEGATIVA_OPER);
        if(oper != null){
            
            AprobacionBean aprobacionBean = 
                (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
            
            Integer idNivelAprobacion = null;
            Number numIdNivelAprob = null;
            Integer tipoSolicitud = aprobacionBean.getIntIdTipoSolicitud();
            
            try{
                idNivelAprobacion = 
                    Integer.parseInt(ADFUtils.getBoundAttributeValue(ID_NIVEL_APROB_ATRIBUTO).toString());    
                numIdNivelAprob = new Number(idNivelAprobacion);
            }catch(Exception e){
                LOGGER.severe("Error al obtener Id de Nivel Aprobacion", e);    
            }
            
            oper.getParamsMap().put("idNivelAprobacion", numIdNivelAprob);
            oper.getParamsMap().put("idTipoSolicitudAprob", tipoSolicitud);
            
            oper.execute();
            if(oper.getErrors().isEmpty()){
                
                Boolean result = null;
                result = (Boolean) oper.getResult();
                if(result != null){
                    if(!result){
                        LOGGER.severe("Error la actualizacion no fue realizada con exito");
                        msgError = 
                            getStringFromBundle(MSG_ERROR_GUARDAR_ACCION_NEGATIVA);
                    }
                }else{
                    LOGGER.severe("Error resultado de la operacion no obtenido");
                    msgError = 
                        getStringFromBundle(MSG_ERROR_GUARDAR_ACCION_NEGATIVA);   
                }
            }else{
                LOGGER.severe("Error la operacion contiene errores: " + 
                              oper.getErrors().toString());
                msgError = 
                    getStringFromBundle(MSG_ERROR_GUARDAR_ACCION_NEGATIVA);    
            }
        }else{
            LOGGER.severe("Error no se encuentra Operator Binding: " + 
                          ACTUALIZAR_DECISION_ACCION_NEGATIVA_OPER);
            msgError = 
                getStringFromBundle(MSG_ERROR_GUARDAR_ACCION_NEGATIVA);
        }
        
        if(msgError != null){
            if(showMsg){
                JSFUtils.addFacesErrorMessage(msgError);
            }
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "actualizarDecisionAccionNegativa",
                       msgError);
        return msgError;
    }
    
    /**
     * Ejecuta el guardado de las observaciones para el registro de Decision de Aprobacion
     * @param showMsg contiene valor booleano, true indica si se desea mostrar mensajes de error en pantalla o false
     *                para no mostrar mensajes
     * @return devuelve cadena con mensaje de error, o null en caso de exito
     */
    @SuppressWarnings("unchecked")
    public String guardarObservacionPresidente(boolean showMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "guardarObservacionPresidente",
                        showMsg);
        
        String msgError = null;
        String observacion = null;
        
        if(getObservacionUIC() != null &&
           getObservacionUIC().getValue() != null){
            observacion = getObservacionUIC().getValue().toString();
        }
        
        LOGGER.warning("Valida contenido de observacion: " + observacion);
        if(observacion != null &&
           !"".equals(observacion)){
            
            BindingContainer binding = getBindings();
            OperationBinding oper = 
                binding.getOperationBinding(GUARDAR_OBSERVACION_PRESIDENTE);
            if(oper != null){
                oper.getParamsMap().put("observacion", observacion);
                
                LOGGER.warning("Se ejecuta Operator Binding: " + GUARDAR_OBSERVACION_PRESIDENTE);
                oper.execute();
                if(!oper.getErrors().isEmpty()){
                    LOGGER.severe("La ejecucion del Operator Binding contiene errores");
                    msgError = 
            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_guardar_observacion");
                }else{
                    Boolean exito = (Boolean) oper.getResult();
                    if(exito != null){
                        if(!exito){
                            LOGGER.severe("Error el dato no fue guardado");
                            msgError = 
            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_guardar_observacion");
                        }
                    }else{
                        LOGGER.severe("Resultado de la ejecucion no devuelto");
                        msgError = 
            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_guardar_observacion");
                    }
                }
            }else{
                LOGGER.severe("Error no se encuentra Operator Binding: " + GUARDAR_OBSERVACION_PRESIDENTE);
                msgError = 
            getStringFromBundle("pc04aprobacion_dar_seguimiento_solicitud_presidencia_msg_error_guardar_observacion");
            }
        }else{
            LOGGER.warning("No hay observaciones que guardar");
        }
        
        if(msgError != null){
            if(showMsg){
                LOGGER.warning("Muestra mensaje de error");
                JSFUtils.addFacesErrorMessage(msgError);
            }
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "guardarObservacionPresidente",
                       msgError);
        return msgError;
    }

    public void setPanelFormAccionPresidenteUIC(RichPanelFormLayout panelFormAccionPresidenteUIC) {
        this.panelFormAccionPresidenteUIC = panelFormAccionPresidenteUIC;
    }

    public RichPanelFormLayout getPanelFormAccionPresidenteUIC() {
        return panelFormAccionPresidenteUIC;
    }
    
    /**
     * Valida si el usuario en session ha ingresado un comentario de operacion en la fecha actual
     * @param intTarea contiene id de tarea
     * @param showMsg indicador booleano, true para mostrar mensaje de error o false en caso contrario
     * @return devuelve valor booleano, true si ha agregado comentario o false en caso contrario
     */
    @SuppressWarnings("unchecked")
    public boolean validaComentarioOperacion(Integer intTarea,
                                             boolean showMsg){
        
        LOGGER.entering(AprobacionActionBean.class.getName(), 
                        "validaComentarioOperacion",
                        new Object[]{intTarea, showMsg});
                       
        Boolean esComentario = false;
        int codigoTarea = getCodigoTarea();
        
        Number idOperacion = null;
        String msg = null;
        
        AprobacionBean aprobacionBean =
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        try{
            idOperacion = new Number(aprobacionBean.getIdOperacion());    
        }catch(Exception e){
            LOGGER.severe("No se obtuvo Id de Operacion", e);
        }
        
        BindingContainer binding = getBindings();
        LOGGER.warning("Obtiene Operator Bindings " + VALIDAR_COMENTARIO_OPERACION_FH_ACTUAL_OPER);
        OperationBinding oper = 
            binding.getOperationBinding(VALIDAR_COMENTARIO_OPERACION_FH_ACTUAL_OPER);
        if(oper != null){
            
            LOGGER.warning("Asigna parametros del Operator Bindings: idOperacion = " + idOperacion +
                           ", intTarea = " + intTarea);
            oper.getParamsMap().put("idOperacion", idOperacion);
            oper.getParamsMap().put("intTarea", intTarea);
            
            LOGGER.warning("Ejecuta Operator Binding: valComentarioOperUsuFchActual" + 
                           VALIDAR_COMENTARIO_OPERACION_FH_ACTUAL_OPER);
            oper.execute();
            
            if(oper.getErrors().isEmpty()){
                
                esComentario = (Boolean)oper.getResult();
                
                LOGGER.warning("Result Oper Binding: " + esComentario);
                if(esComentario != null){
                    if(!esComentario){
                        switch (codigoTarea) {
                        case FenixConstants.PC04_CONSOLIDARDECISIONDIRECTORIO:
                            LOGGER.warning("Es necesario agregar un comentario para solicitar ajustes.");
                            msg = getStringFromBundle(MSG_ERROR_CONSOLIDARDECISIONDIRECTORIO_COMENTARIO);
                        break;
                        case FenixConstants.PC04_REVISARSOLICITUDPRESIDENTE:
                            LOGGER.warning("Debe ingresar la información requerida.");
                            msg = getStringFromBundle(MSG_ERROR_REVISARSOLICITUDPRESIDENTE_COMENTARIO);
                        break;
                        default:
                            LOGGER.warning("No existe comentario de operacion");
                            msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_REQUERIDO);
                        break;
                        }
                        
                    }else{
                        LOGGER.warning("Comentario de operacion si existe");
                    }
                }else{
                    LOGGER.severe("El resultado de la operacion no fue obtenido");
                    msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_FALLO);        
                }
            }else{
                LOGGER.severe("La operacion contiene errores. " + oper.getErrors().toString());
                msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_FALLO);    
            }
        }else{
            LOGGER.severe("Operator Bindings: " + VALIDAR_COMENTARIO_OPERACION_FH_ACTUAL_OPER + 
                          " No encontrado");
            msg = getStringFromBundle(MSG_ERROR_COMENTARIO_OPER_NO_ENCONTRADO);
        }
        
        if(showMsg &&
           msg != null){
            LOGGER.warning("Muestra mensaje de error: " + msg);
            JSFUtils.addFacesErrorMessage(msg); 
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(), 
                       "validaComentarioOperacion",
                       esComentario);
        return esComentario;
    }
    
    private String formatResolucionToUrl(String resolucion){
        
        if(resolucion != null){
            resolucion = resolucion.replace("/", "-");
        }
        return resolucion;
    }
    
    public void actualizaNumeroResolucionListener(ValueChangeEvent event){
        
        if(event == null){
            return;
        }
        //Actualiza modelo de datos
        event.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        String destination = getUrlLotusNoteResolucion();
        if(destination != null){
            getVerDocumentoButtonUIC().setDestination(destination);    
        }
        
        //Resfresca input text de numero resolucion y boton de ver documento
        AdfFacesContext.getCurrentInstance().addPartialTarget(getNumResolucionUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getVerDocumentoButtonUIC());
    }
    
    public void setUrlLotusNoteResolucion(String urlLotusNoteResolucion) {
        this.urlLotusNoteResolucion = urlLotusNoteResolucion;
    }


    public void cancelPopupActionListenerCondicion(PopupCanceledEvent popupCanceledEvent) 
    {   
        if(popupCanceledEvent == null){
            return;
        }
        cancelCondicionPopup();
        getCondicionAprobacionTableUIC().setDisplayRow(RichTable.DISPLAY_ROW_SELECTED);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCondicionAprobacionTableUIC());
    }

    public void setCondicionAprobacionTableUIC(RichTable condicionAprobacionTableUIC) {
        this.condicionAprobacionTableUIC = condicionAprobacionTableUIC;
    }

    public RichTable getCondicionAprobacionTableUIC() {
        return condicionAprobacionTableUIC;
    }
    
    public String getUrlLotusNoteResolucion() {
        
        String numeroResolucion = null;
        String url = null;
        
        if(ADFUtils.getBoundAttributeValue("NumeroResolucion") != null){
            numeroResolucion = ADFUtils.getBoundAttributeValue("NumeroResolucion").toString();
        }
        
        if(numeroResolucion != null){
            numeroResolucion = formatResolucionToUrl(numeroResolucion);
            
            DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
            ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
            Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_LOTUS_NOTE_ABRIR_DOC"}));
            String urlDB = row == null ? null : (String) row.getAttribute("Valor");
            
            url = MessageFormat.format(urlDB, numeroResolucion);
        }
        
        if(url != null){
            setUrlLotusNoteResolucion(url);
        }
        
        return urlLotusNoteResolucion;
    }

    public void setNumResolucionUIC(RichInputText numResolucionUIC) {
        this.numResolucionUIC = numResolucionUIC;
    }

    public RichInputText getNumResolucionUIC() {
        return numResolucionUIC;
    }

    public void setVerDocumentoButtonUIC(RichButton verDocumentoButtonUIC) {
        this.verDocumentoButtonUIC = verDocumentoButtonUIC;
    }

    public RichButton getVerDocumentoButtonUIC() {
        return verDocumentoButtonUIC;
    }

    public void setAgregarComentarioRButtonUIC(RichButton agregarComentarioRButtonUIC) {
        this.agregarComentarioRButtonUIC = agregarComentarioRButtonUIC;
    }

    public RichButton getAgregarComentarioRButtonUIC() {
        return agregarComentarioRButtonUIC;
    }

    public void setCategoriaCondicionUIC(RichSelectManyChoice categoriaCondicionUIC) {
        this.categoriaCondicionUIC = categoriaCondicionUIC;
    }

    public RichSelectManyChoice getCategoriaCondicionUIC() {
        return categoriaCondicionUIC;
    }

    public void setEventosCondicionUIC(RichSelectManyChoice eventosCondicionUIC) {
        this.eventosCondicionUIC = eventosCondicionUIC;
    }

    public RichSelectManyChoice getEventosCondicionUIC() {
        return eventosCondicionUIC;
    }
    
    /**
     * Realiza la seleccion por defecto de las listas de multiseleccion para formulario de Condicion en la tarea de
     * Actualizar TCC
     */
    public void asignarSeleccionCondicion(){
        
        Array values = null;
        List<Integer> seletedIndex = null;
        
        try{
            values = (Array) ADFUtils.getBoundAttributeValue("IdCategoriaCondicionList");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener los valores de Id Categoria Condicion del registro de condicion Actual", e);
        }
        
        if(values != null &&
           values.getSize() > 0){
            
            LOGGER.warning("Valores de Categoria Condicion para seleccion: " + values.toString());
            LOGGER.warning("Cantidad de Categoria Condicion seleccionados: " + values.getSize());

            RowSetIterator iter = 
                ADFUtils.getDCBindingContainer().findIteratorBinding("TcaCategoriaCondicionVOIterator").getRowSetIterator();
            
            Number id = null;
            Number idAux = null;
            
            for(Object value : values.getList()){
                id = (Number) value;
                
                int index = 0;
                for(Row row : iter.getAllRowsInRange()){
                    idAux = (Number) row.getAttribute("Id");
                    if(id != null &&
                       idAux != null){
                        if(id.equals(idAux)){
                            if(seletedIndex == null){
                                 seletedIndex = new ArrayList<Integer>();
                            }
                            seletedIndex.add(index);
                        }
                    }
                    index = index + 1;
                }
            }
            
            if(seletedIndex != null){
                int[] intSeletedIndex = new int[seletedIndex.size()];
                int position = 0;
                for (Integer integer : seletedIndex) {
                    intSeletedIndex[position] = integer.intValue();
                    position ++;
                }
                JUCtrlListBinding listBindings = null;
                listBindings = 
                    (JUCtrlListBinding)getBindings().get("TcaCategoriaCondicionVO");
                listBindings.setSelectedIndices(intSeletedIndex);
            }
        }
        
        seletedIndex = null;
        
        try{
            values = (Array) ADFUtils.getBoundAttributeValue("IdEventoCondicionList");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener los valores de Id Evento Condicion del registro de condicion Actual", e);
        }
        
        if(values != null &&
           values.getSize() > 0){
            
            LOGGER.warning("Valores de Eventos Condicion para seleccion: " + values.toString());
            LOGGER.warning("Cantidad de Eventos Condicion seleccionados: " + values.getSize());
            
            RowSetIterator iter = 
                ADFUtils.getDCBindingContainer().findIteratorBinding("TcaEventoCondicionLOVIterator").getRowSetIterator();
            
            Number id = null;
            Number idAux = null;

            for(Object value : values.getList()){
                id = (Number) value;
                
                int index = 0;
                iter.reset();
                for(Row row : iter.getAllRowsInRange()){
                    idAux = (Number) row.getAttribute("TecId");
                    if(id != null &&
                       idAux != null){
                        if(id.equals(idAux)){
                            if(seletedIndex == null){
                                seletedIndex = new ArrayList<Integer>();
                            }
                            seletedIndex.add(index);
                        }
                    }
                    index = index + 1;
                }
            }
            
            if(seletedIndex != null){
                int[] intSeletedIndex = new int[seletedIndex.size()];
                int position = 0;
                for (Integer integer : seletedIndex) {
                    intSeletedIndex[position] = integer.intValue();
                    position ++;
                }
                
                JUCtrlListBinding listBindings = null;
                listBindings = 
                    (JUCtrlListBinding)getBindings().get("TcaEventoCondicionLOV");
                listBindings.setSelectedIndices(intSeletedIndex);
            }
        }
        
        //Refresca componentes de multiseleccion
        if(getCategoriaCondicionUIC() != null){
            AdfFacesContext.getCurrentInstance().addPartialTarget(getCategoriaCondicionUIC());    
        }
        if(getEventosCondicionUIC() != null){
            AdfFacesContext.getCurrentInstance().addPartialTarget(getEventosCondicionUIC());    
        }
    }

    public void setMessagePopupUIC(RichMessages messagePopupUIC) {
        this.messagePopupUIC = messagePopupUIC;
    }

    public RichMessages getMessagePopupUIC() {
        return messagePopupUIC;
    }
    
    public void usuarioChangesListener(ValueChangeEvent event){
        
        if(event == null){
            return;
        }
        //Forza la actualizacion al Modelo
        event.getComponent().processUpdates(FacesContext.getCurrentInstance());
    }
    
    public void tipoDecisionChangesListener(ValueChangeEvent event){
        
        if(event == null){
            return;
        }
        //Forza la actualizacion al Modelo
        event.getComponent().processUpdates(FacesContext.getCurrentInstance());
    }

    public void setLineasCreditoTableUIC(RichTable lineasCreditoTableUIC) {
        this.lineasCreditoTableUIC = lineasCreditoTableUIC;
    }

    public RichTable getLineasCreditoTableUIC() {
        return lineasCreditoTableUIC;
    }

    public void setFechaTerminoUIC(RichInputDate fechaTerminoUIC) {
        this.fechaTerminoUIC = fechaTerminoUIC;
    }

    public RichInputDate getFechaTerminoUIC() {
        return fechaTerminoUIC;
    }
    
    public void miembroVotacionChangeListener(ValueChangeEvent event){
        
        if(event == null){
            return;
        }
        
        event.getComponent().processUpdates(FacesContext.getCurrentInstance());
    }
    
    public void decisionVotacionChangeListener(ValueChangeEvent event){
        
        if(event == null){
            return;
        }
        
        event.getComponent().processUpdates(FacesContext.getCurrentInstance());
    }
    
    public void comentarioVotacionChangeListener(ValueChangeEvent event){
        
        if(event == null){
            return;
        }
        
        event.getComponent().processUpdates(FacesContext.getCurrentInstance());
    }

    public void setAgregarCondicionButtonUIC(RichButton agregarCondicionButtonUIC) {
        this.agregarCondicionButtonUIC = agregarCondicionButtonUIC;
    }

    public RichButton getAgregarCondicionButtonUIC() {
        return agregarCondicionButtonUIC;
    }
    
    /**
     * Realiza la validacion de la existencia de documento de respaldo cuando existen votos o decisiones fuera de
     * sistema para las tareas de Dar Seguimiento a Votacion y Dar Seguimiento a Decision Presidente
     * @return devuelve valor booleano, true si es valido o false en caso contrario
     */
    @SuppressWarnings("unchecked")
    public boolean validaDocumentoRespaldoVotoFueraSistema(){
        
        boolean esValido = false;
        boolean esError = false;
        
        Number idSolicitud = null;
        Number idOperacion = null;
        Integer idTareaBpm = null;
        
        try{
            idSolicitud = (Number) ADFUtils.getBoundAttributeValue("IdSolicitudAprobacion");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Solicitud", e);
            esError = true;
        }
        
        try{
            idOperacion = (Number) ADFUtils.getBoundAttributeValue("IdOperacion");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Operacion", e);
            esError = true;
        }
        
        try{
            idTareaBpm = (Integer) ADFUtils.getBoundAttributeValue("CodigoTarea");
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Tarea BPM", e);
        }
        
        if(idSolicitud != null &&
           idOperacion != null &&
           idTareaBpm != null){
            
            Boolean existeVotoFS = false;
            Map params = new HashMap();
            params.put("idSolicitudAprobacion", idSolicitud);
            OperationBinding oper = executeOperBinding(params, "existeVotoFueraSistema");
            if(oper != null){
                if(oper.getErrors().isEmpty()){
                    existeVotoFS = (Boolean) oper.getResult();
                    if(existeVotoFS){
                        
                        params = new HashMap();
                        params.put("idOperacion", idOperacion);
                        params.put("idTareaBpm", idTareaBpm);
                        params.put("idTipoDocumento", FenixConstants.DOCUMENTO_VOTO_FUERA_DE_SISTEMA);
                        params.put("esAdjunto", false);
                        OperationBinding operValDoc = executeOperBinding(params, "validaDocumentoPorIdOperacionTarea");
                        if(operValDoc != null){
                            if(operValDoc.getErrors().isEmpty()){
                                
                                Boolean result = null;
                                result = (Boolean) operValDoc.getResult();
                                if(result != null){
                                    esValido = result.booleanValue();
                                    LOGGER.warning("Resultado de la validacion de Documento de Respaldo: " + esValido);
                                }else{
                                    esError = true;
                                    esValido = false;
                                    LOGGER.severe("Error resultado de la validacion de documento no obtenido");
                                }
                            }else{
                                esError = true;
                                esValido = false;
                                LOGGER.severe("Error al realizar la validacion de documento: " + 
                                              operValDoc.getErrors().toString());
                            }
                        }else{
                            esError = true;
                            esValido = false;
                            LOGGER.severe("Error no se pudo ejecutar la validacion de documento, operacion no definida");
                        }
                    }else{
                        esValido = true;
                        LOGGER.warning("El resultado de la verificacion de Voto fuera de Sistema no requiere documento respaldo");
                    }
                }else{
                    LOGGER.severe("Error al realizar la verificacion de Voto fuera de Sistema");
                    esError = true;
                }
            }else{
                LOGGER.severe("Error operacion de verificacion de voto fuera de sistema no definida");
                esError = true;
            }
        }
        
        if(esError){
            String msg = 
                getStringFromBundle("pc04aprobacion_msg_error_validacion_documento_respaldo_voto_fuera_sistema_fallo");
            LOGGER.severe("Muestra mensaje de error: " + msg);
            JSFUtils.addFacesErrorMessage(msg);
            esValido = false;
        }
                
        return esValido;
    }
    
    /**
     * Realiza la validacion de la existencia de documento de respaldo cuando existen votos o decisiones fuera de
     * sistema para las tareas de Dar Seguimiento a Votacion y Dar Seguimiento a Decision Presidente
     * @return devuelve valor booleano, true si es valido o false en caso contrario
     */
    @SuppressWarnings("unchecked")
    public boolean validaDocumentoRespaldoVotoFueraSistemaCliente(){
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        boolean esValido = false;
        boolean esError = false;
        
        Number idSolicitud = null;
        Long idCliente = null;
        Integer idTareaBpm = null;
        oracle.jbo.domain.Number idFlujo = null;
        
        try{
            idSolicitud = (Number) ADFUtils.getBoundAttributeValue("IdSolicitudAprobacion");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Solicitud", e);
            esError = true;
        }
        
        try{
            idCliente = (Long) ADFUtils.getBoundAttributeValue("IdCliente1");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Cliente", e);
            esError = true;
        }
        
        try{
            idTareaBpm = (Integer) ADFUtils.getBoundAttributeValue("CodigoTarea");
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Tarea BPM", e);
        }
        
        try{
            idFlujo = aprobacionBean.getIdFlujo();
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Flujo", e);
        }
        
        if(idSolicitud != null &&
           idCliente != null &&
           idTareaBpm != null && 
           idFlujo != null){
            
            Boolean existeVotoFS = false;
            Map params = new HashMap();
            params.put("idSolicitudAprobacion", idSolicitud);
            OperationBinding oper = executeOperBinding(params, "existeVotoFueraSistema");
            if(oper != null){
                if(oper.getErrors().isEmpty()){
                    existeVotoFS = (Boolean) oper.getResult();
                    if(existeVotoFS){
                        
                        LOGGER.warning("IdTareaBPM: " + idTareaBpm);
                        params = new HashMap();
                        params.put("idCliente", idCliente);
                        params.put("idTareaBPM", idTareaBpm);
                        params.put("idTipoDocumento", FenixConstants.TD_SOPORTE_VOTO_FUERA_SISTEMA_SCR);
                        params.put("numeroSerieDocumento", idFlujo);
                        OperationBinding operValDoc = executeOperBinding(params, "validarDocumentoClientePorNumeroSerieTarea");
                        if(operValDoc != null){
                            if(operValDoc.getErrors().isEmpty()){
                                
                                Boolean result = null;
                                result = (Boolean) operValDoc.getResult();
                                if(result != null){
                                    esValido = result.booleanValue();
                                    LOGGER.warning("Resultado de la validacion de Documento de Respaldo: " + esValido);
                                }else{
                                    esError = true;
                                    esValido = false;
                                    LOGGER.severe("Error resultado de la validacion de documento no obtenido");
                                }
                            }else{
                                esError = true;
                                esValido = false;
                                LOGGER.severe("Error al realizar la validacion de documento: " + 
                                              operValDoc.getErrors().toString());
                            }
                        }else{
                            esError = true;
                            esValido = false;
                            LOGGER.severe("Error no se pudo ejecutar la validacion de documento, operacion no definida");
                        }
                    }else{
                        esValido = true;
                        LOGGER.warning("El resultado de la verificacion de Voto fuera de Sistema no requiere documento respaldo");
                    }
                }else{
                    LOGGER.severe("Error al realizar la verificacion de Voto fuera de Sistema");
                    esError = true;
                }
            }else{
                LOGGER.severe("Error operacion de verificacion de voto fuera de sistema no definida");
                esError = true;
            }
        }
        
        if(esError){
            String msg = 
                getStringFromBundle("pc04aprobacion_msg_error_validacion_documento_respaldo_voto_fuera_sistema_fallo");
            LOGGER.severe("Muestra mensaje de error: " + msg);
            JSFUtils.addFacesErrorMessage(msg);
            esValido = false;
        }
                
        return esValido;
    }
    
    public void setNumAcuerdo(String numAcuerdo) {
        this.numAcuerdo = numAcuerdo;
    }

    public String getNumAcuerdo() {
        
        if(ADFUtils.getBoundAttributeValue("NumeroAcuerdo") != null){
            numAcuerdo = (String) ADFUtils.getBoundAttributeValue("NumeroAcuerdo");
        }
        return numAcuerdo;
    }

    public void setNumActa(String numActa) {
        this.numActa = numActa;
    }

    public String getNumActa() {
        
        if(ADFUtils.getBoundAttributeValue("NumeroActa") != null){
            numActa = (String) ADFUtils.getBoundAttributeValue("NumeroActa");
        }
        return numActa;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getAcuerdo() {
        
        if(ADFUtils.getBoundAttributeValue("Acuerdo") != null){
            acuerdo = (String) ADFUtils.getBoundAttributeValue("Acuerdo");
        }
        return acuerdo;
    }

    public void setTipoAccionDecisionUIC(RichSelectOneChoice tipoAccionDecisionUIC) {
        this.tipoAccionDecisionUIC = tipoAccionDecisionUIC;
    }

    public RichSelectOneChoice getTipoAccionDecisionUIC() {
        LOGGER.warning("Into getTipoAccionDecisionUIC");
        if(ADFUtils.getBoundAttributeValue("IdTcaAccionASeguir") != null &&
           ADFUtils.getBoundAttributeValue("IdTipoAccionVarAttr") == null){
            
            LOGGER.warning("Configura Lista de Acciones de Decision");
            
            Number idTcaAccion = null;
            try{         
                AprobacionBean aprobacionBean = 
                        (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                idTcaAccion = (Number) ADFUtils.getBoundAttributeValue("IdTcaAccionASeguir");
                LOGGER.warning("idTcaAccion:"+idTcaAccion);
                aprobacionBean.setIdTipoAccion(idTcaAccion);
                ADFUtils.setBoundAttributeValue("IdTipoAccionVarAttr", idTcaAccion);
                LOGGER.warning("IdTipoAccionVarAttr :"+ADFUtils.getBoundAttributeValue("IdTipoAccionVarAttr"));
                ADFUtils.findControlBinding("IdTipoAccionVarList").setInputValue(idTcaAccion);
                
                RowSetIterator iter = 
                    ADFUtils.getDCBindingContainer().findIteratorBinding("TcaAccionASeguirLOV1Iterator").getRowSetIterator();
                Number idAux = null;
                int index = 0;
                iter.reset();
                for(Row row : iter.getAllRowsInRange()){
                    idAux = (Number) row.getAttribute("Id");
                    if(idTcaAccion != null &&
                       idAux != null){
                        if(idTcaAccion.equals(idAux)){
                            aprobacionBean.setCodigoTipoAccion((String)row.getAttribute("Descripcion"));
                            break;
                        }
                    }
                    index = index + 1;
                }

                JUCtrlListBinding listBindings = null;
                listBindings = 
                    (JUCtrlListBinding)getBindings().get("IdTipoAccionVarList");
                listBindings.setSelectedIndex(index);
                LOGGER.warning("codigoTipoAccion :"+aprobacionBean.getCodigoTipoAccion());
                LOGGER.warning("Configura lista como solo lectura");
                tipoAccionDecisionUIC.setReadOnly(true);
            }catch(Exception e){
                LOGGER.severe("Error al asignar valores precargados de la Lista de Accion a Seguir");
            }
        }else{
            LOGGER.warning("No entra a settear valores");
        }
        LOGGER.warning("Leave getTipoAccionDecisionUIC");
        return tipoAccionDecisionUIC;
    }

    @SuppressWarnings("unchecked")
    public boolean validarTCC(){
            
        boolean esValido = false;
        OperationBinding oper = null;
        
        Long idOperacion = null;
        try{
            if(ADFUtils.getBoundAttributeValue("CodigoOperacion") != null){
                idOperacion = Long.valueOf(ADFUtils.getBoundAttributeValue("CodigoOperacion").toString());    
            }else{
                LOGGER.severe("Error el Codigo de Operacion es NULL");
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el codigo de operacion");
        }
        
        if(idOperacion != null){
            
            Map params = null;
            params = new HashMap();
            params.put("idOperacion", idOperacion);
            
            oper = executeOperBinding(params, "validarEstadosTCCAprobacion");
            if(oper != null){
                if(oper.getErrors().isEmpty()){
                    if(oper.getResult() != null){
                        try{
                            esValido = Boolean.valueOf(oper.getResult().toString());    
                        }catch(Exception e){
                            LOGGER.severe("Error al obtener el resultado de la Operacion: validarEstadosTCCAprobacion");
                        }
                        
                        if(esValido){
                            LOGGER.warning("El resultado de la validacion de TCC es positiva");    
                        }else{
                            LOGGER.severe("El resultado de la validacion de TCC es negativa");
                            String msg = getStringFromBundle("VALIDAR_TCC_NEGATIVO");
                            JSFUtils.addFacesErrorMessage(msg);
                        }
                    }else{
                        String msg = getStringFromBundle("VALIDAR_TCC_ERROR");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                }else{
                    LOGGER.severe("Error al ejecutar la operacion");
                    JSFUtils.addFacesErrorMessage(oper.getErrors().toString());
                }
            }else{
                LOGGER.severe("Error Operator Binding no definido");
                String msg = getStringFromBundle("VALIDAR_TCC_ERROR");
                JSFUtils.addFacesErrorMessage(msg);
            }
        }else{
            LOGGER.severe("Error ID de Operacion no definida");
            String msg = getStringFromBundle("VALIDAR_TCC_ERROR");
            JSFUtils.addFacesErrorMessage(msg);
        }
        
        return esValido;
    }
    
    /**
     * Realiza la actualizacion de TCC cambiando sus Estados a Aprobado,
     * al finalizar la tarea de Actualizar TCC
     */
    @SuppressWarnings("unchecked")
    public boolean actualizarTCCEstadoAprobado(){
        
        LOGGER.entering(AprobacionActionBean.class.getName(),
                        "actualizarTCCEstadoAprobado");
        
        boolean exito = false;
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        //Verifica si debe realizar la actualizacion de estados tcc cuando el tipo de solicitud es Operacion
        if(TIPO_SOLICITUD_APROB_OPERACION.equals(aprobacionBean.getIntIdTipoSolicitud())){
            
            Long idOperacion = null;
            try{
                if(ADFUtils.getBoundAttributeValue("CodigoOperacion") != null){
                    idOperacion = Long.valueOf(ADFUtils.getBoundAttributeValue("CodigoOperacion").toString());
                }
                        
                if(idOperacion != null){
                    LOGGER.severe("Error el valor del ID de Operacion es NULL");
                }
            }catch(Exception e){
                LOGGER.severe("Error al obtener el ID de Operacion");
            }
            
            if(idOperacion != null){
                Map params = new HashMap();
                params.put("idOperacion", idOperacion);
                OperationBinding oper = executeOperBinding(params, "actualizarEstadosTCCAprobacion");
                if(oper != null){
                    if(oper.getErrors().isEmpty()){
                        
                        Boolean result = (Boolean) oper.getResult();
                        if(result != null){
                            if(result){
                                LOGGER.warning("Actualizacion de Estados TCC exitosa");
                                exito = true;
                            }else{
                                LOGGER.severe("Resultado de la actualizacion de Estados TCC no exitosa");
                                exito = false;
                                String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_no_realizado");
                                JSFUtils.addFacesErrorMessage(msg);
                            }
                        }else{
                            LOGGER.severe("Resultado de actualizacion de Estados TCC NULL");
                            exito = false;
                            String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_no_resultado");
                            JSFUtils.addFacesErrorMessage(msg);
                        }
                    }else{
                        LOGGER.severe("Error en la operacion de actualizar Estados TCC. " + 
                                      oper.getErrors().toString());
                        exito = false;
                        String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_contiene_fallo");
                        msg = msg + ". " + oper.getErrors().toString();
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                }
            }
        }else{
            if(TIPO_SOLICITUD_APROB_ENMIENDA.equals(aprobacionBean.getIntIdTipoSolicitud())){
                LOGGER.warning("El tipo de solicitud es Enmienda, la actualizacion de estados TCC no es necesaria");
                exito = true;
            }else{
                LOGGER.warning("El tipo de solicitud de aprobacion no esta definido por Payload");
                String msg = getStringFromBundle("actualizar_estados_tcc_oper_msg_error_contiene_fallo");
                JSFUtils.addFacesErrorMessage(msg);
            }
        }
        
        LOGGER.exiting(AprobacionActionBean.class.getName(),
                       "actualizarTCCEstadoAprobado",
                       exito);
        return exito;
    }
    
    public void setMatrizTccGridVisible(String matrizTccGridVisible) {
        this.matrizTccGridVisible = matrizTccGridVisible;
    }

    public String getMatrizTccGridVisible() {
        
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        String value = null;
        if(aprobacionBean != null &&
           aprobacionBean.getIntIdTipoSolicitud() != null){
            if(TIPO_SOLICITUD_APROB_ENMIENDA.equals(aprobacionBean.getIntIdTipoSolicitud())){
                value = String.valueOf(Boolean.TRUE);
            }else{
                value = String.valueOf(Boolean.FALSE);
            }
        }
        
        if(value != null){
            setMatrizTccGridVisible(value);
        }
        
        return matrizTccGridVisible;
    }
    
    public Boolean actualizarComisionesMontoFormalizado(){
            AprobacionBean aprobacionBean = 
                (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        Boolean retorno=null;
        Long idOperacion= Long.parseLong(aprobacionBean.getIdOperacion());
            BindingContainer bindings = ADFUtils.getBindingContainer();
                   OperationBinding operationBinding = null;
                    operationBinding = bindings.getOperationBinding("actualizarComisionMonto");
                    operationBinding.getParamsMap().put("idOperacion", idOperacion);
                   operationBinding.getParamsMap().put("tipoMonto", FenixConstants.MONTO_APROBADO); // Monto Aprobado Id = 3, según tabla TCA_TIPO_MONTO_OPERACION
                   operationBinding.execute();
                   if(operationBinding.getErrors().isEmpty()) {
                       // Asignación para Monto aprobado
                       retorno = (Boolean)operationBinding.getResult();
                   }
                   if(null==retorno || !retorno){
                       JSFUtils.addFacesErrorMessage("Error al actualizar los montos en las comisiones");
                   }
        return retorno;
        }
    
    public void consultarCondicionAprobacion(){
        //executeOperBinding(null, "consultarCondicionAprobacion");
        actualizarComisionesMontoFormalizado();
    }
    
    public void setMatrizTccTreeEscritura(String matrizTccTreeEscritura) {
        this.matrizTccTreeEscritura = matrizTccTreeEscritura;
    }

    public String getMatrizTccTreeEscritura() {
        
        String value = String.valueOf(Boolean.FALSE);
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        if(aprobacionBean != null &&
           aprobacionBean.getIntIdTipoSolicitud() != null){
            if(TIPO_SOLICITUD_APROB_ENMIENDA.equals(aprobacionBean.getIntIdTipoSolicitud())){
                value = String.valueOf(Boolean.FALSE);
            }else{
                value = String.valueOf(Boolean.TRUE);
            }
        }
        
        setMatrizTccTreeEscritura(value);
        
        return matrizTccTreeEscritura;
    }

    public void setMatrizTccGridEscritura(String matrizTccGridEscritura) {
        this.matrizTccGridEscritura = matrizTccGridEscritura;
    }

    public String getMatrizTccGridEscritura() {
        
        String value = String.valueOf(Boolean.FALSE);
        AprobacionBean aprobacionBean = 
            (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        if(aprobacionBean != null &&
           aprobacionBean.getIntIdTipoSolicitud() != null){
            if(TIPO_SOLICITUD_APROB_ENMIENDA.equals(aprobacionBean.getIntIdTipoSolicitud())){
                value = String.valueOf(Boolean.TRUE);
            }else{
                value = String.valueOf(Boolean.FALSE);
            }
        }
        
        setMatrizTccGridEscritura(value);
        
        return matrizTccGridEscritura;
    }
    

    public void setIdEnmienda(Long idEnmienda) {
        this.idEnmienda = idEnmienda;
    }

    public Long getIdEnmienda() {
        
        if(ADFUtils.getBoundAttributeValue("IdRequerimiento") != null){
            Integer intIdEnmienda = null;
            try{
                intIdEnmienda = (Integer) ADFUtils.getBoundAttributeValue("IdRequerimiento");
                setIdEnmienda(new Long(intIdEnmienda));
            }catch(Exception e){
                LOGGER.severe("Error al obtener y convertir Id Requerimiento en Id Enmienda", e);
            }
        }
        
        return idEnmienda;
    }
    
    private Long getIdCliente() {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        if (null != aprobacionBean.getIdCliente() && aprobacionBean.getIdCliente().trim().length() > 0) {
            return Long.parseLong(aprobacionBean.getIdCliente());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el idCLiente>");
        return 0L;
    }
    
    private Number getIdFlujo(){
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        if (null != aprobacionBean.getIdFlujo()) {
            return aprobacionBean.getIdFlujo();
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el idFlujo>");
        return null;
    }
    
    private Integer getIdTareaBpm(){
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        if (null != aprobacionBean.getIdTarea() && aprobacionBean.getIdTarea().trim().length() > 0) {
            return Integer.parseInt(aprobacionBean.getIdTarea());
        }
        LOGGER.log(ADFLogger.ERROR, "<No se pudo obtener el idTareaBpm>");
        return 0;
    }
    
    public void tipoReunionValueChangeListener (ValueChangeEvent valueChangeEvent){
        LOGGER.log(ADFLogger.WARNING, "Dentro de tipoReunionValueChangeListener " +
            "value : " +valueChangeEvent.getNewValue());
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        String idTipoReunionInteger = null;
        //actualiza el valor seleccionado
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        if(null != valueChangeEvent.getNewValue()){
            idTipoReunionInteger = (String)valueChangeEvent.getNewValue();
            aprobacionBean.setIdTipoReunion(idTipoReunionInteger);
        }
        
        //AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelFormPresencialUIC());
        //AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelFormVirtualUIC());
    }
    
    public void fechaAperturaReunionVirtualValueChangeListener(ValueChangeEvent valueChangeEvent){
        //Cuando se seleccione una fecha de apertura para reunion virtual , 
        //limpiar la fecha de cierre para reunion virtual.
        this.getFechaCierreRVirtual().resetValue();
    }

    public void guardaCambiosMiembrosActionListener(ActionEvent actionEvent) {
        LOGGER.warning("click guardaCambiosMiembrosActionListener " + actionEvent.toString());
        this.guardaComiteCredito();
        this.guardaConNotificacion();
        this.guardaEquipoTrabajo();
        this.precargaMiembrosAprobacion();
    }
    
    private void guardaComiteCredito() {
        LOGGER.warning("guardaComiteCredito...");
        DCBindingContainer container = ADFUtils.getDCBindingContainer();
        
        if(container != null){
            DCIteratorBinding iterator = container.findIteratorBinding("GrupoRolAprobacionVO1Iterator");
            
            if (iterator != null) {
                ViewObject vo = iterator.getViewObject();
                
                if (vo != null) {
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                    
                    if (rsi != null) {
                        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                        Boolean existeCambio = Boolean.FALSE;
                        
                        while (rsi.hasNext()) {
                            Row row = rsi.next();
                            
                            if (row != null) {
                                
                                if (row.getAttribute("IdRolBpm") != null) {
                                    Number idRolBpm = (Number) row.getAttribute("IdRolBpm");
                                    
                                    if (aprobacionBean != null && 
                                        aprobacionBean.getMapaComiteCredito() != null && 
                                        aprobacionBean.getMapaComiteCredito().containsKey(idRolBpm.intValue())) {
                                        LOGGER.warning("Los roles son iguales");
                                        Row rowMap = aprobacionBean.getMapaComiteCredito().get(idRolBpm.intValue());
                                        String nombreMiembroMap = (String) rowMap.getAttribute("NombreUsuario");
                                        String nombreMiembroRow = (String) row.getAttribute("NombreMiembro");
                                        
                                        if (nombreMiembroMap != null && nombreMiembroRow != null &&
                                            nombreMiembroMap.equals(nombreMiembroRow)) {
                                            LOGGER.warning("No hay cambios");
                                        } else {
                                            if (nombreMiembroMap != null && nombreMiembroRow != null) {
                                                LOGGER.warning("Hay cambios");
                                                existeCambio = Boolean.TRUE;
                                                this.addUsuarioReunionAprobacion(row, 1, 0, 1, 0);
                                            } else {
                                                LOGGER.warning("nombreMiembroMap o nombreMiembroRow es NULL");
                                            }
                                        }
                                    } else {
                                        if (aprobacionBean != null && aprobacionBean.getMapaComiteCredito() != null) {
                                            LOGGER.warning("Los roles No son iguales");
                                            this.addUsuarioReunionAprobacion(row, 1, 0, 1, 0);
                                        } else {
                                            LOGGER.warning("AprobacionBean o MapaComiteCredito es NULL");
                                        }
                                    }
                                } else {
                                    LOGGER.warning("IdRolBpm del row es null.");
                                }
                            } else {
                                LOGGER.warning("Row null.");
                            }
                        }
                        rsi.closeRowSetIterator();
                        
                        if (existeCambio) {
                            JSFUtils.addFacesInformationMessage(this.getStringFromBundle("MSG_UPDATE_COMITE_CREDITO"));
                        } else {
                            LOGGER.warning("No existió cambio");
                        }
                    } else {
                        LOGGER.warning("RowSetIterator null.");
                    }
                } else {
                    LOGGER.warning("ViewObject null.");
                }
            } else {
                LOGGER.warning("DCIteratorBinding null.");
            }
        } else {
            LOGGER.warning("container NULL");
        }
        LOGGER.warning("Terminó guardaComiteCredito.");
    }
    
    private void guardaConNotificacion() {
        LOGGER.warning("guardaConNotificacion...");
        DCBindingContainer container = ADFUtils.getDCBindingContainer();
        
        try{
        if(container != null){
            DCIteratorBinding iterator = container.findIteratorBinding("MiembrosAprobacionNotificacionVO1Iterator");
            
            if (iterator != null) {
                ViewObject vo = iterator.getViewObject();
                
                if (vo != null) {
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                    
                    if (rsi != null) {
                        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                        Boolean existeCambio = Boolean.FALSE;
                        
                        while (rsi.hasNext()) {
                            Row row = rsi.next();
                            
                            if (row != null) {
                                
                                if (row.getAttribute("Nombre") != null) {
                                    String usuario = (String) row.getAttribute("Nombre");
                                    
                                    if (aprobacionBean != null && 
                                        aprobacionBean.getMapaConNotificacion() != null && 
                                        aprobacionBean.getMapaConNotificacion().containsKey(usuario)) {
                                        LOGGER.warning("Los roles son iguales");
                                        Row rowMap = aprobacionBean.getMapaConNotificacion().get(usuario);
                                        Integer conNotificacion = (Integer) rowMap.getAttribute("ConNotificacion");
                                        Boolean conNotificacionMap = conNotificacion == 1 ? Boolean.TRUE : Boolean.FALSE;
                                        Boolean conNotificacionRow = (Boolean) row.getAttribute("ConNotificacion");
                                        
                                        if (conNotificacionMap != null && conNotificacionRow != null &&
                                            conNotificacionMap.equals(conNotificacionRow)) {
                                            LOGGER.warning("No hay cambios");
                                        } else {
                                            if (conNotificacionMap != null && conNotificacionRow != null) {
                                                LOGGER.warning("Hay cambios");
                                                LOGGER.warning("Id" + rowMap.getAttribute("Id") + " - conNotificacionMap " + conNotificacionMap  +" - conNotificacionRow " + conNotificacionRow + " - seteo " + (conNotificacionRow ? 1 : 0));
                                                existeCambio = Boolean.TRUE;
                                                rowMap.setAttribute("ConNotificacion", conNotificacionRow ? 1 : 0);
                                                this.updateUsuarioReunionAprobacion(rowMap);
                                            } else {
                                                LOGGER.warning("conNotificacionMap o conNotificacionRow es NULL");
                                            }
                                        }
                                    } else {
                                        LOGGER.warning("Los roles NO son iguales");
                                    }
                                } else {
                                    LOGGER.warning("IdRolBpm del row es null.");
                                }
                            } else {
                                LOGGER.warning("Row null.");
                            }
                        }
                        rsi.closeRowSetIterator();
                        
                        if (existeCambio) {
                            this.commitUsuarioReunionAprobacion();
                            JSFUtils.addFacesInformationMessage(this.getStringFromBundle("MSG_UPDATE_CON_NOTIFICACION"));
                        } else {
                            LOGGER.warning("No existió cambio");
                        }
                    } else {
                        LOGGER.warning("RowSetIterator null.");
                    }
                } else {
                    LOGGER.warning("ViewObject null.");
                }
            } else {
                LOGGER.warning("DCIteratorBinding null.");
            }
        } else {
            LOGGER.warning("container NULL");
        }
        }catch(Exception e){
            LOGGER.warning("Error en guardaConNotificacion.", e);
        }
        LOGGER.warning("Terminó guardaConNotificacion.");
    }
    
    private void guardaEquipoTrabajo() {
        LOGGER.warning("guardaEquipoTrabajo...");
        DCBindingContainer container = ADFUtils.getDCBindingContainer();
        
        if(container != null){
            DCIteratorBinding iterator = container.findIteratorBinding("MiembrosAprobacionNotificacionVO1Iterator");
            
            if (iterator != null) {
                ViewObject vo = iterator.getViewObject();
                
                if (vo != null) {
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                    
                    if (rsi != null) {
                        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                        Boolean existeCambio = Boolean.FALSE;
                        
                        while (rsi.hasNext()) {
                            Row row = rsi.next();
                            
                            if (row != null) {
                                
                                if (row.getAttribute("Usuario") != null) {
                                    String usuario = (String) row.getAttribute("Usuario");
                                    
                                    if (aprobacionBean != null && 
                                        aprobacionBean.getMapaEquipoTrabajo() != null && 
                                        aprobacionBean.getMapaEquipoTrabajo().containsKey(usuario)) {
                                        LOGGER.warning("Los roles son iguales");
                                        Row rowMap = aprobacionBean.getMapaEquipoTrabajo().get(usuario);
                                        Integer conNotificacion = (Integer) rowMap.getAttribute("ConNotificacion");
                                        Boolean conNotificacionMap = conNotificacion == 1 ? Boolean.TRUE : Boolean.FALSE;
                                        Boolean conNotificacionRow = (Boolean) row.getAttribute("ConNotificacion");
                                        
                                        if (conNotificacionMap != null && conNotificacionRow != null &&
                                            conNotificacionMap.equals(conNotificacionRow)) {
                                            LOGGER.warning("No hay cambios");
                                        } else {
                                            if (conNotificacionMap != null && conNotificacionRow != null) {
                                                LOGGER.warning("Hay cambios");
                                                existeCambio = Boolean.TRUE;
                                                rowMap.setAttribute("ConNotificacion", conNotificacionRow ? 1 : 0);
                                                this.updateUsuarioReunionAprobacion(rowMap);
                                            } else {
                                                LOGGER.warning("conNotificacionMap o conNotificacionRow es NULL");
                                            }
                                        }
                                    } else {
                                        LOGGER.warning("Los roles NO son iguales");
                                    }
                                } else {
                                    LOGGER.warning("IdRolBpm del row es null.");
                                }
                            } else {
                                LOGGER.warning("Row null.");
                            }
                        }
                        rsi.closeRowSetIterator();
                        
                        if (existeCambio) {
                            this.commitUsuarioReunionAprobacion();
                            JSFUtils.addFacesInformationMessage(this.getStringFromBundle("MSG_UPDATE_EQUIPO_TRABAJO"));
                        } else {
                            LOGGER.warning("No existió cambio");
                        }
                    } else {
                        LOGGER.warning("RowSetIterator null.");
                    }
                } else {
                    LOGGER.warning("ViewObject null.");
                }
            } else {
                LOGGER.warning("DCIteratorBinding null.");
            }
        } else {
            LOGGER.warning("container NULL");
        }
        LOGGER.warning("Terminó guardaEquipoTrabajo.");
    }

    public void precargaMiembrosAprobacion() {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            String idSolicitud = JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}").toString();
            params.put("idSolicitud", idSolicitud);
            LOGGER.warning("precargaMiembrosAprobacion para ejecutar consultarUsuarioReunionPorIdSolicitud [idSolicitud] [" + idSolicitud + "]");
            super.execute(params, "consultarUsuarioReunionPorIdSolicitud");
            LOGGER.warning("Terminó de ejecutar consultarUsuarioReunionPorIdSolicitud");
            this.precargarConfiguracionRegistrada();
            //VA_02 - asignar valor a la bandera para habilitar o deshabilitar boton guardar
            validarBotonGuardar();
        } catch (OperationExecuteException e) {
            LOGGER.log(ADFLogger.ERROR, "*** " + e.getMessage());
        }
    }
    
    /**
     * validarBotonGuardar
     * Metodo para validar que en la tarea de Dar seguimiento se oculte el boton guardar hasta que exista un cambio
     * */
    public void validarBotonGuardar(){
        LOGGER.warning("Dentro de validarBotonGuardar");
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        
        if(null != aprobacionBean){
            //inicializar el boton de guardar cambios para la tarea Dar seguimiento a votación
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}")){
                if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}").equals(
                            FenixConstants.PC04_DARSEGUIMIENTOVOTACION) 
                                || JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}").equals(
                                    FenixConstants.PC04_DARSEGUIMIENTODECISIONPRESIDENTE)){
                    LOGGER.warning("idTarea:"+
                                        JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}"));
                    aprobacionBean.setExistenCambiosComiteCredito(Boolean.FALSE);
                    aprobacionBean.setExistenCambiosConNotificacion(Boolean.FALSE);
                }else{
                    LOGGER.warning("idTarea:"+
                                        JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}"));
                    aprobacionBean.setExistenCambiosComiteCredito(Boolean.TRUE);
                    aprobacionBean.setExistenCambiosConNotificacion(Boolean.TRUE);
                }
            }else{
                LOGGER.warning("pIdTarea es nulo");  
                aprobacionBean.setExistenCambiosComiteCredito(Boolean.TRUE);
                aprobacionBean.setExistenCambiosConNotificacion(Boolean.TRUE);
            }
            
            LOGGER.warning("existenCambiosComiteCredito :"+aprobacionBean.getExistenCambiosComiteCredito());
            LOGGER.warning("existenCambiosConNotificacion :"+aprobacionBean.getExistenCambiosConNotificacion());
        }else{
            LOGGER.warning(AprobacionBean.BEAN_EXPRESSION + " es nulo");
        }
        
        LOGGER.warning("Fuera de validarBotonGuardar");
    }
    
    private void precargarConfiguracionRegistrada() {
        LOGGER.warning("precargarConfiguracionRegistrada...");
        DCBindingContainer bc = ADFUtils.getDCBindingContainer();
        
        if (bc != null) {
            DCIteratorBinding iterator = bc.findIteratorBinding("UsuarioReunionAprobacionLOVIterator");
            
            if (iterator != null) {
                ViewObject vo = iterator.getViewObject();
                
                if (vo != null) {
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                    
                    if (rsi != null) {
                        Map<Integer, Row> mapaComiteCredito = new HashMap<Integer, Row>();
                        Map<String, Row> mapaConNotificacion = new HashMap<String, Row>();
                        Map<String, Row> mapaEquipoTrabajo = new HashMap<String, Row>();
                        
                        Map<Integer, String> comiteCredito = new HashMap<Integer, String>();
                        Map<String, Integer> conNotificacion = new HashMap<String, Integer>();
                        
                        while (rsi.hasNext()) {
                            Row row = rsi.next();
                            if (row != null) {
                                Integer idTcaTipoUsuario = (Integer) row.getAttribute("IdTcaTipoUsuario");
                                String nombreUsuario = (String) row.getAttribute("NombreUsuario");
                                Number idTcaRolBpm = (Number) row.getAttribute("IdTcaRolBpm");
                                Integer idRol = idTcaRolBpm.intValue();
                                if(null!=idTcaTipoUsuario){
                                        switch (idTcaTipoUsuario) {
                                        case 1:
                                            LOGGER.warning("Atributos comite de credito");
                                            mapaComiteCredito.put(idTcaRolBpm.intValue(), row);
                                            comiteCredito.put(idRol, (String)row.getAttribute("NombreUsuario"));
                                        break;
                                        case 2:
                                            LOGGER.warning("Atributos con notificacion");
                                            mapaConNotificacion.put(nombreUsuario, row);
                                            conNotificacion.put((String) row.getAttribute("NombreUsuario"), (Integer)row.getAttribute("ConNotificacion"));
                                        break;
                                        case 3:
                                            mapaEquipoTrabajo.put(nombreUsuario, row);
                                        break;
                                        default:
                                            LOGGER.warning("idTcaTipoUsuario desconocido " + idTcaTipoUsuario);
                                        break;
                                        }
                                    }
                                
                            } else {
                                LOGGER.warning("Row null.");
                            }
                        }
                        rsi.closeRowSetIterator();
                        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                        if (aprobacionBean != null) {
                            aprobacionBean.setMapaComiteCredito(mapaComiteCredito);
                            aprobacionBean.setMapaConNotificacion(mapaConNotificacion);
                            aprobacionBean.setMapaEquipoTrabajo(mapaEquipoTrabajo);
                            //Se agregan nuevos mapas con los valores del row
                            aprobacionBean.setComiteCreditoMap(comiteCredito);
                            aprobacionBean.setConNotificacionMap(conNotificacion);
                        }
                    } else {
                        LOGGER.warning("RowSetIterator null.");
                    }
                } else {
                    LOGGER.warning("ViewObject null.");
                }
            } else {
                LOGGER.warning("DCIteratorBinding null.");
            }
        } else {
            LOGGER.warning("DCBindingContainer null.");
        }
        LOGGER.warning("Precarga terminada.");
    }
    
    public void actualizarCambiosMiembrosActionListener(ActionEvent actionEvent) {
        LOGGER.warning("Entrando en guardaCambiosMiembrosActionListener.");
        
        List<MiembroNotificacionDTO> listaNotificacion = new ArrayList<>();
        
        filtrarUsuarioReunionAprobacion();
        
        //Se realiza la precarga de UsuarioReunionAprobacionLOV
        precargaMiembrosAprobacion();
        
        listaNotificacion.addAll(this.actualizarMiembrosVotantes());
        
        listaNotificacion.addAll(this.actualizarMiembrosConNotificacion());
        
        notificarMiembrosModificados(listaNotificacion);
        
        if (commitUsuarioReunionAprobacion()){
            JSFUtils.addFacesInformationMessage(this.getStringFromBundle("MSG_CAMBIOS_GUARDADOS_EXITO"));
            //Se realiza la precarga de UsuarioReunionAprobacionLOV para obtener los valores iniciales.
            precargaMiembrosAprobacion();
        }
    }
    
    private List<MiembroNotificacionDTO> actualizarMiembrosVotantes() {
        LOGGER.warning("Entrando en actualizarMiembrosVotantes");
        DCBindingContainer container = ADFUtils.getDCBindingContainer();
        List<MiembroNotificacionDTO> listaNotificacion = new ArrayList<>();
        try{
        if(container != null){
            DCIteratorBinding iterator = container.findIteratorBinding("GrupoRolAprobacionVO1Iterator");
            
            if (iterator != null) {
                ViewObject vo = iterator.getViewObject();
                
                if (vo != null) {
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                    
                    if (rsi != null) {
                        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                        Long idSolicitud = obtenerIdSolicitudAprobacion();
                        LOGGER.warning("Numero de registros GrupoRolAprobacionVO1Iterator : " + vo.getEstimatedRowCount());
                        if(vo.getEstimatedRowCount() > 0){
                        while (rsi.hasNext()) {
                            Row row = rsi.next();
                            
                            if (row != null) {
                                
                                if (row.getAttribute("IdRolBpm") != null) {
                                    Number idRolBpm = (Number) row.getAttribute("IdRolBpm");
                                    LOGGER.warning("idRolBpm en vista :"+idRolBpm);
                                    if (aprobacionBean != null && 
                                        aprobacionBean.getMapaComiteCredito() != null && 
                                        aprobacionBean.getMapaComiteCredito().containsKey(idRolBpm.intValue())) {
                                        LOGGER.warning("Los roles son iguales");
                                        Row rowMap = aprobacionBean.getMapaComiteCredito().get(idRolBpm.intValue());
                                        
                                        String nombreMiembroMap = (String) rowMap.getAttribute("NombreUsuario");
                                        String nombreMiembroRow = (String) row.getAttribute("NombreMiembro");
                                        
                                        if (nombreMiembroMap != null && nombreMiembroRow != null &&
                                            nombreMiembroMap.equals(nombreMiembroRow)) {
                                            LOGGER.warning("No hay cambios");
                                        } else {
                                            if (nombreMiembroMap != null && nombreMiembroRow != null) {
                                                LOGGER.warning("Hay cambios");
                                                
                                                rowMap.setAttribute("LoginUsuario", row.getAttribute("Usuario"));
                                                rowMap.setAttribute("NombreUsuario", row.getAttribute("NombreMiembro"));
                                                
                                                if(ID_ROL_JEFE_ASJUR.compareTo(idRolBpm.intValue()) == 0){
                                                    rowMap.setAttribute("EmiteVoto", 0);
                                                    rowMap.setAttribute("ConNotificacion", 1);
                                                }else{
                                                    rowMap.setAttribute("EmiteVoto", 1);
                                                    rowMap.setAttribute("ConNotificacion", 1);
                                                }
                                                //Se valida la seleccion de usuarios 'Otro' y 'Excusado'
                                                if(ES_OTRO_VALUE.equalsIgnoreCase((String)rowMap.getAttribute("LoginUsuario"))){
                                                    LOGGER.warning("Se selecciona el usuario 'Otro'");
                                                    rowMap.setAttribute("LoginUsuario", null);
                                                    rowMap.setAttribute("NombreUsuario", "(" + row.getAttribute("NombreMiembro").toString().toLowerCase() + ")");
                                                    rowMap.setAttribute("EmiteVoto", 1);
                                                    rowMap.setAttribute("ConNotificacion", 0);
                                                }
                                                if(ES_EXCUSADO_VALUE.equalsIgnoreCase((String)rowMap.getAttribute("LoginUsuario"))){
                                                    LOGGER.warning("Se selecciona el usuario 'Excusado'");
                                                    rowMap.setAttribute("LoginUsuario", null);
                                                    rowMap.setAttribute("NombreUsuario", "(" + row.getAttribute("NombreMiembro").toString().toLowerCase() + ")");
                                                    rowMap.setAttribute("EmiteVoto", 0);
                                                    rowMap.setAttribute("ConNotificacion", 0);
                                                }
                                                this.updateUsuarioReunionAprobacion(rowMap);
                                                
                                                listaNotificacion.add(obtenerMiembroNotificacion(idSolicitud, rowMap, Boolean.TRUE));
                                            } else {
                                                LOGGER.warning("nombreMiembroMap o nombreMiembroRow es NULL");
                                            }
                                        }
                                    } else {
                                        LOGGER.warning("No aplica el cambio de rol en la actualizacion");
                                    }
                                } else {
                                    LOGGER.warning("IdRolBpm del row es null.");
                                }
                            } else {
                                LOGGER.warning("Row null.");
                            }
                        }
                        }else{
                            LOGGER.warning("Error, No se recuperaron registros de GrupoRolAprobacionVO1Iterator.");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_ACTUALIZAR_DATOS"));
                        }
                        rsi.closeRowSetIterator();
                    } else {
                        LOGGER.warning("RowSetIterator null.");
                    }
                } else {
                    LOGGER.warning("ViewObject null.");
                }
            } else {
                LOGGER.warning("DCIteratorBinding null.");
            }
        } else {
            LOGGER.warning("container NULL");
        }
        }catch(Exception e){
            LOGGER.warning("Error en actualizarMiembrosVotantes", e);
        }
        return listaNotificacion;
    }

    private List<MiembroNotificacionDTO> actualizarMiembrosConNotificacion() {
        LOGGER.warning("Entrando en actualizarMiembrosConNotificacion.");
        DCBindingContainer container = ADFUtils.getDCBindingContainer();
        List<MiembroNotificacionDTO> listaNotificacion = new ArrayList<>();
        try{
        if(container != null){
            DCIteratorBinding iterator = container.findIteratorBinding("MiembrosAprobacionNotificacionVO1Iterator");
            
            if (iterator != null) {
                ViewObject vo = iterator.getViewObject();
                
                if (vo != null) {
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                    
                    if (rsi != null) {
                        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                        Long idSolicitud = obtenerIdSolicitudAprobacion();
                        LOGGER.warning("Numero de registros miembros aprobacion : " + vo.getEstimatedRowCount());
                        if(vo.getEstimatedRowCount() > 0){
                        while (rsi.hasNext()) {
                            Row row = rsi.next();
                            
                            if (row != null) {
                                
                                if (row.getAttribute("Nombre") != null) {
                                    String usuario = (String) row.getAttribute("Nombre");
                                    LOGGER.warning("Nombre usuario : " + usuario);
                                    if (aprobacionBean != null && 
                                        aprobacionBean.getMapaConNotificacion() != null && 
                                        aprobacionBean.getMapaConNotificacion().containsKey(usuario)) {
                                        LOGGER.warning("Los roles son iguales");
                                        Row rowMap = aprobacionBean.getMapaConNotificacion().get(usuario);
                                        
                                        Integer conNotificacion = (Integer) rowMap.getAttribute("ConNotificacion");
                                        Boolean conNotificacionMap = conNotificacion == 1 ? Boolean.TRUE : Boolean.FALSE;
                                        Boolean conNotificacionRow = (Boolean) row.getAttribute("ConNotificacion");
                                        
                                        if (conNotificacionMap != null && conNotificacionRow != null &&
                                            conNotificacionMap.equals(conNotificacionRow)) {
                                            LOGGER.warning("No hay cambios");
                                        } else {
                                            if (conNotificacionMap != null && conNotificacionRow != null) {
                                                LOGGER.warning("Hay cambios");
                                                LOGGER.warning("Id" + rowMap.getAttribute("Id") + " - conNotificacionMap " + conNotificacionMap  +" - conNotificacionRow " + conNotificacionRow + " - seteo " + (conNotificacionRow ? 1 : 0));
                                                
                                                rowMap.setAttribute("ConNotificacion", conNotificacionRow ? 1 : 0);
                                                this.updateUsuarioReunionAprobacion(rowMap);
                                                
                                                listaNotificacion.add(obtenerMiembroNotificacion(idSolicitud, rowMap, Boolean.FALSE));
                                            } else {
                                                LOGGER.warning("conNotificacionMap o conNotificacionRow es NULL");
                                            }
                                        }
                                    } else {
                                        LOGGER.warning("Los roles NO son iguales");
                                    }
                                } else {
                                    LOGGER.warning("IdRolBpm del row es null.");
                                }
                            } else {
                                LOGGER.warning("Row null.");
                            }
                        }
                        rsi.closeRowSetIterator();
                        }else{
                            LOGGER.warning("Error, No se recuperaron registros de MiembrosAprobacionNotificacionVO1Iterator.");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_ACTUALIZAR_DATOS"));
                        }
                    } else {
                        LOGGER.warning("RowSetIterator null.");
                    }
                } else {
                    LOGGER.warning("ViewObject null.");
                }
            } else {
                LOGGER.warning("DCIteratorBinding null.");
            }
        } else {
            LOGGER.warning("container NULL");
        }
        }catch(Exception e){
            LOGGER.warning("Error en actualizarMiembrosConNotificacion.", e);
        }
        return listaNotificacion;
    }
    
    private MiembroNotificacionDTO obtenerMiembroNotificacion(Long idSolicitud, Row row, boolean esVotante) {
        LOGGER.warning("Entrando en obtenerMiembroNotificacion.");
        LOGGER.warning("idSolicitud: " + idSolicitud);
        LOGGER.warning("esVotante: " + esVotante);
        
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        String usuario = null;
        
        usuario = (String) row.getAttribute("LoginUsuario");
        
        LOGGER.warning("usuario: " + usuario);
        
        Integer idPlantilla = null;
        //Integer idNivelAprobacion = aprobacionBean.getIntIdNivelAprob();
        
        Integer idNivelAprobacion = new Integer(JSFUtils.resolveExpression("#{pageFlowScope.idNivelAprobacion}") != null ?
                                        JSFUtils.resolveExpression("#{pageFlowScope.idNivelAprobacion}").toString() : "0");
        
        LOGGER.warning("IdNivelAprobacion : " + idNivelAprobacion);
        if (null != idNivelAprobacion) {
            if (esVotante) {
                // Es miembro de comite de credito o presidencia

                if (idNivelAprobacion.compareTo(NIVEL_APROBACION_PRESIDENCIA) == 0) {
                    idPlantilla = PLANTILLA_EMITIR_VOTO_NIVEL_PRESIDENCIA;
                } else {
                    // Se considera que se trata de una aprobacion por operacion
                    // en caso de querer considerar la aprobacion por cliente se
                    // tendra que definir otra plantilla (con id 85).
                    idPlantilla = PLANTILLA_EMITIR_VOTO_NIVEL_COMITE_OPERACION;
                }
            } else {
                Number idTcaRolBpmNumber = (Number) row.getAttribute("IdTcaRolBpm");
                Integer idTcaRolBpm = null;

                if (null != idTcaRolBpmNumber) {
                    idTcaRolBpm = idTcaRolBpmNumber.intValue();
                }

                // Es miembro con notificacion
                if (idNivelAprobacion.compareTo(NIVEL_APROBACION_PRESIDENCIA) == 0) {
                    if (null != idTcaRolBpm && idTcaRolBpm.compareTo(SECRETARIO_PRESIDENCIA) == 0) {
                        idPlantilla = PLANTILLA_CON_NOTIFICACION_SECRETARIO_PRESIDENCIA;
                    } else {
                        idPlantilla = PLANTILLA_CON_NOTIFICACION_NO_SECRETARIO_PRESIDENCIA;
                    }
                } else {
                    // Se considera que se trata de una aprobacion por operacion
                    // en caso de querer considerar la aprobacion por cliente se
                    // tendra que definir otra plantilla (con id 15).
                    idPlantilla = PLANTILLA_CON_NOTIFICACION_NIVEL_COMITE_OPERACION;
                }
            }
        }
        LOGGER.warning("IdPlantilla : " + idPlantilla);
        MiembroNotificacionDTO miembroNotificacionDTO = new MiembroNotificacionDTO();
        miembroNotificacionDTO.setUsuario(usuario);
        miembroNotificacionDTO.setIdOperacion(obtenerIdOperacion());
        miembroNotificacionDTO.setIdSolicitudAprobacion(idSolicitud);
        miembroNotificacionDTO.setIdPlantilla(idPlantilla);
        miembroNotificacionDTO.setIdNivelAprobacion(idNivelAprobacion);
        miembroNotificacionDTO.setIdUsuarioReunion(aprobacionBean.getIdUsuarioReunion());
        
        // EL valor de IdClienteFlex es obtenido del tag IdCliente del payload.
        // Se reviso el valor cotra la base de datos de fenix
        miembroNotificacionDTO.setIdClienteFenix(aprobacionBean.getIdClienteFlex());
        
        return miembroNotificacionDTO;
    }

    @Deprecated
    private Long obtenerIdSolicitud() {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        Long idSolicitud = null;
                                                
        try {
            idSolicitud = Long.valueOf(aprobacionBean.getIdSolicitud());
        } catch(Exception e) {
            LOGGER.warning("Error al obtener .idSolicitud", e);
        }

        return idSolicitud;
    }

    @SuppressWarnings("unchecked")
    private boolean notificarMiembrosModificados(List<MiembroNotificacionDTO> listaNotificacion) {
        LOGGER.warning("Entrando en notificarMiembrosModificados.");
        boolean notificacionExitosa = Boolean.TRUE;
        List<CorreoElectronicoDTO> listaCorreoElectronico = obtenerListaUsuariosNotificar(listaNotificacion);
        
        try {
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("enviarCorreoService");
            
            for(CorreoElectronicoDTO correoElectronicoDTO : listaCorreoElectronico) {
                operationBinding.getParamsMap().put("objetoCorreo", correoElectronicoDTO);
                operationBinding.execute();
                if(operationBinding.getErrors().isEmpty()){
                    if (notificacionExitosa) {
                        notificacionExitosa = (Boolean) operationBinding.getResult();
                    }
                } else {
                    notificacionExitosa = Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            LOGGER.warning("Error al notificar a los miembros modificados", e);
        }
        
        return notificacionExitosa;
    }
    
    private List<CorreoElectronicoDTO> obtenerListaUsuariosNotificar(List<MiembroNotificacionDTO> listaNotificacion) {
        List<CorreoElectronicoDTO> listaCorreoElectronico = new ArrayList<>();
        
        for(MiembroNotificacionDTO miembroNotificacionDTO : listaNotificacion) {
            CorreoElectronicoDTO correoElectronico = new CorreoElectronicoDTO();
            Usuario usuario = new Usuario();
            usuario.getUsuario().add(miembroNotificacionDTO.getUsuario());
            correoElectronico.setTo(usuario);
            if (null != miembroNotificacionDTO.getIdPlantilla()) {
                correoElectronico.setIdPlantilla(miembroNotificacionDTO.getIdPlantilla());
                
                // Se llena la solicitud de envio de correo de acuerdo al id de la plantilla
                switch(miembroNotificacionDTO.getIdPlantilla()) {
                case PLANTILLA_EMITIR_VOTO_NIVEL_PRESIDENCIA: //16
                    correoElectronico.setIdOperacion(miembroNotificacionDTO.getIdOperacion());
                    break;
                case PLANTILLA_EMITIR_VOTO_NIVEL_COMITE_OPERACION: //13
                    correoElectronico.setIdOperacion(miembroNotificacionDTO.getIdOperacion());
                    
                    Param tipoReunionParam = new Param();
                    tipoReunionParam.setTag("TIPO_REUNION");
                    if (null != miembroNotificacionDTO.getIdSolicitudAprobacion()) {
                        tipoReunionParam.setValor(miembroNotificacionDTO.getIdSolicitudAprobacion().toString());
                    }
                    correoElectronico.getParametros().add(tipoReunionParam);
                    
                    Param datosReunionParam = new Param();
                    datosReunionParam.setTag("DATOS_REUNION");
                    if (null != miembroNotificacionDTO.getIdSolicitudAprobacion()) {
                        datosReunionParam.setValor(miembroNotificacionDTO.getIdSolicitudAprobacion().toString());
                    }
                    correoElectronico.getParametros().add(datosReunionParam);
                    
                    break;
                case PLANTILLA_EMITIR_VOTO_NIVEL_COMITE_CLIENTE : //85
                    // Implementar en caso de necesitar notificaciones en aprobaciones por operacion
                    break;
                case PLANTILLA_CON_NOTIFICACION_SECRETARIO_PRESIDENCIA : //3
                    correoElectronico.setIdOperacion(miembroNotificacionDTO.getIdOperacion());
                    correoElectronico.setIdClienteFenix(miembroNotificacionDTO.getIdClienteFenix());
                    
                    Param idSolicitudParam = new Param();
                    idSolicitudParam.setTag("ID_SOLICITUD_APROBACION");
                    if (null != miembroNotificacionDTO.getIdSolicitudAprobacion()) {
                        idSolicitudParam.setValor(miembroNotificacionDTO.getIdSolicitudAprobacion().toString());
                    }
                    correoElectronico.getParametros().add(idSolicitudParam);
                    
                    Param idNivelAprobacionParam = new Param();
                    idNivelAprobacionParam.setTag("ID_NIVEL_APROBACION");
                    idNivelAprobacionParam.setValor(miembroNotificacionDTO.getIdNivelAprobacion().toString());
                    correoElectronico.getParametros().add(idNivelAprobacionParam);
                    
                    Param idUsuarioParam = new Param();
                    idUsuarioParam.setTag("ID_USUARIO");
                    if (null != miembroNotificacionDTO.getIdUsuarioReunion()) {
                        try {
                            idUsuarioParam.setValor(miembroNotificacionDTO.getIdUsuarioReunion());
                        } catch(Exception e) {
                            LOGGER.warning("No es posible otener el IdUsuarioReunion", e);
                        }
                        correoElectronico.getParametros().add(idUsuarioParam);
                    }
                    
                    break;
                case PLANTILLA_CON_NOTIFICACION_NO_SECRETARIO_PRESIDENCIA: //18
                    correoElectronico.setIdOperacion(miembroNotificacionDTO.getIdOperacion());
                    correoElectronico.setIdClienteFenix(miembroNotificacionDTO.getIdClienteFenix());
                    break;
                case PLANTILLA_CON_NOTIFICACION_NIVEL_COMITE_OPERACION : //15
                    correoElectronico.setIdOperacion(miembroNotificacionDTO.getIdOperacion());
                    
                    Param tipoReunion2Param = new Param();
                    tipoReunion2Param.setTag("TIPO_REUNION");
                    if (null != miembroNotificacionDTO.getIdSolicitudAprobacion()) {
                        tipoReunion2Param.setValor(miembroNotificacionDTO.getIdSolicitudAprobacion().toString());
                    }
                    correoElectronico.getParametros().add(tipoReunion2Param);
                    
                    Param datosReunion2Param = new Param();
                    datosReunion2Param.setTag("DATOS_REUNION");
                    if (null != miembroNotificacionDTO.getIdSolicitudAprobacion()) {
                        datosReunion2Param.setValor(miembroNotificacionDTO.getIdSolicitudAprobacion().toString());
                    }
                    correoElectronico.getParametros().add(datosReunion2Param);
                    break;
                case PLANTILLA_CON_NOTIFICACION_NIVEL_COMITE_CLIENTE: //87
                    // Implementar en caso de necesitar notificaciones en aprobaciones por operacion
                    break;
                }
            
                listaCorreoElectronico.add(correoElectronico);
            } else {
                LOGGER.warning("IdPlantilla es nulo.");
            }
        }
        
        return listaCorreoElectronico;
    }

    public void setGetInitForm(RichOutputText getInitForm) {
        this.getInitForm = getInitForm;
    }

    /**
     * Acción para precargar la información guardada para la tarea de "Dar seguimiento a votación"
     * @return
     */
    public RichOutputText getGetInitForm() {
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}") instanceof String) {
                String idTarea = (String) JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}");
                
                if (TAREA_DAR_SEGUIMIENTO_VOTACION.equals(idTarea)) {
                    this.cargarInfoGuardadaComiteCredito();
                    this.cargarInfoGuardadaConNotificacion();
                    this.cargarInfoGuardadaEquipoTrabajo();
                } else {
                    LOGGER.warning("No es TAREA_DAR_SEGUIMIENTO_VOTACION, la tarea es " + idTarea);
                }
            } else {
                LOGGER.warning("pIdTarea es NULL o no es un String");
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "*** " + e.getMessage());
        }
        return getInitForm;
    }
    
    private void cargarInfoGuardadaComiteCredito() {
        LOGGER.warning("cargando InfoGuardadaComiteCredito...");
        DCBindingContainer bc = ADFUtils.getDCBindingContainer();
        try{
        if (bc != null) {
            DCIteratorBinding iterator = bc.findIteratorBinding("GrupoRolAprobacionVO1Iterator");
            
            if (iterator != null) {
                ViewObject vo = iterator.getViewObject();
                
                if (vo != null) {
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                    
                    if (rsi != null) {
                        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                        boolean concidenMiembros = Boolean.TRUE;
                        
                        while (rsi.hasNext()) {
                            Row row = rsi.next();
                            
                            if (row != null) {
                                
                                if (row.getAttribute("IdRolBpm") != null) {
                                    Number idRolBpm = (Number) row.getAttribute("IdRolBpm");
                                    
                                    if (aprobacionBean != null && 
                                        aprobacionBean.getMapaComiteCredito() != null && 
                                        aprobacionBean.getMapaComiteCredito().containsKey(idRolBpm.intValue())) {
                                        LOGGER.warning("aprobacionBean o mapa es NULL o los roles son iguales");
                                        String nombreMiembro = (String ) aprobacionBean.getMapaComiteCredito().get(idRolBpm.intValue()).getAttribute("NombreUsuario");
                                        row.setAttribute("NombreMiembro", nombreMiembro);
                                    } else {
                                        concidenMiembros = Boolean.FALSE;
                                        LOGGER.warning("Los roles NO son iguales");
                                    }
                                } else {
                                    LOGGER.warning("IdRolBpm del row es null.");
                                }
                            } else {
                                LOGGER.warning("Row null.");
                            }
                        }
                        rsi.closeRowSetIterator();
                        this.msgComiteCredito = !concidenMiembros;
                    } else {
                        LOGGER.warning("RowSetIterator null.");
                    }
                } else {
                    LOGGER.warning("ViewObject null.");
                }
            } else {
                LOGGER.warning("DCIteratorBinding null.");
            }
        } else {
            LOGGER.warning("DCBindingContainer null.");
        }
        }catch(Exception e){
            LOGGER.warning("Error en cargarInfoGuardadaComiteCredito.", e);
        }
        LOGGER.warning("Terminó InfoGuardadaComiteCredito...");
    }
    
    private void cargarInfoGuardadaConNotificacion() {
        LOGGER.warning("cargando InfoGuardadaConNotificacion...");
        DCBindingContainer bc = ADFUtils.getDCBindingContainer();
        try{
        if (bc != null) {
            DCIteratorBinding iterator = bc.findIteratorBinding("MiembrosAprobacionNotificacionVO1Iterator");
            
            if (iterator != null) {
                ViewObject vo = iterator.getViewObject();
                
                if (vo != null) {
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                    
                    if (rsi != null) {
                        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                        boolean concidenMiembros = Boolean.TRUE;
                        
                        while (rsi.hasNext()) {
                            Row row = rsi.next();
                            
                            if (row != null) {
                                
                                if (row.getAttribute("Nombre") != null) {
                                    String usuario = (String) row.getAttribute("Nombre");
                                    
                                    if (aprobacionBean != null && 
                                        aprobacionBean.getMapaConNotificacion() != null && 
                                        aprobacionBean.getMapaConNotificacion().containsKey(usuario)) {
                                        LOGGER.warning("Los roles son iguales");
                                        Row rowMap = aprobacionBean.getMapaConNotificacion().get(usuario);
                                        Integer conNotificacion = (Integer) rowMap.getAttribute("ConNotificacion");
                                        row.setAttribute("ConNotificacion", conNotificacion == 1 ? Boolean.TRUE : Boolean.FALSE);
                                    } else {
                                        LOGGER.warning("Los roles NO son iguales");
                                        concidenMiembros = Boolean.FALSE;
                                        this.addUsuarioReunionAprobacion(row, 1, 0, 2, 0);
                                    }
                                } else {
                                    LOGGER.warning("IdTcaRolBpm del row es null.");
                                }
                            } else {
                                LOGGER.warning("Row null.");
                            }
                        }
                        rsi.closeRowSetIterator();
                        this.msgConNotificacion = !concidenMiembros;
                    } else {
                        LOGGER.warning("RowSetIterator null.");
                    }
                } else {
                    LOGGER.warning("ViewObject null.");
                }
            } else {
                LOGGER.warning("DCIteratorBinding null.");
            }
        } else {
            LOGGER.warning("DCBindingContainer null.");
        }
        }catch(Exception e){
            LOGGER.warning("Error en cargarInfoGuardadaConNotificacion.", e);
        }
        LOGGER.warning("Terminó InfoGuardadaConNotificacion...");
    }
    
    private void cargarInfoGuardadaEquipoTrabajo() {
        LOGGER.warning("cargando InfoGuardadaEquipoTrabajo...");
        DCBindingContainer bc = ADFUtils.getDCBindingContainer();
        
        if (bc != null) {
            DCIteratorBinding iterator = bc.findIteratorBinding("MiembrosAprobacionEquipoTrabajoVO1Iterator");
            
            if (iterator != null) {
                ViewObject vo = iterator.getViewObject();
                
                if (vo != null) {
                    RowSetIterator rsi = vo.createRowSetIterator(null);
                    
                    if (rsi != null) {
                        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
                        boolean concidenMiembros = Boolean.TRUE;
                        
                        while (rsi.hasNext()) {
                            Row row = rsi.next();
                            
                            if (row != null) {
                                
                                if (row.getAttribute("Nombre") != null) {
                                    String usuario = (String) row.getAttribute("Nombre");
                                    
                                    if (aprobacionBean != null && 
                                        aprobacionBean.getMapaEquipoTrabajo() != null && 
                                        aprobacionBean.getMapaEquipoTrabajo().containsKey(usuario)) {
                                        LOGGER.warning("Los roles son iguales");
                                        Row rowMap = aprobacionBean.getMapaEquipoTrabajo().get(usuario);
                                        Integer conNotificacion = (Integer) rowMap.getAttribute("ConNotificacion");
                                        row.setAttribute("ConNotificacion", conNotificacion == 1 ? Boolean.TRUE : Boolean.FALSE);
                                    } else {
                                        LOGGER.warning("Los roles NO son iguales");
                                        concidenMiembros = Boolean.FALSE;
                                        this.addUsuarioReunionAprobacion(row, 1, 0, 3, 0);
                                    }
                                } else {
                                    LOGGER.warning("IdTcaRolBpm del row es null.");
                                }
                            } else {
                                LOGGER.warning("Row null.");
                            }
                        }
                        rsi.closeRowSetIterator();
                        this.msgEquipoTrabajo = !concidenMiembros;
                    } else {
                        LOGGER.warning("RowSetIterator null.");
                    }
                } else {
                    LOGGER.warning("ViewObject null.");
                }
            } else {
                LOGGER.warning("DCIteratorBinding null.");
            }
        } else {
            LOGGER.warning("DCBindingContainer null.");
        }
        LOGGER.warning("Terminó InfoGuardadaEquipoTrabajo...");
    }
    
    private void addUsuarioReunionAprobacion(Row row, Integer banEstatus, Integer comoLeido, Integer tipoUsuario, Integer conNotificacion) {
        LOGGER.warning("guardando Usuario reunión aprobación...");
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            
            map.put("IdTcaRolBpm", row.getAttribute(tipoUsuario == 1 ? "IdRolBpm" : "IdTcaRolBpm"));
            map.put("LoginUsuario", row.getAttribute("Usuario"));
            Boolean emiteVoto = row.getAttribute("EmiteVoto") != null ? Boolean.valueOf(row.getAttribute("EmiteVoto").toString()) : Boolean.FALSE;
            map.put("EmiteVoto", emiteVoto ? 1 : 0);
            map.put("NombreUsuario", row.getAttribute(tipoUsuario == 1 ? "NombreMiembro" : "Nombre"));
            
            Number idSolicitud = new Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}") != null ?
                                            JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}").toString() : "0");
            map.put("IdSolicitudAprobacion", idSolicitud);
            map.put("FechaRegistro", new java.sql.Timestamp(System.currentTimeMillis()));
            map.put("BanEstatus", banEstatus);
            map.put("MarcadoComoLeido", comoLeido);
            map.put("IdTcaTipoUsuario", tipoUsuario);
            map.put("ConNotificacion", conNotificacion);
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("map", map);
            super.execute(params, "create");
        } catch (OperationExecuteException e) {
            LOGGER.log(ADFLogger.ERROR, "***OperationExecuteException*** " + e.getMessage());
        } catch (SQLException e) {
            LOGGER.log(ADFLogger.ERROR, "***SQLException*** " + e.getMessage());
        }
        LOGGER.warning("terminó de guardar Usuario reunión aprobación...");
    }
    
    private void updateUsuarioReunionAprobacion(Row row) {
        LOGGER.warning("actualizando Usuario reunión aprobación...");
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("rowMap", row);
            LOGGER.warning("actualizando...");
            super.execute(params, "update");
            LOGGER.warning("Se actualizó de forma correcta.");
        } catch (OperationExecuteException e) {
            LOGGER.log(ADFLogger.ERROR, "***OperationExecuteException*** " + e.getMessage());
        }
        LOGGER.warning("terminó de actualizar Usuario reunión aprobación...");
    }
    
    private void filtrarUsuarioReunionAprobacion() {
        LOGGER.warning("Entra en filtrarUsuarioReunionApronacion.");
        Long idSolicitud = null;
        try {
            idSolicitud = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}") != null ?
                                            JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}").toString() : "0");

            LOGGER.warning("idSolicitud : " + idSolicitud);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idSolicitud", idSolicitud);
            super.execute(params, "obtenerUsuarioReunionRow");
        } catch (OperationExecuteException e) {
            LOGGER.log(ADFLogger.ERROR, "***OperationExecuteException*** " + e.getMessage());
        }
        LOGGER.warning("Termina filtrar miembros aprobacion.");
    }
    
    private boolean commitUsuarioReunionAprobacion() {
        boolean guardoConExito = Boolean.FALSE;
        LOGGER.warning("haciendo commit Usuario reunión aprobación...");
        try {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("crudCommit");
            operationBinding.execute();

            if (operationBinding.getErrors().isEmpty()) {
                guardoConExito = Boolean.TRUE;
            } else {
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }

            LOGGER.warning("Se ejecutó el commit de forma correcta.");
        } catch (Exception e) {
            LOGGER.warning("Error en la actualizacion.", e);
        }
        LOGGER.warning("terminó de hacer commit Usuario reunión aprobación...");
        
        return guardoConExito;
    }
    
    private Long obtenerIdSolicitudAprobacion() {
        Long idSolicitud = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}") != null ?
                                        JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitud}").toString() : "0");
        return idSolicitud;
    }
    
    private Long obtenerIdOperacion() {
        Long idSolicitud = new Long(JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}") != null ?
                                        JSFUtils.resolveExpression("#{pageFlowScope.idOperacion}").toString() : "0");
        return idSolicitud;
    }

    public String invocarRequiereAED() {
        AprobacionBean aprobacionBean = (AprobacionBean) JSFUtils.resolveExpression(AprobacionBean.BEAN_EXPRESSION);
        LOGGER.entering(AprobacionActionBean.class.getName(), "invocarFinalizarTarea");

        try {
            LOGGER.warning("Inicia el proceso de Invocar Requiere AED");

            List<String> budleKeyErroList = new ArrayList<String>();
            Boolean isValidFinalizarTarea = Boolean.FALSE;
            String msgErrorKey = null;

            Boolean isRN03Valid = null;
            int codigoTarea = getCodigoTarea();

            switch (codigoTarea) {
            case FenixConstants.PC04_REALIZARMODIFICACIONES:
                LOGGER.fine("PC04_MODIFICARPROYECTORESOLUCION");
                isValidFinalizarTarea = Boolean.TRUE;
                break;
            default:
                LOGGER.log(ADFLogger.ERROR, "invocarFinalizarTarea(): Codigo de tarea no valido - " + codigoTarea);
                break;
            }

            if (!isValidFinalizarTarea) {
                if (budleKeyErroList.size() > 0) {
                    for (String bundleKey : budleKeyErroList)
                        JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
                }
            } else {
                requiereAEDPopup();
            }
        } catch (Exception e) {
            LOGGER.severe("Error al procesar invocar finalizar tarea", e);
        }

        LOGGER.exiting(AprobacionActionBean.class.getName(), "invocarFinalizarTarea", null);
        return null;
    }

    public RichPopup getPopupRequiereAED() {
        return popupRequiereAED;
    }

    public void setPopupRequiereAED(RichPopup popupRequiereAED) {
        this.popupRequiereAED = popupRequiereAED;
    }
    
    public Boolean validarVotantesDecisores(){
        Boolean respuesta=Boolean.FALSE;
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = binding.getOperationBinding("validarLista");
          //  operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
            LOGGER.warning("Error al obtener los datos");
            }
            else{
                    respuesta=(Boolean)operationBinding.getResult(); 
                }
            LOGGER.warning("valor final obtenida para validar a los votantes o decisores: " + respuesta);
        return respuesta;
        }
    
       public void actualizarDatosSCR(Long idCliente, String nombre, String login){
         LOGGER.warning("Inicia metodo actualizarDatosSCR");
            BindingContainer binding = getBindings();
            OperationBinding operationBinding = null;
           
            try{  
                    operationBinding = binding.getOperationBinding("actualizarSCRCliente");                      
                    operationBinding.getParamsMap().put("idCliente", idCliente);
                    operationBinding.getParamsMap().put("login", login);
                    operationBinding.getParamsMap().put("nombre", nombre);                    
                    operationBinding.execute();
            }catch(Exception e){
                LOGGER.warning("ha ocurrido un error al ejecutar el metodo actualizarSCRCliente ->", e);
                JSFUtils.addFacesErrorMessage("Ha ocurrido un error ->"+e.getMessage());
            }
            
            if(!operationBinding.getErrors().isEmpty()){
                LOGGER.warning("Error al invocar el metodo de actualizarSCR");
            }
           
           
          LOGGER.warning("termina metodo actualizarDatosSCR"); 
        }
    
    private Boolean existenCondicionesPorIdOperacion() {
        LOGGER.warning("Entra en existenCondicionesPorIdOperacion");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("existenCondicionesPorIdOperacion");

        Boolean existenCondiciones = null;
        
        if(getIdOperacion()!= null){
            operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
        }else{
            LOGGER.severe("Error al obtener el Id de Operacion desde Managed Bean");
        }
        
        operationBinding.execute();
        if(operationBinding.getErrors().isEmpty()) {
            // Asignación para Monto aprobado
            existenCondiciones = (Boolean)operationBinding.getResult();
        } else {
            LOGGER.severe("Error al ejecutar el operation binding existenCondicionesPorIdOperacion");
        }
        
        LOGGER.warning("Fuera de existenCondicionesPorIdOperacion,existenCondiciones :"+existenCondiciones);
        return existenCondiciones;
    }  
    
    public void asignarDesicionCurrent() {
        LOGGER.warning("Inside asignarDesicionCurrent.");

        AprobacionBean aprobacionBean =
            (AprobacionBean) JSFUtils.resolveExpression("#{pageFlowScope.aprobacionManagedBean}");

        Long idSolicitud = new Long(aprobacionBean.getIdSolicitud());

        BindingContainer binding = getBindings();
        OperationBinding operationBinding = null;
        try {
            operationBinding = binding.getOperationBinding("asignarDesicionCurrent");
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            operationBinding.execute();
        } catch (Exception e) {
            LOGGER.warning("ha ocurrido un error al ejecutar el metodo actualizarSCRCliente ->", e);
        }
        if (!operationBinding.getErrors().isEmpty()) {
            LOGGER.warning("Error al invocar el metodo de actualizarSCR");
        }
        LOGGER.warning("termina metodo asignarDesicionCurrent");
    }
	
	public void monedaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inside monedaValueChangeListener: " + valueChangeEvent.getComponent().getId());
        
        Integer IdTcaTipoMoneda = (Integer) valueChangeEvent.getNewValue();
        IdTcaTipoMoneda = IdTcaTipoMoneda + 1;
        Row rowSelected = getIteratorCurrentRow(DATOS_APROBACION_ITERATOR);
        rowSelected.setAttribute("IdTcaTipoMoneda", IdTcaTipoMoneda);
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        LOGGER.warning("id Moneda: " + IdTcaTipoMoneda);

    }	
}
