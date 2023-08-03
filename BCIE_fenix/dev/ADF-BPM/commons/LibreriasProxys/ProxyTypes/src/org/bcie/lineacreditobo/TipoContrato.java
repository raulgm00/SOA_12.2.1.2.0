
package org.bcie.lineacreditobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.comisionbo.Comision;


/**
 * <p>Clase Java para TipoContrato complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TipoContrato">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice minOccurs="0">
 *         &lt;element name="LineaCredito" type="{http://www.bcie.org/LineaCreditoBO}LineaCredito"/>
 *         &lt;element name="Comision" type="{http://www.bcie.org/ComisionBO}Comision"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoContrato", propOrder = {
    "lineaCredito",
    "comision"
})
public class TipoContrato {

    @XmlElement(name = "LineaCredito")
    protected LineaCredito lineaCredito;
    @XmlElement(name = "Comision")
    protected Comision comision;

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

}
