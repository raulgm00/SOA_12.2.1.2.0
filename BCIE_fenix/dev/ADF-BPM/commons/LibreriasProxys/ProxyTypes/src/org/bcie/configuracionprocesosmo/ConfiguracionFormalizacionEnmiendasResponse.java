
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionFormalizacionEnmiendas;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionFormalizacionEnmiendasResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionFormalizacionEnmiendasResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionFormalizacionEnmiendas" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionFormalizacionEnmiendas"/>
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
@XmlType(name = "ConfiguracionFormalizacionEnmiendasResponse", propOrder = {
    "configuracionFormalizacionEnmiendas",
    "resultado"
})
public class ConfiguracionFormalizacionEnmiendasResponse {

    @XmlElement(required = true)
    protected ConfiguracionFormalizacionEnmiendas configuracionFormalizacionEnmiendas;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionFormalizacionEnmiendas.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionFormalizacionEnmiendas }
     *     
     */
    public ConfiguracionFormalizacionEnmiendas getConfiguracionFormalizacionEnmiendas() {
        return configuracionFormalizacionEnmiendas;
    }

    /**
     * Define el valor de la propiedad configuracionFormalizacionEnmiendas.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionFormalizacionEnmiendas }
     *     
     */
    public void setConfiguracionFormalizacionEnmiendas(ConfiguracionFormalizacionEnmiendas value) {
        this.configuracionFormalizacionEnmiendas = value;
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
