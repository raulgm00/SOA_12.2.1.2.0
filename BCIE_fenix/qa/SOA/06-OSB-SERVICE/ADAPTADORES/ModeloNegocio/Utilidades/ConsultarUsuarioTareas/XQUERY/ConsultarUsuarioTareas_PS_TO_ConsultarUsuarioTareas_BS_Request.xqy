xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bicie.org/ConsultarUsuarioTareasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarUsuarioTareas/V1/Schema/ConsultarUsuarioTareasMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarUsuarioTareasDB";
(:: import schema at "../XSD/ConsultarUsuarioTareasDB.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarUsuarioTareasBO";

declare variable $ConsultarUsuarioTareasRequest as element() (:: schema-element(ns1:ConsultarUsuarioTareasRequest) ::) external;

declare function local:func($ConsultarUsuarioTareasRequest as element() (:: schema-element(ns1:ConsultarUsuarioTareasRequest) ::)) as element() (:: schema-element(ns2:ConsultarUsuarioTareasDBInput) ::) {
    <ns2:ConsultarUsuarioTareasDBInput>
        <ns2:idProceso>{fn:data($ConsultarUsuarioTareasRequest/ns1:idProceso)}</ns2:idProceso>
        <ns2:idCliente>{fn:data($ConsultarUsuarioTareasRequest/ns1:idCliente)}</ns2:idCliente>
    </ns2:ConsultarUsuarioTareasDBInput>
};

local:func($ConsultarUsuarioTareasRequest)
