xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarClienteByIdCliente";
(:: import schema at "../XSD/ConsultarClienteByIdCliente.xsd" ::)

declare variable $ClientRequest as element() (:: schema-element(ns1:ConsultarClienteByIdClienteRequest) ::) external;

declare function local:func($ClientRequest as element() (:: schema-element(ns1:ConsultarClienteByIdClienteRequest) ::)) as element() (:: schema-element(ns2:ConsultarClienteByIdClienteInput) ::) {
    <ns2:ConsultarClienteByIdClienteInput>
        <ns2:idCliente>{fn:data($ClientRequest/ns1:idCliente)}</ns2:idCliente>
        <ns2:idFacturador>{fn:data($ClientRequest/ns1:idFacturador)}</ns2:idFacturador>
    </ns2:ConsultarClienteByIdClienteInput>
};

local:func($ClientRequest)