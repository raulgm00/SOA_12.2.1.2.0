package org.bcie.fenix.view.encabezadocliente;

import oracle.adf.share.logging.ADFLogger;

public class EncabezadoClienteBean {
    
    private static ADFLogger logger = null;
    
    public EncabezadoClienteBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
}
