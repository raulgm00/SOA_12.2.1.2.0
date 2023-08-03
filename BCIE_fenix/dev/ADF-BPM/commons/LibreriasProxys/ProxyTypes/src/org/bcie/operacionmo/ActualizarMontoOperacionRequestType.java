
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.MontoOperacion;


/**
 * <p>Clase Java para ActualizarMontoOperacionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ActualizarMontoOperacionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MontoOperacion" type="{http://www.bcie.org/OperacionBO}MontoOperacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActualizarMontoOperacionRequestType", propOrder = {
    "montoOperacion"
})
public class ActualizarMontoOperacionRequestType {

    @XmlElement(name = "MontoOperacion", required = true)
    protected MontoOperacion montoOperacion;

    /**
     * Obtiene el valor de la propiedad montoOperacion.
     * 
     * @return
     *     possible object is
     *     {@link MontoOperacion }
     *     
     */
    public MontoOperacion getMontoOperacion() {
        return montoOperacion;
    }

    /**
     * Define el valor de la propiedad montoOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoOperacion }
     *     
     */
    public void setMontoOperacion(MontoOperacion value) {
        this.montoOperacion = value;
    }

}
