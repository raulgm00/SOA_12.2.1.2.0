
package org.bcie.xmlns.objetoproceso.comun.proceso.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Entidad que contiene la información basica de un proceso BPM.
 * 
 * <p>Clase Java para Proceso complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Proceso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdProceso" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CodigoProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EsProcesoCore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RolIniciaProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsuarioIniciaProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InstanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdFlujo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Proceso", propOrder = {
    "idProceso",
    "codigoProceso",
    "nombreProceso",
    "esProcesoCore",
    "rolIniciaProceso",
    "usuarioIniciaProceso",
    "instanciaProceso",
    "idFlujo"
})
public class Proceso {

    @XmlElement(name = "IdProceso")
    protected int idProceso;
    @XmlElement(name = "CodigoProceso")
    protected String codigoProceso;
    @XmlElement(name = "NombreProceso")
    protected String nombreProceso;
    @XmlElement(name = "EsProcesoCore")
    protected Boolean esProcesoCore;
    @XmlElement(name = "RolIniciaProceso")
    protected String rolIniciaProceso;
    @XmlElement(name = "UsuarioIniciaProceso")
    protected String usuarioIniciaProceso;
    @XmlElement(name = "InstanciaProceso")
    protected String instanciaProceso;
    @XmlElement(name = "IdFlujo")
    protected Long idFlujo;

    /**
     * Obtiene el valor de la propiedad idProceso.
     * 
     */
    public int getIdProceso() {
        return idProceso;
    }

    /**
     * Define el valor de la propiedad idProceso.
     * 
     */
    public void setIdProceso(int value) {
        this.idProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProceso() {
        return codigoProceso;
    }

    /**
     * Define el valor de la propiedad codigoProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProceso(String value) {
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

    /**
     * Obtiene el valor de la propiedad esProcesoCore.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsProcesoCore() {
        return esProcesoCore;
    }

    /**
     * Define el valor de la propiedad esProcesoCore.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsProcesoCore(Boolean value) {
        this.esProcesoCore = value;
    }

    /**
     * Obtiene el valor de la propiedad rolIniciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRolIniciaProceso() {
        return rolIniciaProceso;
    }

    /**
     * Define el valor de la propiedad rolIniciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRolIniciaProceso(String value) {
        this.rolIniciaProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioIniciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioIniciaProceso() {
        return usuarioIniciaProceso;
    }

    /**
     * Define el valor de la propiedad usuarioIniciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioIniciaProceso(String value) {
        this.usuarioIniciaProceso = value;
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
     * Obtiene el valor de la propiedad idFlujo.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdFlujo() {
        return idFlujo;
    }

    /**
     * Define el valor de la propiedad idFlujo.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdFlujo(Long value) {
        this.idFlujo = value;
    }

}
