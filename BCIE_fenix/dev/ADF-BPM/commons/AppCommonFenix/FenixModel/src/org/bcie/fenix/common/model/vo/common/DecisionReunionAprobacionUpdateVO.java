package org.bcie.fenix.common.model.vo.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Dec 05 19:51:57 CST 2017
// ---------------------------------------------------------------------
public interface DecisionReunionAprobacionUpdateVO extends ViewObject {
    Boolean actualizarDecisionReunion(oracle.jbo.domain.Number idSolicitudAprob, oracle.jbo.domain.Number idTipoAccion,
                                      String numActa, String numAcuerdo, String acuerdo, String userName);
}

