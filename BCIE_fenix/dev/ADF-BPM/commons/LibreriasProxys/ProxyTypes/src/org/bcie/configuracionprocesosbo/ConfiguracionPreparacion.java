
package org.bcie.configuracionprocesosbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.Operacion;
import org.bcie.productobo.Producto;


/**
 * Elemento para la configuración inicial del proceso de Preparación
 * 
 * <p>Clase Java para ConfiguracionPreparacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionPreparacion">
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
 *                   &lt;element name="requiereElaborarDocumentoTecnicoOPD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="requiereGenerarPlanMonitoreoOPD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="requiereRevisarEvidenciaCumplimientoOPD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "ConfiguracionPreparacion", propOrder = {
    "operacion",
    "producto",
    "rolesEquipoTrabajo",
    "configuration"
})
public class ConfiguracionPreparacion {

    @XmlElement(required = true)
    protected Operacion operacion;
    @XmlElement(required = true)
    protected Producto producto;
    @XmlElement(name = "RolesEquipoTrabajo", required = true)
    protected EquipoTrabajo rolesEquipoTrabajo;
    @XmlElement(required = true)
    protected ConfiguracionPreparacion.Configuration configuration;

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
     *     {@link ConfiguracionPreparacion.Configuration }
     *     
     */
    public ConfiguracionPreparacion.Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Define el valor de la propiedad configuration.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfiguracionPreparacion.Configuration }
     *     
     */
    public void setConfiguration(ConfiguracionPreparacion.Configuration value) {
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
     *         &lt;element name="requiereElaborarDocumentoTecnicoOPD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="requiereGenerarPlanMonitoreoOPD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="requiereRevisarEvidenciaCumplimientoOPD" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
        "requiereElaborarDocumentoTecnicoOPD",
        "requiereGenerarPlanMonitoreoOPD",
        "requiereRevisarEvidenciaCumplimientoOPD"
    })
    public static class Configuration {

        protected Boolean requiereElaborarDocumentoTecnicoOPD;
        protected Boolean requiereGenerarPlanMonitoreoOPD;
        protected Boolean requiereRevisarEvidenciaCumplimientoOPD;

        /**
         * Obtiene el valor de la propiedad requiereElaborarDocumentoTecnicoOPD.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isRequiereElaborarDocumentoTecnicoOPD() {
            return requiereElaborarDocumentoTecnicoOPD;
        }

        /**
         * Define el valor de la propiedad requiereElaborarDocumentoTecnicoOPD.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setRequiereElaborarDocumentoTecnicoOPD(Boolean value) {
            this.requiereElaborarDocumentoTecnicoOPD = value;
        }

        /**
         * Obtiene el valor de la propiedad requiereGenerarPlanMonitoreoOPD.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isRequiereGenerarPlanMonitoreoOPD() {
            return requiereGenerarPlanMonitoreoOPD;
        }

        /**
         * Define el valor de la propiedad requiereGenerarPlanMonitoreoOPD.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setRequiereGenerarPlanMonitoreoOPD(Boolean value) {
            this.requiereGenerarPlanMonitoreoOPD = value;
        }

        /**
         * Obtiene el valor de la propiedad requiereRevisarEvidenciaCumplimientoOPD.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isRequiereRevisarEvidenciaCumplimientoOPD() {
            return requiereRevisarEvidenciaCumplimientoOPD;
        }

        /**
         * Define el valor de la propiedad requiereRevisarEvidenciaCumplimientoOPD.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setRequiereRevisarEvidenciaCumplimientoOPD(Boolean value) {
            this.requiereRevisarEvidenciaCumplimientoOPD = value;
        }

    }

}
