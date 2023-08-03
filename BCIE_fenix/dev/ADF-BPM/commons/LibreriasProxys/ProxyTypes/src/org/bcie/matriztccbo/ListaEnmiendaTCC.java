
package org.bcie.matriztccbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para listaEnmiendaTCC complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="listaEnmiendaTCC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnmiendaTCC" type="{http://www.bcie.org/MatrizTCCBO}enmiendaTCC" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaEnmiendaTCC", propOrder = {
    "enmiendaTCC"
})
public class ListaEnmiendaTCC {

    @XmlElement(name = "EnmiendaTCC")
    protected List<EnmiendaTCC> enmiendaTCC;

    /**
     * Gets the value of the enmiendaTCC property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enmiendaTCC property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnmiendaTCC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnmiendaTCC }
     * 
     * 
     */
    public List<EnmiendaTCC> getEnmiendaTCC() {
        if (enmiendaTCC == null) {
            enmiendaTCC = new ArrayList<EnmiendaTCC>();
        }
        return this.enmiendaTCC;
    }

}
