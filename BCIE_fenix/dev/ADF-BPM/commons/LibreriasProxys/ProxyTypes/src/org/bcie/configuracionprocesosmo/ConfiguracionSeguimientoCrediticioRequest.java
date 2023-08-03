
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para ConfiguracionSeguimientoCrediticioRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionSeguimientoCrediticioRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCliente" type="{http://www.bcie.org/ClienteBO}idCliente"/>
 *         &lt;element name="TIPO_INICIO" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="InstanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionSeguimientoCrediticioRequest", propOrder = {
    "idCliente",
    "tipoinicio",
    "instanciaProceso"
})
public class ConfiguracionSeguimientoCrediticioRequest {

    protected long idCliente;
    @XmlElement(name = "TIPO_INICIO", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String tipoinicio;
    @XmlElement(name = "InstanciaProceso", required = true)
    protected String instanciaProceso;

    /**
     * Obtiene el valor de la propiedad idCliente.
     * 
     */
    public long getIdCliente() {
        return idCliente;
    }

    /**
     * Define el valor de la propiedad idCliente.
     * 
     */
    public void setIdCliente(long value) {
        this.idCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoinicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOINICIO() {
        return tipoinicio;
    }

    /**
     * Define el valor de la propiedad tipoinicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOINICIO(String value) {
        this.tipoinicio = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanciaProceso(String value) {
        this.instanciaProceso = value;
    }

}
