package mx.agarcia.ea.jda.core;

import java.io.File;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

import javax.xml.xpath.XPathFactory;

import mx.agarcia.ea.jda.APIException;
import mx.agarcia.ea.jda.config.ConfigEntries;
import mx.agarcia.ea.jda.config.ConfigEntry;
import mx.agarcia.ea.jda.config.ConfigException;
import mx.agarcia.ea.jda.config.ConfigFileImpl;
import mx.agarcia.ea.jda.core.model.Request;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract class ConnectAction<I, O, C> extends IConnectAction<I, O, C> {

    protected static Logger _internalLogger = Logger.getLogger("JDAAction");

    private String id;

    protected Document xmlRequestData;

    private static Logger _log = Logger.getLogger("ConnectAction");
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder;
    private Document doc = null;
    private XPathFactory xpathFactory;
    private XPath xpath;

    public ConnectAction() /*throws ConfigException */{
        this.factory.setNamespaceAware(true);
        try {
            this.builder = this.factory.newDocumentBuilder();
            this.xpathFactory = XPathFactory.newInstance();
            this.xpath = this.xpathFactory.newXPath();
        } catch (Exception e) {
            _log.log(Level.SEVERE, "No es posible preparar lectura de XMLs de configuraci�n", e);
            //throw new ConfigException();
        }
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    protected void info(String msg) {
        System.out.println("[INFO] " + msg);
    }

    protected void debug(String msg) {
        System.out.println("[DEBUG] " + msg);
    }

    protected void error(String msg, Throwable th) {
        System.err.println("[ERROR] " + msg);
        th.printStackTrace();
    }

    protected void config(String msg) {
        System.out.println("[CONFIG] " + msg);
    }

    protected Document parseInput(Source data) throws APIException {

        try {
            this.debug("Haciendo parsing de XML Request: " + data);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            StreamSource streamSource = (StreamSource) data;

            xmlRequestData = builder.parse(streamSource.getInputStream());
        } catch (Exception e) {
            this.error("No es posible hacer parsing de request en Action", e);

            throw new APIException();
        }
        return xmlRequestData;
    }

    protected Document parseDocument(Document data) throws APIException {

        xmlRequestData = data;
        return xmlRequestData;
    }

    protected HashMap<String, String> parseSQLParameters(String filterId) throws ConfigException {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parseXmlParametersContent(filterId, parameters);

        return parameters;
    }


    private void parseXmlParametersContent(String parameterSetId,
                                           HashMap<String, String> parameters) throws ConfigException {
        try {


            if (xmlRequestData != null) {
                System.out.println("parseXmlParametersContent: " + this.xmlRequestData);
                String iPath = "/Request/Parameters/Parameter";
                if (parameterSetId != null) {
                    iPath = "/Request/Parameters[@id='" + parameterSetId + "']/Parameter";
                }
                System.out.println("<" + parameterSetId  + "> Buscando parametros " +  iPath);

                XPathExpression expr = this.xpath.compile(iPath);
                NodeList nodes = (NodeList) expr.evaluate(this.xmlRequestData, XPathConstants.NODESET);

                _log.log(Level.FINE, "<" + iPath + "> Cantidad de parametros " + nodes.getLength());
                System.out.println("<" + iPath + ">Cantidad de parametros " + nodes.getLength());
                for (int i = 0; i < nodes.getLength(); i++) {
                    if (1 == nodes.item(i).getNodeType()) {

                        Element parameterElement = (Element) nodes.item(i);
                        System.out.println("parseXmlParametersContent::Agregando parametro " +
                                           parameterElement.getAttribute("name") + " ->" +
                                           parameterElement.getTextContent() );
                        parameters.put(parameterElement.getAttribute("name"), parameterElement.getTextContent());

                    }
                }
            } else {
                System.err.println("parseXmlParametersContent NO HAY XML DE ENTRADA A ESTE ACTION ");
            }

        } catch (Exception e) {
            e.printStackTrace();
            _log.log(Level.SEVERE, "No es posible leer mapa de parametros (@ID=" + parameterSetId + ") ", e);
            throw new ConfigException();
        }
    }


}
