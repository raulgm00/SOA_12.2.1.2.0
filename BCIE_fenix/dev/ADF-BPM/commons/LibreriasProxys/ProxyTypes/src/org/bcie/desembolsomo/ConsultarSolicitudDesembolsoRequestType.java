
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarSolicitudDesembolsoRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarSolicitudDesembolsoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idSolicitud" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="idContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/DesembolsoBO}idDesembolso" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarSolicitudDesembolsoRequestType", propOrder = {
    "idSolicitud",
    "idContratoDesembolso",
    "idOperacion"
})
public class ConsultarSolicitudDesembolsoRequestType {

    protected long idSolicitud;
    protected long idContratoDesembolso;
    protected Long idOperacion;

    /**
     * Obtiene el valor de la propiedad idSolicitud.
     * 
     */
    public long getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * Define el valor de la propiedad idSolicitud.
     * 
     */
    public void setIdSolicitud(long value) {
        this.idSolicitud = value;
    }

    /**
     * Obtiene el valor de la propiedad idContratoDesembolso.
     * 
     */
    public long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }

    /**
     * Define el valor de la propiedad idContratoDesembolso.
     * 
     */
    public void setIdContratoDesembolso(long value) {
        this.idContratoDesembolso = value;
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
