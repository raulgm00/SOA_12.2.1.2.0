xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarOperacionLogico";
(:: import schema at "../XSD/EliminarOperacionLogicoDB.xsd" ::)

declare variable $EliminarOperacionRequest as element() (:: schema-element(ns1:EliminarOperacionRequest) ::) external;

declare function local:func($EliminarOperacionRequest as element() (:: schema-element(ns1:EliminarOperacionRequest) ::)) as element() (:: schema-element(ns2:EliminarOperacionLogicoInput) ::) {
    <ns2:EliminarOperacionLogicoInput>
        <ns2:ID_OPERACION>{fn:data($EliminarOperacionRequest/ns1:Operacion)}</ns2:ID_OPERACION>
    </ns2:EliminarOperacionLogicoInput>
};

local:func($EliminarOperacionRequest)
