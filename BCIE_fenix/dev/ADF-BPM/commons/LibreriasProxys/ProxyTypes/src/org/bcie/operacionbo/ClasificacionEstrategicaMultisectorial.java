
package org.bcie.operacionbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ClasificacionEstrategicaMultisectorial complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ClasificacionEstrategicaMultisectorial">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="componenteClasificacionEstrategica" type="{http://www.bcie.org/OperacionBO}ComponenteClasificacionEstrategicaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClasificacionEstrategicaMultisectorial", propOrder = {
    "componenteClasificacionEstrategica"
})
public class ClasificacionEstrategicaMultisectorial {

    protected List<ComponenteClasificacionEstrategicaType> componenteClasificacionEstrategica;

    /**
     * Gets the value of the componenteClasificacionEstrategica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componenteClasificacionEstrategica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponenteClasificacionEstrategica().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComponenteClasificacionEstrategicaType }
     * 
     * 
     */
    public List<ComponenteClasificacionEstrategicaType> getComponenteClasificacionEstrategica() {
        if (componenteClasificacionEstrategica == null) {
            componenteClasificacionEstrategica = new ArrayList<ComponenteClasificacionEstrategicaType>();
        }
        return this.componenteClasificacionEstrategica;
    }

}
