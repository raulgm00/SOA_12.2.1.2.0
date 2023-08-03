
package org.bcie.xmlns.objetoproceso.comun.operacion.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Entidad que contiene la información basica de una operación del banco.
 * 
 * <p>Clase Java para Operacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Operacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoOperacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NombreOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResponsableOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NombreCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoProducto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NombreProducto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MontoSolicitado" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Pais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idSectorInstitucional" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idEncargado" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idRol" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="descripcionRol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Operacion", propOrder = {
    "codigoOperacion",
    "nombreOperacion",
    "responsableOperacion",
    "codigoCliente",
    "nombreCliente",
    "codigoProducto",
    "nombreProducto",
    "montoSolicitado",
    "pais",
    "idSectorInstitucional",
    "idEncargado",
    "idRol",
    "descripcionRol"
})
public class Operacion {

    @XmlElement(name = "CodigoOperacion")
    protected int codigoOperacion;
    @XmlElement(name = "NombreOperacion", required = true)
    protected String nombreOperacion;
    @XmlElement(name = "ResponsableOperacion")
    protected String responsableOperacion;
    @XmlElement(name = "CodigoCliente", required = true)
    protected String codigoCliente;
    @XmlElement(name = "NombreCliente", required = true)
    protected String nombreCliente;
    @XmlElement(name = "CodigoProducto")
    protected Integer codigoProducto;
    @XmlElement(name = "NombreProducto", required = true)
    protected String nombreProducto;
    @XmlElement(name = "MontoSolicitado")
    protected BigDecimal montoSolicitado;
    @XmlElement(name = "Pais", required = true)
    protected String pais;
    protected Long idSectorInstitucional;
    protected Long idEncargado;
    protected Long idRol;
    protected String descripcionRol;

    /**
     * Obtiene el valor de la propiedad codigoOperacion.
     * 
     */
    public int getCodigoOperacion() {
        return codigoOperacion;
    }

    /**
     * Define el valor de la propiedad codigoOperacion.
     * 
     */
    public void setCodigoOperacion(int value) {
        this.codigoOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOperacion() {
        return nombreOperacion;
    }

    /**
     * Define el valor de la propiedad nombreOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOperacion(String value) {
        this.nombreOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad responsableOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsableOperacion() {
        return responsableOperacion;
    }

    /**
     * Define el valor de la propiedad responsableOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsableOperacion(String value) {
        this.responsableOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Define el valor de la propiedad codigoCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCliente(String value) {
        this.codigoCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Define el valor de la propiedad nombreCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCliente(String value) {
        this.nombreCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoProducto.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * Define el valor de la propiedad codigoProducto.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoProducto(Integer value) {
        this.codigoProducto = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreProducto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Define el valor de la propiedad nombreProducto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreProducto(String value) {
        this.nombreProducto = value;
    }

    /**
     * Obtiene el valor de la propiedad montoSolicitado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    /**
     * Define el valor de la propiedad montoSolicitado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoSolicitado(BigDecimal value) {
        this.montoSolicitado = value;
    }

    /**
     * Obtiene el valor de la propiedad pais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Define el valor de la propiedad pais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
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

}
