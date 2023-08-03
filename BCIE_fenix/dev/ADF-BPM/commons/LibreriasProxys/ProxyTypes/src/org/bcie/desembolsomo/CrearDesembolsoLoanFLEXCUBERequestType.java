
package org.bcie.desembolsomo;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.clientebo.ClienteBasicType;
import org.bcie.lineacreditobo.LineaCredito;
import org.bcie.operacionbo.OperacionBasicType;


/**
 * <p>Clase Java para CrearDesembolsoLoanFLEXCUBERequestType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CrearDesembolsoLoanFLEXCUBERequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cliente" type="{http://www.bcie.org/ClienteBO}ClienteBasicType"/>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}OperacionBasicType"/>
 *         &lt;element name="LineaCredito" type="{http://www.bcie.org/LineaCreditoBO}LineaCredito"/>
 *         &lt;element name="TipoOperacion" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrearDesembolsoLoanFLEXCUBERequestType", propOrder = {
    "cliente",
    "operacion",
    "lineaCredito",
    "tipoOperacion"
})
public class CrearDesembolsoLoanFLEXCUBERequestType {

    @XmlElement(name = "Cliente", required = true)
    protected ClienteBasicType cliente;
    @XmlElement(name = "Operacion", required = true)
    protected OperacionBasicType operacion;
    @XmlElement(name = "LineaCredito", required = true)
    protected LineaCredito lineaCredito;
    @XmlElement(name = "TipoOperacion", required = true)
    protected BigInteger tipoOperacion;

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link ClienteBasicType }
     *     
     */
    public ClienteBasicType getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link ClienteBasicType }
     *     
     */
    public void setCliente(ClienteBasicType value) {
        this.cliente = value;
    }

    /**
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link OperacionBasicType }
     *     
     */
    public OperacionBasicType getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link OperacionBasicType }
     *     
     */
    public void setOperacion(OperacionBasicType value) {
        this.operacion = value;
    }

    /**
     * Obtiene el valor de la propiedad lineaCredito.
     * 
     * @return
     *     possible object is
     *     {@link LineaCredito }
     *     
     */
    public LineaCredito getLineaCredito() {
        return lineaCredito;
    }

    /**
     * Define el valor de la propiedad lineaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link LineaCredito }
     *     
     */
    public void setLineaCredito(LineaCredito value) {
        this.lineaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoOperacion.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Define el valor de la propiedad tipoOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTipoOperacion(BigInteger value) {
        this.tipoOperacion = value;
    }

}
