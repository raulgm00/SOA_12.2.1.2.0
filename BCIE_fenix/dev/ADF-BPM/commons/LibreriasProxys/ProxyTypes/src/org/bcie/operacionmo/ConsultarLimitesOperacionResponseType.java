
package org.bcie.operacionmo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.LimitesOperacion;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConsultarLimitesOperacionResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarLimitesOperacionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetalleLimiteOperacion" type="{http://www.bcie.org/OperacionBO}LimitesOperacion" maxOccurs="unbounded"/>
 *         &lt;element name="Resultado" type="{http://www.bcie.org/ResultBO}Resultado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarLimitesOperacionResponseType", propOrder = {
    "detalleLimiteOperacion",
    "resultado"
})
public class ConsultarLimitesOperacionResponseType {

    @XmlElement(name = "DetalleLimiteOperacion", required = true)
    protected List<LimitesOperacion> detalleLimiteOperacion;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Gets the value of the detalleLimiteOperacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleLimiteOperacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleLimiteOperacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LimitesOperacion }
     * 
     * 
     */
    public List<LimitesOperacion> getDetalleLimiteOperacion() {
        if (detalleLimiteOperacion == null) {
            detalleLimiteOperacion = new ArrayList<LimitesOperacion>();
        }
        return this.detalleLimiteOperacion;
    }

    /**
     * Obtiene el valor de la propiedad resultado.
     * 
     * @return
     *     possible object is
     *     {@link Resultado }
     *     
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * Define el valor de la propiedad resultado.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultado }
     *     
     */
    public void setResultado(Resultado value) {
        this.resultado = value;
    }

}
