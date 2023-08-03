
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.reglabo.ReglaBasicType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ValidarLimiteProgramaBPELResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidarLimiteProgramaBPELResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Regla" type="{http://www.bcie.org/ReglaBO}ReglaBasicType"/>
 *         &lt;element name="Resultado" type="{http://www.bcie.org/ResultBO}Resultado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarLimiteProgramaBPELResponseType", propOrder = {
    "regla",
    "resultado"
})
public class ValidarLimiteProgramaBPELResponseType {

    @XmlElement(name = "Regla", required = true)
    protected ReglaBasicType regla;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

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

    /**
     * Obtiene el valor de la propiedad resultado.
     * 
     * @return
     *     possible object is
     *     {@link Resultado }
     *     
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * Define el valor de la propiedad resultado.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultado }
     *     
     */
    public void setResultado(Resultado value) {
        this.resultado = value;
    }

}
