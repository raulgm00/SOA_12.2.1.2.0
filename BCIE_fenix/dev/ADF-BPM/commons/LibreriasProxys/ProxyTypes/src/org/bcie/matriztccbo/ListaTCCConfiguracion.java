
package org.bcie.matriztccbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para listaTCCConfiguracion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="listaTCCConfiguracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TCCConfiguracion" type="{http://www.bcie.org/MatrizTCCBO}TCCConfiguracion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaTCCConfiguracion", propOrder = {
    "tccConfiguracion"
})
public class ListaTCCConfiguracion {

    @XmlElement(name = "TCCConfiguracion")
    protected List<TCCConfiguracion> tccConfiguracion;

    /**
     * Gets the value of the tccConfiguracion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tccConfiguracion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTCCConfiguracion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TCCConfiguracion }
     * 
     * 
     */
    public List<TCCConfiguracion> getTCCConfiguracion() {
        if (tccConfiguracion == null) {
            tccConfiguracion = new ArrayList<TCCConfiguracion>();
        }
        return this.tccConfiguracion;
    }

}
