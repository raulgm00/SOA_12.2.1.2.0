
package org.bcie.condicionbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para ValidacionCondicion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidacionCondicion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCondicion" type="{http://www.bcie.org/CondicionBO}idCondicion"/>
 *         &lt;element name="rolBPM" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="loginUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observacion" type="{http://www.bcie.org/CondicionBO}ObservacionCondicion" minOccurs="0"/>
 *         &lt;element name="esValidador" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidacionCondicion", propOrder = {
    "idCondicion",
    "rolBPM",
    "loginUsuario",
    "nombreUsuario",
    "observacion",
    "esValidador",
    "estado",
    "fechaRegistro"
})
public class ValidacionCondicion {

    protected long idCondicion;
    protected Catalogo rolBPM;
    protected String loginUsuario;
    protected String nombreUsuario;
    protected ObservacionCondicion observacion;
    protected Boolean esValidador;
    protected Boolean estado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;

    /**
     * Obtiene el valor de la propiedad idCondicion.
     * 
     */
    public long getIdCondicion() {
        return idCondicion;
    }

    /**
     * Define el valor de la propiedad idCondicion.
     * 
     */
    public void setIdCondicion(long value) {
        this.idCondicion = value;
    }

    /**
     * Obtiene el valor de la propiedad rolBPM.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getRolBPM() {
        return rolBPM;
    }

    /**
     * Define el valor de la propiedad rolBPM.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setRolBPM(Catalogo value) {
        this.rolBPM = value;
    }

    /**
     * Obtiene el valor de la propiedad loginUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginUsuario() {
        return loginUsuario;
    }

    /**
     * Define el valor de la propiedad loginUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginUsuario(String value) {
        this.loginUsuario = value;
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
     * Obtiene el valor de la propiedad observacion.
     * 
     * @return
     *     possible object is
     *     {@link ObservacionCondicion }
     *     
     */
    public ObservacionCondicion getObservacion() {
        return observacion;
    }

    /**
     * Define el valor de la propiedad observacion.
     * 
     * @param value
     *     allowed object is
     *     {@link ObservacionCondicion }
     *     
     */
    public void setObservacion(ObservacionCondicion value) {
        this.observacion = value;
    }

    /**
     * Obtiene el valor de la propiedad esValidador.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsValidador() {
        return esValidador;
    }

    /**
     * Define el valor de la propiedad esValidador.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsValidador(Boolean value) {
        this.esValidador = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstado(Boolean value) {
        this.estado = value;
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

}
