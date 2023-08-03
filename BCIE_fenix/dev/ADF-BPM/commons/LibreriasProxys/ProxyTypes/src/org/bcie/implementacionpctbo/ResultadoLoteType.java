
package org.bcie.implementacionpctbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ResultadoLoteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResultadoLoteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Lotes" type="{http://www.bcie.org/ImplementacionPctBO}LoteType" minOccurs="0"/>
 *         &lt;element name="ResultadoProceso" type="{http://www.bcie.org/ResultBO}Resultado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoLoteType", propOrder = {
    "lotes",
    "resultadoProceso"
})
public class ResultadoLoteType {

    @XmlElement(name = "Lotes")
    protected LoteType lotes;
    @XmlElement(name = "ResultadoProceso", required = true)
    protected Resultado resultadoProceso;

    /**
     * Obtiene el valor de la propiedad lotes.
     * 
     * @return
     *     possible object is
     *     {@link LoteType }
     *     
     */
    public LoteType getLotes() {
        return lotes;
    }

    /**
     * Define el valor de la propiedad lotes.
     * 
     * @param value
     *     allowed object is
     *     {@link LoteType }
     *     
     */
    public void setLotes(LoteType value) {
        this.lotes = value;
    }

    /**
     * Obtiene el valor de la propiedad resultadoProceso.
     * 
     * @return
     *     possible object is
     *     {@link Resultado }
     *     
     */
    public Resultado getResultadoProceso() {
        return resultadoProceso;
    }

    /**
     * Define el valor de la propiedad resultadoProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultado }
     *     
     */
    public void setResultadoProceso(Resultado value) {
        this.resultadoProceso = value;
    }

}
