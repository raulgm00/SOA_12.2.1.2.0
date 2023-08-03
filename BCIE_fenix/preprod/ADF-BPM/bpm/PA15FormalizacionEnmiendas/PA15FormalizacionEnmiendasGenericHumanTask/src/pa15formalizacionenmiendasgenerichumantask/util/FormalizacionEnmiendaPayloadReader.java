package pa15formalizacionenmiendasgenerichumantask.util;

import java.io.IOException;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.BPMUtils;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Clase inmutable encargada de leer el payload de Formalización de Enmiendas.
 */
public final class FormalizacionEnmiendaPayloadReader {

    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(FormalizacionEnmiendaPayloadReader.class);

    /* CONSTANTES PARA NOMBRES DE ATRIBUTOS EN PAYLOAD DE LA TAREA. */
    private static final String CODIGO_TAREA = "CodigoTarea";
    private static final String CODIGO_OPERACION = "CodigoOperacion";
    private static final String ID_ENMIENDA = "idEnmienda";
    private static final String ORIGEN_ENMIENDA = "OrigenFormalizacionEnmiendas";
    private static final String CODIGO_PRODUCTO = "CodigoProducto";
    private static final String SOLICITUD_CONTRATACION = "solicitudContratacion";
    private static final String DESOBLIGACION = "esDesobligacion";
    private static final String CAMBIO_PLAZO = "esCambioPlazo";

    /** Código de la tarea en el sistema Fenix. Su valor proviene del Payload de la tarea.*/
    private final int codigoTarea;

    /** Identificador de la operación. Su valor proviene del Payload de la tarea.*/
    private final long idOperacion;

    /** Identificador de la enmienda. Su valor proviene del Payload de la tarea.*/
    private final long idEnmienda;
    
    /** Identificador del producto. Su valor proviene del Payload de la tarea.*/
    private final int idProducto;
    
    /** Identifica el origen de la formalización de Enmiendas. Sus valores se encuentran en FenixConstants.*/
    private final int origen;
    
    /** Su valor proviene del Payload de la tarea.*/
    private final boolean solicitudContratacion;

    /** Su valor proviene del Payload de la tarea.*/
    private final boolean esDesobligacion;
    
    /** Su valor proviene del Payload de la tarea.*/
    private final boolean esCambioPlazo;
    
    /** 
     * Mapa que contiene los montos a desobligar de las líneas de crédito que se almacenan en el payload. 
     * Key=ID Línea crédito, Value = Monto a Desobligar
     */
    private final Map<Long, BigDecimal> mapMontosDesobligar;
    
    /**
     * Constructor de esta clase.
     * No permite creación directa de objetos.
     * Se espera que el cliente use el método constructor.
     * @param xmlPayload Payload de donde se extraerán los valores de los atributos de esta clase.
     * @see #readCurrentTaskPayload()
     */
    private FormalizacionEnmiendaPayloadReader(XMLElement xmlPayload) {

        if (xmlPayload == null) {
            LOGGER.warning("Invocación de FormalizacionEnmiendaPayloadReader sin payload para leer sus atributos.");
            this.codigoTarea = -1;
            this.idOperacion = -1;
            this.idEnmienda = -1;
            this.idProducto = -1;
            this.origen = -1;
            this.solicitudContratacion = false;
            this.esDesobligacion = false;
            this.esCambioPlazo = false;
            this.mapMontosDesobligar = Collections.emptyMap();
        } else {
            NodeList nl;

            nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
            this.codigoTarea = this.parseInt(nl);

            nl = xmlPayload.getElementsByTagName(CODIGO_OPERACION);
            this.idOperacion = this.parseLong(nl);

            nl = xmlPayload.getElementsByTagName(ID_ENMIENDA);
            this.idEnmienda = this.parseLong(nl);
            
            nl = xmlPayload.getElementsByTagName(CODIGO_PRODUCTO);
            this.idProducto = this.parseInt(nl);
            
            nl = xmlPayload.getElementsByTagName(ORIGEN_ENMIENDA);
            this.origen = this.parseInt(nl);
            
            nl = xmlPayload.getElementsByTagName(SOLICITUD_CONTRATACION);
            this.solicitudContratacion = this.parseBoolean(nl);
            
            nl = xmlPayload.getElementsByTagName(DESOBLIGACION);
            this.esDesobligacion = this.parseBoolean(nl);
            
            nl = xmlPayload.getElementsByTagName(CAMBIO_PLAZO);
            this.esCambioPlazo = this.parseBoolean(nl);
            
            nl = xmlPayload.getElementsByTagName(PA15Constants.PAYLOAD_PARAMETERTYPE_TAGNAME);
            this.mapMontosDesobligar = this.readParameterTypeDesobligar(nl, PA15Constants.PALYOAD_PREFIXDESOBLIGAR);
        }
    }

