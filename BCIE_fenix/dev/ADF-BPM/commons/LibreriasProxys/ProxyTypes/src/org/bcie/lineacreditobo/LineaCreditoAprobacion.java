
package org.bcie.lineacreditobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para LineaCreditoAprobacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LineaCreditoAprobacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LineasCredito" type="{http://www.bcie.org/LineaCreditoBO}LineaCredito" minOccurs="0"/>
 *         &lt;element name="idAprobacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineaCreditoAprobacion", propOrder = {
    "lineasCredito",
    "idAprobacion"
})
public class LineaCreditoAprobacion {

    @XmlElement(name = "LineasCredito")
    protected LineaCredito lineasCredito;
    protected Long idAprobacion;

    /**
     * Obtiene el valor de la propiedad lineasCredito.
     * 
     * @return
     *     possible object is
     *     {@link LineaCredito }
     *     
     */
    public LineaCredito getLineasCredito() {
        return lineasCredito;
    }

    /**
     * Define el valor de la propiedad lineasCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link LineaCredito }
     *     
     */
    public void setLineasCredito(LineaCredito value) {
        this.lineasCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad idAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAprobacion() {
        return idAprobacion;
    }

    /**
     * Define el valor de la propiedad idAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAprobacion(Long value) {
        this.idAprobacion = value;
    }

}
