
package org.bcie.reglatareabo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TareaReglas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TareaReglas">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/ReglaTareaBO}TareaBasicType">
 *       &lt;sequence>
 *         &lt;element name="ReglasEvaluacion" type="{http://www.bcie.org/ReglaTareaBO}ReglaEvaluacionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TareaReglas", propOrder = {
    "reglasEvaluacion"
})
public class TareaReglas
    extends TareaBasicType
{

    @XmlElement(name = "ReglasEvaluacion")
    protected List<ReglaEvaluacionType> reglasEvaluacion;

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

}
