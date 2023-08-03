
package org.bcie.aprobacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.aprobacionbo.Aprobacion;


/**
 * <p>Clase Java para CrearAprobacionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearAprobacionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Aprobacion" type="{http://www.bcie.org/AprobacionBO}Aprobacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearAprobacionRequestType", propOrder = {
    "aprobacion"
})
public class CrearAprobacionRequestType {

    @XmlElement(name = "Aprobacion", required = true)
    protected Aprobacion aprobacion;

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

}
