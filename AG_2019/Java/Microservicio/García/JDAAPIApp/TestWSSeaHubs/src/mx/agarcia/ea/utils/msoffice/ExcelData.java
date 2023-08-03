
package mx.agarcia.ea.utils.msoffice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for excelData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="excelData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="row" type="{http://msoffice.utils.ea.agarcia.mx/}excelRow" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="requester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="success" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="rowsCount" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="fileType" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "excelData", propOrder = { "row", "code", "details", "fileName", "message", "requester", "success" })
public class ExcelData {

    protected List<ExcelRow> row;
    protected String code;
    protected String details;
    protected String fileName;
    protected String message;
    protected String requester;
    protected Boolean success;
    @XmlAttribute(name = "rowsCount")
    protected Integer rowsCount;
    @XmlAttribute(name = "fileType")
    protected Integer fileType;

    /**
     * Gets the value of the row property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the row property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRow().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExcelRow }
     *
     *
     */
    public List<ExcelRow> getRow() {
        if (row == null) {
            row = new ArrayList<ExcelRow>();
        }
        return this.row;
    }

    /**
     * Gets the value of the code property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the details property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDetails(String value) {
        this.details = value;
    }

    /**
     * Gets the value of the fileName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the message property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the requester property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRequester() {
        return requester;
    }

    /**
     * Sets the value of the requester property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRequester(String value) {
        this.requester = value;
    }

    /**
     * Gets the value of the success property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setSuccess(Boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the rowsCount property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getRowsCount() {
        return rowsCount;
    }

    /**
     * Sets the value of the rowsCount property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setRowsCount(Integer value) {
        this.rowsCount = value;
    }

    /**
     * Gets the value of the fileType property.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * Sets the value of the fileType property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setFileType(Integer value) {
        this.fileType = value;
    }

}
