package mx.agarcia.ea.jda.pricechange;


import java.io.BufferedReader;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;


/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class SavePriceChangeUtil {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private static  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder builder;
    private static Document doc = null;
    private static XPathFactory xpathFactory;
    private static XPath xpath;


    private static String testFilePath = "/Proyectos/AGEAI/apijda/JDAAPIApp/JDAPriceChangeUtil/src/mx/agarcia/ea/jda/pricechange/TestEvent.xml";


    public static String saveFile(String as400SYSName, String as400IP, String directory, String eventID, String priceChangeData) {
        String result = "0";
        //\\192.1.103.51\qdls\EVENT

        String fileName = "AGCP-" + eventID + "_" + format.format(new Date());
        try {
            String data = parseXmlContent( priceChangeData );

            writePriceChangeFile(directory + "/" + fileName, data);
            //AS400 as400 = new AS400(as400SYSName);
            //IFSFile file = new IFSFile(as400, "/qdls/EVENT/" + fileName   );
            //IFSTextFileOutputStream file = new IFSTextFileOutputStream(as400, "/qdls/EVENT/" + fileName);
            //
            //
        } catch (Exception e) {
            System.err.println("[AS400] No es posible escribir archivo de JDA "+ fileName);

            result = "-1|" + e.getMessage();

            e.printStackTrace();
        }


        return result;
    }


    public static void main(String[] args) throws Exception {
        config() ;
        //saveFile( "PRUEBA" );
        String fileContent = readFileContent( testFilePath );
        log( "Archivo "+testFilePath+" leido.." );
        log("\n");
        String data = parseXmlContent( fileContent );
        //savePriceChangeFile( "0", data );
        
    }
    

    private static void config() throws Exception {
        factory.setNamespaceAware(true);
        builder = factory.newDocumentBuilder();
        xpathFactory = XPathFactory.newInstance();
        xpath = xpathFactory.newXPath();
        log( "UTIL configurado correctamente" );
    }    
    


    public static void log(String message) {
        //"[AGAS400:API] " + 
        System.out.println(message);

    }


    private static String parseXmlContent(String data) throws Exception {
        //log("parseXmlContent: " + pFile.getAbsolutePath());
        StringBuffer buffer = new StringBuffer();
        doc = convertStringToXMLDocument( data );
        XPathExpression changePricesExpr = xpath.compile( "/event/changes/change" );
        XPathExpression retailStoreExpr = xpath.compile( "/event/retailStores/retailStore" );
        
        buffer.append( buildRecordType1() + "\n" );


        NodeList storesNodes = (NodeList) retailStoreExpr.evaluate( doc, XPathConstants.NODESET);
        Element storeElement ;

        if(storesNodes != null)
            for (int i = 0; i < storesNodes.getLength(); i++) {
                storeElement = (Element) storesNodes.item(i);
                NodeList storeDataElement = storeElement.getChildNodes();
                
                for (int j = 0; j < storeDataElement.getLength(); j++) {
                    if (1 == storeDataElement.item(j).getNodeType()) {
                        Element entryElement = (Element) storeDataElement.item( j );
                        log( "RetailStore["+ j +"]" + entryElement.getNodeName() + "=" + entryElement.getTextContent() + "--check:" +
                              entryElement.getNodeName().indexOf( "storeID"  ));
                        if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "storeID"  ) >= 0 ){
                            buffer.append( buildRecordType2(entryElement.getTextContent()) + "\n" );
                        }
                    }
                }
                
            
            }
        
        
        NodeList changesNodes = (NodeList) changePricesExpr.evaluate( doc, XPathConstants.NODESET);


        String supplierId = "", style = "", sku = "", price = "", percent = "", changeId = "", eventName = "", itemType = "";
        NodeList idNodeList;
        log("Cantidad de líneas de  cambio de precios" + changesNodes.getLength());
        
        Element changePriceElement, itemId, itemElement ;
        if(changesNodes != null)
            for (int i = 0; i < changesNodes.getLength(); i++) {
                supplierId = ""; style = ""; sku = ""; price = ""; percent = ""; changeId = ""; eventName = ""; itemType = "";
                if (1 == changesNodes.item(i).getNodeType()) {
                    changePriceElement = (Element) changesNodes.item(i);
                    log(i + " cantidad de info en price change: " + changePriceElement.getChildNodes().getLength() );
    
                    NodeList childrenNodes = changePriceElement.getChildNodes();
    
                    for (int j = 0; j < childrenNodes.getLength(); j++) {
                        if (1 == childrenNodes.item(j).getNodeType()) {
                            Element entryElement = (Element) childrenNodes.item( j );
                            log( "PriceChange["+ j +"]" + entryElement.getNodeName() + "=" + entryElement.getTextContent() );

                            log( "\tid [Check:"+ entryElement.getNodeName().indexOf( "id"  ) +"]");
                            log( "\tname [Check:"+ entryElement.getNodeName().indexOf( "name"  ) +"]");
                            log( "\tprice [Check:"+ entryElement.getNodeName().indexOf( "price"  ) +"]");
                            log( "\tpercentage [Check:"+ entryElement.getNodeName().indexOf( "percentage"  ) +"]");
                            log( "\titem [Check:"+ entryElement.getNodeName().indexOf( "item"  ) +"] item");
                            if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "id"  ) >= 0 ){

                                changeId = entryElement.getTextContent();
                            } else if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "name"  ) >= 0 ){
                                eventName = entryElement.getTextContent();
                            } else if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "price"  ) >= 0 ){
                                price = entryElement.getTextContent();
                            } else if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "percentage"  ) >= 0 ){
                                percent = entryElement.getTextContent();
                            } else if( entryElement.getNodeName() != null && entryElement.getNodeName().indexOf( "item"  ) >= 0 ){
                                
                                idNodeList = entryElement.getChildNodes();
                                
                                for (int z = 0; z < idNodeList.getLength(); z++) {
                                    if (1 == idNodeList.item(z).getNodeType()) {
                                        itemElement =(Element) idNodeList.item( z );
                                        log( "\tItem["+z+"]  --> SubElement=" + itemElement.getNodeName() );
                                        if( itemElement.getNodeName() != null && itemElement.getNodeName().indexOf( "id"  ) >= 0 ){
                                            itemType = itemElement.getAttribute( "Type" ) ;
                                            log("\t\titemType="+ itemType);
                                            if( "Style".equals( itemType ) ){
                                                sku = itemElement.getTextContent();
                                            } else {
                                                style = itemElement.getTextContent();
                                            }                                            
                                        }
                                    }
                                }
                                    
                                
                                
                            }

                        }
                    }
                    
                    buffer.append( buildRecordType3(supplierId,  style,  sku,  price,  percent) + "\n" );
                }
            }
        
        return buffer.toString();
    }


    private static String buildRecordType1(){
        return "EVDESC,EVTIPO,FECINI,FECFIN,EVRAZO,EVNIVE,EVPROV,EVSTYLE,EVSTOR,EVSKU,EVPREC,EVPORC,EVPRPL,EVTREC,EVSEC";
    }

    private static String buildRecordType2(String retailStore){
        return ",,,,,,,,"+retailStore+",,,,,2,";
    }

    private static String buildRecordType3(String supplierId, String style, String sku, String price, String percent){
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
    
    
    public static void writePriceChangeFile(String filePath, String fileContent) throws IOException
    {
         
        FileOutputStream outputStream = new FileOutputStream( filePath );
        byte[] strToBytes = fileContent.getBytes();
        outputStream.write(strToBytes);
      
        outputStream.close();
    }

}
