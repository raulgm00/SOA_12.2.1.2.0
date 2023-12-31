package org.bcie.fenix.common.model.vo;

import com.bcie.xmlns.clienteservice.Cliente12BndQSService;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.clientebo.Cliente;
import org.bcie.clientemo.ConsultarClienteResponseType;
import org.bcie.clientemo.ConsultarClientesByIdClienteRequestType;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.common.ValidarT406ByDesembolsoVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Apr 10 17:15:17 CDT 2019
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ValidarT406ByDesembolsoVOImpl extends ViewObjectImpl implements ValidarT406ByDesembolsoVO {
   
    private static ADFLogger logger = null;
   
    public ValidarT406ByDesembolsoVOImpl() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public Map validarTermino406(Long idDesembolso){
        logger.warning("Inicia metodo validarTermino406 para el desembolso: "+idDesembolso);
       
        Map datos = new HashMap();
        Boolean respuesta = Boolean.TRUE;
        
        
        setpIdDesembolso(idDesembolso);
        this.executeQuery();
        
        logger.warning("Numero contrapartes encontradas para el desembolso: "+getEstimatedRowCount());
        
         if(getEstimatedRowCount() > 0){
              
            this.setRangeSize(-1);
         
            Row[] rows = getAllRowsInRange();
            
            for(Row row: rows){
                   Long idCliente = (Long)row.getAttribute("IdCliente");
                   
                    try{ 
                        if(clienteEnMora(idCliente)){
                            datos.put("clietneEnMora", idCliente);
                            respuesta = Boolean.FALSE; 
                            break;
                        }
                    }catch(Exception e){                    
                        JboException ex = new JboException(e.getMessage());
                        throw ex;
                    } 
            }                        
            
         }else{
             logger.warning("no se encontraron registros de contrapartes para el desembolso, no aplica validacion T406 ");
         }
         
        datos.put("respuesta", respuesta);
        
        
        logger.warning("Termina metodo pasoValidacionTermino406 respuesta: "+respuesta);
        return datos;
    }
    
    
    public Boolean clienteEnMora(Long idCliente){
        logger.warning("inicia metodo consultarClienteByIdCliente");
        boolean respuesta = Boolean.TRUE;
      
        boolean infoMora = Boolean.TRUE;
        boolean infoDeterioro = Boolean.FALSE;
                            
        try{
            FenixAMImpl fenixAM;   
            fenixAM = (FenixAMImpl)this.getRootApplicationModule();
            
            String wsdl = fenixAM.getWsdl(IWsdlLocation.CLIENTE);
            Cliente12BndQSService cliente12BndQSService = IWsdlLocation.Service.getInstance(Cliente12BndQSService.class, wsdl);
            ConsultarClientesByIdClienteRequestType request = new ConsultarClientesByIdClienteRequestType();
            
            request.setIdCliente(idCliente);
            request.setInfoDeterioro(infoDeterioro);
            request.setInfoMora(infoMora);
            
            Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_CONSULTAR_CLIENTE);
            ConsultarClienteResponseType response = cliente12BndQSService.getCliente12BndQSPort().consultarByIdCliente(request);
            ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_CONSULTAR_CLIENTE, horaInicio);
            
            if(0 < response.getCliente().size() && null != response.getCliente()){                            
                
                Cliente cliente = response.getCliente().get(0);
                logger.warning("Cliente en Mora: "+cliente.isEnMora());                
                
                   respuesta = cliente.isEnMora();                                
                           
            }else{
                logger.warning("ConsultarClienteResponse es resuleto a null, no se puede validar la mora");
            }
            
        }catch(Exception e){
            
            JboException ex = new JboException("Error no se pudo validar la Mora del cliente: "+idCliente+ " error->" +e.getMessage());
            throw ex;
        } 
        
        return respuesta;
    }
    

    /**
     * Returns the bind variable value for pIdDesembolso.
     * @return bind variable value for pIdDesembolso
     */
    public Long getpIdDesembolso() {
        return (Long) getNamedWhereClauseParam("pIdDesembolso");
    }

    /**
     * Sets <code>value</code> for bind variable pIdDesembolso.
     * @param value value to bind as pIdDesembolso
     */
    public void setpIdDesembolso(Long value) {
        setNamedWhereClauseParam("pIdDesembolso", value);
    }
}

