
package org.bcie.configuracionprocesosbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Enmienda complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Enmienda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="element1" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Enmienda", propOrder = {
    "element1"
})
public class Enmienda {

    @XmlElement(required = true)
    protected Object element1;

    /**
     * Obtiene el valor de la propiedad element1.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getElement1() {
        return element1;
    }

    /**
     * Define el valor de la propiedad element1.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setElement1(Object value) {
        this.element1 = value;
    }

}
