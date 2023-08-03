package org.bcie.fenix.common.events;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.utils.JSFUtils;


public class ProcessContextualEvent {
    public static final ADFLogger LOGGER =ADFLogger.createADFLogger(ProcessContextualEvent.class);
    public ProcessContextualEvent() {
        super();
    }
    public void onChange(Object payload,String mbPath,Object extra){
        LOGGER.finest("Inicio evento onChange");
        ProcessContextualEventInterface mb=(ProcessContextualEventInterface)JSFUtils.resolveExpression("#{"+mbPath.trim()+"}");
        if(mb != null){
            mb.processOnChange(payload,extra);
        }
        LOGGER.finest("Fin evento onChange");
    }
    public void onRefresh(Object payload,String mbPath,Object extra){
        LOGGER.finest("Inicio evento onRefresh");
        ProcessContextualEventInterface mb=(ProcessContextualEventInterface)JSFUtils.resolveExpression("#{"+mbPath.trim()+"}");
        if(mb != null){
            mb.processOnRefresh(payload,extra);
        }
        LOGGER.finest("Fin evento onRefresh");
    }
    public void onClick(Object payload,String mbPath,Object extra){
        LOGGER.finest("Inicio evento onClick");
        ProcessContextualEventInterface mb=(ProcessContextualEventInterface)JSFUtils.resolveExpression("#{"+mbPath.trim()+"}");
        if(mb != null){
            mb.processOnClick(payload,extra);
        }
        LOGGER.finest("Fin evento onClick");
    }
}
