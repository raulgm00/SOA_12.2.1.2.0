
package org.bcie.productobo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Reglas del Producto
 *           Define las reglas específicas de cada producto en el flujo del proceso
 *       
 * 
 * <p>Clase Java para ReglasOperacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReglasOperacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="banStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereElegibilidad" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereLaft" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="OFICrealizaAnalisisLAFT" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereEvaluacionExAnte" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereFormularProgramas" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="elaboraAnalizarAdquisiciones" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="elaboraHojaTerminosPCT" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="elaboraHojaTerminosSEPRI" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="analizarModelo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="realizarPreanalisis" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="participaSupervision" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="participaSeguimiento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="participaFINAM" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SRC" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="OpinionJuridica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="firmaContrato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="condicionesPreviasFormalizar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="condicionesPreviasDesembolso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="programacionDesembolsos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="reglaLAFT" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="adquisiciones2" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="supervicionTecnica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="seguimientoCrediticio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="enmiendas" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="recuperacion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cierreOperativo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="rendicionCuentas" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="evaluacionMedioTermino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="evaluacionExPost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requiereAdquisiciones" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereRAROC" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereIBICIE" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereSIEMAS" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereGERIESElegibilidad" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereOpinionTecnica" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereAsjurAnalisis" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereAsjurElegibilidad" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requierePreparacion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tieneRiesgoCredito" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="responsableAnalisis" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreResponsableAnalisis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requiereFirmacontrato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereRegistroLinea" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereCore" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereDocumentacionContractual" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereCOF" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="esDesobligacion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="esCambioPlazo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="requiereValidarMatriz" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReglasOperacion", propOrder = {
    "banStatus",
    "requiereElegibilidad",
    "requiereLaft",
    "ofiCrealizaAnalisisLAFT",
    "requiereEvaluacionExAnte",
    "requiereFormularProgramas",
    "elaboraAnalizarAdquisiciones",
    "elaboraHojaTerminosPCT",
    "elaboraHojaTerminosSEPRI",
    "analizarModelo",
    "realizarPreanalisis",
    "participaSupervision",
    "participaSeguimiento",
    "participaFINAM",
    "src",
    "opinionJuridica",
    "firmaContrato",
    "condicionesPreviasFormalizar",
    "condicionesPreviasDesembolso",
    "programacionDesembolsos",
    "reglaLAFT",
    "adquisiciones2",
    "supervicionTecnica",
    "seguimientoCrediticio",
    "enmiendas",
    "recuperacion",
    "cierreOperativo",
    "rendicionCuentas",
    "evaluacionMedioTermino",
    "evaluacionExPost",
    "requiereAdquisiciones",
    "requiereRAROC",
    "requiereIBICIE",
    "requiereSIEMAS",
    "requiereGERIESElegibilidad",
    "requiereOpinionTecnica",
    "requiereAsjurAnalisis",
    "requiereAsjurElegibilidad",
    "requierePreparacion",
    "tieneRiesgoCredito",
    "responsableAnalisis",
    "nombreResponsableAnalisis",
    "requiereFirmacontrato",
    "requiereRegistroLinea",
    "requiereCore",
    "requiereDocumentacionContractual",
    "requiereCOF",
    "esDesobligacion",
    "esCambioPlazo",
    "requiereValidarMatriz"
})
public class ReglasOperacion {

    protected boolean banStatus;
    protected boolean requiereElegibilidad;
    protected boolean requiereLaft;
    @XmlElement(name = "OFICrealizaAnalisisLAFT")
    protected boolean ofiCrealizaAnalisisLAFT;
    protected boolean requiereEvaluacionExAnte;
    protected boolean requiereFormularProgramas;
    protected boolean elaboraAnalizarAdquisiciones;
    protected boolean elaboraHojaTerminosPCT;
    protected boolean elaboraHojaTerminosSEPRI;
    protected boolean analizarModelo;
    protected boolean realizarPreanalisis;
    protected boolean participaSupervision;
    protected boolean participaSeguimiento;
    protected boolean participaFINAM;
    @XmlElement(name = "SRC")
    protected boolean src;
    @XmlElement(name = "OpinionJuridica")
    protected boolean opinionJuridica;
    protected boolean firmaContrato;
    protected boolean condicionesPreviasFormalizar;
    protected boolean condicionesPreviasDesembolso;
    protected boolean programacionDesembolsos;
    protected boolean reglaLAFT;
    protected boolean adquisiciones2;
    protected boolean supervicionTecnica;
    protected boolean seguimientoCrediticio;
    protected boolean enmiendas;
    protected boolean recuperacion;
    protected boolean cierreOperativo;
    protected boolean rendicionCuentas;
    @XmlElement(required = true)
    protected String evaluacionMedioTermino;
    @XmlElement(required = true)
    protected String evaluacionExPost;
    protected boolean requiereAdquisiciones;
    protected boolean requiereRAROC;
    protected boolean requiereIBICIE;
    protected boolean requiereSIEMAS;
    protected boolean requiereGERIESElegibilidad;
    protected boolean requiereOpinionTecnica;
    protected boolean requiereAsjurAnalisis;
    protected boolean requiereAsjurElegibilidad;
    protected boolean requierePreparacion;
    protected boolean tieneRiesgoCredito;
    protected int responsableAnalisis;
    @XmlElement(required = true)
    protected String nombreResponsableAnalisis;
    protected boolean requiereFirmacontrato;
    protected boolean requiereRegistroLinea;
    protected boolean requiereCore;
    protected boolean requiereDocumentacionContractual;
    protected boolean requiereCOF;
    protected boolean esDesobligacion;
    protected boolean esCambioPlazo;
    protected boolean requiereValidarMatriz;

