
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionCierreFideicomisoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionCierreFideicomisoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConfiguracionCierreFideicomiso" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionType"/>
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
@XmlType(name = "ConfiguracionCierreFideicomisoResponse", propOrder = {
    "configuracionCierreFideicomiso",
    "resultado"
})
public class ConfiguracionCierreFideicomisoResponse {

    @XmlElement(name = "ConfiguracionCierreFideicomiso", required = true)
    protected ConfiguracionType configuracionCierreFideicomiso;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionCierreFideicomiso.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionType }
     *     
     */
    public ConfiguracionType getConfiguracionCierreFideicomiso() {
        return configuracionCierreFideicomiso;
    }

    /**
     * Define el valor de la propiedad configuracionCierreFideicomiso.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionType }
     *     
     */
    public void setConfiguracionCierreFideicomiso(ConfiguracionType value) {
        this.configuracionCierreFideicomiso = value;
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
