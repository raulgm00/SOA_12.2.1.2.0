
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.ClasificacionesAmbiental;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConsultarClasificacionAmbientalResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarClasificacionAmbientalResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClasificacionAmbiental" type="{http://www.bcie.org/OperacionBO}ClasificacionesAmbiental"/>
 *         &lt;element name="Resultado" type="{http://www.bcie.org/ResultBO}Resultado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarClasificacionAmbientalResponseType", propOrder = {
    "clasificacionAmbiental",
    "resultado"
})
public class ConsultarClasificacionAmbientalResponseType {

    @XmlElement(name = "ClasificacionAmbiental", required = true)
    protected ClasificacionesAmbiental clasificacionAmbiental;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad clasificacionAmbiental.
     * 
     * @return
     *     possible object is
     *     {@link ClasificacionesAmbiental }
     *     
     */
    public ClasificacionesAmbiental getClasificacionAmbiental() {
        return clasificacionAmbiental;
    }

    /**
     * Define el valor de la propiedad clasificacionAmbiental.
     * 
     * @param value
     *     allowed object is
     *     {@link ClasificacionesAmbiental }
     *     
     */
    public void setClasificacionAmbiental(ClasificacionesAmbiental value) {
        this.clasificacionAmbiental = value;
    }

    /**
     * Obtiene el valor de la propiedad resultado.
     * 
     * @return
     *     possible object is
     *     {@link Resultado }
     *     
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * Define el valor de la propiedad resultado.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultado }
     *     
     */
    public void setResultado(Resultado value) {
        this.resultado = value;
    }

}
