xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DeclaracionJuradaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/DeclaracionJurada/V1/Schema/DeclaracionJuradaMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $mensaje as xs:string external;
declare variable $ResponseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;

declare function local:func($mensaje as xs:string, 
                            $ResponseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::)) 
                            as element() (:: schema-element(ns2:CrearDeclaracionOperacionResponse) ::) {
    <ns2:CrearDeclaracionOperacionResponse>
        <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($mensaje)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($ResponseMapeoErrorMessage/ns1:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($ResponseMapeoErrorMessage/ns1:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($ResponseMapeoErrorMessage/ns1:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:CrearDeclaracionOperacionResponse>
};

local:func($mensaje, $ResponseMapeoErrorMessage)
