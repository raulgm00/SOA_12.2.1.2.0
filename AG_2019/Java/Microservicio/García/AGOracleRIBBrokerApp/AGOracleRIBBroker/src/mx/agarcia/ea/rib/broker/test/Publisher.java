package mx.agarcia.ea.rib.broker.test;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

import java.sql.Connection;

import java.util.Date;

import javax.jms.BytesMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;

import javax.jms.TopicPublisher;

import javax.resource.spi.ConnectionDefinition;

import oracle.jms.AQjmsAgent;
import oracle.jms.AQjmsSession;
import oracle.jms.AQjmsTopicConnectionFactory;

public class Publisher {
    
    public static void main(String[] args) throws Exception {
               String queueOwner = ConnectionUtil.queueOwner;
               String queueName = ConnectionUtil.queueName;
                       
               System.out.println( new Date() + "[García::AUXPublisher] conectando a AQ..." );

        
               Class.forName("oracle.AQ.AQOracleDriver");
               Connection con = ConnectionUtil.getOracleDataSource().getConnection();
               TopicConnection tc_conn =AQjmsTopicConnectionFactory.createTopicConnection(con);
               tc_conn.start();

               System.out.println( new Date() + "[García::AUXPublisher] Creando sesión de AQ..." );

               TopicSession jms_sess = tc_conn.createTopicSession(true, TopicSession.SESSION_TRANSACTED);

               System.out.println( new Date() + "[García::AUXPublisher] Buscando tópico AQ " + queueOwner + "." + queueName);
               Topic queueTopic= ((AQjmsSession )jms_sess).getTopic(queueOwner,  queueName);
               
               System.out.println( new Date() + "[García::AUXPublisher] Creando Publisher a tópico AQ " + queueOwner + "." + queueName);
               TopicPublisher publisherAq = jms_sess.createPublisher(queueTopic);

               System.out.println( new Date() + "[García::AUXPublisher] Enviando mensaje a AQ " + queueOwner + "." + queueName);
               BytesMessage messAll = jms_sess.createBytesMessage();
               
               
               
               
               
               messAll.writeUTF( readSampleData()  );
               System.out.println( new Date() + "[García::AUXPublisher->AQ<Tópico "+ queueName +">] Enviando Mensaje ..." );



               publisherAq.publish(messAll);
               System.out.println( new Date() + "[García::AUXPublisher->AQ<Tópico "+ queueName +">] Mensaje enviado ..." );

               System.out.println( new Date() + "[García::AUXPublisher->AQ<Cola "+ queueName +">] liberando recursos" );

               jms_sess.commit();

               con.commit();
               tc_conn.close();
               con.close();                
           } 
    
    
    public static String readSampleData() throws Exception {
        StringBuffer data = new StringBuffer();
        //FileReader reader = new FileReader( "/Proyectos/AGEAI/VendorDesc.xml" );
        
        data.append( new String(Files.readAllBytes(  Paths.get( "/Proyectos/AGEAI/SmallVendorDesc.xml") )) );
        
        return data.toString();
    
    }
}
