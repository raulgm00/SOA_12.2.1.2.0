
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConfiguracionAdquisicionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionAdquisicionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="idAdquisicion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idNoObjecion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionAdquisicionRequestType", propOrder = {
    "idOperacion",
    "idAdquisicion",
    "idNoObjecion",
    "instanciaProceso"
})
public class ConfiguracionAdquisicionRequestType {

    protected long idOperacion;
    protected long idAdquisicion;
    protected long idNoObjecion;
    @XmlElement(required = true)
    protected String instanciaProceso;

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     */
    public long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     */
    public void setIdOperacion(long value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idAdquisicion.
     * 
     */
    public long getIdAdquisicion() {
        return idAdquisicion;
    }

    /**
     * Define el valor de la propiedad idAdquisicion.
     * 
     */
    public void setIdAdquisicion(long value) {
        this.idAdquisicion = value;
    }

    /**
     * Obtiene el valor de la propiedad idNoObjecion.
     * 
     */
    public long getIdNoObjecion() {
        return idNoObjecion;
    }

    /**
     * Define el valor de la propiedad idNoObjecion.
     * 
     */
    public void setIdNoObjecion(long value) {
        this.idNoObjecion = value;
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
