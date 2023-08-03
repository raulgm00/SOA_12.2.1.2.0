package mx.agarcia.ea.jda.core.pricechange;

import com.ibm.as400.access.AS400;

import com.ibm.as400.access.AS400BidiTransform;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.IFSFile;

import com.ibm.as400.access.IFSTextFileOutputStream;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import mx.agarcia.ea.jda.APIException;
import mx.agarcia.ea.jda.config.PropertyConfig;
import mx.agarcia.ea.jda.config.PropertyConstants;
import mx.agarcia.ea.jda.core.model.IResponse;
import mx.agarcia.ea.jda.core.model.Request;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;

@Stateless
@LocalBean
@WebService(name = "SavePriceChangeMicroTask", serviceName = "SavePriceChangeMicroTaskSvc", portName = "SavePriceChangeMicroTaskPort")
public class SavePriceChangeMicroTask implements SavePriceChangeMicroTaskLocal, SavePriceChangeMicroTaskRemote{

    private static Logger _log = Logger.getLogger("ConnectProxy");
 
//"yyyy-MM-dd_HH-mm-ss
    static SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
    static SimpleDateFormat formatInput = new SimpleDateFormat("yyyy-MM-dd");
    static DecimalFormat decimalFormat = new DecimalFormat( "####" );

    
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder;
    private XPathFactory xpathFactory;
    private XPath xpath;
    private String testFilePath = "/Proyectos/AGEAI/apijda/JDAAPIApp/JDAPriceChangeUtil/src/mx/agarcia/ea/jda/pricechange/TestEvent.xml";

    private AS400BidiTransform as400Transform;

    private String as400SYSName;
    private String as400IP;
    private String as400QDLSPath;
    private Document doc = null;


