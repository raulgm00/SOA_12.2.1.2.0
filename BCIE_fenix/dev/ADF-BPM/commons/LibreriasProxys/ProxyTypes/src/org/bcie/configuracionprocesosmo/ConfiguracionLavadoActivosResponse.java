
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionLavadoActivos;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionLavadoActivosResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionLavadoActivosResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionLavadoActivos" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionLavadoActivos"/>
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
@XmlType(name = "ConfiguracionLavadoActivosResponse", propOrder = {
    "configuracionLavadoActivos",
    "resultado"
})
public class ConfiguracionLavadoActivosResponse {

    @XmlElement(required = true)
    protected ConfiguracionLavadoActivos configuracionLavadoActivos;
    @XmlElement(name = "Resultado")
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionLavadoActivos.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionLavadoActivos }
     *     
     */
    public ConfiguracionLavadoActivos getConfiguracionLavadoActivos() {
        return configuracionLavadoActivos;
    }

    /**
     * Define el valor de la propiedad configuracionLavadoActivos.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionLavadoActivos }
     *     
     */
    public void setConfiguracionLavadoActivos(ConfiguracionLavadoActivos value) {
        this.configuracionLavadoActivos = value;
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
