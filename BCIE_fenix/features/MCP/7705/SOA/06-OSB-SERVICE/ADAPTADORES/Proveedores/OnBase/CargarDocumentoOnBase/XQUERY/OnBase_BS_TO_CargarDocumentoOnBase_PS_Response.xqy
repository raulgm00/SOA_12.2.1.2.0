xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://tempuri.org/";
(:: import schema at "../../WSDL/OnBase.wsdl" ::)
declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $InsertResponse as element() (:: schema-element(ns1:InsertResponse) ::) external;

declare function local:func($InsertResponse as element() (:: schema-element(ns1:InsertResponse) ::)) as element() (:: schema-element(ns2:CargarDocumentoOnBaseResponse) ::) {
    <ns2:CargarDocumentoOnBaseResponse>
     {  if (fn:data($InsertResponse/ns1:InsertResult/ns1:ErrorCode)='1') then
                ( <ns2:Resultado>
            <res:result>OK</res:result>
                    <res:message>{fn:data($InsertResponse/ns1:InsertResult/ns1:Result)}</res:message>
                    <res:error>
                        <err:errorCode>{fn:data($InsertResponse/ns1:InsertResult/ns1:ErrorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($InsertResponse/ns1:InsertResult/ns1:Glosa)}</err:errorDescription>
                        <err:errorType>{fn:data($InsertResponse/ns1:InsertResult/ns1:Result)}</err:errorType>
                    </res:error>
      
        </ns2:Resultado>)
                else
                (  <ns2:Resultado>
            <res:result>ERROR</res:result>
                    <res:message>{fn:data($InsertResponse/ns1:InsertResult/ns1:Result)}</res:message>
                    <res:error>
                        <err:errorCode>{fn:data($InsertResponse/ns1:InsertResult/ns1:ErrorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($InsertResponse/ns1:InsertResult/ns1:Glosa)}</err:errorDescription>
                        <err:errorType>{fn:data($InsertResponse/ns1:InsertResult/ns1:Result)}</err:errorType>
                    </res:error>
      
        </ns2:Resultado>)}
            
    </ns2:CargarDocumentoOnBaseResponse>
};

local:func($InsertResponse)