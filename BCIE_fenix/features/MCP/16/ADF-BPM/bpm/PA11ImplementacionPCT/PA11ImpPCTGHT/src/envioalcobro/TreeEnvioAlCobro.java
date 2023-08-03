package envioalcobro;


import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.ArrayList;
import oracle.jbo.domain.Date;
import java.util.List;

public class TreeEnvioAlCobro {
    
        private String numeroLinea;
        private Long id;
        private BigDecimal monto;
        private BigDecimal interes;
        private BigDecimal montoAmpliacion;
        private Date fechaVigencia;    
        private Timestamp fechaMaximaDesembolsar;
        private String tipoRow;
        private List<TreeEnvioAlCobro> nodos;

    public TreeEnvioAlCobro(String numeroLinea, BigDecimal monto, BigDecimal interes,BigDecimal montoAmpliacion, Timestamp fechaMaximaDesembolsar, String tipoRow, Long id) {
        super();
        this.numeroLinea = numeroLinea; 
        this.monto = monto; 
        this.interes = interes; 
        this.montoAmpliacion = montoAmpliacion;
        this.fechaMaximaDesembolsar = fechaMaximaDesembolsar;
        this.tipoRow = tipoRow;
        this.id = id;
                nodos = new ArrayList<TreeEnvioAlCobro>(); 
    }
    
    public void addNodo(TreeEnvioAlCobro nodo) {
              nodos.add(nodo);
          }
    
    
    public void setNumeroLinea(String numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public String getNumeroLinea() {
        return numeroLinea;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setNodos(List<TreeEnvioAlCobro> nodos) {
        this.nodos = nodos;
    }

    public List<TreeEnvioAlCobro> getNodos() {
        return nodos;
    }
    
    public void setMontoAmpliacion(BigDecimal montoAmpliacion) {
        this.montoAmpliacion = montoAmpliacion;
    }

    public BigDecimal getMontoAmpliacion() {
        return montoAmpliacion;
    }
    
    public void setTipoRow(String tipoRow) {
        this.tipoRow = tipoRow;
    }

    public String getTipoRow() {
        return tipoRow;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void setFechaMaximaDesembolsar(Timestamp fechaMaximaDesembolsar) {
        this.fechaMaximaDesembolsar = fechaMaximaDesembolsar;
    }

    public Timestamp getFechaMaximaDesembolsar() {
        return fechaMaximaDesembolsar;
    }
}
