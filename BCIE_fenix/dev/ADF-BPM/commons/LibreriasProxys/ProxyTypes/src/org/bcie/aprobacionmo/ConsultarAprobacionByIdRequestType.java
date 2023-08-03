
package org.bcie.aprobacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarAprobacionByIdRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarAprobacionByIdRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAprobacion" type="{http://www.bcie.org/AprobacionBO}idAprobacion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarAprobacionByIdRequestType", propOrder = {
    "idAprobacion"
})
public class ConsultarAprobacionByIdRequestType {

    protected Long idAprobacion;

    /**
     * Obtiene el valor de la propiedad idAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAprobacion() {
        return idAprobacion;
    }

    /**
     * Define el valor de la propiedad idAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAprobacion(Long value) {
        this.idAprobacion = value;
    }

}
