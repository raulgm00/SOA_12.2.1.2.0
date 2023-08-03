package org.bcie.fenix.view.modelo;

import java.io.Serializable;

public class Justificacion implements Serializable{
    @SuppressWarnings("compatibility:7184055415932625609")
    private static final long serialVersionUID = 1L;
    private Boolean guardado;
    private String texto;
    public Justificacion() {
        super();
        this.guardado = Boolean.FALSE;
    }

    public void setGuardado(Boolean guardado) {
        this.guardado = guardado;
    }

    public Boolean getGuardado() {
        return guardado;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
