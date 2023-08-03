
package org.bcie.productobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Tarea;


/**
 * Listado de tareas por producto
 * 
 * <p>Clase Java para TareasPorProducto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TareasPorProducto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listadoTareas" type="{http://www.bcie.org/CatalogoBO}Tarea" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TareasPorProducto", propOrder = {
    "listadoTareas"
})
public class TareasPorProducto {

    protected List<Tarea> listadoTareas;

    /**
     * Gets the value of the listadoTareas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listadoTareas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListadoTareas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tarea }
     * 
     * 
     */
    public List<Tarea> getListadoTareas() {
        if (listadoTareas == null) {
            listadoTareas = new ArrayList<Tarea>();
        }
        return this.listadoTareas;
    }

}
