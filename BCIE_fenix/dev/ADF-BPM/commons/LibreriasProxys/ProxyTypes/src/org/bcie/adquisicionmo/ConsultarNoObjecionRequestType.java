
package org.bcie.adquisicionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarNoObjecionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarNoObjecionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idNoObjecion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idAquisicion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarNoObjecionRequestType", propOrder = {
    "idNoObjecion",
    "idAquisicion"
})
public class ConsultarNoObjecionRequestType {

    protected Long idNoObjecion;
    protected Long idAquisicion;

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
     * Obtiene el valor de la propiedad idAquisicion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAquisicion() {
        return idAquisicion;
    }

    /**
     * Define el valor de la propiedad idAquisicion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAquisicion(Long value) {
        this.idAquisicion = value;
    }

}
