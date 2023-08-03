
package org.bcie.productobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConfiguracionProductoTareas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionProductoTareas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tareas" type="{http://www.bcie.org/ProductoBO}Tareas" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionProductoTareas", propOrder = {
    "tareas"
})
public class ConfiguracionProductoTareas {

    @XmlElement(name = "Tareas")
    protected List<Tareas> tareas;

    /**
     * Gets the value of the tareas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tareas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTareas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tareas }
     * 
     * 
     */
    public List<Tareas> getTareas() {
        if (tareas == null) {
            tareas = new ArrayList<Tareas>();
        }
        return this.tareas;
    }

}
