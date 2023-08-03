xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/consultarReserva";
(:: import schema at "../XSD/consultarReserva_sp.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarClienteREPRequest as element() (:: schema-element(ns1:ConsultarClienteREPRequest) ::) external;

declare function local:func($ConsultarClienteREPRequest as element() (:: schema-element(ns1:ConsultarClienteREPRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:CODIGO_CLIENTE>{fn:data($ConsultarClienteREPRequest/cli:idFacturador)}</ns2:CODIGO_CLIENTE>
        <ns2:FONDOS/>
    </ns2:InputParameters>
};

local:func($ConsultarClienteREPRequest)