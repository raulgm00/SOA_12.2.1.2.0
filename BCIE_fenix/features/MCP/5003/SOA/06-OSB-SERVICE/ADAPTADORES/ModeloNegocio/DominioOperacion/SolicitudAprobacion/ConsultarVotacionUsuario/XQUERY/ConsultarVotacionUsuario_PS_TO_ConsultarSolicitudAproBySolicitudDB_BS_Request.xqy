xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarSolicitudAproByIdSolicitud";
(:: import schema at "../XSD/ConsultarSolicitudAproByIdSolicitud.xsd" ::)

declare variable $ConsultarVotacionUsuarioRequest as element() (:: schema-element(ns1:ConsultarVotacionUsuarioRequest) ::) external;

declare function local:func($ConsultarVotacionUsuarioRequest as element() (:: schema-element(ns1:ConsultarVotacionUsuarioRequest) ::)) as element() (:: schema-element(ns2:ConsultarSolicitudAproByIdSolicitudInput) ::) {
    <ns2:ConsultarSolicitudAproByIdSolicitudInput>
        <ns2:idSolicitudAprobacion>{fn:data($ConsultarVotacionUsuarioRequest/ns1:idSolicitudAprobacion)}</ns2:idSolicitudAprobacion>
    </ns2:ConsultarSolicitudAproByIdSolicitudInput>
};

local:func($ConsultarVotacionUsuarioRequest)
