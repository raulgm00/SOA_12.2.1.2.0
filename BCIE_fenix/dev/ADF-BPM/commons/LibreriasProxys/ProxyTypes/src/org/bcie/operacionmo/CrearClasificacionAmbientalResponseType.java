
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.ClasificacionesAmbiental;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para CrearClasificacionAmbientalResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearClasificacionAmbientalResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClasificacionesAmbiental" type="{http://www.bcie.org/OperacionBO}ClasificacionesAmbiental"/>
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
@XmlType(name = "CrearClasificacionAmbientalResponseType", propOrder = {
    "clasificacionesAmbiental",
    "resultado"
})
public class CrearClasificacionAmbientalResponseType {

    @XmlElement(name = "ClasificacionesAmbiental", required = true)
    protected ClasificacionesAmbiental clasificacionesAmbiental;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad clasificacionesAmbiental.
     * 
     * @return
     *     possible object is
     *     {@link ClasificacionesAmbiental }
     *     
     */
    public ClasificacionesAmbiental getClasificacionesAmbiental() {
        return clasificacionesAmbiental;
    }

    /**
     * Define el valor de la propiedad clasificacionesAmbiental.
     * 
     * @param value
     *     allowed object is
     *     {@link ClasificacionesAmbiental }
     *     
     */
    public void setClasificacionesAmbiental(ClasificacionesAmbiental value) {
        this.clasificacionesAmbiental = value;
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
