
package org.bcie.operacionbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ClasificacionesAmbiental complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ClasificacionesAmbiental">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clasificacionAmbiental" type="{http://www.bcie.org/OperacionBO}ClasificacionAmbiental" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClasificacionesAmbiental", propOrder = {
    "clasificacionAmbiental"
})
public class ClasificacionesAmbiental {

    protected List<ClasificacionAmbiental> clasificacionAmbiental;

    /**
     * Gets the value of the clasificacionAmbiental property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clasificacionAmbiental property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClasificacionAmbiental().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClasificacionAmbiental }
     * 
     * 
     */
    public List<ClasificacionAmbiental> getClasificacionAmbiental() {
        if (clasificacionAmbiental == null) {
            clasificacionAmbiental = new ArrayList<ClasificacionAmbiental>();
        }
        return this.clasificacionAmbiental;
    }

}
