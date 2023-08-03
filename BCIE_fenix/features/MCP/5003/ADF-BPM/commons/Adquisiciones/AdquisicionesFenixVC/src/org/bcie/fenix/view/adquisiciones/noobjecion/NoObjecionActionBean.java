package org.bcie.fenix.view.adquisiciones.noobjecion;

import com.bcie.xmlns.adquisicionservice.AdquisicionPT;
import com.bcie.xmlns.adquisicionservice.AdquisicionPT12BndQSService;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
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
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;
import org.apache.myfaces.trinidad.context.Agent;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;

import org.bcie.adquisicionmo.InformeNoObjecionRequestType;
import org.bcie.adquisicionmo.InformeNoObjecionResponseType;
import org.bcie.comisionmo.CrearAvisoCobroComisionRequestType;
import org.bcie.comisionmo.CrearAvisoCobroComisionResponseType;
import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAdquisicionAMImpl;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.view.adquisiciones.AdquisicionesActionBean;
import org.bcie.fenix.view.adquisiciones.constantes.ResultadoProcesoEnum;
import org.bcie.fenix.view.adquisiciones.constantes.TipoConcursanteEnum;
import org.bcie.fenix.view.adquisiciones.constantes.TipoObjecionEnum;
import org.bcie.xmlns.objetoproceso.comun.proceso.v1.Proceso;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;

import oracle.adf.view.rich.util.ResetUtils;

import oracle.jbo.domain.BlobDomain;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.model.UploadedFile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.bcie.fenix.view.adquisiciones.AdquisicionesBean;

@SuppressWarnings("oracle.jdeveloper.java.field-not-serializable")
public class NoObjecionActionBean implements Serializable {
    @SuppressWarnings("compatibility:2138289811791639741")
    private static final long serialVersionUID = 1L;
    private String documento;
    private String documentoVista;

    private static ADFLogger logger = null;
    private RichPanelGroupLayout panelAvisoUI;
    private RichPanelGroupLayout panelInformeUI;
    private RichPanelGroupLayout panelOferentesUI;
    private RichPanelGroupLayout panelAdjudicatariosUI;
    private RichPanelGroupLayout panelContratadosUI;
    private RichPanelHeader panelNoObjecionesUIC;

    //variables para los popup´s
    private transient RichPopup popupEliminarOferente;
    private transient RichPopup popupEliminarAdjudicatario;
    private transient RichPopup popupEliminarContratado;

    // variables para los Table
    private RichTable oferentesTableUIC;
    private RichTable adjudicatariosTableUIC;
    private RichTable contratadosTableUIC;
    private RichPopup popupCancelarObjecion;
    private RichPopup popupIniciarAdquisicion;
    private RichPopup popupGuardarObjecion;
    private RichPanelHeader participantesUI;
    
    private RichSelectBooleanCheckbox oficialUIC;
    private RichSelectBooleanCheckbox abogadoUIC;
    private RichSelectBooleanCheckbox analistaUIC;

    private static final String BUNDLE = "org.bcie.fenix.view.adquisiciones.AdquisicionesFenixVCBundle";

    private RichTable pflNoObjecionTableUIC;

    private Boolean esSelectionEventQueue = Boolean.FALSE;
    private RowKeySet disclosedRowKeysTable;
    private Long keyId; // Guarda el elemento seleccionado en la tabla
    private Boolean  servicioRespuesta;
    private String mensaje;
    private RichTable contratadoTableUIC;
    private RichTable adjudicatarioTableUIC;
    private RichCommandButton btnDescargarPlantillaBinding;
    private RichPanelGroupLayout groupBtnPlantilla;
    private RichInputFile inputFilePantillaBinding;


