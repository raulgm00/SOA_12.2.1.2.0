package mx.agarcia.ea.jda.actions.insert;


import java.util.HashMap;

import java.util.Iterator;

import javax.persistence.Query;

import mx.agarcia.ea.jda.APIException;
import mx.agarcia.ea.jda.core.APIExecutionContext;
import mx.agarcia.ea.jda.core.ActionException;
import mx.agarcia.ea.jda.core.ConnectAction;
import mx.agarcia.ea.jda.core.IConnectAction;
import mx.agarcia.ea.jda.core.execution.ExecutionAction;
import mx.agarcia.ea.jda.core.execution.ExecutionPayload;
import mx.agarcia.ea.jda.core.model.IResponse;
import mx.agarcia.ea.jda.core.model.QueryRequest;
import mx.agarcia.ea.jda.core.model.ResponseImpl;
import mx.agarcia.ea.jda.core.model.RowData;

public class InsertBasicAction extends ExecutionAction {
    
    public InsertBasicAction() {
        super();
        this.setId("JDAInsert");
    }


    @Override
    public ResponseImpl execute(APIExecutionContext context, ExecutionPayload payload) throws ActionException {
        super.executionContext = context;

        ResponseImpl response = new ResponseImpl();

        String sqlId = this.getType();

        try {
            super.parseDocument(payload.getData());

            String sqlInsert = context.getSQLInsert(sqlId);
            context.info("[INSERT:"+ payload.getInterfaceId() +":"+sqlId+"] --> " + sqlInsert);


            Query insertStmt = context.getEntityMgr().createNativeQuery(sqlInsert);

            HashMap<String, String> insertParameters = super.parseSQLParameters(sqlId);


            if (insertParameters != null && insertParameters.keySet().size() > 0) {
                context.debug("[INSERT:"+ payload.getInterfaceId() +":"+sqlId+"] --> " + insertParameters.size());
                Iterator<String> itParams = insertParameters.keySet().iterator();

                String paramName;
                Object paramValue;

                while (itParams.hasNext()) {
                    paramName = itParams.next();
                    paramValue = insertParameters.get( paramName );
                    super.debug("[ *** INSERT:"+ payload.getInterfaceId() +":"+sqlId+"] --> Parametro<"+ paramName +"> " + paramValue );
                    insertStmt.setParameter(paramName, paramValue);
                }
                
                //insertStmt.setParameter("ZMSEQ", Integer.valueOf("9999"));
                
            }else{
                    context.debug("[INSERT:"+ payload.getInterfaceId() +":"+sqlId+"] NO HAY PARAMETROS" );
                
                }
            
            
            int insertResult = insertStmt.executeUpdate();
            if( insertResult >= 0 ){
                response.setSuccess(Boolean.TRUE);
                response.setCode(ResponseImpl.SUCESS_CODE);
                response.setMessage( sqlId + " - Registro creado correctamente - " + insertResult );
            } else {
                response.setSuccess(Boolean.FALSE);
                response.setCode(ResponseImpl.FAIL_CODE);
                response.setMessage( sqlId + " - Registro NO pudo ser creado correctamente - " + insertResult );
                }

        } catch (Exception e) {
            context.error("No es posible ejecutar InsertAction-" + sqlId, e);


            response.setSuccess(Boolean.FALSE);
            response.setCode(ResponseImpl.FAIL_CODE);
            response.setMessage(e.getMessage());
            
            throw new ActionException();
        }

        //
        return response;
    }


}
