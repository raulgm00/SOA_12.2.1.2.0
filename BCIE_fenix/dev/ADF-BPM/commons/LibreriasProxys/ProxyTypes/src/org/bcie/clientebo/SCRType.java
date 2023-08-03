
package org.bcie.clientebo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para SCRType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SCRType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.bcie.org/ClienteBO}ClienteIdentificador"/>
 *         &lt;element name="scr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoScr" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="usuarioModifico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuarioValido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCRType", propOrder = {
    "idCliente",
    "idFacturador",
    "scr",
    "estadoScr",
    "observacion",
    "fecha",
    "usuarioModifico",
    "usuarioValido"
})
public class SCRType {

    protected long idCliente;
    protected String idFacturador;
    protected String scr;
    protected BigDecimal estadoScr;
    protected String observacion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    protected String usuarioModifico;
    protected String usuarioValido;

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
     * Obtiene el valor de la propiedad scr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScr() {
        return scr;
    }

    /**
     * Define el valor de la propiedad scr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScr(String value) {
        this.scr = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoScr.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEstadoScr() {
        return estadoScr;
    }

    /**
     * Define el valor de la propiedad estadoScr.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEstadoScr(BigDecimal value) {
        this.estadoScr = value;
    }

    /**
     * Obtiene el valor de la propiedad observacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Define el valor de la propiedad observacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacion(String value) {
        this.observacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioModifico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioModifico() {
        return usuarioModifico;
    }

    /**
     * Define el valor de la propiedad usuarioModifico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioModifico(String value) {
        this.usuarioModifico = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioValido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioValido() {
        return usuarioValido;
    }

    /**
     * Define el valor de la propiedad usuarioValido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioValido(String value) {
        this.usuarioValido = value;
    }

}
