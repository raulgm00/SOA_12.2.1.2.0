
package org.bcie.configuracionprocesosbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.adquisicionbo.ListaAdquisicion;
import org.bcie.comisionbo.ListaComision;
import org.bcie.condicionbo.ListaTransaccionCondicion;
import org.bcie.crearbitacoraprocesobo.ListaInstanciaProceso;
import org.bcie.desembolsobo.ListaContratoDesembolso;
import org.bcie.desembolsobo.ListaSolicitudDesembolso;
import org.bcie.implementacionpctbo.ListaImplementacionPCT;
import org.bcie.matriztccbo.ListaEnmiendaTCC;


/**
 * <p>Clase Java para ConfiguracionReasignarOperacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionReasignarOperacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Enmiendas" type="{http://www.bcie.org/MatrizTCCBO}listaEnmiendaTCC"/>
 *         &lt;element name="Comisiones" type="{http://www.bcie.org/ComisionBO}listaComision"/>
 *         &lt;element name="ImplementacionPCT" type="{http://www.bcie.org/ImplementacionPctBO}ListaImplementacionPCT"/>
 *         &lt;element name="SolicitudDesembolso" type="{http://www.bcie.org/DesembolsoBO}ListaSolicitudDesembolso"/>
 *         &lt;element name="ContratoDesembolso" type="{http://www.bcie.org/DesembolsoBO}ListaContratoDesembolso"/>
 *         &lt;element name="Condiciones" type="{http://www.bcie.org/CondicionBO}ListaTransaccionCondicion"/>
 *         &lt;element name="Adquisiciones" type="{http://www.bcie.org/AdquisicionBO}ListaAdquisicion"/>
 *         &lt;element name="Prepago" type="{http://www.bcie.org/CrearBitacoraProcesoBO}ListaInstanciaProceso"/>
 *         &lt;element name="RegistrarComision" type="{http://www.bcie.org/CrearBitacoraProcesoBO}ListaInstanciaProceso"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfiguracionReasignarOperacion", propOrder = {
    "enmiendas",
    "comisiones",
    "implementacionPCT",
    "solicitudDesembolso",
    "contratoDesembolso",
    "condiciones",
    "adquisiciones",
    "prepago",
    "registrarComision"
})
public class ConfiguracionReasignarOperacion {

    @XmlElement(name = "Enmiendas", required = true)
    protected ListaEnmiendaTCC enmiendas;
    @XmlElement(name = "Comisiones", required = true)
    protected ListaComision comisiones;
    @XmlElement(name = "ImplementacionPCT", required = true)
    protected ListaImplementacionPCT implementacionPCT;
    @XmlElement(name = "SolicitudDesembolso", required = true)
    protected ListaSolicitudDesembolso solicitudDesembolso;
    @XmlElement(name = "ContratoDesembolso", required = true)
    protected ListaContratoDesembolso contratoDesembolso;
    @XmlElement(name = "Condiciones", required = true)
    protected ListaTransaccionCondicion condiciones;
    @XmlElement(name = "Adquisiciones", required = true)
    protected ListaAdquisicion adquisiciones;
    @XmlElement(name = "Prepago", required = true)
    protected ListaInstanciaProceso prepago;
    @XmlElement(name = "RegistrarComision", required = true)
    protected ListaInstanciaProceso registrarComision;

    /**
     * Obtiene el valor de la propiedad enmiendas.
     * 
     * @return
     *     possible object is
     *     {@link ListaEnmiendaTCC }
     *     
     */
    public ListaEnmiendaTCC getEnmiendas() {
        return enmiendas;
    }

    /**
     * Define el valor de la propiedad enmiendas.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaEnmiendaTCC }
     *     
     */
    public void setEnmiendas(ListaEnmiendaTCC value) {
        this.enmiendas = value;
    }

    /**
     * Obtiene el valor de la propiedad comisiones.
     * 
     * @return
     *     possible object is
     *     {@link ListaComision }
     *     
     */
    public ListaComision getComisiones() {
        return comisiones;
    }

    /**
     * Define el valor de la propiedad comisiones.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaComision }
     *     
     */
    public void setComisiones(ListaComision value) {
        this.comisiones = value;
    }

    /**
     * Obtiene el valor de la propiedad implementacionPCT.
     * 
     * @return
     *     possible object is
     *     {@link ListaImplementacionPCT }
     *     
     */
    public ListaImplementacionPCT getImplementacionPCT() {
        return implementacionPCT;
    }

    /**
     * Define el valor de la propiedad implementacionPCT.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaImplementacionPCT }
     *     
     */
    public void setImplementacionPCT(ListaImplementacionPCT value) {
        this.implementacionPCT = value;
    }

    /**
     * Obtiene el valor de la propiedad solicitudDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link ListaSolicitudDesembolso }
     *     
     */
    public ListaSolicitudDesembolso getSolicitudDesembolso() {
        return solicitudDesembolso;
    }

    /**
     * Define el valor de la propiedad solicitudDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaSolicitudDesembolso }
     *     
     */
    public void setSolicitudDesembolso(ListaSolicitudDesembolso value) {
        this.solicitudDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad contratoDesembolso.
     * 
     * @return
     *     possible object is
     *     {@link ListaContratoDesembolso }
     *     
     */
    public ListaContratoDesembolso getContratoDesembolso() {
        return contratoDesembolso;
    }

    /**
     * Define el valor de la propiedad contratoDesembolso.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaContratoDesembolso }
     *     
     */
    public void setContratoDesembolso(ListaContratoDesembolso value) {
        this.contratoDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad condiciones.
     * 
     * @return
     *     possible object is
     *     {@link ListaTransaccionCondicion }
     *     
     */
    public ListaTransaccionCondicion getCondiciones() {
        return condiciones;
    }

    /**
     * Define el valor de la propiedad condiciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaTransaccionCondicion }
     *     
     */
    public void setCondiciones(ListaTransaccionCondicion value) {
        this.condiciones = value;
    }

    /**
     * Obtiene el valor de la propiedad adquisiciones.
     * 
     * @return
     *     possible object is
     *     {@link ListaAdquisicion }
     *     
     */
    public ListaAdquisicion getAdquisiciones() {
        return adquisiciones;
    }

    /**
     * Define el valor de la propiedad adquisiciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaAdquisicion }
     *     
     */
    public void setAdquisiciones(ListaAdquisicion value) {
        this.adquisiciones = value;
    }

    /**
     * Obtiene el valor de la propiedad prepago.
     * 
     * @return
     *     possible object is
     *     {@link ListaInstanciaProceso }
     *     
     */
    public ListaInstanciaProceso getPrepago() {
        return prepago;
    }

    /**
     * Define el valor de la propiedad prepago.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaInstanciaProceso }
     *     
     */
    public void setPrepago(ListaInstanciaProceso value) {
        this.prepago = value;
    }

    /**
     * Obtiene el valor de la propiedad registrarComision.
     * 
     * @return
     *     possible object is
     *     {@link ListaInstanciaProceso }
     *     
     */
    public ListaInstanciaProceso getRegistrarComision() {
        return registrarComision;
    }

    /**
     * Define el valor de la propiedad registrarComision.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaInstanciaProceso }
     *     
     */
    public void setRegistrarComision(ListaInstanciaProceso value) {
        this.registrarComision = value;
    }

}
