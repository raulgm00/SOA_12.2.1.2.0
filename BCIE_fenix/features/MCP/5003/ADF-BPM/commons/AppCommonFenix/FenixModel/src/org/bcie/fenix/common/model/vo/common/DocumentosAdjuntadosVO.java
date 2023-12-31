package org.bcie.fenix.common.model.vo.common;

import java.util.List;
import java.util.Map;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Aug 27 10:43:33 CDT 2015
// ---------------------------------------------------------------------
public interface DocumentosAdjuntadosVO extends ViewObject {

    void cargarDocumentos();

    void setoperacionId(oracle.jbo.domain.Number value);

    void limpiarCacheDocumentosAdjuntadosVO();

    void setidTarea(oracle.jbo.domain.Number value);

    void setnumeroSerieDocumento(oracle.jbo.domain.Number value);


    Map currentDocuments();

    Boolean verificarEstadoDocumento();

    Map enviarDocumentoOnBase(String usuario);

    void crearRegistroTbiDocumentoPorDB(Long idDocumento, String usuario, Integer idTarea);

    void refreshDocumentosAdjuntadosAction();

    //[KB: 15525]
    Boolean existeDocumentoAdjuntoConCodigoExterno(String codExterno);
}

