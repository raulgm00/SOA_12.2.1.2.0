
package org.bcie.desembolsomo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para GeneraReportePlanAmortizacionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="GeneraReportePlanAmortizacionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion" minOccurs="0"/>
 *         &lt;element name="idFacturador" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CargarDocumento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneraReportePlanAmortizacionRequestType", propOrder = {
    "idDesembolso",
    "idOperacion",
    "idFacturador",
    "cargarDocumento"
})
public class GeneraReportePlanAmortizacionRequestType {

    protected long idDesembolso;
    @XmlElementRef(name = "idOperacion", namespace = "http://www.bcie.org/DesembolsoMO", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> idOperacion;
    protected String idFacturador;
    @XmlElementRef(name = "CargarDocumento", namespace = "http://www.bcie.org/DesembolsoMO", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> cargarDocumento;

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
     * Obtiene el valor de la propiedad idOperacion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdOperacion(JAXBElement<Long> value) {
        this.idOperacion = value;
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
     * Obtiene el valor de la propiedad cargarDocumento.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getCargarDocumento() {
        return cargarDocumento;
    }

    /**
     * Define el valor de la propiedad cargarDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setCargarDocumento(JAXBElement<Boolean> value) {
        this.cargarDocumento = value;
    }

}
