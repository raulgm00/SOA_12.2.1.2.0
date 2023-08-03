xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare variable $ConsultarAdjuntoByIdAdjuntoResponse as element() (:: schema-element(ns1:ConsultarAdjuntoByIdAdjuntoResponse) ::) external;

declare function local:func($ConsultarAdjuntoByIdAdjuntoResponse as element() (:: schema-element(ns1:ConsultarAdjuntoByIdAdjuntoResponse) ::)) as element() (:: schema-element(ns1:ObtenerDocumentoOnBaseRequest) ::) {
    <ns1:ObtenerDocumentoOnBaseRequest>
        <ns1:idExterno>{fn:data($ConsultarAdjuntoByIdAdjuntoResponse/ns1:Documento/doc:idExterno)}</ns1:idExterno>
        <ns1:tipoArchivo>application/pdf</ns1:tipoArchivo>
    </ns1:ObtenerDocumentoOnBaseRequest>
};

local:func($ConsultarAdjuntoByIdAdjuntoResponse)
