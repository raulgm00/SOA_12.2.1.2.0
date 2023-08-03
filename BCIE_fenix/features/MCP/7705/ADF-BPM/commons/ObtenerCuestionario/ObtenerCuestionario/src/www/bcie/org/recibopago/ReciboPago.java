
package www.bcie.org.recibopago;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReciboPago complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ReciboPago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Recibo" type="{http://www.bcie.org/herramientaOfismatica/ReciboPago}Recibo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReciboPago", propOrder = { "recibo" })
public class ReciboPago {

    @XmlElement(name = "Recibo", required = true)
    protected List<Recibo> recibo;

    /**
     * Gets the value of the recibo property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recibo property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecibo().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Recibo }
     *
     *
     */
    public List<Recibo> getRecibo() {
        if (recibo == null) {
            recibo = new ArrayList<Recibo>();
        }
        return this.recibo;
    }

}
