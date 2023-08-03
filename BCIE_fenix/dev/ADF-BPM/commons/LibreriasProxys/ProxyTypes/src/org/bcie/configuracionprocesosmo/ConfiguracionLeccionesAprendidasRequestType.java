
package org.bcie.configuracionprocesosmo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.leccionaprendidabo.LeccionAprendidaType;


/**
 * <p>Clase Java para ConfiguracionLeccionesAprendidasRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionLeccionesAprendidasRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="LeccionAprendida" type="{http://www.bcie.org/LeccionAprendidaBO}LeccionAprendidaType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionLeccionesAprendidasRequestType", propOrder = {
    "idOperacion",
    "leccionAprendida"
})
public class ConfiguracionLeccionesAprendidasRequestType {

    protected long idOperacion;
    @XmlElement(name = "LeccionAprendida", required = true)
    protected List<LeccionAprendidaType> leccionAprendida;

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     */
    public long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     */
    public void setIdOperacion(long value) {
        this.idOperacion = value;
    }

    /**
     * Gets the value of the leccionAprendida property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the leccionAprendida property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLeccionAprendida().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LeccionAprendidaType }
     * 
     * 
     */
    public List<LeccionAprendidaType> getLeccionAprendida() {
        if (leccionAprendida == null) {
            leccionAprendida = new ArrayList<LeccionAprendidaType>();
        }
        return this.leccionAprendida;
    }

}
