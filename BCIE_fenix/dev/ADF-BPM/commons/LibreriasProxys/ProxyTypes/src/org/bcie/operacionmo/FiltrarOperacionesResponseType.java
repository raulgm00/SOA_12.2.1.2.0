
package org.bcie.operacionmo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.clientebo.Cliente;
import org.bcie.operacionbo.Operacion;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para FiltrarOperacionesResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FiltrarOperacionesResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultadoFiltro" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}Operacion" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="Cliente" type="{http://www.bcie.org/ClienteBO}Cliente" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CantidadOperaciones" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Resultado" type="{http://www.bcie.org/ResultBO}Resultado" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FiltrarOperacionesResponseType", propOrder = {
    "resultadoFiltro",
    "cantidadOperaciones",
    "resultado"
})
public class FiltrarOperacionesResponseType {

    @XmlElement(name = "ResultadoFiltro", required = true)
    protected List<FiltrarOperacionesResponseType.ResultadoFiltro> resultadoFiltro;
    @XmlElement(name = "CantidadOperaciones")
    protected int cantidadOperaciones;
    @XmlElement(name = "Resultado")
    protected Resultado resultado;

    /**
     * Gets the value of the resultadoFiltro property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultadoFiltro property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultadoFiltro().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FiltrarOperacionesResponseType.ResultadoFiltro }
     * 
     * 
     */
    public List<FiltrarOperacionesResponseType.ResultadoFiltro> getResultadoFiltro() {
        if (resultadoFiltro == null) {
            resultadoFiltro = new ArrayList<FiltrarOperacionesResponseType.ResultadoFiltro>();
        }
        return this.resultadoFiltro;
    }

    /**
     * Obtiene el valor de la propiedad cantidadOperaciones.
     * 
     */
    public int getCantidadOperaciones() {
        return cantidadOperaciones;
    }

    /**
     * Define el valor de la propiedad cantidadOperaciones.
     * 
     */
    public void setCantidadOperaciones(int value) {
        this.cantidadOperaciones = value;
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


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}Operacion" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="Cliente" type="{http://www.bcie.org/ClienteBO}Cliente" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "operacion",
        "cliente"
    })
    public static class ResultadoFiltro {

        @XmlElement(name = "Operacion")
        protected List<Operacion> operacion;
        @XmlElement(name = "Cliente")
        protected List<Cliente> cliente;

        /**
         * Gets the value of the operacion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the operacion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOperacion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Operacion }
         * 
         * 
         */
        public List<Operacion> getOperacion() {
            if (operacion == null) {
                operacion = new ArrayList<Operacion>();
            }
            return this.operacion;
        }

        /**
         * Gets the value of the cliente property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cliente property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCliente().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Cliente }
         * 
         * 
         */
        public List<Cliente> getCliente() {
            if (cliente == null) {
                cliente = new ArrayList<Cliente>();
            }
            return this.cliente;
        }

    }

}
