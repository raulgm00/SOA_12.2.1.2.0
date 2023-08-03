
package org.bcie.clientebo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para reservaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="reservaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="importeReserva" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="tipoReserva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservaType", propOrder = {
    "importeReserva",
    "tipoReserva"
})
public class ReservaType {

    @XmlElement(required = true)
    protected BigDecimal importeReserva;
    @XmlElement(required = true)
    protected String tipoReserva;

    /**
     * Obtiene el valor de la propiedad importeReserva.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImporteReserva() {
        return importeReserva;
    }

    /**
     * Define el valor de la propiedad importeReserva.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImporteReserva(BigDecimal value) {
        this.importeReserva = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoReserva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoReserva() {
        return tipoReserva;
    }

    /**
     * Define el valor de la propiedad tipoReserva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoReserva(String value) {
        this.tipoReserva = value;
    }

}
