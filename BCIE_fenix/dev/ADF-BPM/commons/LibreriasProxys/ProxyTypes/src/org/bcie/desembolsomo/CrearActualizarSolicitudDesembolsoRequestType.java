
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.SolicitudDesembolso;


/**
 * <p>Clase Java para CrearActualizarSolicitudDesembolsoRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarSolicitudDesembolsoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="SolicitudDesembolso" type="{http://www.bcie.org/DesembolsoBO}SolicitudDesembolso"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearActualizarSolicitudDesembolsoRequestType", propOrder = {
    "idOperacion",
    "solicitudDesembolso"
})
public class CrearActualizarSolicitudDesembolsoRequestType {

    @XmlElement(name = "IdOperacion")
    protected long idOperacion;
    @XmlElement(name = "SolicitudDesembolso", required = true)
    protected SolicitudDesembolso solicitudDesembolso;

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
     * Obtiene el valor de la propiedad solicitudDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudDesembolso }
     *     
     */
    public SolicitudDesembolso getSolicitudDesembolso() {
        return solicitudDesembolso;
    }

    /**
     * Define el valor de la propiedad solicitudDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudDesembolso }
     *     
     */
    public void setSolicitudDesembolso(SolicitudDesembolso value) {
        this.solicitudDesembolso = value;
    }

}
