
package org.bcie.aprobacionmo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.bcie.aprobacionbo.Aprobacion;


/**
 * <p>Clase Java para ActualizarAprobacionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ActualizarAprobacionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Aprobacion" type="{http://www.bcie.org/AprobacionBO}Aprobacion" minOccurs="0"/>
 *         &lt;element name="actualizarTermino" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActualizarAprobacionRequestType", propOrder = {
    "aprobacion",
    "actualizarTermino"
})
public class ActualizarAprobacionRequestType {

    @XmlElement(name = "Aprobacion")
    protected Aprobacion aprobacion;
    @XmlElementRef(name = "actualizarTermino", namespace = "http://www.bcie.org/AprobacionMO", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> actualizarTermino;

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
     * Obtiene el valor de la propiedad actualizarTermino.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getActualizarTermino() {
        return actualizarTermino;
    }

    /**
     * Define el valor de la propiedad actualizarTermino.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setActualizarTermino(JAXBElement<Boolean> value) {
        this.actualizarTermino = value;
    }

}
