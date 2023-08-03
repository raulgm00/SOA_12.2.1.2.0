package org.bcie.fenix.view.gestordesembolsos;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.internal.binding.DCTaskFlowBinding;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adfinternal.controller.state.AdfcContext;
import oracle.adfinternal.controller.state.ChildViewPortContextImpl;
import oracle.adfinternal.controller.state.RootViewPortContextImpl;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;
import org.bcie.fenix.common.view.beans.FenixActionBean.OperationExecuteException;

public class DetalleDesembolsoActionBean extends FenixActionBean {
    
    private static ADFLogger logger = ADFLogger.createADFLogger(DetalleDesembolsoActionBean.class);
    
    public static final String BUNDLE = "view.GestorDesembolsosFenixVCBundle";

    private RichInputText tiCliente;
    private RichInputText itIdSolicitud;
    private RichSelectOneChoice socResponsableOperacion;
    private RichInputText itBhqCliente;
    private RichInputText itNumeroLineaCredito;
    private RichSelectOneChoice socSectorInstitucional;
    private RichInputText itNombreOperacion;
    private RichInputText itIdDesembolso;
    private RichInputText itIdOperacion;
    private RichSelectOneChoice socPais;
    private RichSelectOneChoice socEstado;
    private RichSelectOneChoice socTipoProducto;
    private RichSelectOneChoice socTipoFecha;
    private RichInputDate idFechaDel;
    private RichInputDate idFechaAl;
    private RichButton btnCrearSolicitud;

    public DetalleDesembolsoActionBean() {
    }
    
