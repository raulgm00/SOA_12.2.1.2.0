
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para TransferenciaFT05Type complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TransferenciaFT05Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTransferenciaFT05" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}DesembolsoIdentificador"/>
 *         &lt;element name="FechaEfectiva" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransferenciaFT05Type", propOrder = {
    "idTransferenciaFT05",
    "idDesembolso",
    "idFacturador",
    "fechaEfectiva"
})
public class TransferenciaFT05Type {

    protected long idTransferenciaFT05;
    protected long idDesembolso;
    protected String idFacturador;
    @XmlElement(name = "FechaEfectiva")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEfectiva;

    /**
     * Obtiene el valor de la propiedad idTransferenciaFT05.
     * 
     */
    public long getIdTransferenciaFT05() {
        return idTransferenciaFT05;
    }

    /**
     * Define el valor de la propiedad idTransferenciaFT05.
     * 
     */
    public void setIdTransferenciaFT05(long value) {
        this.idTransferenciaFT05 = value;
    }

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
     * Obtiene el valor de la propiedad idFacturador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFacturador() {
        return idFacturador;
    }

    /**
     * Define el valor de la propiedad idFacturador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFacturador(String value) {
        this.idFacturador = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEfectiva.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEfectiva() {
        return fechaEfectiva;
    }

    /**
     * Define el valor de la propiedad fechaEfectiva.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEfectiva(XMLGregorianCalendar value) {
        this.fechaEfectiva = value;
    }

}
