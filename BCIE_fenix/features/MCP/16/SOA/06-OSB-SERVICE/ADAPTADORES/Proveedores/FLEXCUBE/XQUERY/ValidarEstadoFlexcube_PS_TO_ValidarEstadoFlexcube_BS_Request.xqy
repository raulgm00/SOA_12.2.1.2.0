xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ValidarEstadoFlexcube_DB";
(:: import schema at "../XSD/ValidarEstadoFlexcube_DB_table.xsd" ::)

declare variable $ns1:ValidarEstadoFlexcube_DBSelectInputParameters as element() (:: schema-element(ns1:ValidarEstadoFlexcube_DBSelectInputParameters) ::) external;

declare function local:func($ns1:ValidarEstadoFlexcube_DBSelectInputParameters as element() (:: schema-element(ns1:ValidarEstadoFlexcube_DBSelectInputParameters) ::)) as element() (:: schema-element(ns1:ValidarEstadoFlexcube_DBSelectInputParameters) ::) {
    <ns1:ValidarEstadoFlexcube_DBSelectInputParameters></ns1:ValidarEstadoFlexcube_DBSelectInputParameters>
};

local:func($ns1:ValidarEstadoFlexcube_DBSelectInputParameters)
