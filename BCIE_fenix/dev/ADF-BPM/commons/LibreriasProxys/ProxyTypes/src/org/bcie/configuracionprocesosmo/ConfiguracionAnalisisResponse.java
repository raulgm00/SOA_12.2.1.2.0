
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionAnalisis;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionAnalisisResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionAnalisisResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionAnalisis" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionAnalisis"/>
 *         &lt;element name="Resultado" type="{http://www.bcie.org/ResultBO}Resultado" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionAnalisisResponse", propOrder = {
    "configuracionAnalisis",
    "resultado"
})
public class ConfiguracionAnalisisResponse {

    @XmlElement(required = true)
    protected ConfiguracionAnalisis configuracionAnalisis;
    @XmlElement(name = "Resultado")
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionAnalisis.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionAnalisis }
     *     
     */
    public ConfiguracionAnalisis getConfiguracionAnalisis() {
        return configuracionAnalisis;
    }

    /**
     * Define el valor de la propiedad configuracionAnalisis.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionAnalisis }
     *     
     */
    public void setConfiguracionAnalisis(ConfiguracionAnalisis value) {
        this.configuracionAnalisis = value;
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
