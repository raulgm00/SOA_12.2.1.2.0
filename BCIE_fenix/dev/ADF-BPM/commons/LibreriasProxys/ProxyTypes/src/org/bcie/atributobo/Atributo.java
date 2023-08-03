
package org.bcie.atributobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para Atributo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Atributo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="columna" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ordenamiento" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="soloLectura" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="esObligatorio" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="etiqueta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="tipoValor" type="{http://www.bcie.org/AtributoBO}TipoValorType" minOccurs="0"/>
 *         &lt;element name="catalogo" type="{http://www.bcie.org/CatalogoBO}Catalogo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Atributo", propOrder = {
    "id",
    "columna",
    "ordenamiento",
    "soloLectura",
    "esObligatorio",
    "etiqueta",
    "valor",
    "tipoValor",
    "catalogo"
})
public class Atributo {

    protected Long id;
    @XmlElement(required = true)
    protected String columna;
    protected Integer ordenamiento;
    protected Boolean soloLectura;
    protected Boolean esObligatorio;
    protected String etiqueta;
    @XmlElement(required = true)
    protected List<String> valor;
    @XmlSchemaType(name = "string")
    protected TipoValorType tipoValor;
    protected List<Catalogo> catalogo;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad columna.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumna() {
        return columna;
    }

    /**
     * Define el valor de la propiedad columna.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumna(String value) {
        this.columna = value;
    }

    /**
     * Obtiene el valor de la propiedad ordenamiento.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrdenamiento() {
        return ordenamiento;
    }

    /**
     * Define el valor de la propiedad ordenamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrdenamiento(Integer value) {
        this.ordenamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad soloLectura.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSoloLectura() {
        return soloLectura;
    }

    /**
     * Define el valor de la propiedad soloLectura.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSoloLectura(Boolean value) {
        this.soloLectura = value;
    }

    /**
     * Obtiene el valor de la propiedad esObligatorio.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsObligatorio() {
        return esObligatorio;
    }

    /**
     * Define el valor de la propiedad esObligatorio.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsObligatorio(Boolean value) {
        this.esObligatorio = value;
    }

    /**
     * Obtiene el valor de la propiedad etiqueta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * Define el valor de la propiedad etiqueta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtiqueta(String value) {
        this.etiqueta = value;
    }

    /**
     * Gets the value of the valor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getValor() {
        if (valor == null) {
            valor = new ArrayList<String>();
        }
        return this.valor;
    }

    /**
     * Obtiene el valor de la propiedad tipoValor.
     * 
     * @return
     *     possible object is
     *     {@link TipoValorType }
     *     
     */
    public TipoValorType getTipoValor() {
        return tipoValor;
    }

    /**
     * Define el valor de la propiedad tipoValor.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoValorType }
     *     
     */
    public void setTipoValor(TipoValorType value) {
        this.tipoValor = value;
    }

    /**
     * Gets the value of the catalogo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the catalogo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCatalogo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Catalogo }
     * 
     * 
     */
    public List<Catalogo> getCatalogo() {
        if (catalogo == null) {
            catalogo = new ArrayList<Catalogo>();
        }
        return this.catalogo;
    }

}
