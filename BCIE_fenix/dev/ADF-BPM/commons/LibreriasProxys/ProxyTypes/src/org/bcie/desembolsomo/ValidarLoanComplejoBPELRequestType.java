
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ValidarLoanComplejoBPELRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidarLoanComplejoBPELRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarLoanComplejoBPELRequestType", propOrder = {
    "idContratoDesembolso"
})
public class ValidarLoanComplejoBPELRequestType {

    protected long idContratoDesembolso;

    /**
     * Obtiene el valor de la propiedad idContratoDesembolso.
     * 
     */
    public long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }

    /**
     * Define el valor de la propiedad idContratoDesembolso.
     * 
     */
    public void setIdContratoDesembolso(long value) {
        this.idContratoDesembolso = value;
    }

}
