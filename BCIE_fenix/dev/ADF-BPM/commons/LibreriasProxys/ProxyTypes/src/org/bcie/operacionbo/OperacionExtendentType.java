
package org.bcie.operacionbo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;
import org.bcie.clientebo.Cliente;
import org.bcie.declaracionjuradabo.DeclaracionJurada;
import org.bcie.productobo.Producto;


/**
 * <p>Clase Java para OperacionExtendentType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OperacionExtendentType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/OperacionBO}OperacionBasicType">
 *       &lt;sequence>
 *         &lt;element name="cliente" type="{http://www.bcie.org/ClienteBO}Cliente" minOccurs="0"/>
 *         &lt;element name="producto" type="{http://www.bcie.org/ProductoBO}Producto" minOccurs="0"/>
 *         &lt;element name="descripcion" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="250"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="actividadEconomica" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="actividadEconomicaAsociada" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="objetivosEstrategicos" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;totalDigits value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="areaFocalizacion" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="ejeEstrategico" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="iniciativaEstrategica" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="cantidadPaises" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="sectorMercado" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="programadoPOA" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ejercicioPOA" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="tipoGarantia" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="componenteConcesionalidad" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="estructurador" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="beneficiario" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="unidadEjecutora" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="programa" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="asociadas" type="{http://www.bcie.org/OperacionBO}AsociadasOperaciones" minOccurs="0"/>
 *         &lt;element name="declaracionJurada" type="{http://www.bcie.org/DeclaracionJuradaBO}DeclaracionJurada" minOccurs="0"/>
 *         &lt;element name="masInformacionRiesgo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="informacionAdicionalRiesgo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="masInformacionJuridico" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="informacionAdicionalJuridico" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="contrapartesProhibidas" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="comentarioContrapartes" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="limitesAnalisis" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;totalDigits value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="limitesDeterminar" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="etapa" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;totalDigits value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="status" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fechaBaja" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="comentarioLaft" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cumpleLaft" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="calificacionRiesgo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="perspectiva" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="lineaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SRC" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="scrClienteDesembolsos" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="RAROC" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="TIR" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="TIREstatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requiereRecursosExternos" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="enProcesoLaft" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="aplicaLaft" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="montoOperacion" type="{http://www.bcie.org/OperacionBO}MontosOperacion"/>
 *         &lt;element name="idTcaTipoMoneda" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="monedaOperacionTipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clasificacionAmbiental" type="{http://www.bcie.org/OperacionBO}ClasificacionesAmbiental"/>
 *         &lt;element name="justificacionCancela" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="observacionCancela" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="justificacionEnvio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Factibilidad" type="{http://www.bcie.org/OperacionBO}Factibilidad" minOccurs="0"/>
 *         &lt;element name="TipoMoneda" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="esMultisectorial" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="clasificacionEstrategicaMultisectorial" type="{http://www.bcie.org/OperacionBO}ClasificacionEstrategicaMultisectorial" minOccurs="0"/>
 *         &lt;element name="insertClasificacionAmbiental" type="{http://www.bcie.org/OperacionBO}InsertClasificacionAmbiental" minOccurs="0"/>
 *         &lt;element name="idSectorInstitucional" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idEncargado" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="sectorInstitucional" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="encargado" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="idSectorEncargado" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="descripcionEncargado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idRol" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="descripcionRol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ubicacionProyecto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCatPais" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Pais" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperacionExtendentType", propOrder = {
    "cliente",
    "producto",
    "descripcion",
    "actividadEconomica",
    "actividadEconomicaAsociada",
    "objetivosEstrategicos",
    "areaFocalizacion",
    "ejeEstrategico",
    "iniciativaEstrategica",
    "cantidadPaises",
    "sectorMercado",
    "fechaInicio",
    "programadoPOA",
    "ejercicioPOA",
    "tipoGarantia",
    "componenteConcesionalidad",
    "estructurador",
    "beneficiario",
    "unidadEjecutora",
    "programa",
    "asociadas",
    "declaracionJurada",
    "masInformacionRiesgo",
    "informacionAdicionalRiesgo",
    "masInformacionJuridico",
    "informacionAdicionalJuridico",
    "contrapartesProhibidas",
    "comentarioContrapartes",
    "limitesAnalisis",
    "limitesDeterminar",
    "etapa",
    "status",
    "fechaBaja",
    "comentarioLaft",
    "cumpleLaft",
    "calificacionRiesgo",
    "perspectiva",
    "estado",
    "lineaCredito",
    "src",
    "scrClienteDesembolsos",
    "raroc",
    "tir",
    "tirEstatus",
    "requiereRecursosExternos",
    "enProcesoLaft",
    "aplicaLaft",
    "montoOperacion",
    "idTcaTipoMoneda",
    "monedaOperacionTipo",
    "clasificacionAmbiental",
    "justificacionCancela",
    "observacionCancela",
    "justificacionEnvio",
    "factibilidad",
    "tipoMoneda",
    "esMultisectorial",
    "clasificacionEstrategicaMultisectorial",
    "insertClasificacionAmbiental",
    "idSectorInstitucional",
    "idEncargado",
    "sectorInstitucional",
    "encargado",
    "idSectorEncargado",
    "descripcionEncargado",
    "idRol",
    "descripcionRol",
    "ubicacionProyecto",
    "idCatPais",
    "pais"
})
@XmlSeeAlso({
    Operacion.class
})
public class OperacionExtendentType
    extends OperacionBasicType
{

    protected Cliente cliente;
    protected Producto producto;
    protected String descripcion;
    protected Catalogo actividadEconomica;
    protected Catalogo actividadEconomicaAsociada;
    protected Integer objetivosEstrategicos;
    protected Catalogo areaFocalizacion;
    protected Catalogo ejeEstrategico;
    protected Catalogo iniciativaEstrategica;
    protected Catalogo cantidadPaises;
    protected Catalogo sectorMercado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicio;
    protected Boolean programadoPOA;
    protected Catalogo ejercicioPOA;
    protected Catalogo tipoGarantia;
    protected Boolean componenteConcesionalidad;
    protected String estructurador;
    protected String beneficiario;
    protected String unidadEjecutora;
    protected String programa;
    protected AsociadasOperaciones asociadas;
    protected DeclaracionJurada declaracionJurada;
    protected String masInformacionRiesgo;
    protected String informacionAdicionalRiesgo;
    protected String masInformacionJuridico;
    protected String informacionAdicionalJuridico;
    protected String contrapartesProhibidas;
    protected String comentarioContrapartes;
    protected Integer limitesAnalisis;
    protected Integer limitesDeterminar;
    protected Integer etapa;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaBaja;
    protected String comentarioLaft;
    protected String cumpleLaft;
    protected String calificacionRiesgo;
    protected Catalogo perspectiva;
    protected Catalogo estado;
    protected String lineaCredito;
    @XmlElement(name = "SRC")
    protected Catalogo src;
    protected Catalogo scrClienteDesembolsos;
    @XmlElement(name = "RAROC")
    protected Float raroc;
    @XmlElement(name = "TIR")
    protected Float tir;
    @XmlElement(name = "TIREstatus")
    protected String tirEstatus;
    protected Integer requiereRecursosExternos;
    protected Boolean enProcesoLaft;
    protected Boolean aplicaLaft;
    @XmlElement(required = true)
    protected MontosOperacion montoOperacion;
    protected Integer idTcaTipoMoneda;
    protected String monedaOperacionTipo;
    @XmlElement(required = true)
    protected ClasificacionesAmbiental clasificacionAmbiental;
    protected String justificacionCancela;
    protected String observacionCancela;
    protected String justificacionEnvio;
    @XmlElement(name = "Factibilidad")
    protected Factibilidad factibilidad;
    @XmlElement(name = "TipoMoneda")
    protected Catalogo tipoMoneda;
    protected Boolean esMultisectorial;
    protected ClasificacionEstrategicaMultisectorial clasificacionEstrategicaMultisectorial;
    protected InsertClasificacionAmbiental insertClasificacionAmbiental;
    protected Long idSectorInstitucional;
    protected Long idEncargado;
    protected Catalogo sectorInstitucional;
    protected Catalogo encargado;
    protected Long idSectorEncargado;
    protected String descripcionEncargado;
    protected Long idRol;
    protected String descripcionRol;
    protected String ubicacionProyecto;
    protected BigDecimal idCatPais;
    @XmlElement(name = "Pais")
    protected Catalogo pais;

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link Cliente }
     *     
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Cliente }
     *     
     */
    public void setCliente(Cliente value) {
        this.cliente = value;
    }

    /**
     * Obtiene el valor de la propiedad producto.
     * 
     * @return
     *     possible object is
     *     {@link Producto }
     *     
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Define el valor de la propiedad producto.
     * 
     * @param value
     *     allowed object is
     *     {@link Producto }
     *     
     */
    public void setProducto(Producto value) {
        this.producto = value;
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
     * Obtiene el valor de la propiedad actividadEconomica.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getActividadEconomica() {
        return actividadEconomica;
    }

    /**
     * Define el valor de la propiedad actividadEconomica.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setActividadEconomica(Catalogo value) {
        this.actividadEconomica = value;
    }

    /**
     * Obtiene el valor de la propiedad actividadEconomicaAsociada.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getActividadEconomicaAsociada() {
        return actividadEconomicaAsociada;
    }

    /**
     * Define el valor de la propiedad actividadEconomicaAsociada.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setActividadEconomicaAsociada(Catalogo value) {
        this.actividadEconomicaAsociada = value;
    }

    /**
     * Obtiene el valor de la propiedad objetivosEstrategicos.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getObjetivosEstrategicos() {
        return objetivosEstrategicos;
    }

    /**
     * Define el valor de la propiedad objetivosEstrategicos.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setObjetivosEstrategicos(Integer value) {
        this.objetivosEstrategicos = value;
    }

    /**
     * Obtiene el valor de la propiedad areaFocalizacion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getAreaFocalizacion() {
        return areaFocalizacion;
    }

    /**
     * Define el valor de la propiedad areaFocalizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setAreaFocalizacion(Catalogo value) {
        this.areaFocalizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad ejeEstrategico.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEjeEstrategico() {
        return ejeEstrategico;
    }

    /**
     * Define el valor de la propiedad ejeEstrategico.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEjeEstrategico(Catalogo value) {
        this.ejeEstrategico = value;
    }

    /**
     * Obtiene el valor de la propiedad iniciativaEstrategica.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIniciativaEstrategica() {
        return iniciativaEstrategica;
    }

    /**
     * Define el valor de la propiedad iniciativaEstrategica.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIniciativaEstrategica(Catalogo value) {
        this.iniciativaEstrategica = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadPaises.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getCantidadPaises() {
        return cantidadPaises;
    }

    /**
     * Define el valor de la propiedad cantidadPaises.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setCantidadPaises(Catalogo value) {
        this.cantidadPaises = value;
    }

    /**
     * Obtiene el valor de la propiedad sectorMercado.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSectorMercado() {
        return sectorMercado;
    }

    /**
     * Define el valor de la propiedad sectorMercado.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSectorMercado(Catalogo value) {
        this.sectorMercado = value;
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
     * Obtiene el valor de la propiedad programadoPOA.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProgramadoPOA() {
        return programadoPOA;
    }

    /**
     * Define el valor de la propiedad programadoPOA.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProgramadoPOA(Boolean value) {
        this.programadoPOA = value;
    }

    /**
     * Obtiene el valor de la propiedad ejercicioPOA.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEjercicioPOA() {
        return ejercicioPOA;
    }

    /**
     * Define el valor de la propiedad ejercicioPOA.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEjercicioPOA(Catalogo value) {
        this.ejercicioPOA = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoGarantia.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoGarantia() {
        return tipoGarantia;
    }

    /**
     * Define el valor de la propiedad tipoGarantia.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoGarantia(Catalogo value) {
        this.tipoGarantia = value;
    }

    /**
     * Obtiene el valor de la propiedad componenteConcesionalidad.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isComponenteConcesionalidad() {
        return componenteConcesionalidad;
    }

    /**
     * Define el valor de la propiedad componenteConcesionalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setComponenteConcesionalidad(Boolean value) {
        this.componenteConcesionalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad estructurador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstructurador() {
        return estructurador;
    }

    /**
     * Define el valor de la propiedad estructurador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstructurador(String value) {
        this.estructurador = value;
    }

    /**
     * Obtiene el valor de la propiedad beneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiario() {
        return beneficiario;
    }

    /**
     * Define el valor de la propiedad beneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiario(String value) {
        this.beneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadEjecutora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadEjecutora() {
        return unidadEjecutora;
    }

    /**
     * Define el valor de la propiedad unidadEjecutora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadEjecutora(String value) {
        this.unidadEjecutora = value;
    }

    /**
     * Obtiene el valor de la propiedad programa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * Define el valor de la propiedad programa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrograma(String value) {
        this.programa = value;
    }

    /**
     * Obtiene el valor de la propiedad asociadas.
     * 
     * @return
     *     possible object is
     *     {@link AsociadasOperaciones }
     *     
     */
    public AsociadasOperaciones getAsociadas() {
        return asociadas;
    }

    /**
     * Define el valor de la propiedad asociadas.
     * 
     * @param value
     *     allowed object is
     *     {@link AsociadasOperaciones }
     *     
     */
    public void setAsociadas(AsociadasOperaciones value) {
        this.asociadas = value;
    }

    /**
     * Obtiene el valor de la propiedad declaracionJurada.
     * 
     * @return
     *     possible object is
     *     {@link DeclaracionJurada }
     *     
     */
    public DeclaracionJurada getDeclaracionJurada() {
        return declaracionJurada;
    }

    /**
     * Define el valor de la propiedad declaracionJurada.
     * 
     * @param value
     *     allowed object is
     *     {@link DeclaracionJurada }
     *     
     */
    public void setDeclaracionJurada(DeclaracionJurada value) {
        this.declaracionJurada = value;
    }

    /**
     * Obtiene el valor de la propiedad masInformacionRiesgo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasInformacionRiesgo() {
        return masInformacionRiesgo;
    }

    /**
     * Define el valor de la propiedad masInformacionRiesgo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasInformacionRiesgo(String value) {
        this.masInformacionRiesgo = value;
    }

    /**
     * Obtiene el valor de la propiedad informacionAdicionalRiesgo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformacionAdicionalRiesgo() {
        return informacionAdicionalRiesgo;
    }

    /**
     * Define el valor de la propiedad informacionAdicionalRiesgo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformacionAdicionalRiesgo(String value) {
        this.informacionAdicionalRiesgo = value;
    }

    /**
     * Obtiene el valor de la propiedad masInformacionJuridico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasInformacionJuridico() {
        return masInformacionJuridico;
    }

    /**
     * Define el valor de la propiedad masInformacionJuridico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasInformacionJuridico(String value) {
        this.masInformacionJuridico = value;
    }

    /**
     * Obtiene el valor de la propiedad informacionAdicionalJuridico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformacionAdicionalJuridico() {
        return informacionAdicionalJuridico;
    }

    /**
     * Define el valor de la propiedad informacionAdicionalJuridico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformacionAdicionalJuridico(String value) {
        this.informacionAdicionalJuridico = value;
    }

    /**
     * Obtiene el valor de la propiedad contrapartesProhibidas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrapartesProhibidas() {
        return contrapartesProhibidas;
    }

    /**
     * Define el valor de la propiedad contrapartesProhibidas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrapartesProhibidas(String value) {
        this.contrapartesProhibidas = value;
    }

    /**
     * Obtiene el valor de la propiedad comentarioContrapartes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentarioContrapartes() {
        return comentarioContrapartes;
    }

    /**
     * Define el valor de la propiedad comentarioContrapartes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentarioContrapartes(String value) {
        this.comentarioContrapartes = value;
    }

    /**
     * Obtiene el valor de la propiedad limitesAnalisis.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLimitesAnalisis() {
        return limitesAnalisis;
    }

    /**
     * Define el valor de la propiedad limitesAnalisis.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLimitesAnalisis(Integer value) {
        this.limitesAnalisis = value;
    }

    /**
     * Obtiene el valor de la propiedad limitesDeterminar.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLimitesDeterminar() {
        return limitesDeterminar;
    }

    /**
     * Define el valor de la propiedad limitesDeterminar.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLimitesDeterminar(Integer value) {
        this.limitesDeterminar = value;
    }

    /**
     * Obtiene el valor de la propiedad etapa.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEtapa() {
        return etapa;
    }

    /**
     * Define el valor de la propiedad etapa.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEtapa(Integer value) {
        this.etapa = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaBaja.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Define el valor de la propiedad fechaBaja.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaBaja(XMLGregorianCalendar value) {
        this.fechaBaja = value;
    }

    /**
     * Obtiene el valor de la propiedad comentarioLaft.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentarioLaft() {
        return comentarioLaft;
    }

    /**
     * Define el valor de la propiedad comentarioLaft.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentarioLaft(String value) {
        this.comentarioLaft = value;
    }

    /**
     * Obtiene el valor de la propiedad cumpleLaft.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCumpleLaft() {
        return cumpleLaft;
    }

    /**
     * Define el valor de la propiedad cumpleLaft.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCumpleLaft(String value) {
        this.cumpleLaft = value;
    }

    /**
     * Obtiene el valor de la propiedad calificacionRiesgo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalificacionRiesgo() {
        return calificacionRiesgo;
    }

    /**
     * Define el valor de la propiedad calificacionRiesgo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalificacionRiesgo(String value) {
        this.calificacionRiesgo = value;
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
     * Obtiene el valor de la propiedad lineaCredito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineaCredito() {
        return lineaCredito;
    }

    /**
     * Define el valor de la propiedad lineaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineaCredito(String value) {
        this.lineaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad src.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSRC() {
        return src;
    }

    /**
     * Define el valor de la propiedad src.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSRC(Catalogo value) {
        this.src = value;
    }

    /**
     * Obtiene el valor de la propiedad scrClienteDesembolsos.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getScrClienteDesembolsos() {
        return scrClienteDesembolsos;
    }

    /**
     * Define el valor de la propiedad scrClienteDesembolsos.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setScrClienteDesembolsos(Catalogo value) {
        this.scrClienteDesembolsos = value;
    }

    /**
     * Obtiene el valor de la propiedad raroc.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getRAROC() {
        return raroc;
    }

    /**
     * Define el valor de la propiedad raroc.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setRAROC(Float value) {
        this.raroc = value;
    }

    /**
     * Obtiene el valor de la propiedad tir.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTIR() {
        return tir;
    }

    /**
     * Define el valor de la propiedad tir.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTIR(Float value) {
        this.tir = value;
    }

    /**
     * Obtiene el valor de la propiedad tirEstatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIREstatus() {
        return tirEstatus;
    }

    /**
     * Define el valor de la propiedad tirEstatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIREstatus(String value) {
        this.tirEstatus = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereRecursosExternos.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequiereRecursosExternos() {
        return requiereRecursosExternos;
    }

    /**
     * Define el valor de la propiedad requiereRecursosExternos.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequiereRecursosExternos(Integer value) {
        this.requiereRecursosExternos = value;
    }

    /**
     * Obtiene el valor de la propiedad enProcesoLaft.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnProcesoLaft() {
        return enProcesoLaft;
    }

    /**
     * Define el valor de la propiedad enProcesoLaft.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnProcesoLaft(Boolean value) {
        this.enProcesoLaft = value;
    }

    /**
     * Obtiene el valor de la propiedad aplicaLaft.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAplicaLaft() {
        return aplicaLaft;
    }

    /**
     * Define el valor de la propiedad aplicaLaft.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAplicaLaft(Boolean value) {
        this.aplicaLaft = value;
    }

    /**
     * Obtiene el valor de la propiedad montoOperacion.
     * 
     * @return
     *     possible object is
     *     {@link MontosOperacion }
     *     
     */
    public MontosOperacion getMontoOperacion() {
        return montoOperacion;
    }

    /**
     * Define el valor de la propiedad montoOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link MontosOperacion }
     *     
     */
    public void setMontoOperacion(MontosOperacion value) {
        this.montoOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idTcaTipoMoneda.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdTcaTipoMoneda() {
        return idTcaTipoMoneda;
    }

    /**
     * Define el valor de la propiedad idTcaTipoMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdTcaTipoMoneda(Integer value) {
        this.idTcaTipoMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaOperacionTipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaOperacionTipo() {
        return monedaOperacionTipo;
    }

    /**
     * Define el valor de la propiedad monedaOperacionTipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaOperacionTipo(String value) {
        this.monedaOperacionTipo = value;
    }

    /**
     * Obtiene el valor de la propiedad clasificacionAmbiental.
     * 
     * @return
     *     possible object is
     *     {@link ClasificacionesAmbiental }
     *     
     */
    public ClasificacionesAmbiental getClasificacionAmbiental() {
        return clasificacionAmbiental;
    }

    /**
     * Define el valor de la propiedad clasificacionAmbiental.
     * 
     * @param value
     *     allowed object is
     *     {@link ClasificacionesAmbiental }
     *     
     */
    public void setClasificacionAmbiental(ClasificacionesAmbiental value) {
        this.clasificacionAmbiental = value;
    }

    /**
     * Obtiene el valor de la propiedad justificacionCancela.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificacionCancela() {
        return justificacionCancela;
    }

    /**
     * Define el valor de la propiedad justificacionCancela.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificacionCancela(String value) {
        this.justificacionCancela = value;
    }

    /**
     * Obtiene el valor de la propiedad observacionCancela.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacionCancela() {
        return observacionCancela;
    }

    /**
     * Define el valor de la propiedad observacionCancela.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacionCancela(String value) {
        this.observacionCancela = value;
    }

    /**
     * Obtiene el valor de la propiedad justificacionEnvio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificacionEnvio() {
        return justificacionEnvio;
    }

    /**
     * Define el valor de la propiedad justificacionEnvio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificacionEnvio(String value) {
        this.justificacionEnvio = value;
    }

    /**
     * Obtiene el valor de la propiedad factibilidad.
     * 
     * @return
     *     possible object is
     *     {@link Factibilidad }
     *     
     */
    public Factibilidad getFactibilidad() {
        return factibilidad;
    }

    /**
     * Define el valor de la propiedad factibilidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Factibilidad }
     *     
     */
    public void setFactibilidad(Factibilidad value) {
        this.factibilidad = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoMoneda.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoMoneda() {
        return tipoMoneda;
    }

    /**
     * Define el valor de la propiedad tipoMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoMoneda(Catalogo value) {
        this.tipoMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad esMultisectorial.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsMultisectorial() {
        return esMultisectorial;
    }

    /**
     * Define el valor de la propiedad esMultisectorial.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsMultisectorial(Boolean value) {
        this.esMultisectorial = value;
    }

    /**
     * Obtiene el valor de la propiedad clasificacionEstrategicaMultisectorial.
     * 
     * @return
     *     possible object is
     *     {@link ClasificacionEstrategicaMultisectorial }
     *     
     */
    public ClasificacionEstrategicaMultisectorial getClasificacionEstrategicaMultisectorial() {
        return clasificacionEstrategicaMultisectorial;
    }

    /**
     * Define el valor de la propiedad clasificacionEstrategicaMultisectorial.
     * 
     * @param value
     *     allowed object is
     *     {@link ClasificacionEstrategicaMultisectorial }
     *     
     */
    public void setClasificacionEstrategicaMultisectorial(ClasificacionEstrategicaMultisectorial value) {
        this.clasificacionEstrategicaMultisectorial = value;
    }

    /**
     * Obtiene el valor de la propiedad insertClasificacionAmbiental.
     * 
     * @return
     *     possible object is
     *     {@link InsertClasificacionAmbiental }
     *     
     */
    public InsertClasificacionAmbiental getInsertClasificacionAmbiental() {
        return insertClasificacionAmbiental;
    }

    /**
     * Define el valor de la propiedad insertClasificacionAmbiental.
     * 
     * @param value
     *     allowed object is
     *     {@link InsertClasificacionAmbiental }
     *     
     */
    public void setInsertClasificacionAmbiental(InsertClasificacionAmbiental value) {
        this.insertClasificacionAmbiental = value;
    }

    /**
     * Obtiene el valor de la propiedad idSectorInstitucional.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdSectorInstitucional() {
        return idSectorInstitucional;
    }

    /**
     * Define el valor de la propiedad idSectorInstitucional.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdSectorInstitucional(Long value) {
        this.idSectorInstitucional = value;
    }

    /**
     * Obtiene el valor de la propiedad idEncargado.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdEncargado() {
        return idEncargado;
    }

    /**
     * Define el valor de la propiedad idEncargado.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdEncargado(Long value) {
        this.idEncargado = value;
    }

    /**
     * Obtiene el valor de la propiedad sectorInstitucional.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSectorInstitucional() {
        return sectorInstitucional;
    }

    /**
     * Define el valor de la propiedad sectorInstitucional.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSectorInstitucional(Catalogo value) {
        this.sectorInstitucional = value;
    }

    /**
     * Obtiene el valor de la propiedad encargado.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEncargado() {
        return encargado;
    }

    /**
     * Define el valor de la propiedad encargado.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEncargado(Catalogo value) {
        this.encargado = value;
    }

    /**
     * Obtiene el valor de la propiedad idSectorEncargado.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdSectorEncargado() {
        return idSectorEncargado;
    }

    /**
     * Define el valor de la propiedad idSectorEncargado.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdSectorEncargado(Long value) {
        this.idSectorEncargado = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionEncargado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionEncargado() {
        return descripcionEncargado;
    }

    /**
     * Define el valor de la propiedad descripcionEncargado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionEncargado(String value) {
        this.descripcionEncargado = value;
    }

    /**
     * Obtiene el valor de la propiedad idRol.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdRol() {
        return idRol;
    }

    /**
     * Define el valor de la propiedad idRol.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdRol(Long value) {
        this.idRol = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionRol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionRol() {
        return descripcionRol;
    }

    /**
     * Define el valor de la propiedad descripcionRol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionRol(String value) {
        this.descripcionRol = value;
    }

    /**
     * Obtiene el valor de la propiedad ubicacionProyecto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUbicacionProyecto() {
        return ubicacionProyecto;
    }

    /**
     * Define el valor de la propiedad ubicacionProyecto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUbicacionProyecto(String value) {
        this.ubicacionProyecto = value;
    }

    /**
     * Obtiene el valor de la propiedad idCatPais.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdCatPais() {
        return idCatPais;
    }

    /**
     * Define el valor de la propiedad idCatPais.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdCatPais(BigDecimal value) {
        this.idCatPais = value;
    }

    /**
     * Obtiene el valor de la propiedad pais.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getPais() {
        return pais;
    }

    /**
     * Define el valor de la propiedad pais.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setPais(Catalogo value) {
        this.pais = value;
    }

}
