package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.CalculoDetallePenalidadVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 10 13:24:27 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CalculoDetallePenalidadVOImpl extends ViewObjectImpl implements CalculoDetallePenalidadVO {
    /**
     * This is the default constructor (do not remove).
     */
    public CalculoDetallePenalidadVOImpl() {
    }

    /**
     * Returns the bind variable value for idPrepago.
     * @return bind variable value for idPrepago
     */
    public Long getIdPrepago() {
        return (Long) getNamedWhereClauseParam("IdPrepago");
    }

    /**
     * Sets <code>value</code> for bind variable idPrepago.
     * @param value value to bind as idPrepago
     */
    public void setIdPrepago(Long value) {
        setNamedWhereClauseParam("IdPrepago", value);
    }
}

