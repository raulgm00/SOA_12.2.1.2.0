
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.ExcepcionType;


/**
 * <p>Clase Java para CrearActualizarExcepcionSolicitudRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarExcepcionSolicitudRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Excepcion" type="{http://www.bcie.org/DesembolsoBO}ExcepcionType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearActualizarExcepcionSolicitudRequestType", propOrder = {
    "excepcion"
})
public class CrearActualizarExcepcionSolicitudRequestType {

    @XmlElement(name = "Excepcion", required = true)
    protected List<ExcepcionType> excepcion;

    /**
     * Gets the value of the excepcion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the excepcion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExcepcion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExcepcionType }
     * 
     * 
     */
    public List<ExcepcionType> getExcepcion() {
        if (excepcion == null) {
            excepcion = new ArrayList<ExcepcionType>();
        }
        return this.excepcion;
    }

}
