
package org.bcie.desembolsobo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.bcie.commonbo.RangoType;


/**
 * <p>Clase Java para variableType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="variableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tasaReferencia" type="{http://www.bcie.org/DesembolsoBO}referenciaType"/>
 *         &lt;element name="spread" type="{http://www.bcie.org/DesembolsoBO}spreadType"/>
 *         &lt;element name="revision" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.bcie.org/DesembolsoBO}FrecuenciaType">
 *                 &lt;sequence>
 *                   &lt;element name="NumeroCuotas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="limite" type="{http://www.bcie.org/CommonBO}RangoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "variableType", propOrder = {
    "tasaReferencia",
    "spread",
    "revision",
    "limite"
})
public class VariableType {

    @XmlElement(required = true)
    protected ReferenciaType tasaReferencia;
    @XmlElement(required = true)
    protected SpreadType spread;
    protected VariableType.Revision revision;
    protected RangoType limite;

    /**
     * Obtiene el valor de la propiedad tasaReferencia.
     * 
     * @return
     *     possible object is
     *     {@link ReferenciaType }
     *     
     */
    public ReferenciaType getTasaReferencia() {
        return tasaReferencia;
    }

    /**
     * Define el valor de la propiedad tasaReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenciaType }
     *     
     */
    public void setTasaReferencia(ReferenciaType value) {
        this.tasaReferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad spread.
     * 
     * @return
     *     possible object is
     *     {@link SpreadType }
     *     
     */
    public SpreadType getSpread() {
        return spread;
    }

    /**
     * Define el valor de la propiedad spread.
     * 
     * @param value
     *     allowed object is
     *     {@link SpreadType }
     *     
     */
    public void setSpread(SpreadType value) {
        this.spread = value;
    }

    /**
     * Obtiene el valor de la propiedad revision.
     * 
     * @return
     *     possible object is
     *     {@link VariableType.Revision }
     *     
     */
    public VariableType.Revision getRevision() {
        return revision;
    }

    /**
     * Define el valor de la propiedad revision.
     * 
     * @param value
     *     allowed object is
     *     {@link VariableType.Revision }
     *     
     */
    public void setRevision(VariableType.Revision value) {
        this.revision = value;
    }

    /**
     * Obtiene el valor de la propiedad limite.
     * 
     * @return
     *     possible object is
     *     {@link RangoType }
     *     
     */
    public RangoType getLimite() {
        return limite;
    }

    /**
     * Define el valor de la propiedad limite.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoType }
     *     
     */
    public void setLimite(RangoType value) {
        this.limite = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.bcie.org/DesembolsoBO}FrecuenciaType">
     *       &lt;sequence>
     *         &lt;element name="NumeroCuotas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "numeroCuotas"
    })
    public static class Revision
        extends FrecuenciaType
    {

        @XmlElementRef(name = "NumeroCuotas", namespace = "http://www.bcie.org/DesembolsoBO", type = JAXBElement.class, required = false)
        protected JAXBElement<Integer> numeroCuotas;

        /**
         * Obtiene el valor de la propiedad numeroCuotas.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
         *     
         */
        public JAXBElement<Integer> getNumeroCuotas() {
            return numeroCuotas;
        }

        /**
         * Define el valor de la propiedad numeroCuotas.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
         *     
         */
        public void setNumeroCuotas(JAXBElement<Integer> value) {
            this.numeroCuotas = value;
        }

    }

}
