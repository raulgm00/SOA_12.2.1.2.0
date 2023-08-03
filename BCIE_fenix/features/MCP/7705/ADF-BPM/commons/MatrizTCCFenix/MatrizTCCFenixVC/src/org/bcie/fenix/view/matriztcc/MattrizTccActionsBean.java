package org.bcie.fenix.view.matriztcc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.net.URLEncoder;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichQuery;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.layout.RichToolbar;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.model.QueryDescriptor;
import oracle.adf.view.rich.model.QueryModel;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Array;
import oracle.jbo.domain.Number;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.component.UIXTree;
import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.DisclosureEvent;
import org.apache.myfaces.trinidad.event.RowDisclosureEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.ModelUtils;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.apache.myfaces.trinidad.model.RowKeySetTreeImpl;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import org.bcie.atributobo.Accion;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.matriztccmo.CrearReporteTCCRequestType;
import org.bcie.matriztccmo.CrearReporteTCCResponseType;
import org.bcie.matriztccservice.MatrizTCC12BndQSService;
import org.bcie.matriztccservice.MatrizTCCPT;


public class MattrizTccActionsBean extends FenixActionBean {
    
    /**
     * Define ubicacion y nombre del archivo properties de resource bundle
     */
    public static final String BUNDLE = "org.bcie.fenix.view.MatrizTCCFenixVCBundle";
    
    /**
     * Define nombre de operator binding para inicializar la pantalla de Matriz TCC modalidad Grid
     */
    public static final String INI_MATRIZ_TCC_GRID_OPER = "iniciaMatrizTccGrid";
    
    /**
     * Define nombre del operator binding para buscar el proceso BPM por Id de Tarea
     */
    public static final String BUSCAR_PROCESO_BPM_POR_TAREA_OPER = "buscarProcesoPorIdTareaBpm";
    
    /**
     * Define Id de Modalidad Tree
     */
    public static final Integer ID_MODALIDAD_TREE = 1;
    
    /**
     * Define Id de Modalidad Grid
     */
    public static final Integer ID_MODALIDAD_GRID = 2;
    
    /**
     * Define Id de tipo gestion para ver un termino
     */
    public static final Integer ID_GESTION_TIPO_VER_TERMINO = 1;
    
    /**
     * Define Id de tipo gestion para editar un termino
     */
    public static final Integer ID_GESTION_TIPO_EDITAR_TERMINO = 2;
    
    /**
     * Define Id del tipo gestion para ver una condicion
     */
    public static final Integer ID_GESTION_TIPO_VER_CONDICION = 3;
    
    /**
     * Define Id del tipo gestion para editar una condicion
     */
    public static final Integer ID_GESTION_TIPO_EDITAR_CONDICION = 4;
    
    /**
     * Define Id del tipo gestion para ver una comision
     */
    public static final Integer ID_GESTION_TIPO_VER_COMISION = 5;
    
    /**
     * Define Id del tipo gestion para editar una comision
     */
    public static final Integer ID_GESTION_TIPO_EDITAR_COMISION = 6;
    
    /**
     * Define Id del proceso BPM de Preparacion
     */
    public static final Integer ID_PROCESO_PREPARACION_BPM = 2;
    
    /**
     * Define Id del proceso BPM de Analisis
     */
    public static final Integer ID_PROCESO_ANALISIS_BPM = 3;
    
    /**
     * Define Id del proceso BPM de Aprobacion
     */
    public static final Integer ID_PROCESO_APROBACION_BPM = 4;
    
    /**
     * Define Id del proceso BPM de Enmiendas
     */
    public static final Integer ID_PROCESO_EVALUACION_BPM = 6;
    
    /**
     * Define Id del proceso BPM de Enmiendas
     */
    public static final Integer ID_PROCESO_EVALUACION_SIEMAS_BPM = 31;
    
    /**
     * Define Id del proceso BPM de Enmiendas
     */
    public static final Integer ID_PROCESO_ENMIENDAS_BPM = 10;
    
    /**
     * Define Id del proceso BPM de Administracion de Condiciones
     */
    public static final Integer ID_PROCESO_ADMON_CONDICIONES_BPM = 9;
    
    /**
     * Define Id de tarea de Evaluacion
     */
    public static final Integer ID_TAREA_ACTUALIZAR_CONDICIONES_SIEMAS = 178;
    
    /**
     * Define Id de tarea de Gestor de Operaciones
     */
    public static final Integer ID_TAREA_GESTOR_OPERACIONES = 65;
    
    /**
     * Define Id del proceso BPM de Formalizacion
     */
    public static final Integer ID_PROCESO_FORMALIZACION_BPM = 5;
    
    public static final Integer ID_ESTADO_TCC_SUGERIDA = 1;
    
    public static final Integer ID_ESTADO_TCC_NUEVA = 7;
    
    public static final Integer ID_ESTADO_TCC_MANDATORIA = 8;
    
    public static final Integer ID_ESTADO_TCC_EDITADA = 9;
    
    public static final Integer ID_ESTADO_TCC_ELIMINADA_1 = 10;
    
    public static final Integer ID_ESTADO_TCC_MANDATORIA_EDITADA = 11;
    
    public static final Integer ID_ESTADO_TCC_EXCEPTUADA = 12;
    
    public static final Integer ID_ESTADO_TCC_POR_APROBAR = 13;
    
    public static final Integer ID_ESTADO_TCC_APROBADA = 14;
    
    public static final Integer ID_ESTADO_TCC_FORMALIZADA = 15;
    
    public static final Integer ID_ESTADO_TCC_NUEVA_POR_APROBAR = 16;
    
    public static final Integer ID_ESTADO_TCC_POR_DISPENSAR = 17;
    
    public static final Integer ID_ESTADO_TCC_POR_MODIFICAR = 18;
    
    public static final Integer ID_ESTADO_TCC_POR_ELIMINAR = 19;
    
    public static final Integer ID_ESTADO_TCC_ENMIENDA_CANCELADA = 20;
    
    public static final Integer ID_ESTADO_TCC_POR_VALIDAR_1 = 21;
    
    public static final Integer ID_ESTADO_TCC_ELIMINADA_0 = 22;
    
    public static final Integer ID_ESTADO_TCC_POR_VALIDAR_0 = 23;
    
    public static final Integer ID_ESTADO_TCC_CON_EVIDENCIA = 24;
    
    public static final Integer ID_ESTADO_TCC_VALIDA_PARCIALMENTE = 25;
    
    public static final Integer ID_ESTADO_TCC_VALIDADA_0 = 26;
    
    public static final Integer ID_ESTADO_TCC_APROBADA_EDITADA = 27;
    
    public static final Integer ID_ESTADO_TCC_VALIDADA_1 = 28;
    
    public static final Integer ID_ESTADO_TCC_INACTIVA_0 = 30;
    
    public static final Integer ID_ESTADO_TCC_INACTIVA_1 = 31;
    
    public static final Integer ID_ESTADO_TCC_ACTUALIZADA_0 = 32;
    
    /**
     * Define el ID de TCA Control Condicion para Evento
     */
    public static final Integer ID_CONTROL_CONDICION_EVENTO = 1;
    
    /**
     * Define el ID de TCA Control Condicion para Calendario
     */
    public static final Integer ID_CONTROL_CONDICION_CALENDARIO = 2;
    
    /**
     * Define el ID de Tipo Fecha de Inicio para Fecha Especifica
     */
    public static final Integer ID_TIPO_FECHA_INICIO_FECHA_ESPECIFICA = 7;
    
    /**
     * Define si el tipo frecuencia en comision es al VENCIMIENTO
     */
    public static final Integer TIPO_VENCIMIENTO = 4;
    
    /**
     * Define si el tipo VALOR  en comision es el MONTO
     * */
    public static final Integer TIPO_VALOR_MONTO = 1;
    
    /**
     * Definen los tipos de Termino
     * */
    public static final String TERMINO_TIPO_PLAZO="TERMINO_TIPO_PLAZO";
    public static final String TERMINO_TIPO_MONTO="TERMINO_TIPO_MONTO";
    public static final String TERMINO_TIPO_CONDICIONES="TERMINO_TIPO_CONDICIONES";
    public static final String TERMINO_TIPO_CONTRAPARTES="TERMINO_TIPO_CONTRAPARTES";
    public static final String TERMINO_TIPO_RESTRICCIONES="TERMINO_TIPO_RESTRICCIONES";
    public static final String TERMINO_TIPO_FECHA="TERMINO_TIPO_FECHA";
    public static final String TERMINO_TIPO_OTRO="TERMINO_TIPO_OTRO";
    
    /**
     * Valores para validaciones de Termino
     */
    public static final String TERMINO_ID_TIPO_FECHA_INICIO_ESPECIFICA="TERMINO_ID_TIPO_FECHA_INICIO_ESPECIFICA";
    
    /**
     * Define Id del proceso BPM de Formalizacion de Enmiendas
     */
    public static final Integer ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM = 36;

    /**
     * Valores para visibles Termino
     */
    private Boolean mostrarNombre;
    private Boolean mostrarDescripcion;
    private Boolean mostrarFechaInicio;
    private Boolean mostrarTipoFechaInicio;
    private Boolean mostrarFechaVencimiento;
    private Boolean mostrarPlazo;
    private Boolean mostrarTipoPlazo;
    private Boolean mostrarMoneda;
    private Boolean mostrarMonto;
    private Boolean mostrartasa;
    private Boolean mostrarTipoTasa;
    private Boolean mostrarFrecuenciaRevision;
    private Boolean mostrarTipoFrecRevision;
    private Boolean mostrarFechaInicioRevision;
    private Boolean mostrarFrecPagoInteres;
    private Boolean mostrarTipoFrecPagoInteres;
    private Boolean mostrarFechaInicioPagoInteres;
    private Boolean mostrarFrecAmortizacion;
    private Boolean mostrarTipoFrecAmortizacion;
    private Boolean mostrarMora;
    private Boolean mostrarCobertura;
    private Boolean mostrarConcesionales;
    private Boolean mostrarExternos;    
    private Boolean mostrarNombreContraparte;
    private Boolean mostrarTipoContraparte;
    private Boolean mostrarMontoMinimo;
    private Boolean mostrarMontoMaximo;
    private Boolean mostrarTasaMinima;
    private Boolean mostrarTasaMaxima;
    private Boolean mostrarFecha;
    
    /**
     * Valores para labels Termino
     */
    private String lblNombre;
    private String lblDescripcion;
    private String lblFechaInicioPlazo;
    private String lblTipoFechaInicioPlazo;
    private String lblplazo;
    private String lblTipoPlazo;
    private String lblFechaVencimientoPlazo;
    private String lblMoneda;
    private String lblMonto;
    private String lblTasa;
    private String lblTipoTasa;
    private String lblFrecuenciaRevision;
    private String lblTipoFrecRevision;
    private String lblFechaInicioRevision;
    private String lblFrecPagoInteres;
    private String lblTipoFrecPagoInteres;
    private String lblFechaInicioPagoInteres;
    private String lblFrecAmortizacion;
    private String lblTipoFrecAmortizacion;
    private String lblMora;
    private String lblCobertura;
    private String lblConcesionales;
    private String lblExternos;
    private String lblNombreContraparte;
    private String lblTipoContraparte;
    private String lblMontoMinimo;
    private String lblMontoMaximo;
    private String lblTasaMinima;
    private String lblTasaMaxima;
    private String lblFecha;
    
    /**
     * Valores para required Termino
     */
    private Boolean reqNombre;
    private Boolean reqDescripcion;
    private Boolean reqFechaInicio;
    private Boolean reqTipoFechaInicio;
    private Boolean reqFechaVencimiento;
    private Boolean reqPlazo;
    private Boolean reqTipoPlazo;
    private Boolean reqMoneda;
    private Boolean reqMonto;
    private Boolean reqtasa;
    private Boolean reqTipoTasa;
    private Boolean reqFrecuenciaRevision;
    private Boolean reqTipoFrecRevision;
    private Boolean reqFechaInicioRevision;
    private Boolean reqFrecPagoInteres;
    private Boolean reqTipoFrecPagoInteres;
    private Boolean reqFechaInicioPagoInteres;
    private Boolean reqFrecAmortizacion;
    private Boolean reqTipoFrecAmortizacion;
    private Boolean reqMora;
    private Boolean reqCobertura;
    private Boolean reqConcesionales;
    private Boolean reqExternos;    
    private Boolean reqNombreContraparte;
    private Boolean reqTipoContraparte;
    private Boolean reqMontoMinimo;
    private Boolean reqMontoMaximo;
    private Boolean reqTasaMinima;
    private Boolean reqTasaMaxima;
    private Boolean reqFecha;
    
    private Timestamp fechaInicioOrigen;
    private Integer idFechaInicioOrigen;
    
    /**
     * Valor para renderizar calendario de campo TipoFechaInicio para TERMINO
     */
    private Boolean esFechaEspecificaTermino;

    /**
     * Log de la aplicacion
     */
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(MattrizTccActionsBean.class);
    
    /**
     * Binding de la region para gestionar el elemento de TCC
     */
    private RichRegion regionGestionTccUIC;
    
    /**
     * Define el id del tipo de gestion a realizar en la TCC
     */
    private Integer idTipoGestion;
    
    /**
     * Define el id del elemento TCC
     */
    private Number idTcc;
    
    /**
     * Define valor bandera para la propiedad rendered o visible del boton Editar
     */
    private boolean btnEditarVisible;
    
    /**
     * Define valor bandera para la propiedad rendered o visible del boton Eliminar
     */
    private boolean btnEliminarVisible;
    
    /**
     * Define valor bandera para la propiedad rendered o visible del boton Exceptuar
     */
    private boolean btnExceptuarVisible;
    
    /**
     * Define valor bandera para la propiedad rendered o visible del boton Dispensar
     */
    private boolean btnDispensarVisible;
    
    /**
     * Define valor bandera para la propiedad rendered o visible del boton de Validar
     */
    private boolean btnValidarVisible;
    
    /**
     * Define valor bandera para la propiedad rendered o visible del boton de Eliminar validacion
     */
    private boolean btnEliminarValidacionVisible;
    
    /**
     * Define valor bandera para la propiedad rendered o visible del boton de Por validar o Lista para validar
     */
    private boolean btnPorValidarVisible;
    
    /**
     * Define valor bandera para la propiedad rendered o visible del boton de Por Eliminar
     */
    private boolean btnPorEliminarVisible;

    /**
     * Define valor bandera para la propiedad rendered o visible del boton Agregar
     */
    private boolean btnAgregarVisible;
    
    /**
     * Define el valor bandera para la propiedad rendered o visible del boton Borrar del Grid
     */
    private boolean btnBorrarGridVisible;
    
    /**
     * Define el valor bandera para la propiedad rendered o visible del boton Borrar del Grid
     */
    private boolean btnReactivarVisible;

    

    /**
     * Define el valor bandera para la propiedad rendered o visible del boton Borrar del Grid
     */
    private boolean btnDesactivarVisible;

    private boolean esEventoCondicion;
    
    private boolean esCalendarioCondicion;
    
    private boolean esFechaEspecificaCondicion;

    private RichSelectManyChoice categoriaCondicionUIC;

    private RichSelectManyChoice eventosCondicionUIC;
    
    private RichPanelFormLayout formEditCondicionUIC;
    
    private RichPanelGroupLayout panelRegionGestionUIC;
    
    // Variables para el tree
    private RichOutputText otInitForm;
    private RichInputDate fechaInicioUIC; // Inicializa los hijos del tree filtrados por el pIdOperacion
    
    // Variables para funcionalidad de botones Seleccionar / Agregar
    private RichPopup popupAgregarSeleccionar;
    private RichPopup popupAgregarSeleccionarGrid;
    private RichPanelGroupLayout panelMatrizTccGridUIC;
    private RichInputDate fechaVencimientoTerminoUIC;
    private RichShowDetailItem tabTerminos;
    private RichShowDetailItem tabCondiciones;
    private RichShowDetailItem tabComisiones;
    private RichTable tccTablaUIC;
    private RichPopup popupAsociarContrapartes;
    private RichQuery queryBuscarCliente;
    private RichPopup popupConfirmarDesasociarContrapartes;
    
    private RichOutputText otInitFormContrapartes;
    private String consultarContrapartes;
    private RichToolbar toolbarAccionesUIC;
    private RichToolbar toolbarAccionesGridUIC;
    
    private boolean esFrecuenciaAlVencimiento = Boolean.FALSE;
    private RichPopup popUpCrearReporteTCC;
    
    /*
     * Agrega variable para file del reporte de TCC
     * */
    
    private final String REPORTE_FILE_NAME = "ReporteTcc.pdf";
    
    // Client Ids para los botones de refresh. Se pasan como parámetro al flujo de gestión. El cual los necesita 
    // para invocar el javascript de refresh.
    private String clientIdBtnRefreshTccTree;
    private RichPanelGroupLayout panelButtonGrid;
    private RichTree tccTreeUIC;
    private RichButton btnRefreshTccTree;
    private RichPanelFormLayout formFechasCondUIC;
    private RichPanelGroupLayout panelGroupTipoCalendarioCondUIC;
    private RichPanelGroupLayout panelGroupTipoEventoCondUIC;
    private RichPanelHeader panelHeaderCondicionUIC;
    private RichPanelGroupLayout actualizarTerminoFormUI;
    private RichSelectOneChoice socIdTcaTipoDesembolsoUIC;
    private RichPanelGroupLayout pglTerminoInicioUIC;
    
    //Ver si el termino habilita el boton de modificar segun la columna ES_EDITABLE_EN_FORMALIZACION  de la
    //tabla TCA_TERMINO
    private boolean editableEnFormalizacion;


    /**
     * Constructor por defecto
     */
    public MattrizTccActionsBean() {
        super();
    }
    
    public void setRegionGestionTccUIC(RichRegion regionGestionTccUIC) {
        this.regionGestionTccUIC = regionGestionTccUIC;
    }

    public RichRegion getRegionGestionTccUIC() {
        return regionGestionTccUIC;
    }
    
    public void setIdTipoGestion(Integer idTipoGestion) {
        this.idTipoGestion = idTipoGestion;
    }

    public Integer getIdTipoGestion() {
        
        if(idTipoGestion == null){
            leeAsignaIdTipoGestionTcc();
        }
        
        try{
            idTipoGestion = (Integer) ADFUtils.getBoundAttributeValue("IdTipoGestionTccAttrValue");
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Tipo Gestion TCC de bindings", e);
        }        
        
        return idTipoGestion;
    }
    
    public void setIdTcc(Number idTcc) {
        this.idTcc = idTcc;
    }

    public Number getIdTcc() {
        
        if(idTcc == null){
            leeAsignaIdTccActivo();
        }
        
        try{
            idTcc = (Number) ADFUtils.getBoundAttributeValue("IdTccAttrValue");
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id TCC de bindings", e);
        }
            
        return idTcc;
    }
    
    public void setBtnEditarVisible(boolean btnEditarVisible) {
        this.btnEditarVisible = btnEditarVisible;
    }

    public boolean isBtnEditarVisible() {
        btnEditarVisible = validaAccionTCC(1);
        return btnEditarVisible;
    }

    public void setBtnEliminarVisible(boolean btnEliminarVisible) {
        this.btnEliminarVisible = btnEliminarVisible;
    }

    public boolean isBtnEliminarVisible() {
        btnEliminarVisible = validaAccionTCC(2);
        return btnEliminarVisible;
    }

    public void setBtnExceptuarVisible(boolean btnExceptuarVisible) {
        this.btnExceptuarVisible = btnExceptuarVisible;
    }

    public boolean isBtnExceptuarVisible() {
        btnExceptuarVisible = validaAccionTCC(3);
        return btnExceptuarVisible;
    }

    public void setBtnDispensarVisible(boolean btnDispensarVisible) {
        this.btnDispensarVisible = btnDispensarVisible;
    }

    public boolean isBtnDispensarVisible() {
        btnDispensarVisible = validaAccionTCC(4);
        return btnDispensarVisible;
    }

    public void setBtnValidarVisible(boolean btnValidarVisible) {
        this.btnValidarVisible = btnValidarVisible;
    }

    public boolean isBtnValidarVisible() {
        btnValidarVisible = validaAccionTCC(5);
        return btnValidarVisible;
    }

    public void setBtnEliminarValidacionVisible(boolean btnEliminarValidacionVisible) {
        this.btnEliminarValidacionVisible = btnEliminarValidacionVisible;
    }

    public boolean isBtnEliminarValidacionVisible() {
        btnEliminarValidacionVisible = validaAccionTCC(6);
        return btnEliminarValidacionVisible;
    }
    
    public void setBtnPorValidarVisible(boolean btnPorValidarVisible) {
        this.btnPorValidarVisible = btnPorValidarVisible;
    }

    public boolean isBtnPorValidarVisible() {
        btnPorValidarVisible = validaAccionTCC(7);
        return btnPorValidarVisible;
    }
    
    public void setBtnPorEliminarVisible(boolean btnPorEliminarVisible) {
        this.btnPorEliminarVisible = btnPorEliminarVisible;
    }

    public boolean isBtnPorEliminarVisible() {
        btnPorEliminarVisible = validaAccionTCC(8);
        return btnPorEliminarVisible;
    }

    public void setBtnAgregarVisible(boolean btnAgregarVisible) {
        this.btnAgregarVisible = btnAgregarVisible;
    }

    public boolean isBtnAgregarVisible() {
        btnAgregarVisible = validaAccionTCC(9);
        return btnAgregarVisible;
    }
    
    public void setBtnBorrarGridVisible(boolean btnBorrarGridVisible) {
        this.btnBorrarGridVisible = btnBorrarGridVisible;
    }
    
    public void setBtnReactivarVisible(boolean btnReactivarVisible) {
        this.btnReactivarVisible = btnReactivarVisible;
    }

    public boolean isBtnReactivarVisible() {
        btnReactivarVisible = validaAccionTCC(10);
        return btnReactivarVisible;
    }

    public void setBtnDesactivarVisible(boolean btnDesactivarVisible) {
        this.btnDesactivarVisible = btnDesactivarVisible;
    }

    public boolean isBtnDesactivarVisible() {
        btnDesactivarVisible = validaAccionTCC(11);
        return btnDesactivarVisible;
    }

