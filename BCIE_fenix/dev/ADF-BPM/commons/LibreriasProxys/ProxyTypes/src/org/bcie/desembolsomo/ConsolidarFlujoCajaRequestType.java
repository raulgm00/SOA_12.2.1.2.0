
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.ContratoDesembolso;


/**
 * <p>Clase Java para ConsolidarFlujoCajaRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsolidarFlujoCajaRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso" maxOccurs="unbounded"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsolidarFlujoCajaRequestType", propOrder = {
    "contratoDesembolso",
    "instanciaProceso",
    "idOperacion"
})
public class ConsolidarFlujoCajaRequestType {

    @XmlElement(name = "ContratoDesembolso", required = true)
    protected List<ContratoDesembolso> contratoDesembolso;
    protected long instanciaProceso;
    protected long idOperacion;

    /**
     * Gets the value of the contratoDesembolso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contratoDesembolso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContratoDesembolso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContratoDesembolso }
     * 
     * 
     */
    public List<ContratoDesembolso> getContratoDesembolso() {
        if (contratoDesembolso == null) {
            contratoDesembolso = new ArrayList<ContratoDesembolso>();
        }
        return this.contratoDesembolso;
    }

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     */
    public long getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     */
    public void setInstanciaProceso(long value) {
        this.instanciaProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     */
    public long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     */
    public void setIdOperacion(long value) {
        this.idOperacion = value;
    }

}
