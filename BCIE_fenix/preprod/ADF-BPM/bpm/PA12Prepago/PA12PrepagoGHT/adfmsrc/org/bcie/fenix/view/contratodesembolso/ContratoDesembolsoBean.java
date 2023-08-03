package org.bcie.fenix.view.contratodesembolso;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

public class ContratoDesembolsoBean implements Serializable {
    @SuppressWarnings("compatibility:-6538337870295221504")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    private Boolean esLineaGlobal;
    
    public ContratoDesembolsoBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public Boolean getEsLineaGlobal() {
        return esLineaGlobal;
    }

    public void setEsLineaGlobal(Boolean esLineaGlobal) {
        this.esLineaGlobal = esLineaGlobal;
    }
}
