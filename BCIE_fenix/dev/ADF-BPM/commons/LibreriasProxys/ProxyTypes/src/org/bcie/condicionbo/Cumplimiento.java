
package org.bcie.condicionbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.catalogobo.Catalogo;
import org.bcie.desembolsobo.ContratoDesembolso;
import org.bcie.desembolsobo.SolicitudDesembolso;
import org.bcie.operacionbo.OperacionBasicType;


/**
 * <p>Clase Java para Cumplimiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Cumplimiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Operacion" type="{http://www.bcie.org/OperacionBO}OperacionBasicType" minOccurs="0"/>
 *         &lt;element name="Solicitud" type="{http://www.bcie.org/DesembolsoBO}SolicitudDesembolso" minOccurs="0"/>
 *         &lt;element name="ContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}ContratoDesembolso" minOccurs="0"/>
 *         &lt;element name="EstadoTCC" type="{http://www.bcie.org/CatalogoBO}Catalogo"/>
 *         &lt;element name="EventoCondicion" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="agrupador" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="subEstadoTCC" type="{http://www.bcie.org/CatalogoBO}Catalogo" minOccurs="0"/>
 *         &lt;element name="enProceso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cumplimiento", propOrder = {
    "id",
    "operacion",
    "solicitud",
    "contratoDesembolso",
    "estadoTCC",
    "eventoCondicion",
    "agrupador",
    "estatus",
    "subEstadoTCC",
    "enProceso"
})
public class Cumplimiento {

    @XmlElement(name = "Id")
    protected Long id;
    @XmlElement(name = "Operacion")
    protected OperacionBasicType operacion;
    @XmlElement(name = "Solicitud")
    protected SolicitudDesembolso solicitud;
    @XmlElement(name = "ContratoDesembolso")
    protected ContratoDesembolso contratoDesembolso;
    @XmlElement(name = "EstadoTCC", required = true)
    protected Catalogo estadoTCC;
    @XmlElement(name = "EventoCondicion")
    protected Catalogo eventoCondicion;
    protected Long agrupador;
    protected long estatus;
    protected Catalogo subEstadoTCC;
    protected boolean enProceso;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
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

}
