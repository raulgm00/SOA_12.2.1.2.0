package org.bcie.fenix.common.model.utils;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.common.Diagnostic;
import oracle.jbo.server.OracleSQLBuilderImpl;
import oracle.jbo.server.SQLBuilder;


public class OracleSQLBuilderImplEx extends OracleSQLBuilderImpl {
    private static SQLBuilder mSQLBuilderInterface = null;
    private static final String FOR_UPDATE_WAIT = "FOR UPDATE WAIT 30"; // wait up to 30 seconds, to acquire lock

    
    public OracleSQLBuilderImplEx() {
        super();
    }

    /**
     * Gets the singleton instance of this class.
     * This is required by the framework in order
     * to override the default SQLBuilder
     * @return a <tt>SQLBuilder</tt> object.
     */
    public static SQLBuilder getInterface() {

        if (mSQLBuilderInterface == null) {
            if (Diagnostic.isOn()) {
                Diagnostic.println("OracleSQLBuilder reached getInterface");
            }
            mSQLBuilderInterface = (SQLBuilder)(new OracleSQLBuilderImplEx());
            if (Diagnostic.isOn()) {
                Diagnostic.println(mSQLBuilderInterface.getVersion());
            }
        }
        return mSQLBuilderInterface;
    }


    @Override
    protected String getSqlVariantLockTrailer() {
        // change default FOR UPDATE NOWAIT to FOR UPDATE WAIT 30, to avoid lock error
        return FOR_UPDATE_WAIT;
    }
}
