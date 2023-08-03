
package org.bcie.eqipotrabajo.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ListadoEquipoTrbajo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ListadoEquipoTrbajo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="equipoTrabajo" type="{http://www.bcie.org/EqipoTrabajo/V1}RolEquipoTrabajo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListadoEquipoTrbajo", propOrder = {
    "equipoTrabajo"
})
public class ListadoEquipoTrbajo {

    @XmlElement(required = true)
    protected List<RolEquipoTrabajo> equipoTrabajo;

    /**
     * Gets the value of the equipoTrabajo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equipoTrabajo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEquipoTrabajo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RolEquipoTrabajo }
     * 
     * 
     */
    public List<RolEquipoTrabajo> getEquipoTrabajo() {
        if (equipoTrabajo == null) {
            equipoTrabajo = new ArrayList<RolEquipoTrabajo>();
        }
        return this.equipoTrabajo;
    }

}
