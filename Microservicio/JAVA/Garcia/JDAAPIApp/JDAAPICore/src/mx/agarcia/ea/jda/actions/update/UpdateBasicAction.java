package mx.agarcia.ea.jda.actions.update;

import java.util.HashMap;
import java.util.Iterator;

import javax.persistence.Query;

import mx.agarcia.ea.jda.actions.insert.InsertBasicAction;
import mx.agarcia.ea.jda.core.APIExecutionContext;
import mx.agarcia.ea.jda.core.ActionException;
import mx.agarcia.ea.jda.core.execution.ExecutionAction;
import mx.agarcia.ea.jda.core.execution.ExecutionPayload;
import mx.agarcia.ea.jda.core.model.ResponseImpl;

public class UpdateBasicAction extends ExecutionAction {
    
    
    public UpdateBasicAction() {
        super();
        this.setId("JDAUpdate");
    }


    @Override
    public ResponseImpl execute(APIExecutionContext context, ExecutionPayload payload) throws ActionException {
        super.executionContext = context;

        ResponseImpl response = new ResponseImpl();

        String sqlId = this.getType();

        try {
            super.parseDocument(payload.getData());

            String sqlUpdate = context.getSQLUpdate( sqlId );
            context.info("[UPDATE:"+ payload.getInterfaceId() +":"+sqlId+"] --> " + sqlUpdate);


            Query updateStmt = context.getEntityMgr().createNativeQuery( sqlUpdate );

            HashMap<String, String> updateParameters = super.parseSQLParameters(sqlId);


            if (updateParameters != null && updateParameters.keySet().size() > 0) {
                context.debug("[UPDATE:"+ payload.getInterfaceId() +":"+sqlId+"] --> " + updateParameters.size());
                Iterator<String> itParams = updateParameters.keySet().iterator();

                String paramName;
                Object paramValue;

                while (itParams.hasNext()) {
                    paramName = itParams.next();
                    paramValue = updateParameters.get( paramName );
                    super.debug("[ *** UPDATE:"+ payload.getInterfaceId() +":"+sqlId+"] --> Parámetro<"+ paramName +"> " + paramValue );
                    updateStmt.setParameter(paramName, paramValue);
                }
                
                //insertStmt.setParameter("ZMSEQ", Integer.valueOf("9999"));
                
            } else {
                    context.debug("[UPDATE:"+ payload.getInterfaceId() +":"+sqlId+"] NO HAY PARAMETROS" );
                
                }
            
            
            int updateResult = updateStmt.executeUpdate();
            if( updateResult >= 0 ){
                response.setSuccess(Boolean.TRUE);
                response.setCode(ResponseImpl.SUCESS_CODE);
                response.setMessage( sqlId + " - Registro actualizado correctamente - " + updateResult );
            } else {
                response.setSuccess(Boolean.FALSE);
                response.setCode(ResponseImpl.FAIL_CODE);
                response.setMessage( sqlId + " - Registro NO pudo ser actualizado correctamente - " + updateResult );
                }

        } catch (Exception e) {
            context.error("No es posible ejecutar UpdateAction-" + sqlId, e);


            response.setSuccess(Boolean.FALSE);
            response.setCode(ResponseImpl.FAIL_CODE);
            response.setMessage(e.getMessage());
            
            throw new ActionException();
        }

        //
        return response;
    }

    
    
}
