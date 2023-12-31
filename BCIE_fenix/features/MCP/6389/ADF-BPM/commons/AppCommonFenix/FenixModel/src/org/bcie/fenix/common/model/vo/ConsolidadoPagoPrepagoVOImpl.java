package org.bcie.fenix.common.model.vo;

import com.bcie.xmlns.operacionservice.Operacion12BndQSService;
import com.bcie.xmlns.operacionservice.Operacion12Port;

import com.bcie.xmlns.prepagoservice.PlanAmortizacionPT;

import com.bcie.xmlns.prepagoservice.PlanAmortizacionPTSOAP12BndQSService;

import com.bcie.xmlns.prepagoservice.PrepagoPT;
import com.bcie.xmlns.prepagoservice.PrepagoPT12BndQSService;

import java.math.BigDecimal;

import java.util.Date;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.atributobo.NivelType;
import org.bcie.commonbo.MontoType;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.common.ConsolidadoPagoPrepagoVO;
import org.bcie.operacionbo.Operacion;
import org.bcie.operacionmo.ConsultarOperacionByIdOperacionRequestType;
import org.bcie.operacionmo.ConsultarOperacionResponseType;
import org.bcie.prepagobo.CargoPenalidadType;
import org.bcie.prepagobo.PrepagoType;
import org.bcie.prepagomo.ReportePrepagoRequestType;
import org.bcie.prepagomo.ReportePrepagoResponseType;
import org.bcie.prepagomo.ConsultarPrepagoRequestType;
import org.bcie.prepagomo.ConsultarPrepagoResponseType;
import org.bcie.prepagomo.InformePrepagoRequestType;
import org.bcie.prepagomo.InformePrepagoResponseType;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Oct 03 17:56:22 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ConsolidadoPagoPrepagoVOImpl extends ViewObjectImpl implements ConsolidadoPagoPrepagoVO {
    /**
     * This is the default constructor (do not remove).
     */
    
    public static ADFLogger logger = null;
    public static final String MONTO_PREPAGO="Monto prepago";
    public static final String INTERESES="Intereses";
    public static final String PENALIDAD="Penalidad";
    public static final String CARGO_TRAMITE="Cargo por tr�mite";
    public static final String OTRO_CARGO="Otros cargos";
    
    public ConsolidadoPagoPrepagoVOImpl() {
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void crearRowConsolidado(){
      Row  rowConsolidadoPago = this.createRow();
        rowConsolidadoPago.setAttribute(ConsolidadoPagoPrepagoVORowImpl.CONCEPTO, MONTO_PREPAGO);
        this.insertRow(rowConsolidadoPago);
        this.setCurrentRow(rowConsolidadoPago);
    }
        /**
    ???? * Se crea metodo para crear rows de Consolidado de Pago
    ???? * @param 
    ???? * @since 05/10/2016
    ???? * @by Jos� Hern�ndez Cruz
    ???? */
    public void crearRowConsolidadoPago(Map<String, Object> valoresConsolidado){
        logger.log(ADFLogger.WARNING, "INTO crearRowConsolidadoPago.");
        //Variables para insertar los montos de los distintos CONCEPTOS
        String concepto = null;
        BigDecimal montoMonedaDolar = null;
        BigDecimal montoMonedaLocal = null;
//        Integer tipoMoneda = null;
//        BigDecimal montoMonedaDolares = null;
//        Integer TipoMonedaDolares = null;
//        String monedaLocal =null;
//        String monedaDolar =null;
        
        //Variables para los montos de los conceptos del consolidado
        BigDecimal montoDolarPrincipal = new BigDecimal(0);
        BigDecimal montoLocalPrincipal = new BigDecimal(0);
        BigDecimal montoDolarIntereses = new BigDecimal(0);
        BigDecimal montoLocalIntereses = new BigDecimal(0);
        BigDecimal montoDolarPenalidad = new BigDecimal(0);
        BigDecimal montoLocalPenalidad = new BigDecimal(0);
        BigDecimal montoDolarTramite = new BigDecimal(0);
        BigDecimal montoLocalTramite = new BigDecimal(0);
        BigDecimal montoDolarCargos = new BigDecimal(0);
        BigDecimal montoLocalCargos = new BigDecimal(0);
        
        Integer idTcaTipoResolucion = null;
        
        montoDolarPrincipal = (BigDecimal)valoresConsolidado.get("montoDolarPrincipal");
        montoLocalPrincipal = (BigDecimal)valoresConsolidado.get("montoLocalPrincipal");
        montoDolarIntereses = (BigDecimal)valoresConsolidado.get("montoDolarIntereses");
        montoLocalIntereses = (BigDecimal)valoresConsolidado.get("montoLocalIntereses");
        montoLocalPenalidad = (BigDecimal)valoresConsolidado.get("montoLocalPenalidad");
        montoDolarPenalidad = (BigDecimal)valoresConsolidado.get("montoDolarPenalidad");
        montoLocalTramite = (BigDecimal)valoresConsolidado.get("montoLocalTramite");
        montoDolarTramite = (BigDecimal)valoresConsolidado.get("montoDolarTramite");
        montoLocalCargos = (BigDecimal)valoresConsolidado.get("montoLocalCargos");
        montoDolarCargos = (BigDecimal)valoresConsolidado.get("montoDolarCargos");
        
        idTcaTipoResolucion = (Integer)valoresConsolidado.get("idTcaTipoResolucion");
        logger.log(ADFLogger.WARNING, "Valor de la resolucion :" + idTcaTipoResolucion);
        
        try{
        for(int i=1; i<6; i++){
            logger.log(ADFLogger.WARNING, "Entra For." + i);
            Row rowConsolidadoPago = null;
            concepto = null;
            montoMonedaDolar = null;
            montoMonedaLocal = null;
            switch(i){
            case 1: 
                rowConsolidadoPago = this.createRow();
                concepto =  FenixModelConstants.OTRO_CARGO;
                montoMonedaDolar = montoDolarCargos;
                montoMonedaLocal = montoLocalCargos;
                logger.log(ADFLogger.WARNING, "OTRO_CARGO : monto MonedaLocal :" + montoMonedaLocal + ".montoMonedaDolar :" + montoMonedaDolar);
            break;
            case 2:
                if(idTcaTipoResolucion != 3){
                    rowConsolidadoPago = this.createRow();
                    concepto =  FenixModelConstants.CARGO_TRAMITE;
                    montoMonedaDolar = montoDolarTramite;
                    montoMonedaLocal = montoLocalTramite;
                    logger.log(ADFLogger.WARNING, "CARGO_TRAMITE : monto MonedaLocal :" + montoMonedaLocal + ".montoMonedaDolar :" + montoMonedaDolar);
                }else{
                    logger.log(ADFLogger.WARNING, "La resolucion es Otras Condiciones");
                }
            break;
            case 3:
                if(idTcaTipoResolucion != 3){
                    rowConsolidadoPago = this.createRow();
                    concepto =  FenixModelConstants.PENALIDAD;
                    montoMonedaDolar = montoDolarPenalidad;
                    montoMonedaLocal = montoLocalPenalidad;
                    logger.log(ADFLogger.WARNING, "PENALIDAD : monto MonedaLocal :" + montoMonedaLocal + ".montoMonedaDolar :" + montoMonedaDolar);
                }else{
                    logger.log(ADFLogger.WARNING, "La resolucion es Otras Condiciones");
                }
            break;
            case 4:
                if(idTcaTipoResolucion == 1){
                    rowConsolidadoPago = this.createRow();
                    concepto =  FenixModelConstants.INTERESES;
                    montoMonedaDolar = montoDolarIntereses;
                    montoMonedaLocal = montoLocalIntereses;
                    logger.log(ADFLogger.WARNING, "INTERESES : monto MonedaLocal :" + montoMonedaLocal + ".montoMonedaDolar :" + montoMonedaDolar);
                }else{
                    logger.log(ADFLogger.WARNING, "La resolucion es diferente a PRE-10/2008");
                }
            break;
            case 5:
                rowConsolidadoPago = this.createRow();
                logger.log(ADFLogger.WARNING, "Entra case 1." + i);
                concepto =  FenixModelConstants.MONTO_PREPAGO;
                montoMonedaDolar = montoDolarPrincipal;
                montoMonedaLocal = montoLocalPrincipal;
                logger.log(ADFLogger.WARNING, "MONTO_PREPAGO : monto MonedaLocal :" + montoMonedaLocal + ".montoMonedaDolar :" + montoMonedaDolar);
            break;
            default:
            break;
            }
            
            if(concepto != null & montoMonedaDolar != null && montoMonedaLocal != null){
            
                logger.log(ADFLogger.WARNING, "Entra For." + i + "."+concepto+"."+montoMonedaDolar+"."+montoMonedaLocal);
            rowConsolidadoPago.setAttribute(ConsolidadoPagoPrepagoVORowImpl.CONCEPTO, concepto);
            
            rowConsolidadoPago.setAttribute(ConsolidadoPagoPrepagoVORowImpl.MONTOMONEDALOCAL, montoMonedaLocal);
          
            rowConsolidadoPago.setAttribute(ConsolidadoPagoPrepagoVORowImpl.MONTODOLAR, montoMonedaDolar);
            
                this.insertRow(rowConsolidadoPago);
                this.setCurrentRow(rowConsolidadoPago);
                
            }else{
                logger.log(ADFLogger.WARNING, "Valores vacios.");
            
            }
            
                
        }
        }catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error en crearRowConsolidadoPago." + e.getClass() + "." + e.getMessage());
            }
    }
    
    public Map consultarConsolidadoPagoByIdPrepago(Long idPrepago, Integer idTcaTipoResolucion){
            logger.log(ADFLogger.WARNING, "Dentro consultarConsolidadoPagoByIdPrepago.");
            logger.log(ADFLogger.WARNING, "idPrepago :" + idPrepago);
            logger.log(ADFLogger.WARNING, "idTcaTipoResolucion:" + idTcaTipoResolucion);
            Map<String,Object> valoresConsolidado = new HashMap<String, Object>();
            String descripcionMonedaPrincipal = null;
            PrepagoPT12BndQSService prepagoPT12BndQSService = null;
            String wsdl = null;
            FenixAMImpl fenixAM = null;
            try{
                fenixAM = (FenixAMImpl)this.getRootApplicationModule();
                
                wsdl = fenixAM.getWsdl(IWsdlLocation.REPORTE_PREPAGO);
                prepagoPT12BndQSService = IWsdlLocation.Service.getInstance(PrepagoPT12BndQSService.class, wsdl);
                
                PrepagoPT prepagoPT = prepagoPT12BndQSService.getPrepagoPT12BndQSPort();
                InformePrepagoRequestType request = new InformePrepagoRequestType();
                request.setIdPrepago(idPrepago);
                
                Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_CONSOLIDADO_PREPAGO);
                InformePrepagoResponseType response = prepagoPT.consolidadoPrepago(request);
                ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_CONSOLIDADO_PREPAGO, horaInicio);
                valoresConsolidado = this.mapConsultarConsolidadoPagoByIdPrepago(response.getReporte().getConsolidado(), idTcaTipoResolucion);           
                
            } catch(Exception e){
                logger.log(ADFLogger.ERROR, "Error en consultarConsolidadoPagoByIdPrepago :  ",e);
                JboException ex = new JboException(e);
                throw ex;
            }
            return valoresConsolidado;
        }
    
    public Map mapConsultarConsolidadoPagoByIdPrepago(List<CargoPenalidadType> consolidados, Integer idTcaTipoResolucion){
        logger.warning("INTO mapConsultarConsolidadoPagoByIdPrepago.");
        logger.warning("Valor de la resolucion :" + idTcaTipoResolucion);
        Map<String,Object> valoresConsolidado = new HashMap<String, Object>();
        String descripcionMonedaPrincipal = null;
        try{
            logger.warning("INTO mapConsultarConsolidadoPagoByIdPrepago");
            
            Row rowReporte = null;
            String concepto = null;
            BigDecimal montoDolarPrincipal = new BigDecimal(0);
            BigDecimal montoLocalPrincipal = new BigDecimal(0);
            BigDecimal montoDolarIntereses = new BigDecimal(0);
            BigDecimal montoLocalIntereses = new BigDecimal(0);
            BigDecimal montoDolarPenalidad = new BigDecimal(0);
            BigDecimal montoLocalPenalidad = new BigDecimal(0);
            BigDecimal montoDolarTramite = new BigDecimal(0);
            BigDecimal montoLocalTramite = new BigDecimal(0);
            BigDecimal montoDolarCargos = new BigDecimal(0);
            BigDecimal montoLocalCargos = new BigDecimal(0);
            BigDecimal montoDolarConFuentes = new BigDecimal(0);
            BigDecimal montoDolarSinFuentes = new BigDecimal(0);
            String monedaConFuentes = null;
            Boolean isMontoMayor = Boolean.FALSE;
            
            for(CargoPenalidadType consolidado : consolidados){
                
                for(MontoType monto : consolidado.getMonto()){
                    concepto = monto.getTipo().getDescripcion();
                    logger.warning("Concepto :::" + concepto);
                    switch(concepto){
                    case "Monto Prepago(principal)":
                        logger.warning("INTO Monto Prepago(principal).");
                        if(monto.getMoneda().getDescripcionCorta().compareTo(FenixModelConstants.MONEDA_DESCRIPCION_USD) == 0){
                            montoDolarPrincipal = monto.getImporte();
                        }else{
                            montoLocalPrincipal = monto.getImporte();
                            descripcionMonedaPrincipal = monto.getMoneda().getDescripcionCorta();
                        }
                    break;
                    case "Intereses":
                        logger.warning("INTO Intereses.");
                        if(monto.getMoneda().getDescripcionCorta().compareTo(FenixModelConstants.MONEDA_DESCRIPCION_USD) == 0){
                            montoDolarIntereses = monto.getImporte();
                        }else{
                            montoLocalIntereses = monto.getImporte();
                        }
                    break;
                    case "Penalidad":
                        logger.warning("INTO Penalidad.");
                        if(monto.getMoneda().getDescripcionCorta().compareTo(FenixModelConstants.MONEDA_DESCRIPCION_USD) == 0){
                            montoDolarPenalidad = monto.getImporte();
                        }else{
                            montoLocalPenalidad = monto.getImporte();
                        }
                    break;
                    case "Cargo por tramite":
                        logger.warning("INTO Cargo por tramite.");
                        if(monto.getMoneda().getDescripcionCorta().compareTo(FenixModelConstants.MONEDA_DESCRIPCION_USD) == 0){
                            montoDolarTramite = monto.getImporte();
                        }else{
                            montoLocalTramite = monto.getImporte();
                        }
                    break;
                    case "Otros Cargos":
                        logger.warning("INTO Otros Cargos.");
                        if(monto.getMoneda().getDescripcionCorta().compareTo(FenixModelConstants.MONEDA_DESCRIPCION_USD) == 0){
                            montoDolarCargos = monto.getImporte();
                        }else{
                            montoLocalCargos = monto.getImporte();
                        }
                    break;
                    case "Monto con fuente":
                        logger.warning("Entra en Con fuentes.");
                        if(null != monto.getImporte()){
                            montoDolarConFuentes = monto.getImporte();
                            monedaConFuentes = (String)monto.getMoneda().getDescripcionCorta();
                        }else{
                            logger.warning("no se encontraron montos con fuentes");
                        }
                    break;
                    case "Monto sin fuente":
                        logger.warning("Entra en Sin fuentes.");
                        if(null != monto.getImporte()){
                            montoDolarSinFuentes = monto.getImporte();
                        }else{
                            logger.warning("no se encontraron montos sin fuentes");
                        }
                    break;
                    default:
                    break;
                    }
                }
            }
            valoresConsolidado.put("montoDolarPrincipal", montoDolarPrincipal);
            valoresConsolidado.put("montoLocalPrincipal", montoLocalPrincipal);
            valoresConsolidado.put("montoDolarIntereses", montoDolarIntereses);
            valoresConsolidado.put("montoLocalIntereses", montoLocalIntereses);
            valoresConsolidado.put("montoLocalPenalidad", montoLocalPenalidad);
            valoresConsolidado.put("montoDolarPenalidad", montoDolarPenalidad);
            valoresConsolidado.put("montoLocalTramite", montoLocalTramite);
            valoresConsolidado.put("montoDolarTramite", montoDolarTramite);
            valoresConsolidado.put("montoLocalCargos", montoLocalCargos);
            valoresConsolidado.put("montoDolarCargos", montoDolarCargos);
            
            valoresConsolidado.put("descripcionMonedaPrincipal", descripcionMonedaPrincipal);
            valoresConsolidado.put("idTcaTipoResolucion", idTcaTipoResolucion);
            valoresConsolidado.put("montoDolarConFuentes", montoDolarConFuentes);
            valoresConsolidado.put("monedaConFuentes", monedaConFuentes);
            crearRowConsolidadoPago(valoresConsolidado);
            isMontoMayor = validaFondos(montoDolarConFuentes, montoDolarSinFuentes);
            valoresConsolidado.put("isMontoMayor", isMontoMayor);
        }catch(Exception e){
            logger.warning("Error en mapConsultarConsolidadoPagoByIdPrepago " + e.getClass() + ":" + e.getMessage());
            JboException ex = new JboException(e);
            throw ex;
        }
        return valoresConsolidado;
    }
    
    public Boolean validaFondos(BigDecimal montoDolarConFuentes, BigDecimal montoDolarSinFuentes){
            logger.log(ADFLogger.WARNING, "Entra en validaFondos.");
            Map<String, Boolean> validaCalculos = new HashMap<>();
            Boolean isValidaMontoMayor = Boolean.FALSE;
            try{
                if (null != montoDolarConFuentes && null != montoDolarSinFuentes) {
                    if (montoDolarSinFuentes.compareTo(montoDolarConFuentes) == 1) {
                        logger.warning("Monto fuentes ordinarios es mayor");
                        isValidaMontoMayor = Boolean.TRUE;
                    } else {
                        if (montoDolarSinFuentes.compareTo(montoDolarConFuentes) == -1) {
                            logger.warning("Monto fuentes ordinarios es menor");
                            isValidaMontoMayor = Boolean.FALSE;
                        }
                    }
                }else{
                    logger.warning("Un monto es nulo y no se puede realizar la validacion.");
                }
            }catch(Exception e){
                logger.log(ADFLogger.WARNING, "Error en validaFondos.", e);
            }
            return isValidaMontoMayor;
        }
    
}

