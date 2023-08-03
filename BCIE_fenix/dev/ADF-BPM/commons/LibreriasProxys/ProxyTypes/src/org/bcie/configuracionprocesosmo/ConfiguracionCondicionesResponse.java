
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionCondiciones;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionCondicionesResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionCondicionesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionCondiciones" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionCondiciones"/>
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
@XmlType(name = "ConfiguracionCondicionesResponse", propOrder = {
    "configuracionCondiciones",
    "resultado"
})
public class ConfiguracionCondicionesResponse {

    @XmlElement(required = true)
    protected ConfiguracionCondiciones configuracionCondiciones;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionCondiciones.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionCondiciones }
     *     
     */
    public ConfiguracionCondiciones getConfiguracionCondiciones() {
        return configuracionCondiciones;
    }

    /**
     * Define el valor de la propiedad configuracionCondiciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionCondiciones }
     *     
     */
    public void setConfiguracionCondiciones(ConfiguracionCondiciones value) {
        this.configuracionCondiciones = value;
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
