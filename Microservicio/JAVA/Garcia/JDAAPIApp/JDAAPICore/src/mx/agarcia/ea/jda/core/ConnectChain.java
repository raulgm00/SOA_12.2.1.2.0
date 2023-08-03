package mx.agarcia.ea.jda.core;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract class ConnectChain<I, O> implements IConnectChain<I, O> {
	private static Logger _log = Logger.getLogger("ConnectChain");
	private String id;
	protected List<IConnectAction> actions = new ArrayList();

	public ConnectChain(String pId) {
		this.id = pId;
	}

	protected abstract void prepare(I paramI) throws ExecutionException;

	public void addConnectAction(IConnectAction action) {
		config("Agregado action " + action.getId());
		this.actions.add(action);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	protected void info(String msg) {
		System.out.println("[INFO] " + msg);
	}

	protected void debug(String msg) {
		System.out.println("[DEBUG] " + msg);
	}

	protected void config(String msg) {
		System.out.println("[CONFIG] " + msg);
	}

	protected void error(String msg, Throwable th) {
		System.out.println("[ERROR] " + msg);
		th.printStackTrace();
	}
}
