package org.bcie.fenix.common.model.vo;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Sep 18 12:00:56 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TcaTipoDesembolsoVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public TcaTipoDesembolsoVOImpl() {
    }

    /**
     * Returns the bind variable value for pIdEvento.
     * @return bind variable value for pIdEvento
     */
    public Number getpIdEvento() {
        return (Number) getNamedWhereClauseParam("pIdEvento");
    }

    /**
     * Sets <code>value</code> for bind variable pIdEvento.
     * @param value value to bind as pIdEvento
     */
    public void setpIdEvento(Number value) {
        setNamedWhereClauseParam("pIdEvento", value);
    }
}

