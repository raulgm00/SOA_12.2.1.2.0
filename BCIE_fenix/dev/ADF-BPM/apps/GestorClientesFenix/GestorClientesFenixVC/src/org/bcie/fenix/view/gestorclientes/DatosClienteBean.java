package org.bcie.fenix.view.gestorclientes;

import java.io.Serializable;

import java.sql.Timestamp;

import oracle.adf.share.logging.ADFLogger;

public class DatosClienteBean implements Serializable{
    
    private static ADFLogger logger = null;
    
    public DatosClienteBean() {
        if(logger == null){
            logger = ADFLogger.createADFLogger(this.getClass());
        }                        
    }
    
    private Long idCliente;
    private String idFlexcube;
    private String razonSocial;
    private String abreviatura;
    private Integer tipoPersona;
    private Long tipoCliente;
    private Integer sector;
    private Integer tipoInstitucion;
    private Integer pais;
    private Integer grupoEconomico;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private Integer oficina;
    private Timestamp fechaRegistro;
    private Timestamp fechaAprobacion;
    private String ejecutivo;
    private String comentarioAprobacion;
    private String autorizo;
    private String banEstatus;
    private Timestamp fechaBaja;
    private Integer diaPago;
    private Long scrId;
    private String scrDescripcion;
    private Long perspectivaId;
    private String perspectivaDescripcion;
    private Boolean enMora;
    private Boolean reqEnvioAvisoCobroAut;
    private Boolean esDeteriorado;
    private Integer diasMora;
    private String bicCode;
    private String direccion;

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdFlexcube(String idFlexcube) {
        this.idFlexcube = idFlexcube;
    }

    public String getIdFlexcube() {
        return idFlexcube;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setTipoPersona(Integer tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Integer getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoCliente(Long tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Long getTipoCliente() {
        return tipoCliente;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    public Integer getSector() {
        return sector;
    }

    public void setTipoInstitucion(Integer tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }

    public Integer getTipoInstitucion() {
        return tipoInstitucion;
    }

    public void setPais(Integer pais) {
        this.pais = pais;
    }

    public Integer getPais() {
        return pais;
    }

    public void setGrupoEconomico(Integer grupoEconomico) {
        this.grupoEconomico = grupoEconomico;
    }

    public Integer getGrupoEconomico() {
        return grupoEconomico;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setOficina(Integer oficina) {
        this.oficina = oficina;
    }

    public Integer getOficina() {
        return oficina;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaAprobacion(Timestamp fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Timestamp getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
    }

    public String getEjecutivo() {
        return ejecutivo;
    }

    public void setComentarioAprobacion(String comentarioAprobacion) {
        this.comentarioAprobacion = comentarioAprobacion;
    }

    public String getComentarioAprobacion() {
        return comentarioAprobacion;
    }

    public void setAutorizo(String autorizo) {
        this.autorizo = autorizo;
    }

    public String getAutorizo() {
        return autorizo;
    }

    public void setBanEstatus(String banEstatus) {
        this.banEstatus = banEstatus;
    }

    public String getBanEstatus() {
        return banEstatus;
    }

    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    public void setDiaPago(Integer diaPago) {
        this.diaPago = diaPago;
    }

    public Integer getDiaPago() {
        return diaPago;
    }

    public void setScrId(Long scrId) {
        this.scrId = scrId;
    }

    public Long getScrId() {
        return scrId;
    }

    public void setScrDescripcion(String scrDescripcion) {
        this.scrDescripcion = scrDescripcion;
    }

    public String getScrDescripcion() {
        return scrDescripcion;
    }

    public void setPerspectivaId(Long perspectivaId) {
        this.perspectivaId = perspectivaId;
    }

    public Long getPerspectivaId() {
        return perspectivaId;
    }

    public void setPerspectivaDescripcion(String perspectivaDescripcion) {
        this.perspectivaDescripcion = perspectivaDescripcion;
    }

    public String getPerspectivaDescripcion() {
        return perspectivaDescripcion;
    }

    public void setEnMora(Boolean enMora) {
        this.enMora = enMora;
    }

    public Boolean getEnMora() {
        return enMora;
    }

    public void setReqEnvioAvisoCobroAut(Boolean reqEnvioAvisoCobroAut) {
        this.reqEnvioAvisoCobroAut = reqEnvioAvisoCobroAut;
    }

    public Boolean getReqEnvioAvisoCobroAut() {
        return reqEnvioAvisoCobroAut;
    }

    public void setEsDeteriorado(Boolean esDeteriorado) {
        this.esDeteriorado = esDeteriorado;
    }

    public Boolean getEsDeteriorado() {
        return esDeteriorado;
    }

    public void setDiasMora(Integer diasMora) {
        this.diasMora = diasMora;
    }

    public Integer getDiasMora() {
        return diasMora;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }

    public String getBicCode() {
        return bicCode;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }
}

