
package org.bcie.condicionbo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.desembolsobo.ContratoDesembolso;
import org.bcie.desembolsobo.SolicitudDesembolso;
import org.bcie.operacionbo.OperacionBasicType;


/**
 * <p>Clase Java para TransaccionCondicion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TransaccionCondicion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdTransaccion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}OperacionBasicType" minOccurs="0"/>
 *         &lt;element name="Solicitud" type="{http://www.bcie.org/DesembolsoBO}SolicitudDesembolso" minOccurs="0"/>
 *         &lt;element name="ContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso" minOccurs="0"/>
 *         &lt;element name="Condicion" type="{http://www.bcie.org/CondicionBO}Condicion" maxOccurs="unbounded"/>
 *         &lt;element name="EstadoTCC" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="enProceso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="agrupador" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="EventoCondicion" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="subEstadoTCC" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransaccionCondicion", propOrder = {
    "idTransaccion",
    "operacion",
    "solicitud",
    "contratoDesembolso",
    "condicion",
    "estadoTCC",
    "estatus",
    "enProceso",
    "agrupador",
    "eventoCondicion",
    "subEstadoTCC"
})
public class TransaccionCondicion {

    @XmlElement(name = "IdTransaccion")
    protected Long idTransaccion;
    @XmlElement(name = "Operacion")
    protected OperacionBasicType operacion;
    @XmlElement(name = "Solicitud")
    protected SolicitudDesembolso solicitud;
    @XmlElement(name = "ContratoDesembolso")
    protected ContratoDesembolso contratoDesembolso;
    @XmlElement(name = "Condicion", required = true)
    protected List<Condicion> condicion;
    @XmlElement(name = "EstadoTCC", required = true)
    protected Catalogo estadoTCC;
    protected long estatus;
    protected boolean enProceso;
    protected Long agrupador;
    @XmlElement(name = "EventoCondicion")
    protected Catalogo eventoCondicion;
    protected Catalogo subEstadoTCC;

    /**
     * Obtiene el valor de la propiedad idTransaccion.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * Define el valor de la propiedad idTransaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTransaccion(Long value) {
        this.idTransaccion = value;
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
     * Obtiene el valor de la propiedad solicitud.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudDesembolso }
     *     
     */
    public SolicitudDesembolso getSolicitud() {
        return solicitud;
    }

    /**
     * Define el valor de la propiedad solicitud.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudDesembolso }
     *     
     */
    public void setSolicitud(SolicitudDesembolso value) {
        this.solicitud = value;
    }

    /**
     * Obtiene el valor de la propiedad contratoDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link ContratoDesembolso }
     *     
     */
    public ContratoDesembolso getContratoDesembolso() {
        return contratoDesembolso;
    }

    /**
     * Define el valor de la propiedad contratoDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link ContratoDesembolso }
     *     
     */
    public void setContratoDesembolso(ContratoDesembolso value) {
        this.contratoDesembolso = value;
    }

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
     * Obtiene el valor de la propiedad estadoTCC.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEstadoTCC() {
        return estadoTCC;
    }

    /**
     * Define el valor de la propiedad estadoTCC.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEstadoTCC(Catalogo value) {
        this.estadoTCC = value;
    }

    /**
     * Obtiene el valor de la propiedad estatus.
     * 
     */
    public long getEstatus() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     */
    public void setEstatus(long value) {
        this.estatus = value;
    }

    /**
     * Obtiene el valor de la propiedad enProceso.
     * 
     */
    public boolean isEnProceso() {
        return enProceso;
    }

    /**
     * Define el valor de la propiedad enProceso.
     * 
     */
    public void setEnProceso(boolean value) {
        this.enProceso = value;
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
     * Obtiene el valor de la propiedad eventoCondicion.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getEventoCondicion() {
        return eventoCondicion;
    }

    /**
     * Define el valor de la propiedad eventoCondicion.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setEventoCondicion(Catalogo value) {
        this.eventoCondicion = value;
    }

    /**
     * Obtiene el valor de la propiedad subEstadoTCC.
     * 
     * @return
     *     possible object is
     *     {@link Catalogo }
     *     
     */
    public Catalogo getSubEstadoTCC() {
        return subEstadoTCC;
    }

    /**
     * Define el valor de la propiedad subEstadoTCC.
     * 
     * @param value
     *     allowed object is
     *     {@link Catalogo }
     *     
     */
    public void setSubEstadoTCC(Catalogo value) {
        this.subEstadoTCC = value;
    }

}
