package mx.agarcia.ea.jda.management;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract interface IGovernable {
	public abstract String getName();

	public abstract void setName(String paramString);

	public abstract void start() throws ManagementException;

	public abstract void stop() throws ManagementException;

	public abstract void restart() throws ManagementException;

	public abstract void reset() throws ManagementException;
}
