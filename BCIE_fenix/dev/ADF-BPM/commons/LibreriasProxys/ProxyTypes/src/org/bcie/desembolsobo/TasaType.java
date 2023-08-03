
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.operacionbo.Factibilidad;


/**
 * <p>Clase Java para tasaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tasaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipo" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;group ref="{http://www.bcie.org/DesembolsoBO}tasaGroup"/>
 *         &lt;element name="spreadMora" type="{http://www.bcie.org/DesembolsoBO}spreadType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tasaType", propOrder = {
    "tipo",
    "fija",
    "variable",
    "especial",
    "spreadMora"
})
@XmlSeeAlso({
    Factibilidad.class
})
public class TasaType {

    @XmlElement(required = true)
    protected Catalogo tipo;
    protected FijaType fija;
    protected VariableType variable;
    protected FijaType especial;
    protected SpreadType spreadMora;

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipo(Catalogo value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad fija.
     * 
     * @return
     *     possible object is
     *     {@link FijaType }
     *     
     */
    public FijaType getFija() {
        return fija;
    }

    /**
     * Define el valor de la propiedad fija.
     * 
     * @param value
     *     allowed object is
     *     {@link FijaType }
     *     
     */
    public void setFija(FijaType value) {
        this.fija = value;
    }

    /**
     * Obtiene el valor de la propiedad variable.
     * 
     * @return
     *     possible object is
     *     {@link VariableType }
     *     
     */
    public VariableType getVariable() {
        return variable;
    }

    /**
     * Define el valor de la propiedad variable.
     * 
     * @param value
     *     allowed object is
     *     {@link VariableType }
     *     
     */
    public void setVariable(VariableType value) {
        this.variable = value;
    }

    /**
     * Obtiene el valor de la propiedad especial.
     * 
     * @return
     *     possible object is
     *     {@link FijaType }
     *     
     */
    public FijaType getEspecial() {
        return especial;
    }

    /**
     * Define el valor de la propiedad especial.
     * 
     * @param value
     *     allowed object is
     *     {@link FijaType }
     *     
     */
    public void setEspecial(FijaType value) {
        this.especial = value;
    }

    /**
     * Obtiene el valor de la propiedad spreadMora.
     * 
     * @return
     *     possible object is
     *     {@link SpreadType }
     *     
     */
    public SpreadType getSpreadMora() {
        return spreadMora;
    }

    /**
     * Define el valor de la propiedad spreadMora.
     * 
     * @param value
     *     allowed object is
     *     {@link SpreadType }
     *     
     */
    public void setSpreadMora(SpreadType value) {
        this.spreadMora = value;
    }

}
