
package org.bcie.matriztccbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ProcesarEnmiendaRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ProcesarEnmiendaRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PID_OPERACION" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PID_ENMIENDA" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PID_TCC" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PIDTAREA" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcesarEnmiendaRequestType", propOrder = {
    "pidoperacion",
    "pidenmienda",
    "pidtcc",
    "pidtarea"
})
public class ProcesarEnmiendaRequestType {

    @XmlElement(name = "PID_OPERACION")
    protected long pidoperacion;
    @XmlElement(name = "PID_ENMIENDA")
    protected long pidenmienda;
    @XmlElement(name = "PID_TCC")
    protected long pidtcc;
    @XmlElement(name = "PIDTAREA")
    protected long pidtarea;

    /**
     * Obtiene el valor de la propiedad pidoperacion.
     * 
     */
    public long getPIDOPERACION() {
        return pidoperacion;
    }

    /**
     * Define el valor de la propiedad pidoperacion.
     * 
     */
    public void setPIDOPERACION(long value) {
        this.pidoperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad pidenmienda.
     * 
     */
    public long getPIDENMIENDA() {
        return pidenmienda;
    }

    /**
     * Define el valor de la propiedad pidenmienda.
     * 
     */
    public void setPIDENMIENDA(long value) {
        this.pidenmienda = value;
    }

    /**
     * Obtiene el valor de la propiedad pidtcc.
     * 
     */
    public long getPIDTCC() {
        return pidtcc;
    }

    /**
     * Define el valor de la propiedad pidtcc.
     * 
     */
    public void setPIDTCC(long value) {
        this.pidtcc = value;
    }

    /**
     * Obtiene el valor de la propiedad pidtarea.
     * 
     */
    public long getPIDTAREA() {
        return pidtarea;
    }

    /**
     * Define el valor de la propiedad pidtarea.
     * 
     */
    public void setPIDTAREA(long value) {
        this.pidtarea = value;
    }

}
