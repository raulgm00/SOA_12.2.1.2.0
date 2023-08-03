
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EliminarReservaFondosMFCRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EliminarReservaFondosMFCRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroReserva" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EliminarReservaFondosMFCRequestType", propOrder = {
    "numeroReserva"
})
public class EliminarReservaFondosMFCRequestType {

    @XmlElement(name = "NumeroReserva")
    protected int numeroReserva;

    /**
     * Obtiene el valor de la propiedad numeroReserva.
     * 
     */
    public int getNumeroReserva() {
        return numeroReserva;
    }

    /**
     * Define el valor de la propiedad numeroReserva.
     * 
     */
    public void setNumeroReserva(int value) {
        this.numeroReserva = value;
    }

}
