package org.bcie.fenix.common.model.vo.consultarconfigcondicionesfinancieras.common;

import java.util.Map;

import oracle.jbo.ViewObject;

import org.bcie.fenix.common.model.vo.consultarconfigcondicionesfinancieras.clases.TablaDinamicaVOBean;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Dec 13 18:13:06 CST 2021
// ---------------------------------------------------------------------
public interface TcaTareaConBtnDinVO extends ViewObject {
    Map<String, TablaDinamicaVOBean> obtenerDatos(String p_nombreTabla, Integer p_idTareaBpm, Integer p_IdProducto);
}

