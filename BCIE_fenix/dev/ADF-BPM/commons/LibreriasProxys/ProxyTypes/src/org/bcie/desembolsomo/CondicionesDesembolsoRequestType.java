
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.condicionbo.ListaCondiciones;


/**
 * <p>Clase Java para CondicionesDesembolsoRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CondicionesDesembolsoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idSolicitudDesembolso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idContratoDesembolso" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ListaCondicion" type="{http://www.bcie.org/CondicionBO}listaCondiciones" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CondicionesDesembolsoRequestType", propOrder = {
    "idSolicitudDesembolso",
    "idContratoDesembolso",
    "listaCondicion"
})
public class CondicionesDesembolsoRequestType {

    protected long idSolicitudDesembolso;
    protected Long idContratoDesembolso;
    @XmlElement(name = "ListaCondicion")
    protected ListaCondiciones listaCondicion;

    /**
     * Obtiene el valor de la propiedad idSolicitudDesembolso.
     * 
     */
    public long getIdSolicitudDesembolso() {
        return idSolicitudDesembolso;
    }

    /**
     * Define el valor de la propiedad idSolicitudDesembolso.
     * 
     */
    public void setIdSolicitudDesembolso(long value) {
        this.idSolicitudDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad idContratoDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }

    /**
     * Define el valor de la propiedad idContratoDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdContratoDesembolso(Long value) {
        this.idContratoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad listaCondicion.
     * 
     * @return
     *     possible object is
     *     {@link ListaCondiciones }
     *     
     */
    public ListaCondiciones getListaCondicion() {
        return listaCondicion;
    }

    /**
     * Define el valor de la propiedad listaCondicion.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaCondiciones }
     *     
     */
    public void setListaCondicion(ListaCondiciones value) {
        this.listaCondicion = value;
    }

}
