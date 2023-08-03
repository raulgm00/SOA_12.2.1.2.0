
package org.bcie.desembolsobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TransaccionDesembolsoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TransaccionDesembolsoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="TransaccionDetalle" type="{http://www.bcie.org/DesembolsoBO}TransaccionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransaccionDesembolsoType", propOrder = {
    "idDesembolso",
    "transaccionDetalle"
})
public class TransaccionDesembolsoType {

    protected long idDesembolso;
    @XmlElement(name = "TransaccionDetalle")
    protected List<TransaccionType> transaccionDetalle;

    /**
     * Obtiene el valor de la propiedad idDesembolso.
     * 
     */
    public long getIdDesembolso() {
        return idDesembolso;
    }

    /**
     * Define el valor de la propiedad idDesembolso.
     * 
     */
    public void setIdDesembolso(long value) {
        this.idDesembolso = value;
    }

    /**
     * Gets the value of the transaccionDetalle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transaccionDetalle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransaccionDetalle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransaccionType }
     * 
     * 
     */
    public List<TransaccionType> getTransaccionDetalle() {
        if (transaccionDetalle == null) {
            transaccionDetalle = new ArrayList<TransaccionType>();
        }
        return this.transaccionDetalle;
    }

}
