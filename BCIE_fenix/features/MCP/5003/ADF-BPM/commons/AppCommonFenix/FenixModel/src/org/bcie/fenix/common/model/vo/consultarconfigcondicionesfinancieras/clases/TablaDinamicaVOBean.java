package org.bcie.fenix.common.model.vo.consultarconfigcondicionesfinancieras.clases;

public class TablaDinamicaVOBean {
    
    private Integer idTabla;
    private String componente;
    private Integer idTareaBpm;
    private Integer idProducto;
    private String accion;
    private Integer productoConfigurado;
    private Integer esRetorno;
    private Integer visibilidad;
    private Integer tablaVerdad[][];
    private String fechaRegistro;
    private String productosConfigurados;


    public TablaDinamicaVOBean(Integer idTabla, String componente, Integer idTareaBpm, Integer idProducto, String accion,Integer productoConfigurado, Integer esRetorno, Integer visibilidad, String fechaRegistro,  Integer [][] tablaVerdad, String productosConfigurados) {
        super();
        this.idTabla = idTabla;
        this.componente = componente;
        this.idTareaBpm = idTareaBpm;
        this.idProducto = idProducto;
        this.accion = accion;
        this.productoConfigurado = productoConfigurado;
        this.esRetorno = esRetorno;
        this.visibilidad = visibilidad;
        this.fechaRegistro = fechaRegistro;
        this.tablaVerdad = tablaVerdad;
        this.productosConfigurados = productosConfigurados;
    }
    
    public String toString(){
        
        String cadena= "";
        cadena = cadena + "ID = " + this.idTabla + "\n";
        cadena = cadena + "Componente = " + this.componente + "\n";
        cadena = cadena + "ID Tarea BPM = " + this.idTareaBpm + "\n";
        cadena = cadena + "ID Producto = " + this.idProducto + "\n";
        cadena = cadena + "Acción = " + this.accion + "\n";
        cadena = cadena + "Producto Configurado = " + this.productoConfigurado+ "\n";
        cadena = cadena + "¿Es retorno? = " + this.esRetorno + "\n";
        cadena = cadena + "Visibilidad = " + this.visibilidad + "\n";
        cadena = cadena + "Fecha Registro = " + this.fechaRegistro + "\n";
        cadena = cadena + imprimirTablaVerdad( this.tablaVerdad);
        cadena = cadena + "Productos Configurados = " + this.productosConfigurados + "\n";
        return cadena;
    }
    
    public String imprimirTablaVerdad(Integer[][] t) {
        String cadena= "";
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                //System.out.println("[" + x + "]" + "[" + y + "]" + " = " + t[x][y]);
                cadena = cadena + "[" + x + "]" + "[" + y + "]" + " = " + t[x][y] +"\n";
            }
        }
        return cadena;
    }

    public void setIdTabla(Integer idTabla) {
        this.idTabla = idTabla;
    }

    public Integer getIdTabla() {
        return idTabla;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getComponente() {
        return componente;
    }

    public void setIdTareaBpm(Integer idTareaBpm) {
        this.idTareaBpm = idTareaBpm;
    }

    public Integer getIdTareaBpm() {
        return idTareaBpm;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setTablaVerdad(Integer[][] tablaVerdad) {
        this.tablaVerdad = tablaVerdad;
    }

    public Integer[][] getTablaVerdad() {
        return tablaVerdad;
    }
    public void setProductoConfigurado(Integer productoConfigurado) {
        this.productoConfigurado = productoConfigurado;
    }

    public Integer getProductoConfigurado() {
        return productoConfigurado;
    }

    public void setEsRetorno(Integer esRetorno) {
        this.esRetorno = esRetorno;
    }

    public Integer getEsRetorno() {
        return esRetorno;
    }

    public void setVisibilidad(Integer visibilidad) {
        this.visibilidad = visibilidad;
    }

    public Integer getVisibilidad() {
        return visibilidad;
    }
    

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }
    

    public void setProductosConfigurados(String productosConfigurados) {
        this.productosConfigurados = productosConfigurados;
    }

    public String getProductosConfigurados() {
        return productosConfigurados;
    }

}

