
package org.bcie.matriztccbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para detalleEnmiendaTCC complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="detalleEnmiendaTCC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idEnmiendaTCC" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tipoTCC" type="{http://www.bcie.org/MatrizTCCBO}tipo"/>
 *         &lt;element name="idTCC" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="accion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="estado_formalizacion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detalleEnmiendaTCC", propOrder = {
    "idEnmiendaTCC",
    "tipoTCC",
    "idTCC",
    "accion",
    "estado",
    "estadoFormalizacion"
})
public class DetalleEnmiendaTCC {

    protected long idEnmiendaTCC;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Tipo tipoTCC;
    protected long idTCC;
    @XmlElement(required = true)
    protected String accion;
    protected boolean estado;
    @XmlElement(name = "estado_formalizacion")
    protected boolean estadoFormalizacion;

    /**
     * Obtiene el valor de la propiedad idEnmiendaTCC.
     * 
     */
    public long getIdEnmiendaTCC() {
        return idEnmiendaTCC;
    }

    /**
     * Define el valor de la propiedad idEnmiendaTCC.
     * 
     */
    public void setIdEnmiendaTCC(long value) {
        this.idEnmiendaTCC = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoTCC.
     * 
     * @return
     *     possible object is
     *     {@link Tipo }
     *     
     */
    public Tipo getTipoTCC() {
        return tipoTCC;
    }

    /**
     * Define el valor de la propiedad tipoTCC.
     * 
     * @param value
     *     allowed object is
     *     {@link Tipo }
     *     
     */
    public void setTipoTCC(Tipo value) {
        this.tipoTCC = value;
    }

    /**
     * Obtiene el valor de la propiedad idTCC.
     * 
     */
    public long getIdTCC() {
        return idTCC;
    }

    /**
     * Define el valor de la propiedad idTCC.
     * 
     */
    public void setIdTCC(long value) {
        this.idTCC = value;
    }

    /**
     * Obtiene el valor de la propiedad accion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Define el valor de la propiedad accion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccion(String value) {
        this.accion = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     */
    public void setEstado(boolean value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoFormalizacion.
     * 
     */
    public boolean isEstadoFormalizacion() {
        return estadoFormalizacion;
    }

    /**
     * Define el valor de la propiedad estadoFormalizacion.
     * 
     */
    public void setEstadoFormalizacion(boolean value) {
        this.estadoFormalizacion = value;
    }

}
