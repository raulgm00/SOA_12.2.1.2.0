
package org.bcie.condicionbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para CatalogoCondicion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CatalogoCondicion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCatCondicion" type="{http://www.bcie.org/CatalogoBO}idCatalogo" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionCorta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idTipoCondicion" type="{http://www.bcie.org/CatalogoBO}idCatalogo" minOccurs="0"/>
 *         &lt;element name="esEditableEnFormalizacion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="requiereValidarCOFI" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="sePuedeDispensar" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="esPlantilla" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idCondicionPrecarga" type="{http://www.bcie.org/CondicionBO}idCondicion" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="codigoExterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoCondicion" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogoCondicion", propOrder = {
    "idCatCondicion",
    "descripcion",
    "descripcionCorta",
    "idTipoCondicion",
    "esEditableEnFormalizacion",
    "requiereValidarCOFI",
    "sePuedeDispensar",
    "esPlantilla",
    "idCondicionPrecarga",
    "fechaRegistro",
    "estado",
    "codigoExterno",
    "tipoCondicion"
})
public class CatalogoCondicion {

    protected Long idCatCondicion;
    protected String descripcion;
    protected String descripcionCorta;
    protected Long idTipoCondicion;
    protected Boolean esEditableEnFormalizacion;
    protected Boolean requiereValidarCOFI;
    protected Boolean sePuedeDispensar;
    protected Boolean esPlantilla;
    protected Long idCondicionPrecarga;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected Boolean estado;
    protected String codigoExterno;
    protected Catalogo tipoCondicion;

    /**
     * Obtiene el valor de la propiedad idCatCondicion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCatCondicion() {
        return idCatCondicion;
    }

    /**
     * Define el valor de la propiedad idCatCondicion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCatCondicion(Long value) {
        this.idCatCondicion = value;
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
     * Obtiene el valor de la propiedad descripcionCorta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    /**
     * Define el valor de la propiedad descripcionCorta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionCorta(String value) {
        this.descripcionCorta = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoCondicion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTipoCondicion() {
        return idTipoCondicion;
    }

    /**
     * Define el valor de la propiedad idTipoCondicion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTipoCondicion(Long value) {
        this.idTipoCondicion = value;
    }

    /**
     * Obtiene el valor de la propiedad esEditableEnFormalizacion.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsEditableEnFormalizacion() {
        return esEditableEnFormalizacion;
    }

    /**
     * Define el valor de la propiedad esEditableEnFormalizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsEditableEnFormalizacion(Boolean value) {
        this.esEditableEnFormalizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereValidarCOFI.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequiereValidarCOFI() {
        return requiereValidarCOFI;
    }

    /**
     * Define el valor de la propiedad requiereValidarCOFI.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequiereValidarCOFI(Boolean value) {
        this.requiereValidarCOFI = value;
    }

    /**
     * Obtiene el valor de la propiedad sePuedeDispensar.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSePuedeDispensar() {
        return sePuedeDispensar;
    }

    /**
     * Define el valor de la propiedad sePuedeDispensar.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSePuedeDispensar(Boolean value) {
        this.sePuedeDispensar = value;
    }

    /**
     * Obtiene el valor de la propiedad esPlantilla.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsPlantilla() {
        return esPlantilla;
    }

    /**
     * Define el valor de la propiedad esPlantilla.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsPlantilla(Boolean value) {
        this.esPlantilla = value;
    }

    /**
     * Obtiene el valor de la propiedad idCondicionPrecarga.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCondicionPrecarga() {
        return idCondicionPrecarga;
    }

    /**
     * Define el valor de la propiedad idCondicionPrecarga.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCondicionPrecarga(Long value) {
        this.idCondicionPrecarga = value;
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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstado(Boolean value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoExterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoExterno() {
        return codigoExterno;
    }

    /**
     * Define el valor de la propiedad codigoExterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoExterno(String value) {
        this.codigoExterno = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCondicion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoCondicion() {
        return tipoCondicion;
    }

    /**
     * Define el valor de la propiedad tipoCondicion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoCondicion(Catalogo value) {
        this.tipoCondicion = value;
    }

}
