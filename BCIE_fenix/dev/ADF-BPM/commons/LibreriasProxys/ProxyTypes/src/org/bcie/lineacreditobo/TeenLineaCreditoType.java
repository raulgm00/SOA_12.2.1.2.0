
package org.bcie.lineacreditobo;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.desembolsobo.SaldoCarteraType;


/**
 * <p>Clase Java para TeenLineaCreditoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TeenLineaCreditoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idLineaCredito" type="{http://www.bcie.org/LineaCreditoBO}idLineaCredito" minOccurs="0"/>
 *         &lt;element name="idContrato" type="{http://www.bcie.org/LineaCreditoBO}idContrato" minOccurs="0"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion" minOccurs="0"/>
 *         &lt;element name="NumeroLineaCredito" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Flexcube" type="{http://www.bcie.org/LineaCreditoBO}Flexcube" minOccurs="0"/>
 *         &lt;element name="Fondo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdTipoMonedaMontoLinea" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="MontoLinea" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TeenLineaCreditoType", propOrder = {
    "idLineaCredito",
    "idContrato",
    "idOperacion",
    "numeroLineaCredito",
    "descripcion",
    "flexcube",
    "fondo",
    "idTipoMonedaMontoLinea",
    "montoLinea",
    "saldo",
    "moneda"
})
@XmlSeeAlso({
    LineaCreditoBasicType.class,
    SaldoCarteraType.class
})
public class TeenLineaCreditoType {

    protected Long idLineaCredito;
    protected Long idContrato;
    protected Long idOperacion;
    @XmlElement(name = "NumeroLineaCredito", required = true)
    protected String numeroLineaCredito;
    @XmlElement(name = "Descripcion", required = true)
    protected String descripcion;
    @XmlElement(name = "Flexcube")
    protected Flexcube flexcube;
    @XmlElement(name = "Fondo")
    protected String fondo;
    @XmlElement(name = "IdTipoMonedaMontoLinea")
    protected BigInteger idTipoMonedaMontoLinea;
    @XmlElement(name = "MontoLinea")
    protected BigDecimal montoLinea;
    protected BigDecimal saldo;
    @XmlElement(name = "Moneda")
    protected Catalogo moneda;

    /**
     * Obtiene el valor de la propiedad idLineaCredito.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdLineaCredito() {
        return idLineaCredito;
    }

    /**
     * Define el valor de la propiedad idLineaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdLineaCredito(Long value) {
        this.idLineaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad idContrato.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdContrato() {
        return idContrato;
    }

    /**
     * Define el valor de la propiedad idContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdContrato(Long value) {
        this.idContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdOperacion(Long value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroLineaCredito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroLineaCredito() {
        return numeroLineaCredito;
    }

    /**
     * Define el valor de la propiedad numeroLineaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroLineaCredito(String value) {
        this.numeroLineaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad flexcube.
     * 
     * @return
     *     possible object is
     *     {@link Flexcube }
     *     
     */
    public Flexcube getFlexcube() {
        return flexcube;
    }

    /**
     * Define el valor de la propiedad flexcube.
     * 
     * @param value
     *     allowed object is
     *     {@link Flexcube }
     *     
     */
    public void setFlexcube(Flexcube value) {
        this.flexcube = value;
    }

    /**
     * Obtiene el valor de la propiedad fondo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFondo() {
        return fondo;
    }

    /**
     * Define el valor de la propiedad fondo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFondo(String value) {
        this.fondo = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoMonedaMontoLinea.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdTipoMonedaMontoLinea() {
        return idTipoMonedaMontoLinea;
    }

    /**
     * Define el valor de la propiedad idTipoMonedaMontoLinea.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdTipoMonedaMontoLinea(BigInteger value) {
        this.idTipoMonedaMontoLinea = value;
    }

    /**
     * Obtiene el valor de la propiedad montoLinea.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoLinea() {
        return montoLinea;
    }

    /**
     * Define el valor de la propiedad montoLinea.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoLinea(BigDecimal value) {
        this.montoLinea = value;
    }

    /**
     * Obtiene el valor de la propiedad saldo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * Define el valor de la propiedad saldo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSaldo(BigDecimal value) {
        this.saldo = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setMoneda(Catalogo value) {
        this.moneda = value;
    }

}
