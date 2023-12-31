package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Key;
import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.fenix.common.model.FenixModelConstants;
import static org.bcie.fenix.common.model.FenixModelConstants.BANESTATUS_TRUE;
import org.bcie.fenix.common.model.vo.common.CargoPrepagoVO;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 08 18:45:51 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CargoPrepagoVOImpl extends ViewObjectImpl implements CargoPrepagoVO {
    /**
     * This is the default constructor (do not remove).
     */
    
    private static ADFLogger logger = null;
    
    public CargoPrepagoVOImpl() {
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public Row buscarCargoPrepagoPorId(Long id){
        logger.warning("Dentro de buscarCargoPrepagoPorId idCargoPrepago:"+id);
        Row row = null;
        ViewCriteria criteria = null;
        try{                                                    
            this.setvarIdCargoPrepago(id);                                 
            criteria = getViewCriteria("CargoPrepagoPorId");                       
            this.applyViewCriteria(criteria);
            this.executeQuery();
            for(Row rowAux : this.getAllRowsInRange()){
                row = rowAux;
            }
        }catch(Exception e){
            logger.severe("Error al ejecutar el criteria CargoPrepagoPorId : ",e);                                    
        }finally{
            getViewCriteriaManager().removeApplyViewCriteriaName("CargoPrepagoPorId");  
        }    
        logger.warning("Fuera de buscarCargoPrepagoPorId");
        return row;
    }
    
    public Long insertarRowCargoPrepago(Long idPrepago, Integer idTcaRolBpm, Integer aplicaCargoAdicional, 
                                        Timestamp fechaReferencia, Integer idTcaTipoMonedaTramite, Timestamp fechaVigentePenalizacion,
                                        BigDecimal montoCargoTramite, BigDecimal prime, BigDecimal tasaLibor,
                                        BigDecimal montoCargo, Integer idTcaTipoMoneda, Long idObservacion){
                                                                                        
        logger.log(ADFLogger.WARNING, "INTO insertarRowCargoPrepago");
        logger.log(ADFLogger.WARNING, "idPrepago: " + idPrepago);
        logger.log(ADFLogger.WARNING, "idTcaRolBpm: " + idTcaRolBpm);
        logger.log(ADFLogger.WARNING, "aplicaCargoAdicional: " + aplicaCargoAdicional);
        logger.log(ADFLogger.WARNING, "fechaReferencia: " + fechaReferencia);
        logger.log(ADFLogger.WARNING, "idTcaTipoMonedaTramite: " + idTcaTipoMonedaTramite);
        logger.log(ADFLogger.WARNING, "fechaVigentePenalizacion: " + fechaVigentePenalizacion);
        logger.log(ADFLogger.WARNING, "montoCargoTramite: " + montoCargoTramite);
        logger.log(ADFLogger.WARNING, "prime: " + prime);
        logger.log(ADFLogger.WARNING, "tasaLibor: " + tasaLibor);
        logger.log(ADFLogger.WARNING, "montoCargo: " + montoCargo);
        logger.log(ADFLogger.WARNING, "idTcaTipoMoneda: " + idTcaTipoMoneda);
        
        Long idPrepagoSeq = null;
        Row rowCargoPrepago = null;
        
        oracle.jbo.domain.Number idCargoPrepago = null;
        SequenceImpl sequenceCargoPrepago = null;
        NameValuePairs nvpCargoPrepago = null;
        
        try{
            sequenceCargoPrepago = new SequenceImpl("CARGO_PREPAGO_SEQ", getDBTransaction());
            idCargoPrepago = sequenceCargoPrepago.getSequenceNumber();
            nvpCargoPrepago = new NameValuePairs();
            
            idPrepagoSeq = idCargoPrepago.longValue();
            
            nvpCargoPrepago.setAttribute("Id", idPrepagoSeq);
            nvpCargoPrepago.setAttribute("IdPrepago",idPrepago);
            nvpCargoPrepago.setAttribute("IdTcaRolBpm",idTcaRolBpm);
            nvpCargoPrepago.setAttribute("AplicaCargoAdicional", aplicaCargoAdicional);
            nvpCargoPrepago.setAttribute("MontoCargo", montoCargo);
            nvpCargoPrepago.setAttribute("IdTcaTipoMoneda", idTcaTipoMoneda);
            nvpCargoPrepago.setAttribute("IdObservacion", idObservacion);
            nvpCargoPrepago.setAttribute("MontoCargoTramite", montoCargoTramite);
            nvpCargoPrepago.setAttribute("IdTcaTipoMonedaTramite", idTcaTipoMonedaTramite);
            nvpCargoPrepago.setAttribute("TasaLibor", tasaLibor);
            nvpCargoPrepago.setAttribute("Prime", prime);
            nvpCargoPrepago.setAttribute("FechaReferencia", fechaReferencia);
            nvpCargoPrepago.setAttribute("FechaVigentePenalizacion", fechaVigentePenalizacion);
            nvpCargoPrepago.setAttribute("FechaRegistro", new java.sql.Timestamp(System.currentTimeMillis()));
            nvpCargoPrepago.setAttribute("BanEstatus", BANESTATUS_TRUE);
            
            rowCargoPrepago = this.createAndInitRow(nvpCargoPrepago);
            getDBTransaction().commit();
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error en insertarRowCargoPrepago : ", e);
            getDBTransaction().rollback();
        }
        this.executeQuery();
        return idPrepagoSeq;
    }
    
    public void actualizarCargoPrepago(Long idCargoPrepago, Integer aplicaCargoAdicional,BigDecimal montoCargo, 
                                        Integer idTcaTipoMoneda,BigDecimal montoCargoTramite,Integer idTcaTipoMonedaTramite,
                                            Timestamp fechaVigentePenalizacion,Timestamp fechaReferencia,BigDecimal prime,
                                                BigDecimal tasaLibor){
        logger.warning("Into actualizarCargoPrepago.");
        Row rowCargoPrepago = null;
        rowCargoPrepago = buscarCargoPrepagoPorId(idCargoPrepago);
        logger.log(ADFLogger.WARNING, "Valor ID de cargo prepago." + idCargoPrepago + "Numero de rows.."+ this.getRowCount());
        try{
        if(rowCargoPrepago != null){
            logger.log(ADFLogger.WARNING,"Row encontrado.");
            rowCargoPrepago.setAttribute("AplicaCargoAdicional", aplicaCargoAdicional);
            rowCargoPrepago.setAttribute("MontoCargo", montoCargo);
            rowCargoPrepago.setAttribute("IdTcaTipoMoneda", idTcaTipoMoneda);
            rowCargoPrepago.setAttribute("MontoCargoTramite", montoCargoTramite);
            rowCargoPrepago.setAttribute("IdTcaTipoMonedaTramite", idTcaTipoMonedaTramite);
            rowCargoPrepago.setAttribute("FechaVigentePenalizacion", fechaVigentePenalizacion);
            rowCargoPrepago.setAttribute("FechaReferencia", fechaReferencia);
            rowCargoPrepago.setAttribute("Prime", prime);
            rowCargoPrepago.setAttribute("TasaLibor", tasaLibor);
            getDBTransaction().commit();
        }else{
            logger.warning("Row no encontrado.");
        }
        }catch(Exception ex){
            logger.severe("Error al actualizar el cargo de prepago en actualizarCargoPrepago.",ex);
        }
        
    }
    
    public Map obtenerCargoPrepagoByIdPrepago(Long idPrepago){
        
        logger.log(ADFLogger.WARNING, "INTO  obtenerCargoPrepagoByIdPrepago : ");
        //variable Map que se va retornar
        Map<String,Object> montosCargoPrepago = new HashMap<String,Object>();
        //variables temporales que se recuperan del criterio de busqueda
        
        ///////////////////////////////////////
        BigDecimal montoCargoPrepagoLocal = new BigDecimal(0);
        BigDecimal totalMontoCargoPrepagoLocal = new BigDecimal(0);
        BigDecimal montoCargoPrepagoDolares = new BigDecimal(0);
        BigDecimal totalMontoCargoPrepagoDolares = new BigDecimal(0);
        //Monto Cargo obtenido por area
        BigDecimal montoCargoCopres = new BigDecimal(0);
        Integer idTipoMonedaCopres = null;
        BigDecimal montoCargoDaeci = new BigDecimal(0);
        Integer idTipoMonedaDaeci = null;
        BigDecimal montoCargoMdc = new BigDecimal(0);
        Integer idTipoMonedaMdc = null;
        BigDecimal montoCargoSepri = new BigDecimal(0);
        Integer idTipoMonedaSepri = null;
        BigDecimal montoCargoCofi = new BigDecimal(0);
        Integer idTipoMonedaCofi = null;
        BigDecimal montoCargoTramiteCofi = new BigDecimal(0);
        Integer idTipoMonedaTramiteCofi = null;
        Integer idTcaRolBpm = null;
        Timestamp fechaVigentePenalizacion = null;
        
        Integer idTipoMoneda = null;
        Integer idTipoMonedaLocal = null;
        Integer idTipoMonedaDolares = null;
        
        //////////////////////////////////////////
        
        try {
            ViewCriteria criteria =this.getViewCriteriaManager().getViewCriteria("CargoPrepagoVOCriteria");
            criteria.ensureVariableManager().setVariableValue("pIdPrepago", idPrepago);
            this.applyViewCriteria(criteria);
            this.executeQuery();
        }catch(Exception e){
            logger.log(ADFLogger.WARNING, "Error al ejecutar criterio de busqueda." + e.getClass() + "." + e);
        }
            
            RowSetIterator rowsCategoriaVo = createRowSetIterator(null);
            rowsCategoriaVo.reset();
            try{
                while (rowsCategoriaVo.hasNext()) {
                    CargoPrepagoVORowImpl rowL = (CargoPrepagoVORowImpl) rowsCategoriaVo.next();
                        if(null != rowL.getAttribute("IdTcaTipoMoneda")){
                            idTipoMoneda = (Integer)rowL.getAttribute("IdTcaTipoMoneda");
                    if(idTipoMoneda.compareTo(FenixModelConstants.MONEDA_USD) == 0){
                        if(null != rowL.getAttribute("MontoCargo")){
                        montoCargoPrepagoDolares = (BigDecimal)rowL.getAttribute("MontoCargo");
                        totalMontoCargoPrepagoDolares = totalMontoCargoPrepagoDolares.add(montoCargoPrepagoDolares);
                        }else{
                            logger.log(ADFLogger.WARNING, "Valor MontoCargo es nulo.");
                        }
                        if(null != rowL.getAttribute("IdTcaTipoMoneda")){
                        idTipoMonedaDolares = (Integer)rowL.getAttribute("IdTcaTipoMoneda");
                        }else{
                            logger.log(ADFLogger.WARNING, "Valor IdTcaTipoMoneda es nulo.");
                        }
                    }else{
                        if(null != rowL.getAttribute("MontoCargo")){
                        montoCargoPrepagoLocal = (BigDecimal)rowL.getAttribute("MontoCargo");
                        totalMontoCargoPrepagoLocal = totalMontoCargoPrepagoLocal.add(montoCargoPrepagoLocal);
                        }else{
                            logger.log(ADFLogger.WARNING, "Valor MontoCargo es nulo.");
                        }
                        if(null != rowL.getAttribute("IdTcaTipoMoneda")){
                        idTipoMonedaLocal = (Integer)rowL.getAttribute("IdTcaTipoMoneda");
                        }else{
                            logger.log(ADFLogger.WARNING, "Valor IdTcaTipoMoneda es nulo.");
                        }
                    }
                        }else{
                            logger.log(ADFLogger.WARNING, "Valor IdTcaTipoMoneda es nulo.");
                        }
                        if(null != rowL.getAttribute("IdTcaRolBpm")){
                            idTcaRolBpm = (Integer)rowL.getAttribute("IdTcaRolBpm");
                            
                    if(idTcaRolBpm.compareTo(FenixModelConstants.COPRES) == 0){
                        if(null != rowL.getAttribute("MontoCargo")){
                        montoCargoCopres = (BigDecimal)rowL.getAttribute("MontoCargo");
                        }else{
                            logger.log(ADFLogger.WARNING, "Valor MontoCargo COPRES es nulo.");  
                        }
                        if(null != rowL.getAttribute("IdTcaTipoMoneda")){
                        idTipoMonedaCopres = (Integer)rowL.getAttribute("IdTcaTipoMoneda");
                        }else{
                            logger.log(ADFLogger.WARNING, "Valor IdTcaTipoMoneda COPRES es nulo.");
                        }
                    }
                        if(idTcaRolBpm.compareTo(FenixModelConstants.DAECI) == 0){
                            if(null != rowL.getAttribute("MontoCargo")){
                            montoCargoDaeci = (BigDecimal)rowL.getAttribute("MontoCargo");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor MontoCargo DAECI es nulo.");
                            }
                            if(null != rowL.getAttribute("IdTcaTipoMoneda")){
                            idTipoMonedaDaeci = (Integer)rowL.getAttribute("IdTcaTipoMoneda"); 
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor IdTcaTipoMoneda DAECI es nulo.");
                            }
                        }
                        if(idTcaRolBpm.compareTo(FenixModelConstants.MDC) == 0){
                            if(null != rowL.getAttribute("MontoCargo")){
                            montoCargoMdc = (BigDecimal)rowL.getAttribute("MontoCargo");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor MontoCargo MDC es nulo.");
                            }
                            if(null != rowL.getAttribute("IdTcaTipoMoneda")){
                            idTipoMonedaMdc = (Integer)rowL.getAttribute("IdTcaTipoMoneda");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor IdTcaTipoMoneda MDC es nulo.");
                            }
                        }
                        if(idTcaRolBpm.compareTo(FenixModelConstants.SEPRI) == 0){
                            if(null != rowL.getAttribute("MontoCargo")){
                            montoCargoSepri = (BigDecimal)rowL.getAttribute("MontoCargo");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor MontoCargo SEPRI es nulo.");
                            }
                            if(null != rowL.getAttribute("IdTcaTipoMoneda")){
                            idTipoMonedaSepri = (Integer)rowL.getAttribute("IdTcaTipoMoneda");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor IdTcaTipoMoneda SEPRI es nulo.");
                            }
                        }
                        if(idTcaRolBpm.compareTo(FenixModelConstants.COFI) == 0){
                            if(null != rowL.getAttribute("MontoCargo")){
                            montoCargoCofi = (BigDecimal)rowL.getAttribute("MontoCargo");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor MontoCargo COFI es nulo.");
                            }
                            if(null != rowL.getAttribute("IdTcaTipoMoneda")){
                            idTipoMonedaCofi = (Integer)rowL.getAttribute("IdTcaTipoMoneda");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor IdTcaTipoMoneda COFI es nulo.");
                            }
                            if(null != rowL.getAttribute("MontoCargoTramite")){
                            montoCargoTramiteCofi = (BigDecimal)rowL.getAttribute("MontoCargoTramite");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor MontoCargoTramite COFI es nulo.");
                            }
                            if(null != rowL.getAttribute("IdTcaTipoMonedaTramite")){
                            idTipoMonedaTramiteCofi = (Integer)rowL.getAttribute("IdTcaTipoMonedaTramite");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor IdTcaTipoMonedaTramite COFI es nulo.");
                            }
                            if(null != rowL.getAttribute("FechaVigentePenalizacion")){
                            fechaVigentePenalizacion = (Timestamp)rowL.getAttribute("FechaVigentePenalizacion");
                            }else{
                                logger.log(ADFLogger.WARNING, "Valor FechaVigentePenalizacion COFI es nulo.");
                            }
                        }
                        }else{
                            logger.log(ADFLogger.WARNING, "Valor IdTcaRolBpm es nulo.");
                        }
                        logger.warning("Monto penalidad: " + rowL.getAttribute("MontoCargo"));
                    }
            rowsCategoriaVo.closeRowSetIterator();
            }catch(Exception e){
                logger.log(ADFLogger.WARNING, "Error al ejecutar el WHILE." + e.getClass() + "." + e);
            }
            
            try{
            montosCargoPrepago.put("totalMontoCargoPrepagoDolares", totalMontoCargoPrepagoDolares);
            montosCargoPrepago.put("idTipoMonedaDolares", idTipoMonedaDolares);
            montosCargoPrepago.put("totalMontoCargoPrepagoLocal", totalMontoCargoPrepagoLocal);
            montosCargoPrepago.put("idTipoMonedaLocal", idTipoMonedaLocal);
            //Se agrega el Cargo del prepago por Area
            montosCargoPrepago.put("montoCargoCopres", montoCargoCopres);
            montosCargoPrepago.put("idTipoMonedaCopres", idTipoMonedaCopres);
            montosCargoPrepago.put("montoCargoDaeci", montoCargoDaeci);
            montosCargoPrepago.put("idTipoMonedaDaeci", idTipoMonedaDaeci);
            montosCargoPrepago.put("montoCargoMdc", montoCargoMdc);
            montosCargoPrepago.put("idTipoMonedaMdc", idTipoMonedaMdc);
            montosCargoPrepago.put("montoCargoSepri", montoCargoSepri);
            montosCargoPrepago.put("idTipoMonedaSepri", idTipoMonedaSepri);
            montosCargoPrepago.put("montoCargoCofi", montoCargoCofi);
            montosCargoPrepago.put("idTipoMonedaCofi", idTipoMonedaCofi);
            montosCargoPrepago.put("montoCargoTramiteCofi", montoCargoTramiteCofi);
            montosCargoPrepago.put("idTipoMonedaTramiteCofi", idTipoMonedaTramiteCofi);
            montosCargoPrepago.put("fechaVigentePenalizacion", fechaVigentePenalizacion);
            ////////////////////////////////////////////////////////////////////////////////////////

        } catch (Exception ex) {
            logger.log(ADFLogger.ERROR, "Error al agregar valores al MAPA." + ex.getClass() + ":" + ex.getMessage());
        } finally {
            //This takes care of removing the applied VC.
            this.getViewCriteriaManager().removeApplyViewCriteriaName("CargoPrepagoVOCriteria");
        }
        return montosCargoPrepago;
    }

    /**
     * Returns the variable value for pIdPrepago.
     * @return variable value for pIdPrepago
     */
    public Long getpIdPrepago() {
        return (Long) ensureVariableManager().getVariableValue("pIdPrepago");
    }

    /**
     * Sets <code>value</code> for variable pIdPrepago.
     * @param value value to bind as pIdPrepago
     */
    public void setpIdPrepago(Long value) {
        ensureVariableManager().setVariableValue("pIdPrepago", value);
    }

    /**
     * Returns the variable value for varIdCargoPrepago.
     * @return variable value for varIdCargoPrepago
     */
    public Long getvarIdCargoPrepago() {
        return (Long) ensureVariableManager().getVariableValue("varIdCargoPrepago");
    }

    /**
     * Sets <code>value</code> for variable varIdCargoPrepago.
     * @param value value to bind as varIdCargoPrepago
     */
    public void setvarIdCargoPrepago(Long value) {
        ensureVariableManager().setVariableValue("varIdCargoPrepago", value);
    }
}

