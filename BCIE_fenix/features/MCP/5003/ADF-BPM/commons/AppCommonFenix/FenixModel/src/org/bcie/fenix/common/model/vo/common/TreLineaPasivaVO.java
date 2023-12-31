package org.bcie.fenix.common.model.vo.common;

import java.math.BigDecimal;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Sep 09 17:36:21 CDT 2016
// ---------------------------------------------------------------------
public interface TreLineaPasivaVO extends ViewObject {
    Row obtenerLineaPasiva(Long idContrato, Long idFuente);

    Boolean guardarMontoDesembolsarLineaPasiva(Long idContrato, Long idFuente, BigDecimal montoDesembolsar,
                                               BigDecimal montoDisponible, BigDecimal reservaTotal);

    Row buscarRowByIdContrato(Long idContrato);

    Boolean guardarFuentesCambioTab();

    Boolean montoDesembolsarLineaPasivaVsContrato(Long idContrato);
}

