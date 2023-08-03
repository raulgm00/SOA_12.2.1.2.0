
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para GenerarInformeOperacionResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="GenerarInformeOperacionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cuestionario" type="{http://www.bcie.org/OperacionBO}Cuestionario" minOccurs="0"/>
 *         &lt;element name="Resultado" type="{http://www.bcie.org/ResultBO}Resultado" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenerarInformeOperacionResponseType", propOrder = {
    "cuestionario",
    "resultado"
})
public class GenerarInformeOperacionResponseType {

    @XmlElement(name = "Cuestionario")
    protected byte[] cuestionario;
    @XmlElement(name = "Resultado")
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad cuestionario.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCuestionario() {
        return cuestionario;
    }

    /**
     * Define el valor de la propiedad cuestionario.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCuestionario(byte[] value) {
        this.cuestionario = value;
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
