
package org.bcie.adquisicionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.adquisicionbo.HeaderPublicacionType;


/**
 * <p>Clase Java para InformeResultadosOtrosType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InformeResultadosOtrosType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/AdquisicionBO}HeaderPublicacionType">
 *       &lt;sequence>
 *         &lt;element name="resultadoProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformeResultadosOtrosType", propOrder = {
    "resultadoProceso"
})
public class InformeResultadosOtrosType
    extends HeaderPublicacionType
{

    @XmlElement(required = true)
    protected String resultadoProceso;

    /**
     * Obtiene el valor de la propiedad resultadoProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultadoProceso() {
        return resultadoProceso;
    }

    /**
     * Define el valor de la propiedad resultadoProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultadoProceso(String value) {
        this.resultadoProceso = value;
    }

}
