package mx.agarcia.ea.utils.msoffice.model;


import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



public class ExcelCell implements Serializable{

    @SuppressWarnings("compatibility:8727078820873623914")
    private static final long serialVersionUID = 1L;
    
    @XmlAttribute(name="yPos")
    private Integer colNumber;
    
    @XmlAttribute(name="xPos")
    private Integer cellNumber;
    
    @XmlElement
    private String data;
    
    @XmlAttribute
    private String type = "VA";

    public void setColNumber(Integer colNumber) {
        this.colNumber = colNumber;
    }

    public Integer getColNumber() {
        return colNumber;
    }

    public void setCellNumber(Integer cellNumber) {
        this.cellNumber = cellNumber;
    }

    public Integer getCellNumber() {
        return cellNumber;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setType(String dataType) {
        this.type = dataType;
    }

    public String getype() {
        return type;
    }
}
