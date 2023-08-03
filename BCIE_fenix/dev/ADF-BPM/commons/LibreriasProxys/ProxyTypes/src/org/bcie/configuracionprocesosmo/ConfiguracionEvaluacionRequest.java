
package org.bcie.configuracionprocesosmo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.commonbo.ParameterType;


/**
 * <p>Clase Java para ConfiguracionEvaluacionRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionEvaluacionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idTipoEvaluacion" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="loginUsuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Parameters" type="{http://www.bcie.org/CommonBO}parameterType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionEvaluacionRequest", propOrder = {
    "idOperacion",
    "instanciaProceso",
    "idTipoEvaluacion",
    "loginUsuario",
    "parameters"
})
public class ConfiguracionEvaluacionRequest {

    protected long idOperacion;
    @XmlElement(required = true)
    protected String instanciaProceso;
    @XmlElement(required = true)
    protected BigDecimal idTipoEvaluacion;
    @XmlElement(required = true)
    protected String loginUsuario;
    @XmlElement(name = "Parameters", required = true)
    protected List<ParameterType> parameters;

    /**
     * Obtiene el valor de la propiedad idOperacion.
     * 
     */
    public long getIdOperacion() {
        return idOperacion;
    }

    /**
     * Define el valor de la propiedad idOperacion.
     * 
     */
    public void setIdOperacion(long value) {
        this.idOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanciaProceso(String value) {
        this.instanciaProceso = value;
    }

    /**
     * Obtiene el valor de la propiedad idTipoEvaluacion.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdTipoEvaluacion() {
        return idTipoEvaluacion;
    }

    /**
     * Define el valor de la propiedad idTipoEvaluacion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdTipoEvaluacion(BigDecimal value) {
        this.idTipoEvaluacion = value;
    }

    /**
     * Obtiene el valor de la propiedad loginUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginUsuario() {
        return loginUsuario;
    }

    /**
     * Define el valor de la propiedad loginUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginUsuario(String value) {
        this.loginUsuario = value;
    }

    /**
     * Gets the value of the parameters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterType }
     * 
     * 
     */
    public List<ParameterType> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<ParameterType>();
        }
        return this.parameters;
    }

}
