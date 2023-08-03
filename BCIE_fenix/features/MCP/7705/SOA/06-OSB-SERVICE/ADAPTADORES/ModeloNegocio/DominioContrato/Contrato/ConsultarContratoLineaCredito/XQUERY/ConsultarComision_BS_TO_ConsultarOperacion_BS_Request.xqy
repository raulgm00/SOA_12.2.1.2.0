xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionByIdOperacion";
(:: import schema at "../../../../DominioOperacion/ConsultarOperacionByIDOperacion/XSD/ConsultarOperacionByIdOperacion.xsd" ::)

declare variable $consultarOperacionReq as xs:string external;

declare function local:func($consultarOperacionReq as xs:string) as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionInput) ::) {
    <ns1:ConsultarOperacionByIdOperacionInput>
        <ns1:idOperacion>{fn:data($consultarOperacionReq)}</ns1:idOperacion>
    </ns1:ConsultarOperacionByIdOperacionInput>
};

local:func($consultarOperacionReq)
