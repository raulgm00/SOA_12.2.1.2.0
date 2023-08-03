
package www.bcie.org.recibopago;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Recibo complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="Recibo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numRecibo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaEfectiva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tablaDetalle" type="{http://www.bcie.org/herramientaOfismatica/ReciboPago}TablaDetalle" maxOccurs="unbounded"/>
 *         &lt;element name="tablaDetalleUSD" type="{http://www.bcie.org/herramientaOfismatica/ReciboPago}TablaDetalle" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recibo", propOrder = {
         "fechaProceso", "numRecibo", "cliente", "fechaEfectiva", "tablaDetalle", "tablaDetalleUSD" })
public class Recibo {

    @XmlElement(required = true)
    protected String fechaProceso;
    @XmlElement(required = true)
    protected String numRecibo;
    @XmlElement(required = true)
    protected String cliente;
    @XmlElement(required = true)
    protected String fechaEfectiva;
    @XmlElement(required = true)
    protected List<TablaDetalle> tablaDetalle;
    @XmlElement(required = true)
    protected List<TablaDetalle> tablaDetalleUSD;

    /**
     * Gets the value of the fechaProceso property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFechaProceso() {
        return fechaProceso;
    }

    /**
     * Sets the value of the fechaProceso property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFechaProceso(String value) {
        this.fechaProceso = value;
    }

    /**
     * Gets the value of the numRecibo property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNumRecibo() {
        return numRecibo;
    }

    /**
     * Sets the value of the numRecibo property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNumRecibo(String value) {
        this.numRecibo = value;
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
     * Gets the value of the fechaEfectiva property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFechaEfectiva() {
        return fechaEfectiva;
    }

    /**
     * Sets the value of the fechaEfectiva property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFechaEfectiva(String value) {
        this.fechaEfectiva = value;
    }

    /**
     * Gets the value of the tablaDetalle property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaDetalle property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaDetalle().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TablaDetalle }
     *
     *
     */
    public List<TablaDetalle> getTablaDetalle() {
        if (tablaDetalle == null) {
            tablaDetalle = new ArrayList<TablaDetalle>();
        }
        return this.tablaDetalle;
    }

    /**
     * Gets the value of the tablaDetalleUSD property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaDetalleUSD property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaDetalleUSD().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TablaDetalle }
     *
     *
     */
    public List<TablaDetalle> getTablaDetalleUSD() {
        if (tablaDetalleUSD == null) {
            tablaDetalleUSD = new ArrayList<TablaDetalle>();
        }
        return this.tablaDetalleUSD;
    }

}