    public SavePriceChangeMicroTask(){
        as400SYSName = PropertyConfig.getProperty( PropertyConstants.AS400_SYSNAME);
        as400IP = PropertyConfig.getProperty( PropertyConstants.AS400_IP );
        as400QDLSPath = PropertyConfig.getProperty( PropertyConstants.AS400_QDLS_PATH );
        as400Transform = new AS400BidiTransform(819);
        try {
            config();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    private static String convertToHex( String eventID ){
        String hex = eventID;
        try {
            //String.
            hex = Long.toHexString( Long.parseLong( eventID ) );
       } catch (Exception e) {
            e.printStackTrace();
        }
        return hex;  
    }


    @WebMethod
    public  String saveEvent( @WebParam(name = "eventID") String eventID, @WebParam(name = "priceChangeData")String priceChangeData)
    throws APIException {
        String result = "0";
        //\\192.1.103.51\qdls\EVENT

        String fileName = "P@" + convertToHex( eventID ) + ".csv";
        
        log( fileName + ":\n" + priceChangeData );
        
        try {
            String data = parseXmlContent( doc, priceChangeData );

            try {
               log( " ------------------------------ " +  fileName + " escribiendo archivo local");
               writePriceChangeFile(fileName, data);
           } catch (Exception e) {
                result = "-1|writePriceChangeFile:" + e.getMessage();
                e.printStackTrace();
            }
            
            try {
                log( " ------------------------------ " +  fileName + " escribiendo archivo AS400");
               writePriceChangeFileToAS400(fileName, data);
            } catch (Exception e) {
                result += "-1|writePriceChangeFileToAS400:" + e.getMessage();
                e.printStackTrace();
            }
            result = "0";
            //
            //
        } catch (Exception e) {
            
            log(  "---------------------------  AS400_ERROR_QDLS  -------------------------------------["+
                  e.getMessage()  + "] " );
            
            //System.out.println(e);
            //System.err.println(e);
            
            System.err.println("[AS400] No es posible escribir archivo de JDA "+ fileName);

            result += "-1|" + e.getMessage();

            e.printStackTrace(System.out);
        }


        return result;
    }


    public static void main(String[] args) throws Exception {
        
        /*String convertedFecIni = "", convertedFecFin = "";
            convertedFecIni = format.format( formatInput.parse( "2019-08-06T00:00:00.000-05:00" ) ) ;

        System.out.println( "convertedFec" + convertedFecIni  );*/
        
        
       // config();
        /*
        //saveFile( "PRUEBA" );
        String fileContent = readFileContent( testFilePath );
        log( "Archivo "+testFilePath+" leido.." );
        log("\n");
        String data = parseXmlContent( fileContent );
        //savePriceChangeFile( "0", data );
        */
        
        System.out.println(convertToHex( "9999999" )  );
    }
    

    private void config() throws Exception {
        factory.setNamespaceAware(true);
        builder = factory.newDocumentBuilder();
        log( "UTIL configurado correctamente" );
    }    
    


    public static void log(String message) {
        // 
        System.out.println("[AGAS400:API] " + message);

    }


    private static String parseXmlContent(Document doc, String data) throws Exception {
        
        log(  "---------------------------  AS400_QDLS_DATA  -------------------------------------" );
        log( data );
        
        String eventName = "";
        String eventType = "";
        String startDate = "";
        String finishDate = "";
        String eventLevel = "";
        String supplierId = "", style = "", sku = "", price = "", percent = "", changeId = "", itemType = "";
        
        
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        
        //log("parseXmlContent: " + pFile.getAbsolutePath());
        StringBuffer buffer = new StringBuffer();
        doc = convertStringToXMLDocument( data );
        XPathExpression changePricesExpr = xPath.compile( "/event/changes/change" );
        XPathExpression retailStoreExpr = xPath.compile( "/event/retailStores/retailStore" );
        XPathExpression businessUnitsExpr = xPath.compile( "/event/businessStores/businessUnit" );
        XPathExpression scopeLevelExpr = xPath.compile( "/event/scope" );
        
        XPathExpression effectiveDateExp = xPath.compile( "/event/effectiveDate" );
        XPathExpression expirationDateExp = xPath.compile( "/event/expirationDate" );
        XPathExpression eventNameExp = xPath.compile( "/event/idType/@Name" );
        XPathExpression eventTypeExp = xPath.compile( "/event/idType/@type" );
        
        Node effectiveDateNode = (Node) effectiveDateExp.evaluate(doc, XPathConstants.NODE);
        Node expirationDateNode = (Node) expirationDateExp.evaluate(doc, XPathConstants.NODE);
        Node eventNameNode = (Node) eventNameExp.evaluate(doc, XPathConstants.NODE);
        Node eventTypeNode = (Node) eventTypeExp.evaluate(doc, XPathConstants.NODE);
        
        if( effectiveDateNode != null ){
            startDate = effectiveDateNode.getTextContent();
        }
        if( expirationDateNode != null ){
            finishDate = expirationDateNode.getTextContent();
        }
        if( eventNameNode != null ){
            eventName = eventNameNode.getTextContent();
        }
        if( eventTypeNode != null ){
            eventType = eventTypeNode.getTextContent();
        }

        NodeList scopeNodes = (NodeList) scopeLevelExpr.evaluate( doc, XPathConstants.NODESET);
        Element scopeElement ;
        
        
        
        if( scopeNodes != null && scopeNodes.getLength() > 0 ){
            for (int j = 0; j < scopeNodes.getLength(); j++) {
               scopeElement = (Element) scopeNodes.item(j);
                if( "S".equals( scopeElement.getTextContent() )  ){
                    eventLevel = "S";   
                } else {
                    eventLevel = "C";
                }
           }

            
        }
        
        buffer.append( buildRecordType1(eventName,  eventType, startDate, finishDate,  eventLevel)  + "\n"); 
    


        NodeList storesNodes = (NodeList) retailStoreExpr.evaluate( doc, XPathConstants.NODESET);
        Element storeElement ;

        if(storesNodes != null && "S".equals(eventLevel))
            for (int i = 0; i < storesNodes.getLength(); i++) {
                storeElement = (Element) storesNodes.item(i);
                NodeList storeDataElement = storeElement.getChildNodes();
                
                for (int j = 0; j < storeDataElement.getLength(); j++) {
                    if (1 == storeDataElement.item(j).getNodeType()) {
                        Element entryElement = (Element) storeDataElement.item( j );
                        /*log( "RetailStore["+ j +"]" + entryElement.getNodeName() + "=" + entryElement.getTextContent() + "--check:" +
                              entryElement.getNodeName().indexOf( "storeID"  ));*/
                        if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "storeID"  ) >= 0 &&
                                entryElement.getTextContent() != null && entryElement.getTextContent() != "" ){
                            buffer.append( buildRecordType2(entryElement.getTextContent()) + "\n" );
                        }
                    }
                }
                
            
            }
        
        
        NodeList changesNodes = (NodeList) changePricesExpr.evaluate( doc, XPathConstants.NODESET);


        NodeList idNodeList;
        
        Element changePriceElement, itemElement ;
        if(changesNodes != null) {
            log("Cantidad de líneas de  cambio de precios: ----------------------- " + changesNodes.getLength());
            for (int i = 0; i <= changesNodes.getLength(); i++) {
                supplierId = ""; style = ""; sku = ""; price = ""; percent = ""; changeId = ""; eventName = ""; itemType = "";
                if (changesNodes.item(i) != null && (1 == changesNodes.item(i).getNodeType())) {
                    changePriceElement = (Element) changesNodes.item(i);
                    log(i + " cantidad de info en price change: " + changePriceElement.getChildNodes().getLength() );
    
                    NodeList childrenNodes = changePriceElement.getChildNodes();
    
                    for (int j = 0; j < childrenNodes.getLength(); j++) {
                        
                        supplierId = ""; style = ""; sku = ""; //price = ""; percent = "";

                        if (1 == childrenNodes.item(j).getNodeType()) {
                            Element entryElement = (Element) childrenNodes.item( j );
                            log( "PriceChange["+ j +"]" + entryElement.getNodeName() + "=" + entryElement.getTextContent() );

                            /*log( "\tid [Check:"+ entryElement.getNodeName().indexOf( "id"  ) +"]");
                            log( "\tname [Check:"+ entryElement.getNodeName().indexOf( "name"  ) +"]");
                            log( "\tprice [Check:"+ entryElement.getNodeName().indexOf( "price"  ) +"]");
                            log( "\tpercentage [Check:"+ entryElement.getNodeName().indexOf( "percentage"  ) +"]");
                            log( "\titem [Check:"+ entryElement.getNodeName().indexOf( "item"  ) +"] item");*/

                            if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "id"  ) >= 0 ){

                                changeId = entryElement.getTextContent();
                            } else if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "name"  ) >= 0 
                                && entryElement.getTextContent() != null && entryElement.getTextContent() != "" 
                            
                            ){
                                eventName = entryElement.getTextContent();
                            } else if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "price"  ) >= 0 
                                && entryElement.getTextContent() != null && entryElement.getTextContent() != "" 
                            
                            ){
                                price = entryElement.getTextContent();
                            } else if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "percentage"  ) >= 0 
                                && entryElement.getTextContent() != null && entryElement.getTextContent() != "" 
                            
                            ){
                                percent = entryElement.getTextContent();
                                try{
                                    if( !(percent != null &&  percent != "" && Long.valueOf(percent ) > 0) ){
                                            percent = "";
                                        }    
                                } catch (Exception e){}
                                
                            } else if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "item"  ) >= 0 
                                && entryElement.getTextContent() != null && entryElement.getTextContent() != "" 
                            
                            ){
                                
                                idNodeList = entryElement.getChildNodes();
                                
                                for (int z = 0; z < idNodeList.getLength(); z++) {
                                    if (1 == idNodeList.item(z).getNodeType()) {
                                        itemElement =(Element) idNodeList.item( z );
                                        //log( "\tItem["+z+"]  --> SubElement=" + itemElement.getNodeName() );
                                        if( itemElement.getNodeName() != null && itemElement.getNodeName().indexOf( "id"  ) >= 0 ){
                                            itemType = itemElement.getAttribute( "Type" ) ;
                                            //log("\t\titemType="+ itemType);
                                            if( "Style".equals( itemType ) ){
                                                style = itemElement.getTextContent();
                                            } else {
                                                sku = itemElement.getTextContent();
                                            }                                            
                                        } else if( itemElement.getNodeName() != null && itemElement.getNodeName().indexOf( "internalID"  ) >= 0 ) {
                                            supplierId = itemElement.getTextContent();
                                        }
                                    }
                                }
                                    
                                
                                
                            }

                        }
                    }
                    //log( " **** --------------------------------- supplierId=" + supplierId + "style:sku" + style  + ":" + sku);
                    buffer.append( buildRecordType3(supplierId,  style,  sku,  price,  percent) + "\n" );
                }
            }
    }
        
        return buffer.toString();
    }


    private static String buildRecordType1(String eventName, String eventType, String startDate, String finishDate, 
                                           String eventLevel){
        
        String evPrpl = "1";
        
        /*String jdaEventType = "R";*/
        
        if( "L".equals( eventType ) ){
            eventType = "R";
        }
        
        if( "P".equals( eventType ) ){
            evPrpl = "";
        }
        
       // eventType = jdaEventType;
        
        /*if( "P".equals( eventType ) ){
            jdaEventType = "P";
        } */
        
        if( eventLevel == null ){
            eventLevel = "C";
        }
            
        String convertedFecIni = "", convertedFecFin = "";
        try{
            convertedFecIni = format.format( formatInput.parse(startDate) ) ;
        } catch(Exception ex){
            convertedFecIni = "150719";
        }
        try{
            if (finishDate != null && finishDate != ""){
                convertedFecFin = format.format( formatInput.parse(finishDate) ) ;
            } 
        } catch(Exception ex){
            convertedFecFin = "150719";
        }        
        
        return 
            "EVDESC,EVTIPO,FECINI,FECFIN,EVRAZO,EVNIVE,EVPROV,EVSTYLE,EVSTOR,EVSKU,EVPREC,EVPORC,EVPRPL,EVTREC,EVSEC\n" + 
            "\"" + eventName + "\"" +
            ","+eventType+","+convertedFecIni+","+convertedFecFin+",3,"+eventLevel+",,,,,,,"+ evPrpl +",1,";
        
        
        
    }




    private static String buildRecordType2(String retailStore){
        cleanNumberToInteger( retailStore );
        return ",,,,,,,,"+retailStore+",,,,,2,";
    }

    private static String buildRecordType3(String supplierId, String style, String sku, String price, String percent){
        cleanNumberToInteger( supplierId );
        
        return ",,,,,,"+supplierId+","+style+",,"+sku+","+price+","+percent+",,3,";
    }


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
    
    private static void cleanNumberToInteger(String data){
        if( data != null && !"".equals( data )  ){
            data = decimalFormat.format( Double.parseDouble( data ) );
        }
    }

    
    private static String readFileContent(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
     
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }    
    
    
    
    
    private void writePriceChangeFile(String fileName, String fileContent) throws IOException {
        log( this.as400QDLSPath + "/" + fileName  );
        
        FileWriter fw=new FileWriter( this.as400QDLSPath + "/" + fileName  );    
        fw.write(fileContent);    
        fw.close();    
    }
    
    private void writePriceChangeFileToAS400(String fileName, String fileContent) throws Exception {
        //b10b829
        //log( "Guardando a AS400" );
        AS400 as400 = new AS400("192.1.103.50", "GSISSOLIT1", "GSISSOLIT1");
        log( "Guardando a AS400 --> " + as400.getGSSName() + ":" + as400.getRelease() );
        //IFSFile file = new IFSFile(as400, "/qdls/EVENT/" + fileName   );
        IFSTextFileOutputStream file = new IFSTextFileOutputStream(as400, "/qdls/EVENT/" + fileName);
        String transformedData = as400Transform.toAS400Layout( fileContent );
        log( fileName + " transformed data:\n\n" + transformedData );

        //log( "\n  $$$$$$$ AS400 $$$$$$$$$$$$$$$$$" );
        log( transformedData );

        //log( "\n" );

        file.write( transformedData );
        
        file.close();
    }
    
    
    
    
}
