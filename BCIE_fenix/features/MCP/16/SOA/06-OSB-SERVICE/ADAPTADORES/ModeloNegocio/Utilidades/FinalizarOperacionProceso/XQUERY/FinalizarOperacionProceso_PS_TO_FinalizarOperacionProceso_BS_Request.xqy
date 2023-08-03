xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/FinalizarOperacionProcesoDB";
(:: import schema at "../XSD/FinalizarOperacionProcesoDB.xsd" ::)

declare variable $idOperacionProceso as xs:long external;

declare function local:func($idOperacionProceso as xs:long) as element() (:: schema-element(ns1:FinalizarOperacionProcesoDBInput) ::) {
    <ns1:FinalizarOperacionProcesoDBInput>
        <ns1:ID_OPERACION_PROCESO>{fn:data($idOperacionProceso)}</ns1:ID_OPERACION_PROCESO>
    </ns1:FinalizarOperacionProcesoDBInput>
};

local:func($idOperacionProceso)
