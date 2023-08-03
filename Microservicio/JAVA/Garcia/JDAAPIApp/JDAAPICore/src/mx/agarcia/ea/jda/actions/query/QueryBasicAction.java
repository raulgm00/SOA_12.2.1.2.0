package mx.agarcia.ea.jda.actions.query;


import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.HashMap;

import java.util.Iterator;

import java.util.List;

import java.util.Map;

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

import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;

public class QueryBasicAction extends ExecutionAction {
    public QueryBasicAction() {
        super();
        this.setId("JDAQuery");
    }


    @Override
    public ResponseImpl execute(APIExecutionContext context, ExecutionPayload payload) throws ActionException {
        super.executionContext = context;

        ResponseImpl response = new ResponseImpl();

        context.info("[JDA] Iniciando consulta a JDA...");

        try {
            super.parseDocument(payload.getData());
            String queryId = payload.getHeader()
                                    .getParameters()
                                    .get("queryId");

            String sqlQuery = context.getSQLQuery(queryId);

            Query query = context.getEntityMgr().createNativeQuery(sqlQuery).setHint(QueryHints.RESULT_TYPE, ResultType.Map);


            HashMap<String, String> queryParameters = super.parseSQLParameters(null);

            super.debug("[______________ QUERY] Cantidad de parametros para Query<" + queryId + ">: " +
                        queryParameters.keySet().size() + " // Query: " + sqlQuery);
            Iterator<String> itParams = queryParameters.keySet().iterator();

            String paramName;
            Object paramValue;
            Long longValue;
            while (itParams.hasNext()) {
                longValue = null;
                paramName = itParams.next();
                paramValue = queryParameters.get(paramName);
                try {
                    if( !paramValue.toString().startsWith( "0" ) ){
                        longValue = Long.valueOf(paramValue.toString());
                    }
                    
                } catch (Exception e) {
                    //e.printStackTrace();

                }
                if (longValue != null && longValue > 0) {
                    super.debug("ParametroLong<" + paramName + "> " + paramValue);
                    query.setParameter(paramName, longValue);
                } else {
                    super.debug("Parametro<" + paramName + "> " + paramValue);
                    query.setParameter(paramName, paramValue);
                }
            }


            //Ejecutar Query
            super.debug("Iniciando ExecuteQuery<" + queryId + "> Query: " + sqlQuery);
            List result = query.getResultList();
            super.debug("ExecuteQuery terminado: " + queryId + " // Query: " + sqlQuery + "//CantidadResultados:" +
                        result.size());


            /*
            PreparedStatement statement = (PreparedStatement) payload.getConnection().createStatement();
            ResultSet result = statement.executeQuery(sqlQuery);*/
            Object dataResult, dataObject;
            RowData data;
            int i = 0, j = 0;
            Iterator itNext = result.iterator();
            String rowItem = null;
            
            i = 0;
            
            while (itNext.hasNext()) {
                //System.out.println( "Result= " + dataResult );
                data = new RowData();
                dataResult = itNext.next();
                if (dataResult instanceof Map) {

                    super.debug("Map<" + queryId + "::" + i + ">[Map]:" +  dataResult.toString());
                    
                    Iterator itKeys = ((Map)dataResult).keySet().iterator();
                    String dataHM = "";
                    j = 0;
                    while(itKeys.hasNext()){
                        dataHM = itKeys.next().toString();
                        dataObject =  ((Map)dataResult).get( dataHM );
                        rowItem = dataObject.toString();
                        //super.debug("_Object_MAP<" + queryId + ":"+dataHM+":" + i + "/" + j + ">" +getClassInfo(dataObject, true) );
                        data.addData(j, dataHM, rowItem);
                        j++;
                    }
                    

                } else if (dataResult instanceof List) {
                    Iterator itRowItem = ((List) dataResult).iterator();

                    j = 0;
                    while (itRowItem.hasNext()) {
                        rowItem = j+ "**" + itRowItem.next().toString();
                        //super.debug("ColData<" + queryId + "::" + i + "/" + j + ">" +rowItem );
                        data.addData(i, "C"+ i, rowItem);

                    }

                } else if (dataResult instanceof Object[]) {
                    j = 0;
                    for(Object dataObj : ((Object[])dataResult) ){
                        rowItem = dataObj.toString();
                        //super.debug("_Object_Data<" + queryId + "::" + i + "/" + j + ">" +rowItem );
                        data.addData(i, getClassInfo(dataObj, false), rowItem);
                        j++;
                    }

                }  else {
                    String  classInfo = this.getClassInfo(dataResult, true);
                    /*super.debug("QueryResult<" + queryId + "::" + i + ">[NO MAPEADO] <CLASS_INFO:" + classInfo +
                                "> " + dataResult.toString());*/
                    
                   
                   
                                       
                        
                        
                    rowItem = "Desconocido_"+ classInfo +"_-" + dataResult.toString();
                    data.addData(i, "C"+ i, rowItem);

                    
                }
                response.getDataSet().put(String.valueOf(i++), data);

                //int i = 0;

                /*for( Object item : result ) {
                }*/
            }
            //result.close();
            //statement.close();


            //Recorrer resultados y agregarlos a Response
            //ESte es solo un ejemplo, en caso que la tabla destino no tenga una PK clara se debe regresar como ID del data set un consecutivo


            /*RowData data = new RowData();
            data.put("empName", "Carlos");
            data.put("empSalary", "1000");


            data = new RowData();
            data.put("empName", "Pedro");
            data.put("empSalary", "12000");
*/
            //response.getDataSet().put("205", data);


            response.setSuccess(Boolean.TRUE);
            response.setCode(ResponseImpl.SUCESS_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            context.error("No es posible ejecutar QueryAction [" + e.getMessage() + "]", e);


            response.setSuccess(Boolean.FALSE);
            response.setCode(ResponseImpl.FAIL_CODE);
            response.setMessage(e.getMessage());
            throw new ActionException();
        }


        return response;
    }
    
    private String getClassInfo(Object data, boolean extended){
            
            String info = data.getClass().getTypeName();
            
            if( extended )    
                info = data.getClass().toGenericString() + "/getSimpleName=" + 
                               data.getClass().getSimpleName() + "/getTypeName=" + 
                                data.getClass().getTypeName() ;
            info += "$getInterfaces$$";
            for(Class cSuper : data.getClass().getInterfaces()){
                info += "SC=" + cSuper.getTypeName();
            }
            
            if(data.getClass().getSuperclass() != null)
                info += "*getSuperclass=" + data.getClass().getSuperclass();
            
            if(data.getClass().getAnnotatedSuperclass() != null)
                info += "*getAnnotatedSuperclass=" + data.getClass().getAnnotatedSuperclass();


            info += "$$getClasses=";
            for(Class cSuper : data.getClass().getClasses() ){
                info += "SC=" + cSuper.getTypeName();
            }
            info += "$$getDeclaredClasses=";
            for(Class cSuper : data.getClass().getDeclaredClasses() ){
                info += "SC=" + cSuper.getTypeName();
            }

            
            return info;
        }


}
