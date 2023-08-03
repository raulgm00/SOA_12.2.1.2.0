package pc05formalizaciongenerichumantask.util;

import java.io.IOException;

import java.util.Collections;

import java.util.logging.Level;

import oracle.adf.share.logging.ADFLogger;

import oracle.bpel.services.workflow.WorkflowException;

import oracle.xml.parser.v2.XMLElement;

import org.bcie.fenix.common.utils.BPMUtils;

import org.w3c.dom.NodeList;

public final class PayloadReader {
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(PayloadReader.class);
    public PayloadReader() {
        super();
    }
    
    /**
     * Constructor de esta clase.
     * No permite creaciÃ³n directa de objetos.
     * Se espera que el cliente use el mÃ©todo constructor.
     * @param xmlPayload Payload de donde se extraerÃ¡n los valores de los atributos de esta clase.
     * @see #readCurrentTaskPayload()
     */
    private PayloadReader(XMLElement xmlPayload) {

        if (xmlPayload == null) {
            LOGGER.warning("Invocación de FormalizacionEnmiendaPayloadReader sin payload para leer sus atributos."); 
        } else {
            LOGGER.warning("Invocación de FormalizacionEnmiendaPayloadReader correcto."); 
            NodeList nl;

            //nl = xmlPayload.getElementsByTagName(CODIGO_TAREA);
            //this.codigoTarea = this.parseInt(nl);

        }
    }

    /**
     * Método constructor. Lee el payload de la tarea actual y crea un objeto de esta clase.
     * @return Objeto de esta clase con los valores del payload extraídos.
     * @throws WorkflowException Si ocurre un error en la lectura del payload de la tarea actual.
     */
    public static PayloadReader readCurrentTaskPayload() throws WorkflowException {
        LOGGER.info("Inicia readCurrentTaskPayload: Leyendo PayloadReader");
        
        XMLElement xmlPayload = BPMUtils.getPayloadInformation();

        if (LOGGER.isInfo()) {
            //get the payload as a simple string, useful for debugging
            try {
                java.io.StringWriter writer = new java.io.StringWriter();
                xmlPayload.print(writer);
                String payloadAsString = writer.toString();
                LOGGER.info("Escribiendo PayloadReader");
                LOGGER.info(payloadAsString);
            } catch (IOException ex) {
                LOGGER.severe("Error al escribir el Payload a este logger", ex);
            }
        }
        
        LOGGER.info("Finaliza readCurrentTaskPayload");

        return new PayloadReader(xmlPayload);
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
    
}
