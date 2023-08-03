package mx.agarcia.ea.rib.broker;

import java.util.Date;


public class LoggingUtil {
    
    public static String PREFIX = "[AGRIBBroker]";
    
    public LoggingUtil() {
        super();
    }
    
    public static void debug(Object message){
        System.out.println( new Date() + " " + PREFIX + " debug " + message );
    }

    public static void warn(Object message){
        System.out.println( new Date() + " " + PREFIX + " warn " + message );
    }

    public static void info(Object message){
        System.out.println( new Date() + " " + PREFIX + " info " + message );
    }

    public static void infoTabbed(String parentId, Object message){
        System.out.println( "\t[INFO] " + parentId + " - " + message );
    }
    
    public static void errorTabbed(String parentId, Object message){
        System.err.println( "\t[ERROR] " + parentId + " - " + message );
    }

    public static void line(){
        System.out.println( "" );
    }


    
    public static void error(Object message, Throwable error){
        if( error != null ){
            System.out.println( new Date() + " " + PREFIX + " error " + message );
            error.printStackTrace();            
        } else {
            System.out.println( new Date() + " " + PREFIX + " error " + message );
        }
    }

}
