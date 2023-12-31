package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.NameValuePairs;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.ViewObjectImpl;

import static org.bcie.fenix.common.model.FenixModelConstants.BANESTATUS_TRUE;
import static org.bcie.fenix.common.model.FenixModelConstants.ES_PRINCIPAL_FALSE;
import org.bcie.fenix.common.model.vo.common.ObservacionCondicionVO;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jan 13 13:17:51 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ObservacionCondicionVOImpl extends ViewObjectImpl implements ObservacionCondicionVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public ObservacionCondicionVOImpl() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public Long obtenerEstimatedRowCount() {
        
        return this.getEstimatedRowCount();
    }
    
    public void agregarObservacion(Long idCondicion, Integer idTareaBpm, String observacion, String loginUsuario, String nombreUsuario, Long idAgrupador) {
        logger.warning("Inside agregarObservacion.");
        
        // Creamos una nueva observación
        oracle.jbo.domain.Number idObservacion = null;
        NameValuePairs nvpObservacion = null;
        SequenceImpl seqObservacion = null;
        
        seqObservacion = new SequenceImpl("OBSERVACION_CONDICION_SEQ", getDBTransaction());
        idObservacion = seqObservacion.getSequenceNumber();
        
        nvpObservacion = new NameValuePairs();
        nvpObservacion.setAttribute("Id", idObservacion);
        nvpObservacion.setAttribute("IdCondicion", idCondicion);
        nvpObservacion.setAttribute("IdTcaTareaBpm", idTareaBpm);
        nvpObservacion.setAttribute("Observacion", observacion);
        nvpObservacion.setAttribute("LoginUsuario", loginUsuario);
        nvpObservacion.setAttribute("NombreUsuario", nombreUsuario);
        nvpObservacion.setAttribute("FechaRegistro", new java.sql.Timestamp(System.currentTimeMillis()));
        nvpObservacion.setAttribute("BanEstatus", BANESTATUS_TRUE); // Estado Activo = 1
        nvpObservacion.setAttribute("EsPrincipal", ES_PRINCIPAL_FALSE);
        nvpObservacion.setAttribute("Agrupador", idAgrupador);
        
        this.createAndInitRow(nvpObservacion);
        getDBTransaction().commit();
    }

    /**
     * Returns the bind variable value for varIdCondicion.
     * @return bind variable value for varIdCondicion
     */
    public Long getvarIdCondicion() {
        return (Long) getNamedWhereClauseParam("varIdCondicion");
    }

    /**
     * Sets <code>value</code> for bind variable varIdCondicion.
     * @param value value to bind as varIdCondicion
     */
    public void setvarIdCondicion(Long value) {
        logger.log(ADFLogger.WARNING, "Inside setvarIdCondicion: " + value);
        setNamedWhereClauseParam("varIdCondicion", value);
    }
    
    public void refreshQuery() {
        logger.log(ADFLogger.WARNING, "Inside refreshQuery.");
        this.executeQuery();
        logger.warning("Numero de rows :" + this.getEstimatedRowCount());
    }
    
    public Long obtenerEstimatedRowCount(Long idCondicion) {
        logger.log(ADFLogger.TRACE, "Inside obtenerEstimatedRowCount.");
        Long varIdCondicionActual = null;
        Long estimatedRowCount = null;
        
        // Obtenemos el valor del varIdCondicion actual
        varIdCondicionActual = this.getvarIdCondicion();
        
        // Filtramos query con el idCondicion requerido
        setvarIdCondicion(idCondicion);
        this.executeQuery();
        estimatedRowCount = this.getEstimatedRowCount();
        
        // Regresamos el query a su valor anterior
        setvarIdCondicion(varIdCondicionActual);
        this.executeQuery();
        
        return estimatedRowCount;
    }

    public Long obtenerObservacionCondicionPorIdAgrupador(Long idCondicion, Long idAgrupador) {
        logger.warning("Entra en obtenerObservacionCondicionPorIdAgrupador");
        logger.warning("idCondicion: "+idCondicion);
        logger.warning("idAgrupador: "+idAgrupador);
        Long numeroObservacion = null;
        try {
            setvarIdCondicion(idCondicion);
            setvarIdAgrupador(idAgrupador);
            this.executeQuery();

            numeroObservacion = this.getEstimatedRowCount();
            logger.warning("numero de observaciones recuperadas :" + numeroObservacion);
        } catch (Exception e) {
            logger.warning("Error al obtener el numero de observaciones.", e);
        } 
        logger.warning("termina metodo obtenerObservacionCondicionPorIdAgrupador");
        return numeroObservacion;
    }
    /**
     * Returns the variable value for varIdAgrupador.
     * @return variable value for varIdAgrupador
     */
    public Long getvarIdAgrupador() {
        return (Long) ensureVariableManager().getVariableValue("varIdAgrupador");
    }

    /**
     * Sets <code>value</code> for variable varIdAgrupador.
     * @param value value to bind as varIdAgrupador
     */
    public void setvarIdAgrupador(Long value) {
        logger.log(ADFLogger.WARNING, "Inside setvarIdAgrupador: " + value);
        ensureVariableManager().setVariableValue("varIdAgrupador", value);
    }
}

