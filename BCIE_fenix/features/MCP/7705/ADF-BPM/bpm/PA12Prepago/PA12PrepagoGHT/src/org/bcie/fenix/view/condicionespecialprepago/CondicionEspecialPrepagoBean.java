package org.bcie.fenix.view.condicionespecialprepago;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class CondicionEspecialPrepagoBean implements Serializable{
    @SuppressWarnings("compatibility:659015880878223113")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    public CondicionEspecialPrepagoBean() {
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void inicalizarCondicionEspecialPrepago() {
        logger.warning("Dentro inicalizarCondicionEspecialPrepago");
        
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Long pIdLineaCredito = null;
        OperationBinding operationBinding = null;
        
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdLineaCredito}")){
                pIdLineaCredito = Long.valueOf(
                            JSFUtils.resolveExpression("#{pageFlowScope.pIdLineaCredito}").toString());
                logger.log(ADFLogger.WARNING, "pIdLineaCredito : " + pIdLineaCredito);
                operationBinding = bindings.getOperationBinding("setpIdLineaCredito");
                operationBinding.getParamsMap().put("value", pIdLineaCredito);
                operationBinding.execute();
            }else{
                logger.log(ADFLogger.WARNING, "pIdLineaCredito es nulo, no se inicializa " +
                    "la tabla de Condiciones Especiales");
            }
        }catch(Exception e){
            logger.severe("Error en inicalizarCondicionEspecialPrepago : ",e);
        }
        
        logger.warning("Fuera inicalizarCondicionEspecialPrepago");
    }
}
