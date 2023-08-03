
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.TransaccionType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para CrearActualizarDetalleTransaccionResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarDetalleTransaccionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransaccionDesembolso" type="{http://www.bcie.org/DesembolsoBO}TransaccionType"/>
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
@XmlType(name = "CrearActualizarDetalleTransaccionResponseType", propOrder = {
    "transaccionDesembolso",
    "resultado"
})
public class CrearActualizarDetalleTransaccionResponseType {

    @XmlElement(name = "TransaccionDesembolso", required = true)
    protected TransaccionType transaccionDesembolso;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

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
