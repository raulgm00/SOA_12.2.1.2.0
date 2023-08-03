
package org.bcie.operacionmo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.resultbo.Resultado;


/**
 * <p>Clase Java para ReactivarOperacionResponseType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReactivarOperacionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}idOperacion" minOccurs="0"/>
 *         &lt;element name="ListaEstatus" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="idProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="nombreProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="mensajeEstatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
@XmlType(name = "ReactivarOperacionResponseType", propOrder = {
    "operacion",
    "listaEstatus",
    "resultado"
})
public class ReactivarOperacionResponseType {

    @XmlElement(name = "Operacion")
    protected Long operacion;
    @XmlElement(name = "ListaEstatus")
    protected List<ReactivarOperacionResponseType.ListaEstatus> listaEstatus;
    @XmlElement(name = "Resultado")
    protected Resultado resultado;

    /**
     * Obtiene el valor de la propiedad operacion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOperacion() {
        return operacion;
    }

    /**
     * Define el valor de la propiedad operacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOperacion(Long value) {
        this.operacion = value;
    }

    /**
     * Gets the value of the listaEstatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaEstatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaEstatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReactivarOperacionResponseType.ListaEstatus }
     * 
     * 
     */
    public List<ReactivarOperacionResponseType.ListaEstatus> getListaEstatus() {
        if (listaEstatus == null) {
            listaEstatus = new ArrayList<ReactivarOperacionResponseType.ListaEstatus>();
        }
        return this.listaEstatus;
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
     *         &lt;element name="idProceso" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="nombreProceso" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="mensajeEstatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "idProceso",
        "nombreProceso",
        "estatus",
        "mensajeEstatus"
    })
    public static class ListaEstatus {

        protected long idProceso;
        @XmlElement(required = true)
        protected String nombreProceso;
        @XmlElement(required = true)
        protected String estatus;
        @XmlElement(required = true)
        protected String mensajeEstatus;

        /**
         * Obtiene el valor de la propiedad idProceso.
         * 
         */
        public long getIdProceso() {
            return idProceso;
        }

        /**
         * Define el valor de la propiedad idProceso.
         * 
         */
        public void setIdProceso(long value) {
            this.idProceso = value;
        }

        /**
         * Obtiene el valor de la propiedad nombreProceso.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombreProceso() {
            return nombreProceso;
        }

        /**
         * Define el valor de la propiedad nombreProceso.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombreProceso(String value) {
            this.nombreProceso = value;
        }

        /**
         * Obtiene el valor de la propiedad estatus.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEstatus() {
            return estatus;
        }

        /**
         * Define el valor de la propiedad estatus.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEstatus(String value) {
            this.estatus = value;
        }

        /**
         * Obtiene el valor de la propiedad mensajeEstatus.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMensajeEstatus() {
            return mensajeEstatus;
        }

        /**
         * Define el valor de la propiedad mensajeEstatus.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMensajeEstatus(String value) {
            this.mensajeEstatus = value;
        }

    }

}
