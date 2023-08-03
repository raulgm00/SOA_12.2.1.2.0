
package org.bcie.desembolsobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;
import org.bcie.commonbo.MontoType;


/**
 * <p>Clase Java para SolicitudDesembolso complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SolicitudDesembolso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}DesembolsoIdentificador"/>
 *         &lt;element name="fechaCreacion" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="fechaSolicitud" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="monto" type="{http://www.bcie.org/CommonBO}MontoType"/>
 *         &lt;element name="tipoMoneda" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="estado" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ValidacionAsignacion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Excepcion" type="{http://www.bcie.org/DesembolsoBO}ExcepcionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolicitudDesembolso", propOrder = {
    "idDesembolso",
    "idFacturador",
    "fechaCreacion",
    "fechaSolicitud",
    "monto",
    "tipoMoneda",
    "estado",
    "estatus",
    "instanciaProceso",
    "validacionAsignacion",
    "excepcion"
})
@XmlSeeAlso({
    SolicitudDesembolsoType.class
})
public class SolicitudDesembolso {

    protected long idDesembolso;
    protected String idFacturador;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaCreacion;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaSolicitud;
    @XmlElement(required = true)
    protected MontoType monto;
    @XmlElement(required = true)
    protected Catalogo tipoMoneda;
    @XmlElement(required = true)
    protected Catalogo estado;
    protected boolean estatus;
    protected int instanciaProceso;
    @XmlElement(name = "ValidacionAsignacion")
    protected boolean validacionAsignacion;
    @XmlElement(name = "Excepcion")
    protected List<ExcepcionType> excepcion;

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
     * Obtiene el valor de la propiedad fechaCreacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Define el valor de la propiedad fechaCreacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCreacion(XMLGregorianCalendar value) {
        this.fechaCreacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaSolicitud.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * Define el valor de la propiedad fechaSolicitud.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaSolicitud(XMLGregorianCalendar value) {
        this.fechaSolicitud = value;
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
     * Obtiene el valor de la propiedad tipoMoneda.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoMoneda() {
        return tipoMoneda;
    }

    /**
     * Define el valor de la propiedad tipoMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoMoneda(Catalogo value) {
        this.tipoMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEstado(Catalogo value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad estatus.
     * 
     */
    public boolean isEstatus() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     */
    public void setEstatus(boolean value) {
        this.estatus = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     */
    public int getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     */
    public void setInstanciaProceso(int value) {
        this.instanciaProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad validacionAsignacion.
     * 
     */
    public boolean isValidacionAsignacion() {
        return validacionAsignacion;
    }

    /**
     * Define el valor de la propiedad validacionAsignacion.
     * 
     */
    public void setValidacionAsignacion(boolean value) {
        this.validacionAsignacion = value;
    }

    /**
     * Gets the value of the excepcion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the excepcion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExcepcion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExcepcionType }
     * 
     * 
     */
    public List<ExcepcionType> getExcepcion() {
        if (excepcion == null) {
            excepcion = new ArrayList<ExcepcionType>();
        }
        return this.excepcion;
    }

}
