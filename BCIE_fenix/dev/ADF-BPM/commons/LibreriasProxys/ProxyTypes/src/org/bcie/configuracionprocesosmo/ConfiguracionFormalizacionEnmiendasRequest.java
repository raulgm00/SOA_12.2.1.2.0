
package org.bcie.configuracionprocesosmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConfiguracionFormalizacionEnmiendasRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionFormalizacionEnmiendasRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="idEnmienda" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idTarea" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionFormalizacionEnmiendasRequest", propOrder = {
    "idOperacion",
    "idEnmienda",
    "idProceso",
    "idTarea"
})
public class ConfiguracionFormalizacionEnmiendasRequest {

    protected long idOperacion;
    protected long idEnmienda;
    protected long idProceso;
    protected long idTarea;

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     */
    public long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     */
    public void setIdOperacion(long value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad idEnmienda.
     * 
     */
    public long getIdEnmienda() {
        return idEnmienda;
    }

    /**
     * Define el valor de la propiedad idEnmienda.
     * 
     */
    public void setIdEnmienda(long value) {
        this.idEnmienda = value;
    }

    /**
     * Obtiene el valor de la propiedad idProceso.
     * 
     */
    public long getIdProceso() {
        return idProceso;
    }

    /**
     * Define el valor de la propiedad idProceso.
     * 
     */
    public void setIdProceso(long value) {
        this.idProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad idTarea.
     * 
     */
    public long getIdTarea() {
        return idTarea;
    }

    /**
     * Define el valor de la propiedad idTarea.
     * 
     */
    public void setIdTarea(long value) {
        this.idTarea = value;
    }

}
