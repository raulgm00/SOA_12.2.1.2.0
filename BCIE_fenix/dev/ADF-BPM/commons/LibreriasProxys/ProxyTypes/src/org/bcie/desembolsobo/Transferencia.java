
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;
import org.bcie.commonbo.MontoType;


/**
 * <p>Clase Java para Transferencia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Transferencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTransferencia" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}DesembolsoIdentificador"/>
 *         &lt;element name="Monto" type="{http://www.bcie.org/CommonBO}MontoType"/>
 *         &lt;element name="FormaTransferencia" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="referenciaMensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="esConsolidada" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="esConsolidacionPadre" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idConsolidacionPadre" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="bhqTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroReserva" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idBancoTransferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nombreBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="tipoMensaje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Beneficiario" type="{http://www.bcie.org/DesembolsoBO}InformacionTransferencia" minOccurs="0"/>
 *         &lt;element name="Banco" type="{http://www.bcie.org/DesembolsoBO}InformacionTransferencia" minOccurs="0"/>
 *         &lt;element name="Intermediario" type="{http://www.bcie.org/DesembolsoBO}InformacionTransferencia" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transferencia", propOrder = {
    "idTransferencia",
    "idDesembolso",
    "idFacturador",
    "monto",
    "formaTransferencia",
    "referenciaMensaje",
    "esConsolidada",
    "esConsolidacionPadre",
    "idConsolidacionPadre",
    "bhqTransferencia",
    "numeroReserva",
    "idBancoTransferencia",
    "numeroCuenta",
    "nombreCuenta",
    "nombreBanco",
    "idOperacion",
    "tipoMensaje",
    "estado",
    "fechaRegistro",
    "beneficiario",
    "banco",
    "intermediario"
})
public class Transferencia {

    protected long idTransferencia;
    protected long idDesembolso;
    protected String idFacturador;
    @XmlElement(name = "Monto", required = true)
    protected MontoType monto;
    @XmlElement(name = "FormaTransferencia", required = true)
    protected Catalogo formaTransferencia;
    protected String referenciaMensaje;
    protected Boolean esConsolidada;
    protected Long esConsolidacionPadre;
    protected Long idConsolidacionPadre;
    protected String bhqTransferencia;
    @XmlElement(name = "NumeroReserva")
    protected Long numeroReserva;
    protected String idBancoTransferencia;
    protected String numeroCuenta;
    @XmlElement(required = true)
    protected String nombreCuenta;
    protected String nombreBanco;
    protected long idOperacion;
    @XmlElement(required = true)
    protected String tipoMensaje;
    protected String estado;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlElement(name = "Beneficiario")
    protected InformacionTransferencia beneficiario;
    @XmlElement(name = "Banco")
    protected InformacionTransferencia banco;
    @XmlElement(name = "Intermediario")
    protected InformacionTransferencia intermediario;

    /**
     * Obtiene el valor de la propiedad idTransferencia.
     * 
     */
    public long getIdTransferencia() {
        return idTransferencia;
    }

    /**
     * Define el valor de la propiedad idTransferencia.
     * 
     */
    public void setIdTransferencia(long value) {
        this.idTransferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad idDesembolso.
     * 
     */
    public long getIdDesembolso() {
        return idDesembolso;
    }

    /**
     * Define el valor de la propiedad idDesembolso.
     * 
     */
    public void setIdDesembolso(long value) {
        this.idDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad idFacturador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFacturador() {
        return idFacturador;
    }

    /**
     * Define el valor de la propiedad idFacturador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFacturador(String value) {
        this.idFacturador = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     * @return
     *     possible object is
     *     {@link MontoType }
     *     
     */
    public MontoType getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoType }
     *     
     */
    public void setMonto(MontoType value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad formaTransferencia.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getFormaTransferencia() {
        return formaTransferencia;
    }

    /**
     * Define el valor de la propiedad formaTransferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setFormaTransferencia(Catalogo value) {
        this.formaTransferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad referenciaMensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenciaMensaje() {
        return referenciaMensaje;
    }

    /**
     * Define el valor de la propiedad referenciaMensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenciaMensaje(String value) {
        this.referenciaMensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad esConsolidada.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsConsolidada() {
        return esConsolidada;
    }

    /**
     * Define el valor de la propiedad esConsolidada.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsConsolidada(Boolean value) {
        this.esConsolidada = value;
    }

    /**
     * Obtiene el valor de la propiedad esConsolidacionPadre.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEsConsolidacionPadre() {
        return esConsolidacionPadre;
    }

    /**
     * Define el valor de la propiedad esConsolidacionPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEsConsolidacionPadre(Long value) {
        this.esConsolidacionPadre = value;
    }

    /**
     * Obtiene el valor de la propiedad idConsolidacionPadre.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdConsolidacionPadre() {
        return idConsolidacionPadre;
    }

    /**
     * Define el valor de la propiedad idConsolidacionPadre.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdConsolidacionPadre(Long value) {
        this.idConsolidacionPadre = value;
    }

    /**
     * Obtiene el valor de la propiedad bhqTransferencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBhqTransferencia() {
        return bhqTransferencia;
    }

    /**
     * Define el valor de la propiedad bhqTransferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBhqTransferencia(String value) {
        this.bhqTransferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroReserva.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumeroReserva() {
        return numeroReserva;
    }

    /**
     * Define el valor de la propiedad numeroReserva.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumeroReserva(Long value) {
        this.numeroReserva = value;
    }

    /**
     * Obtiene el valor de la propiedad idBancoTransferencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdBancoTransferencia() {
        return idBancoTransferencia;
    }

    /**
     * Define el valor de la propiedad idBancoTransferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdBancoTransferencia(String value) {
        this.idBancoTransferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Define el valor de la propiedad numeroCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCuenta(String value) {
        this.numeroCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCuenta() {
        return nombreCuenta;
    }

    /**
     * Define el valor de la propiedad nombreCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCuenta(String value) {
        this.nombreCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreBanco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreBanco() {
        return nombreBanco;
    }

    /**
     * Define el valor de la propiedad nombreBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreBanco(String value) {
        this.nombreBanco = value;
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
     * Obtiene el valor de la propiedad tipoMensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMensaje() {
        return tipoMensaje;
    }

    /**
     * Define el valor de la propiedad tipoMensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMensaje(String value) {
        this.tipoMensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
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

    /**
     * Obtiene el valor de la propiedad beneficiario.
     * 
     * @return
     *     possible object is
     *     {@link InformacionTransferencia }
     *     
     */
    public InformacionTransferencia getBeneficiario() {
        return beneficiario;
    }

    /**
     * Define el valor de la propiedad beneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionTransferencia }
     *     
     */
    public void setBeneficiario(InformacionTransferencia value) {
        this.beneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad banco.
     * 
     * @return
     *     possible object is
     *     {@link InformacionTransferencia }
     *     
     */
    public InformacionTransferencia getBanco() {
        return banco;
    }

    /**
     * Define el valor de la propiedad banco.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionTransferencia }
     *     
     */
    public void setBanco(InformacionTransferencia value) {
        this.banco = value;
    }

    /**
     * Obtiene el valor de la propiedad intermediario.
     * 
     * @return
     *     possible object is
     *     {@link InformacionTransferencia }
     *     
     */
    public InformacionTransferencia getIntermediario() {
        return intermediario;
    }

    /**
     * Define el valor de la propiedad intermediario.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionTransferencia }
     *     
     */
    public void setIntermediario(InformacionTransferencia value) {
        this.intermediario = value;
    }

}
