package org.bcie.fenix.common.model.utils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import oracle.adf.share.logging.ADFLogger;

public class PrintWSResponseDocsRunnable implements Runnable {
    private static ADFLogger logger = null;
    
    private Object poWSObject;
    
    private String prefix;
    
    private static final int NUMERO_CARACTERES = 500000;
    
    public PrintWSResponseDocsRunnable(String prefix, Object poWSObject) {
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
            String writer = writeXMLRequest(poWSObject, poWSObject.getClass()).toString();
            Integer finIndice = writer.length()/20;
            int numCaracteresMod = writer.length()%20;
            int numCaracterEntero = writer.length() - numCaracteresMod;
            logger.warning("Tamaño original : " + writer.length());
            logger.warning("Numero finIndice : " + finIndice);
            String[] arregloCadena = new String[21];
            int y = 0;
            if(writer.length() > NUMERO_CARACTERES){
                for(int x = 0; x <= 20; x++){
                    if(y < numCaracterEntero){
                        String cadena1 = writer.substring(y, y + finIndice);
                        arregloCadena[x] = cadena1;
                        y += finIndice;
                    }else{
                        if(numCaracteresMod > 0){
                            String cadena1 = writer.substring(y, y + numCaracteresMod);
                            arregloCadena[x] = cadena1;
                            y += finIndice;
                        }else{
                            break;
                        }
                    }
                }
                logger.warning("Imprimir response dividido.");
                for(String cadena : arregloCadena){
                    if(null != cadena && !"".equals(cadena)){
                        logger.warning(cadena);
                    }
                }
                logger.warning("Termina imprimir response");
            }else{
                logger.log(ADFLogger.WARNING,
                           prefix + " - Objeto response nuevo : " + 
                           poWSObject.toString() + 
                           " - " + writeXMLRequest(poWSObject, poWSObject.getClass()).toString());
            }
        } catch (JAXBException e) {
            logger.severe("Error al escribir WS Response. Objeto: " + poWSObject.toString(), e);
        }
    }
    
    public static StringWriter writeXMLRequest(Object request, Class requestClass) throws JAXBException {
        StringWriter writer = new StringWriter();
        logger.warning("Prueba xml : " + request.toString());
        logger.warning("Prueba xml clase : " + requestClass.toString());
        JAXBContext context = JAXBContext.newInstance(requestClass);
        Marshaller m = context.createMarshaller();
        m.marshal(new JAXBElement(new QName(requestClass.getName()), requestClass, request), writer);
        return writer;
    }
}
