package mx.agarcia.ea.rib.publisher;

import java.util.Date;
import java.util.Hashtable;

import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;

import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import javax.naming.Context;

import javax.naming.InitialContext;

import mx.agarcia.ea.rib.broker.model.TargetTopic;



public class MessagePublisher {
    
    private static Logger _log = Logger.getLogger( "MessagePublisher" );
    
    Context context;

    TopicConnectionFactory topicConnectionFactory;

    Topic messageTopic;

    TopicConnection topicConnection;

    TopicSession topicSession;


    TopicPublisher topicPublisher;

    TargetTopic target;
    
    public MessagePublisher(TargetTopic target){
        this.target = target;
    }
   
    
    public void config() throws Exception {
        //_log.info("[RIBBroker::Publisher] conectando a Weblogic JMS ...");

        if(context == null){
            Hashtable env = new Hashtable();

            env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");

            env.put(Context.PROVIDER_URL, target.getAqURL() );


            context = new InitialContext(env);
            //jms/ag/rib/AGRIBBrokerConnectionFactory
            //jms/ag/AGRIBBrokerConnectionFactory
            topicConnectionFactory = (TopicConnectionFactory) context.lookup("jms/ag/rib/RIBCNFactory");



        }
        
        _log.info( "["+ target.getTargetPublisherTopic() +"] Conectando con recursos JMS..." );
        messageTopic = (Topic) context.lookup( target.getTargetPublisherTopic() );

        topicConnection = topicConnectionFactory.createTopicConnection();

        topicSession = topicConnection.createTopicSession(true, TopicSession.AUTO_ACKNOWLEDGE);


        topicPublisher = topicSession.createPublisher( messageTopic );
        _log.info( "Recursos JMS conectados -> " + topicPublisher.getDestination().toString()  );


    }
    

    public void publish(String message) throws Exception {

        //Context ctx = getInitialContext();
        System.out.println(new Date() + "[García::RIBBroker::PublishToWebLogic] Creando sesión JMS ...");



        TextMessage msg = topicSession.createTextMessage();

        System.out.println(new Date() + "[García::RIBBroker::PublishToWebLogic:createTextMessage_OK] Enviando mensaje JMS ...");

        msg.setText( message );
        
        topicPublisher.publish(msg);


        topicSession.commit();
        _log.info("[Publisher<"+ target.getTargetPublisherTopic() +"> Mensaje JMS enviado ...");
        System.out.println(new Date() + "[García::RIBBroker::PublishToWebLogic::topicSession_OK] Enviado mensaje JMS\n"+message + "\n\n");

    }
    
    
    public void close() throws JMSException {
        try {
            if (topicPublisher != null) topicPublisher.close();
            if (topicSession != null) topicSession.close();
            if (topicConnection != null) topicConnection.close();

        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public void setTarget(TargetTopic target) {
        this.target = target;
    }

    public TargetTopic getTarget() {
        return target;
    }
}
