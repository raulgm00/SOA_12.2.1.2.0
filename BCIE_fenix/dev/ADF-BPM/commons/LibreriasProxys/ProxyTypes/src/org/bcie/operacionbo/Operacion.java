
package org.bcie.operacionbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.bcie.contratobo.Contrato;


/**
 * <p>Clase Java para Operacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Operacion">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/OperacionBO}OperacionExtendentType">
 *       &lt;sequence>
 *         &lt;element name="contrato" type="{http://www.bcie.org/ContratoBO}Contrato" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Operacion", propOrder = {
    "contrato"
})
public class Operacion
    extends OperacionExtendentType
{

    protected List<Contrato> contrato;

    /**
     * Gets the value of the contrato property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contrato property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContrato().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Contrato }
     * 
     * 
     */
    public List<Contrato> getContrato() {
        if (contrato == null) {
            contrato = new ArrayList<Contrato>();
        }
        return this.contrato;
    }

}
