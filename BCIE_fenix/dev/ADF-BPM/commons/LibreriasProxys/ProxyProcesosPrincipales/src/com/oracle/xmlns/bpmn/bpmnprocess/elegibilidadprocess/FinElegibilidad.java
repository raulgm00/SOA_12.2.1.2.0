
package com.oracle.xmlns.bpmn.bpmnprocess.elegibilidadprocess;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.eqipotrabajo.v1.ListadoEquipoTrbajo;
import org.bcie.xmlns.objetoproceso.comun.header.v1.Header;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1}Header"/>
 *         &lt;element name="equipoTrabajo" type="{http://www.bcie.org/EqipoTrabajo/V1}ListadoEquipoTrbajo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "equipoTrabajo"
})
@XmlRootElement(name = "FinElegibilidad")
public class FinElegibilidad {

    @XmlElement(name = "Header", required = true)
    protected Header header;
    @XmlElement(required = true)
    protected ListadoEquipoTrbajo equipoTrabajo;

    /**
     * Obtiene el valor de la propiedad header.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Define el valor de la propiedad header.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Obtiene el valor de la propiedad equipoTrabajo.
     * 
     * @return
     *     possible object is
     *     {@link ListadoEquipoTrbajo }
     *     
     */
    public ListadoEquipoTrbajo getEquipoTrabajo() {
        return equipoTrabajo;
    }

    /**
     * Define el valor de la propiedad equipoTrabajo.
     * 
     * @param value
     *     allowed object is
     *     {@link ListadoEquipoTrbajo }
     *     
     */
    public void setEquipoTrabajo(ListadoEquipoTrbajo value) {
        this.equipoTrabajo = value;
    }

}
