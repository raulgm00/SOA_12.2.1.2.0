package org.bcie.fenix.common.model.vo.common;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Jan 06 19:43:01 CST 2017
// ---------------------------------------------------------------------
public interface ContratosPorLiquidarByOperacion extends ViewObject {

    Integer obtenerContratosPorLiquidar(Long idOperacion);

    Row[] recuperarContratosPorLiquidar(Long idOperacion, Long idSolicitud);
}

