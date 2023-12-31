package org.bcie.fenix.common.model.vo;

import java.sql.ResultSet;

import java.sql.Timestamp;
import java.math.BigDecimal;

import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.List;

import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;

import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.am.FenixPrepagoAMImpl;
import org.bcie.fenix.common.model.vo.common.FormularioDetallePenalidadVO;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Oct 07 18:08:02 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FormularioDetallePenalidadVOImpl extends ViewObjectImpl implements FormularioDetallePenalidadVO {
    
    private static ADFLogger logger = null;
    
    /**
     * This is the default constructor (do not remove).
     */
    public FormularioDetallePenalidadVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void limpiarFormularioDetallePenalidadVO(){
        //limpiar rows de FormularioDetallePenalidadVO
        this.clearCache();
    }

    public void llenarCalculoPenalidades(List<Map<String, String>> listaIntereses) {
        logger.log(ADFLogger.WARNING,"Inicio llenarCalculoPenalidades.");
        int id = 1;
        this.executeQuery();
        logger.log(ADFLogger.WARNING,"Parameter value listaIntereses : " + listaIntereses); 
        try 
        {
            for(Map<String, String>  item : listaIntereses)
            {
                Timestamp V_fechaInicio = null;
                Timestamp V_fechaFin = null;
                BigDecimal V_plazo = null;
                BigDecimal V_spread = null;
                String V_fraccionLibor = null;
                BigDecimal V_tasaPrepago = null;
                BigDecimal V_montoPenalidad = null;
                BigDecimal V_intereses = null;
                Long V_idTrePrepagoContrato = null;
                String V_contratoDesembolso = null;
                String V_moneda = null;
                String V_resolucion = null;
                BigDecimal V_montoPrepago = null;
                Timestamp V_proximoPago = null;
                
                V_fechaInicio = item.get("fechaInicio") != null ? Timestamp.valueOf(item.get("fechaInicio").toString()) : null;
                V_fechaFin = item.get("fechaFin") != null ? Timestamp.valueOf(item.get("fechaFin").toString()) : null;
                V_plazo = item.get("plazo") != null ? new BigDecimal(item.get("plazo").toString()) : null;
                V_spread = item.get("spread") != null ? new BigDecimal(item.get("spread").toString()) : null;
                V_fraccionLibor = item.get("fraccionLibor") != null ? (String)item.get("fraccionLibor") : null;
                V_tasaPrepago = item.get("tasaPrepago") != null ? new BigDecimal(item.get("tasaPrepago").toString()) : null;
                V_montoPenalidad = item.get("montoPenalidad") != null ? new BigDecimal(item.get("montoPenalidad").toString()) : null;
                V_intereses = item.get("intereses") != null ? new BigDecimal(item.get("intereses").toString()) : null;
                V_idTrePrepagoContrato = item.get("idTrePrepagoContrato") != null ? Long.valueOf(item.get("idTrePrepagoContrato").toString()) : null;
                V_contratoDesembolso = item.get("contratoDesembolso") != null ? (String)item.get("contratoDesembolso") : null;
                V_moneda = item.get("moneda") != null ? (String)item.get("moneda") : null;
                V_resolucion = item.get("resolucion") != null ? (String)item.get("resolucion") : null;
                V_montoPrepago = item.get("montoPrepago") != null ? new BigDecimal(item.get("montoPrepago").toString()) : null;
                V_proximoPago = item.get("proximoPago") != null ? Timestamp.valueOf(item.get("proximoPago").toString()) : null;
                 
                Row row = this.createRow();
                row.setAttribute(FormularioDetallePenalidadVORowImpl.IDTREPREPAGOCONTRATO,V_idTrePrepagoContrato);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.CONTRATODESEMBOLSO,V_contratoDesembolso);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.MONEDA,V_moneda);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.RESOLUCION,V_resolucion);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.FECHAINICIO,V_fechaInicio);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.FECHAFIN,V_fechaFin);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.PLAZO,V_plazo);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.SPREAD,V_spread);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.FRACCIONLIBOR,V_fraccionLibor);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.TASAPREPAGO,V_tasaPrepago);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.MONTOPENALIDAD,V_montoPenalidad);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.INTERESES,V_intereses);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.MONTOPREPAGO,V_montoPrepago);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.PROXIMOPAGO, V_proximoPago);
                
                logger.log(ADFLogger.WARNING, "Into crearRowFormularioDetallePenalidad.");
                logger.log(ADFLogger.WARNING, "V_idTrePrepagoContrato." + V_idTrePrepagoContrato); 
                logger.log(ADFLogger.WARNING, "V_contratoDesembolso." + V_contratoDesembolso); 
                logger.log(ADFLogger.WARNING, "V_moneda." + V_moneda); 
                logger.log(ADFLogger.WARNING, "V_resolucion." + V_resolucion); 
                logger.log(ADFLogger.WARNING, "V_fechaInicio." + V_fechaInicio); 
                logger.log(ADFLogger.WARNING, "V_fechaFin." + V_fechaFin); 
                logger.log(ADFLogger.WARNING, "V_plazo." + V_plazo); 
                logger.log(ADFLogger.WARNING, "V_spread." + V_spread);  
                logger.log(ADFLogger.WARNING, "V_fraccionLibor." + V_fraccionLibor); 
                logger.log(ADFLogger.WARNING, "V_tasaPrepago." + V_tasaPrepago); 
                logger.log(ADFLogger.WARNING, "V_montoPenalidad." + V_montoPenalidad); 
                logger.log(ADFLogger.WARNING, "V_intereses." + V_intereses); 
                logger.log(ADFLogger.WARNING, "V_montoPrepago." + V_montoPrepago); 
                logger.log(ADFLogger.WARNING, "V_proximoPago." + V_proximoPago); 
                id++;
                this.insertRow(row); 
    
            }
               
        }catch(Exception error){
            logger.log(ADFLogger.ERROR,"Error llenarCalculoPenalidades: " + error.getMessage());
            throw new JboException(error);
        }  

        logger.log(ADFLogger.WARNING,"Fin llenarCalculoPenalidades.");
    }
    
    public void crearRowFormularioDetallePenalidad(Timestamp fechaInicio,Timestamp fechaFin,BigDecimal plazo,
                                                        BigDecimal spread,String fraccionLibor,BigDecimal tasaPrepago,
                                                            BigDecimal montoPenalidad,BigDecimal intereses,
                                                                Long idTrePrepagoContrato,String contratoDesembolso,
                                                                    String moneda,String resolucion,BigDecimal montoPrepago,
                                                                        Timestamp proximoPago){
            
            try{
                logger.log(ADFLogger.WARNING, "Into crearRowFormularioDetallePenalidad.");
                logger.log(ADFLogger.WARNING, "idTrePrepagoContrato." + idTrePrepagoContrato); 
                logger.log(ADFLogger.WARNING, "contratoDesembolso." + contratoDesembolso); 
                logger.log(ADFLogger.WARNING, "moneda." + moneda); 
                logger.log(ADFLogger.WARNING, "resolucion." + resolucion); 
                logger.log(ADFLogger.WARNING, "fechaInicio." + fechaInicio); 
                logger.log(ADFLogger.WARNING, "fechaFin." + fechaFin); 
                logger.log(ADFLogger.WARNING, "plazo." + plazo); 
                logger.log(ADFLogger.WARNING, "spread." + spread);  
                logger.log(ADFLogger.WARNING, "tasaPrepago." + tasaPrepago); 
                logger.log(ADFLogger.WARNING, "fraccionLibor." + fraccionLibor); 
                logger.log(ADFLogger.WARNING, "montoPenalidad." + montoPenalidad); 
                logger.log(ADFLogger.WARNING, "intereses." + intereses); 
                logger.log(ADFLogger.WARNING, "montoPrepago." + montoPrepago); 
                logger.log(ADFLogger.WARNING, "proximoPago." + proximoPago); 
                Row row = this.createRow();
                row.setAttribute(FormularioDetallePenalidadVORowImpl.IDTREPREPAGOCONTRATO,idTrePrepagoContrato);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.CONTRATODESEMBOLSO,contratoDesembolso);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.MONEDA,moneda);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.RESOLUCION,resolucion);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.FECHAINICIO,fechaInicio);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.FECHAFIN,fechaFin);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.PLAZO,plazo);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.SPREAD,spread);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.FRACCIONLIBOR,fraccionLibor);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.TASAPREPAGO,tasaPrepago);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.MONTOPENALIDAD,montoPenalidad);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.INTERESES,intereses);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.MONTOPREPAGO,montoPrepago);
                row.setAttribute(FormularioDetallePenalidadVORowImpl.PROXIMOPAGO, proximoPago);
                //row.setAttribute(FormularioDetallePenalidadVORowImpl.ID, Integer.valueOf(id));
                this.insertRow(row);
                this.setCurrentRow(row); // Asignamos  row como current
                logger.log(ADFLogger.WARNING,"insert row succesfull : " + row.getAttribute("Id"));
            }
            catch(Exception exp){
                logger.log(ADFLogger.WARNING,"Error crearRowFormularioDetallePenalidad : " + exp.getMessage()); 
            }
        }
    
    public List<BigDecimal> obtenerListaPlazo(){
        logger.warning("Dentro obtenerListaPlazo");
        List<BigDecimal> listaPlazo = new ArrayList<BigDecimal>();
        BigDecimal plazo;
        this.setRangeSize(-1);
        for(Row row : this.getAllRowsInRange()){
            plazo = null;
            
            try{
                plazo = (BigDecimal) row.getAttribute("Plazo");
            }catch(Exception e){
                logger.severe("Error al recuperar Plazo en FormularioDetallePenalidadVO :",e);
            }
            
            if(null != plazo){
                logger.warning("plazo : "+plazo);
                listaPlazo.add(plazo);
            }
        }
        logger.warning("Fuera obtenerListaPlazo return : "+listaPlazo);
        return listaPlazo;
    }
    
    
    /**
     * executeQueryForCollection - overridden for custom java data source support.
     */
    @Override
    protected void executeQueryForCollection(Object qc, Object[] params, int noUserParams) {
        super.executeQueryForCollection(qc, params, noUserParams);
    }

    /**
     * hasNextForCollection - overridden for custom java data source support.
     */
    @Override
    protected boolean hasNextForCollection(Object qc) {
        boolean bRet = super.hasNextForCollection(qc);
        return bRet;
    }

    /**
     * createRowFromResultSet - overridden for custom java data source support.
     */
    @Override
    protected ViewRowImpl createRowFromResultSet(Object qc, ResultSet resultSet) {
        ViewRowImpl value = super.createRowFromResultSet(qc, resultSet);
        return value;
    }

    /**
     * getQueryHitCount - overridden for custom java data source support.
     */
    @Override
    public long getQueryHitCount(ViewRowSetImpl viewRowSet) {
        long value = super.getQueryHitCount(viewRowSet);
        return value;
    }

    /**
     * getCappedQueryHitCount - overridden for custom java data source support.
     */
    @Override
    public long getCappedQueryHitCount(ViewRowSetImpl viewRowSet, Row[] masterRows, long oldCap, long cap) {
        long value = super.getCappedQueryHitCount(viewRowSet, masterRows, oldCap, cap);
        return value;
    }
}

