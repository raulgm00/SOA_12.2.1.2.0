
package org.bcie.lineacreditobo;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para LineaCreditoFlexcubeType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="LineaCreditoFlexcubeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroLineaCreditoResp_out" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumeroContrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DescripcionLinea" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MontoAprobado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CodigoCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Revolvencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Fondo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LineaFinanciera" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Destino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoRecurso" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Pais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActividadEconomica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SectorInstitucional" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Ejecutivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EjeEstrategico" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AreaFocalizacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjeticoEstrategico" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PlantillaLimite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SerialLimite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Saldo" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Disponibilidad" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="DisponibilidadIfacil" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TieneCondEspeciales" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DescCondEspeciales" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaMaxDesembolsar" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="NumeroDesembolsos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineaCreditoFlexcubeType", propOrder = {
    "numeroLineaCreditoRespOut",
    "numeroContrato",
    "descripcionLinea",
    "moneda",
    "montoAprobado",
    "codigoCliente",
    "revolvencia",
    "fondo",
    "lineaFinanciera",
    "destino",
    "tipoRecurso",
    "pais",
    "actividadEconomica",
    "sectorInstitucional",
    "ejecutivo",
    "ejeEstrategico",
    "areaFocalizacion",
    "objeticoEstrategico",
    "plantillaLimite",
    "serialLimite",
    "saldo",
    "disponibilidad",
    "disponibilidadIfacil",
    "tieneCondEspeciales",
    "descCondEspeciales",
    "fechaMaxDesembolsar",
    "numeroDesembolsos"
})
public class LineaCreditoFlexcubeType {

