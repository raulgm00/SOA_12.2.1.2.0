xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CrearActualizarBitacoraDocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarBitacoraDocumento/V1/Schema/CrearActualizarBitacoraDocumentoMO.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $message as xs:normalizedString external;
declare variable $errorCode as xs:normalizedString external;
declare variable $errorDescripcion as xs:normalizedString external;
declare variable $errorType as xs:normalizedString external;

declare function local:func($message as xs:normalizedString, 
                            $errorCode as xs:normalizedString, 
                            $errorDescripcion as xs:normalizedString, 
                            $errorType as xs:normalizedString) 
                            as element() (:: schema-element(ns1:responseCrearActualizarBitacoraDocumentoMessage) ::) {
    <ns1:responseCrearActualizarBitacoraDocumentoMessage>
        <ns1:Resultado>
            <res:result>ERROR</res:result>
            <res:message>{fn:data($message)}</res:message>
            <res:error>
                <err:errorCode>{fn:data($errorCode)}</err:errorCode>
                <err:errorDescription>{fn:data($errorDescripcion)}</err:errorDescription>
                <err:errorType>{fn:data($errorType)}</err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:responseCrearActualizarBitacoraDocumentoMessage>
};
local:func($message, $errorCode, $errorDescripcion, $errorType)