
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para InteresType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InteresType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/DesembolsoBO}FrecuenciaType">
 *       &lt;sequence>
 *         &lt;element name="Tasa" type="{http://www.bcie.org/DesembolsoBO}tasaType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InteresType", propOrder = {
    "tasa"
})
public class InteresType
    extends FrecuenciaType
{

    @XmlElement(name = "Tasa", required = true)
    protected TasaType tasa;

    /**
     * Obtiene el valor de la propiedad tasa.
     * 
     * @return
     *     possible object is
     *     {@link TasaType }
     *     
     */
    public TasaType getTasa() {
        return tasa;
    }

    /**
     * Define el valor de la propiedad tasa.
     * 
     * @param value
     *     allowed object is
     *     {@link TasaType }
     *     
     */
    public void setTasa(TasaType value) {
        this.tasa = value;
    }

}
