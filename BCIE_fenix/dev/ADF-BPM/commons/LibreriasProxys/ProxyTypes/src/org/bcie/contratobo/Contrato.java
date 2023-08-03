
package org.bcie.contratobo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.lineacreditobo.LineaCredito;


/**
 * <p>Clase Java para Contrato complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Contrato">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idContrato" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="fechaFirma" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="fechaVigencia" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="idTipoMonedaMontoEscriturado" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="MontoEscriturado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="cuentaCliente" type="{http://www.bcie.org/ContratoBO}Cuentas"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaValor" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="LineaCredito" type="{http://www.bcie.org/LineaCreditoBO}LineaCredito" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contrato", propOrder = {
    "idContrato",
    "idOperacion",
    "fechaFirma",
    "fechaVigencia",
    "idTipoMonedaMontoEscriturado",
    "montoEscriturado",
    "cuentaCliente",
    "instanciaProceso",
    "fechaValor",
    "lineaCredito"
})
public class Contrato {

    protected int idContrato;
    protected long idOperacion;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFirma;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaVigencia;
    protected BigInteger idTipoMonedaMontoEscriturado;
    @XmlElement(name = "MontoEscriturado", required = true)
    protected BigDecimal montoEscriturado;
    @XmlElement(required = true)
    protected Cuentas cuentaCliente;
    @XmlElement(required = true)
    protected String instanciaProceso;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaValor;
    @XmlElement(name = "LineaCredito", required = true)
    protected List<LineaCredito> lineaCredito;

    /**
     * Obtiene el valor de la propiedad idContrato.
     * 
     */
    public int getIdContrato() {
        return idContrato;
    }

    /**
     * Define el valor de la propiedad idContrato.
     * 
     */
    public void setIdContrato(int value) {
        this.idContrato = value;
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
     * Obtiene el valor de la propiedad fechaFirma.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFirma() {
        return fechaFirma;
    }

    /**
     * Define el valor de la propiedad fechaFirma.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFirma(XMLGregorianCalendar value) {
        this.fechaFirma = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVigencia.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * Define el valor de la propiedad fechaVigencia.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaVigencia(XMLGregorianCalendar value) {
        this.fechaVigencia = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoMonedaMontoEscriturado.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdTipoMonedaMontoEscriturado() {
        return idTipoMonedaMontoEscriturado;
    }

    /**
     * Define el valor de la propiedad idTipoMonedaMontoEscriturado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdTipoMonedaMontoEscriturado(BigInteger value) {
        this.idTipoMonedaMontoEscriturado = value;
    }

    /**
     * Obtiene el valor de la propiedad montoEscriturado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoEscriturado() {
        return montoEscriturado;
    }

    /**
     * Define el valor de la propiedad montoEscriturado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoEscriturado(BigDecimal value) {
        this.montoEscriturado = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaCliente.
     * 
     * @return
     *     possible object is
     *     {@link Cuentas }
     *     
     */
    public Cuentas getCuentaCliente() {
        return cuentaCliente;
    }

    /**
     * Define el valor de la propiedad cuentaCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Cuentas }
     *     
     */
    public void setCuentaCliente(Cuentas value) {
        this.cuentaCliente = value;
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
     * Obtiene el valor de la propiedad fechaValor.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaValor() {
        return fechaValor;
    }

    /**
     * Define el valor de la propiedad fechaValor.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaValor(XMLGregorianCalendar value) {
        this.fechaValor = value;
    }

    /**
     * Gets the value of the lineaCredito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineaCredito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineaCredito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineaCredito }
     * 
     * 
     */
    public List<LineaCredito> getLineaCredito() {
        if (lineaCredito == null) {
            lineaCredito = new ArrayList<LineaCredito>();
        }
        return this.lineaCredito;
    }

}
