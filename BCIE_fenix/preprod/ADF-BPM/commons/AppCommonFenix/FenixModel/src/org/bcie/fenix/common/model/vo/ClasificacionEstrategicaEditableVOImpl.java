package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ClasificacionEstrategicaEditableVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Nov 16 12:15:54 CST 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ClasificacionEstrategicaEditableVOImpl extends ViewObjectImpl implements ClasificacionEstrategicaEditableVO {
    /**
     * This is the default constructor (do not remove).
     */
    public ClasificacionEstrategicaEditableVOImpl() {
    }

    /**
     * Returns the bind variable value for pIdOperacion.
     * @return bind variable value for pIdOperacion
     */
    public Integer getpIdOperacion() {
        return (Integer) getNamedWhereClauseParam("pIdOperacion");
    }

    /**
     * Sets <code>value</code> for bind variable pIdOperacion.
     * @param value value to bind as pIdOperacion
     */
    public void setpIdOperacion(Integer value) {
        setNamedWhereClauseParam("pIdOperacion", value);
    }
}

