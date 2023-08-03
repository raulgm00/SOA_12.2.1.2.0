xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarReactivarOperacion_DB";
(:: import schema at "../XSD/ActualizarReactivarOperacion_DB.xsd" ::)

declare variable $ActualizarReactivarOperacionRequest as element() (:: schema-element(ns1:ActualizarReactivarOperacionRequest) ::) external;

declare function local:func($ActualizarReactivarOperacionRequest as element() (:: schema-element(ns1:ActualizarReactivarOperacionRequest) ::)) as element() (:: schema-element(ns2:ActualizarReactivarOperacion_DBInput) ::) {
    <ns2:ActualizarReactivarOperacion_DBInput>
        <ns2:idOperacion>{fn:data($ActualizarReactivarOperacionRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idProceso>{fn:data($ActualizarReactivarOperacionRequest/ns1:idProceso)}</ns2:idProceso>
    </ns2:ActualizarReactivarOperacion_DBInput>
};

local:func($ActualizarReactivarOperacionRequest)
