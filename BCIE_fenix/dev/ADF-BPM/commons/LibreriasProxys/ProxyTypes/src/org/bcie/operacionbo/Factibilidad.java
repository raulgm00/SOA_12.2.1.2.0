
package org.bcie.operacionbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.desembolsobo.TasaType;


/**
 * <p>Clase Java para Factibilidad complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Factibilidad">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/DesembolsoBO}tasaType">
 *       &lt;sequence>
 *         &lt;element name="esFactible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fechaFinalizacionEstudios" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="fechaCalculoInteres" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Factibilidad", propOrder = {
    "esFactible",
    "fechaFinalizacionEstudios",
    "fechaCalculoInteres"
})
public class Factibilidad
    extends TasaType
{

    protected Boolean esFactible;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFinalizacionEstudios;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaCalculoInteres;

    /**
     * Obtiene el valor de la propiedad esFactible.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsFactible() {
        return esFactible;
    }

    /**
     * Define el valor de la propiedad esFactible.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsFactible(Boolean value) {
        this.esFactible = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFinalizacionEstudios.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinalizacionEstudios() {
        return fechaFinalizacionEstudios;
    }

    /**
     * Define el valor de la propiedad fechaFinalizacionEstudios.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinalizacionEstudios(XMLGregorianCalendar value) {
        this.fechaFinalizacionEstudios = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaCalculoInteres.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCalculoInteres() {
        return fechaCalculoInteres;
    }

    /**
     * Define el valor de la propiedad fechaCalculoInteres.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCalculoInteres(XMLGregorianCalendar value) {
        this.fechaCalculoInteres = value;
    }

}
