
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.commonbo.PlazoType;


/**
 * <p>Clase Java para LimitePlazoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LimitePlazoType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/CommonBO}PlazoType">
 *       &lt;sequence>
 *         &lt;element name="TipoLimite" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimitePlazoType", propOrder = {
    "tipoLimite"
})
public class LimitePlazoType
    extends PlazoType
{

    @XmlElement(name = "TipoLimite", required = true)
    protected Catalogo tipoLimite;

    /**
     * Obtiene el valor de la propiedad tipoLimite.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoLimite() {
        return tipoLimite;
    }

    /**
     * Define el valor de la propiedad tipoLimite.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoLimite(Catalogo value) {
        this.tipoLimite = value;
    }

}
