
package org.bcie.xmlns.objetoproceso.comun.header.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.xmlns.objetoproceso.comun.cliente.v1.Cliente;
import org.bcie.xmlns.objetoproceso.comun.operacion.v1.Operacion;
import org.bcie.xmlns.objetoproceso.comun.parameter.v1.ParameterType;
import org.bcie.xmlns.objetoproceso.comun.proceso.v1.Proceso;
import org.bcie.xmlns.objetoproceso.comun.tarea.v1.Tarea;


/**
 * Elemento que contiene la información del Header para TODOS los payload de BPM.
 * 
 * <p>Clase Java para Header complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Header">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;choice minOccurs="0">
 *             &lt;element name="Operacion" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1}Operacion"/>
 *             &lt;element name="Cliente" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1}Cliente"/>
 *           &lt;/choice>
 *           &lt;element name="Tarea" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1}Tarea" minOccurs="0"/>
 *           &lt;element name="Proceso" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1}Proceso" minOccurs="0"/>
 *           &lt;element ref="{http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1}ParameterType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Header", propOrder = {
    "operacion",
    "cliente",
    "tarea",
    "proceso",
    "parameterType"
})
public class Header {

    @XmlElement(name = "Operacion")
    protected Operacion operacion;
    @XmlElement(name = "Cliente")
    protected Cliente cliente;
    @XmlElement(name = "Tarea")
    protected Tarea tarea;
    @XmlElement(name = "Proceso")
    protected Proceso proceso;
    @XmlElement(name = "ParameterType", namespace = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1")
    protected List<ParameterType> parameterType;

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
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link Cliente }
     *     
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Cliente }
     *     
     */
    public void setCliente(Cliente value) {
        this.cliente = value;
    }

    /**
     * Obtiene el valor de la propiedad tarea.
     * 
     * @return
     *     possible object is
     *     {@link Tarea }
     *     
     */
    public Tarea getTarea() {
        return tarea;
    }

    /**
     * Define el valor de la propiedad tarea.
     * 
     * @param value
     *     allowed object is
     *     {@link Tarea }
     *     
     */
    public void setTarea(Tarea value) {
        this.tarea = value;
    }

    /**
     * Obtiene el valor de la propiedad proceso.
     * 
     * @return
     *     possible object is
     *     {@link Proceso }
     *     
     */
    public Proceso getProceso() {
        return proceso;
    }

    /**
     * Define el valor de la propiedad proceso.
     * 
     * @param value
     *     allowed object is
     *     {@link Proceso }
     *     
     */
    public void setProceso(Proceso value) {
        this.proceso = value;
    }

    /**
     * Gets the value of the parameterType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameterType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameterType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterType }
     * 
     * 
     */
    public List<ParameterType> getParameterType() {
        if (parameterType == null) {
            parameterType = new ArrayList<ParameterType>();
        }
        return this.parameterType;
    }

}
