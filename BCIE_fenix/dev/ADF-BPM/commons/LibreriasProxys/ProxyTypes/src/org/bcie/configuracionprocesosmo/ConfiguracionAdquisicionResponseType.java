
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionAdquisicionResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionAdquisicionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionAdquisicion" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionType"/>
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
@XmlType(name = "ConfiguracionAdquisicionResponseType", propOrder = {
    "configuracionAdquisicion",
    "resultado"
})
public class ConfiguracionAdquisicionResponseType {

    @XmlElement(required = true)
    protected ConfiguracionType configuracionAdquisicion;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionAdquisicion.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionType }
     *     
     */
    public ConfiguracionType getConfiguracionAdquisicion() {
        return configuracionAdquisicion;
    }

    /**
     * Define el valor de la propiedad configuracionAdquisicion.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionType }
     *     
     */
    public void setConfiguracionAdquisicion(ConfiguracionType value) {
        this.configuracionAdquisicion = value;
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
