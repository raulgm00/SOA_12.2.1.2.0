package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Dec 15 11:40:36 COT 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Tre_Condicion_Financiera_Cal_VOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public Tre_Condicion_Financiera_Cal_VOImpl() {
    }

    /**
     * Returns the variable value for pidCondicionFinanciera.
     * @return variable value for pidCondicionFinanciera
     */
    public Long getpidCondicionFinanciera() {
        return (Long) ensureVariableManager().getVariableValue("pidCondicionFinanciera");
    }

    /**
     * Sets <code>value</code> for variable pidCondicionFinanciera.
     * @param value value to bind as pidCondicionFinanciera
     */
    public void setpidCondicionFinanciera(Long value) {
        ensureVariableManager().setVariableValue("pidCondicionFinanciera", value);
    }
}

