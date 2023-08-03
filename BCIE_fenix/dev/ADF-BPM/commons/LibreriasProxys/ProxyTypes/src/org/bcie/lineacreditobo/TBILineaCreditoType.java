
package org.bcie.lineacreditobo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para TBILineaCreditoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TBILineaCreditoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idContrato" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idLineaCredito" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idTareaBpm" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoLineaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="banEstatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroLineaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TBILineaCreditoType", propOrder = {
    "id",
    "idContrato",
    "idLineaCredito",
    "idTareaBpm",
    "instanciaProceso",
    "estadoLineaCredito",
    "usuario",
    "fechaRegistro",
    "banEstatus",
    "numeroLineaCredito"
})
public class TBILineaCreditoType {

    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long id;
    @XmlElementRef(name = "idContrato", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> idContrato;
    @XmlElementRef(name = "idLineaCredito", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> idLineaCredito;
    @XmlElementRef(name = "idTareaBpm", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> idTareaBpm;
    @XmlElementRef(name = "instanciaProceso", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<String> instanciaProceso;
    @XmlElementRef(name = "estadoLineaCredito", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<String> estadoLineaCredito;
    @XmlElementRef(name = "usuario", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<String> usuario;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;
    protected Integer banEstatus;
    @XmlElementRef(name = "numeroLineaCredito", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<String> numeroLineaCredito;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad idContrato.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdContrato() {
        return idContrato;
    }

    /**
     * Define el valor de la propiedad idContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdContrato(JAXBElement<Long> value) {
        this.idContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad idLineaCredito.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdLineaCredito() {
        return idLineaCredito;
    }

    /**
     * Define el valor de la propiedad idLineaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdLineaCredito(JAXBElement<Long> value) {
        this.idLineaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad idTareaBpm.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getIdTareaBpm() {
        return idTareaBpm;
    }

    /**
     * Define el valor de la propiedad idTareaBpm.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setIdTareaBpm(JAXBElement<Long> value) {
        this.idTareaBpm = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInstanciaProceso(JAXBElement<String> value) {
        this.instanciaProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoLineaCredito.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEstadoLineaCredito() {
        return estadoLineaCredito;
    }

    /**
     * Define el valor de la propiedad estadoLineaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEstadoLineaCredito(JAXBElement<String> value) {
        this.estadoLineaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsuario(JAXBElement<String> value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRegistro.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Define el valor de la propiedad fechaRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRegistro(XMLGregorianCalendar value) {
        this.fechaRegistro = value;
    }

    /**
     * Obtiene el valor de la propiedad banEstatus.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBanEstatus() {
        return banEstatus;
    }

    /**
     * Define el valor de la propiedad banEstatus.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBanEstatus(Integer value) {
        this.banEstatus = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroLineaCredito.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNumeroLineaCredito() {
        return numeroLineaCredito;
    }

    /**
     * Define el valor de la propiedad numeroLineaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNumeroLineaCredito(JAXBElement<String> value) {
        this.numeroLineaCredito = value;
    }

}
