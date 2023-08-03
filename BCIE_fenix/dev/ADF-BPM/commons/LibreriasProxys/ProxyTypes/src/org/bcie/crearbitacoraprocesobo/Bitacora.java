
package org.bcie.crearbitacoraprocesobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Registro de bitácora de procesos y tareas
 * 
 * <p>Clase Java para Bitacora complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Bitacora">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idOperacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="nombreOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCliente" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nombreProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idProcesoSiguiente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="esProceso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idTarea" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nombreTarea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="instanciaTarea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="esFinActividad" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="tiempoProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tiempoTarea" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="raroc" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="finalizado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="string01" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="string02" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="string03" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bitacora", propOrder = {
    "id",
    "idOperacion",
    "nombreOperacion",
    "idCliente",
    "razonSocial",
    "idProceso",
    "nombreProceso",
    "instanciaProceso",
    "idProcesoSiguiente",
    "esProceso",
    "idTarea",
    "nombreTarea",
    "instanciaTarea",
    "usuario",
    "nombreUsuario",
    "esFinActividad",
    "fechaRegistro",
    "tiempoProceso",
    "tiempoTarea",
    "raroc",
    "finalizado",
    "string01",
    "string02",
    "string03"
})
public class Bitacora {

    protected long id;
    protected Long idOperacion;
    protected String nombreOperacion;
    protected Long idCliente;
    protected String razonSocial;
    protected long idProceso;
    @XmlElement(required = true)
    protected String nombreProceso;
    @XmlElement(required = true)
    protected String instanciaProceso;
    @XmlElement(required = true)
    protected String idProcesoSiguiente;
    protected boolean esProceso;
    protected long idTarea;
    @XmlElement(required = true)
    protected String nombreTarea;
    @XmlElement(required = true)
    protected String instanciaTarea;
    @XmlElement(required = true)
    protected String usuario;
    @XmlElement(required = true)
    protected String nombreUsuario;
    protected boolean esFinActividad;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;
    protected long tiempoProceso;
    protected long tiempoTarea;
    protected double raroc;
    protected boolean finalizado;
    @XmlElement(required = true)
    protected String string01;
    @XmlElement(required = true)
    protected String string02;
    @XmlElement(required = true)
    protected String string03;

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
     * Obtiene el valor de la propiedad idOperacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdOperacion(Long value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOperacion() {
        return nombreOperacion;
    }

    /**
     * Define el valor de la propiedad nombreOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOperacion(String value) {
        this.nombreOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idCliente.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Define el valor de la propiedad idCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCliente(Long value) {
        this.idCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Define el valor de la propiedad razonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad idProceso.
     * 
     */
    public long getIdProceso() {
        return idProceso;
    }

    /**
     * Define el valor de la propiedad idProceso.
     * 
     */
    public void setIdProceso(long value) {
        this.idProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreProceso() {
        return nombreProceso;
    }

    /**
     * Define el valor de la propiedad nombreProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreProceso(String value) {
        this.nombreProceso = value;
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
     * Obtiene el valor de la propiedad idProcesoSiguiente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdProcesoSiguiente() {
        return idProcesoSiguiente;
    }

    /**
     * Define el valor de la propiedad idProcesoSiguiente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdProcesoSiguiente(String value) {
        this.idProcesoSiguiente = value;
    }

    /**
     * Obtiene el valor de la propiedad esProceso.
     * 
     */
    public boolean isEsProceso() {
        return esProceso;
    }

    /**
     * Define el valor de la propiedad esProceso.
     * 
     */
    public void setEsProceso(boolean value) {
        this.esProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad idTarea.
     * 
     */
    public long getIdTarea() {
        return idTarea;
    }

    /**
     * Define el valor de la propiedad idTarea.
     * 
     */
    public void setIdTarea(long value) {
        this.idTarea = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreTarea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTarea() {
        return nombreTarea;
    }

    /**
     * Define el valor de la propiedad nombreTarea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTarea(String value) {
        this.nombreTarea = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaTarea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanciaTarea() {
        return instanciaTarea;
    }

    /**
     * Define el valor de la propiedad instanciaTarea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanciaTarea(String value) {
        this.instanciaTarea = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
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
     * Obtiene el valor de la propiedad esFinActividad.
     * 
     */
    public boolean isEsFinActividad() {
        return esFinActividad;
    }

    /**
     * Define el valor de la propiedad esFinActividad.
     * 
     */
    public void setEsFinActividad(boolean value) {
        this.esFinActividad = value;
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
     * Obtiene el valor de la propiedad tiempoProceso.
     * 
     */
    public long getTiempoProceso() {
        return tiempoProceso;
    }

    /**
     * Define el valor de la propiedad tiempoProceso.
     * 
     */
    public void setTiempoProceso(long value) {
        this.tiempoProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad tiempoTarea.
     * 
     */
    public long getTiempoTarea() {
        return tiempoTarea;
    }

    /**
     * Define el valor de la propiedad tiempoTarea.
     * 
     */
    public void setTiempoTarea(long value) {
        this.tiempoTarea = value;
    }

    /**
     * Obtiene el valor de la propiedad raroc.
     * 
     */
    public double getRaroc() {
        return raroc;
    }

    /**
     * Define el valor de la propiedad raroc.
     * 
     */
    public void setRaroc(double value) {
        this.raroc = value;
    }

    /**
     * Obtiene el valor de la propiedad finalizado.
     * 
     */
    public boolean isFinalizado() {
        return finalizado;
    }

    /**
     * Define el valor de la propiedad finalizado.
     * 
     */
    public void setFinalizado(boolean value) {
        this.finalizado = value;
    }

    /**
     * Obtiene el valor de la propiedad string01.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getString01() {
        return string01;
    }

    /**
     * Define el valor de la propiedad string01.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setString01(String value) {
        this.string01 = value;
    }

    /**
     * Obtiene el valor de la propiedad string02.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getString02() {
        return string02;
    }

    /**
     * Define el valor de la propiedad string02.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setString02(String value) {
        this.string02 = value;
    }

    /**
     * Obtiene el valor de la propiedad string03.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getString03() {
        return string03;
    }

    /**
     * Define el valor de la propiedad string03.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setString03(String value) {
        this.string03 = value;
    }

}
