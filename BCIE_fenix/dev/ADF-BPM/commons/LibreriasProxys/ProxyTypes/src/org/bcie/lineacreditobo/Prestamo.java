
package org.bcie.lineacreditobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.comisionbo.Comision;
import org.bcie.operacionbo.Operacion;


/**
 * <p>Clase Java para Prestamo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Prestamo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="Comision" type="{http://www.bcie.org/ComisionBO}Comision"/>
 *         &lt;/choice>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Prestamo", propOrder = {
    "comision",
    "operacion"
})
public class Prestamo {

    @XmlElement(name = "Comision")
    protected Comision comision;
    @XmlElement(name = "Operacion", required = true)
    protected Operacion operacion;

    /**
     * Obtiene el valor de la propiedad comision.
     * 
     * @return
     *     possible object is
     *     {@link Comision }
     *     
     */
    public Comision getComision() {
        return comision;
    }

    /**
     * Define el valor de la propiedad comision.
     * 
     * @param value
     *     allowed object is
     *     {@link Comision }
     *     
     */
    public void setComision(Comision value) {
        this.comision = value;
    }

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

}
