
package org.bcie.adquisicionbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para TipoNoObjecionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TipoNoObjecionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/CatalogoBO}Catalogo">
 *       &lt;sequence>
 *         &lt;element name="NoObjecionRelacion" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoNoObjecionType", propOrder = {
    "noObjecionRelacion"
})
public class TipoNoObjecionType
    extends Catalogo
{

    @XmlElement(name = "NoObjecionRelacion", type = Long.class)
    protected List<Long> noObjecionRelacion;

    /**
     * Gets the value of the noObjecionRelacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the noObjecionRelacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNoObjecionRelacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getNoObjecionRelacion() {
        if (noObjecionRelacion == null) {
            noObjecionRelacion = new ArrayList<Long>();
        }
        return this.noObjecionRelacion;
    }

}
