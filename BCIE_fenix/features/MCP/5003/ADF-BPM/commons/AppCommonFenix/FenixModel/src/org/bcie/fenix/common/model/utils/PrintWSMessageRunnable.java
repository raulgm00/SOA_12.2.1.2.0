package org.bcie.fenix.common.model.utils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.share.logging.ADFLogger;

public class PrintWSMessageRunnable implements Runnable {
    
    private static ADFLogger logger = null;
    
    private Object poWSObject;
    
    private String prefix;
    

    public PrintWSMessageRunnable(String prefix, Object poWSObject) {
        super();
        logger = ADFLogger.createADFLogger(this.getClass());
        this.prefix = prefix;
        this.poWSObject = poWSObject;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPoWSObject(Object poWSObject) {
        this.poWSObject = poWSObject;
    }

    public Object getPoWSObject() {
        return poWSObject;
    }

    @Override
    public void run() {

        try {
            logger.log(ADFLogger.WARNING,
                       prefix + " - Objeto: " + 
                       poWSObject.toString() + 
                       " - " +
                       writeXMLRequest(poWSObject, poWSObject.getClass()).toString());
        } catch (JAXBException e) {
            logger.severe("Error al escribir WS Response. Objeto: " + poWSObject.toString(), e);
        }
    }
    
    public static StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(requestClass);
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);
        return writer;
    }
}
