package org.bcie.fenix.view.gestordesembolsos;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

public class CriterioBusquedaBean {
    
    private RichInputText tiCliente;
    private RichInputText itIdSolicitud;
    private RichSelectOneChoice socResponsableOperacion;
    private RichInputText itBhqCliente;
    private RichInputText itNumeroLineaCredito;
    private RichSelectOneChoice socSectorInstitucional;
    private RichInputText itNombreOperacion;
    private RichInputText itIdDesembolso;
    private RichInputText itIdOperacion;
    private RichSelectOneChoice socPais;
    private RichSelectOneChoice socEstado;
    private RichSelectOneChoice socTipoProducto;
    private RichSelectOneChoice socTipoFecha;
    private RichInputDate idFechaDel;
    private RichInputDate idFechaAl;
    
    private String cliente;
    private String idSolicitud;
    private String responsableOperacion;
    private String bhqCliente;
    private String numeroLineaCredito;
    private String sectorInstitucional;
    private String nombreOperacion;
    private String idDesembolso;
    private String idOperacion;
    private String pais;
    private String estado;
    private String tipoProducto;
    private String tipoFecha;
    private String fechaDel;
    private String fechaAl;
    
    public void setTiCliente(RichInputText tiCliente) {
        this.tiCliente = tiCliente;
    }

    public RichInputText getTiCliente() {
        return tiCliente;
    }

    public void setItIdSolicitud(RichInputText itIdSolicitud) {
        this.itIdSolicitud = itIdSolicitud;
    }

    public RichInputText getItIdSolicitud() {
        return itIdSolicitud;
    }

    public void setSocResponsableOperacion(RichSelectOneChoice socResponsableOperacion) {
        this.socResponsableOperacion = socResponsableOperacion;
    }

    public RichSelectOneChoice getSocResponsableOperacion() {
        return socResponsableOperacion;
    }

    public void setItBhqCliente(RichInputText itBhqCliente) {
        this.itBhqCliente = itBhqCliente;
    }

    public RichInputText getItBhqCliente() {
        return itBhqCliente;
    }

    public void setItNumeroLineaCredito(RichInputText itNumeroLineaCredito) {
        this.itNumeroLineaCredito = itNumeroLineaCredito;
    }

    public RichInputText getItNumeroLineaCredito() {
        return itNumeroLineaCredito;
    }

    public void setSocSectorInstitucional(RichSelectOneChoice socSectorInstitucional) {
        this.socSectorInstitucional = socSectorInstitucional;
    }

    public RichSelectOneChoice getSocSectorInstitucional() {
        return socSectorInstitucional;
    }

    public void setItNombreOperacion(RichInputText itNombreOperacion) {
        this.itNombreOperacion = itNombreOperacion;
    }

    public RichInputText getItNombreOperacion() {
        return itNombreOperacion;
    }

    public void setItIdDesembolso(RichInputText itIdDesembolso) {
        this.itIdDesembolso = itIdDesembolso;
    }

    public RichInputText getItIdDesembolso() {
        return itIdDesembolso;
    }

    public void setItIdOperacion(RichInputText itIdOperacion) {
        this.itIdOperacion = itIdOperacion;
    }

    public RichInputText getItIdOperacion() {
        return itIdOperacion;
    }

    public void setSocPais(RichSelectOneChoice socPais) {
        this.socPais = socPais;
    }

    public RichSelectOneChoice getSocPais() {
        return socPais;
    }

    public void setSocEstado(RichSelectOneChoice socEstado) {
        this.socEstado = socEstado;
    }

    public RichSelectOneChoice getSocEstado() {
        return socEstado;
    }

    public void setSocTipoProducto(RichSelectOneChoice socTipoProducto) {
        this.socTipoProducto = socTipoProducto;
    }

    public RichSelectOneChoice getSocTipoProducto() {
        return socTipoProducto;
    }

    public void setSocTipoFecha(RichSelectOneChoice socTipoFecha) {
        this.socTipoFecha = socTipoFecha;
    }

    public RichSelectOneChoice getSocTipoFecha() {
        return socTipoFecha;
    }

    public void setIdFechaDel(RichInputDate idFechaDel) {
        this.idFechaDel = idFechaDel;
    }

    public RichInputDate getIdFechaDel() {
        return idFechaDel;
    }

    public void setIdFechaAl(RichInputDate idFechaAl) {
        this.idFechaAl = idFechaAl;
    }

    public RichInputDate getIdFechaAl() {
        return idFechaAl;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getResponsableOperacion() {
        return responsableOperacion;
    }

    public void setResponsableOperacion(String responsableOperacion) {
        this.responsableOperacion = responsableOperacion;
    }

    public String getBhqCliente() {
        return bhqCliente;
    }

    public void setBhqCliente(String bhqCliente) {
        this.bhqCliente = bhqCliente;
    }

    public String getNumeroLineaCredito() {
        return numeroLineaCredito;
    }

    public void setNumeroLineaCredito(String numeroLineaCredito) {
        this.numeroLineaCredito = numeroLineaCredito;
    }

    public String getSectorInstitucional() {
        return sectorInstitucional;
    }

    public void setSectorInstitucional(String sectorInstitucional) {
        this.sectorInstitucional = sectorInstitucional;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public String getIdDesembolso() {
        return idDesembolso;
    }

    public void setIdDesembolso(String idDesembolso) {
        this.idDesembolso = idDesembolso;
    }

    public String getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getTipoFecha() {
        return tipoFecha;
    }

    public void setTipoFecha(String tipoFecha) {
        this.tipoFecha = tipoFecha;
    }

    public String getFechaDel() {
        return fechaDel;
    }

    public void setFechaDel(String fechaDel) {
        this.fechaDel = fechaDel;
    }

    public String getFechaAl() {
        return fechaAl;
    }

    public void setFechaAl(String fechaAl) {
        this.fechaAl = fechaAl;
    }
}
