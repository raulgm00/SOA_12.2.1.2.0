package mx.agarcia.ea.jda.core;

import javax.ejb.Local;

import mx.agarcia.ea.jda.core.execution.ExecutionPayload;
import mx.agarcia.ea.jda.core.model.IRequest;
import mx.agarcia.ea.jda.core.model.IResponse;

@Local
public interface TransactionProcessorFacadeLocal {
    
    public IResponse execute(ExecutionPayload request) throws ExecutionException;
    
}
