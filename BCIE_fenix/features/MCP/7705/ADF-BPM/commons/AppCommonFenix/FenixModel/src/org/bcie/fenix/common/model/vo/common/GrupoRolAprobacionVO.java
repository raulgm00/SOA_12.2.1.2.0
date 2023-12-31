package org.bcie.fenix.common.model.vo.common;

import java.util.List;

import oracle.jbo.ViewObject;

import org.bcie.fenix.common.model.vo.GrupoRolAprobacionVORowImpl;
import org.bcie.fenix.common.model.vo.MiembrosAprobacionVORowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Nov 27 20:05:26 CST 2015
// ---------------------------------------------------------------------
public interface GrupoRolAprobacionVO extends ViewObject {
    List<GrupoRolAprobacionVORowImpl> getRows();


    void configGrupoPorMiembrosAprob(List<MiembrosAprobacionVORowImpl> miembros, oracle.jbo.domain.Number idNivelAprob);

    void setRows(List<GrupoRolAprobacionVORowImpl> rows, oracle.jbo.domain.Number idNivelAprob);

    Boolean validarLista();
}

