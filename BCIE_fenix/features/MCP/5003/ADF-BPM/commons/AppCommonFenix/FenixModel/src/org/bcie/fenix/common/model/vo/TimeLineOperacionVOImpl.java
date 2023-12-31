package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.TimeLineOperacionVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed May 17 14:27:36 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TimeLineOperacionVOImpl extends ViewObjectImpl implements TimeLineOperacionVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public TimeLineOperacionVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public Row[] obtenerFechaTimeLineOperacion(Long idOperacion){
        logger.warning("Dentro obtenerFechaTimeLineOperacion");
        logger.warning("idOperacion: " + idOperacion);
        this.setpIdOperacion(idOperacion);
        this.executeQuery();
        this.setRangeSize(-1);
        logger.warning("Cantidad de registros encontrados : "+this.getRowCount());
        logger.warning("Fuera obtenerFechaTimeLineOperacion");
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

