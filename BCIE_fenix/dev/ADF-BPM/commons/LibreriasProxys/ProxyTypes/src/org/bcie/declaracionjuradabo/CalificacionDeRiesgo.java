
package org.bcie.declaracionjuradabo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CalificacionDeRiesgo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CalificacionDeRiesgo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoCalificacionDeRiesgo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nombreCalificacionDeRiesgo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalificacionDeRiesgo", propOrder = {
    "codigoCalificacionDeRiesgo",
    "nombreCalificacionDeRiesgo"
})
public class CalificacionDeRiesgo {

    protected Integer codigoCalificacionDeRiesgo;
    protected String nombreCalificacionDeRiesgo;

    /**
     * Obtiene el valor de la propiedad codigoCalificacionDeRiesgo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoCalificacionDeRiesgo() {
        return codigoCalificacionDeRiesgo;
    }

    /**
     * Define el valor de la propiedad codigoCalificacionDeRiesgo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoCalificacionDeRiesgo(Integer value) {
        this.codigoCalificacionDeRiesgo = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCalificacionDeRiesgo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCalificacionDeRiesgo() {
        return nombreCalificacionDeRiesgo;
    }

    /**
     * Define el valor de la propiedad nombreCalificacionDeRiesgo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCalificacionDeRiesgo(String value) {
        this.nombreCalificacionDeRiesgo = value;
    }

}
