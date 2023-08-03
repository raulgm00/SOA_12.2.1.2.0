package pa12prepagoght.bean;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.ResourceBundle;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.AttributeBinding;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.bpel.services.workflow.worklist.adf.InvokeActionBean;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import oracle.jdbc.proxy.annotation.Pre;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
//import org.bcie.fenix.view.observacioncargoprepago.ObservacionCargoPrepagoBean;

import oracle.jbo.Row;

import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.DateListProvider;

import org.bcie.fenix.view.observacioncargoprepago.ObservacionCargoPrepagoBean;
import org.bcie.fenix.view.penalidadintereses.PenalidadInteresesBean;
import org.bcie.fenix.view.gestioncomisionprepago.GestionComisionActionBean;

import java.sql.Timestamp;

import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.faces.event.ActionEvent;

import oracle.bpel.services.workflow.WorkflowException;

public class PrepagoActionBean extends FenixActionBean {
    
    public static ADFLogger logger = null;
    public static final String BUNDLE = "pa12prepagoght.PA12PrepagoGHTBundle";
    
    private RichPopup popupFinalizarTarea;
    private RichPopup popupMasInformacion;
    private RichRegion validaInformacionCargoPrepago;
    private RichInputDate uiFechaSolicitud;
    private RichInputDate uiFechaPrepago;
    private static final Integer ID_MANUAL=1;
    private static final Integer ID_POR_PRORRATEO=2;
    private static final String DAY_SATURDAY = "sat";
    private static final String DAY_SUNDAY = "sun";
    public static final Set<String> WEEKEND = new HashSet<String>(Arrays.asList(DAY_SATURDAY, DAY_SUNDAY));
    public static final Set<String> EMPTY = new HashSet<String>();
    
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
    
    //Dias minimos de notificacion dependiendo de el tipo  cliente
    public static final Integer ANTICIPACION_PRE10_2008_SECTOR_PUBLICO = 30;
    public static final Integer ANTICIPACION_PRE10_2008_SECTOR_PRIVADO = 10;
    public static final Integer ANTICIPACION_PRE28_2003_SECTOR_PUBLICO = 30;
    public static final Integer ANTICIPACION_PRE28_2003_SECTOR_PRIVADO = 5;
    
    //Componente seleccionado en la vista, para setear valores a nulos
    public static final Integer FECHA_SOLICITUD = 1;
    public static final Integer RESOLUCION = 2;
    public static final Integer FECHA_PREPAGO = 3;
    public static final Integer MONEDA = 4;
    
    private static final String MONTO_CARGO_DEFECTO = "500";
    
    //Tipos de resolucion
    public static final Integer PRE10_2008 = 1;
    public static final Integer PRE28_2003 = 2;
    public static final Integer OTRAS_CONDICIONES = 3;
    private RichOutputText initListaTipoMoneda;
    private RichPopup popupActualizarPrepago;
    private RichPopup popupActualizarFechaPrepago;
    private List<Date> diasInhabilesMoneda;
    private RichPopup popupDetalleComision;
    private RichOutputText initListaDiasHabiles;
    private RichSelectOneChoice monedaUIC;
    private RichTable tablaContratoUIC;
    private RichSelectOneChoice tipoResolucionUI;
    
    private Date fechaInicioPre2008;
    private Date fechaVigenciaPre2003;
    private RichTreeTable treeContratoUIC;
    
    private String contratoFlexSeleccionado;
    private Long idPadreSeleccionado;
    private RichOutputText calculoUIC;
    private RichOutputText cargoTramiteUIC;
    private RichPanelGroupLayout groupCargoTramiteUIC;
    private RichPanelHeader headerTablaContratoUIC;

    public PrepagoActionBean() {
        super();
        if (logger == null){
            logger = ADFLogger.createADFLogger(PrepagoActionBean.class);
        }
    }
    
