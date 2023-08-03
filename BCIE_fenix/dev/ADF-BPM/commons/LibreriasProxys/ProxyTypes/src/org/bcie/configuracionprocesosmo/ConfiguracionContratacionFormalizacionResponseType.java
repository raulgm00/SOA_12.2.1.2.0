
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionContratoFormalizacionType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionContratacionFormalizacionResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionContratacionFormalizacionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionContratoFormalizacion" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionContratoFormalizacionType"/>
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
@XmlType(name = "ConfiguracionContratacionFormalizacionResponseType", propOrder = {
    "configuracionContratoFormalizacion",
    "resultado"
})
public class ConfiguracionContratacionFormalizacionResponseType {

    @XmlElement(required = true)
    protected ConfiguracionContratoFormalizacionType configuracionContratoFormalizacion;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionContratoFormalizacion.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionContratoFormalizacionType }
     *     
     */
    public ConfiguracionContratoFormalizacionType getConfiguracionContratoFormalizacion() {
        return configuracionContratoFormalizacion;
    }

    /**
     * Define el valor de la propiedad configuracionContratoFormalizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionContratoFormalizacionType }
     *     
     */
    public void setConfiguracionContratoFormalizacion(ConfiguracionContratoFormalizacionType value) {
        this.configuracionContratoFormalizacion = value;
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
