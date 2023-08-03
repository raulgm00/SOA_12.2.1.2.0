package org.bcie.fenix.view.consultarobservacioncargoprepago;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class ConsultarObservacionCargoPrepagoBean implements Serializable{
    @SuppressWarnings("compatibility:4385953310694589912")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    public ConsultarObservacionCargoPrepagoBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void inicializarConsultarObservacionCargoPrepago() {
       
        Long idPrepago = null; 
        BindingContainer bindings = ADFUtils.getBindingContainer();
        
        if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}")){
         idPrepago = Long.valueOf(JSFUtils.resolveExpression("#{pageFlowScope.pIdPrepago}").toString());
        logger.log(ADFLogger.WARNING, "Valor de Id de prepago.." + idPrepago);
        }else{
                logger.log(ADFLogger.WARNING, "Objeto pageFlowScope.pIdPrepago Nulo.");
        }
        
        try{
            OperationBinding operationBinding = bindings.getOperationBinding("setpIdPrepago");
            operationBinding.getParamsMap().put("value", idPrepago);
            
            operationBinding.execute();
        
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al agregar datos en inicializarConsultarObservacionCargoPrepago.", e);
        }
    }
}
