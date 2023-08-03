
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para EstimadoDesembolsoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EstimadoDesembolsoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Plazo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Frecuencia" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstimadoDesembolsoType", propOrder = {
    "plazo",
    "frecuencia"
})
public class EstimadoDesembolsoType {

    @XmlElement(name = "Plazo")
    protected int plazo;
    @XmlElement(name = "Frecuencia", required = true)
    protected Catalogo frecuencia;

    /**
     * Obtiene el valor de la propiedad plazo.
     * 
     */
    public int getPlazo() {
        return plazo;
    }

    /**
     * Define el valor de la propiedad plazo.
     * 
     */
    public void setPlazo(int value) {
        this.plazo = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuencia.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getFrecuencia() {
        return frecuencia;
    }

    /**
     * Define el valor de la propiedad frecuencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setFrecuencia(Catalogo value) {
        this.frecuencia = value;
    }

}
