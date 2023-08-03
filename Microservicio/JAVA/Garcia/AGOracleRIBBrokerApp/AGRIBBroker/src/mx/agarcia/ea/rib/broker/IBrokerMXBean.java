package mx.agarcia.ea.rib.broker;

public interface IBrokerMXBean {
    

    public void subscribe() throws BrokerException;
    
    public void unsubscribe() throws BrokerException;
    
    public void shutdown() throws BrokerException;
    
}
