
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.lineacreditobo.LineaCredito;


/**
 * <p>Clase Java para LimitePorcentajeLineaRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LimitePorcentajeLineaRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LineaCredito" type="{http://www.bcie.org/LineaCreditoBO}LineaCredito"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimitePorcentajeLineaRequestType", propOrder = {
    "lineaCredito"
})
public class LimitePorcentajeLineaRequestType {

    @XmlElement(name = "LineaCredito", required = true)
    protected LineaCredito lineaCredito;

    /**
     * Obtiene el valor de la propiedad lineaCredito.
     * 
     * @return
     *     possible object is
     *     {@link LineaCredito }
     *     
     */
    public LineaCredito getLineaCredito() {
        return lineaCredito;
    }

    /**
     * Define el valor de la propiedad lineaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link LineaCredito }
     *     
     */
    public void setLineaCredito(LineaCredito value) {
        this.lineaCredito = value;
    }

}
