
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionLeccionesAprendidas;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionLeccionesAprendidasResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionLeccionesAprendidasResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionLeccionesAprendidas" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionLeccionesAprendidas"/>
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
@XmlType(name = "ConfiguracionLeccionesAprendidasResponseType", propOrder = {
    "configuracionLeccionesAprendidas",
    "resultado"
})
public class ConfiguracionLeccionesAprendidasResponseType {

    @XmlElement(required = true)
    protected ConfiguracionLeccionesAprendidas configuracionLeccionesAprendidas;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionLeccionesAprendidas.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionLeccionesAprendidas }
     *     
     */
    public ConfiguracionLeccionesAprendidas getConfiguracionLeccionesAprendidas() {
        return configuracionLeccionesAprendidas;
    }

    /**
     * Define el valor de la propiedad configuracionLeccionesAprendidas.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionLeccionesAprendidas }
     *     
     */
    public void setConfiguracionLeccionesAprendidas(ConfiguracionLeccionesAprendidas value) {
        this.configuracionLeccionesAprendidas = value;
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
