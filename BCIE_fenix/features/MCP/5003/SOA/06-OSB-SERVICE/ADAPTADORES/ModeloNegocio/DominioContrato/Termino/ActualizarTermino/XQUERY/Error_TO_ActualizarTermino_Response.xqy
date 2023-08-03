xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/TerminoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Termino/V1/Schema/TerminoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ErrorResponse as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;
declare variable $message as xs:string external;

declare function local:func($ErrorResponse as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::), 
                            $message as xs:string) 
                            as element() (:: schema-element(ns2:ActualizarTerminoResponse) ::) {
    <ns2:ActualizarTerminoResponse>
        <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($message)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($ErrorResponse/ns1:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($ErrorResponse/ns1:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($ErrorResponse/ns1:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ActualizarTerminoResponse>
};

local:func($ErrorResponse, $message)
