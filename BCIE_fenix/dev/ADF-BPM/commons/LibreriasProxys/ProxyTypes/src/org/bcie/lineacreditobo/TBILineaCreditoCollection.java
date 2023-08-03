
package org.bcie.lineacreditobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TBILineaCreditoCollection complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TBILineaCreditoCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TbiLineaCredito" type="{http://www.bcie.org/LineaCreditoBO}TBILineaCreditoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TBILineaCreditoCollection", propOrder = {
    "tbiLineaCredito"
})
public class TBILineaCreditoCollection {

    @XmlElement(name = "TbiLineaCredito")
    protected List<TBILineaCreditoType> tbiLineaCredito;

    /**
     * Gets the value of the tbiLineaCredito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tbiLineaCredito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTbiLineaCredito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TBILineaCreditoType }
     * 
     * 
     */
    public List<TBILineaCreditoType> getTbiLineaCredito() {
        if (tbiLineaCredito == null) {
            tbiLineaCredito = new ArrayList<TBILineaCreditoType>();
        }
        return this.tbiLineaCredito;
    }

}
