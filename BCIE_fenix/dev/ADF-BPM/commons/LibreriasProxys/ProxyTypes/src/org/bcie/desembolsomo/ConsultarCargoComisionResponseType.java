
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.ContratoDesembolso;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConsultarCargoComisionResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarCargoComisionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Desembolso" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso"/>
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
@XmlType(name = "ConsultarCargoComisionResponseType", propOrder = {
    "desembolso",
    "resultado"
})
public class ConsultarCargoComisionResponseType {

    @XmlElement(name = "Desembolso", required = true)
    protected ContratoDesembolso desembolso;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

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
