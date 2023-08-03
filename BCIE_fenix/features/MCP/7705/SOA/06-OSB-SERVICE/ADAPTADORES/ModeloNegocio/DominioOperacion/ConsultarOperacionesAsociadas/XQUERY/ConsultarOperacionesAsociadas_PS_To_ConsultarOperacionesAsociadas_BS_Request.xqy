xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionesAsociadas_DB";
(:: import schema at "../XSD/ConsultarOperacionesAsociadas_DB.xsd" ::)

declare variable $ConsultarOperacionesAsociadasRequest as element() (:: schema-element(ns1:ConsultarOperacionesAsociadasRequest) ::) external;

declare function local:func($ConsultarOperacionesAsociadasRequest as element() (:: schema-element(ns1:ConsultarOperacionesAsociadasRequest) ::)) as element() (:: schema-element(ns2:ConsultarOperacionesAsociadas_DBInput) ::) {
    <ns2:ConsultarOperacionesAsociadas_DBInput>
        <ns2:ID_OPERACION>{fn:data($ConsultarOperacionesAsociadasRequest/ns1:idOperacion)}</ns2:ID_OPERACION>
    </ns2:ConsultarOperacionesAsociadas_DBInput>
};

local:func($ConsultarOperacionesAsociadasRequest)
