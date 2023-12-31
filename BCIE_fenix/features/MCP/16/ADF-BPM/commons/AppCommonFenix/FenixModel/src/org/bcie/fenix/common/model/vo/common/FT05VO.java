package org.bcie.fenix.common.model.vo.common;

import java.sql.Timestamp;

import java.util.Map;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Nov 23 15:44:22 VET 2016
// ---------------------------------------------------------------------
public interface FT05VO extends ViewObject {

    boolean validarSaldoContratosDesembolso(Map contratosDesembolso);

    void insertarRowValidarFT(Long idContratoDesembolso, String contratoFelxcube, Timestamp fechaEfectiva,
                              String cuentaCustomer, String cuentaPuente);

    void validarFT(Long idContratoDesembolso, String cuentaCustomer, String cuentaPuente);
}

