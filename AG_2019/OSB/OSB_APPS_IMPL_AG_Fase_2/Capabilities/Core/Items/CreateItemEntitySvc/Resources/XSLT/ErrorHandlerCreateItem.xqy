xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://mx.agarcia.ea/Commons/Schemas/AGStandardMessage";
(:: import schema at "../WSDL/CreateItemEntitySvc.wsdl" ::)

declare variable $xErrorReason as xs:string external;
declare variable $xErrorCode as xs:string external;

declare function local:func($xErrorReason as xs:string, 
                            $xErrorCode as xs:string) 
                            as element() (:: schema-element(ns1:messageError) ::) {
    <ns1:messageError>
        <ns1:errorCode>{fn:data($xErrorCode)}</ns1:errorCode>
        <ns1:errorDescription>{fn:data($xErrorReason)}</ns1:errorDescription>
    </ns1:messageError>
};

local:func($xErrorReason, $xErrorCode)