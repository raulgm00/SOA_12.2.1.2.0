
package org.bcie.declaracionjuradabo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Justificacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Justificacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="justificacionDeclaracion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="justificacionBusqueda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Justificacion", propOrder = {
    "justificacionDeclaracion",
    "justificacionBusqueda"
})
public class Justificacion {

    @XmlElement(required = true)
    protected String justificacionDeclaracion;
    @XmlElement(required = true)
    protected String justificacionBusqueda;

    /**
     * Obtiene el valor de la propiedad justificacionDeclaracion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificacionDeclaracion() {
        return justificacionDeclaracion;
    }

    /**
     * Define el valor de la propiedad justificacionDeclaracion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificacionDeclaracion(String value) {
        this.justificacionDeclaracion = value;
    }

    /**
     * Obtiene el valor de la propiedad justificacionBusqueda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificacionBusqueda() {
        return justificacionBusqueda;
    }

    /**
     * Define el valor de la propiedad justificacionBusqueda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificacionBusqueda(String value) {
        this.justificacionBusqueda = value;
    }

}
