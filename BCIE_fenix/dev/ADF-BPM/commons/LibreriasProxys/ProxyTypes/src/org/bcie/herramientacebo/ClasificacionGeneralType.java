
package org.bcie.herramientacebo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para ClasificacionGeneralType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ClasificacionGeneralType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SectorMercado" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="SectorInstitucional" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClasificacionGeneralType", propOrder = {
    "sectorMercado",
    "sectorInstitucional"
})
public class ClasificacionGeneralType {

    @XmlElement(name = "SectorMercado", required = true)
    protected Catalogo sectorMercado;
    @XmlElement(name = "SectorInstitucional", required = true)
    protected Catalogo sectorInstitucional;

    /**
     * Obtiene el valor de la propiedad sectorMercado.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSectorMercado() {
        return sectorMercado;
    }

    /**
     * Define el valor de la propiedad sectorMercado.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSectorMercado(Catalogo value) {
        this.sectorMercado = value;
    }

    /**
     * Obtiene el valor de la propiedad sectorInstitucional.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSectorInstitucional() {
        return sectorInstitucional;
    }

    /**
     * Define el valor de la propiedad sectorInstitucional.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSectorInstitucional(Catalogo value) {
        this.sectorInstitucional = value;
    }

}
