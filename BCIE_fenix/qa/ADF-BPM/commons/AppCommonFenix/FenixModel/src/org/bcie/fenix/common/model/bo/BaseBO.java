package org.bcie.fenix.common.model.bo;

import java.util.Objects;

import oracle.jbo.server.ApplicationModuleImpl;

import org.bcie.fenix.common.model.am.FenixAMImpl;

public abstract class BaseBO implements AutoCloseable{
    
    /** AM del cual se leen los VOs y la transacción actual. */
    private ApplicationModuleImpl am;

    public BaseBO(ApplicationModuleImpl am) {
        Objects.requireNonNull(am, "ApplicationModule es requerido.");
        this.am = am;
    }
    
    protected ApplicationModuleImpl getAM(){
        return this.am;
    }
    
    /**
     * Libera recursos de esta clase. En especial la referencia al ApplicationModule.
     */
    @Override
    public void close() throws Exception {
        this.am = null;
    }

    public abstract void execute();
}
