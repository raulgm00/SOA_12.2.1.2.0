package mx.agarcia.ea.rib.dispatcher;

import java.util.HashMap;
import java.util.List;

import mx.agarcia.ea.rib.broker.model.TargetTopic;
import mx.agarcia.ea.rib.publisher.MessagePublisher;

import org.apache.log4j.Logger;

public class DispatcherManager {
    
    
    private static Logger _log = Logger.getLogger( "DispatcherManager" );

    private static DispatcherManager instance;
    
    private static HashMap<String, MessagePublisher> publishers = new HashMap<String, MessagePublisher>();

    
    private DispatcherManager(List<TargetTopic> targetTopics) throws Exception {
        MessagePublisher publisher;
        for(TargetTopic target : targetTopics){
            publisher = new MessagePublisher( target );
            publisher.config();
            publishers.put( target.getTopicName(), publisher );
            
        }
    }


    public static DispatcherManager getInstance(List<TargetTopic> targetTopics) throws Exception {
        if( instance == null && targetTopics!= null){
            instance = new DispatcherManager( targetTopics );
        }
        
        return instance;
    }




    public void dispatch(String fileName, String msgContent) throws Exception {
        _log.info( "Preparando <"+ fileName +"> mensaje para enviarlo:\n" + msgContent );
        System.out.println(  "Preparando <"+ fileName +"> mensaje para enviarlo:\n" + msgContent );
        if( fileName != null ){
            String topicName =  fileName.substring(0, fileName.indexOf("-"));
            MessagePublisher publisher;
            if(publishers.containsKey( topicName )){
                publisher = publishers.get( topicName );
                _log.info( "Publisher <"+ publisher.getTarget().getTargetPublisherTopic() +"> preparando envío..." );
                publisher.publish( msgContent );
            } else {
                _log.error( "No hay un publisher <"+ fileName +"> asociado" );
                throw new Exception( "PUB-0010" );
            }
        }
    }


    public static void main(String args[] ){
        String fileName= "ETVENDORFROMRMS-ID:89E4D58D97772655E053211410ACDF76.ms";
        
        System.out.println( "== : *" + fileName.substring(0, fileName.indexOf( "-" ) ) + "*");
        
        
    }
}
