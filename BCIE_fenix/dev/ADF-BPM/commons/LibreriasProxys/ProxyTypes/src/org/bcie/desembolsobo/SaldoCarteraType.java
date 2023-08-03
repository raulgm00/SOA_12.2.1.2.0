
package org.bcie.desembolsobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.bcie.lineacreditobo.TeenLineaCreditoType;


/**
 * <p>Clase Java para SaldoCarteraType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SaldoCarteraType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/LineaCreditoBO}TeenLineaCreditoType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}LineaFinancieraGroup"/>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}DestinoGroup"/>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}MonedaGroup"/>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}PaisesGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SaldoCarteraType", propOrder = {
    "listaLineaFinanciera",
    "lineaFinanciera",
    "listaDestino",
    "listaMoneda",
    "listaPaises"
})
public class SaldoCarteraType
    extends TeenLineaCreditoType
{

    @XmlElementRef(name = "ListaLineaFinanciera", namespace = "http://www.bcie.org/DesembolsoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<String> listaLineaFinanciera;
    @XmlElement(name = "LineaFinanciera", nillable = true)
    protected List<LineaFinancieraType> lineaFinanciera;
    @XmlElementRef(name = "ListaDestino", namespace = "http://www.bcie.org/DesembolsoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> listaDestino;
    @XmlElementRef(name = "ListaMoneda", namespace = "http://www.bcie.org/DesembolsoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> listaMoneda;
    @XmlElementRef(name = "ListaPaises", namespace = "http://www.bcie.org/DesembolsoBO", type = JAXBElement.class, required = false)
    protected JAXBElement<Object> listaPaises;

    /**
     * Obtiene el valor de la propiedad listaLineaFinanciera.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getListaLineaFinanciera() {
        return listaLineaFinanciera;
    }

    /**
     * Define el valor de la propiedad listaLineaFinanciera.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setListaLineaFinanciera(JAXBElement<String> value) {
        this.listaLineaFinanciera = value;
    }

    /**
     * Gets the value of the lineaFinanciera property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineaFinanciera property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineaFinanciera().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineaFinancieraType }
     * 
     * 
     */
    public List<LineaFinancieraType> getLineaFinanciera() {
        if (lineaFinanciera == null) {
            lineaFinanciera = new ArrayList<LineaFinancieraType>();
        }
        return this.lineaFinanciera;
    }

    /**
     * Obtiene el valor de la propiedad listaDestino.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getListaDestino() {
        return listaDestino;
    }

    /**
     * Define el valor de la propiedad listaDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setListaDestino(JAXBElement<Object> value) {
        this.listaDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad listaMoneda.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getListaMoneda() {
        return listaMoneda;
    }

    /**
     * Define el valor de la propiedad listaMoneda.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setListaMoneda(JAXBElement<Object> value) {
        this.listaMoneda = value;
    }

    /**
     * Obtiene el valor de la propiedad listaPaises.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public JAXBElement<Object> getListaPaises() {
        return listaPaises;
    }

    /**
     * Define el valor de la propiedad listaPaises.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Object }{@code >}
     *     
     */
    public void setListaPaises(JAXBElement<Object> value) {
        this.listaPaises = value;
    }

}
