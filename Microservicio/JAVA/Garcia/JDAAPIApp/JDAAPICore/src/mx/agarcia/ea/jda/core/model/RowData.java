package mx.agarcia.ea.jda.core.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class RowData implements Serializable {
    
    @XmlAttribute(name="num")
    private Integer row = 0;
    


    @XmlElement(name="field")
    private List<Data> fields = new ArrayList<Data>();
    
    public void addData(int pos, String name, String info){
        Data data = new Data(pos, name, info);
        fields.add( data );
    }

}
