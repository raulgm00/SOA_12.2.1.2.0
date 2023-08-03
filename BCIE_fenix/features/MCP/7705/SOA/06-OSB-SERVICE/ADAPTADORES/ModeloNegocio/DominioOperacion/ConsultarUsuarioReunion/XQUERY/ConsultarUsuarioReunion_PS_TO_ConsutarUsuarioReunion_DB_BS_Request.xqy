xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarUsuarioReunion_DB";
(:: import schema at "../XSD/ConsultarUsuarioReunion_DB.xsd" ::)

declare variable $ConsultarUsuarioReunionRequest as element() (:: schema-element(ns1:ConsultarUsuarioReunionRequest) ::) external;

declare function local:func($ConsultarUsuarioReunionRequest as element() (:: schema-element(ns1:ConsultarUsuarioReunionRequest) ::)) as element() (:: schema-element(ns2:ConsultarUsuarioReunion_DBInput) ::) {
    <ns2:ConsultarUsuarioReunion_DBInput>
        <ns2:idUsuarioReunion>{fn:data($ConsultarUsuarioReunionRequest/ns1:idUsuarioReunion)}</ns2:idUsuarioReunion>
        <ns2:idSolicitudAprobacion>{fn:data($ConsultarUsuarioReunionRequest/ns1:idSolicitudAprobacion)}</ns2:idSolicitudAprobacion>
    </ns2:ConsultarUsuarioReunion_DBInput>
};

local:func($ConsultarUsuarioReunionRequest)
