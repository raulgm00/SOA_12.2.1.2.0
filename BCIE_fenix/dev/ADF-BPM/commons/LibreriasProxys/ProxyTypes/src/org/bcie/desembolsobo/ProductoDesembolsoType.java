
package org.bcie.desembolsobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.productobo.ProductoBasicType;


/**
 * <p>Clase Java para ProductoDesembolsoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ProductoDesembolsoType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/ProductoBO}ProductoBasicType">
 *       &lt;sequence>
 *         &lt;element name="Componente" type="{http://www.bcie.org/DesembolsoBO}ComponenteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductoDesembolsoType", propOrder = {
    "componente"
})
public class ProductoDesembolsoType
    extends ProductoBasicType
{

    @XmlElement(name = "Componente")
    protected List<ComponenteType> componente;

    /**
     * Gets the value of the componente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComponenteType }
     * 
     * 
     */
    public List<ComponenteType> getComponente() {
        if (componente == null) {
            componente = new ArrayList<ComponenteType>();
        }
        return this.componente;
    }

}
