xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionesByIdCliente";
(:: import schema at "../XSD/ConsultarOperacionesByIdCliente.xsd" ::)

declare variable $ConsultarOperacionesByIdClienteRequest as element() (:: schema-element(ns1:ConsultarOperacionesByIdClienteRequest) ::) external;

declare function local:func($ConsultarOperacionesByIdClienteRequest as element() (:: schema-element(ns1:ConsultarOperacionesByIdClienteRequest) ::)) as element() (:: schema-element(ns2:ConsultarOperacionesByIdClienteInput) ::) {
    <ns2:ConsultarOperacionesByIdClienteInput>
        <ns2:idCliente>{fn:data($ConsultarOperacionesByIdClienteRequest/ns1:idCliente)}</ns2:idCliente>
    </ns2:ConsultarOperacionesByIdClienteInput>
};

local:func($ConsultarOperacionesByIdClienteRequest)
