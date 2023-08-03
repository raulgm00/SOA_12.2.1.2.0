
package org.bcie.configuracionprocesosbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Tarea;


/**
 * <p>Clase Java para Tareas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tareas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tarea" type="{http://www.bcie.org/CatalogoBO}Tarea" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tareas", propOrder = {
    "tarea"
})
public class Tareas {

    @XmlElement(name = "Tarea")
    protected List<Tarea> tarea;

    /**
     * Gets the value of the tarea property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tarea property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTarea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tarea }
     * 
     * 
     */
    public List<Tarea> getTarea() {
        if (tarea == null) {
            tarea = new ArrayList<Tarea>();
        }
        return this.tarea;
    }

}
