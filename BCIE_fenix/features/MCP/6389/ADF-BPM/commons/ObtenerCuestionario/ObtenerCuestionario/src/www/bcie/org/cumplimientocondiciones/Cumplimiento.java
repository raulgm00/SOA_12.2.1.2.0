//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.01.04 a las 08:46:22 AM CST 
//


package www.bcie.org.cumplimientocondiciones;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cumplimiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cumplimiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lstCondicion" type="{http://www.bcie.org/herramientaOfismatica/cumplimientocondiciones}lstCondicion" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cumplimiento", propOrder = {
    "lstCondicion"
})
public class Cumplimiento {

    @XmlElement(name = "lstCondicion",required = true)
    protected List<LstCondicion> lstCondicion;

    /**
     * Gets the value of the lstCondicion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lstCondicion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLstCondicion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LstCondicion }
     * 
     * 
     */
    public List<LstCondicion> getLstCondicion() {
        if (lstCondicion == null) {
            lstCondicion = new ArrayList<LstCondicion>();
        }
        return this.lstCondicion;
    }

}
