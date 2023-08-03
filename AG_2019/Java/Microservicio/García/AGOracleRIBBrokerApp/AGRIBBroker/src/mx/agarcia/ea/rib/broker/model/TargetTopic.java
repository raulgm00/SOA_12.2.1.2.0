package mx.agarcia.ea.rib.broker.model;

public class TargetTopic {
    
    private String topicName;
    private String subscriberName;
    
    private String targetPublisherTopic;
    
    private String aqURL;
    private int index = -1;
    private String urlPrefix;
    private Integer jmxPort;
    
    public TargetTopic(String topicName, String subscriberName, String targetPublisherTopic, int index, String urlPrefix){
        this.topicName = topicName;
        this.subscriberName = subscriberName;
        this.targetPublisherTopic = targetPublisherTopic;
        this.urlPrefix = urlPrefix;
        this.index = index;
        
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getSubscriberName() {
        return subscriberName;
    }
    
    public String toString(){
        return "TargetTopic{topicName: "+topicName+", subscriberName: "+subscriberName+
               ", targetPublisherTopic: "+targetPublisherTopic+"  }";    
    }

    public void setAqURL(String aqURL) {
        this.aqURL = aqURL;
    }

    public String getAqURL() {
        return aqURL;
    }

    public void setTargetPublisherTopic(String targetPublisherTopic) {
        this.targetPublisherTopic = targetPublisherTopic;
    }

    public String getTargetPublisherTopic() {
        return targetPublisherTopic;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setJmxPort(Integer jmxPort) {
        this.jmxPort = jmxPort;
    }

    public Integer getJmxPort() {
        return jmxPort;
    }
}
