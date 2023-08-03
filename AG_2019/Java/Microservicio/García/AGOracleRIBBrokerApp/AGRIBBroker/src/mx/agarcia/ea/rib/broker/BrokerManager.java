package mx.agarcia.ea.rib.broker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import java.util.TreeSet;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import mx.agarcia.ea.rib.broker.model.TargetTopic;
import mx.agarcia.ea.rib.dispatcher.DispatcherManager;
import mx.agarcia.ea.rib.dispatcher.MessageWatcher;

public class BrokerManager {
    
    public static ResourceBundle resourceBundle;
    private static HashMap<String, String> properties = new HashMap<String, String>();
    private static int topicsCount = 0;
    
    private static List<TargetTopic> targetTopics = new ArrayList<TargetTopic> ();
    private static HashMap<String, TargetTopic> targetTopicsMAP = new HashMap<String, TargetTopic>();

    private static String subscriberPrefix ;
    
    private static String executionDir = "DIR_DEFAULT";
    
    public BrokerManager() {
        super();
    }

    public static void main(String[] args) throws Exception {
        
        if( args != null && args.length >= 1  ){
            //BrokerManager broker = new BrokerManager();
            executionDir = System.getProperty("user.dir") + "/";
            
            if("MENU".equals( args[0] ) ){
                menu();                  
            } else if("STOP".equals( args[0] ) ){
                stop();                  
            } else if("WATCH".equals( args[0] ) ){
                config();
                startWatcher();                  
            } else if("START".equals( args[0] ) ){
                if( args.length >= 2 && args[1] != null ){
                    config();                  
                    try {
                        Integer.valueOf( args[1] );
                        startBroker( args[1] );
                    }catch( Exception ex ){
                        LoggingUtil.error( "Id de broker de válido: " + args[1], ex );
                    }

                } else {
                    use();
                }
            } else if("LIST".equals( args[0] ) ){
                config() ;
                list();                  
            } else if("STATS".equals( args[0] ) ){
                stats();                  
            } else {//
                use();
            }
            
            
            
        } else {
            use();
   
        }
    }
    
    
    private static void menu() {
        LoggingUtil.info( "MENU" );
    }
    
    private static void config() {
        //LoggingUtil.info( "Iniciando todos" );
        readConfigFile() ;
        readTargetTopics();
        //launchBrokers();
    }
    
    private static void startBroker(String pBrokerId) throws Exception {
        TargetTopic target = targetTopicsMAP.get( pBrokerId );
        LoggingUtil.info( "Iniciando broker ["+ pBrokerId +"] " +  target);
        IBrokerMXBean broker = new Broker( target );//
        LoggingUtil.info( "Preparando subscripción de broker ["+ pBrokerId +"] " + broker );
        //broker.setTarget( targetTopicsMAP.get( pBrokerId ));
        broker.subscribe();
    }


    private static void startWatcher() throws Exception {
        
        LoggingUtil.info( "Iniciando Watcher en " + System.getProperty("user.dir") + "/data");
        LoggingUtil.infoTabbed("[Watcher]", "Conectando a destinos JMS" );
        DispatcherManager.getInstance( targetTopics );
        MessageWatcher watcher = new MessageWatcher();
        watcher.watch();
        LoggingUtil.info( "Escaneando directorio de mensajes");

    }


    private static void printInternalInfo(String brokerId, ProcessBuilder builder) {
        Iterator it = builder.environment().keySet().iterator();
        String key;
        while( it.hasNext() ){
            key = (String)it.next();
            LoggingUtil.infoTabbed("BROMNGR_" + brokerId, key + "=" + builder.environment().get( key ));

        }    
    }


    private static void stop() {
        LoggingUtil.info( "Parando todos" );
        
    }

