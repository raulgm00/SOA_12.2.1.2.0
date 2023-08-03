xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare variable $ObtenerDocumentoRequest as element() (:: schema-element(ns1:ObtenerDocumentoRequest) ::) external;

declare function local:func($ObtenerDocumentoRequest as element() (:: schema-element(ns1:ObtenerDocumentoRequest) ::)) as element() (:: schema-element(ns1:ConsultarAdjuntoByIdAdjuntoRequest) ::) {
    <ns1:ConsultarAdjuntoByIdAdjuntoRequest>
        <ns1:idAdjunto>{fn:data($ObtenerDocumentoRequest/ns1:idAdjunto)}</ns1:idAdjunto>
    </ns1:ConsultarAdjuntoByIdAdjuntoRequest>
};

local:func($ObtenerDocumentoRequest)
