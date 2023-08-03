
package org.bcie.adquisicionbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;
import org.bcie.commonbo.MontoType;


/**
 * <p>Clase Java para ConcursanteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConcursanteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idConcursante" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idNoObjecion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tipoPerfil" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nacionalidad" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="monto" type="{http://www.bcie.org/CommonBO}MontoType" minOccurs="0"/>
 *         &lt;element name="instanciaAprobacion" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConcursanteType", propOrder = {
    "idConcursante",
    "idNoObjecion",
    "tipoPerfil",
    "nombre",
    "nacionalidad",
    "monto",
    "instanciaAprobacion",
    "fechaRegistro"
})
public class ConcursanteType {

    protected long idConcursante;
    protected long idNoObjecion;
    @XmlElement(required = true)
    protected Catalogo tipoPerfil;
    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected Catalogo nacionalidad;
    protected MontoType monto;
    protected Catalogo instanciaAprobacion;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;

    /**
     * Obtiene el valor de la propiedad idConcursante.
     * 
     */
    public long getIdConcursante() {
        return idConcursante;
    }

    /**
     * Define el valor de la propiedad idConcursante.
     * 
     */
    public void setIdConcursante(long value) {
        this.idConcursante = value;
    }

    /**
     * Obtiene el valor de la propiedad idNoObjecion.
     * 
     */
    public long getIdNoObjecion() {
        return idNoObjecion;
    }

    /**
     * Define el valor de la propiedad idNoObjecion.
     * 
     */
    public void setIdNoObjecion(long value) {
        this.idNoObjecion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoPerfil.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoPerfil() {
        return tipoPerfil;
    }

    /**
     * Define el valor de la propiedad tipoPerfil.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoPerfil(Catalogo value) {
        this.tipoPerfil = value;
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
     * Obtiene el valor de la propiedad nacionalidad.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define el valor de la propiedad nacionalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setNacionalidad(Catalogo value) {
        this.nacionalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     * @return
     *     possible object is
     *     {@link MontoType }
     *     
     */
    public MontoType getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoType }
     *     
     */
    public void setMonto(MontoType value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getInstanciaAprobacion() {
        return instanciaAprobacion;
    }

    /**
     * Define el valor de la propiedad instanciaAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setInstanciaAprobacion(Catalogo value) {
        this.instanciaAprobacion = value;
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

}
