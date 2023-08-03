package org.bcie.fenix.view.adquisiciones;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.el.ELContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichListView;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import oracle.jbo.domain.BlobDomain;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import org.apache.myfaces.trinidad.model.RowKeySet;

import org.apache.myfaces.trinidad.model.RowKeySetImpl;

import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

import javax.faces.component.UIViewRoot;

import org.bcie.fenix.common.FenixConstants;

public class AdquisicionesActionBean extends FenixActionBean {

    public static ADFLogger logger = null;
    private RichPanelFormLayout pflAdquisicionFormUIC;
    private RichTable pflAdquisicionTableUIC;
    private RichPopup popupAgregarEvidenciasAdquisicion;
    private RichRegion noObjecionUI;
    private RichPopup popupEliminarEvidencia;
    private String docpop;
    private RichPopup popupGuardarAdquisicion;
    private RichPopup popupCancelarAdquisicion;
    private RichPopup popupEliminar;
    private RichPopup popupCancelarModificarAdquisicion;
    private RichInputDate fechaContratoRID;
    private RichListView listViewEvidencias;
    private RichTable tableEvidencias;
    private Boolean esSelectionEventQueue = Boolean.FALSE;
    private RowKeySet disclosedRowKeysTable; // Guarda el elemento seleccionado en la tabla
    private Long keyId;
    private Boolean adquisicionesPrevias;
    private RichRegion rpNoObjecionesUIC;
    private RichPanelFormLayout formDatos1UIC;
    private RichPanelFormLayout formDatos2UIC;
    private RichPanelFormLayout formDatos3UIC;
    private RichPanelFormLayout formDatos4UIC;
    private RichPanelFormLayout formEvidencias;
    private RichPanelFormLayout formDatosTitulo;
    
    private Boolean faltanCampos;
    private RichInputText objetivoUIC;
    
    private static final Integer idTareaBpmGestorOperaciones = 65;
    private static final Integer idTareaBpmAdquisiciones = 198;
    private RichInputText montoPUIC;
    private RichInputText montoCUIC;
    private RichInputText montoAUIC;
    private RichSelectOneChoice tipoAdUIC;
    private RichSelectOneChoice modalidadUIC;
    private RichSelectOneChoice normativaApUIC;
    private RichInputText especificarUIC;
    private RichInputText numeroAdUIC;
    private RichSelectOneChoice alcanceUIC;
    private RichInputText adjudicatarioUIC;
    private RichInputText nacionalidadAdjUIC;
    private RichInputText tituloAdUIC;
    
    private long startTime;
    private long endTime;
    private Integer conteo;
    public void setAdquisicionesPrevias(Boolean adquisicionesPrevias) {
        this.adquisicionesPrevias = adquisicionesPrevias;
    }

    public Boolean getAdquisicionesPrevias() {
        AttributeBinding claveIdObjecion;
        claveIdObjecion = ADFUtils.findControlBinding("IdTcaTipoNormativaAplicada");
        if(null!=claveIdObjecion.getInputValue()){
                if(((Integer)claveIdObjecion.getInputValue()).compareTo(7)==0){
                       adquisicionesPrevias=Boolean.FALSE;
                    }
                else{
                        if(((Integer)claveIdObjecion.getInputValue()).compareTo(8)==0){
                                adquisicionesPrevias=Boolean.FALSE;
                            }
                        else{
                                adquisicionesPrevias=Boolean.TRUE;
                            }
                    }
            }
        else{
                adquisicionesPrevias=Boolean.TRUE;
            }
        return adquisicionesPrevias;
    }

