
package org.bcie.lineacreditobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Flexcube complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Flexcube">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idProductoFlexcube" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idFlexcubePasivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Flexcube", propOrder = {
    "id",
    "idProductoFlexcube",
    "idFlexcubePasivo"
})
public class Flexcube {

    protected String id;
    protected String idProductoFlexcube;
    protected String idFlexcubePasivo;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad idProductoFlexcube.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdProductoFlexcube() {
        return idProductoFlexcube;
    }

    /**
     * Define el valor de la propiedad idProductoFlexcube.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdProductoFlexcube(String value) {
        this.idProductoFlexcube = value;
    }

    /**
     * Obtiene el valor de la propiedad idFlexcubePasivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFlexcubePasivo() {
        return idFlexcubePasivo;
    }

    /**
     * Define el valor de la propiedad idFlexcubePasivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFlexcubePasivo(String value) {
        this.idFlexcubePasivo = value;
    }

}
