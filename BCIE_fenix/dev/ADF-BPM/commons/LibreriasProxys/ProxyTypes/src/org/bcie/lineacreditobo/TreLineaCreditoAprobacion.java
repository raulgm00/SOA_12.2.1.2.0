
package org.bcie.lineacreditobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TreLineaCreditoAprobacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TreLineaCreditoAprobacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTreLineaCreditoAprobacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idLineaCredito" type="{http://www.bcie.org/LineaCreditoBO}idLineaCredito" minOccurs="0"/>
 *         &lt;element name="idAprobacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TreLineaCreditoAprobacion", propOrder = {
    "idTreLineaCreditoAprobacion",
    "idLineaCredito",
    "idAprobacion"
})
public class TreLineaCreditoAprobacion {

    protected Long idTreLineaCreditoAprobacion;
    protected Long idLineaCredito;
    protected Long idAprobacion;

    /**
     * Obtiene el valor de la propiedad idTreLineaCreditoAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTreLineaCreditoAprobacion() {
        return idTreLineaCreditoAprobacion;
    }

    /**
     * Define el valor de la propiedad idTreLineaCreditoAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTreLineaCreditoAprobacion(Long value) {
        this.idTreLineaCreditoAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idLineaCredito.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdLineaCredito() {
        return idLineaCredito;
    }

    /**
     * Define el valor de la propiedad idLineaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdLineaCredito(Long value) {
        this.idLineaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad idAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAprobacion() {
        return idAprobacion;
    }

    /**
     * Define el valor de la propiedad idAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAprobacion(Long value) {
        this.idAprobacion = value;
    }

}
