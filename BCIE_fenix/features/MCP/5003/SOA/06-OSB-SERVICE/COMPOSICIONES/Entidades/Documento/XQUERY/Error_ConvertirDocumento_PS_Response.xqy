xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DocumentoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:normalizedString external;
declare variable $errorCode as xs:normalizedString external;
declare variable $errorDescription as xs:normalizedString external;
declare variable $errorType as xs:normalizedString external;

declare function local:func($message as xs:normalizedString, 
                            $errorCode as xs:normalizedString, 
                            $errorDescription as xs:normalizedString, 
                            $errorType as xs:normalizedString) as element() (:: schema-element(ns1:ConvertirDocumentoFenixResponse) ::) {
    <ns1:ConvertirDocumentoFenixResponse>
        <ns1:resultado>
            <res:result>ERROR</res:result>
            <res:message>{data($message)}</res:message>
            <res:error>
                <err:errorCode>{data($errorCode)}</err:errorCode>
                <err:errorDescription>{data($errorDescription)}</err:errorDescription>
                <err:errorType>{data($errorType)}</err:errorType>
            </res:error>
        </ns1:resultado>
    </ns1:ConvertirDocumentoFenixResponse>
};

local:func($message, $errorCode, $errorDescription, $errorType)
