package org.bcie.fenix.common.model.vo;

import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Mar 09 12:38:04 COT 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CFandCalendario1VOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CFandCalendario1VOImpl() {
    }

    /**
     * Returns the bind variable value for ID_CONTRATO_DESEMBOLSO.
     * @return bind variable value for ID_CONTRATO_DESEMBOLSO
     */
    public String getID_CONTRATO_DESEMBOLSO() {
        return (String) getNamedWhereClauseParam("ID_CONTRATO_DESEMBOLSO");
    }

    /**
     * Sets <code>value</code> for bind variable ID_CONTRATO_DESEMBOLSO.
     * @param value value to bind as ID_CONTRATO_DESEMBOLSO
     */
    public void setID_CONTRATO_DESEMBOLSO(String value) {
        setNamedWhereClauseParam("ID_CONTRATO_DESEMBOLSO", value);
    }
    
    public Row llenarCFandCal(String idContratoDesembolso){
        //--- llenar nuevo VOs
        Row row = null;
        System.out.println("***obteniendo CFandCalendario1VOImpl *****");
        setNamedWhereClauseParam("ID_CONTRATO_DESEMBOLSO", idContratoDesembolso);
        executeQuery();
        System.out.println("voCFandCal.getEstimatedRowCount(): "+getEstimatedRowCount());
        if (getEstimatedRowCount() > 0) {
            setCurrentRow(first());
        }
        RowSetIterator rowiter =  this.createRowSetIterator(null);
        rowiter.reset();
        while (rowiter.hasNext()) {
            CFandCalendario1VORowImpl rowCFandCalVO =
            (CFandCalendario1VORowImpl) rowiter.next();
            row = rowCFandCalVO;
        }
        rowiter.closeRowSetIterator();
        if (row != null) {
            String idCalendario = null;
            try {
                idCalendario = String.valueOf(row.getAttribute("Id"));
            } catch (Exception e) {
                System.out.println("CFandCalendario1VOImpl sin registros "+e);
            }

            System.out.println("Se encontro Registro de CFandCalendario1VOImpl. idCalendario: " + idCalendario);
        }
        //---------------------
        return row;
    }
}

