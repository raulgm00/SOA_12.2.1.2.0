package org.bcie.fenix.common.model.vo.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Oct 07 10:47:44 CDT 2020
// ---------------------------------------------------------------------
public interface JustificacionOperacionVO extends ViewObject {
    void obtenerJustificacionPorId(oracle.jbo.domain.Number idOperacion);

    Long validarJustificacion(oracle.jbo.domain.Number idOperacion);
}

