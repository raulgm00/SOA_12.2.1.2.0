
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ExceptuarSolicitudRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ExceptuarSolicitudRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idSolicitud" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExceptuarSolicitudRequestType", propOrder = {
    "idSolicitud",
    "instanciaProceso"
})
public class ExceptuarSolicitudRequestType {

    protected long idSolicitud;
    protected long instanciaProceso;

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
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     */
    public long getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     */
    public void setInstanciaProceso(long value) {
        this.instanciaProceso = value;
    }

}
