
package org.bcie.matriztccbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ProcesarEnmiendaResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ProcesarEnmiendaResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PESTADOSP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PMENSAJESP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcesarEnmiendaResponseType", propOrder = {
    "pestadosp",
    "pmensajesp"
})
public class ProcesarEnmiendaResponseType {

    @XmlElement(name = "PESTADOSP", required = true)
    protected String pestadosp;
    @XmlElement(name = "PMENSAJESP", required = true)
    protected String pmensajesp;

    /**
     * Obtiene el valor de la propiedad pestadosp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPESTADOSP() {
        return pestadosp;
    }

    /**
     * Define el valor de la propiedad pestadosp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPESTADOSP(String value) {
        this.pestadosp = value;
    }

    /**
     * Obtiene el valor de la propiedad pmensajesp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPMENSAJESP() {
        return pmensajesp;
    }

    /**
     * Define el valor de la propiedad pmensajesp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPMENSAJESP(String value) {
        this.pmensajesp = value;
    }

}
