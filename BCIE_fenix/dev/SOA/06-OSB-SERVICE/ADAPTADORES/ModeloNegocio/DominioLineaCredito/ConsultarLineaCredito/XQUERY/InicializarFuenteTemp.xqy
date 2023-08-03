xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarFuente";
(:: import schema at "../../ConsultarFuente/XSD/ConsultarFuente.xsd" ::)

declare variable $Inicial as xs:string external;

declare function local:func($Inicial as xs:string) as element() (:: schema-element(ns1:ConsultarFuenteOutputCollection) ::) {
    <ns1:ConsultarFuenteOutputCollection>
    </ns1:ConsultarFuenteOutputCollection>
};

local:func($Inicial)
