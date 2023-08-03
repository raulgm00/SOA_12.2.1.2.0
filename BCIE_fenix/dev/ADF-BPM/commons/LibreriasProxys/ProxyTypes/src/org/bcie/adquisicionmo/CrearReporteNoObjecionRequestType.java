
package org.bcie.adquisicionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.adquisicionbo.AdquisicionType;


/**
 * <p>Clase Java para CrearReporteNoObjecionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearReporteNoObjecionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Adquisicion" type="{http://www.bcie.org/AdquisicionBO}AdquisicionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearReporteNoObjecionRequestType", propOrder = {
    "adquisicion"
})
public class CrearReporteNoObjecionRequestType {

    @XmlElement(name = "Adquisicion", required = true)
    protected AdquisicionType adquisicion;

    /**
     * Obtiene el valor de la propiedad adquisicion.
     * 
     * @return
     *     possible object is
     *     {@link AdquisicionType }
     *     
     */
    public AdquisicionType getAdquisicion() {
        return adquisicion;
    }

    /**
     * Define el valor de la propiedad adquisicion.
     * 
     * @param value
     *     allowed object is
     *     {@link AdquisicionType }
     *     
     */
    public void setAdquisicion(AdquisicionType value) {
        this.adquisicion = value;
    }

}
