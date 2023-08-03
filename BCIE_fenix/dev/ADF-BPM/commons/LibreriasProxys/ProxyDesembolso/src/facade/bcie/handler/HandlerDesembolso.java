package facade.bcie.handler;

import javax.xml.ws.handler.soap.SOAPMessageContext;

import oracle.adf.share.logging.ADFLogger;

public class HandlerDesembolso extends HandlerMessageProxyService {
    public static final ADFLogger LOGGER = ADFLogger.createADFLogger(HandlerDesembolso.class);
    public HandlerDesembolso() {
        super();
    }
    @Override
    public boolean handleMessage(final SOAPMessageContext context){
        LOGGER.finest(this.getClass().getName()+".handleMessage");
        this.writeLog(context, LOGGER);
        LOGGER.finest(this.getClass().getName()+".handleMessage");
        return Boolean.TRUE;
    }
}
