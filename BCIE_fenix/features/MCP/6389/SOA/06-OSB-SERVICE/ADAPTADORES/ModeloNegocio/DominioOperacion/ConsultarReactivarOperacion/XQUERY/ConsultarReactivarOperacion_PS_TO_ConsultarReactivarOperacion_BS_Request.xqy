xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarReactivarOperacion_DB";
(:: import schema at "../XSD/ConsultarReactivarOperacion_DB.xsd" ::)

declare variable $ConsultarReactivarOperacionRequest as element() (:: schema-element(ns1:ConsultarReactivarOperacionRequest) ::) external;

declare function local:func($ConsultarReactivarOperacionRequest as element() (:: schema-element(ns1:ConsultarReactivarOperacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarReactivarOperacion_DBInput) ::) {
    <ns2:ConsultarReactivarOperacion_DBInput>
        <ns2:idOperacion>{fn:data($ConsultarReactivarOperacionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarReactivarOperacion_DBInput>
};

local:func($ConsultarReactivarOperacionRequest)
