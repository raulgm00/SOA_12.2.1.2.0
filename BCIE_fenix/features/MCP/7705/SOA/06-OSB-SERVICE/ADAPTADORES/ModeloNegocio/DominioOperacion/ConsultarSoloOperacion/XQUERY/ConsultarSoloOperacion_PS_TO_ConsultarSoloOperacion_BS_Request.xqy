xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarOperacion";
(:: import schema at "../XSD/ConsultarOperacion_table.xsd" ::)

declare variable $ConsultarSoloOperacionRequest as element() (:: schema-element(ns1:ConsultarSoloOperacionRequest) ::) external;

declare function local:func($ConsultarSoloOperacionRequest as element() (:: schema-element(ns1:ConsultarSoloOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarOperacionSelect_idOperacionInputParameters) ::) {
    <ns2:ConsultarOperacionSelect_idOperacionInputParameters>
        <ns2:idOperacion>{fn:data($ConsultarSoloOperacionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarOperacionSelect_idOperacionInputParameters>
};

local:func($ConsultarSoloOperacionRequest)
