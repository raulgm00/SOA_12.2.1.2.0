xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace con="http://www.bcie.org/ConfiguracionProcesosMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/Utilidades/ConfiguracionProcesos/V1/Schema/ConfiguracionProcesosMO.xsd" ::)
declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $inputVariable.request as element() (:: schema-element(con:configuracionSeguimientoCobroRequest) ::) external;
declare variable $outInvokeConsultarDocumentosFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::) external;

declare function local:funcXqactualizardocumento($inputVariable.request as element() (:: schema-element(con:configuracionSeguimientoCobroRequest) ::), 
                                                 $outInvokeConsultarDocumentosFenix.response as element() (:: schema-element(doc:ConsultarDocumentosFenixResponse) ::)) 
                                                 as element() (:: schema-element(doc:ActualizarDocumentoRequest) ::) {
    <doc:ActualizarDocumentoRequest>
      { for $documento in $outInvokeConsultarDocumentosFenix.response/doc:Documento 
      where $documento/doc1:idTipoDocumento/text() = '1121' and (string-length($documento/doc1:idFlujoNegocio/text()) = 0) and ($documento/doc1:idAdjunto/text() = $inputVariable.request/con:Parameters[com:name/text() = 'ID_ADJUNTO_WORD' or com:name/text() = 'ID_ADJUNTO_PDF']/com:value/text())
      return
        <doc:Documento>
            <doc1:idDocumento>{fn:data($documento/doc1:idDocumento/text())}</doc1:idDocumento>
            <doc1:idFlujoNegocio>{fn:data($inputVariable.request/con:InstanciaProceso)}</doc1:idFlujoNegocio>
        </doc:Documento>
        }
    </doc:ActualizarDocumentoRequest>
};

local:funcXqactualizardocumento($inputVariable.request, $outInvokeConsultarDocumentosFenix.response)
