package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Dec 07 17:37:17 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConsultaContactosClienteVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public ConsultaContactosClienteVOImpl() {
    }

    /**
     * Returns the bind variable value for varIdCliente.
     * @return bind variable value for varIdCliente
     */
    public Long getvarIdCliente() {
        return (Long) getNamedWhereClauseParam("varIdCliente");
    }

    /**
     * Sets <code>value</code> for bind variable varIdCliente.
     * @param value value to bind as varIdCliente
     */
    public void setvarIdCliente(Long value) {
        setNamedWhereClauseParam("varIdCliente", value);
    }
}

