package org.bcie.fenix.common.model.vo.common;

import java.math.BigDecimal;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat Oct 08 12:44:36 CDT 2016
// ---------------------------------------------------------------------
public interface DetalleCargoPenalidadPrepagoVO extends ViewObject {
    void crearRowDetalleCargoPenalidadPrepago(BigDecimal penalidad, String nombreMoneda, BigDecimal montoCargoCopres,
                                              Integer idTipoMonedaCopres, BigDecimal montoCargoDaeci,
                                              Integer idTipoMonedaDaeci, BigDecimal montoCargoMdc,
                                              Integer idTipoMonedaMdc, BigDecimal montoCargoSepri,
                                              Integer idTipoMonedaSepri, BigDecimal montoCargoCofi,
                                              Integer idTipoMonedaCofi, BigDecimal montoCargoTramiteCofi,
                                              Integer idTipoMonedaTramiteCofi, String nombreMonedaLocal,
                                              String nombreMonedaDolar);
}

