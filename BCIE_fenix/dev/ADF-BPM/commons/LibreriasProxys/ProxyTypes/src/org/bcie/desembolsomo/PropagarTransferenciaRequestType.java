
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PropagarTransferenciaRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PropagarTransferenciaRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTransferencia" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropagarTransferenciaRequestType", propOrder = {
    "idTransferencia",
    "ejecutivo",
    "instanciaProceso"
})
public class PropagarTransferenciaRequestType {

    protected long idTransferencia;
    @XmlElement(required = true)
    protected String ejecutivo;
    protected Long instanciaProceso;

    /**
     * Obtiene el valor de la propiedad idTransferencia.
     * 
     */
    public long getIdTransferencia() {
        return idTransferencia;
    }

    /**
     * Define el valor de la propiedad idTransferencia.
     * 
     */
    public void setIdTransferencia(long value) {
        this.idTransferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad ejecutivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEjecutivo() {
        return ejecutivo;
    }

    /**
     * Define el valor de la propiedad ejecutivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEjecutivo(String value) {
        this.ejecutivo = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInstanciaProceso(Long value) {
        this.instanciaProceso = value;
    }

}
