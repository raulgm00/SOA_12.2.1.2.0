
package org.bcie.matriztccbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para listaValidaciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="listaValidaciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Validacion" type="{http://www.bcie.org/MatrizTCCBO}validacion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaValidaciones", propOrder = {
    "validacion"
})
public class ListaValidaciones {

    @XmlElement(name = "Validacion")
    protected List<Validacion> validacion;

    /**
     * Gets the value of the validacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Validacion }
     * 
     * 
     */
    public List<Validacion> getValidacion() {
        if (validacion == null) {
            validacion = new ArrayList<Validacion>();
        }
        return this.validacion;
    }

}
