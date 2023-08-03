package mx.agarcia.ea.jda.transform;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import mx.agarcia.ea.jda.config.ConfigException;
import mx.agarcia.ea.jda.config.ConfigFilesManager;
import mx.agarcia.ea.jda.core.ActionException;
import mx.agarcia.ea.jda.core.ConnectAction;

import mx.agarcia.ea.jda.core.IConnectAction;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class TransformAction extends ConnectAction<TransformPayload, TransformResponse, TransformContext> {
    DocumentBuilder docBuilder;
    TransformerFactory transformerFactory;
    Transformer transformer;
    StreamSource stylesource;
    File xslFile;



    public TransformAction(String id, DocumentBuilderFactory factory, File xslFile) throws ConfigException {
        try {
            super.setId(id);

            this.docBuilder = factory.newDocumentBuilder();

            this.transformerFactory = new net.sf.saxon.TransformerFactoryImpl();
                //TransformerFactory.newInstance();
/*
            if (transformerFactory.getFeature(SAXTransformerFactory.FEATURE)) {
                SAXTransformerFactory saxTransFact = (SAXTransformerFactory) transformerFactory;
                super.debug( "SAXTransformerFactory "  + saxTransFact + " downcast possible");
            }*/


            this.xslFile = xslFile;

            this.stylesource = new StreamSource(this.xslFile);

            this.transformer = this.transformerFactory.newTransformer(this.stylesource);




            super.config("Action @" + id + " construido");
        } catch (Exception e) {//ParserConfigurationException | TransformerConfiguration
            super.info(id + "- ERROR - No es posible aplicar transformación ////////////////////////////");
            e.printStackTrace();
            
            super.error(id + "- Error configurando action para transformaciones", e);
            throw new ConfigException();
        }
    }
    
    

    public TransformResponse execute(TransformContext context, TransformPayload request) throws ActionException {
        TransformResponse response = new TransformResponse();
        try {
            super.info("Iniciando transformación " + context.getClass() + " ...");
            DOMSource source = new DOMSource(request.getCurrentDoc());
            //StreamResult result = new StreamResult(System.out);
            //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //StreamResult result = new StreamResult( byteArrayOutputStream );
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            this.transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            this.transformer.transform(source, result);

            String dataResult = result.getWriter().toString();
            super.info( "XML RESULT --->\n\n\n" + dataResult + "\n\n");
            
            Document xmlResult = context.getDocumentBuilder().parse(
                new java.io.ByteArrayInputStream(dataResult.getBytes()));

            response.setDocument(xmlResult);
            super.info("Transformaci�n finalizada: " + dataResult);
        } catch (TransformerException e) {
            super.error("No es posible ejecutar transformaci�n de datos", e);
        } catch (IOException ex) {
            super.error("No es posible ejecutar transformaci�n de datos", ex);
        } catch (SAXException ex) {
            super.error("No es posible ejecutar transformaci�n de datos", ex);
        }
        return response;
    }
    
    
    public static void main(String args[]) throws Exception {
        DocumentBuilderFactory documentBuilderFactory;
        documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        IConnectAction<TransformPayload, TransformResponse, TransformContext> action = new TransformAction(
                        "Transform" , documentBuilderFactory,
         
                        new File(PATH, "createStyle.xslt" )
            );
        TransformContext executionContext = new TransformContext();
        TransformPayload payload = new TransformPayload();
        payload.setCurrentDoc( getSampleDoc() );
        System.out.println("[DEBUG] Iniciando ejecución de Transform");

        action.execute(executionContext, payload);
        
    }
    
              
    private static Document getSampleDoc() throws Exception {
        return convertStringToXMLDocument( readSampleData());
    }
    

    public static String readSampleData() throws Exception {
        StringBuffer data = new StringBuffer();
        //FileReader reader = new FileReader( "/Proyectos/AGEAI/VendorDesc.xml" );
        
        data.append( new String(Files.readAllBytes(  Paths.get( PATH + "CreateStyleSampleRequest.xml") )) );
        
        return data.toString();
    
    }
    
    private static final String PATH = "/Proyectos/AGEAI/apijda/JDAAPIApp/JDAAPICore/src/META-INF/config/";
    
    
    
    private static Document convertStringToXMLDocument(String xmlString)
       {
           //Parser that produces DOM object trees from XML content
           DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
           //API to obtain DOM Document instance
           DocumentBuilder builder = null;
           try
           {
               //Create DocumentBuilder with default configuration
               builder = factory.newDocumentBuilder();
                
               //Parse the content to Document object
               Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
               return doc;
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }
           return null;
       }
}
