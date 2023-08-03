xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarUsuarioProcesoClienteMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConsultarUsuarioProcesoCliente/V1/Schema/ConsultarUsuarioProcesoClienteMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarUsuarioProcesoCliente_DB";
(:: import schema at "../XSD/ConsultarUsuarioProcesoCliente_DB.xsd" ::)

declare variable $ConsultarUsuarioProcesoClienteRequest as element() (:: schema-element(ns1:ConsultarUsuarioProcesoClienteRequest) ::) external;

declare function local:func($ConsultarUsuarioProcesoClienteRequest as element() (:: schema-element(ns1:ConsultarUsuarioProcesoClienteRequest) ::)) as element() (:: schema-element(ns2:ConsultarUsuarioProcesoCliente_DBInput) ::) {
    <ns2:ConsultarUsuarioProcesoCliente_DBInput>
        <ns2:idFlujoNegocio>{fn:data($ConsultarUsuarioProcesoClienteRequest/ns1:instanciaProceso)}</ns2:idFlujoNegocio>
        <ns2:idOperacion>{fn:data($ConsultarUsuarioProcesoClienteRequest/ns1:idOperacion)}</ns2:idOperacion>
        <ns2:idProceso>{fn:data($ConsultarUsuarioProcesoClienteRequest/ns1:idProceso)}</ns2:idProceso>
    </ns2:ConsultarUsuarioProcesoCliente_DBInput>
};

local:func($ConsultarUsuarioProcesoClienteRequest)
