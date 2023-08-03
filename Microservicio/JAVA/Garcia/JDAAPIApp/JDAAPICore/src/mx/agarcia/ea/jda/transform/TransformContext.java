package mx.agarcia.ea.jda.transform;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class TransformContext {
    private DocumentBuilder documentBuilder;
    
    public TransformContext(){
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    private String interfaceId;

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setDocumentBuilder(DocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }

    public DocumentBuilder getDocumentBuilder() {
        return documentBuilder;
    }
}