    /**
     * Método constructor. Lee el payload de la tarea actual y crea un objeto de esta clase.
     * @return Objeto de esta clase con los valores del payload extraídos.
     * @throws WorkflowException Si ocurre un error en la lectura del payload de la tarea actual.
     */
    public static FormalizacionEnmiendaPayloadReader readCurrentTaskPayload() throws WorkflowException {
        LOGGER.info("Inicia readCurrentTaskPayload: Leyendo FormalizacionEnmiendaPayload");
        
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();

        if (LOGGER.isInfo()) {
            //get the payload as a simple string, useful for debugging
            try {
                java.io.StringWriter writer = new java.io.StringWriter();
                xmlPayload.print(writer);
                String payloadAsString = writer.toString();
                LOGGER.info("Escribiendo FormalizacionEnmiendaPayload");
                LOGGER.info(payloadAsString);
            } catch (IOException ex) {
                LOGGER.severe("Error al escribir el Payload a este logger", ex);
            }
        }
        
        LOGGER.info("Finaliza readCurrentTaskPayload");

        return new FormalizacionEnmiendaPayloadReader(xmlPayload);
    }
    
    /**
     * Se encarga de leer el contenido del nodo y convertirlo a int.
     * @param nl Nodo de donde se leerá su contenido.
     * @return Valor convertido a int o -1 si no se encontró contenido u ocurrió un error en su conversión.
     */
    private int parseInt(NodeList nl) {
        int result = -1;

        if (nl.getLength() > 0) {
            String tmpTxt = nl.item(0).getTextContent();
            if (null != tmpTxt && !tmpTxt.isEmpty()) {
                try {
                    result = Integer.parseInt(tmpTxt);
                } catch (NumberFormatException ex) {
                    String msg = "Error leyendo Payload. No se pudo convertir a int: {0}";
                    LOGGER.log(Level.SEVERE, msg, new Object[] { tmpTxt }, ex);
                }
            }
        }

        return result;
    }

    /**
     * Se encarga de leer el contenido del nodo y convertirlo a long.
     * @param nl Nodo de donde se leerá su contenido.
     * @return Valor convertidfo a long o -1 si no se encontró contenido u ocurrió un error en su conversión.
     */
    private long parseLong(NodeList nl) {
        long result = -1;

        if (nl.getLength() > 0) {
            String tmpTxt = nl.item(0).getTextContent();
            if (null != tmpTxt && !tmpTxt.isEmpty()) {
                try {
                    result = Long.parseLong(tmpTxt);
                } catch (NumberFormatException ex) {
                    String msg = "Error leyendo Payload. No se pudo convertir a long: {0}";
                    LOGGER.log(Level.SEVERE, msg, new Object[] { tmpTxt }, ex);
                }
            }
        }

        return result;
    }

    /**
     * Se encarga de leer el contenido del nodo y convertirlo a boolean.
     * @param nl Nodo de donde se leerá su contenido.
     * @return Valor convertido a Boolean o false si no se encontró contenido u ocurrió un error en su conversión.
     */
    private boolean parseBoolean(NodeList nl) {
        boolean result = false;

        if (nl.getLength() > 0) {
            String tmpTxt = nl.item(0).getTextContent();
            if (null != tmpTxt && !tmpTxt.isEmpty()) {
                result = Boolean.parseBoolean(tmpTxt);
            }
        }

        return result;
    }
    
