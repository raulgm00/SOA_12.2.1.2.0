package org.bcie.fenix.view.lineatiempooperacion;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

public class LineaTiempoOperacion implements Serializable {
    @SuppressWarnings("compatibility:-6551658847597797578")
    private static final long serialVersionUID = 1L;

    private BigDecimal id = null;
    private String descripcion = null;
    private Date fecha    = null;
    
    public LineaTiempoOperacion(String descripcion,Date fecha,BigDecimal id) {
        this.setDescripcion(descripcion);
        this.setFecha(fecha);    
        this.setId(id);
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
}
