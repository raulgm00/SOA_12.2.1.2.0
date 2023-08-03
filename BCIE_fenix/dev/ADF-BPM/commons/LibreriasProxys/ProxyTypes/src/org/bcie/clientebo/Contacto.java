
package org.bcie.clientebo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Entidad Contacto - (Modelo canónico)
 * 
 * <p>Clase Java para Contacto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Contacto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idContacto" type="{http://www.bcie.org/ClienteBO}idContacto"/>
 *         &lt;element name="idCliente" type="{http://www.bcie.org/ClienteBO}idCliente"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="correoElectronico" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cargo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="idContactosClientes" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="recibeAvisoCobro" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contacto", propOrder = {
    "idContacto",
    "idCliente",
    "nombre",
    "telefono",
    "correoElectronico",
    "cargo",
    "tipo",
    "fechaRegistro",
    "idContactosClientes",
    "recibeAvisoCobro"
})
public class Contacto {

    protected long idContacto;
    protected long idCliente;
    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String telefono;
    @XmlElement(required = true)
    protected String correoElectronico;
    @XmlElement(required = true)
    protected String cargo;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;
    protected long idContactosClientes;
    @XmlElement(defaultValue = "false")
    protected Boolean recibeAvisoCobro;

    /**
     * Obtiene el valor de la propiedad idContacto.
     * 
     */
    public long getIdContacto() {
        return idContacto;
    }

    /**
     * Define el valor de la propiedad idContacto.
     * 
     */
    public void setIdContacto(long value) {
        this.idContacto = value;
    }

    /**
     * Obtiene el valor de la propiedad idCliente.
     * 
     */
    public long getIdCliente() {
        return idCliente;
    }

    /**
     * Define el valor de la propiedad idCliente.
     * 
     */
    public void setIdCliente(long value) {
        this.idCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad telefono.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Define el valor de la propiedad telefono.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono(String value) {
        this.telefono = value;
    }

    /**
     * Obtiene el valor de la propiedad correoElectronico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Define el valor de la propiedad correoElectronico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoElectronico(String value) {
        this.correoElectronico = value;
    }

    /**
     * Obtiene el valor de la propiedad cargo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Define el valor de la propiedad cargo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargo(String value) {
        this.cargo = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
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
     * Obtiene el valor de la propiedad idContactosClientes.
     * 
     */
    public long getIdContactosClientes() {
        return idContactosClientes;
    }

    /**
     * Define el valor de la propiedad idContactosClientes.
     * 
     */
    public void setIdContactosClientes(long value) {
        this.idContactosClientes = value;
    }

    /**
     * Obtiene el valor de la propiedad recibeAvisoCobro.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecibeAvisoCobro() {
        return recibeAvisoCobro;
    }

    /**
     * Define el valor de la propiedad recibeAvisoCobro.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecibeAvisoCobro(Boolean value) {
        this.recibeAvisoCobro = value;
    }

}
