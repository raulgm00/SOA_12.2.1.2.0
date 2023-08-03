package mx.agarcia.ea.jda.config;

import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class ConfigFileImpl implements IConfigFile {
	private static Logger _log = Logger.getLogger("ConfigFile");
	private HashMap<String, ConfigEntries> configuration = new HashMap();
	private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder builder;
	private Document doc = null;
	private XPathFactory xpathFactory;
	private XPath xpath;

	public ConfigFileImpl() throws ConfigException {
		this.factory.setNamespaceAware(true);
		try {
			this.builder = this.factory.newDocumentBuilder();
			this.xpathFactory = XPathFactory.newInstance();
			this.xpath = this.xpathFactory.newXPath();
		} catch (Exception e) {
			_log.log(Level.SEVERE, "No es posible preparar lectura de archivo de configuraci�n", e);
			throw new ConfigException();
		}
	}

	public ConfigEntry getEntry(String keySet, String key) {
		ConfigEntry entry = null;
                
                Iterator itKey = this.configuration.keySet().iterator();
                String keyNext;
                
                
                /*System.out.println("************ <"+keySet+"::"+ key +">getEntry KEY SETS *****");
                while(itKey.hasNext()){
                    keyNext = (String)itKey.next();
                    System.out.println("Tiene esta llave ------------------> " +  keyNext  );
                }*/
                
		if (this.configuration.containsKey(keySet)) {
		   
                    
			entry = ((ConfigEntries) this.configuration.get(keySet)).getEntry(key);
		}
		return entry;
	}

	public ConfigEntries getEntries(String key) {
		ConfigEntries entrySet = (ConfigEntries) this.configuration.get(key);

		return entrySet;
	}

	public void readContent(File pFile) throws ConfigException {
		parseXmlContent(pFile);
	}

	private void parseXmlContent(File pFile) throws ConfigException {
		try {
			System.out.println("parseXmlContent: " + pFile.getAbsolutePath());
			this.doc = this.builder.parse(pFile);
			XPathExpression expr = this.xpath.compile("/Config/Entries");
			NodeList nodes = (NodeList) expr.evaluate(this.doc, XPathConstants.NODESET);

			_log.log(Level.FINE, "Cantidad de configuraciones " + nodes.getLength());
			System.out.println("Cantidad de configuraciones " + nodes.getLength());
			for (int i = 0; i < nodes.getLength(); i++) {
				if (1 == nodes.item(i).getNodeType()) {
					ConfigEntries configEntries = new ConfigEntries();
					Element configElement = (Element) nodes.item(i);

					configEntries.setId(configElement.getAttribute("id"));
					if ("list".equals(configElement.getAttribute("format"))) {
						configEntries.setList(true);
					} else {
						configEntries.setList(false);
					}
					NodeList childrenNodes = configElement.getChildNodes();

/*
					System.out.println("Entries @Id=" + configEntries.getId() + " List?" + configEntries.isList()
							+ " Nodos=" + childrenNodes.getLength());
                                        */
					for (int j = 0; j < childrenNodes.getLength(); j++) {
						if (1 == childrenNodes.item(j).getNodeType()) {
							Element entryElement = (Element) childrenNodes.item(j);
							ConfigEntry configEntry = new ConfigEntry();
							configEntry.setId(entryElement.getAttribute("id"));
							configEntry.setType(entryElement.getAttribute("type"));

							configEntry.setValue(entryElement.getTextContent());

/*
							System.out.println(j + "........ >Entry: getId=" + configEntry.getId() +
                                                                           "/getType=" + configEntry.getType() + 
                                                                           "/getValue=" + configEntry.getValue());*/

							configEntries.addConfigEntry(configEntry);
						}
					}
                                        /*
					System.out.println("Agregando config " + configEntries.getId() + "  entries ->"
							+ configEntries.getEntries().size());
                                        */
					this.configuration.put(configEntries.getId(), configEntries);
				}
			}
		} catch (Exception e) {
			_log.log(Level.SEVERE, "No es posible leer archivo de configuraci�n " + pFile.getAbsolutePath(), e);
			throw new ConfigException();
		}
	}
}
