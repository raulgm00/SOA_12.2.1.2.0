
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.TransferenciaRecursosType;


/**
 * <p>Clase Java para CrearActualizarTransferenciaRecursosRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarTransferenciaRecursosRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransferenciaRecursos" type="{http://www.bcie.org/DesembolsoBO}TransferenciaRecursosType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearActualizarTransferenciaRecursosRequestType", propOrder = {
    "transferenciaRecursos"
})
public class CrearActualizarTransferenciaRecursosRequestType {

    @XmlElement(name = "TransferenciaRecursos", required = true)
    protected TransferenciaRecursosType transferenciaRecursos;

    /**
     * Obtiene el valor de la propiedad transferenciaRecursos.
     * 
     * @return
     *     possible object is
     *     {@link TransferenciaRecursosType }
     *     
     */
    public TransferenciaRecursosType getTransferenciaRecursos() {
        return transferenciaRecursos;
    }

    /**
     * Define el valor de la propiedad transferenciaRecursos.
     * 
     * @param value
     *     allowed object is
     *     {@link TransferenciaRecursosType }
     *     
     */
    public void setTransferenciaRecursos(TransferenciaRecursosType value) {
        this.transferenciaRecursos = value;
    }

}
