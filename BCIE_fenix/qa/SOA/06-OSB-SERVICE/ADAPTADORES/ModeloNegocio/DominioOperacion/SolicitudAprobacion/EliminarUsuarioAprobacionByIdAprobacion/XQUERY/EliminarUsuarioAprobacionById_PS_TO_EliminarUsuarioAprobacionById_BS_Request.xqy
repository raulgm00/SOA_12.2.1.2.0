xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarUsuarioAprobacionByIdAprobacion_DB";
(:: import schema at "../XSD/EliminarUsuarioAprobacionByIdAprobacion_DB.xsd" ::)

declare variable $EliminarUsuarioAprobacionByIdRequest as element() (:: schema-element(ns1:EliminarUsuarioAprobacionByIdSolicitudRequest) ::) external;

declare function local:func($EliminarUsuarioAprobacionByIdRequest as element() (:: schema-element(ns1:EliminarUsuarioAprobacionByIdSolicitudRequest) ::)) as element() (:: schema-element(ns2:EliminarUsuarioAprobacionByIdAprobacion_DBInput) ::) {
    <ns2:EliminarUsuarioAprobacionByIdAprobacion_DBInput>
        <ns2:ID_SOLICITUD_APROBACION>{fn:data($EliminarUsuarioAprobacionByIdRequest/ns1:idSolicitud)}</ns2:ID_SOLICITUD_APROBACION>
    </ns2:EliminarUsuarioAprobacionByIdAprobacion_DBInput>
};

local:func($EliminarUsuarioAprobacionByIdRequest)
