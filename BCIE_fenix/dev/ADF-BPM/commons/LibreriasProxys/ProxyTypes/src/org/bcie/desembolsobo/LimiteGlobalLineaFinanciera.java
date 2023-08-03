
package org.bcie.desembolsobo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para LimiteGlobalLineaFinanciera complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LimiteGlobalLineaFinanciera">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/DesembolsoBO}LimiteLineaFinancieraType">
 *       &lt;sequence>
 *         &lt;element name="LineasGrupo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MonedasGrupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConcentradoPais" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimiteGlobalLineaFinanciera", propOrder = {
    "lineasGrupo",
    "monedasGrupo",
    "concentradoPais"
})
public class LimiteGlobalLineaFinanciera
    extends LimiteLineaFinancieraType
{

    @XmlElement(name = "LineasGrupo", required = true)
    protected String lineasGrupo;
    @XmlElement(name = "MonedasGrupo")
    protected String monedasGrupo;
    @XmlElementRef(name = "ConcentradoPais", namespace = "http://www.bcie.org/DesembolsoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> concentradoPais;

    /**
     * Obtiene el valor de la propiedad lineasGrupo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineasGrupo() {
        return lineasGrupo;
    }

    /**
     * Define el valor de la propiedad lineasGrupo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineasGrupo(String value) {
        this.lineasGrupo = value;
    }

    /**
     * Obtiene el valor de la propiedad monedasGrupo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedasGrupo() {
        return monedasGrupo;
    }

    /**
     * Define el valor de la propiedad monedasGrupo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedasGrupo(String value) {
        this.monedasGrupo = value;
    }

    /**
     * Obtiene el valor de la propiedad concentradoPais.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getConcentradoPais() {
        return concentradoPais;
    }

    /**
     * Define el valor de la propiedad concentradoPais.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setConcentradoPais(JAXBElement<Long> value) {
        this.concentradoPais = value;
    }

}
