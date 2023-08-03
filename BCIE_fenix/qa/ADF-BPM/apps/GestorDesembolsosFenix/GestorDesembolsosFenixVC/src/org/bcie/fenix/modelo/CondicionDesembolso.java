package org.bcie.fenix.modelo;

import java.io.Serializable;

import java.sql.Timestamp;

public class CondicionDesembolso implements Serializable {
    @SuppressWarnings("compatibility:2934071977387951747")
    private static final long serialVersionUID = 1L;
    private Long idOperacion;
    private Long idCondicion;
    private String nombre;
    private String banEstatus;
    private String estadoTcc;
    private String controlCondicion;
    private String evento;
    private String tipoDesembolso;
    private Timestamp fechaRegistro;
    private String descripcionCalendario;
    private String categorias;
    private String validadores;
    private Long agrupador;
    private Integer estadoTransaccion;
    private Integer enProceso;
    private Boolean seleccionado;
    public CondicionDesembolso() {
        super();
    }

    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Long getIdOperacion() {
        return idOperacion;
    }

    public void setIdCondicion(Long idCondicion) {
        this.idCondicion = idCondicion;
    }

    public Long getIdCondicion() {
        return idCondicion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setBanEstatus(String banEstatus) {
        this.banEstatus = banEstatus;
    }

    public String getBanEstatus() {
        return banEstatus;
    }

    public void setEstadoTcc(String estadoTcc) {
        this.estadoTcc = estadoTcc;
    }

    public String getEstadoTcc() {
        return estadoTcc;
    }

    public void setControlCondicion(String controlCondicion) {
        this.controlCondicion = controlCondicion;
    }

    public String getControlCondicion() {
        return controlCondicion;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getEvento() {
        return evento;
    }

    public void setTipoDesembolso(String tipoDesembolso) {
        this.tipoDesembolso = tipoDesembolso;
    }

    public String getTipoDesembolso() {
        return tipoDesembolso;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setDescripcionCalendario(String descripcionCalendario) {
        this.descripcionCalendario = descripcionCalendario;
    }

    public String getDescripcionCalendario() {
        return descripcionCalendario;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setValidadores(String validadores) {
        this.validadores = validadores;
    }

    public String getValidadores() {
        return validadores;
    }

    public void setAgrupador(Long agrupador) {
        this.agrupador = agrupador;
    }

    public Long getAgrupador() {
        return agrupador;
    }

    public void setEstadoTransaccion(Integer estadoTransaccion) {
        this.estadoTransaccion = estadoTransaccion;
    }

    public Integer getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setEnProceso(Integer enProceso) {
        this.enProceso = enProceso;
    }

    public Integer getEnProceso() {
        return enProceso;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Boolean getSeleccionado() {
        return seleccionado;
    }
}
