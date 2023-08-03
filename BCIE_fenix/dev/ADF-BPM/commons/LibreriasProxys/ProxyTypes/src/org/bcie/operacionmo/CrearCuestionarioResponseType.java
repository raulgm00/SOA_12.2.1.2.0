
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.PreguntaCuestionario;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para CrearCuestionarioResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearCuestionarioResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PreguntaCuestionario" type="{http://www.bcie.org/OperacionBO}PreguntaCuestionario"/>
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
@XmlType(name = "CrearCuestionarioResponseType", propOrder = {
    "preguntaCuestionario",
    "resultado"
})
public class CrearCuestionarioResponseType {

    @XmlElement(name = "PreguntaCuestionario", required = true)
    protected PreguntaCuestionario preguntaCuestionario;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad preguntaCuestionario.
     * 
     * @return
     *     possible object is
     *     {@link PreguntaCuestionario }
     *     
     */
    public PreguntaCuestionario getPreguntaCuestionario() {
        return preguntaCuestionario;
    }

    /**
     * Define el valor de la propiedad preguntaCuestionario.
     * 
     * @param value
     *     allowed object is
     *     {@link PreguntaCuestionario }
     *     
     */
    public void setPreguntaCuestionario(PreguntaCuestionario value) {
        this.preguntaCuestionario = value;
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