    public boolean isBtnBorrarGridVisible() {
        
        //Obtiene el Id de Proceso BPM
        Integer idProcesoBpm = null;
        try{
            idProcesoBpm = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm"); //Definicion Obligatorio en todas las pantallas
        }catch(Exception e){
            LOGGER.severe("Error al obtener el valor del Atributo Id Proceso BPM de Bindings", e);
        }
        
        if(idProcesoBpm != null){
            
            if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                
                Number idEstadoTcc = null;
                try{
                    idEstadoTcc = (Number) ADFUtils.getBoundAttributeValue("IdTcaEstadoTccAttrValue");
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el Id de Estado TCC para la validacion del boton Borrar Elemento Grid");
                }
                
                if(idEstadoTcc != null){
                    if(ID_ESTADO_TCC_NUEVA.equals(new Integer(idEstadoTcc.intValue()))){
                        //El boton borrar elemento en el grid no se debe visualizar cuando el Estado TCC es Nuevo
                        btnBorrarGridVisible = false;
                    }else{
                        //El boton borrar elemento en el grid se debe mostrar para todos los Estados TCC diferente a Nuevo
                        btnBorrarGridVisible = true;    
                    }
                }else{
                    btnBorrarGridVisible = false;    
                }
                
            } else if (ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)) {
                LOGGER.info("(Formalizacion de Enmiendas) btnBorrarGridVisible = true;");
                btnBorrarGridVisible = true;
            } else {
                btnBorrarGridVisible = false;
            }
        }else{
            btnBorrarGridVisible = false;
        }

        return btnBorrarGridVisible;
    }
    
    public void setEsEventoCondicion(boolean esEventoCondicion) {
        this.esEventoCondicion = esEventoCondicion;
    }

    public boolean isEsEventoCondicion() {
        
        Integer idControlCondicion = null;
        try{
            idControlCondicion = (Integer) ADFUtils.getBoundAttributeValue("IdTcaControlCondicion");    
        }catch(Exception e){
            LOGGER.severe("No se pudo obtener definicion de Atributo Id Tca Control Condicion", e);
        }
        
        if(idControlCondicion != null){
            
            if(ID_CONTROL_CONDICION_EVENTO.equals(idControlCondicion)){
                esEventoCondicion = true;
            }else{
                esEventoCondicion = false;
            }
        }
        LOGGER.warning("Es Tipo de Control Evento: " + esEventoCondicion);
        return esEventoCondicion;
    }
    

    public void setEsCalendarioCondicion(boolean esCalendarioCondicion) {
        this.esCalendarioCondicion = esCalendarioCondicion;
    }

    public boolean isEsCalendarioCondicion() {
        
        Integer idControlCondicion = null;
        try{
            idControlCondicion = (Integer) ADFUtils.getBoundAttributeValue("IdTcaControlCondicion");    
        }catch(Exception e){
            LOGGER.severe("No se pudo obtener definicion de Atributo Id Tca Control Condicion", e);
        }
        
        if(idControlCondicion != null){
            
            if(ID_CONTROL_CONDICION_CALENDARIO.equals(idControlCondicion)){
                esCalendarioCondicion = true;
            }else{
                esCalendarioCondicion = false;
            }
        }
        LOGGER.warning("Es Tipo de Control Calendario: " + esCalendarioCondicion);
        return esCalendarioCondicion;
    }
    
    public void setEsFechaEspecificaCondicion(boolean esFechaEspecificaCondicion) {
        this.esFechaEspecificaCondicion = esFechaEspecificaCondicion;
    }

    public boolean isEsFechaEspecificaCondicion() {
        
        Integer idTipoFechaInicio = null;
        try{
            idTipoFechaInicio = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoFechaInicio");    
        }catch(Exception e){
            LOGGER.severe("No se pudo obtener definicion de Atributo Id Tca Tipo Fecha Inicio Condicion", e);
        }
        
        if(idTipoFechaInicio != null){
            
            if(ID_TIPO_FECHA_INICIO_FECHA_ESPECIFICA.equals(idTipoFechaInicio)){
                esFechaEspecificaCondicion = true;
            }else{
                esFechaEspecificaCondicion = false;
            }
        }
        LOGGER.warning("Es Fecha Especifica: " + esFechaEspecificaCondicion);
        return esFechaEspecificaCondicion;
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
    
    public void setFormEditCondicionUIC(RichPanelFormLayout formEditCondicionUIC) {
        this.formEditCondicionUIC = formEditCondicionUIC;
    }

    public RichPanelFormLayout getFormEditCondicionUIC() {
        esVisibleAplicableA();
        asignarSeleccionCondicion();
        return formEditCondicionUIC;
    }
    
    public void setPanelRegionGestionUIC(RichPanelGroupLayout panelRegionGestionUIC) {
        this.panelRegionGestionUIC = panelRegionGestionUIC;
    }

    public RichPanelGroupLayout getPanelRegionGestionUIC() {
        return panelRegionGestionUIC;
    }
    
    public void setPanelMatrizTccGridUIC(RichPanelGroupLayout panelMatrizTccGridUIC) {
        this.panelMatrizTccGridUIC = panelMatrizTccGridUIC;
    }

    public RichPanelGroupLayout getPanelMatrizTccGridUIC() {
        return panelMatrizTccGridUIC;
    }
    
    public void setFechaVencimientoTerminoUIC(RichInputDate fechaVencimientoTerminoUIC) {
        this.fechaVencimientoTerminoUIC = fechaVencimientoTerminoUIC;
    }

    public RichInputDate getFechaVencimientoTerminoUIC() {
        return fechaVencimientoTerminoUIC;
    }

    public void setTccTablaUIC(RichTable tccTablaUIC) {
        this.tccTablaUIC = tccTablaUIC;
    }

    public RichTable getTccTablaUIC() {
        return tccTablaUIC;
    }

    public void setMostrarNombre(Boolean mostrarNombre) {
        this.mostrarNombre = mostrarNombre;
    }

    public Boolean getMostrarNombre() {
        return mostrarNombre;
    }

    public void setMostrarDescripcion(Boolean mostrarDescripcion) {
        this.mostrarDescripcion = mostrarDescripcion;
    }

    public Boolean getMostrarDescripcion() {
        return mostrarDescripcion;
    }

    public void setMostrarFechaInicio(Boolean mostrarFechaInicio) {
        this.mostrarFechaInicio = mostrarFechaInicio;
    }

    public Boolean getMostrarFechaInicio() {
        return mostrarFechaInicio;
    }

    public void setMostrarTipoFechaInicio(Boolean mostrarTipoFechaInicio) {
        this.mostrarTipoFechaInicio = mostrarTipoFechaInicio;
    }

    public Boolean getMostrarTipoFechaInicio() {
        return mostrarTipoFechaInicio;
    }

    public void setMostrarFechaVencimiento(Boolean mostrarFechaVencimiento) {
        this.mostrarFechaVencimiento = mostrarFechaVencimiento;
    }

    public Boolean getMostrarFechaVencimiento() {
        return mostrarFechaVencimiento;
    }

    public void setMostrarPlazo(Boolean mostrarPlazo) {
        this.mostrarPlazo = mostrarPlazo;
    }

    public Boolean getMostrarPlazo() {
        return mostrarPlazo;
    }

    public void setMostrarTipoPlazo(Boolean mostrarTipoPlazo) {
        this.mostrarTipoPlazo = mostrarTipoPlazo;
    }

    public Boolean getMostrarTipoPlazo() {
        return mostrarTipoPlazo;
    }

    public void setMostrarMoneda(Boolean mostrarMoneda) {
        this.mostrarMoneda = mostrarMoneda;
    }

    public Boolean getMostrarMoneda() {
        return mostrarMoneda;
    }

    public void setMostrarMonto(Boolean mostrarMonto) {
        this.mostrarMonto = mostrarMonto;
    }

    public Boolean getMostrarMonto() {
        return mostrarMonto;
    }

    public void setMostrartasa(Boolean mostrartasa) {
        this.mostrartasa = mostrartasa;
    }

    public Boolean getMostrartasa() {
        return mostrartasa;
    }

    public void setMostrarTipoTasa(Boolean mostrarTipoTasa) {
        this.mostrarTipoTasa = mostrarTipoTasa;
    }

    public Boolean getMostrarTipoTasa() {
        return mostrarTipoTasa;
    }

    public void setMostrarFrecuenciaRevision(Boolean mostrarFrecuenciaRevision) {
        this.mostrarFrecuenciaRevision = mostrarFrecuenciaRevision;
    }

    public Boolean getMostrarFrecuenciaRevision() {
        return mostrarFrecuenciaRevision;
    }

    public void setMostrarTipoFrecRevision(Boolean mostrarTipoFrecRevision) {
        this.mostrarTipoFrecRevision = mostrarTipoFrecRevision;
    }

    public Boolean getMostrarTipoFrecRevision() {
        return mostrarTipoFrecRevision;
    }

    public void setMostrarFechaInicioRevision(Boolean mostrarFechaInicioRevision) {
        this.mostrarFechaInicioRevision = mostrarFechaInicioRevision;
    }

    public Boolean getMostrarFechaInicioRevision() {
        return mostrarFechaInicioRevision;
    }

    public void setMostrarFrecPagoInteres(Boolean mostrarFrecPagoInteres) {
        this.mostrarFrecPagoInteres = mostrarFrecPagoInteres;
    }

    public Boolean getMostrarFrecPagoInteres() {
        return mostrarFrecPagoInteres;
    }

    public void setMostrarTipoFrecPagoInteres(Boolean mostrarTipoFrecPagoInteres) {
        this.mostrarTipoFrecPagoInteres = mostrarTipoFrecPagoInteres;
    }

    public Boolean getMostrarTipoFrecPagoInteres() {
        return mostrarTipoFrecPagoInteres;
    }

    public void setMostrarFechaInicioPagoInteres(Boolean mostrarFechaInicioPagoInteres) {
        this.mostrarFechaInicioPagoInteres = mostrarFechaInicioPagoInteres;
    }

    public Boolean getMostrarFechaInicioPagoInteres() {
        return mostrarFechaInicioPagoInteres;
    }

    public void setMostrarFrecAmortizacion(Boolean mostrarFrecAmortizacion) {
        this.mostrarFrecAmortizacion = mostrarFrecAmortizacion;
    }

    public Boolean getMostrarFrecAmortizacion() {
        return mostrarFrecAmortizacion;
    }

    public void setMostrarTipoFrecAmortizacion(Boolean mostrarTipoFrecAmortizacion) {
        this.mostrarTipoFrecAmortizacion = mostrarTipoFrecAmortizacion;
    }

    public Boolean getMostrarTipoFrecAmortizacion() {
        return mostrarTipoFrecAmortizacion;
    }

    public void setMostrarMora(Boolean mostrarMora) {
        this.mostrarMora = mostrarMora;
    }

    public Boolean getMostrarMora() {
        return mostrarMora;
    }

    public void setMostrarCobertura(Boolean mostrarCobertura) {
        this.mostrarCobertura = mostrarCobertura;
    }

    public Boolean getMostrarCobertura() {
        return mostrarCobertura;
    }

    public void setMostrarConcesionales(Boolean mostrarConcesionales) {
        this.mostrarConcesionales = mostrarConcesionales;
    }

    public Boolean getMostrarConcesionales() {
        return mostrarConcesionales;
    }

    public void setMostrarExternos(Boolean mostrarExternos) {
        this.mostrarExternos = mostrarExternos;
    }

    public Boolean getMostrarExternos() {
        return mostrarExternos;
    }

    public void setMostrarNombreContraparte(Boolean mostrarNombreContraparte) {
        this.mostrarNombreContraparte = mostrarNombreContraparte;
    }

    public Boolean getMostrarNombreContraparte() {
        return mostrarNombreContraparte;
    }

    public void setMostrarTipoContraparte(Boolean mostrarTipoContraparte) {
        this.mostrarTipoContraparte = mostrarTipoContraparte;
    }

    public Boolean getMostrarTipoContraparte() {
        return mostrarTipoContraparte;
    }

    public void setMostrarMontoMinimo(Boolean mostrarMontoMinimo) {
        this.mostrarMontoMinimo = mostrarMontoMinimo;
    }

    public Boolean getMostrarMontoMinimo() {
        return mostrarMontoMinimo;
    }

    public void setMostrarMontoMaximo(Boolean mostrarMontoMaximo) {
        this.mostrarMontoMaximo = mostrarMontoMaximo;
    }

    public Boolean getMostrarMontoMaximo() {
        return mostrarMontoMaximo;
    }

    public void setMostrarTasaMinima(Boolean mostrarTasaMinima) {
        this.mostrarTasaMinima = mostrarTasaMinima;
    }

    public Boolean getMostrarTasaMinima() {
        return mostrarTasaMinima;
    }

    public void setMostrarTasaMaxima(Boolean mostrarTasaMaxima) {
        this.mostrarTasaMaxima = mostrarTasaMaxima;
    }

    public Boolean getMostrarTasaMaxima() {
        return mostrarTasaMaxima;
    }

    public void setMostrarFecha(Boolean mostrarFecha) {
        this.mostrarFecha = mostrarFecha;
    }

    public Boolean getMostrarFecha() {
        return mostrarFecha;
    }

    public void setLblNombre(String lblNombre) {
        this.lblNombre = lblNombre;
    }

    public String getLblNombre() {
        return lblNombre;
    }

    public void setLblDescripcion(String lblDescripcion) {
        this.lblDescripcion = lblDescripcion;
    }

    public String getLblDescripcion() {
        return lblDescripcion;
    }

    public void setLblFechaInicioPlazo(String lblFechaInicioPlazo) {
        this.lblFechaInicioPlazo = lblFechaInicioPlazo;
    }

    public String getLblFechaInicioPlazo() {
        return lblFechaInicioPlazo;
    }

    public void setLblTipoFechaInicioPlazo(String lblTipoFechaInicioPlazo) {
        this.lblTipoFechaInicioPlazo = lblTipoFechaInicioPlazo;
    }

    public String getLblTipoFechaInicioPlazo() {
        return lblTipoFechaInicioPlazo;
    }

    public void setLblplazo(String lblplazo) {
        this.lblplazo = lblplazo;
    }

    public String getLblplazo() {
        return lblplazo;
    }

    public void setLblTipoPlazo(String lblTipoPlazo) {
        this.lblTipoPlazo = lblTipoPlazo;
    }

    public String getLblTipoPlazo() {
        return lblTipoPlazo;
    }

    public void setLblFechaVencimientoPlazo(String lblFechaVencimientoPlazo) {
        this.lblFechaVencimientoPlazo = lblFechaVencimientoPlazo;
    }

    public String getLblFechaVencimientoPlazo() {
        return lblFechaVencimientoPlazo;
    }

    public void setLblTasa(String lblTasa) {
        this.lblTasa = lblTasa;
    }

    public String getLblTasa() {
        return lblTasa;
    }

    public void setLblTipoTasa(String lblTipoTasa) {
        this.lblTipoTasa = lblTipoTasa;
    }

    public String getLblTipoTasa() {
        return lblTipoTasa;
    }

    public void setLblFrecuenciaRevision(String lblFrecuenciaRevision) {
        this.lblFrecuenciaRevision = lblFrecuenciaRevision;
    }

    public String getLblFrecuenciaRevision() {
        return lblFrecuenciaRevision;
    }

    public void setLblTipoFrecRevision(String lblTipoFrecRevision) {
        this.lblTipoFrecRevision = lblTipoFrecRevision;
    }

    public String getLblTipoFrecRevision() {
        return lblTipoFrecRevision;
    }

    public void setLblFechaInicioRevision(String lblFechaInicioRevision) {
        this.lblFechaInicioRevision = lblFechaInicioRevision;
    }

    public String getLblFechaInicioRevision() {
        return lblFechaInicioRevision;
    }

    public void setLblFrecPagoInteres(String lblFrecPagoInteres) {
        this.lblFrecPagoInteres = lblFrecPagoInteres;
    }

    public String getLblFrecPagoInteres() {
        return lblFrecPagoInteres;
    }

    public void setLblTipoFrecPagoInteres(String lblTipoFrecPagoInteres) {
        this.lblTipoFrecPagoInteres = lblTipoFrecPagoInteres;
    }

    public String getLblTipoFrecPagoInteres() {
        return lblTipoFrecPagoInteres;
    }

    public void setLblFechaInicioPagoInteres(String lblFechaInicioPagoInteres) {
        this.lblFechaInicioPagoInteres = lblFechaInicioPagoInteres;
    }

    public String getLblFechaInicioPagoInteres() {
        return lblFechaInicioPagoInteres;
    }

    public void setLblFrecAmortizacion(String lblFrecAmortizacion) {
        this.lblFrecAmortizacion = lblFrecAmortizacion;
    }

    public String getLblFrecAmortizacion() {
        return lblFrecAmortizacion;
    }

    public void setLblTipoFrecAmortizacion(String lblTipoFrecAmortizacion) {
        this.lblTipoFrecAmortizacion = lblTipoFrecAmortizacion;
    }

    public String getLblTipoFrecAmortizacion() {
        return lblTipoFrecAmortizacion;
    }

    public void setLblMora(String lblMora) {
        this.lblMora = lblMora;
    }

    public String getLblMora() {
        return lblMora;
    }

    public void setLblCobertura(String lblCobertura) {
        this.lblCobertura = lblCobertura;
    }

    public String getLblCobertura() {
        return lblCobertura;
    }

    public void setLblConcesionales(String lblConcesionales) {
        this.lblConcesionales = lblConcesionales;
    }

    public String getLblConcesionales() {
        return lblConcesionales;
    }

    public void setLblExternos(String lblExternos) {
        this.lblExternos = lblExternos;
    }

    public String getLblExternos() {
        return lblExternos;
    }

    public void setLblNombreContraparte(String lblNombreContraparte) {
        this.lblNombreContraparte = lblNombreContraparte;
    }

    public String getLblNombreContraparte() {
        return lblNombreContraparte;
    }

    public void setLblTipoContraparte(String lblTipoContraparte) {
        this.lblTipoContraparte = lblTipoContraparte;
    }

    public String getLblTipoContraparte() {
        return lblTipoContraparte;
    }

    public void setLblMontoMinimo(String lblMontoMinimo) {
        this.lblMontoMinimo = lblMontoMinimo;
    }

    public String getLblMontoMinimo() {
        return lblMontoMinimo;
    }

    public void setLblMontoMaximo(String lblMontoMaximo) {
        this.lblMontoMaximo = lblMontoMaximo;
    }

    public String getLblMontoMaximo() {
        return lblMontoMaximo;
    }

    public void setLblTasaMinima(String lblTasaMinima) {
        this.lblTasaMinima = lblTasaMinima;
    }

    public String getLblTasaMinima() {
        return lblTasaMinima;
    }

    public void setLblTasaMaxima(String lblTasaMaxima) {
        this.lblTasaMaxima = lblTasaMaxima;
    }

    public String getLblTasaMaxima() {
        return lblTasaMaxima;
    }

    public void setLblFecha(String lblFecha) {
        this.lblFecha = lblFecha;
    }

    public String getLblFecha() {
        return lblFecha;
    }

    public void setLblMoneda(String lblMoneda) {
        this.lblMoneda = lblMoneda;
    }

    public String getLblMoneda() {
        return lblMoneda;
    }

    public void setLblMonto(String lblMonto) {
        this.lblMonto = lblMonto;
    }

    public String getLblMonto() {
        return lblMonto;
    }

    public void setReqNombre(Boolean reqNombre) {
        this.reqNombre = reqNombre;
    }

    public Boolean getReqNombre() {
        return reqNombre;
    }

    public void setReqDescripcion(Boolean reqDescripcion) {
        this.reqDescripcion = reqDescripcion;
    }

    public Boolean getReqDescripcion() {
        return reqDescripcion;
    }

    public void setReqFechaInicio(Boolean reqFechaInicio) {
        this.reqFechaInicio = reqFechaInicio;
    }

    public Boolean getReqFechaInicio() {
        return reqFechaInicio;
    }

    public void setReqTipoFechaInicio(Boolean reqTipoFechaInicio) {
        this.reqTipoFechaInicio = reqTipoFechaInicio;
    }

    public Boolean getReqTipoFechaInicio() {
        return reqTipoFechaInicio;
    }

    public void setReqFechaVencimiento(Boolean reqFechaVencimiento) {
        this.reqFechaVencimiento = reqFechaVencimiento;
    }

    public Boolean getReqFechaVencimiento() {
        return reqFechaVencimiento;
    }

    public void setReqPlazo(Boolean reqPlazo) {
        this.reqPlazo = reqPlazo;
    }

    public Boolean getReqPlazo() {
        return reqPlazo;
    }

    public void setReqTipoPlazo(Boolean reqTipoPlazo) {
        this.reqTipoPlazo = reqTipoPlazo;
    }

    public Boolean getReqTipoPlazo() {
        return reqTipoPlazo;
    }

    public void setReqMoneda(Boolean reqMoneda) {
        this.reqMoneda = reqMoneda;
    }

    public Boolean getReqMoneda() {
        return reqMoneda;
    }

    public void setReqMonto(Boolean reqMonto) {
        this.reqMonto = reqMonto;
    }

    public Boolean getReqMonto() {
        return reqMonto;
    }

    public void setReqtasa(Boolean reqtasa) {
        this.reqtasa = reqtasa;
    }

    public Boolean getReqtasa() {
        return reqtasa;
    }

    public void setReqTipoTasa(Boolean reqTipoTasa) {
        this.reqTipoTasa = reqTipoTasa;
    }

    public Boolean getReqTipoTasa() {
        return reqTipoTasa;
    }

    public void setReqFrecuenciaRevision(Boolean reqFrecuenciaRevision) {
        this.reqFrecuenciaRevision = reqFrecuenciaRevision;
    }

    public Boolean getReqFrecuenciaRevision() {
        return reqFrecuenciaRevision;
    }

    public void setReqTipoFrecRevision(Boolean reqTipoFrecRevision) {
        this.reqTipoFrecRevision = reqTipoFrecRevision;
    }

    public Boolean getReqTipoFrecRevision() {
        return reqTipoFrecRevision;
    }

    public void setReqFechaInicioRevision(Boolean reqFechaInicioRevision) {
        this.reqFechaInicioRevision = reqFechaInicioRevision;
    }

    public Boolean getReqFechaInicioRevision() {
        return reqFechaInicioRevision;
    }

    public void setReqFrecPagoInteres(Boolean reqFrecPagoInteres) {
        this.reqFrecPagoInteres = reqFrecPagoInteres;
    }

    public Boolean getReqFrecPagoInteres() {
        return reqFrecPagoInteres;
    }

    public void setReqTipoFrecPagoInteres(Boolean reqTipoFrecPagoInteres) {
        this.reqTipoFrecPagoInteres = reqTipoFrecPagoInteres;
    }

    public Boolean getReqTipoFrecPagoInteres() {
        return reqTipoFrecPagoInteres;
    }

    public void setReqFechaInicioPagoInteres(Boolean reqFechaInicioPagoInteres) {
        this.reqFechaInicioPagoInteres = reqFechaInicioPagoInteres;
    }

    public Boolean getReqFechaInicioPagoInteres() {
        return reqFechaInicioPagoInteres;
    }

    public void setReqFrecAmortizacion(Boolean reqFrecAmortizacion) {
        this.reqFrecAmortizacion = reqFrecAmortizacion;
    }

    public Boolean getReqFrecAmortizacion() {
        return reqFrecAmortizacion;
    }

    public void setReqTipoFrecAmortizacion(Boolean reqTipoFrecAmortizacion) {
        this.reqTipoFrecAmortizacion = reqTipoFrecAmortizacion;
    }

    public Boolean getReqTipoFrecAmortizacion() {
        return reqTipoFrecAmortizacion;
    }

    public void setReqMora(Boolean reqMora) {
        this.reqMora = reqMora;
    }

    public Boolean getReqMora() {
        return reqMora;
    }

    public void setReqCobertura(Boolean reqCobertura) {
        this.reqCobertura = reqCobertura;
    }

    public Boolean getReqCobertura() {
        return reqCobertura;
    }

    public void setReqConcesionales(Boolean reqConcesionales) {
        this.reqConcesionales = reqConcesionales;
    }

    public Boolean getReqConcesionales() {
        return reqConcesionales;
    }

    public void setReqExternos(Boolean reqExternos) {
        this.reqExternos = reqExternos;
    }

    public Boolean getReqExternos() {
        return reqExternos;
    }

    public void setReqNombreContraparte(Boolean reqNombreContraparte) {
        this.reqNombreContraparte = reqNombreContraparte;
    }

    public Boolean getReqNombreContraparte() {
        return reqNombreContraparte;
    }

    public void setReqTipoContraparte(Boolean reqTipoContraparte) {
        this.reqTipoContraparte = reqTipoContraparte;
    }

    public Boolean getReqTipoContraparte() {
        return reqTipoContraparte;
    }

    public void setReqMontoMinimo(Boolean reqMontoMinimo) {
        this.reqMontoMinimo = reqMontoMinimo;
    }

    public Boolean getReqMontoMinimo() {
        return reqMontoMinimo;
    }

    public void setReqMontoMaximo(Boolean reqMontoMaximo) {
        this.reqMontoMaximo = reqMontoMaximo;
    }

    public Boolean getReqMontoMaximo() {
        return reqMontoMaximo;
    }

    public void setReqTasaMinima(Boolean reqTasaMinima) {
        this.reqTasaMinima = reqTasaMinima;
    }

    public Boolean getReqTasaMinima() {
        return reqTasaMinima;
    }

    public void setReqTasaMaxima(Boolean reqTasaMaxima) {
        this.reqTasaMaxima = reqTasaMaxima;
    }

    public Boolean getReqTasaMaxima() {
        return reqTasaMaxima;
    }

    public void setReqFecha(Boolean reqFecha) {
        this.reqFecha = reqFecha;
    }

    public Boolean getReqFecha() {
        return reqFecha;
    }

    public void setEsFechaEspecificaTermino(Boolean esFechaEspecificaTermino) {
        this.esFechaEspecificaTermino = esFechaEspecificaTermino;
    }

    public Boolean getEsFechaEspecificaTermino() {
        Boolean retorno = Boolean.FALSE;
        AttributeBinding idTipoFechaInicio;
        idTipoFechaInicio = ADFUtils.findControlBinding("IdTcaTipoFechaInicio");
        String idTipoFechaInicioEspecifica = getStringFromBundle("TERMINO_ID_TIPO_FECHA_INICIO_ESPECIFICA");
        
        if(null != idTipoFechaInicio.getInputValue() && idTipoFechaInicio.getInputValue().toString().equals(idTipoFechaInicioEspecifica)){
            retorno = Boolean.TRUE;
        }
        
        return retorno;
    }
    
    public void setTabTerminos(RichShowDetailItem tabTerminos) {
        this.tabTerminos = tabTerminos;
    }

    public RichShowDetailItem getTabTerminos() {
        return tabTerminos;
    }

    public void setTabCondiciones(RichShowDetailItem tabCondiciones) {
        this.tabCondiciones = tabCondiciones;
    }

    public RichShowDetailItem getTabCondiciones() {
        return tabCondiciones;
    }

    public void setTabComisiones(RichShowDetailItem tabComisiones) {
        this.tabComisiones = tabComisiones;
    }

    public RichShowDetailItem getTabComisiones() {
        return tabComisiones;
    }

    public void setFechaInicioOrigen(Timestamp fechaInicioOrigen) {
        this.fechaInicioOrigen = fechaInicioOrigen;
    }

    public Timestamp getFechaInicioOrigen() {
        return fechaInicioOrigen;
    }

    public void setIdFechaInicioOrigen(Integer idFechaInicioOrigen) {
        this.idFechaInicioOrigen = idFechaInicioOrigen;
    }

    public Integer getIdFechaInicioOrigen() {
        return idFechaInicioOrigen;
    }

    /**
     * Ejecuta la logica para validar la aplicacion de las acciones de TCC que modifican los estados o subestados TCC
     * @param idAccion contiene id de accion TCC. Los valores definidos para las acciones son:
     *        1 -> Accion Editar
     *        2 -> Accion Eliminar
     *        3 -> Accion Exceptuar
     *        4 -> Accion Dispensar
     *        5 -> Accion Validar
     *        6 -> Accion Eliminar Validacion
     *        7 -> Accion Por validar
     *        8 -> Accion Por eliminar
     *        9 -> Accion Agregar
     *        10 -> Accion Reactivar
     *        11 -> Accion Desactivar
     * @return devuelve valor booleano, true si la accion es valida de aplicar o false en caso contrario
     */
    public boolean validaAccionTCC(int idAccion){
        
        boolean esValida = false;
        
        //Obtiene el valor Bandera que indica si es Modo Lectura o Escritura
        Boolean esModoEscritura = null;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}") != null){
                esModoEscritura = Boolean.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}").toString());    
            }else{
                LOGGER.severe("Parametro pEsModoEscritura no obtenido");
            }
            
            if(esModoEscritura == null){
                esModoEscritura = Boolean.FALSE; 
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el valor de Modo Lectura/Escritura", e);
        }
        
        if(esModoEscritura){
            
            //Obtiene el Id de Proceso BPM
            Integer idProcesoBpm = null;
            try{
                idProcesoBpm = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm"); //Definicion Obligatorio en todas las pantallas
            }catch(Exception e){
                LOGGER.severe("Error al obtener el valor del Atributo Id Proceso BPM de Bindings", e);
            }
            
            Integer idEstadoTcc = null;
            Integer idSubEstadoTcc = null;

            //Verifica si es una accion de botones fuera de panel, como el de Agregar
            if(idAccion != 9){
                try{
                    //Obtiene el Id de Estado TCC
                    if(ADFUtils.findControlBinding("IdTcaEstadoTccAttrValue") != null){
                        idEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaEstadoTccAttrValue");  
                    }
                }catch(Exception e){
                    LOGGER.warning("Error al obtener el valor del Atributo Id de Estado TCC");
                }
                
                try{
                    //Obtiene el Id de Sub Estado TCC
                    if(ADFUtils.findControlBinding("IdTcaSubEstadoTccAttrValue") != null){ 
                        idSubEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaSubEstadoTccAttrValue");    
                    }
                }catch(Exception e){
                    LOGGER.warning("Error al obtener el  valor del Atributo Id de Sub Estado TCC");
                }
            }
            
            //Obtiene el Id de Tarea BPM
            Integer idTareaBpm = null;
            try{
                idTareaBpm = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"); //Viene de parametro de Task Flow
            }catch(Exception e){
                LOGGER.severe("Error al obtener el valor del parametro de Task Flow que contiene el Id de Tarea BPM");
            }
            
            Boolean esEditableForm = null;
            try{
                esEditableForm = (Boolean) ADFUtils.getBoundAttributeValue("EditableFormalizacion");
            }catch(Exception e){
                LOGGER.warning("Valor de configuracion para Editable en Formalizacion no obtenido");
            }
            
            Boolean dispensaEnEnmienda = null;
            try{
                dispensaEnEnmienda = (Boolean) ADFUtils.getBoundAttributeValue("DispensaEnmienda");
            }catch(Exception e){
                LOGGER.warning("Valor de configuracion para Dispensar en Enmienda no obtenido");
            }
            
            if(idProcesoBpm != null){
                
                if(!ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm) &&
                   !ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm) &&
                   !ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm) &&
                   !ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm) &&
                   !ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm) &&
                   !ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm) &&
                   !ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm) &&
                   !ID_PROCESO_EVALUACION_BPM.equals(idProcesoBpm) &&
                   !ID_PROCESO_EVALUACION_SIEMAS_BPM.equals(idProcesoBpm) &&
                   !ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)){
                    LOGGER.severe("El Id del Proceso BPM es invalido");
                    esValida = false;
                }
                
                switch(idAccion){
                    case 1: //Codigo para el Id Accion Editar
                        if(ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_SUGERIDA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_MANDATORIA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_POR_APROBAR.equals(idEstadoTcc)){
                                    if(idSubEstadoTcc == null){
                                        esValida = true;
                                    }else{
                                        esValida = true;
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;
                            }
                        }
                        if(ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                            //esValida = false;
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_POR_APROBAR.equals(idEstadoTcc)){
                                    
                                    if(idSubEstadoTcc == null){
                                        esValida = true;
                                    }else{
                                        
                                        if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc)){
                                            esValida = true;
                                        }
                                        
                                        if(ID_ESTADO_TCC_POR_APROBAR.equals(idEstadoTcc)){
                                            if(ID_ESTADO_TCC_EDITADA.equals(idSubEstadoTcc)){
                                                esValida = true;
                                            }else{
                                                esValida = true;
                                            }
                                        }
                                    } 
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;
                            }
                        }
                        if(ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_APROBADA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_SUGERIDA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_MANDATORIA.equals(idEstadoTcc)){
                                    //esValida = true;
                                    if(idSubEstadoTcc == null){
                                        esValida = true;    
                                    }else{
                                        esValida = true;        
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;
                            }
                            
                            //Si el campo tiene valor aplicar validacion en caso contrario se toma criterios del 
                            //diagrama de estado tcc
                            if(esEditableForm != null){
                                if(!esEditableForm){
                                    esValida = esEditableForm;
                                }
                            }
                        }
                        if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_APROBADA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_FORMALIZADA.equals(idEstadoTcc)){
                                    //esValida = true;
                                    if(idSubEstadoTcc == null){
                                        esValida = true;    
                                    }else{
                                        esValida = true;        
                                    }
                                }else{
                                    if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc)){
                                        if(idSubEstadoTcc == null){
                                            esValida = true;    
                                        }else{
                                            esValida = true;        
                                        }
                                    }else{
                                        esValida = false;    
                                    }
                                }
                            }else{
                                esValida = false;
                            }
                        }
                        if(ID_PROCESO_EVALUACION_BPM.equals(idProcesoBpm) || ID_PROCESO_EVALUACION_SIEMAS_BPM.equals(idProcesoBpm)){
                            if(ID_TAREA_ACTUALIZAR_CONDICIONES_SIEMAS.equals(idTareaBpm)) {
                                if(idEstadoTcc != null){
                                    if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc)){
                                        if(idSubEstadoTcc == null){
                                            esValida = true;    
                                        }else{
                                            esValida = true;        
                                        }
                                    }else{
                                        esValida = false;    
                                    }
                                }else{
                                    esValida = false;
                                }
                            }
                        }
                        if (ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)) {
                            if (idTareaBpm != null && FenixConstants.PA15_SOLICITAR_FORMALIZACION_ENMIENDA == idTareaBpm) {
                                esValida = true;
                            } else {
                                esValida = false;
                            }
                        }
                    break;
                    case 2: //Codigo para el Id Accion Eliminar
                        if(ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_SUGERIDA.equals(idEstadoTcc)){
                                    
                                    if(idSubEstadoTcc == null){
                                        esValida = true;  
                                    }else{
                                        if(ID_ESTADO_TCC_EDITADA.equals(idSubEstadoTcc)){
                                            esValida = true;  
                                        }else{
                                            esValida = false;      
                                        }
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;    
                            }
                        }
                        if(ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                            //esValida = false;
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc)){
                                    if(idSubEstadoTcc == null){
                                        esValida = true;
                                    }else{
                                        if(ID_ESTADO_TCC_EDITADA.equals(idSubEstadoTcc)){
                                            esValida = true;   
                                        }else{
                                            esValida = false;    
                                        }
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;
                            }
                        }
                        if(ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_SUGERIDA.equals(idEstadoTcc)){
                                    //esValida = true;    
                                    if(idSubEstadoTcc == null){
                                        esValida = true;   
                                    }else{
                                        if(ID_ESTADO_TCC_EDITADA.equals(idSubEstadoTcc)){
                                            esValida = true;    
                                        }else{
                                            esValida = false;    
                                        }
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;
                            }
                        }
                        if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc)){
                                    if(idSubEstadoTcc != null){
                                        if(ID_ESTADO_TCC_NUEVA_POR_APROBAR.equals(idSubEstadoTcc)){
                                            esValida = true;
                                        }else{
                                            esValida = false;
                                        }
                                    }else{
                                        esValida = true;
                                    }
                                }else{
                                    /* Aplica modificacion por Incidencia FNXII-3543
                                    if(ID_ESTADO_TCC_APROBADA.equals(idEstadoTcc) ||
                                       ID_ESTADO_TCC_FORMALIZADA.equals(idEstadoTcc)){
                                        
                                        if(idSubEstadoTcc == null || ID_ESTADO_TCC_POR_MODIFICAR.equals(idSubEstadoTcc)){
                                            esValida = true;
                                        }else{
                                            esValida = false;
                                        }
                                        
                                        
                                    }else{
                                        esValida = false;
                                    }
                                    */
                                    esValida = false;
                                }
                            }else{
                                esValida = false;    
                            }
                        }
                        if(ID_PROCESO_EVALUACION_BPM.equals(idProcesoBpm) || ID_PROCESO_EVALUACION_SIEMAS_BPM.equals(idProcesoBpm)){
                            if(ID_TAREA_ACTUALIZAR_CONDICIONES_SIEMAS.equals(idTareaBpm)) {
                                if(idEstadoTcc != null){
                                    if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc)){
                                        if(idSubEstadoTcc == null){
                                            esValida = true;   
                                        }else{
                                            if(ID_ESTADO_TCC_EDITADA.equals(idSubEstadoTcc)){
                                                esValida = true;    
                                            }else{
                                                esValida = false;    
                                            }
                                        }
                                    }else{
                                        esValida = false;    
                                    }
                                }else{
                                    esValida = false;
                                }
                            }
                        }
                        if (ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)) {
                            if (idTareaBpm != null && FenixConstants.PA15_SOLICITAR_FORMALIZACION_ENMIENDA == idTareaBpm) {
                                esValida = true;
                            } else {
                                esValida = false;
                            }
                        }
                    break;
                    case 3: //Codigo para el Id Accion Exceptuar
                        if(ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_MANDATORIA.equals(idEstadoTcc)){
                                    //esValida = true;
                                    if(idSubEstadoTcc == null || ID_ESTADO_TCC_MANDATORIA_EDITADA.equals(idSubEstadoTcc)){
                                        esValida = true;
                                    }else{
                                        esValida = false;
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;    
                            }
                        }
                        if(ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                            //esValida = false;
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_MANDATORIA.equals(idEstadoTcc)){
                                    if(idSubEstadoTcc == null || ID_ESTADO_TCC_MANDATORIA_EDITADA.equals(idSubEstadoTcc)){
                                        esValida = true;
                                    }else{
                                        esValida = false;
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;
                            }
                        }
                        if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                    break;
                    case 4: //Codigo para el Id Accion Dispensar
                        if(ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_APROBADA.equals(idEstadoTcc) || ID_ESTADO_TCC_FORMALIZADA.equals(idEstadoTcc)){
                                    //esValida = true;
                                    if(idSubEstadoTcc == null || ID_ESTADO_TCC_POR_MODIFICAR.equals(idSubEstadoTcc)){
                                        esValida = true;
                                    }else{
                                        esValida = false;
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;
                            }
                            
                            //Si el campo tiene valor aplicar validacion en caso contrario se toma criterios del 
                            //diagrama de estado tcc
                            if(dispensaEnEnmienda != null){
                                if(!dispensaEnEnmienda){
                                    esValida = dispensaEnEnmienda;
                                }
                            }
                        }   
                        if(ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                    break;
                    case 5: //Codigo para el Id Accion Validar
                        if(ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_APROBADA.equals(idEstadoTcc)){
                                    if(ID_ESTADO_TCC_CON_EVIDENCIA.equals(idSubEstadoTcc)){
                                        esValida = true;
                                    }else{
                                        esValida = false;
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;    
                            }
                        }
                        if(ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }   
                        if(ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                    break;
                    case 6: //Codigo para el Id Accion Eliminar Validacion
                        if(ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_APROBADA.equals(idEstadoTcc)){
                                    if(ID_ESTADO_TCC_VALIDA_PARCIALMENTE.equals(idSubEstadoTcc)){
                                        esValida = true;
                                    }else{
                                        esValida = false;
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;
                            }
                        }
                        if(ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                    break;
                    case 7: //Codigo para el Id Accion Por Validar o Lista para validar
                        if(ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_APROBADA.equals(idEstadoTcc)){
                                    if(ID_ESTADO_TCC_POR_VALIDAR_0.equals(idSubEstadoTcc) ||
                                       ID_ESTADO_TCC_POR_VALIDAR_1.equals(idSubEstadoTcc)){
                                        esValida = true;
                                    }else{
                                        esValida = false;
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;    
                            }
                        }
                        if(ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                    break;
                    case 8: //Codigo para el Id Accion Por Eliminar
                        if(ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            if(idEstadoTcc != null){
                                if(ID_ESTADO_TCC_APROBADA.equals(idEstadoTcc) ||
                                   ID_ESTADO_TCC_FORMALIZADA.equals(idEstadoTcc)){
                                    //esValida = true;
                                    if(idSubEstadoTcc == null){
                                        esValida = true;
                                    }else{
                                        esValida = false;
                                    }
                                }else{
                                    esValida = false;
                                }
                            }else{
                                esValida = false;
                            }
                        }
                        if(ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                    break;
                    case 9: //Codigo para el Id Accion Agregar
                        if(ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm)){
                            esValida = true;
                        }
                        if(ID_PROCESO_PREPARACION_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                            esValida = true;
                        }
                        if(ID_PROCESO_ADMON_CONDICIONES_BPM.equals(idProcesoBpm)){
                            esValida = false;
                        }
                        if(ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                            esValida = true;
                        }
                        if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm)){
                            esValida = true;
                        }
                        if (ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)) {
                            if (idTareaBpm != null && FenixConstants.PA15_SOLICITAR_FORMALIZACION_ENMIENDA == idTareaBpm) {
                                esValida = true;
                            } else {
                                esValida = false;
                            }
                        }
                    break;
                    case 10: //Codigo para el Id Accion Reactivar
                        if(ID_PROCESO_EVALUACION_BPM.equals(idProcesoBpm) || ID_PROCESO_EVALUACION_SIEMAS_BPM.equals(idProcesoBpm)){
                            if(ID_TAREA_ACTUALIZAR_CONDICIONES_SIEMAS.equals(idTareaBpm)){
                                if(idEstadoTcc != null){
                                    if(idSubEstadoTcc != null){
                                        if(ID_ESTADO_TCC_INACTIVA_1.equals(idSubEstadoTcc)){
                                            esValida = true;
                                        }else{
                                            esValida = false;
                                        }
                                    }else{
                                        esValida = false;
                                    }
                                }else{
                                    esValida = false;
                                }
                            } else {
                                esValida = false;
                            }
                        }
                    break;
                    case 11: //Codigo para el Id Accion Desactivar
                        if(ID_PROCESO_EVALUACION_BPM.equals(idProcesoBpm) || ID_PROCESO_EVALUACION_SIEMAS_BPM.equals(idProcesoBpm)){
                            if(ID_TAREA_ACTUALIZAR_CONDICIONES_SIEMAS.equals(idTareaBpm)){
                                if(idEstadoTcc != null){
                                    if(ID_ESTADO_TCC_INACTIVA_0.equals(idEstadoTcc) ||
                                       ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc)){
                                        esValida = false;
                                    }else{
                                        if(idSubEstadoTcc != null){
                                            if(ID_ESTADO_TCC_INACTIVA_1.equals(idSubEstadoTcc)){
                                                esValida = false;
                                            }else{
                                                esValida = true;
                                            }
                                        }else{
                                            esValida = true;
                                        }
                                    }
                                }else{
                                    esValida = false;
                                }
                            } else {
                                esValida = false;
                            }
                        }
                    break;
                    default:
                        esValida = false;
                        LOGGER.warning("Se asigna valor por defecto al no encontrar Id de Accion definida: " + 
                                       esValida);
                    break;
                }
            }else{
                //Se asigna valor por defecto cuando no fue posible obtener id de proceso bpm
                esValida = false;
                LOGGER.warning("Se asigna valor por defecto al no obtener Id de Proceso BPM: " + esValida);
            }
        }else{
            LOGGER.warning("Modo Lectura, no se permiten acciones");
        }
        
        return esValida;
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
        
        LOGGER.entering(MattrizTccActionsBean.class.getName(),
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
                LOGGER.warning("Ejecuta metodo ".concat(operBindName));
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
        
        LOGGER.exiting(MattrizTccActionsBean.class.getName(), 
                       "executeOperBinding",
                       oper);
        return oper;
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
    
    public static void selectListenerTableUIC(SelectionEvent event){
        
        RichTable _table = (RichTable) event.getSource();
        if(_table != null){
            //the Collection Model is the object that provides the
            //structured data
            //for the table to render
            CollectionModel _tableModel = (CollectionModel) _table.getValue();
            
            //the ADF object that implements the CollectionModel is
            //JUCtrlHierBinding. It is wrapped by the CollectionModel API
            JUCtrlHierBinding _adfTableBinding = (JUCtrlHierBinding) _tableModel.getWrappedData();
            
            if(_adfTableBinding != null){
                //Acess the ADF iterator binding that is used with
                //ADF table binding
                DCIteratorBinding _tableIteratorBinding = _adfTableBinding.getDCIteratorBinding();
                
                if(_tableIteratorBinding != null){
                    
                    //selection with the selection in the ADF model
                    Object _selectedRowData = _table.getSelectedRowData();
                
                    //cast to JUCtrlHierNodeBinding, which is the ADF object
                    //that represents a row
                    JUCtrlHierNodeBinding _nodeBinding = (JUCtrlHierNodeBinding) _selectedRowData;
                    
                    if(_nodeBinding != null){
                        //get the row key from the node binding and set it
                        //as the current row in the iterator
                        Key _rwKey = _nodeBinding.getRowKey();  
                        
                        if(_rwKey != null){
                            _tableIteratorBinding.setCurrentRowWithKey(_rwKey.toStringFormat(true));        
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Determina el Id del tipo de gestion conforme al tipo de TCC y si se desea editar o no, es para uso interno de 
     * la aplicacion y conforme a los valores definidos por el task flow de gestion TCC
     * @param idLinkTipoTcc contiene id del tipo de TCC, definido internamente por los valores:
     *        -1 = Termino
     *        -2 = Condicion
     *        -3 = Comision
     * @param editar define valor booleano para indicar si se desea editar la TCC
     * @return devuelve indicador numerico del tipo de gestion
     */
    public Integer obtenerIdTipoGestion(Integer idLinkTipoTcc, 
                                        boolean editar){
        
        Integer idGestionTipo = null;
        
        if(idLinkTipoTcc != null){
            
            switch(idLinkTipoTcc){
                case 1:
                    idGestionTipo = editar ? ID_GESTION_TIPO_EDITAR_TERMINO : ID_GESTION_TIPO_VER_TERMINO;
                break;
                case 2:
                    idGestionTipo = editar ? ID_GESTION_TIPO_EDITAR_CONDICION : ID_GESTION_TIPO_VER_CONDICION;
                break;
                case 3:
                    idGestionTipo = editar ? ID_GESTION_TIPO_EDITAR_COMISION : ID_GESTION_TIPO_VER_COMISION;
                break;
                default:
                    idGestionTipo = null;
                break;
            }
        }
        
        return idGestionTipo;
    }
    
    /**
     * Ejecuta la inicializacion de la pantalla para el Grid de TCC
     */
    @SuppressWarnings("unchecked")
    public void iniciaMatrizTccGrid() {    
        LOGGER.warning("Inicia metodo iniciaMatrizTccGrid");
        MatrizTccBean matrizTccBean = null;
        Boolean esEstadoCompletado = Boolean.FALSE;
        try{
            
            matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
            esEstadoCompletado = matrizTccBean.getEsEstadoCompletado();
            LOGGER.warning("esEstadoCompletado :"+esEstadoCompletado);
                    
            if(esEstadoCompletado){
                LOGGER.warning("Cargar solo datos de la tabla,no cargar taskflow secundario");
                //Inicializa Componentes de Grid por Id de Operacion
                inicializaGridTccPorOperacion();
                
                //Ejecuta la consulta del Proceso BPM
                consultaProcesoBPMTaskFlow();
            }else{
                LOGGER.warning("Flujo normal");
                //Lee parametros de Task y asigna a atributos de Managed Bean de PageFlow Scope
                asignaParametrosTaskFlowToBean();
        
                //Inicializa Componentes de Grid por Id de Operacion
                inicializaGridTccPorOperacion();
        
                //Ejecuta la consulta del Proceso BPM
                consultaProcesoBPMTaskFlow();
            }
        }catch(Exception ex){
            LOGGER.warning("Error en iniciaMatrizTccGrid");            
        }
        LOGGER.warning("Fuera del metodo iniciaMatrizTccGrid");
    }
    
    /**
     * Ejecuta la inicializacion de la pantalla para el Tree de TCC
     */
    @SuppressWarnings("unchecked")
    public void iniciaMatrizTccTree() {    
        LOGGER.warning("Inicia metodo iniciaMatrizTccTree");
        //Lee parametros de Task y asigna a atributos de Managed Bean de PageFlow Scope
        asignaParametrosTaskFlowToBean();
        
        //Ejecuta la consulta del Proceso BPM
        consultaProcesoBPMTaskFlow();
    }
    
    public void asignaParametrosTaskFlowToBean(){
        Long idOperacion = null;
        Integer idModalidad = null;
        Boolean esModoEscritura = null;
        Integer idTareaBpm = null;
        BigDecimal montoSolicitado = null;
        Long idEnmienda = null;
        
        String strValue = null;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
                strValue = JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
            }
            
            if(strValue == null){
                LOGGER.severe("Error el Id de Operacion es NULL");
            }else{
                idOperacion = new Long(strValue);
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Operacion", e);
        }
        
        strValue = null;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null){
                strValue = JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}").toString();
            }
            
            if(strValue == null){
                LOGGER.severe("Error el Id de Modalidad es NULL");
            }else{
                idModalidad = new Integer(strValue);
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Modalidad", e);
        }
        
        strValue = null;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}") != null){
                strValue = JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}").toString();
            }
            
            if(strValue == null){
                LOGGER.severe("Error parametro Es Modo Escritura es NULL");
            }else{
                esModoEscritura = Boolean.parseBoolean(strValue);
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el parametro de Es Modo Escritura", e);
        }
        
        strValue = null;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null){
                strValue = JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString();
            }
            
            if(strValue == null){
                LOGGER.severe("Error el Id de Tarea BPM es NULL");
            }else{
                idTareaBpm = new Integer(strValue);
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Tarea BPM", e);
        }
        
        strValue = null;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pMontoSolicitado}") != null){
                strValue = JSFUtils.resolveExpression("#{pageFlowScope.pMontoSolicitado}").toString();
            }
            
            if(strValue == null){
                LOGGER.severe("Error el Monto Solicitado es NULL");
            }else{
                montoSolicitado = new BigDecimal(strValue);    
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Monto Solicitado", e);
        }
        
        strValue = null;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdEnmienda}") != null){
                strValue = JSFUtils.resolveExpression("#{pageFlowScope.pIdEnmienda}").toString();
            }
            
            if(strValue ==  null){
                LOGGER.warning("El Id de Enmienda es NULL");
            }else{
                idEnmienda = new Long(strValue);
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Enmienda", e);
        }
        
        if(idOperacion != null &&
           idModalidad != null &&
           esModoEscritura != null &&
           idTareaBpm != null){
            
            MatrizTccBean bean = null;
            bean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
            if(bean != null){
                bean.setIdOperacion(idOperacion);
                bean.setIdModalidad(idModalidad);
                bean.setEsModoEscritura(esModoEscritura);
                bean.setIdTareaBpm(idTareaBpm);
                bean.setMontoSolicitado(montoSolicitado);
                bean.setIdEnmienda(idEnmienda);
            }else{
                LOGGER.severe("Managed Bean de Matriz TCC no obtenido");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void inicializaGridTccPorOperacion(){
        
        String strIdOperacion = null;
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
                strIdOperacion = JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
            }
            
            if(strIdOperacion == null){
                LOGGER.severe("Error el Id de Operacion es NULL");
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Operacion", e);
        }
        
        if(strIdOperacion != null){
            
            Number idOperacion = null;
            try{
                idOperacion = new Number(strIdOperacion);
            }catch(Exception e){
                LOGGER.severe("Error al convertir cadena de Id Operacion a objeto Number. Valor: " + strIdOperacion);
            }
            
            MatrizTccBean matrizTccBean = null;
            matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
            
            Number idEnmienda = null;
            if(matrizTccBean != null &&
               matrizTccBean.getIdEnmienda() != null){
                try{
                    idEnmienda = new Number(matrizTccBean.getIdEnmienda());
                }catch(Exception e){
                    LOGGER.warning("No se pudo convertir Long de Id Enmienda a objeto Number. Valor: " + matrizTccBean.getIdEnmienda());
                } 
            }
            
            if(idOperacion != null){
                Map params = null;
                params = new HashMap();
                params.put("idOperacion", idOperacion);
                params.put("idEnmienda", idEnmienda);
                executeOperBinding(params, INI_MATRIZ_TCC_GRID_OPER);
            }
        }
        
        LOGGER.warning("Finaliza metodo inicializaGridTccPorOperacion");
    }
    
    /**
     * Metodo para el evento de seleccion en la tabla de TCC
     * @param event contiene objeto evento
     */
    public void gridSelectionListener(SelectionEvent event){        
        LOGGER.warning("Entra a método gridSelectionListener");
        LOGGER.warning("Valor de evento: " + event.toString());
        MatrizTccBean matrizTccBean = null;
        Boolean esEstadoCompletado = Boolean.FALSE;
        
        matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
        esEstadoCompletado = matrizTccBean.getEsEstadoCompletado();
        LOGGER.warning("esEstadoCompletado :"+esEstadoCompletado);
                
        if(esEstadoCompletado){
             LOGGER.warning("No realizar ningun accion esEstadoCompletado es true , mostrar solo lectura tree");
        }else{
            LOGGER.warning("Flujo normal");    
            if(event == null){
                return;
            }
        
            boolean isDirty = ADFUtils.getDCBindingContainer().getDataControl().isTransactionDirty();
            //Previene la seleccion cuando el modelo de datos ha sido modificado y hay transacciones pendientes
            if(!isDirty){
                //Realiza la seleccion del current row programatica
                selectListenerTableUIC(event);
            
                ADFUtils.setBoundAttributeValue("editarTccAttrValue", false);
            
                //Forza la actualizacion del modelo
                event.getComponent().processUpdates(FacesContext.getCurrentInstance());
            
                boolean continuar = false;
                continuar = leeAsignaIdTccActivo();
                if(continuar){
                    continuar = leeAsignaIdTipoGestionTcc();
                    if(continuar){
                        //Refresca region para gestionar TCC
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getRegionGestionTccUIC());
                    
                        //Refresca los botones de acciones generales del Grid
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelButtonGrid());
                    }
                }
            }else{
                JSFUtils.addFacesErrorMessage(getStringFromBundle("transaccion.pendiente.confirmar"));
            }
            
        }
        
        LOGGER.warning("Fuera de método gridSelectionListener");
    }
    
    /**
     * Realiza la lectura de valores del elemento TCC actual y asigna su valor a variable bindings
     * @return devuelve valor booleano para indicar la correcta asignacion de valores
     */
    public boolean leeAsignaIdTccActivo(){
        
        boolean exito = false;
        Number idTcc = null;
        Integer idTipoTccSeleccionado = null;
        try{
            idTcc = (Number) ADFUtils.getBoundAttributeValue("IdTccVO");
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id del elemento TCC", e);
        }
        
        if(null != ADFUtils.getBoundAttributeValue("IdLinkVO")){
            try{
                idTipoTccSeleccionado = Integer.parseInt(ADFUtils.getBoundAttributeValue("IdLinkVO").toString());
            }catch(Exception e){
                LOGGER.severe("Error al obtener el Id del tipo de elemento TCC", e);
            }
        }
        
        if(idTcc != null){
            try{
                LOGGER.warning("Valor del TCC seleccionado: " + idTcc);
                ADFUtils.setBoundAttributeValue("IdTccAttrValue", idTcc);
                
                oracle.jbo.domain.Number idTccNumber = new Number(idTcc.intValue());
                MatrizTccBean matrizTCCBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
                LOGGER.warning("Seteando IdTccSeleccionado a MatrizTccBean");
                matrizTCCBean.setIdTccGridSeleccionado(idTccNumber);
                if(null != idTipoTccSeleccionado){
                    LOGGER.warning("Seteando idTipoTccSeleccionado a MatrizTccBean");
                    matrizTCCBean.setIdTcaTccSeleccionado(idTipoTccSeleccionado);
                } else {
                    LOGGER.warning("No se pudo recuperar el tipo de Tcc Seleccionado");
                }
                exito = true;
            }catch(Exception e){
                LOGGER.severe("Error no se pudo asignar el Id del elemento TCC", e);
            }
        }
        
        return exito;
    }
    
    /**
     * Realiza la lectura de valores del elemento TCC actual y asigna su valor a variable bindings
     * @return devuelve valor booleano que indica la correcta asignacion de valores
     */
    public boolean leeAsignaIdTipoGestionTcc(){
        
        boolean exito = false;
        Integer idTipoGestion = null;
        Number idLink = null;
        
        try{
            idLink = (Number) ADFUtils.getBoundAttributeValue("IdLinkVO");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener el identificador interno del tipo TCC", e);
        }
        
        Boolean editarTcc = null;
        try{
            editarTcc = (Boolean) ADFUtils.getBoundAttributeValue("editarTccAttrValue");
        }catch(Exception e){
            LOGGER.warning("No se pudo obtener indicador de edicion");
        }
        
        if(editarTcc == null){
            //Asigna valor por defecto al indicador de edicion
            editarTcc = false;
        }
        
        if(idLink != null){
            idTipoGestion = obtenerIdTipoGestion(idLink.intValue(), editarTcc);
            
            if(idTipoGestion != null){
                try{
                    ADFUtils.setBoundAttributeValue("IdTipoGestionTccAttrValue", idTipoGestion);    
                    exito = true;
                }catch(Exception e){
                    LOGGER.severe("Error no se pudo asignar el Id de Tipo de Gestion TCC", e);
                }
            }
        }
    
        return exito;
    }
    
    /**
     * Realiza la busqueda del Termino por Id antes de cargar la pantalla
     */
    @SuppressWarnings("unchecked")
    public void cargarPantallaTermino(){
        LOGGER.warning("Entra a metodo cargarPantallaTermino");
        
        TerminoManagedBean terminoManagedBean = (TerminoManagedBean)JSFUtils.resolveExpression("#{pageFlowScope.terminoManagedBean}");
        
        String strIdTcc = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcc}") != null){
            strIdTcc = JSFUtils.resolveExpression("#{pageFlowScope.pIdTcc}").toString();
        }
        
        if(strIdTcc != null){
            Map params = null;
            params = new HashMap();
            
            oracle.jbo.domain.Number id = null;
            try{
                id = new oracle.jbo.domain.Number(strIdTcc);
            }catch(Exception e){
                LOGGER.severe("Error al convertir el Id de TCC en Number", e);
            }
            
            Integer idModalidad = null;
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null){
                idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");    
            } else {
                LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");  
            }
            
            params.put("id", id);
            params.put("idModalidad", idModalidad);
            
            LOGGER.warning("Invocar metodo executeOperBinding");
            OperationBinding oper = executeOperBinding(params, "buscarTerminoPorId");
            
            if(oper.getErrors().isEmpty())
            {
              //params = new HashMap();
              //params.put("", 1);
                
              oper = null;
              oper = executeOperBinding(null, "asignarConfigTipoTermino");
              if(oper != null &&
                 !oper.getErrors().isEmpty()){
                  LOGGER.severe("Error al obtener y asignar configuracion del catalogo de tipo termino");
              }
              
              oper = executeOperBinding(params, "obtenerConfigucacionAtributosTcc");
               
              if(oper.getErrors().isEmpty())
              {
                  Map<String,Map> configuracionAtributosTCCMap = (Map<String,Map>) oper.getResult();
                  
                  if(null!=terminoManagedBean)
                  {
                    terminoManagedBean.setAtributeValues(configuracionAtributosTCCMap);
                  }
              }
              else
              {
                String msg = "Error al obtener configuracion de atributos TCC ";
                LOGGER.severe(msg);
                JSFUtils.addFacesErrorMessage(msg);
              }
              
              oper = null;
              oper = executeOperBinding(null, "validarTipoPorcentaje");
              
                if(oper.getErrors().isEmpty()){
                    Boolean mostrarPorcentajeUnico = null;
                    try{
                        mostrarPorcentajeUnico = (Boolean) oper.getResult();
                    }catch(Exception e){
                        LOGGER.warning("ERROR al recuprar el resultado del operBinding validarTipoPorcentaje.", e);
                    }
                    
                    if(null != terminoManagedBean){
                        terminoManagedBean.setMostrarPorcentajeUnico(mostrarPorcentajeUnico);
                    }
                }else{
                  String msg = "Error al validar el tipo de porcentaje. " + oper.getErrors().toString();
                  LOGGER.warning(msg);
                }
            }
            else
            {
                String msg = "Error al buscar los datos ralacionados con el Termino " + strIdTcc;
                LOGGER.severe(msg);
                JSFUtils.addFacesErrorMessage(msg);
            }
        }
    }

    /**
     * Realiza la busqueda de la Condicion por Id antes de cargar la pantalla
     */
    @SuppressWarnings("unchecked")
    public void cargarPantallaCondicion() {

        CondicionMangedBean condicionManagedBean =
            (CondicionMangedBean) JSFUtils.resolveExpression("#{pageFlowScope.condicionManagedBean}");

        String strIdTcc = null;
        if (JSFUtils.resolveExpression("#{pageFlowScope.pIdTcc}") != null) {
            strIdTcc = JSFUtils.resolveExpression("#{pageFlowScope.pIdTcc}").toString();
        }

        if (strIdTcc != null) {
            Map params = null;
            params = new HashMap();

            oracle.jbo.domain.Number id = null;
            try {
                id = new oracle.jbo.domain.Number(strIdTcc);
            } catch (Exception e) {
                LOGGER.severe("Error al convertir el Id de TCC en Number", e);
            }
            params.put("id", id);
            OperationBinding oper = executeOperBinding(params, "buscarCondicionPorId");
            if (oper != null) {
                if (oper.getErrors().isEmpty()) {

                    oper = null;
                    oper = executeOperBinding(null, "asignarConfigTipoCondicion");
                    if (oper != null && !oper.getErrors().isEmpty()) {
                        LOGGER.severe("Error al obtener y asignar configuracion del catalogo de tipo condicion");
                    }

                    Integer idModalidad = null;
                    if (JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null) {
                        idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");
                    } else {
                        LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");
                    }

                    Map inicializaCondParams = null;
                    inicializaCondParams = new HashMap();

                    inicializaCondParams.put("idModalidad", idModalidad);
                    LOGGER.warning("idModalidad para invocar inicializaTccCondicion: " + idModalidad);
                    OperationBinding operInicializa =
                        executeOperBinding(inicializaCondParams, "inicializaTccCondicion");
                    if (operInicializa != null) {

                        if (operInicializa.getErrors().isEmpty()) {
                            LOGGER.warning("Inicializacion de Condicion terminada sin errores");

                            /*
                     * hacer validacion de eventos para mostrar IdTcaTipoDesembolso
                     */
                            oper = null;
                            oper = executeOperBinding(null, "evaluarEventoDesembolso");
                            if (oper != null && !oper.getErrors().isEmpty()) {
                                LOGGER.severe("Error al evaluar eventos de desembolso para mostrar IdTcaTipoDesembolso.");
                            } else {
                                LOGGER.warning("Evaluando resultado de OperationBinding evaluarEventoDesembolso.");
                                Boolean existeEventoDesembolso = Boolean.FALSE;

                                try {
                                    existeEventoDesembolso = (Boolean) oper.getResult();
                                } catch (Exception e) {
                                    LOGGER.warning("ERROR al recuperar la respuesta del OperationBinding evaluarEventoDesembolso.",
                                                   e);
                                }

                                LOGGER.warning("Mostrar atributo IdTcaTipoDesembolso: " + existeEventoDesembolso);
                                if (null != condicionManagedBean) {
                                    condicionManagedBean.setMostrarIdTcaTipoDesembolso(existeEventoDesembolso);
                                }
                            }

                            OperationBinding operConfig = executeOperBinding(null, "obtenerConfigucacionAtributosTcc");
                            if (operConfig != null) {
                                if (operConfig.getErrors().isEmpty()) {

                                    Map<String, Map> configuracionAtributosTCCMap =
                                        (Map<String, Map>) operConfig.getResult();
                                    if (configuracionAtributosTCCMap != null) {
                                        if (null != condicionManagedBean) {
                                            condicionManagedBean.setAtributeValues(configuracionAtributosTCCMap);
                                        }
                                    } else {
                                        LOGGER.severe("Error no se obtuvo resultado de la configuracion de Atributos");
                                        String msg = getStringFromBundle("msg.error.configuracion.atributos");
                                        LOGGER.severe(msg);
                                        JSFUtils.addFacesErrorMessage(msg);
                                    }
                                } else {
                                    String msg = getStringFromBundle("msg.error.configuracion.atributos");
                                    LOGGER.severe(msg);
                                    JSFUtils.addFacesErrorMessage(msg);
                                }
                            }
                        } else {
                            LOGGER.severe("Inicializacion de Condicion tuvo error. Errores: " +
                                          operInicializa.getErrors().toString());
                        }
                    } else {
                        LOGGER.severe("Error al inicializar la condicion, operator binding no definido");
                    }
                } else {
                    LOGGER.severe("Error la busqueda de la Condicion tuvo errores");
                }
            } else {
                LOGGER.severe("Error la operacion de busqueda de Condicion no esta definida");
            }
        }
    }
    
    /**
     * Realiza la busqueda de la Comision por Id antes de cargar la pantalla
     */
    @SuppressWarnings("unchecked")
    public void cargarPantallaComision(){
               
        String strIdTcc = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcc}") != null)
        {
            strIdTcc = JSFUtils.resolveExpression("#{pageFlowScope.pIdTcc}").toString();
        }
        
        if(strIdTcc != null)
        {
            Map params = null;
            params = new HashMap();
            oracle.jbo.domain.Number id = null;
            try{
                id = new oracle.jbo.domain.Number(strIdTcc);
            }catch(Exception e){
                LOGGER.severe("Error al convertir el Id de TCC en Number", e);
            }
            params.put("id", id);
            OperationBinding oper = executeOperBinding(params, "buscarComisionPorId");
            
            if(oper.getErrors().isEmpty())
            {
              //params = new HashMap();
              //params.put("", 1);
                
              oper = null;
              oper = executeOperBinding(null, "asignarConfigTipoComision");
              if(oper != null &&
                 !oper.getErrors().isEmpty()){
                  LOGGER.severe("Error al obtener y asignar configuracion del catalogo de tipo comision");
              }
              
              oper = executeOperBinding(params, "obtenerConfigucacionAtributosTcc");
               
              if(oper.getErrors().isEmpty())
              {
                  Map<String,Map> configuracionAtributosTCCMap = (Map<String,Map>) oper.getResult();
                  
                  ComisionManagedBean comisionManagedBean = (ComisionManagedBean)JSFUtils.resolveExpression("#{pageFlowScope.comisionManagedBean}");
                  
                  if(null!=comisionManagedBean)
                  {
                    comisionManagedBean.setAtributeValues(configuracionAtributosTCCMap);
                  }
              }
              else
              {
                String msg = "Error al obtener configuracion de atributos TCC ";
                LOGGER.severe(msg);
                JSFUtils.addFacesErrorMessage(msg);
              }
               
            }
            else
            {
                String msg = "Error al bucar los datos ralacionados con la comision " + strIdTcc;
                LOGGER.severe(msg);
                JSFUtils.addFacesErrorMessage(msg);
            }
        }  
    }
    
    /**
     * Realiza la navegacion por outcome de taskflow correspondiente a la pantalla activa
     * @param event contiene evento
     * @param outcome contiene nombre del flujo
     */
    private void navigate(FacesEvent event, String outcome) {
        RichRegion regionComponent = null;
        
        for (UIComponent uic = event.getComponent().getParent(); uic != null; uic = uic.getParent()) {
            if (uic instanceof RichRegion) {
                regionComponent = (RichRegion) uic;
                break;
            }
        }
        
        if (regionComponent != null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExpressionFactory ef = fc.getApplication().getExpressionFactory();
            ELContext elc = fc.getELContext();
            MethodExpression me = 
                ef.createMethodExpression(elc, outcome, String.class, new Class[] { });
            regionComponent.queueActionEventInRegion(me, null, null, false, -1, -1, event.getPhaseId());
        }
    }
    
    /**
     * Ejecuta la busqueda del Proceso BPM por Id de Tarea, llamando metodo de busqueda e inicializando VO de consulta, 
     * la ejecucion del metodo retorna el id de Proceso BPM si es encontrado registro
     * @param idTareaBpm contiene id de tarea BPM
     * @return devuelve id del proceso BPM
     */
    @SuppressWarnings("unchecked")
    public Integer obtenerIdProcesoBPM(Integer idTareaBpm){
        
        LOGGER.entering(MattrizTccActionsBean.class.getName(), 
                        "obtenerIdProcesoBPM",
                        idTareaBpm);
        
        Integer idProcesoBpm = null;
        if(idTareaBpm != null){
            
            Map params = new HashMap();
            params.put("idTareaBpm", idTareaBpm);
            LOGGER.warning("Invoca metodo executeOperBinding para proceso");
            OperationBinding oper = executeOperBinding(params, BUSCAR_PROCESO_BPM_POR_TAREA_OPER);
            if(oper.getErrors().isEmpty()){
                idProcesoBpm = (Integer) oper.getResult();
                if(idProcesoBpm != null){
                    LOGGER.warning("Id de Proceso BPM retornado: " + idProcesoBpm);    
                }else{
                    LOGGER.warning("Id de Proceso BPM no retornado");
                }
            }
        }else{
            LOGGER.severe("El id de tarea bpm es NULL");
        }
        
        LOGGER.exiting(MattrizTccActionsBean.class.getName(), 
                       "obtenerIdProcesoBPM",
                       idProcesoBpm);
        LOGGER.warning("Retorna metodo obtenerIdProcesoBPM");
        return idProcesoBpm;
    }
    
    /**
     * Ejecuta la consulta del Proceso BPM correspondiente al Id de Tarea contenido en los parametros del Task Flow
     * @return devuelve id del proceso BPM
     */
    public Integer consultaProcesoBPMTaskFlow(){
        
        LOGGER.entering(MattrizTccActionsBean.class.getName(), 
                        "consultaProcesoBPMTaskFlow");
        
        Integer idProcesoBpm = null;
        Integer idTareaBpm = null;
        
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null
           && JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString().trim().length() > 0){
            try{
                idTareaBpm = Integer.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString());    
            }catch(Exception e){
                LOGGER.severe("Error al obtener Id de Tarea del parametro de Task Flow", e);
            }
        }else{
            LOGGER.severe("Error el valor del parametro de Id de Tarea BPM no esta definido");
        }
        
        idProcesoBpm = obtenerIdProcesoBPM(idTareaBpm);
        
        LOGGER.exiting(MattrizTccActionsBean.class.getName(), 
                       "consultaProcesoBPMTaskFlow",
                       idProcesoBpm);
        LOGGER.warning("retorno de metodo consultaProcesoBPMTaskFlow");
        return idProcesoBpm;
    }
        
    public void popupEliminarTerminoDialogListener(DialogEvent dialogEvent) {
        LOGGER.warning("Entrando en popupEliminarTerminoDialogListener.");
        boolean esExitoEliminarTermino = true;
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Obtiene el Id de Proceso BPM
                Integer idProcesoBpm = null;
                try{
                    idProcesoBpm = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm"); 
                }catch(Exception e){
                    LOGGER.warning("Error al obtener el valor del Atributo Id Proceso BPM de Bindings", e);
                }
                
                Integer idEstadoTcc = null;
                try{
                    //Obtiene el Id de Estado TCC
                    if(ADFUtils.findControlBinding("IdTcaEstadoTccAttrValue") != null){
                        idEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaEstadoTccAttrValue");    
                    }
                }catch(Exception e){
                    LOGGER.warning("No se obtuvo Atributo Id de Estado TCC");
                }
                
                Integer idSubEstadoTcc = null;
                try{
                    //Obtiene el Id de Sub Estado TCC
                    if(ADFUtils.findControlBinding("IdTcaSubEstadoTccAttrValue") != null){ 
                        idSubEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaSubEstadoTccAttrValue");    
                    }
                }catch(Exception e){
                    LOGGER.warning("No se obtuvo Atributo Id de Sub Estado TCC");
                }
                
                Integer idModalidad = null;
                if(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null){
                    idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");    
                } else {
                    LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");  
                }
                
                LOGGER.warning("idModalidad: " + idModalidad);
                
                if(idProcesoBpm != null){
                    if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm) || ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm) || ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm) || ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                        if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc) && 
                           idSubEstadoTcc == null){
                            LOGGER.warning("Llama metodo de eliminacion fisica de Termino");
                            esExitoEliminarTermino = eliminarTermino();
                        }else{
                            if ((ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm) || ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)) && (ID_MODALIDAD_GRID.equals(idModalidad))) {
                                LOGGER.warning("Llama metodo de eliminacion fisica de Termino");
                                esExitoEliminarTermino = eliminarTermino();
                            } else {
                                LOGGER.warning("Llama metodo de actualizarEstadoTermino");
                                // Actualizamos el estado del Término mediante el servicio actualizarTCC
                                esExitoEliminarTermino = actualizarEstadoTermino(Accion.ELIMINAR);
                            }
                        }
                    } else if (ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)) {
                        LOGGER.info("(Formalizacion de Enmiendas) Llama metodo de eliminacion fisica de Termino");
                        esExitoEliminarTermino = eliminarTermino();
                    } else{
                        LOGGER.warning("Llama metodo de actualizarEstadoTermino");
                        // Actualizamos el estado del Término mediante el servicio actualizarTCC
                        esExitoEliminarTermino = actualizarEstadoTermino(Accion.ELIMINAR); 
                    }
                }
                
                LOGGER.warning("esExitoEliminarTermino: " + esExitoEliminarTermino);
                
                if(esExitoEliminarTermino) {
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    // Invocamos mediante javascript el método que actualiza datos en la tarea de Ingresar enmienda.
                    try {
                        LOGGER.warning("pIdTareaBPM: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"));
                        LOGGER.warning("Parametros insuficientes para actualizar la enmienda");
                        
                        if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
                           && (Integer.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString()).intValue() 
                               == FenixConstants.PAC04_INGRESAR_ENMIENDA)) {
                            
                            ExtendedRenderKitService erks = Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                                        ExtendedRenderKitService.class);
                            erks.addScript(FacesContext.getCurrentInstance(), 
                                           "refreshDatosEnmienda('" + 
                                           JSFUtils.resolveExpression("#{pageFlowScope.pClientIdBtnRefreshEnmienda}") +"');");
                        } else {
                            LOGGER.warning("Parametros insuficientes para actualizar la enmienda");
                        }
                    } catch (Exception e) {
                        LOGGER.warning("Error en la actualizacion de enmienda: ", e);
                    }
                    
                    // Navegación para salir de flujo
                    navigate(dialogEvent, "goReturn");
                }
            }else{
                
                //Codigo que no realiza ninguna accion sobre el termino
            }
            this.refreshTccTree();
        } else {
            LOGGER.warning("DialogEvent es nulo.");
        }
    }
    
    public void popupEliminarCondicionDialogListener(DialogEvent dialogEvent) {
        boolean esExitoEliminarCondicion = true;
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Obtiene el Id de Proceso BPM
                Integer idProcesoBpm = null;
                try{
                    idProcesoBpm = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm"); 
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el valor del Atributo Id Proceso BPM de Bindings", e);
                }
                
                Integer idEstadoTcc = null;
                try{
                    //Obtiene el Id de Estado TCC
                    if(ADFUtils.findControlBinding("IdTcaEstadoTccAttrValue") != null){
                        idEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaEstadoTccAttrValue");    
                    }
                }catch(Exception e){
                    LOGGER.warning("No se obtuvo Atributo Id de Estado TCC");
                }
                
                Integer idSubEstadoTcc = null;
                try{
                    //Obtiene el Id de Sub Estado TCC
                    if(ADFUtils.findControlBinding("IdTcaSubEstadoTccAttrValue") != null){ 
                        idSubEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaSubEstadoTccAttrValue");    
                    }
                }catch(Exception e){
                    LOGGER.warning("No se obtuvo Atributo Id de Sub Estado TCC");
                }
                
                Integer idModalidad = null;
                if (JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null) {
                    idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");
                } else {
                    LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");
                }
                
                if(idProcesoBpm != null){
                    if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm) || ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm) || ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm) || ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm) || ID_PROCESO_EVALUACION_BPM.equals(idProcesoBpm) || ID_PROCESO_EVALUACION_SIEMAS_BPM.equals(idProcesoBpm)){
                        if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc) && 
                           idSubEstadoTcc == null){
                            LOGGER.warning("Llama método de eliminación física de Condición");
                            esExitoEliminarCondicion = eliminarCondicion();
                        }else{
                            if ((ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm) || ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)) && (ID_MODALIDAD_GRID.equals(idModalidad))) {
                                LOGGER.warning("Llama método de eliminación física de Condición");
                                esExitoEliminarCondicion = eliminarCondicion();
                            } else {
                                LOGGER.warning("Llama metodo de actualizarEstadoCondicion");
                                // Actualizamos el estado de la Condición mediante el servicio actualizarTCC
                                esExitoEliminarCondicion = actualizarEstadoCondicion(Accion.ELIMINAR);
                            }                             
                        }
                    } else if (ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)) {
                        LOGGER.info("(Formalizacion de Enmiendas) Llama método de eliminación física de Condición");
                        esExitoEliminarCondicion = eliminarCondicion();
                    } else{
                        // Actualizamos el estado de la Condición mediante el servicio actualizarTCC
                        esExitoEliminarCondicion = actualizarEstadoCondicion(Accion.ELIMINAR);
                    }
                }
                
                if(esExitoEliminarCondicion) {
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    // Invocamos mediante javascript el método que actualiza datos en la tarea de Ingresar enmienda.
                    if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
                       && (Integer.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString()).intValue() 
                           == FenixConstants.PAC04_INGRESAR_ENMIENDA)) {
                        
                        ExtendedRenderKitService erks = Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                                    ExtendedRenderKitService.class);
                        erks.addScript(FacesContext.getCurrentInstance(), 
                                       "refreshDatosEnmienda('" + 
                                       JSFUtils.resolveExpression("#{pageFlowScope.pClientIdBtnRefreshEnmienda}") +"');");
                    }
                    
                    navigate(dialogEvent, "goReturn");
                }
            }else{
                
                //Codigo que no realiza ninguna accion sobre el termino
            }
            this.refreshTccTree();
        }
    }
    
    public void popupEliminarComisionDialogListener(DialogEvent dialogEvent) {
        boolean esExitoEliminarComision = true;
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Obtiene el Id de Proceso BPM
                Integer idProcesoBpm = null;
                try{
                    idProcesoBpm = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm"); 
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el valor del Atributo Id Proceso BPM de Bindings", e);
                }
                
                Integer idEstadoTcc = null;
                try{
                    //Obtiene el Id de Estado TCC
                    if(ADFUtils.findControlBinding("IdTcaEstadoTccAttrValue") != null){
                        idEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaEstadoTccAttrValue");    
                    }
                }catch(Exception e){
                    LOGGER.warning("No se obtuvo Atributo Id de Estado TCC");
                }
                
                Integer idSubEstadoTcc = null;
                try{
                    //Obtiene el Id de Sub Estado TCC
                    if(ADFUtils.findControlBinding("IdTcaSubEstadoTccAttrValue") != null){ 
                        idSubEstadoTcc = (Integer) ADFUtils.getBoundAttributeValue("IdTcaSubEstadoTccAttrValue");    
                    }
                }catch(Exception e){
                    LOGGER.warning("No se obtuvo Atributo Id de Sub Estado TCC");
                }
                
                Integer idModalidad = null;
                if (JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null) {
                    idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");
                } else {
                    LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");
                }
                
                if(idProcesoBpm != null){                      
                    if(ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm) || ID_PROCESO_ANALISIS_BPM.equals(idProcesoBpm) || ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm) || ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)){
                        if(ID_ESTADO_TCC_NUEVA.equals(idEstadoTcc) && 
                           idSubEstadoTcc == null){
                            LOGGER.warning("Llama método de eliminación física de Comisión");
                            esExitoEliminarComision = eliminarComision();
                        }else{
                            if ((ID_PROCESO_ENMIENDAS_BPM.equals(idProcesoBpm) || ID_PROCESO_APROBACION_BPM.equals(idProcesoBpm)) && (ID_MODALIDAD_GRID.equals(idModalidad))) {
                                LOGGER.warning("Llama método de eliminación física de Comisión");
                                esExitoEliminarComision = eliminarComision();
                            } else {
                                // Actualizamos el estado de la Comisión mediante el servicio actualizarTCC
                                esExitoEliminarComision = actualizarEstadoComision(Accion.ELIMINAR);  
                            }
                        }
                    } else if (ID_PROCESO_FORMALIZACION_ENMIENDAS_BPM.equals(idProcesoBpm)) {
                        LOGGER.info("(Formalizacion de Enmiendas) Llama método de eliminación física de Comisión");
                        esExitoEliminarComision = eliminarComision();
                    } else{
                        // Actualizamos el estado de la Comisión mediante el servicio actualizarTCC
                        esExitoEliminarComision = actualizarEstadoComision(Accion.ELIMINAR);
                    }
                }
                                
                if(esExitoEliminarComision) {
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    // Invocamos mediante javascript el método que actualiza datos en la tarea de Ingresar enmienda.
                    if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
                       && (Integer.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString()).intValue() 
                           == FenixConstants.PAC04_INGRESAR_ENMIENDA)) {
                        
                        ExtendedRenderKitService erks = Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                                                                    ExtendedRenderKitService.class);
                        erks.addScript(FacesContext.getCurrentInstance(), 
                                       "refreshDatosEnmienda('" + 
                                       JSFUtils.resolveExpression("#{pageFlowScope.pClientIdBtnRefreshEnmienda}") +"');");
                    }
                    
                    // Reentramos al flujo de gestión para recargar los datos
                    navigate(dialogEvent, "goReturn");
                }
            }else{
                
                //Codigo que no realiza ninguna accion sobre el termino
            }
            this.refreshTccTree();
        }
    }

    @SuppressWarnings("unchecked")
    public void popupEditarAceptarTerminoDialogListener(DialogEvent dialogEvent){
        LOGGER.warning("Inicia metodo popupEditarAceptarTerminoDialogListener");
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                Boolean esValido = validarTerminos();
                
                if(esValido){
                    
                    String strIdInstPro = null;
                    String loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();
                    LOGGER.warning("Usuario obtenido => " + loginUsuario);
                    
                    //Asigna valor de Id Instancia de Proceso
                    try{
                        if(JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}") != null){
                            strIdInstPro = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}").toString();
                        }
                        
                        if(strIdInstPro != null){
                            ADFUtils.setBoundAttributeValue("IdInstanciaProceso", strIdInstPro);    
                        }else{
                            LOGGER.warning("El Id de Instancia de Proceso es NULL");
                        }
                    }catch(Exception e){
                        LOGGER.warning("El ID de Instancia de Proceso al Termino no fue asignado");
                    }
                    
                    LOGGER.warning("Invocando OperationBinding para guardarMatrizTCCEditarTermino");
                    //Codigo para guardar los cambios realizados
                    
                    Integer idModalidad = null;
                    if(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null){
                        idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");    
                    } else {
                        LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");  
                    }
                    
                    Map params = new HashMap();
                    params.put("idModalidad", idModalidad);
                    params.put("loginUsuario", loginUsuario);
                    OperationBinding  oper = executeOperBinding(params, "guardarMatrizTCCEditarTermino");
                    if(oper != null){
                        if(oper.getErrors().isEmpty()){
                            LOGGER.warning("Datos guardados correctamente");
                            
                            reiniciarMatrizTccModel();
                            
                            navigate(dialogEvent, "retornaVerTermino");
                            
                            try{
                                executeOperBinding(null, "retablecerListaIdContraparteEliminada");
                            }catch(Exception e){
                                LOGGER.severe("Error al ejecutar metodo retablecerListaIdContraparteEliminada :"+e);
                            }
                        }else{
                            reiniciarMatrizTccModel();
                            
                            LOGGER.severe("Error al guardar los datos");
                            String msg = getStringFromBundle("tcc.editar.termino.error.msg.guardar.editar");
                            JSFUtils.addFacesErrorMessage(msg);
                            JSFUtils.addFacesErrorMessage(oper.getErrors().toString());
                        }
                    }else{
                        LOGGER.severe("Error al guardar los datos, operator Binding no definido");
                        String msg = getStringFromBundle("tcc.editar.condicion.error.msg.operacion.no.definida");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                    this.refreshTccTree();
                }
            }
        }
        LOGGER.warning("Termina metodo popupEditarAceptarTerminoDialogListener");
    }
    
    public void popupEditarCancelarTerminoDialogListener(DialogEvent dialogEvent){
        
        if(dialogEvent != null){
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                //Codigo para cancelar los cambios realizados
                executeOperBinding(null, "Rollback");
                try{
                    executeOperBinding(null, "retablecerListaIdContraparteEliminada");
                }catch(Exception e){
                    LOGGER.severe("Error al ejecutar metodo retablecerListaIdContraparteEliminada :"+e);
                }
                reiniciarMatrizTccModel();
                this.refreshTccTree();
                navigate(dialogEvent, "retornaVerTermino");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void popupEditarAceptarCondicionDialogListener(DialogEvent dialogEvent){
        LOGGER.warning("Inicia metodo popupEditarAceptarCondicionDialogListener");
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                boolean esValido = false;
                esValido = validaGuardarEditarCondicion();
                
                //Codigo para guardar los cambios realizados
                if(esValido){
                    
                    Integer idModalidad = null;
                    if(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null){
                        idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");    
                    } else {
                        LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");  
                    }
                                        
                    Map params = new HashMap();
                    params.put("idModalidad", idModalidad);
                    
                    OperationBinding  oper = executeOperBinding(params, "guardarMatrizTCCEditarCondicion");
                    if(oper != null){
                        if(oper.getErrors().isEmpty()){
                            LOGGER.warning("Datos guardados correctamente");
                            //String msg = getStringFromBundle("confirmacion.guardar");
                            //JSFUtils.addFacesInformationMessage(msg);
                            
                            reiniciarMatrizTccModel();
                                
                            //Redirecciona la navegacion a la pantalla anterior
                            navigate(dialogEvent, "retornaVerCondicion");    
                            
                        }else{
                            reiniciarMatrizTccModel();
                            
                            LOGGER.severe("Error al guardar los datos");
                            String msg = getStringFromBundle("tcc.editar.condicion.error.msg.guardar.editar");
                            JSFUtils.addFacesErrorMessage(msg);
                        }
                    }else{
                        LOGGER.severe("Error al guardar los datos, operator Binding no definido");
                        String msg = getStringFromBundle("tcc.editar.condicion.error.msg.operacion.no.definida");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                    this.refreshTccTree();
                }
            }
        }
    }
    
    /**
     * Realiza la validacion de campos al editar una condicion y querer guardar los datos
     * @return devuelve valor booleano, true si la validacion es positiva o false en caso contrario
     */
    public boolean validaGuardarEditarCondicion(){
        
        LOGGER.entering(MattrizTccActionsBean.class.getName(), 
                        "validaGuardarEditarCondicion");
        
        boolean esValido = true;
        boolean esError = false;
        
        String msg = null;
        
        String nombre = null;
        String descripcion = null;
        Integer tipoControl = null;
        
        
        CondicionMangedBean condicionManagedBean = null;
        try{
            condicionManagedBean = 
                (CondicionMangedBean)JSFUtils.resolveExpression("#{pageFlowScope.condicionManagedBean}");    
        }catch(Exception e){
            LOGGER.severe("Error al obtener Managed Bean de Configuracion de Atributos", e);
        }
        
        if(condicionManagedBean != null){
            
            if(condicionManagedBean.getNombreCondicionVisible() != null &
               condicionManagedBean.getNombreCondicionVisible()){
                
                try{
                    nombre = (String) ADFUtils.getBoundAttributeValue("Nombre");
                    if(nombre != null){
                        nombre = nombre.trim();
                        if("".equals(nombre)){
                            msg = getStringFromBundle("tcc.editar.condicion.error.msg.nombre.invalido");
                            JSFUtils.addFacesErrorMessage(msg);
                            esValido = false;
                        }
                    }else{
                        msg = getStringFromBundle("tcc.editar.condicion.error.msg.nombre.requerido");
                        JSFUtils.addFacesErrorMessage(msg);
                        esValido = false;
                    }
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el campo Nombre", e);
                    esError = true;
                }
            }
            
            if(condicionManagedBean.getDescipcionCondicionVisible() != null &&
               condicionManagedBean.getDescipcionCondicionVisible()){
                
                try{
                    descripcion = (String) ADFUtils.getBoundAttributeValue("Descripcion");
                    if(descripcion != null){
                        descripcion = descripcion.trim();
                        if("".equals(descripcion)){
                            msg = getStringFromBundle("tcc.editar.condicion.error.msg.descripcion.valida");
                            JSFUtils.addFacesErrorMessage(msg);
                            esValido = false;
                        }
                    }else{
                        msg = getStringFromBundle("tcc.editar.condicion.error.msg.descripcion.requerido");
                        JSFUtils.addFacesErrorMessage(msg);
                        esValido = false;
                    }
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el campo Descripcion", e);
                    esError = true;
                }
            }
            
            if(condicionManagedBean.getIdTipocontrolVisible() != null &&
               condicionManagedBean.getIdTipocontrolVisible()){
               
                try{
                    tipoControl = (Integer) ADFUtils.getBoundAttributeValue("IdTcaControlCondicion");
                    if(tipoControl == null){
                        msg = getStringFromBundle("tcc.editar.condicion.error.msg.tipo.control.requerido");
                        JSFUtils.addFacesErrorMessage(msg);
                        esValido = false;
                    }
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el campo Id Tipo Control", e);
                    esError = true;
                }
            }
            
            if(!esError){
                if(esValido){
                    
                    if(condicionManagedBean.getCategoriaVisible() != null &&
                       condicionManagedBean.getCategoriaVisible()){
                        esValido = asignarCategoriasCondicionMultiseleccion();    
                    }
                    
                    if(esValido){
                        
                        if(condicionManagedBean.getIdTipocontrolVisible() != null &&
                           condicionManagedBean.getIdTipocontrolVisible()){
                            
                            if(isEsCalendarioCondicion()){
                                
                                if(condicionManagedBean.getIdTipoFechaInicioVisible() != null &&
                                   condicionManagedBean.getIdTipoFechaInicioVisible()){
                                    Integer tipoFechaInicio = null;
                                    try{
                                        tipoFechaInicio = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoFechaInicio");
                                        if(tipoFechaInicio == null){
                                            msg = getStringFromBundle("tcc.editar.condicion.error.msg.tipo.fecha.inicio.requerido");
                                            JSFUtils.addFacesErrorMessage(msg);
                                            esValido = false;
                                        }
                                    }catch(Exception e){
                                        LOGGER.severe("Error al obtener el campo Id Tipo Fecha Inicio", e);
                                        esError = true;
                                    }
                                }
                                
                                if(esValido &&
                                   !esError){
                                    
                                    Integer tipoPlazo = null;
                                    Integer plazo = null;
                                    java.sql.Timestamp fechaFin = null; //TODO verifica si se debe validar o se debe calcular
                                    
                                    if(condicionManagedBean.getIdTipoPlazoVisible() != null &&
                                       condicionManagedBean.getIdTipoPlazoVisible()){
                                        try{
                                            tipoPlazo = (Integer) ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo");
                                            if(tipoPlazo == null){
                                                msg = getStringFromBundle("tcc.editar.condicion.error.msg.tipo.plazo.requerido");
                                                JSFUtils.addFacesErrorMessage(msg);
                                                esValido = false;
                                            }
                                        }catch(Exception e){
                                            LOGGER.severe("Error al obtener el campo Tipo Plazo");
                                            esError = true;
                                        }
                                    }
                                    
                                    if(condicionManagedBean.getPlazoVisible() != null &&
                                       condicionManagedBean.getPlazoVisible()){
                                        try{
                                            plazo = (Integer) ADFUtils.getBoundAttributeValue("Plazo");
                                            if(plazo != null){
                                                if(plazo.intValue() <= 0){
                                                    msg = getStringFromBundle("tcc.editar.condicion.error.msg.plazo.invalido");
                                                    JSFUtils.addFacesErrorMessage(msg);
                                                    esValido = false;
                                                }
                                            }else{
                                                msg = getStringFromBundle("tcc.editar.condicion.error.msg.plazo.requerido");
                                                JSFUtils.addFacesErrorMessage(msg);
                                                esValido = false;
                                            }
                                        }catch(Exception e){
                                            LOGGER.severe("Error al obtener el campo Plazo", e);
                                            esError = true;
                                        }
                                    }
                                    
//                                    if(condicionManagedBean.getFechaFinalVisible() != null &&
//                                       condicionManagedBean.getFechaFinalVisible()){
//                                        try{
//                                            fechaFin = (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaFinal");
//                                            if(fechaFin == null){
//                                                msg = getStringFromBundle("tcc.editar.condicion.error.msg.fecha.final.requerido");
//                                                JSFUtils.addFacesErrorMessage(msg);
//                                                esValido = false;
//                                            }
//                                        }catch(Exception e){
//                                            LOGGER.severe("Error al obtener el campo Fecha Fin", e);
//                                            esError = true;
//                                        }
//                                    }
                                    
                                    if(esValido &&
                                       !esError){
                                        
                                        if(isEsFechaEspecificaCondicion()){
                                            
                                            if(condicionManagedBean.getFechaInicioVisible() != null &&
                                               condicionManagedBean.getFechaInicioVisible()){
                                                java.sql.Timestamp fechaInicio = null;
                                                try{
                                                    fechaInicio = 
                                                        (java.sql.Timestamp) ADFUtils.getBoundAttributeValue("FechaInicio");
                                                    if(fechaInicio == null){
                                                        msg = 
                                                            getStringFromBundle("tcc.editar.condicion.error.msg.fecha.inicio.requerido");
                                                        JSFUtils.addFacesErrorMessage(msg);
                                                        esValido = false;
                                                    }
                                                }catch(Exception e){
                                                    LOGGER.severe("Error al obtener el campo Fecha Inicio", e);
                                                    esError = true;
                                                }
                                            }
                                        } 
                                        
                                        LOGGER.warning("Limpia los Id de Evento Condicion");
                                        ADFUtils.setBoundAttributeValue("IdEventoCondList", null);
                                    }
                                }
                            }else{ 
                                
                                if(condicionManagedBean.getEventosVisible() != null &&
                                   condicionManagedBean.getEventosVisible()){
                                    esValido = asignarEventosCondicionMultiseleccion();   
                                    
                                    if(esValido){
                                        esValido = validarIdTcaTipoDesembolso();
                                        if(!esValido){
                                            LOGGER.warning("El campo idTcaTipoDesembolso es Requerido.");
                                            msg = getStringFromBundle("tcc.editar.condicion.error.msg.aplicable.a");
                                            JSFUtils.addFacesErrorMessage(msg);
                                            esValido = false;
                                        }
                                    }
                                }else{
                                    esValido = true;
                                }
                            }
                        }
                    }
                    
//                    if(condicionManagedBean.getObservacionesCondicionVisible() != null &&
//                       condicionManagedBean.getObservacionesCondicionVisible()){
//                        
//                        String observacion = null;
//                        try{
//                            observacion = (String) ADFUtils.getBoundAttributeValue("DescObservacionPrincipal");
//                            if(observacion != null){
//                                observacion = observacion.trim();
//                                if("".equals(observacion)){
//                                    msg = getStringFromBundle("tcc.editar.condicion.error.msg.observacion.principal.valida");
//                                    JSFUtils.addFacesErrorMessage(msg);
//                                    esValido = false;
//                                }
//                            }else{
//                                msg = getStringFromBundle("tcc.editar.condicion.error.msg.observacion.principal.requerida");
//                                JSFUtils.addFacesErrorMessage(msg);
//                                esValido = false;
//                            }
//                        }catch(Exception e){
//                            LOGGER.severe("Error al obtener el campo Observacion", e);
//                            esError = true;
//                        }
//                    }
                    
                    //Validar Fuentes //TODO verificar si aplica
                    //Validar Lineas de credito //TODO verificar si aplica
                }
            }else{
                esValido = false;
                msg = getStringFromBundle("tcc.editar.condicion.error.msg.validacion.error");
                LOGGER.severe("Asigna mensaje de error al validar: " + msg);
                JSFUtils.addFacesErrorMessage(msg);
            }
        }else{
            LOGGER.severe("Error no se pudo obtener Managed Bean de Configuracion de Atributos");
        }

        LOGGER.warning("Resultado de la validacion: " + esValido);
        
        LOGGER.exiting(MattrizTccActionsBean.class.getName(), 
                       "validaGuardarEditarCondicion", 
                       esValido);
        return esValido;
    }
    
    public void popupEditarCancelarCondicionDialogListener(DialogEvent dialogEvent){
        
        if(dialogEvent != null){
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                //Codigo para cancelar los cambios realizados
                executeOperBinding(null, "Rollback");
                
                reiniciarMatrizTccModel();
                this.refreshTccTree();
                navigate(dialogEvent, "retornaVerCondicion");    
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void popupEditarAceptarComisionDialogListener(DialogEvent dialogEvent){
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                if(comisionValida()){
                    
                    BindingContainer bindings = getBindings();
                    OperationBinding operationBinding = bindings.getOperationBinding("guardarMatrizTCCEditarComision");
                    
                    Integer idModalidad = null;
                    if(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null){
                        idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");    
                    } else {
                        LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");  
                    }
                                        
                    Map params = new HashMap();
                    params.put("idModalidad", idModalidad);
                    operationBinding.getParamsMap().putAll(params);
                    
                    operationBinding.execute();
                    //Codigo para guardar los cambios realizados
                    
                    if(operationBinding.getErrors().isEmpty()){
                        reiniciarMatrizTccModel();
                        
                        navigate(dialogEvent, "retornaVerComision");    
                    }else{
                        reiniciarMatrizTccModel();
                    }
                    this.refreshTccTree();
                }
            }
        }
    }
    
    public void popupEditarCancelarComisionDialogListener(DialogEvent dialogEvent){
        
        if(dialogEvent != null){
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                //Codigo para cancelar los cambios realizados
                executeOperBinding(null, "Rollback");
                
                reiniciarMatrizTccModel();
                this.refreshTccTree();
                navigate(dialogEvent, "retornaVerComision");
            }
        }
    }
       
    public void popupExceptuarTerminoDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus del Termino por Exceptuar
                if(actualizarEstadoTermino(Accion.EXCEPTUAR)) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerTermino");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupExceptuarCondicionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Condicion por Exceptuar
                if(actualizarEstadoCondicion(Accion.EXCEPTUAR)) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerCondicion");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupExceptuarComisionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Comision por Exceptuar
                if(actualizarEstadoComision(Accion.EXCEPTUAR)) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerComision");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupDispensarTerminoDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus del Termino por Dispensar
                if(actualizarEstadoTermino(Accion.DISPENSAR)) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerTermino");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupDispensarCondicionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Condicion por Dispensar
                if(actualizarEstadoCondicion(Accion.DISPENSAR)) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerCondicion");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupDispensarComisionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Comision por Dispensar
                if(actualizarEstadoComision(Accion.DISPENSAR)) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerComision");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupValidarTerminoDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus del Termino por Validar
                if(actualizarEstadoTermino(Accion.fromValue("VALIDAR"))) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerTermino");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupValidarCondicionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Condicion por Validar
                if(actualizarEstadoCondicion(Accion.fromValue("VALIDAR"))) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerCondicion");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupValidarComisionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Comision por Validar
                if(actualizarEstadoComision(Accion.fromValue("VALIDAR"))) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerComision");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupEliminarValidacionTerminoDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus del Termino por Eliminar Validacion
                if(actualizarEstadoTermino(Accion.fromValue("ELIMINAR_VALIDACION"))) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerTermino");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupEliminarValidacionCondicionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Condicion por Eliminar Validacion
                if(actualizarEstadoCondicion(Accion.fromValue("ELIMINAR_VALIDACION"))) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerCondicion");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupEliminarValidacionComisionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Comision por Eliminar Validacion
                if(actualizarEstadoComision(Accion.fromValue("ELIMINAR_VALIDACION"))) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerComision");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupPorValidarTerminoDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus del Termino Por Validar
                if(actualizarEstadoTermino(Accion.fromValue("POR_VALIDAR"))) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerTermino");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupPorValidarCondicionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Condicion Por Validar
                if(actualizarEstadoCondicion(Accion.fromValue("POR_VALIDAR"))) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerCondicion");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupPorValidarComisionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Comision Por Validar
                if(actualizarEstadoComision(Accion.fromValue("POR_VALIDAR"))) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerComision");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupPorEliminarTerminoDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus del Termino
                if(actualizarEstadoTermino(Accion.ELIMINAR)) { 

                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerTermino");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupPorEliminarCondicionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Condicion
                if(actualizarEstadoCondicion(Accion.ELIMINAR)) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerCondicion");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupPorEliminarComisionDialogListener(DialogEvent dialogEvent) {
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Codigo que actualiza el estatus de la Comision
                if(actualizarEstadoComision(Accion.ELIMINAR)) {
                
                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();
                    
                    //Refresca panel de acciones
                    if(getToolbarAccionesUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());    
                    }
                    if(getToolbarAccionesGridUIC() != null){
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC()); 
                    }
                    
                    navigate(dialogEvent, "retornaCargarVerComision");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupEliminarTccDialogListener(DialogEvent dialogEvent){
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                //Codigo que actualiza la TCC
                
                //Identifica el tipo de TCC
                
                //Ejecuta operacion para actualizar TCC
            }else{
                //Codigo que no realiza ninguna accion sobre la TCC
            }
        }
    }
    
    public void popupReactivarCondicionDialogListener(DialogEvent dialogEvent) {
        LOGGER.warning("Inside popupReactivarCondicionDialogListener.");

        if (dialogEvent != null) {

            if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {

                //Codigo que actualiza el estatus de la Condicion Por Validar
                if (actualizarEstadoCondicion(Accion.REACTIVAR)) {

                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();

                    //Refresca panel de acciones
                    if (getToolbarAccionesUIC() != null) {
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());
                    }
                    if (getToolbarAccionesGridUIC() != null) {
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC());
                    }

                    navigate(dialogEvent, "retornaCargarVerCondicion");
                }
            }
            this.refreshTccTree();
        }
    }
    
    public void popupDesactivarCondicionDialogListener(DialogEvent dialogEvent) {
        LOGGER.warning("Inside popupDesactivarCondicionDialogListener.");

        if (dialogEvent != null) {

            if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {

                //Codigo que actualiza el estatus de la Condicion Por Validar
                if (actualizarEstadoCondicion(Accion.INACTIVAR)) {

                    //Reinicia el Modelo de Matriz TCC
                    reiniciarMatrizTccModel();

                    //Refresca panel de acciones
                    if (getToolbarAccionesUIC() != null) {
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesUIC());
                    }
                    if (getToolbarAccionesGridUIC() != null) {
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getToolbarAccionesGridUIC());
                    }

                    navigate(dialogEvent, "retornaCargarVerCondicion");
                }
            }
            this.refreshTccTree();
        }
    }
    
    /**
     * Method to evalute if kind is expiration 
     * @return Boolean 
     */
    public void cambioFrecuencia(ValueChangeEvent vce) {
        Integer valorDefecto=1;
        Integer frecuenciaVencimiento=4;
        Integer valorTipoFrecuencia=(Integer)vce.getNewValue();
        AttributeBinding frecuencia;
        frecuencia = ADFUtils.findControlBinding("FrecuenciaPago");
        if(valorTipoFrecuencia.compareTo(frecuenciaVencimiento)==0){
                frecuencia.setInputValue(valorDefecto);
            }
        else{
             //   frecuencia.setInputValue(null);
            }


        /*
        Boolean respuesta = Boolean.TRUE;
        AttributeBinding tipoFrecuencia;
        tipoFrecuencia = ADFUtils.findControlBinding("IdTcaTipoFrecuencia");
        Integer value = 0;
        LOGGER.warning("Valor Frecuencia: "+ tipoFrecuencia.getInputValue());
        if(null != tipoFrecuencia.getInputValue() && !"".equalsIgnoreCase(tipoFrecuencia.getInputValue().toString())) {
            LOGGER.warning("Valor Frecuencia: "+ tipoFrecuencia.getInputValue().toString());
            value = (Integer)tipoFrecuencia.getInputValue();
            if(TIPO_VENCIMIENTO.compareTo(value)==0) {
                AttributeBinding frecuencia;
                frecuencia = ADFUtils.findControlBinding("FrecuenciaPago");
                frecuencia.setInputValue("");
                respuesta = Boolean.FALSE;
            }
            else{
                respuesta = Boolean.TRUE;
                }
        }
        LOGGER.warning("Respuesta: "+ respuesta);
        return respuesta;
*/
    }
    
    public String getEsTextoComision() {
            String respuesta = "Monto comisión:";
            AttributeBinding tipoValorTasa;
            tipoValorTasa = ADFUtils.findControlBinding("IdTcaTipoTasa");
            Integer value = 0;
            if(null != tipoValorTasa.getInputValue()) {
            value = (Integer)tipoValorTasa.getInputValue();
            if(TIPO_VALOR_MONTO.equals(value)) {
               respuesta = "Valor:";
            }
            }
            return respuesta;
        }
    
    /**
    * Method to evalute if kind is valor en comisiones
    * @return Boolean
    */
    public Boolean getEsTipoValor() {
    Boolean respuesta = true;
    AttributeBinding tipoValorTasa;
    tipoValorTasa = ADFUtils.findControlBinding("IdTcaTipoTasa");
    Integer value = 0;
    if(null != tipoValorTasa.getInputValue()) {
    value = (Integer)tipoValorTasa.getInputValue();
    if(TIPO_VALOR_MONTO.equals(value)) {
       respuesta = false;
    }
    }
    return respuesta;
    }
    
    public void cambioValorTasa(ValueChangeEvent vce) {
            LOGGER.warning("Porcentaje: "+ vce.getNewValue());
            AttributeBinding montoComision = ADFUtils.findControlBinding("MontoComision");
            if(null != vce && null != vce.getNewValue()) {
                BigDecimal nuevoValor = (BigDecimal)vce.getNewValue();
                if(nuevoValor.doubleValue() <= 0 || nuevoValor.doubleValue() >100) {
                    montoComision.setInputValue(BigDecimal.ZERO);
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_MSG_PORCENTAJE"));
                } else {
                    //preparacion.setPorcentajeBase(nuevoValor);
                    AttributeBinding porcentajeBase;
                    porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");
                    porcentajeBase.setInputValue(nuevoValor);
                }
            }
            montoComision.setInputValue(calculaComision());
        }

    public void cambioTasa(ValueChangeEvent vce) {
         AttributeBinding montoBase;
         montoBase = ADFUtils.findControlBinding("MontoBase");
         AttributeBinding montoComision;
         montoComision = ADFUtils.findControlBinding("MontoComision");
         AttributeBinding idMontoBase;
         idMontoBase = ADFUtils.findControlBinding("IdTcaMontoBase");
         AttributeBinding porcentajeBase;
         porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");

         Integer valorNulo = null;
         montoBase.setInputValue(null);
         porcentajeBase.setInputValue(null);
         montoComision.setInputValue(null);
         idMontoBase.setInputValue(valorNulo);
     }
    
    public void cambioMontoBase(ValueChangeEvent vce) {
            vce.getComponent().processUpdates(FacesContext.getCurrentInstance());
            Integer monedaDefault=1;
            AttributeBinding idMoneda;
            idMoneda = ADFUtils.findControlBinding("IdTcaMoneda");
            idMoneda.setInputValue(monedaDefault);
            
            AttributeBinding idMontoBaseComision;
            idMontoBaseComision = ADFUtils.findControlBinding("IdTcaMontoBase");
            
            AttributeBinding montoBase;
            montoBase = ADFUtils.findControlBinding("MontoBase");
            AttributeBinding porcentajeBase;
            porcentajeBase = ADFUtils.findControlBinding("PorcentajeSobreMontoBase");
            AttributeBinding montoComisionPay;
            montoComisionPay = ADFUtils.findControlBinding("MontoComision");
                    
            BigDecimal porcentajeComision=BigDecimal.ZERO;
            BigDecimal porcentaje = new BigDecimal("100");
            int valorMax = 1; 
            int valorMin = -1;
            if(null != porcentajeBase.getInputValue() && !"".equalsIgnoreCase(porcentajeBase.getInputValue().toString())) {
            porcentajeComision=(BigDecimal)porcentajeBase.getInputValue();
            valorMax=porcentajeComision.compareTo(porcentaje);
            valorMin=porcentajeComision.compareTo(BigDecimal.ZERO);
            }
            
            LOGGER.warning("Monto Solicitado: "+(BigDecimal) JSFUtils.resolveExpression("#{pageFlowScope.pMontoSolicitado}"));
        
            LOGGER.warning("Done"); 
            BigDecimal montoBaseD = BigDecimal.ZERO;
            BigDecimal montoComision;
            Integer montoAprobado=3;
            Integer montoFormalizado=4;
            LOGGER.warning("ID monto " + vce.getNewValue());
            Integer idMontoBase = (Integer) vce.getNewValue();
            idMontoBaseComision.setInputValue(idMontoBase);
            switch (idMontoBase){
            case 1:
                       LOGGER.warning("Monto Solicitado");
                       montoBaseD = (BigDecimal) JSFUtils.resolveExpression("#{pageFlowScope.pMontoSolicitado}");
                       montoBase.setInputValue(montoBaseD);
                       if(valorMax<=0 && valorMin>=0){
                          montoComisionPay.setInputValue(calculaComision());
                       }
                       else{
                           montoComisionPay.setInputValue(BigDecimal.ZERO);
                           JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_MSG_PORCENTAJE"));
                       }
                   break;
            case 2:
                       LOGGER.warning("Monto Aprobado");
                       montoBaseD=obtenerMontoBase(montoAprobado);
                       montoBase.setInputValue(montoBaseD);
                       if(valorMax<=0 && valorMin>=0){
                          montoComisionPay.setInputValue(calculaComision());
                       }
                       else{
                           montoComisionPay.setInputValue(BigDecimal.ZERO);
                           JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_MSG_PORCENTAJE"));
                       }
                       if(null==montoBaseD || montoBaseD.compareTo(BigDecimal.ZERO)==0){
                              // JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                               if(null==montoBaseD){
                                       montoBaseD=BigDecimal.ZERO;
                                   }
                               montoBase.setInputValue(montoBaseD);
                               montoComisionPay.setInputValue(calculaComision());
                           }
            break;
            case 3:
                       LOGGER.warning("Monto Formalizado");
                       montoBaseD=obtenerMontoBase(4);
                       montoBase.setInputValue(montoBaseD);
                       if(valorMax<=0 && valorMin>=0){
                          montoComisionPay.setInputValue(calculaComision());
                       }
                       else{
                           montoComisionPay.setInputValue(BigDecimal.ZERO);
                           JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_MSG_PORCENTAJE"));
                       }
                               if(null==montoBaseD || montoBaseD.compareTo(BigDecimal.ZERO)==0){
                                       //JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                               if(null==montoBaseD){
                                       montoBaseD=BigDecimal.ZERO;
                                   }
                                       montoBase.setInputValue(montoBaseD);
                                       montoComisionPay.setInputValue(calculaComision());
                           }
            break;
                   default:
                       //JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_CONFIRMA_NO_MONTO"));
                       montoBase.setInputValue(montoBaseD);
                       montoComisionPay.setInputValue(calculaComision());
                       break;
                   }
        }
        
        public BigDecimal obtenerMontoBase(Integer tipoMonto){
                BigDecimal resultado= BigDecimal.ZERO;
                BindingContainer bindings = getBindings();
                OperationBinding operationBinding = bindings.getOperationBinding("obtenerMontoPorTipo");
                operationBinding.getParamsMap().put("varTipoMonto",tipoMonto);
                operationBinding.execute();
                resultado=(BigDecimal)operationBinding.getResult();
                //guarda el monto base
                                  
                if(!operationBinding.getErrors().isEmpty()){
                resultado=BigDecimal.ZERO;
                }
                LOGGER.warning("Monto obtenido:" +resultado);
                return resultado;
            }
    /**
    * Method for calculate comision amount
    * @return
    */
    private BigDecimal calculaComision() {
    LOGGER.warning("calculaMontoComision()");

    AttributeBinding montoBase = ADFUtils.findControlBinding("MontoBase");
    AttributeBinding porcentajeBase= ADFUtils.findControlBinding("PorcentajeSobreMontoBase");

    LOGGER.warning("Monto base: "+ montoBase.getInputValue());
    LOGGER.warning("Porcentaje base: "+porcentajeBase.getInputValue());

    BigDecimal montoComision = new BigDecimal(0);
    BigDecimal porcentaje = new BigDecimal("100");
    if(null != porcentajeBase.getInputValue() && !"".equalsIgnoreCase(porcentajeBase.getInputValue().toString())){
        BigDecimal porcentajeComision = (BigDecimal)porcentajeBase.getInputValue();
    if(porcentajeComision.doubleValue() >0 && porcentajeComision.doubleValue()<100L) {
        if(null != montoBase && null != montoBase.getInputValue()) {
            BigDecimal monto = (BigDecimal)montoBase.getInputValue();
            if(monto.doubleValue() >0.00) {
                LOGGER.warning("porcentaje:"+ porcentajeComision);
                monto = monto.multiply(porcentajeComision);
                montoComision = monto.divide(porcentaje);
            }
        }
    }
    }
    LOGGER.warning("Monto comision calculado: " + montoComision);
    return montoComision.setScale(2, RoundingMode.CEILING);
    }
    
    /**
     * Realiza la seleccion por defecto de las listas de multiseleccion para formulario de Condicion en Modo Escritura 
     * para Editar la Condicion
     */
    public void asignarSeleccionCondicion(){
        
        Array values = null;
        List<Integer> seletedIndex = null;
        Integer idModalidad = null;
        
        idModalidad = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}").toString());
            
        if(null != idModalidad){
            LOGGER.warning("idModalidad: " + idModalidad);
            try{
                values = (Array) ADFUtils.getBoundAttributeValue("IdCategoriaCondList");    
            }catch(Exception e){
                LOGGER.severe("Error al obtener los valores de Id Categoria Condicion del registro de condicion Actual", e);
            }
            
            if(values != null &&
               values.getSize() > 0){
                
                LOGGER.warning("Valores de Categoria Condicion para seleccion: " + values.toString());
                LOGGER.warning("Cantidad de Categoria Condicion seleccionados: " + values.getSize());
                
                RowSetIterator iter = null;
                
                if(new Integer(1).equals(idModalidad)){
                    iter = ADFUtils.getDCBindingContainer().findIteratorBinding("TccTcaCategoriaCondicionVOIterator").getRowSetIterator();
                }else{
                    iter = ADFUtils.getDCBindingContainer().findIteratorBinding("TccTcaCategoriaCondicionGridVOIterator").getRowSetIterator();
                }
                
                
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
                                // ID =2 Es Primer desembolso
                                if(id.compareTo(2) == 0){
                                    Map params = null;
                                    params = new HashMap();
                                    params.put("idEvento", id);
                                    executeOperBinding(params, "setIdEvento");
                                    LOGGER.warning("Omitir el registro Contrato de Desembolso");
                                }else{
                                    Map params = null;
                                    params = new HashMap();
                                    params.put("idEvento", null);
                                    executeOperBinding(params, "setIdEvento");
                                    LOGGER.warning("No omitir el registro Contrato de Desembolso");
                                 }
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
                    if(new Integer(1).equals(idModalidad)){
                        listBindings = 
                            (JUCtrlListBinding)getBindings().get("TccTcaCategoriaCondicionVO");
                    } else {
                        listBindings = 
                            (JUCtrlListBinding)getBindings().get("TccTcaCategoriaCondicionGridVO");

                    }
                    
                    listBindings.setSelectedIndices(intSeletedIndex);
                }
            }
            
            seletedIndex = null;
            
            try{
                values = (Array) ADFUtils.getBoundAttributeValue("IdEventoCondList");    
            }catch(Exception e){
                LOGGER.severe("Error al obtener los valores de Id Evento Condicion del registro de condicion Actual", e);
            }
            
            if(values != null &&
               values.getSize() > 0){
                
                LOGGER.warning("Valores de Eventos Condicion para seleccion: " + values.toString());
                LOGGER.warning("Cantidad de Eventos Condicion seleccionados: " + values.getSize());
                
                RowSetIterator iter = null;
                
                if(new Integer(1).equals(idModalidad)){
                    iter = ADFUtils.getDCBindingContainer().findIteratorBinding("TccTcaEventoCondicionLOVIterator").getRowSetIterator();
                } else {
                    iter = ADFUtils.getDCBindingContainer().findIteratorBinding("TccTcaEventoCondicionGridLOVIterator").getRowSetIterator();
                }
                
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
                            
                            // ID =2 Es Primer desembolso
                            if(id.compareTo(2) == 0){
                                Map params = null;
                                params = new HashMap();
                                params.put("idEvento", id);
                                executeOperBinding(params, "setIdEvento");
                                LOGGER.warning("Omitir el registro Contrato de Desembolso");
                            }else{
                                Map params = null;
                                params = new HashMap();
                                params.put("idEvento", null);
                                executeOperBinding(params, "setIdEvento");
                                LOGGER.warning("No omitir el registro Contrato de Desembolso");
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
                    if(new Integer(1).equals(idModalidad)){
                        listBindings = 
                            (JUCtrlListBinding)getBindings().get("TccTcaEventoCondicionLOV");
                    } else {
                        listBindings = 
                            (JUCtrlListBinding)getBindings().get("TccTcaEventoCondicionGridLOV"); 
                    }
                    
                    listBindings.setSelectedIndices(intSeletedIndex);
                }
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

    @SuppressWarnings("unchecked")
    public boolean asignarCategoriasCondicionMultiseleccion(){
        
        boolean esError = false;
        String msgError = null;
            
        Number id = null;
        List idCategoriaCondList = null;
        
        JUCtrlListBinding listBindings = null;
        
        Integer idModalidad = null;
        
        try{
            idModalidad = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}").toString());
            if(null != idModalidad){
                
                if(new Integer(1).equals(idModalidad)){
                    listBindings = 
                        (JUCtrlListBinding)getBindings().get("TccTcaCategoriaCondicionVO");
                } else {
                    listBindings = 
                        (JUCtrlListBinding)getBindings().get("TccTcaCategoriaCondicionGridVO");
                }
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
                    esError = false;
                }else{
                    esError = true;
                    //Mensaje de campo requerido
                    msgError = getStringFromBundle("tcc.editar.condicion.error.msg.seleccion.categorias.requerida");
                }
            }
        }catch(Exception e){
            LOGGER.severe("Error al iterar las categorias de condicion seleccionadas", e);
            msgError = e.getMessage();
            esError = true;
        }
        
        if(!esError){
            Array arrayCategoriaList = new Array(idCategoriaCondList);
            ADFUtils.setBoundAttributeValue("IdCategoriaCondList", arrayCategoriaList);
        }
        
        if(msgError != null){
            String msg = getStringFromBundle("tcc.editar.condicion.error.msg.asignar.seleccion.categorias");
            JSFUtils.addFacesErrorMessage(msg);
            JSFUtils.addFacesErrorMessage(msgError);
        }
        
        return !esError;
    }

    @SuppressWarnings("unchecked")
    public boolean asignarEventosCondicionMultiseleccion(){
        
        boolean esError = false;
        String msgError = null;
        
        Number id = null;
        List idEventosCondList = null;
        
        Integer idModalidad = null;
        
        if(isEsEventoCondicion()){
            
            JUCtrlListBinding listBindings = null;
            try{
                idModalidad = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}").toString());
                if(null != idModalidad){
                    if(new Integer(1).equals(idModalidad)){    
                        listBindings = 
                            (JUCtrlListBinding)getBindings().get("TccTcaEventoCondicionLOV");
                    } else {
                        listBindings = 
                            (JUCtrlListBinding)getBindings().get("TccTcaEventoCondicionGridLOV");
                    }
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
                        esError = false;
                        
                        LOGGER.warning("Limpia los campos de Tipo Control Calendario");
                        ADFUtils.setBoundAttributeValue("IdTcaTipoFechaInicio", null);
                        ADFUtils.setBoundAttributeValue("IdTcaFrecuenciaPlazo", null);
                        ADFUtils.setBoundAttributeValue("Plazo", null);
                        ADFUtils.setBoundAttributeValue("FechaInicio", null);
                        ADFUtils.setBoundAttributeValue("FechaFinal", null);
                    }else{
                        esError = true;
                        //Mensaje de campo requerido
                        msgError = getStringFromBundle("tcc.editar.condicion.error.msg.seleccion.eventos.requerido");
                    }
                }
            }catch(Exception e){
                LOGGER.severe("Error al iterar los Eventos de condicion seleccionados", e);
                msgError = e.getMessage();
                esError = true;
            }
            
            if(!esError){
                Array arrayCategoriaList = new Array(idEventosCondList);
                ADFUtils.setBoundAttributeValue("IdEventoCondList", arrayCategoriaList);
            }
            
            if(msgError != null){
                String msg = getStringFromBundle("tcc.editar.condicion.error.msg.asignar.seleccion.eventos");
                JSFUtils.addFacesErrorMessage(msg);
                JSFUtils.addFacesErrorMessage(msgError);
            }
        }
        
        return !esError;
    }
    
    public Boolean getEsTerminoTipoPlazo(){
        LOGGER.warning("Inicia metodo getEsTerminoTipoPlazo");
        Boolean respuesta = Boolean.FALSE;
        AttributeBinding idTipoTermino;
        idTipoTermino = ADFUtils.findControlBinding("IdTipoTermino");
        if(null != idTipoTermino.getInputValue() && !"".equals(idTipoTermino.getInputValue().toString())){
            if(getStringFromBundle(TERMINO_TIPO_PLAZO).equals(idTipoTermino.getInputValue().toString())){
                LOGGER.warning("idTipoTermino" + idTipoTermino.getInputValue().toString());
                respuesta = Boolean.TRUE;
                AttributeBinding idTcaTermino;
                idTcaTermino =ADFUtils.findControlBinding("IdTcaTermino");
                
                if(null != idTcaTermino.getInputValue() && !"".equals(idTcaTermino.getInputValue().toString())){
                    LOGGER.warning("idTcaTermino: " + idTcaTermino.getInputValue().toString());
                    setMostrarNombre(Boolean.TRUE);
                    setMostrarDescripcion(Boolean.TRUE);
                    setMostrarTipoFechaInicio(Boolean.TRUE);
                    setMostrarFechaInicio(Boolean.TRUE);
                    setMostrarPlazo(Boolean.TRUE);
                    setMostrarTipoPlazo(Boolean.TRUE);
                    setMostrarFechaVencimiento(Boolean.TRUE);
                    
                    setLblNombre("LBL_NOMBRE_COMP");
                    setLblDescripcion(getStringFromBundle("LBL_DESCRIPCION_COMP"));
                    setLblFechaInicioPlazo(getStringFromBundle("LBL_FECHA_INICIO_COMP"));
                    setLblTipoFechaInicioPlazo(getStringFromBundle("LBL_TIPO_FECHA_INICIO_COMP"));
                    setLblplazo(getStringFromBundle("LBL_PLAZO_COMP"));
                    setLblTipoPlazo(getStringFromBundle("LBL_FRECUENCIA_PLAZO_COMP"));
                    setLblFechaVencimientoPlazo(getStringFromBundle("LBL_FECHA_VENCIMIENTO_COMP"));
                    
                    setReqNombre(Boolean.FALSE);
                    setReqDescripcion(Boolean.FALSE);
                    setReqTipoFechaInicio(Boolean.FALSE);
                    setReqFechaInicio(Boolean.FALSE);
                    setReqPlazo(Boolean.FALSE);
                    setReqTipoPlazo(Boolean.FALSE);
                    setReqFechaVencimiento(Boolean.FALSE);
                    
                    LOGGER.warning("Inicia seteo de etiquetas");
                    if(getStringFromBundle("TERMINO_PLAZO_FORMALIZACION").equals(idTcaTermino.getInputValue().toString()) 
                       || getStringFromBundle("TERMINO_PLAZO_INICIO_DESEMBOLSO").equals(idTcaTermino.getInputValue().toString())){
                           LOGGER.warning("Es subtipo Formalizacion o Inicio Desembolso");
                           setMostrarNombre(Boolean.FALSE);
                           
                           setReqTipoFechaInicio(Boolean.TRUE);
                           setReqPlazo(Boolean.TRUE);
                           setReqTipoPlazo(Boolean.TRUE);
                           setReqFechaInicio(Boolean.TRUE);
                       }
                    if(getStringFromBundle("TERMINO_PLAZO_DESEMBOLSO_TOTALIDAD").equals(idTcaTermino.getInputValue().toString())
                       || getStringFromBundle("TERMINO_PLAZO_PRESENTAR_INFORMES").equals(idTcaTermino.getInputValue().toString())){
                           LOGGER.warning("Es subtipo Desembolso de la totalidad o Para PresentarInformes");
                           setMostrarNombre(Boolean.FALSE);
                           setMostrarDescripcion(Boolean.FALSE);
                           
                           setReqTipoFechaInicio(Boolean.TRUE);
                           setReqPlazo(Boolean.TRUE);
                           setReqTipoPlazo(Boolean.TRUE);
                           setReqFechaInicio(Boolean.TRUE);
                       }
                    if(getStringFromBundle("TERMINO_PLAZO_LINEA").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo De la Línea");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        
                        setLblplazo(getStringFromBundle("LBL_PLAZO_LINEA_COMP"));
                        setLblTipoPlazo(getStringFromBundle("LBL_TIPO_PLAZO_LINEA_COMP"));
                        setLblFechaVencimientoPlazo(getStringFromBundle("LBL_FECHA_MAXIMA_COMP"));
                        
                        setReqTipoFechaInicio(Boolean.TRUE);
                        setReqPlazo(Boolean.TRUE);
                        setReqTipoPlazo(Boolean.TRUE);
                        setReqFechaInicio(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_PLAZO_FINANCIAMIENTO").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo De Financiamiento");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        setMostrarFechaVencimiento(Boolean.FALSE);
                        
                        setLblplazo(getStringFromBundle("LBL_PLAZO_FINANCIAMIENTO_COMP"));
                        setLblTipoPlazo(getStringFromBundle("LBL_TIPO_PLAZO_FINANCIAMIENTO_COMP"));
                        
                        setReqTipoFechaInicio(Boolean.TRUE);
                        setReqPlazo(Boolean.TRUE);
                        setReqTipoPlazo(Boolean.TRUE);
                        setReqFechaInicio(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_PLAZO_GARANTIA").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo De La Garantía");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        setMostrarFechaVencimiento(Boolean.FALSE);
                        setMostrarFechaInicio(Boolean.FALSE);
                        setMostrarTipoFechaInicio(Boolean.FALSE);
                        
                        setLblplazo(getStringFromBundle("LBL_PLAZO_GARANTIA_COMP"));
                        setLblTipoPlazo(getStringFromBundle("LBL_TIPO_PLAZO_GARANTIA_COMP"));
                        
                        setReqPlazo(Boolean.TRUE);
                        setReqTipoPlazo(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_PLAZO_PERIODO_GRACIA").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Período de Gracia");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        setMostrarFechaVencimiento(Boolean.FALSE);
                        
                        setLblplazo(getStringFromBundle("LBL_PERIODO_GRACIA_COMP"));
                        setLblTipoPlazo(getStringFromBundle("LBL_TIPO_PERIODO_GRACIA_COMP"));
                        
                        setReqPlazo(Boolean.TRUE);
                        setReqTipoPlazo(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINIO_PLAZO_ESPECIFICO").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Específico");
                        setMostrarFechaInicio(Boolean.FALSE);
                        setMostrarTipoFechaInicio(Boolean.FALSE);
                        setMostrarPlazo(Boolean.FALSE);
                        setMostrarTipoPlazo(Boolean.FALSE);
                        
                        setLblFechaVencimientoPlazo(getStringFromBundle("LBL_FECHA_COMP"));
                        
                        setReqNombre(Boolean.TRUE);
                        setReqDescripcion(Boolean.TRUE);
                        setReqFechaVencimiento(Boolean.TRUE);
                    }
                }else{
                    LOGGER.warning("idTcaTermino vacío"); 
                }
            }
        }else {
            LOGGER.warning("idTipoTermino vacío");
        }
        LOGGER.warning("Termina metodo getEsTerminoTipoPlazo");
        return respuesta;
    }
    
    public Boolean getEsTerminoTipoMonto(){
        LOGGER.warning("Inicia metodo getEsTerminoTipoMonto");
        Boolean respuesta = Boolean.FALSE;
        AttributeBinding idTipoTermino;
        idTipoTermino = ADFUtils.findControlBinding("IdTipoTermino");
        if(null != idTipoTermino.getInputValue() && !"".equals(idTipoTermino.getInputValue().toString())){
            if(getStringFromBundle(TERMINO_TIPO_MONTO).equals(idTipoTermino.getInputValue().toString())){
                LOGGER.warning("idTipoTermino" + idTipoTermino.getInputValue().toString());
                respuesta = Boolean.TRUE;
                AttributeBinding idTcaTermino;
                idTcaTermino =ADFUtils.findControlBinding("IdTcaTermino");
                
                if(null != idTcaTermino.getInputValue() && !"".equals(idTcaTermino.getInputValue().toString())){
                    LOGGER.warning("idTcaTermino: " + idTcaTermino.getInputValue().toString());
                    setMostrarNombre(Boolean.TRUE);
                    setMostrarDescripcion(Boolean.TRUE);
                    setMostrarMoneda(Boolean.TRUE);
                    setMostrarMonto(Boolean.TRUE);
                    
                    setLblNombre(getStringFromBundle("LBL_NOMBRE_COMP"));
                    setLblDescripcion(getStringFromBundle("LBL_DESCRIPCION_COMP"));
                    setLblMoneda(getStringFromBundle("LBL_MONEDA_COMP"));
                    setLblMonto(getStringFromBundle("LBL_MONTO_COMP"));
                    
                    setReqNombre(Boolean.FALSE);
                    setReqDescripcion(Boolean.FALSE);
                    setReqMoneda(Boolean.FALSE);
                    setReqMonto(Boolean.FALSE);
                    if(getStringFromBundle("TERMINO_MONTO_SOLICITADO").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Solicitado");
                        setMostrarNombre(Boolean.FALSE);
                        
                        setLblFechaVencimientoPlazo(getStringFromBundle("LBL_FECHA_COMP"));
                        
                        setReqMoneda(Boolean.TRUE);
                        setReqMonto(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_MONTO_APROBADO").equals(idTcaTermino.getInputValue().toString())
                       || getStringFromBundle("TERMINO_MONTO_FORMALIZADO").equals(idTcaTermino.getInputValue().toString())){
                           LOGGER.warning("Es subtipo Aprobado o Formalizado");
                           setMostrarNombre(Boolean.FALSE);
                           setMostrarDescripcion(Boolean.FALSE);
                           
                           setReqMoneda(Boolean.TRUE);
                           setReqMonto(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINIO_PLAZO_ESPECIFICO").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Específico");
                        setReqNombre(Boolean.TRUE);
                        setReqDescripcion(Boolean.TRUE);
                        setReqMoneda(Boolean.TRUE);
                        setReqMonto(Boolean.TRUE);
                    }
                }else{
                    LOGGER.warning("idTcaTermino vacío");
                }
            }
        }else{
            LOGGER.warning("idTipoTermino vacío");
        }
        LOGGER.warning("Termina metodo getEsTerminoTipoMonto");
        return respuesta;
    }
    
    public Boolean getEsTerminoTipoCondiciones(){
        LOGGER.warning("Entra a metodo getEsTerminoTipoCondiciones");
        Boolean respuesta = Boolean.FALSE;
        AttributeBinding idTipoTermino;
        idTipoTermino = ADFUtils.findControlBinding("IdTipoTermino");
        if(null != idTipoTermino.getInputValue() && !"".equals(idTipoTermino.getInputValue().toString())){
            if(getStringFromBundle(TERMINO_TIPO_CONDICIONES).equals(idTipoTermino.getInputValue().toString())){
                LOGGER.warning("idTipoTermino" + idTipoTermino.getInputValue().toString());
                respuesta = Boolean.TRUE;
                AttributeBinding idTcaTermino;
                idTcaTermino =ADFUtils.findControlBinding("IdTcaTermino");
                
                if(null != idTcaTermino.getInputValue() && !"".equals(idTcaTermino.getInputValue().toString())){
                    LOGGER.warning("idTcaTermino: " + idTcaTermino.getInputValue().toString());
                    setMostrarNombre(Boolean.TRUE);
                    setMostrarDescripcion(Boolean.TRUE);
                    setMostrartasa(Boolean.TRUE);
                    setMostrarTipoTasa(Boolean.TRUE);
                    setMostrarFrecuenciaRevision(Boolean.TRUE);
                    setMostrarTipoFrecRevision(Boolean.TRUE);
                    setMostrarFechaInicioRevision(Boolean.TRUE);
                    setMostrarFrecPagoInteres(Boolean.TRUE);
                    setMostrarTipoFrecPagoInteres(Boolean.TRUE);
                    setMostrarFechaInicioPagoInteres(Boolean.TRUE);
                    setMostrarFrecAmortizacion(Boolean.TRUE);
                    setMostrarTipoFrecAmortizacion(Boolean.TRUE);
                    setMostrarMora(Boolean.TRUE);
                    setMostrarCobertura(Boolean.TRUE);
                    setMostrarConcesionales(Boolean.TRUE);
                    setMostrarExternos(Boolean.TRUE);
                                        
                    setLblNombre(getStringFromBundle("LBL_NOMBRE_COMP"));
                    setLblDescripcion(getStringFromBundle("LBL_DESCRIPCION_COMP"));
                    setLblTasa(getStringFromBundle("LBL_TASA_COMP"));
                    setLblTipoTasa(getStringFromBundle("LBL_TIPO_TASA_COMP"));
                    setLblFrecuenciaRevision(getStringFromBundle("LBL_FRECUENCIA_REVISION_COMP"));
                    setLblTipoFrecRevision(getStringFromBundle("LBL_TIPO_FRECUENCIA_REVISION_COMP"));
                    setLblFechaInicioRevision(getStringFromBundle("LBL_FECHA_INICIO_REVISION_COMP"));
                    setLblFrecPagoInteres(getStringFromBundle("LBL_FRECUENCIA_PAGO_INTERES_COMP"));
                    setLblTipoFrecPagoInteres(getStringFromBundle("LBL_TIPO_FRECUENCIA_PAGO_INTERES_COMP"));
                    setLblFechaInicioPagoInteres(getStringFromBundle("LBL_FECHA_INICIO_PAGO_INTERES_COMP"));
                    setLblFrecAmortizacion(getStringFromBundle("LBL_FRECUENCIA_AMORTIZACION_COMP"));
                    setLblTipoFrecAmortizacion(getStringFromBundle("LBL_TIPO_FRECUENCIA_AMORTIZACION_COMP"));
                    setLblMora(getStringFromBundle("LBL_MORA_COMP"));
                    setLblCobertura(getStringFromBundle("LBL_PORCENTAJE_COBERTURA_COMP"));
                    setLblConcesionales(getStringFromBundle("LBL_APLICAN_RECURSOS_CONCESION_COMP"));
                    setLblExternos(getStringFromBundle("LBL_APLICAN_RECURSOS_EXTERNOS_COMP"));
                    
                    setReqNombre(Boolean.FALSE);
                    setReqDescripcion(Boolean.FALSE);
                    setReqtasa(Boolean.FALSE);
                    setReqTipoTasa(Boolean.FALSE);
                    setReqFrecuenciaRevision(Boolean.FALSE);
                    setReqTipoFrecRevision(Boolean.FALSE);
                    setReqFechaInicioRevision(Boolean.FALSE);
                    setReqFrecPagoInteres(Boolean.FALSE);
                    setReqTipoFrecPagoInteres(Boolean.FALSE);
                    setReqFechaInicioPagoInteres(Boolean.FALSE);
                    setReqFrecAmortizacion(Boolean.FALSE);
                    setReqTipoFrecAmortizacion(Boolean.FALSE);
                    setReqMora(Boolean.FALSE);
                    setReqCobertura(Boolean.FALSE);
                    setReqConcesionales(Boolean.FALSE);
                    setReqExternos(Boolean.FALSE);
                    
                    if(getStringFromBundle("TERMINO_CONDICIONES_TASA_INTERES").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Tasa de Interés");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarMora(Boolean.FALSE);
                        setMostrarCobertura(Boolean.FALSE);
                        setMostrarConcesionales(Boolean.FALSE);
                        setMostrarExternos(Boolean.FALSE);
                        
                        setReqtasa(Boolean.TRUE);
                        setReqTipoTasa(Boolean.TRUE);
                        setReqFrecPagoInteres(Boolean.TRUE);
                        setReqTipoFrecPagoInteres(Boolean.TRUE);
                        setReqFechaInicioPagoInteres(Boolean.TRUE);
                        setReqFrecAmortizacion(Boolean.TRUE);
                        setReqTipoFrecAmortizacion(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONDICIONES_MORA").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Mora");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        setMostrartasa(Boolean.FALSE);
                        setMostrarTipoTasa(Boolean.FALSE);
                        setMostrarFrecuenciaRevision(Boolean.FALSE);
                        setMostrarTipoFrecRevision(Boolean.FALSE);
                        setMostrarFechaInicioRevision(Boolean.FALSE);
                        setMostrarFrecPagoInteres(Boolean.FALSE);
                        setMostrarTipoFrecPagoInteres(Boolean.FALSE);
                        setMostrarFechaInicioPagoInteres(Boolean.FALSE);
                        setMostrarFrecAmortizacion(Boolean.FALSE);
                        setMostrarTipoFrecAmortizacion(Boolean.FALSE);
                        setMostrarCobertura(Boolean.FALSE);
                        setMostrarConcesionales(Boolean.FALSE);
                        setMostrarExternos(Boolean.FALSE);
                        
                        setReqMora(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONDICIONES_PORCENTAJE_COBERTURA").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Porcentaje de Cobertura");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        setMostrartasa(Boolean.FALSE);
                        setMostrarTipoTasa(Boolean.FALSE);
                        setMostrarFrecuenciaRevision(Boolean.FALSE);
                        setMostrarTipoFrecRevision(Boolean.FALSE);
                        setMostrarFechaInicioRevision(Boolean.FALSE);
                        setMostrarFrecPagoInteres(Boolean.FALSE);
                        setMostrarTipoFrecPagoInteres(Boolean.FALSE);
                        setMostrarFechaInicioPagoInteres(Boolean.FALSE);
                        setMostrarFrecAmortizacion(Boolean.FALSE);
                        setMostrarTipoFrecAmortizacion(Boolean.FALSE);
                        setMostrarMora(Boolean.FALSE);
                        setMostrarConcesionales(Boolean.FALSE);
                        setMostrarExternos(Boolean.FALSE);
                        
                        setReqCobertura(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONDICIONES_CONCESIONALES").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Recursos Concesionales");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        setMostrartasa(Boolean.FALSE);
                        setMostrarTipoTasa(Boolean.FALSE);
                        setMostrarFrecuenciaRevision(Boolean.FALSE);
                        setMostrarTipoFrecRevision(Boolean.FALSE);
                        setMostrarFechaInicioRevision(Boolean.FALSE);
                        setMostrarFrecPagoInteres(Boolean.FALSE);
                        setMostrarTipoFrecPagoInteres(Boolean.FALSE);
                        setMostrarFechaInicioPagoInteres(Boolean.FALSE);
                        setMostrarFrecAmortizacion(Boolean.FALSE);
                        setMostrarTipoFrecAmortizacion(Boolean.FALSE);
                        setMostrarMora(Boolean.FALSE);
                        setMostrarCobertura(Boolean.FALSE);
                        setMostrarExternos(Boolean.FALSE);
                        
                        setReqConcesionales(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONDICIONES_EXTERNOS").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Recursos Externos");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        setMostrartasa(Boolean.FALSE);
                        setMostrarTipoTasa(Boolean.FALSE);
                        setMostrarFrecuenciaRevision(Boolean.FALSE);
                        setMostrarTipoFrecRevision(Boolean.FALSE);
                        setMostrarFechaInicioRevision(Boolean.FALSE);
                        setMostrarFrecPagoInteres(Boolean.FALSE);
                        setMostrarTipoFrecPagoInteres(Boolean.FALSE);
                        setMostrarFechaInicioPagoInteres(Boolean.FALSE);
                        setMostrarFrecAmortizacion(Boolean.FALSE);
                        setMostrarTipoFrecAmortizacion(Boolean.FALSE);
                        setMostrarMora(Boolean.FALSE);
                        setMostrarCobertura(Boolean.FALSE);
                        setMostrarConcesionales(Boolean.FALSE);
                        
                        setReqExternos(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONDICIONES_ESPECIFICA").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Específica");
                        setMostrartasa(Boolean.FALSE);
                        setMostrarTipoTasa(Boolean.FALSE);
                        setMostrarFrecuenciaRevision(Boolean.FALSE);
                        setMostrarTipoFrecRevision(Boolean.FALSE);
                        setMostrarFechaInicioRevision(Boolean.FALSE);
                        setMostrarFrecPagoInteres(Boolean.FALSE);
                        setMostrarTipoFrecPagoInteres(Boolean.FALSE);
                        setMostrarFechaInicioPagoInteres(Boolean.FALSE);
                        setMostrarFrecAmortizacion(Boolean.FALSE);
                        setMostrarTipoFrecAmortizacion(Boolean.FALSE);
                        setMostrarMora(Boolean.FALSE);
                        setMostrarCobertura(Boolean.FALSE);
                        setMostrarConcesionales(Boolean.FALSE);
                        setMostrarExternos(Boolean.FALSE);
                        
                        setReqNombre(Boolean.TRUE);
                        setReqDescripcion(Boolean.TRUE);
                    }
                }else{
                    LOGGER.warning("idTcaTermino vacío"); 
                }
            }
        }else{
            LOGGER.warning("idTipoTermino vacío");
        }
        LOGGER.warning("Termina metodo getEsTerminoTipoCondiciones");
        return respuesta;
    }
    
    public Boolean getEsTerminoTipoContraparte(){
        LOGGER.warning("Entra a metodo getEsTerminoTipoContraparte");
        Boolean respuesta = Boolean.FALSE;
        AttributeBinding idTipoTermino;
        idTipoTermino = ADFUtils.findControlBinding("IdTipoTermino");
        if(null != idTipoTermino.getInputValue() && !"".equals(idTipoTermino.getInputValue().toString())){
            if(getStringFromBundle(TERMINO_TIPO_CONTRAPARTES).equals(idTipoTermino.getInputValue().toString())){
                LOGGER.warning("idTipoTermino" + idTipoTermino.getInputValue().toString());
                respuesta = Boolean.TRUE;
                AttributeBinding idTcaTermino;
                idTcaTermino =ADFUtils.findControlBinding("IdTcaTermino");
                
                if(null != idTcaTermino.getInputValue() && !"".equals(idTcaTermino.getInputValue().toString())){
                    LOGGER.warning("idTcaTermino: " + idTcaTermino.getInputValue().toString());
                    setMostrarNombre(Boolean.TRUE);
                    setMostrarDescripcion(Boolean.TRUE);
                    setMostrarNombreContraparte(Boolean.TRUE);
                    setMostrarTipoContraparte(Boolean.TRUE);
                    
                    setLblNombre(getStringFromBundle("LBL_NOMBRE_TERMINO_COMP"));
                    setLblNombreContraparte(getStringFromBundle("LBL_NOMBRE_CONTRAPARTE_COMP"));
                    setLblTipoContraparte(getStringFromBundle("LBL_TIPO_CONTRAPARTE_COMP"));
                    setLblDescripcion(getStringFromBundle("LBL_DESCRIPCION_COMP"));
                    
                    setReqNombre(Boolean.FALSE);
                    setReqNombreContraparte(Boolean.FALSE);
                    setReqTipoContraparte(Boolean.FALSE);
                    setReqDescripcion(Boolean.FALSE);
                    if(getStringFromBundle("TERMINO_CONTRAPARTE_ESTRUCTURADOR").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Estructurador");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarTipoContraparte(Boolean.FALSE);
                        
                        setLblNombreContraparte(getStringFromBundle("LBL_AGENTE_ESTRUCTURADOR_COMP"));
                        setReqNombreContraparte(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONTRAPARTE_BENEFICIARIOS").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Beneficiarios");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarTipoContraparte(Boolean.FALSE);
                        
                        setLblNombreContraparte(getStringFromBundle("LBL_BENEFICIARIO_COMP"));
                        setReqNombreContraparte(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONTRAPARTE_ADMINISTRADOR").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Administrador");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarTipoContraparte(Boolean.FALSE);
                        
                        setLblNombreContraparte(getStringFromBundle("LBL_AGENTE_ADMINISTRADOR_COMP"));
                        setReqNombreContraparte(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONTRAPARTE_PARTICIPANTES").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Participantes");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarTipoContraparte(Boolean.FALSE);
                        
                        setLblNombreContraparte(getStringFromBundle("LBL_PARTICIPANTE_COMP"));
                        setReqNombreContraparte(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONTRAPARTE_FINANCIADORES").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Financiadores");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarTipoContraparte(Boolean.FALSE);
                        
                        setLblNombreContraparte(getStringFromBundle("LBL_COFINANCIADOR_COMP"));
                        setReqNombreContraparte(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_CONTRAPARTE_ESPECIFICAS").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Específicas");
                        setReqNombre(Boolean.TRUE);
                        setReqNombreContraparte(Boolean.TRUE);
                        setReqDescripcion(Boolean.TRUE);
                    }
                }else{
                    LOGGER.warning("idTcaTermino vacío"); 
                }
            }
        }else{
            LOGGER.warning("idTipoTermino vacío");
        }
        LOGGER.warning("Termina metodo getEsTerminoTipoContraparte");
        return respuesta;
    }
    
    public Boolean getEsTerminoTipoRestriccion(){
        LOGGER.warning("Entra a metodo getEsTerminoTipoRestriccion");
        Boolean respuesta = Boolean.FALSE;
        AttributeBinding idTipoTermino;
        idTipoTermino = ADFUtils.findControlBinding("IdTipoTermino");
        if(null != idTipoTermino.getInputValue() && !"".equals(idTipoTermino.getInputValue().toString())){
            if(getStringFromBundle(TERMINO_TIPO_RESTRICCIONES).equals(idTipoTermino.getInputValue().toString())){
                LOGGER.warning("idTipoTermino" + idTipoTermino.getInputValue().toString());
                respuesta = Boolean.TRUE;
                AttributeBinding idTcaTermino;
                idTcaTermino =ADFUtils.findControlBinding("IdTcaTermino");
                
                if(null != idTcaTermino.getInputValue() && !"".equals(idTcaTermino.getInputValue().toString())){
                    LOGGER.warning("idTcaTermino: " + idTcaTermino.getInputValue().toString());
                    setMostrarNombre(Boolean.TRUE);
                    setMostrarDescripcion(Boolean.TRUE);
                    setMostrarMontoMinimo(Boolean.TRUE);
                    setMostrarMontoMaximo(Boolean.TRUE);
                    setMostrarTasaMinima(Boolean.TRUE);
                    setMostrarTasaMaxima(Boolean.TRUE);
                    
                    setLblNombre(getStringFromBundle("LBL_NOMBRE_COMP"));
                    setLblDescripcion(getStringFromBundle("LBL_DESCRIPCION_COMP"));
                    setLblMontoMinimo(getStringFromBundle("LBL_MONTO_MINIMO_DESEMBOLSO_COMP"));
                    setLblMontoMaximo(getStringFromBundle("LBL_MONTO_MAXIMO_DESEMBOLSO_COMP"));
                    setLblTasaMinima(getStringFromBundle("LBL_TASA_MINIMA_DESEMBOLSO_COMP"));
                    setLblTasaMaxima(getStringFromBundle("LBL_TASA_MAXIMA_DESEMBOLSO_COMP"));
                    
                    setReqNombre(Boolean.FALSE);
                    setReqDescripcion(Boolean.FALSE);
                    setReqMontoMinimo(Boolean.FALSE);
                    setReqMontoMaximo(Boolean.FALSE);
                    setReqTasaMinima(Boolean.FALSE);
                    setReqTasaMaxima(Boolean.FALSE);
                    if(getStringFromBundle("TERMINO_RESTRICCION_DESEMBOLSO").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo En Desembolsos");
                        setMostrarNombre(Boolean.FALSE);
                        
                        setReqMontoMinimo(Boolean.TRUE);
                        setReqMontoMaximo(Boolean.TRUE);
                        setReqTasaMinima(Boolean.TRUE);
                        setReqTasaMaxima(Boolean.TRUE);
                    } else {
                        LOGGER.warning("Es subtipo Específicas");
                        setReqNombre(Boolean.TRUE);
                        setReqDescripcion(Boolean.TRUE);
                    }
                }else{
                    LOGGER.warning("idTcaTermino vacío"); 
                }
            }
        }else{
            LOGGER.warning("idTipoTermino vacío");
        }
        LOGGER.warning("Termina metodo getEsTerminoTipoRestriccion");
        return respuesta;
    }
    
    public Boolean getEsTerminoTipoFecha(){
        LOGGER.warning("Entra a metodo getEsTerminoTipoFecha");
        Boolean respuesta = Boolean.FALSE;
        AttributeBinding idTipoTermino;
        idTipoTermino = ADFUtils.findControlBinding("IdTipoTermino");
        if(null != idTipoTermino.getInputValue() && !"".equals(idTipoTermino.getInputValue().toString())){
            if(getStringFromBundle(TERMINO_TIPO_FECHA).equals(idTipoTermino.getInputValue().toString())){
                LOGGER.warning("idTipoTermino" + idTipoTermino.getInputValue().toString());
                respuesta = Boolean.TRUE;
                AttributeBinding idTcaTermino;
                idTcaTermino =ADFUtils.findControlBinding("IdTcaTermino");
                
                if(null != idTcaTermino.getInputValue() && !"".equals(idTcaTermino.getInputValue().toString())){
                    LOGGER.warning("idTcaTermino: " + idTcaTermino.getInputValue().toString());
                    setMostrarNombre(Boolean.TRUE);
                    setMostrarDescripcion(Boolean.TRUE);
                    setMostrarFecha(Boolean.TRUE);
                    
                    setLblNombre(getStringFromBundle("LBL_NOMBRE_COMP"));
                    setLblDescripcion(getStringFromBundle("LBL_DESCRIPCION_COMP"));
                    setLblFecha(getStringFromBundle("LBL_FECHA_COMP"));
                    
                    setReqNombre(Boolean.FALSE);
                    setReqDescripcion(Boolean.FALSE);
                    setReqFecha(Boolean.FALSE);
                    if(getStringFromBundle("TERMINO_FECHAS_INGRESO").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Ingreso de la Operacion");
                        setMostrarNombre(Boolean.FALSE);
                        
                        setLblFecha(getStringFromBundle("LBL_FECHA_INGRESO_COMP"));
                        
                        setReqFecha(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_FECHAS_APROBACION").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Aprobación");
                        setMostrarNombre(Boolean.FALSE);
                        
                        setLblFecha(getStringFromBundle("LBL_FECHA_APROBACION_COMP"));
                        
                        setReqFecha(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_FECHAS_CONTRATO").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Firma del Contrato");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        
                        setLblFecha(getStringFromBundle("LBL_FECHA_FIRMA_CONTRATO_COMP"));
                        
                        setReqFecha(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_FECHAS_VIGENCIA").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Vigencia");
                        setMostrarNombre(Boolean.FALSE);
                        setMostrarDescripcion(Boolean.FALSE);
                        
                        setLblFecha(getStringFromBundle("LBL_FECHA_VIGENCIA_COMP"));
                        
                        setReqFecha(Boolean.TRUE);
                    }
                    if(getStringFromBundle("TERMINO_FECHAS_ESPECIFICAS").equals(idTcaTermino.getInputValue().toString())){
                        LOGGER.warning("Es subtipo Específicas");
                        setReqNombre(Boolean.TRUE);
                        setReqDescripcion(Boolean.TRUE);
                        setReqFecha(Boolean.TRUE);
                    }
                }else{
                    LOGGER.warning("idTcaTermino vacío"); 
                }
            }
        }else{
            LOGGER.warning("idTipoTermino vacío");
        }
        LOGGER.warning("Termina metodo getEsTerminoTipoFecha");
        return respuesta;
    }
    
    public Boolean getEsTerminoTipoOtro(){
        LOGGER.warning("Entra a metodo getEsTerminoTipoOtro");
        Boolean respuesta = Boolean.FALSE;
        AttributeBinding idTipoTermino;
        idTipoTermino = ADFUtils.findControlBinding("IdTipoTermino");
        if(null != idTipoTermino.getInputValue() && !"".equals(idTipoTermino.getInputValue().toString())){
            if(getStringFromBundle(TERMINO_TIPO_OTRO).equals(idTipoTermino.getInputValue().toString())){
                LOGGER.warning("idTipoTermino" + idTipoTermino.getInputValue().toString());
                respuesta = Boolean.TRUE;
                AttributeBinding idTcaTermino;
                idTcaTermino =ADFUtils.findControlBinding("IdTcaTermino");
                
                if(null != idTcaTermino.getInputValue() && !"".equals(idTcaTermino.getInputValue().toString())){
                    LOGGER.warning("idTcaTermino: " + idTcaTermino.getInputValue().toString());
                    setMostrarNombre(Boolean.TRUE);
                    setMostrarDescripcion(Boolean.TRUE);
                    
                    setLblNombre(getStringFromBundle("LBL_NOMBRE_COMP"));
                    setLblDescripcion(getStringFromBundle("LBL_DESCRIPCION_COMP"));
                    
                    setReqNombre(Boolean.TRUE);
                    setReqDescripcion(Boolean.TRUE);
                }else{
                    LOGGER.warning("idTcaTermino vacío"); 
                }
            }
        }else{
            LOGGER.warning("idTipoTermino vacío");
        }
        LOGGER.warning("Termina metodo getEsTerminoTipoOtro");
        return respuesta;
    }
    
    @SuppressWarnings("unchecked")
    public void cambioTipoFechaEspecifica(ValueChangeEvent vce){
        LOGGER.warning("Entra a metodo cambioTipoFechaEspecifica");        
        AttributeBinding idTipoFechaInicio;
        idTipoFechaInicio = ADFUtils.findControlBinding("IdTcaTipoFechaInicio");
        AttributeBinding fechaInicio;
        fechaInicio = ADFUtils.findControlBinding("FechaInicio");
        AttributeBinding idTermino;
        idTermino = ADFUtils.findControlBinding("Id");
        LOGGER.warning("idTipoFechaInicio " + vce.getNewValue());
        Integer idModalidad = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}").toString());
        
        LOGGER.warning("idModalidad: " + idModalidad);        
        if(null != vce.getNewValue()){
            if(vce.getNewValue().toString().equals(getStringFromBundle(TERMINO_ID_TIPO_FECHA_INICIO_ESPECIFICA))){
                LOGGER.warning("El tipo de fecha es Específica");
                setEsFechaEspecificaTermino(Boolean.TRUE);
                LOGGER.warning("setEsFechaEspecificaTermino: " + getEsFechaEspecificaTermino());
            }else{
                Integer idFechaInicio = Integer.parseInt(vce.getNewValue().toString());
                Map params = new HashMap();
                params.put("idFechaInicio", idFechaInicio);
                params.put("idModalidad", idModalidad);
                LOGGER.warning("Invoca metodo executeOperBinding para proceso");
                OperationBinding oper = executeOperBinding(params, "obtenerTipoFechaInicioTermino");
                
                if(oper.getErrors().isEmpty()){                    
                    RowSetIterator iter = 
                        ADFUtils.getDCBindingContainer().findIteratorBinding("TccTerminoVOIterator").getRowSetIterator();
                        
                    Row row = iter.getCurrentRow();
                        SimpleDateFormat formatter = new SimpleDateFormat(getStringFromBundle("TERMINO_FORMATO_FECHA"));
                        if(row.getAttribute("Id").toString().equals(idTermino.getInputValue().toString())){
                            if(null != row.getAttribute("FechaInicio")){
                                LOGGER.warning("fecha de inicio: " + row.getAttribute("FechaInicio").toString());
                                String fechaString = row.getAttribute("FechaInicio").toString();
        
                                try {
                                    LOGGER.warning("Convirtiendo fecha a Timestamp");
                                    Date fecha = formatter.parse(fechaString);
                                    Timestamp newFechaInicio = new Timestamp(fecha.getTime());
                                    fechaInicio.setInputValue(newFechaInicio);
                                } catch (Exception e) {
                                    LOGGER.warning("Error al convertir fecha a Timestamp");
                                }
                            }else{
                                LOGGER.warning("fecha de inicio es null");
                                fechaInicio.setInputValue(null);
                            }
                        }
                }else{
                    String msg = "Error al obtener la fecha de inicio";
                    LOGGER.severe(msg);
                    JSFUtils.addFacesErrorMessage(msg);
                }
                LOGGER.warning("Invocando metodo calcularFechaVencimientoTermino");
                calcularFechaVencimientoTermino();
                LOGGER.warning("Seteando setEsFechaEspecificaTermino a False");
                setEsFechaEspecificaTermino(Boolean.FALSE);
            }
        }
        LOGGER.warning("Termina metodo cambioTipoFechaEspecifica");
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaInicioUIC());
    }
    
    /**
     * En caso de una selección y existan cambios pendientes por guardar/deshacer (isDirty), 
     * restauramos el current row al elemento que se estaba editando (elegido anteriormente y guardado en MatrizTccBean)
     * Creado a raíz de la incidencia FNXII-3485.
     * 
     * @author Francisco Cuevas Pineda 
     * @since 7/julio/2016
     * @see Frank Nimphius response - https://community.oracle.com/thread/2480829 
     */
    private void restaurarSeleccionAnterior() {
        LOGGER.warning("Dentro de restaurarSeleccionAnterior");
        MatrizTccBean matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");    
        RichTree tccTree = null;
        LOGGER.warning("esSelectionEventQueue :"+matrizTccBean.getEsSelectionEventQueue());
        if(!matrizTccBean.getEsSelectionEventQueue()) {
            LOGGER.warning("Entra al IF");
            tccTree = getTccTreeUIC();
            // 2. Regresamos el current row del tree a su estado anterior. Para ello, creamos un nuevo evento de 
            // selección y lo encolamos al tree indicado. Además, reasignamos el selected row en pantalla.         
            SelectionEvent selectionEvent = new SelectionEvent(new RowKeySetImpl() , matrizTccBean.getDisclosedRowKeysTcc(), tccTree);
            selectionEvent.queue(); //queue event on component, es decir, llama de nuevo al Selectionlistener
            tccTree.setSelectedRowKeys(matrizTccBean.getDisclosedRowKeysTcc()); // Reasigna (pinta) el selected row en pantalla
            
            // 3. Asigna flag de control 
            matrizTccBean.setEsSelectionEventQueue(true); // Con este flag evitamos que entre repetidamente al Selectionlistener para el mismo evento
            //AdfFacesContext.getCurrentInstance().addPartialTarget(tccTree); // PPR the tree (no es necesario puesto que viene de un Selectionlistener del mismo tree)      
        } 
        /*
        else {
            LOGGER.warning("Selection NULL carga mensaje");
            LOGGER.warning("se envia mensaje : "+getStringFromBundle("transaccion.pendiente.confirmar"));
            // 1. Mostramos mensaje informando que hay cambios pendientes
            JSFUtils.addFacesErrorMessage(getStringFromBundle("transaccion.pendiente.confirmar"));
        }*/
        LOGGER.warning("Fuera de restaurarSeleccionAnterior");
    }
    
    private void treeRowSelectionlistener(SelectionEvent selectionEvent) {
        MatrizTccBean matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
        boolean isDirty = ADFUtils.getDCBindingContainer().getDataControl().isTransactionDirty();
        Boolean esEditar = null;
        RichTree tree = null;
        RowKeySet rowKeySet = null;
        Iterator rksIterator = null;
                
        // Asignación de variables
        //esEditar = Boolean.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}").toString());
        esEditar = false; //Para visualizar las pantallas de Ver al seleccionar un elemento en tree
        
        tree = (RichTree)selectionEvent.getSource(); // get the tree component from the event
        rowKeySet = selectionEvent.getAddedSet(); //get selected nodes
        rksIterator = rowKeySet.iterator();
        
        //Validating for single select only. Need to check for multiselect
        while (rksIterator.hasNext()) {
            List key = (List)rksIterator.next();
            JUCtrlHierBinding treeBinding = null;
            CollectionModel collectionModel = (CollectionModel)tree.getValue();
            treeBinding = (JUCtrlHierBinding)collectionModel.getWrappedData();           
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            Row row = null;
            if(nodeBinding != null){
                row = nodeBinding.getRow();
                if(row != null){
                    
                    List<String> atributos = Arrays.asList(row.getAttributeNames());
                    
                    // Obtenemos el Id seleccionado
                    if((atributos.contains("IdTermino")) && (row.getAttribute("IdTermino") != null)) {
                        
                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", obtenerIdTipoGestion(1, esEditar)); //setIdTipoGestion(obtenerIdTipoGestion(1, esEditar));
                        ADFContext.getCurrent().getViewScope().put
                            ("pIdTccTree", new Number(Long.valueOf(row.getAttribute("IdTermino").toString()))); //setIdTcc(new Number(Long.valueOf(row.getAttribute("IdTermino").toString())));                   
                    }
                    else if((atributos.contains("IdCondicion")) && (row.getAttribute("IdCondicion") != null)) {
                        
                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", obtenerIdTipoGestion(2, esEditar)); 
                        ADFContext.getCurrent().getViewScope().put
                            ("pIdTccTree", new Number(Long.valueOf(row.getAttribute("IdCondicion").toString()))); 
                    }
                    else if((atributos.contains("IdComision")) && (row.getAttribute("IdComision") != null)) {
                        
                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", obtenerIdTipoGestion(3, esEditar)); 
                        ADFContext.getCurrent().getViewScope().put
                            ("pIdTccTree", new Number(Long.valueOf(row.getAttribute("IdComision").toString()))); 
                    }
                    else{
                        // Se seleccionó un nodo padre, es decir NO es un Término, Condición o Comisión
                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", null); 
                        ADFContext.getCurrent().getViewScope().put("pIdTccTree", null); 
                    }
                    
                    // Incidencia FNXII-3485: Cuando NO existan cambios pendientes por guardar/deshacer (!isDirty), guardamos 
                    // la selección actual del tree, para restaurarla en caso de un isDirty
                    if(!isDirty) {
                        RowKeySetImpl rksNew = new RowKeySetImpl();
                        rksNew.add(key);
                        matrizTccBean.setDisclosedRowKeysTcc(rksNew);
                        matrizTccBean.setEsSelectionEventQueue(false); // limpiamos flag de control de entrada infinita al Selectionlistener
                    }
                }
            }
        }
    }
    
    public void setOtInitForm(RichOutputText otInitForm) {
        this.otInitForm = otInitForm;
    }

    public RichOutputText getOtInitForm() {
        inicializarTreeTcc();
        return otInitForm;
    }
    
    /**
     * Método que actualiza el tree. Para ello hace un executeQuery del iterador padre y sus hijos.
     * @param rootIteratorName Nombre del iterador padre.
     * @param idLink 1 = Término, 2 = Condición, 3 = Comisión.
     * @param accessorName ViewLinkAccessor name definido en el VO padre.
     * 
     * @author Francisco Cuevas Pineda 
     * @since 24/mayo/2016
     * @see http://www.awasthiashish.com/2013/09/refreshing-child-nodes-of-aftreetable.html
     */
    private void refreshTccTree(String rootIteratorName, Integer idLink, String accessorName) {
        DCIteratorBinding treeRootIteratorVO = null;
        ViewObject treeRootVO = null;
        
        inicializarTreeTcc();
        
        // Asignación de variables
        treeRootIteratorVO = ADFUtils.findIterator(rootIteratorName);
        treeRootVO = treeRootIteratorVO.getViewObject(); // Get Master ViewObject
                
        // Filtramos el VO padre por su Key Attribute "IdLink" (1 = Término, 2 = Condición, 3 = Comisión)
        Row[] rootTccRows = treeRootVO.findByKey(new Key(new Object[] { idLink }), 1);  
        
        // Realizamos el executeQuery de los hijos
        if(rootTccRows.length>0) {
            // Get Child Rows using ViewLink Accessor. 
            // "accessorName" es el ViewLinkAccessor name definido en el VO padre (ej. TccTreeTcaTipoTerminoVO para el
            // padre TccTreeRootTerminoVO).
            RowSet childRows = (RowSet)rootTccRows[0].getAttribute(accessorName);  
            childRows.executeQuery(); // Execute Child Rowset 
        }
    }
            
    /*** END Métodos del tree ***/
    
    /*** START Funcionalidad de popup y botones Seleccionar / Agregar ***/
    public void setPopupAgregarSeleccionar(RichPopup popupAgregarSeleccionar) {
        this.popupAgregarSeleccionar = popupAgregarSeleccionar;
    }

    public RichPopup getPopupAgregarSeleccionar() {
        return popupAgregarSeleccionar;
    }
    
    public void setPopupAgregarSeleccionarGrid(RichPopup popupAgregarSeleccionarGrid) {
        this.popupAgregarSeleccionarGrid = popupAgregarSeleccionarGrid;
    }

    public RichPopup getPopupAgregarSeleccionarGrid() {
        return popupAgregarSeleccionarGrid;
    }

    public void setFechaInicioUIC(RichInputDate fechaInicioUIC) {
        this.fechaInicioUIC = fechaInicioUIC;
    }

    public RichInputDate getFechaInicioUIC() {
        return fechaInicioUIC;
    }
    
    public void agregarSeleccionarTccActionListener(ActionEvent actionEvent) {
        LOGGER.warning("Dentro de agregarSeleccionarTccActionListener");
        MatrizTccBean matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
        Long idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        RichPopup.PopupHints popupHints = null;
        String opAgregarSeleccionar = null;
        String instanciaProceso = null;
        Integer idModalidad = null;
        
        Long idEnmienda = null;
        
        Integer idTareaBpm = null;
        
        // Asignación de variables
        opAgregarSeleccionar = (String)JSFUtils.resolveExpression("#{viewScope.opAgregarSeleccionar}");
        instanciaProceso = (String)JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}");
        idModalidad = (matrizTccBean.getIdModalidad() == null) ? Integer.valueOf(0) : matrizTccBean.getIdModalidad();
        idEnmienda = matrizTccBean.getIdEnmienda();
        idTareaBpm = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}");
        
        if((opAgregarSeleccionar != null) && (opAgregarSeleccionar.equalsIgnoreCase("agregar"))) {
            LOGGER.warning("En opcion agregar ");
            // Se inicializa VO de captura para agregar, dependiendo de si es tree o grid
            if(ID_MODALIDAD_TREE.equals(idModalidad)){
                LOGGER.warning("En opcion tree");
                operationBinding = bindings.getOperationBinding("inicializarTccAgregarVoConIdOperacionYIdProcesoBpm"); 
                operationBinding.getParamsMap().put("idTareaBpm", idTareaBpm);
            }else if(ID_MODALIDAD_GRID.equals(idModalidad)) {
                LOGGER.warning("En opcion grid");
                operationBinding = bindings.getOperationBinding("inicializarAgregarEnmendadoVOConIdProcesoBpm");
                //operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso); // Filtro para tabla ENMIENDA_TCC    
                operationBinding.getParamsMap().put("idEnmienda", idEnmienda);
                operationBinding.getParamsMap().put("idTareaBpm", idTareaBpm);
            }
        }
        else
            operationBinding = bindings.getOperationBinding("inicializarSeleccionarVO");
        
        // Ejecutamos método de inicialización de VO (crea row vacío y asigna Id Operación)
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        //Asigna el Tab Por defecto al abrir Popup.
        JSFUtils.setExpressionValue("#{viewScope.tabTccSeleccionado}", FenixConstants.TERMINO);
        
        //Reset popup (fuerza un reset si el lazyUncached no funciona )
        //oracle.adf.view.rich.util.ResetUtils.reset(getPopupAgregarSeleccionar());
        
        if(ID_MODALIDAD_TREE.equals(idModalidad)){
            // Abrimos popup
            popupHints = new RichPopup.PopupHints();
            getPopupAgregarSeleccionar().show(popupHints);
        }else{
            // Abrimos popup
            popupHints = new RichPopup.PopupHints();
            getPopupAgregarSeleccionarGrid().show(popupHints);
        }
        LOGGER.warning("Fuera de agregarSeleccionarTccActionListener");
    }

    public void terminosDisclosureListener(DisclosureEvent disclosureEvent) {
        // Click en tab Términos
        LOGGER.log(ADFLogger.TRACE, "Inside terminosDisclosureListener: " + disclosureEvent.getComponent().getId());
        JSFUtils.setExpressionValue("#{viewScope.tabTccSeleccionado}", FenixConstants.TERMINO);
    }

    public void condicionesDisclosureListener(DisclosureEvent disclosureEvent) {
        // Click en tab Condiciones
        LOGGER.log(ADFLogger.TRACE, "Inside condicionesDisclosureListener: " + disclosureEvent.getComponent().getId());
        JSFUtils.setExpressionValue("#{viewScope.tabTccSeleccionado}", FenixConstants.CONDICION);
    }

    public void comisionesDisclosureListener(DisclosureEvent disclosureEvent) {
        // Click en tab Comisiones
        LOGGER.log(ADFLogger.TRACE, "Inside comisionesDisclosureListener: " + disclosureEvent.getComponent().getId());
        JSFUtils.setExpressionValue("#{viewScope.tabTccSeleccionado}", FenixConstants.COMISION);
    }    
    
    public void aceptarAgregarSeleccionarActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.WARNING, "Inside aceptarAgregarSeleccionarActionListener: " + actionEvent.getComponent().getId());
        MatrizTccBean matrizTccBean = null;
        Long idOperacion = null;
        String instanciaProceso = null;
        String opAgregarSeleccionar = null;
        String tabTccSeleccionado = null;
        oracle.jbo.domain.Number idTccSeleccionado = null;
        Integer idTcaTcc = null;
        String nombreTcc = null;
        String concatTcas = null;
        String tcaDescCorta = null;
        Integer idModalidad = null;
        Long idTccCurrentRow = null;
        Long idEnmienda = null;
        Integer idTareaBpm = null;
        ExtendedRenderKitService erks = null;
        String loginUsuario = ADFContext.getCurrent().getSecurityContext().getUserName();
        LOGGER.warning("Usuario obtenido => " + loginUsuario);
        
        // Asignación de variables
        matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
        idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
        instanciaProceso = (String)JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaProceso}");
        opAgregarSeleccionar = (String)JSFUtils.resolveExpression("#{viewScope.opAgregarSeleccionar}");
        idModalidad = (matrizTccBean.getIdModalidad() == null) ? Integer.valueOf(0) : matrizTccBean.getIdModalidad();
        

        try{
            idEnmienda = matrizTccBean.getIdEnmienda() != null ? matrizTccBean.getIdEnmienda() : null;
        }catch(Exception e){
            LOGGER.warning("No se pudo obtener el Id de Enmienda");
        }
        
        try{
            idTareaBpm = matrizTccBean.getIdTareaBpm() != null ? matrizTccBean.getIdTareaBpm() : null;
        }catch(Exception e){
            LOGGER.warning("No se pudo obtener el Id de Tarea");
        }
        
        tabTccSeleccionado = validaTabSeleccionado(idTareaBpm);
        
        if(tabTccSeleccionado.equals(FenixConstants.TERMINO)) {
            // Variable específica de enmendarTcc
            idTccSeleccionado = (JSFUtils.resolveExpression("#{bindings.IdTermino.inputValue}") == null) ? null : 
                new oracle.jbo.domain.Number((Long)JSFUtils.resolveExpression("#{bindings.IdTermino.inputValue}"));
            
            // Variables específicas de crearTcc
            idTcaTcc = (JSFUtils.resolveExpression("#{bindings.IdTcaTermino.inputValue}") == null) ? null : 
                (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaTermino.inputValue}");
            //nombreTcc = (JSFUtils.resolveExpression("#{bindings.TcaTerminoDescripcion.inputValue}") == null) ? null : 
                //(String)JSFUtils.resolveExpression("#{bindings.TcaTerminoDescripcion.inputValue}");
            concatTcas = (String)JSFUtils.resolveExpression("#{bindings.ConcatTcasTermino.inputValue}");
            tcaDescCorta = (String)ADFUtils.getBoundAttributeValue("TcaTerminoDescCorta");
            
            //Nombre con que se debe guardar el Termino
            if(null!= concatTcas){
                nombreTcc = concatTcas.replace("/", "-");
                LOGGER.warning("Nombre del Termino: " + nombreTcc);
            }
        }
        else if(tabTccSeleccionado.equals(FenixConstants.CONDICION)) {
            // Variable específica de enmendarTcc
            idTccSeleccionado = (JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}") == null) ? null : 
                new oracle.jbo.domain.Number((Long)JSFUtils.resolveExpression("#{bindings.IdCondicion.inputValue}"));
            
            // Variables específicas de crearTcc
            idTcaTcc = (JSFUtils.resolveExpression("#{bindings.IdTcaCondicion.inputValue}") == null) ? null : 
                (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaCondicion.inputValue}");
            //nombreTcc = (JSFUtils.resolveExpression("#{bindings.TcaCondicionDescripcion.inputValue}") == null) ? null : 
                //(String)JSFUtils.resolveExpression("#{bindings.TcaCondicionDescripcion.inputValue}");
            concatTcas = (String)JSFUtils.resolveExpression("#{bindings.ConcatTcasCondicion.inputValue}");
            tcaDescCorta = (String)ADFUtils.getBoundAttributeValue("TcaCondicionDescCorta");
            
            //Nombre con que se debe guardar la Condición
            if(null!= concatTcas){
                nombreTcc = concatTcas.replace("/", "-");
                LOGGER.warning("Nombre de la Condición: " + nombreTcc);
            }
        }
        else if(tabTccSeleccionado.equals(FenixConstants.COMISION)) {
            // Variable específica de enmendarTcc
            idTccSeleccionado = (JSFUtils.resolveExpression("#{bindings.IdComision.inputValue}") == null) ? null :
                new oracle.jbo.domain.Number((Long)JSFUtils.resolveExpression("#{bindings.IdComision.inputValue}"));
            
            // Variables específicas de crearTcc
            idTcaTcc = (JSFUtils.resolveExpression("#{bindings.IdTcaComision.inputValue}") == null) ? null : 
                (Integer)JSFUtils.resolveExpression("#{bindings.IdTcaComision.inputValue}");
            //nombreTcc = (JSFUtils.resolveExpression("#{bindings.TcaComisionDescripcion.inputValue}") == null) ? null : 
                //(String)JSFUtils.resolveExpression("#{bindings.TcaComisionDescripcion.inputValue}");
            concatTcas = (String)JSFUtils.resolveExpression("#{bindings.ConcatTcasComision.inputValue}");
            tcaDescCorta = (String)ADFUtils.getBoundAttributeValue("TcaComisionDescCorta");
            
            //Nombre con que se debe guardar la Comisión
            if(null!= concatTcas){
                nombreTcc = concatTcas.replace("/", "-");
                LOGGER.warning("Nombre de la Comisión: " + nombreTcc);
            }
        }

        if((opAgregarSeleccionar != null) && (opAgregarSeleccionar.equalsIgnoreCase("agregar"))) {
            
            // Agregar desde el tree es diferente al agregar desde el grid. En ambos se crean nuevos registros TCC pero, 
            // en el caso del grid, se crea con BAN_ESTATUS = 0 y su registro correspondiente en la tabla DETALLE_ENMIENDA_TCC
            if(ID_MODALIDAD_TREE.equals(idModalidad)) {
                // Agregamos TCC - desde el tree. Estado [nuevo], sub estado [null]
                if(validarCrearTcc(tabTccSeleccionado, concatTcas, tcaDescCorta)) {
                    
                    idTccCurrentRow = ADFContext.getCurrent().getViewScope().get("pIdTccTree") == null ? null : 
                        Long.valueOf(ADFContext.getCurrent().getViewScope().get("pIdTccTree").toString());
                    crearTcc(idOperacion, tabTccSeleccionado, nombreTcc, idTcaTcc, FenixConstants.ESTADO_TCC_NUEVA, null, 
                             idTccCurrentRow, tcaDescCorta, loginUsuario);
                }
            }
            else if(ID_MODALIDAD_GRID.equals(idModalidad)) {
                // Agregamos TCC - desde el grid. Estado [nuevo], sub estado [null]
                crearTccEnmendado(idOperacion, idEnmienda, instanciaProceso, tabTccSeleccionado, nombreTcc, idTcaTcc, 
                                  FenixConstants.ESTADO_TCC_NUEVA, null, null, tcaDescCorta, loginUsuario);
            }
        }
        else{
            // Seleccionar (enmendamos) TCC - desde el grid. Estado [igual al estado del padre], sub estado [null]
            enmendarTcc(idOperacion, idEnmienda, instanciaProceso, idTccSeleccionado, tabTccSeleccionado, null, null, null, tcaDescCorta, loginUsuario);
        }
        
        // Asignamos como disclosed tab a Términos
        asignarTerminosDisclosedTab();
        
        // Reiniciamos Grid/Tree
        reiniciarGridTree();
        
        if(ID_MODALIDAD_TREE.equals(idModalidad)) {
            
            // Cerramos popup - agregar para el tree
            LOGGER.info("Cerrando popup del tree");
            getPopupAgregarSeleccionar().hide();
        }
        else if(ID_MODALIDAD_GRID.equals(idModalidad)) {
            
            // Cerramos popup - agregar para el grid
            LOGGER.info("Cerrando popup del grid");
            getPopupAgregarSeleccionarGrid().hide();
            
            // Invocamos mediante javascript el método que actualiza datos en la tarea de Ingresar enmienda.
            if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
               && (Integer.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString()).intValue() 
                   == FenixConstants.PAC04_INGRESAR_ENMIENDA)) {
                
                erks = Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
                erks.addScript(FacesContext.getCurrentInstance(), 
                               "refreshDatosEnmienda('" + 
                               JSFUtils.resolveExpression("#{pageFlowScope.pClientIdBtnRefreshEnmienda}") +"');");
            }
            
            // Recarga de datos en el Grid
            if(null != matrizTccBean.getIdTcaTccSeleccionado()) {
                
                Integer idTipoTccSeleccionado = matrizTccBean.getIdTcaTccSeleccionado();
                if(idTipoTccSeleccionado.compareTo(FenixConstants.TERMINO_INT)==0){        
                    if(null != matrizTccBean.getIdTccGridSeleccionado()){
                        LOGGER.warning("Invocando metodos de carga de pantalla de verTermino");
                        oracle.jbo.domain.Number idTccSelected = matrizTccBean.getIdTccGridSeleccionado();
                        Map params = new HashMap();
                        LOGGER.warning("ID de Termino en vista: " + idTccSelected + " en modalidad: " + idModalidad);
                        params.put("id", idTccSelected);
                        params.put("idModalidad", idModalidad);
                        LOGGER.warning("Invocar metodo executeOperBinding");
                        OperationBinding oper = executeOperBinding(params, "buscarTerminoPorId");
                        
                        if(oper.getErrors().isEmpty())
                        {
                          oper = executeOperBinding(params, "obtenerConfigucacionAtributosTcc");
                           
                          if(oper.getErrors().isEmpty())
                          {
                              Map<String,Map> configuracionAtributosTCCMap = (Map<String,Map>) oper.getResult();
                              
                              TerminoManagedBean terminoManagedBean = (TerminoManagedBean)JSFUtils.resolveExpression("#{pageFlowScope.terminoManagedBean}");
                              
                              if(null!=terminoManagedBean)
                              {
                                terminoManagedBean.setAtributeValues(configuracionAtributosTCCMap);
                              }
                          }
                          else
                          {
                            String msg = "Error al obtener configuracion de atributos TCC ";
                            LOGGER.severe(msg);
                            JSFUtils.addFacesErrorMessage(msg);
                          }
                           
                        }
                        else
                        {
                            String msg = "Error al buscar los datos ralacionados con el Termino " + idTccSelected;
                            LOGGER.severe(msg);
                            JSFUtils.addFacesErrorMessage(msg);
                        }
                    }
                } else if(idTipoTccSeleccionado.compareTo(FenixConstants.CONDICION_INT)==0) {
                    if(null != matrizTccBean.getIdTccGridSeleccionado()){
                        LOGGER.warning("Invocando metodos de carga de pantalla de verCondicion");
                        oracle.jbo.domain.Number idTccSelected = matrizTccBean.getIdTccGridSeleccionado();
                        Map params = new HashMap();
                        LOGGER.warning("ID de Condicion en vista: " + idTccSelected + " en modalidad: " + idModalidad);
                        params.put("id", idTccSelected);
                        LOGGER.warning("Invocar metodo executeOperBinding");
                        OperationBinding oper = executeOperBinding(params, "buscarCondicionPorId");
                        
                        if(oper != null){
                            if(oper.getErrors().isEmpty()){
                                
                                Map inicializaCondParams = null;
                                inicializaCondParams = new HashMap();
                                
                                inicializaCondParams.put("idModalidad", idModalidad);
                                LOGGER.warning("idModalidad para invocar inicializaTccCondicion: " + idModalidad);
                                OperationBinding operInicializa = executeOperBinding(inicializaCondParams, "inicializaTccCondicion");
                                if(operInicializa != null){
                                    
                                    if(operInicializa.getErrors().isEmpty()){
                                        LOGGER.warning("Inicializacion de Condicion terminada sin errores");
                                        
                                        OperationBinding operConfig = executeOperBinding(null, "obtenerConfigucacionAtributosTcc");
                                        if(operConfig != null){
                                            if(operConfig.getErrors().isEmpty()){
                                                
                                                Map<String,Map> configuracionAtributosTCCMap = (Map<String,Map>) operConfig.getResult();
                                                if(configuracionAtributosTCCMap != null){
                                                    CondicionMangedBean condicionManagedBean = 
                                                        (CondicionMangedBean)JSFUtils.resolveExpression("#{pageFlowScope.condicionManagedBean}");
                                                    
                                                    if(null != condicionManagedBean){
                                                        condicionManagedBean.setAtributeValues(configuracionAtributosTCCMap);
                                                    }
                                                }else{
                                                    LOGGER.severe("Error no se obtuvo resultado de la configuracion de Atributos");
                                                    String msg = getStringFromBundle("msg.error.configuracion.atributos");
                                                    LOGGER.severe(msg);
                                                    JSFUtils.addFacesErrorMessage(msg);
                                                }
                                            }else{
                                                String msg = getStringFromBundle("msg.error.configuracion.atributos");
                                                LOGGER.severe(msg);
                                                JSFUtils.addFacesErrorMessage(msg);
                                            }
                                        }
                                    }else{
                                        LOGGER.severe("Inicializacion de Condicion tuvo error. Errores: " + 
                                        operInicializa.getErrors().toString());
                                    }
                                }else{
                                    LOGGER.severe("Error al inicializar la condicion, operator binding no definido");
                                }
                            }else{
                                LOGGER.severe("Error la busqueda de la Condicion tuvo errores");
                            }
                        }else{
                            LOGGER.severe("Error la operacion de busqueda de Condicion no esta definida");
                        }
                    }
                } else if(idTipoTccSeleccionado.compareTo(FenixConstants.COMISION_INT)==0) {
                    if(null != matrizTccBean.getIdTccGridSeleccionado()){
                        LOGGER.warning("Invocando metodos de carga de pantalla de verComision");
                        oracle.jbo.domain.Number idTccSelected = matrizTccBean.getIdTccGridSeleccionado();
                        Map params = new HashMap();
                        LOGGER.warning("ID de Condicion en vista: " + idTccSelected + " en modalidad: " + idModalidad);
                        params.put("id", idTccSelected);
                        LOGGER.warning("Invocar metodo executeOperBinding");
                        OperationBinding oper = executeOperBinding(params, "buscarComisionPorId");
                        
                        if(oper.getErrors().isEmpty())
                        {
                          //params = new HashMap();
                          //params.put("", 1);
                          
                          oper = executeOperBinding(params, "obtenerConfigucacionAtributosTcc");
                           
                          if(oper.getErrors().isEmpty())
                          {
                              Map<String,Map> configuracionAtributosTCCMap = (Map<String,Map>) oper.getResult();
                              
                              ComisionManagedBean comisionManagedBean = (ComisionManagedBean)JSFUtils.resolveExpression("#{pageFlowScope.comisionManagedBean}");
                              
                              if(null!=comisionManagedBean)
                              {
                                comisionManagedBean.setAtributeValues(configuracionAtributosTCCMap);
                              }
                          }
                          else
                          {
                            String msg = "Error al obtener configuracion de atributos TCC ";
                            LOGGER.severe(msg);
                            JSFUtils.addFacesErrorMessage(msg);
                          }
                           
                        }
                        else
                        {
                            String msg = "Error al bucar los datos ralacionados con la comision " + idTccSelected;
                            LOGGER.severe(msg);
                            JSFUtils.addFacesErrorMessage(msg);
                        }
                    }
                }
            }
        }
    }
    
    private Boolean validarCrearTcc(String tabTccSeleccionado, String concatTcas, String tcaDescCorta) {
    LOGGER.warning("Inside validarCrearTcc");
    LOGGER.warning("tabTccSeleccionado: " + tabTccSeleccionado);
    LOGGER.warning("concatTcas: " + concatTcas);
    LOGGER.warning("tcaDescCorta: " + tcaDescCorta);
    
    Boolean esDatosCorrectos = Boolean.TRUE;
    String nombreProceso = (JSFUtils.resolveExpression("#{bindings.Descripcion.inputValue}") == null) ? "" : 
    (String)JSFUtils.resolveExpression("#{bindings.Descripcion.inputValue}");

    Integer idProcesoBpm = null;
    try{
    idProcesoBpm = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm");
    }catch(Exception e){
    LOGGER.severe("Error al obtener el Id de Proceso BPM");
    }
    
    if(concatTcas != null &&
    tcaDescCorta != null &&
              idProcesoBpm != null){
                
                concatTcas = concatTcas.trim();
                tcaDescCorta = tcaDescCorta.trim();
                
                // Validación para los TCC que sólo se pueden crear en un proceso específico         
                if(tabTccSeleccionado.equals(FenixConstants.TERMINO)) {
                    
                /*if(concatTcas.contains("Monto")) {
                        
                    if(concatTcas.contains("Aprobado") && !nombreProceso.equalsIgnoreCase("Aprobación")) {
                        // Incidencia FNXII-3157: El Monto aprobado, sólo se puede agregar en el proceso de Aprobación. 
                        esDatosCorrectos = Boolean.FALSE;
                        String msg = getStringFromBundle("validacion.crear.tcc.termino.T202.msg");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                    else if(concatTcas.contains("Formalizado") && !nombreProceso.equalsIgnoreCase("Formalización")) {
                        // Incidencia FNXII-3157: El Monto formalizado, sólo se puede agregar en el proceso de Formalización.
                        esDatosCorrectos = Boolean.FALSE;
                        String msg = getStringFromBundle("validacion.crear.tcc.termino.T203.msg");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                }*/
                    
                if("T202".equals(tcaDescCorta)){
                    // Incidencia FNXII-3157: El Monto aprobado, sólo se agrega automaticamente en las tareas Certificar decision o Consolidar decision en Aprobacion. 
                    esDatosCorrectos = Boolean.FALSE;
                    String msg = getStringFromBundle("validacion.crear.tcc.termino.T202.no.aprobacion.msg");
                    JSFUtils.addFacesErrorMessage(msg);
                }
                    
                if("T203".equals(tcaDescCorta) &&
                    !ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                    // Incidencia FNXII-3157: El Monto formalizado, sólo se puede agregar en el proceso de Formalización.
                    esDatosCorrectos = Boolean.FALSE;
                    String msg = getStringFromBundle("validacion.crear.tcc.termino.T203.msg");
                    JSFUtils.addFacesErrorMessage(msg);
                }

                if("T602".equals(tcaDescCorta)){
                    //Incidencia FNXII-3178
                    esDatosCorrectos = Boolean.FALSE;
                    String msg = getStringFromBundle("validacion.crear.tcc.termino.T602.msg");
                    JSFUtils.addFacesErrorMessage(msg);
                }
                
                if("T603".equals(tcaDescCorta) &&
                   !ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                    //Incidencia FNXII-3178
                    esDatosCorrectos = Boolean.FALSE;
                    String msg = getStringFromBundle("validacion.crear.tcc.termino.T603.no.formalizacion.msg");
                    JSFUtils.addFacesErrorMessage(msg);
                }else{
                    if("T603".equals(tcaDescCorta) &&
                       ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                        //Incidencia FNXII-3178
                        esDatosCorrectos = Boolean.FALSE;
                        String msg = getStringFromBundle("validacion.crear.tcc.termino.T603.si.formalizacion.msg");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                }
                
                if("T604".equals(tcaDescCorta) &&
                   !ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                    //Incidencia FNXII-3178
                    esDatosCorrectos = Boolean.FALSE;
                    String msg = getStringFromBundle("validacion.crear.tcc.termino.T604.no.formalizacion.msg");
                    JSFUtils.addFacesErrorMessage(msg);
                }else{
                    if("T604".equals(tcaDescCorta) &&
                       ID_PROCESO_FORMALIZACION_BPM.equals(idProcesoBpm)){
                        //Incidencia FNXII-3178
                        esDatosCorrectos = Boolean.FALSE;
                        String msg = getStringFromBundle("validacion.crear.tcc.termino.T604.si.formalizacion.msg");
                        JSFUtils.addFacesErrorMessage(msg);
                    }
                }
            }
        }else{
            esDatosCorrectos = Boolean.FALSE;
            String msg = getStringFromBundle("validacion.crear.tcc.mgs.error");
            JSFUtils.addFacesErrorMessage(msg);
            LOGGER.severe("Error no se puede validar la creacion de la TCC");
        }

        return esDatosCorrectos;
    }
    
    public void cancelarAgregarSeleccionarActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside cancelarAgregarSeleccionarActionListener: " + actionEvent.getComponent().getId());

        // Asignamos como disclosed tab a Términos
        //asignarTerminosDisclosedTab();
        
        Integer idModalidad = null;
        MatrizTccBean matrizTccBean = null;
        matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
        idModalidad = (matrizTccBean.getIdModalidad() == null) ? Integer.valueOf(0) : matrizTccBean.getIdModalidad();
        
        if(ID_MODALIDAD_TREE.equals(idModalidad)) {
            // Cerramos popup
            getPopupAgregarSeleccionar().hide();    
        }else{
            // Cerramos popup
            getPopupAgregarSeleccionarGrid().hide();
        }
        // Reiniciamos Grid/Tree
        reiniciarGridTree();
    }
    
    private void asignarTerminosDisclosedTab(){
        
        getTabTerminos().setDisclosed(true);
        getTabCondiciones().setDisclosed(false);
        getTabComisiones().setDisclosed(false);
    }
    
    private void reiniciarGridTree() {
        LOGGER.log(ADFLogger.TRACE, "Inside reiniciarGridTree.");
        LOGGER.warning("Inside reiniciarGridTree");
        MatrizTccBean matrizTccBean = null;
        String tabTccSeleccionado = null;
        
        matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
        tabTccSeleccionado = (JSFUtils.resolveExpression("#{viewScope.tabTccSeleccionado}") == null) ? 
            FenixConstants.TERMINO : JSFUtils.resolveExpression("#{viewScope.tabTccSeleccionado}").toString();
        
        if(matrizTccBean != null){
            Integer idModalidad = matrizTccBean.getIdModalidad();
            
            if(ID_MODALIDAD_TREE.equals(idModalidad)) {
                // Actualizamos tree correspondiente
                if(tabTccSeleccionado.equals(FenixConstants.TERMINO))
                    refreshTccTree("TccTreeRootVOIterator", Integer.valueOf(1), "TccTreeTcaTipoVO");
                else if(tabTccSeleccionado.equals(FenixConstants.CONDICION))
                    refreshTccTree("TccTreeRootVOIterator", Integer.valueOf(2), "TccTreeTcaTipoVO");
                else if(tabTccSeleccionado.equals(FenixConstants.COMISION))
                    refreshTccTree("TccTreeRootVOIterator", Integer.valueOf(3), "TccTreeTcaTipoVO");
            }
            else if(ID_MODALIDAD_GRID.equals(idModalidad)) {
                
                //Reinicia la pantalla en modalidad Grid
                inicializaGridTccPorOperacion();
                //Refresca componente del Grid
                AdfFacesContext.getCurrentInstance().addPartialTarget(getTccTablaUIC());
            }
        }
    }
    
    private void crearTcc(Long idOperacion, String tipoTcc, String nombreTcc, Integer idTcaTcc, Integer idTcaEstadoTcc, 
                          Integer idTcaSubEstadoTcc, Long idTccCurrentRow, String tcaDescCorta, String loginUsuario) {
        LOGGER.log(ADFLogger.WARNING, "Inside crearTcc.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Ejecutamos método que crea un TCC
        operationBinding = bindings.getOperationBinding("crearTcc");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("tipoTcc", tipoTcc);
        operationBinding.getParamsMap().put("nombreTcc", nombreTcc);
        operationBinding.getParamsMap().put("idTcaTcc", idTcaTcc);
        operationBinding.getParamsMap().put("idTcaEstadoTcc", idTcaEstadoTcc);
        operationBinding.getParamsMap().put("idTcaSubEstadoTcc", idTcaSubEstadoTcc);
        operationBinding.getParamsMap().put("idTccCurrentRow", idTccCurrentRow);
        operationBinding.getParamsMap().put("tcaDescCorta", tcaDescCorta);
        operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        else{ 
            if((operationBinding.getResult() != null) && ((Boolean)operationBinding.getResult())){
                //Se deshabilita mensaje por incidencia FNXII-3303
                //JSFUtils.addFacesInformationMessage("TCC creado exitosamente.");
                LOGGER.warning("TCC creado exitosamente");
            }else{
                JSFUtils.addFacesErrorMessage("Error al crear TCC.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void crearTccEnmendado(Long idOperacion, Long idEnmienda, String instanciaProceso, String tipoTcc, String nombreTcc, 
                                   Integer idTcaTcc, Integer idTcaEstadoTcc, Integer idTcaSubEstadoTcc, String accion,
                                   String tcaDescCorta, String loginUsuario) {
        LOGGER.log(ADFLogger.TRACE, "Inside crearTccEnmendado.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        // Ejecutamos método que crea un TCC
        operationBinding = bindings.getOperationBinding("crearTccEnmendado");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idEnmienda", idEnmienda);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
        operationBinding.getParamsMap().put("tipoTcc", tipoTcc);
        operationBinding.getParamsMap().put("nombreTcc", nombreTcc);
        operationBinding.getParamsMap().put("idTcaTcc", idTcaTcc);
        operationBinding.getParamsMap().put("idTcaEstadoTcc", idTcaEstadoTcc);
        operationBinding.getParamsMap().put("idTcaSubEstadoTcc", idTcaSubEstadoTcc);
        operationBinding.getParamsMap().put("accion", accion);
        operationBinding.getParamsMap().put("tcaDescCorta", tcaDescCorta);
        operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            if((operationBinding.getResult() != null) && ((Boolean)operationBinding.getResult())) {
                //Se deshabilita mensaje por incidencia FNXII-3303
                //JSFUtils.addFacesInformationMessage("TCC creado exitosamente.");
                LOGGER.warning("TCC Enmienda creado exitosamente");
            } else {
                JSFUtils.addFacesErrorMessage("Error al crear TCC.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void enmendarTcc(Long idOperacion, Long idEnmienda, String instanciaProceso, oracle.jbo.domain.Number idTccSeleccionado, 
                             String tipoTcc, Integer idTcaEstadoTcc, Integer idTcaSubEstadoTcc, String accion,
                             String tcaDescCorta, String loginUsuario) {
        LOGGER.log(ADFLogger.TRACE, "Inside enmendarTcc.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        // Ejecutamos método que enmienda un TCC
        operationBinding = bindings.getOperationBinding("enmendarTcc");
        operationBinding.getParamsMap().put("idOperacion", idOperacion);
        operationBinding.getParamsMap().put("idEnmienda", idEnmienda);
        operationBinding.getParamsMap().put("instanciaProceso", instanciaProceso);
        operationBinding.getParamsMap().put("idTcc", idTccSeleccionado);
        operationBinding.getParamsMap().put("tipoTcc", tipoTcc);
        operationBinding.getParamsMap().put("idTcaEstadoTcc", idTcaEstadoTcc);
        operationBinding.getParamsMap().put("idTcaSubEstadoTcc", idTcaSubEstadoTcc);
        operationBinding.getParamsMap().put("accion", accion);
        operationBinding.getParamsMap().put("tcaDescCorta", tcaDescCorta);
        operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            if((operationBinding.getResult() != null) && ((Boolean)operationBinding.getResult())) {
                //Se deshabilita mensaje por incidencia FNXII-3303
                //JSFUtils.addFacesInformationMessage("TCC enmendado exitosamente.");
                LOGGER.warning("TCC Enmendado exitosamente");
            } else {
                JSFUtils.addFacesErrorMessage("Error al enmendar TCC.");
            }
        }
    }
    
    /*** END Funcionalidad de popup y botones Seleccionar / Agregar ***/
    
    /**
     * Reinicia el Modelo de Matriz TCC, ejecutando el proceso correspondiente al Arbol o Tabla
     */
    @SuppressWarnings("unchecked")
    public void reiniciarMatrizTccModel(){
        
        String strIdOperacion = null;
        oracle.jbo.domain.Number idOperacion = null;
        Integer idModalidad = null;
        
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
                strIdOperacion = JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();
            }
            
            if(strIdOperacion == null){
                LOGGER.severe("Error el Id de Operacion es NULL");
            }else{
                idOperacion = new oracle.jbo.domain.Number(strIdOperacion);
            }
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id de Operacion", e);
        }
        
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null){
            String strIdModalidad = null;
            try{
                strIdModalidad = JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}").toString();
                idModalidad = new Integer(strIdModalidad);
            }catch(Exception e){
                LOGGER.severe("Error al obtener Id de la Modalidad para el reinicio de datos", e);
            }
        }else{
            LOGGER.severe("No existe Id de Modalidad definida");
        }

        if(idOperacion != null &&
           idModalidad != null){
            
            if(ID_MODALIDAD_TREE.equals(idModalidad)){
                Map params = new HashMap();
                params.put("value", idOperacion.longValue());
                
                LOGGER.warning("Ejecuta inicializacion de Matriz Tcc Tree");
                executeOperBinding(params, "setvarIdOperacionTcc");
            }
            
            if(ID_MODALIDAD_GRID.equals(idModalidad)){
                
                oracle.jbo.domain.Number idEnmienda = null;
                try{
                    if(JSFUtils.resolveExpression("#{pageFlowScope.pIdEnmienda}") != null){
                        idEnmienda = new Number(JSFUtils.resolveExpression("#{pageFlowScope.pIdEnmienda}").toString());
                    }
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el Id de Enmienda", e);
                }
                
                Map params = new HashMap();
                params.put("idOperacion", idOperacion);
                params.put("idEnmienda", idEnmienda);
                LOGGER.warning("Ejecuta inicializacion de Matriz Tcc Grid");
                executeOperBinding(params, INI_MATRIZ_TCC_GRID_OPER);
            }
        }
    }
    
    public boolean actualizarEstadoTermino(Accion accion){
        
        String msg = null;
        String keyMsg = null;
        boolean esError = false;
        
        Integer idProceso = null;
        try{
            idProceso = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm");
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id del Proceso BPM", e);
        }
        
        OperationBinding oper = actualizarEstadoTCC(accion,
                                                    idProceso,
                                                    FenixModelConstants.TERMINO);
        
        if(oper != null){
            
            if(oper.getErrors().isEmpty()){
                
                Boolean result = null;
                result = (Boolean) oper.getResult();
                if(result != null){
                    
                    if(result){
                        LOGGER.warning("El Estado TCC del Termino fue actualizado correctamente");
                        //keyMsg = "tcc.ver.termino.actualizar.estado.tcc.msg.exito";
                    }else{
                        LOGGER.severe("Error el Estado TCC del Termino no fue actualizado");    
                        esError = true;
                        keyMsg = "tcc.ver.termino.actualizar.estado.tcc.msg.error";
                    }
                }else{
                    LOGGER.severe("Error el resultado obtenido no definido");
                    esError = true;    
                    keyMsg = "tcc.ver.termino.actualizar.estado.tcc.msg.error";
                }
            }else{
                LOGGER.severe("Error el Estado TCC del Termino no fue actualizado");
                esError = true;
                keyMsg = "tcc.ver.termino.actualizar.estado.tcc.msg.error";
            }
        }
        
        if(keyMsg != null){
            LOGGER.warning("Obtiene mensaje para mostrar");
            msg = getStringFromBundle(keyMsg);
        }
        
        if(esError){
            LOGGER.warning("Muestra mensaje de error");
            JSFUtils.addFacesErrorMessage(msg);
        }else{
            LOGGER.warning("Muestra mensaje de exito");
            //JSFUtils.addFacesInformationMessage(msg);
        }
        
        return !esError;
    }
    
    /**
     * Realiza la ejecucion del metodo para actualizar el Estado TCC de la Condicion por WS
     */
    public boolean actualizarEstadoCondicion(Accion accion){
        
        String msg = null;
        String keyMsg = null;
        boolean esError = false;
        
        Integer idProceso = null;
        try{
            idProceso = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm");
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id del Proceso BPM", e);
        }
        
        OperationBinding oper = actualizarEstadoTCC(accion,
                                                    idProceso,
                                                    FenixModelConstants.CONDICION);
        if(oper != null){
            
            if(oper.getErrors().isEmpty()){
                
                Boolean result = null;
                result = (Boolean) oper.getResult();
                if(result != null){
                    
                    if(result){
                        LOGGER.warning("El Estado TCC de la Condicion fue actualizado correctamente");
                        //keyMsg = "tcc.ver.condicion.actualizar.estado.tcc.msg.exito";
                    }else{
                        LOGGER.severe("Error el Estado TCC de la Condicion no fue actualizado");    
                        esError = true;
                        keyMsg = "tcc.ver.condicion.actualizar.estado.tcc.msg.error";
                    }
                }else{
                    LOGGER.severe("Error el resultado obtenido no definido");
                    esError = true;    
                    keyMsg = "tcc.ver.condicion.actualizar.estado.tcc.msg.error";
                }
            }else{
                LOGGER.severe("Error el Estado TCC de la Condicion no fue actualizado");
                esError = true;
                keyMsg = "tcc.ver.condicion.actualizar.estado.tcc.msg.error";
            }
        }
        
        if(keyMsg != null){
            LOGGER.warning("Obtiene mensaje para mostrar");
            msg = getStringFromBundle(keyMsg);
        }
        
        if(esError){
            LOGGER.warning("Muestra mensaje de error");
            JSFUtils.addFacesErrorMessage(msg);
        }else{
            LOGGER.warning("Muestra mensaje de exito");
            //JSFUtils.addFacesInformationMessage(msg);
        }
        
        return !esError;
    }
    
    public boolean actualizarEstadoComision(Accion accion){
        
        String msg = null;
        String keyMsg = null;
        boolean esError = false;
        
        Integer idProceso = null;
        try{
            idProceso = (Integer) ADFUtils.getBoundAttributeValue("IdProcesoBpm");
        }catch(Exception e){
            LOGGER.severe("Error al obtener el Id del Proceso BPM", e);
        }
        
        OperationBinding oper = actualizarEstadoTCC(accion,
                                                    idProceso,
                                                    FenixModelConstants.COMISION);
        
        if(oper != null){
            
            if(oper.getErrors().isEmpty()){
                
                Boolean result = null;
                result = (Boolean) oper.getResult();
                if(result != null){
                    
                    if(result){
                        LOGGER.warning("El Estado TCC de la Comision fue actualizado correctamente");
                        //keyMsg = "tcc.ver.comision.actualizar.estado.tcc.msg.exito";
                    }else{
                        LOGGER.severe("Error el Estado TCC de la Comision no fue actualizado");    
                        esError = true;
                        keyMsg = "tcc.ver.comision.actualizar.estado.tcc.msg.error";
                    }
                }else{
                    LOGGER.severe("Error el resultado obtenido no definido");
                    esError = true;    
                    keyMsg = "tcc.ver.comision.actualizar.estado.tcc.msg.error";
                }
            }else{
                LOGGER.severe("Error el Estado TCC de la Comision no fue actualizado");
                esError = true;
                keyMsg = "tcc.ver.comision.actualizar.estado.tcc.msg.error";
            }
        }
        
        if(keyMsg != null){
            LOGGER.warning("Obtiene mensaje para mostrar");
            msg = getStringFromBundle(keyMsg);
        }
        
        if(esError){
            LOGGER.warning("Muestra mensaje de error");
            JSFUtils.addFacesErrorMessage(msg);
        }else{
            LOGGER.warning("Muestra mensaje de exito");
            //JSFUtils.addFacesInformationMessage(msg);
        }
        
        return !esError;
    }

    @SuppressWarnings("unchecked")
    public OperationBinding actualizarEstadoTCC(Accion accion,
                                                Integer idProcesoBpm,
                                                String tipoTcc){
        OperationBinding oper = null;
        
        Integer idModalidad = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null){
            idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");    
        } else {
            LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");  
        }
        
        Map params = null;
        if(accion != null &&
           idProcesoBpm != null &&
           tipoTcc != null &&
           idModalidad != null){
            
            params = new HashMap(); 
            params.put("sIdProceso",idProcesoBpm.toString());
            params.put("sTipoTCC", tipoTcc.toString());
            params.put("sAccion", accion.toString()); 
            params.put("idModalidad", idModalidad);
            oper = executeOperBinding(params, "actualizarAccionesMatrizTCC");
        }else{
            LOGGER.severe("Parametros requeridos no recibidos");
        }
        
        return oper;
    }
    
    /**
     * Realiza la eliminación física del Término
     */
    public boolean eliminarTermino(){
        
        boolean exito = false;
        OperationBinding operBorrar = executeOperBinding(null, "eliminarTerminoActual");
        if((operBorrar != null) && operBorrar.getErrors().isEmpty()) 
            exito = true;
        else{
            LOGGER.severe("Muestra los errores al ejecutar el Operator Bindings: " + 
                          operBorrar.getErrors().toString());
            JSFUtils.addFacesErrorMessage(operBorrar.getErrors().toString());
        }
                
        if(!exito){            
            LOGGER.warning("Muestra mensaje de eliminacion fallida del Termino");
            String msg = getStringFromBundle("tcc.ver.termino.eliminar.fallo");
            JSFUtils.addFacesErrorMessage(msg);
        }
        
        return exito;
    }
    
    /**
     * Realiza la eliminación física de la Condición
     */
    public boolean eliminarCondicion(){
        
        boolean exito = false;
        OperationBinding operBorrar = executeOperBinding(null, "eliminarCondicionActual");
        if((operBorrar != null) && operBorrar.getErrors().isEmpty()) 
            exito = true;
        else{
            LOGGER.severe("Muestra los errores al ejecutar el Operator Bindings: " + 
                          operBorrar.getErrors().toString());
            JSFUtils.addFacesErrorMessage(operBorrar.getErrors().toString());
        }
                
        if(!exito){
            LOGGER.warning("Muestra mensaje de eliminacion fallida de la Condicion");
            String msg = getStringFromBundle("tcc.ver.condicion.eliminar.fallo");
            JSFUtils.addFacesErrorMessage(msg);
        }
        
        return exito;
    }
    
    /**
     * Realiza la eliminación física de la Comisión
     */
    public boolean eliminarComision(){
        
        boolean exito = false;
        OperationBinding operBorrar = executeOperBinding(null, "eliminarComisionActual");
        if((operBorrar != null) && operBorrar.getErrors().isEmpty()) 
            exito = true;
        else{
            LOGGER.severe("Muestra los errores al ejecutar el Operator Bindings: " + 
                          operBorrar.getErrors().toString());
            JSFUtils.addFacesErrorMessage(operBorrar.getErrors().toString());
        }
           
        if(!exito){
            LOGGER.warning("Muestra mensaje de eliminacion fallida de la Comision");
            String msg = getStringFromBundle("tcc.ver.comision.eliminar.fallo");
            JSFUtils.addFacesErrorMessage(msg);
        }
        
        return exito;
    }

    public void cambioTipoFechaInicioCondicion(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inicia cambioTipoFechaInicioCondicion.");
        
        if (valueChangeEvent == null) {
            return;
        }
        
        LOGGER.warning("Nuevo valor de Tipo Fecha Inicio: " + valueChangeEvent.getNewValue());
        
        //Forza la actualizacion del Modelo
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        //Refresca componente de Fecha Inicio
        if(getFechaInicioUIC() != null){
            LOGGER.warning("Refresca parcialmente el campo de Fecha Inicio");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaInicioUIC());    
        }
        
        if(getPanelHeaderCondicionUIC() != null){
            LOGGER.warning("Refresca parcialmente el Panel Header de la Condicion");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelHeaderCondicionUIC());    
        }
        
        /*
        LOGGER.warning("Refresh page...");
        
        FacesContext fctx = FacesContext.getCurrentInstance();
        String page = fctx.getViewRoot().getViewId();
        ViewHandler ViewH = fctx.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fctx, page);
        UIV.setViewId(page);
        fctx.setViewRoot(UIV);
        */
        
        LOGGER.warning("Finaliza cambioTipoFechaInicioCondicion.");
    }
    
    public void calcularFechaVencimientoTermino() {
        LOGGER.warning("Entra a metodo calcularFechaVencimientoTermino");

        Timestamp fechaInicio = null;
        String fechaInicioAux = null;
        Integer idTipoFrecuenciaPlazo = null;
        String tipoFrecuenciaAux = null;
        Integer plazo = null;
        AttributeBinding fechaVencimientoBinding = null;
        try {
            if (null != ADFUtils.findControlBinding("FechaInicio")) {
                fechaInicioAux = String.valueOf(ADFUtils.findControlBinding("FechaInicio").toString());
                LOGGER.log(ADFLogger.WARNING, "Valor de la fecha inicio" + fechaInicioAux);
                if (null != fechaInicioAux && !fechaInicioAux.trim().equals("")) {
                    fechaInicio = (Timestamp) ADFUtils.findControlBinding("FechaInicio").getInputValue();
                } else {
                    LOGGER.log(ADFLogger.WARNING, "El valor de la fecha Inicio tiene la cadena vacia.");
                }
            } else {
                LOGGER.warning("la fecha es nula.");
            }

            if (null != ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo")) {
                tipoFrecuenciaAux = (String.valueOf(ADFUtils.getBoundAttributeValue("IdTcaFrecuenciaPlazo")));
                if (null != tipoFrecuenciaAux && !tipoFrecuenciaAux.trim().equals("")) {
                    idTipoFrecuenciaPlazo = Integer.valueOf(tipoFrecuenciaAux);
                } else {
                    LOGGER.log(ADFLogger.WARNING, "El tipo de frecuencia esta vacio.");
                }
            } else {
                LOGGER.warning("El id tca plazo es nulo.");
            }

            if (null != ADFUtils.getBoundAttributeValue("Plazo")) {
                plazo = (Integer.parseInt(ADFUtils.getBoundAttributeValue("Plazo").toString()));
            } else {
                LOGGER.warning("El plazo es nulo.");
            }

            if (null != ADFUtils.findControlBinding("FechaVencimiento")) {
                LOGGER.log(ADFLogger.WARNING, "Fecha vencimiento." + ADFUtils.findControlBinding("FechaVencimiento"));
                fechaVencimientoBinding = ADFUtils.findControlBinding("FechaVencimiento");
            } else {
                LOGGER.warning("fechaVencimientoBinding es nula.");
            }
        } catch (Exception e) {
            LOGGER.warning("Error en casteo de atributos.", e);
        }

        Timestamp fechaVencimiento = null;
        Calendar calendar = null;

        try {
            LOGGER.warning("fechaInicio :" + fechaInicio);
            LOGGER.warning("idTipoFrecuenciaPlazo : " + idTipoFrecuenciaPlazo);
            LOGGER.warning("plazo : " + plazo);

            if ((null != fechaInicio) && (null != idTipoFrecuenciaPlazo) && (null != plazo)) {
                LOGGER.warning("Iniciando calculo de fechaVencimiento");
                LOGGER.warning("fecha de Inicio: " + fechaInicio);
                LOGGER.warning("Formato: " + getStringFromBundle("TERMINO_FORMATO_FECHA"));
//                SimpleDateFormat formatter = new SimpleDateFormat(getStringFromBundle("TERMINO_FORMATO_FECHA"));
//                Date date = formatter.parse(fechaInicio.getInputValue().toString());
//                Timestamp fechaInicioTimestamp = new Timestamp(date.getTime());
                calendar = Calendar.getInstance();
                calendar.setTime(fechaInicio);
                Integer plazoInteger = plazo;
                //Se cambia if's por switch
                switch (idTipoFrecuenciaPlazo) {
                case 1:
                    calendar.add(Calendar.DATE, plazoInteger);
                    break;
                case 2:
                    calendar.add(Calendar.MONTH, plazoInteger);
                    break;
                case 3:
                    calendar.add(Calendar.YEAR, plazoInteger);
                    break;
                default:
                    LOGGER.log(ADFLogger.WARNING, "No hay frecuencia que agregar a la fecha inicio de capital.");
                    break;
                }

                fechaVencimiento = new Timestamp(calendar.getTime().getTime());
                LOGGER.warning("FechaVencimientoCalculada: " + fechaVencimiento);
                fechaVencimientoBinding.setInputValue(fechaVencimiento);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaVencimientoTerminoUIC());
                AdfFacesContext.getCurrentInstance().addPartialTarget(getActualizarTerminoFormUI());
            } else {
                LOGGER.log(ADFLogger.WARNING, "Se setea valor nulo a Fecha de vencimiento.");
                fechaVencimientoBinding.setInputValue(null);
            }
        } catch (Exception e) {
            LOGGER.warning("Error al convertir fecha", e);
        }
        LOGGER.warning("Termina metodo calcularFechaVencimientoTermino");
    }

    public void actualizarVencimientoPorFechaInicio(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Entra a metodo actualizarVencimientoPorFechaInicio");
        Boolean respuesta = getEsCalcularFechaVencimientoTermino();
        AttributeBinding fechaVencimiento;
        fechaVencimiento = ADFUtils.findControlBinding("FechaVencimiento");
        Timestamp fechaInicioTmStmp = null;
        
        try{
            fechaInicioTmStmp = (Timestamp) ADFUtils.getBoundAttributeValue("FechaInicio");
            LOGGER.warning("FECHAINICIO de BOUNDATTRIBUTE: " + fechaInicioTmStmp);
        }catch(Exception e){
            LOGGER.warning("ERROR al recuperar boundAttributeBinding de fechaInicio.", e);
        }
        
        if(respuesta){
            if(null != valueChangeEvent.getNewValue()){
                LOGGER.warning("Nuevo valor de Fecha Incicio: " + valueChangeEvent.getNewValue());
                Timestamp fechaInicioVce = null;
                String strFechaInicio = null;
                try{
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//                    
//                    strFechaInicio = (String) valueChangeEvent.getNewValue();
//                    Date date = sdf.parse(strFechaInicio);
//                    strFechaInicio = dateFormat.format(date);
//                    
//                    //strFechaInicio = parsedDate.toString();
//                    LOGGER.warning("STRFechaInicio: " + strFechaInicio);
                    fechaInicioVce = (Timestamp) valueChangeEvent.getNewValue();
                    LOGGER.warning("Fecha de inicio:." + fechaInicioVce);
                }catch(Exception e){
                    LOGGER.warning("ERROR al castear la fecha inicio.", e);
                }
                
                AttributeBinding fechaInicio;
                fechaInicio = ADFUtils.findControlBinding("FechaInicio");
                
                if(null != fechaInicioVce){
                    fechaInicio.setInputValue(fechaInicioVce);
                }else{
                    LOGGER.warning("FechaInicioVCE es NULL.");
                }
                
                try{
                    if(null != fechaInicio.getInputValue() && !fechaInicio.getInputValue().toString().trim().equals("")){
                        LOGGER.warning("Nueva fechaInicio: " + fechaInicio.getInputValue().toString());
                        LOGGER.warning("Invocando servicio calcularFechaVencimientoTermino");
                        calcularFechaVencimientoTermino();
                    } else {
                        fechaVencimiento.setInputValue(null);
                    }
                }catch(Exception e){
                    LOGGER.warning("ERROR al calcular fecha de vencimiento.", e);
                }
            }else{
                fechaVencimiento.setInputValue(null);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaVencimientoTerminoUIC());
                AdfFacesContext.getCurrentInstance().addPartialTarget(getActualizarTerminoFormUI());
            }
        }
        LOGGER.warning("Finzaliza metodo actualizarVencimientoPorFechaInicio");
    }

    public void actualizarVencimientoPorPlazo(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Entra a metodo actualizarVencimientoPorPlazo");
        
        AttributeBinding fechaVencimiento;
        fechaVencimiento = ADFUtils.findControlBinding("FechaVencimiento");
        
        if(null != valueChangeEvent.getNewValue()){
            Integer nuevoValor = (Integer) valueChangeEvent.getNewValue();
            if(nuevoValor > 0){
            Boolean respuesta = getEsCalcularFechaVencimientoTermino();
                if(respuesta){                
                    LOGGER.warning("Nuevo valor de Plazo: " + valueChangeEvent.getNewValue());
                    AttributeBinding plazo;
                    plazo = ADFUtils.findControlBinding("Plazo");
                    plazo.setInputValue(valueChangeEvent.getNewValue());
                    LOGGER.warning("Nuevo plazo: " + plazo.getInputValue().toString());
                    if(null != plazo.getInputValue() && !plazo.getInputValue().toString().trim().equals("")){    
                        LOGGER.warning("Invocando servicio calcularFechaVencimientoTermino");
                        calcularFechaVencimientoTermino();
                    }
                }
            } else {
                LOGGER.warning("Muestra mensaje de error");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("TERMINO_ERROR_PLAZO"));
            }
        }else{
            fechaVencimiento.setInputValue(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaVencimientoTerminoUIC());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getActualizarTerminoFormUI());
        }
        LOGGER.warning("Finzaliza metodo actualizarVencimientoPorPlazo");
    }

    public void actualizarVencimientoPorTipoPlazo(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Entra a metodo actualizarVencimientoPorTipoPlazo");
        AttributeBinding fechaVencimiento;
        fechaVencimiento = ADFUtils.findControlBinding("FechaVencimiento");
        Boolean respuesta = getEsCalcularFechaVencimientoTermino();
        if(respuesta){
            if(null != valueChangeEvent.getNewValue()){
                LOGGER.warning("Nuevo valor de TipoPlazo: " + valueChangeEvent.getNewValue());
                AttributeBinding idTipoFrecuenciaPlazo;
                idTipoFrecuenciaPlazo = ADFUtils.findControlBinding("IdTcaFrecuenciaPlazo");
                idTipoFrecuenciaPlazo.setInputValue(valueChangeEvent.getNewValue());
                LOGGER.warning("Nuevo TipoPlazo: " + idTipoFrecuenciaPlazo.getInputValue().toString());
                if(null != idTipoFrecuenciaPlazo.getInputValue()){
                    LOGGER.warning("Invocando servicio calcularFechaVencimientoTermino");
                    calcularFechaVencimientoTermino();
                }
            }else{
                fechaVencimiento.setInputValue(null);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getFechaVencimientoTerminoUIC());
                AdfFacesContext.getCurrentInstance().addPartialTarget(getActualizarTerminoFormUI());
            }
        }
        LOGGER.warning("Finzaliza metodo actualizarVencimientoPorTipoPlazo");
    }

    public void abrirDialogAsociarContrapartesActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE,
                   "Inside abrirDialogAsociarContrapartesActionListener: " + actionEvent.getComponent().getId());
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupAsociarContrapartes().show(popupHints);
    }

    public void cerrarDialogAsociarContrapartesActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE,
                   "Inside cerrarDialogAsociarContrapartesActionListener: " + actionEvent.getComponent().getId());
        // Limpiamos tabla y panel de búsqueda dentro del popup
        resetPopupAsociarContrapartes();
        // Cerramos popup
        getPopupAsociarContrapartes().hide();
    }
    
    private void resetPopupAsociarContrapartes() {
        LOGGER.log(ADFLogger.TRACE, "Inside resetPopupAsociarContrapartes.");

        DCIteratorBinding buscarClienteIterator = null;
        ViewObject buscarClienteVO = null;

        // Hacemos un reset en panel de búsqueda antes de cerrar el popup
        QueryModel queryModel = queryBuscarCliente.getModel();
        QueryDescriptor queryDescriptor = queryBuscarCliente.getValue();
        queryModel.reset(queryDescriptor);
        queryBuscarCliente.refresh(FacesContext.getCurrentInstance());

        // Limpiamos tabla
        buscarClienteIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("BuscarClienteVOIterator");
        buscarClienteVO = buscarClienteIterator.getViewObject();
        buscarClienteVO.executeEmptyRowSet();
        
    }
    
    public void asociarContrapartesActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside asociarContrapartesActionListener:" + actionEvent.getComponent().getId());
        BindingContainer bindings = getBindings();
        OperationBinding asociarContrapartes = bindings.getOperationBinding("asociarContrapartes");
        DCIteratorBinding buscarClienteIterator = null;
        ViewObject buscarClienteVO = null;

        buscarClienteIterator = ADFUtils.getDCBindingContainer().findIteratorBinding("BuscarClienteVOIterator");
        buscarClienteVO = buscarClienteIterator.getViewObject();
        Row[] contraparteSeleccionadasRows = buscarClienteVO.getFilteredRows("SeleccionarCliente", true);
        for (Row row : contraparteSeleccionadasRows) {
            asociarContrapartes.getParamsMap().put("row", row);
            asociarContrapartes.execute();

            if (asociarContrapartes.getErrors().size() != 0) {
                // Manejo de errores

            }
        }

        // Limpiamos tabla y panel de búsqueda dentro del popup
        resetPopupAsociarContrapartes();

        // Cerramos popup
        getPopupAsociarContrapartes().hide();
    
    }
    
    public void eliminarContrapartesAsociadasActionListener(ActionEvent actionEvent) {
        LOGGER.warning("Dentro de eliminarContrapartesAsociadasActionListener");
        
        RichPopup.PopupHints popupHints = null;
        
        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        getPopupConfirmarDesasociarContrapartes().show(popupHints);
        
        LOGGER.warning("abrir popupConfirmarDesasociarContrapartes");
        
        LOGGER.warning("Fuera de eliminarContrapartesAsociadasActionListener");
    }
    
    public void aceptarEliminarContrapartesAsociadasActionListener(ActionEvent actionEvent) {
        LOGGER.warning("Dentro de aceptarEliminarContrapartesAsociadasActionListener");
        Long IdContraparte = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Row rowVencimiento = null;
        
        if(null != JSFUtils.resolveExpression("#{bindings.IdContraparte.inputValue}")){
            IdContraparte=(Long)JSFUtils.resolveExpression("#{bindings.IdContraparte.inputValue}");
            LOGGER.warning("IdContraparte :"+IdContraparte);
        }else{
            LOGGER.severe("bindings.IdContraparte.inputValue es nulo");
        }
        
        operationBinding = bindings.getOperationBinding("desasociarContrapartes");
        operationBinding.getParamsMap().put("IdContraparte",IdContraparte);
        
        // Invocamos método
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        // Cerramos dialog
        getPopupConfirmarDesasociarContrapartes().hide();
        
        LOGGER.warning("cierra popupConfirmarDesasociarContrapartes");
        
        LOGGER.warning("Fuera de aceptarEliminarContrapartesAsociadasActionListener");
    }

    public void cancelarEliminarContrapartesAsociadasActionListener(ActionEvent actionEvent) {
        LOGGER.log(ADFLogger.TRACE, "Inside cancelarEliminarContrapartesAsociadasActionListener:" 
                                    + actionEvent.getComponent().getId());
        getPopupConfirmarDesasociarContrapartes().hide();
    }

    public void setPopupAsociarContrapartes(RichPopup popupAsociarContrapartes) {
        this.popupAsociarContrapartes = popupAsociarContrapartes;
    }

    public RichPopup getPopupAsociarContrapartes() {
        return popupAsociarContrapartes;
    }

    public void setQueryBuscarCliente(RichQuery queryBuscarCliente) {
        this.queryBuscarCliente = queryBuscarCliente;
    }

    public RichQuery getQueryBuscarCliente() {
        return queryBuscarCliente;
    }

    public void setPopupConfirmarDesasociarContrapartes(RichPopup popupConfirmarDesasociarContrapartes) {
        this.popupConfirmarDesasociarContrapartes = popupConfirmarDesasociarContrapartes;
    }

    public RichPopup getPopupConfirmarDesasociarContrapartes() {
        return popupConfirmarDesasociarContrapartes;
    }
    
        
    private Boolean comisionValida() {
        Boolean valida = true;
        String msg="";               
        ComisionManagedBean comisionManagedBean = (ComisionManagedBean)JSFUtils.resolveExpression("#{pageFlowScope.comisionManagedBean}");
        
        LOGGER.warning("Validando campos comision");
                                           
        AttributeBinding idTcaTasa= ADFUtils.findControlBinding("IdTcaTipoTasa");
        AttributeBinding porcentaje= ADFUtils.findControlBinding("PorcentajeSobreMontoBase");      
        AttributeBinding montoComision = ADFUtils.findControlBinding("MontoComision");
        AttributeBinding tipoMontoBase = ADFUtils.findControlBinding("IdTcaMontoBase");
        AttributeBinding montoBase = ADFUtils.findControlBinding("MontoBase");
        AttributeBinding idTipoFrecuencia = ADFUtils.findControlBinding("IdTcaTipoFrecuencia");
        AttributeBinding frecuenciaPago = ADFUtils.findControlBinding("FrecuenciaPago");
        
        AttributeBinding fechaValor = ADFUtils.findControlBinding("FechaValor");
        AttributeBinding fechaInicioCapital = ADFUtils.findControlBinding("FechaInicioCapital");
        AttributeBinding fechaVencimiento = ADFUtils.findControlBinding("FechaVencimiento");
        
        Timestamp valorFecha=(Timestamp)fechaValor.getInputValue();
        Timestamp inicioCapitalFecha=(Timestamp)fechaInicioCapital.getInputValue();
        Timestamp vencimientoFecha=null;
        if(null!=fechaVencimiento.getInputValue()){
        vencimientoFecha=(Timestamp)fechaVencimiento.getInputValue();
        }
        Timestamp flexcubeFecha=null;
            try {
                flexcubeFecha = comisionManagedBean.getFechaFlexcubeActual().timestampValue();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
            LOGGER.warning("Valida tasa");         
            if(null!=idTcaTasa.getInputValue()){
                
                Integer valorIdTasa = null;
                try{
                    LOGGER.warning("Valor Id TCA Tasa: " + idTcaTasa.getInputValue().toString());
                    //valorIdTasa = (Integer)idTcaTasa.getInputValue();    
                    valorIdTasa = Integer.parseInt(idTcaTasa.getInputValue().toString());
                }catch(Exception e){
                    LOGGER.severe("Error al obtener y convertir valor Id TCA Tasa", e);
                }
                
            
            if(valorIdTasa != null &&
               valorIdTasa.compareTo(FenixConstants.COMISION_TASA)==0){
                if(null!=porcentaje){
                        if(null!=porcentaje.getInputValue()){
                            
                            BigDecimal valorPorcentaje = null;
                            try{
                                //valorPorcentaje = (BigDecimal) porcentaje.getInputValue(); 
                                valorPorcentaje = BigDecimal.valueOf(Double.valueOf(porcentaje.getInputValue().toString()));
                            }catch(Exception e){
                                LOGGER.severe("Error al obtener y convertir valor de PorcentajeSobreMontoBase", e);
                            }
                            
                                
                            if(null == valorPorcentaje) {
                            
                            msg = getStringFromBundle("MSG_ERROR_PORCENTAJE_VACIO");
                            JSFUtils.addFacesErrorMessage(msg);
                            valida = false;
                            }
                            else{
                                    if(valorPorcentaje.doubleValue()<= 0 || valorPorcentaje.doubleValue()>=100) {
                                    
                                    msg = getStringFromBundle("MSG_ERROR_PORCENTAJE_SOBREPASA");
                                    JSFUtils.addFacesErrorMessage(msg);
                                    valida = false;
                                    }
                                
                                }
                                
                        }
                        else{
                        msg = getStringFromBundle("MSG_ERROR_PORCENTAJE_VACIO");
                        JSFUtils.addFacesErrorMessage(msg);
                        valida = false;
                        }  
                    }
            else{
            msg = getStringFromBundle("MSG_ERROR_PORCENTAJE_VACIO");
            JSFUtils.addFacesErrorMessage(msg);
            valida = false;
            }  
                
                
//            if(null==montoBase.getInputValue()){
//            msg = getStringFromBundle("MSG_ERROR_MONTO_BASE");
//            JSFUtils.addFacesErrorMessage(msg);
//            valida = false;
//            }
                        
        }
            if(valorIdTasa == null){
                LOGGER.warning("El valor IdTcaTipoTasa es NULL");
                msg = getStringFromBundle("MSG_ERROR_COMISION");
                 JSFUtils.addFacesErrorMessage(msg);
                valida = false;
            }else{
                if(null==montoComision.getInputValue() && (null==tipoMontoBase.getInputValue() || valorIdTasa.compareTo(FenixConstants.COMISION_MONTO)==0)){
                 msg = getStringFromBundle("MSG_ERROR_COMISION");
                  JSFUtils.addFacesErrorMessage(msg);
                 valida = false;
                  }
            }
        }
       else{
       msg = getStringFromBundle("MSG_ERROR_ID_TASA");
       JSFUtils.addFacesErrorMessage(msg);
        valida = false;
        }
                        
        if(null!=idTipoFrecuencia.getInputValue()){
           Integer valorTipoFrecuencia=(Integer) idTipoFrecuencia.getInputValue();
           Integer frecuenciaVencimiento=4;
              
               if(valorTipoFrecuencia.compareTo(frecuenciaVencimiento)!=0){
                        if(null==frecuenciaPago.getInputValue() || 
                        (Integer.valueOf(frecuenciaPago.getInputValue().toString()).compareTo(0) == 0)){
                        msg = getStringFromBundle("MSG_ERROR_FRECUENCUA");
                        JSFUtils.addFacesErrorMessage(msg);
                        valida = false;
                        }
                }
        }
        else{
        msg = getStringFromBundle("MSG_ERROR_ID_FRECUENCUA");
       JSFUtils.addFacesErrorMessage(msg);
       valida = false;
      }  
        LOGGER.warning("Validando fecha vencimiento:" + vencimientoFecha);
        LOGGER.warning("Validando fecha valor:" + valorFecha);
        LOGGER.warning("Validando fecha capital:" + inicioCapitalFecha);
        LOGGER.warning("Validando fecha flexcube:" + flexcubeFecha);
        
        
//                if (null!= vencimientoFecha && (vencimientoFecha.compareTo(flexcubeFecha))<=0) {
//                    msg = getStringFromBundle("MSG_ERROR_FECHA_VENCIMIENTO2");
//                    JSFUtils.addFacesErrorMessage(msg);
//                    valida = false;
//                }
//        if(null!= flexcubeFecha){
//                SimpleDateFormat  fechaFlex=new SimpleDateFormat("dd/MMM/yyyy");
//                if (null!=valorFecha && valorFecha.compareTo(flexcubeFecha)>0) {
//                    msg =
//                        "La Fecha vigencia de préstamo debe ser menor o igual a la Fecha Flexcube " + fechaFlex.format(flexcubeFecha) + ".";
//                    JSFUtils.addFacesErrorMessage(msg);
//                    valida = false;
//                }
//        }
//               if ((null!= vencimientoFecha && inicioCapitalFecha.compareTo(valorFecha) < 0 ) || (null!= vencimientoFecha && inicioCapitalFecha.compareTo(vencimientoFecha)>0)) {
//                    msg = getStringFromBundle("MSG_ERROR_FECHA_INICIO_CAPITAL2");
//                    JSFUtils.addFacesErrorMessage(msg);
//                    valida = false;
//                }
          
            return valida;
    }

    @SuppressWarnings("unchecked")
    private void consultarContrapartesTermino(){
        LOGGER.warning("Entrando en consultarContrapartesTermino.");
        BindingContainer bindings = getBindings();
        String id = null;
        OperationBinding voConsultarContrapartesTermino = bindings.getOperationBinding("setidTerminoContrapartes");
        if(JSFUtils.resolveExpression("#{bindings.Id.inputValue}") != null){
            id = JSFUtils.resolveExpression("#{bindings.Id.inputValue}").toString();
        }
        
        LOGGER.warning("Id termino contrapartes: " + id);
        
        if(id != null){
            try{
                voConsultarContrapartesTermino.getParamsMap().put("value",new oracle.jbo.domain.Number(id));
                voConsultarContrapartesTermino.execute();
                if (!voConsultarContrapartesTermino.getErrors().isEmpty()) {
                    // Manejo de errores
                }
            }catch(Exception e){
                LOGGER.severe("Error al convertir el Id en Number into consultarContrapartesTermino ", e);
            }
        }
    }

    public String getConsultarContrapartes() {
        //consultar ContrapartesTerminoVO
        this.consultarContrapartesTermino();
        return consultarContrapartes;
    }

    public RichOutputText getOtInitFormContrapartes() {
        //consultar ContrapartesTerminoVO
        this.consultarContrapartesTermino();
        return otInitFormContrapartes;
    }


    public void setOtInitFormContrapartes(RichOutputText otInitFormContrapartes) {
        this.otInitFormContrapartes = otInitFormContrapartes;
    }

    public void setToolbarAccionesUIC(RichToolbar toolbarAccionesUIC) {
        this.toolbarAccionesUIC = toolbarAccionesUIC;
    }

    public RichToolbar getToolbarAccionesUIC() {
        return toolbarAccionesUIC;
    }
    
    public void setToolbarAccionesGridUIC(RichToolbar toolbarAccionesGridUIC) {
        this.toolbarAccionesGridUIC = toolbarAccionesGridUIC;
    }

    public RichToolbar getToolbarAccionesGridUIC() {
        return toolbarAccionesGridUIC;
    }
    
    public Boolean getEsCalcularFechaVencimientoTermino(){
        LOGGER.warning("Entra a metodo getEsCalcularFechaVencimientoTermino");
        Boolean retorno = null;
        AttributeBinding IdTcaTermino;
        IdTcaTermino = ADFUtils.findControlBinding("IdTcaTermino");
        
        if(null != IdTcaTermino.getInputValue()){
            String idTcaTerminoString = IdTcaTermino.getInputValue().toString();
            if(idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_FINANCIAMIENTO")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_GARANTIA")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_PERIODO_GRACIA"))){
                   LOGGER.warning("No aplica calculo de fecha de vencimiento");
                   retorno = Boolean.FALSE;
            } else {
                retorno = Boolean.TRUE;
            }
        } else {
            LOGGER.warning("Valor de TCATERMINO vacío");
            retorno = Boolean.FALSE;
        }
        LOGGER.warning("Termina metodo getEsCalcularFechaVencimientoTermino");
        return retorno;
    }

    public void esFrecuenciaAlVencimiento(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Entra a metodo esFrecuenciaAlVencimiento");
        AttributeBinding idTipoFrecuencia;
        idTipoFrecuencia =  ADFUtils.findControlBinding("IdTcaFrecuenciaPagoInteres");
        if(null != valueChangeEvent.getNewValue()){
            idTipoFrecuencia.setInputValue(valueChangeEvent.getNewValue());
            LOGGER.warning("Nuevo valor de tipo de Frecuencia de Pago de Interés: " + idTipoFrecuencia.toString());
            if(idTipoFrecuencia.getInputValue().toString().equals(getStringFromBundle("TERMINO_TIPO_FRECUENCIA_VENCIMIENTO"))){
                LOGGER.warning("Es frecuencia Al Vencimiento");
                setEsFrecuenciaAlVencimiento(Boolean.TRUE);
            }else{
                setEsFrecuenciaAlVencimiento(Boolean.FALSE);
            }
        }
        LOGGER.warning("Termina metodo esFrecuenciaAlVencimiento");
    }

    public void setEsFrecuenciaAlVencimiento(boolean esFrecuenciaAlVencimiento) {
        this.esFrecuenciaAlVencimiento = esFrecuenciaAlVencimiento;
    }

    public boolean isEsFrecuenciaAlVencimiento() {
        return esFrecuenciaAlVencimiento;
    }

    public void validarMontoMAyorCero(ValueChangeEvent valueChangeEvent) {
        BigDecimal monto = new BigDecimal(valueChangeEvent.getNewValue().toString());
        if(null != monto && monto.compareTo(BigDecimal.ZERO)==0){
            JSFUtils.addFacesErrorMessage(getStringFromBundle("TERMINO_ERROR_MONTO"));
        }
    }

    public void validarPorcentajeCobertura(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Entra a metodo validarPorcentajeCobertura");
        if(null != valueChangeEvent && null != valueChangeEvent.getNewValue()){
            BigDecimal porcentajeNuevo = new BigDecimal(valueChangeEvent.getNewValue().toString());
            if((porcentajeNuevo.compareTo(BigDecimal.ZERO)==0 || porcentajeNuevo.compareTo(BigDecimal.ZERO)==-1) || porcentajeNuevo.compareTo(new BigDecimal(100))==1){
                JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_MSG_PORCENTAJE"));
            }
        }
    }

    public Boolean validarTerminos() {
        LOGGER.warning("Entra a metodo validarTerminos");
        Boolean retorno = Boolean.TRUE;
        AttributeBinding idTcaTermino;
        idTcaTermino = ADFUtils.findControlBinding("IdTcaTermino");
        if(null != idTcaTermino.getInputValue() && !idTcaTermino.getInputValue().toString().equals("")){
            String idTcaTerminoString = idTcaTermino.getInputValue().toString();
            if(idTcaTerminoString.equals(getStringFromBundle("TERMINO_CONTRAPARTE_ESTRUCTURADOR")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_CONTRAPARTE_BENEFICIARIOS")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_CONTRAPARTE_ADMINISTRADOR")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_CONTRAPARTE_PARTICIPANTES")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_CONTRAPARTE_FINANCIADORES")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_CONTRAPARTE_ESPECIFICAS"))){
                    RowSetIterator iter = 
                        ADFUtils.getDCBindingContainer().findIteratorBinding("ContrapartesTerminoVOIterator").getRowSetIterator();
                    LOGGER.warning("Rows de iterador de Contrapartes " + iter.getRowCount());
                    if(null == iter || iter.getRowCount() <= 0){
                        retorno = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("TERMINO_CONTRAPARTES_VACIO"));
                    }
               }
            if(idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_FORMALIZACION")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_INICIO_DESEMBOLSO")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_DESEMBOLSO_TOTALIDAD")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_LINEA")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_PRESENTAR_INFORMES")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_FINANCIAMIENTO")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_GARANTIA")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_PERIODO_GRACIA")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_DIVULGACION")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_SOLCIITUD_FINANCIAMIENTO")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_EVALUACION_MEDIO_TERMINO")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_SEGUIMIENTO_SIEMAS"))){
                
                   AttributeBinding plazo;
                   plazo = ADFUtils.findControlBinding("Plazo");
                   
                   if(null != plazo.getInputValue() && !plazo.getInputValue().toString().trim().equals("")){
                       Integer plazoInt = Integer.parseInt(plazo.getInputValue().toString());
                       if(plazoInt <= 0){
                           retorno = Boolean.FALSE;
                           JSFUtils.addFacesErrorMessage(getStringFromBundle("TERMINO_ERROR_PLAZO"));
                       }
                   }
                    
                   if(!idTcaTerminoString.equals(getStringFromBundle("TERMINO_PLAZO_GARANTIA"))){
                       AttributeBinding tipoFechaInicio;
                       tipoFechaInicio = ADFUtils.findControlBinding("IdTcaTipoFechaInicio");
                       if(null != tipoFechaInicio.getInputValue() && tipoFechaInicio.getInputValue().toString().equals(getStringFromBundle("TERMINO_ID_TIPO_FECHA_INICIO_ESPECIFICA"))){
                           AttributeBinding fechaInicio;
                           fechaInicio = ADFUtils.findControlBinding("FechaInicio");
                           
                           if(null == fechaInicio.getInputValue() || fechaInicio.getInputValue().toString().equals("")){
                               retorno = Boolean.FALSE;
                               JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_FECHA_INICIO"));
                           }
                       }
                   }
               }
            
            if(idTcaTerminoString.equals(getStringFromBundle("TERMINO_MONTO_SOLICITADO")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_MONTO_FORMALIZADO")) ||
               idTcaTerminoString.equals(getStringFromBundle("TERMINO_MONTO_ESPECIFICO"))){
                   AttributeBinding monto;
                   monto = ADFUtils.findControlBinding("Monto");
                   if(null != monto.getInputValue() && !monto.getInputValue().toString().trim().equals("")){
                       BigDecimal montoBD = new BigDecimal(monto.getInputValue().toString());
                       if(montoBD.doubleValue() <= 0){
                           retorno = Boolean.FALSE;
                           JSFUtils.addFacesErrorMessage(getStringFromBundle("TERMINO_ERROR_MONTO"));
                       }
                   }
               }
            
            if(idTcaTerminoString.equals(getStringFromBundle("TERMINO_CONDICIONES_PORCENTAJE_COBERTURA"))){
                AttributeBinding porcentaje;
                porcentaje = ADFUtils.findControlBinding("PorcentajeCobertura");
                if(null != porcentaje){
                   BigDecimal porcentajeNuevo = new BigDecimal(porcentaje.toString());
                   if((porcentajeNuevo.compareTo(BigDecimal.ZERO)==0 || porcentajeNuevo.compareTo(BigDecimal.ZERO)==-1) || porcentajeNuevo.compareTo(new BigDecimal(100))==1){
                       retorno = Boolean.FALSE;
                       JSFUtils.addFacesErrorMessage(getStringFromBundle("TCC_MSG_PORCENTAJE"));
                   }
                }
            }
        }
        return retorno;
    }
    
    public void activarFechaFlexcube(ActionEvent actionEvent){
        
            AttributeBinding fechaValor = ADFUtils.findControlBinding("FechaValor");
            AttributeBinding fechaInicioCapital = ADFUtils.findControlBinding("FechaInicioCapital");
            
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerFechaFlexcube");
            oracle.jbo.domain.Timestamp result=(oracle.jbo.domain.Timestamp)operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                LOGGER.warning("Error");
                JSFUtils.addFacesErrorMessage("Error al obtener la fecha Flexcube");
            }
            else{
                    ComisionManagedBean comisionManagedBean = (ComisionManagedBean)JSFUtils.resolveExpression("#{pageFlowScope.comisionManagedBean}");
                    comisionManagedBean.setFechaFlexcubeActual(result);
                if(null==fechaValor.getInputValue()){
                       // fechaValor.setInputValue(result);
                    }
                    if(null==fechaInicioCapital.getInputValue()){
                         // fechaInicioCapital.setInputValue(result);
                        }
                }
        }
    
    /*
     * 
     * Construcción invocación de reporte de TCC.
     * 26/04/2016
     * Gabriel Niño Rosales
     * 
     */

    public void reporteTccFileDownloadActionListener(FacesContext facesContext, OutputStream outputStream) {
        // Add event code here...
        Long idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
        byte[] reporteTcc = null;
        BindingContainer bindings = getBindings();
        try{
        applyIE11Fix(REPORTE_FILE_NAME);    
        OperationBinding operationBinding = bindings.getOperationBinding("crearReporteTcc");
        operationBinding.getParamsMap().put("idOperacion", idOperacion); 
        reporteTcc= (byte[])operationBinding.execute();
        //cerrar popup
        getPopUpCrearReporteTCC().hide();
            
        if(reporteTcc!= null && reporteTcc.length>0){
            outputStream.write(reporteTcc);
        }
            
        }catch(IOException ioex){
            ioex.printStackTrace();
        } finally{
            flushAndCloseOutputStream(outputStream);
        }

    }
    
    
    public StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);       
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);        
        return writer;
    }
    
    private void applyIE11Fix(String docName){
        
        RequestContext requestCtx = RequestContext.getCurrentInstance();
        Agent agent = requestCtx.getAgent();
        String version = agent.getAgentVersion();
        String browser = agent.getAgentName();

        if (browser != null && "ie".equals(browser) && version != null && version.startsWith("11")){

            HttpServletResponse resp =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            String finalName = docName;

            try{
                finalName = URLEncoder.encode(docName, "UTF-8");
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }

            resp.setHeader("content-disposition", "attachment; filename=\"" + finalName + "\"");
            resp.setContentType("application/octet-stream");
        }
    }
    
    private void flushAndCloseOutputStream(OutputStream output){
        flushOutputStream(output);
        closeOutputStream(output);
    }

    private void flushOutputStream(OutputStream output){
        try{
            if(output != null)
                output.flush();
        }catch(IOException ioex){
            ioex.printStackTrace();}
    }

    private void closeOutputStream(OutputStream output){
        try{
            if(output != null)
                output.close();
        }catch(IOException ioex){
            ioex.printStackTrace();}
    }

    public String getREPORTE_FILE_NAME() {
        return REPORTE_FILE_NAME;
    }


    public void abrirPopUpCrearReporteTCCActionListener(ActionEvent actionEvent) {
        // Add event code here...
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopUpCrearReporteTCC().show(popupHints);
    }

    public void setPopUpCrearReporteTCC(RichPopup popUpCrearReporteTCC) {
        this.popUpCrearReporteTCC = popUpCrearReporteTCC;
    }

    public RichPopup getPopUpCrearReporteTCC() {
        return popUpCrearReporteTCC;
    }

    public void cancelarCrearReporteTCCActionListener(ActionEvent actionEvent) {
        // Add event code here...
        getPopUpCrearReporteTCC().hide();
    }
