package org.bcie.fenix.view.adquisiciones.constantes;

public enum TipoConcursanteEnum {
    OFERENTE(1),
    ADJUDICATARIO(2),
    CONTRATADO(3);
    
    private Integer id;
    
    private TipoConcursanteEnum(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public static TipoConcursanteEnum loockup(Integer value) {
        TipoConcursanteEnum tipoEnum = null;
        
        if (value != null) {
            for(TipoConcursanteEnum auxEnum: values()) {
                if (auxEnum.getId().equals(value)) {
                    tipoEnum = auxEnum;
                    break;
                }
            }
        }
        
        return tipoEnum;
    }
}
