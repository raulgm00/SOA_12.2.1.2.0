
package org.bcie.clientebo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DetalleSCRType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DetalleSCRType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="scrFuente" type="{http://www.bcie.org/ClienteBO}SCRType"/>
 *         &lt;element name="scrReferencia" type="{http://www.bcie.org/ClienteBO}SCRType"/>
 *         &lt;element name="estatusComparacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleSCRType", propOrder = {
    "scrFuente",
    "scrReferencia",
    "estatusComparacion"
})
public class DetalleSCRType {

    @XmlElement(required = true)
    protected SCRType scrFuente;
    @XmlElement(required = true)
    protected SCRType scrReferencia;
    @XmlElement(required = true)
    protected String estatusComparacion;

    /**
     * Obtiene el valor de la propiedad scrFuente.
     * 
     * @return
     *     possible object is
     *     {@link SCRType }
     *     
     */
    public SCRType getScrFuente() {
        return scrFuente;
    }

    /**
     * Define el valor de la propiedad scrFuente.
     * 
     * @param value
     *     allowed object is
     *     {@link SCRType }
     *     
     */
    public void setScrFuente(SCRType value) {
        this.scrFuente = value;
    }

    /**
     * Obtiene el valor de la propiedad scrReferencia.
     * 
     * @return
     *     possible object is
     *     {@link SCRType }
     *     
     */
    public SCRType getScrReferencia() {
        return scrReferencia;
    }

    /**
     * Define el valor de la propiedad scrReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link SCRType }
     *     
     */
    public void setScrReferencia(SCRType value) {
        this.scrReferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad estatusComparacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstatusComparacion() {
        return estatusComparacion;
    }

    /**
     * Define el valor de la propiedad estatusComparacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstatusComparacion(String value) {
        this.estatusComparacion = value;
    }

}
