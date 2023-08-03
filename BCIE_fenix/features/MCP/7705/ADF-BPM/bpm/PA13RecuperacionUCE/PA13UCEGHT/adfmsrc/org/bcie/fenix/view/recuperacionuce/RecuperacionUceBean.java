package org.bcie.fenix.view.recuperacionuce;

import org.bcie.fenix.common.utils.JSFUtils;

public class RecuperacionUceBean {
    
    private String motivoSolicitud;
    
    public RecuperacionUceBean() {
        super();
    }
    
    public String getMotivoSolicitud() {
        String motivoSolicitudBD = null;
        String motivoSolicitudVista = null;
        if(null != JSFUtils.resolveExpression("#{bindings.MotivoSolicitud.inputValue}")){
            motivoSolicitudBD= (String) JSFUtils.resolveExpression("#{bindings.MotivoSolicitud.inputValue}");
            if(motivoSolicitudBD.equals("Discrecion") || motivoSolicitudBD.equals("DISCRECION") 
                || motivoSolicitudBD.equals("discrecion") || motivoSolicitudBD.equals("DISCRECIÓN")){
                motivoSolicitudVista="A discreción";
            }else{
                motivoSolicitudVista = motivoSolicitudBD;
            }
        }else{
            motivoSolicitudVista = "NULL";
        }
        return motivoSolicitudVista;
    }

    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }
    
    
}
