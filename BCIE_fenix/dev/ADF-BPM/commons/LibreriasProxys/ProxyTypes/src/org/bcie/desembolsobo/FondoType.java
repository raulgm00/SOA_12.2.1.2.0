
package org.bcie.desembolsobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para FondoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FondoType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/CatalogoBO}Catalogo">
 *       &lt;sequence>
 *         &lt;element name="Validador" type="{http://www.bcie.org/CatalogoBO}Catalogo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FondoType", propOrder = {
    "validador"
})
public class FondoType
    extends Catalogo
{

    @XmlElement(name = "Validador")
    protected List<Catalogo> validador;

    /**
     * Gets the value of the validador property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validador property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidador().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Catalogo }
     * 
     * 
     */
    public List<Catalogo> getValidador() {
        if (validador == null) {
            validador = new ArrayList<Catalogo>();
        }
        return this.validador;
    }

}
