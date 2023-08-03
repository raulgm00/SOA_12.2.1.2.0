package org.bcie.fenix.view.aplicacionesexternas;

import java.io.Serializable;

public class AplicacionExterna implements Serializable {
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nombre;
    private String url;
    private String destino;

    public AplicacionExterna(Long id, String nombre, String url, String destino) {
        this.setId(id);
        this.setNombre(nombre);
        this.setUrl(url);
        this.setDestino(destino);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
