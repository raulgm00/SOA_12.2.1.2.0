package org.bcie.fenix.view.estructuracionprestamo;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

public class AdministradorEstructuracionPrestamoBean implements Serializable {
    @SuppressWarnings("compatibility:4990817843750261915")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    
    private Double montoTotalSindicado = null;

    public AdministradorEstructuracionPrestamoBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public Double getMontoTotalSindicado() {
        return montoTotalSindicado;
    }

    public void setMontoTotalSindicado(Double montoTotalSindicado) {
        this.montoTotalSindicado = montoTotalSindicado;
        ADFContext.getCurrent().getSessionScope().put("montoTotalSindicado", montoTotalSindicado);
    }
    
}
