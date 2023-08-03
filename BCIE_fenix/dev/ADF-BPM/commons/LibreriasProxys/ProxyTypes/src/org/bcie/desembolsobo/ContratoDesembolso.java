
package org.bcie.desembolsobo;

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
import org.bcie.comisionbo.Comision;
import org.bcie.commonbo.MontoType;
import org.bcie.herramientacebo.HerramientaCEType;
import org.bcie.herramientacebo.ProgramaType;
import org.bcie.lineacreditobo.Fuente;


/**
 * <p>Clase Java para ContratoDesembolso complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ContratoDesembolso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}DesembolsoIdentificador"/>
 *         &lt;element name="producto" type="{http://www.bcie.org/DesembolsoBO}ProductoDesembolsoType" minOccurs="0"/>
 *         &lt;element name="referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.bcie.org/CommonBO}MontoType" maxOccurs="unbounded"/>
 *         &lt;element name="estado" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="programado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fechaEstimadaDesembolsar" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaEfectiva" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaAsignacion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="condicionesFinancieras" type="{http://www.bcie.org/DesembolsoBO}condicionesFinancieras" minOccurs="0"/>
 *         &lt;element name="idLinea" type="{http://www.bcie.org/LineaCreditoBO}idLineaCredito" minOccurs="0"/>
 *         &lt;element name="recursosExternos" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="cuentaCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaDisponibilidadFondos" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="origenTransferenciaCte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Programa" type="{http://www.bcie.org/HerramientaCEBO}ProgramaType" minOccurs="0"/>
 *         &lt;element name="EstimadoDesembolso" type="{http://www.bcie.org/DesembolsoBO}EstimadoDesembolsoType" minOccurs="0"/>
 *         &lt;element name="fechaInicioProceso" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaEstimadaDisponibilidad" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Utilizacion" type="{http://www.bcie.org/LineaCreditoBO}Fuente" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Cargo" type="{http://www.bcie.org/DesembolsoBO}CargoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Comision" type="{http://www.bcie.org/ComisionBO}Comision" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Transferencia" type="{http://www.bcie.org/DesembolsoBO}Transferencia" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TransferenciaFT05" type="{http://www.bcie.org/DesembolsoBO}TransferenciaFT05Type" minOccurs="0"/>
 *         &lt;element name="TransferenciaRecursos" type="{http://www.bcie.org/DesembolsoBO}TransferenciaRecursosType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="HerramientaCE" type="{http://www.bcie.org/HerramientaCEBO}HerramientaCEType" minOccurs="0"/>
 *         &lt;element name="modalidadFinan" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="idClasificacionEstrategica" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContratoDesembolso", propOrder = {
    "idDesembolso",
    "idFacturador",
    "producto",
    "referencia",
    "monto",
    "estado",
    "programado",
    "fechaEstimadaDesembolsar",
    "fechaEfectiva",
    "fechaAsignacion",
    "fechaRegistro",
    "fechaVencimiento",
    "estatus",
    "condicionesFinancieras",
    "idLinea",
    "recursosExternos",
    "cuentaCliente",
    "usuario",
    "fechaDisponibilidadFondos",
    "origenTransferenciaCte",
    "programa",
    "estimadoDesembolso",
    "fechaInicioProceso",
    "fechaEstimadaDisponibilidad",
    "utilizacion",
    "cargo",
    "comision",
    "transferencia",
    "transferenciaFT05",
    "transferenciaRecursos",
    "herramientaCE",
    "modalidadFinan",
    "idClasificacionEstrategica"
})
public class ContratoDesembolso {

    protected long idDesembolso;
    protected String idFacturador;
    protected ProductoDesembolsoType producto;
    protected String referencia;
    @XmlElement(required = true)
    protected List<MontoType> monto;
    @XmlElement(required = true)
    protected Catalogo estado;
    protected Boolean programado;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEstimadaDesembolsar;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEfectiva;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaAsignacion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaVencimiento;
    protected Boolean estatus;
    protected CondicionesFinancieras condicionesFinancieras;
    protected Long idLinea;
    protected Boolean recursosExternos;
    protected String cuentaCliente;
    protected String usuario;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaDisponibilidadFondos;
    protected String origenTransferenciaCte;
    @XmlElement(name = "Programa")
    protected ProgramaType programa;
    @XmlElement(name = "EstimadoDesembolso")
    protected EstimadoDesembolsoType estimadoDesembolso;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicioProceso;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEstimadaDisponibilidad;
    @XmlElement(name = "Utilizacion")
    protected List<Fuente> utilizacion;
    @XmlElement(name = "Cargo")
    protected List<CargoType> cargo;
    @XmlElement(name = "Comision")
    protected List<Comision> comision;
    @XmlElement(name = "Transferencia")
    protected List<Transferencia> transferencia;
    @XmlElement(name = "TransferenciaFT05")
    protected TransferenciaFT05Type transferenciaFT05;
    @XmlElement(name = "TransferenciaRecursos")
    protected List<TransferenciaRecursosType> transferenciaRecursos;
    @XmlElement(name = "HerramientaCE")
    protected HerramientaCEType herramientaCE;
    protected Catalogo modalidadFinan;
    protected BigInteger idClasificacionEstrategica;

    /**
     * Obtiene el valor de la propiedad idDesembolso.
     * 
     */
    public long getIdDesembolso() {
        return idDesembolso;
    }

    /**
     * Define el valor de la propiedad idDesembolso.
     * 
     */
    public void setIdDesembolso(long value) {
        this.idDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad idFacturador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFacturador() {
        return idFacturador;
    }

    /**
     * Define el valor de la propiedad idFacturador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFacturador(String value) {
        this.idFacturador = value;
    }

    /**
     * Obtiene el valor de la propiedad producto.
     * 
     * @return
     *     possible object is
     *     {@link ProductoDesembolsoType }
     *     
     */
    public ProductoDesembolsoType getProducto() {
        return producto;
    }

    /**
     * Define el valor de la propiedad producto.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductoDesembolsoType }
     *     
     */
    public void setProducto(ProductoDesembolsoType value) {
        this.producto = value;
    }

    /**
     * Obtiene el valor de la propiedad referencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Define el valor de la propiedad referencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencia(String value) {
        this.referencia = value;
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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEstado(Catalogo value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad programado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProgramado() {
        return programado;
    }

    /**
     * Define el valor de la propiedad programado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProgramado(Boolean value) {
        this.programado = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEstimadaDesembolsar.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEstimadaDesembolsar() {
        return fechaEstimadaDesembolsar;
    }

    /**
     * Define el valor de la propiedad fechaEstimadaDesembolsar.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEstimadaDesembolsar(XMLGregorianCalendar value) {
        this.fechaEstimadaDesembolsar = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEfectiva.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEfectiva() {
        return fechaEfectiva;
    }

    /**
     * Define el valor de la propiedad fechaEfectiva.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEfectiva(XMLGregorianCalendar value) {
        this.fechaEfectiva = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAsignacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * Define el valor de la propiedad fechaAsignacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAsignacion(XMLGregorianCalendar value) {
        this.fechaAsignacion = value;
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
     * Obtiene el valor de la propiedad estatus.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstatus() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstatus(Boolean value) {
        this.estatus = value;
    }

    /**
     * Obtiene el valor de la propiedad condicionesFinancieras.
     * 
     * @return
     *     possible object is
     *     {@link CondicionesFinancieras }
     *     
     */
    public CondicionesFinancieras getCondicionesFinancieras() {
        return condicionesFinancieras;
    }

    /**
     * Define el valor de la propiedad condicionesFinancieras.
     * 
     * @param value
     *     allowed object is
     *     {@link CondicionesFinancieras }
     *     
     */
    public void setCondicionesFinancieras(CondicionesFinancieras value) {
        this.condicionesFinancieras = value;
    }

    /**
     * Obtiene el valor de la propiedad idLinea.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdLinea() {
        return idLinea;
    }

    /**
     * Define el valor de la propiedad idLinea.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdLinea(Long value) {
        this.idLinea = value;
    }

    /**
     * Obtiene el valor de la propiedad recursosExternos.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecursosExternos() {
        return recursosExternos;
    }

    /**
     * Define el valor de la propiedad recursosExternos.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecursosExternos(Boolean value) {
        this.recursosExternos = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaCliente() {
        return cuentaCliente;
    }

    /**
     * Define el valor de la propiedad cuentaCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaCliente(String value) {
        this.cuentaCliente = value;
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
     * Obtiene el valor de la propiedad fechaDisponibilidadFondos.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaDisponibilidadFondos() {
        return fechaDisponibilidadFondos;
    }

    /**
     * Define el valor de la propiedad fechaDisponibilidadFondos.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaDisponibilidadFondos(XMLGregorianCalendar value) {
        this.fechaDisponibilidadFondos = value;
    }

    /**
     * Obtiene el valor de la propiedad origenTransferenciaCte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigenTransferenciaCte() {
        return origenTransferenciaCte;
    }

    /**
     * Define el valor de la propiedad origenTransferenciaCte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigenTransferenciaCte(String value) {
        this.origenTransferenciaCte = value;
    }

    /**
     * Obtiene el valor de la propiedad programa.
     * 
     * @return
     *     possible object is
     *     {@link ProgramaType }
     *     
     */
    public ProgramaType getPrograma() {
        return programa;
    }

    /**
     * Define el valor de la propiedad programa.
     * 
     * @param value
     *     allowed object is
     *     {@link ProgramaType }
     *     
     */
    public void setPrograma(ProgramaType value) {
        this.programa = value;
    }

    /**
     * Obtiene el valor de la propiedad estimadoDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link EstimadoDesembolsoType }
     *     
     */
    public EstimadoDesembolsoType getEstimadoDesembolso() {
        return estimadoDesembolso;
    }

    /**
     * Define el valor de la propiedad estimadoDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link EstimadoDesembolsoType }
     *     
     */
    public void setEstimadoDesembolso(EstimadoDesembolsoType value) {
        this.estimadoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicioProceso.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioProceso() {
        return fechaInicioProceso;
    }

    /**
     * Define el valor de la propiedad fechaInicioProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioProceso(XMLGregorianCalendar value) {
        this.fechaInicioProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEstimadaDisponibilidad.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEstimadaDisponibilidad() {
        return fechaEstimadaDisponibilidad;
    }

    /**
     * Define el valor de la propiedad fechaEstimadaDisponibilidad.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEstimadaDisponibilidad(XMLGregorianCalendar value) {
        this.fechaEstimadaDisponibilidad = value;
    }

    /**
     * Gets the value of the utilizacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the utilizacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUtilizacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Fuente }
     * 
     * 
     */
    public List<Fuente> getUtilizacion() {
        if (utilizacion == null) {
            utilizacion = new ArrayList<Fuente>();
        }
        return this.utilizacion;
    }

    /**
     * Gets the value of the cargo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cargo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCargo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CargoType }
     * 
     * 
     */
    public List<CargoType> getCargo() {
        if (cargo == null) {
            cargo = new ArrayList<CargoType>();
        }
        return this.cargo;
    }

    /**
     * Gets the value of the comision property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comision property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComision().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comision }
     * 
     * 
     */
    public List<Comision> getComision() {
        if (comision == null) {
            comision = new ArrayList<Comision>();
        }
        return this.comision;
    }

    /**
     * Gets the value of the transferencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transferencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransferencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Transferencia }
     * 
     * 
     */
    public List<Transferencia> getTransferencia() {
        if (transferencia == null) {
            transferencia = new ArrayList<Transferencia>();
        }
        return this.transferencia;
    }

    /**
     * Obtiene el valor de la propiedad transferenciaFT05.
     * 
     * @return
     *     possible object is
     *     {@link TransferenciaFT05Type }
     *     
     */
    public TransferenciaFT05Type getTransferenciaFT05() {
        return transferenciaFT05;
    }

    /**
     * Define el valor de la propiedad transferenciaFT05.
     * 
     * @param value
     *     allowed object is
     *     {@link TransferenciaFT05Type }
     *     
     */
    public void setTransferenciaFT05(TransferenciaFT05Type value) {
        this.transferenciaFT05 = value;
    }

    /**
     * Gets the value of the transferenciaRecursos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transferenciaRecursos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransferenciaRecursos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransferenciaRecursosType }
     * 
     * 
     */
    public List<TransferenciaRecursosType> getTransferenciaRecursos() {
        if (transferenciaRecursos == null) {
            transferenciaRecursos = new ArrayList<TransferenciaRecursosType>();
        }
        return this.transferenciaRecursos;
    }

    /**
     * Obtiene el valor de la propiedad herramientaCE.
     * 
     * @return
     *     possible object is
     *     {@link HerramientaCEType }
     *     
     */
    public HerramientaCEType getHerramientaCE() {
        return herramientaCE;
    }

    /**
     * Define el valor de la propiedad herramientaCE.
     * 
     * @param value
     *     allowed object is
     *     {@link HerramientaCEType }
     *     
     */
    public void setHerramientaCE(HerramientaCEType value) {
        this.herramientaCE = value;
    }

    /**
     * Obtiene el valor de la propiedad modalidadFinan.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getModalidadFinan() {
        return modalidadFinan;
    }

    /**
     * Define el valor de la propiedad modalidadFinan.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setModalidadFinan(Catalogo value) {
        this.modalidadFinan = value;
    }

    /**
     * Obtiene el valor de la propiedad idClasificacionEstrategica.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdClasificacionEstrategica() {
        return idClasificacionEstrategica;
    }

    /**
     * Define el valor de la propiedad idClasificacionEstrategica.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdClasificacionEstrategica(BigInteger value) {
        this.idClasificacionEstrategica = value;
    }

}
