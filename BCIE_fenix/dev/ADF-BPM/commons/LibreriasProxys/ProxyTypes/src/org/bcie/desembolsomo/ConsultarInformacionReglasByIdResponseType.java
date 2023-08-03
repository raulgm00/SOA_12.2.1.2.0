
package org.bcie.desembolsomo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.clientebo.Cliente;
import org.bcie.desembolsobo.ContratoDesembolso;
import org.bcie.lineacreditobo.LineaCredito;
import org.bcie.operacionbo.Operacion;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ConsultarInformacionReglasByIdResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarInformacionReglasByIdResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cliente" type="{http://www.bcie.org/ClienteBO}Cliente"/>
 *         &lt;element name="LineaCredito" type="{http://www.bcie.org/LineaCreditoBO}LineaCredito"/>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *         &lt;element name="Desembolso" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso"/>
 *         &lt;element name="Resultado" type="{http://www.bcie.org/ResultBO}Resultado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarInformacionReglasByIdResponseType", propOrder = {
    "cliente",
    "lineaCredito",
    "operacion",
    "desembolso",
    "resultado"
})
public class ConsultarInformacionReglasByIdResponseType {

    @XmlElement(name = "Cliente", required = true)
    protected Cliente cliente;
    @XmlElement(name = "LineaCredito", required = true)
    protected LineaCredito lineaCredito;
    @XmlElement(name = "Operacion", required = true)
    protected Operacion operacion;
    @XmlElement(name = "Desembolso", required = true)
    protected ContratoDesembolso desembolso;
    @XmlElement(name = "Resultado", required = true)
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link Cliente }
     *     
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Cliente }
     *     
     */
    public void setCliente(Cliente value) {
        this.cliente = value;
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
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link Operacion }
     *     
     */
    public Operacion getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Operacion }
     *     
     */
    public void setOperacion(Operacion value) {
        this.operacion = value;
    }

    /**
     * Obtiene el valor de la propiedad desembolso.
     * 
     * @return
     *     possible object is
     *     {@link ContratoDesembolso }
     *     
     */
    public ContratoDesembolso getDesembolso() {
        return desembolso;
    }

    /**
     * Define el valor de la propiedad desembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link ContratoDesembolso }
     *     
     */
    public void setDesembolso(ContratoDesembolso value) {
        this.desembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad resultado.
     * 
     * @return
     *     possible object is
     *     {@link Resultado }
     *     
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * Define el valor de la propiedad resultado.
     * 
     * @param value
     *     allowed object is
     *     {@link Resultado }
     *     
     */
    public void setResultado(Resultado value) {
        this.resultado = value;
    }

}
