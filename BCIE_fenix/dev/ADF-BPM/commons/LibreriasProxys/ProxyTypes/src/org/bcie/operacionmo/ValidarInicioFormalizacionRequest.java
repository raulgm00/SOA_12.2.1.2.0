
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.OperacionBasicType;


/**
 * <p>Clase Java para ValidarInicioFormalizacionRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidarInicioFormalizacionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}OperacionBasicType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarInicioFormalizacionRequest", propOrder = {
    "operacion"
})
public class ValidarInicioFormalizacionRequest {

    @XmlElement(name = "Operacion", required = true)
    protected OperacionBasicType operacion;

    /**
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link OperacionBasicType }
     *     
     */
    public OperacionBasicType getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link OperacionBasicType }
     *     
     */
    public void setOperacion(OperacionBasicType value) {
        this.operacion = value;
    }

}
