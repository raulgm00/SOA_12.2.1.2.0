package org.bcie.fenix.common.model.vo;

import com.bcie.xmlns.clienteservice.Cliente12BndQSService;

import java.util.Date;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.NameValuePairs;
import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.domain.Number;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.ViewObjectImpl;

import org.bcie.catalogobo.Catalogo;
import org.bcie.clientebo.Cliente;
import org.bcie.clientemo.ConsultarClienteResponseType;
import org.bcie.clientemo.ConsultarClientesByIdClienteRequestType;
import org.bcie.fenix.common.model.FenixModelConstants;
import org.bcie.fenix.common.model.am.FenixAMImpl;
import org.bcie.fenix.common.model.utils.IWsdlLocation;
import org.bcie.fenix.common.model.utils.ModelUtils;
import org.bcie.fenix.common.model.vo.common.RecuperacionUceVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jun 09 16:32:31 CDT 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class RecuperacionUceVOImpl extends ViewObjectImpl implements RecuperacionUceVO {
    /**
     * This is the default constructor (do not remove).
     */
    
    private static ADFLogger logger = null;
    
    
    public RecuperacionUceVOImpl() {
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public RecuperacionUceVORowImpl getRecuperacionUcePorIdClienteInstanciaProceso(oracle.jbo.domain.Number idCliente, String instanciaProceso) {
        logger.log(ADFLogger.WARNING, "Inside getRecuperacionUcePorIdClienteInstanciaProceso.");
        RecuperacionUceVORowImpl recuperacionUceRow = null;
        ViewCriteria recuperacionUceVOCriteria = null;
        
        //Los atributos idCliente e instanciaProceso son requeridos por el ViewCriteria
        if((idCliente != null) && (instanciaProceso != null)) {
            // 1) Filtramos el VO por idCliente e instanciaProceso
            recuperacionUceVOCriteria = 
                this.getViewCriteriaManager().getViewCriteria("RecuperacionUceVOCriteria");
            recuperacionUceVOCriteria.ensureVariableManager().setVariableValue("varIdCliente", idCliente);
            recuperacionUceVOCriteria.ensureVariableManager().setVariableValue("varInstanciaProceso", instanciaProceso);
            
            this.applyViewCriteria(recuperacionUceVOCriteria);
            this.executeQuery();
            
            // 2) Asignamos valor de retorno
            if(this.getEstimatedRowCount() > 0) {
                recuperacionUceRow = (RecuperacionUceVORowImpl)this.getRowAtRangeIndex(0);
                // Asignamos row como current
                this.setCurrentRow(recuperacionUceRow); 
                // Removemos criteria del VO
                this.getViewCriteriaManager().removeApplyViewCriteriaName("RecuperacionUceVOCriteria");
            }
        }
        return recuperacionUceRow;
    }
    
    public Row insertarRowRecuperacionUce(oracle.jbo.domain.Number idCliente, String  instanciaProceso, String motivoSolicitud, 
                                                oracle.jbo.domain.Number clienteDeteriorado){
        logger.log(ADFLogger.WARNING, "Inside insertarRowRecuperacionUce.");
        Row recuperacionUceRow = null;
        NameValuePairs nvpRecuperacionUce = null;
        SequenceImpl seqRecuperacionUce = null;
        oracle.jbo.domain.Number id = null;

        seqRecuperacionUce = new SequenceImpl("RECUPERACION_UCE_SEQ", getDBTransaction());
        id  = seqRecuperacionUce.getSequenceNumber();
        
        nvpRecuperacionUce = new NameValuePairs();
        
        nvpRecuperacionUce.setAttribute("Id", id);
        nvpRecuperacionUce.setAttribute("IdCliente", idCliente);
        nvpRecuperacionUce.setAttribute("InstanciaProceso", instanciaProceso);
        //Guardar el motivoSolicitud en MAYUSCULA.
        nvpRecuperacionUce.setAttribute("MotivoSolicitud", motivoSolicitud.toUpperCase());
        nvpRecuperacionUce.setAttribute("ClienteDeteriorado", clienteDeteriorado);
        nvpRecuperacionUce.setAttribute("BanEstatus", 1);
        nvpRecuperacionUce.setAttribute("FechaRegistro", new java.sql.Timestamp(System.currentTimeMillis()));
        nvpRecuperacionUce.setAttribute("Justificacion"," ");
        
        recuperacionUceRow = this.createAndInitRow(nvpRecuperacionUce);
                
        getDBTransaction().commit();
        
        return recuperacionUceRow;
    }
    
    
    /*
     * Gabriel Ni�o Rosales
     * Metodo que verificara primero si existe el row en BD, en base al idCliente y instanciaProceso.
     * De no existir crear el row en la base de datos con los valores de entrada.
     * 22/08/2016
     * llenarRecuperacionUceVO
     *
     * 
     */
    public void llenarRecuperacionUceVO(oracle.jbo.domain.Number idCliente, String  instanciaProceso, String motivoSolicitud, 
                                        oracle.jbo.domain.Number clienteDeteriorado){
        
        logger.warning("Inicia llenarRecuperacionUceVO");
        
        //Se aplica modificacion por incidencia FNXII-4557, debido a que el parametro clienteDeteriorado no es valido.
        oracle.jbo.domain.Number clienteDeterioradoWs = null;
        clienteDeterioradoWs = getFlagClienteDeterioradoWS(idCliente);
        
        if(clienteDeterioradoWs != null){
            
            // Buscamos el row por idCliente y instanciaProceso
            Row recuperacionUceRow = getRecuperacionUcePorIdClienteInstanciaProceso(idCliente,instanciaProceso);

            if(recuperacionUceRow == null){

                logger.log(ADFLogger.WARNING, "recuperacionUceRow no existe en BD.");
                // Si el row es null, insertamos en la base de datos con los parametros de entrada.
                recuperacionUceRow =insertarRowRecuperacionUce(idCliente,instanciaProceso,motivoSolicitud,clienteDeterioradoWs);
                // Insertamos el row que se creo.
                this.insertRowAtRangeIndex(0, recuperacionUceRow); // Insertamos row en VO
                this.setCurrentRow(recuperacionUceRow); // Asignamos nuevo row como current
            }
            Long id = (Long)this.getCurrentRow().getAttribute("Id");
            logger.log(ADFLogger.WARNING, "id recuperacionUce: "+id);
        }else{
            logger.severe("No fue posible inicializar el registro de Recuperacion UCE por falta de informacion del cliente");
        }
    }
    
    /**
     * Consume el servicio para obtener informacion del deterioro del cliente
     * @param idCliente contiene el id del cliente
     * @return devuelve valor bandera del deterioro
     */
    public oracle.jbo.domain.Number getFlagClienteDeterioradoWS(oracle.jbo.domain.Number idCliente){
        
        logger.warning("Inicia getFlagClienteDeterioradoWS");
        oracle.jbo.domain.Number flag = null;
        
        if(idCliente != null){
            
            boolean infoMora = false; 
            boolean infoDeterioro = true;
            try{
                logger.log(ADFLogger.WARNING,"Into consultarClienteByIdCliente");
                FenixAMImpl fenixAM = (FenixAMImpl)this.getRootApplicationModule();
                String wsdl = fenixAM.getWsdl(IWsdlLocation.CLIENTE);
                Cliente12BndQSService cliente12BndQSService = IWsdlLocation.Service.getInstance(Cliente12BndQSService.class, wsdl);
                
                ConsultarClientesByIdClienteRequestType request = new ConsultarClientesByIdClienteRequestType();
                request.setIdCliente(idCliente.longValue());
                request.setInfoDeterioro(infoDeterioro);
                request.setInfoMora(infoMora);
                
                Date horaInicio = ModelUtils.logStartWS(logger, request, FenixModelConstants.WSC_CONSULTAR_CLIENTE);
                  ConsultarClienteResponseType response = cliente12BndQSService.getCliente12BndQSPort().consultarByIdCliente(request);
                ModelUtils.logEndWS(logger, response, FenixModelConstants.WSC_CONSULTAR_CLIENTE, horaInicio);
               
                if(response != null){
                    if(response.getResultado() != null){
                        if(response.getResultado().getResult() != null &&
                           response.getResultado().getResult().toString().equalsIgnoreCase("OK")){
                            
                            logger.warning("Servicio responde OK");
                            if(response.getCliente() != null){
                                
                                if(response.getCliente().size() > 0){
                                    Cliente client = response.getCliente().get(0);
                                    if(client != null){
                                        
                                        if(client.isDeteriorado() != null){
                                            if(client.isDeteriorado()){
                                                logger.warning("El cliente esta deteriorado");
                                                flag = new oracle.jbo.domain.Number(1);
                                            }else{
                                                logger.warning("El cliente no esta deteriorado");
                                                flag = new oracle.jbo.domain.Number(0);
                                            }
                                        }else{
                                            logger.severe("Bandera de deterioro es NULL");
                                        }
                                    }else{
                                        logger.severe("Objeto de datos del cliente es NULL");
                                    }
                                }else{
                                    logger.severe("El objeto Cliente no devuelve elementos");
                                }
                            }else{
                                logger.severe("El objeto Cliente es NULL");
                            }
                            //plazoCondicionResponse.getResult().getResult().toString().equalsIgnoreCase("OK")
                        }else{
                            logger.severe("Servicio de consultar cliente por Id, devuelve error");
                            if(response.getResultado().getResult() != null){
                                logger.severe("Resultado con error. " + response.getResultado().getResult());
                                
                                if(response.getResultado().getMessage() != null){
                                    logger.severe("Resultado con error. Mensaje: " + response.getResultado().getMessage());
                                }
                            }
                            if(response.getResultado().getError() != null){
                                logger.severe("Objeto Error. Code: " + response.getResultado().getError().getErrorCode() + 
                                              " . Descripcion: " + response.getResultado().getError().getErrorDescription());
                            }
                        }
                    }else{
                        logger.severe("Resultado del servicio es NULL");
                    }
                }else{
                    logger.severe("Response es NULL");
                }
            }catch(Exception e){
                logger.severe("Error al consumir la consulta del cliente", e);
            } 
        }

        logger.warning("Finaliza getFlagClienteDeterioradoWS");
        return flag;
    }


    /**
     * Returns the variable value for varIdCliente.
     * @return variable value for varIdCliente
     */
    public Number getvarIdCliente() {
        return (Number) ensureVariableManager().getVariableValue("varIdCliente");
    }

    /**
     * Sets <code>value</code> for variable varIdCliente.
     * @param value value to bind as varIdCliente
     */
    public void setvarIdCliente(Number value) {
        ensureVariableManager().setVariableValue("varIdCliente", value);
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
}

