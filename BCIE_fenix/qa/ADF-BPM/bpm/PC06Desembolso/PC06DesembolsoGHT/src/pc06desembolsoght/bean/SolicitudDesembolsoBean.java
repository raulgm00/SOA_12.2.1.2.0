package pc06desembolsoght.bean;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

public class SolicitudDesembolsoBean {
    
    private static ADFLogger logger = null;
    
    public SolicitudDesembolsoBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void precargaSolicitud() {
        logger.warning("Dentro precargaSolicitud");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        Long idSolicitudDesembolsoLong = null;
        String idSolicitudDesembolsoStr = null;
        Long idContratoDesembolso = null;
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitudDesembolso}")){
                idSolicitudDesembolsoStr = (String)JSFUtils.resolveExpression("#{pageFlowScope.pIdSolicitudDesembolso}");
                logger.warning("idSolicitudDesembolsoStr :"+idSolicitudDesembolsoStr);
                idSolicitudDesembolsoLong = new Long(idSolicitudDesembolsoStr);
                logger.warning("idSolicitudDesembolsoLong :"+idSolicitudDesembolsoLong);
                //consultar contrato de desembolso por idSolicitudDesembolso
                OperationBinding operationBinding = bindings.getOperationBinding("idContratoMayorSolicitud");
                operationBinding.getParamsMap().put("idSolicitud",idSolicitudDesembolsoLong);
                operationBinding.execute();
                if (operationBinding.getResult() != null) {
                    idContratoDesembolso= (Long) operationBinding.getResult();
                    if(null != idContratoDesembolso){
                        //consultar DetalleContratoDesembolsoVO por idContratoDesembolso
                        operationBinding = bindings.getOperationBinding("buscarDetalleContratoDesembolsoPorId");
                        operationBinding.getParamsMap().put("idContratoDesembolso",idContratoDesembolso);
                        operationBinding.execute();
                    }else{
                        logger.warning("idContratoDesembolso es nulo");
                    }
                }
            }else{
                logger.warning("#{pageFlowScope.pIdSolicitudDesembolso} es nulo, no consulta detalleContratoDesembolso");
            }
        }catch(Exception e){
            logger.warning("Error en precargaSolicitud :"+e);
        }
        logger.warning("Fuera precargaSolicitud");
    }
    
}
