xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://tempuri.org/";
(:: import schema at "../../WSDL/OnBase.wsdl" ::)
declare namespace ns2="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $updateDataResponse as element() (:: schema-element(ns1:UpdateDataResponse) ::) external;

declare function local:func($updateDataResponse as element() (:: schema-element(ns1:UpdateDataResponse) ::)) as element() (:: schema-element(ns2:ModificarDocumentoOnBaseResponse) ::) {
    <ns2:ModificarDocumentoOnBaseResponse>
   
        {  if (fn:data($updateDataResponse/ns1:UpdateDataResult/ns1:ErrorCode)='1') then
                ( <ns2:Resultado>
            <res:result>OK</res:result>
                    <res:message>{fn:data($updateDataResponse/ns1:UpdateDataResult/ns1:Glosa)}</res:message>
                    <res:error>
                        <err:errorCode>{fn:data($updateDataResponse/ns1:UpdateDataResult/ns1:ErrorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($updateDataResponse/ns1:UpdateDataResult/ns1:Result)}</err:errorDescription>
                
                    </res:error>
      
        </ns2:Resultado>)
                else
                (  <ns2:Resultado>
            <res:result>ERROR</res:result>
                    <res:message>{fn:data($updateDataResponse/ns1:UpdateDataResult/ns1:Glosa)}</res:message>
                    <res:error>
                        <err:errorCode>{fn:data($updateDataResponse/ns1:UpdateDataResult/ns1:ErrorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($updateDataResponse/ns1:UpdateDataResult/ns1:Result)}</err:errorDescription>
                      
                    </res:error>
      
        </ns2:Resultado>)}
    </ns2:ModificarDocumentoOnBaseResponse>
};

local:func($updateDataResponse)
