
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.condicionbo.Condicion;


/**
 * <p>Clase Java para ConsultarCumplimientoCondicionesRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarCumplimientoCondicionesRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Condicion" type="{http://www.bcie.org/CondicionBO}Condicion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarCumplimientoCondicionesRequestType", propOrder = {
    "condicion"
})
public class ConsultarCumplimientoCondicionesRequestType {

    @XmlElement(name = "Condicion")
    protected List<Condicion> condicion;

    /**
     * Gets the value of the condicion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the condicion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCondicion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Condicion }
     * 
     * 
     */
    public List<Condicion> getCondicion() {
        if (condicion == null) {
            condicion = new ArrayList<Condicion>();
        }
        return this.condicion;
    }

}
