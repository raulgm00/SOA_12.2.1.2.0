
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.ContratoDesembolso;


/**
 * <p>Clase Java para EnvioCobroBPELRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EnvioCobroBPELRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Desembolsos" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvioCobroBPELRequestType", propOrder = {
    "desembolsos"
})
public class EnvioCobroBPELRequestType {

    @XmlElement(name = "Desembolsos", required = true)
    protected List<ContratoDesembolso> desembolsos;

    /**
     * Gets the value of the desembolsos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the desembolsos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDesembolsos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContratoDesembolso }
     * 
     * 
     */
    public List<ContratoDesembolso> getDesembolsos() {
        if (desembolsos == null) {
            desembolsos = new ArrayList<ContratoDesembolso>();
        }
        return this.desembolsos;
    }

}