    /**
     * Obtiene el valor de la propiedad banStatus.
     * 
     */
    public boolean isBanStatus() {
        return banStatus;
    }

    /**
     * Define el valor de la propiedad banStatus.
     * 
     */
    public void setBanStatus(boolean value) {
        this.banStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereElegibilidad.
     * 
     */
    public boolean isRequiereElegibilidad() {
        return requiereElegibilidad;
    }

    /**
     * Define el valor de la propiedad requiereElegibilidad.
     * 
     */
    public void setRequiereElegibilidad(boolean value) {
        this.requiereElegibilidad = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereLaft.
     * 
     */
    public boolean isRequiereLaft() {
        return requiereLaft;
    }

    /**
     * Define el valor de la propiedad requiereLaft.
     * 
     */
    public void setRequiereLaft(boolean value) {
        this.requiereLaft = value;
    }

    /**
     * Obtiene el valor de la propiedad ofiCrealizaAnalisisLAFT.
     * 
     */
    public boolean isOFICrealizaAnalisisLAFT() {
        return ofiCrealizaAnalisisLAFT;
    }

    /**
     * Define el valor de la propiedad ofiCrealizaAnalisisLAFT.
     * 
     */
    public void setOFICrealizaAnalisisLAFT(boolean value) {
        this.ofiCrealizaAnalisisLAFT = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereEvaluacionExAnte.
     * 
     */
    public boolean isRequiereEvaluacionExAnte() {
        return requiereEvaluacionExAnte;
    }

    /**
     * Define el valor de la propiedad requiereEvaluacionExAnte.
     * 
     */
    public void setRequiereEvaluacionExAnte(boolean value) {
        this.requiereEvaluacionExAnte = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereFormularProgramas.
     * 
     */
    public boolean isRequiereFormularProgramas() {
        return requiereFormularProgramas;
    }

    /**
     * Define el valor de la propiedad requiereFormularProgramas.
     * 
     */
    public void setRequiereFormularProgramas(boolean value) {
        this.requiereFormularProgramas = value;
    }

    /**
     * Obtiene el valor de la propiedad elaboraAnalizarAdquisiciones.
     * 
     */
    public boolean isElaboraAnalizarAdquisiciones() {
        return elaboraAnalizarAdquisiciones;
    }

    /**
     * Define el valor de la propiedad elaboraAnalizarAdquisiciones.
     * 
     */
    public void setElaboraAnalizarAdquisiciones(boolean value) {
        this.elaboraAnalizarAdquisiciones = value;
    }

    /**
     * Obtiene el valor de la propiedad elaboraHojaTerminosPCT.
     * 
     */
    public boolean isElaboraHojaTerminosPCT() {
        return elaboraHojaTerminosPCT;
    }

    /**
     * Define el valor de la propiedad elaboraHojaTerminosPCT.
     * 
     */
    public void setElaboraHojaTerminosPCT(boolean value) {
        this.elaboraHojaTerminosPCT = value;
    }

    /**
     * Obtiene el valor de la propiedad elaboraHojaTerminosSEPRI.
     * 
     */
    public boolean isElaboraHojaTerminosSEPRI() {
        return elaboraHojaTerminosSEPRI;
    }

    /**
     * Define el valor de la propiedad elaboraHojaTerminosSEPRI.
     * 
     */
    public void setElaboraHojaTerminosSEPRI(boolean value) {
        this.elaboraHojaTerminosSEPRI = value;
    }

    /**
     * Obtiene el valor de la propiedad analizarModelo.
     * 
     */
    public boolean isAnalizarModelo() {
        return analizarModelo;
    }

    /**
     * Define el valor de la propiedad analizarModelo.
     * 
     */
    public void setAnalizarModelo(boolean value) {
        this.analizarModelo = value;
    }

    /**
     * Obtiene el valor de la propiedad realizarPreanalisis.
     * 
     */
    public boolean isRealizarPreanalisis() {
        return realizarPreanalisis;
    }

    /**
     * Define el valor de la propiedad realizarPreanalisis.
     * 
     */
    public void setRealizarPreanalisis(boolean value) {
        this.realizarPreanalisis = value;
    }

    /**
     * Obtiene el valor de la propiedad participaSupervision.
     * 
     */
    public boolean isParticipaSupervision() {
        return participaSupervision;
    }

    /**
     * Define el valor de la propiedad participaSupervision.
     * 
     */
    public void setParticipaSupervision(boolean value) {
        this.participaSupervision = value;
    }

    /**
     * Obtiene el valor de la propiedad participaSeguimiento.
     * 
     */
    public boolean isParticipaSeguimiento() {
        return participaSeguimiento;
    }

    /**
     * Define el valor de la propiedad participaSeguimiento.
     * 
     */
    public void setParticipaSeguimiento(boolean value) {
        this.participaSeguimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad participaFINAM.
     * 
     */
    public boolean isParticipaFINAM() {
        return participaFINAM;
    }

    /**
     * Define el valor de la propiedad participaFINAM.
     * 
     */
    public void setParticipaFINAM(boolean value) {
        this.participaFINAM = value;
    }

    /**
     * Obtiene el valor de la propiedad src.
     * 
     */
    public boolean isSRC() {
        return src;
    }

    /**
     * Define el valor de la propiedad src.
     * 
     */
    public void setSRC(boolean value) {
        this.src = value;
    }

    /**
     * Obtiene el valor de la propiedad opinionJuridica.
     * 
     */
    public boolean isOpinionJuridica() {
        return opinionJuridica;
    }

    /**
     * Define el valor de la propiedad opinionJuridica.
     * 
     */
    public void setOpinionJuridica(boolean value) {
        this.opinionJuridica = value;
    }

    /**
     * Obtiene el valor de la propiedad firmaContrato.
     * 
     */
    public boolean isFirmaContrato() {
        return firmaContrato;
    }

    /**
     * Define el valor de la propiedad firmaContrato.
     * 
     */
    public void setFirmaContrato(boolean value) {
        this.firmaContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad condicionesPreviasFormalizar.
     * 
     */
    public boolean isCondicionesPreviasFormalizar() {
        return condicionesPreviasFormalizar;
    }

    /**
     * Define el valor de la propiedad condicionesPreviasFormalizar.
     * 
     */
    public void setCondicionesPreviasFormalizar(boolean value) {
        this.condicionesPreviasFormalizar = value;
    }

    /**
     * Obtiene el valor de la propiedad condicionesPreviasDesembolso.
     * 
     */
    public boolean isCondicionesPreviasDesembolso() {
        return condicionesPreviasDesembolso;
    }

    /**
     * Define el valor de la propiedad condicionesPreviasDesembolso.
     * 
     */
    public void setCondicionesPreviasDesembolso(boolean value) {
        this.condicionesPreviasDesembolso = value;
    }

    /**
     * Obtiene el valor de la propiedad programacionDesembolsos.
     * 
     */
    public boolean isProgramacionDesembolsos() {
        return programacionDesembolsos;
    }

    /**
     * Define el valor de la propiedad programacionDesembolsos.
     * 
     */
    public void setProgramacionDesembolsos(boolean value) {
        this.programacionDesembolsos = value;
    }

    /**
     * Obtiene el valor de la propiedad reglaLAFT.
     * 
     */
    public boolean isReglaLAFT() {
        return reglaLAFT;
    }

    /**
     * Define el valor de la propiedad reglaLAFT.
     * 
     */
    public void setReglaLAFT(boolean value) {
        this.reglaLAFT = value;
    }

    /**
     * Obtiene el valor de la propiedad adquisiciones2.
     * 
     */
    public boolean isAdquisiciones2() {
        return adquisiciones2;
    }

    /**
     * Define el valor de la propiedad adquisiciones2.
     * 
     */
    public void setAdquisiciones2(boolean value) {
        this.adquisiciones2 = value;
    }

    /**
     * Obtiene el valor de la propiedad supervicionTecnica.
     * 
     */
    public boolean isSupervicionTecnica() {
        return supervicionTecnica;
    }

    /**
     * Define el valor de la propiedad supervicionTecnica.
     * 
     */
    public void setSupervicionTecnica(boolean value) {
        this.supervicionTecnica = value;
    }

    /**
     * Obtiene el valor de la propiedad seguimientoCrediticio.
     * 
     */
    public boolean isSeguimientoCrediticio() {
        return seguimientoCrediticio;
    }

    /**
     * Define el valor de la propiedad seguimientoCrediticio.
     * 
     */
    public void setSeguimientoCrediticio(boolean value) {
        this.seguimientoCrediticio = value;
    }

    /**
     * Obtiene el valor de la propiedad enmiendas.
     * 
     */
    public boolean isEnmiendas() {
        return enmiendas;
    }

    /**
     * Define el valor de la propiedad enmiendas.
     * 
     */
    public void setEnmiendas(boolean value) {
        this.enmiendas = value;
    }

    /**
     * Obtiene el valor de la propiedad recuperacion.
     * 
     */
    public boolean isRecuperacion() {
        return recuperacion;
    }

    /**
     * Define el valor de la propiedad recuperacion.
     * 
     */
    public void setRecuperacion(boolean value) {
        this.recuperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad cierreOperativo.
     * 
     */
    public boolean isCierreOperativo() {
        return cierreOperativo;
    }

    /**
     * Define el valor de la propiedad cierreOperativo.
     * 
     */
    public void setCierreOperativo(boolean value) {
        this.cierreOperativo = value;
    }

    /**
     * Obtiene el valor de la propiedad rendicionCuentas.
     * 
     */
    public boolean isRendicionCuentas() {
        return rendicionCuentas;
    }

    /**
     * Define el valor de la propiedad rendicionCuentas.
     * 
     */
    public void setRendicionCuentas(boolean value) {
        this.rendicionCuentas = value;
    }

    /**
     * Obtiene el valor de la propiedad evaluacionMedioTermino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvaluacionMedioTermino() {
        return evaluacionMedioTermino;
    }

    /**
     * Define el valor de la propiedad evaluacionMedioTermino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvaluacionMedioTermino(String value) {
        this.evaluacionMedioTermino = value;
    }

    /**
     * Obtiene el valor de la propiedad evaluacionExPost.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvaluacionExPost() {
        return evaluacionExPost;
    }

    /**
     * Define el valor de la propiedad evaluacionExPost.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvaluacionExPost(String value) {
        this.evaluacionExPost = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereAdquisiciones.
     * 
     */
    public boolean isRequiereAdquisiciones() {
        return requiereAdquisiciones;
    }

    /**
     * Define el valor de la propiedad requiereAdquisiciones.
     * 
     */
    public void setRequiereAdquisiciones(boolean value) {
        this.requiereAdquisiciones = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereRAROC.
     * 
     */
    public boolean isRequiereRAROC() {
        return requiereRAROC;
    }

    /**
     * Define el valor de la propiedad requiereRAROC.
     * 
     */
    public void setRequiereRAROC(boolean value) {
        this.requiereRAROC = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereIBICIE.
     * 
     */
    public boolean isRequiereIBICIE() {
        return requiereIBICIE;
    }

    /**
     * Define el valor de la propiedad requiereIBICIE.
     * 
     */
    public void setRequiereIBICIE(boolean value) {
        this.requiereIBICIE = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereSIEMAS.
     * 
     */
    public boolean isRequiereSIEMAS() {
        return requiereSIEMAS;
    }

    /**
     * Define el valor de la propiedad requiereSIEMAS.
     * 
     */
    public void setRequiereSIEMAS(boolean value) {
        this.requiereSIEMAS = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereGERIESElegibilidad.
     * 
     */
    public boolean isRequiereGERIESElegibilidad() {
        return requiereGERIESElegibilidad;
    }

    /**
     * Define el valor de la propiedad requiereGERIESElegibilidad.
     * 
     */
    public void setRequiereGERIESElegibilidad(boolean value) {
        this.requiereGERIESElegibilidad = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereOpinionTecnica.
     * 
     */
    public boolean isRequiereOpinionTecnica() {
        return requiereOpinionTecnica;
    }

    /**
     * Define el valor de la propiedad requiereOpinionTecnica.
     * 
     */
    public void setRequiereOpinionTecnica(boolean value) {
        this.requiereOpinionTecnica = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereAsjurAnalisis.
     * 
     */
    public boolean isRequiereAsjurAnalisis() {
        return requiereAsjurAnalisis;
    }

    /**
     * Define el valor de la propiedad requiereAsjurAnalisis.
     * 
     */
    public void setRequiereAsjurAnalisis(boolean value) {
        this.requiereAsjurAnalisis = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereAsjurElegibilidad.
     * 
     */
    public boolean isRequiereAsjurElegibilidad() {
        return requiereAsjurElegibilidad;
    }

    /**
     * Define el valor de la propiedad requiereAsjurElegibilidad.
     * 
     */
    public void setRequiereAsjurElegibilidad(boolean value) {
        this.requiereAsjurElegibilidad = value;
    }

    /**
     * Obtiene el valor de la propiedad requierePreparacion.
     * 
     */
    public boolean isRequierePreparacion() {
        return requierePreparacion;
    }

    /**
     * Define el valor de la propiedad requierePreparacion.
     * 
     */
    public void setRequierePreparacion(boolean value) {
        this.requierePreparacion = value;
    }

    /**
     * Obtiene el valor de la propiedad tieneRiesgoCredito.
     * 
     */
    public boolean isTieneRiesgoCredito() {
        return tieneRiesgoCredito;
    }

    /**
     * Define el valor de la propiedad tieneRiesgoCredito.
     * 
     */
    public void setTieneRiesgoCredito(boolean value) {
        this.tieneRiesgoCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad responsableAnalisis.
     * 
     */
    public int getResponsableAnalisis() {
        return responsableAnalisis;
    }

    /**
     * Define el valor de la propiedad responsableAnalisis.
     * 
     */
    public void setResponsableAnalisis(int value) {
        this.responsableAnalisis = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreResponsableAnalisis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreResponsableAnalisis() {
        return nombreResponsableAnalisis;
    }

    /**
     * Define el valor de la propiedad nombreResponsableAnalisis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreResponsableAnalisis(String value) {
        this.nombreResponsableAnalisis = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereFirmacontrato.
     * 
     */
    public boolean isRequiereFirmacontrato() {
        return requiereFirmacontrato;
    }

    /**
     * Define el valor de la propiedad requiereFirmacontrato.
     * 
     */
    public void setRequiereFirmacontrato(boolean value) {
        this.requiereFirmacontrato = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereRegistroLinea.
     * 
     */
    public boolean isRequiereRegistroLinea() {
        return requiereRegistroLinea;
    }

    /**
     * Define el valor de la propiedad requiereRegistroLinea.
     * 
     */
    public void setRequiereRegistroLinea(boolean value) {
        this.requiereRegistroLinea = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereCore.
     * 
     */
    public boolean isRequiereCore() {
        return requiereCore;
    }

    /**
     * Define el valor de la propiedad requiereCore.
     * 
     */
    public void setRequiereCore(boolean value) {
        this.requiereCore = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereDocumentacionContractual.
     * 
     */
    public boolean isRequiereDocumentacionContractual() {
        return requiereDocumentacionContractual;
    }

    /**
     * Define el valor de la propiedad requiereDocumentacionContractual.
     * 
     */
    public void setRequiereDocumentacionContractual(boolean value) {
        this.requiereDocumentacionContractual = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereCOF.
     * 
     */
    public boolean isRequiereCOF() {
        return requiereCOF;
    }

    /**
     * Define el valor de la propiedad requiereCOF.
     * 
     */
    public void setRequiereCOF(boolean value) {
        this.requiereCOF = value;
    }

    /**
     * Obtiene el valor de la propiedad esDesobligacion.
     * 
     */
    public boolean isEsDesobligacion() {
        return esDesobligacion;
    }

    /**
     * Define el valor de la propiedad esDesobligacion.
     * 
     */
    public void setEsDesobligacion(boolean value) {
        this.esDesobligacion = value;
    }

    /**
     * Obtiene el valor de la propiedad esCambioPlazo.
     * 
     */
    public boolean isEsCambioPlazo() {
        return esCambioPlazo;
    }

    /**
     * Define el valor de la propiedad esCambioPlazo.
     * 
     */
    public void setEsCambioPlazo(boolean value) {
        this.esCambioPlazo = value;
    }

    /**
     * Obtiene el valor de la propiedad requiereValidarMatriz.
     * 
     */
    public boolean isRequiereValidarMatriz() {
        return requiereValidarMatriz;
    }

    /**
     * Define el valor de la propiedad requiereValidarMatriz.
     * 
     */
    public void setRequiereValidarMatriz(boolean value) {
        this.requiereValidarMatriz = value;
    }

}
