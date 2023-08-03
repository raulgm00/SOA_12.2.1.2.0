
package org.bcie.aprobacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.aprobacionbo.Aprobacion;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ValidarProcesoAprobacionFinalizadoResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidarProcesoAprobacionFinalizadoResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Aprobacion" type="{http://www.bcie.org/AprobacionBO}Aprobacion"/>
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
@XmlType(name = "ValidarProcesoAprobacionFinalizadoResponseType", propOrder = {
    "aprobacion",
    "resultado"
})
public class ValidarProcesoAprobacionFinalizadoResponseType {

    @XmlElement(name = "Aprobacion", required = true)
    protected Aprobacion aprobacion;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad aprobacion.
     * 
     * @return
     *     possible object is
     *     {@link Aprobacion }
     *     
     */
    public Aprobacion getAprobacion() {
        return aprobacion;
    }

    /**
     * Define el valor de la propiedad aprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Aprobacion }
     *     
     */
    public void setAprobacion(Aprobacion value) {
        this.aprobacion = value;
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
