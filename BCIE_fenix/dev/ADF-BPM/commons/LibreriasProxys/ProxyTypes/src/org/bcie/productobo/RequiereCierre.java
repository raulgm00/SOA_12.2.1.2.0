
package org.bcie.productobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para RequiereCierre complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RequiereCierre">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Requiere_Cierre" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Tipo_Cierre" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequiereCierre", propOrder = {
    "requiereCierre",
    "tipoCierre"
})
public class RequiereCierre {

    @XmlElement(name = "Requiere_Cierre")
    protected boolean requiereCierre;
    @XmlElement(name = "Tipo_Cierre")
    protected Integer tipoCierre;

    /**
     * Obtiene el valor de la propiedad requiereCierre.
     * 
     */
    public boolean isRequiereCierre() {
        return requiereCierre;
    }

    /**
     * Define el valor de la propiedad requiereCierre.
     * 
     */
    public void setRequiereCierre(boolean value) {
        this.requiereCierre = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCierre.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoCierre() {
        return tipoCierre;
    }

    /**
     * Define el valor de la propiedad tipoCierre.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoCierre(Integer value) {
        this.tipoCierre = value;
    }

}
