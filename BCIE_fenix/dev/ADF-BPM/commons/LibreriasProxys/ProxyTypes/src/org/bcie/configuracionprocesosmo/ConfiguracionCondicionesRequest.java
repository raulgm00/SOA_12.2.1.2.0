
package org.bcie.configuracionprocesosmo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.bcie.condicionbo.Condicion;


/**
 * <p>Clase Java para ConfiguracionCondicionesRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionCondicionesRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idOperacion" type="{http://www.bcie.org/OperacionBO}idOperacion"/>
 *         &lt;element name="idEventoCondicion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idSolicitud" type="{http://www.bcie.org/DesembolsoBO}idDesembolso" minOccurs="0"/>
 *         &lt;element name="idContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}idDesembolso" minOccurs="0"/>
 *         &lt;element name="Agrupador" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="instanciaProceso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="listaCondicion" type="{http://www.bcie.org/CondicionBO}Condicion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionCondicionesRequest", propOrder = {
    "idOperacion",
    "idEventoCondicion",
    "idSolicitud",
    "idContratoDesembolso",
    "agrupador",
    "instanciaProceso",
    "listaCondicion"
})
public class ConfiguracionCondicionesRequest {

    protected long idOperacion;
    protected Long idEventoCondicion;
    protected Long idSolicitud;
    protected Long idContratoDesembolso;
    @XmlElement(name = "Agrupador")
    protected Long agrupador;
    @XmlElementRef(name = "instanciaProceso", namespace = "http://www.bcie.org/ConfiguracionProcesosMO", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> instanciaProceso;
    protected List<Condicion> listaCondicion;

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
     * Obtiene el valor de la propiedad idEventoCondicion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdEventoCondicion() {
        return idEventoCondicion;
    }

    /**
     * Define el valor de la propiedad idEventoCondicion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdEventoCondicion(Long value) {
        this.idEventoCondicion = value;
    }

    /**
     * Obtiene el valor de la propiedad idSolicitud.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * Define el valor de la propiedad idSolicitud.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdSolicitud(Long value) {
        this.idSolicitud = value;
    }

    /**
     * Obtiene el valor de la propiedad idContratoDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdContratoDesembolso() {
        return idContratoDesembolso;
    }

    /**
     * Define el valor de la propiedad idContratoDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdContratoDesembolso(Long value) {
        this.idContratoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad agrupador.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAgrupador() {
        return agrupador;
    }

    /**
     * Define el valor de la propiedad agrupador.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAgrupador(Long value) {
        this.agrupador = value;
    }

    /**
     * Obtiene el valor de la propiedad instanciaProceso.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getInstanciaProceso() {
        return instanciaProceso;
    }

    /**
     * Define el valor de la propiedad instanciaProceso.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setInstanciaProceso(JAXBElement<Integer> value) {
        this.instanciaProceso = value;
    }

    /**
     * Gets the value of the listaCondicion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaCondicion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaCondicion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Condicion }
     * 
     * 
     */
    public List<Condicion> getListaCondicion() {
        if (listaCondicion == null) {
            listaCondicion = new ArrayList<Condicion>();
        }
        return this.listaCondicion;
    }

}
