package org.bcie.fenix.view.gestoroperaciones;

import com.bcie.xmlns.operacionservice.Operacion12BndQSService;
import com.bcie.xmlns.operacionservice.Operacion12Port;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.CollectionModel;

import org.bcie.catalogobo.Catalogo;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.vo.ConfigVisibilidadVORowImpl;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixPanelBean;

import org.bcie.operacionbo.MontoOperacion;
import org.bcie.operacionbo.MontosOperacion;
import org.bcie.operacionbo.Operacion;

import org.bcie.operacionmo.ConsultarOperacionByIdOperacionRequestType;
import org.bcie.operacionmo.ConsultarOperacionResponseType;

import org.bcie.resultbo.SuccessType;

import java.io.Serializable;
import java.io.StringWriter;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.model.SelectItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;


public class GestorOperacionesBean extends FenixPanelBean
    implements Serializable {
    @SuppressWarnings("compatibility:3993040006256848017")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    public static final String BUNDLE = "org.bcie.fenix.view.GestorOperacionesFenixVCBundle";

    // Constantes de productos
    private static final int COOPERACION_TECNICA_RECUPERACION_CONTINGENTE = 17;
    private static final int COOPERACION_TECNICA_NO_REEMBOMSABLE = 18;
    private static final int COOPERACION_RECURSOS_EXT_FOPROY = 19;
    private static final int COOPERACION_RECURSOS_EXT_FINAM = 20;
    private static final int COOPERACION_NO_REEMBOMSABLE_FETS = 21;
    private static final int PRE_INVERSION = 16;
    private Boolean esMontoTotal = false;
    private Boolean esTipoDeGarantia = false;
    private Boolean esComponenteDeConsecionalidad = false;
    private Boolean esEstructurador = false;
    private Boolean esBeneficiario = false;
    private Boolean esPrograma = false;
    private Boolean esOperacionAsociada = false;
    private Boolean filaSeleccionada = Boolean.TRUE;
    private boolean esGerentePais = Boolean.FALSE;

    // Variables Permisos Gestor
    private Boolean esGerenteRepresentante = false;
    private Boolean esOfic = false;
    private Boolean esProcessControl = false;
    private boolean esResponsableOperacion = false; //Responsable de la Operacion correspondiente a operación.
    private Boolean esGerenteResponsable = false; //Gerente correspondiente al Responsable de operación
    private Boolean esAnalistaSupervision = false;
    private Boolean esAnalistaUCE = false;
    private Boolean esAnalistaCOFI = false;
    private Boolean esEjecutivoPCT = false;
    private boolean esOficialAdquisiciones = false;
    private Boolean esMora = Boolean.FALSE;
    private Boolean esVisibleLineaCredito = Boolean.FALSE;
    
    private String nombreUsuario = "";    
    private String tipoProcesoEvaluaciones = "";
    private String tipoProcesoEvaluacionesDescripcion = "";

    // Variables Sector publico
    private Boolean esUnidadEjecutoraSecPublico = false;
    private Boolean esTipoDeGarantiaSecPublico = false;

    // Variables Sector Institucional - 21/sept/2020
    private Boolean esSecInstitucionalPublico = false;

    //variables CrearOperacion
    private Boolean esEjercicioPoa = false;
    private BigDecimal idCatPais = null;

    // Variables para página Detalle de la Operación
    private Long idOperacion = null;
    private String operacion = null;
    private String cliente = null;
    private String sectorInstitucional = null; // Funcionalidad de Sector Institucional - 21/sept/2020
    private String encargado = null; // Funcionalidad de Encargado - 21/sept/2020
    private String producto = null;
    private String estado = null;
    private String idCliente = null;
    private Integer idProducto = null;
    private BigDecimal montoSolicitado = null;
    private String pais = null;
    private String cuentaConNoObjecion = null;
    private Boolean trazabilidadFormalizacion;
    private Boolean visualizarImplementacionPct = Boolean.FALSE;
    private Boolean esFactible;
    private Long idEstadoOperacion = null;

    // Variable template CrearOperacion dinamicas
    private String template;
    private String panelHeaderTitulo;
    private Boolean botonesCrearOperacion;
    private Boolean botonesActualizarOperacion;
    private Boolean validaCondicion;

    // Variables para sección Datos generales en página Crear operación
    private String nombreCliente = null;

    //Variables para sección Crear Cliente
    private String razonSocialCliente;
    private String abreviaturaCliente;
    private Integer idTipoPersonaCliente;
    private Integer idSectorCliente;
    private Integer idTipoInstitucion;
    private Integer idPaisCliente;
    private Integer idGrupoCliente;
    private String numeroIdentificacionCliente;
    private Integer idOficinaCliente;

    //Variables para inicios de Procesos
    private String rolInicio;
    private String responsableOperacionInicio;
    private Boolean isLoteImplementacion;

    //Variables para parametros de Desembolsos
    private Long idOperacionDesembolsos;
    private Long idContratoDesembolsos;
    private Long idLineaCreditoDesembolsos;
    private Long idSolicitudDesembolsos;
    private Integer idTcaEstadoDesembolsos;
    private Integer idSectorMercado;
    private boolean implementacionPCTFinalizado = Boolean.FALSE;
    private Boolean mostrarIniciarProcesoFirmaContrato;
    private Boolean mostrarSolicitarFormalizacionParcial;
    private Boolean mostrarIniciarCumplimientoDeCondiciones;
    private Boolean esResponsableOperativo;
    private Boolean camposFactibilidad = Boolean.FALSE;
    private Boolean confirmaFactibilidad = Boolean.FALSE;
    private Boolean esEnvioGasto = Boolean.FALSE;
    private boolean mostrarFuncionReasignarResponsable = Boolean.FALSE;
    private Boolean tccFormalizacionAutomatica = Boolean.TRUE;
    private Boolean procesoAprobacionFinalizado = Boolean.FALSE;
    private Boolean resultadoSplit = Boolean.FALSE;
    private transient CollectionModel aplicacionesCM;
    private transient List<SelectItem> listaResponsablesSI;
    private String reasignarA;
    private Boolean operacionFormalizada = Boolean.FALSE;
    private Boolean esVisibleEstadoCondiciones = Boolean.FALSE;
    private Boolean esGerentePaisAdquisiciones = Boolean.FALSE;
    private Boolean esResponsableOperacionAdquisiciones = Boolean.FALSE;
    private Boolean esValidaFechaFirmaContrato = Boolean.FALSE;
    private Boolean esAprobacionFinalizado = Boolean.FALSE;
    private Boolean esRecuperacionFinalizado = Boolean.FALSE;
    Boolean procesoAprobacionTerminadoEnTrazabilidad = Boolean.FALSE;

    public GestorOperacionesBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void setResultadoSplit(Boolean resultadoSplit) {
        this.resultadoSplit = resultadoSplit;
    }

    public Boolean getResultadoSplit() {
        return resultadoSplit;
    }

    public void setConfirmaFactibilidad(Boolean confirmaFactibilidad) {
        this.confirmaFactibilidad = confirmaFactibilidad;
    }

    public Boolean getConfirmaFactibilidad() {
        return confirmaFactibilidad;
    }

    public void setEsEnvioGasto(Boolean esEnvioGasto) {
        this.esEnvioGasto = esEnvioGasto;
    }

    public Boolean getEsEnvioGasto() {
        return esEnvioGasto;
    }

    public void setIdSectorMercado(Integer idSectorMercado) {
        this.idSectorMercado = idSectorMercado;
    }

    public Integer getIdSectorMercado() {
        return idSectorMercado;
    }

    public Boolean getEsMontoTotal() {
        if ((esMontoTotal != true) || (esMontoTotal == false)) {
            DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer()
                                                         .findIteratorBinding("CrearOperacionVOIterator");

            if (voCrearOperacion.getRowAtRangeIndex(0) != null) {
                voCrearOperacion.getRowAtRangeIndex(0)
                                .setAttribute("MontoTotal", null);
            } else {
                logger.severe("Registro de Operacion no encontrado");
            }
        }

        return esMontoTotal;
    }

    public void setEsMontoTotal(Boolean esMontoTotal) {
        this.esMontoTotal = esMontoTotal;
    }

    public void setEsTipoDeGarantia(Boolean esTipoDeGarantia) {
        this.esTipoDeGarantia = esTipoDeGarantia;
    }

    public void setEsMora(Boolean esMora) {
        this.esMora = esMora;
    }

    public Boolean getEsMora() {
        return esMora;
    }

    public Boolean getEsTipoDeGarantia() {
        if ((esTipoDeGarantia != true) || (esTipoDeGarantia == false)) {
            DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer()
                                                         .findIteratorBinding("CrearOperacionVOIterator");

            if (voCrearOperacion.getRowAtRangeIndex(0) != null) {
                voCrearOperacion.getRowAtRangeIndex(0)
                                .setAttribute("TipoGarantia", null);
            } else {
                logger.severe("Registro de Operacion no encontrado");
            }
        }

        return esTipoDeGarantia;
    }

    public void setEsComponenteDeConsecionalidad(
        Boolean esComponenteDeConsecionalidad) {
        this.esComponenteDeConsecionalidad = esComponenteDeConsecionalidad;
    }

    public Boolean getEsComponenteDeConsecionalidad() {
        if ((esComponenteDeConsecionalidad != true) ||
                (esComponenteDeConsecionalidad == false)) {
            DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer()
                                                         .findIteratorBinding("CrearOperacionVOIterator");

            if (voCrearOperacion.getRowAtRangeIndex(0) != null) {
                voCrearOperacion.getRowAtRangeIndex(0)
                                .setAttribute("ComponenteConcecionalidadReformular",
                    null);
            } else {
                logger.severe("Registro de Operacion no encontrado");
            }
        }

        return esComponenteDeConsecionalidad;
    }

    public void setEsEstructurador(Boolean esEstructurador) {
        this.esEstructurador = esEstructurador;
    }

    public Boolean getEsEstructurador() {
        if ((esEstructurador != true) || (esEstructurador == false)) {
            DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer()
                                                         .findIteratorBinding("CrearOperacionVOIterator");

            if (voCrearOperacion.getRowAtRangeIndex(0) != null) {
                voCrearOperacion.getRowAtRangeIndex(0)
                                .setAttribute("Estructurador", null);
            } else {
                logger.severe("Registro de Operacion no encontrado");
            }
        }

        return esEstructurador;
    }

    public void setEsBeneficiario(Boolean esBeneficiario) {
        this.esBeneficiario = esBeneficiario;
    }

    public Boolean getEsBeneficiario() {
        if ((esBeneficiario != true) || (esBeneficiario == false)) {
            DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer()
                                                         .findIteratorBinding("CrearOperacionVOIterator");

            if (voCrearOperacion.getRowAtRangeIndex(0) != null) {
                voCrearOperacion.getRowAtRangeIndex(0)
                                .setAttribute("Beneficiario", null);
            } else {
                logger.severe("Registro de Operacion no encontrado");
            }
        }

        return esBeneficiario;
    }

    public void setEsPrograma(Boolean esPrograma) {
        this.esPrograma = esPrograma;
    }

    public Boolean getEsPrograma() {
        if ((esPrograma != true) || (esPrograma == false)) {
            DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer()
                                                         .findIteratorBinding("CrearOperacionVOIterator");

            if (voCrearOperacion.getRowAtRangeIndex(0) != null) {
                voCrearOperacion.getRowAtRangeIndex(0)
                                .setAttribute("Programa", null);
            } else {
                logger.severe("Registro de Operacion no encontrado");
            }
        }

        return esPrograma;
    }

    public void setEsOperacionAsociada(Boolean esOperacionAsociada) {
        this.esOperacionAsociada = esOperacionAsociada;
    }

    public Boolean getEsOperacionAsociada() {
        //No se ocupa la bandera
        return esOperacionAsociada;
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setEsSecInstitucionalPublico(Boolean esSecInstitucionalPublico) {
        this.esSecInstitucionalPublico = esSecInstitucionalPublico;
    }

    public Boolean getEsSecInstitucionalPublico() {
        return esSecInstitucionalPublico;
    }

    public void setSectorInstitucional(String sectorInstitucional) {
        this.sectorInstitucional = sectorInstitucional;
    }

    public String getSectorInstitucional() {
        return sectorInstitucional;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setCuentaConNoObjecion(String cuentaConNoObjecion) {
        this.cuentaConNoObjecion = cuentaConNoObjecion;
    }

    public String getCuentaConNoObjecion() {
        return cuentaConNoObjecion;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setEsGerenteResponsable(Boolean esGerenteResponsable) {
        this.esGerenteResponsable = esGerenteResponsable;
    }

    public Boolean getEsGerenteResponsable() {
        return esGerenteResponsable;
    }

    public void setEsAnalistaSupervision(Boolean esAnalistaSupervision) {
        this.esAnalistaSupervision = esAnalistaSupervision;
    }

    public Boolean getEsAnalistaSupervision() {
        return esAnalistaSupervision;
    }

    public void setEsAnalistaUCE(Boolean esAnalistaUCE) {
        this.esAnalistaUCE = esAnalistaUCE;
    }

    public Boolean getEsAnalistaUCE() {
        return esAnalistaUCE;
    }

    public void setEsAnalistaCOFI(Boolean esAnalistaCOFI) {
        this.esAnalistaCOFI = esAnalistaCOFI;
    }

    public Boolean getEsAnalistaCOFI() {
        return esAnalistaCOFI;
    }

    public void setRolInicio(String rolInicio) {
        this.rolInicio = rolInicio;
    }

    public String getRolInicio() {
        return rolInicio;
    }

    public void setResponsableOperacionInicio(String responsableOperacionInicio) {
        this.responsableOperacionInicio = responsableOperacionInicio;
    }

    public String getResponsableOperacionInicio() {
        return responsableOperacionInicio;
    }

    public void setEsFactible(Boolean esFactible) {
        this.esFactible = esFactible;
    }

    public Boolean getEsFactible() {
        return esFactible;
    }

    public void setCamposFactibilidad(Boolean camposFactibilidad) {
        this.camposFactibilidad = camposFactibilidad;
    }

    public Boolean getCamposFactibilidad() {
        return camposFactibilidad;
    }

    public String inicializarConfiguracionByIdProducto() {
        logger.log(ADFLogger.TRACE,
            "Inside inicializarConfiguracionByIdProducto()");

        BindingContainer bindings = getBindings();

        //Invocar el metodo recuperarConfiguracionVisibilidad declarado en el AM.
        OperationBinding operationBinding = bindings.getOperationBinding(
                "recuperarConfiguracionVisibilidad");
        //Recuperar el idProducto, valor se obtiene de la entrada del taskFlow
        operationBinding.getParamsMap()
                        .put("idProducto",
            JSFUtils.resolveExpression("#{pageFlowScope.pIdProducto}"));
        operationBinding.execute();

        ConfigVisibilidadVORowImpl rowConfigVisibilidad = (ConfigVisibilidadVORowImpl) operationBinding.getResult();
        logger.log(ADFLogger.TRACE,
            "ID TABLA CONFIGURACION: " + rowConfigVisibilidad.getId());
        logger.log(ADFLogger.TRACE,
            "ID PRODUCTO: " + rowConfigVisibilidad.getIdProducto());
        logger.log(ADFLogger.TRACE,
            "DatosGenerales: " +
            rowConfigVisibilidad.getDatosGenerales().toString());
        logger.log(ADFLogger.TRACE,
            "MontoTotal: " + rowConfigVisibilidad.getMontoTotal().toString());
        logger.log(ADFLogger.TRACE,
            "Actividad Economica: " +
            rowConfigVisibilidad.getActividadEconomica().toString());
        logger.log(ADFLogger.TRACE,
            "Iniciativa estrategica: " +
            rowConfigVisibilidad.getIniciativaEstrategica().toString());
        logger.log(ADFLogger.TRACE,
            "Objetivo estrategico: " +
            rowConfigVisibilidad.getObjetivoEstrategico().toString());
        logger.log(ADFLogger.TRACE,
            "Area focalizacion: " +
            rowConfigVisibilidad.getAreaFocalizacion().toString());
        logger.log(ADFLogger.TRACE,
            "Eje estrategico: " +
            rowConfigVisibilidad.getEjeEstrategico().toString());
        logger.log(ADFLogger.TRACE,
            "Tipo de garantia: " +
            rowConfigVisibilidad.getTipoGarantia().toString());
        logger.log(ADFLogger.TRACE,
            "Componente de consecinalidad:" +
            rowConfigVisibilidad.getComponenteConcesionalidad().toString());
        logger.log(ADFLogger.TRACE,
            "Estructurador:" +
            rowConfigVisibilidad.getEstructurador().toString());
        logger.log(ADFLogger.TRACE,
            "Beneficiario:" +
            rowConfigVisibilidad.getBeneficiario().toString());
        logger.log(ADFLogger.TRACE,
            "Programa:" + rowConfigVisibilidad.getPrograma().toString());
        logger.log(ADFLogger.TRACE,
            "Operaciones asociadas:" +
            rowConfigVisibilidad.getOperacionesAsociadas().toString());
        logger.log(ADFLogger.TRACE,
            "Responde cuestionario:" +
            rowConfigVisibilidad.getRespondeCuestionario().toString());
        logger.log(ADFLogger.TRACE,
            "Analisis LAFT:" +
            rowConfigVisibilidad.getAnalisisLaft().toString());
        logger.log(ADFLogger.TRACE,
            "Sector mercado:" +
            rowConfigVisibilidad.getSectorMercado().toString());
        //Invoca el metodo mapeoConfiguracionVisibilidad para mapear los valores obtenidos
        this.mapeoConfiguracionVisibilidad(rowConfigVisibilidad);

        // return para continuar el flujo al siguiente metodo 
        return "irConsultarOperacionReformular";
    }

    public void mapeoConfiguracionVisibilidad(
        ConfigVisibilidadVORowImpl rowConfigVisibilidad) {
        logger.log(ADFLogger.TRACE, "Inside mapeoConfiguracionVisibilidad");

        if (Integer.parseInt(rowConfigVisibilidad.getMontoTotal().toString()) == 0) {
            this.setEsMontoTotal(false);
        } else {
            this.setEsMontoTotal(true);
        }

        if (Integer.parseInt(rowConfigVisibilidad.getTipoGarantia().toString()) == 0) {
            this.setEsTipoDeGarantia(false);
        } else {
            this.setEsTipoDeGarantia(true);
        }

        if (Integer.parseInt(rowConfigVisibilidad.getComponenteConcesionalidad()
                                                     .toString()) == 0) {
            this.setEsComponenteDeConsecionalidad(false);
        } else {
            this.setEsComponenteDeConsecionalidad(true);
        }

        if (Integer.parseInt(rowConfigVisibilidad.getEstructurador().toString()) == 0) {
            this.setEsEstructurador(false);
        } else {
            this.setEsEstructurador(true);
        }

        if (Integer.parseInt(rowConfigVisibilidad.getBeneficiario().toString()) == 0) {
            this.setEsBeneficiario(false);
        } else {
            this.setEsBeneficiario(true);
        }

        if (Integer.parseInt(rowConfigVisibilidad.getPrograma().toString()) == 0) {
            this.setEsPrograma(false);
        } else {
            this.setEsPrograma(true);
        }
    }

    public String inicializarConsultarOperacionReformular() {
        // Invocamos actualizarDetalleOperacion
        OperationBinding operationBinding;
        Operacion operacion = new Operacion();
        BindingContainer bindings = getBindings();
        HashMap<String, ConsultarOperacionResponseType> respuestaServicio = null;
        ConsultarOperacionResponseType response = new ConsultarOperacionResponseType();
        String errorCode = null;
        DCIteratorBinding voOperacionesAsociadas = null;

        //recuperar el parametro de entrada del taskFlow busquedaOperacionBTF
        Long idOperacion = Long.valueOf((String) JSFUtils.resolveExpression(
                    "#{pageFlowScope.pIdOperacion}"));

        //To do  (validar que el casting funcione)
        Integer idProducto = (Integer) JSFUtils.resolveExpression(
                "#{pageFlowScope.pIdProducto}");
        //invocar el meotodo set y ejecutar el query de OperacionesAsociadas para recuperarlas
        operationBinding = bindings.getOperationBinding("setidOperacion");
        operationBinding.getParamsMap().put("value", idOperacion);
        operationBinding.execute();

        if (operationBinding.getErrors().size() != 0) {
            // Manejo de errores    
        }

        voOperacionesAsociadas = ADFUtils.getDCBindingContainer()
                                         .findIteratorBinding("OperacionesAsociadasLOVIterator");
        voOperacionesAsociadas.executeQuery();
        // invocamos el metodo consultarOperacionReformular del AM
        operationBinding = bindings.getOperationBinding(
                "consultarOperacionReformular");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idProducto", idProducto);
        //ejecuta el metodo consultarOperacionReformular del AM
        operationBinding.execute();

        if (operationBinding.getErrors().size() != 0) {
            // Manejo de errores    
        } else if (operationBinding.getResult() != null) {
            respuestaServicio = (HashMap<String, ConsultarOperacionResponseType>) operationBinding.getResult();
            response = respuestaServicio.get("response");

            if (response.getOperacion() != null) {
                if (response.getResultado().getResult().toString() == "OK") {
                    operacion = (org.bcie.operacionbo.Operacion) response.getOperacion()
                                                                         .get(0);
                }

                //setiar el valor de la razon social a la vista
                this.setNombreCliente(operacion.getCliente().getRazonSocial());

                //recuperar el sector del clientes.
                //validar si sector es igual "Sector Público"
                if (("Sector Público" == operacion.getCliente().getSector()
                                                      .getDescripcion()) ||
                        (true == operacion.getCliente().getSector()
                                              .getDescripcion()
                                              .equals("Sector Público"))) {
                    this.setEsUnidadEjecutoraSecPublico(Boolean.TRUE);
                    this.setEsSecInstitucionalPublico(Boolean.TRUE);
                    this.setEsTipoDeGarantiaSecPublico(Boolean.TRUE);
                } else {
                    this.setEsUnidadEjecutoraSecPublico(Boolean.FALSE);
                    this.setEsSecInstitucionalPublico(Boolean.FALSE);
                    this.setEsTipoDeGarantiaSecPublico(Boolean.FALSE);
                }
            } else {
                JSFUtils.addFacesErrorMessage(response.getResultado()
                                                      .getMessage().toString());
            }
        }

        return "irActualizarOperacion";
    }

    public String inicializarDetalleOperacion() {
        // Inicializamos valores del header de la página Detalle de la operación
        logger.log(ADFLogger.WARNING, "Inside inicializarDetalleOperacion");

        Operacion12BndQSService operacion12BndQSService = null;
        Operacion12Port operacionPort = null;
        ConsultarOperacionByIdOperacionRequestType requestConsultarOperacionById =
            null;
        ConsultarOperacionResponseType consultarOperacionById = null;
        SuccessType result = null;

        //agrega variables FNXII-2901
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        //FNXII-2901
        //Inicializar valor "Cuenta con la no objeción"
        try {
            logger.warning("set idOperacion to ConsultarDeclaracionJurada");
            logger.warning("idOperacion: " + this.getIdOperacion());
            operationBinding = bindings.getOperationBinding(
                    "consultarDeclaracionJurada");
            operationBinding.getParamsMap()
                            .put("idOperacion", this.getIdOperacion().toString());
            operationBinding.execute();
            //Trazabilidad de la operación
            logger.warning("set idOperacion to TrazabilidadOperacionVO");
            operationBinding = bindings.getOperationBinding("setoperacionId");
            operationBinding.getParamsMap().put("value", this.getIdOperacion());
            operationBinding.execute();
            //Tareas activas
            logger.warning("set idOperacion to TareasActivasOperacionVO");
            operationBinding = bindings.getOperationBinding("setvarIdOperacion");
            operationBinding.getParamsMap().put("value", this.getIdOperacion());
            operationBinding.execute();
            //Asociacion de Operaciones
            logger.warning("set idOperacion to ConsultarAsociadasVO");
            operationBinding = bindings.getOperationBinding(
                    "setvarIdOperacion1");
            operationBinding.getParamsMap().put("value", this.getIdOperacion());
            operationBinding.execute();
            //Crear row Responsable Operacion
            logger.warning("crearRowResponsable");
            operationBinding = bindings.getOperationBinding(
                    "crearRowResponsable");
            operationBinding.execute();

            logger.warning("Consulta tipo de monto Aprobado y Formalizado");

            BigDecimal monto = null;
            operationBinding = bindings.getOperationBinding(
                    "obtenerMontoPorTipo");
            operationBinding.getParamsMap()
                            .put("varIdOperacion", this.getIdOperacion());
            operationBinding.getParamsMap()
                            .put("varTipoMonto", Integer.valueOf(3));
            operationBinding.execute();

            monto = (BigDecimal) operationBinding.getResult();
            logger.warning("Monto: " + monto);

            if (monto == null) {
                operationBinding = bindings.getOperationBinding(
                        "obtenerMontoPorTipo");
                operationBinding.getParamsMap()
                                .put("varIdOperacion", this.getIdOperacion());
                operationBinding.getParamsMap()
                                .put("varTipoMonto", Integer.valueOf(5));
                operationBinding.execute();

                monto = (BigDecimal) operationBinding.getResult();

                if (monto != null) {
                    logger.warning("Existe tipo de monto tipo 5");
                    resultadoSplit = Boolean.TRUE;
                } else {
                    resultadoSplit = Boolean.FALSE;
                }
            } else {
                resultadoSplit = Boolean.TRUE;
            }

            logger.warning("resultadoSplit: " + resultadoSplit);
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, e.getClass() + " : ", e);
        }

        //termina FNXII-2901
        boolean isErrorWS = false;

        try {
            logger.log(ADFLogger.WARNING,
                "Invocando Servicio - Consultar operacion por Id-");
            operacion12BndQSService = this.initOperacionService();
            operacionPort = operacion12BndQSService.getOperacion12BndQSPort();

            logger.warning("Se consume para el Id de Operacion: " +
                this.getIdOperacion().longValue());

            requestConsultarOperacionById = new ConsultarOperacionByIdOperacionRequestType();
            requestConsultarOperacionById.setIdOperacion(this.getIdOperacion()
                                                             .longValue());

            //Descomentar si se requieren datos del Cliente.
            //requestConsultarOperacionById.setInfoDetalleCliente(Boolean.TRUE);
            logger.log(ADFLogger.WARNING,
                ">HNWS" +
                writeXMLRequest(requestConsultarOperacionById,
                    requestConsultarOperacionById.getClass()));

            consultarOperacionById = operacionPort.consultarOperacionByIdOperacion(requestConsultarOperacionById);

            result = consultarOperacionById.getResultado().getResult();
            logger.log(ADFLogger.WARNING,
                ">HNWS" +
                writeXMLRequest(consultarOperacionById,
                    consultarOperacionById.getClass()));

            if (result.value().equalsIgnoreCase("OK")) {
                List<Operacion> listaOperaciones = consultarOperacionById.getOperacion();
                logger.log(ADFLogger.WARNING,
                    "listaOperaciones.size():" + listaOperaciones.size());

                if (listaOperaciones.size() > 0) {
                    Operacion detalleOperacion = listaOperaciones.get(0);
                    String claveProducto = Long.toString(detalleOperacion.getProducto()
                                                                         .getIdProducto());
                    setOperacion(detalleOperacion.getNombre());
                    setCliente(detalleOperacion.getCliente().getRazonSocial());

                    // Funcionalidad de Sector Institucional y Encargado - 21/sept/2020
                    if (detalleOperacion.getSectorInstitucional() != null) {
                        setSectorInstitucional(detalleOperacion.getSectorInstitucional()
                                                               .getDescripcion());
                    }

                    if (detalleOperacion.getEncargado() != null) {
                        setEncargado(detalleOperacion.getEncargado()
                                                     .getDescripcion());
                    }

                    if (detalleOperacion.getEstado() != null) {
                        Catalogo estadoCat = detalleOperacion.getEstado();
                        logger.warning("id Estado de operacion : " +
                            estadoCat.getId());
                        logger.warning("descripcion estado de operacion : " +
                            estadoCat.getDescripcion());
                        logger.warning(
                            "descripcion corta estado de operacion : " +
                            estadoCat.getDescripcionCorta());
                        setIdEstadoOperacion(estadoCat.getId());
                        logger.warning("idEstadoOperacion:" +
                            getIdEstadoOperacion());
                    } else {
                        logger.severe(
                            "estado de operacion es nulo, no se settea valor a idEstadoOperacion");
                    }

                    //setMontoSolicitado(detalleOperacion.getMontoSolicitado());
                    MontosOperacion montosOperacion = detalleOperacion.getMontoOperacion();

                    if ((null != montosOperacion) &&
                            (montosOperacion.getMontoOperacion().size() > 0)) {
                        for (MontoOperacion monto : montosOperacion.getMontoOperacion()) {
                            if (FenixModelConstants.TIPO_MONTO_SOLICITADO.compareTo(
                                        monto.getIdTcaTipoMonto()) == 0) {
                                setMontoSolicitado(monto.getMonto());
                            }
                        }
                    }

                    // producto -- pendiente de mapear
                    setIdProducto(Integer.parseInt(claveProducto));
                    setProducto(detalleOperacion.getProducto().getDescripcion());

                    Integer producto = getIdProducto();

                    if ((producto == 17) || (producto == 18) ||
                            (producto == 19) || (producto == 20) ||
                            (producto == 21)) {
                        logger.warning("IdProducto: " + producto);
                        setMostrarIniciarProcesoFirmaContrato(Boolean.TRUE);
                    } else {
                        setMostrarIniciarProcesoFirmaContrato(Boolean.FALSE);
                    }

                    logger.warning("MostrarIniciarProcesoFirmaContrato: " +
                        getMostrarIniciarProcesoFirmaContrato());

                    procesoAprobacionTerminadoEnTrazabilidad = getValidarProcesoOperacionTerminado(4);

                    if (procesoAprobacionTerminadoEnTrazabilidad) {
                        setMostrarSolicitarFormalizacionParcial(Boolean.TRUE);
                        setMostrarIniciarCumplimientoDeCondiciones(Boolean.TRUE);
                    } else {
                        setMostrarSolicitarFormalizacionParcial(Boolean.FALSE);
                        setMostrarIniciarCumplimientoDeCondiciones(Boolean.FALSE);
                    }

                    logger.warning("MostrarSolicitarFormalizacionParcial: " +
                        getMostrarSolicitarFormalizacionParcial());
                    logger.warning("MostrarIniciarCumplimientoDeCondiciones: " +
                        getMostrarIniciarCumplimientoDeCondiciones());

                    //Validar proceso ImplementacionPCT terminado
                    operationBinding = bindings.getOperationBinding(
                            "validarProcesoOperacionTerminado");
                    operationBinding.getParamsMap()
                                    .put("idProceso",
                        FenixConstants.PROCESO_IMPLEMENTACION_PCT);

                    Integer respuestaProcesoTerminado = (Integer) operationBinding.execute();
                    logger.warning(
                        "respuesta validarProcesoOperacionTerminado:" +
                        respuestaProcesoTerminado);

                    if ((null != respuestaProcesoTerminado) &&
                            respuestaProcesoTerminado.equals(new Integer(1))) {
                        setImplementacionPCTFinalizado(Boolean.TRUE);
                    }

                    logger.warning("ImplementacionPCTFinalizado: " +
                        implementacionPCTFinalizado);

                    logger.warning("Sector: " +
                        detalleOperacion.getSectorMercado().getId());
                    setIdSectorMercado(detalleOperacion.getSectorMercado()
                                                       .getId().intValue());
                    // estado -- pendiente de mapear                    
                    /*switch (detalleOperacion.getEstado().getId().intValue()){
                    case 1:
                        setEstado(getStringFromBundle("ESTADO_EN_PROCESO"));
                        break;
                    case 2:
                        setEstado(getStringFromBundle("ESTADO_SUSPENDIDO"));
                        break;
                    case 3:
                        setEstado(getStringFromBundle("ESTADO_SUSPENDIDO"));
                        break;
                    case 4:
                        setEstado(getStringFromBundle("ESTADO_SUSPENDIDO"));
                        break;
                    }*/
                    setEstado(detalleOperacion.getEstado().getDescripcion());
                    logger.warning("Estado: " +
                        detalleOperacion.getEstado().getDescripcion());
                    setIdCliente(Long.valueOf(detalleOperacion.getCliente()
                                                              .getIdCliente())
                                     .toString());
                    logger.warning("IdCliente: " +
                        Long.valueOf(detalleOperacion.getCliente().getIdCliente())
                            .toString());
                    setPais(detalleOperacion.getCliente().getPais()
                                            .getDescripcion());
                    logger.warning("Pais: " +
                        detalleOperacion.getCliente().getPais().getDescripcion());

                    //Descomentar si se requiere Mora y se envia como parametro en request setInfoDetalleCliente en true.
                    //setEsMora(detalleOperacion.getCliente().isEnMora());
                    //logger.warning("Mora: " + getEsMora());

                    //FIX(FNXII-3052)
                    String responsableOperacion = detalleOperacion.getResponsable();
                    logger.warning("responsableOperacion: " +
                        detalleOperacion.getResponsable());

                    String loginUsuario = ADFContext.getCurrent()
                                                    .getSecurityContext()
                                                    .getUserName();
                    logger.warning("loginUsuario: " +
                        ADFContext.getCurrent().getSecurityContext()
                                  .getUserName());

                    setEsResponsableOperacion(Boolean.FALSE);
                    setEsGerenteResponsable(Boolean.FALSE);

                    setResponsableOperacionInicio(responsableOperacion);

                    String[] strAllRoles = ADFContext.getCurrent()
                                                     .getSecurityContext()
                                                     .getUserRoles();

                    logger.warning("Grupos del usuario: " +
                        ADFContext.getCurrent().getSecurityContext()
                                  .getUserName());

                    for (String role : strAllRoles) {
                        logger.warning("Roles : " + role);

                        if (role.startsWith("FENIX_REPRESENTANTE")) {
                            if ((responsableOperacion != null) &&
                                    (loginUsuario != null)) {
                                if (responsableOperacion.trim()
                                                            .equalsIgnoreCase(loginUsuario.trim())) {
                                    setEsResponsableOperacion(Boolean.TRUE);
                                    setRolInicio("FENIX_REPRESENTANTE");
                                }
                            }
                        }

                        if (role.startsWith("FENIX_GERENTE")) {
                            BindingContainer bindingsGerente = getBindings();
                            OperationBinding operBinding = null;
                            operBinding = bindingsGerente.getOperationBinding(
                                    "validarGerenteResponsable");
                            operBinding.getParamsMap()
                                       .put("responsableOperacion",
                                responsableOperacion);
                            operBinding.getParamsMap()
                                       .put("loginUsuario", loginUsuario);

                            Boolean respuesta = (Boolean) operBinding.execute();

                            if (respuesta) {
                                setEsGerenteResponsable(Boolean.TRUE);
                            }
                        }

                        //Resulve FNXII-5672. 19042017
                        if (role.startsWith("FENIX_EJECUTIVO_PCT")) {
                            setEsEjecutivoPCT(Boolean.TRUE);
                        }
                    }

                    logger.warning("EsResponsableOperacion: " +
                        esResponsableOperacion);
                    logger.warning("RolInicio: " + rolInicio);
                    logger.warning("EsGerenteResponsable: " +
                        esGerenteResponsable);
                    logger.warning("ResponsableOperacionInicio: " +
                        responsableOperacionInicio);
                    logger.warning("EsEjecutivoPCT: " + esEjecutivoPCT);
                }
            } else {
                logger.log(ADFLogger.WARNING,
                    "No se recuperaron datos del servicio WSDL_OPERACION en su metodo consultarOperacionByIdOperacion");
                isErrorWS = true;
                JSFUtils.addFacesErrorMessage(
                    "Error. El servicio ConsultarOperacionById no devuelve informaci\u00F3n de la operaci\u00F3n");
            }

            //Se valida que la operacion este formailzada
            validaOperacionFormalizada();
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                "Ocurrio un error en la validacion de roles por operacion.", e);
            isErrorWS = true;
            JSFUtils.addFacesErrorMessage(
                "Error al procesar informacion del servicio de ConsultarOperacionById");
        }

        if (!isErrorWS) {
            try {
                logger.log(ADFLogger.TRACE,
                    "set idOperacion to setpIdOperacionConRegNoCumpDesemExc");
                logger.log(ADFLogger.TRACE,
                    "idOperacion: " + this.getIdOperacion());
                operationBinding = bindings.getOperationBinding(
                        "setpIdOperacionConRegNoCumpDesemExc");
                operationBinding.getParamsMap()
                                .put("value",
                    Long.parseLong(this.getIdOperacion().toString()));
                operationBinding.execute();
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR, e.getClass() + " : ", e);
            }

            setEsResponsableOperativo(obtuvoResponsable());
            logger.severe("responsable: " + getEsResponsableOperativo());

            //Validar si existen lotes de implementacion para finalizar.
            Long idOperacion = null;

            try {
                if (null != getIdOperacion()) {
                    idOperacion = getIdOperacion();
                    operationBinding = bindings.getOperationBinding(
                            "inicializarLoteImplementacionFinConcurso");
                    operationBinding.getParamsMap()
                                    .put("idOperacion", idOperacion);
                    isLoteImplementacion = (Boolean) operationBinding.execute();
                    logger.log(ADFLogger.WARNING,
                        "Validacion de  los lotes es:" + isLoteImplementacion);
                } else {
                    logger.warning(
                        "El id de la operacion es nulo, No se realiza busqueda de lotes.");
                }
            } catch (Exception e) {
                logger.warning("Error en al obtener los lotes para finalizar." +
                    e.getClass() + ".", e);
            }

            verificaAprobacion();
            verificaFormalizacionAutomatica();

            //se remueve validacion por incidencia FNXII-7370
            formalizacionTrazabilidad();
            //se agrega validacion por el termino de fecha / firma de contrato para determinar si se ve la accion 
            //de inicio formalizacion
            validarFechaFirmaContrato();

            visualizarImplementacionPct();
            visualizarFactibilidad();
            visualizarCalculoInteresCobro();

            //Validacion para comportamiento en "Determinar factiblidad" y "Enviar al gasto"
            modoEnvioGasto();
        }

        //Validacion FNXII-6897
        //Esta opción se debe mostrar hasta que se cuente con al menos un proceso de condiciones activo
        //derivado de un proceso de desembolsos.
        validarEsVisibleEstadoCondiciones();

        //FNXII-7370
        //validar que el proceso  de aprobacion se finalizo por medio de la tabla TBI_PROCESO_OPERACION, comparando
        //la cantidad de tareas activas contra la cantidad de tareas cerradas
        validarEsAprobacionFinalizado();

        //FNXII-7368
        //validar que el proceso de recuperacion UCE se finalizo por medio de la tabla TBI_PROCESO_OPERACION, comparando
        //la cantidad de proceso inicio contra la cantidad de procesos fializados
        validarEsRecuperacionFinalizado();

        //El Tab linea de credito sera visibles solo si existe una o mas lineas asociadas a la operacion
        validarEsVisibleLineaCredito();
        
        return "irDetalleOperacion";
    }

    private String getStringFromBundle(String psKey) {
        ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
        String txt = null;

        if (resourceBundle != null) {
            txt = resourceBundle.getString(psKey);
        }

        return txt;
    }

    public Boolean getEsUnidadEjecutoraSecPublico() {
        //valida valor = false
        if ((esUnidadEjecutoraSecPublico != true) ||
                (esUnidadEjecutoraSecPublico == false)) {
            //Crea instancia del iterador CrearOperacionVOIterator expuesto en  Binding CrearOperacion.jsff
            DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer()
                                                         .findIteratorBinding("CrearOperacionVOIterator");

            //RowAtRangeIndex = 0,al valor UnidadEjecutora se coloca en null        
            if (voCrearOperacion.getRowAtRangeIndex(0) != null) {
                voCrearOperacion.getRowAtRangeIndex(0)
                                .setAttribute("UnidadEjecutora", null);
            } else {
                logger.severe("Registro de Operacion no encontrado");
            }
        }

        return esUnidadEjecutoraSecPublico;
    }

    public void setEsUnidadEjecutoraSecPublico(
        Boolean esUnidadEjecutoraSecPublico) {
        this.esUnidadEjecutoraSecPublico = esUnidadEjecutoraSecPublico;
    }

    public Boolean getEsTipoDeGarantiaSecPublico() {
        //Valida valor = false
        if ((esTipoDeGarantiaSecPublico != true) ||
                (esTipoDeGarantiaSecPublico == false)) {
            //Crea instancia del iterador CrearOperacionVOIterator expuesto en  Binding CrearOperacion.jsff
            DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer()
                                                         .findIteratorBinding("CrearOperacionVOIterator");

            //RowAtRangeIndex = 0,al valor TipoGarantia se coloca en null
            if (voCrearOperacion.getRowAtRangeIndex(0) != null) {
                voCrearOperacion.getRowAtRangeIndex(0)
                                .setAttribute("TipoGarantia", null);
            } else {
                logger.severe("Registro de Operacion no encotrado");
            }
        }

        return esTipoDeGarantiaSecPublico;
    }

    public void setEsTipoDeGarantiaSecPublico(
        Boolean esTipoDeGarantiaSecPublico) {
        this.esTipoDeGarantiaSecPublico = esTipoDeGarantiaSecPublico;
    }

    public String getTemplate() {
        try {
            Boolean esBpm = Boolean.valueOf((String) JSFUtils.resolveExpression(
                        "#{pageFlowScope.esBPM}"));

            if (esBpm) {
                template = "/templates/reformularOperacionTemplate.jspx";
            } else {
                template = "/templates/gestorOperacionesTemplate.jspx";
            }
        } catch (Exception e) {
            template = "/templates/gestorOperacionesTemplate.jspx";
            e.printStackTrace();
        }

        return template;
    }

    public String getPanelHeaderTitulo() {
        Boolean esBpm = Boolean.valueOf((String) JSFUtils.resolveExpression(
                    "#{pageFlowScope.esBPM}"));

        if (esBpm) {
            panelHeaderTitulo = "";
        } else {
            panelHeaderTitulo = "Crear operación";
        }

        return panelHeaderTitulo;
    }

    public Boolean getBotonesCrearOperacion() {
        Boolean esBpm = Boolean.valueOf((String) JSFUtils.resolveExpression(
                    "#{pageFlowScope.esBPM}"));

        if (esBpm) {
            botonesCrearOperacion = false;
        } else {
            botonesCrearOperacion = true;
        }

        return botonesCrearOperacion;
    }

    public Boolean getBotonesActualizarOperacion() {
        Boolean esBpm = Boolean.valueOf((String) JSFUtils.resolveExpression(
                    "#{pageFlowScope.esBPM}"));

        if (esBpm) {
            botonesActualizarOperacion = true;
        } else {
            botonesActualizarOperacion = false;
        }

        return botonesActualizarOperacion;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public Boolean getEsEjercicioPoa() {
        if ((esEjercicioPoa != true) || (esEjercicioPoa == false)) {
            DCIteratorBinding voCrearOperacion = ADFUtils.getDCBindingContainer()
                                                         .findIteratorBinding("CrearOperacionVOIterator");

            if (voCrearOperacion.getRowAtRangeIndex(0) != null) {
                voCrearOperacion.getRowAtRangeIndex(0)
                                .setAttribute("EjercicioPoa", null);
            } else {
                logger.severe("Registro de Operacion no encontrado");
            }
        }

        return esEjercicioPoa;
    }

    public void setEsEjercicioPoa(Boolean esEjercicioPoa) {
        this.esEjercicioPoa = esEjercicioPoa;
    }

    public void setIdCatPais(BigDecimal idCatPais) {
        this.idCatPais = idCatPais;
    }

    public BigDecimal getIdCatPais() {
        return idCatPais;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public void setEsGerenteRepresentante(Boolean esGerenteRepresentante) {
        this.esGerenteRepresentante = esGerenteRepresentante;
    }

    public Boolean getEsGerenteRepresentante() {
        return esGerenteRepresentante;
    }

    public void setEsOfic(Boolean esOfic) {
        this.esOfic = esOfic;
    }

    public Boolean getEsOfic() {
        return esOfic;
    }

    public void setEsProcessControl(Boolean esProcessControl) {
        this.esProcessControl = esProcessControl;
    }

    public Boolean getEsProcessControl() {
        return esProcessControl;
    }

    public String inicializarIdOperacion() {
        // Add event code here...
        logger.log(ADFLogger.TRACE, "Inside inicializarIdOperacion");

        Long pIdOperacion = new java.lang.Long((JSFUtils.resolveExpression(
                    "#{param.pIdOperacion}") != null)
                ? JSFUtils.resolveExpression("#{param.pIdOperacion}").toString()
                : "0");

        if (pIdOperacion != null) {
            setIdOperacion(pIdOperacion);
            logger.log(ADFLogger.WARNING, "pIdOperacion: " + pIdOperacion);

            // Subimos a view scope el pIdOperacion
            ADFContext.getCurrent().getViewScope()
                      .put("pIdOperacion", pIdOperacion);
            logger.log(ADFLogger.WARNING,
                "viewScope.pIdOperacion: " +
                JSFUtils.resolveExpression("#{viewScope.pIdOperacion}"));
        }

        return "irInicializarDetalleOperacion";
    }

    public void getPermissions() {
        String[] strAllRoles = ADFContext.getCurrent().getSecurityContext()
                                         .getUserRoles();

        setNombreUsuario(ADFContext.getCurrent().getSecurityContext()
                                   .getUserName());

        logger.warning("Grupos del usuario: " + getNombreUsuario());

        for (String role : strAllRoles) {
            logger.warning("Roles : " + role);

            if (role.startsWith("FENIX_REPRESENTANTE") ||
                    role.startsWith("FENIX_GERENTE")) {
                if (role.startsWith("FENIX_REPRESENTANTE")) {
                    logger.warning("Se recupera rol de FENIX_REPRESENTANTE");
                    //se trata de  un representante
                    this.setEsResponsableOperacionAdquisiciones(Boolean.TRUE);
                }

                if (role.startsWith("FENIX_GERENTE")) {
                    logger.warning("Se recupera rol de FENIX_GERENTE");
                    //se trata de un gerente
                    setEsGerentePaisAdquisiciones(Boolean.TRUE);
                }

                if (role.startsWith("FENIX_GERENTES")) {
                    setEsGerentePais(Boolean.TRUE);
                }

                if (!(role.startsWith("FENIX_GERENTE_GERSYP") ||
                        role.startsWith("FENIX_GERENTE_GERFIN") ||
                        role.startsWith("FENIX_GERENTE_GERIES") ||
                        role.startsWith("FENIX_GERENTE_GERCRED"))) {
                    setEsGerenteRepresentante(Boolean.TRUE);

                    break;
                }

                logger.warning("Valor esGerenteRepresentante: " +
                    esGerenteRepresentante);
            } else if (role.startsWith("FENIX_OFIC")) {
                setEsOfic(Boolean.TRUE);
                logger.warning("Valor esOfic: " + esOfic);
            } else if (role.startsWith("FENIX_PROCESS_CONTROL")) {
                setEsProcessControl(Boolean.TRUE);
                logger.warning("Valor esProcessControl: " + esProcessControl);
            } else if (role.startsWith("FENIX_ANALISTA_SUPERVISION_CREDITO")) {
                setEsAnalistaSupervision(Boolean.TRUE);
                logger.warning("Valor esAnalistaSupervision: " +
                    esAnalistaSupervision);
            } else if (role.startsWith("FENIX_ANALISTA_UCE")) {
                setEsAnalistaUCE(Boolean.TRUE);
                setRolInicio("FENIX_ANALISTA_UCE");
                logger.warning("Valor esAnalistaUCE: " + esAnalistaUCE);
                logger.warning("Valor rolInicio: " + rolInicio);
            } else if (role.startsWith("FENIX_ANALISTA_COFI")) {
                setEsAnalistaCOFI(Boolean.TRUE);
                logger.warning("Valor esAnalistaCOFI: " + esAnalistaCOFI);
            } else if (role.startsWith("FENIX_EJECUTIVO_PCT")) {
                setEsEjecutivoPCT(Boolean.TRUE);
                logger.warning("Valor esEjecutivoPCT: " + esEjecutivoPCT);
            } else if (role.startsWith("FENIX_UNIDAD_ADQUISICIONES")) {
                setEsOficialAdquisiciones(Boolean.TRUE);
                logger.warning("Valor esOficialAdquisiciones: " +
                    esOficialAdquisiciones);
            } else if (role.startsWith("FENIX_GERENTES")) {
                setEsGerentePais(Boolean.TRUE);
            }
        }

        //agregan logs para parametros de adquisiciones respecto al cambio FNXII-7223
        logger.warning("parametros de adquisicionesBTF1");
        logger.warning("esResponsableOperacionAdquisiciones :" +
            this.esResponsableOperacionAdquisiciones);
        logger.warning("esGerentePaisAdquisiciones :" +
            this.esGerentePaisAdquisiciones);
    }

    private Operacion12BndQSService initOperacionService() {
        DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = dcItteratorBindings.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(
                    new Object[] { IWsdlLocation.OPERACION }));
        String wsdl = (row == null) ? null : (String) row.getAttribute("Valor");

        return IWsdlLocation.Service.getInstance(Operacion12BndQSService.class,
            wsdl);
    }

    public void setTrazabilidadFormalizacion(Boolean trazabilidadFormalizacion) {
        this.trazabilidadFormalizacion = trazabilidadFormalizacion;
    }

    public Boolean getTrazabilidadFormalizacion() {
        return trazabilidadFormalizacion;
    }

    public Boolean getValidarProcesoOperacionTerminado(Integer idProceso) {
        logger.warning("Entra a metodo getValidarProcesoOperacionTerminado");

        Boolean validarProcesoTerminado = Boolean.FALSE;
        BindingContainer bindings = getBindings();
        logger.warning(
            "Entra a metodo getValidarProcesoOperacionTerminado paso 1");

        OperationBinding operationBinding = bindings.getOperationBinding(
                "validarProcesoOperacionTerminado");
        operationBinding.getParamsMap().put("idProceso", idProceso);
        logger.warning(
            "Entra a metodo getValidarProcesoOperacionTerminado paso 2");

        Integer respuesta = (Integer) operationBinding.execute();

        if ((null != operationBinding.getResult()) && (respuesta == 1)) {
            logger.warning("Respuesta de OperBinding es TRUE");
            validarProcesoTerminado = Boolean.TRUE;
        } else {
            logger.warning("Respuesta de OperBinding es NULL o FALSE");
        }

        logger.warning(
            "Entra a metodo getValidarProcesoOperacionTerminado paso 3");

        return validarProcesoTerminado;
    }

    public void setIdTipoInstitucion(Integer idTipoInstitucion) {
        this.idTipoInstitucion = idTipoInstitucion;
    }

    public Integer getIdTipoInstitucion() {
        return idTipoInstitucion;
    }

    public void setRazonSocialCliente(String razonSocialCliente) {
        this.razonSocialCliente = razonSocialCliente;
    }

    public String getRazonSocialCliente() {
        return razonSocialCliente;
    }

    public void setAbreviaturaCliente(String abreviaturaCliente) {
        this.abreviaturaCliente = abreviaturaCliente;
    }

    public String getAbreviaturaCliente() {
        return abreviaturaCliente;
    }

    public void setIdTipoPersonaCliente(Integer idTipoPersonaCliente) {
        this.idTipoPersonaCliente = idTipoPersonaCliente;
    }

    public Integer getIdTipoPersonaCliente() {
        return idTipoPersonaCliente;
    }

    public void setIdSectorCliente(Integer idSectorCliente) {
        this.idSectorCliente = idSectorCliente;
    }

    public Integer getIdSectorCliente() {
        return idSectorCliente;
    }

    public void setIdPaisCliente(Integer idPaisCliente) {
        this.idPaisCliente = idPaisCliente;
    }

    public Integer getIdPaisCliente() {
        return idPaisCliente;
    }

    public void setIdGrupoCliente(Integer idGrupoCliente) {
        this.idGrupoCliente = idGrupoCliente;
    }

    public Integer getIdGrupoCliente() {
        return idGrupoCliente;
    }

    public void setNumeroIdentificacionCliente(
        String numeroIdentificacionCliente) {
        this.numeroIdentificacionCliente = numeroIdentificacionCliente;
    }

    public String getNumeroIdentificacionCliente() {
        return numeroIdentificacionCliente;
    }

    public void setIdOficinaCliente(Integer idOficinaCliente) {
        this.idOficinaCliente = idOficinaCliente;
    }

    public Integer getIdOficinaCliente() {
        return idOficinaCliente;
    }

    public void setFilaSeleccionada(Boolean filaSeleccionada) {
        this.filaSeleccionada = filaSeleccionada;
    }

    public Boolean getFilaSeleccionada() {
        return filaSeleccionada;
    }

    public void setValidaCondicion(Boolean validaCondicion) {
        this.validaCondicion = validaCondicion;
    }

    public Boolean getValidaCondicion() {
        return validaCondicion;
    }

    public void setIsLoteImplementacion(Boolean isLoteImplementacion) {
        this.isLoteImplementacion = isLoteImplementacion;
    }

    public void setIdOperacionDesembolsos(Long idOperacionDesembolsos) {
        this.idOperacionDesembolsos = idOperacionDesembolsos;
    }

    public Boolean getIsLoteImplementacion() {
        return isLoteImplementacion;
    }

    public Long getIdOperacionDesembolsos() {
        return idOperacionDesembolsos;
    }

    public void setIdContratoDesembolsos(Long idContratoDesembolsos) {
        this.idContratoDesembolsos = idContratoDesembolsos;
    }

    public Long getIdContratoDesembolsos() {
        return idContratoDesembolsos;
    }

    public void setIdLineaCreditoDesembolsos(Long idLineaCreditoDesembolsos) {
        this.idLineaCreditoDesembolsos = idLineaCreditoDesembolsos;
    }

    public Long getIdLineaCreditoDesembolsos() {
        return idLineaCreditoDesembolsos;
    }

    public void setIdSolicitudDesembolsos(Long idSolicitudDesembolsos) {
        this.idSolicitudDesembolsos = idSolicitudDesembolsos;
    }

    public Long getIdSolicitudDesembolsos() {
        return idSolicitudDesembolsos;
    }

    public void setIdTcaEstadoDesembolsos(Integer idTcaEstadoDesembolsos) {
        this.idTcaEstadoDesembolsos = idTcaEstadoDesembolsos;
    }

    public Integer getIdTcaEstadoDesembolsos() {
        return idTcaEstadoDesembolsos;
    }

    public void setMostrarIniciarProcesoFirmaContrato(
        Boolean mostrarIniciarProcesoFirmaContrato) {
        this.mostrarIniciarProcesoFirmaContrato = mostrarIniciarProcesoFirmaContrato;
    }

    public Boolean getMostrarIniciarProcesoFirmaContrato() {
        return mostrarIniciarProcesoFirmaContrato;
    }

    public void setMostrarSolicitarFormalizacionParcial(
        Boolean mostrarSolicitarFormalizacionParcial) {
        this.mostrarSolicitarFormalizacionParcial = mostrarSolicitarFormalizacionParcial;
    }

    public Boolean getMostrarSolicitarFormalizacionParcial() {
        return mostrarSolicitarFormalizacionParcial;
    }

    public Boolean getEsEjecutivoPCT() {
        return esEjecutivoPCT;
    }

    public void setEsEjecutivoPCT(Boolean esEjecutivoPCT) {
        this.esEjecutivoPCT = esEjecutivoPCT;
    }

    public boolean getEsProductoParaArgumentar() {
        boolean argumentar = Boolean.FALSE;
        int idProductoInt = ((null != getIdProducto()) ? getIdProducto() : 0);

        switch (idProductoInt) {
        case COOPERACION_TECNICA_RECUPERACION_CONTINGENTE:
        case COOPERACION_TECNICA_NO_REEMBOMSABLE:
        case COOPERACION_RECURSOS_EXT_FOPROY:
        case COOPERACION_RECURSOS_EXT_FINAM:
        case COOPERACION_NO_REEMBOMSABLE_FETS:
        case PRE_INVERSION:
            argumentar = Boolean.TRUE;

            break;
        }

        return argumentar;
    }

    public boolean getTieneDesembolso() {
        Boolean contratosOperacion = Boolean.FALSE;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("verificaContratos");
        operationBinding.getParamsMap()
                        .put("idOperacion", Long.toString(getIdOperacion()));
        operationBinding.execute();
        contratosOperacion = ((Boolean) operationBinding.getResult());
        logger.log(ADFLogger.WARNING,
            "getTieneDesembolso: " + contratosOperacion);

        return contratosOperacion;
    }

    public Boolean obtuvoResponsable() {
        Boolean respuesta = Boolean.FALSE;

        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("seObtieneResponsable");
        operationBinding.getParamsMap()
                        .put("operacion", Long.toString(getIdOperacion()));
        operationBinding.execute();
        respuesta = ((Boolean) operationBinding.getResult());
        logger.log(ADFLogger.TRACE, "obtuvo responsable: " + respuesta);

        return respuesta;
    }

    public void visualizarImplementacionPct() {
        logger.warning("Inside visualizarImplementacionPct.");

        Integer formalizacionFinalizado = null;
        Integer aprobacionFinalizado = null;

        setVisualizarImplementacionPct(Boolean.FALSE);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding(
                "validarProcesoOperacionTerminado");
        operationBinding.getParamsMap()
                        .put("idProceso", FenixConstants.PROCESO_FORMALIZACION);
        formalizacionFinalizado = (Integer) operationBinding.execute();

        operationBinding = bindings.getOperationBinding(
                "validarProcesoOperacionTerminado");
        operationBinding.getParamsMap()
                        .put("idProceso", FenixConstants.PROCESO_APROBACION);
        aprobacionFinalizado = (Integer) operationBinding.execute();

        logger.warning("formalizacionFinalizado: " + formalizacionFinalizado);
        logger.warning("aprobacionFinalizado: " + aprobacionFinalizado);

        //Al no tener el Termino Formalizacion Automatica o tenga valor SI, Formalizacion debe estar finalizado.
        if (formalizacionFinalizado != null) {
            if (formalizacionFinalizado.compareTo(1) == 0) {
                logger.warning("Id delproducto :" + getIdProducto());

                if ((getIdProducto()
                             .compareTo(FenixConstants.COOPERACION_TECNICA_RECUPERACION_CONTINGENTE) == 0) ||
                        (getIdProducto()
                                 .compareTo(FenixConstants.COOPERACION_TECNICA_NO_REEMBOMSABLE) == 0) ||
                        (getIdProducto()
                                 .compareTo(FenixConstants.COOPERACION_RECURSOS_EXT_FOPROY) == 0) ||
                        (getIdProducto()
                                 .compareTo(FenixConstants.COOPERACION_RECURSOS_EXT_FINAM) == 0) ||
                        (getIdProducto()
                                 .compareTo(FenixConstants.COOPERACION_NO_REEMBOLSABLE_FETS) == 0)) {
                    logger.warning("Es Formalizacion Automatica.");
                    setVisualizarImplementacionPct(Boolean.TRUE);
                } else {
                    logger.warning(
                        "El idProducto no corresponde a los solicitados.");
                }
            }
        }

        //Al tener el Termino Formalizacion Automatica con valor NO, Aprobacion debe estar finalizado.
        if (!getVisualizarImplementacionPct()) {
            if (aprobacionFinalizado != null) {
                if (aprobacionFinalizado.compareTo(1) == 0) {
                    logger.warning("Id delproducto :" + getIdProducto());

                    if ((getIdProducto()
                                 .compareTo(FenixConstants.COOPERACION_TECNICA_RECUPERACION_CONTINGENTE) == 0) ||
                            (getIdProducto()
                                     .compareTo(FenixConstants.COOPERACION_TECNICA_NO_REEMBOMSABLE) == 0) ||
                            (getIdProducto()
                                     .compareTo(FenixConstants.COOPERACION_RECURSOS_EXT_FOPROY) == 0) ||
                            (getIdProducto()
                                     .compareTo(FenixConstants.COOPERACION_RECURSOS_EXT_FINAM) == 0) ||
                            (getIdProducto()
                                     .compareTo(FenixConstants.COOPERACION_NO_REEMBOLSABLE_FETS) == 0)) {
                        logger.warning("No es Formalizacion Automatica.");
                        setVisualizarImplementacionPct(Boolean.TRUE);
                    } else {
                        logger.warning(
                            "El idProducto no corresponde a los solicitados.");
                    }
                }
            }
        }

        logger.warning("Visualizar implementacion : " +
            getVisualizarImplementacionPct());
    }

    public Boolean formalizacionAutomatica() {
        logger.warning("Inside formalizacionAutomatica.");

        Boolean result = Boolean.FALSE;

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("obtenerValor");
        operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
        result = (Boolean) operationBinding.execute();

        return result;
    }

    public Boolean esFactible() {
        logger.warning("Inside esFactible.");

        String idOperacion = Long.toString(getIdOperacion());
        oracle.jbo.domain.Number idOperacionValue = new oracle.jbo.domain.Number(Long.valueOf(
                    idOperacion));

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding(
                "eligeOperacionFactible");
        operationBinding.getParamsMap().put("idOperacion", idOperacionValue);

        Boolean result = (Boolean) operationBinding.execute();

        logger.warning("eligeOperacionFactible: " + result);

        return result;
    }

    public Boolean confirmaFactibilidad() {
        logger.warning("Inside confirmaFactibilidad.");

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding(
                "confirmaFactibilidad");
        Boolean result = (Boolean) operationBinding.execute();

        return result;
    }

    public void visualizarFactibilidad() {
        logger.warning("Inside visualizarFactibilidad.");

        if (null != getIdProducto()) {
            if ((getIdProducto()
                         .compareTo(FenixConstants.COOPERACION_TECNICA_RECUPERACION_CONTINGENTE) == 0) ||
                    (getIdProducto().compareTo(FenixConstants.PREINVERSION) == 0)) {
                if (!esFactible() && getTieneDesembolso()) {
                    setEsFactible(Boolean.TRUE);
                } else {
                    setEsFactible(Boolean.FALSE);
                }
            } else {
                setEsFactible(Boolean.FALSE);
            }
        } else {
            setEsFactible(Boolean.FALSE);
        }

        logger.warning("esFactible: " + getEsFactible());
    }

    public void visualizarCalculoInteresCobro() {
        logger.warning("Inside visualizarCalculoInteresCobro.");

        if (confirmaFactibilidad()) {
            setConfirmaFactibilidad(Boolean.TRUE);
        } else {
            setConfirmaFactibilidad(Boolean.FALSE);
        }

        logger.warning("esFactible: " + getEsFactible());
    }

    public void modoEnvioGasto() {
        logger.warning("Inside modoEnvioGasto.");

        if (getIdProducto() != null) {
            logger.warning("Compara Id Producto : " + getIdProducto() +
                " con Id de Cooperacion Tecnica Recuperacion Contingente: " +
                FenixConstants.COOPERACION_TECNICA_RECUPERACION_CONTINGENTE);

            if (getIdProducto()
                        .compareTo(FenixConstants.COOPERACION_TECNICA_RECUPERACION_CONTINGENTE) == 0) {
                logger.warning("Es Envio Gasto True");
                setEsEnvioGasto(Boolean.TRUE);
            } else {
                logger.warning("Es Envio Gasto False");
                setEsEnvioGasto(Boolean.FALSE);
            }
        } else {
            logger.warning("Id Producto es NULL");
        }

        logger.warning("Finaliza modoEnvioGasto");
    }

    public void aprobacion() {
        logger.warning("Inside modoEnvioGasto.");

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding(
                "validarProcesoOperacionTerminado");
        operationBinding.getParamsMap()
                        .put("idProceso", FenixConstants.PROCESO_FORMALIZACION);

        Integer result = (Integer) operationBinding.execute();
    }

    public void formalizacionTrazabilidad() {
        logger.warning("Inside formalizacionTrazabilidad.");

        setTrazabilidadFormalizacion(Boolean.FALSE);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding(
                "validarProcesoOperacion");
        Boolean respuesta = (Boolean) operationBinding.execute();

        if (respuesta != null) {
            setTrazabilidadFormalizacion(respuesta);
        } else {
            logger.warning("No se recupero trazabilidadFormalizacion.");
        }

        logger.warning("trazabilidadFormalizacion: " +
            getTrazabilidadFormalizacion());
    }

    public void validarFechaFirmaContrato() {
        logger.warning("Dentro de validarFechaFirmaContrato");

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding(
                "validarTcaTipoTerminoFirmaContrato");
        operationBinding.getParamsMap().put("idOperacion", getIdOperacion());

        Boolean respuesta = (Boolean) operationBinding.execute();
        logger.warning("respuesta :" + respuesta);
        setEsValidaFechaFirmaContrato(respuesta);
        logger.warning("esValidaFechaFirmaContrato :" +
            esValidaFechaFirmaContrato);
        logger.warning("Fuera de validarFechaFirmaContrato");
    }

    public void validaOperacionFormalizada() {
        logger.warning("Entra en validaOperacionFormalizada.");

        Boolean result = Boolean.FALSE;

        try {
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = null;

            operationBinding = bindings.getOperationBinding(
                    "obtenerTcaTerminoFirmaContrato");
            operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
            result = (Boolean) operationBinding.execute();

            setOperacionFormalizada(result);
        } catch (Exception e) {
            logger.warning("Error en validaOperacionFormalizada.", e);
        }

        logger.warning("Formalizacion automatica: " +
            getTccFormalizacionAutomatica());
    }

    public boolean isImplementacionPCTFinalizado() {
        return implementacionPCTFinalizado;
    }

    public void setImplementacionPCTFinalizado(
        boolean implementacionPCTFinalizado) {
        this.implementacionPCTFinalizado = implementacionPCTFinalizado;
    }

    public void setMostrarIniciarCumplimientoDeCondiciones(
        Boolean mostrarIniciarCumplimientoDeCondiciones) {
        this.mostrarIniciarCumplimientoDeCondiciones = mostrarIniciarCumplimientoDeCondiciones;
    }

    public Boolean getMostrarIniciarCumplimientoDeCondiciones() {
        return mostrarIniciarCumplimientoDeCondiciones;
    }

    public Boolean getEsResponsableOperativo() {
        return esResponsableOperativo;
    }

    public void setEsResponsableOperativo(Boolean esResponsableOperativo) {
        this.esResponsableOperativo = esResponsableOperativo;
    }

    public boolean isEsResponsableOperacion() {
        return esResponsableOperacion;
    }

    public void setEsResponsableOperacion(boolean esResponsableOperacion) {
        this.esResponsableOperacion = esResponsableOperacion;
    }

    public void setVisualizarImplementacionPct(
        Boolean visualizarImplementacionPct) {
        this.visualizarImplementacionPct = visualizarImplementacionPct;
    }

    public Boolean getVisualizarImplementacionPct() {
        return visualizarImplementacionPct;
    }

    public StringWriter writeXMLRequest(Object request, Class requestClass)
        throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()),
                requestClass, request), writer);

        return writer;
    }

    public boolean isEsOficialAdquisiciones() {
        return esOficialAdquisiciones;
    }

    public void setEsOficialAdquisiciones(boolean esOficialAdquisiciones) {
        this.esOficialAdquisiciones = esOficialAdquisiciones;
    }

    public CollectionModel getAplicacionesCM() {
        return aplicacionesCM;
    }

    public void setTccFormalizacionAutomatica(
        Boolean tccFormalizacionAutomatica) {
        this.tccFormalizacionAutomatica = tccFormalizacionAutomatica;
    }

    public Boolean getTccFormalizacionAutomatica() {
        return tccFormalizacionAutomatica;
    }

    public void setAplicacionesCM(CollectionModel aplicacionesCM) {
        this.aplicacionesCM = aplicacionesCM;
    }

    public Long getIdEstadoOperacion() {
        return idEstadoOperacion;
    }

    public void setIdEstadoOperacion(Long idEstadoOperacion) {
        this.idEstadoOperacion = idEstadoOperacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoProcesoEvaluaciones() {
        return this.tipoProcesoEvaluaciones;
    }

    public void setTipoProcesoEvaluaciones(String tipoProcesoEvaluaciones) {
        this.tipoProcesoEvaluaciones = tipoProcesoEvaluaciones;
    }

    public String getTipoProcesoEvaluacionesDescripcion() {
        return this.tipoProcesoEvaluacionesDescripcion;
    }

    public void setTipoProcesoEvaluacionesDescripcion(
        String tipoProcesoEvaluacionesDescripcion) {
        this.tipoProcesoEvaluacionesDescripcion = tipoProcesoEvaluacionesDescripcion;
    }

    public boolean isEsGerentePais() {
        return esGerentePais;
    }

    public void setEsGerentePais(boolean esGerentePais) {
        this.esGerentePais = esGerentePais;
    }

    public boolean isMostrarFuncionReasignarResponsable() {
        return mostrarFuncionReasignarResponsable;
    }

    public void setMostrarFuncionReasignarResponsable(
        boolean mostrarFuncionReasignarResponsable) {
        this.mostrarFuncionReasignarResponsable = mostrarFuncionReasignarResponsable;
    }

    public List<SelectItem> getListaResponsablesSI() {
        return listaResponsablesSI;
    }

    public void setListaResponsablesSI(List<SelectItem> listaResponsablesSI) {
        this.listaResponsablesSI = listaResponsablesSI;
    }

    public String getReasignarA() {
        return reasignarA;
    }

    public void setReasignarA(String reasignarA) {
        this.reasignarA = reasignarA;
    }

    public void setProcesoAprobacionFinalizado(
        Boolean procesoAprobacionFinalizado) {
        this.procesoAprobacionFinalizado = procesoAprobacionFinalizado;
    }

    public Boolean getProcesoAprobacionFinalizado() {
        return procesoAprobacionFinalizado;
    }

    public void setOperacionFormalizada(Boolean operacionFormalizada) {
        this.operacionFormalizada = operacionFormalizada;
    }

    public Boolean getOperacionFormalizada() {
        return operacionFormalizada;
    }

    public void verificaFormalizacionAutomatica() {
        logger.warning("Inside verificaFormalizacionAutomatica.");

        Boolean result = Boolean.TRUE;
        setTccFormalizacionAutomatica(result);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("obtenerValor");
        operationBinding.getParamsMap().put("idOperacion", getIdOperacion());
        result = (Boolean) operationBinding.execute();

        setTccFormalizacionAutomatica(result);

        logger.warning("Formalizacion automatica: " +
            getTccFormalizacionAutomatica());
    }

    public void verificaAprobacion() {
        logger.warning("Inside verificaAprobacion.");

        Integer result = 0;
        setProcesoAprobacionFinalizado(Boolean.FALSE);

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding(
                "validarProcesoOperacionTerminado");
        operationBinding.getParamsMap()
                        .put("idProceso", FenixConstants.PROCESO_APROBACION);
        result = (Integer) operationBinding.execute();

        if (result.compareTo(1) == 0) {
            setProcesoAprobacionFinalizado(Boolean.TRUE);
        }

        logger.warning("Aprobacion finalizado: " +
            getProcesoAprobacionFinalizado());
    }

    public void validarEsVisibleEstadoCondiciones() {
        logger.warning("Dentro validarEsVisibleEstadoCondiciones");
        logger.warning("idOperacion :" + this.getIdOperacion());

        Boolean result = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;

        try {
            operationBinding = bindings.getOperationBinding(
                    "validarEstadoCondicionesPorIdOperacion");
            operationBinding.getParamsMap()
                            .put("idOperacion", this.getIdOperacion());
            operationBinding.getParamsMap()
                            .put("idCatProducto", this.getIdProducto());
            result = (Boolean) operationBinding.execute();
            setEsVisibleEstadoCondiciones(result);
        } catch (Exception e) {
            logger.severe("Error en validarEsVisibleEstadoCondiciones :" + e);
        }

        logger.warning("esVisibleEstadoCondiciones :" +
            getEsVisibleEstadoCondiciones());
        logger.warning("Fuera validarEsVisibleEstadoCondiciones");
    }

    public void validarEsAprobacionFinalizado() {
        logger.warning("Dentro validarEsAprobacionFinalizado");
        logger.warning("idOperacion :" + this.getIdOperacion());

        Boolean result = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;

        try {
            operationBinding = bindings.getOperationBinding(
                    "esProcesoFinalizado");
            operationBinding.getParamsMap()
                            .put("idOperacion", this.getIdOperacion());
            operationBinding.getParamsMap().put("idProceso", new Integer("4"));
            result = (Boolean) operationBinding.execute();
            setEsAprobacionFinalizado(result);
        } catch (Exception e) {
            logger.severe("Error en validarEsAprobacionFinalizado :" + e);
        }

        if (!getEsAprobacionFinalizado()) {
            logger.warning(
                "validacion esProcesoFinalizado en tbi proceso operacion no cumplida");
            logger.warning("validando en vta_trazabilidad_operacion...");

            if (procesoAprobacionTerminadoEnTrazabilidad) {
                logger.warning(
                    "ok Fecha fin de aprobacion encontrada en vta_trazabilidad_operacion");
                setEsAprobacionFinalizado(procesoAprobacionTerminadoEnTrazabilidad);
            } else {
                logger.warning(
                    "Fecha fin de aprobacion no encontrada en vta_trazabilidad_operacion");
            }
        }

        logger.warning("estatus validacion esAprobacionFinalizado :" +
            getEsAprobacionFinalizado());
        logger.warning("Fuera validarEsAprobacionFinalizado");
    }

    /**
     * metodo para validar si el proceso de Recuperacion UCE se encuentra finalizado
     * se implementa para determinar si se muestra la opcion de "Iniciar Cierre de operación"
     * asigna valor a la variable esRecuperacionFinalizado, por defecto el valor es FALSE
     */
    public void validarEsRecuperacionFinalizado() {
        logger.warning("Dentro validarEsRecuperacionFinalizado");
        logger.warning("idCliente :" + this.getIdCliente());
        logger.warning("idProcesoBpm :" + FenixConstants.PROCESO_UCE);

        //declaracion de variables
        Boolean result = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;

        //obtener si ocurre un error en el modelo
        try {
            //asignar nombre del metodo "esProcesoClienteFinalizado" que se encuentra implementado en 
            //"ProcesoClienteVOImpl"
            operationBinding = bindings.getOperationBinding(
                    "esProcesoClienteFinalizado");
            //asignar valores para el metodo "esProcesoFinalizado"
            //identificador unico de cliente
            operationBinding.getParamsMap().put("idCliente", this.getIdCliente());
            //identificador unico de proceso (Se toma de constantes ya declaradas en ViewController generico)
            operationBinding.getParamsMap()
                            .put("idProceso", FenixConstants.PROCESO_UCE);
            //ejecutar el metodo y obtener el resultado 
            result = (Boolean) operationBinding.execute();
            //asignar el valor obtenido a la variable "esRecuperacionFinalizado"
            this.setEsRecuperacionFinalizado(result);
        } catch (Exception e) {
            //pintar error severe ya que el modelo obtuvo un error
            logger.severe("Error en validarEsRecuperacionFinalizado :" + e);
        }

        //mostrar valor de "esRecuperacionFinalizado"
        logger.warning("esRecuperacionFinalizado :" +
            this.getEsRecuperacionFinalizado());
        logger.warning("Fuera validarEsRecuperacionFinalizado");
    }

    public Boolean getEsVisibleEstadoCondiciones() {
        return esVisibleEstadoCondiciones;
    }

    public void setEsVisibleEstadoCondiciones(
        Boolean esVisibleEstadoCondiciones) {
        this.esVisibleEstadoCondiciones = esVisibleEstadoCondiciones;
    }

    public Boolean getEsGerentePaisAdquisiciones() {
        return esGerentePaisAdquisiciones;
    }

    public void setEsGerentePaisAdquisiciones(
        Boolean esGerentePaisAdquisiciones) {
        this.esGerentePaisAdquisiciones = esGerentePaisAdquisiciones;
    }

    public Boolean getEsResponsableOperacionAdquisiciones() {
        return esResponsableOperacionAdquisiciones;
    }

    public void setEsResponsableOperacionAdquisiciones(
        Boolean esResponsableOperacionAdquisiciones) {
        this.esResponsableOperacionAdquisiciones = esResponsableOperacionAdquisiciones;
    }

    public Boolean getEsValidaFechaFirmaContrato() {
        return esValidaFechaFirmaContrato;
    }

    public void setEsValidaFechaFirmaContrato(
        Boolean esValidaFechaFirmaContrato) {
        this.esValidaFechaFirmaContrato = esValidaFechaFirmaContrato;
    }

    public void setEsAprobacionFinalizado(Boolean esAprobacionFinalizado) {
        this.esAprobacionFinalizado = esAprobacionFinalizado;
    }

    public Boolean getEsAprobacionFinalizado() {
        return esAprobacionFinalizado;
    }

    public Boolean getEsRecuperacionFinalizado() {
        return esRecuperacionFinalizado;
    }

    public void setEsRecuperacionFinalizado(Boolean esRecuperacionFinalizado) {
        this.esRecuperacionFinalizado = esRecuperacionFinalizado;
    }


    public void setEsVisibleLineaCredito(Boolean esVisibleLineaCredito) {
        this.esVisibleLineaCredito = esVisibleLineaCredito;
    }

    public Boolean getEsVisibleLineaCredito() {
        return esVisibleLineaCredito;
    }
    
    public void validarEsVisibleLineaCredito() {
        logger.warning("Dentro validarEsVisibleLineaCredito");
        logger.warning("idOperacion :"+this.getIdOperacion());
        Boolean result = null;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = null;
        try{
            operationBinding = bindings.getOperationBinding("isLineaCredito");
            operationBinding.getParamsMap().put("idOperacion",this.getIdOperacion());
            result = (Boolean) operationBinding.execute();
            setEsVisibleLineaCredito(result);
        }catch(Exception e){
            logger.severe("Error en validarEsVisibleLineaCredito :"+e);
        }
        logger.warning("esVisibleLineaCredito :"+getEsVisibleLineaCredito());
        logger.warning("Fuera validarEsVisibleLineaCredito");
    }
}
