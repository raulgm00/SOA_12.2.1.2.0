
package org.bcie.lineacreditobo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.commonbo.MontoType;


/**
 * <p>Clase Java para LineaCreditoBasicType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LineaCreditoBasicType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/LineaCreditoBO}TeenLineaCreditoType">
 *       &lt;sequence>
 *         &lt;element name="EsRevolvente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TratamientoDiasFeriados" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaValor" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="FechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="CodigoPantallaLimite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoSerialLimite" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CodigoAsignacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DescripcionAsignacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CondicionEspecial" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="FechaMaximaDesembolso" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="estado_formalizacion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="descCondEspecial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="frecuenciaGracia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="plazoGracia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="frecuenciaFinanciamiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="plazoFinanciamiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recursosExternos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tasaMinima" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="tasaMaxima" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="montoMinimo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="montoMaximo" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Monto" type="{http://www.bcie.org/CommonBO}MontoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EstadoMonto" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="diasSpotTasa" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idTcaTipoRedondeo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="cantidadDecimales" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idTcaTipoMora" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="codigoTasaReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorTasa" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="spreadTasa" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="frecuencia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idTcaTipoFrecuencia" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="MoverEntreMeses" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineaCreditoBasicType", propOrder = {
    "esRevolvente",
    "tratamientoDiasFeriados",
    "fechaValor",
    "fechaVencimiento",
    "codigoPantallaLimite",
    "codigoSerialLimite",
    "codigoAsignacion",
    "descripcionAsignacion",
    "condicionEspecial",
    "fechaRegistro",
    "fechaMaximaDesembolso",
    "estado",
    "estadoFormalizacion",
    "descCondEspecial",
    "frecuenciaGracia",
    "plazoGracia",
    "frecuenciaFinanciamiento",
    "plazoFinanciamiento",
    "recursosExternos",
    "tasaMinima",
    "tasaMaxima",
    "montoMinimo",
    "montoMaximo",
    "monto",
    "estadoMonto",
    "diasSpotTasa",
    "idTcaTipoRedondeo",
    "cantidadDecimales",
    "idTcaTipoMora",
    "codigoTasaReferencia",
    "valorTasa",
    "spreadTasa",
    "frecuencia",
    "idTcaTipoFrecuencia",
    "moverEntreMeses"
})
@XmlSeeAlso({
    LineaCredito.class
})
public class LineaCreditoBasicType
    extends TeenLineaCreditoType
{

    @XmlElement(name = "EsRevolvente")
    protected Boolean esRevolvente;
    @XmlElement(name = "TratamientoDiasFeriados", required = true)
    protected String tratamientoDiasFeriados;
    @XmlElement(name = "FechaValor", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaValor;
    @XmlElement(name = "FechaVencimiento", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaVencimiento;
    @XmlElement(name = "CodigoPantallaLimite", required = true)
    protected String codigoPantallaLimite;
    @XmlElement(name = "CodigoSerialLimite")
    protected int codigoSerialLimite;
    @XmlElement(name = "CodigoAsignacion")
    protected Long codigoAsignacion;
    @XmlElement(name = "DescripcionAsignacion", required = true)
    protected String descripcionAsignacion;
    @XmlElement(name = "CondicionEspecial")
    protected Boolean condicionEspecial;
    @XmlElement(name = "FechaRegistro", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlElement(name = "FechaMaximaDesembolso", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaMaximaDesembolso;
    @XmlElement(name = "Estado")
    protected Boolean estado;
    @XmlElement(name = "estado_formalizacion")
    protected Boolean estadoFormalizacion;
    @XmlElement(required = true)
    protected String descCondEspecial;
    protected int frecuenciaGracia;
    protected int plazoGracia;
    protected int frecuenciaFinanciamiento;
    protected int plazoFinanciamiento;
    protected boolean recursosExternos;
    @XmlElementRef(name = "tasaMinima", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> tasaMinima;
    @XmlElementRef(name = "tasaMaxima", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> tasaMaxima;
    @XmlElementRef(name = "montoMinimo", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> montoMinimo;
    @XmlElementRef(name = "montoMaximo", namespace = "http://www.bcie.org/LineaCreditoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> montoMaximo;
    @XmlElement(name = "Monto")
    protected List<MontoType> monto;
    @XmlElement(name = "EstadoMonto")
    protected Boolean estadoMonto;
    protected Integer diasSpotTasa;
    protected Long idTcaTipoRedondeo;
    protected Integer cantidadDecimales;
    protected Long idTcaTipoMora;
    protected String codigoTasaReferencia;
    protected BigDecimal valorTasa;
    protected BigDecimal spreadTasa;
    protected Integer frecuencia;
    protected Long idTcaTipoFrecuencia;
    @XmlElement(name = "MoverEntreMeses")
    protected BigDecimal moverEntreMeses;

    /**
     * Obtiene el valor de la propiedad esRevolvente.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsRevolvente() {
        return esRevolvente;
    }

    /**
     * Define el valor de la propiedad esRevolvente.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsRevolvente(Boolean value) {
        this.esRevolvente = value;
    }

    /**
     * Obtiene el valor de la propiedad tratamientoDiasFeriados.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTratamientoDiasFeriados() {
        return tratamientoDiasFeriados;
    }

    /**
     * Define el valor de la propiedad tratamientoDiasFeriados.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTratamientoDiasFeriados(String value) {
        this.tratamientoDiasFeriados = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaValor.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaValor() {
        return fechaValor;
    }

    /**
     * Define el valor de la propiedad fechaValor.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaValor(XMLGregorianCalendar value) {
        this.fechaValor = value;
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
     * Obtiene el valor de la propiedad codigoPantallaLimite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPantallaLimite() {
        return codigoPantallaLimite;
    }

    /**
     * Define el valor de la propiedad codigoPantallaLimite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPantallaLimite(String value) {
        this.codigoPantallaLimite = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoSerialLimite.
     * 
     */
    public int getCodigoSerialLimite() {
        return codigoSerialLimite;
    }

    /**
     * Define el valor de la propiedad codigoSerialLimite.
     * 
     */
    public void setCodigoSerialLimite(int value) {
        this.codigoSerialLimite = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoAsignacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodigoAsignacion() {
        return codigoAsignacion;
    }

    /**
     * Define el valor de la propiedad codigoAsignacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodigoAsignacion(Long value) {
        this.codigoAsignacion = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionAsignacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionAsignacion() {
        return descripcionAsignacion;
    }

    /**
     * Define el valor de la propiedad descripcionAsignacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionAsignacion(String value) {
        this.descripcionAsignacion = value;
    }

    /**
     * Obtiene el valor de la propiedad condicionEspecial.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCondicionEspecial() {
        return condicionEspecial;
    }

    /**
     * Define el valor de la propiedad condicionEspecial.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCondicionEspecial(Boolean value) {
        this.condicionEspecial = value;
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
     * Obtiene el valor de la propiedad fechaMaximaDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaMaximaDesembolso() {
        return fechaMaximaDesembolso;
    }

    /**
     * Define el valor de la propiedad fechaMaximaDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaMaximaDesembolso(XMLGregorianCalendar value) {
        this.fechaMaximaDesembolso = value;
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
     * Obtiene el valor de la propiedad estadoFormalizacion.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstadoFormalizacion() {
        return estadoFormalizacion;
    }

    /**
     * Define el valor de la propiedad estadoFormalizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstadoFormalizacion(Boolean value) {
        this.estadoFormalizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad descCondEspecial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescCondEspecial() {
        return descCondEspecial;
    }

    /**
     * Define el valor de la propiedad descCondEspecial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescCondEspecial(String value) {
        this.descCondEspecial = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuenciaGracia.
     * 
     */
    public int getFrecuenciaGracia() {
        return frecuenciaGracia;
    }

    /**
     * Define el valor de la propiedad frecuenciaGracia.
     * 
     */
    public void setFrecuenciaGracia(int value) {
        this.frecuenciaGracia = value;
    }

    /**
     * Obtiene el valor de la propiedad plazoGracia.
     * 
     */
    public int getPlazoGracia() {
        return plazoGracia;
    }

    /**
     * Define el valor de la propiedad plazoGracia.
     * 
     */
    public void setPlazoGracia(int value) {
        this.plazoGracia = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuenciaFinanciamiento.
     * 
     */
    public int getFrecuenciaFinanciamiento() {
        return frecuenciaFinanciamiento;
    }

    /**
     * Define el valor de la propiedad frecuenciaFinanciamiento.
     * 
     */
    public void setFrecuenciaFinanciamiento(int value) {
        this.frecuenciaFinanciamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad plazoFinanciamiento.
     * 
     */
    public int getPlazoFinanciamiento() {
        return plazoFinanciamiento;
    }

    /**
     * Define el valor de la propiedad plazoFinanciamiento.
     * 
     */
    public void setPlazoFinanciamiento(int value) {
        this.plazoFinanciamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad recursosExternos.
     * 
     */
    public boolean isRecursosExternos() {
        return recursosExternos;
    }

    /**
     * Define el valor de la propiedad recursosExternos.
     * 
     */
    public void setRecursosExternos(boolean value) {
        this.recursosExternos = value;
    }

    /**
     * Obtiene el valor de la propiedad tasaMinima.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getTasaMinima() {
        return tasaMinima;
    }

    /**
     * Define el valor de la propiedad tasaMinima.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setTasaMinima(JAXBElement<Double> value) {
        this.tasaMinima = value;
    }

    /**
     * Obtiene el valor de la propiedad tasaMaxima.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getTasaMaxima() {
        return tasaMaxima;
    }

    /**
     * Define el valor de la propiedad tasaMaxima.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setTasaMaxima(JAXBElement<Double> value) {
        this.tasaMaxima = value;
    }

    /**
     * Obtiene el valor de la propiedad montoMinimo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getMontoMinimo() {
        return montoMinimo;
    }

    /**
     * Define el valor de la propiedad montoMinimo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setMontoMinimo(JAXBElement<Double> value) {
        this.montoMinimo = value;
    }

    /**
     * Obtiene el valor de la propiedad montoMaximo.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getMontoMaximo() {
        return montoMaximo;
    }

    /**
     * Define el valor de la propiedad montoMaximo.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setMontoMaximo(JAXBElement<Double> value) {
        this.montoMaximo = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the monto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMonto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MontoType }
     * 
     * 
     */
    public List<MontoType> getMonto() {
        if (monto == null) {
            monto = new ArrayList<MontoType>();
        }
        return this.monto;
    }

    /**
     * Obtiene el valor de la propiedad estadoMonto.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstadoMonto() {
        return estadoMonto;
    }

    /**
     * Define el valor de la propiedad estadoMonto.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstadoMonto(Boolean value) {
        this.estadoMonto = value;
    }

    /**
     * Obtiene el valor de la propiedad diasSpotTasa.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiasSpotTasa() {
        return diasSpotTasa;
    }

    /**
     * Define el valor de la propiedad diasSpotTasa.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiasSpotTasa(Integer value) {
        this.diasSpotTasa = value;
    }

    /**
     * Obtiene el valor de la propiedad idTcaTipoRedondeo.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTcaTipoRedondeo() {
        return idTcaTipoRedondeo;
    }

    /**
     * Define el valor de la propiedad idTcaTipoRedondeo.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTcaTipoRedondeo(Long value) {
        this.idTcaTipoRedondeo = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadDecimales.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadDecimales() {
        return cantidadDecimales;
    }

    /**
     * Define el valor de la propiedad cantidadDecimales.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadDecimales(Integer value) {
        this.cantidadDecimales = value;
    }

    /**
     * Obtiene el valor de la propiedad idTcaTipoMora.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTcaTipoMora() {
        return idTcaTipoMora;
    }

    /**
     * Define el valor de la propiedad idTcaTipoMora.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTcaTipoMora(Long value) {
        this.idTcaTipoMora = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTasaReferencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTasaReferencia() {
        return codigoTasaReferencia;
    }

    /**
     * Define el valor de la propiedad codigoTasaReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTasaReferencia(String value) {
        this.codigoTasaReferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad valorTasa.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorTasa() {
        return valorTasa;
    }

    /**
     * Define el valor de la propiedad valorTasa.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorTasa(BigDecimal value) {
        this.valorTasa = value;
    }

    /**
     * Obtiene el valor de la propiedad spreadTasa.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSpreadTasa() {
        return spreadTasa;
    }

    /**
     * Define el valor de la propiedad spreadTasa.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSpreadTasa(BigDecimal value) {
        this.spreadTasa = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuencia.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFrecuencia() {
        return frecuencia;
    }

    /**
     * Define el valor de la propiedad frecuencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFrecuencia(Integer value) {
        this.frecuencia = value;
    }

    /**
     * Obtiene el valor de la propiedad idTcaTipoFrecuencia.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTcaTipoFrecuencia() {
        return idTcaTipoFrecuencia;
    }

    /**
     * Define el valor de la propiedad idTcaTipoFrecuencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTcaTipoFrecuencia(Long value) {
        this.idTcaTipoFrecuencia = value;
    }

    /**
     * Obtiene el valor de la propiedad moverEntreMeses.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMoverEntreMeses() {
        return moverEntreMeses;
    }

    /**
     * Define el valor de la propiedad moverEntreMeses.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMoverEntreMeses(BigDecimal value) {
        this.moverEntreMeses = value;
    }

}
