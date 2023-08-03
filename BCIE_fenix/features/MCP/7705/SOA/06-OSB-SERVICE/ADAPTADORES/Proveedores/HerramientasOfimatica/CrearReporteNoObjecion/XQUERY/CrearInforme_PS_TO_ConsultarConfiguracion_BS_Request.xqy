xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarConfiguracion";
(:: import schema at "../../../../ModeloNegocio/Utilidades/ConsultarConfiguracion/XSD/ConsultarConfiguracion.xsd" ::)

declare variable $llaveConsultar as xs:string external;

declare function local:func($llaveConsultar as xs:string) as element() (:: schema-element(ns1:ConsultarConfiguracionInput) ::) {
    <ns1:ConsultarConfiguracionInput>
        <ns1:llave>{fn:data($llaveConsultar)}</ns1:llave>
    </ns1:ConsultarConfiguracionInput>
};

local:func($llaveConsultar)