xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/ReglaTareaMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ReglaTarea/V1/Schema/ReglaTareaMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaTareaBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace reg1 = "http://www.bcie.org/ReglaBO";

declare variable $responseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;
declare variable $message as xs:string external;

declare function local:func($responseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::), 
                            $message as xs:string) 
                            as element() (:: schema-element(ns2:ConsultarReglasTareaResponse) ::) {
    <ns2:ConsultarReglasTareaResponse>
        <ns2:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($message)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($responseMapeoErrorMessage/ns1:ErrorOutput/err:errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($responseMapeoErrorMessage/ns1:ErrorOutput/err:errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($responseMapeoErrorMessage/ns1:ErrorOutput/err:errorType)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ConsultarReglasTareaResponse>
};

local:func($responseMapeoErrorMessage, $message)