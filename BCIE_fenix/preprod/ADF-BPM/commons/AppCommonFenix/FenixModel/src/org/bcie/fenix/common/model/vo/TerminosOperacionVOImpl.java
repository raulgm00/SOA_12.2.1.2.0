package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Feb 11 12:18:02 COT 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TerminosOperacionVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public TerminosOperacionVOImpl() {
    }

    /**
     * Returns the bind variable value for pIdOperacion.
     * @return bind variable value for pIdOperacion
     */
    public Long getpIdOperacion() {
        return (Long) getNamedWhereClauseParam("pIdOperacion");
    }

    /**
     * Sets <code>value</code> for bind variable pIdOperacion.
     * @param value value to bind as pIdOperacion
     */
    public void setpIdOperacion(Long value) {
        setNamedWhereClauseParam("pIdOperacion", value);
    }

    /**
     * Returns the bind variable value for pIdTipoTermino.
     * @return bind variable value for pIdTipoTermino
     */
    public Integer getpIdTipoTermino() {
        return (Integer) getNamedWhereClauseParam("pIdTipoTermino");
    }

    /**
     * Sets <code>value</code> for bind variable pIdTipoTermino.
     * @param value value to bind as pIdTipoTermino
     */
    public void setpIdTipoTermino(Integer value) {
        setNamedWhereClauseParam("pIdTipoTermino", value);
    }
}

