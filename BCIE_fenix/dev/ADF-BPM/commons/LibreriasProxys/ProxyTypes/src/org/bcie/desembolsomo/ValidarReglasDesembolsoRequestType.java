
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.reglatareabo.TareaReglas;


/**
 * <p>Clase Java para ValidarReglasDesembolsoRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidarReglasDesembolsoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="TareaReglas" type="{http://www.bcie.org/ReglaTareaBO}TareaReglas"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidarReglasDesembolsoRequestType", propOrder = {
    "idDesembolso",
    "instanciaProceso",
    "tareaReglas"
})
public class ValidarReglasDesembolsoRequestType {

    protected long idDesembolso;
    protected long instanciaProceso;
    @XmlElement(name = "TareaReglas", required = true)
    protected TareaReglas tareaReglas;

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
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     */
    public long getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     */
    public void setInstanciaProceso(long value) {
        this.instanciaProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad tareaReglas.
     * 
     * @return
     *     possible object is
     *     {@link TareaReglas }
     *     
     */
    public TareaReglas getTareaReglas() {
        return tareaReglas;
    }

    /**
     * Define el valor de la propiedad tareaReglas.
     * 
     * @param value
     *     allowed object is
     *     {@link TareaReglas }
     *     
     */
    public void setTareaReglas(TareaReglas value) {
        this.tareaReglas = value;
    }

}
