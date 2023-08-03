
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionAprobacion;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionAprobacionResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionAprobacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionAprobacion" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionAprobacion"/>
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
@XmlType(name = "ConfiguracionAprobacionResponse", propOrder = {
    "configuracionAprobacion",
    "resultado"
})
public class ConfiguracionAprobacionResponse {

    @XmlElement(required = true)
    protected ConfiguracionAprobacion configuracionAprobacion;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionAprobacion }
     *     
     */
    public ConfiguracionAprobacion getConfiguracionAprobacion() {
        return configuracionAprobacion;
    }

    /**
     * Define el valor de la propiedad configuracionAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionAprobacion }
     *     
     */
    public void setConfiguracionAprobacion(ConfiguracionAprobacion value) {
        this.configuracionAprobacion = value;
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
