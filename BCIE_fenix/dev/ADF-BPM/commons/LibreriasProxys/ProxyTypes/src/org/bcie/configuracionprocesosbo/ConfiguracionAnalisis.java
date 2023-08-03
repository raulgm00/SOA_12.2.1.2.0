
package org.bcie.configuracionprocesosbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.operacionbo.Operacion;
import org.bcie.productobo.Producto;


/**
 * Elemento para la configuración inicial del proceso de Analisis
 * 
 * <p>Clase Java para ConfiguracionAnalisis complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionAnalisis">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *         &lt;element name="producto" type="{http://www.bcie.org/ProductoBO}Producto"/>
 *         &lt;element name="esIFI" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereSCR" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "ConfiguracionAnalisis", propOrder = {
    "operacion",
    "producto",
    "esIFI",
    "requiereSCR",
    "rolesEquipoTrabajo"
})
public class ConfiguracionAnalisis {

    @XmlElement(required = true)
    protected Operacion operacion;
    @XmlElement(required = true)
    protected Producto producto;
    protected boolean esIFI;
    protected boolean requiereSCR;
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
     * Obtiene el valor de la propiedad esIFI.
     * 
     */
    public boolean isEsIFI() {
        return esIFI;
    }

    /**
     * Define el valor de la propiedad esIFI.
     * 
     */
    public void setEsIFI(boolean value) {
        this.esIFI = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereSCR.
     * 
     */
    public boolean isRequiereSCR() {
        return requiereSCR;
    }

    /**
     * Define el valor de la propiedad requiereSCR.
     * 
     */
    public void setRequiereSCR(boolean value) {
        this.requiereSCR = value;
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
