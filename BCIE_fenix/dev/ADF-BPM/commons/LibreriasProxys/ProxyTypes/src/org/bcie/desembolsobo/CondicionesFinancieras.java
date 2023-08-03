
package org.bcie.desembolsobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.bcie.catalogobo.Catalogo;


/**
 * <p>Clase Java para condicionesFinancieras complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="condicionesFinancieras">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCondicionFinanciera" type="{http://www.bcie.org/DesembolsoBO}idDesembolso"/>
 *         &lt;element name="tasa" type="{http://www.bcie.org/DesembolsoBO}tasaType"/>
 *         &lt;element name="fondo" type="{http://www.bcie.org/DesembolsoBO}FondoType"/>
 *         &lt;element name="baseCalculo" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="principal">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.bcie.org/DesembolsoBO}FrecuenciaType">
 *                 &lt;sequence>
 *                   &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="NumeroCuotas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="interes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.bcie.org/DesembolsoBO}FrecuenciaType">
 *                 &lt;sequence>
 *                   &lt;element name="NumeroCuotas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="periodoGracia" type="{http://www.bcie.org/DesembolsoBO}FrecuenciaType"/>
 *         &lt;element name="tratamientoDiasFeriados" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoCalendario" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="moverEntreMeses" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="calendarioComplejo" type="{http://www.bcie.org/DesembolsoBO}CalendarioComplejoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "condicionesFinancieras", propOrder = {
    "idCondicionFinanciera",
    "tasa",
    "fondo",
    "baseCalculo",
    "principal",
    "interes",
    "periodoGracia",
    "tratamientoDiasFeriados",
    "tipoCalendario",
    "moverEntreMeses",
    "calendarioComplejo"
})
public class CondicionesFinancieras {

    protected long idCondicionFinanciera;
    @XmlElement(required = true)
    protected TasaType tasa;
    @XmlElement(required = true)
    protected FondoType fondo;
    @XmlElement(required = true)
    protected Catalogo baseCalculo;
    @XmlElement(required = true)
    protected CondicionesFinancieras.Principal principal;
    @XmlElement(required = true)
    protected CondicionesFinancieras.Interes interes;
    @XmlElement(required = true)
    protected FrecuenciaType periodoGracia;
    @XmlElement(required = true)
    protected String tratamientoDiasFeriados;
    @XmlElement(required = true)
    protected Catalogo tipoCalendario;
    protected boolean moverEntreMeses;
    protected List<CalendarioComplejoType> calendarioComplejo;

    /**
     * Obtiene el valor de la propiedad idCondicionFinanciera.
     * 
     */
    public long getIdCondicionFinanciera() {
        return idCondicionFinanciera;
    }

    /**
     * Define el valor de la propiedad idCondicionFinanciera.
     * 
     */
    public void setIdCondicionFinanciera(long value) {
        this.idCondicionFinanciera = value;
    }

    /**
     * Obtiene el valor de la propiedad tasa.
     * 
     * @return
     *     possible object is
     *     {@link TasaType }
     *     
     */
    public TasaType getTasa() {
        return tasa;
    }

    /**
     * Define el valor de la propiedad tasa.
     * 
     * @param value
     *     allowed object is
     *     {@link TasaType }
     *     
     */
    public void setTasa(TasaType value) {
        this.tasa = value;
    }

    /**
     * Obtiene el valor de la propiedad fondo.
     * 
     * @return
     *     possible object is
     *     {@link FondoType }
     *     
     */
    public FondoType getFondo() {
        return fondo;
    }

    /**
     * Define el valor de la propiedad fondo.
     * 
     * @param value
     *     allowed object is
     *     {@link FondoType }
     *     
     */
    public void setFondo(FondoType value) {
        this.fondo = value;
    }

    /**
     * Obtiene el valor de la propiedad baseCalculo.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getBaseCalculo() {
        return baseCalculo;
    }

    /**
     * Define el valor de la propiedad baseCalculo.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setBaseCalculo(Catalogo value) {
        this.baseCalculo = value;
    }

    /**
     * Obtiene el valor de la propiedad principal.
     * 
     * @return
     *     possible object is
     *     {@link CondicionesFinancieras.Principal }
     *     
     */
    public CondicionesFinancieras.Principal getPrincipal() {
        return principal;
    }

    /**
     * Define el valor de la propiedad principal.
     * 
     * @param value
     *     allowed object is
     *     {@link CondicionesFinancieras.Principal }
     *     
     */
    public void setPrincipal(CondicionesFinancieras.Principal value) {
        this.principal = value;
    }

    /**
     * Obtiene el valor de la propiedad interes.
     * 
     * @return
     *     possible object is
     *     {@link CondicionesFinancieras.Interes }
     *     
     */
    public CondicionesFinancieras.Interes getInteres() {
        return interes;
    }

    /**
     * Define el valor de la propiedad interes.
     * 
     * @param value
     *     allowed object is
     *     {@link CondicionesFinancieras.Interes }
     *     
     */
    public void setInteres(CondicionesFinancieras.Interes value) {
        this.interes = value;
    }

    /**
     * Obtiene el valor de la propiedad periodoGracia.
     * 
     * @return
     *     possible object is
     *     {@link FrecuenciaType }
     *     
     */
    public FrecuenciaType getPeriodoGracia() {
        return periodoGracia;
    }

    /**
     * Define el valor de la propiedad periodoGracia.
     * 
     * @param value
     *     allowed object is
     *     {@link FrecuenciaType }
     *     
     */
    public void setPeriodoGracia(FrecuenciaType value) {
        this.periodoGracia = value;
    }

    /**
     * Obtiene el valor de la propiedad tratamientoDiasFeriados.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTratamientoDiasFeriados() {
        return tratamientoDiasFeriados;
    }

    /**
     * Define el valor de la propiedad tratamientoDiasFeriados.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTratamientoDiasFeriados(String value) {
        this.tratamientoDiasFeriados = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCalendario.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getTipoCalendario() {
        return tipoCalendario;
    }

    /**
     * Define el valor de la propiedad tipoCalendario.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setTipoCalendario(Catalogo value) {
        this.tipoCalendario = value;
    }

    /**
     * Obtiene el valor de la propiedad moverEntreMeses.
     * 
     */
    public boolean isMoverEntreMeses() {
        return moverEntreMeses;
    }

    /**
     * Define el valor de la propiedad moverEntreMeses.
     * 
     */
    public void setMoverEntreMeses(boolean value) {
        this.moverEntreMeses = value;
    }

    /**
     * Gets the value of the calendarioComplejo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calendarioComplejo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalendarioComplejo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalendarioComplejoType }
     * 
     * 
     */
    public List<CalendarioComplejoType> getCalendarioComplejo() {
        if (calendarioComplejo == null) {
            calendarioComplejo = new ArrayList<CalendarioComplejoType>();
        }
        return this.calendarioComplejo;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.bcie.org/DesembolsoBO}FrecuenciaType">
     *       &lt;sequence>
     *         &lt;element name="NumeroCuotas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "numeroCuotas"
    })
    public static class Interes
        extends FrecuenciaType
    {

        @XmlElementRef(name = "NumeroCuotas", namespace = "http://www.bcie.org/DesembolsoBO", type = JAXBElement.class, required = false)
        protected JAXBElement<Integer> numeroCuotas;

        /**
         * Obtiene el valor de la propiedad numeroCuotas.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
         *     
         */
        public JAXBElement<Integer> getNumeroCuotas() {
            return numeroCuotas;
        }

        /**
         * Define el valor de la propiedad numeroCuotas.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
         *     
         */
        public void setNumeroCuotas(JAXBElement<Integer> value) {
            this.numeroCuotas = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.bcie.org/DesembolsoBO}FrecuenciaType">
     *       &lt;sequence>
     *         &lt;element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="NumeroCuotas" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "fechaVencimiento",
        "numeroCuotas"
    })
    public static class Principal
        extends FrecuenciaType
    {

        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar fechaVencimiento;
        @XmlElementRef(name = "NumeroCuotas", namespace = "http://www.bcie.org/DesembolsoBO", type = JAXBElement.class, required = false)
        protected JAXBElement<Integer> numeroCuotas;

        /**
         * Obtiene el valor de la propiedad fechaVencimiento.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getFechaVencimiento() {
            return fechaVencimiento;
        }

        /**
         * Define el valor de la propiedad fechaVencimiento.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setFechaVencimiento(XMLGregorianCalendar value) {
            this.fechaVencimiento = value;
        }

        /**
         * Obtiene el valor de la propiedad numeroCuotas.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
         *     
         */
        public JAXBElement<Integer> getNumeroCuotas() {
            return numeroCuotas;
        }

        /**
         * Define el valor de la propiedad numeroCuotas.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
         *     
         */
        public void setNumeroCuotas(JAXBElement<Integer> value) {
            this.numeroCuotas = value;
        }

    }

}
