package mx.agarcia.ea.jda.core.execution;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.lang.reflect.Type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;

import mx.agarcia.ea.jda.config.ConfigEntries;
import mx.agarcia.ea.jda.config.ConfigEntry;
import mx.agarcia.ea.jda.config.ConfigException;
import mx.agarcia.ea.jda.config.ConfigFilesManager;
import mx.agarcia.ea.jda.config.IConfigFile;
import mx.agarcia.ea.jda.core.ActionContext;
import mx.agarcia.ea.jda.core.IConnectAction;
import mx.agarcia.ea.jda.core.IConnectChain;
import mx.agarcia.ea.jda.core.model.IResponse;
import mx.agarcia.ea.jda.management.Governable;
import mx.agarcia.ea.jda.management.ManagementException;
import mx.agarcia.ea.jda.transform.TransformAction;
import mx.agarcia.ea.jda.transform.TransformChain;
import mx.agarcia.ea.jda.transform.TransformChainManager;
import mx.agarcia.ea.jda.transform.TransformContext;
import mx.agarcia.ea.jda.transform.TransformPayload;
import mx.agarcia.ea.jda.transform.TransformResponse;

public class ExecutionChainManager extends Governable {

    private static ExecutionChainManager instance;
    private static Logger _log = Logger.getLogger("ExecutionChainManager");

    private HashMap<String, IConnectChain> configuration = new HashMap<String, IConnectChain>();
    private HashMap<String, Class> classesCache = new HashMap<String, Class>();


    private DocumentBuilderFactory documentBuilderFactory;

    private ExecutionChainManager() throws ConfigException, ManagementException {
        this.documentBuilderFactory = DocumentBuilderFactory.newInstance();

        start();
    }

    public static ExecutionChainManager getInstance() throws ConfigException, ManagementException {
        if (instance == null) {
            instance = new ExecutionChainManager();
        }
        return instance;
    }

    public IConnectChain build(String pId) throws ConfigException, ManagementException {
        System.out.println("[ExecutionChainManager] Construyendo Chain @" + pId);

        IConnectChain chain = null;
        if (pId != null) {
            if (this.configuration.containsKey(pId)) {
                chain = this.configuration.get(pId);
            } else {
                IConfigFile configFile = ConfigFilesManager.getInstance().getConfigFile("IntegrationMasterConfig");
                if (configFile != null) {
                    chain = new ExecutionChain(pId);

                    ConfigEntries config = configFile.getEntries(pId);
                    System.out.println("[ExecutionChainManager<" + pId + ">] Config para Chain leida " + configFile +
                                       "-->" + config);
                    if (config != null) {
                        Iterator<ConfigEntry> itCnfEnt = config.getEntries().iterator();

                        System.out.println("[ExecutionChainManager<" + pId + "> Cantidad de entries " +
                                           config.getEntries().size());
                        while (itCnfEnt.hasNext()) {
                            ConfigEntry configEntry = itCnfEnt.next();
                            System.out.println("[ExecutionChainManager<" + pId + "> ConfigEntry:" +
                                               configEntry.getId() + "-->Class: " + configEntry.getValue());

                            Class targetClass = null;
                            if (!classesCache.containsKey(configEntry.getValue())) {
                                try {
                                    System.out.println("[ExecutionChainManager<" + pId + "> ConfigEntry:" +
                                                       configEntry.getId() + "[" + configEntry.getValue() +
                                                       "] - preparando carga de clase");

                                    targetClass = this.getClass()
                                                      .getClassLoader()
                                                      .loadClass(configEntry.getValue());


                                    classesCache.put(configEntry.getValue(), targetClass);

                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                    throw new ConfigException();
                                }
                            } else {
                                targetClass = classesCache.get(configEntry.getValue());
                                System.out.println("[ExecutionChainManager<" + pId + "> ConfigEntry:" +
                                                   configEntry.getId() + "[" + configEntry.getValue() +
                                                   "] - Clase recuperada de cache correctamente");

                            }


                            IConnectAction<ExecutionPayload, IResponse, ActionContext> action;
                            
                            try {
                                System.out.println("[ExecutionChainManager<" + pId + "> ConfigEntry:" +
                                                   configEntry.getId() + "[" + configEntry.getValue() +
                                                   "] - creando nueva instancia ...");
                                action =
                                    (IConnectAction<ExecutionPayload, IResponse, ActionContext>) targetClass.newInstance();



/*
                                Method[] allMethods = targetClass.getMethods();
                                
                                for (Method m : allMethods) {
                                    String mname = m.getName();
                                    
                                    System.out.println("**** [ExecutionChainManager<" + pId + "> ConfigEntry:" +
                                                       configEntry.getId() + "-->" + mname );
                                    
                                    Type[] pType = m.getGenericParameterTypes();
                                       
                                    if( pType != null && pType.length > 0 ){
                                        for(Type iType :  pType ){
                                            System.out.println("**** [ExecutionChainManager<" + pId + "> ConfigEntry:" +
                                                               configEntry.getId() + "-->" + mname + ":Name=" + iType.getTypeName() + ":Class=" + iType.getClass() );
                                        }
                                        
                                        
                                    }

                                    System.out.format("invoking %s()%n", mname);


                                }*/


                                Class argTypes = String.class;


                                Method setTypeMethod = targetClass.getMethod("setType", argTypes);
                               


                                System.out.println("***[ExecutionChainManager<" + pId + "> ConfigEntry:" +
                                                   configEntry.getId() + "[" + configEntry.getValue() +
                                                   "] - asignando type= " + configEntry.getType());

                                setTypeMethod.invoke(action, configEntry.getType());


                                chain.addConnectAction(action);

                            } catch (IllegalAccessException | InstantiationException e) {
                                e.printStackTrace();
                                throw new ConfigException();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                                throw new ConfigException();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                                throw new ConfigException();
                            }


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
