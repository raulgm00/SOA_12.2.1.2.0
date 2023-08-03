
package mx.agarcia.ea.utils.msoffice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for excelRow complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="excelRow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cell" type="{http://msoffice.utils.ea.agarcia.mx/}excelCell" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="num" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="sheetNum" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "excelRow", propOrder = { "cell" })
public class ExcelRow {

    protected List<ExcelCell> cell;
    @XmlAttribute(name = "num")
    protected Integer num;
    @XmlAttribute(name = "sheetNum")
    protected Integer sheetNum;

    /**
     * Gets the value of the cell property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cell property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCell().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExcelCell }
     *
     *
     */
    public List<ExcelCell> getCell() {
        if (cell == null) {
            cell = new ArrayList<ExcelCell>();
        }
        return this.cell;
    }

    /**
     * Gets the value of the num property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getNum() {
        return num;
    }

    /**
     * Sets the value of the num property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setNum(Integer value) {
        this.num = value;
    }

    /**
     * Gets the value of the sheetNum property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getSheetNum() {
        return sheetNum;
    }

    /**
     * Sets the value of the sheetNum property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setSheetNum(Integer value) {
        this.sheetNum = value;
    }

}
