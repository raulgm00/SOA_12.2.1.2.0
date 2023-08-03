package mx.agarcia.ea.utils.msoffice;

public class LoggerUtil {
    
    
    public static void debug(String prefix, String message){
        System.out.println( "[DEBUG] ["+ prefix +"]" + message );
    }
    
    public static void info(String prefix, String message){
        System.out.println( "[INFO] ["+ prefix +"]" + message );
    }
    
    public static void error(String prefix, String message, Throwable th){
        System.err.println( "[ERRRO] ["+ prefix +"]" + message );
        if( th != null){
            th.printStackTrace();
        }
    }
}