    /**
     * Lee parametros del payload donde se almacenan montos a desobligar.
     * @param nl Lista de nodos tipo ParameterType.
     * @param prefix Prefijo usado en el nombre del nodo para identificar los parametros de tipo Montos a Desobligar.
     * @return Mapa que contiene el ID (key) de la línea y su monto a desobligar (value). Si no se encuentran nodos con información de 
     * montos a desobligar, el mapa se retorna vacío.
     */
    private Map<Long, BigDecimal> readParameterTypeDesobligar(NodeList nl, String prefix){
        LOGGER.info("Dentro de readParameterTypeDesobligar");
        
        Map<Long, BigDecimal> map = new HashMap<>();

        if (nl != null && nl.getLength() > 0) {
            LOGGER.info("{0} ParameterType encontrados", nl.getLength());
            //Recorre todos los nodos tipo ParameterType
            for (int i = 0; i < nl.getLength(); i++) {
                Node param = nl.item(i);
                NodeList paramChildNodes = param.getChildNodes();
                //busca los nodos nombre y valor
                Node paramName = null;
                Node paramValue = null;
                for (int j = 0; j < paramChildNodes.getLength(); j++) {
                    String nodeName = paramChildNodes.item(j).getNodeName();
                    if (nodeName != null) {
                        if (nodeName.equalsIgnoreCase(PA15Constants.PAYLOAD_PARAMETERTYPE_NAME_NODE)) {
                            paramName = paramChildNodes.item(j);
                        } else if (nodeName.equalsIgnoreCase(PA15Constants.PAYLOAD_PARAMETERTYPE_VALUE_NODE)) {
                            paramValue = paramChildNodes.item(j);
                        }
                    }
                }

                if (LOGGER.isInfo()) {
                    if (paramName != null) {
                        String nameStr = paramName.getTextContent();
                        String valueStr = null;
                        if (paramValue != null) {
                            valueStr = paramValue.getTextContent();
                        }

                        LOGGER.info("{0}=>{1}", new Object[] { nameStr, valueStr });
                    }
                }

                //verifica si se encontró un parametro que guarda valores de desobligaciones para poderlo almacenar en el mapa de retorno
                if (paramName != null && paramName.getTextContent() != null &&
                    paramName.getTextContent().contains(prefix)) {

                    //recorta el ID de la línea de crédito
                    String completeName = paramName.getTextContent();
                    String lcStr = completeName.substring(prefix.length(), completeName.length());
                    Long idLc = null;
                    try {
                        idLc = Long.parseLong(lcStr);
                    } catch (NumberFormatException ex) {
                        String msg = "Error leyendo Payload. No se pudo convertir a long: {0}";
                        LOGGER.log(Level.SEVERE, msg, new Object[] { lcStr }, ex);
                        //si no se pudo leer el ID de la LC, no continuar con la lectura de este parametro
                        continue;
                    }

                    //Convierte el valor a desobligar, si lo tiene, a BigDecimal
                    BigDecimal montoDesobligar = null;
                    if (paramValue != null && paramValue.getTextContent() != null) {
                        try {
                            montoDesobligar = new BigDecimal(paramValue.getTextContent());
                        } catch (NumberFormatException ex) {
                            String msg = "Error leyendo Payload. No se pudo convertir a BigDecimal: {0}";
                            LOGGER.log(Level.SEVERE, msg, new Object[] { paramValue.getTextContent() }, ex);
                        }
                    }

                    map.put(idLc, montoDesobligar);
                }
            }
        } else {
            LOGGER.warning("No se encontraron ParameterTypes en el payload");
        }

        LOGGER.info("===Mapa de montos a desobligar leido del payload===");
        LOGGER.info("{0}", map);
        LOGGER.info("===================================================");

        return map;
    }

    public int getCodigoTarea() {
        return codigoTarea;
    }

    public long getIdOperacion() {
        return idOperacion;
    }

    public long getIdEnmienda() {
        return idEnmienda;
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public int getOrigen() {
        return origen;
    }

    public boolean isSolicitudContratacion() {
        return solicitudContratacion;
    }

    public boolean isDesobligacion() {
        return esDesobligacion;
    }

    public boolean isCambioPlazo() {
        return esCambioPlazo;
    }
    
    public Map<Long, BigDecimal> getMapMontosDesobligar(){
        return new HashMap<>(this.mapMontosDesobligar);
    }
}
