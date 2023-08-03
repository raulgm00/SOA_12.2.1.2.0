
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para CrearOperacionAsociadaResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearOperacionAsociadaResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacionPadre" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="idOperacionAsociada" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
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
@XmlType(name = "CrearOperacionAsociadaResponseType", propOrder = {
    "idOperacionPadre",
    "idOperacionAsociada",
    "resultado"
})
public class CrearOperacionAsociadaResponseType {

    protected long idOperacionPadre;
    protected long idOperacionAsociada;
    @XmlElement(name = "Resultado")
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad idOperacionPadre.
     * 
     */
    public long getIdOperacionPadre() {
        return idOperacionPadre;
    }

    /**
     * Define el valor de la propiedad idOperacionPadre.
     * 
     */
    public void setIdOperacionPadre(long value) {
        this.idOperacionPadre = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacionAsociada.
     * 
     */
    public long getIdOperacionAsociada() {
        return idOperacionAsociada;
    }

    /**
     * Define el valor de la propiedad idOperacionAsociada.
     * 
     */
    public void setIdOperacionAsociada(long value) {
        this.idOperacionAsociada = value;
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
