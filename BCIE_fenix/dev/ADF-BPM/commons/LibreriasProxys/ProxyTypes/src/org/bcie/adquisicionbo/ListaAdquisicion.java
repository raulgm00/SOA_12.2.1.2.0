
package org.bcie.adquisicionbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ListaAdquisicion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ListaAdquisicion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Adquisicion" type="{http://www.bcie.org/AdquisicionBO}AdquisicionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListaAdquisicion", propOrder = {
    "adquisicion"
})
public class ListaAdquisicion {

    @XmlElement(name = "Adquisicion")
    protected List<AdquisicionType> adquisicion;

    /**
     * Gets the value of the adquisicion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adquisicion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdquisicion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdquisicionType }
     * 
     * 
     */
    public List<AdquisicionType> getAdquisicion() {
        if (adquisicion == null) {
            adquisicion = new ArrayList<AdquisicionType>();
        }
        return this.adquisicion;
    }

}
