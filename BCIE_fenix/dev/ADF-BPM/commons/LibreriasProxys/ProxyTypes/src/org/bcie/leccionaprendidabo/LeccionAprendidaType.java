
package org.bcie.leccionaprendidabo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.operacionbo.OperacionBasicType;
import org.bcie.productobo.ProductoBasicType;
import org.bcie.xmlns.objetoproceso.comun.proceso.v1.Proceso;
import org.bcie.xmlns.objetoproceso.comun.tarea.v1.Tarea;


/**
 * <p>Clase Java para LeccionAprendidaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LeccionAprendidaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idLeccionAprendida" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recomendacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nivelIncidencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estado" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="producto" type="{http://www.bcie.org/ProductoBO}ProductoBasicType"/>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}OperacionBasicType"/>
 *         &lt;element name="proceso" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1}Proceso"/>
 *         &lt;element name="tarea" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1}Tarea"/>
 *         &lt;element name="categorias" type="{http://www.bcie.org/LeccionAprendidaBO}CategoriasType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LeccionAprendidaType", propOrder = {
    "idLeccionAprendida",
    "descripcion",
    "recomendacion",
    "nivelIncidencia",
    "usuario",
    "estado",
    "producto",
    "operacion",
    "proceso",
    "tarea",
    "categorias"
})
public class LeccionAprendidaType {

    protected long idLeccionAprendida;
    @XmlElement(required = true)
    protected String descripcion;
    @XmlElement(required = true)
    protected String recomendacion;
    @XmlElement(required = true)
    protected String nivelIncidencia;
    @XmlElement(required = true)
    protected String usuario;
    @XmlElement(required = true)
    protected Catalogo estado;
    @XmlElement(required = true)
    protected ProductoBasicType producto;
    @XmlElement(required = true)
    protected OperacionBasicType operacion;
    @XmlElement(required = true)
    protected Proceso proceso;
    @XmlElement(required = true)
    protected Tarea tarea;
    @XmlElement(required = true)
    protected CategoriasType categorias;

    /**
     * Obtiene el valor de la propiedad idLeccionAprendida.
     * 
     */
    public long getIdLeccionAprendida() {
        return idLeccionAprendida;
    }

    /**
     * Define el valor de la propiedad idLeccionAprendida.
     * 
     */
    public void setIdLeccionAprendida(long value) {
        this.idLeccionAprendida = value;
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
     * Obtiene el valor de la propiedad recomendacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecomendacion() {
        return recomendacion;
    }

    /**
     * Define el valor de la propiedad recomendacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecomendacion(String value) {
        this.recomendacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nivelIncidencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelIncidencia() {
        return nivelIncidencia;
    }

    /**
     * Define el valor de la propiedad nivelIncidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelIncidencia(String value) {
        this.nivelIncidencia = value;
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
     * Obtiene el valor de la propiedad producto.
     * 
     * @return
     *     possible object is
     *     {@link ProductoBasicType }
     *     
     */
    public ProductoBasicType getProducto() {
        return producto;
    }

    /**
     * Define el valor de la propiedad producto.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductoBasicType }
     *     
     */
    public void setProducto(ProductoBasicType value) {
        this.producto = value;
    }

    /**
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link OperacionBasicType }
     *     
     */
    public OperacionBasicType getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link OperacionBasicType }
     *     
     */
    public void setOperacion(OperacionBasicType value) {
        this.operacion = value;
    }

    /**
     * Obtiene el valor de la propiedad proceso.
     * 
     * @return
     *     possible object is
     *     {@link Proceso }
     *     
     */
    public Proceso getProceso() {
        return proceso;
    }

    /**
     * Define el valor de la propiedad proceso.
     * 
     * @param value
     *     allowed object is
     *     {@link Proceso }
     *     
     */
    public void setProceso(Proceso value) {
        this.proceso = value;
    }

    /**
     * Obtiene el valor de la propiedad tarea.
     * 
     * @return
     *     possible object is
     *     {@link Tarea }
     *     
     */
    public Tarea getTarea() {
        return tarea;
    }

    /**
     * Define el valor de la propiedad tarea.
     * 
     * @param value
     *     allowed object is
     *     {@link Tarea }
     *     
     */
    public void setTarea(Tarea value) {
        this.tarea = value;
    }

    /**
     * Obtiene el valor de la propiedad categorias.
     * 
     * @return
     *     possible object is
     *     {@link CategoriasType }
     *     
     */
    public CategoriasType getCategorias() {
        return categorias;
    }

    /**
     * Define el valor de la propiedad categorias.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoriasType }
     *     
     */
    public void setCategorias(CategoriasType value) {
        this.categorias = value;
    }

}
