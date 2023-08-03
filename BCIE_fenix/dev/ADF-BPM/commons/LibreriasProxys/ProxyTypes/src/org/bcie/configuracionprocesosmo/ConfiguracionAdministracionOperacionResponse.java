
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionAdminstracionOperacion;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionAdministracionOperacionResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionAdministracionOperacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionAdministracionOperacion" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionAdminstracionOperacion"/>
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
@XmlType(name = "ConfiguracionAdministracionOperacionResponse", propOrder = {
    "configuracionAdministracionOperacion",
    "resultado"
})
public class ConfiguracionAdministracionOperacionResponse {

    @XmlElement(required = true)
    protected ConfiguracionAdminstracionOperacion configuracionAdministracionOperacion;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionAdministracionOperacion.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionAdminstracionOperacion }
     *     
     */
    public ConfiguracionAdminstracionOperacion getConfiguracionAdministracionOperacion() {
        return configuracionAdministracionOperacion;
    }

    /**
     * Define el valor de la propiedad configuracionAdministracionOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionAdminstracionOperacion }
     *     
     */
    public void setConfiguracionAdministracionOperacion(ConfiguracionAdminstracionOperacion value) {
        this.configuracionAdministracionOperacion = value;
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
