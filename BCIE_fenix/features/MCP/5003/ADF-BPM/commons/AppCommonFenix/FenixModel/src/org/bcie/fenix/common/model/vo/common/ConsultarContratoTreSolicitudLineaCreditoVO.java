package org.bcie.fenix.common.model.vo.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Nov 02 17:07:37 CST 2016
// ---------------------------------------------------------------------
public interface ConsultarContratoTreSolicitudLineaCreditoVO extends ViewObject {
    Long obtenerPrimerContratoActivo(Long idSolicitud);

    Boolean setFechaIniProcesParaContratosByIdSolicitud(Long idSolicitud);

    Boolean validarCamopsObligatoriosContratos(Long idSolicitud);

    Boolean validarEstadoContratosBySolicitud(Long idSolicitud);

    Boolean desestimarContratosBySolicitud(Long idSolicitud);

    Long idContratoMayorSolicitud(Long idSolicitud);
}

