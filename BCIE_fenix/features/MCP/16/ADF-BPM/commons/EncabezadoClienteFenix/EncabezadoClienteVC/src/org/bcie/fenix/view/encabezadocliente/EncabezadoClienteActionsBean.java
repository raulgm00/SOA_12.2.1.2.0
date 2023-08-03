package org.bcie.fenix.view.encabezadocliente;

import oracle.adf.share.logging.ADFLogger;

public class EncabezadoClienteActionsBean {
    
    private static ADFLogger logger = null;
    
    public EncabezadoClienteActionsBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
}
