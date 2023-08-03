package org.bcie.fenix.view.adquisiciones.constantes;

public enum BotonEnum {
    AGREGAR(1),
    ELIMINAR(2),
    ADJUDICAR(3),
    CONTRATAR(4);
    
    private Integer id;
    
    private BotonEnum(Integer id) {
        this.id = id;
    }
    
}
