package org.bcie.fenix.view.cuestionarios;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.share.ADFContext;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;

import org.bcie.evaluacionmo.ConsultarEvaluacionResponseType;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.FenixConstants;

public class CuestionariosBean implements Serializable {
    @SuppressWarnings("compatibility:-1159340577946868895")
    private static final long serialVersionUID = 1L;

    public ADFLogger logger = null;
    private Boolean esEstadoCompletado;

    public CuestionariosBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    //variables Sector
    private Integer idSector;
    //agrego descripcion del sector
    private String descripcionSector;
    //variables SubSector
    private Integer idSubSector;
    //agrego descripcion del sector
    private String descripcionSubSector;
    private boolean muestraCuestionarios = false;
    private boolean muestraEvaluaciones = false;

    private String idOperacion;
    private String idTarea;
    private String usuario;
    private String tipoEvaluacion = "";

    private String nombrePanelHeader;
    private String nombreConsultarCuestionarioBtn;
    private String nombreEliminarBtn;
    private Boolean muestraColumnaEvaluacion = Boolean.FALSE;
    private Boolean muestraColumnaCalificacion = Boolean.FALSE;

    private String idCuestionario;
    private String idEvaluacion;
    private Integer eventoEvaluacion;
    private String instanciaProceso;
    private Boolean readOnlyCalificacion;

    private boolean muestraActualizarOperacion = false;
    private boolean banPrecarga = false;
    private String sectorIbcie;
    private String subSectorIbcie;
    private Map<Integer, String> mapSectorIbcie = new HashMap<>();
    private Map<Integer, String> mapSubSectorIbcie = new HashMap<>();


    public static final String BUNDLE = "org.bcie.fenix.view.AdministradorCuestionariosVCBundle";

