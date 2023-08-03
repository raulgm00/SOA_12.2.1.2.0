xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://tempuri.org/";
(:: import schema at "../../WSDL/OnBase.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare variable $EliminarDocumentoOnBaseRequest as element() (:: schema-element(ns1:EliminarDocumentoOnBaseRequest) ::) external;

declare function local:func($EliminarDocumentoOnBaseRequest as element() (:: schema-element(ns1:EliminarDocumentoOnBaseRequest) ::)) as element() (:: schema-element(ns2:deleteDocument) ::) {
    <ns2:deleteDocument>
        <ns2:documentHandle>{fn:data($EliminarDocumentoOnBaseRequest/ns1:idDocumento)}</ns2:documentHandle>
    </ns2:deleteDocument>
};

local:func($EliminarDocumentoOnBaseRequest)
