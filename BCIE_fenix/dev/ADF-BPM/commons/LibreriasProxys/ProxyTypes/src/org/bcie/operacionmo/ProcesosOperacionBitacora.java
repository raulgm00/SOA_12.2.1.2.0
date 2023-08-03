
package org.bcie.operacionmo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ProcesosOperacionBitacora complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ProcesosOperacionBitacora">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="codigoProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="responsableOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcesosOperacionBitacora", propOrder = {
    "idOperacion",
    "idProceso",
    "codigoProceso",
    "responsableOperacion"
})
public class ProcesosOperacionBitacora {

    protected long idOperacion;
    protected long idProceso;
    @XmlElement(required = true)
    protected String codigoProceso;
    @XmlElement(required = true)
    protected String responsableOperacion;

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
     * Obtiene el valor de la propiedad responsableOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsableOperacion() {
        return responsableOperacion;
    }

    /**
     * Define el valor de la propiedad responsableOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsableOperacion(String value) {
        this.responsableOperacion = value;
    }

}
