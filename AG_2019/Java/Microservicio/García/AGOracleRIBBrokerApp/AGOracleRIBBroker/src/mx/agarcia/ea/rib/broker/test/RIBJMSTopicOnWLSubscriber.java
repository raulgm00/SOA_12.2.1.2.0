package mx.agarcia.ea.rib.broker.test;

import java.util.Hashtable;

import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


//
public class RIBJMSTopicOnWLSubscriber {
    

    public static void main(String[] args) throws Exception {
        

        Context ctx = getInitialContext();

        TopicConnectionFactory tConFactory = (TopicConnectionFactory) ctx.lookup("jms/Generic/Topic/XAConnectionFactoryjms1");

        Topic messageTopic = (Topic) ctx.lookup("jms/Generic/Topic/jms1/etASNOutAT");
        
        
        TopicConnection tCon = tConFactory.createTopicConnection();

        TopicSession session = tCon.createTopicSession(true, /* not a transacted session */
                                                       TopicSession. SESSION_TRANSACTED);

        TopicSubscriber  subscriber = session.createSubscriber(messageTopic);

        tCon.start();

        System.out.println("[TOPICO_RIB_WEBLOGIC_JMS] Escuchando mensajes..." );
        Message msg = (TextMessage) subscriber.receive();
        while (msg != null) {
                System.err.println("[TOPICO_RIB_WEBLOGIC_JMS] LLEGO MENSAJE -->" + (((TextMessage)msg).getText()) );
            
            }

    }
    
    public static Context getInitialContext() throws NamingException {
        //System.out.println(new Date() + "[García::RIBBroker::Publisher] conectando a Weblogic JMS ...");

        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");

        env.put(Context.PROVIDER_URL, "t3://gdmomapp4.agarcia.com.mx:7115");


        Context ctx = new InitialContext(env);

        return ctx;
    }
}
