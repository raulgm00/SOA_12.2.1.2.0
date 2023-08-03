package mx.agarcia.ea.jda.core;

import java.io.PrintStream;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

import mx.agarcia.ea.jda.APIException;
import mx.agarcia.ea.jda.config.ConfigException;
import mx.agarcia.ea.jda.config.ConfigFilesManager;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract class ActionContext {

    private Logger log;
    private String contextName;
    private EntityManager entityMgr;

    public ActionContext(Logger pLog, String pCtxName, EntityManager pEntMgr) throws APIException {
        this.log = pLog;
        this.contextName = pCtxName;
        this.entityMgr = pEntMgr;
    }

    public void info(String msg) {
        this.log.info(msg);
        System.out.println("INFO [" + this.contextName + "] " + msg);
    }

    public void debug(String msg) {
        this.log.fine(msg);
        System.out.println("DEBUG [" + this.contextName + "] " + msg);
    }

    public void error(String msg, Throwable error) {
        this.log.warning(msg);
        System.out.println("ERROR [" + this.contextName + "] " + msg);

        if( error != null )
            error.printStackTrace();
    }

    public void config(String msg) {
        this.log.info(msg);
        System.out.println("CONFIG [" + this.contextName + "] " + msg);
    }

    public void setEntityMgr(EntityManager entityMgr) {
        this.entityMgr = entityMgr;
    }

    public EntityManager getEntityMgr() {
        return entityMgr;
    }
    
    public abstract String getSQLQuery(String queryId) throws APIException;
    public abstract String getSQLInsert(String queryId) throws APIException;
    public abstract String getSQLUpdate(String queryId) throws APIException;
      

    
}
