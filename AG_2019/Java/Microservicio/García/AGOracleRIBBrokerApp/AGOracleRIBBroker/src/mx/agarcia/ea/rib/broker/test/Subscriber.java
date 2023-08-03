package mx.agarcia.ea.rib.broker.test;

import java.sql.Connection;

import java.util.Date;

import java.util.Hashtable;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import javax.jms.TopicSubscriber;

import javax.naming.Context;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import oracle.jms.AQjmsSession;
import oracle.jms.AQjmsTopicConnectionFactory;

public class Subscriber {


    public static void main(String[] args) throws Exception {
        String queueOwner = ConnectionUtil.queueOwner;
        String queueName = ConnectionUtil.queueName;

        Class.forName("oracle.AQ.AQOracleDriver");
        System.out.println(new Date() + "[García::RIBBroker] conectando a AQ...");

        Connection con = ConnectionUtil.getOracleDataSource().getConnection();


        System.out.println(new Date() + "[García::RIBBroker] Creando sesión de AQ...");

        javax.jms.TopicConnection tc_conn = AQjmsTopicConnectionFactory.createTopicConnection(con);
        TopicSession jms_sess = tc_conn.createTopicSession(true, TopicSession.AUTO_ACKNOWLEDGE); //, AQOracleSession.
        tc_conn.start();

        System.out.println(new Date() + "[García::RIBBroker] Buscando tópico AQ " + queueOwner + "." + queueName);
        Topic queueTopic = ((AQjmsSession) jms_sess).getTopic(queueOwner, queueName);

        TopicSubscriber subscriber =
            (TopicSubscriber) ((AQjmsSession) jms_sess)
            .createDurableSubscriber(queueTopic, "AGSOA_FannyPilla_RIBAQBroker");

        //TopicSubscriber subRed =  (TopicSubscriber)((AQjmsSession) jms_sess).createDurableSubscriber(queueTopic, "RED");
        System.out.println(new Date() + "[García::RIBBroker] Esperando Mensajes " + queueOwner + "." + queueName);
        Message msg = subscriber.receive(); //10

        //System.out.println( new Date() + "[García::RIBBroker->AQ<Tópico "+ queueName +">] Obteniendo Mensajes ..." );

        boolean messagesReceived = false;
        String data = "";
        
        oracle.jms.AQjmsTextMessage aqMsg;
        
        while (msg != null) {
            
            System.out.println(new Date() + "[García::RIBBroker->AQ<Tópico " + queueName +
                               ">] Recibido mensaje con ID " + msg.getJMSMessageID() + " >> " + msg.getJMSType() );

            aqMsg = (oracle.jms.AQjmsTextMessage)msg;
            data = aqMsg.getText();
            
            //data = new String(((BytesMessage) msg).readUTF());
                
            System.out.println(new Date() + "[García::RIBBroker->AQ<Tópico " + queueName +
                               ">] Contenido " + msg.getJMSMessageID() + " >> " + data);

            publishToOtherJMS("DATA: " + data);

            //System.out.println(new Date() + "\t\t\t" + ((BytesMessage) msg).readUTF());
            //System.err.println("     recive message "+ ((BytesMessage)msg).readUTF());
            msg = subscriber.receive(); // receive with timeout;
            messagesReceived = true;


        }

        if (!messagesReceived) {
            System.out.println(new Date() + "[García::RIBBroker->AQ<Cola " + queueName +
                               ">] No se encontraron mensajes");
        }

        System.out.println(new Date() + "[García::RIBBroker->AQ<Cola " + queueName +
                           ">] eliminando suscripción de mensajes");
        System.out.println("  ");

        con.commit();
        tc_conn.close();
        con.close();
        System.out.println(new Date() + "[García::RIBBroker->AQ<Cola " + queueName + ">] liberando recursos");

    }


    public static Context getInitialContext() throws NamingException {
        //System.out.println(new Date() + "[García::RIBBroker::Publisher] conectando a Weblogic JMS ...");

        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");

        env.put(Context.PROVIDER_URL, "t3://192.1.50.179:7010");


        Context ctx = new InitialContext(env);

        return ctx;
    }


    public static void publishToOtherJMS(String message) throws Exception {


        Context ctx = getInitialContext();

        //System.out.println(new Date() + "[García::RIBBroker::PublishToWebLogic] Creando sesión JMS ...");

        TopicConnectionFactory tConFactory = (TopicConnectionFactory) ctx.lookup("jms/ag/rib/AGRIBBrokerConnectionFactory");

        Topic messageTopic = (Topic) ctx.lookup("jms/ag/rib/RMSSupplierTopic");

        TopicConnection tCon = tConFactory.createTopicConnection();

        TopicSession session = tCon.createTopicSession(true, TopicSession.SESSION_TRANSACTED);


        TopicPublisher publisher = session.createPublisher(messageTopic);

        Message msg = session.createTextMessage();

        //System.out.println(new Date() + "[García::RIBBroker::PublishToWebLogic] Enviando mensaje JMS ...");

        ((TextMessage) msg).setText(message);
        publisher.publish(msg);


        session.commit();
        publisher.close();
        session.close();
        tCon.close();
        System.out.println(new Date() + "<<<<<   [PUBLISH_TO_STANDARD_WEBLOGIC_TOPIC] mensaje JMS <"+message+">  enviado ...");

    }


}
