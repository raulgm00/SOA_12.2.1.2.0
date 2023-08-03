xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://mx.agarcia.ea/Applications/Prototipos/CreateSKU";
(:: import schema at "CreateSKURestPXY.wsdl" ::)

declare variable $jsonResponse as text() external;

declare function local:func($jsonResponse as text()) as element() (:: schema-element(ns1:createSKUResponse) ::) {
    <ns1:createSKUResponse>
        <ns1:code>{fn:substring-before(fn:substring-after($jsonResponse,'"code":'),'}')}</ns1:code>
        <ns1:msg>{fn:substring-before(fn:substring-after($jsonResponse,'"msg":"'),'",')}</ns1:msg>
    </ns1:createSKUResponse>
};

local:func($jsonResponse)