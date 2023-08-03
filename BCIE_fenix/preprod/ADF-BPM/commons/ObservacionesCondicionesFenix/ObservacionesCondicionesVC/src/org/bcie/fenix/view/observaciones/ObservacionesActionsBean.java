package org.bcie.fenix.view.observaciones;

import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

/**
 * Clase para manejar eventos/acciones disparados desde el fragmento
 */
public class ObservacionesActionsBean {
    private static ADFLogger logger = null;
    
    public ObservacionesActionsBean() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void agregarObservacionActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside agregarObservacionActionListener: " + actionEvent.getComponent().getId());
        logger.warning("Ingresa a agregarObservacionActionListener");
        BindingContainer bindings = ADFUtils.getBindingContainer();
        DCIteratorBinding voObservacionCondicion = null;
        ObservacionesBean observacionesBean = null;
        OperationBinding operationBinding = null;
        logger.warning("paso 1");
        Integer agrupadorAux = null;
        Long idAgrupador = null;
        try{
            if(null != JSFUtils.resolveExpression("#{pageFlowScope.pIdAgrupador}")){
                agrupadorAux = (Integer)JSFUtils.resolveExpression("#{pageFlowScope.pIdAgrupador}");
                idAgrupador = agrupadorAux.longValue();
                logger.warning("Valor del agrupador :" + idAgrupador);
            }else{
                logger.warning("El valor del agrupador es nulo.");
            }
            if(validarAgregarObservacion()) {
                // Asignación de variables
                logger.warning("Ingresa al if");
                observacionesBean = (ObservacionesBean)JSFUtils.resolveExpression("#{pageFlowScope.ObservacionesManagedBean}");
                voObservacionCondicion = ADFUtils.getDCBindingContainer().findIteratorBinding("ObservacionCondicionVOIterator");
                logger.warning("paso 2");
                // Agregamos observación a la BD
                operationBinding = bindings.getOperationBinding("agregarObservacion");
                operationBinding.getParamsMap().put("idCondicion", JSFUtils.resolveExpression("#{pageFlowScope.pIdCondicion}"));
                operationBinding.getParamsMap().put("idTareaBpm", JSFUtils.resolveExpression("#{pageFlowScope.pIdTareaBpm}"));
                operationBinding.getParamsMap().put("idAgrupador", idAgrupador);
                operationBinding.getParamsMap().put("observacion", observacionesBean.getObservacionCumplimiento());
                operationBinding.getParamsMap().put("loginUsuario", ADFContext.getCurrent().getSecurityContext().getUserName());
                operationBinding.getParamsMap().put("nombreUsuario", ADFContext.getCurrent().getSecurityContext().getUserProfile().getDisplayName());
                logger.warning("paso 3");
                operationBinding.execute();
                if(!operationBinding.getErrors().isEmpty()){
                    // Manejo de errores
                    JSFUtils.addFacesErrorMessage(operationBinding.getErrors().toString());
                }
                
                // Limpiamos input text
                observacionesBean.setObservacionCumplimiento(null);
                
                // Actualizamos los comentarios insertados
                voObservacionCondicion.executeQuery();
            }
        }catch(Exception e){
            logger.warning("Error al agregar la observacion.",e);
        }
    }
    
    private Boolean validarAgregarObservacion(){
        // Validación de campos obligatorios
        Boolean esDatosCorrectos = Boolean.TRUE;
        ObservacionesBean observacionesBean = (ObservacionesBean)JSFUtils.resolveExpression("#{pageFlowScope.ObservacionesManagedBean}");

        if((observacionesBean.getObservacionCumplimiento() == null) 
           || (observacionesBean.getObservacionCumplimiento().trim().length() < 1)) {
            
            esDatosCorrectos = Boolean.FALSE;
            JSFUtils.addFacesErrorMessage("Debe completar la información requerida: Observación.");
        }
                
        return esDatosCorrectos;
    }
}
