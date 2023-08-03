
package org.bcie.adquisicionbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para NoObjecionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="NoObjecionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idNoObjecion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idAdquisicion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tipoNoObjecion" type="{http://www.bcie.org/AdquisicionBO}TipoNoObjecionType"/>
 *         &lt;element name="fechaPublicacion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaInicioDoctoBase" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaFinDoctoBase" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaRecepcionPropuesta" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="lugarObtenerDoctoBase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="correoInfoAdicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccionPresentacionPropuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultadoProceso" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="revisadoPublicacion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="publicado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="numeroCorrespondencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otorgoNoObjecion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fechaFirmaDocto" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="enProcesoBpm" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="concursante" type="{http://www.bcie.org/AdquisicionBO}ConcursanteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NoObjecionType", propOrder = {
    "idNoObjecion",
    "idAdquisicion",
    "tipoNoObjecion",
    "fechaPublicacion",
    "fechaInicioDoctoBase",
    "fechaFinDoctoBase",
    "fechaRecepcionPropuesta",
    "lugarObtenerDoctoBase",
    "correoInfoAdicional",
    "direccionPresentacionPropuesta",
    "resultadoProceso",
    "revisadoPublicacion",
    "publicado",
    "numeroCorrespondencia",
    "otorgoNoObjecion",
    "fechaFirmaDocto",
    "estado",
    "fechaRegistro",
    "enProcesoBpm",
    "concursante"
})
public class NoObjecionType {

    protected Long idNoObjecion;
    protected long idAdquisicion;
    @XmlElement(required = true)
    protected TipoNoObjecionType tipoNoObjecion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaPublicacion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicioDoctoBase;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFinDoctoBase;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRecepcionPropuesta;
    protected String lugarObtenerDoctoBase;
    protected String correoInfoAdicional;
    protected String direccionPresentacionPropuesta;
    protected Catalogo resultadoProceso;
    protected Boolean revisadoPublicacion;
    protected Boolean publicado;
    protected String numeroCorrespondencia;
    protected Boolean otorgoNoObjecion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFirmaDocto;
    protected boolean estado;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected Integer enProcesoBpm;
    protected List<ConcursanteType> concursante;

    /**
     * Obtiene el valor de la propiedad idNoObjecion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdNoObjecion() {
        return idNoObjecion;
    }

    /**
     * Define el valor de la propiedad idNoObjecion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdNoObjecion(Long value) {
        this.idNoObjecion = value;
    }

    /**
     * Obtiene el valor de la propiedad idAdquisicion.
     * 
     */
    public long getIdAdquisicion() {
        return idAdquisicion;
    }

    /**
     * Define el valor de la propiedad idAdquisicion.
     * 
     */
    public void setIdAdquisicion(long value) {
        this.idAdquisicion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoNoObjecion.
     * 
     * @return
     *     possible object is
     *     {@link TipoNoObjecionType }
     *     
     */
    public TipoNoObjecionType getTipoNoObjecion() {
        return tipoNoObjecion;
    }

    /**
     * Define el valor de la propiedad tipoNoObjecion.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoNoObjecionType }
     *     
     */
    public void setTipoNoObjecion(TipoNoObjecionType value) {
        this.tipoNoObjecion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPublicacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Define el valor de la propiedad fechaPublicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPublicacion(XMLGregorianCalendar value) {
        this.fechaPublicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicioDoctoBase.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioDoctoBase() {
        return fechaInicioDoctoBase;
    }

    /**
     * Define el valor de la propiedad fechaInicioDoctoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioDoctoBase(XMLGregorianCalendar value) {
        this.fechaInicioDoctoBase = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFinDoctoBase.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinDoctoBase() {
        return fechaFinDoctoBase;
    }

    /**
     * Define el valor de la propiedad fechaFinDoctoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinDoctoBase(XMLGregorianCalendar value) {
        this.fechaFinDoctoBase = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRecepcionPropuesta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRecepcionPropuesta() {
        return fechaRecepcionPropuesta;
    }

    /**
     * Define el valor de la propiedad fechaRecepcionPropuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRecepcionPropuesta(XMLGregorianCalendar value) {
        this.fechaRecepcionPropuesta = value;
    }

    /**
     * Obtiene el valor de la propiedad lugarObtenerDoctoBase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarObtenerDoctoBase() {
        return lugarObtenerDoctoBase;
    }

    /**
     * Define el valor de la propiedad lugarObtenerDoctoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarObtenerDoctoBase(String value) {
        this.lugarObtenerDoctoBase = value;
    }

    /**
     * Obtiene el valor de la propiedad correoInfoAdicional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoInfoAdicional() {
        return correoInfoAdicional;
    }

    /**
     * Define el valor de la propiedad correoInfoAdicional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoInfoAdicional(String value) {
        this.correoInfoAdicional = value;
    }

    /**
     * Obtiene el valor de la propiedad direccionPresentacionPropuesta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionPresentacionPropuesta() {
        return direccionPresentacionPropuesta;
    }

    /**
     * Define el valor de la propiedad direccionPresentacionPropuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionPresentacionPropuesta(String value) {
        this.direccionPresentacionPropuesta = value;
    }

    /**
     * Obtiene el valor de la propiedad resultadoProceso.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getResultadoProceso() {
        return resultadoProceso;
    }

    /**
     * Define el valor de la propiedad resultadoProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setResultadoProceso(Catalogo value) {
        this.resultadoProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad revisadoPublicacion.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRevisadoPublicacion() {
        return revisadoPublicacion;
    }

    /**
     * Define el valor de la propiedad revisadoPublicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRevisadoPublicacion(Boolean value) {
        this.revisadoPublicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad publicado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPublicado() {
        return publicado;
    }

    /**
     * Define el valor de la propiedad publicado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPublicado(Boolean value) {
        this.publicado = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroCorrespondencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCorrespondencia() {
        return numeroCorrespondencia;
    }

    /**
     * Define el valor de la propiedad numeroCorrespondencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCorrespondencia(String value) {
        this.numeroCorrespondencia = value;
    }

    /**
     * Obtiene el valor de la propiedad otorgoNoObjecion.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOtorgoNoObjecion() {
        return otorgoNoObjecion;
    }

    /**
     * Define el valor de la propiedad otorgoNoObjecion.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOtorgoNoObjecion(Boolean value) {
        this.otorgoNoObjecion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFirmaDocto.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFirmaDocto() {
        return fechaFirmaDocto;
    }

    /**
     * Define el valor de la propiedad fechaFirmaDocto.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFirmaDocto(XMLGregorianCalendar value) {
        this.fechaFirmaDocto = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     */
    public void setEstado(boolean value) {
        this.estado = value;
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
     * Obtiene el valor de la propiedad enProcesoBpm.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEnProcesoBpm() {
        return enProcesoBpm;
    }

    /**
     * Define el valor de la propiedad enProcesoBpm.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEnProcesoBpm(Integer value) {
        this.enProcesoBpm = value;
    }

    /**
     * Gets the value of the concursante property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the concursante property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConcursante().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConcursanteType }
     * 
     * 
     */
    public List<ConcursanteType> getConcursante() {
        if (concursante == null) {
            concursante = new ArrayList<ConcursanteType>();
        }
        return this.concursante;
    }

}
