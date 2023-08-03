
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ComisionesDesembolsoRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ComisionesDesembolsoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idSolicitudDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComisionesDesembolsoRequestType", propOrder = {
    "idSolicitudDesembolso"
})
public class ComisionesDesembolsoRequestType {

    protected long idSolicitudDesembolso;

    /**
     * Obtiene el valor de la propiedad idSolicitudDesembolso.
     * 
     */
    public long getIdSolicitudDesembolso() {
        return idSolicitudDesembolso;
    }

    /**
     * Define el valor de la propiedad idSolicitudDesembolso.
     * 
     */
    public void setIdSolicitudDesembolso(long value) {
        this.idSolicitudDesembolso = value;
    }

}
