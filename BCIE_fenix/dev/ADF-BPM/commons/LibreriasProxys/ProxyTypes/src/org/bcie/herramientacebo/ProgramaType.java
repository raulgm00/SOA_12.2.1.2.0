
package org.bcie.herramientacebo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para ProgramaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ProgramaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LineaFinanciera" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="DestinoFinanciamiento" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="ModalidadFinanciamiento" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="HerramientaCE" type="{http://www.bcie.org/HerramientaCEBO}HerramientaCEType"/>
 *         &lt;element name="ClasificacionGeneral" type="{http://www.bcie.org/HerramientaCEBO}ClasificacionGeneralType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramaType", propOrder = {
    "lineaFinanciera",
    "destinoFinanciamiento",
    "modalidadFinanciamiento",
    "herramientaCE",
    "clasificacionGeneral"
})
public class ProgramaType {

    @XmlElement(name = "LineaFinanciera", required = true)
    protected Catalogo lineaFinanciera;
    @XmlElement(name = "DestinoFinanciamiento", required = true)
    protected Catalogo destinoFinanciamiento;
    @XmlElement(name = "ModalidadFinanciamiento", required = true)
    protected Catalogo modalidadFinanciamiento;
    @XmlElement(name = "HerramientaCE", required = true)
    protected HerramientaCEType herramientaCE;
    @XmlElement(name = "ClasificacionGeneral", required = true)
    protected ClasificacionGeneralType clasificacionGeneral;

    /**
     * Obtiene el valor de la propiedad lineaFinanciera.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getLineaFinanciera() {
        return lineaFinanciera;
    }

    /**
     * Define el valor de la propiedad lineaFinanciera.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setLineaFinanciera(Catalogo value) {
        this.lineaFinanciera = value;
    }

    /**
     * Obtiene el valor de la propiedad destinoFinanciamiento.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getDestinoFinanciamiento() {
        return destinoFinanciamiento;
    }

    /**
     * Define el valor de la propiedad destinoFinanciamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setDestinoFinanciamiento(Catalogo value) {
        this.destinoFinanciamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad modalidadFinanciamiento.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getModalidadFinanciamiento() {
        return modalidadFinanciamiento;
    }

    /**
     * Define el valor de la propiedad modalidadFinanciamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setModalidadFinanciamiento(Catalogo value) {
        this.modalidadFinanciamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad herramientaCE.
     * 
     * @return
     *     possible object is
     *     {@link HerramientaCEType }
     *     
     */
    public HerramientaCEType getHerramientaCE() {
        return herramientaCE;
    }

    /**
     * Define el valor de la propiedad herramientaCE.
     * 
     * @param value
     *     allowed object is
     *     {@link HerramientaCEType }
     *     
     */
    public void setHerramientaCE(HerramientaCEType value) {
        this.herramientaCE = value;
    }

    /**
     * Obtiene el valor de la propiedad clasificacionGeneral.
     * 
     * @return
     *     possible object is
     *     {@link ClasificacionGeneralType }
     *     
     */
    public ClasificacionGeneralType getClasificacionGeneral() {
        return clasificacionGeneral;
    }

    /**
     * Define el valor de la propiedad clasificacionGeneral.
     * 
     * @param value
     *     allowed object is
     *     {@link ClasificacionGeneralType }
     *     
     */
    public void setClasificacionGeneral(ClasificacionGeneralType value) {
        this.clasificacionGeneral = value;
    }

}
