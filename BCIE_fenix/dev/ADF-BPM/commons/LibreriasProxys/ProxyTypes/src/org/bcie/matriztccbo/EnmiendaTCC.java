
package org.bcie.matriztccbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para enmiendaTCC complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="enmiendaTCC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idEnmienda" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requiereOGC" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereASJUR" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="estado_formalizacion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereCOF" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="esDesobligacion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="esCambioPlazo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="detalleEnmiendaTCC" type="{http://www.bcie.org/MatrizTCCBO}detalleEnmiendaTCC" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enmiendaTCC", propOrder = {
    "idEnmienda",
    "idOperacion",
    "instanciaProceso",
    "requiereOGC",
    "requiereASJUR",
    "fechaRegistro",
    "estado",
    "estadoFormalizacion",
    "requiereCOF",
    "esDesobligacion",
    "esCambioPlazo",
    "detalleEnmiendaTCC"
})
public class EnmiendaTCC {

    protected long idEnmienda;
    protected long idOperacion;
    @XmlElement(required = true)
    protected String instanciaProceso;
    protected boolean requiereOGC;
    protected boolean requiereASJUR;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected boolean estado;
    @XmlElement(name = "estado_formalizacion")
    protected boolean estadoFormalizacion;
    protected boolean requiereCOF;
    protected boolean esDesobligacion;
    protected boolean esCambioPlazo;
    protected List<DetalleEnmiendaTCC> detalleEnmiendaTCC;

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
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanciaProceso(String value) {
        this.instanciaProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereOGC.
     * 
     */
    public boolean isRequiereOGC() {
        return requiereOGC;
    }

    /**
     * Define el valor de la propiedad requiereOGC.
     * 
     */
    public void setRequiereOGC(boolean value) {
        this.requiereOGC = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereASJUR.
     * 
     */
    public boolean isRequiereASJUR() {
        return requiereASJUR;
    }

    /**
     * Define el valor de la propiedad requiereASJUR.
     * 
     */
    public void setRequiereASJUR(boolean value) {
        this.requiereASJUR = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaRegistro.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Define el valor de la propiedad fechaRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRegistro(XMLGregorianCalendar value) {
        this.fechaRegistro = value;
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

    /**
     * Obtiene el valor de la propiedad requiereCOF.
     * 
     */
    public boolean isRequiereCOF() {
        return requiereCOF;
    }

    /**
     * Define el valor de la propiedad requiereCOF.
     * 
     */
    public void setRequiereCOF(boolean value) {
        this.requiereCOF = value;
    }

    /**
     * Obtiene el valor de la propiedad esDesobligacion.
     * 
     */
    public boolean isEsDesobligacion() {
        return esDesobligacion;
    }

    /**
     * Define el valor de la propiedad esDesobligacion.
     * 
     */
    public void setEsDesobligacion(boolean value) {
        this.esDesobligacion = value;
    }

    /**
     * Obtiene el valor de la propiedad esCambioPlazo.
     * 
     */
    public boolean isEsCambioPlazo() {
        return esCambioPlazo;
    }

    /**
     * Define el valor de la propiedad esCambioPlazo.
     * 
     */
    public void setEsCambioPlazo(boolean value) {
        this.esCambioPlazo = value;
    }

    /**
     * Gets the value of the detalleEnmiendaTCC property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleEnmiendaTCC property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleEnmiendaTCC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleEnmiendaTCC }
     * 
     * 
     */
    public List<DetalleEnmiendaTCC> getDetalleEnmiendaTCC() {
        if (detalleEnmiendaTCC == null) {
            detalleEnmiendaTCC = new ArrayList<DetalleEnmiendaTCC>();
        }
        return this.detalleEnmiendaTCC;
    }

}
