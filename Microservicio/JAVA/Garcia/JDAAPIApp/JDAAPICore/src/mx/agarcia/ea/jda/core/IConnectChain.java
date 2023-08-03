package mx.agarcia.ea.jda.core;


/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract interface IConnectChain<I, O> {
	public abstract O apply(I paramI) throws ExecutionException;

	public abstract void addConnectAction(IConnectAction paramIConnectAction);

	public abstract String getId();

	public abstract void setId(String paramString);
}
