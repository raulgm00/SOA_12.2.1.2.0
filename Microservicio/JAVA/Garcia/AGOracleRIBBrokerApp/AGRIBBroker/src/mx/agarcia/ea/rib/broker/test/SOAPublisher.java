package mx.agarcia.ea.rib.broker.test;

import java.util.Date;

import java.util.Hashtable;

import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SOAPublisher {
    public static void main(String[] args) throws Exception {
        
        publishToOtherJMS(   "TEST!!!!!!!!!!!!!!" );
    }
    
    
    

    public static void publishToOtherJMS(String message) throws Exception {


        Context ctx = getInitialContext();

        System.out.println(new Date() + "[JMSTESTPublisher::PublishToWebLogic] Creando sesión JMS ...");

        TopicConnectionFactory tConFactory = (TopicConnectionFactory) ctx.lookup("jms/ag/rib/AGRIBBrokerConnectionFactory");

        Topic messageTopic = (Topic) ctx.lookup("jms/ag/rib/RMSSupplierTopic");
        System.out.println(new Date() + "[JMSTESTPublisher::PublishToWebLogic] Encontrado <"+tConFactory+">: jms/ag/rib/RMSSupplierTopic" );

        System.out.println(new Date() + "[JMSTESTPublisher::PublishToWebLogic:START] createTopicConnection ...");
        TopicConnection tCon = tConFactory.createTopicConnection();
        System.out.println(new Date() + "[JMSTESTPublisher::PublishToWebLogic:COMPLETED] createTopicConnection ...");

        TopicSession session = tCon.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        System.out.println(new Date() + "[JMSTESTPublisher::PublishToWebLogic] createTopicSession..." );


        TopicPublisher publisher = session.createPublisher(messageTopic);
        System.out.println(new Date() + "[JMSTESTPublisher::PublishToWebLogic] createPublisher "+messageTopic+"..." );

        Message msg = session.createTextMessage();

        System.out.println(new Date() + "[JMSTESTPublisher::PublishToWebLogic] Enviando mensaje JMS ...");

        ((TextMessage) msg).setText(message);
        publisher.publish(msg);


        session.commit();
        publisher.close();
        session.close();
        tCon.close();
        System.out.println(new Date() + "<<<<<   [PUBLISH_TO_STANDARD_WEBLOGIC_TOPIC] mensaje JMS <"+message+">  enviado ...");

    }
    
    
    public static Context getInitialContext() throws NamingException {
        //System.out.println(new Date() + "[JMSTESTPublisher::Publisher] conectando a Weblogic JMS ...");

        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");

        env.put(Context.PROVIDER_URL, "t3://uat-messaging.agarcia.com.mx:8004");
        //env.put(Context.SECURITY_PRINCIPAL, "uatadminapps");
        //env.put(Context.SECURITY_CREDENTIALS, "WelcomeUATS0A!");


        Context ctx = new InitialContext(env);

        return ctx;
    }


}
