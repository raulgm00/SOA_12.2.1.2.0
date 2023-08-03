package org.bcie.fenix.view.elegibilidad;

import org.w3c.dom.Element;

public class Criterio
{
    private Integer IdCriterio;
    private Element elementCriterio;
    private Element elementPreguntas;
    
    public Criterio()
    {
        super();
    }


    public void setIdCriterio(Integer IdCriterio)
    {
        this.IdCriterio = IdCriterio;
    }

    public Integer getIdCriterio()
    {
        return IdCriterio;
    }

    public void setElementPreguntas(Element elementPreguntas)
    {
        this.elementPreguntas = elementPreguntas;
    }

    public Element getElementPreguntas()
    {
        return elementPreguntas;
    }


    public void setElementCriterio(Element elementCriterio)
    {
        this.elementCriterio = elementCriterio;
    }

    public Element getElementCriterio()
    {
        return elementCriterio;
    }

}
