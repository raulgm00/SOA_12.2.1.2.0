
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionComisiones;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionComisionesResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionComisionesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionComisiones" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionComisiones"/>
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
@XmlType(name = "ConfiguracionComisionesResponse", propOrder = {
    "configuracionComisiones",
    "resultado"
})
public class ConfiguracionComisionesResponse {

    @XmlElement(required = true)
    protected ConfiguracionComisiones configuracionComisiones;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionComisiones.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionComisiones }
     *     
     */
    public ConfiguracionComisiones getConfiguracionComisiones() {
        return configuracionComisiones;
    }

    /**
     * Define el valor de la propiedad configuracionComisiones.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionComisiones }
     *     
     */
    public void setConfiguracionComisiones(ConfiguracionComisiones value) {
        this.configuracionComisiones = value;
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
