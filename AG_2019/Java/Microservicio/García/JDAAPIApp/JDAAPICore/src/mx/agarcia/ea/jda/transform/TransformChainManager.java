package mx.agarcia.ea.jda.transform;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import mx.agarcia.ea.jda.config.ConfigEntries;
import mx.agarcia.ea.jda.config.ConfigEntry;
import mx.agarcia.ea.jda.config.ConfigException;
import mx.agarcia.ea.jda.config.ConfigFilesManager;
import mx.agarcia.ea.jda.config.IConfigFile;
import mx.agarcia.ea.jda.core.IConnectAction;
import mx.agarcia.ea.jda.core.IConnectChain;
import mx.agarcia.ea.jda.management.Governable;
import mx.agarcia.ea.jda.management.ManagementException;

/**
 * 
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class TransformChainManager extends Governable {
	
	private static TransformChainManager instance;
	private static Logger _log = Logger.getLogger("TransformChainManager");
	private HashMap<String, IConnectChain> configuration = new HashMap();
	private DocumentBuilderFactory documentBuilderFactory;

	private TransformChainManager() throws ConfigException, ManagementException {
		this.documentBuilderFactory = DocumentBuilderFactory.newInstance();

		start();
	}

	public static TransformChainManager getInstance() throws ConfigException, ManagementException {
		if (instance == null) {
			instance = new TransformChainManager();
		}
		return instance;
	}

	public IConnectChain build(String pId) throws ConfigException, ManagementException {
		System.out.println("[TransformChainManager] Construyendo Chain @" + pId);

		IConnectChain chain = null;
		if (pId != null) {
			if (this.configuration.containsKey(pId)) {
				chain = (IConnectChain) this.configuration.get(pId);
			} else {
				IConfigFile configFile = ConfigFilesManager.getInstance().getConfigFile("TransformMasterConfig");
				if (configFile != null) {
					chain = new TransformChain(pId);

					ConfigEntries config = configFile.getEntries(pId);
					System.out.println("[TransformChainManager<" + pId + ">] Config para Chain leida " + configFile
							+ "-->" + config);
					if (config != null) {
						Iterator<ConfigEntry> itCnfEnt = config.getEntries().iterator();

						System.out.println("[TransformChainManager<" + pId + "> Cantidad de entries "
								+ config.getEntries().size());
						while (itCnfEnt.hasNext()) {
							ConfigEntry configEntry = (ConfigEntry) itCnfEnt.next();
							System.out.println("[TransformChainManager<");

							IConnectAction<TransformPayload, TransformResponse, TransformContext> action = new TransformAction(
									configEntry.getValue(), this.documentBuilderFactory,
                                                    ConfigFilesManager.getInstance().getBaseFile(configEntry.getValue()));

							chain.addConnectAction(action);
						}
						this.configuration.put(pId, chain);
						System.out.println("[TransformChainManager] Chain @" + pId + " construido");
					}
				} else {
					throw new ConfigException();
				}
			}
		} else {
			throw new ConfigException();
		}
		return chain;
	}

	public void start() throws ManagementException {
		this.isActive = true;
		setName("TransformChainManager");
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
		this.configuration.clear();
	}
}
