
package org.bcie.crearbitacoraprocesobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ListaBitacora complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ListaBitacora">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Bitacora" type="{http://www.bcie.org/CrearBitacoraProcesoBO}Bitacora" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListaBitacora", propOrder = {
    "bitacora"
})
public class ListaBitacora {

    @XmlElement(name = "Bitacora")
    protected List<Bitacora> bitacora;

    /**
     * Gets the value of the bitacora property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bitacora property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBitacora().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bitacora }
     * 
     * 
     */
    public List<Bitacora> getBitacora() {
        if (bitacora == null) {
            bitacora = new ArrayList<Bitacora>();
        }
        return this.bitacora;
    }

}
