
package org.bcie.terminobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.atributobo.Accion;
import org.bcie.atributobo.Atributo;
import org.bcie.atributobo.EntidadMinima;
import org.bcie.atributobo.EstadoTCC;
import org.bcie.catalogobo.Catalogo;


/**
 * Entidad Termino - (Modelo canónico)
 *           Se usa para la administración de los terminos.
 *       
 * 
 * <p>Clase Java para Termino complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Termino">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTermino" type="{http://www.bcie.org/TerminoBO}idTermino"/>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoTermino" type="{http://www.bcie.org/TerminoBO}CatalogoTermino"/>
 *         &lt;element name="tipoFechaInicio" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="plazo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="frecuenciaPlazo" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="moneda" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tasa" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tipoTasa" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="frecuenciaRevision" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoFrecuenciaRevision" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="fechaInicioRevision" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="frecuenciaPagoInteres" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoFrecuenciaPagoInteres" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="fechaInicioPagoInteres" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="frecuenciaAmortizacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoFrecuenciaAmortizacion" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="mora" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="porcentajeCobertura" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="seAplicanRecursosConcesion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="seAplicanRecursosExternos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipoContraparte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="montoMinimoDesembolso" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="montoMaximoDesembolso" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tasaMinimaDesembolso" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tasaMaximaDesembolso" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="estadoTCC" type="{http://www.bcie.org/AtributoBO}EstadoTCC"/>
 *         &lt;element name="subEstadoTCC" type="{http://www.bcie.org/AtributoBO}EstadoTCC"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="terminoEnmendado" type="{http://www.bcie.org/TerminoBO}idTermino"/>
 *         &lt;element name="fechaEnmienda" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="porcentajeModificacion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="idTcaTipoAvance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="idTcaTipoPorcentaje" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="porcentaje" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="porcentajeInicial" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="porcentajeFinal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="loginUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginUsuarioUltimoCambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreUsuarioUltimoCambio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaUltimoCambio" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="requiereFondoPresupuestario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="configAtributo" type="{http://www.bcie.org/AtributoBO}Atributo" maxOccurs="unbounded"/>
 *         &lt;element name="Accion" type="{http://www.bcie.org/AtributoBO}Accion" minOccurs="0"/>
 *         &lt;element name="Contraparte" type="{http://www.bcie.org/AtributoBO}EntidadMinima" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Termino", propOrder = {
    "idTermino",
    "operacion",
    "nombre",
    "descripcion",
    "tipoTermino",
    "tipoFechaInicio",
    "fechaInicio",
    "plazo",
    "frecuenciaPlazo",
    "fechaVencimiento",
    "moneda",
    "monto",
    "tasa",
    "tipoTasa",
    "fecha",
    "frecuenciaRevision",
    "tipoFrecuenciaRevision",
    "fechaInicioRevision",
    "frecuenciaPagoInteres",
    "tipoFrecuenciaPagoInteres",
    "fechaInicioPagoInteres",
    "frecuenciaAmortizacion",
    "tipoFrecuenciaAmortizacion",
    "mora",
    "porcentajeCobertura",
    "seAplicanRecursosConcesion",
    "seAplicanRecursosExternos",
    "tipoContraparte",
    "montoMinimoDesembolso",
    "montoMaximoDesembolso",
    "tasaMinimaDesembolso",
    "tasaMaximaDesembolso",
    "estadoTCC",
    "subEstadoTCC",
    "fechaRegistro",
    "estado",
    "terminoEnmendado",
    "fechaEnmienda",
    "porcentajeModificacion",
    "idTcaTipoAvance",
    "idTcaTipoPorcentaje",
    "porcentaje",
    "porcentajeInicial",
    "porcentajeFinal",
    "loginUsuario",
    "loginUsuarioUltimoCambio",
    "nombreUsuario",
    "nombreUsuarioUltimoCambio",
    "fechaUltimoCambio",
    "requiereFondoPresupuestario",
    "configAtributo",
    "accion",
    "contraparte"
})
public class Termino {

    protected long idTermino;
    protected long operacion;
    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String descripcion;
    @XmlElement(required = true)
    protected CatalogoTermino tipoTermino;
    @XmlElement(required = true)
    protected Catalogo tipoFechaInicio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicio;
    protected int plazo;
    @XmlElement(required = true)
    protected Catalogo frecuenciaPlazo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaVencimiento;
    @XmlElement(required = true)
    protected Catalogo moneda;
    protected double monto;
    protected double tasa;
    @XmlElement(required = true)
    protected Catalogo tipoTasa;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fecha;
    protected int frecuenciaRevision;
    @XmlElement(required = true)
    protected Catalogo tipoFrecuenciaRevision;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicioRevision;
    protected int frecuenciaPagoInteres;
    @XmlElement(required = true)
    protected Catalogo tipoFrecuenciaPagoInteres;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicioPagoInteres;
    protected int frecuenciaAmortizacion;
    @XmlElement(required = true)
    protected Catalogo tipoFrecuenciaAmortizacion;
    protected double mora;
    protected double porcentajeCobertura;
    protected boolean seAplicanRecursosConcesion;
    protected boolean seAplicanRecursosExternos;
    @XmlElement(required = true)
    protected String tipoContraparte;
    protected double montoMinimoDesembolso;
    protected double montoMaximoDesembolso;
    protected double tasaMinimaDesembolso;
    protected double tasaMaximaDesembolso;
    @XmlElement(required = true)
    protected EstadoTCC estadoTCC;
    @XmlElement(required = true)
    protected EstadoTCC subEstadoTCC;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected boolean estado;
    protected long terminoEnmendado;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEnmienda;
    protected double porcentajeModificacion;
    protected double idTcaTipoAvance;
    protected double idTcaTipoPorcentaje;
    protected double porcentaje;
    protected double porcentajeInicial;
    protected double porcentajeFinal;
    protected String loginUsuario;
    protected String loginUsuarioUltimoCambio;
    protected String nombreUsuario;
    protected String nombreUsuarioUltimoCambio;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaUltimoCambio;
    protected Boolean requiereFondoPresupuestario;
    @XmlElement(required = true)
    protected List<Atributo> configAtributo;
    @XmlElement(name = "Accion")
    @XmlSchemaType(name = "string")
    protected Accion accion;
    @XmlElement(name = "Contraparte")
    protected List<EntidadMinima> contraparte;

    /**
     * Obtiene el valor de la propiedad idTermino.
     * 
     */
    public long getIdTermino() {
        return idTermino;
    }

    /**
     * Define el valor de la propiedad idTermino.
     * 
     */
    public void setIdTermino(long value) {
        this.idTermino = value;
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
     * Obtiene el valor de la propiedad tipoTermino.
     * 
     * @return
     *     possible object is
     *     {@link CatalogoTermino }
     *     
     */
    public CatalogoTermino getTipoTermino() {
        return tipoTermino;
    }

    /**
     * Define el valor de la propiedad tipoTermino.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogoTermino }
     *     
     */
    public void setTipoTermino(CatalogoTermino value) {
        this.tipoTermino = value;
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
     */
    public int getPlazo() {
        return plazo;
    }

    /**
     * Define el valor de la propiedad plazo.
     * 
     */
    public void setPlazo(int value) {
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
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setMoneda(Catalogo value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     */
    public void setMonto(double value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad tasa.
     * 
     */
    public double getTasa() {
        return tasa;
    }

    /**
     * Define el valor de la propiedad tasa.
     * 
     */
    public void setTasa(double value) {
        this.tasa = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoTasa.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoTasa() {
        return tipoTasa;
    }

    /**
     * Define el valor de la propiedad tipoTasa.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoTasa(Catalogo value) {
        this.tipoTasa = value;
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
     * Obtiene el valor de la propiedad frecuenciaRevision.
     * 
     */
    public int getFrecuenciaRevision() {
        return frecuenciaRevision;
    }

    /**
     * Define el valor de la propiedad frecuenciaRevision.
     * 
     */
    public void setFrecuenciaRevision(int value) {
        this.frecuenciaRevision = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoFrecuenciaRevision.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoFrecuenciaRevision() {
        return tipoFrecuenciaRevision;
    }

    /**
     * Define el valor de la propiedad tipoFrecuenciaRevision.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoFrecuenciaRevision(Catalogo value) {
        this.tipoFrecuenciaRevision = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicioRevision.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioRevision() {
        return fechaInicioRevision;
    }

    /**
     * Define el valor de la propiedad fechaInicioRevision.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioRevision(XMLGregorianCalendar value) {
        this.fechaInicioRevision = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuenciaPagoInteres.
     * 
     */
    public int getFrecuenciaPagoInteres() {
        return frecuenciaPagoInteres;
    }

    /**
     * Define el valor de la propiedad frecuenciaPagoInteres.
     * 
     */
    public void setFrecuenciaPagoInteres(int value) {
        this.frecuenciaPagoInteres = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoFrecuenciaPagoInteres.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoFrecuenciaPagoInteres() {
        return tipoFrecuenciaPagoInteres;
    }

    /**
     * Define el valor de la propiedad tipoFrecuenciaPagoInteres.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoFrecuenciaPagoInteres(Catalogo value) {
        this.tipoFrecuenciaPagoInteres = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicioPagoInteres.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioPagoInteres() {
        return fechaInicioPagoInteres;
    }

    /**
     * Define el valor de la propiedad fechaInicioPagoInteres.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioPagoInteres(XMLGregorianCalendar value) {
        this.fechaInicioPagoInteres = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuenciaAmortizacion.
     * 
     */
    public int getFrecuenciaAmortizacion() {
        return frecuenciaAmortizacion;
    }

    /**
     * Define el valor de la propiedad frecuenciaAmortizacion.
     * 
     */
    public void setFrecuenciaAmortizacion(int value) {
        this.frecuenciaAmortizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoFrecuenciaAmortizacion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoFrecuenciaAmortizacion() {
        return tipoFrecuenciaAmortizacion;
    }

    /**
     * Define el valor de la propiedad tipoFrecuenciaAmortizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoFrecuenciaAmortizacion(Catalogo value) {
        this.tipoFrecuenciaAmortizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad mora.
     * 
     */
    public double getMora() {
        return mora;
    }

    /**
     * Define el valor de la propiedad mora.
     * 
     */
    public void setMora(double value) {
        this.mora = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajeCobertura.
     * 
     */
    public double getPorcentajeCobertura() {
        return porcentajeCobertura;
    }

    /**
     * Define el valor de la propiedad porcentajeCobertura.
     * 
     */
    public void setPorcentajeCobertura(double value) {
        this.porcentajeCobertura = value;
    }

    /**
     * Obtiene el valor de la propiedad seAplicanRecursosConcesion.
     * 
     */
    public boolean isSeAplicanRecursosConcesion() {
        return seAplicanRecursosConcesion;
    }

    /**
     * Define el valor de la propiedad seAplicanRecursosConcesion.
     * 
     */
    public void setSeAplicanRecursosConcesion(boolean value) {
        this.seAplicanRecursosConcesion = value;
    }

    /**
     * Obtiene el valor de la propiedad seAplicanRecursosExternos.
     * 
     */
    public boolean isSeAplicanRecursosExternos() {
        return seAplicanRecursosExternos;
    }

    /**
     * Define el valor de la propiedad seAplicanRecursosExternos.
     * 
     */
    public void setSeAplicanRecursosExternos(boolean value) {
        this.seAplicanRecursosExternos = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoContraparte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContraparte() {
        return tipoContraparte;
    }

    /**
     * Define el valor de la propiedad tipoContraparte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContraparte(String value) {
        this.tipoContraparte = value;
    }

    /**
     * Obtiene el valor de la propiedad montoMinimoDesembolso.
     * 
     */
    public double getMontoMinimoDesembolso() {
        return montoMinimoDesembolso;
    }

    /**
     * Define el valor de la propiedad montoMinimoDesembolso.
     * 
     */
    public void setMontoMinimoDesembolso(double value) {
        this.montoMinimoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad montoMaximoDesembolso.
     * 
     */
    public double getMontoMaximoDesembolso() {
        return montoMaximoDesembolso;
    }

    /**
     * Define el valor de la propiedad montoMaximoDesembolso.
     * 
     */
    public void setMontoMaximoDesembolso(double value) {
        this.montoMaximoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad tasaMinimaDesembolso.
     * 
     */
    public double getTasaMinimaDesembolso() {
        return tasaMinimaDesembolso;
    }

    /**
     * Define el valor de la propiedad tasaMinimaDesembolso.
     * 
     */
    public void setTasaMinimaDesembolso(double value) {
        this.tasaMinimaDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad tasaMaximaDesembolso.
     * 
     */
    public double getTasaMaximaDesembolso() {
        return tasaMaximaDesembolso;
    }

    /**
     * Define el valor de la propiedad tasaMaximaDesembolso.
     * 
     */
    public void setTasaMaximaDesembolso(double value) {
        this.tasaMaximaDesembolso = value;
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
     * Obtiene el valor de la propiedad terminoEnmendado.
     * 
     */
    public long getTerminoEnmendado() {
        return terminoEnmendado;
    }

    /**
     * Define el valor de la propiedad terminoEnmendado.
     * 
     */
    public void setTerminoEnmendado(long value) {
        this.terminoEnmendado = value;
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
     * Obtiene el valor de la propiedad porcentajeModificacion.
     * 
     */
    public double getPorcentajeModificacion() {
        return porcentajeModificacion;
    }

    /**
     * Define el valor de la propiedad porcentajeModificacion.
     * 
     */
    public void setPorcentajeModificacion(double value) {
        this.porcentajeModificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idTcaTipoAvance.
     * 
     */
    public double getIdTcaTipoAvance() {
        return idTcaTipoAvance;
    }

    /**
     * Define el valor de la propiedad idTcaTipoAvance.
     * 
     */
    public void setIdTcaTipoAvance(double value) {
        this.idTcaTipoAvance = value;
    }

    /**
     * Obtiene el valor de la propiedad idTcaTipoPorcentaje.
     * 
     */
    public double getIdTcaTipoPorcentaje() {
        return idTcaTipoPorcentaje;
    }

    /**
     * Define el valor de la propiedad idTcaTipoPorcentaje.
     * 
     */
    public void setIdTcaTipoPorcentaje(double value) {
        this.idTcaTipoPorcentaje = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentaje.
     * 
     */
    public double getPorcentaje() {
        return porcentaje;
    }

    /**
     * Define el valor de la propiedad porcentaje.
     * 
     */
    public void setPorcentaje(double value) {
        this.porcentaje = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajeInicial.
     * 
     */
    public double getPorcentajeInicial() {
        return porcentajeInicial;
    }

    /**
     * Define el valor de la propiedad porcentajeInicial.
     * 
     */
    public void setPorcentajeInicial(double value) {
        this.porcentajeInicial = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajeFinal.
     * 
     */
    public double getPorcentajeFinal() {
        return porcentajeFinal;
    }

    /**
     * Define el valor de la propiedad porcentajeFinal.
     * 
     */
    public void setPorcentajeFinal(double value) {
        this.porcentajeFinal = value;
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
     * Obtiene el valor de la propiedad loginUsuarioUltimoCambio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginUsuarioUltimoCambio() {
        return loginUsuarioUltimoCambio;
    }

    /**
     * Define el valor de la propiedad loginUsuarioUltimoCambio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginUsuarioUltimoCambio(String value) {
        this.loginUsuarioUltimoCambio = value;
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
     * Obtiene el valor de la propiedad nombreUsuarioUltimoCambio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreUsuarioUltimoCambio() {
        return nombreUsuarioUltimoCambio;
    }

    /**
     * Define el valor de la propiedad nombreUsuarioUltimoCambio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreUsuarioUltimoCambio(String value) {
        this.nombreUsuarioUltimoCambio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaUltimoCambio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaUltimoCambio() {
        return fechaUltimoCambio;
    }

    /**
     * Define el valor de la propiedad fechaUltimoCambio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaUltimoCambio(XMLGregorianCalendar value) {
        this.fechaUltimoCambio = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereFondoPresupuestario.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequiereFondoPresupuestario() {
        return requiereFondoPresupuestario;
    }

    /**
     * Define el valor de la propiedad requiereFondoPresupuestario.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequiereFondoPresupuestario(Boolean value) {
        this.requiereFondoPresupuestario = value;
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
     * Gets the value of the contraparte property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contraparte property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContraparte().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntidadMinima }
     * 
     * 
     */
    public List<EntidadMinima> getContraparte() {
        if (contraparte == null) {
            contraparte = new ArrayList<EntidadMinima>();
        }
        return this.contraparte;
    }

}
