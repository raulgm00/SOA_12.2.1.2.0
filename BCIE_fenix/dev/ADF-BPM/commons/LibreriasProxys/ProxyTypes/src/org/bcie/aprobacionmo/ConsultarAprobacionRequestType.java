
package org.bcie.aprobacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarAprobacionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarAprobacionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idSolicitudAprobacion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarAprobacionRequestType", propOrder = {
    "idSolicitudAprobacion",
    "idOperacion"
})
public class ConsultarAprobacionRequestType {

    protected long idSolicitudAprobacion;
    protected long idOperacion;

    /**
     * Obtiene el valor de la propiedad idSolicitudAprobacion.
     * 
     */
    public long getIdSolicitudAprobacion() {
        return idSolicitudAprobacion;
    }

    /**
     * Define el valor de la propiedad idSolicitudAprobacion.
     * 
     */
    public void setIdSolicitudAprobacion(long value) {
        this.idSolicitudAprobacion = value;
    }

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

}
