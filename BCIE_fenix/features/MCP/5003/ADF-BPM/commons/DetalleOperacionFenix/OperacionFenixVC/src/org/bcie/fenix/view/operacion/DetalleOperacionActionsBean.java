package org.bcie.fenix.view.operacion;

import java.math.BigDecimal;

import java.security.Timestamp;

import java.util.ResourceBundle;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.bcie.fenix.common.utils.JSFUtils;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.binding.AttributeBinding;

import oracle.javatools.resourcebundle.BundleFactory;

import oracle.jbo.Row;

import org.bcie.fenix.common.utils.ADFUtils;


public class DetalleOperacionActionsBean {
    
    private static ADFLogger LOGGER = null;
    private Boolean lecturaIFI = null;
    private static Long id = null;
    private RichPanelGroupLayout panelDataUIC;

    public DetalleOperacionActionsBean() {
        super();
        if (LOGGER == null) {
            LOGGER = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public Boolean getMostrarTIR()
    {
      return JSFUtils.resolveExpression("#{pageFlowScope.pMostrarTIR}")==null?Boolean.FALSE:(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.pMostrarTIR}");
    }
    
    public Boolean getMostrarSCR()
    {
      return JSFUtils.resolveExpression("#{pageFlowScope.pMostrarSCR}")==null?Boolean.FALSE:(Boolean)JSFUtils.resolveExpression("#{pageFlowScope.pMostrarSCR}");
    }
    
    public void setLecturaIFI(Boolean lecturaIFI) {
        this.lecturaIFI = lecturaIFI;
    }

    public Boolean getLecturaIFI() {
        return lecturaIFI;
    }
    
    public Boolean validaCampos() {
        LOGGER.warning("inside validaCampos");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        //OperationBinding operationBinding = null;
        
        OperacionBean operacionBean = (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");
        Boolean result = Boolean.FALSE;
        Boolean avanceFin = Boolean.TRUE;
        Boolean avanceFis = Boolean.TRUE;
        
        BigDecimal avanceFinanciero = null;
        BigDecimal avanceFisico = null;
        java.sql.Timestamp fechaCorte = null;
        
        try {
            DCIteratorBinding iteratorBinding = (DCIteratorBinding) bindings.get("DatosSupervisionVOIterator");
            if(iteratorBinding != null){
                Row currentRow = iteratorBinding.getCurrentRow();
                if(currentRow != null){
                    avanceFinanciero = (BigDecimal) currentRow.getAttribute("AvanceFinanciero");
                    avanceFisico = (BigDecimal) currentRow.getAttribute("AvanceFisico");
                    fechaCorte = (java.sql.Timestamp) currentRow.getAttribute("FechaAvance");
                    id = (Long) currentRow.getAttribute("Id");
                    operacionBean.setAvanceFinanciero(avanceFinanciero);
                    operacionBean.setAvanceFisico(avanceFisico);
                    operacionBean.setFechaCorte(fechaCorte);
                }
            }
        } catch (Exception e) {
            LOGGER.severe("Error al obtener valores de supervision.", e);
            LOGGER.warning("Excepcion al obtener valores de supervision." + e.getClass(), ".", e.getMessage());
        }
        
        LOGGER.warning("avanceFinanciero: " + operacionBean.getAvanceFinanciero());
        LOGGER.warning("avanceFisico: " + operacionBean.getAvanceFisico());
        LOGGER.warning("fechaCorte: " + operacionBean.getFechaCorte());
        LOGGER.warning("id: " + id);
        
        AttributeBinding lecturaIFIPar;
        lecturaIFIPar = ADFUtils.findControlBinding("lecturaIFI");
        
        setLecturaIFI((Boolean)lecturaIFIPar.getInputValue());
        
        LOGGER.warning("lecturaIFI: " + getLecturaIFI());
        LOGGER.warning("proyectoOperacion: " + operacionBean.getProyectoOperacion());
        
            if ((operacionBean.getProyectoOperacion() == false) && (getLecturaIFI() == false)) {
            if ((operacionBean.getAvanceFinanciero() != null) &&
                (operacionBean.getAvanceFisico() != null) && 
                (operacionBean.getFechaCorte() != null)) {
                    if ((operacionBean.getAvanceFinanciero().intValue() < 0 || operacionBean.getAvanceFinanciero().intValue() > 100)) {
                        LOGGER.warning("Avance Financiero fuera de rango");
                        avanceFin = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage("Avance Financiero fuera de rango");
                    } 
                    
                    if ((operacionBean.getAvanceFisico().intValue() < 0 || operacionBean.getAvanceFisico().intValue() > 100)) {
                        LOGGER.warning("Avance Fisico fuera de rango");
                        avanceFis = Boolean.FALSE;
                        JSFUtils.addFacesErrorMessage("Avance Físico fuera de rango.");
                    }
                    
                    if (avanceFin && avanceFis) {
                        LOGGER.warning("Datos introducidos correctos");
                        result = Boolean.TRUE;
                    }
            } else {
                JSFUtils.addFacesErrorMessage("Debe introducir los datos requeridos.");
            }
        }
        if ((operacionBean.getProyectoOperacion() == true) || (getLecturaIFI() == true)) {
            if (operacionBean.getFechaCorte() != null) {
                result = Boolean.TRUE;
            } else {
                JSFUtils.addFacesErrorMessage("Debe introducir la Fecha de Corte.");
            }
        }
        return (result);
    }
    
    public void resetValues() {
        LOGGER.warning("inside resetValues");
        
        OperacionBean operacionBean = (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");
        
        LOGGER.warning("lecturaIFI: " + getLecturaIFI());

        if ((operacionBean.getProyectoOperacion() == false) && (getLecturaIFI() == false)) {
            if ((operacionBean.getAvanceFinanciero() != null) &&
                (operacionBean.getAvanceFisico() != null) && 
                (operacionBean.getFechaCorte() != null)) {
                operacionBean.setAvanceFinanciero(null);
                operacionBean.setAvanceFisico(null);
                operacionBean.setFechaCorte(null);
            } 
        }
            
        if ((operacionBean.getProyectoOperacion() == true) || (getLecturaIFI() == true)) {
            if (operacionBean.getFechaCorte() != null) {
                operacionBean.setFechaCorte(null);
            } 
        }
    }

    public void cambioProyectoOperacionValueChange(ValueChangeEvent valueChangeEvent) {
        LOGGER.warning("inside cambioProyectoOperacionValueChange");
            
        OperacionBean operacionBean = (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");
        if (operacionBean.getProyectoOperacion() == null || !operacionBean.getProyectoOperacion()) {
            operacionBean.setProyectoOperacion(Boolean.TRUE);
            LOGGER.warning("Proyecto en Operacion: " + operacionBean.getProyectoOperacion());
        } else {
            operacionBean.setProyectoOperacion(Boolean.FALSE);
            LOGGER.warning("Proyecto en Operacion: " + operacionBean.getProyectoOperacion());
        }
    }
    
    public void cleanValueProyecto() {
        LOGGER.warning("inside validaOperacion");
        
        OperacionBean operacionBean = (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");
        
        if (operacionBean.getProyectoOperacion()) {
            if (operacionBean.getAvanceFinanciero() != null || operacionBean.getAvanceFisico() != null) {
                operacionBean.setAvanceFinanciero(null);
                operacionBean.setAvanceFisico(null);
            } 
        }
    }

    public void agregarAvanceSupervisionActionListener(ActionEvent actionEvent) {
        LOGGER.warning("inside agregarAvanceSupervisionActionListener");
        
        OperacionBean operacionBean = (OperacionBean) JSFUtils.resolveExpression("#{pageFlowScope.operacionBean}");
        Boolean validaCampos = Boolean.FALSE;
        Integer proyectoOperacion = operacionBean.getProyectoOperacion() ? 1 : 0;
        
        validaCampos = validaCampos();
        LOGGER.warning("validaCampos: " + validaCampos);
        
        if(validaCampos) {
            BindingContainer bindings = getBindings();
            
            LOGGER.warning("idOperacion: " + Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString()));
            LOGGER.warning("AvanceFisico: " + operacionBean.getAvanceFisico());
            LOGGER.warning("AvanceFinanciero: " + operacionBean.getAvanceFisico());
            LOGGER.warning("FechaAvance: " + operacionBean.getFechaCorte());
            LOGGER.warning("EsProyectoEnOperacion: " + proyectoOperacion);
            
            cleanValueProyecto();
            
            OperationBinding operationBindingBanderaInsAct = bindings.getOperationBinding("obtenerDatosSupervisionById");
            operationBindingBanderaInsAct.getParamsMap().put("id", id);
            operationBindingBanderaInsAct.execute();
            Boolean banderaInsAct = (Boolean)operationBindingBanderaInsAct.getResult();
            LOGGER.warning("banderaInsAct: " + banderaInsAct);
            
            if(banderaInsAct == false)
            {
                OperationBinding operationBinding = bindings.getOperationBinding("agregarSupervision");
                operationBinding.getParamsMap().put("idOperacion", Long.parseLong(JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString()));
                operationBinding.getParamsMap().put("avanceFisico", operacionBean.getAvanceFisico());
                operationBinding.getParamsMap().put("avanceFinanciero", operacionBean.getAvanceFinanciero());
                operationBinding.getParamsMap().put("fechaAvance", operacionBean.getFechaCorte());
                operationBinding.getParamsMap().put("proyectoOperacion", proyectoOperacion);
                operationBinding.getParamsMap().put("loginUsuario", (String) JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}"));
                
                operationBinding.execute();
                
                if (!operationBinding.getErrors().isEmpty()) {
                    LOGGER. warning("Empty");
                }
                resetValues();
            }
            else{
                OperationBinding operationBinding = bindings.getOperationBinding("actualizarSupervision");
                operationBinding.getParamsMap().put("id", id);
                operationBinding.getParamsMap().put("avanceFisico", operacionBean.getAvanceFisico());
                operationBinding.getParamsMap().put("avanceFinanciero", operacionBean.getAvanceFinanciero());
                operationBinding.getParamsMap().put("fechaAvance", operacionBean.getFechaCorte());
                operationBinding.getParamsMap().put("loginUsuario", (String) JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}"));
                operationBinding.getParamsMap().put("proyectoOperacion", proyectoOperacion);
                
                operationBinding.execute();
                
                if (!operationBinding.getErrors().isEmpty()) {
                    LOGGER. warning("Empty");
                }
                
                resetValues();
                
                currentSupervision();
                
                AdfFacesContext.getCurrentInstance().addPartialTarget(panelDataUIC);
            }
        }
    }

    public void setPanelDataUIC(RichPanelGroupLayout panelDataUIC) {
        this.panelDataUIC = panelDataUIC;
    }

    public RichPanelGroupLayout getPanelDataUIC() {
        return panelDataUIC;
    }
    
    public void currentSupervision() {
        LOGGER.warning("Inside currentSupervision.");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        OperationBinding operationBinding = bindings.getOperationBinding("currentSupervision");
        operationBinding.getParamsMap().put("value", JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
        operationBinding.execute();
    }
}
