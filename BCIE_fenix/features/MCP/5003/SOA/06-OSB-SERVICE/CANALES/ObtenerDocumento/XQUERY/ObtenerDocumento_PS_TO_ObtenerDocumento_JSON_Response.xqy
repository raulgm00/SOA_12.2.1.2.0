xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/ObtenerDocumento";
(:: import schema at "../XSD/ObtenerDocumento_JSON_Response.xsd" ::)

declare variable $ObtenerDocumentoResponse as element() (:: schema-element(ns1:ObtenerDocumentoResponse) ::) external;

declare function local:func($ObtenerDocumentoResponse as element() (:: schema-element(ns1:ObtenerDocumentoResponse) ::)) as element() (:: schema-element(ns2:ObtenerDocumentoResponse) ::) {
    <ns2:ObtenerDocumentoResponse>
        <ns2:codigo>{fn:data($ObtenerDocumentoResponse/ns1:codigoDocumento)}</ns2:codigo>
        <ns2:resultado>
            <ns2:result>OK</ns2:result>
            <ns2:message>Operacion Exitosa</ns2:message>
        </ns2:resultado>
    </ns2:ObtenerDocumentoResponse>
};

local:func($ObtenerDocumentoResponse)
