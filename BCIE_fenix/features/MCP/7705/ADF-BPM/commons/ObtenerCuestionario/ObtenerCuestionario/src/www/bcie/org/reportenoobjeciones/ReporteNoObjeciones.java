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
 * <p>Java class for ReporteNoObjeciones complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReporteNoObjeciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReporteTemplate" type="{http://www.bcie.org/herramientaOfismatica/ReporteNoObjeciones}ReporteTemplate" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReporteNoObjeciones", propOrder = {
    "reporteTemplate"
})
public class ReporteNoObjeciones {

    @XmlElement(name = "ReporteTemplate", required = true)
    protected List<ReporteTemplate> reporteTemplate;

    /**
     * Gets the value of the reporteTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reporteTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReporteTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReporteTemplate }
     * 
     * 
     */
    public List<ReporteTemplate> getReporteTemplate() {
        if (reporteTemplate == null) {
            reporteTemplate = new ArrayList<ReporteTemplate>();
        }
        return this.reporteTemplate;
    }

}
