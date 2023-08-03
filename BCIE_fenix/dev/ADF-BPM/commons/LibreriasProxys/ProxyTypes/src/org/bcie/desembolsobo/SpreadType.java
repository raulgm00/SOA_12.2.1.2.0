
package org.bcie.desembolsobo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para spreadType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="spreadType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/DesembolsoBO}fijaType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spreadType", propOrder = {
    "codigo",
    "revision"
})
public class SpreadType
    extends FijaType
{

    protected String codigo;
    protected SpreadType.Revision revision;

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad revision.
     * 
     * @return
     *     possible object is
     *     {@link SpreadType.Revision }
     *     
     */
    public SpreadType.Revision getRevision() {
        return revision;
    }

    /**
     * Define el valor de la propiedad revision.
     * 
     * @param value
     *     allowed object is
     *     {@link SpreadType.Revision }
     *     
     */
    public void setRevision(SpreadType.Revision value) {
        this.revision = value;
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
