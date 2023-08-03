package pa15formalizacionenmiendasgenerichumantask.util;

/**
 * Contiene constantes usadas en el proceso PA15.
 * No permite instanciación.
 */
public final class PA15Constants {

    public PA15Constants() {
        throw new RuntimeException("Clase no permite instanciación.");
    }

    /** Prefijo usado en el Payload del proceso para almacenar los valores de desobligaciones de líneas de crédito.*/
    public static final String PALYOAD_PREFIXDESOBLIGAR = "$DESOBLIGAR$";
    
    /** TagName de un nodo que representan un param en el payload de la tarea. Usado en la busqueda de nodos.*/
    public static final String PAYLOAD_PARAMETERTYPE_TAGNAME = "ParameterType";
    /** Tipo de nodo que representa un param en el payload de la tarea.*/
    public static final String PAYLOAD_PARAMETERTYPE_NODE = "parameter:ParameterType";
    /** Tipo de nodo que almacena el nombre de un param dentro del payload de la tarea.*/
    public static final String PAYLOAD_PARAMETERTYPE_NAME_NODE = "parameter:parameterName";
    /** Tipo de nodo que almacena el valor de un param dentro del payload de la tarea.*/
    public static final String PAYLOAD_PARAMETERTYPE_VALUE_NODE = "parameter:parameterValue";
    
    /** Código externo del borrador de contrato de Enmienda.*/
    public static final String COD_EXTERNO_DOC_BORRADOR_CONTRATO_ENM = "BORCONTRAENM";

}
