package org.bcie.fenix.view.observacioncargoprepago;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.utils.ADFUtils;
import org.bcie.fenix.common.utils.JSFUtils;

import pa12prepagoght.bean.PrepagoBean;

import org.bcie.fenix.common.view.beans.FenixActionBean;

public class ObservacionCargoPrepagoActionBean extends FenixActionBean{
    
    private static ADFLogger logger = null;
    public ObservacionCargoPrepagoActionBean() {
        super();
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }

    public void aplicaCargoValueChangeListener(ValueChangeEvent valueChangeEvent) {
        logger.log(ADFLogger.WARNING, "INTO montoCargoValueChangeListener.");
        ObservacionCargoPrepagoBean observacionCargoPrepagoBean = (ObservacionCargoPrepagoBean) JSFUtils.resolveExpression("#{pageFlowScope.observacionCargoPrepagoBean}");
        valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        Integer aplicaCargo = null;
        try{
            aplicaCargo = (Integer)(valueChangeEvent.getNewValue());
        }catch(Exception e){
            logger.log(ADFLogger.ERROR, "Error al recuperar valor seleccionado " +
                "en aplicaCargoValueChangeListener. "+e);
        }
        if(null != aplicaCargo){
        logger.log(ADFLogger.WARNING, "Valor de Aplica cargo :" + aplicaCargo);
            if(aplicaCargo == 1){
                observacionCargoPrepagoBean.setDeterminarMonto(Boolean.FALSE);
                    logger.log(ADFLogger.WARNING, "Valor Monto :" + observacionCargoPrepagoBean.getDeterminarMonto());
            }else{
                try{
                    ADFUtils.setBoundAttributeValue("DeterminarMonto", null);
                    ADFUtils.setBoundAttributeValue("TipoMoneda", null);
                    observacionCargoPrepagoBean.setDeterminarMonto(Boolean.TRUE);
                    logger.log(ADFLogger.WARNING, "Valor Monto :" + observacionCargoPrepagoBean.getDeterminarMonto());
                }catch(Exception e){
                    logger.log(ADFLogger.ERROR, "Error al limpiar valores DeterminarMonto y TipoMoenda."+e);
                }
            }
        }else{
            JSFUtils.addFacesErrorMessage("Error al recuperar un valor seleccionado.");
            logger.log(ADFLogger.ERROR, "No se pudo recuperar un valor.");
        }
    }
}
