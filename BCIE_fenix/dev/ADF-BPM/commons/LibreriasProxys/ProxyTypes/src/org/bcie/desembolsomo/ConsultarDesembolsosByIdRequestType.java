
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarDesembolsosByIdRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarDesembolsosByIdRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarDesembolsosByIdRequestType", propOrder = {
    "idContratoDesembolso"
})
public class ConsultarDesembolsosByIdRequestType {

    @XmlElement(type = Long.class)
    protected List<Long> idContratoDesembolso;

    /**
     * Gets the value of the idContratoDesembolso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idContratoDesembolso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdContratoDesembolso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getIdContratoDesembolso() {
        if (idContratoDesembolso == null) {
            idContratoDesembolso = new ArrayList<Long>();
        }
        return this.idContratoDesembolso;
    }

}
