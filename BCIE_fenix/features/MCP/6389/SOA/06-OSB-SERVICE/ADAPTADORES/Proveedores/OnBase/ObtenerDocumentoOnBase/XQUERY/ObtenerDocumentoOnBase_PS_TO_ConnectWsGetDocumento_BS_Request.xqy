xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://tempuri.org/";
(:: import schema at "../../WSDL/WsGetDocumentOnBaseService.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare variable $ObtenerDocumentoOnBaseRequest as element() (:: schema-element(ns1:ObtenerDocumentoOnBaseRequest) ::) external;

declare function local:func($ObtenerDocumentoOnBaseRequest as element() (:: schema-element(ns1:ObtenerDocumentoOnBaseRequest) ::)) as element() (:: schema-element(ns2:Connect) ::) {
    <ns2:Connect/>
};

local:func($ObtenerDocumentoOnBaseRequest)
