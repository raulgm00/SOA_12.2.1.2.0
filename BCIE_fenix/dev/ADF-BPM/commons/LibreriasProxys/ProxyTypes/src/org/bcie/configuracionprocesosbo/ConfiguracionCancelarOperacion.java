
package org.bcie.configuracionprocesosbo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.bcie.adquisicionbo.ListaAdquisicion;
import org.bcie.comisionbo.ListaComision;
import org.bcie.condicionbo.ListaTransaccionCondicion;
import org.bcie.crearbitacoraprocesobo.ListaInstanciaProceso;
import org.bcie.desembolsobo.ListaSolicitudDesembolso;
import org.bcie.implementacionpctbo.ListaImplementacionPCT;
import org.bcie.matriztccbo.ListaEnmiendaTCC;
import org.bcie.operacionbo.Operacion;
import org.bcie.productobo.Producto;


/**
 * Elemento para la configuración cancelar Operacion
 * 
 * <p>Clase Java para ConfiguracionCancelarOperacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfiguracionCancelarOperacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operacion" type="{http://www.bcie.org/OperacionBO}Operacion"/>
 *         &lt;element name="producto" type="{http://www.bcie.org/ProductoBO}Producto"/>
 *         &lt;element name="RolesEquipoTrabajo" type="{http://www.bcie.org/ConfiguracionProcesosBO}EquipoTrabajo"/>
 *         &lt;element name="gerente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Enmiendas" type="{http://www.bcie.org/MatrizTCCBO}listaEnmiendaTCC"/>
 *         &lt;element name="Comisiones" type="{http://www.bcie.org/ComisionBO}listaComision"/>
 *         &lt;element name="ImplementacionPCT" type="{http://www.bcie.org/ImplementacionPctBO}ListaImplementacionPCT"/>
 *         &lt;element name="SolicitudDesembolso" type="{http://www.bcie.org/DesembolsoBO}ListaSolicitudDesembolso"/>
 *         &lt;element name="Condiciones" type="{http://www.bcie.org/CondicionBO}ListaTransaccionCondicion"/>
 *         &lt;element name="Adquisiciones" type="{http://www.bcie.org/AdquisicionBO}ListaAdquisicion"/>
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
@XmlType(name = "ConfiguracionCancelarOperacion", propOrder = {
    "operacion",
    "producto",
    "rolesEquipoTrabajo",
    "gerente",
    "enmiendas",
    "comisiones",
    "implementacionPCT",
    "solicitudDesembolso",
    "condiciones",
    "adquisiciones",
    "registrarComision"
})
public class ConfiguracionCancelarOperacion {

    @XmlElement(required = true)
    protected Operacion operacion;
    @XmlElement(required = true)
    protected Producto producto;
    @XmlElement(name = "RolesEquipoTrabajo", required = true)
    protected EquipoTrabajo rolesEquipoTrabajo;
    protected boolean gerente;
    @XmlElement(name = "Enmiendas", required = true)
    protected ListaEnmiendaTCC enmiendas;
    @XmlElement(name = "Comisiones", required = true)
    protected ListaComision comisiones;
    @XmlElement(name = "ImplementacionPCT", required = true)
    protected ListaImplementacionPCT implementacionPCT;
    @XmlElement(name = "SolicitudDesembolso", required = true)
    protected ListaSolicitudDesembolso solicitudDesembolso;
    @XmlElement(name = "Condiciones", required = true)
    protected ListaTransaccionCondicion condiciones;
    @XmlElement(name = "Adquisiciones", required = true)
    protected ListaAdquisicion adquisiciones;
    @XmlElement(name = "RegistrarComision", required = true)
    protected ListaInstanciaProceso registrarComision;

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
     * Obtiene el valor de la propiedad producto.
     * 
     * @return
     *     possible object is
     *     {@link Producto }
     *     
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Define el valor de la propiedad producto.
     * 
     * @param value
     *     allowed object is
     *     {@link Producto }
     *     
     */
    public void setProducto(Producto value) {
        this.producto = value;
    }

    /**
     * Obtiene el valor de la propiedad rolesEquipoTrabajo.
     * 
     * @return
     *     possible object is
     *     {@link EquipoTrabajo }
     *     
     */
    public EquipoTrabajo getRolesEquipoTrabajo() {
        return rolesEquipoTrabajo;
    }

    /**
     * Define el valor de la propiedad rolesEquipoTrabajo.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipoTrabajo }
     *     
     */
    public void setRolesEquipoTrabajo(EquipoTrabajo value) {
        this.rolesEquipoTrabajo = value;
    }

    /**
     * Obtiene el valor de la propiedad gerente.
     * 
     */
    public boolean isGerente() {
        return gerente;
    }

    /**
     * Define el valor de la propiedad gerente.
     * 
     */
    public void setGerente(boolean value) {
        this.gerente = value;
    }

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
