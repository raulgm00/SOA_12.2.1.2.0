xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://tempuri.org/";
(:: import schema at "../../WSDL/WsGetDocumentOnBaseService.wsdl" ::)
declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $GetDocumentResponse as element() (:: schema-element(ns1:GetDocumentResponse) ::) external;

declare function local:func($GetDocumentResponse as element() (:: schema-element(ns1:GetDocumentResponse) ::)) as element() (:: schema-element(ns2:ObtenerDocumentoOnBaseResponse) ::) {
    <ns2:ObtenerDocumentoOnBaseResponse>
        <ns2:codigo>{fn:data($GetDocumentResponse/ns1:GetDocumentResult/ns1:Result)}</ns2:codigo>
        <ns2:resultado>
            <res:result>OK</res:result>
            <res:message>Operacion Exitosa</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:resultado>
    </ns2:ObtenerDocumentoOnBaseResponse>
};

local:func($GetDocumentResponse)
