
package org.bcie.operacionbo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para AccionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AccionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdAccion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IdCliente" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IdOperacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Accion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdEstadoAccion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TcaCategoriaAccion" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="TcaRolAsignado" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="FechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FechaAtencion" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="BanEstatus" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccionType", propOrder = {
    "idAccion",
    "idCliente",
    "idOperacion",
    "accion",
    "idEstadoAccion",
    "tcaCategoriaAccion",
    "tcaRolAsignado",
    "fechaRegistro",
    "fechaAtencion",
    "banEstatus"
})
public class AccionType {

    @XmlElement(name = "IdAccion", required = true)
    protected BigDecimal idAccion;
    @XmlElement(name = "IdCliente", required = true)
    protected BigDecimal idCliente;
    @XmlElement(name = "IdOperacion", required = true)
    protected BigDecimal idOperacion;
    @XmlElement(name = "Accion", required = true)
    protected String accion;
    @XmlElement(name = "IdEstadoAccion", required = true)
    protected BigDecimal idEstadoAccion;
    @XmlElement(name = "TcaCategoriaAccion", required = true)
    protected Catalogo tcaCategoriaAccion;
    @XmlElement(name = "TcaRolAsignado", required = true)
    protected Catalogo tcaRolAsignado;
    @XmlElement(name = "FechaRegistro", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlElement(name = "FechaAtencion", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAtencion;
    @XmlElement(name = "BanEstatus", required = true)
    protected BigDecimal banEstatus;

    /**
     * Obtiene el valor de la propiedad idAccion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdAccion() {
        return idAccion;
    }

    /**
     * Define el valor de la propiedad idAccion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdAccion(BigDecimal value) {
        this.idAccion = value;
    }

    /**
     * Obtiene el valor de la propiedad idCliente.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdCliente() {
        return idCliente;
    }

    /**
     * Define el valor de la propiedad idCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdCliente(BigDecimal value) {
        this.idCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdOperacion(BigDecimal value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad accion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Define el valor de la propiedad accion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccion(String value) {
        this.accion = value;
    }

    /**
     * Obtiene el valor de la propiedad idEstadoAccion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdEstadoAccion() {
        return idEstadoAccion;
    }

    /**
     * Define el valor de la propiedad idEstadoAccion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdEstadoAccion(BigDecimal value) {
        this.idEstadoAccion = value;
    }

    /**
     * Obtiene el valor de la propiedad tcaCategoriaAccion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTcaCategoriaAccion() {
        return tcaCategoriaAccion;
    }

    /**
     * Define el valor de la propiedad tcaCategoriaAccion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTcaCategoriaAccion(Catalogo value) {
        this.tcaCategoriaAccion = value;
    }

    /**
     * Obtiene el valor de la propiedad tcaRolAsignado.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTcaRolAsignado() {
        return tcaRolAsignado;
    }

    /**
     * Define el valor de la propiedad tcaRolAsignado.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTcaRolAsignado(Catalogo value) {
        this.tcaRolAsignado = value;
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
     * Obtiene el valor de la propiedad fechaAtencion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAtencion() {
        return fechaAtencion;
    }

    /**
     * Define el valor de la propiedad fechaAtencion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAtencion(XMLGregorianCalendar value) {
        this.fechaAtencion = value;
    }

    /**
     * Obtiene el valor de la propiedad banEstatus.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBanEstatus() {
        return banEstatus;
    }

    /**
     * Define el valor de la propiedad banEstatus.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBanEstatus(BigDecimal value) {
        this.banEstatus = value;
    }

}
