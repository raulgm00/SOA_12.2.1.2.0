
package org.bcie.adquisicionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarAdquisicionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarAdquisicionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAdquisicion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idNoObjecion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idOperacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarAdquisicionRequestType", propOrder = {
    "idAdquisicion",
    "idNoObjecion",
    "idOperacion"
})
public class ConsultarAdquisicionRequestType {

    protected long idAdquisicion;
    protected Long idNoObjecion;
    protected Long idOperacion;

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
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdNoObjecion() {
        return idNoObjecion;
    }

    /**
     * Define el valor de la propiedad idNoObjecion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdNoObjecion(Long value) {
        this.idNoObjecion = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdOperacion(Long value) {
        this.idOperacion = value;
    }

}
