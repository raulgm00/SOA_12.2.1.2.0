package mx.agarcia.ea.jda.config;


/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract interface IConfigFile {
	public abstract ConfigEntry getEntry(String paramString1, String paramString2);

	public abstract ConfigEntries getEntries(String paramString);
}
