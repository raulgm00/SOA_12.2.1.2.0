
package org.bcie.comisionbo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para MontoBase complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="MontoBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idMontoBase" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="valorMontoBase" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="porcentajeMontoBase" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="descripcionMontoBase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MontoBase", propOrder = {
    "idMontoBase",
    "valorMontoBase",
    "porcentajeMontoBase",
    "descripcionMontoBase"
})
public class MontoBase {

    protected Integer idMontoBase;
    protected Integer valorMontoBase;
    protected BigDecimal porcentajeMontoBase;
    protected String descripcionMontoBase;

    /**
     * Obtiene el valor de la propiedad idMontoBase.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdMontoBase() {
        return idMontoBase;
    }

    /**
     * Define el valor de la propiedad idMontoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdMontoBase(Integer value) {
        this.idMontoBase = value;
    }

    /**
     * Obtiene el valor de la propiedad valorMontoBase.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getValorMontoBase() {
        return valorMontoBase;
    }

    /**
     * Define el valor de la propiedad valorMontoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setValorMontoBase(Integer value) {
        this.valorMontoBase = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajeMontoBase.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPorcentajeMontoBase() {
        return porcentajeMontoBase;
    }

    /**
     * Define el valor de la propiedad porcentajeMontoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPorcentajeMontoBase(BigDecimal value) {
        this.porcentajeMontoBase = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionMontoBase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionMontoBase() {
        return descripcionMontoBase;
    }

    /**
     * Define el valor de la propiedad descripcionMontoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionMontoBase(String value) {
        this.descripcionMontoBase = value;
    }

}
