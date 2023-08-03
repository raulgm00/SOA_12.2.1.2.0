/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package www.bcie.org.javabeans;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author latbc
 */
public class Operacion implements  Serializable{
private long idOperacion;
private String usuario;
private String oficina;
private String nombreOperacion;
private String cliente;
private String producto;
private String descripcion;
private String montoTotal;
private String montoSolicitado;
private String lineaCredito;
private String actividadEconomica;
private String objetivosEstrategicos;
private String areaFocalizacion;
private String ejeEstrategico;
private String iniciativaEstrategica;
private String sectorMercado;
private String fechaInicio;
private String programadoPoa;
private String ejercicioPoa;
private String tipoGarantia;
private String componenteConcecionalidad;
private String estructurador;
private String beneficiario;
private String unidadEjecutora;
private String programa;
private String asInfoRiesgo;
private String infoAdicionalRiesgo;
private String masInfoJuridico;
private String infoAdicionalJuridico;
private String cumpleLaFt;
private String comentarioLaFt;
private String contrapartesProhibidas;
private String comentarioContrapartes;
private String limitesAnalisis;
private String opinionElegibilidad;
private String comentarioElegibilidad;
private String limitesDeterminar;
private String opinionDeterminar;
private String comentarioDeterminar;
private String etapa;
private String status;
private String fechaBaja;
private    String preguntaLaft;
private    String respuestaLaft;
private    String preguntaContraPartes;
private    String respuestaContraPartes;


    public Operacion() {
    }

    public Operacion(long idOperacion, String usuario, String oficina, String nombreOperacion, String cliente, String producto, String descripcion, String montoTotal, String montoSolicitado, String lineaCredito, String actividadEconomica, String objetivosEstrategicos, String areaFocalizacion, String ejeEstrategico, String iniciativaEstrategica, String sectorMercado, String fechaInicio, String programadoPoa, String ejercicioPoa, String tipoGarantia, String componenteConcecionalidad, String estructurador, String beneficiario, String unidadEjecutora, String programa, String asInfoRiesgo, String infoAdicionalRiesgo, String masInfoJuridico, String infoAdicionalJuridico, String cumpleLaFt, String comentarioLaFt, String contrapartesProhibidas, String comentarioContrapartes, String limitesAnalisis, String opinionElegibilidad, String comentarioElegibilidad, String limitesDeterminar, String opinionDeterminar, String comentarioDeterminar, String etapa, String status, String fechaBaja) {
        this.idOperacion = idOperacion;
        this.usuario = usuario;
        this.oficina = oficina;
        this.nombreOperacion = nombreOperacion;
        this.cliente = cliente;
        this.producto = producto;
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
        this.montoSolicitado = montoSolicitado;
        this.lineaCredito = lineaCredito;
        this.actividadEconomica = actividadEconomica;
        this.objetivosEstrategicos = objetivosEstrategicos;
        this.areaFocalizacion = areaFocalizacion;
        this.ejeEstrategico = ejeEstrategico;
        this.iniciativaEstrategica = iniciativaEstrategica;
        this.sectorMercado = sectorMercado;
        this.fechaInicio = fechaInicio;
        this.programadoPoa = programadoPoa;
        this.ejercicioPoa = ejercicioPoa;
        this.tipoGarantia = tipoGarantia;
        this.componenteConcecionalidad = componenteConcecionalidad;
        this.estructurador = estructurador;
        this.beneficiario = beneficiario;
        this.unidadEjecutora = unidadEjecutora;
        this.programa = programa;
        this.asInfoRiesgo = asInfoRiesgo;
        this.infoAdicionalRiesgo = infoAdicionalRiesgo;
        this.masInfoJuridico = masInfoJuridico;
        this.infoAdicionalJuridico = infoAdicionalJuridico;
        this.cumpleLaFt = cumpleLaFt;
        this.comentarioLaFt = comentarioLaFt;
        this.contrapartesProhibidas = contrapartesProhibidas;
        this.comentarioContrapartes = comentarioContrapartes;
        this.limitesAnalisis = limitesAnalisis;
        this.opinionElegibilidad = opinionElegibilidad;
        this.comentarioElegibilidad = comentarioElegibilidad;
        this.limitesDeterminar = limitesDeterminar;
        this.opinionDeterminar = opinionDeterminar;
        this.comentarioDeterminar = comentarioDeterminar;
        this.etapa = etapa;
        this.status = status;
        this.fechaBaja = fechaBaja;
    }

    public long getIdOperacion() {        
        return idOperacion;
    }

