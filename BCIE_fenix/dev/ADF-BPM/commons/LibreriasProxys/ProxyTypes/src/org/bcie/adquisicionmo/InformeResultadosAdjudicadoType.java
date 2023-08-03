
package org.bcie.adquisicionmo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.adquisicionbo.AdjudicadoType;
import org.bcie.adquisicionbo.HeaderPublicacionType;


/**
 * <p>Clase Java para InformeResultadosAdjudicadoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InformeResultadosAdjudicadoType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/AdquisicionBO}HeaderPublicacionType">
 *       &lt;sequence>
 *         &lt;element name="resultadoProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adjudicados" type="{http://www.bcie.org/AdquisicionBO}AdjudicadoType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformeResultadosAdjudicadoType", propOrder = {
    "resultadoProceso",
    "adjudicados"
})
public class InformeResultadosAdjudicadoType
    extends HeaderPublicacionType
{

    @XmlElement(required = true)
    protected String resultadoProceso;
    @XmlElement(required = true)
    protected List<AdjudicadoType> adjudicados;

    /**
     * Obtiene el valor de la propiedad resultadoProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultadoProceso() {
        return resultadoProceso;
    }

    /**
     * Define el valor de la propiedad resultadoProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultadoProceso(String value) {
        this.resultadoProceso = value;
    }

    /**
     * Gets the value of the adjudicados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adjudicados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdjudicados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdjudicadoType }
     * 
     * 
     */
    public List<AdjudicadoType> getAdjudicados() {
        if (adjudicados == null) {
            adjudicados = new ArrayList<AdjudicadoType>();
        }
        return this.adjudicados;
    }

}
