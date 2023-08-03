
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
import org.bcie.clientebo.ClienteBasicType;
import org.bcie.commonbo.MontoType;
import org.bcie.operacionbo.Operacion;


/**
 * <p>Clase Java para AdquisicionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AdquisicionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAdquisicion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *         &lt;element name="cliente" type="{http://www.bcie.org/ClienteBO}ClienteBasicType"/>
 *         &lt;element name="normativaAplicada" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="normativaEspecifica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adquisicionPrevia" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoAdquisicion" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="montoPresupuestado" type="{http://www.bcie.org/CommonBO}MontoType" minOccurs="0"/>
 *         &lt;element name="montoContratado" type="{http://www.bcie.org/CommonBO}MontoType" minOccurs="0"/>
 *         &lt;element name="montoAsociado" type="{http://www.bcie.org/CommonBO}MontoType" minOccurs="0"/>
 *         &lt;element name="tipoAlcance" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="fechaContrato" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="tipoModalidad" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="Adjudicatario" type="{http://www.bcie.org/AdquisicionBO}ConcursanteType" minOccurs="0"/>
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="objetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="noObjecion" type="{http://www.bcie.org/AdquisicionBO}NoObjecionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdquisicionType", propOrder = {
    "idAdquisicion",
    "operacion",
    "cliente",
    "normativaAplicada",
    "normativaEspecifica",
    "adquisicionPrevia",
    "numero",
    "tipoAdquisicion",
    "montoPresupuestado",
    "montoContratado",
    "montoAsociado",
    "tipoAlcance",
    "fechaContrato",
    "tipoModalidad",
    "adjudicatario",
    "titulo",
    "objetivo",
    "instanciaProceso",
    "estado",
    "fechaRegistro",
    "noObjecion"
})
public class AdquisicionType {

    protected long idAdquisicion;
    @XmlElement(required = true)
    protected Operacion operacion;
    @XmlElement(required = true)
    protected ClienteBasicType cliente;
    @XmlElement(required = true)
    protected Catalogo normativaAplicada;
    protected String normativaEspecifica;
    protected Boolean adquisicionPrevia;
    protected String numero;
    @XmlElement(required = true)
    protected Catalogo tipoAdquisicion;
    protected MontoType montoPresupuestado;
    protected MontoType montoContratado;
    protected MontoType montoAsociado;
    protected Catalogo tipoAlcance;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaContrato;
    protected Catalogo tipoModalidad;
    @XmlElement(name = "Adjudicatario")
    protected ConcursanteType adjudicatario;
    protected String titulo;
    protected String objetivo;
    protected String instanciaProceso;
    protected boolean estado;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected List<NoObjecionType> noObjecion;

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
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link Operacion }
     *     
     */
    public Operacion getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Operacion }
     *     
     */
    public void setOperacion(Operacion value) {
        this.operacion = value;
    }

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link ClienteBasicType }
     *     
     */
    public ClienteBasicType getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link ClienteBasicType }
     *     
     */
    public void setCliente(ClienteBasicType value) {
        this.cliente = value;
    }

    /**
     * Obtiene el valor de la propiedad normativaAplicada.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getNormativaAplicada() {
        return normativaAplicada;
    }

    /**
     * Define el valor de la propiedad normativaAplicada.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setNormativaAplicada(Catalogo value) {
        this.normativaAplicada = value;
    }

    /**
     * Obtiene el valor de la propiedad normativaEspecifica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNormativaEspecifica() {
        return normativaEspecifica;
    }

    /**
     * Define el valor de la propiedad normativaEspecifica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNormativaEspecifica(String value) {
        this.normativaEspecifica = value;
    }

    /**
     * Obtiene el valor de la propiedad adquisicionPrevia.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAdquisicionPrevia() {
        return adquisicionPrevia;
    }

    /**
     * Define el valor de la propiedad adquisicionPrevia.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAdquisicionPrevia(Boolean value) {
        this.adquisicionPrevia = value;
    }

    /**
     * Obtiene el valor de la propiedad numero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Define el valor de la propiedad numero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoAdquisicion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoAdquisicion() {
        return tipoAdquisicion;
    }

    /**
     * Define el valor de la propiedad tipoAdquisicion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoAdquisicion(Catalogo value) {
        this.tipoAdquisicion = value;
    }

    /**
     * Obtiene el valor de la propiedad montoPresupuestado.
     * 
     * @return
     *     possible object is
     *     {@link MontoType }
     *     
     */
    public MontoType getMontoPresupuestado() {
        return montoPresupuestado;
    }

    /**
     * Define el valor de la propiedad montoPresupuestado.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoType }
     *     
     */
    public void setMontoPresupuestado(MontoType value) {
        this.montoPresupuestado = value;
    }

    /**
     * Obtiene el valor de la propiedad montoContratado.
     * 
     * @return
     *     possible object is
     *     {@link MontoType }
     *     
     */
    public MontoType getMontoContratado() {
        return montoContratado;
    }

    /**
     * Define el valor de la propiedad montoContratado.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoType }
     *     
     */
    public void setMontoContratado(MontoType value) {
        this.montoContratado = value;
    }

    /**
     * Obtiene el valor de la propiedad montoAsociado.
     * 
     * @return
     *     possible object is
     *     {@link MontoType }
     *     
     */
    public MontoType getMontoAsociado() {
        return montoAsociado;
    }

    /**
     * Define el valor de la propiedad montoAsociado.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoType }
     *     
     */
    public void setMontoAsociado(MontoType value) {
        this.montoAsociado = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoAlcance.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoAlcance() {
        return tipoAlcance;
    }

    /**
     * Define el valor de la propiedad tipoAlcance.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoAlcance(Catalogo value) {
        this.tipoAlcance = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaContrato.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaContrato() {
        return fechaContrato;
    }

    /**
     * Define el valor de la propiedad fechaContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaContrato(XMLGregorianCalendar value) {
        this.fechaContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoModalidad.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoModalidad() {
        return tipoModalidad;
    }

    /**
     * Define el valor de la propiedad tipoModalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoModalidad(Catalogo value) {
        this.tipoModalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad adjudicatario.
     * 
     * @return
     *     possible object is
     *     {@link ConcursanteType }
     *     
     */
    public ConcursanteType getAdjudicatario() {
        return adjudicatario;
    }

    /**
     * Define el valor de la propiedad adjudicatario.
     * 
     * @param value
     *     allowed object is
     *     {@link ConcursanteType }
     *     
     */
    public void setAdjudicatario(ConcursanteType value) {
        this.adjudicatario = value;
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
     * Obtiene el valor de la propiedad objetivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * Define el valor de la propiedad objetivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjetivo(String value) {
        this.objetivo = value;
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
     * Gets the value of the noObjecion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the noObjecion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNoObjecion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NoObjecionType }
     * 
     * 
     */
    public List<NoObjecionType> getNoObjecion() {
        if (noObjecion == null) {
            noObjecion = new ArrayList<NoObjecionType>();
        }
        return this.noObjecion;
    }

}
