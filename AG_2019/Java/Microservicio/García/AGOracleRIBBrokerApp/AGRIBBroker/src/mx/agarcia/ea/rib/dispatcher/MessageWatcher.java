package mx.agarcia.ea.rib.dispatcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import mx.agarcia.ea.rib.broker.LoggingUtil;

import org.apache.log4j.Logger;


public class MessageWatcher {

    private static Logger _log = Logger.getLogger( "MessageWatcher" );


    public static void main(String args[]){
        System.out.println( "[WATCHER]  Listening starting..." );
        MessageWatcher watcher = new MessageWatcher();
        watcher.watch();
        System.out.println( "[WATCHER] Listening finished.." );
    }


    public void watch() {
        //Path dir = Paths.get( "/u01/oracle/agribbroker/data" );
        Path dir = Paths.get( System.getProperty("user.dir") + "/data" );

        //MessageWatcher messageWatcher =  new MessageWatcher();
        try {
            System.out.println( "[WATCHER] WatchService created" );
            WatchService watchService = FileSystems.getDefault().newWatchService();


            System.out.println( "[WATCHER] WatchService registered" );

            dir.register(watchService,
                                   ENTRY_CREATE,
                                   ENTRY_DELETE,
                                   ENTRY_MODIFY
                         );


            WatchKey key;
            String fileName;
            while ((key = watchService.take()) != null) {
               for (WatchEvent<?> event : key.pollEvents()) {
                   System.out.println( "Detectado evento<"+ event.kind() +"> en directorio --> " +  event.context()  );
                   _log.info( "Detectado evento<"+ event.kind() +"> en directorio --> " +  event.context() );
                   if (StandardWatchEventKinds.ENTRY_CREATE == event.kind() ){
                       fileName = event.context().toString();
                        _log.info( "Evento<"+ event.kind() +"> leyendo mensaje para publicarlo @" +  fileName );
                        
                        System.out.println("Evento<"+ event.kind() +"> leyendo mensaje para publicarlo @" +  fileName );
                        DispatcherManager.getInstance(null).dispatch(fileName , getFileContent( fileName ));
                        moveFile(fileName);
                    }
                   /*System.out.println(
                     "Event kind:" + 
                       + ". File affected: " + event.context() + ".");*/
               }
               key.reset();
                
            }
        } catch (Exception x) {
            System.err.println(x);
            x.printStackTrace();
        }
    }


    private String getFileContent( String fileName ) throws Exception {
        StringBuffer buffer = new StringBuffer();
        _log.info("Leyendo archivo <"+fileName+"> en " + System.getProperty("user.dir") + "/data");
        
        System.out.println( "Leyendo archivo <"+fileName+"> en " + System.getProperty("user.dir") + "/data" );
        //FileReader reader = new FileReader( new File(System.getProperty("user.dir") + "/data", fileName) );
        buffer.append( new String ( Files.readAllBytes( Paths.get(  System.getProperty("user.dir") + "/data/" + fileName ) ) ) );
        return buffer.toString();
    }
    
    
    private void moveFile(String fileName ) throws Exception {
        ProcessBuilder builder ;
        Process process;
        
        System.out.println( "Moviendo archivo <"+fileName+"> de " + System.getProperty("user.dir") + "/data a " + 
                System.getProperty("user.dir") + "/trash"  );
        
        _log.info("Moviendo archivo <"+fileName+"> de " + System.getProperty("user.dir") + "/data a " + 
                System.getProperty("user.dir") + "/trash"  );
        
        String[] action = 
            {
                "mv",
                System.getProperty("user.dir") + "/data/" + fileName, 
                System.getProperty("user.dir") + "/trash/"
            };                    

            
        builder = new ProcessBuilder(action);
        builder.redirectError(new File(  System.getProperty("user.dir")  + "/agribroker.err" ));
        
        process = builder.start();
        
        BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        
        LoggingUtil.infoTabbed("[MV:"+ fileName +"]", output.readLine() );
        LoggingUtil.errorTabbed("[MV:"+ fileName +"] Error? (sólo si != null --->)", error.readLine() );
        
        //LoggingUtil.info( "Broker " + target.getSubscriberName() + " iniciado correctamente ");
        process.destroy();
        if (process.isAlive()) {
            process.destroyForcibly();
            LoggingUtil.infoTabbed("[MV:"+ fileName +"]", " proceso de mover archivo "+fileName+" terminado" );
        }
            
        
    }
    
}
