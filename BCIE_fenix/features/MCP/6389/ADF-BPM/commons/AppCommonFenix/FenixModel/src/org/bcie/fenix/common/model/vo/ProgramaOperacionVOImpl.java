package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Oct 07 14:11:50 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ProgramaOperacionVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ProgramaOperacionVOImpl() {
    }

    /**
     * Returns the bind variable value for varVariante.
     * @return bind variable value for varVariante
     */
    public String getvarVariante() {
        return (String) getNamedWhereClauseParam("varVariante");
    }

    /**
     * Sets <code>value</code> for bind variable varVariante.
     * @param value value to bind as varVariante
     */
    public void setvarVariante(String value) {
        setNamedWhereClauseParam("varVariante", value);
    }
}

