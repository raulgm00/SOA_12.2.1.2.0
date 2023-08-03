package pc05formalizaciongenerichumantask.constants;

public enum ConRecursoExternoEnum {
    SI(1),
    NO(2),
    GESTION(3);
    
    private Integer id;
    
    private ConRecursoExternoEnum(Integer id) {
        this.id = id;
    }
    
    public static ConRecursoExternoEnum lookup(Integer id) {
        ConRecursoExternoEnum cuentaRecEnum = null;
        
        for(ConRecursoExternoEnum lookupEnum: values()) {
            if (lookupEnum.getId() == id) {
                cuentaRecEnum = lookupEnum;
                break;
            }
        }
        
        return cuentaRecEnum;
    }
    
    public Integer getId() {
        return this.id;
    }
}
