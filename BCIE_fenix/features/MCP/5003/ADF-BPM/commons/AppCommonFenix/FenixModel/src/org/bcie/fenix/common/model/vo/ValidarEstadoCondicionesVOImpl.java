package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.ValidarEstadoCondicionesVO;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Dec 20 11:32:20 CST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ValidarEstadoCondicionesVOImpl extends ViewObjectImpl implements ValidarEstadoCondicionesVO {
    
    public static final ADFLogger logger = ADFLogger.createADFLogger(ValidarEstadoCondicionesVOImpl.class);
    
    
    /**
     * This is the default constructor (do not remove).
     */
    public ValidarEstadoCondicionesVOImpl() {
    }
    
    
    public Boolean validarEstadoCondicionesPorIdOperacion(Long idOperacion, Integer idCatProducto) {
        logger.warning("Dentro validarEstadoCondicionesPorIdOperacion");
        Boolean result = null;
        ViewCriteria criteria = null;
        try {
            //asignan valores de filtro para el viewCriteria
            this.setpIdOperacion(idOperacion);
            this.setpIdCatProducto(idCatProducto);
            criteria = getViewCriteria("ValidarEstadoCondicionesVOCriteriaPorIdOperacion");
            applyViewCriteria(criteria);
            executeQuery();
            logger.warning("Numero de registros : " + this.getEstimatedRowCount());
            if (this.getEstimatedRowCount() > 0) {
                logger.warning("Se encontro row con el parametro idOperacion : "+idOperacion);
                result = Boolean.TRUE;
            } else {
                logger.warning("No se encontro row con el parametro idOperacion : "+idOperacion);
                result = Boolean.FALSE;
            }
        } catch (Exception e) {
            logger.severe("Error en el metodo validarEstadoCondicionesPorIdOperacion : ", e);
        } finally {
            logger.warning("Into finally remove criteria : ValidarEstadoCondicionesVOCriteriaPorIdOperacion");
            getViewCriteriaManager().removeApplyViewCriteriaName("ValidarEstadoCondicionesVOCriteriaPorIdOperacion");
        }
        logger.warning("Saliendo validarEstadoCondicionesPorIdOperacion result :"+result);
        return result;
    }


    /**
     * Returns the variable value for pIdOperacion.
     * @return variable value for pIdOperacion
     */
    public Long getpIdOperacion() {
        return (Long) ensureVariableManager().getVariableValue("pIdOperacion");
    }

    /**
     * Sets <code>value</code> for variable pIdOperacion.
     * @param value value to bind as pIdOperacion
     */
    public void setpIdOperacion(Long value) {
        logger.warning("IdOperacion : " + value);
        ensureVariableManager().setVariableValue("pIdOperacion", value);
    }

    /**
     * Returns the variable value for pIdCatProducto.
     * @return variable value for pIdCatProducto
     */
    public Integer getpIdCatProducto() {
        return (Integer) ensureVariableManager().getVariableValue("pIdCatProducto");
    }

    /**
     * Sets <code>value</code> for variable pIdCatProducto.
     * @param value value to bind as pIdCatProducto
     */
    public void setpIdCatProducto(Integer value) {
        logger.warning("pIdCatProducto : " + value);
        ensureVariableManager().setVariableValue("pIdCatProducto", value);
    }
}

