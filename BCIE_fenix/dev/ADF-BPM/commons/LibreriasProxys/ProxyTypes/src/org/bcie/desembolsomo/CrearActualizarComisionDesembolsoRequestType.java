
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para CrearActualizarComisionDesembolsoRequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearActualizarComisionDesembolsoRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idComisionDesembolso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="idComision" type="{http://www.bcie.org/ComisionBO}idComision"/>
 *         &lt;element name="codigoBHQ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="porcentaje" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="montoComision" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearActualizarComisionDesembolsoRequestType", propOrder = {
    "idComisionDesembolso",
    "idContratoDesembolso",
    "idComision",
    "codigoBHQ",
    "porcentaje",
    "montoComision",
    "fechaRegistro",
    "estado"
})
public class CrearActualizarComisionDesembolsoRequestType {

    protected long idComisionDesembolso;
    protected long idContratoDesembolso;
    protected long idComision;
    @XmlElement(required = true)
    protected String codigoBHQ;
    protected Double porcentaje;
    protected double montoComision;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    protected boolean estado;

    /**
     * Obtiene el valor de la propiedad idComisionDesembolso.
     * 
     */
    public long getIdComisionDesembolso() {
        return idComisionDesembolso;
    }

    /**
     * Define el valor de la propiedad idComisionDesembolso.
     * 
     */
    public void setIdComisionDesembolso(long value) {
        this.idComisionDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad idContratoDesembolso.
     * 
     */
    public long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }

    /**
     * Define el valor de la propiedad idContratoDesembolso.
     * 
     */
    public void setIdContratoDesembolso(long value) {
        this.idContratoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad idComision.
     * 
     */
    public long getIdComision() {
        return idComision;
    }

    /**
     * Define el valor de la propiedad idComision.
     * 
     */
    public void setIdComision(long value) {
        this.idComision = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoBHQ.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoBHQ() {
        return codigoBHQ;
    }

    /**
     * Define el valor de la propiedad codigoBHQ.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoBHQ(String value) {
        this.codigoBHQ = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentaje.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPorcentaje() {
        return porcentaje;
    }

    /**
     * Define el valor de la propiedad porcentaje.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPorcentaje(Double value) {
        this.porcentaje = value;
    }

    /**
     * Obtiene el valor de la propiedad montoComision.
     * 
     */
    public double getMontoComision() {
        return montoComision;
    }

    /**
     * Define el valor de la propiedad montoComision.
     * 
     */
    public void setMontoComision(double value) {
        this.montoComision = value;
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

}
