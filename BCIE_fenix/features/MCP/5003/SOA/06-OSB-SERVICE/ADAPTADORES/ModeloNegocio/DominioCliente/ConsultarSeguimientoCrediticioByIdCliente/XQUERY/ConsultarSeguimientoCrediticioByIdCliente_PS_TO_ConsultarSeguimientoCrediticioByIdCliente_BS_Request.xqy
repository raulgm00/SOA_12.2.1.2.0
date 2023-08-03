xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSeguimientoCrediticioByIdClienteDB";
(:: import schema at "../XSD/ConsultarSeguimientoCrediticioByIdClienteDB.xsd" ::)

declare variable $ConsultarSeguimientoCrediticioByIdClienteRequest as element() (:: schema-element(ns1:ConsultarSeguimientoCrediticioByIdClienteRequest) ::) external;

declare function local:func($ConsultarSeguimientoCrediticioByIdClienteRequest as element() (:: schema-element(ns1:ConsultarSeguimientoCrediticioByIdClienteRequest) ::)) as element() (:: schema-element(ns2:ConsultarSeguimientoCrediticioByIdClienteDBInput) ::) {
    <ns2:ConsultarSeguimientoCrediticioByIdClienteDBInput>
        <ns2:idCliente>{fn:data($ConsultarSeguimientoCrediticioByIdClienteRequest/ns1:idCliente)}</ns2:idCliente>
    </ns2:ConsultarSeguimientoCrediticioByIdClienteDBInput>
};

local:func($ConsultarSeguimientoCrediticioByIdClienteRequest)
