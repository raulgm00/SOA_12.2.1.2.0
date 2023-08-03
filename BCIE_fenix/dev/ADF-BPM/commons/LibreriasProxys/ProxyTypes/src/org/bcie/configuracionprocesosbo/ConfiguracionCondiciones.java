
package org.bcie.configuracionprocesosbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.Operacion;
import org.bcie.productobo.Producto;
import org.bcie.xmlns.objetoproceso.comun.parameter.v1.ParameterType;


/**
 * Elemento para la configuración Condiciones
 * 
 * <p>Clase Java para ConfiguracionCondiciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionCondiciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *         &lt;element name="producto" type="{http://www.bcie.org/ProductoBO}Producto"/>
 *         &lt;element name="RolesEquipoTrabajo" type="{http://www.bcie.org/ConfiguracionProcesosBO}EquipoTrabajo"/>
 *         &lt;element name="requiereValidarCondiciones" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1}ParameterType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionCondiciones", propOrder = {
    "operacion",
    "producto",
    "rolesEquipoTrabajo",
    "requiereValidarCondiciones",
    "parameterType"
})
public class ConfiguracionCondiciones {

    @XmlElement(required = true)
    protected Operacion operacion;
    @XmlElement(required = true)
    protected Producto producto;
    @XmlElement(name = "RolesEquipoTrabajo", required = true)
    protected EquipoTrabajo rolesEquipoTrabajo;
    protected boolean requiereValidarCondiciones;
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
     * Obtiene el valor de la propiedad producto.
     * 
     * @return
     *     possible object is
     *     {@link Producto }
     *     
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Define el valor de la propiedad producto.
     * 
     * @param value
     *     allowed object is
     *     {@link Producto }
     *     
     */
    public void setProducto(Producto value) {
        this.producto = value;
    }

    /**
     * Obtiene el valor de la propiedad rolesEquipoTrabajo.
     * 
     * @return
     *     possible object is
     *     {@link EquipoTrabajo }
     *     
     */
    public EquipoTrabajo getRolesEquipoTrabajo() {
        return rolesEquipoTrabajo;
    }

    /**
     * Define el valor de la propiedad rolesEquipoTrabajo.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipoTrabajo }
     *     
     */
    public void setRolesEquipoTrabajo(EquipoTrabajo value) {
        this.rolesEquipoTrabajo = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereValidarCondiciones.
     * 
     */
    public boolean isRequiereValidarCondiciones() {
        return requiereValidarCondiciones;
    }

    /**
     * Define el valor de la propiedad requiereValidarCondiciones.
     * 
     */
    public void setRequiereValidarCondiciones(boolean value) {
        this.requiereValidarCondiciones = value;
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
