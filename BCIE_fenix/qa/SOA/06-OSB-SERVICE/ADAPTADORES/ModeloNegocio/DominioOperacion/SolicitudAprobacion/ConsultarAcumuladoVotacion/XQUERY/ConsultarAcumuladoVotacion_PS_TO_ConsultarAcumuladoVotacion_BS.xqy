xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/SolicitudAprobacionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/SolicitudAprobacion/V1/Schema/SolicitudAprobacionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarVotacion_BD";
(:: import schema at "../XSD/ConsultarAcumuladoVotacion_BD.xsd" ::)

declare variable $ConsultarAcumuladoVotacionRequest as element() (:: schema-element(ns1:ConsultarAcumuladoVotacionRequest) ::) external;

declare function local:func($ConsultarAcumuladoVotacionRequest as element() (:: schema-element(ns1:ConsultarAcumuladoVotacionRequest) ::)) as element() (:: schema-element(ns2:ConsultarVotacion_BDInput) ::) {
    <ns2:ConsultarVotacion_BDInput>
        <ns2:idVotacion>{fn:data($ConsultarAcumuladoVotacionRequest/ns1:idSolicitudAprobacion)}</ns2:idVotacion>
        <ns2:idVotacion>{fn:data($ConsultarAcumuladoVotacionRequest/ns1:idSolicitudAprobacion)}</ns2:idVotacion>
        <ns2:idVotacion>{fn:data($ConsultarAcumuladoVotacionRequest/ns1:idSolicitudAprobacion)}</ns2:idVotacion>
    </ns2:ConsultarVotacion_BDInput>
};

local:func($ConsultarAcumuladoVotacionRequest)
