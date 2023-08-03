
package org.bcie.aprobacionbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para UsuarioReunion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="UsuarioReunion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="propietario" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dependecia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UsuarioReunion", propOrder = {
    "usuario",
    "nombreUsuario",
    "propietario",
    "dependecia"
})
public class UsuarioReunion {

    @XmlElement(required = true)
    protected String usuario;
    @XmlElement(required = true)
    protected String nombreUsuario;
    protected boolean propietario;
    @XmlElement(required = true)
    protected String dependecia;

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

    /**
     * Obtiene el valor de la propiedad nombreUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Define el valor de la propiedad nombreUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreUsuario(String value) {
        this.nombreUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad propietario.
     * 
     */
    public boolean isPropietario() {
        return propietario;
    }

    /**
     * Define el valor de la propiedad propietario.
     * 
     */
    public void setPropietario(boolean value) {
        this.propietario = value;
    }

    /**
     * Obtiene el valor de la propiedad dependecia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDependecia() {
        return dependecia;
    }

    /**
     * Define el valor de la propiedad dependecia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDependecia(String value) {
        this.dependecia = value;
    }

}
