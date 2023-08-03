
package org.bcie.clientebo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;


/**
 * Entidad Cliente - (Modelo canónico)
 * 
 * <p>Clase Java para Cliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Cliente">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/ClienteBO}ClienteBasicType">
 *       &lt;sequence>
 *         &lt;element name="Contactos" type="{http://www.bcie.org/ClienteBO}Contactos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="InformacionCorrecta" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ActualizacionPermitida" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="comentarioAprobacion" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="250"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="revisadoAprobacion" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="status" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fechaBaja" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="diaPago" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SCR" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="perspectiva" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="enMora" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="mora" type="{http://www.bcie.org/ClienteBO}mora" minOccurs="0"/>
 *         &lt;element name="deteriorado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="reserva" type="{http://www.bcie.org/ClienteBO}reservaType" minOccurs="0"/>
 *         &lt;element name="requiereEnvioAutomatico" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="detalleSCR" type="{http://www.bcie.org/ClienteBO}DetalleSCRType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cliente", propOrder = {
    "contactos",
    "informacionCorrecta",
    "actualizacionPermitida",
    "comentarioAprobacion",
    "revisadoAprobacion",
    "status",
    "fechaBaja",
    "diaPago",
    "scr",
    "perspectiva",
    "enMora",
    "mora",
    "deteriorado",
    "reserva",
    "requiereEnvioAutomatico",
    "detalleSCR"
})
public class Cliente
    extends ClienteBasicType
{

    @XmlElement(name = "Contactos")
    protected List<Contactos> contactos;
    @XmlElement(name = "InformacionCorrecta")
    protected boolean informacionCorrecta;
    @XmlElement(name = "ActualizacionPermitida")
    protected boolean actualizacionPermitida;
    protected String comentarioAprobacion;
    protected String revisadoAprobacion;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaBaja;
    protected Integer diaPago;
    @XmlElement(name = "SCR")
    protected Catalogo scr;
    protected Catalogo perspectiva;
    @XmlElement(defaultValue = "false")
    protected Boolean enMora;
    protected Mora mora;
    @XmlElement(defaultValue = "false")
    protected Boolean deteriorado;
    protected ReservaType reserva;
    protected BigDecimal requiereEnvioAutomatico;
    protected DetalleSCRType detalleSCR;

    /**
     * Gets the value of the contactos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Contactos }
     * 
     * 
     */
    public List<Contactos> getContactos() {
        if (contactos == null) {
            contactos = new ArrayList<Contactos>();
        }
        return this.contactos;
    }

    /**
     * Obtiene el valor de la propiedad informacionCorrecta.
     * 
     */
    public boolean isInformacionCorrecta() {
        return informacionCorrecta;
    }

    /**
     * Define el valor de la propiedad informacionCorrecta.
     * 
     */
    public void setInformacionCorrecta(boolean value) {
        this.informacionCorrecta = value;
    }

    /**
     * Obtiene el valor de la propiedad actualizacionPermitida.
     * 
     */
    public boolean isActualizacionPermitida() {
        return actualizacionPermitida;
    }

    /**
     * Define el valor de la propiedad actualizacionPermitida.
     * 
     */
    public void setActualizacionPermitida(boolean value) {
        this.actualizacionPermitida = value;
    }

    /**
     * Obtiene el valor de la propiedad comentarioAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentarioAprobacion() {
        return comentarioAprobacion;
    }

    /**
     * Define el valor de la propiedad comentarioAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentarioAprobacion(String value) {
        this.comentarioAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad revisadoAprobacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevisadoAprobacion() {
        return revisadoAprobacion;
    }

    /**
     * Define el valor de la propiedad revisadoAprobacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevisadoAprobacion(String value) {
        this.revisadoAprobacion = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaBaja.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Define el valor de la propiedad fechaBaja.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaBaja(XMLGregorianCalendar value) {
        this.fechaBaja = value;
    }

    /**
     * Obtiene el valor de la propiedad diaPago.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiaPago() {
        return diaPago;
    }

    /**
     * Define el valor de la propiedad diaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiaPago(Integer value) {
        this.diaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad scr.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSCR() {
        return scr;
    }

    /**
     * Define el valor de la propiedad scr.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSCR(Catalogo value) {
        this.scr = value;
    }

    /**
     * Obtiene el valor de la propiedad perspectiva.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getPerspectiva() {
        return perspectiva;
    }

    /**
     * Define el valor de la propiedad perspectiva.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setPerspectiva(Catalogo value) {
        this.perspectiva = value;
    }

    /**
     * Obtiene el valor de la propiedad enMora.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnMora() {
        return enMora;
    }

    /**
     * Define el valor de la propiedad enMora.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnMora(Boolean value) {
        this.enMora = value;
    }

    /**
     * Obtiene el valor de la propiedad mora.
     * 
     * @return
     *     possible object is
     *     {@link Mora }
     *     
     */
    public Mora getMora() {
        return mora;
    }

    /**
     * Define el valor de la propiedad mora.
     * 
     * @param value
     *     allowed object is
     *     {@link Mora }
     *     
     */
    public void setMora(Mora value) {
        this.mora = value;
    }

    /**
     * Obtiene el valor de la propiedad deteriorado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDeteriorado() {
        return deteriorado;
    }

    /**
     * Define el valor de la propiedad deteriorado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDeteriorado(Boolean value) {
        this.deteriorado = value;
    }

    /**
     * Obtiene el valor de la propiedad reserva.
     * 
     * @return
     *     possible object is
     *     {@link ReservaType }
     *     
     */
    public ReservaType getReserva() {
        return reserva;
    }

    /**
     * Define el valor de la propiedad reserva.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservaType }
     *     
     */
    public void setReserva(ReservaType value) {
        this.reserva = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereEnvioAutomatico.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRequiereEnvioAutomatico() {
        return requiereEnvioAutomatico;
    }

    /**
     * Define el valor de la propiedad requiereEnvioAutomatico.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRequiereEnvioAutomatico(BigDecimal value) {
        this.requiereEnvioAutomatico = value;
    }

    /**
     * Obtiene el valor de la propiedad detalleSCR.
     * 
     * @return
     *     possible object is
     *     {@link DetalleSCRType }
     *     
     */
    public DetalleSCRType getDetalleSCR() {
        return detalleSCR;
    }

    /**
     * Define el valor de la propiedad detalleSCR.
     * 
     * @param value
     *     allowed object is
     *     {@link DetalleSCRType }
     *     
     */
    public void setDetalleSCR(DetalleSCRType value) {
        this.detalleSCR = value;
    }

}
