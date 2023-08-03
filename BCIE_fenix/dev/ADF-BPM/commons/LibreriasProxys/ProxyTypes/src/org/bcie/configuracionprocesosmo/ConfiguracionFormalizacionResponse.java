
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionFormalizacion;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionFormalizacionResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionFormalizacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionFormalizacion" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionFormalizacion"/>
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
@XmlType(name = "ConfiguracionFormalizacionResponse", propOrder = {
    "configuracionFormalizacion",
    "resultado"
})
public class ConfiguracionFormalizacionResponse {

    @XmlElement(required = true)
    protected ConfiguracionFormalizacion configuracionFormalizacion;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionFormalizacion.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionFormalizacion }
     *     
     */
    public ConfiguracionFormalizacion getConfiguracionFormalizacion() {
        return configuracionFormalizacion;
    }

    /**
     * Define el valor de la propiedad configuracionFormalizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionFormalizacion }
     *     
     */
    public void setConfiguracionFormalizacion(ConfiguracionFormalizacion value) {
        this.configuracionFormalizacion = value;
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
