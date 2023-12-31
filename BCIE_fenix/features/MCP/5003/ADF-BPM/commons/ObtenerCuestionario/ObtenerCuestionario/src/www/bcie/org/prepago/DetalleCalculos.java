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
 * <p>Java class for DetalleCalculos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetalleCalculos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoReporte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="columnaPenalidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoDetallePenalidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tablaCargosPenalidades" type="{http://www.bcie.org/herramientaOfismatica/prepago}DetalleCargosPenalidades" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Observaciones" type="{http://www.bcie.org/herramientaOfismatica/prepago}Observaciones" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tablaDetallePenalidad" type="{http://www.bcie.org/herramientaOfismatica/prepago}DetallePenalidad" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tablaCondicionesEspeciales" type="{http://www.bcie.org/herramientaOfismatica/prepago}CondicionesEspeciales" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tablaCoberturas" type="{http://www.bcie.org/herramientaOfismatica/prepago}Coberturas" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tablaVentaCartera" type="{http://www.bcie.org/herramientaOfismatica/prepago}VentaCartera" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tablaFuentesExternas" type="{http://www.bcie.org/herramientaOfismatica/prepago}FuentesExternas" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleCalculos", propOrder = {
    "tipoReporte",
    "columnaPenalidad",
    "tipoDetallePenalidad",
    "tablaCargosPenalidades",
    "observaciones",
    "tablaDetallePenalidad",
    "tablaCondicionesEspeciales",
    "tablaCoberturas",
    "tablaVentaCartera",
    "tablaFuentesExternas"
})
public class DetalleCalculos {

    @XmlElement(required = true)
    protected String tipoReporte;
    @XmlElement(required = true)
    protected String columnaPenalidad;
    @XmlElement(required = true)
    protected String tipoDetallePenalidad;
    protected List<DetalleCargosPenalidades> tablaCargosPenalidades;
    @XmlElement(name = "Observaciones")
    protected List<Observaciones> observaciones;
    protected List<DetallePenalidad> tablaDetallePenalidad;
    protected List<CondicionesEspeciales> tablaCondicionesEspeciales;
    protected List<Coberturas> tablaCoberturas;
    protected List<VentaCartera> tablaVentaCartera;
    protected List<FuentesExternas> tablaFuentesExternas;

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
     * Gets the value of the columnaPenalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumnaPenalidad() {
        return columnaPenalidad;
    }

    /**
     * Sets the value of the columnaPenalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumnaPenalidad(String value) {
        this.columnaPenalidad = value;
    }

    /**
     * Gets the value of the tipoDetallePenalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDetallePenalidad() {
        return tipoDetallePenalidad;
    }

    /**
     * Sets the value of the tipoDetallePenalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDetallePenalidad(String value) {
        this.tipoDetallePenalidad = value;
    }

    /**
     * Gets the value of the tablaCargosPenalidades property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaCargosPenalidades property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaCargosPenalidades().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleCargosPenalidades }
     * 
     * 
     */
    public List<DetalleCargosPenalidades> getTablaCargosPenalidades() {
        if (tablaCargosPenalidades == null) {
            tablaCargosPenalidades = new ArrayList<DetalleCargosPenalidades>();
        }
        return this.tablaCargosPenalidades;
    }

    /**
     * Gets the value of the observaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the observaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObservaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Observaciones }
     * 
     * 
     */
    public List<Observaciones> getObservaciones() {
        if (observaciones == null) {
            observaciones = new ArrayList<Observaciones>();
        }
        return this.observaciones;
    }

    /**
     * Gets the value of the tablaDetallePenalidad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaDetallePenalidad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaDetallePenalidad().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetallePenalidad }
     * 
     * 
     */
    public List<DetallePenalidad> getTablaDetallePenalidad() {
        if (tablaDetallePenalidad == null) {
            tablaDetallePenalidad = new ArrayList<DetallePenalidad>();
        }
        return this.tablaDetallePenalidad;
    }

    /**
     * Gets the value of the tablaCondicionesEspeciales property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaCondicionesEspeciales property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaCondicionesEspeciales().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CondicionesEspeciales }
     * 
     * 
     */
    public List<CondicionesEspeciales> getTablaCondicionesEspeciales() {
        if (tablaCondicionesEspeciales == null) {
            tablaCondicionesEspeciales = new ArrayList<CondicionesEspeciales>();
        }
        return this.tablaCondicionesEspeciales;
    }

    /**
     * Gets the value of the tablaCoberturas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaCoberturas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaCoberturas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Coberturas }
     * 
     * 
     */
    public List<Coberturas> getTablaCoberturas() {
        if (tablaCoberturas == null) {
            tablaCoberturas = new ArrayList<Coberturas>();
        }
        return this.tablaCoberturas;
    }

    /**
     * Gets the value of the tablaVentaCartera property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaVentaCartera property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaVentaCartera().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VentaCartera }
     * 
     * 
     */
    public List<VentaCartera> getTablaVentaCartera() {
        if (tablaVentaCartera == null) {
            tablaVentaCartera = new ArrayList<VentaCartera>();
        }
        return this.tablaVentaCartera;
    }

    /**
     * Gets the value of the tablaFuentesExternas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaFuentesExternas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaFuentesExternas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FuentesExternas }
     * 
     * 
     */
    public List<FuentesExternas> getTablaFuentesExternas() {
        if (tablaFuentesExternas == null) {
            tablaFuentesExternas = new ArrayList<FuentesExternas>();
        }
        return this.tablaFuentesExternas;
    }

}