    public NoObjecionActionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(AdquisicionesActionBean.class);
        }

        if (null == disclosedRowKeysTable) {
            disclosedRowKeysTable = new RowKeySetImpl();
        }
    }

    @SuppressWarnings("unchecked")
    public void agregarNoObjecion(Long idAdquisicion) {
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        Boolean adquiscionActiva = Boolean.TRUE;
        if (JSFUtils.resolveExpression("#{sessionScope.adquisicionForm}") != null) {
            adquiscionActiva = (Boolean) (JSFUtils.resolveExpression("#{sessionScope.adquisicionForm}"));
        }

        logger.warning("adquiscionActiva: " + adquiscionActiva);

        if (adquiscionActiva) {
            logger.warning("Se puede insertar no objecion");
            noObjecionBean.setHabilitarCampos(Boolean.FALSE);
            logger.warning("7 log habilitarCampos: " + noObjecionBean.getHabilitarCampos());
            logger.warning("7 log esFinanciero: " + noObjecionBean.getEsFinanciero());
            logger.warning("7 log esEstadoCompletado: " + noObjecionBean.getEsEstadoCompletado());
            BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
            OperationBinding operationBindingIdOp = null;
            //metodo para agregar nueva no objecion
            operationBindingIdOp = bindingsIdOp.getOperationBinding("agregar");
            operationBindingIdOp.getParamsMap().put("idAdquisicion", idAdquisicion);
            Object nuevoIdNoObjecionObj = operationBindingIdOp.execute();

            JSFUtils.setExpressionValue("#{sessionScope.noObjecion}", noObjecionBean.getHabilitarCampos());

            if (!operationBindingIdOp.getErrors().isEmpty()) {
                logger.warning("No se pudo iniciar el metodo de nueva NO objecion");
            } else {
                Long nuevoIdNoObjecion = (Long) nuevoIdNoObjecionObj;
                AttributeBinding idBinding = ADFUtils.findControlBinding("Id");
                if (null != idBinding && null != nuevoIdNoObjecion) {
                    idBinding.setInputValue(nuevoIdNoObjecion);
                    setKeyId(nuevoIdNoObjecion);
                }
                AttributeBinding modalidadBinding = ADFUtils.findControlBinding("modalidad");
                if (null != modalidadBinding && null != noObjecionBean.getModalidad()) {
                    modalidadBinding.setInputValue(noObjecionBean.getModalidad());
                }
            }
        } else {
            JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
        }
    }

    public Boolean consultarDatosNoObjecion(Integer tipo, Integer resultadoProceso) {
        Boolean resultado = Boolean.FALSE;
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        //metodo para obtener si el produto es 'Linea global de credito'
        operationBindingIdOp = bindingsIdOp.getOperationBinding("precargaNoObjecion");
        if (null != tipo) {
            operationBindingIdOp.getParamsMap().put("tipo", tipo);
        }
        if (null != resultadoProceso) {
            operationBindingIdOp.getParamsMap().put("resultadoProceso", resultadoProceso);
        }
        resultado = (Boolean) operationBindingIdOp.execute();
        if (!operationBindingIdOp.getErrors().isEmpty()) {
            logger.warning("No se pudo iniciar el metodo de nueva NO objecion");
            return Boolean.FALSE;
        }
        return resultado;
    }

    public void nuevaNoObjecion(@SuppressWarnings("unused") ActionEvent actionEvent) {
        // Add event code here...
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        if (null != noObjecionBean.getIdAdquisiones()) {
            agregarNoObjecion(noObjecionBean.getIdAdquisiones());
            recargarConcursantes();
        }
        
        accionCargarPlantilla(null);
    }

    public void cambioTipoObjecion(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Integer tipo = null;
        Integer valorAnterior = null;
        if (null != (Integer) valueChangeEvent.getNewValue()) {
            tipo = (Integer) valueChangeEvent.getNewValue();
        }
        
        logger.warning("tipo: "+tipo);
        
        if (null != (Integer) valueChangeEvent.getOldValue()) {
            valorAnterior = (Integer) valueChangeEvent.getOldValue();
            if (limpiarCampos(valorAnterior)) {
                logger.warning("se limpio correctamente");
            } else {
                logger.warning("no se limpio la no objecion");
            }
        }
        if (consultarDatosNoObjecion(tipo, null)) {
            logger.warning("Datos cargados");

            cargarDatosParticipantes();
        } else {
            logger.warning("Datos no cargados");
        }
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        if (noObjecionBean.getTarea().compareTo(FenixConstants.PA09_REALIZAR_AJUSTES) == 0) {

        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelAvisoUI());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelInformeUI());
        AdfFacesContext.getCurrentInstance().addPartialTarget(participantesUI);

        recargarConcursantes();
        
        //Recarca adjudicatarios solo cuando sea una No Objecion de tipo Contrato
        if (tipo.compareTo(9) == 0) {
            refreshAdjudicatarios();
            limpiarContratados();
        }
        AttributeBinding modalidad;
        modalidad = ADFUtils.findControlBinding("modalidad");
        modalidad.setInputValue(noObjecionBean.getModalidad());
        
        accionCargarPlantilla(tipo);
        
    }
    
    

    public Boolean limpiarCampos(Integer tipo) {
        Boolean resultado = Boolean.TRUE;
        if (null != tipo) {
            AttributeBinding fechaPublicacion;
            fechaPublicacion = ADFUtils.findControlBinding("FechaPublicacion");
            AttributeBinding fechaFinDispDoctoBase;
            fechaFinDispDoctoBase = ADFUtils.findControlBinding("FechaFinDispDoctoBase");
            AttributeBinding fechaRecepcionPropuesta;
            fechaRecepcionPropuesta = ADFUtils.findControlBinding("FechaRecepcionPropuesta");
            AttributeBinding fechaInicioDispDoctoBase;
            fechaInicioDispDoctoBase = ADFUtils.findControlBinding("FechaInicioDispDoctoBase");
            AttributeBinding lugarObtenerDoctoBase;
            lugarObtenerDoctoBase = ADFUtils.findControlBinding("LugarObtenerDoctoBase");

            AttributeBinding correoInformacionAdicional;
            correoInformacionAdicional = ADFUtils.findControlBinding("CorreoInformacionAdicional");
            AttributeBinding dirPresentacionPropuesta;
            dirPresentacionPropuesta = ADFUtils.findControlBinding("DirPresentacionPropuesta");
            AttributeBinding idTcaResultadoProceso;
            idTcaResultadoProceso = ADFUtils.findControlBinding("IdTcaResultadoProceso");
            switch (tipo) {
            case FenixConstants.NO_OBJECION_AVISO:
                fechaPublicacion.setInputValue(null);
                fechaFinDispDoctoBase.setInputValue(null);
                fechaRecepcionPropuesta.setInputValue(null);
                fechaInicioDispDoctoBase.setInputValue(null);
                lugarObtenerDoctoBase.setInputValue(null);
                correoInformacionAdicional.setInputValue(null);
                dirPresentacionPropuesta.setInputValue(null);
                break;
            case FenixConstants.NO_OBJECION_INFORME_RESULTADOS:
                idTcaResultadoProceso.setInputValue(null);
                //Borrado de registros de las tablas
                break;
            case FenixConstants.NO_OBJECION_CONTRATO:
                //Borrado de registros de las tablas
                break;
            case FenixConstants.NO_OBJECION_RESULTADO_PROCESO:
                //Borrado de registros de las tablas
                break;
            case FenixConstants.NO_OBJECION_CARGO_COMPRA:
                //Borrado de registros de las tablas
                break;

            }
        }


        return resultado;
    }

    public void setPanelAvisoUI(RichPanelGroupLayout panelAvisoUI) {
        this.panelAvisoUI = panelAvisoUI;
    }

    public RichPanelGroupLayout getPanelAvisoUI() {
        return panelAvisoUI;
    }

    public void setPanelInformeUI(RichPanelGroupLayout panelInformeUI) {
        this.panelInformeUI = panelInformeUI;
    }

    public RichPanelGroupLayout getPanelInformeUI() {
        return panelInformeUI;
    }

    /**
     * M&eacute;todo para agregar un oferente
     * @author Jonathan Ruiz
     * @param actionEvent
     */
    @SuppressWarnings("unused")
    public void agregarOferenteActionListener(ActionEvent actionEvent) {
        actionEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        OperationBinding oferentesParamBinding;

        oferentesParamBinding = ADFUtils.findOperation("crearRowOferente");
        oferentesParamBinding.execute();

        if (!oferentesParamBinding.getErrors().isEmpty()) {
            logger.warning("No se pudo agregar el oferente");
        }
    }

    /**
     * M&eacute;todo para eliminar un oferente
     * @author Jonathan Ruiz
     * @param actionEvent
     */
    @SuppressWarnings("unused")
    public void eliminarOferenteActionListener(ActionEvent actionEvent) {
        // TODO realizar validaciones aqui...

        RichPopup.PopupHints popupHints = null;

        // Abrimos popup
        popupHints = new RichPopup.PopupHints();
        this.getPopupEliminarOferente().show(popupHints);
    }

    /**
     * M&eacute;todo para pasar un oferente a adjudicatarios
     * @author Jonathan Ruiz
     * @param actionEvent
     */
    @SuppressWarnings("unused")
    public void pasarOferenteaAdjudicatario(ActionEvent actionEvent) {
        actionEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        OperationBinding oferentesParamBinding;

        oferentesParamBinding = ADFUtils.findOperation("adjudicarOferente");
        oferentesParamBinding.execute();

        if (!oferentesParamBinding.getErrors().isEmpty()) {
            logger.warning("No se pudo agregar el oferente");
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelAdjudicatariosUI());
        
    }

    /**
     * M&eacute;todo para aceptar eliminar un oferente
     * @author Jonathan Ruiz
     * @param actionEvent
     */
    @SuppressWarnings("unused")
    public void aceptarEliminarOferente(ActionEvent actionEvent) {
        OperationBinding oferentesParamBinding;

        oferentesParamBinding = ADFUtils.findOperation("eliminarOferente");
        oferentesParamBinding.execute();

        if (!oferentesParamBinding.getErrors().isEmpty()) {
            logger.warning("No se pudo eliminar el oferente");
        } else {
            actualizarTablaOferentes();
        }

        getPopupEliminarOferente().hide();
    }

    /**
     * M&eacute;todo para cancelar eliminar un oferente
     * @author Jonathan Ruiz
     * @param actionEvent
     */
    @SuppressWarnings("unused")
    public void cancelarEliminarOferente(ActionEvent actionEvent) {
        getPopupEliminarOferente().hide();
    }

    /**
     * M&eacute;todo para escuchar el cambio de resultado de proceso
     * @author Jonathan Ruiz
     * @param actionEvent
     */
    public void cambioResultadoProceso(ValueChangeEvent valueChangeEvent) {
        logger.warning("cambioResultadoProceso(): "+valueChangeEvent.getNewValue());
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        recargarConcursantes();

        Integer resultadoProceso = (valueChangeEvent.getNewValue() != null) 
                                 ? (Integer)valueChangeEvent.getNewValue() : null ;
        
        noObjecionBean.setBtnDescargarPlantillaDisabled("false"); 
        
        if(resultadoProceso != null && resultadoProceso.compareTo(new Integer(1))== 0)
                       noObjecionBean.setBtnDescargarPlantillaDisabled("true"); 
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getGroupBtnPlantilla());        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
    }

    public void actualizarTablaOferentes() {
        logger.warning("Inside actualizarTablaOferentes.");
        AdfFacesContext.getCurrentInstance().addPartialTarget(getOferentesTableUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelOferentesUI());
    }

    public void actualizarTablaAdjudicatarios() {
        logger.warning("Inside actualizarTablaAdjudicatarios.");
        AdfFacesContext.getCurrentInstance().addPartialTarget(getAdjudicatariosTableUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelAdjudicatariosUI());
    }

    public void actualizarTablaContratados() {
        logger.warning("Inside actualizarTablaContratados.");
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContratadosTableUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelContratadosUI());
    }

    public RichPopup getPopupEliminarOferente() {
        return popupEliminarOferente;
    }

    public void setPopupEliminarOferente(RichPopup popupEliminarOferente) {
        this.popupEliminarOferente = popupEliminarOferente;
    }

    public RichTable getOferentesTableUIC() {
        return oferentesTableUIC;
    }

    public void setOferentesTableUIC(RichTable oferentesTableUIC) {
        this.oferentesTableUIC = oferentesTableUIC;
    }

    public RichTable getAdjudicatariosTableUIC() {
        return adjudicatariosTableUIC;
    }

    public void setAdjudicatariosTableUIC(RichTable adjudicatariosTableUIC) {
        this.adjudicatariosTableUIC = adjudicatariosTableUIC;
    }

    @SuppressWarnings("unused")
    public void cancelarEliminarContratado(ActionEvent actionEvent) {
        getPopupEliminarContratado().hide();
    }

    @SuppressWarnings("unused")
    public void aceptarEliminarContratado(ActionEvent actionEvent) {
        
        OperationBinding oferentesParamBinding;
        
        String idTcaTipoNoObjecion = (ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion") == null) ? null :
                                             ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion").toString();
        
        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion").toString().length() > 0))) {
            logger.warning("El tipo de No Objecion es Null.");
        } else {
            Integer tipoNoObjecion = Integer.valueOf(idTcaTipoNoObjecion);
            
            if (tipoNoObjecion.compareTo(9) == 0) {
                eliminarContratadoAdjudicatario();
            } else {
                oferentesParamBinding = ADFUtils.findOperation("removerContratado");
                oferentesParamBinding.execute();

                if (!oferentesParamBinding.getErrors().isEmpty()) {
                    logger.warning("No se pudo eliminar el contratado");
                } else {
                    actualizarTablaContratados();
                }
            }
            
    }
        getPopupEliminarContratado().hide();
    }

    public RichPopup getPopupEliminarAdjudicatario() {
        return popupEliminarAdjudicatario;
    }

    public void setPopupEliminarAdjudicatario(RichPopup popupEliminarAdjudicatario) {
        this.popupEliminarAdjudicatario = popupEliminarAdjudicatario;
    }

    public RichPopup getPopupEliminarContratado() {
        return popupEliminarContratado;
    }

    public void setPopupEliminarContratado(RichPopup popupEliminarContratado) {
        this.popupEliminarContratado = popupEliminarContratado;
    }

    public RichTable getContratadosTableUIC() {
        return contratadosTableUIC;
    }

    public void setContratadosTableUIC(RichTable contratadosTableUIC) {
        this.contratadosTableUIC = contratadosTableUIC;
    }

    @SuppressWarnings("unused")
    public void eliminarContratadoActionListener(ActionEvent actionEvent) {
        // Abrimos popup
        this.getPopupEliminarContratado().show(new RichPopup.PopupHints());
    }

    @SuppressWarnings("unused")
    public void agregarContratadoActionListener(ActionEvent actionEvent) {
        OperationBinding oferentesParamBinding;

        oferentesParamBinding = ADFUtils.findOperation("crearRowContratado");
        oferentesParamBinding.execute();

        if (!oferentesParamBinding.getErrors().isEmpty()) {
            logger.warning("No se pudo agregar el contratado");
        }
    }

    public RichPanelGroupLayout getPanelOferentesUI() {
        return panelOferentesUI;
    }

    public void setPanelOferentesUI(RichPanelGroupLayout panelOferentesUI) {
        this.panelOferentesUI = panelOferentesUI;
    }

    public RichPanelGroupLayout getPanelAdjudicatariosUI() {
        return panelAdjudicatariosUI;
    }

    public void setPanelAdjudicatariosUI(RichPanelGroupLayout panelAdjudicatariosUI) {
        this.panelAdjudicatariosUI = panelAdjudicatariosUI;
    }

    public RichPanelGroupLayout getPanelContratadosUI() {
        return panelContratadosUI;
    }

    public void setPanelContratadosUI(RichPanelGroupLayout panelContratadosUI) {
        this.panelContratadosUI = panelContratadosUI;
    }

    @SuppressWarnings("unused")
    public void eliminarAdjudicatarioActionListener(ActionEvent actionEvent) {
        // TODO realizar validaciones aqui...

        // Abrimos popup
        this.getPopupEliminarAdjudicatario().show(new RichPopup.PopupHints());
    }

    @SuppressWarnings("unused")
    public void aceptarEliminarAdjudicatario(ActionEvent actionEvent) {
        OperationBinding oferentesParamBinding;

        oferentesParamBinding = ADFUtils.findOperation("removerAdjudicatario");
        oferentesParamBinding.execute();

        if (!oferentesParamBinding.getErrors().isEmpty()) {
            logger.warning("No se pudo eliminar el adjudicatario");
        } else {
            actualizarTablaAdjudicatarios();
        }
        
        getPopupEliminarAdjudicatario().hide();
    }

    @SuppressWarnings("unused")
    public void cancelarEliminarAdjudicatario(ActionEvent actionEvent) {
        getPopupEliminarAdjudicatario().hide();
    }

    public void seleccionarNoObjecion(SelectionEvent selectionEvent) {
        logger.log(ADFLogger.WARNING, "Entrando a seleccionarNoObjecion.");
        boolean isDirty = ADFUtils.getDCBindingContainer().getDataControl().isTransactionDirty();
        NoObjecionBean noObjecionBean =
            (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        
        logger.log(ADFLogger.WARNING, "habilitarCampos: " + noObjecionBean.getHabilitarCampos());
        logger.log(ADFLogger.WARNING, "isDirty: " + isDirty);
        
        if(!noObjecionBean.getHabilitarCampos()){
                if (!isDirty) {
                    seleccionarNoObjecionCarga(selectionEvent);
                } else {
                    restaurarSeleccionAnterior();
                }
            }
        else{
                seleccionarNoObjecionCarga(selectionEvent);
            }
        
    }
    
    public void seleccionarNoObjecionCarga(SelectionEvent selectionEvent){
            logger.warning("Entra a seleccionarNoObjecionCarga");
            NoObjecionBean noObjecionBean =
                (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");

            //        JSFUtils.resolveExpression("#{bindings.AdquisicionesVO.collectionModel.makeCurrent}");
            //
            //        AdfFacesContext.getCurrentInstance().addPartialTarget(noObjecionUI);

            invokeTable("#{bindings.NoObjecionVO.collectionModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                        selectionEvent });
            // get the selected row , by this you can get any attribute of that row
            //comentado por NullPointerException y al no ser utilizado
            /*Row selectedRow = (Row) evaluateTable("#{bindings.NoObjecionVOIterator1.currentRow}");
            FacesMessage msg = new FacesMessage(selectedRow.getAttribute("NumeroCorrespondencia").toString());*/
            
            AttributeBinding tipoBinding = ADFUtils.findControlBinding("IdTcaResultadoProceso");
            Integer iTipoResultado = null;
            try {
                iTipoResultado = (Integer)tipoBinding.getInputValue();
            } catch (Exception e) {
                logger.log(ADFLogger.WARNING, "Error no se pudo convertir el IdTcaResultadoProceso.");
            }

            consultarDatosNoObjecion(iTipoResultado, null);
            
            recargarConcursantes();

            cargarDatosParticipantes();
            //    msg.setSeverity(FacesMessage.SEVERITY_INFO);
            //    FacesContext.getCurrentInstance().addMessage(null, msg);
            AdfFacesContext.getCurrentInstance().addPartialTarget(participantesUI);
            AttributeBinding modalidadBinding = ADFUtils.findControlBinding("modalidad");
            if (null != modalidadBinding && null != noObjecionBean.getModalidad()) {
                modalidadBinding.setInputValue(noObjecionBean.getModalidad());
            }

            // la selección actual de la tabla, para restaurarla en caso de un isDirty
            try {
                Row selectedRow = (Row) evaluateTable("#{bindings.NoObjecionVOIterator1.currentRow}");

                setKeyId((Long) selectedRow.getAttribute("Id"));
                JSFUtils.setExpressionValue("#{sessionScope.idNoObjecion}", keyId); 
                logger.warning(">>>>>>>>>>>>>>>>>>>>>> Id No Objecion seleccionado: " + selectedRow.getAttribute("Id"));
                List rowKeyList = new ArrayList();
                Key jboKey = new Key(new Object[] { selectedRow.getAttribute("Id") });
                rowKeyList.add(jboKey);
                RowKeySet rksNew = new RowKeySetImpl();
                rksNew.add(rowKeyList);
                setDisclosedRowKeysTable(rksNew);
                setEsSelectionEventQueue(false); // limpiamos flag de control de entrada infinita al Selectionlistener
            } catch (Exception e) {
                logger.severe("No es posible obtener la fila seleccionada", e);
            }
        }

    /**
     * En caso de una selección y existan cambios pendientes por guardar/deshacer (isDirty),
     * restauramos el current row al elemento que se estaba editando (elegido anteriormente y guardado en MatrizTccBean)
     *
     * @see Frank Nimphius response - https://community.oracle.com/thread/2480829
     */
    private void restaurarSeleccionAnterior() {
        logger.log(ADFLogger.WARNING, "Entrando a restaurarSeleccionAnterior.");
        RichTable noObjecionTable = null;

        if (!getEsSelectionEventQueue()) {
            noObjecionTable = getPflNoObjecionTableUIC();
            // 1. Regresamos el current row del tree a su estado anterior. Para ello, creamos un nuevo evento de
            // selección y lo encolamos al tree indicado. Además, reasignamos el selected row en pantalla.
            SelectionEvent selectionEvent =
                new SelectionEvent(new RowKeySetImpl(), getDisclosedRowKeysTable(), noObjecionTable);
            selectionEvent.queue(); //queue event on component, es decir, llama de nuevo al Selectionlistener
            noObjecionTable.setSelectedRowKeys(getDisclosedRowKeysTable()); // Reasigna (pinta) el selected row en pantalla

            // 2. Asigna flag de control
            setEsSelectionEventQueue(true); // Con este flag evitamos que entre repetidamente al Selectionlistener para el mismo evento

            // 3. Mostramos mensaje informando que hay cambios pendientes
            JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");

            Row selectedRow = (Row) evaluateTable("#{bindings.NoObjecionVOIterator1.currentRow}");

            keyId = (Long) selectedRow.getAttribute("Id");

            if (null != keyId) {
                DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator("NoObjecionVOIterator1");
                RowSetIterator rowSetIterator = dcItteratorBindings.getRowSetIterator();
                Key key = new Key(new Long[] { keyId });
                Row[] row = rowSetIterator.findByKey(key, 1);
                rowSetIterator.setCurrentRow(row[0]);
            }

            if (null != selectedRow) {
                logger.warning(">>>NoObjecion.invokeTable.selectedRow:" + selectedRow.getAttribute("Id"));
            }

            invokeTable("#{bindings.NoObjecionVO.collectionModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                        selectionEvent });

            RowKeySet rowSet = noObjecionTable.getSelectedRowKeys();
            rowSet.clear();

            logger.warning("remove from table id:" + noObjecionTable.getClientRowKey());

            AdfFacesContext.getCurrentInstance().addPartialTarget(noObjecionTable);
        }


    }

    public static Object invokeTable(String cadena, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext context = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(context, cadena, Object.class, paramTypes);
        return exp.invoke(context, params);
    }


    public static Object evaluateTable(String cadena) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext context = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(context, cadena, Object.class);
        return exp.getValue(context);
    }

    public void setPopupCancelarObjecion(RichPopup popupCancelarObjecion) {
        this.popupCancelarObjecion = popupCancelarObjecion;
    }

    public RichPopup getPopupCancelarObjecion() {
        return popupCancelarObjecion;
    }

    public void setPopupIniciarAdquisicion(RichPopup popupIniciarAdquisicion) {
        this.popupIniciarAdquisicion = popupIniciarAdquisicion;
    }

    public RichPopup getPopupIniciarAdquisicion() {
        return popupIniciarAdquisicion;
    }

    public void setPopupGuardarObjecion(RichPopup popupGuardarObjecion) {
        this.popupGuardarObjecion = popupGuardarObjecion;
    }

    public RichPopup getPopupGuardarObjecion() {
        return popupGuardarObjecion;
    }

    public void guardarObjecion(ActionEvent actionEvent) {
        long startTime = System.currentTimeMillis();                    
                        startTime = System.currentTimeMillis();
                logger.warning("Tiempo de inicio validacion de campos : "
                 + startTime);
        if (adquisicionValida()) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            popupGuardarObjecion.show(hints);
        }
        
        long endTime = System.currentTimeMillis();
        logger.warning("Tiempo de fin de validacion de campos: " + endTime);
        logger.warning("Tiempo diferencia "
        + (endTime - startTime)/1000 + " segundos");
    }

    public void iniciarAdquisicion(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "Entra en iniciarAdquisicion");

        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        logger.log(ADFLogger.WARNING, "Paso Aprobacion es: " + noObjecionBean.getPasoAprobacion());
        logger.log(ADFLogger.WARNING, "Habilitar campos es: " + noObjecionBean.getHabilitarCampos());
        long startTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
        logger.warning("Tiempo de inicio validacion de campos : " + startTime);

        if (montoAdjudicatariosValido()) {
            if (adquisicionValida()) {
                popupIniciarAdquisicion.show(hints);
                AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupIniciarAdquisicion());
            } else {
                JSFUtils.addFacesErrorMessage("Debe capturar los datos obligatorios.");
            }
        } else {
            JSFUtils.addFacesErrorMessage(ADFUtils.getStringFromBundle("MONTO_ADJUDICATARIOS_INVALIDA", BUNDLE));
        }
        
        logger.log(ADFLogger.WARNING, "Saliendo de iniciarAdquisicion");

        long endTime = System.currentTimeMillis();
        logger.warning("Tiempo de fin de validacion de campos: " + endTime);
        logger.warning("Tiempo diferencia " + (endTime - startTime) / 1000 + " segundos");
    }

    public String cancelarObjecion() {
        // Add event code here...
        FacesContext facesContext = FacesContext.getCurrentInstance();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        popupCancelarObjecion.show(hints);
        return null;
    }

    public String cancelarGuardar() {
        popupGuardarObjecion.hide();
        return null;
    }

    public String cancelarCancelarObjecion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        popupCancelarObjecion.hide();
        return null;
    }

    public String cancelarInicioAdquisicion() {
        popupIniciarAdquisicion.hide();
        return null;
    }

    public String aceptarGuardarObjecion() {
        popupGuardarObjecion.hide();
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        long startTime = System.currentTimeMillis();                    
                        startTime = System.currentTimeMillis();
                logger.warning("Tiempo inicio del guardado de participantes : "
                 + startTime);
        if (!guardarParticipantes()) {
            logger.warning("Error al guardar los datos de la no objecion de los participantes");
        }
        long endTime = System.currentTimeMillis();
        logger.warning("Tiempo de fin guardado de participantes: " + endTime);
        logger.warning("Tiempo de diferencia "
        + (endTime - startTime)/1000 + " segundos");
        
        long startTime2 = System.currentTimeMillis();                    
                        startTime2 = System.currentTimeMillis();
                logger.warning("Tiempo inicio del guardado  no objecion : "
                 + startTime2);
            guardarContratados();
        if (guardarNoObjecion()) {
            noObjecionBean.setHabilitarCampos(Boolean.TRUE);
            logger.warning("8 log setHabilitarCampos: " + noObjecionBean.getHabilitarCampos());
            JSFUtils.setExpressionValue("#{sessionScope.noObjecion}", noObjecionBean.getHabilitarCampos());
            //Recarga de concursantes
            recargarConcursantes();
            //recargaDatosConcursantes();
        } else {
            logger.warning("Error al guardar los datos de la no objecion");
        }
        // Precarga modalidad
        cargaModalidad(noObjecionBean.getModalidad());
        
        long endTime2 = System.currentTimeMillis();
        logger.warning("Tiempo de fin guardado de no objecion: " + endTime2);
        logger.warning("Tiempo de diferencia "
        + (endTime2 - startTime2)/1000 + " segundos");
        return null;
    }
    
    public void recargaDatosConcursantes() {
        logger.warning("Entrado a recargaDatosConcursantes");
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        operationBindingIdOp = bindingsIdOp.getOperationBinding("recargaConcursantes");
        operationBindingIdOp.getParamsMap().put("idNoObjecion", noObjecionBean.getPrecargadoNoObjecion());
        
        operationBindingIdOp.execute();
        if (!operationBindingIdOp.getErrors().isEmpty()) {
            logger.warning("No se pudo iniciar el metodo de nueva NO objecion");
        }
    }

    public String aceptarCancelarObjecion() {
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        Long idAdquisicion= noObjecionBean.getIdAdquisiones();
        popupCancelarObjecion.hide();
        noGuardarNoObjecion();
        noObjecionBean.setHabilitarCampos(Boolean.TRUE);
        logger.warning("9 log setHabilitarCampos: " + noObjecionBean.getHabilitarCampos());
        JSFUtils.setExpressionValue("#{sessionScope.noObjecion}", noObjecionBean.getHabilitarCampos());
        // Precarga modalidad
        cargaModalidad(noObjecionBean.getModalidad());
        changeAdquisicionesCurrentRow(idAdquisicion);
        
        //Precarga concursantes
        recargarConcursantes();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelNoObjecionesUIC());
        return null;
    }

    public String aceptarInicioAdquisicion() {
        logger.warning("Dentro de aceptarInicioAdquisicion");
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
   
        popupIniciarAdquisicion.hide();
        
        Long idObjecion = null;
        AttributeBinding claveIdObjecion;
        claveIdObjecion = ADFUtils.findControlBinding("Id");
        String retornarARegionPadre = null;
        if (null != (Long) claveIdObjecion.getInputValue()) {
            idObjecion = (Long) claveIdObjecion.getInputValue();
        }
        logger.warning("idNoObjecion:"+idObjecion);
        logger.warning("habilitarCampos:"+noObjecionBean.getHabilitarCampos());
        
        if (!noObjecionBean.getHabilitarCampos()) {
            if (guardarParticipantes()) {
                guardarContratados();
                if (guardarNoObjecion()) {
                    noObjecionBean.setHabilitarCampos(Boolean.TRUE);
                    logger.warning("1 log setHabilitarCampos: " + noObjecionBean.getHabilitarCampos());
                    JSFUtils.setExpressionValue("#{sessionScope.noObjecion}", noObjecionBean.getHabilitarCampos());
                } else {
                    logger.warning("Error al guardar los datos de la no objecion");
                    noObjecionBean.setHabilitarCampos(Boolean.FALSE);
                    logger.warning("2 log setHabilitarCampos: " + noObjecionBean.getHabilitarCampos());
                }
            } else {
                logger.warning("Error al guardar los datos de la no objecion de los participantes");
                noObjecionBean.setHabilitarCampos(Boolean.FALSE);
                logger.warning("3 log setHabilitarCampos: " + noObjecionBean.getHabilitarCampos());
            }
        }
        Boolean validaAdquisiciones=validarNoObjecion(idObjecion, noObjecionBean.getIdAdquisiones());
        Long idAdquisicion=null; 
        if(!validaAdquisiciones){
            idAdquisicion=obtenerIdAdquisicion(idObjecion);
            }

        
        if(validaAdquisiciones || (!validaAdquisiciones && null!=idAdquisicion)){
                if (inicioAdquisciones(idObjecion)) {
                    logger.warning("Inicio correctamente");
                    noObjecionBean.setHabilitarCampos(Boolean.TRUE);
                    logger.warning("4 log setHabilitarCampos: " + noObjecionBean.getHabilitarCampos());
                    actualizarSePuedeEliminarTreEvidenciaAdquisicion();
                    retornarARegionPadre = "irRegresarAdquisicion";
                    logger.warning("retornarARegionPadre : " + retornarARegionPadre);
                    JSFUtils.addFacesInformationMessage("Inicio proceso de adquisiciones correctamente");
                } else {
                    noObjecionBean.setHabilitarCampos(Boolean.FALSE);
                logger.warning("5 log setHabilitarCampos: " + noObjecionBean.getHabilitarCampos());
                    logger.warning("No inicio correctamente");
                }            
            }
        else{
                JSFUtils.addFacesErrorMessage("No se puede iniciar el proceso de adquisiciones, la no " +
                    "objeción ingresada no pertenece a la adquisción");
                noObjecionBean.setHabilitarCampos(Boolean.FALSE);
                logger.warning("6 log setHabilitarCampos: " + noObjecionBean.getHabilitarCampos());
            }

        JSFUtils.setExpressionValue("#{sessionScope.noObjecion}", noObjecionBean.getHabilitarCampos());
        //recargarConcursantes();
        
        recargaConcursantes(idObjecion);
        
        // Refrescar tabla de contratados
        refreshAdjudicatarios();
        refreshContratados();
        
       // recargaDatosConcursantes();
        // Precarga modalidad
        cargaModalidad(noObjecionBean.getModalidad());
        
        //Seleccionamos nuevamente la objecion por perdida de current row en la cargaModalidad
        changeNoObjecionCurrentRow(idObjecion);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelNoObjecionesUIC());

        logger.warning("Dentro de aceptarInicioAdquisicion");
        return retornarARegionPadre;
    }
    
        public Boolean actualizarSePuedeEliminarTreEvidenciaAdquisicion() {
        logger.warning("Entra en actualizarSePuedeEliminarTreEvidenciaAdquisicion");
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;

        try {
            //para actualizar la columna SE_PUEDE_ELIMINAR en TRE_EVIDENCIA_CONDICION
            operationBindingIdOp = bindingsIdOp.getOperationBinding("actualizarSePuedeEliminarTreEvidenciaAdquisicion");
            resultado = (Boolean) operationBindingIdOp.execute();
            if (!operationBindingIdOp.getErrors().isEmpty()) {
                logger.warning("error al actualizar los registros en treEvidenciaAdquisicion");
                resultado = Boolean.FALSE;
            }
        } catch (Exception e) {
            logger.warning("Error en actualizarSePuedeEliminarTreEvidenciaAdquisicion.", e);
        }

        logger.warning("valor obtenido: " + resultado);
        return resultado;
    }
        
    public Boolean ValidaNoObjecion(Integer tipoNoObjecion) {
        Boolean respuesta = Boolean.TRUE;
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        if (noObjecionBean.getTarea().compareTo(FenixConstants.PA09_REALIZAR_AJUSTES) == 0) {

        }

        if (null != tipoNoObjecion) {
            AttributeBinding fechaPublicacion;
            fechaPublicacion = ADFUtils.findControlBinding("FechaPublicacion");
            AttributeBinding fechaFinDispDoctoBase;
            fechaFinDispDoctoBase = ADFUtils.findControlBinding("FechaFinDispDoctoBase");
            AttributeBinding fechaRecepcionPropuesta;
            fechaRecepcionPropuesta = ADFUtils.findControlBinding("FechaRecepcionPropuesta");
            AttributeBinding fechaInicioDispDoctoBase;
            fechaInicioDispDoctoBase = ADFUtils.findControlBinding("FechaInicioDispDoctoBase");
            AttributeBinding lugarObtenerDoctoBase;
            lugarObtenerDoctoBase = ADFUtils.findControlBinding("LugarObtenerDoctoBase");

            AttributeBinding correoInformacionAdicional;
            correoInformacionAdicional = ADFUtils.findControlBinding("CorreoInformacionAdicional");
            AttributeBinding dirPresentacionPropuesta;
            dirPresentacionPropuesta = ADFUtils.findControlBinding("DirPresentacionPropuesta");
            AttributeBinding idTcaResultadoProceso;
            idTcaResultadoProceso = ADFUtils.findControlBinding("IdTcaResultadoProceso");
            switch (tipoNoObjecion) {
            case FenixConstants.NO_OBJECION_AVISO:
                if (null == fechaPublicacion.getInputValue()) {
                }
                break;
            case FenixConstants.NO_OBJECION_INFORME_RESULTADOS:
                idTcaResultadoProceso.setInputValue(null);
                //Borrado de registros de las tablas
                break;
            case FenixConstants.NO_OBJECION_CONTRATO:
                //Borrado de registros de las tablas
                break;
            case FenixConstants.NO_OBJECION_RESULTADO_PROCESO:
                //Borrado de registros de las tablas
                break;
            case FenixConstants.NO_OBJECION_CARGO_COMPRA:
                //Borrado de registros de las tablas
                break;

            }
        }


        return respuesta;
    }

    public Boolean inicioAdquisciones(Long idNoObjecion) {
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        //metodo para revertir el guardado de la no objecion
        operationBindingIdOp = bindingsIdOp.getOperationBinding("iniciarProceso");
        operationBindingIdOp.getParamsMap().put("idNoObjecion", idNoObjecion);
        resultado = (Boolean) operationBindingIdOp.execute();
        if (!operationBindingIdOp.getErrors().isEmpty()) {
            logger.warning("No se pudo iniciar el proceso de las adquisciones");
            resultado = Boolean.FALSE;
        }
        logger.warning("valor obtenido: " + resultado);
        return resultado;
    }
    
        public Boolean validarNoObjecion(Long idNoObjecion, Long idAdquisicion) {
            Boolean resultado = Boolean.TRUE;
            BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
            OperationBinding operationBindingIdOp = null;
            //metodo para revertir el guardado de la no objecion
            //Long noObjecionPrueba=33L;
            operationBindingIdOp = bindingsIdOp.getOperationBinding("validarNoObjecion");
            operationBindingIdOp.getParamsMap().put("noObjecion", idNoObjecion);
            operationBindingIdOp.getParamsMap().put("idAdquisicion", idAdquisicion);
            resultado = (Boolean) operationBindingIdOp.execute();
            if (!operationBindingIdOp.getErrors().isEmpty()) {
                logger.warning("No se pudo obtener la validacion de la no objecion");
                resultado = Boolean.FALSE;
            }
            logger.warning("valor obtenido: " + resultado);
            return resultado;
        }
        
        public Long obtenerIdAdquisicion(Long idNoObjecion) {
            Long resultado = null;
            NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
            BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
            OperationBinding operationBindingIdOp = null;
            //metodo para revertir el guardado de la no objecion
            //Long noObjecionPrueba=33L;
            operationBindingIdOp = bindingsIdOp.getOperationBinding("obtenerAdquisicion");
            operationBindingIdOp.getParamsMap().put("idObjecion", idNoObjecion);
            resultado = (Long) operationBindingIdOp.execute();
            if (!operationBindingIdOp.getErrors().isEmpty()) {
                logger.warning("No se pudo obtener el id de adquisicon de la no objecion");
                resultado=null;
            }
            else{
                if(null!=resultado){
                        noObjecionBean.setIdAdquisiones(resultado);
                    }

                }
            logger.warning("valor obtenido de la adquisicion: " + resultado);
            return resultado;
        }
    
    public void cargaModalidad(Integer modalidad) {
        if(null!=modalidad){
                BindingContainer bindings = ADFUtils.getBindingContainer();
                OperationBinding operationBinding = bindings.getOperationBinding("precargaModalidad");
                operationBinding.getParamsMap().put("modalidad", modalidad);
                operationBinding.execute();
                AdfFacesContext.getCurrentInstance().addPartialTarget(getPflNoObjecionTableUIC());
            }
    }

    public Boolean guardarParticipantes() {
        logger.warning("Dentro de guardarParticipantes");
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        Boolean resultado = Boolean.TRUE;
        Long idObjecion = null;
        AttributeBinding claveIdObjecion = null;
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        
        claveIdObjecion = ADFUtils.findControlBinding("Id");
        if (null != (Long) claveIdObjecion.getInputValue()) {
            idObjecion = (Long) claveIdObjecion.getInputValue();
            logger.warning("idNoObjecion :"+idObjecion);
        }else{
            logger.warning("idNoObjecion es null");
        }
        
        //metodo para guardar los participantes seleccionados en la vista dentro de la tabla NO_OBJECION_PARTICIPANTE
        operationBindingIdOp = bindingsIdOp.getOperationBinding("guardarParticipantes");
        operationBindingIdOp.getParamsMap().put("idNoObjecion", idObjecion);
        resultado = (Boolean) operationBindingIdOp.execute();
        if (!operationBindingIdOp.getErrors().isEmpty()) {
            logger.severe("ERROR al ejecutar el metodo guardarParticipantes");
            resultado = Boolean.FALSE;
        }
        
        logger.warning("Fuera de guardarParticipantes");
        return resultado;
    }

    public Boolean guardarNoObjecion() {
        logger.warning("Inside guardarNoObjecion.");
        
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        long startTime = System.currentTimeMillis(); 
        logger.warning("Tiempo de inicio del guardado: " + startTime);
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        
        // Recuperar el id de la nueva no objecion
        Long nuevoIdNoObjecion = null;
        AttributeBinding idBinding = ADFUtils.findControlBinding("Id");
        if (null != idBinding) {
            try {
                nuevoIdNoObjecion = (Long)idBinding.getInputValue();
            } catch(Exception e) {
                logger.warning("Error al recuperar el nuevo id de la noObjecion.", e);
            }
        }
        
        logger.warning("Valor de nuevoIdNoObjecion: " + nuevoIdNoObjecion);
        
        //metodo para guardar la no objecion
        operationBindingIdOp = bindingsIdOp.getOperationBinding("comitNoObjecion");
        String respuesta = (String) operationBindingIdOp.execute();         
        logger.warning("respuesta guardarNoObjecion: " + respuesta);
        resultado = respuesta == "OK";
        
        if (!operationBindingIdOp.getErrors().isEmpty()) {
            logger.warning("No se pudo guardar el metodo de nueva NO objecion");
            resultado = Boolean.FALSE;
        } else {
            if (null != nuevoIdNoObjecion) {
                changeNoObjecionCurrentRow(nuevoIdNoObjecion);
            }
        }
        
        changeAdquisicionesCurrentRow(noObjecionBean.getIdAdquisiones());
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelNoObjecionesUIC());
        long endTime = System.currentTimeMillis();
        logger.warning("Tiempo de fin del guardado: " + endTime);
        logger.warning("Tiempo de diferencia: "
        + (endTime - startTime)/1000 + " segundos");
        return resultado;
    }
    
    public void changeAdquisicionesCurrentRow(Long idAdquisicion) {
        logger.warning("Inside changeAdquisicionesCurrentRow.");
        logger.warning("idAdquisicion" + idAdquisicion);
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("seleccionarAdquisicion");
        operationBinding.getParamsMap().put("idAdquisicion", idAdquisicion);
        operationBinding.execute();   
    }
    
    public void changeNoObjecionCurrentRow(Long idNoObjecion) {
        logger.warning("Inside changeNoObjecionCurrentRow.");
        logger.warning("idNoObjecion:" + idNoObjecion);
        
        Boolean resultadoSeleccion = Boolean.TRUE;
        BindingContainer bindingsSeleccion = ADFUtils.getBindingContainer();
        OperationBinding operationSeleccion = null;
        try {
            NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");

            bindingsSeleccion = ADFUtils.getBindingContainer();
            operationSeleccion = bindingsSeleccion.getOperationBinding("seleccionarNoObjecion2");
            operationSeleccion.getParamsMap().put("idNoObjecion", idNoObjecion);
            operationSeleccion.getParamsMap().put("modalidad", noObjecionBean.getModalidad());
            resultadoSeleccion = (Boolean) operationSeleccion.execute();
            keyId = idNoObjecion;
            logger.warning("Resultado seleccionar nueva NO objecion: " + resultadoSeleccion);
        } catch (Exception e) {
            logger.warning("Error seleccionar nueva NO objecion.", e);
        }  
    }

    public Boolean guardarParticipantesVO(Long idNoObjecion, Integer rol, Integer obligatorio) {
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        //metodo para revertir el guardado de la no objecion
        operationBindingIdOp = bindingsIdOp.getOperationBinding("agregarParticipante");
        operationBindingIdOp.getParamsMap().put("idNoObjecion", idNoObjecion);
        operationBindingIdOp.getParamsMap().put("obligatorio", obligatorio);
        operationBindingIdOp.getParamsMap().put("rol", rol);
        resultado = (Boolean) operationBindingIdOp.execute();
        if (!operationBindingIdOp.getErrors().isEmpty()) {
            logger.warning("No se pudo iniciar el metodo de nueva NO objecion");
            resultado = Boolean.FALSE;
        }
        return resultado;
    }

    public Boolean noGuardarNoObjecion() {
        Boolean resultado = Boolean.TRUE;
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        //metodo para revertir el guardado de la no objecion
        operationBindingIdOp = bindingsIdOp.getOperationBinding("rollBackNoObjecion");
        resultado = (Boolean) operationBindingIdOp.execute();
        if (!operationBindingIdOp.getErrors().isEmpty()) {
            logger.warning("No se pudo iniciar el metodo de nueva NO objecion");
            resultado = Boolean.FALSE;
        }
        return resultado;
    }

    public void setParticipantesUI(RichPanelHeader participantesUI) {
        this.participantesUI = participantesUI;
    }

    public RichPanelHeader getParticipantesUI() {
        return participantesUI;
    }

    public void agregarAdjudicatarioActionListener(ActionEvent actionEvent) {
        OperationBinding oferentesParamBinding;

        oferentesParamBinding = ADFUtils.findOperation("crearRowAdjudicatario");
        oferentesParamBinding.execute();

        if (!oferentesParamBinding.getErrors().isEmpty()) {
            logger.warning("No se pudo agregar el adjudicatario");
        }
        else
        {
                actualizarTablaAdjudicatarios();
            }
    }

    public void contratarAdjudicatarioActionListener(ActionEvent actionEvent) {
        logger.warning("Inside contratarAdjudicatarioActionListener.");

        Row selectedRow = (Row) evaluateTable("#{bindings.AdjudicatariosContratoVOIterator.currentRow}");

        OperationBinding oferentesParamBinding;

        oferentesParamBinding = ADFUtils.findOperation("crearRowContratadosAdjudicatarios");
        oferentesParamBinding.getParamsMap().put("row", selectedRow);
        oferentesParamBinding.execute();
        
        oferentesParamBinding = ADFUtils.findOperation("eliminarAdjudicatarioContrato");
        oferentesParamBinding.getParamsMap().put("adjudicatario", selectedRow);
        oferentesParamBinding.execute();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getAdjudicatarioTableUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContratadoTableUIC());
    }
    
    public void eliminarContratadoAdjudicatario() {
        logger.warning("Inside eliminarContratadoAdjudicatario.");

        Row selectedRow = (Row) evaluateTable("#{bindings.ContratadosAdjudicatariosVOIterator.currentRow}");

        OperationBinding oferentesParamBinding;

        oferentesParamBinding = ADFUtils.findOperation("crearRowAdjudicatarioContrato");
        oferentesParamBinding.getParamsMap().put("row", selectedRow);
        oferentesParamBinding.execute();
        
        oferentesParamBinding = ADFUtils.findOperation("eliminarContratadoAdjudicatario");
        oferentesParamBinding.getParamsMap().put("contratado", selectedRow);
        oferentesParamBinding.execute();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getAdjudicatarioTableUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContratadoTableUIC());
    }

    public void refreshAdjudicatarios() {
        logger.warning("Inside refreshAdjudicatarios.");

        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");

        Long idAdquisicion = noObjecionBean.getIdAdquisiones();

        if (idAdquisicion != null) {
            OperationBinding oferentesParamBinding;

            oferentesParamBinding = ADFUtils.findOperation("llenarAdjudicatariosContrato");
            oferentesParamBinding.getParamsMap().put("idAdquisicion", idAdquisicion);
            oferentesParamBinding.execute();
        }
    }
    
    public void refreshContratados() {
        logger.warning("Inside refreshContratados.");
        
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        
//        AttributeBinding idNoObjecionAT;
//        idNoObjecionAT = ADFUtils.findControlBinding("Id");
        Long idNoObjecion = null;

//        if (idNoObjecionAT != null && idNoObjecionAT.getInputValue() != null &&
//            idNoObjecionAT.getInputValue().toString().length() > 0) {
//            idNoObjecion = Long.valueOf(idNoObjecionAT.getInputValue().toString());
//        }
        
        idNoObjecion = noObjecionBean.getPrecargadoNoObjecion();
        
        if (idNoObjecion != null) {
            OperationBinding oferentesParamBinding;

            oferentesParamBinding = ADFUtils.findOperation("llenarContratados");
            oferentesParamBinding.getParamsMap().put("idNoObjecion", idNoObjecion);
            oferentesParamBinding.execute();
        }
    }
    
    public void limpiarContratados() {
        logger.warning("Inside limpiarContratados.");

        OperationBinding oferentesParamBinding;

        oferentesParamBinding = ADFUtils.findOperation("limpiarContratados");
        oferentesParamBinding.execute();
    }
    
    public void guardarContratados() {
        logger.warning("Inside guardarContratados.");
        
        String idTcaTipoNoObjecion = (ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion") == null) ? null :
                                             ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion").toString();
        
        AttributeBinding idNoObjecionAT;
        idNoObjecionAT = ADFUtils.findControlBinding("Id");
        Long idNoObjecion = null;

        if (idNoObjecionAT != null && idNoObjecionAT.getInputValue() != null &&
            idNoObjecionAT.getInputValue().toString().length() > 0) {
            idNoObjecion = Long.valueOf(idNoObjecionAT.getInputValue().toString());
        }
        
        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion").toString().length() > 0))) {
            logger.warning("El tipo de No Objecion es Null.");
        } else {
            Integer tipoNoObjecion = Integer.valueOf(idTcaTipoNoObjecion);
            if (tipoNoObjecion.compareTo(9) == 0) {
                if (idNoObjecion != null) {
                    OperationBinding oferentesParamBinding;
                    
                    oferentesParamBinding = ADFUtils.findOperation("contratarAdjudicatarios");
                    oferentesParamBinding.getParamsMap().put("idNoObjecion", idNoObjecion);
                    oferentesParamBinding.execute();
                    /*
                    oferentesParamBinding = ADFUtils.findOperation("adjudicarContratados");
                    oferentesParamBinding.getParamsMap().put("idNoObjecion", idNoObjecion);
                    oferentesParamBinding.execute();*/
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private boolean adquisicionValida() {
        logger.log(ADFLogger.WARNING, "Entrando en adquisicionValida.");
        boolean valida = Boolean.TRUE;
        
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");

        OperationBinding validarCamposBinding = null;
        validarCamposBinding = ADFUtils.findOperation("validarCampos");
        validarCamposBinding.getParamsMap().put("idAdquisicion", noObjecionBean.getIdAdquisiones());

        HashMap<String, Object> result = (HashMap<String, Object>) validarCamposBinding.execute();

        if (!validarCamposBinding.getErrors().isEmpty()) {
            logger.warning("No se pudo iniciar el metodo de validarCampos en la NO objecion");
        }
        
        NoObjecionValidationBean noObjecionValidationBean =
            (NoObjecionValidationBean) JSFUtils.resolveExpression("#{noObjecionValidationBean}");
        
        if (!noObjecionValidationBean.evaluarValidaCampos(result)) {
            valida = Boolean.FALSE;
        }

        logger.log(ADFLogger.WARNING, "Valor de retorno: " + valida);
        return valida;
    }

    @SuppressWarnings("unchecked")
    public void cargarDatosParticipantes() {
        logger.warning("cargarDatosParticipantes.");
        AttributeBinding idNoObjecionAT;
        AttributeBinding idTcaTipoNoObjecionAT;
        idNoObjecionAT = ADFUtils.findControlBinding("Id");
        idTcaTipoNoObjecionAT = ADFUtils.findControlBinding("IdTcaTipoNoObjecion");
        Long idNoObjecion = null;
        Integer idTcaTipoNoObjecion = null;

        if (idNoObjecionAT != null && idNoObjecionAT.getInputValue() != null &&
            idNoObjecionAT.getInputValue().toString().length() > 0) {
            idNoObjecion = Long.valueOf(idNoObjecionAT.getInputValue().toString());
        }

        if (idTcaTipoNoObjecionAT != null && idTcaTipoNoObjecionAT.getInputValue() != null &&
            idTcaTipoNoObjecionAT.getInputValue().toString().length() > 0) {
            idTcaTipoNoObjecion = Integer.valueOf(idTcaTipoNoObjecionAT.getInputValue().toString());
        }

        logger.warning("idNoObjecion:" + idNoObjecion);
        logger.warning("idTcaTipoNoObjecion:" + idTcaTipoNoObjecion);

        if (null != idNoObjecion && null != idTcaTipoNoObjecion) {
            OperationBinding limpiarBinding = ADFUtils.findOperation("limpiarParticiapantes");
            limpiarBinding.execute();

            OperationBinding paramBinding = ADFUtils.findOperation("cargarDatosParticipantes");
            paramBinding.getParamsMap().put("idNoObjecion", idNoObjecion);
            paramBinding.getParamsMap().put("tipo", idTcaTipoNoObjecion);
            paramBinding.execute();
        }
        
        datosParticipantesMapping();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getParticipantesUI());
    }
    
    public void datosParticipantesMapping() {
        logger.log(ADFLogger.WARNING, "INTO datosParticipantesMapping.");
        
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        noObjecionBean.datosParticipantesMapping();        
    }
    
    @SuppressWarnings("unchecked")
    public void recargarConcursantes() {
        logger.log(ADFLogger.WARNING, "Entrando a recargarConcursantes");
        OperationBinding operationBindingIdOp;
        Object resultObject;
        Object adjudicatariosParamObject;
        AttributeBinding idNoObjecionAT;
        idNoObjecionAT = ADFUtils.findControlBinding("Id");
        Integer idNoObjecion = null;
        String idTcaTipoNoObjecion = (ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion") == null) ? null :
                                             ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion").toString();
        
        if (idNoObjecionAT != null && idNoObjecionAT.getInputValue() != null &&
            idNoObjecionAT.getInputValue().toString().length() > 0) {
            idNoObjecion = Integer.valueOf(idNoObjecionAT.getInputValue().toString());
        }
        
        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoNoObjecion").toString().length() > 0))) {
            logger.warning("El tipo de No Objecion es Null.");
        } else {
            Integer tipoNoObjecion = Integer.valueOf(idTcaTipoNoObjecion);
            if (tipoNoObjecion.compareTo(9) == 0) {
               refreshAdjudicatarios();
               refreshContratados();
               
               contratadosWhereParams(idNoObjecion);
               actualizarTablaContratados();
            } else {
                if (null != idNoObjecion) {
                    logger.info(">>>>>>>oferentesWhereParams");
                    operationBindingIdOp = ADFUtils.findOperation("oferentesWhereParams");
                    operationBindingIdOp.getParamsMap().put("idNoObjecion", idNoObjecion);
                    operationBindingIdOp.getParamsMap().put("idTcaTipoPerfil", TipoConcursanteEnum.OFERENTE.getId());
                    resultObject = operationBindingIdOp.execute();

                    logger.info(">>>>>>>adjudicatariosWhereParams");
                    operationBindingIdOp = ADFUtils.findOperation("adjudicatariosWhereParams");
                    operationBindingIdOp.getParamsMap().put("idNoObjecion", idNoObjecion);
                    operationBindingIdOp.getParamsMap().put("idTcaTipoPerfil", TipoConcursanteEnum.ADJUDICATARIO.getId());
                    resultObject = operationBindingIdOp.execute();

                    contratadosWhereParams(idNoObjecion);

                    actualizarTablaOferentes();
                    actualizarTablaAdjudicatarios();
                    actualizarTablaContratados();
                }
            }
        }
        logger.finest(getClass().getName(), "recargarConcursantes");
    }
    
    private void contratadosWhereParams(Integer idNoObjecion) {
        logger.log(ADFLogger.WARNING, "entrando a contratadosWhereParams.");
        logger.log(ADFLogger.WARNING, "idNoObjecion: " + idNoObjecion);
        OperationBinding operationBindingIdOp;
        Object resultObject;
        
        if (null != idNoObjecion) {
            logger.info(">>>>>>>contratadosWhereParams");
            operationBindingIdOp = ADFUtils.findOperation("contratadosWhereParams");
            operationBindingIdOp.getParamsMap().put("idNoObjecion", idNoObjecion);
            operationBindingIdOp.getParamsMap().put("idTcaTipoPerfil", TipoConcursanteEnum.CONTRATADO.getId());
            resultObject = operationBindingIdOp.execute();
        } else {
            logger.log(ADFLogger.WARNING, "No se estan recibiendo los parametros necesarios.");
        }
    }

    public String getReporteFileName() {
        documento = "plantilla1.pdf";
        return documento;
    }

    public String getReporteFileNameVista() {
        Integer publica = 0;
        Integer tipo = null;
        AttributeBinding clavetipoObjecion;
        clavetipoObjecion = ADFUtils.findControlBinding("IdTcaTipoNoObjecion");
        if (null != (Integer) clavetipoObjecion.getInputValue()) {
            tipo = (Integer) clavetipoObjecion.getInputValue();

            if (tipo.compareTo(FenixConstants.TIPO_NOOBJECION_AVISO_COBRO) == 0) {
                documentoVista = FenixConstants.PLANTILLA_AVISO_COBRO;
            }

            if (tipo.compareTo(FenixConstants.TIPO_NOOBJECION_CONTRATO) == 0) {
                documentoVista = FenixConstants.PLANTILLA_CONTRATO;
            }
            Integer resultado = null;
            AttributeBinding claveResultado;
            claveResultado = ADFUtils.findControlBinding("IdTcaResultadoProceso");
            if (null != (Integer) claveResultado.getInputValue()) {
                resultado = (Integer) claveResultado.getInputValue();
                if (resultado.compareTo(FenixConstants.TIPO_NOOBJECION_INFORME_ADJUDICADO) == 0) {
                    documentoVista = FenixConstants.PLANTILLA_INFORME_ADJUDICADO;
                } else {
                    if (resultado.compareTo(FenixConstants.TIPO_NOOBJECION_INFORME_ANULADO) == 0) {
                        documentoVista = FenixConstants.PLANTILLA_INFORME_ANULADO;
                    } else {
                        documentoVista = FenixConstants.PLANTILLA_INFORME_OTROS;
                    }
                }
            }
        }

        return getDocumentoVista();
    }

    public StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);
        return writer;
    }

    private void applyIE11Fix(String docName) {
        RequestContext requestCtx = RequestContext.getCurrentInstance();
        Agent agent = requestCtx.getAgent();
        String version = agent.getAgentVersion();
        String browser = agent.getAgentName();

        //if ie 11
        if (browser != null && "ie".equals(browser) && version != null && version.startsWith("11")) {

            HttpServletResponse resp =
                (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            String finalName = docName;

            try {
                finalName = URLEncoder.encode(docName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.warning("Error: " + e.getMessage());
            }

            resp.setHeader("content-disposition", "attachment; filename=\"" + finalName + "\"");
            resp.setContentType("application/octet-stream");
        }
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

    public String downloadPublicar() {
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        Integer publica = 1;
        Long idObjecion = null;
        servicioRespuesta=Boolean.FALSE;
        mensaje=null;
        AttributeBinding claveIdObjecion;
        claveIdObjecion = ADFUtils.findControlBinding("Id");
        if (null != (Long) claveIdObjecion.getInputValue()) {
            idObjecion = (Long) claveIdObjecion.getInputValue();
        }

        byte[] documentoVistaPublica = null;
        try {
            applyIE11Fix(getDocumento());
            documentoVistaPublica = crearDoc(noObjecionBean.getIdAdquisiones(), idObjecion, publica);
            if (documentoVistaPublica != null && documentoVistaPublica.length > 0) {
            }

        } catch (Exception ioex) {
            ioex.printStackTrace();
        } finally {
        }
        return null;
    }

    public void downloadVistaPrevia(FacesContext facesContext, OutputStream outputStream) {
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        servicioRespuesta=Boolean.FALSE;
        mensaje=null;

        Long idObjecion = null;
        AttributeBinding claveIdObjecion;
        claveIdObjecion = ADFUtils.findControlBinding("Id");
        if (null != (Long) claveIdObjecion.getInputValue()) {
            idObjecion = (Long) claveIdObjecion.getInputValue();
        }
        Integer publica = 0;
        byte[] documentoVistaPublica = null;
        try {
            applyIE11Fix(getDocumentoVista());
            documentoVistaPublica = crearDoc(noObjecionBean.getIdAdquisiones(), idObjecion, publica);
            if (documentoVistaPublica != null && documentoVistaPublica.length > 0) {
                outputStream.write(documentoVistaPublica);
            }

        } catch (IOException ioex) {
            ioex.printStackTrace();
        } finally {
            flushAndCloseOutputStream(outputStream);
        }
    }

    private byte[] crearDoc(long adquisicionId, Long noObjecion, Integer vistaPublicar) {
        logger.warning("Ingresa al metodo crearDoc");
        
        logger.warning("Se recibe adquisicionId: " + adquisicionId);
        logger.warning("Se recibe noObjecion: " + noObjecion);
        logger.warning("Se recibe vistaPublicar: " + vistaPublicar);
        
        String mensajeError = null;
        Boolean esError = Boolean.FALSE;

        InformeNoObjecionRequestType request = new InformeNoObjecionRequestType();

        InformeNoObjecionResponseType response = null;

        try {
            AdquisicionPT12BndQSService adquisicion = this.initAdquisicionService();
            AdquisicionPT adquisicionPT = adquisicion.getAdquisicionPT12BndQSPort();
            // Add your code to call the desired methods.

            request.setIdAdquisicion(adquisicionId);
            request.setIdNoObjecion(noObjecion);
            request.setRequierePublicacion(vistaPublicar);
            String mensaje = null;
            StringWriter xmlEntrada = null;
            StringWriter xmlSalida = null;
            mensaje = "Invocando Servicio - Aviso de cobro comision-";
            //try{xmlEntrada = writeXMLRequest(request, request.getClass());}catch(Exception ex){;}
            try {
                logger.log(ADFLogger.WARNING, ">HNWS" + writeXMLRequest(request, request.getClass()));
            } catch (Exception ex) {
                ;
            }
            response = adquisicionPT.publicarNoObjecion(request);
            mensaje = "Invocando Servicio - Aviso de cobro comision -";
            try {
                logger.log(ADFLogger.WARNING, ">HNWS" + writeXMLRequest(response, response.getClass()));
            } catch (Exception ex) {
                ;
            }

            if (response.getResultado().getResult().value() == "ERROR") {
                mensajeError = response.getResultado().getMessage();
                mensajeError = mensajeError.concat(response.getResultado().getError().getErrorDescription());
                esError = Boolean.TRUE;
            }
            
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR, "Error al generar el aviso de cobro " + e.getClass() + ":" + e.getMessage());

        } finally {
            if (esError) {
                JSFUtils.addFacesErrorMessage(mensajeError);
            } else {
                JSFUtils.addFacesInformationMessage(response.getResultado().getMessage());
            }
        }
        logger.warning("Finaliza el metodo crearDoc");
        return response != null ? response.getDocumento().getContent() : null;
    }

    private AdquisicionPT12BndQSService initAdquisicionService() {
        DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = dcItteratorBindings.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[] { IWsdlLocation.ADQUISICION }));
        String wsdl = row == null ? null : (String) row.getAttribute("Valor");

        return IWsdlLocation.Service.getInstance(AdquisicionPT12BndQSService.class, wsdl);
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDocumentoVista() {
        return documentoVista;
    }

    public void setDocumentoVista(String documentoVista) {
        this.documentoVista = documentoVista;
    }

    public Boolean getEsSelectionEventQueue() {
        return esSelectionEventQueue;
    }

    public void setEsSelectionEventQueue(Boolean esSelectionEventQueue) {
        this.esSelectionEventQueue = esSelectionEventQueue;
    }

    public RowKeySet getDisclosedRowKeysTable() {
        return disclosedRowKeysTable;
    }

    public void setDisclosedRowKeysTable(RowKeySet disclosedRowKeysTable) {
        this.disclosedRowKeysTable = disclosedRowKeysTable;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public RichTable getPflNoObjecionTableUIC() {
        return pflNoObjecionTableUIC;
    }

    public void setPflNoObjecionTableUIC(RichTable pflNoObjecionTableUIC) {
        this.pflNoObjecionTableUIC = pflNoObjecionTableUIC;
    }

    public Long getSelectedRowId() {
        Long rowId = 0L;
        if (null != keyId) {
            rowId = keyId;
        }
        return rowId;
    }

    public Boolean getServicioRespuesta() {
        return servicioRespuesta;
    }

    public void setServicioRespuesta(Boolean servicioRespuesta) {
        this.servicioRespuesta = servicioRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @SuppressWarnings("unchecked")
    private void cambiarEstadoParticipantes(int idRol, boolean participa, boolean obligatorio) {
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        
        operationBindingIdOp = bindingsIdOp.getOperationBinding("cambiarEstadoParticipantes");
        operationBindingIdOp.getParamsMap().put("idRol", idRol);
        operationBindingIdOp.getParamsMap().put("participa", participa);
        operationBindingIdOp.getParamsMap().put("obligatorio", obligatorio);
        operationBindingIdOp.execute();
    }
    
    public void cambioOficial(ValueChangeEvent valueChangeEvent) {
        logger.log("cambioOficial");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean participa = (Boolean) valueChangeEvent.getNewValue();
        logger.log(ADFLogger.WARNING, "participa: " + participa);
        if (null != participa) {
            NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
            cambiarEstadoParticipantes(FenixModelConstants.ROL_OFICIAL_ADQUISICIONES, 
                                       participa, noObjecionBean.isOficialObligatorio());
        }
    }
    
    public void cambioAbogado(ValueChangeEvent valueChangeEvent) {
        logger.log("cambioAbogado");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean participa = (Boolean) valueChangeEvent.getNewValue();
        logger.log(ADFLogger.WARNING, "participa: " + participa);
        if (null != participa) {
            NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
            cambiarEstadoParticipantes(FenixModelConstants.ROL_ABOGADO, 
                                       participa, noObjecionBean.isAbogadoObligatorio());
        }
    }
    
    public void cambioAnalista(ValueChangeEvent valueChangeEvent) {
        logger.log("cambioAnalista");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Boolean participa = (Boolean) valueChangeEvent.getNewValue();
        logger.log(ADFLogger.WARNING, "participa: " + participa);
        if (null != participa) {
            NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
            cambiarEstadoParticipantes(FenixModelConstants.ROL_ANALISTA_SUPERVISION, 
                                       participa, noObjecionBean.isAnalistaObligatorio());
        }
    }

    public void cambioAbogado(ActionEvent actionEvent) {
        logger.warning("Dentro de cambioAbogado");
        Boolean participa = null;
        try{
            participa = (Boolean) 
            JSFUtils.resolveExpression("#{pageFlowScope.pParticipante}");
            logger.warning("participa : " + participa);
        }catch(Exception e){
            logger.severe("Error al recuperar pageFlowScope.pParticipante",e);
        }

        if (null != participa) {
            NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");

            noObjecionBean.setAbogadoParticipa(participa);
            cambiarEstadoParticipantes(FenixModelConstants.ROL_ABOGADO, 
                                       participa, noObjecionBean.isAbogadoObligatorio());
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(getParticipantesUI());

        logger.warning("Termina de cambioAbogado");
    }

    public void cambioOficial(ActionEvent actionEvent) {
        logger.warning("Dentro de cambioOficial");
        Boolean participa = null;
        try{
            participa = (Boolean) 
            JSFUtils.resolveExpression("#{pageFlowScope.pParticipante}");
            logger.warning("participa : " + participa);
        }catch(Exception e){
            logger.severe("Error al recuperar pageFlowScope.pParticipante",e);
        }

        if (null != participa) {
            NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");

            noObjecionBean.setOficialParticipa(participa);
            cambiarEstadoParticipantes(FenixModelConstants.ROL_OFICIAL_ADQUISICIONES, 
                                       participa, noObjecionBean.isOficialObligatorio());
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(getParticipantesUI());
        
        logger.warning("Termina de cambioOficial");
    }

    public void cambioAnalista(ActionEvent actionEvent) {
        logger.warning("Dentro de cambioAnalista");
        Boolean participa = null;
        try{
            participa = (Boolean) 
            JSFUtils.resolveExpression("#{pageFlowScope.pParticipante}");
            logger.warning("participa : " + participa);
        }catch(Exception e){
            logger.severe("Error al recuperar pageFlowScope.pParticipante",e);
        }

        if (null != participa) {
            NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");

            noObjecionBean.setAnalistaParticipa(participa);
            cambiarEstadoParticipantes(FenixModelConstants.ROL_ANALISTA_SUPERVISION, 
                                       participa, noObjecionBean.isAnalistaObligatorio());
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(getParticipantesUI());
        
        logger.warning("Termina de cambioAnalista");
    }

    @SuppressWarnings("unchecked")
    private boolean montoAdjudicatariosValido() {
        boolean montoValido = Boolean.FALSE;
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        Long idObjecion = null;
        Long idAdquisicion = null;
        
        try {
            AttributeBinding idObjecionAttr;
            idObjecionAttr = ADFUtils.findControlBinding("Id");
            
            if (null != idObjecionAttr.getInputValue()) {
                idObjecion = (Long) idObjecionAttr.getInputValue();
            }
            
            idAdquisicion = noObjecionBean.getIdAdquisiones();
        } catch(Exception e) {
            logger.warning("Error al obtener el id de no no objecion.", e);
        }
        
        logger.warning("idObjecion: " + idObjecion);
        logger.warning("idAdquisicion: " + idAdquisicion);
        
        if (null != idAdquisicion && null != idObjecion) {
            BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            operationBinding = bindingsIdOp.getOperationBinding("validarMontoAdjudicatarios");
            operationBinding.getParamsMap().put("idAdquisicion", idAdquisicion);
            operationBinding.getParamsMap().put("idNoObjecion", idObjecion);
            operationBinding.execute();
            
            if (operationBinding.getErrors().isEmpty()) {
                montoValido = (Boolean)operationBinding.getResult();
            } else {
                JSFUtils.addFacesInformationMessage(operationBinding.getErrors().toString());
            }
        }
        
        return montoValido;
    }

    public RichSelectBooleanCheckbox getOficialUIC() {
        return oficialUIC;
    }

    public void setOficialUIC(RichSelectBooleanCheckbox oficialUIC) {
        this.oficialUIC = oficialUIC;
    }

    public RichSelectBooleanCheckbox getAbogadoUIC() {
        return abogadoUIC;
    }

    public void setAbogadoUIC(RichSelectBooleanCheckbox abogadoUIC) {
        this.abogadoUIC = abogadoUIC;
    }

    public RichSelectBooleanCheckbox getAnalistaUIC() {
        return analistaUIC;
    }

    public void setAnalistaUIC(RichSelectBooleanCheckbox analistaUIC) {
        this.analistaUIC = analistaUIC;
    }

    public RichPanelHeader getPanelNoObjecionesUIC() {
        return panelNoObjecionesUIC;
    }

    public void setPanelNoObjecionesUIC(RichPanelHeader panelNoObjecionesUIC) {
        this.panelNoObjecionesUIC = panelNoObjecionesUIC;
    }

    public void setContratadoTableUIC(RichTable contratadoTableUIC) {
        this.contratadoTableUIC = contratadoTableUIC;
    }

    public RichTable getContratadoTableUIC() {
        return contratadoTableUIC;
    }

    public void setAdjudicatarioTableUIC(RichTable adjudicatarioTableUIC) {
        this.adjudicatarioTableUIC = adjudicatarioTableUIC;
    }

    public RichTable getAdjudicatarioTableUIC() {
        return adjudicatarioTableUIC;
    }
    public void recargaConcursantes(Long idObjecion){
        Long resulta=null;
            logger.warning("Objecion obtenida "+idObjecion);
            logger.warning("Entra a consultar objecion");
                Map respuesta=new HashMap();
                BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
                OperationBinding operationBindingIdOp = null;
                operationBindingIdOp = bindingsIdOp.getOperationBinding("recuperaDatos");
                operationBindingIdOp.getParamsMap().put("idObjecion", idObjecion);
                operationBindingIdOp.execute();
            if(null!=(Long)respuesta.get("claveObjecion")){
                    resulta=(Long)respuesta.get("claveObjecion");
                    logger.warning("objecion obtenida");
                }
        }
    
        public List onSuggestOferente(String string) {
                logger.warning("*Inf, Into method onSuggestOferente");
                ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();
                SelectItem element = null;
                
                BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
                JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("NacionalidadTr");         
                
                 RowSetIterator iter = list.getListRowSetIterator();                  
                 iter.reset();
                 Row row = null;
                    
                logger.warning("*Inf, Num de paises recuperados para los oferentes : "+iter.getAllRowsInRange().length);
                 if(null != iter){            
                    
                    for(int i = 0; i< iter.getAllRowsInRange().length; i++){
                         logger.warning("*Inf, Iterando el row "+i+"...");
                          row = iter.getRowAtRangeIndex(i);
                            if(null != row){
                               String value = (String) row.getAttribute("Descripcion");
                               if(null != string &&  null != value){
                                     logger.warning("*Inf, comparando String "+string+" con elemento "+value+" en la lista");
                                  if(value.toUpperCase().contains(string.toUpperCase())){
                                         logger.warning("*Inf, ok cadena a buscar  "+string
                                                               +" coincide en el inicio con el valor : "+value+" en la lista agregando a respuesta..");
                                       element = new SelectItem(value);
                                       resultItems.add(element);
                                     }
                                 }else{
                                       logger.warning("*Inf, Important! String "+string+" valor en lista "+value);
                                   }
                            }
                        }                
                    
                }else{
                  logger.warning("*Inf, Important! Iterador NULL");
                  element = new SelectItem("");
                  resultItems.add(element);
                }
                 iter.closeRowSetIterator();
              
                return resultItems;
            }
        
        public List onSuggestAdjudicatario(String string) {
                logger.warning("*Inf, Into method onSuggestOferente");
                ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();;
                SelectItem element = null;
                
                BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
                JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("NacionalidadTr1");         
                
                 RowSetIterator iter = list.getListRowSetIterator();                  
                 iter.reset();
                 Row row = null;
                    
                logger.warning("*Inf, Num de paises recuperados para los adjudicatarios : "+iter.getAllRowsInRange().length);
                 if(null != iter){            
                    
                    for(int i = 0; i< iter.getAllRowsInRange().length; i++){
                         logger.warning("*Inf, Iterando el row "+i+"...");
                          row = iter.getRowAtRangeIndex(i);
                            if(null != row){
                               String value = (String) row.getAttribute("Descripcion");
                               if(null != string &&  null != value){
                                     logger.warning("*Inf, comparando String "+string+" con elemento "+value+" en la lista");
                                  if(value.toUpperCase().contains(string.toUpperCase())){
                                         logger.warning("*Inf, ok cadena a buscar  "+string
                                                               +" coincide en el inicio con el valor : "+value+" en la lista agregando a respuesta..");
                                       element = new SelectItem(value);
                                       resultItems.add(element);
                                     }
                                 }else{
                                       logger.warning("*Inf, Important! String "+string+" valor en lista "+value);
                                   }
                            }
                        }                
                    
                }else{
                  logger.warning("*Inf, Important! Iterador NULL");
                  element = new SelectItem("");
                  resultItems.add(element);
                }
                 iter.closeRowSetIterator();
              
                return resultItems;
            }
        
        
        public List onSuggestContratado(String string) {
                logger.warning("*Inf, Into method onSuggestOferente");
                ArrayList<SelectItem> resultItems = new ArrayList<SelectItem>();;
                SelectItem element = null;
                
                BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
                JUCtrlListBinding list = (JUCtrlListBinding) bindings.get("NacionalidadTr2");         
                
                 RowSetIterator iter = list.getListRowSetIterator();                  
                 iter.reset();
                 Row row = null;
                    
                logger.warning("*Inf, Num de paises recuperados para los contratados : "+iter.getAllRowsInRange().length);
                 if(null != iter){            
                    
                    for(int i = 0; i< iter.getAllRowsInRange().length; i++){
                         logger.warning("*Inf, Iterando el row "+i+"...");
                          row = iter.getRowAtRangeIndex(i);
                            if(null != row){
                               String value = (String) row.getAttribute("Descripcion");
                               if(null != string &&  null != value){
                                     logger.warning("*Inf, comparando String "+string+" con elemento "+value+" en la lista");
                                  if(value.toUpperCase().contains(string.toUpperCase())){
                                         logger.warning("*Inf, ok cadena a buscar  "+string
                                                               +" coincide en el inicio con el valor : "+value+" en la lista agregando a respuesta..");
                                       element = new SelectItem(value);
                                       resultItems.add(element);
                                     }
                                 }else{
                                       logger.warning("*Inf, Important! String "+string+" valor en lista "+value);
                                   }
                            }
                        }                
                    
                }else{
                  logger.warning("*Inf, Important! Iterador NULL");
                  element = new SelectItem("");
                  resultItems.add(element);
                }
                 iter.closeRowSetIterator();
              
                return resultItems;
            }

    public void actualizarRegionPadreActionListener(ActionEvent actionEvent) {
        logger.warning("Entra en actualizarRegionPadreActionListener.");
        try {
            logger.warning("Objeto : " + actionEvent.getComponent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent());
            RichRegion region =
                (RichRegion) actionEvent.getComponent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();
            logger.warning("Region component : " + region);
            AdfFacesContext.getCurrentInstance().addPartialTarget(region);
            
        } catch (Exception e) {
            logger.warning("Error en actualizarRegionPadreActionListener.", e);
        }
    }
    
    
    public void descargaPlantillaAviso(FacesContext facesContext, OutputStream outputStream){
        logger.warning("Inicia metodo descargaPlantillaAviso.");            
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        //String pathTest = getPath().toString(); 
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        String pathTest = noObjecionBean.getPathPLantillaNoObjecion();        

        logger.warning("Path de descarga: " + pathTest);
        File filed = new File(pathTest);
        String nombreArchivo = filed.getName();
        String errorMsg = null;
            
        logger.warning("Nombre archivo: " + nombreArchivo);
        try{
            response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo);
            response.setCharacterEncoding("UTF-8");
        }catch(Exception e){
            logger.warning("ERROR al ejecutar response.", e);
            errorMsg = ("Error en la descarga del documento ");
        }
        
        if(null == errorMsg){    
            FileInputStream fis = null;;
            try {
                fis = new FileInputStream(filed);
            } catch (FileNotFoundException e) {
                logger.warning("No se pudo descargar el archivo.", e);
                errorMsg = ("Error no se encontro el formato para la descarga");
            }
            byte[] b = new byte[2048];
            
            if(null == errorMsg){    
                
                try{
                    int n;
                    while ((n = fis.available()) > 0){
                        b = new byte[n];
                        int result = fis.read(b);
                        outputStream.write(b, 0, b.length);
                        
                        if (result == -1)
                            break;

                    }
                }catch(Exception e){
                    logger.warning("No se pudo realizar la descarga del archivo.", e);
                    errorMsg = ("Error no se encontro el formato para la descarga");
                }
                
                if(null == errorMsg){
                    try {
                        outputStream.flush();
                        fis.close();
                        outputStream.close();
                    } catch (Exception e) {
                        logger.warning("Error: ", e);
                        errorMsg = ("Error no se encontro el formato para la descarga");
                    }
                }
            }
        }    
        facesContext.responseComplete();
        logger.warning("Termina metodo descargaPlantillaAviso");
    }

    private String extencionExcel = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    
    private BlobDomain newCalendarioBlodDomain(InputStream in) throws SQLException, IOException {
        logger.log(ADFLogger.WARNING, "Inside newCalendarioBlodDomain");
        BlobDomain loBlob = new BlobDomain();
        OutputStream out = loBlob.getBinaryOutputStream();
        writeInputStreamToWriter(in, out);
        in.close();
        out.close();
        return loBlob;
    }
    
    private static void writeInputStreamToWriter(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[8192];
        int charsRead = 0;
        while ((charsRead = in.read(buffer, 0, 8192)) != -1) {
            out.write(buffer, 0, charsRead);
        }
    }
    
    public String getPathPlantilla(String tipoPlantilla) {
        logger.log(ADFLogger.WARNING, " --- Inside getFormatoInteresPath");
      
        String path = "";
        
        try{
               BindingContainer binding = ADFUtils.getBindingContainer();
               OperationBinding operationBinding = binding.getOperationBinding("getValorWsdl");
               operationBinding.getParamsMap().put("llave", tipoPlantilla);
               operationBinding.execute();
                  
                if (operationBinding.getResult() != null) {
                    path = (String) operationBinding.getResult();
                    logger.warning("PATH_PLANTILLA:" + path);
                    if (!operationBinding.getErrors().isEmpty()) {
                        logger.log(ADFLogger.ERROR,
                                   "Error al Retornar getFormatoInteresPath " + operationBinding.getErrors());
                    }
                }
              
        }catch(Exception e){
           logger.warning("Error al validarArchivoExcel  ",e);
        }
        
       return path;
    }
    
    
    public void accionCargarPlantilla(Integer tipoNoObjecion){
      logger.warning("Into metodo accionCargarPlantilla");
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
        
        final int AVISO = 2;
        final int INFORME_RESULTADOS = 7;
        final int CONTRATO = 9;
     
        getInputFilePantillaBinding().resetValue();
        noObjecionBean.setBtnDescargarPlantillaDisabled("false");
     
        if(tipoNoObjecion != null){
            int tipoPlantilla = tipoNoObjecion.intValue();
            
            switch(tipoPlantilla){
                case AVISO: 
                logger.warning("cargar plantilla para aviso");
                noObjecionBean.setFileNamePlantilla("AVISO");
                noObjecionBean.setBtnDescargarPlantillaDisabled("true");
                noObjecionBean.setPathPLantillaNoObjecion(getPathPlantilla("PLANTILLA_AVISO"));
                break;
                case INFORME_RESULTADOS:
                logger.warning("cargar plantilla para informe de resultados");
                noObjecionBean.setFileNamePlantilla("INFORME_RESULTADOS");
                //noObjecionBean.setBtnDescargarPlantillaDisabled(Boolean.FALSE);
                noObjecionBean.setPathPLantillaNoObjecion(getPathPlantilla("PLANTILLA_INFORME_RESULTADOS"));
                break;
                case CONTRATO: 
                logger.warning("cargar plantilla para contrato");
                noObjecionBean.setFileNamePlantilla("CONTRATO");
                noObjecionBean.setBtnDescargarPlantillaDisabled("true");               
                noObjecionBean.setPathPLantillaNoObjecion(getPathPlantilla("PLANTILLA_CONTRATO"));
                break;
            }
             
           
        }else{
            logger.warning("*Inf, Importante!,  No se recibio el tipo de no objecion");
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getGroupBtnPlantilla());
      
      logger.warning("termina metodo accionCargarPlantilla");        
    }

    public void cargarDatosPlantilla(ValueChangeEvent value) {
        logger.warning("Inicia metodo cargarDatosPlantilla");
        value.getComponent().processUpdates(FacesContext.getCurrentInstance());
        NoObjecionBean noObjecionBean = (NoObjecionBean) JSFUtils.resolveExpression("#{pageFlowScope.noObjecionBean}");
  
        InputStream inputStream = null;
        InputStream in = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        UploadedFile file = (UploadedFile) value.getNewValue();
        BlobDomain fileDataValidar = null;
        BlobDomain fileDataGuardar = null;
        Boolean esValido = null;
        String nombreArchivo = null;
        String extencionArchivo = null;
           
           
            if (file != null && file.getLength() > 0) {
                try {
                    UploadedFile fileVal = (UploadedFile) value.getNewValue();
                    logger.warning("Validamos que es archivo EXCEL");
                    logger.warning("fileVal.getContentType() : "+fileVal.getContentType());
                    
                    if (fileVal.getContentType().equals(extencionExcel)) {
                        
                        nombreArchivo = fileVal.getFilename();
                        extencionArchivo = fileVal.getContentType();
                           
                        inputStream = fileVal.getInputStream();
                        in = fileVal.getInputStream();
                        fileDataValidar = newCalendarioBlodDomain(inputStream);
                        fileDataGuardar = newCalendarioBlodDomain(in);
                        
                        logger.warning("filename: " + fileVal.getFilename());
                        logger.warning("mimeType: " + fileVal.getContentType());
                        
                        esValido = validarArchivo(fileDataValidar, noObjecionBean.getFileNamePlantilla());
  
                        logger.warning("EsValido: " + esValido);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelAdjudicatariosUI());
                        
                        
                    } else {
                        JSFUtils.addFacesErrorMessage("Se requiere un tipo de documento Excel (xlsx)");
                        logger.warning("Se requiere un tipo de documento Excel");
                        ResetUtils.reset(value.getComponent());
                    }
                } catch (Exception e) {
                    logger.warning("Ocurrio un error al adjuntar el archivo.", e);
                    JSFUtils.addFacesErrorMessage("Ocurrio un error al Leer el archivo.");
                    ResetUtils.reset(value.getComponent());
                }
            } else {
                logger.log(ADFLogger.WARNING, "ERROR NO HAY DOCUMENTO QUE GUARDAR");
                JSFUtils.addFacesErrorMessage("ERROR NO HAY DOCUMENTO QUE GUARDAR");
                ResetUtils.reset(value.getComponent());
            }
            
       
        logger.warning("Termina metodo cargarDatosPlantilla");
    }



    
    public Boolean validarArchivo(BlobDomain fileDataValidar, String tipoPlantilla){
    logger.warning("*Inf, inicia metodo validarArchivo");    
     Boolean esValido = Boolean.FALSE;
     Map datosOperaciones = new HashMap();   
        
         Long idNoObjecion = (Long) JSFUtils.resolveExpression("#{bindings.Id.inputValue}");
 
            datosOperaciones.put("tipoPlantilla", tipoPlantilla);
            datosOperaciones.put("idNoObjecion", idNoObjecion);
    
           // adjudicarOferente// #{bindings.IdOferente}
     
        try{
               BindingContainer binding = ADFUtils.getBindingContainer();
               OperationBinding operBinding = binding.getOperationBinding("validarArchivoExcel");
               operBinding.getParamsMap().put("fileData", fileDataValidar);
               operBinding.getParamsMap().put("datosOperaciones", datosOperaciones);
               operBinding.execute();
                  
            if(!operBinding.getErrors().isEmpty()){
               logger.warning("OperationBinding validarArchivoExcel devuelve errores" +operBinding.getErrors().toString());
               JSFUtils.addFacesErrorMessage("Error, "+operBinding.getErrors().toString());  
            }
              
        }catch(Exception e){
           logger.warning("Error al validarArchivoExcel  ",e);
        }
        
     logger.warning("*Inf, Termina metodo validarArchivo");     
     return esValido;
    }

    public void setBtnDescargarPlantillaBinding(RichCommandButton btnDescargarPlantillaBinding) {
        this.btnDescargarPlantillaBinding = btnDescargarPlantillaBinding;
    }

    public RichCommandButton getBtnDescargarPlantillaBinding() {
        return btnDescargarPlantillaBinding;
    }

    public void setGroupBtnPlantilla(RichPanelGroupLayout groupBtnPlantilla) {
        this.groupBtnPlantilla = groupBtnPlantilla;
    }

    public RichPanelGroupLayout getGroupBtnPlantilla() {
        return groupBtnPlantilla;
    }

    public void setInputFilePantillaBinding(RichInputFile inputFilePantillaBinding) {
        this.inputFilePantillaBinding = inputFilePantillaBinding;
    }

    public RichInputFile getInputFilePantillaBinding() {
        return inputFilePantillaBinding;
    }
}
