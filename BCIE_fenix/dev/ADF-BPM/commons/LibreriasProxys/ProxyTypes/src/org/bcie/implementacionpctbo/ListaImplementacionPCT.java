
package org.bcie.implementacionpctbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ListaImplementacionPCT complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ListaImplementacionPCT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loteImplementacion" type="{http://www.bcie.org/ImplementacionPctBO}LoteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListaImplementacionPCT", propOrder = {
    "loteImplementacion"
})
public class ListaImplementacionPCT {

    protected List<LoteType> loteImplementacion;

    /**
     * Gets the value of the loteImplementacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loteImplementacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoteImplementacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoteType }
     * 
     * 
     */
    public List<LoteType> getLoteImplementacion() {
        if (loteImplementacion == null) {
            loteImplementacion = new ArrayList<LoteType>();
        }
        return this.loteImplementacion;
    }

}
