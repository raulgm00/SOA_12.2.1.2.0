package org.bcie.fenix.common.model.vo.common;

import java.sql.Timestamp;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Sep 15 12:19:30 CDT 2017
// ---------------------------------------------------------------------
public interface ConsultarFechaEfectivaVO extends ViewObject {
    Timestamp obtenerFechaEfectiva(Long idOperacion);
}

