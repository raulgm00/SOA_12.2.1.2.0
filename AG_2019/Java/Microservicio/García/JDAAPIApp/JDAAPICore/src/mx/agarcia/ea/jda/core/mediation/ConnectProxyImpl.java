package mx.agarcia.ea.jda.core.mediation;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import mx.agarcia.ea.jda.APIException;
import mx.agarcia.ea.jda.config.XMLUtil;
import mx.agarcia.ea.jda.core.IConnectChain;
import mx.agarcia.ea.jda.core.TransactionProcessorFacade;
import mx.agarcia.ea.jda.core.execution.ExecutionPayload;
import mx.agarcia.ea.jda.core.model.Header;
import mx.agarcia.ea.jda.core.model.IResponse;
import mx.agarcia.ea.jda.core.model.Request;
import mx.agarcia.ea.jda.core.model.ResponseImpl;
import mx.agarcia.ea.jda.transform.TransformChainManager;
import mx.agarcia.ea.jda.transform.TransformPayload;
import mx.agarcia.ea.jda.transform.TransformResponse;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.xml.sax.InputSource;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
@Stateless
@LocalBean
@WebService(name = "ConnectProxy", serviceName = "ConnectSvc", portName = "ConnectPort")
//@Asynchronous
public class ConnectProxyImpl implements ConnectProxyRemote, ConnectProxyLocal {

    private static Logger _log = Logger.getLogger("ConnectProxy");

    @EJB
    private TransactionProcessorFacade facade;

    @WebMethod
    public IResponse process(@WebParam(name = "request") Request request) throws APIException {
        System.out.println("Solicitud recibida: " + request.getPayload());

        _log.log(Level.FINE, "Solicitud recibida: " + request.getPayload());

        IResponse response = new ResponseImpl();

        response.setId("@" + System.currentTimeMillis());
        response.setSuccess(Boolean.FALSE);

        System.out.println("IConnectChain para interface " + request.getHeader().getInterfaceId());

        IConnectChain<TransformPayload, TransformResponse> globalTransformChain =
            TransformChainManager.getInstance().build(request.getHeader().getInterfaceId());

        TransformPayload payload = new TransformPayload();
        payload.setInterfaceId(request.getHeader().getInterfaceId());

        Document payloadDocument;
        try {

            payloadDocument = parseSource2Document( request.getPayload() );
            payload.setCurrentDoc(payloadDocument);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            response.setCode("JDA-INVALID-PAYLOAD");
            response.setMessage("No es posible interpretar payload." + sw.toString());
            return response;
        }

        System.out.println("Ejecutando IConnectTransformChain para interface " + request.getHeader().getInterfaceId());

        //TransformResponse transformResponse = globalTransformChain.apply(payload);

        //System.out.println("Global payload @" + request.getHeader().getInterfaceId() + ":" + transformResponse);
        //Source source = new DOMSource ( transformResponse.getDocument() );


        ExecutionPayload executionPayload = new ExecutionPayload();
        executionPayload.getHeader().setParameters(request.getHeader().getParameters());
        //executionPayload.setData(  transformResponse.getDocument() );
        //executionPayload.setPayload(payload); (  transformResponse.getDocument() );

        System.out.println(" ¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿ set document payload "+ payloadDocument +" ?????????????????? ");


        /*
         * TODO: jmoreno 21/May
         * Se esta pasando data pero se usa payload!!!
         */
        executionPayload.setData(payloadDocument);

        XMLUtil.writeXmlDocumentToString(payloadDocument);


        executionPayload.setInterfaceId(request.getHeader().getInterfaceId());
        executionPayload.getHeader().setInterfaceId(request.getHeader().getInterfaceId());

        System.out.println("Iniciando Execution : " + executionPayload.getInterfaceId() + facade);

        executionPayload.print();
        response = facade.execute(executionPayload);

        response.setSuccess(Boolean.TRUE);

        return response;
    }


    private Document parseXml() {
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            File xml = new File("C:/Proyectos/config/persons.xml");

            DocumentBuilder builder = factory.newDocumentBuilder();

            document = builder.parse(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    private Document parseInput2(Request request) throws APIException {
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // File xml = new File("C:/Proyectos/config/persons.xml");
            DocumentBuilder builder = factory.newDocumentBuilder();

            StreamSource streamSource = (StreamSource) request.getPayload();

            document = builder.parse(streamSource.getInputStream());
        } catch (Exception e) {

            e.printStackTrace();
            throw new APIException();
        }
        return document;
    }

    private Document parseRawInput2(Request request) throws APIException {
        try {
            DOMResult dom = new DOMResult();
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.transform(request.getPayload(), dom);

            Node node = dom.getNode();
            // do something with it ...
            DOMSource src = new DOMSource(node);


            StringWriter requestXmlWriter = new StringWriter();
            trans.transform(request.getPayload(), new StreamResult(requestXmlWriter));
            String requestXml = requestXmlWriter.toString();

            System.out.println("$$$$$$$$$$$$$$$$$  XML DATA -------------");
            System.out.println(requestXml);

            return node.getOwnerDocument();

        } catch (Exception e) {

            e.printStackTrace();
            throw new APIException();
        }
    }


    private Document parseSource2Document(Source payload) {

        Document doc = null;

        try {
            DOMResult dom = new DOMResult();
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.transform(payload, dom);

            Node node = dom.getNode();
            // do something with it ...
            DOMSource src = new DOMSource(node);


            StringWriter requestXmlWriter = new StringWriter();
            trans.transform(payload, new StreamResult(requestXmlWriter));
            String requestXml = requestXmlWriter.toString();

            System.out.println("################  XML DATA =================");
            System.out.println( requestXml );
            
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream( new StringReader( requestXml ));

            doc = db.parse(is);
           
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

}
