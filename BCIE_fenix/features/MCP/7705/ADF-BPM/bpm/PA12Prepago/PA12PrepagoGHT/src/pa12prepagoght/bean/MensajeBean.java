package pa12prepagoght.bean;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class MensajeBean {
    
    public static ADFLogger logger = ADFLogger.createADFLogger(MensajeBean.class);
    private boolean clieteEnMora = Boolean.FALSE;
    private int diasMora = 0;
    private BigDecimal montoMora = BigDecimal.ZERO;
    
    public MensajeBean() {
        super();
    }
    
    @SuppressWarnings("unchecked")
    public void precargaClienteEnMora() {
        logger.warning("precargaClienteEnMora");
        Long idOperacion = null;

        if (null != JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}")) {
            idOperacion = (Long) JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}");
        }

        if (null != idOperacion) {
            BindingContainer bindings = ADFUtils.getBindingContainer();
            OperationBinding operationBinding = null;
            Map<String, Object> reporteAviso = new HashMap<String, Object>();

            try {
                operationBinding = bindings.getOperationBinding("obtenerMoraCliente");
                operationBinding.getParamsMap().put("idOperacion", idOperacion);
                operationBinding.execute();

                if (operationBinding.getErrors().isEmpty()) {
                    reporteAviso = (Map<String, Object>) operationBinding.getResult();

                    if (null != reporteAviso.get("enMora")) {
                        setClieteEnMora((Boolean) reporteAviso.get("enMora"));
                    }

                    if (null != reporteAviso.get("diasMora")) {
                        setDiasMora((Integer) reporteAviso.get("diasMora"));
                    }

                    if (null != reporteAviso.get("montoMora")) {
                        setMontoMora((BigDecimal) reporteAviso.get("montoMora"));
                    }
                } else {
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }
            } catch (Exception e) {
                logger.warning("Error al obtener el valor de cliente en mora.", e);
            }
        } else {
            logger.warning("Id de operacion es nulo.");
        }
    }

    public boolean isClieteEnMora() {
        return clieteEnMora;
    }

    public void setClieteEnMora(boolean clieteEnMora) {
        this.clieteEnMora = clieteEnMora;
    }

    public int getDiasMora() {
        return diasMora;
    }

    public void setDiasMora(int diasMora) {
        this.diasMora = diasMora;
    }

    public BigDecimal getMontoMora() {
        return montoMora;
    }

    public void setMontoMora(BigDecimal montoMora) {
        this.montoMora = montoMora;
    }
}
