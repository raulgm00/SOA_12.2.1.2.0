package org.bcie.fenix.common.model.vo.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Dec 14 16:59:50 CST 2016
// ---------------------------------------------------------------------
public interface VcaTasaDesembolsoFlexcubeVO extends ViewObject {
    void filtrarPorCodigoMoneda(String codigoMoneda);

    String obtenerDescripcionTasa(String codigo);
}

