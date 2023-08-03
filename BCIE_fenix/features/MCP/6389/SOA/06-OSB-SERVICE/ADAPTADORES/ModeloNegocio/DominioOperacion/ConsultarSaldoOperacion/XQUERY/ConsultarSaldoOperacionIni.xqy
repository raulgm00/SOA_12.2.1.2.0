xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)

declare variable $string as xs:string external;

declare function local:func($string as xs:string) as element() (:: schema-element(ns1:ConsultarSaldoOperacionResponse) ::) {
    <ns1:ConsultarSaldoOperacionResponse></ns1:ConsultarSaldoOperacionResponse>
};

local:func($string)
