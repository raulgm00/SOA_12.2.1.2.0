
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.commonbo.ParameterExtenderType;


/**
 * <p>Clase Java para ValidarPlazoRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidarPlazoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="TipoLimite" type="{http://www.bcie.org/CommonBO}parameterExtenderType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarPlazoRequestType", propOrder = {
    "idDesembolso",
    "tipoLimite"
})
public class ValidarPlazoRequestType {

    protected long idDesembolso;
    @XmlElement(name = "TipoLimite", required = true)
    protected ParameterExtenderType tipoLimite;

    /**
     * Obtiene el valor de la propiedad idDesembolso.
     * 
     */
    public long getIdDesembolso() {
        return idDesembolso;
    }

    /**
     * Define el valor de la propiedad idDesembolso.
     * 
     */
    public void setIdDesembolso(long value) {
        this.idDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoLimite.
     * 
     * @return
     *     possible object is
     *     {@link ParameterExtenderType }
     *     
     */
    public ParameterExtenderType getTipoLimite() {
        return tipoLimite;
    }

    /**
     * Define el valor de la propiedad tipoLimite.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterExtenderType }
     *     
     */
    public void setTipoLimite(ParameterExtenderType value) {
        this.tipoLimite = value;
    }

}
