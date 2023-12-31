//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.15 at 04:10:21 PM CST 
//


package www.bcie.org.reportenoobjeciones;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReporteTemplate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReporteTemplate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NombreOperacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TituloProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumeroProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Aviso" type="{http://www.bcie.org/herramientaOfismatica/ReporteNoObjeciones}AvisoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="informeResultadosAdjudicados" type="{http://www.bcie.org/herramientaOfismatica/ReporteNoObjeciones}informeResultadosAdjudicadoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="informeResultadosOtros" type="{http://www.bcie.org/herramientaOfismatica/ReporteNoObjeciones}informeResultadosOtrosType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReporteTemplate", propOrder = {
    "pais",
    "nombreOperacion",
    "tituloProceso",
    "numeroProceso",
    "aviso",
    "informeResultadosAdjudicados",
    "informeResultadosOtros"
})
public class ReporteTemplate {

    @XmlElement(name = "Pais", required = true)
    protected String pais;
    @XmlElement(name = "NombreOperacion", required = true)
    protected String nombreOperacion;
    @XmlElement(name = "TituloProceso", required = true)
    protected String tituloProceso;
    @XmlElement(name = "NumeroProceso", required = true)
    protected String numeroProceso;
    @XmlElement(name = "Aviso")
    protected List<AvisoType> aviso;
    protected List<InformeResultadosAdjudicadoType> informeResultadosAdjudicados;
    protected List<InformeResultadosOtrosType> informeResultadosOtros;

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the nombreOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOperacion() {
        return nombreOperacion;
    }

    /**
     * Sets the value of the nombreOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOperacion(String value) {
        this.nombreOperacion = value;
    }

    /**
     * Gets the value of the tituloProceso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloProceso() {
        return tituloProceso;
    }

    /**
     * Sets the value of the tituloProceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloProceso(String value) {
        this.tituloProceso = value;
    }

    /**
     * Gets the value of the numeroProceso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroProceso() {
        return numeroProceso;
    }

    /**
     * Sets the value of the numeroProceso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroProceso(String value) {
        this.numeroProceso = value;
    }

    /**
     * Gets the value of the aviso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aviso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAviso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AvisoType }
     * 
     * 
     */
    public List<AvisoType> getAviso() {
        if (aviso == null) {
            aviso = new ArrayList<AvisoType>();
        }
        return this.aviso;
    }

    /**
     * Gets the value of the informeResultadosAdjudicados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the informeResultadosAdjudicados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformeResultadosAdjudicados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InformeResultadosAdjudicadoType }
     * 
     * 
     */
    public List<InformeResultadosAdjudicadoType> getInformeResultadosAdjudicados() {
        if (informeResultadosAdjudicados == null) {
            informeResultadosAdjudicados = new ArrayList<InformeResultadosAdjudicadoType>();
        }
        return this.informeResultadosAdjudicados;
    }

    /**
     * Gets the value of the informeResultadosOtros property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the informeResultadosOtros property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformeResultadosOtros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InformeResultadosOtrosType }
     * 
     * 
     */
    public List<InformeResultadosOtrosType> getInformeResultadosOtros() {
        if (informeResultadosOtros == null) {
            informeResultadosOtros = new ArrayList<InformeResultadosOtrosType>();
        }
        return this.informeResultadosOtros;
    }

}
