package org.bcie.fenix.view.adquisiciones.constantes;

public enum ResultadoProcesoEnum {
    ADJUDICADO(1),
    ANULADO(2),
    DESIERTO(3),
    FRACASADO(4);
    
    private Integer id;
    
    private ResultadoProcesoEnum(Integer id){
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public static ResultadoProcesoEnum loockup(Integer value) {
        ResultadoProcesoEnum resultadoEnum = null;
        
        if (value != null) {
            for(ResultadoProcesoEnum auxEnum: values()) {
                if (auxEnum.getId().equals(value)) {
                    resultadoEnum = auxEnum;
                    break;
                }
            }
        }
        
        return resultadoEnum;
    }
}
