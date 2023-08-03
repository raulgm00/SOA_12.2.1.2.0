package org.bcie.fenix.detalle.linea.bean;

import java.io.Serializable;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;


public class DetalleLineaCreditoBean implements Serializable {
    @SuppressWarnings("compatibility:-5274982369129965881")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger logger = ADFLogger.createADFLogger(DetalleLineaCreditoBean.class);
    private static final String CLASS_NAME = DetalleLineaCreditoBean.class.getName();

    public DetalleLineaCreditoBean() {
        super();
    }

    @SuppressWarnings("unchecked")
    public void precargaDetalleLineaCredito() {
        logger.warning("------------------> Inicio precargaDetalleLineaCredito ");
        System.out.println("------------------> Inicio precargaDetalleLineaCredito ");
        final String METHOD_NAME = "precargaDetalleLineaCredito";
        try {

            logger.entering(CLASS_NAME, METHOD_NAME);

            logger.warning("Ingresanod a la operacion manage bean precargaDetalleLineaCredito: ");
            // declarar recursos
            final BindingContainer binding = ADFUtils.getBindingContainer();
            final DCBindingContainer bindingsVO = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
                       
            final DCIteratorBinding dcIterator = bindingsVO.findIteratorBinding("DatosLineaCreditoVOIterator");
            
            final ViewObject vo = dcIterator.getViewObject();

            vo.executeEmptyRowSet();
            
            logger.warning("Despues de limpiar el Iter: Count : " + vo.getFetchedRowCount());
            
            
            final OperationBinding operationBinding = binding.getOperationBinding("consultarLineaCreditoByIdOperacion");
            final String idOperacion = JSFUtils.resolveExpression("#{pageFlowScope.pIdOperacion}").toString();

            logger.warning("Parametros : " + operationBinding + " - " + idOperacion);
            logger.warning("idOperacion enviado: " + idOperacion);

            if (operationBinding != null) {
                operationBinding.getParamsMap().put("idOperacion", Long.parseLong(idOperacion));
                operationBinding.execute();
                if (!operationBinding.getErrors().isEmpty()) {
                    logger.warning("OperationBinding devuelve errores metodo setIdOperacion");
                } else {
                    logger.warning("***Carga IdOperacion Listo, redireccionando... ");
                }
            } else {
                logger.severe("Operacion no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(ADFLogger.ERROR, METHOD_NAME, e.getMessage());
        }

        logger.exiting(CLASS_NAME, METHOD_NAME);
    }
}
