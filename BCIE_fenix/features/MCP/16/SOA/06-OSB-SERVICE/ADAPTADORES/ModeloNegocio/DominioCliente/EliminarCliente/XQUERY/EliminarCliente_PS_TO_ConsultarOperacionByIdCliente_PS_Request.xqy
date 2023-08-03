xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)

declare variable $idCliente as element() (:: schema-element(ns1:EliminarClienteRequest) ::) external;

declare function local:func($idCliente as element() (:: schema-element(ns1:EliminarClienteRequest) ::)) as element() (:: schema-element(ns1:ConsultarOperacionesByIdClienteRequest) ::) {
    <ns1:ConsultarOperacionesByIdClienteRequest>
        {
            if ($idCliente/ns1:idCliente)
            then <ns1:idCliente>{fn:data($idCliente/ns1:idCliente)}</ns1:idCliente>
            else ()
        }
    </ns1:ConsultarOperacionesByIdClienteRequest>
};

local:func($idCliente)
