package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ObtenerDetallePenalidadVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Oct 25 18:24:17 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ObtenerDetallePenalidadVOImpl extends ViewObjectImpl implements ObtenerDetallePenalidadVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public ObtenerDetallePenalidadVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public List<Long> obtenerListaDetallePenalidadPorIdPrepago(Long idPrepago){
        logger.log(ADFLogger.WARNING, "Dentro de obtenerListaDetallePenalidadPorIdPrepago con el parametro idPrepago : "+idPrepago);
        List<Long> listaIdDetallePenalidad = new ArrayList<Long>();
        Long id = null;
        try {
            ViewCriteria criteria =this.getViewCriteriaManager().getViewCriteria("ObtenerDetallePenalidadVOCriteriaByIdPrepago");
            criteria.ensureVariableManager().setVariableValue("VarIdPrepago", idPrepago);
            this.applyViewCriteria(criteria);
            this.executeQuery();
            this.setRangeSize(-1);
            for(Row row : this.getAllRowsInRange()){
                id = (Long) row.getAttribute("Id");
                listaIdDetallePenalidad.add(id);
            }
        } catch (Exception ex) {
            logger.log(ADFLogger.ERROR, "Error en obtenerListaDetallePenalidadPorIdPrepago " + ex.getClass() + ":" + ex.getMessage());
        } finally {
            //This takes care of removing the applied VC.
            this.getViewCriteriaManager().removeApplyViewCriteriaName("ObtenerDetallePenalidadVOCriteriaByIdPrepago");
        }
        logger.log(ADFLogger.WARNING, "obtenerListaDetallePenalidadPorIdPrepago retorna : "+listaIdDetallePenalidad);
        return listaIdDetallePenalidad;
    }


    /**
     * Returns the variable value for VarIdPrepago.
     * @return variable value for VarIdPrepago
     */
    public Long getVarIdPrepago() {
        return (Long) ensureVariableManager().getVariableValue("VarIdPrepago");
    }

    /**
     * Sets <code>value</code> for variable VarIdPrepago.
     * @param value value to bind as VarIdPrepago
     */
    public void setVarIdPrepago(Long value) {
        ensureVariableManager().setVariableValue("VarIdPrepago", value);
    }
}

