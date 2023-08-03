package mx.agarcia.ea.rib.broker.test;

import java.sql.SQLException;
import java.util.Date;


import oracle.AQ.AQDequeueOption;
import oracle.AQ.AQDriverManager;
import oracle.AQ.AQEnqueueOption;
import oracle.AQ.AQException;
import oracle.AQ.AQMessage;
import oracle.AQ.AQObjectPayload;
import oracle.AQ.AQOracleQueue;
import oracle.AQ.AQQueue;
import oracle.AQ.AQSession;


import oracle.jdbc.pool.OracleDataSource;

import oracle.sql.ORADataFactory;

import oracle.xdb.XMLType;

public class AQTest {

    private static final String queueOwner = "RIB_AQ";

    private static final String queueName = "etRTV";

    public static void main(String[] args) {

        AQTest aqTest = new AQTest();


        String xmlMessage = "<message>test message</message>";
        try {
            //aqTest.enqueueMessage(xmlMessage);
            System.out.println( new Date() + "[García::RIBBroker] conectando a AQ..." );
            aqTest.dequeueMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void enqueueMessage(String xmlMessage) throws SQLException, AQException, ClassNotFoundException {

        java.sql.Connection aqconn = getOracleDataSource().getConnection();
        aqconn.setAutoCommit(false);

        AQSession aqsession = null;

        // Register the Oracle AQ Driver
        Class.forName("oracle.AQ.AQOracleDriver");
        try {

            AQEnqueueOption enqueueOption = new AQEnqueueOption();

            aqsession = AQDriverManager.createAQSession(aqconn);
            AQQueue queue = aqsession.getQueue(queueOwner, queueName);
            
            AQMessage msg = ((AQOracleQueue) queue).createMessage();

            AQObjectPayload payload = msg.getObjectPayload();
            XMLType payloadData = XMLType.createXML(aqconn, xmlMessage);
            payload.setPayloadData(payloadData);

            queue.enqueue(enqueueOption, msg);
            aqconn.commit();
            System.out.println("Message succesfully enqueued..");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            aqsession.close();
            aqconn.close();
        }
    }


    public void dequeueMessage() throws AQException, SQLException, ClassNotFoundException {
        System.out.println( new Date() + "[García::RIBBroker] Conectando a Base de Datos Oracle" );
        java.sql.Connection aqconn = getOracleDataSource().getConnection();
        System.out.println( new Date() + "[García::RIBBroker] Conectado a Base de Datos Oracle" );

        aqconn.setAutoCommit(false);

        AQSession aq_sess = null;

        System.out.println( new Date() + "[García::RIBBroker] Cargando driver java para AQ oracle.AQ.AQOracleDriver ..." );
        Class.forName("oracle.AQ.AQOracleDriver");

        try {
            System.out.println( new Date() + "[García::RIBBroker] Creando sesión de AQ..." );
            
        
            aq_sess = AQDriverManager.createAQSession(aqconn);

            AQQueue queue;
            AQMessage message;
            AQDequeueOption deq_option;



            System.out.println( new Date() + "[García::RIBBroker] Buscando cola AQ " + queueOwner + "." + queueName);
            
            queue = aq_sess.getQueue(queueOwner, queueName);

            deq_option = new AQDequeueOption();
            deq_option.setConsumerName( "Garcia_SOA_RIBBroker" );

            // receive a message via native interface
            ORADataFactory factory = XMLType.getORADataFactory();

            
            boolean dataPending = true;
            
            while(dataPending){
                    System.out.println( new Date() + "[García::RIBBroker] Descargando mensajes de AQ " + queueOwner + "." + queueName);
                    message = queue.dequeue(deq_option, factory);
                
                    //message = ((AQOracleQueue) queue).dequeue(deq_option);//, factory

                    if (message == null) {
                        System.out.println( new Date() + "[García::RIBBroker->AQ<Cola "+ queueName +">] No hay mensajes" );
                    } else {
                        System.out.println( new Date() + "[García::RIBBroker->AQ<Cola "+ queueName +">] Obteniendo mensaje" );

                        //System.out.println("message id: " + message.getMessageId() );
                        System.out.println(new Date() +  "[García::RIBBroker->AQ<Cola "+ queueName +">] mensaje con ID " + message.getMessageId() );

                        XMLType messageData = (XMLType) message.getObjectPayload().getPayloadData();
                        String xmlString = messageData.getStringVal();
                        System.out.println(new Date() +  "[García::RIBBroker->AQ<Cola "+ queueName +">] mensaje:  " + xmlString );

                        //Commit
                        aqconn.commit();

                    }
                
                    dataPending = false;
                
                }
            

        } finally {
            System.out.println( new Date() + "[García::RIBBroker] Cerrando conexión con cola AQ " + queueOwner + "." + queueName);
            aq_sess.close();
            aqconn.close();
        }

    }


    public static OracleDataSource getOracleDataSource() throws SQLException {

        String serverIp= "172.16.20.33";
        String sid = "RIBUAT";
        String userName = "rib_aq";
        System.out.println( new Date() + "[García::RIBBroker] Conección a base de datos " + userName + "@" + sid +"//" +  serverIp);
        
        OracleDataSource ds = new OracleDataSource();
        ds.setDriverType("thin");
        ds.setServerName(serverIp);
        ds.setPortNumber(1521);
        ds.setDatabaseName(sid); // sid
        ds.setUser("rib_aq");
        ds.setPassword("Welcome1");

        return ds;
    }

}
