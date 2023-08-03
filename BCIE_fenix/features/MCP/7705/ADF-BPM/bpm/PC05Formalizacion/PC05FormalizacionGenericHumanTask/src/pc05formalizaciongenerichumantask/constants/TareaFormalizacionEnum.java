package pc05formalizaciongenerichumantask.constants;

/**
 * Enum de las tareas de formalizaci&oacute;n
 * @author Jonathan Ruiz
 */
public enum TareaFormalizacionEnum {
    DEFINIR_RECURSOS_EXTERNOS("54"),
    ELABORAR_CONTRATO_FIRMADO("55"),
    ADJUNTAR_CONTRATO_FIRMADO("59"),
    ASIGNACION_RECURSOS("63");
    
    // Id de la tarea
    private String idTarea;
    
    private TareaFormalizacionEnum(String codigoTarea) {
        this.idTarea = codigoTarea;
    }
    
    public String getIdTarea() {
        return idTarea;
    }
    
    /**
     * Método que devuelve el enum correspondiete a la tarea
     * @author Jonathan Ruiz
     * @param codigoTarea C&oacute;digo de la tarea
     * @return TareaFormalizacionEnum
     */
    public static TareaFormalizacionEnum lookup(String codigoTarea) {
        TareaFormalizacionEnum tareaEnum = null;
        
        for(TareaFormalizacionEnum pantalla: values()) {
            if (pantalla.getIdTarea() == codigoTarea) {
                tareaEnum = pantalla;
                break;
            }
        }
        
        return tareaEnum;
    }
}
