xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $ConsultarClienteResponse as element() (:: schema-element(ns1:ConsultarClienteResponse) ::) external;

declare function local:func($ConsultarClienteResponse as element() (:: schema-element(ns1:ConsultarClienteResponse) ::)) 
                            as element() (:: schema-element(ns1:ConsultarListaClientesResponse) ::) {
    <ns1:ConsultarListaClientesResponse>
    </ns1:ConsultarListaClientesResponse>
};

local:func($ConsultarClienteResponse)
