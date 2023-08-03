xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/QueryEliminaContactoClienteService";
(:: import schema at "../../EliminarContactosCliente/XSD/QueryEliminaContactoClienteService.xsd" ::)

declare variable $EliminarContactoCliente as element() (:: schema-element(ns1:EliminarContactoRequest) ::) external;

declare function local:func($EliminarContactoCliente as element() (:: schema-element(ns1:EliminarContactoRequest) ::)) as element() (:: schema-element(ns2:QueryEliminaContactoClienteServiceInput) ::) {
    <ns2:QueryEliminaContactoClienteServiceInput>
        <ns2:ID>{fn:data($EliminarContactoCliente/ns1:idContacto)}</ns2:ID></ns2:QueryEliminaContactoClienteServiceInput>
};

local:func($EliminarContactoCliente)
