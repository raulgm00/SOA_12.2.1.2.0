
package org.bcie.implementacionpctbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para ImplementacionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ImplementacionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/ImplementacionPctBO}LoteType">
 *       &lt;sequence>
 *         &lt;element name="tipoProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaPublicacion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaLimiteRecepcion" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="requiereLotes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fechaOrdenInicio" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="loteImplementacion" type="{http://www.bcie.org/ImplementacionPctBO}LoteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImplementacionType", propOrder = {
    "tipoProceso",
    "observacion",
    "fechaPublicacion",
    "fechaLimiteRecepcion",
    "requiereLotes",
    "fechaOrdenInicio",
    "fechaRegistro",
    "loteImplementacion"
})
public class ImplementacionType
    extends LoteType
{

    protected String tipoProceso;
    protected String observacion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaPublicacion;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaLimiteRecepcion;
    protected Boolean requiereLotes;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaOrdenInicio;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected List<LoteType> loteImplementacion;

    /**
     * Obtiene el valor de la propiedad tipoProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoProceso() {
        return tipoProceso;
    }

    /**
     * Define el valor de la propiedad tipoProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoProceso(String value) {
        this.tipoProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad observacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Define el valor de la propiedad observacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacion(String value) {
        this.observacion = value;
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

    /**
     * Obtiene el valor de la propiedad fechaLimiteRecepcion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaLimiteRecepcion() {
        return fechaLimiteRecepcion;
    }

    /**
     * Define el valor de la propiedad fechaLimiteRecepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaLimiteRecepcion(XMLGregorianCalendar value) {
        this.fechaLimiteRecepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereLotes.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequiereLotes() {
        return requiereLotes;
    }

    /**
     * Define el valor de la propiedad requiereLotes.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequiereLotes(Boolean value) {
        this.requiereLotes = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaOrdenInicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaOrdenInicio() {
        return fechaOrdenInicio;
    }

    /**
     * Define el valor de la propiedad fechaOrdenInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaOrdenInicio(XMLGregorianCalendar value) {
        this.fechaOrdenInicio = value;
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
     * Gets the value of the loteImplementacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loteImplementacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoteImplementacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoteType }
     * 
     * 
     */
    public List<LoteType> getLoteImplementacion() {
        if (loteImplementacion == null) {
            loteImplementacion = new ArrayList<LoteType>();
        }
        return this.loteImplementacion;
    }

}
