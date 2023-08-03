xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare variable $ListaDocumentosAdjuntos.request as element() (:: schema-element(doc:CargarDocumentoFenixRequest) ::) external;

declare function local:funcXqgeneracionarchivosfenixv2response($ListaDocumentosAdjuntos.request as element() (:: schema-element(doc:CargarDocumentoFenixRequest) ::)) as element() (:: schema-element(ges:GenerarArchivosFenixV2Response) ::) {
    <ges:GenerarArchivosFenixV2Response>
      { 
        for $documento in $ListaDocumentosAdjuntos.request/doc:Documentos/doc1:Documento
        return
        if ($documento/doc1:mime_type = 'application/pdf') 
          then
            <ges:Parameters>
              <com:name>ID_ADJUNTO_PDF</com:name>
              <com:value>{fn:data($documento/doc1:idAdjunto)}</com:value>
            </ges:Parameters>
          else
            <ges:Parameters>
              <com:name>ID_ADJUNTO_WORD</com:name>
              <com:value>{fn:data($documento/doc1:idAdjunto)}</com:value>
            </ges:Parameters>
      }
        <ges:Resultado>
            <res:result>OK</res:result>
            <res:message>Archivo(s) generado(s) exitosamente</res:message>
        </ges:Resultado>
    </ges:GenerarArchivosFenixV2Response>
};

local:funcXqgeneracionarchivosfenixv2response($ListaDocumentosAdjuntos.request)
