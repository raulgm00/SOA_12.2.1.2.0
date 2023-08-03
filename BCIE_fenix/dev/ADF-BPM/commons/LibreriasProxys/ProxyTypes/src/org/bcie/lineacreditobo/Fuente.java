
package org.bcie.lineacreditobo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.commonbo.MontoType;


/**
 * <p>Clase Java para Fuente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Fuente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idFuente" type="{http://www.bcie.org/LineaCreditoBO}idFuente"/>
 *         &lt;element name="idLineaCredito" type="{http://www.bcie.org/LineaCreditoBO}idLineaCredito"/>
 *         &lt;element name="idLineaPasiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoLineaPasiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idFacturadorLineaPasiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idFondo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaObtenido" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="MontoAsignado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="montoDisponible" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Monto" type="{http://www.bcie.org/CommonBO}MontoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="NumeroAsignacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Comentario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idContrato" type="{http://www.bcie.org/LineaCreditoBO}idContrato"/>
 *         &lt;element name="FechaRegistro" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="esExterna" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fuente", propOrder = {
    "idFuente",
    "idLineaCredito",
    "idLineaPasiva",
    "codigoLineaPasiva",
    "idFacturadorLineaPasiva",
    "idFondo",
    "descripcion",
    "fechaObtenido",
    "montoAsignado",
    "montoDisponible",
    "monto",
    "numeroAsignacion",
    "comentario",
    "idContrato",
    "fechaRegistro",
    "estado",
    "esExterna"
})
public class Fuente {

    protected long idFuente;
    protected long idLineaCredito;
    @XmlElement(required = true)
    protected String idLineaPasiva;
    @XmlElement(required = true)
    protected String codigoLineaPasiva;
    @XmlElement(required = true)
    protected String idFacturadorLineaPasiva;
    @XmlElement(required = true)
    protected String idFondo;
    @XmlElement(name = "Descripcion", required = true)
    protected String descripcion;
    @XmlElement(name = "FechaObtenido", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaObtenido;
    @XmlElement(name = "MontoAsignado", required = true)
    protected BigDecimal montoAsignado;
    protected double montoDisponible;
    @XmlElement(name = "Monto")
    protected List<MontoType> monto;
    @XmlElement(name = "NumeroAsignacion", required = true)
    protected BigDecimal numeroAsignacion;
    @XmlElement(name = "Comentario", required = true)
    protected String comentario;
    protected long idContrato;
    @XmlElement(name = "FechaRegistro", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlElement(name = "Estado")
    protected boolean estado;
    protected boolean esExterna;

    /**
     * Obtiene el valor de la propiedad idFuente.
     * 
     */
    public long getIdFuente() {
        return idFuente;
    }

    /**
     * Define el valor de la propiedad idFuente.
     * 
     */
    public void setIdFuente(long value) {
        this.idFuente = value;
    }

    /**
     * Obtiene el valor de la propiedad idLineaCredito.
     * 
     */
    public long getIdLineaCredito() {
        return idLineaCredito;
    }

    /**
     * Define el valor de la propiedad idLineaCredito.
     * 
     */
    public void setIdLineaCredito(long value) {
        this.idLineaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad idLineaPasiva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdLineaPasiva() {
        return idLineaPasiva;
    }

    /**
     * Define el valor de la propiedad idLineaPasiva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdLineaPasiva(String value) {
        this.idLineaPasiva = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoLineaPasiva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLineaPasiva() {
        return codigoLineaPasiva;
    }

    /**
     * Define el valor de la propiedad codigoLineaPasiva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLineaPasiva(String value) {
        this.codigoLineaPasiva = value;
    }

    /**
     * Obtiene el valor de la propiedad idFacturadorLineaPasiva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFacturadorLineaPasiva() {
        return idFacturadorLineaPasiva;
    }

    /**
     * Define el valor de la propiedad idFacturadorLineaPasiva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFacturadorLineaPasiva(String value) {
        this.idFacturadorLineaPasiva = value;
    }

    /**
     * Obtiene el valor de la propiedad idFondo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdFondo() {
        return idFondo;
    }

    /**
     * Define el valor de la propiedad idFondo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdFondo(String value) {
        this.idFondo = value;
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
     * Obtiene el valor de la propiedad fechaObtenido.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaObtenido() {
        return fechaObtenido;
    }

    /**
     * Define el valor de la propiedad fechaObtenido.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaObtenido(XMLGregorianCalendar value) {
        this.fechaObtenido = value;
    }

    /**
     * Obtiene el valor de la propiedad montoAsignado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoAsignado() {
        return montoAsignado;
    }

    /**
     * Define el valor de la propiedad montoAsignado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoAsignado(BigDecimal value) {
        this.montoAsignado = value;
    }

    /**
     * Obtiene el valor de la propiedad montoDisponible.
     * 
     */
    public double getMontoDisponible() {
        return montoDisponible;
    }

    /**
     * Define el valor de la propiedad montoDisponible.
     * 
     */
    public void setMontoDisponible(double value) {
        this.montoDisponible = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the monto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMonto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MontoType }
     * 
     * 
     */
    public List<MontoType> getMonto() {
        if (monto == null) {
            monto = new ArrayList<MontoType>();
        }
        return this.monto;
    }

    /**
     * Obtiene el valor de la propiedad numeroAsignacion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumeroAsignacion() {
        return numeroAsignacion;
    }

    /**
     * Define el valor de la propiedad numeroAsignacion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumeroAsignacion(BigDecimal value) {
        this.numeroAsignacion = value;
    }

    /**
     * Obtiene el valor de la propiedad comentario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Define el valor de la propiedad comentario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentario(String value) {
        this.comentario = value;
    }

    /**
     * Obtiene el valor de la propiedad idContrato.
     * 
     */
    public long getIdContrato() {
        return idContrato;
    }

    /**
     * Define el valor de la propiedad idContrato.
     * 
     */
    public void setIdContrato(long value) {
        this.idContrato = value;
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

    /**
     * Obtiene el valor de la propiedad esExterna.
     * 
     */
    public boolean isEsExterna() {
        return esExterna;
    }

    /**
     * Define el valor de la propiedad esExterna.
     * 
     */
    public void setEsExterna(boolean value) {
        this.esExterna = value;
    }

}
