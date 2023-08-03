package org.bcie.fenix.view.matriztcc;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.bcie.fenix.common.utils.ADFUtils;

public class MatriztccTestBean {

    private RichRegion matriztccRegionUIC;

    /**
     * Constructor por defecto
     */
    public MatriztccTestBean() {
        super();
    }

    public void setMatriztccRegionUIC(RichRegion matriztccRegionUIC) {
        this.matriztccRegionUIC = matriztccRegionUIC;
    }

    public RichRegion getMatriztccRegionUIC() {
        return matriztccRegionUIC;
    }

    public void testActionListener(ActionEvent actionEvent) {
        
        if(actionEvent == null){
            return;
        }
        
        String value = null;
        value = String.valueOf(ADFUtils.getBoundAttributeValue("IdOperacionVarAttrValue"));
        System.out.println("Id Operacion: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("IdModalidadVarAttrValue"));
        System.out.println("Id Modalidad: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("EsModoEscrituraVarAttrValue"));
        System.out.println("Es Modo Escritura: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("IdTareaBpmVarAttrValue"));
        System.out.println("Id Tarea BPM: " + value);
        
        value = String.valueOf(ADFUtils.getBoundAttributeValue("MontoSolicitadoVarAttrValue"));
        System.out.println("Monto Solicitado: " + value);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(getMatriztccRegionUIC());
    }
}
