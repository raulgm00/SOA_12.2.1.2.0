package org.bcie.fenix.view.ambiental;
import java.io.Serializable;
public class ComponenteAmbiental implements Serializable{
    @SuppressWarnings("compatibility:302347969632486226")
    private static final long serialVersionUID = 1L;
    String sector;
    String idSector;
    String aporte;
    String idAporte;
    String categoria;
    String idCategoria;
    String subCategoria;
    String idSubCategoria;

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return sector;
    }

    public void setAporte(String aporte) {
        this.aporte = aporte;
    }

    public String getAporte() {
        return aporte;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getSubCategoria() {
        return subCategoria;
    }
    public void setIdSector(String idSector) {
        this.idSector = idSector;
    }

    public String getIdSector() {
        return idSector;
    }

    public void setIdAporte(String idAporte) {
        this.idAporte = idAporte;
    }

    public String getIdAporte() {
        return idAporte;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdSubCategoria(String idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    public String getIdSubCategoria() {
        return idSubCategoria;
    }
}
