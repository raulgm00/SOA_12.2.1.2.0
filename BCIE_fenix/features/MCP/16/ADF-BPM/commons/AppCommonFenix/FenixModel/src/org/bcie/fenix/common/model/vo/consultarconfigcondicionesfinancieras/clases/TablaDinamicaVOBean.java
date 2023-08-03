package org.bcie.fenix.common.model.vo.consultarconfigcondicionesfinancieras.clases;

public class TablaDinamicaVOBean {
    
    private Integer idTabla;
    private String componente;
    private Integer idTareaBpm;
    private Integer banStatus;
    private String accion;
    private Integer productoConfigurado;
    private Integer esRetorno;
    private Integer visibilidad;
    private String fechaRegistro;
    private Integer tablaVerdad[][];
    private Integer idproductoConfigurado;
    


    public TablaDinamicaVOBean(Integer idTabla, String componente, Integer idTareaBpm, Integer banStatus, String accion,Integer productoConfigurado, Integer esRetorno, Integer visibilidad, String fechaRegistro,  Integer [][] tablaVerdad, Integer idproductoConfigurado) {
        super();
        this.idTabla = idTabla;
        this.componente = componente;
        this.idTareaBpm = idTareaBpm;
        this.banStatus = banStatus;
        this.accion = accion;
        this.productoConfigurado = productoConfigurado;
        this.esRetorno = esRetorno;
        this.visibilidad = visibilidad;
        this.fechaRegistro = fechaRegistro;
        this.tablaVerdad = tablaVerdad;
        this.idproductoConfigurado = idproductoConfigurado;
        
    }
    
    public String toString(){
        
        String cadena= "";
        cadena = cadena + "ID = " + this.idTabla + "\n";
        cadena = cadena + "Componente = " + this.componente + "\n";
        cadena = cadena + "ID Tarea BPM = " + this.idTareaBpm + "\n";
        cadena = cadena + "Ban Status = " + this.banStatus + "\n";
        cadena = cadena + "Acción = " + this.accion + "\n";
        cadena = cadena + "¿Es poducto configurado? = " + this.productoConfigurado+ "\n";
        cadena = cadena + "¿Es retorno? = " + this.esRetorno + "\n";
        cadena = cadena + "Visibilidad = " + this.visibilidad + "\n";
        cadena = cadena + "Fecha Registro = " + this.fechaRegistro + "\n";
        cadena = cadena + "ID Producto Configurado= " + this.idproductoConfigurado + "\n";
        cadena = cadena + imprimirTablaVerdad( this.tablaVerdad);
        
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

    public void setBanStatus(Integer banStatus) {
        this.banStatus = banStatus;
    }

    public Integer getbanStatus() {
        return banStatus;
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


    public void setIdproductoConfigurado(Integer idproductoConfigurado) {
        this.idproductoConfigurado = idproductoConfigurado;
    }

    public Integer getIdproductoConfigurado() {
        return idproductoConfigurado;
    }

}

