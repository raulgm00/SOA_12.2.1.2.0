
package org.bcie.aprobacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.aprobacionbo.Aprobacion;
import org.bcie.operacionbo.Operacion;


/**
 * <p>Clase Java para CrearAprobacionFLEXCUBERequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearAprobacionFLEXCUBERequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Aprobacion" type="{http://www.bcie.org/AprobacionBO}Aprobacion" minOccurs="0"/>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}Operacion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearAprobacionFLEXCUBERequestType", propOrder = {
    "aprobacion",
    "operacion"
})
public class CrearAprobacionFLEXCUBERequestType {

    @XmlElement(name = "Aprobacion")
    protected Aprobacion aprobacion;
    @XmlElement(name = "Operacion")
    protected Operacion operacion;

    /**
     * Obtiene el valor de la propiedad aprobacion.
     * 
     * @return
     *     possible object is
     *     {@link Aprobacion }
     *     
     */
    public Aprobacion getAprobacion() {
        return aprobacion;
    }

    /**
     * Define el valor de la propiedad aprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Aprobacion }
     *     
     */
    public void setAprobacion(Aprobacion value) {
        this.aprobacion = value;
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
