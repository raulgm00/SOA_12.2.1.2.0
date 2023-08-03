xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare variable $EliminarClienteRequest as element() (:: schema-element(ns1:EliminarClienteRequest) ::) external;

declare function local:func($EliminarClienteRequest as element() (:: schema-element(ns1:EliminarClienteRequest) ::)) as element() (:: schema-element(ns1:ActualizarClienteRequest) ::) {
    <ns1:ActualizarClienteRequest>
        <ns1:Cliente>
            <cli:idCliente>{fn:data($EliminarClienteRequest/ns1:idCliente)}</cli:idCliente>
            <cli:status>0</cli:status>
        </ns1:Cliente>
    </ns1:ActualizarClienteRequest>
};

local:func($EliminarClienteRequest)
