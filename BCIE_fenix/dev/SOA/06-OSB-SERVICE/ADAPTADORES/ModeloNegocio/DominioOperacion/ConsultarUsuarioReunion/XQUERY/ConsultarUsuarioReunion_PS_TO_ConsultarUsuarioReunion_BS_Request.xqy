xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarUsuarioReunionDB";
(:: import schema at "../XSD/ConsultarUsuarioReunionDB.xsd" ::)

declare variable $ConsultarUsuarioReunionRequest as element() (:: schema-element(ns1:ConsultarUsuarioReunionRequest) ::) external;

declare function local:func($ConsultarUsuarioReunionRequest as element() (:: schema-element(ns1:ConsultarUsuarioReunionRequest) ::)) as element() (:: schema-element(ns2:ConsultarUsuarioReunionDBInput) ::) {
    <ns2:ConsultarUsuarioReunionDBInput>
        <ns2:idOperacion>{fn:data($ConsultarUsuarioReunionRequest/ns1:idOperacion)}</ns2:idOperacion>
    </ns2:ConsultarUsuarioReunionDBInput>
};

local:func($ConsultarUsuarioReunionRequest)
