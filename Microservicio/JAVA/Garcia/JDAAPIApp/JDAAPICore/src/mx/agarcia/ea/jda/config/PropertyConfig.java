package mx.agarcia.ea.jda.config;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class PropertyConfig {
	private static Logger _log = Logger.getLogger("PropertyConfig");

	public static String getProperty(String name) {
		String propValue = null;
		if (System.getProperty(name) != null) {
			propValue = System.getProperty(name);
		}
		_log.log(Level.CONFIG, "getProperty<" + name + ">=" + propValue);

		return propValue;
	}
}
