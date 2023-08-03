
package org.bcie.configuracionprocesosbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.xmlns.objetoproceso.comun.header.v1.Header;


/**
 * <p>Clase Java para ConfiguracionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1}Header"/>
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
@XmlType(name = "ConfiguracionType", propOrder = {
    "header",
    "rolesEquipoTrabajo"
})
public class ConfiguracionType {

    @XmlElement(name = "Header", required = true)
    protected Header header;
    @XmlElement(name = "RolesEquipoTrabajo", required = true)
    protected EquipoTrabajo rolesEquipoTrabajo;

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