    public void inicializarFlujo() {
        logger.log(ADFLogger.WARNING, "INTO inicializarFlujo.");

        try {
            idOperacion =
                null == JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") ? "" :
                JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
            idTarea =
                null == JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}") ? "" :
                JSFUtils.resolveExpression("#{pageFlowScope.pIdTarea}").toString();
            setUsuario(null == JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}") ? "" :
                       JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}").toString());
            tipoEvaluacion =
                null == JSFUtils.resolveExpression("#{pageFlowScope.pTipoEvaluacion}") ? "" :
                JSFUtils.resolveExpression("#{pageFlowScope.pTipoEvaluacion}").toString();
            eventoEvaluacion =
                null == JSFUtils.resolveExpression("#{pageFlowScope.pEventoEvaluacion}") ? null :
                Integer.parseInt( JSFUtils.resolveExpression("#{pageFlowScope.pEventoEvaluacion}").toString());
            logger.warning("eventoEvaluacion recibido en Administrador de cuestionarios: " + eventoEvaluacion);
            instanciaProceso =
                null == JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") ? null :
                (String) JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}");
            logger.warning("instanciaProceso recibido en Administrador de cuestionarios: " + instanciaProceso);

            asignarAtributosPoridTarea();
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en inicializarFlujo." + e.getClass() + "." + e.getMessage());
        }
    }

    public void asignarAtributosPoridTarea() {
        logger.log(ADFLogger.WARNING, "Entra en asignarAtributosPoridTarea");
        try {
            logger.log(ADFLogger.WARNING, "idTarea = " + this.idTarea);
            switch (idTarea) {
            case FenixConstants.PC06_GENERARCUESTIONARIOS:
                nombrePanelHeader = "Generar Cuestionarios";
                muestraEvaluaciones = false;
                nombreConsultarCuestionarioBtn = getStringFromBundle("VER_CUESTIONARIO_DETALLE");
                nombreEliminarBtn = "Eliminar cuestionario";
                break;
            case FenixConstants.PC06_VALIDARDISENOCUESTIONARIOS:
                System.out.println("Into PC06_VALIDARDISENOCUESTIONARIOS.");
                nombrePanelHeader = "Cuestionarios";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("VER_CUESTIONARIO_DETALLE");
                nombreEliminarBtn = "Eliminar";
                setMuestraActualizarOperacion(true);
                break;
            case FenixConstants.PC06_VALIDARCUESTIONARIOS:
                nombrePanelHeader = "Validar cuestionarios completados";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                break;
            case FenixConstants.PC06_GENERARINFORMES:
                nombrePanelHeader = "Generar informes";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = "Ir a cuestionarios";
                break;
            case FenixConstants.PC06_COMPLETARCUESTIONARIOS:
                nombrePanelHeader = "Completar cuestionarios";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                break;
            case FenixConstants.PC06_REVISARINFORMES:
                nombrePanelHeader = "Revisar informes";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                break;
            case FenixConstants.PC06_REVISAR_DISENIO_CUESTIONARIO_IBCIE:
                nombrePanelHeader = "Revisar diseño de cuestionarios.";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("VER_CUESTIONARIO_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_VALIDAR_DISENO_CUESTIONARIOS_IBCIE:
                System.out.println("Into PC06_VALIDAR_DISENO_CUESTIONARIOS_IBCIE.");
                nombrePanelHeader = "Validar diseño de cuestionarios";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEliminarBoton", Boolean.FALSE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SUPERVISION_IBCIE:
                nombrePanelHeader = "Completar cuestionarios";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarActualizarBoton", Boolean.TRUE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_IBCIE:
                nombrePanelHeader = "Completar cuestionarios";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_COMPLETO_IBCIE:
                nombrePanelHeader = "Validar cuestionarios completados";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarActualizarBoton", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarValidado", Boolean.FALSE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE:
                nombrePanelHeader = "Validar cuestionarios ODE y generar informes IBCIE";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                muestraColumnaCalificacion = Boolean.TRUE;
                readOnlyCalificacion = Boolean.FALSE;
                break;
            case FenixConstants.PC06_VALIDAR_INFORME_IBCIE:
                nombrePanelHeader = "Validar informes";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                muestraColumnaCalificacion = Boolean.TRUE;
                readOnlyCalificacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS_SUPERVISION:
                nombrePanelHeader = "Completar cuestionarios";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_COMPLETAR_CUESTIONARIO_SEGUIMIENTO_SIEMAS:
                nombrePanelHeader = "Completar cuestionarios";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_VALIDAR_CUESTIONARIO_COMPLETO_SEGUIMIENTO_SIEMAS:
                nombrePanelHeader = "Validar cuestionarios completados";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarActualizarBoton", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarValidado", Boolean.FALSE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_GENERAR_INFORME_SEGUIMIENTO_SIEMAS:
                nombrePanelHeader = "Generar informes seguimiento SIEMAS";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarActualizarBoton", Boolean.FALSE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_REVISAR_INFORME_SEGUIMIENTO_SIEMAS:
                nombrePanelHeader = "Revisar informes";
                muestraEvaluaciones = true;
                nombreConsultarCuestionarioBtn = getStringFromBundle("IR_CUESTIONARIOS_DETALLE");
                putPageFlowNuevoValorParametro("pActivarCrearEvaluacion", Boolean.FALSE);
                putPageFlowNuevoValorParametro("pActivarAvance", Boolean.TRUE);
                putPageFlowNuevoValorParametro("pActivarEstatus", Boolean.TRUE);
                muestraColumnaEvaluacion = Boolean.TRUE;
                readOnlyCalificacion = Boolean.TRUE;
                break;
            case FenixConstants.PC06_ACTUALIZAR_CONDICIONES_SIEMAS:
                break;
            default:
                break;
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en asignarAtributosPoridTarea." + e.getClass() + "." + e.getMessage());
        }
    }

    public void consultarEvaluacion() {
        logger.log(ADFLogger.WARNING, "Entra en consultarEvaluacion.");

        HashMap<String, ConsultarEvaluacionResponseType> response = new HashMap<String, ConsultarEvaluacionResponseType>();
        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        BigDecimal idEvaluacion = null;

        logger.log(ADFLogger.WARNING, "Valor idOperacion Cuestionario :" + idOperacion);
        logger.log(ADFLogger.WARNING, "Valor eventoEvaluacion cuestionario :" + eventoEvaluacion);
        logger.log(ADFLogger.WARNING, "Valor instanciaProceso cuestionario :" + instanciaProceso);
        logger.log(ADFLogger.WARNING, "Valor Usuario cuestionario :" + getUsuario());
        logger.log(ADFLogger.WARNING, "Valor tipoEvaluacion cuestionario :" + tipoEvaluacion);
        logger.log(ADFLogger.WARNING, "Valor idTarea cuestionario :" + idTarea);
        try {
            
            if (idTarea != null && idTarea.trim().length() > 0) {
                
                if (idTarea.compareTo(FenixConstants.PC06_VALIDAR_CUESTIONARIO_ODE_GENERAR_INFORME_IBCIE) == 0) {
                    logger.log(ADFLogger.WARNING, "Entra en metodo para duplicar cuestionario.");
                    operationBinding = bindings.getOperationBinding("duplicarCuestionario");
                    operationBinding.getParamsMap().put("idOperacion", Long.parseLong(idOperacion));
                    operationBinding.getParamsMap().put("tipoEvaluacion", new Long(eventoEvaluacion));
                    operationBinding.getParamsMap().put("idFlujo", instanciaProceso);
                    operationBinding.getParamsMap().put("loginUsuario", getUsuario());
                    try {
                        operationBinding.execute();
                        if (null != operationBinding.getResult()) {
                            idEvaluacion = (BigDecimal) operationBinding.getResult();
                            logger.log(ADFLogger.WARNING, "Valor id cuestionario duplicado :" + idEvaluacion);
                        } else {
                            logger.log(ADFLogger.WARNING, "El id de cuestionario duplicado es nulo.");
                        }
                    } catch (Exception e) {
                        logger.log(ADFLogger.WARNING,
                                   "Error al ejecutar duplicarCuestionario." + e.getClass() + "." + e);
                    }
                    operationBinding = null;

                    operationBinding = bindings.getOperationBinding("consultarEvaluacionDuplicado");
                    operationBinding.getParamsMap().put("idOperacion", Long.parseLong(idOperacion));
                    operationBinding.getParamsMap().put("idTarea", Long.parseLong(getIdTarea().toString()));
                    operationBinding.getParamsMap().put("tipoEvaluacion", tipoEvaluacion);
                    operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
                    operationBinding.getParamsMap().put("eventoEvaluacion", new Long(eventoEvaluacion));
                    try {
                        operationBinding.execute();
                    } catch (Exception e) {
                        logger.log(ADFLogger.WARNING,
                                   "Error al ejecutar consultarEvaluacionDuplicado" + e.getClass() + "." + e);
                    }
                } else if (idTarea.compareTo(FenixConstants.PC06_VALIDAR_INFORME_IBCIE) == 0) {
                    logger.log(ADFLogger.WARNING, "Entra en PC06_VALIDAR_INFORME_IBCIE.");

                    operationBinding = bindings.getOperationBinding("consultarEvaluacionDuplicado");

                    operationBinding.getParamsMap().put("idOperacion", Long.parseLong(idOperacion));
                    operationBinding.getParamsMap().put("idTarea", Long.parseLong(getIdTarea().toString()));
                    operationBinding.getParamsMap().put("tipoEvaluacion", tipoEvaluacion);
                    operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
                    operationBinding.getParamsMap().put("eventoEvaluacion", new Long(eventoEvaluacion));
                    try {
                        operationBinding.execute();
                        logger.log(ADFLogger.WARNING, "Executo metodo consultarEvaluacionDuplicado correctamente.");
                    } catch (Exception e) {
                        logger.log(ADFLogger.WARNING,
                                   "Error al ejecutar consultarEvaluacionDuplicado" + e.getClass() + "." + e);
                    }
                } else {
                    logger.log(ADFLogger.WARNING, "Entra a ejecutar metodo Consultar en tareas sin duplicar");
                    operationBinding = bindings.getOperationBinding("consultarEvaluacion");
                    operationBinding.getParamsMap().put("idOperacion", Long.parseLong(idOperacion));
                    operationBinding.getParamsMap().put("idTarea", Long.parseLong(getIdTarea().toString()));
                    operationBinding.getParamsMap().put("tipoEvaluacion", tipoEvaluacion);
                    operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
                    operationBinding.getParamsMap().put("eventoEvaluacion", new Long(eventoEvaluacion));
                    operationBinding.execute();

                    logger.log(ADFLogger.WARNING, "Se realizo la ejecucion del metodo exitosamente");
                }
            } else {
                logger.log(ADFLogger.WARNING, "El id de tarea es nulo.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en consultarEvaluacion." + e.getClass() + "." + e);
        }
    }

    public static void putPageFlowNuevoValorParametro(String nombreParametro, Object valorParametro) {
        ADFContext adfContext = ADFContext.getCurrent();
        adfContext.getPageFlowScope().put(nombreParametro, valorParametro);
    }

    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;
        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }

        return txt;
    }

    public void evaluarParametroPStateBpm() {
        logger.warning("Dentro de evaluarParametroPStateBpm");
        String state = null;
        try {
            if (JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}") != null) {
                state = (String) JSFUtils.resolveExpression("#{pageFlowScope.pStateBpm}");
                logger.warning("state :" + state);
                setEsEstadoCompletado(state.equals("COMPLETED") ? Boolean.TRUE : Boolean.FALSE);
            } else {
                logger.warning("pState es nulo");
                setEsEstadoCompletado(Boolean.FALSE);
            }
        } catch (Exception ex) {
            logger.severe("Error al recuperar #{pageFlowScope.pStateBpm} :", ex);
            logger.warning("pState no obtenido");
        }

        logger.warning("esEstadoCompletado : " + getEsEstadoCompletado());
        logger.warning("Fuera de evaluarParametroPStateBpm");
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public Integer getIdSubSector() {
        return idSubSector;
    }

    public void setIdSubSector(Integer idSubSector) {
        this.idSubSector = idSubSector;
    }

    public boolean isMuestraCuestionarios() {
        return muestraCuestionarios;
    }

    public void setMuestraCuestionarios(boolean muestraCuestionarios) {
        this.muestraCuestionarios = muestraCuestionarios;
    }

    public String getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }


    public boolean isMuestraEvaluaciones() {
        return muestraEvaluaciones;
    }

    public void setMuestraEvaluaciones(boolean muestraEvaluaciones) {
        this.muestraEvaluaciones = muestraEvaluaciones;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombrePanelHeader() {
        return nombrePanelHeader;
    }

    public void setNombrePanelHeader(String nombrePanelHeader) {
        this.nombrePanelHeader = nombrePanelHeader;
    }

    public String getNombreConsultarCuestionarioBtn() {
        return nombreConsultarCuestionarioBtn;
    }

    public void setNombreConsultarCuestionarioBtn(String nombreConsultarCuestionarioBtn) {
        this.nombreConsultarCuestionarioBtn = nombreConsultarCuestionarioBtn;
    }

    public String getNombreEliminarBtn() {
        return nombreEliminarBtn;
    }

    public void setNombreEliminarBtn(String nombreEliminarBtn) {
        this.nombreEliminarBtn = nombreEliminarBtn;
    }

    public String getIdCuestionario() {
        DCIteratorBinding voEvaluacionIterator =
            ADFUtils.getDCBindingContainer().findIteratorBinding("EvaluacionVOIterator");
        Row currentRowEvaluacion = voEvaluacionIterator.getCurrentRow();
        return (String) currentRowEvaluacion.getAttribute("idCuestionario");

    }

    public void setIdCuestionario(String idCuestionario) {
        this.idCuestionario = idCuestionario;
    }


    public String getIdEvaluacion() {
        DCIteratorBinding voEvaluacionIterator =
            ADFUtils.getDCBindingContainer().findIteratorBinding("EvaluacionVOIterator");
        Row currentRowEvaluacion = voEvaluacionIterator.getCurrentRow();
        return currentRowEvaluacion.getAttribute("idEvaluacion").toString();

    }

    public void setIdEvaluacion(String idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }


    public boolean isMuestraActualizarOperacion() {
        return muestraActualizarOperacion;
    }

    public void setMuestraActualizarOperacion(boolean muestraActualizarOperacion) {
        this.muestraActualizarOperacion = muestraActualizarOperacion;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }


    public String getDescripcionSector() {
        return descripcionSector;
    }

    public void setDescripcionSector(String descripcionSector) {
        this.descripcionSector = descripcionSector;
    }

    public String getDescripcionSubSector() {
        return descripcionSubSector;
    }

    public void setDescripcionSubSector(String descripcionSubSector) {
        this.descripcionSubSector = descripcionSubSector;
    }

    public void setMuestraColumnaEvaluacion(Boolean muestraColumnaEvaluacion) {
        this.muestraColumnaEvaluacion = muestraColumnaEvaluacion;
    }

    public Boolean getMuestraColumnaEvaluacion() {
        return muestraColumnaEvaluacion;
    }

    public void setMuestraColumnaCalificacion(Boolean muestraColumnaCalificacion) {
        this.muestraColumnaCalificacion = muestraColumnaCalificacion;
    }

    public Boolean getMuestraColumnaCalificacion() {
        return muestraColumnaCalificacion;
    }


    public void setEventoEvaluacion(Integer eventoEvaluacion) {
        this.eventoEvaluacion = eventoEvaluacion;
    }

    public Integer getEventoEvaluacion() {
        return eventoEvaluacion;
    }

    public void setReadOnlyCalificacion(Boolean readOnlyCalificacion) {
        this.readOnlyCalificacion = readOnlyCalificacion;
    }

    public Boolean getReadOnlyCalificacion() {
        return readOnlyCalificacion;
    }

    public void setInstanciaProceso(String instanciaProceso) {
        this.instanciaProceso = instanciaProceso;
    }

    public String getInstanciaProceso() {
        return instanciaProceso;
    }


    public Boolean getEsEstadoCompletado() {
        return esEstadoCompletado;
    }

    public void setEsEstadoCompletado(Boolean esEstadoCompletado) {
        this.esEstadoCompletado = esEstadoCompletado;
    }

    public void setBanPrecarga(boolean banPrecarga) {
        this.banPrecarga = banPrecarga;
    }

    public boolean isBanPrecarga() {
        return banPrecarga;
    }

    /**
     * @User Story: 5003
     * @Feature: 11
     * @Developer: @RUGM
     * @Consulting: Kruger
     * @Date: 09/20/2021
     * @Overview: lectura de Operacion p[ara determianr si es candidata para el seteo de variables en los catalogos Sector & Subsector
     * */

    @SuppressWarnings("unchecked")
    public void busquedaOperacionCandidata() {
        logger.log(ADFLogger.WARNING, "busquedaOperacionCandidata");

        OperationBinding operationBinding = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();


        logger.log(ADFLogger.WARNING, "Valor idOperacion Cuestionario :" + this.getIdOperacion());
        try {
            if (this.getIdOperacion() != null && this.getIdOperacion().trim().length() > 0) {


                logger.log(ADFLogger.WARNING, "Entra en metodo del FenixAM");
                operationBinding = bindings.getOperationBinding("obtenerDetalleOperacionById");
                operationBinding.getParamsMap().put("idOperacion", Integer.parseInt(this.getIdOperacion()));

                try {
                    operationBinding.execute();
                    if (null != operationBinding.getResult()) {
                        banPrecarga = (Boolean) operationBinding.getResult();
                        logger.log(ADFLogger.WARNING, "La Operacion es Candidata:" + banPrecarga);
                        precargaData();

                    } else {
                        logger.log(ADFLogger.WARNING,  "La ejecicion de la OP - obtenerDetalleOperacionById, no tiene resultados");
                    }

                } catch (Exception e) {
                    logger.log(ADFLogger.WARNING, "Error al ejecutar FenixAM - obtenerDetalleOperacionById." + e.getClass() + "." + e);
                }
                operationBinding = null;


            } else {
                logger.log(ADFLogger.WARNING, "El id de operacion es nulo.");
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en consultarEvaluacion. " + e.getClass() + "." + e);
        }

    }


    /**
     * @User Story: 5003
     * @Feature: 11
     * @Developer: @RUGM
     * @Consulting: Kruger
     * @Date: 09/20/2021
     * @Overview: Asignacion de valores de catalogos Sector & Subsector
     * */
    @SuppressWarnings("unchecked")
    public void precargaData() {
        //Recupera en Bean de cuestionarios
        System.out.println("Inside precargaData");
        System.out.println("La operacion es candidata = " + this.isBanPrecarga());
        if (this.isBanPrecarga()) {
            //Recupera los iteradores de Sector y SubSector (combos sector y subSector)
            DCIteratorBinding GetDetalleOperacionVOIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("CatalogoSectorialIBCIEVOIterator");
            //Ejecutamos el query para que retome sus valores iniciales
            //GetDetalleOperacionVOIterator.executeQuery();

            //Empezamos a recorrer el iterator y creamos una lista con los valores
            Row[] filas = GetDetalleOperacionVOIterator.getAllRowsInRange();
            System.out.println("tamaño de Iterador = " + filas.length);
            for (Row row : filas) {
                System.out.println("ID = " + row.getAttribute("idOperacion").toString());
                System.out.println("¿Es multisectorial? = " + row.getAttribute("esMultisectorial").toString());
                System.out.println("ID Sector IBCIE = " + row.getAttribute("idSectorIbcie").toString());
                System.out.println("Descripcioin ID Sector IBCIE = " + row.getAttribute("sectorIbcie").toString());
                System.out.println("ID Sub-Sector IBCIE = " + row.getAttribute("idSubSectorIbcie").toString());
                System.out.println("Descripcion Sub-Sector IBCIE = " + row.getAttribute("subSectorIbcie").toString());


                if (row.getAttribute("sectorIbcie").toString().trim().length() > 0) {
                    this.sectorIbcie = row.getAttribute("sectorIbcie").toString();
                    this.setIdSector((Integer) row.getAttribute("idSectorIbcie"));
                    this.setDescripcionSector(row.getAttribute("sectorIbcie").toString());
                    //this.setMuestraCuestionarios(Boolean.TRUE);
                } else {
                    this.sectorIbcie = "Selecciona una opción";
                    this.setIdSector(0);
                    this.setDescripcionSector(this.sectorIbcie);
                    this.setMuestraCuestionarios(Boolean.FALSE);

                }

                if (row.getAttribute("subSectorIbcie").toString().trim().length() > 0) {
                    this.subSectorIbcie = row.getAttribute("subSectorIbcie").toString();
                    this.setIdSubSector((Integer) row.getAttribute("idSubSectorIbcie"));
                    this.setDescripcionSubSector( row.getAttribute("subSectorIbcie").toString());

                } else {
                    this.subSectorIbcie = "Selecciona una opción";
                    this.setIdSubSector(0);
                    this.setDescripcionSubSector( this.subSectorIbcie);
                    this.setMuestraCuestionarios(Boolean.FALSE);
                }

                if (this.getIdSector() > 0 && this.getIdSubSector() > 0) {

                    try {
                        OperationBinding operationBinding = null;
                        BindingContainer bindings = ADFUtils.getBindingContainer();
                        logger.log(ADFLogger.WARNING, "Entra en metodo del FenixAM");
                        operationBinding = bindings.getOperationBinding("consultarCuestionarios");
                        operationBinding.getParamsMap().put("idSector", this.getIdSector());
                        operationBinding.getParamsMap().put("idSubSector", this.getIdSubSector());
                        operationBinding.getParamsMap().put("idOperacion", Integer.parseInt(this.getIdOperacion()));
                        operationBinding.execute();
                        if (null != operationBinding.getResult()) {
                            this.setMuestraCuestionarios(Boolean.TRUE);
                        } else {
                            this.setMuestraCuestionarios(Boolean.FALSE);
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("GENERAR_CUESTIONARIOS_MSG02"));
                        }
                    } catch (Exception e) {
                        logger.log(ADFLogger.WARNING, "Error en consultarCuestionarios. " + e.getClass() + "." + e);
                    }

                    cargaMapas();


                }

            } //End for
        } else {
            logger.log(ADFLogger.WARNING, "La operacion : " + this.getIdOperacion() + " Es multisectorial");
            this.sectorIbcie = "Selecciona una opción";
            this.setIdSector(0);
            this.setMuestraCuestionarios(Boolean.FALSE);
            this.subSectorIbcie = "Selecciona una opción";
            this.setIdSubSector(0);
            this.setMuestraCuestionarios(Boolean.FALSE);
            cargaMapas();
        }

    }

    public static <K, V> K getSingleKeyFromValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void cargaMapas(){
        
        //Generacion de Mapas Sectior
        try {
            OperationBinding operationBinding = null;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            logger.log(ADFLogger.WARNING, "Entra en metodo del FenixAM");
            operationBinding = bindings.getOperationBinding("generarMapaSector");
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                this.setMapSectorIbcie((Map<Integer, String>) operationBinding.getResult());

            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("GENERAR_CUESTIONARIOS_MSG03"));
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en generarMapaSector. " + e.getClass() + "." + e);
        }

        //Generacion de Mapas Subsector
        try {
            OperationBinding operationBinding = null;
            BindingContainer bindings = ADFUtils.getBindingContainer();
            logger.log(ADFLogger.WARNING, "Entra en metodo del FenixAM");
            operationBinding = bindings.getOperationBinding("generarMapaSubSector");
            operationBinding.execute();
            if (null != operationBinding.getResult()) {
                this.setMapSubSectorIbcie((Map<Integer, String>) operationBinding.getResult());

            } else {
                JSFUtils.addFacesErrorMessage(getStringFromBundle("GENERAR_CUESTIONARIOS_MSG03"));
            }
        } catch (Exception e) {
            logger.log(ADFLogger.WARNING, "Error en generarMapaSubSector. " + e.getClass() + "." + e);
        }
        
    }

    public void setSectorIbcie(String sectorIbcie) {
        this.sectorIbcie = sectorIbcie;
    }


    public String getSectorIbcie() {
        return sectorIbcie;
    }


    public void setSubSectorIbcie(String subSectorIbcie) {
        this.subSectorIbcie = subSectorIbcie;
    }

    public String getSubSectorIbcie() {
        return subSectorIbcie;
    }

    public void setMapSectorIbcie(Map<Integer, String> mapSectorIbcie) {
        this.mapSectorIbcie = mapSectorIbcie;
    }

    public Map<Integer, String> getMapSectorIbcie() {
        return mapSectorIbcie;
    }

    public void setMapSubSectorIbcie(Map<Integer, String> mapSubSectorIbcie) {
        this.mapSubSectorIbcie = mapSubSectorIbcie;
    }

    public Map<Integer, String> getMapSubSectorIbcie() {
        return mapSubSectorIbcie;
    }


}
