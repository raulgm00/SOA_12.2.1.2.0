package mx.agarcia.ea.jda.core.execution;

import java.sql.Connection;

import javax.persistence.EntityManager;

import mx.agarcia.ea.jda.core.model.Request;

import org.w3c.dom.Document;

public class ExecutionPayload extends Request{
    
    private EntityManager entityMgr;
    private Connection connection;
    private String interfaceId;
    private Document data;
    
    public ExecutionPayload() {
        super();
    }

    public void setEntityMgr(EntityManager entityMgr) {
        this.entityMgr = entityMgr;
    }

    public EntityManager getEntityMgr() {
        return entityMgr;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setData(Document data) {
        this.data = data;
    }

    public Document getData() {
        return data;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
