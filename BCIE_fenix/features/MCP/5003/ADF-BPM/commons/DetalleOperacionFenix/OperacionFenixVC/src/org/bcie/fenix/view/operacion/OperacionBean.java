package org.bcie.fenix.view.operacion;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class OperacionBean implements Serializable {
    
    @SuppressWarnings("compatibility:6542102984880004770")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    private Boolean proyectoOperacion = Boolean.FALSE;

    private BigDecimal avanceFisico;
    private BigDecimal avanceFinanciero;
    private java.sql.Timestamp fechaCorte;
    
    public OperacionBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    @SuppressWarnings("unchecked")
    public void inicializarDatosSupervision(){
        logger.log(ADFLogger.WARNING, "INTO inicializarDatosSupervision.");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        try{
            
            OperationBinding operationBinding = bindings.getOperationBinding("setvarIdOperacion");
            operationBinding.getParamsMap().put("value", JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            operationBinding.execute();
            logger.log(ADFLogger.WARNING, "valor idOperacion----->" + JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString());
            
            setProyectoOperacion(obtenerTipoProyecto());
            
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error inicializarDatosSupervision." + e.getClass() + e.getMessage());
        }
    }
    
    public Boolean obtenerTipoProyecto() {
        logger.warning("Inside obtenerTipoProyecto.");
        
        Boolean result = Boolean.FALSE;
        
        BindingContainer bindings = ADFUtils.getBindingContainer();

        OperationBinding operationBinding = bindings.getOperationBinding("obtenerTipoProyecto");
        operationBinding.execute();
        
        if (operationBinding.getResult() != null) {
            Integer tipoProyecto = (Integer) operationBinding.getResult();
            
            result = tipoProyecto.compareTo(1) == 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        
        logger.warning("proyectoOperacion: " + result);
        
        return result;
    }
    
    public void setAvanceFisico(BigDecimal avanceFisico) {
        this.avanceFisico = avanceFisico;
    }

    public BigDecimal getAvanceFisico() {
        return avanceFisico;
    }

    public void setAvanceFinanciero(BigDecimal avanceFinanciero) {
        this.avanceFinanciero = avanceFinanciero;
    }

    public BigDecimal getAvanceFinanciero() {
        return avanceFinanciero;
    }

    public void setFechaCorte(Timestamp fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public Timestamp getFechaCorte() {
        return fechaCorte;
    }
    
    public void setProyectoOperacion(Boolean proyectoOperacion) {
        this.proyectoOperacion = proyectoOperacion;
    }

    public Boolean getProyectoOperacion() {
        return proyectoOperacion;
    }
}