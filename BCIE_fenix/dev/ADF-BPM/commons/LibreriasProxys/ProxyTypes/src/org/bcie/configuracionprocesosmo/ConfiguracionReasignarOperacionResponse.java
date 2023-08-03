
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionReasignarOperacion;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionReasignarOperacionResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionReasignarOperacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionReasignarOperacion" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionReasignarOperacion"/>
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
@XmlType(name = "ConfiguracionReasignarOperacionResponse", propOrder = {
    "configuracionReasignarOperacion",
    "resultado"
})
public class ConfiguracionReasignarOperacionResponse {

    @XmlElement(required = true)
    protected ConfiguracionReasignarOperacion configuracionReasignarOperacion;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionReasignarOperacion.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionReasignarOperacion }
     *     
     */
    public ConfiguracionReasignarOperacion getConfiguracionReasignarOperacion() {
        return configuracionReasignarOperacion;
    }

    /**
     * Define el valor de la propiedad configuracionReasignarOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionReasignarOperacion }
     *     
     */
    public void setConfiguracionReasignarOperacion(ConfiguracionReasignarOperacion value) {
        this.configuracionReasignarOperacion = value;
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
