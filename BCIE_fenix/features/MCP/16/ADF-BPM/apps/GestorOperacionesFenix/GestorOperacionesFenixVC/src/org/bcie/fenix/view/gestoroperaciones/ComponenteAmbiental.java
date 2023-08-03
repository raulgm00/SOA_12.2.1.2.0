package org.bcie.fenix.view.gestoroperaciones;
import java.io.Serializable;
public class ComponenteAmbiental implements Serializable{
    @SuppressWarnings("compatibility:-2578648331672338966")
    private static final long serialVersionUID = 1L;
    String sector;
    String aporte;
    String categoria;
    String subCategoria;

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
}
