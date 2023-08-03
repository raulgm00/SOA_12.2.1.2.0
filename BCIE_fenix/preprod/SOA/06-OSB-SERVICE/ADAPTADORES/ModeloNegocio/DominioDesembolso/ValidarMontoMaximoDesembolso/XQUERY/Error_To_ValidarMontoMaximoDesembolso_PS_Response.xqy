xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $responseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;
declare variable $message as xs:string external;

declare function ns1:responseMapeoErrorMessage($responseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::)) as element() (:: schema-element(ns2:ValidarMontoMaximoDesembolsoResponse) ::) {
    <ns2:ValidarMontoMaximoDesembolsoResponse>
        <ns2:Resultado>
            <res:result></res:result>
            <res:message></res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
                <err:errorDescription>{fn:data($responseMapeoErrorMessage/ns1:Result/res:error/err:errorDescription)}</err:errorDescription>
            </res:error>
            <err:errorType>{fn:data($responseMapeoErrorMessage/ns1:Result/res:error/err:errorType)}</err:errorType>
        </ns2:Resultado>
        <err:errorCode>{fn:data($responseMapeoErrorMessage/ns1:Result/res:error/err:errorCode)}</err:errorCode>
    </ns2:ValidarMontoMaximoDesembolsoResponse>
};

ns1:responseMapeoErrorMessage($responseMapeoErrorMessage)