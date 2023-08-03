package org.bcie.fenix.view.observacioncargoprepago;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;
import org.bcie.fenix.common.view.beans.FenixActionBean;

public class ObservacionCargoPrepagoBean extends FenixActionBean implements Serializable {
    @SuppressWarnings("compatibility:2856883489248399474")
    private static final long serialVersionUID = 1L;
    private static ADFLogger logger = null;
    
    private Boolean determinarMonto = Boolean.TRUE;
    private Boolean validarCargoPrepago = Boolean.FALSE;


    public ObservacionCargoPrepagoBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void insertarObservacionCargoPrepago() {
       
    }
    
    public void agregarDatosCargoPrepago(){
        logger.log(ADFLogger.WARNING, "INTO agregarDatosCargoPrepago.");
        
        Long idPrepago = null;
        Integer idTcaRolBpm = null;
        Integer idTcaTareaBpm = null;
        String loginUsuario = null;
        String nombreUsuario = null;
        
        
        BindingContainer bindings = getBindings();
        
        try{
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}") != null){
            idPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
            }else{
                logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdPrepago Nulo.");
            }
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaRolBpm}") != null){
                idTcaRolBpm = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaRolBpm}").toString());
            }else{
                logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdTcaRolBpm Nulo.");
            }
            if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaTareaBpm}") != null){
                idTcaTareaBpm = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaTareaBpm}").toString());
            }else{
                logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdTcaTareaBpm Nulo.");
            }
            if(JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}") != null){
                loginUsuario = (String)JSFUtils.resolveExpression("#{pageFlowScope.pLoginUsuario}");
            }else{
                logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pLoginUsuario Nulo.");
            }
            if(JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}") != null){
                nombreUsuario = (String)JSFUtils.resolveExpression("#{pageFlowScope.pNombreUsuario}");
            }else{
                logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pNombreUsuario Nulo.");
            }
            OperationBinding operationBinding = bindings.getOperationBinding("crearRowFormularioObservacionCargoPrepago");
            
            operationBinding.getParamsMap().put("idPrepago", idPrepago);
            operationBinding.getParamsMap().put("idTcaRolBpm", idTcaRolBpm);
            operationBinding.getParamsMap().put("idTcaTareaBpm", idTcaTareaBpm);
            operationBinding.getParamsMap().put("loginUsuario", loginUsuario);
            operationBinding.getParamsMap().put("nomUsuario", nombreUsuario);
            operationBinding.execute();
            
        
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al agregar datos en agregarDatosCargoPrepago." + e.getClass() + e.getMessage());
        }
    }
    
    public void cargarDatosCargoPrepago(){
        Integer aplicaCargoAdicional = null;
        BindingContainer bindings = getBindings();
        try{
            OperationBinding operationBinding = bindings.getOperationBinding("llenarFormularioCargoPrepago");
            operationBinding.execute();
            aplicaCargoAdicional = (Integer)operationBinding.getResult();
            logger.log(ADFLogger.WARNING, "Valor de Aplica cargo.." + aplicaCargoAdicional);
            if(aplicaCargoAdicional == 1){
                  setDeterminarMonto(Boolean.FALSE);
                  logger.log(ADFLogger.WARNING, "Valor Monto....." + getDeterminarMonto());
              }else{
                //JSFUtils.setExpressionValue("#{bindings.DeterminarMonto.inputValue}", null);
                ADFUtils.setBoundAttributeValue("DeterminarMonto", null);
                //JSFUtils.setExpressionValue("#{bindings.TipoMoneda.inputValue}", null);
                ADFUtils.setBoundAttributeValue("TipoMoneda", null);
                setDeterminarMonto(Boolean.TRUE);
                logger.log(ADFLogger.WARNING, "Valor Monto.." + getDeterminarMonto());
            }
        }catch(Exception e){
            aplicaCargoAdicional = null;
            logger.log(ADFLogger.ERROR, "Error en cargarDatosCargoPrepago." + e.getClass() + "." + e.getMessage());
        }
        logger.log(ADFLogger.WARNING, "Valor de aplicar Cargo Adicional...." + aplicaCargoAdicional);
    }
    
    public Boolean validarObservacionCargoPrepago(){
           logger.log(ADFLogger.WARNING, "INTO validarObservacionCargoPrepago.");
           Long idPrepago = null;
           Integer idTcaRolBpm = null;
           
           BindingContainer bindings = getBindings();
           
           try{
               logger.log(ADFLogger.WARNING, "Valor Id Prepago PageFlow." + JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}"));
               if(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}") != null){
               idPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
               }else{
                   logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdPrepago Nulo.");
               }
               if(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaRolBpm}") != null){
                   idTcaRolBpm = Integer.parseInt(JSFUtils.resolveExpression("#{pageFlowScope.pIdTcaRolBpm}").toString());
               }else{
                   logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdTcaRolBpm Nulo.");
               }
               logger.log(ADFLogger.WARNING, "Valor id Prepago." + idPrepago);
               logger.log(ADFLogger.WARNING, "Valir id Rol BPM." + idTcaRolBpm);
               OperationBinding operationBinding = bindings.getOperationBinding("validarObservacionCargoPrepago");
               
               operationBinding.getParamsMap().put("idPrepago", idPrepago);
               operationBinding.getParamsMap().put("idTcaRolBpm", idTcaRolBpm);
               operationBinding.execute();
               validarCargoPrepago = (Boolean)operationBinding.getResult();
           }catch(Exception e){
               validarCargoPrepago = Boolean.FALSE;
               logger.log(ADFLogger.ERROR, "Error en validarObservacionCargoPrepago." + e.getClass() + "." + e.getMessage());
           }
           logger.log(ADFLogger.WARNING, "Valor de validarCargoPrepago...." + validarCargoPrepago);
           return validarCargoPrepago;
       }

    public void setDeterminarMonto(Boolean determinarMonto) {
        this.determinarMonto = determinarMonto;
    }

    public Boolean getDeterminarMonto() {
        return determinarMonto;
    }
    
    public void setValidarCargoPrepago(Boolean validarCargoPrepago) {
        this.validarCargoPrepago = validarCargoPrepago;
    }

    public Boolean getValidarCargoPrepago() {
        return validarCargoPrepago;
    }
}
