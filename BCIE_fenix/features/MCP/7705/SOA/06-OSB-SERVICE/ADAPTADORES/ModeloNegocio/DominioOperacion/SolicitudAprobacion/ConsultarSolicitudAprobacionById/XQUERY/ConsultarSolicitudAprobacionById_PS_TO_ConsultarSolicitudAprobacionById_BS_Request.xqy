xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSolicitudAprobacionById";
(:: import schema at "../XSD/ConsultarSolicitudAprobacionById.xsd" ::)

declare variable $ConsultarSolicitudAprobacionByIdRequest as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionByIdRequest) ::) external;

declare function local:func($ConsultarSolicitudAprobacionByIdRequest as element() (:: schema-element(ns1:ConsultarSolicitudAprobacionByIdRequest) ::)) as element() (:: schema-element(ns2:ConsultarSolicitudAprobacionByIdInput) ::) {
    <ns2:ConsultarSolicitudAprobacionByIdInput>
        <ns2:idSolicitud>{fn:data($ConsultarSolicitudAprobacionByIdRequest/ns1:idSolicitudAprobacion)}</ns2:idSolicitud>
    </ns2:ConsultarSolicitudAprobacionByIdInput>
};

local:func($ConsultarSolicitudAprobacionByIdRequest)
