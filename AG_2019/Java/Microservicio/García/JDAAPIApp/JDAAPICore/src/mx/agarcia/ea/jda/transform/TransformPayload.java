package mx.agarcia.ea.jda.transform;

import org.w3c.dom.Document;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class TransformPayload {
    private String interfaceId;

    private Document currentDoc;

    public Document getCurrentDoc() {
        return this.currentDoc;
    }

    public void setCurrentDoc(Document currentDoc) {
        this.currentDoc = currentDoc;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }
}
