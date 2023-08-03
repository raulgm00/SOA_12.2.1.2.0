xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns1="http://www.bcie.org/MapeoErrorMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/MapeoError/V1/Schema/MapeoErrorMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:normalizedString external;
declare variable $errorCode as xs:normalizedString external;
declare variable $errorDescription as xs:normalizedString external;
declare variable $errorType as xs:normalizedString external;
declare variable $responseMapeoErrorMessage as element() (:: schema-element(ns1:responseMapeoErrorMessage) ::) external;

declare function local:func($message as xs:normalizedString, 
                            $errorCode as xs:normalizedString, 
                            $errorDescription as xs:normalizedString, 
                            $errorType as xs:normalizedString)
                            as element() (:: schema-element(ns2:ConsultarContratoComisionFLEXCUBEResponse) ::) {
    <ns2:ConsultarContratoComisionFLEXCUBEResponse>
        <ns2:Resultado>
             <res:result>ERROR</res:result>
            <res:message>{fn:data($message)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($errorDescription)}</err:errorDescription>
                <err:errorType>{fn:data($errorType)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ConsultarContratoComisionFLEXCUBEResponse>
};

local:func($message, $errorCode, $errorDescription, $errorType)