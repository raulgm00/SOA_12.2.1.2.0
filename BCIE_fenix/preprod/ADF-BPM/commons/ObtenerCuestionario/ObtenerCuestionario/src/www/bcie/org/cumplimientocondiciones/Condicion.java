//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.01.04 a las 08:46:22 AM CST 
//


package www.bcie.org.cumplimientocondiciones;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para condicion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="condicion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaVigenciaValidacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="priCategoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="priValidacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoriaValidacion" type="{http://www.bcie.org/herramientaOfismatica/cumplimientocondiciones}categoriaValidacion" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "condicion", propOrder = {
    "tipo",
    "nombre",
    "descripcion",
    "estado",
    "fechaVigenciaValidacion",
    "priCategoria",
    "priValidacion",
    "categoriaValidacion"
})
public class Condicion {

    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String descripcion;
    @XmlElement(required = true)
    protected String estado;
    @XmlElement(required = true)
    protected String fechaVigenciaValidacion;
    @XmlElement(required = true)
    protected String priCategoria;
    @XmlElement(required = true)
    protected String priValidacion;
    @XmlElement(required = true)
    protected List<CategoriaValidacion> categoriaValidacion;

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVigenciaValidacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVigenciaValidacion() {
        return fechaVigenciaValidacion;
    }

    /**
     * Define el valor de la propiedad fechaVigenciaValidacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVigenciaValidacion(String value) {
        this.fechaVigenciaValidacion = value;
    }

    /**
     * Obtiene el valor de la propiedad priCategoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriCategoria() {
        return priCategoria;
    }

    /**
     * Define el valor de la propiedad priCategoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriCategoria(String value) {
        this.priCategoria = value;
    }

    /**
     * Obtiene el valor de la propiedad priValidacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriValidacion() {
        return priValidacion;
    }

    /**
     * Define el valor de la propiedad priValidacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriValidacion(String value) {
        this.priValidacion = value;
    }

    /**
     * Gets the value of the categoriaValidacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoriaValidacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoriaValidacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoriaValidacion }
     * 
     * 
     */
    public List<CategoriaValidacion> getCategoriaValidacion() {
        if (categoriaValidacion == null) {
            categoriaValidacion = new ArrayList<CategoriaValidacion>();
        }
        return this.categoriaValidacion;
    }

}
