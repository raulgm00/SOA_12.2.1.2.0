package mx.agarcia.ea.jda.management;

import javax.ejb.Remote;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
@Remote
public abstract interface ManagerRemote extends IGovernable {
	public abstract void register(IGovernable paramIGovernable) throws ManagementException;
}
