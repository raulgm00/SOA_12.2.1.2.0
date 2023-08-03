
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.ContratoDesembolso;


/**
 * <p>Clase Java para ConsultarCargoComisionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarCargoComisionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Desembolso" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarCargoComisionRequestType", propOrder = {
    "desembolso"
})
public class ConsultarCargoComisionRequestType {

    @XmlElement(name = "Desembolso", required = true)
    protected ContratoDesembolso desembolso;

    /**
     * Obtiene el valor de la propiedad desembolso.
     * 
     * @return
     *     possible object is
     *     {@link ContratoDesembolso }
     *     
     */
    public ContratoDesembolso getDesembolso() {
        return desembolso;
    }

    /**
     * Define el valor de la propiedad desembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link ContratoDesembolso }
     *     
     */
    public void setDesembolso(ContratoDesembolso value) {
        this.desembolso = value;
    }

}
