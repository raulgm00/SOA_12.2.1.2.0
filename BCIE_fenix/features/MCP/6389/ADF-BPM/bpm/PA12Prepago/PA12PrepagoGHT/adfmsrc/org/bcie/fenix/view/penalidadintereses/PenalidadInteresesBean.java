package org.bcie.fenix.view.penalidadintereses;

import java.io.Serializable;

import java.util.Date;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class PenalidadInteresesBean implements Serializable {
    @SuppressWarnings("compatibility:-1542270110461573277")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;

    private Boolean verIntereses;
    private Date fechaAmortizacion;

    public void setFechaAmortizacion(Date fechaAmortizacion) {
        this.fechaAmortizacion = fechaAmortizacion;
    }

    public Date getFechaAmortizacion() {
        return fechaAmortizacion;
    }

    public void setVerIntereses(Boolean verIntereses) {
        this.verIntereses = verIntereses;
    }

    public Boolean getVerIntereses() {
        return verIntereses;
    }

    public PenalidadInteresesBean() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void inicalizarPenalidadIntereses() {
        logger.warning("INTO inicalizarPenalidadIntereses.");

        BindingContainer bindings = ADFUtils.getBindingContainer();
        Long pIdPrepago = null;

        //metodo para inicializar 'DetallePenalidadVO'
        try {
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}")) {
                pIdPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
                logger.warning("Valor de pIdPrepago: " + pIdPrepago);
            }
            OperationBinding operationBinding = bindings.getOperationBinding("setvarIdPrepagoDetallePenalidad");
            operationBinding.getParamsMap().put("value", pIdPrepago);
            operationBinding.execute();
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "Error en inicializar Detalle de Penalidad." + e.getClass() + "." + e.getMessage());
        }

        //metodo para inicializar 'CalculoInteresesVO'
        try {
            if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}")) {
                pIdPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
                logger.warning("Valor de pIdPrepago: " + pIdPrepago);
            }
            OperationBinding operationBinding = bindings.getOperationBinding("setvarIdPrepagoCalculoIntereses");
            operationBinding.getParamsMap().put("value", pIdPrepago);
            operationBinding.execute();
        } catch (Exception e) {
            logger.log(ADFLogger.ERROR,
                       "Error en inicializar Calculo de Intereses." + e.getClass() + "." + e.getMessage());
        }

        //metodo para obtener validacion de Calculo de intereses
        OperationBinding operationBinding = bindings.getOperationBinding("validaIntereses");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
        } else if (operationBinding.getResult() != null) {
            Boolean resultado = (Boolean) operationBinding.getResult();
            setVerIntereses(resultado);
            logger.warning("verIntereses: " + getVerIntereses());
        }
        
        //metodo para obtener la Fecha de amortizacion
        OperationBinding operationB = bindings.getOperationBinding("fechaAmortizacion");
        operationB.execute();
        if (!operationB.getErrors().isEmpty()) {
            // Manejo de errores
            JSFUtils.addFacesErrorMessage(operationB.getErrors().toString());
        } else if (operationB.getResult() != null) {
            Date resultado = (Date) operationB.getResult();
            setFechaAmortizacion(resultado);
            logger.warning("fechaAmortizacion: " + getFechaAmortizacion());
        }
    }
}
