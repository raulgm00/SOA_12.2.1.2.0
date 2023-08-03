
package org.bcie.productobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Responsable de una actividad específica
 * 
 * <p>Clase Java para Responsable complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Responsable">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idResponsable" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreResponsable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Responsable", propOrder = {
    "idResponsable",
    "nombreResponsable"
})
public class Responsable {

    protected int idResponsable;
    @XmlElement(required = true)
    protected String nombreResponsable;

    /**
     * Obtiene el valor de la propiedad idResponsable.
     * 
     */
    public int getIdResponsable() {
        return idResponsable;
    }

    /**
     * Define el valor de la propiedad idResponsable.
     * 
     */
    public void setIdResponsable(int value) {
        this.idResponsable = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreResponsable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreResponsable() {
        return nombreResponsable;
    }

    /**
     * Define el valor de la propiedad nombreResponsable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreResponsable(String value) {
        this.nombreResponsable = value;
    }

}