/*
 * Termina Construcción invocación de reporte de TCC.
 */
    /**
     * Cambia el estado de tcc de un elemento en el Grid a Eliminada (Id Estado TCC = 22) 
     * de forma independiente a los diagramas de estados TCC
     * @param dialogEvent contiene objeto del evento de confirmacion
     */
    @SuppressWarnings("unchecked")
    public void popupBorrarTccGridDialogListener(DialogEvent dialogEvent) {
        LOGGER.warning("Entrando en popupBorrarTccGridDialogListener.");
        ExtendedRenderKitService erks = null;
        
        if(dialogEvent != null){
            
            if(dialogEvent.getOutcome() == DialogEvent.Outcome.ok){
                
                //Identifica el tipo de TCC
                Number numTipoTcc = null;
                try{
                    numTipoTcc = (Number) ADFUtils.getBoundAttributeValue("IdLinkVO");
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el Tipo del elemento TCC");
                }
                
                //Obtiene el id de la tcc a borrar
                Number numIdTcc = null; 
                try{
                    numIdTcc = (Number) ADFUtils.getBoundAttributeValue("IdTccVO");
                }catch(Exception e){
                    LOGGER.severe("Error al obtener el Id del elemento TCC");
                }
                
                Map params = null;
                params = new HashMap();
                params.put("tipoTcc", numTipoTcc);
                params.put("idTcc", numIdTcc);
                
                //Ejecuta operacion para borrar la TCC
                OperationBinding oper = executeOperBinding(params, "borrarElementoTCCGrid");
                if(oper != null &&
                   !oper.getErrors().isEmpty()){
                    
                    LOGGER.warning("Error al eliminar el elemento Grid TCC. " + oper.getErrors().toString());    
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("BORRAR_MSG_ERROR"));
                }else{
                    //Reejecuta Modelo de datos
                    reiniciarMatrizTccModel();
                    
                    //Recargar enmienda
                    try {
                        LOGGER.warning("pIdTareaBpm: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"));
                        
                        // Invocamos mediante javascript el método que actualiza datos en la tarea de Ingresar enmienda.
                        if((JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}") != null) 
                           && (Integer.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}").toString()).intValue() 
                               == FenixConstants.PAC04_INGRESAR_ENMIENDA)) {
                            
                            erks = Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
                            erks.addScript(FacesContext.getCurrentInstance(), 
                                           "refreshDatosEnmienda('" + 
                                           JSFUtils.resolveExpression("#{pageFlowScope.pClientIdBtnRefreshEnmienda}") +"');");
                        }
                    } catch (Exception e) {
                        LOGGER.warning("Error en la actualizacion de enmiendas.", e);
                    }
                    
                }
            }else{
                //Codigo que no realiza ninguna accion sobre la TCC
                LOGGER.warning("Se presiona cancelar.");
            }
        } else {
            LOGGER.warning("DialogEvent es nulo.");
        }
    }

    public void setPanelButtonGrid(RichPanelGroupLayout panelButtonGrid) {
        this.panelButtonGrid = panelButtonGrid;
    }

    public RichPanelGroupLayout getPanelButtonGrid() {
        return panelButtonGrid;
    }

    public void treeRowTccSelectionListener(SelectionEvent selectionEvent) {
        LOGGER.warning("Dentro de treeRowTccSelectionListener");
        MatrizTccBean matrizTccBean = null;
        Boolean esEstadoCompletado = Boolean.FALSE;
        try{
            
            matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
            esEstadoCompletado = matrizTccBean.getEsEstadoCompletado();
            LOGGER.warning("esEstadoCompletado :"+esEstadoCompletado);
        
            if(esEstadoCompletado){
                LOGGER.warning("No realizar ningun accion esEstadoCompletado es true , mostrar solo lectura tree");
            }else{
                LOGGER.warning("Se ejecuta makeCurrent, flujo normal");
                // Incidencia FNXII-3457: Reasignamos las bind variables de los VOs hijos de TccTreeRoot[TCC]
                //reinicializarTrees(); // Se tiene que invocar antes del makeCurrent o NO funciona (se pierde el valor del current)
                inicializarTreeTcc();
            
                // Actualizamos row seleccionado en tree
                JSFUtils.resolveMethodExpression("#{bindings.TccTreeRootVO.treeModel.makeCurrent}", 
                                                    Object.class, new Class[] { SelectionEvent.class }, 
                                                        new Object[] { selectionEvent });
            
                // Asignamos variables de viewScope: pIdTipoGestionTree y pIdTccTree
                treeRowSelectionListener(selectionEvent);
            }
            
        }catch(Exception ex){
            LOGGER.severe("Error en treeRowTccSelectionListener :"+ex);
        }
        
        LOGGER.warning("Fuera de treeRowTccSelectionListener");
    }
    
    private void treeRowSelectionListener(SelectionEvent selectionEvent) {
        LOGGER.warning("Dentro de treeRowSelectionListener");
        MatrizTccBean matrizTccBean = (MatrizTccBean) JSFUtils.resolveExpression("#{pageFlowScope.matrizTccBean}");
        boolean isDirty = ADFUtils.getDCBindingContainer().getDataControl().isTransactionDirty();
        Boolean esEditar = null;
        RichTree tree = null;
        RowKeySet rowKeySet = null;
        Iterator rksIterator = null;
                
        // Asignación de variables
        //esEditar = Boolean.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pEsModoEscritura}").toString());
        esEditar = false; //Para visualizar las pantallas de Ver al seleccionar un elemento en tree
        
        tree = (RichTree)selectionEvent.getSource(); // get the tree component from the event
        rowKeySet = selectionEvent.getAddedSet(); //get selected nodes
        rksIterator = rowKeySet.iterator();
        
        //Validating for single select only. Need to check for multiselect
        while (rksIterator.hasNext()) {
            List key = (List)rksIterator.next();
            JUCtrlHierBinding treeBinding = null;
            CollectionModel collectionModel = (CollectionModel)tree.getValue();
            treeBinding = (JUCtrlHierBinding)collectionModel.getWrappedData();           
            JUCtrlHierNodeBinding nodeBinding = null;
            nodeBinding = treeBinding.findNodeByKeyPath(key);
            Row row = null;
            if(nodeBinding != null){
                row = nodeBinding.getRow();
                if(row != null){
                    
                    List<String> atributos = Arrays.asList(row.getAttributeNames());
                    
                    if (atributos.contains("Tipo") && row.getAttribute("Tipo") != null && row.getAttribute("Tipo").equals("TERMINO")) {
                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", obtenerIdTipoGestion(1, esEditar));
                        ADFContext.getCurrent().getViewScope().put("pIdTccTree", new Number(Long.valueOf(row.getAttribute("IdTcc").toString())));
                    } else if (atributos.contains("Tipo") && row.getAttribute("Tipo") != null && row.getAttribute("Tipo").equals("CONDICION")) {
                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", obtenerIdTipoGestion(2, esEditar));
                        ADFContext.getCurrent().getViewScope().put("pIdTccTree", new Number(Long.valueOf(row.getAttribute("IdTcc").toString())));
                    } else if (atributos.contains("Tipo") && row.getAttribute("Tipo") != null && row.getAttribute("Tipo").equals("COMISION")) {
                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", obtenerIdTipoGestion(3, esEditar));
                        ADFContext.getCurrent().getViewScope().put("pIdTccTree", new Number(Long.valueOf(row.getAttribute("IdTcc").toString())));
                    } else{
                        // Se seleccionó un nodo padre, es decir NO es un Término, Condición o Comisión
                        ADFContext.getCurrent().getViewScope().put("pIdTipoGestionTree", null); 
                        ADFContext.getCurrent().getViewScope().put("pIdTccTree", null); 
                    }
                    
                    // Incidencia FNXII-3485: Cuando NO existan cambios pendientes por guardar/deshacer (!isDirty), guardamos 
                    // la selección actual del tree, para restaurarla en caso de un isDirty
                    if(!isDirty) {
                        RowKeySetImpl rksNew = new RowKeySetImpl();
                        rksNew.add(key);
                        matrizTccBean.setDisclosedRowKeysTcc(rksNew);
                        matrizTccBean.setEsSelectionEventQueue(false); // limpiamos flag de control de entrada infinita al Selectionlistener
                    }
                }
            }
        }
        
        LOGGER.warning("Fuera de treeRowSelectionListener");
    }

    public void setTccTreeUIC(RichTree tccTreeUIC) {
        this.tccTreeUIC = tccTreeUIC;
    }

    public RichTree getTccTreeUIC() {
        return tccTreeUIC;
    }

    public void setBtnRefreshTccTree(RichButton btnRefreshTccTree) {
        this.btnRefreshTccTree = btnRefreshTccTree;
    }

    public RichButton getBtnRefreshTccTree() {
        return btnRefreshTccTree;
    }
    
    private void refreshTccTree() {
        if (ID_MODALIDAD_TREE.equals(this.getIdModalidadFromPageFlowScope())) {
            refreshTccTree("TccTreeRootVOIterator", Integer.valueOf(1), "TccTreeTcaTipoVO");
        }
    }
    
    public String getClientIdBtnRefreshTccTree() {
        this.clientIdBtnRefreshTccTree = getBtnRefreshTccTree() == null ? null : getBtnRefreshTccTree().getClientId();
        return this.clientIdBtnRefreshTccTree;
    }
    
    private void inicializarTreeTcc() {
        LOGGER.log(ADFLogger.WARNING, "Inside inicializarTreeTcc.");
        Long idOperacion = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
        DCIteratorBinding treeRootIteratorVO = null;
        ViewObject treeRootVO = null;
        ViewObject tcaTipoVO = null;
        ViewObject tcaVO = null;
        
        // Asignación de variables
        treeRootIteratorVO = ADFUtils.findIterator("TccTreeRootVOIterator");
        treeRootVO = treeRootIteratorVO.getViewObject();
        
        // Obtenemos los child VOs via the parent view object binding
        tcaTipoVO = ADFUtils.getChildViewObjectFromParent(treeRootVO, "TccTreeTcaTipoVO");
        tcaVO =  ADFUtils.getChildViewObjectFromParent(tcaTipoVO, "TccTreeTcaVO");
        
        // Asignamos los bind variable a todos los VOs hijos de Comisiones
        tcaTipoVO.ensureVariableManager().setVariableValue("varIdOperacionTcaTipo", idOperacion);        
        tcaVO.ensureVariableManager().setVariableValue("varIdOperacionTca", idOperacion);
        
        // Re-ejecutamos los queries de los padres
        treeRootIteratorVO.executeQuery();
        
        this.expandirTodoArbol();// Mandar abrir todos los nodos
    }
    
    private void expandTreeChildrenNode(  RichTree rt
                                       ,  FacesCtrlHierNodeBinding node
                                       ,  List<Key> parentRowKey) {
         ArrayList children = node.getChildren();
         List<Key> rowKey;

         if ( children != null )    {
           for (int i = 0; i < children.size(); i++) {
              rowKey = new ArrayList<Key>();
              rowKey.addAll(parentRowKey);
              rowKey.add(((FacesCtrlHierNodeBinding)children.get(i)).getRowKey());
              rt.getDisclosedRowKeys().add(rowKey);
              if (((FacesCtrlHierNodeBinding)(children.get(i))).getChildren() == null)
                continue;
                expandTreeChildrenNode(rt
                                      ,(FacesCtrlHierNodeBinding)(node.getChildren().get(i))
                                      , rowKey);
              }
         } 
    }

     // find a jsf component
    private UIComponent getUIComponent(String name) {
         FacesContext facesCtx = FacesContext.getCurrentInstance();
         return facesCtx.getViewRoot().findComponent(name) ;
    }

    private UIComponent getUIComponent(UIComponent component,String name ){
         List<UIComponent> items = component.getChildren(); 
         for ( UIComponent item : items ) { 
           UIComponent found = getUIComponent(item,name);
           if ( found != null ) {
             return found; 
           }
           if ( item.getId().equalsIgnoreCase(name)  ) { 
             return item;
           }; 
         }
         return null;
    }
    
    private void expandirTodoArbol() {
        RichTree rt = getTccTreeUIC();
       if ( rt != null ) {
          int rowCount = rt.getRowCount();
          List<Key> rowKey;
          for (int j = 0; j < rowCount; j++) {
             // expand the main nodes
             FacesCtrlHierNodeBinding node = (FacesCtrlHierNodeBinding)rt.getRowData(j);
             rowKey = new ArrayList<Key>();
             rowKey.add(node.getRowKey());
             rt.getDisclosedRowKeys().add(rowKey);
             rt.setRowKey(rowKey);
             // expand the child nodes of the main nodes
             expandTreeChildrenNode(rt , node, rowKey);
           }   
        }
    }
    
    private Integer getIdModalidadFromPageFlowScope() {
        Integer idModalidad = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}") != null){
            idModalidad = (Integer) JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}");    
        } else {
            LOGGER.severe("Parametro de Task Flow pIdModalidad no obtenido");  
        }
        return idModalidad;
    }

    public void setFormFechasCondUIC(RichPanelFormLayout formFechasCondUIC) {
        this.formFechasCondUIC = formFechasCondUIC;
    }

    public RichPanelFormLayout getFormFechasCondUIC() {
        return formFechasCondUIC;
    }

    public void actualizarTipoControlCondicion(ValueChangeEvent valueChangeEvent) {
        
        LOGGER.warning("Inicia actualizarTipoControlCondicion");
        
        if (valueChangeEvent == null) {
            return;
        }
        
        LOGGER.warning("Nuevo valor de Tipo Control: " + valueChangeEvent.getNewValue());
        
        //Forza la actualizacion del Modelo
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        //Refresca componente de Fecha Inicio
        if(getPanelGroupTipoCalendarioCondUIC() != null){
            LOGGER.warning("Refresca Panel Group de campos de Calendario");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelGroupTipoCalendarioCondUIC());    
        }
        
        if(getPanelGroupTipoEventoCondUIC() != null){
            LOGGER.warning("Refresca Panel Group de campos de Evento");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelGroupTipoEventoCondUIC());    
        }
        
        if(getPanelHeaderCondicionUIC() != null){
            LOGGER.warning("Refresca Panel Group de campos de Evento");
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelHeaderCondicionUIC());    
        }
        
        LOGGER.warning("Finaliza actualizarTipoControlCondicion");
    }
    
    public String validaTabSeleccionado(Integer idTareaBpm) {
        LOGGER.warning("Inside validaTabSeleccionado");
    
        String tabTccSeleccionado = null;
    
        if (idTareaBpm != null) {
            
            //En la tarea de Actualizar Condiciones SIEMAS de Evaluaciones, solo se muestra tab de Condiciones.
            if (idTareaBpm.compareTo(ID_TAREA_ACTUALIZAR_CONDICIONES_SIEMAS) == 0) {
                LOGGER.warning("Inside validaTabSeleccionado");
                tabTccSeleccionado = FenixConstants.CONDICION;       
            } else {
                tabTccSeleccionado = (JSFUtils.resolveExpression("#{viewScope.tabTccSeleccionado}") == null) ? 
                    FenixConstants.TERMINO : JSFUtils.resolveExpression("#{viewScope.tabTccSeleccionado}").toString();
            }
        }

        return tabTccSeleccionado;
    }

    public void validarMostrarIdTcaTipoDesembolso(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inicia metodo validarMostrarIdTcaTipoDesembolso.");
        //actualiza el valor seleccionado
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean existeEventoDesembolso = Boolean.FALSE;
        
        CondicionMangedBean condicionManagedBean =
            (CondicionMangedBean) JSFUtils.resolveExpression("#{pageFlowScope.condicionManagedBean}");

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = null;
        
        if(null != ADFUtils.getDCBindingContainer().findIteratorBinding("TccCondicionVOIterator")){
            LOGGER.warning("ENtra a Iterador en modo TREE.");
            list = (JUCtrlListBinding) bindings.get("TccTcaEventoCondicionLOV");
        }
        
        if(null != ADFUtils.getDCBindingContainer().findIteratorBinding("TccCondicionGridVOIterator")){
            LOGGER.warning("ENtra a Iterador en modo GRID.");
            list = (JUCtrlListBinding) bindings.get("TccTcaEventoCondicionGridLOV");
        }
        
        if(null == list){
            LOGGER.warning("ERROR no se pudo recuperar la lista de Eventos.");
            return;
        }
        int contador = 0;
        Integer idEvento = null;

        LOGGER.warning("*Inf, Num de Eventos recuperado para la operacion : " + list.getSelectedIndices().length);
        LOGGER.warning("VALUECHANGEEVENT: " + valueChangeEvent.getNewValue());
        
        int values[] = null;
        if (list != null) {
            values = list.getSelectedIndices();
        }

        if (values != null) {
            LOGGER.warning("Cantidad de Eventos Condicion Seleccionados: " + values.length);
            Row row = null;
            Number id = null;
            for (int index = 0; index < values.length; index++) {
                row = list.getRowAtRangeIndex(values[index]);
                id = (Number) row.getAttribute("TecId");

                LOGGER.warning("ID de evento: " + id);
                if (null != id) {
                    if (id.compareTo(2) == 0 || id.compareTo(3) == 0) {
                        LOGGER.warning("El evento es de tipo de desembolso.");
                        existeEventoDesembolso = Boolean.TRUE;
                        // ID =2 Es Primer desembolso
                        if(id.compareTo(2) == 0){
                            Map params = null;
                            params = new HashMap();
                            params.put("idEvento", id);
                            executeOperBinding(params, "setIdEvento");
                            LOGGER.warning("Omitir el registro Contrato de Desembolso");
                        }else{
                            Map params = null;
                            params = new HashMap();
                            params.put("idEvento", null);
                            executeOperBinding(params, "setIdEvento");
                            LOGGER.warning("No omitir el registro Contrato de Desembolso");
                        }
                        break;
                    } else {
                        LOGGER.warning("El evento NO es de tipo de desembolso.");
                    }
                } else {
                    LOGGER.warning("IdEvento es NULL.");
                }
            }
        }

        LOGGER.warning("Existe evento de desembolso seleccionado: " + existeEventoDesembolso);
        if (!existeEventoDesembolso) {
            ADFUtils.setBoundAttributeValue("IdTcaTipoDesembolsoAttrValue", null);
        }

        condicionManagedBean.setMostrarIdTcaTipoDesembolso(existeEventoDesembolso);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelGroupTipoEventoCondUIC());
        LOGGER.warning("Termina metodo validarMostrarIdTcaTipoDesembolso.");
    }
    
    public Boolean validarIdTcaTipoDesembolso(){
        LOGGER.warning("Inicia metodo validarIdTcaTipoDesembolso.");
        
        CondicionMangedBean condicionManagedBean =
            (CondicionMangedBean) JSFUtils.resolveExpression("#{pageFlowScope.condicionManagedBean}");
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        JUCtrlListBinding list = null;
        
        if(null != ADFUtils.getDCBindingContainer().findIteratorBinding("TccCondicionVOIterator")){
            LOGGER.warning("ENtra a Iterador en modo TREE.");
            list = (JUCtrlListBinding) bindings.get("TccTcaEventoCondicionLOV");
        }
        
        if(null != ADFUtils.getDCBindingContainer().findIteratorBinding("TccCondicionGridVOIterator")){
            LOGGER.warning("ENtra a Iterador en modo GRID.");
            list = (JUCtrlListBinding) bindings.get("TccTcaEventoCondicionGridLOV");
        }
        
        if(null == list){
            LOGGER.warning("ERROR no se pudo recuperar la lista de Eventos.");
            return Boolean.FALSE;
        }
        
        int contador = 0;
        Integer idEvento = null;
        Boolean existeEventoDesembolso = Boolean.FALSE;
        Boolean esValidoIdTcaTipoDesembolso = Boolean.FALSE;

        LOGGER.warning("*Inf, Num de Eventos recuperado para la operacion : " + list.getSelectedIndices().length);
        
        int values[] = null;
        if (list != null) {
            values = list.getSelectedIndices();
        }

        if (values != null) {
            LOGGER.warning("Cantidad de Eventos Condicion Seleccionados: " + values.length);
            Row row = null;
            Number id = null;
            for (int index = 0; index < values.length; index++) {
                row = list.getRowAtRangeIndex(values[index]);
                id = (Number) row.getAttribute("TecId");

                LOGGER.warning("ID de evento: " + id);
                if (null != id) {
                    if (id.compareTo(2) == 0 || id.compareTo(3) == 0) {
                        LOGGER.warning("El evento es de tipo de desembolso.");
                        existeEventoDesembolso = Boolean.TRUE;
                        break;
                    } else {
                        LOGGER.warning("El evento NO es de tipo de desembolso.");
                    }
                } else {
                    LOGGER.warning("IdEvento es NULL.");
                }
            }
        }

        LOGGER.warning("Existe evento de desembolso seleccionado: " + existeEventoDesembolso);
        if (existeEventoDesembolso) {
            LOGGER.warning("Evaluando attributeValue IdTcaTipoDesembolsoAttrValue." + ADFUtils.getBoundAttributeValue("IdTcaTipoDesembolsoAttrValue"));
            if(null != ADFUtils.getBoundAttributeValue("IdTcaTipoDesembolsoAttrValue")){
                esValidoIdTcaTipoDesembolso = Boolean.TRUE;
            }
        }else{
            LOGGER.warning("No existe evento de desembolso.");
            esValidoIdTcaTipoDesembolso = Boolean.TRUE;
        }
        
        LOGGER.warning("Termina metodo validarIdTcaTipoDesembolso.");
        return esValidoIdTcaTipoDesembolso;
    }

    public void validarTipoPorcentaje(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("Inicia metodo validarTipoPorcentaje.");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean mostrarPorcentajeUnico = null;
        Integer idTcaTipoPorcentaje = null;
        TerminoManagedBean terminoManagedBean = 
            (TerminoManagedBean)JSFUtils.resolveExpression("#{pageFlowScope.terminoManagedBean}");
        
        try{
            idTcaTipoPorcentaje = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoPorcentajeAttrValue");
        }catch(Exception e){
            LOGGER.warning("ERROR al recuperar IdTcaTipoPorcentajeAttrValue.", e);
        }
        
        if(null != idTcaTipoPorcentaje){
            switch(idTcaTipoPorcentaje){
            case 1:
                mostrarPorcentajeUnico = Boolean.TRUE;
                ADFUtils.setBoundAttributeValue("PorcentajeInicial", null);
                ADFUtils.setBoundAttributeValue("PorcentajeFinal", null);
                break;
            case 2:
                mostrarPorcentajeUnico = Boolean.FALSE;
                ADFUtils.setBoundAttributeValue("Porcentaje", null);
                break;
            }
        }else{
            LOGGER.warning("idTcaTipoPorcentaje es NULL.");
            ADFUtils.setBoundAttributeValue("PorcentajeInicial", null);
            ADFUtils.setBoundAttributeValue("PorcentajeFinal", null);
            ADFUtils.setBoundAttributeValue("Porcentaje", null);
        }
        
        if(null != terminoManagedBean){
            terminoManagedBean.setMostrarPorcentajeUnico(mostrarPorcentajeUnico);
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPglTerminoInicioUIC());
        LOGGER.warning("Termina metodo validarTipoPorcentaje.");
    }
    
    public void setPanelGroupTipoCalendarioCondUIC(RichPanelGroupLayout panelGroupTipoCalendarioCondUIC) {
        this.panelGroupTipoCalendarioCondUIC = panelGroupTipoCalendarioCondUIC;
    }

    public RichPanelGroupLayout getPanelGroupTipoCalendarioCondUIC() {
        return panelGroupTipoCalendarioCondUIC;
    }

    public void setPanelGroupTipoEventoCondUIC(RichPanelGroupLayout panelGroupTipoEventoCondUIC) {
        this.panelGroupTipoEventoCondUIC = panelGroupTipoEventoCondUIC;
    }

    public RichPanelGroupLayout getPanelGroupTipoEventoCondUIC() {
        return panelGroupTipoEventoCondUIC;
    }

    public void setPanelHeaderCondicionUIC(RichPanelHeader panelHeaderCondicionUIC) {
        this.panelHeaderCondicionUIC = panelHeaderCondicionUIC;
    }

    public RichPanelHeader getPanelHeaderCondicionUIC() {
        return panelHeaderCondicionUIC;
    }

    public void setActualizarTerminoFormUI(RichPanelGroupLayout actualizarTerminoFormUI) {
        this.actualizarTerminoFormUI = actualizarTerminoFormUI;
    }

    public RichPanelGroupLayout getActualizarTerminoFormUI() {
        return actualizarTerminoFormUI;
    }

    public void setSocIdTcaTipoDesembolsoUIC(RichSelectOneChoice socIdTcaTipoDesembolsoUIC) {
        this.socIdTcaTipoDesembolsoUIC = socIdTcaTipoDesembolsoUIC;
    }

    public RichSelectOneChoice getSocIdTcaTipoDesembolsoUIC() {
        return socIdTcaTipoDesembolsoUIC;
    }

    public void setPglTerminoInicioUIC(RichPanelGroupLayout pglTerminoInicioUIC) {
        this.pglTerminoInicioUIC = pglTerminoInicioUIC;
    }

    public RichPanelGroupLayout getPglTerminoInicioUIC() {
        return pglTerminoInicioUIC;
    }


    public boolean isEditableEnFormalizacion() {
        LOGGER.warning("Dentro de isEditableEnFormalizacion");
        Integer idTareaBpm = null;
        Integer idProcesoBpm = null;
        Map params = null;
        Boolean result = null;
        //obtener idProcesoBpm apartir del idTareaBpm
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}")){
            idTareaBpm = (Integer)JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}");
            LOGGER.warning("idTareaBpm :"+idTareaBpm);
            params = new HashMap();
            params.put("idTareaBpm", idTareaBpm);
            OperationBinding oper = executeOperBinding(params, "obtenerIdProcesoBpmPorIdTareaBpm");
            if(oper.getErrors().isEmpty()){
                idProcesoBpm = (Integer) oper.getResult();
                if(FenixConstants.PROCESO_FORMALIZACION == idProcesoBpm){
                    LOGGER.warning("El proceso es formalizacion,evaluar la columna ES_EDITABLE_EN_FORMALIZACION");
                    result = (Boolean)
                        JSFUtils.resolveExpression("#{bindings.EditableFormalizacion.inputValue}");
                    LOGGER.warning("IdTcaTermino : "+JSFUtils.resolveExpression("#{bindings.IdTcaTermino.inputValue}"));
                    LOGGER.warning("editableFormalizacion : "+result);
                    this.setEditableEnFormalizacion(result);
                    
                }else{
                    LOGGER.warning("El proceso no es formalizacion es visible el boton de modificar");
                    this.setEditableEnFormalizacion(true);
                }
            }
        }else{
            LOGGER.warning("#{pageFlowScope.pIdTareaBpm} es nulo");
            this.setEditableEnFormalizacion(true);
        }
        LOGGER.warning("Fuera de isEditableEnFormalizacion,return :"+editableEnFormalizacion);
        return editableEnFormalizacion;
    }

    public void setEditableEnFormalizacion(boolean editableEnFormalizacion) {
        this.editableEnFormalizacion = editableEnFormalizacion;
    }
    /**
     * Metodo para validar que eventos estan selecionados y definir si es campo aplicable a es visible
     */
    public void esVisibleAplicableA(){
        LOGGER.warning("Dentro de esVisibleAplicableA");
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        CondicionMangedBean condicionMangedBean =
            (CondicionMangedBean) JSFUtils.resolveExpression("#{pageFlowScope.condicionManagedBean}");
        JUCtrlListBinding listaEvento = null;
        Integer idModalidad = null;
        
        idModalidad = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdModalidad}").toString());
        LOGGER.warning("idModalidad :"+idModalidad);

        if (new Integer(1).equals(idModalidad)) {
            if (null != ADFUtils.getDCBindingContainer().findIteratorBinding("TccCondicionVOIterator")) {
                LOGGER.warning("Recuperar lista de eventos para tree");
                listaEvento = (JUCtrlListBinding) bindings.get("TccTcaEventoCondicionLOV");
            }
        } else {
            if (null != ADFUtils.getDCBindingContainer().findIteratorBinding("TccCondicionGridVOIterator")) {
                LOGGER.warning("Recuperar lista de eventos para grid");
                listaEvento = (JUCtrlListBinding) bindings.get("TccTcaEventoCondicionGridLOV");
            }
        }
        LOGGER.warning("Se valida si se muestra el campo Aplicabe a:");
        
        int eventoSelect[] = null;
        if (listaEvento != null) {
            LOGGER.warning("La lista de eventos tiene elementos seleccionados");
            eventoSelect = listaEvento.getSelectedIndices();
        }else{
            LOGGER.warning("La lista de eventos no tiene elementos seleccionados");
        }

        if (eventoSelect != null) {
            LOGGER.warning("Cantidad de eventos condicion seleccionados: " + eventoSelect.length);
            
            Row row = null;
            Number id = null;
            for (int index = 0; index < eventoSelect.length; index++) {
                row = listaEvento.getRowAtRangeIndex(eventoSelect[index]);
                id = (Number) row.getAttribute("TecId");

                LOGGER.warning("ID de evento: " + id);

                if (id.compareTo(2) == 0 || id.compareTo(3) == 0) {
                    LOGGER.warning("El evento es de tipo de desembolso, mostrar visible Aplicable a:");
                    condicionMangedBean.setMostrarIdTcaTipoDesembolso(Boolean.TRUE);

                } else {
                    LOGGER.warning("El evento No es de tipo de desembolso, No mostrar visible Aplicable a:");
                }

            }
        }
        
        LOGGER.warning("mostrarIdTcaTipoDesembolso :"+condicionMangedBean.getMostrarIdTcaTipoDesembolso());
        LOGGER.warning("Fuera de esVisibleAplicableA");
        
    }
}
