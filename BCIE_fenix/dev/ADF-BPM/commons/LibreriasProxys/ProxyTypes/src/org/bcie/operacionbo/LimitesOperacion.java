
package org.bcie.operacionbo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para LimitesOperacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LimitesOperacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="esPorcentaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="difValor" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="difPorcentaje" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimitesOperacion", propOrder = {
    "id",
    "descripcion",
    "estatus",
    "esPorcentaje",
    "difValor",
    "difPorcentaje"
})
public class LimitesOperacion {

    @XmlElement(required = true)
    protected BigDecimal id;
    @XmlElement(required = true)
    protected String descripcion;
    @XmlElement(required = true)
    protected String estatus;
    @XmlElement(required = true)
    protected String esPorcentaje;
    @XmlElement(required = true)
    protected BigDecimal difValor;
    @XmlElement(required = true)
    protected BigDecimal difPorcentaje;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setId(BigDecimal value) {
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
     * Obtiene el valor de la propiedad estatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstatus(String value) {
        this.estatus = value;
    }

    /**
     * Obtiene el valor de la propiedad esPorcentaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsPorcentaje() {
        return esPorcentaje;
    }

    /**
     * Define el valor de la propiedad esPorcentaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsPorcentaje(String value) {
        this.esPorcentaje = value;
    }

    /**
     * Obtiene el valor de la propiedad difValor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDifValor() {
        return difValor;
    }

    /**
     * Define el valor de la propiedad difValor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDifValor(BigDecimal value) {
        this.difValor = value;
    }

    /**
     * Obtiene el valor de la propiedad difPorcentaje.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDifPorcentaje() {
        return difPorcentaje;
    }

    /**
     * Define el valor de la propiedad difPorcentaje.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDifPorcentaje(BigDecimal value) {
        this.difPorcentaje = value;
    }

}
