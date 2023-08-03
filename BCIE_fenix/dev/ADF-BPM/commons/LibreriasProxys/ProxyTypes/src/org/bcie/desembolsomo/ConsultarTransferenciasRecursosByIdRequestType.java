
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarTransferenciasRecursosByIdRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarTransferenciasRecursosByIdRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="idTransferencia" type="{http://www.bcie.org/DesembolsoBO}idDesembolso" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarTransferenciasRecursosByIdRequestType", propOrder = {
    "idDesembolso",
    "idTransferencia"
})
public class ConsultarTransferenciasRecursosByIdRequestType {

    protected long idDesembolso;
    protected Long idTransferencia;

    /**
     * Obtiene el valor de la propiedad idDesembolso.
     * 
     */
    public long getIdDesembolso() {
        return idDesembolso;
    }

    /**
     * Define el valor de la propiedad idDesembolso.
     * 
     */
    public void setIdDesembolso(long value) {
        this.idDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad idTransferencia.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTransferencia() {
        return idTransferencia;
    }

    /**
     * Define el valor de la propiedad idTransferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTransferencia(Long value) {
        this.idTransferencia = value;
    }

}
