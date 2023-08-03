
package org.bcie.eqipotrabajo.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Equipo de trabajo que participó en un proceso
 * 
 * <p>Clase Java para RolEquipoTrabajo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RolEquipoTrabajo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DescripcionRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdRol" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="DescripcionCortaRol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idProceso" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RolEquipoTrabajo", propOrder = {
    "descripcionRol",
    "idRol",
    "descripcionCortaRol",
    "idProceso",
    "usuario"
})
public class RolEquipoTrabajo {

    @XmlElement(name = "DescripcionRol", required = true)
    protected String descripcionRol;
    @XmlElement(name = "IdRol", required = true)
    protected BigDecimal idRol;
    @XmlElement(name = "DescripcionCortaRol", required = true)
    protected String descripcionCortaRol;
    @XmlElement(required = true)
    protected BigDecimal idProceso;
    @XmlElement(required = true)
    protected String usuario;

    /**
     * Obtiene el valor de la propiedad descripcionRol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionRol() {
        return descripcionRol;
    }

    /**
     * Define el valor de la propiedad descripcionRol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionRol(String value) {
        this.descripcionRol = value;
    }

    /**
     * Obtiene el valor de la propiedad idRol.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdRol() {
        return idRol;
    }

    /**
     * Define el valor de la propiedad idRol.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdRol(BigDecimal value) {
        this.idRol = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionCortaRol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionCortaRol() {
        return descripcionCortaRol;
    }

    /**
     * Define el valor de la propiedad descripcionCortaRol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionCortaRol(String value) {
        this.descripcionCortaRol = value;
    }

    /**
     * Obtiene el valor de la propiedad idProceso.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdProceso() {
        return idProceso;
    }

    /**
     * Define el valor de la propiedad idProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdProceso(BigDecimal value) {
        this.idProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

}
