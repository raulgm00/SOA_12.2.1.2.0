xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarOperacionMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarDetalleVotacion/V1/Schema/ConsultarOperacionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarOperacionBO";

declare variable $requestConsultarOperacionMessage as element() (:: schema-element(ns1:requestConsultarOperacionMessage) ::) external;

declare function local:func($requestConsultarOperacionMessage as element() (:: schema-element(ns1:requestConsultarOperacionMessage) ::)) as element() (:: element(*, ns2:ConsultarVotacionUsuarioRequestType) ::) {
    <ns2:ConsultarVotacionUsuarioRequest>
        <ns2:idSolicitudAprobacion>{fn:data($requestConsultarOperacionMessage/ns1:operacion/con:idVotacion)}</ns2:idSolicitudAprobacion>
    </ns2:ConsultarVotacionUsuarioRequest>
};

local:func($requestConsultarOperacionMessage)
