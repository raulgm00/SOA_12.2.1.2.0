
package org.bcie.declaracionjuradabo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EstadoDeclaracion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EstadoDeclaracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoEstadoDeclaracion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nombreEstadoDeclaracion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstadoNoObjecion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nombreEstadoNoObjecion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstadoDeclaracion", propOrder = {
    "codigoEstadoDeclaracion",
    "nombreEstadoDeclaracion",
    "estadoNoObjecion",
    "nombreEstadoNoObjecion"
})
public class EstadoDeclaracion {

    protected Integer codigoEstadoDeclaracion;
    protected String nombreEstadoDeclaracion;
    @XmlElement(name = "EstadoNoObjecion")
    protected Boolean estadoNoObjecion;
    protected String nombreEstadoNoObjecion;

    /**
     * Obtiene el valor de la propiedad codigoEstadoDeclaracion.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoEstadoDeclaracion() {
        return codigoEstadoDeclaracion;
    }

    /**
     * Define el valor de la propiedad codigoEstadoDeclaracion.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoEstadoDeclaracion(Integer value) {
        this.codigoEstadoDeclaracion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEstadoDeclaracion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEstadoDeclaracion() {
        return nombreEstadoDeclaracion;
    }

    /**
     * Define el valor de la propiedad nombreEstadoDeclaracion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEstadoDeclaracion(String value) {
        this.nombreEstadoDeclaracion = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoNoObjecion.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstadoNoObjecion() {
        return estadoNoObjecion;
    }

    /**
     * Define el valor de la propiedad estadoNoObjecion.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstadoNoObjecion(Boolean value) {
        this.estadoNoObjecion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEstadoNoObjecion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEstadoNoObjecion() {
        return nombreEstadoNoObjecion;
    }

    /**
     * Define el valor de la propiedad nombreEstadoNoObjecion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEstadoNoObjecion(String value) {
        this.nombreEstadoNoObjecion = value;
    }

}
