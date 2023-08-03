
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.desembolsobo.SolicitudDesembolso;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para VerificarValidacionAsignacionResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="VerificarValidacionAsignacionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SolicitudDesembolso" type="{http://www.bcie.org/DesembolsoBO}SolicitudDesembolso"/>
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
@XmlType(name = "VerificarValidacionAsignacionResponseType", propOrder = {
    "solicitudDesembolso",
    "resultado"
})
public class VerificarValidacionAsignacionResponseType {

    @XmlElement(name = "SolicitudDesembolso", required = true)
    protected SolicitudDesembolso solicitudDesembolso;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad solicitudDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudDesembolso }
     *     
     */
    public SolicitudDesembolso getSolicitudDesembolso() {
        return solicitudDesembolso;
    }

    /**
     * Define el valor de la propiedad solicitudDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudDesembolso }
     *     
     */
    public void setSolicitudDesembolso(SolicitudDesembolso value) {
        this.solicitudDesembolso = value;
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
