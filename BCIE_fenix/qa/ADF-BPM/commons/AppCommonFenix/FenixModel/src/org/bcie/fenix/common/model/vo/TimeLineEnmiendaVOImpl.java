package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.TimeLineEnmiendaVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 17 16:39:30 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TimeLineEnmiendaVOImpl extends ViewObjectImpl implements TimeLineEnmiendaVO {
    
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public TimeLineEnmiendaVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    
    public Row[] obtenerFechaTimeLineEnmienda(Long idOperacion){
        logger.warning("Dentro obtenerFechaTimeLineEnmienda");
        this.setpIdOperacion(idOperacion);
        this.executeQuery();
        this.setRangeSize(-1);
        logger.warning("Cantidad de registros encontrados : "+this.getRowCount());
        logger.warning("Fuera obtenerFechaTimeLineEnmienda");
        return this.getAllRowsInRange();
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
}

