package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.LineaCreditoDesembolsosTreeQueryVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 31 11:58:48 CDT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class LineaCreditoDesembolsosTreeQueryVOImpl extends ViewObjectImpl implements LineaCreditoDesembolsosTreeQueryVO {

    private static ADFLogger logger = null;

    /**
     * This is the default constructor (do not remove).
     */
    public LineaCreditoDesembolsosTreeQueryVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void establecerRowComoActual(Long idLinea, Long idSolicitud) {
        logger.log(ADFLogger.WARNING, "Inicia metodo establecerRowComoActual con Id de linea: " + idLinea);

        ViewCriteria criteria = null;
        Row row = null;

        if (idLinea != null) {
            try {
                logger.log(ADFLogger.WARNING, "< Inside the try > IdLinea" + idLinea + " idSolicitud->" + idSolicitud);
                criteria = this.getViewCriteriaManager().getViewCriteria("BuscarLineaPorIdLineaIdSolicitudVC");
                criteria.ensureVariableManager().setVariableValue("pIdLinea", idLinea);
                criteria.ensureVariableManager().setVariableValue("pIdSolicitud", idSolicitud);
                this.applyViewCriteria(criteria);
                this.executeQuery();

                if (getEstimatedRowCount() > 0) {
                    logger.log(ADFLogger.WARNING, "Registros encontrados: " + getEstimatedRowCount());
                    row = this.getRowAtRangeIndex(0);
                    setCurrentRow(row);

                } else {
                    logger.log(ADFLogger.WARNING, "El row es NULL");
                }
            } catch (Exception e) {
                logger.log(ADFLogger.WARNING,
                           "Error en establecerRowComoActual " + e.getClass() + ":" + e.getMessage());
            } finally {
                this.getViewCriteriaManager().removeApplyViewCriteriaName("BuscarLineaPorIdLineaVC");
            }
        } else {
            logger.warning("El Id de la lista es NULL");
        }
        
        logger.log(ADFLogger.WARNING, "Termina metodo establecerRowComoActual con Id de linea: " + idLinea);
    }

    /**
     * Returns the variable value for pIdLinea.
     * @return variable value for pIdLinea
     */
    public Long getpIdLinea() {
        return (Long) ensureVariableManager().getVariableValue("pIdLinea");
    }

    /**
     * Sets <code>value</code> for variable pIdLinea.
     * @param value value to bind as pIdLinea
     */
    public void setpIdLinea(Long value) {
        ensureVariableManager().setVariableValue("pIdLinea", value);
    }

    /**
     * Returns the variable value for pIdSOlicitud.
     * @return variable value for pIdSOlicitud
     */
    public Long getpIdSolicitud() {
        return (Long) ensureVariableManager().getVariableValue("pIdSolicitud");
    }

    /**
     * Sets <code>value</code> for variable pIdSOlicitud.
     * @param value value to bind as pIdSOlicitud
     */
    public void setpIdSolicitud(Long value) {
        ensureVariableManager().setVariableValue("pIdSolicitud", value);
    }
}

