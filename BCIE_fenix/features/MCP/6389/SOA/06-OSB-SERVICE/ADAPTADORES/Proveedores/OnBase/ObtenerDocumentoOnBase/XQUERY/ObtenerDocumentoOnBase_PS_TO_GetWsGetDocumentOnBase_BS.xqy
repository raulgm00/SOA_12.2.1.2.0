xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://tempuri.org/";
(:: import schema at "../../WSDL/WsGetDocumentOnBaseService.wsdl" ::)
declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare variable $ObtenerDocumentoOnBaseRequest as element() (:: schema-element(ns1:ObtenerDocumentoOnBaseRequest) ::) external;
declare variable $ConnectResponse as element() (:: schema-element(ns2:ConnectResponse) ::) external;

declare function local:func($ObtenerDocumentoOnBaseRequest as element() (:: schema-element(ns1:ObtenerDocumentoOnBaseRequest) ::), 
                            $ConnectResponse as element() (:: schema-element(ns2:ConnectResponse) ::)) 
                            as element() (:: schema-element(ns2:GetDocument) ::) {
    <ns2:GetDocument>
        <ns2:parameters>
            <ns2:DocumentHandler>{fn:data($ObtenerDocumentoOnBaseRequest/ns1:idExterno)}</ns2:DocumentHandler>
            {
                if ($ConnectResponse/ns2:ConnectResult/ns2:Result)
                then <ns2:SessionId>{fn:data($ConnectResponse/ns2:ConnectResult/ns2:Result)}</ns2:SessionId>
                else ()
            }
            <ns2:DataTypeReturn>application/pdf</ns2:DataTypeReturn>
        </ns2:parameters>
    </ns2:GetDocument>
};

local:func($ObtenerDocumentoOnBaseRequest, $ConnectResponse)
