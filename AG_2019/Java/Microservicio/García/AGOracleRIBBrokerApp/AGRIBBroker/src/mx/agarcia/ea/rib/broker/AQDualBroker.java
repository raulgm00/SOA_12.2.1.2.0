package mx.agarcia.ea.rib.broker;


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

import mx.agarcia.ea.rib.broker.model.TargetTopic;

import oracle.jms.AQjmsSession;
import oracle.jms.AQjmsTopicConnectionFactory;

public class AQDualBroker extends Broker {

    public AQDualBroker(TargetTopic target){
        super( target );
    }

    /*
   



    public void publishToOtherJMS(String message) throws Exception {


        //Context ctx = getInitialContext();

        //System.out.println(new Date() + "[García::RIBBroker::PublishToWebLogic] Creando sesión JMS ...");



        Message msg = targetSOAJMSSession.createTextMessage();

        //System.out.println(new Date() + "[García::RIBBroker::PublishToWebLogic] Enviando mensaje JMS ...");

        ((TextMessage) msg).setText(message);
        
        targetSOAJMSPublisher.publish(msg);


        targetSOAJMSSession.commit();
        System.out.println(new Date() + "<<<<<   [PUBLISH_TO_STANDARD_WEBLOGIC_TOPIC] mensaje JMS <"+message+">  enviado ...");

    }
    
*/

    
}
