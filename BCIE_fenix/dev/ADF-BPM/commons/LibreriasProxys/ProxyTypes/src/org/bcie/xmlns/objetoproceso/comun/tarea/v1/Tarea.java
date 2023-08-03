
package org.bcie.xmlns.objetoproceso.comun.tarea.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Entidad que contiene la información basica de una tarea BPM.
 * 
 * <p>Clase Java para Tarea complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Tarea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoTarea" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NombreTarea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodigoRol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreRol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoProceso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NombreProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tarea", propOrder = {
    "codigoTarea",
    "nombreTarea",
    "codigoRol",
    "nombreRol",
    "codigoProceso",
    "nombreProceso"
})
public class Tarea {

    @XmlElement(name = "CodigoTarea")
    protected int codigoTarea;
    @XmlElement(name = "NombreTarea", required = true)
    protected String nombreTarea;
    @XmlElement(name = "CodigoRol")
    protected String codigoRol;
    @XmlElement(name = "NombreRol")
    protected String nombreRol;
    @XmlElement(name = "CodigoProceso")
    protected Integer codigoProceso;
    @XmlElement(name = "NombreProceso")
    protected String nombreProceso;

    /**
     * Obtiene el valor de la propiedad codigoTarea.
     * 
     */
    public int getCodigoTarea() {
        return codigoTarea;
    }

    /**
     * Define el valor de la propiedad codigoTarea.
     * 
     */
    public void setCodigoTarea(int value) {
        this.codigoTarea = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreTarea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTarea() {
        return nombreTarea;
    }

    /**
     * Define el valor de la propiedad nombreTarea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTarea(String value) {
        this.nombreTarea = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoRol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRol() {
        return codigoRol;
    }

    /**
     * Define el valor de la propiedad codigoRol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRol(String value) {
        this.codigoRol = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreRol.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * Define el valor de la propiedad nombreRol.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreRol(String value) {
        this.nombreRol = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoProceso.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoProceso() {
        return codigoProceso;
    }

    /**
     * Define el valor de la propiedad codigoProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoProceso(Integer value) {
        this.codigoProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreProceso() {
        return nombreProceso;
    }

    /**
     * Define el valor de la propiedad nombreProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreProceso(String value) {
        this.nombreProceso = value;
    }

}
