package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.math.RoundingMode;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;

import oracle.jdbc.OracleTypes;

import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.vo.common.FormularioCalculoInteresesVO;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import oracle.jbo.Key;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.am.FenixAdquisicionAMImpl;
import org.bcie.fenix.common.model.am.FenixPrepagoAMImpl;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Oct 07 12:51:20 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FormularioCalculoInteresesVOImpl extends ViewObjectImpl implements FormularioCalculoInteresesVO {
    
    private static ADFLogger logger = null;
    
    
    /**
     * This is the default constructor (do not remove).
     */
    public FormularioCalculoInteresesVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public BigDecimal obtenerIntereses(Long idPrepago,String contratoDesembolso){
        ResultSet rs = null;
        BigDecimal intereses = null;
        int id = 1;
        this.executeQuery();
        logger.log(ADFLogger.WARNING,"Into obtenerIntereses.");
        logger.log(ADFLogger.WARNING,"Parameter value idPrepago : " + idPrepago);
        logger.log(ADFLogger.WARNING,"Parameter value contratoDesembolso : " + contratoDesembolso);
        try {
           
            FenixAMImpl fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
            FenixPrepagoAMImpl fenixPrepagoAMImpl =(FenixPrepagoAMImpl) fenixAMImpl.getFenixPrepagoAM();
                        
            BigDecimal VIdPrepago = new BigDecimal(idPrepago.toString());
            List<Map<String, Object>> listaIntereses = fenixPrepagoAMImpl.ObtenerInteresesPrepago(VIdPrepago);
            
            for(Map<String, Object>  item : listaIntereses)
            {  
                String Vcontractdesembolso = item.get("CONTRACT_DESEMBOLSO") != null ?  (String)item.get("CONTRACT_DESEMBOLSO") : null; 
                BigDecimal Vintereses = item.get("INTERESES") != null ?  (BigDecimal)item.get("INTERESES") : null;
                
                Row row = this.createRow();
                
                if(null != contratoDesembolso && contratoDesembolso.equals(Vcontractdesembolso))
                {
                    intereses = (null != Vintereses ? Vintereses.setScale(2, RoundingMode.CEILING) : null);
                   
                }
                
                id++;
                this.insertRow(row);
            }
            
        }catch(Exception error){
            logger.log(ADFLogger.ERROR,"Exception into obtenerIntereses : " +error.getMessage());
            throw new JboException(error);
        }

        logger.log(ADFLogger.WARNING,"obtenerIntereses return : "+intereses);
        return intereses; 
    }
    
    public BigDecimal obtenerInteresesNew(Long idPrepago,String contratoDesembolso, Integer idTcaTipoResolucion,  Timestamp fechaPrepago){
        ResultSet rs = null;
        BigDecimal intereses = null;
        int id = 1;
        this.executeQuery();
        logger.log(ADFLogger.WARNING,"Into obtenerIntereses.");
        logger.log(ADFLogger.WARNING,"Parameter value idPrepago : " + idPrepago);
        logger.log(ADFLogger.WARNING,"Parameter value idTcaTipoResolucion : " + idTcaTipoResolucion);
        logger.log(ADFLogger.WARNING,"Parameter value fechaPrepago : " + fechaPrepago);
        logger.log(ADFLogger.WARNING,"Parameter value contratoDesembolso : " + contratoDesembolso);
        try {
           
            
          FenixAMImpl fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
          FenixPrepagoAMImpl fenixPrepagoAMImpl =(FenixPrepagoAMImpl) fenixAMImpl.getFenixPrepagoAM();
                      
          BigDecimal VIdPrepago = new BigDecimal(idPrepago.toString());
          List<Map<String, Object>> listaIntereses = fenixPrepagoAMImpl.ObtenerInteresesPrepago(VIdPrepago);
          
          for(Map<String, Object>  item : listaIntereses)
          {  
             
              String Vcontractdesembolso = item.get("CONTRACT_DESEMBOLSO") != null ?  (String)item.get("CONTRACT_DESEMBOLSO") : null; 
              BigDecimal Vintereses = item.get("INTERESES") != null ?  (BigDecimal)item.get("INTERESES") : null;
              BigDecimal Vespagototal = item.get("ES_PAGO_TOTAL") != null ?  (BigDecimal)item.get("ES_PAGO_TOTAL") : null;
              XMLGregorianCalendar VfechadesdeGC = item.get("FECHA_DESDE") != null ?  (XMLGregorianCalendar)item.get("FECHA_DESDE") : null;  
              XMLGregorianCalendar VfechahastaGC = item.get("FECHA_HASTA") != null ?  (XMLGregorianCalendar)item.get("FECHA_HASTA") : null;
              XMLGregorianCalendar VfechaproximopagoGC = item.get("FECHA_PROXIMO_PAGO") != null ?  (XMLGregorianCalendar)item.get("FECHA_PROXIMO_PAGO") : null;
              
              Row row = this.createRow();
              
              Timestamp Vfechadesde = new Timestamp(VfechadesdeGC.toGregorianCalendar().getTimeInMillis());
              Timestamp Vfechahasta = new Timestamp(VfechahastaGC.toGregorianCalendar().getTimeInMillis());
              Timestamp Vfechaproximopago = new Timestamp(VfechaproximopagoGC.toGregorianCalendar().getTimeInMillis());
              
              if(null != contratoDesembolso &&  contratoDesembolso.equals(Vcontractdesembolso))
              {
                  //Se validan condiciones de la RN_11 para mostrar intereses por contrato
                  Boolean mostrarIntereses = Boolean.FALSE;
                  Integer esPagoTotal = Integer.valueOf(Vespagototal.toString());
                  Timestamp proximoPago = Vfechaproximopago;
                  
                  if(fechaPrepago.compareTo(proximoPago) != 0){
                      switch (idTcaTipoResolucion) {
                          case 1: //PRE-10/2008  
                              if(null != esPagoTotal && esPagoTotal == 1){
                                  mostrarIntereses = Boolean.TRUE;
                              }
                              break;
                          case 3: //Otras condiciones
                              mostrarIntereses = Boolean.TRUE;
                              break;
                      }
                  } 
                
                  if(mostrarIntereses)
                  {
                      intereses = (null != Vintereses ? Vintereses.setScale(2,RoundingMode.CEILING): null);
                  }
                     
              }
              
              id++;
              this.insertRow(row);
          }  
            
            
            
        }catch(Exception error){
            logger.log(ADFLogger.ERROR,"Exception into obtenerIntereses : " + error.getMessage());
            throw new JboException(error);
        }

        logger.log(ADFLogger.WARNING,"obtenerIntereses return : "+intereses);
        return intereses; 
    }
    
    public void testLlenarCalculoIntereses() {
        Long idPrepago = 56L;
        Integer idTcaTipoResolucion = 1;
        Timestamp fechaPrepago = new Timestamp(System.currentTimeMillis());
        
        try {
            //2016-10-10 00:00:00.0
            DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            Date date;
            date = (Date) formatter.parse("10-10-2016");
            
            //Descomentar para probar con una fecha especifica
            //fechaPrepago = new Timestamp(date.getTime());
            
            llenarCalculoIntereses(idPrepago, idTcaTipoResolucion, fechaPrepago);
        } catch (ParseException e) {
            logger.log(ADFLogger.WARNING, "", e);
        }
        
        
    }
    
    /**  llenarCalculoIntereses
    *       @param  idPrepago
    *       @since 07/10/2016
    *       @by Jonathan Ruiz
    **/
    public void llenarCalculoIntereses(Long idPrepago, Integer idTcaTipoResolucion, Timestamp fechaPrepago) {
        int id = 1;
        this.executeQuery();
        logger.log(ADFLogger.WARNING,"Into llenarCalculoIntereses nuevo.");
        logger.log(ADFLogger.WARNING,"Parameter value idPrepago : " + idPrepago);
        logger.log(ADFLogger.WARNING,"Parameter value idTcaTipoResolucion : " + idTcaTipoResolucion);
        logger.log(ADFLogger.WARNING,"Parameter value fechaPrepago : " + fechaPrepago);
        try 
        {  
            FenixAMImpl fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
            FenixPrepagoAMImpl fenixPrepagoAMImpl =(FenixPrepagoAMImpl) fenixAMImpl.getFenixPrepagoAM();
            
            BigDecimal VIdPrepago = new BigDecimal(idPrepago.toString());
            List<Map<String, Object>> listaIntereses = fenixPrepagoAMImpl.ObtenerInteresesPrepago(VIdPrepago);
            
            for(Map<String, Object>  item : listaIntereses)
            {  
                String Vcontractdesembolso = item.get("CONTRACT_DESEMBOLSO") != null ?  (String)item.get("CONTRACT_DESEMBOLSO") : null;  
                String Vbase = item.get("BASE") != null ?  (String)item.get("BASE") : null;  
                BigDecimal Vtasa = item.get("TASA") != null ?  (BigDecimal)item.get("TASA") : null;  
                BigDecimal Vmontoprepagar = item.get("MONTO_PREPAGAR") != null ?  (BigDecimal)item.get("MONTO_PREPAGAR") : null;  
                XMLGregorianCalendar VfechadesdeGC = item.get("FECHA_DESDE") != null ?  (XMLGregorianCalendar)item.get("FECHA_DESDE") : null;  
                XMLGregorianCalendar VfechahastaGC = item.get("FECHA_HASTA") != null ?  (XMLGregorianCalendar)item.get("FECHA_HASTA") : null;  
                BigDecimal Vdiascalculados = item.get("DIAS_CALCULADOS") != null ?  (BigDecimal)item.get("DIAS_CALCULADOS") : null;  
                String Vmoneda = item.get("MONEDA") != null ?  (String)item.get("MONEDA") : null;  
                BigDecimal Vintereses = item.get("INTERESES") != null ?  (BigDecimal)item.get("INTERESES") : null; 
                BigDecimal Vidtreprecont = item.get("ID_TRE_PRE_CONT") != null ?  (BigDecimal)item.get("ID_TRE_PRE_CONT") : null; 
                BigDecimal Vespagototal = item.get("ES_PAGO_TOTAL") != null ?  (BigDecimal)item.get("ES_PAGO_TOTAL") : null; 
                XMLGregorianCalendar VfechaproximopagoGC = item.get("FECHA_PROXIMO_PAGO") != null ?  (XMLGregorianCalendar)item.get("FECHA_PROXIMO_PAGO") : null;
                

                Timestamp Vfechadesde = new Timestamp(VfechadesdeGC.toGregorianCalendar().getTimeInMillis());
                Timestamp Vfechahasta = new Timestamp(VfechahastaGC.toGregorianCalendar().getTimeInMillis());
                Timestamp Vfechaproximopago = new Timestamp(VfechaproximopagoGC.toGregorianCalendar().getTimeInMillis());
            
                Row row = this.createRow();
                row.setAttribute(FormularioCalculoInteresesVORowImpl.ID, Integer.valueOf(id));
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.IDTREPREPAGOCONTRATO,Vidtreprecont);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.CONTRATODESEMBOLSO,Vcontractdesembolso);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.BASE,Vbase);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.TASA,Vtasa);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.MONTOPREPAGO,Vmontoprepagar);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.DESDE,Vfechadesde);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.HASTA,Vfechahasta);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.DIAS,Vdiascalculados);
                

                Boolean mostrarIntereses = Boolean.FALSE;
                Integer esPagoTotal = Integer.valueOf(Vespagototal.toString());
                Timestamp proximoPago = Vfechaproximopago;
                
                if(fechaPrepago.compareTo(proximoPago) != 0){
                    switch (idTcaTipoResolucion) {
                        case 1: //PRE-10/2008  
                            if(null != esPagoTotal && esPagoTotal == 1){
                                mostrarIntereses = Boolean.TRUE;
                            }
                            break;
                        case 3: //Otras condiciones
                            mostrarIntereses = Boolean.TRUE;
                            break;
                    }
                } 
                
                logger.log(ADFLogger.WARNING, "mostrarIntereses: " + mostrarIntereses);
                
                if (mostrarIntereses) {
                    if (null == Vintereses) {
                        logger.log(ADFLogger.WARNING, "Los intereses son nulos.");
                    }
                    
                    if (null == Vmoneda) {
                        logger.log(ADFLogger.WARNING, "La moneda es nula.");
                    }
                    
                    row.setAttribute(FormularioCalculoInteresesVORowImpl.INTERESES,Vintereses.setScale(2, RoundingMode.CEILING));
                    
                    row.setAttribute(FormularioCalculoInteresesVORowImpl.MONEDA,Vmoneda);
                }
                
                id++;
                this.insertRow(row);
                logger.log(ADFLogger.WARNING,"row contrato desembolso :" + row.getAttribute("ContratoDesembolso"));
  
            }
               
        }catch(Exception error){
            logger.log(ADFLogger.ERROR,"Exception al llenar ObtenerInteresesPrepago nuevo: " + error.getMessage());
            throw new JboException(error);
        }  
        
        logger.log(ADFLogger.WARNING,"successful method llenarCalculoIntereses.");
    }
    
    public boolean mostrarIntereses(ResultSet rs, Integer idTcaTipoResolucion, Timestamp fechaPrepago) throws SQLException {
        //Se validan condiciones de la RN_11
        Boolean mostrarIntereses = Boolean.FALSE;
        Integer esPagoTotal = rs.getInt("ES_PAGO_TOTAL");
        Timestamp proximoPago = rs.getTimestamp("FECHA_PROXIMO_PAGO");
        
        logger.log(ADFLogger.WARNING,"esPagoTotal: " + esPagoTotal);
        logger.log(ADFLogger.WARNING,"proximoPago: " + proximoPago + " || fechaPrepago: " + fechaPrepago);
        
        if(fechaPrepago.compareTo(proximoPago) != 0){
            switch (idTcaTipoResolucion) {
                case 1: //PRE-10/2008  
                    if(null != esPagoTotal && esPagoTotal == 1){
                        mostrarIntereses = Boolean.TRUE;
                    }
                    break;
                case 3: //Otras condiciones
                    mostrarIntereses = Boolean.TRUE;
                    break;
            }
        }
        
        return mostrarIntereses;
    }
    
    /**  llenarCalculoIntereses"
    *       @param  idPrepago
    *       @since 07/10/2016
    *       @by Gabriel Ni�o Rosales
    **/
    @Deprecated
    public void llenarCalculoIntereses(Long idPrepago) {
        ResultSet rs = null;
        int id = 1;
        this.executeQuery();
        logger.log(ADFLogger.WARNING,"Into llenarCalculoIntereses.");
        logger.log(ADFLogger.WARNING,"Parameter value idPrepago : " + idPrepago);
        
        try {
            
            FenixAMImpl fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule();
            FenixPrepagoAMImpl fenixPrepagoAMImpl =(FenixPrepagoAMImpl) fenixAMImpl.getFenixPrepagoAM();
            
            BigDecimal VIdPrepago = new BigDecimal(idPrepago.toString());
            List<Map<String, Object>> listaIntereses = fenixPrepagoAMImpl.ObtenerInteresesPrepago(VIdPrepago);
    
            for(Map<String, Object>  item : listaIntereses)
            {
                Map<String, Object> map = new HashMap<String, Object>();
                BigDecimal Vidtreprecont = item.get("ID_TRE_PRE_CONT") != null ?  (BigDecimal)item.get("ID_TRE_PRE_CONT") : null;
                String Vcontractdesembolso = item.get("CONTRACT_DESEMBOLSO") != null ?  (String)item.get("CONTRACT_DESEMBOLSO") : null;
                String Vbase = item.get("BASE") != null ?  (String)item.get("BASE") : null;
                BigDecimal Vtasa = item.get("TASA") != null ?  (BigDecimal)item.get("TASA") : null;
                BigDecimal Vmontoprepagar = item.get("MONTO_PREPAGAR") != null ?  (BigDecimal)item.get("MONTO_PREPAGAR") : null;
                XMLGregorianCalendar Vfechadesde = item.get("FECHA_DESDE") != null ?  (XMLGregorianCalendar)item.get("FECHA_DESDE") : null;
                XMLGregorianCalendar Vfechahasta = item.get("FECHA_HASTA") != null ?  (XMLGregorianCalendar)item.get("FECHA_HASTA") : null;
                BigDecimal Vdiascalculados = item.get("DIAS_CALCULADOS") != null ?  (BigDecimal)item.get("DIAS_CALCULADOS") : null;
                BigDecimal Vintereses = item.get("INTERESES") != null ?  (BigDecimal)item.get("INTERESES") : null;
                String Vmoneda = item.get("MONEDA") != null ?  (String)item.get("MONEDA") : null;
                
                Row row = this.createRow();
                row.setAttribute(FormularioCalculoInteresesVORowImpl.ID, Integer.valueOf(id));
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.IDTREPREPAGOCONTRATO,Vidtreprecont);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.CONTRATODESEMBOLSO,Vcontractdesembolso);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.BASE,Vbase);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.TASA,Vtasa);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.MONTOPREPAGO,Vmontoprepagar);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.DESDE,Vfechadesde);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.HASTA,Vfechahasta);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.DIAS,Vdiascalculados);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.INTERESES,Vintereses);
                
                row.setAttribute(FormularioCalculoInteresesVORowImpl.MONEDA,Vmoneda);
                
                id++;
                this.insertRow(row); 
                logger.log(ADFLogger.WARNING,"row contrato desembolso :" + row.getAttribute("ContratoDesembolso"));
            }
            
        }catch(Exception error){
            logger.log(ADFLogger.ERROR,"Exception al llenar ObtenerInteresesPrepago  : " + error.getMessage());
            throw new JboException(error);
        }
        
        logger.log(ADFLogger.WARNING,"successful method llenarCalculoIntereses.");
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

