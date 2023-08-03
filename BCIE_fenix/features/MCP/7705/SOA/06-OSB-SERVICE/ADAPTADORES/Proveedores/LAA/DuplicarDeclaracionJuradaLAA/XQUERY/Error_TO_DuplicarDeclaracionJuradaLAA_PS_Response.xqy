xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $responseMapeoErrorMessage as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::) external;
declare variable $mensaje as xs:string external;

declare function local:func($responseMapeoErrorMessage as element() (:: schema-element(ns2:responseMapeoErrorMessage) ::), 
                            $mensaje as xs:string) 
                            as element() (:: schema-element(ns1:DuplicarDeclaracionJuradaResponse) ::) {
    <ns1:DuplicarDeclaracionJuradaResponse>
        <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($responseMapeoErrorMessage/ns2:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:DuplicarDeclaracionJuradaResponse>
};

local:func($responseMapeoErrorMessage, $mensaje)
