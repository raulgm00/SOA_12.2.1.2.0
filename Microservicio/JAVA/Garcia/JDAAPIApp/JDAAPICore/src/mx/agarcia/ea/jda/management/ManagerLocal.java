package mx.agarcia.ea.jda.management;

import javax.ejb.Local;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
@Local
public abstract interface ManagerLocal extends IGovernable {
	public abstract void register(IGovernable paramIGovernable) throws ManagementException;
}
