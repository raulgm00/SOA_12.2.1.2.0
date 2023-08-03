
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.TransferenciaFT05Type;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para CrearActualizarTransferenciaFT05ResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarTransferenciaFT05ResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransferenciaFT05" type="{http://www.bcie.org/DesembolsoBO}TransferenciaFT05Type"/>
 *         &lt;element name="Resultado" type="{http://www.bcie.org/ResultBO}Resultado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearActualizarTransferenciaFT05ResponseType", propOrder = {
    "transferenciaFT05",
    "resultado"
})
public class CrearActualizarTransferenciaFT05ResponseType {

    @XmlElement(name = "TransferenciaFT05", required = true)
    protected TransferenciaFT05Type transferenciaFT05;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

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

    /**
     * Obtiene el valor de la propiedad resultado.
     * 
     * @return
     *     possible object is
     *     {@link Resultado }
     *     
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * Define el valor de la propiedad resultado.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultado }
     *     
     */
    public void setResultado(Resultado value) {
        this.resultado = value;
    }

}