    public String invocarFinalizarTarea() {
        logger.warning("Inicia validacion para mostrar mensaje de confirmacion de Finalizar tarea");

        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean existeDiferencia = Boolean.FALSE;
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        Boolean condicion1 = Boolean.FALSE;
        Boolean condicion2 = Boolean.FALSE;
        Boolean condicion3 = Boolean.FALSE;
        Boolean condicion4 = Boolean.FALSE;
        Boolean condicion5 = Boolean.FALSE;
        Boolean isDocumento = Boolean.FALSE;
        Boolean isValidar = Boolean.FALSE;

        Boolean isCargoPrepago = Boolean.FALSE;

        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
        case FenixConstants.PA12_INGRESAR_SOLICITUD_PREPAGO:
        case FenixConstants.PA12_INGRESAR_SOLICITUD_PREPAGO_145:
            logger.fine("PA12_INGRESAR_SOLICITUD_PREPAGO");
            logger.warning("Ingresar solicitud de prepago");
            logger.warning("esLineaGlobalCredito: " + prepagoBean.getEsLineaGlobal());
            logger.warning("esBtnCalcularBloqueado: " + prepagoBean.getEsBtnCalcularBloqueado());
            
            if(!prepagoBean.getEsBtnCalcularBloqueado())
            {
                bundleKeyErrorList.add("ERROR_BOTON_CALCULAR_TXT");
                logger.warning("El boton de calcular no esta bloqueado: " + prepagoBean.getEsBtnCalcularBloqueado());
                isValidFinalizarTarea = Boolean.FALSE;
            }
            else{
                /* RN_01 En la RESOLUCIÓN No. PRE-10/2008 el prestatario deberá notificar al BCIE, por medio de la respectiva Gerencia de País, su intención de efectuar un prepago, con una anticipación mínima de treinta (30) días hábiles,
                     *       cuando se trate de financiamientos al sector público con garantía soberana, en relación con la fecha en que pretenda efectuar el prepago.
                     * RN_02 En la RESOLUCIÓN No. PRE-10/2008 o posterior el prestatario deberá notificar al BCIE, por medio de la respectiva Gerencia de País, su intención de efectuar un prepago, con una anticipación mínima de treinta (10) días hábiles,
                     *       cuando se trate de financiamientos al sector privado e instituciones del sector público sin garantía soberana del Estado, en relación con la fecha en que pretenda efectuar el prepago.
                     * RN_03 En la RESOLUCIÓN No. PRE-28/2003  el prestatario deberá notificar al BCIE, por medio de la Gerencia Regional del respectivo país, su intención de efectuar un prepago, con una anticipación de treinta (30) días hábiles,
                     *       para el sector público, en relación con la fecha en que debería efectuar su próxima amortización.
                     * RN_04 En la RESOLUCIÓN No. PRE-28/2003  el prestatario deberá notificar al BCIE, por medio de la Gerencia Regional del respectivo país, su intención de efectuar un prepago, con una anticipación de treinta (30) días hábiles,
                     *       para el sector privado, en relación con la fecha en que debería efectuar su próxima amortización.
                     * EX01 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-10/2008) para financiamientos al sector público con garantía soberana del estado.
                     * EX02 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-10/2008) para financiamientos al sector privado e instituciones del sector público sin garantía soberana del Estado.
                     * EX03 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-28/2003) para financiamientos al sector público.
                     * EX04 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación  requerida. - Fecha de Prepago (PRE-28/2003) para financiamientos al sector privado.
                     * EX05 Identifica que no se ha capturado la información requerida. - Datos obligatorios
                     * EX06 Identifica que el total de la columna "Monto prepago" no es igual al monto capturado en el campo "Monto prepagar". - Diferencias en el campo Monto prepagar y el total de la columna monto prepago.
                     * EX07 El sistema identifica que la fecha prepago ingresada es distinta a una fecha de amortización. - Fecha de prepago diferente a fecha de amortización para PRE-28/2003.
                     * EX08 El sistema identifica que el monto prepagar es mayor al total del capital no vencido a la fecha del prepago. - Monto prepagar mayor a la sumatoria del capital no vencido a la fecha de prepago.
                     * EX09 El sistema identifica que el monto prepago de un contrato de desembolso es mayor al capital no vencido a la fecha del prepago. - Monto prepago mayor al capital no vencido a la fecha de prepago del contrato de desembolso.
                     * MSG_01 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 30 días hábiles a la fecha del prepago.
                     * MSG_02 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 10 días hábiles a la fecha del prepago.
                     * MSG_03 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 30 días hábiles a la fecha de la próxima amortización.
                     * MSG_04 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 5 días hábiles a la fecha de la próxima amortización.
                     * MSG_05 Favor de completar en su totalidad la información requerida.
                     * MSG_06 Es necesario que el total del monto prepago sea igual al monto prepagar ingresado.
                     * MSG_07 La fecha de prepago debe ser igual o mayor a la fecha ingresada en la solicitud.
                     * MSG_08 La fecha de prepago ingresada no corresponde a una fecha de amortización del préstamo.
                     * MSG_09 El monto prepago no debe exceder el monto del capital no vencido a la fecha de prepago.
                     * MSG_10 El monto prepagar no debe exceder el monto de la sumatoria del capital no vencido a la fecha de prepago de todos los contratos.
                     */
    
                Integer idTipoResolucion = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoResolucion");
                BigDecimal montoTotalPrepago = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoTotalPrepago");
                BigDecimal montoPrepago = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoPrepago");
                BigDecimal capitalNovencidoTotalPrepago =
                    (BigDecimal) ADFUtils.getBoundAttributeValue("CapitalNovencidoTotalPrepago");
    
                logger.warning("idTipoResolucion" + idTipoResolucion);
                logger.warning("montoTotalPrepago" + montoTotalPrepago);
                logger.warning("montoPrepago" + montoPrepago);
                logger.warning("capitalNovencidoTotalPrepago" + capitalNovencidoTotalPrepago);
    
                condicion1 = validaFinalizaCamposRequeridos();
                logger.warning("condicion1: " + condicion1);
    
                if (!condicion1) {
                    bundleKeyErrorList.add("MSG05_INGRESAR_SOLICITUD_PREPAGO");
                }
    
                if (montoPrepago != null) {
                    condicion2 = validaFinalizaDiferenciaMonto();
                    logger.warning("condicion2: " + condicion2);
    
                    if (!condicion2) {
                        bundleKeyErrorList.add("MSG06_INGRESAR_SOLICITUD_PREPAGO");
                    }
                }
    
                //Compara si el tipo de resolución es PRE-28/2003
                if (idTipoResolucion != null) {
                    if (idTipoResolucion.compareTo(PRE28_2003) == 0) {
                        //condicion3 = validaFinalizaDiferenciaFecha();
                        condicion3 = Boolean.TRUE;
                        logger.warning("condicion3: " + condicion3);
    
                        if (!condicion3) {
                            bundleKeyErrorList.add("MSG07_INGRESAR_SOLICITUD_PREPAGO");
                        }
                    }
                }
    
                if (montoPrepago != null && capitalNovencidoTotalPrepago != null) {
                    condicion4 = validaFinalizaMontoCapitalTotal();
                    logger.warning("condicion4: " + condicion4);
    
                    if (!condicion4) {
                        bundleKeyErrorList.add("MSG09_INGRESAR_SOLICITUD_PREPAGO");
                    }
                }
    
                if (montoPrepago != null) {
                    condicion5 = validaFinalizaMontoCapital();
                    logger.warning("condicion5: " + condicion5);
    
                    if (!condicion5) {
                        bundleKeyErrorList.add("MSG08_INGRESAR_SOLICITUD_PREPAGO");
                    }
                }
    
                //Compara si el tipo de resolución es PRE-28/2003
    
                if (idTipoResolucion != null) {
                    if (idTipoResolucion.compareTo(PRE28_2003) == 0) {
                        isValidFinalizarTarea = condicion1 && condicion2 && condicion3 && condicion4 && condicion5;
                    } else {
                        isValidFinalizarTarea = condicion1 && condicion2 && condicion4 && condicion5;
                    }
                }
    
                if (idTipoResolucion == null) {
                    isValidFinalizarTarea = condicion1 && condicion2 && condicion4 && condicion5;
                }
            }
            break;
        case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_COPRES:
        case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_DAECI:
        case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_MDC:
        case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI:
            if (getCodigoTarea().compareTo(FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_COPRES) == 0) {
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_COPRES");
                logger.warning("Determinar cargos de prepago COPRES");
            }

            if (getCodigoTarea().compareTo(FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_DAECI) == 0) {
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_DAECI");
                logger.warning("Determinar cargos de prepago DAECI");
            }

            if (getCodigoTarea().compareTo(FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_MDC) == 0) {
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_MDC");
                logger.warning("Determinar cargos de prepago MDC");
            }

            if (getCodigoTarea().compareTo(FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI) == 0) {
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI");
                logger.warning("Determinar cargos de prepago SEPRI");
            }

            /* RN_02 Para finalizar la tarea cuando al prepago le apliquen cargos adicionales será necesario indicar el monto, la moneda y capturar las observaciones relacionadas al cargo. En caso contrario solo debe capturar las observaciones.
                 * EX02 Identifica que no se ha capturado la información requerida.
                 * MSG_02 Favor de completar en su totalidad la información requerida.
                 */
            isCargoPrepago = validarCargoPrepago();
            if (!isCargoPrepago) {
                //Valida que se ingrese la informacion Requerida
                bundleKeyErrorList.add("MSG02_DETERMINAR_CARGOS_PREPAGO");
                logger.log(ADFLogger.WARNING, "Informacion incompleta en MDC...");
            }

            isValidFinalizarTarea = (isCargoPrepago) ? Boolean.TRUE : Boolean.FALSE;

            break;
        case FenixConstants.PA12_VALIDAR_PENALIDAD_PREPAGO_COFI:
            logger.fine("PA12_VALIDAR_PENALIDAD_PREPAGO_COFI");
            logger.warning("Validar penalidad de prepago COFI");

            /* RN_12 Para finalizar la tarea será necesario indicar la fecha que estará vigente el cálculo de penalización.
                 * EX02 Identifica que no se ha capturado la información requerida.
                 * MSG_02 Favor de completar en su totalidad la información requerida.
                 */
            isCargoPrepago = validarCargoPrepago();
            if (!isCargoPrepago) {
                //Valida que se ingrese la informacion Requerida
                bundleKeyErrorList.add("MSG02_DETERMINAR_CARGOS_PREPAGO");
                logger.log(ADFLogger.WARNING, "Informacion incompleta en COFI...");
            }

            isValidFinalizarTarea = (isCargoPrepago) ? Boolean.TRUE : Boolean.FALSE;

            break;
        case FenixConstants.PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO:
        case FenixConstants.PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO_146:
            logger.fine("PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO");
            logger.warning("Realizar ajustes a solicitud de prepago");
            logger.warning("esLineaGlobalCredito: " + prepagoBean.getEsLineaGlobal());
            if(!prepagoBean.getEsBtnCalcularBloqueado())
            {
                bundleKeyErrorList.add("ERROR_BOTON_CALCULAR_TXT");
                logger.warning("El boton de calcular no esta bloqueado: " + prepagoBean.getEsBtnCalcularBloqueado());
                isValidFinalizarTarea = Boolean.FALSE;
            }
            else{
                /* RN_01 En la RESOLUCIÓN No. PRE-10/2008 o posterior el prestatario deberá notificar al BCIE, por medio de la respectiva Gerencia de País, su intención de efectuar un prepago,
                     *       con una anticipación mínima de treinta (30) días hábiles, cuando se trate de financiamientos al sector público con garantía soberana, en relación con la fecha en que pretenda efectuar el prepago.
                     * RN_02 En la RESOLUCIÓN No. PRE-10/2008 o posterior el prestatario deberá notificar al BCIE, por medio de la respectiva Gerencia de País, su intención de efectuar un prepago,
                     *       con una anticipación mínima de treinta (10) días hábiles, cuando se trate de financiamientos al sector privado e instituciones del sector público sin garantía soberana del Estado, en relación con la fecha en que pretenda efectuar el prepago.
                     * RN_03 En la RESOLUCIÓN No. PRE-28/2003  el prestatario deberá notificar al BCIE, por medio de la Gerencia Regional del respectivo país, su intención de efectuar un prepago, con una anticipación de treinta (30) días hábiles,
                     *       para el sector público, en relación con la fecha en que debería efectuar su próxima amortización.
                     * RN_04 En la RESOLUCIÓN No. PRE-28/2003  el prestatario deberá notificar al BCIE, por medio de la Gerencia Regional del respectivo país, su intención de efectuar un prepago, con una anticipación de treinta (5) días hábiles,
                     *       para el sector privado, en relación con la fecha en que debería efectuar su próxima amortización.
                     * RN_07 En la RESOLUCIÓN No. PRE-28/2003 la fecha prepago únicamente puede ser una fecha de amortización del préstamo.
                     * EX01 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-10/2008) para financiamientos al sector público con garantía soberana del estado.
                     * EX02 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-10/2008) para financiamientos al sector privado e instituciones del sector público sin garantía soberana del Estado.
                     * EX03 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-28/2003) para financiamientos al sector público.
                     * EX04 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-28/2003) para financiamientos al sector privado.
                     * EX05 Identifica que no se ha capturado la información requerida.
                     * EX06 Identifica que el total de la columna "Monto prepago" no es igual al monto capturado en el campo "Monto prepagar".
                     * EX07 El sistema identifica que la fecha prepago es menor a la fecha ingresada en la solicitud. MS8
                     * EX08 El sistema identifica que la fecha prepago ingresada es distinta a una fecha de amortización. MSG9
                     * EX09 El sistema identifica que el monto prepagar es mayor al total del capital no vencido a la fecha del prepago. 10
                     * EX10 El sistema identifica que el monto prepago de un contrato de desembolso es mayor al capital no vencido a la fecha del prepago. 11
                     * MSG_01 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 30 días hábiles a la fecha de prepago.
                     * MSG_02 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 10 días hábiles a la fecha de prepago.
                     * MSG_03 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 30 días hábiles a la fecha de la próxima amortización.
                     * MSG_04 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 5 días hábiles a la fecha de la próxima amortización.
                     * MSG_05 Favor de completar en su totalidad la información requerida.
                     * MSG_06 Es necesario que el total del monto prepago sea igual al monto prepagar ingresado.
                     * MSG_07 Favor de completar en su totalidad la información requerida.
                     * MSG_08 La fecha prepago debe ser igual o mayor a la fecha ingresada en la solicitud
                     * MSG_09 El monto prepagar no debe exceder el total del capital no vencido.
                     * MSG_10 El monto prepago no debe exceder el monto del capital no vencido.
                     */
    
                Integer idTipoResolucionRAP = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoResolucion");
                BigDecimal montoTotalPrepagoRAP = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoTotalPrepago");
                BigDecimal montoPrepagoRAP = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoPrepago");
                BigDecimal capitalNovencidoTotalPrepagoRAP =
                    (BigDecimal) ADFUtils.getBoundAttributeValue("CapitalNovencidoTotalPrepago");
    
                logger.warning("idTipoResolucion" + idTipoResolucionRAP);
                logger.warning("montoTotalPrepago" + montoTotalPrepagoRAP);
                logger.warning("montoPrepago" + montoPrepagoRAP);
                logger.warning("capitalNovencidoTotalPrepago" + capitalNovencidoTotalPrepagoRAP);
    
                condicion1 = validaFinalizaCamposRequeridos();
                logger.warning("condicion1: " + condicion1);
    
                if (!condicion1) {
                    bundleKeyErrorList.add("MSG05_INGRESAR_SOLICITUD_PREPAGO");
                }
    
                if (montoPrepagoRAP != null) {
                    condicion2 = validaFinalizaDiferenciaMonto();
                    logger.warning("condicion2: " + condicion2);
    
                    if (!condicion2) {
                        bundleKeyErrorList.add("MSG06_INGRESAR_SOLICITUD_PREPAGO");
                    }
                }
    
                //Compara si el tipo de resolución es PRE-28/2003
                if (idTipoResolucionRAP != null) {
                    if (idTipoResolucionRAP.compareTo(PRE28_2003) == 0) {
                        //condicion3 = validaFinalizaDiferenciaFecha();
                        condicion3 = Boolean.TRUE;
                        logger.warning("condicion3: " + condicion3);
    
                        if (!condicion3) {
                            bundleKeyErrorList.add("MSG07_INGRESAR_SOLICITUD_PREPAGO");
                        }
                    }
                }
    
                if (montoPrepagoRAP != null && capitalNovencidoTotalPrepagoRAP != null) {
                    condicion4 = validaFinalizaMontoCapitalTotal();
                    logger.warning("condicion4: " + condicion4);
    
                    if (!condicion4) {
                        bundleKeyErrorList.add("MSG09_INGRESAR_SOLICITUD_PREPAGO");
                    }
                }
    
                if (montoPrepagoRAP != null) {
                    condicion5 = validaFinalizaMontoCapital();
                    logger.warning("condicion5: " + condicion5);
    
                    if (!condicion5) {
                        bundleKeyErrorList.add("MSG08_INGRESAR_SOLICITUD_PREPAGO");
                    }
                }
    
                //Compara si el tipo de resolución es PRE-28/2003
    
                if (idTipoResolucionRAP != null) {
                    if (idTipoResolucionRAP.compareTo(PRE28_2003) == 0) {
                        isValidFinalizarTarea = condicion1 && condicion2 && condicion3 && condicion4 && condicion5;
                    } else {
                        isValidFinalizarTarea = condicion1 && condicion2 && condicion4 && condicion5;
                    }
                }
    
                if (idTipoResolucionRAP == null) {
                    isValidFinalizarTarea = condicion1 && condicion2 && condicion4 && condicion5;
                }
            }
            break;
        case FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE:
        case FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE_147:
            logger.fine("PA12_GESTIONAR_PREPAGO_CLIENTE");
            logger.warning("Gestionar prepago con el cliente");
            Boolean isComision = Boolean.FALSE;
            Boolean isComentario = Boolean.FALSE;
            Boolean isFechaValida = Boolean.FALSE;
            /* RN_01 Para solicitar la salida "Actualizar prepago", "Desestimar  solicitud", "Desestimar prepago" o "Actualizar penalidad"  es necesario capturar al menos un comentario.
                 * RN_02 Para la PRE-28/2003 el cargo por trámite a cobrar será el que resulte mayor entre la penalidad de la fuente externa y la penalidad de la solicitud obtenida.
                 *       no debe ser considerado en el consolidado del pago cuando el monto es menor al de la fuentes externas.
                 * RN_04 Al solicitar la salida actualizar prepago será necesario que cada área valide los cargos y la penalidad aplicada a la solicitud, dicha información capturada por cada área debe estar en formato de edición.
                 * RN_05 En la RESOLUCIÓN No. PRE-10/2008 o posterior el prestatario deberá notificar al BCIE, por medio de la respectiva Gerencia de País, su intención de efectuar un prepago, con una anticipación mínima de treinta (30) días hábiles, cuando se trate de financiamientos al sector público con garantía soberana, en relación con la fecha en que pretenda efectuar el prepago.
                 * RN_06 En la RESOLUCIÓN No. PRE-10/2008 o posterior el prestatario deberá notificar al BCIE, por medio de la respectiva Gerencia de País, su intención de efectuar un prepago, con una anticipación mínima de treinta (10) días hábiles, cuando se trate de financiamientos al sector privado e instituciones del sector público sin garantía soberana del Estado, en relación con la fecha en que pretenda efectuar el prepago.
                 * RN_07 En la RESOLUCIÓN No. PRE-28/2003  el prestatario deberá notificar al BCIE, por medio de la Gerencia Regional del respectivo país, su intención de efectuar un prepago, con una anticipación de treinta (30) días hábiles, para el sector público, en relación con la fecha en que debería efectuar su próxima amortización.
                 * RN_08 En la RESOLUCIÓN No. PRE-28/2003  el prestatario deberá notificar al BCIE, por medio de la Gerencia Regional del respectivo país, su intención de efectuar un prepago, con una anticipación de treinta (5) días hábiles, para el sector privado, en relación con la fecha en que debería efectuar su próxima amortización.
                 * RN_09 Para solicitar la salida "Desestimar prepago" es necesario ingresar la comisión por incumplimiento de prepago.
                 * RN_10 No se podrá solicitar la salida "Aceptar prepago" si la fecha de vigencia de penalidad ya se ha cumplido.
                 * EX01 Identifica que no se ha agregado un comentario.
                 * EX02 Identifica que la fecha mínima de notificación para efectuar un prepago no coincide con la fecha de anticipación requerida. - Fecha de Prepago (PRE-10/2008) para financiamientos al sector público con garantía soberana del estado.
                 * EX03 Identifica que la fecha mínima de notificación para efectuar un prepago no coincide con la fecha de anticipación requerida. - Fecha de Prepago (PRE-10/2008) para financiamientos al sector privado e instituciones del sector público sin garantía soberana del Estado.
                 * EX04 Identifica que la fecha mínima de notificación para efectuar un prepago no coincide con la fecha de anticipación requerida. - Fecha de Prepago (PRE-28/2003) para financiamientos al sector público.
                 * EX05 Identifica que la fecha mínima de notificación para efectuar un prepago no coincide con la fecha de anticipación requerida. - Fecha de Prepago (PRE-28/2003) para financiamientos al sector.
                 * EX06 Identifica que no se ha modificado la comisión tipo "Incumplimiento de prepago".
                 * MSG_01 Es necesario agregar al menos un comentario.
                 * MSG_02 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 30 días hábiles a la fecha del prepago.
                 * MSG_03 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 10 días hábiles a la fecha del prepago.
                 * MSG_04 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 30 días hábiles a la fecha del prepago.
                 * MSG_05 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 5 días hábiles a la fecha del prepago.
                 * MSG_06 Es necesario modificar la comisión tipo "Incumplimiento de prepago".
                 */
            if (null != validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea())) {
                isComentario = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                logger.log(ADFLogger.WARNING, "Se agrega comentario correctamente :" + isComentario);
            } else {
                logger.log(ADFLogger.WARNING, "El valor de retorno para validar el comentario es nulo.");
            }
            try {
                switch (prepagoBean.getOpSalidaTarea()) {
                case "aceptarPrepago":
                    logger.log(ADFLogger.WARNING, "aceptarPrepago");
                    //Metodo para validar la fecha de calculo definitivo, se atiende FNXII-4931
                    logger.warning("IdTcaTipoResolucion :" + prepagoBean.getIdTcaTipoResolucion());
                    if (prepagoBean.getIdTcaTipoResolucion().compareTo(FenixConstants.OTRAS_CONDICIONES) == 0) {
                        isValidFinalizarTarea = Boolean.TRUE;
                    } else {
                        isFechaValida = validarFechaCalculoDefinitivo();
                        if (!isFechaValida) {
                            bundleKeyErrorList.add("MSG_FECHA_CALCULO_DEFINITIVO");
                        }
                        isValidFinalizarTarea = (isFechaValida) ? Boolean.TRUE : Boolean.FALSE;
                    }
                    prepagoBean.setMensajeFinalizar(getStringFromBundle("ACEPTAR_PREPAGO_GESTIONAR_PREPAGO_MSG"));
                    break;
                case "prepagoIncumplido":
                    logger.log(ADFLogger.WARNING, "prepagoIncumplido");
                    isComision = validarComisionPrepago();
                    if (!isComision) {
                        logger.log(ADFLogger.WARNING, "Valor de la comision. :" + isComision);
                        bundleKeyErrorList.add("MSG06_GESTIONAR_PREPAGO_CLIENTE");
                    }
                    if (!isComentario) {
                        bundleKeyErrorList.add("MSG01_GESTIONAR_PREPAGO_CLIENTE");
                    }
                    prepagoBean.setMensajeFinalizar(getStringFromBundle("DESESTIMAR_PREPAGO_GESTIONAR_PREPAGO_MSG"));
                    isValidFinalizarTarea = (isComentario && isComision) ? Boolean.TRUE : Boolean.FALSE;

                    break;
                case "desestimarSolicitud":
                    logger.log(ADFLogger.WARNING, "desestimarSolicitud");
                    if (!isComentario) {
                        logger.log(ADFLogger.WARNING, "El comentario no se ha agregado" + isComentario);
                        bundleKeyErrorList.add("MSG01_GESTIONAR_PREPAGO_CLIENTE");
                    } else {
                        logger.log(ADFLogger.WARNING, "Se agrego comentario correctamente." + isComentario);
                        prepagoBean.setMensajeFinalizar(getStringFromBundle("DESESTIMAR_GESTIONAR_PREPAGO_MSG"));
                        isValidFinalizarTarea = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
                    }
                    break;
                }
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR,
                           "Error  en acptar Finalizar PA12_GESTIONAR_PREPAGO_CLIENTE." + e.getClass() + "." +
                           e.getMessage());
            }

            break;
        case FenixConstants.PA12_REVISAR_COMISIONES:
            logger.fine("PA12_REVISAR_COMISIONES");
            logger.warning("Revisar comisiones");

            /* EX02 Identifica que el campo "Validar" se encuentra inactivo. MSG_03.
                 * MSG_02 La comisión debe ser validada para poder finalizar la tarea.
                 * VA_03 Todas las comisiones que sean validadas tendrán que ser reflejadas en Flexcube.
                 * VA_04 Se debe iniciar el proceso de gestión de cobro de comisiones de la comisión validada.
                 */

            isValidar = prepagoBean.getIsValidaComision();
            if (isValidar) {
                condicion1 = Boolean.FALSE;
                bundleKeyErrorList.add("MSG02_REVISAR_COMISIONES");
            } else {
                condicion1 = Boolean.TRUE;
            }
            isValidFinalizarTarea = (condicion1) ? Boolean.TRUE : Boolean.FALSE;

            break;
        case FenixConstants.PA12_CONFIRMAR_APLICACION_PREPAGO:
            logger.fine("PA12_CONFIRMAR_APLICACION_PREPAGO");
            logger.warning("Confirmar aplicacion de prepago");

            /* RN_02 Para notificar la aplicación de prepago será necesario adjuntar el documento tipo "Recibo de pago anticipado".
                 * EX02 Identifica que no se ha adjuntado el documento tipo recibo de pago anticipado.
                 * MSG_02 Para finalizar la tarea es necesario adjuntar el documento tipo "Recibo de pago anticipado".
                 */

            /*isDocumento = validateDocumento(Long.valueOf(prepagoBean.getIdOperacion()),
                                                     FenixConstants.DOCUMENTO_RECIBO_PAGO_ANTICIPADO);*/

            isDocumento =
                validarDocumentoPorNumeroSerieDocumento(Long.valueOf(prepagoBean.getIdOperacion()),
                                                        FenixConstants.DOCUMENTO_RECIBO_PAGO_ANTICIPADO,
                                                        prepagoBean.getNumeroSerieDocumento());

            if (!isDocumento) {
                bundleKeyErrorList.add("MSG02_CONFIRMAR_APLICACION_PREPAGO");
            }

            isValidFinalizarTarea = (isDocumento) ? Boolean.TRUE : Boolean.FALSE;

            break;
        case FenixConstants.PA12_GESTIONAR_COBERTURA:
            logger.fine("PA12_GESTIONAR_COBERTURA");
            logger.warning("Gestionar cobertura");
            map = new HashMap<String, Object>();
            existeDiferencia = Boolean.FALSE;
            /* RN_01 Para finalizar la tarea cuando existan diferencias en las coberturas será necesario indicar el monto real y las observaciones de las diferencias en la sección de comentarios.
                 * EX01 Identifica que no se ha agregado un comentario.
                 * EX02 Identifica que no se ha capturado la información requerida.
                 * MSG_01 Es necesario agregar al menos un comentario.
                 * MSG_02 Favor de completar en su totalidad la información requerida.
                 */

            map = (Map) validaCamposFinalizar();
            existeDiferencia = (Boolean) map.get("existeDiferencia");
            condicion2 = (Boolean) map.get("result");

            if (!condicion2) {
                bundleKeyErrorList.add("MSG02_GESTIONAR_COBERTURA");
            }

            if (existeDiferencia) {
                condicion1 = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                if (!condicion1) {
                    bundleKeyErrorList.add("MSG01_GESTIONAR_COBERTURA");
                }
                isValidFinalizarTarea = condicion1 && condicion2;
            } else {
                logger.log(ADFLogger.WARNING, "No existe diferencia en coberturas.");
                isValidFinalizarTarea = condicion2;
            }

            break;
        case FenixConstants.PA12_GESTIONAR_DIFERENCIAS:
        case FenixConstants.PA12_GESTIONAR_DIFERENCIAS_148:
            logger.fine("PA12_GESTIONAR_DIFERENCIAS");
            logger.warning("Gestionar diferencias");
            map = new HashMap<String, Object>();
            existeDiferencia = Boolean.FALSE;
            /* RN_01 Para finalizar la tarea será necesario ingresar las acciones para atender las diferencias en caso que aplique en la sección de comentarios.
                 * EX01 Identifica que no se ha agregado un comentario.
                 * MSG_01 Es necesario agregar al menos un comentario.
                 */

            map = (Map) validaCamposFinalizar();
            existeDiferencia = (Boolean) map.get("existeDiferencia");

            if (existeDiferencia) {
                condicion1 = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
                if (!condicion1) {
                    bundleKeyErrorList.add("MSG01_GESTIONAR_COBERTURA");
                }
                isValidFinalizarTarea = condicion1;
            } else {
                logger.log(ADFLogger.WARNING, "No existe diferencia en coberturas.");
                isValidFinalizarTarea = Boolean.TRUE;
            }

            break;
        default:
            break;
        }

        logger.warning("Finaliza e invoca mensaje de confirmacion: " + isValidFinalizarTarea);

        if (!isValidFinalizarTarea) {
            logger.warning("Entra imprimir bundle Error Principal");
            logger.warning("Cantidad de Mensajes de Error: " + bundleKeyErrorList.size());
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }else {
                JSFUtils.addFacesErrorMessage("Error inesperado");
            }
        } else {
            finalizarTareaPopup();
        }

        return null;
    }
    
    public String invocarMasInformacion() {
        logger.warning("Se validan las condiciones para mostrar mensaje de confirmacion al solicitar Mas informacion");
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");

        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean isValidMasInformacion = Boolean.FALSE;
        Boolean isComentario = Boolean.FALSE;

        int codigoTarea = getCodigoTarea();

        switch (codigoTarea) {
            case FenixConstants.PA12_INGRESAR_SOLICITUD_PREPAGO:
            case FenixConstants.PA12_INGRESAR_SOLICITUD_PREPAGO_145:
                logger.fine("PA12_INGRESAR_SOLICITUD_PREPAGO");
                logger.warning("Ingresar solicitud de prepago");
            
                /* RN_12 Se requiere capturar al menos un comentario para solicitar Desestimar la solicitud de prepago.
                 * EX10 Identifica que no se ha agregado un comentario.
                 * MSG_10 Es necesario agregar al menos un comentario para desestimar la solicitud de prepago.
                 */
                
                isValidMasInformacion = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());

                logger.warning("isValidMasInformacion" + isValidMasInformacion);

                if (!isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG10_INGRESAR_SOLICITUD_PREPAGO");
                } else {
                    isValidMasInformacion = Boolean.TRUE;
                }
            
                break;
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_COPRES:
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_DAECI:
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_MDC:
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI:
                if (getCodigoTarea().compareTo(FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_COPRES) == 0) {
                    logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_COPRES");
                    logger.warning("Determinar cargos de prepago COPRES");
                }
                
                if (getCodigoTarea().compareTo(FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_DAECI) == 0) {
                    logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_DAECI");
                    logger.warning("Determinar cargos de prepago DAECI");
                }
                
                if (getCodigoTarea().compareTo(FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_MDC) == 0) {
                    logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_MDC");
                    logger.warning("Determinar cargos de prepago MDC");
                }
                
                if (getCodigoTarea().compareTo(FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI) == 0) {
                    logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI");
                    logger.warning("Determinar cargos de prepago SEPRI");
                }
                
                /* RN_01 Se requiere capturar al menos un comentario para solicitar Desestimar la solicitud de prepago.
                 * EX01 Identifica que no se ha agregado un comentario.
                 * MSG_01 Es necesario agregar al menos un comentario para solicitar más información.
                 */
            
                isValidMasInformacion = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());

                logger.warning("isValidMasInformacion" + isValidMasInformacion);

                if (!isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG01_DETERMINAR_CARGOS_PREPAGO");
                } else {
                    isValidMasInformacion = Boolean.TRUE;
                }
            
                break;
            case FenixConstants.PA12_VALIDAR_PENALIDAD_PREPAGO_COFI:
                logger.fine("PA12_VALIDAR_PENALIDAD_PREPAGO_COFI");
                logger.warning("Validar penalidad de prepago COFI");
                
                /* RN_01 Para solicitar más información al Responsable de Prepago es necesario capturar al menos un comentario.
                 * EX01 Identifica que no se ha agregado un comentario.
                 * MSG_01 Es necesario agregar al menos un comentario para solicitar más información.
                 */
            
                isValidMasInformacion = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());

                logger.warning("isValidMasInformacion" + isValidMasInformacion);

                if (!isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG01_VALIDAR_PENALIDAD_PREPAGO_COFI");
                } else {
                    isValidMasInformacion = Boolean.TRUE;
                }
            
                break;
            case FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE:
            case FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE_147:
                logger.fine("PA12_GESTIONAR_PREPAGO_CLIENTE");
                logger.warning("Gestionar prepago con el cliente");
    
            //Boolean isComentario = Boolean.FALSE;
            Boolean isFechaPrepago = Boolean.FALSE;
            
            isComentario = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());
            logger.warning("isValidMasInformacion" + isComentario);
            try{
                logger.log(ADFLogger.WARNING, "INTO Switch PA12_GESTIONAR_PREPAGO_CLIENTE" + prepagoBean.getOpSalidaTarea());

                switch(prepagoBean.getOpSalidaTarea()){
                
                case "actualizarPenalidad":
                        logger.log(ADFLogger.WARNING, "Entra en actualizar Penalidad.");
                        if (!isComentario) {
                            bundleKeyErrorList.add("MSG01_GESTIONAR_PREPAGO_CLIENTE");
                        }
                            
                    prepagoBean.setMensajeMasInformacion(getStringFromBundle("ACTUALIZAR_PENALIDAD_GESTIONAR_PREPAGO_MSG"));
                    isValidMasInformacion = (isComentario) ? Boolean.TRUE : Boolean.FALSE;       
                        
                break;
                case "actualizarPrepago":
                            logger.log(ADFLogger.WARNING, "Entra en actualizar prepago.");
                            logger.warning("isValidMasInformacion" + isValidMasInformacion);
                                
                                isFechaPrepago = validaFinalizaFechaPrepago();
                                logger.warning("condicion2: " + isFechaPrepago);
                                if (!isFechaPrepago) {
                                    bundleKeyErrorList.add("MSG07_GESTIONAR_PREPAGO_CLIENTE");
                                }
                                if (!isComentario) {
                                    bundleKeyErrorList.add("MSG01_GESTIONAR_PREPAGO_CLIENTE");
                                }
                    
                    if (isFechaPrepago) {
                        BindingContainer bindings = getBindings();
                        OperationBinding operationBindingValidar = bindings.getOperationBinding("actualizarFechaFormularioPrepago");
                        operationBindingValidar.execute();
                    }
                    prepagoBean.setMensajeMasInformacion(getStringFromBundle("ACTUALIZAR_PREPAGO_GESTIONAR_PREPAGO_MSG"));
                    isValidMasInformacion = (isComentario && isFechaPrepago) ? Boolean.TRUE : Boolean.FALSE;
                    break;
                default:
                    break;
                }
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error en PA12_GESTIONAR_PREPAGO_CLIENTE.");
            }
                break;
            case FenixConstants.PA12_REVISAR_COMISIONES:
                logger.fine("PA12_REVISAR_COMISIONES");
                logger.warning("Revisar comisiones");
                
                /* RN_01 Para solicitar la salida "Requiere ajustes" es necesario capturar al menos un comentario.
                 * EX01 Identifica que no se ha agregado un comentario.
                 * MSG_01 Es necesario agregar al menos un comentario para requerir ajustes al Responsable de Operación.
                 */
                   
                isValidMasInformacion = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());

                logger.warning("isValidMasInformacion" + isValidMasInformacion);

                if (!isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG01_REVISAR_COMISIONES");
                } else {
                    isValidMasInformacion = Boolean.TRUE;
                }
            
                break;
            case FenixConstants.PA12_CONFIRMAR_APLICACION_PREPAGO:
                logger.fine("PA12_CONFIRMAR_APLICACION_PREPAGO");
                logger.warning("Confirmar aplicacion de prepago");
                
                /* RN_01 Para solicitar la salida "Notificar no prepago" es necesario capturar al menos un comentario.
                 * EX01 Identifica que no se ha agregado un comentario.
                 * MSG_01 Es necesario agregar al menos un comentario.
                 */
                
                isComentario = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());

                logger.warning("isValidMasInformacion" + isValidMasInformacion);

                if (!isComentario) {
                    bundleKeyErrorList.add("MSG01_CONFIRMAR_APLICACION_PREPAGO");
                } else {
                    isValidMasInformacion = (isComentario) ? Boolean.TRUE : Boolean.FALSE;
                }
            
                break;
            //AGREGA SALIDA MAS INFORMACION (DESESTIMAR PREPAGO) PARA REAIZAR AJUSTES AL PREPAGO
            case FenixConstants.PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO:
            case FenixConstants.PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO_146:
                logger.warning("Realizar ajustes de la solicitud de prepago");
                //validar que exista un comentario
                isValidMasInformacion = validateComentario(getIdOperacion(), getCodigoTarea().toString(), getInstanciaTarea());

                logger.warning("isValidMasInformacion" + isValidMasInformacion);
                //si es false . mostrar mensaje de error
                if (!isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG10_INGRESAR_SOLICITUD_PREPAGO");
                } else {
                    //continuar flujo al popup
                    isValidMasInformacion = Boolean.TRUE;
                }
                break;
            default:
                break;
        }

        logger.warning("Mas informacion fue correcto para mostrar mensaje de confirmacion: " + isValidMasInformacion);

        if (!isValidMasInformacion) {
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
        } else {
            masInformacionPopup();
        }

        return null;
    }
    
    public String aceptarFinalizarTarea() {
        logger.warning("Inside aceptarFinalizarTarea");
        popupFinalizarTarea.hide();
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        cargarDocumentosOnBase();
        Boolean isValidFinalizarTarea = Boolean.FALSE;
        List<String> bundleKeyErrorList = new ArrayList<String>();
        Boolean cargoMDC = Boolean.TRUE;

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
            case FenixConstants.PA12_INGRESAR_SOLICITUD_PREPAGO:
            case FenixConstants.PA12_INGRESAR_SOLICITUD_PREPAGO_145:
                logger.fine("PA12_INGRESAR_SOLICITUD_PREPAGO");
                logger.warning("Ingresar solicitud de prepago");
                Long idPrepago = prepagoBean.getIdPrepago();
                // set value "idPrepago" in payload
                actualizarPayloadElement("idPrepago", idPrepago);
                logger.warning("idPrepago : " +idPrepago);
                isValidFinalizarTarea = Boolean.TRUE;
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
        
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_COPRES:
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_COPRES");
                logger.warning("Determinar cargos de prepago COPRES");
                isValidFinalizarTarea = guardarDatosFormulario();
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                
                break;
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_DAECI:
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_DAECI");
                logger.warning("Determinar cargos de prepago DAECI");
                
                isValidFinalizarTarea = guardarDatosFormulario();
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_MDC:
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_MDC");
                logger.warning("Determinar cargos de prepago MDC");
                
                isValidFinalizarTarea = guardarDatosFormulario();

                if (validarMonto()) {
                    actualizarPayloadElement("cargoMDC", cargoMDC); 
                    logger.warning("actualiza cargoMDC a TRUE.");
                }
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                } 
                break;
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI:
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI");
                logger.warning("Determinar cargos de prepago SEPRI");
                
                isValidFinalizarTarea = guardarDatosFormulario();
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_VALIDAR_PENALIDAD_PREPAGO_COFI:
                logger.fine("PA12_VALIDAR_PENALIDAD_PREPAGO_COFI");
                logger.warning("Validar penalidad de prepago COFI");
                
                this.insertarFormulariosCalculoInteres();
                this.insertarFormulariosDetallePenalidad();
                
                isValidFinalizarTarea = guardarDatosFormulario();
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO:
            case FenixConstants.PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO_146:
                logger.fine("PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO");
                logger.warning("Realizar ajustes a solicitud de prepago");
                Boolean realizarCambiosPrepago = validarAjustesPrepago(prepagoBean.getIdPrepago());
                Boolean eliminoCorrectamente = Boolean.FALSE;
                Boolean actualizoCorrecto = Boolean.FALSE;
                Long idObservacion = null;
                String observacion = null;
                //recuperar idObservacion y observacion
                try{
                    idObservacion = (Long) ADFUtils.getBoundAttributeValue("IdObservacion");
                }catch(Exception e){
                    logger.severe("Error al recuperar idObservacion "+e);
                }
                try{
                    observacion = (String) ADFUtils.getBoundAttributeValue("Observacion");
                }catch(Exception e){
                    logger.severe("Error al recuperar observacion "+e);
                }
            
                //invocar el metodo para actualiazr la observacion del prepago
                actualizarObservacionPrepago(idObservacion,observacion);
                
                if(realizarCambiosPrepago){
                    //invocar metodo para limpiar tablas hijos y volver a insertar en TRE_CONTRATO_PREPAGO
                    eliminoCorrectamente = eliminarDatosPrepago(prepagoBean.getIdPrepago());
                    if(eliminoCorrectamente){
                        //invocar el metodo para actualizar el encabezado del prepago
                        actualizoCorrecto = actualizarPrepago(prepagoBean.getIdPrepago());                        
                        
                        if(actualizoCorrecto){
                            //insertar TRE_CONTRATO_DESEMBOLSO
                            insertarContratoDesembolso(prepagoBean.getIdPrepago());
                            //asignar valor "True" a "Mas informacion"
                            asignarBanderasBpmRealizarAjustesSolicitudPrepago();
                            
                            actualizarPayloadElement("idPrepago", prepagoBean.getIdPrepago());
                            //Permitir finalizar Tarea
                            isValidFinalizarTarea = Boolean.TRUE;
                        }else{
                            logger.log(ADFLogger.ERROR,"No es posible finalizar la tarea, el prepago no se actualizo.");
                            isValidFinalizarTarea = Boolean.FALSE;
                        }
                    }else{
                        logger.log(ADFLogger.ERROR,"No es posible finalizar la tarea, la información no se elimino correctamente.");
                        isValidFinalizarTarea = Boolean.FALSE;
                    }
                    
                }else{
                    logger.log(ADFLogger.WARNING,"No se realiazo ningun cambio en el prepago.");
                    isValidFinalizarTarea = Boolean.TRUE;
                }
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_REALIZAR_AJUSTES_A_SOLICITUD_DE_PREPAGO_ERROR");
                }
                break;
            case FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE:
            case FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE_147:
                logger.fine("PA12_GESTIONAR_PREPAGO_CLIENTE");
                logger.warning("Gestionar prepago con el cliente");
            Boolean isPrepagoIncumplido = Boolean.FALSE;
            Boolean isPrepagoRecibido = Boolean.FALSE;
            AttributeBinding attributeBindingIncumplido = null;
            AttributeBinding attributeBindingRecibido = null;
            try{
                switch(prepagoBean.getOpSalidaTarea()){
                case "aceptarPrepago":
                    isPrepagoIncumplido = Boolean.FALSE;
                    isPrepagoRecibido = Boolean.TRUE;
                    attributeBindingIncumplido = ADFUtils.findControlBinding("prepagoIncumplido");
                    actualizarPayloadElement("prepagoIncumplido", isPrepagoIncumplido);
                    attributeBindingRecibido = ADFUtils.findControlBinding("prepagoRecibido");
                    actualizarPayloadElement("prepagoRecibido", isPrepagoRecibido);
                    
                break;
                case "prepagoIncumplido":
                    isPrepagoIncumplido = Boolean.TRUE;
                    isPrepagoRecibido = Boolean.TRUE;
                    attributeBindingIncumplido = ADFUtils.findControlBinding("prepagoIncumplido");
                    actualizarPayloadElement("prepagoIncumplido", isPrepagoIncumplido);
                    attributeBindingRecibido = ADFUtils.findControlBinding("prepagoRecibido");
                    actualizarPayloadElement("prepagoRecibido", isPrepagoRecibido);
                    logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingIncumplido.getInputValue());
                    logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingRecibido.getInputValue());
                break;
                case "desestimarSolicitud":
                    isPrepagoIncumplido = Boolean.FALSE;
                    isPrepagoRecibido = Boolean.FALSE;
                    prepagoBean.setMensajeFinalizar(getStringFromBundle("DESESTIMAR_GESTIONAR_PREPAGO_MSG"));
                    attributeBindingIncumplido = ADFUtils.findControlBinding("prepagoIncumplido");
                    actualizarPayloadElement("prepagoIncumplido", isPrepagoIncumplido);
                    attributeBindingRecibido = ADFUtils.findControlBinding("prepagoRecibido");
                    actualizarPayloadElement("prepagoRecibido", isPrepagoRecibido);
                    logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingIncumplido.getInputValue());
                    logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingRecibido.getInputValue());
                break;
                }
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error  en acptar Finalizar PA12_GESTIONAR_PREPAGO_CLIENTE." + e.getClass() +"." + e.getMessage());
            }
                isValidFinalizarTarea = Boolean.TRUE;
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_REVISAR_COMISIONES:
                logger.fine("PA12_REVISAR_COMISIONES");
                logger.warning("Revisar comisiones");
                Long idComision = null;
                String descripcion = null;
                BindingContainer bindings = ADFUtils.getBindingContainer();
                Boolean isComisionValidada = Boolean.FALSE;
                logger.log(ADFLogger.WARNING, "Valor Cmision id" + prepagoBean.getIdComision());
                logger.log(ADFLogger.WARNING, "Valor descripcion :" + prepagoBean.getDescripcionComision());
            try{
                idComision = prepagoBean.getIdComision();
                descripcion = prepagoBean.getDescripcionComision();
                
                OperationBinding operationBinding = bindings.getOperationBinding("crearComision");
                operationBinding.getParamsMap().put("idComision", idComision);
                operationBinding.getParamsMap().put("descripcion", descripcion);
                operationBinding.execute();
                isComisionValidada = (Boolean)operationBinding.getResult();
                isValidFinalizarTarea = (isComisionValidada) ? Boolean.TRUE : Boolean.FALSE;
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
            }catch(Exception e){
                logger.log(ADFLogger.WARNING, "Error en PA12_REVISAR_COMISIONES." + e.getClass() + "." + e.getMessage());
            }
                break;
                case FenixConstants.PA12_CONFIRMAR_APLICACION_PREPAGO:
                logger.fine("PA12_CONFIRMAR_APLICACION_PREPAGO");
                logger.warning("Confirmar aplicacion de prepago");
            
                isValidFinalizarTarea = Boolean.TRUE;
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_GESTIONAR_COBERTURA:
                logger.fine("PA12_GESTIONAR_COBERTURA");
                logger.warning("Gestionar cobertura");
            
                isValidFinalizarTarea = insertarGestionarCoberturas();
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_GESTIONAR_DIFERENCIAS:
            case FenixConstants.PA12_GESTIONAR_DIFERENCIAS_148:
                logger.fine("PA12_GESTIONAR_DIFERENCIAS");
                logger.warning("Gestionar diferencias");
                
                
                isValidFinalizarTarea = Boolean.TRUE;
                
                if (null == isValidFinalizarTarea || !isValidFinalizarTarea) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            default:
                break;
        }

        if (isValidFinalizarTarea) {
            logger.log(ADFLogger.WARNING, "INTO aceptar Finalizar. el valor es. :" + isValidFinalizarTarea);
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            logger.log(ADFLogger.ERROR, "aceptarFinalizarTarea(): " + isValidFinalizarTarea);
            if (bundleKeyErrorList.size() > 0) {
                for (String bundleKey : bundleKeyErrorList)
                    JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle(bundleKey, BUNDLE));
            }
            logger.warning("Se cancela la confirmacion de Finalizar tarea");
            return null;
        }
    }
    
    public String aceptarMasInformacion() {
        logger.warning("Inside aceptarMasInformacion");
        popupFinalizarTarea.hide();
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");

        Boolean isValidMasInformacion = Boolean.FALSE;
        List<String> bundleKeyErrorList = new ArrayList<String>();
        
        //Variables para setear a los atributos del payload
        AttributeBinding attributeBindingIncumplido = null;
        AttributeBinding attributeBindingRecibido = null;
        Boolean isPrepagoIncumplido = Boolean.TRUE;
        Boolean isPrepagoRecibido = Boolean.FALSE;

        int codigoTarea = getCodigoTarea();
        switch (codigoTarea) {
            case FenixConstants.PA12_INGRESAR_SOLICITUD_PREPAGO:
            case FenixConstants.PA12_INGRESAR_SOLICITUD_PREPAGO_145:
                logger.fine("PA12_INGRESAR_SOLICITUD_PREPAGO");
                logger.warning("Ingresar solicitud de prepago");
            
                isValidMasInformacion = Boolean.TRUE;
        
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_COPRES:
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_COPRES");
                logger.warning("Determinar cargos de prepago COPRES");
            
                isValidMasInformacion = Boolean.TRUE;
                
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_DAECI:
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_DAECI");
                logger.warning("Determinar cargos de prepago DAECI");
            
                isValidMasInformacion = Boolean.TRUE;
                
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_MDC:
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_MDC");
                logger.warning("Determinar cargos de prepago MDC");
            
                isValidMasInformacion = Boolean.TRUE;
                
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI:
                logger.fine("PA12_DETERMINAR_CARGOS_PREPAGO_SEPRI");
                logger.warning("Determinar cargos de prepago SEPRI");
            
                isValidMasInformacion = Boolean.TRUE;
                
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_VALIDAR_PENALIDAD_PREPAGO_COFI:
                logger.fine("PA12_VALIDAR_PENALIDAD_PREPAGO_COFI");
                logger.warning("Validar penalidad de prepago COFI");
            
                isValidMasInformacion = Boolean.TRUE;
                
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE:
            case FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE_147:
                logger.fine("PA12_GESTIONAR_PREPAGO_CLIENTE");
                logger.warning("Gestionar prepago con el cliente");
            Boolean isRequiereMasInfoCOFI = Boolean.FALSE;
            Boolean isRequiereMasInfo = Boolean.FALSE;
            try{
            switch(prepagoBean.getOpSalidaTarea()){
                case "actualizarPenalidad":
                        logger.log(ADFLogger.WARNING, "Entra en actualizar prepago.");
                            isRequiereMasInfoCOFI = Boolean.TRUE;
                            AttributeBinding attributeBinding = ADFUtils.findControlBinding("requiereMasInfoCOFI");
                            logger.severe("Revisar Valor del payload en true antes de actualizar ." + attributeBinding.getInputValue());
                            actualizarPayloadElement("requiereMasInfoCOFI", isRequiereMasInfoCOFI);
                            logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBinding.getInputValue()); 
                
                    Boolean limpiarCampos=Boolean.FALSE;
                
                    AttributeBinding attributeBindingCOPRES2 = ADFUtils.findControlBinding("requiereMasInfoCOPRES");
                    AttributeBinding attributeBindingDAECI2 = ADFUtils.findControlBinding("requiereMasInfoDAECI");
                    AttributeBinding attributeBindingMDC2 = ADFUtils.findControlBinding("requiereMasInfoMDC");
                    AttributeBinding attributeBindingSEPRI2 = ADFUtils.findControlBinding("requiereMasInfoSEPRI");
                
                    actualizarPayloadElement("requiereMasInfoCOPRES", limpiarCampos);
                    actualizarPayloadElement("requiereMasInfoDAECI", limpiarCampos);
                    actualizarPayloadElement("requiereMasInfoMDC", limpiarCampos);
                    actualizarPayloadElement("requiereMasInfoSEPRI", limpiarCampos);
                    
                    logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingCOPRES2.getInputValue());
                    logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingDAECI2.getInputValue());
                    logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingMDC2.getInputValue());
                    logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingSEPRI2.getInputValue());
                        
                break;
                case "actualizarPrepago":
                            logger.log(ADFLogger.WARNING, "Entra en actualizar prepago.");
                            logger.warning("isValidMasInformacion" + isValidMasInformacion);
                                 
                                isRequiereMasInfo = Boolean.TRUE;
                                AttributeBinding attributeBindingCOPRES = ADFUtils.findControlBinding("requiereMasInfoCOPRES");
                                AttributeBinding attributeBindingDAECI = ADFUtils.findControlBinding("requiereMasInfoDAECI");
                                AttributeBinding attributeBindingMDC = ADFUtils.findControlBinding("requiereMasInfoMDC");
                                AttributeBinding attributeBindingSEPRI = ADFUtils.findControlBinding("requiereMasInfoSEPRI");
                                AttributeBinding attributeBindingCOFI = ADFUtils.findControlBinding("requiereMasInfoCOFI");
                                logger.severe("Revisar Valor del payload en true antes de actualizar ." + attributeBindingCOPRES.getInputValue());
                                
                                actualizarPayloadElement("requiereMasInfoCOPRES", isRequiereMasInfo);
                                actualizarPayloadElement("requiereMasInfoDAECI", isRequiereMasInfo);
                                actualizarPayloadElement("requiereMasInfoMDC", isRequiereMasInfo);
                                actualizarPayloadElement("requiereMasInfoSEPRI", isRequiereMasInfo);
                                actualizarPayloadElement("requiereMasInfoCOFI", isRequiereMasInfo);
                               
                                logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingCOPRES.getInputValue());
                                logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingDAECI.getInputValue());
                                logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingMDC.getInputValue());
                                logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingSEPRI.getInputValue());
                                logger.severe("Revisar Valor del payload en true Despues de actualizar." + attributeBindingCOFI.getInputValue());
                    break;
                default:
                    break;
                }
                isValidMasInformacion = Boolean.TRUE;
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
            }
            }catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error en aceptar PA12_GESTIONAR_PREPAGO_CLIENTE." + e.getClass() + "." + e.getMessage());
            }
                break;
            case FenixConstants.PA12_REVISAR_COMISIONES:
                logger.fine("PA12_REVISAR_COMISIONES");
                logger.warning("Revisar comisiones");
            
                isPrepagoIncumplido = Boolean.TRUE;
                isPrepagoRecibido = Boolean.FALSE;
            
                attributeBindingIncumplido = ADFUtils.findControlBinding("prepagoIncumplido");
                actualizarPayloadElement("prepagoIncumplido", isPrepagoIncumplido);
                logger.log(ADFLogger.WARNING, "Valor de prepagoIncumplido :" + attributeBindingIncumplido.getInputValue());
                attributeBindingRecibido = ADFUtils.findControlBinding("prepagoRecibido");
                actualizarPayloadElement("prepagoRecibido", isPrepagoRecibido);
                logger.log(ADFLogger.WARNING, "Valor de prepagoIncumplido :" + attributeBindingRecibido.getInputValue());
                isValidMasInformacion = Boolean.TRUE;
                
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
                case FenixConstants.PA12_CONFIRMAR_APLICACION_PREPAGO:
                logger.fine("PA12_CONFIRMAR_APLICACION_PREPAGO");
                logger.warning("Confirmar aplicacion de prepago");
            isPrepagoIncumplido = Boolean.TRUE;
            isPrepagoRecibido = Boolean.FALSE;
            attributeBindingIncumplido = ADFUtils.findControlBinding("prepagoIncumplido");
            actualizarPayloadElement("prepagoIncumplido", isPrepagoIncumplido);
            logger.log(ADFLogger.WARNING, "Valor de prepagoIncumplido :" + attributeBindingIncumplido.getInputValue());
            attributeBindingRecibido = ADFUtils.findControlBinding("prepagoRecibido");
            actualizarPayloadElement("prepagoRecibido", isPrepagoRecibido);
            logger.log(ADFLogger.WARNING, "Valor de prepagoIncumplido :" + attributeBindingRecibido.getInputValue());
                isValidMasInformacion = Boolean.TRUE;
                
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            case FenixConstants.PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO:
            case FenixConstants.PA12_REALIZAR_AJUSTES_SOLICITUD_PREPAGO_146:
                logger.warning("Realizar ajuste a la solicitud de prepago");
            
                isValidMasInformacion = Boolean.TRUE;
            
                if (null == isValidMasInformacion || !isValidMasInformacion) {
                    bundleKeyErrorList.add("MSG_ACTUALIZAR_PREPAGO");
                }
                break;
            default:
                break;
        }

        if (isValidMasInformacion) {
            logger.log(ADFLogger.WARNING, "INTO aceptar mas Informacion. el valor es. :" + isValidMasInformacion);
            InvokeActionBean invokeActionBean = (InvokeActionBean) JSFUtils.getManagedBeanValue("invokeActionBean");
            return invokeActionBean.invokeOperation();
        } else {
            logger.log(ADFLogger.ERROR, "aceptarMasInformacion(): " + isValidMasInformacion);
            return null;
        }
    }
    
    private Integer getCodigoTarea() {
        logger.warning("inside getCodigoTarea");

        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");

        if (null != prepagoBean.getCodigoTarea() && prepagoBean.getCodigoTarea().trim().length() > 0) {
            return Integer.parseInt(prepagoBean.getCodigoTarea());
        }
        return 0;
    }

    private Long getIdOperacion() {
        logger.warning("inside getIdOperacion");

        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");

        if (null != prepagoBean.getIdOperacion() && prepagoBean.getIdOperacion().trim().length() > 0) {
            return Long.parseLong(prepagoBean.getIdOperacion());
        }

        return 0L;
    }
    
    public Boolean guardarDatosFormulario(){
        logger.log(ADFLogger.WARNING, "INTO guardarDatosFormulario.");
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        Boolean validar = Boolean.FALSE;
        Boolean isAceptarFinalizar = Boolean.FALSE;
        Long idPrepago = null;
        Integer idTcaRolBpm = null;
        BindingContainer bindings = getBindings();
        try{
            //logger.log(ADFLogger.WARNING, "Valor Id Prepago PageFlow." + prepagoBean.getIdPrepago());
            logger.log(ADFLogger.WARNING, "Valor Id Rollll PageFlow." + prepagoBean.getCodigoRol());
            
            // Recuperamos el id de prepago
            idPrepago = Long.valueOf(prepagoBean.getIdPrepago());
            
            // Validamos si el id de prepago viene nulo
            if(idPrepago == null) {
                logger.log(ADFLogger.WARNING, "*** Objeto pageFlowScope.pIdPrepago Nulo.");
            }
            if(prepagoBean.getCodigoRol() != null){
                idTcaRolBpm = Integer.parseInt(prepagoBean.getCodigoRol());
            }else{
                logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdTcaRolBpm Nulo.");
            }
            logger.log(ADFLogger.WARNING, "Valor id Prepago." + idPrepago);
            logger.log(ADFLogger.WARNING, "Valir id Rol BPM." + idTcaRolBpm);
        OperationBinding operationBindingValidar = bindings.getOperationBinding("validarObservacionCargoPrepago");
            operationBindingValidar.getParamsMap().put("idPrepago", idPrepago);
            operationBindingValidar.getParamsMap().put("idTcaRolBpm", idTcaRolBpm);
        operationBindingValidar.execute();
        if(null != operationBindingValidar.getResult()){
        validar = (Boolean)operationBindingValidar.getResult();
        }else{
            logger.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
        }
            
        logger.warning("validar: " + validar);
        
        if(validar){
        OperationBinding operationBindingActualizar = bindings.getOperationBinding("actualizarFormularioCargoPrepago");
        operationBindingActualizar.execute();
            isAceptarFinalizar = Boolean.TRUE;
        }else{
            // Se invoca la ultima opreacion que realizara el commit generarl de la tarea
            OperationBinding operationBinding = bindings.getOperationBinding("insertarFormularioCargoPrepago");
            operationBinding.execute();
            
            // Se valida el resultado del commit general
            if(null != operationBinding.getResult()){
                validar = (Boolean)operationBinding.getResult();
                if (null != validar && validar.equals(Boolean.TRUE)) {
                    isAceptarFinalizar = Boolean.TRUE;
                }
            }else{
                logger.log(ADFLogger.WARNING, "Valor insertarFormularioCargoPrepago.getResult() es Nulo.");
            }
            
            // Se muestran los errores encontrados
            if(!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
        }
        }catch(Exception e){
            isAceptarFinalizar = Boolean.FALSE;
            logger.log(ADFLogger.ERROR, "Error en guardarDatosFormulario." + e.getClass() + "." + e.getMessage());
        }
        return isAceptarFinalizar;
    }

    public String cancelarFinalizarTarea() {
        logger.warning("Cancela confirmacion de Finalizar tarea");
        popupFinalizarTarea.hide();
        
        return null;
    }

    public String cancelarMasInformacion() {
        logger.warning("Cancela confirmacion de Mas informacion");
        popupMasInformacion.hide();
        
        return null;
    }
    
    public void finalizarTareaPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupFinalizarTarea.show(hints);
    }
    
    public void masInformacionPopup() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupMasInformacion.show(hints);
    }
    
    public void setPopupFinalizarTarea(RichPopup popupFinalizarTarea) {
        this.popupFinalizarTarea = popupFinalizarTarea;
    }

    public RichPopup getPopupFinalizarTarea() {
        return popupFinalizarTarea;
    }

    public void setPopupMasInformacion(RichPopup popupMasInformacion) {
        this.popupMasInformacion = popupMasInformacion;
    }

    public RichPopup getPopupMasInformacion() {
        return popupMasInformacion;
    }
    
    public void insertarFormulariosDetallePenalidad(){
        logger.log(ADFLogger.WARNING, "Entra en insertarFormulariosDetallePenalidad");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean resultado = null;
        try{
        operationBinding = bindings.getOperationBinding("insertarFormulariosDetallePenalidad");
        operationBinding.execute();
            logger.log(ADFLogger.WARNING, "Se ejecuta metodo insertarFormulariosDetallePenalidad correctamente.");
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error al insertar en Detalle de la penalidad." + e.getClass() + "." + e);
        }
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
    }
    
    public void insertarFormulariosCalculoInteres() {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean resultado = null;
        try {
            operationBinding = bindings.getOperationBinding("insertarFormulariosCalculoInteres");
            operationBinding.execute();
            logger.log(ADFLogger.WARNING, "Se ejecuta metodo insertarFormulariosCalculoInteres correctamente.");
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING,
                       "Error al ejecutar metodo para insertar Calculo de Intereses" + e.getClass() + "." + e);
        }
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
    }
    
    public Boolean validarMonto(){
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean resultado = Boolean.FALSE;
        operationBinding = bindings.getOperationBinding("validaMonto");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            resultado = (Boolean)operationBinding.getResult();
        }
        
        return resultado;
    }
    
    public Long insertarPrepago(){
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long resultado = null;
        operationBinding = bindings.getOperationBinding("insertarFormulariosPrepago");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            resultado = (Long)operationBinding.getResult();
        }
        
        return resultado;
    }
    
    public void dateAbleWeekend(Set days) {
        uiFechaPrepago.setDisabledDaysOfWeek(days);
        uiFechaSolicitud.setDisabledDaysOfWeek(days);
    }
    
    public void disabledWeekend() {
        logger.warning("Inside disabledWeekend.");
        
        uiFechaPrepago.setDisabledDaysOfWeek(WEEKEND);
    }
    
    public void enabledDays() {
        logger.warning("Inside enabledDays.");
        
        uiFechaPrepago.setDisabledDaysOfWeek(EMPTY);
    }
    
    public void tipoResolucionValueChangeListener(ValueChangeEvent vce){
        logger.log(ADFLogger.WARNING, "Into tipoResolucionValueChangeListener.");
        logger.warning("fechaPrepago disabledDaysOfWeek: " + uiFechaPrepago.getDisabledDaysOfWeek());
        logger.warning("fechaPrepago disabledDaysOfWeek: " + uiFechaSolicitud.getDisabledDaysOfWeek());
        
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        //Se comenta  el codigo para limpiar los valores de "fechaSolicitud" y "FechaPrepago"
        //ya que no se cumple con el flujo declarado en CU 
        //BCIE-F3-CU-PA12-Prepago-Ingresar solicitud de prepago
        //reinicia valores de Fecha de solicitud y Fecha de prepago

        /*AttributeBinding fechaSolicitud;
        fechaSolicitud = ADFUtils.findControlBinding("FechaSolicitud");
        if(fechaSolicitud != null) {
            fechaSolicitud.setInputValue(null);
        }
        */
        
        seteaValoresNulos(RESOLUCION);
        
        Timestamp fechaRenovacion = null;
        Timestamp fechaPrepago = null;
        
        if(null != ADFUtils.findControlBinding("FechaPrepago")) {
            fechaPrepago = (Timestamp)JSFUtils.resolveExpression("#{bindings.FechaPrepago.inputValue}");
            //fechaPrepago.setInputValue(null);
        }else{
            logger.warning("La fecha de prepago es nula.");
        }
        if(null != JSFUtils.resolveExpression("#{bindings.FechaRenovacion.inputValue}")){
            fechaRenovacion = (Timestamp)JSFUtils.resolveExpression("#{bindings.FechaRenovacion.inputValue}");
        }else{
            logger.warning("La fecha de renovacion es nula.");
        }
        //actualiza el valor seleccionado
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        Long idOperacion = (null != prepagoBean.getCodigoOperacion()) ? 
            prepagoBean.getCodigoOperacion().longValue() : null;
        Integer idTcaTipoResolucion =(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}")) ? 
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}") : null;
        ADFUtils.setBoundAttributeValue("IdTcaTipoMoneda", null);
        Integer idTcaTipoMoneda =(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}")) ? 
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}") : null;
        //Si el check del pago total se encuentra seleccionado, se cambia valor para deseleccionarlo y habilitarlo.
        prepagoBean.setEsPagoTotalHeader(Boolean.FALSE);
        prepagoBean.setEsLecturaPagoTotal(Boolean.TRUE);
        
        logger.warning("Value idOperacion is : "+idOperacion);
        logger.warning("Value idTcaTipoResolucion is : "+idTcaTipoResolucion);
        logger.warning("Value idTcaTipoMoneda is : "+idTcaTipoMoneda);
        logger.warning("Valor de la fecha de prepago." + fechaPrepago);
        logger.warning("Valor de la fecha de renovacion." + fechaRenovacion);
        
        if (idOperacion != null) {
            // Se comenta por la FNXII-6293
            /*if (null != idTcaTipoResolucion) {
                if (idTcaTipoResolucion.compareTo(PRE10_2008) == 0) {
                    fechaPrepago = null;
                } else {
                    logger.warning("Se utiliza el valor de la fecha prepago en pantalla.");
                }
            }*/
            logger.warning("Se invoca metodo que ejecuta el SP");
            prepagoBean.consultarContratosDesembolsos(idOperacion, idTcaTipoResolucion,
                                                      idTcaTipoMoneda, null, fechaRenovacion,
                                                      fechaPrepago);
            
//            logger.warning("Se invoca metodo que ejecuta el SP Tree");
//            prepagoBean.consultarContratosDesembolsosTree(idOperacion, idTcaTipoResolucion,
//                                                      idTcaTipoMoneda, null, fechaRenovacion,
//                                                      fechaPrepago);

                this.llenarCatalogoTipoDeMoneda();
        } else {
            JSFUtils.addFacesErrorMessage("No se puede ejecutar la consulta por idOperacion no puede ser null.");
        }
        
        Integer idTipoResolucion = (Integer) vce.getNewValue();
        
        if (idTcaTipoMoneda != null) {
            logger.warning("Actualmente existe una moneda seleccionada.");
        } else {
            if (idTipoResolucion != null) {
                if (idTipoResolucion.compareTo(PRE10_2008) == 0) {
                    disabledWeekend();
                } else {
                    enabledDays();
                }
            }
        }
        prepagoBean.setDisabledDaysValue(null);
    }
    
    public void precargarDiasInhabiles(){
        logger.entering(this.getClass().getName(), "precargarDiasInhabiles");
        PrepagoBean prepagoBean = null;
        Integer idTcaTipoResolucion = null;
        
        try{
            
            prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        
            try{
                
                idTcaTipoResolucion =(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}")) ? 
                            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}") : null;
                
                if(null != idTcaTipoResolucion){
                    
                    if ((idTcaTipoResolucion.compareTo(PRE10_2008) == 0 || idTcaTipoResolucion.compareTo(OTRAS_CONDICIONES) == 0)) {
                        
                        logger.warning("Deshabilita fines de semana en calendario.");
                        HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}");
                        prepagoBean.setDisabledDaysValue(holidaysBean);
                        diasInhabilesMoneda();
                        logger.warning("cantidad de dias inhabiles recuperada es: "+getDiasInhabilesMoneda().size());
                        
                    } else if ((idTcaTipoResolucion.compareTo(PRE28_2003) == 0)) {
                        
                        logger.warning("Habilita fines de semana en calendario.");
                        prepagoBean.setDisabledDaysValue(null);
                    }
                }
            
            }catch(Exception e){
                logger.severe("Error al recuperar idTcaTipoResolucion",e);
            }
        
        }catch(Exception e){
            logger.severe("Error al recuperar prepagoBean",e);
        }
        
        logger.exiting(this.getClass().getName(), "precargarDiasInhabiles");
        
    }
    
    public void validarFechaPrepagoSelecionarTipoMoneda() {
        logger.warning("Into validarFechaPrepagoSelecionarTipoMoneda.");
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        
        AttributeBinding fechaPrepagoBinding;
        fechaPrepagoBinding = ADFUtils.findControlBinding("FechaPrepago");
        Integer idTipoResolucion = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoResolucion");
        Date fechaSolicitud = (Date) ADFUtils.getBoundAttributeValue("FechaSolicitud");        
        Date fechaPrepago =  (Date) ADFUtils.getBoundAttributeValue("FechaPrepago");
        
        if (idTipoResolucion != null) {
            if ((idTipoResolucion.compareTo(PRE10_2008) == 0)) {
                try{
                    logger.info("Into case PRE10_2008");
                    validaFechaPrepago(fechaPrepagoBinding, fechaPrepago, fechaSolicitud, idTipoResolucion);
                }catch(Exception e){
                    logger.log(ADFLogger.WARNING, "Error al ejecutar el metodo validaFechaPrepago.",e);
                }

            } else if ((idTipoResolucion.compareTo(PRE28_2003) == 0)) {
                try{
                    logger.info("Into case PRE28_2003");
                    validaFechaPrepago(fechaPrepagoBinding, fechaPrepago, fechaSolicitud, idTipoResolucion);
                }catch(Exception e){
                    logger.log(ADFLogger.WARNING, "Error al ejecutar el metodo validaFechaPrepago.",e);
                }
                
            } else if (idTipoResolucion.compareTo(OTRAS_CONDICIONES) == 0) {
                logger.info("Into case OTRAS_CONDICIONES");
                //no aplica ninguna regla
                logger.warning("Fecha de prepago valida.");
            }
        } else {
            logger.severe("Error al recuperar valor idTcaTipoResolucion");
            fechaPrepagoBinding.setInputValue(null);
            resetValueDateComponent(uiFechaPrepago);
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG12_INGRESAR_SOLICITUD_PREPAGO"));
        }
    }
    
    public void tipoMonedaValueChangeListener(ValueChangeEvent vce){
        logger.warning("Dentro tipoMonedaValueChangeListener");
        long startTime = System.currentTimeMillis();
        Timestamp fechaRenovacion = null;
        Timestamp fechaPrepago = null;
        //actualiza el valor seleccionado
        
        enabledDays();
        
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        
        if(null != ADFUtils.findControlBinding("FechaPrepago")) {
            fechaPrepago = (Timestamp)JSFUtils.resolveExpression("#{bindings.FechaPrepago.inputValue}");
            //fechaPrepago.setInputValue(null);
        }else{
            logger.warning("La fecha de prepago es nula.");
        }
//        if(null != JSFUtils.resolveExpression("#{bindings.FechaRenovacion.inputValue}")){
//            fechaRenovacion = (Timestamp)JSFUtils.resolveExpression("#{bindings.FechaRenovacion.inputValue}");
//        }else{
//            logger.warning("La fecha de renovacion es nula.");
//        }
        //Se obtiene la fecha de renovacion del bean
        fechaRenovacion = prepagoBean.getFechaRenovacion();
        
        seteaValoresNulos(MONEDA);
        //Se deselecciona el check pagoTotal y se habilita.
        prepagoBean.setEsPagoTotalHeader(Boolean.FALSE);
        prepagoBean.setEsLecturaPagoTotal(Boolean.TRUE);
        
        Long idOperacion = (null != prepagoBean.getCodigoOperacion()) ? 
            prepagoBean.getCodigoOperacion().longValue() : null;
        Integer idTcaTipoResolucion =(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}")) ? 
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}") : null;
        Integer idTcaTipoMoneda =(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}")) ? 
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}") : null;
        prepagoBean.setIdTcaTipoMoneda(idTcaTipoMoneda);
        if (idOperacion != null) {
            logger.warning("Value idOperacion tipo Long is : "+idOperacion);
            logger.warning("Value idTcaTipoResolucion is : "+idTcaTipoResolucion);
            logger.warning("Value idTcaTipoMoneda is : "+idTcaTipoMoneda);
            logger.warning("Value fechaRenovacion is : "+fechaRenovacion);
            logger.warning("Valor de la fecha de prepago." + fechaPrepago);
            
            logger.warning("Se invoca metodo que ejecuta el SP");
            prepagoBean.consultarContratosDesembolsos(idOperacion, idTcaTipoResolucion,
                                                      idTcaTipoMoneda, null, fechaRenovacion,
                                                      fechaPrepago);
//            
//            // Se comenta por la FNXII-6293
//            /*if(null != idTcaTipoResolucion){
//                if(idTcaTipoResolucion.compareTo(PRE10_2008) == 0){
//                    fechaPrepago = null;
//                }else{
//                    logger.warning("Se utiliza el valor de la fecha prepago en pantalla.");
//                }
//            }*/ 
        }else{
            JSFUtils.addFacesErrorMessage("No se puede ejecutar la consulta por idOperacion no puede ser null.");
        }
        
        if ((idTcaTipoResolucion.compareTo(PRE10_2008) == 0 || idTcaTipoResolucion.compareTo(OTRAS_CONDICIONES) == 0)) {
            logger.warning("Deshabilita fines de semana en calendario.");
            HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}");
            prepagoBean.setDisabledDaysValue(holidaysBean);
            diasInhabilesMoneda();
            logger.severe("cantidad de dias inhabiles recuperada es: "+getDiasInhabilesMoneda().size());
            AttributeBinding fechaPrepagoBinding;
            fechaPrepagoBinding = ADFUtils.findControlBinding("FechaPrepago");
            Timestamp fechaPrepagoTS = null;
            Integer resultado = null;
            //para validar que la fechaPrepago no sea un dia inhabil , es necesaria una fechaPrepago
            if(null != fechaPrepagoBinding.getInputValue()){
                for(Date fechaInhabil : getDiasInhabilesMoneda()){
                    
                    try{
                        fechaPrepagoTS = (Timestamp) fechaPrepagoBinding.getInputValue();
                        java.util.Date fechaPrepagoDate = new Date(fechaPrepagoTS.getTime());
                        //logger.severe("fecha prepago cast in Date: "+fechaPrepagoDate);
                        resultado = fechaInhabil.compareTo(fechaPrepagoDate);
                        //si la variable "resultado"  es igual a 0, el resultado de la comparacion de fecha es igual
                        //es decir la fecha de prepago es igual a un dia inhabil
                        if(resultado.equals(0)){
                            //muestra el mensaje que la fechaPrepago no puede ser en un dia inhabil
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FECHA_PREPAGO_EN_DIA_INHABIL"));
                            //limpiamos la fechaPrepago
                            if(fechaPrepagoBinding != null) {
                                fechaPrepagoBinding.setInputValue(null);
                            }
                            //terminamos de recorrer los dias inhabiles,ya no es necesario seguir recorriendo fechas 
                            //inhabiles 
                            break;
                        }
                        //logger.severe("resultado de la comparacion de las fechas : "+resultado);
                    }catch(Exception e){
                        logger.severe("Se origino un problema al comparar" +
                            "fechaPrepago con una fecha inhabil ", e);
                    }
                } 
            }else{
                //no se tiene una fechaPrepago no se puede calcular si es un dia inhabil
                logger.severe("FechaPrepago es nula, no se puede calcular los dias habiles");
            }
        } else if ((idTcaTipoResolucion.compareTo(PRE28_2003) == 0)) {
            logger.warning("Habilita fines de semana en calendario.");
            prepagoBean.setDisabledDaysValue(null);
        }
        validarFechaPrepagoSelecionarTipoMoneda();

        
        long endTime = System.currentTimeMillis();
        
        logger.warning("Tiempo en ejecutar metodo tipoMonedaValueChangeListener :  "
                            + (endTime - startTime)/1000 + " segundos");
    }
    
    public void llenarCatalogoTipoDeMoneda(){
        logger.warning("Dentro de llenarCatalogoTipoDeMoneda");
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        //recuperar los tipos de moneda que tienes los diferentes contratos/prepago
        DCIteratorBinding iter = ADFUtils.findIterator("ContratoDesembolsoPrepagoVOIterator");
        iter.setRangeSize(-1);
        Row[] rows= iter.getAllRowsInRange();
        List<SelectItem>  listaTipoMoneda = new ArrayList<SelectItem>();
        Integer id= null;
        String descripcion = null;
        for(Row row:rows){
            id = (Integer) row.getAttribute("IdTcaTipoMoneda");
            descripcion = (String) row.getAttribute("TipoMoneda");
            listaTipoMoneda.add(new SelectItem(id,descripcion));
        }
        
        logger.warning("values of the list : ");
        for(SelectItem selectItem : listaTipoMoneda){
            logger.warning("value : " + selectItem.getValue() + "  label : " + selectItem.getLabel());
        }

        List<SelectItem>  listaTipoMonedaNoRepetidas = new ArrayList<SelectItem>();
        
        for (SelectItem item : listaTipoMoneda) {
            boolean isFound = false;
            for (SelectItem e : listaTipoMonedaNoRepetidas) {
                if (e.getValue().equals(item.getValue()))
                    isFound = true;
            }

            if (!isFound) listaTipoMonedaNoRepetidas.add(item);
        }
        
        logger.warning("values of the list (Not Repeat) : ");
        for(SelectItem selectItem : listaTipoMonedaNoRepetidas){
            logger.warning("value : " + selectItem.getValue() + "  label : " + selectItem.getLabel());
        }
        
        prepagoBean.setListaTipoMoneda(listaTipoMonedaNoRepetidas);
        logger.warning("Fuera de llenarCatalogoTipoDeMoneda");
    }
        
    public Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.getTime();
    }
    
    public Boolean validarCargoPrepago(){
        logger.warning("Into validarCargoPrepago Action Bean....");
        Boolean isValidarCargoPrepago = Boolean.FALSE;
        try{
        
        BindingContainer bindings = getBindings();
        
        OperationBinding operationBinding = bindings.getOperationBinding("validarFormularioCargoPrepago");
        operationBinding.execute();
        isValidarCargoPrepago = (Boolean) operationBinding.getResult();
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en validarCargoPrepago." + this.getClass() + e.getMessage());
        }
        logger.log(ADFLogger.ERROR, "Valor de retorno..." + isValidarCargoPrepago);
        return isValidarCargoPrepago;
    }

    public void setValidaInformacionCargoPrepago(RichRegion validaInformacionCargoPrepago) {
        this.validaInformacionCargoPrepago = validaInformacionCargoPrepago;
    }

    public RichRegion getValidaInformacionCargoPrepago() {
        return validaInformacionCargoPrepago;
    }
    private Boolean validaFinalizaCamposRequeridos() {
        logger.warning("inside validaFinalizaCamposRequeridos");

        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        Boolean result = Boolean.FALSE;
        AttributeBinding idTcaTipoMoneda = null;
        Integer idTcaTipoMonedaInt = null;
        
        AttributeBinding fechaSolicitud;
        fechaSolicitud = ADFUtils.findControlBinding("FechaSolicitud");
        AttributeBinding fechaPrepago;
        fechaPrepago = ADFUtils.findControlBinding("FechaPrepago");
        AttributeBinding fechaRenovacion;
        fechaRenovacion = ADFUtils.findControlBinding("FechaRenovacion");
        AttributeBinding montoPrepago;
        montoPrepago = ADFUtils.findControlBinding("MontoPrepago");
        AttributeBinding idTcaTipoResolucion;
        idTcaTipoResolucion = ADFUtils.findControlBinding("IdTcaTipoResolucion");
        AttributeBinding idTcaTipoPrepago;
        idTcaTipoPrepago = ADFUtils.findControlBinding("IdTcaTipoPrepago");
        AttributeBinding idTcaTipoCaptura;
        idTcaTipoCaptura = ADFUtils.findControlBinding("IdTcaTipoCaptura");
        AttributeBinding observacionPrepago;
        observacionPrepago = ADFUtils.findControlBinding("Observacion");
        //Se agrega el IdTcaTipoMoneda
        idTcaTipoMoneda = ADFUtils.findControlBinding("IdTcaTipoMoneda1");
        
        
        java.sql.Timestamp fechSolicitud = (java.sql.Timestamp) fechaSolicitud.getInputValue();
        java.sql.Timestamp fechPrepago = (java.sql.Timestamp) fechaPrepago.getInputValue();
        java.sql.Timestamp fechRenovacion = (java.sql.Timestamp) fechaRenovacion.getInputValue();
        BigDecimal montPrepago = (BigDecimal) montoPrepago.getInputValue();
        Integer idTcaTipoRes = (Integer) idTcaTipoResolucion.getInputValue();
        Integer idTcaTipoPrep = (Integer) idTcaTipoPrepago.getInputValue();
        Integer idTcaTipoCap = (Integer) idTcaTipoCaptura.getInputValue(); 
        String observacion = (String) observacionPrepago.getInputValue();
        //Se recupera el valor en pantalla de IdTcaTipoMoneda

        logger.warning("Asignacion de idTcaTipoMonedaInt: ");
        if(idTcaTipoMoneda == null){
            Integer VidTcaTipoMoneda2 = ADFUtils.findControlBinding("IdTcaTipoMoneda") != null ? (Integer) ADFUtils.findControlBinding("IdTcaTipoMoneda").getInputValue() : null;
            Integer VidTcaTipoMoneda = ADFUtils.findControlBinding("IdMoneda1") != null ? (Integer) ADFUtils.findControlBinding("IdMoneda1").getInputValue() : null;
                    if(VidTcaTipoMoneda == null && VidTcaTipoMoneda2 != null) {
                    VidTcaTipoMoneda = VidTcaTipoMoneda2;
                logger.warning("cambiado VidTcaTipoMoneda: "+ VidTcaTipoMoneda);
            }
                idTcaTipoMonedaInt = VidTcaTipoMoneda;
        }
        else
        {
            idTcaTipoMonedaInt = idTcaTipoMoneda.getInputValue() != null? (Integer) idTcaTipoMoneda.getInputValue() : null;
            logger.warning("no cambiado VidTcaTipoMoneda: "+ idTcaTipoMoneda.getInputValue()); 
        }
        logger.warning("fechSolicitud: " + fechaSolicitud.getInputValue());
        logger.warning("fechPrepago: " + fechaPrepago.getInputValue());
        logger.warning("fechRenovacion: " + fechaRenovacion.getInputValue());
        logger.warning("montPrepago: " + montoPrepago.getInputValue());
        logger.warning("idTcaTipoRes: " + idTcaTipoResolucion.getInputValue());
        logger.warning("idTcaTipoPrep: " + idTcaTipoPrepago.getInputValue());
        logger.warning("idTcaTipoCap: " + idTcaTipoCaptura.getInputValue());
        logger.warning("idTcaTipoMoneda: "+ idTcaTipoMonedaInt);

        if (prepagoBean.getEsLineaGlobal()) {
            if ((fechSolicitud != null) && (fechPrepago != null) && 
                (fechRenovacion != null) && (montPrepago != null) && 
                (idTcaTipoRes != null) && (idTcaTipoPrep != null) &&
                (idTcaTipoCap != null) && (observacion != null) &&
                (idTcaTipoMonedaInt != null)) {
                    if(observacion.trim().length() > 0) {
                        result = Boolean.TRUE;
                    }
            }
        } else {
            if ((fechSolicitud != null) && (fechPrepago != null) && 
                (montPrepago != null) && (idTcaTipoRes != null) &&
                (idTcaTipoPrep != null) && (idTcaTipoCap != null) && 
                (observacion != null) && (idTcaTipoMonedaInt != null)) {
                    if(observacion.trim().length() > 0) {
                        result = Boolean.TRUE;   
                    }
            }
        }
        return (result);
    }
    
    private Boolean validaFinalizaDiferenciaMonto() {
        logger.warning("inside validaFinalizaDiferenciaMonto");

        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        Boolean result = Boolean.FALSE;
        Integer idTcaTipoCaptura = null;
        AttributeBinding montoPrepago;
        montoPrepago = ADFUtils.findControlBinding("MontoPrepago");
        AttributeBinding montoTotalPrepagoManual;
        BigDecimal montoTotalPrepagoProrrateo = BigDecimal.ZERO;
        //montoTotalPrepago = ADFUtils.findControlBinding("MontoTotalPrepago");
        if(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoCaptura.inputValue}")){
            idTcaTipoCaptura = (Integer) JSFUtils.resolveExpression("#{bindings.IdTcaTipoCaptura.inputValue}");
        }else{
            logger.warning("La resolucion es nula.");
        }
        
        BigDecimal montPrepago = (BigDecimal) montoPrepago.getInputValue();
            try {
            if (null != idTcaTipoCaptura) {
                if (idTcaTipoCaptura.compareTo(ID_POR_PRORRATEO) == 0) {
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("obtenerMontoTotalPrepago");
                    operationBinding.execute();
                    if (!operationBinding.getErrors().isEmpty()) {
                        // Manejo de errores
                        logger.log(ADFLogger.WARNING, "La ejecucion del metodo contiene errores.");
                        JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    } else {
                        if (null != operationBinding.getResult()) {
                            montoTotalPrepagoProrrateo = (BigDecimal) operationBinding.getResult();
                            //BigDecimal montTotalPrepago = (BigDecimal) montoTotalPrepago.getInputValue();

                            logger.warning("montPrepago: " + montPrepago);
                            logger.warning("montoTotalPrepagoProrrateo: " + montoTotalPrepagoProrrateo);
                            try {
                                if ((montPrepago.compareTo(montoTotalPrepagoProrrateo) == 0)) {
                                    logger.warning("Monto Total de Prepago igual de Monto Prepagar.");
                                    result = Boolean.TRUE;
                                }
                            } catch (Exception e) {
                                logger.severe("Ocurrio un problema al comparar montoPrepago contra montoTotal", e);
                            }
                        }
                        logger.log(ADFLogger.WARNING, "Se realiza correctamente el metodo esPagoTotalById.");
                    }
                } else {
                    montoTotalPrepagoManual = ADFUtils.findControlBinding("MontoTotalPrepago");
                    //BigDecimal montTotalPrepago = (BigDecimal) montoTotalPrepago.getInputValue();
                    BigDecimal montTotalPrepago = (BigDecimal) montoTotalPrepagoManual.getInputValue();
                    logger.warning("montPrepago: " + montPrepago);
                    logger.warning("montTotalPrepago: " + montTotalPrepago);
                    try {
                        if ((montPrepago.compareTo(montTotalPrepago) == 0)) {
                            logger.warning("Monto Total de Prepago igual de Monto Prepagar.");
                            result = Boolean.TRUE;
                        }
                    } catch (Exception e) {
                        logger.severe("Ocurrio un problema al comparar montoPrepago contra montoTotal", e);
                    }
                }
            } else {
                logger.warning("Tipo de captura nula.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al ejecutar el metodo esPagoTotalById." + e.getClass() + "." + e);
        }
        return (result);
    }
    
    public Boolean validaFinalizaDiferenciaFecha() {
        logger.warning("inside validaFinalizaDiferenciaFecha");
        
        DCIteratorBinding voContratoDesembolsoPrepago = null;
        Boolean result = Boolean.FALSE;
        Long numeroRegistro = null;
        try{
            voContratoDesembolsoPrepago =
                ADFUtils.getDCBindingContainer().findIteratorBinding("ContratoDesembolsoPrepagoVOIterator");
            
            numeroRegistro = voContratoDesembolsoPrepago.getEstimatedRowCount();
            logger.warning("Número de contratos encontrados." + numeroRegistro);
            if(numeroRegistro > 0){
                result = Boolean.TRUE;
            }else{
                logger.warning("No se encuentran registros.");
            }
        }catch(Exception e){
            logger.warning("arg0");
        }        
        return (result);
    }
    
    public Boolean validaFinalizaFechaPrepago() {
        logger.warning("inside validaFinalizaFechaPrepago");
        
        Boolean result = Boolean.FALSE;
        AttributeBinding fechaPrepago;
        fechaPrepago = ADFUtils.findControlBinding("FechaPrepago");
        
        if (fechaPrepago != null && fechaPrepago.toString().trim().length() > 0) {
            result = Boolean.TRUE;
        }
        return (result);
    }
    
    //Se obtiene Fecha de amortización tomando como referencia la Fecha de proximo pago mas cercana a la actual de la tabla Contrato Desembolso.
    public Date fechaAmortizacion() {
        logger.warning("inside fechaAmortizacion.");
    
        java.sql.Timestamp currentDate = new java.sql.Timestamp(getCurrentDate().getTime());
        
        Long currentTime = currentDate.getTime();
        Long minDiff = -1L;
        Date fechaAmortizacion = null;
        
        DCIteratorBinding voContratoDesembolsoPrepago = ADFUtils.getDCBindingContainer().findIteratorBinding("ContratoDesembolsoPrepagoVOIterator");
        
        Row[] contratoDesembolsoPrepago = voContratoDesembolsoPrepago.getAllRowsInRange();
        try{
        if (contratoDesembolsoPrepago != null) {
            for (Row row : contratoDesembolsoPrepago) {
                java.sql.Timestamp fechaProximoPago = (java.sql.Timestamp)row.getAttribute("FechaProximoPago");
                logger.warning("fechaProximoPago" + fechaProximoPago);
                if (fechaProximoPago != null) {
                    long diff = Math.abs(currentTime - fechaProximoPago.getTime());
                    if ((minDiff == -1) || (diff < minDiff)) {
                        minDiff = diff;
                        fechaAmortizacion = new Date (fechaProximoPago.getTime());
                    }    
                }
            }   
        } else {
            logger.warning("No se encontraron datos en tabla Contratos Desembolso.");
        }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error en fechaAmortizacion." + e.getClass() + "." + e);
        }
        logger.warning("fechaAmortizacion: " + fechaAmortizacion);
        
        return (fechaAmortizacion);
    }
    
    
    public Boolean validaFinalizaMontoCapitalTotal() {
        logger.warning("inside validaFinalizaMontoCapitalTotal");

        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        Boolean result = Boolean.FALSE;
        
        AttributeBinding montoPrepago;
        montoPrepago = ADFUtils.findControlBinding("MontoPrepago");
        AttributeBinding capitalNovencidoTotalPrepago;
        capitalNovencidoTotalPrepago = ADFUtils.findControlBinding("CapitalNovencidoTotalPrepago");
        
        BigDecimal montPrepago = (BigDecimal) montoPrepago.getInputValue();
        BigDecimal capitalNovencidoTotalPrep = (BigDecimal) capitalNovencidoTotalPrepago.getInputValue();
        
        logger.warning("montPrepago: " + montPrepago);
        logger.warning("capitalNovencidoTotalPrepago: " + capitalNovencidoTotalPrep);
        
        if (montPrepago.compareTo(capitalNovencidoTotalPrep) <= 0) {
            logger.warning("Monto Prepagar menor o igual al Total del Capital No Vencido a la fecha del Prepago.");
            result = Boolean.TRUE;
        }
        
        return (result);
    }
        
    public Boolean validaFinalizaMontoCapital() {
        logger.warning("inside validaFinalizaMontoCapital");

        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        Boolean result = Boolean.FALSE;
        
        DCIteratorBinding voContratoDesembolsoPrepago = ADFUtils.getDCBindingContainer().findIteratorBinding("ContratoDesembolsoPrepagoVOIterator");
        
        Row[] contratoDesembolsoPrepago = voContratoDesembolsoPrepago.getAllRowsInRange();
        
        for (Row row : contratoDesembolsoPrepago) {
            BigDecimal capitalNoVencido = (BigDecimal)row.getAttribute("CapitalNoVencido");
            BigDecimal montoPrepago = (BigDecimal)row.getAttribute("MontoPrepago");
            
            logger.warning("capitalNoVencido" + capitalNoVencido);
            logger.warning("montoPrepago" + montoPrepago);
            
            if (montoPrepago != null && capitalNoVencido != null) {
                if (montoPrepago.compareTo(capitalNoVencido) <= 0) {
                    logger.warning("Monto Prepago menor o igual a Capital No Vencido a la fecha del prepago del contrato.");
                    result = Boolean.TRUE;
                } else {
                    logger.warning("Monto Prepago mayor a Capital No Vencido a la fecha del prepago del contrato.");
                    return result = Boolean.FALSE;
                }   
            }
        }
        
        return (result);
    }
    
    public void esPagoTotalValueChangeListener(ValueChangeEvent vce){
        logger.log(ADFLogger.WARNING, "Into esPagoTotalValueChangeListener.");
        Boolean esPagoTotal = null ;
        Integer id = null;
        //actualiza el valor seleccionado
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        DCIteratorBinding iterator = ADFUtils.findIterator("ContratoDesembolsoPrepagoVOIterator");
        Row row = iterator.getCurrentRow();
        esPagoTotal = (null != row.getAttribute("EsPagoTotal")) ? (Boolean) row.getAttribute("EsPagoTotal") : null;
        id = (null != row.getAttribute("Id")) ? (Integer) row.getAttribute("Id") : null; 
        logger.log(ADFLogger.WARNING, "Valor esPagoTotal :" + esPagoTotal);
        logger.log(ADFLogger.WARNING, "Valor id :" + id);
        try{
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("esPagoTotalById");
        operationBinding.getParamsMap().put("id",id);
        operationBinding.getParamsMap().put("esPagoTotal",esPagoTotal);
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            logger.log(ADFLogger.WARNING, "La ejecucion del metodo contiene errores.");
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            logger.log(ADFLogger.WARNING, "Se realiza correctamente el metodo esPagoTotalById.");
        }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error al ejecutar el metodo esPagoTotalById." + e.getClass() + "." + e);
        }
    }
    
    public void esPagoTotalHeaderActionEvent(ActionEvent actionEvent) {
        logger.warning("Dentro de esPagoTotalHeaderActionEvent");
        BindingContainer bindings = getBindings();
        Boolean esPagoTotalHeader = null;
        PrepagoBean prepagoBean = null;
        try{
            try{
                esPagoTotalHeader = (Boolean) 
                JSFUtils.resolveExpression("#{pageFlowScope.esPagoTotalHeaderValueAction}");
                logger.warning("esPagoTotalHeader : " +esPagoTotalHeader);
            }catch(Exception e){
                logger.severe("Error al recuperar esPagoTotalHeaderValueAction",e);
            }
            
            OperationBinding operationBinding = bindings.getOperationBinding("esPagoTotalAllRows");
            operationBinding.getParamsMap().put("esPagoTotal",esPagoTotalHeader);
            operationBinding.execute();
            
            try{
                prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
                prepagoBean.setEsPagoTotalHeader(esPagoTotalHeader);
                
            }catch(Exception e){
                logger.severe("Error al recuperar prepagoBean",e);
            }
            
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            
            //Se agregan todos los contrato a la lista "ListaContratoDesembolso"  
            this.selecionarTodasLineas();
            
        }catch(Exception e){
            logger.severe("Error en esPagoTotalHeaderActionEvent",e);
        }
        //AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaContratoUIC());
        logger.warning("Termina de esPagoTotalHeaderActionEvent");
    }
    public void esPagoTotalHeaderValueChangeListener(ValueChangeEvent vce){
        logger.log(ADFLogger.WARNING, "Into esPagoTotalValueChangeListener.");
        BindingContainer bindings = getBindings();
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean esPagoTotalHeader = null;
        try{
        if(null != vce.getNewValue()){
            esPagoTotalHeader = (Boolean) vce.getNewValue();
            OperationBinding operationBinding = bindings.getOperationBinding("esPagoTotalAllRows");
            operationBinding.getParamsMap().put("esPagoTotal",esPagoTotalHeader);
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            //Si es true el valor del componente pagoTotal del header,agregar los valores de todos los contratos
            //FNXII-4992,  por algun motivo al selecionar el evento "selectionListener" 
            //de la tabla de contrato de desembolsos , distara el evento 'valueChangeListener' de selectBooleanCheckbox
            //del pago total 
            if(esPagoTotalHeader){
                //Seleccionamos todas sus lineas para la carga de las condiciones especiales
                logger.warning("Valor del pagoTotalHeader : "+esPagoTotalHeader);
                selecionarTodasLineas();
            }
        }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error al ejecutar esPagoTotalAllRows." + e.getClass() + "." + e);
        }
    }

    public void contratoDesembolsoPrepagoSelectionListener(SelectionEvent selectionEvent) {
        String lineaCreditoFlexCube = null;
        String contratoDesembolsoFlexcube = null;
        Long idLineaCredito = null;
        // Evento al que entra cuando hace click en el row de la tabla
        logger.log(ADFLogger.WARNING, "Inside contratoDesembolsoPrepagoSelectionListener");
        // 1) Evento por default (para actualizar filas)
        JSFUtils.resolveMethodExpression("#{bindings.ContratoDesembolsoPrepagoVO.collectionModel.makeCurrent}",
                                         Object.class, new Class[] {
                                         org.apache.myfaces.trinidad.event.SelectionEvent.class }, new Object[] {
                                         selectionEvent });
        // 2) creamos una instancia de Bean de prepago
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");

        DCIteratorBinding voContratoDesembolsoPrepago =
            ADFUtils.getDCBindingContainer().findIteratorBinding("ContratoDesembolsoPrepagoVOIterator");

        Row contratoDesembolsoPrepagoRow = voContratoDesembolsoPrepago.getCurrentRow();
        try {
            if (null != contratoDesembolsoPrepagoRow) {
                lineaCreditoFlexCube =
                    (null != contratoDesembolsoPrepagoRow.getAttribute("LineaCreditoFlexcube")) ?
                    (String) contratoDesembolsoPrepagoRow.getAttribute("LineaCreditoFlexcube") : null;
                logger.log(ADFLogger.WARNING, "lineaCreditoFlexCube value : " + lineaCreditoFlexCube);
                //lineaCreditoFlexCube = "BHQLD01090560003";
                List<String> listaLineaCredito = prepagoBean.getListaLineaCredito();
                listaLineaCredito.add(lineaCreditoFlexCube);
                prepagoBean.setListaLineaCredito(listaLineaCredito);

                contratoDesembolsoFlexcube =
                    (null != contratoDesembolsoPrepagoRow.getAttribute("ContratoDesembolsoFlexcube")) ?
                    (String) contratoDesembolsoPrepagoRow.getAttribute("ContratoDesembolsoFlexcube") : null;
                logger.log(ADFLogger.WARNING, "contratoDesembolsoFlexCube value : " + contratoDesembolsoFlexcube);
                //contratoDesembolsoFlexcube = "BHQLD03090560006";
                List<String> listaContratoDesembolso = prepagoBean.getListaContratoDesembolso();
                listaContratoDesembolso.add(contratoDesembolsoFlexcube);
                prepagoBean.setListaContratoDesembolso(listaContratoDesembolso);


                logger.log(ADFLogger.WARNING, "idLineaCredito value : " + idLineaCredito);
                if(null != contratoDesembolsoPrepagoRow.getAttribute("IdLineaCredito")){
                    idLineaCredito = (Long) contratoDesembolsoPrepagoRow.getAttribute("IdLineaCredito");
                    prepagoBean.setIdLineaCredito(idLineaCredito);
                    logger.log(ADFLogger.WARNING, "Valor de Linea de credito :" + idLineaCredito);
                }else{
                    logger.log(ADFLogger.WARNING, "Linea de credito es nulo :");
                }
            }else{
                logger.log(ADFLogger.WARNING, "No se recuperaron rows de la tabla contrato desembolsos.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING,
                       "Error al obtener lista linea contrato, lista contrato y linea de credito." + e.getClass() +
                       "." + e);
        }
        //refrescar los taskFlow para que tomen los nuevos parametros de entrada.
        this.refrescarTaskFlows();
    }
        
    public void cargarProximasFechasActionEvent(ActionEvent actionEvent) {
        logger.warning("Entrando en cargarProximasFechasActionEvent.");

        //variables para ejecutar consultarContratoDesembolsoPorRangoFechasPrepago
        String contratoDesembolsoFlexcube = getContratoFlexSeleccionado();
        Long idPadre = getIdPadreSeleccionado();
        Long idOperacion = null;
        Timestamp fechaPrepago = null;
        Timestamp fechaSolicitud = null;
        Integer idTcaTipoMoneda = null;

        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");

        //recuperar valores

        if (null != prepagoBean.getCodigoOperacion()) {
            idOperacion = prepagoBean.getCodigoOperacion().longValue();
        } else {
            logger.warning("idOperacion  is null");
        }

        if (null != JSFUtils.resolveExpression("#{bindings.FechaSolicitud.inputValue}")) {
            fechaSolicitud = (Timestamp) JSFUtils.resolveExpression("#{bindings.FechaSolicitud.inputValue}");
        } else {
            logger.warning("fechaSolicitud is null");
        }

        if (null != JSFUtils.resolveExpression("#{bindings.FechaPrepago.inputValue}")) {
            fechaPrepago = (Timestamp) JSFUtils.resolveExpression("#{bindings.FechaPrepago.inputValue}");
        } else {
            logger.warning("fechaPrepago is null");
        }

        if (null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}")) {
            idTcaTipoMoneda = (Integer) JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}");
        } else {
            logger.warning("idTcaTipoMoneda is null");
        }

        prepagoBean.consultarContratoDesembolsoPorRangoFechasPrepago(idOperacion, contratoDesembolsoFlexcube,
                                                                     fechaSolicitud, fechaPrepago, idTcaTipoMoneda, idPadre);

        logger.warning("Saliendo de cargarProximasFechasActionEvent.");
    }
    
    public void tipoCapturaValueChangeListener(ValueChangeEvent vce){
        logger.log(ADFLogger.WARNING, "Into tipoCapturaValueChangeListener.");
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        BindingContainer bindings = getBindings();
        vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
        BigDecimal montoPrepagar =(null != JSFUtils.resolveExpression("#{bindings.MontoPrepago.inputValue}")) ?
            (BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoPrepago.inputValue}") : null;
        Integer idTcaTipoCaptura =(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoCaptura.inputValue}")) ?
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoCaptura.inputValue}") : null;
        // ID 2 = Por prorrateo	
        if(idTcaTipoCaptura.equals(ID_POR_PRORRATEO)){
            logger.log(ADFLogger.WARNING, "Into idTcaTipoCaptura == 2");
            //RN_06   Al seleccionar el tipo de captura "Por Prorrateo" el sistema debe actualizar el saldo de la 
            //columna "monto prepago" de cada contrato de desembolso de acuerdo a lo siguiente:
            //Monto prepago por contrato= (Capital no vencido a la fecha de prepago del contrato de desembolso/sumatoria
            //del capital no vencido a la fecha de prepago de todos los contratos a prepagar) * Monto total a prepagar.
            if(montoPrepagar != null){
                OperationBinding operationBinding = bindings.getOperationBinding("tipoCapturaProrrateo");
                operationBinding.getParamsMap().put("montoPrepagar",montoPrepagar);
                operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }
                
                //Actualiza el total de la columna "Monto prepago" que es igual al "monto prepagar" capturado.VA_12.
                //VA_12   Al capturar el monto parcial de un contrato de desembolso el sistema debe actualizar el Checkbox 
                //(columna pago total) de dicho contrato a solo lectura.
                prepagoBean.setEsLecturaPagoTotal(Boolean.TRUE);
                logger.log(ADFLogger.WARNING, "getTablaContratoUIC 1: ");
                if (null != getTablaContratoUIC()) {
                    logger.warning("Actualizar tabla.");
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaContratoUIC());
                    logger.warning("Actualizar header tabla.");
                    AdfFacesContext.getCurrentInstance().addPartialTarget(headerTablaContratoUIC); 
                }
            }else{
                RichSelectOneRadio richSelectOneRadio = prepagoBean.getSelectOneRadioTipoCapturaBinding();
                JSFUtils.setExpressionValue("#{bindings.IdTcaTipoCaptura.inputValue}",0);
                richSelectOneRadio.resetValue();
                AdfFacesContext.getCurrentInstance().addPartialTarget(richSelectOneRadio);
                JSFUtils.addFacesErrorMessage("El campo Monto prepagar no puede estar vacio para realizar la captura por Prorrateo");
            }
            
            //De acuerdo a la incidencia FNXII-5477 cuando es por prorrateo se deben cargar las tablas coberturas, 
            //venta de catera y fuentes externas con todos los contratos de desembolso.
            this.selecionarTodasLineas();
            //prepagoBean.getPagoTotalHeaderBinding().resetValue();
            prepagoBean.setEsPagoTotalHeader(Boolean.FALSE);
        } else {
            OperationBinding operationBinding = bindings.getOperationBinding("tipoCapturaManual");
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            logger.log(ADFLogger.WARNING, "getTablaContratoUIC 2: ");
            if (null != getTablaContratoUIC()) {
                AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaContratoUIC());
            }
            //Cualquier otro valor seleccionado el pagoTotal no se especifica que sea de solo lectura
            prepagoBean.setEsPagoTotalHeader(Boolean.FALSE);
            prepagoBean.setEsLecturaPagoTotal(Boolean.FALSE);
        }
        
    }
    
    public void montoPrepagoValueChangeListener(ValueChangeEvent vce){
        try {
            vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("actualizarMontoTotalPrepago");
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            logger.log(ADFLogger.WARNING, "getTablaContratoUIC 3: ");
            if (null != getTablaContratoUIC()) {
                AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaContratoUIC());
            }
        } catch(Exception e) {
            logger.log(ADFLogger.WARNING,"Ocurrio un error  en montoPrepagoValueChangeListener.", e);
        }
    }
    
    public void fechaSolicitudValueChangeListener(ValueChangeEvent vce) {
        //RN_13 La fecha de solicitud debe ser igual o menor a la fecha actual del sistema.
        logger.warning("Into fechaSolicitudValueChangeListener.");
        logger.warning("valor anterior: " + vce.getOldValue());
        logger.warning("valor actual: " + vce.getNewValue());
        logger.warning("fecha actual: " + getCurrentDate());
        PrepagoBean prepagoBean = null;
        Date fechaSolicitudNueva = (Date) vce.getNewValue();
        Boolean actualizaValor = Boolean.FALSE;
        
        actualizaValor = seteaValoresNulos(FECHA_SOLICITUD);
        if(actualizaValor){
            logger.warning("Los valores han sido seteados a nulos.");
        }else{
            logger.warning("Error, No se cambiaron los valores.");
        }
        try{
            prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
            
            prepagoBean.consultarContratosDesembolsos(null, null, null, null, prepagoBean.getFechaRenovacion(), null);
            prepagoBean.setListaTipoMoneda(null);
            prepagoBean.setEsPagoTotalHeader(Boolean.FALSE);
            prepagoBean.setEsLecturaPagoTotal(Boolean.TRUE);
            
            logger.log(ADFLogger.WARNING, "getTablaContratoUIC 4: ");
            if (null != getTablaContratoUIC()) {
                AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaContratoUIC());
            }    
        }catch(Exception e){
            logger.warning("Error al ejecutar el SP_CONTRATO_DESEMBOLSO.", e);
        }
        logger.warning("Termina fechaSolicitudValueChangeListener correctamente.");
    }
    
    private Boolean seteaValoresNulos(Integer campoSeleccionado){
        logger.warning("Entra en seteaValoresNulos.");
        Boolean actualizaValor = Boolean.FALSE;
        PrepagoBean prepagoBean = null;
        AttributeBinding fechaSolicitud;
        AttributeBinding tipoResolucion = null;;
        AttributeBinding fechaPrepago = null;
        AttributeBinding tipoMoneda = null;
        AttributeBinding tipoCaptura = null;
        try{
            //Se obtene instancia del prepagoBean                    
            tipoResolucion = ADFUtils.findControlBinding("IdTcaTipoResolucion"); 
            fechaPrepago = ADFUtils.findControlBinding("FechaPrepago"); 
            tipoMoneda = ADFUtils.findControlBinding("IdTcaTipoMoneda"); 
            tipoCaptura = ADFUtils.findControlBinding("IdTcaTipoCaptura"); 
            
            logger.warning("tipoResolucion : " + tipoResolucion);
            logger.warning("fechaPrepago : " + fechaPrepago);
            logger.warning("tipoMoneda : " + tipoMoneda);
            logger.warning("tipoCaptura : " + tipoCaptura);
            
        }catch(Exception e){
            logger.warning("Error al obtener los valores del binding.", e);
        }
        try{
            prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
            logger.warning("Valor de case : " + campoSeleccionado);
            switch(campoSeleccionado){
            case 1:
                if(null != tipoResolucion || null != fechaPrepago || null != tipoMoneda || null != tipoCaptura){
                    logger.warning("Selecciona fecha de solicitud.");
                    tipoResolucion.setInputValue(null);
                    fechaPrepago.setInputValue(null);
                    tipoMoneda.setInputValue(null);
                    if (null != tipoCaptura) {
                        tipoCaptura.setInputValue(null);
                    }
                    
                    resetValueDateComponent(uiFechaPrepago);
                    resetValueSelectOneChoiceComponent(tipoResolucionUI);
                    resetValueSelectOneChoiceComponent(monedaUIC);
                    
                    if (null != tipoCaptura) {
                        resetValueOneRadioComponent(prepagoBean.getSelectOneRadioTipoCapturaBinding());     
                    }
                    actualizaValor = Boolean.TRUE;
                }else{
                    logger.warning("No hay algun campo con valor, no se actualizan los campos.");
                }
            break;
            case 2:
                if(null != fechaPrepago || null != tipoMoneda || null != tipoCaptura){
                    logger.warning("Selecciona tipo de resolucion.");
                    fechaPrepago.setInputValue(null);
                    tipoMoneda.setInputValue(null);
                    
                    if (null != tipoCaptura) {
                        tipoCaptura.setInputValue(null);
                    }
                    resetValueDateComponent(uiFechaPrepago);
                    resetValueSelectOneChoiceComponent(monedaUIC);
                    
                    if (null != tipoCaptura) {
                        resetValueOneRadioComponent(prepagoBean.getSelectOneRadioTipoCapturaBinding());     
                    }
                    actualizaValor = Boolean.TRUE;
                }else{
                    logger.warning("No hay algun campo con valor, no se actualizan los campos.");
                }
            break;
            case 3:
                if(null != tipoMoneda || null != tipoCaptura){
                    logger.warning("Selecciona fecha de prepago.");
                    tipoMoneda.setInputValue(null);
                    if (null != tipoCaptura) {
                       tipoCaptura.setInputValue(null);                  
                    }
                     
                    resetValueSelectOneChoiceComponent(monedaUIC);
                    /*
                    if (null != tipoCaptura) {
                        resetValueOneRadioComponent(prepagoBean.getSelectOneRadioTipoCapturaBinding());     
                    }
                    */
                    resetValueSelectOneChoiceComponent(monedaUIC);
                    logger.warning("resetValueOneRadioComponent malo.");
                    resetValueOneRadioComponent(prepagoBean.getSelectOneRadioTipoCapturaBinding());
                    actualizaValor = Boolean.TRUE;
                }else{
                    logger.warning("No hay algun campo con valor, no se actualizan los campos.");
                }
            break;
            case 4:
                if(null != tipoCaptura){
                    logger.warning("Selecciona el tipo de moneda.");
                    tipoCaptura.setInputValue(null);
                    resetValueOneRadioComponent(prepagoBean.getSelectOneRadioTipoCapturaBinding());
                    actualizaValor = Boolean.TRUE;
                }else{
                    logger.warning("No hay algun campo con valor, no se actualizan los campos.");
                }
            break;
            default:
                logger.warning("Se ha seleccionado otro campo, no realiza actualizacion.");
            break;
            }
            logger.warning("tipoResolucion : " + tipoResolucion);
            logger.warning("fechaPrepago : " + fechaPrepago);
            logger.warning("tipoMoneda : " + tipoMoneda);
            logger.warning("tipoCaptura : " + tipoCaptura);
        }catch(Exception e){
            logger.warning("Error al realizar el cambio.", e);
        }
        logger.warning("Se realizo cambio valores a nulos." + actualizaValor);
        return actualizaValor;
    }
    
    public void validaFechaSolicitud(AttributeBinding fechaSolicitud, Date fechaSolicitudActual) {
        logger.warning("inside validaFechaSolicitud.");
        
        AttributeBinding fechaPrepago;
        fechaPrepago = ADFUtils.findControlBinding("FechaPrepago");
        Date fechaActual = getCurrentDate();
        
        // Se comenta por la FNXII-6293
        /*if (fechaPrepago != null) {
            fechaPrepago.setInputValue(null);   
        }*/
            
        if (null != fechaSolicitudActual && fechaSolicitudActual.compareTo(fechaActual) > 0) {
            logger.warning("Fecha de solicitud mayor a fecha actual.");
            fechaSolicitud.setInputValue(null);
            resetValueDateComponent(uiFechaSolicitud);
            logger.warning("binding fecha solicitud: " + fechaSolicitud.getInputValue());
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG11_INGRESAR_SOLICITUD_PREPAGO"));
        }
    }
    
    public void fechaPrepagoValueChangeListener(ValueChangeEvent vce) {
        logger.warning("Into fechaPrepagoValueChangeListener.");
        logger.warning("valor anterior: " + vce.getOldValue());
        logger.warning("valor actual: " + vce.getNewValue());
        
        Boolean valoresNulos = Boolean.FALSE;
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        
        AttributeBinding fechaPrepagoBinding;
        fechaPrepagoBinding = ADFUtils.findControlBinding("FechaPrepago");
        Integer idTipoResolucion = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoResolucion");
        Date fechaSolicitud = (Date) ADFUtils.getBoundAttributeValue("FechaSolicitud");        
        Date fechaPrepago = (Date) vce.getNewValue();
        Integer sector = prepagoBean.getSectorInstitucional();
        Integer tipoGarantia = prepagoBean.getTipoGarantia();
        Integer tipoCliente = tipoCliente(idTipoResolucion);
        
        valoresNulos = seteaValoresNulos(FECHA_PREPAGO);
        if(valoresNulos){
            logger.warning("Se setea el valor de la moneda en null para ejecutar el SP.");
            prepagoBean.setIdTcaTipoMoneda(null);
            //Se deselecciona el check pago total
            prepagoBean.setEsPagoTotalHeader(Boolean.FALSE);
            prepagoBean.setEsLecturaPagoTotal(Boolean.TRUE);
        }else{
            logger.warning("No se actualizaron los valores a nulos");
        }
        if (idTipoResolucion != null) {
            if ((idTipoResolucion.compareTo(PRE10_2008) == 0)) {
                //if (diaHabil(fechaPrepago)) {
                    
                validaFechaPrepago(fechaPrepagoBinding, fechaPrepago, fechaSolicitud, idTipoResolucion);
                    
                logger.log(ADFLogger.WARNING, "getTablaContratoUIC 5: ");
                if (null != getTablaContratoUIC()) {
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaContratoUIC());
                }
                //} else {
                //    fechaPrepagoBinding.setInputValue(null);
                //    resetValueDateComponent(uiFechaPrepago);
                //}
            } else if ((idTipoResolucion.compareTo(PRE28_2003) == 0)) {
                logger.log(ADFLogger.WARNING, "Entra a validar fecha con resolucion 2" + idTipoResolucion);
                try{
                    validaFechaPrepago(fechaPrepagoBinding, fechaPrepago, fechaSolicitud, idTipoResolucion);
                    logger.log(ADFLogger.WARNING, "getTablaContratoUIC 6: ");
                    if (null != getTablaContratoUIC()) {
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaContratoUIC());
                    }
                }catch(Exception e){
                    logger.log(ADFLogger.WARNING, "Error al ejecutar el metodo validaFechaPrepago." + e.getClass() + "." + e);
                }
                
            } else if (idTipoResolucion.compareTo(OTRAS_CONDICIONES) == 0) {
                //if (diaHabil(fechaPrepago)) {
                    logger.warning("Fecha de prepago valida.");
                //} else {
                //    logger.warning("Fecha de prepago invalida.");
                //    fechaPrepagoBinding.setInputValue(null);
                //    resetValueDateComponent(uiFechaPrepago);
                //}
            }
        } else {
            fechaPrepagoBinding.setInputValue(null);
            resetValueDateComponent(uiFechaPrepago);
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG12_INGRESAR_SOLICITUD_PREPAGO"));
        }
    }
    
    public void validaFechaPrepago(AttributeBinding fechaPrepagoBinding, Date fechaPrepago, Date fechaSolicitud, Integer idTipoResolucion) {
            logger.warning("inside validaFechaPrepago.");
        logger.warning("fechaPrepago: " + fechaPrepago);
        logger.warning("fechaSolicitud: " + fechaSolicitud);
        logger.warning("idTipoResolucion: " + idTipoResolucion);
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        PenalidadInteresesBean penalidadInteresesBean = (PenalidadInteresesBean) JSFUtils.resolveExpression("#{pageFlowScope.penalidadInteresesBean}");
        
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        
        Integer sector = prepagoBean.getSectorInstitucional();
        Integer tipoGarantia = prepagoBean.getTipoGarantia();
        Integer tipoCliente = tipoCliente(idTipoResolucion);
        Date fechaRequeridaNew = null;
        
        Timestamp fechaRenovacion = null;
        Timestamp fechaPrepagoAux = null;
        
        if(null != fechaPrepago) {
            fechaPrepagoAux = new Timestamp(fechaPrepago.getTime());
            //fechaPrepago.setInputValue(null);
        }else{
            logger.warning("La fecha de prepago es nula.");
        }
        if(null != JSFUtils.resolveExpression("#{bindings.FechaRenovacion.inputValue}")){
            fechaRenovacion = (Timestamp)JSFUtils.resolveExpression("#{bindings.FechaRenovacion.inputValue}");
        }else{
            logger.warning("La fecha de renovacion es nula.");
        }
        Long idOperacion = (null != prepagoBean.getCodigoOperacion()) ? 
            prepagoBean.getCodigoOperacion().longValue() : null;
        Integer idTcaTipoResolucion =(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}")) ? 
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}") : null;
        Integer idTcaTipoMoneda =(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}")) ? 
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}") : null;
        
        logger.log(ADFLogger.WARNING, "Valor del sector." + sector);
        if (fechaSolicitud != null && fechaPrepago != null && sector != null) {
            Calendar fechaRequerida = Calendar.getInstance();
            fechaRequerida.setTime(fechaSolicitud);
            
            /* RN_01 En la RESOLUCIÓN No. PRE-10/2008 el prestatario deberá notificar al BCIE, por medio de la respectiva Gerencia de País, su intención de efectuar un prepago, con una anticipación mínima de treinta (30) días hábiles, cuando se trate de financiamientos al sector público con garantía soberana, en relación con la fecha en que pretenda efectuar el prepago.
             *       El sistema deberá validar que los días entre la fecha de solicitud y la fecha de prepago cumplan con el párrafo anterior.
             * EX01 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-10/2008) para financiamientos al sector público con garantía soberana del estado.
             * MSG_01 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 30 días hábiles a la fecha del prepago.
             *
             * RN_02 En la RESOLUCIÓN No. PRE-10/2008 o posterior el prestatario deberá notificar al BCIE, por medio de la respectiva Gerencia de País, su intención de efectuar un prepago, con una anticipación mínima de diez (10) días hábiles, cuando se trate de financiamientos al sector privado e instituciones del sector público sin garantía soberana del Estado, en relación con la fecha en que pretenda efectuar el prepago.
             *       El sistema deberá validar que los días entre la fecha de solicitud y la fecha de prepago cumplan con el párrafo anterior.
             * EX02 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-10/2008) para financiamientos al sector privado e instituciones del sector público sin garantía soberana del Estado.
             * MSG_02 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 10 días hábiles a la fecha del prepago.
             */
            if (tipoGarantia != null && tipoCliente != null) {
                if ((tipoCliente.compareTo(CLIENTE_PRE10_2008_PUBLICO_SOBERANO)) == 0 || (tipoCliente.compareTo(CLIENTE_PRE10_2008_PRIVADO_PUBLICO_NO_SOBERANO) == 0)) {
                    logger.warning("idTipoResolucion: " + idTipoResolucion);
                    logger.warning("sector: " + sector);
                    
                    if (idTipoResolucion.compareTo(PRE10_2008) == 0) {
                        fechaRequerida = fechaMaxima(fechaRequerida, diasNotificacion(idTipoResolucion));
                        fechaRequeridaNew = fechaRequerida.getTime();
                    } else if (idTipoResolucion.compareTo(PRE28_2003) == 0) {
                        fechaRequeridaNew = fechaMaximaAllDays(fechaRequerida, diasNotificacion(idTipoResolucion));    
                    }
                        
                    if ((fechaPrepago.compareTo(fechaRequeridaNew) >= 0)) {
                        //RN_07 La fecha prepago aceptada por el sistema debe ser mayor a la ingresada en el campo "Fecha solicitud".
                        if (fechaPrepago.compareTo(fechaSolicitud) > 0) {
                            logger.warning("Fecha de prepago valida.");
                            
                            //Se invoca store SP_CONTRATO_DESEMBOLSO
                            logger.warning("Valor de la moneda : " + prepagoBean.getIdTcaTipoMoneda());
                            prepagoBean.consultarContratosDesembolsos(prepagoBean.getCodigoOperacion().longValue(),
                                                                      idTipoResolucion,
                                                                      prepagoBean.getIdTcaTipoMoneda(),
                                                                      null,
                                                                      prepagoBean.getFechaRenovacion(),
                                                                      new Timestamp(fechaPrepago.getTime()));
                        } else {
                            logger.warning("Fecha de prepago no valida, es menor a Fecha de solicitud.");
                            fechaPrepagoBinding.setInputValue(null);
                            resetValueDateComponent(uiFechaPrepago);
                        }
                    } else {
                        fechaPrepagoBinding.setInputValue(null);
                        resetValueDateComponent(uiFechaPrepago);
                        if (tipoCliente.compareTo(CLIENTE_PRE10_2008_PUBLICO_SOBERANO) == 0) {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG01_INGRESAR_SOLICITUD_PREPAGO"));
                        } else if (tipoCliente.compareTo(CLIENTE_PRE10_2008_PRIVADO_PUBLICO_NO_SOBERANO) == 0) {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG02_INGRESAR_SOLICITUD_PREPAGO"));
                        }
                    }
                }else{
                    logger.warning("El tipo de cliente no es el asignado.");
                }
            } else {
                logger.log(ADFLogger.WARNING,
                           "No se recibio valor para tipo de garantia o tipo de cliente. :" + tipoGarantia + "::" +
                           tipoCliente);
            }
            
            /*
             * RN_03 En la RESOLUCIÓN No. PRE-28/2003  el prestatario deberá notificar al BCIE, por medio de la Gerencia Regional del respectivo país, su intención de efectuar un prepago, con una anticipación de treinta (30) días hábiles, para el sector público, en relación con la fecha en que debería efectuar su próxima amortización.
             *       El sistema deberá validar que los días entre la fecha de solicitud y la fecha de prepago cumplan con el párrafo anterior.
             * EX03 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación requerida. - Fecha de Prepago (PRE-28/2003) para financiamientos al sector público.
             * MSG_03 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 30 días hábiles a la fecha de la próxima amortización.
             *
             * RN_04 En la RESOLUCIÓN No. PRE-28/2003  el prestatario deberá notificar al BCIE, por medio de la Gerencia Regional del respectivo país, su intención de efectuar un prepago, con una anticipación de cinco (5) días hábiles, para el sector privado, en relación con la fecha en que debería efectuar su próxima amortización.
             *       El sistema deberá validar que los días entre la fecha de solicitud y la fecha de prepago cumplan con el párrafo anterior. 
             * EX04 Identifica que la fecha de notificación para efectuar un prepago es mayor a la fecha mínima de anticipación  requerida. - Fecha de Prepago (PRE-28/2003) para financiamientos al sector privado.
             * MSG_04 Es necesario que la notificación al BCIE de realizar un prepago sea con una anticipación de 5 días hábiles a la fecha de la próxima amortización.
             */
            if (null != tipoCliente) {
                if ((tipoCliente.compareTo(CLIENTE_PRE28_2003_PUBLICO) == 0) ||
                    (tipoCliente.compareTo(CLIENTE_PRE28_2003_PRIVADO) == 0)) {
                    logger.warning("idTipoResolucion: " + idTipoResolucion);
                    logger.warning("sector: " + sector);

                    if (idTipoResolucion.compareTo(PRE10_2008) == 0) {
                        fechaRequerida = fechaMaxima(fechaRequerida, diasNotificacion(idTipoResolucion));
                        fechaRequeridaNew = fechaRequerida.getTime();

                        if ((fechaPrepago.compareTo(fechaRequeridaNew) >= 0)) {
                            //RN_07 La fecha prepago aceptada por el sistema debe ser mayor a la ingresada en el campo "Fecha solicitud".
                            if (fechaPrepago.compareTo(fechaSolicitud) > 0) {
                                logger.warning("Fecha de prepago valida.");
                                
                                //Se invoca store SP_CONTRATO_DESEMBOLSO
                                prepagoBean.consultarContratosDesembolsos(prepagoBean.getCodigoOperacion().longValue(),
                                                                          idTipoResolucion,
                                                                          prepagoBean.getIdTcaTipoMoneda(),
                                                                          null,
                                                                          prepagoBean.getFechaRenovacion(),
                                                                          new Timestamp(fechaPrepago.getTime()));
                            } else {
                                logger.warning("Fecha de prepago no valida, es menor a Fecha de solicitud.");
                                fechaPrepagoBinding.setInputValue(null);
                                resetValueDateComponent(uiFechaPrepago);
                            }
                        } else {
                            fechaPrepagoBinding.setInputValue(null);
                            resetValueDateComponent(uiFechaPrepago);
                            if (tipoCliente.compareTo(CLIENTE_PRE28_2003_PUBLICO) == 0) {
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG03_INGRESAR_SOLICITUD_PREPAGO"));
                            } else if (tipoCliente.compareTo(CLIENTE_PRE28_2003_PRIVADO) == 0) {
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG04_INGRESAR_SOLICITUD_PREPAGO"));
                            }
                        }
                    } else if (idTipoResolucion.compareTo(PRE28_2003) == 0) {
                        fechaRequeridaNew = fechaMaximaAllDays(fechaRequerida, diasNotificacion(idTipoResolucion));
                        Date fechaAmortizacion = null;
                        Boolean existeRegistro = Boolean.FALSE;
                        if (getCodigoTarea().compareTo(FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE) == 0 ||
                            getCodigoTarea().compareTo(FenixConstants.PA12_GESTIONAR_PREPAGO_CLIENTE_147) == 0) {
                            obtenerFechaAmortizacion();
                            fechaAmortizacion = penalidadInteresesBean.getFechaAmortizacion();
                            //fechaAmortizacion = penalidadInteresesBean.getFechaAmortizacion();
                            logger.warning("fechaAmortizacion: " + fechaAmortizacion);
                        } else {
                            fechaAmortizacion = fechaAmortizacion();
                        }
                        try{
                        logger.warning("Valor de la fecha de prepago aux :" + fechaPrepagoAux);
                            if ((fechaPrepago.compareTo(fechaRequeridaNew) >= 0)) {
                                //RN_07 La fecha prepago aceptada por el sistema debe ser mayor a la ingresada en el campo "Fecha solicitud".
                                if (fechaPrepago.compareTo(fechaSolicitud) > 0) {
                                    logger.warning("Seejecuta metodo para invocar SP de contrato de desembolso.");
                                    prepagoBean.consultarContratosDesembolsos(idOperacion, idTcaTipoResolucion,
                                                                              idTcaTipoMoneda, null, fechaRenovacion,
                                                                              fechaPrepagoAux);
                                    
                                    existeRegistro = validaFinalizaDiferenciaFecha();
                                    if(existeRegistro){
                                        logger.warning("Existe una fecha de amortizacion");
                                    }else{
                                        logger.warning("No existe una fecha de amortizacion.");
                                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG07_INGRESAR_SOLICITUD_PREPAGO"));
                                        fechaPrepagoBinding.setInputValue(null);
                                        resetValueDateComponent(uiFechaPrepago);
                                    }
                                } else {
                                    logger.warning("Fecha de prepago no valida, es menor a Fecha de solicitud.");
                                    fechaPrepagoBinding.setInputValue(null);
                                    resetValueDateComponent(uiFechaPrepago);
                                } 
                            } else {
                                fechaPrepagoBinding.setInputValue(null);
                                resetValueDateComponent(uiFechaPrepago);
                                if (tipoCliente.compareTo(CLIENTE_PRE28_2003_PUBLICO) == 0) {
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG03_INGRESAR_SOLICITUD_PREPAGO"));
                                } else if (tipoCliente.compareTo(CLIENTE_PRE28_2003_PRIVADO) == 0) {
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG04_INGRESAR_SOLICITUD_PREPAGO"));
                                }
                            }
                        }catch(Exception e){
                            logger.log(ADFLogger.WARNING, "Error al actualizar la fecha de prepago." + e.getClass() + "." + e);
                        }
                    }
                }
            } else {
                logger.log(ADFLogger.WARNING, "No se esta recibiendo un tipo de cliente." + tipoCliente);
            }
            
            // FNXII-6265
            // Se agrega validación faltante para la RN_02
            // cuando se trate de financiamientos al sector privado
            if (idTipoResolucion.compareTo(PRE10_2008) == 0 && sector.compareTo(SECTOR_PRIVADO) == 0 && tipoGarantia == null) {
                fechaRequerida = fechaMaxima(fechaRequerida, ANTICIPACION_PRE10_2008_SECTOR_PRIVADO);
                fechaRequeridaNew = fechaRequerida.getTime();
                
                if ((fechaPrepago.compareTo(fechaRequeridaNew) >= 0)) {
                    //RN_07 La fecha prepago aceptada por el sistema debe ser mayor a la ingresada en el campo "Fecha solicitud".
                    if (fechaPrepago.compareTo(fechaSolicitud) > 0) {
                        logger.warning("Fecha de prepago valida.");
                        
                        //Se invoca store SP_CONTRATO_DESEMBOLSO
                        prepagoBean.consultarContratosDesembolsos(prepagoBean.getCodigoOperacion().longValue(),
                                                                  idTipoResolucion,
                                                                  prepagoBean.getIdTcaTipoMoneda(),
                                                                  null,
                                                                  prepagoBean.getFechaRenovacion(),
                                                                  new Timestamp(fechaPrepago.getTime()));
                    } else {
                        logger.warning("Fecha de prepago no valida, es menor a Fecha de solicitud.");
                        fechaPrepagoBinding.setInputValue(null);
                        resetValueDateComponent(uiFechaPrepago);
                    }
                } else {
                    fechaPrepagoBinding.setInputValue(null);
                    resetValueDateComponent(uiFechaPrepago);
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG02_INGRESAR_SOLICITUD_PREPAGO"));
                }
            }
        }
        //Se ejcuta SP_CONTRATO_DESEMBOLSO cuando la fecha del prepago es nula.
        if(null == fechaPrepago){
            if (idTipoResolucion.compareTo(PRE28_2003) == 0){
                try{
                    logger.warning("Se ejecuta metodo para invocar SP de contrato de desembolso.");
                    prepagoBean.consultarContratosDesembolsos(idOperacion, idTcaTipoResolucion, idTcaTipoMoneda, null,
                                                              fechaRenovacion, fechaPrepagoAux);
                    
                }catch(Exception e){
                    logger.log(ADFLogger.WARNING, "Error al actualizar la fecha de prepago.", e); 
                }                
            }
        }
    }
    
    public void resetValueDateComponent(RichInputDate uiValue) {
        logger.warning("inside resetValueDateComponent.");
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        uiValue.resetValue();
        adfFacesContext.addPartialTarget(uiValue);
        logger.warning("resetValueDateComponent correctamente");
    }
    
    public void resetValueSelectOneChoiceComponent(RichSelectOneChoice uiValue) {
        try{
            logger.warning("inside resetValueSelectOneChoiceComponent.");
            AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
            uiValue.resetValue();
            adfFacesContext.addPartialTarget(uiValue);
            logger.warning("resetValueSelectOneChoiceComponent correctamente");
        }
        catch(Exception exp) 
        {
                logger.warning("error en resetValueSelectOneChoiceComponent");
                logger.warning("error: "+exp.getMessage());
        }
        
    }
    
    public void resetValueOneRadioComponent(RichSelectOneRadio uiValue) {
        try{
            logger.warning("inside resetValueOneRadioComponent.");
            AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
            uiValue.resetValue();
            adfFacesContext.addPartialTarget(uiValue);
            logger.warning("resetValueOneRadioComponent correctamente");
        }
        catch(Exception exp) 
        {
                logger.warning("error en resetValueOneRadioComponent");
                logger.warning("error: "+exp.getMessage());
        }
    }
    
    /**
     * Obtiene Fecha Maxima en la cual se puede realizar un prepago sin tomar en cuenta los dias inhabiles
     * @param fechaInicial contiene fecha inicial a la cual se suman los dias habiles
     * @param dias contiene cantidad de dias habiles a sumar dependiendo de la resolucion y sector
     * @return devuelve Calendar
     */
    public Calendar fechaMaxima(Calendar fechaInicial, Integer dias) {
        logger.entering(this.getClass().getName(),"fechaMaxima",new Object[]{fechaInicial,dias});
        
        HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}");
        Integer diasHabiles = 0;
        Integer cantidadDiasInhabiles = 0;
        Boolean fechaBusqueda = Boolean.FALSE;
        List<Date> diasInhabiles = new ArrayList<Date>();
        diasInhabiles = (ArrayList) holidaysBean.getHoliDay();
        Integer idTipoResolucion = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoResolucion");
        
        logger.warning("Lista dias: " + diasInhabiles.size());
        if (diasInhabiles != null) {
            if (diasInhabiles.size() > 0) {
                while (diasHabiles < dias || fechaBusqueda) {
                    
                    for (Date fechaInhabil : diasInhabiles) {
                        
                        if (fechaInhabil.compareTo(fechaInicial.getTime()) == 0) {
                            logger.warning("la fecha inicial :" + fechaInicial.getTime() + " es igual a fecha inhabil : " + fechaInhabil);
                            fechaBusqueda = Boolean.TRUE;  
                            diasHabiles--;
                            cantidadDiasInhabiles++;
                        } else {
                            
                            fechaBusqueda = Boolean.FALSE;
                        }
                    }
                    
                    if ((!fechaBusqueda) && (diasHabiles < dias)) {
                        diasHabiles++;   
                    }
                    
                    fechaInicial.add(Calendar.DATE, 1);
                }
            } else {
                if (idTipoResolucion != null) {
                    if ((idTipoResolucion.compareTo(PRE10_2008) == 0)) {
                        fechaInicial = fechaMaximaWeekend(fechaInicial, dias);
                    }    
                }
            }
            
        }
        
        logger.warning("Fecha maxima para solicitar el prepago: " + fechaInicial.getTime());
        logger.warning("dias habiles: " + diasHabiles);
        logger.warning("dias inhabiles encontrados: "+cantidadDiasInhabiles);
        
        
        logger.exiting(this.getClass().getName(),"fechaMaxima",fechaInicial);
        return (fechaInicial);
    }
    
    /**
     * Obtiene Fecha Maxima en la cual se puede realizar un prepago sin tomar en cuenta Sabados y Domingos.
     * @param fechaInicial contiene fecha inicial a la cual se suman los dias.
     * @param dias contiene cantidad de dias a sumar dependiendo de la resolucion y sector
     * @return Calendar
     */
    public Calendar fechaMaximaWeekend(Calendar fechaInicial, Integer dias) {
        logger.warning("Inside fechaMaximaWeekend");
        logger.warning("fechaInicial: " + fechaInicial);
        logger.warning("dias: " + dias);
        
        Integer diasHabiles = 0;

        while (diasHabiles < dias) {

            if (diaHabil(fechaInicial)) {
                diasHabiles++;
            }

            fechaInicial.add(Calendar.DATE, 1);
        }
        
        return fechaInicial;
    }
    
    public Integer diasNotificacion(Integer idTipoResolucion) {
        logger.warning("inside diasNotificacion.");
        logger.warning("idTipoResolucion: " + idTipoResolucion);
        
        Integer result = null;
        
        if (idTipoResolucion != null) {
            try {
                if (tipoCliente(idTipoResolucion).compareTo(CLIENTE_PRE10_2008_PUBLICO_SOBERANO) == 0) {
                    result = ANTICIPACION_PRE10_2008_SECTOR_PUBLICO;
                }

                if (tipoCliente(idTipoResolucion).compareTo(CLIENTE_PRE10_2008_PRIVADO_PUBLICO_NO_SOBERANO) == 0) {
                    result = ANTICIPACION_PRE10_2008_SECTOR_PRIVADO;
                }

                if (tipoCliente(idTipoResolucion).compareTo(CLIENTE_PRE28_2003_PUBLICO) == 0) {
                    result = ANTICIPACION_PRE28_2003_SECTOR_PUBLICO;
                }

                if (tipoCliente(idTipoResolucion).compareTo(CLIENTE_PRE28_2003_PRIVADO) == 0) {
                    result = ANTICIPACION_PRE28_2003_SECTOR_PRIVADO;
                }
            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "Error al obtener la anticipacion de dias para el prepago." + e);
            }
        } else {
            logger.warning("idTipoResolucion nulo.");
        }
        logger.log(ADFLogger.WARNING, "numero de dias :" + result);
        return (result);
    }
    
    public Integer tipoCliente(Integer idTipoResolucion) {
        logger.warning("inside tipoCliente.");
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        Integer sector = prepagoBean.getSectorInstitucional();
        Integer tipoGarantia = prepagoBean.getTipoGarantia();
        Integer result = null;
        
        logger.warning("idTipoResolucion: " + idTipoResolucion);
        logger.warning("sector: " + sector);
        logger.warning("tipoGarantia: " + tipoGarantia);
        
        if (idTipoResolucion != null && sector != null && tipoGarantia != null) {
            try {
                if (idTipoResolucion.compareTo(PRE10_2008) == 0 && sector.compareTo(SECTOR_PUBLICO) == 0 &&
                    tipoGarantia.compareTo(GARANTIA_SOBERANA) == 0) {
                    result = CLIENTE_PRE10_2008_PUBLICO_SOBERANO;
                }

                if ((idTipoResolucion.compareTo(PRE10_2008) == 0) && (sector.compareTo(SECTOR_PRIVADO) == 0) ||
                    ((sector.compareTo(SECTOR_PUBLICO) == 0) && (tipoGarantia.compareTo(GARANTIA_NO_SOBERANA) == 0))) {
                    result = CLIENTE_PRE10_2008_PRIVADO_PUBLICO_NO_SOBERANO;
                }
                //Se agrega validaciones para obtener el tipo de cliente para la pre28/2003 cuando existe
                //un tipo de garantia y se atiende FNXII-6171 y FNXII-6173.
                if (idTipoResolucion.compareTo(PRE28_2003) == 0 && sector.compareTo(SECTOR_PUBLICO) == 0) {
                    result = CLIENTE_PRE28_2003_PUBLICO;
                }

                if (idTipoResolucion.compareTo(PRE28_2003) == 0 && sector.compareTo(SECTOR_PRIVADO) == 0) {
                    result = CLIENTE_PRE28_2003_PRIVADO;
                }
            } catch (Exception e) {
                logger.log(ADFLogger.WARNING,
                           "Error al obtener el tipo de cliente cuando se tiene un tipo de garantia." + e);
            }
        } else {
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
        
        logger.warning("result: " + result);
        return (result);
    }
    
    /**
     * Obtiene Fecha Maxima en la cual se puede realizar un prepago contando tomando en cuenta dias habiles
     * @param fechaInicial contiene fecha inicial a la cual se suman los dias habiles
     * @param dias contiene cantidad de dias habiles a sumar dependiendo de la resolucion y sector
     * @return devuelve Date
     */
    public Date fechaMaximaAllDays(Calendar fechaInicial, Integer dias) {
        logger.warning("inside fechaMaximaAllDays.");
        
        for (int diaActual = 0; diaActual < dias; diaActual++) {
            fechaInicial.add(Calendar.DATE, 1);
        }
        logger.warning("Fecha maxima para solicitar el prepago: " + fechaInicial.getTime());
        logger.warning("dias habiles: " + dias);
        return (fechaInicial.getTime());
    }
    
    /**
     * Obtiene cadena valor del Resource Bundle
     * @param psKey contiene clave de bundle
     * @return devuelve cadena
     */
    private String getStringFromBundle(String psKey) {
        logger.warning("inside getStringFromBundle.");
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (null != resourceBundle) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }
    
    public Boolean diaHabil(Calendar fechaEspecifica) {
        logger.warning("inside diaHabil.");
        
        Boolean result = Boolean.FALSE;
        
        //Si el dia de la semana de la fechaInicial es diferente de Sabado y Domingo
        if (fechaEspecifica.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && fechaEspecifica.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            result = Boolean.TRUE;
        }
        
        return (result);
    }
    
    public void diasInhabilesMoneda() {
        logger.warning("inside diasInhabilesMoneda.");
        
        HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}");
        BindingContainer bindings = getBindings();
        //String descripcionMoneda = obtenerDescripcionMoneda();
        String descripcionMoneda = descripcionMoneda();
        
        OperationBinding operationBindingValidar = bindings.getOperationBinding("obtenerDiasInhabilesMoneda");
        logger.log(ADFLogger.WARNING, "VALOR descripcionMoneda (diasInhabilesMoneda): "+descripcionMoneda);
        operationBindingValidar.getParamsMap().put("descripcionMoneda", descripcionMoneda);
        
        operationBindingValidar.execute();
        
        if (null != operationBindingValidar.getResult()) {
            setDiasInhabilesMoneda((ArrayList) operationBindingValidar.getResult());
        } else{
            logger.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
        }
        
        holidaysBean.setHoliDay(getDiasInhabilesMoneda());
    }
    
    public String obtenerDescripcionMoneda() {
        String result = null;
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}"); 
        List<SelectItem> listaTipoMoneda = new ArrayList<SelectItem>(); 
        listaTipoMoneda = prepagoBean.getListaTipoMoneda(); 
        if (listaTipoMoneda != null) {
            result = listaTipoMoneda.get(0).getLabel();    
        }
        
        logger.warning("descripcionMoneda: " + result);
        
        return (result);
    }

    public Map validaCamposFinalizar() {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Map<String, Object> map = new HashMap<String, Object>();
        OperationBinding operationBinding = null;
        try {
            operationBinding = bindings.getOperationBinding("validaCamposFinalizar");
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                map = (Map) operationBinding.getResult();
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al ejecutar validaCamposFinalizar " + e);
        }

        return map;
    }
    
    public Boolean insertarGestionarCoberturas(){
        logger.warning("inside insertarGestionarCoberturas.");
        Boolean result = Boolean.FALSE;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        operationBinding = bindings.getOperationBinding("insertarFormularioGestionarCobertura");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            result = Boolean.FALSE;
        } else {
            result = Boolean.TRUE;
        }
        return (result);
    }
    
    public Boolean validarComisionPrepago(){
        Boolean isComision = Boolean.FALSE;
        logger.log(ADFLogger.WARNING, "INTO buscarComisionById");
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idPrepago = null;
        Long idComision = null;
        
        try{
            if(null != prepagoBean.getIdPrepago()){
                idPrepago = prepagoBean.getIdPrepago();
                logger.log(ADFLogger.WARNING, "Valor de Id Linea de credito.." + idPrepago);
            }
            operationBinding = bindings.getOperationBinding("obtenerComisionPrepagoByIdPrepago");
            operationBinding.getParamsMap().put("idPrepago", idPrepago);
            operationBinding.execute();
            idComision = (Long)operationBinding.getResult();
            logger.log(ADFLogger.WARNING, "Valor retorno de la comision es ..." + idComision);
            operationBinding = null;
            operationBinding = bindings.getOperationBinding("buscarComisionPrepagoPorId");
            operationBinding.getParamsMap().put("id", idComision);
            operationBinding.execute();
            isComision = (Boolean)operationBinding.getResult();
          
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en buscarComisionById." + e.getClass() + "." + e.getMessage());
        }
        logger.log(ADFLogger.ERROR, "Valor de retorno al inicializar la pantalla." + isComision);
        return isComision;
    }
    
    public void validarComisionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "INTO validarComisionValueChangeListener.");
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean aplicaCargo = null;
        if(null != valueChangeEvent.getNewValue()){
        aplicaCargo = (Boolean)(valueChangeEvent.getNewValue());
        logger.log(ADFLogger.WARNING, "Valor de Aplica cargo.." + aplicaCargo);
        }else{
            logger.log(ADFLogger.WARNING, "Valor de Aplica cargo es nulo.." + aplicaCargo);
        }
        if(aplicaCargo == Boolean.TRUE){
              prepagoBean.setIsValidaComision(Boolean.FALSE);
              logger.log(ADFLogger.WARNING, "Valor Monto....." + prepagoBean.getIsValidaComision());
          }else{
            prepagoBean.setIsValidaComision(Boolean.TRUE);
            logger.log(ADFLogger.WARNING, "Valor Monto.." + prepagoBean.getIsValidaComision());
        }
    }
    
    public void validarCalendario() {
        logger.warning("inside validarCalendario.");
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        Integer idTcaTipoResolucion = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoResolucion");
        
        asignarDiasInhabiles();
        
        if ((idTcaTipoResolucion.compareTo(PRE10_2008) == 0 || idTcaTipoResolucion.compareTo(OTRAS_CONDICIONES) == 0)) {
            logger.warning("Deshabilita fines de semana en calendario.");
            HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}");
            prepagoBean.setDisabledDaysValue(holidaysBean);
            diasInhabilesMoneda();
        } else if ((idTcaTipoResolucion.compareTo(PRE28_2003) == 0)) {
            logger.warning("Habilita fines de semana en calendario.");
            prepagoBean.setDisabledDaysValue(null);
        }   
        
        
    }

    public void asignarDiasInhabiles() {
        logger.warning("inside validarCalendario.");
                
        HolidaysBean holidaysBean = (HolidaysBean) JSFUtils.resolveExpression("#{pageFlowScope.holidaysBean}");
        BindingContainer bindings = getBindings();
        String descripcionMoneda = descripcionMoneda();

        OperationBinding operationBindingValidar = bindings.getOperationBinding("obtenerDiasInhabilesMoneda");
        operationBindingValidar.getParamsMap().put("descripcionMoneda", descripcionMoneda);
        operationBindingValidar.execute();

        if (null != operationBindingValidar.getResult()) {
            setDiasInhabilesMoneda((ArrayList) operationBindingValidar.getResult());
        } else {
            logger.log(ADFLogger.WARNING, "Valor operationBindingValidar.getResult() es Nulo.");
        }

        holidaysBean.setHoliDay(getDiasInhabilesMoneda());
    }
    
    public String descripcionMoneda() {
        logger.warning("inside descripcionMoneda.");
        
        Integer idTcaTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
        
        logger.warning("idTcaTipoMoneda: " + idTcaTipoMoneda);
        BindingContainer bindings = getBindings();
        String descripcionMoneda = null;
        
        try{
            OperationBinding operationBindingValidar = bindings.getOperationBinding("obtenerDescripcionMoneda");
            operationBindingValidar.getParamsMap().put("idTipoMoneda", idTcaTipoMoneda);
            operationBindingValidar.execute();
    
            if (null != operationBindingValidar.getResult()) {
                descripcionMoneda = (String) operationBindingValidar.getResult();
            } 
            logger.warning("descripcionMoneda respuesta: " + descripcionMoneda);
        }
        catch(Exception exp)
        {
            logger.warning("descripcionMoneda error: es");
            logger.warning("descripcionMoneda error 1: " + exp.toString()); 
            logger.warning("descripcionMoneda error 2: " + exp.getMessage() ); 
        } 
        logger.warning("termino descripcionMoneda.");
        return (descripcionMoneda);
    }
    
    public void obtenerFechaAmortizacion() {
        logger.warning("Inside obtenerFechaAmortizacion.");
        PenalidadInteresesBean penalidadInteresesBean = (PenalidadInteresesBean) JSFUtils.resolveExpression("#{pageFlowScope.penalidadInteresesBean}");
        
        Date fechaAmortizacion = penalidadInteresesBean.getFechaAmortizacion();
        
        if (fechaAmortizacion == null) {
            BindingContainer bindings = getBindings();
            
            OperationBinding operationB = bindings.getOperationBinding("fechaAmortizacion");
            operationB.execute();
            if (!operationB.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationB.getErrors().toString());
            } else if (operationB.getResult() != null) {
                penalidadInteresesBean.setFechaAmortizacion((Date) operationB.getResult());
            }    
        }
    }
    
    public void setUiFechaSolicitud(RichInputDate uiFechaSolicitud) {
        this.uiFechaSolicitud = uiFechaSolicitud;
    }

    public RichInputDate getUiFechaSolicitud() {
        return uiFechaSolicitud;
    }

    public void setUiFechaPrepago(RichInputDate uiFechaPrepago) {
        this.uiFechaPrepago = uiFechaPrepago;
    }

    public RichInputDate getUiFechaPrepago() {
        return uiFechaPrepago;
    }

    public void setInitListaTipoMoneda(RichOutputText initListaTipoMoneda) {
        this.initListaTipoMoneda = initListaTipoMoneda;
    }

    public RichOutputText getInitListaTipoMoneda() {
        //llenar ListaTipoMoneda
        this.llenarCatalogoTipoDeMoneda();
        return initListaTipoMoneda;
    }

    public void setPopupActualizarPrepago(RichPopup popupActualizarPrepago) {
        this.popupActualizarPrepago = popupActualizarPrepago;
    }

    public RichPopup getPopupActualizarPrepago() {
        return popupActualizarPrepago;
    }

    public void setPopupActualizarFechaPrepago(RichPopup popupActualizarFechaPrepago) {
        this.popupActualizarFechaPrepago = popupActualizarFechaPrepago;
    }

    public RichPopup getPopupActualizarFechaPrepago() {
        return popupActualizarFechaPrepago;
    }

    public String actualizarFechaPrepagoPopup() {
        popupActualizarFechaPrepago();
        return null;
    }
    
    public void setDiasInhabilesMoneda(List<Date> diasInhabilesMoneda) {
        this.diasInhabilesMoneda = diasInhabilesMoneda;
    }

    public List<Date> getDiasInhabilesMoneda() {
        return diasInhabilesMoneda;
    }
    
    public void popupActualizarFechaPrepago() {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupActualizarFechaPrepago.show(hints);
        validarCalendario();
    }

    public String cancelarActualizarFechaPrepago() {
        logger.warning("Cancela Actualizar Fecha de Prepago.");
        popupActualizarFechaPrepago.hide();
        
        return null;
    }

    public void setPopupDetalleComision(RichPopup popupDetalleComision) {
        this.popupDetalleComision = popupDetalleComision;
    }

    public RichPopup getPopupDetalleComision() {
        return popupDetalleComision;
    }

    public String invocarDetalleComisionPopup() {
        logger.log(ADFLogger.WARNING, "INTO invocarDetalleComisionPopup");

        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        GestionComisionActionBean gestionComisionActionBean =
            (GestionComisionActionBean) JSFUtils.resolveExpression("#{gestionComisionActionBean}");

        Long idComision = null;
        Long idPrepago = null;
        Boolean isComision = Boolean.FALSE;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        try {
            DCIteratorBinding condicionIncumplimientoVOIterator = null;
            ViewObject consultarComisionPrepagoVO = null;

            condicionIncumplimientoVOIterator =
                ADFUtils.getDCBindingContainer().findIteratorBinding("ConsultarComisionPrepagoVOIterator");
            consultarComisionPrepagoVO = condicionIncumplimientoVOIterator.getViewObject();
            Row row = consultarComisionPrepagoVO.getCurrentRow();
            if (null != row.getAttribute("IdComision")) {
                idComision = (Long) row.getAttribute("IdComision");
            } else {
                logger.log(ADFLogger.WARNING, "El id de la comision es nulo");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al seleccionar el row de la comision." + e.getClass() + "." + e);
        }
        
        idPrepago = prepagoBean.getIdPrepago();
        logger.log(ADFLogger.WARNING, "Valor retorno del idPrepago es ..." + idPrepago + "Comision :" + idComision);
        
        try {
            operationBinding = bindings.getOperationBinding("buscarComisionPrepagoPorId");
            operationBinding.getParamsMap().put("id", idComision);
            operationBinding.execute();
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al obtener el detalle de la comision." + e.getClass() + "." + e);
        }
        
        if (null != operationBinding.getResult()) {
            isComision = (Boolean) operationBinding.getResult();
        } else {
            logger.log(ADFLogger.WARNING, "El objeto operationBinding.getResult() es nulo");
        }
        gestionComisionActionBean.cargarPantallaComision();

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupDetalleComision.show(hints);
        return null;
    }

    public String aceptarDetalleComision() {
        popupDetalleComision.hide();
        return null;
    }
    
    public Boolean validarAjustesPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de validarAjustesPrepago con parametro idPrepago : "+idPrepago);
        Boolean ocurrioCambioEnPrepago = Boolean.FALSE;
        Boolean ocurrioCambioEnContratosDesembolso = Boolean.FALSE;
        Boolean ocurrioCambio = Boolean.FALSE;
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("validarCambiosPrepago");
            operationBinding.getParamsMap().put("idPrepago",idPrepago);
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                ocurrioCambioEnPrepago = (Boolean) operationBinding.getResult();
            } 
        
            operationBinding = bindings.getOperationBinding("validarCambiosContratoDesembolsoPrepago");
            operationBinding.getParamsMap().put("idPrepago",idPrepago);
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                ocurrioCambioEnContratosDesembolso = (Boolean) operationBinding.getResult();
            } 
        
            if(!ocurrioCambioEnPrepago && !ocurrioCambioEnContratosDesembolso){
                ocurrioCambio = Boolean.FALSE;
            }else{
                if(ocurrioCambioEnPrepago){
                    logger.log(ADFLogger.WARNING, "El cambio fue en prepago.");
                }
                if(ocurrioCambioEnContratosDesembolso){
                    logger.log(ADFLogger.WARNING, "El cambio fue en contrato de desembolso.");
                }
                //Si ocurrio cualquier cambio regresar la bandera en TRUE
                ocurrioCambio = Boolean.TRUE;
            }
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Exception in validarAjustesPrepago" + e.getClass() +"." + e.getMessage());
        } 
        return ocurrioCambio;
    }
    
    public Boolean eliminarDatosPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de eliminarDatosPrepago con parametro idPrepago : "+idPrepago);
        Boolean resultado = Boolean.FALSE;
        Boolean eliminoCorrectoCalculoIntereses = Boolean.FALSE;
        Boolean eliminoDetallePenalidad = Boolean.FALSE;
        Boolean eliminoTrePrepagoContrato = Boolean.FALSE;
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("eliminarCalculoInteresesByIdPrepago");
            operationBinding.getParamsMap().put("idPrepago",idPrepago);
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                eliminoCorrectoCalculoIntereses = (Boolean) operationBinding.getResult();
            }
            
            operationBinding = bindings.getOperationBinding("eliminarDetallePenalidadByIdPrepago");
            operationBinding.getParamsMap().put("idPrepago",idPrepago);
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else if (operationBinding.getResult() != null) {
                eliminoDetallePenalidad = (Boolean) operationBinding.getResult();
            }
            
            if(eliminoCorrectoCalculoIntereses && eliminoDetallePenalidad){
                operationBinding = bindings.getOperationBinding("eliminarTrePrepagoContratoByIdPrepago");
                operationBinding.getParamsMap().put("idPrepago",idPrepago);
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                } else if (operationBinding.getResult() != null) {
                    eliminoTrePrepagoContrato = (Boolean) operationBinding.getResult();
                    resultado = eliminoTrePrepagoContrato;
                }
            }else{
                if(!eliminoCorrectoCalculoIntereses){
                    logger.log(ADFLogger.ERROR, "No se elimino correctamente registros de CalculoIntereses.");
                }
                
                if(!eliminoDetallePenalidad){
                    logger.log(ADFLogger.ERROR, "No se elimino correctamente registros de DetallePenalidad.");
                }
                
                resultado =  Boolean.FALSE;
            }
            
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Exception in eliminarDatosPrepago" + e.getClass() +"." + e.getMessage());
        }
        
        return resultado;
    }
    
    public Boolean actualizarPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de actualizarPrepago con parametro idPrepago : "+idPrepago);
        Boolean resultado = null;
        
        //recuperar los valores de la pantalla
        BigDecimal montoPrepago =(null != JSFUtils.resolveExpression("#{bindings.MontoPrepago.inputValue}")) ?
            (BigDecimal)JSFUtils.resolveExpression("#{bindings.MontoPrepago.inputValue}") : null;
        logger.warning("montoPrepago : "+montoPrepago);
        
        Timestamp fechaRenovacion =(null != JSFUtils.resolveExpression("#{bindings.FechaRenovacion.inputValue}")) ?
            (Timestamp)JSFUtils.resolveExpression("#{bindings.FechaRenovacion.inputValue}") : null;
        logger.warning("fechaRenovacion : "+fechaRenovacion);
        
        Integer idTcaTipoCaptura =(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoCaptura.inputValue}")) ?
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoCaptura.inputValue}") : null;
        logger.warning("idTcaTipoCaptura : "+idTcaTipoCaptura);
        
        //se agregan nuevos atributos para atender la incidencia "FNXII-4848"
        Timestamp fechaSolicitud = (null != JSFUtils.resolveExpression("#{bindings.FechaSolicitud.inputValue}")) ?
            (Timestamp)JSFUtils.resolveExpression("#{bindings.FechaSolicitud.inputValue}") : null;
        logger.warning("fechaSolicitud : "+fechaSolicitud);
        
        Integer idTcaTipoResolucion = (null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}")) ?
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}") : null;
        logger.warning("idTcaTipoResolucion : "+idTcaTipoResolucion);
        
        Timestamp fechaPrepago = (null != JSFUtils.resolveExpression("#{bindings.FechaPrepago.inputValue}")) ?
            (Timestamp)JSFUtils.resolveExpression("#{bindings.FechaPrepago.inputValue}") : null;
        logger.warning("fechaPrepago : "+fechaPrepago);
        
        Integer idTcaTipoPrepago = (null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoPrepago.inputValue}")) ?
            (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoPrepago.inputValue}") : null;
        logger.warning("idTcaTipoPrepago : "+idTcaTipoPrepago);
        
        Integer idTcaTipoMoneda = (null != JSFUtils.resolveExpression("#{bindings.IdMoneda1.inputValue}")) ?
            (Integer)JSFUtils.resolveExpression("#{bindings.IdMoneda1.inputValue}") : null;
        logger.warning("idTcaTipoMoneda : "+idTcaTipoMoneda);
        
        
        try{

            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("actualizarPrepago");
            operationBinding.getParamsMap().put("montoPrepago",montoPrepago);
            operationBinding.getParamsMap().put("fechaRenovacion",fechaRenovacion);
            operationBinding.getParamsMap().put("idTcaTipoCaptura",idTcaTipoCaptura);
            operationBinding.getParamsMap().put("idPrepago",idPrepago);
            //se agregan nuevos atributos para atender la incidencia "FNXII-4848"
            operationBinding.getParamsMap().put("fechaSolicitud",fechaSolicitud);
            operationBinding.getParamsMap().put("idTcaTipoResolucion",idTcaTipoResolucion);
            operationBinding.getParamsMap().put("fechaPrepago",fechaPrepago);
            operationBinding.getParamsMap().put("idTcaTipoPrepago",idTcaTipoPrepago);
            operationBinding.getParamsMap().put("idTcaTipoMoneda",idTcaTipoMoneda);
            
            operationBinding.execute();
            
            if(null != operationBinding){
                if (!operationBinding.getErrors().isEmpty()) {
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                    resultado = Boolean.FALSE;
                }else{
                    resultado = (Boolean) operationBinding.getResult();
                }
            }
            
        }catch(Exception e){
            logger.severe("Exception in actualizarPrepago", e);
            resultado = Boolean.FALSE;
        }
        logger.warning("actualizarPrepago resultado :"+resultado);
        return resultado;
    }
    
    public void insertarContratoDesembolso(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de insertarContratoDesembolso con parametro idPrepago : "+idPrepago);
        try{
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("insertarContratoDesembolso");
            operationBinding.getParamsMap().put("idPrepago",idPrepago);
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            }
            
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Exception in insertarContratoDesembolso" + e.getClass() +"." + e.getMessage());
        }
    }
    
    public void asignarBanderasBpmRealizarAjustesSolicitudPrepago(){
        Boolean esRequeridoMasInformacion = Boolean.TRUE; 
        actualizarPayloadElement("requiereMasInfoCOPRES", esRequeridoMasInformacion);
        actualizarPayloadElement("requiereMasInfoDAECI", esRequeridoMasInformacion);
        actualizarPayloadElement("requiereMasInfoMDC", esRequeridoMasInformacion);
        actualizarPayloadElement("requiereMasInfoSEPRI", esRequeridoMasInformacion);
        actualizarPayloadElement("requiereMasInfoCOFI", esRequeridoMasInformacion);
    }
    
    private void selecionarTodasLineas() {
        logger.log(ADFLogger.WARNING, "selecionarTodasLineas");
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        
        DCIteratorBinding voContratoDesembolsoPrepago = ADFUtils.getDCBindingContainer().findIteratorBinding("ContratoDesembolsoPrepagoVOIterator");
        
        Row[] contratoDesembolsoPrepago = voContratoDesembolsoPrepago.getAllRowsInRange();
        List<String> listaLineaCredito = new ArrayList<>();
        List<String> listaContratoDesembolso = new ArrayList<>();
        for(Row contrato: contratoDesembolsoPrepago) {
            String lineaCreditoFlexCube = (String)contrato.getAttribute("LineaCreditoFlexcube");
            String contratoDesembolsoFlexcube = (String)contrato.getAttribute("ContratoDesembolsoFlexcube");
            if (null != lineaCreditoFlexCube) {
                logger.log(ADFLogger.WARNING, "add lineaCreditoFlexCube: "+lineaCreditoFlexCube);
                listaLineaCredito.add(lineaCreditoFlexCube);
            }
            
            if (null != contratoDesembolsoFlexcube) {
                logger.log(ADFLogger.WARNING, "add contratoDesembolsoFlexcube: "+contratoDesembolsoFlexcube);
                listaContratoDesembolso.add(contratoDesembolsoFlexcube);
            }
            
        }
        
        prepagoBean.setListaLineaCredito(listaLineaCredito);
        prepagoBean.setListaContratoDesembolso(listaContratoDesembolso);
        
        refrescarTaskFlows();
    }
    
    private void refrescarTaskFlows() {
        try {
            PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
            prepagoBean.getRegionConsultarInformacionBinding().refresh(FacesContext.getCurrentInstance());
            prepagoBean.getRegionCondicionEspecialBinding().refresh(FacesContext.getCurrentInstance());
            logger.log(ADFLogger.WARNING, "succesfull contratoDesembolsoPrepagoSelectionListener");
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al actualizar TaskFlows." + e.getClass() + "." + e);
        }
    }
    /**Metodo para validar la fecha de calculo definitivo
     *@param 
     * @return isFechaValida
     */
    public Boolean validarFechaCalculoDefinitivo(){
        logger.log(ADFLogger.WARNING, "Entra en validarFechaCalculoDefinitivo.");
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        OperationBinding operationBinding = null;
        
        Map<String,Object> map = new HashMap<String,Object>();
        Boolean isFechaValida = Boolean.FALSE;
        Integer idTcaTipoResolucion = null;
        Integer diasRequeridos = null;
        Long idPrepago = null;
        Timestamp fechaCalculoDefinitivo = null;
        Timestamp fechaPrepago = null;
        //Timestamp fechaNueva = null;
        
        if (null != ADFUtils.getBoundAttributeValue("IdTcaTipoResolucion")) {
            idTcaTipoResolucion = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoResolucion");
            logger.log(ADFLogger.WARNING, "Tipo de Resolucion :" + idTcaTipoResolucion);
        } else {
            logger.log(ADFLogger.WARNING, "El valor de la resolucion es nula.");
        }
        if (null != prepagoBean.getIdPrepago()) {
            idPrepago = prepagoBean.getIdPrepago();
            logger.log(ADFLogger.WARNING, "Id Prepago :" + idPrepago);
        } else {
            logger.log(ADFLogger.WARNING, "El valor del prepago es nulo.");
        }
        if (null != prepagoBean.getFechaPrepago()) {
            fechaPrepago = prepagoBean.getFechaPrepago();
        } else {
            logger.log(ADFLogger.WARNING, "La fecha de prepago es nula.");
        }
//        try{
//        //Se obtienen dias requeridos para el pago del prepago.
//        diasRequeridos = diasNotificacion(idTcaTipoResolucion);
//        logger.log(ADFLogger.WARNING, "Dias requeridos :" + diasRequeridos);
//        }catch(Exception e){
//            logger.log(ADFLogger.WARNING, "Error al obtener los dias requeridos." + e);
//        }
        try{
            //Se obtiene la fecha de calculo definitivo
            BindingContainer bindings = getBindings();
            operationBinding = bindings.getOperationBinding("obtenerCargoPrepagoByIdPrepago");
            operationBinding.getParamsMap().put("idPrepago", idPrepago);
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                map = (Map)operationBinding.getResult();
                    fechaCalculoDefinitivo = (Timestamp)map.get("fechaVigentePenalizacion");
            }else{
                logger.log(ADFLogger.WARNING, "No se obtuvieron valores del resultado.");
            }
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error al obtener la fecha de Calculo definitivo." + e.getClass() + e);
        }
        try {
            
            //Se valida si se cumple con la fecha para el pago del prepago
            logger.log(ADFLogger.WARNING, "Fecha Prepago :" + fechaPrepago);
            logger.log(ADFLogger.WARNING, "Fecha calculo definitivo :" + fechaCalculoDefinitivo);
            if(null != fechaCalculoDefinitivo){
            if ((fechaCalculoDefinitivo.compareTo(fechaPrepago) == 0 ||
                 fechaCalculoDefinitivo.compareTo(fechaPrepago) == -1)) {
                logger.log(ADFLogger.WARNING, "Se puede realizar el prepago.");
                isFechaValida = Boolean.TRUE;
            } else {
                logger.log(ADFLogger.WARNING,
                           "El prepago aun no se puede realizar ya que la fecha calculo definitivo aun no se ha cumplido");
            }
            }else{
                logger.log(ADFLogger.WARNING, "La fecha de calculo definitivo es nula.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error al calcular la fecha de calculo definitivo");
        }
        logger.log(ADFLogger.WARNING, "Valida si se realiza el prepago :" + isFechaValida);
        return isFechaValida;
    }

    public void setInitListaDiasHabiles(RichOutputText initListaDiasHabiles) {
        this.initListaDiasHabiles = initListaDiasHabiles;
    }

    public RichOutputText getInitListaDiasHabiles() {
        this.precargarDiasInhabiles();
        return initListaDiasHabiles;
    }
    
    public void actualizarObservacionPrepago(Long idObservacion, String observacion){
        logger.warning("Dentro actualizarObservacionPrepago idObservacion : "
                                +idObservacion + "  observacion : "+observacion);
        OperationBinding operationBinding = null;
        try{
            //Se obtiene la fecha de calculo definitivo
            BindingContainer bindings = getBindings();
            operationBinding = bindings.getOperationBinding("actualizarObservacionPrepago");
            operationBinding.getParamsMap().put("idObservacion", idObservacion);
            operationBinding.getParamsMap().put("observacion", observacion);
            operationBinding.execute();
            if(!operationBinding.getErrors().isEmpty()){
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } 
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error en actualizarObservacionPrepago",e);
        }
        
    }
    
    public void fechaRenovacionValueChangeListener(ValueChangeEvent vce){
        logger.warning("Entra en fechaRenovacionValueChangeListener");
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        OperationBinding operationBinding = null;
        BindingContainer bindings = getBindings();
        Timestamp fechaRenovacion = null;
        Integer idOperacion = null;
        Integer idTcaTipoResolucion = null;
        Integer idTcaTipoMoneda = null;
        Integer monedaActual=null;
        Timestamp fechaPrepago = null;
        
        try{
            vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
            if(null != vce.getNewValue()){
                fechaRenovacion = (Timestamp)vce.getNewValue();
                prepagoBean.setFechaRenovacion(fechaRenovacion);
                logger.warning("fecha renovacion :" + fechaRenovacion);
            }else{
                prepagoBean.setFechaRenovacion(null);
                logger.warning("la fecha renovacion es nula.");
            }
            if(null != prepagoBean.getCodigoOperacion()){
                idOperacion = prepagoBean.getCodigoOperacion().intValue();
            }else{
                logger.warning("La operacion es nula");
            }
            if(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}")){
                idTcaTipoResolucion = (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoResolucion.inputValue}");
            }else{
                logger.warning("La resolucion es nula.");
            }
            if(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}")){
                monedaActual = (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}");
            }
            //Se comenta linea de codigo ya que seteaba el valor de la moneda en null, se atiende FNXII-6137
            //ADFUtils.setBoundAttributeValue("IdTcaTipoMoneda", null);
            if(null != JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}")){
                idTcaTipoMoneda = (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTipoMoneda.inputValue}");
            }else{
                logger.warning("La moneda es nula.");
            }
            
            if(null != ADFUtils.findControlBinding("FechaPrepago")) {
                fechaPrepago = (Timestamp)JSFUtils.resolveExpression("#{bindings.FechaPrepago.inputValue}");
            }else{
                logger.warning("La fecha de prepago es nula.");
            }
            
            if(null != idOperacion){
                prepagoBean.consultarContratosDesembolsos(idOperacion.longValue(), idTcaTipoResolucion, idTcaTipoMoneda, null,
                                                          fechaRenovacion, fechaPrepago);
            }else{
                JSFUtils.addFacesErrorMessage("No se puede ejecutar la consulta por idOperacion no puede ser null.");
            }
            //se ejecuta el llenado de la lista de tipos de monedas
            //FNXII-7143
            this.llenarCatalogoTipoDeMoneda();
            
        }catch(Exception e){
            logger.warning("Error en fechaRenovacionValueChangeListener");
        }
       // monedaUIC.setValue(null);
       // monedaUIC.resetValue();
       //cambiarMoneda(monedaActual);
       logger.warning("Se actualiza la tabla de contratos");
        logger.log(ADFLogger.WARNING, "getTablaContratoUIC 7: ");
        if (null != getTablaContratoUIC()) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaContratoUIC());
        }
    }

    public void setMonedaUIC(RichSelectOneChoice monedaUIC) {
        this.monedaUIC = monedaUIC;
    }

    public RichSelectOneChoice getMonedaUIC() {
        return monedaUIC;
    }
    
    public void cambiarMoneda(Integer moneda){
        logger.warning("Entrar a reingresar la moneda  ");
            DCIteratorBinding iter = ADFUtils.findIterator("ContratoDesembolsoPrepagoVOIterator");
            if(iter.getEstimatedRowCount()>0){
                if(null!=moneda){
                        logger.warning("se ingresa nuevamente la moneda  ");
                        ADFUtils.setBoundAttributeValue("IdTcaTipoMoneda", moneda);
                    }
                else{
                        logger.warning("la moneda  es nula");
                    }
                }
            else{
                    logger.warning("no existen registros de contrato desembolso");
                }
        }

    public void setTablaContratoUIC(RichTable tablaContratoUIC) {
        this.tablaContratoUIC = tablaContratoUIC;
    }

    public RichTable getTablaContratoUIC() {
        return tablaContratoUIC;
    }

    public void setTipoResolucionUI(RichSelectOneChoice tipoResolucionUI) {
        this.tipoResolucionUI = tipoResolucionUI;
    }

    public RichSelectOneChoice getTipoResolucionUI() {
        return tipoResolucionUI;
    }
    
    public Date getFechaInicioPre2008() {
        Date fechaInicioPre2008 = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2008);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 6);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        fechaInicioPre2008 = new Date(calendar.getTime().getTime());
        
        return fechaInicioPre2008;
    }
    
    public Date getFechaVigenciaPre2003() {
        Date fechaInicioPre2003 = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getFechaInicioPre2008().getTime());
        calendar.add(Calendar.DATE, -1);

        fechaInicioPre2003 = new Date(calendar.getTime().getTime());
        
        return fechaInicioPre2003;
    }

    public void setFechaInicioPre2008(Date fechaInicioPre2008) {
        this.fechaInicioPre2008 = fechaInicioPre2008;
    }

    public void setFechaVigenciaPre2003(Date fechaVigenciaPre2003) {
        this.fechaVigenciaPre2003 = fechaVigenciaPre2003;
    }

    public void setTreeContratoUIC(RichTreeTable treeContratoUIC) {
        this.treeContratoUIC = treeContratoUIC;
    }

    public RichTreeTable getTreeContratoUIC() {
        return treeContratoUIC;
    }
    
    public void quitarProximasFechasActionEvent(ActionEvent actionEvent) {
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        prepagoBean.removerContratoDesembolsoPorRangoFechasPrepago(getIdPadreSeleccionado());
    }

    public String getContratoFlexSeleccionado() {
        return contratoFlexSeleccionado;
    }

    public void setContratoFlexSeleccionado(String contratoFlexSeleccionado) {
        this.contratoFlexSeleccionado = contratoFlexSeleccionado;
    }

    public Long getIdPadreSeleccionado() {
        return idPadreSeleccionado;
    }

    public void setIdPadreSeleccionado(Long idPadreSeleccionado) {
        this.idPadreSeleccionado = idPadreSeleccionado;
    }
    
    public void calcularCargoTramitePRE2008() {
        logger.warning("Inside calcularCargoTramitePRE2008.");
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        
        Integer idTcaTipoResolucion = prepagoBean.getIdTcaTipoResolucion();
        Integer idTcaTipoMoneda = prepagoBean.getIdTcaTipoMoneda();
        BigDecimal resultadoConversion = null;
        String cargoTramitePrepagoAux = null;
        String descripcionMonedaPrepago = null;
        
        logger.log(ADFLogger.WARNING, "idTcaTipoResolucion :" + idTcaTipoResolucion);
        logger.log(ADFLogger.WARNING, "Id de la moneda :" + idTcaTipoMoneda);
        try{
            String moneda_desc = prepagoBean.moneda_desc;
            descripcionMonedaPrepago=moneda_desc;
            logger.log(ADFLogger.WARNING, "Descripcion de la moneda 1:" + moneda_desc);
        }
        catch(Exception exp){
                logger.log(ADFLogger.WARNING, "Descripcion de la moneda 1 error:" + exp.getMessage() );
        }
        //descripcionMonedaPrepago = "USD";//descripcionMoneda();
        //descripcionMonedaPrepago = prepagoBean.obtenerDescripcionMoneda(idTcaTipoMoneda);
        logger.log(ADFLogger.WARNING, "Descripcion de la moneda 2:" + descripcionMonedaPrepago);
        
        //Cargo por trámite de prepago
        if (idTcaTipoResolucion.compareTo(FenixConstants.PRE10_2008) == 0){
            logger.warning("Entra a la opcion PRE-10/2008");
            //Recuperar la lista de plazos,para determinar si se calcula un cargo por tramite de prepago para PRE-10/2008
            //Retorna true se calcula el cargo por tramite de prepago
            if(calcularCargoPorTramiteContrato()){
                logger.warning("Se calcula cargo por tramite de prepago, plazo es mayor o igual 1");
                //Se mueve validacion de dolares por incidencia FNXII-4302
                if (!(idTcaTipoMoneda.compareTo(FenixConstants.TIPO_MONEDA_DOLAR) ==0 )) {
                    logger.log(ADFLogger.WARNING, "Entra en tipo de moneda 1");
                    //Para la Resolución PRE-10/2008 el sistema debe realizar la conversión de 500 dólares a la moneda
                    //del contrato de desembolso. Aplica cuando el prepago sea en una moneda diferente a dólares.
                    resultadoConversion = prepagoBean.convertirDolaresPorTipoMoneda(idTcaTipoMoneda);
                    cargoTramitePrepagoAux = resultadoConversion+ " " + descripcionMonedaPrepago + " (500 USD)";
                    prepagoBean.setCargoTramitePrepago(cargoTramitePrepagoAux);
                    //llenar variables para guardar el monto y el tipo de moneda
                    prepagoBean.setMontoCargoTramite(resultadoConversion);
                    logger.log(ADFLogger.WARNING, "Valor de monto Cargo :" + prepagoBean.getMontoCargoTramite());
                    prepagoBean.setIdTcaTipoMonedaTramite(idTcaTipoMoneda);
                } else {
                    // Mantenemos los valores originales por la incidencia FNXII-4302
                    prepagoBean.setIdTcaTipoMonedaTramite(idTcaTipoMoneda);
                    prepagoBean.setMontoCargoTramite(new BigDecimal(500)); // Por defecto el cargo por tramite de prepago es 500 USD
                    prepagoBean.setCargoTramitePrepago(MONTO_CARGO_DEFECTO+ " " + descripcionMonedaPrepago + " (500 USD)"); // Por defecto el cargo por tramite de prepago es 500 USD
                    logger.log(ADFLogger.WARNING, "Valor de monto Cargo :" + prepagoBean.getMontoCargoTramite());
                }
            }else{
                prepagoBean.setMontoCargoTramite(new BigDecimal(0));
                logger.warning("No se calculo cargo por tramite de prepago, ya que los plazos son menores a 1");
            }
            
            asignarMontoCargoTramite(prepagoBean.getMontoCargoTramite());
            asignarIdTcaTipoMonedaTramite(prepagoBean.getIdTcaTipoMonedaTramite());
        }
    }
    
    public Boolean calcularCargoPorTramiteContrato(){
        logger.warning("Dentro de calcularCargoPorTramiteContrato");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean result = Boolean.FALSE;
        List<BigDecimal> listaPlazo = new ArrayList<BigDecimal>();
        BigDecimal valueComparate = new BigDecimal(1);
        try{
            operationBinding = bindings.getOperationBinding("obtenerListaPlazoContrato");
            operationBinding.execute();
            if(null != operationBinding.getResult()){
                listaPlazo = (List<BigDecimal>) operationBinding.getResult();
                for(BigDecimal plazoAux : listaPlazo){ 
                    logger.warning("obtenerListaPlazoContrato plazoAux:"+plazoAux.toString());
                    logger.warning("obtenerListaPlazoContrato valueComparate"+valueComparate.toString());
                    if(plazoAux.compareTo(valueComparate)== 1){
                        logger.warning("Se encontro plazo mayor a "+valueComparate.toString());
                        result = Boolean.TRUE;
                        break;
                    }
                    else
                    {                        
                        logger.warning("No Se encontro plazo mayor a "+valueComparate.toString());
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

    public void setCalculoUIC(RichOutputText calculoUIC) {
        this.calculoUIC = calculoUIC;
    }

    public RichOutputText getCalculoUIC() {
        logger.warning("Inside getCalculoUIC.");
        
        //calcularCargoTramitePRE2008();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getGroupCargoTramiteUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCargoTramiteUIC());
        
        return calculoUIC;
    }
    
    public void asignarMontoCargoTramite(BigDecimal montoCargoTramiteValue) {
        logger.warning("Inside asignarMontoCargoTramite.");
        logger.warning("montoCargoTramiteValue: " + montoCargoTramiteValue);
        
        AttributeBinding montoCargoTramite;

        montoCargoTramite = ADFUtils.findControlBinding("MontoCargoTramite");

        montoCargoTramite.setInputValue(montoCargoTramiteValue);
    }
    
    public void asignarIdTcaTipoMonedaTramite(Integer idTcaTipoMonedaTramiteValue) {
        logger.warning("Inside asignarIdTcaTipoMonedaTramite.");
        logger.warning("idTcaTipoMonedaTramite: " + idTcaTipoMonedaTramiteValue);
        
        AttributeBinding idTcaTipoMonedaTramite;

        idTcaTipoMonedaTramite = ADFUtils.findControlBinding("IdTcaTipoMonedaTramite");

        idTcaTipoMonedaTramite.setInputValue(idTcaTipoMonedaTramiteValue);
    }
    
    public void setCargoTramiteUIC(RichOutputText cargoTramiteUIC) {
        this.cargoTramiteUIC = cargoTramiteUIC;
    }

    public RichOutputText getCargoTramiteUIC() {
        return cargoTramiteUIC;
    }

    public void setGroupCargoTramiteUIC(RichPanelGroupLayout groupCargoTramiteUIC) {
        this.groupCargoTramiteUIC = groupCargoTramiteUIC;
    }

    public RichPanelGroupLayout getGroupCargoTramiteUIC() {
        return groupCargoTramiteUIC;
    }

    public void setHeaderTablaContratoUIC(RichPanelHeader headerTablaContratoUIC) {
        this.headerTablaContratoUIC = headerTablaContratoUIC;
    }

    public RichPanelHeader getHeaderTablaContratoUIC() {
        return headerTablaContratoUIC;
    }
    
    public void calcular(ActionEvent ev){
        logger.warning("Inicio calcular"); 
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");

        java.sql.Timestamp VfechaSolicitud = ADFUtils.findControlBinding("FechaSolicitud") != null ? (java.sql.Timestamp) ADFUtils.findControlBinding("FechaSolicitud").getInputValue() : null;
        java.sql.Timestamp VfechaPrepago = ADFUtils.findControlBinding("FechaPrepago") != null ? (java.sql.Timestamp) ADFUtils.findControlBinding("FechaPrepago").getInputValue() : null;
        java.sql.Timestamp VfechaRenovacion = ADFUtils.findControlBinding("FechaRenovacion") != null ? (java.sql.Timestamp) ADFUtils.findControlBinding("FechaRenovacion").getInputValue() : null;
        BigDecimal VmontoPrepago = ADFUtils.findControlBinding("MontoPrepago") != null ? (BigDecimal) ADFUtils.findControlBinding("MontoPrepago").getInputValue() : null;
        Integer VidTcaTipoResolucion = ADFUtils.findControlBinding("IdTcaTipoResolucion") != null ? (Integer) ADFUtils.findControlBinding("IdTcaTipoResolucion").getInputValue() : null;
        Integer VidTcaTipoPrepago  = ADFUtils.findControlBinding("IdTcaTipoPrepago") != null ? (Integer) ADFUtils.findControlBinding("IdTcaTipoPrepago").getInputValue() : null;
        Integer VidTcaTipoCaptura = ADFUtils.findControlBinding("IdTcaTipoCaptura") != null ? (Integer) ADFUtils.findControlBinding("IdTcaTipoCaptura").getInputValue() : null;
        String VobservacionPrepago = ADFUtils.findControlBinding("Observacion") != null ? (String) ADFUtils.findControlBinding("Observacion").getInputValue() : null;
        Integer VidTcaTipoMoneda2 = ADFUtils.findControlBinding("IdTcaTipoMoneda") != null ? (Integer) ADFUtils.findControlBinding("IdTcaTipoMoneda").getInputValue() : null;
        Integer VidTcaTipoMoneda = ADFUtils.findControlBinding("IdMoneda1") != null ? (Integer) ADFUtils.findControlBinding("IdMoneda1").getInputValue() : null;
        Long VidOperacion = prepagoBean.getCodigoOperacion().longValue();
        Long VidPrepago = prepagoBean.getIdPrepago() != null? prepagoBean.getIdPrepago().longValue() : null;
        
        logger.warning("VfechSolicitud: " + VfechaSolicitud);
        logger.warning("VfechPrepago: " + VfechaPrepago);
        logger.warning("VfechRenovacion: " + VfechaRenovacion);
        logger.warning("VmontPrepago: " + VmontoPrepago);
        logger.warning("VidTcaTipoRes: " + VidTcaTipoResolucion);
        logger.warning("VidTcaTipoPrep: " + VidTcaTipoPrepago);
        logger.warning("VidTcaTipoCap: " + VidTcaTipoCaptura);
        logger.warning("VobservacionPrepago: " + VobservacionPrepago);
        logger.warning("VidTcaTipoMoneda: "+ VidTcaTipoMoneda);
        logger.warning("VidTcaTipoMoneda2: "+ VidTcaTipoMoneda2);
        logger.warning("VidOperacion: "+ VidOperacion);
        logger.warning("VidPrepago: "+ VidPrepago);
        
        if(VidTcaTipoMoneda == null && VidTcaTipoMoneda2 != null) {
                VidTcaTipoMoneda = VidTcaTipoMoneda2;
            logger.warning("cambiado VidTcaTipoMoneda: "+ VidTcaTipoMoneda);
        }
        try
        {
            logger.warning("In consultarContratosDesembolsos: ");
            prepagoBean.consultarContratosDesembolsos(VidOperacion, VidTcaTipoResolucion,VidTcaTipoMoneda, VidPrepago, VfechaRenovacion,VfechaPrepago);
            logger.log(ADFLogger.WARNING, "getTablaContratoUIC: ");
            if (null != getTablaContratoUIC()) {
                logger.log(ADFLogger.WARNING, "getTablaContratoUIC no es null");
                AdfFacesContext.getCurrentInstance().addPartialTarget(getTablaContratoUIC()); 
                prepagoBean.setEsPagoTotalHeader(Boolean.FALSE);
                prepagoBean.setEsLecturaPagoTotal(Boolean.TRUE);
                prepagoBean.setEsBtnCalcularBloqueado(Boolean.TRUE);
            } 
            logger.warning("Out consultarContratosDesembolsos: ");
        }
        catch(Exception ex)
        {
            logger.warning("calcular error: " + ex.getMessage());   
        }
         
        logger.warning("Finalizo calcular");  
    }
    
    public void nuevoCalculo(ActionEvent ev){
        logger.warning("Inicio nuevoCalculo"); 
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        prepagoBean.setEsBtnCalcularBloqueado(Boolean.FALSE); 
        logger.warning("Fin nuevoCalculo"); 
    }

    public void guardarPrepago(ActionEvent ev) throws WorkflowException {
        logger.warning("Inicio guardarPrepago");
        
        PrepagoBean prepagoBean = (PrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.prepagoBean}");
        logger.warning("Realizar ajustes a solicitud de prepago");
        Boolean realizarCambiosPrepago = validarAjustesPrepago(prepagoBean.getIdPrepago());
        Boolean eliminoCorrectamente = Boolean.FALSE;
        Boolean actualizoCorrecto = Boolean.FALSE;
        Long idObservacion = null;
        String observacion = null;
        
        try{
            idObservacion = (Long) ADFUtils.getBoundAttributeValue("IdObservacion");
        }catch(Exception e){
            logger.warning("Error al recuperar idObservacion "+e);
        }
        try{
            observacion = (String) ADFUtils.getBoundAttributeValue("Observacion");
        }catch(Exception e){
            logger.warning("Error al recuperar observacion "+e);
        }
         
        actualizarObservacionPrepago(idObservacion,observacion);
        
        if(realizarCambiosPrepago){ 
            eliminoCorrectamente = eliminarDatosPrepago(prepagoBean.getIdPrepago());
            if(eliminoCorrectamente){
                //invocar el metodo para actualizar el encabezado del prepago
                actualizoCorrecto = actualizarPrepago(prepagoBean.getIdPrepago());                        
                
                if(actualizoCorrecto){ 
                    insertarContratoDesembolso(prepagoBean.getIdPrepago());
                    JSFUtils.addFacesErrorMessage("Información del prepago guardada.");
                }else{
                    logger.warning("No es posible finalizar la tarea, el prepago no se actualizo.");
                    JSFUtils.addFacesErrorMessage("No es posible finalizar la tarea, el prepago no se actualizo.");
                  }
            }else{
                logger.warning("No es posible finalizar la tarea, la información no se elimino correctamente.");
                    JSFUtils.addFacesErrorMessage("No es posible finalizar la tarea, la información no se elimino correctamente.");
             }
            
        }else{
            logger.warning("No se realiazo ningun cambio en el prepago.");
                    JSFUtils.addFacesErrorMessage("No se realiazo ningun cambio en el prepago.");
        }
        logger.warning("Fin guardarPrepago");
    }
    
}
