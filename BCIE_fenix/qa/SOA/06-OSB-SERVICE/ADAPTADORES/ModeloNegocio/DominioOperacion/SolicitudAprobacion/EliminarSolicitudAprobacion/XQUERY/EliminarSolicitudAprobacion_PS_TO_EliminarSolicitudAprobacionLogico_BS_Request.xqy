xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/EliminarSolicitudAprobacionLogico_BS";
(:: import schema at "../XSD/EliminarSolicitudAprobacionLogico_BS.xsd" ::)

declare variable $EliminarSolicitudAprobacionRequest as element() (:: schema-element(ns1:EliminarSolicitudAprobacionRequest) ::) external;

declare function local:func($EliminarSolicitudAprobacionRequest as element() (:: schema-element(ns1:EliminarSolicitudAprobacionRequest) ::)) as element() (:: schema-element(ns2:EliminarSolicitudAprobacionLogico_BSInput) ::) {
    <ns2:EliminarSolicitudAprobacionLogico_BSInput>
        <ns2:ID_SOLICITUD_APROBACION>{fn:data($EliminarSolicitudAprobacionRequest/ns1:idSolicitud)}</ns2:ID_SOLICITUD_APROBACION>
    </ns2:EliminarSolicitudAprobacionLogico_BSInput>
};

local:func($EliminarSolicitudAprobacionRequest)
