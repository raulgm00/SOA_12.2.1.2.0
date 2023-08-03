
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.herramientacebo.ProgramaType;


/**
 * <p>Clase Java para ConsultarLimiteByLineaFinancieraRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarLimiteByLineaFinancieraRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Programa" type="{http://www.bcie.org/HerramientaCEBO}ProgramaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarLimiteByLineaFinancieraRequestType", propOrder = {
    "programa"
})
public class ConsultarLimiteByLineaFinancieraRequestType {

    @XmlElement(name = "Programa", required = true)
    protected ProgramaType programa;

    /**
     * Obtiene el valor de la propiedad programa.
     * 
     * @return
     *     possible object is
     *     {@link ProgramaType }
     *     
     */
    public ProgramaType getPrograma() {
        return programa;
    }

    /**
     * Define el valor de la propiedad programa.
     * 
     * @param value
     *     allowed object is
     *     {@link ProgramaType }
     *     
     */
    public void setPrograma(ProgramaType value) {
        this.programa = value;
    }

}
