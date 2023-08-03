package mx.agarcia.ea.jda.core;

import javax.ejb.Remote;

import mx.agarcia.ea.jda.core.execution.ExecutionPayload;
import mx.agarcia.ea.jda.core.model.IRequest;
import mx.agarcia.ea.jda.core.model.IResponse;

@Remote
public interface TransactionProcessorFacade {
    public IResponse execute(ExecutionPayload request) throws ExecutionException;
}
