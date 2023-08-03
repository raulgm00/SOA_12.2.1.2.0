package mx.agarcia.ea.rib.broker;

import javax.management.*;

import java.lang.management.*;

import java.util.logging.Logger;

import mx.agarcia.ea.rib.broker.model.TargetTopic;


public abstract class AbstractAgent {

    private static Logger _log = Logger.getLogger("BrokerAgent");

    protected MBeanServer mbServer = null;
    private TargetTopic targetConfig;

    private ObjectName agentName = null;


    public AbstractAgent(TargetTopic target) {
        this.targetConfig = target;

        mbServer = ManagementFactory.getPlatformMBeanServer();


        try {
            // Uniquely identify the MBeans and register them with the platform MBeanServer
            agentName = new ObjectName("AGEIA:name=" + "RIBAQAdapterMXBean");
            mbServer.registerMBean(this, agentName);
            _log.info(targetConfig.getSubscriberName() + " - " + agentName + " registrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
