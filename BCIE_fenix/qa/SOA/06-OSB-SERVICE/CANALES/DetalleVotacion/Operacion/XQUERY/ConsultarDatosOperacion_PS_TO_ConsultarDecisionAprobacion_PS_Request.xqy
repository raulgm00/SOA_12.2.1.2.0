xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ConsultarOperacionMO";
(:: import schema at "../../../../MDS/Canales/Votacion/ConsultarDetalleVotacion/V1/Schema/ConsultarOperacionMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)

declare namespace con = "http://www.bcie.org/ConsultarOperacionBO";

declare variable $requestConsultarOperacionMessage as element() (:: schema-element(ns1:requestConsultarOperacionMessage) ::) external;

declare function local:func($requestConsultarOperacionMessage as element() (:: schema-element(ns1:requestConsultarOperacionMessage) ::)) as element() (:: schema-element(ns2:ConsultarDecisionAprobacionByIdAprobacionRequest) ::) {
    <ns2:ConsultarDecisionAprobacionByIdAprobacionRequest>
        <ns2:idAprobacion>{fn:data($requestConsultarOperacionMessage/ns1:operacion/con:nivelAprobacion)}</ns2:idAprobacion>
    </ns2:ConsultarDecisionAprobacionByIdAprobacionRequest>
};

local:func($requestConsultarOperacionMessage)
