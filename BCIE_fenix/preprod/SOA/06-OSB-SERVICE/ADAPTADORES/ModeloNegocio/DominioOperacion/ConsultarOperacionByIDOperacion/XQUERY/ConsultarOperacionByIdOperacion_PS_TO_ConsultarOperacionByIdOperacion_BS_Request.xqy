xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionByIdOperacion";
(:: import schema at "../XSD/ConsultarOperacionByIdOperacion.xsd" ::)

declare variable $ClienteRequest as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::) external;

declare function local:func($ClienteRequest as element() (:: schema-element(ns1:ConsultarOperacionByIdOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarOperacionByIdOperacionInput) ::) {
    <ns2:ConsultarOperacionByIdOperacionInput>
        <ns2:idOperacion>{fn:data($ClienteRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarOperacionByIdOperacionInput>
};

local:func($ClienteRequest)
