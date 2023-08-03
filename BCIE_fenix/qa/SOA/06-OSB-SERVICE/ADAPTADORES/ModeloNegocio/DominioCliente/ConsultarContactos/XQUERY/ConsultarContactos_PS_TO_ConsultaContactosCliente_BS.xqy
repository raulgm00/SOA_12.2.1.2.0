xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContactos";
(:: import schema at "../XSD/ConsultarContactos.xsd" ::)

declare variable $ConsultarClienteByIdClienteRequest as element() (:: schema-element(ns1:ConsultarClienteByIdClienteRequest) ::) external;

declare function local:func($ConsultarClienteByIdClienteRequest as element() (:: schema-element(ns1:ConsultarClienteByIdClienteRequest) ::)) as element() (:: schema-element(ns2:ConsultarContactosInput) ::) {
    <ns2:ConsultarContactosInput>
        <ns2:idCliente>{fn:data($ConsultarClienteByIdClienteRequest/ns1:idCliente)}</ns2:idCliente>
    </ns2:ConsultarContactosInput>
};

local:func($ConsultarClienteByIdClienteRequest)
