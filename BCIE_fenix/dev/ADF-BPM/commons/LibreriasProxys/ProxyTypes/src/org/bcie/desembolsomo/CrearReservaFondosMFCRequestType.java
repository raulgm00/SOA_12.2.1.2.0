
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.commonbo.MontoType;


/**
 * <p>Clase Java para CrearReservaFondosMFCRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearReservaFondosMFCRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Referencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaRequerido" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Monto" type="{http://www.bcie.org/CommonBO}MontoType"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Autorizar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CuentaBancaria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Disponible" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaAplicacion" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="FechaAprobacion" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="UsuarioAutoriza" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearReservaFondosMFCRequestType", propOrder = {
    "referencia",
    "fechaRequerido",
    "monto",
    "descripcion",
    "codigoUsuario",
    "autorizar",
    "cuentaBancaria",
    "disponible",
    "fechaAplicacion",
    "fechaAprobacion",
    "usuarioAutoriza"
})
public class CrearReservaFondosMFCRequestType {

    @XmlElement(name = "Referencia", required = true)
    protected String referencia;
    @XmlElement(name = "FechaRequerido", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRequerido;
    @XmlElement(name = "Monto", required = true)
    protected MontoType monto;
    @XmlElement(name = "Descripcion", required = true)
    protected String descripcion;
    @XmlElement(name = "CodigoUsuario", required = true)
    protected String codigoUsuario;
    @XmlElement(name = "Autorizar", required = true)
    protected String autorizar;
    @XmlElement(name = "CuentaBancaria", required = true)
    protected String cuentaBancaria;
    @XmlElement(name = "Disponible", required = true)
    protected String disponible;
    @XmlElement(name = "FechaAplicacion", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaAplicacion;
    @XmlElement(name = "FechaAprobacion", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaAprobacion;
    @XmlElement(name = "UsuarioAutoriza", required = true)
    protected String usuarioAutoriza;

    /**
     * Obtiene el valor de la propiedad referencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Define el valor de la propiedad referencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencia(String value) {
        this.referencia = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRequerido.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRequerido() {
        return fechaRequerido;
    }

    /**
     * Define el valor de la propiedad fechaRequerido.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRequerido(XMLGregorianCalendar value) {
        this.fechaRequerido = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     * @return
     *     possible object is
     *     {@link MontoType }
     *     
     */
    public MontoType getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoType }
     *     
     */
    public void setMonto(MontoType value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * Define el valor de la propiedad codigoUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoUsuario(String value) {
        this.codigoUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad autorizar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutorizar() {
        return autorizar;
    }

    /**
     * Define el valor de la propiedad autorizar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutorizar(String value) {
        this.autorizar = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaBancaria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    /**
     * Define el valor de la propiedad cuentaBancaria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaBancaria(String value) {
        this.cuentaBancaria = value;
    }

    /**
     * Obtiene el valor de la propiedad disponible.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisponible() {
        return disponible;
    }

    /**
     * Define el valor de la propiedad disponible.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisponible(String value) {
        this.disponible = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAplicacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAplicacion() {
        return fechaAplicacion;
    }

    /**
     * Define el valor de la propiedad fechaAplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAplicacion(XMLGregorianCalendar value) {
        this.fechaAplicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAprobacion() {
        return fechaAprobacion;
    }

    /**
     * Define el valor de la propiedad fechaAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAprobacion(XMLGregorianCalendar value) {
        this.fechaAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioAutoriza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioAutoriza() {
        return usuarioAutoriza;
    }

    /**
     * Define el valor de la propiedad usuarioAutoriza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioAutoriza(String value) {
        this.usuarioAutoriza = value;
    }

}
