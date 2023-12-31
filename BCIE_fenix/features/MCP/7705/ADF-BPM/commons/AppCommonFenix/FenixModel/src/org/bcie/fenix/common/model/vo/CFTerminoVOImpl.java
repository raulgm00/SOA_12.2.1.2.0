package org.bcie.fenix.common.model.vo;

import java.sql.ResultSet;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jun 25 12:45:13 COT 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CFTerminoVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CFTerminoVOImpl() {
    }

    /**
     * Returns the bind variable value for ID_LINEA.
     * @return bind variable value for ID_LINEA
     */
    public Long getID_LINEA() {
        return (Long) getNamedWhereClauseParam("ID_LINEA");
    }

    /**
     * Sets <code>value</code> for bind variable ID_LINEA.
     * @param value value to bind as ID_LINEA
     */
    public void setID_LINEA(Long value) {
        setNamedWhereClauseParam("ID_LINEA", value);
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

    public Row llenarCFTerminoVO(Long idLinea) {
        //--- llenar nuevo VOs
        Row row = null;
        System.out.println("***obteniendo CFTerminoVOImpl *****");
        setNamedWhereClauseParam("ID_LINEA", idLinea);
        executeQuery();
        System.out.println("CFTerminoVO.getEstimatedRowCount(): " + getEstimatedRowCount());
        if (getEstimatedRowCount() > 0) {
            setCurrentRow(first());
        }
        RowSetIterator rowiter = this.createRowSetIterator(null);
        rowiter.reset();
        while (rowiter.hasNext()) {
            CFTerminoVORowImpl rowCFTerminoVO = (CFTerminoVORowImpl) rowiter.next();
            row = rowCFTerminoVO;
        }
        rowiter.closeRowSetIterator();
        if (row != null) {
            String id = null;
            try {
                id = String.valueOf(row.getAttribute("Id"));
            } catch (Exception e) {
                System.out.println("CFTerminoVOImpl sin registros " + e);
            }

            System.out.println("Se encontro Registro de CFTerminoVO. id: " + id);
        }
        //---------------------
        return row;
    }
}

