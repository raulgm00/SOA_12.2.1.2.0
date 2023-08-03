
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.Factibilidad;


/**
 * <p>Clase Java para AplicarFactibilidadRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AplicarFactibilidadRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="Factibilidad" type="{http://www.bcie.org/OperacionBO}Factibilidad"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AplicarFactibilidadRequestType", propOrder = {
    "idDesembolso",
    "factibilidad"
})
public class AplicarFactibilidadRequestType {

    protected long idDesembolso;
    @XmlElement(name = "Factibilidad", required = true)
    protected Factibilidad factibilidad;

    /**
     * Obtiene el valor de la propiedad idDesembolso.
     * 
     */
    public long getIdDesembolso() {
        return idDesembolso;
    }

    /**
     * Define el valor de la propiedad idDesembolso.
     * 
     */
    public void setIdDesembolso(long value) {
        this.idDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad factibilidad.
     * 
     * @return
     *     possible object is
     *     {@link Factibilidad }
     *     
     */
    public Factibilidad getFactibilidad() {
        return factibilidad;
    }

    /**
     * Define el valor de la propiedad factibilidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Factibilidad }
     *     
     */
    public void setFactibilidad(Factibilidad value) {
        this.factibilidad = value;
    }

}
