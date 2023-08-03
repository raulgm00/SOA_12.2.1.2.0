package facade.bcie.handler;

import java.io.StringWriter;

import java.util.Set;

import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;

import oracle.adf.share.logging.ADFLogger;

import oracle.xml.parser.v2.XMLDocument;

import org.w3c.dom.Element;


public class HandlerMessageProxyService implements SOAPHandler<SOAPMessageContext> {
    public static final ADFLogger LOGGER =ADFLogger.createADFLogger(HandlerMessageProxyService.class);
    public HandlerMessageProxyService() {
        super();
    }
    public void log(final String log,final ADFLogger logger){
        logger.finest(log);
    }
    public void log(final Exception excepcion){
        LOGGER.severe(excepcion);
    }
    public String getServiceName(final SOAPMessageContext context){
        final QName svcn = (QName) context.get(MessageContext.WSDL_SERVICE);
        return svcn.getLocalPart();
    }
    public String getOperationName(final SOAPMessageContext context){
        final QName opn = (QName) context.get(MessageContext.WSDL_OPERATION);
        return opn.getLocalPart();
    }
    protected void writeLog(final SOAPMessageContext context,final ADFLogger logger){
        try{            
            if (Boolean.TRUE.equals(context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY))) {
                log("########################################################    REQUEST    ########################################################",logger);
                try {
                   log(prettyXml(context.getMessage()),logger);
                } catch (Exception e){
                   log(e);
                }
                log("########################################################   FIN REQUEST    ########################################################",logger);
            } else {           
                log("########################################################    RESPONSE    ########################################################",logger);
                try {
                    log(prettyXml(context.getMessage()),logger);
                } catch (Exception e){
                    log(e);
                }
                log("########################################################   FIN RESPONSE    ########################################################",logger);
           }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean handleMessage(final SOAPMessageContext context)  {
        LOGGER.finest(this.getClass().getName()+".handleMessage");
        this.writeLog(context, LOGGER);
        LOGGER.finest(this.getClass().getName()+".handleMessage");
       return Boolean.TRUE; 
    }
    @Override
    public boolean handleFault(final SOAPMessageContext context) {
        //Log("ManejadorAuditoriaServicios : handleFault()......");
        return Boolean.TRUE;
    }
    @Override
    public void close(final MessageContext context) {
    }
    @Override
    public Set<QName> getHeaders() {
        return null;
    }
    private String prettyXml(final SOAPMessage msg) {
        try {
            if (msg == null) {
                return "";
            }
        
            final StringWriter sw = new StringWriter();
            final Element msgDocElem = msg.getSOAPPart().getDocumentElement();
            final XMLDocument xdoc = new XMLDocument();
            xdoc.appendChild(xdoc.importNode(msgDocElem, true));
            xdoc.print(sw);
            return sw.toString();
        } catch (Exception e) {
            log(e);
            return "error XML: " + e.getMessage();
        }
    }
}
