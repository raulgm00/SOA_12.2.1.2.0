package org.bcie.fenix.common.model.vo.common;

import java.sql.Timestamp;

import java.util.List;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Dec 18 18:47:13 CST 2015
// ---------------------------------------------------------------------
public interface VencimientoPlazoVO extends ViewObject {


    void eliminarVencimiento(Integer id);

    void agregarVencimiento(Long idTipoDeVencimiento, Long idTipoDeFecha, Long idPlazo, String tipoDeVencimiento,
                            String tipoDeFecha, String tipoPlazo, String plazo, Timestamp fechaInicio,
                            Timestamp fechaVencimiento);

    void modificarVencimiento(Integer id, Long idVencimiento, Long idTipoDeVencimiento, Long idTipoDeFecha,
                              Long idPlazo, String tipoDeVencimiento, String tipoDeFecha, String tipoPlazo,
                              String plazo, Timestamp fechaInicio, Timestamp fechaVencimiento);

    void agregarVencimientosEliminados(List idsVencimientosEliminados);

    void eliminarVencimientoAgregados(List idsVencimientosEliminados);
}

