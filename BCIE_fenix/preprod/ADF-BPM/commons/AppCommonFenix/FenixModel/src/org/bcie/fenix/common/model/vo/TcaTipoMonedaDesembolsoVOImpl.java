package org.bcie.fenix.common.model.vo;

import java.sql.ResultSet;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;

import org.bcie.fenix.common.model.vo.common.TcaTipoMonedaDesembolsoVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Oct 26 21:33:27 CST 2021
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TcaTipoMonedaDesembolsoVOImpl extends ViewObjectImpl implements TcaTipoMonedaDesembolsoVO {
    /**
     * This is the default constructor (do not remove).
     */
    public TcaTipoMonedaDesembolsoVOImpl() {
    }

    /**
     * Returns the bind variable value for P_ID_OPERACION.
     * @return bind variable value for P_ID_OPERACION
     */
    public String getP_ID_OPERACION() {
        return (String) getNamedWhereClauseParam("P_ID_OPERACION");
    }

    /**
     * Sets <code>value</code> for bind variable P_ID_OPERACION.
     * @param value value to bind as P_ID_OPERACION
     */
    public void setP_ID_OPERACION(String value) {
        setNamedWhereClauseParam("P_ID_OPERACION", value);
    }

    /**
     * executeQueryForCollection - overridden for custom java data source support.
     */
    @Override
    protected void executeQueryForCollection(Object qc, Object[] params, int noUserParams) {
        super.executeQueryForCollection(qc, params, noUserParams);
    }

    /**
     * hasNextForCollection - overridden for custom java data source support.
     */
    @Override
    protected boolean hasNextForCollection(Object qc) {
        boolean bRet = super.hasNextForCollection(qc);
        return bRet;
    }

    /**
     * createRowFromResultSet - overridden for custom java data source support.
     */
    @Override
    protected ViewRowImpl createRowFromResultSet(Object qc, ResultSet resultSet) {
        ViewRowImpl value = super.createRowFromResultSet(qc, resultSet);
        return value;
    }

    /**
     * getQueryHitCount - overridden for custom java data source support.
     */
    @Override
    public long getQueryHitCount(ViewRowSetImpl viewRowSet) {
        long value = super.getQueryHitCount(viewRowSet);
        return value;
    }

    /**
     * getCappedQueryHitCount - overridden for custom java data source support.
     */
    @Override
    public long getCappedQueryHitCount(ViewRowSetImpl viewRowSet, Row[] masterRows, long oldCap, long cap) {
        long value = super.getCappedQueryHitCount(viewRowSet, masterRows, oldCap, cap);
        return value;
    }
}

