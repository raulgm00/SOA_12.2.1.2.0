package org.bcie.fenix.view.gestordesembolsos;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.domain.Date;

public class DatosSesionManagedBean implements Serializable {
    
    @SuppressWarnings("compatibility:2303799147747537613")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    
    public DatosSesionManagedBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    private String operacion;
    private String cliente;
    private String producto;
    private String pais;
    private String flexcube;
    private String noObjecion;
    private String scr;
    private String mora;
    private String diasMora;
    private String moneda;
    private String sectorDescripcion;
    private String descSectorMercado;
    
    private Long IdOperacion;
    private Long IdCliente;
    private Long Sector;
    private Long IdProducto;
    private Long IdSectorMercado;
    
    private Double MontoMora;
    private Double MontoFormalizado;
    private Double MontoDesembolsar;
    
    private oracle.jbo.domain.Date VigenciaNoObjecionLaft;
    
    /**Accessors**/
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setFlexcube(String flexcube) {
        this.flexcube = flexcube;
    }

    public String getFlexcube() {
        return flexcube;
    }

    public void setNoObjecion(String noObjecion) {
        this.noObjecion = noObjecion;
    }

    public String getNoObjecion() {
        return noObjecion;
    }

    public void setScr(String scr) {
        this.scr = scr;
    }

    public String getScr() {
        return scr;
    }

    public void setMora(String mora) {
        this.mora = mora;
    }

    public String getMora() {
        return mora;
    }

    public void setDiasMora(String diasMora) {
        this.diasMora = diasMora;
    }

    public String getDiasMora() {
        return diasMora;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setSectorDescripcion(String sectorDescripcion) {
        this.sectorDescripcion = sectorDescripcion;
    }

    public String getSectorDescripcion() {
        return sectorDescripcion;
    }

    public void setDescSectorMercado(String descSectorMercado) {
        this.descSectorMercado = descSectorMercado;
    }

    public String getDescSectorMercado() {
        return descSectorMercado;
    }

    public void setIdOperacion(Long IdOperacion) {
        this.IdOperacion = IdOperacion;
    }

    public Long getIdOperacion() {
        return IdOperacion;
    }

    public void setIdCliente(Long IdCliente) {
        this.IdCliente = IdCliente;
    }

    public Long getIdCliente() {
        return IdCliente;
    }

    public void setSector(Long Sector) {
        this.Sector = Sector;
    }

    public Long getSector() {
        return Sector;
    }

    public void setIdProducto(Long IdProducto) {
        this.IdProducto = IdProducto;
    }

    public Long getIdProducto() {
        return IdProducto;
    }

    public void setIdSectorMercado(Long IdSectorMercado) {
        this.IdSectorMercado = IdSectorMercado;
    }

    public Long getIdSectorMercado() {
        return IdSectorMercado;
    }

    public void setMontoMora(Double MontoMora) {
        this.MontoMora = MontoMora;
    }

    public Double getMontoMora() {
        return MontoMora;
    }

    public void setMontoFormalizado(Double MontoFormalizado) {
        this.MontoFormalizado = MontoFormalizado;
    }

    public Double getMontoFormalizado() {
        return MontoFormalizado;
    }

    public void setMontoDesembolsar(Double MontoDesembolsar) {
        this.MontoDesembolsar = MontoDesembolsar;
    }

    public Double getMontoDesembolsar() {
        return MontoDesembolsar;
    }

    public void setVigenciaNoObjecionLaft(Date VigenciaNoObjecionLaft) {
        this.VigenciaNoObjecionLaft = VigenciaNoObjecionLaft;
    }

    public Date getVigenciaNoObjecionLaft() {
        return VigenciaNoObjecionLaft;
    }

}
