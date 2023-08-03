xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ErrorResponse as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;
declare variable $mensaje as xs:string external;

declare function local:func($ErrorResponse as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::), 
                            $mensaje as xs:string) 
                            as element() (:: schema-element(ns2:CrearMontoOperacionResponse) ::) {
    <ns2:CrearMontoOperacionResponse>
        <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($ErrorResponse/ns1:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($ErrorResponse/ns1:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($ErrorResponse/ns1:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:CrearMontoOperacionResponse>
};

local:func($ErrorResponse, $mensaje)