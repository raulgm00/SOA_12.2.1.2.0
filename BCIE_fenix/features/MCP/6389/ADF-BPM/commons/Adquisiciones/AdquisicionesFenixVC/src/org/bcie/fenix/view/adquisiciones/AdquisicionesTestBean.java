package org.bcie.fenix.view.adquisiciones;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.bcie.fenix.common.utils.ADFUtils;

public class AdquisicionesTestBean {
    private RichRegion regionAdquisicionUI;

    public AdquisicionesTestBean() {
        super();
    }
    
    public void testActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        
        String value = null;
        value = String.valueOf(ADFUtils.getBoundAttributeValue("CodigoTareaAtt"));
        System.out.println("Codigo tarea: " + value);
            
        value = String.valueOf(ADFUtils.getBoundAttributeValue("RolIdAtt"));
        System.out.println("Rol usuario: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("LecturaAtt"));
        System.out.println("Lectura: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("CodigoOperacionAtt"));
        System.out.println("Codigo de operacion: " + value);
              
        AdfFacesContext.getCurrentInstance().addPartialTarget(regionAdquisicionUI);
    }

    public void setRegionAdquisicionUI(RichRegion regionAdquisicionUI) {
        this.regionAdquisicionUI = regionAdquisicionUI;
    }

    public RichRegion getRegionAdquisicionUI() {
        return regionAdquisicionUI;
    }
}
