package mx.agarcia.ea.rib.dispatcher;

import java.io.IOException;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class MessageDispatcher {
     
    
    public static void main(String[] args){
        Path dir = Paths.get( "/u01/oracle/agribbroker/data" );
        MessageWatcher messageWatcher =  new MessageWatcher();
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            
        
        
            dir.register(watchService,
                                   ENTRY_CREATE,
                                   ENTRY_DELETE,
                                   ENTRY_MODIFY);
            
            WatchKey key;
            while ((key = watchService.take()) != null) {
               for (WatchEvent<?> event : key.pollEvents()) {
                   
                   
                   
                   System.out.println(
                     "Event kind:" + event.kind() 
                       + ". File affected: " + event.context() + ".");
               }
               key.reset();
            }
        } catch (Exception x) {
            System.err.println(x);
            x.printStackTrace();
        }
    }
}
