
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.LimiteGlobalLineaFinanciera;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConsultarLimiteByLineaFinancieraResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarLimiteByLineaFinancieraResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LimiteGlobalLineaFinanciera" type="{http://www.bcie.org/DesembolsoBO}LimiteGlobalLineaFinanciera" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ConsultarLimiteByLineaFinancieraResponseType", propOrder = {
    "limiteGlobalLineaFinanciera",
    "resultado"
})
public class ConsultarLimiteByLineaFinancieraResponseType {

    @XmlElement(name = "LimiteGlobalLineaFinanciera")
    protected List<LimiteGlobalLineaFinanciera> limiteGlobalLineaFinanciera;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Gets the value of the limiteGlobalLineaFinanciera property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the limiteGlobalLineaFinanciera property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLimiteGlobalLineaFinanciera().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LimiteGlobalLineaFinanciera }
     * 
     * 
     */
    public List<LimiteGlobalLineaFinanciera> getLimiteGlobalLineaFinanciera() {
        if (limiteGlobalLineaFinanciera == null) {
            limiteGlobalLineaFinanciera = new ArrayList<LimiteGlobalLineaFinanciera>();
        }
        return this.limiteGlobalLineaFinanciera;
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
