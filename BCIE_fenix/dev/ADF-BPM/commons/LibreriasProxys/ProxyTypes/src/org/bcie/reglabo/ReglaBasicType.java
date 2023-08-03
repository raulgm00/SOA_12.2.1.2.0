
package org.bcie.reglabo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.atributobo.EntidadMinima;
import org.bcie.catalogobo.Catalogo;
import org.bcie.catalogobo.NoCatalogo;
import org.bcie.desembolsobo.ExcepcionType;
import org.bcie.reglatareabo.ReglaEvaluacionType;


/**
 * <p>Clase Java para ReglaBasicType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReglaBasicType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.bcie.org/ReglaBO}idRegla"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Transaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="PosibleExceptuar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Exceptuado" type="{http://www.bcie.org/CatalogoBO}NoCatalogo" minOccurs="0"/>
 *         &lt;element name="UsuarioExceptua" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaExcepcion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Estado" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="Estatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DetalleRegla" type="{http://www.bcie.org/AtributoBO}EntidadMinima" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReglaBasicType", propOrder = {
    "id",
    "descripcion",
    "transaccion",
    "tipo",
    "posibleExceptuar",
    "exceptuado",
    "usuarioExceptua",
    "fechaExcepcion",
    "estado",
    "estatus",
    "detalleRegla"
})
@XmlSeeAlso({
    ExcepcionType.class,
    ReglaEvaluacionType.class
})
public class ReglaBasicType {

    @XmlElement(name = "Id")
    protected long id;
    @XmlElement(name = "Descripcion")
    protected String descripcion;
    @XmlElement(name = "Transaccion")
    protected String transaccion;
    @XmlElement(name = "Tipo")
    protected Catalogo tipo;
    @XmlElement(name = "PosibleExceptuar")
    protected Boolean posibleExceptuar;
    @XmlElement(name = "Exceptuado")
    protected NoCatalogo exceptuado;
    @XmlElement(name = "UsuarioExceptua")
    protected String usuarioExceptua;
    @XmlElement(name = "FechaExcepcion")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaExcepcion;
    @XmlElement(name = "Estado")
    protected Catalogo estado;
    @XmlElement(name = "Estatus")
    protected Boolean estatus;
    @XmlElement(name = "DetalleRegla")
    protected List<EntidadMinima> detalleRegla;

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
     * Obtiene el valor de la propiedad transaccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransaccion() {
        return transaccion;
    }

    /**
     * Define el valor de la propiedad transaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransaccion(String value) {
        this.transaccion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipo(Catalogo value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad posibleExceptuar.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPosibleExceptuar() {
        return posibleExceptuar;
    }

    /**
     * Define el valor de la propiedad posibleExceptuar.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPosibleExceptuar(Boolean value) {
        this.posibleExceptuar = value;
    }

    /**
     * Obtiene el valor de la propiedad exceptuado.
     * 
     * @return
     *     possible object is
     *     {@link NoCatalogo }
     *     
     */
    public NoCatalogo getExceptuado() {
        return exceptuado;
    }

    /**
     * Define el valor de la propiedad exceptuado.
     * 
     * @param value
     *     allowed object is
     *     {@link NoCatalogo }
     *     
     */
    public void setExceptuado(NoCatalogo value) {
        this.exceptuado = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioExceptua.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioExceptua() {
        return usuarioExceptua;
    }

    /**
     * Define el valor de la propiedad usuarioExceptua.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioExceptua(String value) {
        this.usuarioExceptua = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaExcepcion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaExcepcion() {
        return fechaExcepcion;
    }

    /**
     * Define el valor de la propiedad fechaExcepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaExcepcion(XMLGregorianCalendar value) {
        this.fechaExcepcion = value;
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
     * Gets the value of the detalleRegla property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleRegla property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleRegla().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntidadMinima }
     * 
     * 
     */
    public List<EntidadMinima> getDetalleRegla() {
        if (detalleRegla == null) {
            detalleRegla = new ArrayList<EntidadMinima>();
        }
        return this.detalleRegla;
    }

}
