package mx.agarcia.ea.rib.broker;


import java.util.Iterator;

import mx.agarcia.ea.rib.broker.model.TargetTopic;

import org.apache.log4j.Logger;


public abstract class AbstractBroker extends AbstractAgent implements IBrokerMXBean {

    protected static Logger _log = Logger.getLogger("Broker");

    protected TargetTopic target;

    public AbstractBroker(TargetTopic target) {
        super(target);
        this.target = target;
        //printEnv();
        _log.info(target.getSubscriberName() + " - AQ Broker iniciado");
    }



    protected void printEnv() {
        Iterator it = System.getenv()
                            .keySet()
                            .iterator();
        String key;
        while (it.hasNext()) {
            key = (String) it.next();
            _log.info("[PROPERTIES] <" + key + ">" + System.getenv().get(key));
        }
    }


    public void setTarget(TargetTopic target) {
        this.target = target;
        _log.info(target.getSubscriberName() + " - configurado para apuntar a " + target.getTargetPublisherTopic());

    }

    public TargetTopic getTarget() {
        return target;
    }

    /*
    public static void main(String[] args) {
        if( args != null && args.length == 2 ){
            LoggingUtil.info(" BROKER " + args[0] + " --> " + args[1]);

        } else {
            LoggingUtil.error(" BROKER MO VALIDO", null);
        }
    }*/


    /*
     * 
    public Context configContext() throws Exception {
        //_log.info("[RIBBroker::Publisher] conectando a Weblogic JMS ...");

        if(context == null){
            Hashtable env = new Hashtable();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");

            env.put(Context.PROVIDER_URL, target.getAqURL() );


            context = new InitialContext(env);

            targetSOAJMSConnectionFactory = (TopicConnectionFactory) context.lookup("jms/ag/AGRIBBrokerConnectionFactory");



        }

        targetSOAJMSTopic = (Topic) context.lookup( target.getTargetPublisherTopic() );

        targetSOAJMSConnection = targetSOAJMSConnectionFactory.createTopicConnection();

        targetSOAJMSSession = targetSOAJMSConnection.createTopicSession(true, TopicSession.SESSION_TRANSACTED);


        targetSOAJMSPublisher = targetSOAJMSSession.createPublisher( targetSOAJMSTopic );


        return context;
    }
    */

    /*
    public void close() throws JMSException {
        try{
            if (targetSOAJMSPublisher != null) targetSOAJMSPublisher.close();
            if (targetSOAJMSSession != null) targetSOAJMSSession.close();
            if (targetSOAJMSConnection != null) targetSOAJMSConnection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    */


    protected void configLogging() {
        /*
        rootLogger = Logger.getLogger("Broker");  ///Proyectos/AGEAI/AGRIBBroker/
        FileHandler logHandler = new FileHandler("ribbroker.log",
                                     2*1024*1024,
                                     10, true);
        logHandler.setFormatter(new SimpleFormatter());
        logHandler.setLevel(Level.INFO);
        //rootLogger.removeHandler(rootLogger.getHandlers()[0]);
        rootLogger.setLevel(Level.INFO);
        rootLogger.addHandler(logHandler);

        rootLogger.info( "Broker creado" );*/
    }


}
