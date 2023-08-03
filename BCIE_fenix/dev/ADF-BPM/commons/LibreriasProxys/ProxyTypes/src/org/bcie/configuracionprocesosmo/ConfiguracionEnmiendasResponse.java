
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionEmniendas;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionEnmiendasResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionEnmiendasResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionEnmiendas" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionEmniendas"/>
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
@XmlType(name = "ConfiguracionEnmiendasResponse", propOrder = {
    "configuracionEnmiendas",
    "resultado"
})
public class ConfiguracionEnmiendasResponse {

    @XmlElement(required = true)
    protected ConfiguracionEmniendas configuracionEnmiendas;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionEnmiendas.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionEmniendas }
     *     
     */
    public ConfiguracionEmniendas getConfiguracionEnmiendas() {
        return configuracionEnmiendas;
    }

    /**
     * Define el valor de la propiedad configuracionEnmiendas.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionEmniendas }
     *     
     */
    public void setConfiguracionEnmiendas(ConfiguracionEmniendas value) {
        this.configuracionEnmiendas = value;
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
