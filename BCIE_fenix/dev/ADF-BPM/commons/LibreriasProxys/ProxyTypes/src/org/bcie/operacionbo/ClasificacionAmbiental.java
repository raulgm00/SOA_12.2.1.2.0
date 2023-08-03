
package org.bcie.operacionbo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para ClasificacionAmbiental complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ClasificacionAmbiental">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.bcie.org/OperacionBO}idOperacion" minOccurs="0"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion" minOccurs="0"/>
 *         &lt;element name="sectorAmbiental" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="aporteAmbiental" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="categoriaAmbiental" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="subCategoriaAmbiental" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="banStatus" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClasificacionAmbiental", propOrder = {
    "id",
    "idOperacion",
    "sectorAmbiental",
    "aporteAmbiental",
    "categoriaAmbiental",
    "subCategoriaAmbiental",
    "banStatus",
    "fechaRegistro"
})
public class ClasificacionAmbiental {

    protected Long id;
    protected Long idOperacion;
    protected Catalogo sectorAmbiental;
    protected Catalogo aporteAmbiental;
    protected Catalogo categoriaAmbiental;
    protected Catalogo subCategoriaAmbiental;
    protected BigDecimal banStatus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdOperacion(Long value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad sectorAmbiental.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSectorAmbiental() {
        return sectorAmbiental;
    }

    /**
     * Define el valor de la propiedad sectorAmbiental.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSectorAmbiental(Catalogo value) {
        this.sectorAmbiental = value;
    }

    /**
     * Obtiene el valor de la propiedad aporteAmbiental.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getAporteAmbiental() {
        return aporteAmbiental;
    }

    /**
     * Define el valor de la propiedad aporteAmbiental.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setAporteAmbiental(Catalogo value) {
        this.aporteAmbiental = value;
    }

    /**
     * Obtiene el valor de la propiedad categoriaAmbiental.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getCategoriaAmbiental() {
        return categoriaAmbiental;
    }

    /**
     * Define el valor de la propiedad categoriaAmbiental.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setCategoriaAmbiental(Catalogo value) {
        this.categoriaAmbiental = value;
    }

    /**
     * Obtiene el valor de la propiedad subCategoriaAmbiental.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSubCategoriaAmbiental() {
        return subCategoriaAmbiental;
    }

    /**
     * Define el valor de la propiedad subCategoriaAmbiental.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSubCategoriaAmbiental(Catalogo value) {
        this.subCategoriaAmbiental = value;
    }

    /**
     * Obtiene el valor de la propiedad banStatus.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBanStatus() {
        return banStatus;
    }

    /**
     * Define el valor de la propiedad banStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBanStatus(BigDecimal value) {
        this.banStatus = value;
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
