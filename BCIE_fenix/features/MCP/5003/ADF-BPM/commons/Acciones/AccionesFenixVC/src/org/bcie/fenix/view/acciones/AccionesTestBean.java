package org.bcie.fenix.view.acciones;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.bcie.fenix.common.utils.ADFUtils;

public class AccionesTestBean {
    
    private RichRegion accionesRegionUIC;
    
    public AccionesTestBean() {
        super();
        
    }

    public RichRegion getAccionesRegionUIC() {
        return accionesRegionUIC;
    }

    public void setAccionesRegionUIC(RichRegion accionesRegionUIC) {
        this.accionesRegionUIC = accionesRegionUIC;
    }
    
    public void testActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        
        String value = null;
        value = String.valueOf(ADFUtils.getBoundAttributeValue("IdOperacionVarAtt"));
        System.out.println("Id Operacion: " + value);
            
        value = String.valueOf(ADFUtils.getBoundAttributeValue("EsModoEscrituraVarAtt"));
        System.out.println("Es Modo Escritura: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("IdProcesoBpmVarAtt"));
        System.out.println("Id Proceso BPM: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("IdRolVarAtt"));
        System.out.println("Rol: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("IdClienteVarAtt"));
        System.out.println("Id cliente: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("LoginUsuarioAtt"));
        System.out.println("Login usuario: " + value);
                
        value = String.valueOf(ADFUtils.getBoundAttributeValue("EsValidacionAtt"));
        System.out.println("requiere valdiacion: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("IdCodigoTareaAtt"));
        System.out.println("codigo tarea: " + value);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getAccionesRegionUIC());
    }
    
}
