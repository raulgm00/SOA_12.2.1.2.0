
package org.bcie.condicionbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.atributobo.Accion;
import org.bcie.atributobo.Atributo;
import org.bcie.atributobo.EntidadMinima;
import org.bcie.atributobo.EstadoTCC;
import org.bcie.catalogobo.Catalogo;
import org.bcie.documentobo.ListaDocumentos;


/**
 * Entidad Condicion - (Modelo canónico)
 *           Se usa para la administración de las condiciones.
 *       
 * 
 * <p>Clase Java para Condicion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Condicion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCondicion" type="{http://www.bcie.org/CondicionBO}idCondicion"/>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoCondicion" type="{http://www.bcie.org/CondicionBO}CatalogoCondicion" minOccurs="0"/>
 *         &lt;element name="categoriaCondicion" type="{http://www.bcie.org/CondicionBO}CategoriaCondicion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="controlCondicion" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="eventoCondicion" type="{http://www.bcie.org/CatalogoBO}Catalogo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tipoFechaInicio" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="plazo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="frecuenciaPlazo" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="fechaFinal" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="estadoTCC" type="{http://www.bcie.org/AtributoBO}EstadoTCC" minOccurs="0"/>
 *         &lt;element name="subEstadoTCC" type="{http://www.bcie.org/AtributoBO}EstadoTCC" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="condicionEnmendada" type="{http://www.bcie.org/CondicionBO}idCondicion" minOccurs="0"/>
 *         &lt;element name="fechaEnmienda" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="configAtributo" type="{http://www.bcie.org/AtributoBO}Atributo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="evidencia" type="{http://www.bcie.org/DocumentoBO}ListaDocumentos" minOccurs="0"/>
 *         &lt;element name="observaciones" type="{http://www.bcie.org/CondicionBO}ObservacionCondicion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lineaCredito" type="{http://www.bcie.org/AtributoBO}EntidadMinima" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fuente" type="{http://www.bcie.org/AtributoBO}EntidadMinima" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Accion" type="{http://www.bcie.org/AtributoBO}Accion" minOccurs="0"/>
 *         &lt;element name="fechaVigencia" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Cumplimientos" type="{http://www.bcie.org/CondicionBO}Cumplimiento" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tipoDesembolso" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="banEstatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Condicion", propOrder = {
    "idCondicion",
    "operacion",
    "nombre",
    "descripcion",
    "tipoCondicion",
    "categoriaCondicion",
    "controlCondicion",
    "eventoCondicion",
    "tipoFechaInicio",
    "fechaInicio",
    "plazo",
    "frecuenciaPlazo",
    "fechaFinal",
    "estadoTCC",
    "subEstadoTCC",
    "fechaRegistro",
    "estado",
    "condicionEnmendada",
    "fechaEnmienda",
    "configAtributo",
    "evidencia",
    "observaciones",
    "lineaCredito",
    "fuente",
    "accion",
    "fechaVigencia",
    "cumplimientos",
    "tipoDesembolso",
    "banEstatus"
})
public class Condicion {

    protected long idCondicion;
    protected long operacion;
    protected String nombre;
    protected String descripcion;
    protected CatalogoCondicion tipoCondicion;
    protected List<CategoriaCondicion> categoriaCondicion;
    protected Catalogo controlCondicion;
    protected List<Catalogo> eventoCondicion;
    protected Catalogo tipoFechaInicio;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicio;
    protected Integer plazo;
    protected Catalogo frecuenciaPlazo;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFinal;
    protected EstadoTCC estadoTCC;
    protected EstadoTCC subEstadoTCC;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected Boolean estado;
    protected Long condicionEnmendada;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEnmienda;
    protected List<Atributo> configAtributo;
    protected ListaDocumentos evidencia;
    protected List<ObservacionCondicion> observaciones;
    protected List<EntidadMinima> lineaCredito;
    protected List<EntidadMinima> fuente;
    @XmlElement(name = "Accion")
    @XmlSchemaType(name = "string")
    protected Accion accion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaVigencia;
    @XmlElement(name = "Cumplimientos")
    protected List<Cumplimiento> cumplimientos;
    protected Catalogo tipoDesembolso;
    @XmlElementRef(name = "banEstatus", namespace = "http://www.bcie.org/CondicionBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> banEstatus;

    /**
     * Obtiene el valor de la propiedad idCondicion.
     * 
     */
    public long getIdCondicion() {
        return idCondicion;
    }

    /**
     * Define el valor de la propiedad idCondicion.
     * 
     */
    public void setIdCondicion(long value) {
        this.idCondicion = value;
    }

    /**
     * Obtiene el valor de la propiedad operacion.
     * 
     */
    public long getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     */
    public void setOperacion(long value) {
        this.operacion = value;
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
     * Obtiene el valor de la propiedad tipoCondicion.
     * 
     * @return
     *     possible object is
     *     {@link CatalogoCondicion }
     *     
     */
    public CatalogoCondicion getTipoCondicion() {
        return tipoCondicion;
    }

    /**
     * Define el valor de la propiedad tipoCondicion.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogoCondicion }
     *     
     */
    public void setTipoCondicion(CatalogoCondicion value) {
        this.tipoCondicion = value;
    }

    /**
     * Gets the value of the categoriaCondicion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoriaCondicion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoriaCondicion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoriaCondicion }
     * 
     * 
     */
    public List<CategoriaCondicion> getCategoriaCondicion() {
        if (categoriaCondicion == null) {
            categoriaCondicion = new ArrayList<CategoriaCondicion>();
        }
        return this.categoriaCondicion;
    }

    /**
     * Obtiene el valor de la propiedad controlCondicion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getControlCondicion() {
        return controlCondicion;
    }

    /**
     * Define el valor de la propiedad controlCondicion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setControlCondicion(Catalogo value) {
        this.controlCondicion = value;
    }

    /**
     * Gets the value of the eventoCondicion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventoCondicion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventoCondicion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Catalogo }
     * 
     * 
     */
    public List<Catalogo> getEventoCondicion() {
        if (eventoCondicion == null) {
            eventoCondicion = new ArrayList<Catalogo>();
        }
        return this.eventoCondicion;
    }

    /**
     * Obtiene el valor de la propiedad tipoFechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoFechaInicio() {
        return tipoFechaInicio;
    }

    /**
     * Define el valor de la propiedad tipoFechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoFechaInicio(Catalogo value) {
        this.tipoFechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Define el valor de la propiedad fechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        this.fechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad plazo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPlazo() {
        return plazo;
    }

    /**
     * Define el valor de la propiedad plazo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPlazo(Integer value) {
        this.plazo = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuenciaPlazo.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getFrecuenciaPlazo() {
        return frecuenciaPlazo;
    }

    /**
     * Define el valor de la propiedad frecuenciaPlazo.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setFrecuenciaPlazo(Catalogo value) {
        this.frecuenciaPlazo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFinal.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinal() {
        return fechaFinal;
    }

    /**
     * Define el valor de la propiedad fechaFinal.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinal(XMLGregorianCalendar value) {
        this.fechaFinal = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoTCC.
     * 
     * @return
     *     possible object is
     *     {@link EstadoTCC }
     *     
     */
    public EstadoTCC getEstadoTCC() {
        return estadoTCC;
    }

    /**
     * Define el valor de la propiedad estadoTCC.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoTCC }
     *     
     */
    public void setEstadoTCC(EstadoTCC value) {
        this.estadoTCC = value;
    }

    /**
     * Obtiene el valor de la propiedad subEstadoTCC.
     * 
     * @return
     *     possible object is
     *     {@link EstadoTCC }
     *     
     */
    public EstadoTCC getSubEstadoTCC() {
        return subEstadoTCC;
    }

    /**
     * Define el valor de la propiedad subEstadoTCC.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoTCC }
     *     
     */
    public void setSubEstadoTCC(EstadoTCC value) {
        this.subEstadoTCC = value;
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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstado(Boolean value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad condicionEnmendada.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCondicionEnmendada() {
        return condicionEnmendada;
    }

    /**
     * Define el valor de la propiedad condicionEnmendada.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCondicionEnmendada(Long value) {
        this.condicionEnmendada = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEnmienda.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEnmienda() {
        return fechaEnmienda;
    }

    /**
     * Define el valor de la propiedad fechaEnmienda.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEnmienda(XMLGregorianCalendar value) {
        this.fechaEnmienda = value;
    }

    /**
     * Gets the value of the configAtributo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the configAtributo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfigAtributo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Atributo }
     * 
     * 
     */
    public List<Atributo> getConfigAtributo() {
        if (configAtributo == null) {
            configAtributo = new ArrayList<Atributo>();
        }
        return this.configAtributo;
    }

    /**
     * Obtiene el valor de la propiedad evidencia.
     * 
     * @return
     *     possible object is
     *     {@link ListaDocumentos }
     *     
     */
    public ListaDocumentos getEvidencia() {
        return evidencia;
    }

    /**
     * Define el valor de la propiedad evidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaDocumentos }
     *     
     */
    public void setEvidencia(ListaDocumentos value) {
        this.evidencia = value;
    }

    /**
     * Gets the value of the observaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the observaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObservaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObservacionCondicion }
     * 
     * 
     */
    public List<ObservacionCondicion> getObservaciones() {
        if (observaciones == null) {
            observaciones = new ArrayList<ObservacionCondicion>();
        }
        return this.observaciones;
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
     * {@link EntidadMinima }
     * 
     * 
     */
    public List<EntidadMinima> getLineaCredito() {
        if (lineaCredito == null) {
            lineaCredito = new ArrayList<EntidadMinima>();
        }
        return this.lineaCredito;
    }

    /**
     * Gets the value of the fuente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fuente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFuente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntidadMinima }
     * 
     * 
     */
    public List<EntidadMinima> getFuente() {
        if (fuente == null) {
            fuente = new ArrayList<EntidadMinima>();
        }
        return this.fuente;
    }

    /**
     * Obtiene el valor de la propiedad accion.
     * 
     * @return
     *     possible object is
     *     {@link Accion }
     *     
     */
    public Accion getAccion() {
        return accion;
    }

    /**
     * Define el valor de la propiedad accion.
     * 
     * @param value
     *     allowed object is
     *     {@link Accion }
     *     
     */
    public void setAccion(Accion value) {
        this.accion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVigencia.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * Define el valor de la propiedad fechaVigencia.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaVigencia(XMLGregorianCalendar value) {
        this.fechaVigencia = value;
    }

    /**
     * Gets the value of the cumplimientos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cumplimientos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCumplimientos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cumplimiento }
     * 
     * 
     */
    public List<Cumplimiento> getCumplimientos() {
        if (cumplimientos == null) {
            cumplimientos = new ArrayList<Cumplimiento>();
        }
        return this.cumplimientos;
    }

    /**
     * Obtiene el valor de la propiedad tipoDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoDesembolso() {
        return tipoDesembolso;
    }

    /**
     * Define el valor de la propiedad tipoDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoDesembolso(Catalogo value) {
        this.tipoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad banEstatus.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getBanEstatus() {
        return banEstatus;
    }

    /**
     * Define el valor de la propiedad banEstatus.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setBanEstatus(JAXBElement<Integer> value) {
        this.banEstatus = value;
    }

}
