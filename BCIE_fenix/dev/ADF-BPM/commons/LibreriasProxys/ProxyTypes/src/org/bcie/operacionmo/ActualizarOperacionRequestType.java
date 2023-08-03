
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.Operacion;


/**
 * <p>Clase Java para ActualizarOperacionRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ActualizarOperacionRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}Operacion" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reasignar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="actualizarTermino" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActualizarOperacionRequestType", propOrder = {
    "operacion",
    "status",
    "reasignar",
    "actualizarTermino"
})
public class ActualizarOperacionRequestType {

    @XmlElement(name = "Operacion")
    protected Operacion operacion;
    @XmlElement(name = "Status", required = true)
    protected String status;
    protected boolean reasignar;
    protected boolean actualizarTermino;

    /**
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link Operacion }
     *     
     */
    public Operacion getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Operacion }
     *     
     */
    public void setOperacion(Operacion value) {
        this.operacion = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad reasignar.
     * 
     */
    public boolean isReasignar() {
        return reasignar;
    }

    /**
     * Define el valor de la propiedad reasignar.
     * 
     */
    public void setReasignar(boolean value) {
        this.reasignar = value;
    }

    /**
     * Obtiene el valor de la propiedad actualizarTermino.
     * 
     */
    public boolean isActualizarTermino() {
        return actualizarTermino;
    }

    /**
     * Define el valor de la propiedad actualizarTermino.
     * 
     */
    public void setActualizarTermino(boolean value) {
        this.actualizarTermino = value;
    }

}
