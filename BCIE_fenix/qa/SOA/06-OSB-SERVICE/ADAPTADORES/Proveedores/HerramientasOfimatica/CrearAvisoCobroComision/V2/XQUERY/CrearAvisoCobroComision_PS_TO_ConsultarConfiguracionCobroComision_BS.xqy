xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarConfiguracionCobroComision";
(:: import schema at "../XSD/ConsultarConfiguracionCobroComision.xsd" ::)


declare function local:func() as element() (:: schema-element(ns1:ConsultarConfiguracionCobroComisionInput) ::) {
    <ns1:ConsultarConfiguracionCobroComisionInput/>
};

local:func()
