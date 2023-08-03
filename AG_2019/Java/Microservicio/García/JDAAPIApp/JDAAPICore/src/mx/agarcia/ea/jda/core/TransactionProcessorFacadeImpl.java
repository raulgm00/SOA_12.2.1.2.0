package mx.agarcia.ea.jda.core;

import java.io.StringWriter;

import java.sql.SQLException;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import mx.agarcia.ea.jda.config.ConfigException;
import mx.agarcia.ea.jda.config.XMLUtil;
import mx.agarcia.ea.jda.core.execution.ExecutionChainManager;
import mx.agarcia.ea.jda.core.execution.ExecutionPayload;
import mx.agarcia.ea.jda.core.model.IRequest;
import mx.agarcia.ea.jda.core.model.IResponse;
import mx.agarcia.ea.jda.core.model.ResponseImpl;
import mx.agarcia.ea.jda.management.ManagementException;
import mx.agarcia.ea.jda.transform.TransformChainManager;
import mx.agarcia.ea.jda.transform.TransformPayload;
import mx.agarcia.ea.jda.transform.TransformResponse;

import org.w3c.dom.Document;

@Stateless(name = "TransactionProcessorFacade", mappedName = "TransactionProcessorFacade")
public class TransactionProcessorFacadeImpl implements TransactionProcessorFacade, TransactionProcessorFacadeLocal {
    @Resource
    SessionContext sessionContext;

    @PersistenceContext
    EntityManager entityMgr;

    @EJB
    JDAConnectionManager connectionManager;
    
    private static Logger _log = Logger.getLogger( "TransactionProcessorFacade" );
    
    public TransactionProcessorFacadeImpl() {
        
    }

    public IResponse execute(ExecutionPayload request) throws ExecutionException {
        IResponse response = new ResponseImpl();
        response.setId("@" + System.currentTimeMillis());
        response.setSuccess(Boolean.FALSE);

        System.out.println("IConnectChain para interface " + request.getHeader().getInterfaceId());

        IConnectChain<ExecutionPayload, IResponse> executionChain;
        try {
            executionChain = ExecutionChainManager.getInstance().build(request.getHeader().getInterfaceId());
        } catch (ConfigException | ManagementException e) {
            e.printStackTrace();
            throw new ExecutionException();
        }

        ExecutionPayload payload = new ExecutionPayload();
        payload.getHeader().setParameters(request.getHeader().getParameters());
        payload.setData( request.getData() );
        
        XMLUtil.writeXmlDocumentToString( request.getData() );
        
        request.print();
        payload.setEntityMgr( this.entityMgr );
        payload.setInterfaceId(request.getHeader().getInterfaceId());

        response = executionChain.apply(payload);
        System.out.println("[EXECUTION finished]  @" + request.getHeader().getInterfaceId() + ": " + response);
        return response;

    }
    
    
    
}
