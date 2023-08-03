xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ResultBO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/Common/V1/Schema/ResultBO.xsd" ::)

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:normalizedString external;
declare variable $errorCode as xs:normalizedString external;
declare variable $errorDescription as xs:normalizedString external;
declare variable $errorType as xs:normalizedString external;

declare function local:func($message as xs:normalizedString, 
                            $errorCode as xs:normalizedString, 
                            $errorDescription as xs:normalizedString, 
                            $errorType as xs:normalizedString) 
                            as element() (:: element(ns1:Resultado) ::) {
    <ns1:Resultado>
        <ns1:result>ERROR</ns1:result>
        <ns1:message>{fn:data($message)}</ns1:message>
        <ns1:error>
            <err:errorCode>{fn:data($errorCode)}</err:errorCode>
            <err:errorDescription>{fn:data($errorDescription)}</err:errorDescription>
            <err:errorType>{fn:data($errorType)}</err:errorType>
        </ns1:error>
    </ns1:Resultado>
};

local:func($message, $errorCode, $errorDescription, $errorType)
