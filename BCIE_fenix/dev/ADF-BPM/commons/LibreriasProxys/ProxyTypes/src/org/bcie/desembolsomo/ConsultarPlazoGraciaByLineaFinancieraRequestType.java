
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.ContratoDesembolso;


/**
 * <p>Clase Java para ConsultarPlazoGraciaByLineaFinancieraRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarPlazoGraciaByLineaFinancieraRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarPlazoGraciaByLineaFinancieraRequestType", propOrder = {
    "contratoDesembolso"
})
public class ConsultarPlazoGraciaByLineaFinancieraRequestType {

    @XmlElement(name = "ContratoDesembolso", required = true)
    protected ContratoDesembolso contratoDesembolso;

    /**
     * Obtiene el valor de la propiedad contratoDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link ContratoDesembolso }
     *     
     */
    public ContratoDesembolso getContratoDesembolso() {
        return contratoDesembolso;
    }

    /**
     * Define el valor de la propiedad contratoDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link ContratoDesembolso }
     *     
     */
    public void setContratoDesembolso(ContratoDesembolso value) {
        this.contratoDesembolso = value;
    }

}
