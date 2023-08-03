
package org.bcie.herramientacebo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para HerramientaCEType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="HerramientaCEType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActividadEconomica" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="EjeEstrategico" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="AreaFocalizacion" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HerramientaCEType", propOrder = {
    "actividadEconomica",
    "ejeEstrategico",
    "areaFocalizacion"
})
public class HerramientaCEType {

    @XmlElement(name = "ActividadEconomica", required = true)
    protected Catalogo actividadEconomica;
    @XmlElement(name = "EjeEstrategico", required = true)
    protected Catalogo ejeEstrategico;
    @XmlElement(name = "AreaFocalizacion", required = true)
    protected Catalogo areaFocalizacion;

    /**
     * Obtiene el valor de la propiedad actividadEconomica.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getActividadEconomica() {
        return actividadEconomica;
    }

    /**
     * Define el valor de la propiedad actividadEconomica.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setActividadEconomica(Catalogo value) {
        this.actividadEconomica = value;
    }

    /**
     * Obtiene el valor de la propiedad ejeEstrategico.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEjeEstrategico() {
        return ejeEstrategico;
    }

    /**
     * Define el valor de la propiedad ejeEstrategico.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEjeEstrategico(Catalogo value) {
        this.ejeEstrategico = value;
    }

    /**
     * Obtiene el valor de la propiedad areaFocalizacion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getAreaFocalizacion() {
        return areaFocalizacion;
    }

    /**
     * Define el valor de la propiedad areaFocalizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setAreaFocalizacion(Catalogo value) {
        this.areaFocalizacion = value;
    }

}
