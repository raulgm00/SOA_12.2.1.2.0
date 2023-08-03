
package org.bcie.declaracionjuradabo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DeclaracionJurada complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DeclaracionJurada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDeclaracion" type="{http://www.bcie.org/DeclaracionJuradaBO}idDeclaracion" minOccurs="0"/>
 *         &lt;element name="codigoExternoDeclaracion" type="{http://www.bcie.org/DeclaracionJuradaBO}codigoExternoDeclaracion" minOccurs="0"/>
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoDeclaracion" type="{http://www.bcie.org/DeclaracionJuradaBO}EstadoDeclaracion" minOccurs="0"/>
 *         &lt;element name="estadoBusqueda" type="{http://www.bcie.org/DeclaracionJuradaBO}EstadoBusqueda" minOccurs="0"/>
 *         &lt;element name="calificacionDeRiesgo" type="{http://www.bcie.org/DeclaracionJuradaBO}CalificacionDeRiesgo" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaFirmaDeclaracion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="comentarioDeclaracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comentarioBusqueda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="elevarAOtraInstancia" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tipoOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoSeguimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esSoloLectura" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeclaracionJurada", propOrder = {
    "idDeclaracion",
    "codigoExternoDeclaracion",
    "titulo",
    "estadoDeclaracion",
    "estadoBusqueda",
    "calificacionDeRiesgo",
    "fechaRegistro",
    "fechaFirmaDeclaracion",
    "fechaVencimiento",
    "comentarioDeclaracion",
    "comentarioBusqueda",
    "elevarAOtraInstancia",
    "tipoOrigen",
    "tipoSeguimiento",
    "instanciaProceso",
    "esSoloLectura",
    "status"
})
public class DeclaracionJurada {

    protected Long idDeclaracion;
    protected Long codigoExternoDeclaracion;
    protected String titulo;
    protected EstadoDeclaracion estadoDeclaracion;
    protected EstadoBusqueda estadoBusqueda;
    protected CalificacionDeRiesgo calificacionDeRiesgo;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFirmaDeclaracion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaVencimiento;
    protected String comentarioDeclaracion;
    protected String comentarioBusqueda;
    protected Boolean elevarAOtraInstancia;
    protected String tipoOrigen;
    protected String tipoSeguimiento;
    protected String instanciaProceso;
    protected Boolean esSoloLectura;
    protected Boolean status;

    /**
     * Obtiene el valor de la propiedad idDeclaracion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDeclaracion() {
        return idDeclaracion;
    }

    /**
     * Define el valor de la propiedad idDeclaracion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDeclaracion(Long value) {
        this.idDeclaracion = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoExternoDeclaracion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodigoExternoDeclaracion() {
        return codigoExternoDeclaracion;
    }

    /**
     * Define el valor de la propiedad codigoExternoDeclaracion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodigoExternoDeclaracion(Long value) {
        this.codigoExternoDeclaracion = value;
    }

    /**
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoDeclaracion.
     * 
     * @return
     *     possible object is
     *     {@link EstadoDeclaracion }
     *     
     */
    public EstadoDeclaracion getEstadoDeclaracion() {
        return estadoDeclaracion;
    }

    /**
     * Define el valor de la propiedad estadoDeclaracion.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoDeclaracion }
     *     
     */
    public void setEstadoDeclaracion(EstadoDeclaracion value) {
        this.estadoDeclaracion = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoBusqueda.
     * 
     * @return
     *     possible object is
     *     {@link EstadoBusqueda }
     *     
     */
    public EstadoBusqueda getEstadoBusqueda() {
        return estadoBusqueda;
    }

    /**
     * Define el valor de la propiedad estadoBusqueda.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoBusqueda }
     *     
     */
    public void setEstadoBusqueda(EstadoBusqueda value) {
        this.estadoBusqueda = value;
    }

    /**
     * Obtiene el valor de la propiedad calificacionDeRiesgo.
     * 
     * @return
     *     possible object is
     *     {@link CalificacionDeRiesgo }
     *     
     */
    public CalificacionDeRiesgo getCalificacionDeRiesgo() {
        return calificacionDeRiesgo;
    }

    /**
     * Define el valor de la propiedad calificacionDeRiesgo.
     * 
     * @param value
     *     allowed object is
     *     {@link CalificacionDeRiesgo }
     *     
     */
    public void setCalificacionDeRiesgo(CalificacionDeRiesgo value) {
        this.calificacionDeRiesgo = value;
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
     * Obtiene el valor de la propiedad fechaFirmaDeclaracion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFirmaDeclaracion() {
        return fechaFirmaDeclaracion;
    }

    /**
     * Define el valor de la propiedad fechaFirmaDeclaracion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFirmaDeclaracion(XMLGregorianCalendar value) {
        this.fechaFirmaDeclaracion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVencimiento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Define el valor de la propiedad fechaVencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaVencimiento(XMLGregorianCalendar value) {
        this.fechaVencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad comentarioDeclaracion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentarioDeclaracion() {
        return comentarioDeclaracion;
    }

    /**
     * Define el valor de la propiedad comentarioDeclaracion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentarioDeclaracion(String value) {
        this.comentarioDeclaracion = value;
    }

    /**
     * Obtiene el valor de la propiedad comentarioBusqueda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentarioBusqueda() {
        return comentarioBusqueda;
    }

    /**
     * Define el valor de la propiedad comentarioBusqueda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentarioBusqueda(String value) {
        this.comentarioBusqueda = value;
    }

    /**
     * Obtiene el valor de la propiedad elevarAOtraInstancia.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isElevarAOtraInstancia() {
        return elevarAOtraInstancia;
    }

    /**
     * Define el valor de la propiedad elevarAOtraInstancia.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setElevarAOtraInstancia(Boolean value) {
        this.elevarAOtraInstancia = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOrigen() {
        return tipoOrigen;
    }

    /**
     * Define el valor de la propiedad tipoOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOrigen(String value) {
        this.tipoOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoSeguimiento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSeguimiento() {
        return tipoSeguimiento;
    }

    /**
     * Define el valor de la propiedad tipoSeguimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSeguimiento(String value) {
        this.tipoSeguimiento = value;
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
     * Obtiene el valor de la propiedad esSoloLectura.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsSoloLectura() {
        return esSoloLectura;
    }

    /**
     * Define el valor de la propiedad esSoloLectura.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsSoloLectura(Boolean value) {
        this.esSoloLectura = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStatus(Boolean value) {
        this.status = value;
    }

}
