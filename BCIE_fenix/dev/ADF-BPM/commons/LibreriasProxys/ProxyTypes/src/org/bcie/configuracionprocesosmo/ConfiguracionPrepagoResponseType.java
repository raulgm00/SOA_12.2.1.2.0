
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionPrepagoResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionPrepagoResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionPrepago" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionType"/>
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
@XmlType(name = "ConfiguracionPrepagoResponseType", propOrder = {
    "configuracionPrepago",
    "resultado"
})
public class ConfiguracionPrepagoResponseType {

    @XmlElement(required = true)
    protected ConfiguracionType configuracionPrepago;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionPrepago.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionType }
     *     
     */
    public ConfiguracionType getConfiguracionPrepago() {
        return configuracionPrepago;
    }

    /**
     * Define el valor de la propiedad configuracionPrepago.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionType }
     *     
     */
    public void setConfiguracionPrepago(ConfiguracionType value) {
        this.configuracionPrepago = value;
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
