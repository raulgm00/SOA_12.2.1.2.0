package mx.agarcia.ea.jda.core;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

import mx.agarcia.ea.jda.APIException;
import mx.agarcia.ea.jda.config.ConfigEntry;
import mx.agarcia.ea.jda.config.ConfigException;
import mx.agarcia.ea.jda.config.ConfigFilesManager;
import mx.agarcia.ea.jda.config.IConfigFile;
import mx.agarcia.ea.jda.management.ManagementException;

public class APIExecutionContext extends ActionContext {

    private static Logger _log = Logger.getLogger( "APIExecutionContext" );
    private static final String JDA_QUERIES_CONF = "JDA-Queries-Config";
    private static final String JDA_UPDATES_CONF = "JDA-Updates-Config";
    private static final String JDA_INSERT_CONF = "JDA-Inserts-Config";
    
    public APIExecutionContext(Logger pLog, String pCtxName, EntityManager pEntMgr) throws APIException {
        super(pLog, pCtxName, pEntMgr);
        //cSQLConfigFilesManager = ConfigFilesManager.getInstance().getConfigFile("JDA")
    }
    
    
    
    public APIExecutionContext(String pCtxName, EntityManager pEntMgr) throws APIException {
        super(_log, pCtxName, pEntMgr);
        //cSQLConfigFilesManager = ConfigFilesManager.getInstance().getConfigFile("JDA")
    }

    @Override
    public String getSQLQuery(String queryId) throws APIException {
        //super.debug( "Recuperando entrada de configuración <"+ queryId + ">" );
        IConfigFile config = ConfigFilesManager.getInstance().getConfigFile( JDA_QUERIES_CONF );
        ConfigEntry entry = config.getEntry("QUERY_MAP", queryId);
        if( entry == null){
            super.debug( "Entrada de configuración <"+ queryId + "> NO ENCONTRADA"   );
        } /*else {
            super.debug( "Recuperada entrada de configuraci�n <"+ queryId + ">" + entry.getValue() );
        }*/
        return entry.getValue();
    }

    @Override
    public String getSQLInsert(String sqlId) throws APIException {
        
        super.debug( "******************      APIExecCtx:getSQLInsert: Consultando:" + sqlId  + "  ******************");
        IConfigFile fileConfig = ConfigFilesManager.getInstance().getConfigFile( JDA_INSERT_CONF );
        ConfigEntry entry = fileConfig.getEntry("INSERTS_MAP", sqlId);
        if( entry != null ){
            super.debug( "Recuperada entrada de configuración para INSERTS <"+ sqlId + ">" + entry.getValue() );
        } else {
            throw new APIException();
        }

        return entry.getValue();
    }

    @Override
    public String getSQLUpdate(String sqlId) throws APIException {
        IConfigFile config = ConfigFilesManager.getInstance().getConfigFile( JDA_UPDATES_CONF );
        ConfigEntry entry = config.getEntry("UPDATE_MAP", sqlId);
        super.debug( "Recuperada entrada de configuración <"+ sqlId + ">" + entry.getValue() );
        return entry.getValue();
    }
}
