
package org.bcie.declaracionjuradabo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EstadoBusqueda complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EstadoBusqueda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoEstadoBusqueda" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nombreEstadoBusqueda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstadoBusqueda", propOrder = {
    "codigoEstadoBusqueda",
    "nombreEstadoBusqueda"
})
public class EstadoBusqueda {

    protected Integer codigoEstadoBusqueda;
    protected String nombreEstadoBusqueda;

    /**
     * Obtiene el valor de la propiedad codigoEstadoBusqueda.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoEstadoBusqueda() {
        return codigoEstadoBusqueda;
    }

    /**
     * Define el valor de la propiedad codigoEstadoBusqueda.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoEstadoBusqueda(Integer value) {
        this.codigoEstadoBusqueda = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEstadoBusqueda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEstadoBusqueda() {
        return nombreEstadoBusqueda;
    }

    /**
     * Define el valor de la propiedad nombreEstadoBusqueda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEstadoBusqueda(String value) {
        this.nombreEstadoBusqueda = value;
    }

}
