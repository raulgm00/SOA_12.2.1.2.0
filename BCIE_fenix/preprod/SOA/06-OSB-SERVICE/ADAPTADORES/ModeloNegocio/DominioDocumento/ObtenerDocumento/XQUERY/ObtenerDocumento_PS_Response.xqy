xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $Documento as xs:string external;

declare function local:func($Documento as xs:string) as element() (:: schema-element(ns1:ObtenerDocumentoResponse) ::) {
    <ns1:ObtenerDocumentoResponse>
        <ns1:codigoDocumento>{fn:data($Documento)}</ns1:codigoDocumento>
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta exitosa</res:message>
        </ns1:Resultado>
    </ns1:ObtenerDocumentoResponse>
};

local:func($Documento)
