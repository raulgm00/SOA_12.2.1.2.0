
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.configuracionprocesosbo.ConfiguracionAdminstracionCliente;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConfiguracionAdministracionClienteResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionAdministracionClienteResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuracionAdministracionCliente" type="{http://www.bcie.org/ConfiguracionProcesosBO}ConfiguracionAdminstracionCliente"/>
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
@XmlType(name = "ConfiguracionAdministracionClienteResponse", propOrder = {
    "configuracionAdministracionCliente",
    "resultado"
})
public class ConfiguracionAdministracionClienteResponse {

    @XmlElement(required = true)
    protected ConfiguracionAdminstracionCliente configuracionAdministracionCliente;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad configuracionAdministracionCliente.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionAdminstracionCliente }
     *     
     */
    public ConfiguracionAdminstracionCliente getConfiguracionAdministracionCliente() {
        return configuracionAdministracionCliente;
    }

    /**
     * Define el valor de la propiedad configuracionAdministracionCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionAdminstracionCliente }
     *     
     */
    public void setConfiguracionAdministracionCliente(ConfiguracionAdminstracionCliente value) {
        this.configuracionAdministracionCliente = value;
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