    public void buscarActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.WARNING, "inside. " + actionEvent.getComponent());
        
        JSFUtils.setExpressionValue("#{sessionScope.btn_ver_detalle}", Boolean.TRUE); // deshabilita el boton de ver detalle       
        RichButton rbVerDetalle = (RichButton) actionEvent.getComponent().getParent().getParent().findComponent("b2");
        AdfFacesContext.getCurrentInstance().addPartialTarget(rbVerDetalle);

        realizarBusquedaPorCriterios();
    }
    
    private void realizarBusquedaPorCriterios() {
        CriterioBusquedaBean noObjecionBean = 
            (CriterioBusquedaBean) JSFUtils.resolveExpression("#{criterioBusquedaBean}");
        
        try {
            Map<String, Object> paramsQuery = new HashMap<String, Object>();
            paramsQuery.put("pCliente", noObjecionBean.getTiCliente() != null ? noObjecionBean.getTiCliente().getValue() : null);
            paramsQuery.put("pBhqCliente", noObjecionBean.getItBhqCliente() != null ? noObjecionBean.getItBhqCliente().getValue(): null);
            paramsQuery.put("pNombreOperacion", noObjecionBean.getItNombreOperacion() != null ? noObjecionBean.getItNombreOperacion().getValue() : null);
            paramsQuery.put("pStrIdOperacion", noObjecionBean.getItIdOperacion() != null ? noObjecionBean.getItIdOperacion().getValue() : null);
            paramsQuery.put("pIdSolicitud", noObjecionBean.getItIdSolicitud() != null ? noObjecionBean.getItIdSolicitud().getValue() : null);
            paramsQuery.put("pNumeroLineaCredito", noObjecionBean.getItNumeroLineaCredito() != null ? noObjecionBean.getItNumeroLineaCredito().getValue() : null);
            paramsQuery.put("pIdDesembolso", noObjecionBean.getItIdDesembolso() != null ? noObjecionBean.getItIdDesembolso().getValue() : null);
            paramsQuery.put("pPais", noObjecionBean.getSocPais() != null ? noObjecionBean.getSocPais().getValue(): null);
            paramsQuery.put("pResponsableOperacion", noObjecionBean.getSocResponsableOperacion() != null ? noObjecionBean.getSocResponsableOperacion().getValue(): null);
            paramsQuery.put("pSectorInstitucional", noObjecionBean.getSocSectorInstitucional() != null ? noObjecionBean.getSocSectorInstitucional().getValue(): null);
            paramsQuery.put("pTipoProducto", null);
            paramsQuery.put("pEstado", noObjecionBean.getSocEstado() != null ? noObjecionBean.getSocEstado().getValue(): null);
            
            int op = noObjecionBean.getSocTipoFecha().getValue() != null ? (noObjecionBean.getSocTipoFecha().getValue().toString().isEmpty() ? 0 : Integer.valueOf(noObjecionBean.getSocTipoFecha().getValue().toString())) : 0;
            logger.log(ADFLogger.WARNING, "TipoFecha [" + op + "]");
            
            if (this.validaFechas(op)) {
                switch (op) {
                case 1: // Para el tipo Fecha de solicitud
                    paramsQuery.put("pFechaSolicitudDel", noObjecionBean.getIdFechaDel() != null ? noObjecionBean.getIdFechaDel().getValue() : null);
                    paramsQuery.put("pFechaSolicitudAl", noObjecionBean.getIdFechaAl() != null ? noObjecionBean.getIdFechaAl().getValue() : null);
                    paramsQuery.put("pFechaEfectivaDel", null);
                    paramsQuery.put("pFechaEfectivaAl", null);
                    break;
                case 2: // Para el tipo Fecha efectiva
                    paramsQuery.put("pFechaSolicitudDel", null);
                    paramsQuery.put("pFechaSolicitudAl", null);
                    paramsQuery.put("pFechaEfectivaDel", noObjecionBean.getIdFechaDel() != null ? noObjecionBean.getIdFechaDel().getValue() : null);
                    paramsQuery.put("pFechaEfectivaAl", noObjecionBean.getIdFechaAl() != null ? noObjecionBean.getIdFechaAl().getValue() : null);
                    break;
                default:
                    paramsQuery.put("pFechaSolicitudDel", null);
                    paramsQuery.put("pFechaSolicitudAl", null);
                    paramsQuery.put("pFechaEfectivaDel", null);
                    paramsQuery.put("pFechaEfectivaAl", null);
                    break;
                }
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("params", paramsQuery);
                logger.log(ADFLogger.WARNING, "Ejecutando el criterio con los filtros de búsqueda.");
                this.execute(params, "executeViewCriteriaConsultaSolicitud");
            }
        } catch (OperationExecuteException e) {
            logger.log(ADFLogger.WARNING, "Ocurrio un error al generar la busqueda",  e);
        }
    }
    
    private boolean validaFechas(int op) {
        logger.log(ADFLogger.WARNING, "validando fechas del - al");
        boolean valida = Boolean.FALSE;
        
        CriterioBusquedaBean noObjecionBean = 
            (CriterioBusquedaBean) JSFUtils.resolveExpression("#{criterioBusquedaBean}");
        
        
        if (op > 0) { // Está validando que esté seleccionada una opción del Tipo de fecha

            if (noObjecionBean.getIdFechaDel().getValue() != null) {
                
                if (noObjecionBean.getIdFechaAl().getValue() != null) {
                    oracle.jbo.domain.Date del = (oracle.jbo.domain.Date) noObjecionBean.getIdFechaDel().getValue();
                    oracle.jbo.domain.Date al = (oracle.jbo.domain.Date) noObjecionBean.getIdFechaAl().getValue();
                    
                    if (del.compareTo(al) < 0) {
                        valida = Boolean.TRUE;
                    } else {
                        valida = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("La fecha Del debe ser menor a la fecha Al");
                    }
                } else {
                    logger.info("Se asigna fecha actual a la fecha AL");
                    noObjecionBean.setIdFechaAl(new RichInputDate());
                    noObjecionBean.getIdFechaAl().setValue(new oracle.jbo.domain.Date(new java.sql.Date(new java.util.Date().getTime())));
                    valida = Boolean.TRUE;
                }
            } else {
                    valida = Boolean.FALSE;
                    JSFUtils.addFacesErrorMessage("Se debe de ingresar una fecha en Del");
            }
        } else {
            valida = Boolean.TRUE;
        }
        return valida;
    }

    public void verDetalleActionListener(ActionEvent actionEvent) {
        logger.info("inside. " + actionEvent.getComponent());
        Map<String, Object> params = this.parametersPageFlowResultado();
        JSFUtils.setExpressionValue("#{pageFlowScope.idContratoDesembolso}", params.get("idContratoDesembolso"));
        JSFUtils.setExpressionValue("#{pageFlowScope.idLineaCredito}", params.get("idLineaCredito"));
        JSFUtils.setExpressionValue("#{pageFlowScope.idSolicitud}", params.get("idSolicitud"));
        JSFUtils.setExpressionValue("#{pageFlowScope.idEstadoSolicitud}", params.get("idEstadoSolicitud"));
        JSFUtils.setExpressionValue("#{pageFlowScope.idOperacion}", params.get("idOperacion"));
    }
    
    public oracle.jbo.domain.Date getCurrentDateDel() {
        return new oracle.jbo.domain.Date(new java.sql.Date(new java.util.Date().getTime()));
    }
    
    public oracle.jbo.domain.Date getCurrentDateAl() {
        java.util.Calendar calendar = new java.util.GregorianCalendar();
        calendar.set(java.util.Calendar.DAY_OF_MONTH, calendar.get(java.util.Calendar.DAY_OF_MONTH) + 1);
        return new oracle.jbo.domain.Date(new java.sql.Date(calendar.getTime().getTime()));
    }

    public void setTiCliente(RichInputText tiCliente) {
        this.tiCliente = tiCliente;
    }

    public RichInputText getTiCliente() {
        return tiCliente;
    }

    public void setItIdSolicitud(RichInputText itIdSolicitud) {
        this.itIdSolicitud = itIdSolicitud;
    }

    public RichInputText getItIdSolicitud() {
        return itIdSolicitud;
    }

    public void setSocResponsableOperacion(RichSelectOneChoice socResponsableOperacion) {
        this.socResponsableOperacion = socResponsableOperacion;
    }

    public RichSelectOneChoice getSocResponsableOperacion() {
        return socResponsableOperacion;
    }

    public void setItBhqCliente(RichInputText itBhqCliente) {
        this.itBhqCliente = itBhqCliente;
    }

    public RichInputText getItBhqCliente() {
        return itBhqCliente;
    }

    public void setItNumeroLineaCredito(RichInputText itNumeroLineaCredito) {
        this.itNumeroLineaCredito = itNumeroLineaCredito;
    }

    public RichInputText getItNumeroLineaCredito() {
        return itNumeroLineaCredito;
    }

    public void setSocSectorInstitucional(RichSelectOneChoice socSectorInstitucional) {
        this.socSectorInstitucional = socSectorInstitucional;
    }

    public RichSelectOneChoice getSocSectorInstitucional() {
        return socSectorInstitucional;
    }

    public void setItNombreOperacion(RichInputText itNombreOperacion) {
        this.itNombreOperacion = itNombreOperacion;
    }

    public RichInputText getItNombreOperacion() {
        return itNombreOperacion;
    }

    public void setItIdDesembolso(RichInputText itIdDesembolso) {
        this.itIdDesembolso = itIdDesembolso;
    }

    public RichInputText getItIdDesembolso() {
        return itIdDesembolso;
    }

    public void setItIdOperacion(RichInputText itIdOperacion) {
        this.itIdOperacion = itIdOperacion;
    }

    public RichInputText getItIdOperacion() {
        return itIdOperacion;
    }

    public void setSocPais(RichSelectOneChoice socPais) {
        this.socPais = socPais;
    }

    public RichSelectOneChoice getSocPais() {
        return socPais;
    }

    public void setSocEstado(RichSelectOneChoice socEstado) {
        this.socEstado = socEstado;
    }

    public RichSelectOneChoice getSocEstado() {
        return socEstado;
    }

    public void setSocTipoProducto(RichSelectOneChoice socTipoProducto) {
        this.socTipoProducto = socTipoProducto;
    }

    public RichSelectOneChoice getSocTipoProducto() {
        return socTipoProducto;
    }

    public void setSocTipoFecha(RichSelectOneChoice socTipoFecha) {
        this.socTipoFecha = socTipoFecha;
    }

    public RichSelectOneChoice getSocTipoFecha() {
        return socTipoFecha;
    }

    public void setIdFechaDel(RichInputDate idFechaDel) {
        this.idFechaDel = idFechaDel;
    }

    public RichInputDate getIdFechaDel() {
        return idFechaDel;
    }

    public void setIdFechaAl(RichInputDate idFechaAl) {
        this.idFechaAl = idFechaAl;
    }

    public RichInputDate getIdFechaAl() {
        return idFechaAl;
    }

    public void selectRowTreeTableActionListener(SelectionEvent selectionEvent) {
        logger.warning("inside");
        /* START PRESERVER DEFAULT ADF SELECT BEHAVIOR */
        
        String adfSelectionListener = "#{bindings.ConsultaSolicitudDesembolsoTreeTableVO.treeModel.makeCurrent}";
        FacesContext fctx = FacesContext.getCurrentInstance();
        Application application = fctx.getApplication();
        ELContext elCtx = fctx.getELContext();
        ExpressionFactory exprFactory = application.getExpressionFactory();
     
        MethodExpression me = null;
        me = exprFactory.createMethodExpression(elCtx, adfSelectionListener, Object.class, new Class[] { SelectionEvent.class });
        me.invoke(elCtx, new Object[] { selectionEvent });
     
        /* END PRESERVER DEFAULT ADF SELECT BEHAVIOR */
        
        RichTreeTable treeTable = (RichTreeTable)selectionEvent.getSource();
        RowKeySet rowKeySet = selectionEvent.getAddedSet();
        Iterator<Object> rksIterator = rowKeySet.iterator();
        Long idEstadoAux = null;
        BigDecimal idEstadoContrato = null;
        Integer idEstado = null;
        
        DetalleDesembolsosBean detalleDesembolsosBean = 
            (DetalleDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.detalleDesembolsosBean}");
        detalleDesembolsosBean.setIdTcaEstadoOperacion(null);
        
        while (rksIterator.hasNext()) {
            @SuppressWarnings("unchecked")
            List<Object> key = (List<Object>)rksIterator.next();
            JUCtrlHierBinding treeBinding = null;
            CollectionModel collectionModel = (CollectionModel)treeTable.getValue();
            treeBinding = (JUCtrlHierBinding)collectionModel.getWrappedData();
            JUCtrlHierNodeBinding nodeContrato = null;
            nodeContrato = treeBinding.findNodeByKeyPath(key);
            Row rowContrato = nodeContrato.getRow();
            
            String rowTypeContrato = rowContrato.getStructureDef().getDefName();    
            
            if (rowTypeContrato.equalsIgnoreCase("ConsultaSolicitudDesembolsoTreeTableVO")) {
                logger.warning("Inf, Se selecciono el nodo padre de solicitud");
                JSFUtils.setExpressionValue("#{pageFlowScope.idSolicitud}", rowContrato.getAttribute("Id"));
                JSFUtils.setExpressionValue("#{pageFlowScope.idEstadoSolicitud}", rowContrato.getAttribute("IdEstado"));
                JSFUtils.setExpressionValue("#{pageFlowScope.idOperacion}", rowContrato.getAttribute("IdOperacion"));
                idEstadoAux = (Long) rowContrato.getAttribute("IdEstado");
                idEstado = idEstadoAux.intValue();

                if (null != rowContrato.getAttribute("IdTcaEstadoOperacion")) {
                    detalleDesembolsosBean.setIdTcaEstadoOperacion((Integer) rowContrato.getAttribute("IdTcaEstadoOperacion"));
                    logger.warning("IdTcaEstadoOperacion: " + detalleDesembolsosBean.getIdTcaEstadoOperacion());
                } else {
                    logger.warning("El estado de la opercion es nulo.");
                }
            }
            
             if(rowTypeContrato.equalsIgnoreCase("ConsultaSolicitudLineaCreditoTreeTableVO")){                                                                                       
                  logger.warning("Inf, Se selecciono el nodo de Linea");                  
                    JSFUtils.setExpressionValue("#{pageFlowScope.idLineaCredito}", rowContrato.getAttribute("IdLineaCredito"));
                    
                    JUCtrlHierNodeBinding nodeSolicitud = nodeContrato.getParent();
                    Row rowSolicitud = nodeSolicitud.getRow();                                     
                    JSFUtils.setExpressionValue("#{pageFlowScope.idSolicitud}", rowSolicitud.getAttribute("Id"));
                    JSFUtils.setExpressionValue("#{pageFlowScope.idEstadoSolicitud}", rowSolicitud.getAttribute("IdEstado"));
                    JSFUtils.setExpressionValue("#{pageFlowScope.idOperacion}", rowSolicitud.getAttribute("IdOperacion"));                    
                }
                                                 
            if (rowTypeContrato.equalsIgnoreCase("ConsultaSolicitudContratoDesembolsoTreeTableVO")) {
                
                logger.warning("Inf, Se selecciono el nodo de Contrato de desembolso");
                
                    JSFUtils.setExpressionValue("#{pageFlowScope.idContratoDesembolso}", rowContrato.getAttribute("Id"));
                    
                    JUCtrlHierNodeBinding nodeLinea = nodeContrato.getParent();
                    Row rowLinea = nodeLinea.getRow();
                    JSFUtils.setExpressionValue("#{pageFlowScope.idLineaCredito}", rowLinea.getAttribute("IdLineaCredito"));
                    
                    JUCtrlHierNodeBinding nodeSolicitud = nodeLinea.getParent();
                    Row rowSolicitud = nodeSolicitud.getRow();
                    //Se obtiene el row del contrato seleccionado
                    Row rowSolicitudContrato = nodeContrato.getRow();
                    JSFUtils.setExpressionValue("#{pageFlowScope.idSolicitud}", rowSolicitud.getAttribute("Id"));
                    JSFUtils.setExpressionValue("#{pageFlowScope.idEstadoSolicitud}", rowSolicitud.getAttribute("IdEstado"));
                    JSFUtils.setExpressionValue("#{pageFlowScope.idOperacion}", rowSolicitud.getAttribute("IdOperacion"));
                    idEstadoContrato = (BigDecimal)rowSolicitudContrato.getAttribute("IdEstado");        
                    idEstado = idEstadoContrato.intValue();
            } 
            //Se realiza validacion para habilitar el boton de Ver detalle de acuerdo 
            //a el estado en que se encuentra el contrato
            logger.warning("Valor del estado :" + idEstado);
            if(null != idEstado){
                if (idEstado.compareTo(FenixConstants.ID_ESTADO_CONTRATO_DESEMBOLSO_CREADO_POR_IMPLEMENTACION) == 0 ||
                    idEstado.compareTo(FenixConstants.ID_ESTADO_SOLICITUD_DESEMBOLSO_CREADO_POR_IMPLEMENTACION) == 0) {
                        //Desactivamos el boton ver detalle cuando el estado es creado por implementacion
                        JSFUtils.setExpressionValue("#{sessionScope.btn_ver_detalle}", Boolean.TRUE); 
                }else{
                    JSFUtils.setExpressionValue("#{sessionScope.btn_ver_detalle}", Boolean.FALSE); //Activamos el boton ver detalle   
                }
            }else{
                logger.warning("El estado es nulo, se habilita el boton ver detalle.");
                JSFUtils.setExpressionValue("#{sessionScope.btn_ver_detalle}", Boolean.FALSE); //Activamos el boton ver detalle   
            }
            
        }
        RichButton rbVerDetalle = (RichButton) selectionEvent.getComponent().getParent().getParent().findComponent("b2");
        AdfFacesContext.getCurrentInstance().addPartialTarget(rbVerDetalle);
    }
    
    private Map<String, Object> parametersPageFlowResultado() {
        BindingContext bctx = BindingContext.getCurrent();
        DCBindingContainer mainViewPageBinding = (DCBindingContainer) bctx.getCurrentBindingsEntry();

        // find the task flow pagedef binding, see the pageDef
        DCTaskFlowBinding tf = (DCTaskFlowBinding) mainViewPageBinding.findExecutableBinding("resultadoSolicitudDesembolsoBTF1");
        System.out.println(tf.getFullName());

        ControllerContext conn = ControllerContext.getInstance();

        RootViewPortContextImpl rootViewPort = (RootViewPortContextImpl) conn.getCurrentRootViewPort();
        ChildViewPortContextImpl childView = (ChildViewPortContextImpl) rootViewPort.getChildViewPortByClientId(tf.getFullName());

        return childView.getPageFlowScopeMap(AdfcContext.getCurrentInstance());
    }
    
    public String irVerCrearSolicitudDesembolsosGral() {
        logger.warning("Entrando en irVerCrearSolicitudDesembolsosGral.");
        String navegacion = null;
        
        navegacion = "irVerCrearSolicitudDesembolsosGral";
        
        return navegacion;
    }

    public RichButton getBtnCrearSolicitud() {
        return btnCrearSolicitud;
    }

    public void setBtnCrearSolicitud(RichButton btnCrearSolicitud) {
        this.btnCrearSolicitud = btnCrearSolicitud;
    }
}
