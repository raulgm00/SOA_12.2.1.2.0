
package org.bcie.aprobacionbo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;
import org.bcie.lineacreditobo.LineaCredito;


/**
 * <p>Clase Java para Aprobacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Aprobacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAprobacion" type="{http://www.bcie.org/AprobacionBO}idAprobacion"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="idReunionAprobacion" type="{http://www.bcie.org/AprobacionBO}idReunion"/>
 *         &lt;element name="fechaAprobacion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="tipoAprobacion" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="numeroResolucion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resumen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idMontoAprobacion" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="idTipoMonedaMontoAprobacion" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="MontoAprobacion" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="loginUsuario" type="{http://www.bcie.org/AprobacionBO}UsuarioReunion" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="idContrato" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="LineaCredito" type="{http://www.bcie.org/LineaCreditoBO}LineaCredito" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Aprobacion", propOrder = {
    "idAprobacion",
    "idOperacion",
    "idReunionAprobacion",
    "fechaAprobacion",
    "tipoAprobacion",
    "numeroResolucion",
    "resumen",
    "idMontoAprobacion",
    "idTipoMonedaMontoAprobacion",
    "montoAprobacion",
    "loginUsuario",
    "fechaRegistro",
    "idContrato",
    "lineaCredito"
})
public class Aprobacion {

    protected long idAprobacion;
    protected long idOperacion;
    protected long idReunionAprobacion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaAprobacion;
    protected Catalogo tipoAprobacion;
    protected String numeroResolucion;
    protected String resumen;
    protected BigDecimal idMontoAprobacion;
    protected BigInteger idTipoMonedaMontoAprobacion;
    @XmlElement(name = "MontoAprobacion")
    protected BigDecimal montoAprobacion;
    protected UsuarioReunion loginUsuario;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected Long idContrato;
    @XmlElement(name = "LineaCredito", required = true)
    protected List<LineaCredito> lineaCredito;

    /**
     * Obtiene el valor de la propiedad idAprobacion.
     * 
     */
    public long getIdAprobacion() {
        return idAprobacion;
    }

    /**
     * Define el valor de la propiedad idAprobacion.
     * 
     */
    public void setIdAprobacion(long value) {
        this.idAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     */
    public long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     */
    public void setIdOperacion(long value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idReunionAprobacion.
     * 
     */
    public long getIdReunionAprobacion() {
        return idReunionAprobacion;
    }

    /**
     * Define el valor de la propiedad idReunionAprobacion.
     * 
     */
    public void setIdReunionAprobacion(long value) {
        this.idReunionAprobacion = value;
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
     * Obtiene el valor de la propiedad tipoAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoAprobacion() {
        return tipoAprobacion;
    }

    /**
     * Define el valor de la propiedad tipoAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoAprobacion(Catalogo value) {
        this.tipoAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroResolucion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroResolucion() {
        return numeroResolucion;
    }

    /**
     * Define el valor de la propiedad numeroResolucion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroResolucion(String value) {
        this.numeroResolucion = value;
    }

    /**
     * Obtiene el valor de la propiedad resumen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * Define el valor de la propiedad resumen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResumen(String value) {
        this.resumen = value;
    }

    /**
     * Obtiene el valor de la propiedad idMontoAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdMontoAprobacion() {
        return idMontoAprobacion;
    }

    /**
     * Define el valor de la propiedad idMontoAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdMontoAprobacion(BigDecimal value) {
        this.idMontoAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoMonedaMontoAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdTipoMonedaMontoAprobacion() {
        return idTipoMonedaMontoAprobacion;
    }

    /**
     * Define el valor de la propiedad idTipoMonedaMontoAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdTipoMonedaMontoAprobacion(BigInteger value) {
        this.idTipoMonedaMontoAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad montoAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoAprobacion() {
        return montoAprobacion;
    }

    /**
     * Define el valor de la propiedad montoAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoAprobacion(BigDecimal value) {
        this.montoAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad loginUsuario.
     * 
     * @return
     *     possible object is
     *     {@link UsuarioReunion }
     *     
     */
    public UsuarioReunion getLoginUsuario() {
        return loginUsuario;
    }

    /**
     * Define el valor de la propiedad loginUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuarioReunion }
     *     
     */
    public void setLoginUsuario(UsuarioReunion value) {
        this.loginUsuario = value;
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
     * Obtiene el valor de la propiedad idContrato.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdContrato() {
        return idContrato;
    }

    /**
     * Define el valor de la propiedad idContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdContrato(Long value) {
        this.idContrato = value;
    }

    /**
     * Gets the value of the lineaCredito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineaCredito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineaCredito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineaCredito }
     * 
     * 
     */
    public List<LineaCredito> getLineaCredito() {
        if (lineaCredito == null) {
            lineaCredito = new ArrayList<LineaCredito>();
        }
        return this.lineaCredito;
    }

}
