package mx.agarcia.ea.jda.config;

import java.io.File;
import java.io.PrintStream;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.agarcia.ea.jda.management.Governable;
import mx.agarcia.ea.jda.management.ManagementException;
import mx.agarcia.ea.jda.management.ManagerLocal;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class ConfigFilesManager extends Governable {
    private static Logger _log = Logger.getLogger("ConfigFilesManager");
    private static ConfigFilesManager instance;
    private HashMap<String, IConfigFile> cache;
    private File configDir;
    private String configDirPath;
    ManagerLocal manager;

    public static void main(String[] args) throws Exception {
        ConfigFilesManager instance = getInstance();
        IConfigFile config = instance.getConfigFile("template");
    }

    private ConfigFilesManager() throws ConfigException, ManagementException {
        this.cache = new HashMap();
        this.configDirPath = PropertyConfig.getProperty( PropertyConstants.MASTER_CONFIG_PATH );
        //this.configDirPath = "/Proyectos/AGEAI/apijda/JDAAPIApp/JDAAPICore/src/META-INF/config2/";
        //this.configDirPath = "/u01/oracle/domains/apijda";
        this.configDir = new File(this.configDirPath);
        if (!this.configDir.exists()) {
            _log.log(Level.SEVERE, "Directorio de configuraci�n " + this.configDirPath + " no encontrado");
            throw new ConfigException();
        }
        _log.log(Level.CONFIG, "Directorio de configuraci�n " + this.configDirPath + " encontrado correctamente");

        start();
    }

    public static ConfigFilesManager getInstance() throws ConfigException, ManagementException {
        instance = new ConfigFilesManager();

        return instance;
    }

    public File getBaseFile(String pName) throws ConfigException {
        return new File(this.configDir, pName);
    }

    public IConfigFile getConfigFile(String pId) throws ConfigException {
        IConfigFile config = null;
        if (this.cache.containsKey(pId)) {
            config = (IConfigFile) this.cache.get(pId);
        } else {
            File configFile = new File(this.configDir, pId + ".xml");
            if (configFile.exists()) {
                config = new ConfigFileImpl();

                _log.log(Level.CONFIG, "Iniciando lectura de archivo de configuraci�n " + pId);
                System.out.println("Iniciando lectura de archivo de configuraci�n " + pId);

                ((ConfigFileImpl) config).readContent(configFile);
                _log.log(Level.CONFIG, "Archivo de configuraci�n " + pId + " leido correctamente");
                System.out.println("Archivo de configuraci�n " + pId + " leido correctamente");

                this.cache.put(pId, config);
            } else {
                _log.log(Level.SEVERE, "Archivo de configuraci�n " + pId + ".xml no encontrado");
                throw new ConfigException();
            }
        }
        return config;
    }

    public String getName() {
        return null;
    }

    public void setName(String pName) {
    }

    public void start() throws ManagementException {
        this.isActive = true;
        setName("ConfigFilesManager");
    }

    public void stop() throws ManagementException {
        reset();
        this.isActive = false;
    }

    public void restart() throws ManagementException {
        stop();
        start();
    }

    public void reset() throws ManagementException {
        this.cache.clear();
    }
}
