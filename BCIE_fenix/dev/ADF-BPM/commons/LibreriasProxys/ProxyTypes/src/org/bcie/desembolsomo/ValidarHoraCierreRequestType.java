
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.reglabo.ReglaBasicType;


/**
 * <p>Clase Java para ValidarHoraCierreRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidarHoraCierreRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Regla" type="{http://www.bcie.org/ReglaBO}ReglaBasicType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarHoraCierreRequestType", propOrder = {
    "regla"
})
public class ValidarHoraCierreRequestType {

    @XmlElement(name = "Regla", required = true)
    protected ReglaBasicType regla;

    /**
     * Obtiene el valor de la propiedad regla.
     * 
     * @return
     *     possible object is
     *     {@link ReglaBasicType }
     *     
     */
    public ReglaBasicType getRegla() {
        return regla;
    }

    /**
     * Define el valor de la propiedad regla.
     * 
     * @param value
     *     allowed object is
     *     {@link ReglaBasicType }
     *     
     */
    public void setRegla(ReglaBasicType value) {
        this.regla = value;
    }

}
