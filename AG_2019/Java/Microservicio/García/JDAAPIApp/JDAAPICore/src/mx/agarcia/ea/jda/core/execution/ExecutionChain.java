package mx.agarcia.ea.jda.core.execution;

import mx.agarcia.ea.jda.core.APIExecutionContext;
import mx.agarcia.ea.jda.core.ActionContext;
import mx.agarcia.ea.jda.core.ActionException;
import mx.agarcia.ea.jda.core.ConnectChain;
import mx.agarcia.ea.jda.core.ExecutionException;
import mx.agarcia.ea.jda.core.IConnectAction;
import mx.agarcia.ea.jda.core.model.IResponse;
import mx.agarcia.ea.jda.core.model.Request;
import mx.agarcia.ea.jda.core.model.ResponseImpl;
import mx.agarcia.ea.jda.transform.TransformChain;
import mx.agarcia.ea.jda.transform.TransformContext;
import mx.agarcia.ea.jda.transform.TransformPayload;
import mx.agarcia.ea.jda.transform.TransformResponse;

public class ExecutionChain extends ConnectChain<ExecutionPayload, IResponse> {
    
    
    public ExecutionChain(String pId) {
        super(pId);
    }


    @Override
    public IResponse apply(ExecutionPayload payload) throws ExecutionException {
        IResponse response = new ResponseImpl();
        
        try {
            ActionContext context = new APIExecutionContext(payload.getInterfaceId(), payload.getEntityMgr());
            System.out.println("[DEBUG] Iniciando ejecución de Chain ");
            
            for (IConnectAction<ExecutionPayload, IResponse, ActionContext> action : this.actions) {
                response =  action.execute(context, payload);
                System.out.println("[DEBUG] Action " + action.getId() + " ejecutada --> " + response);
                //payload.setCurrentDoc(response.getDocument());
            }
        } catch (ActionException e) {
            super.error("No es posible ejecutar ExecutionChain::ActionException", e);
        } catch (Exception e) {
            response.setSuccess( Boolean.FALSE );
            response.setMessage( e.getMessage() );
            super.error("No es posible ejecutar ExecutionChain::TechException?", e);
        }
        return response;
    }

    protected void prepare(ExecutionPayload payload) throws ExecutionException {
    }



}
