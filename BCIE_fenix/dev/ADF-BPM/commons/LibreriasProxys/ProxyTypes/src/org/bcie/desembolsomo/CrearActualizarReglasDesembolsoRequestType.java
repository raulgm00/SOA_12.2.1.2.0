
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.reglabo.ReglaBasicType;


/**
 * <p>Clase Java para CrearActualizarReglasDesembolsoRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarReglasDesembolsoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="ReglasDesembolso" type="{http://www.bcie.org/ReglaBO}ReglaBasicType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearActualizarReglasDesembolsoRequestType", propOrder = {
    "idDesembolso",
    "reglasDesembolso"
})
public class CrearActualizarReglasDesembolsoRequestType {

    protected long idDesembolso;
    @XmlElement(name = "ReglasDesembolso")
    protected List<ReglaBasicType> reglasDesembolso;

    /**
     * Obtiene el valor de la propiedad idDesembolso.
     * 
     */
    public long getIdDesembolso() {
        return idDesembolso;
    }

    /**
     * Define el valor de la propiedad idDesembolso.
     * 
     */
    public void setIdDesembolso(long value) {
        this.idDesembolso = value;
    }

    /**
     * Gets the value of the reglasDesembolso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reglasDesembolso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReglasDesembolso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReglaBasicType }
     * 
     * 
     */
    public List<ReglaBasicType> getReglasDesembolso() {
        if (reglasDesembolso == null) {
            reglasDesembolso = new ArrayList<ReglaBasicType>();
        }
        return this.reglasDesembolso;
    }

}