    private static void list() {
        LoggingUtil.info( " ***** Adaptadores agribaq conectados *****  " );
        
        Iterator<String> it = targetTopicsMAP.keySet().iterator();
        String itKey;
        TargetTopic target;
        JMXConnector jmxConnector;
        JMXServiceURL serviceUrl;
        MBeanServerConnection mbServerConnection;
        
        while( it.hasNext() ){
            itKey = it.next();;
            target = targetTopicsMAP.get( itKey );
            
            try {
                serviceUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:" + target.getJmxPort() + "/jmxrmi");
                jmxConnector = JMXConnectorFactory.connect(serviceUrl, null);
                mbServerConnection  = jmxConnector.getMBeanServerConnection();
                echo(target.getUrlPrefix() + ":JMX_LIST", "\nDomains:");
                String domains[] = mbServerConnection.getDomains();
                Arrays.sort(domains);
                for (String domain : domains) {
                    echo(target.getUrlPrefix() +":JMX_LIST","\tDomain = " + domain);
                }
                        
                        
                echo(target.getUrlPrefix() +":JMX_LIST","\nMBeanServer default domain = " + mbServerConnection.getDefaultDomain());

                echo(target.getUrlPrefix() +"J:MX_LIST","\nMBean count = " +  mbServerConnection.getMBeanCount());
                echo(target.getUrlPrefix() +":JMX_LIST","\nQuery MBeanServer MBeans:");
                Set<ObjectName> names =  new TreeSet<ObjectName>(mbServerConnection.queryNames(null, null));

                for (ObjectName name : names) {
                    echo(target.getUrlPrefix() +":JMX_LIST","\tObjectName = " + name);
                }
                
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
        
    }

    private static void echo(String pTab, String message) {
        LoggingUtil.infoTabbed(pTab, message); 

    }    
    

    private static void stats() {
        LoggingUtil.info( "Estadísticas" );

    }    

    private static void use() {
        LoggingUtil.error( "Uso: java -jar AGRIBBroker.jar [MENU | START PID | LIST | STOP | WATCH]", null );

    }
    
    private static void readConfigFile() {
        resourceBundle =  ResourceBundle.getBundle( "mx.agarcia.ea.rib.broker.broker" );
        Iterator itKeys = resourceBundle.keySet().iterator();
        String propName;
        while( itKeys.hasNext() ){
            propName = itKeys.next().toString();
            properties.put(propName, resourceBundle.getString( propName ));
            //LoggingUtil.info( propName + " -> " + resourceBundle.getString( propName ) );
        }
        
        topicsCount = Integer.parseInt( resourceBundle.getString( "ag.rib.broker.topics" ) );
        subscriberPrefix = resourceBundle.getString( "ag.rib.broker.aqSubscriberPrefix" );
        //ag.rib.broker.aqSubscriberPrefix
    }


    private static void readTargetTopics() {
        TargetTopic target;
        for(int i = 0; i < topicsCount; i++){
            target = new TargetTopic ( 
                                resourceBundle.getString( "ag.rib.broker.t"+ i +".topicName" ),
                                subscriberPrefix + resourceBundle.getString( "ag.rib.broker.t"+ i +".aqSubscriberSufix" ),
                                resourceBundle.getString( "ag.rib.broker.t"+ i +".soaDestinyTopic" ),
                                i, "ag.rib.broker.t" 
                                );
            target.setAqURL( resourceBundle.getString("ag.rib.broker.jms.URL") );
            target.setJmxPort( Integer.valueOf(  resourceBundle.getString( "ag.rib.broker.t"+ i +".jmx.port" ) ) );
            
            targetTopics.add( target );
            targetTopicsMAP.put(String.valueOf( i ), target);
            //LoggingUtil.debug( target  );
        }

    }
    
    
    private static void launchBrokers(){
        ProcessBuilder builder ;
        Process process;
        String commandAction = "";
        //;
        try {
            for( TargetTopic target : targetTopics ){
                    
                String[] commandParameters = 
                    {
                    "java",
                    "-server", 
                    "-classpath", classpath_,
                     "mx.agarcia.ea.rib.broker.BrokerManager", "START", String.valueOf( target.getIndex() ) };                    
                    
                    
                /*commandAction = "java -jar "+executionDir+"AGRIBBroker.jar START "+  target.getIndex() +"  -Dtopic="+ target.getTopicName() +
                                                         " -Dsubscriber=" + target.getSubscriberName();
                */
                //LoggingUtil.infoTabbed( target.getSubscriberName(),"-classpath=" + classpath );
                
                builder = new ProcessBuilder(commandParameters);
                builder.redirectError(new File( target.getSubscriberName() + ".log" ));
                
                LoggingUtil.info( "Iniciando Broker " + target );
                printInternalInfo(target.getTargetPublisherTopic(), builder);
            
                
                LoggingUtil.line();
                LoggingUtil.line();

                process = builder.start();
                
                BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                
                LoggingUtil.infoTabbed(target.getSubscriberName(), output.readLine() );
                LoggingUtil.errorTabbed(target.getSubscriberName(), error.readLine() );
                
                //LoggingUtil.info( "Broker " + target.getSubscriberName() + " iniciado correctamente ");
                process.destroy();
                if (process.isAlive()) {
                    process.destroyForcibly();
                    LoggingUtil.infoTabbed(target.getSubscriberName(), "Terminando Broker" );
                    LoggingUtil.line();
                }
    
            }
        } catch (Exception e) {
            LoggingUtil.error("No es posible iniciar Broker", e);
        }
    }
    
    
  
  
  private  static String classpath = "/Proyectos/AGEAI/AGRIBBroker/libs/log4j-1.2.17.jar";
      //"/Proyectos/AGEAI/AGOracleRIBBrokerApp/.adf:/Proyectos/AGEAI/AGOracleRIBBrokerApp/AGRIBBroker/classes:/Proyectos/AGEAI/AGRIBBroker/libs/wlfullclient.jar:/Proyectos/AGEAI/AGRIBBroker/libs/ojdbc8.jar:/Proyectos/AGEAI/AGRIBBroker/libs/aqapi.jar:/Server/Oracle/Middleware/JDevHome/oracle_common/modules/oracle.xdk/xmlparserv2.jar:/Server/Oracle/Middleware/JDevHome/oracle_common/modules/oracle.xdk/xml.jar:/Server/Oracle/Middleware/JDevHome/oracle_common/modules/oracle.nlsrtl/orai18n-mapping.jar:/Proyectos/AGEAI/AGRIBBroker/libs/log4j-1.2.17.jar";
    

    private static String classpath_ = "/Proyectos/AGEAI/AGOracleRIBBrokerApp/.adf:/Proyectos/AGEAI/AGOracleRIBBrokerApp/AGRIBBroker/classes:/Proyectos/AGEAI/AGRIBBroker/libs/wlfullclient.jar:/Proyectos/AGEAI/AGRIBBroker/libs/ojdbc8.jar:/Proyectos/AGEAI/AGRIBBroker/libs/aqapi.jar:/Server/Oracle/Middleware/JDevHome/oracle_common/modules/oracle.xdk/xmlparserv2.jar:/Server/Oracle/Middleware/JDevHome/oracle_common/modules/oracle.xdk/xml.jar:/Proyectos/AGEAI/AGRIBBroker/libs/log4j-1.2.17.jar";

}
