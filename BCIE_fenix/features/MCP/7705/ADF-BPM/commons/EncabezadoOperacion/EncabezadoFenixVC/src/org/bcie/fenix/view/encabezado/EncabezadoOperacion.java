package org.bcie.fenix.view.encabezado;

public class EncabezadoOperacion {
    public EncabezadoOperacion() {
        super();
    }
    
    public boolean validateVisibility(Object poValue) {
        if(poValue!=null && poValue!="") {
            return true;
        }   
        
        return false;
    }
    
}
