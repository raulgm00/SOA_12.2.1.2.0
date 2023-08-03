package mx.agarcia.ea.jda.core.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Data implements Serializable {
    
    
    @XmlAttribute(name="pos")
    private Integer pos;
    
    
    @XmlAttribute
    private String name;

    @XmlElement
    private String data;
    
    @XmlAttribute
    private String type = "VA";
    
    public Data(){
    }

    public Data(int pos, String data){
        this.pos = pos;
        this.data = data;
    }
    
    public Data(int pos, String name , String data){
        this.pos = pos;
        this.name = name;
        this.data = data;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public Integer getPos() {
        return pos;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
