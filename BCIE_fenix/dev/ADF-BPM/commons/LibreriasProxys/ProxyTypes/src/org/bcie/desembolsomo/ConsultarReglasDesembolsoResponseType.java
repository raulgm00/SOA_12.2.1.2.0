
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.reglatareabo.ReglaEvaluacionType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConsultarReglasDesembolsoResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarReglasDesembolsoResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReglasEvaluacion" type="{http://www.bcie.org/ReglaTareaBO}ReglaEvaluacionType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "ConsultarReglasDesembolsoResponseType", propOrder = {
    "reglasEvaluacion",
    "resultado"
})
public class ConsultarReglasDesembolsoResponseType {

    @XmlElement(name = "ReglasEvaluacion")
    protected List<ReglaEvaluacionType> reglasEvaluacion;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Gets the value of the reglasEvaluacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reglasEvaluacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReglasEvaluacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReglaEvaluacionType }
     * 
     * 
     */
    public List<ReglaEvaluacionType> getReglasEvaluacion() {
        if (reglasEvaluacion == null) {
            reglasEvaluacion = new ArrayList<ReglaEvaluacionType>();
        }
        return this.reglasEvaluacion;
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
