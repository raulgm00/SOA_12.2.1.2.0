xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ConsultarOperacion";
(:: import schema at "../XSD/ConsultarOperacionBO_JSON.xsd" ::)
declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $responseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;
declare variable $message as xs:string external;

declare function local:func($responseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::), 
                            $message as xs:string) 
                            as element() (:: schema-element(ns2:ConsultarOperacionResponse) ::) {
    <ns2:ConsultarOperacionResponse>
        <ns2:Result>
            <ns2:result>ERROR</ns2:result>
            <ns2:message>{fn:data($message)}</ns2:message>
            <ns2:error>
                <ns2:errorCode>{fn:data($responseMapeoErrorMessage/ns1:ErrorOutput/err:errorCode)}</ns2:errorCode>
                <ns2:errorDescription>{fn:data($responseMapeoErrorMessage/ns1:ErrorOutput/err:errorDescription)}</ns2:errorDescription>
                <ns2:errorType>{fn:data($responseMapeoErrorMessage/ns1:ErrorOutput/err:errorType)}</ns2:errorType>
            </ns2:error>
        </ns2:Result>
    </ns2:ConsultarOperacionResponse>
};

local:func($responseMapeoErrorMessage, $message)
