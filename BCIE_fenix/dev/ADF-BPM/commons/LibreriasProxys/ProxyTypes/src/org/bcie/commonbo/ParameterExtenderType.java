
package org.bcie.commonbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para parameterExtenderType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="parameterExtenderType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/CommonBO}parameterType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="agrupador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parameterExtenderType", propOrder = {
    "id",
    "agrupador"
})
public class ParameterExtenderType
    extends ParameterType
{

    protected long id;
    @XmlElement(required = true)
    protected String agrupador;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad agrupador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgrupador() {
        return agrupador;
    }

    /**
     * Define el valor de la propiedad agrupador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgrupador(String value) {
        this.agrupador = value;
    }

}
