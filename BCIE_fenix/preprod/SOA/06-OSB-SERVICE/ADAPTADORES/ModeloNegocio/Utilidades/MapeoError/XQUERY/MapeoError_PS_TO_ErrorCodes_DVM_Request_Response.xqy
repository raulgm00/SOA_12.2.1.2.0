xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $requestMapeoErrorMessage as element() (:: schema-element(ns1:requestMapeoErrorMessage) ::) external;

declare function local:func($requestMapeoErrorMessage as element() (:: schema-element(ns1:requestMapeoErrorMessage) ::)) as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) {
    <ns1:responseMapeoErrorMessage>
        <ns1:ErrorOutput>
            <err:errorCode>{dvmtr:lookup('MDS/Resources/ComponentesComunes/Common/V1/DVM/ErrorCodes', 'Codigo', $requestMapeoErrorMessage/ns1:ErrorInput/err:errorCode,'CodigoFenix', xs:string(fn:data($requestMapeoErrorMessage/ns1:ErrorInput/err:errorCode)))}</err:errorCode>
            <err:errorDescription>{dvmtr:lookup('MDS/Resources/ComponentesComunes/Common/V1/DVM/ErrorCodes', 'Codigo', $requestMapeoErrorMessage/ns1:ErrorInput/err:errorCode,'Descripcion', '')}</err:errorDescription>
            <err:errorType>{dvmtr:lookup('MDS/Resources/ComponentesComunes/Common/V1/DVM/ErrorCodes', 'Codigo', $requestMapeoErrorMessage/ns1:ErrorInput/err:errorCode,'Tipo', '')}</err:errorType>
        </ns1:ErrorOutput>
    </ns1:responseMapeoErrorMessage>
};

local:func($requestMapeoErrorMessage)