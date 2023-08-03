xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)

declare variable $string as xs:string external;

declare function local:func($string as xs:string) as element() (:: schema-element(ns1:procesoLoteResponse) ::) {
    <ns1:procesoLoteResponse>
    </ns1:procesoLoteResponse>
};

local:func($string)
