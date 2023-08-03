xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $MaperoError as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;

declare function local:func($MaperoError as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::)) as element() (:: schema-element(ns2:CrearActualizarLoteImplementacionResponse) ::) {
    <ns2:CrearActualizarLoteImplementacionResponse>
        <ns2:Resultado>
            <res:error>
                <err:errorCode>{fn:data($MaperoError/ns1:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($MaperoError/ns1:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($MaperoError/ns1:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:CrearActualizarLoteImplementacionResponse>
};

local:func($MaperoError)