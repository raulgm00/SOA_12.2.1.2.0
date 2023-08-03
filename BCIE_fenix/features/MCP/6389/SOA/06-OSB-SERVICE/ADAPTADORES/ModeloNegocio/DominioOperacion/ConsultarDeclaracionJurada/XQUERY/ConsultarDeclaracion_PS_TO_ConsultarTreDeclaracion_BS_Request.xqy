xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTreDeclaracionOperacion_DB";
(:: import schema at "../XSD/ConsultarTreDeclaracionOperacion_DB.xsd" ::)

declare variable $idOperacion as xs:long external;

declare function local:func($idOperacion as xs:long) as element() (:: schema-element(ns1:ConsultarTreDeclaracionOperacion_DBInput) ::) {
    <ns1:ConsultarTreDeclaracionOperacion_DBInput>
        <ns1:idOperacion>{fn:data($idOperacion)}</ns1:idOperacion>
    </ns1:ConsultarTreDeclaracionOperacion_DBInput>
};

local:func($idOperacion)
