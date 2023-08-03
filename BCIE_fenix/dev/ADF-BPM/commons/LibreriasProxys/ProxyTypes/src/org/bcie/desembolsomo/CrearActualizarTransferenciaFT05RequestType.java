
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.TransferenciaFT05Type;


/**
 * <p>Clase Java para CrearActualizarTransferenciaFT05RequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarTransferenciaFT05RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransferenciaFT05" type="{http://www.bcie.org/DesembolsoBO}TransferenciaFT05Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearActualizarTransferenciaFT05RequestType", propOrder = {
    "transferenciaFT05"
})
public class CrearActualizarTransferenciaFT05RequestType {

    @XmlElement(name = "TransferenciaFT05", required = true)
    protected TransferenciaFT05Type transferenciaFT05;

    /**
     * Obtiene el valor de la propiedad transferenciaFT05.
     * 
     * @return
     *     possible object is
     *     {@link TransferenciaFT05Type }
     *     
     */
    public TransferenciaFT05Type getTransferenciaFT05() {
        return transferenciaFT05;
    }

    /**
     * Define el valor de la propiedad transferenciaFT05.
     * 
     * @param value
     *     allowed object is
     *     {@link TransferenciaFT05Type }
     *     
     */
    public void setTransferenciaFT05(TransferenciaFT05Type value) {
        this.transferenciaFT05 = value;
    }

}
