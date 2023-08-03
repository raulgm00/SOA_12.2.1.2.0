
package org.bcie.configuracionprocesosbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.Operacion;
import org.bcie.productobo.Producto;


/**
 * Elemento para la configuración inicial del proceso de Elegibilidad
 * 
 * <p>Clase Java para ConfiguracionElegibilidad complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionElegibilidad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *         &lt;element name="producto" type="{http://www.bcie.org/ProductoBO}Producto"/>
 *         &lt;element name="RolesEquipoTrabajo" type="{http://www.bcie.org/ConfiguracionProcesosBO}EquipoTrabajo"/>
 *         &lt;element name="configuration">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="requiereAsjur" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="requiereGeries" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="requiereOej" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionElegibilidad", propOrder = {
    "operacion",
    "producto",
    "rolesEquipoTrabajo",
    "configuration"
})
public class ConfiguracionElegibilidad {

    @XmlElement(required = true)
    protected Operacion operacion;
    @XmlElement(required = true)
    protected Producto producto;
    @XmlElement(name = "RolesEquipoTrabajo", required = true)
    protected EquipoTrabajo rolesEquipoTrabajo;
    @XmlElement(required = true)
    protected ConfiguracionElegibilidad.Configuration configuration;

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
     * Obtiene el valor de la propiedad configuration.
     * 
     * @return
     *     possible object is
     *     {@link ConfiguracionElegibilidad.Configuration }
     *     
     */
    public ConfiguracionElegibilidad.Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Define el valor de la propiedad configuration.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionElegibilidad.Configuration }
     *     
     */
    public void setConfiguration(ConfiguracionElegibilidad.Configuration value) {
        this.configuration = value;
    }


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
     *         &lt;element name="requiereAsjur" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="requiereGeries" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="requiereOej" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
        "requiereAsjur",
        "requiereGeries",
        "requiereOej"
    })
    public static class Configuration {

        protected Boolean requiereAsjur;
        protected Boolean requiereGeries;
        protected Boolean requiereOej;

        /**
         * Obtiene el valor de la propiedad requiereAsjur.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isRequiereAsjur() {
            return requiereAsjur;
        }

        /**
         * Define el valor de la propiedad requiereAsjur.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setRequiereAsjur(Boolean value) {
            this.requiereAsjur = value;
        }

        /**
         * Obtiene el valor de la propiedad requiereGeries.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isRequiereGeries() {
            return requiereGeries;
        }

        /**
         * Define el valor de la propiedad requiereGeries.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setRequiereGeries(Boolean value) {
            this.requiereGeries = value;
        }

        /**
         * Obtiene el valor de la propiedad requiereOej.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isRequiereOej() {
            return requiereOej;
        }

        /**
         * Define el valor de la propiedad requiereOej.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setRequiereOej(Boolean value) {
            this.requiereOej = value;
        }

    }

}
