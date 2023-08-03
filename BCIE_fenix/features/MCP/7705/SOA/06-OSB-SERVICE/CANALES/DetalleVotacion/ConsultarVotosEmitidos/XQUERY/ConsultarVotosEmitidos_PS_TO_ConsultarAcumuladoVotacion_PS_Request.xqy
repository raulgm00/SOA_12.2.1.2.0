xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarVotosEmitidosMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarVotosEmitidos/V1/Schema/ConsultarVotosEmitidosMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarVotosEmitidosBO";

declare variable $requestConsultarVotosEmitidosMessage as element() (:: schema-element(ns1:requestConsultarVotosEmitidosMessage) ::) external;

declare function local:func($requestConsultarVotosEmitidosMessage as element() (:: schema-element(ns1:requestConsultarVotosEmitidosMessage) ::)) as element() (:: schema-element(ns2:ConsultarAcumuladoVotacionRequest) ::) {
    <ns2:ConsultarAcumuladoVotacionRequest>
        <ns2:idSolicitudAprobacion>{fn:data($requestConsultarVotosEmitidosMessage/ns1:solicitudAprobacion/con:idSolicitudAprobacion)}</ns2:idSolicitudAprobacion>
    </ns2:ConsultarAcumuladoVotacionRequest>
};

local:func($requestConsultarVotosEmitidosMessage)
