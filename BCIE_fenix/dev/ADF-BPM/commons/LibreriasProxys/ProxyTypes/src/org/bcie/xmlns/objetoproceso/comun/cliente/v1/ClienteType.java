
package org.bcie.xmlns.objetoproceso.comun.cliente.v1;

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
 * Elemento que contiene la información del Cliente para su proceso de aprobacion BPM.
 * 
 * <p>Clase Java para ClienteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ClienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="IdFlexCube" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RazonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Abreviatura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdTipoPersona" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="IdTipoCliente" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="IdSector" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="IdTipoInstitucion" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="IdPais" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="IdGrupoEconomico" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="TipoIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdOficina" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="FechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="FechaAprobacion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ResponsableCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comentarios" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1}ComentarioType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AutorizoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FechaBaja" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DiaPago" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Contactos" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1}ContactoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClienteType", propOrder = {
    "idCliente",
    "idFlexCube",
    "razonSocial",
    "abreviatura",
    "idTipoPersona",
    "idTipoCliente",
    "idSector",
    "idTipoInstitucion",
    "idPais",
    "idGrupoEconomico",
    "tipoIdentificacion",
    "numeroIdentificacion",
    "idOficina",
    "fechaRegistro",
    "fechaAprobacion",
    "responsableCliente",
    "comentarios",
    "autorizoCliente",
    "estatus",
    "fechaBaja",
    "diaPago",
    "contactos"
})
public class ClienteType {

    @XmlElement(name = "IdCliente")
    protected Integer idCliente;
    @XmlElement(name = "IdFlexCube")
    protected String idFlexCube;
    @XmlElement(name = "RazonSocial")
    protected String razonSocial;
    @XmlElement(name = "Abreviatura")
    protected String abreviatura;
    @XmlElement(name = "IdTipoPersona")
    protected Catalogo idTipoPersona;
    @XmlElement(name = "IdTipoCliente")
    protected Catalogo idTipoCliente;
    @XmlElement(name = "IdSector")
    protected Catalogo idSector;
    @XmlElement(name = "IdTipoInstitucion")
    protected Catalogo idTipoInstitucion;
    @XmlElement(name = "IdPais")
    protected Catalogo idPais;
    @XmlElement(name = "IdGrupoEconomico")
    protected Catalogo idGrupoEconomico;
    @XmlElement(name = "TipoIdentificacion")
    protected String tipoIdentificacion;
    @XmlElement(name = "NumeroIdentificacion")
    protected String numeroIdentificacion;
    @XmlElement(name = "IdOficina")
    protected Catalogo idOficina;
    @XmlElement(name = "FechaRegistro")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlElement(name = "FechaAprobacion")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaAprobacion;
    @XmlElement(name = "ResponsableCliente")
    protected String responsableCliente;
    @XmlElement(name = "Comentarios")
    protected List<ComentarioType> comentarios;
    @XmlElement(name = "AutorizoCliente")
    protected String autorizoCliente;
    @XmlElement(name = "Estatus")
    protected Boolean estatus;
    @XmlElement(name = "FechaBaja")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaBaja;
    @XmlElement(name = "DiaPago")
    protected Integer diaPago;
    @XmlElement(name = "Contactos")
    protected List<ContactoType> contactos;

    /**
     * Obtiene el valor de la propiedad idCliente.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * Define el valor de la propiedad idCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdCliente(Integer value) {
        this.idCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad idFlexCube.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlexCube() {
        return idFlexCube;
    }

    /**
     * Define el valor de la propiedad idFlexCube.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlexCube(String value) {
        this.idFlexCube = value;
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
     * Obtiene el valor de la propiedad abreviatura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * Define el valor de la propiedad abreviatura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbreviatura(String value) {
        this.abreviatura = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoPersona.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIdTipoPersona() {
        return idTipoPersona;
    }

    /**
     * Define el valor de la propiedad idTipoPersona.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIdTipoPersona(Catalogo value) {
        this.idTipoPersona = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoCliente.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIdTipoCliente() {
        return idTipoCliente;
    }

    /**
     * Define el valor de la propiedad idTipoCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIdTipoCliente(Catalogo value) {
        this.idTipoCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad idSector.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIdSector() {
        return idSector;
    }

    /**
     * Define el valor de la propiedad idSector.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIdSector(Catalogo value) {
        this.idSector = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoInstitucion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIdTipoInstitucion() {
        return idTipoInstitucion;
    }

    /**
     * Define el valor de la propiedad idTipoInstitucion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIdTipoInstitucion(Catalogo value) {
        this.idTipoInstitucion = value;
    }

    /**
     * Obtiene el valor de la propiedad idPais.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIdPais() {
        return idPais;
    }

    /**
     * Define el valor de la propiedad idPais.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIdPais(Catalogo value) {
        this.idPais = value;
    }

    /**
     * Obtiene el valor de la propiedad idGrupoEconomico.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIdGrupoEconomico() {
        return idGrupoEconomico;
    }

    /**
     * Define el valor de la propiedad idGrupoEconomico.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIdGrupoEconomico(Catalogo value) {
        this.idGrupoEconomico = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoIdentificacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    /**
     * Define el valor de la propiedad tipoIdentificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoIdentificacion(String value) {
        this.tipoIdentificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroIdentificacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * Define el valor de la propiedad numeroIdentificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroIdentificacion(String value) {
        this.numeroIdentificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idOficina.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIdOficina() {
        return idOficina;
    }

    /**
     * Define el valor de la propiedad idOficina.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIdOficina(Catalogo value) {
        this.idOficina = value;
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
     * Obtiene el valor de la propiedad fechaAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAprobacion() {
        return fechaAprobacion;
    }

    /**
     * Define el valor de la propiedad fechaAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAprobacion(XMLGregorianCalendar value) {
        this.fechaAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad responsableCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsableCliente() {
        return responsableCliente;
    }

    /**
     * Define el valor de la propiedad responsableCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsableCliente(String value) {
        this.responsableCliente = value;
    }

    /**
     * Gets the value of the comentarios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comentarios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComentarios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComentarioType }
     * 
     * 
     */
    public List<ComentarioType> getComentarios() {
        if (comentarios == null) {
            comentarios = new ArrayList<ComentarioType>();
        }
        return this.comentarios;
    }

    /**
     * Obtiene el valor de la propiedad autorizoCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutorizoCliente() {
        return autorizoCliente;
    }

    /**
     * Define el valor de la propiedad autorizoCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutorizoCliente(String value) {
        this.autorizoCliente = value;
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
     * Obtiene el valor de la propiedad diaPago.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiaPago() {
        return diaPago;
    }

    /**
     * Define el valor de la propiedad diaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiaPago(Integer value) {
        this.diaPago = value;
    }

    /**
     * Gets the value of the contactos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactoType }
     * 
     * 
     */
    public List<ContactoType> getContactos() {
        if (contactos == null) {
            contactos = new ArrayList<ContactoType>();
        }
        return this.contactos;
    }

}
