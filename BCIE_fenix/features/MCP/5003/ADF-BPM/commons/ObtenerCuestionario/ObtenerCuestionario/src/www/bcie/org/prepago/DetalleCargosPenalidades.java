//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.25 at 11:19:22 AM CDT 
//


package www.bcie.org.prepago;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetalleCargosPenalidades complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetalleCargosPenalidades">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaTecnica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="penalidad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monedaP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cargosTramite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monedaCT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otrosCargos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monedaOC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleCargosPenalidades", propOrder = {
    "areaTecnica",
    "penalidad",
    "monedaP",
    "cargosTramite",
    "monedaCT",
    "otrosCargos",
    "monedaOC"
})
public class DetalleCargosPenalidades {

    @XmlElement(required = true)
    protected String areaTecnica;
    @XmlElement(required = true)
    protected String penalidad;
    @XmlElement(required = true)
    protected String monedaP;
    @XmlElement(required = true)
    protected String cargosTramite;
    @XmlElement(required = true)
    protected String monedaCT;
    @XmlElement(required = true)
    protected String otrosCargos;
    @XmlElement(required = true)
    protected String monedaOC;

    /**
     * Gets the value of the areaTecnica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaTecnica() {
        return areaTecnica;
    }

    /**
     * Sets the value of the areaTecnica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaTecnica(String value) {
        this.areaTecnica = value;
    }

    /**
     * Gets the value of the penalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenalidad() {
        return penalidad;
    }

    /**
     * Sets the value of the penalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenalidad(String value) {
        this.penalidad = value;
    }

    /**
     * Gets the value of the monedaP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaP() {
        return monedaP;
    }

    /**
     * Sets the value of the monedaP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaP(String value) {
        this.monedaP = value;
    }

    /**
     * Gets the value of the cargosTramite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargosTramite() {
        return cargosTramite;
    }

    /**
     * Sets the value of the cargosTramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargosTramite(String value) {
        this.cargosTramite = value;
    }

    /**
     * Gets the value of the monedaCT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaCT() {
        return monedaCT;
    }

    /**
     * Sets the value of the monedaCT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaCT(String value) {
        this.monedaCT = value;
    }

    /**
     * Gets the value of the otrosCargos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtrosCargos() {
        return otrosCargos;
    }

    /**
     * Sets the value of the otrosCargos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtrosCargos(String value) {
        this.otrosCargos = value;
    }

    /**
     * Gets the value of the monedaOC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaOC() {
        return monedaOC;
    }

    /**
     * Sets the value of the monedaOC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaOC(String value) {
        this.monedaOC = value;
    }

}
