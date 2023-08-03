
package org.bcie.adquisicionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.adquisicionbo.NoObjecionType;


/**
 * <p>Clase Java para CrearActualizarNoObjecionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarNoObjecionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NoObjecion" type="{http://www.bcie.org/AdquisicionBO}NoObjecionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearActualizarNoObjecionRequestType", propOrder = {
    "noObjecion"
})
public class CrearActualizarNoObjecionRequestType {

    @XmlElement(name = "NoObjecion", required = true)
    protected NoObjecionType noObjecion;

    /**
     * Obtiene el valor de la propiedad noObjecion.
     * 
     * @return
     *     possible object is
     *     {@link NoObjecionType }
     *     
     */
    public NoObjecionType getNoObjecion() {
        return noObjecion;
    }

    /**
     * Define el valor de la propiedad noObjecion.
     * 
     * @param value
     *     allowed object is
     *     {@link NoObjecionType }
     *     
     */
    public void setNoObjecion(NoObjecionType value) {
        this.noObjecion = value;
    }

}
