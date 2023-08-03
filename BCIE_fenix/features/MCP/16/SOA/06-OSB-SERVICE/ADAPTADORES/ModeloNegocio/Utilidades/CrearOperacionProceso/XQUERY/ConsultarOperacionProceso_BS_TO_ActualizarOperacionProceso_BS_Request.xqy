xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ActualizarOperacionProceso_DB";
(:: import schema at "../XSD/ActualizarOperacionProceso_DB.xsd" ::)

declare variable $idOperacionProceso as xs:long external;

declare function local:func($idOperacionProceso as xs:long) as element() (:: schema-element(ns1:ActualizarOperacionProceso_DBInput) ::) {
    <ns1:ActualizarOperacionProceso_DBInput>
        <ns1:ID_OPERACION_PROCESO>{fn:data($idOperacionProceso)}</ns1:ID_OPERACION_PROCESO>
    </ns1:ActualizarOperacionProceso_DBInput>
};

local:func($idOperacionProceso)
