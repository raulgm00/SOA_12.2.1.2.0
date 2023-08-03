
package org.bcie.comisionbo;

import java.math.BigDecimal;
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
import org.bcie.atributobo.EstadoTCC;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para Comision complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Comision">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idComision" type="{http://www.bcie.org/ComisionBO}idComision"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoComision" type="{http://www.bcie.org/ComisionBO}CatalogoComision" minOccurs="0"/>
 *         &lt;element name="moneda" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="montoComision" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="montoBase" type="{http://www.bcie.org/ComisionBO}MontoBase" minOccurs="0"/>
 *         &lt;element name="fechaValor" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaInicioCapital" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaInicioComision" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="tipoFrecuencia" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="frecuenciaPago" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fondo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comisionCompartida" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="codigoDesembolso" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="numeroTesoreria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoContrato" type="{http://www.bcie.org/ComisionBO}codigoContrato" minOccurs="0"/>
 *         &lt;element name="momentoCobro" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="tipoTasa" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="baseCalculo" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="responsableComision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoTCC" type="{http://www.bcie.org/AtributoBO}EstadoTCC"/>
 *         &lt;element name="subEstadoTCC" type="{http://www.bcie.org/AtributoBO}EstadoTCC"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="comisionEnmendada" type="{http://www.bcie.org/ComisionBO}idComision"/>
 *         &lt;element name="fechaEnmienda" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="banSugerida" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroCuentaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="configAtributo" type="{http://www.bcie.org/AtributoBO}Atributo" maxOccurs="unbounded"/>
 *         &lt;element name="Accion" type="{http://www.bcie.org/AtributoBO}Accion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Comision", propOrder = {
    "idComision",
    "idOperacion",
    "nombre",
    "descripcion",
    "tipoComision",
    "moneda",
    "montoComision",
    "montoBase",
    "fechaValor",
    "fechaVencimiento",
    "fechaInicioCapital",
    "fechaInicioComision",
    "tipoFrecuencia",
    "frecuenciaPago",
    "fondo",
    "comisionCompartida",
    "codigoDesembolso",
    "numeroTesoreria",
    "codigoContrato",
    "momentoCobro",
    "tipoTasa",
    "baseCalculo",
    "responsableComision",
    "estadoTCC",
    "subEstadoTCC",
    "fechaRegistro",
    "estado",
    "comisionEnmendada",
    "fechaEnmienda",
    "banSugerida",
    "numeroCuentaCliente",
    "observaciones",
    "configAtributo",
    "accion"
})
public class Comision {

    protected long idComision;
    protected long idOperacion;
    protected String nombre;
    protected String descripcion;
    protected CatalogoComision tipoComision;
    protected Catalogo moneda;
    protected BigDecimal montoComision;
    protected MontoBase montoBase;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaValor;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaVencimiento;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicioCapital;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicioComision;
    protected Catalogo tipoFrecuencia;
    protected Integer frecuenciaPago;
    protected String fondo;
    protected Boolean comisionCompartida;
    protected Long codigoDesembolso;
    protected String numeroTesoreria;
    protected String codigoContrato;
    protected Catalogo momentoCobro;
    protected Catalogo tipoTasa;
    protected Catalogo baseCalculo;
    protected String responsableComision;
    @XmlElement(required = true)
    protected EstadoTCC estadoTCC;
    @XmlElement(required = true)
    protected EstadoTCC subEstadoTCC;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected boolean estado;
    protected long comisionEnmendada;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEnmienda;
    protected Integer banSugerida;
    protected String numeroCuentaCliente;
    protected String observaciones;
    @XmlElement(required = true)
    protected List<Atributo> configAtributo;
    @XmlElement(name = "Accion")
    @XmlSchemaType(name = "string")
    protected Accion accion;

    /**
     * Obtiene el valor de la propiedad idComision.
     * 
     */
    public long getIdComision() {
        return idComision;
    }

    /**
     * Define el valor de la propiedad idComision.
     * 
     */
    public void setIdComision(long value) {
        this.idComision = value;
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
     * Obtiene el valor de la propiedad tipoComision.
     * 
     * @return
     *     possible object is
     *     {@link CatalogoComision }
     *     
     */
    public CatalogoComision getTipoComision() {
        return tipoComision;
    }

    /**
     * Define el valor de la propiedad tipoComision.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogoComision }
     *     
     */
    public void setTipoComision(CatalogoComision value) {
        this.tipoComision = value;
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
     * Obtiene el valor de la propiedad montoComision.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoComision() {
        return montoComision;
    }

    /**
     * Define el valor de la propiedad montoComision.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoComision(BigDecimal value) {
        this.montoComision = value;
    }

    /**
     * Obtiene el valor de la propiedad montoBase.
     * 
     * @return
     *     possible object is
     *     {@link MontoBase }
     *     
     */
    public MontoBase getMontoBase() {
        return montoBase;
    }

    /**
     * Define el valor de la propiedad montoBase.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoBase }
     *     
     */
    public void setMontoBase(MontoBase value) {
        this.montoBase = value;
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
     * Obtiene el valor de la propiedad fechaInicioCapital.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioCapital() {
        return fechaInicioCapital;
    }

    /**
     * Define el valor de la propiedad fechaInicioCapital.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioCapital(XMLGregorianCalendar value) {
        this.fechaInicioCapital = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicioComision.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioComision() {
        return fechaInicioComision;
    }

    /**
     * Define el valor de la propiedad fechaInicioComision.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioComision(XMLGregorianCalendar value) {
        this.fechaInicioComision = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoFrecuencia.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    /**
     * Define el valor de la propiedad tipoFrecuencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoFrecuencia(Catalogo value) {
        this.tipoFrecuencia = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuenciaPago.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFrecuenciaPago() {
        return frecuenciaPago;
    }

    /**
     * Define el valor de la propiedad frecuenciaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFrecuenciaPago(Integer value) {
        this.frecuenciaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad fondo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFondo() {
        return fondo;
    }

    /**
     * Define el valor de la propiedad fondo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFondo(String value) {
        this.fondo = value;
    }

    /**
     * Obtiene el valor de la propiedad comisionCompartida.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isComisionCompartida() {
        return comisionCompartida;
    }

    /**
     * Define el valor de la propiedad comisionCompartida.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setComisionCompartida(Boolean value) {
        this.comisionCompartida = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodigoDesembolso() {
        return codigoDesembolso;
    }

    /**
     * Define el valor de la propiedad codigoDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodigoDesembolso(Long value) {
        this.codigoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroTesoreria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTesoreria() {
        return numeroTesoreria;
    }

    /**
     * Define el valor de la propiedad numeroTesoreria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTesoreria(String value) {
        this.numeroTesoreria = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoContrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoContrato() {
        return codigoContrato;
    }

    /**
     * Define el valor de la propiedad codigoContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoContrato(String value) {
        this.codigoContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad momentoCobro.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getMomentoCobro() {
        return momentoCobro;
    }

    /**
     * Define el valor de la propiedad momentoCobro.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setMomentoCobro(Catalogo value) {
        this.momentoCobro = value;
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
     * Obtiene el valor de la propiedad baseCalculo.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getBaseCalculo() {
        return baseCalculo;
    }

    /**
     * Define el valor de la propiedad baseCalculo.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setBaseCalculo(Catalogo value) {
        this.baseCalculo = value;
    }

    /**
     * Obtiene el valor de la propiedad responsableComision.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsableComision() {
        return responsableComision;
    }

    /**
     * Define el valor de la propiedad responsableComision.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsableComision(String value) {
        this.responsableComision = value;
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
     * Obtiene el valor de la propiedad comisionEnmendada.
     * 
     */
    public long getComisionEnmendada() {
        return comisionEnmendada;
    }

    /**
     * Define el valor de la propiedad comisionEnmendada.
     * 
     */
    public void setComisionEnmendada(long value) {
        this.comisionEnmendada = value;
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
     * Obtiene el valor de la propiedad banSugerida.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBanSugerida() {
        return banSugerida;
    }

    /**
     * Define el valor de la propiedad banSugerida.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBanSugerida(Integer value) {
        this.banSugerida = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroCuentaCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCuentaCliente() {
        return numeroCuentaCliente;
    }

    /**
     * Define el valor de la propiedad numeroCuentaCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCuentaCliente(String value) {
        this.numeroCuentaCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad observaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Define el valor de la propiedad observaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
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

}
