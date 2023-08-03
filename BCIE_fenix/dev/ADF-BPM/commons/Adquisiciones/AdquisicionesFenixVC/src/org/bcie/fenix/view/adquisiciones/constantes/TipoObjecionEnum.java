package org.bcie.fenix.view.adquisiciones.constantes;

public enum TipoObjecionEnum {
    INFORME_RESULTADOS(7),
    CONTRATADO(9),
    RESULTADO_PROCESO(10),
    ORDEN_COMPRA(11);
    
    private Integer id;
    
    private TipoObjecionEnum(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public static TipoObjecionEnum loockup(Integer value) {
        TipoObjecionEnum tipoEnum = null;
        
        if (value != null) {
            for(TipoObjecionEnum auxEnum: values()) {
                if (auxEnum.getId().equals(value)) {
                    tipoEnum = auxEnum;
                    break;
                }
            }
        }
        
        return tipoEnum;
    }
}
