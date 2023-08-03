xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioCliente/Cliente/V1/Schema/ClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/EliminarContactoService";
(:: import schema at "../XSD/EliminarContactoService_table.xsd" ::)

declare variable $EliminarContactorequest as element() (:: schema-element(ns1:EliminarContactoRequest) ::) external;

declare function local:func($EliminarContactorequest as element() (:: schema-element(ns1:EliminarContactoRequest) ::)) as element() (:: schema-element(ns2:ContactosCollection) ::) {
    <ns2:ContactosCollection>
        <ns2:Contactos>
            <ns2:idContacto>{fn:data($EliminarContactorequest/ns1:idContacto)}</ns2:idContacto></ns2:Contactos></ns2:ContactosCollection>
};

local:func($EliminarContactorequest)
