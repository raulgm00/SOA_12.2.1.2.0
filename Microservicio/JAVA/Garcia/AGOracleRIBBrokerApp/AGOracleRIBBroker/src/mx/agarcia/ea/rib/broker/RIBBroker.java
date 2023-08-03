package mx.agarcia.ea.rib.broker;

import java.sql.Connection;

import java.util.Date;

import java.util.HashMap;

import java.util.Iterator;

import org.apache.log4j.Logger;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import mx.agarcia.ea.rib.broker.test.ConnectionUtil;

import oracle.jms.AQjmsSession;
import oracle.jms.AQjmsTopicConnectionFactory;

public class RIBBroker {
    private static RIBBroker instance;
    private static Logger _log = Logger.getLogger(RIBBroker.class);

    private boolean running = false;
    private boolean messagesReceived = false;


    private RIBBroker() {
    }

    private HashMap<Date, String> messages = new HashMap<Date, String>();

    public static RIBBroker getInstance() {
        System.out.println("[García::RIBBroker->AQ] Iniciando servicios de suscripción");
        if (instance == null) {
            instance = new RIBBroker();
        }

        return instance;
    }

    private Connection con;
    private TopicSession jms_sess;
    private javax.jms.TopicConnection tc_conn;
    private Topic queueTopic;
    private TopicSubscriber subscriber;
    String queueOwner = ConnectionUtil.queueOwner;
    String queueName = ConnectionUtil.queueName;


    public void stop() {
        try {
            running = false;

            if (!messagesReceived) {
                System.out.println("[García::RIBBroker->AQ<Cola " + queueName + ">] No se recibieron mensajes");
            }

            System.out.println("[García::RIBBroker->AQ<Cola " + queueName + ">] eliminando suscripción de mensajes");

            System.out.println("[García::RIBBroker->AQ<Cola " + queueName + ">] liberando recursos");

            if (subscriber != null)
                subscriber.close();
            if (jms_sess != null)
                jms_sess.close();
            if (tc_conn != null)
                tc_conn.close();
            if (con != null) {
                con.commit();
                con.close();
            }


        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }


    public void subscribe(String topicName) throws Exception {
        queueName = topicName;

        Class.forName("oracle.AQ.AQOracleDriver");
        System.out.println("[García::RIBBroker] conectando a AQ...");

        con = ConnectionUtil.getOracleDataSource().getConnection();


        System.out.println("[García::RIBBroker] Creando sesión de AQ...");

        tc_conn = AQjmsTopicConnectionFactory.createTopicConnection(con);
        jms_sess = tc_conn.createTopicSession(true, TopicSession.AUTO_ACKNOWLEDGE); //, AQOracleSession.
        tc_conn.start();

        System.out.println("[García::RIBBroker] Buscando tópico AQ " + queueOwner + "." + queueName);
        queueTopic = ((AQjmsSession) jms_sess).getTopic(queueOwner, queueName);

        subscriber =
            (TopicSubscriber) ((AQjmsSession) jms_sess)
            .createDurableSubscriber(queueTopic, "AGarciaSOA_CustomRIBBroker_To_" + queueName);

        //TopicSubscriber subRed =  (TopicSubscriber)((AQjmsSession) jms_sess).createDurableSubscriber(queueTopic, "RED");
        System.out.println("[García::RIBBroker] Esperando Mensajes " + queueOwner + "." + queueName);
        Message msg = subscriber.receive(); //10

        //System.out.println( "[García::RIBBroker->AQ<Tópico "+ queueName +">] Obteniendo Mensajes ..." );

        try {

            while (msg != null) {
                running = true;
                System.out.println("[García::RIBBroker->AQ<Tópico " + queueName + ">] Recibido mensaje con ID " +
                          msg.getJMSMessageID());
                System.out.println("\t" + msg.getJMSMessageID() + "=>\t\t" + ((BytesMessage) msg).readUTF());
                
                this.messages.put(new Date(), msg.getJMSMessageID()+ " / "+ ((BytesMessage) msg).readUTF());
                
                //System.err.println("     recive message "+ ((BytesMessage)msg).readUTF());
                msg = subscriber.receive(); // receive with timeout;
                messagesReceived = true;
            }


        } finally {


        }


    }


    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public String htmlMessages() {
        StringBuffer buffer = new StringBuffer();
        Iterator it = messages.keySet().iterator();
        System.out.println("Imprimiendo mensajes recibidos de AQTopic " + queueName);
        Date date;
        int i = 0;
        buffer.append("<table><tr><td>Fecha</td><td>Mensaje</td></tr>");
        while (it.hasNext()) {
            i++;
            date = (Date) it.next();
            System.out.println("Mensaje " + i + ":" + date + " --> " + messages.get(date));

            buffer.append("<tr>" + "<td>" + date +

                          "</td><td>" + messages.get(date) + "</td></tr>");

        }
        buffer.append("</table>");
        return buffer.toString();
    }

    public void setMessagesReceived(boolean messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public boolean isMessagesReceived() {
        return messagesReceived;
    }
}
