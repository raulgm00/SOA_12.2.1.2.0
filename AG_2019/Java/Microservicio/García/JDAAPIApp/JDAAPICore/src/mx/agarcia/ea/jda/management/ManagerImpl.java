package mx.agarcia.ea.jda.management;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
@Stateful
@LocalBean
public class ManagerImpl implements ManagerRemote, ManagerLocal {
	private static Logger _log = Logger.getLogger("Manager");
	private List<IGovernable> cache = new ArrayList();

	public void register(IGovernable component) throws ManagementException {
		_log.log(Level.CONFIG, "Registrando componente " + component.getName());
	}

	public String getName() {
		return "Manager";
	}

	public void setName(String pName) {
	}

	public void start() throws ManagementException {
	}

	public void stop() throws ManagementException {
	}

	public void restart() throws ManagementException {
	}

	public void reset() throws ManagementException {
	}
}
