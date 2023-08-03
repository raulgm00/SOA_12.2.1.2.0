xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarVotacionesMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarVotaciones/V1/Schema/ConsultarVotacionesMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare variable $RequestConsultar as element() (:: schema-element(ns1:ConsultarVotacionesRequest) ::) external;

declare function local:func($RequestConsultar as element() (:: schema-element(ns1:ConsultarVotacionesRequest) ::)) as element() (:: schema-element(ns2:ConsultarVotacionesRequest) ::) {
    <ns2:ConsultarVotacionesRequest>
        <ns2:Usuario>{fn:data($RequestConsultar/ns1:Usuario)}</ns2:Usuario>
        <ns2:historial>{fn:data($RequestConsultar/ns1:historial)}</ns2:historial>
        <ns2:aprobacionCliente>{fn:data($RequestConsultar/ns1:aprobacionCliente)}</ns2:aprobacionCliente>
    </ns2:ConsultarVotacionesRequest>
};

local:func($RequestConsultar)
