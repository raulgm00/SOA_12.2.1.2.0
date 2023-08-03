
package org.bcie.desembolsobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.commonbo.MontoType;


/**
 * <p>Clase Java para CalendarioComplejoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CalendarioComplejoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoPlan" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="Frecuencia" type="{http://www.bcie.org/DesembolsoBO}FrecuenciaType"/>
 *         &lt;element name="Monto" type="{http://www.bcie.org/CommonBO}MontoType"/>
 *         &lt;element name="NumeroCuotas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalendarioComplejoType", propOrder = {
    "tipoPlan",
    "frecuencia",
    "monto",
    "numeroCuotas"
})
public class CalendarioComplejoType {

    @XmlElement(name = "TipoPlan", required = true)
    protected Catalogo tipoPlan;
    @XmlElement(name = "Frecuencia", required = true)
    protected FrecuenciaType frecuencia;
    @XmlElement(name = "Monto", required = true)
    protected MontoType monto;
    @XmlElement(name = "NumeroCuotas")
    protected int numeroCuotas;

    /**
     * Obtiene el valor de la propiedad tipoPlan.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoPlan() {
        return tipoPlan;
    }

    /**
     * Define el valor de la propiedad tipoPlan.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoPlan(Catalogo value) {
        this.tipoPlan = value;
    }

    /**
     * Obtiene el valor de la propiedad frecuencia.
     * 
     * @return
     *     possible object is
     *     {@link FrecuenciaType }
     *     
     */
    public FrecuenciaType getFrecuencia() {
        return frecuencia;
    }

    /**
     * Define el valor de la propiedad frecuencia.
     * 
     * @param value
     *     allowed object is
     *     {@link FrecuenciaType }
     *     
     */
    public void setFrecuencia(FrecuenciaType value) {
        this.frecuencia = value;
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

    /**
     * Obtiene el valor de la propiedad numeroCuotas.
     * 
     */
    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    /**
     * Define el valor de la propiedad numeroCuotas.
     * 
     */
    public void setNumeroCuotas(int value) {
        this.numeroCuotas = value;
    }

}
