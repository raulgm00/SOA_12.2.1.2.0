//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.25 at 11:19:22 AM CDT 
//


package www.bcie.org.prepago;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrePagoReporte complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrePagoReporte">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoReporte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaEmision" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Operacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaPrePago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DatosPrePago" type="{http://www.bcie.org/herramientaOfismatica/prepago}DatosPrePago" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ConsolidadoPago" type="{http://www.bcie.org/herramientaOfismatica/prepago}ConsolidadoPago" maxOccurs="unbounded"/>
 *         &lt;element name="Comentario1" type="{http://www.bcie.org/herramientaOfismatica/prepago}comentario1" maxOccurs="unbounded"/>
 *         &lt;element name="lstBancos" type="{http://www.bcie.org/herramientaOfismatica/prepago}DatosBanco" maxOccurs="unbounded"/>
 *         &lt;element name="Comentario2" type="{http://www.bcie.org/herramientaOfismatica/prepago}comentario2" maxOccurs="unbounded"/>
 *         &lt;element name="detalleCalculo" type="{http://www.bcie.org/herramientaOfismatica/prepago}DetalleCalculos" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrePagoReporte", propOrder = {
    "tipoReporte",
    "fechaEmision",
    "pais",
    "cliente",
    "operacion",
    "moneda",
    "fechaPrePago",
    "datosPrePago",
    "consolidadoPago",
    "comentario1",
    "lstBancos",
    "comentario2",
    "detalleCalculo"
})
public class PrePagoReporte {

    @XmlElement(required = true)
    protected String tipoReporte;
    @XmlElement(required = true)
    protected String fechaEmision;
    @XmlElement(name = "Pais", required = true)
    protected String pais;
    @XmlElement(name = "Cliente", required = true)
    protected String cliente;
    @XmlElement(name = "Operacion", required = true)
    protected String operacion;
    @XmlElement(name = "Moneda", required = true)
    protected String moneda;
    @XmlElement(name = "FechaPrePago", required = true)
    protected String fechaPrePago;
    @XmlElement(name = "DatosPrePago")
    protected List<DatosPrePago> datosPrePago;
    @XmlElement(name = "ConsolidadoPago", required = true)
    protected List<ConsolidadoPago> consolidadoPago;
    @XmlElement(name = "Comentario1", required = true)
    protected List<Comentario1> comentario1;
    @XmlElement(required = true)
    protected List<DatosBanco> lstBancos;
    @XmlElement(name = "Comentario2", required = true)
    protected List<Comentario2> comentario2;
    @XmlElement(required = true)
    protected List<DetalleCalculos> detalleCalculo;

    /**
     * Gets the value of the tipoReporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoReporte() {
        return tipoReporte;
    }

    /**
     * Sets the value of the tipoReporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoReporte(String value) {
        this.tipoReporte = value;
    }

    /**
     * Gets the value of the fechaEmision property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Sets the value of the fechaEmision property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEmision(String value) {
        this.fechaEmision = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the cliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Sets the value of the cliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCliente(String value) {
        this.cliente = value;
    }

    /**
     * Gets the value of the operacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * Sets the value of the operacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperacion(String value) {
        this.operacion = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the fechaPrePago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPrePago() {
        return fechaPrePago;
    }

    /**
     * Sets the value of the fechaPrePago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPrePago(String value) {
        this.fechaPrePago = value;
    }

    /**
     * Gets the value of the datosPrePago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datosPrePago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatosPrePago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatosPrePago }
     * 
     * 
     */
    public List<DatosPrePago> getDatosPrePago() {
        if (datosPrePago == null) {
            datosPrePago = new ArrayList<DatosPrePago>();
        }
        return this.datosPrePago;
    }

    /**
     * Gets the value of the consolidadoPago property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the consolidadoPago property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConsolidadoPago().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConsolidadoPago }
     * 
     * 
     */
    public List<ConsolidadoPago> getConsolidadoPago() {
        if (consolidadoPago == null) {
            consolidadoPago = new ArrayList<ConsolidadoPago>();
        }
        return this.consolidadoPago;
    }

    /**
     * Gets the value of the comentario1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comentario1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComentario1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comentario1 }
     * 
     * 
     */
    public List<Comentario1> getComentario1() {
        if (comentario1 == null) {
            comentario1 = new ArrayList<Comentario1>();
        }
        return this.comentario1;
    }

    /**
     * Gets the value of the lstBancos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lstBancos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLstBancos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatosBanco }
     * 
     * 
     */
    public List<DatosBanco> getLstBancos() {
        if (lstBancos == null) {
            lstBancos = new ArrayList<DatosBanco>();
        }
        return this.lstBancos;
    }

    /**
     * Gets the value of the comentario2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comentario2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComentario2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comentario2 }
     * 
     * 
     */
    public List<Comentario2> getComentario2() {
        if (comentario2 == null) {
            comentario2 = new ArrayList<Comentario2>();
        }
        return this.comentario2;
    }

    /**
     * Gets the value of the detalleCalculo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleCalculo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleCalculo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleCalculos }
     * 
     * 
     */
    public List<DetalleCalculos> getDetalleCalculo() {
        if (detalleCalculo == null) {
            detalleCalculo = new ArrayList<DetalleCalculos>();
        }
        return this.detalleCalculo;
    }

}
