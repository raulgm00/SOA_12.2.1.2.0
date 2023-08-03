
package org.bcie.configuracionprocesosbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.crearbitacoraprocesobo.ListaBitacora;


/**
 * <p>Clase Java para ConfiguracionReasignarCliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionReasignarCliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GestionCobro" type="{http://www.bcie.org/CrearBitacoraProcesoBO}ListaBitacora"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionReasignarCliente", propOrder = {
    "gestionCobro"
})
public class ConfiguracionReasignarCliente {

    @XmlElement(name = "GestionCobro", required = true)
    protected ListaBitacora gestionCobro;

    /**
     * Obtiene el valor de la propiedad gestionCobro.
     * 
     * @return
     *     possible object is
     *     {@link ListaBitacora }
     *     
     */
    public ListaBitacora getGestionCobro() {
        return gestionCobro;
    }

    /**
     * Define el valor de la propiedad gestionCobro.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaBitacora }
     *     
     */
    public void setGestionCobro(ListaBitacora value) {
        this.gestionCobro = value;
    }

}
