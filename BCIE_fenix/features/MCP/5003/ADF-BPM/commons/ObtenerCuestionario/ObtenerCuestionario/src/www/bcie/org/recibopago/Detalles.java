
package www.bcie.org.recibopago;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Detalles complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Detalles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="prestamo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desembolso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detalle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monedaAdeudo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cantidadAdeudada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monedaPagada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cantidadPagada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Detalles", propOrder = {
         "prestamo", "desembolso", "detalle", "monedaAdeudo", "cantidadAdeudada", "monedaPagada", "cantidadPagada"
    })
public class Detalles {

    @XmlElement(required = true)
    protected String prestamo;
    @XmlElement(required = true)
    protected String desembolso;
    @XmlElement(required = true)
    protected String detalle;
    @XmlElement(required = true)
    protected String monedaAdeudo;
    @XmlElement(required = true)
    protected String cantidadAdeudada;
    @XmlElement(required = true)
    protected String monedaPagada;
    @XmlElement(required = true)
    protected String cantidadPagada;

    /**
     * Gets the value of the prestamo property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPrestamo() {
        return prestamo;
    }

    /**
     * Sets the value of the prestamo property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPrestamo(String value) {
        this.prestamo = value;
    }

    /**
     * Gets the value of the desembolso property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDesembolso() {
        return desembolso;
    }

    /**
     * Sets the value of the desembolso property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDesembolso(String value) {
        this.desembolso = value;
    }

    /**
     * Gets the value of the detalle property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * Sets the value of the detalle property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDetalle(String value) {
        this.detalle = value;
    }

    /**
     * Gets the value of the monedaAdeudo property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMonedaAdeudo() {
        return monedaAdeudo;
    }

    /**
     * Sets the value of the monedaAdeudo property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMonedaAdeudo(String value) {
        this.monedaAdeudo = value;
    }

    /**
     * Gets the value of the cantidadAdeudada property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCantidadAdeudada() {
        return cantidadAdeudada;
    }

    /**
     * Sets the value of the cantidadAdeudada property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCantidadAdeudada(String value) {
        this.cantidadAdeudada = value;
    }

    /**
     * Gets the value of the monedaPagada property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMonedaPagada() {
        return monedaPagada;
    }

    /**
     * Sets the value of the monedaPagada property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMonedaPagada(String value) {
        this.monedaPagada = value;
    }

    /**
     * Gets the value of the cantidadPagada property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCantidadPagada() {
        return cantidadPagada;
    }

    /**
     * Sets the value of the cantidadPagada property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCantidadPagada(String value) {
        this.cantidadPagada = value;
    }

}
