package envioalcobro;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class TreeLiquidarDesembolso {
    
    
    private Long id; 
    private String clave;   
    private BigDecimal saldo;
    private Integer idEstado;
    private String estado;
    private Timestamp fechaEfectiva;
    private String tipoRow;
    private List<TreeLiquidarDesembolso>nodos;
    
    public TreeLiquidarDesembolso(Long id, String clave, BigDecimal saldo, Integer idEstado, String estado, Timestamp fechaEfectiva, String tipoRow) {        
        super();        
        this.id = id;
        this.clave = clave;
        this.saldo = saldo;
        this.estado = estado;
        this.fechaEfectiva = fechaEfectiva;
        this.tipoRow = tipoRow;
        this.setIdEstado(idEstado);
        nodos = new ArrayList<TreeLiquidarDesembolso>();
    }
    
    public void addNodo(TreeLiquidarDesembolso nodo) {
              nodos.add(nodo);
          }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }
    
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setTipoRow(String tipoRow) {
        this.tipoRow = tipoRow;
    }

    public String getTipoRow() {
        return tipoRow;
    }

    public void setNodos(List<TreeLiquidarDesembolso> nodos) {
        this.nodos = nodos;
    }

    public List<TreeLiquidarDesembolso> getNodos() {
        return nodos;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public void setFechaEfectiva(Timestamp fechaEfectiva) {
        this.fechaEfectiva = fechaEfectiva;
    }

    public Timestamp getFechaEfectiva() {
        return fechaEfectiva;
    }
}
