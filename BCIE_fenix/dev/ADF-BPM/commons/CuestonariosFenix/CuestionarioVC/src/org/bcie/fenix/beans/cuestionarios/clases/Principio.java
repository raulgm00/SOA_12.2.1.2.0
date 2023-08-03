package org.bcie.fenix.beans.cuestionarios.clases;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

public class Principio implements Serializable {
    @SuppressWarnings("compatibility:4215657272782797879")
    private static final long serialVersionUID = 1L;

    private Integer IdPrincipio;
    private Integer IdRol;
    private transient Element elementPrincipio;
    private List<Criterio> listCriterios;


    public Principio() {
        super();
        listCriterios = new ArrayList<Criterio>();
    }

    public void setIdPrincipio(Integer IdPrincipio) {
        this.IdPrincipio = IdPrincipio;
    }

    public Integer getIdPrincipio() {
        return IdPrincipio;
    }

    public void setElementPrincipio(Element elementPrincipio) {
        this.elementPrincipio = elementPrincipio;
    }

    public Element getElementPrincipio() {
        return elementPrincipio;
    }

    public void setIdRol(Integer IdRol) {
        this.IdRol = IdRol;
    }

    public Integer getIdRol() {
        return IdRol;
    }

    public void setListCriterios(List<Criterio> listCriterios) {
        this.listCriterios = listCriterios;
    }

    public List<Criterio> getListCriterios() {
        return listCriterios;
    }
}
