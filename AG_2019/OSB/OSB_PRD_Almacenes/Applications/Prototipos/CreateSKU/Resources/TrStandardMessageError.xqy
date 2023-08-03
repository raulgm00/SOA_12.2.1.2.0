xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://mx.agarcia.ea/Commons/Schemas/AGStandardMessage";
(:: import schema at "AGStandardMessageSchema.xsd" ::)

declare variable $errorCode as xs:string external;
declare variable $errorDescription as xs:string external;
declare variable $errorType as xs:string external;
declare variable $severity as xs:string external;

declare function local:func($errorCode as xs:string, 
                            $errorDescription as xs:string, 
                            $errorType as xs:string, 
                            $severity as xs:string) 
                            as element() (:: schema-element(ns1:messageError) ::) {
    <ns1:messageError>
        <ns1:errorCode>{fn:data($errorCode)}</ns1:errorCode>
        <ns1:errorDescription>{fn:data($errorDescription)}</ns1:errorDescription>
        <ns1:errorType>{fn:data($errorType)}</ns1:errorType>
        <ns1:severity>{fn:data($severity)}</ns1:severity>
    </ns1:messageError>
};

local:func($errorCode, $errorDescription, $errorType, $severity)