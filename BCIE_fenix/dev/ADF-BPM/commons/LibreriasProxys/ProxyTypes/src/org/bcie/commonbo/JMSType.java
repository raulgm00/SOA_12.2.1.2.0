
package org.bcie.commonbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para JMSType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="JMSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombreJMS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JMSType", propOrder = {
    "nombreJMS",
    "mensaje"
})
public class JMSType {

    @XmlElement(required = true)
    protected String nombreJMS;
    @XmlElement(required = true)
    protected Object mensaje;

    /**
     * Obtiene el valor de la propiedad nombreJMS.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreJMS() {
        return nombreJMS;
    }

    /**
     * Define el valor de la propiedad nombreJMS.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreJMS(String value) {
        this.nombreJMS = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMensaje(Object value) {
        this.mensaje = value;
    }

}
