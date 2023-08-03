
package org.bcie.adquisicionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.adquisicionbo.HeaderPublicacionType;


/**
 * <p>Clase Java para AvisoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AvisoType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/AdquisicionBO}HeaderPublicacionType">
 *       &lt;sequence>
 *         &lt;element name="nombreOrganismoEjecutor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="objetivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lugarOnBase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaInicioOnBase" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="fechaFinOnBase" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="direccionElectronica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaRecepcionPropuesta" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="direccionPresentarPropuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaPublicacion" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvisoType", propOrder = {
    "nombreOrganismoEjecutor",
    "objetivo",
    "lugarOnBase",
    "fechaInicioOnBase",
    "fechaFinOnBase",
    "direccionElectronica",
    "fechaRecepcionPropuesta",
    "direccionPresentarPropuesta",
    "fechaPublicacion"
})
public class AvisoType
    extends HeaderPublicacionType
{

    @XmlElement(required = true)
    protected String nombreOrganismoEjecutor;
    @XmlElement(required = true)
    protected String objetivo;
    @XmlElement(required = true)
    protected String lugarOnBase;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicioOnBase;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFinOnBase;
    @XmlElement(required = true)
    protected String direccionElectronica;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRecepcionPropuesta;
    @XmlElement(required = true)
    protected String direccionPresentarPropuesta;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaPublicacion;

    /**
     * Obtiene el valor de la propiedad nombreOrganismoEjecutor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOrganismoEjecutor() {
        return nombreOrganismoEjecutor;
    }

    /**
     * Define el valor de la propiedad nombreOrganismoEjecutor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOrganismoEjecutor(String value) {
        this.nombreOrganismoEjecutor = value;
    }

    /**
     * Obtiene el valor de la propiedad objetivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * Define el valor de la propiedad objetivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjetivo(String value) {
        this.objetivo = value;
    }

    /**
     * Obtiene el valor de la propiedad lugarOnBase.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarOnBase() {
        return lugarOnBase;
    }

    /**
     * Define el valor de la propiedad lugarOnBase.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarOnBase(String value) {
        this.lugarOnBase = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicioOnBase.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioOnBase() {
        return fechaInicioOnBase;
    }

    /**
     * Define el valor de la propiedad fechaInicioOnBase.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioOnBase(XMLGregorianCalendar value) {
        this.fechaInicioOnBase = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFinOnBase.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinOnBase() {
        return fechaFinOnBase;
    }

    /**
     * Define el valor de la propiedad fechaFinOnBase.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinOnBase(XMLGregorianCalendar value) {
        this.fechaFinOnBase = value;
    }

    /**
     * Obtiene el valor de la propiedad direccionElectronica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionElectronica() {
        return direccionElectronica;
    }

    /**
     * Define el valor de la propiedad direccionElectronica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionElectronica(String value) {
        this.direccionElectronica = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRecepcionPropuesta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRecepcionPropuesta() {
        return fechaRecepcionPropuesta;
    }

    /**
     * Define el valor de la propiedad fechaRecepcionPropuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRecepcionPropuesta(XMLGregorianCalendar value) {
        this.fechaRecepcionPropuesta = value;
    }

    /**
     * Obtiene el valor de la propiedad direccionPresentarPropuesta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionPresentarPropuesta() {
        return direccionPresentarPropuesta;
    }

    /**
     * Define el valor de la propiedad direccionPresentarPropuesta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionPresentarPropuesta(String value) {
        this.direccionPresentarPropuesta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPublicacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Define el valor de la propiedad fechaPublicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPublicacion(XMLGregorianCalendar value) {
        this.fechaPublicacion = value;
    }

}
