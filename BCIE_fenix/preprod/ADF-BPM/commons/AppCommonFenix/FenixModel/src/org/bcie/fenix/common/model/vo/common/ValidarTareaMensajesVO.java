package org.bcie.fenix.common.model.vo.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Apr 27 15:31:29 CST 2021
// ---------------------------------------------------------------------
public interface ValidarTareaMensajesVO extends ViewObject {
    void filtrarMensajes(Long idTarea);

    void filtrarMensajes(Long idTarea, Long idProducto, String accionFinalizarTarea);

    void filtrarMensajes(Long idTarea, String accionFinalizarTarea);

    void filtrarMensajes(Long idTarea, Long idProducto, Integer idOperacion, Integer idCliente, Integer idProceso,
                         String accionFinalizarTarea);
}

