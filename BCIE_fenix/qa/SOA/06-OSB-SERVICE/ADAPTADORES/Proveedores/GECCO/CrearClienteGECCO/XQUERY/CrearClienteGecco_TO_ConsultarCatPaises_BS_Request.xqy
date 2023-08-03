xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCodExternoPais";
(:: import schema at "../XSD/ConsultarCodExternoPais.xsd" ::)

declare variable $idPais as element() external;

declare function local:func($idPais as element()) as element() (:: schema-element(ns1:ConsultarCodExternoPaisInput) ::) {
    <ns1:ConsultarCodExternoPaisInput>
        <ns1:ID>{fn:data($idPais)}</ns1:ID>
    </ns1:ConsultarCodExternoPaisInput>
};

local:func($idPais)
