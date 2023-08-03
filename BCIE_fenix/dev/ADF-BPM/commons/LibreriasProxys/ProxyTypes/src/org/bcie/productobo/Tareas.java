
package org.bcie.productobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Entidad Tareas - (Modelo canónico)
 *           Se usa para la administración de los Tareas del banco
 * 			  
 * 
 * <p>Clase Java para Tareas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tareas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTarea" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionCorta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idProcesoBpm" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tareas", propOrder = {
    "idTarea",
    "descripcion",
    "descripcionCorta",
    "idProcesoBpm"
})
public class Tareas {

    protected Long idTarea;
    protected String descripcion;
    protected String descripcionCorta;
    protected Long idProcesoBpm;

    /**
     * Obtiene el valor de la propiedad idTarea.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTarea() {
        return idTarea;
    }

    /**
     * Define el valor de la propiedad idTarea.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTarea(Long value) {
        this.idTarea = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionCorta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    /**
     * Define el valor de la propiedad descripcionCorta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionCorta(String value) {
        this.descripcionCorta = value;
    }

    /**
     * Obtiene el valor de la propiedad idProcesoBpm.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdProcesoBpm() {
        return idProcesoBpm;
    }

    /**
     * Define el valor de la propiedad idProcesoBpm.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdProcesoBpm(Long value) {
        this.idProcesoBpm = value;
    }

}