    @XmlElement(name = "numeroLineaCreditoResp_out", required = true)
    protected String numeroLineaCreditoRespOut;
    @XmlElement(name = "NumeroContrato", required = true)
    protected String numeroContrato;
    @XmlElement(name = "DescripcionLinea", required = true)
    protected String descripcionLinea;
    @XmlElement(name = "Moneda", required = true)
    protected String moneda;
    @XmlElement(name = "MontoAprobado", required = true)
    protected BigDecimal montoAprobado;
    @XmlElement(name = "CodigoCliente", required = true)
    protected String codigoCliente;
    @XmlElement(name = "Revolvencia", required = true)
    protected String revolvencia;
    @XmlElement(name = "Fondo", required = true)
    protected String fondo;
    @XmlElement(name = "LineaFinanciera", required = true)
    protected String lineaFinanciera;
    @XmlElement(name = "Destino", required = true)
    protected String destino;
    @XmlElement(name = "TipoRecurso", required = true)
    protected String tipoRecurso;
    @XmlElement(name = "Pais", required = true)
    protected String pais;
    @XmlElement(name = "ActividadEconomica", required = true)
    protected String actividadEconomica;
    @XmlElement(name = "SectorInstitucional", required = true)
    protected String sectorInstitucional;
    @XmlElement(name = "Ejecutivo", required = true)
    protected String ejecutivo;
    @XmlElement(name = "EjeEstrategico", required = true)
    protected String ejeEstrategico;
    @XmlElement(name = "AreaFocalizacion", required = true)
    protected String areaFocalizacion;
    @XmlElement(name = "ObjeticoEstrategico", required = true)
    protected String objeticoEstrategico;
    @XmlElement(name = "PlantillaLimite", required = true)
    protected String plantillaLimite;
    @XmlElement(name = "SerialLimite", required = true)
    protected String serialLimite;
    @XmlElement(name = "Saldo", required = true)
    protected BigDecimal saldo;
    @XmlElement(name = "Disponibilidad", required = true)
    protected BigDecimal disponibilidad;
    @XmlElement(name = "DisponibilidadIfacil", required = true)
    protected BigDecimal disponibilidadIfacil;
    @XmlElement(name = "TieneCondEspeciales", required = true)
    protected String tieneCondEspeciales;
    @XmlElement(name = "DescCondEspeciales", required = true)
    protected String descCondEspeciales;
    @XmlElement(name = "FechaMaxDesembolsar", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaMaxDesembolsar;
    @XmlElement(name = "NumeroDesembolsos", required = true)
    protected BigDecimal numeroDesembolsos;

    /**
     * Obtiene el valor de la propiedad numeroLineaCreditoRespOut.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroLineaCreditoRespOut() {
        return numeroLineaCreditoRespOut;
    }

    /**
     * Define el valor de la propiedad numeroLineaCreditoRespOut.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroLineaCreditoRespOut(String value) {
        this.numeroLineaCreditoRespOut = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroContrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroContrato() {
        return numeroContrato;
    }

    /**
     * Define el valor de la propiedad numeroContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroContrato(String value) {
        this.numeroContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionLinea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionLinea() {
        return descripcionLinea;
    }

    /**
     * Define el valor de la propiedad descripcionLinea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionLinea(String value) {
        this.descripcionLinea = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad montoAprobado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoAprobado() {
        return montoAprobado;
    }

    /**
     * Define el valor de la propiedad montoAprobado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoAprobado(BigDecimal value) {
        this.montoAprobado = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Define el valor de la propiedad codigoCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCliente(String value) {
        this.codigoCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad revolvencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevolvencia() {
        return revolvencia;
    }

    /**
     * Define el valor de la propiedad revolvencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevolvencia(String value) {
        this.revolvencia = value;
    }

    /**
     * Obtiene el valor de la propiedad fondo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFondo() {
        return fondo;
    }

    /**
     * Define el valor de la propiedad fondo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFondo(String value) {
        this.fondo = value;
    }

    /**
     * Obtiene el valor de la propiedad lineaFinanciera.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineaFinanciera() {
        return lineaFinanciera;
    }

    /**
     * Define el valor de la propiedad lineaFinanciera.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineaFinanciera(String value) {
        this.lineaFinanciera = value;
    }

    /**
     * Obtiene el valor de la propiedad destino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Define el valor de la propiedad destino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestino(String value) {
        this.destino = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoRecurso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRecurso() {
        return tipoRecurso;
    }

    /**
     * Define el valor de la propiedad tipoRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRecurso(String value) {
        this.tipoRecurso = value;
    }

    /**
     * Obtiene el valor de la propiedad pais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Define el valor de la propiedad pais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Obtiene el valor de la propiedad actividadEconomica.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividadEconomica() {
        return actividadEconomica;
    }

    /**
     * Define el valor de la propiedad actividadEconomica.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividadEconomica(String value) {
        this.actividadEconomica = value;
    }

    /**
     * Obtiene el valor de la propiedad sectorInstitucional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSectorInstitucional() {
        return sectorInstitucional;
    }

    /**
     * Define el valor de la propiedad sectorInstitucional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSectorInstitucional(String value) {
        this.sectorInstitucional = value;
    }

    /**
     * Obtiene el valor de la propiedad ejecutivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEjecutivo() {
        return ejecutivo;
    }

    /**
     * Define el valor de la propiedad ejecutivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEjecutivo(String value) {
        this.ejecutivo = value;
    }

    /**
     * Obtiene el valor de la propiedad ejeEstrategico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEjeEstrategico() {
        return ejeEstrategico;
    }

    /**
     * Define el valor de la propiedad ejeEstrategico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEjeEstrategico(String value) {
        this.ejeEstrategico = value;
    }

    /**
     * Obtiene el valor de la propiedad areaFocalizacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaFocalizacion() {
        return areaFocalizacion;
    }

    /**
     * Define el valor de la propiedad areaFocalizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaFocalizacion(String value) {
        this.areaFocalizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad objeticoEstrategico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjeticoEstrategico() {
        return objeticoEstrategico;
    }

    /**
     * Define el valor de la propiedad objeticoEstrategico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjeticoEstrategico(String value) {
        this.objeticoEstrategico = value;
    }

    /**
     * Obtiene el valor de la propiedad plantillaLimite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlantillaLimite() {
        return plantillaLimite;
    }

    /**
     * Define el valor de la propiedad plantillaLimite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlantillaLimite(String value) {
        this.plantillaLimite = value;
    }

    /**
     * Obtiene el valor de la propiedad serialLimite.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialLimite() {
        return serialLimite;
    }

    /**
     * Define el valor de la propiedad serialLimite.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialLimite(String value) {
        this.serialLimite = value;
    }

    /**
     * Obtiene el valor de la propiedad saldo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * Define el valor de la propiedad saldo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSaldo(BigDecimal value) {
        this.saldo = value;
    }

    /**
     * Obtiene el valor de la propiedad disponibilidad.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * Define el valor de la propiedad disponibilidad.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDisponibilidad(BigDecimal value) {
        this.disponibilidad = value;
    }

    /**
     * Obtiene el valor de la propiedad disponibilidadIfacil.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDisponibilidadIfacil() {
        return disponibilidadIfacil;
    }

    /**
     * Define el valor de la propiedad disponibilidadIfacil.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDisponibilidadIfacil(BigDecimal value) {
        this.disponibilidadIfacil = value;
    }

    /**
     * Obtiene el valor de la propiedad tieneCondEspeciales.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTieneCondEspeciales() {
        return tieneCondEspeciales;
    }

    /**
     * Define el valor de la propiedad tieneCondEspeciales.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTieneCondEspeciales(String value) {
        this.tieneCondEspeciales = value;
    }

    /**
     * Obtiene el valor de la propiedad descCondEspeciales.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescCondEspeciales() {
        return descCondEspeciales;
    }

    /**
     * Define el valor de la propiedad descCondEspeciales.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescCondEspeciales(String value) {
        this.descCondEspeciales = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaMaxDesembolsar.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaMaxDesembolsar() {
        return fechaMaxDesembolsar;
    }

    /**
     * Define el valor de la propiedad fechaMaxDesembolsar.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaMaxDesembolsar(XMLGregorianCalendar value) {
        this.fechaMaxDesembolsar = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroDesembolsos.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumeroDesembolsos() {
        return numeroDesembolsos;
    }

    /**
     * Define el valor de la propiedad numeroDesembolsos.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumeroDesembolsos(BigDecimal value) {
        this.numeroDesembolsos = value;
    }

}
