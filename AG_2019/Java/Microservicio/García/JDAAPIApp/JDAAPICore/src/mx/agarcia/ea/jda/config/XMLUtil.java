package mx.agarcia.ea.jda.config;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XMLUtil {

    public static void writeXmlDocumentToString(Document xmlDocument) {
        
        if (xmlDocument != null) {
            System.out.println("[XML UTIL xml test] " + xmlDocument.getFirstChild().getNodeName());
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer;
            try {
                transformer = tf.newTransformer();

                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
                String xmlString = writer.getBuffer().toString();
                System.out.println("··························· ______________________");
                System.out.println(xmlString);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("[XML UTIL test] DOCUMENTO VACÍO ");

        }


    }
}