    public AdquisicionesActionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(AdquisicionesActionBean.class);
        }
        
        if (null == disclosedRowKeysTable) {
            disclosedRowKeysTable = new RowKeySetImpl();
        }
    }

    public void setPflAdquisicionFormUIC(RichPanelFormLayout pflAdquisicionFormUIC) {
        this.pflAdquisicionFormUIC = pflAdquisicionFormUIC;
    }

    public RichPanelFormLayout getPflAdquisicionFormUIC() {
        return pflAdquisicionFormUIC;
    }

    @SuppressWarnings("unchecked")
    public void seleccionarAdquisicion(SelectionEvent selectionEvent) {
        logger.warning("Entra a seleccionarAdquisicion");
        
        AdquisicionesBean adquisicionBean = (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        AttributeBinding idAdquisicionAttribute = ADFUtils.findControlBinding("Id");;
        Boolean noObjecionActiva = null;
        boolean isDirty = ADFUtils.getDCBindingContainer().getDataControl().isTransactionDirty();
        
        if (JSFUtils.resolveExpression("#{sessionScope.noObjecion}") != null) {
            noObjecionActiva = (Boolean) (JSFUtils.resolveExpression("#{sessionScope.noObjecion}"));
        }
        
        logger.warning("noObjecionActiva: " + noObjecionActiva);
        logger.warning("disableFormulario: " + adquisicionBean.getDisableFormulario());
        
        if((noObjecionActiva == true && adquisicionBean.getDisableFormulario() == true) ||
           (noObjecionActiva == null && adquisicionBean.getDisableFormulario() == true)) {
            //logger.warning("Ingresa al if - isDirty");
            //JSFUtils.resolveExpression("#{bindings.AdquisicionesVO.collectionModel.makeCurrent}");
            //
            //AdfFacesContext.getCurrentInstance().addPartialTarget(noObjecionUI);
            
            invokeTable("#{bindings.AdquisicionesVO.collectionModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                     selectionEvent });
            // get the selected row , by this you can get any attribute of that row
            Row selectedRow = (Row) evaluateTable("#{bindings.AdquisicionesVOIterator.currentRow}");
            //FacesMessage msg = new FacesMessage(selectedRow.getAttribute("NumeroAdquisicion").toString());
            //msg.setSeverity(FacesMessage.SEVERITY_INFO);
            //FacesContext.getCurrentInstance().addMessage(null, msg);

            // la selección actual del tree, para restaurarla en caso de un isDirty
            try {
                keyId = (Long)selectedRow.getAttribute("Id");
                JSFUtils.setExpressionValue("#{sessionScope.idAdquisicion}", keyId); 
                adquisicionBean.setIdAdquiscionPrincipal(keyId);
                logger.warning(">>>>>>>>>>>>>>> Id adquisicion seleccionado: " + selectedRow.getAttribute("Id"));
                List rowKeyList = new ArrayList();
                Key jboKey = new Key(new Object[] { selectedRow.getAttribute("Id") });
                rowKeyList.add(jboKey);
                RowKeySet rksNew = new RowKeySetImpl();
                rksNew.add(rowKeyList);
                setDisclosedRowKeysTable(rksNew);
                setEsSelectionEventQueue(false); // limpiamos flag de control de entrada infinita al Selectionlistener
                //idAdquisicionAttribute.setInputValue(keyId);
                adquisicionBean.setIdAdquisiones(keyId);
                adquisicionBean.setIdAdquisicionAux(keyId);
                //AdfFacesContext.getCurrentInstance().addPartialTarget(rpNoObjecionesUIC);
                //rpNoObjecionesUIC.getRegionRemoteRefreshListeners();
                rpNoObjecionesUIC.refresh(FacesContext.getCurrentInstance());
            } catch(Exception e) {
                logger.severe("No es posible obtener la fila seleccionada", e);
            }
            actualizarEvidenciasAdquisicion();
        }else{
            logger.warning("Entra al else - restaurarSeleccionAnterior");
            restaurarSeleccionAnterior();
        }
        logger.warning("Finaliza a seleccionarAdquisicion");
    }
    
    /**
     * En caso de una selección y existan cambios pendientes por guardar/deshacer (isDirty), 
     * restauramos el current row al elemento que se estaba editando (elegido anteriormente y guardado en MatrizTccBean)
     * 
     * @see Frank Nimphius response - https://community.oracle.com/thread/2480829 
     */
    private void restaurarSeleccionAnterior() {
        RichTable adquisicionTable = null;
        AttributeBinding idAdquisicionAttribute = null;
        idAdquisicionAttribute = ADFUtils.findControlBinding("Id");
        
        if(!getEsSelectionEventQueue()) {
            adquisicionTable = getPflAdquisicionTableUIC();
            //getPflAdquisicionTableUIC().getSelectedRowKeys().clear();
            //AdfFacesContext.getCurrentInstance().addPartialTarget(pflAdquisicionTableUIC);
            
            // 2. Regresamos el current row del tree a su estado anterior. Para ello, creamos un nuevo evento de 
            // selección y lo encolamos al tree indicado. Además, reasignamos el selected row en pantalla.         
            SelectionEvent selectionEvent = new SelectionEvent(new RowKeySetImpl(), getDisclosedRowKeysTable(), adquisicionTable);
            selectionEvent.queue(); //queue event on component, es decir, llama de nuevo al Selectionlistener
            adquisicionTable.setSelectedRowKeys(getDisclosedRowKeysTable()); // Reasigna (pinta) el selected row en pantalla
            
            // 3. Asigna flag de control 
            setEsSelectionEventQueue(true); // Con este flag evitamos que entre repetidamente al Selectionlistener para el mismo evento

            // 1. Mostramos mensaje informando que hay cambios pendientes    
            JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
            Row selectedRow = (Row) evaluateTable("#{bindings.AdquisicionesVOIterator.currentRow}");
            
            keyId = (Long)selectedRow.getAttribute("Id");
            idAdquisicionAttribute.setInputValue(keyId);
            if (null != keyId) {
                DCIteratorBinding dcItteratorBindings = ADFUtils.findIterator("AdquisicionesVOIterator");
                RowSetIterator rowSetIterator = dcItteratorBindings.getRowSetIterator();
                Key key = new Key(new oracle.jbo.domain.Number[] { new oracle.jbo.domain.Number(keyId) });
                Row[] row = rowSetIterator.findByKey(key, 1);
                rowSetIterator.setCurrentRow(row[0]);
            }
            
            if (null != selectedRow) {
                logger.warning("invokeTable.selectedRow:"+selectedRow.getAttribute("Id"));
            }
            
            invokeTable("#{bindings.AdquisicionesVO.collectionModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                     selectionEvent });
            Long adquisicionActiva = null;
            try {
                if (JSFUtils.resolveExpression("#{sessionScope.idAdquisicion}") != null) {
                    adquisicionActiva = Long.parseLong(JSFUtils.resolveExpression("#{sessionScope.idAdquisicion}").toString());
                }
                logger.warning("valor activo de la adquisicion " + adquisicionActiva);
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.warning("idAdquisicion no obtenida");
                }
            if(null!=adquisicionActiva){
                changeAdquisicionesCurrentRow(adquisicionActiva);
                //elegirAdquisicion(adquisicionActiva);
                //JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(adquisicionTable);
        }
       // JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
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


    public void agregarActionListener(ActionEvent actionEvent) {
        logger.warning("Entra a agregarActionListener");

        AdquisicionesBean adquisicionBean =
            (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        Boolean noObjecionActiva = Boolean.TRUE;

        if (JSFUtils.resolveExpression("#{sessionScope.noObjecion}") != null) {
            noObjecionActiva = (Boolean) (JSFUtils.resolveExpression("#{sessionScope.noObjecion}"));
        }

        logger.warning("noObjecionActiva: " + noObjecionActiva);
        if (noObjecionActiva) {
            logger.warning("Se pueden hacer cambios a adquisiciones");
            adquisicionBean.setEdita2agrega1(1);
            adquisicionBean.setAgregarEvidencia(Boolean.TRUE);
            adquisicionBean.setDisableFormulario(Boolean.FALSE);
            JSFUtils.setExpressionValue("#{sessionScope.adquisicionForm}", adquisicionBean.getDisableFormulario());
            adquisicionBean.setDisableBotones(Boolean.TRUE);
            adquisicionBean.setDisableBotonesAcciones(Boolean.FALSE);
            adquisicionBean.setAgregarAdquisicion(Boolean.TRUE);
            adquisicionBean.setListaHabilitada(Boolean.FALSE);

            BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
            OperationBinding operationBindingIdOp = null;
            //metodo para obtener si el produto es 'Linea global de credito'
            operationBindingIdOp = bindingsIdOp.getOperationBinding("agregarNuevaRow");


            logger.warning("ID operacion : " + adquisicionBean.getIdOperacion());
            operationBindingIdOp.getParamsMap().put("idOperacion", Long.valueOf(adquisicionBean.getIdOperacion()));
            Long idAdquisicionLong = (Long) operationBindingIdOp.execute();

            if (null != idAdquisicionLong) {
                keyId = idAdquisicionLong;
                adquisicionBean.setIdAdquiscionPrincipal(idAdquisicionLong);
                adquisicionBean.setIdAdquisicionAux(idAdquisicionLong);
            }

            Long adquisionObtenida = null;
            adquisionObtenida = adquisicionBean.getIdAdquisicionAux();
            if (null != adquisionObtenida) {
                adquisicionBean.setIdAdquiscionPrincipal(adquisionObtenida);
                JSFUtils.setExpressionValue("#{sessionScope.idAdquisicion}", adquisionObtenida);
            }
            logger.warning("Entra adquisicion" + adquisionObtenida);

            actualizarEvidenciasAdquisicion();

            adquisicionBean.setIdAdquisiones(idAdquisicionLong);

            AdfFacesContext.getCurrentInstance().addPartialTarget(getPflAdquisicionTableUIC());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getTableEvidencias());
            AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatosTitulo());
            rpNoObjecionesUIC.refresh(FacesContext.getCurrentInstance());
            limpiaCampos();
            //AdfFacesContext.getCurrentInstance().addPartialTarget(getPflAdquisicionFormUIC());
        } else {
            JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
        }


    }

    public void modificarActionListener(ActionEvent actionEvent) {
        logger.warning("Entra a modificarActionListener");
        Boolean noObjecionActiva = Boolean.TRUE;

        if (JSFUtils.resolveExpression("#{sessionScope.noObjecion}") != null) {
            noObjecionActiva = (Boolean) (JSFUtils.resolveExpression("#{sessionScope.noObjecion}"));
        }

        logger.warning("noObjecionActiva: " + noObjecionActiva);
        if (noObjecionActiva) {
            logger.warning("Se pueden hacer cambios a adquisiciones");
            boolean isDirty = ADFUtils.getDCBindingContainer().getDataControl().isTransactionDirty();

            if (!isDirty) {

                AdquisicionesBean adquisicionBean =
                    (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");

                Long adquisionObtenida = null;
                adquisionObtenida = adquisicionBean.getIdAdquisicionAux();
                if (null != adquisionObtenida) {
                    adquisicionBean.setIdAdquiscionPrincipal(adquisionObtenida);
                    JSFUtils.setExpressionValue("#{sessionScope.idAdquisicion}", adquisionObtenida);
                }
                logger.warning("Entra adquisicion" + adquisionObtenida);

                adquisicionBean.setEdita2agrega1(2);
                adquisicionBean.setDisableFormulario(Boolean.FALSE);
                JSFUtils.setExpressionValue("#{sessionScope.adquisicionForm}", adquisicionBean.getDisableFormulario());
                adquisicionBean.setDisableBotones(Boolean.TRUE);
                adquisicionBean.setDisableBotonesAcciones(Boolean.FALSE);
                adquisicionBean.setAgregarAdquisicion(Boolean.TRUE);
                adquisicionBean.setModificarAdquisicion(Boolean.TRUE);
                adquisicionBean.setAgregarEvidencia(Boolean.TRUE);
                adquisicionBean.setListaHabilitada(Boolean.FALSE);
                limpiaCampos();

            } else {
                JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
            }
        } else {
            JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
        }
    }
    
    public String cancelarAccion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        logger.warning("entra a cancelarAccion");
        
        AdquisicionesBean adquisicionBean =
                    (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        
        Long adquisionObtenida=null;
        adquisionObtenida=adquisicionBean.getIdAdquisicionAux();
        if(null!=adquisionObtenida){
                adquisicionBean.setIdAdquiscionPrincipal(adquisionObtenida);
                JSFUtils.setExpressionValue("#{sessionScope.idAdquisicion}", adquisionObtenida); 
            }
        logger.warning("Entra adquisicion" + adquisionObtenida);
        
        adquisicionBean.setDisableFormulario(Boolean.TRUE);
        JSFUtils.setExpressionValue("#{sessionScope.adquisicionForm}", adquisicionBean.getDisableFormulario());
        adquisicionBean.setDisableBotones(Boolean.FALSE);
        adquisicionBean.setDisableBotonesAcciones(Boolean.TRUE);
        adquisicionBean.setAgregarAdquisicion(Boolean.FALSE);
        adquisicionBean.setAgregarEvidencia(Boolean.FALSE);
        adquisicionBean.setListaHabilitada(Boolean.TRUE);
        // aplica metodo de revertir cambios
        Boolean cancelaAdquisicion=aplicaRollBack();
        logger.warning("Cancela la adquiscion "+ cancelaAdquisicion);
        // se mantiene el id de adquisicion
        if(adquisicionBean.getEdita2agrega1().compareTo(2)==0){
                logger.warning("se mantiene el id de adquiscion al editar");
                changeAdquisicionesCurrentRow(adquisicionBean.getIdAdquiscionPrincipal());
            }
        // se limpia metodo de idAdquiscion
        else{
                logger.warning("se quita el id de adquiscion al agregar");
                AttributeBinding idAdquisicionAttribute = null;
                        idAdquisicionAttribute = ADFUtils.findControlBinding("Id");
                adquisicionBean.setIdAdquiscionPrincipal((Long)idAdquisicionAttribute.getInputValue());
                logger.warning("id de adquisicionactual: "+ (Long)idAdquisicionAttribute.getInputValue());
            }
        
        adquisicionBean.setModificarAdquisicion(Boolean.TRUE);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPflAdquisicionTableUIC());
        actualizarEvidenciasAdquisicion();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTableEvidencias());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPflAdquisicionFormUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatos1UIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatos2UIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatos3UIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatos4UIC());
        
        Long adquisionRecuperada=null;
        adquisicionBean.setIdAdquisicionAux(recuperarCurrentRowAdquisicion());
        adquisionRecuperada=adquisicionBean.getIdAdquisicionAux();
        if(null!=adquisionRecuperada){
                adquisicionBean.setIdAdquiscionPrincipal(adquisionRecuperada);
                JSFUtils.setExpressionValue("#{sessionScope.idAdquisicion}", adquisionRecuperada);
            }
        logger.warning("Id adquisicion cancelado: " + adquisionObtenida);
        logger.warning("Id adquisicion recuperado: " + adquisionRecuperada);
        
        popupCancelarModificarAdquisicion.hide();
               
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPflAdquisicionTableUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTableEvidencias());
        rpNoObjecionesUIC.refresh(FacesContext.getCurrentInstance());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getRpNoObjecionesUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatosTitulo());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getObjetivoUIC());
        
        objetivoUIC.setValue(null);
        objetivoUIC.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getObjetivoUIC());
        
        limpiaCampos();
        
        return null;
    }
    
    protected void refreshPage() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        String page = fctx.getViewRoot().getViewId();
        ViewHandler ViewH = fctx.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fctx, page);
        UIV.setViewId(page);
        fctx.setViewRoot(UIV);
    }

    public void eliminarAdquisicion(ActionEvent actionEvent) {
        logger.warning("Entra a eliminarAdquisicion");
        
        AdquisicionesBean adquisicionBean =
            (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        
        Long adquisionObtenida=null;
        adquisionObtenida=adquisicionBean.getIdAdquisicionAux();
        if(null!=adquisionObtenida){
                adquisicionBean.setIdAdquiscionPrincipal(adquisionObtenida);
                JSFUtils.setExpressionValue("#{sessionScope.idAdquisicion}", adquisionObtenida); 
            }
        logger.warning("Entra adquisicion" + adquisionObtenida);
        
        BindingContainer binding = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = binding.getOperationBinding("cambiarAdquisicionABanEstatusCero");
        operationBinding.execute();
        
        Long adquisionRecuperada=null;
        adquisicionBean.setIdAdquisicionAux(recuperarCurrentRowAdquisicion());
        adquisionRecuperada=adquisicionBean.getIdAdquisicionAux();
        if(null!=adquisionRecuperada){
                adquisicionBean.setIdAdquiscionPrincipal(adquisionRecuperada);
                JSFUtils.setExpressionValue("#{sessionScope.idAdquisicion}", adquisionRecuperada); 
            }
        logger.warning("Id adquisicion elimindado: " + adquisionObtenida);
        logger.warning("Id adquisicion recuperado: " + adquisionRecuperada);
        
        actualizarEvidenciasAdquisicion();
        
        popupEliminar.hide();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTableEvidencias());
        rpNoObjecionesUIC.refresh(FacesContext.getCurrentInstance());
        limpiaCampos();
        
    }

    public void eliminarAdquisicionPopup(ActionEvent actionEvent) {
        Boolean noObjecionActiva = Boolean.TRUE;

        if (JSFUtils.resolveExpression("#{sessionScope.noObjecion}") != null) {
            noObjecionActiva = (Boolean) (JSFUtils.resolveExpression("#{sessionScope.noObjecion}"));
        }

        logger.warning("noObjecionActiva: " + noObjecionActiva);
        if (noObjecionActiva) {
            logger.warning("Se pueden hacer cambios a adquisiciones");
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupEliminar().show(popupHints);
        } else {
            JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
        }

    }

    public void guardarAdquisicion() {
        logger.warning("Entra a guardarAdquisicion");
        
        String idOperacion =
            (ADFUtils.getBoundAttributeValue("IdOperacion") == null) ? null :
            ADFUtils.getBoundAttributeValue("IdOperacion").toString();

        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        operationBindingIdOp = bindingsIdOp.getOperationBinding("setvarIdOperacion");
        operationBindingIdOp.getParamsMap().put("value", idOperacion);
        operationBindingIdOp.execute();

        //Obtiene valores del formulario
        String id =
            (ADFUtils.getBoundAttributeValue("Id") == null) ? null : ADFUtils.getBoundAttributeValue("Id").toString();



        String cuentaConAdquisicionPrevia =
            (ADFUtils.getBoundAttributeValue("CuentaConAdquisicionPrevia") == null) ? null :
            ADFUtils.getBoundAttributeValue("CuentaConAdquisicionPrevia").toString();

        Date fechaContrato = (Date)getFechaContratoRID().getValue();
                
//        String fechaContrato =
//            (ADFUtils.getBoundAttributeValue("FechaContrato") == null) ? null :
//            ADFUtils.getBoundAttributeValue("FechaContrato").toString();

        String fechaRegistro =
            (ADFUtils.getBoundAttributeValue("FechaRegistro") == null) ? null :
            ADFUtils.getBoundAttributeValue("FechaRegistro").toString();

        String idTcaTipoAdquisicion =
            (ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion") == null) ? null :
            ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion").toString();

        String idTcaTipoAlcanceProceso =
            (ADFUtils.getBoundAttributeValue("IdTcaTipoAlcanceProceso") == null) ? null :
            ADFUtils.getBoundAttributeValue("IdTcaTipoAlcanceProceso").toString();

        String idTcaTipoModalidadProceso =
            (ADFUtils.getBoundAttributeValue("IdTcaTipoModalidadProceso") == null) ? null :
            ADFUtils.getBoundAttributeValue("IdTcaTipoModalidadProceso").toString();

        String idTcaTipoMonedaAsociado =
            (ADFUtils.getBoundAttributeValue("IdTcaTipoMonedaAsociado") == null) ? null :
            ADFUtils.getBoundAttributeValue("IdTcaTipoMonedaAsociado").toString();

        String idTcaTipoMonedaContratado =
            (ADFUtils.getBoundAttributeValue("IdTcaTipoMonedaContratado") == null) ? null :
            ADFUtils.getBoundAttributeValue("IdTcaTipoMonedaContratado").toString();

        String idTcaTipoMonedaPresupuesto =
            (ADFUtils.getBoundAttributeValue("IdTcaTipoMonedaPresupuesto") == null) ? null :
            ADFUtils.getBoundAttributeValue("IdTcaTipoMonedaPresupuesto").toString();

        String idTcaTipoNormativaAplicada =
            (ADFUtils.getBoundAttributeValue("IdTcaTipoNormativaAplicada") == null) ? null :
            ADFUtils.getBoundAttributeValue("IdTcaTipoNormativaAplicada").toString();

        String montoAsociadoString =
            (ADFUtils.getBoundAttributeValue("MontoAsociado") == null) ? null :
            ADFUtils.getBoundAttributeValue("MontoAsociado").toString();
        BigDecimal montoAsociado = new BigDecimal(0);
        if (montoAsociadoString != null) {
            montoAsociado = new BigDecimal(montoAsociadoString);
        }

        String montoContratadoString =
            (ADFUtils.getBoundAttributeValue("MontoContratado") == null) ? null :
            ADFUtils.getBoundAttributeValue("MontoContratado").toString();
        BigDecimal montoContratado = new BigDecimal(0);
        if (montoContratadoString != null) {
            montoContratado = new BigDecimal(montoContratadoString);
        }

        String montoPresupuestadoString =
            (ADFUtils.getBoundAttributeValue("MontoPresupuestado") == null) ? null :
            ADFUtils.getBoundAttributeValue("MontoPresupuestado").toString();
        BigDecimal montoPresupuestado = new BigDecimal(0);
        if (montoPresupuestadoString != null) {
            montoPresupuestado = new BigDecimal(montoPresupuestadoString);
        }


        String nacionalidadAdjudicatario =
            (ADFUtils.getBoundAttributeValue("NacionalidadAdjudicatario") == null) ? null :
            ADFUtils.getBoundAttributeValue("NacionalidadAdjudicatario").toString();

        String nombreAdjudicatario =
            (ADFUtils.getBoundAttributeValue("NombreAdjudicatario") == null) ? null :
            ADFUtils.getBoundAttributeValue("NombreAdjudicatario").toString();

        String normativaEspecifica =
            (ADFUtils.getBoundAttributeValue("NormativaEspecifica") == null) ? null :
            ADFUtils.getBoundAttributeValue("NormativaEspecifica").toString();

        String numeroAdquisicion =
            (ADFUtils.getBoundAttributeValue("NumeroAdquisicion") == null) ? null :
            ADFUtils.getBoundAttributeValue("NumeroAdquisicion").toString();

        String objetivoAdquisicion =
            (ADFUtils.getBoundAttributeValue("ObjetivoAdquisicion") == null) ? null :
            ADFUtils.getBoundAttributeValue("ObjetivoAdquisicion").toString();

        String tituloAdquisicion =
            (ADFUtils.getBoundAttributeValue("TituloAdquisicion") == null) ? null :
            ADFUtils.getBoundAttributeValue("TituloAdquisicion").toString();
        

        logger.warning("idOperacion: " + idOperacion);
        logger.warning("cuentaConAdquisicionPrevia: " + cuentaConAdquisicionPrevia);
        logger.warning("idTcaTipoAdquisicion: " + idTcaTipoAdquisicion);
        logger.warning("idTcaTipoAlcanceProceso: " + idTcaTipoAlcanceProceso);
        logger.warning("idTcaTipoModalidadProceso: " + idTcaTipoModalidadProceso);
        logger.warning("idTcaTipoMonedaAsociado: " + idTcaTipoMonedaAsociado);
        logger.warning("idTcaTipoMonedaContratado: " + idTcaTipoMonedaContratado);
        logger.warning("idTcaTipoMonedaPresupuesto: " + idTcaTipoMonedaPresupuesto);
        logger.warning("idTcaTipoNormativaAplicada: " + idTcaTipoNormativaAplicada);
        logger.warning("montoAsociado: " + montoAsociado);
        logger.warning("montoContratado" + montoContratado);
        logger.warning("montoPresupuestado: " + montoPresupuestado);
        logger.warning("nacionalidadAdjudicatario: " + nacionalidadAdjudicatario);
        logger.warning("nombreAdjudicatario: " + nombreAdjudicatario);
        logger.warning("normativaEspecifica: " + normativaEspecifica);
        logger.warning("numeroAdquisicion: " + numeroAdquisicion);
        logger.warning("objetivoAdquisicion: " + objetivoAdquisicion);
        logger.warning("tituloAdquisicion: " + tituloAdquisicion);
        logger.warning("fechaContrato: " + fechaContrato);
        //logger.warning("instanciaProceso: " + instanciaProceso);

        //Metodo de VO para guardar/actualizar Adquisicion
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("actualizarAdquisicion");
        operationBinding.getParamsMap().put("id", id == null ? null : Long.valueOf(id));
        operationBinding.getParamsMap().put("idOperacion", Long.valueOf(idOperacion));

        
        operationBinding.getParamsMap().put("cuentaConAdquisicionPrevia",
                                            cuentaConAdquisicionPrevia == null || cuentaConAdquisicionPrevia == "" ?
                                            null : Integer.valueOf(cuentaConAdquisicionPrevia));

        operationBinding.getParamsMap().put("tipoAdquiscion",
                                            idTcaTipoAdquisicion == null || idTcaTipoAdquisicion == "" ? null :
                                            Integer.valueOf(idTcaTipoAdquisicion));

        operationBinding.getParamsMap().put("tipoAlcanceProceso",
                                            idTcaTipoAlcanceProceso == null || idTcaTipoAlcanceProceso == "" ? null :
                                            Integer.valueOf(idTcaTipoAlcanceProceso));

        operationBinding.getParamsMap().put("tipoModalidadProceso",
                                            idTcaTipoModalidadProceso == null || idTcaTipoModalidadProceso == "" ?
                                            null : Integer.valueOf(idTcaTipoModalidadProceso));

        operationBinding.getParamsMap().put("tipoMonedaAsociado",
                                            idTcaTipoMonedaAsociado == null || idTcaTipoMonedaAsociado == "" ? null :
                                            Integer.valueOf(idTcaTipoMonedaAsociado));

        operationBinding.getParamsMap().put("tipoMonedaContratado",
                                            idTcaTipoMonedaContratado == null || idTcaTipoMonedaContratado == "" ?
                                            null : Integer.valueOf(idTcaTipoMonedaContratado));

        operationBinding.getParamsMap().put("tipoMonedaPresupuestado",
                                            idTcaTipoMonedaPresupuesto == null || idTcaTipoMonedaPresupuesto == "" ?
                                            null : Integer.valueOf(idTcaTipoMonedaPresupuesto));

        operationBinding.getParamsMap().put("tipoNotmativaAplicada",
                                            idTcaTipoNormativaAplicada == null || idTcaTipoNormativaAplicada == "" ?
                                            null : Integer.valueOf(idTcaTipoNormativaAplicada));

        operationBinding.getParamsMap().put("montoAsociado", montoAsociado);
        operationBinding.getParamsMap().put("montoContratado", montoContratado);
        operationBinding.getParamsMap().put("montoPresupuestado", montoPresupuestado);
        operationBinding.getParamsMap().put("nacionalidadAdjudicatario", nacionalidadAdjudicatario);
        operationBinding.getParamsMap().put("nombreAdjudicatario", nombreAdjudicatario);
        operationBinding.getParamsMap().put("normativaEspecifica", normativaEspecifica);
        operationBinding.getParamsMap().put("numeroAdquisicion", numeroAdquisicion);
        operationBinding.getParamsMap().put("objetivoAdquisicion", objetivoAdquisicion);
        operationBinding.getParamsMap().put("tituloAdquisicion", tituloAdquisicion);
        operationBinding.getParamsMap().put("fechaContrato", fechaContrato);
        operationBinding.getParamsMap().put("instanciaProceso", null);
        
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            logger.warning("Error al ejecutar actualizarAdquisicion");
                faltanCampos=Boolean.FALSE;
            }
        else{
                AdquisicionesBean adquisicionBean =
                            (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
                adquisicionBean.setDisableFormulario(Boolean.TRUE);
                adquisicionBean.setDisableBotones(Boolean.FALSE);
                adquisicionBean.setDisableBotonesAcciones(Boolean.TRUE);
                adquisicionBean.setAgregarAdquisicion(Boolean.FALSE);
                adquisicionBean.setAgregarEvidencia(Boolean.FALSE);
                
                logger.warning("formulario: " + adquisicionBean.getDisableFormulario());
                JSFUtils.setExpressionValue("#{sessionScope.adquisicionForm}", adquisicionBean.getDisableFormulario());
                logger.warning("botones arriba: " + adquisicionBean.getDisableBotones());
                logger.warning("botones abajo: " + adquisicionBean.getDisableBotonesAcciones());
                logger.warning("Agregar adquisicion: " + adquisicionBean.getAgregarAdquisicion());
            }
        logger.warning("Termina de guardar");
    }


    public void setPflAdquisicionTableUIC(RichTable pflAdquisicionTableUIC) {
        this.pflAdquisicionTableUIC = pflAdquisicionTableUIC;
    }

    public RichTable getPflAdquisicionTableUIC() {
        return pflAdquisicionTableUIC;
    }

    public void cambiaNormativaAplicada(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inside cambiaNormativaAplicada");
        
        /* Se agrega para FNXII-5640*/
        logger.warning("Render response");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        FacesContext.getCurrentInstance().renderResponse();
        AdfFacesContext.getCurrentInstance().addPartialTarget(formDatos2UIC);
        AdfFacesContext.getCurrentInstance().addPartialTarget(formDatos3UIC);
    }

 public void setNoObjecionUI(RichRegion noObjecionUI) {
        this.noObjecionUI = noObjecionUI;
    }

    public RichRegion getNoObjecionUI() {
        return noObjecionUI;
}

    public void abrirPopupBusquedaDocumentoAdquisicion(ActionEvent actionEvent) {
        AdquisicionesBean adquisicionBean =
            (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        Boolean noObjecionActiva = Boolean.TRUE;

        if (JSFUtils.resolveExpression("#{sessionScope.noObjecion}") != null) {
            noObjecionActiva = (Boolean) (JSFUtils.resolveExpression("#{sessionScope.noObjecion}"));
        }

        logger.warning("noObjecionActiva: " + noObjecionActiva);
            if ((noObjecionActiva && adquisicionBean.getTarea().compareTo(FenixConstants.TAREA_GESTOR_OPERACIONES)==0) 
                || adquisicionBean.getTarea().compareTo(FenixConstants.TAREA_GESTOR_OPERACIONES)!=0) {
            logger.warning("Se pueden hacer cambios a adquisiciones");
            Long variable1 = null;

            logger.warning("Llamada al metodo limpiarTabla()...");
            limpiarTabla();

            logger.warning("Limpiando formulario de busqueda...");
            //Limpiar campos del formulario
            ADFUtils.setBoundAttributeValue("Documento", null);
            ADFUtils.setBoundAttributeValue("IdTipoDocumento", null);
            ADFUtils.setBoundAttributeValue("IdProcesoBpm", null);
            ADFUtils.setBoundAttributeValue("FechaIni", null);
            ADFUtils.setBoundAttributeValue("FechaFin", null);
            logger.warning("Listo formulario de busqueda limpio...");

            eligeAdquisicion2();
            try {
                if (JSFUtils.resolveExpression("#{sessionScope.idNoObjecion}") != null) {
                    variable1 = Long.parseLong(JSFUtils.resolveExpression("#{sessionScope.idNoObjecion}").toString());
                }
                logger.warning("valor activo de la adquisicion " + variable1);
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.warning("idAdquisicion no obtenida");
            }

            logger.warning("entra a abrirPopupBusquedaDocumentoAdquisicion");
            adquisicionBean.setIdObjecionUnica(variable1);
            //metodoconsulta();
            // Add event code here...
            cargarRow(); // se crea una fila en la VO programatica para utilizar en el formulario
            // metodo limpia las row de la vo
            limpiarTabla();

            try {
                //Limpiar campos del formulario
                logger.warning("Se comienza a cargar y limpiar los campos del formulario...");
                ADFUtils.setBoundAttributeValue("Documento", null);
                logger.warning("Documento listo.");
                ADFUtils.setBoundAttributeValue("IdTipoDocumento", null);
                logger.warning("IdTipoDocumento listo.");
                ADFUtils.setBoundAttributeValue("IdProcesoBpm", null);
                logger.warning("IdProcesoBpm listo.");
                ADFUtils.setBoundAttributeValue("FechaIni", null);
                logger.warning("FechaIni listo.");
                ADFUtils.setBoundAttributeValue("FechaFin", null);
                logger.warning("FechaIni FechaFin.");
                logger.warning("Listo formulario de busqueda limpio...");
            } catch (Exception e) {
                logger.warning("Error al cargar y limpiar los campos del formulario... " + e.getClass() + ", " +
                               e.getMessage());
            }

            // Abrir popUp busqueda documentos trazabilidad operacion
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupAgregarEvidenciasAdquisicion().show(popupHints);
        } else {
            JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
        }
    }
    
    public void limpiarTabla(){
      // Accedemos y ejecutamlos el metodo limpiarFilas() de DocumentosTrazabilidadOperacionVO
      logger.warning("Accedemos y ejecutamlos el metodo limpiarFilas() de DocumentosTrazabilidadOperacionVO...");
            try{
                logger.warning("Acceso permitido...");
                BindingContainer binding = getBindings();
                OperationBinding operBindingLimpiarTabla = binding.getOperationBinding("limpiarFilas");
                operBindingLimpiarTabla.execute();
                
                if(!operBindingLimpiarTabla.getErrors().isEmpty()){
                    logger.warning(" operBindingLimpiarTabla devuelve errores: " + operBindingLimpiarTabla.getErrors());
                }
                
            }catch(Exception e){
                    logger.warning("Acceso negado...");
                    logger.warning(" Error al ejecutar el operBindingLimpiarTabla: ", e);
            }
            

            logger.warning("Se a finalizado el metodo limpiarTabla()...");
        }
    
    public Boolean cargarRow(){
        
        logger.warning("*** Inicia la llamada a el metodo cargarRow de VO ");
        AdquisicionesBean adquisicionBean =
                    (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        
        Boolean respuestaCargarRow = Boolean.FALSE;
        
        try{
            Integer idTarea = Integer.valueOf( adquisicionBean.getTarea().toString() );
            Integer idTareaBpm = 0;
            Integer idProcesoBpm =null;
            
            if(idTarea != null){
                    idTareaBpm = idTarea;
            }
            
            logger.warning("idTarea a enviar: " + idTareaBpm);
            logger.warning("idProceso a enviar: " + idProcesoBpm);
            BindingContainer binding = getBindings();
            OperationBinding operBindingCargarRow = binding.getOperationBinding("cargarRowVo");
            operBindingCargarRow.getParamsMap().put("idTarea", idTareaBpm);
            operBindingCargarRow.getParamsMap().put("idProceso", idProcesoBpm);
            operBindingCargarRow.execute();
            Boolean respuestaMetodo = (Boolean) operBindingCargarRow.getResult();
            
            if(respuestaMetodo==true)
                respuestaCargarRow = Boolean.TRUE;
            else
                respuestaCargarRow = Boolean.FALSE;
            
        }catch(Exception e){
                logger.warning(" Error al ejecutar el operBindingCargarRow: ", e);
                respuestaCargarRow = Boolean.FALSE;
        }
        logger.warning("valor a retornar respuestaCargarRow: " + respuestaCargarRow);
        logger.warning("*** Termina la llamada a el metodo cargarRow de VO ");
        return respuestaCargarRow;
    }

    public void eliminarEvidenciaActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside eliminarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        AdquisicionesBean adquisicionBean =
            (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        Boolean noObjecionActiva = Boolean.TRUE;

        if (JSFUtils.resolveExpression("#{sessionScope.noObjecion}") != null) {
            noObjecionActiva = (Boolean) (JSFUtils.resolveExpression("#{sessionScope.noObjecion}"));
        }
        logger.warning("noObjecionActiva: " + noObjecionActiva);
        if ((noObjecionActiva && adquisicionBean.getTarea().compareTo(FenixConstants.TAREA_GESTOR_OPERACIONES)==0) 
            || adquisicionBean.getTarea().compareTo(FenixConstants.TAREA_GESTOR_OPERACIONES)!=0) {
            logger.warning("Se pueden hacer cambios a adquisiciones");
            Long variable1 = null;
            try {
                if (JSFUtils.resolveExpression("#{sessionScope.idNoObjecion}") != null) {
                    variable1 = Long.parseLong(JSFUtils.resolveExpression("#{sessionScope.idNoObjecion}").toString());
                }
                logger.warning("valor activo de la adquisicion " + variable1);
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.warning("idAdquisicion no obtenida");
            }
            adquisicionBean.setIdObjecionUnica(variable1);
            // Abrimos popup
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupEliminarEvidencia().show(popupHints);
            AdfFacesContext.getCurrentInstance().addPartialTarget(formEvidencias);
        } else {
            JSFUtils.addFacesErrorMessage("Hay cambios pendientes por confirmar");
        }
    }
    
    public String getDocpop() {
        DCIteratorBinding configuracionVOIterator = ADFUtils.findIterator(IWsdlLocation.CONFIGURACION_ITERATOR);
        ViewObject configuracionVoImpl = configuracionVOIterator.getViewObject();
        Row row = configuracionVoImpl.getRow(new Key(new Object[]{"URL_DOCPOP"}));
        String url = row == null ? null : (String) row.getAttribute("Valor");
        return url;
    }
    
    
    public void buttonBuscarDocumentosTrazabilidadOperacion(ActionEvent actionEvent){
        logger.warning("Boton Buscar presionado iniciando accion...");
        
        AdquisicionesBean adquisicionBean =
                    (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        
        String msg=null;
        limpiarTabla();
        String Documento = null;
        Integer idTipoDocumento = null;
        Integer idProcesoBpm = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        Date parsedDate = null;
        Timestamp fechaIni = null;
        Timestamp fechaFin = null;
        
        Integer idTarea = null;
        Integer idTareaBpm = null;
        
        logger.warning("Recuperando datos para filtrar evidencias...");
        
        Documento = ( null != ADFUtils.getBoundAttributeValue("Documento"))? ADFUtils.getBoundAttributeValue("Documento").toString() : null;
        logger.warning("Documento--->" + Documento);
        
        try{
            idTipoDocumento = ( null != ADFUtils.getBoundAttributeValue("IdTipoDocumento"))? new Integer(ADFUtils.getBoundAttributeValue("IdTipoDocumento").toString()) : null;
            logger.warning("idTipoDocumento--->" + idTipoDocumento);
        }catch(Exception e){
            logger.warning("idTipoDocumento--->null");
        }
        
        try{
            idProcesoBpm = ( null != ADFUtils.getBoundAttributeValue("IdProcesoBpm"))? new Integer(ADFUtils.getBoundAttributeValue("IdProcesoBpm").toString()) : null;
            logger.warning("idProcesoBpm--->" + idProcesoBpm);
        }catch(Exception e){
            logger.warning("idProcesoBpm--->null");
        }
        
        try{
            parsedDate = dateFormat.parse(ADFUtils.getBoundAttributeValue("FechaIni").toString());
            fechaIni = new java.sql.Timestamp(parsedDate.getTime());
            logger.warning("fechaIni--->" + fechaIni);
        }catch(Exception e){//this generic but you can control another types of exception
            logger.warning("fechaIni es null...");
        }
        
        try{
            parsedDate = dateFormat.parse(ADFUtils.getBoundAttributeValue("FechaFin").toString());
            fechaFin = new java.sql.Timestamp(parsedDate.getTime());
            logger.warning("fechaFin--->" + fechaFin);
        }catch(Exception e){//this generic but you can control another types of exception
            logger.warning("fechaFin es null...");
        }
        
        if(fechaIni==null){
                logger.warning("Ingresando fecha inicial (fechaIni es null...)");
                try{
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    parsedDate = dateFormat.parse("2000-01-01 00:00:00.0");
                    fechaIni = new java.sql.Timestamp(parsedDate.getTime());
                    logger.warning("Listo fechaIni--->"+fechaIni);
                }catch(Exception e){//this generic but you can control another types of exception
                    logger.warning("No se pudo introducir la fecha inicial(fechaIni es null...)");
                }
            }
            
            if(fechaFin==null){
                logger.warning("Ingresando fecha actual del sistema (fechaFin es null...)");
                try{
                    Date date = new Date();
                    String fechaAct = dateFormat.format(date);
                    parsedDate = dateFormat.parse(fechaAct);
                    fechaFin = new java.sql.Timestamp(parsedDate.getTime());
                    logger.warning("Listo fechaFin--->"+fechaFin);
                }catch(Exception e){//this generic but you can control another types of exception
                    logger.warning("No se pudo introducir la fecha actual del sistema (fechaFin es null...)");
                }
            }
        
            try{
                logger.warning("Inicia verificacion(fecha inicial no sea mayor que fecha fin)");
                if(fechaFin.getTime() < fechaIni.getTime()){
                    logger.warning("Ingresa al if)");
                    msg = "La fecha inicial no puede ser mayor que la fecha fin ";
                    logger.warning("Se muestra mensaje de error: )" + msg);
                }
            }catch(Exception e){
                logger.warning("Error en la verificacion...", e);
            }
            
            try{
                idTarea = Integer.valueOf( adquisicionBean.getTarea().toString() );
                idTareaBpm = 0;
               
            }catch(Exception e){
                logger.warning("Error al obtener el id de la tarea... " + e.getClass() + ", " + e.getMessage());
            }
        
        if (msg != null) {
            logger.warning("Muestra mensaje de error: " + msg);
            JSFUtils.addFacesErrorMessage(msg);
        }else{
        
            logger.warning("Ejecutando el View Criteria a DocumentosTrazabilidadOperacionVO...)");
            BindingContainer binding = getBindings();
            OperationBinding operBinding = binding.getOperationBinding("buscarDocumentosTrazabilidadOperacion");
            operBinding.getParamsMap().put("Documento", Documento);
            operBinding.getParamsMap().put("idTipoDocumento", idTipoDocumento);
            operBinding.getParamsMap().put("idProcesoBpm", idProcesoBpm);
            operBinding.getParamsMap().put("fechaIni", fechaIni);
            operBinding.getParamsMap().put("fechaFin", fechaFin);
            operBinding.getParamsMap().put("idTarea", idTareaBpm);
        
            operBinding.execute();
        
            if(!operBinding.getErrors().isEmpty()){
                logger.warning("OperationBinding devuelve errores");
            }
        
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupAgregarEvidenciasAdquisicion());
        
            logger.warning("*********** termina metodo busquedaOperaciones");
        }
    }
    
    public void buttonRestablecerDocumentosTrazabilidadOperacion(ActionEvent actionEvent){
        logger.warning("Se a oprimido el boton de Restablecer, iniciando accion...");
        // metodo limpia las row de la vo          
        logger.warning("Llamada al metodo limpiarTabla()...");
        limpiarTabla();
        
        logger.warning("Limpiando formulario de busqueda...");
        //Limpiar campos del formulario
        ADFUtils.setBoundAttributeValue("Documento", null);
        ADFUtils.setBoundAttributeValue("IdTipoDocumento", null);
        ADFUtils.setBoundAttributeValue("IdProcesoBpm", null);
        ADFUtils.setBoundAttributeValue("FechaIni", null);
        ADFUtils.setBoundAttributeValue("FechaFin", null);
        logger.warning("Listo formulario de busqueda limpio...");
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupAgregarEvidenciasAdquisicion());
    }
    
    public void aceptarAgregarEvidenciaActionListener(ActionEvent actionEvent){
        logger.warning("Inicia metodo para aceptarAgregarEvidenciaActionListener...");
        metodoconsulta();
        eligeAdquisicion2();
        AttributeBinding idAdquisicionAttribute = null;
        idAdquisicionAttribute = ADFUtils.findControlBinding("Id");
        AdquisicionesBean adquisicionBean = (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        Long idAdquisicion = null;
        Boolean seleccion = null;
        
        try{
            seleccion = ( null != ADFUtils.getBoundAttributeValue("seleccion"))? new Boolean(ADFUtils.getBoundAttributeValue("seleccion").toString()) : null;
            logger.warning("seleccionCheckBox--->" + seleccion);
        }catch(Exception e){
            logger.warning("seleccionCheckBox--->null");
        }
        logger.warning("*** Continua proceso agregar evidencias 1");
        
        logger.log(ADFLogger.TRACE, "Inside aceptarAgregarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        BindingContainer bindings = ADFUtils.getBindingContainer();
        //DCIteratorBinding voEvidenciasAdquisicion = null;
        OperationBinding operationBinding = null;
        logger.warning("*** Continua proceso agregar evidencias 2");
        
        // ASIGNACION de variables
        //voEvidenciasAdquisicion = ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasAdquisicionVOIterator");
        logger.warning("*** Continua proceso agregar evidencias 3");
        // Invocamos método que agrega las evidencias seleccionadas
        operationBinding = bindings.getOperationBinding("agregarEvidenciasAdquisicion");
        eligeAdquisicion2();
        idAdquisicion = (Long) ADFUtils.getBoundAttributeValue("Id");
        
        logger.warning("*** Id adquisicion: " + idAdquisicion);
        
        logger.warning("adquisicionBean.getIdAdquisiones(): " + adquisicionBean.getIdAdquisiones());
        adquisicionBean.setIdAdquisiones(idAdquisicion);
        logger.warning("adquisicionBean.getIdAdquisiones() after: " + adquisicionBean.getIdAdquisiones());
        
        operationBinding.getParamsMap().put("idAdquisicion", Long.valueOf(idAdquisicion));
        
        logger.warning("*** Continua proceso agregar evidencias 4");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        
        logger.warning("adquisicionBean.getIdAdquisiones() after asigna binding: " + adquisicionBean.getIdAdquisiones());
        //idAdquisicionAttribute.setInputValue(adquisicionBean.getIdAdquisiones());
        idAdquisicion = (Long) ADFUtils.getBoundAttributeValue("Id");
        
        logger.warning("*** Id adquisicionAfterExecute: " + idAdquisicion);
        logger.warning("*** Continua proceso agregar evidencias 5");
        
        // Actualizamos lista de evidencias
        actualizarEvidenciasAdquisicion2();
        
        logger.warning("Llamada al metodo limpiarTabla()...");
        limpiarTabla();
        
        logger.warning("Limpiando formulario de busqueda...");
        //Limpiar campos del formulario
        ADFUtils.setBoundAttributeValue("Documento", null);
        ADFUtils.setBoundAttributeValue("IdTipoDocumento", null);
        ADFUtils.setBoundAttributeValue("IdProcesoBpm", null);
        ADFUtils.setBoundAttributeValue("FechaIni", null);
        ADFUtils.setBoundAttributeValue("FechaFin", null);
        logger.warning("Listo formulario de busqueda limpio...");
        
        // Cerramos popup
        getPopupAgregarEvidenciasAdquisicion().hide();
        eligeAdquisicion2();
        AdfFacesContext.getCurrentInstance().addPartialTarget(formEvidencias);

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
    
    public void elegirAdquisicion(Long idAdquisicion) {
        logger.warning("Inside elegirAdquisicion.");
        logger.warning("idAdquisicion" + idAdquisicion);
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("adquisicionUnica");
        operationBinding.getParamsMap().put("idAdquiscion", idAdquisicion);
        operationBinding.execute();
        
    }
    
    public Boolean aplicaRollBack() {
        Boolean respuesta=Boolean.FALSE;
        logger.warning("Inside rollback.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("rollbackAdquisiciones");
        respuesta=(Boolean)operationBinding.execute();
        return respuesta;
    }
    
    public Boolean aplicaCommit() {
        Boolean respuesta=Boolean.FALSE;
        logger.warning("Inside rollback.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        operationBinding = bindings.getOperationBinding("commitAdquisiciones");
        respuesta=(Boolean)operationBinding.execute();
        return respuesta;
        
    }
    
    private void actualizarEvidenciasAdquisicion(){
        logger.warning("Inside actualizarEvidenciasAdquisicion.");
        logger.warning("Ingresa al metodo actualizarEvidenciasAdquisicion");

        AttributeBinding idAdquisicionAttribute = null;
        idAdquisicionAttribute = ADFUtils.findControlBinding("Id");
        
        Long idAdquisicion = null;
        
        //DCIteratorBinding voEvidenciasAdquisicion = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        //voEvidenciasAdquisicion = ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasAdquisicionVOIterator");
        
        idAdquisicion = (Long) ADFUtils.getBoundAttributeValue("Id");
        logger.warning("idAdquisicion: " + idAdquisicion);
        
        idAdquisicion = recuperarCurrentRowAdquisicion();
        logger.warning("idAdquisicionRecuperado: " + idAdquisicion);
        /*
        idAdquisicionAttribute.setInputValue(idAdquisicion);
        logger.warning("idAdquisicionAttribute.getInputValue(): " + idAdquisicionAttribute.getInputValue());
        */
        operationBinding = bindings.getOperationBinding("setpIdAdquisicionEviAdq");
        operationBinding.getParamsMap().put("value", idAdquisicion);
        operationBinding.execute();
        
        // Actualizamos lista de evidencias
        //voEvidenciasAdquisicion.executeQuery();
        logger.warning("Finaliza el metodo actualizarEvidenciasAdquisicion");
        logger.warning("End actualizarEvidenciasAdquisicion.");
    }
    
    
    private void actualizarEvidenciasAdquisicion2(){
        
        logger.warning("Inside actualizarEvidenciasAdquisicion.");
        logger.warning("Ingresa al metodo actualizarEvidenciasAdquisicion");
        eligeAdquisicion2();
        AttributeBinding idAdquisicionAttribute = null;
        idAdquisicionAttribute = ADFUtils.findControlBinding("Id");
        
        Long idAdquisicion = null;
        
        //DCIteratorBinding voEvidenciasAdquisicion = null;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        
        //voEvidenciasAdquisicion = ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasAdquisicionVOIterator");
        
        idAdquisicion = (Long) ADFUtils.getBoundAttributeValue("Id");
        logger.warning("idAdquisicion: " + idAdquisicion);
        
        idAdquisicion = recuperarCurrentRowAdquisicion();
        logger.warning("idAdquisicionRecuperado: " + idAdquisicion);
        /*
        idAdquisicionAttribute.setInputValue(idAdquisicion);
        logger.warning("idAdquisicionAttribute.getInputValue(): " + idAdquisicionAttribute.getInputValue());
        */
        operationBinding = bindings.getOperationBinding("setpIdAdquisicionEviAdq");
        operationBinding.getParamsMap().put("value", idAdquisicion);
        operationBinding.execute();
        
        // Actualizamos lista de evidencias
        //voEvidenciasAdquisicion.executeQuery();
        logger.warning("Finaliza el metodo actualizarEvidenciasAdquisicion");
        logger.warning("End actualizarEvidenciasAdquisicion.");
    }
    
    public Long recuperarCurrentRowAdquisicion() {
        logger.warning("Ingresa al metodo recuperarCurrentRowAdquisicion.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Long idAdquisicionRecuperado = null;
        
        operationBinding = bindings.getOperationBinding("recuperarIdAdquisicion");
        
        if (!operationBinding.getErrors().isEmpty()) {
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else {
            idAdquisicionRecuperado = (Long) operationBinding.execute();    
        }
        
        logger.warning("idAdquisicionRecuperado: " + idAdquisicionRecuperado);
        
        logger.warning("Finaliza el metodo actualizarEvidenciasAdquisicion.");
        return idAdquisicionRecuperado;
    }
    
    public void cancelarAgregarEvidenciaActionListener(ActionEvent actionEvent){
        logger.log(ADFLogger.TRACE, "Inside cancelarAgregarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        
        logger.warning("Llamada al metodo limpiarTabla()...");
        limpiarTabla();
        
        logger.warning("Limpiando formulario de busqueda...");
        //Limpiar campos del formulario
        ADFUtils.setBoundAttributeValue("Documento", null);
        ADFUtils.setBoundAttributeValue("IdTipoDocumento", null);
        ADFUtils.setBoundAttributeValue("IdProcesoBpm", null);
        ADFUtils.setBoundAttributeValue("FechaIni", null);
        ADFUtils.setBoundAttributeValue("FechaFin", null);
        logger.warning("Listo formulario de busqueda limpio...");
        
        // Cerramos popup de Agregar evidencia
        getPopupAgregarEvidenciasAdquisicion().hide();
    }
    
    public void eliminarAgregarEvidenciaActionListener(ActionEvent actionEvent){
        logger.warning("Inicia metodo eliminarAgregarEvidencia");
        
        AdquisicionesBean adquisicionBean = (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");        
        Long idAdquisicion = null;
        eligeAdquisicion2();
        metodoconsulta();
        BindingContainer bindings = ADFUtils.getBindingContainer();
        logger.warning("Inicia metodo eliminarAgregarEvidencia--2");
        //DCIteratorBinding voEvidenciasAdquisicion = null;
        logger.warning("Inicia metodo eliminarAgregarEvidencia--3");
        OperationBinding operationBinding = null;
        logger.warning("Inicia metodo eliminarAgregarEvidencia--4");
        // Asignación de variables
        //voEvidenciasAdquisicion = ADFUtils.getDCBindingContainer().findIteratorBinding("EvidenciasAdquisicionVOIterator");
        logger.warning("Inicia metodo eliminarAgregarEvidencia--5");
        // Invocamos método que elimina la evidencia seleccionada
        operationBinding = bindings.getOperationBinding("eliminarTreEvidenciaAdquisicion");
        logger.warning("Inicia metodo eliminarAgregarEvidencia--6");
        logger.warning("idTreEvidenciaAdquisicion--->" + Integer.parseInt(JSFUtils.resolveExpression("#{viewScope.idEvidenciaAdquisicion}").toString()));
        operationBinding.getParamsMap().put("idTreEvidenciaAdquisicion", Integer.parseInt(JSFUtils.resolveExpression("#{viewScope.idEvidenciaAdquisicion}").toString()));
        logger.warning("Inicia metodo eliminarAgregarEvidencia--7(LLama al metodo de elminar de la VO)");
        operationBinding.execute();
        if(!operationBinding.getErrors().isEmpty()){
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        }
        logger.warning("Inicia metodo eliminarAgregarEvidencia--8(Finaliza el meotodo eliminarAgregarEvidencia)");
        // Actualizamos lista de evidencias
        //voEvidenciasAdquisicion.executeQuery();
        logger.warning("Actualizamos lista de evidencias)");
        
        eligeAdquisicion2();
        metodoconsulta();
        
        idAdquisicion = (Long) ADFUtils.getBoundAttributeValue("Id");
        
        logger.warning("*** Id adquisicionAfterExecute: " + idAdquisicion);
        
        actualizarEvidenciasAdquisicion2();
        // Cerramos popup
        getPopupEliminarEvidencia().hide();
        eligeAdquisicion2();
        AdfFacesContext.getCurrentInstance().addPartialTarget(formEvidencias);
        metodoconsulta();
    }
    
    public void cancelarEliminarEvidenciaActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside cancelarEliminarEvidenciaActionListener: " + actionEvent.getComponent().getId());
        
        // Cerramos popup
        getPopupEliminarEvidencia().hide();
    }
    
    public void setPopupAgregarEvidenciasAdquisicion(RichPopup popupAgregarEvidenciasAdquisicion) {
        this.popupAgregarEvidenciasAdquisicion = popupAgregarEvidenciasAdquisicion;
    }

    public RichPopup getPopupAgregarEvidenciasAdquisicion() {
        return popupAgregarEvidenciasAdquisicion;
    }

    public void setPopupEliminarEvidencia(RichPopup popupEliminarEvidencia) {
        this.popupEliminarEvidencia = popupEliminarEvidencia;
    }

    public RichPopup getPopupEliminarEvidencia() {
        return popupEliminarEvidencia;
    }
    
    public void setDocpop(String docpop) {
        this.docpop = docpop;
    }

    public String getDocpop1() {
        return docpop;
    }    
    
    public void validarAdquisicion() {
        logger.warning("Entra a validarAdquisicion");
        
        AdquisicionesBean adquisicionBean = (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        Boolean finaliza = Boolean.TRUE;
        String idTcaTipoNormativaAplicada = (ADFUtils.getBoundAttributeValue("IdTcaTipoNormativaAplicada") == null) ? null :
                                             ADFUtils.getBoundAttributeValue("IdTcaTipoNormativaAplicada").toString();
        String cuentaConAdquisicionPrevia = (ADFUtils.getBoundAttributeValue("CuentaConAdquisicionPrevia") == null) ? null :
                                             ADFUtils.getBoundAttributeValue("CuentaConAdquisicionPrevia").toString();
        
        Boolean adquisicionPreviaBoolean = cuentaConAdquisicionPrevia.equals("1") ? true : false;
        Boolean esPGA = ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion") != null? (ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion").toString().equals("5")) : Boolean.FALSE;
        
        //Obtiene campos a validar
        logger.warning("normativaApl: " + idTcaTipoNormativaAplicada);
        logger.warning("adquisicionPreviaBoolean: " + adquisicionPreviaBoolean);
        
        logger.warning("NormativaEspecifica: " + ADFUtils.getBoundAttributeValue("NormativaEspecifica"));
        logger.warning("NumeroAdquisicion: " + ADFUtils.getBoundAttributeValue("NumeroAdquisicion"));
        logger.warning("IdTcaTipoAdquisicion: " + ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion"));
        logger.warning("MontoPresupuestado: " + ADFUtils.getBoundAttributeValue("MontoPresupuestado"));
        logger.warning("MontoContratado: " + ADFUtils.getBoundAttributeValue("MontoContratado"));
        logger.warning("MontoAsociado: " + ADFUtils.getBoundAttributeValue("MontoAsociado"));
        logger.warning("IdTcaTipoAlcanceProceso: " + ADFUtils.getBoundAttributeValue("IdTcaTipoAlcanceProceso"));
        logger.warning("IdTcaTipoModalidadProceso: " + ADFUtils.getBoundAttributeValue("IdTcaTipoModalidadProceso"));
        logger.warning("FechaContrato: " + ADFUtils.getBoundAttributeValue("FechaContrato"));
        logger.warning("NombreAdjudicatario: " + ADFUtils.getBoundAttributeValue("NombreAdjudicatario"));
        logger.warning("NacionalidadAdjudicatario: " + ADFUtils.getBoundAttributeValue("NacionalidadAdjudicatario"));
        logger.warning("TituloAdquisicion: " + ADFUtils.getBoundAttributeValue("TituloAdquisicion"));
        logger.warning("ObjetivoAdquisicion: " + ADFUtils.getBoundAttributeValue("ObjetivoAdquisicion"));
        logger.warning("cuentaConAdquisicionPrevia: " + cuentaConAdquisicionPrevia);
        logger.warning("esPGA: " + esPGA);
        
        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoNormativaAplicada") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoNormativaAplicada").toString().length() > 0))) {
            finaliza = Boolean.FALSE;
            //JSFUtils.addFacesErrorMessage("Debe seleccionar una Normativa Aplicada.");
        } else {
            Integer normativaApl = Integer.valueOf(idTcaTipoNormativaAplicada);
            if (normativaApl.compareTo(1) == 0) {
                
                //Flujo principal.
                if(esPGA){
                    if (!adquisicionPreviaBoolean) {
                        if (ADFUtils.getBoundAttributeValue("NumeroAdquisicion") == null) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar un Número de Adquisición.");
                        }
                        
                        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion").toString().length() > 0))) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar un Tipo de Adquisición.");
                        }
                        
                        if (ADFUtils.getBoundAttributeValue("TituloAdquisicion") == null) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar el Título de Adquisición");
                        } 
                        
                    //EA07 Adquisicion previa de normativa BCIE.
                    } else if (adquisicionPreviaBoolean) {
                            if ((ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion").toString().length() > 0))) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar un Tipo de Adquisición.");
                        }

                        if (ADFUtils.getBoundAttributeValue("TituloAdquisicion") == null) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar el Título de Adquisición");
                        }
                    }
                }
                else{
                    if (!adquisicionPreviaBoolean) {
                        if (ADFUtils.getBoundAttributeValue("NumeroAdquisicion") == null) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar un Número de Adquisición.");
                        }
                        
                        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion").toString().length() > 0))) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar un Tipo de Adquisición.");
                        }

                        if (ADFUtils.getBoundAttributeValue("MontoPresupuestado") == null) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar Monto presupuestado");
                        }

                        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoAlcanceProceso") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoAlcanceProceso").toString().length() > 0))) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar Alcance del Proceso");
                        }

                        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoModalidadProceso") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoModalidadProceso").toString().length() > 0))) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar la Modalidad del Proceso");
                        }

                        if (ADFUtils.getBoundAttributeValue("TituloAdquisicion") == null) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar el Título de Adquisición");
                        }
                        
                        if (ADFUtils.getBoundAttributeValue("ObjetivoAdquisicion") == null) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar un Objetivo de la Adquisición");
                        }
                        
                    //EA07 Adquisicion previa de normativa BCIE.
                    } else if (adquisicionPreviaBoolean) {
                            if ((ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion").toString().length() > 0))) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar un Tipo de Adquisición.");
                        }

                        if (ADFUtils.getBoundAttributeValue("MontoPresupuestado") == null) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar Monto presupuestado");
                        }

                        if ((ADFUtils.getBoundAttributeValue("IdTcaTipoModalidadProceso") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoModalidadProceso").toString().length() > 0))) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar la Modalidad del Proceso");
                        }

                        if (ADFUtils.getBoundAttributeValue("TituloAdquisicion") == null) {
                            finaliza = Boolean.FALSE;
                            JSFUtils.addFacesErrorMessage("Debe agregar el Título de Adquisición");
                        }
                    }    
                }
                
            }
            
            //EA05 Normativa diferente de BCIE y Otra Política.
            if (normativaApl.compareTo(8) != 0 && normativaApl.compareTo(1) != 0) {
                
                if ((ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion").toString().length() > 0))) {
                    finaliza = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe agregar un Tipo de Adquisicion.");
                }
                
                if (normativaApl.compareTo(7) == 0 || (normativaApl.compareTo(7) != 0 && !adquisicionPreviaBoolean)) {
                    if (ADFUtils.getBoundAttributeValue("MontoContratado") == null) {
                        finaliza = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage("Debe agregar el Monto contratado");
                    }
                    
                    if (ADFUtils.getBoundAttributeValue("FechaContrato") == null) {
                        finaliza = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage("Debe agregar la Fecha de contrato");
                    }
                    
                    if (ADFUtils.getBoundAttributeValue("NombreAdjudicatario") == null) {
                        finaliza = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage("Debe agregar el Nombre del Adjudicatario");
                    }
                    
                    if (ADFUtils.getBoundAttributeValue("NacionalidadAdjudicatario") == null) {
                        finaliza = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage("Debe agregar la Nacionalidad del Adjudicatario");
                    }
                    
                } else {

                    if (ADFUtils.getBoundAttributeValue("MontoAsociado") == null) {
                        finaliza = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage("Debe agregar el Monto asociado");
                    }
                }
                
                if (ADFUtils.getBoundAttributeValue("TituloAdquisicion") == null) {
                    finaliza = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe agregar el Título de adquisición");
                }
            }
            
            //EA06 Normativa Otra Política.
            if (normativaApl.compareTo(8) == 0) {
                
                if (ADFUtils.getBoundAttributeValue("NormativaEspecifica") == null) {
                    finaliza = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe agregar el campo especificar.");
                }
                
                    if ((ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion") == null) || (!(ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion").toString().length() > 0))) {
                    finaliza = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe agregar un Tipo de Adquisición.");
                }
                    else{
                        Integer valor= Integer.parseInt(ADFUtils.getBoundAttributeValue("IdTcaTipoAdquisicion").toString());
                        if(valor.compareTo(0)==0){
                                finaliza = Boolean.FALSE;
                                JSFUtils.addFacesErrorMessage("Debe agregar un Tipo de Adquisición.");
                            }
                        }

                if (ADFUtils.getBoundAttributeValue("MontoContratado") == null) {
                    finaliza = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe agregar el Monto contratado");
                }
                
                if (ADFUtils.getBoundAttributeValue("FechaContrato") == null) {
                    finaliza = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe agregar Fecha Contrato");
                }
                
                if (ADFUtils.getBoundAttributeValue("NombreAdjudicatario") == null) {
                    finaliza = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe agregar el Nombre del Adjudicatario");
                }
                
                if (ADFUtils.getBoundAttributeValue("NacionalidadAdjudicatario") == null) {
                    finaliza = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe agregar la Nacionalidad del Adjudicatario");
                }

                if (ADFUtils.getBoundAttributeValue("TituloAdquisicion") == null) {
                    finaliza = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Debe agregar el Titulo de adquisicion");
                }
            }
        }
        
        logger.warning("finaliza: " + finaliza);
        
        if (finaliza) {
            guardarAdquisicion();
            adquisicionBean.setAgregarEvidencia(Boolean.FALSE);
            adquisicionBean.setListaHabilitada(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(getPflAdquisicionTableUIC());
        } else {
            JSFUtils.addFacesErrorMessage("Debe capturar los datos obligatorios.");
            logger.warning("Existen datos faltantes para poder realizar Guardado.");    
            faltanCampos=Boolean.FALSE;
        }
        
        popupGuardarAdquisicion.hide();
    }

    @SuppressWarnings("unchecked")
    public void aceptarGuardarAquisicion(ActionEvent actionEvent) {
        logger.warning("Ingresa al metodo aceptarGuardarAquisicion");
        faltanCampos=Boolean.TRUE;
        validarAdquisicion();
        
        AdquisicionesBean adquisicionBean =
                            (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        
        logger.log(ADFLogger.WARNING," seleccionarAdquisicion >>> "+adquisicionBean.getIdAdquisiones());
        //Seleccionar current row en la lista de adquisiciones
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("seleccionarAdquisicion");
        operationBinding.getParamsMap().put("idAdquisicion", adquisicionBean.getIdAdquisiones());
        operationBinding.execute();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPflAdquisicionTableUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTableEvidencias());
        rpNoObjecionesUIC.refresh(FacesContext.getCurrentInstance());
        logger.warning("se muestran mensajes de validacion? " + faltanCampos);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getRpNoObjecionesUIC());
        logger.warning("Finaliza al metodo aceptarGuardarAquisicion");
    }
    
    //Metodo de prueba
    public static void main(String[] args) {
        boolean estaEnEdicion = Boolean.FALSE;
        
        Integer aux = 1;
        Long auxIdAdquisicion = 1L;  
        
        if (aux != null && aux.compareTo(new Integer(1)) == 0) {
            estaEnEdicion = Boolean.TRUE; 
        }
        
        System.out.println("auxIdAdquisicion: " + auxIdAdquisicion);
        System.out.println("estaEnEdicion: " + estaEnEdicion);
        if (auxIdAdquisicion != null && !estaEnEdicion) {
            System.out.println("Entra");
        } else {
            System.out.println("NO Entra");
        }
    }
    
    public void eligeAdquisicion(){
        AdquisicionesBean adquisicionBean =
                        (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
            Long variable1=null;
            boolean estaEnEdicion = Boolean.FALSE;
                try {
                    if (adquisicionBean.getEdita2agrega1() != null && adquisicionBean.getEdita2agrega1().compareTo(new Integer(1)) == 0) {
                        estaEnEdicion = Boolean.TRUE; 
                    }
                    
                    if (JSFUtils.resolveExpression("#{sessionScope.idAdquisicion}") != null && !estaEnEdicion) {
                    variable1 = Long.parseLong(JSFUtils.resolveExpression("#{sessionScope.idAdquisicion}").toString());
                        BindingContainer bindings = ADFUtils.getBindingContainer();
                        OperationBinding operationBinding = null;

                        operationBinding = bindings.getOperationBinding("seleccionarAdquisicion");
                        operationBinding.getParamsMap().put("idAdquisicion", variable1);
                        operationBinding.execute();
                    }
                    logger.warning("valor actual de la adquisicion " + variable1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.warning("valor adquisicion no obtenida");
                }
        }
    
    
    public void eligeAdquisicion2(){
        AdquisicionesBean adquisicionBean =
                        (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
            Long variable1=null;
            boolean estaEnEdicion = Boolean.FALSE;
                try {
                    
                    variable1 = Long.parseLong(JSFUtils.resolveExpression("#{sessionScope.idAdquisicion}").toString());
                        BindingContainer bindings = ADFUtils.getBindingContainer();
                        OperationBinding operationBinding = null;

                        operationBinding = bindings.getOperationBinding("seleccionarAdquisicion");
                        operationBinding.getParamsMap().put("idAdquisicion", variable1);
                        operationBinding.execute();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.warning("valor adquisicion no obtenida");
                }
        }
    
    public void actualizarDatosAdquisicion() {
        logger.warning("Inside actualizarDatosAdquisicion.");
        
        AttributeBinding idAdquisicionAttribute = null;
        idAdquisicionAttribute = ADFUtils.findControlBinding("Id");
        
        Long idAdquisicion = null;
        
        idAdquisicion = (Long) ADFUtils.getBoundAttributeValue("Id");
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupGuardarAdquisicion());    
    }
    
    public void cancelarGuardarAdquisicion(ActionEvent actionEvent) {
        popupGuardarAdquisicion.hide();
    }
    
    public String modificarCancelarAdquisicion(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        popupCancelarModificarAdquisicion.hide();
        return null;
        }
    
    public void guardarAdquisicion(ActionEvent actionEvent) {
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupGuardarAdquisicion().show(popupHints);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(getPopupGuardarAdquisicion());
    }
    
    public String guardarAdquisicion2() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupGuardarAdquisicion().show(popupHints);
        return null;
    }
    
    public void cancelarModificarAdquisicion(ActionEvent actionEvent){
        actionEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        actualizarDatosAdquisicion();
        actualizarEvidenciasAdquisicion();
        
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
        getPopupCancelarModificarAdquisicion().show(popupHints);
    }
    
    public String cancelarModificarAdquisicion2(){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
            getPopupCancelarModificarAdquisicion().show(popupHints);
            return null;
    }
    
    public void cerrarEliminarAdquisicion(ActionEvent actionEvent) {
            popupEliminar.hide();
    }
    
    public RichPopup getPopupEliminar() {
        return popupEliminar;
    }
    
    public void setPopupEliminar(RichPopup popupEliminar) {
        this.popupEliminar = popupEliminar;
    }

    public void setPopupGuardarAdquisicion(RichPopup popupGuardarAdquisicion) {
        this.popupGuardarAdquisicion = popupGuardarAdquisicion;
    }

    public RichPopup getPopupGuardarAdquisicion() {
        return popupGuardarAdquisicion;
    }

    public void setPopupCancelarAdquisicion(RichPopup popupCancelarAdquisicion) {
        this.popupCancelarAdquisicion = popupCancelarAdquisicion;
    }

    public RichPopup getPopupCancelarAdquisicion() {
        return popupCancelarAdquisicion;
    }

    public void setPopupCancelarModificarAdquisicion(RichPopup popupCancelarModificarAdquisicion) {
        this.popupCancelarModificarAdquisicion = popupCancelarModificarAdquisicion;
    }

    public RichPopup getPopupCancelarModificarAdquisicion() {
        return popupCancelarModificarAdquisicion;
    }

    public void setFechaContratoRID(RichInputDate fechaContratoRID) {
        this.fechaContratoRID = fechaContratoRID;
    }

    public RichInputDate getFechaContratoRID() {
        return fechaContratoRID;
    }


    public void descargarEvidencia(FacesContext facesContext, OutputStream outputStream) {
        
        logger.warning("Entra a descargarEvidencia");
        
        Long idDocumento = (Long)JSFUtils.resolveExpression("#{viewScope.IdDocumento}");
        
        logger.warning("Id Documento: " + idDocumento);
        
        BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
        OperationBinding operationBindingIdOp = null;
        //metodo para obtener si el produto es 'Linea global de credito'
        operationBindingIdOp = bindingsIdOp.getOperationBinding("getAdjuntoPorIdDocumento");
        operationBindingIdOp.getParamsMap().put("idDocumento", idDocumento);
        operationBindingIdOp.execute();
        
        BlobDomain content = (BlobDomain) operationBindingIdOp.getResult();
        InputStream in = null;
        
        logger.warning("Comienza a crear el archivo");
        if(content != null){
            try {
                in = content.getInputStream();
                byte[] buffer = new byte[8192];
                int bytesRead = 0;

                while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                content.closeInputStream(); // close the blob to release the recources
                outputStream.flush(); // flush the output stream
            } catch (IOException e) {
                logger.warning("Error al descargar el archivo : " + e.getMessage()); 
            }
        }
        logger.warning("Termina de crear el archivo");
    }

    public void setListViewEvidencias(RichListView listViewEvidencias) {
        this.listViewEvidencias = listViewEvidencias;
    }

    public RichListView getListViewEvidencias() {
        return listViewEvidencias;
    }

    public void setTableEvidencias(RichTable tableEvidencias) {
        this.tableEvidencias = tableEvidencias;
    }

    public RichTable getTableEvidencias() {
        return tableEvidencias;
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
    
    public Long getSelectedRowId() {
        Long rowId = 0L;
        if (null != keyId) {
            rowId = keyId;
        }
        return rowId;
    }


    public RichRegion getRpNoObjecionesUIC() {
        return rpNoObjecionesUIC;
    }

    public void setRpNoObjecionesUIC(RichRegion rpNoObjecionesUIC) {
        this.rpNoObjecionesUIC = rpNoObjecionesUIC;
    }

    public String aceptarGuardarAdquisicion2() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        validarAdquisicion();
        AdquisicionesBean adquisicionBean =
                            (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
        
        logger.log(ADFLogger.WARNING," seleccionarAdquisicion >>> "+adquisicionBean.getIdAdquisiones());
        //Seleccionar current row en la lista de adquisiciones
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;

        operationBinding = bindings.getOperationBinding("seleccionarAdquisicion");
        operationBinding.getParamsMap().put("idAdquisicion", adquisicionBean.getIdAdquisiones());
        operationBinding.execute();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPflAdquisicionTableUIC());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getTableEvidencias());
        rpNoObjecionesUIC.refresh(FacesContext.getCurrentInstance());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getRpNoObjecionesUIC());
        return null;
    }

    public String cancelarGuardarAdquisicion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        popupGuardarAdquisicion.hide();
        return null;
    }

    public void setFormDatos1UIC(RichPanelFormLayout formDatos1UIC) {
        this.formDatos1UIC = formDatos1UIC;
    }

    public RichPanelFormLayout getFormDatos1UIC() {
        return formDatos1UIC;
    }

    public void setFormDatos2UIC(RichPanelFormLayout formDatos2UIC) {
        this.formDatos2UIC = formDatos2UIC;
    }

    public RichPanelFormLayout getFormDatos2UIC() {
        return formDatos2UIC;
    }

    public void setFormDatos3UIC(RichPanelFormLayout formDatos3UIC) {
        this.formDatos3UIC = formDatos3UIC;
    }

    public RichPanelFormLayout getFormDatos3UIC() {
        return formDatos3UIC;
    }

    public void setFormDatos4UIC(RichPanelFormLayout formDatos4UIC) {
        this.formDatos4UIC = formDatos4UIC;
    }

    public RichPanelFormLayout getFormDatos4UIC() {
        return formDatos4UIC;
    }
    
    public void recargaConcursantes(){
            logger.warning("Ingresa metodo recargaConcursantes");
            AdquisicionesBean adquisicionBean = (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
            logger.warning("Objecion obtenida "+adquisicionBean.getIdObjecionUnica());
                logger.warning("Entra a consultar objecion");
                Map respuesta=new HashMap();
                BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
                OperationBinding operationBindingIdOp = null;
                operationBindingIdOp = bindingsIdOp.getOperationBinding("recuperaDatos");
                if(null!=adquisicionBean.getIdObjecionUnica()){
                        operationBindingIdOp.getParamsMap().put("idObjecion", adquisicionBean.getIdObjecionUnica());

                    }
                respuesta=(HashMap)operationBindingIdOp.execute();
                logger.warning("Objecion obtenida "+(Long)respuesta.get("claveObjecion"));
                if(null!=(Long)respuesta.get("claveObjecion")){
                        adquisicionBean.setIdObjecionUnica((Long)respuesta.get("claveObjecion"));
                    }
                else{
                        logger.warning("No se obtuvo objecion o no tiene");
                    }
            logger.warning("Finaliza metodo recargaConcursantes");
        }
    
    public void recargaIdAdquisicionObjecion(String metodo){
            AdquisicionesBean adquisicionBean = (AdquisicionesBean) JSFUtils.resolveExpression("#{pageFlowScope.adquisicionBean}");
            logger.warning("Objecion obtenida "+adquisicionBean.getIdObjecionUnica());
                logger.warning("Entra a consultar objecion");
                BindingContainer bindingsIdOp = ADFUtils.getBindingContainer();
                OperationBinding operationBindingIdOp = null;
                operationBindingIdOp = bindingsIdOp.getOperationBinding(metodo);
                operationBindingIdOp.execute();
                }
    public void metodoconsulta(){
            recargaIdAdquisicionObjecion("obtenerAdquisicion");
            recargaIdAdquisicionObjecion("obtenerNoObjecion");
        Long variable1=null;
            try {
                if (JSFUtils.resolveExpression("#{sessionScope.idAdquisicion}") != null) {
                variable1 = Long.parseLong(JSFUtils.resolveExpression("#{sessionScope.idAdquisicion}").toString());
                }
                logger.warning("valor actual de la adquisicion " + variable1);
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.warning("valor adquisicion no obtenida");
            }
            
        try {
            if (JSFUtils.resolveExpression("#{sessionScope.idNoObjecion}") != null) {
            variable1 = Long.parseLong(JSFUtils.resolveExpression("#{sessionScope.idNoObjecion}").toString());
            }
            logger.warning("objecion obtenida " + variable1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("no objecion no obtenida");
        }
    }

    public void setFormEvidencias(RichPanelFormLayout formEvidencias) {
        this.formEvidencias = formEvidencias;
    }

    public RichPanelFormLayout getFormEvidencias() {
        return formEvidencias;
    }

    public void cambiaTipoAdquisicion(ValueChangeEvent valueChangeEvent) {
        logger.warning("Entrando a cambiaTipoAdquisicion");
        conteo=1;
        startTime = System.currentTimeMillis();
        logger.warning("Tiempo de inicio respuesta del: "
         + startTime + " segundos");
        //obtener los atributos del tipo de adquisicion
        logger.warning("valor obtenido en el tipo de adquisicion " + (Integer) valueChangeEvent.getOldValue());
        AdfFacesContext.getCurrentInstance().addPartialTarget(getFormDatos2UIC());
    }

    public Boolean getFaltanCampos() {
        return faltanCampos;
    }

    public void setFaltanCampos(Boolean faltanCampos) {
        this.faltanCampos = faltanCampos;
    }

    public RichPanelFormLayout getFormDatosTitulo() {
        return formDatosTitulo;
    }

    public void setFormDatosTitulo(RichPanelFormLayout formDatosTitulo) {
        this.formDatosTitulo = formDatosTitulo;
    }

    public void setObjetivoUIC(RichInputText objetivoUIC) {
        this.objetivoUIC = objetivoUIC;
    }

    public RichInputText getObjetivoUIC() {
        return objetivoUIC;
    }

    public void cambioPrevia(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        FacesContext.getCurrentInstance().renderResponse();
        AdfFacesContext.getCurrentInstance().addPartialTarget(formDatos2UIC);
        AdfFacesContext.getCurrentInstance().addPartialTarget(formDatos3UIC);
    }

    public void setMontoPUIC(RichInputText montoPUIC) {
        this.montoPUIC = montoPUIC;
    }

    public RichInputText getMontoPUIC() {
        return montoPUIC;
    }

    public void setMontoCUIC(RichInputText montoCUIC) {
        this.montoCUIC = montoCUIC;
    }

    public RichInputText getMontoCUIC() {
        return montoCUIC;
    }

    public void setMontoAUIC(RichInputText montoAUIC) {
        this.montoAUIC = montoAUIC;
    }

    public RichInputText getMontoAUIC() {
        return montoAUIC;
    }

    public void setTipoAdUIC(RichSelectOneChoice tipoAdUIC) {
        this.tipoAdUIC = tipoAdUIC;
    }

    public RichSelectOneChoice getTipoAdUIC() {
        return tipoAdUIC;
    }

    public void setModalidadUIC(RichSelectOneChoice modalidadUIC) {
        this.modalidadUIC = modalidadUIC;
    }

    public RichSelectOneChoice getModalidadUIC() {
        return modalidadUIC;
    }
    
    public void limpiaCampos(){
    logger.warning("Inicia limpieza de campos");
    getMontoPUIC().setValue(null);
    getMontoPUIC().resetValue();
    getMontoAUIC().setValue(null);
    getMontoAUIC().resetValue();
    getMontoCUIC().setValue(null);
    getMontoCUIC().resetValue();
    getTipoAdUIC().setValue(null);
    getTipoAdUIC().resetValue();
    getModalidadUIC().setValue(null);
    getModalidadUIC().resetValue();
    
    getAlcanceUIC().setValue(null);
    getAlcanceUIC().resetValue();
    getNumeroAdUIC().setValue(null);
    getNumeroAdUIC().resetValue();
    getEspecificarUIC().setValue(null);
    getEspecificarUIC().resetValue();
    getFechaContratoRID().setValue(null);
    getFechaContratoRID().resetValue();
    getTituloAdUIC().setValue(null);
    getTituloAdUIC().resetValue();
    getNormativaApUIC().setValue(null);
    getNormativaApUIC().resetValue();
    getAdjudicatarioUIC().setValue(null);
    getAdjudicatarioUIC().resetValue();
    getNacionalidadAdjUIC().setValue(null);
    getNacionalidadAdjUIC().resetValue();
    }

    public void cambioNumeroAdquisicion(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        logger.warning("Se ingresa numero de adquisicion");
        AdfFacesContext.getCurrentInstance().addPartialTarget(tipoAdUIC);
    }

    public void setNormativaApUIC(RichSelectOneChoice normativaApUIC) {
        this.normativaApUIC = normativaApUIC;
    }

    public RichSelectOneChoice getNormativaApUIC() {
        return normativaApUIC;
    }

    public void setEspecificarUIC(RichInputText especificarUIC) {
        this.especificarUIC = especificarUIC;
    }

    public RichInputText getEspecificarUIC() {
        return especificarUIC;
    }

    public void setNumeroAdUIC(RichInputText numeroAdUIC) {
        this.numeroAdUIC = numeroAdUIC;
    }

    public RichInputText getNumeroAdUIC() {
        return numeroAdUIC;
    }

    public void setAlcanceUIC(RichSelectOneChoice alcanceUIC) {
        this.alcanceUIC = alcanceUIC;
    }

    public RichSelectOneChoice getAlcanceUIC() {
        return alcanceUIC;
    }

    public void setAdjudicatarioUIC(RichInputText adjudicatarioUIC) {
        this.adjudicatarioUIC = adjudicatarioUIC;
    }

    public RichInputText getAdjudicatarioUIC() {
        return adjudicatarioUIC;
    }

    public void setNacionalidadAdjUIC(RichInputText nacionalidadAdjUIC) {
        this.nacionalidadAdjUIC = nacionalidadAdjUIC;
    }

    public RichInputText getNacionalidadAdjUIC() {
        return nacionalidadAdjUIC;
    }

    public void setTituloAdUIC(RichInputText tituloAdUIC) {
        this.tituloAdUIC = tituloAdUIC;
    }

    public RichInputText getTituloAdUIC() {
        return tituloAdUIC;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void cambioModalidad(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
                endTime = System.currentTimeMillis();
                logger.warning("Tiempo de respuesta del: "
                 + endTime + " segundos");
    }

    public Integer getConteo() {
        return conteo;
    }

    public void setConteo(Integer conteo) {
        this.conteo = conteo;
    }
}
