
package org.bcie.comisionbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para CatalogoComision complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CatalogoComision">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCatComision" type="{http://www.bcie.org/CatalogoBO}idCatalogo" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcionCorta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idTipoComision" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="esEditableEnFormalizacion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereValidarCOFI" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sePuedeDispensar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="seRegistraEnFlexCube" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="esPlantilla" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idComisionPrecarga" type="{http://www.bcie.org/ComisionBO}idComision"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="codigoExterno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogoComision", propOrder = {
    "idCatComision",
    "descripcion",
    "descripcionCorta",
    "idTipoComision",
    "esEditableEnFormalizacion",
    "requiereValidarCOFI",
    "sePuedeDispensar",
    "seRegistraEnFlexCube",
    "esPlantilla",
    "idComisionPrecarga",
    "fechaRegistro",
    "estado",
    "codigoExterno"
})
public class CatalogoComision {

    protected Long idCatComision;
    @XmlElement(required = true)
    protected String descripcion;
    @XmlElement(required = true)
    protected String descripcionCorta;
    protected Catalogo idTipoComision;
    protected boolean esEditableEnFormalizacion;
    protected boolean requiereValidarCOFI;
    protected boolean sePuedeDispensar;
    protected boolean seRegistraEnFlexCube;
    protected boolean esPlantilla;
    protected long idComisionPrecarga;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected boolean estado;
    @XmlElement(required = true)
    protected String codigoExterno;

    /**
     * Obtiene el valor de la propiedad idCatComision.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCatComision() {
        return idCatComision;
    }

    /**
     * Define el valor de la propiedad idCatComision.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCatComision(Long value) {
        this.idCatComision = value;
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
     * Obtiene el valor de la propiedad idTipoComision.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getIdTipoComision() {
        return idTipoComision;
    }

    /**
     * Define el valor de la propiedad idTipoComision.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setIdTipoComision(Catalogo value) {
        this.idTipoComision = value;
    }

    /**
     * Obtiene el valor de la propiedad esEditableEnFormalizacion.
     * 
     */
    public boolean isEsEditableEnFormalizacion() {
        return esEditableEnFormalizacion;
    }

    /**
     * Define el valor de la propiedad esEditableEnFormalizacion.
     * 
     */
    public void setEsEditableEnFormalizacion(boolean value) {
        this.esEditableEnFormalizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereValidarCOFI.
     * 
     */
    public boolean isRequiereValidarCOFI() {
        return requiereValidarCOFI;
    }

    /**
     * Define el valor de la propiedad requiereValidarCOFI.
     * 
     */
    public void setRequiereValidarCOFI(boolean value) {
        this.requiereValidarCOFI = value;
    }

    /**
     * Obtiene el valor de la propiedad sePuedeDispensar.
     * 
     */
    public boolean isSePuedeDispensar() {
        return sePuedeDispensar;
    }

    /**
     * Define el valor de la propiedad sePuedeDispensar.
     * 
     */
    public void setSePuedeDispensar(boolean value) {
        this.sePuedeDispensar = value;
    }

    /**
     * Obtiene el valor de la propiedad seRegistraEnFlexCube.
     * 
     */
    public boolean isSeRegistraEnFlexCube() {
        return seRegistraEnFlexCube;
    }

    /**
     * Define el valor de la propiedad seRegistraEnFlexCube.
     * 
     */
    public void setSeRegistraEnFlexCube(boolean value) {
        this.seRegistraEnFlexCube = value;
    }

    /**
     * Obtiene el valor de la propiedad esPlantilla.
     * 
     */
    public boolean isEsPlantilla() {
        return esPlantilla;
    }

    /**
     * Define el valor de la propiedad esPlantilla.
     * 
     */
    public void setEsPlantilla(boolean value) {
        this.esPlantilla = value;
    }

    /**
     * Obtiene el valor de la propiedad idComisionPrecarga.
     * 
     */
    public long getIdComisionPrecarga() {
        return idComisionPrecarga;
    }

    /**
     * Define el valor de la propiedad idComisionPrecarga.
     * 
     */
    public void setIdComisionPrecarga(long value) {
        this.idComisionPrecarga = value;
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
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     */
    public void setEstado(boolean value) {
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

}
