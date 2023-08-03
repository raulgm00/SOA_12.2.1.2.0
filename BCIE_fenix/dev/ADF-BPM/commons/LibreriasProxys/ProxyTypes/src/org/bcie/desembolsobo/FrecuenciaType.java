
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.commonbo.PlazoType;


/**
 * <p>Clase Java para FrecuenciaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FrecuenciaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FechaInicio" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Frecuencia" type="{http://www.bcie.org/CommonBO}PlazoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FrecuenciaType", propOrder = {
    "fechaInicio",
    "frecuencia"
})
@XmlSeeAlso({
    InteresType.class,
    org.bcie.desembolsobo.CondicionesFinancieras.Principal.class,
    org.bcie.desembolsobo.CondicionesFinancieras.Interes.class,
    org.bcie.desembolsobo.VariableType.Revision.class,
    org.bcie.desembolsobo.SpreadType.Revision.class
})
public class FrecuenciaType {

    @XmlElement(name = "FechaInicio", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicio;
    @XmlElement(name = "Frecuencia", required = true)
    protected PlazoType frecuencia;

    /**
     * Obtiene el valor de la propiedad fechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Define el valor de la propiedad fechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        this.fechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuencia.
     * 
     * @return
     *     possible object is
     *     {@link PlazoType }
     *     
     */
    public PlazoType getFrecuencia() {
        return frecuencia;
    }

    /**
     * Define el valor de la propiedad frecuencia.
     * 
     * @param value
     *     allowed object is
     *     {@link PlazoType }
     *     
     */
    public void setFrecuencia(PlazoType value) {
        this.frecuencia = value;
    }

}
