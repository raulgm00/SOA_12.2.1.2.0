xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $codigoError as xs:string external;

declare function local:func($codigoError as xs:string) as element() (:: schema-element(ns1:requestMapeoErrorMessage) ::) {
    <ns1:requestMapeoErrorMessage>
        <ns1:ErrorInput>
            <err:errorCode>{$codigoError}</err:errorCode>
        </ns1:ErrorInput>
    </ns1:requestMapeoErrorMessage>
};

local:func($codigoError)