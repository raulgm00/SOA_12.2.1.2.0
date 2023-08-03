
package org.bcie.operacionbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para InsertClasificacionAmbiental complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InsertClasificacionAmbiental">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="componenteClasificacionAmbiental" type="{http://www.bcie.org/OperacionBO}ComponenteClasificacionAmbientalType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsertClasificacionAmbiental", propOrder = {
    "componenteClasificacionAmbiental"
})
public class InsertClasificacionAmbiental {

    protected List<ComponenteClasificacionAmbientalType> componenteClasificacionAmbiental;

    /**
     * Gets the value of the componenteClasificacionAmbiental property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componenteClasificacionAmbiental property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponenteClasificacionAmbiental().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComponenteClasificacionAmbientalType }
     * 
     * 
     */
    public List<ComponenteClasificacionAmbientalType> getComponenteClasificacionAmbiental() {
        if (componenteClasificacionAmbiental == null) {
            componenteClasificacionAmbiental = new ArrayList<ComponenteClasificacionAmbientalType>();
        }
        return this.componenteClasificacionAmbiental;
    }

}
