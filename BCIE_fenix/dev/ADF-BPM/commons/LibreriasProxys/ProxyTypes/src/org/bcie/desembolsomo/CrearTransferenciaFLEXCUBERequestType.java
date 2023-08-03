
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.commonbo.ParameterType;
import org.bcie.desembolsobo.ContratoDesembolso;


/**
 * <p>Clase Java para CrearTransferenciaFLEXCUBERequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearTransferenciaFLEXCUBERequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso"/>
 *         &lt;element name="Parametros" type="{http://www.bcie.org/CommonBO}parameterType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearTransferenciaFLEXCUBERequestType", propOrder = {
    "contratoDesembolso",
    "parametros"
})
public class CrearTransferenciaFLEXCUBERequestType {

    @XmlElement(name = "ContratoDesembolso", required = true)
    protected ContratoDesembolso contratoDesembolso;
    @XmlElement(name = "Parametros", required = true)
    protected ParameterType parametros;

    /**
     * Obtiene el valor de la propiedad contratoDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link ContratoDesembolso }
     *     
     */
    public ContratoDesembolso getContratoDesembolso() {
        return contratoDesembolso;
    }

    /**
     * Define el valor de la propiedad contratoDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link ContratoDesembolso }
     *     
     */
    public void setContratoDesembolso(ContratoDesembolso value) {
        this.contratoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad parametros.
     * 
     * @return
     *     possible object is
     *     {@link ParameterType }
     *     
     */
    public ParameterType getParametros() {
        return parametros;
    }

    /**
     * Define el valor de la propiedad parametros.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterType }
     *     
     */
    public void setParametros(ParameterType value) {
        this.parametros = value;
    }

}