    public void setIdOperacion(long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(String montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public String getLineaCredito() {
        return lineaCredito;
    }

    public void setLineaCredito(String lineaCredito) {
        this.lineaCredito = lineaCredito;
    }

    public String getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(String actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    public String getObjetivosEstrategicos() {
        return objetivosEstrategicos;
    }

    public void setObjetivosEstrategicos(String objetivosEstrategicos) {
        this.objetivosEstrategicos = objetivosEstrategicos;
    }

    public String getAreaFocalizacion() {
        return areaFocalizacion;
    }

    public void setAreaFocalizacion(String areaFocalizacion) {
        this.areaFocalizacion = areaFocalizacion;
    }

    public String getEjeEstrategico() {
        return ejeEstrategico;
    }

    public void setEjeEstrategico(String ejeEstrategico) {
        this.ejeEstrategico = ejeEstrategico;
    }

    public String getIniciativaEstrategica() {
        return iniciativaEstrategica;
    }

    public void setIniciativaEstrategica(String iniciativaEstrategica) {
        this.iniciativaEstrategica = iniciativaEstrategica;
    }

    public String getSectorMercado() {
        return sectorMercado;
    }

    public void setSectorMercado(String sectorMercado) {
        this.sectorMercado = sectorMercado;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getProgramadoPoa() {
        return programadoPoa;
    }

    public void setProgramadoPoa(String programadoPoa) {
        this.programadoPoa = programadoPoa;
    }

    public String getEjercicioPoa() {
        return ejercicioPoa;
    }

    public void setEjercicioPoa(String ejercicioPoa) {
        this.ejercicioPoa = ejercicioPoa;
    }

    public String getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(String tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }

    public String getComponenteConcecionalidad() {
        return componenteConcecionalidad;
    }

    public void setComponenteConcecionalidad(String componenteConcecionalidad) {
        this.componenteConcecionalidad = componenteConcecionalidad;
    }

    public String getEstructurador() {
        return estructurador;
    }

    public void setEstructurador(String estructurador) {
        this.estructurador = estructurador;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getUnidadEjecutora() {
        return unidadEjecutora;
    }

    public void setUnidadEjecutora(String unidadEjecutora) {
        this.unidadEjecutora = unidadEjecutora;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getAsInfoRiesgo() {
        return asInfoRiesgo;
    }

    public void setAsInfoRiesgo(String asInfoRiesgo) {
        this.asInfoRiesgo = asInfoRiesgo;
    }

    public String getInfoAdicionalRiesgo() {
        return infoAdicionalRiesgo;
    }

    public void setInfoAdicionalRiesgo(String infoAdicionalRiesgo) {
        this.infoAdicionalRiesgo = infoAdicionalRiesgo;
    }

    public String getMasInfoJuridico() {
        return masInfoJuridico;
    }

    public void setMasInfoJuridico(String masInfoJuridico) {
        this.masInfoJuridico = masInfoJuridico;
    }

    public String getInfoAdicionalJuridico() {
        return infoAdicionalJuridico;
    }

    public void setInfoAdicionalJuridico(String infoAdicionalJuridico) {
        this.infoAdicionalJuridico = infoAdicionalJuridico;
    }

    public String getCumpleLaFt() {
        return cumpleLaFt;
    }

    public void setCumpleLaFt(String cumpleLaFt) {
        this.cumpleLaFt = cumpleLaFt;
    }

    public String getComentarioLaFt() {
        return comentarioLaFt;
    }

    public void setComentarioLaFt(String comentarioLaFt) {
        this.comentarioLaFt = comentarioLaFt;
    }

    public String getContrapartesProhibidas() {
        return contrapartesProhibidas;
    }

    public void setContrapartesProhibidas(String contrapartesProhibidas) {
        this.contrapartesProhibidas = contrapartesProhibidas;
    }

    public String getComentarioContrapartes() {
        return comentarioContrapartes;
    }

    public void setComentarioContrapartes(String comentarioContrapartes) {
        this.comentarioContrapartes = comentarioContrapartes;
    }

    public String getLimitesAnalisis() {
        return limitesAnalisis;
    }

    public void setLimitesAnalisis(String limitesAnalisis) {
        this.limitesAnalisis = limitesAnalisis;
    }

    public String getOpinionElegibilidad() {
        return opinionElegibilidad;
    }

    public void setOpinionElegibilidad(String opinionElegibilidad) {
        this.opinionElegibilidad = opinionElegibilidad;
    }

    public String getComentarioElegibilidad() {
        return comentarioElegibilidad;
    }

    public void setComentarioElegibilidad(String comentarioElegibilidad) {
        this.comentarioElegibilidad = comentarioElegibilidad;
    }

    public String getLimitesDeterminar() {
        return limitesDeterminar;
    }

    public void setLimitesDeterminar(String limitesDeterminar) {
        this.limitesDeterminar = limitesDeterminar;
    }

    public String getOpinionDeterminar() {
        return opinionDeterminar;
    }

    public void setOpinionDeterminar(String opinionDeterminar) {
        this.opinionDeterminar = opinionDeterminar;
    }

    public String getComentarioDeterminar() {
        return comentarioDeterminar;
    }

    public void setComentarioDeterminar(String comentarioDeterminar) {
        this.comentarioDeterminar = comentarioDeterminar;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

	public String getPreguntaLaft() {
        return preguntaLaft;
    }

    public void setPreguntaLaft(String preguntaLaft) {
        this.preguntaLaft = preguntaLaft;
    }

    public String getRespuestaLaft() {
        return respuestaLaft;
    }

    public void setRespuestaLaft(String respuestaLaft) {
        this.respuestaLaft = respuestaLaft;
    }

    public String getPreguntaContraPartes() {
        return preguntaContraPartes;
    }

    public void setPreguntaContraPartes(String preguntaContraPartes) {
        this.preguntaContraPartes = preguntaContraPartes;
    }

    public String getRespuestaContraPartes() {
        return respuestaContraPartes;
    }

    public void setRespuestaContraPartes(String respuestaContraPartes) {
        this.respuestaContraPartes = respuestaContraPartes;
    }

}
