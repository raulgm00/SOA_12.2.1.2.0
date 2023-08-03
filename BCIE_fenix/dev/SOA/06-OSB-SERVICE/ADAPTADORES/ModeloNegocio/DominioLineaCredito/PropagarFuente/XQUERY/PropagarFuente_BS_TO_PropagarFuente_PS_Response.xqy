xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at 
                     "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:string external;
declare variable $PropagarFuenteResponse as element() (:: schema-element(ns1:PropagarFuenteResponse) ::) external;

declare function local:func($message as xs:string, $PropagarFuenteResponse as element() (:: schema-element(ns1:PropagarFuenteResponse) ::)) as element() (:: schema-element(ns1:PropagarFuenteLCResponse) ::) {
    <ns1:PropagarFuenteLCResponse>
        <ns1:Resultado>
            <res:result>{fn:data($PropagarFuenteResponse/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($message)}</res:message>
            {
                if ($PropagarFuenteResponse/ns1:Resultado/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($PropagarFuenteResponse/ns1:Resultado/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($PropagarFuenteResponse/ns1:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($PropagarFuenteResponse/ns1:Resultado/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </ns1:Resultado>
    </ns1:PropagarFuenteLCResponse>
};

local:func($message,$PropagarFuenteResponse)
