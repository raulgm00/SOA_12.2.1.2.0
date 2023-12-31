package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.sql.ResultSet;

import java.sql.Timestamp;

import java.util.Date;

import java.util.List;
import java.util.Map;

import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;
import oracle.jbo.server.ViewRowSetImpl;

import org.bcie.commonbo.MontoType;
import org.bcie.desembolso.DesembolsoPT;
import org.bcie.desembolso.DesembolsoPTSOAP12BindingQSService;
import org.bcie.desembolsomo.ConsultarSaldoRequestType;
import org.bcie.desembolsomo.ConsultarSaldoResponseType;
import org.bcie.desembolsomo.EnvioGastoBPELRequestType;
import org.bcie.desembolsomo.EnvioGastoBPELResponseType;
import org.bcie.desembolsomo.PropagarContratoDesembolsoRequestType;
import org.bcie.desembolsomo.PropagarContratoDesembolsoResponseType;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.common.FT05VO;
import org.bcie.gestioncobro.RecuperacionPTSOAP12BndQSService;
import org.bcie.resultbo.Resultado;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Nov 23 14:45:52 VET 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class FT05VOImpl extends ViewObjectImpl implements FT05VO {
    
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(FT05VOImpl.class);
    private static final String DESCRIPCION_CORTA_SALDO = "SALDO";
    private static final String WS_ERROR_VALUE= "ERROR";
    
    
    /**
     * This is the default constructor (do not remove).
     */
    public FT05VOImpl() {
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
    
    public void insertarRowValidarFT(Long idContratoDesembolso, String contratoFelxcube, Timestamp fechaEfectiva, String cuentaCustomer, String cuentaPuente){
        LOGGER.entering(this.getClass().getName(), "insertarRowValidarFT");
        LOGGER.warning("Ingresa metodo insertarRowValidarFT en la VO");
        Row newRow = null;
        newRow = this.getRow(new Key(new Object[] { idContratoDesembolso }));
        
        if (newRow == null) {
            LOGGER.warning("Se inserta un nuevo row.");
            Row row = this.createRow();
            row.setAttribute("idContratoDesembolso", idContratoDesembolso);
            row.setAttribute("BHQTransferencia", contratoFelxcube); 
            row.setAttribute("FechaEfectiva", fechaEfectiva);
            row.setAttribute("CuentaCustomer", cuentaCustomer);
            row.setAttribute("CuentaPuente", cuentaPuente);
            this.insertRow(row);
            this.setCurrentRow(row);
        }
        else{
            RowSetIterator rowsFT05Vo = createRowSetIterator(null);
            rowsFT05Vo.reset();

            try {
                while (rowsFT05Vo.hasNext()) {
                    FT05VORowImpl rowL = (FT05VORowImpl) rowsFT05Vo.next();
                    LOGGER.warning("idContratoDesembolso: " + rowL.getAttribute("idContratoDesembolso"));
                    if (Long.compare( (Long)rowL.getAttribute("idContratoDesembolso"), idContratoDesembolso) == 0) {
                        LOGGER.warning("Se actualizan valores del row");
                        rowL.setAttribute("BHQTransferencia", contratoFelxcube); 
                        rowL.setAttribute("FechaEfectiva", fechaEfectiva);
                        rowL.setAttribute("CuentaCustomer", cuentaCustomer);
                        rowL.setAttribute("CuentaPuente", cuentaPuente);
                        this.setCurrentRow(rowL);
                    }
                    else{
                        LOGGER.warning("No se encontro algun registro con el idContratoDesembolso: " + idContratoDesembolso);
                    }
                }
                rowsFT05Vo.closeRowSetIterator();
            } catch (Exception e) {
                LOGGER.warning("Error insertarRowValidarFT.", e);
            }
        }
        
        LOGGER.warning("Finaliza metodo insertarRowValidarFT en la VO");
        LOGGER.exiting(this.getClass().getName(), "insertarRowValidarFT");
    }
    
    public void validarFT(Long idContratoDesembolso, String cuentaCustomer, String cuentaPuente){
        LOGGER.entering(this.getClass().getName(), "validarFT");
        LOGGER.warning("Ingresa metodo validarFT en la VO");
        EnvioGastoBPELRequestType request = null;
        EnvioGastoBPELResponseType response = null;
        FenixAMImpl fenixAM = null;
        //this.clearCache();
        Row newRow = this.createRow();
        String transferencia = null;
        Timestamp fechaEfectiva = null;
        
        try{
            Resultado result = null;
            fenixAM = (FenixAMImpl)this.getRootApplicationModule();
            
            //Service declaration
            String wsdl = fenixAM.getWsdl(IWsdlLocation.DESEMBOLSO);
            DesembolsoPTSOAP12BindingQSService service = 
                IWsdlLocation.Service.getInstance(DesembolsoPTSOAP12BindingQSService.class, wsdl);
            DesembolsoPT port = service.getDesembolsoPTSOAP12BindingQSPort();
            
            //Request declaration
            request = new EnvioGastoBPELRequestType();
            request.setIdDesembolso(idContratoDesembolso);
            
            //Service execution             
            Date horaInicioService =
                ModelUtils.logStartWS(LOGGER, request, FenixModelConstants.WSC_PROPAGAR_CONTRATO_DESEMBOLSO);
            response = port.liquidarContrato(request);
            ModelUtils.logEndWS(LOGGER, response, FenixModelConstants.WSC_PROPAGAR_CONTRATO_DESEMBOLSO, horaInicioService);
            result = response.getResultado();
            
            if(result.getResult() == null || 
               (result.getResult()!=null && result.getResult().value().equalsIgnoreCase(WS_ERROR_VALUE))){
                LOGGER.log(ADFLogger.WARNING, "Error al validar FT05");                
                throw new Exception(result.getMessage());
            }else{
                //newRow.setAttribute("idContratoDesembolso", idContratoDesembolso);
                if(null != response.getTransferenciaFT05().getIdFacturador()){
                    LOGGER.warning("BHQTransferencia: "+ response.getTransferenciaFT05().getIdFacturador());  
                    transferencia = response.getTransferenciaFT05().getIdFacturador();
                    //newRow.setAttribute("BHQTransferencia", transferencia);                    
                }else{
                    LOGGER.warning("La transferencia es nula.");
                }
                if(null != response.getTransferenciaFT05().getFechaEfectiva()){
                    LOGGER.warning("FechaEfectiva: "+ response.getTransferenciaFT05().getFechaEfectiva());
                    fechaEfectiva = convertXmlGregorianCalendarToTimestamp(response.getTransferenciaFT05().getFechaEfectiva());
                    //newRow.setAttribute("FechaEfectiva", response.getTransferenciaFT05().getFechaEfectiva());
                }else{
                    LOGGER.warning("La fecha efectiva es nula.");
                }
                insertarRowValidarFT(idContratoDesembolso, transferencia, fechaEfectiva, cuentaCustomer, cuentaPuente);
            }
            
            //this.insertRowAtRangeIndex(0, newRow);
            //this.setCurrentRow(newRow);
        }
        catch(Exception e) 
        {
             LOGGER.log(ADFLogger.WARNING, "Excepcion al validar FT05"+e);
             setCurrentRow(newRow);
             JboException ex = new JboException(e);
             throw ex;            
        }
        LOGGER.warning("Finaliza metodo validarFT en la VO");
        LOGGER.exiting(this.getClass().getName(), "validarFT");
    }
    
    public boolean validarSaldoContratosDesembolso(Map contratosDesembolso){
            LOGGER.entering(this.getClass().getName(), "validarSaldoContratosDesembolso");
            Boolean respuestaValidarSaldo = Boolean.TRUE;
            ConsultarSaldoRequestType request = null;
            ConsultarSaldoResponseType response = null;
            FenixAMImpl fenixAM = null;        
            
            try
            {
                Resultado result = null;
                fenixAM = (FenixAMImpl)this.getRootApplicationModule();
                
                //Service declaration
                String wsdl = fenixAM.getWsdl(IWsdlLocation.DESEMBOLSO);
                DesembolsoPTSOAP12BindingQSService service = 
                IWsdlLocation.Service.getInstance(DesembolsoPTSOAP12BindingQSService.class, wsdl);
                DesembolsoPT port = service.getDesembolsoPTSOAP12BindingQSPort();
                
                for(Object idContratoDesembolso : contratosDesembolso.keySet())
                {
                    //Request declaration
                    request = new ConsultarSaldoRequestType();
                    request.setIdDesembolso((Long) idContratoDesembolso);
                    LOGGER.warning("Consulta de saldo de idContratoDesembolso: "+request.getIdDesembolso());
                    //Service execution             
                    Date horaInicioService =
                        ModelUtils.logStartWS(LOGGER, request, FenixModelConstants.WSC_CONSULTAR_SALDO_CONTRATO_DESEMBOLSO);
                    response = port.consultarSaldo(request);
                    ModelUtils.logEndWS(LOGGER, response, FenixModelConstants.WSC_CONSULTAR_SALDO_CONTRATO_DESEMBOLSO, horaInicioService);
                    result = response.getResultado();
                    
                    if(result.getResult() == null || 
                       (result.getResult()!=null && result.getResult().value().equalsIgnoreCase(WS_ERROR_VALUE))){
                        LOGGER.log(ADFLogger.WARNING, "Error al consultar saldo contrato desembolso");                
                        respuestaValidarSaldo = Boolean.FALSE;
                    }else{
                        List<MontoType> listaMontosContrato = response.getContratoDesembolso().getMonto();
                        BigDecimal montoTotalDesembolsado = new BigDecimal(0);
                        for (MontoType monto: listaMontosContrato) {
                            if(monto.getTipo().getDescripcionCorta().equalsIgnoreCase(DESCRIPCION_CORTA_SALDO))
                            {
                                if(monto.getImporte() != null){
                                    if(monto.getImporte().compareTo(BigDecimal.ZERO) != 0)
                                        respuestaValidarSaldo = Boolean.FALSE;
                                }
                            }                        
                        }
                    }            
                }
            }
            catch(Exception e) 
            {
                LOGGER.log(ADFLogger.WARNING, "Excepcion al validar Saldo contratos desembolso"+e);
                respuestaValidarSaldo = Boolean.FALSE;
            }
            LOGGER.log(ADFLogger.WARNING, "Valor a retornar respuestaValidarSaldo: " + respuestaValidarSaldo);
            LOGGER.exiting(this.getClass().getName(), "validarSaldoContratosDesembolso");
            return respuestaValidarSaldo;
        }
    
    /**
     * Convierte de XMLGregorianCalendar a Timestamp
     * 
     * @param xgc XMLGregorianCalendar
     * @return timestamp Timestamp
     */
    private Timestamp convertXmlGregorianCalendarToTimestamp(XMLGregorianCalendar xgc) {
        Timestamp timestamp = null;
        if (xgc != null) {
            if (xgc.toGregorianCalendar() != null) {
                try {
                    timestamp = new Timestamp(xgc.toGregorianCalendar().getTimeInMillis());
                } catch (Exception e) {
                    LOGGER.log(ADFLogger.ERROR, "timestamp " + e.getMessage());
                }
            } else {
                LOGGER.log(ADFLogger.WARNING, "xgc.toGregorianCalendar() NULL");
            }
        } else {
            LOGGER.log(ADFLogger.WARNING, "xgc NULL");
        }
        return timestamp;
    }
}

