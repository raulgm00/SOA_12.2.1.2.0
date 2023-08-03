
package org.bcie.clientebo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para SeguimientoCrediticioType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SeguimientoCrediticioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCliente" type="{http://www.bcie.org/ClienteBO}idCliente"/>
 *         &lt;element name="tipoInicio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoRevision" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scr" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="estadoSCR" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="perspectiva" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="esCalificacionDividida" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fechaAprobado" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loginUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="banEstatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeguimientoCrediticioType", propOrder = {
    "id",
    "idCliente",
    "tipoInicio",
    "tipoRevision",
    "scr",
    "estadoSCR",
    "perspectiva",
    "esCalificacionDividida",
    "fechaAprobado",
    "instanciaProceso",
    "loginUsuario",
    "nombreUsuario",
    "fechaRegistro",
    "banEstatus"
})
public class SeguimientoCrediticioType {

    protected long id;
    protected long idCliente;
    @XmlElement(required = true)
    protected String tipoInicio;
    @XmlElement(required = true)
    protected String tipoRevision;
    @XmlElement(required = true)
    protected Catalogo scr;
    @XmlElement(required = true)
    protected Catalogo estadoSCR;
    @XmlElement(required = true)
    protected Catalogo perspectiva;
    protected boolean esCalificacionDividida;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAprobado;
    @XmlElement(required = true)
    protected String instanciaProceso;
    @XmlElement(required = true)
    protected String loginUsuario;
    @XmlElement(required = true)
    protected String nombreUsuario;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;
    protected boolean banEstatus;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(long value) {
        this.id = value;
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
     * Obtiene el valor de la propiedad tipoInicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoInicio() {
        return tipoInicio;
    }

    /**
     * Define el valor de la propiedad tipoInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoInicio(String value) {
        this.tipoInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRevision.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRevision() {
        return tipoRevision;
    }

    /**
     * Define el valor de la propiedad tipoRevision.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRevision(String value) {
        this.tipoRevision = value;
    }

    /**
     * Obtiene el valor de la propiedad scr.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getScr() {
        return scr;
    }

    /**
     * Define el valor de la propiedad scr.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setScr(Catalogo value) {
        this.scr = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoSCR.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEstadoSCR() {
        return estadoSCR;
    }

    /**
     * Define el valor de la propiedad estadoSCR.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEstadoSCR(Catalogo value) {
        this.estadoSCR = value;
    }

    /**
     * Obtiene el valor de la propiedad perspectiva.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getPerspectiva() {
        return perspectiva;
    }

    /**
     * Define el valor de la propiedad perspectiva.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setPerspectiva(Catalogo value) {
        this.perspectiva = value;
    }

    /**
     * Obtiene el valor de la propiedad esCalificacionDividida.
     * 
     */
    public boolean isEsCalificacionDividida() {
        return esCalificacionDividida;
    }

    /**
     * Define el valor de la propiedad esCalificacionDividida.
     * 
     */
    public void setEsCalificacionDividida(boolean value) {
        this.esCalificacionDividida = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAprobado.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAprobado() {
        return fechaAprobado;
    }

    /**
     * Define el valor de la propiedad fechaAprobado.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAprobado(XMLGregorianCalendar value) {
        this.fechaAprobado = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanciaProceso(String value) {
        this.instanciaProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad loginUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginUsuario() {
        return loginUsuario;
    }

    /**
     * Define el valor de la propiedad loginUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginUsuario(String value) {
        this.loginUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Define el valor de la propiedad nombreUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreUsuario(String value) {
        this.nombreUsuario = value;
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
     */
    public boolean isBanEstatus() {
        return banEstatus;
    }

    /**
     * Define el valor de la propiedad banEstatus.
     * 
     */
    public void setBanEstatus(boolean value) {
        this.banEstatus = value;
    }

}
