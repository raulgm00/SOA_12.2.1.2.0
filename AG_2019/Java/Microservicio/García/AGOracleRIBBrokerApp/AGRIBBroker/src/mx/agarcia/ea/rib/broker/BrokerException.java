package mx.agarcia.ea.rib.broker;

public class BrokerException extends Exception {
    public BrokerException() {
        super();
    }
    
    public BrokerException(String error) {
        super(error);
    }
    
    public BrokerException(String error, Throwable cause) {
        super(error, cause);
    }
}
