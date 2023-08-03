package org.bcie.fenix.beans.cuestionarios.clases;

import java.io.Serializable;
import org.w3c.dom.Element;

public class Criterio implements Serializable {
    @SuppressWarnings("compatibility:1075169230487180745")
    private static final long serialVersionUID = 1L;

    private Integer IdCriterio;
    private transient  Element elementCriterio;
    private transient  Element elementPreguntas;

    public Criterio() {
        super();
    }

    public void setIdCriterio(Integer IdCriterio) {
        this.IdCriterio = IdCriterio;
    }

    public Integer getIdCriterio() {
        return IdCriterio;
    }

    public void setElementPreguntas(Element elementPreguntas) {
        this.elementPreguntas = elementPreguntas;
    }

    public Element getElementPreguntas() {
        return elementPreguntas;
    }

    public void setElementCriterio(Element elementCriterio) {
        this.elementCriterio = elementCriterio;
    }

    public Element getElementCriterio() {
        return elementCriterio;
    }

}
