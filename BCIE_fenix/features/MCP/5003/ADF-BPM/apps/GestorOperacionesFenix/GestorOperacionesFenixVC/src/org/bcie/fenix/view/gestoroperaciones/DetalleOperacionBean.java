package org.bcie.fenix.view.gestoroperaciones;

import com.bcie.xmlns.comisionservice.Comision;
import com.bcie.xmlns.comisionservice.ComisionPT;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.math.BigDecimal;

import java.net.URLEncoder;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.ColumnSelectionEvent;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;

import org.bcie.comisionmo.CrearAvisoCobroComisionRequestType;
import org.bcie.comisionmo.CrearAvisoCobroComisionResponseType;
import org.bcie.condicionmo.ConsultarCondicionByRolRequestType;
import org.bcie.condicionmo.ConsultarCondicionByRolResponseType;
import org.bcie.condicionmo.ConsultarCumplimientoCondicionRequestType;
import org.bcie.condicionmo.ConsultarCumplimientoCondicionResponseType;
import org.bcie.condicionservice.Condicion12BndQSService;
import org.bcie.condicionservice.CondicionPT;
import org.bcie.documentobo.Documento;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class DetalleOperacionBean implements Serializable {
    @SuppressWarnings("compatibility:7367430880238867186")
    private static final long serialVersionUID = 1L;

    private static final Integer PROCESO_ENMIENDAS = 10;
    private static final Integer PROCESO_LAFT = 7;
    private static final Integer PROCESO_SUPERVISION = 23;
    private static final Integer PROCESO_PREPAGO = 26;
    private static final Integer PROCESO_CIERRE = 19;
    private static final Integer PROCESO_EVALUACIONES = 6;
    private static final Integer PROCESO_EVALUACIONES_SEGUIMIENTO_SIEMAS = 31;
    private static final Integer PROCESO_CANCELAR = 11;
    private static final Integer PROCESO_APROBACION = 4;
    private static final Integer PROCESO_FORMALIZACION = 5;
    private static final Integer PROCESO_IMPLEMENTACION_PCT = 25;
    private static final Integer PROCESO_REGISTRAR_COMISION = 33;
    private static final String DOCUMENTO_FILE_NAME = "Reporte_de_condiciones.pdf";    
    private static final String FORMATO_FECHA_HORA = "yyyyMMdd_hhmmss";
    private static final String LOCALE_ESPAÑOL = "es";
    private static final String LOCALE_MX = "MX";
    
    private static final Long ESTADO_PROCESO = 1L;
    private static final Long ESTADO_SUSPENDIDO = 2L;
    private static final Long ESTADO_CANCELADO = 4L;
    private static final Long ESTADO_FINALIZADO = 3L;
    private static final Long ESTADO_CERRADO = 27L;

    private static final Integer PRODUCTO_FIDEICOMISO = 14;
    private static final Integer PRODUCTO_FONDO = 15;

    private static final Integer ID_TCA_TERMINO_FECHA_FIRMA_CONTRATO = 30;

    private static ADFLogger logger = null;
    private String mensajeConfirmacion = null;
    private String justificacion = null;
    private transient RichPopup popupConfirmarHeaderAction;
    private transient RichPopup popupCancelarHeaderAction;
    private transient RichPopup popupSuspenderHeaderAction;
    private GestorOperacionesBean gestorOperacionesBean = null;
    public static final String BUNDLE = "org.bcie.fenix.view.GestorOperacionesFenixVCBundle";
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(DetalleOperacionBean.class);
    private transient RichInputText justificacionIT;
    private RichPopup popupInicioCumplimientoCondicion;
    private RichPopup popupInicioCondicionCumplimiento;

    private RichTreeTable treeTable;
    private transient RichPopup popupSolicitarExcepcion;
    private transient RichPopup popupVerDetalleDesembolso;
    private transient RichButton btn_solicitarExcepcion;
    private transient RichButton btn_verDetalleDesembolso;
    private Boolean habilitarBotonExcepcion = null;
    private Boolean habilitarBotonVerDetalle = null;
    private static Long idOp = null;
    private static Long idContrato = null;
    private static Long idLinea = null;
    private static Long idTcaEstado = null;
    private static Long idSolicitud = null; 

    private RichPopup popUpGestionarCobroComisiones;
    private RichPopup popUpDeterminarFactibilidad;
    private RichPopup popUpFormalizacionParcial;
    private RichPopup popUpSplit;
    private RichPopup popUpRegistrarFinConcurso;
    private RichPopup popUpCambiarResponsable;
    private RichPopup popUpDescargaRepCondiciones;
    private RichOutputFormatted tasaTotalUIC;
    private RichCommandMenuItem determinarFactibilidadUIC;
    private RichInputText valorTasaUIC;

    private static final Integer REGLA_MORA = 1;
    private static final Integer REGLA_SCR = 2;
    private static final Integer REGLA_LIMITES = 6;
    private RichPopup popupLimites;
    
    private static final String REPORTE_VALIDACION_CONDICION_FILE_NAME = "Estado_condiciones_desembolso_";
    private RichPopup popupDescargarReporteValidacionCondicion;
    
    /** Popup con opciones para las formalizaciones de enmiendas genericas.*/
    private RichPopup popupConfirmacionEnmiendaGenerica;
    /** Popup con opciones para las formalizaciones de enmiendas genericas.*/
    
    private RichPopup popupConfirmacionEvaluaciones;
    /* Constantes de formalización de enmiendas */
    private static final String HEADER_ACTION_FORMENMIENDA_GENERICA = "solicitarFormalizacionEnmiendaGenerica";
    
    /* Banderas para las formalizaciones de enmiendas genéricas. */
    private boolean esRequiereCambiosCondicionesFinancieras = Boolean.FALSE;
    private boolean esDesobligacion = Boolean.FALSE;
    private boolean esCambioPlazo = Boolean.FALSE;
    
    /* Constantes de administracion de operacion */
    private static final String HEADER_ACTION_ADMINOPE_FENIX = "solicitarAdministracionOperacionFenix";
    
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacionIT(RichInputText justificacionIT) {
        this.justificacionIT = justificacionIT;
    }

    public RichInputText getJustificacionIT() {
        return justificacionIT;
    }

    public void setMensajeConfirmacion(String mensajeConfirmacion) {
        this.mensajeConfirmacion = mensajeConfirmacion;
    }

    public String getMensajeConfirmacion() {
        return mensajeConfirmacion;
    }

    public void setPopupConfirmarHeaderAction(RichPopup popupConfirmarHeaderAction) {
        this.popupConfirmarHeaderAction = popupConfirmarHeaderAction;
    }

    public RichPopup getPopupConfirmarHeaderAction() {
        return popupConfirmarHeaderAction;
    }


    public void setPopupCancelarHeaderAction(RichPopup popupCancelarHeaderAction) {
        this.popupCancelarHeaderAction = popupCancelarHeaderAction;
    }

    public RichPopup getPopupCancelarHeaderAction() {
        return popupCancelarHeaderAction;
    }

    public void setPopupSuspenderHeaderAction(RichPopup popupSuspenderHeaderAction) {
        this.popupSuspenderHeaderAction = popupSuspenderHeaderAction;
    }

    public RichPopup getPopupSuspenderHeaderAction() {
        return popupSuspenderHeaderAction;
    }
    /*  */

    public Boolean obtenerColumna(Integer producto, Integer columna) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerCampo");
        operationBinding.getParamsMap().put("idProducto", producto);
        operationBinding.getParamsMap().put("columna", columna);
        Boolean result = (Boolean) operationBinding.execute();
        return result;
    }

    public DetalleOperacionBean() {

        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }

        if (gestorOperacionesBean == null) {
            gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        }
    }

    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }
        return txt;
    }

    public void suspenderOperacionActionListener(ActionEvent actionEvent) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBindingProceso = null;
        operationBindingProceso = bindings.getOperationBinding("validaProcesos");
        operationBindingProceso.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBindingProceso.getParamsMap().put("proceso", PROCESO_CANCELAR);
        Boolean resultProceso = (Boolean) operationBindingProceso.execute();
        if (resultProceso) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELAR_OPERACION"));
        } else {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBinding.getParamsMap().put("idOperacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long result = (Long) operationBinding.execute();
            if (result == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (result == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (result == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {
                        logger.log(ADFLogger.TRACE,
                                   "Inside suspenderOperacionActionListener: " + actionEvent.getComponent().getId());
                        showPopupSuspenderHeaderAction();
                    }
                }
            }
        }
    }

    public void cancelarOperacionActionListener(ActionEvent actionEvent) {
        logger.warning("Inside cancelarOperacionActionListener.");
        Boolean inicioProcesoDesembolso = Boolean.FALSE;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBindingProceso = null;
        operationBindingProceso = bindings.getOperationBinding("validaProcesos");
        operationBindingProceso.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBindingProceso.getParamsMap().put("proceso", PROCESO_CANCELAR);
        Boolean resultProceso = (Boolean) operationBindingProceso.execute();
        if (resultProceso) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELAR_OPERACION"));
            logger.warning("La operacion se encuentra en un proceso activo de Cancelar Operacion.");
        } else {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBinding.getParamsMap().put("idOperacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long result = (Long) operationBinding.execute();
            if (result == ESTADO_CANCELADO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
            } else {
                if (result == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    //No se podrá cancelar la operacion si cuenta con un desembolso (contrato desembolsado).
                    LOGGER.warning("Validando si existe Contrato desembolsado.");
                    BindingContainer bindingsContratoDesembolsado = ADFUtils.getBindingContainer();
                    OperationBinding operationBindingContDesembolsado = null;
                    Boolean existeContratoDesembolsado = null;
                    
                    //Validar que la operacion no tenga ninguna solicitud asociada, eso indica que no a iniciado el 
                    //proceso desembolso entonces valido que mande el error al cancelar la operaion si ya esta formalizada
                    try{
                        operationBindingContDesembolsado =
                            bindingsContratoDesembolsado.getOperationBinding("validarOperacionEnProcesoDesembolso");
                        operationBindingContDesembolsado.getParamsMap().put("idOperacion",
                                                                        Long.toString(gestorOperacionesBean.getIdOperacion()));
                        inicioProcesoDesembolso = (Boolean) operationBindingContDesembolsado.execute();
                    }catch(Exception e){
                        logger.severe("Error en validarOperacionEnProcesoDesembolso : "+e);
                    }
                    if(inicioProcesoDesembolso){
                        logger.warning("La operacion ya inicio un proceso de desembolso, se omite la validacion de operacion formalizada");
                        try {
                            operationBindingContDesembolsado =
                                bindingsContratoDesembolsado.getOperationBinding("validarContratosDesembolsadosSolicitud");
                            operationBindingContDesembolsado.getParamsMap().put("idOperacion",
                                                                                Long.toString(gestorOperacionesBean.getIdOperacion()));
                            existeContratoDesembolsado = (Boolean) operationBindingContDesembolsado.execute();
                        } catch (Exception e) {
                            LOGGER.warning("ERROR al ejecutar OperationBinding validarContratosDesembolsadosSolicitud.", e);
                        }

                        LOGGER.warning("existeContratoDesembolsado: " + existeContratoDesembolsado);
                            if (!existeContratoDesembolsado) {
                                LOGGER.warning("Se muestra el popUp de cancelar operacion");
                                showPopupCancelarHeaderAction();
                            } else {
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_EXISTE_DESEMBOLSO"));
                            }
                    }else{
                    try {
                        operationBindingContDesembolsado =
                            bindingsContratoDesembolsado.getOperationBinding("validarContratosDesembolsadosSolicitud");
                        operationBindingContDesembolsado.getParamsMap().put("idOperacion",
                                                                            Long.toString(gestorOperacionesBean.getIdOperacion()));
                        existeContratoDesembolsado = (Boolean) operationBindingContDesembolsado.execute();
                    } catch (Exception e) {
                        LOGGER.warning("ERROR al ejecutar OperationBinding validarContratosDesembolsadosSolicitud.", e);
                    }

                    LOGGER.warning("existeContratoDesembolsado: " + existeContratoDesembolsado);
                    if (null != existeContratoDesembolsado) {
                        if (!existeContratoDesembolsado) {
                            //No se podrá cancelar la operacion si la operacion ya esta formalizada (Fecha de firma de contrato registrada).
                            LOGGER.warning("Validando si existe Termino de Firma de contrato.");
                            BindingContainer bindingsTermino = ADFUtils.getBindingContainer();
                            OperationBinding operationBindingTermino = null;
                            Boolean existeTermino = null;

                            try {
                                operationBindingTermino = bindingsTermino.getOperationBinding("validarExisteTermino");
                                operationBindingTermino.getParamsMap().put("idOperacion",
                                                                           Long.toString(gestorOperacionesBean.getIdOperacion()));
                                operationBindingTermino.getParamsMap().put("idTcaTermino",
                                                                           ID_TCA_TERMINO_FECHA_FIRMA_CONTRATO);
                                existeTermino = (Boolean) operationBindingTermino.execute();
                            } catch (Exception e) {
                                LOGGER.warning("ERROR al ejecutar OperationBinding validarExisteTermino.", e);
                            }

                            if (null != existeTermino) {
                                if (!existeTermino) {
                                    LOGGER.warning("La operacion no esta formalizada. Se mostrará Popup de CancelarOperacion.");
                                    showPopupCancelarHeaderAction();
                                } else {
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_FECHA_FIRMA_CONTRATO"));
                                }
                            } else {
                                LOGGER.warning("La validacion de la operacion formalizada regresa NULL. No se pudo validar correctamente.");
                                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_FECHA_FIRMA_CONTRATO"));
                            }
                        } else {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_EXISTE_DESEMBOLSO"));
                        }
                    } else {
                        LOGGER.warning("La validacion de contratos desembolsados regresa NULL. No se pudo validar correctamente.");
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_VALIDACION_EXISTE_DESEMBOLSO"));
                    }
                }
                }
            }
        }

        logger.warning("Termina cancelarOperacionActionListener.");
    }

    public void restablecerOperacionActionListener(ActionEvent actionEvent) {
        logger.warning("Inside restablecerOperacionActionListener.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validarEstadoOperacion");
        operationBinding.getParamsMap().put("idOperacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        Long result = (Long) operationBinding.execute();
        if (result == ESTADO_CANCELADO) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
        } else {
            if (result == ESTADO_FINALIZADO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
            } else {
                if (result == ESTADO_PROCESO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_PROCESO"));
                } else {
                    logger.log(ADFLogger.TRACE,
                               "Inside cancelarOperacionActionListener: " + actionEvent.getComponent().getId());
                    setMensajeConfirmacion("¿Confirma restablecer la operación " +
                                           gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                    showPopupConfirmarHeaderAction(); // Mostramos popup
                }
            }
        }
    }

    public void solicitarLAFTActionListener(ActionEvent actionEvent) {
        Boolean requiereLAFT = obtenerColumna(gestorOperacionesBean.getIdProducto(), FenixConstants.PC07_LAFT);

        if (requiereLAFT) {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("validaProcesos");
            operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
            operationBinding.getParamsMap().put("proceso", PROCESO_LAFT);
            Boolean result = (Boolean) operationBinding.execute();
            if (result) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_LAFT"));
            } else {
                BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
                OperationBinding operationBindingEstado = null;
                operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
                operationBindingEstado.getParamsMap().put("idOperacion",
                                                          Long.toString(gestorOperacionesBean.getIdOperacion()));
                Long resultEstado = (Long) operationBindingEstado.execute();

                if (resultEstado == ESTADO_SUSPENDIDO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
                } else {
                    if (resultEstado == ESTADO_FINALIZADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                    } else {
                        if (resultEstado == ESTADO_CANCELADO) {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                        } else {

                            logger.log(ADFLogger.TRACE,
                                       "Inside solicitarLAFTActionListener: " + actionEvent.getComponent().getId());
                            setMensajeConfirmacion("¿Confirma iniciar el ‘Proceso de LA-FT’ de la operación " +
                                                   gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                            showPopupConfirmarHeaderAction(); // Mostramos popup

                        }
                    }
                }
            }
        } else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_NO_LAFT"));
        }
    }

    public void solicitarEnmiendaActionListener(ActionEvent actionEvent) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.getParamsMap().put("proceso", PROCESO_ENMIENDAS);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ENMIENDAS"));
        } else {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long resultEstado = (Long) operationBindingEstado.execute();
            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {
                        logger.log(ADFLogger.TRACE,
                                   "Inside solicitarEnmiendaActionListener: " + actionEvent.getComponent().getId());
                        setMensajeConfirmacion("¿Esta seguro de iniciar el Proceso de Enmienda de la operación " +
                                               gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                        showPopupConfirmarHeaderAction(); // Mostramos popup
                    }
                }
            }
        }
    }

    public void solicitarEvaluacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inicia solicitarEvaluacionActionListener.");  
        String p_componente = actionEvent.getComponent().getId();
        BigDecimal p_tipo_proceso = new BigDecimal("0");
        p_tipo_proceso = (BigDecimal) JSFUtils.resolveExpression("#{bindings.CodigoTipoEvaluacion.inputValue}");  
        logger.log(ADFLogger.WARNING, "input_value: "+p_tipo_proceso.toString());
        gestorOperacionesBean.setTipoProcesoEvaluaciones(p_tipo_proceso.toString());
        Boolean vb_esAprobacionFinalizado = gestorOperacionesBean.getEsAprobacionFinalizado(); 
        String p_tipo_proceso_CODIGO = "";
        String p_tipo_proceso_DESCRIPCION = "";
        String p_tipo_proceso_ESTADO = ""; 

        if(!vb_esAprobacionFinalizado && (p_tipo_proceso.toString().equals("4") || p_tipo_proceso.toString().equals("5")))
        {
                logger.warning("La operación aún no esta aprobada."); 
                JSFUtils.addFacesErrorMessage("La operación aún no esta aprobada.");
        }
        else
        {
            try
            {
                logger.warning("Inicio vtaEvaluacionTipoVOIterator"); 
                BindingContext bindingctx = BindingContext.getCurrent();
                BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
                DCBindingContainer bindingsImpl =  (DCBindingContainer)bindings; 
                DCIteratorBinding dciter = bindingsImpl.findIteratorBinding("vtaEvaluacionTipoVOIterator");
                 
                for(int i=0; i < dciter.getEstimatedRowCount();  i++) 
                {
                    logger.warning("vtaEvaluacionTipoVOIterator i = " + i);
                    Row temp = dciter.getRowAtRangeIndex(i);
                    
                    p_tipo_proceso_CODIGO = temp.getAttribute(0).toString();
                    p_tipo_proceso_DESCRIPCION = temp.getAttribute(1).toString();
                    p_tipo_proceso_ESTADO = temp.getAttribute(2).toString();
                    
                    if(p_tipo_proceso.toString().equals(p_tipo_proceso_CODIGO)){
                        logger.warning("Elegido p_tipo_proceso_DESCRIPCION: " + p_tipo_proceso_DESCRIPCION);              
                        gestorOperacionesBean.setTipoProcesoEvaluacionesDescripcion(p_tipo_proceso_DESCRIPCION);
                        logger.warning("p_tipo_proceso_CODIGO: " + p_tipo_proceso_CODIGO);                    
                        logger.warning("p_tipo_proceso_DESCRIPCION: " + p_tipo_proceso_DESCRIPCION);                    
                        logger.warning("p_tipo_proceso_ESTADO: " + p_tipo_proceso_ESTADO);
                    }
                }    
            }
            catch(Exception exp)
            { 
                logger.log(ADFLogger.WARNING, " Error al obtener el id de evaluaciones por input_value: "+exp.getMessage()); 
            }
            solicitarEvaluacion(p_componente, p_tipo_proceso.toString()); 
        }      
        getPopupConfirmacionEvaluaciones().hide(); 
        
        logger.log(ADFLogger.WARNING, "Finaliza solicitarEvaluacionActionListener.");        
    }

    public void solicitarEvaluacionExAnteActionListener(ActionEvent actionEvent) {
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Boolean requiereSIEMAS = obtenerColumna(gestorOperacionesBean.getIdProducto(), FenixConstants.PC03_SIEMAS);
        Boolean requiereIBCIE = obtenerColumna(gestorOperacionesBean.getIdProducto(), FenixConstants.PC03_IBCIE);
        if (requiereIBCIE && requiereSIEMAS) {
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("validaProcesos");
            operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
            operationBinding.getParamsMap().put("proceso", PROCESO_EVALUACIONES);
            Boolean result = (Boolean) operationBinding.execute();
            if (result) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_EVALUACIONES"));
            } else {
                BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
                OperationBinding operationBindingEstado = null;
                operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
                operationBindingEstado.getParamsMap().put("idOperacion",
                                                          Long.toString(gestorOperacionesBean.getIdOperacion()));
                Long resultEstado = (Long) operationBindingEstado.execute();
                if (resultEstado == ESTADO_SUSPENDIDO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
                } else {
                    if (resultEstado == ESTADO_FINALIZADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                    } else {
                        if (resultEstado == ESTADO_CANCELADO) {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                        } else {
                            logger.log(ADFLogger.TRACE,
                                       "Inside solicitarEvaluacionActionListener: " +
                                       actionEvent.getComponent().getId());
                            setMensajeConfirmacion("¿Esta seguro de iniciar el proceso de evaluaciones de la operación " +
                                                   gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                            showPopupConfirmarHeaderAction(); // Mostramos popup
                        }
                    }
                }
            }
        } else {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_NO_EVALUACIONES"));
        }
    }

    private void showPopupConfirmarHeaderAction() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmarHeaderAction().show(popupHints);
    }

    private void showPopupCancelarHeaderAction() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupCancelarHeaderAction().show(popupHints);
    }

    private void showPopupSuspenderHeaderAction() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupSuspenderHeaderAction().show(popupHints);
    }

    private void showPopupLimitesHeaderAction() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupLimites().show(popupHints);
    }

    private void closePopupLimitesHeaderAction() {
        getPopupLimites().hide();
    }


    public void aceptarConfirmarActionListener(ActionEvent actionEvent) {
        logger.warning("Dentro de aceptarConfirmarActionListener");
        String headerAction = (String) JSFUtils.resolveExpression("#{viewScope.headerAction}");
        logger.warning("headerAction :"+headerAction);
        Boolean metodo = Boolean.FALSE;
        Boolean cerrar = Boolean.TRUE;

        // Invocamos acción correspondiente
        if ((headerAction != null) && (headerAction.trim().length() > 0)) {

            if (headerAction.equalsIgnoreCase("suspender")) {
                suspenderOperacion();
            }
            if (headerAction.equalsIgnoreCase("cancelar")) {
                cancelarOperacion();
            }

            if (headerAction.equalsIgnoreCase("solicitarLAFT")) {
                metodo = solicitarLAFT();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                }

            }
            if (headerAction.equalsIgnoreCase("solicitarCambiarResponsableOperacion")) {
                solicitarCambiarResponsableOperacion();

            }
            if (headerAction.equalsIgnoreCase("solicitarSupervision")) {
                metodo = solicitarSupervision();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                }

            }
            if (headerAction.equalsIgnoreCase("solicitarImplementacionPct")) {
                metodo = solicitarImplementacionPct();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                }

            }
            if (headerAction.equalsIgnoreCase("solicitarGestionarCobroComisiones")) {
                solicitarGestionarCobroComisiones();
                cerrar = Boolean.TRUE;
            }
            if (headerAction.equalsIgnoreCase("solicitarDeterminarFactibilidad")) {
                solicitarDeterminarFactibilidad();
                cerrar = Boolean.TRUE;
            }
            if (headerAction.equalsIgnoreCase("solicitarCalculoInteresesCobro")) {
                solicitarCalculoInteresesCobro();
                cerrar = Boolean.TRUE;
            }
            if (headerAction.equalsIgnoreCase("solicitarProcesoFirmaContrato")) {
                metodo = solicitarProcesoFirmaContrato();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                }

            }
            if (headerAction.equalsIgnoreCase("solicitarFormalizacionParcial")) {
                metodo = solicitarFormalizacionParcial();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                }

            }
            if (headerAction.equalsIgnoreCase("solicitarFormalizacion")) {
                metodo = solicitarFormalizacion();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                }

            }
            if (headerAction.equalsIgnoreCase("solicitarSplit")) {
                metodo = solicitarSplit();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                }

            }
            if (headerAction.equalsIgnoreCase("solicitarRegistrarFinConcurso")) {
                logger.warning("Dentro de la opcion solicitarRegistrarFinConcurso");
                metodo = solicitarRegistrarFinConcurso();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                    logger.log(ADFLogger.WARNING, "No se inicio el proceso de Implementacion.");
                } else {
                    logger.log(ADFLogger.WARNING, "El inicio del proceso de Implementacion se realizo con exito.");
                                    validaLoteRegistrado();
                    getPopUpRegistrarFinConcurso().hide();
                }
                logger.warning("Fuera de la opcion solicitarRegistrarFinConcurso");
            }
            if (headerAction.equalsIgnoreCase("solicitarAdministracionOperacionFenix")) {
                metodo = solicitarAdministracionOperacion();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                }

            }
            if (headerAction.equalsIgnoreCase("solicitarPrepago")) {
                metodo = solicitarPrepago();
                if (!metodo) {
                    cerrar = Boolean.FALSE;
                }

            }
            if (headerAction.equalsIgnoreCase("solicitarCierre")) {
                if (validaProducto()) {
                    metodo = solicitarCierreFideicomiso();
                    if (!metodo) {
                        cerrar = Boolean.FALSE;
                    }
                } else {
                    metodo = solicitarCierre();
                    if (!metodo) {
                        cerrar = Boolean.FALSE;
                    }
                }
            }
            if (headerAction.equalsIgnoreCase("solicitarEnmienda"))
            {   solicitarEnmienda();
            }
            
            if(headerAction.equalsIgnoreCase("solicitarEvaluacion"))
            {
                String p_tipo_proceso = gestorOperacionesBean.getTipoProcesoEvaluaciones(); 
                String p_tipo_proceso_descripcion = gestorOperacionesBean.getTipoProcesoEvaluacionesDescripcion(); 
                logger.warning("Entro en proceso de solicitarEvaluacion con p_tipo_proceso = " + p_tipo_proceso);
                logger.warning("Entro en proceso de solicitarEvaluacion con p_tipo_proceso_descripcion = " + p_tipo_proceso_descripcion); 
                metodo = solicitarEvaluaciones(p_tipo_proceso_descripcion,p_tipo_proceso);
                if(!metodo){cerrar=Boolean.FALSE;}
            }
            if (headerAction.equalsIgnoreCase("solicitarRegistrarComision"))
            {   metodo=solicitarRegistrarComision();
                if(!metodo){cerrar = Boolean.FALSE;}
            }
            if (headerAction.equalsIgnoreCase("restablecer"))
            {   restablecerOperacion();
            }
            if (headerAction.equalsIgnoreCase(HEADER_ACTION_FORMENMIENDA_GENERICA)){
                //evita que se cierre el popup de confirmacion general porque para este caso se usa otro popup
                metodo = false;
                cerrar = false;
                getPopupConfirmacionEnmiendaGenerica().hide();
                
                solicitarFormalizacionEnmienda(FenixModelConstants.ORIGEN_FORMALIZACION_ENMIENDA_GENERICO_VALUE);
            }
        } else {
            JSFUtils.addFacesErrorMessage("Ocurrió un error al invocar la operación, intente más tarde");
        }
        if (metodo) {
            getPopupConfirmarHeaderAction().hide();
        } else {
            if (cerrar) {
                getPopupConfirmarHeaderAction().hide();
            }
        }
        // Cerramos popup de confirmar
        logger.warning("Fuera de aceptarConfirmarActionListener");

    }

    public void restablecerOperacion() {
        logger.log(ADFLogger.TRACE, "Inside restablecerOperacion.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("reactivarOperacion");
        operationBinding.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
        operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());

        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            JSFUtils.addFacesInformationMessage("restablecer realizada exitosamente.");
        }
    }

    public void solicitarCambiarResponsableOperacion() {
        logger.warning("Dentro de solicitarCambiarResponsableOperacion");

        getPopUpCambiarResponsable().hide();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBindingProceso = null;

        String usuario = (String) JSFUtils.resolveExpression("#{bindings.Responsable.inputValue}");
        logger.warning("Responsable de la operacion actual: " + usuario);

        operationBindingProceso = bindings.getOperationBinding("actualizarResponsableOperacion");
        operationBindingProceso.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
        operationBindingProceso.getParamsMap().put("loginUsuario", usuario);
        operationBindingProceso.execute();

        if (!operationBindingProceso.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBindingProceso.getErrors().toString());
        } else {
            JSFUtils.addFacesInformationMessage("Cambiar responsable realizado exitosamente.");
        }
        
        logger.warning("Fuera de solicitarCambiarResponsableOperacion");
    }

    public void cancelarConfirmarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarConfirmarActionListener: " + actionEvent.getComponent().getId());
        getPopupConfirmarHeaderAction().hide();
    }

    public void cerrarSuspenderPopupActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cerrarSuspenderPopupActionListener: " + actionEvent.getComponent().getId());
        getPopupSuspenderHeaderAction().hide();
    }

    public void cerrarCancelarPopupActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cerrarCancelarPopupActionListener: " + actionEvent.getComponent().getId());
        getPopupCancelarHeaderAction().hide();
    }

    private void suspenderOperacion() {
        logger.log(ADFLogger.TRACE, "Inside suspenderOperacion.");
        String justificacion = (String) JSFUtils.resolveExpression("#{DetalleOperacionManagedBean.justificacion}");
        if (null == justificacion || justificacion.equals("")) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_JUSTIFICACION"));
        } else {

            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;

            operationBinding = bindings.getOperationBinding("inicioCancelarOperacion");
            operationBinding.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("suspenderTemporalmente", "1"); //1 valor suspender
            operationBinding.getParamsMap().put("justificacion", justificacion);
            operationBinding.getParamsMap().put("nombreOperacion", gestorOperacionesBean.getOperacion());

            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {

                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {

                getPopupSuspenderHeaderAction().hide();
                JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_INICIO_SUSPENDER"));
            }
        }
    }

    private void cancelarOperacion() {
        logger.log(ADFLogger.WARNING, "Inside cancelarOperacion.");
        String justificacion = (String) JSFUtils.resolveExpression("#{DetalleOperacionManagedBean.justificacion}");
        if (null == justificacion || justificacion.equals("")) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_JUSTIFICACION"));
        } else {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;

            operationBinding = bindings.getOperationBinding("inicioCancelarOperacion");
            operationBinding.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("suspenderTemporalmente", "0"); //0 valor cancelar
            operationBinding.getParamsMap().put("justificacion", justificacion);
            operationBinding.getParamsMap().put("nombreOperacion", gestorOperacionesBean.getOperacion());

            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
            } else {
                getPopupCancelarHeaderAction().hide();
                JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_INICIO_CANCELAR"));
            }
        }
    }

    private Boolean solicitarLAFT() {
        Integer ContadorErrores = 0;
        logger.log(ADFLogger.TRACE, "Inside solicitarLAFT.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {
            Boolean validaDeclaracion = Boolean.TRUE;
            AttributeBinding idDJ;
            idDJ = ADFUtils.findControlBinding("IdDeclaracion");
            oracle.jbo.domain.Number declaracion = (oracle.jbo.domain.Number) idDJ.getInputValue();
            if (null == declaracion || declaracion.intValue() == 0) {
                validaDeclaracion = Boolean.FALSE;
            }
            logger.warning("idDeclaraciom valor: " + declaracion);
            logger.warning("idDeclaraciom: " + idDJ.getInputValue());
            logger.warning("delcaracionBoolean: " + validaDeclaracion);
            operationBinding = bindings.getOperationBinding("inicioLAFT");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("solicitaAnalista", false);
            operationBinding.getParamsMap().put("codigoCliente", gestorOperacionesBean.getIdCliente());
            operationBinding.getParamsMap().put("nombreCliente", gestorOperacionesBean.getNombreCliente());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("cuentaDJ", validaDeclaracion);

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar LA-FT realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioLAFT " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    private Boolean solicitarImplementacionPct() {
        logger.log(ADFLogger.TRACE, "Inside solicitarImplementacionPct.");

        Integer ContadorErrores = 0;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        try {
            operationBinding = bindings.getOperationBinding("inicioImplementacionPct");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar Implementación PCT realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioImplementacionPct " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }
    
    private Boolean solicitarSupervision() {
        Integer ContadorErrores = 0;
        logger.log(ADFLogger.TRACE, "Inside solicitarSupervision.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {

            operationBinding = bindings.getOperationBinding("inicioSupervision");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar Supervisión realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioSupervision " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    private Boolean solicitarAdministracionOperacion() {
        Integer ContadorErrores = 0;
        logger.log(ADFLogger.WARNING, "Inside solicitarAdministracionOperacion.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {

            operationBinding = bindings.getOperationBinding("inicioAdministrarOperacion");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            //operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
            //operationBinding.getParamsMap().put("rol", gestorOperacionesBean.getRolInicio());
            //operationBinding.getParamsMap().put("responsableOperacion", gestorOperacionesBean.getResponsableOperacionInicio()); 

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar Administracion Operacion realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioAdministracionOperacion " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }    
    
    private Boolean solicitarPrepago() {
        Integer ContadorErrores = 0;
        logger.log(ADFLogger.TRACE, "Inside solicitarPrepago.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {

            operationBinding = bindings.getOperationBinding("inicioPrepago");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("rol", gestorOperacionesBean.getRolInicio());
            operationBinding.getParamsMap().put("responsableOperacion", gestorOperacionesBean.getResponsableOperacionInicio());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar Prepago realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioPrepago " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    private Boolean solicitarCierre() {
        Integer ContadorErrores = 0;
        Boolean resultado = Boolean.FALSE;
        try {
            logger.log(ADFLogger.TRACE, "Inside solicitarCierre.");
            BindingContainer bindings = ADFUtils.getBindingContainer();
            GestorOperacionesBean gestorOperacionesBean = null;
            OperationBinding operationBinding = null;

            gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
            operationBinding = bindings.getOperationBinding("inicioCierre");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoCliente", gestorOperacionesBean.getIdCliente());
            operationBinding.getParamsMap().put("nombreCliente", gestorOperacionesBean.getNombreCliente());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());
            
            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                System.out.println("Error");
                resultado = null;

            } else {
                JSFUtils.addFacesInformationMessage("Solicitar Cierre realizada exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }
            if (ContadorErrores > 0) {
                resultado = Boolean.FALSE;
            }

            resultado = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioCierre " + e.getClass() + ":" + e.getMessage());
            resultado = Boolean.FALSE;
        }
        return resultado;
    }

    private Boolean solicitarCierreFideicomiso() {
        Integer ContadorErrores = 0;
        logger.log(ADFLogger.TRACE, "Inside solicitarCierreFideicomiso.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {

            operationBinding = bindings.getOperationBinding("inicioCierreFideicomiso");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar CierreFideicomiso realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioCierreFideicomiso " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    private Boolean solicitarGestionarCobroComisiones() {
        logger.warning("Inside solicitarGestionarCobroComisiones.");

        getPopUpGestionarCobroComisiones().hide();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        List<Long> comisionesInicio = new ArrayList<Long>();

        DCIteratorBinding voComisionesNoPagadas =
            ADFUtils.getDCBindingContainer().findIteratorBinding("ComisionesNoPagadasVOIterator");
        ViewObject vo = voComisionesNoPagadas.getViewObject();
        logger.warning("cantidad de rows:" + voComisionesNoPagadas.getEstimatedRowCount());

        Row[] comisionesSeleccionadasRows = vo.getFilteredRows("Seleccionar", true);
        logger.warning("cantidad de rows check en null" + comisionesSeleccionadasRows.length);

        if (comisionesSeleccionadasRows != null) {

            comisionesInicio = (ArrayList<Long>) validaBHQComisiones(comisionesSeleccionadasRows);
            logger.warning("comisionesInicio size: " + comisionesInicio.size());
            for (Long idComision : comisionesInicio) {

                operationBinding = bindings.getOperationBinding("servicioInicioComisiones");
                operationBinding.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
                operationBinding.getParamsMap().put("loginUsuario",
                                                    ADFContext.getCurrent().getSecurityContext().getUserName());
                operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
                operationBinding.getParamsMap().put("codigoCliente", gestorOperacionesBean.getIdCliente());
                operationBinding.getParamsMap().put("nombreCliente", gestorOperacionesBean.getNombreCliente());
                operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
                operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
                operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
                operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());

                logger.warning("idComision: " + idComision);
                operationBinding.getParamsMap().put("idComision", idComision);
                operationBinding.execute();

            }
        }
        return false;
    }

    public List validaBHQComisiones(Row[] comisionesSeleccionadasRows) {
        logger.warning("Inside validaBHQComisiones.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String codigoContrato = null;
        Boolean result = Boolean.FALSE;

        List<Long> comisionesInicio = new ArrayList<Long>();

        for (Row row : comisionesSeleccionadasRows) {

            Long idComision = (Long) row.getAttribute("Id");

            if (row.getAttribute("CodigoContrato") != null) {
                comisionesInicio.add(idComision);
                logger.warning("Comision con BHQ actualmente asignado y a iniciar proceso: " + idComision);
            } else {
                logger.warning("Comision sin BHQ");
                /* Solicitud de cambio: Solo se muestran comisiones No pagadas de Flexcube, no es necesario enviar a Flexcube ya que las comisiones cuentan con un BHQ asignado.
                 * Resuelve incidencia: FNXII-4789.
                logger.warning("Se asignara BHQ a Comision: " + idComision);
                operationBinding = bindings.getOperationBinding("crearComisionGestor");
                logger.warning("idComision: " + idComision);
                operationBinding.getParamsMap().put("idComision", idComision);
                operationBinding.getParamsMap().put("descripcion", gestorOperacionesBean.getOperacion());
                operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
                operationBinding.execute();

                if (operationBinding.getResult() != null) {
                    result = (Boolean) operationBinding.getResult();
                    logger.warning("Resultado crearComisionGestor: " + result);
                    if (result) {
                        logger.warning("BHQ asignado a Comision: " + idComision);
                        comisionesInicio.add((Long) row.getAttribute("Id"));
                        logger.warning("Comision con BHQ actualmente asignado y a iniciar proceso: " + idComision);
                        validaEstadoComision(row);
                    } else {
                        logger.warning("Comisión sin BHQ asignado: " + idComision);
                    }
                } else {
                    logger.warning("Resultado de crearComisionGestor es null.");
                }*/
            }
        }

        return comisionesInicio;
    }

    public void validaEstadoComision(Row row) {
        logger.warning("Inside validaEstadoComision.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        Long idComision = null;
        Long idTcaEstadoTcc = null;
        Long idTcaSubEstadoTcc = null;
        Integer subEstadoValidadoParcialmente = 25;

        idComision = (Long) row.getAttribute("Id");

        idTcaEstadoTcc = (Long) row.getAttribute("IdTcaEstadoTcc");

        idTcaSubEstadoTcc = (Long) row.getAttribute("IdTcaSubEstadoTcc");

        if (idTcaSubEstadoTcc == 0) {
            logger.warning("Asigna Sub Estado");

            logger.warning("idComision: " + idComision);

            operationBinding = bindings.getOperationBinding("actualizarEstadoComision");
            operationBinding.getParamsMap().put("idComision", idComision);
            operationBinding.getParamsMap().put("idTcaEstadoTcc", idTcaEstadoTcc);
            operationBinding.getParamsMap().put("idTcaSubEstadoTcc", subEstadoValidadoParcialmente);
            operationBinding.execute();
        }
    }

    private Boolean solicitarRegistrarComision() {
        logger.warning("Entra en solicitarRegistrarComision bean.");

        Integer ContadorErrores = 0;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {

            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());

            operationBinding = bindings.getOperationBinding("inicioRegistrarComision");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());

            Boolean result = (Boolean) operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                logger.warning("Se reciben errores del servicio.");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                JSFUtils.addFacesErrorMessage("MSG_04_DETALLE_OPERACION");
                return null;
            } else {
                JSFUtils.addFacesInformationMessage(getStringFromBundle("SOLICITUD_REGISTRAR_COMISION_EXITOSO"));
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.warning("Error en inicioRegistrarComision " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    private Boolean solicitarDeterminarFactibilidad() {
        logger.log(ADFLogger.TRACE, "Inside solicitarDeterminarFactibilidad.");

        if (gestorOperacionesBean.getEsEnvioGasto()) {
            actualizarOperacion();
            solicitarEnvioGastoDeterminar();
            gestorOperacionesBean.visualizarCalculoInteresCobro();
            gestorOperacionesBean.setEsFactible(Boolean.FALSE);
            getDeterminarFactibilidadUIC().setVisible(Boolean.FALSE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(getDeterminarFactibilidadUIC());
            getPopUpDeterminarFactibilidad().hide();
        } else {
            actualizarFactibilidad();
            solicitarInteresesCobro();
            gestorOperacionesBean.visualizarCalculoInteresCobro();
            gestorOperacionesBean.setEsFactible(Boolean.FALSE);
            getDeterminarFactibilidadUIC().setVisible(Boolean.FALSE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(getDeterminarFactibilidadUIC());
            getPopUpDeterminarFactibilidad().hide();
        }
        return false;
    }

    private Boolean solicitarCalculoInteresesCobro() {
        logger.log(ADFLogger.TRACE, "Inside solicitarDeterminarFactibilidad.");

        if (gestorOperacionesBean.getEsEnvioGasto()) {
            if (validarEstadoOperacion()) {
                if (validarProcesoOperacion(PROCESO_IMPLEMENTACION_PCT)) {
                    solicitarEnviarGasto();
                }
            }
        } else {
            actualizarCalculoInteresesCobro();
            solicitarInicioCalculoIntereses();
            getPopUpDeterminarFactibilidad().hide();
        }

        return false;
    }

    private Boolean solicitarProcesoFirmaContrato() {
        logger.log(ADFLogger.TRACE, "Inside solicitarProcesoFirmaContrato.");
        return false;
    }

    private Boolean solicitarFormalizacionParcial() {
        logger.log(ADFLogger.TRACE, "Inside solicitarFormalizacionParcial.");
        return false;
    }

    private Boolean solicitarFormalizacion() {
        Integer ContadorErrores = 0;
        logger.log(ADFLogger.TRACE, "Inside solicitarFormalizacion.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {

            operationBinding = bindings.getOperationBinding("inicioFormalizacion");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar Formalizacion realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioFormalizacion " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    private Boolean solicitarSplit() {
        logger.log(ADFLogger.TRACE, "Inside solicitarSplit.");
        return false;
    }

    private Boolean solicitarRegistrarFinConcurso() {
        logger.warning("Dentro solicitarRegistrarFinConcurso");
        Integer ContadorErrores = 0;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Map<String, Object> map = new HashMap<String, Object>();

        String idOperacion = null;
        String nombreOperacion = null;
        String responsable = null;
        Boolean result = null;

        if (null != gestorOperacionesBean.getIdOperacion()) {
            idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            logger.log(ADFLogger.WARNING, "Valor de la operacion :" + idOperacion);
            map.put("idOperacion", idOperacion);
        } else {
            logger.log(ADFLogger.WARNING, "El valor de la operacion es nulo.");
        }
        if (null != gestorOperacionesBean.getOperacion()) {
            nombreOperacion = gestorOperacionesBean.getOperacion();
            logger.log(ADFLogger.WARNING, "Nombre de la operacion :" + nombreOperacion);
            map.put("nombreOperacion", nombreOperacion);
        } else {
            logger.log(ADFLogger.WARNING, "El nombre de la operacion es nulo.");
        }
        if (null != gestorOperacionesBean.getResponsableOperacionInicio()) {
            responsable = gestorOperacionesBean.getResponsableOperacionInicio();
            logger.log(ADFLogger.WARNING, "Responsable de la operacion :" + responsable);
            map.put("responsable", responsable);
        } else {
            logger.log(ADFLogger.WARNING, "Responsable de la operacion es nulo.");
        }


        try {
            if (null != map) {
                operationBinding = bindings.getOperationBinding("inicioProcesoPorIdLote");
                operationBinding.getParamsMap().put("map", map);
                result = (Boolean) operationBinding.execute();
            } else {
                logger.log(ADFLogger.WARNING, "El mapa de parametros es nulo.");
            }
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                if(result){
                    JSFUtils.addFacesInformationMessage("Solicitar RegistrarFinConcurso realizado exitosamente.");
                }else{
                    JSFUtils.addFacesErrorMessage("Error en ImplementacionPCTService");
                    if(null != getPopupConfirmarHeaderAction()){
                        getPopupConfirmarHeaderAction().hide();
                    }
                    
                    if(null != getPopUpRegistrarFinConcurso()){
                        getPopUpRegistrarFinConcurso().hide();
                    }
                }
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }
            
            if (ContadorErrores > 0) {
                return false;
            }
            
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioRegistrarFinConcurso " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    private void validaLoteRegistrado(){
        logger.warning("Entra en validaLotesRegistrados");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Boolean seleccionado = Boolean.FALSE;
        Long idOperacion = null;
        
        try{
            if(null != gestorOperacionesBean.getIdOperacion()){
            idOperacion = gestorOperacionesBean.getIdOperacion();
            operationBinding = bindings.getOperationBinding("inicializarLoteImplementacionFinConcurso");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
            seleccionado = (Boolean)operationBinding.getResult();
            }else{
                logger.warning("El id de ña operacion es nula no se ejecuta metodo");
            }
            if(!seleccionado){
                logger.warning("Todos los lotes se han finalizado.");
                gestorOperacionesBean.setIsLoteImplementacion(seleccionado);
            }else{
                logger.warning("Existen lotes por finalizar.");
            }
        }catch(Exception e){
            LOGGER.warning("Error al buscar lotes seleccionados");
        }
    }
    private Boolean solicitarEvaluacionesMedioTermino() {
        Integer ContadorErrores = 0;
        logger.log(ADFLogger.TRACE, "Inside solicitarEvaluacionesMedioTermino.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String idEvaluacion = "2";
        try {

            operationBinding = bindings.getOperationBinding("inicioEvaluacion");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());
            operationBinding.getParamsMap().put("idEvaluacion", idEvaluacion);

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar EvaluacionesMedioTermino realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioEvaluacionesMedioTermino " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }
    
    private Boolean solicitarEvaluacionesExPost() {
        Integer ContadorErrores = 0;
        logger.log(ADFLogger.TRACE, "Inside solicitarEvaluacionesExPost.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String idEvaluacion = "3";
        try {

            operationBinding = bindings.getOperationBinding("inicioEvaluacion");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());
            operationBinding.getParamsMap().put("idEvaluacion", idEvaluacion);

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar EvaluacionesExPost realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioEvaluacionesExPost " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    private Boolean solicitarEvaluacionesSiemas() {
        Integer ContadorErrores = 0;
        logger.log(ADFLogger.TRACE, "Inside solicitarEvaluacionesSiemas.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String idEvaluacion = "4";
        try {

            operationBinding = bindings.getOperationBinding("inicioEvaluacion");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());
            operationBinding.getParamsMap().put("idEvaluacion", idEvaluacion);

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar EvaluacionesSIEMAS realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioEvaluacionSIEMAS " + e.getClass() + ":" + e.getMessage());
            return false;
        }
    }

    /**
     * Realiza la invocacion del metodo inicioEnmiendas del AM
     *
     * 04/07/2016 Se realiza ajuste para mostrar el mensaje de  Error enviado por el backEnd
     */
    private void solicitarEnmienda() {
        logger.log(ADFLogger.TRACE, "Inside solicitarEnmienda.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        GestorOperacionesBean gestorOperacionesBean = null;
        OperationBinding operationBinding = null;

        gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        operationBinding = bindings.getOperationBinding("inicioEnmiendas");
        operationBinding.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
        operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());

        operationBinding.execute();
        if (operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            // JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString()); El ,ensaje de error es propagado automaticamente
            JSFUtils.addFacesInformationMessage("Solicitar Enmienda realizada exitosamente.");
        }
    }

    private Boolean solicitarEvaluacion() {
        Integer ContadorErrores = 0;
        Boolean resultado = Boolean.FALSE;
        String idEvaluacion = "1";
        try {
            logger.log(ADFLogger.TRACE, "Inside solicitarEvaluacion.");
            BindingContainer bindings = ADFUtils.getBindingContainer();
            GestorOperacionesBean gestorOperacionesBean = null;
            OperationBinding operationBinding = null;

            gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
            operationBinding = bindings.getOperationBinding("inicioEvaluacion");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("solicitaAnalista", false);
            operationBinding.getParamsMap().put("codigoCliente", gestorOperacionesBean.getIdCliente());
            operationBinding.getParamsMap().put("nombreCliente", gestorOperacionesBean.getNombreCliente());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("idEvaluacion", idEvaluacion);

            Boolean result = (Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                System.out.println("Error");
                resultado = null;

            } else {
                JSFUtils.addFacesInformationMessage("Solicitar Evaluaciones realizada exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }
            if (ContadorErrores > 0) {
                resultado = Boolean.FALSE;
            }

            resultado = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioLAFT " + e.getClass() + ":" + e.getMessage());
            resultado = Boolean.FALSE;
        }
        return resultado;
    }

    public void solicitarSupervisionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarSupervisionActionListener.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.getParamsMap().put("proceso", PROCESO_SUPERVISION);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUPERVISION"));
        } else {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long resultEstado = (Long) operationBindingEstado.execute();

            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {

                        logger.log(ADFLogger.TRACE,
                                   "Inside solicitarSupervisionActionListener: " + actionEvent.getComponent().getId());
                        setMensajeConfirmacion("¿Confirma iniciar el ‘Proceso de Supervisión’ de la operación " +
                                               gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                        showPopupConfirmarHeaderAction(); // Mostramos popup

                    }
                }
            }
        }
    }

    public void solicitarImplementacionPctActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside solicitarImplementacionPctActionListener.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.getParamsMap().put("proceso", PROCESO_IMPLEMENTACION_PCT);
        Boolean result = (Boolean) operationBinding.execute();
        logger.log(ADFLogger.WARNING, "Se  validara el estado de la operacion");

            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long resultEstado = (Long) operationBindingEstado.execute();

            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {

                        logger.log(ADFLogger.WARNING,
                                   "Inside solicitarImplementacionPctActionListener: " +
                                   actionEvent.getComponent().getId());
                        setMensajeConfirmacion("¿Confirma iniciar el ‘Proceso de Implementación PCT’ de la operación " +
                                               gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                        showPopupConfirmarHeaderAction(); // Mostramos popup

                    }
                }
            } 
    }

    public void solicitarPrepagoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarPrepagoActionListener.");

        logger.log(ADFLogger.TRACE, "idOperacion." + gestorOperacionesBean.getIdOperacion());
        logger.log(ADFLogger.TRACE, "userName." + ADFContext.getCurrent().getSecurityContext().getUserName());
        logger.log(ADFLogger.TRACE, "rol." + gestorOperacionesBean.getRolInicio());
        logger.log(ADFLogger.TRACE, "responsableOperacion." + gestorOperacionesBean.getResponsableOperacionInicio());

        Boolean contratosOperacion = Boolean.FALSE;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("verificarSaldoNoVencido");
        operationBinding.getParamsMap().put("idOperacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.execute();
        contratosOperacion = ((Boolean) operationBinding.getResult());
        logger.warning("contratosOperacion" + contratosOperacion);
        
        if (contratosOperacion) {
            operationBinding = bindings.getOperationBinding("validaProcesos");
            operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
            operationBinding.getParamsMap().put("proceso", PROCESO_PREPAGO);
            Boolean result = (Boolean) operationBinding.execute();

            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long resultEstado = (Long) operationBindingEstado.execute();

            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {

                        logger.log(ADFLogger.TRACE,
                                   "Inside solicitarCierreActionListener: " + actionEvent.getComponent().getId());
                        if (!result) {
                            //JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_PREPAGO"));
                            if (validaProducto()) {
                                setMensajeConfirmacion("¿Confirma iniciar el ‘Proceso de Prepago’ de la operación " +
                                                       gestorOperacionesBean.getIdOperacion() +
                                                       "?"); // Asignamos mensaje
                            } else {
                                setMensajeConfirmacion("¿Confirma iniciar el ‘Proceso de Prepago’ de la operación " +
                                                       gestorOperacionesBean.getIdOperacion() +
                                                       "?"); // Asignamos mensaje
                            }
                        } else {
                            setMensajeConfirmacion("La operación ya cuenta con un proceso de Prepago iniciado, ¿desea continuar?"); // Asignamos mensaje
                        }

                        showPopupConfirmarHeaderAction(); // Mostramos popup
                    }
                }
            }

        } else {
            logger.log(ADFLogger.TRACE, "Se debe haber realizado un desembolso para iniciar el proceso de Prepago.");
            //JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_PREPAGO"));
        }
    }

    public void solicitarCierreActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarCierreActionListener.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.getParamsMap().put("proceso", PROCESO_CIERRE);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CIERRE"));
        } else {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long resultEstado = (Long) operationBindingEstado.execute();

            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {
                        if (resultEstado == ESTADO_CERRADO) {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CERRADO"));
                        } else {
                            logger.log(ADFLogger.TRACE,
                                       "Inside solicitarCierreActionListener: " + actionEvent.getComponent().getId());
                            if (validaProducto()) {
                                setMensajeConfirmacion("¿Confirma iniciar el ‘Proceso de Cierre Fideicomiso’ de la operación " +
                                                       gestorOperacionesBean.getIdOperacion() +
                                                       "?"); // Asignamos mensaje
                            } else {
                                setMensajeConfirmacion("¿Confirma iniciar el ‘Proceso de Cierre’ de la operación " +
                                                       gestorOperacionesBean.getIdOperacion() +
                                                       "?"); // Asignamos mensaje
                            }
                            showPopupConfirmarHeaderAction(); // Mostramos popup
                        }
                    }
                }
            }
        }
    }

    public void setPopupInicioCumplimientoCondicion(RichPopup popupInicioCumplimientoCondicion) {
        this.popupInicioCumplimientoCondicion = popupInicioCumplimientoCondicion;
    }

    public RichPopup getPopupInicioCumplimientoCondicion() {
        return popupInicioCumplimientoCondicion;
    }

    /**
    ???? * Se crea metodo iniciaCumplimientoCondicionActionListener() para validar que
    ???? * hay condiciones con el estado por validar
    ???? * @param actionEvent
    ???? * @since 01/11/2016
    ???? */
    public void iniciaCumplimientoCondicionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "INTO iniciaCumplimientoCondicionActionListener");

        Boolean validar = Boolean.FALSE;
        Long idOperacion = null;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        try {
            if (null != gestorOperacionesBean.getIdOperacion()) {

                idOperacion = gestorOperacionesBean.getIdOperacion();
                operationBinding = bindings.getOperationBinding("obtenerCondicionPorValidar");
                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                operationBinding.execute();

            } else {
                logger.log(ADFLogger.WARNING, "El id de Operacion es Nulo.");
            }
            if (null != operationBinding.getResult()) {
                validar = (Boolean) operationBinding.getResult();
            } else {
                logger.log(ADFLogger.WARNING, "El valor de retorno de obtenerCondicionPorValidar es nulo.");
            }
            if (!validar) {
                JSFUtils.addFacesInformationMessage(getStringFromBundle("MSG_38_DETALLE_OPERACION"));
            } else {
                RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                getPopupInicioCumplimientoCondicion().show(popupHints);
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "Error en iniciaCumplimientoCondicionActionListener." + e.getClass() + "." + e.getMessage());
        }
    }

    public void setPopupInicioCondicionCumplimiento(RichPopup popupInicioCondicionCumplimiento) {
        this.popupInicioCondicionCumplimiento = popupInicioCondicionCumplimiento;
    }

    public RichPopup getPopupInicioCondicionCumplimiento() {
        return popupInicioCondicionCumplimiento;
    }

    /**
    ???? * Se crea metodo cumplimientoCondicionActionListener() para validar que el proceso de Aprobacion ha finalizado
    ???? * y que el usuario tiene rol de responsable de la operacion
    ???? * @param actionEvent
    ???? * @since 01/11/2016
    ???? */
    public void cumplimientoCondicionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside cumplimientoCondicionActionListener.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idOperacion = null;
        Boolean iniciaProceso = Boolean.FALSE;
        Boolean responsable = Boolean.FALSE;
        Boolean result = Boolean.TRUE;

        /*try {
            if (null != idOperacion) {
                idOperacion = gestorOperacionesBean.getIdOperacion();
                operationBinding = bindings.getOperationBinding("validaProcesos");
                operationBinding.getParamsMap().put("operacion", Long.toString(idOperacion));
                operationBinding.getParamsMap().put("proceso", PROCESO_APROBACION);
                operationBinding.execute();
            } else {
                logger.log(ADFLogger.WARNING, "La operacion es nula.");
            }
            if (null != operationBinding.getResult()) {
                result = (Boolean) operationBinding.getResult();
            } else {
                logger.log(ADFLogger.WARNING, "Valor de Resultado es nulo.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "Error al ejecutar el metodo validaProcesos." + e.getClass() + "." + e.getMessage());
        }
        logger.log(ADFLogger.WARNING, "Valor de Resultado :" + result);
        logger.log(ADFLogger.WARNING, "Es responsable :" + gestorOperacionesBean.getEsResponsableOperacion());
        try {
            if (null != result && null != responsable) {
                if (result || !responsable) {
                    logger.log(ADFLogger.WARNING, "Into error MSG_04");
                    getPopupInicioCondicionCumplimiento().hide();
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_04_DETALLE_OPERACION"));
                } else {
                    iniciaProceso = agregarCondicionById();
                    if (null != iniciaProceso) {
                        if (iniciaProceso) {
                            getPopupInicioCondicionCumplimiento().hide();
                            getPopupInicioCumplimientoCondicion().hide();
                        } else {
                            getPopupInicioCondicionCumplimiento().hide();
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_04_DETALLE_OPERACION"));
                        }
                    } else {
                        logger.log(ADFLogger.WARNING, "El valor de retorno es nulo.");
                    }
                }
            } else {
                logger.log(ADFLogger.WARNING, "El resultado del metodo validaProcesos o el responsable son nulos.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "Error en cumplimientoCondicionActionListener." + e.getClass() + "." + e.getMessage());
        }*/
        try {
            iniciaProceso = agregarCondicionById();
            if (null != iniciaProceso) {
                if (iniciaProceso) {
                    getPopupInicioCondicionCumplimiento().hide();
                    getPopupInicioCumplimientoCondicion().hide();
                } else {
                    getPopupInicioCondicionCumplimiento().hide();
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_04_DETALLE_OPERACION"));
                }
            } else {
                logger.log(ADFLogger.WARNING, "El valor de retorno es nulo.");
            }

        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "Error en cumplimientoCondicionActionListener." + e.getClass() + "." + e.getMessage());
        }
    }

    public void aceptarCumplimientoCondicionActionListener(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupInicioCondicionCumplimiento().show(popupHints);
    }

    /**
    ???? * Se crea metodo validaCondicionValueChangeListener() para validar que
    ???? * se han seleccionado condiciones
    ???? * @param valueChangeEvent
    ???? * @since 01/11/2016
    ???? */
    public void validaCondicionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "INTO validaCondicionValueChangeListener.");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean porValidar = null;
        if (null != valueChangeEvent.getNewValue()) {
            porValidar = (Boolean) (valueChangeEvent.getNewValue());
            logger.log(ADFLogger.WARNING, "Valor de Condicion a Validar.." + porValidar);
        } else {
            gestorOperacionesBean.setValidaCondicion(Boolean.FALSE);
            logger.log(ADFLogger.WARNING, "Valor de Condicion a validar es nulo.." + porValidar);
        }
        if (porValidar == Boolean.TRUE) {
            gestorOperacionesBean.setValidaCondicion(Boolean.TRUE);
        } else {
            gestorOperacionesBean.setValidaCondicion(Boolean.FALSE);
        }
    }

    /**
    ???? * Se crea metodo para insertar las condiciones seleccionadas en el boton
    ???? * Iniciar Cumplimiento de condiciones
    ???? * @param
    ???? * @since 02/11/2016
    ???? */
    public Boolean agregarCondicionById() {
        logger.log(ADFLogger.WARNING, "INTO agregarCondicionById.");

        GestorOperacionesBean gestorOperacionesBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        Boolean resultado = Boolean.FALSE;
        Row[] SeleccionaRows = null;
        Long idOperacion = null;
        String responsable = null;

        if (null != gestorOperacionesBean.getIdOperacion()) {
            idOperacion = gestorOperacionesBean.getIdOperacion();
            logger.log(ADFLogger.WARNING, "Valor de la operacion :" + idOperacion);
        } else {
            logger.log(ADFLogger.WARNING, "Valor de la operacion es nulo");
        }
        if (null != gestorOperacionesBean.getIdOperacion()) {
            responsable = ADFContext.getCurrent().getSecurityContext().getUserName();
            logger.log(ADFLogger.WARNING, "Nombre delresponsable es :" + responsable);
        } else {
            logger.log(ADFLogger.WARNING, "Nombre del responsable es nulo");
        }

        try {
            OperationBinding operationBinding = bindings.getOperationBinding("agregarCondiciones");
            DCIteratorBinding condicionIncumplimientoVOIterator = null;
            ViewObject condicionIncumplimientoResultadoVO = null;

            condicionIncumplimientoVOIterator =
                ADFUtils.getDCBindingContainer().findIteratorBinding("CondicionIncumplimientoVOIterator");
            condicionIncumplimientoResultadoVO = condicionIncumplimientoVOIterator.getViewObject();
            if (null != condicionIncumplimientoResultadoVO) {
                SeleccionaRows = condicionIncumplimientoResultadoVO.getFilteredRows("Seleccionar", true);
                logger.log(ADFLogger.WARNING, "numero de rows seleccionados es :" + SeleccionaRows.length);
                if (SeleccionaRows.length > 0 && null != idOperacion && null != responsable) {
                    operationBinding.getParamsMap().put("idOperacion", idOperacion);
                    operationBinding.getParamsMap().put("responsableOperacion", responsable);
                    operationBinding.getParamsMap().put("SeleccionCondiciones", SeleccionaRows);
                    operationBinding.execute();
                } else {
                    logger.log(ADFLogger.WARNING, "Valor de los parametros para agregarCondiciones son nulos.");
                }
            } else {
                logger.log(ADFLogger.WARNING, "El objeto operacionResultadoVO es nulo.");
            }
            if (null != operationBinding.getResult()) {
                resultado = (Boolean) operationBinding.getResult();
            } else {
                logger.log(ADFLogger.WARNING, "El resultado es nulo.");
            }

            if (operationBinding.getErrors().size() != 0) {
                // Manejo de errores


            }
        } catch (Exception e) {
            resultado = Boolean.FALSE;
            logger.log(ADFLogger.ERROR, "Error en agregarCondicionById." + e.getClass() + "." + e.getMessage());
        }
        return resultado;
    }

    public void solicitarEvaluacionesMedioTerminoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarEvaluacionesMedioTerminoActionListener.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.getParamsMap().put("proceso", PROCESO_EVALUACIONES);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_EVALUACIONES"));
        } else {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long resultEstado = (Long) operationBindingEstado.execute();
            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {
                        logger.log(ADFLogger.TRACE,
                                   "Inside solicitarEvaluacionesMedioTerminoActionListener: " +
                                   actionEvent.getComponent().getId());
                        setMensajeConfirmacion("¿Esta seguro de iniciar el proceso de Evaluaciones Medio Termino de la operación " +
                                               gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                        showPopupConfirmarHeaderAction(); // Mostramos popup
                    }
                }
            }
        }
    }

    public void solicitarEvaluacionesExPostActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarEvaluacionesExPostActionListener.");

        BindingContainer bindings = ADFUtils.getBindingContainer();

        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.getParamsMap().put("proceso", PROCESO_EVALUACIONES);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_EVALUACIONES"));
        } else {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long resultEstado = (Long) operationBindingEstado.execute();
            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {
                        logger.log(ADFLogger.TRACE,
                                   "Inside solicitarEvaluacionesExPostActionListener: " +
                                   actionEvent.getComponent().getId());
                        setMensajeConfirmacion("¿Esta seguro de iniciar el proceso de Evaluaciones Ex Post de la operación " +
                                               gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                        showPopupConfirmarHeaderAction(); // Mostramos popup
                    }
                }
            }
        }
    }

    public void solicitarProcesoFirmaContratoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarProcesoFirmaContratoActionListener.");

    }

    public void solicitarFormalizacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarFormalizacionActionListener.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.getParamsMap().put("proceso", PROCESO_FORMALIZACION);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FORMALIZACION"));
        } else {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long resultEstado = (Long) operationBindingEstado.execute();

            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {

                        logger.log(ADFLogger.TRACE,
                                   "Inside solicitarFormalizacionActionListener: " +
                                   actionEvent.getComponent().getId());
                        setMensajeConfirmacion("¿Confirma iniciar el ‘Proceso de Formalizacion’ de la operación " +
                                               gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                        showPopupConfirmarHeaderAction(); // Mostramos popup

                    }
                }
            }
        }
    }

    public void solicitarCambiarResponsableOperacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarCambiarResponsableOperacionActionListener.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validarEstadoOperacion");
        operationBinding.getParamsMap().put("idOperacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        Long result = (Long) operationBinding.execute();
        if (result == ESTADO_CANCELADO) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
        } else {
            if (result == ESTADO_FINALIZADO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
            } else {
                logger.log(ADFLogger.TRACE, "Inside solicitarCambiarResponsableOperacionActionListener.");
                //openDialogGestionarCobroComisionesActionListener();
                showPopupConfirmarHeaderAction();
            }
        }
    }

    public void solicitarGestionarCobroComisionesActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarGestionarCobroComisionesActionListener.");
        setMensajeConfirmacion("¿Desea iniciar el proceso “Gestionar Cobro de Comisiones” para cada una de las comisiones seleccionadas?"); // Asignamos mensaje
        showPopupConfirmarHeaderAction();
    }

    public void solicitarDeterminarFactibilidadActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarDeterminarFactibilidadActionListener.");

        String esFactible =
            (ADFUtils.getBoundAttributeValue("EsFactible") == null) ? null :
            ADFUtils.getBoundAttributeValue("EsFactible").toString();
        String headerAction = (String) JSFUtils.resolveExpression("#{viewScope.headerAction}");


        if ((ADFUtils.getBoundAttributeValue("EsFactible") == null) ||
            (!(ADFUtils.getBoundAttributeValue("EsFactible").toString().length() > 0))) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FACTIBILIDAD"));
        } else {
            Integer isFactible = Integer.valueOf(esFactible);
            if (gestorOperacionesBean.getEsEnvioGasto()) {
                if (isFactible.compareTo(0) == 0) {
                    if (validarEstadoOperacion()) {
                        if (validarProcesoOperacion(PROCESO_IMPLEMENTACION_PCT)) {
                            setMensajeConfirmacion("¿Desea ingresar la factibilidad de la operación " +
                                                   gestorOperacionesBean.getIdOperacion() + "?");
                            showPopupConfirmarHeaderAction();
                        }
                    }
                } else {
                    setMensajeConfirmacion("¿Desea ingresar la factibilidad de la operación " +
                                           gestorOperacionesBean.getIdOperacion() + "?");
                    showPopupConfirmarHeaderAction();
                }
            } else {
                if (isFactible != null) {
                    //No Factible
                    if (isFactible.compareTo(0) == 0) {
                        if (validarCampos()) {
                            if (validarEstadoOperacion()) {
                                if (validarProcesoOperacion(PROCESO_IMPLEMENTACION_PCT)) {

                                    if ((headerAction != null) && (headerAction.trim().length() > 0)) {
                                        if (headerAction.equalsIgnoreCase("solicitarDeterminarFactibilidad")) {
                                            logger.warning("Entra en solicitarDeterminarFactibilidad");
                                            setMensajeConfirmacion("¿Desea ingresar la factibilidad de la operación " +
                                                                   gestorOperacionesBean.getIdOperacion() + "?");
                                        }
                                        if (headerAction.equalsIgnoreCase("solicitarCalculoInteresesCobro")) {
                                            logger.warning("Entra en solicitarCalculoInteresesCobro");
                                            setMensajeConfirmacion("¿Está seguro que desea iniciar el proceso de Cálculo de interés y Cobro?");
                                        }
                                    }
                                    showPopupConfirmarHeaderAction();
                                }
                            }
                        } else {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FACTIBILIDAD"));
                        }
                    }

                    if ((headerAction != null) && (headerAction.trim().length() > 0)) {
                        if (headerAction.equalsIgnoreCase("solicitarDeterminarFactibilidad")) {
                            logger.warning("Entra en solicitarDeterminarFactibilidad");
                            //Factible
                            if (isFactible.compareTo(1) == 0) {
                                if (validarCampos()) {
                                    if (headerAction.equalsIgnoreCase("solicitarDeterminarFactibilidad")) {
                                        logger.warning("Entra en solicitarDeterminarFactibilidad");
                                        setMensajeConfirmacion("¿Desea ingresar la factibilidad de la operación " +
                                                               gestorOperacionesBean.getIdOperacion() + "?");
                                    }
                                    showPopupConfirmarHeaderAction();
                                } else {
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FACTIBILIDAD"));
                                }
                            }
                        }
                        if (headerAction.equalsIgnoreCase("solicitarCalculoInteresesCobro")) {
                            logger.warning("Entra en solicitarCalculoInteresesCobro");
                            //Factible
                            if (isFactible.compareTo(1) == 0) {
                                if (validarCampos()) {
                                    if (validarEstadoOperacion()) {
                                        if (validarProcesoOperacion(PROCESO_IMPLEMENTACION_PCT)) {
                                            setMensajeConfirmacion("¿Está seguro que desea iniciar el proceso de Cálculo de interés y Cobro?");
                                            showPopupConfirmarHeaderAction();
                                        }
                                    }
                                } else {
                                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FACTIBILIDAD"));
                                }
                            }
                        }
                    }
                } else {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FACTIBILIDAD"));
                }
            }
        }
    }

    public void solicitarFormalizacionParcialActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarFormalizacionParcialActionListener.");
        showPopupConfirmarHeaderAction();
    }

    public void solicitarSplitActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarSplitActionListener.");
        showPopupConfirmarHeaderAction();
    }

    public Boolean validaProducto() {
        logger.log(ADFLogger.TRACE, "Inside validaProducto.");

        Boolean result = Boolean.FALSE;

        if ((gestorOperacionesBean.getIdProducto().compareTo(PRODUCTO_FIDEICOMISO) == 0) ||
            (gestorOperacionesBean.getIdProducto().compareTo(PRODUCTO_FONDO) == 0)) {
            result = Boolean.TRUE;
        }

        return (result);
    }

    public void cerrarGestionarCobroComisionesPopupActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cerrarGestionarCobroComisionesPopupActionListener.");
        getPopUpGestionarCobroComisiones().hide();
    }

    public void cerrarDeterminarFactibilidadPopupActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cerrarDeterminarFactibilidadPopupActionListener.");
        getPopUpDeterminarFactibilidad().hide();
    }

    public void cerrarFormalizacionParcialActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cerrarFormalizacionParcialActionListener.");
        getPopUpFormalizacionParcial().hide();
    }

    public void cerrarSplitActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cerrarSplitActionListener.");
        getPopUpSplit().hide();
    }

    public void cerrarRegistrarFinConcursoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cerrarRegistrarFinConcursoActionListener.");
        getPopUpRegistrarFinConcurso().hide();
    }

    public void openDialogGestionarCobroComisionesActionListener(ActionEvent actionEvent) {
        logger.warning("Inside openDialogGestionarCobroComisionesActionListener.");
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        Boolean result = Boolean.FALSE;
        result = llenarComisionesNoPagadas();
        logger.warning("result: " + result);
        if (!result) {
            getPopUpGestionarCobroComisiones().show(popupHints);
        } else {
            JSFUtils.addFacesErrorMessage("No hay comisiones pendientes por cobrar.");
        }

    }

    public void openDialogDeterminarFactibilidadActionListener(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();

        consultarFactibilidad();

        String headerAction = (String) JSFUtils.resolveExpression("#{viewScope.headerAction}");

        if ((headerAction != null) && (headerAction.trim().length() > 0)) {
            if (headerAction.equalsIgnoreCase("solicitarDeterminarFactibilidad")) {
                logger.warning("Entra en solicitarDeterminarFactibilidad");
                gestorOperacionesBean.setCamposFactibilidad(Boolean.FALSE);
                getPopUpDeterminarFactibilidad().show(popupHints);
            }
            if (headerAction.equalsIgnoreCase("solicitarCalculoInteresesCobro")) {
                logger.warning("Entra en solicitarCalculoInteresesCobro");

                if (gestorOperacionesBean.getEsEnvioGasto()) {
                    if (!(validaCalculoIntereses())) {
                        setMensajeConfirmacion("¿Está seguro que desea iniciar el proceso de Envió al gasto?");
                        showPopupConfirmarHeaderAction();
                    } else {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ENVIO_GASTO"));
                    }
                } else {
                    if (!(validaCalculoIntereses())) {
                        gestorOperacionesBean.setCamposFactibilidad(Boolean.TRUE);
                        getPopUpDeterminarFactibilidad().show(popupHints);
                    } else {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CALCULO_INTERES"));
                    }
                }
            }
        }
    }

    public void openSplitActionListener(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpSplit().show(popupHints);
    }

    public void openFormalizacionParcialActionListener(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpFormalizacionParcial().show(popupHints);
    }

    public void openRegistrarFinCorcursoActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en openRegistrarFinCorcursoActionListener");
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Long idOperacion = null;
        Long idImplementacion = null;

        try {
            if (null != gestorOperacionesBean.getIdOperacion()) {
                idOperacion = gestorOperacionesBean.getIdOperacion();
            } else {
                logger.warning("El id de la operacion es nulo.");
            }
            operationBinding = bindings.getOperationBinding("obtenerIdImplementacionByIdOperacion");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.execute();
            idImplementacion = (Long) operationBinding.getResult();
        } catch (Exception e) {
            logger.warning("Error al obtener el id de Implementacion.");
        }
        try {
            operationBinding = null;
            if (null == idImplementacion) {
                logger.warning("El id de implementacion es nulo.");
            } else {
                operationBinding = bindings.getOperationBinding("inicializarLoteImplementacionFinConcurso");
                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                operationBinding.execute();
            }
        } catch (Exception e) {
            logger.warning("Error en openRegistrarFinCorcursoActionListener." + e.getClass() + "." + e);
        }
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpRegistrarFinConcurso().show(popupHints);
    }


    public void setPopUpFormalizacionParcial(RichPopup popUpFormalizacionParcial) {
        this.popUpFormalizacionParcial = popUpFormalizacionParcial;
    }

    public RichPopup getPopUpFormalizacionParcial() {
        return popUpFormalizacionParcial;
    }

    public void setPopUpSplit(RichPopup popUpSplit) {
        this.popUpSplit = popUpSplit;
    }

    public RichPopup getPopUpSplit() {
        return popUpSplit;
    }

    public void setPopUpGestionarCobroComisiones(RichPopup popUpGestionarCobroComisiones) {
        this.popUpGestionarCobroComisiones = popUpGestionarCobroComisiones;
    }

    public void setPopUpRegistrarFinConcurso(RichPopup popUpRegistrarFinConcurso) {
        this.popUpRegistrarFinConcurso = popUpRegistrarFinConcurso;
    }

    public RichPopup getPopUpRegistrarFinConcurso() {
        return popUpRegistrarFinConcurso;
    }

    public RichPopup getPopUpGestionarCobroComisiones() {
        return popUpGestionarCobroComisiones;
    }

    public void setPopUpDeterminarFactibilidad(RichPopup popUpDeterminarFactibilidad) {
        this.popUpDeterminarFactibilidad = popUpDeterminarFactibilidad;
    }

    public RichPopup getPopUpDeterminarFactibilidad() {
        return popUpDeterminarFactibilidad;
    }

    public void cancelarCumplimientoCondicionActionListener(ActionEvent actionEvent) {
        popupInicioCondicionCumplimiento.hide();
    }


    public void seleccionarSolicitud(SelectionEvent selectionEvent) {
        LOGGER.warning("Inside seleccionarSolicitud.");
        
        Boolean isLinea = Boolean.FALSE;
        Boolean isSolicitud = Boolean.FALSE;
        habilitarBotonExcepcion = Boolean.FALSE;
        habilitarBotonVerDetalle = Boolean.FALSE;
        
        // Actualizamos row seleccionado en tabla
        JSFUtils.resolveMethodExpression("#{bindings.ConsultarReglasNoCumplidasDesembolsoExcepcionVO.collectionModel.makeCurrent}",
                                         Object.class, new Class[] { SelectionEvent.class }, new Object[] {
                                         selectionEvent });

        try {
            RichTreeTable treeTable = this.getTreeTable();
            RowKeySet rks = treeTable.getSelectedRowKeys();
            Iterator keys = rks.iterator();
        
            while (keys.hasNext()) {
                List key = (List) keys.next();
                treeTable.setRowKey(key);
                treeTable.getColumnSelection();
                Row row = null;
                Row rowLinea = null;
                Row rowContrato = null;

                JUCtrlHierNodeBinding nodeSolicitud = (JUCtrlHierNodeBinding) treeTable.getRowData();

                if (nodeSolicitud != null) {
                    row = nodeSolicitud.getRow();
                } else {
                    logger.warning("nodeSolicitud null.");
                }

                JUCtrlHierNodeBinding nodeLinea = nodeSolicitud.getParent();

                if (nodeLinea != null) {
                    rowLinea = nodeLinea.getRow();
                } else {
                    logger.warning("nodeLinea null.");
                }

                JUCtrlHierNodeBinding nodeContrato = nodeLinea.getParent();

                if (nodeContrato != null) {
                    rowContrato = nodeContrato.getRow();
                } else {
                    isSolicitud = Boolean.TRUE;
                }
                
                JUCtrlHierNodeBinding nodeParent = nodeLinea.getParent();
                ArrayList nodeChildren = nodeSolicitud.getChildren();

                idOp =
                    (null == gestorOperacionesBean.getIdOperacion()) ? null :
                    Long.parseLong(gestorOperacionesBean.getIdOperacion().toString());

                logger.warning("idOperacion: " + idOp);
                
                if (nodeParent != null && nodeChildren == null && nodeContrato != null) {
                    isLinea = Boolean.TRUE;
                }
                
                if (isSolicitud) {
                    logger.warning("Solicitud seleccionada.");
                    
                    if (nodeSolicitud != null) {
                        idSolicitud =
                            (null == nodeSolicitud.getAttribute("Id")) ? null :
                            Long.parseLong(nodeSolicitud.getAttribute("Id").toString());    
                    }
                    
                    List<JUCtrlHierNodeBinding> solicitudChildren = nodeSolicitud.getChildren(); // Otenemos las lineas de la solicitud
                    obtenerDatosSolicitud(solicitudChildren);
                }
                
                if (!isLinea && nodeContrato != null) {
                    logger.warning("Linea seleccionada.");
                    
                    JUCtrlHierNodeBinding nodeSolicitudParent = nodeSolicitud.getParent(); // Obtenemos el nodo linea del contrato seleccionado
                    List<JUCtrlHierNodeBinding> solicitudChildren = null;
                    
                    if (nodeSolicitudParent != null) {
                        solicitudChildren = nodeSolicitudParent.getChildren(); // Otenemos las lineas de la solicitud   
                        
                        idSolicitud =
                            (null == nodeSolicitudParent.getAttribute("Id")) ? null :
                            Long.parseLong(nodeSolicitudParent.getAttribute("Id").toString());
                    } else {
                        logger.warning("Nodo Solicitud es Null.");
                    }
                    
                    obtenerDatosSolicitud(solicitudChildren);
                }
                
                if (nodeContrato != null && isLinea) {
                    logger.warning("Contrato seleccionado.");
                    
                    JUCtrlHierNodeBinding nodeLineaParent = nodeSolicitud.getParent(); // Obtenemos el nodo linea del contrato seleccionado
                    JUCtrlHierNodeBinding nodeSolicitudParent = null;
                    List<JUCtrlHierNodeBinding> solicitudChildren = null;
                    
                    if (nodeLineaParent != null) {
                        nodeSolicitudParent = nodeLineaParent.getParent(); // Obtenemos el nodo solicitud del contrato seleccionado
                        logger.warning("idSolicitud: " + nodeLineaParent.getAttribute("Id"));
                        
                    } else {
                        logger.warning("Nodo Linea es Null.");
                    }
                    
                    if (nodeSolicitudParent != null) {
                        solicitudChildren = nodeSolicitudParent.getChildren(); // Otenemos las lineas de la solicitud    
                        
                        idSolicitud =
                            (null == nodeSolicitudParent.getAttribute("Id")) ? null :
                            Long.parseLong(nodeSolicitudParent.getAttribute("Id").toString());
                        
                    } else {
                        logger.warning("Nodo Solicitud es Null.");
                    }
                    
                    obtenerDatosSolicitud(solicitudChildren);
                }
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(treeTable);
                btn_solicitarExcepcion.setDisabled(habilitarBotonExcepcion);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getBtn_solicitarExcepcion());
            }
        } catch (Exception e) {
            logger.warning("Ha ocurrido un error al seleccionar un elemento del Tree: ", e);
        }
    }
    
    public Long obtenerContratoActual(List<Long> contratosList) {
        logger.warning("Inside obtenerContratoActual.");
        Long idContratoActual = null;
        
        idContratoActual = contratosList.get(0);
        
        for (Long idContract : contratosList) {
            if (idContratoActual < idContract) {
                idContratoActual = idContract;
            }
        }
        
        logger.warning("idContratoActual: " + idContratoActual);
        return idContratoActual;
    }
    
    public void obtenerDatosSolicitud(List<JUCtrlHierNodeBinding> solicitudChildren) {
        logger.warning("Inside obtenerDatosSolicitud.");
        
        List<Long> contratoListKey = new ArrayList<Long>();
        
        if (solicitudChildren != null) {
            if (solicitudChildren.size() > 0) {
                
                for (JUCtrlHierNodeBinding nodeSolicitudIter : solicitudChildren) {
                    logger.warning("Linea : " + nodeSolicitudIter);
                    
                    idLinea =   
                        (null == nodeSolicitudIter.getAttribute("IdLineaCredito")) ? null :
                        Long.parseLong(nodeSolicitudIter.getAttribute("IdLineaCredito").toString());
                    
                    if (nodeSolicitudIter.hasChildren()) { // Verificamos si la Linea tiene Contratos
                        List<JUCtrlHierNodeBinding> lineaChildren = nodeSolicitudIter.getChildren();
                        
                        if (lineaChildren != null) {
                            if (lineaChildren.size() > 0) {
                                    
                                for (JUCtrlHierNodeBinding nodeLineaIter : lineaChildren) {
                                    logger.warning("Contrato : " + nodeLineaIter);
                                    
                                    idContrato =
                                        (null == nodeLineaIter.getAttribute("Id")) ? null :
                                        Long.parseLong(nodeLineaIter.getAttribute("Id").toString());
                                    
                                    if (idContrato != null ) {
                                        contratoListKey.add(idContrato);    
                                        idContrato =
                                            (null == nodeLineaIter.getAttribute("Id")) ? null :
                                            Long.parseLong(nodeLineaIter.getAttribute("Id").toString());
                                    }
                                }   
                            } else {
                                logger.warning("No existen contratos para la linea.");
                            }
                        } else {
                            logger.warning("Contratos null.");
                        }   
                    } else {
                        logger.warning("Linea " + idLinea + " sin contratos.");
                    }
                }   
            } else {
                logger.warning("No existen lineas para la solicitud.");
            }
        } else {
            logger.warning("Solicitud sin Lineas.");
        }
        
        if (contratoListKey != null) {
            if (contratoListKey.size() > 0) {
                idContrato = obtenerContratoActual(contratoListKey);    
            } else {
                logger.warning("Solicitud sin contratos");
                JSFUtils.addFacesErrorMessage("La solicitud seleccionada no contiene contratos de desembolso asociados.");
            }   
        }
        
        logger.warning("idSolicitud: " + idSolicitud);
        logger.warning("idLinea existente: " + idLinea);
        logger.warning("idContrato: " + idContrato);
    }
    
    public void cancelarSolicitudExcepcionActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside cancelarSolicitudExcepcionActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupSolicitarExcepcion().hide();
    }
    
    public void iniciarProcesoDesembolsoExcepcionActionListener(ActionEvent actionEvent) {
       
        /*
        recuperarReglasActivasDesembolso();
          RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
          getPopupSolicitarExcepcion().show(popupHints);
        */
       
        if(cargarSolicitudExcepcion()){
          RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
          getPopupSolicitarExcepcion().show(popupHints);
        }
        
    }
    
    public Boolean cargarSolicitudExcepcion(){
        LOGGER.warning("Inicia el metodo para cargar las reglas cumplidas no exceptuadas para confirmar inicio de preoceso");
        Boolean permiteInicioProceso = Boolean.FALSE;
        Map mapaDatos = new HashMap();
        String mensaje1 = "";
        try {           
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("recuperarReglasInicioProcesoDesembolsoPorExcepcion");
            operationBinding.getParamsMap().put("idOperacion", idOp);
            operationBinding.getParamsMap().put("idDesembolso", idContrato);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("OperationBinding recuperarReglasInicioProcesoDesembolsoPorExcepcion devuelve errores"
                                                                               +operationBinding.getErrors().toString());
               // JSFUtils.addFacesErrorMessage("Error al intentar recuperar reglas validadas ->"
               //                                                            +operationBinding.getErrors().toString());
            }else{
              mapaDatos = (Map)operationBinding.getResult();
              permiteInicioProceso = (Boolean)mapaDatos.get("permitirInicioProceso");
              
                    if(!permiteInicioProceso){
                           mensaje1 = (String)mapaDatos.get("mensaje1");
                           JSFUtils.addFacesInformationMessage(mensaje1);
                    }
            }
            
        } catch (Exception e) {           
            LOGGER.log(ADFLogger.ERROR, "Error en operBinding validar reglas desembolso " + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Ha ocurrido un error al validar las reglas para el desembolso ->"+e.getMessage().toString());
        }
        return permiteInicioProceso;
    }
    
    
    public void recuperarReglasActivasDesembolso(){
        LOGGER.warning("*Inf, Inicia metodo recuperarReglasActivasDesembolso");
        LOGGER.warning("*Inf, idOperacion:" +idOp);
        LOGGER.warning("*Inf, idDesembolso:" +idContrato);
        
        try {           
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("setpIdOperacionContratoSolExc");
            operationBinding.getParamsMap().put("idOp", idOp);
            operationBinding.getParamsMap().put("idCont", idContrato);
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("***Error, OperationBinding setpIdOperacionContratoSolExc devuelve errores");
                JSFUtils.addFacesErrorMessage("Error al intentar recuperar reglas activas ->"
                                                                             +operationBinding.getErrors().toString());
            }
            
        } catch (Exception e) {           
            LOGGER.log(ADFLogger.ERROR, "Error en operBinding validar reglas desembolso " + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Ha ocurrido un error al validar las reglas para el desembolso ->"+e.getMessage().toString());
        }
        
    };
    
    
    
    public void aceptarSolicitudExcepcionActionListener(ActionEvent actionEvent) {
        LOGGER.warning("Ingresa metodo aceptarSolicitudExcepcionActionListener");
        //Long idSolicitud = null;
        Integer reglaSCR = null;
        Integer reglaMora = null;
        Integer reglaLimites = null;
        Integer regla = null;
        DCIteratorBinding voSolicitudExcepcion = null;
        RowSetIterator rowSetIterator = null;
        Row row = null;
        List<String> reglasParam = new ArrayList<String>();
        String PARAM_SCR = "SCR";
        String PARAM_MORA = "MORA";
        String PARAM_LIMITES = "LIMITES";
                    
        //--------------                                       ---------------->>
    
        try {           
            BindingContainer binding = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = binding.getOperationBinding("recuperarRegistrosReglasValidadasVO");            
            operationBinding.execute();

            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("OperationBinding recuperarRegistrosReglasValidadasVO devuelve errores");
            }else{
                reglasParam = (List<String>)operationBinding.getResult();
                
                if(reglasParam != null){
                    
                        for(String reglaDesc : reglasParam ){
                            if(reglaDesc.equals("MORA")){
                                reglaMora = 1;                        
                            }
                            if(reglaDesc.equals("SCR")){
                                reglaSCR = 2;
                            }
                            if(reglaDesc.equals("LIMITES")){
                                reglaSCR = 6;
                            }
                        }
                }else{
                    LOGGER.warning("***Error, no se recuperaron reglas para iniciarl el proceso");
                }
                
            }
            
        } catch (Exception e) {           
            LOGGER.log(ADFLogger.ERROR, "Error en operBinding validar reglas desembolso " + e.getClass() + ":" + e.getMessage());
            JSFUtils.addFacesErrorMessage("Ha ocurrido un error al validar las reglas para el desembolso ->"+e.getMessage().toString());
        }
         
       //--------------                                       ---------------->>
       /* 
        voSolicitudExcepcion = ADFUtils.getDCBindingContainer().findIteratorBinding("SolicitudExcepcionVOIterator");
        rowSetIterator = voSolicitudExcepcion.getRowSetIterator();
        row = rowSetIterator.first();
        while(row!=null){
            LOGGER.warning("Valor IdSolicitud: " + row.getAttribute("IdSolicitud").toString());
            LOGGER.warning("Valor IdTcaEstado: " + row.getAttribute("IdTcaEstado").toString());
            idSolicitud = Long.parseLong(row.getAttribute("IdSolicitud").toString());
            regla = Integer.parseInt(row.getAttribute("IdTcaEstado").toString());
            if(regla == REGLA_MORA){
                reglaMora = 1;
                LOGGER.warning("reglaMora--->" + reglaMora);
            }
            if(regla == REGLA_SCR){
                reglaSCR = 2;
                LOGGER.warning("reglaSCR--->" + reglaSCR);
            }
            if(regla == REGLA_LIMITES){
                reglaLimites = 6;
                LOGGER.warning("reglaLimites--->" + reglaLimites);
            }    
            row = rowSetIterator.next();
        }
       */
        //--------------                                       ---------------->>
        
        
        if (reglaSCR != null) {
            reglasParam.add(PARAM_SCR);
        }
        if (reglaMora != null) {
            reglasParam.add(PARAM_MORA);
        }
        if (reglaLimites != null) {
            reglasParam.add(PARAM_LIMITES);
        }
        
        /*Inicia Incidencia 5979*/
        /*
        LOGGER.warning("id de Solicitud final--->" + idSolicitud);
        String idSolicitudEnviar = String.valueOf(idSolicitud);
        Boolean respuestaMetodo = inicioDesembolsoExcepcion(reglasParam, idSolicitudEnviar); 
        logger.warning("valor obtenido del metodo inicioDesembolsoExcepcion: " + respuestaMetodo);
        if (respuestaMetodo == Boolean.TRUE) {            
            LOGGER.warning("Inicia metodo para insertar en agregarTreExcepcionSolicitud");
            if(reglaMora!=null){
                Boolean existeRegistroIdSolicitudIdRegla = busquedaDesembolsoExcepcionExiste(idSolicitud, reglaMora);
                if(!existeRegistroIdSolicitudIdRegla){
                    LOGGER.warning("Inicia Metodo insertar Mora...");
                    insertarRegistroReglaDesembolsoExcepcion(idSolicitud, reglaMora);
                }
                else{
                    LOGGER.warning("Ya existe un registro en DB con idSolicitud " + idSolicitud + " y idRegla " + reglaMora);
                }
                
            }
            if(reglaSCR!=null){
                Boolean existeRegistroIdSolicitudIdRegla = busquedaDesembolsoExcepcionExiste(idSolicitud, reglaSCR);
                if(!existeRegistroIdSolicitudIdRegla){
                    LOGGER.warning("Inicia Metodo insertar SCR...");
                    insertarRegistroReglaDesembolsoExcepcion(idSolicitud, reglaSCR);
                }
                else{
                    LOGGER.warning("Ya existe un registro en DB con idSolicitud " + idSolicitud + " y idRegla " + reglaSCR);
                }
            }
            if(reglaLimites!=null){
                Boolean existeRegistroIdSolicitudIdRegla = busquedaDesembolsoExcepcionExiste(idSolicitud, reglaLimites);
                if(!existeRegistroIdSolicitudIdRegla){
                    LOGGER.warning("Inicia Metodo insertar Limites...");
                    insertarRegistroReglaDesembolsoExcepcion(idSolicitud, reglaLimites);
                }
                else{
                    LOGGER.warning("Ya existe un registro en DB con idSolicitud " + idSolicitud + " y idRegla " + reglaLimites);
                }
            }
        } else {
            LOGGER.warning("El metodo para consumir el servicio inicioDesembolsoExcepcion devuelve ERROR, no se inserta o actualiza en base de datos para Tre_Excepcion_Solicitud");
        }
        */
        
        LOGGER.warning("id de Solicitud final--->" + idSolicitud);        
        
        String idSolicitudEnviar = String.valueOf(idSolicitud);
        
        LOGGER.warning("Se comienza a insertar en agregarTreExcepcionSolicitud");
        if(reglaMora!=null){
            Boolean existeRegistroIdSolicitudIdRegla = busquedaDesembolsoExcepcionExiste(idSolicitud, reglaMora);
            if(!existeRegistroIdSolicitudIdRegla){
                LOGGER.warning("Inicia Metodo insertar Mora...");
                insertarRegistroReglaDesembolsoExcepcion(idSolicitud, reglaMora);
            }
            else{
                LOGGER.warning("Ya existe un registro en DB con idSolicitud " + idSolicitud + " y idRegla " + reglaMora);
            }
            
        }
        if(reglaSCR!=null){
            Boolean existeRegistroIdSolicitudIdRegla = busquedaDesembolsoExcepcionExiste(idSolicitud, reglaSCR);
            if(!existeRegistroIdSolicitudIdRegla){
                LOGGER.warning("Inicia Metodo insertar SCR...");
                insertarRegistroReglaDesembolsoExcepcion(idSolicitud, reglaSCR);
            }
            else{
                LOGGER.warning("Ya existe un registro en DB con idSolicitud " + idSolicitud + " y idRegla " + reglaSCR);
            }
        }
        if(reglaLimites!=null){
            Boolean existeRegistroIdSolicitudIdRegla = busquedaDesembolsoExcepcionExiste(idSolicitud, reglaLimites);
            if(!existeRegistroIdSolicitudIdRegla){
                LOGGER.warning("Inicia Metodo insertar Limites...");
                insertarRegistroReglaDesembolsoExcepcion(idSolicitud, reglaLimites);
            }
            else{
                LOGGER.warning("Ya existe un registro en DB con idSolicitud " + idSolicitud + " y idRegla " + reglaLimites);
            }
        }
        LOGGER.warning("Se finaliza insercion en agregarTreExcepcionSolicitud");
        
        LOGGER.warning("Se hace la llamada al metodo inicioDesembolsoExcepcion");
        Boolean respuestaMetodo = inicioDesembolsoExcepcion(reglasParam, idSolicitudEnviar); 
        logger.warning("valor obtenido del metodo inicioDesembolsoExcepcion: " + respuestaMetodo);
        if (respuestaMetodo == Boolean.TRUE) {
            logger.warning("El servicio inicioDesembolsoExcepcion se a ejecutado correctamente");
        } else {
            LOGGER.warning("El metodo para consumir el servicio inicioDesembolsoExcepcion devuelve ERROR");
        }
        
        
        
        /*Finaliza incidencia FNXII-5979*/
        LOGGER.warning("Finaliza metodo aceptarSolicitudExcepcionActionListener");
        
        getPopupSolicitarExcepcion().hide();
    }
    
    private Boolean inicioDesembolsoExcepcion(List<String> reglasParam, String idSolicitud) {
        logger.warning("Ingresa metodo inicioDesembolsoExcepcion");
        logger.warning("Valor idSolicitud a enviar: " + idSolicitud);
        Boolean respuesta = Boolean.FALSE;
        Map resultado = null;
        try {
            logger.log(ADFLogger.TRACE, "Inside inicioDesembolsoExcepcion.");
            BindingContainer bindings = ADFUtils.getBindingContainer();
            GestorOperacionesBean gestorOperacionesBean = null;
            OperationBinding operationBinding = null;

            gestorOperacionesBean =
                (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
            operationBinding = bindings.getOperationBinding("inicioDesembolsoExcepcion");
            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            operationBinding.getParamsMap().put("idOperacion", idOp);
            
            operationBinding.getParamsMap().put("loginUsuario",
                                                ADFContext.getCurrent().getSecurityContext().getUserName());
            
            operationBinding.getParamsMap().put("reglasParam", reglasParam);
            
            operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
            
            operationBinding.execute();
            
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                respuesta = Boolean.FALSE;
            } else {
                /*
                resultado = (Map)operationBinding.getResult();
                Boolean success = (Boolean) resultado.get("success");
                String message = (String) resultado.get("message");
                if(null != success && success.equals(Boolean.TRUE)){
                    JSFUtils.addFacesInformationMessage("Se ha iniciado el proceso de Gesti\u00F3n de Cobro correctamente");
                }else{
                    if(message.compareTo("Ya existe un proceso de Gestión de Cobro en ejecución") == 0)
                        JSFUtils.addFacesErrorMessage(message);
                    else if(message.compareTo("Error al iniciar el proceso GestionCobro") == 0)
                        JSFUtils.addFacesErrorMessage(message);
                        //JSFUtils.addFacesErrorMessage("No se ha iniciado el proceso de Gesti\u00F3n de Cobro");
                    else if (message == "")
                        JSFUtils.addFacesErrorMessage("No se ha iniciado el proceso de Gesti\u00F3n de Cobro");
                }
                */
                resultado = (Map)operationBinding.getResult();
                Boolean success = (Boolean) resultado.get("success");
                String message = (String) resultado.get("message");
                logger.warning("Respuesta del servicio: " + success);
                logger.warning("Mensaje del servicio: " + message);
                if(null != success && success.equals(Boolean.TRUE)){
                    JSFUtils.addFacesInformationMessage(message);
                    respuesta = Boolean.TRUE;
                }else{
                    JSFUtils.addFacesErrorMessage(message);
                    respuesta = Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en inicioDesembolsoExcepcion " + e.getClass() + ":" + e.getMessage());
            respuesta = Boolean.FALSE;
        }
        logger.warning("Valor de retorno: " + respuesta);
        logger.warning("Finaliza metodo inicioDesembolsoExcepcion");
        return respuesta;
    }
    
    public Boolean busquedaDesembolsoExcepcionExiste(Long idSolicitud, Integer regla){
        Boolean respuesta = Boolean.FALSE;
        
        try{
                LOGGER.warning("Inicia Metodo busquedaDesembolsoExcepcionExiste por idSolicitud " + idSolicitud + " y idRegla " + regla);
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("existeDesembolsoExcepcionByIdSolicitudIdRegla");
                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                operationBinding.getParamsMap().put("regla", regla);
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding devuelve errores al buscar si existe el registro con idSolicitud " + idSolicitud + " y idRegla " + regla);
                }else{
                        LOGGER.warning("***Carga de parametros Listo, redireccionando... ");
                        respuesta = (Boolean)operationBinding.getResult();
                    }
            }catch(Exception e){
                    LOGGER.warning("Acceso negado...");
                    LOGGER.log(ADFLogger.ERROR, "Error en busquedaDesembolsoExcepcionExiste ", e);
                    JSFUtils.addFacesErrorMessage("Error al Consultar datos de la operacion. Por favor intente más tarde.");
            }
        
        LOGGER.warning("Finaliza Metodo busquedaDesembolsoExcepcionExiste por idSolicitud " + idSolicitud + " y idRegla " + regla);
        return respuesta;
    }
    
    public Boolean insertarRegistroReglaDesembolsoExcepcion(Long idSolicitud, Integer regla){
        Boolean respuesta = Boolean.FALSE;
        
        try{
                LOGGER.warning("Inicia Metodo insertar regla... " + regla );
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = binding.getOperationBinding("agregarTreExcepcionSolicitud");
                operationBinding.getParamsMap().put("idSolicitud", idSolicitud);
                operationBinding.getParamsMap().put("idTcaRegla", regla);
                operationBinding.getParamsMap().put("exceptuado", 0);
                operationBinding.getParamsMap().put("instanciaProceso", "null");
                operationBinding.getParamsMap().put("enProcesoExcepcion", 0);
                operationBinding.execute();
            
                if(!operationBinding.getErrors().isEmpty()){
                    LOGGER.warning("OperationBinding devuelve errores, regla: " + regla);
                }else{
                        LOGGER.warning("***Carga de parametros Listo, redireccionando... ");
                        respuesta = Boolean.TRUE;
                    }
            }catch(Exception e){
                    LOGGER.warning("Acceso negado...");
                    LOGGER.log(ADFLogger.ERROR, "Error en consultarDatosOperacion " + e.getClass() + ":" + e.getMessage());
                    JSFUtils.addFacesErrorMessage("Error al Consultar datos de la operacion. Por favor intente más tarde.");
            }
        
        return respuesta;
    }
    
    public void iniciarVerDetalleDesembolsoActionListener(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupVerDetalleDesembolso().show(popupHints);
        cargarValoresRegionVerDetalleDesembolso();
    }
    
    public void cargarValoresRegionVerDetalleDesembolso(){
        logger.warning("Ingresa al metodo cargarValoresRegionVerDetalleDesembolso.");
        /*JSFUtils.setExpressionValue("#{pageFlowScope.idOperacion}", idOp);
        JSFUtils.setExpressionValue("#{pageFlowScope.idContratoDesembolso}", idContrato);
        JSFUtils.setExpressionValue("#{pageFlowScope.idLineaCredito}", idLinea);
        JSFUtils.setExpressionValue("#{pageFlowScope.idEstadoSolicitud}", idTcaEstado);
        JSFUtils.setExpressionValue("#{pageFlowScope.idSolicitud}", idSolicitud);*/
        
        GestorOperacionesBean gestorOperacionBean =
            (GestorOperacionesBean) JSFUtils.resolveExpression("#{pageFlowScope.GestorOperacionesManagedBean}");
        
        if(null != gestorOperacionBean){
            Integer idEstadoInt = null;
            try{
             idEstadoInt = Integer.parseInt(idTcaEstado.toString());
            }catch(Exception e){
                logger.warning("ERROR al convertir IDESTADO a integer.", e);
            }
            try{
                gestorOperacionBean.setIdOperacionDesembolsos(idOp);
                gestorOperacionBean.setIdSolicitudDesembolsos(idSolicitud);
                gestorOperacionBean.setIdLineaCreditoDesembolsos(idLinea);
                gestorOperacionBean.setIdContratoDesembolsos(idContrato);
                gestorOperacionBean.setIdTcaEstadoDesembolsos(idEstadoInt);
            }catch(Exception e){
                logger.warning("ERROR al setear valores de desembolosos a GestorOperacionesBean", e);
            }
        }else{
            logger.warning("Instancia de GestorOperacionesBean es NULL");
        }
        logger.warning("Termino de cargar valores en metodo cargarValoresRegionVerDetalleDesembolso.");
    }
    
    public void cancelarVerDetalleOperacionActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside cancelarSolicitudExcepcionActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupVerDetalleDesembolso().hide();
    }

    public void setTreeTable(RichTreeTable treeTable) {
        this.treeTable = treeTable;
    }

    public RichTreeTable getTreeTable() {
        return treeTable;
    }

    public void setPopupSolicitarExcepcion(RichPopup popupSolicitarExcepcion) {
        this.popupSolicitarExcepcion = popupSolicitarExcepcion;
    }

    public RichPopup getPopupSolicitarExcepcion() {
        return popupSolicitarExcepcion;
    }

    public void setBtn_solicitarExcepcion(RichButton btn_solicitarExcepcion) {
        this.btn_solicitarExcepcion = btn_solicitarExcepcion;
    }

    public RichButton getBtn_solicitarExcepcion() {
        return btn_solicitarExcepcion;
    }

    public void setBtn_verDetalleDesembolso(RichButton btn_verDetalleDesembolso) {
        this.btn_verDetalleDesembolso = btn_verDetalleDesembolso;
    }

    public RichButton getBtn_verDetalleDesembolso() {
        return btn_verDetalleDesembolso;
    }

    public void setPopupVerDetalleDesembolso(RichPopup popupVerDetalleDesembolso) {
        this.popupVerDetalleDesembolso = popupVerDetalleDesembolso;
    }

    public RichPopup getPopupVerDetalleDesembolso() {
        return popupVerDetalleDesembolso;
    }

    public void solicitarSeguimientoSiemasActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside solicitarSeguimientoSiemasActionListener.");

        if (gestorOperacionesBean.getTrazabilidadFormalizacion()) {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindings.getOperationBinding("validaProcesos");
            operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
            operationBinding.getParamsMap().put("proceso", PROCESO_EVALUACIONES_SEGUIMIENTO_SIEMAS);
            Boolean result = (Boolean) operationBinding.execute();
            if (result) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_EVALUACIONES"));
            } else {
                BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
                OperationBinding operationBindingEstado = null;
                operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
                operationBindingEstado.getParamsMap().put("idOperacion",
                                                          Long.toString(gestorOperacionesBean.getIdOperacion()));
                Long resultEstado = (Long) operationBindingEstado.execute();
                if (resultEstado == ESTADO_SUSPENDIDO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
                } else {
                    if (resultEstado == ESTADO_FINALIZADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                    } else {
                        if (resultEstado == ESTADO_CANCELADO) {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                        } else {
                            logger.log(ADFLogger.TRACE,
                                       "Inside solicitarEvaluacionesMedioTerminoActionListener: " +
                                       actionEvent.getComponent().getId());
                            setMensajeConfirmacion("¿Esta seguro de iniciar el proceso de Evaluaciones SIEMAS de la operación " +
                                                   gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
                            showPopupConfirmarHeaderAction(); // Mostramos popup
                        }
                    }
                }
            }
        } else {
            logger.warning("La Operacion no se encuentra Aprobada.");
        }
    }

    public void setPopUpCambiarResponsable(RichPopup popUpCambiarResponsable) {
        this.popUpCambiarResponsable = popUpCambiarResponsable;
    }

    public RichPopup getPopUpCambiarResponsable() {
        return popUpCambiarResponsable;
    }

    public void solicitarCambiarResponsableActionListener(ActionEvent actionEvent) {
        logger.warning("Dentro de solicitarCambiarResponsableActionListener");
        setMensajeConfirmacion("¿Confirma Cambiar Responsable de la Operación?"); // Asignamos mensaje
        showPopupConfirmarHeaderAction(); // Mostramos popup
        logger.warning("Fuera de solicitarCambiarResponsableActionListener");
    }

    public void cerrarCambiarResponsableActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cerrarCambiarResponsableActionListener.");
        getPopUpCambiarResponsable().hide();
    }

    public void openDialogCambiarResponsable(ActionEvent actionEvent) {
        logger.warning("Dentro de openDialogCambiarResponsable");

        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("obtenerResponsable");
        operationBinding.getParamsMap().put("idOperacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.execute();
        String usuario = ((String) operationBinding.getResult());
        getPopUpCambiarResponsable().show(popupHints);

        ADFUtils.setBoundAttributeValue("ResponsableAttrValue", usuario);
        logger.warning("Responsable actualizado: " + usuario);
        
        logger.warning("Fuera de openDialogCambiarResponsable");
    }

    public Boolean llenarComisionesNoPagadas() {
        logger.log(ADFLogger.TRACE, "Inside llenarComisionesNoPagadas.");

        Boolean result1 = Boolean.FALSE;
        //Boolean result2 = Boolean.FALSE;
        //Boolean resultFinal = Boolean.FALSE;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        //Insertar desde servicio
        operationBinding = bindings.getOperationBinding("obtenerComisionesNoPagadas");
        operationBinding.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
        operationBinding.execute();
        result1 = (Boolean) operationBinding.getResult();
        logger.warning("obtenerComisionesNoPagadas: " + result1);

        /*
        //Insertar desde VO
        operationBinding = bindings.getOperationBinding("insertarComisiones");
        operationBinding.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
        operationBinding.execute();
        result2 = (Boolean) operationBinding.getResult();
        logger.warning("insertarComisiones: " + result2);

        if (!result1 && !result2) {
            resultFinal = Boolean.FALSE;
        } else {
            resultFinal = Boolean.TRUE;
        }

        resultFinal = result1 && result2;*/

        logger.warning("Lista vacia: " + result1);
        return result1;
    }

    public void solicitarRegistrarComision(ActionEvent actionEvent) {
        logger.warning("Entra en solicitarRegistrarComision, abre popup.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.getParamsMap().put("proceso", PROCESO_REGISTRAR_COMISION);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_REGISTRAR_COMISION"));
        } else {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      Long.toString(gestorOperacionesBean.getIdOperacion()));
            Long resultEstado = (Long) operationBindingEstado.execute();

            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {

                        logger.log(ADFLogger.TRACE,
                                   "Inside solicitarRegistrarComision: " + actionEvent.getComponent().getId());
                        setMensajeConfirmacion(getStringFromBundle("INICIAR_REGISTRO_COMISIONES")); // Asignamos mensaje
                        showPopupConfirmarHeaderAction(); // Mostramos popup
                    }
                }
            }
        }
        logger.warning("obtener id evento : " + actionEvent.getComponent().getId());
    }

    public RichPopup getPopUpDescargaRepCondiciones() {
        return popUpDescargaRepCondiciones;
    }

    public void setPopUpDescargaRepCondiciones(RichPopup popUpDescargaRepCondiciones) {
        this.popUpDescargaRepCondiciones = popUpDescargaRepCondiciones;
    }

    public void descargaRepCondicionesActionListener(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = null;
        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        this.getPopUpDescargaRepCondiciones().show(popupHints);
    }

    public void determinarFactibilidadActionListener(ActionEvent actionEvent) {

        logger.log(ADFLogger.TRACE, "Inside determinarFactibilidadActionListener.");

        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpDeterminarFactibilidad().show(popupHints);

    }

    public void aceptarDescargarRepCondiciones(ActionEvent actionEvent) {
        logger.warning("aceptarDescargarRepCondiciones: ");
        popUpDescargaRepCondiciones.hide();
    }

    public void cancelarDescargarRepCondiciones(ActionEvent actionEvent) {
        popUpDescargaRepCondiciones.hide();
    }

    private void flushAndCloseOutputStream(OutputStream output) {
        flushOutputStream(output);
        closeOutputStream(output);
    }

    private void flushOutputStream(OutputStream output) {
        try {
            if (output != null)
                output.flush();
        } catch (IOException ioex) {
            ;
        }
    }

    private void closeOutputStream(OutputStream output) {
        try {
            if (output != null)
                output.close();
        } catch (IOException ioex) {
            ;
        }
    }

    public void downloadCumplimiento(FacesContext facesContext, OutputStream outputStream) {
        logger.warning("downloadCumplimiento: ");
        Long idOperacion = gestorOperacionesBean.getIdOperacion();
        byte[] documentoAvisoCobro = null;
        try {
            //applyIE11Fix(DOCUMENTO_FILE_NAME);
        applyIE11Fix(getReporteFileName());
        logger.warning("Antes de escribir el archivo");
            documentoAvisoCobro = crearDoc(idOperacion);
            if (documentoAvisoCobro != null && documentoAvisoCobro.length > 0) {
                    logger.warning("Escribiendo archivo");
                outputStream.write(documentoAvisoCobro);
            }

        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            flushAndCloseOutputStream(outputStream);
            popUpDescargaRepCondiciones.hide();
        }
    }

    private byte[] crearDoc(long idOperacion) {
        String mensajeError = null;
        Boolean esError = Boolean.FALSE;

        ConsultarCumplimientoCondicionRequestType request = new ConsultarCumplimientoCondicionRequestType();

        ConsultarCumplimientoCondicionResponseType response = null;

        try {
            Condicion12BndQSService condicion = this.initCondicionService();
            CondicionPT condicionPT = condicion.getCondicion12BndQSPort();
            // Add your code to call the desired methods.

            request.setIdOperacion(idOperacion);

            String mensaje = null;
            StringWriter xmlEntrada = null;
            StringWriter xmlSalida = null;
            mensaje = "Invocando Servicio - Aviso de cobro comision-";
            //try{xmlEntrada = writeXMLRequest(request, request.getClass());}catch(Exception ex){;}
            try {
                LOGGER.log(ADFLogger.WARNING, ">HNWS" + writeXMLRequest(request, request.getClass()));
            } catch (Exception ex) {
                ;
            }
            response = condicionPT.reporteCumplimientoCondicion(request);
            mensaje = "Invocando Servicio - Aviso de cobro comision -";
            try {
                LOGGER.log(ADFLogger.WARNING, ">HNWS" + writeXMLRequest(response, response.getClass()));
            } catch (Exception ex) {
                ;
            }

            if (response.getResultado().getResult().value() == "ERROR") {
                mensajeError = response.getResultado().getMessage();
                mensajeError = mensajeError.concat(response.getResultado().getError().getErrorDescription());
                esError = Boolean.TRUE;
            }
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error al generar el aviso de cobro " + e.getClass() + ":" + e.getMessage());

        } finally {
            if (esError) {
                JSFUtils.addFacesErrorMessage(mensajeError);
            }
        }
        return response != null ? response.getDocumento().getContent() : null;
    }

    public StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);
        return writer;
    }

    private void applyIE11Fix(String docName) {
        logger.warning("Estas usando internet explorer");
        RequestContext requestCtx = RequestContext.getCurrentInstance();
        Agent agent = requestCtx.getAgent();
        String version = agent.getAgentVersion();
        String browser = agent.getAgentName();

        //if ie 11
        if (browser != null && "ie".equals(browser) && version != null && version.startsWith("11")) {
            logger.warning("Internet explorer 11");
            HttpServletResponse resp =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            String finalName = docName;

            try {
                finalName = URLEncoder.encode(docName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                ;
            }

            resp.setHeader("Content-Disposition", "attachment; filename=\"" + finalName + "\"");
            resp.setContentType("application/octet-stream");
        }
    }

    public String getReporteFileName() {
        return DOCUMENTO_FILE_NAME;
    }

    private String obtenFechaHora(){
        logger.warning("Entra enobtenFechaHora.");
        Date date = new Date();
        SimpleDateFormat  formatter = new SimpleDateFormat(FORMATO_FECHA_HORA, new Locale(LOCALE_ESPAÑOL, LOCALE_MX));
        logger.warning("Fecha con formato : " + formatter.format(date));
        return formatter.format(date);
    }
    
    private Condicion12BndQSService initCondicionService() {
        DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator("ConfiguracionVOIterator");
        ViewObject configuracionVoImpl = dcItteratorBindings.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[] { IWsdlLocation.CONDICION }));
        String wsdl = row == null ? null : (String) row.getAttribute("Valor");

        return IWsdlLocation.Service.getInstance(Condicion12BndQSService.class, wsdl);
    }

    public void validaSeleccionValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "INTO validaSeleccionValueChangeListener.");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean rowSeleccionado = null;
        if (null != valueChangeEvent.getNewValue()) {
            rowSeleccionado = (Boolean) (valueChangeEvent.getNewValue());
            logger.log(ADFLogger.WARNING, "Valor el row seleccionado.." + rowSeleccionado);
        } else {
            gestorOperacionesBean.setFilaSeleccionada(Boolean.FALSE);
            logger.log(ADFLogger.WARNING, "Valor del row es nulo.." + rowSeleccionado);
        }
        if (rowSeleccionado == Boolean.TRUE) {
            gestorOperacionesBean.setFilaSeleccionada(Boolean.TRUE);
        } else {
            gestorOperacionesBean.setFilaSeleccionada(Boolean.FALSE);
        }
    }

    public void solicitarRegistrarFinCursoActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Inside solicitarRegistrarFinCursoActionListener.");

        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Boolean validaLoteSeleccionado = Boolean.FALSE;
        try {
            operationBinding = bindings.getOperationBinding("validaLoteSeleccionado");
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                validaLoteSeleccionado = (Boolean) operationBinding.getResult();
                logger.warning("El lote seleccionado es :" + validaLoteSeleccionado);
            } else {
                logger.warning("El valor de retorno es nulo.");
            }
            if (validaLoteSeleccionado) {
                setMensajeConfirmacion(getStringFromBundle("MSG_28_DETALLE_OPERACION"));
                showPopupConfirmarHeaderAction();
            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_27_DETALLE_OPERACION"));
            }
        } catch (Exception e) {
            logger.warning("Error al seleccionar los lotes de implementacion." + e.getClass() + "." + e);
        }
        //showPopupConfirmarHeaderAction();
    }

    public void consultarFactibilidad() {
        logger.warning("Inside consultarFactibilidad.");

        String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
        oracle.jbo.domain.Number idOperacionValue = new oracle.jbo.domain.Number(Long.valueOf(idOperacion));

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("eligeOperacionFactible");
        operationBinding.getParamsMap().put("idOperacion", idOperacionValue);
        operationBinding.execute();

        consultarTasasReferencia();
    }

    //Metodo para llenar campo autocompletable
    public void consultarTasasReferencia() {
        logger.warning("Inside consultarTasasReferencia.");

        String descripcionMoneda = obtenerDescripcionMoneda();

        if (descripcionMoneda != null) {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("setvarCodigoMoneda");
            operationBinding.getParamsMap().put("value", descripcionMoneda);
            operationBinding.execute();
        } else {
            logger.warning("La descripcion de la moneda es Null.");
        }
    }

    public String obtenerDescripcionMoneda() {
        logger.warning("Inside obtenerDescripcionMoneda.");

        Integer idTerminoMontoFormalizado = 12;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("obtenerMonedaTerminoPorId");
        operationBinding.getParamsMap().put("idTcaTermino", idTerminoMontoFormalizado);
        operationBinding.getParamsMap().put("idOperacion", gestorOperacionesBean.getIdOperacion());
        String descripcionMoneda = (String) operationBinding.execute();

        return descripcionMoneda;
    }

    public void actualizarFactibilidad() {
        logger.warning("Inside actualizarFactibilidad.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarOperacionFactible");
        operationBinding.execute();
    }

    public void actualizarOperacion() {
        logger.warning("Inside actualizarOperacion.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarOperacion");
        operationBinding.execute();
    }

    public void actualizarCalculoInteresesCobro() {
        logger.warning("Inside actualizarCalculoInteresesCobro.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("actualizarCalculoInteresesCobro");
        operationBinding.execute();
    }

    public Boolean validarCampos() {
        logger.warning("Inside validarCampos.");

        Boolean result = Boolean.FALSE;

        AttributeBinding tasaTotal;
        tasaTotal = ADFUtils.findControlBinding("TasaTotal");
        AttributeBinding valorTasa;
        valorTasa = ADFUtils.findControlBinding("ValorTasa");
        AttributeBinding valorSpread;
        valorSpread = ADFUtils.findControlBinding("ValorSpread");
        AttributeBinding codigoTasaReferencia;
        codigoTasaReferencia = ADFUtils.findControlBinding("CodigoTasaReferencia");
        String idTcaTipoTasa =
            (ADFUtils.getBoundAttributeValue("IdTcaTipoTasa") == null) ? null :
            ADFUtils.getBoundAttributeValue("IdTcaTipoTasa").toString();
        String esFactible =
            (ADFUtils.getBoundAttributeValue("EsFactible") == null) ? null :
            ADFUtils.getBoundAttributeValue("EsFactible").toString();

        String headerAction = (String) JSFUtils.resolveExpression("#{viewScope.headerAction}");

        if ((ADFUtils.getBoundAttributeValue("EsFactible") == null) ||
            (!(ADFUtils.getBoundAttributeValue("EsFactible").toString().length() > 0))) {
            logger.warning("Es necesario seleccionar una opcion de Factibilidad.");
        } else {
            Integer isFactible = Integer.valueOf(esFactible);
            if (isFactible != null) {

                if ((headerAction != null) && (headerAction.trim().length() > 0)) {
                    if (headerAction.equalsIgnoreCase("solicitarDeterminarFactibilidad")) {
                        logger.warning("Entra en solicitarDeterminarFactibilidad");

                        isFactible = isFactible.compareTo(1) == 0 ? 1 : 0;
                    }
                    if (headerAction.equalsIgnoreCase("solicitarCalculoInteresesCobro")) {
                        logger.warning("Entra en solicitarCalculoInteresesCobro");

                        isFactible = isFactible.compareTo(1) == 0 ? 0 : 1;
                    }
                }

                logger.warning("isFactible: " + isFactible);

                //No Factible
                if (isFactible.compareTo(0) == 0) {

                    if ((ADFUtils.getBoundAttributeValue("IdTcaTipoTasa") != null) &&
                        ((ADFUtils.getBoundAttributeValue("IdTcaTipoTasa").toString().length() > 0))) {
                        Integer tipoTasa = Integer.valueOf(idTcaTipoTasa);
                        //Tasa Fija
                        if (tipoTasa.compareTo(2) == 0) {
                            if (valorTasa.getInputValue() != null &&
                                ((ADFUtils.getBoundAttributeValue("FechaFinalizacionEstudios") != null) &&
                                 (ADFUtils.getBoundAttributeValue("FechaFinalizacionEstudios").toString().length() >
                                  0))) {
                                result = Boolean.TRUE;
                            }
                        }

                        //Tasa Variable
                        if (tipoTasa.compareTo(3) == 0) {
                            if (codigoTasaReferencia.getInputValue() != null && valorTasa.getInputValue() != null &&
                                valorSpread.getInputValue() != null && tasaTotal.getInputValue() != null &&
                                ((ADFUtils.getBoundAttributeValue("FechaFinalizacionEstudios") != null) &&
                                 (ADFUtils.getBoundAttributeValue("FechaFinalizacionEstudios").toString().length() >
                                  0))) {
                                result = Boolean.TRUE;
                            }
                        }
                    }
                }

                //Factible.
                if (isFactible.compareTo(1) == 0) {
                    result = Boolean.TRUE;
                }
            }
        }

        return result;
    }

    public List onSuggestTasaReferencia(String string) {
        logger.warning("Inside onSuggestTasaReferencia");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();

        SelectItem element = null;

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("CodigoTasaReferencia1");

        Row[] values = list.getAllRowsInRange();
        if (null != values) {
            for (Row obj : values) {
                String value = (String) obj.getAttribute("CodigoTasaReferencia");
                if (null != string && null != value) {
                    if (value.toUpperCase().contains(string.toUpperCase())) {
                        element = new SelectItem(value);
                        resultItems.add(element);
                    }
                }
            }
        } else {
            element = new SelectItem("");
            resultItems.add(element);
        }

        return resultItems;
    }
    
    public List onSuggestTasa(String string) {
        LOGGER.warning("*Inf, Into method onSuggestTasa");
        ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();;
        SelectItem element = null;
        //String moneda="USD";
        String monedaImplementar=null;
        monedaImplementar=obtenerDescripcionMoneda();
        LOGGER.warning("Moneda a implementar: "+monedaImplementar);
        
        List<String> listaObtenida= new ArrayList<String>();
        BindingContainer bindingsIA = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIA = null;

            operationBindingIA = bindingsIA.getOperationBinding("cargarLista");
            operationBindingIA.getParamsMap().put("nombre", string);
            operationBindingIA.getParamsMap().put("moneda", monedaImplementar);
            listaObtenida=(ArrayList<String>)operationBindingIA.execute();
        
            if(null!=listaObtenida){
                    if(listaObtenida.size()>0){
                        for(String valor :listaObtenida){
                                LOGGER.warning("Registro encontrado: " +valor);
                                element=new SelectItem(valor);
                                resultItems.add(element);
                            }
                        }
                }

      
        return resultItems;
    }


    public void tasaReferenciaValueChange(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside tasaReferenciaValueChange.");
        logger.warning("valueNewCodigoTasaReferencia: " + valueChangeEvent.getNewValue());

        AttributeBinding bindingValorTasa;
        bindingValorTasa = ADFUtils.findControlBinding("ValorTasa");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        String descripcionTasa = null;
        String codigo = null;
        BigDecimal valorActual = null;
        String descripcionMoneda=obtenerDescripcionMoneda();
        descripcionTasa = (String) valueChangeEvent.getNewValue();

        operationBinding = bindings.getOperationBinding("obtenerDatosTasaReferencia2");
        operationBinding.getParamsMap().put("descripcionTasa", descripcionTasa);
        operationBinding.getParamsMap().put("moneda", descripcionMoneda);
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Map<String, Object> resultado = new HashMap<String, Object>();
            resultado = (HashMap<String, Object>) operationBinding.getResult();

            valorActual = (BigDecimal) resultado.get("ValorActual");
            codigo = (String) resultado.get("Codigo");

            logger.warning("valorActual: " + valorActual);
            logger.warning("codigo: " + codigo);

            bindingValorTasa.setInputValue(valorActual);
            asignaBindingCodigoTasa(codigo);
            
            operationBinding = bindings.getOperationBinding("calcularTotal");
            operationBinding.execute();
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(getTasaTotalUIC());
        }
    }
    
    //El valor que se guardara en BD es el codigo que acompaña a la descripcion de la Tasa
    public void asignaBindingCodigoTasa(String codigo) {
        logger.warning("Inside asignaBindingCodigoTasa.");
        
        AttributeBinding bindingCodigoTasaReferencia;
        bindingCodigoTasaReferencia = ADFUtils.findControlBinding("CodigoTasaReferencia");

        bindingCodigoTasaReferencia.setInputValue(codigo);

        logger.warning("CodigoTasaReferencia: " + codigo);
    }

    public void setTasaTotalUIC(RichOutputFormatted tasaTotalUIC) {
        this.tasaTotalUIC = tasaTotalUIC;
    }

    public RichOutputFormatted getTasaTotalUIC() {
        return tasaTotalUIC;
    }

    public void varlorSpreadValueChange(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside varlorSpreadValueChange.");
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("calcularTotal");
        operationBinding.execute();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTasaTotalUIC());
    }

    public void setDeterminarFactibilidadUIC(RichCommandMenuItem determinarFactibilidadUIC) {
        this.determinarFactibilidadUIC = determinarFactibilidadUIC;
    }

    public RichCommandMenuItem getDeterminarFactibilidadUIC() {
        return determinarFactibilidadUIC;
    }

    public void valorTasaValueChange(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside valorTasaValueChange.");
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        AttributeBinding valueTasa;
        valueTasa = ADFUtils.findControlBinding("ValorTasa");
        String idTcaTipoTasa = (ADFUtils.getBoundAttributeValue("IdTcaTipoTasa") == null) ? null :
                                                     ADFUtils.getBoundAttributeValue("IdTcaTipoTasa").toString();
        
        
        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoTasa") != null) &&
            ((ADFUtils.getBoundAttributeValue("IdTcaTipoTasa").toString().length() > 0))) {
            Integer tipoTasa = Integer.valueOf(idTcaTipoTasa);
            
            //Tasa Fija
            if (tipoTasa.compareTo(2) == 0) {
                
                oracle.jbo.domain.Number valorTasa = (oracle.jbo.domain.Number) (valueChangeEvent.getNewValue());
                oracle.jbo.domain.Number limite = new oracle.jbo.domain.Number (0);
                logger.warning("valorTasa: " + valorTasa);
                
                if (valorTasa != null) {
                    if (valorTasa.compareTo(limite) == -1) {
                        valueTasa.setInputValue(null);
                        getValorTasaUIC().resetValue();
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getValorTasaUIC());
                    }
                }
            }

            //Tasa Variable
            if (tipoTasa.compareTo(3) == 0) {
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = null;
                operationBinding = bindings.getOperationBinding("calcularTotal");
                operationBinding.execute();
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(getTasaTotalUIC());
            }
        }
    }

    public void setValorTasaUIC(RichInputText valorTasaUIC) {
        this.valorTasaUIC = valorTasaUIC;
    }

    public RichInputText getValorTasaUIC() {
        return valorTasaUIC;
    }

    public void tipoTasaValueChange(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside tipoTasaValueChange.");
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        AttributeBinding valueTasa;
        valueTasa = ADFUtils.findControlBinding("ValorTasa");
        Integer tipoTasa = (Integer) valueChangeEvent.getNewValue();
        
        if ((ADFUtils.getBoundAttributeValue("ValorTasa") != null) &&
            ((ADFUtils.getBoundAttributeValue("ValorTasa").toString().length() > 0))) {
            
            oracle.jbo.domain.Number valorTasa = (oracle.jbo.domain.Number) (valueTasa.getInputValue());
            
            //Tasa Fija
            if (tipoTasa != null) {
                if (tipoTasa.compareTo(2) == 0) {
                    
                    oracle.jbo.domain.Number limite = new oracle.jbo.domain.Number (0);
                    
                    if (valorTasa.compareTo(limite) == -1) {
                        valueTasa.setInputValue(null);
                        getValorTasaUIC().resetValue();
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getValorTasaUIC());
                    }
                }    
            }
        }
    }
    
    public void solicitarEnviarGasto() {
        logger.log(ADFLogger.TRACE, "Inside solicitarEnviarGasto.");

        String esFactible =
            (ADFUtils.getBoundAttributeValue("EsFactible") == null) ? null :
            ADFUtils.getBoundAttributeValue("EsFactible").toString();

        if ((ADFUtils.getBoundAttributeValue("EsFactible") == null) ||
            (!(ADFUtils.getBoundAttributeValue("EsFactible").toString().length() > 0))) {
            logger.warning("Es necesario seleccionar una opcion de Factibilidad.");
        } else {
            Integer isFactible = Integer.valueOf(esFactible);
            if (isFactible != null) {
                //No Factible - Inicia Proceso Interes Cobro.
                if (isFactible.compareTo(1) == 0) {
                    BindingContainer bindings = ADFUtils.getBindingContainer();
                    OperationBinding operationBinding = null;

                    try {
                        operationBinding = bindings.getOperationBinding("inicioEnviarGasto");
                        String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
                        operationBinding.getParamsMap().put("idOperacion", idOperacion);
                        operationBinding.getParamsMap().put("loginUsuario",
                                                            ADFContext.getCurrent().getSecurityContext().getUserName());
                        operationBinding.execute();

                        if (!operationBinding.getErrors().isEmpty()) {
                            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                        } else {
                            JSFUtils.addFacesInformationMessage("Se ha iniciado el proceso Envió al gasto de manera correcta.");
                        }
                    } catch (Exception e) {
                        LOGGER.log(ADFLogger.ERROR,
                                   "Error en inicioEnviarGasto " + e.getClass() + ":" + e.getMessage());
                    }
                } else {
                    logger.warning("La Operacion se ha seleccionado como Factible, no se inicia proceso.");
                }
            }
        }
    }
    
    public void solicitarInteresesCobro() {
        logger.log(ADFLogger.TRACE, "Inside solicitarInteresesCobro.");

        String esFactible =
            (ADFUtils.getBoundAttributeValue("EsFactible") == null) ? null :
            ADFUtils.getBoundAttributeValue("EsFactible").toString();

        if ((ADFUtils.getBoundAttributeValue("EsFactible") == null) ||
            (!(ADFUtils.getBoundAttributeValue("EsFactible").toString().length() > 0))) {
            logger.warning("Es necesario seleccionar una opcion de Factibilidad.");
        } else {
            Integer isFactible = Integer.valueOf(esFactible);
            if (isFactible != null) {
                //No Factible - Inicia Proceso Interes Cobro.
                if (isFactible.compareTo(0) == 0) {
                    BindingContainer bindings = ADFUtils.getBindingContainer();
                    OperationBinding operationBinding = null;

                    try {
                        operationBinding = bindings.getOperationBinding("inicioInteresesCobro");
                        String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
                        operationBinding.getParamsMap().put("idOperacion", idOperacion);
                        operationBinding.getParamsMap().put("loginUsuario",
                                                            ADFContext.getCurrent().getSecurityContext().getUserName());
                        operationBinding.execute();

                        if (!operationBinding.getErrors().isEmpty()) {
                            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                        } else {
                            /*Inicia incidencia FNXII-5808*/
                            //JSFUtils.addFacesInformationMessage("Se ha iniciado el proceso Calculo de Interés y cobro de manera correcta.");
                            LOGGER.warning("Se ha iniciado el proceso Calculo de Interés y cobro de manera correcta.");
                            /*Finaliza incidencia FNXII-5808*/
                        }
                    } catch (Exception e) {
                        LOGGER.log(ADFLogger.ERROR,
                                   "Error en inicioInteresesCobro " + e.getClass() + ":" + e.getMessage());
                    }
                } else {
                    logger.warning("La Operacion se ha seleccionado como Factible, no se inicia proceso.");
                }
            }
        }
    }
    
    public void solicitarEnvioGastoDeterminar() {
        logger.log(ADFLogger.TRACE, "Inside solicitarEnvioGastoDeterminar.");

        String esFactible =
            (ADFUtils.getBoundAttributeValue("EsFactible") == null) ? null :
            ADFUtils.getBoundAttributeValue("EsFactible").toString();

        if ((ADFUtils.getBoundAttributeValue("EsFactible") == null) ||
            (!(ADFUtils.getBoundAttributeValue("EsFactible").toString().length() > 0))) {
            logger.warning("Es necesario seleccionar una opcion de Factibilidad.");
        } else {
            Integer isFactible = Integer.valueOf(esFactible);
            if (isFactible != null) {
                //No Factible - Inicia Proceso Interes Cobro.
                if (isFactible.compareTo(0) == 0) {
                    BindingContainer bindings = ADFUtils.getBindingContainer();
                    OperationBinding operationBinding = null;

                    try {
                        operationBinding = bindings.getOperationBinding("inicioEnviarGasto");
                        String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
                        operationBinding.getParamsMap().put("idOperacion", idOperacion);
                        operationBinding.getParamsMap().put("loginUsuario",
                                                            ADFContext.getCurrent().getSecurityContext().getUserName());
                        operationBinding.execute();

                        if (!operationBinding.getErrors().isEmpty()) {
                            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                        } else {
                            /*Inicia incidencia FNXII-5785*/
                            //JSFUtils.addFacesInformationMessage("Se ha iniciado el proceso Envió al gasto de manera correcta.");
                            logger.warning("Se ha iniciado el proceso Envió al gasto de manera correcta.");
                            /*Finaliza incidencia FNXII-5785*/
                        }
                    } catch (Exception e) {
                        LOGGER.log(ADFLogger.ERROR,
                                   "Error en inicioEnviarGasto " + e.getClass() + ":" + e.getMessage());
                    }
                } else {
                    logger.warning("La Operacion se ha seleccionado como Factible, no se inicia proceso.");
                }
            }
        }
    }
    
    public void solicitarInicioCalculoIntereses() {
        logger.log(ADFLogger.TRACE, "Inside solicitarCalculoInteresesCobro.");

        String esFactible =
            (ADFUtils.getBoundAttributeValue("EsFactible") == null) ? null :
            ADFUtils.getBoundAttributeValue("EsFactible").toString();

        if ((ADFUtils.getBoundAttributeValue("EsFactible") == null) ||
            (!(ADFUtils.getBoundAttributeValue("EsFactible").toString().length() > 0))) {
            logger.warning("Es necesario seleccionar una opcion de Factibilidad.");
        } else {
            Integer isFactible = Integer.valueOf(esFactible);
            if (isFactible != null) {
                //No Factible - Inicia Proceso Interes Cobro.
                if (isFactible.compareTo(1) == 0) {
                    BindingContainer bindings = ADFUtils.getBindingContainer();
                    OperationBinding operationBinding = null;

                    try {
                        operationBinding = bindings.getOperationBinding("inicioInteresesCobro");
                        String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
                        operationBinding.getParamsMap().put("idOperacion", idOperacion);
                        operationBinding.getParamsMap().put("loginUsuario",
                                                            ADFContext.getCurrent().getSecurityContext().getUserName());
                        operationBinding.execute();

                        if (!operationBinding.getErrors().isEmpty()) {
                            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                        } else {
                            JSFUtils.addFacesInformationMessage("Se ha iniciado el proceso Calculo de Interés y cobro de manera correcta.");
                        }
                    } catch (Exception e) {
                        LOGGER.log(ADFLogger.ERROR,
                                   "Error en inicioInteresesCobro " + e.getClass() + ":" + e.getMessage());
                    }
                } else {
                    logger.warning("La Operacion se ha seleccionado como Factible, no se inicia proceso.");
                }
            }
        }
    }
    
    public Boolean validarEstadoOperacion() {
        logger.log(ADFLogger.WARNING, "Inside validarEstadoOperacion.");
        
        Boolean result = Boolean.FALSE;
        
        BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
        OperationBinding operationBindingEstado = null;
        operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
        operationBindingEstado.getParamsMap().put("idOperacion",
                                                  Long.toString(gestorOperacionesBean.getIdOperacion()));
        Long resultEstado = (Long) operationBindingEstado.execute();
        
        if (gestorOperacionesBean.getEsEnvioGasto()) {
            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ENVIO_GASTO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ENVIO_GASTO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ENVIO_GASTO"));
                    } else {
                        result = Boolean.TRUE;
                    }
                }
            }
        } else {
            if (resultEstado == ESTADO_SUSPENDIDO) {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else {
                if (resultEstado == ESTADO_FINALIZADO) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else {
                    if (resultEstado == ESTADO_CANCELADO) {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else {
                        result = Boolean.TRUE;
                    }
                }
            }
        }
        return result;
    }
    
    public Boolean validarProcesoOperacion(Integer proceso) {
        logger.log(ADFLogger.WARNING, "Inside validarProcesoOperacion.");
        
        Boolean result = Boolean.FALSE;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.getParamsMap().put("proceso", proceso);
        Boolean resultProceso = (Boolean) operationBinding.execute();
        
        String headerAction = (String) JSFUtils.resolveExpression("#{viewScope.headerAction}");

        if ((headerAction != null) && (headerAction.trim().length() > 0)) {
            if (headerAction.equalsIgnoreCase("solicitarDeterminarFactibilidad")) {
                logger.warning("Entra en solicitarDeterminarFactibilidad");
                if (resultProceso) {
                    if (proceso.compareTo(PROCESO_IMPLEMENTACION_PCT) == 0) {
                        if (gestorOperacionesBean.getEsEnvioGasto()) {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG02_ENVIO_GASTO"));
                        } else {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_INTERESES_COBRO"));    
                        }
                    }
                } else {
                    result = Boolean.TRUE;
                }
            }
            if (headerAction.equalsIgnoreCase("solicitarCalculoInteresesCobro")) {
                logger.warning("Entra en solicitarCalculoInteresesCobro");
                if (resultProceso) {
                    if (proceso.compareTo(PROCESO_IMPLEMENTACION_PCT) == 0) {
                        if (gestorOperacionesBean.getEsEnvioGasto()) {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ENVIO_GASTO"));
                        } else {
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG02_CALCULO_INTERES"));    
                        }
                    }
                } else {
                    result = Boolean.TRUE;
                }
            }
        }
        return result;
    }
    
    public Boolean validaCalculoIntereses() {
        logger.log(ADFLogger.WARNING, "Inside validaCalculoIntereses.");
        
        Boolean result = Boolean.FALSE;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaCalculoIntereses");
        Boolean resultProceso = (Boolean) operationBinding.execute();
        
        if (resultProceso) {
            result = Boolean.TRUE;
        }
        
        return result;
    }
    
    public Boolean obtenerSaldoNoVencido() {
        logger.warning("Inside obtenerSaldoNoVencido.");
        
        Boolean result = Boolean.TRUE;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        operationBinding = bindings.getOperationBinding("verificarSaldoNoVencido");
        operationBinding.getParamsMap().put("idOperacion", Long.toString(gestorOperacionesBean.getIdOperacion()));
        operationBinding.execute();
        
        result = ((Boolean) operationBinding.getResult());
        logger.warning("Saldo no vencido: " + result);
        
        return result;
    }

    public void setPopupLimites(RichPopup popupLimites) {
        this.popupLimites = popupLimites;
    }

    public RichPopup getPopupLimites() {
        return popupLimites;
    }

    public void evaluaLimite(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBindingProceso = null;
        operationBindingProceso = bindings.getOperationBinding("cargarLimites");
        operationBindingProceso.getParamsMap().put("idoperacion",
                                                   Long.toString(gestorOperacionesBean.getIdOperacion()));
        Map resultProceso = (HashMap) operationBindingProceso.execute();
        if (!operationBindingProceso.getErrors().isEmpty()) {
            logger.warning("Error al evaluar limites de exposición.");
            JSFUtils.addFacesErrorMessage(operationBindingProceso.getErrors().toString());
//            if (null != resultProceso.get("mensaje")) {
//                JSFUtils.addFacesErrorMessage((String) resultProceso.get("mensaje"));
//            }else{
//            }

        } else {
            showPopupLimitesHeaderAction();
        }
    }

    public void descargarReporteEstadoCondicionesActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en descargarReporteEstadoCondicionesActionListener.");
        try{
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupDescargarReporteValidacionCondicion().show(popupHints);
        }catch(Exception e){
            logger.warning("Error en descargarReporteEstadoCondicionesActionListener.", e);
        }
    }

    public void aceptarDescargarReporteValidacionCondicionActionListener(ActionEvent actionEvent) {
        popupDescargarReporteValidacionCondicion.hide();
    }

    public void descargarReporteEstadoCondiciones(FacesContext facesContext, OutputStream outputStream) {
        logger.warning("Entra en descargarReporteEstadoCondiciones");
        OperationBinding operationBinding = null;
        BindingContainer bindings = null;
        Long idOperacion = null;
        Integer idCatProducto = null;
        byte[] documentoEstadoCondicion = null;
        try {
            bindings = ADFUtils.getBindingContainer();
            idOperacion = gestorOperacionesBean.getIdOperacion();
            idCatProducto = gestorOperacionesBean.getIdProducto();
            applyIE11Fix(getReporteValidacionCondicionFileName());
            
            operationBinding = bindings.getOperationBinding("crearReporteEstadoCondicion");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("idCatProducto", idCatProducto);
            documentoEstadoCondicion = (byte[])operationBinding.execute();
            
            if (documentoEstadoCondicion != null && documentoEstadoCondicion.length > 0) {
                logger.warning("Escribiendo archivo");
                outputStream.write(documentoEstadoCondicion);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("Error en descargarReporteEstadoCondiciones.", ex);
        } finally {
            flushAndCloseOutputStream(outputStream);
        }
    }
    
    public String getReporteValidacionCondicionFileName() {
        logger.warning("Entra en getReporteValidacionCondicionFileName");
        logger.warning("Nombre reporte : " + REPORTE_VALIDACION_CONDICION_FILE_NAME + obtenFechaHora() + ".pdf");
        return REPORTE_VALIDACION_CONDICION_FILE_NAME + obtenFechaHora() + ".pdf";
    }

    public void setPopupDescargarReporteValidacionCondicion(RichPopup popupDescargarReporteValidacionCondicion) {
        this.popupDescargarReporteValidacionCondicion = popupDescargarReporteValidacionCondicion;
    }

    public RichPopup getPopupDescargarReporteValidacionCondicion() {
        return popupDescargarReporteValidacionCondicion;
    }

    public void cancelarDescargaReporteValidacionCondicion(ActionEvent actionEvent) {
        popupDescargarReporteValidacionCondicion.hide();
    }
    
    /**
     * Verifica si se debe mostrar o no el menu de Formalización de Enmiendas.
     * Para que se pueda visualizar el menú deben cumplirse las siguientes condiciones:
     * 1. Que el usuario actual sea el responsable de la operación.
     * 2. Que la operacion se encuentre en estado EN PROCESO.
     * 3. Que haya finalizado el proceso de aprobación.
     *
     * @return {@code true} si y solo si se cumplen las condiciones y se debe renderizar el menú de Formalización de Enmiendas,
     * {@code false} en caso contrario.
     */
    public boolean isRenderFormalizacionEnmiendasMenu() {
        //Si gestorOperacionesBean no fue inyectado en el constructor de este bean, no se puede procesar nada mas.
        if (gestorOperacionesBean == null) {
            logger.warning("gestorOperacionesBean es nulo. No se puede continuar con las validaciones requeridas para mostrar menu Formalización de Enmiendas.");
            return false;
        }

        //Inicia validaciones para validar si se muestra o no el menú de formalización de enmiendas
        boolean mostrar = true;
        
        //Responsable operación
        mostrar &= gestorOperacionesBean.isEsResponsableOperacion();

        //Estado En Proceso
        String enProcesoStatus = getStringFromBundle("ESTADO_EN_PROCESO");
        mostrar &= enProcesoStatus.equals(gestorOperacionesBean.getEstado());
        
        // Aprobación finalizada
        mostrar &= gestorOperacionesBean.getEsAprobacionFinalizado();

        return mostrar;
    }
    
    /**
     * Revisa que se pueda solicitar una formalización de enmiendas de la operación actual.
     * Levanta popup para seleccionar las banderas de inicio del proceso
     * Condiciones para que se pueda iniciar un proceso de formalización de enmiendas:
     * 1. Que el usuario actual sea el responsable de la operación.
     * 2. Que la operacion se encuentre en estado EN PROCESO.
     * 
     * @param actionEvent
     */
    public void solicitarFormalizacionEnmiendaActionListener(ActionEvent actionEvent) {
        logger.info("Iniciando solicitarFormalizacionEnmiendaActionListener");
        logger.log(ADFLogger.TRACE,
                   "Inside solicitarFormalizacionEnmiendaActionListener: " + actionEvent.getComponent().getId());

        //Si gestorOperacionesBean no fue inyectado en el constructor de este bean, no se puede procesar nada mas.
        if (gestorOperacionesBean == null) {
            logger.warning("gestorOperacionesBean es nulo. No se puede continuar con las validaciones requeridas para iniciar Formalización de Enmiendas.");
            return;
        }

        //Primero validamos usuario responsable
        if (!gestorOperacionesBean.isEsResponsableOperacion()) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("FORMALIZACION_ENMIENDAS_USUARIO_RESP_ERROR"));
            return;
        }

        String strIdOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
        BindingContainer bindings = ADFUtils.getBindingContainer();

        //Valida estado En Proceso
        OperationBinding operationBindingEstado = null;
        operationBindingEstado = bindings.getOperationBinding("validarEstadoOperacion");
        operationBindingEstado.getParamsMap().put("idOperacion", strIdOperacion);
        Long resultEstado = (Long) operationBindingEstado.execute();
        if (!ESTADO_PROCESO.equals(resultEstado)) {
            logger.info("Operacion no se encuentra en estado En Proceso, sino en el estado: " + resultEstado);
            JSFUtils.addFacesErrorMessage(getStringFromBundle("FORMALIZACION_ENMIENDAS_ESTADO_OPERACION_ERROR"));
            return;
        }

        //para formalizaciones genericas se debe mostrar popup de informacion adicional
        showPopupConfirmacionEnmiendaGenerica();
        
    }

    /**
     * Inicio del proceso de administración de operaciones:
     * 1. Que el usuario actual sea el responsable de la operación.
     * 2. Que la operacion se encuentre en estado EN PROCESO. 
     *  
     * 
     * @param actionEvent
     */
    public void solicitarAdministracionOperacionActionListener(ActionEvent actionEvent) {
        String accion = JSFUtils.resolveExpressionAsString("#{viewScope.headerAction}");
        logger.info("Iniciando solicitarAdministracionOperacionActionListener accion = " + accion);
        logger.log(ADFLogger.WARNING, "Inside solicitarAdministracionOperacionActionListener: " + actionEvent.getComponent().getId());

        if (gestorOperacionesBean == null) {
            logger.warning("gestorOperacionesBean es nulo. No se puede continuar con las validaciones requeridas para iniciar Administración Operación.");
            return;
        }

        //Primero validamos usuario responsable
        if (!gestorOperacionesBean.isEsResponsableOperacion()) {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ADMIN_OPERACION_USUARIO_RESP_ERROR"));//cambiar mensaje
            return;
        }

        String strIdOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
        BindingContainer bindings = ADFUtils.getBindingContainer();

        //Valida estado En Proceso
        OperationBinding operationBindingEstado = null;
        operationBindingEstado = bindings.getOperationBinding("validarEstadoOperacion");
        operationBindingEstado.getParamsMap().put("idOperacion", strIdOperacion);
        Long resultEstado = (Long) operationBindingEstado.execute();
        if (!ESTADO_PROCESO.equals(resultEstado)) {
            logger.info("Operacion no se encuentra en estado En Proceso, sino en el estado: " + resultEstado);
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ADMIN_OPERACION_ESTADO_OPERACION_ERROR"));//cambiar mensaje
            return;
        }

        //Para Administracion Operacion desde fenix se debe solicitar confirmacion
        if (HEADER_ACTION_ADMINOPE_FENIX.equals(accion)) {
            // Asignamos mensaje al popup de confirmacion
            String msg = getStringFromBundle("ADMIN_OPERACION_PREGUNTA_TXT");//cambiar mensaje
            setMensajeConfirmacion(MessageFormat.format(msg, gestorOperacionesBean.getIdOperacion()));
            // Mostramos popup
            showPopupConfirmarHeaderAction();
        } else {
            //para formalizaciones genericas se debe mostrar popup de informacion adicional
            showPopupConfirmacionEnmiendaGenerica();
        }
    }


    /**
     * Pasa la solicitud de la formalización de Enmienda al AM principal.
     * @param origen Origen de la formalización de enmienda. Sus valores se encuentran en {@link org.bcie.fenix.common.FenixConstants}.
     */
    private void solicitarFormalizacionEnmienda(int origen) {
        logger.info("Inside solicitarFormalizacionEnmienda.");

        if (origen < 0 || origen > 5) {
            throw new IllegalArgumentException("Valor de origen no valido.");
        }

        //Si gestorOperacionesBean no fue inyectado en el constructor de este bean, no se puede procesar nada mas.
        if (gestorOperacionesBean == null) {
            logger.warning("gestorOperacionesBean es nulo. No se puede continuar con la solicitud de Formalización de Enmiendas.");
            return;
        }
        
        //Informacion base para iniciar proceso de formalizacion de enmiendas
        Map<String, Object> params = new HashMap<>();
        params.put(FenixModelConstants.AM_PARAM_KEY_IDOPERACION, gestorOperacionesBean.getIdOperacion());
        params.put(FenixModelConstants.AM_PARAM_KEY_DESCRIPCION, gestorOperacionesBean.getOperacion());
        params.put(FenixModelConstants.AM_PARAM_KEY_ORIGEN, origen);
        params.put(FenixModelConstants.AM_PARAM_KEY_CODCLIENTE, gestorOperacionesBean.getIdCliente());
        params.put(FenixModelConstants.AM_PARAM_KEY_NOMCLIENTE, gestorOperacionesBean.getNombreCliente());
        params.put(FenixModelConstants.AM_PARAM_KEY_CODPRODUCTO, gestorOperacionesBean.getIdProducto());
        params.put(FenixModelConstants.AM_PARAM_KEY_NOMPRODUCTO, gestorOperacionesBean.getProducto());
        params.put(FenixModelConstants.AM_PARAM_KEY_MONTOSOLICITADO, gestorOperacionesBean.getMontoSolicitado());
        params.put(FenixModelConstants.AM_PARAM_KEY_PAIS, gestorOperacionesBean.getPais());
        params.put(FenixModelConstants.AM_PARAM_KEY_RESPONSABLE,
                   ADFContext.getCurrent().getSecurityContext().getUserName());
        //las formalizaciones de origen genérico requieren pasar la información ingresada por el usuario
        params.put(FenixModelConstants.AM_PARAM_KEY_DESOBLIGACION, this.getEsDesobligacion());
        params.put(FenixModelConstants.AM_PARAM_KEY_CAMBIO_PLAZO, this.getEsCambioPlazo());
        params.put(FenixModelConstants.AM_PARAM_KEY_VALIDAR_COND_FINANCIERAS, this.getEsRequiereCambiosCondicionesFinancieras());
        //las formalizaciones genéricas no requieren un idEnmienda
        params.put(FenixModelConstants.AM_PARAM_KEY_IDENMIENDA, null);
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("inicioFormalizacionEnmiendas");
        operationBinding.getParamsMap().put("params", params);
        operationBinding.execute();

        if (operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesInformationMessage(getStringFromBundle("FORMALIZACION_ENMIENDAS_RESPUESTA_TXT"));
        }
    }

    public void setPopupConfirmacionEnmiendaGenerica(RichPopup popupConfirmacionEnmiendaGenerica) {
        this.popupConfirmacionEnmiendaGenerica = popupConfirmacionEnmiendaGenerica;
    }

    public RichPopup getPopupConfirmacionEnmiendaGenerica() {
        return popupConfirmacionEnmiendaGenerica;
    }

    public void setPopupConfirmacionEvaluaciones(RichPopup popupConfirmacionEvaluaciones) {
        this.popupConfirmacionEvaluaciones = popupConfirmacionEvaluaciones;
    }

    public RichPopup getPopupConfirmacionEvaluaciones() {
        return popupConfirmacionEvaluaciones;
    }
    
    public void setEsRequiereCambiosCondicionesFinancieras(boolean esRequiereCambiosCondicionesFinancieras) {
        this.esRequiereCambiosCondicionesFinancieras = esRequiereCambiosCondicionesFinancieras;
    }

    public boolean getEsRequiereCambiosCondicionesFinancieras() {
        return esRequiereCambiosCondicionesFinancieras;
    }

    public void setEsDesobligacion(boolean esDesobligacion) {
        this.esDesobligacion = esDesobligacion;
    }

    public boolean getEsDesobligacion() {
        return esDesobligacion;
    }

    public void setEsCambioPlazo(boolean esCambioPlazo) {
        this.esCambioPlazo = esCambioPlazo;
    }

    public boolean getEsCambioPlazo() {
        return esCambioPlazo;
    }
    
    /**
     * Muestra el popup de confirmacion para formalizaciones de enmiendas genericas.
     */
    private void showPopupConfirmacionEnmiendaGenerica() {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmacionEnmiendaGenerica().show(popupHints);
    }

    /**
     * Oculta el popup de confirmación de formalización de enmienda genérica.
     * @param actionEvent
     */
    public void cancelarConfirmacionEnmiendaGenericaActionListener(ActionEvent actionEvent) {
        logger.info("Inside cancelarConfirmacionEnmiendaGenericaActionListener: " + actionEvent.getComponent().getId());
        getPopupConfirmacionEnmiendaGenerica().hide();
    }
    
    /**
      * Muestra el popup de confirmacion para Evaluaciones.
      */
     private void showPopupConfirmacionEvaluaciones() {
         RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
         getPopupConfirmacionEvaluaciones().show(popupHints);
     }

     /**
      * Oculta el popup de confirmación de Evaluaciones.
      * @param actionEvent
      */
     public void cancelarConfirmacionEvaluacionesActionListener(ActionEvent actionEvent) {
         logger.log(ADFLogger.TRACE,
                    "Inside cancelarConfirmacionEvaluacionesActionListener: " + actionEvent.getComponent().getId());
         getPopupConfirmacionEvaluaciones().hide();
     }

    public void showPopupConfirmacionEvaluaciones(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupConfirmacionEvaluaciones().show(popupHints);
    }
    
 public void solicitarEvaluacion(String p_componente, String p_tipo_proceso) 
    {
        logger.log(ADFLogger.WARNING, "Inicia solicitarEvaluacion.");

        String vPROCESO_EVALUACIONES = "6";
        String vPROCESO_EVALUACIONES_SEGUIMIENTO_SIEMAS = "31";
        
        String tipoProcesoEvaluacion = "";
        String poseeCuestionarioBase = "ERROR";
        Long id_operacion =  gestorOperacionesBean.getIdOperacion();
        logger.log(ADFLogger.WARNING, "p_componente: " + p_componente);
        logger.log(ADFLogger.WARNING, "p_tipo_proceso: " + p_tipo_proceso);
        logger.log(ADFLogger.WARNING, "id_operacion: " + id_operacion);
        
        
        
        try{
            logger.log(ADFLogger.WARNING, "Verificacion de si la evaluacion a crear posee cuestionario base");
            BindingContainer bindingEvaluaciones = ADFUtils.getBindingContainer();      
            OperationBinding operationBindingEvaluaciones = bindingEvaluaciones.getOperationBinding("poseeCuestionarioBase");
            operationBindingEvaluaciones.getParamsMap().put("id_operacion", id_operacion);
            operationBindingEvaluaciones.getParamsMap().put("codigo_tipo_evaluacion", p_tipo_proceso);
            operationBindingEvaluaciones.execute();
    
            if (!operationBindingEvaluaciones.getErrors().isEmpty()) {
                    //si no posse cuestionario base lanzar mensaje y no permitir inicio de proceso de evaluacion
                    //JSFUtils.addFacesInformationMessage(getStringFromBundle("FORMALIZACION_ENMIENDAS_RESPUESTA_TXT"));
                poseeCuestionarioBase = (String)operationBindingEvaluaciones.getResult();
                logger.log(ADFLogger.WARNING, "resultado poseeCuestionarioBase: " + poseeCuestionarioBase);
                if(poseeCuestionarioBase.equals("OK"))
                {
                    logger.log(ADFLogger.WARNING, "Se iniciaria el proceso de evaluaciones - posseCustioneraioBase: " + poseeCuestionarioBase);
                }
                else
                {
                    logger.log(ADFLogger.WARNING, "NO se iniciaria el proceso de evaluaciones - posseCustioneraioBase: " + poseeCuestionarioBase);
                    //JSFUtils.addFacesInformationMessage(poseeCuestionarioBase);
                }
            }
            else
            {
                logger.log(ADFLogger.WARNING, "Retorno vacio: " + poseeCuestionarioBase);
            }                               
        }
        catch(Exception exp)
        {
            logger.log(ADFLogger.WARNING, "Error vacio: " + exp);
        }
        
        if(p_tipo_proceso.equals("1") || p_tipo_proceso.equals("2") || p_tipo_proceso.equals("3"))
        {
            tipoProcesoEvaluacion = vPROCESO_EVALUACIONES;
        }
        else if (p_tipo_proceso.equals("4") || p_tipo_proceso.equals("5"))
        {
            tipoProcesoEvaluacion = vPROCESO_EVALUACIONES_SEGUIMIENTO_SIEMAS;
        }
        logger.log(ADFLogger.WARNING, "tipoProcesoEvaluacion: " + tipoProcesoEvaluacion);
        
        BindingContainer bindings = ADFUtils.getBindingContainer(); 
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("validaProcesos");
        operationBinding.getParamsMap().put("operacion", id_operacion.toString());
        operationBinding.getParamsMap().put("proceso", tipoProcesoEvaluacion);
        Boolean result = (Boolean) operationBinding.execute();
        if (result) 
        {
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_EVALUACIONES"));
        } else 
        {
            BindingContainer bindingsEstado = ADFUtils.getBindingContainer();
            OperationBinding operationBindingEstado = null;
            operationBindingEstado = bindingsEstado.getOperationBinding("validarEstadoOperacion");
            operationBindingEstado.getParamsMap().put("idOperacion",
                                                      id_operacion.toString());
            Long resultEstado = (Long) operationBindingEstado.execute();
            if (resultEstado == ESTADO_SUSPENDIDO) 
                            {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_SUSPENDIDO"));
            } else 
                            {
                if (resultEstado == ESTADO_FINALIZADO) 
                                    {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_FINALIZADO"));
                } else 
                                    {
                    if (resultEstado == ESTADO_CANCELADO) 
                                            {
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_CANCELADO"));
                    } else 
                                            {
                        logger.log(ADFLogger.WARNING, "Inside solicitarEvaluacion: " + p_componente);
                        setMensajeConfirmacion("¿Esta seguro de iniciar el proceso de evaluaciones de la operación " +
                                               gestorOperacionesBean.getIdOperacion() + "?"); // Asignamos mensaje
 

                        showPopupConfirmarHeaderAction(); // Mostramos popup
                    }
                }
            }
        }
        logger.log(ADFLogger.WARNING, "Finaliza solicitarEvaluacion.");
    }        

    /*
     * Se creo la funcion solicitarEvaluaciones para sustituir las siguientes funciones ya que tenian codigo redundante,
     * ahora se enviaran como parametro los campos necesarios para sus diferentes funcionamientos.
     * 
     * solicitarEvaluaciones
     * solicitarEvaluacionesSiemas
     * solicitarEvaluacionesExPost
     * solicitarEvaluacionesMedioTermino
    */
    private Boolean solicitarEvaluaciones(String descripcion_evaluacion, String idEvaluacion) {
        logger.log(ADFLogger.WARNING, "Inside " + descripcion_evaluacion + "."); 
        Integer ContadorErrores = 0; 
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null; 
        try {

            String idOperacion = Long.toString(gestorOperacionesBean.getIdOperacion());
            
            logger.log(ADFLogger.WARNING, "Ejecución inicioEvaluacion-----");
            logger.log(ADFLogger.WARNING, "loginUsuario: " + ADFContext.getCurrent().getSecurityContext().getUserName());
            logger.log(ADFLogger.WARNING, "descripcionOperacion: " + gestorOperacionesBean.getOperacion());
            logger.log(ADFLogger.WARNING, "codigoProducto: " + gestorOperacionesBean.getIdProducto());
            logger.log(ADFLogger.WARNING, "nombreProducto: " + gestorOperacionesBean.getProducto());
            logger.log(ADFLogger.WARNING, "montoSolicitado: " + gestorOperacionesBean.getMontoSolicitado());
            logger.log(ADFLogger.WARNING, "pais: " + gestorOperacionesBean.getPais());
            logger.log(ADFLogger.WARNING, "idEvaluacion: " + idEvaluacion);
             
            operationBinding = bindings.getOperationBinding("inicioEvaluacion");
            operationBinding.getParamsMap().put("idOperacion", idOperacion);
            operationBinding.getParamsMap().put("loginUsuario",ADFContext.getCurrent().getSecurityContext().getUserName());
            operationBinding.getParamsMap().put("descripcionOperacion", gestorOperacionesBean.getOperacion());
            operationBinding.getParamsMap().put("codigoProducto", gestorOperacionesBean.getIdProducto());
            operationBinding.getParamsMap().put("nombreProducto", gestorOperacionesBean.getProducto());
            operationBinding.getParamsMap().put("montoSolicitado", gestorOperacionesBean.getMontoSolicitado());
            operationBinding.getParamsMap().put("pais", gestorOperacionesBean.getPais());
            operationBinding.getParamsMap().put("idEvaluacion", idEvaluacion);

            Boolean result = (Boolean) operationBinding.execute(); 
            if (!operationBinding.getErrors().isEmpty()) {
                // Manejo de errores
                System.out.println("Error");

                JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                return null;
            } else {
                JSFUtils.addFacesInformationMessage("Solicitar " + descripcion_evaluacion + " realizado exitosamente.");
            }
            if (!result) {
                ContadorErrores = ContadorErrores + 1;
            }

            if (ContadorErrores > 0) {
                return false;
            }
            logger.log(ADFLogger.WARNING, "Finaliza Ejecución inicioEvaluacion-----");
            
            return true;
        } catch (Exception e) {
            LOGGER.log(ADFLogger.ERROR, "Error en " + descripcion_evaluacion + " " + e.getClass() + ":" + e.getMessage());
            return false;
        } 
    }
     
}
