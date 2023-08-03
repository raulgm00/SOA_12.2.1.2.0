xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarClienteResponse as element() (:: schema-element(ns1:ConsultarClienteResponse) ::) external;

declare function local:func($ConsultarClienteResponse as element() (:: schema-element(ns1:ConsultarClienteResponse) ::)) as element() (:: schema-element(ns1:CrearClienteDefinitivoRequest) ::) {
    <ns1:CrearClienteDefinitivoRequest>
        <ns1:idCliente>{fn:data($ConsultarClienteResponse/ns1:Cliente/cli:idCliente)}</ns1:idCliente>
    </ns1:CrearClienteDefinitivoRequest>
};

local:func($ConsultarClienteResponse)
