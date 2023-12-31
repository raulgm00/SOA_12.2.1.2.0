package org.bcie.fenix.common.model.vo;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.catalogobo.Catalogo;
import org.bcie.commonbo.MontoType;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.am.FenixGestorDesembolsosAMImpl;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.common.TimeLineFechaUltimoDesembolsoVO;
import org.bcie.lineacreditobo.InformacionAdicional;
import org.bcie.lineacreditomo.ConsultarLineaCreditoBPELResponseType;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Feb 14 19:17:52 CST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TimeLineFechaUltimoDesembolsoVOImpl extends ViewObjectImpl implements TimeLineFechaUltimoDesembolsoVO {
    /**
     * This is the default constructor (do not remove).
     */
    
    private static ADFLogger logger = null;
    public TimeLineFechaUltimoDesembolsoVOImpl() {
        if(null == logger){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    /**
     *  Metodo que valida que el monto disponible de la linea se encuentre en cero
     * @param idOperacion
     * @return
     */
    public Row obtenerFechaEfectivaUltimoDesembolso(Long idOperacion){
        logger.warning("Entra en obtenerFechaEfectivaUltimoDesembolso.");
        Boolean esUltimoDesembolso = Boolean.FALSE;
        Long idLinea = null;
        Row row = null;
        try{
            this.setpIdOperacion(idOperacion);
            this.executeQuery();
            
            row = this.first();
            if(row != null){
                idLinea = (Long)row.getAttribute("IdLinea");
                row.setAttribute("Descripcion", "Fecha efectiva del �ltimo desembolso");
                logger.warning("idLinea : " + idLinea);
                logger.warning("Descripcion : " + row.getAttribute("Descripcion"));
                logger.warning("FechaEfectiva maxima : " + row.getAttribute("Fecha"));
                
                esUltimoDesembolso = consultaMontoDesembolsar(idLinea);
                
                if(esUltimoDesembolso){
                    logger.warning("Se retorna el row con la fecha de ultimo desembolso para mostrar en el time line");
                }else{
                    logger.warning("Se inicia el row a null y se retorna.");
                    row = null;
                }
            }else{
                logger.warning("No se recuperaron registros de contratos con fecha efectiva.");
            }
            
        }catch(Exception e){
            logger.warning("Error en obtenerFechaEfectivaUltimoDesembolso.", e);
        }
        return row;
    }
    
    private Boolean consultaMontoDesembolsar(Long idLinea){
        logger.warning("Entra en consultaMontoDesembolsar.");
        FenixAMImpl fenixAMImpl = null;
        String wsdl = null;
        com.bcie.xmlns.lineacreditoservice.LineaCredito lineaCredito12BndQSService = null;
        com.bcie.xmlns.lineacreditoservice.LineaCreditoPT lineaCreditoPT = null;
        org.bcie.lineacreditomo.ConsultarLineaCreditoBPELRequestType request = null;
        ConsultarLineaCreditoBPELResponseType response = null;
        
        Boolean esUltimoDesembolso = Boolean.FALSE;
        String descripcionMonto = null;
        BigDecimal montoDisponibleLinea = null;
        try{
            fenixAMImpl = (FenixAMImpl) this.getRootApplicationModule(); 
            wsdl = fenixAMImpl.getWsdl(IWsdlLocation.LINEA_CREDITO);
            
            lineaCredito12BndQSService =
                      IWsdlLocation.Service.getInstance(com.bcie.xmlns.lineacreditoservice.LineaCredito.class, wsdl);
            lineaCreditoPT = lineaCredito12BndQSService.getLineaCredito12Bnd();
            
            request = new org.bcie.lineacreditomo.ConsultarLineaCreditoBPELRequestType();
            request.setIdLineaCredito(idLinea);

            Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_CONSULTAR_LINEA_CREDITO);
            response = lineaCreditoPT.consultarLineaCreditoById(request);
            ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_CONSULTAR_LINEA_CREDITO, horaInicio);
            
            if(null != response && null != response.getLineaCredito() && 
               response.getLineaCredito().getMonto() != null && response.getLineaCredito().getMonto().size() > 0){
            
            List<MontoType> listaMonto=response.getLineaCredito().getMonto();
            
            for(MontoType monto: listaMonto){  
                if(monto.getTipo() != null && null != monto.getTipo().getDescripcionCorta() &&
                    !"".equals(monto.getTipo().getDescripcionCorta())){
                    
                    descripcionMonto = monto.getTipo().getDescripcionCorta();
                    logger.warning("*Monto disponible de la linea : " + descripcionMonto);
                         
                    if(descripcionMonto.compareTo("DISPONIBLE") == 0){
                        
                        if(monto.getImporte() != null){
                            
                            montoDisponibleLinea = monto.getImporte();
                            logger.warning("Monto disponible de la linea : " + montoDisponibleLinea);
                            if(montoDisponibleLinea.compareTo(BigDecimal.ZERO) == 0){
                                logger.warning("Se muestra la fecha de ultimo desembolso");
                                esUltimoDesembolso = Boolean.TRUE;   
                            }else{
                                logger.warning("No se muestra fecha de ultimo desembolso.");
                            }
                            
                            logger.warning("*Importe recuperado: "+monto.getImporte()); 
                        }else{
                            logger.warning("*El Monto recuperado: "+ monto.getTipo().getDescripcionCorta()
                                                                            +" no tiene un importe");
                        }
                      }                               
                                                      
                  }else{
                    logger.warning("No se recupera la descripcion del monto de la linea disponible"); 
                  }                             
            }
            }else{
                logger.warning("No se recupera la lista de montos del servicio.");
            }
        }catch(Exception e){
            logger.warning("Error en consultaMontoDesembolsar.", e);
        }
        return esUltimoDesembolso;
    }
    /**
     * Returns the bind variable value for pIdOperacion.
     * @return bind variable value for pIdOperacion
     */
    public Long getpIdOperacion() {
        return (Long) getNamedWhereClauseParam("pIdOperacion");
    }

    /**
     * Sets <code>value</code> for bind variable pIdOperacion.
     * @param value value to bind as pIdOperacion
     */
    public void setpIdOperacion(Long value) {
        setNamedWhereClauseParam("pIdOperacion", value);
    }
}

