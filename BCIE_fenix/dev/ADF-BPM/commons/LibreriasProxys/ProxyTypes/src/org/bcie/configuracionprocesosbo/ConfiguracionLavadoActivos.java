
package org.bcie.configuracionprocesosbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.Operacion;
import org.bcie.productobo.Producto;


/**
 * Elemento para la configuración inicial del proceso de Lavado de activos
 * 
 * <p>Clase Java para ConfiguracionLavadoActivos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionLavadoActivos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *         &lt;element name="producto" type="{http://www.bcie.org/ProductoBO}Producto"/>
 *         &lt;element name="responsableRO" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="responsableOFIC" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="solicitarAnalista" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="aplicaLAFT" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RolesEquipoTrabajo" type="{http://www.bcie.org/ConfiguracionProcesosBO}EquipoTrabajo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionLavadoActivos", propOrder = {
    "operacion",
    "producto",
    "responsableRO",
    "responsableOFIC",
    "solicitarAnalista",
    "aplicaLAFT",
    "rolesEquipoTrabajo"
})
public class ConfiguracionLavadoActivos {

    @XmlElement(required = true)
    protected Operacion operacion;
    @XmlElement(required = true)
    protected Producto producto;
    protected boolean responsableRO;
    protected boolean responsableOFIC;
    protected boolean solicitarAnalista;
    protected boolean aplicaLAFT;
    @XmlElement(name = "RolesEquipoTrabajo", required = true)
    protected EquipoTrabajo rolesEquipoTrabajo;

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
     * Obtiene el valor de la propiedad responsableRO.
     * 
     */
    public boolean isResponsableRO() {
        return responsableRO;
    }

    /**
     * Define el valor de la propiedad responsableRO.
     * 
     */
    public void setResponsableRO(boolean value) {
        this.responsableRO = value;
    }

    /**
     * Obtiene el valor de la propiedad responsableOFIC.
     * 
     */
    public boolean isResponsableOFIC() {
        return responsableOFIC;
    }

    /**
     * Define el valor de la propiedad responsableOFIC.
     * 
     */
    public void setResponsableOFIC(boolean value) {
        this.responsableOFIC = value;
    }

    /**
     * Obtiene el valor de la propiedad solicitarAnalista.
     * 
     */
    public boolean isSolicitarAnalista() {
        return solicitarAnalista;
    }

    /**
     * Define el valor de la propiedad solicitarAnalista.
     * 
     */
    public void setSolicitarAnalista(boolean value) {
        this.solicitarAnalista = value;
    }

    /**
     * Obtiene el valor de la propiedad aplicaLAFT.
     * 
     */
    public boolean isAplicaLAFT() {
        return aplicaLAFT;
    }

    /**
     * Define el valor de la propiedad aplicaLAFT.
     * 
     */
    public void setAplicaLAFT(boolean value) {
        this.aplicaLAFT = value;
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

}
