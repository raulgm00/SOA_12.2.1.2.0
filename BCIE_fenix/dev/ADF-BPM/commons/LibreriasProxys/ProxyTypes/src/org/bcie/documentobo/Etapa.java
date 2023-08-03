
package org.bcie.documentobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Etapa complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Etapa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombreEtapa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoDocumento" type="{http://www.bcie.org/DocumentoBO}TipoDcoumento" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Etapa", propOrder = {
    "nombreEtapa",
    "tipoDocumento"
})
public class Etapa {

    @XmlElement(required = true)
    protected String nombreEtapa;
    protected List<TipoDcoumento> tipoDocumento;

    /**
     * Obtiene el valor de la propiedad nombreEtapa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEtapa() {
        return nombreEtapa;
    }

    /**
     * Define el valor de la propiedad nombreEtapa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEtapa(String value) {
        this.nombreEtapa = value;
    }

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tipoDocumento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTipoDocumento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoDcoumento }
     * 
     * 
     */
    public List<TipoDcoumento> getTipoDocumento() {
        if (tipoDocumento == null) {
            tipoDocumento = new ArrayList<TipoDcoumento>();
        }
        return this.tipoDocumento;
    }

}
