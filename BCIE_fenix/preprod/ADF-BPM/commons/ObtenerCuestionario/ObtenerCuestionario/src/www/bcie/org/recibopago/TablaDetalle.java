
package www.bcie.org.recibopago;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TablaDetalle complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TablaDetalle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="detalles" type="{http://www.bcie.org/herramientaOfismatica/ReciboPago}Detalles" maxOccurs="unbounded"/>
 *         &lt;element name="totalPagada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TablaDetalle", propOrder = { "detalles", "totalPagada" })
public class TablaDetalle {

    @XmlElement(required = true)
    protected List<Detalles> detalles;
    @XmlElement(required = true)
    protected String totalPagada;

    /**
     * Gets the value of the detalles property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalles property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalles().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Detalles }
     *
     *
     */
    public List<Detalles> getDetalles() {
        if (detalles == null) {
            detalles = new ArrayList<Detalles>();
        }
        return this.detalles;
    }

    /**
     * Gets the value of the totalPagada property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTotalPagada() {
        return totalPagada;
    }

    /**
     * Sets the value of the totalPagada property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTotalPagada(String value) {
        this.totalPagada = value;
    }

}
