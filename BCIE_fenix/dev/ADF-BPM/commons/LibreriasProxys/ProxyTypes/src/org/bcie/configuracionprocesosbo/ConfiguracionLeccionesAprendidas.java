
package org.bcie.configuracionprocesosbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.leccionaprendidabo.LeccionAprendidaType;
import org.bcie.operacionbo.Operacion;


/**
 * Elemento para la configuración de LeccionesAprendidas
 * 
 * <p>Clase Java para ConfiguracionLeccionesAprendidas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionLeccionesAprendidas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *         &lt;element name="LeccionAprendida" type="{http://www.bcie.org/LeccionAprendidaBO}LeccionAprendidaType" maxOccurs="unbounded"/>
 *         &lt;element name="RolesEquipoTrabajo" type="{http://www.bcie.org/ConfiguracionProcesosBO}EquipoTrabajo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionLeccionesAprendidas", propOrder = {
    "operacion",
    "leccionAprendida",
    "rolesEquipoTrabajo"
})
public class ConfiguracionLeccionesAprendidas {

    @XmlElement(required = true)
    protected Operacion operacion;
    @XmlElement(name = "LeccionAprendida", required = true)
    protected List<LeccionAprendidaType> leccionAprendida;
    @XmlElement(name = "RolesEquipoTrabajo", required = true)
    protected EquipoTrabajo rolesEquipoTrabajo;

    /**
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link Operacion }
     *     
     */
    public Operacion getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Operacion }
     *     
     */
    public void setOperacion(Operacion value) {
        this.operacion = value;
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

    /**
     * Obtiene el valor de la propiedad rolesEquipoTrabajo.
     * 
     * @return
     *     possible object is
     *     {@link EquipoTrabajo }
     *     
     */
    public EquipoTrabajo getRolesEquipoTrabajo() {
        return rolesEquipoTrabajo;
    }

    /**
     * Define el valor de la propiedad rolesEquipoTrabajo.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipoTrabajo }
     *     
     */
    public void setRolesEquipoTrabajo(EquipoTrabajo value) {
        this.rolesEquipoTrabajo = value;
    }

}
