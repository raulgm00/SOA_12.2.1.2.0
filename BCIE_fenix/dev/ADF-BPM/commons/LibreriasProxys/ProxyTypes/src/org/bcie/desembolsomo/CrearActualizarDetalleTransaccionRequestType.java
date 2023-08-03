
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.TransaccionType;


/**
 * <p>Clase Java para CrearActualizarDetalleTransaccionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarDetalleTransaccionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransaccionDesembolso" type="{http://www.bcie.org/DesembolsoBO}TransaccionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearActualizarDetalleTransaccionRequestType", propOrder = {
    "transaccionDesembolso"
})
public class CrearActualizarDetalleTransaccionRequestType {

    @XmlElement(name = "TransaccionDesembolso", required = true)
    protected TransaccionType transaccionDesembolso;

    /**
     * Obtiene el valor de la propiedad transaccionDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link TransaccionType }
     *     
     */
    public TransaccionType getTransaccionDesembolso() {
        return transaccionDesembolso;
    }

    /**
     * Define el valor de la propiedad transaccionDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link TransaccionType }
     *     
     */
    public void setTransaccionDesembolso(TransaccionType value) {
        this.transaccionDesembolso = value;
    }

}
