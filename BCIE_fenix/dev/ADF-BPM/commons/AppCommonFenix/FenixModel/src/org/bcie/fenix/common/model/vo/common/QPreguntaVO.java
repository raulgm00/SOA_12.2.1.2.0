package org.bcie.fenix.common.model.vo.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Oct 29 18:48:26 CDT 2021
// ---------------------------------------------------------------------
public interface QPreguntaVO extends ViewObject {


    void setvarIdProceso(Long value);

    void setvarIdOperacion(Long value);

    Boolean agregarEvidenciaPorId(Long id, Long idDocumento);
}

