
package org.bcie.desembolsomo;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ActualizarEstadoContratoDesembolsoRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ActualizarEstadoContratoDesembolsoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTcaEstado" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="idContrato" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActualizarEstadoContratoDesembolsoRequestType", propOrder = {
    "idTcaEstado",
    "idContrato"
})
public class ActualizarEstadoContratoDesembolsoRequestType {

    @XmlElement(required = true)
    protected BigInteger idTcaEstado;
    @XmlElement(required = true)
    protected BigInteger idContrato;

    /**
     * Obtiene el valor de la propiedad idTcaEstado.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdTcaEstado() {
        return idTcaEstado;
    }

    /**
     * Define el valor de la propiedad idTcaEstado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdTcaEstado(BigInteger value) {
        this.idTcaEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad idContrato.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdContrato() {
        return idContrato;
    }

    /**
     * Define el valor de la propiedad idContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdContrato(BigInteger value) {
        this.idContrato = value;
    }

}
