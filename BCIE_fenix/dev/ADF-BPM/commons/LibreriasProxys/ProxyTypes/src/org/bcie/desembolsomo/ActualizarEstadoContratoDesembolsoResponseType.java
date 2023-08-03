
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ActualizarEstadoContratoDesembolsoResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ActualizarEstadoContratoDesembolsoResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultado" type="{http://www.bcie.org/ResultBO}Resultado"/>
 *         &lt;element name="respuestaDB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActualizarEstadoContratoDesembolsoResponseType", propOrder = {
    "resultado",
    "respuestaDB"
})
public class ActualizarEstadoContratoDesembolsoResponseType {

    @XmlElement(required = true)
    protected Resultado resultado;
    @XmlElement(required = true)
    protected String respuestaDB;

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

    /**
     * Obtiene el valor de la propiedad respuestaDB.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespuestaDB() {
        return respuestaDB;
    }

    /**
     * Define el valor de la propiedad respuestaDB.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespuestaDB(String value) {
        this.respuestaDB = value;
    }

}
