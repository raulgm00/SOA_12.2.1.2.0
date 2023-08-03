
package org.bcie.adquisicionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarParticipantesByIdNoObjecionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarParticipantesByIdNoObjecionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idNoObjecion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarParticipantesByIdNoObjecionRequestType", propOrder = {
    "idNoObjecion"
})
public class ConsultarParticipantesByIdNoObjecionRequestType {

    protected long idNoObjecion;

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

}
