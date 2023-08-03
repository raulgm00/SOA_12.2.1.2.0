
package mx.agarcia.ea.utils.msoffice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for excelCell complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="excelCell"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="yPos" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="xPos" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "excelCell", propOrder = { "data" })
public class ExcelCell {

    protected String data;
    @XmlAttribute(name = "yPos")
    protected Integer yPos;
    @XmlAttribute(name = "xPos")
    protected Integer xPos;
    @XmlAttribute(name = "type")
    protected String type;

    /**
     * Gets the value of the data property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setData(String value) {
        this.data = value;
    }

    /**
     * Gets the value of the yPos property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getYPos() {
        return yPos;
    }

    /**
     * Sets the value of the yPos property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setYPos(Integer value) {
        this.yPos = value;
    }

    /**
     * Gets the value of the xPos property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getXPos() {
        return xPos;
    }

    /**
     * Sets the value of the xPos property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setXPos(Integer value) {
        this.xPos = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setType(String value) {
        this.type = value;
    }

}
