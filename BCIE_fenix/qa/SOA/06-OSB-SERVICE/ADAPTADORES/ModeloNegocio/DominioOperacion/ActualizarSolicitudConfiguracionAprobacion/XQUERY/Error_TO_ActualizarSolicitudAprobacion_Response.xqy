xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ErrorReponse as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;

declare variable $message as xs:string external;

declare function local:func($ErrorReponse as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::),
                              $message as xs:string) as element() (:: schema-element(ns2:ActualizarConfiguracionSolicitudAprobacionResponse) ::) {
    <ns2:ActualizarConfiguracionSolicitudAprobacionResponse>
        <ns2:Resultado>
            <res:result>{fn:data($ErrorReponse/ns1:Result/res:result)}</res:result>
            <res:message>{fn:data($message)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($ErrorReponse/ns1:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($ErrorReponse/ns1:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($ErrorReponse/ns1:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ActualizarConfiguracionSolicitudAprobacionResponse>
};

local:func($ErrorReponse, $message)
