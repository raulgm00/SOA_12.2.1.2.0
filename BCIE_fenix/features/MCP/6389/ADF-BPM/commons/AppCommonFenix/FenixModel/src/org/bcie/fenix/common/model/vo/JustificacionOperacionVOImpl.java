package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.vo.common.JustificacionOperacionVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Oct 07 10:35:32 CDT 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class JustificacionOperacionVOImpl extends ViewObjectImpl implements JustificacionOperacionVO {
    
    /**
     * Class name.
     */
    private static final String CLASS_NAME = JustificacionOperacionVOImpl.class.getName();
    
    /**
     * Aplication logger.
     */
    private static ADFLogger LOGGER = ADFLogger.createADFLogger(CLASS_NAME);
    
    /**
     * This is the default constructor (do not remove).
     */
    public JustificacionOperacionVOImpl() {
    }
    
    /**
     * Nombre del ViewCriteria para buscar la justificacion por id operacion.
     */
    public final String VC_OBTENER_POR_OPERACION = "obtenerJustificacionPorOperacion";
    
    
    /**
     * Obtiene la justificacion por Id Operacion.
     * @param idOperacion
     */
    public void obtenerJustificacionPorId(Number idOperacion) {
        
        final String METHOD_NAME = "obtenerJustificacionPorId";
        LOGGER.entering(CLASS_NAME, METHOD_NAME);
        
        if(idOperacion != null) {
            
            LOGGER.info("Limpiando la consulta y parametros");
            setWhereClause(null);
            setWhereClauseParams(null);
            
            setBindIdOperacion(idOperacion);
            setApplyViewCriteriaName(VC_OBTENER_POR_OPERACION);
            executeQuery();
         
            LOGGER.info("Limpiar ViewCriteria.");
            removeApplyViewCriteriaName(VC_OBTENER_POR_OPERACION);
            
        } else {
            LOGGER.severe("Parametro id operacion es nulo.");
        }
        
        LOGGER.exiting(CLASS_NAME, METHOD_NAME);
        
    }

    /**
     * Validar la justificacion por Id Operacion.
     * @param idOperacion
     */
    public Long validarJustificacion(Number idOperacion) {
        
        final String METHOD_NAME = "validarJustificacion";
        LOGGER.entering(CLASS_NAME, METHOD_NAME);
        
        Long count = null;
        
        if(idOperacion != null) {
            
            LOGGER.info("Limpiando la consulta y parametros");
            setWhereClause(null);
            setWhereClauseParams(null);
            
            setBindIdOperacion(idOperacion);
            setApplyViewCriteriaName(VC_OBTENER_POR_OPERACION);
            executeQuery();

            count = getEstimatedRowCount();

            LOGGER.info("Limpiar ViewCriteria.");
            removeApplyViewCriteriaName(VC_OBTENER_POR_OPERACION);
            
        } else {
            LOGGER.severe("Parametro id operacion es nulo.");
        }
        
        LOGGER.exiting(CLASS_NAME, METHOD_NAME);
        
        return count;
    }

    /**
     * Returns the variable value for BindIdOperacion.
     * @return variable value for BindIdOperacion
     */
    public Number getBindIdOperacion() {
        return (Number) ensureVariableManager().getVariableValue("BindIdOperacion");
    }

    /**
     * Sets <code>value</code> for variable BindIdOperacion.
     * @param value value to bind as BindIdOperacion
     */
    public void setBindIdOperacion(Number value) {
        ensureVariableManager().setVariableValue("BindIdOperacion", value);
    }
}

