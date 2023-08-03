
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.Transferencia;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para PropagarTransferenciaResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PropagarTransferenciaResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Transferencia" type="{http://www.bcie.org/DesembolsoBO}Transferencia"/>
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
@XmlType(name = "PropagarTransferenciaResponseType", propOrder = {
    "transferencia",
    "resultado"
})
public class PropagarTransferenciaResponseType {

    @XmlElement(name = "Transferencia", required = true)
    protected Transferencia transferencia;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad transferencia.
     * 
     * @return
     *     possible object is
     *     {@link Transferencia }
     *     
     */
    public Transferencia getTransferencia() {
        return transferencia;
    }

    /**
     * Define el valor de la propiedad transferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Transferencia }
     *     
     */
    public void setTransferencia(Transferencia value) {
        this.transferencia = value;
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
