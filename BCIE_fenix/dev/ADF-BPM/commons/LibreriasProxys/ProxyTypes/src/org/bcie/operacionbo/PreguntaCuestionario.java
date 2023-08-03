
package org.bcie.operacionbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para PreguntaCuestionario complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PreguntaCuestionario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPregunta" type="{http://www.w3.org/2001/XMLSchema}ID" minOccurs="0"/>
 *         &lt;element name="Pregunta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Responsable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreguntaCuestionario", propOrder = {
    "idPregunta",
    "pregunta",
    "responsable"
})
public class PreguntaCuestionario {

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idPregunta;
    @XmlElement(name = "Pregunta")
    protected String pregunta;
    @XmlElement(name = "Responsable")
    protected String responsable;

    /**
     * Obtiene el valor de la propiedad idPregunta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPregunta() {
        return idPregunta;
    }

    /**
     * Define el valor de la propiedad idPregunta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPregunta(String value) {
        this.idPregunta = value;
    }

    /**
     * Obtiene el valor de la propiedad pregunta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Define el valor de la propiedad pregunta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPregunta(String value) {
        this.pregunta = value;
    }

    /**
     * Obtiene el valor de la propiedad responsable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * Define el valor de la propiedad responsable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsable(String value) {
        this.responsable = value;
    }

}
