
package org.bcie.desembolsomo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PropagarReservaRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PropagarReservaRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuarioAutoriza" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="idDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *           &lt;element name="idTransferencia" type="{http://www.bcie.org/DesembolsoBO}idDesembolso" maxOccurs="unbounded"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropagarReservaRequestType", propOrder = {
    "usuarioAutoriza",
    "idDesembolso",
    "idTransferencia"
})
public class PropagarReservaRequestType {

    @XmlElement(required = true)
    protected String usuarioAutoriza;
    protected Long idDesembolso;
    @XmlElement(type = Long.class)
    protected List<Long> idTransferencia;

    /**
     * Obtiene el valor de la propiedad usuarioAutoriza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioAutoriza() {
        return usuarioAutoriza;
    }

    /**
     * Define el valor de la propiedad usuarioAutoriza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioAutoriza(String value) {
        this.usuarioAutoriza = value;
    }

    /**
     * Obtiene el valor de la propiedad idDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdDesembolso() {
        return idDesembolso;
    }

    /**
     * Define el valor de la propiedad idDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdDesembolso(Long value) {
        this.idDesembolso = value;
    }

    /**
     * Gets the value of the idTransferencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idTransferencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdTransferencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getIdTransferencia() {
        if (idTransferencia == null) {
            idTransferencia = new ArrayList<Long>();
        }
        return this.idTransferencia;
    }

}
