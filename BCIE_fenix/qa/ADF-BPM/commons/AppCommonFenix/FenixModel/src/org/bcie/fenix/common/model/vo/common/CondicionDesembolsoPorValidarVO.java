package org.bcie.fenix.common.model.vo.common;

import java.util.List;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 26 13:58:50 CST 2020
// ---------------------------------------------------------------------
public interface CondicionDesembolsoPorValidarVO extends ViewObject {
    Boolean condicionDesembolsoPorValidar(Long pnID_Contrato_Desembolso);


    Integer configuracionCondicion(Long idSolicitud, Long idOperacion,Long idContrato,Integer idEvento, List listaCondiciones);

    Boolean iniciarAdminstracionCondicion(Long idSolicitud, Long idOperacion, Integer agrupador, Long idContrato,
                                          String loginUsuario, String descEvento);

    Boolean iniciarCondicionDesembolso(Long idSolicitud, Long idOperacion, Long idContrato, List listaCondiciones);

    Boolean inicioValidacionAsignacionRecursos(Long idOperacion, String loginUsuario, Long idSolicitud,
                                               Long idContrato);
}

