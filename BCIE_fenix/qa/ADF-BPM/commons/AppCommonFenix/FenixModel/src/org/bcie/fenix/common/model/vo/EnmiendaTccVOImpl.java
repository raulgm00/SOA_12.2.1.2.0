package org.bcie.fenix.common.model.vo;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.ViewObjectImpl;

import static org.bcie.fenix.common.model.FenixModelConstants.BANESTATUS_FALSE;
import static org.bcie.fenix.common.model.FenixModelConstants.BANESTATUS_TRUE;
import org.bcie.fenix.common.model.vo.common.EnmiendaTccVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Feb 26 13:53:45 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EnmiendaTccVOImpl extends ViewObjectImpl implements EnmiendaTccVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public EnmiendaTccVOImpl() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    /**
     * Returns the variable value for varIdOperacion.
     * @return variable value for varIdOperacion
     */
    public Long getvarIdOperacion() {
        return (Long) ensureVariableManager().getVariableValue("varIdOperacion");
    }

    /**
     * Sets <code>value</code> for variable varIdOperacion.
     * @param value value to bind as varIdOperacion
     */
    public void setvarIdOperacion(Long value) {
        ensureVariableManager().setVariableValue("varIdOperacion", value);
    }

    /**
     * Returns the variable value for varInstanciaProceso.
     * @return variable value for varInstanciaProceso
     */
    public String getvarInstanciaProceso() {
        return (String) ensureVariableManager().getVariableValue("varInstanciaProceso");
    }

    /**
     * Sets <code>value</code> for variable varInstanciaProceso.
     * @param value value to bind as varInstanciaProceso
     */
    public void setvarInstanciaProceso(String value) {
        ensureVariableManager().setVariableValue("varInstanciaProceso", value);
    }
    
    public Long getIdEnmiendaTccByOperacionInstanciaProceso(Long idOperacion, String instanciaProceso) {
        logger.log(ADFLogger.TRACE, "Inside getIdEnmiendaTccByOperacionInstanciaProceso.");
        Row enmiendaTccRow = null;
        
        // 1) Filtramos el VO por idOperacion e instanciaProceso para saber si ya existe un id asociado
        enmiendaTccRow = getEnmiendaTccByOperacionInstanciaProceso(idOperacion, instanciaProceso);
        
        // 2) Si no existe un row bajo ese criterio, creamos uno nuevo
        if(enmiendaTccRow == null) {
            
            enmiendaTccRow = crearEnmiendaTcc(idOperacion, instanciaProceso);
            
            // Re-ejecutamos el query debido a que se insert� un nuevo contrato
            this.executeQuery();
        }
        
        // 3) Asignamos valor de retorno
        return (enmiendaTccRow != null ? (Long)enmiendaTccRow.getAttribute("Id") : null);
    }
    
    private Row getEnmiendaTccByOperacionInstanciaProceso(Long idOperacion, String instanciaProceso) {
        logger.log(ADFLogger.TRACE, "Inside getEnmiendaTccByOperacionInstanciaProceso.");
        Row enmiendaTccRow = null;
        ViewCriteria criteriaByOperacionInstanciaProceso = null;
        
        // Los atributos idOperacion e instanciaProceso son requeridos por el ViewCriteria
        if((idOperacion != null) && (instanciaProceso != null)) {
            // 1) Filtramos el VO por idOperacion e instanciaProceso
            criteriaByOperacionInstanciaProceso = 
                this.getViewCriteriaManager().getViewCriteria("EnmiendaTccVOCriteriaByOperacionInstanciaProceso");
            criteriaByOperacionInstanciaProceso.ensureVariableManager().setVariableValue("varIdOperacion", idOperacion);
            criteriaByOperacionInstanciaProceso.ensureVariableManager().setVariableValue("varInstanciaProceso", instanciaProceso);
            
            this.applyViewCriteria(criteriaByOperacionInstanciaProceso);
            this.executeQuery();
            
            // 2) Asignamos valor de retorno
            if(this.getEstimatedRowCount() > 0) {
                enmiendaTccRow = this.getRowAtRangeIndex(0);
                
                // Removemos criteria del VO
                this.getViewCriteriaManager().removeApplyViewCriteriaName("EnmiendaTccVOCriteriaByOperacionInstanciaProceso");
                
                // Re-ejecutamos el query 
                this.executeQuery();
            }
        }
        
        return enmiendaTccRow;
    }
    
    private Row crearEnmiendaTcc(Long idOperacion, String instanciaProceso) {
        logger.log(ADFLogger.TRACE, "Inside crearEnmiendaTcc.");
        
        // Creamos una nueva Enmienda asignado a un IdOperacion e InstanciaProceso
        Row enmiendaTccRow = null;
        oracle.jbo.domain.Number idEnmiendaTcc = null;
        NameValuePairs nvpEnmiendaTcc = null;
        SequenceImpl seqEnmiendaTcc = null;

        seqEnmiendaTcc = new SequenceImpl("ENMIENDA_TCC_SEQ", getDBTransaction());
        idEnmiendaTcc = seqEnmiendaTcc.getSequenceNumber();
        System.out.println("Inicia metodo crear enmiendas ");
        nvpEnmiendaTcc = new NameValuePairs();
        nvpEnmiendaTcc.setAttribute("BanEstatus", BANESTATUS_TRUE);
        nvpEnmiendaTcc.setAttribute("FechaRegistro", new java.sql.Timestamp(System.currentTimeMillis()));
        nvpEnmiendaTcc.setAttribute("Id", idEnmiendaTcc);
        nvpEnmiendaTcc.setAttribute("IdOperacion", idOperacion);
        nvpEnmiendaTcc.setAttribute("InstanciaProceso", instanciaProceso);
        nvpEnmiendaTcc.setAttribute("RequiereAsjur", BANESTATUS_FALSE);
        nvpEnmiendaTcc.setAttribute("RequiereOgc", BANESTATUS_FALSE);
        nvpEnmiendaTcc.setAttribute("RequiereCof", BANESTATUS_FALSE);
        nvpEnmiendaTcc.setAttribute("BanderaFormalizacion", BANESTATUS_FALSE);
        nvpEnmiendaTcc.setAttribute("CambiodePlazo", BANESTATUS_FALSE);
        nvpEnmiendaTcc.setAttribute("EsDesobligacion", BANESTATUS_FALSE);
        
        enmiendaTccRow = this.createAndInitRow(nvpEnmiendaTcc);
                
        getDBTransaction().commit();
        return enmiendaTccRow;
    }
    
    public Boolean actualizarInstanciaEnmienda(Long idEnmienda, String instanciaProceso){
            logger.log(ADFLogger.TRACE, "Inside actualizarInstanciaEnmienda.");
            Boolean esCorrecto=Boolean.TRUE;
            //Refresca VO
            executeQuery();
     //   logger.warning("");
            if(idEnmienda != null && !instanciaProceso.isEmpty()) {
            Row row = this.getRow(new Key(new Object[]{idEnmienda}));
            if(row != null){
                String instancia=(String) row.getAttribute("InstanciaProceso");
                if(null==instancia || instancia.isEmpty() || instancia.equalsIgnoreCase("0")){
                        row.setAttribute("InstanciaProceso", instanciaProceso);
                        logger.warning("Asigna current row");
                        this.setCurrentRow(row);
                        try{
                            logger.warning("Ejecuta Commit");
                            getDBTransaction().commit();
                        }catch(Exception e){
                            esCorrecto = Boolean.FALSE;
                            logger.severe("Error al realizar Commit", e);
                            try{
                                getDBTransaction().rollback();
                            }catch(Exception ex){
                                logger.severe("Error al realizar Rollback al fallar Commit", ex);
                                                }
                                            }
                                }
                            else{
                                    logger.warning("Ya existe una instancia, por lo tanto no se actualiza enmienda");
                                }
                        }
                        else{
                        esCorrecto = Boolean.FALSE;
                        }
                    }
        else{
                logger.warning("No esxisten datos de id enmienda o de instancia");
            }
            return esCorrecto;
    }
    
    
    public Row getRowEnmiendaTCC(Long idEnmienda) {
        logger.warning("Dentro de getRowEnmiendaTCC");
        logger.warning("idEnmienda :"+idEnmienda);
        Row enmiendaTccRow = null;
        ViewCriteria enmiendaTccVOCriteriaById = null;
        
        // Los atributos idEnmienda son requeridos por el ViewCriteria
        if((idEnmienda != null)) {
            // 1) Filtramos el VO por idEnmienda
            enmiendaTccVOCriteriaById = 
                this.getViewCriteriaManager().getViewCriteria("EnmiendaTccVOCriteriaById");
            enmiendaTccVOCriteriaById.ensureVariableManager().setVariableValue("varIdEnmienda", idEnmienda);
            
            
            this.applyViewCriteria(enmiendaTccVOCriteriaById);
            this.executeQuery();
            
            // 2) Asignamos valor de retorno
            if(this.getEstimatedRowCount() > 0) {
                enmiendaTccRow = this.getRowAtRangeIndex(0);
                
                // Removemos criteria del VO
                this.getViewCriteriaManager().removeApplyViewCriteriaName("EnmiendaTccVOCriteriaById");
                
                // Re-ejecutamos el query 
                this.executeQuery();
            }
        }else{
            logger.severe("idEnmienda es nulo, no recupera row");
        }
        logger.warning("Fuera de getRowEnmiendaTCC");
        return enmiendaTccRow;
    }

    /**
     * Returns the variable value for varIdEnmienda.
     * @return variable value for varIdEnmienda
     */
    public Long getvarIdEnmienda() {
        return (Long) ensureVariableManager().getVariableValue("varIdEnmienda");
    }

    /**
     * Sets <code>value</code> for variable varIdEnmienda.
     * @param value value to bind as varIdEnmienda
     */
    public void setvarIdEnmienda(Long value) {
        ensureVariableManager().setVariableValue("varIdEnmienda", value);
    }
}
