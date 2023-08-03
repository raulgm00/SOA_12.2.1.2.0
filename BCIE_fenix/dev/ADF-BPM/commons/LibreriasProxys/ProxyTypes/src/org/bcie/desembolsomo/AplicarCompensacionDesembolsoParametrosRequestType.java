
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.DetalleTransaccionBasicType;


/**
 * <p>Clase Java para AplicarCompensacionDesembolsoParametrosRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AplicarCompensacionDesembolsoParametrosRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="plataforma" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DetalleTransaccion" type="{http://www.bcie.org/DesembolsoBO}DetalleTransaccionBasicType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AplicarCompensacionDesembolsoParametrosRequestType", propOrder = {
    "idDesembolso",
    "plataforma",
    "detalleTransaccion"
})
public class AplicarCompensacionDesembolsoParametrosRequestType {

    protected long idDesembolso;
    protected List<String> plataforma;
    @XmlElement(name = "DetalleTransaccion")
    protected List<DetalleTransaccionBasicType> detalleTransaccion;

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
     * Gets the value of the plataforma property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plataforma property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlataforma().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPlataforma() {
        if (plataforma == null) {
            plataforma = new ArrayList<String>();
        }
        return this.plataforma;
    }

    /**
     * Gets the value of the detalleTransaccion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleTransaccion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleTransaccion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleTransaccionBasicType }
     * 
     * 
     */
    public List<DetalleTransaccionBasicType> getDetalleTransaccion() {
        if (detalleTransaccion == null) {
            detalleTransaccion = new ArrayList<DetalleTransaccionBasicType>();
        }
        return this.detalleTransaccion;
    }

}
