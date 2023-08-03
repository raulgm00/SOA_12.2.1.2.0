package mx.agarcia.ea.jda.core;

import javax.xml.transform.Source;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract class IConnectAction<I, O, C> {
    protected String type;
    protected String actionID;
    protected C executionContext;
    
    public abstract O execute(C executionContext, I request) throws ActionException;

    public String getId() {
        return this.actionID;
    }

    public void setId(String pActionID) {
        this.actionID = pActionID;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
