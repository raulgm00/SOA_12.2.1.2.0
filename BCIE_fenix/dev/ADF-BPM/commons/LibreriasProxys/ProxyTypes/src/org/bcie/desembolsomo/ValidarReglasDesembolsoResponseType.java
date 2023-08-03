
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.reglatareabo.TareaReglas;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ValidarReglasDesembolsoResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidarReglasDesembolsoResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TareaReglas" type="{http://www.bcie.org/ReglaTareaBO}TareaReglas"/>
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
@XmlType(name = "ValidarReglasDesembolsoResponseType", propOrder = {
    "tareaReglas",
    "resultado"
})
public class ValidarReglasDesembolsoResponseType {

    @XmlElement(name = "TareaReglas", required = true)
    protected TareaReglas tareaReglas;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad tareaReglas.
     * 
     * @return
     *     possible object is
     *     {@link TareaReglas }
     *     
     */
    public TareaReglas getTareaReglas() {
        return tareaReglas;
    }

    /**
     * Define el valor de la propiedad tareaReglas.
     * 
     * @param value
     *     allowed object is
     *     {@link TareaReglas }
     *     
     */
    public void setTareaReglas(TareaReglas value) {
        this.tareaReglas = value;
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
