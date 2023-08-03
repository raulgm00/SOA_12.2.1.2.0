
package org.bcie.lineacreditobo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.comisionbo.Comision;
import org.bcie.commonbo.ParameterType;
import org.bcie.condicionbo.Condicion;
import org.bcie.desembolsobo.ContratoDesembolso;
import org.bcie.herramientacebo.HerramientaCEType;
import org.bcie.terminobo.Termino;


/**
 * <p>Clase Java para LineaCredito complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LineaCredito">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bcie.org/LineaCreditoBO}LineaCreditoBasicType">
 *       &lt;sequence>
 *         &lt;element name="Condicion" type="{http://www.bcie.org/CondicionBO}Condicion" maxOccurs="unbounded"/>
 *         &lt;element name="Termino" type="{http://www.bcie.org/TerminoBO}Termino" maxOccurs="unbounded"/>
 *         &lt;element name="Comision" type="{http://www.bcie.org/ComisionBO}Comision" maxOccurs="unbounded"/>
 *         &lt;element name="Fuente" type="{http://www.bcie.org/LineaCreditoBO}Fuente" maxOccurs="unbounded"/>
 *         &lt;element name="ContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="atributos" type="{http://www.bcie.org/CommonBO}parameterType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LineaCreditoFlexcube" type="{http://www.bcie.org/LineaCreditoBO}LineaCreditoFlexcubeType" minOccurs="0"/>
 *         &lt;element name="HerramientaCE" type="{http://www.bcie.org/HerramientaCEBO}HerramientaCEType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineaCredito", propOrder = {
    "condicion",
    "termino",
    "comision",
    "fuente",
    "contratoDesembolso",
    "atributos",
    "lineaCreditoFlexcube",
    "herramientaCE"
})
public class LineaCredito
    extends LineaCreditoBasicType
{

    @XmlElement(name = "Condicion", required = true)
    protected List<Condicion> condicion;
    @XmlElement(name = "Termino", required = true)
    protected List<Termino> termino;
    @XmlElement(name = "Comision", required = true)
    protected List<Comision> comision;
    @XmlElement(name = "Fuente", required = true)
    protected List<Fuente> fuente;
    @XmlElement(name = "ContratoDesembolso")
    protected List<ContratoDesembolso> contratoDesembolso;
    protected List<ParameterType> atributos;
    @XmlElement(name = "LineaCreditoFlexcube")
    protected LineaCreditoFlexcubeType lineaCreditoFlexcube;
    @XmlElement(name = "HerramientaCE")
    protected HerramientaCEType herramientaCE;

    /**
     * Gets the value of the condicion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the condicion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCondicion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Condicion }
     * 
     * 
     */
    public List<Condicion> getCondicion() {
        if (condicion == null) {
            condicion = new ArrayList<Condicion>();
        }
        return this.condicion;
    }

    /**
     * Gets the value of the termino property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the termino property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTermino().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Termino }
     * 
     * 
     */
    public List<Termino> getTermino() {
        if (termino == null) {
            termino = new ArrayList<Termino>();
        }
        return this.termino;
    }

    /**
     * Gets the value of the comision property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comision property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComision().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comision }
     * 
     * 
     */
    public List<Comision> getComision() {
        if (comision == null) {
            comision = new ArrayList<Comision>();
        }
        return this.comision;
    }

    /**
     * Gets the value of the fuente property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fuente property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFuente().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Fuente }
     * 
     * 
     */
    public List<Fuente> getFuente() {
        if (fuente == null) {
            fuente = new ArrayList<Fuente>();
        }
        return this.fuente;
    }

    /**
     * Gets the value of the contratoDesembolso property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contratoDesembolso property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContratoDesembolso().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContratoDesembolso }
     * 
     * 
     */
    public List<ContratoDesembolso> getContratoDesembolso() {
        if (contratoDesembolso == null) {
            contratoDesembolso = new ArrayList<ContratoDesembolso>();
        }
        return this.contratoDesembolso;
    }

    /**
     * Gets the value of the atributos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the atributos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAtributos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterType }
     * 
     * 
     */
    public List<ParameterType> getAtributos() {
        if (atributos == null) {
            atributos = new ArrayList<ParameterType>();
        }
        return this.atributos;
    }

    /**
     * Obtiene el valor de la propiedad lineaCreditoFlexcube.
     * 
     * @return
     *     possible object is
     *     {@link LineaCreditoFlexcubeType }
     *     
     */
    public LineaCreditoFlexcubeType getLineaCreditoFlexcube() {
        return lineaCreditoFlexcube;
    }

    /**
     * Define el valor de la propiedad lineaCreditoFlexcube.
     * 
     * @param value
     *     allowed object is
     *     {@link LineaCreditoFlexcubeType }
     *     
     */
    public void setLineaCreditoFlexcube(LineaCreditoFlexcubeType value) {
        this.lineaCreditoFlexcube = value;
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

}
