package mx.agarcia.ea.rib.broker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.Connection;

import java.util.Date;
import java.util.Hashtable;

import java.util.Iterator;


/*
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.logging.SimpleFormatter;
*/
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;

import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import javax.jms.TopicSubscriber;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.agarcia.ea.rib.broker.model.TargetTopic;

import oracle.jms.AQjmsBytesMessage;
import oracle.jms.AQjmsObjectMessage;
import oracle.jms.AQjmsSession;
import oracle.jms.AQjmsStreamMessage;
import oracle.jms.AQjmsTopicConnectionFactory;

import org.apache.log4j.Logger;

public class Broker extends AbstractBroker {

    //private static Logger _log = Logger.getLogger( Broker.class );
    private static Logger _log = Logger.getLogger("Broker");


    private static Context context;


    boolean messagesReceived = false;
    String data = "";
    String queueOwner;

    String queueName;
    javax.jms.TopicConnection tc_conn;
    TopicSession jms_sess ;
    TopicSubscriber subscriber ;
    Connection con ;

    //Logger rootLogger;

    protected static TopicConnectionFactory targetSOAJMSConnectionFactory;


    protected Topic targetSOAJMSTopic;
    protected TopicConnection targetSOAJMSConnection;
    protected TopicSession targetSOAJMSSession;
    protected TopicPublisher targetSOAJMSPublisher;


    public Broker(TargetTopic target) {
        super(target);
    }

    /*
    public void start() throws Exception {
        configContext();
    }*/


    public void subscribe() throws BrokerException {

        try {
            
            queueOwner = ConnectionUtil.queueOwner;

            queueName = super.target.getTopicName();

            Class.forName("oracle.AQ.AQOracleDriver");
            _log.info("[RIBBroker] conectando a AQ...");

            con = ConnectionUtil.getOracleDataSource().getConnection();


            _log.info("[RIBBroker] Creando sesión de AQ...");

            tc_conn = AQjmsTopicConnectionFactory.createTopicConnection(con);
            jms_sess =
                tc_conn.createTopicSession(true, TopicSession.AUTO_ACKNOWLEDGE); //, AQOracleSession.
            tc_conn.start();

            _log.info("[RIBBroker] Buscando tópico AQ " + queueOwner + "." + queueName);
            Topic queueTopic = ((AQjmsSession) jms_sess).getTopic(queueOwner, queueName);


            subscriber =
                (TopicSubscriber) ((AQjmsSession) jms_sess)
                .createDurableSubscriber(queueTopic, this.target.getSubscriberName());

            //TopicSubscriber subRed =  (TopicSubscriber)((AQjmsSession) jms_sess).createDurableSubscriber(queueTopic, "RED");
            _log.info("[RIBBroker] Esperando Mensajes " + queueOwner + "." + queueName);
            Message msg = subscriber.receive(); //10

            //_log.info( "[RIBBroker->AQ<Tópico "+ queueName +">] Obteniendo Mensajes ..." );

            while (msg != null) {
                //data = new String(((BytesMessage) msg).readUTF());

                if (msg instanceof oracle.jms.AQjmsTextMessage) {
                    _log.debug("oracle.jms.AQjmsTextMessage --> " + msg.getJMSMessageID());
                    data = ((oracle.jms.AQjmsTextMessage) msg).getText();
                } else if (msg instanceof AQjmsBytesMessage) {
                    _log.debug("oracle.jms.AQjmsBytesMessage --> " + msg.getJMSMessageID());
                    data = new String(((oracle.jms.AQjmsBytesMessage) msg).getBytesData());
                } else if (msg instanceof AQjmsObjectMessage) {
                    _log.debug("oracle.jms.AQjmsObjectMessage --> " + msg.getJMSMessageID());
                    data = new String(((oracle.jms.AQjmsObjectMessage) msg).getBytesData());
                } else if (msg instanceof AQjmsStreamMessage) {
                    _log.debug("oracle.jms.AQjmsStreamMessage --> " + msg.getJMSMessageID());
                    data = "oracle.jms.AQjmsStreamMessage"; // new String( ((oracle.jms.AQjmsStreamMessage)msg).get );
                } else {
                    _log.error("Mensaje no reconocido --> " + msg.getJMSMessageID() + " / Type=" + msg.getJMSType());


                }

                _log.info("[RIBBroker->AQ<Tópico " + queueName + ">] Recibido mensaje con ID " + msg.getJMSMessageID() +
                          " >> " + data);

                //System.exit( 0 );

                saveMessage(msg.getJMSMessageID(), data);
                //publishToOtherJMS("DATA: " + data);

                //_log.info("\t\t\t" + ((BytesMessage) msg).readUTF());
                //System.err.println("     recive message "+ ((BytesMessage)msg).readUTF());
                msg = subscriber.receive(); // receive with timeout;
                messagesReceived = true;
                jms_sess.commit();

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    private void saveMessage(String messageId, String content) throws IOException {

        _log.info(super.target.getSubscriberName() + " - guardando mensaje: " + messageId + ".msg");

        FileWriter writer = new FileWriter(new File("data", this.target.getTopicName() + "-" + messageId + ".msg"));

        writer.write(content);
        writer.close();
    }


    @Override
    public void unsubscribe() throws BrokerException {
        try {
            _log.info( super.target.getSubscriberName() + " - Bajando suscripción ");
            if (!messagesReceived) {
                _log.info(super.target.getSubscriberName() + " - No se encontraron mensajes");
            }

            _log.info(super.target.getSubscriberName() +" - eliminando suscripción de mensajes");

            if(con != null) {
                con.commit();
            }
            
            if(tc_conn != null) tc_conn.close();
            if(con != null) con.close();
            

            _log.info( super.target.getSubscriberName() + " - Suscripción terminada");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() throws BrokerException {
        try {
            _log.info( super.target.getSubscriberName() + " - apagando agente");
            unsubscribe() ;
            _log.info( super.target.getSubscriberName() + " - apagando apagado");
            Thread.sleep( 3000 );
            System.exit( -1 );
            
        } catch (Exception e) {
            _log.error( super.target.getSubscriberName() + " - error deteniendo agente", e);
            e.printStackTrace();
        }
    }
}
