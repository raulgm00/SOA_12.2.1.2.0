
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.commonbo.MontoType;
import org.bcie.operacionbo.Operacion;


/**
 * <p>Clase Java para ValidarLimitesLIMRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidarLimitesLIMRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *         &lt;element name="Monto" type="{http://www.bcie.org/CommonBO}MontoType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarLimitesLIMRequestType", propOrder = {
    "operacion",
    "monto"
})
public class ValidarLimitesLIMRequestType {

    @XmlElement(name = "Operacion", required = true)
    protected Operacion operacion;
    @XmlElement(name = "Monto", required = true)
    protected List<MontoType> monto;

    /**
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link Operacion }
     *     
     */
    public Operacion getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Operacion }
     *     
     */
    public void setOperacion(Operacion value) {
        this.operacion = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the monto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMonto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MontoType }
     * 
     * 
     */
    public List<MontoType> getMonto() {
        if (monto == null) {
            monto = new ArrayList<MontoType>();
        }
        return this.monto;
    }

}
