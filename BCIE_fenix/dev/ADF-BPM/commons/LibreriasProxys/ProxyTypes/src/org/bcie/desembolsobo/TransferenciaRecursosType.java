
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
 * <p>Clase Java para TransferenciaRecursosType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TransferenciaRecursosType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idTransferencia" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}DesembolsoIdentificador"/>
 *         &lt;element name="idLineaPasiva" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Monto" type="{http://www.bcie.org/CommonBO}MontoType"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="fechaEfectiva" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="FormaTransferencia" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="idBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreBanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransferenciaRecursosType", propOrder = {
    "idTransferencia",
    "idDesembolso",
    "idFacturador",
    "idLineaPasiva",
    "monto",
    "fecha",
    "fechaEfectiva",
    "formaTransferencia",
    "idBanco",
    "nombreBanco",
    "numeroCuenta",
    "nombreCuenta",
    "estatus",
    "fechaRegistro"
})
public class TransferenciaRecursosType {

    protected long idTransferencia;
    protected long idDesembolso;
    protected String idFacturador;
    protected int idLineaPasiva;
    @XmlElement(name = "Monto", required = true)
    protected MontoType monto;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fecha;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaEfectiva;
    @XmlElement(name = "FormaTransferencia", required = true)
    protected Catalogo formaTransferencia;
    protected String idBanco;
    protected String nombreBanco;
    protected String numeroCuenta;
    protected String nombreCuenta;
    protected Boolean estatus;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;

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
     * Obtiene el valor de la propiedad idLineaPasiva.
     * 
     */
    public int getIdLineaPasiva() {
        return idLineaPasiva;
    }

    /**
     * Define el valor de la propiedad idLineaPasiva.
     * 
     */
    public void setIdLineaPasiva(int value) {
        this.idLineaPasiva = value;
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
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaEfectiva.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEfectiva() {
        return fechaEfectiva;
    }

    /**
     * Define el valor de la propiedad fechaEfectiva.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEfectiva(XMLGregorianCalendar value) {
        this.fechaEfectiva = value;
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
     * Obtiene el valor de la propiedad idBanco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdBanco() {
        return idBanco;
    }

    /**
     * Define el valor de la propiedad idBanco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdBanco(String value) {
        this.idBanco = value;
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
     * Obtiene el valor de la propiedad estatus.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstatus() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstatus(Boolean value) {
        this.estatus = value;
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
