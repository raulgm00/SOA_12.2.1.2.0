package org.bcie.fenix.view.gestordesembolsos;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import java.util.concurrent.TimeUnit;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;

import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.jbo.domain.Date;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import oracle.ods.virtualization.engine.Entry;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

import org.bcie.fenix.common.FenixConstants;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class ContratoDesembolsosActionBean extends FenixActionBean {

    private static ADFLogger logger = null;
    public static final String BUNDLE = "view.GestorDesembolsosFenixVCBundle";
    public static final String COD_EXT_MONEDA_LINEA_CREDITO = "USD";
    public static final Integer FRECUENCIA_PERIODO_CERO = 0;
    public static final Integer ID_TCA_FRECUENCIA_PERIODO_DIAS = 1;
    
    private RichPanelTabbed panelTabsContrato;
    private RichShowDetailItem tabAsignacionUI;
    private RichShowDetailItem tabDatosUI;
    private RichSelectOneChoice programadoUIC;
    private RichInputDate disponibilidadRecursosUIC;
    private RichInputText campoProgramadoUIC;
    private RichShowDetailItem tabCondicionesUI;
    private RichShowDetailItem tabCargosUI;
    private RichPanelGroupLayout panelGroupContratoFormUIC;
    private RichPanelTabbed panelTabbedUIC;
    private RichShowDetailItem tabTransferenciasUI;
    private RichShowDetailItem tabLiquidarUI;
    private RichShowDetailItem tabConsolidarUI;
    private RichPopup alertaLimitesPopUpUIC;
    private Boolean esErrorLimiteMonto;
    private Boolean esErrorLimiteTasa;
    private RichPopup confirmarInicioDesembolsoPopupUIC;
    private Boolean esErrorValidacion = Boolean.FALSE;
    private RichPanelGroupLayout contenedorMontoProgramado;
    private RichRegion regionCargosUIC;
    private RichRegion regionDatosGeneralesUIC;
    private RichRegion regionTransferenciasReg;
    private RichPanelGroupLayout pglRegionAsignRec;
    private RichRegion regionAsignacionRecursosUIC;
    private RichOutputText initForm;
    private RichOutputText initPanelTab;
    private RichRegion regionReservaUI;
    private RichShowDetailItem tabReservaUI;
    private RichShowDetailItem tabCoberturaUI;
    private RichRegion regionCoberturaUI;
    private RichPopup alertaProgramacionUIC;
    
    Long idCondicionFinanciera = null;
    Integer especificacionFechas = null;
    Integer tipoCalendario = null;
    private RichRegion regionCondicionesFinancierasUIC;
    private RichPanelGroupLayout pglConidicionesUIC;

    public ContratoDesembolsosActionBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void setPanelTabsContrato(RichPanelTabbed panelTabsContrato) {
        this.panelTabsContrato = panelTabsContrato;
    }

    public RichPanelTabbed getPanelTabsContrato() {
        return panelTabsContrato;
    }

    public void setTabAsignacionUI(RichShowDetailItem tabAsignacionUI) {
        this.tabAsignacionUI = tabAsignacionUI;
    }

    public RichShowDetailItem getTabAsignacionUI() {
        return tabAsignacionUI;
    }

    public void setTabDatosUI(RichShowDetailItem tabDatosUI) {
        this.tabDatosUI = tabDatosUI;
    }

    public RichShowDetailItem getTabDatosUI() {
        return tabDatosUI;
    }

    public void setTabCondicionesUI(RichShowDetailItem tabCondicionesUI) {
        this.tabCondicionesUI = tabCondicionesUI;
    }

    public RichShowDetailItem getTabCondicionesUI() {
        return tabCondicionesUI;
    }

    public void setTabCargosUI(RichShowDetailItem tabCargosUI) {
        this.tabCargosUI = tabCargosUI;
    }

    public RichShowDetailItem getTabCargosUI() {
        return tabCargosUI;
    }

    public void setTabTransferenciasUI(RichShowDetailItem tabTransferenciasUI) {
        this.tabTransferenciasUI = tabTransferenciasUI;
    }

    public RichShowDetailItem getTabTransferenciasUI() {
        return tabTransferenciasUI;
    }
    
    public void setIdCondicionFinanciera(Long idCondicionFinanciera) {
        this.idCondicionFinanciera = idCondicionFinanciera;
    }

    public Long getIdCondicionFinanciera() {
        return idCondicionFinanciera;
    }

    public void setEspecificacionFechas(Integer especificacionFechas) {
        this.especificacionFechas = especificacionFechas;
    }

    public Integer getEspecificacionFechas() {
        return especificacionFechas;
    }

    public void setTipoCalendario(Integer tipoCalendario) {
        this.tipoCalendario = tipoCalendario;
    }

    public Integer getTipoCalendario() {
        return tipoCalendario;
    }

    public void cambioTabTranferencias(DisclosureEvent disclosureEvent) {
        logger.warning("Inicia metodo cambioTabTranferencias");
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        try{
            
            if(null == FacesContext.getCurrentInstance())
                logger.warning("FacesContext.getCurrentInstance() resuelto a NULL");
            
            disclosureEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        }catch(Exception e){           
            logger.warning("*Inf, ha ocurrido un error en el cambio de tab ->", e);
        }
        
        logger.warning("Entra a Transferencias");
        logger.warning("guardo? " + contratoDesembolsoBean.getGuardaTab());
        logger.warning("Tab anterior " + contratoDesembolsoBean.getValorAnteriorTab());
        logger.warning("Tab transferencias " + contratoDesembolsoBean.getTabTransferencias());
        
        if (contratoDesembolsoBean.getGuardaTab()) {
            if (contratoDesembolsoBean.getTabTransferencias()) {
                logger.warning("sale y guarda transferencias");
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_TRANSFERENCIAS);
                contratoDesembolsoBean.setGuardaTab(guardarContratoTransferencias()); //Metodo para guardar el tab con la informacion incluyendo validaciones en caso de tenerlo
                //contratoDesembolsoBean.setTabTransferencias(Boolean.FALSE);
                configurarCambioTab();
            } else {
                logger.warning("inicia transferencias");
                contratoDesembolsoBean.setTabTransferencias(Boolean.TRUE);
                //recarga de transferencias
                AdfFacesContext.getCurrentInstance().addPartialTarget(regionTransferenciasReg);
                regionTransferenciasReg.refresh(FacesContext.getCurrentInstance());
            }
        } else {
            tabTransferenciasUI.setDisclosed(false);
            contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.FALSE);
            contratoDesembolsoBean.setGuardaTab(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tabTransferenciasUI);
            switch (contratoDesembolsoBean.getValorAnteriorTab()) {
            case FenixConstants.TAB_ASIGNACION_RECURSOS:
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                logger.warning("regresa Asignacion de recursos");
                tabAsignacionUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabAsignacionUI);
                break;
            case FenixConstants.TAB_DATOS_GENERALES:
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa datos generales");
                tabDatosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabDatosUI);
                break;
            case FenixConstants.TAB_CONDICIONES_FINANCIERAS:
                contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa condiciones financieras");
                tabCondicionesUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCondicionesUI);
                break;
            case FenixConstants.TAB_CARGOS:
                contratoDesembolsoBean.setDisclosedTabCargos(Boolean.TRUE);
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                logger.warning("regresa a cargos");
                tabCargosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCargosUI);
                break;
            case FenixConstants.TAB_LIQUIDAR_CONTRATO:
                contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.TRUE);
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
                logger.warning("regresa liquidar contrato");
                tabLiquidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabLiquidarUI);
                break;
            case FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
                logger.warning("regresa a consolidar transferencias");
                tabConsolidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabConsolidarUI);
                break;
            case FenixConstants.TAB_RESERVA_FONDOS:
                contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.TRUE);
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                logger.warning("regresa a reserva fondos");
                tabReservaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabReservaUI);
                break;
            case FenixConstants.TAB_COBERTURAS:
                contratoDesembolsoBean.setDisclosedTabCoberturas(Boolean.TRUE);
                contratoDesembolsoBean.setTabCoberturas(Boolean.TRUE);
                logger.warning("regresa a cobertura");
                tabCoberturaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCoberturaUI);
                break;
            default:
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_TRANSFERENCIAS);
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabTransferencias(Boolean.TRUE);
                break;
            }
        }
        // Add event code here...
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        // Add event code here...
        logger.warning("termina metodo cambioTabTranferencias");
    }

    public void cambioTabCargos(DisclosureEvent disclosureEvent) {
        logger.warning("inicia metodo cambioTabCargos");
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
                
        try{
            
            if(null == FacesContext.getCurrentInstance())
                logger.warning("FacesContext.getCurrentInstance() resuelto a NULL");
            
            disclosureEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        }catch(Exception e){
            logger.warning("ha ocurrido un error en el cambio de tab ->",e);
        }
        
        logger.warning("Entra a cargos");
        logger.warning("guardo? " + contratoDesembolsoBean.getGuardaTab());
        logger.warning("Tab anterior " + contratoDesembolsoBean.getValorAnteriorTab());
        logger.warning("Tab cargos " + contratoDesembolsoBean.getTabCargos());

        if (contratoDesembolsoBean.getGuardaTab()) {
            if (contratoDesembolsoBean.getTabCargos()) {
                logger.warning("sale y guarda cargos");
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_CARGOS);
                // metodo de guardarCargos
                actualizarCargos();
                contratoDesembolsoBean.setGuardaTab(Boolean.TRUE); //Metodo para guardar el tab con la informacion incluyendo validaciones en caso de tenerlo
                //contratoDesembolsoBean.setTabCargos(Boolean.FALSE);
                configurarCambioTab();
            } else {
                logger.warning("inicia cargos");
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                recargaCargos();
            }
        } else {
            tabCargosUI.setDisclosed(false);
            contratoDesembolsoBean.setDisclosedTabCargos(Boolean.FALSE);
            contratoDesembolsoBean.setGuardaTab(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tabCargosUI);
            switch (contratoDesembolsoBean.getValorAnteriorTab()) {
            case FenixConstants.TAB_ASIGNACION_RECURSOS:
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                logger.warning("regresa Asignacion de recursos");
                tabAsignacionUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabAsignacionUI);
                break;
            case FenixConstants.TAB_DATOS_GENERALES:
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa datos generales");
                tabDatosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabDatosUI);
                break;
            case FenixConstants.TAB_CONDICIONES_FINANCIERAS:
                contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa condiciones financieras");
                tabCondicionesUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCondicionesUI);
                break;
            case FenixConstants.TAB_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabTransferencias(Boolean.TRUE);
                logger.warning("regresa a transferencias");
                tabTransferenciasUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabTransferenciasUI);
                break;
            case FenixConstants.TAB_LIQUIDAR_CONTRATO:
                contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.TRUE);
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
                logger.warning("regresa liquidar contrato");
                tabLiquidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabLiquidarUI);
                break;
            case FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
                logger.warning("regresa a consolidar transferencias");
                tabConsolidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabConsolidarUI);
                break;
            case FenixConstants.TAB_RESERVA_FONDOS:
                contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.TRUE);
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                logger.warning("regresa a reserva fondos");
                tabReservaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabReservaUI);
                break;
            case FenixConstants.TAB_COBERTURAS:
                contratoDesembolsoBean.setDisclosedTabCoberturas(Boolean.TRUE);
                contratoDesembolsoBean.setTabCoberturas(Boolean.TRUE);
                logger.warning("regresa a cobertura");
                tabCoberturaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCoberturaUI);
                break;
            default:
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_CARGOS);
                contratoDesembolsoBean.setDisclosedTabCargos(Boolean.TRUE);
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                break;
            }
        }
        // Add event code here...
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        // Add event code here...
        
        logger.warning("termina metodo cambioTabCargos");
    }
    
    public void cargarCFTemp(){
        logger.warning("**** Ingreso a cargarCFTemp ****");
        try{
            BindingContainer bindings = getBindings();
            ContratoDesembolsosBean contratoDesembolsoBean =(ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
            Long idContratoSeleccionado = (Long) ADFUtils.getBoundAttributeValue("Id");
            if(null!=idContratoSeleccionado && null!=contratoDesembolsoBean.getIdLinea()){
                Long idLineaCredito=contratoDesembolsoBean.getIdLinea();
                logger.warning("idLineaCredito --> "+idLineaCredito);
                OperationBinding cargarVO = bindings.getOperationBinding("cargarCFTempVO");
                cargarVO.getParamsMap().put("idContrato", String.valueOf(idContratoSeleccionado));
                cargarVO.getParamsMap().put("idLineaCredito", String.valueOf(idLineaCredito));
                cargarVO.execute();  
            }
        }catch(Exception ex){
            logger.warning("Error al cargar cargarCFTempVO ",ex);
            ex.printStackTrace();
        }
    }

    public void cambioTabCondiciones(DisclosureEvent disclosureEvent) {
      logger.warning("Inicia metodo cambioTabCondiciones");
        
        BindingContainer bindings = getBindings();
        
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        try{
            if(null == FacesContext.getCurrentInstance())
                logger.warning("FacesContext.getCurrentInstance() resuelto a NULL");
            
             disclosureEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        }catch(Exception e){
            logger.warning("ha ocurrido un error en el cambio de tab ->",e);
        }
        
        logger.warning("Entra a condiciones financieras");
        logger.warning("guardo? " + contratoDesembolsoBean.getGuardaTab());
        logger.warning("Tab anterior " + contratoDesembolsoBean.getValorAnteriorTab());
        logger.warning("Tab condiciones financieras " + contratoDesembolsoBean.getDisclosedTabCondicionesFinancieras());
        cargarCFTemp();
        
        if (contratoDesembolsoBean.getGuardaTab()) {
            if (contratoDesembolsoBean.getTabCondicionesFinancieras()) {
                logger.warning("sale y guarda condiciones financieras");
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_CONDICIONES_FINANCIERAS);
                try{
                    //execute(null, "guardarCondicionesFinancieras");
                }catch(Exception e){
                    logger.warning("ERROR al ejecutar el OperBinding guardarCondicionesFinancieras.", e);
                }
                contratoDesembolsoBean.setGuardaTab(Boolean.TRUE); //Metodo para guardar el tab con la informacion incluyendo validaciones en caso de tenerlo
                //contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.FALSE);
                configurarCambioTab();
            } else {
                logger.warning("inicia condiciones financieras");
                Map<String,Object> params = new HashMap<String,Object>();
                Boolean recargaProducto = Boolean.FALSE;
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("Recargando region de Condiciones financieras...");
                //AdfFacesContext.getCurrentInstance().addPartialTarget(regionCondicionesFinancierasUIC);
                regionCondicionesFinancierasUIC.refresh(FacesContext.getCurrentInstance());
                //params.put("idContratoDesembolso", contratoDesembolsoBean.getIdContratoDesembolso());
                //params.put("idOperacion", contratoDesembolsoBean.getIdOperacionTF());
                //try{
                    //recargaProducto = execute(params, "recargaVistaProducto");
                //}catch(Exception e){
                    //logger.warning("ERROR al ejecutar OperBinding .", e);
                //}
                //if(!recargaProducto){
                    //logger.warning("Vista de producto no recargada.");
                //}
            }
        } else {
            tabCondicionesUI.setDisclosed(false);
            contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.FALSE);
            contratoDesembolsoBean.setGuardaTab(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tabCondicionesUI);
            switch (contratoDesembolsoBean.getValorAnteriorTab()) {
            case FenixConstants.TAB_ASIGNACION_RECURSOS:
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                logger.warning("regresa Asignacion de recursos");
                tabAsignacionUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabAsignacionUI);
                break;
            case FenixConstants.TAB_DATOS_GENERALES:
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setTabDatosGenerales(Boolean.TRUE);
                logger.warning("regresa datos generales");
                tabDatosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabDatosUI);
                break;
            case FenixConstants.TAB_CARGOS:
                contratoDesembolsoBean.setDisclosedTabCargos(Boolean.TRUE);
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                logger.warning("regresa cargos");
                tabCargosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCargosUI);
                break;
            case FenixConstants.TAB_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabTransferencias(Boolean.TRUE);
                logger.warning("regresa a transferencias");
                tabTransferenciasUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabTransferenciasUI);
                break;
            case FenixConstants.TAB_LIQUIDAR_CONTRATO:
                contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.TRUE);
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
                logger.warning("regresa liquidar contrato");
                tabLiquidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabLiquidarUI);
                break;
            case FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
                logger.warning("regresa a consolidar transferencias");
                tabConsolidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabConsolidarUI);
                break;
            case FenixConstants.TAB_RESERVA_FONDOS:
                contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.TRUE);
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                logger.warning("regresa a reserva fondos");
                tabReservaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabReservaUI);
                break;
            case FenixConstants.TAB_COBERTURAS:
                contratoDesembolsoBean.setDisclosedTabCoberturas(Boolean.TRUE);
                contratoDesembolsoBean.setTabCoberturas(Boolean.TRUE);
                logger.warning("regresa a cobertura");
                tabCoberturaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCoberturaUI);
                break;
            default:
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_CONDICIONES_FINANCIERAS);
                contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                break;
            }
        }
        // Add event code here...
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        // Add event code here...
        logger.warning("termina metodo cambioTabCondiciones");
    }

    public void cambioTabDatos(DisclosureEvent disclosureEvent) {
      logger.warning("Inicia metodo cambioTabDatos");
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        try{            
            if(null == FacesContext.getCurrentInstance())
                   logger.warning("FacesContext.getCurrentInstance() resuelto a null");    
        
           disclosureEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        }catch(Exception e){
            logger.warning("ha ocurrido un error en el cambio de tab ->",e);
        }
        
        logger.warning("Entra a datos generales");
        logger.warning("guardo? " + contratoDesembolsoBean.getGuardaTab());
        logger.warning("Tab anterior " + contratoDesembolsoBean.getValorAnteriorTab());
        logger.warning("Tab datos generales " + contratoDesembolsoBean.getTabDatosGenerales());

        if (contratoDesembolsoBean.getGuardaTab()) {
            if (contratoDesembolsoBean.getTabDatosGenerales()) {
                logger.warning("sale y guarda Datos generales");
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_DATOS_GENERALES);
                Integer modalidad=1;
                actualizaDatosGenerales(modalidad, Boolean.FALSE);
                contratoDesembolsoBean.setGuardaTab(Boolean.TRUE); //Metodo para guardar el tab con la informacion incluyendo validaciones en caso de tenerlo
                //contratoDesembolsoBean.setTabDatosGenerales(Boolean.FALSE);
                configurarCambioTab();
            } else {
                logger.warning("inicia Datos generales");
                contratoDesembolsoBean.setTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                recargaDatosGenerales();
            }
        } else {
            tabDatosUI.setDisclosed(false);
            contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.FALSE);
            contratoDesembolsoBean.setGuardaTab(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tabDatosUI);
            switch (contratoDesembolsoBean.getValorAnteriorTab()) {
            case FenixConstants.TAB_ASIGNACION_RECURSOS:
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                logger.warning("regresa Asignacion de recursos");
                tabAsignacionUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabAsignacionUI);
                break;
            case FenixConstants.TAB_CONDICIONES_FINANCIERAS:
                contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa condiciones financieras");
                tabCondicionesUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCondicionesUI);
                break;
            case FenixConstants.TAB_CARGOS:
                contratoDesembolsoBean.setDisclosedTabCargos(Boolean.TRUE);
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                logger.warning("regresa cargos");
                tabCargosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCargosUI);
                break;
            case FenixConstants.TAB_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabTransferencias(Boolean.TRUE);
                logger.warning("regresa a transferencias");
                tabTransferenciasUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabTransferenciasUI);
                break;
            case FenixConstants.TAB_LIQUIDAR_CONTRATO:
                contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.TRUE);
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
                logger.warning("regresa liquidar contrato");
                tabLiquidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabLiquidarUI);
                break;
            case FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
                logger.warning("regresa a consolidar transferencias");
                tabConsolidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabConsolidarUI);
                break;
            case FenixConstants.TAB_RESERVA_FONDOS:
                contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.TRUE);
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                logger.warning("regresa a reserva fondos");
                tabReservaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabReservaUI);
                break;
            case FenixConstants.TAB_COBERTURAS:
                contratoDesembolsoBean.setDisclosedTabCoberturas(Boolean.TRUE);
                contratoDesembolsoBean.setTabCoberturas(Boolean.TRUE);
                logger.warning("regresa a cobertura");
                tabCoberturaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCoberturaUI);
                break;
            default:
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_DATOS_GENERALES);
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setTabDatosGenerales(Boolean.TRUE);
                break;
            }
        }
        // Add event code here...
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        // Add event code here...
        logger.warning("termina metodo cambioTabDatos");
    }

    public void cambioTabAsignacion(DisclosureEvent disclosureEvent) {
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        disclosureEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        logger.warning("Entra a Asignacion de recursos");
        logger.warning("guardo? " + contratoDesembolsoBean.getGuardaTab());
        logger.warning("Tab anterior " + contratoDesembolsoBean.getValorAnteriorTab());
        logger.warning("Tab asignacion " + contratoDesembolsoBean.getTabAsignacionRecursos());

        if (contratoDesembolsoBean.getGuardaTab()) {
            if (contratoDesembolsoBean.getTabAsignacionRecursos()) {
                logger.warning("sale y guarda asignacion de recursos");
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_ASIGNACION_RECURSOS);
                contratoDesembolsoBean.setGuardaTab(guardarAsignacionRecursos());
                contratoDesembolsoBean.setGuardaTab(Boolean.TRUE); //se invoca metodo para guardar los datos del tab
                //contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.FALSE);
                configurarCambioTab();
            } else {
                logger.warning("inicia asignacion de recursos");
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                recargarAsignacionRecursos();
            }
        } else {
            tabAsignacionUI.setDisclosed(false);
            contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.FALSE);
            contratoDesembolsoBean.setGuardaTab(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tabAsignacionUI);
            switch (contratoDesembolsoBean.getValorAnteriorTab()) {
            case FenixConstants.TAB_DATOS_GENERALES:
                contratoDesembolsoBean.setTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                logger.warning("regresa datos generales");
                tabDatosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabDatosUI);
                break;
            case FenixConstants.TAB_CONDICIONES_FINANCIERAS:
                contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa condiciones financieras");
                tabCondicionesUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCondicionesUI);
                break;
            case FenixConstants.TAB_CARGOS:
                contratoDesembolsoBean.setDisclosedTabCargos(Boolean.TRUE);
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                logger.warning("regresa cargos");
                tabCargosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCargosUI);
                break;
            case FenixConstants.TAB_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabTransferencias(Boolean.TRUE);
                logger.warning("regresa a transferencias");
                tabTransferenciasUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabTransferenciasUI);
                break;
            case FenixConstants.TAB_LIQUIDAR_CONTRATO:
                contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.TRUE);
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
                logger.warning("regresa liquidar contrato");
                tabLiquidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabLiquidarUI);
                break;
            case FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
                logger.warning("regresa a consolidar transferencias");
                tabConsolidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabConsolidarUI);
                break;
            case FenixConstants.TAB_RESERVA_FONDOS:
                contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.TRUE);
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                logger.warning("regresa a reserva fondos");
                tabReservaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabReservaUI);
                break;
            case FenixConstants.TAB_COBERTURAS:
                contratoDesembolsoBean.setDisclosedTabCoberturas(Boolean.TRUE);
                contratoDesembolsoBean.setTabCoberturas(Boolean.TRUE);
                logger.warning("regresa a cobertura");
                tabCoberturaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCoberturaUI);
                break;
            default:
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_ASIGNACION_RECURSOS);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                break;
            }
        }
        // Add event code here...
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        // Add event code here...
    }
    
    public void configurarCambioTab(){
        logger.warning("Inicia metodo configurarCambioTab");
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.FALSE);
        contratoDesembolsoBean.setTabDatosGenerales(Boolean.FALSE);
        contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.FALSE);
        contratoDesembolsoBean.setTabTransferencias(Boolean.FALSE);
        contratoDesembolsoBean.setTabCargos(Boolean.FALSE);
        contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.FALSE);
        contratoDesembolsoBean.setTabLiquidarContrato(Boolean.FALSE);
        contratoDesembolsoBean.setTabReservaFondos(Boolean.FALSE);
        contratoDesembolsoBean.setTabCoberturas(Boolean.FALSE);
        logger.warning("Termina metodo configurarCambioTab");
    }
    
    public Boolean guardarAsignacionRecursos(){
        logger.warning("Inicia metodo guardarAsignacionRecursos");
        Boolean resultado = Boolean.FALSE;
        Integer modoEjecucionAsignacionRecursos = null;
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        modoEjecucionAsignacionRecursos = contratoDesembolsoBean.getModoEjecucionTabAsignacionRecursos();
        if(null != modoEjecucionAsignacionRecursos){
            if(!modoEjecucionAsignacionRecursos.equals(FenixConstants.MODO_EJECUCION_LECTURA)){
                try{
                    resultado = execute(null, "actualizarDatosContratoDesembolso");
                }catch(Exception e){
                    logger.warning("ERROR al ejecutar la actualizacion de Asignacion de recursos.", e);
                }
            }
        }else{
            logger.warning("El modo ejecucion de Asignacion de Recursos es NULL.");
        }
        
        logger.warning("Resultado del guardado de asignacion de recursos: " + resultado);
        logger.warning("Termina guardarAsignacionRecursos");
        return resultado;
    }
    
    public Boolean guardarContratoTransferencias(){
        logger.warning("Inicia metodo guardarContratoTransferencias.");
        
        Boolean resultado = Boolean.FALSE;
        
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding =
                bindings.getOperationBinding("actualizarDatosContratoDesembolso");
            logger.warning("Ejecutando OperationBinding de actualizarDatosContratoDesembolso");
            resultado = (Boolean) operationBinding.execute();

            if (operationBinding.getErrors().isEmpty()) {
                logger.warning("El operationBinding actualizarDatosContratoDesembolso se ejecuto correctamente");
                if (resultado) {
                    logger.warning("La actualizacion del contrato se realizo correctamente");
                } else {
                    logger.warning("La actualizacion NO se realizo correctamente");
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIOS_MSG"));
                }
            } else {
                logger.warning("El operationBinding actualizarDatosContratoDesembolso devuelve errores" +
                               operationBinding.getErrors().toString());
            }
        } catch (Exception e) {
            logger.warning("Error al ejecutar el OperationBinding actualizarDatosContratoDesembolso", e);
        }
        
        logger.warning("Termina metodo guardarContratoTransferencias.");
        return resultado;
    }

    public void setTabLiquidarUI(RichShowDetailItem tabLiquidarUI) {
        this.tabLiquidarUI = tabLiquidarUI;
    }

    public RichShowDetailItem getTabLiquidarUI() {
        return tabLiquidarUI;
    }

    public void cambioTabLiquidar(DisclosureEvent disclosureEvent) {
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        disclosureEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        logger.warning("Entra a liquidar contrato");
        logger.warning("guardo? " + contratoDesembolsoBean.getGuardaTab());
        logger.warning("Tab anterior " + contratoDesembolsoBean.getValorAnteriorTab());
        logger.warning("Tab liquidar contrato " + contratoDesembolsoBean.getTabLiquidarContrato());

        if (contratoDesembolsoBean.getGuardaTab()) {
            if (contratoDesembolsoBean.getTabLiquidarContrato()) {
                logger.warning("sale y guarda liquidar contrato");
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_LIQUIDAR_CONTRATO);
                contratoDesembolsoBean.setGuardaTab(Boolean.TRUE); //Metodo para guardar el tab con la informacion incluyendo validaciones en caso de tenerlo
                //contratoDesembolsoBean.setTabLiquidarContrato(Boolean.FALSE);
                configurarCambioTab();
            } else {
                logger.warning("inicia liquidar contrato");
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
            }
        } else {
            tabLiquidarUI.setDisclosed(false);
            contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.FALSE);
            contratoDesembolsoBean.setGuardaTab(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tabLiquidarUI);
            switch (contratoDesembolsoBean.getValorAnteriorTab()) {
            case FenixConstants.TAB_ASIGNACION_RECURSOS:
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                logger.warning("regresa Asignacion de recursos");
                tabAsignacionUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabAsignacionUI);
                break;
            case FenixConstants.TAB_DATOS_GENERALES:
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa datos generales");
                tabDatosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabDatosUI);
                break;
            case FenixConstants.TAB_CONDICIONES_FINANCIERAS:
                contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa condiciones financieras");
                tabCondicionesUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCondicionesUI);
                break;
            case FenixConstants.TAB_CARGOS:
                contratoDesembolsoBean.setDisclosedTabCargos(Boolean.TRUE);
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                logger.warning("regresa a cargos");
                tabCargosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCargosUI);
                break;
            case FenixConstants.TAB_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabTransferencias(Boolean.TRUE);
                logger.warning("regresa a transferencias");
                tabTransferenciasUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabTransferenciasUI);
            break;
            case FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
                logger.warning("regresa a consolidar transferencias");
                tabConsolidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabConsolidarUI);
                break;
            case FenixConstants.TAB_RESERVA_FONDOS:
                contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.TRUE);
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                logger.warning("regresa a reserva fondos");
                tabReservaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabReservaUI);
                break;
            case FenixConstants.TAB_COBERTURAS:
                contratoDesembolsoBean.setDisclosedTabCoberturas(Boolean.TRUE);
                contratoDesembolsoBean.setTabCoberturas(Boolean.TRUE);
                logger.warning("regresa a cobertura");
                tabCoberturaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCoberturaUI);
                break;
            default:
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_LIQUIDAR_CONTRATO);
                contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.TRUE);
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
                break;
            }
        }
        // Add event code here...
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        // Add event code here...
    }

    public void cambioTabConsolidar(DisclosureEvent disclosureEvent) {
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        disclosureEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        logger.warning("Entra a consolidar trnasferencias");
        logger.warning("guardo? " + contratoDesembolsoBean.getGuardaTab());
        logger.warning("Tab anterior " + contratoDesembolsoBean.getValorAnteriorTab());
        logger.warning("Tab consolidar transferencias " + contratoDesembolsoBean.getTabConsolidarTransferencias());

        if (contratoDesembolsoBean.getGuardaTab()) {
            if (contratoDesembolsoBean.getTabConsolidarTransferencias()) {
                logger.warning("sale y guarda consolidar transferencias");
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS);
                contratoDesembolsoBean.setGuardaTab(Boolean.TRUE); //Metodo para guardar el tab con la informacion incluyendo validaciones en caso de tenerlo
                //contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.FALSE);
                configurarCambioTab();
            } else {
                logger.warning("inicia consolidar transferencias");
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
            }
        } else {
            tabConsolidarUI.setDisclosed(false);
            contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.FALSE);
            contratoDesembolsoBean.setGuardaTab(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tabConsolidarUI);
            switch (contratoDesembolsoBean.getValorAnteriorTab()) {
            case FenixConstants.TAB_ASIGNACION_RECURSOS:
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                logger.warning("regresa Asignacion de recursos");
                tabAsignacionUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabAsignacionUI);
                break;
            case FenixConstants.TAB_DATOS_GENERALES:
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa datos generales");
                tabDatosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabDatosUI);
                break;
            case FenixConstants.TAB_CONDICIONES_FINANCIERAS:
                contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa condiciones financieras");
                tabCondicionesUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCondicionesUI);
                break;
            case FenixConstants.TAB_CARGOS:
                contratoDesembolsoBean.setDisclosedTabCargos(Boolean.TRUE);
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                logger.warning("regresa a cargos");
                tabCargosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCargosUI);
                break;
            case FenixConstants.TAB_LIQUIDAR_CONTRATO:
                contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.TRUE);
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
                logger.warning("regresa liquidar contrato");
                tabLiquidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabLiquidarUI);
                break;
            case FenixConstants.TAB_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabTransferencias(Boolean.TRUE);
                logger.warning("regresa a transferencias");
                tabTransferenciasUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabTransferenciasUI);
            break;
            case FenixConstants.TAB_RESERVA_FONDOS:
                contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.TRUE);
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                logger.warning("regresa a reserva fondos");
                tabReservaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabReservaUI);
                break;
            case FenixConstants.TAB_COBERTURAS:
                contratoDesembolsoBean.setDisclosedTabCoberturas(Boolean.TRUE);
                contratoDesembolsoBean.setTabCoberturas(Boolean.TRUE);
                logger.warning("regresa a cobertura");
                tabCoberturaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCoberturaUI);
                break;
            default:
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS);
                contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
                break;
            }
        }
        // Add event code here...
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        // Add event code here...
    }
    
    public void cambioTabReservaFondos(DisclosureEvent disclosureEvent) {
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        disclosureEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        logger.warning("Entra a reserva de fondos");
        logger.warning("guardo? " + contratoDesembolsoBean.getGuardaTab());
        logger.warning("Tab anterior " + contratoDesembolsoBean.getValorAnteriorTab());
        logger.warning("Tab reserva de fondos " + contratoDesembolsoBean.getTabReservaFondos());

        if (contratoDesembolsoBean.getGuardaTab()) {
            if (contratoDesembolsoBean.getTabReservaFondos()) {
                logger.warning("sale y guarda reserva de fondos");
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_RESERVA_FONDOS);  //tab de reserva de fondos
                contratoDesembolsoBean.setGuardaTab(Boolean.TRUE); //Metodo para guardar el tab con la informacion incluyendo validaciones en caso de tenerlo
              //  contratoDesembolsoBean.setTabReservaFondos(Boolean.FALSE);
                configurarCambioTab();
            } else {
                logger.warning("inicia reserva de fondos");
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                //recarga de transferencias
                AdfFacesContext.getCurrentInstance().addPartialTarget(regionReservaUI);
                regionReservaUI.refresh(FacesContext.getCurrentInstance());
            }
        } else {
            tabReservaUI.setDisclosed(false);
            contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.FALSE);
            contratoDesembolsoBean.setGuardaTab(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tabReservaUI);
            switch (contratoDesembolsoBean.getValorAnteriorTab()) {
            case FenixConstants.TAB_ASIGNACION_RECURSOS:
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                logger.warning("regresa Asignacion de recursos");
                tabAsignacionUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabAsignacionUI);
                break;
            case FenixConstants.TAB_DATOS_GENERALES:
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa datos generales");
                tabDatosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabDatosUI);
                break;
            case FenixConstants.TAB_CONDICIONES_FINANCIERAS:
                contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa condiciones financieras");
                tabCondicionesUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCondicionesUI);
                break;
            case FenixConstants.TAB_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                logger.warning("regresa a transferencias");
                tabTransferenciasUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabTransferenciasUI);
                break;
            case FenixConstants.TAB_CARGOS:
                contratoDesembolsoBean.setDisclosedTabCargos(Boolean.TRUE);
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                logger.warning("regresa a cargos");
                tabCargosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCargosUI);
                break;
            case FenixConstants.TAB_LIQUIDAR_CONTRATO:
                contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.TRUE);
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
                logger.warning("regresa liquidar contrato");
                tabLiquidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabLiquidarUI);
                break;
            case FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
                logger.warning("regresa a consolidar transferencias");
                tabConsolidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabConsolidarUI);
                break;
            default:
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_RESERVA_FONDOS);
                contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.TRUE);
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                break;
            }
        }
        // Add event code here...
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        // Add event code here...
    }

    public void setTabConsolidarUI(RichShowDetailItem tabConsolidarUI) {
        this.tabConsolidarUI = tabConsolidarUI;
    }

    public RichShowDetailItem getTabConsolidarUI() {
        return tabConsolidarUI;
    }


    public void actualizarContratoDesembolso(ActionEvent actionEvent) {       
       logger.warning("Inicia metodo actualizarContratoDesembolso");
        
        BigDecimal montoDesembolsarAttr = null;
        Integer idTipoMonedaAttr = null;
        Date fechaEstimadaDesembolsarAttr = null;
        String fondoAttr = null;
        boolean actualizarContrato = Boolean.TRUE;
        Boolean validarDatosObligatorios = Boolean.FALSE;
        
        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}")) {
            
               Long idContrato = (Long)JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}");
                ContratoDesembolsosBean contratoBean =
                    (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
                 
                     contratoBean.montoProgramadoMesVig(idContrato);
            
            try {
                if (null != ADFUtils.getBoundAttributeValue("MontoDesembolsar")) {
                    montoDesembolsarAttr = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoDesembolsar");
                    logger.warning("El montoDesembolsar es: " + montoDesembolsarAttr);
                } else {
                    logger.warning("MontoDesembolsado es NULL");
                }
            } catch (Exception e) {
                logger.warning("Error al obtener el binding de MontoDesembolsar ", e);
            }

            try {
                if (null != ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda") &&
                    !"".equals(ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda").toString())) {
                    idTipoMonedaAttr = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
                    logger.warning("El tipoMoneda es: " + idTipoMonedaAttr);
                } else {
                    logger.warning("tipoMoneda es NULL");
                }
            } catch (Exception e) {
                logger.warning("Error al obtener el binding de IdTcaTipoMoneda ", e);
            }

            try {
                if (null != ADFUtils.getBoundAttributeValue("FechaEstimadaDesembolsar")) {
                    fechaEstimadaDesembolsarAttr = (Date) ADFUtils.getBoundAttributeValue("FechaEstimadaDesembolsar");
                    logger.warning("La fechaEstimadaDesembolsar es: " + fechaEstimadaDesembolsarAttr);
                } else {
                    logger.warning("El fechaEstimadaDesembolsar es: " + fechaEstimadaDesembolsarAttr);
                }
            } catch (Exception e) {
                logger.warning("Error al obtener el binding de FechaEstimadaDesembolsar ", e);
            }
            
            try {
                if (null != ADFUtils.getBoundAttributeValue("Fondo")) {
                    fondoAttr = (String) ADFUtils.getBoundAttributeValue("Fondo");
                    logger.warning("El fondo es: " + fondoAttr);
                } else {
                    logger.warning("El fondo es NULL.");
                }
            } catch (Exception e) {
                logger.warning("Error al obtener el binding de Fondo ", e);
            }
            
            // Obtenemos el monto asignado total
            Boolean montoCorrespondeContrato = getMontoTotal(montoDesembolsarAttr);
            
            /* if(null != montoTotal){
                if (null != montoDesembolsarAttr) {
                    if (montoTotal.compareTo(montoDesembolsarAttr)==0) {
                        montoCorrespondeContrato = Boolean.TRUE;
                    }
                }else{
                    logger.warning("montoDesembolsarAttr o montoTotal son NULL.");
                }
            }else{
                logger.warning("El montoTotal de la asignacion de recursos es NULL.");
                montoCorrespondeContrato = Boolean.TRUE;
            } */
            
            validarDatosObligatorios = null != montoDesembolsarAttr && null != idTipoMonedaAttr && null != fechaEstimadaDesembolsarAttr 
                                       && (null != fondoAttr && !fondoAttr.equals(""));
            logger.warning("ValidarDatosObligatorios: " + validarDatosObligatorios);
            if (validarDatosObligatorios && montoCorrespondeContrato) {
                logger.warning("Datos requeridos completos");
                Boolean resultado = null;
                
                asignartransientAProgramado();
                asignarTransientAFondo();
                asignarTransientAIdTcaTipoMoneda();
                asignarTransientAFechaEstimadaDispRecursos();
                try {
                    BindingContainer bindings = ADFUtils.getBindingContainer();
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("actualizarDatosContratoDesembolso");
                    logger.warning("Ejecutando OperationBinding de precargaInformacionContrato");
                    resultado = (Boolean) operationBinding.execute();

                    if (operationBinding.getErrors().isEmpty()) {
                        logger.warning("El operationBinding se ejecuto correctamente");
                        if (resultado) {
                            logger.warning("La actualizacion del contrato se realizo correctamente");

                            logger.warning("Actualizando bandera de cambios pendientes en AsignacionRecursos.");
                            try{
                                execute(null, "asignarCambioPendienteFalso");
                            }catch(Exception e){
                                logger.warning("ERROR al ejecutar operBinding asignarCambioPendienteFalso.", e);
                            }
                        } else {
                            logger.warning("La actualizacion NO se realizo correctamente");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIOS_MSG"));
                        }
                    } else {
                        logger.warning("El operationBinding devuelve errores" +
                                       operationBinding.getErrors().toString());
                    }
                } catch (Exception e) {
                    logger.warning("Error al ejecutar el OperationBinding", e);
                }
            
            //Aplica guardado de datos generales y cargos
                if(contratoBean.getRenderTabCargos().compareTo(1)==0){
                    //  guardado de cargos
                    actualizarCargos();
                    }
                if(contratoBean.getRenderTabDatosGenerales().compareTo(1)==0){
                    //  guardado de datos generales
                    Integer modalidades=1;
                    actualizaDatosGenerales(modalidades, Boolean.FALSE);
                    }

            } else {
                actualizarContrato = Boolean.FALSE;
                if (!validarDatosObligatorios) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("DATOS_REQ_ERROR_MSG"));
                    logger.warning("Datos requeridos NO completos");
                }
                
                if(!montoCorrespondeContrato) {
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ASIGNACION_DE_RECURSOS_NO_VALIDA"));
                    logger.warning("El monto asignado total no corresponde con su contrato");
                }
            }
           
                Long idCondicionFinanciera = (Long)JSFUtils.getFromSession("idCondicionFinanciera");
                if(idCondicionFinanciera!=null){
                    System.out.println("idCondicionFinanciera --> "+idCondicionFinanciera);
    
                    Map hash_map = new HashMap();
                    hash_map.put("idCondicionFinanciera",idCondicionFinanciera);
                    System.out.println("Mappings : " + hash_map); 
                    
                    BindingContainer bindings = getBindings();
                    OperationBinding guardar = bindings.getOperationBinding("guardarCalendarioComponente");
                    guardar.getParamsMap().put("map", hash_map);
                    guardar.execute();
                    cargarCFTemp();
                }
                
            logger.warning("Valida y comienza guardado de datos generales ");
            try
            {
                //Se valida si la actividad economica esta null, se cargan los datos y se guardan los datos generales
                DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding iter = bindings.findIteratorBinding("DatosGeneralesDesembolsoVOIterator");
                iter.executeQuery();
                
                logger.warning("Actividad Economica " + JSFUtils.resolveExpression("#{bindings.IdActividadEconomica.inputValue}"));
                if(null == JSFUtils.resolveExpression("#{bindings.IdActividadEconomica.inputValue}"))
                {
                    precargaDatosGenerales();
                    //  guardado de datos generales
                    Integer modalidades=1;
                    actualizaDatosGenerales(modalidades, Boolean.FALSE);
                }
            }
            catch(Exception ex)
            {
                logger.warning("Error al actualizar, guardar datos generales desde condiciones financieras.");
                logger.warning(ex.getCause());
                logger.warning(ex.getMessage());
            }
        }
        
        //Validamos si se debe actualizar el contrato
        if (actualizarContrato) {
            // se recarga el tree y los montos de la solicitud
            
            Long pIdSolicitudDesembolso = null;
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitudDesembolso}") != null){
                 pIdSolicitudDesembolso =  new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitudDesembolso}").toString());     
            }else{
                logger.warning("Error al castear parametro pIdSolicitudDesembolso");
            }
            
            Long pIdOperacion = null;
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){        
                pIdOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            }else{
                logger.warning("IdOperacion es NULL");
            }
            
            if(pIdOperacion != null || pIdSolicitudDesembolso != null ){
                    inicializarTree(pIdOperacion, pIdSolicitudDesembolso);
                    obtenerSumaContratosSolicitud(pIdSolicitudDesembolso);
            }else{
                logger.warning("No es posible recargar el Tree y los datos de solicitud, los parametros requeridos son null");
            }
            
            validarRowContratoCorrespondeContratoTaskFlow();
            ADFUtils.dispararContextualEvent("onClickGuardarContratoEventBinding", Boolean.TRUE);
           FacesContext fctx = FacesContext.getCurrentInstance();
           String page = fctx.getViewRoot().getViewId();
           ViewHandler ViewH = fctx.getApplication().getViewHandler();
           UIViewRoot UIV = ViewH.createView(fctx, page);
           UIV.setViewId(page);
           fctx.setViewRoot(UIV);
        }
        
        logger.warning("Termina metodo actualizarContratoDesembolso");
    }
    
    public void precargaDatosGenerales() {
            logger.warning("Inicia precarga de datos generales");
            cargarTipoTasa();
            cargaDatosDesembolso();
            consultarDatosContratoDesembolso();

            logger.warning("termina precarga de datos generales");
        }
    
    public void cargarTipoTasa() {
            logger.warning("*Inf, Inicia metodo cargarTipoTasa ");

            Long idOperacion =
                (null == JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")) ? null :
                (Long) JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");

            Long idDesembolso =
                (null == JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}")) ? null :
                (Long) JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}");


            if (idOperacion == null || idDesembolso == null) {
                logger.warning("*Inf, Importante parametros requeridos resuletos a null");
                logger.warning("idOperacion: " + idOperacion);
                logger.warning("idDesembolso: " + idDesembolso);
            } else {


                try {

                    BindingContainer binding = ADFUtils.getBindingContainer();
                    OperationBinding operBinding = binding.getOperationBinding("crearRow");
                    operBinding.getParamsMap().put("idContratoDesembolso", idDesembolso);
                    operBinding.getParamsMap().put("idOperacion", idOperacion);
                    operBinding.execute();

                    if (!operBinding.getErrors().isEmpty()) {
                        logger.warning("***Error, al ejecutar operation Binding crearRow  en condiciones financieras ->" +
                                       operBinding.getErrors());
                    }

                } catch (Exception e) {
                    logger.log(ADFLogger.WARNING, "Error en setCurrentRow", e);
                }
            }


            logger.warning("*Inf, Termina metodo cargarTipoTasa ");
        }
            
       public void cargaDatosDesembolso() {
            logger.warning("Inicia metodo cargaDatosDesembolso");

            try {
                BindingContainer binding = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("cargaDatosDesembolso");
                operBinding.execute();

            } catch (Exception e) {
                logger.warning("!Ha ocurrido un error al ejecutar el operBinding cargarDatosDesembolso ->", e);
            }

            logger.warning("Termina metodo cargaDatosDesembolso");
        }
       
       public void consultarDatosContratoDesembolso() {
            logger.warning("\n=== Se ejecuta consultarDatosContratoDesembolso ===");
            Long idClasificacion = null;
            boolean banConsulta = false;
            Integer idOperacion = Integer.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());

            DCBindingContainer bindingsCid = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iteratorCid = bindingsCid.findIteratorBinding("ConsultarIDContratoDesembolsoVO1Iterator");
            ViewObject voCid = iteratorCid.getViewObject();
            RowSetIterator rsiCid = voCid.createRowSetIterator(null);
            logger.warning("=============== voCid size:" + rsiCid.getRowCount());
            if (rsiCid.getRowCount() > 0) {
                while (rsiCid.hasNext()) {
                    Row row = rsiCid.next();
                    idClasificacion = Long.valueOf(row.getAttribute("IdClasificacionEstrategica").toString());
                    logger.warning("=============== IdClasificacionEstrategica:" + idClasificacion);
                }

                DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding iterator =
                    bindings.findIteratorBinding("DatosDesembolsoClasificacionEstrategicaVO1Iterator");
                ViewObject vo = iterator.getViewObject();
                RowSetIterator rsi = vo.createRowSetIterator(null);
                //  RowSetIterator rsi = iterator.getRowSetIterator();
                logger.warning("=============== valoresVO size:" + rsi.getRowCount());
                while (rsi.hasNext()) {
                    Row row = rsi.next();
                    Long idClasificacionRow =
                        row.getAttribute("IdClasificacion") != null ?
                        Long.valueOf(row.getAttribute("IdClasificacion").toString()) : 0;
                    logger.warning("=== id Clasificacion: " + idClasificacionRow + "|  IdClasificacionEstrategica:" +
                                   idClasificacion);
                    if (idClasificacion.equals(idClasificacionRow)) {
                        logger.warning("=============== ENTRA IF ===>> ID's IGUALES");
                        String nombre = row.getAttribute("NombreDelComponente").toString();
                        String decripcion = row.getAttribute("DescripcionDelComponente").toString();
                        String porcentaje = row.getAttribute("Porcentaje").toString();
                        String actividadEconomica = row.getAttribute("ActividadEconomica").toString();
                        String areaFocalizacion = row.getAttribute("AreaFocalizacion").toString();
                        String ejeEstrategico = row.getAttribute("EjeEstrategico").toString();

                        JSFUtils.setExpressionValue("#{pageFlowScope.nombreCE}", nombre);
                        JSFUtils.setExpressionValue("#{pageFlowScope.descripcionCE}", decripcion);
                        JSFUtils.setExpressionValue("#{pageFlowScope.porcentajeCE}", porcentaje);
                        JSFUtils.setExpressionValue("#{pageFlowScope.actividadEconomicaCE}", actividadEconomica);
                        JSFUtils.setExpressionValue("#{pageFlowScope.areaFocalizacionCE}", areaFocalizacion);
                        JSFUtils.setExpressionValue("#{pageFlowScope.ejeEstrategicoCE}", ejeEstrategico);

                    }
                }
                rsi.closeRowSetIterator();
            } else {
                // JSFUtils.addFacesErrorMessage("Error al consultar información de Contrato Desembolso");
                logger.warning("Termina metodo consultarDatosDesembolsoClasificacionEstrategica el valor de ID CLASIFICACION es nulo.");
            }
            
            rsiCid.closeRowSetIterator();
            logger.warning("Termina metodo consultarDatosDesembolsoClasificacionEstrategica");
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
    
