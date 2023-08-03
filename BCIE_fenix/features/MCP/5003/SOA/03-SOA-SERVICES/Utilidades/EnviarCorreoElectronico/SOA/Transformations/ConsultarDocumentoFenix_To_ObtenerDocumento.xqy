xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace ns1="http://www.w3.org/2005/xquery-local-functions";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ConsultarDocumentosFenixResponse as element() (:: schema-element(ns2:ConsultarDocumentosFenixResponse) ::) external;

declare function ns1:func($ConsultarDocumentosFenixResponse as element() (:: schema-element(ns2:ConsultarDocumentosFenixResponse) ::)) as element() (:: schema-element(ns2:ObtenerDocumentoOnBaseRequest) ::) {
    
    let $id := max(fn:data($ConsultarDocumentosFenixResponse/ns2:Documento[doc:idTipoDocumento = 1100]/doc:idDocumento))
    return
    
   
    
    <ns2:ObtenerDocumentoOnBaseRequest>
        <ns2:idExterno>{fn:data($ConsultarDocumentosFenixResponse/ns2:Documento[doc:idDocumento=$id]/doc:idExterno)}</ns2:idExterno>
        <ns2:tipoArchivo>{fn:data($ConsultarDocumentosFenixResponse/ns2:Documento[doc:idDocumento=$id]/doc:mime_type)}</ns2:tipoArchivo>
    </ns2:ObtenerDocumentoOnBaseRequest>
};

ns1:func($ConsultarDocumentosFenixResponse)
