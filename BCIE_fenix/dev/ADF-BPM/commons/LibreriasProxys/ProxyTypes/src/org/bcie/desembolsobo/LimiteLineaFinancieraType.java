
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.commonbo.MontoType;


/**
 * <p>Clase Java para LimiteLineaFinancieraType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LimiteLineaFinancieraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codigo" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="LineaFinanciera" type="{http://www.bcie.org/DesembolsoBO}LineaFinancieraType"/>
 *         &lt;element name="Monto" type="{http://www.bcie.org/CommonBO}MontoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimiteLineaFinancieraType", propOrder = {
    "codigo",
    "lineaFinanciera",
    "monto"
})
@XmlSeeAlso({
    LimiteGlobalLineaFinanciera.class
})
public class LimiteLineaFinancieraType {

    @XmlElement(name = "Codigo", required = true)
    protected Catalogo codigo;
    @XmlElement(name = "LineaFinanciera", required = true)
    protected LineaFinancieraType lineaFinanciera;
    @XmlElement(name = "Monto", required = true)
    protected MontoType monto;

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setCodigo(Catalogo value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad lineaFinanciera.
     * 
     * @return
     *     possible object is
     *     {@link LineaFinancieraType }
     *     
     */
    public LineaFinancieraType getLineaFinanciera() {
        return lineaFinanciera;
    }

    /**
     * Define el valor de la propiedad lineaFinanciera.
     * 
     * @param value
     *     allowed object is
     *     {@link LineaFinancieraType }
     *     
     */
    public void setLineaFinanciera(LineaFinancieraType value) {
        this.lineaFinanciera = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     * @return
     *     possible object is
     *     {@link MontoType }
     *     
     */
    public MontoType getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     * @param value
     *     allowed object is
     *     {@link MontoType }
     *     
     */
    public void setMonto(MontoType value) {
        this.monto = value;
    }

}
