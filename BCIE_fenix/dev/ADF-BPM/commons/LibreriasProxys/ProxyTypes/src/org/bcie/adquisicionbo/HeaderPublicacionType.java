
package org.bcie.adquisicionbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para HeaderPublicacionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="HeaderPublicacionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idNoObjecion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nombrePais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroOperacion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="tituloProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modalidadProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderPublicacionType", propOrder = {
    "idNoObjecion",
    "nombrePais",
    "nombreOperacion",
    "numeroOperacion",
    "tituloProceso",
    "numeroProceso",
    "modalidadProceso"
})
public class HeaderPublicacionType {

    protected long idNoObjecion;
    @XmlElement(required = true)
    protected String nombrePais;
    @XmlElement(required = true)
    protected String nombreOperacion;
    protected long numeroOperacion;
    @XmlElement(required = true)
    protected String tituloProceso;
    @XmlElement(required = true)
    protected String numeroProceso;
    @XmlElement(required = true)
    protected String modalidadProceso;

    /**
     * Obtiene el valor de la propiedad idNoObjecion.
     * 
     */
    public long getIdNoObjecion() {
        return idNoObjecion;
    }

    /**
     * Define el valor de la propiedad idNoObjecion.
     * 
     */
    public void setIdNoObjecion(long value) {
        this.idNoObjecion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombrePais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePais() {
        return nombrePais;
    }

    /**
     * Define el valor de la propiedad nombrePais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePais(String value) {
        this.nombrePais = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOperacion() {
        return nombreOperacion;
    }

    /**
     * Define el valor de la propiedad nombreOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOperacion(String value) {
        this.nombreOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroOperacion.
     * 
     */
    public long getNumeroOperacion() {
        return numeroOperacion;
    }

    /**
     * Define el valor de la propiedad numeroOperacion.
     * 
     */
    public void setNumeroOperacion(long value) {
        this.numeroOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad tituloProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloProceso() {
        return tituloProceso;
    }

    /**
     * Define el valor de la propiedad tituloProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloProceso(String value) {
        this.tituloProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroProceso() {
        return numeroProceso;
    }

    /**
     * Define el valor de la propiedad numeroProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroProceso(String value) {
        this.numeroProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad modalidadProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModalidadProceso() {
        return modalidadProceso;
    }

    /**
     * Define el valor de la propiedad modalidadProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModalidadProceso(String value) {
        this.modalidadProceso = value;
    }

}