//    public List<String> validarCondiciones(){
//            logger.warning("Inicia metodo validarCondiciones");
//            Long pIdOperacion = null;
//            List<String> listaErrores = new ArrayList<String>();
//            //Omite la validacion temporalmente
//            
//            Map<String, Object> params = new HashMap<String, Object>();
//            try{
//            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){        
//                pIdOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
//                params.put("idOperacion", pIdOperacion);
//            }else{
//                logger.warning("IdOperacion es NULL");
//            }
//            }catch(Exception ex){
//                logger.warning("Error al obtener la operacion" , ex.getMessage());
//            }
//            Long pIdContratoDesembolso=null;
//            try{
//            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}")){
//            pIdContratoDesembolso =
//            new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}").toString());
//            params.put("idDesembolso", pIdContratoDesembolso);
//            }else{
//                logger.warning("IdContrato es NULL");
//            }
//            }catch(Exception ep){
//                    logger.warning("Error al obtener la operacion" , ep.getMessage());
//                }
//                logger.warning("Invocando metodo para campos de condiciones");
//                try{
//                    listaErrores = execute(params, "validarCamposCondiciones");
//                    logger.warning("Registros de la lista de errores: " + listaErrores.size());
//                }catch(Exception e){
//                    logger.warning("Error al ejecutar el operationBinding validarCondiciones.", e);
//                    listaErrores.add("Error al ejecutar la validacion de los campos en condiciones financieras");
//                }
//           // */
//            logger.warning("Termina metodo validarCondiciones");
//            return listaErrores;
//        }
    
    public Map validarCondiciones(){
            logger.warning("Inicia metodo validarCondiciones");;
            
            Map<String, Object> params = new HashMap<String, Object>();
            Map<String, Object> result = new HashMap<String, Object>();
            
            Long pIdContratoDesembolso=null;
            try{
                if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}")){
                    pIdContratoDesembolso = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}").toString());
                    logger.warning("IdContrato: "+pIdContratoDesembolso);
                    params.put("idContrato", pIdContratoDesembolso);
                }
                else{
                    logger.warning("IdContrato es NULL");
                }
            }catch(Exception ep){
                    logger.warning("Error al obtener la operacion" , ep.getMessage());
            }
            
            logger.warning("Invocando metodo para campos de condiciones");
                
            try{
                result = execute(params, "buscarCondicionFinancieraPorIdContrato");
            }catch(Exception e){
                logger.warning("Error al ejecutar el operationBinding validarCondiciones.", e);
                result.put("mjeError", "Error al ejecutar la validacion de los campos en condiciones financieras");
            }
            
            logger.warning("Termina metodo validarCondiciones");
            return result;
    }

    public void validarDatosIniciarProcesoDesembolso(ActionEvent actionEvent) {
        logger.warning("Inicia metodo validarDatosIniciarProcesoDesembolso");
        Boolean sinSalidaFondos = false ;
        String origenTransferencia = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("origenTransferencia").toString();
        
        logger.warning("origenTransferencia: " + origenTransferencia);
        if(origenTransferencia.equals("SIN_SALIDA_FONDOS")){
            sinSalidaFondos = true;
            logger.warning("origenTransferencia: " + origenTransferencia);
        }
        
        Boolean camposDatos=validarDatosGeSinGuardar();       //Se omite validacion a peticion de Mariano para FNXII-6323
        Boolean montoDisponibleTransferir =  false;
        Boolean cuentaClienteNoNull=ValidarCuentaClienteNoNull();
        
        if(origenTransferencia.equals("SIN_SALIDA_FONDOS")){
            montoDisponibleTransferir = true;
        }
        else
        {
            montoDisponibleTransferir =  validarMontoDisponibleTransferir();    
        }
        
        logger.warning("montoDisponibleTransferir: " + montoDisponibleTransferir);
        logger.warning("camposDatos: " + camposDatos);
        logger.warning("cuentaClienteNoNull: " + cuentaClienteNoNull);
        
        if(montoDisponibleTransferir && camposDatos && cuentaClienteNoNull ){
                /*
                List<String> listaCondiciones = new ArrayList<String>();
                listaCondiciones = validarCondiciones();      //Se omite validacion a peticion de Mariano para FNXII-6323
                if (listaCondiciones.size() > 0) {
                    for (String mensajeCondicion : listaCondiciones) {
                        logger.warning("Obteniendo errores de validacion de Condicion.");
                        JSFUtils.addFacesErrorMessage(mensajeCondicion);
                    }
                */
                Map resultado = new HashMap<String, Object>();
                resultado = validarCondiciones();
                Boolean esValidado = (Boolean)resultado.get("esValidado");
                if(!esValidado){
                        Iterator it = resultado.entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry e = (Map.Entry)it.next();
                            if(null != e.getValue()){
                                String key = (String)e.getKey();
                                logger.warning("Valor del KEY :" + key);
                                if(key.equalsIgnoreCase("esValidado")){
                                    logger.warning("El valor es Boolean");
                                }
                                else{
                                    logger.warning("mensaje de error :" + (String)e.getValue());
                                    JSFUtils.addFacesErrorMessage((String)e.getValue());
                                }
                            }
                        }
                        it.remove();

                } else {
                    if (calcularPlazosPorEspecificacionFechas()) {

                        Boolean esValido = Boolean.TRUE;
                        List<String> errorBundleList = new ArrayList<String>();
                        String errorBundle = null;
                        Integer programado = null;
                        Integer idTipoMoneda = null;
                        Integer idTipoMonedaLineaCredito = null;
                        setEsErrorLimiteTasa(Boolean.FALSE);
                        setEsErrorLimiteMonto(Boolean.FALSE);
                        Boolean errorMontos = Boolean.FALSE;

                        //Asigna PROGRAMA_OPERACION y DESTINO_FINANCIAMIENTO, solo si producto de operacion NO es IFI.


                        /* if(!asignarProgramaDestino()){
                                                return;
                                            } */

                        Long pIdOperacion = null;
                        if (JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null) {
                            try {
                                pIdOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
                                logger.warning("pIdOperacion: "+pIdOperacion);
                            } catch (Exception e) {
                                logger.warning("ERROR al obtener el idOperacion del TaskFlow.");
                            }
                        } else {
                            logger.warning("IdOperacion es NULL");
                        }
                        
                        try {
                            idTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
                        } catch (Exception e) {
                            logger.warning("ERROR al obtener el idTipoMoneda.");
                        }

                        try {
                            idTipoMonedaLineaCredito = this.getIdTipoMonedaLineaCredito();
                        } catch (Exception e) {
                            logger.warning("ERROR al obtener el idTipoMoneda.");
                        }

                        ContratoDesembolsosBean contratoBean =
                            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
                        
                        if (null != contratoBean) {

                            contratoBean.setEsErrorMontoLimite(Boolean.FALSE);
                            contratoBean.setEsMontoMinimo(Boolean.FALSE);
                            contratoBean.setEsMontoMaximo(Boolean.FALSE);
                            setEsErrorLimiteMonto(Boolean.FALSE);

                            BigDecimal montoDesembolsarContrato = null;

                            try {
                                logger.warning("Recuperando montoDesembolsar del contrato");
                                montoDesembolsarContrato =
                                    new BigDecimal(ADFUtils.getBoundAttributeValue("MontoDesembolsar").toString());
                            } catch (Exception e) {
                                logger.warning("Error a obtener montoDesembolsar del contrato.");
                            }
                            BigDecimal montoMinimo = null;
                            BigDecimal montoMaximo = null;
                            BigDecimal montoMinimoConvertido = null;
                            BigDecimal montoMaximoConvertido = null;
                            montoMinimo = contratoBean.getMontoMinimoDesembolso();
                            montoMaximo = contratoBean.getMontoMaximoDesembolso();

                            if (null != montoDesembolsarContrato) {

                                if (null != idTipoMoneda) {
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    BigDecimal montoConvertido = null;

                                    params.put("claveTipo", idTipoMoneda);
                                    params.put("codigoA", COD_EXT_MONEDA_LINEA_CREDITO);
                                    params.put("monto", montoDesembolsarContrato);
                                    try {
                                        montoConvertido = execute(params, "convertirMonedas");
                                        logger.warning("Monto convertido: " + montoConvertido);
                                        contratoBean.setMontoConvertido(montoConvertido);
                                    } catch (Exception e) {
                                        logger.warning("ERROR al convertir el monto a otro tipo de moneda.", e);
                                    }

                                    if (null != montoConvertido) {
                                        if (null != montoMinimo) {
                                            if(null != idTipoMonedaLineaCredito)
                                            {
                                                //se obtiene el montoMinimo convertido
                                                params = new HashMap<String, Object>();
                                                
                                                params.put("claveTipo", idTipoMonedaLineaCredito);
                                                params.put("codigoA", COD_EXT_MONEDA_LINEA_CREDITO);
                                                params.put("monto", montoMinimo);
                                                try {
                                                    montoMinimoConvertido = execute(params, "convertirMonedas");
                                                    logger.warning("Monto minimo convertido: " + montoMinimoConvertido);
                                                } catch (Exception e) {
                                                    logger.warning("ERROR al convertir el monto minimo a otro tipo de moneda.", e);
                                                }
                                                
                                                //se verifica el monto convertido no sea null
                                                if(null != montoMinimoConvertido)
                                                {
                                            logger.warning("Validando monto minimo");
                                            logger.warning("Monto minimo de la linea: " + montoMinimo);
                                                    logger.warning("Monto minimo convertido de la linea: " + montoMinimoConvertido);
                                            logger.warning("Monto a desembolsar del contrato: " + montoConvertido);
                                                    if (montoConvertido.compareTo(montoMinimoConvertido) == -1) {
                                                logger.warning("El monto a desembolsar del contrato es MENOR que el monto minimo de la linea");
                                                setEsErrorLimiteMonto(Boolean.TRUE);
                                                contratoBean.setEsErrorMontoLimite(Boolean.TRUE);
                                                contratoBean.setEsMontoMinimo(Boolean.TRUE);
                                            } else {
                                                logger.warning("El monto a desembolsar del contrato es MAYOR o IGUAL al monto minimo de la linea");
                                            }
                                                }
                                                else
                                                {
                                                    logger.warning("Monto Minimo convertido es NULL.");
                                                    esValido = Boolean.FALSE;
                                                }    
                                            }
                                            else
                                            {
                                                logger.warning("ERROR: el idTipoMonedaLineaCredito es NULL, se salta la validacion");
                                                esValido = Boolean.TRUE;
                                            }
                                        } else {
                                            logger.warning("ERROR: montoMinimo de linea es NULL, se salta la validacion");
                                            esValido = Boolean.TRUE;
                                        }

                                        if (null != montoMaximo) {
                                            if(null != idTipoMonedaLineaCredito)
                                            {
                                                //se obtiene el montoMaximo convertido
                                                params = new HashMap<String, Object>();
                                                
                                                params.put("claveTipo", idTipoMonedaLineaCredito);
                                                params.put("codigoA", COD_EXT_MONEDA_LINEA_CREDITO);
                                                params.put("monto", montoMaximo);
                                                try {
                                                    montoMaximoConvertido = execute(params, "convertirMonedas");
                                                    logger.warning("Monto maximo convertido: " + montoMaximoConvertido);
                                                } catch (Exception e) {
                                                    logger.warning("ERROR al convertir el monto maximo a otro tipo de moneda.", e);
                                                }
                                                
                                                //se verifica el monto convertido no sea null
                                                if(null != montoMaximoConvertido)
                                                {
                                            logger.warning("Validando monto maximo");
                                            logger.warning("Monto maximo de la linea: " + montoMaximo);
                                                    logger.warning("Monto maximo Convertido de la linea: " + montoMaximoConvertido);
                                            logger.warning("Monto a desembolsar del contrato: " + montoConvertido);
                                                    if (montoConvertido.compareTo(montoMaximoConvertido) == 1) {
                                                logger.warning("El monto a desembolsar del contrato es MAYOR que el monto maximo de la linea");
                                                contratoBean.setEsErrorMontoLimite(Boolean.TRUE);
                                                setEsErrorLimiteMonto(Boolean.TRUE);
                                                contratoBean.setEsMontoMaximo(Boolean.TRUE);
                                            } else {
                                                logger.warning("El monto a desembolsar del contrato es MENOR o IGUAL al monto maximo de la linea");
                                            }
                                                }
                                                else
                                                {
                                                    logger.warning("Monto Maximo convertido es NULL.");
                                                    esValido = Boolean.FALSE;
                                                }    
                                            }
                                            else
                                            {
                                                logger.warning("ERROR: el idTipoMonedaLineaCredito es NULL, se salta la validacion");
                                                esValido = Boolean.TRUE;
                                            }
                                        } else {
                                            logger.warning("ERROR: montoMaximo de linea es NULL, se salta la validacion");
                                            esValido = Boolean.TRUE;
                                        }
                                    } else {
                                        logger.warning("Monto convertido es NULL.");
                                        esValido = Boolean.FALSE;
                                    }
                                }
                            } else {
                                logger.warning("ERROR: Monto a desembolsar del contrato es NULL, no se pudo hacer la validacion");
                                esValido = Boolean.FALSE;
                            }
                        } else {
                            logger.warning("Instancia de ContratoDesembolsoManagedBean es NULL.");
                            esValido = Boolean.FALSE;
                        }
                        
                        if (esValido) {
                            logger.warning("Invocando metodo para validar la reglas de negocio.");
                            validarReglasNegocio(null);
                        } else {
                            logger.warning("La validacion de limite de montos no se realizo correctamente.");
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_MONTO_LIMITES_FALLIDA"));
                        }
                        
                        Long idCondicionFinanciera = (Long)JSFUtils.getFromSession("idCondicionFinanciera");
                        System.out.println("idCondicionFinanciera --> "+idCondicionFinanciera);
                        
                       
                        if(idCondicionFinanciera!=null){
                            Map hash_map = new HashMap();
                            hash_map.put("idCondicionFinanciera",idCondicionFinanciera);
                            System.out.println("Mappings : " + hash_map); 
                            
                            BindingContainer bindings = getBindings();
                            OperationBinding guardar = bindings.getOperationBinding("guardarCalendarioComponente");
                            guardar.getParamsMap().put("map", hash_map);
                            guardar.execute();
                            cargarCFTemp();
                        }
                        
                    } else {
                        logger.warning("Error, no se pudo calcular los plazos de la condicion financiera ");
                    }
                }
            
            }
        logger.warning("Termina metodo validarDatosIniciarProcesoDesembolso");
    }
    
    public void actualizarMontoMaximoMinimo(){
        logger.warning("Inicia metodo actualizarMontoMaximoMinimo");
        Long pIdOperacion = null;
        Map mapaMontos = new HashMap();
        BigDecimal montoMaximo = null;
        BigDecimal montoMinimo = null;
        
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
            try{
                pIdOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            }catch(Exception e){
                logger.warning("ERROR al obtener el idOperacion del TaskFlow.");
            }
        }else{
            logger.warning("IdOperacion es NULL");
        }
        
        BigDecimal montoDesembolsarContrato = null;

        try{
            logger.warning("Recuperando montoDesembolsar del contrato");
            montoDesembolsarContrato = new BigDecimal(ADFUtils.getBoundAttributeValue("MontoDesembolsar").toString());
        }catch(Exception e){
           logger.warning("Error a obtener montoDesembolsar del contrato."); 
        }
        
        ContratoDesembolsosBean contratoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("montoDesembolsarCOntrato", montoDesembolsarContrato);
        params.put("idOperacion", pIdOperacion);
        try{
            mapaMontos = execute(params, "actualizarMontoMaximoMinimoEnDesembolsos");
        }catch(Exception e){
            logger.warning("ERROR al actualizar el monto minimo o maximo del termino En desembolso.", e);
        }
        
        if(mapaMontos.size() > 0){
            try{
                montoMaximo = (BigDecimal) mapaMontos.get("MontoMaximoDesembolso");
            }catch(Exception e){
                logger.warning("ERROR al obtener monto maximo", e);
            }
            try{
                montoMinimo = (BigDecimal) mapaMontos.get("MontoMinimoDesembolso");
            }catch(Exception e){
                logger.warning("ERROR al obtener monto minimo", e);
            }
            
            contratoBean.setMontoMaximoDesembolso(montoMaximo);
            contratoBean.setMontoMinimoDesembolso(montoMinimo);
        }
        logger.warning("Termina metodo actualizarMontoMaximoMinimo");
    }
    
    private Integer getIdTipoMonedaLineaCredito()
    {
        logger.warning("Inicia metodo getIdTipoMonedaLineaCredito");
        
        Integer idTipoMoneda = null;
        String codExternoTipoMoneda = null;
        
        Long idLineaCredito = null;
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> paramsMoneda = new HashMap<String, Object>();
        Map resultMap = new HashMap();
        
        ContratoDesembolsosBean contratoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        
        idLineaCredito = contratoBean.getIdLinea();
        logger.warning("IdLineaCredito a consultar: " + idLineaCredito);
        
        if(null != idLineaCredito)
        {
            params.put("idLineaCredito", idLineaCredito);
            params.put("tipoMoneda", null);
            try{
                resultMap = execute(params, "consultarLineaCreditoByIdLineaCredito");
            }catch(Exception e){
                logger.warning("ERROR al ejecutar OperBinding consultarLineaCreditoByIdLineaCredito.", e);
            }
        }else{
            logger.warning("IdLineaCredito es NULL. No se puede obtener el monto disponible de la linea.");
        }
        
        if(null != resultMap && !resultMap.isEmpty() && resultMap.size() > 0){
            logger.warning("Recuperando codigoExterno de la linea de servicio.");
            try{
                codExternoTipoMoneda = (String) resultMap.get("moneda");
            }catch(Exception e){
                logger.warning("ERROR al recuperar codExternoMoneda de la linea.", e);
            }
            
            logger.warning("CodExterno Moneda de la linea: " + codExternoTipoMoneda);
        }else{
            logger.warning("resultMap no devuelve datos. No se puede recuperar el monto disponible de la linea.");
        }
        
        //Una vez que se tiene el codExternoMoneda se busca el idTipoMoneda
        if(codExternoTipoMoneda != null)
        {
            paramsMoneda.put("descripcionCorta", codExternoTipoMoneda);
            try{
                idTipoMoneda = execute(paramsMoneda, "obtenerIdTcaTipoMoneda");
            }catch(Exception e){
                logger.warning("ERROR al ejecutar OperBinding obtenerIdTcaTipoMoneda.", e);
            }
        }
         
        logger.warning("Termina metodo getIdTipoMonedaLineaCredito");
        return idTipoMoneda;
    }
    
    public String validarMontoDisponibleLineaCredito(){
        logger.warning("Inicia metodo validarMontoDisponibleLineaCredito");
        String mensajeError = null;
        BigDecimal montoDisponibleLineaCredito = null;
        BigDecimal montoDesembolsarContrato = null;

        try{
            logger.warning("Recuperando montoDesembolsar del contrato");
            montoDesembolsarContrato = new BigDecimal(ADFUtils.getBoundAttributeValue("MontoDesembolsar").toString());
        }catch(Exception e){
           logger.warning("Error a obtener montoDesembolsar del contrato.");
        }
        
        ContratoDesembolsosBean contratoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        if(null != contratoBean){
            montoDisponibleLineaCredito = obtenerMontoDisponibleDesembolsarLineaCredito();
            if(null != montoDisponibleLineaCredito){
                logger.warning("Validando monto de la linea de credito vs el monto del contrato de desembolso.");
                if(null != montoDesembolsarContrato){
                    logger.warning("Monto disponible de la linea: " + montoDisponibleLineaCredito);
                    logger.warning("Monto a desembolsar de contrato: " + montoDesembolsarContrato);
                    
                    Integer idTipoMoneda = null;
                    BigDecimal montoConvertido = null;

                    try {
                        idTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
                    } catch (Exception e) {
                        logger.warning("ERROR al obtener el idTipoMoneda.");
                    }

                    if (null != idTipoMoneda) {
                        Map<String, Object> params = new HashMap<String, Object>();

                        params.put("claveTipo", idTipoMoneda);
                        params.put("codigoA", COD_EXT_MONEDA_LINEA_CREDITO);
                        params.put("monto", montoDesembolsarContrato);
                        try {
                            montoConvertido = execute(params, "convertirMonedas");
                            logger.warning("Monto convertido: " + montoConvertido);
                            contratoBean.setMontoConvertido(montoConvertido);
                        } catch (Exception e) {
                            logger.warning("ERROR al convertir el monto a otro tipo de moneda.", e);
                        }

                        if (null != montoConvertido) {
                            if (montoConvertido.compareTo(montoDisponibleLineaCredito) == 1) {
                                logger.warning("El monto a desembolsar del contrato es mayor que el monto disponible de la linea.");
                                mensajeError = getStringFromBundle("LINEA_CREDITO_SIN_DISPONIBILIDAD_ERROR");
                            }else{
                                logger.warning("El monto del desembolso convertido a dolares es menor al monto disponible de la linea de credito.");
                            }
                        }else{
                            logger.warning("ERROR no se pudo recuperar el monto convertido del contrato de desembolso.");
                            mensajeError = getStringFromBundle("MSG_ERROR_MONTO_CONVERTIDO_DESEMBOLSO");
                        }
                    }else{
                        logger.warning("ERROR el tipo de moneda es NULL. No se pudo validar el monto disponible de la linea.");
                        mensajeError = getStringFromBundle("MSG_ERROR_TIPO_MONEDA_NULL");
                    }
                }else{
                    logger.warning("Monto a desembolsar de contrato es NULL");
                    mensajeError = getStringFromBundle("ERROR_LINEA_SIN_MONTO_DISPONIBLE");
                }
            }else{
                logger.warning("El monto de la linea de credito es NULL.");
                mensajeError = getStringFromBundle("ERROR_LINEA_SIN_MONTO_DISPONIBLE");
            }
        }else{
            logger.warning("ERROR al instanciar contratoBean es NULL.");
            mensajeError = getStringFromBundle("ERROR_LINEA_SIN_MONTO_DISPONIBLE");
        }
        
        logger.warning("Termina metodo validarMontoDisponibleLineaCredito");
        return mensajeError;
    }
    
    public BigDecimal obtenerMontoDisponibleDesembolsarLineaCredito(){
        logger.warning("Inicia metodo obtenerMontoDisponibleDesembolsarLineaCredito.");
        BigDecimal montoDisponibleDesembolsarLinea = null;
        Long idLineaCredito = null;
        Map<String, Object> params = new HashMap<String, Object>();
        ContratoDesembolsosBean contratoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        Map resultMap = new HashMap();
        
        idLineaCredito = contratoBean.getIdLinea();
        logger.warning("IdLineaCredito a consultar: " + idLineaCredito);
        
        if(null != idLineaCredito){
            params.put("idLineaCredito", idLineaCredito);
            params.put("tipoMoneda", null);
            try{
                resultMap = execute(params, "consultarLineaCreditoByIdLineaCredito");
            }catch(Exception e){
                logger.warning("ERROR al ejecutar OperBinding consultarLineaCreditoByIdLineaCredito.", e);
            }
        }else{
            logger.warning("IdLineaCredito es NULL. No se puede obtener el monto disponible de la linea.");
        }
        
        if(null != resultMap && !resultMap.isEmpty() && resultMap.size() > 0){
            logger.warning("Recuperando Monto Disponible de la linea de servicio.");
            try{
                montoDisponibleDesembolsarLinea = (BigDecimal) resultMap.get("DISPONIBLE");
            }catch(Exception e){
                logger.warning("ERROR al recuperar montoDisponibleDesembolsar de la linea.", e);
            }
            
            logger.warning("Monto disponible a desembolsar de la linea: " + montoDisponibleDesembolsarLinea);
        }else{
            logger.warning("resultMap no devuelve datos. No se puede recuperar el monto disponible de la linea.");
        }
        
        logger.warning("Termina metodo obtenerMontoDisponibleDesembolsarLineaCredito.");
        return montoDisponibleDesembolsarLinea;
    }
    
    public void mostrarPopupLimites(){
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();        
        getAlertaLimitesPopUpUIC().show(popupHints);
    }
    
    public void mostrarPopupProgramacion(){
        RichPopup.PopupHints popupHints = new RichPopup.PopupHints();        
        getAlertaProgramacionUIC().show(popupHints);
    }
    
    public void cerrarPopupLimites(){
        getAlertaLimitesPopUpUIC().hide();
    }

    @SuppressWarnings("unchecked")
    public void validarReglasNegocio(ActionEvent actionEvent) {
        logger.warning("Inicia metodo validarReglasNegocio");
        List listaReglasValidar = new ArrayList();
        String instanciaTarea = null;
        Map<String, Object> params = new HashMap<String, Object>();
        Map mapaReglasValidadas = new HashMap();
        List<String> listaErrores = new ArrayList<String>();
        String mensajeErrorValidacion = null;
        Boolean esErrorProgramacion = Boolean.FALSE;
        ContratoDesembolsosBean contratoDesembolsoBean = new ContratoDesembolsosBean();

        try {
            contratoDesembolsoBean =
                (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
            
            } catch (Exception e) {
                logger.warning("Excepcion al obtener Contrato Desembolso." + e.getClass(), ".", e.getMessage());
            }

        setEsErrorLimiteTasa(Boolean.FALSE);


        //cerrarPopupLimites();
        logger.warning("EsErrorLimiteMonto: " + contratoDesembolsoBean.getEsErrorMontoLimite());
        if (contratoDesembolsoBean.getEsErrorMontoLimite()) {
            logger.warning("ERROR de limite de montos. ");
            mensajeErrorValidacion = getStringFromBundle("GD_CREAR_CONTRATO_MSG_23");
            listaErrores.add(mensajeErrorValidacion);
            contratoDesembolsoBean.setEsErrorMontoLimite(Boolean.FALSE);
            contratoDesembolsoBean.setEsMontoMinimo(Boolean.FALSE);
            contratoDesembolsoBean.setEsMontoMaximo(Boolean.FALSE);
            setEsErrorLimiteMonto(Boolean.FALSE);
        }

        
        logger.warning("Recuperando valor de TaskFlow de idContrato");
        Long pIdContratoDesembolso = null;
        try {
                logger.warning("IdContrato con EL de TF: " +
                               JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}"));
                if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}")) {
                    pIdContratoDesembolso =
                        new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}").toString());
                } else {
                    logger.warning("IdContrato es NULL");
                }
            } catch (Exception e) {
                logger.warning("Excepcion al obtener ID Contrato Desembolso." + e.getClass(), ".", e.getMessage());
            }

        logger.warning("Recuperando valor de TaskFlow de idTarea");
        Integer pIdTarea = null;
        try {
                if (JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}") != null) {
                    pIdTarea = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}").toString());
                } else {
                    logger.warning("IdTareaBPM es NULL");
                }
            } catch (Exception e) {
                logger.warning("Excepcion al obtener ID Tarea." + e.getClass(), ".", e.getMessage());
            }

        logger.warning("Recuperando valor de TaskFlow de idProceso");
        Integer pIdProceso = null;
        try {
                if (JSFUtils.resolveExpression("#{pageFlowScope.pIdProcesoBPM}") != null) {
                    pIdProceso = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdProcesoBPM}").toString());
                } else {
                    logger.warning("IdProcesoBPM es NULL");
                }
            } catch (Exception e) {
                logger.warning("Excepcion al obtener ID Proceso." + e.getClass(), ".", e.getMessage());
            }

        try {
                logger.warning("Recuperando valor de TaskFlow de Instancia");
                if (JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaTareaBPM}") != null) {
                    instanciaTarea = JSFUtils.resolveExpression("#{pageFlowScope.pInstanciaTareaBPM}").toString();
                } else {
                    logger.warning("pInstanciaTareaBPM es NULL");
                }
            } catch (Exception e) {
                logger.warning("Excepcion al obtener valor de instanciaTarea." + e.getClass(), ".", e.getMessage());
            }

        logger.warning("pIdTarea: " + pIdTarea);
        logger.warning("idInstancia: " + instanciaTarea);
        logger.warning("pIdProceso: " + pIdProceso);
        logger.warning("pIdContrato: " + pIdContratoDesembolso);

        logger.warning("Asignando listado de reglas a validar por servicio para iniciar el proceso de Desembolso");
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_LIMITES); //RN_28
        //Se descomenta regla ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO para atender tarjeta FNXII-7443
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO); //RN_27
        //Se agrega validacion de regla ID_REGLA_TRANSACCION_LIMITE_GLOBAL ya que no se estaba validando.
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_LIMITE_GLOBAL);
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION); //RN_05
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO); //RN_06
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_MORA); //RN_07
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_CONDICIONES); //RN_10
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO); //RN_12 TODO : Revisar definicion de reglas de SCR para mostrar validaciones correspondientes.
        //listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_UMIPYME); //RN_13
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PRESENTACION_F1); //RN_14
        //listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_AJUSTE_TASA); //RN_16
        //listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_MOMENTO_COBRO); //RN_17
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_COMPONENTE_INTERES); //RN_22
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO); //RN_23
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PERIODO_GRACIA); //RN_24
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO); //RN_25
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO); //RN_26
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_LAFT); //RN_09
        //Regla No.23
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_MONTO_MAXIMO_OCHO_VECES_SALDO); // User Story 57 - Validar Monto máximo ocho veces saldo en certificados.
        //Regla No.25
        listaReglasValidar.add(FenixConstants.ID_REGLA_TRANSACCION_DESEMBOLSOS_PROGRAMADOS_MISMA_FECHA); // User Story 58 - Prohibición de Vencimientos en la misma fecha.
        //Regla No.24
        //listaReglasValidar.add(FenixConstants.ID_REGLA_VALIDACION_TEMPRANA_FLEXCUBE); //RN_24
        logger.warning("Cantidad de reglas a validar por servicio: " + listaReglasValidar.size());

        logger.warning("Asignando parametros de entrada de metodo de validacion de reglas...");
        params.put("listaReglas", listaReglasValidar);
        params.put("idContratoDesembolso", pIdContratoDesembolso);
        params.put("instanciaTarea", instanciaTarea);
        params.put("idTarea", pIdTarea);
        params.put("idProceso", pIdProceso);

        try {
            logger.warning("Invocando metodo obtenerReglasNegocio");
            mapaReglasValidadas = execute(params, "obtenerReglasNegocio");
        } catch (Exception e) {
            logger.warning("ERROR al ejecutar metodo obtenerReglasNegocio ", e);
            JSFUtils.addFacesErrorMessage("ERROR al ejecutar metodo obtenerReglasNegocio: " + e.getMessage());
        }

        if (!mapaReglasValidadas.isEmpty() && mapaReglasValidadas.size() > 0) {
            logger.warning("Obteniendo resultados de mapa de reglas ya validadas.");
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_LIMITES);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_LIMITES");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PROGRAMACION");
                if(mensajeErrorValidacion.equalsIgnoreCase(getStringFromBundle("ERROR_ESTADO_FLEXCUBE")))
                {
                    //verificamos si existe este error en la lista previo a agregarlo
                    if(!listaErrores.contains(mensajeErrorValidacion))
                    {
                        listaErrores.add(mensajeErrorValidacion);
                    }
                }
                else
                {
                    esErrorProgramacion = Boolean.TRUE;
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas,
                                             FenixConstants.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_SCR_INICIO_PROCESO_DESEMBOLSO");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas,
                                             FenixConstants.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO); //TODO : Revisar definicion de reglas de SCR para mostrar validaciones correspondientes.
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_SCR_CONTINUAR_PROCESO_DESEMBOLSO");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_MORA);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_MORA");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_CONDICIONES);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_CONDICIONES");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            //mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_UMIPYME);
            //if(null != mensajeErrorValidacion){
            //    logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_UMIPYME");
            //    listaErrores.add(mensajeErrorValidacion);
            //}
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_PRESENTACION_F1);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PRESENTACION_F1");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            //mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_AJUSTE_TASA);
            //if(null != mensajeErrorValidacion){
            //    logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_AJUSTE_TASA");
            //listaErrores.add(mensajeErrorValidacion);
            //    setEsErrorLimiteTasa(Boolean.TRUE);
            //}
            //mensajeErrorValidacion = obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_MOMENTO_COBRO);
            //if(null != mensajeErrorValidacion){
            //    logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_MOMENTO_COBRO");
            //    listaErrores.add(mensajeErrorValidacion);
            //}
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas,
                                             FenixConstants.ID_REGLA_TRANSACCION_COMPONENTE_INTERES);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_COMPONENTE_INTERES");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas,
                                             FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MAXIMO");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_PERIODO_GRACIA);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PERIODO_GRACIA");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas,
                                             FenixConstants.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_PLAZO_CAPITAL_MINIMO");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas,
                                             FenixConstants.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_TOTAL_LINEA_CREDITO");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_LAFT);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_LAFT");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_LIMITE_GLOBAL);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_LIMITE_GLOBAL");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
           
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_MONTO_MAXIMO_OCHO_VECES_SALDO);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_MONTO_MAXIMO_OCHO_VECES_SALDO");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            //Regla No.23
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_DESEMBOLSOS_PROGRAMADOS_MISMA_FECHA);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_DESEMBOLSOS_PROGRAMADOS_MISMA_FECHA");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
            //Regla No.25
            mensajeErrorValidacion =
                obtenerEstadoReglasValidadas(mapaReglasValidadas, FenixConstants.ID_REGLA_TRANSACCION_DESEMBOLSOS_PROGRAMADOS_MISMA_FECHA);
            if (null != mensajeErrorValidacion) {
                logger.warning("Añadiendo listado de mensajes.ID_REGLA_TRANSACCION_DESEMBOLSOS_PROGRAMADOS_MISMA_FECHA");
                //verificamos si existe este error en la lista previo a agregarlo
                if(!listaErrores.contains(mensajeErrorValidacion))
                {
                    listaErrores.add(mensajeErrorValidacion);
                }
            }
        } else {
            logger.warning("ERROR: No se obtivieron reglas validadas por servicio.");
            //NOTA: Se hace ajuste para NO mostrar errores en caso de no recibir reglas para evaluar.
            //listaErrores.add(getStringFromBundle("ERROR_CONSUMO_SERVICIO_REGLAS"));
        }

        /*
                 * Se validan reglas que no son por servicio
                 */
        //RN_11
        mensajeErrorValidacion = validarMontoDisponibleLineaCredito();
        if (null != mensajeErrorValidacion) {
            logger.warning("Añadiendo listado de mensajes.");
            listaErrores.add(mensajeErrorValidacion);
        }

        //RN_18, RN_19
        List<String> listaErroresFecha = new ArrayList<String>();
        listaErroresFecha = validarFechasInicioDesembolsoTotalidadRecursos2();
        for (String mensajeFechas : listaErroresFecha) {
            logger.warning("Obteniendo errores de validacion de fechas.");
            listaErrores.add(mensajeFechas);
        }

        //RN_32
        mensajeErrorValidacion = validarFechaEstimadaContraFechaActual();
        if (null != mensajeErrorValidacion) {
            logger.warning("Añadiendo listado de mensajes.");
            listaErrores.add(mensajeErrorValidacion);
        }

        //Validacion de Fuentes sin idLineaPasiva
        Boolean esValido = validarFuentesSinIdLineaPasiva();
        if (null != esValido) {
            if (esValido) {
                logger.warning("Validacion de fuentes sin idLineaPasiva Si se cumplio.");
            } else {
                logger.warning("Validacion de Fuentes sin IdLinePasiva no cumplida.");
                listaErrores.add(getStringFromBundle("MSG_ERROR_FUENTES_PENDIENTES"));
            }
        } else {
            logger.warning("Validacion de Fuentes sin Id linea pasiva no se realizo correctamente.");
            listaErrores.add("Validacion de Fuentes sin Id linea pasiva no se realizo correctamente.");
        }

        if (listaErrores.size() > 0) {
            if (contratoDesembolsoBean.getListaErrores().size() <= 0) {
                logger.warning("Seteando lista de errores a ManagedBean.");
                contratoDesembolsoBean.setListaErrores(listaErrores);
                logger.warning("1 contratoDesembolsoBean: " + contratoDesembolsoBean.getListaErrores().size() );
                /* if(esErrorLimiteTasa){
                            logger.warning("Mostrando popup de limite de tasas.");
                            mostrarPopupLimites();
                        }else{
                            logger.warning("Validando reglas de negocio.");
                            recuperarMensajesError();
                        } */
                if (esErrorProgramacion) {
                    logger.warning("Mostrando popup de limite de tasas.");
                    mostrarPopupProgramacion();
                } else {
                    logger.warning("Validando reglas de negocio.");
                    recuperarMensajesError();
                }
            } else {
                logger.warning("Validando reglas de negocio.");
                logger.warning("Seteando lista de errores a ManagedBean.");
                contratoDesembolsoBean.setListaErrores(listaErrores);
                logger.warning("2 contratoDesembolsoBean: " + contratoDesembolsoBean.getListaErrores().size() );
                recuperarMensajesError();
            }
        } else {
            if (esErrorProgramacion) {
                logger.warning("Mostrando popup de limite de tasas.");
                contratoDesembolsoBean.setListaErrores(listaErrores);
                mostrarPopupProgramacion();
            } else {
                logger.warning("Mostrando Popup de confirmacion para Iniciar proceso de desembolso.");
                RichPopup.PopupHints popupHints = new RichPopup.PopupHints();
                getConfirmarInicioDesembolsoPopupUIC().show(popupHints);
            }
        }
        
        logger.warning("Termina metodo validarReglasNegocio");
    }
    
    public Boolean validarFuentesSinIdLineaPasiva(){
        logger.warning("Inicia metodo validarFuentesSinIdLineaPasiva.");
        Boolean esValido = Boolean.FALSE;
        Long idLineaCredito = null;
        Map<String, Object> params = new HashMap<String, Object>();
        ContratoDesembolsosBean contratoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        idLineaCredito = contratoBean.getIdLinea();
        
        logger.warning("IdLineaCredito: " + idLineaCredito);
        params.put("idLineaCredito", idLineaCredito);
        try{
            esValido = execute(params, "validarFuentesSinIdLineaPasiva");
        }catch(Exception e){
            logger.warning("ERROR al ejecutar el operBinding validarFuentesSinIdLineaPasiva.", e);
        }
        
        logger.warning("Termina metodo validarFuentesSinIdLineaPasiva.");
        return esValido;
    }
    
    public Boolean actualizarMontoDesembolsoContrato(){
        logger.warning("Inicia metodo validarFechaEstimadaContraFechaActual");
        Boolean resultado = Boolean.FALSE;
        Integer idTipoMoneda = null;
        String codExtMonedaContrato = null;
        Map<String, Object> params = new HashMap<String, Object>();
        
        try{
            idTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
        }catch(Exception e){
            logger.warning("ERROR al obtener el idTipoMoneda.");
        }
        
        params.put("idTipoMoneda", idTipoMoneda);
        try{
                codExtMonedaContrato = execute(params, "obtenerCodigoExternoMoneda");
            logger.warning("Codigo externo de la moneda del contrato: " + codExtMonedaContrato);
        }catch(Exception e){
            logger.warning("ERROR al obtener el codigo externo de la moneda del contrato.", e);
        }
        
        ContratoDesembolsosBean contratoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        params.clear();
        if(null != contratoBean){
            BigDecimal montoConvertido = null;
            if(contratoBean.getEsMontoMinimo()){
                logger.warning("Actualizando monto a desembolsar por montoMinimo");
                
                params.put("claveTipo", idTipoMoneda);
                params.put("codigoA", codExtMonedaContrato);
                params.put("monto", contratoBean.getMontoMinimoDesembolso());
                try{
                    montoConvertido = execute(params, "convertirMonedas");
                    logger.warning("Monto convertido: " + montoConvertido);
                }catch(Exception e){
                    logger.warning("ERROR al convertir el monto a otro tipo de moneda.", e);
                }
            }
            if(contratoBean.getEsMontoMaximo()){
                logger.warning("Actualizando monto a desembolsar por montoMaximo");
                
                params.put("claveTipo", idTipoMoneda);
                params.put("codigoA", codExtMonedaContrato);
                params.put("monto", contratoBean.getMontoMaximoDesembolso());
                try{
                    montoConvertido = execute(params, "convertirMonedas");
                    logger.warning("Monto convertido: " + montoConvertido);
                }catch(Exception e){
                    logger.warning("ERROR al convertir el monto a otro tipo de moneda.", e);
                }
            }
            params.clear();
            params.put("nuevoMontoDesembolsar", montoConvertido);
            try{
                resultado = execute(params, "actualizarMontoDesembolsar");
            }catch(Exception e){
                logger.warning("ERROR al ejecutar la actualizacion del monto a desembolsar.", e);
            }
        }
        
        logger.warning("Termina metodo validarFechaEstimadaContraFechaActual");
        return resultado;
    }
    public String validarFechaEstimadaContraFechaActual(){
        logger.warning("Inicia metodo validarFechaEstimadaContraFechaActual");
        String mensajeError = null;
        java.util.Date fechaActual = new java.util.Date();
        Date fechaEstimadaDesembolsar = null;
        java.util.Date fechaEstimada = null;
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaEstimadaStr = null;
        String fechaActualStr = null;
        
        try{
            fechaEstimadaDesembolsar = (Date) ADFUtils.getBoundAttributeValue("FechaEstimadaDesembolsar");
            java.sql.Date sqldate = fechaEstimadaDesembolsar.dateValue();
            fechaEstimada = new java.util.Date(sqldate.getTime());
        }catch(Exception e){
            logger.warning("ERROR al obtener la Fecha estimada a desembolsar.", e);
        }
        
        if(null != fechaEstimadaDesembolsar){
            logger.warning("Fecha actual del sistema: " + fechaActual);
            logger.warning("Fecha estimada a desembolsar: " + fechaEstimada);
            
            try{
                fechaEstimadaStr = dmyFormat.format(fechaEstimada);
                fechaActualStr = dmyFormat.format(fechaActual);
                
                logger.warning("Fecha actual del sistema STR: " + fechaActualStr);
                logger.warning("Fecha estimada a desembolsar STR: " + fechaEstimadaStr);
                
                fechaActual = dmyFormat.parse(fechaActualStr);
                fechaEstimada = dmyFormat.parse(fechaEstimadaStr);
                
                logger.warning("Fecha actual del sistema: " + fechaActual);
                logger.warning("Fecha estimada a desembolsar: " + fechaEstimada);
            }catch(Exception e){
                logger.warning("ERROR al dar formato a las fechas.", e);
            }
            
            try{
                if(fechaEstimada.before(fechaActual)){
                    logger.warning("La fecha estimada a desembolsar es menor a la fecha del sistema.");
                    mensajeError = getStringFromBundle("ERROR_FECHA_ACTUAL_MAYOR_MSG");
                }
            }catch(Exception e){
                logger.warning("ERROR al comparar las fechas.", e);
                mensajeError = getStringFromBundle("ERROR_FECHA_ESTIMADA_DESEMBOLSAR");
            }
        }else{
            logger.warning("La FechaEstimadaDesembolsar es NULL.");
            mensajeError = getStringFromBundle("ERROR_FECHA_ESTIMADA_DESEMBOLSAR");
        }
        
        logger.warning("Termina metodo validarFechaEstimadaContraFechaActual");
        return mensajeError;
    }
    
    public List<String> validarFechasInicioDesembolsoTotalidadRecursos(){
        logger.warning("Inicia metodo validarFechasInicioDesembolsoTotalidadRecursos");
        List<String> listaErrores = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        
        Long pIdOperacion = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
            try{
                pIdOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            }catch(Exception e){
                logger.warning("ERROR al obtener el idOperacion del TaskFlow.");
            }
        }else{
            logger.warning("IdOperacion es NULL");
        }
        
        params.put("idOperacion", pIdOperacion);
        logger.warning("Invocando metodo para validar las fechas de inicio de desembolsos y desembolso de la totalidad de los recursos");
        try{
            listaErrores = execute(params, "recuperarFechasTerminosDesembolsos");
            logger.warning("Registros de la lista de errores: " + listaErrores.size());
        }catch(Exception e){
            logger.warning("Error al ejecutar el operationBinding para validar las fechas de terminos.", e);
        }
        
        logger.warning("Termina metodo validarFechasInicioDesembolsoTotalidadRecursos");
        return listaErrores;
    }
    
    public List<String> validarFechasInicioDesembolsoTotalidadRecursos2(){
        logger.warning("Inicia metodo validarFechasInicioDesembolsoTotalidadRecursos2");
        List<String> listaErrores = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        
        Long pIdOperacion = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){
            try{
                pIdOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            }catch(Exception e){
                logger.warning("ERROR al obtener el idOperacion del TaskFlow.");
            }
        }else{
            logger.warning("IdOperacion es NULL");
        }
        
        Long pIdLinea = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdLineaCredito}") != null){
            try{
                pIdLinea = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdLineaCredito}").toString());
            }catch(Exception e){
                logger.warning("ERROR al obtener la pIdLineaCredito del TaskFlow.");
            }
        }else{
            logger.warning("IdOperacion es NULL");
        }
        
        params.put("idOperacion", pIdOperacion);
        params.put("idLinea", pIdLinea);
        logger.warning("Invocando metodo para validar las fechas de inicio de desembolsos y desembolso de la totalidad de los recursos");
        try{
            listaErrores = execute(params, "recuperaFechasTerminosLineaDesembolso");
            logger.warning("Registros de la lista de errores: " + listaErrores.size());
        }catch(Exception e){
            logger.warning("Error al ejecutar el operationBinding para validar las fechas de terminos.", e);
        }
        
        logger.warning("Termina metodo validarFechasInicioDesembolsoTotalidadRecursos2");
        return listaErrores;
    }
    
    public String obtenerEstadoReglasValidadas(Map mapaReglasValidadas, Long idReglaPorValidar){
        logger.warning("Inicia metodo obtenerEstadoReglasValidadas");
        String descripcionEstadoRegla = null;
        String mensajeError = null;
        
        try{
            logger.warning("Id regla a Validar : " + idReglaPorValidar);
            descripcionEstadoRegla = (String) mapaReglasValidadas.get(idReglaPorValidar);
        }catch(Exception e){
            logger.warning("ERROR al recibir la validacion de la Regla de negocio de limites.", e);
            //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
            mensajeError = getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO");
        }
        
        if(null != descripcionEstadoRegla){
            logger.warning("Evaluando estado de regla.");            
            if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_CUMPLIDA")) ||
               descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_EXCEPTUADA")) ||
               descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_NO_EVALUADA"))){
                logger.warning("Regla validada correctamente.");
            }
            else if(descripcionEstadoRegla.equalsIgnoreCase(getStringFromBundle("ESTADO_VALIDACION_REGLA_ERROR_FLEXCUBE")))
            {
                mensajeError = getStringFromBundle("ERROR_ESTADO_FLEXCUBE");
            }
            else{
                logger.warning("La validacion de la regla no se cumplio.");
                if(FenixConstants.ID_REGLA_TRANSACCION_LIMITES.compareTo(idReglaPorValidar)==0){
                    ///JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_LIMITE_PAIS_GRUPO_ECONOMICO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_TOTAL_CONTRATO_DESEMBOLSO.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG"));
                    mensajeError = getStringFromBundle("ERROR_TOTAL_CONTRATO_DESEMBOLSO_MSG");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_LIMITE_GLOBAL.compareTo(idReglaPorValidar)==0){
                    logger.warning("mensajeErrorRegla22 : " + (String) mapaReglasValidadas.get("mensajeErrorRegla22"));
                    mensajeError = (String) mapaReglasValidadas.get("mensajeErrorRegla22");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_PROGRAMACION.compareTo(idReglaPorValidar)==0){
                    //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_PROGRAMACION_MSG"));
                    mensajeError = getStringFromBundle("ERROR_PROGRAMACION_MSG");
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
                if(FenixConstants.ID_REGLA_TRANSACCION_MONTO_MAXIMO_OCHO_VECES_SALDO.compareTo(idReglaPorValidar)==0){
                    logger.warning("mensajeErrorRegla23: " + (String) mapaReglasValidadas.get("mensajeErrorRegla23"));
                    mensajeError = (String) mapaReglasValidadas.get("mensajeErrorRegla23");
                }
                if(FenixConstants.ID_REGLA_TRANSACCION_DESEMBOLSOS_PROGRAMADOS_MISMA_FECHA.compareTo(idReglaPorValidar)==0){
                    mensajeError = getStringFromBundle("ERROR_DESEMBOLSOS_PROGRAMADOS_MISMA_FECHA");
                }
            }
        }else{
            logger.warning("descripcionEstadoRegla es NULL, No se pudo obtener el estado de la regla de negocio.");
            //JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
            logger.warning("Regla No encontrada por posible inactivacion: " + idReglaPorValidar);
            /* Se inactiva codigo para atender incidencia FNXII-5587, debido a que el servicio de validacion solo devuelve
             * las reglas activas
            if(!getEsErrorValidacion()){
                setEsErrorValidacion(Boolean.TRUE);
                mensajeError = getStringFromBundle("ERROR_CONSUMO_SERVICIO_REGLAS");
            }*/
        }
        logger.warning("Termina metodo obtenerEstadoReglasValidadas");
        return mensajeError;
    }
    
    public void mostrarMensajesError(ActionEvent actionEvent){
        recuperarMensajesError();
        getAlertaProgramacionUIC().hide();
    }
    
    public void recuperarMensajesError(){
        logger.warning("Inicia metodo recuperarMensajesError");        
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        List<String> listaErrores = new ArrayList<String>();
        
        if(null != contratoDesembolsoBean){
            listaErrores = contratoDesembolsoBean.getListaErrores();
        }else{
            logger.warning("ERROR la instancia de ContratoDesembolsoBean es NULL. No se pueden recuperar las validaciones de REGLAS.");
            JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_REGLAS_NO_VALIDADAS_POR_SERVICIO"));
            return;
        }
        
        if(listaErrores.size() > 0){
            logger.warning("Iterando lista de errores para impresion en pantalla.");
            for(String mensaje : listaErrores){
                JSFUtils.addFacesErrorMessage(mensaje);
            }
        }else{
            RichPopup.PopupHints popupHints = new RichPopup.PopupHints();        
            getConfirmarInicioDesembolsoPopupUIC().show(popupHints);
        }
        
        logger.warning("Termina metodo recuperarMensajesError");
    }
    
    public void iniciarProcesoDesembolso(ActionEvent actionEvent){
        logger.warning("Inicia metodo iniciarProcesoDesembolso");
        Map<String, Object> mapaParametros = new HashMap<String, Object>();
        Boolean resultado = Boolean.FALSE;
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        Integer idTcaEstado = null;
        Long idSolicitudTF = null;
        
        Long pIdOperacion = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}") != null){ 
            try{
                pIdOperacion = new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            }catch(Exception e){
                logger.warning("ERROR al convertir pIdOperacion", e);
            }
        }else{
            logger.warning("IdOperacion es NULL");
        }
        
        String pResponsableOperacion = null;
        pResponsableOperacion = ADFContext.getCurrent().getSecurityContext().getUserName();
        
        Long pIdContratoDesembolso = null;
        logger.warning("IdContrato con EL de TF: " + JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}"));
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}")){
            try{
                pIdContratoDesembolso =
                    new Long(JSFUtils.resolveExpression("#{pageFlowScope.pIdContratoDesembolso}").toString());
            }catch(Exception e){
                logger.warning("ERROR al convertir pIdContratoDesembolso", e);
            }
        }else{
            logger.warning("IdContrato es NULL");
        }
        
        try{
            idSolicitudTF = contratoDesembolsoBean.getIdSolicitudTF();
            logger.warning("IdSolicitud: " + idSolicitudTF);
        }catch(Exception e){
            logger.warning("ERROR al recuperar el IdSolicitud del bean.", e);
        }
        
        if(null != pIdOperacion && null != pResponsableOperacion && null != pIdContratoDesembolso){
            mapaParametros.put("idOperacion", pIdOperacion);
            mapaParametros.put("responsableOperacion", pResponsableOperacion);
            mapaParametros.put("idContrato", pIdContratoDesembolso);
            mapaParametros.put("idSolicitud", idSolicitudTF);
            try {
                logger.warning("Ejecutando metodo iniciarProcesoDesembolso");
                resultado = super.execute(mapaParametros, "iniciarProcesoDesembolso");
                if(!resultado){
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("INICIO_DESEMBOLSOS_ERROR"));
                }else{                    
                    try {        
                        mapaParametros.clear();
                        mapaParametros.put("idContrato", pIdContratoDesembolso);
                        resultado = super.execute(mapaParametros, "asignarFechaInicioDesembolso");
                    } catch (Exception e) {
                        logger.warning("Error, fallo al guardar fecha de inicio de proceso de desembolso.", e);
                        JSFUtils.addFacesErrorMessage("No se pudo guardar la Fecha de inicio del proceso de Desembolso: " + e.getMessage());
                        resultado = Boolean.FALSE;
                    }
                    if(resultado){
                        try{
                            idTcaEstado = (Integer) ADFUtils.getBoundAttributeValue("IdTcaEstadoAttr");
                        }catch(Exception e){
                            logger.warning("ERROR al obtener el idTcaEstado.", e);
                        }                        
                        
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("estadoContratoSeleccionado", "En tránsito");  
                        
                        contratoDesembolsoBean.setIdEstadoContrato(idTcaEstado);
                        contratoDesembolsoBean.setEsIniciarProcesoDesembolso(Boolean.TRUE);
                        logger.warning("Invocando metodo configurarFormulario.");
                        contratoDesembolsoBean.configurarFormularioContrato();
                        logger.warning("Refrescando pantalla...");
                        FacesContext fctx = FacesContext.getCurrentInstance();
                        String page = fctx.getViewRoot().getViewId();
                        ViewHandler ViewH = fctx.getApplication().getViewHandler();
                        UIViewRoot UIV = ViewH.createView(fctx, page);
                        UIV.setViewId(page);
                        fctx.setViewRoot(UIV);
                    }else{
                        logger.warning("Importante!, ha ocurrido un erro al inicio del proceso de desembolsos");
                    }
                }
            
                
            } catch (Exception e) {
                logger.warning("ERROR al ejecutar el metodo iniciarProcesoDesembolso", e);
                JSFUtils.addFacesErrorMessage(getStringFromBundle("INICIO_DESEMBOLSOS_ERROR"));
            }
        }else{
            logger.warning("No se pudo ejecutar el metodo iniciarProcesoDesembolso");
            logger.warning("Parametros requeridos son NULL");
            logger.warning("idContrato: " + pIdContratoDesembolso);
            logger.warning("responsableOperacion: " + pResponsableOperacion);
            logger.warning("idOperacion: " + pIdOperacion);
            JSFUtils.addFacesErrorMessage(getStringFromBundle("INICIO_DESEMBOLSOS_ERROR"));
        }
        
        getConfirmarInicioDesembolsoPopupUIC().hide();

        logger.warning("Termina metodo iniciarProcesoDesembolso");
    }

    public void cambioTabs(AttributeChangeEvent attributeChangeEvent) {
        logger.warning("valor anterior "+ attributeChangeEvent.getOldValue());
        logger.warning("valor nuevo "+ attributeChangeEvent.getNewValue());
       
        // Add event code here...
    }
    
    public void guardarDatosContratoDesembolso(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo guardarDatosContratoDesembolso");
        Boolean resultado = Boolean.FALSE;
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = null;
        Map mapaDatos = new HashMap();
        
        //Asignacion de campos transient a campos basados en Entity de PROGRAMADO, FONDO Y IDTCATIPOMONEDA
        asignartransientAProgramado();
        asignarTransientAFondo();
        asignarTransientAIdTcaTipoMoneda();
        asignarTransientAFechaEstimadaDispRecursos();
        
        resultado = validarCamposContrato();
        
        if (resultado) {
            logger.warning("Ejecutando operationBinding para realizar guardado de contrato");
            try {
                operationBinding = bindings.getOperationBinding("guardarMontoMonedaFechaEstimada");
                logger.warning("Ejecutando OperationBinding de guardarMontoMonedaFechaEstimada");
                resultado = (Boolean) operationBinding.execute();

                if (!operationBinding.getErrors().isEmpty()) {
                    logger.warning("OperationBinding regresa ERRORES: " + operationBinding.getErrors().toString());
                    resultado = Boolean.FALSE;
                }
            } catch (Exception e) {
                logger.warning("ERROR al ejecutar el operationBinding", e);
                resultado = Boolean.FALSE;
            }
        }

        if (resultado) {
            logger.warning("Ejecutar OperationBinding para obtener campo PROGRAMADO y días configurados por moneda");
            try {
                if (resultado) {
                    logger.warning("Ejecutando operationBinding para obtener programado y fechaEstimada de disponibilidad de recursos");
                    operationBinding = bindings.getOperationBinding("obtenerProgramadoDiasConfiguracionMoneda");
                    logger.warning("Ejecutando OperationBinding de obtenerProgramadoDiasConfiguracionMoneda");
                    operationBinding.execute();

                    if (!operationBinding.getErrors().isEmpty()) {
                        logger.warning("OperationBinding regresa ERRORES: " + operationBinding.getErrors().toString());
                        resultado = Boolean.FALSE;
                    } else {
                        ContratoDesembolsosBean contratoBean =
                            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");

                        try {
                            logger.warning("Llamando metodo para asignar Monto programado para mes vigente.");
                            BigDecimal montoProgramMesVigente = null;
                            montoProgramMesVigente =
                                (BigDecimal) ADFUtils.getBoundAttributeValue("MontoProgramadoMesVigente");
                            contratoBean.setMontoProgramMesVigente(montoProgramMesVigente);
                        } catch (Exception e) {
                            logger.warning("ERROR al asignar el Monto programado para mes vigente.", e);
                        }
                        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorMontoProgramado());
                    }

                    AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelGroupContratoFormUIC());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(getPanelTabsContrato());
                }
            } catch (Exception e) {
                logger.warning("ERROR al ejecutar el operationBinding", e);
                resultado = Boolean.FALSE;
            }
        }
        logger.warning("Termina metodo guardarDatosContratoDesembolso");
    }
    
    public void asignartransientAProgramado(){
        logger.warning("Inicia metodo asignartransientAProgramado.");
        
        Integer programado = null;
        
        try{
            programado = (null == ADFUtils.getBoundAttributeValue("ProgramadoTransient")) ? null  
                           : Integer.parseInt(ADFUtils.getBoundAttributeValue("ProgramadoTransient").toString());
        }catch(Exception e){
            logger.warning("ERROR al obtener el BoundAttribute ProgramadoTransient1.", e);
        }
        logger.warning("ProgramadoTransient1: " + programado);
        
        try{
            ADFUtils.setBoundAttributeValue("Programado", programado);
        }catch(Exception e){
            logger.warning("ERROR al asignar el BoundAttribute Programado1.", e);
        }
        
        logger.warning("Termina metodo asignartransientAProgramado.");
    }
    
    public void asignarTransientAFondo(){
        logger.warning("Inicia metodo asignarTransientAFondo.");
        
        if(null != ADFUtils.getBoundAttributeValue("Fondo")){
            logger.warning("El attribute Value Fondo ya tiene asignado un valor.");
            return;
        }
        
        String fondo = null;
        
        try{
            fondo = (String) ADFUtils.getBoundAttributeValue("FondoTransient");
        }catch(Exception e){
            logger.warning("ERROR al recuperar el Attribute value FondoTransient.", e);
        }
        
        if(null != fondo){
            logger.warning("Asignando FondoTransient a Fondo.");
            try{
                ADFUtils.setBoundAttributeValue("Fondo", fondo);
            }catch(Exception e){
                logger.warning("ERROR al asignar AttributeValue Fondo.", e);
            }
        }else{
            logger.warning("Fondo tiene valor: " + fondo);
        }
        
        logger.warning("Termina metodo asignarTransientAFondo.");
    }
    
    public void asignarTransientAIdTcaTipoMoneda(){
        logger.warning("Inicia metodo asignarTransientAIdTcaTipoMoneda.");
        
        if(null != ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda")){
            logger.warning("El attribute Value IdTcaTipoMoneda ya tiene asignado un valor.");
            return;
        }
        
        Integer idTcaTipoMoneda = null;
        
        try{
            idTcaTipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMonedaTransient");
        }catch(Exception e){
            logger.warning("ERROR al recuperar el Attribute value IdTcaTipoMonedaTransient1.", e);
        }
        
        if(null != idTcaTipoMoneda){
            logger.warning("Asignando IdTcaTipoMonedaTransient1 a IdTcaTipoMoneda.");
            try{
                ADFUtils.setBoundAttributeValue("IdTcaTipoMoneda", idTcaTipoMoneda);
            }catch(Exception e){
                logger.warning("ERROR al asignar AttributeValue IdTcaTipoMoneda.", e);
            }
        }else{
            logger.warning("IdTcaTipoMoneda tiene valor: " + idTcaTipoMoneda);
        }
        
        logger.warning("Termina metodo asignarTransientAIdTcaTipoMoneda.");
    }
    
    public void asignarTransientAFechaEstimadaDispRecursos(){
        logger.warning("Inicia metodo asignarTransientAFechaEstimadaDispRecursos.");
        
        Timestamp fechaEstimadaDisponibilidadRecursos = null;
        
        try{
            fechaEstimadaDisponibilidadRecursos = (Timestamp) ADFUtils.getBoundAttributeValue("FechaEstimadaDisponibilidadRecursos");
        }catch(Exception e){
            logger.warning("ERROR al recuperar el Attribute value IdTcaTipoMonedaTransient1.", e);
        }
        
        if(null != fechaEstimadaDisponibilidadRecursos){
            logger.warning("Asignando FechaEstimadaDisponibilidadRecursos a FechaEstimadaDispRecursos.");
            try{
                ADFUtils.setBoundAttributeValue("FechaEstimadaDispRecursos", fechaEstimadaDisponibilidadRecursos);
            }catch(Exception e){
                logger.warning("ERROR al asignar AttributeValue FechaEstimadaDispRecursos.", e);
            }
        }else{
            logger.warning("fechaEstimadaDisponibilidadRecursos tiene valor NULL.");
        }
        
        logger.warning("Termina metodo asignarTransientAFechaEstimadaDispRecursos.");
    }
    
    public Boolean validarCamposContrato(){
        logger.warning("Inicia metodo validarCamposContrato");
        Boolean resultado = Boolean.FALSE;
        BigDecimal monto = null;
        Integer tipoMoneda = null;
        Date fechaEstimada = null;
        
        if(null != ADFUtils.getBoundAttributeValue("MontoDesembolsar")){
            try{
                monto = (BigDecimal) ADFUtils.getBoundAttributeValue("MontoDesembolsar");
            }catch(Exception e){
                logger.warning("ERROR al obtener el montoDesembolsar ", e);
            }
        }else{
            logger.warning("Atributo MontoDesembolsar de Bindings es NULL");
        }
        
        if(null != ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda")){
            try{
                tipoMoneda = (Integer) ADFUtils.getBoundAttributeValue("IdTcaTipoMoneda");
            }catch(Exception e){
                logger.warning("ERROR al obtener el tipoMoneda ", e);
            }
        }else{
            logger.warning("Atributo IdTcaTipoMoneda de Bindings es NULL");
        }
        
        if(null != ADFUtils.getBoundAttributeValue("FechaEstimadaDesembolsar")){
            try{
                fechaEstimada = (Date) ADFUtils.getBoundAttributeValue("FechaEstimadaDesembolsar");
            }catch(Exception e){
                logger.warning("ERROR al obtener el tipoMoneda ", e);
            }
        }else{
            logger.warning("Atributo IdTcaTipoMoneda de Bindings es NULL");
        }
        
        logger.warning("Monto: " + monto);
        logger.warning("Moneda: " + tipoMoneda);
        logger.warning("Fecha estimada: " + fechaEstimada);
        
        if(null != monto && null != tipoMoneda && null != fechaEstimada){
            logger.warning("Campos de formulario de contrato completos.");
            resultado = Boolean.TRUE;            
        }else{
            logger.warning("Campos de contrato NO completos.");
        }
        
        logger.warning("Termina metodo validarCamposContrato");
        return resultado;
    }

    public void obtenerMontoDesembolsar(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo obtenerMontoDesembolsar");
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        if(null != valueChangeEvent){
            logger.warning("Nuevo Monto a desembolsar: " + valueChangeEvent.getNewValue());
            ADFUtils.setBoundAttributeValue("MontoDesembolsar", valueChangeEvent.getNewValue());
        }else{
            logger.warning("ValueChangeEvent MontoDesembolsar es NULL");
        }
        
        guardarDatosContratoDesembolso(null);
        
        logger.warning("Inicia metodo obtenerMontoDesembolsar");
    }

    public void obtenerTipoMoneda(ValueChangeEvent valueChangeEvent) {
            logger.warning("*Cambio moneda desembolso Inicia metodo obtenerTipoMoneda");
            Long idSolicitud = null;
            Map<String, Object> params = new HashMap<>();
            ContratoDesembolsosBean contratoDesembolsoBean =
                (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Integer monedaNueva=null;
        if(null!=valueChangeEvent.getNewValue()){
                monedaNueva=(Integer)valueChangeEvent.getNewValue();
            }
            if(null != valueChangeEvent){
                logger.warning("Nuevo TipoMoneda: " + valueChangeEvent.getNewValue());
                ADFUtils.setBoundAttributeValue("IdTcaTipoMoneda", valueChangeEvent.getNewValue());
                ADFUtils.setBoundAttributeValue("IdTcaTipoMonedaTransient", valueChangeEvent.getNewValue());
            }else{
                logger.warning("ValueChangeEvent IdTcaTipoMoneda es NULL");
            }
            
            idSolicitud = contratoDesembolsoBean.getIdSolicitudTF();
            logger.warning("Id de la solicitud recuperado: "+idSolicitud);
            
            if(null != idSolicitud){
            cambioMoneda(idSolicitud, monedaNueva);
            
                params.put("idSolicitud", idSolicitud);
                try{
                    execute(params, "cambiarMonedaContratosSolicitud");
                }catch(Exception e){
                    logger.warning("ERROR al ejecutar el OperactionBinding de ", e);
                }
                
            
                guardarDatosContratoDesembolso(null);
            }else{
                logger.warning("Error al recuperar la solicitud.");
                JSFUtils.addFacesErrorMessage("No se pudo realizar el cambio de moneda para todos los contratos. Error al recuperar la solicitud para recuperar los contratos correspondientes.");
            }
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorMontoProgramado());
            logger.warning("Termina metodo obtenerTipoMoneda");
        }
    
    public void obtenerTipoMonedaTransient(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo obtenerTipoMonedaTransient");
        Long idSolicitud = null;
        Integer idTcaTipoMoneda = null;
        Map<String, Object> params = new HashMap<>();
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        if(null != valueChangeEvent){
            valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
            logger.warning("Nuevo TipoMoneda: " + valueChangeEvent.getNewValue());
            
            try{
                idTcaTipoMoneda = (Integer) valueChangeEvent.getNewValue();
            }catch(Exception e){
                logger.warning("ERROR al recuperar el BoundAttribute de IdTcaTipoMoneda.", e);
            }
            
            ADFUtils.setBoundAttributeValue("IdTcaTipoMoneda", idTcaTipoMoneda);
            ADFUtils.setBoundAttributeValue("IdTcaTipoMonedaTransient", idTcaTipoMoneda);
        }else{
            logger.warning("ValueChangeEvent IdTcaTipoMoneda es NULL");
        }
        
        idSolicitud = contratoDesembolsoBean.getIdSolicitudTF();
        
        if(null != idSolicitud){
            params.put("idSolicitud", idSolicitud);
            try{
                execute(params, "cambiarMonedaContratosSolicitud");
            }catch(Exception e){
                logger.warning("ERROR al ejecutar el OperactionBinding de ", e);
            }
            
            guardarDatosContratoDesembolso(null);
        }else{
            logger.warning("Error al recuperar la solicitud.");
            JSFUtils.addFacesErrorMessage("No se pudo realizar el cambio de moneda para todos los contratos. Error al recuperar la solicitud para recuperar los contratos correspondientes.");
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorMontoProgramado());
        logger.warning("Termina metodo obtenerTipoMonedaTransient");
    }

    public void obtenerFechaEstimadaDesembolsar(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo obtenerFechaEstimadaDesembolsar");
        
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        
        if(null != valueChangeEvent){
            logger.warning("Nueva fechaEstimadaDesembolsar: " + valueChangeEvent.getNewValue());
            ADFUtils.setBoundAttributeValue("FechaEstimadaDesembolsar", valueChangeEvent.getNewValue());
        }else{
            logger.warning("ValueChangeEvent FechaEstimadaDesembolsar es NULL");
        }
        
        guardarDatosContratoDesembolso(null);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorMontoProgramado());
        logger.warning("Termina metodo obtenerFechaEstimadaDesembolsar");
    }

    public void setProgramadoUIC(RichSelectOneChoice programadoUIC) {
        this.programadoUIC = programadoUIC;
    }

    public RichSelectOneChoice getProgramadoUIC() {
        return programadoUIC;
    }

    public void setDisponibilidadRecursosUIC(RichInputDate disponibilidadRecursosUIC) {
        this.disponibilidadRecursosUIC = disponibilidadRecursosUIC;
    }

    public RichInputDate getDisponibilidadRecursosUIC() {
        return disponibilidadRecursosUIC;
    }

    public void setCampoProgramadoUIC(RichInputText campoProgramadoUIC) {
        this.campoProgramadoUIC = campoProgramadoUIC;
    }

    public RichInputText getCampoProgramadoUIC() {
        return campoProgramadoUIC;
    }

    public void setPanelGroupContratoFormUIC(RichPanelGroupLayout panelGroupContratoFormUIC) {
        this.panelGroupContratoFormUIC = panelGroupContratoFormUIC;
    }

    public RichPanelGroupLayout getPanelGroupContratoFormUIC() {
        return panelGroupContratoFormUIC;
    }

    public void setPanelTabbedUIC(RichPanelTabbed panelTabbedUIC) {
        this.panelTabbedUIC = panelTabbedUIC;
    }

    public RichPanelTabbed getPanelTabbedUIC() {
        return panelTabbedUIC;
    }

    public void setAlertaLimitesPopUpUIC(RichPopup alertaLimitesPopUpUIC) {
        this.alertaLimitesPopUpUIC = alertaLimitesPopUpUIC;
    }

    public RichPopup getAlertaLimitesPopUpUIC() {
        return alertaLimitesPopUpUIC;
    }

    public void setEsErrorLimiteMonto(Boolean esErrorLimiteMonto) {
        this.esErrorLimiteMonto = esErrorLimiteMonto;
    }

    public Boolean getEsErrorLimiteMonto() {
        return esErrorLimiteMonto;
    }

    public void setEsErrorLimiteTasa(Boolean esErrorLimiteTasa) {
        this.esErrorLimiteTasa = esErrorLimiteTasa;
    }

    public Boolean getEsErrorLimiteTasa() {
        return esErrorLimiteTasa;
    }

    public void setConfirmarInicioDesembolsoPopupUIC(RichPopup confirmarInicioDesembolsoPopupUIC) {
        this.confirmarInicioDesembolsoPopupUIC = confirmarInicioDesembolsoPopupUIC;
    }

    public RichPopup getConfirmarInicioDesembolsoPopupUIC() {
        return confirmarInicioDesembolsoPopupUIC;
    }

    public void cerrarPopupIniciarDesembolso(ActionEvent actionEvent) {
        getConfirmarInicioDesembolsoPopupUIC().hide();
    }

    public void setEsErrorValidacion(Boolean esErrorValidacion) {
        this.esErrorValidacion = esErrorValidacion;
    }

    public Boolean getEsErrorValidacion() {
        return esErrorValidacion;
    }

    public Boolean actualizaDatosGenerales(Integer modalidad, Boolean mostrarMensajes){

        Boolean resultado=Boolean.TRUE;
        if(modalidad.compareTo(1)==0){
                Integer condicion1=null;
                condicion1= actualizarDatosGenerales(modalidad);
                if(null!=condicion1){
                    if(condicion1.compareTo(1)==0){
                        if(mostrarMensajes){
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_ACTUALIZAR_DATOS"));
                        }
                        logger.warning("Error al actulizar los datos del contrato intente mas tarde en datos generales");
                           resultado = Boolean.FALSE;
                        }
                    if(condicion1.compareTo(2)==0){
                            if(mostrarMensajes){
                            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_VALIDACION_DATOS"));
                            }
                            logger.warning("Es necesario capturar los datos de la herramienta de clasificación estratégica");
                            resultado = Boolean.FALSE;
                        }
                    }
                else{
                        if(mostrarMensajes){
                        JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_ACTUALIZAR_DATOS"));
                        }
                        logger.warning("Error al actulizar los datos del contrato intente mas tarde en datos generales");
                       resultado = Boolean.FALSE;
                    }
            }
           
            return resultado;
        
        }
    
    public Integer actualizarDatosGenerales(Integer opcion){
            ContratoDesembolsosBean contratoDesembolsoBean =
                (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");

        Integer retorna=1;
        Map resultado=new HashMap<String, Object>();
            Boolean registro = Boolean.FALSE;
            Boolean validoCampos=Boolean.FALSE;
            Boolean actualizoDatos=Boolean.FALSE;
            Boolean actualizaProyecto = Boolean.FALSE;
            Integer modalidad=1;
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("validarCampos");
            operationBinding.getParamsMap().put("aplicaProyecto", actualizaProyecto);
            operationBinding.getParamsMap().put("valida", opcion);
            operationBinding.getParamsMap().put("idOperacion", contratoDesembolsoBean.getIdOperacionTF());
            operationBinding.getParamsMap().put("idContratoActual", contratoDesembolsoBean.getIdContratoDesembolso());
            operationBinding.getParamsMap().put("modalidadActual", modalidad);
            operationBinding.getParamsMap().put("codigoTarea", contratoDesembolsoBean.getIdTareaBPMTF());

            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("obtuvo problemas al actualizar");
            } else {
                resultado = (HashMap<String, Object>) operationBinding.getResult();
                if (null != (Boolean) resultado.get("actualiza")) {
                    actualizoDatos=(Boolean) resultado.get("actualiza");
                    if(actualizoDatos){
                            validoCampos=(Boolean) resultado.get("valida");
                            if (validoCampos) {
                                retorna=3;
                                logger.warning("Se actualizo correctamente");
                            } else {
                                retorna=2;                            }
                        }
                }
            }
        return retorna;
        }
    
    
    public Boolean validarDatosGeSinGuardar(){
        Boolean respuesta=Boolean.TRUE;
        //Se omite la validacion temporal
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        Integer modalidad=1;
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("validarCamposSinGuardar");
            operationBinding.getParamsMap().put("idOperacion", contratoDesembolsoBean.getIdOperacionTF());
            operationBinding.getParamsMap().put("idContratoActual", contratoDesembolsoBean.getIdContratoDesembolso());
            operationBinding.getParamsMap().put("modalidadActual", modalidad);
            operationBinding.getParamsMap().put("codigoTarea", contratoDesembolsoBean.getIdTareaBPMTF());
            
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                respuesta=Boolean.FALSE;
                logger.warning("obtuvo problemas operationBinding validarCamposSinGuardar->"+operationBinding.getErrors().toString());                
                JSFUtils.addFacesErrorMessage("Error al validar campos ->"+operationBinding.getErrors());
            } else {
                respuesta = (Boolean) operationBinding.getResult();
            }
            
            if(!respuesta)
                JSFUtils.addFacesErrorMessage("Es necesario capturar la totalidad de datos obligatorios de la sección Datos generales.");

        return respuesta;
        }
    
    public Boolean actualizarCargos(){
            ContratoDesembolsosBean contratoDesembolsoBean =
                (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");

        Boolean retorna=Boolean.FALSE;

            BindingContainer bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("aplicarGuardado");
            operationBinding.getParamsMap().put("idContrato", contratoDesembolsoBean.getIdContratoDesembolso());

           retorna=(Boolean) operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                logger.warning("obtuvo problemas al actualizar");
            } 
            logger.warning("actualizo datos de cargos? "+retorna);
        return retorna;
        }
    
    
    public void inicializarTree(Long idOperacion, Long idSolicitud){
            logger.warning("Inf, Inicia metodo inicializarTree");
            
            if(idOperacion == null || idSolicitud == null){
                logger.warning("***Error, parametros requeridos son resueltos a null");
                logger.warning("***Error, idOperacion->"+idOperacion);
                logger.warning("***Error, idSolicitud->"+idSolicitud);
            }else{
            
                BindingContainer bindings = getBindings();
                try{                    
                   OperationBinding operationBinding = bindings.getOperationBinding("inicializarTree");
                    operationBinding.getParamsMap().put("idOperacion", idOperacion);             
                    operationBinding.getParamsMap().put("idSolicitud", idSolicitud);               
                    operationBinding.execute();      
                    
                }catch(Exception e){
                   logger.log(ADFLogger.ERROR, "Error al inicializarTree ", e);               
                }
            }
               logger.warning("Inf, Termina metodo inicializarTree");
        }


    public void obtenerSumaContratosSolicitud(Long idSolicitud){
        logger.warning("Inicia metodo obtenerSumaContratosSolicitud");
    
        BindingContainer bindings = getBindings();       
        try {
            OperationBinding operationBinding = bindings.getOperationBinding("obtenerMontosSolicitud");        
             operationBinding.getParamsMap().put("idSolicitud", idSolicitud);               
               operationBinding.execute();         
        }catch(Exception e){
                   logger.log(ADFLogger.ERROR, "Error al obtenerSumaContratosSolicitud" + e.getClass() +
                              " : " + e.getMessage()); 
        }

        logger.warning("Termina metodo obtenerSumaContratosSolicitud");
    }
    
    public Boolean getMontoTotal(BigDecimal montoDesembolsarAttr) {
        RowSetIterator iter = null;
        BigDecimal totalMonto = null;
        BigDecimal montoDesembolsar = null;
        Boolean montoCorrespondeContrato = Boolean.FALSE;
        
        iter =
            ADFUtils.getDCBindingContainer().findIteratorBinding("FuentesExternasContratoDesembolsoVOIterator").getRowSetIterator();

        if (null != iter) {
            logger.warning("Iterando FuentesExternasContratoDesembolsoVOIterator");
            for (Row row : iter.getAllRowsInRange()) {
                if (null != row.getAttribute("MontoDesembolsar")) {
                    logger.warning("Sumando Monto a desembolsar de Linea Pasiva " + row.getAttribute("IdLineaPasiva") +
                                   ": " + row.getAttribute("MontoDesembolsar"));
                    montoDesembolsar = new BigDecimal(row.getAttribute("MontoDesembolsar").toString());
                    logger.warning("Monto a desembolsar: " + montoDesembolsar);
                    if(null == totalMonto){
                        totalMonto = montoDesembolsar;
                    }else{
                        totalMonto = totalMonto.add(montoDesembolsar);
                    }
                }
            }

            logger.warning("Total de montos: " + totalMonto);
            
            if(null != totalMonto){
                if (null != montoDesembolsarAttr) {
                    if (totalMonto.compareTo(montoDesembolsarAttr)==0) {
                        montoCorrespondeContrato = Boolean.TRUE;
                    }
                }else{
                    logger.warning("montoDesembolsarAttr o montoTotal son NULL.");
                }
            }else{
                logger.warning("El montoTotal de la asignacion de recursos es NULL.");
                montoCorrespondeContrato = Boolean.TRUE;
            }
        } else {
            logger.warning("Iterador es NULL");
            JSFUtils.addFacesErrorMessage("getMontoTotal(): No se pudo validar correctamente el Monto total a desembolsar contra el Monto del contrato de desembolso.");
        }
        
        return montoCorrespondeContrato;
    }

    public void setContenedorMontoProgramado(RichPanelGroupLayout contenedorMontoProgramado) {
        this.contenedorMontoProgramado = contenedorMontoProgramado;
    }

    public RichPanelGroupLayout getContenedorMontoProgramado() {
        return contenedorMontoProgramado;
    }

    public void recargaCargos() {
        logger.warning("Inicia recarga Cargos");
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("getIdProductoFlexcubeByIdContrato");
        String resupuesta = (String) operationBinding.execute();
        if (contratoDesembolsoBean.getCodigoExterno() == null) {
            try {
                if (null != resupuesta) {
                    contratoDesembolsoBean.setCodigoExterno(resupuesta);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(regionCargosUIC);
                }
            } catch (Exception e) {
                logger.log(ADFLogger.ERROR,
                           "Error al recuperar el codigo de producto" + e.getClass() + " : " + e.getMessage());
            }
        } else {
            if (null != resupuesta) {
                if (!contratoDesembolsoBean.getCodigoExterno().equalsIgnoreCase(resupuesta)) {
                    contratoDesembolsoBean.setCodigoExterno(resupuesta);
                    try {
                        contratoDesembolsoBean.setCodigoExterno(resupuesta);
                        AdfFacesContext.getCurrentInstance().addPartialTarget(regionCargosUIC);

                    } catch (Exception e) {
                        logger.log(ADFLogger.ERROR,
                                   "Error al recuperar el codigo de producto" + e.getClass() + " : " + e.getMessage());
                    }
                }
            }
        }

    }

    public void recargaDatosGenerales2() {
        logger.warning("Inicia recarga Cargos");
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("cargarDatosDesembolso");
        String resupuesta = (String) operationBinding.execute();

    }

    public void recargaDatosGenerales() {

        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        if (contratoDesembolsoBean.getIdContratoDesembolso() == null) {
            logger.warning("Inicia recarga Datos generales debido al id de contrato");
            try {
                Long contrato = (Long) ADFUtils.getBoundAttributeValue("Id");
                logger.warning("id de contrato: " + contrato);
                contratoDesembolsoBean.setIdContratoDesembolso(contrato);
                AdfFacesContext.getCurrentInstance().addPartialTarget(regionDatosGeneralesUIC);

            } catch (Exception e) {
                logger.log(ADFLogger.ERROR,
                           "Error al recuperar el codigo de producto" + e.getClass() + " : " + e.getMessage());
            }
        } else {
            logger.warning("Inicia recarga Datos generales");
            recargaDatosGenerales2();
            AdfFacesContext.getCurrentInstance().addPartialTarget(regionDatosGeneralesUIC);
        }
    }
    
    public void recargarAsignacionRecursos(){
        logger.warning("Inicia metodo recargarAsignacionRecursos.");
        
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        Map<String, Object> mapaParametros = new HashMap<String, Object>();
        
        oracle.jbo.domain.Number pIdLineaCredito = null;
        try {
            pIdLineaCredito =
                new oracle.jbo.domain.Number(contratoDesembolsoBean.getIdLinea().toString());
        } catch (Exception e) {
            logger.warning("Error al castear parametro pIdLineaCredito");
        }
        
        logger.warning("IdLineaCredito de TaskFlow: " + pIdLineaCredito);
        logger.warning("IdContrato de TaskFlow: " + contratoDesembolsoBean.getIdContratoDesembolso());
        logger.warning("IdTareaBPM de TaskFlow: " + contratoDesembolsoBean.getIdTareaBPMTF());
        
        mapaParametros.put("idContrato", contratoDesembolsoBean.getIdContratoDesembolso());
        mapaParametros.put("idLineaCredito", pIdLineaCredito);
        mapaParametros.put("idTareaBPM", contratoDesembolsoBean.getIdTareaBPMTF());
        
        try{
            //Datos de prueba
            //pIdTareaBPM = 153;
            if (cargarFuentesExternas(contratoDesembolsoBean.getIdTareaBPMTF())) {
                logger.warning("Ejecutando OperationBinding de obtenerDatosFuentesExternas");
                super.execute(mapaParametros, "obtenerDatosFuentesExternas");
            } else {
                logger.warning("No se ejecuta obtenerDatosFuentesExternas");
            }
        }catch(Exception e){
            logger.warning("Error al ejecutar operationBinding ", e);
            JSFUtils.addFacesErrorMessage("ERROR al ejecutar obtenerDatosFuentesExternas: " + e.getMessage());
        }
        
        logger.warning("Termina metodo recargarAsignacionRecursos.");
    }
    
    private boolean cargarFuentesExternas(Integer idTarea) {
        logger.warning("Entrando en cargarFuentesExternas.");
        logger.warning("idTarea: " + idTarea);
        
        boolean cargarFuentesExternas = Boolean.TRUE;
        
        if(null != idTarea){
            
            switch(idTarea.intValue()) {
            case FenixConstants.CGD_LIQUIDAR_CONTRATO_DE_DESEMBOLSO:
                cargarFuentesExternas = Boolean.FALSE;
                break;
            case FenixConstants.CGD_VALIDAR_FONDOS_PRESUPUESTARIOS:
                cargarFuentesExternas = Boolean.FALSE;
                break;
            }
            
        }
        
        return cargarFuentesExternas;
    }

    public void setRegionCargosUIC(RichRegion regionCargosUIC) {
        this.regionCargosUIC = regionCargosUIC;
    }

    public RichRegion getRegionCargosUIC() {
        return regionCargosUIC;
    }

    public void setRegionDatosGeneralesUIC(RichRegion regionDatosGeneralesUIC) {
        this.regionDatosGeneralesUIC = regionDatosGeneralesUIC;
    }

    public RichRegion getRegionDatosGeneralesUIC() {
        return regionDatosGeneralesUIC;
    }

    public void setRegionTransferenciasReg(RichRegion regionTransferenciasReg) {
        this.regionTransferenciasReg = regionTransferenciasReg;
    }

    public RichRegion getRegionTransferenciasReg() {
        return regionTransferenciasReg;
    }

    public void setPglRegionAsignRec(RichPanelGroupLayout pglRegionAsignRec) {
        this.pglRegionAsignRec = pglRegionAsignRec;
    }

    public RichPanelGroupLayout getPglRegionAsignRec() {
        return pglRegionAsignRec;
    }

    public void cambiarFondoContrato(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo cambiarFondoContrato");
        String fondo = null;
        
        if(null != valueChangeEvent && null != valueChangeEvent.getNewValue()){
            try{
                fondo = (String) valueChangeEvent.getNewValue();
            }catch(Exception e){
                logger.warning("Fondo Bound Attribute es NULL.", e);
            }
        }
        
        ADFUtils.setBoundAttributeValue("Fondo", fondo);
        ADFUtils.setBoundAttributeValue("FondoTransient", fondo);
        
        guardarDatosContratoDesembolso(null);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorMontoProgramado());
        AdfFacesContext.getCurrentInstance().addPartialTarget(pglRegionAsignRec);
        
        // Validamos que el tab se este mostrando en pantalla
        if (null != regionAsignacionRecursosUIC) {
            regionAsignacionRecursosUIC.refresh(FacesContext.getCurrentInstance());
        }
        
        logger.warning("Termina metodo cambiarFondoContrato");
    }

    public void cambiarFondoContratoTransient(ValueChangeEvent valueChangeEvent) {
        logger.warning("Inicia metodo cambiarFondoContratoTransient");
        String fondo = null;
        
        if(null != valueChangeEvent && null != valueChangeEvent.getNewValue()){
            try{
                fondo = (String) valueChangeEvent.getNewValue();
            }catch(Exception e){
                logger.warning("Fondo Bound Attribute es NULL.", e);
            }
        }
        logger.warning("Nuevo Fondo: " + fondo);
        
        ADFUtils.setBoundAttributeValue("Fondo", fondo);
        ADFUtils.setBoundAttributeValue("FondoTransient", fondo);
        
        guardarDatosContratoDesembolso(null);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getContenedorMontoProgramado());
        AdfFacesContext.getCurrentInstance().addPartialTarget(pglRegionAsignRec);
        regionAsignacionRecursosUIC.refresh(FacesContext.getCurrentInstance());
        logger.warning("Termina metodo cambiarFondoContratoTransient");
    }

    public void setRegionAsignacionRecursosUIC(RichRegion regionAsignacionRecursosUIC) {
        this.regionAsignacionRecursosUIC = regionAsignacionRecursosUIC;
    }

    public RichRegion getRegionAsignacionRecursosUIC() {
        return regionAsignacionRecursosUIC;
    }

    public void setInitForm(RichOutputText initForm) {
        this.initForm = initForm;
    }

    public RichOutputText getInitForm() {
        Integer pIdTarea = null;
        if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}") != null){
            pIdTarea = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBPM}").toString());
        }else{
            logger.warning("IdTareaBPM es NULL");
        }
        
        if (pIdTarea != null) {
            if (pIdTarea.compareTo(FenixConstants.CGD_VALIDAR_CLASIFICACION_ESTRATEGICA_Y_USO_DE_RECURSOS) == 0) {
          //      recargaDatosGenerales();
                logger.warning("Refresh - Datos Generales.");
            }
        }
        return initForm;
    }

    public void setInitPanelTab(RichOutputText initPanelTab) {
        this.initPanelTab = initPanelTab;
    }

    public RichOutputText getInitPanelTab() {
//        ContratoDesembolsosBean contratoDesembolsoBean =
//            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
//        contratoDesembolsoBean.configurarFormularioContrato();
//        
//        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        return initPanelTab;
    }

    public void setRegionReservaUI(RichRegion regionReservaUI) {
        this.regionReservaUI = regionReservaUI;
    }

    public RichRegion getRegionReservaUI() {
        return regionReservaUI;
    }

    public void setTabReservaUI(RichShowDetailItem tabReservaUI) {
        this.tabReservaUI = tabReservaUI;
    }

    public RichShowDetailItem getTabReservaUI() {
        return tabReservaUI;
    }

    public void setTabCoberturaUI(RichShowDetailItem tabCoberturaUI) {
        this.tabCoberturaUI = tabCoberturaUI;
    }

    public RichShowDetailItem getTabCoberturaUI() {
        return tabCoberturaUI;
    }

    public void cambioTabCobertura(DisclosureEvent disclosureEvent) {
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        disclosureEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());

        logger.warning("Entra a cobertura");
        logger.warning("guardo? " + contratoDesembolsoBean.getGuardaTab());
        logger.warning("Tab anterior " + contratoDesembolsoBean.getValorAnteriorTab());
        logger.warning("Tab cobertura " + contratoDesembolsoBean.getTabCoberturas());

        if (contratoDesembolsoBean.getGuardaTab()) {
            if (contratoDesembolsoBean.getTabCoberturas()) {
                logger.warning("sale y guarda cobertura");
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_COBERTURAS);  //tab de reserva de fondos
                contratoDesembolsoBean.setGuardaTab(Boolean.TRUE); //Metodo para guardar el tab con la informacion incluyendo validaciones en caso de tenerlo
              //  contratoDesembolsoBean.setTabReservaFondos(Boolean.FALSE);
                configurarCambioTab();
            } else {
                logger.warning("inicia cobertura");
                contratoDesembolsoBean.setTabCoberturas(Boolean.TRUE);
                //recarga de transferencias
                AdfFacesContext.getCurrentInstance().addPartialTarget(regionCoberturaUI);
                regionCoberturaUI.refresh(FacesContext.getCurrentInstance());
            }
        } else {
            tabCoberturaUI.setDisclosed(false);
            contratoDesembolsoBean.setDisclosedTabCoberturas(Boolean.FALSE);
            contratoDesembolsoBean.setGuardaTab(Boolean.TRUE);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tabCoberturaUI);
            switch (contratoDesembolsoBean.getValorAnteriorTab()) {
            case FenixConstants.TAB_ASIGNACION_RECURSOS:
                contratoDesembolsoBean.setTabAsignacionRecursos(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabAsignacionRecursos(Boolean.TRUE);
                logger.warning("regresa Asignacion de recursos");
                tabAsignacionUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabAsignacionUI);
                break;
            case FenixConstants.TAB_DATOS_GENERALES:
                contratoDesembolsoBean.setDisclosedTabDatosGenerales(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa datos generales");
                tabDatosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabDatosUI);
                break;
            case FenixConstants.TAB_CONDICIONES_FINANCIERAS:
                contratoDesembolsoBean.setDisclosedTabCondicionesFinancieras(Boolean.TRUE);
                contratoDesembolsoBean.setTabCondicionesFinancieras(Boolean.TRUE);
                logger.warning("regresa condiciones financieras");
                tabCondicionesUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCondicionesUI);
                break;
            case FenixConstants.TAB_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setDisclosedTabTransferencias(Boolean.TRUE);
                logger.warning("regresa a transferencias");
                tabTransferenciasUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabTransferenciasUI);
                break;
            case FenixConstants.TAB_CARGOS:
                contratoDesembolsoBean.setDisclosedTabCargos(Boolean.TRUE);
                contratoDesembolsoBean.setTabCargos(Boolean.TRUE);
                logger.warning("regresa a cargos");
                tabCargosUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabCargosUI);
                break;
            case FenixConstants.TAB_LIQUIDAR_CONTRATO:
                contratoDesembolsoBean.setDisclosedTabLiquidarContrato(Boolean.TRUE);
                contratoDesembolsoBean.setTabLiquidarContrato(Boolean.TRUE);
                logger.warning("regresa liquidar contrato");
                tabLiquidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabLiquidarUI);
                break;
            case FenixConstants.TAB_CONSOLIDAR_TRANSFERENCIAS:
                contratoDesembolsoBean.setDisclosedTabConsolidarTransferencias(Boolean.TRUE);
                contratoDesembolsoBean.setTabConsolidarTransferencias(Boolean.TRUE);
                logger.warning("regresa a consolidar transferencias");
                tabConsolidarUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabConsolidarUI);
                break;
            case FenixConstants.TAB_RESERVA_FONDOS:
                contratoDesembolsoBean.setDisclosedTabReservaFondos(Boolean.TRUE);
                contratoDesembolsoBean.setTabReservaFondos(Boolean.TRUE);
                logger.warning("regresa a reserva fondos");
                tabReservaUI.setDisclosed(true);
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabReservaUI);
                break;
            default:
                contratoDesembolsoBean.setValorAnteriorTab(FenixConstants.TAB_COBERTURAS);
                contratoDesembolsoBean.setDisclosedTabCoberturas(Boolean.TRUE);
                contratoDesembolsoBean.setTabCoberturas(Boolean.TRUE);
                break;
            }
        }
        // Add event code here...
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelTabsContrato);
        // Add event code here...
    }
    
    public String actualizarDatosTabsEnvioCobroActionListener() {
        logger.warning("Entra en actualizarDatosTabsEnvioCobroActionListener.");
        ContratoDesembolsosBean contratoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        Boolean resultado = null;
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = bindings.getOperationBinding("actualizarDatosContratoDesembolso");
            logger.warning("Ejecutando OperationBinding de precargaInformacionContrato");
            resultado = (Boolean) operationBinding.execute();

            if (operationBinding.getErrors().isEmpty()) {
                logger.warning("El operationBinding se ejecuto correctamente");
                if (resultado) {
                    logger.warning("La actualizacion del contrato se realizo correctamente");
                } else {
                    logger.warning("La actualizacion NO se realizo correctamente");
                    JSFUtils.addFacesErrorMessage(getStringFromBundle("ERROR_GUARDAR_CAMBIOS_MSG"));
                }
            } else {
                logger.warning("El operationBinding devuelve errores" + operationBinding.getErrors().toString());
            }

            //Aplica guardado de datos generales y cargos
            if (contratoBean.getRenderTabCargos().compareTo(1) == 0) {
                //  guardado de cargos
                actualizarCargos();
            }
            if (contratoBean.getRenderTabDatosGenerales().compareTo(1) == 0) {
                //  guardado de datos generales
                Integer modalidades = 1;
                actualizaDatosGenerales(modalidades, Boolean.FALSE);
            }
            if (contratoBean.getRenderTabCondicionesFinancieras().compareTo(1) == 0) {
                //  guardado de condiciones financieras
                guardarCondicionesFinancierasTab();
            }
        } catch (Exception e) {
            logger.warning("Error al ejecutar el OperationBinding", e);
        }
        return null;
    }
    
    public Boolean guardarCondicionesFinancierasTab(){
        logger.warning("Entra en guardarCondicionesFinancierasTab");
        Boolean esDatosGuardados = Boolean.FALSE;
        try{
            esDatosGuardados = execute(null, "validarDatosRequeridos");
            if(!esDatosGuardados){
                logger.warning("Error al guardar los datos de condiciones financieras, existen valores nulos");
                JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_AL_GUARDAR_CONDICIONES_FINANCIERAS"));
            }
                
        }catch(Exception e){
            logger.warning("Error al validar los datos de condiciones financieras.", e);
        }
        logger.warning("Valor de retorno al guardar registro :" + esDatosGuardados);
        return esDatosGuardados;
    }


    public void setRegionCoberturaUI(RichRegion regionCoberturaUI) {
        this.regionCoberturaUI = regionCoberturaUI;
    }

    public RichRegion getRegionCoberturaUI() {
        return regionCoberturaUI;
    }

    public void obtenerFechaEfectivaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.warning("Entra en obtenerFechaEfectivaValueChangeListener.");
        try{ 
                valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
                
                if(null != valueChangeEvent){
                    logger.warning("Valor de la fecha efectiva :" + valueChangeEvent.getNewValue());
                    ADFUtils.setBoundAttributeValue("FechaEfectiva", valueChangeEvent.getNewValue());
                }else{
                    logger.warning("El valor de la FechaEfectiva es nula.");
                }
            
        }catch(Exception e){
            logger.warning("Error en obtenerFechaEfectivaValueChangeListener.", e);
        }
    }
    
    public Boolean asignarProgramaDestino(){
        logger.warning("Inicia metodo asignarProgramaDestino");
        Boolean resultado = Boolean.FALSE;
        Boolean esIfi = null;
        ContratoDesembolsosBean contratoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        try{
            esIfi = execute(null, "validarEsIFI");
        }catch(Exception e){
            logger.warning("ERROR al ejecutarOperationBinding validarEsIFI.", e);
            JSFUtils.addFacesErrorMessage("ERROR al ejecutarOperationBinding validarEsIFI: " + e.getMessage());
        }
        
        if(null != esIfi){
            if(!esIfi){
                logger.warning("La operacion NO tiene producto IFI. Se asignara PROGRAMA_OPERACION y DESTINO_FINANCIAMIENTO.");
                try {
                    execute(null, "actualizarProgramaOperacionDestinoFinaciamiento");
                    resultado = Boolean.TRUE;
                } catch (Exception e) {
                    logger.warning("ERROR al ejecutar operBinding actualizarProgramaOperacionDestinoFinaciamiento.", e);
                    JSFUtils.addFacesErrorMessage("ERROR al ejecutar operBinding actualizarProgramaOperacionDestinoFinaciamiento: " + e.getMessage());
                }
            }else{
                logger.warning("La opercion tiene producto IFI. No se asignara PROGRAMA_OPERACION y DESTINO_FINANCIAMIENTO.");
                resultado = Boolean.TRUE;
            }
        }else{
            logger.warning("Ocurrio un error al validar si el producto de la operacion es IFI.");
            JSFUtils.addFacesErrorMessage("Ocurrio un error al validar si el producto de la operacion es IFI.");
        }
        
        logger.warning("Termina metodo asignarProgramaDestino");
        return resultado;
    }

    public void setAlertaProgramacionUIC(RichPopup alertaProgramacionUIC) {
        this.alertaProgramacionUIC = alertaProgramacionUIC;
    }

    public RichPopup getAlertaProgramacionUIC() {
        return alertaProgramacionUIC;
    }


    public Boolean calcularPlazosPorEspecificacionFechas(){
     logger.warning("Inf, Inicia metodo calcularPlazosPorEspecificacionFechas");
     Boolean resuesta = Boolean.FALSE;
     
        Integer especificadoFechas = 1;
        Integer especificadoPlazo = 2;
        Integer tipoCalendarioSencillo = 2;
        Integer tipoCalendarioComplejo = 1;
                        
         Row rowCondicionFinanciera = recuperarCondicionFinancieraActual();
         Row rowCFTemp = recuperarCFTemp();
                 
            if(especificacionFechas == null || tipoCalendario == null || idCondicionFinanciera == null){
                 logger.warning("*Error, parametros requeridos null ");
                 JSFUtils.addFacesErrorMessage("Error no se pudo recuperar los datos de la condicion financiera para el calulo de plazos");
            }else{                      
                if(especificacionFechas.compareTo(especificadoFechas) == 0){
                                                            
                       if(tipoCalendario.compareTo(tipoCalendarioSencillo) == 0){
                             logger.warning("*inf, se especifico un calculo por fechas, Calculando Plazos...");
                             
                           Boolean seAsociaronPlazosPago = getPlazoPago(rowCondicionFinanciera,rowCFTemp);
                           Boolean seAsociaronPlazosPeriodoGracia = getPlazoPeriodoGracia(rowCondicionFinanciera,rowCFTemp);
                             
                             if(seAsociaronPlazosPago && seAsociaronPlazosPeriodoGracia){
                                   resuesta = Boolean.TRUE;
                                   logger.warning("*Inf, ok se an calculado los plazos a pagar calendario sencillo");
                             }else{
                                 JSFUtils.addFacesErrorMessage("Error no se pudo calcular los plazos de la condicion financiera");
                             }
                       }else if(tipoCalendario.compareTo(tipoCalendarioComplejo) == 0){
                                                          
                               asociarFechasPagoPorCalendarioComplejo(idCondicionFinanciera);
                             
                           Boolean seAsociaronPlazosPago = getPlazoPago(rowCondicionFinanciera,rowCFTemp);
                           Boolean seAsociaronPlazosPeriodoGracia = getPlazoPeriodoGracia(rowCondicionFinanciera,rowCFTemp);
                           
                               if(seAsociaronPlazosPago && seAsociaronPlazosPeriodoGracia){
                                     resuesta = Boolean.TRUE;
                                     logger.warning("*Inf, Ok se han calculado los plazos a pagar calendario complejo");
                               }else
                                   JSFUtils.addFacesErrorMessage("Error no se pudo calcular los plazos de la condicion financiera");
                           
                       }else{
                            JSFUtils.addFacesErrorMessage("Error no se reconoce el tipo de calendario de la condicion financiera");
                            logger.warning("*Inf, No se reconoce el tipo de calendario de la condicion financiera");
                       } 
                
                }else{
                    resuesta = Boolean.TRUE;
                    logger.warning("*Inf, el tipo de especificacion no es por fechas no se realizara calculo de plazos");
                }
            }
        
     logger.warning("Inf, Termina metodo calcularPlazosPorEspecificacionFechas");
     return resuesta;
    }




    public Row recuperarCondicionFinancieraActual(){
    logger.warning("Inf, Inicia metodo recuperarCondicionFinancieraActual");
      Row fila = null;
      BindingContainer bindings = getBindings();
      OperationBinding operBinding = null;
      
        try {                                            
            operBinding = bindings.getOperationBinding("getCondicionFinancieraCurrent");               
            operBinding.execute();
        }catch (Exception e){
            logger.warning("Error, al ejecuter operBinding getCondicionFinancieraCurrent ->"+e);
        }   
            
            if(!operBinding.getErrors().isEmpty()){
                logger.warning("Error, al recuperar la condicion financiera del desembolso ->"+operBinding.getErrors().toString());
                JSFUtils.addFacesErrorMessage("Error, al recuperar la condicion financiera del desembolso ->"+operBinding.getErrors().toString());
            }else{
               fila = (Row)operBinding.getResult();
                if(fila != null){
                    
                    try{                        
                         especificacionFechas = (fila.getAttribute("IdTcaEspecificacionFecha") == null)? null
                                              : (Integer)fila.getAttribute("IdTcaEspecificacionFecha");
                        
                               tipoCalendario = (fila.getAttribute("IdTcaTipoCalendario") == null) ? null
                                              : (Integer)fila.getAttribute("IdTcaTipoCalendario");
                              
                        idCondicionFinanciera = (fila.getAttribute("Id") == null)? null
                                              : (Long)fila.getAttribute("Id");  
                    
                    }catch(Exception e){
                        logger.warning("*Inf, ha ocurrido un error al castear parametros ", e);
                    }
                         
                    logger.warning("*Inf, especificacionFechas: "+especificacionFechas);
                    logger.warning("*Inf, tipoCalendario: "+tipoCalendario);
                    logger.warning("*Inf, idCondicionFinanciera: "+idCondicionFinanciera);
                }
            }
            
        
    
    logger.warning("Inf, Termina metodo recuperarCondicionFinancieraActual");
    return fila;    
    }
    
    public Row recuperarCFTemp(){
    logger.warning("Inf, Inicia metodo recuperarCFTemp");
      Row fila = null;
      BindingContainer bindings = getBindings();
      OperationBinding operBinding = null;
      
        try {                                            
            operBinding = bindings.getOperationBinding("getCFTempVORowCurrent");               
            operBinding.execute();
        }catch (Exception e){
            logger.warning("Error, al ejecuter operBinding getCFTempVORowCurrent ->"+e);
        }   
            
            if(!operBinding.getErrors().isEmpty()){
                logger.warning("Error, al recuperar la CFTemp del desembolso ->"+operBinding.getErrors().toString());
                //JSFUtils.addFacesErrorMessage("Error, al recuperar la condicion financiera del desembolso ->"+operBinding.getErrors().toString());
            }else{
               fila = (Row)operBinding.getResult();
            }

    logger.warning("Inf, Termina metodo recuperarCFTemp");
    return fila;    
    }

    public Boolean getPlazoPago(Row condicionFinanciera,Row rowCFTemp) {
        logger.warning("*Inf, Inicia el metodo getPlazoPago");
        Boolean respuesta = Boolean.FALSE;
        java.util.Date fechaDeInicio = null;
        java.util.Date fechaDeTermino = null;
        Map datosPlazo = new HashMap();
        Integer frecuanciaPlazo = null;
        Integer idTcaPlazo = null;

        if (condicionFinanciera == null) {
            logger.warning("*Error, no se pudo recuperar los datos de la condicion financiera current");
            return respuesta;
        }
        
        if (rowCFTemp == null) {
            logger.warning("*Error, no se pudo recuperar los datos de rowCFTemp");
            return respuesta;
        }

        /**  Recuperamos las fechas de las cuales tomaremos el rango **/

        java.sql.Timestamp fechaFinal =
            (null == condicionFinanciera.getAttribute("FechaVencimiento")) ? null :
            (java.sql.Timestamp) condicionFinanciera.getAttribute("FechaVencimiento");
        
        Integer idTcaFrecPagoCapital = (null == rowCFTemp.getAttribute("IdTcaFrecuenciaPagoCapital")) ? null :
            (Integer) rowCFTemp.getAttribute("IdTcaFrecuenciaPagoCapital");

        logger.warning("*Inf, fecha de vencimiento: " + fechaFinal);

        if (fechaFinal == null) {
            logger.warning("***Error, la fecha de primer pago capital y la fecha vencimiento son requeridas");
            JSFUtils.addFacesErrorMessage("Error, la fecha de primer pago capital y la fecha vencimiento son requeridas");
            return respuesta;
        }

        // Para el cálculo de fecha inicio se tomará la FechaEstimadaDesembolsar para calcular los Plazos como se hace en la opción de Plazos
        Date fechaDesembolso = (null == ADFUtils.getBoundAttributeValue("FechaEstimadaDesembolsar")) ? null :
            ((Date) ADFUtils.getBoundAttributeValue("FechaEstimadaDesembolsar") );

        fechaDeInicio = (java.util.Date)fechaDesembolso.getValue() ;
    

        logger.warning("*** Inf, FechaEstimadaDesembolsar para plazo: " + fechaDeInicio);				 
        fechaDeTermino = new java.util.Date(fechaFinal.getTime());

        if (fechaDeInicio.compareTo(fechaDeTermino) == 1) {
            JSFUtils.addFacesErrorMessage("Error, la fecha de primer pago capital no puede ser mayor a la fecha vencimiento");
            return respuesta;
        }

        if (fechaDeInicio.compareTo(fechaDeTermino) == 0) {
            logger.warning("La fecha de Inicio y la fecha de termino son iguales.");
            frecuanciaPlazo = 0;
            idTcaPlazo = 1;
        } else {
            logger.warning("Se calculara Plazo entre fechas.");
            
            /**  Calulamos el Plazo entre Fechas **/
            try{
                datosPlazo = (Map) calcularPlazosEntreFechas(fechaDeInicio, fechaDeTermino);
                frecuanciaPlazo = (Integer) datosPlazo.get("frecuanciaPlazo");
                idTcaPlazo = (Integer) datosPlazo.get("idTcaPlazo");
            }catch(Exception e){
                logger.warning("ERROR al recuperar FrecuenciaPlazo y idTcaPlazo.", e);
            }
        }
        
        if (frecuanciaPlazo != null && idTcaPlazo != null) {
            logger.warning("*Inf ok, frecuanciaPlazo: " + frecuanciaPlazo + "  idTcaPlazo: " + idTcaPlazo);

            /**  Insetamos el Plazo en BD **/
            respuesta = asociarPlazoPagoAContrato(frecuanciaPlazo, idTcaPlazo);

        } else {
            logger.warning("*Inf, frecuanciaPlazo: " + frecuanciaPlazo);
            logger.warning("*Inf, idTcaPlazo: " + idTcaPlazo);
            logger.warning("***Error, frecuanciaPlazo y idTcaPlazo son requeridas");
        }

        logger.warning("*Inf, Termina el metodo getPlazoPago");
        return respuesta;
    }
    
    
    public Boolean getPlazoPeriodoGracia(Row condicionFinanciera,Row rowCFTemp){
        logger.warning("*Inf, Inicia metodo getPlazoPeriodoGracia");
        Boolean respuesta = Boolean.FALSE;
        java.util.Date fechaDeInicio = null;
        java.util.Date fechaDeTermino = null;
        Integer frecuanciaPlazo = null;
        Integer idTcaPlazo = null;
        
        
        if(condicionFinanciera == null){
            logger.warning("*Error, no se pudo recuperar los datos de la condicion financiera current");
            return respuesta;
        }
        
        if(rowCFTemp == null){
            logger.warning("*Error, no se pudo recuperar los datos de rowCFTemp");
            return respuesta;
        }
                
        /**  Recuperamos las fechas de las cuales tomaremos el rango **/
        
                java.util.Date fechaInicio  = obtenerFechaEstimadaAdesembolsar();
                            
            java.sql.Timestamp fechaFinal   = (null == rowCFTemp.getAttribute("FechaPrimerPagoCapital"))? null
                                            : (java.sql.Timestamp )rowCFTemp.getAttribute("FechaPrimerPagoCapital");
            
             Integer idTcaFrecPagoCapital   = (null == rowCFTemp.getAttribute("IdTcaFrecuenciaPagoCapital")) ? null 
                                            : (Integer) rowCFTemp.getAttribute("IdTcaFrecuenciaPagoCapital");
             
            Integer frecPagoCapital   = (null == rowCFTemp.getAttribute("FrecuenciaPagoCapital")) ? null 
                                           : (Integer) rowCFTemp.getAttribute("FrecuenciaPagoCapital");
                      
                                        
        logger.warning("*Inf, fecha estimada a desembolsar: " + fechaInicio);
        logger.warning("*Inf, fecha de primer pago capital: " + fechaFinal);
                
         if(fechaInicio == null || fechaFinal == null){
             logger.warning("***Error, la fecha de primer pago capital y la fecha vencimiento son requeridas");
             JSFUtils.addFacesErrorMessage("Error, la fecha de primer pago capital y la fecha vencimiento son requeridas");
             return respuesta;
         }
         
        logger.warning("*Inf, frecPagoCapital: "+frecPagoCapital);
        logger.warning("*Inf, idTcaFrecPagoCapital: "+idTcaFrecPagoCapital);
         
        if(frecPagoCapital == null){
            logger.warning("*Inf, frecPagoCapital null set valor 0"); 
            frecPagoCapital = new Integer(0);
        }
        
        if(idTcaFrecPagoCapital == null){
            idTcaFrecPagoCapital = new Integer(1);
            logger.warning("*Inf, idTcaFrecPagoCapital es null set valor 1");
        }
         
             fechaDeInicio =  fechaInicio; // fecha estimada a desembolsar
             fechaDeTermino = new java.util.Date(fechaFinal.getTime()); // fecha de primer pago capital

        logger.warning("*Inf, fecha de primer pago capital: "+ fechaDeTermino);
        logger.warning("*Inf, fecha de estimada a desembolsar: "+ fechaDeInicio );

        if(fechaDeInicio.compareTo(fechaDeTermino) == 1){
            logger.warning("***Error, la fecha Estimada a desembolsar no puede ser mayor a la fecha de primer pago capital");
            logger.warning("*Inf, fecha de primer pago capital: "+fechaDeInicio);
            logger.warning("*Inf, fecha de estimada a desembolsar: "+fechaDeTermino);
            JSFUtils.addFacesErrorMessage("Error, la fecha de primer pago de capital no puede ser menor a la fecha estimada a desembolsar.");
            return respuesta;
        }
        
        
        if (idTcaFrecPagoCapital.compareTo(new Integer(4)) == 0) {
            logger.warning("Se detecta FrecuenciaPlazo Al Vencimiento.");
            frecuanciaPlazo = 0;
            idTcaPlazo = 1;
        }else{
        
        /**  Calulamos el Plazo entre Fechas **/
        
              Map datosPlazo = (Map)calcularFrecuenciaPeriodoGracia(fechaDeInicio, fechaDeTermino, frecPagoCapital, idTcaFrecPagoCapital);
              frecuanciaPlazo = (Integer)datosPlazo.get("frecuanciaPlazo");
              idTcaPlazo = (Integer)datosPlazo.get("idTcaPlazo");
        }
         
              if(frecuanciaPlazo != null && idTcaPlazo != null){
                  
        /**  Insetamos el Plazo en BD **/
                  respuesta = asociarPlazoPeriodoGraciaAContrato(frecuanciaPlazo, idTcaPlazo);
                  
              }else{              
                  logger.warning("*Inf, frecuanciaPlazo: "+frecuanciaPlazo);
                  logger.warning("*Inf, idTcaPlazo: "+idTcaPlazo);
                  logger.warning("***Error, frecuanciaPlazo y idTcaPlazo son requeridas");                  
              }
                    
        logger.warning("*Inf, Termina metodo getPlazoPeriodoGracia");
        return respuesta;
    }

    
    public Map calcularPlazosEntreFechas(java.util.Date fechaDeInicio, java.util.Date fechaDeTermino){
        logger.warning("*Inf, Inicia el metodo calcularPlazosEntreFechas");  
        
        if(fechaDeInicio == null || fechaDeTermino == null){
                JSFUtils.addFacesErrorMessage("Error, al calcular el plazo entre fechas");
        }
        
        int tcaDias = 1, tcaMeses = 2, tcaAnios = 3;
        Integer frecuanciaPlazo = null;
        Integer idTcaPlazo = null;        
        Map datosPlazos = new HashMap();
         
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String fecha1 = formato.format(fechaDeInicio);
            String fecha2 = formato.format(fechaDeTermino);
            
            String fechaInic[] = fecha1.split("-");
            String fechaFina[] = fecha2.split("-");
            int[] fechaIni = new int[fechaInic.length];
            int[] fechaFin = new int[fechaFina.length];
        
            for(int i = 0; i < fechaInic.length; i++){              
               fechaIni[i] = Integer.parseInt(fechaInic[i]);
            }
            for(int i = 0; i < fechaFina.length; i++){              
               fechaFin[i] = Integer.parseInt(fechaFina[i]);
            }
                            
             /** --------- Se revisan las fechas para especificar un plazo por anos ------------**/
              
              if((fechaIni[0] == fechaFin[0])  && (fechaIni[1] == fechaFin[1]) ){
                  logger.warning("*Inf, Se realizara un calculo por anios"); 
                  
                  if(fechaIni[2] != fechaFin[2]){                      
                      if(fechaIni[2] < fechaFin[2]){                          
                          int numAnios =(fechaFin[2] - fechaIni[2]);
                          logger.warning("*Inf, El plazo es de "+numAnios+" año");
                          frecuanciaPlazo = new Integer(numAnios);  
                          idTcaPlazo = new Integer(tcaAnios);                          
                      }else{
                         logger.warning("*Inf, la fecha de inicio no puede ser mayor a la fecha de final");    
                         JSFUtils.addFacesErrorMessage("Error, la fecha de inicio no puede ser mayor a la fecha de final");
                      }                                        
                  }else{
                      logger.warning("*Inf, las fechas son iguales");
                  }                                    
              /** --------- Se revisan las fechas para especificar un plazo por meses ------------**/
              }else if(fechaIni[0] == fechaFin[0]){                  
                  logger.warning("*Inf, Se realizara un calculo por meses");                   
                   if(fechaIni[2] != fechaFin[2]){                      
                          if(fechaIni[2] < fechaFin[2]){
                              
                              int mesesDeAos = (fechaFin[2] - fechaIni[2]) * 12;                                                                
                              int meses = (fechaFin[1] - fechaIni[1]);                     
                                  meses = mesesDeAos + meses;                                  
                               
                               logger.warning("*Inf, El plazo es de "+meses+" meses" );
                               frecuanciaPlazo = new Integer(meses);
                               idTcaPlazo = new Integer(tcaMeses); 
                          }else{
                              logger.warning("*Inf, la fecha de inicio no puede ser mayor a la fecha de final");
                              JSFUtils.addFacesErrorMessage("Error, la fecha de inicio no puede ser mayor a la fecha de final");
                          }                                     
                   }else{                                      
                        if(fechaIni[1] < fechaFin[1]){
                              int meses = (fechaFin[1] - fechaIni[1]);                                                                            
                              logger.warning("*Inf, El plazo es de "+meses+" meses" );    
							  frecuanciaPlazo = new Integer(meses);
							  idTcaPlazo = new Integer(tcaMeses);  							  
                        }else{                        
                            logger.warning("*Inf, la fecha de inicio no puede ser mayor a la fecha de final");
                            JSFUtils.addFacesErrorMessage("Error, la fecha de inicio no puede ser mayor a la fecha de final");
                        }                      
                   }                                
              }else{                  
                  logger.warning("*Inf, Se realizara un calculo por dias");                 
                  try{
                      java.util.Date fechaIniciall = formato.parse(fechaIni[0]+"-"+fechaIni[1]+"-"+fechaIni[2]);
                      java.util.Date fechaFinall = formato.parse(fechaFin[0]+"-"+fechaFin[1]+"-"+fechaFin[2]);        
                      int dias = (int)((fechaFinall.getTime() - fechaIniciall.getTime())/86400000);        
                      logger.warning("*Inf, El pazo es de "+dias+" dias");
                     
                      frecuanciaPlazo = new Integer(dias);  
                      idTcaPlazo = new Integer(tcaDias);
                                          
                  }catch(Exception e){
                      logger.warning("*Inf, Ha ocurrido un error al obtener el plazo en dias ->",e);
                      JSFUtils.addFacesErrorMessage("Ha ocurrido un error al obtener el plazo en dias "+e);
                  }                  
              }     
              
        datosPlazos.put("frecuanciaPlazo", frecuanciaPlazo);
        datosPlazos.put("idTcaPlazo", idTcaPlazo);
        
        logger.warning("*Inf, valor de frecuanciaPlazo "+frecuanciaPlazo+" valor de idTcaPlazo"+idTcaPlazo);
        logger.warning("*Inf, Termina el metodo calcularPlazosEntreFechas");
        return datosPlazos;
    }

    public Map calcularFrecuenciaPeriodoGracia(java.util.Date fechaDeInicio, java.util.Date fechaDeTermino,
                                                Integer frecuenciaCapital, Integer idTcaFrecuenciaCapital) {
        logger.warning("Inicia metodo calcularFrecuenciaPeriodoGracia.");
        if (fechaDeInicio == null || fechaDeTermino == null) {
            JSFUtils.addFacesErrorMessage("Error, al calcular el plazo entre fechas");
        }

        Map map = new HashMap();
        Map mapaDatos = new HashMap();
        Integer diasPlazo = null;
        Long timeBetween = null;
        Integer daysBetween = null;
        Integer totalDiasPlazo = null;
        Integer frecuenciaPeriodoGracia = null;
        Integer idTcaFrecuenciaPeriodoGracia = null;

        try {
            timeBetween = ((fechaDeTermino.getTime() - fechaDeInicio.getTime()) / (60 * 60 * 24 * 1000));
            daysBetween = timeBetween.intValue();
        } catch (Exception e) {
            logger.warning("ERROR al recuperar los dias entre fechas.", e);
        }
        logger.warning("Plazo entre fechas: " + daysBetween);

        switch (idTcaFrecuenciaCapital) {
        case 1:
            diasPlazo = frecuenciaCapital;
            break;
        case 2:
            diasPlazo = frecuenciaCapital * 30;
            break;
        case 3:
            diasPlazo = frecuenciaCapital * 365;
            break;
        }
        
        logger.warning("DiasPlazo: " + diasPlazo);
        totalDiasPlazo = daysBetween - diasPlazo;
        
        if (totalDiasPlazo.compareTo(0) == -1) {
            logger.warning("Se obtuvo un numero de dias negativo.");
            frecuenciaPeriodoGracia = FRECUENCIA_PERIODO_CERO;
            idTcaFrecuenciaPeriodoGracia = ID_TCA_FRECUENCIA_PERIODO_DIAS;
        } else {
            logger.warning("Se obtuvo un numero de dias positivo.");
            map = calcularTipoPlazo(totalDiasPlazo);
            if (null != map.get("idTcaTipoPlazo") && null != map.get("resultado")) {
                frecuenciaPeriodoGracia = (Integer) map.get("resultado");
                idTcaFrecuenciaPeriodoGracia = (Integer) map.get("idTcaTipoPlazo");
            } else {
                logger.warning("La frecuencia o el tipo de frcuencia son nulos, no se guardan los datos.");
            }
        }
        
        logger.warning("El periodo de gracia es: " + frecuenciaPeriodoGracia + " con frecuencia de " + idTcaFrecuenciaPeriodoGracia);
        mapaDatos.put("frecuanciaPlazo", frecuenciaPeriodoGracia);
        mapaDatos.put("idTcaPlazo", idTcaFrecuenciaPeriodoGracia);
        logger.warning("Termina metodo calcularFrecuenciaPeriodoGracia.");
        return mapaDatos;
    }
    
    private Map calcularTipoPlazo(Integer numeroDias){
        logger.warning("Entra en calcularTipoPlazo.");
        Map map = new HashMap();
        Integer resultado = null;
        Integer residuo = null;
        Integer totalDias = null;
        Integer idTcaTipoPlazo = null;
        try{
            logger.warning("Numero de dias : " + numeroDias);
            if(numeroDias >= 365){
                residuo = numeroDias % 365;
                if(residuo == 0){
                    logger.warning("Se obtuvo periodo en años");
                    resultado = numeroDias / 365;
                    idTcaTipoPlazo = 3;
                    map.put("idTcaTipoPlazo", idTcaTipoPlazo);
                    map.put("resultado", resultado);
                }else{
                   residuo = numeroDias % 30;
                    if(residuo == 0){
                        logger.warning("Se obtuvo periodo en meses.");
                        resultado = numeroDias / 30;
                        idTcaTipoPlazo = 2;
                        map.put("idTcaTipoPlazo", idTcaTipoPlazo);
                        map.put("resultado", resultado);
                    }else{
                        logger.warning("El periodo es en dias");
                        idTcaTipoPlazo = 1;
                        map.put("idTcaTipoPlazo", idTcaTipoPlazo);
                        map.put("resultado", numeroDias);
                    }
                }
            }else if(numeroDias >= 30){
                residuo = numeroDias % 30;
                if(residuo == 0){
                    logger.warning("El periodo es en meses");
                    resultado = numeroDias / 30;
                    idTcaTipoPlazo = 2;
                    map.put("idTcaTipoPlazo", idTcaTipoPlazo);
                    map.put("resultado", resultado);
                }else{
                    logger.warning("El periodo es en dias");
                    idTcaTipoPlazo = 1;
                    map.put("idTcaTipoPlazo", idTcaTipoPlazo);
                    map.put("resultado", numeroDias);
                }
            }else{
                logger.warning("El periodo es en dias");
                idTcaTipoPlazo = 1;
                map.put("idTcaTipoPlazo", idTcaTipoPlazo);
                map.put("resultado", numeroDias);
            }
            logger.warning("Regres tipo de frecuencia : " + map.get("idTcaTipoPlazo"));
            logger.warning("Regres frecuencia : " + map.get("resultado"));
        }catch(Exception e){
            logger.warning("Error en calcularTipoPlazo.", e);
            return null;
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public Boolean asociarPlazoPagoAContrato(Integer frecuanciaPlazo, Integer idTcaPlazo){
      logger.warning("*Inf, Inicia metodo asociarPlazoPagoAContrato.");
      Boolean respuesta = Boolean.FALSE;
      
      logger.warning("*Inf, frecuanciaPlazo: "+frecuanciaPlazo);
      logger.warning("*Inf, idTcaPlazo: "+idTcaPlazo);
      
      if(frecuanciaPlazo == null || idTcaPlazo == null ){
           logger.warning("***Error, la frecuanciaPlazo y el idTcaPlazo son requeridos");
           JSFUtils.addFacesErrorMessage("Error no se pudo recuperar el plazo de pago");
       }
                  
        try{             
            BindingContainer binding  = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("asignarPlazoPago");
            operBinding.getParamsMap().put("frecuenciaPlazo", frecuanciaPlazo);
            operBinding.getParamsMap().put("idTcaFrecuenciaPlazo", idTcaPlazo);
            operBinding.execute();  
            
            if(!operBinding.getErrors().isEmpty()){
                logger.warning("***Error, operationBinding asignarPlazosPrimerPagoYVencimiento "+operBinding.getErrors() );
                JSFUtils.addFacesErrorMessage("Error, "+operBinding.getErrors());  
            }else{                                                   
                respuesta = (Boolean)operBinding.getResult();
            }                                                                                    
            
        }catch(Exception e){                
           logger.warning("***Error, al consultar los datos del desembolso ",e);
           
        }
      
      logger.warning("*Inf, Termina metodo asociarPlazoPagoAContrato.");
      return respuesta;
    }


    @SuppressWarnings("unchecked")
    public Boolean asociarPlazoPeriodoGraciaAContrato(Integer frecuanciaPlazo, Integer idTcaPlazo){
      logger.warning("*Inf, Inicia metodo asociarPlazoPeriodoGraciaAContrato.");
      Boolean respuesta = Boolean.FALSE;
      
      logger.warning("*Inf, frecuanciaPlazo: "+frecuanciaPlazo);
      logger.warning("*Inf, idTcaPlazo: "+idTcaPlazo);
      
      if(frecuanciaPlazo == null || idTcaPlazo == null ){
           logger.warning("***Error, la frecuanciaPlazo y el idTcaPlazo son requeridos");
           JSFUtils.addFacesErrorMessage("Error no se pudo recuperar el plazo del periodo de gracia");           
       }
                  
        try{             
            BindingContainer binding  = ADFUtils.getBindingContainer();
            OperationBinding operBinding = binding.getOperationBinding("asignarPlazoPeriodoGracia");
            operBinding.getParamsMap().put("frecuenciaPlazo", frecuanciaPlazo);
            operBinding.getParamsMap().put("idTcaFrecuenciaPlazo", idTcaPlazo);
            operBinding.execute();  
            
            if(!operBinding.getErrors().isEmpty()){
                logger.warning("***Error, operationBinding asignarPlazosPrimerPagoYVencimiento "+operBinding.getErrors() );
                JSFUtils.addFacesErrorMessage("Error, "+operBinding.getErrors());  
            }else{                                                    
                respuesta = (Boolean)operBinding.getResult();
            }                                                                                    
            
        }catch(Exception e){                
           logger.warning("***Error, al consultar los datos del desembolso ",e);
           
        }
      
      logger.warning("*Inf, Termina metodo asociarPlazoPeriodoGraciaAContrato.");
      return respuesta;
    }

    public java.util.Date obtenerFechaEstimadaAdesembolsar(){        
        logger.warning("*Inf, Inicia metodo obtenerFechaEstimadaAdesembolsar");
        Row fila = null;          
        java.util.Date fechaEstimadaAdesembolsar = null;
        
            try{             
                BindingContainer binding  = ADFUtils.getBindingContainer();
                OperationBinding operBinding = binding.getOperationBinding("getContratoSeleccionado");                                       
                operBinding.execute();  
                
                if(!operBinding.getErrors().isEmpty()){
                    logger.warning("***Error, operationBinding getContratoSeleccionado "+operBinding.getErrors() );
                    JSFUtils.addFacesErrorMessage("Error, "+operBinding.getErrors());  
                }else{                   
                                     
                    if(operBinding.getResult() != null){  
                        
                         fila = (Row)operBinding.getResult(); 
                         
                     oracle.jbo.domain.Date  fechaEstimadaDes = (null == fila.getAttribute("FechaEstimadaDesembolsar"))? null
                                                              : (oracle.jbo.domain.Date)fila.getAttribute("FechaEstimadaDesembolsar");
                               
                         if(fechaEstimadaDes != null){
                                 fechaEstimadaAdesembolsar =  fechaEstimadaDes.getValue();
                         }     
                                                      
                              logger.warning("*Inf, FechaEstimadaAdesembolsar recuperada del contrato->"+fechaEstimadaAdesembolsar);                                                                                
                     }else{
                            logger.warning("*** Error, No se pudo recuperar un current en contrato desembolso");
                     }                                                                                    
                }
            }catch(Exception e){
               logger.warning("***Error, al consultar los datos del desembolso ",e);
               
            }
            logger.warning("*Inf, Termina metodo obtenerFechaEstimadaAdesembolsar");
            return fechaEstimadaAdesembolsar;
        }
    
    public Boolean asociarFechasPagoPorCalendarioComplejo(Long idCondicionFinanciera){
         logger.warning("*Inf, inicia metodo asociarFechasPagoPorCalendarioComplejo");
         Boolean respuesta = Boolean.FALSE;
         
         if(idCondicionFinanciera == null){
             logger.warning("*Error, el parametro idCondicionFinanciera es resuelto a null");
           return respuesta;
         }
           
         BindingContainer bindings = ADFUtils.getBindingContainer();
         OperationBinding operBinding = null;
         
         try{            
               operBinding = bindings.getOperationBinding("recuperarFechasMayoMenorCalendarioComplejo");
               operBinding.getParamsMap().put("idCondicionFinanciera", idCondicionFinanciera);            
               operBinding.execute();               
         
               if(!operBinding.getErrors().isEmpty()){
                  logger.warning("OperationBinding recuperarFechasMayoMenorCalendarioComplejo devuelve errores->"+operBinding.getErrors());
                  JSFUtils.addFacesErrorMessage("Error al recuperar fecha de primer pago capital y fecha vencimiento del calendario complejo"+operBinding.getErrors());          
               }else{               
                  respuesta = (Boolean)operBinding.getResult();
               }
    
         }catch(Exception e){
             logger.warning("Ha ocurrido un error en el metodo asociarFechasPagoPorCalendarioComplejo ", e);
             JSFUtils.addFacesErrorMessage("Error al recuperar fecha de primer pago capital y fecha vencimiento del calendario complejo -> "+e);          
         }   
         logger.warning("*Inf, termina metodo asociarFechasPagoPorCalendarioComplejo");   
     return respuesta;
     }

    public Boolean validarMontoDisponibleTransferir(){
     logger.warning("*Inf, Inicia metodo validarMontoDisponibleTransferir"); 
     Boolean respuesta = Boolean.FALSE;
     BigDecimal montoDisponibleTransferir = null;
        
    String origenTransferencia=  
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("origenTransferencia").toString();  
         
        logger.warning("origenTransferencia: " + origenTransferencia);
        
        if(origenTransferencia.equals("DIRECTO_CLIENTE"))
              return Boolean.TRUE;  
                        
        String montoSession =
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("montoDisponibleTransferir").toString();
           
        logger.warning("*Inf, Monto session: "+montoSession);
        
        if(montoSession.equals("") || montoSession.isEmpty() ){
            logger.warning("****Error, No se pudo recuperar el disponible a transferir de la session"); 
            JSFUtils.addFacesErrorMessage("***Error en transferencias no se puedo validar el monto disponible a transferir");
        }else{
            try{
                  montoDisponibleTransferir = new BigDecimal(montoSession);
            }catch(Exception e){
                logger.warning("***Error, al castear monto disponible a transferir ->",e); 
                JSFUtils.addFacesErrorMessage("Error en transferencias no se puedo validar el monto disponible a transferir");
            }   
                if(montoDisponibleTransferir != null){
                       
                        if(montoDisponibleTransferir.compareTo(BigDecimal.ZERO) == 0){
                            respuesta = Boolean.TRUE;
                            logger.warning("*Inf, ok el monto disponible a transferir es cero"); 
                        }else{
                            logger.warning("*Inf, montoDisponibleTransferir: "+montoDisponibleTransferir); 
                            JSFUtils.addFacesErrorMessage("No es posible iniciar el proceso de desembolso, aun hay un monto disponible a transferir de "
                                                                                   +montoDisponibleTransferir +" en tranferencias");
                        }
                    
                }else{
                    logger.warning("***Error, monto disponible a trasferir es null"); 
                    JSFUtils.addFacesErrorMessage("Error en transferencias no se puedo validar el monto disponible a transferir");
                }
        }
                                
     logger.warning("*Inf, termina metodo validarMontoDisponibleTransferir");   
     return respuesta;
    }
    
    public void validarRowContratoCorrespondeContratoTaskFlow(){
        logger.warning("Inicia procedimiento para verificar si se ha perdido el currentRow del contrato.");
        
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        Row row = null;
        row = ADFUtils.getDCBindingContainer().findIteratorBinding("ContratoDesembolsoVOIterator").getCurrentRow();
        Long idContratoDesembolsoGCR = null;
        Long idContratoDesembolsoTF = contratoDesembolsoBean.getIdContratoDesembolso();
        
        if(null != row){
            idContratoDesembolsoGCR = (Long) row.getAttribute("Id");
        }
        else{
            logger.warning("El row recuperado de ContratoDesembolsoVOIterator es null");
        }
        
        logger.warning("Valor idContratoDesembolsoGCR: " + idContratoDesembolsoGCR);
        logger.warning("Valor idContratoDesembolsoTF: " + idContratoDesembolsoTF);
        
        if(null != idContratoDesembolsoTF && null != idContratoDesembolsoGCR){
            if(idContratoDesembolsoTF.compareTo(idContratoDesembolsoGCR) != 0)
            {
                logger.warning("Las variables recuperadas del contrato son distintas, se ejecuta el metodo precargarInformacionContrato");
                //contratoDesembolsoBean.precargarInformacionContrato();
                //se invoca metodo para buscar por id el row de ContratoDesembolVO y convertirlo en currenRow
                setCurrentContratoDesembolsoById(idContratoDesembolsoTF);
            }
            else{
                logger.warning("Las variables recuperadas del contrato son identicas, Solo se refresca la pantalla");
            }
        }
        else{
            logger.warning("El valor de idContratoDesembolsoTF o idContratoDesembolsoGCR es null");
        }
        
        logger.warning("Finaliza procedimiento para verificar si se ha perdido el currentRow del contrato.");
    }
    
    /**
     * metodo que por idContratoDesembolso recuperar el currentRow en ContratoDesembolsoVO 
     * @param idContratoDesembolso
     * @return true o false
     */
    public Boolean setCurrentContratoDesembolsoById(Long idContratoDesembolso){
        logger.warning("Dentro de setCurrentContratoDesembolsoById , idContratoDesembolso : "+idContratoDesembolso);                                                                           
        Boolean resultado = Boolean.FALSE;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idContratoDesembolso", idContratoDesembolso);
        try{
            resultado =(Boolean) execute(params, "setCurrentContratoDesembolsoById");
        }catch(Exception e){
            logger.severe("Error al ejcutar setCurrentContratoDesembolsoById ",e);
        }
        logger.warning("Fuera de setCurrentContratoDesembolsoById , resultado : "+resultado);
        return resultado;
    }


    public void setRegionCondicionesFinancierasUIC(RichRegion regionCondicionesFinancierasUIC) {
        this.regionCondicionesFinancierasUIC = regionCondicionesFinancierasUIC;
    }

    public RichRegion getRegionCondicionesFinancierasUIC() {
        return regionCondicionesFinancierasUIC;
    }

    public void setPglConidicionesUIC(RichPanelGroupLayout pglConidicionesUIC) {
        this.pglConidicionesUIC = pglConidicionesUIC;
    }

    public RichPanelGroupLayout getPglConidicionesUIC() {
        return pglConidicionesUIC;
    }
    public void cambioMoneda(Long idSolicitud, Integer moneda){
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operBinding = null;
                      
                  operBinding = bindings.getOperationBinding("actualizarMoneda");
                  operBinding.getParamsMap().put("solicitud", idSolicitud);  
                  operBinding.getParamsMap().put("moneda", moneda);    
                  operBinding.execute();               
            
                  if(!operBinding.getErrors().isEmpty()){
                      logger.warning("Error al ejecutar el metodo de");
                      }
        
        }

    public void modeloActualSucio(ActionEvent actionEvent) {
        // Add event code here...
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");
        
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding =
                bindings.getOperationBinding("modeloSucio");
            logger.warning("Ejecutando OperationBinding de modeloSucio");
            contratoDesembolsoBean.setCambiosPendientes((Boolean) operationBinding.execute());

            if (operationBinding.getErrors().isEmpty()) {
                logger.warning("El operationBinding modeloSucio se ejecuto correctamente");
            } else {
                logger.warning("El operationBinding modeloSucio devuelve errores" +
                               operationBinding.getErrors().toString());
            }
        } catch (Exception e) {
            logger.warning("Error al ejecutar el OperationBinding modeloSucio", e);
        }
    }

    public void aplicarEjecucion(ActionEvent actionEvent) {
        // Add event code here...
        ContratoDesembolsosBean contratoDesembolsoBean =
            (ContratoDesembolsosBean) JSFUtils.resolveExpression("#{pageFlowScope.ContratoDesembolsoManagedBean}");

        String metodo=null;
        Integer valor1=null;
        Integer valor2=null;
        try {
            logger.warning("Primera instancia de la VO de contrato");
            metodo="getRowState";
            valor1=ejecutarModelo(metodo);
            if(null!=valor1){
                    contratoDesembolsoBean.setInstancia1(valor1);  
                }
            logger.warning("Segunda instancia de la VO de contrato");     
            metodo="getRowState1";
            valor2=ejecutarModelo(metodo);
            if(null!=valor2){
                    contratoDesembolsoBean.setInstancia2(valor2);  
                }
            logger.warning("primer valor obtenido: " +valor1);     
            logger.warning("segundo valor obtenido: " +valor2);      
        } catch (Exception e) {
            logger.warning("Error al ejecutar el OperationBinding aplicarEjecucion", e);
        }
    }
    
    public void recalcularValorTasaReferncia(ValueChangeEvent valueChangeEvent) {
        logger.warning("*inf inicia metodo recalcularValorTasaReferncia");
        BindingContainer bindings = getBindings();
        OperationBinding operBinding = null;
        
          Timestamp fechaEfectiva = (Timestamp)valueChangeEvent.getNewValue();
        
          try {                                            
              operBinding = bindings.getOperationBinding("recalcularValorTasaReferncia");
              operBinding.getParamsMap().put("fechaEfectiva", fechaEfectiva);
              operBinding.execute();
          }catch (Exception e){
              logger.warning("Error, al ejecuter operBinding recalcularValorTasaReferncia ->"+e);
          }   
      
         //regionCondicionesFinancierasUIC.refresh(FacesContext.getCurrentInstance());
        logger.warning("*inf termina metodo recalcularValorTasaReferncia");        
    }
    
    
    
    
    public Integer ejecutarModelo(String  metodo) {
        // Add event code here...
       Integer resultado=null;         
        try {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding =
                bindings.getOperationBinding(metodo);
            logger.warning("Ejecutando OperationBinding de:  "+ metodo);
            operationBinding.execute();

            if (operationBinding.getErrors().isEmpty()) {
                logger.warning("El operationBinding se ejecuto correctamente");
                resultado=(Integer) operationBinding.getResult();
            } else {
                logger.warning("El operationBinding devuelve errores" +
                               operationBinding.getErrors().toString());
            }
        } catch (Exception e) {
            logger.warning("Error al ejecutar el OperationBinding ", e);
        }
        logger.warning("Valor obtenido: " + resultado);
        return resultado;
    }
    
    public Boolean ValidarCuentaClienteNoNull(){
        
            logger.warning("Inicio ValidarCuentaClienteNoNull");
            logger.warning("Gestion de desembolso: ContratoDesembolsosActionBean");
            boolean Resultado =Boolean.TRUE;
            String cuentaCliente;
            try{
            cuentaCliente = (null == JSFUtils.resolveExpression("#{bindings.CuentaCliente.inputValue}")) ? null : (String) JSFUtils.resolveExpression("#{bindings.CuentaCliente.inputValue}");
             
            }catch(Exception ex ){
                    logger.warning("Entrando a la Excepcion: "+ex.getMessage());
                    cuentaCliente=null; 
                }
           
            logger.warning("Cuenta Cliente: "+cuentaCliente);
            if ( cuentaCliente != null) 
            {       Resultado =Boolean.TRUE;
                logger.warning("La cuenta no es null");  
            }
            else
            {   logger.warning("La cuenta si es null");  
                Resultado =Boolean.FALSE ;
            JSFUtils.addFacesErrorMessage(getStringFromBundle("MSG_ERROR_CUENTA_CLIENTE"));
            }
            logger.warning("Final ValidarCuentaClienteNoNull");
            return Resultado;
        }    
}
