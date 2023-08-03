
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.SaldoCarteraType;


/**
 * <p>Clase Java para ConsultarSaldoCarteraRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarSaldoCarteraRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SaldoCartera" type="{http://www.bcie.org/DesembolsoBO}SaldoCarteraType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarSaldoCarteraRequestType", propOrder = {
    "saldoCartera"
})
public class ConsultarSaldoCarteraRequestType {

    @XmlElement(name = "SaldoCartera", required = true)
    protected SaldoCarteraType saldoCartera;

    /**
     * Obtiene el valor de la propiedad saldoCartera.
     * 
     * @return
     *     possible object is
     *     {@link SaldoCarteraType }
     *     
     */
    public SaldoCarteraType getSaldoCartera() {
        return saldoCartera;
    }

    /**
     * Define el valor de la propiedad saldoCartera.
     * 
     * @param value
     *     allowed object is
     *     {@link SaldoCarteraType }
     *     
     */
    public void setSaldoCartera(SaldoCarteraType value) {
        this.saldoCartera = value;
    }

}
