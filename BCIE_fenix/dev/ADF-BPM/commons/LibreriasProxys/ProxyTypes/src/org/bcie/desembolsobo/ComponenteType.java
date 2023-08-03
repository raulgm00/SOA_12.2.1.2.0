
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.commonbo.PlazoType;


/**
 * <p>Clase Java para ComponenteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ComponenteType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/CatalogoBO}Catalogo">
 *       &lt;sequence>
 *         &lt;element name="TipoComponente" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="TipoTasa" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="esPrincipal" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Plazo" type="{http://www.bcie.org/CommonBO}PlazoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComponenteType", propOrder = {
    "tipoComponente",
    "tipoTasa",
    "esPrincipal",
    "plazo"
})
public class ComponenteType
    extends Catalogo
{

    @XmlElement(name = "TipoComponente", required = true)
    protected Catalogo tipoComponente;
    @XmlElement(name = "TipoTasa", required = true)
    protected Catalogo tipoTasa;
    protected boolean esPrincipal;
    @XmlElement(name = "Plazo")
    protected PlazoType plazo;

    /**
     * Obtiene el valor de la propiedad tipoComponente.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoComponente() {
        return tipoComponente;
    }

    /**
     * Define el valor de la propiedad tipoComponente.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoComponente(Catalogo value) {
        this.tipoComponente = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoTasa.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoTasa() {
        return tipoTasa;
    }

    /**
     * Define el valor de la propiedad tipoTasa.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoTasa(Catalogo value) {
        this.tipoTasa = value;
    }

    /**
     * Obtiene el valor de la propiedad esPrincipal.
     * 
     */
    public boolean isEsPrincipal() {
        return esPrincipal;
    }

    /**
     * Define el valor de la propiedad esPrincipal.
     * 
     */
    public void setEsPrincipal(boolean value) {
        this.esPrincipal = value;
    }

    /**
     * Obtiene el valor de la propiedad plazo.
     * 
     * @return
     *     possible object is
     *     {@link PlazoType }
     *     
     */
    public PlazoType getPlazo() {
        return plazo;
    }

    /**
     * Define el valor de la propiedad plazo.
     * 
     * @param value
     *     allowed object is
     *     {@link PlazoType }
     *     
     */
    public void setPlazo(PlazoType value) {
        this.plazo = value;
    }

}
